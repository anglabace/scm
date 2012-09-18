package com.genscript.gsscm.order.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.dozer.DozerBeanMapper;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.order.dto.OrderMainBeanDTO;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.opensymphony.xwork2.ActionContext;

@Repository
public class OrderMainBeanDao extends HibernateDao<OrderMainBean, Integer> {
	@Autowired(required = false)
    private DozerBeanMapper dozer;
	/**
	 * 搜索
	 * 
	 * @param page
	 * @param filters
	 * @param orderNoSet
	 *            orderNo范围内过滤
	 * @return
	 */
	public Page<OrderMainBean> advSearchOrder(Page<OrderMainBean> page,
			List<PropertyFilter> filters, Set<Integer> orderNoSet, List<Criterion> criterionList) {
		Criterion criterion1;
		if (filters != null && !filters.isEmpty()) {
			for (PropertyFilter filter : filters) {
				if (!filter.isMultiProperty()) {
					Criterion criterion = buildPropertyFilterCriterion(filter
							.getPropertyName(), filter.getPropertyValue(),
							filter.getPropertyType(), filter.getMatchType());
					criterionList.add(criterion);
				} else {
					Disjunction disjunction = Restrictions.disjunction();
					for (String param : filter.getPropertyNames()) {
						Criterion criterion = buildPropertyFilterCriterion(
								param, filter.getPropertyValue(), filter
										.getPropertyType(), filter
										.getMatchType());
						disjunction.add(criterion);
					}
					criterionList.add(disjunction);
				}
			}
		}
		if (orderNoSet != null && orderNoSet.size() > 0) {
			criterion1 = Restrictions.in("orderNo", orderNoSet);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(criterion1);
			criterionList.add(disjunction);
		}
		Criterion[] criterions = criterionList
				.toArray(new Criterion[criterionList.size()]);
		return findPage(page, criterions);
	}
	
