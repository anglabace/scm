package com.genscript.gsscm.shipment.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dom4j.DocumentException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fedex.ship.stub.DropoffType;
import com.fedex.ship.stub.PackagingType;
import com.fedex.ship.stub.ServiceType;
import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.HostIpUtil;
import com.genscript.gsscm.common.MyX509TrustManager;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.FilelUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.ShipWebServiceClient;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.zip.FileUtil;
import com.genscript.gsscm.common.zip.ZipUtil;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.epicorwebservice.service.GetInvoicePaymentListJob;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.ArrayOfInventoryQtyAdjItem;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.InventoryQtyAdjItem;
import com.genscript.gsscm.epicorwebservice.stub.invoiceService.PartItem;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.inventory.service.StorageService;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.PaymentVoucherDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.purchase.dao.PoReceiveingTmpDao;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.service.PurchaseOrderService;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.shipment.dao.ShippingChargeLogDao;
import com.genscript.gsscm.shipment.dto.BankCardDTO;
import com.genscript.gsscm.shipment.dto.LotNoDTO;
import com.genscript.gsscm.shipment.dto.PrintPickListDTO;
import com.genscript.gsscm.shipment.dto.PrintPickListsDTO;
import com.genscript.gsscm.shipment.dto.ShipDocDTO;
import com.genscript.gsscm.shipment.dto.ShipMethodDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsSrchDTO;
import com.genscript.gsscm.shipment.dto.ShippingPackageLinesDTO;
import com.genscript.gsscm.shipment.dto.StorageLocationDTO;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShippingChargeLog;
import com.genscript.gsscm.shipment.service.ShipMethodService;
import com.genscript.gsscm.shipment.service.ShipPackageLineService;
import com.genscript.gsscm.shipment.service.ShipPackageService;
import com.genscript.gsscm.shipment.service.ShipmentLinesService;
import com.genscript.gsscm.shipment.service.ShipmentsService;
import com.genscript.gsscm.shipment.service.ShippingService;
import com.genscript.gsscm.systemsetting.service.ShipClerkService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "shipping_frame", location = "shipping/shipping_frame.jsp"),
		@Result(name = "shippingList", location = "shipping/shipping_list.jsp"),
		@Result(name = "shipping_search", location = "shipping/shipping_search.jsp"),
		@Result(name = "packingDetail", location = "shipping/packing_detail.jsp"),
		@Result(name = "addnewItems", location = "shipping/add_new_items.jsp"),
		@Result(name = "addnewItems1", location = "shipping/add_new_items_1.jsp"),
		@Result(name = "toViewShipment", location = "shipping/view_shipment.jsp"),
		@Result(name = "getShipInfo", location = "shipping/shipping_conf_detail.jsp"),
		@Result(name = "appFrame", location = "shipping/shipping_frame.jsp"),
		@Result(name = "print_shipping_label", location = "shipping/print_shipping_label.jsp"),
		@Result(name = "print_shipping_label_trackingNo", location = "shipping/print_shipping_label_trackingNo.jsp"),
		@Result(name = "charge_credit_card", location = "shipping/charge_credit_card.jsp"),
		@Result(name = "print_shipping_label_enter_trackingNo", location = "shipping/print_shipping_label_enter_trackingNo.jsp"),
		@Result(name = "package_information", location = "shipping/package_information.jsp"),
		@Result(name = "printPickList", location = "shipping/Print_Pick_List.jsp"),
		@Result(name = "printPickListPdf", location = "shipping/print_pick_list_pdf.jsp"),
		@Result(name = "viewPackingSlipPdf", location = "shipping/print_pick_list_pdf.jsp"),
		@Result(name = "viewPackingSlip", location = "shipping/view_packing_slip.jsp"),
		@Result(name = "cancelShipping", location = "shipping/cancel_shipping.jsp"),
		@Result(name = "str", location = "shipping/showResult.jsp"),
		@Result(name = "send_customer_email", location = "shipping/send_customer_email.jsp"),
		@Result(name = "ship", location = "shipping/packing_detail.jsp"),
		@Result(name = "search_ship_packages", location = "shipping/search_ship_packages.jsp"),
		@Result(name = "shippingDoc", location = "shipping/view_shipping_doc.jsp"),
        @Result(name = "view_order_note", location = "order/view_order_note.jsp"),
		@Result(name = "picksItems", location = "shipping/picks_items.jsp"),
		@Result(name = "edit_address", location = "shipping/edit_address.jsp"),
		@Result(name = "search_shipping_rule", location = "shipping/search_shipping_rule.jsp")
		
		
		})
