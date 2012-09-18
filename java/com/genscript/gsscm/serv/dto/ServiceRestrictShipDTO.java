package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ServiceRestrictShipDTO", namespace = WsConstants.NS)
public class ServiceRestrictShipDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679752891076150202L;
	private Integer id;
	private Integer serviceId;	
	private String country;
	private String state;
	private String zipCode;
	private Date effFrom;
	private Date effTo;
	private String countryName;
	private String stateName;
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
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
