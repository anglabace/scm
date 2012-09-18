package com.genscript.gsscm.basedata.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OptionItemDTO", namespace = WsConstants.NS)
public class OptionItemDTO {
	private String name;
	private List<PbDropdownListOptionsDTO> options;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the options
	 */
	public List<PbDropdownListOptionsDTO> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<PbDropdownListOptionsDTO> options) {
		this.options = options;
	}



}
