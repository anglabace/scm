package com.genscript.gsscm.quote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.SrchServiceQuoteTemplateBean;

@Repository
public class SrchServiceQuoteTemplateBeanDao extends HibernateDao<SrchServiceQuoteTemplateBean, Integer>{
	@SuppressWarnings("unchecked")
	public List getQuoteServiceTempList(int owner, int custNo){
		String hql = "from SrchServiceQuoteTemplateBean o where o.owner=:owner and o.custNo=:custNo and o.itemNo IS NOT NULL GROUP BY o.name";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("owner", owner);
		map.put("custNo", custNo);
		return find(hql, map);
	}
}
