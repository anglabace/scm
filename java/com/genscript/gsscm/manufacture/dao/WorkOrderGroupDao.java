package com.genscript.gsscm.manufacture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.WorkOrderGroup;

@Repository
public class WorkOrderGroupDao extends HibernateDao<WorkOrderGroup, Integer>{
	/**
	 * 根据的workOrder删除
	 */
	public void deleteByOrderNo(Integer orderNo) {
		String hql = "delete from WorkOrderGroup where orderNo=?";
		this.batchExecute(hql, orderNo);
	}
	
	/**
	 * 根据workGroupId查找Work Order orderNo列表
	 * @serialData 2010-12-02
	 * @author lizhang
	 * @param workGroupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getOrderNoListByGroup(Integer workGroupId) {
		String hql = "select wbd.orderNo from WorkOrderGroup wbd";
		List list = createQuery(hql).list();
		if(workGroupId!=null) {
			hql = hql+" where wbd.workGroupId=?";
			list = createQuery(hql, workGroupId).list();
		} 
		 
		if(list != null){
			return (List<Integer>)list;
		}
		return null;
	}

}
