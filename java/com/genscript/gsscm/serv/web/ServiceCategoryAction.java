package com.genscript.gsscm.serv.web;

/*
 *2010-8-10 13:32:42
 *by mingrs
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.web.LoginAction;
import com.genscript.gsscm.product.dto.MarketingStaffDTO;
import com.genscript.gsscm.product.entity.MarketingGroup;
import com.genscript.gsscm.product.entity.ProductReference;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServicePriceDTO;
import com.genscript.gsscm.serv.entity.PriceRuleGroups;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceReference;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.serv.service.ServicePriceRuleService;
import com.genscript.gsscm.ws.WSException;

@Results({
		@Result(name = LoginAction.SUCCESS, location = "service/category/categorySrch.jsp"),
		@Result(name = "catSubCatAdd", location = "service/category/category_subCategory_add.jsp"),
		@Result(name = "subCategory", location = "service/category/category_subCategory_list.jsp"),
		@Result(name = "categoryCreationForm", location = "service/category/category_creation_form.jsp"),
		@Result(name = "catalogCategoryList", location = "service/category/catalog_category_list.jsp"),
		@Result(name = "cateChooseCateList", location = "service/category/category_chooseCategory_list.jsp"),
		@Result(name = "cateChooseSubCateList", location = "service/category/category_chooseSubCategory_list.jsp"),
		@Result(name = "cataCateAdd", location = "service/category/catalog_category_add.jsp") })
public class ServiceCategoryAction extends BaseAction<ServiceCategoryDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7716056841004316964L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ServicePriceRuleService priceRuleService;
	@Autowired
	private ServService servService;
	@Autowired
	private ProductService productService;

	private Page<ServiceCategory> page;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DozerBeanMapper dozer;

	private String sessionCatalogId;
	private String serviceCategoryKey;
	private String delServiceCategory;
	private String sessionCategoryId;
	private String delSubServiceCategory;
	private String defaultTab;

	private ServiceCategoryDTO entity;
	private ServiceCategoryDTO serviceCategoryDTO;
	private Integer categoryId;
	private String callBackName;
	private Integer categoryLevel;
	private String cataId;

	private String categoryIds;
	private String ctgAstGrp;
	private String ctgDescription;
	private List<Integer> delServSubCategoryIdList;
	private List<Integer> delServPriceIdList;
	private String serviceIds;
	private String standrdPrices;
	private String limitPrices;
	private String serviceDel;
	private List<PbDropdownListOptions> categoryDropdownList;
	private List<PbDropdownListOptions> businessDivisionDropdownList;
	private List<ServiceReference> serviceReferenceList;

	private List<MarketingStaffDTO> marketingStaffDTOList;
	private List<MarketingGroup> marketingGroupList;

	private List<DropDownDTO> dropDownDTO;
	private List<PriceRuleGroups> priceRuleGroupList;

	private String approved;
	private String approvedType;
	private String approvedReason;
	private Boolean nameAppr;
	private Boolean statusAppr;
	private String approvedStatusList;// del product 批量修改status 为INACTIVE
	private List<Integer> statusOfapproved;//
	// 获取从approved 列表页面点来的请求
	private String approvedMethod;
	private Integer requestId;
	private String approvedName;
	private String approvedStatus;

	// 获取从非Service_Category列表页面点过来的请求--Zhang Yong
	private String operation_method;

	private String dodo;

	public String getDodo() {
		return dodo;
	}

	public void setDodo(String dodo) {
		this.dodo = dodo;
	}

	// -- ModelDriven 与 Preparable函数 --//
	public void setCataId(String cataId) {
		this.cataId = cataId;
	}

	public String getCataId() {
		return this.cataId;
	}

	public String getCallBackName() {
		return callBackName;
	}

	public void setCallBackName(String callBackName) {
		this.callBackName = callBackName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public ServiceCategoryDTO getModel() {
		/**
		 * 向跳转页面绑定catalogDTO类型的输出数据。
		 */
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		/**
		 * 对请求进行二次绑定，获取对应的categoryDTO数据;
		 */
		if (categoryId != null) {
			entity = this.servService.getServCategoryDetail(categoryId);
			this.categoryLevel = entity.getCategoryLevel();
		} else {
			entity = new ServiceCategoryDTO();
		}
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String delCategory() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter(
					"delStr");
			List<String> idList = Arrays.asList(dataStr.split("<;>"));
			this.servService.delServCategoryStr(idList);
			rt.put("message", SUCCESS);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String delServiceCategoryToCatalog() {
		if (delServiceCategory != null) {
			String[] delPdt = delServiceCategory.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.DelCatalogServiceCategory.value(),
					sessionCatalogId);
			List<Integer> delServList;
			if (obj != null) {
				delServList = (List<Integer>) obj;
			} else {
				delServList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPdt.length; i++) {
				if (SessionUtil.getOneRow(
						SessionPdtServ.CatalogServiceCategory.value(),
						sessionCatalogId, delPdt[i]) == null) {
					delServList.add(Integer.valueOf(delPdt[i]));
				} else {
					SessionUtil.updateOneRow(
							SessionPdtServ.CatalogServiceCategory.value(),
							sessionCatalogId, delPdt[i], null);
				}
			}
			if (!delServList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelCatalogServiceCategory.value(),
							sessionCatalogId, delServList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelCatalogServiceCategory.value(),
							sessionCatalogId, delServList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	public String addServiceCategoryToCatalog() {
		serviceCategoryDTO = this.servService.getServCategoryDetail(Integer
				.valueOf(serviceCategoryKey));
		serviceCategoryDTO.setAssetGroup(this.ctgAstGrp);
		serviceCategoryDTO.setDescription(this.ctgDescription);
		ServiceCategory serviceCategory = (ServiceCategory) this.dozer.map(
				serviceCategoryDTO, ServiceCategory.class);
		SessionUtil.updateOneRow(SessionPdtServ.CatalogServiceCategory.value(),
				sessionCatalogId, serviceCategoryKey, serviceCategory);
		// Object obj =
		// SessionUtil.getOneRow(SessionPdtServ.CatalogProductCategory.value(),
		// sessionCatalogId, productCategoryKey);
		/*
		 * Object obj =
		 * SessionUtil.getRow(SessionPdtServ.CatalogProductCategory.value(),
		 * sessionCatalogId); System.out.println("come here "); if(obj!=null){
		 * System.out.println("over"); Map<String,ProductCategory>
		 * productCategoryMap = (Map<String, ProductCategory>) obj;
		 * System.out.println
		 * (productCategoryMap.get(productCategoryKey).getName());
		 * for(Map.Entry<String, ProductCategory> entry:
		 * productCategoryMap.entrySet()){ System.out.println("hello ="+
		 * entry.getValue().getName()); } } System.out.println("end");
		 */
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/*
	 * 在SESSION中保存删除的subServiceCategory ID
	 */
	@SuppressWarnings("unchecked")
	public String delSubServiceCategory() {
		if (delSubServiceCategory != null) {
			String[] delPdt = delSubServiceCategory.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.DelSubServiceCategory.value(),
					sessionCategoryId);
			List<Integer> delPdtList;
			if (obj != null) {
				delPdtList = (List<Integer>) obj;
			} else {
				delPdtList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPdt.length; i++) {
				if (SessionUtil.getOneRow(
						SessionPdtServ.SubServiceCategory.value(),
						sessionCategoryId, delPdt[i]) == null) {
					delPdtList.add(Integer.valueOf(delPdt[i]));
				} else {
					SessionUtil.updateOneRow(
							SessionPdtServ.SubServiceCategory.value(),
							sessionCategoryId, delPdt[i], null);
				}
			}
			if (!delPdtList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelSubServiceCategory.value(),
							sessionCategoryId, delPdtList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelSubServiceCategory.value(),
							sessionCategoryId, delPdtList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	/*
	 * 向session中增加保存subServiceCategory信息。
	 */
	public String addSubServiceCategory() {
		serviceCategoryDTO = this.servService.getServCategoryDetail(Integer
				.valueOf(serviceCategoryKey));
		serviceCategoryDTO.setAssetGroup(ctgAstGrp);
		serviceCategoryDTO.setDescription(ctgDescription);
		ServiceCategory serviceCategory = (ServiceCategory) this.dozer.map(
				serviceCategoryDTO, ServiceCategory.class);
		SessionUtil.updateOneRow(SessionPdtServ.SubServiceCategory.value(),
				sessionCategoryId, serviceCategoryKey, serviceCategory);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	@Override
	public String input() throws Exception {
		System.out.println(categoryId);
		System.out.println(dodo);
		String categoryId = ServletActionContext.getRequest().getParameter(
				"categoryId");
		if (categoryId == null) {
			sessionCategoryId = SessionUtil.generateTempId();
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
		} else {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 判断将要修改的单号是否正在被操作
			String editUrl = "serv/service_category!input.action?categoryId="
					+ categoryId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//
			sessionCategoryId = categoryId;
		}
		if (dodo != null && !"".equals(dodo)) {
			if (dodo.equals("first")) {
				SessionUtil.deleteRow(SessionPdtServ.Service.value(),
						sessionCategoryId);
				SessionUtil.deleteRow(SessionPdtServ.DelService.value(),
						sessionCategoryId);
			}
		}

		SessionUtil.deleteRow(SessionPdtServ.SubServiceCategory.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(SessionPdtServ.DelSubServiceCategory.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ServiceCategoryApprovedName.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ServiceCategoryApprovedNameReason.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ServiceCategoryApprovedStatus.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ServiceCategoryApprovedStatusReason.value(),
				sessionCategoryId);
		this.nameAppr = servService.checkPropertyApproved(
				entity.getCategoryId(), RequestApproveType.name.name(),
				RequestApproveType.ServiceCategory.name());
		this.statusAppr = servService.checkPropertyApproved(
				entity.getCategoryId(), RequestApproveType.status.name(),
				RequestApproveType.ServiceCategory.name());
		categoryDropdownList = publicService
				.getDropdownList("CATEGORY_ASSET_GROUP");
		this.businessDivisionDropdownList = publicService
				.getDropdownList("BUSINESS_DEVISION");
		this.marketingGroupList = this.productService.searchAllMarketingGroup();
		this.dropDownDTO = publicService
				.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		if (this.entity.getClsId() != null) {
			this.priceRuleGroupList = this.priceRuleService
					.searchPriceRuleGroupListByClsId(this.entity.getClsId());
		} else {
			this.priceRuleGroupList = this.priceRuleService
					.searchPriceRuleGroupListByClsId(Integer
							.valueOf(dropDownDTO.get(0).getId()));
		}
		if (entity.getMarketingGroup() != null) {
			this.marketingStaffDTOList = this.productService
					.searchMarketingStaffByGroup(entity.getMarketingGroup());
		} else if (!marketingGroupList.isEmpty()) {
			this.marketingStaffDTOList = this.productService
					.searchMarketingStaffByGroup(marketingGroupList.get(0)
							.getGroupId());
		} else {
			this.marketingStaffDTOList = new ArrayList<MarketingStaffDTO>();
		}
		ServletActionContext.getRequest()
				.setAttribute("categoryId", categoryId);
		ServletActionContext.getRequest().setAttribute("sessionCategoryId",
				categoryId);
		return callBackName;
	}

	public String ctlgCatAdd() throws Exception {
		categoryDropdownList = publicService
				.getDropdownList("CATEGORY_ASSET_GROUP");
		return "cataCateAdd";
	}

	public String catSubCatAdd() throws Exception {
		categoryDropdownList = publicService
				.getDropdownList("CATEGORY_ASSET_GROUP");
		return "catSubCatAdd";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceCategory> pagerUtil = new PagerUtil<ServiceCategory>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (categoryLevel != null) {
			PropertyFilter filter = new PropertyFilter("EQI_categoryLevel",
					this.categoryLevel);
			filters.add(filter);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("categoryId");
			page.setOrder(Page.DESC);
		}
		page = this.servService.searchCategoryList(page, filters);
		this.statusOfapproved = this.productService
				.getApprovedRequestListByTableTypeStatus(RequestApproveType.ServiceCategory
						.name());
		Object obj = SessionUtil.getRow(
				SessionPdtServ.DelCatalogServiceCategory.value(),
				sessionCatalogId);
		if (obj != null) {
			List<Integer> delList = (List<Integer>) obj;
			for (int j = 0; j < delList.size(); j++) {
				for (int i = 0; i < page.getResult().size(); i++) {
					if (page.getResult().get(i).getCategoryId()
							.equals(delList.get(j))) {
						page.getResult().remove(i);
					}
				}
			}
		}
		obj = SessionUtil.getRow(SessionPdtServ.CatalogServiceCategory.value(),
				sessionCatalogId);
		if (obj != null) {
			if (page.getPageNo() == 1) {
				Map<String, ServiceCategory> serviceCategoryMap = (Map<String, ServiceCategory>) obj;
				for (Map.Entry<String, ServiceCategory> entry : serviceCategoryMap
						.entrySet()) {
					ServiceCategory serviceCategory = serviceCategoryMap
							.get(entry.getKey());
					if (serviceCategory != null) {
						page.getResult().add(serviceCategory);
					}
				}
			}
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		if ("catalogCategoryList".equals(callBackName)) {
			return "catalogCategoryList";
		}
		dodo = "first";
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String subCategoryList() {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceCategory> pagerUtil = new PagerUtil<ServiceCategory>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		Map<String, String> filterMap = new HashMap<String, String>();
		String parentCatId = ServletActionContext.getRequest().getParameter(
				"parentCatId");
		if (parentCatId == null || "".equals(parentCatId)) {
			parentCatId = "0";
		}
		filterMap.put("EQI_parentCatId", parentCatId);
		page.setOrderBy("catalogId");
		page.setOrder(Page.DESC);
		page = this.servService.searchCategoryList(page, filterMap);
		/*
		 * 将session中对应删除信息，在LIST时去除。
		 */
		Object obj = SessionUtil
				.getRow(SessionPdtServ.DelSubServiceCategory.value(),
						sessionCategoryId);
		if (obj != null) {
			List<Integer> delList = (List<Integer>) obj;
			for (int j = 0; j < delList.size(); j++) {
				for (int i = 0; i < page.getResult().size(); i++) {
					if (page.getResult().get(i).getCategoryId()
							.equals(delList.get(j))) {
						page.getResult().remove(i);
					}
				}
			}
		}
		/*
		 * 将session中对应save信息，在LIST中增加，只增加在第一面。其他页面没有。
		 */
		obj = SessionUtil.getRow(SessionPdtServ.SubServiceCategory.value(),
				sessionCategoryId);
		if (obj != null) {
			if (page.getPageNo() == 1) {
				Map<String, ServiceCategory> serviceCategoryMap = (Map<String, ServiceCategory>) obj;
				for (Map.Entry<String, ServiceCategory> entry : serviceCategoryMap
						.entrySet()) {
					ServiceCategory serviceCategory = serviceCategoryMap
							.get(entry.getKey());
					if (serviceCategory != null) {
						page.getResult().add(serviceCategory);
					}
				}
			}
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "subCategory";
	}

	@SuppressWarnings("unchecked")
	public String ctlgChooseCatList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceCategory> pagerUtil = new PagerUtil<ServiceCategory>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		// page = this.servService.searchCategorySList(page, cataId);
		Object obj = SessionUtil
				.getRow(SessionPdtServ.CatalogServiceCategory.value(),
						sessionCatalogId);
		if (obj != null) {
			page = this.servService.searchCategoryMapList(page, cataId,
					categoryLevel, (Map<String, ServiceCategory>) obj);
		} else {
			page = this.servService.searchCategorySList(page, cataId,
					categoryLevel);
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "cateChooseCateList";
	}

	@SuppressWarnings("unchecked")
	public String cateChooseSubCatList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceCategory> pagerUtil = new PagerUtil<ServiceCategory>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		Object obj = SessionUtil.getRow(
				SessionPdtServ.SubServiceCategory.value(), sessionCategoryId);
		if (obj != null) {
			page = this.servService.searchSubCategoryMapList(page, categoryId,
					categoryLevel, (Map<String, ServiceCategory>) obj);
		} else {
			page = this.servService.searchSubCategoryList(page, categoryId,
					categoryLevel);
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "cateChooseSubCateList";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public String serviceCategorySaveAct() throws Exception {
		Integer loginUserId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessionCategoryId != null && !("").equals(sessionCategoryId)) {
			String editUrl = "serv/service_category!input.action?categoryId="
					+ sessionCategoryId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message",
						"Save category fail,the category is editing by "
								+ operation_method);
				rt.put("id", sessionCategoryId);
				rt.put("back", "service");
				Struts2Util.renderJson(rt);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		try {
			if (this.delServPriceIdList != null) {
				serviceCategoryDTO.setDelServPriceIdList(delServPriceIdList);
			}
			Object obj = SessionUtil.getRow(
					SessionPdtServ.SubServiceCategory.value(),
					sessionCategoryId);
			if (obj != null) {
				Map<String, ServiceCategory> serviceCategoryMap = (Map<String, ServiceCategory>) obj;
				List<ServiceCategory> serviceCategoryList = new ArrayList<ServiceCategory>();
				for (Map.Entry<String, ServiceCategory> entry : serviceCategoryMap
						.entrySet()) {
					ServiceCategory serviceCategory = serviceCategoryMap
							.get(entry.getKey());
					if (serviceCategory != null) {
						serviceCategoryList.add(serviceCategory);
						// this.servService.saveOrUpdateServCategory(serviceCategory,
						// loginUserId);
					}
				}
				SessionUtil.deleteRow(
						SessionPdtServ.SubServiceCategory.value(),
						sessionCategoryId);
				if (!serviceCategoryList.isEmpty()) {
					serviceCategoryDTO.setSubCategoryLists(serviceCategoryList);
				}
			}

			obj = SessionUtil.getRow(
					SessionPdtServ.DelSubServiceCategory.value(),
					sessionCategoryId);
			if (obj != null) {
				List<Integer> delPdtList = (List<Integer>) obj;
				if (!delPdtList.isEmpty()) {
					serviceCategoryDTO.setDelSubCategoryIdList(delPdtList);
				}
				SessionUtil.deleteRow(
						SessionPdtServ.DelSubServiceCategory.value(),
						sessionCategoryId);
			}
			/*
			 * if(this.delServSubCategoryIdList!=null){
			 * serviceCategoryDTO.setDelSubCategoryIdList
			 * (delServSubCategoryIdList); }
			 * if(categoryIds!=null&&!("".equals(categoryIds))){
			 * List<ServiceCategoryDTO> serviceCategoryList = new
			 * ArrayList<ServiceCategoryDTO>(); String[] categoryIdData =
			 * categoryIds.split("<;;>"); String[] ctgDescriptionData =
			 * ctgDescription.split("<;;>"); String[] ctgAstGrpData =
			 * ctgAstGrp.split("<;;>"); if(categoryIdData.length>1){ for(int i =
			 * 1;i<categoryIdData.length;i++){ ServiceCategory category =
			 * this.servService
			 * .getServiceCategoryDetail(Integer.valueOf(categoryIdData[i]));
			 * if(category!=null){ category.setAssetGroup(ctgAstGrpData[i]);
			 * if(ctgDescriptionData.length>i-1){
			 * category.setDescription(ctgDescriptionData[i]); }
			 * ServiceCategoryDTO categoryDTO = (ServiceCategoryDTO)
			 * dozer.map(category, ServiceCategoryDTO.class);
			 * serviceCategoryList.add(categoryDTO); } }
			 * if(serviceCategoryList!=null){
			 * serviceCategoryDTO.setSubCategoryList(serviceCategoryList); } } }
			 */

			obj = SessionUtil.getRow(SessionPdtServ.Service.value(),
					sessionCategoryId);
			if (obj != null) {
				Map<String, ServicePriceDTO> servicePriceMap = (Map<String, ServicePriceDTO>) obj;
				List<ServicePriceDTO> servicePriceDTOList = new ArrayList<ServicePriceDTO>();
				for (Map.Entry<String, ServicePriceDTO> entry : servicePriceMap
						.entrySet()) {
					ServicePriceDTO serviceCategory = servicePriceMap.get(entry
							.getKey());
					if (serviceCategory != null) {
						servicePriceDTOList.add(serviceCategory);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.Service.value(),
						sessionCategoryId);
				if (!servicePriceDTOList.isEmpty()) {
					serviceCategoryDTO.setServPriceList(servicePriceDTOList);
				}
			}

			obj = SessionUtil.getRow(SessionPdtServ.DelService.value(),
					sessionCategoryId);
			if (obj != null) {
				List<Integer> delPriceList = (List<Integer>) obj;
				if (!delPriceList.isEmpty()) {
					serviceCategoryDTO.setDelServPriceIdList(delPriceList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelService.value(),
						sessionCategoryId);
			}
			/*
			 * if(serviceIds!=null&&!("".equals(serviceIds))){
			 * List<ServicePriceDTO> servicePriceList = new
			 * ArrayList<ServicePriceDTO>(); String[] serviceIdDate =
			 * serviceIds.split("<;;>"); String[] standrdPricesDate =
			 * standrdPrices.split("<;;>"); String[] priceLimitDate =
			 * limitPrices.split("<;;>"); if(serviceIdDate.length>1){ for(int i
			 * = 1; i<serviceIdDate.length;i++){ ServicePriceDTO servicePriceDTO
			 * = new ServicePriceDTO();
			 * servicePriceDTO.setCatalogId(serviceCategoryDTO.getCatalogId());
			 * servicePriceDTO
			 * .setCategoryId(serviceCategoryDTO.getCategoryId());
			 * servicePriceDTO.setServiceId(Integer.valueOf(serviceIdDate[i]));
			 * if
			 * (standrdPricesDate.length>i-1&&standrdPricesDate[i]!=null&&!("".
			 * equals(standrdPricesDate[i]))){
			 * servicePriceDTO.setStandardPrice(Double
			 * .valueOf(standrdPricesDate[i])); }
			 * if(priceLimitDate.length>i-1&&priceLimitDate
			 * [i]!=null&&!("".equals(priceLimitDate[i]))){
			 * servicePriceDTO.setLimitPrice(Double.valueOf(priceLimitDate[i]));
			 * } servicePriceDTO.setStatus(serviceCategoryDTO.getStatus());
			 * servicePriceList.add(servicePriceDTO); }
			 * if(servicePriceList!=null){
			 * serviceCategoryDTO.setServPriceList(servicePriceList); } } }
			 */

			// 获取category approved;
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedName.value(),
					sessionCategoryId);
			if (obj != null) {
				serviceCategoryDTO.setNameApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceCategoryApprovedName.value(),
						sessionCategoryId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedNameReason.value(),
					sessionCategoryId);
			if (obj != null) {
				serviceCategoryDTO.setNameReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceCategoryApprovedNameReason
								.value(), sessionCategoryId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedStatus.value(),
					sessionCategoryId);
			if (obj != null) {
				serviceCategoryDTO.setStatusApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceCategoryApprovedStatus.value(),
						sessionCategoryId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedStatusReason.value(),
					sessionCategoryId);
			if (obj != null) {
				serviceCategoryDTO.setStatusReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceCategoryApprovedStatusReason
								.value(), sessionCategoryId);
			}

			String referenceList = ServletActionContext.getRequest()
					.getParameter("referenceList");
			List<ServiceReference> serviceReferenceList = new ArrayList<ServiceReference>();
			if (referenceList != null && !referenceList.equals("")) {
				String[] references = referenceList.split("<;>");
				for (int i = 0; i < references.length; i++) {
					if (references[i] != null && !references[i].equals("")) {
						String[] reference = references[i].split("<,>");
						if (reference != null && reference.length == 3) {
							ServiceReference pr = new ServiceReference();
							if (!reference[0].equals("")) {
								pr = this.servService
										.getServiceRefereeceById(Integer
												.valueOf(reference[0]));
							} else {
								pr.setId(null);
							}
							pr.setReference(reference[1]);
							pr.setUrl(reference[2]);
							serviceReferenceList.add(pr);
						}
					}
				}

			}
			serviceCategoryDTO.setServiceReferenceList(serviceReferenceList);
			String delReferenceId = Struts2Util.getParameter("delReferenceId");
			if (delReferenceId != null) {
				String[] dels = delReferenceId.split("<;>");
				if (dels.length > 0) {
					List<Integer> delList = new ArrayList<Integer>();
					Pattern pattern = Pattern.compile("[0-9]*");
					for (int i = 0; i < dels.length; i++) {
						if (dels[i] != null && !dels[i].equals("")) {
							Matcher isNum = pattern.matcher(dels[i]);
							if (isNum.matches()) {
								delList.add(Integer.valueOf(dels[i]));
							}
						}
					}
					if (!delList.isEmpty()) {
						serviceCategoryDTO.setDelReferenceList(delList);
					}
				}
			}
			ServiceCategory serviceCategory = this.servService
					.saveServCategory(serviceCategoryDTO, loginUserId);
			rt.put("message", "The catalog #" + serviceCategory.getCategoryNo()
					+ " is saved successfully!");
			rt.put("id", serviceCategory.getCategoryId());
			rt.put("back", "service");
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public String delCategoryListApproved() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (this.approvedStatusList == null
				|| "".equals(this.approvedStatusList)) {
			rt.put("message", "You have not chosen any valid data!!!");
		} else {
			String[] statustList = this.approvedStatusList.split(",");
			Integer userId = SessionUtil.getUserId();
			this.servService.delCategoryApproved(statustList, userId, approved,
					approvedReason);
			rt.put("message", SUCCESS);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * Service category Approved session save
	 * 
	 * @param type , name ,reason
	 */
	public String serviceCategoryApprovedSaveSession() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (SessionPdtServ.ServiceCategoryApprovedName.value().equals(
				approvedType)) {
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedName.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceCategoryApprovedName.value(),
						sessionCategoryId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceCategoryApprovedName.value(),
						sessionCategoryId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedNameReason.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceCategoryApprovedNameReason
								.value(), sessionCategoryId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceCategoryApprovedNameReason
								.value(), sessionCategoryId, approvedReason);
			}

		} else if (SessionPdtServ.ServiceCategoryApprovedStatus.value().equals(
				approvedType)) {
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedStatus.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceCategoryApprovedStatus.value(),
						sessionCategoryId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceCategoryApprovedStatus.value(),
						sessionCategoryId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceCategoryApprovedStatusReason.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceCategoryApprovedStatusReason
								.value(), sessionCategoryId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceCategoryApprovedStatusReason
								.value(), sessionCategoryId, approvedReason);
			}
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	// -- 页面属性访问函数 --//
	/**
	 * list页面显示Catalog分页列表.
	 */
	public Page<ServiceCategory> getPage() {
		return page;
	}

	public List<PbDropdownListOptions> getCategoryDropdownList() {
		return categoryDropdownList;
	}

	public void setCategoryDropdownList(
			List<PbDropdownListOptions> categoryDropdownList) {
		this.categoryDropdownList = categoryDropdownList;
	}

	public ServiceCategoryDTO getServiceCategoryDTO() {
		return serviceCategoryDTO;
	}

	public void setServiceCategoryDTO(ServiceCategoryDTO serviceCategoryDTO) {
		this.serviceCategoryDTO = serviceCategoryDTO;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getCtgAstGrp() {
		return ctgAstGrp;
	}

	public void setCtgAstGrp(String ctgAstGrp) {
		this.ctgAstGrp = ctgAstGrp;
	}

	public String getCtgDescription() {
		return ctgDescription;
	}

	public void setCtgDescription(String ctgDescription) {
		this.ctgDescription = ctgDescription;
	}

	public List<Integer> getDelServSubCategoryIdList() {
		return delServSubCategoryIdList;
	}

	public void setDelServSubCategoryIdList(
			List<Integer> delServSubCategoryIdList) {
		this.delServSubCategoryIdList = delServSubCategoryIdList;
	}

	public List<Integer> getDelServPriceIdList() {
		return delServPriceIdList;
	}

	public void setDelServPriceIdList(List<Integer> delServPriceIdList) {
		this.delServPriceIdList = delServPriceIdList;
	}

	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	}

	public String getStandrdPrices() {
		return standrdPrices;
	}

	public void setStandrdPrices(String standrdPrices) {
		this.standrdPrices = standrdPrices;
	}

	public String getLimitPrices() {
		return limitPrices;
	}

	public void setLimitPrices(String limitPrices) {
		this.limitPrices = limitPrices;
	}

	public String getServiceDel() {
		return serviceDel;
	}

	public void setServiceDel(String serviceDel) {
		this.serviceDel = serviceDel;
	}

	public String getSessionCatalogId() {
		return sessionCatalogId;
	}

	public void setSessionCatalogId(String sessionCatalogId) {
		this.sessionCatalogId = sessionCatalogId;
	}

	public String getServiceCategoryKey() {
		return serviceCategoryKey;
	}

	public void setServiceCategoryKey(String serviceCategoryKey) {
		this.serviceCategoryKey = serviceCategoryKey;
	}

	public String getDelServiceCategory() {
		return delServiceCategory;
	}

	public void setDelServiceCategory(String delServiceCategory) {
		this.delServiceCategory = delServiceCategory;
	}

	public String getSessionCategoryId() {
		return sessionCategoryId;
	}

	public void setSessionCategoryId(String sessionCategoryId) {
		this.sessionCategoryId = sessionCategoryId;
	}

	public String getDelSubServiceCategory() {
		return delSubServiceCategory;
	}

	public void setDelSubServiceCategory(String delSubServiceCategory) {
		this.delSubServiceCategory = delSubServiceCategory;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApprovedType() {
		return approvedType;
	}

	public void setApprovedType(String approvedType) {
		this.approvedType = approvedType;
	}

	public String getApprovedReason() {
		return approvedReason;
	}

	public void setApprovedReason(String approvedReason) {
		this.approvedReason = approvedReason;
	}

	public Boolean getNameAppr() {
		return nameAppr;
	}

	public void setNameAppr(Boolean nameAppr) {
		this.nameAppr = nameAppr;
	}

	public Boolean getStatusAppr() {
		return statusAppr;
	}

	public void setStatusAppr(Boolean statusAppr) {
		this.statusAppr = statusAppr;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public List<PbDropdownListOptions> getBusinessDivisionDropdownList() {
		return businessDivisionDropdownList;
	}

	public void setBusinessDivisionDropdownList(
			List<PbDropdownListOptions> businessDivisionDropdownList) {
		this.businessDivisionDropdownList = businessDivisionDropdownList;
	}

	public List<MarketingStaffDTO> getMarketingStaffDTOList() {
		return marketingStaffDTOList;
	}

	public void setMarketingStaffDTOList(
			List<MarketingStaffDTO> marketingStaffDTOList) {
		this.marketingStaffDTOList = marketingStaffDTOList;
	}

	public List<MarketingGroup> getMarketingGroupList() {
		return marketingGroupList;
	}

	public void setMarketingGroupList(List<MarketingGroup> marketingGroupList) {
		this.marketingGroupList = marketingGroupList;
	}

	public String getApprovedMethod() {
		return approvedMethod;
	}

	public void setApprovedMethod(String approvedMethod) {
		this.approvedMethod = approvedMethod;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getApprovedStatusList() {
		return approvedStatusList;
	}

	public void setApprovedStatusList(String approvedStatusList) {
		this.approvedStatusList = approvedStatusList;
	}

	public List<Integer> getStatusOfapproved() {
		return statusOfapproved;
	}

	public void setStatusOfapproved(List<Integer> statusOfapproved) {
		this.statusOfapproved = statusOfapproved;
	}

	public List<DropDownDTO> getDropDownDTO() {
		return dropDownDTO;
	}

	public void setDropDownDTO(List<DropDownDTO> dropDownDTO) {
		this.dropDownDTO = dropDownDTO;
	}

	public List<PriceRuleGroups> getPriceRuleGroupList() {
		return priceRuleGroupList;
	}

	public void setPriceRuleGroupList(List<PriceRuleGroups> priceRuleGroupList) {
		this.priceRuleGroupList = priceRuleGroupList;
	}

	public String getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(String defaultTab) {
		this.defaultTab = defaultTab;
	}

	public List<ServiceReference> getServiceReferenceList() {
		return serviceReferenceList;
	}

	public void setServiceReferenceList(
			List<ServiceReference> serviceReferenceList) {
		this.serviceReferenceList = serviceReferenceList;
	}

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

}
