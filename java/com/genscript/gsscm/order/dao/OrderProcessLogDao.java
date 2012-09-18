package com.genscript.gsscm.order.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genscript.gsscm.order.entity.StatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;

@Repository
public class OrderProcessLogDao extends HibernateDao<OrderProcessLog, Integer> {

    private static final String ITEM_STATUS = "select o.priorStat,o.currentStat,o.processDate,o.reason,u.loginName,o.note from OrderProcessLog o left join o.processedBy u where o.orderItemId=?";
    private static final String ORDER_STATUS = "select o.priorStat,o.currentStat,o.processDate,o.reason,u.loginName,o.note from OrderProcessLog o left join o.processedBy u where o.orderItemId=null and o.orderNo=?";
    private static final String ORDER_LAST_CONFIRM_DATE = "FROM OrderProcessLog o where o.orderItemId=null and o.orderNo=? and o.currentStat=? ORDER BY o.processDate DESC LIMIT 1";
    private static final String ORDER_FIRST_CONFIRM_DATE = "FROM OrderProcessLog o where o.orderNo=? and o.currentStat=? ORDER BY o.processDate ASC LIMIT 1";
    private static final String ORDER_STATUS_RECORDS = "FROM OrderProcessLog o where o.orderItemId=null and o.orderNo=:orderNo and o.currentStat=:currentStat ORDER BY o.processDate DESC";
    private static final String ORDERS_LAST_CONFIRM_DATE = "FROM OrderProcessLog o where o.orderItemId=null and o.orderNo in(?) and o.currentStat=? ORDER BY o.processDate DESC LIMIT 1";
    private static final String ORDER_HISTORY_STATUS = "FROM OrderProcessLog where orderItemId = ? AND currentStat NOT IN('PI','IS') ORDER BY processDate DESC LIMIT 1 ";

    @Autowired
    private StatusListDao statusListDao;


    @SuppressWarnings("rawtypes")
    public List<ProcessLogDTO> getItemStatusHist(Integer orderItemId) {
        List list = find(ITEM_STATUS, orderItemId);
        List<ProcessLogDTO> processLogDTOList = null;
        String CurrentStatname = "";
        if (list != null) {
            processLogDTOList = new ArrayList<ProcessLogDTO>();
            /*	for (int i = 0; i < list.size(); i++) {
                   Object[] obj = (Object[]) list.get(i);
                   String priorStat = (String) obj[0];
                   String currentStat = (String) obj[1];
                   String reason = (String) obj[3];
                   String loginName = (String) obj[4];
                   String note = (String) obj[5];
                   ProcessLogDTO processLogDTO = new ProcessLogDTO();
                   processLogDTO.setPriorStat(priorStat);
                   processLogDTO.setCurrentStat(currentStat);
                   processLogDTO.setProcessDate((Date)obj[2]);
                   processLogDTO.setReason(reason);
                   processLogDTO.setUpdateBy(loginName);
                   processLogDTO.setNote(note);
                   processLogDTOList.add(processLogDTO);
               }*/


            for (int i = 0; i < list.size(); i++) {
                ProcessLogDTO processLogDTO = new ProcessLogDTO();
                Object[] obj = (Object[]) list.get(i);
                String priorStat = (String) obj[0];
                String currentStat = (String) obj[1];
                String reason = (String) obj[3];
                String loginName = (String) obj[4];
                String note = (String) obj[5];
                processLogDTO.setPriorStat(priorStat);
                StatusList statusList = this.statusListDao.findByHqlCode(currentStat);
                if (statusList != null) {
                    CurrentStatname = statusList.getName();
                }
                processLogDTO.setCurrentStat(CurrentStatname);
                processLogDTO.setCurrentStatName(CurrentStatname);
                processLogDTO.setProcessDate((Date) obj[2]);
                processLogDTO.setReason(reason);
                processLogDTO.setUpdateBy(loginName);
                processLogDTO.setNote(note);
                processLogDTOList.add(processLogDTO);
            }
        }
        return processLogDTOList;
    }


    @SuppressWarnings("rawtypes")
    public List<ProcessLogDTO> getOrderStatusHist(Integer orderNo) {
        List list = find(ORDER_STATUS, orderNo);
        List<ProcessLogDTO> processLogDTOList = null;
        if (list != null) {
            processLogDTOList = new ArrayList<ProcessLogDTO>();
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                String priorStat = (String) obj[0];
                String currentStat = (String) obj[1];
                String reason = (String) obj[3];
                String loginName = (String) obj[4];
                String note = (String) obj[5];
                ProcessLogDTO processLogDTO = new ProcessLogDTO();
                processLogDTO.setPriorStat(priorStat);
                processLogDTO.setCurrentStat(currentStat);
                processLogDTO.setProcessDate((Date) obj[2]);
                processLogDTO.setReason(reason);
                processLogDTO.setUpdateBy(loginName);
                processLogDTO.setNote(note);
                processLogDTOList.add(processLogDTO);
            }
        }
        return processLogDTOList;
    }

    /**
     * 取得最后一次confirm 时候的时间。
     *
     * @param orderNo
     * @return
     */
    public Date getOrderLastConfirmDate(Integer orderNo) {
        OrderProcessLog log = (OrderProcessLog) this.createQuery(ORDER_LAST_CONFIRM_DATE, orderNo, OrderStatusType.CC.value()).setMaxResults(1).uniqueResult();
        if (log != null) {
            return log.getProcessDate();
        } else {
            return null;
        }
    }
    
    /**
     * 取得最后一次confirm 时候的时间。
     *
     * @param orderNo
     * @return
     */
    public Date getOrderFirstConfirmDate(Integer orderNo) {
        OrderProcessLog log = (OrderProcessLog) this.createQuery(ORDER_FIRST_CONFIRM_DATE, orderNo, OrderStatusType.CC.value()).setMaxResults(1).uniqueResult();
        if (log != null) {
            return log.getProcessDate();
        } else {
            return null;
        }
    }

    /**
     * 通过orderItemId查询order history status
     *
     * @param orderItemId
     * @return
     */
    public OrderProcessLog getOrderHistoryStatus(int orderItemId) {
        return this.findUnique(ORDER_HISTORY_STATUS, orderItemId);
    }
    
    /**
     * 取得最后一次confirm 时候的记录。
     * @author Zhang Yong
     * @param orderNo
     * @return
     */
    public OrderProcessLog getOrderLastConfirm(Integer orderNo) {
    	return (OrderProcessLog) this.createQuery(ORDER_LAST_CONFIRM_DATE, orderNo, OrderStatusType.CC.value()).setMaxResults(1).uniqueResult();
    }
    
    /**
     * 取得Order状态改变记录。
     * @author Zhang Yong
     * @param orderNo
     * @param status
     * @return
     */
    public List<OrderProcessLog> getOrderStatus(Integer orderNo, String status) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderNo", orderNo);
    	map.put("currentStat", status);
    	return this.find(ORDER_STATUS_RECORDS, map);
    }
    
    /**
     * 取得最后一次confirm 时候的记录。
     * @author mingrs
     * @param orderNo
     * @return
     */
    public OrderProcessLog getOrdersLastConfirm(String orderNos) {
    	return (OrderProcessLog) this.createQuery(ORDERS_LAST_CONFIRM_DATE, orderNos, OrderStatusType.CC.value()).setMaxResults(1).uniqueResult();
    }
}
