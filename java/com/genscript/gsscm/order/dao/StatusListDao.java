package com.genscript.gsscm.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.order.entity.StatusList;

@Repository
public class StatusListDao extends HibernateDao<StatusList, Integer> {

    private static final String GET_STATUS = "from StatusList s where s.type=? and s.code=?";

    /**
     * 获得Order, Quote, OrderItem , OrderItem从现有状态可以更新到status的列表.
     *
     * @param type
     * @param code
     * @param orderStatus
     * @return
     */
    public List<StatusList> getStatusDropDownList(final String type,
                                                  final String code, String orderStatus) {
        List<StatusList> retList = new ArrayList<StatusList>();
        if (type.equals("Q")) {// Quote
            if (code.equals(QuoteStatusType.NW.value())) {
                StatusList statusCO = findUnique(GET_STATUS, type,
                        QuoteStatusType.CO.value());
                StatusList statusVD = findUnique(GET_STATUS, type,
                        QuoteStatusType.VD.value());
                StatusList statusOH = findUnique(GET_STATUS, type,
                        QuoteStatusType.OH.value());
                retList.add(statusCO);
                retList.add(statusVD);
                retList.add(statusOH);
            } else if (code.equals(QuoteStatusType.OP.value())) {
                StatusList statusCO = findUnique(GET_STATUS, type,
                        QuoteStatusType.CO.value());
                StatusList statusVD = findUnique(GET_STATUS, type,
                        QuoteStatusType.VD.value());
                StatusList statusOH = findUnique(GET_STATUS, type,
                        QuoteStatusType.OH.value());
                retList.add(statusCO);
                retList.add(statusVD);
                retList.add(statusOH);
            } else if (code.equals(QuoteStatusType.OH.value())) {
                StatusList statusRV = findUnique(GET_STATUS, type,
                        QuoteStatusType.OP.value());
                StatusList statusCO = findUnique(GET_STATUS, type,
                        QuoteStatusType.CO.value());
                retList.add(statusCO);
                retList.add(statusRV);
            }
        } else if (type.equals("QI")) {// QuoteItem
            if (code.equals(QuoteItemStatusType.CM.value())) {
                if (orderStatus.equals(QuoteStatusType.NW.value())
                        || orderStatus.equals(QuoteStatusType.OP.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            QuoteItemStatusType.CN.value());
                    retList.add(statusCN);
                }
            } else if (code.equals(QuoteItemStatusType.CN.value())) {
                if (orderStatus.equals(QuoteStatusType.NW.value())
                        || orderStatus.equals(QuoteStatusType.OP.value())) {
                    StatusList statusCM = findUnique(GET_STATUS, type,
                            QuoteItemStatusType.CM.value());
                    retList.add(statusCM);
                }
            }
        } else if (type.equals("OI")) {// 大写的o非0
            if (code.equals(OrderItemStatusType.CM.value())) {
                StatusList statusCN = findUnique(GET_STATUS, type,
                        OrderItemStatusType.CN.value());
                retList.add(statusCN);
            } else if (code.equals(OrderItemStatusType.BO.value())) {
                StatusList statusCN = findUnique(GET_STATUS, type,
                        OrderItemStatusType.CN.value());
                retList.add(statusCN);
            } else if (code.equals(OrderItemStatusType.PB.value())) {
                StatusList statusCN = findUnique(GET_STATUS, type,
                        OrderItemStatusType.CN.value());
                retList.add(statusCN);
            } else if (code.equals(OrderItemStatusType.CC.value())) {
                StatusList statusOH = findUnique(GET_STATUS, type,
                        OrderItemStatusType.OH.value());
                retList.add(statusOH);
                if (orderStatus.equals(OrderStatusType.RV.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            OrderItemStatusType.CN.value());
                    retList.add(statusCN);
                }
            } else if (code.equals(OrderItemStatusType.VC.value())) {
                StatusList statusOH = findUnique(GET_STATUS, type,
                        OrderItemStatusType.OH.value());
                retList.add(statusOH);
                if (orderStatus.equals(OrderStatusType.RV.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            OrderItemStatusType.CN.value());
                    retList.add(statusCN);
                }
            } else if (code.equals(OrderItemStatusType.IP.value())) {
                StatusList statusOH = findUnique(GET_STATUS, type,
                        OrderItemStatusType.OH.value());
                StatusList statusVS = findUnique(GET_STATUS, type,
                        OrderItemStatusType.VS.value());
                retList.add(statusOH);
                retList.add(statusVS);
                if (orderStatus.equals(OrderStatusType.RV.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            OrderItemStatusType.CN.value());
                    retList.add(statusCN);
                }
            } else if (code.equals(OrderItemStatusType.PT.value())) {
                StatusList statusOH = findUnique(GET_STATUS, type,
                        OrderItemStatusType.OH.value());
                retList.add(statusOH);
                if (orderStatus.equals(OrderStatusType.RV.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            OrderItemStatusType.CN.value());
                    retList.add(statusCN);
                }
            } else if (code.equals(OrderItemStatusType.CN.value())) {
                if (orderStatus.equals(OrderStatusType.RV.value())
                        || orderStatus.equals(OrderStatusType.NW.value()) || orderStatus.equals(OrderStatusType.BO.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            OrderItemStatusType.CM.value());
                    retList.add(statusCN);
                }
            } else if (code.equals(OrderItemStatusType.OH.value())) {
                StatusList statusCC = findUnique(GET_STATUS, type,
                        OrderItemStatusType.CC.value());
                StatusList statusVC = findUnique(GET_STATUS, type,
                        OrderItemStatusType.VC.value());
                StatusList statusIP = findUnique(GET_STATUS, type,
                        OrderItemStatusType.IP.value());
//				StatusList statusPT = findUnique(GET_STATUS, type,
//						OrderItemStatusType.PT.value());
                retList.add(statusCC);
                retList.add(statusVC);
                retList.add(statusIP);
//				retList.add(statusPT);
                if (orderStatus.equals(OrderStatusType.RV.value())) {
                    StatusList statusCN = findUnique(GET_STATUS, type,
                            OrderItemStatusType.CN.value());
                    retList.add(statusCN);
                }
            }
        }
        return retList;
    }

    /**
     * 由类型和CODE查找statusList
     */
    public StatusList findByTypeAndCode(String type, String code) {
        List<StatusList> list = this.find(GET_STATUS, type, code);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public StatusList findByHqlCode(String code) {
        String hql = "from StatusList s where s.type='OI' and s.code=?";
    //    System.out.println(findUnique(hql, code).toString());
        return this.findUnique(hql, code);
    }
    
    /**
     * 通过Type查询列表
     * @author Zhang Yong
     * @param type
     * @return
     */
    public List<StatusList> getStatusListByType (String type) {
		return this.findBy("type", type);
	}
}
