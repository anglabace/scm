package com.genscript.gsscm.serv.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.serv.entity.ServiceItemBean;

@Repository
public class ServiceItemBeanDao extends HibernateDao<ServiceItemBean, Integer> {
	@Autowired
	private ServiceRelationDao serviceRelationDao;
	
	public Page<ServiceItemBean> findAssociatedItemPage(final Page<ServiceItemBean> page,
			final List<PropertyFilter> filterList, final Integer serviceId){
		Criterion criterion1;
		List<Integer> serviceIdList = serviceRelationDao.getAssociatedItemIdList(serviceId);
		if(serviceIdList == null || serviceIdList.size() == 0){
			return null;
		}
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
		
		criterion1 = Restrictions.in("serviceId", serviceIdList);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(criterion1);
		Criterion criterion2 = Restrictions.eq("baseCatalog", "Y");
		Criterion criterion3 = Restrictions.eq("catalogStatus", "ACTIVE");
		conjunction.add(criterion2);
		conjunction.add(criterion3);
		criterionList.add(conjunction);
		
		Criterion[] criterions = criterionList.toArray(new Criterion[criterionList.size()]);
		return findPage(page, criterions);
	}

    public  ServiceItemBean getGeneServiceItem(String  catalogNo){
        String hql = " from ServiceItemBean where catalogNo=?";
        return this.findUnique(hql, catalogNo);
    }
}
