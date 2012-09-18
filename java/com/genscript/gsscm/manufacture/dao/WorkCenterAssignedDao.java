package com.genscript.gsscm.manufacture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.WorkCenterAssigned;
@Repository
public class WorkCenterAssignedDao extends HibernateDao<WorkCenterAssigned, Integer> {
	
	/**
	 * 根据itemType和clsId和warehousesId查找对象
	 * @param itemType
	 * @param clsId
	 */
	public WorkCenterAssigned findByTypeAndCId(String itemType,Integer clsId,Integer warehousesId){
		String hql = "from WorkCenterAssigned where itemType=? and clsId=? and wareHouses.warehousesId=?";
		return this.findUnique(hql, itemType,clsId,warehousesId);
		
	}
	
	/**
	 * 根据itemType和clsId查找对象
	 * @param itemType
	 * @param clsId
	 */
	public List<WorkCenterAssigned> findByTypeAndCId(String itemType,Integer clsId){
		String hql = "from WorkCenterAssigned where itemType=? and clsId=?";
		return this.find(hql, itemType,clsId);
		
	}
	
	/**
	 * 根据centerId删除关联的所有的WorkCenterAssigned对象
	 */
	public void deleteByCenterId(Integer workCenterId) {
		String hql = "delete from WorkCenterAssigned aga where aga.workCenter.id="+workCenterId;
		this.batchExecute(hql);
	}

}
