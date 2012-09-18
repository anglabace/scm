package com.genscript.gsscm.product.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.web.LoginAction;
import com.genscript.gsscm.product.dto.MarketingStaffDTO;
import com.genscript.gsscm.product.dto.ProductCategoryDTO;
import com.genscript.gsscm.product.dto.ProductPriceDTO;
import com.genscript.gsscm.product.entity.MarketingGroup;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.ws.WSException;

/*
 *2010-8-16 13:32:42
 *by mingrs
 */

@Results({
		@Result(name = LoginAction.SUCCESS, location = "product/category/categorySrch.jsp"),
		@Result(name = "catSubCatAdd", location = "product/category/category_subCategory_add.jsp"),
		@Result(name = "subCategory", location = "product/category/category_subCategory_list.jsp"),
		@Result(name = "categoryCreationForm", location = "product/category/category_creation_form.jsp"),
		@Result(name = "catalogCategoryList", location = "product/category/catalog_category_list.jsp"),
		@Result(name = "cateChooseCateList", location = "product/category/category_chooseCategory_list.jsp"),
		@Result(name = "cateChooseSubCateList", location = "product/category/category_chooseSubCategory_list.jsp"),
		@Result(name = "cataCateAdd", location = "product/category/catalog_category_add.jsp") })
public class ProductCategoryAction extends BaseAction<ProductCategoryDTO> {

	/**
	 *    
	 */
	private static final long serialVersionUID = 305823202180313020L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ProductService productService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DozerBeanMapper dozer;

	private ProductCategoryDTO entity;
	private ProductCategoryDTO productCategoryDTO;
	private String cataId;
	private Integer categoryLevel;
	private Integer categoryId;
	private String parentCatId;
	private String parentCategoryNo;
	private String callBackName;
	private String defaultTab;

	private String sessionCatalogId;
	private String productCategoryKey;
	private String delProductCategory;
	private String sessionCategoryId;
	private String delSubProductCategory;

	private String categoryIds;
	private String ctgAstGrp;
	private String ctgDescription;
	private List<Integer> delPdtSubCategoryList;
	private List<Integer> delPdtPriceList;
	private String productIds;
	private String standrdPrices;
	private String limitPrices;
	private String productDel;

	private Page<ProductCategory> page;

	private String approved;
	private String approvedType;
	private String approvedReason;
	private Boolean nameAppr;
	private Boolean statusAppr;

	// 获取从approved 列表页面点来的请求
	private String approvedMethod;
	private Integer requestId;
	private String approvedName;
	private String approvedStatus;
	private String approvedStatusList;// del product 批量修改status 为INACTIVE
	private List<Integer> statusOfapproved;//

	private List<PbDropdownListOptions> categoryDropdownList;
	private List<PbDropdownListOptions> businessDivisionDropdownList;

	private List<MarketingStaffDTO> marketingStaffDTOList;
	private List<MarketingGroup> marketingGroupList;

	// 获取从非Product_Category列表页面点过来的请求--Zhang Yong
	private String operation_method;

	// -- ModelDriven 与 Preparable函数 --//
	public void setCataId(String cataId) {
		this.cataId = cataId;
	}

