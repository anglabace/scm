package com.genscript.gsscm.ws.request;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.PackageDTO;

@XmlType(name = "ExtCustomerRequest", namespace = WsConstants.NS)
public class ExtCustomerRequest extends WsRequest {
	private Integer defTerrId;
	private Integer orgId;
	private String countryCode;
	private String stateCode;
	private String zipCode;
	private String defSalesRepId;
	public String getDefSalesRepId() {
		return defSalesRepId;
	}
	public void setDefSalesRepId(String defSalesRepId) {
		this.defSalesRepId = defSalesRepId;
	}
	private String defaultSalesGroup;
	private String salesTerrId;
	private Integer customerId;
	private String orderCurrency;
	private List<OrderPackageDTO> packageList;
	private Double exchRate;
	private Date exchRateDate;
	private String country;
	private String state;
	private Integer orderNo;
	private String quoteCurrency;
	private Integer quoteNo;
	private List<PackageDTO> packageDTOList;
	
	private Integer warehouseId;
	private Integer companyId;
	private List<OrderItemDTO> itemDTOList;
	
	private String currency;
	private String baseCurrency;
	private Double orderAmount;
	
	public Integer getDefTerrId() {
		return defTerrId;
	}
	public void setDefTerrId(Integer defTerrId) {
		this.defTerrId = defTerrId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getDefaultSalesGroup() {
		return defaultSalesGroup;
	}
	public void setDefaultSalesGroup(String defaultSalesGroup) {
		this.defaultSalesGroup = defaultSalesGroup;
	}
	public String getSalesTerrId() {
		return salesTerrId;
	}
	public void setSalesTerrId(String salesTerrId) {
		this.salesTerrId = salesTerrId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public List<OrderPackageDTO> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<OrderPackageDTO> packageList) {
		this.packageList = packageList;
	}
	public Double getExchRate() {
		return exchRate;
	}
	public void setExchRate(Double exchRate) {
		this.exchRate = exchRate;
	}
	public Date getExchRateDate() {
		return exchRateDate;
	}
	public void setExchRateDate(Date exchRateDate) {
		this.exchRateDate = exchRateDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	public List<PackageDTO> getPackageDTOList() {
		return packageDTOList;
	}
	public void setPackageDTOList(List<PackageDTO> packageDTOList) {
		this.packageDTOList = packageDTOList;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public List<OrderItemDTO> getItemDTOList() {
		return itemDTOList;
	}
	public void setItemDTOList(List<OrderItemDTO> itemDTOList) {
		this.itemDTOList = itemDTOList;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	
}
