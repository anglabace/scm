package com.genscript.gsscm.quote.dao;


import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.dto.QuoteMainBeanDTO;
import org.springframework.stereotype.Repository;


@Repository
public class QuoteMainDtoDao extends HibernateDao<QuoteMainBeanDTO, Integer> {

}
