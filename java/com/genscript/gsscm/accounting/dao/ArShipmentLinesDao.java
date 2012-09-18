package com.genscript.gsscm.accounting.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.dto.OrderNoDTO;

@Repository
public class ArShipmentLinesDao extends HibernateDao<OrderNoDTO, Integer> {

//	/**
//	 * 分页查询Shipment列表
//	 */
//	public Page<OrderNoDTO> findPage(Page<OrderNoDTO> page,
//			String filter_EQS_shipmentNo, String filter_EQI_orderNo) {
//		if (filter_EQS_shipmentNo != null && filter_EQI_orderNo != null) {
//			String hql = "SELECT DISTINCT a.order.orderNo FROM ShipmentLines a, Shipment b WHERE a.shipments.shipmentId = b.shipmentId AND b.shipmentNo = '"+filter_EQS_shipmentNo+"' AND a.order.orderNo = "+filter_EQI_orderNo;
//			page = super.findPage(page, hql);
//			if (page != null && page.getResult() != null) {
//				page.setTotalCount(Long.valueOf(page.getResult().size()));
//			}			
//		} else if (filter_EQS_shipmentNo != null) {
//			String hql = "SELECT DISTINCT a.order.orderNo FROM ShipmentLines a, Shipment b WHERE a.shipments.shipmentId = b.shipmentId AND b.shipmentNo = ?";
//			page = super.findPage(page, hql, filter_EQS_shipmentNo);
//			page.setTotalCount(Long.valueOf(super.find(hql, filter_EQS_shipmentNo).size()));
//		}
//		return page;
//	}
	

	/**
	 * 分页查询Shipment列表
	 */
	public Page<OrderNoDTO> findPage(Page<OrderNoDTO> page,
			Integer filter_EQS_shipmentNo, Integer filter_EQI_orderNo,
			Integer filter_EQS_custNo) throws Exception {
		String hql = "SELECT DISTINCT a.order.orderNo FROM ShipmentLine a, Shipment b WHERE a.shipments.shipmentId = b.shipmentId ";
		if (filter_EQS_shipmentNo != null && filter_EQS_shipmentNo > 0) {
			hql += " and b.shipmentNo = "+filter_EQS_shipmentNo ;
		}
		if (filter_EQI_orderNo != null && filter_EQI_orderNo > 0) {
			hql += " and a.order.orderNo = "+filter_EQI_orderNo;
		}
		if(filter_EQS_custNo != null && filter_EQS_custNo > 0){
			hql += " and b.custNo = "+filter_EQS_custNo;
		}
		page = super.findPage(page, hql);
		if (page != null && page.getResult() != null) {
//			page.setTotalCount(Long.valueOf(page.getResult().size()));
			page.setTotalCount(this.getTotal(page, filter_EQS_shipmentNo, filter_EQI_orderNo));
		} else {
			page = new Page<OrderNoDTO>();
			page.setResult(new ArrayList<OrderNoDTO>());
			page.setTotalCount(0l);
		}			
		
		return page;
	}
	
	
	@SuppressWarnings("unchecked")
	public long getTotal(Page<OrderNoDTO> page,
			Integer filter_EQS_shipmentNo, Integer filter_EQI_orderNo){
		long count = 0l;
		String hql = "SELECT DISTINCT a.order.orderNo FROM ShipmentLine a, Shipment b WHERE a.shipments.shipmentId = b.shipmentId ";
		if (filter_EQS_shipmentNo != null && filter_EQS_shipmentNo > 0) {
			hql += " and b.shipmentNo = "+filter_EQS_shipmentNo ;
		}
		if (filter_EQI_orderNo != null && filter_EQI_orderNo > 0) {
			hql += " and a.order.orderNo = "+filter_EQI_orderNo;
		}
		List list = this.getSession().createQuery(hql).list();
		count = list.size();
		return count;
	}
}
