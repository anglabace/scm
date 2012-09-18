package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.ManufacturingClerksBean;
@Repository
public class ManufacturingClerksBeanDao extends HibernateDao<ManufacturingClerksBean, Integer>{
	
	/**
	 * 根据filters里保存的条件查询ManufacturingClerks并分页
	 * @param manClerksPage 分页对象
	 * @param filters 查询条件
	 * @return
	 */
	public Page<ManufacturingClerksBean> findPageByFilter(final Page<ManufacturingClerksBean> manClerksPage,final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		return findPage(manClerksPage, criterionList.toArray(new Criterion[criterionList
				.size()]));
	}


}
