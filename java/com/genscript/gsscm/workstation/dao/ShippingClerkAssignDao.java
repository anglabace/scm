package com.genscript.gsscm.workstation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.workstation.entity.ShippingClerkAssigned;

@Repository
public class ShippingClerkAssignDao extends HibernateDao<ShippingClerkAssigned, Integer> {
	
	public List<ShippingClerkAssigned> getListByShippingClerkAssign() {
 		//String hql="select u from User u ,ShippingClerkAssigned s  where u.userId = s.shippingClerk";
		String hql="select distinct(u) from User u ,ShipClerk s where u.userId = s.clerkId and s.clerkFunction in ('Picker/Packer/Shipping', 'All')";
		return this.find(hql);
	}
	public OrderItemDTO getOrderItem(String itemId){
		String hql = "select oi,wh.warehouseId,ord.statusReason from OrderItem oi,OrderMain ord,PurchaseOrder po,Warehouse wh where oi.orderNo = ord.orderNo and po.orderNo = ord.srcPoNo and po.warehouseId = wh.warehouseId and oi.orderItemId =:itemId";
		List list = this.getSession().createQuery(hql).setString("itemId", itemId).list();
		OrderItemDTO dto  = null;
		if(list !=null && list.size()>0){
			Object[] obj = (Object[])list.get(0);
			OrderItem oi = (OrderItem)obj[0];
			int warehouseId = Integer.parseInt(obj[1]+"");
			String statusUpdReason = obj[2]+"";
			dto = new OrderItemDTO();
			dto.setOrderNo(oi.getOrderNo());
			dto.setItemNo(oi.getItemNo());
			dto.setType(oi.getType());
			dto.setClsId(oi.getClsId());
			dto.setWarehouseId(warehouseId);
			dto.setStatusUpdReason(statusUpdReason);
		}
		return dto;
	}
}
