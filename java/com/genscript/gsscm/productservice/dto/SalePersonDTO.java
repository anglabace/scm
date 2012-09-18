package com.genscript.gsscm.productservice.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "SalePersonDTO", namespace = WsConstants.NS)
public class SalePersonDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2109367861626719462L;
	private String salesName;
	private Integer sellQuality;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the salesName
	 */
	public String getSalesName() {
		return salesName;
	}
	/**
	 * @param salesName the salesName to set
	 */
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	/**
	 * @return the sellQuality
	 */
	public Integer getSellQuality() {
		return sellQuality;
	}
	/**
	 * @param sellQuality the sellQuality to set
	 */
	public void setSellQuality(Integer sellQuality) {
		this.sellQuality = sellQuality;
	}
}
