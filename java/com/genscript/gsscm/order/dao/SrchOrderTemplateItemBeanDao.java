package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.SrchOrderTemplateItemBean;

@Repository
public class SrchOrderTemplateItemBeanDao extends HibernateDao<SrchOrderTemplateItemBean, Integer>{
	@SuppressWarnings("unchecked")
	public List getOrderProductTempList(int owner, int custNo){
		String hql = "from SrchOrderTemplateItemBean o where o.owner=:owner and o.custNo=:custNo and o.itemNo IS NOT NULL GROUP BY o.name";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("owner", owner);
		map.put("custNo", custNo);
		return find(hql, map);
	}
}
