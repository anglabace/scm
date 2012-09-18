package com.genscript.gsscm.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * RoyaltyLicense
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "royalty_licenses", catalog="product")
public class RoyaltyLicense extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971363364441425010L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "license_id")
	private Integer licenseId;
	private String title;	
	private Integer royaltyId;
	private BigDecimal royaltyRate;
	private BigDecimal sellingPriceCmsn;
	private BigDecimal grossProfitCmsn;
	private BigDecimal unitRateCmsn;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the licenseId
	 */
	public Integer getLicenseId() {
		return licenseId;
	}
	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the royaltyId
	 */
	public Integer getRoyaltyId() {
		return royaltyId;
	}
	/**
	 * @param royaltyId the royaltyId to set
	 */
	public void setRoyaltyId(Integer royaltyId) {
		this.royaltyId = royaltyId;
	}
	/**
	 * @return the royaltyRate
	 */
	public BigDecimal getRoyaltyRate() {
		return royaltyRate;
	}
	/**
	 * @param royaltyRate the royaltyRate to set
	 */
	public void setRoyaltyRate(BigDecimal royaltyRate) {
		this.royaltyRate = royaltyRate;
	}
	/**
	 * @return the sellingPriceCmsn
	 */
	public BigDecimal getSellingPriceCmsn() {
		return sellingPriceCmsn;
	}
	/**
	 * @param sellingPriceCmsn the sellingPriceCmsn to set
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
	 * @param grossProfitCmsn the grossProfitCmsn to set
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
	 * @param unitRateCmsn the unitRateCmsn to set
	 */
	public void setUnitRateCmsn(BigDecimal unitRateCmsn) {
		this.unitRateCmsn = unitRateCmsn;
	}
}
