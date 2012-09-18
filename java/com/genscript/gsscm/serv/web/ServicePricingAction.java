package com.genscript.gsscm.serv.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genscript.gsscm.serv.dto.ServicePriceListBeanDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.ServiceDetailsType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceIntermediateDTO;
import com.genscript.gsscm.serv.dto.ServicePriceDTO;
import com.genscript.gsscm.serv.entity.PriceRuleGroups;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServicePrice;
import com.genscript.gsscm.serv.entity.ServiceSubStepPrice;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.serv.service.ServicePriceRuleService;
import com.opensymphony.xwork2.Action;

@Results({
		@Result(name = "getPriceChangeHist", location = "service/service/service_price_chang_history.jsp"),
		@Result(name = Action.SUCCESS, location = "service/service/service_price.jsp") })
public class ServicePricingAction extends BaseAction<ServiceDTO> {

	/**
     *
     */
	private static final long serialVersionUID = -740231828274324179L;

	@Autowired
	private ServService servService;

	@Autowired
	private ServicePriceRuleService priceRuleService;

	@Autowired
	private ProductService productService;

	private List<Catalog> catalog;// 返回给页面所有type=service or all and status =
									// active;
	private List<ServiceCategory> serviceCategoryOfPriceList;
	private List<ServiceCategory> serviceBCategoryOfPriceList;
	private List<ServiceCategory> serviceCCategoryOfPriceList;
	private ServicePrice servicePrice;// 如果service detatail = 返回给页面service price
	private ServiceSubStepPrice subStepPrice;
	private String serviceType;
	private List<ServiceIntermediateDTO> breakDown;
	private List<ServiceSubSteps> subSteps;
	private List<PriceRuleGroups> group;
	private Integer clsId;
	private Integer serviceId;
	private String returnType;
	private String currencyCode;
	private String sessionServiceId;
	private Integer bcategoryId;
	private Integer ccategoryId;
	private Integer id;// catalog 中的id;
	private String vtRatio;
	private String btRatio;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * service price return catalog return service detail return serviceprice
	 */
	public String searchServicePrice() {
		this.catalog = this.productService.getCatalogList(CatalogType.SERVICE
				.value());
		if (catalog.isEmpty()) {
			return null;
		} else {
			currencyCode = catalog.get(0).getCurrencyCode();
			if (catalog.get(0) != null && !"".equals(catalog.get(0))) {
				id = catalog.get(0).getId();

				this.serviceBCategoryOfPriceList = this.servService
						.getServiceCategoryByCatalogId(catalog.get(0)
								.getCatalogId(), null, 1);
				if (serviceBCategoryOfPriceList != null
						&& !serviceBCategoryOfPriceList.isEmpty()) {
					if (serviceBCategoryOfPriceList.get(0) != null
							&& !"".equals(serviceBCategoryOfPriceList.get(0))) {
						this.serviceCCategoryOfPriceList = this.servService
								.getServiceCategoryByCatalogId(catalog.get(0)
										.getCatalogId(),
										serviceBCategoryOfPriceList.get(0)
												.getCategoryId(), 2);

						if (serviceCCategoryOfPriceList != null
								&& !serviceCCategoryOfPriceList.isEmpty()) {
							this.serviceCategoryOfPriceList = this.servService
									.getServiceCategoryByCatalogId(
											catalog.get(0).getCatalogId(),
											serviceCCategoryOfPriceList.get(0)
													.getCategoryId(), 3);
						} else {
							this.serviceCategoryOfPriceList = new ArrayList<ServiceCategory>();
						}
					}
				}
			} else {
				this.serviceCCategoryOfPriceList = new ArrayList<ServiceCategory>();
				this.serviceCategoryOfPriceList = new ArrayList<ServiceCategory>();
			}
			this.serviceType = serviceType.toLowerCase();
			if (serviceType == null || serviceType.equals("")) {
				this.servicePrice = new ServicePrice();
				this.returnType = "breakDown";
			} else if (serviceType.equals(ServiceDetailsType.PROTEIN.value())
					|| serviceType
							.equals(ServiceDetailsType.BIOPROCESS.value())
					|| serviceType.equals(ServiceDetailsType.PHARMACEUTICAL
							.value())
					|| serviceType.equals(ServiceDetailsType.ANTIBODYDRUG
							.value())) {
				breakDown = this.servService
						.getServiceIntermediateAllList(serviceId);
				if (breakDown.isEmpty()) {
					this.servicePrice = new ServicePrice();
					this.group = new ArrayList<PriceRuleGroups>();
				} else {
					List<com.genscript.gsscm.serv.entity.Service> serv = null;
					if (breakDown.get(0) != null
							&& !"".equals(breakDown.get(0))) {
						serv = this.servService.getServiceByCatalogNo(breakDown
								.get(0).getIntmdCatalogNo());
					}
					if (serv.isEmpty()) {
						this.servicePrice = new ServicePrice();
						this.group = new ArrayList<PriceRuleGroups>();
					} else {
						if(catalog!=null && subSteps!=null){
						if (catalog.get(0) != null
								&& !"".equals(catalog.get(0))
								&& subSteps.get(0) != null
								&& !"".equals(subSteps.get(0))) {
							this.servicePrice = this.servService
									.getServicePriceByServiceIdAndCatalogId(
											catalog.get(0).getCatalogId(), serv
													.get(0).getServiceId());
						
						}
					}
						this.group = this.priceRuleService
									.searchPriceRuleGroupListByClsId(serv
											.get(0).getServiceClsId());
					}
				}
				this.returnType = "breakDown";
			} else if (serviceType.equals(ServiceDetailsType.PROTEINSTEP
					.value())
					|| serviceType.equals(ServiceDetailsType.BIOPROCESSSTEP
							.value())
					|| serviceType.equals(ServiceDetailsType.PHARMACEUTICALSTEP
							.value())
					|| serviceType.equals(ServiceDetailsType.ANTIBODYDRUGSTEP
							.value())) {
				subSteps = this.servService.getServiceSubStepsList(serviceId);
				System.out.println(subSteps.size() + "<<<<<<<<<<<<<<<<");
				this.group = new ArrayList<PriceRuleGroups>();
				if (subSteps.isEmpty()) {
					ServiceSubStepPrice ssp = new ServiceSubStepPrice();
					if (catalog.get(0) != null && !"".equals(catalog.get(0))) {
						ssp.setCatalogId(catalog.get(0).getCatalogId());
					}
					ssp.setLimitPrice(0.0);
					ssp.setStepId(1);
					this.servService.saveSubStepPrice(ssp);
					subStepPrice=new ServiceSubStepPrice();
				 	if (catalog !=null && subSteps!=null && catalog.get(0) != null && !"".equals(catalog.get(0))
							&& subSteps.get(0) != null
							&& !"".equals(subSteps.get(0))) {
						subStepPrice = this.servService.getSubStepPrice(catalog
								.get(0).getCatalogId(), subSteps.get(0)
								.getStepId());
					} 
					this.group = this.priceRuleService
							.searchPriceRuleGroupListByClsId(clsId);
					
				} else {
					if (catalog.get(0) != null && !"".equals(catalog.get(0))
							&& subSteps.get(0) != null
							&& !"".equals(subSteps.get(0))) {
						subStepPrice = this.servService.getSubStepPrice(catalog
								.get(0).getCatalogId(), subSteps.get(0)
								.getStepId());
					}
					if (subStepPrice != null) {
						System.out.println(">>>>>>>>>>>."
								+ subStepPrice.getPriceId());
					} else {
						ServiceSubStepPrice ssp = new ServiceSubStepPrice();
						if (catalog.get(0) != null
								&& !"".equals(catalog.get(0))) {
							ssp.setCatalogId(catalog.get(0).getCatalogId());
						}
						ssp.setRetailPrice(0.0);
						ssp.setStepId(1);
						this.servService.saveSubStepPrice(ssp);
						if (catalog.get(0) != null
								&& !"".equals(catalog.get(0))) {
							subStepPrice = this.servService.getSubStepPrice(
									catalog.get(0).getCatalogId(), 1);
						}
						System.out.println(">>>>>>>>>>>."
								+ subStepPrice.getPriceId());
					}
					this.group = this.priceRuleService
							.searchPriceRuleGroupListByClsId(clsId);
				}
				this.returnType = "subSteps";
			} else {
				breakDown = null;
				this.servicePrice = this.servService
						.getServicePriceByServiceIdAndCatalogId(catalog.get(0)
								.getCatalogId(), serviceId);
				if (servicePrice != null
						&& servicePrice.getCategoryId() != null) {
					ServiceCategory pcategory = this.servService
							.getServiceCategoryDetail(servicePrice
									.getCategoryId());
					if (pcategory != null && pcategory.getParentCatId() != null) {
						ServiceCategory ccategory = this.servService
								.getServiceCategoryDetail(pcategory
										.getParentCatId());
						if (ccategory != null
								&& ccategory.getParentCatId() != null) {
							this.ccategoryId = ccategory.getCategoryId();
							ServiceCategory bcategory = this.servService
									.getServiceCategoryDetail(ccategory
											.getParentCatId());
							if (bcategory != null) {
								this.bcategoryId = bcategory.getCategoryId();
							}
						}
					}
				}
				this.group = this.priceRuleService
						.searchPriceRuleGroupListByClsId(clsId);
				this.returnType = "serviceId";
			}
		}
		return SUCCESS;
	}

