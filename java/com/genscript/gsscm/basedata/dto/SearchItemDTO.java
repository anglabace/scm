package com.genscript.gsscm.basedata.dto;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "SearchItemDTO", namespace = WsConstants.NS)
public class SearchItemDTO {
	private Integer prefStorage;
	private Integer prefWarehouse;
	private String catalogNo;
	private Integer clsId;
	private String clsName;
	private String taxStatus;
	private String customerInfo;
	private Integer scheduleShip;
	private String description;
	private String fullDesc;
	private String sellingNote;
	private String qtyUom;
	private Double size;
	private Double cost;
	private String uom;
	private String name;
	private String catalogId;
	private String catalogName;
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public Integer getPrefStorage() {
		return prefStorage;
	}
	public void setPrefStorage(Integer prefStorage) {
		this.prefStorage = prefStorage;
	}
	public Integer getPrefWarehouse() {
		return prefWarehouse;
	}
	public void setPrefWarehouse(Integer prefWarehouse) {
		this.prefWarehouse = prefWarehouse;
	}
	public Integer getClsId() {
		return clsId;
	}
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	public String getClsName() {
		return clsName;
	}
	public void setClsName(String clsName) {
		this.clsName = clsName;
	}
	public String getTaxStatus() {
		return taxStatus;
	}
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public Integer getScheduleShip() {
		return scheduleShip;
	}
	public void setScheduleShip(Integer scheduleShip) {
		this.scheduleShip = scheduleShip;
	}
	public String getFullDesc() {
		return fullDesc;
	}
	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
	}
	public String getSellingNote() {
		return sellingNote;
	}
	public void setSellingNote(String sellingNote) {
		this.sellingNote = sellingNote;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
}
