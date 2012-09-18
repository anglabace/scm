package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "OrderPromotionDTO", namespace = WsConstants.NS)
public class OrderPromotionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4731298646569546318L;
	private String prmtCode;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the prmtCode
	 */
	public String getPrmtCode() {
		return prmtCode;
	}
	/**
	 * @param prmtCode the prmtCode to set
	 */
	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}
}
