package com.genscript.gsscm.quote.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteService;

@Repository
public class QuoteServiceDao extends HibernateDao<QuoteService, Integer> {

}
