package com.genscript.gsscm.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "RoyaltyProductDTO", namespace = WsConstants.NS)
public class RoyaltyProductDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -570915645565259491L;
	private Integer id;
	private Integer royaltyId;
	private Integer licenseId;
	private String catalogNo;
	private BigDecimal sellingPriceCmsn;
	private BigDecimal grossProfitCmsn;
	private BigDecimal unitRateCmsn;
	// 非本表而是业务需要的属性
	private String royaltyName;

	public RoyaltyProductDTO() {
		this.sellingPriceCmsn = BigDecimal.ZERO;
		this.grossProfitCmsn = BigDecimal.ZERO;
		this.unitRateCmsn = BigDecimal.ZERO;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the royaltyId
	 */
	public Integer getRoyaltyId() {
		return royaltyId;
	}

	/**
	 * @param royaltyId
	 *            the royaltyId to set
	 */
	public void setRoyaltyId(Integer royaltyId) {
		this.royaltyId = royaltyId;
	}

	/**
	 * @return the licenseId
	 */
	public Integer getLicenseId() {
		return licenseId;
	}

	/**
	 * @param licenseId
	 *            the licenseId to set
	 */
	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}

	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}

	/**
	 * @param catalogNo
	 *            the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	/**
	 * @return the sellingPriceCmsn
	 */
	public BigDecimal getSellingPriceCmsn() {
		return sellingPriceCmsn;
	}

	/**
	 * @param sellingPriceCmsn
	 *            the sellingPriceCmsn to set
	 */
	public void setSellingPriceCmsn(BigDecimal sellingPriceCmsn) {
		this.sellingPriceCmsn = sellingPriceCmsn;
	}

	/**
	 * @return the grossProfitCmsn
	 */
	public BigDecimal getGrossProfitCmsn() {
		return grossProfitCmsn;
	}

	/**
	 * @param grossProfitCmsn
	 *            the grossProfitCmsn to set
	 */
	public void setGrossProfitCmsn(BigDecimal grossProfitCmsn) {
		this.grossProfitCmsn = grossProfitCmsn;
	}

	/**
	 * @return the unitRateCmsn
	 */
	public BigDecimal getUnitRateCmsn() {
		return unitRateCmsn;
	}

	/**
	 * @param unitRateCmsn
	 *            the unitRateCmsn to set
	 */
	public void setUnitRateCmsn(BigDecimal unitRateCmsn) {
		this.unitRateCmsn = unitRateCmsn;
	}

	/**
	 * @return the royaltyName
	 */
	public String getRoyaltyName() {
		return royaltyName;
	}

	/**
	 * @param royaltyName
	 *            the royaltyName to set
	 */
	public void setRoyaltyName(String royaltyName) {
		this.royaltyName = royaltyName;
	}
}
