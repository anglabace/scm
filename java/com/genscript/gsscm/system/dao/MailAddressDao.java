package com.genscript.gsscm.system.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.MailAddress;

/**
 * 
 * 
 * @author mingrs
 */
@Repository
public class MailAddressDao extends HibernateDao<MailAddress, Integer> {
	
	public Long getCountMilAddressByUserIdGroupId(Integer userId,Integer groupId){
		String hql = "select count(*) from MailAddress where user.userId=? and groupId=?";
		return this.findUnique(hql, userId,groupId);
	}
	
	public void delMailAddress(Object ids){
		String del_attribute="delete from MailAddress a where a.mailId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
}
