package com.genscript.gsscm.accounting.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerBean;

@Repository
public class ArCustomerBeanDao extends HibernateDao<CustomerBean, Integer>{

	private static final String QUERY_CUSTOMER_ADDRESS = "select c.custNo,c.altNo,c.status,c.firstName,c.midName,c.lastName,c.state,c.country,c.zipCode,c.busEmail,c.organization.name,c.division.name,c.department.name,c.salesTerritory.territoryCode,c.salesGroup.groupCode,c.salesContact.fullName,c.techSupport.fullName,c.status,a.firstName,a.midName,a.lastName,c.busPhone from Customer c left join c.addresses a where a.addrType = 'contact' and a.defaultFlag = 'Y'";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<CustomerBean> getCustomerList(Page<CustomerBean> page) {
		Assert.notNull(page, "page can not be null");
		Integer pageSize;
		Criterion criterion = Restrictions.eq("status", "ACTIVE");
		Criteria c = createCriteria(criterion);

		if (page.isAutoCount()) {
			Long count = 0L;
			String countHql = "select count(c.custNo) from Customer c where c.status = 'ACTIVE'";

			try {
				count = findUnique(countHql);
			} catch (Exception e) {
				throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
			}
			page.setTotalCount(Long.valueOf(count));
		}
		pageSize = page.getPageSize();
		if(pageSize!= null && pageSize!= 0)
			setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}
	
	@SuppressWarnings("rawtypes")
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page) {
		Assert.notNull(page, "page can not be null");
		List customers = null;
		List<CustomerBean> customerDTO = new ArrayList<CustomerBean>();
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(QUERY_CUSTOMER_ADDRESS);
			page.setTotalCount(totalCount);
		}
		
		Query q = createQuery(QUERY_CUSTOMER_ADDRESS);
		setPageParameter(q, page);
		customers = q.list();
		 if (null != customers && 0 < customers.size()) {
			 for(int i=0;i<customers.size();i++){
				 Object[] obj = (Object[])customers.get(i);
				 CustomerBean customerMainDTO = new CustomerBean();
				 customerMainDTO.setCustNo(Integer.parseInt((obj[0]==null)?"0":obj[0].toString()));
				 customerMainDTO.setAltNo((String)obj[1]);
				 customerMainDTO.setStatus((String)obj[2]);
				 customerMainDTO.setFirstName((String)obj[3]);
				 customerMainDTO.setMidName((String)obj[4]);
				 customerMainDTO.setLastName((String)obj[5]);
				 customerMainDTO.setBusEmail((String)obj[6]);
				 customerMainDTO.setOrganizationName((String)obj[7]);
				 customerMainDTO.setBusPhone((String)obj[11]);
				 customerDTO.add(customerMainDTO);
			 }
			 
			 page.setResult(customerDTO);
			 return page;
		 }else{
			 return null;
		 }
	}
	
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page, List<PropertyFilter> filters) {
        String hql = "from CustomerBean where 1=1 ";
        for(PropertyFilter filter : filters){
            if("java.lang.String".equals(filter.getPropertyType().getName())){
                hql = hql + " and " + filter.getPropertyName() + " " + filter.getMatchType() + " '%" + filter.getPropertyValue()+"%'";
            }else if("EQ".equals(filter.getMatchType().name())){
                hql = hql + " and " + filter.getPropertyName() + "=" + filter.getPropertyValue();
            }
        }
		return findPage(page, hql);
	}
	
	public Page<CustomerBean> advSearchCustomer(Page<CustomerBean> page, List<PropertyFilter> filters, Set<Integer> custNoSet){
		Criterion criterion1;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
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
		if(custNoSet!= null && custNoSet.size()>0){
			criterion1 = Restrictions.in("custNo", custNoSet);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(criterion1);
			criterionList.add(disjunction);
		}
		Criterion[] criterions = criterionList.toArray(new Criterion[criterionList.size()]);
		return findPage(page, criterions);
	}
}
