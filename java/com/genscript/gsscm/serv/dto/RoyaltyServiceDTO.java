package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "RoyaltyServiceDTO", namespace = WsConstants.NS)
public class RoyaltyServiceDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3229786519507879730L;
	
	private Integer id;
	private Integer royaltyId;
	private Integer licenseId;
	private String catalogNo;
	private BigDecimal sellingPriceCmsn;
	private BigDecimal grossProfitCmsn;
	private BigDecimal unitRateCmsn;
	//非本表而是业务需要的属性
	private String royaltyName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoyaltyId() {
		return royaltyId;
	}
	public void setRoyaltyId(Integer royaltyId) {
		this.royaltyId = royaltyId;
	}
	public Integer getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public BigDecimal getSellingPriceCmsn() {
		return sellingPriceCmsn;
	}
	public void setSellingPriceCmsn(BigDecimal sellingPriceCmsn) {
		this.sellingPriceCmsn = sellingPriceCmsn;
	}
	public BigDecimal getGrossProfitCmsn() {
		return grossProfitCmsn;
	}
	public void setGrossProfitCmsn(BigDecimal grossProfitCmsn) {
		this.grossProfitCmsn = grossProfitCmsn;
	}
	public BigDecimal getUnitRateCmsn() {
		return unitRateCmsn;
	}
	public void setUnitRateCmsn(BigDecimal unitRateCmsn) {
		this.unitRateCmsn = unitRateCmsn;
	}
	public String getRoyaltyName() {
		return royaltyName;
	}
	public void setRoyaltyName(String royaltyName) {
		this.royaltyName = royaltyName;
	}
	

}