	public Page<OrderMainBean> getOrderByfilter (Page<OrderMainBean> page, 
			List<Criterion> criterionList, List<PropertyFilter> filters, boolean internalOrderManager) {
		String userName = (String) ActionContext.getContext().getSession().get(StrutsActionContant.USER_NAME);
		Integer userId = (Integer) ActionContext.getContext().getSession().get(StrutsActionContant.USER_ID);
		List<Integer> internalOrderNoList = null;
		if (filters != null && filters.size() > 0) {
			if (internalOrderManager && !Constants.USERNAME_ADMIN.equals(userName)) {
				internalOrderNoList = this.getInternalOrderNoByUserId(userId);
				if (internalOrderNoList != null && !internalOrderNoList.isEmpty()) {
					criterionList.add(Restrictions.in("orderNo", internalOrderNoList));
				} else {
					criterionList.add(Restrictions.isNull("orderNo"));
				}
			} else if (!Constants.USERNAME_ADMIN.equals(userName)) {
				filters.add(new PropertyFilter("NES_custType", Constants.INTERNAL_TYPE_CUSTOMER));
			}
			Criterion[] criterions = buildPropertyFilterCriterions(filters);
			for (Criterion temp : criterions) {
				criterionList.add(temp);
			}
			return this.findPage(page, criterionList.toArray(new Criterion[criterionList.size()]));
		} else {
			String select = "SELECT om.order_No, om.status, om.cust_No, cm.first_Name, cm.mid_Name, cm.last_Name, " +
					" cm.addr_Line1, cm.addr_Line2, cm.addr_Line3, cm.city, cm.state, cm.zip_Code, cm.country, " +
					" om.order_Date, om.exch_Rate_Date, om.amount, u.first_Name, u.last_Name, om.src_Quote_No, u2.login_Name, pc.symbol ";
			String from = " FROM order.order om straight_join customer.customer cm on (om.cust_no = cm.cust_no) join system.users u on (om.sales_contact " +
					" = u.user_id) join system.users u2 on (om.created_by = u2.user_id) join common.currency pc on (om.order_currency = pc.currency_code) where ";
			if (internalOrderManager && !Constants.USERNAME_ADMIN.equals(userName)) {
				internalOrderNoList = this.getInternalOrderNoByUserId(userId);
				if (internalOrderNoList != null && !internalOrderNoList.isEmpty()) {
					String orderNoStr = "";
					for (Integer orderNo : internalOrderNoList) {
						orderNoStr += orderNo + ",";
					}
					from += " om.order_no in ("+orderNoStr.substring(0, orderNoStr.length()-1)+") and ";
				} else {
					from += " om.order_no is null and ";
				}
			} else if (!Constants.USERNAME_ADMIN.equals(userName)) {
				from += " cm.cust_Type != '"+Constants.INTERNAL_TYPE_CUSTOMER+"' and ";
			}
			from += " om.cust_no = cm.cust_no and om.sales_contact = u.user_Id " +
					" and om.created_by = u2.user_Id and om.order_currency = pc.currency_code ";
			String orderBy = " ORDER BY om.order_no desc";
			SQLQuery query = this.getSession().createSQLQuery(select + from + orderBy);
			query.setFirstResult(page.getFirst()!=null&&page.getFirst()>0?(page.getFirst()-1):0);
			query.setMaxResults(page.getPageSize());
			@SuppressWarnings("unchecked")
			List<Object[]> objsList = query.list();
			List<OrderMainBean> omlist = new ArrayList<OrderMainBean>();
			if (objsList != null && !objsList.isEmpty()) {
				for (Object[] objs : objsList) {
					OrderMainBeanDTO omb = new OrderMainBeanDTO();
					OrderMainBean orderMainBean = new OrderMainBean();
					omb.setOrderNo((Integer)objs[0]);
					omb.setStatus(objs[1] != null?objs[1].toString():null);
					omb.setCustNo((Integer)objs[2]);
					omb.setFirstName(objs[3]!=null?objs[3].toString():null);
					omb.setMidName(objs[4]!=null?objs[4].toString():null);
					omb.setLastName(objs[5]!=null?objs[5].toString():null);
					omb.setAddrLine1(objs[6]!=null?objs[6].toString():null);
					omb.setAddrLine2(objs[7]!=null?objs[7].toString():null);
					omb.setAddrLine3(objs[8]!=null?objs[8].toString():null);
					omb.setCity(objs[9]!=null?objs[9].toString():null);
					omb.setState(objs[10]!=null?objs[10].toString():null);
					omb.setZipCode(objs[11]!=null?objs[11].toString():null);
					omb.setCountry(objs[12]!=null?objs[12].toString():null);
					omb.setOrderDate(objs[13]!=null?(Date)objs[13]:null);
					omb.setExprDate(objs[14]!=null?(Date)objs[14]:null);
					omb.setAmount(objs[15]!=null?((BigDecimal)objs[15]).doubleValue():0d);
					String salesContact = (objs[16]!=null?objs[16].toString():"")+" "+(objs[17]!=null?objs[17].toString():"");
					omb.setSalesContact(salesContact);
					omb.setQuoteNo(objs[18]!=null && StringUtils.isNotBlank(objs[18].toString())&&StringUtils
							.isNumeric(objs[18].toString())?Integer.parseInt(objs[18].toString()):null);
					omb.setCreatedBy(objs[19]!=null?objs[19].toString():null);
					omb.setSymbol(objs[20]!=null?objs[20].toString():null);
					orderMainBean = dozer.map(omb, OrderMainBean.class);
					omlist.add(orderMainBean);
				}
			}
			page.setResult(omlist);
			page.setTotalCount(getOrderTotal(from));
			return page;
		}
	}

	/**
	 * 获得order的总条数
	 * @author Zhang Yong
	 * @return
	 */
	private Long getOrderTotal (String from) {
		Long totalCount = 0l;
		String hql = "SELECT COUNT(om.order_no) FROM order.order om ";
		if (StringUtils.isNotBlank(from)) {
			hql = "SELECT COUNT(om.order_no) " + from;
		}
		SQLQuery query = this.getSession().createSQLQuery(hql);
		@SuppressWarnings("unchecked")
		List<BigInteger> list = query.list();
		if (list != null && !list.isEmpty()) {
			totalCount = list.get(0).longValue();
		}
		return totalCount;
	}

