package com.genscript.gsscm.shipment.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ORDER SHIP INFO.
 * 
 * 
 * @author mingrs
 */

@Entity
@Table(name = "shipping_charge_log", catalog = "shipping")
public class ShippingChargeLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer packageId;
	private Integer custNo;
	private String chargeAccType;
	private String chargeAccNo;
	private String receiptAccNo;
	private Double chargeAmt;
	private String currency;
	private Date chargeDate;
	private Integer chargedBy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getChargeAccType() {
		return chargeAccType;
	}
	public void setChargeAccType(String chargeAccType) {
		this.chargeAccType = chargeAccType;
	}
	public String getChargeAccNo() {
		return chargeAccNo;
	}
	public void setChargeAccNo(String chargeAccNo) {
		this.chargeAccNo = chargeAccNo;
	}
	public String getReceiptAccNo() {
		return receiptAccNo;
	}
	public void setReceiptAccNo(String receiptAccNo) {
		this.receiptAccNo = receiptAccNo;
	}

	public Double getChargeAmt() {
		return chargeAmt;
	}
	public void setChargeAmt(Double chargeAmt) {
		this.chargeAmt = chargeAmt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public Integer getChargedBy() {
		return chargedBy;
	}
	public void setChargedBy(Integer chargedBy) {
		this.chargedBy = chargedBy;
	} 
	
	
}
