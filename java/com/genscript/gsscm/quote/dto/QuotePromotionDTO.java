package com.genscript.gsscm.quote.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "QuotePromotionDTO", namespace = WsConstants.NS)
public class QuotePromotionDTO {
	private Integer id;
	private Integer orderNo;
	private Integer prmtId;
	private String prmtCode;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the prmtId
	 */
	public Integer getPrmtId() {
		return prmtId;
	}
	/**
	 * @param prmtId the prmtId to set
	 */
	public void setPrmtId(Integer prmtId) {
		this.prmtId = prmtId;
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
