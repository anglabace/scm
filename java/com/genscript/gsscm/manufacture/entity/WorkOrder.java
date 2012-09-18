package com.genscript.gsscm.manufacture.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "work_orders", catalog = "manufacturing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkOrder extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private Integer orderNo;
	private String altOrderNo;
	private String type;
	private String itemType;
	private Integer clsId;
	@Column(updatable = false)
	private Date orderDate; 
	private Date exprDate;
	private String source;   
	private String status;
	private String interfaceShipFlag;
	private String documentQc;
	private String productQc;
	private String documentQa;
	private String productQa;
	private Date productQcDate;
	private Date documentQaDate;
	private Date productQaDate;
	private Date documentQcDate;
	private Integer soNo;
	private Integer soItemNo;
	@Column(name = "work_center")
	private Integer workCenterId;	
	private Integer workCenterSpvs;
//	@Column(name = "work_group")
//	private Integer workGroupId;
//	private Integer workGroupSpvs;
	private Integer qaGroup;
	private Integer qaClerk;
	private Integer qcGroup;
	private Integer qcClerk;
	private String priority;
	private Integer warehouseId;
	private String description;
	private String catalogNo;
	private String itemName;
	private String comment;
	private Integer quantity;
	private Integer qtyCompleted;
	private String qtyUom;
	private Double size;
	private Double sizeCompleted;
	private String sizeUom;
	private Integer standardRoutine;
	private String storageLocation;
	private Date scheduleStart;
	private Date scheduleEnd;
	private Date actualStart;
	private Date actualEnd;
	private Date customStart;
	private Date customEnd;
	private Short companyId;
	private Integer seqNo;
	private String experimentDataType;
	private String hostName;
	private Integer hostAmount;
	private String hostNo;
	private Integer scheduleChangeFlag;//时间被刷新为1,否则为0或null
	
	@Transient
	private String catalogNoDesc;
	@Transient
	private Integer srcSoNo;
	@Transient
	private String woBatchNo;
	@Transient
	private String woBatchQaNo;
	@Transient
	private String workGroupName;
	@Transient
	private String workCenterName;
	@Transient
	private String productName;
	//以下暂且会在QC页面中用到
	@Transient
	private String qcGroupName;
	@Transient
	private String qcClerkName;
	@Transient
	private String qaGroupName;
	@Transient
	private String qaClerkName;
//	@Transient
//	private String qaClerkName;
	
	@Transient
	private String itemDesc;
	@Transient
	private String lotNo;
	@Transient
	//ship
	private String shippable;
	
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

	public String getAltOrderNo() {
		return altOrderNo;
	}

	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getExprDate() {
		return exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocumentQc() {
		return documentQc;
	}

	public void setDocumentQc(String documentQc) {
		this.documentQc = documentQc;
	}

	public String getProductQc() {
		return productQc;
	}

	public void setProductQc(String productQc) {
		this.productQc = productQc;
	}

	public Integer getSoNo() {
		return soNo;
	}

	public void setSoNo(Integer soNo) {
		this.soNo = soNo;
	}

	public Integer getSoItemNo() {
		return soItemNo;
	}

	public void setSoItemNo(Integer soItemNo) {
		this.soItemNo = soItemNo;
	}

	public Integer getWorkCenterId() {
		return workCenterId;
	}

	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}

	public Integer getWorkCenterSpvs() {
		return workCenterSpvs;
	}

	public void setWorkCenterSpvs(Integer workCenterSpvs) {
		this.workCenterSpvs = workCenterSpvs;
	}

