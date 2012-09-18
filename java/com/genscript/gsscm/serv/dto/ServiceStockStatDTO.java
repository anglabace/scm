package com.genscript.gsscm.serv.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "ServiceStockStatDTO", namespace = WsConstants.NS)
public class ServiceStockStatDTO {
	private Integer stockTotal;
	private Integer commitedTotal;
	private Integer unProcessedTotal;
	private Integer backOrderTotal;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the stockTotal
	 */
	public Integer getStockTotal() {
		return stockTotal;
	}
	/**
	 * @param stockTotal the stockTotal to set
	 */
	public void setStockTotal(Integer stockTotal) {
		this.stockTotal = stockTotal;
	}
	/**
	 * @return the commitedTotal
	 */
	public Integer getCommitedTotal() {
		return commitedTotal;
	}
	/**
	 * @param commitedTotal the commitedTotal to set
	 */
	public void setCommitedTotal(Integer commitedTotal) {
		this.commitedTotal = commitedTotal;
	}
	/**
	 * @return the unProcessedTotal
	 */
	public Integer getUnProcessedTotal() {
		return unProcessedTotal;
	}
	/**
	 * @param unProcessedTotal the unProcessedTotal to set
	 */
	public void setUnProcessedTotal(Integer unProcessedTotal) {
		this.unProcessedTotal = unProcessedTotal;
	}
	/**
	 * @return the backOrderTotal
	 */
	public Integer getBackOrderTotal() {
		return backOrderTotal;
	}
	/**
	 * @param backOrderTotal the backOrderTotal to set
	 */
	public void setBackOrderTotal(Integer backOrderTotal) {
		this.backOrderTotal = backOrderTotal;
	}
}
