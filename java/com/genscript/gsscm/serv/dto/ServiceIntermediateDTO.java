package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;

@XmlType(name = "ServiceIntermediateDTO", namespace = WsConstants.NS)
public class ServiceIntermediateDTO  implements Serializable{

	private static final long serialVersionUID = 679752891076150202L;
	private Integer id;
	private Integer serviceId;
	private Integer quantity;
	private String intmdCatalogNo;
	private Integer listSeq;
    private String requiredFlag;
	private String item;
	private String symbol;
	private Integer leadTime;
    private Double price;
    private String intmdKeyword;
    private List<ServiceSubSteps> stepList;
    private List<ServiceSubStepsDTO> stepDtoList;
    
    public ServiceIntermediateDTO(){
    	stepList = new ArrayList<ServiceSubSteps>();
    	stepDtoList = new ArrayList<ServiceSubStepsDTO>();
    }
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getIntmdCatalogNo() {
		return intmdCatalogNo;
	}
	public void setIntmdCatalogNo(String intmdCatalogNo) {
		this.intmdCatalogNo = intmdCatalogNo;
	}
	public Integer getListSeq() {
		return listSeq;
	}
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}
	public String getRequiredFlag() {
		return requiredFlag;
	}
	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
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
	public String getIntmdKeyword() {
		return intmdKeyword;
	}
	public void setIntmdKeyword(String intmdKeyword) {
		this.intmdKeyword = intmdKeyword;
	}
	public List<ServiceSubSteps> getStepList() {
		return stepList;
	}
	public void setStepList(List<ServiceSubSteps> stepList) {
		this.stepList = stepList;
	}
	public List<ServiceSubStepsDTO> getStepDtoList() {
		return stepDtoList;
	}
	public void setStepDtoList(List<ServiceSubStepsDTO> stepDtoList) {
		this.stepDtoList = stepDtoList;
	}
}
