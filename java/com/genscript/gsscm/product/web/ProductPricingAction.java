package com.genscript.gsscm.product.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.product.dto.ProductPriceListBeanDTO;
import com.genscript.gsscm.product.dto.ProductSpecialPriceDTO;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.ProductInCategoryBean;
import com.genscript.gsscm.product.entity.ProductPrice;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.product.service.ProductService;

@Results({
		@Result(location = "product/product/pdtServ_pricing_list.jsp"),
		@Result(name = "showEditProductPrice", location = "product/product/product_pricing_edit.jsp"),
		@Result(name = "showAddProductPrice", location = "product/product/product_pricing_add.jsp"),
		@Result(name = "createSpecialPrice", location = "product/product/pdt_special_price_edit.jsp"),
		@Result(name = "editSpecialPrice", location = "product/product/pdt_special_price_edit.jsp"),
		@Result(name = "getPriceChangeHist", location = "product/product/pdt_price_chang_history.jsp"),
		@Result(name = "getProductPriceApprovedSession", location = "product/product/pdt_modify_unitprice.jsp") })
public class ProductPricingAction extends BaseAction<ProductInCategoryBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519644626113358209L;

	@Autowired
	private ProductService productService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DozerBeanMapper dozer;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private Boolean priceAppr;

	private String sessionProductId;
	private ProductInCategoryBean productPrice;
	private Map<String, ProductInCategoryBean> resultMap;
	private Map<String, ProductSpecialPriceDTO> specialResultMap;
	private String id;
	private String specialPriceId;
	private String catalogNo;
	private String catalogId;
	private List<ProductCategory> productCategoryList;
	private List<ProductCategory> productCCategoryList;
	private List<ProductCategory> productBCategoryList;
	private Integer ccategoryId;
	private Integer bcategoryId;

	private String approvedType;
	private String approved;
	private String approvedReason;
	private List<Catalog> retCatalogList;
	private ProductSpecialPriceDTO specialPriceDTO;
	private String vtRatio;
	private String btRatio;
	private List<ProductPriceListBeanDTO> productPriceBeanList;

	// 获取其他列表页面点过来的请求--Zhang Yong
	private String operation_method;

	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		getPriceList();
		Map<String, ProductSpecialPriceDTO> sessPriceMap = (Map<String, ProductSpecialPriceDTO>) SessionUtil
				.getRow(SessionPdtServ.SpecailPrice.value(), sessionProductId);
		if (StringUtils.isNumeric(sessionProductId)) {
			List<ProductSpecialPriceDTO> pdtPriceList = productService
					.getProductSpecailPricingList(Integer
							.parseInt(sessionProductId));
			Map<String, ProductSpecialPriceDTO> returnMap = SessionUtil
					.convertList2Map(pdtPriceList, "id");
			specialResultMap = SessionUtil
					.mergeList(sessPriceMap, returnMap, 1);
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	public String getVtRatio() {
		return vtRatio;
	}

	public void setVtRatio(String vtRatio) {
		this.vtRatio = vtRatio;
	}

	public String getBtRatio() {
		return btRatio;
	}

	public void setBtRatio(String btRatio) {
		this.btRatio = btRatio;
	}

	public String showEditProductPrice() throws Exception {
		productPrice = new ProductInCategoryBean();
		String catalogId = Struts2Util.getParameter("catalogId");
		String sessionProductId = Struts2Util.getParameter("sessionProductId");
		if (StringUtils.isNotEmpty(catalogId)) {
			productPrice.setCatalogId(catalogId);

			// *********** Add By Zhang Yong Start
			// *****************************//
			// 判断将要修改的单号是否正在被操作
			String editUrl = "product/product_pricing!showEditProductPrice.action?catalogId="
					+ catalogId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//
		} else {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
		}
		String categoryId = Struts2Util.getParameter("categoryId");
		if (StringUtils.isNotEmpty(categoryId)) {
			productPrice.setCategoryId(Integer.parseInt(categoryId));
		}
		String unitPrice = Struts2Util.getParameter("unitPrice");
		if (StringUtils.isNotEmpty(unitPrice)) {
			productPrice.setUnitPrice(Double.parseDouble(unitPrice));
		}
		String limitPrice = Struts2Util.getParameter("limitPrice");
		if (StringUtils.isNotEmpty(limitPrice)) {
			productPrice.setLimitPrice(Double.parseDouble(limitPrice));
		}
		productPrice.setCurrencyCode(Struts2Util.getParameter("currency"));
		productCategoryList = productService
				.getPdtCategoryListByCatalogAndCategory(catalogId, null, 3);
		this.productCCategoryList = productService
				.getPdtCategoryListByCatalogAndCategory(catalogId, null, 2);
		this.productBCategoryList = productService
				.getPdtCategoryListByCatalogAndCategory(catalogId, null, 1);

		ProductCategory productCategory = this.productService
				.getProductCategoryDetail(Integer.valueOf(categoryId));
		this.ccategoryId = productCategory.getParentCatId();
		productCategory = this.productService
				.getProductCategoryDetail(ccategoryId);
		this.bcategoryId = productCategory.getParentCatId();
		ProductPrice productPrice = productService.getProductPrice(catalogId,
				Integer.parseInt(categoryId),
				Integer.parseInt(sessionProductId));
		if (productPrice != null && productPrice.getPriceId() != null) {
			priceAppr = productService.checkPropertyApproved(
					productPrice.getPriceId(),
					RequestApproveType.standardPrice.name(),
					RequestApproveType.ProductPrice.name());
		}
		return "showEditProductPrice";
	}

	public String savePriceSession() throws Exception {
		String saveType = Struts2Util.getParameter("saveType");
		String mapKey;
		String isRemove = "flase";
		// ProductPrice price = productService.getProductPrice(catalogId,
		// Integer.parseInt(categoryId));
		Map<String, Object> rt = new HashMap<String, Object>();
		// ProductInCategoryBean price = new ProductInCategoryBean();
		if (saveType.equals("edit")) {
			mapKey = productPrice.getCatalogId();
			isRemove = "true";
			// if(productPrice.getCreatedBy() == null){
			//
			// }
		} else {
			mapKey = SessionUtil.generateTempId();
		}
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessionProductId != null && !("").equals(sessionProductId)) {
			String editUrl = "product/product_pricing!showEditProductPrice.action?catalogId="
					+ sessionProductId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message", SUCCESS);
				rt.put("id", sessionProductId);
				rt.put("isRemove", isRemove);
				Struts2Util.renderJson(rt);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//

		if (StringUtils.isNumeric(sessionProductId)) {
			productPrice.setProductId(Integer.parseInt(sessionProductId));
		}
		System.out.println(productPrice);
		SessionUtil.updateOneRow(SessionConstant.ProductPricing.value(),
				sessionProductId, productPrice.getCatalogId(), productPrice);
		rt.put("message", SUCCESS);
		rt.put("id", productPrice.getCatalogId());
		rt.put("isRemove", isRemove);
		Struts2Util.renderJson(rt);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String delPriceSession() throws Exception {
		String priceIdStr = Struts2Util.getParameter("priceIdStr");
		if (priceIdStr != null) {
			String[] delPrc = priceIdStr.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.DelProductPrice.value(), sessionProductId);
			List<String> delList;
			if (obj != null) {
				delList = (List<String>) obj;
			} else {
				delList = new ArrayList<String>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPrc.length; i++) {
				if (SessionUtil.getOneRow(
						SessionConstant.ProductPricing.value(),
						sessionProductId, delPrc[i]) == null) {
					delList.add(delPrc[i]);
				} else {
					Object priceMap = SessionUtil.getOneRow(
							SessionConstant.ProductPricing.value(),
							sessionProductId, delPrc[i]);
					ProductInCategoryBean price = (ProductInCategoryBean) priceMap;
					if (price.getCreatedBy() == null) {
						SessionUtil.updateOneRow(
								SessionConstant.ProductPricing.value(),
								sessionProductId, delPrc[i], null);
					} else {
						delList.add(delPrc[i]);
					}
				}
			}
			if (!delList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelProductPrice.value(),
							sessionProductId, delList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelProductPrice.value(),
							sessionProductId, delList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	public String showAddProductPrice() throws Exception {
		String catalogIdStr = Struts2Util.getParameter("catalogIdStr");
		List<String> catalogIdList = new ArrayList<String>();
		if (StringUtils.isNotBlank(catalogIdStr)) {
			String[] strArray = catalogIdStr.split(",");
			for (String str : strArray) {
				catalogIdList.add(str);
			}
		}
		retCatalogList = productService.getFilterCatalogList(
				CatalogType.PRODUCT.value(), catalogIdList);
		return "showAddProductPrice";
	}

	public String getCategoryByCatalog() {
		String catalogId = Struts2Util.getParameter("catalogId");
		productCategoryList = productService
				.getPdtCategoryListByCatalog(catalogId);
		Map<String, String> categoryMap = new HashMap<String, String>();
		if (productCategoryList != null && !productCategoryList.isEmpty()) {
			for (ProductCategory cate : productCategoryList) {
				categoryMap.put(cate.getCategoryId() + "", cate.getName());
			}
		}
		Catalog catalog = productService.getCatalogByCatalogId(catalogId);
		StringBuilder sb = new StringBuilder();
		if (categoryMap != null && !categoryMap.isEmpty()) {
			for (Iterator<String> iter = categoryMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				String val = categoryMap.get(key);
				sb.append(key).append("|").append(val).append("#");
			}

		}
		sb.append("$").append(catalog.getCurrencyCode());
		Struts2Util.renderText(sb.toString());
		return NONE;
	}

	public String getCategoryByCatalogAndCategory() {
		String catalogId = Struts2Util.getParameter("catalogId");
		String categoryId = Struts2Util.getParameter("categoryId");
		String categoryLevel = Struts2Util.getParameter("categoryLevel");
		productCategoryList = productService
				.getPdtCategoryListByCatalogAndCategory(catalogId,
						Integer.valueOf(categoryId),
						Integer.valueOf(categoryLevel));
		Struts2Util.renderJson(productCategoryList);
		return null;
	}

	public String productPriceApprovedSaveSession() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String catalogId = Struts2Util.getParameter("catalogId");
		if (SessionPdtServ.ProductPriceApproved.value().equals(approvedType)) {
			if (SessionUtil.getRow(SessionPdtServ.ProductPriceApproved.value(),
					catalogId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductPriceApproved.value(), catalogId,
						approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductPriceApproved.value(), catalogId,
						approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ProductPriceApprovedReason.value(),
					catalogId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ProductPriceApprovedReason.value(),
						catalogId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ProductPriceApprovedReason.value(),
						catalogId, approvedReason);
			}
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String getProductPriceApprovedSession() {
		String catalogId = Struts2Util.getParameter("catalogId");
		approved = (String) SessionUtil.getRow(
				SessionPdtServ.ProductPriceApproved.value(), catalogId);
		approvedReason = (String) SessionUtil.getRow(
				SessionPdtServ.ProductPriceApprovedReason.value(), catalogId);

		return "getProductPriceApprovedSession";
	}

	public String createSpecialPrice() throws Exception {
		initDropDownList();
		String baseCatalogId = productService.getBaseCatalogId();
		Double unitPrice = productService.getUnitPriceByBaseCatalog(
				baseCatalogId, Integer.parseInt(sessionProductId));
		if (specialPriceDTO == null) {
			specialPriceDTO = new ProductSpecialPriceDTO();
		}
		specialPriceDTO.setStandardPrice(unitPrice);
		Struts2Util.getRequest().setAttribute("baseCatalogId", baseCatalogId);
		return "createSpecialPrice";
	}

	/**
     *
     */
	private void initDropDownList() {
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
	}

	public String editSpecialPrice() throws Exception {
		initDropDownList();
		if (specialPriceId != null) {
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.SpecailPrice.value(), sessionProductId,
					specialPriceId);
			if (obj != null) {
				specialPriceDTO = (ProductSpecialPriceDTO) obj;
			} else {
				ProductSpecialPrice productSpecPrice = this.productService
						.getProductSpecialPriceById(Integer
								.valueOf(specialPriceId));
				specialPriceDTO = dozer.map(productSpecPrice,
						ProductSpecialPriceDTO.class);
			}
		}
		return "editSpecialPrice";
	}

	public String saveSpecialPriceSession() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String priceKey;
		String isRemove = "flase";
		System.out.println(">>>>>>>>>>>" + specialPriceDTO);
		if (specialPriceDTO.getId() == null) {
			priceKey = SessionUtil.generateTempId();
			String baseCatalogId = productService.getBaseCatalogId();
			specialPriceDTO.setCatalogId(baseCatalogId);
			specialPriceDTO.setStatus("ACTIVE");
		} else {
			priceKey = specialPriceDTO.getId().toString();
			isRemove = "true";
		}
		SessionUtil.updateOneRow(SessionPdtServ.SpecailPrice.value(),
				sessionProductId, priceKey, specialPriceDTO);
		rt.put("message", SUCCESS);
		rt.put("id", priceKey);
		rt.put("isRemove", isRemove);
		Struts2Util.renderJson(rt);
		return NONE;
	}

	@SuppressWarnings("unchecked")
	public String delSpecialPriceSession() {
		String specialPriceIdStr = Struts2Util
				.getParameter("specialPriceIdStr");
		if (specialPriceIdStr != null) {
			String[] delPrc = specialPriceIdStr.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.DelSpecailPrice.value(), sessionProductId);
			List<Integer> delList;
			if (obj != null) {
				delList = (List<Integer>) obj;
			} else {
				delList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPrc.length; i++) {
				if (SessionUtil.getOneRow(SessionPdtServ.SpecailPrice.value(),
						sessionProductId, delPrc[i]) == null) {
					delList.add(Integer.valueOf(delPrc[i]));
				} else {
					Object priceMap = SessionUtil.getOneRow(
							SessionPdtServ.SpecailPrice.value(),
							sessionProductId, delPrc[i]);
					ProductSpecialPriceDTO productSpecialPriceDTO = (ProductSpecialPriceDTO) priceMap;
					if (productSpecialPriceDTO.getId() == null) {
						SessionUtil.updateOneRow(
								SessionPdtServ.SpecailPrice.value(),
								sessionProductId, delPrc[i], null);
					} else {
						delList.add(Integer.valueOf(delPrc[i]));
					}
				}
			}
			if (!delList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelSpecailPrice.value(),
							sessionProductId, delList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelSpecailPrice.value(),
							sessionProductId, delList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public String getPriceChangeHist() throws Exception {
		if (StringUtils.isNumeric(sessionProductId)) {
			System.out.println(sessionProductId + "<<<<<<<<<<<<<<<<");
			productPriceBeanList = productService
					.getProductPriceApproveList(Integer
							.parseInt(sessionProductId));
			System.out.println(sessionProductId + "<<<<<<<<<<<<<<<<");
		}
		System.out.println("productPriceBeanList: "
				+ productPriceBeanList.size());
		return "getPriceChangeHist";
	}

	/**
     *
     */
	@SuppressWarnings("unchecked")
	private void getPriceList() {
		Map<String, ProductInCategoryBean> sessPriceMap = (Map<String, ProductInCategoryBean>) SessionUtil
				.getRow(SessionConstant.ProductPricing.value(),
						sessionProductId);
		List<ProductInCategoryBean> pdtOfCategoryBeanList = productService
				.getProductPricingList(catalogNo);
		Map<String, ProductInCategoryBean> returnMap = SessionUtil
				.convertList2Map(pdtOfCategoryBeanList, "catalogId");
		resultMap = SessionUtil.mergeList(sessPriceMap, returnMap, 1);
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// if (StringUtils.isNotEmpty(id)) {
		// if (!StringUtils.isNumeric(id)) {
		// productPrice = (ProductInCategoryBean) SessionUtil
		// .getOneRow(SessionConstant.ProductPricing.value(),
		// sessionProductId, id);
		// } else {
		// Map<String, Object> sessionMap = (HashMap<String, Object>)
		// SessionUtil
		// .getRow(SessionConstant.ProductPricing.value(),
		// sessionProductId);
		// if (sessionMap != null && !sessionMap.isEmpty()) {
		// if (sessionMap.containsKey(id)) {
		// productPrice = (ProductInCategoryBean) sessionMap
		// .get(id);
		// } else {
		// productPrice = productService
		// .getProductPricingDetail(Integer.parseInt(id));
		// }
		// } else {
		// productPrice = productService
		// .getProductPricingDetail(Integer.parseInt(id));
		// }
		// }
		// } else {
		// productPrice = new ProductInCategoryBean();
		// }
		if (StringUtils.isNotEmpty(specialPriceId)) {
			ProductSpecialPrice productSpecPrice = this.productService
					.getProductSpecialPriceById(Integer.valueOf(specialPriceId));
			specialPriceDTO = dozer.map(productSpecPrice,
					ProductSpecialPriceDTO.class);
		} else {
			specialPriceDTO = new ProductSpecialPriceDTO();
		}
	}

	@Override
	public ProductInCategoryBean getModel() {
		return productPrice;
	}

	public String getSessionProductId() {
		return sessionProductId;
	}

	public void setSessionProductId(String sessionProductId) {
		this.sessionProductId = sessionProductId;
	}

	public Map<String, ProductInCategoryBean> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, ProductInCategoryBean> resultMap) {
		this.resultMap = resultMap;
	}

	public ProductInCategoryBean getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductInCategoryBean productPrice) {
		this.productPrice = productPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public String getApprovedType() {
		return approvedType;
	}

	public void setApprovedType(String approvedType) {
		this.approvedType = approvedType;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApprovedReason() {
		return approvedReason;
	}

	public void setApprovedReason(String approvedReason) {
		this.approvedReason = approvedReason;
	}

	public List<Catalog> getRetCatalogList() {
		return retCatalogList;
	}

	public void setRetCatalogList(List<Catalog> retCatalogList) {
		this.retCatalogList = retCatalogList;
	}

	public Map<String, ProductSpecialPriceDTO> getSpecialResultMap() {
		return specialResultMap;
	}

	public void setSpecialResultMap(
			Map<String, ProductSpecialPriceDTO> specialResultMap) {
		this.specialResultMap = specialResultMap;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public ProductSpecialPriceDTO getSpecialPriceDTO() {
		return specialPriceDTO;
	}

	public void setSpecialPriceDTO(ProductSpecialPriceDTO specialPriceDTO) {
		this.specialPriceDTO = specialPriceDTO;
	}

	public String getSpecialPriceId() {
		return specialPriceId;
	}

	public void setSpecialPriceId(String specialPriceId) {
		this.specialPriceId = specialPriceId;
	}

	public Boolean getPriceAppr() {
		return priceAppr;
	}

	public void setPriceAppr(Boolean priceAppr) {
		this.priceAppr = priceAppr;
	}

	public List<ProductPriceListBeanDTO> getProductPriceBeanList() {
		return productPriceBeanList;
	}

	public void setProductPriceBeanList(
			List<ProductPriceListBeanDTO> productPriceBeanList) {
		this.productPriceBeanList = productPriceBeanList;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public List<ProductCategory> getProductCCategoryList() {
		return productCCategoryList;
	}

	public void setProductCCategoryList(
			List<ProductCategory> productCCategoryList) {
		this.productCCategoryList = productCCategoryList;
	}

	public List<ProductCategory> getProductBCategoryList() {
		return productBCategoryList;
	}

	public void setProductBCategoryList(
			List<ProductCategory> productBCategoryList) {
		this.productBCategoryList = productBCategoryList;
	}

	public Integer getCcategoryId() {
		return ccategoryId;
	}

	public void setCcategoryId(Integer ccategoryId) {
		this.ccategoryId = ccategoryId;
	}

	public Integer getBcategoryId() {
		return bcategoryId;
	}

	public void setBcategoryId(Integer bcategoryId) {
		this.bcategoryId = bcategoryId;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}
}
