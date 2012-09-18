package com.genscript.gsscm.serv.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PRODUCT RELATION.
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "service_relation", catalog="product")
public class ServcieRelation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1496463054018331451L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relation_id")
	private Integer relationId;
	private Integer serviceId;
	private String relationType;
	private Integer rltProductId;
	private String lookupFlag;
	private String displayOnlyBo;
	private String mandatoryFlag;
	private Date effFrom;
	private Date effTo;
	private BigDecimal standardPrice;
	private String discount;
	private BigDecimal unitPrice;
	private String relateInfo;
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public Integer getRltProductId() {
		return rltProductId;
	}
	public void setRltProductId(Integer rltProductId) {
		this.rltProductId = rltProductId;
	}
	public String getRelateInfo() {
		return relateInfo;
	}
	public void setRelateInfo(String relateInfo) {
		this.relateInfo = relateInfo;
	}
	
	public String getLookupFlag() {
		return lookupFlag;
	}
	public void setLookupFlag(String lookupFlag) {
		this.lookupFlag = lookupFlag;
	}
	public String getDisplayOnlyBo() {
		return displayOnlyBo;
	}
	public void setDisplayOnlyBo(String displayOnlyBo) {
		this.displayOnlyBo = displayOnlyBo;
	}
	public String getMandatoryFlag() {
		return mandatoryFlag;
	}
	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}
	public Date getEffFrom() {
		return effFrom;
	}
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	public Date getEffTo() {
		return effTo;
	}
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	public BigDecimal getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
