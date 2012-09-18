package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.basedata.dto.LocationDTO;
import com.genscript.gsscm.basedata.dto.MailContentTemplateDTO;
import com.genscript.gsscm.basedata.dto.OptionItemDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.dto.SearchDTO;
import com.genscript.gsscm.basedata.dto.StateDTO;
import com.genscript.gsscm.basedata.dto.ZipCodeDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.entity.ContentTemplate;
import com.genscript.gsscm.customer.dto.DeptDTO;
import com.genscript.gsscm.customer.dto.DivisionDTO;
import com.genscript.gsscm.customer.dto.OrganizationDTO;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.order.entity.MessageTemplate;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.quoteorder.dto.ChangeCurrencyDTO;
import com.genscript.gsscm.quoteorder.dto.SearchORFCloneGeneDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;

@XmlType(name = "PublicResponse", namespace = WsConstants.NS)
public class PublicResponse  extends WsResponse {

	private List<SearchAttributeDTO> srchAttrList;
	private List<SearchDTO> mySearchList;
	private List<OptionItemDTO> itemsDTO;
	private Integer mySrchMaxCount;
	private List<DropDownListDTO> dropDownListDTOs;
	private List<CountryDTO> countryList;
	private List<StateDTO> stateList;	
	private List<Source> sources;
	private List<OrganizationDTO> orgList;
	private List<DivisionDTO> divisionList;
	private List<DeptDTO> deptList;	
	List<GetInfoByRegionDTO> getInfoByRegionDTOs;
	private List<ContentTemplate> contentTmplList;
	private LocationDTO location;
	List<AllCountryDTO> allCountryDTOs;
	private PageDTO pagerDTO;	
	private List<MessageTemplate> messageTmplList;
	private List<ZipCodeDTO> zipCodeList;
	private List<StatusList> statusLists;
	private List<DropDownDTO> promotionDTOList;
	private Integer promotionExistFlag;
	List<MailContentTemplateDTO> mailContentTemplateDTOList;
	private List<ChangeCurrencyDTO> changeCurrencyDTOList;
	private List<SearchORFCloneGeneDTO> searchORFCloneDTOList;
	
	private ServiceItemPiceDTO servicePriceDTO;
	public List<MailContentTemplateDTO> getMailContentTemplateDTOList() {
		return mailContentTemplateDTOList;
	}
	public void setMailContentTemplateDTOList(
			List<MailContentTemplateDTO> mailContentTemplateDTOList) {
		this.mailContentTemplateDTOList = mailContentTemplateDTOList;
	}
	public List<AllCountryDTO> getAllCountryDTOs() {
		return allCountryDTOs;
	}
	public void setAllCountryDTOs(List<AllCountryDTO> allCountryDTOs) {
		this.allCountryDTOs = allCountryDTOs;
	}
	public List<GetInfoByRegionDTO> getGetInfoByRegionDTOs() {
		return getInfoByRegionDTOs;
	}
	public void setGetInfoByRegionDTOs(List<GetInfoByRegionDTO> getInfoByRegionDTOs) {
		this.getInfoByRegionDTOs = getInfoByRegionDTOs;
	}
	public List<Source> getSources() {
		return sources;
	}
	public void setSources(List<Source> sources) {
		this.sources = sources;
	}
	public List<DropDownListDTO> getDropDownListDTOs() {
		return dropDownListDTOs;
	}
	public void setDropDownListDTOs(List<DropDownListDTO> dropDownListDTOs) {
		this.dropDownListDTOs = dropDownListDTOs;
	}
	/**
	 * @return the srchAttrList
	 */

	public List<SearchAttributeDTO> getSrchAttrList() {
		return srchAttrList;
	}
	/**
	 * @param srchAttrList the srchAttrList to set
	 */
	public void setSrchAttrList(List<SearchAttributeDTO> srchAttrList) {
		this.srchAttrList = srchAttrList;
	}
	/**
	 * @return the mySearchList
	 */
	public List<SearchDTO> getMySearchList() {
		return mySearchList;
	}
	/**
	 * @param mySearchList the mySearchList to set
	 */
	public void setMySearchList(List<SearchDTO> mySearchList) {
		this.mySearchList = mySearchList;
	}

	/**
	 * @return the itemsDTO
	 */
	public List<OptionItemDTO> getItemsDTO() {
		return itemsDTO;
	}

