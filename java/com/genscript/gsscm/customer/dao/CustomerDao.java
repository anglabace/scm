package com.genscript.gsscm.customer.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.order.entity.Promotion;
import com.genscript.gsscm.privilege.entity.User;

@Repository
public class CustomerDao extends HibernateDao<Customer, Integer> {

	private static final String DELETE_CUSTOMER = "update Customer c set c.status = 'INACTIVE',c.modifyDate =?,c.modifiedBy =? where c.custNo =?";
	private static final String PREFER_PAYMENT = "select c.prefPaymentMthd from Customer c where c.custNo=?";
	private static final String SELECT_BY_BUSEMAIL = "select custNo from Customer where busEmail like ?";
	private static final String SELECT_BY_BUSEMAIL2 = "select custNo from Customer where busEmail = ?";

	/**
	 * 合并Customer时设为INACTIVE状态。
	 * 
	 * @param userId
	 * @param customerNo
	 * @param statusUpdateReason
	 * @return
	 */
	public int delSlaveCustomer(Integer userId, Integer customerNo,
			String statusUpdateReason) {
		String hql = "update Customer c set c.status = 'INACTIVE', statusReason=?, c.modifyDate =?,c.modifiedBy =? where c.custNo =?";
		System.out.println("hql=====" + hql + ">>>>>>" + customerNo);
		return batchExecute(hql, statusUpdateReason, new Date(), userId,
				customerNo);
	}

	public int modCustPassword(String password, int custNo) {
		String hql = "update Customer c set c.passwd = ? where c.custNo= ?";
		return batchExecute(hql, password, custNo);
	}

	public int delete(Integer userId, Integer customerNo) {
		Assert.notNull(customerNo, "customer no not be null!");
		return batchExecute(DELETE_CUSTOMER, new Date(), userId, customerNo);
	}

	public String getPreferPaymentMthd(Integer custNo) {
		return (String) createQuery(PREFER_PAYMENT, custNo).uniqueResult();
	}

	@SuppressWarnings("rawtypes")
	public Object getStatistics(final Integer in) {

		String procedure = "get_customer_statistics";// "{call get_customer_statistics(?)}";

		Query query = this.getSession().getNamedQuery(procedure);
		query.setInteger(1, in);
		List list = query.list();
		Object obj = list.get(0);
		return obj;
	}

	/**
	 * 通过busEmail模糊查询
	 * 
	 * @author zhangyong
	 * @param busEmail
	 * @return
	 */
	public List<Integer> findCustomerByBusEmail(String busEmail) {
		return this.find(SELECT_BY_BUSEMAIL, "%" + busEmail + "%");
	}

	/**
	 * 通过主键查询
	 * 
	 * @author zhangyong
	 * @param custNo
	 * @return
	 */
	public Customer findByCustNo(Integer custNo) {
		return this.findUniqueBy("custNo", custNo);
	}

