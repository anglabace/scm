package com.genscript.gsscm.order.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.ExchRateByDate;

@Repository
public class ExchRateByDateDao extends HibernateDao<ExchRateByDate, Integer> {
	private static final String GET_CURRENCY_RATE = "select e.rate from ExchRateByDate e where e.fromCurrency = :fromCurrency and e.toCurrency = :toCurrency and e.effFrom <= :exchRateDate and e.effTo >= :exchRateDate";
	
	public Double getCurrencyRate(final String fromCurrency, final String toCurrency, final Date exchRateDate){
		if (fromCurrency.equals(toCurrency)) {
			return 1.0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromCurrency", fromCurrency);
		map.put("toCurrency", toCurrency);
		map.put("exchRateDate", exchRateDate);
		return (Double)findUnique(GET_CURRENCY_RATE, map);
	}
}
