package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.ServiceQuoteTemplateBean;

@Repository
public class ServiceQuoteTemplateBeanDao extends HibernateDao<ServiceQuoteTemplateBean, Integer> {
	/**
	 * @param custNo
	 * @param orderNo
	 * @return
	 */
	public List<ServiceQuoteTemplateBean> searchServiceQuoteTemplateItem (Integer custNo, 
			Integer quoteNo) {
		String hql = "from ServiceQuoteTemplateBean where custNo=:custNo and srcQuoteNo=:srcQuoteNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		map.put("srcQuoteNo", quoteNo);
		return this.find(hql, map);
	}
}
