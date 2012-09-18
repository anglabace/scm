package com.genscript.gsscm.order.web;

import static java.io.File.separator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.inventory.service.InventoryService;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.WorkCenter;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.service.WorkOrderEntryService;
import com.genscript.gsscm.manufacture.service.WorkOrderService;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.OrderPrintDTO;
import com.genscript.gsscm.order.dto.PaymentVoucherDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.order.entity.OrderPromotion;
import com.genscript.gsscm.order.service.OrderItemService;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.service.PurchaseOrderService;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "order_edit", location = "order/order_edit.jsp"),
		@Result(name = "order_view", location = "order/order_view.jsp"),
		@Result(name = "order_status_history", location = "order/order_status_history.jsp"),
		@Result(name = "order_information", location = "order/order_information.jsp"),
		@Result(name = "OrderItemsDocuments", location = "order/order_Items_Documents.jsp"),
		@Result(name = "order_print", location = "order/order_print.jsp"),
		@Result(name = "all_items_detail", location = "order/order_items_detail.jsp"),
		@Result(name = "orderPrint", location = "order/orderPrint.jsp") })
public class OrderAction extends ActionSupport {
	private static final long serialVersionUID = -7825020478873492402L;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private SystemSettingService systemSettingService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private FileService fileService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private OrderItemService orderItemService;
	private Integer orderNo;
	private String sessOrderNo;
	private OrderMainDTO order;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private List<ProcessLogDTO> orderStatusList;// order status history.
	private List<PbDropdownListOptions> priorityList;// sales tab priority
	private List<String> orderRefList;
	private List<String> quoteRefList;
	private Integer custNo;
	// save template时是否覆盖已有同名的template.
	private String overrideFlag;
	private String tmplName;
	private Integer maxMyTemplateCount = 6;

	private static String FILE_STORAGE = "documents";
	private static String FE = "exl";
	
	private static String batchOligo = "batchOligo";

	// 用于删除
	private String statusReason;
	private String comment;
	private Integer[] orderNos;

	private Map<String, OrderItemDTO> itemMap;
	// 新建时候初始化数据
	private String orderCurrency;
	private String techSupportUser;
	private Integer techSupport;
	private Integer paymentTerm;
	private Integer salesContact;
	private String salesContactUser;
	private Integer projectSupport;
	private String projectSupportUser;
	private int defaultTab;
	private String prmtCode;

	private int purchaseOrderFlag;
	private int salesOrderFlag;
	private String billAccCode;
	private Integer purchaseOrderNo;
	// 用于confirm order
	private String status;
	private String statusText;
	// 用于保存order
	private String shippingRule;
	private String shippingType;
	private String shippingAccount;
	// Sales Information
	private List<Warehouse> warehouseList;
	// 获取从非Order列表页面点过来的请求--Zhang Yong
	private String operation_method;
	// Sales Information通过source获得promotion code list.
	private Integer sourceId;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private WorkOrderEntryService workOrderEntryService;
	private String isSalesManager;
	private OrderPrintDTO orderPrintDTO;
	private String busEmail;
	private Customer searchCust;
	// coupon
	private String couponCode;
	private QuoteMain searchQuote;
	private List<User> projectManagerList;
	private List<User> altProjectManagerList;
	private String allitemsDetail;
	private List<Document> LicensedocumentList = new ArrayList<Document>();
    private List<PbDropdownListOptions> updateReasonSel;
	private int shippedFlag;
	
	
	private Integer lookFromWoFlag;

	public List<Document> getLicensedocumentList() {
		return LicensedocumentList;
	}

	public void setLicensedocumentList(List<Document> licensedocumentList) {
		LicensedocumentList = licensedocumentList;
	}

	// print
	private String editFlg;
	private int internalOrderFlag;

	public String allItemDetail() {
		allitemsDetail = this.orderService.getAllItemsDetail(sessOrderNo,2);
		return "all_items_detail";
	}

