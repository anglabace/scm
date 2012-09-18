package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name="ShippingTotalDTO", namespace=WsConstants.NS)
public class ShippingTotalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5157507142592851817L;
	private Integer shipVia;
	private Integer packageTotal;
	private Double billableWeightTotal;
	private Double costTotal;	
	private String costTotalShow;
	private String zone;
	private String shipAccountFlag;
	private String shipAccount;
	private String currency;
	private String currencySymbol;
	private String handlingFee;
	
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the shipVia
	 */
	public Integer getShipVia() {
		return shipVia;
	}
	/**
	 * @param shipVia the shipVia to set
	 */
	public void setShipVia(Integer shipVia) {
		this.shipVia = shipVia;
	}
	/**
	 * @return the packageTotal
	 */
	public Integer getPackageTotal() {
		return packageTotal;
	}
	/**
	 * @param packageTotal the packageTotal to set
	 */
	public void setPackageTotal(Integer packageTotal) {
		this.packageTotal = packageTotal;
	}
	/**
	 * @return the billableWeightTotal
	 */
	public Double getBillableWeightTotal() {
		return billableWeightTotal;
	}
	/**
	 * @param billableWeightTotal the billableWeightTotal to set
	 */
	public void setBillableWeightTotal(Double billableWeightTotal) {
		this.billableWeightTotal = billableWeightTotal;
	}
	/**
	 * @return the costTotal
	 */
	public Double getCostTotal() {
		return costTotal;
	}
	/**
	 * @param costTotal the costTotal to set
	 */
	public void setCostTotal(Double costTotal) {
		this.costTotal = costTotal;
	}
	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}
	/**
	 * @return the shipAccountFlag
	 */
	public String getShipAccountFlag() {
		return shipAccountFlag;
	}
	/**
	 * @param shipAccountFlag the shipAccountFlag to set
	 */
	public void setShipAccountFlag(boolean accountFlag) {
		if (accountFlag) {
			this.shipAccountFlag = "Y";	
		} else {
			this.shipAccountFlag = "N";				
		}
	}

	public void setShipAccountFlag(String shipAccountFlag) {
		this.shipAccountFlag = shipAccountFlag;
	}
	/**
	 * @return the shipAccount
	 */
	public String getShipAccount() {
		return shipAccount;
	}
	/**
	 * @param shipAccount the shipAccount to set
	 */
	public void setShipAccount(String shipAccount) {
		this.shipAccount = shipAccount;
	}
	public String getCostTotalShow() {
		return costTotalShow;
	}
	public void setCostTotalShow(String costTotalShow) {
		this.costTotalShow = costTotalShow;
	}
	public String getHandlingFee() {
		return handlingFee;
	}
	public void setHandlingFee(String handlingFee) {
		this.handlingFee = handlingFee;
	}
	
}