	/**
	 * 根据一些条件是否能找到满足条件的customer
	 */
	public boolean findBySomeCondition(Promotion promotion) {
		StringBuffer sqlBuf = new StringBuffer(
				"select count(t1.custNo) from Customer t1,RfmRating t2 where 1=1");
		if (promotion.getCustNo() != null) {
			sqlBuf.append(" and t1.custNo=").append(promotion.getCustNo());
		}
		if (!StringUtils.isBlank(promotion.getCustPriorityLvl())) {
			sqlBuf.append(" and t1.priorityLvl='")
					.append(promotion.getCustPriorityLvl()).append("'");
		}
		if (promotion.getCustJobRole() != null) {
			sqlBuf.append(" and t1.custRoleId=").append(
					promotion.getCustJobRole());
		}
		if (!StringUtils.isBlank(promotion.getCustCountry())) {
			sqlBuf.append(" and t1.country='")
					.append(promotion.getCustCountry()).append("'");
		}
		if (promotion.getCustSalesTerritory() != null) {
			sqlBuf.append(" and t1.salesTerritory=").append(
					promotion.getCustSalesTerritory());
		}
		if (promotion.getRfmValue() != null) {
			sqlBuf.append(
					" and t1.rfmRatingId=t2.rfmRatingId and t2.rfmRatingCd>='")
					.append(promotion.getRfmValue()).append("'");
		}
		@SuppressWarnings("rawtypes")
		List list = this.find(sqlBuf.toString());
		if (list != null && list.size() != 0 && (Long) list.get(0) >= 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 根据busEmail查找customer的custNo
	 */
	public Integer getCustNoByEmail(String busEmail) {
		Integer custNo = null;
		if (this.findUnique(SELECT_BY_BUSEMAIL2, busEmail) != null) {
			custNo = (Integer) this.findUnique(SELECT_BY_BUSEMAIL2, busEmail);
		}
		return custNo;
	}

	/*
	 * 根据orderNo 获取customer
	 */
	public List<Customer> searchCustomerByOrderNo(Integer OrderNo) {
		String hql = "select c from Customer c,OrderMain o where o.custNo=c.custNo and o.orderNo="
				+ OrderNo;
		return this.find(hql);
	}

	public int ActiveMasterCustomer(Integer userId, Integer masterNo,
			String reason) {
		String hql = "update Customer c set c.status = 'ACTIVE', statusReason=?, c.modifyDate =?,c.modifiedBy =? where c.custNo =?";
		System.out.println("hql=====" + hql + ">>>>>>" + masterNo);
		return batchExecute(hql, reason, new Date(), userId, masterNo);

	}

	/**
	 * 查询所有在黑名单中的账户
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAccountBlackList(Integer custNo) {
		String sql = "select acc_no from olddb.account_black_list where acc_no = ?";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, custNo);
		return query.list();
	}

	/**
	 * 查询所有在黑名单中的组织机构
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findInstitutionBlackList() {
		String sql = "select inst_domain, inst_name from olddb.inst_black_list";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query.list();
	}

	public String getBusEmailByCustNo(Integer custNo) {
		String sql = "select `bus_email` from `customer`.`customer` where `cust_no`="
				+ custNo;
		String Emailstr = "";
		@SuppressWarnings("rawtypes")
		List list = this.getSession().createSQLQuery(sql).list();
		if (list != null && !"".equals(list) && list.size() > 0) {
			Emailstr = list.get(0).toString();
		}
		return Emailstr;
	}
	
	/**
	 * 通过custNo查询该客户的Tech Account Manager信息
	 * @param custNo
	 * @return
	 */
	public User getUserByCustNo (Integer custNo) {
		String hql = "SELECT u FROM Customer c, User u WHERE c.custNo=:custNo AND c.techSupport = u.userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		List<User> userlist = this.find(hql, map);
		if (userlist != null && !userlist.isEmpty()) {
			return userlist.get(0);
		}
		return null;
	}
	
	/**
	 * 通过custNo查询company，查询结果是在获取的AccountCode前加上"GS"，结果例如：GSUS
	 * @author Zhang Yong
	 * add date 2011-11-04
	 * @param custNo
	 * @return
	 */
	public String getCustomerCompany (Integer custNo, CustomerDTO customerDTO) {
		String company = "GSUS";
		if (customerDTO != null && StringUtils.isNotBlank(customerDTO.getBillAccountCode())) {
			return "GS" + customerDTO.getBillAccountCode();
		}
		if (custNo == null) {
			return company;
		}
		Customer customer = this.getById(custNo);
		if (customer == null || StringUtils.isBlank(customer.getBillAccountCode())) {
			return company;
		}
		return "GS" + customer.getBillAccountCode();
	}
	
	/**
	 * fangquan
	 * 根据custNo更新customer状态
	 */
	
	public int updateStatus(int custNo,String status){
		String hql="update from Customer c set c.status='"+status+"' where c.custNo="+custNo+"";
		return batchExecute(hql);
	}
}
