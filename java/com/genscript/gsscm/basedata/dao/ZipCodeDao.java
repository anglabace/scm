package com.genscript.gsscm.basedata.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.ZipCode;

@Repository
public class ZipCodeDao extends HibernateDao<ZipCode, Integer> {
	/**
	 * 根据Country和State获得ZipCode list.
	 * 
	 * @param countryCodeList
	 * @param stateCodeList
	 * @return
	 */
	public Page<ZipCode> getZipCodeList(Page<ZipCode> page, List<String> countryCodeList,
			List<String> stateCodeList) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from ZipCode where 1=1 ";
		if (countryCodeList != null && !countryCodeList.isEmpty()) {
			map.put("countryList", countryCodeList);
			hql += " and country in (:countryList)";
		}
		if (stateCodeList != null && !stateCodeList.isEmpty()) {
			hql += " and state in (:stateList)";
			map.put("stateList", stateCodeList);
		}
		if (page.getOrder()!=null && page.getOrder().trim().length()>0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}
}
