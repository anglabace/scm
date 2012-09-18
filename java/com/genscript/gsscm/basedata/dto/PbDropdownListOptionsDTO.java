package com.genscript.gsscm.basedata.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "PbDropdownListOptionsDTO", namespace = WsConstants.NS)
public class PbDropdownListOptionsDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1897323659586944385L;
	private Integer optionId;
	private Integer listId;
	private String value;
	private String text;
//	private String description;
	
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
