package com.genscript.gsscm.quote.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.entity.StatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteProcessLog;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;

@Repository
public class QuoteProcessLogDao extends HibernateDao<QuoteProcessLog, Integer> {


    @Autowired
    private StatusListDao statusListDao;
    private static final String ITEM_STATUS = "select q.priorStat,q.currentStat,q.processDate,q.reason,u.loginName,q.note from QuoteProcessLog q left join q.processedBy u where q.quoteItemId=?";
    private static final String QUOTE_STATUS = "select q.priorStat,q.currentStat,q.processDate,q.reason,u.loginName,q.note from QuoteProcessLog q left join q.processedBy u where q.quoteItemId=null and q.quoteNo=?";

    public StatusListDao getStatusListDao() {
        return statusListDao;
    }

    public void setStatusListDao(StatusListDao statusListDao) {
        this.statusListDao = statusListDao;
    }

    @SuppressWarnings("rawtypes")
    public List<ProcessLogDTO> getItemStatusHist(Integer quoteItemId) {
        List list = find(ITEM_STATUS, quoteItemId);
        List<ProcessLogDTO> processLogDTOList = null;
        if (list != null) {
            String CurrentStatname = "";
            processLogDTOList = new ArrayList<ProcessLogDTO>();
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
    public List<ProcessLogDTO> getQuoteStatusHist(Integer quoteNo) {
        List list = find(QUOTE_STATUS, quoteNo);
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
}
