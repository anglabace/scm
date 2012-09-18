package com.genscript.gsscm.workstation.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.workstation.entity.ShippingClerkAdjustment;
import com.genscript.gsscm.workstation.entity.ShippingClerkAssigned;

public class OrderItemDTO implements Serializable {

	private static final long serialVersionUID = -1510059634346219560L;
	
	private Integer itemNo;
	private String status;
	private String name;
	private String type;
	private String salesContact;
	private OrderMain order;
	private Warehouse warehouse;
	private PurchaseOrder purchaseOrder;
	private Vendor vendors;
	private ShippingClerkAdjustment shippingClerkAdjustment;
	private ShippingClerkAssigned shippingClerkAssigned;
	private ServiceClass serviceClassification;
	private ProductClass ProductClassification;
	private String comment;
	private String assigned;
	private Integer orderItemId;
	private String relationType;

	public ShippingClerkAdjustment getShippingClerkAdjustment() {
		return shippingClerkAdjustment;
	}

	public void setShippingClerkAdjustment(
			ShippingClerkAdjustment shippingClerkAdjustment) {
		this.shippingClerkAdjustment = shippingClerkAdjustment;
	}

	public ShippingClerkAssigned getShippingClerkAssigned() {
		return shippingClerkAssigned;
	}

	public void setShippingClerkAssigned(ShippingClerkAssigned shippingClerkAssigned) {
		this.shippingClerkAssigned = shippingClerkAssigned;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public void setVendors(Vendor vendors) {
		this.vendors = vendors;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrderMain getOrder() {
		return order;
	}

	public void setOrder(OrderMain order) {
		this.order = order;
	}

	
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Vendor getVendors() {
		return vendors;
	}

	public void setVendor(Vendor vendors) {
		this.vendors = vendors;
	}

	public ShippingClerkAdjustment getShipClerkAdjustment() {
		return shippingClerkAdjustment;
	}

	public void setShipClerkAdjustment(ShippingClerkAdjustment shippingClerkAdjustment) {
		this.shippingClerkAdjustment = shippingClerkAdjustment;
	}

	public ShippingClerkAssigned getShippingClerkAssign() {
		return shippingClerkAssigned;
	}

	public void setShippingClerkAssign(ShippingClerkAssigned shippingClerkAssigned) {
		this.shippingClerkAssigned = shippingClerkAssigned;
	}

	public ServiceClass getServiceClassification() {
		return serviceClassification;
	}

	public void setServiceClassification(ServiceClass serviceClassification) {
		this.serviceClassification = serviceClassification;
	}

	public ProductClass getProductClassification() {
		return ProductClassification;
	}

	public void setProductClassification(ProductClass productClassification) {
		ProductClassification = productClassification;
	}

	public  String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	
	
	
}
