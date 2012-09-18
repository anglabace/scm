package com.genscript.gsscm.order.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;

import java.util.List;
import java.util.Map;

@Repository
public class OrderGeneSynthesisDao extends HibernateDao<OrderGeneSynthesis, Integer>{

     public List<OrderGeneSynthesis> getBatchOrderGeneByIds(String ids){
         String sql = "from OrderGeneSynthesis where orderItemId in (" + ids + ")";
         Query query = this.getSession().createQuery(sql);
         return query.list();
     }
}