	public String searchServicePriceByCategoryIdServiceId() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String catalogId = ServletActionContext.getRequest().getParameter(
				"catalogId");
		String catalogNo = ServletActionContext.getRequest().getParameter(
				"catalogNo");
		String returnType = ServletActionContext.getRequest().getParameter(
				"returnType");
		String serviceIdDetail = ServletActionContext.getRequest()
				.getParameter("serviceIdDetail");
		String key = null;
		if ("serviceId".equals(returnType)) {
			key = catalogId + "_" + serviceIdDetail;
		} else {
			key = catalogId + "_" + catalogNo;
		}
		Object obj = SessionUtil.getOneRow(
				SessionPdtServ.ServicePricing.value(), sessionServiceId, key);
		if (obj != null) {
			this.servicePrice = (ServicePrice) obj;
		} else {
			if ("serviceId".equals(returnType)) {
				this.servicePrice = this.servService
						.getServicePriceByServiceIdAndCatalogId(catalogId,
								Integer.valueOf(serviceIdDetail));
			} else {
				List<com.genscript.gsscm.serv.entity.Service> serv = this.servService
						.getServiceByCatalogNo(catalogNo);
				if (serv.isEmpty()) {
					this.servicePrice = new ServicePrice();
					this.group = new ArrayList<PriceRuleGroups>();
				} else {
					this.servicePrice = this.servService
							.getServicePriceByServiceIdAndCatalogId(catalogId,
									serv.get(0).getServiceId());
					this.group = this.priceRuleService
							.searchPriceRuleGroupListByClsId(serv.get(0)
									.getServiceClsId());
				}
			}

		}
		Catalog catg = this.productService.getCatalogByCatalogId(catalogId);
		rt.put("priceAppr", this.getPriceAppr());
		rt.put("group", group);
		rt.put("currencyCode", catg.getCurrencyCode());
		rt.put("id", catg.getId());
		rt.put("message", SUCCESS);
		rt.put("servicePrice", servicePrice);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String searchServicePriceByCatalogId() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String catalogId = ServletActionContext.getRequest().getParameter(
				"catalogId");
		String catalogNo = ServletActionContext.getRequest().getParameter(
				"catalogNo");
		String returnType = ServletActionContext.getRequest().getParameter(
				"returnType");
		String serviceIdDetail = ServletActionContext.getRequest()
				.getParameter("serviceIdDetail");
		this.serviceBCategoryOfPriceList = this.servService
				.getServiceCategoryByCatalogId(catalogId, null, 1);
		if (serviceBCategoryOfPriceList != null
				&& !serviceBCategoryOfPriceList.isEmpty()) {
			this.serviceCCategoryOfPriceList = this.servService
					.getServiceCategoryByCatalogId(catalogId,
							serviceBCategoryOfPriceList.get(0).getCategoryId(),
							2);
			if (serviceCCategoryOfPriceList != null
					&& !serviceCCategoryOfPriceList.isEmpty()) {
				this.serviceCategoryOfPriceList = this.servService
						.getServiceCategoryByCatalogId(catalogId,
								serviceCCategoryOfPriceList.get(0)
										.getCategoryId(), 3);
			} else {
				this.serviceCategoryOfPriceList = new ArrayList<ServiceCategory>();
			}
		} else {
			this.serviceCCategoryOfPriceList = new ArrayList<ServiceCategory>();
			this.serviceCategoryOfPriceList = new ArrayList<ServiceCategory>();
		}

