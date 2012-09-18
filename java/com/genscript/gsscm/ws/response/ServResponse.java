package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.purchase.dto.VendorProductDTO;
import com.genscript.gsscm.purchase.dto.VendorServiceDTO;
import com.genscript.gsscm.quoteorder.dto.ItemBeanDTO;
import com.genscript.gsscm.serv.dto.RoyaltyServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceComponentDTO;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceIntermediateDTO;
import com.genscript.gsscm.serv.dto.ServiceRelationDTO;
import com.genscript.gsscm.serv.dto.ServiceRestrictShipDTO;
import com.genscript.gsscm.serv.dto.ServiceStockStatDTO;
import com.genscript.gsscm.serv.entity.ServCategorySearchBean;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;

@XmlType(name="ServResponse", namespace=WsConstants.NS)
public class ServResponse extends WsResponse {
	
	private ServiceDTO serviceDTO;
	private PageDTO page;
	private List<Service> serviceList;
	private ServiceRelationDTO serviceRelationDTO;
	private List<SearchItemDTO> searchItemDTOList;
	private List<ServiceCategory> categoryList;
	private Integer categoryId;
	private ServiceCategoryDTO servCategoryDTO;
	private List<ServiceOfServcategoryBean> serviceOfServCategoryList;
	private List<ServCategorySearchBean> servCategorySearchBeanList;
	private String intmdCatalogNo;
	private String catalogId;
	private String priceLimit;
	private List<ServiceListBean> serviceListBean;
	private List<Integer> unDeleteIdList;
	private List<DropDownDTO> dropDownDTOList;
	private Boolean propertyApprovedStatus;
	private Integer serviceId;
	private List<ServiceRestrictShipDTO> RestrictShipList;
	private ServiceStockStatDTO serviceStockStatDTO;
	private List<ServiceIntermediateDTO> intermediateList;
	private List<ServiceComponentDTO> componentList;
	private List<ItemBeanDTO> itemBeanDTOList;
	private List<com.genscript.gsscm.serv.entity.Service> stdPriceList;
	private RoyaltyServiceDTO royaltyService;
	private List<VendorServiceDTO> verdonServiceList;
	
	public RoyaltyServiceDTO getRoyaltyService() {
		return royaltyService;
	}
	public void setRoyaltyService(RoyaltyServiceDTO royaltyService) {
		this.royaltyService = royaltyService;
	}
	public List<com.genscript.gsscm.serv.entity.Service> getStdPriceList() {
		return stdPriceList;
	}
	public void setStdPriceList(
			List<com.genscript.gsscm.serv.entity.Service> stdPriceList) {
		this.stdPriceList = stdPriceList;
	}
	public List<ServiceComponentDTO> getComponentList() {
		return componentList;
	}
	public void setComponentList(List<ServiceComponentDTO> componentList) {
		this.componentList = componentList;
	}
	public List<ServiceIntermediateDTO> getIntermediateList() {
		return intermediateList;
	}
	public void setIntermediateList(List<ServiceIntermediateDTO> intermediateList) {
		this.intermediateList = intermediateList;
	}
	public ServiceStockStatDTO getServiceStockStatDTO() {
		return serviceStockStatDTO;
	}
	public void setServiceStockStatDTO(ServiceStockStatDTO serviceStockStatDTO) {
		this.serviceStockStatDTO = serviceStockStatDTO;
	}
	
	public List<ServiceRestrictShipDTO> getRestrictShipList() {
		return RestrictShipList;
	}
	public void setRestrictShipList(List<ServiceRestrictShipDTO> restrictShipList) {
		RestrictShipList = restrictShipList;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public List<DropDownDTO> getDropDownDTOList() {
		return dropDownDTOList;
	}
	public void setDropDownDTOList(List<DropDownDTO> dropDownDTOList) {
		this.dropDownDTOList = dropDownDTOList;
	}
	public Boolean getPropertyApprovedStatus() {
		return propertyApprovedStatus;
	}
	public void setPropertyApprovedStatus(Boolean propertyApprovedStatus) {
		this.propertyApprovedStatus = propertyApprovedStatus;
	}
	public List<ServiceListBean> getServiceListBean() {
		return serviceListBean;
	}
	public void setServiceListBean(List<ServiceListBean> serviceListBean) {
		this.serviceListBean = serviceListBean;
	}
	public String getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(String priceLimit) {
		this.priceLimit = priceLimit;
	}
	public List<Integer> getUnDeleteIdList() {
		return unDeleteIdList;
	}
	public void setUnDeleteIdList(List<Integer> unDeleteIdList) {
		this.unDeleteIdList = unDeleteIdList;
	}
	public List<ServCategorySearchBean> getServCategorySearchBeanList() {
		return servCategorySearchBeanList;
	}
	public void setServCategorySearchBeanList(
			List<ServCategorySearchBean> servCategorySearchBeanList) {
		this.servCategorySearchBeanList = servCategorySearchBeanList;
	}
	public List<ServiceOfServcategoryBean> getServiceOfServCategoryList() {
		return serviceOfServCategoryList;
	}
	public void setServiceOfServCategoryList(
			List<ServiceOfServcategoryBean> serviceOfServCategoryList) {
		this.serviceOfServCategoryList = serviceOfServCategoryList;
	}
	public ServiceCategoryDTO getServCategoryDTO() {
		return servCategoryDTO;
	}
	public void setServCategoryDTO(ServiceCategoryDTO servCategoryDTO) {
		this.servCategoryDTO = servCategoryDTO;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public ServiceDTO getServiceDTO() {
		return serviceDTO;
	}
	public void setServiceDTO(ServiceDTO serviceDTO) {
		this.serviceDTO = serviceDTO;
	}
	public PageDTO getPage() {
		return page;
	}
	public void setPage(PageDTO page) {
		this.page = page;
	}
	public List<Service> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}
	public ServiceRelationDTO getServiceRelationDTO() {
		return serviceRelationDTO;
	}
	public void setServiceRelationDTO(ServiceRelationDTO serviceRelationDTO) {
		this.serviceRelationDTO = serviceRelationDTO;
	}
	public List<SearchItemDTO> getSearchItemDTOList() {
		return searchItemDTOList;
	}
	public void setSearchItemDTOList(List<SearchItemDTO> searchItemDTOList) {
		this.searchItemDTOList = searchItemDTOList;
	}
	public List<ServiceCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<ServiceCategory> categoryList) {
		this.categoryList = categoryList;
	}
	public String getIntmdCatalogNo() {
		return intmdCatalogNo;
	}
	public void setIntmdCatalogNo(String intmdCatalogNo) {
		this.intmdCatalogNo = intmdCatalogNo;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public List<ItemBeanDTO> getItemBeanDTOList() {
		return itemBeanDTOList;
	}
	public void setItemBeanDTOList(List<ItemBeanDTO> itemBeanDTOList) {
		this.itemBeanDTOList = itemBeanDTOList;
	}
	public List<VendorServiceDTO> getVerdonServiceList() {
		return verdonServiceList;
	}
	public void setVerdonServiceList(List<VendorServiceDTO> verdonServiceList) {
		this.verdonServiceList = verdonServiceList;
	}
}
