package com.genscript.gsscm.basedata.dao;

import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbDropdownList;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PbDropdownListDao extends HibernateDao<PbDropdownList, Integer> {

    public List<PbDropdownListOptions> getUpdateReasonDropdownList(String type, Integer cls_id){
        List<PbDropdownListOptions> dropdowns = new ArrayList<PbDropdownListOptions>();
        String typeCol = "product_cls_ids";
        if("SERVICE".equals(type)){
            typeCol = "service_cls_ids";
        }
        String sql = "select reason_type, reason from order.order_update_reason where  CONCAT(','," + typeCol + ", ',') like '%," + cls_id + ",%'";
        Query query = this.getSession().createSQLQuery(sql);
        List list = query.list();
        if(list != null && list.size() > 0){
            for(Object objTemp : list){
                PbDropdownListOptions drop = new PbDropdownListOptions();
                Object[] objs = (Object[])objTemp;
                drop.setValue(objs[0].toString());
                drop.setText(objs[1].toString());
                dropdowns.add(drop);
            }
        }
        return dropdowns;
    }

}
