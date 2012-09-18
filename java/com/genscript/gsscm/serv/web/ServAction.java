package com.genscript.gsscm.serv.web;

import java.awt.Color;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.CommonSearchService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.constant.ServiceDetailsType;
import com.genscript.gsscm.common.constant.SessionPdtServ;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.order.dto.ProductServiceSaleDTO;
import com.genscript.gsscm.order.dto.SalesRankingDTO;
import com.genscript.gsscm.product.dto.ProductPriceDTO;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;
import com.genscript.gsscm.product.entity.Royalty;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.dto.VendorServiceDTO;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.entity.VendorServiceEntity;
import com.genscript.gsscm.serv.dto.RoyaltyServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceCategoryDTO;
import com.genscript.gsscm.serv.dto.ServiceDTO;
import com.genscript.gsscm.serv.dto.ServiceIntermediateDTO;
import com.genscript.gsscm.serv.dto.ServicePriceDTO;
import com.genscript.gsscm.serv.dto.ServiceRelationDTO;
import com.genscript.gsscm.serv.dto.ServiceReportSrchDTO;
import com.genscript.gsscm.serv.dto.ServiceRestrictShipDTO;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;
import com.genscript.gsscm.serv.entity.ServicePrice;
import com.genscript.gsscm.serv.entity.ServiceRestrictShip;
import com.genscript.gsscm.serv.entity.ServiceSubStepPrice;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.Action;

/*
 *2010-8-23 13:32:42
 *by mingrs
 */

@Results({
		@Result(name = Action.SUCCESS, location = "service/service/service_list.jsp"),
		@Result(name = "search", location = "service/service/service_search.jsp"),
		@Result(name = "servOfCategory", location = "service/service/category_serv_list.jsp"),
		@Result(name = "catServAddAct", location = "service/service/category_serv_add.jsp"),
		@Result(name = "seviceCreateForm", location = "service/service/pdtServ_create_form.jsp"),
		@Result(name = "showCrossCreateFormAct", location = "service/service/pdtServ_cross_info.jsp"),
		@Result(name = "peptide", location = "service/service/detail/peptide.jsp"),
		@Result(name = "gene", location = "service/service/detail/gene.jsp"),
		@Result(name = "customService", location = "service/service/detail/customService.jsp"),
		@Result(name = "stepItem", location = "service/service/detail/step_item.jsp"),
		@Result(name = "subStepItem", location = "service/service/detail/sub_step_item.jsp"),
		@Result(name = "newSubStepItem", location = "service/service/detail/new_sub_step.jsp"),
		@Result(name = "showEditSuppler", location = "service/service/pdtServ_supplier_edit.jsp"),
		@Result(name = "supplier", location = "service/service/pdtServ_supplier.jsp"),
		@Result(name = "showSupplierPikcerAct", location = "service/service/pdtServ_supplier_picker.jsp"),
		@Result(name = "showMiscAct", location = "service/service/pdtServ_misc.jsp"),
		@Result(name = "showServiceSales", location = "service/service/service_sales.jsp"),
		@Result(name = "searchStdPriceList", location = "service/service/service_crossCtgNo_list.jsp"),
		@Result(name = "oligo", location = "service/service/detail/oligo.jsp"),
		@Result(name = "excel", location = "service/service/service_excel_list.jsp") })
public class ServAction extends BaseAction<ServiceDTO> {

	/**
     *
     */
	private static final long serialVersionUID = 8845065864876376385L;
	@Autowired
	private ExceptionService exceptionUtil;

	@Autowired
	private PublicService publicService;

	@Autowired
	private ServService servService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CommonSearchService commonSearchService;

	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private VendorDao vendorDao;

	private Page<ServiceDTO> page;
	private Page<ServiceOfServcategoryBean> pageOfCategory;
	private Page<ServiceListBean> pageBean;

	private List<ServiceIntermediateDTO> breakDown;
	private List<ServiceSubSteps> subSteps;
	private List<ServiceClass> ServiceClassTypeList;

	/*
	 * sessionId
	 */
	private String sessionServiceId;
	/*
	 * del subStep
	 */
	private String delSubStep;

	/**
	 * 列表页面中的数据
	 */
	private ServiceDTO entity;
	private Integer id;
	private ServiceDTO serviceDTO;
	private ServiceRelationDTO crossDetail;
	private ServiceSubSteps subStep;
	private String supplierId; // product supplier edit or new
								// 如果supplierId为空为new 否edit
	private VendorServiceEntity vendorServiceEntity;
	private Integer defaultTab;// service save 后显示的页面；
	private RoyaltyServiceDTO royolty;// RoyaltyProduct(版权税)信息
	private ProductServiceSaleDTO serviceSaleDTO;// 显示一个Service的各类销售统计情况；

	private String serviceKey;
	private String delService;
	private String sessionCategoryId;
	private String standrdPricesDate;
	private String priceLimitDate;

	private List<SearchAttributeDTO> attrList;
	private List<CountryDTO> countryList;
	private List<DropDownListDTO> arrDropdownList;
	private List<PbDropdownListOptions> pbOptionItemList;
	private List<PbDropdownListOptions> sendOrderedOption;
	private List<DropDownDTO> dropDownDTOList;
	private List<PbDropdownListOptions> generateNoticeOption;
	private List<VendorServiceDTO> verdonServiceList;
	private List<PurchaseOrderDTO> purchaseOrderList;
	private List<VendorDTO> vendorDTOList;
	private List<Royalty> royaltyList;
	private List<SalesRankingDTO> salesRankingList;
	private List<com.genscript.gsscm.serv.entity.Service> stdPriceList;
	private List<DropDownDTO> dropDownDTO;
	private List<ServiceListBean> excelResult;

	private Date fromDate;// 开始时间
	private Date toDate;// 结束时间
	private String isFalse;// 是否是需要统计
	private String salesPeriod;//
	private Integer top;// 查询前几名sales
	private String lastDays;// 查询名次，时间段
	private Integer periodType;// 统计图片时间点
	private String salesPeriodBasedOn;// 统计图片数据类型

	private String catalogNo;
	private String vendorName;
	private String supplierIdStr;

	private String miscName;// getPdtRoyaltyList(name)参数

	private String relationId;// Cross-Sell,UP-Sell,Substitute,Promote Items
								// 修改或增加
	private String name;// search cross product
	private List<String> catalogNoList;// search cross product not catalog

	private Integer categoryId;
	private String catalogId;
	private String stepId;

	private String propName;
	private String srchOperator;
	private String propValue;

	private String type;

	private String selId;
	private String nextId;

	/*
	 * service approved;
	 */
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

	/*
	 * service price
	 */

	/*
	 * Supply Related Info
	 */
	private String leadTime;
	/*
	 * private String saftyStock; private String minOrderQty; private String
	 * unitCost;
	 */

	// 获取从非Server列表页面点过来的请求--Zhang Yong
	private String operation_method;

