package com.genscript.gsscm.purchase.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "VendorDTO", namespace = WsConstants.NS)
public class VendorDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2745748022337053421L;
	private Integer vendorNo;
	private String vendorName;
	private String description;
	private String status;
	public Integer getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