	/**
	 * @param itemsDTO the itemsDTO to set
	 */
	public void setItemsDTO(List<OptionItemDTO> itemsDTO) {
		this.itemsDTO = itemsDTO;
	}
	/**
	 * @return the mySrchMaxCount
	 */
	public Integer getMySrchMaxCount() {
		return mySrchMaxCount;
	}
	/**
	 * @param mySrchMaxCount the mySrchMaxCount to set
	 */
	public void setMySrchMaxCount(Integer mySrchMaxCount) {
		this.mySrchMaxCount = mySrchMaxCount;
	}
	/**
	 * @return the countryList
	 */
	public List<CountryDTO> getCountryList() {
		return countryList;
	}
	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(List<CountryDTO> countryList) {
		this.countryList = countryList;
	}
	/**
	 * @return the stateList
	 */
	public List<StateDTO> getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List<StateDTO> stateList) {
		this.stateList = stateList;
	}
	/**
	 * @return the contentTmplList
	 */
	public List<ContentTemplate> getContentTmplList() {
		return contentTmplList;
	}
	/**
	 * @param contentTmplList the contentTmplList to set
	 */
	public void setContentTmplList(List<ContentTemplate> contentTmplList) {
		this.contentTmplList = contentTmplList;
	}
	/**
	 * @return the location
	 */
	public LocationDTO getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	/**
	 * @return the pagerDTO
	 */
	public PageDTO getPagerDTO() {
		return pagerDTO;
	}
	/**
	 * @param pagerDTO the pagerDTO to set
	 */
	public void setPagerDTO(PageDTO pagerDTO) {
		this.pagerDTO = pagerDTO;
	}
	/**
	 * @return the orgList
	 */
	public List<OrganizationDTO> getOrgList() {
		return orgList;
	}
	/**
	 * @param orgList the orgList to set
	 */
	public void setOrgList(List<OrganizationDTO> orgList) {
		this.orgList = orgList;
	}
	/**
	 * @return the divisionList
	 */
	public List<DivisionDTO> getDivisionList() {
		return divisionList;
	}
	/**
	 * @param divisionList the divisionList to set
	 */
	public void setDivisionList(List<DivisionDTO> divisionList) {
		this.divisionList = divisionList;
	}
	/**
	 * @return the deptList
	 */
	public List<DeptDTO> getDeptList() {
		return deptList;
	}
	/**
	 * @param deptList the deptList to set
	 */
	public void setDeptList(List<DeptDTO> deptList) {
		this.deptList = deptList;
	}
	/**
	 * @return the messageTmplList
	 */
	public List<MessageTemplate> getMessageTmplList() {
		return messageTmplList;
	}
	/**
	 * @param messageTmplList the messageTmplList to set
	 */
	public void setMessageTmplList(List<MessageTemplate> messageTmplList) {
		this.messageTmplList = messageTmplList;
	}
	/**
	 * @return the zipCodeList
	 */
	public List<ZipCodeDTO> getZipCodeList() {
		return zipCodeList;
	}
	/**
	 * @param zipCodeList the zipCodeList to set
	 */
	public void setZipCodeList(List<ZipCodeDTO> zipCodeList) {
		this.zipCodeList = zipCodeList;
	}
	public List<StatusList> getStatusLists() {
		return statusLists;
	}
	public void setStatusLists(List<StatusList> statusLists) {
		this.statusLists = statusLists;
	}
	public List<DropDownDTO> getPromotionDTOList() {
		return promotionDTOList;
	}
	public void setPromotionDTOList(List<DropDownDTO> promotionDTOList) {
		this.promotionDTOList = promotionDTOList;
	}
	public Integer getPromotionExistFlag() {
		return promotionExistFlag;
	}
	public void setPromotionExistFlag(Integer promotionExistFlag) {
		this.promotionExistFlag = promotionExistFlag;
	}
	public List<ChangeCurrencyDTO> getChangeCurrencyDTOList() {
		return changeCurrencyDTOList;
	}
	public void setChangeCurrencyDTOList(
			List<ChangeCurrencyDTO> changeCurrencyDTOList) {
		this.changeCurrencyDTOList = changeCurrencyDTOList;
	}
	public List<SearchORFCloneGeneDTO> getSearchORFCloneDTOList() {
		return searchORFCloneDTOList;
	}
	public void setSearchORFCloneDTOList(
			List<SearchORFCloneGeneDTO> searchORFCloneDTOList) {
		this.searchORFCloneDTOList = searchORFCloneDTOList;
	}
	public ServiceItemPiceDTO getServicePriceDTO() {
		return servicePriceDTO;
	}
	public void setServicePriceDTO(ServiceItemPiceDTO servicePriceDTO) {
		this.servicePriceDTO = servicePriceDTO;
	}
}