		if (serviceCategoryOfPriceList == null
				|| serviceCategoryOfPriceList.isEmpty()) {
			this.servicePrice = new ServicePrice();
		} else {

			String key = null;
			if ("serviceId".equals(returnType)) {
				key = catalogId + "_" + serviceIdDetail;
			} else {
				key = catalogId + "_" + catalogNo;
			}
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.ServicePricing.value(), sessionServiceId,
					key);
			if (obj != null) {
				this.servicePrice = (ServicePrice) obj;
				if (servicePrice != null
						&& servicePrice.getCategoryId() != null) {
					ServiceCategory pcategory = this.servService
							.getServiceCategoryDetail(servicePrice
									.getCategoryId());
					if (pcategory != null && pcategory.getParentCatId() != null) {
						ServiceCategory ccategory = this.servService
								.getServiceCategoryDetail(pcategory
										.getParentCatId());
						if (ccategory != null) {
							this.ccategoryId = ccategory.getCategoryId();
							if (ccategory.getParentCatId() != null) {
								ServiceCategory bcategory = this.servService
										.getServiceCategoryDetail(ccategory
												.getParentCatId());
								if (bcategory != null) {
									this.bcategoryId = bcategory
											.getCategoryId();
								}
							}
						}
					}
				}
			} else {
				if ("serviceId".equals(returnType)) {
					this.servicePrice = this.servService
							.getServicePriceByServiceIdAndCatalogId(catalogId,
									Integer.valueOf(serviceIdDetail));
					if (servicePrice != null
							&& servicePrice.getCategoryId() != null) {
						ServiceCategory pcategory = this.servService
								.getServiceCategoryDetail(servicePrice
										.getCategoryId());
						if (pcategory != null
								&& pcategory.getParentCatId() != null) {
							ServiceCategory ccategory = this.servService
									.getServiceCategoryDetail(pcategory
											.getParentCatId());
							if (ccategory != null) {
								this.ccategoryId = ccategory.getCategoryId();
								if (ccategory.getParentCatId() != null) {
									ServiceCategory bcategory = this.servService
											.getServiceCategoryDetail(ccategory
													.getParentCatId());
									if (bcategory != null) {
										this.bcategoryId = bcategory
												.getCategoryId();
									}
								}
							}
						}
					}
				} else {
					List<com.genscript.gsscm.serv.entity.Service> serv = this.servService
							.getServiceByCatalogNo(catalogNo);
					if (serv.isEmpty()) {
						this.servicePrice = new ServicePrice();
					} else {
						this.servicePrice = this.servService
								.getServicePriceByServiceIdAndCatalogId(
										catalogId, serv.get(0).getServiceId());

					}
				}

			}
		}
		Catalog catg = this.productService.getCatalogByCatalogId(catalogId);
		rt.put("priceAppr", this.getPriceAppr());
		rt.put("serviceCategoryOfPriceList", serviceCategoryOfPriceList);
		rt.put("serviceBCategoryOfPriceList", serviceBCategoryOfPriceList);
		rt.put("serviceCCategoryOfPriceList", serviceCCategoryOfPriceList);
		rt.put("ccategoryId", this.ccategoryId);
		rt.put("bcategoryId", this.bcategoryId);
		rt.put("currencyCode", catg.getCurrencyCode());
		rt.put("id", catg.getId());
		rt.put("message", SUCCESS);
		rt.put("servicePrice", servicePrice);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String getCategoryByCatalogAndCategory() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String catalogId = Struts2Util.getParameter("catalogId");
		String categoryId = Struts2Util.getParameter("categoryId");
		String categoryLevel = Struts2Util.getParameter("categoryLevel");
		if (categoryLevel.equals("2")) {
			this.serviceCCategoryOfPriceList = this.servService
					.getServiceCategoryByCatalogId(catalogId,
							Integer.valueOf(categoryId), 2);
			categoryLevel = "3";
			if (this.serviceCCategoryOfPriceList != null
					&& !this.serviceCCategoryOfPriceList.isEmpty()) {
				this.serviceCategoryOfPriceList = this.servService
						.getServiceCategoryByCatalogId(catalogId,
								serviceCCategoryOfPriceList.get(0)
										.getCategoryId(), 3);
			}
		} else if (categoryLevel.equals("3")) {
			this.serviceCategoryOfPriceList = this.servService
					.getServiceCategoryByCatalogId(catalogId,
							Integer.valueOf(categoryId), 3);
		}
		rt.put("serviceCategoryOfPriceList", serviceCategoryOfPriceList);
		rt.put("serviceCCategoryOfPriceList", serviceCCategoryOfPriceList);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String searchSubStepPrice() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String catalogId = ServletActionContext.getRequest().getParameter(
				"catalogId");
		String stepId = ServletActionContext.getRequest()
				.getParameter("stepId");
		String key = catalogId + "_" + stepId;
		Object obj = SessionUtil.getOneRow(
				SessionPdtServ.ServiceSubStepPricing.value(), sessionServiceId,
				key);
		if (obj != null) {
			this.subStepPrice = (ServiceSubStepPrice) obj;
		} else {
			this.subStepPrice = this.servService.getSubStepPrice(catalogId,
					Integer.valueOf(stepId));
		}
		Catalog catg = this.productService.getCatalogByCatalogId(catalogId);
		rt.put("priceAppr", this.getPriceAppr());
		rt.put("currencyCode", catg.getCurrencyCode());
		rt.put("id", catg.getId());
		rt.put("message", SUCCESS);
		rt.put("subStepPrice", subStepPrice);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String saveServicePriceToSession() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String returnType = ServletActionContext.getRequest().getParameter(
				"returnType");
		String serviceIdDetail = ServletActionContext.getRequest()
				.getParameter("serviceIdDetail");
		String catalogNo = ServletActionContext.getRequest().getParameter(
				"catalogNo");
		String key = null;
		if ("serviceId".equals(returnType)) {
			servicePrice.setServiceId(Integer.valueOf(serviceIdDetail));
			key = servicePrice.getCatalogId() + "_"
					+ servicePrice.getServiceId();
		} else {
			List<com.genscript.gsscm.serv.entity.Service> serv = this.servService
					.getServiceByCatalogNo(catalogNo);
			servicePrice.setServiceId(serv.get(0).getServiceId());
			key = servicePrice.getCatalogId() + "_" + catalogNo;
		}

		ServicePrice sp = this.servService
				.getServicePriceByServiceIdAndCatalogId(
						servicePrice.getCatalogId(),
						servicePrice.getServiceId());
		if (sp == null) {
			sp = servicePrice;
		} else {
			sp.setLimitPrice(servicePrice.getLimitPrice());
			sp.setStandardPrice(servicePrice.getStandardPrice());
			sp.setPriceRuleGroup(servicePrice.getPriceRuleGroup());
			sp.setCategoryId(servicePrice.getCategoryId());
		}
		SessionUtil.updateOneRow(SessionPdtServ.ServicePricing.value(),
				sessionServiceId, key, sp);
		rt.put("key", key);
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String saveServiceSubStepPriceToSession() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String key = subStepPrice.getCatalogId() + "_"
				+ subStepPrice.getStepId();
		ServiceSubStepPrice sssp = this.servService.getSubStepPrice(
				subStepPrice.getCatalogId(), subStepPrice.getStepId());
		if (sssp == null) {
			sssp = subStepPrice;
		} else {
			sssp.setLimitPrice(subStepPrice.getLimitPrice());
			sssp.setRetailPrice(subStepPrice.getRetailPrice());
			sssp.setCatalogId(subStepPrice.getCatalogId());
		}
		SessionUtil.updateOneRow(SessionPdtServ.ServiceSubStepPricing.value(),
				sessionServiceId, key, sssp);
		rt.put("key", key);
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	public String priceApprovedSaveSession() {
		Map<String, Object> rt = new HashMap<String, Object>();
		String key = Struts2Util.getParameter("priceId");
		String approved = Struts2Util.getParameter("approved");
		String groupApproved = Struts2Util.getParameter("groupApproved");
		String approvedReason = Struts2Util.getParameter("approvedReason");
		String oldVal = Struts2Util.getParameter("oldPriceApproved");
		ServicePriceDTO dto = new ServicePriceDTO();
		if (key == null) {
			key = Struts2Util.getParameter("subPriceId");
			if (key != null) {
				dto.setPriceId(Integer.valueOf(key));
				dto.setServiceSubPriceApprove(Double.valueOf(approved));
				dto.setServicePriceReason(approvedReason);
				dto.setServicePriceOldVal(Double.valueOf(oldVal));
				SessionUtil.updateOneRow(
						SessionPdtServ.ServiceSubPriceApproved.value(),
						sessionServiceId, key, dto);
				rt.put("key", key);
			}
		} else {
			dto.setPriceId(Integer.valueOf(key));
			if (groupApproved != null && !groupApproved.equals("")) {
				dto.setServiceRuleGroupPriceApprove(Integer
						.valueOf(groupApproved));
			}
			if (approved != null && !approved.equals("")) {
				dto.setServicePriceApprove(Double.valueOf(approved));
			}
			dto.setServicePriceReason(approvedReason);
			if (oldVal != null && !oldVal.equals("")) {
				dto.setServicePriceOldVal(Double.valueOf(oldVal));
			} else {
				dto.setServicePriceOldVal(Double.valueOf(0));
			}

			SessionUtil.updateOneRow(
					SessionPdtServ.ServicePriceApproved.value(),
					sessionServiceId, key, dto);
			rt.put("key", key);
		}

		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ServService getServService() {
		return servService;
	}

	public void setServService(ServService servService) {
		this.servService = servService;
	}

	public ServicePriceRuleService getPriceRuleService() {
		return priceRuleService;
	}

	public void setPriceRuleService(ServicePriceRuleService priceRuleService) {
		this.priceRuleService = priceRuleService;
	}

	// /------------add by zhougang 2011 05 17-

	private List<ServicePriceListBeanDTO> servicePriceListBeanDTOList;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<ServicePriceListBeanDTO> getServicePriceListBeanDTOList() {
		return servicePriceListBeanDTOList;
	}

	public void setServicePriceListBeanDTOList(
			List<ServicePriceListBeanDTO> servicePriceListBeanDTOList) {
		this.servicePriceListBeanDTOList = servicePriceListBeanDTOList;
	}

	// ------------------------------------------------------------------
	/*
	 * getPriceChangeHist2substeps 按照serviceId 和 breakdown 两种类型来检索价格变动信息
	 * zhougang 2011 05 30
	 */
	public String getPriceChangeHist2seviceIdandbreakdown() throws Exception {
		if (StringUtils.isNumeric(sessionServiceId)) {
			servicePriceListBeanDTOList = servService
					.getServicePriceApproveList2breakDown(
							Integer.parseInt(sessionServiceId), returnType,
							breakDown);
		}
		return "getPriceChangeHist";
	}

	/*
	 * getPriceChangeHist2substeps 读取substeps 类型的价格变动列表 zhougang 2011 05 30
	 */

	public String getPriceChangeHist2substeps() throws Exception {
		if (StringUtils.isNumeric(sessionServiceId)) {
			servicePriceListBeanDTOList = servService
					.getServicePriceApproveList(Integer
							.parseInt(sessionServiceId));
		}
		return "getPriceChangeHist";
	}

	// ---------------------------------------
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ServicePrice getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(ServicePrice servicePrice) {
		this.servicePrice = servicePrice;
	}

	public List<Catalog> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<Catalog> catalog) {
		this.catalog = catalog;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public List<ServiceIntermediateDTO> getBreakDown() {
		return breakDown;
	}

	public void setBreakDown(List<ServiceIntermediateDTO> breakDown) {
		this.breakDown = breakDown;
	}

	public List<ServiceSubSteps> getSubSteps() {
		return subSteps;
	}

	public void setSubSteps(List<ServiceSubSteps> subSteps) {
		this.subSteps = subSteps;
	}

	public ServiceSubStepPrice getSubStepPrice() {
		return subStepPrice;
	}

	public void setSubStepPrice(ServiceSubStepPrice subStepPrice) {
		this.subStepPrice = subStepPrice;
	}

	public List<PriceRuleGroups> getGroup() {
		return group;
	}

	public void setGroup(List<PriceRuleGroups> group) {
		this.group = group;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getSessionServiceId() {
		return sessionServiceId;
	}

	public void setSessionServiceId(String sessionServiceId) {
		this.sessionServiceId = sessionServiceId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ServiceCategory> getServiceCategoryOfPriceList() {
		return serviceCategoryOfPriceList;
	}

	public void setServiceCategoryOfPriceList(
			List<ServiceCategory> serviceCategoryOfPriceList) {
		this.serviceCategoryOfPriceList = serviceCategoryOfPriceList;
	}

	public String getPriceAppr() {
		Integer id = null;
		String type = null;
		if (servicePrice != null && servicePrice.getPriceId() != null) {
			id = servicePrice.getPriceId();
			type = RequestApproveType.ServicePrice.name();
		}
		if (subStepPrice != null && subStepPrice.getPriceId() != null) {
			id = subStepPrice.getPriceId();
			type = RequestApproveType.SubServicePrice.name();
		}
		if (id == null) {
			return "false";
		}
		Boolean approv = servService.checkPropertyApproved(id, type,
				RequestApproveType.ServicePrice.name());
		if (approv == false) {
			approv = servService.checkPropertyApproved(id,
					RequestApproveType.ServicePriceGroup.name(),
					RequestApproveType.ServicePrice.name());
		}
		return approv.toString();
	}

	public List<ServiceCategory> getServiceBCategoryOfPriceList() {
		return serviceBCategoryOfPriceList;
	}

	public void setServiceBCategoryOfPriceList(
			List<ServiceCategory> serviceBCategoryOfPriceList) {
		this.serviceBCategoryOfPriceList = serviceBCategoryOfPriceList;
	}

	public List<ServiceCategory> getServiceCCategoryOfPriceList() {
		return serviceCCategoryOfPriceList;
	}

	public void setServiceCCategoryOfPriceList(
			List<ServiceCategory> serviceCCategoryOfPriceList) {
		this.serviceCCategoryOfPriceList = serviceCCategoryOfPriceList;
	}

	public Integer getBcategoryId() {
		return bcategoryId;
	}

	public void setBcategoryId(Integer bcategoryId) {
		this.bcategoryId = bcategoryId;
	}

	public Integer getCcategoryId() {
		return ccategoryId;
	}

	public void setCcategoryId(Integer ccategoryId) {
		this.ccategoryId = ccategoryId;
	}

}
