package com.genscript.gsscm.manufacture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.manufacture.entity.Operation;
@Repository
public class OperationNameDao extends HibernateDao<Operation, Integer>{
	
	/**
	 * fangquan
	 * 2011-11-29
	 * @param order_No
	 * @param item_NO
	 * @return src_operation_id
	 * 根据订单号和项目号查询工序号,再返回工序名称
	 */
	public List<String> getOperationName(Integer order_No,Integer item_No){
		String hql="select ps.customProductionStatus from "+
		   " ProductionStatusMapping ps,"+
		   " WorkOrderOperation woo,"+
		   " WorkOrder wo,"+
		   " Operation op"+
		   " where woo.workOrderNo=wo.orderNo"+
		   " and wo.soNo=(select mo.orderNo from MfgOrder mo where mo.srcSoNo="+order_No+")"+
		   " and wo.soItemNo="+item_No+""+
		   " and woo.operation.id=op.id"+
		   " and SUBSTRING_INDEX(SUBSTR(op.name,6),'|',1) = ps.productionStatus"+
		   " and wo.workCenterId=ps.workCenterId";
		return this.find(hql);
		
		

	}
	
	

}
