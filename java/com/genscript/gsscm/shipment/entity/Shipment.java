package com.genscript.gsscm.shipment.entity;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.inventory.entity.Warehouse;

@SuppressWarnings("serial")
@Entity
@Table(name = "shipments", catalog = "shipping")
public class Shipment implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipment_id", unique = true, nullable = false)
	private Integer shipmentId;
	private String shipmentNo;
	private String priority;
	private String shippingType;
	private String shippingRule;
	private Integer shippingClerk;
	private String currency;
	private String description;
	private Date shipDate;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "warehouse_id")
	private Warehouse wareHouse;
	private Integer companyId;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private Integer custNo;
	private String trackingNo;
	private String shippingFile;
	private Date receiveTime;
	
	@OneToMany
	@JoinColumn(name = "shipment_id")
	private List<ShipmentLine> shipmentLineList;
	@OneToMany
	@JoinColumn(name = "shipment_id")
	private List<ShipPackage> shipPackageList;
	

	// 多对多定义
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "shipping_clerk_shipment_adjustment", catalog = "shipping", joinColumns = { @JoinColumn(name = "shipmentId") }, inverseJoinColumns = { @JoinColumn(name = "shippingClerk") })
	// 集合按id排序.
	@OrderBy("clerkId")
	private List<ShipClerk> shipClerkList;
	
	
	public List<ShipClerk> getShipClerkList() {
		return shipClerkList;
	}

	public void setShipClerkList(List<ShipClerk> shipClerkList) {
		this.shipClerkList = shipClerkList;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public Integer getShipmentId() {
		return this.shipmentId;
	}

	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}

	@Column(name = "shipment_no", length = 20)
	public String getShipmentNo() {
		return this.shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

	@Column(name = "priority", length = 50)
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "shipping_type", length = 50)
	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	@Column(name = "shipping_rule", length = 50)
	public String getShippingRule() {
		return this.shippingRule;
	}

	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}

	@Column(name = "currency", length = 10)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ship_date", length = 19)
	public Date getShipDate() {
		return this.shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

//	@Column(name = "warehouse_id", nullable = false)
//	public Integer getWarehouseId() {
//		return this.warehouseId;
//	}
//
//	public void setWarehouseId(Integer warehouseId) {
//		this.warehouseId = warehouseId;
//	}

	@Column(name = "company_id", nullable = false)
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	@Column(name = "status", length = 20)
	public String getStatus() {
		return this.status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "creation_date", nullable = false, length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "created_by", nullable = false)
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modify_date", nullable = false, length = 19)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "modified_by", nullable = false)
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Warehouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public Integer getShippingClerk() {
		return shippingClerk;
	}

	public void setShippingClerk(Integer shippingClerk) {
		this.shippingClerk = shippingClerk;
	}

	


	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	
	

	public String getShippingFile() {
		return shippingFile;
	}

	public void setShippingFile(String shippingFile) {
		this.shippingFile = shippingFile;
	}
	
	

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public List<ShipmentLine> getShipmentLineList() {
		return shipmentLineList;
	}

	public void setShipmentLineList(List<ShipmentLine> shipmentLineList) {
		this.shipmentLineList = shipmentLineList;
	}

	public List<ShipPackage> getShipPackageList() {
		return shipPackageList;
	}

	public void setShipPackageList(List<ShipPackage> shipPackageList) {
		this.shipPackageList = shipPackageList;
	}

}