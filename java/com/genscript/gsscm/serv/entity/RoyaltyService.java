package com.genscript.gsscm.serv.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * Royalty
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "royalty_services", catalog="product")
public class RoyaltyService extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971363364441425010L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer royaltyId;
	private Integer licenseId;
	private String catalogNo;
	private BigDecimal sellingPriceCmsn;
	private BigDecimal grossProfitCmsn;
	private BigDecimal unitRateCmsn;
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
	 * @param id the id to set
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
	 * @param royaltyId the royaltyId to set
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
	 * @param licenseId the licenseId to set
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
	 * @param catalogNo the catalogNo to set
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
