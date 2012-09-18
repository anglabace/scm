package com.genscript.gsscm.basedata.dto;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;


public class GetInfoByRegionDTO{
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTerritoryClassification() {
		return territoryClassification;
	}
	public void setTerritoryClassification(String territoryClassification) {
		this.territoryClassification = territoryClassification;
	}
	public List<SalesGroupDTO> getSalesGroupDTOs() {
		return salesGroupDTOs;
	}
	public void setSalesGroupDTOs(List<SalesGroupDTO> salesGroupDTOs) {
		this.salesGroupDTOs = salesGroupDTOs;
	}

	private String id;
	private String name;
	private String territoryClassification;
	private List<SalesGroupDTO> salesGroupDTOs;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