	@SuppressWarnings("unchecked")
	public Map<String,List<Integer>> searchOrderByCustomerNo(final Page<OrderMainBean> page, 
			Map<String,Object> filterMap) throws Exception {
		Map<String,List<Integer>> resultMap = new HashMap<String,List<Integer>>();
		String hql = "";
		if (filterMap.get("orderNo") != null && filterMap.get("custNo") != null) {
			hql = "SELECT DISTINCT a.orderNo " +
			"FROM OrderMainBean a, OrderItem b " +
			"WHERE a.orderNo = b.orderNo " +
			"AND b.status IN ('PS', 'SH', 'VS') " +
			"And a.custNo =:custNo And a.orderNo =:orderNo order by a.orderNo ";
			Query query = this.createQuery(hql)
				.setInteger("custNo", Integer.parseInt(filterMap.get("custNo").toString()))
				.setInteger("orderNo", Integer.parseInt(filterMap.get("orderNo").toString()));
			List<Integer> orderMainBeanAllList = query.list();
			resultMap.put("orderMainBeanAllList", orderMainBeanAllList);
			int first = page.getFirst();
			int initFirst = first-1;
			query.setFirstResult(initFirst);
			query.setMaxResults(page.getPageSize());
			List<Integer> orderMainBeanlist = query.list();
			resultMap.put("orderMainBeanlist", orderMainBeanlist);
		} else if (filterMap.get("custNo") != null) {
			hql = "SELECT DISTINCT a.orderNo " +
			"FROM OrderMainBean a, OrderItem b " +
			"WHERE a.orderNo = b.orderNo " +
			"AND b.status IN ('PS', 'SH', 'VS') " +
			"And a.custNo =:custNo order by a.orderNo ";
			Query query = this.createQuery(hql)
				.setInteger("custNo", Integer.parseInt(filterMap.get("custNo").toString()));
			List<Integer> orderMainBeanAllList = query.list();
			resultMap.put("orderMainBeanAllList", orderMainBeanAllList);
			int first = page.getFirst();
			int initFirst = first-1;
			query.setFirstResult(initFirst);
			query.setMaxResults(page.getPageSize());
			List<Integer> orderMainBeanlist = query.list();
			resultMap.put("orderMainBeanlist", orderMainBeanlist);
		}
		return resultMap;
	}
	
	/**
	 * 通过用户Id查询该用户同一部门的用户下的内部订单的orderNo和同一部门的用户下给其他部门的orderNo
	 * add date 2011-10-17
	 * @author Zhang Yong
	 * @param userId
	 * @return
	 */
	public List<Integer> getInternalOrderNoByUserId (Integer userId) {
		String hql = "SELECT om.orderNo FROM WorkCenter wc, User u, OrderMain om WHERE u.userId=:userId AND u.deptId = wc.deptId AND wc.internalCustNo is not null AND om.custNo = wc.internalCustNo";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		List<Integer> orderNoList = this.find(hql, map);
		if (orderNoList == null || orderNoList.isEmpty()) {
			orderNoList = new ArrayList<Integer>();
		}
		String hql2 = "SELECT om.orderNo FROM OrderMain om, User u1, User u2, Customer c WHERE u2.userId=:userId AND u2.deptId = u1.deptId AND u1.userId = om.createdBy AND om.custNo = c.custNo AND c.custType=:custType";
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("userId", userId);
		map2.put("custType", Constants.INTERNAL_TYPE_CUSTOMER);
		List<Integer> orderNoList2 = this.find(hql2, map2);
		if (orderNoList2 != null && !orderNoList2.isEmpty()) {
			orderNoList.addAll(orderNoList2);
		}
		HashSet<Integer> h = new HashSet<Integer>(orderNoList);   
		orderNoList.clear();   
		orderNoList.addAll(h);   
		return orderNoList;
	}
}
