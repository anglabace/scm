package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;


@XmlType(name = "SearchProperty", namespace = WsConstants.NS)
public class SearchProperty {
	private SearchOperator searchOperator;
	private SearchPropertyType searchPropertyType;
	private String propertyName;
	private String propertyValue1;
	private String propertyValue2;

	/**
	 * @return the searchOperator
	 */
	public SearchOperator getSearchOperator() {
		return searchOperator;
	}

	/**
	 * @param searchOperator
	 *            the searchOperator to set
	 */
	public void setSearchOperator(SearchOperator searchOperator) {
		this.searchOperator = searchOperator;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	

	public String getPropertyValue1() {
		return propertyValue1;
	}

	public String getPropertyValue2() {
		return propertyValue2;
	}

	public void setPropertyValue2(String propertyValue2) {
		this.propertyValue2 = propertyValue2;
	}

	public void setPropertyValue1(String propertyValue1) {
		this.propertyValue1 = propertyValue1;
	}

	public SearchPropertyType getSearchPropertyType() {
		return searchPropertyType;
	}

	public void setSearchPropertyType(SearchPropertyType searchPropertyType) {
		this.searchPropertyType = searchPropertyType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
