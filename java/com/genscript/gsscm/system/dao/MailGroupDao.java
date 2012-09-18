package com.genscript.gsscm.system.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.system.entity.MailGroup;

/**
 * 
 * 
 * 
 * @author mingrs
 */
@Repository
public class MailGroupDao extends HibernateDao<MailGroup, Integer> {

	public void delMailGroup(Object ids){
		String del_attribute="delete from MailGroup a where a.groupId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_attribute, map);
	}
	
	/**
	 * 通过groupFunction查询Email模板
	 * @author Zhang Yong
	 * @param groupFunction
	 * @return
	 */
	public List<MailGroup> findByGroupFunction (String groupFunction) {
		return this.findBy("groupFunction", groupFunction);
	}
}
