package com.genscript.gsscm.order.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.PaymentVoucher;

@Repository
public class PaymentVoucherDao extends HibernateDao<PaymentVoucher, Integer> {
	private static final String PAYMENT_PLAN = "select p.voucherId from PaymentVoucher p where p.paymentType LIKE ? ORDER BY p.voucherId ASC";
	private static final String PAYMENT_LIST = "from PaymentVoucher p where p.orderNo=? ORDER BY p.voucherId DESC";
	private static final String PAYMENT_COUNT = "from PaymentVoucher p where p.orderNo=?";
	/**
	 * 判断是否存在PO ， CREDIT CARD
	 * @param orderNo
	 * @return
	 */
	public Boolean isPoCCexixt(Integer orderNo){
		PaymentVoucher payinfo = (PaymentVoucher) this.createQuery(PAYMENT_COUNT, orderNo).setMaxResults(1).uniqueResult();
		if(payinfo != null){
			return true;
		}
		return false;
	}
	
	public String getPoNumber(Integer orderNo){
		PaymentVoucher payinfo = (PaymentVoucher) createQuery(PAYMENT_COUNT, orderNo).setMaxResults(1).uniqueResult();
		if(payinfo != null){
			return payinfo.getPoNumber();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentVoucher> getPaymentVoucherList(Integer orderNo){
		return createQuery(PAYMENT_LIST,orderNo).list();
	}
	

	@SuppressWarnings("rawtypes")
	public Integer getFirstPaymentPlan(String paymentMthd){
		List list = createQuery(PAYMENT_PLAN, paymentMthd+"%").list();
		if(list != null && list.size() > 0){
			return (Integer)list.get(0);
		}
		return null;
	}
	
	public void delPaymentVoucherList(Object ids) {
		String hql = "delete from PaymentVoucher where voucherId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	public Page<PaymentVoucher> searchPayment(Page<PaymentVoucher> page, List<PropertyFilter> filters) {
		return findPage(page, filters);
	}
}
