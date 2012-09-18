package com.genscript.gsscm.workstation.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.shipment.dao.ShipmentsDao;
import com.genscript.gsscm.shipment.dao.ShippingClerkShipmentAdjustmentDao;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShippingClerkShipmentAdjustment;
import com.genscript.gsscm.workstation.dao.ShipClerkAdjustmentDao;
import com.genscript.gsscm.workstation.dao.ShippingClerkAssignDao;
import com.genscript.gsscm.workstation.dto.OrderItemSrchDTO;
import com.genscript.gsscm.workstation.entity.ShippingClerkAdjustment;
import com.genscript.gsscm.workstation.entity.ShippingClerkAssigned;

@Service
@Transactional
public class WorkStationService {

	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShipmentsDao shipmentsDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private ShippingClerkAssignDao shippingClerkAssignDao;
	@Autowired
	private ShippingClerkShipmentAdjustmentDao shippingClerkShipmentAdjustmentDao;
/*	@Autowired
	private PurchaseOrderDao purchaseOrderDao;*/
	@Autowired
	private ShipClerkAdjustmentDao shipClerkAdjustmentDao;	
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	private OrderItemDTO orderItemDTO;
	
	/*
	 * get shipment list
	 */
	public void updateShipmentById(Integer id,String  shippingClerk,Integer userId){
		//Shipment shipment = this.shipmentsDao.getById(Integer.valueOf(id));
		/*this.shipmentsDao.getSession().evict(shipment);
		if(shipment!=null){
			shipment.setShippingClerk(shippingClerk);
			this.shipmentsDao.save(shipment);
		}*/
		this.shippingClerkShipmentAdjustmentDao.delByShipMentId(id);
		if(shippingClerk!=null){
			String[] shippingClerks = shippingClerk.split(",");
			for(String shipclerk:shippingClerks){
				ShippingClerkShipmentAdjustment sct = new ShippingClerkShipmentAdjustment();
				sct.setAdjustDate(new Date());
				sct.setAdjustedBy(userId);
				sct.setReasion("");
				sct.setShipmentId(id);
				sct.setShippingClerk(Integer.valueOf(shipclerk));
				this.shippingClerkShipmentAdjustmentDao.save(sct);
			}
		}
	}
	/**
	 * 
	 * @param page
	 * @param srch
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public Page<OrderItemDTO> searchOrderItem(Page<OrderItem> page,
			OrderItemSrchDTO srch) {
		return this.orderItemDao.searchOrderItem(page, srch);
	}
	
	public Page<OrderItemDTO> searchOrderItemOfShipment(Page<OrderItem> page,OrderItemSrchDTO srch){
		Page<OrderItemDTO> orderItemPage = this.orderItemDao.searchOrderItemOfShipment(page, srch);
		if(orderItemPage.getResult()!=null&&!orderItemPage.getResult().isEmpty()){
			for(OrderItemDTO dto :orderItemPage.getResult()){
				/*List<PurchaseOrder> porder = this.purchaseOrderDao.findBy("srcSoNo", dto.getOrderNo());
				if(porder!=null&&!porder.isEmpty()){
					dto.setPoOrderNo(porder.get(0).getOrderNo());
				}*/
				OrderErpMapping orderErpMapping = orderErpMappingDao.getById(Integer.valueOf(dto.getOrderNo()));
				if(orderErpMapping != null && orderErpMapping.getErpUsPo() != null){
					dto.setUsPoNo(orderErpMapping.getErpUsPo());
				}
			}
		}
		return orderItemPage;
	}

	public Page<OrderItemDTO> searchOrderItemOfShipment(Page<OrderItem> page,
			List<PropertyFilter> filters){
		
		Page<OrderItemDTO> retPage = new Page<OrderItemDTO>();
		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		page = orderItemDao.searchOrderItem(page, filters);
		if (page.getResult() != null) {
			for (OrderItem orderItem : page.getResult()) {
				OrderItemDTO dto = dozer.map(orderItem, OrderItemDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<OrderItemDTO> searchOrderItem(Page<OrderItem> page,
			List<PropertyFilter> filters) {
		Page<OrderItemDTO> retPage = new Page<OrderItemDTO>();
		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		page = orderItemDao.searchOrderItem(page, filters);
		if (page.getResult() != null) {
			for (OrderItem orderItem : page.getResult()) {
				OrderItemDTO dto = dozer.map(orderItem, OrderItemDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	public OrderItemDTO getOrderItem(Integer orderId) {
		OrderItem orderItem = this.orderItemDao.get(orderId);
		OrderItemDTO dto = null;
		if (orderItem != null) {
			dto = dozer.map(orderItem, OrderItemDTO.class);
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	public List<ProductClass> getListByProductName() {
		List list = this.orderItemDao
				.getListByProductClassification();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ServiceClass> getListByServiceName() {
		List list = this.orderItemDao
				.getListByServiceClassification();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Warehouse> getListByWarehouseName() {
		List list = this.orderItemDao.getListByWarehouse();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	    public List <ShippingClerkAssigned> getListByShippingClerkAssign(){		
		List list=this.shippingClerkAssignDao.getListByShippingClerkAssign();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List <OrderItem> getListByType(){
		
		List list=this.orderItemDao.getListByType();
		return list;
	}
	
	public List<OrderItem> getListByOrderNo(Integer orderNo){
		return this.orderItemDao.findBy("orderNo", orderNo);
	}
	
	public String updateClerk(ShippingClerkAdjustment adjustment)throws Exception{
		return this.shipClerkAdjustmentDao.updateClerk(adjustment);
	}
	
	
	public OrderItemDTO getOrderItem(String itemId){
		OrderItemDTO item = this.shippingClerkAssignDao.getOrderItem(itemId);
		return item;
	}
	
	public OrderItem getOrderItemById(String itemId){
		OrderItem item = this.orderItemDao.getById(Integer.valueOf(itemId));
		return item;
	}
	
	
	public User getAssignedTo(Integer orderNo,Integer itemNo,Integer warehouseId,String type,Integer clsId){
		return this.orderItemDao.getAssignedTo(orderNo,itemNo,warehouseId,type,clsId);
	}

	public ShipClerkAdjustmentDao getShipClerkAdjustmentDao() {
		return shipClerkAdjustmentDao;
	}

	public void setShipClerkAdjustmentDao(
		ShipClerkAdjustmentDao shipClerkAdjustmentDao) {
		this.shipClerkAdjustmentDao = shipClerkAdjustmentDao;
	}
	
	public OrderItemDTO getOrderItemDTO() {
		return orderItemDTO;
	}

	public void setOrderItemDTO(OrderItemDTO orderItemDTO) {
		this.orderItemDTO = orderItemDTO;
	}
}
