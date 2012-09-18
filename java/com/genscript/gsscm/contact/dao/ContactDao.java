package com.genscript.gsscm.contact.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.Contact;

@Repository
public class ContactDao extends HibernateDao<Contact, Integer> {
	private static final String INACTIVE_CONTACT = "update Contact c set c.status = 'INACTIVE',c.modifyDate =?,c.modifiedBy =? where c.contactNo =?";
	private static final String SELECT_BY_BUS_EMAIL = "from Contact where busEmail  = ?";
	private static final String SELECT_BY_BUSEMAIL2 = "select contactNo from Contact where busEmail = ?";
	private static final String ALLIDS = " select c.contactNo from Contact c ,Customer cc where c.busEmail =cc.busEmail";
	private static final String DELETE_CONTACTS = "update Contact c set c.status = 'INACTIVE' where c.contactNo in (:ids)";

	public int delContact(Integer userId, Integer contactNo) {
		return batchExecute(INACTIVE_CONTACT, new Date(), userId, contactNo);
	}

	/**
	 * 通过busEmail查询
	 * 
	 * @author zhangyong
	 * @param busEmail
	 * @return
	 */
	public List<Contact> findContactByBusEmail(String busEmail) {
		return this.find(SELECT_BY_BUS_EMAIL, busEmail);
	}

	/**
	 * 根据busEmail查找customer的custNo
	 */
	public Integer getContactNoByEmail(String busEmail) {
		Integer contactNo = null;
		if (this.findUnique(SELECT_BY_BUSEMAIL2, busEmail) != null) {
			contactNo = (Integer) this
					.findUnique(SELECT_BY_BUSEMAIL2, busEmail);
		}
		return contactNo;
	}
 
	public void delContactByduplicateEmail2customer() {
		List list = this.find(ALLIDS);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) { 
				Object ss = (Object) list.get(i);  
				String sqls="update Contact c set c.status = 'INACTIVE' where c.contactNo="+ss; 
				createQuery(sqls).executeUpdate();
			}
		}

	}
}
