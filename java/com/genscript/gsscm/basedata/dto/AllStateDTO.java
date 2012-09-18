package com.genscript.gsscm.basedata.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
@XmlType(name = "AllStateDTO", namespace = WsConstants.NS)
public class AllStateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7967530110728177162L;
	private String stateCode;
	private String name;
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
