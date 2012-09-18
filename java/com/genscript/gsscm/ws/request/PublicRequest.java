package com.genscript.gsscm.ws.request;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.basedata.entity.PbSearch;
import com.genscript.gsscm.basedata.entity.PbSearchCondition;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.constant.ServicePriceType;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.quoteorder.dto.ChangeCurrencyDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;


@XmlType(name = "PublicRequest", namespace = WsConstants.NS)
public class PublicRequest extends WsRequest {
	private String optionsNames;
	private PageDTO pagerParm;
	private PbSearchCondition[] srchCondition;
	private Integer paramId;
	private String countryCode;
	private PbSearch mySearch;
	private SearchType searchType;
	private String searchValue;
	private Integer custNo;
	private Integer divisionId;
	private Integer orgId;
	private String addrType;
	private TemplateType templateType;
	private List<String> countryCodeList;
	private List<String> stateCodeList;	
	private List<SpecDropDownListName> specDropDownListNames;
	private String statusType;
	private String statusCode;
	private Integer sourceId;
	private String regionLevel;
	private Date currentDate;
	private String promotionCode;
	private String orderStatus;
	private String searchORFCloneType;
	private String searchORFCloneValue;
	private List<ChangeCurrencyDTO> changeCurrencyDTOList;
	//calculate service 
    private Integer serviceId;
    private String catalogId;
    private String sequence;
    private ServiceItemPiceDTO serviceItemPiceDTO;
    private ServicePriceType servicePriceType;
    
	public String getAddrType() {
		return addrType;
	}
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	public String getRegionLevel() {
		return regionLevel;
	}
	public void setRegionLevel(String regionLevel) {
		this.regionLevel = regionLevel;
	}
	public List<SpecDropDownListName> getSpecDropDownListNames() {
		return specDropDownListNames;
	}
	public void setSpecDropDownListNames(
			List<SpecDropDownListName> specDropDownListNames) {
		this.specDropDownListNames = specDropDownListNames;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	/**
	 * @return the optionsNames
	 */
	public String getOptionsNames() {
		return optionsNames;
	}
	/**
	 * @param optionsNames the optionsNames to set
	 */
	public void setOptionsNames(String optionsNames) {
		this.optionsNames = optionsNames;
	}
	/**
	 * @return the pagerParm
	 */
	public PageDTO getPagerParm() {
		return pagerParm;
	}
	/**
	 * @param pagerParm the pagerParm to set
	 */
	public void setPagerParm(PageDTO pagerParm) {
		this.pagerParm = pagerParm;
	}

	/**
	 * @return the srchCondition
	 */
	public PbSearchCondition[] getSrchCondition() {
		return srchCondition;
	}
	/**
	 * @param srchCondition the srchCondition to set
	 */
	public void setSrchCondition(PbSearchCondition[] srchCondition) {
		this.srchCondition = srchCondition;
	}
	/**
	 * @return the paramId
	 */
	public Integer getParamId() {
		return paramId;
	}
	/**
	 * @param paramId the paramId to set
	 */
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	/**
	 * @return the mySearch
	 */
	public PbSearch getMySearch() {
		return mySearch;
	}
	/**
	 * @param mySearch the mySearch to set
	 */
	public void setMySearch(PbSearch mySearch) {
		this.mySearch = mySearch;
	}
	/**
	 * @return the searchType
	 */
	public SearchType getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	/**
	 * @return the divisionId
	 */
	public Integer getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId the divisionId to set
	 */
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public TemplateType getTemplateType() {
		return templateType;
	}
	public void setTemplateType(TemplateType templateType) {
		this.templateType = templateType;
	}
	/**
	 * @return the countryCodeList
	 */
	public List<String> getCountryCodeList() {
		return countryCodeList;
	}
	/**
	 * @param countryCodeList the countryCodeList to set
	 */
	public void setCountryCodeList(List<String> countryCodeList) {
		this.countryCodeList = countryCodeList;
	}
	/**
	 * @return the stateCodeList
	 */
	public List<String> getStateCodeList() {
		return stateCodeList;
	}
	/**
	 * @param stateCodeList the stateCodeList to set
	 */
	public void setStateCodeList(List<String> stateCodeList) {
		this.stateCodeList = stateCodeList;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<ChangeCurrencyDTO> getChangeCurrencyDTOList() {
		return changeCurrencyDTOList;
	}
	public void setChangeCurrencyDTOList(
			List<ChangeCurrencyDTO> changeCurrencyDTOList) {
		this.changeCurrencyDTOList = changeCurrencyDTOList;
	}
	public String getSearchORFCloneType() {
		return searchORFCloneType;
	}
	public void setSearchORFCloneType(String searchORFCloneType) {
		this.searchORFCloneType = searchORFCloneType;
	}
	public String getSearchORFCloneValue() {
		return searchORFCloneValue;
	}
	public void setSearchORFCloneValue(String searchORFCloneValue) {
		this.searchORFCloneValue = searchORFCloneValue;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public ServiceItemPiceDTO getServiceItemPiceDTO() {
		return serviceItemPiceDTO;
	}
	public void setServiceItemPiceDTO(ServiceItemPiceDTO serviceItemPiceDTO) {
		this.serviceItemPiceDTO = serviceItemPiceDTO;
	}
	public ServicePriceType getServicePriceType() {
		return servicePriceType;
	}
	public void setServicePriceType(ServicePriceType servicePriceType) {
		this.servicePriceType = servicePriceType;
	}
	
}
