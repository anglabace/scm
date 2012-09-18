package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ErpOrderDtlDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697978264847320016L;
	private String ItemNum;
	private String UnitPrice;
	private String Cost;
//	private String VtRatio;
//	private String BtRatio;
	private String Status;
	
	
	public String getItemNum() {
		return ItemNum;
	}


	public void setItemNum(String itemNum) {
		ItemNum = itemNum;
	}


	public String getUnitPrice() {
		return UnitPrice;
	}


	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}


	public String getCost() {
		return Cost;
	}


	public void setCost(String cost) {
		Cost = cost;
	}

//
//	public String getVtRatio() {
//		return VtRatio;
//	}
//
//
//	public void setVtRatio(String vtRatio) {
//		VtRatio = vtRatio;
//	}
//
//
//	public String getBtRatio() {
//		return BtRatio;
//	}
//
//
//	public void setBtRatio(String btRatio) {
//		BtRatio = btRatio;
//	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}
	
}
