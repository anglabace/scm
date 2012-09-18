package com.genscript.gsscm.quote.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.QuoteInstructionType;
import com.genscript.gsscm.quote.entity.QuoteMail;

@Repository
public class QuoteMailDao extends HibernateDao<QuoteMail, Integer>{

	private static final String QUERY_MAIL = "from QuoteMail q where q.status = 'INCOMPLETE' and q.scheduleDate >=? and q.scheduleDate < ?";
	
	@SuppressWarnings("unchecked")
	public List<QuoteMail> queryMailSend(){
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
		List<QuoteMail> list = createQuery(QUERY_MAIL, now, tomorrow).list();
		if(list != null && list.size() > 0){
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
	public List<QuoteMail> getQuoteMailList(Integer quoteNo, QuoteInstructionType type) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_quoteNo", quoteNo);
		filterList.add(quoteFilter);
		if (type != null) {
			PropertyFilter typeFilter = new PropertyFilter("EQS_type", type);
			filterList.add(typeFilter);
		}
		List<QuoteMail> mailList = this.find(filterList);
		return mailList;
	}
	
}
