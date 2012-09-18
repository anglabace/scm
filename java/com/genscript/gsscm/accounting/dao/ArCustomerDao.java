package com.genscript.gsscm.accounting.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.Customer;

@Repository
public class ArCustomerDao extends HibernateDao<Customer, Integer> {

	private static final String DELETE_CUSTOMER = "update Customer c set c.status = 'INACTIVE',c.modifyDate =?,c.modifiedBy =? where c.custNo =?";
	private static final String PREFER_PAYMENT = "select c.prefPaymentMthd from Customer c where c.custNo=?";
	
	/**
	 * 合并Customer时设为INACTIVE状态。
	 * @param userId
	 * @param customerNo
	 * @param statusUpdateReason
	 * @return
	 */
	public int delSlaveCustomer(Integer userId, Integer customerNo, String statusUpdateReason) {
		String hql = "update Customer c set c.status = 'INACTIVE', statusReason=?, c.modifyDate =?,c.modifiedBy =? where c.custNo =?";
		return batchExecute(hql, statusUpdateReason,  new Date(), userId, customerNo);
	}
	
	public int delete(Integer userId, Integer customerNo) {
		Assert.notNull(customerNo, "customer no not be null!");
		return batchExecute(DELETE_CUSTOMER, new Date(), userId, customerNo);
	}
	
	public String getPreferPaymentMthd(Integer custNo){
		return (String)createQuery(PREFER_PAYMENT, custNo).uniqueResult();
	}


	@SuppressWarnings("rawtypes")
	public Object getStatistics(final Integer in) {

		String procedure = "get_customer_statistics";//"{call get_customer_statistics(?)}";
		
		Query query = this.getSession().getNamedQuery(procedure);
		query.setInteger(1, in);
		List list = query.list();
	    Object obj = list.get(0);
		return obj;
	}
}
