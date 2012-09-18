package com.genscript.gsscm.serv.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ServiceComponentDTO", namespace = WsConstants.NS)
public class ServiceComponentDTO {
	private static final long serialVersionUID = 679752891076150202L;
	private Integer id;
	private Integer serviceId;
	private String cpntCatalogNo;
	private Double quantity;
	private Integer listSeq;
	private String item;
	private String symbol;
	private Integer leadTime;
    private Double price;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getCpntCatalogNo() {
		return cpntCatalogNo;
	}
	public void setCpntCatalogNo(String cpntCatalogNo) {
		this.cpntCatalogNo = cpntCatalogNo;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Integer getListSeq() {
		return listSeq;
	}
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
