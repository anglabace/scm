package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceListBean;

@Repository
public class ServiceListBeanDao extends HibernateDao<ServiceListBean, Integer> {

	/**
	 * 查询列表
	 * @author Zhang Yong
	 * @param page
	 * @param filters
	 * @param criterion
	 * @return
	 */
	public Page<ServiceListBean> findPageByFilter (Page<ServiceListBean> page,
			List<PropertyFilter> filters, Criterion criterion) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		if (criterion != null) {
			criterionList.add(criterion);
		}
		return this.findPage(page, criterionList.toArray(new Criterion[criterionList.size()]));
	}
}
