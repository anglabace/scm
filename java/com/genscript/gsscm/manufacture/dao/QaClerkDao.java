package com.genscript.gsscm.manufacture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.QaClerk;

@Repository
public class QaClerkDao extends HibernateDao<QaClerk, Integer> {
	public List<QaClerk> getAllList(Integer groupId) {
		String hql = "from QaClerk gr where gr.groupId=? order by creationDate desc";
		return this.find(hql, groupId);		
	}
	
	/**
	 * 由用户登录名查找QaClerk所关联的QaGroupId
	 * @param checkName 登录名
	 * @return
	 */
	public List<Integer> getGroupList(String checkName) {
		String hql = "select gr.groupId from QaClerk gr,User user where gr.userId=user.userId and user.loginName like ? order by gr.creationDate desc";
		return this.find(hql,"%"+checkName+"%");		
	}
	
	/**
	 * 由用户Id和qaGroupId查找
	 */
	public List<QaClerk> getGroupList(Integer userId,Integer groupId) {
		String hql = "from QaClerk gr where gr.groupId=? and userId=? order by creationDate desc";
		return this.find(hql,groupId,userId);		
	}
	
	/**
	 * 根据groupId删除关联的所有的QaClerk对象
	 */
	public void deleteByGroupId(Integer groupId) {
		String hql = "delete from QaClerk gr where gr.groupId="+groupId;
		this.batchExecute(hql);
	}
	
	public void saveQaClerk(QaClerk qaClerk) {
		Assert.notNull(qaClerk, "entity can not be empty");
		this.getSession().save(qaClerk);
		logger.debug("save entity: {}", qaClerk);
	}
	
	public void updateQaClerk(QaClerk qaClerk) {
		Assert.notNull(qaClerk, "entity can not be empty");
		this.getSession().update(qaClerk);
		logger.debug("update entity: {}", qaClerk);
	}
	
}
