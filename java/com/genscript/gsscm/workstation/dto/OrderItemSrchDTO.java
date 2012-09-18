package com.genscript.gsscm.workstation.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.entity.ServiceClassification;

@XmlType(name = "OrderItemSrchDTO", namespace = WsConstants.NS)
public class OrderItemSrchDTO {

	private Integer orderItemId;
	private Integer orderNo;
	private String itemNo;
	private String type;
	private Integer clsId;
	private Warehouse warehouse;
	private String warehouseName;
	private PurchaseOrder purchaseOrder;
	private Integer purchaseOrderNo;
	private ProductClass productClassification;
	private String productClassificationName;
	private ServiceClass ServiceClassification;
	private String serviceClassificationName;
	private String psName;
	private Integer yesOrNo;
	private String relationType;

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public ProductClass getProductClassification() {
		return productClassification;
	}

	public void setProductClassification(
			ProductClass productClassification) {
		this.productClassification = productClassification;
	}

	public ServiceClass getServiceClassification() {
		return ServiceClassification;
	}

	public void setServiceClassification(
			ServiceClass serviceClassification) {
		ServiceClassification = serviceClassification;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getProductClassificationName() {
		return productClassificationName;
	}

	public void setProductClassificationName(String productClassificationName) {
		this.productClassificationName = productClassificationName;
	}

	public String getServiceClassificationName() {
		return serviceClassificationName;
	}

	public void setServiceClassificationName(String serviceClassificationName) {
		this.serviceClassificationName = serviceClassificationName;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public Integer getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Integer purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Integer getYesOrNo() {
		return yesOrNo;
	}

	public void setYesOrNo(Integer yesOrNo) {
		this.yesOrNo = yesOrNo;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	

}
