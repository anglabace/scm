package com.genscript.gsscm.order.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.TaxRate;

@Repository
public class TaxRateDao extends HibernateDao<TaxRate, Integer>{
	private static final String GET_COUNTRY_STATE_TAX_RATE = "select t.taxRate from TaxRate t where t.country=:country and t.state=:state";
	private static final String GET_COUNTRYTAX_RATE = "select t.taxRate from TaxRate t where t.country=?";
	
	public Double getCountryStateRate(final String country, final String state){
		if (country != null && !("").equals(country.trim())) {
			List<Double> taxRateList = new ArrayList<Double>();
			if (state != null && !("").equals(state.trim())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("country", country.trim());
				map.put("state", state.trim());
				taxRateList = this.find(GET_COUNTRY_STATE_TAX_RATE, map);
			} else {
				taxRateList = this.find(GET_COUNTRYTAX_RATE, country);
			}
			if (taxRateList != null && taxRateList.size() > 0) {
				return (Double) taxRateList.get(0);
			}
		}
		return 0d;
	}
	
	/**
	 * 由status和state为空这两条件搜索countryCode
	 */
	public List<String> getCountryCode(String status) {
		String hql = "select distinct country from TaxRate where status=? and state is null";
		return this.find(hql, status);
	}
	
	/**
	 * 由countryCode和state为空这两个条件搜索TaxRate
	 */
	public TaxRate getTaxRate(String countryCode) {
		String hql = "from TaxRate where country=? and state is null";
		List<TaxRate> list = this.find(hql, countryCode);
		return list!=null&&list.size()>0?list.get(0):null;
	}
	
	/**
	 * 由country和state两条件搜索TaxRate
	 */
	public TaxRate getTaxRate(String country,String state) {
		String hql = "from TaxRate where country=? and state=?";
		List<TaxRate> list = this.find(hql, country,state);
		return list!=null&&list.size()>0?list.get(0):null;
	}
	
	/**
	 * 由country条件删除TaxRate
	 */
	public void deleteByCountry(String country) {
		String hql = "delete from TaxRate where country=?";
		this.batchExecute(hql, country);
	}
}
