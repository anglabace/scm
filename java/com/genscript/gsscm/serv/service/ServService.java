package com.genscript.gsscm.serv.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.PbStateDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.RequestApproveStatusType;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.SoapUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.inventory.dao.StockDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderReturnItemDao;
import com.genscript.gsscm.order.dto.ProductServiceSaleDTO;
import com.genscript.gsscm.order.dto.SalesRankingDTO;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.CatalogDao;
import com.genscript.gsscm.product.dao.CatalogNORulesDao;
import com.genscript.gsscm.product.dao.RoyaltyDao;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.CatalogNORules;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.purchase.dao.PurchaseOrderItemDao;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.dao.VendorServiceDao;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.dto.VendorServiceDTO;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.entity.VendorServiceEntity;
import com.genscript.gsscm.serv.dao.RoyaltyServiceDao;
import com.genscript.gsscm.serv.dao.ServCategorySearchBeanDao;
import com.genscript.gsscm.serv.dao.ServiceCategoryDao;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceComponentDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceIntermediateDao;
import com.genscript.gsscm.serv.dao.ServiceItemBeanDao;
import com.genscript.gsscm.serv.dao.ServiceListBeanDao;
import com.genscript.gsscm.serv.dao.ServiceOfServcategoryBeanDao;
import com.genscript.gsscm.serv.dao.ServicePriceDao;
import com.genscript.gsscm.serv.dao.ServiceReferenceDao;
import com.genscript.gsscm.serv.dao.ServiceRelationBeanDao;
import com.genscript.gsscm.serv.dao.ServiceRelationDao;
import com.genscript.gsscm.serv.dao.ServiceRestrictShipDao;
import com.genscript.gsscm.serv.dao.ServiceShipConditionDao;
import com.genscript.gsscm.serv.dao.ServiceSpecialPriceDao;
import com.genscript.gsscm.serv.dao.ServiceStorageConditionDao;
import com.genscript.gsscm.serv.dao.ServiceSubStepPriceDao;
import com.genscript.gsscm.serv.dao.ServiceSubStepsDao;
import com.genscript.gsscm.serv.dto.RoyaltyServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceComponentDTO;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceIntermediateDTO;
import com.genscript.gsscm.serv.dto.ServicePriceDTO;
import com.genscript.gsscm.serv.dto.ServicePriceListBeanDTO;
import com.genscript.gsscm.serv.dto.ServiceRelationDTO;
import com.genscript.gsscm.serv.dto.ServiceRelationItemDTO;
import com.genscript.gsscm.serv.dto.ServiceReportSrchDTO;
import com.genscript.gsscm.serv.dto.ServiceRestrictShipDTO;
import com.genscript.gsscm.serv.dto.ServiceStockStatDTO;
import com.genscript.gsscm.serv.dto.ServiceSubStepsDTO;
import com.genscript.gsscm.serv.dto.SubStepPriceDTO;
import com.genscript.gsscm.serv.entity.RoyaltyService;
import com.genscript.gsscm.serv.entity.ServCategorySearchBean;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.entity.ServiceComponent;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;
import com.genscript.gsscm.serv.entity.ServicePrice;
import com.genscript.gsscm.serv.entity.ServiceReference;
import com.genscript.gsscm.serv.entity.ServiceRelation;
import com.genscript.gsscm.serv.entity.ServiceRestrictShip;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.serv.entity.ServiceSpecialPrice;
import com.genscript.gsscm.serv.entity.ServiceStorageCondition;
import com.genscript.gsscm.serv.entity.ServiceSubStepPrice;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;
import com.genscript.gsscm.system.dao.ApproveRequestBeanDao;
import com.genscript.gsscm.system.dao.ApproveRequestDao;
import com.genscript.gsscm.system.dao.ApproveRequestDetailDao;
import com.genscript.gsscm.system.dao.EmarketingGroupAssignedDao;
import com.genscript.gsscm.system.entity.ApproveRequest;
import com.genscript.gsscm.system.entity.ApproveRequestBean;
import com.genscript.gsscm.system.entity.ApproveRequestDetail;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class ServService {
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private ServiceCategoryDao serviceCategoryDao;
	@Autowired
	private ServicePriceDao servicePriceDao;
	@Autowired
	private CatalogNORulesDao catalogNORulesDao;
	@Autowired
	private ServiceItemBeanDao serviceItemBeanDao;
	@Autowired
	private ServiceRelationDao serviceRelationDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ApproveRequestDetailDao approveRequestDetailDao;
	@Autowired
	private ServiceShipConditionDao serviceShipConditionDao;
	@Autowired
	private ServiceStorageConditionDao serviceStorageConditionDao;
	@Autowired
	private ServiceOfServcategoryBeanDao serviceOfServcategoryBeanDao;
	@Autowired
	private ServCategorySearchBeanDao serviceCategorySearchBeanDao;
	@Autowired
	private ServiceIntermediateDao serviceIntermediateDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private ServiceListBeanDao serviceListBeanDao;
	@Autowired
	private ServiceRelationBeanDao serviceRelationBeanDao;
	@Autowired
	private ApproveRequestBeanDao approveRequestBeanDao;
	@Autowired
	private ApproveRequestDao approveRequestDao;
	@Autowired
	private ServiceComponentDao serviceComponentDao;
	@Autowired
	private ServiceRestrictShipDao serviceRestrictShipDao;
	@Autowired
	private ServiceSpecialPriceDao serviceSpecialPriceDao;
	@Autowired
	private RoyaltyServiceDao royaltyServiceDao;
	@Autowired
	private StockDao stockDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private PurchaseOrderItemDao purchaseOrderItemDao;
	@Autowired
	private OrderReturnItemDao orderReturnItemDao;
	@Autowired
	private ServiceRestrictShipDao restrictShipDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private PbStateDao pbStateDao;
	@Autowired
	private RoyaltyDao royaltyDao;
	@Autowired
	private VendorServiceDao vendorServiceDao;
	@Autowired
	private VendorDao vendorDao;
	@Autowired
	private ServiceSubStepsDao serviceSubStepsDao;
	@Autowired
	private ServiceSubStepPriceDao serviceSubStepPriceDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ServiceReferenceDao serviceReferenceDao;
	@Autowired
	private EmarketingGroupAssignedDao emarketingGroupAssignedDao;

	/*
	 * 获取service列表。
	 * 
	 * @param page<service> 设定列表的分页显示,有 pageNo,pageSize,orderBy,order,autoCount
	 * ; return : result;
	 * 
	 * @param List<PropertyFilter> filters 用于设定SQL语名WHERE后面的条件。
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<com.genscript.gsscm.serv.entity.Service> searchServList(
			final Page<com.genscript.gsscm.serv.entity.Service> page,
			final List<PropertyFilter> filters) {
		return serviceDao.findPage(page, filters);
	}

	/*
	 * 获取service列表 将final Map<String, String> filterParamMap转化为
	 * List<PropertyFilter> filterList;
	 */
	@Transactional(readOnly = true)
	public Page<com.genscript.gsscm.serv.entity.Service> searchServList(
			final Page<com.genscript.gsscm.serv.entity.Service> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		String baseCatalogId = catalogDao.getBaseCatalogId();
		filterList.add(new PropertyFilter("EQS_catalogId", baseCatalogId));
		return serviceDao.findPage(page, filterList);
	}

	/*
	 * 获取service列表 ，SQL没有WHERE条件。
	 */
	@Transactional(readOnly = true)
	public Page<com.genscript.gsscm.serv.entity.Service> searchServList(
			final Page<com.genscript.gsscm.serv.entity.Service> page) {
		return serviceDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ServiceItemBean> searchItemPrice(
			final Page<ServiceItemBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return serviceItemBeanDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public ServiceRelationDTO getServiceRelations(
			ServiceItemBean serviceItemBean) {
		if (serviceItemBean != null) {
			ServiceRelationDTO serviceRelationDTO = null;
			serviceRelationDTO = new ServiceRelationDTO();
			List<ServiceRelationItemDTO> serviceRelationList = serviceRelationDao
					.getRelateServiceBean(serviceItemBean.getServiceId());
			serviceRelationDTO.setName(serviceItemBean.getName());
			serviceRelationDTO
					.setServiceRelationItemDTOList(serviceRelationList);
			return serviceRelationDTO;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<SearchItemDTO> getSearchItemInfo(
			final List<String> catalogNoList, final Integer custNo) {
		if (catalogNoList != null && catalogNoList.size() > 0) {
			List<SearchItemDTO> searchItemDTOList = new ArrayList<SearchItemDTO>();
			for (String catalogNo : catalogNoList) {
				com.genscript.gsscm.serv.entity.Service serv = serviceDao
						.findUniqueBy("catalogNo", catalogNo);
				SearchItemDTO searchItemDTO = new SearchItemDTO();
				searchItemDTO.setCatalogNo(catalogNo);
				if (serv != null) {
					searchItemDTO.setPrefStorage(serv.getPrefStorage());
					searchItemDTO.setPrefWarehouse(serv.getPrefWarehouse());
					searchItemDTO.setClsId(serv.getServiceClsId());
					searchItemDTO.setClsName(serviceClassDao.get(
							serv.getServiceClsId()).getName());
					searchItemDTO.setCustomerInfo(serv.getCustomerInfo());
					searchItemDTO.setFullDesc(serv.getLongDesc());
					searchItemDTO.setDescription(serv.getShortDesc());
					searchItemDTO.setScheduleShip(serv.getLeadTime());
					searchItemDTO.setTaxStatus(serv.getTaxable());
					searchItemDTO.setSellingNote(serv.getSellingNote());
					searchItemDTO.setQtyUom(serv.getQtyUom());
					searchItemDTO.setSize(serv.getSize());
					searchItemDTO.setName(serv.getName());
					searchItemDTO.setUom(serv.getUom());
					String catalogId = getServiceCatlogId(custNo);
					searchItemDTO.setCatalogId(catalogId);
					Catalog catalog = catalogDao.findUniqueBy("catalogId",
							catalogId);
					if (catalog != null) {
						searchItemDTO.setCatalogName(catalog.getCatalogName());
					}
				}
				searchItemDTOList.add(searchItemDTO);
			}
			return searchItemDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public SearchItemDTO getSearchItemInfo(final String catalogNo,
			final Integer custNo) {
		if (StringUtils.isNotBlank(catalogNo)) {
			SearchItemDTO searchItemDTO = new SearchItemDTO();
			com.genscript.gsscm.serv.entity.Service serv = serviceDao
					.findUniqueBy("catalogNo", catalogNo);
			searchItemDTO.setCatalogNo(catalogNo);
			if (serv != null) {
				searchItemDTO.setPrefStorage(serv.getPrefStorage());
				searchItemDTO.setPrefWarehouse(serv.getPrefWarehouse());
				searchItemDTO.setClsId(serv.getServiceClsId());
				searchItemDTO.setClsName(serviceClassDao.get(
						serv.getServiceClsId()).getName());
				searchItemDTO.setCustomerInfo(serv.getCustomerInfo());
				searchItemDTO.setFullDesc(serv.getLongDesc());
				searchItemDTO.setDescription(serv.getShortDesc());
				searchItemDTO.setScheduleShip(serv.getLeadTime());
				searchItemDTO.setTaxStatus(serv.getTaxable());
				searchItemDTO.setSellingNote(serv.getSellingNote());
				searchItemDTO.setQtyUom(serv.getQtyUom());
				searchItemDTO.setSize(serv.getSize());
				searchItemDTO.setName(serv.getName());
				searchItemDTO.setUom(serv.getUom());
				String catalogId = getServiceCatlogId(custNo);
				searchItemDTO.setCatalogId(catalogId);
				Catalog catalog = catalogDao.findUniqueBy("catalogId",
						catalogId);
				if (catalog != null) {
					searchItemDTO.setCatalogName(catalog.getCatalogName());
				}
			}
			return searchItemDTO;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchCategoryList(Page<ServiceCategory> page,
			List<PropertyFilter> filters) throws Exception {
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		// Object userName = session.get(StrutsActionContant.USER_NAME);
		// Object userId = session.get(StrutsActionContant.USER_ID);
		// if (filters == null) {
		// filters = new ArrayList<PropertyFilter>();
		// }
		// if (!Constants.USERNAME_ADMIN.equals(userName)) {
		// boolean isProductionManagerRole = userRoleDao
		// .checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
		// if (!isProductionManagerRole) {
		// PropertyFilter pf = new PropertyFilter("EQI_createdBy",
		// Integer.parseInt(userId.toString()));
		// filters.add(pf);
		// }
		// }
		return serviceCategoryDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchCategorySList(
			Page<ServiceCategory> page, String values, Integer categoryLevel) {
		return serviceCategoryDao.searchCategoryLists(page, categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchCategoryMapList(
			Page<ServiceCategory> page, String values, Integer categoryLevel,
			Map<String, ServiceCategory> servicecatMap) {
		return serviceCategoryDao.searchCategoryByMapList(page, servicecatMap,
				categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchSubCategoryList(
			Page<ServiceCategory> page, Integer categoryId,
			Integer categoryLevel) {
		return serviceCategoryDao.searchSubCategoryLists(page, categoryId,
				categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchSubCategoryMapList(
			Page<ServiceCategory> page, Integer categoryId,
			Integer categoryLevel, Map<String, ServiceCategory> map) {
		return serviceCategoryDao.searchSubCategoryByMapList(page, categoryId,
				map, categoryLevel);
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchCategoryList(Page<ServiceCategory> page) {
		return serviceCategoryDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> searchCategoryList(Page<ServiceCategory> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return serviceCategoryDao.findPage(page, filterList);
	}

	/**
	 * 获得一个service的基本信息 service_ship_condition service_storage_condition基本信息.
	 * 
	 * @param serviceId
	 * @return
	 */
	public ServiceDTO getServDetail(Integer serviceId) {
		com.genscript.gsscm.serv.entity.Service serv = this.serviceDao
				.getById(serviceId);
		if (serv == null) {
			return null;
		}
		ServiceDTO dto = this.dozer.map(serv, ServiceDTO.class);
		User createUser = this.userDao.getById(serv.getCreatedBy());
		if (createUser != null) {
			dto.setCreatedByText(createUser.getLoginName());
		}
		User modifyUser = this.userDao.get(serv.getModifiedBy());
		if (modifyUser != null) {
			dto.setModifyByText(modifyUser.getLoginName());
		}
		// 获得service_ship_condition基本信息
		ServiceShipCondition ShipCondition = this.serviceShipConditionDao
				.getShipCondition(serviceId);
		dto.setShipCondition(ShipCondition);
		// 获得service_storage_condition基本信息
		ServiceStorageCondition StorageCondition = this.serviceStorageConditionDao
				.getStorageCondition(serviceId);
		dto.setStorageCondition(StorageCondition);
		return dto;
	}

	/**
	 * Save ServiceCategory base information and Sub-ServiceCategory list,
	 * Service list.
	 * 
	 * @param dto
	 * @param loginUserId
	 * @return
	 */
	public ServiceCategory saveServCategory(ServiceCategoryDTO dto,
			Integer loginUserId) {
		ServiceCategory category = this.dozer.map(dto, ServiceCategory.class);
		ServiceCategory dbCategory = this.serviceCategoryDao.findUniqueBy(
				"categoryNo", category.getCategoryNo());
		if (dbCategory != null
				&& (category.getCategoryId() == null || !(category
						.getCategoryId().equals(dbCategory.getCategoryId())))) {

			throw new BussinessException(
					BussinessException.ERR_SERVICECATEGORY_CATEGORYNO_UNIQUE);
		}
		this.serviceCategoryDao.getSession().evict(dbCategory);
		Integer objectId = category.getCategoryId();// for approve request.
		this.saveOrUpdateServCategory(category, loginUserId);
		if (dto.getSubCategoryLists() != null
				&& !dto.getSubCategoryLists().isEmpty()) {
			this.attachSubServCategoryLists(category,
					dto.getSubCategoryLists(), dto.getDelSubCategoryIdList(),
					loginUserId);
		} else {
			this.attachSubServCategoryList(category, dto.getSubCategoryList(),
					dto.getDelSubCategoryIdList(), loginUserId);
		}
		this.attachServPrice(category, dto.getServPriceList(),
				dto.getDelServPriceIdList(), loginUserId);
		Integer requestId = null;
		/*
		 * if (objectId != null) { ApproveRequest appRequest =
		 * approveRequestDao.countApproveRequest(
		 * RequestApproveType.ServiceCategory.name(), objectId); if (appRequest
		 * != null) { requestId = appRequest.getRequestId();
		 * approveRequestDao.getSession().evict(appRequest); } }
		 */
		this.attachServiceReference(dto.getServiceReferenceList(),
				category.getCategoryId(), loginUserId,
				dto.getDelReferenceList());
		categoryApproved(dto, loginUserId, category, objectId, requestId);

		return category;
	}

	/*
	 * del category 批量修改status 为INACTIVE
	 */

	public void delCategoryApproved(String[] objectIds, Integer userId,
			String approved, String approvedReason) {
		for (int i = 0; i < objectIds.length; i++) {
			Integer requestId = null;
			ServiceCategoryDTO dto = new ServiceCategoryDTO();
			dto.setStatusApprove(approved);
			ServiceCategory category = this.serviceCategoryDao.getById(Integer
					.valueOf(objectIds[i]));
			categoryApproved(dto, userId, category,
					Integer.valueOf(objectIds[i]), requestId);
		}
	}

	private void categoryApproved(ServiceCategoryDTO dto, Integer loginUserId,
			ServiceCategory category, Integer objectId, Integer requestId) {
		if (dto.getNameApprove() != null || dto.getStatusApprove() != null) {
			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail1, approveRequestDetail2;
			approveRequestDetail1 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "name");
			approveRequestDetailDao.getSession().evict(approveRequestDetail1);
			if (dto.getNameApprove() == null
					|| dto.getNameApprove().length() < 1) {
				if (approveRequestDetail1 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail1);
				}
			} else {
				if (approveRequestDetail1 == null) {
					approveRequestDetail1 = new ApproveRequestDetail();
				}
				approveRequestDetail1.setColumnName("name");
				approveRequestDetail1.setOldValue(category.getName());
				approveRequestDetail1.setNewValue(dto.getNameApprove());
				approveRequestDetail1.setReason(dto.getNameReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail1);
			}

			approveRequestDetail2 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "status");
			approveRequestDetailDao.getSession().evict(approveRequestDetail2);
			if (dto.getStatusApprove() == null
					|| dto.getStatusApprove().length() < 1) {
				if (approveRequestDetail2 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail2);
				}
			} else {
				if (approveRequestDetail2 == null) {
					approveRequestDetail2 = new ApproveRequestDetail();
				}
				approveRequestDetail2.setColumnName("status");
				approveRequestDetail2.setOldValue(category.getStatus());
				approveRequestDetail2.setNewValue(dto.getStatusApprove());
				approveRequestDetail2.setReason(dto.getStatusReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail2);
			}

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(objectId);
			approveRequest.setTableName(RequestApproveType.ServiceCategory
					.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(loginUserId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	public void attachServPrice(ServiceCategory category,
			List<ServicePriceDTO> priceList, List<Integer> delPriceList,
			Integer loginUserId) {
		if (delPriceList != null && delPriceList.get(0) != null) {
			this.servicePriceDao.delServicePriceList(category.getCategoryId(),
					delPriceList);
		}
		if (!(priceList == null || priceList.get(0) == null)) {
			Date now = new Date();
			for (ServicePriceDTO dto : priceList) {
				ServicePrice target = this.dozer.map(dto, ServicePrice.class);
				target.setModifyDate(now);
				target.setModifiedBy(loginUserId);
				if (SoapUtil.getIntegerFromSOAP(target.getPriceId()) == null) {
					target.setPriceId(null);
					target.setCreatedBy(loginUserId);
					target.setCreationDate(now);
				}
				target.setCatalogId(category.getCatalogId());
				target.setCategoryId(category.getCategoryId());
				target.setStatus(category.getStatus());// 取ServiceCategory的status;
				this.servicePriceDao.save(target);
			}
		}
	}

	public void saveOrUpdateServCategory(ServiceCategory category,
			Integer loginUserId) {
		ServiceCategory dbCategory = this.serviceCategoryDao.findUniqueBy(
				"categoryNo", category.getCategoryNo());
		if (dbCategory != null
				&& (category.getCategoryId() == null || !(category
						.getCategoryId().equals(dbCategory.getCategoryId())))) {
			// this.serviceCategoryDao.getSession().evict(dbCategory);
			throw new BussinessException(
					BussinessException.ERR_SERVICECATEGORY_CATEGORYNO_UNIQUE);
		}
		this.serviceCategoryDao.getSession().evict(dbCategory);
		if (category.getCatalogId() == null
				|| category.getCatalogId().trim().length() < 1) {
			category.setCatalogId(null);
		}
		Date now = new Date();
		if (category.getCategoryId() == null
				|| category.getCategoryId().intValue() == 0) {
			category.setCategoryId(null);
			category.setCreationDate(now);
			category.setCreatedBy(loginUserId);
		}
		if (category.getParentCatId() == null
				|| category.getParentCatId().intValue() == 0) {
			category.setParentCatId(null);
		}
		category.setModifiedBy(loginUserId);
		category.setModifyDate(now);
		this.serviceCategoryDao.save(category);
	}

	/**
	 * 删除ServiceCategory的同时新增， 修改和删除它的sub service category.
	 * 
	 * @param category
	 * @param subCatList
	 * @param delCatIdList
	 */
	public void attachSubServCategoryList(ServiceCategory category,
			List<ServiceCategoryDTO> subCatList, List<Integer> delCatIdList,
			Integer loginUserId) {
		if (delCatIdList != null && delCatIdList.get(0) != null) {
			for (Integer servCategoryId : delCatIdList) {
				Map<Integer, String> delResult = this
						.delServCategoryResult(servCategoryId);
				if (delResult != null) {
					throw new BussinessException(delResult.get(servCategoryId));
				}
			}
			this.serviceCategoryDao
					.delServiceCatListByModifyPCatId(delCatIdList);
		}
		if (!(subCatList == null || subCatList.get(0) == null)) {
			Date now = new Date();
			for (ServiceCategoryDTO dto : subCatList) {
				ServiceCategory cat = this.dozer
						.map(dto, ServiceCategory.class);
				cat.setModifyDate(now);
				cat.setModifiedBy(loginUserId);
				if (cat.getCategoryId() == null
						|| cat.getCategoryId().intValue() == 0) {
					cat.setCategoryId(null);
					cat.setCreatedBy(loginUserId);
					cat.setCreationDate(now);
				}
				cat.setCatalogId(category.getCatalogId());
				cat.setParentCatId(category.getCategoryId());
				this.serviceCategoryDao.save(cat);
				List<ServiceCategory> subCategroyList = this.serviceCategoryDao
						.findBy("parentCatId", cat.getCategoryId());
				this.serviceCategoryDao.getSession().evict(subCategroyList);
				if (subCategroyList != null && !subCategroyList.isEmpty()) {
					for (ServiceCategory pc : subCategroyList) {
						pc.setCatalogId(category.getCatalogId());
						this.serviceCategoryDao.save(pc);
					}
				}
			}

		}
	}

	/**
	 * 删除ServieCategory的同时新增， 修改和删除它的sub service category.
	 * 
	 * @param category
	 * @param category
	 * @param delCatIdList
	 */
	public void attachSubServCategoryLists(ServiceCategory category,
			List<ServiceCategory> subCatList, List<Integer> delCatIdList,
			Integer loginUserId) {
		if (delCatIdList != null && delCatIdList.get(0) != null) {
			for (Integer servCategoryId : delCatIdList) {
				Map<Integer, String> delResult = this
						.delServCategoryResult(servCategoryId);
				if (delResult != null) {
					throw new BussinessException(delResult.get(servCategoryId));
				}
			}
			this.serviceCategoryDao
					.delServiceCatListByModifyPCatId(delCatIdList);
		}
		if (!(subCatList == null || subCatList.get(0) == null)) {
			Date now = new Date();
			for (ServiceCategory cat : subCatList) {
				cat.setModifyDate(now);
				cat.setModifiedBy(loginUserId);
				if (cat.getCategoryId() == null
						|| cat.getCategoryId().intValue() == 0) {
					cat.setCategoryId(null);
					cat.setCreatedBy(loginUserId);
					cat.setCreationDate(now);
				}
				cat.setCatalogId(category.getCatalogId());
				cat.setParentCatId(category.getCategoryId());
				this.serviceCategoryDao.save(cat);
				List<ServiceCategory> subCategroyList = this.serviceCategoryDao
						.findBy("parentCatId", cat.getCategoryId());
				this.serviceCategoryDao.getSession().evict(subCategroyList);
				if (subCategroyList != null && !subCategroyList.isEmpty()) {
					for (ServiceCategory pc : subCategroyList) {
						pc.setCatalogId(category.getCatalogId());
						this.serviceCategoryDao.save(pc);
					}
				}
			}

		}
	}

	public Map<Integer, String> delServCategoryResult(Integer servCategoryId) {
		Map<Integer, String> retMap = null;
		// 判断该category是否有子级category.
		Long subCount = this.serviceCategoryDao
				.getSubServCategoryCount(servCategoryId);
		if (subCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(servCategoryId,
					BussinessException.ERR_DEL_SERVCATE_HASSUB);
			return retMap;
		}
		// 判断该category是否有service与之关联.
		Long serviceCount = this.servicePriceDao
				.getCountByCategoryId(servCategoryId);
		if (serviceCount.intValue() > 0) {
			retMap = new HashMap<Integer, String>();
			retMap.put(servCategoryId,
					BussinessException.ERR_DEL_SERVCATE_HASSERVS);
			return retMap;
		}
		// 判断该Category是否已经Approved
		return null;
	}

	public ServiceCategory getServiceCategoryDetail(Integer categoryId) {
		ServiceCategory servCategory = this.serviceCategoryDao
				.getById(categoryId);
		this.serviceCategoryDao.getSession().evict(servCategory);
		return servCategory;
	}

	/*
	 * 获得service category基本信息。
	 * 
	 * @param categoryId
	 */
	public ServiceCategoryDTO getServCategoryDetail(Integer categoryId) {
		ServiceCategory servCategory = this.serviceCategoryDao
				.getById(categoryId);
		ServiceCategoryDTO servCategoryDTO = this.dozer.map(servCategory,
				ServiceCategoryDTO.class);
		User user = this.userDao.getById(servCategory.getCreatedBy());
		if (user != null) {
			servCategoryDTO.setCreateUser(user.getLoginName());
		}
		User modifyUser = this.userDao.get(servCategory.getModifiedBy());
		if (modifyUser != null) {
			servCategoryDTO.setModifyUser(modifyUser.getLoginName());
		}
		if (servCategory.getParentCatId() != null) {
			ServiceCategory servParentCate = this.serviceCategoryDao
					.getById(servCategory.getParentCatId());
			if (servParentCate != null) {
				servCategoryDTO.setParentCatName(servParentCate.getName());
				servCategoryDTO.setParentCatNo(servParentCate.getCategoryNo());
			}
		}
		if (servCategory.getPreviousCatId() != null) {
			ServiceCategory serPrevious = this.serviceCategoryDao
					.getById(servCategory.getPreviousCatId());
			if (serPrevious != null) {
				servCategoryDTO.setPreviousCatName(serPrevious.getName());
				servCategoryDTO.setPreviousCatNo(serPrevious.getCategoryNo());
			}
		}
		servCategoryDTO.setServiceReferenceList(this
				.searchServiceReferenceByCategoryId(categoryId));
		return servCategoryDTO;
	}

	/**
	 * 批量删除service Categories. 同时成功或失败.
	 * 
	 * @param categoryIdList
	 */

	public void delServCategory(List<Integer> categoryIdList) {
		for (Integer servCategoryId : categoryIdList) {
			Map<Integer, String> delResult = this
					.delServCategoryResult(servCategoryId);
			if (delResult != null) {
				throw new BussinessException(delResult.get(servCategoryId));
			} else {
				this.delServCategoryResult(servCategoryId);
			}
		}
		this.serviceCategoryDao.delServiceCatList(categoryIdList);
	}

	/**
	 * 批量删除service Categories. 同时成功或失败.
	 * 
	 * @param categoryIdList
	 */

	public void delServCategoryStr(List<String> categoryIdList) {
		for (String servCategoryStrId : categoryIdList) {
			Integer servCategoryId = Integer.valueOf(servCategoryStrId);
			Map<Integer, String> delResult = this
					.delServCategoryResult(servCategoryId);
			if (delResult != null) {
				throw new BussinessException(delResult.get(servCategoryId));
			}
			this.serviceCategoryDao.delete(servCategoryId);
		}
	}

	/*
	 * 获取service Sub Category List
	 */
	@Transactional(readOnly = true)
	public Page<ServiceOfServcategoryBean> getServOfServCategoryList(
			Page<ServiceOfServcategoryBean> page, List<PropertyFilter> filters) {
		return this.serviceOfServcategoryBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ServiceOfServcategoryBean> getServOfServCategoryList(
			Page<ServiceOfServcategoryBean> page) {
		return this.serviceOfServcategoryBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ServiceOfServcategoryBean> getServOfServCategoryList(
			Page<ServiceOfServcategoryBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return this.serviceOfServcategoryBeanDao.findPage(page, filterList);
	}

	/*
	 * service category search view
	 */
	@Transactional(readOnly = true)
	public Page<ServCategorySearchBean> searchServCategoryBean(
			Page<ServCategorySearchBean> page, List<PropertyFilter> filters) {
		return this.serviceCategorySearchBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ServCategorySearchBean> searchServCategoryBean(
			Page<ServCategorySearchBean> page) {
		return this.serviceCategorySearchBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ServCategorySearchBean> searchServCategoryBean(
			Page<ServCategorySearchBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return this.serviceCategorySearchBeanDao.findPage(page, filterList);
	}

	/**
	 * 判断ServiceCategory是否可以删除. 返回的 id list是不能删除的.
	 * 
	 * @param categoryIdList
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Integer> getDelServCategory(List<Integer> categoryIdList) {
		List<Integer> dtoList = new ArrayList<Integer>();// 不能删除的.
		for (Integer id : categoryIdList) {
			Map<Integer, String> retMap = this.delServCategoryResult(id);
			if (retMap != null) {
				dtoList.add(id);
			}
		}
		return dtoList;
	}

	/**
	 * 肯定有值， 否则直接报错.
	 * 
	 * @param srcCatalogNo
	 * @param intmdKeyword
	 * @return
	 */
	public String getIntermediaService(String srcCatalogNo, String intmdKeyword) {
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.findUniqueBy("catalogNo", srcCatalogNo);
		ServiceIntermediate servIntmd = serviceIntermediateDao.getIntmd(
				service.getServiceId(), intmdKeyword);
		return servIntmd.getIntmdCatalogNo();
	}

	/**
	 * 肯定有值， 否则直接报错.
	 * 
	 * @param srcCatalogNo
	 * @param intmdKeyword
	 * @return
	 */
	public List<ServiceIntermediate> getIntermediaServiceList(
			String srcCatalogNo, String intmdKeyword) {
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.findUniqueBy("catalogNo", srcCatalogNo);
		List<ServiceIntermediate> servIntmdList = serviceIntermediateDao
				.getIntmdList(service.getServiceId(), intmdKeyword);
		return servIntmdList;
	}

	public String getServiceCatlogId(final Integer custNo) {
		Customer customer = customerDao.getById(custNo);
		if (customer != null && customer.getPriceCatalogId() != null) {
			return customer.getPriceCatalogId();
		} else {
			return catalogDao.getBaseCatalogId();
		}
	}

	@Transactional(readOnly = true)
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page) {
		return serviceListBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page,
			List<PropertyFilter> filters) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String userName = (String) session.get(StrutsActionContant.USER_NAME);
		Integer userId = (Integer) session.get(StrutsActionContant.USER_ID);
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		Criterion criterionCatalogNo = null;
		if (!Constants.USERNAME_ADMIN.equals(userName)) {
			boolean isProductionManagerRole = userRoleDao
					.checkIsContainsManagerRole(Constants.ROLE_PRODUCTION_MANAGER);
			if (!isProductionManagerRole) {
				List<Integer> clsIdList = emarketingGroupAssignedDao
						.getClsIdByStaffIdAndType(userId,
								QuoteItemType.SERVICE.value());
				if (clsIdList == null || clsIdList.isEmpty()) {
					return page;
				}
				List<String> catalogNoList = serviceDao
						.getCatalogNoByClsId(clsIdList);
				if (catalogNoList == null || catalogNoList.isEmpty()) {
					return page;
				}
				criterionCatalogNo = Restrictions
						.in("catalogNo", catalogNoList);
				// PropertyFilter pf = new PropertyFilter("EQI_createdBy",
				// Integer.parseInt(userId.toString()));
				// filters.add(pf);
			}
		}
		return serviceListBeanDao.findPageByFilter(page, filters,
				criterionCatalogNo);
	}

	@Transactional(readOnly = true)
	public List<ServiceListBean> searchAllServiceListOfFilter(
			List<PropertyFilter> filters) {
		return serviceListBeanDao.find(filters);
	}

	@Transactional(readOnly = true)
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return serviceListBeanDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public List<DropDownDTO> getRelationItemByServiceId(Integer serviceId) {
		return serviceRelationBeanDao.getRelationItemByServiceIdNew(serviceId);
	}

	@Transactional(readOnly = true)
	public Boolean checkPropertyApproved(final Integer objectId,
			final String columnName, final String tableName) {
		Boolean b = approveRequestBeanDao.getUnapprovedRequestStatus(objectId,
				columnName, tableName);
		this.approveRequestBeanDao.getSession().evict(b);
		return b;
	}

	/**
	 * 保存service
	 * 
	 * @param serviceDTO
	 * @return
	 */
	public com.genscript.gsscm.serv.entity.Service saveService(
			ServiceDTO serviceDTO, Integer userId, String ruleId) {
		com.genscript.gsscm.serv.entity.Service service = this.dozer.map(
				serviceDTO, com.genscript.gsscm.serv.entity.Service.class);
		com.genscript.gsscm.serv.entity.Service dbServcie = this.serviceDao
				.findUniqueBy("catalogNo", service.getCatalogNo());
		if (dbServcie != null
				&& (service.getServiceId() == null || !(service.getServiceId()
						.equals(dbServcie.getServiceId())))) {
			// this.serviceDao.getSession().evict(dbServcie);
			System.out.println("ruleId:" + ruleId);
			if (ruleId != null && !ruleId.equals("") && !ruleId.equals("null")) {
				System.out.println("ruleId:" + ruleId);
				this.productService.saveCatalogNoRules(Integer.valueOf(ruleId));
			}
			throw new BussinessException(
					BussinessException.ERR_SERVICE_CATALOGNO_UNIQUE);
		}
		this.serviceDao.getSession().evict(dbServcie);
		Integer objectId = service.getServiceId();// for approved.
		Date now = new Date();
		if (service.getCreatedBy() == null) {
			service.setCreatedBy(userId);
			service.setCreationDate(now);
		}
		service.setModifiedBy(userId);
		service.setModifyDate(now);
		if (service.getDimUom() == null) {
			service.setDimUom("inches");
		}
		this.serviceDao.save(service);
		System.out.println("ruleId:" + ruleId);
		if (ruleId != null && !ruleId.equals("") && !ruleId.equals("null")) {
			System.out.println("ruleId:" + ruleId);
			this.productService.saveCatalogNoRules(Integer.valueOf(ruleId));
		}
		this.attachShipCondition(service, serviceDTO.getShipCondition(), userId);
		this.attachStorageCondition(service, serviceDTO.getStorageCondition(),
				userId);
		this.attachServiceIntmd(service, serviceDTO.getIntmdList(),
				serviceDTO.getDelIntmdIdList(), userId);
		this.attachServiceComponent(service, serviceDTO.getComponentList(),
				serviceDTO.getDelComIdList(), userId);
		this.attachServiceRestrictShip(service,
				serviceDTO.getRestrictShipList(),
				serviceDTO.getDelRestrictShipIdList(), userId);
		this.attachServiceSpecialPrice(service,
				serviceDTO.getSpecialPriceList(),
				serviceDTO.getDelSpecialPriceIdList(), userId);
		this.attachSupplier(service, serviceDTO.getVendorServiceList(),
				serviceDTO.getDelVendorServiceIdList(), userId);
		this.attachServiceRelation(service, serviceDTO.getServRelationList(),
				userId);
		this.attachServiceRoyalty(service, serviceDTO.getRoyaltyService(),
				userId);
		this.attachServiceSubStep(service, serviceDTO.getSubStepList(),
				serviceDTO.getDelSubStep(), userId);
		this.attachServicePrice(serviceDTO.getServicePrice(), userId);
		this.attachServiceSubStepPrice(serviceDTO.getSubStepPrice());
		// with lifeng's approved module.
		Integer requestId = null;
		if (objectId != null) {
			ApproveRequest appRequest = approveRequestDao.countApproveRequest(
					RequestApproveType.Service.name(), objectId);
			if (appRequest != null) {
				requestId = appRequest.getRequestId();
				approveRequestDao.getSession().evict(appRequest);
			}
		}
		serviceApproved(serviceDTO, userId, service, objectId, requestId);
		if (serviceDTO.getServicePriceApproveList() != null
				&& !serviceDTO.getServicePriceApproveList().isEmpty()) {
			for (ServicePriceDTO dto : serviceDTO.getServicePriceApproveList()) {
				Integer priceObjectId = dto.getPriceId();
				if (priceObjectId != null) {
					servicePriceApproved(serviceDTO, userId, dto,
							priceObjectId, requestId);
				}

			}
		}
		if (serviceDTO.getServiceSubPriceApproveList() != null
				&& !serviceDTO.getServiceSubPriceApproveList().isEmpty()) {
			for (ServicePriceDTO dto : serviceDTO
					.getServiceSubPriceApproveList()) {
				Integer priceObjectId = dto.getPriceId();
				if (priceObjectId != null) {
					servicePriceApproved(serviceDTO, userId, dto,
							priceObjectId, requestId);
				}

			}
		}
		return service;

	}

	/*
	 * del service 批量修改status 为INACTIVE
	 */
	public void delServiceListApproved(String[] objectIds, Integer userId,
			String approved, String approvedReason) {
		for (int i = 0; i < objectIds.length; i++) {
			ServiceDTO serviceDTO = new ServiceDTO();
			serviceDTO.setStatusApprove(approved);
			serviceDTO.setStatusReason(approvedReason);
			com.genscript.gsscm.serv.entity.Service service = this.serviceDao
					.getById(Integer.valueOf(objectIds[i]));
			Integer requestId = null;
			this.serviceApproved(serviceDTO, userId, service,
					Integer.valueOf(objectIds[i]), requestId);
			/*
			 * Integer requestId = null; Integer objectId =
			 * Integer.valueOf(objectIds[i]); boolean statusAppr =
			 * this.checkPropertyApproved
			 * (objectId,RequestApproveType.status.name
			 * (),RequestApproveType.Service.name()); if(!statusAppr){
			 * ServiceDTO serviceDTO = this.getServDetail(objectId);
			 * com.genscript.gsscm.serv.entity.Service serv =
			 * this.dozer.map(serviceDTO,
			 * com.genscript.gsscm.serv.entity.Service.class);
			 * if(!serv.getStatus().equals(approved)){ if (objectId!= null) {
			 * ApproveRequest appRequest =
			 * approveRequestDao.countApproveRequest(
			 * RequestApproveType.Product.name(), objectId); if (appRequest !=
			 * null) { requestId = appRequest.getRequestId();
			 * approveRequestDao.getSession().evict(appRequest); } }
			 * serviceDTO.setStatusApprove(approved);
			 * serviceDTO.setStatusReason(approvedReason);
			 * serviceApproved(serviceDTO, userId, serv, objectId, requestId); }
			 * }
			 */
		}
	}

	private void servicePriceApproved(ServiceDTO serviceDTO, Integer userId,
			ServicePriceDTO servicePriceDTO, Integer objectId, Integer requestId) {
		if (servicePriceDTO.getServiceRuleGroupPriceApprove() != null
				|| servicePriceDTO.getServicePriceApprove() != null
				|| servicePriceDTO.getServiceSubPriceApprove() != null) {
			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail = new ApproveRequestDetail();
			if (servicePriceDTO.getServiceRuleGroupPriceApprove() != null) {
				approveRequestDetail
						.setColumnName(RequestApproveType.ServicePriceGroup
								.name());
				approveRequestDetail.setNewValue(servicePriceDTO
						.getServiceRuleGroupPriceApprove().toString());
			}
			if (servicePriceDTO.getServicePriceApprove() != null) {
				approveRequestDetail
						.setColumnName(RequestApproveType.ServicePrice.name());
				approveRequestDetail.setNewValue(servicePriceDTO
						.getServicePriceApprove().toString());
			}
			if (servicePriceDTO.getServiceSubPriceApprove() != null) {
				approveRequestDetail
						.setColumnName(RequestApproveType.SubServicePrice
								.name());
				approveRequestDetail.setNewValue(servicePriceDTO
						.getServiceSubPriceApprove().toString());
			}
			approveRequestDetail.setOldValue(servicePriceDTO
					.getServicePriceOldVal().toString());

			approveRequestDetail.setReason(servicePriceDTO
					.getServicePriceReason());
			approveRequest.getApproveRequestDetails().add(approveRequestDetail);

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(objectId);
			approveRequest.setTableName(RequestApproveType.ServicePrice.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(userId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	private void serviceApproved(ServiceDTO serviceDTO, Integer userId,
			com.genscript.gsscm.serv.entity.Service service, Integer objectId,
			Integer requestId) {
		if (serviceDTO.getNameApprove() != null
				|| serviceDTO.getStatusApprove() != null) {
			ApproveRequest approveRequest = new ApproveRequest();

			ApproveRequestDetail approveRequestDetail1, approveRequestDetail2;
			approveRequestDetail1 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "name");
			approveRequestDetailDao.getSession().evict(approveRequestDetail1);
			if (serviceDTO.getNameApprove() == null
					|| serviceDTO.getNameApprove().length() < 1) {
				if (approveRequestDetail1 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail1);
				}
			} else {
				if (approveRequestDetail1 == null) {
					approveRequestDetail1 = new ApproveRequestDetail();
				}
				approveRequestDetail1.setColumnName("name");
				approveRequestDetail1.setOldValue(service.getName());
				approveRequestDetail1.setNewValue(serviceDTO.getNameApprove());
				approveRequestDetail1.setReason(serviceDTO.getNameReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail1);
			}

			approveRequestDetail2 = approveRequestDetailDao
					.getUnapprovedRequest(requestId, "status");
			approveRequestDetailDao.getSession().evict(approveRequestDetail2);
			if (serviceDTO.getStatusApprove() == null
					|| serviceDTO.getStatusApprove().length() < 1) {
				if (approveRequestDetail2 != null) {
					approveRequest.getApproveRequestDetails().add(
							approveRequestDetail2);
				}
			} else {
				if (approveRequestDetail2 == null) {
					approveRequestDetail2 = new ApproveRequestDetail();
				}
				approveRequestDetail2.setColumnName("status");
				approveRequestDetail2.setOldValue(service.getStatus());
				approveRequestDetail2
						.setNewValue(serviceDTO.getStatusApprove());
				approveRequestDetail2.setReason(serviceDTO.getStatusReason());
				approveRequest.getApproveRequestDetails().add(
						approveRequestDetail2);
			}

			if (requestId != null) {
				approveRequest.setRequestId(requestId);
			} else {
				approveRequest.setRequestId(null);
			}
			approveRequest.setObjectId(objectId);
			approveRequest.setTableName(RequestApproveType.Service.name());
			approveRequest
					.setApproveStatus(RequestApproveStatusType.UNPROCESSED
							.name());
			approveRequest.setRequestedBy(userId);
			approveRequest.setRequestDate(new Date());
			approveRequestDao.save(approveRequest);
		}
	}

	/**
	 * 保存Service同时保存它的ShipCondition.
	 * 
	 * @param service
	 * @param shipCondition
	 * @param userId
	 */
	private void attachShipCondition(
			com.genscript.gsscm.serv.entity.Service service,
			ServiceShipCondition shipCondition, Integer userId) {
		if (shipCondition != null) {
			Date now = new Date();
			shipCondition.setServiceId(service.getServiceId());
			shipCondition.setCreatedBy(userId);
			shipCondition.setModifiedBy(userId);
			shipCondition.setCreationDate(now);
			shipCondition.setModifyDate(now);
			if (shipCondition.getShipWeightUom() == null) {
				shipCondition.setShipWeightUom("lbs");
			}
			this.serviceShipConditionDao.save(shipCondition);
		}
	}

	/**
	 * 保存Service的同时保存它的StorageCondition
	 * 
	 * @param service
	 * @param storageCondition
	 * @param userId
	 */
	private void attachStorageCondition(
			com.genscript.gsscm.serv.entity.Service service,
			ServiceStorageCondition storageCondition, Integer userId) {
		if (storageCondition != null) {
			Date now = new Date();
			storageCondition.setServiceId(service.getServiceId());
			storageCondition.setCreatedBy(userId);
			storageCondition.setModifiedBy(userId);
			storageCondition.setCreationDate(now);
			storageCondition.setModifyDate(now);
			this.serviceStorageConditionDao.save(storageCondition);
		}
	}

	/**
	 * 保存Service的同时保存intermediate list, 删除intermediate list.
	 * 
	 * @param service
	 * @param intmdList
	 * @param delIntmdIdList
	 * @param userId
	 */
	private void attachServiceIntmd(
			com.genscript.gsscm.serv.entity.Service service,
			List<ServiceIntermediateDTO> intmdList,
			List<Integer> delIntmdIdList, Integer userId) {
		if (delIntmdIdList != null && delIntmdIdList.get(0) != null) {
			this.serviceIntermediateDao.delIntmdList(delIntmdIdList);
		}
		if (intmdList == null || intmdList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ServiceIntermediateDTO dto : intmdList) {
			ServiceIntermediate intmd = this.dozer.map(dto,
					ServiceIntermediate.class);
			intmd.setServiceId(service.getServiceId());
			intmd.setModifyDate(now);
			intmd.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(intmd.getId()) == null) {
				intmd.setId(null);
				intmd.setCreatedBy(userId);
				intmd.setCreationDate(now);
			}
			this.serviceIntermediateDao.save(intmd);
		}
	}

	/**
	 * 保存service的同时保存Component list, 删除Component list.
	 * 
	 * @param service
	 * @param componentList
	 * @param delComIdList
	 * @param userId
	 */
	private void attachServiceComponent(
			com.genscript.gsscm.serv.entity.Service service,
			List<ServiceComponentDTO> componentList,
			List<Integer> delComIdList, Integer userId) {
		if (delComIdList != null && delComIdList.get(0) != null) {
			this.serviceComponentDao.delComponentList(delComIdList);
		}
		if (componentList == null || componentList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ServiceComponentDTO dto : componentList) {
			ServiceComponent intmd = this.dozer
					.map(dto, ServiceComponent.class);
			intmd.setServiceId(service.getServiceId());
			intmd.setModifyDate(now);
			intmd.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(intmd.getId()) == null) {
				intmd.setId(null);
				intmd.setCreatedBy(userId);
				intmd.setCreationDate(now);
			}
			this.serviceComponentDao.save(intmd);
		}
	}

	/**
	 * 保存service的同时保存RestrictShip list, 删除RestrictShip list.
	 * 
	 * @param service
	 * @param restrictShipList
	 * @param delRestrictShipIdList
	 * @param userId
	 */
	private void attachServiceRestrictShip(
			com.genscript.gsscm.serv.entity.Service service,
			List<ServiceRestrictShip> restrictShipList,
			List<Integer> delRestrictShipIdList, Integer userId) {
		if (delRestrictShipIdList != null && !delRestrictShipIdList.isEmpty()) {
			this.serviceRestrictShipDao.delShipList(delRestrictShipIdList);
		}
		if (restrictShipList == null || restrictShipList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ServiceRestrictShip ship : restrictShipList) {
			ship.setServiceId(service.getServiceId());
			ship.setModifyDate(now);
			ship.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(ship.getId()) == null) {
				ship.setId(null);
				ship.setCreatedBy(userId);
				ship.setCreationDate(now);
			}
			this.serviceRestrictShipDao.save(ship);
		}
	}

	/**
	 * 保存Service的同时保存ServiceSpecialPrice list, 删除ServiceSpecialPrice list.
	 * 
	 * @param service
	 * @param specialPriceList
	 * @param delIdList
	 * @param userId
	 */
	private void attachServiceSpecialPrice(
			com.genscript.gsscm.serv.entity.Service service,
			List<ServiceSpecialPrice> specialPriceList,
			List<Integer> delIdList, Integer userId) {
		if (delIdList != null && delIdList.get(0) != null) {
			this.serviceSpecialPriceDao.delSpecialPriceList(delIdList);
		}
		if (specialPriceList == null || specialPriceList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ServiceSpecialPrice specialPrice : specialPriceList) {
			specialPrice.setServiceId(service.getServiceId());
			specialPrice.setModifyDate(now);
			specialPrice.setModifiedBy(userId);
			if (SoapUtil.getIntegerFromSOAP(specialPrice.getPriceId()) == null) {
				specialPrice.setPriceId(null);
				specialPrice.setCreatedBy(userId);
				specialPrice.setCreationDate(now);
			}
			this.serviceSpecialPriceDao.save(specialPrice);
		}
	}

	/**
	 * 保存Service的同时保存ServiceRelation list.
	 * 
	 * @param service
	 * @param serviceRelationList
	 * @param userId
	 */
	private void attachServiceRelation(
			com.genscript.gsscm.serv.entity.Service service,
			List<ServiceRelationDTO> serviceRelationList, Integer userId) {
		if (serviceRelationList == null || serviceRelationList.get(0) == null) {
			return;
		}
		Date now = new Date();
		for (ServiceRelationDTO dto : serviceRelationList) {
			ServiceRelation vp = this.dozer.map(dto, ServiceRelation.class);
			vp.setServiceId(service.getServiceId());
			vp.setModifyDate(now);
			vp.setModifiedBy(userId);
			vp.setCreatedBy(userId);
			vp.setCreationDate(now);
			this.serviceRelationDao.save(vp);
		}
	}

	/**
	 * 在保存Service的同时保存或更新它关联的RoyaltyService.
	 * 
	 * @param service
	 * @param royaltyDTO
	 * @param userId
	 */
	private void attachServiceRoyalty(
			com.genscript.gsscm.serv.entity.Service service,
			RoyaltyServiceDTO royaltyDTO, Integer userId) {
		if (royaltyDTO == null || royaltyDTO.getRoyaltyId() == null) {
			return;
		}
		RoyaltyService royaltyService = this.dozer.map(royaltyDTO,
				RoyaltyService.class);
		royaltyService.setCatalogNo(service.getCatalogNo());
		royaltyService.setCreatedBy(userId);
		royaltyService.setModifiedBy(userId);
		royaltyService.setCreationDate(new Date());
		royaltyService.setModifyDate(new Date());
		this.royaltyServiceDao.save(royaltyService);
	}

	/**
	 * 在保存Service的同时保存或更新它关联的details subStep.
	 * 
	 * @param service
	 * @param subStepList
	 * @param userId
	 */
	private void attachServiceSubStep(
			com.genscript.gsscm.serv.entity.Service service,
			List<ServiceSubSteps> subStepList, List<Integer> delSubSteps,
			Integer userId) {
		if (subStepList != null && !subStepList.isEmpty()) {
			for (ServiceSubSteps subStep : subStepList) {
				subStep.setServiceId(service.getServiceId());
				this.serviceSubStepsDao.save(subStep);
			}
		}
		if (delSubSteps != null && !delSubSteps.isEmpty()) {
			this.serviceSubStepsDao.delSubStepsById(delSubSteps);
		}
	}

	/*
	 * 在保存service 的同时保存或更新它的subStepPrice;
	 */
	private void attachServiceSubStepPrice(
			List<ServiceSubStepPrice> subStepPriceList) {
		if (subStepPriceList != null && !subStepPriceList.isEmpty()) {
			for (ServiceSubStepPrice subStepPrice : subStepPriceList) {
				this.serviceSubStepPriceDao.save(subStepPrice);
			}
		}
	}

	/*
	 * 在保存service 的同时保存或更新它的servicePrice;
	 */
	private void attachServicePrice(List<ServicePrice> servicePriceList,
			Integer userId) {
		if (servicePriceList != null && !servicePriceList.isEmpty()) {
			Date now = new Date();
			for (ServicePrice sp : servicePriceList) {
				if (sp.getCreatedBy() == null) {
					sp.setCreatedBy(userId);
					sp.setCreationDate(now);
				}
				sp.setModifiedBy(userId);
				sp.setModifyDate(now);
				this.servicePriceDao.save(sp);
			}
		}
	}

	/**
	 * 批量删除service. 同时成功或失败.
	 * 
	 * @param delServiceListId
	 */
	@Transactional(readOnly = true)
	public void delServiceList(List<Integer> delServiceListId) {

	}

	/**
	 * 获得一个service的RestrictShip(禁运) list
	 * 
	 * @param serviceId
	 * @return
	 */
	@Transactional()
	public List<ServiceRestrictShipDTO> getServiceRestrictShipList(
			Integer serviceId) {
		List<ServiceRestrictShipDTO> dtoList = new ArrayList<ServiceRestrictShipDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQI_serviceId", serviceId);
		filterList.add(filter);
		List<ServiceRestrictShip> dbList = this.restrictShipDao
				.find(filterList);
		Map<String, String> countryMap = new HashMap<String, String>();
		Map<String, String> stateMap = new HashMap<String, String>();
		if (dbList != null) {
			for (ServiceRestrictShip dbShip : dbList) {
				ServiceRestrictShipDTO dto = this.dozer.map(dbShip,
						ServiceRestrictShipDTO.class);
				if (countryMap.containsKey(dbShip.getCountry())) {
					dto.setCountryName(countryMap.get(dbShip.getCountry()));
				} else {
					PbCountry country = this.pbCountryDao.findUniqueBy(
							"countryCode", dbShip.getCountry());
					if (country != null) {
						dto.setCountryName(country.getName());
						countryMap.put(country.getCountryCode(),
								country.getName());
					}
				}
				if (stateMap.containsKey(dbShip.getState())) {
					dto.setStateName(stateMap.get(dbShip.getState()));
				} else {
					PbState state = this.pbStateDao.findUniqueBy("stateCode",
							dbShip.getState());
					if (state != null) {
						dto.setStateName(state.getName());
						stateMap.put(state.getStateCode(), state.getName());
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 获得一个Service的库存(Inventory Stock)统计信息.
	 * 
	 * @param serviceId
	 * @return
	 */
	public ServiceStockStatDTO getServiceStockStat(Integer serviceId) {
		ServiceStockStatDTO dto = new ServiceStockStatDTO();
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.getById(serviceId);
		String catalogNo = service.getCatalogNo();
		// 从Stock表中获得Stock Total.
		Long stockTotal = this.stockDao.getServiceStockTotal(catalogNo);
		if (stockTotal != null) {
			dto.setStockTotal(stockTotal.intValue());
		}
		Long commitedTotal = this.purchaseOrderItemDao
				.getCommitedItemTotal(catalogNo);
		if (commitedTotal != null) {
			dto.setCommitedTotal(commitedTotal.intValue());
		}
		Long backOrderTotal = this.orderItemDao.getBackOrderTotal(catalogNo);
		if (backOrderTotal != null) {
			dto.setBackOrderTotal(backOrderTotal.intValue());
		}
		Long unProcessedTotal = this.orderReturnItemDao
				.getUnprocessedTotal(catalogNo);
		if (unProcessedTotal != null) {
			dto.setUnProcessedTotal(unProcessedTotal.intValue());
		}
		return dto;
	}

	public Page<ServiceItemBean> searchAssociatedItem(
			final Page<ServiceItemBean> page,
			final Map<String, String> filterParamMap, final Integer serviceId) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return serviceItemBeanDao.findAssociatedItemPage(page, filterList,
				serviceId);
	}

	/**
	 * 获得一个ServiceReleation的详细信息.
	 * 
	 * @param relateId
	 * @return
	 */
	@Transactional(readOnly = true)
	public ServiceRelationDTO getServiceRelationDetail(Integer relateId) {
		ServiceRelationDTO dto = null;
		ServiceRelation ServRlt = this.serviceRelationDao.getById(relateId);
		if (ServRlt != null) {
			dto = this.dozer.map(ServRlt, ServiceRelationDTO.class);
			com.genscript.gsscm.serv.entity.Service serv = this.serviceDao
					.getById(ServRlt.getRltServiceId());
			dto.setRltName(serv.getName());
			dto.setRltCatalogNo(serv.getCatalogNo());
		}
		return dto;
	}

	/**
	 * 获得一个产品的中间产品列表.
	 * 
	 * @param page
	 * @param serviceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ServiceIntermediateDTO> getServiceIntermediateList(
			Page<ServiceIntermediate> page, Integer serviceId) {
		List<ServiceIntermediateDTO> dtoList = new ArrayList<ServiceIntermediateDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_serviceId",
				serviceId);
		filterList.add(orgFilter);
		page = this.serviceIntermediateDao.findPage(page, filterList);
		List<ServiceIntermediate> resultList = page.getResult();
		if (resultList != null) {
			for (ServiceIntermediate intermediate : resultList) {
				ServiceIntermediateDTO dto = dozer.map(intermediate,
						ServiceIntermediateDTO.class);
				com.genscript.gsscm.serv.entity.Service temp = this.serviceDao
						.findUniqueBy("catalogNo",
								intermediate.getIntmdCatalogNo());
				if (temp != null) {
					dto.setItem(temp.getName());
					dto.setLeadTime(temp.getLeadTime());
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<ServiceIntermediateDTO> dtoPage = this.dozer.map(page, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	/**
	 * 根据intmdCatalogNo获得父服务的serviceid
	 * 
	 * @param intmdCatalogNo
	 * @return
	 * @author zouyulu
	 */
	public Integer getIntermediateParentServiceId(String intmdCatalogNo) {
		if (intmdCatalogNo == null) {
			return null;
		}
		List<ServiceIntermediate> result = this.serviceIntermediateDao.findBy(
				"intmdCatalogNo", intmdCatalogNo);
		if (result != null && result.size() > 0) {
			ServiceIntermediate result0 = result.get(0);
			return result0.getServiceId();
		}
		return null;
	}

	/**
	 * 查询可以自动添加为子服务的集合
	 * 
	 * @author Zhang Yong
	 * @param intmdCatalogNo
	 * @param requiredFlag
	 * @return
	 */
	public List<ServiceIntermediate> getIntermediate(String intmdCatalogNo,
			String requiredFlag) {
		if (StringUtils.isBlank(intmdCatalogNo)) {
			return null;
		}
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.getServiceByCatalogNo(intmdCatalogNo);
		if (service == null) {
			return null;
		}
		return serviceIntermediateDao.getIntermediate(service.getServiceId(),
				requiredFlag);
	}

	/**
	 * 根据serviceId获得LeadTime
	 * 
	 * @param serviceId
	 * @return
	 */
	public Integer getLeadTimeByServiceId(Integer serviceId) {
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.get(serviceId);
		if (service != null) {
			return service.getLeadTime();
		} else {
			return null;
		}
	}

	/**
	 * 获得一个产品的中间所有产品列表. 用于service details
	 * (Protein/Bioprocess/Pharmaceutical/Antibody Drug几种类型);
	 * 
	 * @param serviceId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ServiceIntermediateDTO> getServiceIntermediateAllList(
			Integer serviceId) {
		List<ServiceIntermediateDTO> dtoList = new ArrayList<ServiceIntermediateDTO>();
		List<ServiceIntermediate> breakList = this.serviceIntermediateDao
				.getIntmdByServiceId(serviceId);
		if (breakList != null && !breakList.isEmpty()) {
			for (ServiceIntermediate intermediate : breakList) {
				ServiceIntermediateDTO dto = dozer.map(intermediate,
						ServiceIntermediateDTO.class);
				com.genscript.gsscm.serv.entity.Service temp = this.serviceDao
						.findUniqueBy("catalogNo",
								intermediate.getIntmdCatalogNo());
				if (temp != null) {
					dto.setItem(temp.getName());
					List<ServiceSubSteps> subSteps = this.serviceSubStepsDao
							.findBy("serviceId", temp.getServiceId());
					if (subSteps != null && !subSteps.isEmpty()) {
						dto.setStepList(subSteps);
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Transactional(readOnly = true)
	public List<ServiceIntermediateDTO> getServiceIntermediateAllList(
			String catalogNo, String catalogId, Integer serviceId) {
		List<ServiceIntermediateDTO> dtoList = new ArrayList<ServiceIntermediateDTO>();
		List<ServiceIntermediate> breakList = this.serviceIntermediateDao
				.getIntmdByServiceId(serviceId);
		if (breakList != null && !breakList.isEmpty()) {
			for (ServiceIntermediate intermediate : breakList) {
				ServiceIntermediateDTO dto = dozer.map(intermediate,
						ServiceIntermediateDTO.class);
				com.genscript.gsscm.serv.entity.Service temp = this.serviceDao
						.findUniqueBy("catalogNo",
								intermediate.getIntmdCatalogNo());
				if (temp != null) {
					dto.setItem(temp.getName());
					List<ServiceSubStepsDTO> subStepDtolist = getServiceSubStepsList(
							catalogNo, catalogId, temp);
					if (subStepDtolist != null && !subStepDtolist.isEmpty()) {
						dto.setStepDtoList(subStepDtolist);
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 获得一个产品的中间所有产品列表. 用于service details
	 * (Protein/Bioprocess/Pharmaceutical/Antibody Drug几种类型);
	 * 
	 * @param catalogNo
	 * @return
	 * @author zouyulu
	 */
	@Transactional(readOnly = true)
	public List<ServiceIntermediateDTO> getServiceIntermediateAllList(
			String catalogNo) {
		List<ServiceIntermediateDTO> dtoList = new ArrayList<ServiceIntermediateDTO>();
		com.genscript.gsscm.serv.entity.Service ser = this.serviceDao
				.getServiceByCatalogNo(catalogNo);
		if (ser == null) {
			return dtoList;
		} else {
			Integer serviceId = ser.getServiceId();
			return this.getServiceIntermediateAllList(serviceId);
		}
	}

	@Transactional(readOnly = true)
	public List<ServiceIntermediateDTO> getServiceIntermediateAllList(
			String catalogNo, String catalogId) {
		List<ServiceIntermediateDTO> dtoList = new ArrayList<ServiceIntermediateDTO>();
		com.genscript.gsscm.serv.entity.Service ser = this.serviceDao
				.getServiceByCatalogNo(catalogNo);
		if (ser == null) {
			return dtoList;
		} else {
			Integer serviceId = ser.getServiceId();
			return this.getServiceIntermediateAllList(catalogNo, catalogId,
					serviceId);
		}
	}

	/**
	 * 获得一个产品的sub_steps列表.
	 * 
	 * @param serviceId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ServiceSubSteps> getServiceSubStepsList(Integer serviceId) {
		return this.serviceSubStepsDao.getSubStepListBySerivceId(serviceId);
	}

	/**
	 * 获得一个产品的sub_steps列表.
	 * 
	 * @param catalogNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ServiceSubSteps> getServiceSubStepsList(String catalogNo) {
		List<ServiceSubSteps> list = new ArrayList<ServiceSubSteps>();
		com.genscript.gsscm.serv.entity.Service ser = this.serviceDao
				.getServiceByCatalogNo(catalogNo);
		Integer serviceId = null;
		if (ser == null) {
			return list;
		} else {
			serviceId = ser.getServiceId();
		}
		return this.serviceSubStepsDao.getSubStepListBySerivceId(serviceId);
	}

	/**
	 * 查询price和cost
	 * 待以后优化
	 * @author Zhang Yong
	 * @param catalogNo
	 * @param catalogId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<ServiceSubStepsDTO> getServiceSubStepsList(String catalogNo,
			String catalogId, com.genscript.gsscm.serv.entity.Service ser) {
		List<ServiceSubStepsDTO> list = new ArrayList<ServiceSubStepsDTO>();
		if (ser == null) {
			ser = this.serviceDao.getServiceByCatalogNo(catalogNo);
		}
		Integer serviceId = null;
		if (ser == null) {
			return list;
		} else {
			serviceId = ser.getServiceId();
		}
		List<ServiceSubStepPrice> subStepPricelist = serviceSubStepPriceDao
				.getPrice(serviceId, catalogId);
		if (subStepPricelist == null) {
			return list;
		}
		Map<String, Double> subStepPriceMap = new HashMap<String, Double>();
		Iterator<ServiceSubStepPrice> subStepPriceItem = subStepPricelist
				.iterator();
		while (subStepPriceItem.hasNext()) {
			ServiceSubStepPrice subStepPrice = subStepPriceItem.next();
			subStepPriceMap.put(subStepPrice.getStepId() + "",
					subStepPrice.getRetailPrice());
		}
		List<ServiceSubSteps> subSteplist = this.serviceSubStepsDao
				.getSubStepListBySerivceId(serviceId);
		if (subSteplist == null) {
			return list;
		}
		Iterator<ServiceSubSteps> subStepItem = subSteplist.iterator();
		while (subStepItem.hasNext()) {
			ServiceSubSteps subStep = subStepItem.next();
			ServiceSubStepsDTO subStepDto = dozer.map(subStep,
					ServiceSubStepsDTO.class);
			if (subStepPriceMap.containsKey(subStep.getStepId() + "")) {
				subStepDto.setRetailPrice(subStepPriceMap.get(subStep
						.getStepId() + ""));
				list.add(subStepDto);
			}
		}
		return list;
	}
	
	/**
	 * 查询pkgService item的Price、Cost
     * @author Zhang Yong
     * add date 2011-10-25
	 * @param catalogNo
	 * @param catalogId
	 * @param name
	 * @param seqFlag
	 * @return
	 */
	public BigDecimal[] getPriceCost (String catalogNo, String catalogId, String name, String seqFlag) {
		BigDecimal[] priceCost = serviceSubStepPriceDao.getPriceCost(catalogNo, catalogId, name, seqFlag);
		return priceCost;
	}

	/**
	 * 获得一个产品的组件子产品列表.
	 * 
	 * @param page
	 * @param serviceId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ServiceComponentDTO> getServiceComponentList(
			Page<ServiceComponent> page, Integer serviceId) {
		List<ServiceComponentDTO> dtoList = new ArrayList<ServiceComponentDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter orgFilter = new PropertyFilter("EQI_serviceId",
				serviceId);
		filterList.add(orgFilter);
		page = this.serviceComponentDao.findPage(page, filterList);
		List<ServiceComponent> resultList = page.getResult();
		if (resultList != null) {
			for (ServiceComponent component : resultList) {
				ServiceComponentDTO dto = dozer.map(component,
						ServiceComponentDTO.class);
				com.genscript.gsscm.serv.entity.Service temp = this.serviceDao
						.findUniqueBy("catalogNo", component.getCpntCatalogNo());
				if (temp != null) {
					dto.setItem(temp.getName());
					dto.setLeadTime(temp.getLeadTime());
					/*
					 * ServiceStdPriceBean stdPrice = this
					 * .getServicePrice(serviceId); if (stdPrice != null) {
					 * dto.setPrice(stdPrice.getStandardPrice());
					 * dto.setSymbol(stdPrice.getSymbol()); }
					 */
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		Page<ServiceComponentDTO> dtoPage = this.dozer.map(page, Page.class);
		dtoPage.setResult(dtoList);
		return dtoPage;
	}

	/**
	 * 根据name, catalogNo查找StandardPrice list.
	 * 
	 * @param catalogNo
	 *            ， name ,catalogNoList
	 * @param name
	 * @return
	 */
	public List<com.genscript.gsscm.serv.entity.Service> searchServiceBreakDownList(
			String catalogNo, String name, List<String> catalogNoList) {
		return serviceDao.searchServiceBreakDownList(catalogNo, name,
				catalogNoList);
	}

	/**
	 * 获得一个Service的RoyaltyService(版权税)信息.
	 * 
	 * @param catalogNo
	 * @return
	 */
	public RoyaltyServiceDTO getRoyaltyService(String catalogNo) {
		RoyaltyServiceDTO dto = null;
		RoyaltyService royalty = this.royaltyServiceDao
				.getRoyaltyServiceByCataloNo(catalogNo);
		if (royalty != null) {
			dto = this.dozer.map(royalty, RoyaltyServiceDTO.class);
			dto.setRoyaltyName(royaltyDao.getById(royalty.getRoyaltyId())
					.getName());
		}
		return dto;
	}

	private void attachSupplier(
			com.genscript.gsscm.serv.entity.Service pdtService,
			List<VendorServiceDTO> supplierList, List<Integer> delIdList,
			Integer userId) {
		if (supplierList == null || supplierList.get(0) == null) {

		} else {
			Date now = new Date();
			for (VendorServiceDTO dto : supplierList) {
				VendorServiceEntity vp = this.dozer.map(dto,
						VendorServiceEntity.class);
				vp.setCatalogNo(pdtService.getCatalogNo());
				vp.setModifyDate(now);
				vp.setModifiedBy(userId);
				if (SoapUtil.getIntegerFromSOAP(vp.getId()) == null) {
					vp.setId(null);
					vp.setCreatedBy(userId);
					vp.setCreationDate(now);
				}
				this.vendorServiceDao.save(vp);
			}
		}
		if (delIdList != null && delIdList.get(0) != null) {
			this.vendorServiceDao.delSupplierList(delIdList);
		}
	}

	/**
	 * 获得一个Service产品的所有Supplier(Vendor).
	 * 
	 * @param catalogNo
	 * @return
	 */
	public List<VendorServiceDTO> getSupplierList(String catalogNo) {
		List<VendorServiceDTO> dtoList = new ArrayList<VendorServiceDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_catalogNo", catalogNo);
		filterList.add(filter);
		List<VendorServiceEntity> dbList = this.vendorServiceDao
				.find(filterList);
		if (dbList != null) {
			for (VendorServiceEntity src : dbList) {
				VendorServiceDTO dto = this.dozer.map(src,
						VendorServiceDTO.class);
				Vendor vendorTemp = this.vendorDao.getById(src.getVendorNo());
				dto.setVendorName(vendorTemp.getVendorName());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/*
	 * 获取subStep详细信息
	 * 
	 * @param stepId;
	 */
	public ServiceSubSteps getSubStepDetail(Integer id) {
		return this.serviceSubStepsDao.getById(id);
	}

	public SubStepPriceDTO searchCostPrice(Integer subStepId, String catalogId) {
		ServiceSubSteps serviceSubStep = this.serviceSubStepsDao
				.getById(subStepId);
		ServiceSubStepPrice price = this.serviceSubStepPriceDao
				.getServiceSubStepPriceByCatalogIdServiceId(catalogId,
						subStepId);
		SubStepPriceDTO dto = new SubStepPriceDTO();
		if (price != null) {
			dto.setCost(serviceSubStep.getCost());
			dto.setPrice(price.getRetailPrice());
		}
		return dto;
	}

	/*
	 * 保存subStep信息
	 * 
	 * @param subStep;
	 */
	public ServiceSubSteps saveSubStep(ServiceSubSteps subStep) {
		this.serviceSubStepsDao.save(subStep);
		return subStep;
	}

	/*
	 * 获取所有supplies信息。
	 */
	public List<VendorDTO> getAllSuppliesList(String name) {
		List<VendorDTO> dtoList = new ArrayList<VendorDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter1 = new PropertyFilter("EQS_status", "ACTIVE");
		if (name != null) {
			PropertyFilter filter2 = new PropertyFilter("LIKES_vendorName",
					name);
			filterList.add(filter2);
		}
		filterList.add(filter1);

		List<Vendor> dbList = vendorDao.find(filterList);
		if (dbList != null) {
			for (Vendor vendor : dbList) {
				VendorDTO dto = this.dozer.map(vendor, VendorDTO.class);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/*
	 * 获得service vendor_supplier detail
	 */
	public VendorServiceEntity getVendorServiceDetail(Integer id) {
		VendorServiceEntity vendorService = this.vendorServiceDao.getById(id);
		return vendorService;
	}

	/*
	 * 获得一个Service的各类销售统计情况.
	 */
	public ProductServiceSaleDTO getServiceSale(String catalogNo,
			Date fromDate, Date toDate) throws ParseException {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(fromDate);
		String toDateStr = dateFmt.format(toDate);
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDateS = dateFmt.parse(fromDateStr);
		return orderItemDao.getProductSale(catalogNo, fromDateS, dEndDate,
				QuoteItemType.SERVICE.value());
	}

	/*
	 * 获取Sales Person(s) Selling this Service
	 */
	public List<SalesRankingDTO> getSalesRanking(String catalogNo, Integer top,
			Date fromDate, Date toDate) {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(fromDate);
		String toDateStr = dateFmt.format(toDate);
		return orderItemDao.getSalesRanking(catalogNo, top, fromDateStr,
				toDateStr, QuoteItemType.SERVICE.value());
	}

	public List<AnalysisReport> getServSalesReport(
			ServiceReportSrchDTO srchDTO, String salesPeriodBasedOn)
			throws ParseException {
		List<AnalysisReport> dtoList = new ArrayList<AnalysisReport>();
		/*
		 * PdtSalesTotal pdtSalesTotal = null; List<PdtSalesReport> gridDataList
		 * = new ArrayList<PdtSalesReport>();
		 */
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		// System.out.println(srchDTO.getBeginDate());
		String fromDateStr = dateFmt.format(srchDTO.getBeginDate());
		String toDateStr = dateFmt.format(srchDTO.getEndDate());
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDate = dateFmt.parse(fromDateStr);
		String catalogNo = srchDTO.getCatalogNO();
		int period = srchDTO.getPeriod();
		Date toDate = this.getToDate(srchDTO);
		boolean bLoop = true;
		for (; bLoop;) {
			String tempFromDate = dateFmt.format(fromDate);
			String tempToDate = dateFmt.format(toDate);
			// 实际发生到数据库的数据要比显示为DTO的大一天， 否则取不到<=后面的数据.
			String actToDate = dateFmt.format(DateUtils.defineDayBefore2Object(
					toDate, 1, DateUtils.C_DATE_PATTON_DEFAULT, new Date()));
			if (tempToDate.equals(srchDTO.getEndDate())) {
				bLoop = false;
				actToDate = tempToDate;
			} else if (!toDate.before(dEndDate)) {
				bLoop = false;
				tempToDate = toDateStr;
				actToDate = tempToDate;
			}
			AnalysisReport report = new AnalysisReport();
			report.setFromDate(tempFromDate);
			report.setToDate(tempToDate);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("catalogNo", catalogNo);
			map.put("fromDate", dateFmt.parse(tempFromDate));
			map.put("toDate", dateFmt.parse(actToDate));
			map.put("type", QuoteItemType.SERVICE.value());
			System.out.println(salesPeriodBasedOn);
			if (salesPeriodBasedOn.equals("netSales")) {
				report.setVisit(this.orderItemDao.getNetSales(map).longValue());
			} else if (salesPeriodBasedOn.equals("grossSales")) {
				report.setVisit(this.orderItemDao.getTotalSales(map)
						.longValue());
			} else if (salesPeriodBasedOn.equals("lossOnReturn")) {
				report.setVisit(this.orderItemDao.getLossOnReturn(map)
						.longValue());
			} else if (salesPeriodBasedOn.equals("netUnitsSold")) {
				report.setVisit(this.orderItemDao.getNetUnitsSold(map));
			} else if (salesPeriodBasedOn.equals("grossUnitsSold")) {
				report.setVisit(this.orderItemDao.getGrossUnitsSold(map));
			} else if (salesPeriodBasedOn.equals("unitsReturned")) {
				report.setVisit(this.orderItemDao.getUnitsReturned(map));
			}
			// report.setRefered(accessAnalysisDao.getAnalysisRefer(custNo,
			// tempFromDate, actToDate));
			// report.setSearching(accessAnalysisDao.getAnalysisSearching(custNo,
			// tempFromDate, actToDate));
			// report.setPageView(accessAnalysisDao.getAnalysisPageViewed(custNo,
			// tempFromDate, actToDate));
			// report.setAvgViewTime(accessAnalysisDao.getAnalysisAvgPageStays(
			// custNo, tempFromDate, actToDate));
			dtoList.add(report);
			// 下次循环从本次截止日期的后一天开始.
			fromDate = DateUtils.defineDayBefore2Object(toDate, 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			if (period == 1) {
				toDate = DateUtils.defineDayBefore2Object(toDate, 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			} else if (period == 7) {// by week
				toDate = DateUtils.getWeekEndDay(fromDate);
			} else if (period == 30) {// by month
				toDate = DateUtils.getMonthEndDay(fromDate);
			} else if (period == 90) {// by quarter
				toDate = DateUtils.getQuarterEndDay(fromDate);
			} else if (period == 365) {// by year
				toDate = DateUtils.getYearEndDay(fromDate);
			} else {
				toDate = DateUtils.defineDayBefore2Object(toDate, period - 1,
						DateUtils.C_DATE_PATTON_DEFAULT, new Date());
			}
		}
		return dtoList;
	}

	/**
	 * 获得第一次循环的截止日期.
	 * 
	 * @param srchDTO
	 * @return
	 * @throws ParseException
	 */
	private Date getToDate(final ServiceReportSrchDTO srchDTO)
			throws ParseException {
		Date toDate = null;
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = dateFmt.format(srchDTO.getBeginDate());
		String toDateStr = dateFmt.format(srchDTO.getEndDate());
		Date dEndDate = dateFmt.parse(toDateStr);
		Date fromDate = dateFmt.parse(fromDateStr);
		int period = srchDTO.getPeriod();
		if (period == 1) {
			toDate = fromDate;
		} else if (period == 7) {// by week
			toDate = DateUtils.getWeekEndDay(fromDate);
		} else if (period == 30) {// by month
			toDate = DateUtils.getMonthEndDay(fromDate);
		} else if (period == 90) {// by quarter
			toDate = DateUtils.getQuarterEndDay(fromDate);
		} else if (period == 365) {// by year
			toDate = DateUtils.getYearEndDay(fromDate);
		} else {
			toDate = DateUtils.defineDayBefore2Object(fromDate, period - 1,
					DateUtils.C_DATE_PATTON_DEFAULT, new Date());
		}
		if (!toDate.before(dEndDate)) {
			toDate = dEndDate;
		}
		return toDate;
	}

	/**
	 * 获得所有的Service Class list.
	 * 
	 * @return
	 * @author wangsf
	 * @serialData 2010-10-19 13:42
	 */
	@Transactional(readOnly = true)
	public List<ServiceClass> getAllServiceClass() {
		return this.serviceClassDao.findAll(Page.ASC, "name");
	}

	/**
	 * 获得具体Service Class
	 * 
	 * @param clsId
	 * @return
	 * @author wangsf
	 * @serialData 2010-10-19 14:53
	 */
	public ServiceClass getPServiceClass(Integer clsId) {
		return this.serviceClassDao.get(clsId);
	}

	/*
	 * 获得service price 根据serviceId,categoryId,catalogId
	 */
	public ServicePrice getServicePriceByServiceIdAndCatalogId(
			String catalogId, Integer serviceId) {
		return this.servicePriceDao.getServicePriceByCatalogIdServiceId(
				catalogId, serviceId);
	}

	/*
	 * 获得service sub step 根据
	 */
	public ServiceSubStepPrice getSubStepPrice(String catalogId,
			Integer subStepId) {
		return this.serviceSubStepPriceDao
				.getServiceSubStepPriceByCatalogIdServiceId(catalogId,
						subStepId);
	}

	/*
	 * 获得service sub step
	 */
	public List<ServiceSubStepPrice> searchSubStepPrice(Integer subStepId) {
		return this.serviceSubStepPriceDao.findBy("stepId", subStepId);
	}

	/*
	 * 根据catalogNo 获得service
	 */
	public List<com.genscript.gsscm.serv.entity.Service> getServiceByCatalogNo(
			String catalogNo) {
		return this.serviceDao.findBy("catalogNo", catalogNo);
	}

	public Integer getServiceIdByCatalogNo(String catalogNo) {
		return this.serviceDao.getServiceIdByCatalogNo(catalogNo);
	}

	/*
	 * 根据catalogId 获得service category
	 */
	public List<ServiceCategory> getServiceCategoryByCatalogId(String catalogId) {
		return this.serviceCategoryDao.findBy("catalogId", catalogId);
	}

	/*
	 * 根据catalogId 获得service category
	 */
	public List<ServiceCategory> getServiceCategoryByCatalogId(
			String catalogId, Integer categoryId, Integer categoryLevel) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_catalogId", catalogId);
		filters.add(filter);
		PropertyFilter filter1 = new PropertyFilter("EQI_categoryLevel",
				categoryLevel);
		filters.add(filter1);
		if (categoryId != null) {
			PropertyFilter filter2 = new PropertyFilter("EQI_parentCatId",
					categoryId);
			filters.add(filter2);
		}
		return this.serviceCategoryDao.find(filters);
	}

	/**
	 * 查找一个Service的运输条件(ShipCondition)
	 * 
	 * @param catalogNo
	 * @return
	 * @author wangsf
	 * @since 2010-12-16
	 */
	public ServiceShipCondition queryShipCondition(String catalogNo) {
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.getServiceByCatalogNo(catalogNo);
		ServiceShipCondition shipCondition = serviceShipConditionDao
				.get(service.getServiceId());
		return shipCondition;
	}

	/**
	 * 查找一个Service的存储条件(StorageCondition)
	 * 
	 * @param catalogNo
	 * @return
	 * @author wangsf
	 * @since 2010-12-16
	 */
	public ServiceStorageCondition queryStorageCondition(String catalogNo) {
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.getServiceByCatalogNo(catalogNo);
		ServiceStorageCondition storageCondition = serviceStorageConditionDao
				.get(service.getServiceId());
		return storageCondition;
	}

	@Transactional(readOnly = true)
	public Page<ServiceCategory> getAllServiceCategory(
			Page<ServiceCategory> page) {
		return serviceCategoryDao.findPage(page);
	}

	/**
	 * 判断service 是否是 gift service
	 * 
	 * @param catalogNo
	 * @return
	 */
	public boolean isGiftService(String catalogNo) {
		com.genscript.gsscm.serv.entity.Service service = this.serviceDao
				.getServiceByCatalogNo(catalogNo);
		if (service == null) {
			return false;
		}
		if ("Y".equalsIgnoreCase(service.getGiftFlag())) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 查询Service Category Reference 根据categoryId
	 */
	public List<ServiceReference> searchServiceReferenceByCategoryId(
			Integer categoryId) {
		return this.serviceReferenceDao.findBy("catagoryId", categoryId);
	}

	/*
	 * 查询product reference 详细信息；
	 */
	public ServiceReference getServiceRefereeceById(Integer id) {
		return this.serviceReferenceDao.getById(id);
	}

	/*
	 * 保存product reference
	 */
	private void attachServiceReference(List<ServiceReference> list,
			Integer categoryId, Integer userId, List<Integer> delReference) {
		Date now = new Date();
		if (list != null && !list.isEmpty()) {
			for (ServiceReference pr : list) {
				if (pr != null) {
					if (pr.getCreatedBy() == null) {
						pr.setCreatedBy(userId);
						pr.setCreationDate(now);
					}
					pr.setModifiedBy(userId);
					pr.setModifyDate(now);
					pr.setCatagoryId(categoryId);
					this.serviceReferenceDao.save(pr);
				}
			}

		}
		if (delReference != null && !delReference.isEmpty()) {
			this.serviceReferenceDao.delReferenceList(delReference);
		}

	}

	@Transactional(readOnly = true)
	public Page<ServiceOfServcategoryBean> getCatalogNoListByCategory(
			Page<ServiceOfServcategoryBean> page, String categoryNo) {
		return serviceOfServcategoryBeanDao.getCatalogNoListByCategory(page,
				categoryNo);
	}

	// ------------------add by zhou gang 2011 6 1
	Integer priceId = 0;

	public List<ServicePriceListBeanDTO> getServicePriceApproveList(
			Integer sessServiceId) {

		List<ServicePriceListBeanDTO> servicePriceListBeanDTOList = new ArrayList<ServicePriceListBeanDTO>();
		// 先根据当前sessServiceId 从service_sub_steps 表中 取得所有的stepid
		if (sessServiceId != null) {
			List<ServiceSubSteps> stepIds = serviceSubStepsDao
					.getSubStepListBySerivceId(sessServiceId);
			List<Integer> priceIds = new ArrayList<Integer>();
			// 根据stepid 从service_sub_steps_price 表中取得所有的priceId
			if (stepIds.size() > 0) {
				for (int i = 0; i < stepIds.size(); i++) {
					if (stepIds.get(i).getStepId() != null) {
						ServiceSubStepPrice ssp = serviceSubStepPriceDao
								.getPriceBystepsId(stepIds.get(i).getStepId());
						if (ssp != null) {
							priceId = serviceSubStepPriceDao.getPriceBystepsId(
									stepIds.get(i).getStepId()).getPriceId();
							if (priceId != null) {
								priceIds.add(priceId);
							}
						}
					}
				}
				System.out.println(priceIds.size());
				ServiceSubStepPrice serviceSubStepPrice = new ServiceSubStepPrice();
				ServicePriceListBeanDTO servicePriceListBeanDTO = new ServicePriceListBeanDTO();
				if (priceIds.size() > 0) {
					for (int i = 0; i < priceIds.size(); i++) {
						List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
								.getApprovedRequest(priceIds.get(i),
										RequestApproveType.ServicePrice.name());
						serviceSubStepPrice = serviceSubStepPriceDao
								.getObjectByID(priceIds.get(i));
						if (serviceSubStepPrice != null) {
							if (approveRequestBeanList != null
									&& approveRequestBeanList.size() > 0) {
								for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
									if (approveRequestBean.getColumnName()
											.equals("SubServicePrice")) {
										servicePriceListBeanDTO = dozer.map(
												serviceSubStepPrice,
												ServicePriceListBeanDTO.class);
										servicePriceListBeanDTO
												.setPriceNewVal(approveRequestBean
														.getNewValue());
										servicePriceListBeanDTO
												.setPriceOldVal(approveRequestBean
														.getOldValue());
										servicePriceListBeanDTO
												.setPriceReason(approveRequestBean
														.getReason());
										servicePriceListBeanDTO
												.setRequestId(approveRequestBean
														.getRequestId());
										servicePriceListBeanDTO
												.setRequestDate(approveRequestBean
														.getRequestDate());
										servicePriceListBeanDTO
												.setRequestedBy(approveRequestBean
														.getRequestedBy());
									}
								}
								servicePriceListBeanDTOList
										.add(servicePriceListBeanDTO);
							}
						}
					}
				}
			}
		}
		return servicePriceListBeanDTOList;
	}

	private Map<String, ServiceIntermediateDTO> breakdownList = new HashMap<String, ServiceIntermediateDTO>();

	@SuppressWarnings("unchecked")
	public List<ServicePriceListBeanDTO> getServicePriceApproveList2breakDown(
			Integer sessServiceId, String returnType, @SuppressWarnings("rawtypes") List breakDown) {
		// System.out.println("sessServiceId======" + sessServiceId);
		// System.out.println("return type======" + returnType);
		List<ServicePriceListBeanDTO> servicePriceListBeanDTOList = new ArrayList<ServicePriceListBeanDTO>();
		// 先根据sessServicID 获取到具体类型
		List<Integer> objectIdList = approveRequestBeanDao
				.getApprovedRequestId(RequestApproveType.ServicePrice.name());
		String catalogId = "";
		Integer serviceId = 0;
		if (sessServiceId != null) {
			if (returnType.equals("serviceId")) {
				if (objectIdList != null && objectIdList.size() > 0) {
					ServicePrice servicePrice = new ServicePrice();
					for (Integer objectId : objectIdList) {
						servicePrice = servicePriceDao.getObjectByID(objectId);
						if (servicePrice != null) {
							if (servicePrice.getCatalogId() != null
									&& servicePrice.getServiceId() != null) {
								catalogId = servicePrice.getCatalogId();
								serviceId = servicePrice.getServiceId();
								if (sessServiceId.equals(serviceId)) {
									ServiceOfServcategoryBean serviceOfServcategoryBean = serviceOfServcategoryBeanDao
											.getBeanByCatalog2(catalogId,
													serviceId);
									if (serviceOfServcategoryBean != null) {
										List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
												.getApprovedRequest(
														objectId,
														RequestApproveType.ServicePrice
																.name());
										if (approveRequestBeanList != null
												&& approveRequestBeanList
														.size() > 0) {
											for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
												ServicePriceListBeanDTO servicePriceListBeanDTO = new ServicePriceListBeanDTO();
												servicePriceListBeanDTO = dozer
														.map(serviceOfServcategoryBean,
																ServicePriceListBeanDTO.class);
												if (approveRequestBean
														.getColumnName()
														.equals("ServicePrice")) {
													servicePriceListBeanDTO
															.setPriceNewVal(approveRequestBean
																	.getNewValue());
													servicePriceListBeanDTO
															.setPriceOldVal(approveRequestBean
																	.getOldValue());
													servicePriceListBeanDTO
															.setPriceReason(approveRequestBean
																	.getReason());
													servicePriceListBeanDTO
															.setRequestId(approveRequestBean
																	.getRequestId());
													servicePriceListBeanDTO
															.setRequestDate(approveRequestBean
																	.getRequestDate());
													servicePriceListBeanDTO
															.setRequestedBy(approveRequestBean
																	.getRequestedBy());
												}
												servicePriceListBeanDTOList
														.add(servicePriceListBeanDTO);
											}

										}
									}
								}
							}
						}
					}
				}
			} else if (returnType.equals("breakDown")) {
				// System.out.println(sessServiceId);
				breakdownList = ((Map<String, ServiceIntermediateDTO>) SessionUtil
						.getRow(SessionPdtServ.BreakdownList.value(),
								String.valueOf(sessServiceId)));
				System.out
						.println(breakdownList.size() + "<<<<<<<<<<<<<<<<<< ");
				Collection<ServiceIntermediateDTO> c = breakdownList.values();
				@SuppressWarnings("rawtypes")
				Iterator it = c.iterator();
				for (; it.hasNext();) {
					ServiceIntermediateDTO serviceIntermediateDTO = (ServiceIntermediateDTO) it
							.next();
					// System.out.println("***getIntmdCatalogNo*****" +
					// serviceIntermediateDTO.getIntmdCatalogNo() + "********");
					int servicesId = serviceDao
							.getServiceIdByCatalogNo(serviceIntermediateDTO
									.getIntmdCatalogNo());
					// System.out.println("****servicesId****" + servicesId +
					// "********");
					List<ServicePrice> priceIDs = servicePriceDao
							.getServicePriceByServiceId(servicesId);
					if (priceIDs != null) {
						// System.out.println("****priceIDs****" +
						// priceIDs.size() + "********");
						if (priceIDs.size() > 0) {
							ServicePrice servicePrice = new ServicePrice();
							for (int i = 0; i < priceIDs.size(); i++) {
								servicePrice = (ServicePrice) priceIDs.get(i);
								Integer priceids = servicePrice.getPriceId();
								String catalogIdw = servicePrice.getCatalogId();
								ServiceOfServcategoryBean serviceOfServcategoryBean = serviceOfServcategoryBeanDao
										.getBeanByCatalog2(catalogIdw,
												servicePrice.getServiceId());
								if (serviceOfServcategoryBean != null) {
									List<ApproveRequestBean> approveRequestBeanList = approveRequestBeanDao
											.getApprovedRequest(
													priceids,
													RequestApproveType.ServicePrice
															.name());
									if (approveRequestBeanList != null
											&& approveRequestBeanList.size() > 0) {
										for (ApproveRequestBean approveRequestBean : approveRequestBeanList) {
											ServicePriceListBeanDTO servicePriceListBeanDTO = new ServicePriceListBeanDTO();
											servicePriceListBeanDTO = dozer
													.map(serviceOfServcategoryBean,
															ServicePriceListBeanDTO.class);
											if (approveRequestBean
													.getColumnName().equals(
															"ServicePrice")) {
												servicePriceListBeanDTO
														.setPriceNewVal(approveRequestBean
																.getNewValue());
												servicePriceListBeanDTO
														.setPriceOldVal(approveRequestBean
																.getOldValue());
												servicePriceListBeanDTO
														.setPriceReason(approveRequestBean
																.getReason());
												servicePriceListBeanDTO
														.setRequestId(approveRequestBean
																.getRequestId());
												servicePriceListBeanDTO
														.setRequestDate(approveRequestBean
																.getRequestDate());
												servicePriceListBeanDTO
														.setRequestedBy(approveRequestBean
																.getRequestedBy());
											}
											servicePriceListBeanDTOList
													.add(servicePriceListBeanDTO);
										}

									}
								}
							}
						}
					}

				}
			}
		}
		return servicePriceListBeanDTOList;
	}

	public ServiceItemBean getGeneServiceItem(String catalogNo) {
		return serviceItemBeanDao.getGeneServiceItem(catalogNo);
	}

	public Map<String, ServiceIntermediateDTO> getBreakdownList() {
		return breakdownList;
	}

	public void setBreakdownList(
			Map<String, ServiceIntermediateDTO> breakdownList) {
		this.breakdownList = breakdownList;
	}

	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public ServiceClassDao getServiceClassDao() {
		return serviceClassDao;
	}

	public void setServiceClassDao(ServiceClassDao serviceClassDao) {
		this.serviceClassDao = serviceClassDao;
	}

	public ServiceCategoryDao getServiceCategoryDao() {
		return serviceCategoryDao;
	}

	public void setServiceCategoryDao(ServiceCategoryDao serviceCategoryDao) {
		this.serviceCategoryDao = serviceCategoryDao;
	}

	public ServicePriceDao getServicePriceDao() {
		return servicePriceDao;
	}

	public void setServicePriceDao(ServicePriceDao servicePriceDao) {
		this.servicePriceDao = servicePriceDao;
	}

	public ServiceItemBeanDao getServiceItemBeanDao() {
		return serviceItemBeanDao;
	}

	public void setServiceItemBeanDao(ServiceItemBeanDao serviceItemBeanDao) {
		this.serviceItemBeanDao = serviceItemBeanDao;
	}

	public ServiceRelationDao getServiceRelationDao() {
		return serviceRelationDao;
	}

	public void setServiceRelationDao(ServiceRelationDao serviceRelationDao) {
		this.serviceRelationDao = serviceRelationDao;
	}

	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ApproveRequestDetailDao getApproveRequestDetailDao() {
		return approveRequestDetailDao;
	}

	public void setApproveRequestDetailDao(
			ApproveRequestDetailDao approveRequestDetailDao) {
		this.approveRequestDetailDao = approveRequestDetailDao;
	}

	public ServiceShipConditionDao getServiceShipConditionDao() {
		return serviceShipConditionDao;
	}

	public void setServiceShipConditionDao(
			ServiceShipConditionDao serviceShipConditionDao) {
		this.serviceShipConditionDao = serviceShipConditionDao;
	}

	public ServiceStorageConditionDao getServiceStorageConditionDao() {
		return serviceStorageConditionDao;
	}

	public void setServiceStorageConditionDao(
			ServiceStorageConditionDao serviceStorageConditionDao) {
		this.serviceStorageConditionDao = serviceStorageConditionDao;
	}

	public ServiceOfServcategoryBeanDao getServiceOfServcategoryBeanDao() {
		return serviceOfServcategoryBeanDao;
	}

	public void setServiceOfServcategoryBeanDao(
			ServiceOfServcategoryBeanDao serviceOfServcategoryBeanDao) {
		this.serviceOfServcategoryBeanDao = serviceOfServcategoryBeanDao;
	}

	public ServCategorySearchBeanDao getServiceCategorySearchBeanDao() {
		return serviceCategorySearchBeanDao;
	}

	public void setServiceCategorySearchBeanDao(
			ServCategorySearchBeanDao serviceCategorySearchBeanDao) {
		this.serviceCategorySearchBeanDao = serviceCategorySearchBeanDao;
	}

	public ServiceIntermediateDao getServiceIntermediateDao() {
		return serviceIntermediateDao;
	}

	public void setServiceIntermediateDao(
			ServiceIntermediateDao serviceIntermediateDao) {
		this.serviceIntermediateDao = serviceIntermediateDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public CatalogDao getCatalogDao() {
		return catalogDao;
	}

	public void setCatalogDao(CatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}

	public ServiceListBeanDao getServiceListBeanDao() {
		return serviceListBeanDao;
	}

	public void setServiceListBeanDao(ServiceListBeanDao serviceListBeanDao) {
		this.serviceListBeanDao = serviceListBeanDao;
	}

	public ServiceRelationBeanDao getServiceRelationBeanDao() {
		return serviceRelationBeanDao;
	}

	public void setServiceRelationBeanDao(
			ServiceRelationBeanDao serviceRelationBeanDao) {
		this.serviceRelationBeanDao = serviceRelationBeanDao;
	}

	public ApproveRequestBeanDao getApproveRequestBeanDao() {
		return approveRequestBeanDao;
	}

	public void setApproveRequestBeanDao(
			ApproveRequestBeanDao approveRequestBeanDao) {
		this.approveRequestBeanDao = approveRequestBeanDao;
	}

	public ApproveRequestDao getApproveRequestDao() {
		return approveRequestDao;
	}

	public void setApproveRequestDao(ApproveRequestDao approveRequestDao) {
		this.approveRequestDao = approveRequestDao;
	}

	public ServiceComponentDao getServiceComponentDao() {
		return serviceComponentDao;
	}

	public void setServiceComponentDao(ServiceComponentDao serviceComponentDao) {
		this.serviceComponentDao = serviceComponentDao;
	}

	public ServiceRestrictShipDao getServiceRestrictShipDao() {
		return serviceRestrictShipDao;
	}

	public void setServiceRestrictShipDao(
			ServiceRestrictShipDao serviceRestrictShipDao) {
		this.serviceRestrictShipDao = serviceRestrictShipDao;
	}

	public ServiceSpecialPriceDao getServiceSpecialPriceDao() {
		return serviceSpecialPriceDao;
	}

	public void setServiceSpecialPriceDao(
			ServiceSpecialPriceDao serviceSpecialPriceDao) {
		this.serviceSpecialPriceDao = serviceSpecialPriceDao;
	}

	public RoyaltyServiceDao getRoyaltyServiceDao() {
		return royaltyServiceDao;
	}

	public void setRoyaltyServiceDao(RoyaltyServiceDao royaltyServiceDao) {
		this.royaltyServiceDao = royaltyServiceDao;
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	public PurchaseOrderItemDao getPurchaseOrderItemDao() {
		return purchaseOrderItemDao;
	}

	public void setPurchaseOrderItemDao(
			PurchaseOrderItemDao purchaseOrderItemDao) {
		this.purchaseOrderItemDao = purchaseOrderItemDao;
	}

	public OrderReturnItemDao getOrderReturnItemDao() {
		return orderReturnItemDao;
	}

	public void setOrderReturnItemDao(OrderReturnItemDao orderReturnItemDao) {
		this.orderReturnItemDao = orderReturnItemDao;
	}

	public ServiceRestrictShipDao getRestrictShipDao() {
		return restrictShipDao;
	}

	public void setRestrictShipDao(ServiceRestrictShipDao restrictShipDao) {
		this.restrictShipDao = restrictShipDao;
	}

	public PbCountryDao getPbCountryDao() {
		return pbCountryDao;
	}

	public void setPbCountryDao(PbCountryDao pbCountryDao) {
		this.pbCountryDao = pbCountryDao;
	}

	public PbStateDao getPbStateDao() {
		return pbStateDao;
	}

	public void setPbStateDao(PbStateDao pbStateDao) {
		this.pbStateDao = pbStateDao;
	}

	public RoyaltyDao getRoyaltyDao() {
		return royaltyDao;
	}

	public void setRoyaltyDao(RoyaltyDao royaltyDao) {
		this.royaltyDao = royaltyDao;
	}

	public VendorServiceDao getVendorServiceDao() {
		return vendorServiceDao;
	}

	public void setVendorServiceDao(VendorServiceDao vendorServiceDao) {
		this.vendorServiceDao = vendorServiceDao;
	}

	public VendorDao getVendorDao() {
		return vendorDao;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public ServiceSubStepsDao getServiceSubStepsDao() {
		return serviceSubStepsDao;
	}

	public void setServiceSubStepsDao(ServiceSubStepsDao serviceSubStepsDao) {
		this.serviceSubStepsDao = serviceSubStepsDao;
	}

	public ServiceSubStepPriceDao getServiceSubStepPriceDao() {
		return serviceSubStepPriceDao;
	}

	public void setServiceSubStepPriceDao(
			ServiceSubStepPriceDao serviceSubStepPriceDao) {
		this.serviceSubStepPriceDao = serviceSubStepPriceDao;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public ServiceReferenceDao getServiceReferenceDao() {
		return serviceReferenceDao;
	}

	public void setServiceReferenceDao(ServiceReferenceDao serviceReferenceDao) {
		this.serviceReferenceDao = serviceReferenceDao;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public List<ServiceOfServcategoryBean> getSevicesOfServcategoryLisst(
			int sessionCategoryId) {
		List<ServiceOfServcategoryBean> serviceOfPdtcategoryBeanlist = new ArrayList<ServiceOfServcategoryBean>();
		serviceOfPdtcategoryBeanlist = serviceOfServcategoryBeanDao
				.getAllcategorylist(sessionCategoryId);
		return serviceOfPdtcategoryBeanlist;
	}

	public Long getCountBycatalogIdandServiceId(String catalogId,
			Integer serviceKeys) {

		return servicePriceDao.checkAllServiceIdandcatalogId(catalogId,
				serviceKeys);
	}

	public List<CatalogNORules> getCatalogNoRules(String type) {
		return this.catalogNORulesDao.findBy("itemType", type);

	}

	public void saveSubStepPrice(ServiceSubStepPrice ssp) {
		this.serviceSubStepPriceDao.save(ssp);

	}
}
