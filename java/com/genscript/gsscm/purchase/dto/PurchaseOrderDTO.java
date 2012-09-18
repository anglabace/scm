package com.genscript.gsscm.purchase.dto;

import java.sql.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.purchase.entity.Vendor;
@XmlType(name = "PurchaseOrderDTO", namespace = WsConstants.NS)
public class PurchaseOrderDTO {
	private Integer orderNo;
	private String altOrderNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_no")
	private Vendor vendors;
	private String priority;
	private Date orderDate;
	private Date exprDate;
	private String orderType;
	private Date expectedDate;
	private Integer purchaseContact;
	private String purchaseContactName;
	private Double subTotal;
	private Integer warehouseId;
	private String status;
	private Integer srcSoNo;
	private Integer companyId;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	//receving_clerk(为Search封装的字段)
	private String recevingClerk;
	private String vendorName;
	private String catalogNo;
	private Integer catalogQty;
	private String orderedBy;
	private Warehouse warehouse;
	private String serviceType;
	private String greenAccFlag;
	private String usPOrderNo;
	private String usSOrderNo;
	private String tam;
	private String userName;

	public Warehouse getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}


	public String getRecevingClerk() {
		return recevingClerk;
	}


	public void setRecevingClerk(String recevingClerk) {
		this.recevingClerk = recevingClerk;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Integer getPurchaseContact() {
		return purchaseContact;
	}

	public void setPurchaseContact(Integer purchaseContact) {
		this.purchaseContact = purchaseContact;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAltOrderNo() {
		return altOrderNo;
	}

	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}

	public Vendor getVendors() {
		return vendors;
	}

	public void setVendors(Vendor vendors) {
		this.vendors = vendors;
	}

	public Date getExprDate() {
		return exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getSrcSoNo() {
		return srcSoNo;
	}

	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	public Integer getWarehouseId() {
		return warehouseId;
	}


	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public String getVendorName() {
		return vendorName;
	}


	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	public String getCatalogNo() {
		return catalogNo;
	}


	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}


	public Integer getCatalogQty() {
		return catalogQty;
	}


	public void setCatalogQty(Integer catalogQty) {
		this.catalogQty = catalogQty;
	}


	public String getOrderedBy() {
		return orderedBy;
	}


	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}


	public String getPurchaseContactName() {
		return purchaseContactName;
	}


	public void setPurchaseContactName(String purchaseContactName) {
		this.purchaseContactName = purchaseContactName;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public String getGreenAccFlag() {
		return greenAccFlag;
	}


	public void setGreenAccFlag(String greenAccFlag) {
		this.greenAccFlag = greenAccFlag;
	}


	public String getUsPOrderNo() {
		return usPOrderNo;
	}


	public void setUsPOrderNo(String usPOrderNo) {
		this.usPOrderNo = usPOrderNo;
	}


	public String getUsSOrderNo() {
		return usSOrderNo;
	}


	public void setUsSOrderNo(String usSOrderNo) {
		this.usSOrderNo = usSOrderNo;
	}


	public String getTam() {
		return tam;
	}


	public void setTam(String tam) {
		this.tam = tam;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
