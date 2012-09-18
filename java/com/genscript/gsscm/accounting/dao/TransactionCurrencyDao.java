package com.genscript.gsscm.accounting.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbCurrency;

@Repository
public class TransactionCurrencyDao extends HibernateDao<PbCurrency, Integer> {

	private static final String GET_SYMBOL = "select c from PbCurrency c where c.currencyCode=?";
	
	public PbCurrency getCurrencySymbol(String currencyCode){
		return findUnique(GET_SYMBOL, currencyCode);
	}
}