	public ServiceDTO getModel() {
		/**
		 * 向跳转页面绑定ServiceDTO类型的输出数据。
		 */
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		/**
		 * 对请求进行二次绑定，获取对应的catalogDTO数据;
		 */
		if (id != null) {
			entity = this.servService.getServDetail(id);
		} else {
			entity = new ServiceDTO();
		}
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		if (id == null) {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
			// *********** Add By Zhang Yong End *****************************//
			sessionServiceId = SessionUtil.generateTempId();
		} else {
			// *********** Add By Zhang Yong Start
			// *****************************//
			// 判断将要修改的单号是否正在被操作
			String editUrl = "serv/serv!input.action?id=" + id;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			// *********** Add By Zhang Yong End *****************************//
			sessionServiceId = id.toString();
		}
		if (defaultTab == null) {
			defaultTab = 0;
		}
		SessionUtil.deleteRow(SessionPdtServ.ServiceSubStep.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceDelSubStep.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceSupplier.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.DelServiceSupplier.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceRelation.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceApprovedName.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceApprovedNameReason.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceApprovedStatus.value(),
				sessionServiceId);
		SessionUtil.deleteRow(
				SessionPdtServ.ServiceApprovedStatusReason.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceSubPriceApproved.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServicePriceApproved.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.BreakdownList.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.DelBreakdownList.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceSubStepPricing.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServicePricing.value(),
				sessionServiceId);
		SessionUtil.deleteRow(SessionPdtServ.ServiceRestrictShipList.value(),
				sessionServiceId);
		SessionUtil.deleteRow(
				SessionPdtServ.DelServiceRestrictShipList.value(),
				sessionServiceId);
		List<SpecDropDownListName> specDropDownListNames = new ArrayList<SpecDropDownListName>();
		specDropDownListNames.add(SpecDropDownListName.SERVICE_CLASSIFICATION);
		specDropDownListNames.add(SpecDropDownListName.TAX_STATUS_COUNTRY);
		specDropDownListNames.add(SpecDropDownListName.TAX_STATUS_NATIONAL);
		specDropDownListNames.add(SpecDropDownListName.TAX_STATUS_STATE);
		this.dropDownDTOList = this.servService.getRelationItemByServiceId(id);
		this.arrDropdownList = this.publicService
				.getSpecDropDownList(specDropDownListNames);
		this.pbOptionItemList = this.publicService
				.getDropdownList(SpecDropDownListName.PACKAGE_TYPE.value());
		System.out.println(entity.getBtRatio()
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>.." + entity.getVtRatio());
		this.nameAppr = servService.checkPropertyApproved(
				entity.getServiceId(), RequestApproveType.name.name(),
				RequestApproveType.Service.name());
		this.statusAppr = servService.checkPropertyApproved(
				entity.getServiceId(), RequestApproveType.status.name(),
				RequestApproveType.Service.name());
		return "seviceCreateForm";
	}

	public String catServAddAct() throws Exception {
		Map<String, String> filterMap = new HashMap<String, String>();
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceListBean> pagerUtil = new PagerUtil<ServiceListBean>();
		pageBean = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageBean.getPageSize() == null
				|| pageBean.getPageSize().intValue() < 1) {
			pageBean.setPageSize(15);
		}
		if (propValue == null) {
			filterMap.put("EQS_catalogNo", "0");
		} else {
			if (propName.equals("checkType")) {
				propName = "type";
			}
			filterMap.put(srchOperator + "S_" + propName, propValue);
		}
		if (!pageBean.isOrderBySetted()) {
			pageBean.setOrderBy("modifyDate");
			pageBean.setOrder(Page.DESC);
		}
		pageBean = this.servService.searchServiceList(pageBean, filterMap);
		dropDownDTO = publicService
				.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageBean);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "catServAddAct";
	}

	/*
	 * 在session中保存category删除service的信息；
	 */
	@SuppressWarnings("unchecked")
	public String delServiceToCategory() {
		if (delService != null) {
			String[] delServ = delService.split(",");
			Object obj = SessionUtil.getRow(SessionPdtServ.DelService.value(),
					sessionCategoryId);
			List<Integer> delServList;
			if (obj != null) {
				delServList = (List<Integer>) obj;
			} else {
				delServList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delServ.length; i++) {
				if (SessionUtil.getOneRow(SessionPdtServ.Service.value(),
						sessionCategoryId, delServ[i]) == null) {
					delServList.add(Integer.valueOf(delServ[i]));
				} else {
					SessionUtil.updateOneRow(SessionPdtServ.Service.value(),
							sessionCategoryId, delServ[i], null);
				}
			}
			if (!delServList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(SessionPdtServ.DelService.value(),
							sessionCategoryId, delServList);
				} else {
					SessionUtil.updateRow(SessionPdtServ.DelService.value(),
							sessionCategoryId, delServList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	/*
	 * 在SESSION中保存category增加service的信息
	 */
	public boolean checkall(List<ServiceOfServcategoryBean> plist, Integer key) {
		boolean flag = false;
		if (plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				ServiceOfServcategoryBean pdd = plist.get(i);
				if (pdd.getServiceId().equals(key)) {
					return true;
				}
			}
		}
		return flag;
	}

	public String addServiceToCategory() {
		Map<String, Object> rt = new HashMap<String, Object>();
		List<ServiceOfServcategoryBean> listcategroty = this.servService
				.getSevicesOfServcategoryLisst(Integer
						.parseInt(sessionCategoryId));
		String serviceKeys[] = serviceKey.split(",");

		int size = 0;
		if (listcategroty != null) {
			size = listcategroty.size();
		}
		int lengths = 0;
		if (serviceKeys != null) {
			lengths = serviceKeys.length;
		}
		ServicePriceDTO servicePriceDTO = new ServicePriceDTO();
		ServiceOfServcategoryBean serviceOfServcategoryBean = new ServiceOfServcategoryBean();
		if (size > 0) {
			if (lengths == 1) {
				if (size == 1) {
					// 只有一条数据的时候
					serviceOfServcategoryBean = (ServiceOfServcategoryBean) listcategroty
							.get(0);
					if (serviceOfServcategoryBean.getServiceId().toString()
							.equals(serviceKeys[0].toString())) {
						rt.put("message", "error");
					} else {
						if (servService.getCountBycatalogIdandServiceId(
								catalogId, Integer.valueOf(serviceKeys[0])) == 0) {
							servicePriceDTO.setCatalogId(catalogId);
							servicePriceDTO.setCategoryId(Integer
									.valueOf(sessionCategoryId));
							servicePriceDTO.setServiceId(Integer
									.valueOf(serviceKeys[0]));

							SessionUtil.updateOneRow(
									SessionPdtServ.Service.value(),
									sessionCategoryId, serviceKeys[0],
									servicePriceDTO);
						}
						rt.put("message", "ok");
					}
				}
				if (size > 1) {
					for (int i = 0; i < size; i++) {
						serviceOfServcategoryBean = (ServiceOfServcategoryBean) listcategroty
								.get(i);
						if (serviceOfServcategoryBean.getServiceId().toString()
								.equals(serviceKeys[0].toString())) {
							rt.put("message", "error");// 说明这产品已经存在于已选择的数据库里面了

						} else {
							if (servService.getCountBycatalogIdandServiceId(
									catalogId, Integer.valueOf(serviceKeys[0])) == 0) {
								servicePriceDTO.setCatalogId(catalogId);
								servicePriceDTO.setCategoryId(Integer
										.valueOf(sessionCategoryId));
								servicePriceDTO.setServiceId(Integer
										.valueOf(serviceKeys[0]));

								SessionUtil.updateOneRow(
										SessionPdtServ.Service.value(),
										sessionCategoryId, serviceKeys[0],
										servicePriceDTO);
							}
							rt.put("message", "ok");
						}
					}
				}

			} else {
				for (int i = 0; i < lengths; i++) {
					if (!checkall(listcategroty,
							Integer.parseInt(serviceKeys[i]))) {
						if (servService.getCountBycatalogIdandServiceId(
								catalogId, Integer.valueOf(serviceKeys[i])) == 0) {
							servicePriceDTO.setCatalogId(catalogId);
							servicePriceDTO.setCategoryId(Integer
									.valueOf(sessionCategoryId));
							servicePriceDTO.setServiceId(Integer
									.valueOf(serviceKeys[i]));
							SessionUtil.updateOneRow(
									SessionPdtServ.Service.value(),
									sessionCategoryId, serviceKeys[i],
									servicePriceDTO);
						}

					}
				}
				rt.put("message", "ok");

			}
		} else {
			if (lengths == 1) {
				if (servService.getCountBycatalogIdandServiceId(catalogId,
						Integer.valueOf(serviceKeys[0])) == 0) {
					servicePriceDTO.setCatalogId(catalogId);
					servicePriceDTO.setCategoryId(Integer
							.valueOf(sessionCategoryId));
					servicePriceDTO.setServiceId(Integer
							.valueOf(serviceKeys[0]));
					SessionUtil.updateOneRow(SessionPdtServ.Service.value(),
							sessionCategoryId, serviceKeys[0], servicePriceDTO);
					rt.put("message", "ok");

				} else {
					rt.put("message", "error");
				}
			} else if (lengths > 1) {
				for (int i = 0; i < lengths; i++) {
					if (!checkall(listcategroty,
							Integer.parseInt(serviceKeys[i]))) {
						if (productService.getCountBycatalogIdandproductId(
								catalogId, Integer.valueOf(serviceKeys[i])) == 0) {
							servicePriceDTO.setCatalogId(catalogId);
							servicePriceDTO.setCategoryId(Integer
									.valueOf(sessionCategoryId));
							servicePriceDTO.setServiceId(Integer
									.valueOf(serviceKeys[i]));
							SessionUtil.updateOneRow(
									SessionPdtServ.Product.value(),
									sessionCategoryId, serviceKeys[i],
									servicePriceDTO);
						}
					}
				}
				rt.put("message", "ok");
			}
		}
		System.out.println(rt.get("message"));
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 查询页面上的基本数据, 进入查询页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		attrList = commonSearchService
				.getSearchAttributeList(SearchType.SERVICE);
		countryList = publicService.getCountryList();
		ServiceClassTypeList= publicService.getServiceTypeList();
		return "search";
	}

	public List<ServiceClass> getServiceClassTypeList() {
		return ServiceClassTypeList;
	}

	public void setServiceClassTypeList(List<ServiceClass> serviceClassTypeList) {
		ServiceClassTypeList = serviceClassTypeList;
	}

	/*
	 * Cross-Sell,UP-Sell,Substitute,Promote Items add
	 */
	public String showCrossCreateFormAct() {
		this.pbOptionItemList = this.publicService
				.getDropdownList("RELATION_ITEM_TYPE");
		if (relationId == null || relationId.equals("")
				|| "null".equals(relationId)) {
			crossDetail = new ServiceRelationDTO();
		} else {
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.ServiceRelation.value(), sessionServiceId,
					relationId.toString());
			if (obj == null) {
				crossDetail = this.servService.getServiceRelationDetail(Integer
						.valueOf(relationId));
			} else {
				crossDetail = (ServiceRelationDTO) obj;
			}
		}
		return "showCrossCreateFormAct";
	}

	/**
	 * 获得Product的列表. For(Product's add related selling item item catalog no tab.
	 */
	public String searchStdPriceList() {
		if ((catalogNo == null || catalogNo.equals(""))
				&& (name == null || name.equals(""))) {
			stdPriceList = new ArrayList<com.genscript.gsscm.serv.entity.Service>();
		} else {
			stdPriceList = this.servService.searchServiceBreakDownList(
					catalogNo, name, catalogNoList);
		}
		return "searchStdPriceList";
	}

	/*
	 * save session Cross-Sell,UP-Sell,Substitute,Promote Items
	 */
	public String saveSessionCrossAct() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String crossKey;
		if (crossDetail.getRelationId() == null) {
			crossKey = SessionUtil.generateTempId();
		} else {
			crossKey = crossDetail.getRelationId().toString();
		}
		if (crossDetail.getDisplayOnlyBo() == null) {
			crossDetail.setDisplayOnlyBo("N");
		}
		if (crossDetail.getMandatoryFlag() == null) {
			crossDetail.setMandatoryFlag("N");
		}
		SessionUtil.updateOneRow(SessionPdtServ.ServiceRelation.value(),
				sessionServiceId, crossKey, crossDetail);
		rt.put("message", SUCCESS);
		rt.put("id", crossKey);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * sub step item new or edit
	 */
	public String newSubStepItem() {
		if (stepId == null) {
			subStep = new ServiceSubSteps();
		} else {
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.ServiceSubStep.value(), sessionServiceId,
					stepId);
			if (obj != null) {
				subStep = (ServiceSubSteps) obj;
			} else {
				subStep = this.servService.getSubStepDetail(Integer
						.valueOf(stepId));
			}
		}
		return "newSubStepItem";
	}

	/*
	 * sub step seqNo edit
	 */
	public String saveSeqNo() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (selId != null && selId.length() > 4 && nextId != null
				&& nextId.length() > 4) {
			selId = selId.substring(4);
			nextId = nextId.substring(4);
			Object objSel = SessionUtil.getOneRow(
					SessionPdtServ.ServiceSubStep.value(),
					this.sessionServiceId, selId);
			Object objNext = SessionUtil.getOneRow(
					SessionPdtServ.ServiceSubStep.value(),
					this.sessionServiceId, nextId);
			if (objSel != null && objNext != null) {
				ServiceSubSteps subStepSel = (ServiceSubSteps) objSel;
				ServiceSubSteps subStepNext = (ServiceSubSteps) objNext;
				Integer id = subStepSel.getStepNo();
				subStepSel.setStepNo(subStepNext.getStepNo());
				subStepNext.setStepNo(id);
				SessionUtil.updateOneRow(SessionPdtServ.ServiceSubStep.value(),
						this.sessionServiceId, selId, subStepSel);
				SessionUtil.updateOneRow(SessionPdtServ.ServiceSubStep.value(),
						this.sessionServiceId, nextId, subStepNext);
			}
			rt.put("message", SUCCESS);

		} else {
			rt.put("message", "ERROR");
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * sub step item session del
	 */
	@SuppressWarnings("unchecked")
	public String delSubStepItem() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (delSubStep != null) {
			List<Integer> delList;
			String[] del = delSubStep.split(",");
			Object obj = SessionUtil.getRow(
					SessionPdtServ.ServiceDelSubStep.value(),
					this.sessionServiceId);
			if (obj != null) {
				delList = (List<Integer>) obj;
			} else {
				delList = new ArrayList<Integer>();
			}
			int a=0;
			int b=0;
			for (int i = 0; i < del.length; i++) {
				List<ServiceSubStepPrice> serviceSubStepPrice = this.servService
						.searchSubStepPrice(Integer.valueOf(del[i]));
				if (serviceSubStepPrice != null
						&& !serviceSubStepPrice.isEmpty()) {
						a++;
				} else {
					if (SessionUtil.getOneRow(
							SessionPdtServ.ServiceSubStep.value(),
							sessionServiceId, del[i]) == null) {
						delList.add(Integer.valueOf(del[i]));
					} else {
						Object subStepMap = SessionUtil.getOneRow(
								SessionPdtServ.ServiceSubStep.value(),
								sessionServiceId, del[i]);
						ServiceSubSteps subSteps = (ServiceSubSteps) subStepMap;
						if (subSteps.getStepId() == null) {
							SessionUtil.updateOneRow(
									SessionPdtServ.ServiceSubStep.value(),
									sessionServiceId, del[i], null);
						} else {
							delList.add(Integer.valueOf(del[i]));
						}

					}
					b++;
				}
			}
			if (!delList.isEmpty()) {
				
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.ServiceDelSubStep.value(),
							sessionServiceId, delList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.ServiceDelSubStep.value(),
							sessionServiceId, delList);
				}
			}
	    
			rt.put("message", SUCCESS);
		} else {
			rt.put("message", ERROR);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * sub step item session save
	 */
	public String saveSubStepItem() throws Exception {
		String subStepKey;
		Map<String, Object> rt = new HashMap<String, Object>();
		if (id != null) {
			subStep.setServiceId(id);
		}
		if (subStep.getStepId() == null) {
			subStepKey = SessionUtil.generateTempId();
			rt.put("saveSubStep", 1);
		} else {
			subStepKey = subStep.getStepId().toString();
			rt.put("saveSubStep", 0);
		}
		SessionUtil.updateOneRow(SessionPdtServ.ServiceSubStep.value(),
				sessionServiceId, subStepKey, subStep);
		// subStep=this.servService.saveSubStep(subStep);
		rt.put("message", SUCCESS);
		rt.put("id", subStepKey);
		rt.put("stepNo", subStep.getStepNo());
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * service details 页面； 根据TYPE返回不同的JSP页面；
	 */
	public String serviceDetails() {
		System.out.println(type + ">>>>>>>>>>>>>>>>>>>>>>..");
		if (type == null || "".equals(type)) {
			return "peptide";
		}
		this.type = type.toLowerCase();
		if (type.equals(ServiceDetailsType.PEPTIDE.value())) {
			return "peptide";
		} else if (type.equals(ServiceDetailsType.PROTEIN.value())) {
			breakDown = this.servService.getServiceIntermediateAllList(id);
			return "stepItem";
		} else if (ServiceDetailsType.PROTEINSTEP.value().equals(type)) {
			subSteps = this.servService.getServiceSubStepsList(id);
			if (subSteps != null && !subSteps.isEmpty()) {
				Map<String, ServiceSubSteps> serviceSubStepsMap = new HashMap<String, ServiceSubSteps>();
				for (ServiceSubSteps substep : subSteps) {
					ServiceSubSteps ss = this.dozer.map(substep,
							ServiceSubSteps.class);
					serviceSubStepsMap.put(ss.getStepId().toString(), ss);
				}
				SessionUtil.insertRow(SessionPdtServ.ServiceSubStep.value(),
						sessionServiceId, serviceSubStepsMap);
			}
			return "subStepItem";
		} else if (type.equals(ServiceDetailsType.BIOPROCESS.value())) {
			breakDown = this.servService.getServiceIntermediateAllList(id);
			return "stepItem";
		} else if (ServiceDetailsType.BIOPROCESSSTEP.value().equals(type)) {
			subSteps = this.servService.getServiceSubStepsList(id);
			if (subSteps != null && !subSteps.isEmpty()) {
				Map<String, ServiceSubSteps> serviceSubStepsMap = new HashMap<String, ServiceSubSteps>();
				for (ServiceSubSteps substep : subSteps) {
					ServiceSubSteps ss = this.dozer.map(substep,
							ServiceSubSteps.class);
					serviceSubStepsMap.put(ss.getStepId().toString(), ss);
				}
				SessionUtil.insertRow(SessionPdtServ.ServiceSubStep.value(),
						sessionServiceId, serviceSubStepsMap);
			}
			return "subStepItem";
		} else if (type.equals(ServiceDetailsType.PHARMACEUTICAL.value())) {
			breakDown = this.servService.getServiceIntermediateAllList(id);
			return "stepItem";
		} else if (ServiceDetailsType.PHARMACEUTICALSTEP.value().equals(type)) {
			subSteps = this.servService.getServiceSubStepsList(id);
			if (subSteps != null && !subSteps.isEmpty()) {
				Map<String, ServiceSubSteps> serviceSubStepsMap = new HashMap<String, ServiceSubSteps>();
				for (ServiceSubSteps substep : subSteps) {
					ServiceSubSteps ss = this.dozer.map(substep,
							ServiceSubSteps.class);
					serviceSubStepsMap.put(ss.getStepId().toString(), ss);
				}
				SessionUtil.insertRow(SessionPdtServ.ServiceSubStep.value(),
						sessionServiceId, serviceSubStepsMap);
			}
			return "subStepItem";
		} else if (type.equals(ServiceDetailsType.ANTIBODYDRUG.value())) {
			breakDown = this.servService.getServiceIntermediateAllList(id);
			return "stepItem";
		} else if (ServiceDetailsType.ANTIBODYDRUGSTEP.value().equals(type)) {
			subSteps = this.servService.getServiceSubStepsList(id);
			if (subSteps != null && !subSteps.isEmpty()) {
				Map<String, ServiceSubSteps> serviceSubStepsMap = new HashMap<String, ServiceSubSteps>();
				for (ServiceSubSteps substep : subSteps) {
					ServiceSubSteps ss = this.dozer.map(substep,
							ServiceSubSteps.class);
					serviceSubStepsMap.put(ss.getStepId().toString(), ss);
				}
				SessionUtil.insertRow(SessionPdtServ.ServiceSubStep.value(),
						sessionServiceId, serviceSubStepsMap);
			}
			return "subStepItem";
		} else if (type.equals(ServiceDetailsType.customServicesAnimalModel
				.value())
				|| type.equals(ServiceDetailsType.customServicesAntibody
						.value())
				|| type.equals(ServiceDetailsType.customServicesBiomarker
						.value())
				|| type.equals(ServiceDetailsType.biomarker.value())
				|| type.equals(ServiceDetailsType.customServicesGene.value())
				|| type.equals(ServiceDetailsType.customServicesPeptide.value())) {
			return "customService";
		} else if (type.equals(ServiceDetailsType.GENE.value())) {
			return "gene";
		} else if (type.equals(ServiceDetailsType.OLIGO.value())) {
			return "oligo";
		}
		return "peptide";
	}

	/*
	 * del session supplier
	 */
	@SuppressWarnings("unchecked")
	public String delSupplierSession() {
		if (supplierIdStr != null) {
			String[] delPdt = supplierIdStr.split(",");
			Object obj = SessionUtil
					.getRow(SessionPdtServ.DelServiceSupplier.value(),
							sessionServiceId);
			List<Integer> delList;
			if (obj != null) {
				delList = (List<Integer>) obj;
			} else {
				delList = new ArrayList<Integer>();
			}
			// List<Integer> delPdtList = new ArrayList<Integer>();
			for (int i = 0; i < delPdt.length; i++) {
				if (SessionUtil.getOneRow(
						SessionPdtServ.ServiceSupplier.value(),
						sessionServiceId, delPdt[i]) == null) {
					delList.add(Integer.valueOf(delPdt[i]));
				} else {
					Object supplierMap = SessionUtil.getOneRow(
							SessionPdtServ.ServiceSupplier.value(),
							sessionServiceId, delPdt[i]);
					VendorServiceEntity supplieries = (VendorServiceEntity) supplierMap;
					if (supplieries.getId() == null) {
						SessionUtil.updateOneRow(
								SessionPdtServ.ServiceSupplier.value(),
								sessionServiceId, delPdt[i], null);
					} else {
						delList.add(Integer.valueOf(delPdt[i]));
					}
				}
			}
			if (!delList.isEmpty()) {
				if (obj == null) {
					SessionUtil.insertRow(
							SessionPdtServ.DelServiceSupplier.value(),
							sessionServiceId, delList);
				} else {
					SessionUtil.updateRow(
							SessionPdtServ.DelServiceSupplier.value(),
							sessionServiceId, delList);
				}
			}
			Struts2Util.renderText(SUCCESS);
		} else {
			Struts2Util.renderText(ERROR);
		}
		return null;
	}

	/*
	 * session save supplier
	 */
	public String saveSupplierSession() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		String supplierKey;
		String isRemove = "flase";
		if (this.supplierId == null && vendorServiceEntity.getId() == null) {
			supplierKey = SessionUtil.generateTempId();
		} else {
			if (vendorServiceEntity.getId() != null) {
				supplierKey = vendorServiceEntity.getId().toString();
			} else {
				supplierKey = this.supplierId;
			}
			isRemove = "true";
		}
		SessionUtil.updateOneRow(SessionPdtServ.ServiceSupplier.value(),
				sessionServiceId, supplierKey, vendorServiceEntity);
		rt.put("message", SUCCESS);
		rt.put("id", supplierKey);
		rt.put("isRemove", isRemove);
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * service supplier picker
	 */
	public String showSupplierPikcerAct() {
		vendorDTOList = this.servService.getAllSuppliesList(vendorName);
		return "showSupplierPikcerAct";
	}

	/*
	 * service supplier edit or new;
	 */
	public String showEditSuppler() {
		if (supplierId != null) {
			Object obj = SessionUtil.getOneRow(
					SessionPdtServ.ServiceSupplier.value(), sessionServiceId,
					supplierId);
			if (obj != null) {
				vendorServiceEntity = (VendorServiceEntity) obj;
				Vendor vendorTemp = this.vendorDao.getById(vendorServiceEntity
						.getVendorNo());
				vendorName = vendorTemp.getVendorName();
			} else {
				vendorServiceEntity = this.servService
						.getVendorServiceDetail(Integer.valueOf(supplierId));
				Vendor vendorTemp = this.vendorDao.getById(vendorServiceEntity
						.getVendorNo());
				vendorName = vendorTemp.getVendorName();
			}
		}
		return "showEditSuppler";
	}

	/*
	 * service Supplier List
	 */
	public String serviceOfSupplierList() {
		verdonServiceList = this.servService.getSupplierList(catalogNo);
		// purchaseOrderList =
		// this.servService.get.getPdtPurchaseOrderList(catalogNo);
		return "supplier";
	}

	/*
	 * service misc
	 */
	public String showMiscAct() {
		if (catalogNo != null) {
			this.royolty = this.servService.getRoyaltyService(catalogNo);
		} else {
			this.royolty = new RoyaltyServiceDTO();
		}
		this.generateNoticeOption = this.publicService
				.getDropdownList("PROD_NT_GEN_TIME");
		this.sendOrderedOption = this.publicService
				.getDropdownList("PROD_NT_SEND_TYPE");
		return "showMiscAct";
	}

	/*
	 * product sales
	 */
	public String showServiceSales() {
		if (catalogNo != null && isFalse.equals("yes")) {
			if (salesPeriod != null) {
				if (salesPeriod.equals("lastWeek")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getPreviousMonday(),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(
							DateUtils.getPreviousSunday(),
							DateUtils.C_DATE_PATTON_DEFAULT);
				} else if (salesPeriod.equals("thisWeek")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getCurrentMonday(),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.getWeekEndDay(new Date());
				} else if (salesPeriod.equals("lastMonth")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getLastMonth(),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(
							DateUtils.getLastDayOfMonth(fromDate),
							DateUtils.C_DATE_PATTON_DEFAULT);
				} else if (salesPeriod.equals("thisMonth")) {
					fromDate = DateUtils.formatStr2Date(
							DateUtils.getFirstDayOfMonth(new Date()),
							DateUtils.C_DATE_PATTON_DEFAULT);
					toDate = DateUtils.formatStr2Date(
							DateUtils.getLastDayOfMonth(new Date()),
							DateUtils.C_DATE_PATTON_DEFAULT);
				} else if (salesPeriod.equals("lastQuarter")) {
					fromDate = DateUtils.getQuarterStartDay(DateUtils
							.defineMonthBefore2Date(new Date(), -3));
					toDate = DateUtils.getQuarterEndDay(DateUtils
							.defineMonthBefore2Date(new Date(), -3));
				} else if (salesPeriod.equals("thisQuarter")) {
					fromDate = DateUtils.getQuarterStartDay(new Date());
					toDate = DateUtils.getQuarterEndDay(new Date());
				} else if (salesPeriod.equals("last6Months")) {
					fromDate = DateUtils.getLast6MonthsStartDay(DateUtils
							.defineMonthBefore2Date(new Date(), -6));
					toDate = DateUtils.getLast6MonthsEndDay(DateUtils
							.defineMonthBefore2Date(new Date(), -6));
				} else if (salesPeriod.equals("lastYear")) {
					fromDate = DateUtils.getYearStartDay(DateUtils
							.defineMonthBefore2Date(new Date(), -12));
					toDate = DateUtils.getYearEndDay(DateUtils
							.defineMonthBefore2Date(new Date(), -12));
				} else if (salesPeriod.equals("thisYear")) {
					fromDate = DateUtils.getYearStartDay(new Date());
					toDate = DateUtils.getYearEndDay(new Date());
				}
			}
			toDate = DateUtils.defineDayBefore2Date(toDate, 1);
			try {
				serviceSaleDTO = this.servService.getServiceSale(catalogNo,
						fromDate, toDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				serviceSaleDTO.setPicName(this.searchAnalysis());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Struts2Util.renderJson(serviceSaleDTO);
			return null;
		}
		return "showServiceSales";
	}

	/*
	 * Sales Person(s) Selling this Product
	 */
	public String showSalesRanking() {
		if (top == null) {
			top = 5;
		}
		if (lastDays.equals("lastYear")) {
			fromDate = DateUtils.getYearStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -12));
			toDate = DateUtils.getYearEndDay(DateUtils.defineMonthBefore2Date(
					new Date(), -12));
		} else if (lastDays.equals("lastWeek")) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getPreviousMonday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(DateUtils.getPreviousSunday(),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if (lastDays.equals("lastMonth")) {
			fromDate = DateUtils.formatStr2Date(DateUtils.getLastMonth(),
					DateUtils.C_DATE_PATTON_DEFAULT);
			toDate = DateUtils.formatStr2Date(
					DateUtils.getLastDayOfMonth(fromDate),
					DateUtils.C_DATE_PATTON_DEFAULT);
		} else if (lastDays.equals("last3Months")) {
			fromDate = DateUtils.getQuarterStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -3));
			toDate = DateUtils.getQuarterEndDay(DateUtils
					.defineMonthBefore2Date(new Date(), -3));
		} else if (lastDays.equals("last6Months")) {
			fromDate = DateUtils.getLast6MonthsStartDay(DateUtils
					.defineMonthBefore2Date(new Date(), -6));
			toDate = DateUtils.getLast6MonthsEndDay(DateUtils
					.defineMonthBefore2Date(new Date(), -6));
		}
		this.salesRankingList = this.servService.getSalesRanking(catalogNo,
				top, fromDate, toDate);
		Struts2Util.renderJson(salesRankingList);
		return null;
	}

	/*
	 * product sales 图片显示;
	 */
	private String searchAnalysis() throws Exception {
		ServiceReportSrchDTO srchDTO = new ServiceReportSrchDTO();
		List<AnalysisReport> analysisReportList;
		srchDTO.setCatalogNO(catalogNo);
		srchDTO.setBeginDate(fromDate);
		srchDTO.setEndDate(toDate);
		srchDTO.setPeriod(periodType);
		System.out.println("srchDTO: " + srchDTO);
		analysisReportList = this.servService.getServSalesReport(srchDTO,
				salesPeriodBasedOn);
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for (int i = 0; i < analysisReportList.size(); i++) {
			if (analysisReportList.get(i).getVisit() == 0) {
				dataSet.addValue(null, "", String.valueOf(i + 1));
			} else {
				dataSet.addValue(analysisReportList.get(i).getVisit(), "",
						String.valueOf(i + 1));
			}
		}
		JFreeChart chart = null;

		if (periodType == 1) {
			chart = ChartFactory.createBarChart(null, "Day",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else if (periodType == 7) {
			chart = ChartFactory.createBarChart(null, "Week",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else if (periodType == 30) {
			chart = ChartFactory.createBarChart(null, "Month",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else if (periodType == 90) {
			chart = ChartFactory.createBarChart(null, "Quarter",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		} else {
			chart = ChartFactory.createBarChart(null, "Year",
					salesPeriodBasedOn, dataSet, PlotOrientation.VERTICAL,
					false, false, false);
		}
		// Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// chart.setAntiAlias(false);
		CategoryPlot plot = chart.getCategoryPlot();// 设置图的高级属性
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		// BarRenderer3D renderer = new BarRenderer3D();//3D属性修改
		// CategoryAxis domainAxis = plot.getDomainAxis();//对X轴做操作
		// ValueAxis rAxis = plot.getRangeAxis();//对Y轴做操作
		// domainAxis.setLabelFont(labelFont);
		// domainAxis.setTickLabelPaint(Color.GREEN);//X轴的标题文字颜色
		// domainAxis.setAxisLinePaint(Color.BLUE);//X轴横线颜色
		// rAxis.setLabelPaint(Color.GREEN);
		// rAxis.setTickMarkPaint(Color.BLUE);

		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlinesVisible(true);
		String picName = SessionUtil.generateTempId();
		FileOutputStream fos = null;

		try {
			String rootPath = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("\\");
			System.out.println("rootPath: " + rootPath);
			fos = new FileOutputStream(
			 // "/usr/local/jboss/server/default/deploy/ROOT.war"
					rootPath  + "/images/temp/" + picName + ".png");
			ChartUtilities.writeChartAsPNG(fos, chart, 730, 200, null);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return picName;
	}

	public String listExcel() {
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		this.excelResult = this.servService
				.searchAllServiceListOfFilter(filters);
		return "excel";
	}

	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceListBean> pagerUtil = new PagerUtil<ServiceListBean>();
		pageBean = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageBean.getPageSize() == null
				|| pageBean.getPageSize().intValue() < 1) {
			pageBean.setPageSize(20);
		}
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (!pageBean.isOrderBySetted()) {
			pageBean.setOrderBy("modifyDate");
			pageBean.setOrder(Page.DESC);
		}
		pageBean = this.servService.searchServiceList(pageBean, filters);
		this.statusOfapproved = this.productService
				.getApprovedRequestListByTableTypeStatus(RequestApproveType.Service
						.name());
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageBean);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return SUCCESS;
	}

	public String serviceOfServCategoryList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceOfServcategoryBean> pagerUtil = new PagerUtil<ServiceOfServcategoryBean>();
		pageOfCategory = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (pageOfCategory.getPageSize() == null
				|| pageOfCategory.getPageSize().intValue() < 1) {
			pageOfCategory.setPageSize(15);
		}
		Map<String, String> filterMap = new HashMap<String, String>();
		String categoryId = ServletActionContext.getRequest().getParameter(
				"categoryId");
		String catalogNo = ServletActionContext.getRequest().getParameter(
				"catalogNo");
		String name = ServletActionContext.getRequest().getParameter("name");
		if (categoryId == null || "".equals(categoryId)) {
			categoryId = "0";
		}
		if (catalogNo != null && !"".equals(catalogNo)) {
			filterMap.put("EQS_catalogNo", catalogNo);
		}
		if (name != null && !"".equals(name)) {
			filterMap.put("EQS_itemName", name);
		}
		filterMap.put("EQI_categoryId", categoryId);
		pageOfCategory.setOrderBy("modifyDate,serviceId");
		pageOfCategory.setOrder(Page.DESC + "," + Page.DESC);
		pageOfCategory = this.servService.getServOfServCategoryList(
				pageOfCategory, filterMap);
		/*
		 * 将session中对应删除信息，在LIST时去除。
		 */
		Object obj = SessionUtil.getRow(SessionPdtServ.DelProduct.value(),
				sessionCategoryId);
		if (obj != null) {
			List<Integer> delList = (List<Integer>) obj;
			for (int j = 0; j < delList.size(); j++) {
				for (int i = 0; i < pageOfCategory.getResult().size(); i++) {
					if (pageOfCategory.getResult().get(i).getServiceId()
							.equals(delList.get(j))) {
						pageOfCategory.getResult().remove(i);
					}
				}
			}
		}
		/*
		 * 
		 * 将session中对应save信息，在LIST中增加，只增加在第一页。其他页面没有。
		 */

		obj = SessionUtil.getRow(SessionPdtServ.Service.value(),
				sessionCategoryId);
		if (obj != null) {
			if (pageOfCategory.getPageNo() == 1) {
				Map<String, ServicePriceDTO> servicePriceDTOMap = (Map<String, ServicePriceDTO>) obj;
				for (Map.Entry<String, ServicePriceDTO> entry : servicePriceDTOMap
						.entrySet()) {
					ServicePriceDTO serviceCategory = servicePriceDTOMap
							.get(entry.getKey());
					if (serviceCategory != null) {
						ServiceDTO services = this.servService
								.getServDetail(serviceCategory.getServiceId());
						ServiceOfServcategoryBean bean = this.dozer.map(
								services, ServiceOfServcategoryBean.class);
						List<DropDownDTO> dropDownDTO = publicService
								.getSpecDropDownList(SpecDropDownListName.SERVICE_CLASSIFICATION);
						for (DropDownDTO dropDown : dropDownDTO) {
							if (dropDown.getId().equals(
									services.getServiceClsId().toString())) {
								bean.setType(dropDown.getName());
							}
						}
						pageOfCategory.getResult().add(bean);
					}
				}
			}
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(pageOfCategory);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "servOfCategory";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String save() throws Exception {
		System.out.println("sessionServiceId=====" + sessionServiceId);
		if (sessionServiceId != null) {
			String[] str = sessionServiceId.split(",");
			if (str.length > 0) {
				sessionServiceId = str[0];
			}
			System.out
					.println("sessionServiceId===================================="
							+ sessionServiceId);
		}
		Integer loginUserId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessionServiceId != null && !("").equals(sessionServiceId)) {
			String editUrl = "serv/serv!input.action?id=" + sessionServiceId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message",
						"Save serverice fail,the serverice is editing by "
								+ operation_method);
				rt.put("id", sessionServiceId);
				Struts2Util.renderJson(rt);
				// 清除Session
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		try {
			if (serviceDTO.getInvoiceable() == null) {
				serviceDTO.setInvoiceable("Y");
			}
			if (serviceDTO.getTaxable() == null) {
				serviceDTO.setTaxable("Y");
			}
			if (serviceDTO.getDiscountable() == null) {
				serviceDTO.setDiscountable("Y");
			}
			if (serviceDTO.getQuotable() == null) {
				serviceDTO.setQuotable("Y");
			}
			if (serviceDTO.getLotControlFlag() == null) {
				serviceDTO.setLotControlFlag("Y");
			}
			if (serviceDTO.getSellable() == null) {
				serviceDTO.setSellable("Y");
			}
			if (serviceDTO.getShippable() == null) {
				serviceDTO.setShippable("Y");
			}
			if (serviceDTO.getPurchasable() == null) {
				serviceDTO.setPurchasable("Y");
			}
			if (serviceDTO.getReturnable() == null) {
				serviceDTO.setReturnable("Y");
			}
			if (serviceDTO.getGiftFlag() == null) {
				serviceDTO.setGiftFlag("N");
			}
			if (serviceDTO.getStockable() == null) {
				serviceDTO.setStockable("Y");
			}
			if (serviceDTO.getSerialControlFlag() == null) {
				serviceDTO.setSerialControlFlag("Y");
			}
			if (serviceDTO.getCompositeFlag() == null) {
				serviceDTO.setCompositeFlag("Y");
			}
			// -------------add by zhougang 2011 - 18 判断 接受这些值时候 是否为空。
			// System.out.println(serviceDTO.getGrossProfitCmsn()+"<<<<<<<<<<<<");
			if (serviceDTO.getGrossProfitCmsn() == null
					|| serviceDTO.getGrossProfitCmsn().equals("")) {
				serviceDTO.setGrossProfitCmsn(BigDecimal.ZERO);
			}
			if (serviceDTO.getSellingPriceCmsn() == null
					|| serviceDTO.getSellingPriceCmsn().equals("")) {
				serviceDTO.setSellingPriceCmsn(BigDecimal.ZERO);
			}
			if (serviceDTO.getUnitCost() == null
					|| serviceDTO.getUnitCost().equals("")) {
				serviceDTO.setUnitCost(BigDecimal.ZERO);
			}
			// System.out.println(serviceDTO.getBtRatio()
			// +">>>>>>>>>>>"+serviceDTO.getVtRatio());
			// ---------------end-------------------------

			Object obj = SessionUtil.getRow(
					SessionPdtServ.ServiceSubStep.value(), sessionServiceId);
			System.out.println(obj);
			if (obj != null) {
				Map<String, ServiceSubSteps> serviceSubStepsMap = (Map<String, ServiceSubSteps>) obj;
				List<ServiceSubSteps> serviceSubStepsList = new ArrayList<ServiceSubSteps>();
				for (Map.Entry<String, ServiceSubSteps> entry : serviceSubStepsMap
						.entrySet()) {
					ServiceSubSteps serviceSubSteps = serviceSubStepsMap
							.get(entry.getKey());
					if (serviceSubSteps != null) {
						serviceSubStepsList.add(serviceSubSteps);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ServiceSubStep.value(),
						sessionServiceId);
				if (!serviceSubStepsList.isEmpty()) {
					serviceDTO.setSubStepList(serviceSubStepsList);
				}
			}

			obj = SessionUtil.getRow(SessionPdtServ.ServiceDelSubStep.value(),
					sessionServiceId);
			if (obj != null) {
				List<Integer> delPdtList = (List<Integer>) obj;
				if (!delPdtList.isEmpty()) {
					serviceDTO.setDelSubStep(delPdtList);
				}
				SessionUtil.deleteRow(SessionPdtServ.ServiceDelSubStep.value(),
						sessionServiceId);
			}

			Map<String, ServiceRestrictShipDTO> map = (Map<String, ServiceRestrictShipDTO>) SessionUtil
					.getRow(SessionPdtServ.ServiceRestrictShipList.value(),
							sessionServiceId);

			List<ServiceRestrictShip> restrictShipList = new ArrayList<ServiceRestrictShip>();
			if (map != null) {
				// System.out.println("Map Size:"+map.size());
				for (int i = 0; i < map.keySet().size(); i++) {
					restrictShipList.add(dozer.map(
							map.get((String) map.keySet().toArray()[i]),
							ServiceRestrictShip.class));
				}
			}
			// System.out.println("RestrictShip Size"+restrictShipList.size());
			if (restrictShipList.size() > 0)
				serviceDTO.setRestrictShipList(restrictShipList);
			if (SessionUtil.getRow(
					SessionPdtServ.DelServiceRestrictShipList.value(),
					String.valueOf(serviceDTO.getServiceId())) != null) {
				serviceDTO.setDelRestrictShipIdList((List<Integer>) SessionUtil
						.getRow(SessionPdtServ.DelServiceRestrictShipList
								.value(), sessionServiceId));
			}

			obj = SessionUtil.getRow(SessionPdtServ.ServiceSupplier.value(),
					sessionServiceId);
			if (obj != null) {
				Map<String, VendorServiceEntity> vendorServiceMap = (Map<String, VendorServiceEntity>) obj;
				List<VendorServiceDTO> vendorServiceList = new ArrayList<VendorServiceDTO>();
				for (Map.Entry<String, VendorServiceEntity> entry : vendorServiceMap
						.entrySet()) {
					VendorServiceEntity vendorService = vendorServiceMap
							.get(entry.getKey());
					if (vendorService != null) {
						vendorServiceList.add(dozer.map(vendorService,
								VendorServiceDTO.class));
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ServiceSupplier.value(),
						sessionServiceId);
				if (!vendorServiceList.isEmpty()) {
					serviceDTO.setVendorServiceList(vendorServiceList);
				}
			}

			obj = SessionUtil.getRow(SessionPdtServ.DelServiceSupplier.value(),
					sessionServiceId);
			if (obj != null) {
				List<Integer> delList = (List<Integer>) obj;
				if (!delList.isEmpty()) {
					serviceDTO.setDelVendorServiceIdList(delList);
				}
				SessionUtil.deleteRow(
						SessionPdtServ.DelServiceSupplier.value(),
						sessionServiceId);
			}
			String imdStr = Struts2Util.getParameter("imdStr");
			List<ServiceIntermediateDTO> intermediateList = generateList(imdStr);
			if (intermediateList != null) {
				serviceDTO.setIntmdList(intermediateList);
			}
			obj = SessionUtil.getRow(SessionPdtServ.DelBreakdownList.value(),
					sessionServiceId);

			if (obj != null) {
				if (obj != null && ((List<Integer>) obj).size() > 0)
					serviceDTO.setDelIntmdIdList((List<Integer>) obj);
			}

			obj = SessionUtil.getRow(SessionPdtServ.ServiceRelation.value(),
					sessionServiceId);
			if (obj != null) {
				Map<String, ServiceRelationDTO> serviceRelationMap = (Map<String, ServiceRelationDTO>) obj;
				List<ServiceRelationDTO> serviceRelationList = new ArrayList<ServiceRelationDTO>();
				for (Map.Entry<String, ServiceRelationDTO> entry : serviceRelationMap
						.entrySet()) {
					ServiceRelationDTO roductRelation = serviceRelationMap
							.get(entry.getKey());
					if (roductRelation != null) {
						serviceRelationList.add(roductRelation);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ServiceRelation.value(),
						sessionServiceId);
				if (!serviceRelationList.isEmpty()) {
					serviceDTO.setServRelationList(serviceRelationList);
				}
			}

			// 获取product approved;
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedName.value(),
					sessionServiceId);
			if (obj != null) {
				serviceDTO.setNameApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceApprovedName.value(),
						sessionServiceId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedNameReason.value(),
					sessionServiceId);
			if (obj != null) {
				serviceDTO.setNameReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceApprovedNameReason.value(),
						sessionServiceId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedStatus.value(),
					sessionServiceId);
			if (obj != null) {
				serviceDTO.setStatusApprove(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceApprovedStatus.value(),
						sessionServiceId);
			}
			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedStatusReason.value(),
					sessionServiceId);
			if (obj != null) {
				serviceDTO.setStatusReason(obj.toString());
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceApprovedStatusReason.value(),
						sessionServiceId);
			}

			/*
			 * servicePrice serviceSubStepPrice;
			 */
			obj = SessionUtil.getRow(SessionPdtServ.ServicePricing.value(),
					sessionServiceId);
			if (obj != null) {
				Map<String, ServicePrice> servicePriceMap = (Map<String, ServicePrice>) obj;
				List<ServicePrice> servicePriceList = new ArrayList<ServicePrice>();
				for (Map.Entry<String, ServicePrice> entry : servicePriceMap
						.entrySet()) {
					ServicePrice servicePrice = servicePriceMap.get(entry
							.getKey());
					if (servicePrice != null) {
						servicePriceList.add(servicePrice);
					}
				}
				SessionUtil.deleteRow(SessionPdtServ.ServicePricing.value(),
						sessionServiceId);
				if (!servicePriceList.isEmpty()) {
					serviceDTO.setServicePrice(servicePriceList);
				}
			}

			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceSubStepPricing.value(),
					sessionServiceId);
			if (obj != null) {
				Map<String, ServiceSubStepPrice> serviceSubStepPriceMap = (Map<String, ServiceSubStepPrice>) obj;
				List<ServiceSubStepPrice> serviceSubStepPriceList = new ArrayList<ServiceSubStepPrice>();
				for (Map.Entry<String, ServiceSubStepPrice> entry : serviceSubStepPriceMap
						.entrySet()) {
					ServiceSubStepPrice serviceSubStepPrice = serviceSubStepPriceMap
							.get(entry.getKey());
					if (serviceSubStepPrice != null) {
						serviceSubStepPriceList.add(serviceSubStepPrice);
					}
				}
				SessionUtil.deleteRow(
						SessionPdtServ.ServiceSubStepPricing.value(),
						sessionServiceId);
				if (!serviceSubStepPriceList.isEmpty()) {
					serviceDTO.setSubStepPrice(serviceSubStepPriceList);
				}
			}

			obj = SessionUtil.getRow(
					SessionPdtServ.ServicePriceApproved.value(),
					sessionServiceId);
			if (obj != null) {
				Map<String, ServicePriceDTO> servicePriceMap = (Map<String, ServicePriceDTO>) obj;
				List<ServicePriceDTO> servicePriceList = new ArrayList<ServicePriceDTO>();
				for (Map.Entry<String, ServicePriceDTO> entry : servicePriceMap
						.entrySet()) {
					ServicePriceDTO servicePrice = servicePriceMap.get(entry
							.getKey());
					if (servicePrice != null) {
						servicePriceList.add(servicePrice);
					}
				}
				SessionUtil.deleteRow(
						SessionPdtServ.ServicePriceApproved.value(),
						sessionServiceId);
				if (!servicePriceList.isEmpty()) {
					serviceDTO.setServicePriceApproveList(servicePriceList);
				}
			}

			obj = SessionUtil.getRow(
					SessionPdtServ.ServiceSubPriceApproved.value(),
					sessionServiceId);
			if (obj != null) {
				Map<String, ServicePriceDTO> servicePriceMap = (Map<String, ServicePriceDTO>) obj;
				List<ServicePriceDTO> servicePriceList = new ArrayList<ServicePriceDTO>();
				for (Map.Entry<String, ServicePriceDTO> entry : servicePriceMap
						.entrySet()) {
					ServicePriceDTO servicePrice = servicePriceMap.get(entry
							.getKey());
					if (servicePrice != null) {
						servicePriceList.add(servicePrice);
					}
				}
				SessionUtil.deleteRow(
						SessionPdtServ.ServicePriceApproved.value(),
						sessionServiceId);
				if (!servicePriceList.isEmpty()) {
					serviceDTO.setServiceSubPriceApproveList(servicePriceList);
					System.out
							.println("servicesubprice="
									+ serviceDTO
											.getServiceSubPriceApproveList()
											.size());
				}
			}

			String ruleId = ServletActionContext.getRequest().getParameter(
					"catalogNoRuleId");
			com.genscript.gsscm.serv.entity.Service serv = this.servService
					.saveService(serviceDTO, loginUserId, ruleId);
			SessionUtil.deleteRow(
					SessionPdtServ.ServiceRestrictShipList.value(),
					sessionServiceId);
			SessionUtil.deleteRow(SessionPdtServ.BreakdownList.value(),
					sessionServiceId);
			SessionUtil.deleteRow(SessionPdtServ.DelBreakdownList.value(),
					sessionServiceId);
			SessionUtil.deleteRow(
					SessionPdtServ.DelServiceRestrictShipList.value(),
					sessionServiceId);
			rt.put("message", "The catalog #" + serv.getName()
					+ " is saved successfully!");
			rt.put("id", serv.getServiceId());

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

	public String delServiceListApproved() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (this.approvedStatusList == null
				|| "".equals(this.approvedStatusList)) {
			rt.put("message", "You have not chosen any valid data!!!");
		} else {
			String[] statustList = this.approvedStatusList.split(",");
			Integer userId = SessionUtil.getUserId();
			this.servService.delServiceListApproved(statustList, userId,
					approved, approvedReason);
			rt.put("message", SUCCESS);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/*
	 * service Approved session save
	 * 
	 * @param type , name ,reason
	 */
	public String serviceApprovedSaveSession() {
		Map<String, Object> rt = new HashMap<String, Object>();
		if (SessionPdtServ.ServiceApprovedName.value().equals(approvedType)) {
			System.out
					.println("SessionPdtServ.ServiceApprovedName.value()========"
							+ SessionPdtServ.ServiceApprovedName.value());
			System.out.println("sessionServiceId========" + sessionServiceId);
			System.out.println("approved========" + approved);
			if (SessionUtil.getRow(SessionPdtServ.ServiceApprovedName.value(),
					sessionServiceId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceApprovedName.value(),
						sessionServiceId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceApprovedName.value(),
						sessionServiceId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedNameReason.value(),
					sessionServiceId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceApprovedNameReason.value(),
						sessionServiceId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceApprovedNameReason.value(),
						sessionServiceId, approvedReason);
			}

		} else if (SessionPdtServ.ServiceApprovedStatus.value().equals(
				approvedType)) {
			System.out
					.println("SessionPdtServ.ServiceApprovedStatus.value()========"
							+ SessionPdtServ.ServiceApprovedStatus.value());
			System.out.println("sessionServiceId========" + sessionServiceId);
			System.out.println("approved========" + approved);
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedStatus.value(),
					sessionServiceId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceApprovedStatus.value(),
						sessionServiceId, approved);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceApprovedStatus.value(),
						sessionServiceId, approved);
			}
			if (SessionUtil.getRow(
					SessionPdtServ.ServiceApprovedStatusReason.value(),
					sessionServiceId) == null) {
				SessionUtil.insertRow(
						SessionPdtServ.ServiceApprovedStatusReason.value(),
						sessionServiceId, approvedReason);
			} else {
				SessionUtil.updateRow(
						SessionPdtServ.ServiceApprovedStatusReason.value(),
						sessionServiceId, approvedReason);
			}
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * Function: Transfer the String to ServiceIntermediateDTO List
	 * 
	 * @param catStr
	 *            //ServiceIntermediateDTO object String
	 * @return ServiceIntermediateDTO List
	 */
	private List<ServiceIntermediateDTO> generateList(String catStr) {
		if (StringUtils.isBlank(catStr) && StringUtils.isEmpty(catStr))
			return null;
		List<ServiceIntermediateDTO> list = new ArrayList<ServiceIntermediateDTO>();
		String[] catStrArr = catStr.split("<=>");
		for (int i = 0; i < catStrArr.length; i++) {
			ServiceIntermediateDTO temp = new ServiceIntermediateDTO();
			String[] tempArr = catStrArr[i].split("<->");
			if (StringUtils.isNumeric(tempArr[0])) {
				temp.setListSeq(Integer.parseInt(tempArr[0]));
			}
			if (StringUtils.isNumeric(tempArr[1])) {
				temp.setId(Integer.parseInt(tempArr[1]));
			}
			if (StringUtils.isNotBlank(tempArr[2])
					&& StringUtils.isNotEmpty(tempArr[2])) {
				temp.setIntmdCatalogNo(tempArr[2]);
			}
			if (StringUtils.isNotBlank(tempArr[3])
					&& StringUtils.isNotEmpty(tempArr[3])) {
				temp.setItem(tempArr[3]);
			}
			if (StringUtils.isNotBlank(tempArr[4])
					&& StringUtils.isNotEmpty(tempArr[4])
					&& StringUtils.isNumeric(tempArr[4])) {
				temp.setLeadTime(Integer.parseInt(tempArr[4]));
			}
			if (StringUtils.isNotBlank(tempArr[5])
					&& StringUtils.isNotEmpty(tempArr[5])) {
				temp.setSymbol(tempArr[5]);
			}
			if (StringUtils.isNotBlank(tempArr[6])
					&& StringUtils.isNotEmpty(tempArr[6])) {
				temp.setIntmdKeyword(tempArr[6]);
			}
			if (StringUtils.isNotBlank(tempArr[7])
					&& StringUtils.isNotEmpty(tempArr[7])
					&& StringUtils.isNumeric(tempArr[7])) {
				temp.setQuantity(Integer.parseInt(tempArr[7]));
			}
			if (StringUtils.isNotBlank(tempArr[8])
					&& StringUtils.isNotEmpty(tempArr[8])) {
				System.out.println("=======" + tempArr[8] + "======");
				temp.setRequiredFlag(tempArr[8].trim());
			}

			if (id != null) {
				temp.setServiceId(id);
			}
			System.out.println("ServiceIntermediateDTO:" + temp);
			list.add(temp);
		}
		return list;
	}

	// -- 页面属性访问函数 --//

	/**
	 * list页面显示Catalog分页列表.
	 */
	public Page<ServiceDTO> getPage() {
		return page;
	}

	public Page<ServiceListBean> getPageBean() {
		return pageBean;
	}

	public Page<ServiceOfServcategoryBean> getPageOfCategory() {
		return pageOfCategory;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public String getSrchOperator() {
		return srchOperator;
	}

	public void setSrchOperator(String srchOperator) {
		this.srchOperator = srchOperator;
	}

	public List<SearchAttributeDTO> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<SearchAttributeDTO> attrList) {
		this.attrList = attrList;
	}

	public List<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDTO> countryList) {
		this.countryList = countryList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceDTO getServiceDTO() {
		return serviceDTO;
	}

	public void setServiceDTO(ServiceDTO serviceDTO) {
		this.serviceDTO = serviceDTO;
	}

	public List<DropDownListDTO> getArrDropdownList() {
		return arrDropdownList;
	}

	public void setArrDropdownList(List<DropDownListDTO> arrDropdownList) {
		this.arrDropdownList = arrDropdownList;
	}

	public List<PbDropdownListOptions> getPbOptionItemList() {
		return pbOptionItemList;
	}

	public void setPbOptionItemList(List<PbDropdownListOptions> pbOptionItemList) {
		this.pbOptionItemList = pbOptionItemList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ServiceSubSteps getSubStep() {
		return subStep;
	}

	public void setSubStep(ServiceSubSteps subStep) {
		this.subStep = subStep;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getSessionServiceId() {
		return sessionServiceId;
	}

	public void setSessionServiceId(String sessionServiceId) {
		this.sessionServiceId = sessionServiceId;
	}

	public String getDelSubStep() {
		return delSubStep;
	}

	public void setDelSubStep(String delSubStep) {
		this.delSubStep = delSubStep;
	}

	public String getSelId() {
		return selId;
	}

	public void setSelId(String selId) {
		this.selId = selId;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public VendorServiceEntity getVendorServiceEntity() {
		return vendorServiceEntity;
	}

	public void setVendorServiceEntity(VendorServiceEntity vendorServiceEntity) {
		this.vendorServiceEntity = vendorServiceEntity;
	}

	public List<VendorServiceDTO> getVerdonServiceList() {
		return verdonServiceList;
	}

	public void setVerdonServiceList(List<VendorServiceDTO> verdonServiceList) {
		this.verdonServiceList = verdonServiceList;
	}

	public List<PurchaseOrderDTO> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrderDTO> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public List<VendorDTO> getVendorDTOList() {
		return vendorDTOList;
	}

	public void setVendorDTOList(List<VendorDTO> vendorDTOList) {
		this.vendorDTOList = vendorDTOList;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getSupplierIdStr() {
		return supplierIdStr;
	}

	public void setSupplierIdStr(String supplierIdStr) {
		this.supplierIdStr = supplierIdStr;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public Integer getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(Integer defaultTab) {
		this.defaultTab = defaultTab;
	}

	public RoyaltyServiceDTO getRoyolty() {
		return royolty;
	}

	public void setRoyolty(RoyaltyServiceDTO royolty) {
		this.royolty = royolty;
	}

	public List<PbDropdownListOptions> getSendOrderedOption() {
		return sendOrderedOption;
	}

	public void setSendOrderedOption(
			List<PbDropdownListOptions> sendOrderedOption) {
		this.sendOrderedOption = sendOrderedOption;
	}

	public List<PbDropdownListOptions> getGenerateNoticeOption() {
		return generateNoticeOption;
	}

	public void setGenerateNoticeOption(
			List<PbDropdownListOptions> generateNoticeOption) {
		this.generateNoticeOption = generateNoticeOption;
	}

	public List<Royalty> getRoyaltyList() {
		return royaltyList;
	}

	public void setRoyaltyList(List<Royalty> royaltyList) {
		this.royaltyList = royaltyList;
	}

	public String getMiscName() {
		return miscName;
	}

	public void setMiscName(String miscName) {
		this.miscName = miscName;
	}

	public ProductServiceSaleDTO getServiceSaleDTO() {
		return serviceSaleDTO;
	}

	public void setServiceSaleDTO(ProductServiceSaleDTO serviceSaleDTO) {
		this.serviceSaleDTO = serviceSaleDTO;
	}

	public List<SalesRankingDTO> getSalesRankingList() {
		return salesRankingList;
	}

	public void setSalesRankingList(List<SalesRankingDTO> salesRankingList) {
		this.salesRankingList = salesRankingList;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getIsFalse() {
		return isFalse;
	}

	public void setIsFalse(String isFalse) {
		this.isFalse = isFalse;
	}

	public String getSalesPeriod() {
		return salesPeriod;
	}

	public void setSalesPeriod(String salesPeriod) {
		this.salesPeriod = salesPeriod;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getLastDays() {
		return lastDays;
	}

	public void setLastDays(String lastDays) {
		this.lastDays = lastDays;
	}

	public Integer getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}

	public String getSalesPeriodBasedOn() {
		return salesPeriodBasedOn;
	}

	public void setSalesPeriodBasedOn(String salesPeriodBasedOn) {
		this.salesPeriodBasedOn = salesPeriodBasedOn;
	}

	public ServiceRelationDTO getCrossDetail() {
		return crossDetail;
	}

	public void setCrossDetail(ServiceRelationDTO crossDetail) {
		this.crossDetail = crossDetail;
	}

	public List<com.genscript.gsscm.serv.entity.Service> getStdPriceList() {
		return stdPriceList;
	}

	public void setStdPriceList(
			List<com.genscript.gsscm.serv.entity.Service> stdPriceList) {
		this.stdPriceList = stdPriceList;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCatalogNoList() {
		return catalogNoList;
	}

	public void setCatalogNoList(List<String> catalogNoList) {
		this.catalogNoList = catalogNoList;
	}

	public List<DropDownDTO> getDropDownDTOList() {
		return dropDownDTOList;
	}

	public void setDropDownDTOList(List<DropDownDTO> dropDownDTOList) {
		this.dropDownDTOList = dropDownDTOList;
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

	public String getApprovedStatusList() {
		return approvedStatusList;
	}

	public void setApprovedStatusList(String approvedStatusList) {
		this.approvedStatusList = approvedStatusList;
	}

	public String getSessionCategoryId() {
		return sessionCategoryId;
	}

	public void setSessionCategoryId(String sessionCategoryId) {
		this.sessionCategoryId = sessionCategoryId;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public String getDelService() {
		return delService;
	}

	public void setDelService(String delService) {
		this.delService = delService;
	}

	public String getStandrdPricesDate() {
		return standrdPricesDate;
	}

	public void setStandrdPricesDate(String standrdPricesDate) {
		this.standrdPricesDate = standrdPricesDate;
	}

	public String getPriceLimitDate() {
		return priceLimitDate;
	}

	public void setPriceLimitDate(String priceLimitDate) {
		this.priceLimitDate = priceLimitDate;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
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

	public List<ServiceListBean> getExcelResult() {
		return excelResult;
	}

	public void setExcelResult(List<ServiceListBean> excelResult) {
		this.excelResult = excelResult;
	}

}
