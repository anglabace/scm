package com.genscript.gsscm.product.entity;

import java.io.Serializable;
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
 * RestrictShip
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "product_restrict_ship", catalog="product")
public class RestrictShip extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971363364441425010L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restrict_id")
	private Integer id;
	private Integer productId;	
	private String country;
	private String state;
	private String zipCode;
	private Date effFrom;
	private Date effTo;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the effFrom
	 */
	public Date getEffFrom() {
		return effFrom;
	}
	/**
	 * @param effFrom the effFrom to set
	 */
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	/**
	 * @return the effTo
	 */
	public Date getEffTo() {
		return effTo;
	}
	/**
	 * @param effTo the effTo to set
	 */
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
}
