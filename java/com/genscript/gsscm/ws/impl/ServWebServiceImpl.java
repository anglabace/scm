package com.genscript.gsscm.ws.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
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
import com.genscript.gsscm.serv.entity.ServiceComponent;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.ServWebService;
import com.genscript.gsscm.ws.request.ServRequest;
import com.genscript.gsscm.ws.response.ServResponse;

@WebService(serviceName = "ServService", portName = "ServServicePort", endpointInterface = "com.genscript.gsscm.ws.api.ServWebService", targetNamespace = WsConstants.NS)
public class ServWebServiceImpl implements ServWebService {

	@Autowired
	private ServService servService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private ExceptionService exceptionUtil;
	
	@Override
	public ServResponse searchServList(ServRequest servRequest) {
		Integer userId = servRequest.getUserId();
		Page<Service> page = null;
		ServResponse servResponse = new ServResponse();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<Service> pageService = servRequest.getPageService();
		if (!pageService.isOrderBySetted()) {
			pageService.setOrderBy("serviceId");
			pageService.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = servRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = servService.searchServList(pageService);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = servService.searchServList(pageService, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<Service> serviceList = page.getResult();
			servResponse.setPage(pageDTO);
			servResponse.setServiceList(serviceList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServiceRelationList(ServRequest servRequest) {
		Integer userId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		ServiceItemBean serviceItemBean = servRequest.getServiceItemBean();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert
					.notNull(serviceItemBean,
							"serviceItemBean can't be null");
			ServiceRelationDTO dto = new ServiceRelationDTO();
			dto = servService.getServiceRelations(serviceItemBean);

			servResponse.setServiceRelationDTO(dto);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getSearchItemInfo(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		List<String> catalogNoList = servRequest.getCatalogNoList();
		Integer custNo = servRequest.getCustNo();
		try {
			List<SearchItemDTO> searchItemDTOList = servService.getSearchItemInfo(catalogNoList,custNo);
			servResponse.setSearchItemDTOList(searchItemDTOList);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse searchCategoryList(ServRequest servRequest) {
		Integer userId;
		Page<ServiceCategory> pageCategory;
		Page<ServiceCategory> page = null;
		ServResponse servResponse = new ServResponse();
		userId = servRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageCategory = servRequest.getPageCategory();
		if (!pageCategory.isOrderBySetted()) {
			pageCategory.setOrderBy("categoryId");
			pageCategory.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = servRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = servService.searchCategoryList(pageCategory);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType;
					propType = searchProperty.getSearchPropertyType().name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = servService.searchCategoryList(pageCategory,
						filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ServiceCategory> categoryList = page.getResult();
			servResponse.setPage(pageDTO);
			servResponse.setCategoryList(categoryList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServiceDetail(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			ServiceDTO dto = this.servService.getServDetail(servRequest.getParamId());
			servResponse.setServiceDTO(dto);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse saveServCategory(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try{
			ServiceCategoryDTO categoryDTO = servRequest.getServCategoryDTO();
			ServiceCategory category = this.servService.saveServCategory(categoryDTO, loginUserId);
			servResponse.setCategoryId(category.getCategoryId());
		}catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		
 		return servResponse;
	}

	@Override
	public ServResponse getServCategoryDetail(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			ServiceCategoryDTO dto = this.servService.getServCategoryDetail(servRequest.getParamId());
			servResponse.setServCategoryDTO(dto);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse delServCategory(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try{
			List<Integer> delCategoryId = servRequest.getDelCategoryId();
		    this.servService.delServCategory(delCategoryId);
		    servResponse.setHasException(Boolean.FALSE);
		} catch(Exception e){
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServiceOfServCategoryList(ServRequest servRequest) {
		Integer userId = servRequest.getUserId();
		Page<ServiceOfServcategoryBean> page = null;
		ServResponse servResponse = new ServResponse();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<ServiceOfServcategoryBean> pageService = servRequest.getPageServiceOfServCategoryBean();
		if (!pageService.isOrderBySetted()) {
			pageService.setOrderBy("serviceId");
			pageService.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchSercList = servRequest
					.getSearchPropertyList();
			if (srchSercList == null) {
				page = servService.getServOfServCategoryList(pageService);
			} else {
				for (SearchProperty searchSercerty : srchSercList) {
					String sercName = searchSercerty.getPropertyName();
					String srchOperator = searchSercerty.getSearchOperator()
							.name();
					String sercType = searchSercerty.getSearchPropertyType()
							.name();
					String sercValue1 = searchSercerty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(sercType).append("_")
							.append(sercName);
					filterMap.put(srchStr.toString(), sercValue1);
				}
				page = servService.getServOfServCategoryList(pageService, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ServiceOfServcategoryBean> serviceCategoryList = page.getResult();
			servResponse.setPage(pageDTO);
			servResponse.setServiceOfServCategoryList(serviceCategoryList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse searchCategoryView(ServRequest servRequest) {
		Integer userId = servRequest.getUserId();
		Page<ServCategorySearchBean> page = null;
		ServResponse servResponse = new ServResponse();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<ServCategorySearchBean> pageService = servRequest.getPageServCategorySearchBean();
		if (!pageService.isOrderBySetted()) {
			pageService.setOrderBy("categoryId");
			pageService.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchServList = servRequest
					.getSearchPropertyList();
			if (srchServList == null) {
				page = servService.searchServCategoryBean(pageService);
			} else {
				for (SearchProperty searchServ : srchServList) {
					String servName = searchServ.getPropertyName();
					String srchOperator = searchServ.getSearchOperator()
							.name();
					String servType = searchServ.getSearchPropertyType()
							.name();
					String servValue1 = searchServ.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(servType).append("_")
							.append(servName);
					filterMap.put(srchStr.toString(), servValue1);
				}
				page = servService.searchServCategoryBean(pageService, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ServCategorySearchBean> serviceCategoryList = page.getResult();
			servResponse.setPage(pageDTO);
			servResponse.setServCategorySearchBeanList(serviceCategoryList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	/**
	 * 判断ServiceCategory是否可以删除. 返回的 id list是不能删除的.
	 */
	@Override
	public ServResponse getDelServCategory(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			List<Integer> categoryIdList = servRequest.getCategoryIdList();
			List<Integer> unDeleteIdList = this.servService
					.getDelServCategory(categoryIdList);
			servResponse.setUnDeleteIdList(unDeleteIdList);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	/**
	 * 根据一个Service的catalogNo, 及intmdKeyword获得唯一的那条ServiceIntermediate的对应Service的catalogNo.
	 */
	@Override
	public ServResponse getIntmdService(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			String catalogNo = servRequest.getCatalogNo();
			String intmdKeyword = servRequest.getIntmdKeyword();
			String intmdCatalogNo = this.servService.getIntermediaService(catalogNo, intmdKeyword);
			servResponse.setIntmdCatalogNo(intmdCatalogNo);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServiceCatlogId(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		Integer custNo = servRequest.getCustNo();
		try {
			String catalogId = servService.getServiceCatlogId(custNo);
			servResponse.setCatalogId(catalogId);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse searchServiceList(ServRequest servRequest) {
		Integer userId;
		Page<ServiceListBean> pageService;
		Page<ServiceListBean> page = null;
		ServResponse servResponse = new ServResponse();
		userId = servRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		pageService = servRequest.getPgService();
		if (!pageService.isOrderBySetted()) {
			pageService.setOrderBy("id");
			pageService.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = servRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = servService.searchServiceList(pageService);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = servService.searchServiceList(pageService, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ServiceListBean> serviceList = page.getResult();
			if(servRequest.getCatalogId()!=null){
				/*
				 * 将priceLimit 提供给前台页面，前台页面根据priceLimit决定price limit标签是否可写.
				 */
				CatalogDTO catalog = productService.getCatalogDetail(servRequest.getCatalogId());
				servResponse.setPriceLimit(catalog.getPriceLimit());
			}
			servResponse.setPage(pageDTO);
			servResponse.setServiceListBean(serviceList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse delServiceList(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try{
			List<Integer> delServiceListId = servRequest.getDelServiceListId();
		    this.servService.delServiceList(delServiceListId);
		    servResponse.setHasException(Boolean.FALSE);
		} catch(Exception e){
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getRelationItemByServiceId(ServRequest servRequest) {
		ServResponse servResponse = new ServResponse();
		Integer userId = servRequest.getUserId();
		Integer serviceId = servRequest.getServiceId();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(serviceId, "serviceId id can't be null");
			List<DropDownDTO> dropDownDTOList = servService
					.getRelationItemByServiceId(serviceId);
			servResponse.setDropDownDTOList(dropDownDTOList);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0201", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse checkPropertyApproved(ServRequest servRequest) {
		Integer userId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		RequestApproveType requestApproveType = servRequest
				.getRequestApproveType();
		Integer objectId = servRequest.getObjectId();
		String columnName = servRequest.getColumnName();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(requestApproveType,"request approve type can't be null");
			Assert.notNull(columnName, "column name can't be null");
			Assert.notNull(objectId, "object id can't be null");
			Boolean propertyApprovedStatus = servService
					.checkPropertyApproved(objectId, columnName,
							requestApproveType.name());
			servResponse.setPropertyApprovedStatus(propertyApprovedStatus);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse saveService(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			ServiceDTO dto = servRequest.getService();
			Service service = this.servService.saveService(dto, loginUserId,"");
			servResponse.setServiceId(service.getServiceId());// 返回的是主键.
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServRestrictShip(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			Integer serviceId = servRequest.getParamId();
			List<ServiceRestrictShipDTO> restrictShipList = this.servService
					.getServiceRestrictShipList(serviceId);
			servResponse.setRestrictShipList(restrictShipList);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServStockInfo(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			Integer serviceId = servRequest.getParamId();
			ServiceStockStatDTO serviceStockStatDTO = this.servService
					.getServiceStockStat(serviceId);
			servResponse.setServiceStockStatDTO(serviceStockStatDTO);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getServRelationDetail(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			Integer relationId = servRequest.getParamId();
			ServiceRelationDTO dto = this.servService
						.getServiceRelationDetail(relationId);
			servResponse.setServiceRelationDTO(dto);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getComponentList(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			Integer serviceId = servRequest.getParamId();
			Page<ServiceComponent> page = servRequest.getPageComponent();
			Page<ServiceComponentDTO> dtoPage = this.servService.getServiceComponentList(
					page, serviceId);
			List<ServiceComponentDTO> componentList = dtoPage.getResult();
			PageDTO pagerInfo = (PageDTO) dozer.map(page, PageDTO.class);
			servResponse.setComponentList(componentList);
			servResponse.setPage(pagerInfo);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse getIntermediateList(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			Integer serviceId = servRequest.getParamId();
			Page<ServiceIntermediate> page = servRequest.getPageIntermediate();
			Page<ServiceIntermediateDTO> dtoPage = this.servService
					.getServiceIntermediateList(page, serviceId);
			List<ServiceIntermediateDTO> intermediateList = dtoPage.getResult();
			PageDTO pagerInfo = (PageDTO) dozer.map(page, PageDTO.class);
			servResponse.setIntermediateList(intermediateList);
			servResponse.setPage(pagerInfo);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse searchAssociatedItem(ServRequest servRequest) {
		Integer userId;
		Page<ServiceItemBean> pageServiceItemBean;
		Page<ServiceItemBean> page = null;
		ServResponse servResponse = new ServResponse();
		userId = servRequest.getUserId();
		Integer serviceId = servRequest.getServiceId();
		pageServiceItemBean = servRequest.getPageServiceItemBean();
		Map<String, String> filterMap = new HashMap<String, String>();
		List<SearchProperty> srchPropList = servRequest
				.getSearchPropertyList();
		if (srchPropList != null) {
			
			for (SearchProperty searchProperty : srchPropList) {
				String propName = searchProperty.getPropertyName();
				String srchOperator = searchProperty.getSearchOperator().name();
				String propType = searchProperty.getSearchPropertyType().name();
				String propValue1 = searchProperty.getPropertyValue1();

				StringBuilder srchStr = new StringBuilder();
				srchStr.append(srchOperator).append(propType).append("_")
						.append(propName);
				filterMap.put(srchStr.toString(), propValue1);
			}
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			page = servService.searchAssociatedItem(pageServiceItemBean, filterMap, serviceId);
			List<ServiceItemBean> serviceItemBeanList = page.getResult();
			List<ItemBeanDTO> itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
				ItemBeanDTO itemBean = dozer.map(serviceItemBean,
						ItemBeanDTO.class);
				itemBean.setUpSymbol(serviceItemBean.getSymbol());
				itemBeanDTOList.add(itemBean);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			servResponse.setHasException(Boolean.FALSE);
			servResponse.setPage(pageDTO);
			servResponse.setItemBeanDTOList(itemBeanDTOList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	@Override
	public ServResponse searchStdPriceList(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			String catalogNo = servRequest.getCatalogNo();
			String name = servRequest.getName();
			List<String> catalogNoList = servRequest.getCatalogNoList();
			List<com.genscript.gsscm.serv.entity.Service> stdPriceList = this.servService
					.searchServiceBreakDownList(catalogNo, name, catalogNoList);
			servResponse.setStdPriceList(stdPriceList);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	/**
	 * 获得一个Service的RoyaltyService(版权税)信息.(For misc tab);
	 */
	@Override
	public ServResponse getRoyaltyService(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			String catalogNo = servRequest.getCatalogNo();
			RoyaltyServiceDTO royalty = this.servService
					.getRoyaltyService(catalogNo);
			servResponse.setRoyaltyService(royalty);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}

	/**
	 * For Suppliers's Tab: Purchasing Level and Supplier List .
	 */
	@Override
	public ServResponse getServSupplierList(ServRequest servRequest) {
		Integer loginUserId = servRequest.getUserId();
		ServResponse servResponse = new ServResponse();
		try {
			String catalogNo = servRequest.getCatalogNo();
			List<VendorServiceDTO> verdonServiceList = this.servService.getSupplierList(catalogNo);
			servResponse.setVerdonServiceList(verdonServiceList);
			servResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0207", loginUserId);
			servResponse.setHasException(true);
			servResponse.setWsException(exDTO);
		}
		return servResponse;
	}
}
