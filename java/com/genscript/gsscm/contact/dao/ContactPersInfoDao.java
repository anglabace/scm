package com.genscript.gsscm.contact.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContactPersonalInfo;

@Repository
public class ContactPersInfoDao extends HibernateDao<ContactPersonalInfo, Integer> {
	   public ContactPersonalInfo getInfoByContact(Integer contactNo) {
		   return this.findUniqueBy("contactNo", contactNo);
	   }
}
