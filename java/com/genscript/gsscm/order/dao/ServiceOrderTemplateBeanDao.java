package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.ServiceOrderTemplateBean;

@Repository
public class ServiceOrderTemplateBeanDao extends HibernateDao<ServiceOrderTemplateBean, Integer> {

	/**
	 * @param custNo
	 * @param orderNo
	 * @return
	 */
	public List<ServiceOrderTemplateBean> searchServiceOrderTemplateItem (Integer custNo, 
			Integer orderNo) {
		String hql = "from ServiceOrderTemplateBean where custNo=:custNo and srcOrderNo=:srcOrderNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		map.put("srcOrderNo", orderNo);
		return this.find(hql, map);
	}
}
