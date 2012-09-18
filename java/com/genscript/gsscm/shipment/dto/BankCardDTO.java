package com.genscript.gsscm.shipment.dto;

import java.io.Serializable;
import java.util.List;

import com.genscript.gsscm.shipment.entity.ShipPackage;

public class BankCardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardHolder;
	private String cardNo;
	private String cvc;
	private String exprMonth;
	private String exprYear;
	private String billTo;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private Double customerCharge;
	private String orderNo;
	private Integer cusNo;
	private String currency;
	private String type;
	private List<ShipPackage> packageIds;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BankCardDTO(String cardHolder, String cardNo, String cvc, String exprMonth, String exprYear, String billTo, String city, String state, String country, String zipCode, Double customerCharge) {
		super();
		this.cardHolder = cardHolder;
		this.cardNo = cardNo;
		this.cvc = cvc;
		this.exprMonth = exprMonth;
		this.exprYear = exprYear;
		this.billTo = billTo;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.customerCharge = customerCharge;
	}
	public BankCardDTO() {
		super();
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getCustomerCharge() {
		return customerCharge;
	}
	public void setCustomerCharge(Double customerCharge) {
		this.customerCharge = customerCharge;
	}
	public String getCvc() {
		return cvc;
	}
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	public String getExprMonth() {
		return exprMonth;
	}
	public void setExprMonth(String exprMonth) {
		this.exprMonth = exprMonth;
	}
	public String getExprYear() {
		return exprYear;
	}
	public void setExprYear(String exprYear) {
		this.exprYear = exprYear;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public List<ShipPackage> getPackageIds() {
		return packageIds;
	}
	public void setPackageIds(List<ShipPackage> packageIds) {
		this.packageIds = packageIds;
	}
	public Integer getCusNo() {
		return cusNo;
	}
	public void setCusNo(Integer cusNo) {
		this.cusNo = cusNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
