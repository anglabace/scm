package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.SalesProjectManagerAssignment;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.entity.User;

@Repository
public class SalesProjectManagerAssignmentDao extends HibernateDao<SalesProjectManagerAssignment, Integer>{
	/**
	 * 查找sales_project_manager_assignment的结果页，
	 * @param salesProjectManagerAssignmentPage
	 * @param filters
	 * @return
	 */
	public Page<SalesProjectManagerAssignment> findPageBySomeFilter(final Page<SalesProjectManagerAssignment> salesProjectManagerAssignmentPage,
			final List<PropertyFilter> filters,List<Integer> salesIdList) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		// 判断是否有或的查询条件.
		List<PropertyFilter> delList = new ArrayList<PropertyFilter>();
		int len = filters.size();
		for (int i = 0; i < len; i++) {
			PropertyFilter pf = filters.get(i);
			if (pf.getPropertyName().equals("serviceType")) {
				Criterion criterion = Restrictions.eq("serviceType", Integer
						.parseInt(pf.getPropertyValue().toString()));
				criterionList.add(criterion);
				delList.add(pf);
			}
			if(pf.getPropertyName().equals("resourceName")) {
				delList.add(pf);
			}
		}
		filters.removeAll(delList);// 删除原先的条件.
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		for (Criterion temp : criterions) {
			criterionList.add(temp);
		}
		if (salesIdList != null && !salesIdList.isEmpty()) {
			Criterion criterion = Restrictions.in("salesId", salesIdList);
			criterionList.add(criterion);
		}
		return findPage(salesProjectManagerAssignmentPage, criterionList.toArray(new Criterion[criterionList
				.size()]));
	}
	
	/**
	 * 由serviceType查找SalesRep
	 */
	public List<SalesRep> findSalesRepByServiceType(Integer clsId) {
		String hql = "select t2 from SalesProjectManagerAssignment t1,SalesRep t2 where t2.salesId=t1.salesId and t1.serviceType=?";
		return this.find(hql, clsId);
	}
	
	/**
	 * 查找SalesRep
	 */
	public List<SalesRep> findSalesRep() {
		String hql = "select t2 from SalesProjectManagerAssignment t1,SalesRep t2 where t2.salesId=t1.salesId";
		return this.find(hql);
	}
	
	/**
	 * 通过clsId查找project Manager
	 * @author Zhang Yong
	 * @param clsId
	 * @return
	 */
	public List<User> findProManagerByClsId (Integer clsId) {
		String hql = "select u from SalesProjectManagerAssignment spma, User u where spma.serviceType = ? and spma.salesId = u.userId order by u.userId";
		return this.find(hql, clsId);
	}
}
