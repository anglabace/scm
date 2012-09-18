package com.genscript.gsscm.customer.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.customer.entity.SimpCustProductPriceBean;

@Repository
public class CustomerPriceBeanDao extends HibernateDao<CustomerPriceBean, Integer> {
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;

	public List<CustomerPriceBean> distinctList(List<CustomerPriceBean> list) {
		return distinct(list);
	}
	
	/**                                         、
	 * 取得unitPrice
	 * @param custNo
	 * @param catalogNo
	 * @return
	 */
	public Double getUnitPrice(Integer custNo, String catalogNo){
		CustomerPriceBean customerPriceBean = this.findUnique(Restrictions.and(
				Restrictions.eq("catalogNo", catalogNo),
				Restrictions.eq("custNo", custNo)));
		if(customerPriceBean == null){
			throw new RuntimeException("Product Item not existed.");
		}
		return customerPriceBean.getUnitPrice();
	}
	
	/**
	 * 取得unitPrice
	 * @param custNo
	 * @param catalogNo
	 * @return
	 */
	public Double getSpecialPrice(Integer custNo, String catalogNo, Integer quantity, Double amountTotal){
		CustomerSpecialPrice customerSpecialPrice = customerSpecialPriceDao
		.findUnique(Restrictions.and(
				Restrictions.eq("catalogNo", catalogNo),
				Restrictions.eq("custNo", custNo)));
		if(customerSpecialPrice == null){
			return null;
		}
		BigDecimal amountTotalB = new BigDecimal(amountTotal);
		Date oDate = new Date();
		Integer minQty = customerSpecialPrice.getMinQty();
		BigDecimal orderTotal = customerSpecialPrice.getOrderTotal();
		Date effFrom = customerSpecialPrice.getEffFrom();
		Date effTo = customerSpecialPrice.getEffTo();
		
		if (orderTotal != null && effFrom != null && effTo != null
				&& quantity >= minQty
				&& amountTotalB.compareTo(orderTotal) >= 0
				&& oDate.compareTo(effFrom) >= 0
				&& oDate.compareTo(effTo) <= 0) {
			BigDecimal sPrice = customerSpecialPrice.getUnitPrice();
			if(sPrice != null){
				return sPrice.doubleValue();
			}
		}else{
			BigDecimal standardPrice = customerSpecialPrice.getStandardPrice();
			if(standardPrice != null){
				return standardPrice.doubleValue();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param custNo
	 * @param catalogNo
	 * @return
	 */
	public CustomerPriceBean getCustomerPrice(Integer custNo, String catalogNo){
		return this.findUnique(Restrictions.and(
				Restrictions.eq("catalogNo", catalogNo),
				Restrictions.eq("custNo", custNo)));
	}

	@SuppressWarnings("unchecked")
	public Page<CustomerPriceBean> findPage(final Page<CustomerPriceBean> page,
			final List<PropertyFilter> filterList, List<String> catalogNoList) {
		Criterion criterion1;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		boolean categoryFlag = false;
		for (PropertyFilter filter : filterList) {
			if (!filter.isMultiProperty()) {
				Criterion criterion = buildPropertyFilterCriterion(
						filter.getPropertyName(), filter.getPropertyValue(),
						filter.getPropertyType(), filter.getMatchType());
				criterionList.add(criterion);
				if(filter.getPropertyName().equals("categoryName"))
				{
					categoryFlag = true;
				}
			} else {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param,
							filter.getPropertyValue(),
							filter.getPropertyType(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		if (catalogNoList != null) {
			criterion1 = Restrictions.not(Restrictions.in("catalogNo",
					catalogNoList));
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(criterion1);
			criterionList.add(disjunction);
		}
		Criterion[] criterions = criterionList
				.toArray(new Criterion[criterionList.size()]);
		if(categoryFlag == true){
			Assert.notNull(page, "page can not be null");
			Integer pageSize;
			Criteria c = createCriteria(criterions);
			Criteria criteria = getSession().createCriteria(SimpCustProductPriceBean.class);
			for (Criterion cr : criterions) {
				criteria.add(cr);
			}
			
			if (page.isAutoCount()) {
				int totalCount = countCriteriaResult(criteria);
				page.setTotalCount(Long.valueOf(totalCount));
				if(page.getPageNo() > page.getTotalPages()){
					page.setPageNo(page.getTotalPages().intValue());
				}
			}
			pageSize = page.getPageSize();
			if(pageSize!= null && pageSize!= 0)
				setPageParameter(c, page);
			List result = c.list();
			page.setResult(result);
			return page;
		}
		return findPage(page, criterions);
	}

	@SuppressWarnings("unchecked")
	public Page<CustomerPriceBean> searchProductItemPrice(
			final Page<CustomerPriceBean> page, final Integer custNo,
			Object catalogNoList) {
		Assert.notNull(page, "page can not be null");
		Integer pageSize;
		Criteria c;
		if (catalogNoList != null) {
			List<String> catalogNos = (List<String>) catalogNoList;
			Criterion criterion1 = Restrictions.not(Restrictions.in(
					"catalogNo", catalogNos));
			Criterion criterion2 = Restrictions.eq("custNo", custNo);
			c = createCriteria(criterion1, criterion2);
			if (page.isAutoCount()) {
				Long count = 0L;
				Map<String, Object> map = Collections.singletonMap(
						"catalogNoList", catalogNoList);
				String countHql = "select count(p.productId) from Product p where not (p.catalogNo in (:catalogNoList))";

				try {
					count = findUnique(countHql, map);
				} catch (Exception e) {
					throw new RuntimeException(
							"hql can't be auto count, hql is:" + countHql, e);
				}
				page.setTotalCount(Long.valueOf(count));
			}
		} else {
			Criterion criterion2 = Restrictions.eq("custNo", custNo);
			c = createCriteria(criterion2);
			if (page.isAutoCount()) {
				Long count = 0L;
				String countHql = "select count(p.productId) from Product p ";
				try {
					count = findUnique(countHql);
				} catch (Exception e) {
					throw new RuntimeException(
							"hql can't be auto count, hql is:" + countHql, e);
				}
				page.setTotalCount(Long.valueOf(count));
			}
		}

		pageSize = page.getPageSize();
		if (pageSize != null && pageSize != 0)
			setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public Page<CustomerPriceBean> searchProductItemPrice(
			final Page<CustomerPriceBean> page, final Integer custNo, String status) {
		Assert.notNull(page, "page can not be null");
		Integer pageSize;
		Criterion criterion1 = Restrictions.eq("status", status);
		Criterion criterion2 = Restrictions.eq("custNo", custNo);
		Criteria c = createCriteria(criterion1,criterion2);
		if (page.isAutoCount()) {
			Long count = 0L;
			String countHql = "select count(p.productId) from CustomerPriceBean p where p.custNo = ? and p.status=?";
			try {
				count = findUnique(countHql,custNo,status);
			} catch (Exception e) {
				throw new RuntimeException(
						"hql can't be auto count, hql is:" + countHql, e);
			}
			page.setTotalCount(Long.valueOf(count));
		}
		pageSize = page.getPageSize();
		if (pageSize != null && pageSize != 0)
			setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}
	
	/**
	 * 通过custNo和name或shortDesc查询列表
	 * @author Zhang Yong
	 * @param custNo
	 * @param searchKey
	 * @return
	 */
	public List<CustomerPriceBean> findCustomerPrice (Integer custNo, String searchKey) {
		String hql = "from CustomerPriceBean where custNo = ? and (name = ? or shortDesc = ?)";
		return this.find(hql, searchKey, searchKey);
	}
	
}
