package com.genscript.gsscm.basedata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbCurrency;

@Repository
public class CurrencyDao extends HibernateDao<PbCurrency, Integer> {

	private static final String GET_SYMBOL = "select c.symbol from PbCurrency c where c.currencyCode=?";
	
	public String getCurrencySymbol(String currencyCode){
		return findUnique(GET_SYMBOL, currencyCode);
	}
	
	/**
	 * 
	 */
	public List<PbCurrency> findAll(String order,String orderBy) {
		String hql = "from PbCurrency order by "+orderBy+" "+order;
		return this.find(hql);
	}
	
	/**
	 * 通过currencyCode查询整个对象
	 * @author Zhang Yong
	 * @param currencyCode
	 * @return
	 */
	public PbCurrency getByCurrencyCode (String currencyCode) {
		return this.findUniqueBy("currencyCode", currencyCode);
	}
}
