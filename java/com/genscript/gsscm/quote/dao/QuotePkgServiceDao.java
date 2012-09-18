package com.genscript.gsscm.quote.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPkgService;
import com.genscript.gsscm.quote.entity.QuotePkgService;

@Repository
public class QuotePkgServiceDao extends HibernateDao<QuotePkgService, Integer>{

}
