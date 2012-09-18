package com.genscript.gsscm.basedata.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "AllCountryDTO", namespace = WsConstants.NS)
public class AllCountryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8495600766099022173L;
	private String countryCode;
	private String name;
	
	private List<AllStateDTO> allStateDTOs;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(required=true)
	public List<AllStateDTO> getAllStateDTOs() {
		return allStateDTOs;
	}

	public void setAllStateDTOs(List<AllStateDTO> allStateDTOs) {
		this.allStateDTOs = allStateDTOs;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
