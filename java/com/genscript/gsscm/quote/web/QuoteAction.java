package com.genscript.gsscm.quote.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.inventory.service.InventoryService;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.quote.dto.QuotationPrintDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuotePaymentPlan;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "quote_edit", location = "quote/quote_edit.jsp"),
		@Result(name = "quote_information", location = "quote/quote_information.jsp"),
		@Result(name = "quote_status_history", location = "quote/quote_status_history.jsp"),
		@Result(name = "quotationPrint", location = "quote/quotationPrint.jsp"),
		@Result(name = "quote_confirm_status", location = "quote/quote_confirm_status.jsp") })
public class QuoteAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3844237117835940883L;
	private Integer quoteNo;
	private String itemId;
	private Integer[] quoteNos;
	private String sessQuoteNo;
	private QuoteMainDTO quote;
	// 新建时候初始化数据
	private String quoteCurrency;
	private String techSupportUser;
	private Integer techSupport;
	private Integer projectSupport;
	private String projectSupportUser;
	private Integer paymentTerm;
	private int purchaseQuoteFlag;
	private String status;
	private String statusText;
	private String tmpCustNo;

	private Integer salesContact;
	private int defaultTab;
	private String salesContactUser;
	// 用于删除
	private String statusReason;
	private String comment;
	private Integer custNo;
	private List<String> quoteRefList;
	private List<String> orderRefList;
	// 用于显示列表
	private Map<String, QuoteItemDTO> itemMap;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private CustomerService customerService;
	// Sales Information
	private List<Warehouse> warehouseList;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private ExceptionService exceptionUtil;
	private List<ProcessLogDTO> quoteStatusList;// quote status history
	private List<PbDropdownListOptions> priorityList;// sales tab priority
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private String prmtCode;
	private String tempStatus;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private FileService fileService;
	// 用户获取非quote列表页面到修改页面的URL--Zhang Yong
	private String operation_method;
	// Sales Information通过source获得promotion code list.
	private Integer sourceId;
	private String isSalesManager;
	// 显示打印页对象
	private QuotationPrintDTO quotationPrintDTO;
	private String shippingAccount;
	private List<User> projectManagerList;
	private List<User> altProjectManagerList;
	private String busEmail;

	// coupon
	private String couponCode;

	// print
	private String editFlg;



	public void convertToOrder() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Integer userId = SessionUtil.getUserId();
			Integer dataId = quoteService.convertToOrder(quoteNo, userId);
			rt.put("quoteNo", dataId);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
	}

	@SuppressWarnings("unchecked")
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessQuoteNo != null && !("").equals(sessQuoteNo)) {
			String editUrl = "quote/quote!edit.action?quoteNo=" + sessQuoteNo;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message", "Save quote fail,the quote is editing by "
						+ operation_method);
				rt.put("no", sessQuoteNo);
				Struts2Util.renderJson(rt);
				// 清除Session
				SessionUtil.deleteRow(SessionConstant.QuotePackage.value(),
						sessQuoteNo);
				SessionUtil.deleteRow(SessionConstant.QuoteItemList.value(),
						sessQuoteNo);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		try {
			quote = (QuoteMainDTO) SessionUtil.getRow(
					SessionConstant.Quote.value(), sessQuoteNo);
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			/** add by lizhang 给订单添加赠品 ***/
			// QuoteItemDTO newAddItem =(QuoteItemDTO) SessionUtil.getRow(
			// SessionConstant.NewAddQuoteItem.value(), sessQuoteNo);
			// if(itemMap!=null&&newAddItem!=null) {
			// itemMap.put(SessionUtil.generateTempId(), newAddItem);
			// SessionUtil.deleteRow(SessionConstant.NewAddQuoteItem.value(),
			// sessQuoteNo);
			// }
			/** end **/
			this.autoHandleDataForQuote();
			// 数据验证
			WSException exDTO = this.validateQuote();
			if (exDTO != null) {
				rt.put("hasException", "Y");
				rt.put("exception", exDTO);
				Struts2Util.renderJson(rt);
				return null;
			}
			// 保存quote items
			this.attachQuoteItem();
			// 保存order packages
			this.attachPackage();
			this.attachNote();
			// 处理payment数据
			this.attachPaymentList();
			// Quotation->Status处理逻辑，当Quotation total没值时，Status 为NW
			// ,反之为OP已经CW、VD状态除外
			// if (StringUtils.isNumeric(sessQuoteNo)
			// && itemMap != null && itemMap.size() > 0) {
			// Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
			// .iterator();
			// while (it.hasNext()) {
			// Entry<String, QuoteItemDTO> entry = it.next();
			// QuoteItemDTO itemDTO = entry.getValue();
			// if(QuoteItemStatusType.CM.value().equals(itemDTO.getStatus())){
			// if(quoteService.isPackageExist(Integer.parseInt(sessQuoteNo))){
			// quote.setStatus(QuoteStatusType.OP.value());
			// }else{
			// quote.setStatus(QuoteStatusType.NW.value());
			// }
			// }
			// }
			// }
			if (StringUtils.isNumeric(sessQuoteNo)
					&& ("NW").equalsIgnoreCase(quote.getStatus())) {
				if (quoteService.isPackageExist(Integer.parseInt(sessQuoteNo))) {
					quote.setStatus(QuoteStatusType.OP.value());
				} else {
					quote.setStatus(QuoteStatusType.NW.value());
				}
			}
			QuoteMainDTO model = quoteService.saveQuote(quote,
					SessionUtil.getUserId());
			rt.put("no", model.getQuoteNo() + "");
			rt.put("message", "The quote is saved.");
			// 清除Session
			SessionUtil.deleteRow(SessionConstant.QuotePackage.value(),
					sessQuoteNo);
		} catch (Exception e) {
			if (e instanceof BussinessException) {
				BussinessException be = (BussinessException) e;
				if (StringUtils.isNotBlank(be.getCode()) && be.getCode().equalsIgnoreCase("SE0203")) {
					WSException exDTO = exceptionUtil.getExceptionDetails(e);
					List<String> paramValue = be.getReplaceParamValues();
					if (paramValue != null && !paramValue.isEmpty()) {
						exDTO.setMessageDetail(paramValue.get(0));
						exDTO.setAction("");
						rt.put("hasException", "Y");
						rt.put("exception", exDTO);
						Struts2Util.renderJson(rt);
						return null;
					}
				}
			}
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

	// 检查quote 数据
	private void autoHandleDataForQuote() {
		quote.setShippingAccount(shippingAccount);
		Integer shiptoAddrFlag = quote.getShiptoAddrFlag();
		// shiptoAddrFlag 不合法自动赋值1
		if (shiptoAddrFlag == null || shiptoAddrFlag.intValue() <= 0
				|| shiptoAddrFlag.intValue() > 3) {
			quote.setShiptoAddrFlag(1);
			shiptoAddrFlag = 1;
		}
		if (itemMap != null && itemMap.size() > 0) {
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO itemDTO = entry.getValue();
				if (itemDTO == null) {
					it.remove();
					itemMap.remove(entry.getKey());
					continue;
				}
				// 处理address数据
				if (shiptoAddrFlag.intValue() == 1
						|| shiptoAddrFlag.intValue() == 2) {
					if (quote.getQuoteAddrList() != null
							&& quote.getQuoteAddrList().getShipToAddr() != null) {
						itemDTO.setShipToAddress(quote.getQuoteAddrList()
								.getShipToAddr());
					}
				}
				if (itemDTO.getShipSchedule() == null) {
					itemDTO.setShipSchedule(0);
				}
			}
		}
		if (quote.getAmount() == null) {
			quote.setAmount(new BigDecimal(0.0));
		}
		if (quote.getShipAmt() == null) {
			quote.setShipAmt(new BigDecimal(0.0));
		}
		if (quote.getSubTotal() == null) {
			quote.setSubTotal(new BigDecimal(0.0));
		}
		if (quote.getDiscount() == null) {
			quote.setDiscount(new BigDecimal(0.0));
		}
		if (quote.getTax() == null) {
			quote.setTax(new BigDecimal(0.0));
		}
	}

	/**
	 * 保存前验证数据
	 * 
	 * @return
	 */
	private WSException validateQuote() {
		WSException exDTO = null;
		if (itemMap == null || itemMap.size() == 0) {
			exDTO = new WSException();
			exDTO.setMessageDetail("No item added, please added one item at least.");
			exDTO.setAction("");
			return exDTO;
		}
		if (quote.getQuoteAddrList().getShipToAddr() == null) {
			exDTO = new WSException();
			exDTO.setMessageDetail("The address of 'Ship To' is required");
			exDTO.setAction("");
			return exDTO;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		int i=1;
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO tmpItem = entry.getValue();
			String itemKey = entry.getKey();
			tmpItem.setItemNo(i);
			itemMap.put(itemKey, tmpItem);
			i++;
		}
		SessionUtil.insertRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, itemMap);
		Iterator<Entry<String, QuoteItemDTO>> iter = itemMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, QuoteItemDTO> entry = iter.next();
			QuoteItemDTO tmpItem = entry.getValue();
			if (tmpItem.getShipToAddress() == null && !"CN".equalsIgnoreCase(tmpItem.getStatus())) {
				exDTO = new WSException();
				exDTO.setMessageDetail("The 'ship-to' addres of Item#" + tmpItem.getItemNo() + " is required");
				exDTO.setAction("");
				return exDTO;
			}
		}
		return null;
	}

	/**
	 * 处理payment数据
	 */
	@SuppressWarnings("unchecked")
	private void attachPaymentList() {
		Map<String, QuotePaymentPlan> paymentMap = (Map<String, QuotePaymentPlan>) SessionUtil
				.getRow(SessionConstant.QuotePayment.value(), sessQuoteNo);
		if (paymentMap != null) {
			Iterator<Entry<String, QuotePaymentPlan>> it = paymentMap
					.entrySet().iterator();
			List<Integer> delList = new ArrayList<Integer>();
			List<QuotePaymentPlan> paymentList = new ArrayList<QuotePaymentPlan>();
			while (it.hasNext()) {
				Entry<String, QuotePaymentPlan> entry = it.next();
				QuotePaymentPlan shipPackageDTO = entry.getValue();
				String tmpId = entry.getKey();
				if (StringUtils.isNumeric(tmpId) && shipPackageDTO == null) {
					delList.add(Integer.parseInt(tmpId));
				} else {
					paymentList.add(shipPackageDTO);
				}
			}
			if (!delList.isEmpty()) {
				quote.setDelPaymentPlanIdList(delList);
			}
			if (!paymentList.isEmpty()) {
				quote.setPaymentPlanList(paymentList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void attachPackage() {
		Map<String, QuotePackageDTO> packageMap = (Map<String, QuotePackageDTO>) SessionUtil
				.getRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		if (packageMap != null) {
			Iterator<Entry<String, QuotePackageDTO>> it = packageMap.entrySet()
					.iterator();
			List<Integer> delList = new ArrayList<Integer>();
			List<QuotePackageDTO> packageList = new ArrayList<QuotePackageDTO>();
			while (it.hasNext()) {
				Entry<String, QuotePackageDTO> entry = it.next();
				QuotePackageDTO quotePackage = entry.getValue();
				String tmpId = entry.getKey();
				if (StringUtils.isNumeric(tmpId)) {
					delList.add(Integer.parseInt(tmpId));
				} else {
					packageList.add(quotePackage);
				}
			}
			if (!delList.isEmpty()) {
				quote.setDelQuotePackageIdList(delList);
			}
			if (!packageList.isEmpty()) {
				quote.setQuotePackageList(packageList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void attachNote() {
		List<InstructionDTO> noteList = new ArrayList<InstructionDTO>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, InstructionDTO> sessAddrMap = (Map<String, InstructionDTO>) SessionUtil
				.getRow(SessionConstant.QuoteNote.value(), this.sessQuoteNo);
		if (sessAddrMap != null) {
			Iterator<Entry<String, InstructionDTO>> it = sessAddrMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, InstructionDTO> entry = it.next();
				InstructionDTO tmpDTO = (InstructionDTO) entry.getValue();
				if (tmpDTO == null && StringUtils.isNumeric(entry.getKey())) {
					delIdList.add(Integer.parseInt(entry.getKey()));
				} else {
					if (tmpDTO.getId() != null
							&& tmpDTO.getId().intValue() == 0) {
						tmpDTO.setId(null);
					}
					noteList.add(tmpDTO);
				}
			}
		}
		if (!delIdList.isEmpty()) {
			// quoteMain.setd
		}
		if (!noteList.isEmpty()) {
			quote.setInstructionList(noteList);
		}
	}

	private void attachQuoteItem() throws Exception {
		List<QuoteItemDTO> quoteItemDTOList = new ArrayList<QuoteItemDTO>();
		if (itemMap != null) {
			if (StringUtils.isNumeric(sessQuoteNo)) {
				List<QuoteItem> dbItemList = quoteService
						.getQuoteAllItemList(Integer.parseInt(sessQuoteNo));
				if (dbItemList != null && dbItemList.size() > 0) {
					List<Integer> delQuoteItemIdList = new ArrayList<Integer>();
					for (QuoteItem dbOrderItem : dbItemList) {
						if (!itemMap.containsKey(dbOrderItem.getQuoteItemId()
								.toString())) {
							delQuoteItemIdList
									.add(dbOrderItem.getQuoteItemId());
						}
					}
					quote.setDelItemIdList(delQuoteItemIdList);
				}
			}
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO tmpItemDTO = entry.getValue();
				String tmpKey = entry.getKey();
				if (StringUtils.isEmpty(tmpItemDTO.getParentId())) {
					this.attachSubItemList(tmpKey, tmpItemDTO);
					quoteItemDTOList.add(tmpItemDTO);
				}
			}
		}
		quote.setItemList(quoteItemDTOList);
	}

	/**
	 * 获得子itemList
	 * 
	 * @param itemId
	 * @return
	 */
	private void attachSubItemList(String itemId, QuoteItemDTO itemDTO)
			throws Exception {
		List<QuoteItemDTO> subItemList = new ArrayList<QuoteItemDTO>();
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			QuoteItemDTO tmpItemDTO = entry.getValue();
			if (itemId.equalsIgnoreCase(tmpItemDTO.getParentId())) {
				this.attachSubItemList(tmpKey, tmpItemDTO);
				subItemList.add(tmpItemDTO);
			}
		}
		if (subItemList.size() > 0) {
			itemDTO.setSubItemList(subItemList);
		}
	}

	public String saveSaleInfo() {
		QuoteMainDTO sessQuote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), this.sessQuoteNo);
		BigDecimal zero = new BigDecimal(0);
		if (zero.compareTo(quote.getTax()) == 0) {
			quote.setTax(null);
		}
		if (zero.compareTo(quote.getDiscount()) == 0) {
			quote.setDiscount(null);
		}
		try {
			ModelUtils.mergerModel(this.quote, sessQuote);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SessionUtil.insertRow(SessionConstant.Quote.value(), this.sessQuoteNo,
				sessQuote);
		Struts2Util.renderJson("successful");
		return null;
	}

	/**
	 * 执行删除操作， 返回list页面.
	 * 
	 * @return
	 */
	public String delete() {
		try {
			if (quoteNos != null) {
				// 判断当前用户是否含有销售经理角色
				boolean salesManager = privilegeService
						.checkIsSalesManagerRole();
				if (!salesManager) {
					Struts2Util.renderText("error");
					return null;
				}
				int delTotal = quoteNos.length;
				int delSuccessCount = 0;
				for (Integer quoteNo : quoteNos) {
					quoteService.delQuote(quoteNo, status, statusReason,
							SessionUtil.getUserId(), comment);
					delSuccessCount++;
				}
				if (delTotal == delSuccessCount) {
					Struts2Util.renderText("success");
				} else {
					Struts2Util.renderText("error");
				}
			}
		} catch (Exception ex) {
			Struts2Util.renderText("error");
		}
		return null;
	}

	/**
	 * 根据itemId获得详细信息
	 * 
	 * @return
	 */
	/*
	 * public String getItemDetail(){ Integer
	 * quoteNo=Integer.parseInt(ServletActionContext
	 * .getRequest().getParameter("sessQuoteNo")); quote
	 * =quoteService.getQuoteDetail(quoteNo);
	 * quoteItemDTO=quote.getItemList().get(Integer.valueOf(itemId));
	 * Struts2Util.renderJson(quoteItemDTO); return null; }
	 */
	/**
	 * 返回quote的新增或修改页面; 如果参数quoteNo为null则意为新增， 不为null则是修改该quoteNo对应的quote
	 * 新增与修改返回的是同一个页面， 但修改则要查出该quote对应的各个Tab中的数据 对于页面中的下拉框的数据源则无论是新增还是修改都需要提供
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public String edit() {
		try {
			// 下拉列表
			dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
			dropDownList.put("STATUS_UPD_REASON",
					publicService.getDropdownList("QUOTE_STATUS_UPD_REASON"));
			// 判断当前用户是否含有销售经理角色(超级管理员拥有所有权限)
			boolean salesManager = privilegeService.checkIsSalesManagerRole();
			if (salesManager) {
				isSalesManager = "true";
			} else {
				isSalesManager = "false";
			}
			if (quoteNo != null) {// 修改
				// *********** Add By Zhang Yong Start
				// *****************************//
				// 判断将要修改的单号是否正在被操作
				String editUrl = "quote/quote!edit.action?quoteNo=" + quoteNo;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
				// *********** Add By Zhang Yong End
				// *****************************//

				this.quote = this.quoteService.getQuoteDetail(quoteNo);
				this.tempStatus = quote.getStatus();
				this.setCustNo(this.quote.getCustNo());
				this.sessQuoteNo = Integer.toString(quoteNo);
				// 清除Quote模块各子模块的session
				SessionUtil.deleteRow(SessionConstant.QuoteNote.value(),
						this.sessQuoteNo);
				SessionUtil.deleteRow(SessionConstant.QuotePackage.value(),
						this.sessQuoteNo);
				SessionUtil.deleteRow(SessionConstant.QuotePayment.value(),
						this.sessQuoteNo);
				SessionUtil
						.deleteRow(SessionConstant.QuoteShippingTotal.value(),
								sessQuoteNo);
				SessionUtil.deleteRow(
						SessionConstant.OrderBillingTotal.value(), sessQuoteNo);
				SessionUtil.deleteRow(SessionConstant.OtherPeptideList.value(),
						sessQuoteNo);
				// 建新的session
				SessionUtil.insertRow(SessionConstant.Quote.value(),
						sessQuoteNo, quote);
				// itemlist
				itemMap = SessionUtil.convertList2Map(quote.getItemList(),
						"quoteItemId");
				SessionUtil.insertRow(SessionConstant.QuoteItemList.value(),
						sessQuoteNo, itemMap);
			} else {// 新增
				this.sessQuoteNo = SessionUtil.generateTempId();
				this.quote = new QuoteMainDTO();
				// 初始化数据
				this.quote.setCustNo(custNo);
				this.quote.setStatus(QuoteStatusType.NW.value());
				this.quote.setStatusText("New");
				this.quote.setBaseCurrency(CurrencyType.USD.value());
				this.quote.setQuoteCurrency(quoteCurrency);
				this.quote.setTechSupportUser(techSupportUser);
				this.quote.setTechSupport(techSupport);
				this.quote.setPaymentTerm(paymentTerm);
				this.quote.setShiptoAddrFlag(1);
				this.quote.setSalesContact(salesContact);
				Date date = new Date();
				Date exDate = new Date();
				int l = date.getMonth() + 3;
				exDate.setMonth(l);
				this.quote.setQuoteDate(date);
				this.quote.setExprDate(exDate);
				this.quote.setSalesContactUser(salesContactUser);
				this.quote.setProjectSupport(projectSupport);
				this.quote.setProjectSupportUser(projectSupportUser);
				this.quote.setWarehouseId(0);
				this.quote.setAltQuoteNo(this.sessQuoteNo.substring(0, 5));// 临时数据
				SessionUtil.insertRow(SessionConstant.Quote.value(),
						this.sessQuoteNo, this.quote);

				// *********** Add By Zhang Yong Start
				// *****************************//
				// 释放application中的订单锁，主要针对从其他模块中新建此对象时
				OrderLockRelease realeseOrderLock = new OrderLockRelease();
				realeseOrderLock.releaseOrderLock();
				// *********** Add By Zhang Yong End
				// *****************************//

				// 2011-03-17: by zhangyong用于复制customer的accouting -> notes.
				this.copyNotesFromCustomer(custNo);
			}
			@SuppressWarnings("unchecked")
			Map<String, QuoteItemDTO> _sessItemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			if (_sessItemMap == null) {
				_sessItemMap = new LinkedHashMap<String, QuoteItemDTO>();
				SessionUtil.insertRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, _sessItemMap);
			}
			busEmail = this.quoteService.findBusEmailByNo(custNo, quoteNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "quote_edit";
	}

	/**
	 * 用于复制customer的accouting -> notes.
	 * 
	 * @param customerNo
	 */
	private void copyNotesFromCustomer(Integer customerNo) {
		List<CustNoteDTO> noteList = customerService.getNoteList(customerNo);
		if (noteList != null && noteList.size() > 0) {
			Map<String, InstructionDTO> sessNoteMap = new LinkedHashMap<String, InstructionDTO>();
			for (CustNoteDTO custNote : noteList) {
				InstructionDTO orderNote = new InstructionDTO();
				orderNote.setType(custNote.getType());
				orderNote.setDescription(custNote.getDescription());
				orderNote.setDocFlag(custNote.getDocFlag());
				orderNote.setInstructionDate(new Date());
				if (custNote.getDocumentList() != null
						&& custNote.getDocumentList().size() > 0) {
					List<Document> docList = new ArrayList<Document>();
					for (NoteDocument custNoteDoc : custNote.getDocumentList()) {
						Document doc = new Document();
						doc.setRefType(DocumentType.QUOTE_INST_NOTE.value());
						doc.setDocName(custNoteDoc.getDocName());
						// 上传文件
						try {
							File srcFile = new File(fileService.getUploadPath()
									+ "/" + custNoteDoc.getFilePath());
							String tagDir = fileService.getUploadPath() + "/"
									+ "quote_notes";
							String shortName = SessionUtil.generateTempId()
									+ "_" + custNoteDoc.getDocName();
							File tagFile = new File(tagDir + "/" + shortName);
							FileUtils.copyFile(srcFile, tagFile);
							doc.setFilePath("quote_notes/" + shortName);
							docList.add(doc);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					orderNote.setDocumentList(docList);
				}
				try {
					sessNoteMap.put(SessionUtil.generateTempId(), orderNote);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			SessionUtil.insertRow(SessionConstant.QuoteNote.value(),
					sessQuoteNo, sessNoteMap);
		}
	}

	/**
	 * 更新quote salesInfo 的状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateStatus() throws Exception {
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		quote.setStatus(status);
		quote.setStatusText(statusText);
		quote.setStatusReason(statusReason);
		SessionUtil
				.updateRow(SessionConstant.Quote.value(), sessQuoteNo, quote);
		if (QuoteStatusType.CO.value().equals(status)) {
			Map<String, QuoteItemDTO> quoteItemMap = (Map<String, QuoteItemDTO>) SessionUtil
					.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			if (quoteItemMap != null && !quoteItemMap.isEmpty()) {
				for (String key : quoteItemMap.keySet()) {
					quoteItemMap.get(key).setStatus(
							QuoteItemStatusType.CN.value());
				}
			}
		}
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 根据请求的sessQuoteNo获得相关数据，显示Sales Information标签.
	 * 
	 * @return
	 */
	public String getInformation() {
		// 下拉列表 order_type
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("QUOTE_TYPE",
				publicService.getDropdownList("QUOTE_TYPE"));

		try {

			quote = (QuoteMainDTO) SessionUtil.getRow(
					SessionConstant.Quote.value(), this.sessQuoteNo);
			prmtCode = quote.getQuotePromotion() == null ? "" : quote
					.getQuotePromotion().getPrmtCode();
			couponCode = quote.getCouponCode();
			// 获得页面下拉框的数据源
			List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
			speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
			speclListName.add(SpecDropDownListName.SALES_CONTACT);
			speclListName.add(SpecDropDownListName.TECH_SUPPORT);
			speclListName.add(SpecDropDownListName.PROJECT_SUPPORT);
			speclListName.add(SpecDropDownListName.QUOTE_MEMO_TEMPLATE);
			specDropDownList = publicService.getSpecDropDownMap(speclListName);
			// 把Promotion Code list Drop-Down也放入Map中.

			// 显示或修改已存在的order时才会在promotion code下拉中显示相关选项
			if (StringUtils.isNumeric(this.sessQuoteNo)) {

			} else {
				CustomerDTO customerDTO = customerService.getCustomerBase(quote
						.getCustNo());
				Integer companyId = customerDTO.getCompanyId();
				if (companyId != null) {
					quote.setGsCoId(companyId);
					Warehouse warehouse = inventoryService
							.getCompanyDefaultWarehouse(companyId);
					if (warehouse != null) {
						quote.setWarehouseId(warehouse.getWarehouseId());
					}
				}
				// 特殊客户(Merck)的Promotion在新增quote时选中
				this.doSpecPromotionFilter(customerDTO);
			}
			// mod by lizhang promotioncode select list
			Integer quoteSrc = null;// 保存source下拉列表默认第一项
			DropDownListDTO dropDownListDTO = specDropDownList
					.get(SpecDropDownListName.ORIGINAL_SOURCE);
			if (dropDownListDTO != null
					&& dropDownListDTO.getDropDownDTOs() != null) {
				quoteSrc = Integer.parseInt(dropDownListDTO.getDropDownDTOs()
						.get(0).getId());
			}
			Integer defaultSourceKey = quote.getQuoteSrc() == null ? quoteSrc
					: quote.getQuoteSrc();
			List<String> prmtCodeList = this.systemSettingService
					.getPrmtCdListBySourceId(defaultSourceKey,
							this.sessQuoteNo, quote.getCustNo(), "quote");
			if (quote.getQuotePromotion() != null
					&& !prmtCodeList.contains(quote.getQuotePromotion()
							.getPrmtCode())) {
				prmtCodeList.add(quote.getQuotePromotion().getPrmtCode());
			}
			List<DropDownDTO> prmtList = new ArrayList<DropDownDTO>();
			if (prmtCodeList != null && !prmtCodeList.isEmpty()) {
				for (String prmtCode : prmtCodeList) {
					DropDownDTO dto = new DropDownDTO();
					dto.setName(prmtCode);
					dto.setValue(prmtCode);
					prmtList.add(dto);
				}
			}
			DropDownListDTO dropDownListDTO2 = new DropDownListDTO();
			dropDownListDTO2.setName(SpecDropDownListName.PROMOTION_CODE
					.value());
			dropDownListDTO2.setDropDownDTOs(prmtList);
			specDropDownList.put(SpecDropDownListName.PROMOTION_CODE,
					dropDownListDTO2);
			// end

			// mod by lizhang couponcode select list
			List<DropDownDTO> couponList = new ArrayList<DropDownDTO>();
			String[] couponCodeArray = StringUtils.isNotBlank(quote
					.getCouponCode()) ? quote.getCouponCode().split(",")
					: new String[] {};
			String[] couponIdArray = StringUtils
					.isNotBlank(quote.getCouponId()) ? quote.getCouponId()
					.split(",") : new String[] {};
			if (couponCodeArray.length != 0) {
				int i = 0;
				for (String couponCode : couponCodeArray) {
					DropDownDTO dto = new DropDownDTO();
					dto.setName(couponCode);
					dto.setValue(couponIdArray[i]);
					i++;
					couponList.add(dto);
				}
			}
			DropDownListDTO dropDownListDTO3 = new DropDownListDTO();
			dropDownListDTO3.setName(SpecDropDownListName.COUPON_CODE.value());
			dropDownListDTO3.setDropDownDTOs(couponList);
			specDropDownList.put(SpecDropDownListName.COUPON_CODE,
					dropDownListDTO3);

			// Quote priority drop down list的数据源
			this.priorityList = this.publicService
					.getDropdownList("ORDER_PRIORITY");
			if (StringUtils.isEmpty(quote.getPriority())) {
				quote.setPriority("Medium");
			}
			String refQuoteNo = quote.getRefQuoteNo();
			if (!StringUtils.isBlank(refQuoteNo)) {
				this.quoteRefList = Arrays.asList(refQuoteNo.split(","));
			} else {
				this.quoteRefList = new ArrayList<String>();
			}

			String refOrderNo = quote.getOrderNo();
			if (!StringUtils.isBlank(refOrderNo)) {
				this.orderRefList = Arrays.asList(refOrderNo.split(","));
			} else {
				this.orderRefList = new ArrayList<String>();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.warehouseList = this.inventoryService.getAllWarehouse();
		projectManagerList = quoteService.getProManagerList(quote);
		altProjectManagerList = quoteService.getAltProManagerList();
		return "quote_information";
	}

	/**
	 * 2011-01-14 By wangsf 特指Promotion的一个过滤方法. 为解决： ‘Merck 添加一个promotion
	 * 只要是Merck的客户Promotion自动应用到这个Promotion’这样的问题
	 * 
	 * @param customerDTO
	 */
	private void doSpecPromotionFilter(CustomerDTO customerDTO) {
		String specPromotionUser = "Merck";
		if (specPromotionUser.equals(customerDTO.getFirstName())
				|| specPromotionUser.equals(customerDTO.getLastName())) {
			Struts2Util.getRequest().setAttribute("merckUser", "Merck");
		}
	}

	/**
	 * 判断手动输入的Promotion Code 是否存在
	 * 
	 * @return
	 */
	public String isPromotionExist() {
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), this.sessQuoteNo);
		String retInfo = null;
		Integer prmtCount = null;
		// 显示或修改已存在的quote时
		if (StringUtils.isNumeric(this.sessQuoteNo)) {
			prmtCount = publicService.isPromotionExist(prmtCode,
					quote.getQuoteDate());
		} else {
			prmtCount = publicService.isPromotionExist(prmtCode, new Date());
		}
		if (prmtCount.intValue() <= 0) {
			retInfo = "N";
		} else {
			retInfo = "Y";
		}
		Struts2Util.renderJson(retInfo);
		return null;
	}

	public String getQuoteStatusHistory() {

		this.sessQuoteNo = ServletActionContext.getRequest().getParameter(
				"sessQuoteNo");
		if (StringUtils.isNumeric(this.sessQuoteNo)) {
			this.quoteStatusList = quoteService.getQuoteStatusHist(Integer
					.valueOf(this.sessQuoteNo));
		}
		return "quote_status_history";
	}

	/**
	 * 重新选择一source获得它的Promotion Code list.
	 * 
	 * @author wangsf
	 * @since 2010-11-18
	 * @param
	 * @return
	 */
	public String getPrmtListBySource() {
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		List<String> list = systemSettingService.getPrmtCdListBySourceId(
				sourceId, this.sessQuoteNo, quote.getCustNo(), "quote");
		Struts2Util.renderJson(list);
		return null;
	} 

	/**
	 * 显示打印页面
	 * 
	 * @return
	 */
	public String showPrintPage() {
		try {
			if (quotationPrintDTO != null) {
				String email = quotationPrintDTO.getEmail();
				String department = quotationPrintDTO.getDepartment();
				String institution = quotationPrintDTO.getInstitution();
				String state = quotationPrintDTO.getState();
				String country = quotationPrintDTO.getCountry();
				String comments =null;
				comments=quotationPrintDTO.getComments();
				String companyName = quotationPrintDTO.getCompanyName();
				String address1 = quotationPrintDTO.getAddress1();
				String address2 = quotationPrintDTO.getAddress2();
				String telephone = quotationPrintDTO.getTelephone();
				String fax = quotationPrintDTO.getFax();
				String custEmail = quotationPrintDTO.getCustEmail();
				String web = quotationPrintDTO.getWeb();
				quotationPrintDTO = this.quoteService
						.getQuotationPrintDTO(quoteNo);
				quotationPrintDTO.setEmail(email);
				quotationPrintDTO.setDepartment(department);
				quotationPrintDTO.setInstitution(institution);
				quotationPrintDTO.setState(state);
				quotationPrintDTO.setCountry(country);
				quotationPrintDTO.setComments(comments);
				quotationPrintDTO.setCompanyName(companyName);
				quotationPrintDTO.setAddress1(address1);
				quotationPrintDTO.setAddress2(address2);
				quotationPrintDTO.setCustEmail(custEmail);
				quotationPrintDTO.setWeb(web);
				quotationPrintDTO.setFax(fax);
				quotationPrintDTO.setTelephone(telephone);
			} else {
				quotationPrintDTO = this.quoteService
						.getQuotationPrintDTO(quoteNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "quotationPrint";
	}

	/**
	 * Sales Information tab中点击update弹出页面.
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	public String updateQuoteStatus() {
		// 获取quote的状态
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		return "quote_confirm_status";
	}

	public QuoteMainDTO getQuote() {
		return quote;
	}

	public void setQuote(QuoteMainDTO quote) {
		this.quote = quote;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public List<String> getQuoteRefList() {
		return quoteRefList;
	}

	public void setQuoteRefList(List<String> quoteRefList) {
		this.quoteRefList = quoteRefList;
	}

	public List<String> getOrderRefList() {
		return orderRefList;
	}

	public void setOrderRefList(List<String> orderRefList) {
		this.orderRefList = orderRefList;
	}

	public List<PbDropdownListOptions> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(List<PbDropdownListOptions> priorityList) {
		this.priorityList = priorityList;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public List<ProcessLogDTO> getQuoteStatusList() {
		return quoteStatusList;
	}

	public void setQuoteStatusList(List<ProcessLogDTO> quoteStatusList) {
		this.quoteStatusList = quoteStatusList;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer[] getQuoteNos() {
		return quoteNos;
	}

	public void setQuoteNos(Integer[] quoteNos) {
		this.quoteNos = quoteNos;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}

	public Map<String, QuoteItemDTO> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, QuoteItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(
			Map<String, List<PbDropdownListOptions>> dropDownList) {
		this.dropDownList = dropDownList;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public String getQuoteCurrency() {
		return quoteCurrency;
	}

	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}

	public String getTechSupportUser() {
		return techSupportUser;
	}

	public void setTechSupportUser(String techSupportUser) {
		this.techSupportUser = techSupportUser;
	}

	public Integer getTechSupport() {
		return techSupport;
	}

	public void setTechSupport(Integer techSupport) {
		this.techSupport = techSupport;
	}

	public Integer getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public Integer getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}

	public String getSalesContactUser() {
		return salesContactUser;
	}

	public void setSalesContactUser(String salesContactUser) {
		this.salesContactUser = salesContactUser;
	}

	public int getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(int defaultTab) {
		this.defaultTab = defaultTab;
	}

	public String getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}

	public int getPurchaseQuoteFlag() {
		return purchaseQuoteFlag;
	}

	public void setPurchaseQuoteFlag(int purchaseQuoteFlag) {
		this.purchaseQuoteFlag = purchaseQuoteFlag;
	}

	public String getPrmtCode() {
		return prmtCode;
	}

	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public Integer getProjectSupport() {
		return projectSupport;
	}

	public void setProjectSupport(Integer projectSupport) {
		this.projectSupport = projectSupport;
	}

	public String getProjectSupportUser() {
		return projectSupportUser;
	}

	public void setProjectSupportUser(String projectSupportUser) {
		this.projectSupportUser = projectSupportUser;
	}

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getTmpCustNo() {
		return tmpCustNo;
	}

	public void setTmpCustNo(String tmpCustNo) {
		this.tmpCustNo = tmpCustNo;
	}

	public String getIsSalesManager() {
		return isSalesManager;
	}

	public void setIsSalesManager(String isSalesManager) {
		this.isSalesManager = isSalesManager;
	}

	public QuotationPrintDTO getQuotationPrintDTO() {
		return quotationPrintDTO;
	}

	public void setQuotationPrintDTO(QuotationPrintDTO quotationPrintDTO) {
		this.quotationPrintDTO = quotationPrintDTO;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getShippingAccount() {
		return shippingAccount;
	}

	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
	}

	public String getBusEmail() {
		return busEmail;
	}

	public void setBusEmail(String busEmail) {
		this.busEmail = busEmail;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public List<User> getProjectManagerList() {
		return projectManagerList;
	}

	public void setProjectManagerList(List<User> projectManagerList) {
		this.projectManagerList = projectManagerList;
	}

	public List<User> getAltProjectManagerList() {
		return altProjectManagerList;
	}

	public void setAltProjectManagerList(List<User> altProjectManagerList) {
		this.altProjectManagerList = altProjectManagerList;
	}

	public String getEditFlg() {
		return editFlg;
	}

	public void setEditFlg(String editFlg) {
		this.editFlg = editFlg;
	}
}
