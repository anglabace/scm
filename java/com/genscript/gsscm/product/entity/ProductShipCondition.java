package com.genscript.gsscm.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ProductShipCondition.
 * 
 * 
 * @author Dtf
 */
@Entity
@Table(name = "product_ship_condition", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductShipCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 851064787954492956L;
	@Id

	private Integer productId;
	private String form;
	private String container;
	private Double domShipWeight;
	private Double intlShipWeight;
	private String shipWeightUom;
	private Double temperature;
	private Double specShipCharge;
	private String shipExemptFlag;
	private String shipPkgType;
	private String urgentFlag;
	private String comment;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public Double getDomShipWeight() {
		return domShipWeight;
	}
	public void setDomShipWeight(Double domShipWeight) {
		this.domShipWeight = domShipWeight;
	}
	public Double getIntlShipWeight() {
		return intlShipWeight;
	}
	public void setIntlShipWeight(Double intlShipWeight) {
		this.intlShipWeight = intlShipWeight;
	}
	public String getShipWeightUom() {
		return shipWeightUom;
	}
	public void setShipWeightUom(String shipWeightUom) {
		this.shipWeightUom = shipWeightUom;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getSpecShipCharge() {
		return specShipCharge;
	}
	public void setSpecShipCharge(Double specShipCharge) {
		this.specShipCharge = specShipCharge;
	}
	public String getShipExemptFlag() {
		return shipExemptFlag;
	}
	public void setShipExemptFlag(String shipExemptFlag) {
		this.shipExemptFlag = shipExemptFlag;
	}
	public String getShipPkgType() {
		return shipPkgType;
	}
	public void setShipPkgType(String shipPkgType) {
		this.shipPkgType = shipPkgType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getUrgentFlag() {
		return urgentFlag;
	}
	public void setUrgentFlag(String urgentFlag) {
		this.urgentFlag = urgentFlag;
	}
	public ProductShipCondition(Integer productId, String form,
			String container, Double domShipWeight, Double intlShipWeight,
			String shipWeightUom, Double temperature, Double specShipCharge,
			String shipExemptFlag, String shipPkgType, String comment,
			Date creationDate, Integer createdBy, Date modifyDate,
			Integer modifiedBy) {
		super();
		this.productId = productId;
		this.form = form;
		this.container = container;
		this.domShipWeight = domShipWeight;
		this.intlShipWeight = intlShipWeight;
		this.shipWeightUom = shipWeightUom;
		this.temperature = temperature;
		this.specShipCharge = specShipCharge;
		this.shipExemptFlag = shipExemptFlag;
		this.shipPkgType = shipPkgType;
		this.comment = comment;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
	}
	public ProductShipCondition() {
		super();
	}
}
