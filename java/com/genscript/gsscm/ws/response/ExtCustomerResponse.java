package com.genscript.gsscm.ws.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.dto.CustomerDTO;

@XmlType(name = "ExtCustomerResponse", namespace = WsConstants.NS)
public class ExtCustomerResponse extends WsResponse {
	private List<GetInfoByRegionDTO> territoryInfoList;
	private CustomerDTO customerDTO;
	private Double cost; 
	private BigDecimal specialDiscount;
	private Double taxRate;
	private Map<String, DropDownListDTO> salesInfo;
	
	public List<GetInfoByRegionDTO> getTerritoryInfoList() {
		return territoryInfoList;
	}

	public void setTerritoryInfoList(List<GetInfoByRegionDTO> territoryInfoList) {
		this.territoryInfoList = territoryInfoList;
	}

	public Map<String, DropDownListDTO> getSalesInfo() {
		return salesInfo;
	}

	public void setSalesInfo(Map<String, DropDownListDTO> salesInfo) {
		this.salesInfo = salesInfo;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
}
