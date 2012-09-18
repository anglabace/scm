package com.genscript.gsscm.basedata.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "SearchDTO", namespace = WsConstants.NS)
public class SearchDTO {
	private Integer searchId;
	private String name;
	private List<SearchConditionDTO> attrList;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the searchId
	 */
	public Integer getSearchId() {
		return searchId;
	}
	/**
	 * @param searchId the searchId to set
	 */
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
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
	 * @return the attrList
	 */
	public List<SearchConditionDTO> getAttrList() {
		return attrList;
	}
	/**
	 * @param attrList the attrList to set
	 */
	public void setAttrList(List<SearchConditionDTO> attrList) {
		this.attrList = attrList;
	}


}
