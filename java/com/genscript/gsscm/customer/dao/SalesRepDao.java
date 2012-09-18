package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.privilege.entity.User;
import com.opensymphony.xwork2.ActionContext;

@Repository
public class SalesRepDao extends HibernateDao<SalesRep, Integer>{

	private static String SELECT_PROJECT_SUPPORT = " from SalesRep where function = ?";
	
	private static String SELECT_PROJECT_SUPPORT_ASS_USER = "select u from SalesRep s, User u where s.function = ? and s.salesId = u.userId";
	
	public List<SalesRep> getSalesRepBySaleSRoleGpId (String salesRole, Integer salesGroupId) {
		String hql = "from SalesRep where function=:function and salesGroupId=:salesGroupId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("function", salesRole);
		map.put("salesGroupId", salesGroupId);
		return this.find(hql, map);
	}
	
	/**
	 * 查询与登陆用户相同组的用户ID以及登陆用的所属角色
	 * @return
	 */
	public Map<String,Object> getSameGroupUser () throws Exception {
		Map<String,Object> salesRoleAndUserIdsMap = new HashMap<String,Object>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userId = session.get(StrutsActionContant.USER_ID);
		SalesRep salesRep = this.findUniqueBy("salesId", userId);
		if (salesRep != null && StringUtils.isNotBlank(salesRep.getFunction()) && salesRep.getSalesGroupId() != null) {
			String salesRole =  salesRep.getFunction();
			Integer salesGroupId = salesRep.getSalesGroupId();
			List<SalesRep> salesReplist = this.getSalesRepBySaleSRoleGpId(salesRole, salesGroupId);
			if (salesReplist != null && salesReplist.size() >0) {
				Integer[] userIdsArr = new Integer[salesReplist.size()];
				for (int i=0;i<salesReplist.size();i++) {
					userIdsArr[i] = salesReplist.get(i).getSalesId();
				}
				salesRoleAndUserIdsMap.put("function", salesRole);
				salesRoleAndUserIdsMap.put("User_Ids", userIdsArr);
			}
		}
		return salesRoleAndUserIdsMap;
	}
	
	/**
	 * 查询不同SalesRole的SalesRep
	 * @author zhangyong
	 * @return
	 */
	public List<SalesRep> findSalesRep (String salesRoleType) {
		return this.find(SELECT_PROJECT_SUPPORT, salesRoleType);
	}
	
	/**
	 * 通过主键查询
	 * @author zhangyong
	 * @param salesId
	 * @return
	 */
	public SalesRep findBySalesId (Integer salesId) {
		return this.findUniqueBy("salesId", salesId);
	}
	
	/**
	 * 通过groupId查询
	 */
	public List<SalesRep> findByGroupId(Integer groupId) {
		String hql ="from SalesRep where salesGroupId=?";
		return this.find(hql, groupId);
		
	}
	
	/**
	 * 根据salesGroupId删除所有相关的SalesRep
	 */
	public void deleteByGroupId(Integer groupId) {
		String hql ="update SalesRep set salesGroupId=null where salesGroupId=?";
		this.batchExecute(hql, groupId);
	}
	
	/**
	 * 根据resourceName模糊查询所有相关的SalesRep的salesId
	 */
	public List<Integer> findByResourceName(String resourceName) {
		String hql = "select salesId from SalesRep where resourceName like ?";
		return this.find(hql,"'%"+resourceName+"%'");
	}
	
	/**
	 * 查询不同SalesRole的SalesRep
	 * @author zhangyong
	 * @return
	 */
	public List<User> findUserByFunction (String salesRoleType) {
		return this.find(SELECT_PROJECT_SUPPORT_ASS_USER, salesRoleType);
	}

   
	public List<SalesRep> getSalesRepsByFunction(String funName){
        String hql = "from SalesRep where function=:funName order by resourceName";
        List<SalesRep> newlist=this.getSession().createQuery(hql).setParameter("funName", funName).list(); 
        return newlist;
    }
}
