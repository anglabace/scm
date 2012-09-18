package com.genscript.gsscm.manufacture.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;

@XmlType(name = "WorkOrderDTO", namespace = WsConstants.NS)
public class WorkOrderDTO implements Serializable {
	
	private static final long serialVersionUID = -1510059634346219560L;
	private Integer orderNo;
	private String altOrderNo;
	private String type;
	private String itemType;
	private Integer clsId;
	private Date orderDate;
	private Date exprDate;
	private String source;
	private String status;
	private String documentQc;
	private String productQc;
	private String documentQa;
	private String productQa;
	private Date productQcDate;
	private Date documentQaDate;
	private Date productQaDate;
	private Date documentQcDate;
	private Integer soNo;//china so
	private Integer soItemNo;
	private Integer workCenterId;	
	private Integer workCenterSpvs;
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
	private Integer scheduleChangeFlag;
	private Date actualStart;
	private Date customStart;
	private Date customEnd;
	private Date actualEnd;
	private Short companyId;
	protected Date creationDate;
	protected Integer createdBy;
	protected Date modifyDate;
	protected Integer modifiedBy;
	//以下为非数据库属性
	private Integer srcSoNo;//us so
	private String catalogNoDesc;
	private String modifyUser;
	private List<DropDownDTO> workCenterSuperList;
	private List<DropDownDTO> workGroupSuperList;
	private String salesContact;
	private String projectSupport;
	private String techSupport;
	private List<Integer> delOperationIdList;
	private List<WorkOrderOperation> workOrderOperationList;
	private String workGroupNames;
	private String workGroupIds;
	private String parentOrderNo;
	//非数据库属性之file
	private List<ManuDocument> documentList;
	private List<Integer> delDocIdList;
	private String workGroupName;
	private String batchNo;
	private String woBatchId;
	private String locationCode;
	private String name;
	private Integer quantityWoTemp; 
	// receving_clerk(为Search封装的字段)
	private String recevingClerk;
	//编辑页面Quality Assurance
	private String lastDocumentQaDate;
	private String lastProductQaDate;
	private Integer orderNo2;
	//print labels
	private String labels;
	//change status reason
	private String reason;
	private String comment;
	
	private String experimentDataType;
	private String hostName;
	private Integer hostAmount;
	private String hostNo;
	
	//customer
	private Integer custNo;
	private String email;
	private String password;
	private String custName;
	
	//lot no
	private List<Integer> delWorkOrderLotList;
	private List<WorkOrderLot> workOrderLotList;
	private Integer lotLength;
	//ship
	private String shippable;
	
	
	public Integer getOrderNo2() {
		return orderNo2;
	}
	public void setOrderNo2(Integer orderNo2) {
		this.orderNo2 = orderNo2;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getWoBatchId() {
		return woBatchId;
	}
	public void setWoBatchId(String woBatchId) {
		this.woBatchId = woBatchId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getWorkGroupName() {
		return workGroupName;
	}
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public List<DropDownDTO> getWorkCenterSuperList() {
		return workCenterSuperList;
	}
	public void setWorkCenterSuperList(List<DropDownDTO> workCenterSuperList) {
		this.workCenterSuperList = workCenterSuperList;
	}
	public List<DropDownDTO> getWorkGroupSuperList() {
		return workGroupSuperList;
	}
	public void setWorkGroupSuperList(List<DropDownDTO> workGroupSuperList) {
		this.workGroupSuperList = workGroupSuperList;
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
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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
//	public void setWorkGroupId(Integer workGroupId) {
//		this.workGroupId = workGroupId;
//	}
	public String getSalesContact() {
		return salesContact;
	}
	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}
	public String getProjectSupport() {
		return projectSupport;
	}
	public void setProjectSupport(String projectSupport) {
		this.projectSupport = projectSupport;
	}
	public List<Integer> getDelOperationIdList() {
		return delOperationIdList;
	}
	public void setDelOperationIdList(List<Integer> delOperationIdList) {
		this.delOperationIdList = delOperationIdList;
	}
	public List<WorkOrderOperation> getWorkOrderOperationList() {
		return workOrderOperationList;
	}
	public void setWorkOrderOperationList(
			List<WorkOrderOperation> workOrderOperationList) {
		this.workOrderOperationList = workOrderOperationList;
	}
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
	public List<ManuDocument> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<ManuDocument> documentList) {
		this.documentList = documentList;
	}
	public List<Integer> getDelDocIdList() {
		return delDocIdList;
	}
	public void setDelDocIdList(List<Integer> delDocIdList) {
		this.delDocIdList = delDocIdList;
	}
	public String getRecevingClerk() {
		return recevingClerk;
	}
	public void setRecevingClerk(String recevingClerk) {
		this.recevingClerk = recevingClerk;
	}
	public Integer getQuantityWoTemp() {
		return quantityWoTemp;
	}
	public void setQuantityWoTemp(Integer quantityWoTemp) {
		this.quantityWoTemp = quantityWoTemp;
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
	public String getLastProductQaDate() {
		return lastProductQaDate;
	}
	public void setLastProductQaDate(String lastProductQaDate) {
		this.lastProductQaDate = lastProductQaDate;
	}
	public String getLastDocumentQaDate() {
		return lastDocumentQaDate;
	}
	public void setLastDocumentQaDate(String lastDocumentQaDate) {
		this.lastDocumentQaDate = lastDocumentQaDate;
	}
	public String getWorkGroupNames() {
		return workGroupNames;
	}
	public void setWorkGroupNames(String workGroupNames) {
		this.workGroupNames = workGroupNames;
	}
	public String getWorkGroupIds() {
		return workGroupIds;
	}
	public void setWorkGroupIds(String workGroupIds) {
		this.workGroupIds = workGroupIds;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
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
	public String getTechSupport() {
		return techSupport;
	}
	public void setTechSupport(String techSupport) {
		this.techSupport = techSupport;
	}
	public String getCatalogNoDesc() {
		return catalogNoDesc;
	}
	public void setCatalogNoDesc(String catalogNoDesc) {
		this.catalogNoDesc = catalogNoDesc;
	}
	public Integer getSrcSoNo() {
		return srcSoNo;
	}
	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getParentOrderNo() {
		return parentOrderNo;
	}
	public void setParentOrderNo(String parentOrderNo) {
		this.parentOrderNo = parentOrderNo;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Integer getScheduleChangeFlag() {
		return scheduleChangeFlag;
	}
	public void setScheduleChangeFlag(Integer scheduleChangeFlag) {
		this.scheduleChangeFlag = scheduleChangeFlag;
	}
	public List<Integer> getDelWorkOrderLotList() {
		return delWorkOrderLotList;
	}
	public void setDelWorkOrderLotList(List<Integer> delWorkOrderLotList) {
		this.delWorkOrderLotList = delWorkOrderLotList;
	}
	public List<WorkOrderLot> getWorkOrderLotList() {
		return workOrderLotList;
	}
	public void setWorkOrderLotList(List<WorkOrderLot> workOrderLotList) {
		this.workOrderLotList = workOrderLotList;
	}
	public String getShippable() {
		return shippable;
	}
	public void setShippable(String shippable) {
		this.shippable = shippable;
	}
	public Integer getLotLength() {
		return lotLength;
	}
	public void setLotLength(Integer lotLength) {
		this.lotLength = lotLength;
	}

}
