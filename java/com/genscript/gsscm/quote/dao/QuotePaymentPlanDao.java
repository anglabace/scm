package com.genscript.gsscm.quote.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuotePaymentPlan;

@Repository
public class QuotePaymentPlanDao extends HibernateDao<QuotePaymentPlan, Integer> {

	private static final String PAYMENT_PLAN = "select q.planId from QuotePaymentPlan q where q.paymentType LIKE ? ORDER BY q.planId ASC";
	private static final String PAYMENT_PLAN_LIST = "from QuotePaymentPlan q where q.orderNo=? ORDER BY q.planId DESC";
	

	@SuppressWarnings("rawtypes")
	public Integer getFirstPaymentPlan(String paymentMthd){
		List list = createQuery(PAYMENT_PLAN, paymentMthd+"%").list();
		if(list != null && list.size() > 0 ){
			return (Integer)list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<QuotePaymentPlan> getQuotePaymentPlanList(Integer quoteNo){
		return createQuery(PAYMENT_PLAN_LIST,quoteNo).list();
	}
	
	public void delPaymentPlanList(Object ids) {
		String hql = "delete from QuotePaymentPlan where planId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
}