	public String getCataId() {
		return this.cataId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCallBackName() {
		return callBackName;
	}

	public void setCallBackName(String callBackName) {
		this.callBackName = callBackName;
	}

	public ProductCategoryDTO getModel() {
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
			entity = this.productService.getPdtCategoryDetail(categoryId);
			this.categoryLevel = entity.getCategoryLevel();
		} else {
			entity = new ProductCategoryDTO();
		}
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 删除productCatagory
	 */
	public String delCategory() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			String dataStr = ServletActionContext.getRequest().getParameter(
					"delStr");
			List<String> idList = Arrays.asList(dataStr.split("<;>"));
			this.productService.delPdtCategoryListStr(idList);
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

	/*
	 * 在SESSION中保存删除的productCategory ID
	 */
	@SuppressWarnings("unchecked")
	public String delProductCategoryToCatalog() {
		if (delProductCategory != null) {
			List<Integer> delPdtList;
			String[] delPdt = delProductCategory.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.DelCatalogProductCategory.value(),
					sessionCatalogId);
			if (obj != null) {
				delPdtList = (List<Integer>) obj;
			} else {
				delPdtList = new ArrayList<Integer>();
			}
			for (int i = 0; i < delPdt.length; i++) {
				if (SessionUtil.getOneRow(
						SessionPdtServ.CatalogProductCategory.value(),
						sessionCatalogId, delPdt[i]) == null) {
					delPdtList.add(Integer.valueOf(delPdt[i]));
				} else {
					SessionUtil.updateOneRow(
							SessionPdtServ.CatalogProductCategory.value(),
							sessionCatalogId, delPdt[i], null);
				}
			}
			if (!delPdtList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelCatalogProductCategory.value(),
							sessionCatalogId, delPdtList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelCatalogProductCategory.value(),
							sessionCatalogId, delPdtList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	/*
	 * 向session中增加保存productCategory信息。
	 */
	public String addProductCategoryToCatalog() {
		productCategoryDTO = this.productService.getPdtCategoryDetail(Integer
				.valueOf(productCategoryKey));
		productCategoryDTO.setAssetGroup(ctgAstGrp);
		productCategoryDTO.setDescription(ctgDescription);
		ProductCategory productCategory = (ProductCategory) this.dozer.map(
				productCategoryDTO, ProductCategory.class);
		SessionUtil.updateOneRow(SessionPdtServ.CatalogProductCategory.value(),
				sessionCatalogId, productCategoryKey, productCategory);
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
	 * 在SESSION中保存删除的subProductCategory ID
	 */
	@SuppressWarnings("unchecked")
	public String delSubProductCategory() {
		if (delSubProductCategory != null) {
			String[] delPdt = delSubProductCategory.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.DelSubProductCategory.value(),
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
						SessionPdtServ.SubProductCategory.value(),
						sessionCategoryId, delPdt[i]) == null) {
					delPdtList.add(Integer.valueOf(delPdt[i]));
				} else {
					SessionUtil.updateOneRow(
							SessionPdtServ.SubProductCategory.value(),
							sessionCategoryId, delPdt[i], null);
				}
			}
			if (!delPdtList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelSubProductCategory.value(),
							sessionCategoryId, delPdtList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelSubProductCategory.value(),
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
	 * 向session中增加保存subproductCategory信息。
	 */
	public String addSubProductCategory() {
		productCategoryDTO = this.productService.getPdtCategoryDetail(Integer
				.valueOf(productCategoryKey));
		productCategoryDTO.setAssetGroup(this.ctgAstGrp);
		productCategoryDTO.setDescription(ctgDescription);
		ProductCategory productCategory = (ProductCategory) this.dozer.map(
				productCategoryDTO, ProductCategory.class);
		SessionUtil.updateOneRow(SessionPdtServ.SubProductCategory.value(),
				sessionCategoryId, productCategoryKey, productCategory);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	private String dodo;

	public String getDodo() {
		return dodo;
	}

	public void setDodo(String dodo) {
		this.dodo = dodo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genscript.gsscm.common.web.BaseAction#input()
	 * 新建和修改category时通过ID把数据读取出来。 CategoryDTO 由prepareModel()进行获取。
	 * sessionCategoryId 用于session 使用，当ID为空时获取随机数，否则获取该category id;
	 */
	@Override
	public String input() throws Exception {
		if (this.categoryId == null) {
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
			String editUrl = "product/product_category!input.action?categoryId="
					+ categoryId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//
			sessionCategoryId = this.entity.getCategoryId().toString();
		}
		System.out.println(dodo);
		SessionUtil.deleteRow(SessionPdtServ.SubProductCategory.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(SessionPdtServ.DelSubProductCategory.value(),
				sessionCategoryId);
		if (dodo != "") {
			if (dodo.equals("first")) {
				SessionUtil.deleteRow(SessionPdtServ.Product.value(),
						sessionCategoryId);
				SessionUtil.deleteRow(SessionPdtServ.DelProduct.value(),
						sessionCategoryId);
			}
		}

		SessionUtil.deleteRow(
				SessionPdtServ.ProductCategoryApprovedName.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ProductCategoryApprovedNameReason.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ProductCategoryApprovedStatus.value(),
				sessionCategoryId);
		SessionUtil.deleteRow(
				SessionPdtServ.ProductCategoryApprovedStatusReason.value(),
				sessionCategoryId);
		this.nameAppr = productService.checkPropertyApproved(
				entity.getCategoryId(), RequestApproveType.name.name(),
				RequestApproveType.ProductCategory.name());
		this.statusAppr = productService.checkPropertyApproved(
				entity.getCategoryId(), RequestApproveType.status.name(),
				RequestApproveType.ProductCategory.name());
		categoryDropdownList = publicService
				.getDropdownList("CATEGORY_ASSET_GROUP");
		this.businessDivisionDropdownList = publicService
				.getDropdownList("BUSINESS_DEVISION");
		this.marketingGroupList = this.productService.searchAllMarketingGroup();
		System.out.println(marketingGroupList.size());
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

		System.out.println(callBackName);
		return callBackName;
	}

	/*
	 * catalog add category时使用。 用于product/category/category_category_add.jsp页面;
	 */
	public String ctlgCatAdd() throws Exception {
		categoryDropdownList = publicService
				.getDropdownList("CATEGORY_ASSET_GROUP");
		return "cataCateAdd";
	}

	/*
	 * catalog add subCategory时使用。
	 * 用于product/category/category_SubCategory_add.jsp页面;
	 */
	public String catSubCatAdd() throws Exception {
		categoryDropdownList = publicService
				.getDropdownList("CATEGORY_ASSET_GROUP");
		return "catSubCatAdd";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genscript.gsscm.common.web.BaseAction#list()
	 * 根据条件查询productCategory信息。 查询条件参考PropertyFilter对像。
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductCategory> pagerUtil = new PagerUtil<ProductCategory>();
		page = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (categoryLevel != null) {
			PropertyFilter filter = new PropertyFilter("EQI_categoryLevel",
					this.categoryLevel);
			filters.add(filter);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("modifyDate");
			page.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		/*
		 * 将session中对应删除信息，在LIST时去除。
		 */
		page = this.productService.searchCategoryList(page, filters);
		this.statusOfapproved = this.productService
				.getApprovedRequestListByTableTypeStatus(RequestApproveType.ProductCategory
						.name());
		Object obj = SessionUtil.getRow(
				SessionPdtServ.DelCatalogProductCategory.value(),
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
		/*
		 * 将session中对应save信息，在LIST中增加，只增加在第一面。其他页面没有。
		 */
		obj = SessionUtil.getRow(SessionPdtServ.CatalogProductCategory.value(),
				sessionCatalogId);
		if (obj != null) {
			if (page.getPageNo() == 1) {
				Map<String, ProductCategory> productCategoryMap = (Map<String, ProductCategory>) obj;
				for (Map.Entry<String, ProductCategory> entry : productCategoryMap
						.entrySet()) {
					ProductCategory productCategory = productCategoryMap
							.get(entry.getKey());
					if (productCategory != null) {
						page.getResult().add(productCategory);
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
		return SUCCESS;
	}

	/*
	 * 查询出subCategory信息；
	 */
	@SuppressWarnings("unchecked")
	public String subCategoryList() {
		Map<String, String> filterMap = new HashMap<String, String>();
		if (parentCatId == null || "".equals(parentCatId)) {
			parentCatId = "0";
		}
		filterMap.put("EQI_parentCatId", parentCatId);
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductCategory> pagerUtil = new PagerUtil<ProductCategory>();
		page = pagerUtil.getRequestPage();
		page.setOrderBy("catalogId");
		page.setOrder(Page.DESC);
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		page = this.productService.searchCategoryList(page, filterMap);
		/*
		 * 将session中对应删除信息，在LIST时去除。
		 */
		Object obj = SessionUtil
				.getRow(SessionPdtServ.DelSubProductCategory.value(),
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
		 * 将session中对应save信息，在LIST中增加，只增加在第一页。其他页没有。
		 */
		obj = SessionUtil.getRow(SessionPdtServ.SubProductCategory.value(),
				sessionCategoryId);
		if (obj != null) {
			if (page.getPageNo() == 1) {
				Map<String, ProductCategory> productCategoryMap = (Map<String, ProductCategory>) obj;
				for (Map.Entry<String, ProductCategory> entry : productCategoryMap
						.entrySet()) {
					ProductCategory productCategory = productCategoryMap
							.get(entry.getKey());
					if (productCategory != null) {
						page.getResult().add(productCategory);
					}
				}
			}
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "subCategory";
	}

	/*
	 * product/category/category_chooseCategory_list.jsp面页中查询方法。
	 */
	@SuppressWarnings("unchecked")
	public String ctlgChooseCatList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductCategory> pagerUtil = new PagerUtil<ProductCategory>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		/*
		 * obj作用是将已经保存到SESSION中的数据不显示。
		 */
		Object obj = SessionUtil
				.getRow(SessionPdtServ.CatalogProductCategory.value(),
						sessionCatalogId);
		if (obj != null) {
			page = this.productService.searchCategoryMapList(page, cataId,
					categoryLevel, (Map<String, ProductCategory>) obj);
		} else {
			page = this.productService.searchCategorySList(page, cataId,
					categoryLevel);
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "cateChooseCateList";
	}

	/*
	 * product/category/category_chooseSubCategory_list.jsp面页中查询方法。
	 */
	@SuppressWarnings("unchecked")
	public String cateChooseSubCatList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductCategory> pagerUtil = new PagerUtil<ProductCategory>();
		page = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(15);
		}
		Object obj = SessionUtil.getRow(
				SessionPdtServ.SubProductCategory.value(), sessionCategoryId);
		if (obj != null) {
			page = this.productService.searchSubCategoryMapList(page,
					categoryId, categoryLevel,
					(Map<String, ProductCategory>) obj);
		} else {
			page = this.productService.searchSubCategoryList(page,
					categoryLevel, categoryId);
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

	/*
	 * 保存productCategory
	 */
	@SuppressWarnings("unchecked")
	public String productCategorySaveAct() throws Exception {
		Integer loginUserId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessionCategoryId != null && !("").equals(sessionCategoryId)) {
			String editUrl = "product/product_category!input.action?categoryId="
					+ sessionCategoryId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message",
						"Save category fail,the category is editing by "
								+ operation_method);
				rt.put("id", sessionCategoryId);
				Struts2Util.renderJson(rt);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		try {
			Object obj = SessionUtil.getRow(
					SessionPdtServ.SubProductCategory.value(),
					sessionCategoryId);
			if (obj != null) {
				Map<String, ProductCategory> productCategoryMap = (Map<String, ProductCategory>) obj;
				List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
				for (Map.Entry<String, ProductCategory> entry : productCategoryMap
						.entrySet()) {
					ProductCategory productCategory = productCategoryMap
							.get(entry.getKey());
					if (productCategory != null) {
						productCategoryList.add(productCategory);
						// this.productService.saveOrUpdateProductCategory(productCategory,
						// loginUserId);
					}
				}
				SessionUtil.deleteRow(
						SessionPdtServ.SubProductCategory.value(),
						sessionCategoryId);
				if (!productCategoryList.isEmpty()) {
					productCategoryDTO.setSubCategoryLists(productCategoryList);
				}
			}

			obj = SessionUtil.getRow(
					SessionPdtServ.DelSubProductCategory.value(),
					sessionCategoryId);
			if (obj != null) {
				List<Integer> delPdtList = (List<Integer>) obj;
				if (!delPdtList.isEmpty()) {
					productCategoryDTO.setDelSubCategoryIdList(delPdtList);
				}
				SessionUtil.deleteRow(
						SessionPdtServ.DelSubProductCategory.value(),
						sessionCategoryId);
			}

			obj = SessionUtil.getRow(SessionPdtServ.Product.value(),
					sessionCategoryId);
			if (obj != null) {
				Map<String, ProductPriceDTO> productPriceMap = (Map<String, ProductPriceDTO>) obj;
				List<ProductPriceDTO> productPriceDTOList = new ArrayList<ProductPriceDTO>();
				for (Map.Entry<String, ProductPriceDTO> entry : productPriceMap
						.entrySet()) {
					ProductPriceDTO productCategory = productPriceMap.get(entry
							.getKey());
					System.out.println(productCategory.getCatalogId()
							+ ">>>4444>" + productCategory.getProductId()
							+ "   " + productCategory.getCategoryId());

					if (productCategory != null) {
						productPriceDTOList.add(productCategory);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.Product.value(),
						sessionCategoryId);
				if (!productPriceDTOList.isEmpty()) {
					for (int i = 0; i < productPriceDTOList.size(); i++) {
						System.out.println(productPriceDTOList.get(i)
								+ "<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					}
					productCategoryDTO.setProductPriceList(productPriceDTOList);
				}
			}

			obj = SessionUtil.getRow(SessionPdtServ.DelProduct.value(),
					sessionCategoryId);
			if (obj != null) {
				List<Integer> delPriceList = (List<Integer>) obj;
				if (!delPriceList.isEmpty()) {
					productCategoryDTO.setDelProductPriceIdList(delPriceList);
				}
				SessionUtil.deleteRow(SessionPdtServ.DelProduct.value(),
						sessionCategoryId);
			}
			/*
			 * if(categoryIds!=null&&!("".equals(categoryIds))){
			 * List<ProductCategoryDTO> productCategoryList = new
			 * ArrayList<ProductCategoryDTO>(); String[] categoryIdData =
			 * categoryIds.split("<;;>"); String[] ctgDescriptionData =
			 * ctgDescription.split("<;;>"); String[] ctgAstGrpData =
			 * ctgAstGrp.split("<;;>"); if(categoryIdData.length>1){ for(int i =
			 * 1;i<categoryIdData.length;i++){
			 * if(!(categoryIdData[i].equals("-1"))){
			 * System.out.println(categoryIdData[i]); ProductCategory category =
			 * this.productService.getProductCategoryDetail(Integer.valueOf(
			 * categoryIdData[i])); if(category!=null){
			 * category.setAssetGroup(ctgAstGrpData[i]);
			 * if(ctgDescriptionData.length>i-1){
			 * category.setDescription(ctgDescriptionData[i]); }
			 * ProductCategoryDTO categoryDTO = (ProductCategoryDTO)
			 * dozer.map(category, ProductCategoryDTO.class);
			 * productCategoryList.add(categoryDTO); } } }
			 * if(!(productCategoryList.isEmpty())){
			 * productCategoryDTO.setSubCategoryList(productCategoryList); } } }
			 * 
			 * if(productIds!=null&&!("".equals(productIds))){
			 * List<ProductPriceDTO> productPriceDTOList = new
			 * ArrayList<ProductPriceDTO>(); String[] productIdDate =
			 * productIds.split("<;;>"); String[] standrdPricesDate =
			 * standrdPrices.split("<;;>"); String[] priceLimitDate =
			 * limitPrices.split("<;;>"); if(productIdDate.length>1){ for(int i
			 * =1;i<productIdDate.length;i++){
			 * if(!productIdDate[i].equals("-1")){ ProductPriceDTO
			 * productPriceDTO = new ProductPriceDTO();
			 * productPriceDTO.setCatalogId(productCategoryDTO.getCatalogId());
			 * productPriceDTO
			 * .setCategoryId(productCategoryDTO.getCategoryId());
			 * productPriceDTO.setProductId(Integer.valueOf(productIdDate[i]));
			 * if
			 * (standrdPricesDate.length>i-1&&standrdPricesDate[i]!=null&&!("".
			 * equals(standrdPricesDate[i]))){
			 * productPriceDTO.setStandardPrice(Double
			 * .valueOf(standrdPricesDate[i])); }
			 * if(priceLimitDate.length>i-1&&priceLimitDate
			 * [i]!=null&&!("".equals(priceLimitDate[i]))){
			 * productPriceDTO.setLimitPrice(Double.valueOf(priceLimitDate[i]));
			 * } productPriceDTO.setStatus(productCategoryDTO.getStatus());
			 * productPriceDTOList.add(productPriceDTO); } }
			 * if(productPriceDTOList!=null){
			 * productCategoryDTO.setProductPriceList(productPriceDTOList); } }
			 * }
			 */
			// 获取category approved;
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedName.value(),
					sessionCategoryId);
			if (obj != null) {
				productCategoryDTO.setNameApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductCategoryApprovedName.value(),
						sessionCategoryId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedNameReason.value(),
					sessionCategoryId);
			if (obj != null) {
				productCategoryDTO.setNameReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductCategoryApprovedNameReason
								.value(), sessionCategoryId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedStatus.value(),
					sessionCategoryId);
			if (obj != null) {
				productCategoryDTO.setStatusApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductCategoryApprovedStatus.value(),
						sessionCategoryId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedStatusReason.value(),
					sessionCategoryId);
			if (obj != null) {
				productCategoryDTO.setStatusReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ProductCategoryApprovedStatusReason
								.value(), sessionCategoryId);
			}
			ProductCategory productCategory = this.productService
					.savePdtCategory(productCategoryDTO, loginUserId);
			rt.put("message", "The catalog #" + productCategory.getCategoryNo()
					+ " is saved successfully!");
			rt.put("id", productCategory.getCategoryId());
			rt.put("back", "product");
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
			this.productService.delCategoryApproved(statustList, userId,
					approved, approvedReason);
			rt.put("message", SUCCESS);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * Product category Approved session save
	 * 
	 * @param type , name ,reason
	 */
	public String productCategoryApprovedSaveSession() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (SessionPdtServ.ProductCategoryApprovedName.value().equals(
				approvedType)) {
			if (SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedName.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductCategoryApprovedName.value(),
						sessionCategoryId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductCategoryApprovedName.value(),
						sessionCategoryId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedNameReason.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductCategoryApprovedNameReason
								.value(), sessionCategoryId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductCategoryApprovedNameReason
								.value(), sessionCategoryId, approvedReason);
			}

		} else if (SessionPdtServ.ProductCategoryApprovedStatus.value().equals(
				approvedType)) {
			if (SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedStatus.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductCategoryApprovedStatus.value(),
						sessionCategoryId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductCategoryApprovedStatus.value(),
						sessionCategoryId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ProductCategoryApprovedStatusReason.value(),
					sessionCategoryId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductCategoryApprovedStatusReason
								.value(), sessionCategoryId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductCategoryApprovedStatusReason
								.value(), sessionCategoryId, approvedReason);
			}
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * 根据marketing group 获取 marketing staff
	 */
	public String searchMarketingStaff() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String marketGroup = ServletActionContext.getRequest().getParameter(
				"marketGroup");
		List<MarketingStaffDTO> marketingStaffDTO = this.productService
				.searchMarketingStaffByGroup(Integer.valueOf(marketGroup));
		rt.put("marketingStaff", marketingStaffDTO);
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String searchUserName() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String userId = ServletActionContext.getRequest()
				.getParameter("userId");
		User user = this.productService.getUser(Integer.valueOf(userId));
		rt.put("message", SUCCESS);
		rt.put("name", user.getLoginName());
		Struts2Util.renderJson(rt);
		return null;
	}

	// -- 页面属性访问函数 --//
	/**
	 * list页面显示Catalog分页列表.
	 */
	public Page<ProductCategory> getPage() {
		return page;
	}

	public List<PbDropdownListOptions> getCategoryDropdownList() {
		return categoryDropdownList;
	}

	public void setCategoryDropdownList(
			List<PbDropdownListOptions> categoryDropdownList) {
		this.categoryDropdownList = categoryDropdownList;
	}

	public ProductCategoryDTO getProductCategoryDTO() {
		return productCategoryDTO;
	}

	public void setProductCategoryDTO(ProductCategoryDTO productCategoryDTO) {
		this.productCategoryDTO = productCategoryDTO;
	}

	public String getParentCatId() {
		return parentCatId;
	}

	public void setParentCatId(String parentCatId) {
		this.parentCatId = parentCatId;
	}

	public String getParentCategoryNo() {
		return parentCategoryNo;
	}

	public void setParentCategoryNo(String parentCategoryNo) {
		this.parentCategoryNo = parentCategoryNo;
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

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
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

	public String getProductDel() {
		return productDel;
	}

	public void setProductDel(String productDel) {
		this.productDel = productDel;
	}

	public List<Integer> getDelPdtSubCategoryList() {
		return delPdtSubCategoryList;
	}

	public void setDelPdtSubCategoryList(List<Integer> delPdtSubCategoryList) {
		this.delPdtSubCategoryList = delPdtSubCategoryList;
	}

	public List<Integer> getDelPdtPriceList() {
		return delPdtPriceList;
	}

	public void setDelPdtPriceList(List<Integer> delPdtPriceList) {
		this.delPdtPriceList = delPdtPriceList;
	}

	public String getSessionCatalogId() {
		return sessionCatalogId;
	}

	public void setSessionCatalogId(String sessionCatalogId) {
		this.sessionCatalogId = sessionCatalogId;
	}

	public String getProductCategoryKey() {
		return productCategoryKey;
	}

	public void setProductCategoryKey(String productCategoryKey) {
		this.productCategoryKey = productCategoryKey;
	}

	public String getDelProductCategory() {
		return delProductCategory;
	}

	public void setDelProductCategory(String delProductCategory) {
		this.delProductCategory = delProductCategory;
	}

	public String getSessionCategoryId() {
		return sessionCategoryId;
	}

	public void setSessionCategoryId(String sessionCategoryId) {
		this.sessionCategoryId = sessionCategoryId;
	}

	public String getDelSubProductCategory() {
		return delSubProductCategory;
	}

	public void setDelSubProductCategory(String delSubProductCategory) {
		this.delSubProductCategory = delSubProductCategory;
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

	public String getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(String defaultTab) {
		this.defaultTab = defaultTab;
	}

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

}
