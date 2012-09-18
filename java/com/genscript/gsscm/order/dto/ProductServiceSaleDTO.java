package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ProductSaleDTO", namespace = WsConstants.NS)
public class ProductServiceSaleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2857346978811102475L;

	private Long grossUitsSales;//总销售量;
	private Long unitsReturned;//退贷量；
	private Double totalSales;//总销售额；
	private Double totalProfit;//总利润；
	private String margin;//利润率;
	private String picName;//统计图片名称；
	
	public Long getGrossUitsSales() {
		return grossUitsSales;
	}
	public void setGrossUitsSales(Long grossUitsSales) {
		this.grossUitsSales = grossUitsSales;
	}
	public Long getUnitsReturned() {
		return unitsReturned;
	}
	public void setUnitsReturned(Long unitsReturned) {
		this.unitsReturned = unitsReturned;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	public Double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	
}
