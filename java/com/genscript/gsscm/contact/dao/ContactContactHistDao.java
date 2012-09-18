package com.genscript.gsscm.contact.dao;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContactContactHistory;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;

@Repository
public class ContactContactHistDao extends
		HibernateDao<ContactContactHistory, Integer> {

	private static final String DELETE_CONTACTS = "delete from ContactContactHistory c where c.contactId in (:ids)";
	private static final String QUERY_MAIL = "from ContactContactHistory c where c.status = 'INCOMPLETE' and c.scheduleDate >=? and c.scheduleDate < ?";
	public void delContactsList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE_CONTACTS, map);
	}

	public List<ContactContactHistory> getListByContact(Integer contactNo) {
		String hql = "from ContactContactHistory where contactNo=?";
		return this.find(hql, contactNo);
	}
	
	public Long getContactCount(Integer contactNo) {
		String hql = "from ContactContactHistory where contactNo=:contactNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contactNo", contactNo);
		return this.countHqlResult(hql, map);
	}

	public ContactContactHistory getLastContact(Integer contactNo) {
		ContactContactHistory contactHistory = null;
		String hql = "from ContactContactHistory where contactNo=:contactNo order by modifyDate desc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contactNo", contactNo);
		Page<ContactContactHistory> page = new Page<ContactContactHistory>();
		page.setPageNo(1);
		page.setPageSize(1);
		page = this.findPage(page, hql, map);
		if (page.getResult() != null && !page.getResult().isEmpty()) {
			contactHistory = page.getResult().get(0);
		}
		return contactHistory;
	}

	public int getMethodCount(String contactMethod, Integer contactNo) {
		int count = 0;
		String hql = "from ContactContactHistory where contactMethod=? and contactNo=?";
		List<CustomerContactHistory> list = this.find(hql, contactMethod,
				contactNo);
		if (list != null) {
			count = list.size();
		}
		return count;
	}
	
	public ContactContactHistory getLastContactByMethod(Integer contactNo, String contactMethod) {
		ContactContactHistory contactHistory = null;
		String hql = "from ContactContactHistory where contactNo=:contactNo AND contactMethod=:contactMethod and (status=:status1 or status=:status2) order by modifyDate desc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contactNo", contactNo);
		map.put("contactMethod", contactMethod);
		map.put("status1", "CALLED");
		map.put("status2", "SENT");
		Page<ContactContactHistory> page = new Page<ContactContactHistory>();
		page.setPageNo(1);
		page.setPageSize(1);
		page = this.findPage(page, hql, map);
		if (page.getResult() != null && !page.getResult().isEmpty()) {
			contactHistory = page.getResult().get(0);
		}
		return contactHistory;
	}
	
	public ContactContactHistory getLastContactByContentType(Integer contactNo, String contentType) {
		ContactContactHistory contactHistory = null;
		String hql = "from ContactContactHistory where contactNo=:contactNo AND contentType=:contentType and (status=:status1 or status=:status2) order by modifyDate desc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contactNo", contactNo);
		map.put("contentType", contentType);
		map.put("status1", "CALLED");
		map.put("status2", "SENT");
		Page<ContactContactHistory> page = new Page<ContactContactHistory>();
		page.setPageNo(1);
		page.setPageSize(1);
		page = this.findPage(page, hql, map);
		if (page.getResult() != null && !page.getResult().isEmpty()) {
			contactHistory = page.getResult().get(0);
		}
		return contactHistory;
	}
	
	@SuppressWarnings("unchecked")
	public List<ContactContactHistory> queryMailSend(){
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
		List<ContactContactHistory> list = createQuery(QUERY_MAIL, now, tomorrow).list();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}
