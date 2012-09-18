package com.genscript.gsscm.serv.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ServiceRestrictShip
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "service_restrict_ship", catalog="product")
public class ServiceRestrictShip extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1269864554517711200L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restrict_id")
	private Integer id;
	private Integer serviceId;	
	private String country;
	private String state;
	private String zipCode;
	private Date effFrom;
	private Date effTo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	
}