public class ShippingAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private ShipPackageService shipPackageService;
	@Autowired
	private ShipPackageLineService shipPackageLineService;
	@Autowired
	private ShipMethodService shipMethodService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ShipWebServiceClient shipWebServiceClient;
	@Autowired
	private ShipmentLinesService shipmentLineService;
	@Autowired
	private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
	@Autowired
	private ShipmentsService shipmentService;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private GetInvoicePaymentListJob getInvoicePaymentListJob;
	@Autowired
	private OrderDao orderDao;
    @Autowired
	private PublicService publicService;
    @Autowired
	private OrderAddressDao orderAddressDao;
    @Autowired
	private PoReceiveingTmpDao poReceiveingTmpDao;
	@Autowired
	private ShipClerkService shipClerkService;
	@Autowired
	private FileService fileService;
	@Autowired
	private ShippingChargeLogDao shippingChargeLogDao;
	@Autowired
	private PaymentVoucherDao paymentVoucherDao;
	@Autowired
	private PbCountryDao pbCountryDao;
    private InstructionDTO orderNote;
	
    @Autowired
    private StorageService storageService;
	private ZipUtil ziputil = new ZipUtil() ;
	private FileUtil fu= new FileUtil() ;
	private Page<ShipmentsDTO> pageShipmentsDTO;
	private ShipmentsDTO sDto;
	public ShipmentsSrchDTO shipschDTO;
	private Page<ShipPackageDTO> pagePackagesDTO;
	private ShipPackageDTO pDto;
	private ShipmentsDTO srch;
	private ShipPackage sips;
	
	// 取消trackingNo
	private String[] trakingNo;//订单号
	private String returnStr = "";//返回信息
	@Autowired
	private CustomerService customerService;
	private HostIpUtil hostip = new HostIpUtil();
	private String customerBusEmail;
	private String emailSubject;
	private String emailContent;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private boolean sendStatus;
	private List<String> customerBusEmailList;
	private List<ShipPackage> printShipPackageList;
	private List<ShipPackageDTO> printShipPackageDTOList;
	private String createCommercialInvoice;
	private Integer shipmentId;
	private Integer count;
	private Integer counts;
	private List<ShipMethod> shipMenthodList;
	private ShipPackage sps;
	private String shippingLable;
    private HashMap<String, Object> notesMap;
	
    
    private String notReceivedItem;
    private String expiredItem;
	
	
	private List<ManuDocument> shippingDocList;
	private List<ShipDocDTO> shippingProductDocList;
	private String isPrint;
	private String isShippingProductDoc;
	
	private List<PbCountry> countryList;
	
	private String orderItemId;

	public ShipPackage getSips() {
		return sips;
	}

	public void setSips(ShipPackage sips) {
		this.sips = sips;
	}

	public ShipmentsDTO getSrch() {
		return srch;
	}

	public void setSrch(ShipmentsDTO srch) {
		this.srch = srch;
	}

	public String cancelDoNewItem() {
		return "packingDetail";
	}

	/**
	 * 此方法返回shippments_Frame框架
	 * 
	 * @return String
	 * @author JLiu
	 */
	public String appFrame() {
		Struts2Util.getSession().removeAttribute("orderNos");
		Struts2Util.getSession().removeAttribute("Picklist");
		return "appFrame";
	}

	/**
	 * 初始化并分页查询中国与美国仓库列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String shippingList() throws Exception {
		Integer userId = SessionUtil.getUserId();
		try {
			if (shipschDTO == null)
				shipschDTO = new ShipmentsSrchDTO();
			String type = (String) Struts2Util.getParameter("warehouseType");
			if (type == null || type.trim().length() == 0)
				type = (String) Struts2Util.getSession().getAttribute(
						"warehouseType");
			@SuppressWarnings("unused")
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());

			if (type == null || type.equals("") || "SALES".equals(type)) {
				// 实例化一个pageUtil对象
				PagerUtil<ShipmentsDTO> pagerUtil = new PagerUtil<ShipmentsDTO>();
				pageShipmentsDTO = pagerUtil.getRequestPage();
				pageShipmentsDTO.setPageSize(10);
				if (!pageShipmentsDTO.isOrderBySetted()) {
					pageShipmentsDTO.setOrderBy("modifyDate");
					pageShipmentsDTO.setOrder(Page.DESC);
				}
				Page<Shipment> shipmentPage = this.dozer.map(pageShipmentsDTO,
						Page.class);
				ShipmentsDTO srch = new ShipmentsDTO();
				srch.setOrderNo(shipschDTO.getOrderNo());
				srch.setStatus(shipschDTO.getStatus());
				srch.setPriority(shipschDTO.getPriority());
				srch.setOrderType(shipschDTO.getType());
				srch.setShippingRule(shipschDTO.getShippingRule());
				srch.setShippingClerk(shipschDTO.getShippingClerk());
				srch.setPoNo(shipschDTO.getPoNo());
				User user = this.userDao.getById(userId);
				boolean isProductionManagerRole = false;
				if (!Constants.USERNAME_ADMIN.equals(user.getLoginName())) {
		            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_SHIPPING_MANAGER);
		        }else{
		        	isProductionManagerRole = true;
		        }
				if(isProductionManagerRole){
					if(srch.getShippingClerk()==null&&!Constants.USERNAME_ADMIN.equals(user.getLoginName())){
						srch.setShippingClerk(userId.toString());
					}
				}else{
					srch.setShippingClerk(userId.toString());
				}
				pageShipmentsDTO = this.shippingService.getShipmentList(
						shipmentPage, srch);
				Struts2Util.getRequest().setAttribute("pagerInfo",
						pageShipmentsDTO);
				Struts2Util.getSession().setAttribute("warehouseType", "SALES");
			} else if ("MANUFACTURING".equals(type)) {
				// 实例化一个pageUtil对象
				PagerUtil<ShipPackageDTO> pagerUtil = new PagerUtil<ShipPackageDTO>();
				pagePackagesDTO = pagerUtil.getRequestPage();
				pagePackagesDTO.setPageSize(10);

				Page<ShipPackage> packagePage = this.dozer.map(
						pagePackagesDTO, Page.class);
				ShipPackageDTO srch = new ShipPackageDTO();
				if (shipschDTO.getOrderNo() != null
						&& shipschDTO.getOrderNo().trim().length() > 0)
					srch.setOrderNo(Integer.parseInt(shipschDTO.getOrderNo()));
				srch.setStatus(shipschDTO.getStatus());
				srch.setPriority(shipschDTO.getPriority());
				srch.setOrderType(shipschDTO.getType());
				srch.setShippingClerk(shipschDTO.getShippingClerk());
				pagePackagesDTO = this.shippingService.getShippackagesList(
						packagePage, srch);

				Struts2Util.getRequest().setAttribute("pagerInfo",
						pagePackagesDTO);
				Struts2Util.getSession().setAttribute("warehouseType",
						"MANUFACTURING");
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "shippingList";
	}

	/**
	 * 根据输入的orderNo查询OrderItem中已经接受的相关记录
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String searchItems() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			String orderNo = Struts2Util.getRequest().getParameter("orderNo");
			if (orderNo == null || orderNo.equals("")) {
				// 如果是不是第一次进入，orderNo为空，则从session里取得orderNo
				orderNo = (String) Struts2Util.getSession().getAttribute(
						"orderNo");
			}
			if (orderNo != null && !orderNo.equals("")) {
				PagerUtil<OrderItem> pagerUtil = new PagerUtil<OrderItem>();
				Page<OrderItem> pages = pagerUtil.getRequestPage();
				pages.setPageSize(3);
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<OrderItem> retPage = this.dozer.map(pages, Page.class);
				if (filters == null || filters.isEmpty()) {
					pages = this.shippingService.getOrderItemsByOrderNo(
							retPage, orderNo);
				}
				Struts2Util.getSession().setAttribute("pagerInfo", pages);
				List list = pages.getResult();
				if (list == null || list.size() == 0) {// 如果查出空值，则提示没有对应的ItemNo
					out
							.print("<script>alert('This order number do not include any item numbers\\nthat had received');</script>");
					out
							.print("<script>location.href='shipping!toAddNewItems.action'</script>");
					return null;
				}
				Struts2Util.getSession().setAttribute("list", list);
				// 如果session里没有orderNo，进入更新相对的父页面的地址，否则只需当前页的地址改变
				if ((String) Struts2Util.getSession().getAttribute("orderNo") != orderNo) {
					Struts2Util.getSession().setAttribute("orderNo", orderNo);
					out
							.print("<script>parent.location.href='shipping!toAddNewItems1.action';</script>");
					return null;
				}
				Struts2Util.getSession().setAttribute("orderNo", orderNo);
				out
						.print("<script>location.href='shipping!toAddNewItems1.action';</script>");

			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}

	/**
	 * 保存shipping的数据
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveShipping() throws Exception {
		try {
			String message = "";
			List<ShipmentsDTO> list = (List<ShipmentsDTO>) Struts2Util.getSession().getAttribute("listTemp");
			List<ShipmentsDTO> listPackage = (List) Struts2Util.getSession().getAttribute("listPackage");
			System.out.println("size="+listPackage.size());
			String packageIds = (String) Struts2Util.getSession().getAttribute("packageTemp");
			List listOip = (List) Struts2Util.getSession().getAttribute("oipList");

			if (list == null && packageIds == null && listOip == null) {
				message = "No data needs to be saved!";
				Struts2Util.getRequest().setAttribute("message", message);
				return packingDetail();
			}

			Integer userId = SessionUtil.getUserId();
			String flag = this.shippingService.saveShipping(list, packageIds, listOip, userId,shipmentId,listPackage);
			
			System.out.println("<<<<<<<<<<<<<<<<<<<<<");
			if (flag.equals("1")) {
				message = "Data saved successfully!";
			} else {
				message = "Data saved failed!";
			}
			Struts2Util.getRequest().setAttribute("message", message);
			Struts2Util.getSession().removeAttribute("listOrderItem");
			Struts2Util.getSession().removeAttribute("listPackage");
			Struts2Util.getSession().removeAttribute("listTemp");
			Struts2Util.getSession().removeAttribute("packageTemp");
			Struts2Util.getSession().removeAttribute("oipList");
			Struts2Util.getSession().removeAttribute("count");
			Struts2Util.getSession().removeAttribute("tag");
			Struts2Util.getSession().removeAttribute("tagList");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(), "INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return packingDetail();

	}

	/**
	 * 准备数据，跳往View shipment Instrutction
	 * 
	 * @return
	 */
	/*public String toViewShipment(){
        try {
            String orderNos = (String) Struts2Util.getSession().getAttribute(
                    "orderNos");
            if (orderNos == null)
                orderNos = "";
            HashMap<String, Object> notesMap = this.shippingService.getOrderNotesByOrderNosS(orderNos);
            Struts2Util.getRequest().setAttribute("orderNotesList", notesMap);
        } catch (Exception ex) {
            WSException exDTO = exceptionUtil.getExceptionDetails(ex);
            exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(),
                    "INTF0203", SessionUtil.getUserId());
            ExceptionOut.printException(exDTO);
        }
		return "toViewShipment";
	}*/

	/**
	 * 重置
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			Struts2Util.getSession().removeAttribute("orderNos");
			Struts2Util.getSession().removeAttribute("tag");
			Struts2Util.getSession().removeAttribute("tagList");
			Struts2Util.getSession().removeAttribute("listOrderItem");
			Struts2Util.getSession().removeAttribute("listPackage");
			Struts2Util.getSession().removeAttribute("listTemp");
			Struts2Util.getSession().removeAttribute("packageTemp");
			Struts2Util.getSession().removeAttribute("oipList");
			Struts2Util.getSession().removeAttribute("count");
			Struts2Util.getSession().removeAttribute("Picklist");
			Struts2Util.getSession().removeAttribute("viewlist");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return appFrame();
	}
	
	/*
	 * list 页面获取detail数据
	 */
	public String searchShippingRuleDetail(){
		String orderNo = (String)Struts2Util.getParameter("orderNo");
		String notRecItem = (String)Struts2Util.getParameter("notRecItem");
		String expItem = (String)Struts2Util.getParameter("expItem");
		try {
			notesMap = this.shippingService.getOrderNotesByOrderNosS(orderNo);
			if(notRecItem.equals("1")){
				this.notReceivedItem = this.shippingService.getNotReceivedItem(orderNo);
			}else{
				this.notReceivedItem = "";
			}
			if(expItem.equals("1")){
				this.expiredItem = this.shippingService.getExpiredItem(orderNo);
			}else{
				this.expiredItem = "";
			}
			
		} catch (SQLException ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		
		return "search_shipping_rule";
	}

	/**
	 * 进入packingDetail页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String packingDetail() throws Exception {
		try {
			System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<");
			String chks = Struts2Util.getParameter("chks");
			if (chks == null)
				chks = (String) Struts2Util.getSession().getAttribute("orderNos");
			String orderNos = this.shipmentLineService.getOrderByShipmentId(shipmentId);
			if(orderNos!=""){
				chks=orderNos;
			}
			List<ShipmentsDTO> listOrderItem = this.shippingService.getOrderItemList(chks, true);
			List<ShipmentsDTO> listPackage = this.shippingService.getPackageList(chks);
			String statusPicked=null;
			String statusPacked=null;
			if(listPackage!=null&&!listPackage.isEmpty()){
				for(ShipmentsDTO dto:listPackage){
					String status = dto.getPackageStatus();
					if(status!=null&&(status.equals("Picked")||status.equals("Packed"))){
						statusPicked = "Picked";
					}
					if(status!=null&&status.equals("Packed")){
						statusPacked = "Packed";
					}
				}
			}else{
				statusPicked = null;
				statusPacked = null;
			}
			String orderNoGreen="";
			if (orderNos == null)
				orderNos = "";
			else
				orderNoGreen = (orderNos.split(","))[0];
			notesMap = this.shippingService.getOrderNotesByOrderNosS(orderNos);
            //放入session
            Struts2Util.getSession().setAttribute("noteMap", notesMap);
			if(!orderNos.equals("")){
				String greenAccFlag = this.shippingService.getGreenAccFlag(Integer.valueOf(orderNoGreen));
				Struts2Util.getRequest().setAttribute("greenAccFlag", greenAccFlag);
			}else{
				Struts2Util.getRequest().setAttribute("greenAccFlag", null);
			}
			Struts2Util.getSession().setAttribute("statusPicked", statusPicked);
			Struts2Util.getSession().setAttribute("statusPacked", statusPacked);
			Struts2Util.getSession().setAttribute("orderNos", chks);
			Struts2Util.getSession().setAttribute("listOrderItem",listOrderItem);
			Struts2Util.getSession().setAttribute("listPackage", listPackage);
			Struts2Util.getSession().removeAttribute("listTemp");
			Struts2Util.getSession().removeAttribute("packageTemp");
			Struts2Util.getSession().removeAttribute("oipList");
			Struts2Util.getSession().removeAttribute("count");
			Struts2Util.getSession().removeAttribute("Picklist");
			Struts2Util.getSession().removeAttribute("viewlist");
			Struts2Util.getSession().removeAttribute("tag");
			Struts2Util.getSession().removeAttribute("tagList");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "packingDetail";
	}

	/**
	 * 此方法返回搜索页
	 * 
	 * @return String
	 * @author JLiu
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String shipping_search() throws Exception {
		// 取shipments字段
		//List sList = this.shippingService.getListByClert();
		// 取仓库名称
		List wList = this.shippingService.getListByWareHouse();
		// 取人员名称
		Integer userId = SessionUtil.getUserId();
		User user = this.userDao.getById(userId);
		boolean isProductionManagerRole = false;
		if (!Constants.USERNAME_ADMIN.equals(user.getLoginName())) {
            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_SHIPPING_MANAGER);
        }else{
        	isProductionManagerRole = true;
        }
		if(isProductionManagerRole){
			userId = null;
		}
		Map<String, String> productAndServiceCls = shipClerkService.getProductAndService();
		List nList = this.shippingService.getLoginName(userId);
		// 取类型
		//List tList = this.shippingService.getTypeList();
		// 取优先级
		//List<PbDropdownListOptions> pList = this.shippingService.getPriorityList();
		Struts2Util.getRequest().setAttribute("userId", userId);
		Struts2Util.getRequest().setAttribute("isProductionManagerRole", isProductionManagerRole);
		//Struts2Util.getRequest().setAttribute("sList", sList);
		Struts2Util.getRequest().setAttribute("wList", wList);
		Struts2Util.getRequest().setAttribute("nList", nList);
		ServletActionContext.getRequest().setAttribute("allcls",
				productAndServiceCls);
		//Struts2Util.getRequest().setAttribute("tList", tList);
		//Struts2Util.getRequest().setAttribute("pList", pList);
		return "shipping_search";
	}

	/**
	 * 跳转到addNewItem页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddNewItems() throws Exception {
		return "addnewItems";
	}

	/**
	 * 进行Pack操作
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String pack() throws Exception {
		try {
			String type = (String) Struts2Util.getSession().getAttribute("warehouseType");
			String packAll = Struts2Util.getParameter("packAll");
			if ("SALES".equals(type) && "1".equals(packAll))
				return packAll();
			List<ShipmentsDTO> listOrderItem = (List) Struts2Util.getSession().getAttribute("listOrderItem");
			List<ShipmentsDTO> listPackage = (List) Struts2Util.getSession().getAttribute("listPackage");
			List<ShipmentsDTO> listOrderItem_ = new ArrayList<ShipmentsDTO>();
			listOrderItem_.addAll(listOrderItem);
			List<ShipmentsDTO> list = (List) Struts2Util.getSession().getAttribute("listTemp");
			List<ShipmentsDTO> listTemp = new ArrayList<ShipmentsDTO>();

			if (list == null)
				list = new ArrayList<ShipmentsDTO>();
			if(listPackage == null)
				listPackage = new ArrayList<ShipmentsDTO>();
			String chkRid = Struts2Util.getParameter("chkRid");
			String quantityText = Struts2Util.getParameter("quantity");
			String sizeText = Struts2Util.getParameter("size");
			String quantity = Struts2Util.getParameter("qtys");
			String size = Struts2Util.getParameter("sizes");
			String packageId = Struts2Util.getParameter("packageIds");

			if (chkRid == null || quantity == null || size == null || sizeText == null || quantityText == null
					|| chkRid.trim().length() == 0 || quantity.trim().length() == 0 || size.trim().length() == 0
					|| quantityText.trim().length() == 0 || sizeText.trim().length() == 0) {
				Struts2Util.getRequest().setAttribute("message", "Pack failed");
				return "packingDetail";
			}
			
			listPackage = this.getNewShipmentDto(listPackage);
			for(int i=0;i<listPackage.size();i++){
				ShipmentsDTO pdto = listPackage.get(i);
				if(!"Drafted".equals(pdto.getPackageStatus()) && pdto.getPackageId().equals(packageId)){
					Struts2Util.getRequest().setAttribute("message", "Please choose the Drafted package");
					return "packingDetail";
				}
			}

			String[] chkRids = chkRid.split(",");
			String[] quantitys = quantity.split(",");
			String[] sizes = size.split(",");
			List<Reservation> reList = this.shippingService.getReservationList(chkRid);
			for (int i = 0; i < chkRids.length; i++) {
				Reservation res = new Reservation();
				for(Reservation r : reList){
					if(chkRids[i].equals(r.getId().toString())){
						res = r;
					}
				}
				if (!"All".equals(quantityText) && res.getQty() < Integer.parseInt(quantityText)) {
					Struts2Util.getRequest().setAttribute("message", "Quantity too big");
					return "packingDetail";
				}
				if (!"All".equals(sizeText) && res.getSize() < Double.valueOf(sizeText)) {
					Struts2Util.getRequest().setAttribute("message", "Size too big");
					return "packingDetail";
				}
			}
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			
			for (int i = 0; i < chkRids.length; i++) {
				Reservation re = new Reservation();
				for(Reservation r : reList){
					if(chkRids[i].equals(r.getId().toString())){
						re = r;
					}
				}
				ShipmentsDTO dto = this.getShipmentTemp(packageId, quantityText, sizeText, chkRids[i], quantitys[i], sizes[i],orderItemList,re);

				ShipPackage packages = new ShipPackage();
				packages.setPackageId(Integer.parseInt(packageId));
				packages.setPackageNo(packageId);
				dto.setPackages(packages);
				System.out.println("dto.orderType="+dto.getOrderItemType());
				listTemp.add(dto);
				list.add(dto);
			}
			for (int i = 0; i < listOrderItem_.size(); i++) {
				ShipmentsDTO dto = (ShipmentsDTO) listOrderItem_.get(i);
				for (int j = 0; j < listTemp.size(); j++) {
					ShipmentsDTO dto_ = listTemp.get(j);
					this.changeOrderItem(dto, dto_, listOrderItem);
				}
			}
			List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();
			for (int i = 0; i < listPackage.size(); i++) {
				ShipmentsDTO dto = (ShipmentsDTO) listPackage.get(i);				
				List<ShipPackageLineDTO> listLine = dto.getListPackageLine();
				if (listLine == null || listLine.size() == 0) {
					List<ShipPackageLineDTO> listNewLine = new ArrayList<ShipPackageLineDTO>();
					for (int j = 0; j < listTemp.size(); j++) {
						ShipmentsDTO dto_ = listTemp.get(j);
						if (listNewLine.size() == 0) {
							ShipPackageLineDTO lineDto = this.getShipPackageLine(dto_,orderItemDTOList);
							listNewLine.add(lineDto);
						} else {
							boolean flag = true;
							for (int k = 0; k < listNewLine.size(); k++) {
								ShipPackageLineDTO ldto = listNewLine.get(k);
								if (ldto.getOrderNo() == Integer.parseInt(dto_.getOrderNo())&& ldto.getItemNo() == Integer.parseInt(dto_.getItemNo())) {
									this.getShipPackageLine(dto_, ldto);
									flag = false;
									break;
								}
							}
							if (flag == true) {
								ShipPackageLineDTO lineDto = this.getShipPackageLine(dto_,orderItemDTOList);
								listNewLine.add(lineDto);
							}
						}
					}
					if (Integer.parseInt(dto.getPackageId()) == Integer.parseInt(packageId))
						dto.setListPackageLine(listNewLine);
				} else {
					for (int k = 0; k < listTemp.size(); k++) {
						ShipmentsDTO dto_ = listTemp.get(k);
						boolean flag = true;
						for (int j = 0; listLine != null && j < listLine.size(); j++) {
							ShipPackageLineDTO ldto = (ShipPackageLineDTO) listLine.get(j);
							if (ldto.getOrderNo() == Integer.parseInt(dto_.getOrderNo())&& ldto.getItemNo() == Integer.parseInt((dto_.getItemNo()))
									&& ldto.getPackageId() == Integer.parseInt(dto_.getPackageId())) {
								this.getShipPackageLine(dto_, ldto);
								flag = false;
								break;
							}
						}
						if (flag == true) {
							if (Integer.parseInt(dto.getPackageId()) == Integer.parseInt(packageId)) {
								ShipPackageLineDTO lineDto = this.getShipPackageLine(dto_,orderItemDTOList);
								dto.getListPackageLine().add(lineDto);
							}
						}
					}
				}
			}
			this.shippingService.setPackage(listOrderItem);
			Struts2Util.getSession().setAttribute("listOrderItem", listOrderItem);
			this.shippingService.setPackage(listPackage);
			Struts2Util.getSession().setAttribute("listPackage", listPackage);
			this.shippingService.setPackage(list);
			Struts2Util.getSession().setAttribute("listTemp", list);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "packingDetail";
	}

	/**
	 * 进行PackAll操作
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String packAll() throws Exception {
		try {
			List<ShipmentsDTO> listOrderItem = new ArrayList<ShipmentsDTO>();
			
			List<ShipmentsDTO> listPackage = (List<ShipmentsDTO>)Struts2Util.getSession().getAttribute("listPackage");
			if(listPackage == null)
				listPackage = new ArrayList<ShipmentsDTO>();
			
			listPackage = this.getNewShipmentDto(listPackage);
			for(int i=0;i<listPackage.size();i++){
				if(!"Drafted".equals(listPackage.get(i).getPackageStatus())){
					Struts2Util.getRequest().setAttribute("message", "Can not PackAll the " + listPackage.get(i).getPackageStatus() + " records");
					return "packingDetail";
				}
			}
			listPackage = new ArrayList<ShipmentsDTO>();
			List<ShipmentsDTO> list = new ArrayList<ShipmentsDTO>();
			String orderNos = (String) Struts2Util.getSession().getAttribute("orderNos");
			List<PoReceivingTmp> tmpList = this.poReceiveingTmpDao.searcPoTmp(orderNos);
			if(tmpList!=null&&!tmpList.isEmpty()){
				Struts2Util.getRequest().setAttribute("message", "No matched orderPackage,Can not packAll.");
				return "packingDetail";
			}
			List<PurchaseOrder> purchaseOrderList = this.purchaseOrderService.findBySrcSoNos(orderNos);
			if(purchaseOrderList!=null&&!purchaseOrderList.isEmpty()){
				for(PurchaseOrder purchaseOrder:purchaseOrderList){
					if(purchaseOrder!=null&&(purchaseOrder.getReceivingFlag()==null||!purchaseOrder.getReceivingFlag().equals("1"))){
						Struts2Util.getRequest().setAttribute("message", "No matched orderPackages,Can not packAll.");
						return "packingDetail";
					}
				}
			}
			List<ShipmentsDTO> listOrderItemTemp = this.shippingService.getOrderItemList(orderNos, false);
			List<OrderPackage> oPackageList = this.shippingService.getOrderPackageByOrderNo(orderNos);
			if(oPackageList == null||oPackageList.isEmpty()){
				Struts2Util.getRequest().setAttribute("message", "No matched orderPackage,Can not packAll.");
				return "packingDetail";
			}
			List<ShipmentsDTO> listPackages = this.shippingService.getPackageList(orderNos);
			String packageIds = "";
			for (int i = 0; listPackages != null && i < listPackages.size(); i++) {
				ShipmentsDTO dto = listPackages.get(i);
				packageIds += dto.getPackageId() + ",";
			}
			if (packageIds.trim().length() > 1)
				packageIds = packageIds.substring(0, packageIds.length() - 1);
			int count = this.shippingService.getCount();
			ShipmentsDTO dto = new ShipmentsDTO();
			Integer k =0;
			for(OrderPackage oPackage:oPackageList){
				Integer oPackageId = oPackage.getPackageId();
				dto = this.dozer.map(oPackage, ShipmentsDTO.class);
				k++;
				dto = new ShipmentsDTO();
				count +=1; 
				if(oPackage.getCustomerCharge()!=null&&oPackage.getHandlingFee()!=null){
					dto.setCustomerCharge(Arith.sub(oPackage.getCustomerCharge(),oPackage.getHandlingFee()));
				}
				dto.setPackageId(count + "");
				dto.setPackageNo(count + "");
				dto.setShipmentId(shipmentId);
				dto.setDeliveryType(oPackage.getDeliveryType());
				dto.setIsNew("1");
				dto.setPkgBatchCount(oPackageList.size());
				dto.setPkgBatchSeq(k);
				dto.setShipVia(this.shippingService.getShipVia(null, null, oPackage.getShipMethod(), null).getName());
				dto.setPackageStatus(oPackage.getStatus());
				dto.setBillableWeight(oPackage.getBillableWeight());
				dto.setType("package");
				dto.setStatus("Drafted");
				dto.setZone(oPackage.getZone());
				dto.setShipMethodId(oPackage.getShipMethod());
				dto.setShipTo(oPackage.getShiptoAddress());
				dto.setShipToAddress(oPackage.getShiptoAddress());
				dto.setWarehouseId(oPackage.getWarehouseId());
				dto.setHandingFee(oPackage.getHandlingFee());
				//dto.setCustomerCharge(customerCharge)
				dto.setFlag(true);
				dto.setPackageStatus("Drafted");
				ShipPackage packages = new ShipPackage();
				packages.setPackageId(count);
				packages.setPackageNo(count+"");
				packages.setStatus("Drafted");
				packages.setBillableWeight(oPackage.getBillableWeight());
				packages.setWarehouseId(oPackage.getWarehouseId());
                //Rcp* add by zhanghuibin
                OrderAddress orderAddress = orderAddressDao.getAddrByOrderNoAndType(oPackage.getOrderNo(), "SHIP_TO");
                dto.setRcpCity(orderAddress.getCity());
                dto.setRcpCountry(orderAddress.getCountry());
                dto.setRcpFirstName(orderAddress.getFirstName());
                dto.setRcpLastName(orderAddress.getLastName());
                dto.setRcpMidName(orderAddress.getMidName());
                dto.setRcpMobile(orderAddress.getMobile());
                dto.setRcpOrgName(orderAddress.getOrgName());
                dto.setRcpPhone(orderAddress.getBusPhone());
                dto.setRcpState(orderAddress.getState());
                dto.setRcpZipCode(orderAddress.getZipCode());
                dto.setRcpBusEmail(orderAddress.getBusEmail());
                dto.setRcpAddrClass(orderAddress.getAddrClass());
                dto.setRcpAddrLine1(orderAddress.getAddrLine1());
                dto.setRcpAddrLine2(orderAddress.getAddrLine2());
                dto.setRcpAddrLine3(orderAddress.getAddrLine3());
                dto.setRcpFax(orderAddress.getFax());
                dto.setRcpTitle(orderAddress.getTitle());
                list.add(dto);
				listPackage.add(dto);
	
				for (int i = 0; i < listOrderItemTemp.size(); i++) {
					ShipmentsDTO ldto = listOrderItemTemp.get(i);
					if(this.shippingService.checkOrderPackageItem(Integer.valueOf(ldto.getOrderNo()), Integer.valueOf(ldto.getItemNo()),oPackageId)){
						ldto.setPackageId(oPackage.getPackageId() + "");
						ldto.setPackages(packages);
						ldto.setStatus("Drafted");
						ldto.setType("line");
						list.add(ldto);
					}
				}
				List<ShipPackageLineDTO> listNewLine = new ArrayList<ShipPackageLineDTO>();
				List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();
				for (int i = 0; i < list.size(); i++) {
					ShipmentsDTO dto_ = list.get(i);
					if ("line".equals(dto_.getType())&&(oPackage.getPackageId().toString()).equals(dto_.getPackageId())) {
						if (listNewLine.size() > 0) {
							boolean flag = true;
							for (int j = 0; j < listNewLine.size(); j++) {
								ShipPackageLineDTO ldto = listNewLine.get(j);
								if (Integer.parseInt(dto_.getOrderNo()) == ldto.getOrderNo() && Integer.parseInt(dto_.getItemNo()) == ldto.getItemNo()) {
									this.getShipPackageLine(dto_, ldto);
									flag = false;
									break;
								}
							}
							if (flag == true) {
								ShipPackageLineDTO lineDto = this.getShipPackageLine(dto_,orderItemDTOList);
								listNewLine.add(lineDto);
							}
						} else {
							ShipPackageLineDTO lineDto = this.getShipPackageLine(dto_,orderItemDTOList);
							listNewLine.add(lineDto);
						}
					}
				}
				dto.setListPackageLine(listNewLine);
			}
			Struts2Util.getSession().setAttribute("packageTemp", packageIds);
			Struts2Util.getSession().setAttribute("listOrderItem", listOrderItem);
			Struts2Util.getSession().setAttribute("listPackage", listPackage);
			Struts2Util.getSession().setAttribute("listTemp", list);
			Struts2Util.getSession().setAttribute("count", count);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "packingDetail";
	}


	/**
	 * For remove
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String remove() throws Exception {
		try {
			String orderNo = Struts2Util.getRequest().getParameter("orderNo");
			String itemNo = Struts2Util.getRequest().getParameter("itemNo");
			String packageId = Struts2Util.getRequest().getParameter("packageIds");
			List<ShipmentsDTO> rightDtoOld = (List<ShipmentsDTO>) Struts2Util.getSession().getAttribute("listPackage");
			if(rightDtoOld == null)
				rightDtoOld = new ArrayList<ShipmentsDTO>();
			List<ShipPackageLineDTO> tagList = (List<ShipPackageLineDTO>) Struts2Util.getSession().getAttribute("tagList");
			if (tagList == null || tagList.size() == 0) {
				tagList = new ArrayList<ShipPackageLineDTO>();
				for (ShipmentsDTO shipmentsDTO : rightDtoOld) {
					if (shipmentsDTO != null && shipmentsDTO.getListPackageLine() != null)
						tagList.addAll(shipmentsDTO.getListPackageLine());
				}
			}
			List<ShipmentsDTO> leftDtoOld = (List<ShipmentsDTO>) Struts2Util.getSession().getAttribute("listOrderItem");
			List<String> oipList = new ArrayList<String>();
			List<String> tag = (List<String>) Struts2Util.getSession().getAttribute("tag");
			if (tag == null || tag.size() == 0) {
				tag = new ArrayList<String>();
				for (int o = 0; o < tagList.size(); o++) {
					for (int h = 0; h < tagList.size(); h++) {
						tag.add("init");
					}
				}
			}
			if (orderNo != null && !orderNo.equals("") && itemNo != null && !itemNo.equals("") && packageId != null && !packageId.equals("")) {
				String[] orderNos = orderNo.substring(0, orderNo.length() - 1).split(",");
				String[] itemNos = itemNo.substring(0, itemNo.length() - 1).split(",");
				String[] packageIds = packageId.substring(0, packageId.length() - 1).split(",");
				
				rightDtoOld = this.getNewShipmentDto(rightDtoOld);
				for(int i=0;i<rightDtoOld.size();i++){
					for(int j=0;j<packageIds.length;j++){
						if(!"Drafted".equals(rightDtoOld.get(i).getPackageStatus()) && rightDtoOld.get(i).getPackageId().equals(packageIds[j])){
							Struts2Util.getRequest().setAttribute("message", "Please choose the Drafted package");
							return "packingDetail";
						}
					}
				}
				
				List<ShipmentsDTO> tempList = (List<ShipmentsDTO>) Struts2Util.getSession().getAttribute("listTemp");
				for (int i = 0; i < orderNos.length; i++) {
					// 根据orderNo，itemNo，packageId查出对应的shipmentDTO列表
					List<ShipmentsDTO> leftDto = this.shippingService.getShipmentsDTOList(orderNos[i], itemNos[i],packageIds[i]);
					/*
					 * 若果查出的shipmentDTO不为空，则将数据添加到左边
					 */

					boolean flag2 = true;

					for (int x = 0; x < tagList.size(); x++) {
						ShipPackageLineDTO s = tagList.get(x);
						if (orderNos[i].equals(s.getOrderNo().toString())&& itemNos[i].equals(s.getItemNo().toString())
								&& packageIds[i].equals(s.getPackageId().toString())) {
							// rightDtoOld1.get(t).getListPackageLine().remove(spld);
							// tag=1;
							if (tag.get(x).equals("init")) {
								tag.set(x, "used");
							} else
								flag2 = false;
							break;
						}
					}

					if (leftDto != null && leftDto.size() > 0) {
						for (int f = 0; f < leftDto.size(); f++) {
							boolean flag = true;
							for (int k = 0; k < leftDtoOld.size(); k++) {
								if (leftDto.get(f).getReservationId().equals(leftDtoOld.get(k).getReservationId())) {
									if (flag2) {
										if ("1".equals(leftDtoOld.get(k).getOrderItemQty())) {
											Double size = Arith.add(Double.parseDouble(leftDtoOld.get(k).getSize())
													, Double.parseDouble(leftDto.get(f).getSplSize()));
											leftDtoOld.get(k).setSize(size + "");
										} else {
											int qty = Integer.parseInt(leftDtoOld.get(k).getQuantity())
													+ Integer.parseInt(leftDto.get(f).getSplQuantity());
											leftDtoOld.get(k).setQuantity(qty + "");
										}
									}
									flag = false;
									break;
								}
							}
							if (flag) {
								leftDtoOld.add(leftDto.get(f));
							}
						}
					}

					/*
					 * 如果中间list不为空，先判断有无对应的orderNo，itemNo，packageId的记录，如果有，加入左边（左边就的就加qty或size
					 * ，左边没有的在 左边添加条新记录，加完后删出中间list里的对应的此条记录
					 */
					if (tempList != null && tempList.size() > 0) {
						for (int w = 0; w < tempList.size(); w++) {
							ShipmentsDTO tempDto = tempList.get(w);
							if (orderNos[i].equals(tempDto.getOrderNo())&& itemNos[i].equals(tempDto.getItemNo())
									&& packageIds[i].equals(tempDto.getPackageId())) {
								boolean flag = true;
								for (int e = 0; e < leftDtoOld.size(); e++) {
									ShipmentsDTO leftTempDto = leftDtoOld.get(e);
									if (leftTempDto.getReservationId().equals(tempDto.getReservationId())) {
										if ("1".equals(leftTempDto.getOrderItemQty())) {
											Double size = Arith.add(Double.parseDouble(leftTempDto.getSize())
													, Double.parseDouble(tempDto.getSize()));
											leftDtoOld.get(e).setSize(size + "");
										} else {
											int qty = Integer.parseInt(leftTempDto.getQuantity())
													+ Integer.parseInt(tempDto.getQuantity());
											leftDtoOld.get(e).setQuantity(qty + "");
										}
										flag = false;
										break;
									}
								}
								if (flag) {
									leftDtoOld.add(tempDto);
								}
								tempList.remove(tempDto);
								w = -1;
							}
						}
					}

					/*
					 * 移除右边选定的line记录
					 */
					for (int n = 0; n < rightDtoOld.size(); n++) {
						List<ShipPackageLineDTO> splDto = rightDtoOld.get(n).getListPackageLine();
						if (splDto == null || splDto.size() == 0)
							continue;
						for (int j = 0; j < splDto.size(); j++) {
							ShipPackageLineDTO splDto1 = splDto.get(j);
							if (orderNos[i].equals(splDto1.getOrderNo().toString())
									&& itemNos[i].equals(splDto1.getItemNo().toString())
									&& packageIds[i].equals(splDto1.getPackageId().toString())) {
								rightDtoOld.get(n).getListPackageLine().remove(splDto1);
								// flagItem=true;
								break;
							}
						}
					}
					// 为save所准备的参数
					String oip = orderNos[i] + "," + itemNos[i] + "," + packageIds[i];
					oipList.add(oip);
				}
			}
			this.shippingService.setPackage(rightDtoOld);
			this.shippingService.setPackage(leftDtoOld);
			Struts2Util.getSession().setAttribute("listOrderItem", leftDtoOld);
			Struts2Util.getSession().setAttribute("listPackage", rightDtoOld);
			Struts2Util.getSession().setAttribute("oipList", oipList);
			Struts2Util.getSession().setAttribute("tag", tag);
			Struts2Util.getSession().setAttribute("tagList", tagList);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "packingDetail";
	}

	/**
	 * 进行UnDoAll操作
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String unDoAll() throws Exception {
		try {
			String chks = (String) Struts2Util.getSession().getAttribute("orderNos");
			List<ShipmentsDTO> listPackage = (List<ShipmentsDTO>) Struts2Util.getSession().getAttribute("listPackage");
			List<String> listOip = (List<String>)Struts2Util.getSession().getAttribute("oipList");
			if(listOip == null)
				listOip = new ArrayList<String>();
			if(listPackage == null)
				listPackage = new ArrayList<ShipmentsDTO>();
			
			listPackage = this.getNewShipmentDto(listPackage);
			for(int i=0;i<listPackage.size();i++){
				if(!"Drafted".equals(listPackage.get(i).getPackageStatus())){
					Struts2Util.getRequest().setAttribute("message", "Can not UndoAll the " + listPackage.get(i).getPackageStatus() + " records");
					return "packingDetail";
				}
			}
			for (int i = 0;i < listPackage.size();i++) {
				ShipmentsDTO pdto = listPackage.get(i);
				if(pdto.getListPackageLine()!=null){
					for(int j = 0;j < pdto.getListPackageLine().size();j++){
						ShipPackageLineDTO line = pdto.getListPackageLine().get(j);
						listOip.add(line.getOrderNo()+","+line.getItemNo()+","+pdto.getPackageId());
					}
				}
				pdto.setListPackageLine(new ArrayList<ShipPackageLineDTO>());
			}
			List<ShipmentsDTO> listOrderItem = (List<ShipmentsDTO>) this.shippingService.getOrderItemList(chks, false);
			this.shippingService.setPackage(listPackage);
			this.shippingService.setPackage(listOrderItem);
			Struts2Util.getSession().removeAttribute("listTemp");
			Struts2Util.getSession().setAttribute("listPackage", listPackage);
			Struts2Util.getSession().setAttribute("listOrderItem",listOrderItem);
			Struts2Util.getSession().setAttribute("oipList", listOip);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "packingDetail";
	}


	/**
	 * 添加一个新package
	 * 
	 * @return String
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public String addPackage() throws Exception {
		try {
			List<ShipmentsDTO> list = (List<ShipmentsDTO>) Struts2Util
					.getSession().getAttribute("listTemp");
			List<ShipmentsDTO> listPackage = (List<ShipmentsDTO>) Struts2Util
					.getSession().getAttribute("listPackage");
			Integer count = (Integer) Struts2Util.getSession().getAttribute(
					"count");
			if (list == null)
				list = new ArrayList<ShipmentsDTO>();
			if (listPackage == null)
				listPackage = new ArrayList<ShipmentsDTO>();
			if (count == null)
				count = this.shippingService.getCount();
			
			ShipmentsDTO dto = new ShipmentsDTO();
			if(listPackage!=null&&!listPackage.isEmpty()){
				for(ShipmentsDTO shipmentDto : listPackage){
					if(!"line".equals(shipmentDto.getType())){
						shipmentDto.setPkgBatchCount(shipmentDto.getPkgBatchCount()+1);
						dto.setPkgBatchCount(shipmentDto.getPkgBatchCount()+1);
						dto.setPkgBatchSeq(shipmentDto.getPkgBatchCount()+1);
					}
				}
			}else{
				dto.setPkgBatchCount(1);
				dto.setPkgBatchSeq(1);
			}
			dto.setPackageNo((count + 1) + "");
			dto.setPackageId((count + 1) + "");
			dto.setShipVia("");
			dto.setPackageStatus("Drafted");
			dto.setBillableWeight(0d);
			dto.setShipTo("");
			dto.setType("package");
			dto.setIsNew("1");
			list.add(dto);
			listPackage.add(dto);
			count++;
			Struts2Util.getSession().setAttribute("count", count);
			Struts2Util.getSession().setAttribute("listTemp", list);
			Struts2Util.getSession().setAttribute("listPackage", listPackage);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}

		return "packingDetail";
	}

	/**
	 * 删除package
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deletePackage() throws Exception {
		try {
			String packageIds_ = Struts2Util.getParameter("packageIds");
			String packageTemp = (String) Struts2Util.getSession().getAttribute("packageTemp");
			if(packageTemp == null)
				packageTemp = packageIds_;
			else
				packageTemp = packageTemp + "," + packageIds_;
			if (packageIds_ == null || packageIds_.trim().length() == 0)
				return "packingDetail";
			String[] packageIds = packageIds_.split(",");
			List<ShipmentsDTO> listPackage = (List<ShipmentsDTO>) Struts2Util.getSession().getAttribute("listPackage");
			for (int j = 0; j < packageIds.length; j++) {
				for (int i = 0; i < listPackage.size(); i++) {
					ShipmentsDTO dto = listPackage.get(i);
					if (Integer.parseInt(dto.getPackageId()) == Integer.parseInt(packageIds[j])
							&& (dto.getListPackageLine() == null || dto.getListPackageLine().size() == 0)) {
						listPackage.remove(dto);
					}
				}
			}
			Struts2Util.getSession().setAttribute("packageTemp", packageTemp);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}

		return "packingDetail";
	}

	/**
	 * 根据传过来的ShipmentDTO对象得到ShipPackageLineDTO对象
	 * 
	 * @param dto
	 * @return ShipPackageLineDTO
	 */
	public ShipPackageLineDTO getShipPackageLine(ShipmentsDTO dto,List<OrderItemDTO> orderItemDTOList) {
		ShipPackageLineDTO lineDto = new ShipPackageLineDTO();
		lineDto.setReservationId(Integer.parseInt(dto.getReservationId()));
		lineDto.setOrderNo(Integer.parseInt(dto.getOrderNo()));
		lineDto.setItemNo(Integer.parseInt(dto.getItemNo()));
		String isBreak="0";
		for(OrderItemDTO itemDto : orderItemDTOList){
			if(itemDto.getCatalogNo().equals(dto.getCatalogNo())){
				lineDto.setTemperature(itemDto.getTemperature()+"");
				isBreak = "1";
				break;
			}
		}
		if(isBreak.equals("0")){
			lineDto.setTemperature(this.shippingService.getTemperature(dto.getOrderItemType(), dto.getCatalogNo()));
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setCatalogNo(dto.getCatalogNo());
			orderItemDTO.setTemperature(Double.valueOf(lineDto.getTemperature()));
			orderItemDTOList.add(orderItemDTO);
		}
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		orderItemDTO.setCatalogNo(dto.getCatalogNo());
		
		lineDto.setQtyUom(dto.getQtyUom());
		lineDto.setSizeUom(dto.getSizeUom());
		lineDto.setItemName(dto.getItemName());
		lineDto.setPackageId(Integer.parseInt(dto.getPackageId()));
		lineDto.setOrderItemQty(Integer.parseInt(dto.getOrderItemQty()));
		lineDto.setStatus("Drafted");
		lineDto.setQuantity(Integer.parseInt(dto.getQuantity()));
		lineDto.setSize(Double.parseDouble(dto.getSize()));
		String lineStr = " OrderMain No " + lineDto.getOrderNo() + " Item #"
				+ lineDto.getItemNo() + ": " + lineDto.getQuantity()
				+ lineDto.getQtyUom() + lineDto.getSize()
				+ lineDto.getSizeUom() + " ";
		if (lineDto.getItemName() != null
				&& lineDto.getItemName().toString().length() > 10)
			lineStr += lineDto.getItemName().substring(0, 10)+"...";
		else
			lineStr += lineDto.getItemName();
		lineDto.setLineStr(lineStr);
		return lineDto;
	}

	/**
	 * 得到shipmentDTO对象
	 * 
	 * @param packageId
	 * @param quantityText
	 * @param sizeText
	 * @param chkRid
	 * @param quantity
	 * @param size
	 * @return ShipmentsDTO
	 */
	public ShipmentsDTO getShipmentTemp(String packageId, String quantityText,
			String sizeText, String chkRid, String quantity, String size,List<OrderItem> orderItemList,Reservation re) {
		ShipmentsDTO dto = new ShipmentsDTO();
		dto.setReservationId(chkRid);
		if ("All".equals(quantityText))
			dto.setQuantity(quantity);
		else
			dto.setQuantity(quantityText);
		if ("All".equals(sizeText))
			dto.setSize(size);
		else
			dto.setSize(sizeText);
		
		/*Reservation re = this.shippingService.getReservation(dto
				.getReservationId());*/

		dto.setPackageId(packageId);
		dto.setItemNo(re.getItemNo() + "");
		dto.setOrderNo(re.getOrderNo() + "");
		OrderItem item = null;
		for(OrderItem oi:orderItemList){
			System.out.println("oi.catalogNo="+oi.getCatalogNo());
			if(oi.getCatalogNo().equals(re.getCatalogNo())){
				item = oi;
			}
		}
		if(item==null){
			item = this.shippingService.getOrderItem(re.getOrderNo(),
					(int) re.getItemNo());
			System.out.println("item.catalogNo="+item.getCatalogNo());
			orderItemList.add(item);
		}
		
		dto.setCatalogNo(item.getCatalogNo());
		dto.setOrderItemType(item.getType());
		dto.setQtyUom(item.getQtyUom());
		dto.setSizeUom(item.getSizeUom());
		dto.setOrderItemQty(item.getQuantity() + "");
		dto.setItemName(item.getName());
		dto.setItemStatus(item.getStatus());
		dto.setType("line");
		
		return dto;
	}

	/**
	 * 改变packing_detial页面中左边list中的quantity或size
	 * 
	 * @param dto
	 * @param dto_
	 * @param listOrderItem
	 */
	public void changeOrderItem(ShipmentsDTO dto, ShipmentsDTO dto_,
			List<ShipmentsDTO> listOrderItem) {
		int rid = Integer.parseInt(dto.getReservationId());
		int orderItemQty_ = Integer.parseInt(dto.getOrderItemQty());
		if (rid == Integer.parseInt(dto_.getReservationId())) {
			if (orderItemQty_ == 1) {
				if (Arith.sub(Double.parseDouble(dto.getSize())
						, Double.parseDouble(dto_.getSize())) == 0) {
					listOrderItem.remove(dto);
				} else {
					dto.setSize((Arith.sub(Double.parseDouble(dto.getSize()) , Double
							.parseDouble(dto_.getSize())))
							+ "");
				}
			} else {
				if (Integer.parseInt(dto.getQuantity())
						- Integer.parseInt(dto_.getQuantity()) == 0) {
					listOrderItem.remove(dto);
				} else {
					dto
							.setQuantity((Integer.parseInt(dto.getQuantity()) - Integer
									.parseInt(dto_.getQuantity()))
									+ "");
				}
			}
		}
	}

	/**
	 * 得到ShipPackageLine对象
	 * 
	 * @param dto_
	 * @param ldto
	 */
	public void getShipPackageLine(ShipmentsDTO dto_, ShipPackageLineDTO ldto) {
		try {
			if (ldto.getOrderItemQty() == 1) {
				ldto.setSize((Arith.add(ldto.getSize() , Double.parseDouble(dto_.getSize()))));
			} else {
				ldto.setQuantity((ldto.getQuantity() + Integer.parseInt(dto_.getQuantity())));
			}
			ldto.setShipPackages(dto_.getPackages());
			String lineStr = " OrderMain No " + ldto.getOrderNo() + " Item #" + ldto.getItemNo() + ": " + ldto.getQuantity()
					+ ldto.getQtyUom() + ldto.getSize() + ldto.getSizeUom() + " ";
			if (ldto.getItemName() != null && ldto.getItemName().toString().length() > 10)
				lineStr += (ldto.getItemName() + "").substring(0, 10);
			ldto.setTemperature(this.shippingService.getTemperature(dto_.getOrderItemType(), dto_.getCatalogNo()));
			ldto.setLineStr(lineStr);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex, new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
	}

	/**
	 * New Item
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doAddNewItems() throws Exception {
		try {
			String idsStr = Struts2Util.getRequest().getParameter("ids");
			List<ShipmentsDTO> listOrderItem = (List) Struts2Util.getSession()
					.getAttribute("listOrderItem");
			if (idsStr != null && !idsStr.equals("")) {
				String itemNos = idsStr.substring(0, idsStr.length() - 1);
				String orderNo = (String) Struts2Util.getSession()
						.getAttribute("orderNo");
				List<ShipmentsDTO> newListOrderItem = this.shippingService
						.getNewOrderItemList(orderNo, itemNos);
				List<ShipmentsDTO> listOrderItem1 = (List) Struts2Util
						.getSession().getAttribute("listOrderItem");
				List<ShipmentsDTO> tempList = (List<ShipmentsDTO>) Struts2Util
						.getSession().getAttribute("listTemp");
				boolean flag = true;
				for (ShipmentsDTO newShipmentsDTO : newListOrderItem) {
					boolean flag1 = true;
					if (tempList != null && tempList.size() != 0) {
						for (int w = 0; w < tempList.size(); w++) {
							if (newShipmentsDTO.getReservationId().equals(
									tempList.get(w).getReservationId())) {
								flag1 = false;
							}
						}
					}
					if (flag1) {
						if (listOrderItem1 != null
								&& listOrderItem1.size() != 0) {
							for (ShipmentsDTO shipmentsDTO : listOrderItem1) {
								if (newShipmentsDTO.getReservationId().equals(
										shipmentsDTO.getReservationId())) {// 如果已经显示了
									flag = false;
									break;
								}
							}
						}
						// 没有显示的
						// 将reservation对应的信息添加进listOrderItem
						if (flag)
							listOrderItem.add(newShipmentsDTO);
						flag = true;
					}
				}
				String orderNos = (String) Struts2Util.getSession()
						.getAttribute("orderNos");
				String[] orderNosStrs = orderNos.split(",");
				boolean flag1 = true;
				for (int i = 0; i < orderNosStrs.length; i++) {
					if (orderNo.equals(orderNosStrs[i])) {
						flag1 = false;
					}
				}
				if (flag1) {
					orderNos += "," + orderNo;
					Struts2Util.getSession().setAttribute("orderNos", orderNos);
				}
			}
			Struts2Util.getSession().setAttribute("listOrderItem",
					listOrderItem);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "packingDetail";
	}

	/**
	 * 查询详细信息
	 */
	@SuppressWarnings("unchecked")
	public String packageInformation() {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			Integer packageId = Integer.parseInt(Struts2Util.getRequest()
					.getParameter("packageId"));
			sps = this.shippingService.findByPackageId(packageId);
			if (sps != null) {
                    Double insureValue = 0.0;
					//qty = this.shippingService.getLineQutByPackage(shipPackage.getPackageId());
					List<ShipPackageLines> shipPackageLineses = this.shippingService.getLineQutByPackage(sps.getPackageId());
                    if (shipPackageLineses != null&&!shipPackageLineses.isEmpty()) {
                        for (ShipPackageLines line : shipPackageLineses) {
                            if (line.getQuantity() != null) {
                                insureValue = Arith.add(insureValue, line.getQuantity() * line.getUnitPrice());
                            }
                        }
                        sps.setItemAmt(insureValue.toString());
                    }
				/*int shipMethodId = sps.getShipMethod();
				List shipMethod = this.shipMethodService
						.getShipMethod(shipMethodId);
				String name = "";
				String carrier = "";
				ShipMethodDTO sd = null;
				for (int i = 0; i < shipMethod.size(); i++) {
					sd = new ShipMethodDTO();
					ShipMethod sm = (ShipMethod) shipMethod.get(i);
					name += am.getName() + ",";
					carrier += sm.getCarrier()+",";

				}
				if (name.length() > 0) {
					name = name.substring(0, name.length() - 1);
					carrier = carrier.substring(0, carrier.length() - 1);
				}
				if(sd!=null){
					sd.setName_(name);
					sd.setCarrier(carrier);
				}
				Struts2Util.getRequest().setAttribute("sd", sd);*/
				shipMenthodList = this.shipMethodService.getShipMethodList();
				//System.out.println("shipmet="+shipMenthodList.size());
				//Struts2Util.getRequest().setAttribute("shipMenthodList", shipMenthodList);
				//Struts2Util.getRequest().setAttribute("sps", sps);
				System.out.println(sps.getShiptoAddress());
				System.out.println(sps.getCiItemDesc()+">>>>>>>>>>>>>>>>>>.");
				return "package_information";
			} else {
				out
						.print("<script>alert('Sorry, the package can not be edited,please save the package first.');</script>");
				// 关闭窗口
				out
						.print("<script>parent.$('#package_information').dialog('close')</script>");
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}
	
	/**
	 * 详细信息更新数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String packageInformationUpdate() {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Integer userId = SessionUtil.getUserId();
			PrintWriter out = Struts2Util.getResponse().getWriter();
			String additionalHandle = Struts2Util.getRequest().getParameter(
					"additionalHandle");
			String deliveryConfirm = Struts2Util.getRequest().getParameter(
					"deliveryConfirm");
			String hazardousMtl = Struts2Util.getRequest().getParameter(
					"hazardousMtl");
			String saturdayPickup = Struts2Util.getRequest().getParameter(
					"saturdayPickup");
			String noteOnShip = Struts2Util.getRequest().getParameter(
					"noteOnShip");
			String noteOnDelivery = Struts2Util.getRequest().getParameter(
					"noteOnDelivery");
			String noteOnExcp = Struts2Util.getRequest().getParameter(
					"noteOnExcp");
			String ciItemDescFromorder = Struts2Util.getRequest().getParameter(
					"ciItemDescFromorder");
			String ciInsuranceFromorder = Struts2Util.getRequest().getParameter(
					"ciInsuranceFromorder");
			String creationDate = Struts2Util.getParameter("creationDate");
			String shipmentDate = Struts2Util.getParameter("shipmentDate");
			if(creationDate!=null&&!creationDate.equals("")){
				sips.setCreationDate(format.parse(creationDate));
			}else{
				sips.setCreationDate(new Date());
			}
			if(shipmentDate!=null&&!shipmentDate.equals("")){
				sips.setShipmentDate(format.parse(shipmentDate+" 00:00:00"));
			}else{
				sips.setShipmentDate(new Date());
			}
			
			sips.setCiItemDescFromorder(ciItemDescFromorder);
			sips.setCiInsuranceFromorder(ciInsuranceFromorder);
			sips.setAdditionalHandle(additionalHandle);
			sips.setDeliveryConfirm(deliveryConfirm);
			sips.setHazardousMtl(hazardousMtl);
			sips.setSaturdayPickup(saturdayPickup);
			sips.setNoteOnShip(noteOnShip);
			sips.setNoteOnDelivery(noteOnDelivery);
			sips.setNoteOnExcp(noteOnExcp);
			/*if(sips.getCiInsuranceFromorder()!=null&&sips.getCiInsuranceFromorder().equals("Y")){
				if(sips.getInsuranceValue()==null){
					List<ShipPackageLines> lineList = this.shipPackageLineService.searchSplByPackageId(sips.getPackageId());
					sips.setInsuranceValue(new BigDecimal(Arith.mul(lineList.size(), 10)).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}else{
				sips.setInsuranceValue(new BigDecimal(10.00));
			}*/
			BigDecimal actlCarrCharge = new BigDecimal(0.00);
			if(sips.getBaseCharge()!=null) {
				actlCarrCharge = actlCarrCharge.add(sips.getBaseCharge());
			}
			if(sips.getDeliveryConfirmFee()!=null) {
				actlCarrCharge = actlCarrCharge.add(sips.getDeliveryConfirmFee());
			}
			if(sips.getPackagingFee()!=null) {
				actlCarrCharge = actlCarrCharge.add(sips.getPackagingFee());
			}
			sips.setActlCarrCharge(actlCarrCharge);
			BigDecimal carrierCharge = new BigDecimal(0.00);
			if(sips.getActlCarrCharge()!=null && sips.getInsuranceCharge()!=null&& sips.getAdtlCustomerCharge()!=null){
				carrierCharge = sips.getActlCarrCharge().add(sips.getInsuranceCharge());
				carrierCharge = carrierCharge.add(sips.getAdtlCustomerCharge());
			}
			//sips.setCarrierCharge(carrierCharge);//9.30 jinhong 要求不变。
			sips.setShippingAccount(String.valueOf(carrierCharge));
			
			sips.setModifyDate(new Date());
			sips.setModifiedBy(userId.toString());
			this.shippingService.updatePackId(sips);
			
			out.print("<script>alert('Data saved successfully!');</script>");
			// 关闭窗口
			out.print("<script>parent.showNewWeight('"+sips.getPackageNo() + "','" + sips.getActualWeight()+"');" +
					"parent.location=parent.location.href;</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}

		return null;
	}


	/**
	 * addnewItems1
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAddNewItems1() throws Exception {
		return "addnewItems1";
	}

	/**
	 * Print Shipping Label
	 */
	@SuppressWarnings("unchecked")
	public String printShippingLabel() {

		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			// 判断保存save后，session是不是存在
			if (Struts2Util.getSession().getAttribute("listTemp") == null) {
				String orderNo = (String) Struts2Util.getSession()
						.getAttribute("orderNos");
				// 获取packageId
				List list3 = this.shippingService.getPackageTn(orderNo);
				List sp = null;
				String name = "";
				String country = "";
				if(orderNo !=null){
					String[] orderNos = orderNo.split(",");
					if(orderNos.length>0){
						List<OrderAddress> orderAddressList = this.orderAddressDao.getAddrByOrderNo(Integer.valueOf(orderNos[0]));
						if(orderAddressList!=null&&!orderAddressList.isEmpty()){
							for(OrderAddress oa : orderAddressList){
								if(oa.getAddrType().equals("SHIP_TO")){
									country = oa.getCountry();
								}
							}
							
						}
					}
				}
				ShipMethodDTO sd = null;
				// 判断list是否为null
				if (list3 != null && list3.size() > 0) {
					// 循环获取packageId
					for (int i = 0; i < list3.size(); i++) {
						sd = new ShipMethodDTO();
						// 获取packageId
						String packageIds = list3.get(i) + "";
						// 根据packageId获取List集合
						sp = this.shippingService.getPackageId(packageIds);
						// for (int j = 0; j < sp.size();j++) {
						if (sp != null && sp.size() > 0) {
							// 获取ShipPackage实体对象
							ShipPackage sps = (ShipPackage) sp.get(0);
							// 判断现在的状态Packed
							Integer shipmentId = sps.getShipMethod();
							if (sps.getStatus().equals("Packed")) {
								
								//为调用银行接口而提供参数的集合(准备参数)
								BankCardDTO bank=shippingService.selectForBand(orderNo);
								Struts2Util.getRequest().setAttribute("bank", bank);
								
								List shipMethod = this.shipMethodService
										.getShipMethod(shipmentId);

								for (int k = 0; k < shipMethod.size(); k++) {
									ShipMethod sm = (ShipMethod) shipMethod
											.get(k);
									name += sm.getCarrier().toUpperCase() + ",";
								}

							} else {
								out
										.print("<script>alert('Sorry, this button did not complete your current operation!');</script>");
								// 关闭窗口
								out
										.print("<script>parent.$('#print_shipping_label').dialog('close')</script>");
								return null;
							}
						}
					}
					if (name.length() > 0) {
						name = name.substring(0, name.length() - 1);
					}
					sd.setName_(name);
					Struts2Util.getRequest().setAttribute("sd", sd);
					Struts2Util.getRequest().setAttribute("country", country);
					return "print_shipping_label";
				} else {
					out
							.print("<script>alert('Sorry, this button did not complete your current operation!');</script>");
					// 关闭窗口
					out
							.print("<script>parent.$('#print_shipping_label').dialog('close')</script>");
					return null;
				}

			} else {
				out
						.print("<script>alert('Sorry, this button did not complete your current operation!');</script>");
				// 关闭窗口
				out
						.print("<script>parent.$('#print_shipping_label').dialog('close')</script>");
				return null;
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}

	/**
	 * 调用生成trackingNo的接口
	 * 生成文件默认存放在c:\shippingLable下
	 */
//	@SuppressWarnings({ "unchecked", "static-access" })
//	public String printShippingLabelTrackingNo() {
//		try {
//			PrintWriter out = Struts2Util.getResponse().getWriter();
//			 String filename = "c:\\shippingLable";
//			 String filenameZip = "c:\\shippingLable.zip";
//			//String filename = "/tmp/shippingLable";       //linux地址
//			//String filenameZip = "/tmp/shippingLable.zip";//linux地址
//			  File folder = new File(filename);
//			  File folderZip = new File(filenameZip);
//			//删除文件夹和压缩包
//			if (folder.exists()) {
//				fu.delFolder("c:/shippingLable");
//				//fu.delFolder("/tmp/shippingLable");
//			}
//			if(folderZip.exists()){
//				fu.delFolder("c:/shippingLable.zip");
//				//fu.delFolder("/tmp/shippingLable.zip");
//			}
//			//创建文件夹
//			fu.createFolder("c:/shippingLable");
//			//fu.createFolder("/tmp/shippingLable");
//			
//			//获取orderNo
//			String orderNo = (String) Struts2Util.getSession().getAttribute(
//					"orderNos");
//			//根据orderNo查询
//			List list3 = this.shippingService
//					.getPackagesAndPackageLine(orderNo);
//			ShipPackageLineDTO sp = null;
//			ShipPackage sps = null;
//			//实例化一个list2
//			List list2 = new ArrayList();
//			//判断list3
//			if (list3.size() > 0) {
//				String trackingNo_ = "";
//				//for循环list3
//				for (int i = 0; i < list3.size(); i++) {
//					sp = new ShipPackageLineDTO();
//					Object[] obj = (Object[]) list3.get(i);
//					sps = (ShipPackage) obj[0];
//					String orderNos = obj[1] + "";
//					String itemNos = obj[2] + "";
//					String oi = orderNos + "-" + itemNos;
//					String dimLength = (int) (sps.getLength() == null ? 0d
//							: sps.getLength())
//							+ "";
//					String dimWidth = (int) (sps.getWidth() == null ? 0d : sps
//							.getWidth())
//							+ "";
//					String dimHeight = (int) (sps.getHeight() == null ? 0d
//							: sps.getHeight())
//							+ "";
//					String rcpOrgName = sps.getRcpOrgName();
//					String rcpPhone = sps.getRcpPhone();
//					String shiptoAddress = sps.getShiptoAddress();
//					String rcpCity = sps.getRcpCity();
//					String rcpState = sps.getRcpState();
//					String rcpZipCode = sps.getRcpZipCode();
//					String rcpCountry = sps.getRcpCountry();
//					Double actualWeight = sps.getActualWeight();
//					Double billableWeight = sps.getBillableWeight();
//					String note = sps.getNote();
//					String recpPersonName = sps.getRcpFirstName()
//							+ sps.getRcpMidName() + sps.getRcpLastName();
//					// trackingNo_ = this.shipWebServiceClient
//					// .getFexdexTrackingNumber(true,
//					// ServiceType.STANDARD_OVERNIGHT,
//					// PackagingType.FEDEX_PAK,
//					// "admin",recpPersonName,
//					// rcpOrgName,rcpPhone,shiptoAddress,
//					// rcpCity,rcpState,rcpZipCode,rcpCountry,
//					// "1",actualWeight, dimLength,dimWidth,
//					// dimHeight, oi, "",billableWeight,
//					// 4.0,"1",note, 3.0, 3.0);
//					trackingNo_ = this.shipWebServiceClient
//							.getFexdexTrackingNumber(true,
//									ServiceType.FEDEX_GROUND,
//									PackagingType.YOUR_PACKAGING, "admin",
//									"recPersonName", "aa", "111111111",
//									"shiptoAddress", "Collierville", "TN",
//									"38017", "US", "1", 2.00, "2", "2", "4",
//									oi, "11", 5.00, 4.0, "1", "notessss", 3.0,
//									3.0);
//					sp.setTrackingNo(trackingNo_);
//					sp.setPackageId(sps.getPackageId());
//					list2.add(sp);
//				}
//				//实现文件压缩
//				ziputil.zipFile("c:\\shippingLable", "c:\\shippingLable.zip");
//				//ziputil.zipFile("/tmp/shippingLable", "/tmp/shippingLable.zip");
//				
//				//放在request
//				Struts2Util.getSession().setAttribute("m", list2);
//				return "print_shipping_label_trackingNo";
//			} else {
//				out
//						.print("<script>alert('Sorry,Data is not correct!');</script>");
//				out
//						.print("<script>parent.$('#print_shipping_label').dialog('close');</script>");
//				return null;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
//			exceptionUtil.logException(exDTO, this.getClass(), ex,
//					new Exception().getStackTrace()[0].getMethodName(),
//					"INTF0203", SessionUtil.getUserId());
//			ExceptionOut.printException(exDTO);
//		}
//		return null;
//	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public String printShippingLabelTrackingNo() {
		
		try {
			String shippingLable = SessionUtil.generateTempId();
			File file = new File(this.fileService.getUploadPath()+ shippingLable);
			if (!file.exists()) {// 如果不存在该文件夹
				file.mkdir();// 新建

			}
			PrintWriter out = Struts2Util.getResponse().getWriter();
			//String filename = "c:\\shippingLable";
			//String filenameZip = "c:\\shippingLable.zip";
			/*String filename = "/tmp/shippingLable";       //linux地址
			String filenameZip = "/tmp/shippingLable.zip";//linux地址
			  File folder = new File(filename);
			  File folderZip = new File(filenameZip);*/
			//删除文件夹和压缩包
			/*if (folder.exists()) {
				//fu.delFolder("c:/shippingLable");
				fu.delFolder("/tmp/shippingLable");
			}
			if(folderZip.exists()){
				//fu.delFolder("c:/shippingLable.zip");
				fu.delFolder("/tmp/shippingLable.zip");
			}
			//创建文件夹
			//fu.createFolder("c:/shippingLable");
			fu.createFolder("/tmp/shippingLable");*/
			
			//获取orderNo
			String orderNo = (String) Struts2Util.getSession().getAttribute(
					"orderNos");
			//根据orderNo查询
			List list3 = this.shippingService
					.getPackagesAndPackageLine(orderNo);
			ShipPackageLineDTO sp = null;
			ShipPackage sps = null;
			//Shipment shipment = new Shipment();
			//实例化一个list2
			List list2 = new ArrayList();
			//判断list3
			if (list3.size() > 0) {
				String trackingNo_ = "";
				//for循环list3
				for (Integer i = 0; i < list3.size(); i++) {
					sp = new ShipPackageLineDTO();
					Object[] obj = (Object[]) list3.get(i);
					sps = (ShipPackage) obj[0];
					String flag = "Y".equals(sps.getCiInsuranceFromorder())? "Y" : "N";
					Double insureValue = 0.0;
					//qty = this.shippingService.getLineQutByPackage(shipPackage.getPackageId());
					List<ShipPackageLines> shipPackageLineses = this.shippingService.getLineQutByPackage(sps.getPackageId());
				    for (ShipPackageLines line : shipPackageLineses) {
                         if (line.getQuantity() != null) {
                             insureValue = Arith.add(insureValue, line.getQuantity() * line.getUnitPrice());
                         }
                    }
				    if("Y".equals(flag)){
                        sps.setInsuranceValue(new BigDecimal(insureValue));
                    }
					String ciItemDesc = sps.getCiItemDesc();
					//shipment = sps.getShipments();
					/*if(i==0&&shipment!=null&&shipment.getShippingFile()!=null&&!shipment.getShippingFile().equals("")){
						shippingLable = shipment.getShippingFile();
					}*/
					
					if(sps.getTrackingNo()==null||sps.getTrackingNo().equals("")||sps.getTrackingNo().equals("null")){
						
						String orderNos = obj[1] + "";
						//String itemNos = obj[2] + "";
						String oi = orderNos;
						System.out.println(orderNos);
						//OrderAddress orderAddress = this.shipmentService.getShipTo(Integer.valueOf(orderNos), null);
						OrderMain order = this.orderDao.getById(Integer.valueOf(orderNos));
						//PurchaseOrder po = this.purchaseOrderService.getPurchaseOrderBySoId(orderAddress.getOrderNo());
						List<ShipPackageLines> lineList = this.shipPackageLineService.searchSplByPackageId(sps.getPackageId());
						Double amount = 0.0;
						String currency = "USD";//order.getOrderCurrency();
						//String desc = "";
						Integer qty = 0;
						String uom = "";
						if(lineList!=null&&!lineList.isEmpty()){
							Integer isT = 0;
							for(ShipPackageLines line:lineList){
								OrderItem orderItem = this.shippingService.getOrderItem(line.getOrderNo(), line.getItemNo());
								/*if(orderItem.getType().equals("PRODUCT")){
									Product product = this.productDao.findUniqueBy("catalogNo", orderItem.getCatalogNo());
								}else if(orderItem.getType().equals("SERVICE")){
									com.genscript.gsscm.serv.entity.Service service = this.servDao.findUniqueBy("catalogNo", orderItem.getCatalogNo());
								}*/
								if("Y".equals(sps.getCiItemDescFromorder())&&isT==0){
									ciItemDesc=orderItem.getShortDesc();
									isT=1;
								}
								Double price = Arith.mul(orderItem.getUnitPrice(),line.getQuantity());
								amount = Arith.add(amount,price); 
								qty +=line.getQuantity();
								uom = line.getQtyUom();
							}
						}
						/*String poNo=null;
						if(po!=null){
							poNo = po.getOrderNo().toString();
						}*/
						String poNo = "CC";
						List<PaymentVoucher> poList = this.paymentVoucherDao.findBy("orderNo", order.getOrderNo());
						if(poList!=null&&!poList.isEmpty()){
							for(PaymentVoucher pv :poList){
								if(pv.getPaymentType().equals("PO")&&pv.getPoNumber()!=null){
									poNo = pv.getPoNumber().toString();
								}
							}
						}
/*						String dimLength = (int) (sps.getLength() == null ? 0d
								: sps.getLength())
								+ "";
						String dimWidth = (int) (sps.getWidth() == null ? 0d : sps
								.getWidth())
								+ "";
						String dimHeight = (int) (sps.getHeight() == null ? 0d
								: sps.getHeight())
								+ "";
						String rcpOrgName = sps.getRcpOrgName();
						String rcpPhone = sps.getRcpPhone();
						String shiptoAddress = sps.getShiptoAddress();
						String rcpCity = sps.getRcpCity();
						String rcpState = sps.getRcpState();
						String rcpZipCode = sps.getRcpZipCode();
						String rcpCountry = sps.getRcpCountry();
						Double actualWeight = sps.getActualWeight();
						Double billableWeight = sps.getBillableWeight();
						//String note = sps.getNote();
						String recpPersonName = sps.getRcpFirstName()
								+ sps.getRcpMidName() + sps.getRcpLastName();*/
						// trackingNo_ = this.shipWebServiceClient
						// .getFexdexTrackingNumber(true,
						// ServiceType.STANDARD_OVERNIGHT,
						// PackagingType.FEDEX_PAK,
						// "admin",recpPersonName,
						// rcpOrgName,rcpPhone,shiptoAddress,
						// rcpCity,rcpState,rcpZipCode,rcpCountry,
						// "1",actualWeight, dimLength,dimWidth,
						// dimHeight, oi, "",billableWeight,
						// 4.0,"1",note, 3.0, 3.0);
						//Integer loginUserId = SessionUtil.getUserId();
						//User user = this.userDao.getById(loginUserId);
						//Integer pageCount = list3.size();
						Integer shipMethodId = sps.getShipMethod();
						System.out.println(shipMethodId);
						ServiceType serviceType=null;
						if(shipMethodId.equals(1)){
							serviceType = ServiceType.PRIORITY_OVERNIGHT;
						}else if(shipMethodId.equals(2)){
							serviceType = ServiceType.STANDARD_OVERNIGHT;
						}else if(shipMethodId.equals(3)){
							serviceType = ServiceType.FEDEX_2_DAY_FREIGHT;
						}else if(shipMethodId.equals(4)){
							serviceType = ServiceType.FEDEX_2_DAY;
						}else if(shipMethodId.equals(5)){
							serviceType = ServiceType.FEDEX_EXPRESS_SAVER;
						}else if(shipMethodId.equals(7)){
							serviceType = ServiceType.GROUND_HOME_DELIVERY;
						}else if(shipMethodId.equals(6)){
							serviceType = ServiceType.FEDEX_GROUND;
						}else if(shipMethodId.equals(8)){
							serviceType = ServiceType.INTERNATIONAL_PRIORITY;
						}else if(shipMethodId.equals(9)){
							serviceType = ServiceType.INTERNATIONAL_ECONOMY;
						}
						//serviceType = ServiceType.INTERNATIONAL_PRIORITY;
						DropoffType dropoffType = DropoffType.REGULAR_PICKUP;
						PackagingType packagingType = PackagingType.YOUR_PACKAGING;
						if(sps.getPackageType()!=null){
							if(sps.getPackageType().equals("Envelop")){
								packagingType = PackagingType.FEDEX_ENVELOPE;
								if(sps.getBillableWeight()==null||sps.getBillableWeight()==0||sps.getBillableWeight()<0){
									sps.setBillableWeight(0.2);
								}
							}else if(sps.getPackageType().equals("Box")){
								packagingType = PackagingType.FEDEX_BOX;
							}else if(sps.getPackageType().equals("Package")){
								packagingType = PackagingType.FEDEX_PAK;
							}else if(sps.getPackageType().equals("Your package")){
								packagingType = packagingType.YOUR_PACKAGING;
							}
							
						}
						trackingNo_ = this.shipWebServiceClient
								.getFexdexTrackingNumber/*(true,ServiceType.FEDEX_GROUND,
										PackagingType.YOUR_PACKAGING, user.getEmployee().getEmployeeName(), orderAddress, sps.getShipments(), sps, list3.size());*/
										(sps,poNo,false,serviceType,
										packagingType,dropoffType, "LucyWang",
										"Recevice Person Name", sps.getShiptoAddress().replaceAll("&#13;&#10;", "</br>"), "111111111",
										"Ship To Address", "Collierville", "TN",
										"38017", "US", "1", 2.00, "2", "2", "4",
										oi, "11", 5.00, 4.0, i.toString(), "notessss", 3.0,
										3.0,shippingLable,amount,currency,qty,uom,ciItemDesc);
						sp.setTrackingNo(trackingNo_);
						//sps.setCiItemDesc(ciItemDesc);
						//shippingLable = trackingNo_;
						sp.setTrackingNo_("");
						sp.setPackageId(sps.getPackageId());
						if(trackingNo_!=null&&!trackingNo_.equals("")){
							String[] trNos = trackingNo_.split(":");
							if(!trNos[0].equals("ERROR")){
								this.shippingService.saveShipPackageByPackageId(sps.getPackageId(), trackingNo_);
								
								sp.setTrackingNo("The shipping label is generated and the tracking number is "+trackingNo_);
								sp.setTrackingNo_(trackingNo_);
								ziputil.zipFile(this.fileService.getUploadPath()+trackingNo_, this.fileService.getUploadPath()+trackingNo_+".zip");
							}
						}
					}else{
						sp.setTrackingNo("The shipping label is generated and the tracking number is "+sps.getTrackingNo());
						sp.setTrackingNo_(sps.getTrackingNo());
						sp.setPackageId(sps.getPackageId());
						FilelUtil.CopyDirectory(this.fileService.getUploadPath()+sps.getTrackingNo(), this.fileService.getUploadPath()+shippingLable);
					}
					
					list2.add(sp);
				}
				//实现文件压缩
				//ziputil.zipFile("c:\\shippingLable", "c:\\shippingLable.zip");
				ziputil.zipFile(this.fileService.getUploadPath()+shippingLable, this.fileService.getUploadPath()+shippingLable+".zip");
				
				//放在request
				Struts2Util.getSession().setAttribute("shippingLable", shippingLable);
				Struts2Util.getSession().setAttribute("m", list2);
				return "print_shipping_label_trackingNo";
			} else {
				out
						.print("<script>alert('Sorry,Data is not correct!');</script>");
				out
						.print("<script>parent.$('#print_shipping_label').dialog('close');</script>");
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}

	
	/**
	 * 下载生成trackingNo
	 * 以下载压缩包的方式下载
	 * @throws IOException
	 */
	public void printShippingLabelTrackingNoUpload() throws IOException {
		HttpServletResponse response = Struts2Util.getResponse();
		System.out.println("shippingLable="+shippingLable);
		response.setContentType("APPLICATION/DOWNLOAD");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ shippingLable + ".zip");// 下载的文件名
		java.io.OutputStream os = response.getOutputStream();
		//java.io.FileInputStream fis = new java.io.FileInputStream("c:/shippingLable.zip");  //生成的png文件的路径
		java.io.FileInputStream fis = new java.io.FileInputStream(this.fileService.getUploadPath()+shippingLable+".zip");  //生成的png文件的路径
		byte[] b = new byte[1024];
		int i = 0;
		while ((i = fis.read(b)) > 0) {
			os.write(b, 0, i);
		}
		fis.close();
		os.flush();
		response.flushBuffer();
		os.close();
	}

	/**
	 * print_shippinglabelTrackingno--点击close
	 * 更新shipPackage和shippackagesLine状态
	 */
	@SuppressWarnings("unchecked")
	public String printShippinglabelTrackingno() {
		try {
			Integer userId = SessionUtil.getUserId();
			PrintWriter out = Struts2Util.getResponse().getWriter();
			String trackingNo[] = Struts2Util.getRequest().getParameterValues(
					"trackingNo");
			if(trackingNo!=null&&trackingNo.length>0){
				for(int i = 0;i<trackingNo.length;i++){
					if(trackingNo[i]==null||trackingNo[i].equals("")||trackingNo[i].equals("null")){
						out.print("<script>alert('Data updated failed!');</script>");
						return null;
					}
				}
			}
			String packageId[] = Struts2Util.getRequest().getParameterValues(
					"packageId");
			String orderNo = (String) Struts2Util.getSession().getAttribute(
					"orderNos");
						
			// 获取packageId
			List list3 = this.shippingService.getPackageTn(orderNo);
			List sp = null;
			List spl = null;
			// 判断list是否为null
			if (list3 != null && list3.size() > 0) {
				// 循环获取packageId
				for (int i = 0; i < list3.size(); i++) {
					// 获取packageId
					String packageIds = list3.get(i) + "";
					// 根据packageId获取List集合
					sp = this.shippingService.getPackageId(packageIds);
					for (int j = 0; j < sp.size(); j++) {
						// 获取ShipPackage实体对象
						ShipPackage sps = (ShipPackage) sp.get(0);
						// 判断现在的状态Packed
						if (sps.getStatus().equals("Packed")) {
							sps.setStatus("Ready To Ship");
							sps.setSendBy(userId);
							// 把状态更新为Ready To Ship
							this.shippingService.updatePackId(sps);
						}
					}

					spl = this.shippingService.getPackageLineId(packageIds);
					// 循环list集合
					for (int j = 0; j < spl.size(); j++) {
						// 获取ShipPackageLines实体对象
						ShipPackageLines spls = (ShipPackageLines) spl.get(0);
						// 判断现在状态
						if (spls.getStatus().equals("Packed")) {
							spls.setStatus("Ready To Ship");
							// 更新状态
							this.shipPackageLineService
									.saveShipPackageLines(spls);
						}
					}
				}
			}
			// 更新
			List list = this.shippingService.getPackageIds(packageId, trackingNo,null,userId, null);
			List list2 = this.shippingService.getPackageLineIds(packageId,
					trackingNo);
			if (list != null && list.size() > 0 && list2 != null
					&& list2.size() > 0) {
				out.print("<script>alert('Data updated successfully.');</script>");
				String custNoStr = this.shippingService.findListOrderNo(orderNo);
				// 调用发送邮件页面
				out
				.print("<script>parent.window.location='shipping!appFrame.action?custNo="+custNoStr+"';</script>");
				// 关闭窗口
				//out.print("<script>parent.$('#print_shipping_label').dialog('close')</script>");
			} else {
				//out.print("<script>alert('Data updated failed!');</script>");
				// 关闭窗口
				out.print("<script>parent.window.location.href='shipping!appFrame.action';</script>");
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}

	/**
	 * charge_credit_card
	 */
	public String chargeCreditCard() {
		return "charge_credit_card";
	}

	/**
	 * print_shipping_label_enter_trackingNo
	 */
	@SuppressWarnings("unchecked")
	public String printShippingLabelEnterTrackingNo() {
		try {
			String orderNo = (String) Struts2Util.getSession().getAttribute(
					"orderNos");
			List list = this.shippingService.getPackageTnList(orderNo);
			int count = list.size();
            shipMenthodList = this.shipMethodService.getShipMethodList();
			Struts2Util.getRequest().setAttribute("list", list);
			Struts2Util.getRequest().setAttribute("count", count);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "print_shipping_label_enter_trackingNo";

	}

	/**
	 * 数据更新，点击ok的时候
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String printShippingLabelEnterTrackingNoUpdate() {
		try {
			Integer userId = SessionUtil.getUserId();
			PrintWriter out = Struts2Util.getResponse().getWriter();
			String trackingNo[] = Struts2Util.getRequest().getParameterValues(
					"trackingNo");
            String shipMethod[] = Struts2Util.getRequest().getParameterValues(
					"shipMethod");
			String packageId[] = Struts2Util.getRequest().getParameterValues(
					"packageId");
			// 更新
			List list = this.shippingService.getPackageIds(packageId, trackingNo,shipmentId,userId, shipMethod);
			List list2 = this.shippingService.getPackageLineIds(packageId,
					trackingNo);
			System.out.println("12321321");
			
			if (list != null && list.size() > 0 && list2 != null
					&& list2.size() > 0) {
				out
						.print("<script>alert('Data updated successfully!');</script>");
				// 关闭窗口
				out.print("<script>parent.location=parent.location.href;</script>");
				//out.print("<script>parent.$('#print_shipping_label').dialog('close')</script>");
			} else {
				out.print("<script>alert('Data updated failed!');</script>");
				// 关闭窗口
				out.print("<script>parent.location=parent.location.href;</script>");
				//out.print("<script>parent.$('#print_shipping_label').dialog('close')</script>");
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}

	/**
	 * 判断是否已经保存
	 * 
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Boolean judgeSaveAndStatus(String status) throws Exception {
		Boolean save = true;
		//Object o = Struts2Util.getSession().getAttribute("listTemp");
		String orderNo = (String) (Struts2Util.getSession()
				.getAttribute("orderNos"));
		Boolean selectStatus = shippingService.SelectPackageIdAndStatus(
				orderNo, status);

		// 没有保存或者没有查到相应的状态时
		if (/*o != null || */!selectStatus) {
			// PrintWriter out = Struts2Util.getResponse().getWriter();
			save = false;
			// out.print("<script>alert('Sorry, no Save or no
			// Data!');</script>");
		}
		return save;
	}

	/*
	 * 
	 */
	public String confirmPrintPick(){
		Integer userId = SessionUtil.getUserId();
		Map<String, Object> rt = new HashMap<String, Object>();
		String packageIds = (String) (Struts2Util.getSession().getAttribute("packageIds"));
		//String orderNo = (String) (Struts2Util.getSession().getAttribute("orderNos"));
		String lotNos = (String) (Struts2Util.getParameter("lotNos"));
		System.out.println(lotNos+">>>>>>>>>>>>");
		if(packageIds==null){
			
			rt.put("message",SUCCESS);
			Struts2Util.renderJson(rt);
			return null;
		}
		String[] packageId = packageIds.split(",");
		if(Struts2Util.getSession().getAttribute("Picklist")!=null){
			return "printPickList";
		}
		for (int i = 0; i < packageId.length; i++) {
			List<ShipPackage> shipPackageList = this.shipPackageLineService.getShipPackageList(Integer.parseInt(packageId[i]));
			if (shipPackageList != null && shipPackageList.size() > 0) {
				for (ShipPackage shipPackage : shipPackageList) {
					if(shipPackage.getStatus().equals("Drafted")){
						// 减去库存接口
						this.shipPackageLineService.uptReservationBypackageId(new String[]{packageId[i]});	
						//减去实际库存接口
					}
				}
			}
		}
		// 更新
		this.shippingService.printPickListModify(packageIds,userId,lotNos);
		rt.put("message",SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * printPickList 打印包信息
	 * 
	 * @return String
	 */
	public String printPickList()  throws Exception {
		try {
			String packageIds = Struts2Util.getParameter("packageIds");
			//System.out.println("packageIds="+packageIds);
			String lotNo = Struts2Util.getParameter("lotNos");
			//System.out.println(lotNo+">>>>>>>>>>>>>>>>>>>>>>>>");
			List<PrintPickListsDTO> list = shippingService.printPickLists(shipmentId,packageIds);
			//System.out.println("packageIds="+list.size());
			if(lotNo!=null&&!lotNo.equals("")&&list!=null&&!list.isEmpty()){
				String[] lotNos = lotNo.split(";");
				List<String> lotNoList = new ArrayList<String>();
				List<String> lineIdList = new ArrayList<String>();
				List<String> locationCodeList = new ArrayList<String>();
				if(lotNos.length>0){
					for(String no:lotNos){
						String[] nos = no.split(",");
						if(nos.length>0){
							if(nos[1].equals("null")){
								nos[1] = "";
							}
							if(nos[2].equals("WorkStation")){
								nos[2] = "Work Station";
							}
							lotNoList.add(nos[1]);
							lineIdList.add(nos[0]);
							locationCodeList.add(nos[2]);
						}
					}
				}
				for(PrintPickListsDTO dto : list){
					if(dto.getPrintPickListDTOList()!=null&&!dto.getPrintPickListDTOList().isEmpty()){
						for(PrintPickListDTO pick : dto.getPrintPickListDTOList()){
							//System.out.println("?>>>>>>"+pick.getStorageLocation());
							//pick.setLocationCode(pick.getStorageLocation());
							for(int i=0;i<lineIdList.size();i++){
								if(lineIdList.get(i).equals(pick.getLineId())){
									pick.setLotNo(lotNoList.get(i));
									pick.setLocationCode(locationCodeList.get(i));
									System.out.println(pick.getLocationCode()+" ......");
								}
							}
						}
					}
					
				}
			}
			//List<Storage> storageList = this.storageService.findStorage("1");
			//Struts2Util.getSession().setAttribute("storageList", storageList);
			Struts2Util.getSession().setAttribute("Picklist", list);
			//out.print("<script>window.opener.location=window.opener.location;</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
			new Exception().getStackTrace()[0].getMethodName(),
			"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "printPickList";
		
	}

	/**
	 * 转换PDF功能
	 * 
	 * @throws Exception
	 * @throws DocumentException
	 */
	public String printPick() throws Exception {
		if(Struts2Util.getSession().getAttribute("Picklist")!=null)
			System.out.print("------------------printPick环节------------------");
		else
		{
			try {
				if (!judgeSaveAndStatus("Drafted") && !judgeSaveAndStatus("Picked") && !judgeSaveAndStatus("Packed")) {
					String message = "No matches status.";
					Struts2Util.getRequest().setAttribute("message", message);
					return null;
				}
				print();
				List<ShipmentsDTO> listPackage = (List) Struts2Util.getSession().getAttribute("listPackage");
				if(listPackage!=null){
					for(ShipmentsDTO dto:listPackage){
						if(dto!=null){
							if(dto.getPackageStatus().equals("Drafted")){
								dto.setPackageStatus("Picked");
							}
						}
					}
				}
				//printPickList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Struts2Util.getSession().removeAttribute("Picklist");
		//return packingDetail();
		return null;
	}
	
	/**
	 * 第一个打印
	 *
	 */
	@SuppressWarnings("static-access")
	public void print() throws Exception{
		String lotNos = Struts2Util.getParameter("lotNos");
		String packageIds = Struts2Util.getParameter("packageIds");
		HttpURLConnection con = null;
		HttpsURLConnection connection =null;
		URL url = null;
		Process p = null;
		String cmd = "html2pdf";
		String newip = this.hostip.getLocalIP();
		System.out.print("newIP========================================================="+newip);
		System.out.print("http://"+newip+":8080/scm/shipping!printPickList.action");
		try {
			String sessionid = Struts2Util.getRequest().getSession().getId();
			System.out.println("sessionid==" + sessionid);
			String port = "";
			if (Struts2Util.getRequest().getServerPort() != 80) {
				port = ":" + Struts2Util.getRequest().getServerPort();
			}
			if (Struts2Util.getRequest().getServerPort() ==443) {
				port = ":8443";
			}
			
			String basePath = Struts2Util.getRequest().getScheme() + "://"
					+ Struts2Util.getRequest().getServerName() + port
					+ Struts2Util.getRequest().getContextPath() + "/";
			url = new URL(basePath+"shipping!printPickList.action?packageIds="+packageIds+"&shipmentId="+this.shipmentId+"&lotNos="+lotNos);
			if(basePath.startsWith("https://")) {
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

				sslContext.init(null, tm, new java.security.SecureRandom());

				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				
				        
				
				HostnameVerifier hv = new HostnameVerifier()

				{

				      public boolean verify(String urlHostName, SSLSession session)

				      {

				             System.out.println("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());

				             return true;

				      }

				};

				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				connection = (HttpsURLConnection) url
				.openConnection();
				connection.setSSLSocketFactory(ssf); 
				connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
 
				connection.connect();
			} else {
				con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
				con.connect();
			}
			int size = 0;
			byte[] buf = new byte[1024];
			BufferedInputStream bis = null;
			if(con!=null) {
				bis = new BufferedInputStream(con
						.getInputStream());
			} else {
				bis = new BufferedInputStream(connection
						.getInputStream());
			}
			StringBuffer strb = new StringBuffer();
			while ((size = bis.read(buf)) != -1) {
				strb.append(new String(buf, 0, size));
			}
			FileWriter writer = null;
			File file = new File("/tmp/printPick.html");
			writer = new FileWriter(file);
			writer.write(strb.toString());
			System.out.print("writer==" + writer);
			writer.flush();
			writer.close();
			System.out.println("打印页面的内容==" + strb.toString());
			ProcessBuilder pb = new ProcessBuilder(cmd, "/tmp/printPick.html",
					"/tmp/printPick.pdf");
			pb.redirectErrorStream(true);
			p = pb.start();
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ "PrintPickList" + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(
					"/tmp/printPick.pdf");
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
			bis.close();
			if(con!=null) {
				con.disconnect();
			} else  {
				connection.disconnect();
			}
			
		} catch (Exception e) {
			if(p != null)
				p.destroy();
			System.out.println("Error exec!");
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail()+"\n"+exDTO.getAction());
			e.printStackTrace();
		}
		
	}
	

	public String searchPrintShipPackages() {
		try {
			String trackingNo[] = Struts2Util.getRequest().getParameterValues(
					"trackingNo");
			String shipMethod[] = Struts2Util.getRequest().getParameterValues(
					"shipMethod");
			String packageId[] = Struts2Util.getRequest().getParameterValues(
					"packageId");
			//String packageIds = Struts2Util.getRequest().getParameter("packageId");
			this.printShipPackageDTOList = new ArrayList<ShipPackageDTO>();
			printShipPackageList = this.shippingService.getShipPackagesBypackageIds(packageId);
			if(printShipPackageList!=null){
				for(ShipPackage shipPackage:printShipPackageList){
                    String flag = "Y".equals(shipPackage.getCiInsuranceFromorder())? "Y" : "N";
					ShipPackageDTO dto = new ShipPackageDTO();
					dto = this.dozer.map(shipPackage, ShipPackageDTO.class);
					Integer qty = 0;
                    Double insureValue = 0.0;
					//qty = this.shippingService.getLineQutByPackage(shipPackage.getPackageId());
					List<ShipPackageLines> shipPackageLineses = this.shippingService.getLineQutByPackage(shipPackage.getPackageId());
                    if (shipPackageLineses != null&&!shipPackageLineses.isEmpty()) {
                        for (ShipPackageLines line : shipPackageLineses) {
                            if (line.getQuantity() != null) {
                                qty += line.getQuantity();
                                insureValue = Arith.add(insureValue, line.getQuantity() * line.getUnitPrice());
                            }
                        }
                        /*
                         * 当package的same as item name选项勾选了以后，这个package的commercial invoice文档中的description，
                         * 就取当前package中第一个item在order processing模块的item details tab中的description值；
                         * 如果没有勾选，就取当前选中的下拉列表中的description的值。
                         */
                        if(dto.getCiItemDescFromorder()!=null&&dto.getCiItemDescFromorder().equals("Y")){
                        	OrderItem orderItem = this.shippingService.getOrderItem(shipPackageLineses.get(0).getOrderNo(), shipPackageLineses.get(0).getItemNo());
                        	dto.setCiItemDesc(orderItem.getShortDesc());
                        }
                    }else{
                    	/*
                    	 * 如果没line
                    	 */
                    	continue;
                    }
                   
                    
                    //qty乘以item的unit price（order processing的item列表中有unit price列）；
                    if("Y".equals(flag)){
                        dto.setInsuranceValue(insureValue);
                    }/*else{
                        dto.setInsuranceValue(10.00);
                    }*/
					dto.setQuty(qty);
					if(dto.getInsuranceValue()!=null&&dto.getInsuranceValue()>0&&qty>0){
						dto.setUnitvalue(Arith.div(dto.getInsuranceValue(),qty));
					}else{
						dto.setUnitvalue(0.0d);
					}
                    //包裹中的Same as item name选项
                    if("Y".equals(dto.getCiItemDescFromorder())){
                        dto.setCiItemDesc(shipPackageLineses.size() > 0 ? shipPackageLineses.get(0).getShortDesc() : "");
                    }
                    if(dto.getInsuranceValue()!=null&&dto.getAdtlCustomerCharge()!=null){
                    	  dto.setTotalInvoiceValue(Arith.add(dto.getInsuranceValue(), dto.getAdtlCustomerCharge()));
                    }else{
                    	dto.setTotalInvoiceValue(0.0);
                    }
                    if( dto.getShipmentDate()==null){
                    	Calendar cal = Calendar.getInstance();
                        dto.setShipmentDate(cal.getTime());
                    }
                    if(dto.getTrackingNo()==null||dto.getTrackingNo().equals("")){
                    	for(int i=0;i<packageId.length;i++){
                    		if(dto.getPackageId().toString().equals(packageId[i])){
                    			dto.setTrackingNo(trackingNo[i]);
                    		}
                    	}
                    }
					printShipPackageDTOList.add(dto);
				}
			}

		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "search_ship_packages";
	}

	@SuppressWarnings("static-access")
	public void printShipPackages() throws Exception {
		String packageIds[] = Struts2Util.getRequest().getParameterValues(
				"packageId");
		// 打印海关发票
		if (packageIds != null && packageIds.length > 0) {
			StringBuffer sbf = new StringBuffer();
			for (String pakgId : packageIds) {
				sbf.append(pakgId).append(",");
			}
			HttpURLConnection con = null;
			HttpsURLConnection connection =null;
			URL url = null;
			Process p = null;
			String cmd = "html2pdf";
			String newip = this.hostip.getLocalIP();
			try {
				String sessionid = Struts2Util.getRequest().getSession().getId();
				System.out.println("sessionid==" + sessionid);
				String port = "";
				
				if (Struts2Util.getRequest().getServerPort() != 80) {
					port = ":" + Struts2Util.getRequest().getServerPort();
				}
				if (Struts2Util.getRequest().getServerPort() ==443) {
					port = ":8443";
				}
				String basePath = Struts2Util.getRequest().getScheme() + "://"
						+ Struts2Util.getRequest().getServerName() + port
						+ Struts2Util.getRequest().getContextPath() + "/";
				url = new URL(basePath+"shipping!searchPrintShipPackages.action?packageId="+sbf.toString().substring(0, sbf.toString().length()-1));
				if(basePath.startsWith("https://")) {
					// 创建SSLContext对象，并使用我们指定的信任管理器初始化
					TrustManager[] tm = { new MyX509TrustManager() };
					SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

					sslContext.init(null, tm, new java.security.SecureRandom());

					// 从上述SSLContext对象中得到SSLSocketFactory对象
					SSLSocketFactory ssf = sslContext.getSocketFactory();
					
					        
					
					HostnameVerifier hv = new HostnameVerifier()

					{

					      public boolean verify(String urlHostName, SSLSession session)

					      {

					             System.out.println("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());

					             return true;

					      }

					};

					HttpsURLConnection.setDefaultHostnameVerifier(hv);
					connection = (HttpsURLConnection) url
					.openConnection();
					connection.setSSLSocketFactory(ssf); 
					connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
	 
					connection.connect();
				} else {
					con = (HttpURLConnection) url.openConnection();
					con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
					con.connect();
				}
				int size = 0;
				byte[] buf = new byte[1024];
				BufferedInputStream bis = null;
				if(con!=null) {
					bis = new BufferedInputStream(con
							.getInputStream());
				} else {
					bis = new BufferedInputStream(connection
							.getInputStream());
				}
				StringBuffer strb = new StringBuffer();
				while ((size = bis.read(buf)) != -1) {
					strb.append(new String(buf, 0, size));
				}
				FileWriter writer = null;
				File file = new File(
						"/tmp/printShipPackages.html");
				writer = new FileWriter(file);
				writer.write(strb.toString());
				System.out.print("writer==" + writer);
				writer.flush();
				writer.close();
				System.out.println("打印页面的内容==" + strb.toString());
				ProcessBuilder pb = new ProcessBuilder(cmd,
						"/tmp/printShipPackages.html",
						"/tmp/printShipPackages.pdf");
				pb.redirectErrorStream(true);
				p = pb.start();
				InputStreamReader ir = new InputStreamReader(p.getInputStream());
				LineNumberReader input = new LineNumberReader(ir);
				String line;
				while ((line = input.readLine()) != null) {
					System.out.println(line);
				}
				// 提示下载
				HttpServletResponse response = Struts2Util.getResponse();
				response.setContentType("APPLICATION/DOWNLOAD");
				response.setHeader("Content-Disposition", "attachment; filename="
						+ "PrintCommercialInvoice" + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
				java.io.OutputStream os = response.getOutputStream();
				java.io.FileInputStream fis = new java.io.FileInputStream(
						"/tmp/printShipPackages.pdf");
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) > 0) {
					os.write(b, 0, i);
				}
				fis.close();
				os.flush();
				response.flushBuffer();
				os.close();
				bis.close();
				if(con!=null) {
					con.disconnect();
				} else  {
					connection.disconnect();
				}
			} catch (Exception e) {
				if (p != null) {
					p.destroy();
				}
				System.out.println("Error exec!");
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				Struts2Util.renderText(exDTO.getMessageDetail()+"\n"+exDTO.getAction());
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * viewPackingSlip
	 * 
	 * @return
	 */
	public String viewPackingSlip() {
		Integer userId = SessionUtil.getUserId();
		User user = this.userDao.getById(userId);
		String email = "";
		if(user!=null){
			email = user.getEmail();
		}
		if(Struts2Util.getSession().getAttribute("viewlist")!=null)
			return "viewPackingSlip";
		String orderNo = (String) (Struts2Util.getSession()
				.getAttribute("orderNos"));// "36214"
		// 更新
		shippingService.updateViewStatus(orderNo);
		// 查询
		System.out.println(this.shipmentId);
		Struts2Util.getSession().setAttribute("email", email);
		List<ViewPackingSlipDTO> list = shippingService
				.ViewPackingSlip(orderNo,this.shipmentId);
		Struts2Util.getSession().setAttribute("viewlist", list);
		return "viewPackingSlip";
	}

	/**
	 * 转换PDF功能
	 * 
	 * @throws Exception
	 * @throws DocumentException
	 */
	public String ViewPrint() throws Exception, DocumentException {
		if(Struts2Util.getSession().getAttribute("viewlist")!=null)
			System.out.print("------------------ViewPrint环节------------------");
		else
		{
			try {
				if (!judgeSaveAndStatus("Picked") && !judgeSaveAndStatus("Packed")) {
					String message = "No matches status!";
					Struts2Util.getRequest().setAttribute("message", message);
					return "packingDetail";
				}
				print2();
				List<ShipmentsDTO> listPackage = (List) Struts2Util.getSession().getAttribute("listPackage");
				if(listPackage!=null){
					for(ShipmentsDTO dto:listPackage){
						if(dto!=null){
							if(dto.getPackageStatus().equals("Picked")){
								dto.setPackageStatus("Packed");
							}
						}
					}
				}
				//viewPackingSlip();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Struts2Util.getSession().removeAttribute("viewlist");
		//return packingDetail();
		return null;
	}
	
	
	/**
	 * 第二个打印
	 */
	@SuppressWarnings("static-access")
	public void print2(){
		HttpURLConnection con = null;
		HttpsURLConnection connection =null;
		URL url = null;
		Process p = null;
		String cmd = "html2pdf";
		String newip = this.hostip.getLocalIP();
		System.out.print("newIP========================================================="+newip);
		System.out.print("https://"+newip+"/scm/shipping!viewPackingSlip.action?shipmentId="+this.shipmentId);
		try {
			String sessionid = Struts2Util.getRequest().getSession().getId();
			System.out.println("sessionid==" + sessionid);
			String port = "";
			if (Struts2Util.getRequest().getServerPort() != 80) {
				port = ":" + Struts2Util.getRequest().getServerPort();
			}
			if (Struts2Util.getRequest().getServerPort() ==443) {
				port = ":8443";
			}
			
			String basePath = Struts2Util.getRequest().getScheme() + "://"
					+ Struts2Util.getRequest().getServerName() + port
					+ Struts2Util.getRequest().getContextPath() + "/";
			url = new URL(basePath+"shipping!viewPackingSlip.action?shipmentId="+this.shipmentId);
			if(basePath.startsWith("https://")) {
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

				sslContext.init(null, tm, new java.security.SecureRandom());

				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				
				        
				
				HostnameVerifier hv = new HostnameVerifier()

				{

				      public boolean verify(String urlHostName, SSLSession session)

				      {

				             System.out.println("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());

				             return true;

				      }

				};

				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				connection = (HttpsURLConnection) url
				.openConnection();
				connection.setSSLSocketFactory(ssf); 
				connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
 
				connection.connect();
			} else {
				con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Cookie", "JSESSIONID=" + sessionid);
				con.connect();
			}
			int size = 0;
			byte[] buf = new byte[1024];
			BufferedInputStream bis = null;
			if(con!=null) {
				bis = new BufferedInputStream(con
						.getInputStream());
			} else {
				bis = new BufferedInputStream(connection
						.getInputStream());
			}
			StringBuffer strb = new StringBuffer();
			while ((size = bis.read(buf)) != -1) {
				strb.append(new String(buf, 0, size));
			}
			FileWriter writer = null;
			File file = new File("/tmp/ViewPrint.html");
			writer = new FileWriter(file);
			writer.write(strb.toString());
			System.out.print("writer==" + writer);
			writer.flush();
			writer.close();
			System.out.println("打印页面的内容==" + strb.toString());
			ProcessBuilder pb = new ProcessBuilder(cmd, "/tmp/ViewPrint.html","/tmp/ViewPrint.pdf");
			pb.redirectErrorStream(true);
			p = pb.start();
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ "PrintPackingSlip" + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream(
					"/tmp/ViewPrint.pdf");
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			fis.close();
			os.flush();
			response.flushBuffer();
			os.close();
			bis.close();
			if(con!=null) {
				con.disconnect();
			} else  {
				connection.disconnect();
			}
		} catch (Exception e) {
			if(p != null)
				p.destroy();
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail()+"\n"+exDTO.getAction());
			System.out.println("Error exec!");
		}
	}
	/**
	 * print shipping label页面取消装运功能
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String cancelShipping() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			//Struts2Util.getRequest().setAttribute("m", list2);
			String packageId = Struts2Util.getRequest().getParameter("packageId");
			// 判断packageId是否为空
			if (packageId != null && !packageId.equals("")) {
				ShipPackage sp = this.shippingService
						.getShipPackagesByid(Integer.parseInt(packageId));
				if (sp != null
						&& (sp.getTrackingNo() == null || sp.getTrackingNo()
								.equals(""))) {
					out
							.print("<script>parent.$('#cancel_ship').dialog('close');</script>");
					out.print("<script>alert('No TrackingNo!');</script>");
					out
							.print("<script>parent.location.href=shipments!getPkgInfo.action?packageId="
									+ packageId + ";</script>");
					return null;
				}
				List<ShipPackage> list = new ArrayList<ShipPackage>();
				list.add(sp);
				Struts2Util.getRequest().setAttribute("trlist1", list);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "cancelShipping";
	}

	/**
	 * 保存取消装运信息功能
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String doCancelShipping() throws Exception {
		try {
			// 获取页面数据并处理
			String packageIdStr = Struts2Util.getParameter("packageId");
			String reason = Struts2Util.getRequest().getParameter("reason");
			int packageId = 0;
			if (packageIdStr != null && packageIdStr != "") {
				packageId = Integer.parseInt(packageIdStr);
			}
			PrintWriter out = Struts2Util.getResponse().getWriter();

			// 调用service层方法执行存储并跳回父页面
			this.shippingService.changeShipPackagesByPackageId(packageId,
					reason);
			out
					.print("<script>parent.$('#cancel_ship').dialog('close');</script>");
			out
					.print("<script>parent.location.href=shipments!getPkgInfo.action?packageId="
                            + packageId + ";</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return null;
	}
	
	/**
	 * 传custNo,跳到发送邮件页面.执行去操作
	 * 
	 * @return
	 */
	public String searchCustomer() {
		try {
			String custList = Struts2Util.getRequest().getParameter("custNo");
			String []custNo = custList.split(",");
			int[] intTemp = new int[custNo.length]; 
			customerBusEmailList = new ArrayList<String>();
			for (int i = 0; custNo != null&&i < custNo.length; i++) {
					custList = custNo[i];
					intTemp[i]=Integer.parseInt((custNo[i])); 
					CustomerDTO customerDTO = customerService.getCustomerBase(intTemp[i]);
					customerBusEmailList.add(customerDTO.getBusEmail());
			}
			Struts2Util.getRequest().setAttribute("custNo", custList);
		} catch (Exception ex) {
			sendStatus = false;
			ex.printStackTrace();
		}
		return "send_customer_email";
	}

	/**
	 * 发送邮件
	 * 
	 * @return
	 */
	public String sendEmailToCustomer() {
		PrintWriter out = null;
		String custNo = Struts2Util.getRequest().getParameter("custNo");
		try {
			out = Struts2Util.getResponse().getWriter();
			List<File> uploadList = new ArrayList<File>();
			List<String> uploadContentTypeList = new ArrayList<String>();
			List<String> uploadFileNameList = new ArrayList<String>();
			if(upload!=null){
				uploadList.add(upload);
			}
			if(uploadContentType!=null){
				uploadContentTypeList.add(uploadContentType);
			}
			if(uploadFileName!=null){
				uploadFileNameList.add(uploadFileName);
			}
			//emailContent += "<br/>"+"11232131"+"<br/>lllll";
			System.out.println(emailContent);
			sendStatus = customerService.sendEmailToCustomer(uploadList,
					uploadContentTypeList, uploadFileNameList, Integer.valueOf(custNo),
					customerBusEmail, emailSubject, emailContent);
			out.print("<script>alert('Send Email Success.');</script>");
			out.print("<script>parent.window.location.href='shipping!appFrame.action';</script>");
		} catch (Exception ex) {
			sendStatus = false;
			try {
				out = Struts2Util.getResponse().getWriter();
				out.print("<script>alert('Send Email failure!');parent.$('#print_shipping_label').dialog('close');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		return null;
	}
	


	

	/**
	 * 信用卡银行接口
	 */
	public String credcard() {
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer userId = SessionUtil.getUserId();
		if(userId==null){
			String str = "Don't operation this.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		String orderNo = (String) Struts2Util.getSession().getAttribute("orderNos");
		String chargeAmount = Struts2Util.getParameter("chargeAmount");
		//System.out.println("chargeAmount="+chargeAmount);
		BankCardDTO bank=shippingService.selectForBand(orderNo);
		if(chargeAmount!=null&&!chargeAmount.equals("")){
			bank.setCustomerCharge(Double.valueOf(chargeAmount));
		}
		//System.out.println("cost="+bank.getCustomerCharge());
		if(bank.getCustomerCharge()<0||bank.getCustomerCharge().equals(0.0)){
			String str = "Please don't operation this.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		//System.out.println("=======================信用卡银行接口的环节=======================");
		String action = "ns_quicksale_cc";
		String acctid = "FC14L";
		if(bank.getCountry()!=null&&!bank.getCountry().equals("US")&&!bank.getCountry().equals("CA")){
			acctid = "FCX2U";
		}
		String Accepturl = "http://genscript.com/cgi-bin/ccreceipt.cgi";
		String Declineurl = "http://genscript.com/cgi-bin/ccreceipt.cgi";
		//String ci_memo = "GenScrip OrderID: 2322323";
		//String merchantordernumber = "2322323";
		String ccname = bank.getCardHolder();//"Frank Zhang";
		if(ccname==null||ccname.equals("")){
			String str = "Missing  some information of credit card.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		String ccnum = bank.getCardNo();//"4246315166955516";
		if(ccnum==null||ccnum.equals("")){
			String str = "Missing  some information of credit card.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		String cvv2 = bank.getCvc();//"689";
		if(cvv2==null||cvv2.equals("")){
			String str = "Missing  some information of credit card.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		String amount = bank.getCustomerCharge().toString();//"0.2";
		String expmon =bank.getExprMonth(); //"07";
		if(expmon==null||expmon.equals("")){
			String str = "Missing  some information of credit card.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		String expyear = bank.getExprYear();//"2012";
		if(expyear==null||expyear.equals("")){
			String str = "Missing  some information of credit card.";
			rt.put("message",str);
			Struts2Util.renderJson(rt);
			return null;
		}
		String ci_companyname = "Genscript Corp";
		//String orderNo = Struts2Util.getRequest().getParameter("orderNo");
		//String order_id = "2322323";
		//String user = "ming";
		String Authonly = "1";
		String usepost = "1";
		String ci_billadd1 = bank.getBillTo();
		String ci_billcity = bank.getCity();
		String ci_billstate = bank.getState();
		String ci_billcountry = bank.getCountry();
		String ci_billzip = bank.getZipCode();
		StringBuffer url = new StringBuffer("https://trans.merchantpartners.com/cgi-bin/process.cgi");
		StringBuffer param = new StringBuffer();
		try {
			System.out.println("带参数的url=============================================="+url);
			//user="+user+"&order_id="+order_id+"&merchantordernumber="+merchantordernumber+"&ci_memo="+ci_memo+"&
			param.append("action="+action+"&acctid="+acctid+"&Accepturl="+Accepturl+"&Declineurl="+Declineurl+"&ccname="+ccname+"&ccnum="+ccnum+"&cvv2="+cvv2+"&amount="+amount+"&expmon="+expmon+"&expyear="+expyear+"&ci_companyname="+ci_companyname+"&Authonly="+Authonly+"&usepost="+usepost+"&ci_billadd1="+ci_billadd1+"&ci_billcity="+ci_billcity+"&ci_billstate="+ci_billstate+"&ci_billcountry="+ci_billcountry+"&ci_billzip="+ci_billzip);
			System.out.println(param.toString());
			returnStr = this.postReq(url.toString(),param.toString());
			System.out.println("returnStr="+returnStr);
			if(returnStr!=null&&!returnStr.equals("")){
				System.out.println("returnStr="+returnStr);
				String[] retruns = returnStr.split("Accepted");
				if(retruns.length>1){
					Date date = new Date();
					for(ShipPackage shipPackage:bank.getPackageIds()){
						//System.out.println("shipPackage="+shipPackage.getPackageId());
						ShippingChargeLog log = new ShippingChargeLog();
						log.setChargeAccNo(ccnum);
						//System.out.println(ccnum);
						log.setChargeAccType(bank.getType());
						//System.out.println(bank.getType());
						log.setChargeAmt(shipPackage.getCustomerCharge().doubleValue());
						//System.out.println(shipPackage.getCustomerCharge());
						log.setChargeDate(date);
						//System.out.println(date);
						log.setChargedBy(userId);
						//System.out.println(userId);
						log.setCurrency(bank.getCurrency());
						//System.out.println(bank.getCurrency());
						log.setCustNo(bank.getCusNo());
						//System.out.println(bank.getCusNo());
						log.setPackageId(shipPackage.getPackageId());
						//System.out.println(shipPackage.getPackageId());
						log.setReceiptAccNo("Genscript Corp");
						//System.out.println("Genscript Corp");
						this.shippingService.saveShippingChargLogDao(log);
					}
					returnStr = "Charge credit card successfully.";
				}else{
					returnStr = "Charge Credit Card ERROR.";
				}
				
			}
			System.out.println("======================"+returnStr+"======================");
			rt.put("message",returnStr);
			Struts2Util.renderJson(rt);
		} catch (Exception e) {
			returnStr = "Error";
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 取消trackingNo接口方法
	 * @return
	 */
	public String cancelTraking() {
		PrintWriter out = null;
		System.out.print("======================取消trackingNo接口的环节======================");
		try {
			out = Struts2Util.getResponse().getWriter();
			String trackingNo = Struts2Util.getParameter("trackingNo");
			String[] trackingNos = trackingNo.split(",");
			//String trackingNo[] = {"800029815024591","800029815024592"};
			for(int i=0; i < trackingNos.length; i++){
				System.out.println(trackingNos[i]);
				StringBuffer url = new StringBuffer("https://www.genscriptcorp.com/fedexapi/DeleteShipment.php?trackingNumber="+trackingNos[i]);
				System.out.println("url 1="+url);
				String returnStr1 = this.shipmentService.getReq(url.toString());
				String[] returnStrs = returnStr1.split("success");
				if(returnStrs.length>1){
					this.shipPackageService.updatePackageByTrackingNumber(trackingNos[i]);
					returnStr +="<br />trackingNumber:"+ trackingNos[i]+"<br/>cancel successful <br/>Transaction processed successfully.<br/>";
				}else{
					returnStr +="<br />trackingNumber:"+ trackingNos[i]+" "+"ERROR.<br/>";
				}
				System.out.println("======================"+returnStr+"======================");
			}
			out.print("<script>alert('"+returnStr+"');</script>");
			out.print("<script>parent.$('#print_shipping_label').dialog('close');</script>");
		} catch (Exception e) {
			returnStr = "Error";
			e.printStackTrace();
		}
		return "str";
	}	
	/************************private method*****************************/
	/**
	 * get请求
	 */
	

	/**
	 * post请求
	 */
	private String postReq(String url,String param) throws Exception{
		String resultStr = null;
		StringBuffer readOneLineBuff = new StringBuffer(); 
		URL postUrl = new URL(url);   
        // 打开连接   
		if(url.startsWith("https://")) {
			HttpsURLConnection connection = (HttpsURLConnection)postUrl.openConnection(); 
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("Content-Type","text/html;charset=utf-8"); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.write(param.getBytes());   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;    
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		} else {
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);     
		    connection.setDoInput(true);   
		    connection.setRequestMethod("POST");
		    connection.setUseCaches(false);
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("Content-Type","text/html"); 
		    connection.connect();  
		    DataOutputStream out = new DataOutputStream(connection   
	                .getOutputStream()); 
		    out.writeBytes(param);   
	        out.flush();   
	        out.close();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(   
	                connection.getInputStream()));   
	        String line=null;   
	        while ((line = reader.readLine()) != null) {   
	            readOneLineBuff.append(line);   
	        } 
	        connection.disconnect();
		}    
        resultStr = readOneLineBuff.toString();
		return resultStr;
	}
	
	/*
	 * print shipping doc
	 */
	@SuppressWarnings("unchecked")
	public String shippingDocument() throws Exception{
		String orderNo = Struts2Util.getRequest().getParameter("orderNo");
		String itemNo = Struts2Util.getRequest().getParameter("itemNo");
		
		if(orderNo!=null&&!orderNo.equals("")){
			String[] orderNoList = orderNo.split(",");
			
			if(itemNo!=null&&!itemNo.equals("")){
				String[] itemNoList = itemNo.split(",");
				Integer[] orderNoI = new Integer[orderNoList.length];
				Integer[] itemNoI = new Integer[itemNoList.length];
				for(int i=0;i<orderNoList.length;i++){
					Integer cnSo = this.shippingService.getCNSOByUSSO(Integer.parseInt(orderNoList[i]));
					if(cnSo!=null){
						orderNoI[i]=cnSo;
						itemNoI[i]=Integer.parseInt(itemNoList[i]);
					}
				}
				System.out.println(orderNo+"  "+itemNo);
				this.shippingDocList=this.shippingService.getDocumentList(orderNoI, itemNoI);
				this.shippingProductDocList=this.shippingService.getProductDocument(orderNoList,itemNoList);

			}else{
				Integer cnSo = this.shippingService.getCNSOByUSSO(Integer.parseInt(orderNo));
				if(cnSo!=null){
					this.shippingDocList=this.shippingService.getDocumentList(cnSo);
				}else{
					this.shippingDocList = null;
				}
				
			}
		}else{
			String packageId = Struts2Util.getRequest().getParameter("packageId");
			if(packageId!=null&&!packageId.equals("")){
				List<ShipPackageLines> lineList = this.shipPackageLineService.findPackageLineList(packageId);
				if(lineList!=null&&!lineList.isEmpty()){
					Integer[] orderNoI = new Integer[lineList.size()];
					Integer[] itemNoI = new Integer[lineList.size()];
					String[] orderNoList = new String[lineList.size()];
					String[] itemNoList = new String[lineList.size()];
					for(int i=0;i<lineList.size();i++ ){
						Integer cnSo = this.shippingService.getCNSOByUSSO(lineList.get(i).getOrderNo());
						orderNoList[i] = lineList.get(i).getOrderNo().toString();
						itemNoList[i] = lineList.get(i).getItemNo().toString();
						if(cnSo!=null){
							orderNoI[i]=cnSo;
							itemNoI[i] = lineList.get(i).getItemNo();
						}
					}
					this.shippingDocList=this.shippingService.getDocumentList(orderNoI, itemNoI);
					
					this.shippingProductDocList=this.shippingService.getProductDocument(orderNoList,itemNoList);
					
				}
			}
		}
		if(shippingDocList!=null&&!shippingDocList.isEmpty()){
			 HashSet h = new HashSet(shippingDocList);   
			 shippingDocList.clear();   
			 shippingDocList.addAll(h);   

		}
		if(shippingProductDocList!=null&&!shippingProductDocList.isEmpty()){
			 HashSet h = new HashSet(shippingProductDocList);   
			 shippingProductDocList.clear();   
			 shippingProductDocList.addAll(h);   

		}
		if(this.isPrint!=null&&this.isPrint.equals("100")){
			//这个IF内没有什么作用了。ELSE中页面还用到。
/*			Map<String, Object> rt = new HashMap<String, Object>();
			if(this.shippingDocList!=null&&!this.shippingDocList.isEmpty()){
				for(ManuDocument manuDocument:shippingDocList){
					String ret = this.shippingService.printPanel(manuDocument.getFilePath(), manuDocument.getIsOldProdctFile());
					if(ret.equals("nonPrinter")){
						rt.put("message","nonPrinter");
						Struts2Util.renderJson(rt);
						return null;
					}
				}
			}
			if(this.shippingProductDocList!=null&&!this.shippingProductDocList.isEmpty()){
				for(ShipDocDTO shipDocDTO:shippingProductDocList){
					if(shipDocDTO.getManuDoc()!=null){
						for(ManuDocument manuDocument:shipDocDTO.getManuDoc()){
							String ret = this.shippingService.printPanel(manuDocument.getFilePath(), manuDocument.getIsOldProdctFile());
							if(ret.equals("nonPrinter")){
								rt.put("message","nonPrinter");
								Struts2Util.renderJson(rt);
								return null;
							}
						}
					}
				}
			}
			if((this.shippingDocList!=null&&!this.shippingDocList.isEmpty())||(this.shippingProductDocList!=null&&!this.shippingProductDocList.isEmpty())){
				rt.put("message",SUCCESS);
			}else{
				rt.put("message","NONE");
			}
			Struts2Util.renderJson(rt);*/
			return null;
		}else{
			String productDocument_orderno = "ProductDocument_";
			if(orderNo!=null&&!orderNo.equals("")){
				String[] orderNoList = orderNo.split(",");
				if(orderNoList[0]!=null){
					productDocument_orderno +=orderNoList[0];
					System.out.println(productDocument_orderno+">>>>>>>>>>>>>>>>>>>>>>>>>");
				}
			}
			String productDoc = this.fileService.getUploadPath()+SessionUtil.generateTempId();
			File file = new File(productDoc);
			file.mkdir();
				//FilelUtil.deleteAllFile(productDoc);
			File fileZip = new File(this.fileService.getUploadPath() + productDocument_orderno
						+ ".zip");
			if (fileZip.exists()) {// 如果存在该文件
				fileZip.delete();// 删除

			}
			
			//System.out.println(">>>>>>>>>>>1");
			if(this.shippingDocList!=null&&!this.shippingDocList.isEmpty()){
				for(ManuDocument manuDocument:shippingDocList){
					if(manuDocument.getFilePath()!=null&&manuDocument.getDocName()!=null&&!manuDocument.getFilePath().equals("")&&!manuDocument.getDocName().equals("")){
						this.shippingService.copyToPath(manuDocument.getFilePath(), manuDocument.getIsOldProdctFile(), productDoc);
					}
					
				}
			}
			//System.out.println(">>>>>>>>>>>12");
			if(this.shippingProductDocList!=null&&!this.shippingProductDocList.isEmpty()){
				for(ShipDocDTO shipDocDTO:shippingProductDocList){
					if(shipDocDTO.getManuDoc()!=null){
						for(ManuDocument manuDocument:shipDocDTO.getManuDoc()){
							if(manuDocument.getFilePath()!=null&&manuDocument.getDocName()!=null&&!manuDocument.getFilePath().equals("")&&!manuDocument.getDocName().equals("")){
								this.shippingService.copyToPath(manuDocument.getFilePath(), manuDocument.getIsOldProdctFile(), productDoc);
							}
						}
					}
				}
			}
			//System.out.println(">>>>>>>>>>>13");
			try {
				ZipUtil.zipFile(productDoc, this.fileService.getUploadPath()+productDocument_orderno+".zip");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletActionContext.getRequest().setAttribute("zipDoc",
					productDocument_orderno);
		}
		ServletActionContext.getRequest().setAttribute("orderNo",
				orderNo);
		ServletActionContext.getRequest().setAttribute("itemNo",
				itemNo);
		return "shippingDoc";
	}
	
	/*
	 * printAll 打印。
	 */
	public String printShippingDoc(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		Map<String, Object> rt = new HashMap<String, Object>();
		String message = "success";
		String docs = Struts2Util.getRequest().getParameter("docs");
		if(docs!=null&&!docs.equals("")){
			String[]  docStrs = docs.split(";");
			for(String docStr : docStrs){
				String[] doc = docStr.split(",");
				if(doc.length!=2){
					message = this.shippingService.printPanel(doc[0], null);
				}else{
					message = this.shippingService.printPanel(doc[0], doc[1]);
				}
				
			}
		}else{
			message = "nonFile";
		}
		if(orderItemId!=null&&!orderItemId.equals("")){
			this.purchaseOrderService.updatePurchaseItemFileDownloaded(orderItemId, "0");
		}
		rt.put("message",message);
		return null;
	}
	
	private List<ShipmentsDTO> getNewShipmentDto(List<ShipmentsDTO> list){
		if(list == null)
			list = new ArrayList<ShipmentsDTO>();
		for(int i=0;i<list.size();i++){
			ShipmentsDTO dto = list.get(i);
			List<ShipPackage> listPackage = this.shippingService.getPackageId(dto.getPackageId());
			if(listPackage == null || listPackage.size() == 0){
				return list;
			}
			ShipPackage packages = listPackage.get(0);
			if(packages != null)
				dto.setPackageStatus(packages.getStatus());
		}
		return list;
	}
	
	/*
	 * 更改ship_packages status 状态
 	 */
	public String changeShipPackagesStatus(){
		Map<String, Object> rt = new HashMap<String, Object>();
		String type = Struts2Util.getParameter("type");
		if(this.shipmentId!=null){
			this.shippingService.setPackageStatus(this.shipmentId,type);
		}
		rt.put("message",SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/*
	 * pick item list
	 */
	public String picksItems(){
		String packageIds = Struts2Util.getParameter("packageIds");
		List<PrintPickListsDTO> list = shippingService.printPickLists(shipmentId,packageIds);
		//List<Storage> storageList = this.storageService.findStorage("1");
		Struts2Util.getSession().setAttribute("packageIds", packageIds);
		Struts2Util.getSession().setAttribute("Picklists", list);
		//Struts2Util.getSession().setAttribute("storageList", storageList);
		return "picksItems";
	}
	
	public String searchLotNo() throws DatatypeConfigurationException{
		Map<String, Object> rt = new HashMap<String, Object>();
		String packageIds = Struts2Util.getParameter("packageIds");
		List<ShippingPackageLinesDTO> list = shippingService.searchShipPackageLineOrderItem(packageIds);
		if(packageIds!=null&&!packageIds.equals("")){
			if(list!=null&&!list.isEmpty()){
				List<PartItem> partItemList = new ArrayList<PartItem>();
				for(ShippingPackageLinesDTO dto : list){
					if(dto.getStatus().equals("Drafted")&&dto.getType().equals("PRODUCT")){
						//System.out.println(dto.getCatalogNo());
						PartItem partItem = new PartItem();
						String company = dto.getCompany();
						if(company.equals("1")){
							company = "GSUS";
						}
						if(company.equals("2")){
							company = "GSNJ";
						}
						if(company.equals("3")){
							company = "GSPK";
						}
						if(company.equals("4")){
							company = "GSHK";
						}
						partItem.setCompany(company);
						partItem.setPartNum(dto.getCatalogNo());
						partItemList.add(partItem);
						
/*						//String lotNos = erpSalesOrderService.getLotNumber(dto.getQty(),dto.getCatalogNo(),company);
						//System.out.println("lotNos="+lotNoList);
						List<StorageLocationDTO> storageLocationList  = erpSalesOrderService.getPartStorageLocationList(dto.getCatalogNo(),company);
						System.out.println(dto.toString());
						String lotNoValue = "";
						BigDecimal inventory = new BigDecimal(0.0);
						if(storageLocationList!=null&&!storageLocationList.isEmpty()){
							for(StorageLocationDTO storageLocationDTO : storageLocationList){
								if(storageLocationDTO.getLotNoList()!=null&&!storageLocationDTO.getLotNoList().isEmpty()){
									if(lotNoValue.equals("")){
						        		lotNoValue = storageLocationDTO.getStorageLocation();
						        		inventory = storageLocationDTO.getLotNoList().get(0).getValue();
						        	}else{
						        		for(LotNoDTO lotNoDTO : storageLocationDTO.getLotNoList()){
						        			int i=inventory.compareTo(lotNoDTO.getValue());
						        			if(i<0){
						        				lotNoValue = storageLocationDTO.getStorageLocation();
						        				inventory = lotNoDTO.getValue();
						        			}
						        		}
						        	}
								}
								
							}
						    
						}
						dto.setLotNoValue(lotNoValue);
						dto.setInventory(inventory);
						//System.out.println("storageLocation="+storageLocation);
						//dto.setStorageLocation(storageLocation);
						//dto.setLotNo("11");
						dto.setStorageLocationList(storageLocationList);*/
					}
				}
				if(partItemList!=null&&!partItemList.isEmpty()){
					ArrayOfInventoryQtyAdjItem item = getInvoicePaymentListJob.searchInventoryQtyAdjItem(partItemList);
					if(item!=null&&item.getInventoryQtyAdjItem()!=null&&!item.getInventoryQtyAdjItem().isEmpty()){
						List<InventoryQtyAdjItem> itemList = item.getInventoryQtyAdjItem();
						for(InventoryQtyAdjItem inventoryItem : itemList){
							String storageLocateonValue =  inventoryItem.getWarehouseCode()+"-"+inventoryItem.getBinNum();
							LotNoDTO lotDTO = new LotNoDTO();
				        	lotDTO.setLotNo(inventoryItem.getLotNum());
				        	lotDTO.setValue(inventoryItem.getOnhandQty());
				        	for(ShippingPackageLinesDTO dto : list){
				        		if(dto.getCatalogNo().equals(inventoryItem.getPartNum())){
				        			String isAdd = "1";
				                	if(dto.getStorageLocationList()==null||dto.getStorageLocationList().isEmpty()){
				                		dto.setStorageLocationList(new ArrayList<StorageLocationDTO>());
				                	}else{
				                		for(StorageLocationDTO storageLocationDTO : dto.getStorageLocationList()){
				                			if(storageLocationDTO.getStorageLocation().equals(storageLocateonValue)){
				                				String isAddLotNo = "1";
				                				for(LotNoDTO lotNoDto:storageLocationDTO.getLotNoList()){
				                					if(lotNoDto.getLotNo().equals(lotDTO.getLotNo())){
				                						isAddLotNo = "0";
				                					}
				                				}
				                				if(isAddLotNo.equals("1")){
				                					storageLocationDTO.getLotNoList().add(lotDTO);
				                				}
				                				isAdd= "0";
				                			}
				                		}
				                	}
				                	
				                	if(isAdd.equals("1")){
				                		StorageLocationDTO storageLocationDTO = new StorageLocationDTO();
				                		storageLocationDTO.setStorageLocation(storageLocateonValue);
				                		storageLocationDTO.setLotNoList(new ArrayList<LotNoDTO>());
				                		storageLocationDTO.getLotNoList().add(lotDTO);
				                		dto.getStorageLocationList().add(storageLocationDTO);
				                	}
				        		}
				        	}
						}
						for(ShippingPackageLinesDTO dto : list){
							String lotNoValue = "";
							BigDecimal inventory = new BigDecimal(0.0);
							if(dto.getStorageLocationList()!=null&&!dto.getStorageLocationList().isEmpty()){
								for(StorageLocationDTO storageLocationDTO : dto.getStorageLocationList()){
									if(storageLocationDTO.getLotNoList()!=null&&!storageLocationDTO.getLotNoList().isEmpty()){
										if(lotNoValue.equals("")){
							        		lotNoValue = storageLocationDTO.getStorageLocation();
							        		inventory = storageLocationDTO.getLotNoList().get(0).getValue();
							        	}else{
							        		for(LotNoDTO lotNoDTO : storageLocationDTO.getLotNoList()){
							        			int i=inventory.compareTo(lotNoDTO.getValue());
							        			if(i<0){
							        				lotNoValue = storageLocationDTO.getStorageLocation();
							        				inventory = lotNoDTO.getValue();
							        			}
							        		}
							        	}
									}
									
								}
							    
							}
							dto.setLotNoValue(lotNoValue);
							dto.setInventory(inventory);
							//System.out.println("storageLocation="+storageLocation);
							//dto.setStorageLocation(storageLocation);
							//dto.setLotNo("11");
							//dto.setStorageLocationList(storageLocationList);*/
						}
					}
				}
			}
			
			
		}
		rt.put("list",list);
		rt.put("message","SUCCESS");
		Struts2Util.renderJson(rt);
		return null;
	}
	
	public String editAddress(){
		this.countryList = this.pbCountryDao.getAll();
		return "edit_address";
	}

    //add by zhanghuibin note
    public String viewOrderNote () {
		try {
            String noteKey = ServletActionContext.getRequest().getParameter("noteKey");
            String searchNoteType = ServletActionContext.getRequest().getParameter("searchNoteType");
			orderNote = this.shippingService.getNotes(Integer.valueOf(noteKey), searchNoteType);
		} catch (Exception ex) {
			return "view_order_note";
		}
		return "view_order_note";
	}
	
	/*
	 * 获取PrintPickList pdf
	 
	public String printPickListPdf(){
		List<PrintPickListsDTO> list = shippingService.printPickList(this.shipmentId);
		Struts2Util.getSession().setAttribute("Picklist", list);
		return "printPickListPdf";
	}*/
   
	public List<PbDropdownListOptions> getAllShippingRule(){
		return this.publicService.getDropdownList("SHIPPING_RULE");
	}
	/*******************************getter(),setter()********************************/
	
	public String ship() {
		return "ship";
	}

	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public Page<ShipmentsDTO> getPageShipmentsDTO() {
		return pageShipmentsDTO;
	}

	public void setPageShipmentsDTO(Page<ShipmentsDTO> pageShipmentsDTO) {
		this.pageShipmentsDTO = pageShipmentsDTO;
	}

	public ShipmentsDTO getSDto() {
		return sDto;
	}

	public void setSDto(ShipmentsDTO sDto) {
		this.sDto = sDto;
	}

	public ShipmentsSrchDTO getShipschDTO() {
		return shipschDTO;
	}

	public void setShipschDTO(ShipmentsSrchDTO shipschDTO) {
		this.shipschDTO = shipschDTO;
	}

	public ShipPackageDTO getPDto() {
		return pDto;
	}

	public void setPDto(ShipPackageDTO dto) {
		pDto = dto;
	}

	public Page<ShipPackageDTO> getPagePackagesDTO() {
		return pagePackagesDTO;
	}

	public void setPagePackagesDTO(Page<ShipPackageDTO> pagePackagesDTO) {
		this.pagePackagesDTO = pagePackagesDTO;
	}

	public ShipPackageLineService getShipPackageLineService() {
		return shipPackageLineService;
	}

	public void setShipPackageLineService(
			ShipPackageLineService shipPackageLineService) {
		this.shipPackageLineService = shipPackageLineService;
	}

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}

	public ShipMethodService getShipMethodService() {
		return shipMethodService;
	}

	public void setShipMethodService(ShipMethodService shipMethodService) {
		this.shipMethodService = shipMethodService;
	}

	public String[] getTrakingNo() {
		return trakingNo;
	}

	public void setTrakingNo(String[] trakingNo) {
		this.trakingNo = trakingNo;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}

	public List<String> getCustomerBusEmailList() {
		return customerBusEmailList;
	}

	public void setCustomerBusEmailList(List<String> customerBusEmailList) {
		this.customerBusEmailList = customerBusEmailList;
	}

	public List<ShipPackage> getPrintShipPackageList() {
		return printShipPackageList;
	}

	public void setPrintShipPackageList(List<ShipPackage> printShipPackageList) {
		this.printShipPackageList = printShipPackageList;
	}

	public Integer getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	public List<CountryDTO> getCountry(){
		return this.publicService.getCountryList();
	}

	public List<ShipPackageDTO> getPrintShipPackageDTOList() {
		return printShipPackageDTOList;
	}

	public void setPrintShipPackageDTOList(
			List<ShipPackageDTO> printShipPackageDTOList) {
		this.printShipPackageDTOList = printShipPackageDTOList;
	}

	public String getCreateCommercialInvoice() {
		return createCommercialInvoice;
	}

	public void setCreateCommercialInvoice(String createCommercialInvoice) {
		this.createCommercialInvoice = createCommercialInvoice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	
	public Integer getuserId(){
		return SessionUtil.getUserId();
	}

	public List<ManuDocument> getShippingDocList() {
		return shippingDocList;
	}

	public void setShippingDocList(List<ManuDocument> shippingDocList) {
		this.shippingDocList = shippingDocList;
	}
	public String getCustomerBusEmail() {
		return customerBusEmail;
	}

	public void setCustomerBusEmail(String customerBusEmail) {
		this.customerBusEmail = customerBusEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadContentType(){
		return this.uploadContentType;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadFileName(){
		return this.uploadFileName;
	}

	public List<ShipMethod> getShipMenthodList() {
		return shipMenthodList;
	}

	public void setShipMenthodList(List<ShipMethod> shipMenthodList) {
		this.shipMenthodList = shipMenthodList;
	}

	public ShipPackage getSps() {
		return sps;
	}

	public void setSps(ShipPackage sps) {
		this.sps = sps;
	}

	public String getShippingLable() {
		return shippingLable;
	}

	public void setShippingLable(String shippingLable) {
		this.shippingLable = shippingLable;
	}

    public InstructionDTO getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(InstructionDTO orderNote) {
        this.orderNote = orderNote;
    }

    public HashMap<String, Object> getNotesMap() {
        if(notesMap == null){
           notesMap = (HashMap<String, Object>)Struts2Util.getSession().getAttribute("noteMap");
        }
        return notesMap;
    }

    public void setNotesMap(HashMap<String, Object> notesMap) {
        this.notesMap = notesMap;
    }

	public List<ShipDocDTO> getShippingProductDocList() {
		return shippingProductDocList;
	}

	public void setShippingProductDocList(List<ShipDocDTO> shippingProductDocList) {
		this.shippingProductDocList = shippingProductDocList;
	}

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public String getIsShippingProductDoc() {
		return isShippingProductDoc;
	}

	public void setIsShippingProductDoc(String isShippingProductDoc) {
		this.isShippingProductDoc = isShippingProductDoc;
	}

	public List<PbCountry> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<PbCountry> countryList) {
		this.countryList = countryList;
	}

	public String getNotReceivedItem() {
		return notReceivedItem;
	}

	public void setNotReceivedItem(String notReceivedItem) {
		this.notReceivedItem = notReceivedItem;
	}

	public String getExpiredItem() {
		return expiredItem;
	}

	public void setExpiredItem(String expiredItem) {
		this.expiredItem = expiredItem;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

    
}