//	public Integer getWorkGroupSpvs() {
//		return workGroupSpvs;
//	}
//
//	public void setWorkGroupSpvs(Integer workGroupSpvs) {
//		this.workGroupSpvs = workGroupSpvs;
//	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQtyCompleted() {
		return qtyCompleted;
	}

	public void setQtyCompleted(Integer qtyCompleted) {
		this.qtyCompleted = qtyCompleted;
	}

	public String getQtyUom() {
		return qtyUom;
	}

	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getSizeCompleted() {
		return sizeCompleted;
	}

	public void setSizeCompleted(Double sizeCompleted) {
		this.sizeCompleted = sizeCompleted;
	}

	public String getSizeUom() {
		return sizeUom;
	}

	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}

	public Integer getStandardRoutine() {
		return standardRoutine;
	}

	public void setStandardRoutine(Integer standardRoutine) {
		this.standardRoutine = standardRoutine;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public Date getScheduleStart() {
		return scheduleStart;
	}

	public void setScheduleStart(Date scheduleStart) {
		this.scheduleStart = scheduleStart;
	}

	public Date getScheduleEnd() {
		return scheduleEnd;
	}

	public void setScheduleEnd(Date scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}

	public Date getActualStart() {
		return actualStart;
	}

	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}

	public Date getActualEnd() {
		return actualEnd;
	}

	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}

	public Short getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Short companyId) {
		this.companyId = companyId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

//	public Integer getWorkGroupId() {
//		return workGroupId;
//	}
//
//	public void setWorkGroupId(Integer workGroupId) {
//		this.workGroupId = workGroupId;
//	}

	public Integer getQaGroup() {
		return qaGroup;
	}

	public void setQaGroup(Integer qaGroup) {
		this.qaGroup = qaGroup;
	}

	public Integer getQaClerk() {
		return qaClerk;
	}

	public void setQaClerk(Integer qaClerk) {
		this.qaClerk = qaClerk;
	}

	public Integer getQcGroup() {
		return qcGroup;
	}

	public void setQcGroup(Integer qcGroup) {
		this.qcGroup = qcGroup;
	}

	public Integer getQcClerk() {
		return qcClerk;
	}

	public void setQcClerk(Integer qcClerk) {
		this.qcClerk = qcClerk;
	}

	public String getWoBatchNo() {
		return woBatchNo;
	}

	public void setWoBatchNo(String woBatchNo) {
		this.woBatchNo = woBatchNo;
	}

	public String getWorkGroupName() {
		return workGroupName;
	}

	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getQcGroupName() {
		return qcGroupName;
	}

	public void setQcGroupName(String qcGroupName) {
		this.qcGroupName = qcGroupName;
	}

	public String getQcClerkName() {
		return qcClerkName;
	}

	public void setQcClerkName(String qcClerkName) {
		this.qcClerkName = qcClerkName;
	}

	public String getQaGroupName() {
		return qaGroupName;
	}

	public void setQaGroupName(String qaGroupName) {
		this.qaGroupName = qaGroupName;
	}

	public String getQaClerkName() {
		return qaClerkName;
	}

	public void setQaClerkName(String qaClerkName) {
		this.qaClerkName = qaClerkName;
	}

	public String getDocumentQa() {
		return documentQa;
	}

	public void setDocumentQa(String documentQa) {
		this.documentQa = documentQa;
	}

	public String getProductQa() {
		return productQa;
	}

	public void setProductQa(String productQa) {
		this.productQa = productQa;
	}

	public String getWorkCenterName() {
		return workCenterName;
	}

	public void setWorkCenterName(String workCenterName) {
		this.workCenterName = workCenterName;
	}

	public String getWoBatchQaNo() {
		return woBatchQaNo;
	}

	public void setWoBatchQaNo(String woBatchQaNo) {
		this.woBatchQaNo = woBatchQaNo;
	}

	public Date getProductQcDate() {
		return productQcDate;
	}

	public void setProductQcDate(Date productQcDate) {
		this.productQcDate = productQcDate;
	}

	public Date getDocumentQaDate() {
		return documentQaDate;
	}

	public void setDocumentQaDate(Date documentQaDate) {
		this.documentQaDate = documentQaDate;
	}

	public Date getProductQaDate() {
		return productQaDate;
	}

	public void setProductQaDate(Date productQaDate) {
		this.productQaDate = productQaDate;
	}

	public Date getDocumentQcDate() {
		return documentQcDate;
	}

	public void setDocumentQcDate(Date documentQcDate) {
		this.documentQcDate = documentQcDate;
	}

	public Integer getSrcSoNo() {
		return srcSoNo;
	}

	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}

	public String getCatalogNoDesc() {
		return catalogNoDesc;
	}

	public void setCatalogNoDesc(String catalogNoDesc) {
		this.catalogNoDesc = catalogNoDesc;
	}

	public String getInterfaceShipFlag() {
		return interfaceShipFlag;
	}

	public void setInterfaceShipFlag(String interfaceShipFlag) {
		this.interfaceShipFlag = interfaceShipFlag;
	}

	public Date getCustomStart() {
		return customStart;
	}

	public void setCustomStart(Date customStart) {
		this.customStart = customStart;
	}

	public Date getCustomEnd() {
		return customEnd;
	}

	public void setCustomEnd(Date customEnd) {
		this.customEnd = customEnd;
	}

	public String getExperimentDataType() {
		return experimentDataType;
	}

	public void setExperimentDataType(String experimentDataType) {
		this.experimentDataType = experimentDataType;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Integer getHostAmount() {
		return hostAmount;
	}

	public void setHostAmount(Integer hostAmount) {
		this.hostAmount = hostAmount;
	}

	public String getHostNo() {
		return hostNo;
	}

	public void setHostNo(String hostNo) {
		this.hostNo = hostNo;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getScheduleChangeFlag() {
		return scheduleChangeFlag;
	}

	public void setScheduleChangeFlag(Integer scheduleChangeFlag) {
		this.scheduleChangeFlag = scheduleChangeFlag;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getShippable() {
		return shippable;
	}

	public void setShippable(String shippable) {
		this.shippable = shippable;
	}
	
	
}
