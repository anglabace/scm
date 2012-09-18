package com.genscript.gsscm.manufacture.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;

@Repository
public class WorkOrderLotDao extends HibernateDao<WorkOrderLot, Integer>{
	public Long getTotalCount() {
		String hql = "select count(id) from WorkOrderLot";
		return this.findUnique(hql);
	}
	
	//获取有效的lotNo
	public List<WorkOrderLot> getWorkOrderLotByWo(Integer workOrderNo) {
		String hql = "from WorkOrderLot where workOrderNo=? and status='ACTIVE'";
		return this.find(hql,workOrderNo);
	}
	
	public void delLotList(Object ids) {
		String hql = "delete from WorkOrderLot c where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	public void updateLotList(Object ids) {
		String hql = "update WorkOrderLot c set c.status='INACTIVE' where c.id in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	/*
	 * 根据ShipPackageLines 获取 workOrderlot
	 * mingrs
	 */
	public List<WorkOrderLot> searchWorkOrderLotByOrderNoAndItemNo(Integer orderNo , Integer itemNo){
			String hql = "select wol from WorkOrderLot wol,WorkOrder wo,MfgOrder o,PurchaseOrder p where  wol.workOrderNo=wo.orderNo and o.orderNo=wo.soNo and o.srcPoNo = p.orderNo and p.srcSoNo = " + orderNo+" and wo.soItemNo = " + itemNo;
			System.out.println("hql="+hql);
				
			return this.find(hql);
		
			

	
	}
	
}
