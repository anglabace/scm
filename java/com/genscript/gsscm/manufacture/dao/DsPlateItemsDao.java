package com.genscript.gsscm.manufacture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.DsPlateItems;

@Repository
public class DsPlateItemsDao extends HibernateDao<DsPlateItems,Integer>{
	
	public void updateDsPlateItems(Integer id,String concerntrationValue) {
		String hql = "update DsPlateItems set concerntrationValue=? where id=?";
		this.batchExecute(hql, concerntrationValue,id);
	}
	
	public List<Integer> getAllWorkOrderNos() {
		String hql = "select workOrderNo from DsPlateItems where fileAnalysisFlag=0";
		return this.find(hql);
	}

}
