package com.genscript.gsscm.system.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderMail;
import com.genscript.gsscm.system.entity.MailLog;

@Repository
public class MailLogDao extends HibernateDao<MailLog, Integer>{
	private static final String QUERY_MAIL = "from MailLog o where  o.sendDate >=? and o.sendDate < ?";
	/**
	 * 查找当天要发送的邮件
	 * @return
	 */
	public List<MailLog> queryMailSend(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date now = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH,1);
		Date tomorrow = calendar.getTime();
		List<MailLog> list = createQuery(QUERY_MAIL, now, tomorrow).list();
		return list;
	}
}
