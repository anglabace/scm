package com.genscript.gsscm.order.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.order.entity.OrderMail;

@Repository
public class OrderMailDao extends HibernateDao<OrderMail, Integer>{

	private static final String QUERY_MAIL = "from OrderMail o where o.status = 'INCOMPLETE' and o.scheduleDate >=? and o.scheduleDate < ?";
	private static final String QUERY_CUSTMAIL = "from OrderMail o where o.orderNo=? and o.status = 'COMPLETE' and o.type='CUST_CONFIRM_EMAIL'";
	private static final String QUERY_ORDERCOMPLETEMAIL = "from OrderMail o where o.orderNo=? and o.creationDate>? and o.status = 'COMPLETE' and o.type=? order by o.creationDate desc";
	
	/**
	 * 
	 * @param orderNo
	 * @return
	 */
	public OrderMail getCustConfirmCompleteMail(Integer orderNo){
		return  (OrderMail) this.createQuery(QUERY_CUSTMAIL, orderNo).setMaxResults(1).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderMail> getOrderCompleteMail(Integer orderNo, Date afterDate, String emailType){
		return this.createQuery(QUERY_ORDERCOMPLETEMAIL, orderNo, afterDate, emailType).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderMail> queryMailSend(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date now = calendar.getTime();
		System.out.println(now);
		calendar.add(Calendar.DAY_OF_MONTH,1);
		Date tomorrow = calendar.getTime();
		System.out.println(tomorrow);
		List<OrderMail> list = createQuery(QUERY_MAIL, now, tomorrow).list();
		if(list != null && list.size() > 0){
			for(OrderMail o : list){
				System.out.println(o);
			}
		return list;
		}
		return null;
	}
	
	/**
	 * 根据orderNo查找相关OrderMail.
	 * @param orderNo
	 * @param type
	 * @return
	 */
	public List<OrderMail> getOrderMailList(Integer orderNo, OrderInstructionType type) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo", orderNo);
		filterList.add(quoteFilter);
		if (type != null) {
			PropertyFilter typeFilter = new PropertyFilter("EQS_type", type);
			filterList.add(typeFilter);
		}
		List<OrderMail> mailList = this.find(filterList);
		return mailList;
	}
}
