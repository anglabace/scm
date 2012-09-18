package com.genscript.gsscm.product.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "PdtSalesTotal", namespace = WsConstants.NS)
public class PdtSalesTotal {
	private Double unitSold;
	private Double unitReturned;
	private Double totalSales;
	private String totalSalesCurrency;
	private Double totalProfit;
	private String totalProfitCurrency;
	private Double profitMargin;
	private List<PdtSalesReport> salesReportList;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the unitSold
	 */
	public Double getUnitSold() {
		return unitSold;
	}
	/**
	 * @param unitSold the unitSold to set
	 */
	public void setUnitSold(Double unitSold) {
		this.unitSold = unitSold;
	}
	/**
	 * @return the unitReturned
	 */
	public Double getUnitReturned() {
		return unitReturned;
	}
	/**
	 * @param unitReturned the unitReturned to set
	 */
	public void setUnitReturned(Double unitReturned) {
		this.unitReturned = unitReturned;
	}
	/**
	 * @return the totalSales
	 */
	public Double getTotalSales() {
		return totalSales;
	}
	/**
	 * @param totalSales the totalSales to set
	 */
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	/**
	 * @return the totalSalesCurrency
	 */
	public String getTotalSalesCurrency() {
		return totalSalesCurrency;
	}
	/**
	 * @param totalSalesCurrency the totalSalesCurrency to set
	 */
	public void setTotalSalesCurrency(String totalSalesCurrency) {
		this.totalSalesCurrency = totalSalesCurrency;
	}
	/**
	 * @return the totalProfit
	 */
	public Double getTotalProfit() {
		return totalProfit;
	}
	/**
	 * @param totalProfit the totalProfit to set
	 */
	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
	/**
	 * @return the totalProfitCurrency
	 */
	public String getTotalProfitCurrency() {
		return totalProfitCurrency;
	}
	/**
	 * @param totalProfitCurrency the totalProfitCurrency to set
	 */
	public void setTotalProfitCurrency(String totalProfitCurrency) {
		this.totalProfitCurrency = totalProfitCurrency;
	}
	/**
	 * @return the profitMargin
	 */
	public Double getProfitMargin() {
		return profitMargin;
	}
	/**
	 * @param profitMargin the profitMargin to set
	 */
	public void setProfitMargin(Double profitMargin) {
		this.profitMargin = profitMargin;
	}
	/**
	 * @return the salesReportList
	 */
	public List<PdtSalesReport> getSalesReportList() {
		return salesReportList;
	}
	/**
	 * @param salesReportList the salesReportList to set
	 */
	public void setSalesReportList(List<PdtSalesReport> salesReportList) {
		this.salesReportList = salesReportList;
	}
}
