package com.genscript.gsscm.order.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dao.MfgOrderItemDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderLineErpMappingDao;
import com.genscript.gsscm.order.dto.OrderErpMappingDTO;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderLineErpMapping;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;

@Service
@Transactional
public class ErpOrderService {

	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	@Autowired
	private OrderLineErpMappingDao orderLineErpMappingDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	@Autowired
	private MfgOrderItemDao mfgOrderItemDao;
	@Autowired(required = false)
    private DozerBeanMapper dozer;
	
	public List<Integer> updateOrderSoPo(OrderErpMappingDTO orderErpMappingDTO) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Integer orderNo = orderErpMappingDTO.getOrderNo();
		List<OrderLineErpMapping> orderLineList = orderErpMappingDTO.getOrderLineList();
		OrderErpMapping orderErpMapping = dozer.map(orderErpMappingDTO, OrderErpMapping.class);
		OrderErpMapping srcOrderErpMapping = orderErpMappingDao.findUniqueBy("orderNo", orderNo);
		if(srcOrderErpMapping != null){
			//orderErpMapping = (OrderErpMapping)ModelUtils.mergerModel(orderErpMapping, srcOrderErpMapping);
			if(StringUtils.isNotBlank(srcOrderErpMapping.getErpNjSo()) && StringUtils.isNotBlank(orderErpMapping.getErpNjSo())){
				orderErpMapping.setErpNjSo(srcOrderErpMapping.getErpNjSo()+","+orderErpMapping.getErpNjSo());
			}else if(StringUtils.isNotBlank(srcOrderErpMapping.getErpNjSo())){
				orderErpMapping.setErpNjSo(srcOrderErpMapping.getErpNjSo());
			}
			if(StringUtils.isNotBlank(srcOrderErpMapping.getErpUsPo()) && StringUtils.isNotBlank(orderErpMapping.getErpUsPo())){
				orderErpMapping.setErpUsPo(srcOrderErpMapping.getErpUsPo()+","+orderErpMapping.getErpUsPo());
			}else if(StringUtils.isNotBlank(srcOrderErpMapping.getErpUsPo())){
				orderErpMapping.setErpUsPo(srcOrderErpMapping.getErpUsPo());
			}
			if(StringUtils.isNotBlank(srcOrderErpMapping.getErpUsSo()) && StringUtils.isNotBlank(orderErpMapping.getErpUsSo())){
				orderErpMapping.setErpUsSo(srcOrderErpMapping.getErpUsSo()+","+orderErpMapping.getErpUsSo());
			}else if(StringUtils.isNotBlank(srcOrderErpMapping.getErpUsSo())){
				orderErpMapping.setErpUsSo(srcOrderErpMapping.getErpUsSo());
			}
			
			orderErpMappingDao.getSession().evict(srcOrderErpMapping);
		}
		orderErpMappingDao.save(orderErpMapping);
		if(orderNo != null && orderLineList != null && orderLineList.size() > 0){
			for(OrderLineErpMapping orderLine : orderLineList){
				Integer orderItemNo = orderLine.getItemNo();
				//System.out.println("<<<<<<<<<<<<<<<"+orderLineList+", "+orderLine);
				OrderItem orderItem = orderItemDao.getOrderItem(orderNo, orderItemNo);
				//System.out.println(">>>>>>>>>"+orderNo+", "+ orderItemNo+"======"+orderItem);
				Integer orderItemId = orderItem.getOrderItemId();
				OrderLineErpMapping orderLineMapping = dozer.map(orderLine, OrderLineErpMapping.class);
				OrderLineErpMapping srcOrderLineMapping = orderLineErpMappingDao.getErpItem(orderNo, orderItemNo);
				//System.out.println("*********"+orderLineMapping);
				if(srcOrderLineMapping != null){
					orderLineMapping = (OrderLineErpMapping)ModelUtils.mergerModel(orderLineMapping, srcOrderLineMapping);
					orderLineErpMappingDao.getSession().evict(srcOrderLineMapping);
				}
				orderLineMapping.setOrderItemId(orderItemId);
				orderLineMapping.setOrderNo(orderNo);
				//System.out.println(orderLineMapping);
				
				orderLineErpMappingDao.save(orderLineMapping);
				//orderLineErpMappingDao.getSession().evict(orderLineMapping);
			}
		}
		if(StringUtils.isNotBlank(orderErpMapping.getErpUsPo())){
			List<PurchaseOrder> purchaseOrderList = purchaseOrderDao.findBy("srcSoNo", orderErpMapping.getOrderNo());
			if(purchaseOrderList != null && purchaseOrderList.size() > 0){
				for(PurchaseOrder purchaseOrder : purchaseOrderList){
					purchaseOrder.setStatus("CC");
				}
			}
		}
		if(StringUtils.isNotBlank(orderErpMapping.getErpNjSo())){
			List<MfgOrder> mfgOrderList = mfgOrderDao.findBy("srcSoNo", orderErpMapping.getOrderNo());
			if(mfgOrderList != null && mfgOrderList.size() > 0){
				List<String> itemStatus = new ArrayList<String>();
		    	itemStatus.add("CN");
		    	List<Integer> itemIdList = new ArrayList<Integer>();
		    	List<MfgOrderItem> itemList = new ArrayList<MfgOrderItem>();
				for(MfgOrder mfgOrder : mfgOrderList){
					mfgOrder.setStatus("CC");
					itemList.addAll(this.mfgOrderItemDao.getItemListNotInType(mfgOrder.getOrderNo(),itemStatus));
				}
				for(MfgOrderItem item:itemList) {
					itemIdList.add(item.getOrderItemId());
				}
				return itemIdList;
			}
		}
		return null;
	}
}