	public void createTxt4OrderItems() {
		StringBuffer sb = new StringBuffer();
		allitemsDetail = this.orderService.getAllItemsDetail(sessOrderNo,1);
		long r1 = System.currentTimeMillis();
		String fileName = "orderNo-" + this.sessOrderNo + r1 + ".txt";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			File tempFile = new File(getDocumentItemStorage(request)
					+ separator + fileName);
			if (tempFile.exists()) {
				if (tempFile.delete()) {
					System.out.println("delete the ...." + tempFile.getName());
				}
			}
			FileOutputStream fos = null;
			FileUtils.forceMkdir(tempFile.getParentFile());
			System.out.println(">>>>>>>" + allitemsDetail.toString());
			fos = new FileOutputStream(tempFile, true);
			if (allitemsDetail == null || "".equals(allitemsDetail)) {
				allitemsDetail = "Sorry! there has none allitemsDetail about the order Items   \r\n";
			}
			sb.append(allitemsDetail);
			fos.write((sb.toString()).getBytes());
			fos.flush();
			fos.close();
			HttpServletResponse response = Struts2Util.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			java.io.OutputStream os = response.getOutputStream();
			String file = getDocumentItemStorage(request) + separator
					+ fileName;
			java.io.FileInputStream fis = new java.io.FileInputStream(file);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
					+ exDTO.getAction());
			e.printStackTrace();
		}
	}

	public void createDocument4OrderItems() {
		StringBuffer sb = new StringBuffer();
		allitemsDetail = this.orderService.getAllItemsDetail(sessOrderNo,1);
		long r1 = System.currentTimeMillis();
		String fileName = "orderNo-" + this.sessOrderNo + r1 + ".doc";
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			File tempFile = new File(getDocumentItemStorage(request)
					+ separator + fileName);
			if (tempFile.exists()) {
				if (tempFile.delete()) {
					System.out.println("delete the ...." + tempFile.getName());
				}
			}
			FileOutputStream fos = null;
			FileUtils.forceMkdir(tempFile.getParentFile());
			System.out.println(">>>>>>>" + allitemsDetail.toString());
			fos = new FileOutputStream(tempFile, true);
			if (allitemsDetail == null || "".equals(allitemsDetail)) {
				allitemsDetail = "Sorry! there has none allitemsDetail about the order Items   \r\n";
				sb.append(allitemsDetail);
			}
			sb.append(allitemsDetail);
			fos.write((sb.toString()).getBytes());
			fos.flush();
			fos.close();
			sb = null;
			HttpServletResponse response = Struts2Util.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			java.io.OutputStream os = response.getOutputStream();
			String file = getDocumentItemStorage(request) + separator
					+ fileName;
			java.io.FileInputStream fis = new java.io.FileInputStream(file);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
		} catch (Exception e) {
			System.out.println("Error exec!");
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail() + "\n"
					+ exDTO.getAction());
			e.printStackTrace();
		}

	}

	public static String getFileStorage(HttpServletRequest request, String sss) {
		return request.getSession().getServletContext()
				.getRealPath(FILE_STORAGE + separator + sss);
	}

	public String getAllitemsDetail() {
		return allitemsDetail;
	}

	public void setAllitemsDetail(String allitemsDetail) {
		this.allitemsDetail = allitemsDetail;
	}

	public static String getDocumentItemStorage(HttpServletRequest request) {
		return getFileStorage(request, FE);
	}

	public String download() {
		String fileName = "Oligo_Batch_Order.xls";
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			java.io.OutputStream os = response.getOutputStream();
			String file = getDocumentItemStorage(request) + separator
					+ fileName;
			System.out.println(file);
			java.io.FileInputStream fis = new java.io.FileInputStream(file);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行删除操作， 返回list页面.
	 * 
	 * @return
	 */
	public String delete() {
		if (orderNos != null) {
			try {
				// 判断当前用户是否含有销售经理角色
				boolean salesManager = privilegeService
						.checkIsSalesManagerRole();
				if (!salesManager) {
					Struts2Util.renderText("error");
					return null;
				}
				int delTotal = orderNos.length;
				int delSuccessCount = 0;
				for (Integer orderNo : orderNos) {
					OrderMainDTO order = orderService.getOrderDetail(orderNo);
					order.setStatus(OrderStatusType.CN.value());
					orderService.delOrder(orderNo, statusReason,
							SessionUtil.getUserId(), comment, order);
					delSuccessCount++;
				}
				if (delTotal == delSuccessCount) {
					Struts2Util.renderText("success");
				} else {
					Struts2Util.renderText("error");
				}
			} catch (Exception ex) {
				Struts2Util.renderText("error");
			}
		}
		return null;
	}

	/**
	 * 返回order的新增或修改页面; 如果参数orderNo为null则意为新增， 不为null则是修改该orderNo对应的order
	 * 新增与修改返回的是同一个页面， 但修改则要查出该order对应的各个Tab中的数据 对于页面中的下拉框的数据源则无论是新增还是修改都需要提供
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() {
		try {
			// 下拉列表
			dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
			dropDownList.put("STATUS_UPD_REASON", publicService.getDropdownList("ORDER_STATUS_UPD_REASON"));
			// 判断当前用户是否含有销售经理角色(超级管理员拥有所有权限)
			boolean salesManager = privilegeService.checkIsSalesManagerRole();
			if (salesManager) {
				isSalesManager = "true";
			} else {
				isSalesManager = "false";
			}
			if (orderNo != null) {// 修改
				// *********** Add By Zhang Yong Start
				// *****************************//
				// 判断将要修改的单号是否正在被操作
				String editUrl = "order/order!edit.action?orderNo=" + orderNo;
				OrderLockRelease orderLockRelease = new OrderLockRelease();
				String byUser = orderLockRelease.checkOrderStatus(editUrl);
				if (byUser != null) {
					operation_method = byUser;
				}
				// *********** Add By Golf
				PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderBySoId(orderNo);
				if(purchaseOrder != null){
					String purchaseStatus = purchaseOrder.getStatus();
					if("Received".equals(purchaseStatus) || "OP".equals(purchaseStatus)){
						shippedFlag = 1;
					}
				}
				
				//*********** Ended By Golf
				// *********** Add By Zhang Yong End
				// *****************************//
				this.order = this.orderService.getOrderDetail(orderNo);

				this.setCustNo(this.order.getCustNo());
				this.sessOrderNo = Integer.toString(orderNo);
				CustomerDTO dbCustomerDTO = customerService.getCustomerBase(order.getCustNo());
				if (Constants.INTERNAL_TYPE_CUSTOMER.equalsIgnoreCase(dbCustomerDTO.getCustType())) {
					internalOrderFlag = 1;
				}
				Map<String, Document> sessDocMap = new LinkedHashMap<String, Document>();
				// 因为sessMap可能在session中尚不存在, 所以这句必须要执行
				List<Document> docList = this.order.getDocumentList();
				if (docList != null && !"".equals(docList)) {
					for (Document doc : docList) {
						sessDocMap.put(doc.getDocId() + "", doc);
					}
				}
				SessionUtil.insertRow(SessionConstant.OrderLicenceDocument.value(), this.sessOrderNo, sessDocMap);
				// 初始化session数据
				// 清除Order模块各子模块的session
				SessionUtil.deleteRow(SessionConstant.OrderNote.value(), this.sessOrderNo);
				SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), this.sessOrderNo);
				SessionUtil.deleteRow(SessionConstant.OrderPayment.value(), this.sessOrderNo);
				SessionUtil.deleteRow(SessionConstant.OrderShippingTotal.value(), sessOrderNo);
				SessionUtil.deleteRow(SessionConstant.OrderBillingTotal.value(), sessOrderNo);
				SessionUtil.deleteRow(SessionConstant.OtherPeptideList.value(), sessOrderNo);
				// 建新的session
				SessionUtil.insertRow(SessionConstant.Order.value(), sessOrderNo, order);
				// itemlist
				itemMap = SessionUtil.convertList2Map(order.getItemList(), "orderItemId");
				// sessOrderNo, sessMap);
				SessionUtil.insertRow(SessionConstant.OrderItemList.value(), sessOrderNo, itemMap);
                //add by zhanghubin
                if(order.getItemList().size() > 0 && OrderStatusType.RV.name().equals(order.getStatus())){
                    OrderItemDTO itemdto = order.getItemList().get(0);
                    initConfirmStatus(itemdto.getType(), itemdto.getClsId());
                }
			} else if (StringUtils.isNotBlank(sessOrderNo) && batchOligo.equals(Struts2Util.getParameter("batchType"))) {
				order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			} else {// 新增
				this.sessOrderNo = SessionUtil.generateTempId();
				// 初始化session数据
				Map<String, Document> sessDocMap = new LinkedHashMap<String, Document>();
				// 因为sessMap可能在session中尚不存在, 所以这句必须要执行
				SessionUtil.insertRow(SessionConstant.OrderLicenceDocument.value(), this.sessOrderNo, sessDocMap);
				this.order = new OrderMainDTO();
				if (StringUtils.isNotBlank(Struts2Util.getParameter("workOrderSessNo"))
						&& StringUtils.isNotBlank(Struts2Util.getParameter("workCenterId"))) {
					// 新增内部订单
					this.createInternalOrder(this.order, null);
					//批量Oligo时创建Order到session中并返回json数据
					if (batchOligo.equals(Struts2Util.getParameter("batchType"))) {
						SessionUtil.insertRow(SessionConstant.Order.value(), this.sessOrderNo, this.order);
						Map<String, String> retMap = new HashMap<String, String>();
						retMap.put("sessOrderNo", sessOrderNo);
						retMap.put("custNo", String.valueOf(this.custNo));
						Struts2Util.renderJson(retMap);
						return null;
					}
				} else {
					CustomerDTO dbCustomerDTO = customerService.getCustomerBase(custNo);
					if (Constants.INTERNAL_TYPE_CUSTOMER.equalsIgnoreCase(dbCustomerDTO.getCustType())) {
						purchaseOrderFlag = 0;
						// 新增内部订单
						this.createInternalOrder(this.order, dbCustomerDTO);
					} else {
						// 初始化数据
						this.order.setCustNo(custNo);
						this.order.setStatus(OrderStatusType.NW.value());
						this.order.setStatusText("New");
						this.order.setBaseCurrency(CurrencyType.USD.value());
						this.order.setOrderCurrency(orderCurrency);
						this.order.setTechSupportUser(techSupportUser);
						this.order.setTechSupport(techSupport);
						this.order.setPaymentTerm(paymentTerm);
						this.order.setShiptoAddrFlag(1);
						this.order.setSalesContact(salesContact);
						this.order.setSalesContactUser(salesContactUser);
						this.order.setProjectSupport(projectSupport);
						this.order.setProjectSupportUser(projectSupportUser);
						this.order.setAltOrderNo("88888");// 临时数据 fake data
					}
				}
				SessionUtil.insertRow(SessionConstant.Order.value(), this.sessOrderNo, this.order);
				// *********** Add By Zhang Yong Start
				// *****************************//
				// 释放application中的订单锁
				OrderLockRelease realeseOrderLock = new OrderLockRelease();
				realeseOrderLock.releaseOrderLock();
				// *********** Add By Zhang Yong End
				// *****************************//
				// 2010-11-18: by wangsf用于复制customer的accouting -> notes.
				this.copyNotesFromCustomer(custNo);
			}
			@SuppressWarnings("unchecked")
			Map<String, OrderItemDTO> _sessItemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			if (_sessItemMap == null) {
				_sessItemMap = new LinkedHashMap<String, OrderItemDTO>();
				SessionUtil.insertRow(SessionConstant.OrderItemList.value(), sessOrderNo, _sessItemMap);
			}
			searchCust = this.orderService.findBusEmailByNo(custNo, orderNo);
			if (searchCust != null) {
				busEmail = searchCust.getBusEmail();
			}
			searchQuote = this.quoteService.findByOrderNo(orderNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "order_edit";
	}

    private void initConfirmStatus(String type, Integer cls_id){
        updateReasonSel = publicService.getUpdateReasonDropdownList(type, cls_id);
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
						doc.setRefType(DocumentType.ORDER_INST_NOTE.value());
						doc.setDocName(custNoteDoc.getDocName());
						// 上传文件
						try {
							File srcFile = new File(fileService.getUploadPath()
									+ "/" + custNoteDoc.getFilePath());
							String tagDir = fileService.getUploadPath() + "/"
									+ "order_notes";
							String shortName = SessionUtil.generateTempId()
									+ "_" + custNoteDoc.getDocName();
							File tagFile = new File(tagDir + "/" + shortName);
							FileUtils.copyFile(srcFile, tagFile);
							doc.setFilePath("order_notes/" + shortName);
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
			SessionUtil.insertRow(SessionConstant.OrderNote.value(),
					sessOrderNo, sessNoteMap);
		}
	}

	/**
	 * 创建内部订单: 目前是WorkOrder那边需要
	 * 
	 * @author wangsf
	 * @serialData 2010-11-24
	 * @param orderTmpl
	 */
	private void createInternalOrder(OrderMainDTO orderTmpl, CustomerDTO custDTO) {
		if (custDTO == null) {
			String workOrderSessNo = Struts2Util
					.getParameter("workOrderSessNo");
			if (!StringUtil.isNumeric(workOrderSessNo.trim())) {
				return;
			}
			String workCenterId = Struts2Util.getParameter("workCenterId");
			if (!StringUtil.isNumeric(workCenterId.trim())) {
				return;
			}
			WorkOrder workOrder = workOrderService.getWorkOrderById(Integer
					.parseInt(workOrderSessNo.trim()));
			if (workOrder == null) {
				return;
			}
			WorkCenter workCenter = workOrderEntryService
					.getWorkCenterById(Integer.parseInt(workCenterId.trim()));
			if (workCenter == null || workCenter.getInternalCustNo() == null) {
				return;
			}
			custDTO = this.customerService.getCustomerBase(workCenter
					.getInternalCustNo());
		}
		if (custDTO == null) {
			return;
		}
		// 初始化数据
		this.custNo = custDTO.getCustNo();
		this.orderCurrency = custDTO.getPaymentCurrency();
		this.techSupport = custDTO.getTechSupport();
		this.paymentTerm = custDTO.getPrefShipMthd();
		this.salesContact = custDTO.getSalesContact();
		this.projectSupport = custDTO.getProjectSupport();

		orderTmpl.setCustNo(custDTO.getCustNo());
		orderTmpl.setStatus(OrderStatusType.NW.value());
		orderTmpl.setStatusText("New");
		orderTmpl.setOrderCurrency(custDTO.getPaymentCurrency());
		orderTmpl.setBaseCurrency(custDTO.getPaymentCurrency());
		orderTmpl.setTechSupport(custDTO.getTechSupport());
		orderTmpl.setCustType(custDTO.getCustType());
		// TechSupport FullName
		if (custDTO.getTechSupport() != null) {
			SalesRep temp = this.orderService.getSalesRep(custDTO
					.getTechSupport());
			if (temp != null) {
				orderTmpl.setTechSupportUser(temp.getResourceName());
				this.techSupportUser = temp.getResourceName();
			}
		}
		orderTmpl.setPaymentTerm(custDTO.getPrefShipMthd());
		orderTmpl.setShiptoAddrFlag(1);
		orderTmpl.setSalesContact(custDTO.getSalesContact());
		// SalesContact FullName
		if (custDTO.getSalesContact() != null) {
			SalesRep temp = this.orderService.getSalesRep(custDTO
					.getSalesContact());
			if (temp != null) {
				orderTmpl.setSalesContactUser(temp.getResourceName());
				this.salesContactUser = temp.getResourceName();
			}
		}
		orderTmpl.setProjectSupport(custDTO.getProjectSupport());
		// ProjectSupport FullName
		if (custDTO.getProjectSupport() != null) {
			SalesRep temp = this.orderService.getSalesRep(custDTO
					.getProjectSupport());
			if (temp != null) {
				orderTmpl.setProjectSupportUser(temp.getResourceName());
				this.projectSupportUser = temp.getResourceName();
			}
		}
		orderTmpl.setOrderType(Constants.INTERNAL_TYPE_CUSTOMER);
		orderTmpl.setAltOrderNo("88888");// 临时数据 fake data
	}

	/**
	 * 根据页面提交的数据保存Contact模块中各个Tab的内容, 返回提示信息 对于提交的时间要做格式转化处理
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String save() {
		Map<String, Object> rt = new HashMap<String, Object>();
		// *********** Add By Zhang Yong Start *****************************//
		// 校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (sessOrderNo != null && !("").equals(sessOrderNo)) {
			String editUrl = "order/order!edit.action?orderNo=" + sessOrderNo;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message", "Save order fail,the order is editing by " + operation_method);
				rt.put("no", sessOrderNo);
				Struts2Util.renderJson(rt);
				// 清除Session
				SessionUtil.deleteRow(SessionConstant.OrderLicenceDocument.value(), sessOrderNo);
				SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
				return null;
			}
		}
		// *********** Add By Zhang Yong End *****************************//
		try {
			order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			// 赋值keyInfoChanged
			String keyInfo = Struts2Util.getParameter("keyInfoChanged") == null
					|| "".equals(Struts2Util.getParameter("keyInfoChanged")) ? "0"
					: Struts2Util.getParameter("keyInfoChanged");
			order.setKeyInfoChanged(Integer.parseInt(keyInfo));
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			this.autoHandleDataForOrder();
			// 数据验证
			WSException exDTO = this.validateOrder();
			if (exDTO != null) {
				rt.put("hasException", "Y");
				rt.put("exception", exDTO);
				Struts2Util.renderJson(rt);
				return null;
			}
			// 保存order items
			this.attachOrderItem();
			// 保存order packages
			this.attachPackage();
			this.attachNote();
			// 处理payment数据
			this.attachPaymentList();
			if (StringUtils.isNumeric(sessOrderNo) && itemMap != null && itemMap.size() > 0) {
				Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, OrderItemDTO> entry = it.next();
					OrderItemDTO itemDTO = entry.getValue();
					if (OrderItemStatusType.CM.value().equals(itemDTO.getStatus())) {
						order.setStatus(OrderStatusType.RV.value());
					}
				}
			}
			// 如果保存时候状态为NW，
			if (StringUtils.isNumeric(sessOrderNo) && "NW".equalsIgnoreCase(order.getStatus())) {
				order.setStatus(OrderStatusType.RV.value());
			}
			String prmtCode = Struts2Util.getParameter("order.orderPromotion.prmtCode");
			if (StringUtils.isNotEmpty(prmtCode)) {
				OrderPromotion orderPromotion = new OrderPromotion();
				orderPromotion.setPrmtCode(prmtCode);
			}
			this.attachDocument();
			OrderMain orderMain = (order.getOrderNo() != null ? orderService.getOrder(order.getOrderNo()) : null);
			boolean flag = this.workOrderEntryService.orderToCCCreateWorkOrders(order);
			OrderMainDTO model = orderService.saveOrder(order, SessionUtil.getUserId());
			if (orderMain == null || orderMain.getCouponId() == null || 
					(orderMain != null && !orderMain.getCouponId().equals(order.getCouponId()))) {
				orderService.uploadExcelToFtp(model, orderMain != null ? orderMain.getCouponId() : null, "Account/");// 传送xml文件到ftp
			}
			try {
				this.sessId = SessionUtil.generateTempId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			SessionUtil.deleteRow(SessionConstant.OrderLicenceDocument.value(), sessOrderNo);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.remove(this.sessId);
			rt.put("no", model.getOrderNo() + "");
			rt.put("message", "The order is saved.");
			// 清除Session
			SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
			if(flag) {
				List<Integer> itemIdList = this.orderItemService.getItemIdsByOrderNo(model.getOrderNo());
				StringBuffer items = new StringBuffer("");
				if(itemIdList!=null&&itemIdList.size()>0) {
					for(Integer itemId:itemIdList) {
						if(items.toString().equals("")) {
							items.append(itemId.toString());
						} else {
							items.append(",").append(itemId.toString());
						}
						
					}
				}
				if(!items.toString().equals("")) {
					this.workOrderEntryService.createWorkOrders(items.toString(), null);
				}
			}
		} catch (Exception e) {
			if (e instanceof BussinessException) {
				BussinessException be = (BussinessException) e;
				if (StringUtils.isNotBlank(be.getCode())
						&& be.getCode().equalsIgnoreCase("SE0203")) {
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
					new Exception().getStackTrace()[0].getMethodName(), "INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	// 检查order 数据
	private void autoHandleDataForOrder() {
		order.setShippingRule(shippingRule);
		order.setShippingType(shippingType);
		order.setShippingAccount(shippingAccount);
		Integer shiptoAddrFlag = order.getShiptoAddrFlag();
		// shiptoAddrFlag 不合法自动赋值1
		if (shiptoAddrFlag == null || shiptoAddrFlag.intValue() <= 0
				|| shiptoAddrFlag.intValue() > 3) {
			order.setShiptoAddrFlag(1);
			shiptoAddrFlag = 1;
		}
		if (itemMap != null && itemMap.size() > 0) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO itemDTO = entry.getValue();
				if (itemDTO == null) {
					it.remove();
					itemMap.remove(entry.getKey());
					continue;
				}
				// 处理address数据
				if (shiptoAddrFlag.intValue() == 1
						|| shiptoAddrFlag.intValue() == 2) {
					if (order.getOrderAddrList() != null
							&& order.getOrderAddrList().getShipToAddr() != null) {
						itemDTO.setShipToAddress(order.getOrderAddrList()
								.getShipToAddr());
					}
				}
				if (itemDTO.getShipSchedule() == null) {
					itemDTO.setShipSchedule(0);
				}
			}
		}
		if (order.getAmount() == null) {
			order.setAmount(0d);
		}
		if (order.getShipAmt() == null) {
			order.setShipAmt(0d);
		}
		if (order.getSubTotal() == null) {
			order.setSubTotal(0d);
		}
		if (order.getDiscount() == null) {
			order.setDiscount(0d);
		}
		if (order.getTax() == null) {
			order.setTax(0d);
		}
	}

	/**
	 * 保存前验证数据
	 * 
	 * @return
	 */
	private WSException validateOrder() {
		WSException exDTO = null;
		if (itemMap == null || itemMap.size() == 0) {
			exDTO = new WSException();
			exDTO.setMessageDetail("No item added, please added one item at least.");
			exDTO.setAction("");
			return exDTO;
		}
		if (order.getOrderAddrList() == null
				|| order.getOrderAddrList().getBillToAddr() == null) {
			exDTO = new WSException();
			exDTO.setMessageDetail("The address of 'Bill To' is required");
			exDTO.setAction("");
			return exDTO;
		}
		if (order.getOrderAddrList().getShipToAddr() == null) {
			exDTO = new WSException();
			exDTO.setMessageDetail("The address of 'Ship To' is required");
			exDTO.setAction("");
			return exDTO;
		}
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		int i=1;
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO tmpItem = entry.getValue();
			String itemKey = entry.getKey();
			tmpItem.setItemNo(i);
			itemMap.put(itemKey, tmpItem);
			i++;
		}
		SessionUtil.insertRow(SessionConstant.OrderItemList.value(), sessOrderNo, itemMap);
		Iterator<Entry<String, OrderItemDTO>> iter = itemMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, OrderItemDTO> entry = iter.next();
			OrderItemDTO tmpItem = entry.getValue();
			if (tmpItem.getShipToAddress() == null
					&& !"CN".equalsIgnoreCase(tmpItem.getStatus())) {
				exDTO = new WSException();
				exDTO.setMessageDetail("The 'ship-to' addres of Item#"
						+ tmpItem.getItemNo() + " is required");
				exDTO.setAction("");
				return exDTO;
			}
		}
		return null;
	}

	public String saveSaleInfo() {
		OrderMainDTO sessOrder = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), this.sessOrderNo);
		try {
			boolean bInternal = false;// 是否为内部订单
			if (this.order.getCustNo() == -1) {
				bInternal = true;
			}
			ModelUtils.mergerModel(this.order, sessOrder);
			if (bInternal) {
				sessOrder.setCustNo(-1);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		SessionUtil.insertRow(SessionConstant.Order.value(), this.sessOrderNo,
				sessOrder);
		Struts2Util.renderJson("successful");
		return null;
	}

	// 整理itemList 到 order中.
	private void attachOrderItem() {
		List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();
		if (itemMap != null) {
			if (StringUtils.isNumeric(sessOrderNo)) {
				List<OrderItem> dbItemList = orderService
						.getOrderAllItemList(Integer.parseInt(sessOrderNo));
				if (dbItemList != null && dbItemList.size() > 0) {
					List<Integer> delOrderItemIdList = new ArrayList<Integer>();
					for (OrderItem dbOrderItem : dbItemList) {
						if (!itemMap.containsKey(dbOrderItem.getOrderItemId()
								.toString())) {
							delOrderItemIdList
									.add(dbOrderItem.getOrderItemId());
						}
					}
					order.setDelItemIdList(delOrderItemIdList);
				}
			}
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO tmpItemDTO = entry.getValue();
				String tmpKey = entry.getKey();
				if (StringUtils.isEmpty(tmpItemDTO.getParentId())) {
					this.attachSubItemList(tmpKey, tmpItemDTO);
					orderItemDTOList.add(tmpItemDTO);
				}
			}
		}
		order.setItemList(orderItemDTOList);
	}

	/**
	 * 获得子itemList
	 * 
	 * @author zouyulu
	 * @param itemId
	 * @return
	 */
	private void attachSubItemList(String itemId, OrderItemDTO itemDTO) {
		List<OrderItemDTO> subItemList = new ArrayList<OrderItemDTO>();
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			OrderItemDTO tmpItemDTO = entry.getValue();
			if (itemId.equalsIgnoreCase(tmpItemDTO.getParentId())) {
				this.attachSubItemList(tmpKey, tmpItemDTO);
				subItemList.add(tmpItemDTO);
			}
		}
		if (subItemList.size() > 0) {
			itemDTO.setSubItemList(subItemList);
		}
	}

	/**
	 * 或得package
	 */
	@SuppressWarnings("unchecked")
	private void attachPackage() {
		Map<String, OrderPackageDTO> packageMap = (Map<String, OrderPackageDTO>) SessionUtil
				.getRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		if (packageMap != null) {
			Iterator<Entry<String, OrderPackageDTO>> it = packageMap.entrySet()
					.iterator();
			// List<Integer> delList = new ArrayList<Integer>();
			List<OrderPackageDTO> packageList = new ArrayList<OrderPackageDTO>();
			while (it.hasNext()) {
				Entry<String, OrderPackageDTO> entry = it.next();
				OrderPackageDTO orderPackageDTO = entry.getValue();
				// String tmpId = entry.getKey();
				// if (StringUtils.isNumeric(tmpId)) {
				// delList.add(Integer.parseInt(tmpId));
				// } else {
				packageList.add(orderPackageDTO);
				// }
			}
			// if (!delList.isEmpty()) {
			// order.setDelOrderPackageIdList(delList);
			// }
			if (!packageList.isEmpty()) {
				order.setOrderPackageList(packageList);
			}
		}
	}

	/**
	 * 处理payment数据
	 */
	@SuppressWarnings("unchecked")
	private void attachPaymentList() {
		Map<String, PaymentVoucherDTO> paymentMap = (Map<String, PaymentVoucherDTO>) SessionUtil
				.getRow(SessionConstant.OrderPayment.value(), sessOrderNo);
		if (paymentMap != null) {
			Iterator<Entry<String, PaymentVoucherDTO>> it = paymentMap
					.entrySet().iterator();
			List<Integer> delList = new ArrayList<Integer>();
			List<PaymentVoucherDTO> paymentList = new ArrayList<PaymentVoucherDTO>();
			while (it.hasNext()) {
				Entry<String, PaymentVoucherDTO> entry = it.next();
				PaymentVoucherDTO orderPackageDTO = entry.getValue();
				String tmpId = entry.getKey();
				if (StringUtils.isNumeric(tmpId) && orderPackageDTO == null) {
					delList.add(Integer.parseInt(tmpId));
				} else {
					paymentList.add(orderPackageDTO);
				}
			}
			if (!delList.isEmpty()) {
				order.setDelPaymentPlanIdList(delList);
			}
			if (!paymentList.isEmpty()) {
				order.setPaymentPlanList(paymentList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void attachNote() {
		List<InstructionDTO> noteList = new ArrayList<InstructionDTO>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, InstructionDTO> sessAddrMap = (Map<String, InstructionDTO>) SessionUtil
				.getRow(SessionConstant.OrderNote.value(), this.sessOrderNo);
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
			// orderMain.setd
		}
		if (!noteList.isEmpty()) {
			order.setInstructionList(noteList);
		}
	}

	/**
	 * 根据请求的sessOrderNo获得相关数据，显示Sales Information标签.
	 * 
	 * @return
	 */
	public String getInformation() {
		// 下拉列表 order_type
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("ORDER_TYPE",
				publicService.getDropdownList("ORDER_TYPE"));

		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), this.sessOrderNo);
		if (order != null) {
			prmtCode = order.getOrderPromotion() == null ? "" : order
					.getOrderPromotion().getPrmtCode();
		}
		@SuppressWarnings("unchecked")
		Map<String, Document> sessMap = (Map<String, Document>) SessionUtil
				.getRow(SessionConstant.OrderLicenceDocument.value(),
						this.sessOrderNo);
		if (sessMap != null && !sessMap.isEmpty()) {
			for (Entry<String, Document> entry : sessMap.entrySet()) {
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (entry.getValue() == null) {// 执行了临时的删除操作
					} else {
						LicensedocumentList.add(entry.getValue());
					}
				} else {
					LicensedocumentList.add(entry.getValue());// 新增的
				}
			}
		}
		if (LicensedocumentList.isEmpty() && StringUtils.isNumeric(this.sessOrderNo)) { 
			LicensedocumentList = orderService.getALLDocumentByREFType(
					"LICENCE_ORDER", Integer.parseInt(this.sessOrderNo)); 
		 	if (LicensedocumentList != null && !"".equals(LicensedocumentList)) {
				this.order.setDocumentList(LicensedocumentList);
		 	}
		}

		Map<String, Document> sessDocMap = new LinkedHashMap<String, Document>();
		// 因为sessMap可能在session中尚不存在, 所以这句必须要执行
		
		List<Document> docList = this.order.getDocumentList();
		if(docList!=null && !"".equals(docList)){
		for (Document doc : docList) {
			sessDocMap.put(doc.getDocId() + "", doc);
		}
		}
		SessionUtil.insertRow(SessionConstant.OrderLicenceDocument.value(),
				this.sessOrderNo, sessDocMap);
		// 获得页面下拉框的数据源
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
		speclListName.add(SpecDropDownListName.SALES_CONTACT);
		speclListName.add(SpecDropDownListName.TECH_SUPPORT);
		speclListName.add(SpecDropDownListName.PROJECT_SUPPORT);
		speclListName.add(SpecDropDownListName.ORDER_MEMO_TEMPLATE);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		// 显示或修改已存在的order时才会在promotion code下拉中显示相关选项
		if (StringUtils.isNumeric(this.sessOrderNo)) {
			// publicService.getPrmtCdBySource(order
			// .getOrderSrc(), order.getOrderDate());

			// 从数据库中取出PoNumber.
			// modify by zhanghuibin , add method : getPaymentVoucherList
			PaymentVoucherDTO dbPayment = orderService
					.getPaymentVoucherList(Integer.parseInt(sessOrderNo));
			if (dbPayment != null) {
				Struts2Util.getRequest().setAttribute("poNumber",
						dbPayment.getPoNumber());
			}

		} else {
			CustomerDTO customerDTO = customerService.getCustomerBase(order
					.getCustNo());
			if (customerDTO == null) {
				return "order_information";
			}
			Integer companyId = customerDTO.getCompanyId();
			if (companyId != null) {
				order.setGsCoId(companyId);
				Warehouse warehouse = inventoryService
						.getCompanyDefaultWarehouse(companyId);
				if (warehouse != null) {
					order.setWarehouseId(warehouse.getWarehouseId());
				}
			}
			// 特殊客户(Merck)的Promotion在新增时选中
			this.doSpecPromotionFilter(customerDTO);
		}
		// mod by lizhang promotioncode select list
		Integer orderSrc = null;// 保存source下拉列表默认第一项
		DropDownListDTO dropDownListDTO = specDropDownList
				.get(SpecDropDownListName.ORIGINAL_SOURCE);
		if (dropDownListDTO != null
				&& dropDownListDTO.getDropDownDTOs() != null) {
			orderSrc = Integer.parseInt(dropDownListDTO.getDropDownDTOs()
					.get(0).getId());
		}
		List<String> prmtCodeList = new ArrayList<String>();
		Integer defaultSourceKey = 0;
		try {
			this.sessId = SessionUtil.generateTempId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DropDownDTO> prmtList = new ArrayList<DropDownDTO>();
		if (order != null) {
			defaultSourceKey = order.getOrderSrc() == null ? orderSrc : order
					.getOrderSrc();
			prmtCodeList = this.systemSettingService.getPrmtCdListBySourceId(
					defaultSourceKey, this.sessOrderNo, order.getCustNo(),
					"order");
			if (order.getOrderPromotion() != null
					&& !prmtCodeList.contains(order.getOrderPromotion()
							.getPrmtCode())) {
				prmtCodeList.add(order.getOrderPromotion().getPrmtCode());
			}
			if (prmtCodeList != null && !prmtCodeList.isEmpty()) {
				for (String prmtCode : prmtCodeList) {
					DropDownDTO dto = new DropDownDTO();
					dto.setName(prmtCode);
					dto.setValue(prmtCode);
					prmtList.add(dto);
				}
			}
		}
		DropDownListDTO dropDownListDTO2 = new DropDownListDTO();
		dropDownListDTO2.setName(SpecDropDownListName.PROMOTION_CODE.value());
		dropDownListDTO2.setDropDownDTOs(prmtList);
		specDropDownList.put(SpecDropDownListName.PROMOTION_CODE,
				dropDownListDTO2);
		// end
		// mod by lizhang couponcode select list
		List<DropDownDTO> couponList = new ArrayList<DropDownDTO>();
		String[] couponCodeArray = StringUtils
				.isNotBlank(order.getCouponCode()) ? order.getCouponCode()
				.split(",") : new String[] {};
		String[] couponIdArray = StringUtils.isNotBlank(order.getCouponId()) ? order
				.getCouponId().split(",") : new String[] {};
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
		specDropDownList
				.put(SpecDropDownListName.COUPON_CODE, dropDownListDTO3);

		// Order priority drop down list的数据源
		this.priorityList = this.publicService
				.getDropdownList("ORDER_PRIORITY");
		if (StringUtils.isEmpty(order.getPriority())) {
			order.setPriority("Medium");
		}

		String refOrderNo = order.getRefOrderNo();
		if (!StringUtils.isBlank(refOrderNo)) {
			this.orderRefList = Arrays.asList(refOrderNo.split(","));
		} else {
			this.orderRefList = new ArrayList<String>();
		}
		String refQuoteNo = order.getSrcQuoteNo();
		if (!StringUtils.isBlank(refQuoteNo)) {
			this.quoteRefList = Arrays.asList(refQuoteNo.split(","));
		} else {
			this.quoteRefList = new ArrayList<String>();
		}
		this.warehouseList = this.inventoryService.getAllWarehouse();

		projectManagerList = orderService.getProManagerList(order);
		altProjectManagerList = orderService.getAltProManagerList();

		return "order_information";
	}

	/**
	 * 判断手动输入的Promotion Code 是否存在
	 * 
	 * @return
	 */
	public String isPromotionExist() {
		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), this.sessOrderNo);
		String retInfo = null;
		Integer prmtCount = null;
		// 显示或修改已存在的order时
		if (StringUtils.isNumeric(this.sessOrderNo)) {
			prmtCount = publicService.isPromotionExist(prmtCode,
					order.getOrderDate());
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

	/**
	 * 根据请求的sessOrderNo获得Order status history, 当该Order尚未保存即sessOrderNo为非数字时，无结果.
	 * 
	 * @return
	 */
	public String getOrderStatusHistory() {
		if (StringUtils.isNumeric(this.sessOrderNo)) {
			this.orderStatusList = orderService.getOrderStatusHist(Integer
					.valueOf(this.sessOrderNo));
		}
		return "order_status_history";
	}

	/**
	 * customer case edit 页面的 order 查看页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		try {
			order = orderService.getOrderDetail(Integer.parseInt(sessOrderNo));
		} catch (Exception e) {
			Struts2Util.renderText("The order " + sessOrderNo + " does exist.");
			return null;
		}
		return "order_view";
	}

	/**
	 * 判断输入的order是否存在
	 */
	public String isExistOrder() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			OrderMain order = this.orderService.getOrder(orderNo);
			if (order == null) {
				rt.put("message", "error");
				Struts2Util.renderJson(rt);
				return null;
			}
			rt.put("message", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 更新order的状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateStatus() throws Exception {
		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		order.setStatus(status);
		order.setStatusText(statusText);
		if ("CC".equalsIgnoreCase(status)) {
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
					SessionConstant.OrderItemList.value(), sessOrderNo);
			if (itemMap != null) {
				Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
						.iterator();
				while (it.hasNext()) {
					Entry<String, OrderItemDTO> entry = it.next();
					OrderItemDTO tmpDTO = entry.getValue();
					if (!tmpDTO.getStatus().equalsIgnoreCase("CN")) {
						tmpDTO.setStatus(status);
						tmpDTO.setStatusText(statusText);
					}
				}
			}
		}
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	/**
	 * 用于confirm之前的数据检查
	 * 
	 * @return
	 */
	public String checkConfirm() {
		if (!StringUtils.isNumeric(sessOrderNo)) {
			Struts2Util.renderText("The order are not available to be confirmed.");
			return null;
		}
		order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		if (order.getOrderCurrency() == null || "".equals(order.getOrderCurrency())) {
			Struts2Util.renderText("The order currency is null, couldn't comfirm.");
			return null;
		}
		// }else if (!order.getOrderCurrency().equals(order.getBaseCurrency()))
		// {
		// Struts2Util.renderText("The order base currency '" +
		// order.getBaseCurrency() + "' is not same as order currency '" +
		// order.getOrderCurrency() + ", so can't to be confirmed.");
		// return null;
		// }
		String oldStatus = order.getStatus();
		if (status.equalsIgnoreCase("CC") && !oldStatus.equalsIgnoreCase("NW") && !oldStatus.equalsIgnoreCase("RV")) {
			Struts2Util.renderText("The order are not available to be confirmed.");
			return null;
		}
		if (order.getWarehouseId() == null) {
			Struts2Util.renderText("Order's WarehouseId is null, couldn't comfirm.");
			return null;
		}
		if (internalOrderFlag != 1) {
			if (orderService.isPoCCexixt(Integer.parseInt(sessOrderNo)).booleanValue() == false) {
				Struts2Util.renderText("Please add the payment method!");
				return null;
			}
			// 判断customer-confirm email是否发送
			if (orderService.isCustMailSent(Integer.parseInt(sessOrderNo),
					Constants.CUST_CONFIRM_EMAIL).booleanValue() == false) {
				Struts2Util.renderText("The customer-confirm email has not been sent yet.");
				return null;
			}
		}
		// 判断Add Order Change Notification邮件是否发送
		if (orderService.isCustMailSent(Integer.parseInt(sessOrderNo),
				Constants.ORDER_CHANGE_NOTIFICATION).booleanValue() == false) {
			Struts2Util.renderText("The 'Add Order Change Notification' email has not been sent yet.");
			return null;
		}
		// order total < creit limit
		BigDecimal orderTotal = quoteOrderService.getTotal(order.getAmount(),
				order.getDiscount(), order.getTax(), order.getShipAmt(), order.getOrderCurrency());
		if (!"USD".equalsIgnoreCase(order.getOrderCurrency())) {
			orderTotal = quoteOrderService.changeTotalCurrency(orderTotal, order.getOrderCurrency(), "USD");
		}
		Double creditLimit = customerService.getCreditLimit(order.getCustNo(), order.getOrderCurrency());
		if (creditLimit == null || orderTotal.compareTo(new BigDecimal(creditLimit)) == 1) {
			Struts2Util.renderText("The customer's credit limit is less than order total.");
			return null;
		}
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	/**
	 * Customer Confirm Vendor Confirm
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String confirm() {
		try {
			// 数据检查
            String update_reason_sel = ServletActionContext.getRequest().getParameter("update_reason_sel");String item_update_reason = ServletActionContext.getRequest().getParameter("item_update_reason");
            String order_update_reason = ServletActionContext.getRequest().getParameter("order_update_reason");
			order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			Map<String, Integer> stockMap = new HashMap<String, Integer>();
			Map<String, List<OrderItemDTO>> productMap = new HashMap<String, List<OrderItemDTO>>();
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				String tmpStatus = orderItemDTO.getStatus();
				if (tmpStatus.equalsIgnoreCase("CN")) {
					continue;
				}
				if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(orderItemDTO.getType())) {
					if (orderItemDTO.getShipSchedule() == null) {
						Struts2Util.renderText("The OrderItem:" + orderItemDTO.getItemNo()
										+ " Scheduled Delivery is null, please enter it in 'Item Detail' tab.");
						return null;
					}
					Date now = new Date();
					Integer orderShipSch = orderItemDTO.getShipSchedule();
					if (orderShipSch == null) {
						orderItemDTO.setTargetDate(now);
					} else {
						orderItemDTO.setTargetDate(DateUtils.dayBefore2Date(orderShipSch));
					}
				}
				
				orderItemDTO.setStatus(status);
				orderItemDTO.setStatusText(statusText);
				orderItemDTO.setUpdateFlag("Y");
				orderService.setItemMoreDetailByClsId(orderItemDTO);
				if (!CatalogType.PRODUCT.value().equals(orderItemDTO.getType())) {
					continue;
				}
				List<OrderItemDTO> prdList = productMap.get(orderItemDTO.getCatalogNo());
				if (prdList == null) {
					prdList = new ArrayList<OrderItemDTO>();
				}
				prdList.add(orderItemDTO);
				productMap.put(orderItemDTO.getCatalogNo(), prdList);
				String catlogNoWareId = orderItemDTO.getCatalogNo() + "," + order.getWarehouseId();
				if (stockMap.containsKey(catlogNoWareId)) {
					Integer countQty = orderItemDTO.getQuantity() + stockMap.get(catlogNoWareId);
					stockMap.put(catlogNoWareId, countQty);
				} else {
					stockMap.put(catlogNoWareId, orderItemDTO.getQuantity());
				}
			}
			if (!stockMap.isEmpty()) {
				String customerCompany = customerService.getCustomerCompany(order.getCustNo(), null);
				for (String key : stockMap.keySet()) {
					try {
						if (!orderService.checkOrderItemProductStock(key, stockMap.get(key), customerCompany)) {
							if (productMap.containsKey(key.split(",")[0])) {
								List<OrderItemDTO> oilist = productMap.get(key.split(",")[0]);
								for (OrderItemDTO oi : oilist) {
									oi.setStatus(OrderItemStatusType.BO.value());
								}
							}
						}
					} catch (Exception be) {
						Struts2Util.renderText("Confirm failure,get the CatalogNo:"
								+ key.split(",")[0] + " inventory information failure.");
						return null;
					}
				}
			}
            //添加reopen历史记录
            if(OrderStatusType.RV.name().equals(order.getStatus())){
                OrderProcessLog orderProcessLog = new OrderProcessLog();
                orderProcessLog.setOrderNo(order.getOrderNo());
                orderProcessLog.setPriorStat(OrderStatusType.RV.name());
                orderProcessLog.setCurrentStat(OrderStatusType.CC.name());
                orderProcessLog.setReason(update_reason_sel);
                orderProcessLog.setNote("item:" + item_update_reason + ";order:"+order_update_reason);
                orderProcessLog.setProcessDate(new Date());
                order.setOrderProcessLog(orderProcessLog);
            }
			order.setStatus(status);
			order.setStatusText(statusText);
			Struts2Util.renderText("SUCCESS");
		} catch (Exception ex) {
			Struts2Util.renderText("Confirm failure,Please contact system administrator for help.");
			ex.printStackTrace();
		}
		return null;
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
		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		List<String> list = systemSettingService.getPrmtCdListBySourceId(
				sourceId, this.sessOrderNo, order.getCustNo(), "order");
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
			if (orderPrintDTO != null) {
				String email = orderPrintDTO.getEmail();
				String department = orderPrintDTO.getDepartment();
				String institution = orderPrintDTO.getInstitution();
				String state = orderPrintDTO.getState();
				String country = orderPrintDTO.getCountry();
				String comments = orderPrintDTO.getComments();
				String companyName = orderPrintDTO.getCompanyName();
				String address1 = orderPrintDTO.getAddress1();
				String address2 = orderPrintDTO.getAddress2();
				String telephone = orderPrintDTO.getTelephone();
				String fax = orderPrintDTO.getFax();
				String custEmail = orderPrintDTO.getCustEmail();
				String web = orderPrintDTO.getWeb();
				orderPrintDTO = this.orderService.getOrderPrintDTO(orderNo);
				orderPrintDTO.setEmail(email);
				orderPrintDTO.setDepartment(department);
				orderPrintDTO.setInstitution(institution);
				orderPrintDTO.setState(state);
				orderPrintDTO.setCountry(country);
				orderPrintDTO.setComments(comments);
				orderPrintDTO.setCompanyName(companyName);
				orderPrintDTO.setAddress1(address1);
				orderPrintDTO.setAddress2(address2);
				orderPrintDTO.setCustEmail(custEmail);
				orderPrintDTO.setWeb(web);
				orderPrintDTO.setFax(fax);
				orderPrintDTO.setTelephone(telephone);
			} else {
				orderPrintDTO = this.orderService.getOrderPrintDTO(orderNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderPrint";
	}

	private HashMap<Integer, HashMap<Integer, List<ManuDocument>>> itemDocumentsMap;
	private HashMap<Integer, List<ManuDocument>> itemDocumentsMap2;

	public String getOrderItemsDocuments() {
		String sessOrderNos = ServletActionContext.getRequest().getParameter(
				"sessOrderNo");
		if (sessOrderNos != null && !"".equals(sessOrderNos)) {
			itemDocumentsMap2 = this.orderService
					.getAllItemsDocumentsByorderNo2(Integer
							.parseInt(sessOrderNos));
		}

		return "OrderItemsDocuments";
	}

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String refType;
	private String delFilePath;
	private String filePath;
	private String fileName;
	private String sessId;

	public String getSessId() {
		return sessId;
	}

	public void setSessId(String sessId) {
		this.sessId = sessId;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	/**
	 * 上传附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String uploadLicensFile() throws Exception { 
		Map<String, Document> sessMap = (LinkedHashMap<String, Document>) SessionUtil
				.getRow(SessionConstant.OrderLicenceDocument.value(),
						this.sessOrderNo); 
		Map<String, Document> uploadMap = new LinkedHashMap<String, Document>();

		String docType = Struts2Util.getParameter("docType");
		// 目前页面上只能一次上传一个文件.
		for (int i = 0; i < upload.size(); i++) {
			String srcFileName = uploadFileName.get(i);
			Document doc = new Document();
			doc.setDocName(srcFileName);
			uploadFileName.set(i, SessionUtil.generateTempId() + "_"
					+ srcFileName);
			doc.setFilePath(FilePathConstant.Order_licenceDocument.value()
					+ "/" + uploadFileName.get(i));
			doc.setDocType(docType);
			doc.setRefType(SessionConstant.OrderLicenceDocument.value());
			doc.setRefId(Integer.parseInt(this.sessOrderNo));
			doc.setCreatedBy(SessionUtil.getUserId());
			doc.setModifyName(SessionUtil.getUserName());
			doc.setCreationDate(new Date());
			doc.setDescription("new Licence Documents for " + this.sessOrderNo
					+ ";");
			String tempId = SessionUtil.generateTempId();
			sessMap.put(tempId, doc);
			uploadMap.put(tempId, doc);
		}
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				FilePathConstant.Order_licenceDocument.value());
		System.out.println("uploadMap : " + uploadMap);
		System.out.println("sessMap size : " + sessMap.size());
		String html = Struts2Util.conventJavaObjToJson(uploadMap);
		Struts2Util.renderHtml(html);
		return null;
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteFile() throws Exception {

		Map<String, Document> sessMap = (Map<String, Document>) SessionUtil
				.getRow(SessionConstant.OrderLicenceDocument.value(),
						this.sessOrderNo);
		System.out.println(sessId);
		if (sessMap != null && sessMap.size()>0) {
			// 操作Session内容删除相应记录， 对于数据库中的则置其value为null.
			String docIds = Struts2Util.getParameter("docIds");
			String[] docIdArray = docIds.split(",");
			for (String key : docIdArray) {
				if (StringUtils.isNumeric(key)) {
					sessMap.put(key, null);
				} else {
					sessMap.remove(key);
				}
			}
			// 返回信号交由js执行reload页面
			Struts2Util.renderText("Success");
			return null;
		} else {
			Struts2Util.renderText("Error");
			return null;
		}

	}

	/**
	 * 保存WorkOrder时级联保存Document.
	 */
	@SuppressWarnings({ "unchecked" })
	private void attachDocument() {
		List<Document> roList = new ArrayList<Document>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, Document> sessMap = (Map<String, Document>) SessionUtil
				.getRow(SessionConstant.OrderLicenceDocument.value(),
						this.sessOrderNo);
		if (sessMap != null) {
			for (Entry<String, Document> entry : sessMap.entrySet()) {
				Document doc = entry.getValue();
				// 本例中对session中的数据只有新增和删除操作
				if (StringUtils.isNumeric(entry.getKey())) {// 数据库已有的
					if (doc == null) {// 执行了临时的删除操作
						delIdList.add(Integer.parseInt(entry.getKey()));
					} else {
						// 修改的不需要作任何操作.
					}
				} else {
					doc.setDocId(null);
					roList.add(doc);// 新增的
				}
			}
		}
		this.order.setDelDocumentsList(delIdList);
		this.order.setDocumentList(roList);
		System.out.println("roList: " + roList);
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDelFilePath() {
		return delFilePath;
	}

	public void setDelFilePath(String delFilePath) {
		this.delFilePath = delFilePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public HashMap<Integer, List<ManuDocument>> getItemDocumentsMap2() {
		return itemDocumentsMap2;
	}

	public void setItemDocumentsMap2(
			HashMap<Integer, List<ManuDocument>> itemDocumentsMap2) {
		this.itemDocumentsMap2 = itemDocumentsMap2;
	}

	public HashMap<Integer, HashMap<Integer, List<ManuDocument>>> getItemDocumentsMap() {
		return itemDocumentsMap;
	}

	public void setItemDocumentsMap(
			HashMap<Integer, HashMap<Integer, List<ManuDocument>>> itemDocumentsMap) {
		this.itemDocumentsMap = itemDocumentsMap;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public OrderMainDTO getOrder() {
		return order;
	}

	public void setOrder(OrderMainDTO order) {
		this.order = order;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public List<ProcessLogDTO> getOrderStatusList() {
		return orderStatusList;
	}

	public void setOrderStatusList(List<ProcessLogDTO> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}

	public List<PbDropdownListOptions> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(List<PbDropdownListOptions> priorityList) {
		this.priorityList = priorityList;
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

	public Integer[] getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(Integer[] orderNos) {
		this.orderNos = orderNos;
	}

	public void setItemMap(Map<String, OrderItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public Map<String, OrderItemDTO> getItemMap() {
		return itemMap;
	}

	public List<String> getOrderRefList() {
		return orderRefList;
	}

	public void setOrderRefList(List<String> orderRefList) {
		this.orderRefList = orderRefList;
	}

	public List<String> getQuoteRefList() {
		return quoteRefList;
	}

	public void setQuoteRefList(List<String> quoteRefList) {
		this.quoteRefList = quoteRefList;
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

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
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

	public Integer getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(Integer defaultTab) {
		this.defaultTab = defaultTab;
	}

	public String getPrmtCode() {
		return prmtCode;
	}

	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}

	public int getPurchaseOrderFlag() {
		return purchaseOrderFlag;
	}

	public void setPurchaseOrderFlag(int purchaseOrderFlag) {
		this.purchaseOrderFlag = purchaseOrderFlag;
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

	public int getSalesOrderFlag() {
		return salesOrderFlag;
	}

	public void setSalesOrderFlag(int salesOrderFlag) {
		this.salesOrderFlag = salesOrderFlag;
	}

	public String getShippingRule() {
		return shippingRule;
	}

	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}

	public void setProjectSupport(Integer projectSupport) {
		this.projectSupport = projectSupport;
	}

	public void setProjectSupportUser(String projectSupportUser) {
		this.projectSupportUser = projectSupportUser;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getOverrideFlag() {
		return overrideFlag;
	}

	public void setOverrideFlag(String overrideFlag) {
		this.overrideFlag = overrideFlag;
	}

	public String getTmplName() {
		return tmplName;
	}

	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}

	public Integer getMaxMyTemplateCount() {
		return maxMyTemplateCount;
	}

	public void setMaxMyTemplateCount(Integer maxMyTemplateCount) {
		this.maxMyTemplateCount = maxMyTemplateCount;
	}

	public String getIsSalesManager() {
		return isSalesManager;
	}

	public void setIsSalesManager(String isSalesManager) {
		this.isSalesManager = isSalesManager;
	}

	public String getShippingAccount() {
		return shippingAccount;
	}

	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
	}

	public Integer getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Integer purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public OrderPrintDTO getOrderPrintDTO() {
		return orderPrintDTO;
	}

	public void setOrderPrintDTO(OrderPrintDTO orderPrintDTO) {
		this.orderPrintDTO = orderPrintDTO;
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

	public Customer getSearchCust() {
		return searchCust;
	}

	public void setSearchCust(Customer searchCust) {
		this.searchCust = searchCust;
	}

	public QuoteMain getSearchQuote() {
		return searchQuote;
	}

	public void setSearchQuote(QuoteMain searchQuote) {
		this.searchQuote = searchQuote;
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

	public String getBillAccCode() {
		return billAccCode;
	}

	public void setBillAccCode(String billAccCode) {
		this.billAccCode = billAccCode;
	}

	public String getEditFlg() {
		return editFlg;
	}

	public void setEditFlg(String editFlg) {
		this.editFlg = editFlg;
	}

	public int getInternalOrderFlag() {
		return internalOrderFlag;
	}

	public void setInternalOrderFlag(int internalOrderFlag) {
		this.internalOrderFlag = internalOrderFlag;
	}

	public Integer getLookFromWoFlag() {
		return lookFromWoFlag;
	}

	public void setLookFromWoFlag(Integer lookFromWoFlag) {
		this.lookFromWoFlag = lookFromWoFlag;
	}

    public List<PbDropdownListOptions> getUpdateReasonSel() {
        return updateReasonSel;
    }

    public void setUpdateReasonSel(List<PbDropdownListOptions> updateReasonSel) {
        this.updateReasonSel = updateReasonSel;
    }

	public int getShippedFlag() {
		return shippedFlag;
	}

	public void setShippedFlag(int shippedFlag) {
		this.shippedFlag = shippedFlag;
	}
}
