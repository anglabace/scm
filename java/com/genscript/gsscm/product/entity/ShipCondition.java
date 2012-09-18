package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
import com.genscript.gsscm.common.constant.WsConstants;

/**
 * ShipCondition.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "product_ship_condition", catalog = "product")
@XmlType(name = "ShipCondition", namespace = WsConstants.NS)
public class ShipCondition extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008369632411038932L;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the form
	 */
	public String getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(String form) {
		this.form = form;
	}

	/**
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * @return the domShipWeight
	 */
	public Double getDomShipWeight() {
		return domShipWeight;
	}

	/**
	 * @param domShipWeight the domShipWeight to set
	 */
	public void setDomShipWeight(Double domShipWeight) {
		this.domShipWeight = domShipWeight;
	}

	/**
	 * @return the specShipCharge
	 */
	public Double getSpecShipCharge() {
		return specShipCharge;
	}

	/**
	 * @param specShipCharge the specShipCharge to set
	 */
	public void setSpecShipCharge(Double specShipCharge) {
		this.specShipCharge = specShipCharge;
	}

	/**
	 * @return the shipExemptFlag
	 */
	public String getShipExemptFlag() {
		return shipExemptFlag;
	}

	/**
	 * @param shipExemptFlag the shipExemptFlag to set
	 */
	public void setShipExemptFlag(String shipExemptFlag) {
		this.shipExemptFlag = shipExemptFlag;
	}

	/**
	 * @return the shipPkgType
	 */
	public String getShipPkgType() {
		return shipPkgType;
	}

	/**
	 * @param shipPkgType the shipPkgType to set
	 */
	public void setShipPkgType(String shipPkgType) {
		this.shipPkgType = shipPkgType;
	}

	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the intlShipWeight
	 */
	public Double getIntlShipWeight() {
		return intlShipWeight;
	}

	/**
	 * @param intlShipWeight the intlShipWeight to set
	 */
	public void setIntlShipWeight(Double intlShipWeight) {
		this.intlShipWeight = intlShipWeight;
	}

	/**
	 * @return the shipWeightUom
	 */
	public String getShipWeightUom() {
		return shipWeightUom;
	}

	/**
	 * @param shipWeightUom the shipWeightUom to set
	 */
	public void setShipWeightUom(String shipWeightUom) {
		this.shipWeightUom = shipWeightUom;
	}

	public String getUrgentFlag() {
		return urgentFlag;
	}

	public void setUrgentFlag(String urgentFlag) {
		this.urgentFlag = urgentFlag;
	}
	
	
}
