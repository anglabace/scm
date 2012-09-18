package com.genscript.gsscm.quote.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteTemplateItemBean;

@Repository
public class QuoteTemplateItemBeanDao extends HibernateDao<QuoteTemplateItemBean, Integer>{
	public Page<QuoteTemplateItemBean> findPage(final Page<QuoteTemplateItemBean> page,final List<PropertyFilter> filterList,List<String> catalogNoList){
		Criterion criterion1;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filterList) {
			if (!filter.isMultiProperty()) {
				Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(),
						filter.getPropertyType(), filter.getMatchType());
				criterionList.add(criterion);
			} else {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(), filter
							.getPropertyType(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		if(catalogNoList!= null){
			criterion1 = Restrictions.not(Restrictions.in("catalogNo", catalogNoList));
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(criterion1);
			criterionList.add(disjunction);
		}
		Criterion[] criterions = criterionList.toArray(new Criterion[criterionList.size()]);
		return findPage(page, criterions);
	}
}
