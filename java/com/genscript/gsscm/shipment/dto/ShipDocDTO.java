package com.genscript.gsscm.shipment.dto;

import java.util.List;

import com.genscript.gsscm.manufacture.entity.ManuDocument;

public class ShipDocDTO {
	private List<ManuDocument> manuDoc;
	private List<ManuDocument> manuDocA;
	private String orderNo;
	private String itemNo;
	private String catalogNo;
	
	public List<ManuDocument> getManuDoc() {
		return manuDoc;
	}
	public void setManuDoc(List<ManuDocument> manuDoc) {
		this.manuDoc = manuDoc;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public List<ManuDocument> getManuDocA() {
		return manuDocA;
	}
	public void setManuDocA(List<ManuDocument> manuDocA) {
		this.manuDocA = manuDocA;
	}

}
