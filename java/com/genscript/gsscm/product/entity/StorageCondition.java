package com.genscript.gsscm.product.entity;

import java.io.Serializable;

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
@Table(name = "product_storage_condition", catalog="product")
@XmlType(name = "StorageCondition", namespace = WsConstants.NS)
public class StorageCondition extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008369632411038932L;
	@Id
	private Integer productId;
	private String form;
	private String container;
	private Double temperature;
	private Double humidity;
	private String lightProtection;
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
	 * @return the humidity
	 */
	public Double getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
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
	 * @return the lightProtection
	 */
	public String getLightProtection() {
		return lightProtection;
	}
	/**
	 * @param lightProtection the lightProtection to set
	 */
	public void setLightProtection(String lightProtection) {
		this.lightProtection = lightProtection;
	}
}
