package com.genscript.gsscm.basedata.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "SearchAttribute", namespace = WsConstants.NS)
public class SearchAttributeDTO {
	private Integer attributeId;
	private String name;
	private String type;
	private List<DropDownDTO> dropDownDTOs;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the attributeId
	 */
	public Integer getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId
	 *            the attributeId to set
	 */
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public List<DropDownDTO> getDropDownDTOs() {
		return dropDownDTOs;
	}

	public void setDropDownDTOs(List<DropDownDTO> dropDownDTOs) {
		this.dropDownDTOs = dropDownDTOs;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
