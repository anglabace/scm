package com.genscript.gsscm.purchase.dao;

import org.springframework.stereotype.Repository;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrderBean;

@Repository
public class PurchaseOrderBeanDao extends HibernateDao<PurchaseOrderBean, Integer> {

}
