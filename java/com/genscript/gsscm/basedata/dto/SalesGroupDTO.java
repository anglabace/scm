package com.genscript.gsscm.basedata.dto;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;


public class SalesGroupDTO{
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
	
	public List<SalesContactDTO> getSalesContactDTOs() {
		return salesContactDTOs;
	}
	public void setSalesContactDTOs(List<SalesContactDTO> salesContactDTOs) {
		this.salesContactDTOs = salesContactDTOs;
	}
	public List<TechSupportDTO> getTechSupportDTOs() {
		return techSupportDTOs;
	}
	public void setTechSupportDTOs(List<TechSupportDTO> techSupportDTOs) {
		this.techSupportDTOs = techSupportDTOs;
	}
	public List<TechSupportDTO> getProjectSupportDTOs() {
		return projectSupportDTOs;
	}
	public void setProjectSupportDTOs(List<TechSupportDTO> projectSupportDTOs) {
		this.projectSupportDTOs = projectSupportDTOs;
	}

	public SalesGroupDTO(){}
	public SalesGroupDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private String id;
	private String name;
	private List<SalesContactDTO> salesContactDTOs;
	private List<TechSupportDTO> techSupportDTOs;
	private List<TechSupportDTO> projectSupportDTOs;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}