package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceDocTemplate;

@Repository
public class ServiceDocTemplateDao extends HibernateDao<ServiceDocTemplate, Integer>{
	
	/**
	 * 根据filters里保存的条件查询serviceDocTemplate并分页
	 * @param serviceDocTemplatePage 分页对象
	 * @param filters 查询条件
	 * @return
	 */
	public Page<ServiceDocTemplate> findPageByFilter(final Page<ServiceDocTemplate> serviceDocTemplatePage,final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(serviceDocTemplatePage, criterionList.toArray(new Criterion[criterionList
				.size()]));
		
	}

}
