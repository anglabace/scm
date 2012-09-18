package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.entity.ServCategorySearchBean;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceComponent;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;

@XmlType(name="ServRequest",namespace=WsConstants.NS)
public class ServRequest extends WsRequest {
	
	private Integer serviceId;
	private ServiceDTO serviceDTO;
	private Page<Service> pageService;
	private List<SearchProperty> searchPropertyList;
	private ServiceItemBean serviceItemBean;
	private List<String> catalogNoList;
	private Page<ServiceCategory> pageCategory;
	private ServiceCategoryDTO servCategoryDTO;
	private Integer categoryId;
	private List<Integer> delCategoryId;//通过delCategoryId,删除category表中对应category_id的数据;
	private Page<ServiceOfServcategoryBean> pageServiceOfServCategoryBean;
	private Page<ServCategorySearchBean> pageServCategorySearchBean;
	private Integer paramId;
	private List<Integer> categoryIdList;
	private String catalogNo;
	private String intmdKeyword;
	private Integer custNo;
	private String catalogId;
	private List<Integer> delServiceListId;
	private Page<ServiceListBean> pgService;
	private RequestApproveType requestApproveType;
	private Integer objectId;
    private String columnName;
    private ServiceDTO service;
    private Page<ServiceIntermediate> pageIntermediate;
    private Page<ServiceComponent> pageComponent;
    private String name;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Page<ServiceComponent> getPageComponent() {
		return pageComponent;
	}
	public void setPageComponent(Page<ServiceComponent> pageComponent) {
		this.pageComponent = pageComponent;
	}
	public Page<ServiceIntermediate> getPageIntermediate() {
		return pageIntermediate;
	}
	public void setPageIntermediate(Page<ServiceIntermediate> pageIntermediate) {
		this.pageIntermediate = pageIntermediate;
	}
    private Page<ServiceItemBean> pageServiceItemBean;
	public ServiceDTO getService() {
		return service;
	}
	public void setService(ServiceDTO service) {
		this.service = service;
	}
	public RequestApproveType getRequestApproveType() {
		return requestApproveType;
	}
	public void setRequestApproveType(RequestApproveType requestApproveType) {
		this.requestApproveType = requestApproveType;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public List<Integer> getDelServiceListId() {
		return delServiceListId;
	}
	public void setDelServiceListId(List<Integer> delServiceListId) {
		this.delServiceListId = delServiceListId;
	}
	public List<Integer> getCategoryIdList() {
		return categoryIdList;
	}
	public void setCategoryIdList(List<Integer> categoryIdList) {
		this.categoryIdList = categoryIdList;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public Page<ServiceListBean> getPgService() {
		return pgService;
	}
	public void setPgService(Page<ServiceListBean> pgService) {
		this.pgService = pgService;
	}
	public Integer getParamId() {
		return paramId;
	}
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}

	public Page<ServCategorySearchBean> getPageServCategorySearchBean() {
		return pageServCategorySearchBean;
	}
	public void setPageServCategorySearchBean(
			Page<ServCategorySearchBean> pageServCategorySearchBean) {
		this.pageServCategorySearchBean = pageServCategorySearchBean;
	}
	public Page<ServiceOfServcategoryBean> getPageServiceOfServCategoryBean() {
		return pageServiceOfServCategoryBean;
	}
	public void setPageServiceOfServCategoryBean(
			Page<ServiceOfServcategoryBean> pageServiceOfServCategoryBean) {
		this.pageServiceOfServCategoryBean = pageServiceOfServCategoryBean;
	}
	public List<Integer> getDelCategoryId() {
		return delCategoryId;
	}
	public void setDelCategoryId(List<Integer> delCategoryId) {
		this.delCategoryId = delCategoryId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public ServiceCategoryDTO getServCategoryDTO() {
		return servCategoryDTO;
	}
	public void setServCategoryDTO(ServiceCategoryDTO servCategoryDTO) {
		this.servCategoryDTO = servCategoryDTO;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public ServiceDTO getServiceDTO() {
		return serviceDTO;
	}
	public void setServiceDTO(ServiceDTO serviceDTO) {
		this.serviceDTO = serviceDTO;
	}
	public Page<Service> getPageService() {
		return pageService;
	}
	public void setPageService(Page<Service> pageService) {
		this.pageService = pageService;
	}
	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}
	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
	}
	public ServiceItemBean getServiceItemBean() {
		return serviceItemBean;
	}
	public void setServiceItemBean(ServiceItemBean serviceItemBean) {
		this.serviceItemBean = serviceItemBean;
	}
	public List<String> getCatalogNoList() {
		return catalogNoList;
	}
	public void setCatalogNoList(List<String> catalogNoList) {
		this.catalogNoList = catalogNoList;
	}
	public Page<ServiceCategory> getPageCategory() {
		return pageCategory;
	}
	public void setPageCategory(Page<ServiceCategory> pageCategory) {
		this.pageCategory = pageCategory;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getIntmdKeyword() {
		return intmdKeyword;
	}
	public void setIntmdKeyword(String intmdKeyword) {
		this.intmdKeyword = intmdKeyword;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Page<ServiceItemBean> getPageServiceItemBean() {
		return pageServiceItemBean;
	}
	public void setPageServiceItemBean(Page<ServiceItemBean> pageServiceItemBean) {
		this.pageServiceItemBean = pageServiceItemBean;
	}
}
