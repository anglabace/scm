package com.genscript.gsscm.manufacture.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "work_order_lot", catalog = "manufacturing")
public class WorkOrderLot extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String lotNo;
	private Integer workOrderNo;
	private BigDecimal storageTemperature;
	private BigDecimal shipTemperature;
	private String lotDesc;
	@Column(name = "dept_code")
	private String departmentCode;
	private String workGroupCode;
	private String status;
	private String quantity;
	private String adtlInfo1;
	@Transient
	private String createdByName;
	@Transient
	private String creationDateStr;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public Integer getWorkOrderNo() {
		return workOrderNo;
	}
	public void setWorkOrderNo(Integer workOrderNo) {
		this.workOrderNo = workOrderNo;
	}
	public BigDecimal getStorageTemperature() {
		return storageTemperature;
	}
	public void setStorageTemperature(BigDecimal storageTemperature) {
		this.storageTemperature = storageTemperature;
	}
	public BigDecimal getShipTemperature() {
		return shipTemperature;
	}
	public void setShipTemperature(BigDecimal shipTemperature) {
		this.shipTemperature = shipTemperature;
	}
	public String getLotDesc() {
		return lotDesc;
	}
	public void setLotDesc(String lotDesc) {
		this.lotDesc = lotDesc;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getWorkGroupCode() {
		return workGroupCode;
	}
	public void setWorkGroupCode(String workGroupCode) {
		this.workGroupCode = workGroupCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCreationDateStr() {
		return creationDateStr;
	}
	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAdtlInfo1() {
		return adtlInfo1;
	}
	public void setAdtlInfo1(String adtlInfo1) {
		this.adtlInfo1 = adtlInfo1;
	}
	
	

}
