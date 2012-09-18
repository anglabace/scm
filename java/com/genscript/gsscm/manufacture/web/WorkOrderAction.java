package com.genscript.gsscm.manufacture.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.HostIpUtil;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.MyX509TrustManager;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.inventory.entity.ReceivingLog;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.inventory.entity.Storage;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.inventory.service.ReceivingLogService;
import com.genscript.gsscm.inventory.service.ReservationService;
import com.genscript.gsscm.inventory.service.StorageService;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.WoBatchError;
import com.genscript.gsscm.manufacture.entity.WoBatche;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.service.WorkOrderService;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.purchase.dto.PurchaseOrderDTO;
import com.genscript.gsscm.purchase.dto.PurchaseOrderItemDTO;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.service.PurchaseOrderItemService;
import com.genscript.gsscm.purchase.service.PurchaseOrderService;
import com.genscript.gsscm.purchase.service.ReadReceiveXmlService;
import com.genscript.gsscm.purchase.service.VendorService;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageErrors;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.shipment.service.ShipPackageLineService;
import com.genscript.gsscm.shipment.service.ShipPackageService;
import com.genscript.gsscm.shipment.service.ShipmentsService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "workOrder_manage_frame", location = "workOrder/workOrder_manage_frame.jsp"),
		@Result(name = "workOrder_search", location = "workOrder/workOrder_search.jsp"),
		@Result(name = "vendor_manage_list", location = "workOrder/vendor_manage_list.jsp"),
		@Result(name = "batch_order_receiving", location = "workOrder/batch_order_receiving.jsp"),
		@Result(name = "package_information", location = "workOrder/package_information.jsp"),
		@Result(name = "receive_order_po", location = "workOrder/receive_order_po.jsp"),
		@Result(name = "receive_order_wo", location = "workOrder/receive_order_wo.jsp"),
		@Result(name = "purchase_order_receiving", location = "workOrder/purchase_order_receiving.jsp"),
		@Result(name = "batch_order_receiving_po_detail", location = "workOrder/batch_order_receiving_po_detail.jsp"),
		@Result(name = "batch_order_receiving_po_trackingNo_detail", location = "workOrder/batch_order_receiving_po_trackingNo_detail.jsp"),
		@Result(name = "batch_order_receiving_wo_detail", location = "workOrder/batch_order_receiving_wo_detail.jsp"),
		@Result(name = "batch_order_receiving_wo_batchNo_detail", location = "workOrder/batch_order_receiving_wo_batchNo_detail.jsp"),
		@Result(name = "purchase_order_item_receiving", location = "workOrder/purchase_order_item_receiving.jsp"),
		@Result(name = "create_receiving_report", location = "workOrder/create_receiving_report.jsp"),
		@Result(name = "work_order_item_receiving", location = "workOrder/work_order_item_receiving.jsp"),
		@Result(name = "work_order_receiving", location = "workOrder/work_order_receiving.jsp"),
		@Result(name = "workOrder_manage_list", location = "workOrder/workOrder_manage_list.jsp"),
		@Result(name = "unmatched_package_po", location = "workOrder/unmatched_package_po.jsp"),
		@Result(name = "unmatched_package_wo", location = "workOrder/unmatched_package_wo.jsp"),
		@Result(name = "printOrderItemDoc", location = "workOrder/order_item.jsp"),
		
		@Result(name = "viewprintReceivingReport", location = "workOrder/print_receiving_report_k.jsp") })
public class WorkOrderAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DozerBeanMapper dozer;
	// spring注入
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private PurchaseOrderItemService purchaseOrderItemService;
	@Autowired
	private ShipPackageLineService shipPackageLineService;
	@Autowired
	private ShipPackageService shipPackageService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private ReceivingLogService receivingLogService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private ShipmentsService shipmentsService;
	
	@Autowired
	private FileService fs;
	@Autowired
	private ReadReceiveXmlService readReceiveXmlService;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private FileService fileService;


	@Autowired
	private ExceptionService exceptionUtil;
	private HostIpUtil hostip = new HostIpUtil();
	

	private Page<WorkOrderDTO> pageWorkOrderDTO;

	private Page<PurchaseOrderDTO> pagePurchaseOrderDTO;

	private Page<ShipPackageLineDTO> pageShipPackageLineDTO;

	private Page<PurchaseOrderItemDTO> purchaseOrderItemDTO;

	private Page<VendorDTO> pageVendorDTO;

	private Page<OrderItemDTO> pageOrderItemDTO;

	private Page<WorkOrder> pageWorkOrder;

	private Page<OrderItem> pageOrderItems;

	private Page<PurchaseOrderItem> pagePurchaseOrderItems;

	private Page<PurchaseOrderItemDTO> pagePurchaseOrderItemDTO;

	private WorkOrderDTO workOrderDTO;

	private PurchaseOrderDTO purchaseOrderDTO;

	private String vendor;

	private ReceivingLog receivingLog;

	private WorkGroup workGroup;

	private ShipPackage shipPackages;

	private List<WorkOrderDTO> listWorkOrder;
	
	private List<OrderItem> orderItemList;
	
	

	/**
	 * 运行workOrder_manage_frame框架
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String workOrderManageFrame() throws Exception {
		// 消除session
		String type = Struts2Util.getRequest().getParameter("type");
		Struts2Util.getSession().removeAttribute("list");
		Struts2Util.getSession().removeAttribute("listLogs");
		Struts2Util.getSession().removeAttribute("receivewo1");
		Struts2Util.getSession().removeAttribute("receivewo2");
		Struts2Util.getSession().removeAttribute("po");
		Struts2Util.getSession().removeAttribute("poi");
		Struts2Util.getSession().removeAttribute("receive1");
		Struts2Util.getSession().removeAttribute("receive2");
		Struts2Util.getSession().removeAttribute("str");
		Struts2Util.getSession().removeAttribute("chksss");
		Struts2Util.getSession().removeAttribute("chk");
		Struts2Util.getSession().removeAttribute("vendorNames");
		Struts2Util.getSession().removeAttribute("chks");
		Struts2Util.getSession().removeAttribute("wo2");
		Struts2Util.getSession().removeAttribute("wo");
		Struts2Util.getSession().removeAttribute("listTemp");
		Struts2Util.getSession().removeAttribute("listTemp2");
		Struts2Util.getRequest().setAttribute("type", type);
		return "workOrder_manage_frame";
	}

	/**
	 * unmatched_package_po
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String unmatchedPackagePo() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		String[] str = (String[]) Struts2Util.getSession().getAttribute("str");
		List<PurchaseOrderItemDTO> list = (List<PurchaseOrderItemDTO>) Struts2Util
				.getSession().getAttribute("list1");
		String[] chk = (String[]) Struts2Util.getSession().getAttribute("chk");
		List listTemp = (List)Struts2Util.getSession().getAttribute("listTemp");
		if (list != null && list.size() != 0 && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Integer temp = null;
				Double tempsize = null;
				PurchaseOrderItemDTO poiDTO = list.get(i);
				if (str != null && str.length != 0) {
					for (int j = 0; j < str.length; j++) {
						if (poiDTO.getOrderItemId().equals(
								Integer.parseInt(str[j]))) {
							temp = poiDTO.getQuantity();
							tempsize = poiDTO.getSize();
						}
					}
				}
				if(listTemp==null){
					if (chk != null && chk.length != 0) {
						for (int q = 0; q < chk.length; q++) {
							if (poiDTO.getOrderItemId().equals(
									Integer.parseInt(chk[q]))) {
								temp = poiDTO.getQuantity();
								tempsize = poiDTO.getSize();
							}
						}
					}
				} else {
					for (int j = 0; j < listTemp.size(); j++) {
						Object[] obj = (Object[])listTemp.get(j);
						Integer qty = Integer.parseInt(obj[1]+"");
						Double size = Double.parseDouble(obj[2]+"");
						Integer Id = Integer.parseInt(obj[0]+"");
						if (Id.equals(poiDTO.getOrderItemId())) {
							temp = qty;
							tempsize = size;
						}
					}
				}
				if (temp == null || !temp.equals(poiDTO.getQuantity())
						|| tempsize == null
						|| !tempsize.equals(poiDTO.getSize())) {
					/*System.out.println(temp);
					System.out.println(poiDTO.getQuantity());
					System.out.println(tempsize);
					System.out.println(poiDTO.getSize());*/
					return "unmatched_package_po";
				}
			}
		}
		out
				.print("<script>location.href='work_order!purchaseOrderSave.action'</script>");
		return null;
	}

	/**
	 * unmatched_package_wo
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String unmatchedPackageWo() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		String[] str = (String[]) Struts2Util.getSession().getAttribute("str");
		List<WorkOrderDTO> list = (List<WorkOrderDTO>) Struts2Util.getSession()
				.getAttribute("list2");
		List listTemp2 = (List)Struts2Util.getSession().getAttribute("listTemp2");
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Integer temp = null;
				Double tempsize = null;
				WorkOrderDTO woDTO = list.get(i);
				if (str != null && str.length != 0) {
					for (int j = 0; j < str.length; j++) {
						if (woDTO.getOrderNo().equals(Integer.parseInt(str[j]))) {
							temp = woDTO.getQuantity();
							tempsize = woDTO.getSize();
						}
					}
				}
				
				for (int j = 0; j < listTemp2.size(); j++) {
					Object[] obj = (Object[])listTemp2.get(0);
					Integer qty = Integer.parseInt(obj[1]+"");
					Double size = Double.parseDouble(obj[2]+"");
					Integer orderNo = Integer.parseInt(obj[0]+"");
					if (listTemp2 != null && orderNo.equals(woDTO.getOrderNo())) {
						temp = qty;
						tempsize = size;
					}
				}
				if (temp == null || !temp.equals(woDTO.getQuantity())
						|| tempsize == null
						|| !tempsize.equals(woDTO.getSize())) {
					return "unmatched_package_wo";
				}
			}
		}
		out
				.print("<script>location.href='work_order!workOrderSave.action'</script>");
		return null;
	}

	/**
	 * po Reset按钮
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 */
	public String purchaseOrderReset() throws Exception {
		Struts2Util.getSession().removeAttribute("listTemp");
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			String type = Struts2Util.getRequest().getParameter("po");
			String chks = Struts2Util.getRequest().getParameter("chks") + "";
			String trackingNo = Struts2Util.getRequest().getParameter("trackingNo")+ "";
			Struts2Util.getSession().removeAttribute("po");
			Struts2Util.getSession().removeAttribute("receive1");
			Struts2Util.getSession().removeAttribute("receive2");
			Struts2Util.getSession().removeAttribute("chk");
			Struts2Util.getSession().removeAttribute("str");
			Struts2Util.getSession().removeAttribute("chks");
			Struts2Util.getSession().removeAttribute("trackingNo");
			if (type.equals("po")) {
				int orderNo = Integer.parseInt(Struts2Util.getRequest()
						.getParameter("orderNo"));
				// 消除session
				Struts2Util.getSession().removeAttribute("po");
				Struts2Util.getSession().removeAttribute("chk");
				Struts2Util.getSession().removeAttribute("str");
				out.print("<script>window.location='work_order!getReceiveOrderPo.action?orderNo="+ orderNo + "'</script>");
				return null;
			} else if (type.equals("po2")) {
				out
						.print("<script>window.location='work_order!getPurchaseReceivingDetail.action?chks="
								+ chks + "'</script>");
			} else if (type.equals("po3")) {
				out
						.print("<script>window.location='work_order!getPurchaseReceivingTrackingNoDetail.action?trackingNo="
								+ trackingNo + "'</script>");
			}
			// 消除session

			
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}


	/**
	 * wo Reset按钮
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 */
	public String workOrderReset() throws Exception {
		Struts2Util.getSession().removeAttribute("listTemp2");
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			String type = Struts2Util.getRequest().getParameter("wo");
			String chks = Struts2Util.getRequest().getParameter("chks") + "";
			String batchNos = Struts2Util.getRequest().getParameter("batchNo")
					+ "";
			if (type.equals("wo")) {
				int orderNo = Integer.parseInt(Struts2Util.getRequest()
						.getParameter("orderNo"));
				// 消除session
				Struts2Util.getSession().removeAttribute("list");
				Struts2Util.getSession().removeAttribute("str");
				Struts2Util.getSession().removeAttribute("wo2");
				Struts2Util.getSession().removeAttribute("wo");
				out
						.print("<script>window.location='work_order!getReceiveOrderWo.action?orderNo="
								+ orderNo + "'</script>");
				return null;
			} else if (type.equals("wo2")) {
				out
						.print("<script>window.location='work_order!getWorkOrderReceivingDetail.action?chks="
								+ chks + "'</script>");
			} else if (type.equals("wo3")) {
				out
						.print("<script>window.location='work_order!getWorkOrderReceivingBatchNoDetail.action?batchNo="
								+ batchNos + "'</script>");
			}
			// 消除session
			Struts2Util.getSession().removeAttribute("list");
			Struts2Util.getSession().removeAttribute("str");
			Struts2Util.getSession().removeAttribute("wo2");
			Struts2Util.getSession().removeAttribute("wo");
			Struts2Util.getSession().removeAttribute("chk");
			Struts2Util.getSession().removeAttribute("chks");
			Struts2Util.getSession().removeAttribute("batchNos");
			
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * 显示Receiving列表
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String getWorkOrderManageList() throws Exception {

		// 获取warehouse的类型
		String type = Struts2Util.getRequest().getParameter("type");
		// 获取poVendorName
		String poVendorName = Struts2Util.getRequest().getParameter("poname");
		Integer userId = SessionUtil.getUserId();
		try {
			String cu = "ok";
			if (type == null || type.equals("") || type.equals("SALES")) {
				if (purchaseOrderDTO == null)
					purchaseOrderDTO = new PurchaseOrderDTO();
				// 实例化一个pageUtil对象
				PagerUtil<PurchaseOrderDTO> pagerUtil = new PagerUtil<PurchaseOrderDTO>();
				pagePurchaseOrderDTO = pagerUtil.getRequestPage();
				pagePurchaseOrderDTO.setPageSize(10);
				Page<PurchaseOrder> retPage = this.dozer.map(
						pagePurchaseOrderDTO, Page.class);
				PurchaseOrderDTO srch2 = new PurchaseOrderDTO();
				srch2.setOrderNo(purchaseOrderDTO.getOrderNo());
				srch2.setStatus(purchaseOrderDTO.getStatus());
				srch2.setUsPOrderNo(purchaseOrderDTO.getUsPOrderNo());
				srch2.setUsSOrderNo(purchaseOrderDTO.getUsSOrderNo());
				// RecevingClerk
				srch2.setRecevingClerk(Struts2Util.getParameter("rc"));
				if(srch2.getRecevingClerk()!=null&&!srch2.getRecevingClerk().equals("")&&!srch2.getRecevingClerk().equals("0")){
					User userClerk = this.userDao.findByUserId(Integer.valueOf(srch2.getRecevingClerk()));
					if(userClerk!=null){
						srch2.setUserName(userClerk.getLoginName());
					}
				}
				User user = this.userDao.getById(userId);
				boolean isProductionManagerRole = false;
				if (!Constants.USERNAME_ADMIN.equals(user.getLoginName())) {
		            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_RECEIVING_MANAGER);
		        }else{
		        	isProductionManagerRole = true;
		        }
				if(isProductionManagerRole){
					if(srch2.getRecevingClerk()==null&&!Constants.USERNAME_ADMIN.equals(user.getLoginName())){
						srch2.setRecevingClerk(userId.toString());
					}
				}else{
					srch2.setRecevingClerk(userId.toString());
				}
				srch2.setVendorName(poVendorName);
				// 判断传过来的类型
				if (type == null || type.equals("")) {
					// 执行查询方法
					pagePurchaseOrderDTO = this.purchaseOrderService.searchPo(
							retPage, srch2);
					Struts2Util.getRequest().setAttribute("usa", cu);
				}
				// 判断传过来的类型是SALES
				else if (type.equals("SALES")) {
					pagePurchaseOrderDTO = this.purchaseOrderService.searchPo(
							retPage, srch2);
					Struts2Util.getRequest().setAttribute("usa", cu);
				}
				// 获取count
				Integer count = pagePurchaseOrderDTO.getResult().size();
				// 放到request
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo",
						pagePurchaseOrderDTO);
			} else if (type.equals("MANUFACTURING")) {
				if (workOrderDTO == null)
					workOrderDTO = new WorkOrderDTO();
				// 实例化一个pageUtil对象
				PagerUtil<WorkOrderDTO> pagerUtil = new PagerUtil<WorkOrderDTO>();
				pageWorkOrderDTO = pagerUtil.getRequestPage();
				pageWorkOrderDTO.setPageSize(10);
				Page<WorkOrder> retPage = this.dozer.map(pageWorkOrderDTO,
						Page.class);
				WorkOrderDTO srch = new WorkOrderDTO();
				srch.setOrderNo2(workOrderDTO.getOrderNo2());
				srch.setOrderNo(workOrderDTO.getOrderNo());
				srch.setStatus(workOrderDTO.getStatus());
				srch.setRecevingClerk(Struts2Util.getParameter("rc"));
				// 判断传过来的类型是MANUFACTURING
				if (type.equals("MANUFACTURING")) {
					// 执行方法
					pageWorkOrderDTO = this.workOrderService.searchwo(retPage,
							srch);
					Struts2Util.getRequest().setAttribute("china", cu);
				}
				// 获取count
				Integer count = pageWorkOrderDTO.getResult().size();
				// 放到request里面
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo",
						pageWorkOrderDTO);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "workOrder_manage_list";
	}

	/**
	 * PurchaseOrder ReceiveAll
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String getPurchaseOrderReceiving() throws Exception {
		try {
			Struts2Util.getSession().removeAttribute("chk");
			Struts2Util.getSession().removeAttribute("po");
			// session和request获取值
			String loginName = SessionUtil.getUserName();
			String trackingNo = Struts2Util.getRequest().getParameter(
					"trackingNo");
			String chks = Struts2Util.getRequest().getParameter("cks");

			Struts2Util.getRequest().setAttribute("trackingNo", trackingNo);
			Struts2Util.getRequest().setAttribute("loginName", loginName);
			Struts2Util.getSession().setAttribute("chksss", chks);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "purchase_order_receiving";
	}




	/**
	 * WorkOrder ReceiveAll
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String getWorkOrderReceiving() throws Exception {
		Struts2Util.getSession().removeAttribute("listTemp2");
		Struts2Util.getSession().removeAttribute("wo2");
		Struts2Util.getSession().removeAttribute("wo");
		try {
			Struts2Util.getSession().removeAttribute("chk");
			// session和request获取值
			String loginName = SessionUtil.getUserName();
			String batchNo = Struts2Util.getRequest().getParameter("batchNo");
			String chks = Struts2Util.getRequest().getParameter("cks");

			// 把相应的数据放到request里面
			Struts2Util.getRequest().setAttribute("batchNo", batchNo);
			Struts2Util.getRequest().setAttribute("loginName", loginName);
			Struts2Util.getSession().setAttribute("chksss", chks);
			Struts2Util.getSession().removeAttribute("receivewo1");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "work_order_receiving";
	}


	/**
	 * WorkOrder ReceiveAll confirm
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String getWorkOrderItemReceiveAllAdd() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		// 获取相应的值
		String batchNo = Struts2Util.getRequest().getParameter("batchNo");
		Integer Station = Integer.parseInt(Struts2Util.getRequest()
				.getParameter("Station"));
		String note = Struts2Util.getRequest().getParameter("note");
		String layer = Struts2Util.getRequest().getParameter("ly");
		String box = Struts2Util.getRequest().getParameter("bx");
		String well = Struts2Util.getRequest().getParameter("wl");
		try {
			String Ws = "";
			String lbw = "";
			// 判断
			if (Station == 1) {
				Ws = "Work Station";
				lbw = "Work Station";
			} else if (Station == 2) {
				Ws = Struts2Util.getRequest().getParameter("sfr");
				// 拼接数据
				lbw = "Ly" + layer + "-" + "Bo" + box + "-" + "Wl" + well;
			}
			// 根据name查询sgId
			Storage sg = storageService.getStorageByName(Ws);
			String[] chks = Struts2Util.getRequest().getParameterValues("chks");
			String[] chk2 = chks[0].split(",");
			ReceivingLog rl = null;
			WorkOrderDTO wo2 = null;
			List<ReceivingLog> list = new ArrayList<ReceivingLog>();
			for (int i = 0; i < chk2.length; i++) {
				// 获取当前时间
				java.util.Date utildate = new java.util.Date();
				Integer orderNo = Integer.parseInt(chk2[i]);

				wo2 = this.workOrderService.searchWoByOrderNo(orderNo, batchNo);

				rl = new ReceivingLog();
				// 把值set到ReceivingLogs中
				rl.setOrderType(wo2.getType());
				rl.setTrackingNo(batchNo);
				rl.setOrderNo(wo2.getOrderNo());
				rl.setCatalogNo(wo2.getCatalogNo());
				rl.setQtyReceived(wo2.getQuantity());
				rl.setQtyUom(wo2.getQtyUom());
				rl.setSizeReceived(wo2.getSize());
				rl.setSizeUom(wo2.getSizeUom());
				rl.setStorageId(sg.getStorageId());
				rl.setWarehouseId(wo2.getWarehouseId());
				rl.setLocationCode(lbw);
				rl.setReceivingNote(note);
				rl.setReveivingDate(utildate);
				rl.setReceivedBy(SessionUtil.getUserId());
				list.add(rl);

				Struts2Util.getSession().setAttribute("rllss", rl);
			}
			// 保存到session里面
			Struts2Util.getSession().removeAttribute("wo");

			Struts2Util.getSession().setAttribute("list", list);
			// 判断list集合
			if (list == null || list.size() <= 0) {
				Struts2Util.getSession().setAttribute("wo2", wo2);
			}
			if (list != null && list.size() > 0)
				Struts2Util.getSession().removeAttribute("wo2");
			Struts2Util.getSession().setAttribute("str", chk2);
			String receivewo2 = "woReceive2";
			Struts2Util.getSession().setAttribute("receivewo2", receivewo2);
			Struts2Util.getSession().removeAttribute("receivewo1");
			// 关闭窗口
			out
					.print("<script>parent.$('#workOrderReceiving').dialog('close')</script>");
			out
					.print("<script>parent.location.href=parent.location.href</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * PurchaseOrder ReceiveAll confirm
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String getPurchaseOrderItemReceiveAllAdd() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		// 获取相应的值
		Struts2Util.getSession().removeAttribute("listTemp");
		String trackingNo = Struts2Util.getRequest().getParameter("trackingNo");
		Integer Station = Integer.parseInt(Struts2Util.getRequest()
				.getParameter("Station"));
		String note = Struts2Util.getRequest().getParameter("note");
		/*String layer = Struts2Util.getRequest().getParameter("ly");
		String box = Struts2Util.getRequest().getParameter("bx");
		String well = Struts2Util.getRequest().getParameter("wl");*/
		try {
			String Ws = "";
			String lbw = "";
			if (Station == 1) {
				Ws = "Work Station";
				lbw = "Work Station";
			} else if (Station == 2) {
				Ws = Struts2Util.getRequest().getParameter("sfr");
				lbw = Ws;//"Ly" + layer + "-" + "Bo" + box + "-" + "Wl" + well;
			}
			// 根据name查询sgId
			Storage sg = storageService.getStorageByName(Ws);

			// 获取选中的数组
			String[] chks = Struts2Util.getRequest().getParameterValues("chks");

			String[] chk2 = chks[0].split(",");
			ReceivingLog rl = null;
			PurchaseOrderItemDTO poi = null;
			List<ReceivingLog> list = new ArrayList<ReceivingLog>();
			// for循环
			for (int i = 0; i < chk2.length; i++) {
				// 获取当前时间
				java.util.Date utildate = new java.util.Date();
				Integer orderItemIds = Integer.parseInt(chk2[i]);
				poi = this.purchaseOrderItemService
						.getPurchaseByItemId(orderItemIds);

				rl = new ReceivingLog();
				// 把值set到ReceivingLogs中
				rl.setOrderType(poi.getPurchaseOrder().getOrderType());
				rl.setTrackingNo(trackingNo);
				rl.setOrderNo(poi.getPurchaseOrder().getOrderNo());
				rl.setItemNo(poi.getItemNo());
				rl.setCatalogNo(poi.getCatalogNo());
				rl.setQtyReceived(poi.getQuantity());
				rl.setQtyUom(poi.getQtyUom());
				rl.setSizeReceived(poi.getSize());
				rl.setSizeUom(poi.getSizeUom());
				rl.setStorageId(sg.getStorageId());
				rl.setWarehouseId(poi.getPurchaseOrder().getWarehouseId());
				rl.setLocationCode(lbw);
				rl.setReceivingNote(note);
				rl.setReveivingDate(utildate);
				rl.setReceivedBy(SessionUtil.getUserId());
				list.add(rl);
				Struts2Util.getSession().setAttribute("rlll", rl);
			}
			// 保存到session里面
			Struts2Util.getSession().removeAttribute("po");
			Struts2Util.getSession().setAttribute("poi", poi);
			Struts2Util.getSession().setAttribute("list", list);
			Struts2Util.getSession().setAttribute("lbw", lbw);
			Struts2Util.getSession().setAttribute("str", chk2);
			String receive1 = "ReceiveAll";
			Struts2Util.getSession().setAttribute("receive1", receive1);
			Struts2Util.getSession().removeAttribute("receive2");
			// 关闭窗口
			out
					.print("<script>parent.$('#purchaseOrderReceiving').dialog('close')</script>");
			// 刷新父窗体
			out
					.print("<script>parent.location.href=parent.location.href</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}



	/**
	 * WorkOrder Receive
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String getWorkOrderItemReceiving() throws Exception {
		Struts2Util.getSession().removeAttribute("chks");
		Struts2Util.getSession().removeAttribute("str");
		// 获取loginName
		String loginName = SessionUtil.getUserName();
		try {
			// 获取值
			String batchNo = Struts2Util.getRequest().getParameter("batchNo");
			String[] chks = Struts2Util.getRequest().getParameterValues("cks");
			//获取选中的chks
			String chksShow2 = Struts2Util.getParameter("chksShow2");
			
			//获取listTemp
			List<String[]> listTemp2= (List<String[]>)Struts2Util.getSession().getAttribute("listTemp2");
			//判断listTemp是否为null,否则new
			if(listTemp2 ==null)
				listTemp2 = new ArrayList<String[]>();
			// 获取数组
			String[] chk = chks[0].split(",");
			if(chksShow2 !=null && chksShow2.length()>1){
				//截取
				chksShow2 = chksShow2.substring(0, chksShow2.length()-1);
				String[] chksShows = chksShow2.split(",");
				//for循环
				for (int i = 0; i < chksShows.length; i++) {
					for (int j = 0; j < listTemp2.size(); j++) {
						if(i%2==0){
							if(listTemp2.get(j)[0].equals(chksShows[i])){
								listTemp2.remove(listTemp2.get(j));
							}
						}
					}
				}
			}
			
			// 判断chk的长度
			if (chk.length == 1) {
				// 获取orderNo
				Integer orderNo = Integer.parseInt(chks[0]);
				// 调用方法
				WorkOrderDTO wo = this.workOrderService.searchWoByOrderNo(
						orderNo, batchNo);
				Struts2Util.getRequest().setAttribute("wo", wo);
			}
			if (chk.length > 1)
				Struts2Util.getSession().removeAttribute("wo2");
			// 把相应的值放到request里面
			Struts2Util.getSession().setAttribute("chk", chk);
			Struts2Util.getRequest().setAttribute("chkLength", chk.length);
			Struts2Util.getRequest().setAttribute("batchNo", batchNo);
			Struts2Util.getRequest().setAttribute("loginName", loginName);
			// 消除receivewo2
			Struts2Util.getSession().removeAttribute("receivewo2");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "work_order_item_receiving";
	}
	/**
	 * PurchaseOrder Receive
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String getPurchaseOrderItemReceiving() throws Exception {
		// 消除session
		Struts2Util.getSession().removeAttribute("chks");
		Struts2Util.getSession().removeAttribute("str");
		// 获取用户名
		String loginName = SessionUtil.getUserName();

		// 查询全部的trackingNo
		String trackingNo = Struts2Util.getRequest().getParameter("trackingNo");
		String[] chks = Struts2Util.getRequest().getParameterValues("cks");
		String[] receIds = Struts2Util.getRequest().getParameterValues("receIds");
		//获取选中的chks
		String chksShow = Struts2Util.getParameter("chksShow");
		
		//获取listTemp
		List<String[]> listTemp = (List<String[]>)Struts2Util.getSession().getAttribute("listTemp");
		try {
			//判断listTemp是否为null,否则new
			if(listTemp ==null)
				listTemp = new ArrayList<String[]>();
			// 得到数组
			String[] chk = chks[0].split(",");
			if(chksShow !=null && chksShow.length()>1){
				//截取
				chksShow = chksShow.substring(0, chksShow.length()-1);
				String[] chksShows = chksShow.split(",");
				//for循环
				for (int i = 0; i < chksShows.length; i++) {
					for (int j = 0; j < listTemp.size(); j++) {
						if(i%2==0){
							if(listTemp.get(j)[0].equals(chksShows[i])){
								listTemp.remove(listTemp.get(j));
							}
						}
					}
				}
			}
			if (chk.length == 1) {
				// 获取orderItemId
				Integer orderItemId = Integer.parseInt(chks[0]);
				// 根据orderItemId 查询PurchaseOrderItems
				PurchaseOrderItemDTO poi = this.purchaseOrderItemService
						.getPurchaseByItemId(orderItemId);
				System.out.println("poi="+poi.getSize());
				// 放在request
				Struts2Util.getRequest().setAttribute("poi", poi);
			}
			if (chk.length > 1) {
				Struts2Util.getSession().removeAttribute("poi");
			}
			// 把相应的值放到request里面
			Struts2Util.getSession().setAttribute("chk", chk);
			Struts2Util.getSession().setAttribute("receIds", receIds);
			Struts2Util.getRequest().setAttribute("chkLength", chk.length);
			Struts2Util.getRequest().setAttribute("trackingNo", trackingNo);
			Struts2Util.getRequest().setAttribute("loginName", loginName);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "purchase_order_item_receiving";
	}
	/**
	 * wo Receive confirm
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */

	@SuppressWarnings("unchecked")
	public String getWorkOrderItemReceiveAdd() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		// 获取相应的值
		String[] chk = (String[]) Struts2Util.getSession().getAttribute("chk");
		String batchNo = Struts2Util.getRequest().getParameter("batchNo");
		String quantity_ = Struts2Util.getRequest().getParameter("quantity");
		String size_ = Struts2Util.getRequest().getParameter("size");
		Integer Station = Integer.parseInt(Struts2Util.getRequest()
				.getParameter("Station"));
		String note = Struts2Util.getRequest().getParameter("note");
		String layer = Struts2Util.getRequest().getParameter("ly");
		String box = Struts2Util.getRequest().getParameter("bx");
		String well = Struts2Util.getRequest().getParameter("wl");

		try {
			List<String[]> listTemp2 = (List<String[]>)Struts2Util.getSession().getAttribute("listTemp2");
			if(listTemp2 == null)
				listTemp2 = new ArrayList<String[]>();
			//声明数组
			String[] arr = null;
			String lbw = "";
			String Ws = "";
			// 判断station数据
			if (Station == 1) {
				Ws = "Work Station";
				lbw = "Work Station";
			} else if (Station == 2) {
				Ws = Struts2Util.getRequest().getParameter("sfr");
				// 然后拼接
				lbw = "Ly" + layer + "-" + "Bo" + box + "-" + "Wl" + well;
			}
			// 根据name查询sgId
			Storage sg = storageService.getStorageByName(Ws);
			// for循环
			for (int i = 0; i < chk.length; i++) {
				WorkOrderDTO wo2 = this.workOrderService.searchWoByOrderNo(
						Integer.parseInt(chk[i]), batchNo);
				// 获取qty
				int quantity = wo2.getQuantity();
				// 获取seize
				double size = wo2.getSize();
				if (quantity_ != null && quantity_.length() > 0)
					quantity = Integer.parseInt(quantity_);
				if (size_ != null && size_.length() > 0)
					size = Double.parseDouble(size_);
				arr = new String[]{chk[i],quantity+"",size+"",batchNo,sg.getStorageId()+"",lbw,note};
				
				for (int j = 0; j < listTemp2.size(); j++) {
					if(listTemp2.get(j)[0].equals(chk[i])){
						listTemp2.remove(listTemp2.get(j));
					}
				}
				listTemp2.add(arr);
			}
			// remove里面session
			Struts2Util.getSession().removeAttribute("wo2");
			// 把qt和size保存到session里面
			if (chk.length == 1) {
				WorkOrder wo = new WorkOrder();
				wo.setQuantity(Integer.parseInt(quantity_));
				wo.setSize(Double.parseDouble(size_));
				wo.setOrderNo(Integer.parseInt(chk[0]));
				Struts2Util.getSession().setAttribute("wo2", wo);
			}
			if (chk.length > 1)
				Struts2Util.getSession().removeAttribute("wo");
			// 把数据放在session里面
			Struts2Util.getSession().setAttribute("listTemp2", listTemp2);
			Struts2Util.getSession().setAttribute("note", note);
			String receivewo1 = "woReceive1";
			Struts2Util.getSession().setAttribute("receivewo1", receivewo1);
			Struts2Util.getSession().removeAttribute("receivewo2");
			// 关闭窗口
			out
					.print("<script>parent.$('#workOrderItemReceiving').dialog('close')</script>");
			out
					.print("<script>parent.location.href=parent.location.href</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;

	}
	/**
	 * PurchaseOrder Receive confirm
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	
	@SuppressWarnings("unchecked")
	public String getPurchaseOrderItemReceiveAdd() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		// 获取相应的值
		String[] chk = (String[]) Struts2Util.getSession().getAttribute("chk");
		String trackingNo = Struts2Util.getRequest().getParameter("trackingNo");
		String quantity_ = Struts2Util.getRequest().getParameter("quantity");
		String size_ = Struts2Util.getRequest().getParameter("size");
		Integer Station = Integer.parseInt(Struts2Util.getRequest().getParameter("Station"));
		String note = Struts2Util.getRequest().getParameter("note");
		/*String layer = Struts2Util.getRequest().getParameter("ly");
		String box = Struts2Util.getRequest().getParameter("bx");
		String well = Struts2Util.getRequest().getParameter("wl");*/
		try {
			List<String[]> listTemp = (List<String[]>)Struts2Util.getSession().getAttribute("listTemp");
			if(listTemp == null)
				listTemp = new ArrayList<String[]>();
				
			String[] arr = null;
			String Ws = "";
			String lbw = "";
			if (Station == 1) {
				Ws = "Work Station";
				lbw = "Work Station";
			} else if (Station == 2) {
				Ws = Struts2Util.getRequest().getParameter("sfr");
				// 拼接数据
				lbw = Ws;//"Ly" + layer + "-" + "Bo" + box + "-" + "Wl" + well;
			}
			// 根据name查询sgId
			Storage sg = storageService.getStorageByName(Ws);
			
			// for循环
			String listChks = "";
			for(int i = 0;i<chk.length;i++){
				String listchk =chk[i];
				if(listChks.equals("")){
					listChks = listchk;
				}else{
					listChks +=(","+ listchk);
				}
			}
			List<PurchaseOrderItemDTO> poiDTOList = this.purchaseOrderItemService.searchPurchaseByItemId(listChks);
			for (int i = 0; i < chk.length; i++) {
				PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
				for(PurchaseOrderItemDTO dto:poiDTOList){
					if(dto.getOrderItemId().toString().equals(chk[i])){
						poi = dto;
						break;
					}
				}
				
				// 获取qty
				int quantity = poi.getQuantity();
				// 获取size
				double size = poi.getSize();
				if (quantity_ != null && quantity_.trim().length() > 0)
					quantity = Integer.parseInt(quantity_.trim());
				if (size_ != null && size_.trim().length() > 0)
					size = Double.parseDouble(size_.trim());
				
				arr = new String[]{chk[i],quantity+"",size+"",trackingNo,sg.getStorageId()+"",lbw,note};
				
				for (int j = 0; j < listTemp.size(); j++) {
					if(listTemp.get(j)[0].equals(chk[i])){
						listTemp.remove(listTemp.get(j));
					}
				}
				listTemp.add(arr);
			}
			Struts2Util.getSession().removeAttribute("po");

			Struts2Util.getSession().setAttribute("listTemp", listTemp);
			String receive2 = "Receive";
			Struts2Util.getSession().setAttribute("receive2", receive2);
			Struts2Util.getSession().removeAttribute("receive1");
			// 关闭窗口
			out.print("<script>parent.$('#purchaseOrderItemReceiving').dialog('close')</script>");
			// 刷新父窗体
			out.print("<script>parent.location.href=parent.location.href</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}
	/**
	 * WorkOrder Save
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String workOrderSave() throws Exception {
		String miss = Struts2Util.getRequest().getParameter("miss");
		String woBatchIdsStr = Struts2Util.getRequest().getParameter(
				"woBatchIds");
		String orderNosStr = Struts2Util.getRequest().getParameter("orderNos");
		System.out.println(miss);
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {

			if (miss != null && !miss.equals("")) {

				String[] misss = miss.split(",");
				String[] woBatchIds = null;
				String[] orderNos = null;
				if (woBatchIdsStr != null && !woBatchIdsStr.equals(""))
					woBatchIds = woBatchIdsStr.split(",");
				if (orderNosStr != null && !orderNosStr.equals(""))
					orderNos = orderNosStr.split(",");
				for (int i = 0; i < misss.length; i++) {
					if (!"Unreceived".equals(misss[i])) {
						WoBatchError woe = new WoBatchError();
						String[] m = misss[i].split("_");
						if (!"error".equals(m[0])) {
							woe.setMissingQty(Integer.parseInt(m[0]));
						}
						if (!"error".equals(m[1])) {
							woe.setMissingSize(Double.parseDouble(m[1]));
						}
						Integer.parseInt(woBatchIds[i]);
						this.workOrderService.getWoBatcheById(Integer
								.parseInt(woBatchIds[i]));
						woe.setWoBatches(this.workOrderService
								.getWoBatcheById(Integer
										.parseInt(woBatchIds[i])));
						woe
								.setWorkOrder(this.workOrderService
										.getWorkOrderById(Integer
												.parseInt(orderNos[i])));
						woe.setCreationDate(new java.sql.Date(System
								.currentTimeMillis()));
						woe.setCreatedBy(SessionUtil.getUserId());
						woe.setModifiedBy(SessionUtil.getUserId());
						woe.setModifyDate(new java.sql.Date(System
								.currentTimeMillis()));
						this.workOrderService.saveWoBatchError(woe);
					}
				}
			}
			// 获取list集合
			List list = (List)Struts2Util.getSession().getAttribute("listTemp2");
			List<ReceivingLog> listLogs = (List<ReceivingLog>)Struts2Util.getSession().getAttribute("listLogs");
			if(listLogs == null)
				listLogs = new ArrayList<ReceivingLog>();
			// 循环list集合
			for (int i = 0; i < list.size(); i++) {
				String []listchk = (String [])list.get(i);
				WorkOrderDTO wo2 = this.workOrderService.searchWoByOrderNo(
						Integer.parseInt(listchk[0]), listchk[3]);
				// 获取当前时间
				java.util.Date utildate = new java.util.Date();

				ReceivingLog rl = new ReceivingLog();
				// 把值set到ReceivingLogs中
				//rl.setOrderType(wo2.getType());
				rl.setOrderType("Work Order");
				rl.setTrackingNo(listchk[3]);
				rl.setOrderNo(wo2.getOrderNo());
				rl.setCatalogNo(wo2.getCatalogNo());
				rl.setQtyReceived(Integer.parseInt(listchk[1]));
				rl.setQtyUom(wo2.getQtyUom());
				rl.setSizeReceived(Double.parseDouble(listchk[2]));
				rl.setSizeUom(wo2.getSizeUom());
				rl.setStorageId(Integer.parseInt(listchk[4]));
				rl.setWarehouseId(wo2.getWarehouseId());
				rl.setLocationCode(listchk[5]);
				rl.setReceivingNote(listchk[6]);
				rl.setReveivingDate(utildate);
				rl.setReceivedBy(SessionUtil.getUserId());
				// 执行添加方法
				this.receivingLogService.addReceivingLogs(rl);
				// 获取batchNo、itemNo、orderNo
				String batchNo = rl.getTrackingNo();
				String orderNo = rl.getOrderNo() + "";
				// 查询到的order_no的状态将被更新的数据
				List listtio = this.workOrderService
						.findWoBatchDetails(orderNo);
				String status = "Received";
				if (listtio != null && listtio.size() > 0) {
					Object[] obj = (Object[]) listtio.get(0);
					// 获取orderNo
					Integer orderNos = Integer.parseInt(obj[0] + "");
					// 执行更新
					this.workOrderService.updateWorkOrderStatus(orderNos,
							status);
				}
				boolean flg = true;
				Integer woBatchId = 0;
				// 根据batchNo判断是否要更新woBatch中的数据
				List listWoBatchDetail = this.workOrderService
						.findWoBatchIdDetailss(batchNo);
				// 循环list
				for (int k = 0; listWoBatchDetail != null
						&& k < listWoBatchDetail.size(); k++) {
					Object[] obj = (Object[]) listWoBatchDetail.get(k);
					// 获取woBatchId
					woBatchId = Integer.parseInt(obj[0] + "");
				}
				// 当flg==false的时候，执行更新方法
				if (flg == false) {
					this.workOrderService.updateWorkBatchStatus(woBatchId,
							status);
				}
			}
			// 消除session中的wo2 chk note receivewo1
			Struts2Util.getSession().removeAttribute("wo2");
			Struts2Util.getSession().removeAttribute("chk");
			Struts2Util.getSession().removeAttribute("note");
			Struts2Util.getSession().removeAttribute("receivewo1");
			// 执行成功
			out.print("<script>alert('Data saved successfully!')</script>");
			String MANUFACTURING = "MANUFACTURING";
			if (miss != null && !miss.equals("")) {
				//out.print("<script>location.href='work_order!workOrderManageFrame.action'</script>");
				out.print("<script>location.href='work_order!workOrderManageFrame.action?type="+MANUFACTURING+"'</script>");
			} else {
//				out.print("<script>parent.location.href='work_order!workOrderManageFrame.action'</script>");
				out.print("<script>location.href='work_order!workOrderManageFrame.action?type="+MANUFACTURING+"'</script>");
			}
			out.flush();
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}
	/**
	 * PurchaseOrder Save
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String setPurchaseOrderItemToReceive(){
		try {
			Integer userId = SessionUtil.getUserId();
			String[] chk = (String[]) Struts2Util.getSession().getAttribute("receIds");
			String quantity_ = Struts2Util.getRequest().getParameter("quantity");
			if(quantity_==null||quantity_.equals("")){
				quantity_="0";
			}
			String size_ = Struts2Util.getRequest().getParameter("size");
			if(size_==null||size_.equals("")){
				size_="0";
			}
			if(chk!=null&&chk.length>0){
				for(int i = 0; i < chk.length; i++){
					
				}
					
			}
			PrintWriter out = Struts2Util.getResponse().getWriter();
			// 刷新父窗体
			out.print("<script>parent.location.href=parent.location.href</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public String purchaseOrderSave() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		String miss = Struts2Util.getRequest().getParameter("miss");
		/*;
		String packageIdsStr = Struts2Util.getRequest().getParameter(
				"packageIds");
		String pkgLineIdsStr = Struts2Util.getRequest().getParameter(
				"pkgLineIds");
		System.out.println(miss);
		*/
		try {
			/*
			 * 发送邮件通知;
			 */
			if(miss!=null&&!miss.equals("")){
				this.purchaseOrderService.sendMail(miss);
			}
			if (miss != null && !miss.equals("")) {

				String[] misss = miss.split(",");
				/*String[] packageIds = null;
				String[] pkglineIds = null;*/
				/*if (packageIdsStr != null && !packageIdsStr.equals(""))
					packageIds = packageIdsStr.split(",");
				if (pkgLineIdsStr != null && !pkgLineIdsStr.equals(""))
					pkglineIds = pkgLineIdsStr.split(",");*/
				for (int i = 0; i < misss.length; i++) {
					//if (!"Unreceived".equals(misss[i])) {
					ShipPackageErrors spe = new ShipPackageErrors();
					String[] m = misss[i].split("_");
					if (!"Unreceived".equals(m[0])) {
						if (!"error".equals(m[6])) {
							spe.setMissingQty(Integer.parseInt(m[6]));
						}
						if (!"error".equals(m[7])) {
							spe.setMissingSize(Double.parseDouble(m[7]));
						}
						spe.setPackageId(Integer.valueOf(m[2]));
						spe.setPkgLineId(Integer.valueOf(m[3]));
						spe.setCreationDate(new java.sql.Date(System
								.currentTimeMillis()));
						spe.setCreatedBy(SessionUtil.getUserId());
						spe.setModifiedBy(SessionUtil.getUserId());
						spe.setModifyDate(new java.sql.Date(System
								.currentTimeMillis()));
						this.workOrderService.saveShipPackageError(spe);
					}
				}
			}
			Integer userId = SessionUtil.getUserId();
			List list = (List)Struts2Util.getSession().getAttribute("listTemp");
			/*List<ReceivingLog> listLogs = (List<ReceivingLog>)Struts2Util.getSession().getAttribute("listLogs");
			if(listLogs == null)
				listLogs = new ArrayList<ReceivingLog>();*/
			int shipPLine = 0;
			String tmpId="";
			String listChks = "";
			List<Integer> tmpPoNos = new ArrayList<Integer>();
			List<Integer> pSrcSo = new ArrayList<Integer>();
			List<ReceivingLog> updatePObyReceList = new ArrayList<ReceivingLog>();
			for(int i = 0;i<list.size();i++){
				String []listchk = (String [])list.get(i);
				if(listChks.equals("")){
					listChks = listchk[0];
				}else{
					listChks +=(","+ listchk[0]);
				}
			}
			List<PurchaseOrderItemDTO> poiDTOList = this.purchaseOrderItemService.searchPurchaseByItemId(listChks);
			String sentPackage="<?xml version=\"1.0\" encoding=\"utf-8\"?><Receive><Package>";
			for (int i = 0; i < list.size(); i++) {
				String []listchk = (String [])list.get(i);
				PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
				for(PurchaseOrderItemDTO poiDTO :poiDTOList){
					
					if(listchk[0].equals(poiDTO.getOrderItemId().toString())){
						poi = poiDTO;
						break;
					}
				}
				PoReceivingTmp tmp = poi.getTmp();
				String size_ = listchk[2];
				String quantity_ = listchk[1];
				if(tmp!=null){
					PurchaseOrder purchaseOrder = poi.getPurchaseOrder();
					if(purchaseOrder!=null){
						List<Reservation> reservationList = this.reservationService.getReservationList(purchaseOrder.getSrcSoNo(), tmp.getPoLineNo());
						Reservation reservation = new Reservation();
						if(reservationList!=null&&!reservationList.isEmpty()){
							reservation = reservationList.get(0);
							if(reservation.getQty().equals(1)){
								reservation.setQty(1);
								if(size_!=null){
									if(reservation.getSize()==null){
										reservation.setSize(0.0);
									}
									reservation.setSize(Arith.add(reservation.getSize(), Double.valueOf(size_)));
								}
								tmp.setQty(1);
								if(tmp.getSize()>Double.valueOf(size_)){
									tmp.setSize(Arith.sub(tmp.getSize(), Double.valueOf(size_)));
								}else{
									tmp.setSize(0.0);
								}
							}else{
								reservation.setQty(reservation.getQty()+Integer.valueOf(quantity_));
								if(size_!=null){
									if(reservation.getSize()==null){
										reservation.setSize(0.0);
									}
									reservation.setSize(Arith.add(reservation.getSize(), Double.valueOf(size_)));
								}
								if(tmp.getQty()>Integer.valueOf(quantity_)){
									tmp.setQty(tmp.getQty()-Integer.valueOf(quantity_));
								}else{
									tmp.setQty(0);
								}
								if(tmp.getSize()>Double.valueOf(size_)){
									tmp.setSize(Arith.sub(tmp.getSize(), Double.valueOf(size_)));
								}else{
									tmp.setSize(0.0);
								}
							}
						}else{
							PurchaseOrderItem purchaseOrderItem = poi.getPorderItem();
							if(purchaseOrderItem!=null){
								reservation.setOrderNo(purchaseOrder.getSrcSoNo());
								reservation.setItemNo(tmp.getPoLineNo());
								reservation.setQtyUom(purchaseOrderItem.getQtyUom());
								reservation.setSizeUom(purchaseOrderItem.getSizeUom());
								//reservation.setStockDetailId(111);
								reservation.setCatalogNo(purchaseOrderItem.getCatalogNo());
								/*if(purchaseOrderItem.getQuantity().equals(1)){*/
									reservation.setQty(1);
									reservation.setSize(Double.valueOf(size_));
									tmp.setQty(1);
									if(tmp.getSize()>Double.valueOf(size_)){
										tmp.setSize(Arith.sub(tmp.getSize(), Double.valueOf(size_)));
									}else{
										tmp.setSize(0.0);
									}
									/*}else{
									reservation.setQty(Integer.valueOf(quantity_));
									if(tmp.getQty()>Integer.valueOf(quantity_)){
										tmp.setQty(tmp.getQty()-Integer.valueOf(quantity_));
									}else{
										tmp.setQty(0);
									}
								}*/
							}
						}
						reservation.setReserveDate(tmp.getReceivingTime());
						reservation.setReservedBy(userId);
						this.reservationService.saveReservation(reservation);
						if((tmp.getQty().equals(1)&&tmp.getSize().equals(0.0))||tmp.getQty().equals(0)){
							//Integer tmpPoNo = tmp.getPoNo();
							if(tmpId.equals("")){
								tmpId = tmp.getId()+"";
							}else{
								tmpId += (","+tmp.getId());
							}
							//this.purchaseOrderItemService.delPoPeceiveTmp(tmp);
							//List<PoReceivingTmp> tmpReceived = this.purchaseOrderItemService.searchReceiveTmp(tmpPoNo);
							String isAddTmpPO = "1";
							for(Integer tmpPoNo : tmpPoNos){
								if(tmpPoNo.equals(tmp.getPoNo())){
									isAddTmpPO="0";
								}
							}
							if(isAddTmpPO.equals("1")){
								tmpPoNos.add(tmp.getPoNo());
							}
							/*if(tmpReceived==null||tmpReceived.isEmpty()){
								purchaseOrder.setStatus("Received");
								this.purchaseOrderService.savePO(purchaseOrder);
								//this.readReceiveXmlService.setInfoXML(purchaseOrder.getSrcSoNo(), purchaseOrder.getStatus(), "/tmp/received", "received/");
							}*/
						}else{
							this.purchaseOrderItemService.savePoPeceiveTmp(tmp);
						}
						/*List<Shipment> shipmentList = this.shipmentLinesService.getShipmentByOrderNo(purchaseOrder.getSrcSoNo());
						if(shipmentList!=null&&!shipmentList.isEmpty()){
							for(Shipment shipment : shipmentList){
								if(!shipment.getStatus().equals("Invalid")){
									shipment.setModifyDate(new Date());
									shipment.setReceiveTime(new Date());
									String isAddShipment = "1";
									for(Shipment shipment1:shipmentListAdd){
										if(shipment1.getShipmentId().equals(shipment.getShipmentId())){
											isAddShipment = "0";
										}
									}
									if(isAddShipment.equals("1")){
										this.shipmentsService.saveShipment(shipment);
										shipmentListAdd.add(shipment);
									}
									
									break;
								}
							}
						}*/
						String isAddSrcSoNo = "1";
						for(Integer srcSoNo : pSrcSo){
							if(purchaseOrder.getSrcSoNo().equals(srcSoNo)){
								isAddSrcSoNo="0";
							}
						}
						if(isAddSrcSoNo.equals("1")){
							pSrcSo.add(purchaseOrder.getSrcSoNo());
						}
						sentPackage += "<line><soNo>"+purchaseOrder.getSrcSoNo()+"</soNo>";
						/*String po = this.shipmentsService.getPOBySO(purchaseOrder.getOrderNo());
						sentPackage +="<po>"+po+"</po>";*/
						sentPackage +="<itemNo>"+tmp.getPoLineNo()+"</itemNo>";
						/*if(shipPackageLine.getSize()!=null){
							sentPackage +="<size>"+shipPackageLine.getSize()+"</size>";
						}else{
							sentPackage +="<size></size>";
						}*/
						//
						OrderItem orderItem = poi.getOrderItem();
						if("SERVICE".equals(orderItem.getType())&&orderItem.getClsId()==3&&"SC1010".equals(orderItem.getCatalogNo())){
							OrderGeneSynthesis orderGeneSynthesis = this.orderGeneSynthesisDao.getById(orderItem.getOrderItemId());
							if(orderGeneSynthesis!=null){
								sentPackage +="<qty>"+orderGeneSynthesis.getSeqLength()+"</qty>";
							}else{
								sentPackage +="<qty>"+quantity_+"</qty>";
							}
						}else{
							if(size_!=null&&!size_.equals("0")&&orderItem.getSize()!=null&&orderItem.getSize()!=0){
								Double sizeR = Arith.div(Double.valueOf(size_), Arith.mul(orderItem.getSize(),orderItem.getQuantity()));
								sentPackage +="<qty>"+sizeR+"</qty>";
							}else{
								sentPackage +="<qty>"+quantity_+"</qty>";
							}
						}
						OrderMain order = poi.getOrder();
						String company = "";
						if(order!=null){
							//Customer customer = this.customerDao.getById(order.getCustNo());
							/*OrderAddress orderAddress = orderAddressDao.getAddrByOrderNoAndType(purchaseOrder.getOrderNo(), "BILL_TO");
							if(orderAddress!=null){
								company = billTerritoryDao.getAccountCode(orderAddress.getCountry(), orderAddress.getState(), orderAddress.getZipCode());
							}*/
							if(order.getGsCoId()==1){
								company = "GSUS";
							}
							if(order.getGsCoId()==2){
								company = "GSNJ";
							}
							if(order.getGsCoId()==3){
								company = "GSPK";
							}
							if(order.getGsCoId()==4){
								company = "GSHK";
							}
						}
						
						sentPackage +="<company>"+company+"</company></line>";
						shipPLine ++;
						
					}
				

				// 获取当前时间
				java.util.Date utildate = new java.util.Date();
		
				ReceivingLog rl = new ReceivingLog();
				// 把值set到ReceivingLogs中
				//rl.setOrderType(poi.getPurchaseOrder().getOrderType());
				rl.setOrderType("Purchase Order");
				rl.setTrackingNo(tmp.getTrackingNo());
				rl.setOrderNo(poi.getPurchaseOrder().getOrderNo());
				rl.setItemNo(poi.getItemNo());
				rl.setCatalogNo(poi.getCatalogNo());
			    rl.setQtyReceived(Integer.parseInt(listchk[1]));
				rl.setQtyUom(poi.getQtyUom());
				rl.setSizeReceived(Double.parseDouble(listchk[2]));
				rl.setSizeUom(poi.getSizeUom());
				rl.setStorageId(Integer.parseInt(listchk[4]));
			    rl.setWarehouseId(poi.getPurchaseOrder().getWarehouseId());
				rl.setLocationCode(listchk[5]);
				rl.setReceivingNote(listchk[6]);
				rl.setReveivingDate(utildate);
				rl.setReceivedBy(SessionUtil.getUserId());
				// 执行添加方法
				this.receivingLogService.addReceivingLogs(rl);
				// 获取trackingNo、itemNo、orderNo
				//String trackingNo = rl.getTrackingNo();
				String isAddRece = "1";
				for(ReceivingLog receivingLog :updatePObyReceList){
					if(receivingLog.getOrderNo().equals(rl.getOrderNo())){
						isAddRece = "0";
						break;
					}
				}
				if(isAddRece.equals("1")){
					updatePObyReceList.add(rl);
				}
				/*Integer itemNo = rl.getItemNo();
				Integer orderNo = rl.getOrderNo();
				// 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
				boolean isTrue = this.purchaseOrderService.findReceiveLineFlag(
						itemNo, orderNo);

				// 更新spk_lines状态
				if (isTrue) {
					//String orderNos = listtio.get(0);
					this.purchaseOrderService.updateReceiveFlag(orderNo);
				}*/
				/*boolean flg = true;
				String packageId = "";*/
				/*// spk和spk_line表，判断是不是要更新
				List listSpk_lines = this.shipPackageLineService.findSpk_lines(
						orderNo, itemNo);*/
				/*for (int k = 0; k < listSpk_lines.size(); k++) {
					Object[] obj = (Object[]) listSpk_lines.get(k);
					// 判断状态
					if (!"Received".equals(obj[0] + "")) {
						flg = false;
						break;
					}
					// 获取packageId
					packageId = obj[1] + "";
				}*/
				// 当flg==true的时候，执行更新方法
				/*if (flg == true) {
					this.shipPackageLineService.updateShipPkReceive(packageId);
				}*/
			}
				if(shipPLine>80){
					sentPackage +="</Package></Receive>";
					//sent xml
					this.readReceiveXmlService.setInfoStringToXML(sentPackage, fileService.getUploadPath()+"received", "received/");
					shipPLine=0;
					sentPackage="<?xml version=\"1.0\" encoding=\"utf-8\"?><Receive><Package>";
				}
			}
			if(!tmpId.equals("")){
				this.purchaseOrderItemService.delPoPeceiveTmps(tmpId);
			}

			for(Integer tmpPoNo : tmpPoNos){
				List<PoReceivingTmp> tmpReceived = this.purchaseOrderItemService.searchReceiveTmp(tmpPoNo);
				if(tmpReceived==null||tmpReceived.isEmpty()){
					this.purchaseOrderService.updatePO(tmpPoNo,"Received");
				}
			}
			for(ReceivingLog rl: updatePObyReceList){
				Integer orderNo = rl.getOrderNo();
				// 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
				boolean isTrue = this.purchaseOrderService.findReceiveLineFlag(orderNo);

				// 更新spk_lines状态
				if (isTrue) {
					//String orderNos = listtio.get(0);
					this.purchaseOrderService.updateReceiveFlag(orderNo);
				}
			}
			
			List<ShipmentLine> shipmentLineList = this.shipmentsService.getShipmentByOrderNo(pSrcSo);
			if(shipmentLineList!=null&&!shipmentLineList.isEmpty()){
				List<Shipment> shipmentList = new ArrayList<Shipment>();
				for(ShipmentLine shipmentLine : shipmentLineList){
					String isUpdate = "1";
					for(Shipment shipment : shipmentList){
						if(shipmentLine.getShipments().getShipmentId().equals(shipment.getShipmentId())){
							isUpdate = "0";
						}
					}
					if(isUpdate .equals("1")){
						if(!shipmentLine.getShipments().getStatus().equals("Invalid")){
							shipmentLine.getShipments().setModifyDate(new Date());
							shipmentLine.getShipments().setReceiveTime(new Date());
							this.shipmentsService.saveShipment(shipmentLine.getShipments());
							shipmentList.add(shipmentLine.getShipments());
						}
					}
					
				}
			}

			
			
			if(shipPLine>0){
				sentPackage +="</Package></Receive>";
				//System.out.println("AAAAAAAAAAAAAA"+sentPackage);
				//sent xml
				this.readReceiveXmlService.setInfoStringToXML(sentPackage,fileService.getUploadPath()+ "received", "received/");
				
				shipPLine=0;
				
				sentPackage="";
			}
			// 消除session中po receive2 chk note
			Struts2Util.getSession().removeAttribute("po");
			Struts2Util.getSession().removeAttribute("receive2");
			Struts2Util.getSession().removeAttribute("chk");
			Struts2Util.getSession().removeAttribute("note");
			// 保存成功
			out.print("<script>alert('Data saved successfully!')</script>");
			if (miss != null && !miss.equals("")) {
				out
						.print("<script>parent.location.href='work_order!workOrderManageFrame.action'</script>");
			} else {
				out
						.print("<script>parent.location.href='work_order!workOrderManageFrame.action'</script>");
			}
			out.flush();
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * PurchaseOrder SaveAll
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
public String purchaseOrderAllSave(){
		
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			Integer userId = SessionUtil.getUserId();
			// 从sesion里面获取list1
			List list1 = (List) Struts2Util.getSession().getAttribute("list1");
			// for循环
			int shipPLine = 0;
			String tmpId="";
			String listChks = "";
			List<Integer> tmpPoNos = new ArrayList<Integer>();
			List<Integer> pSrcSo = new ArrayList<Integer>();
			List<ReceivingLog> updatePObyReceList = new ArrayList<ReceivingLog>();
			for(int i = 0;i<list1.size();i++){
				PurchaseOrderItemDTO poiDTO = (PurchaseOrderItemDTO) list1.get(i);
				if(listChks.equals("")){
					listChks = poiDTO.getOrderItemId()+"";
				}else{
					listChks +=(","+ poiDTO.getOrderItemId());
				}
			}
			List<PurchaseOrderItemDTO> poiDTOList = this.purchaseOrderItemService.searchPurchaseByItemId(listChks);
			String sentPackage="<?xml version=\"1.0\" encoding=\"utf-8\"?><Receive><Package>";
			for (int i = 0; list1 != null && i < list1.size(); i++) {
				PurchaseOrderItemDTO poi1 = (PurchaseOrderItemDTO) list1.get(i);
				PurchaseOrderItemDTO poi = new PurchaseOrderItemDTO();
				for(PurchaseOrderItemDTO poiDTO :poiDTOList){
					
					if(poi1.getOrderItemId().equals(poiDTO.getOrderItemId())){
						poi = poiDTO;
						break;
					}
				}
				PoReceivingTmp tmp = poi.getTmp();
				String size_ = poi1.getSize().toString();
				String quantity_ = poi1.getQuantity().toString();
				if(tmp!=null){
					PurchaseOrder purchaseOrder = poi.getPurchaseOrder();
					if(purchaseOrder!=null){
						List<Reservation> reservationList = this.reservationService.getReservationList(purchaseOrder.getSrcSoNo(), tmp.getPoLineNo());
						Reservation reservation = new Reservation();
						if(reservationList!=null&&!reservationList.isEmpty()){
							reservation = reservationList.get(0);
							if(reservation.getQty().equals(1)){
								reservation.setQty(1);
								reservation.setSize(Arith.add(reservation.getSize(), Double.valueOf(size_)));
								tmp.setQty(1);
								if(tmp.getSize()>Double.valueOf(size_)){
									tmp.setSize(Arith.sub(tmp.getSize(), Double.valueOf(size_)));
								}else{
									tmp.setSize(0.0);
								}
							}else{
								reservation.setQty(reservation.getQty()+Integer.valueOf(quantity_));
								if(tmp.getQty()>Integer.valueOf(quantity_)){
									tmp.setQty(tmp.getQty()-Integer.valueOf(quantity_));
								}else{
									tmp.setQty(0);
								}
							}
						}else{
							PurchaseOrderItem purchaseOrderItem = poi.getPorderItem();
							if(purchaseOrderItem!=null){
								reservation.setOrderNo(purchaseOrder.getSrcSoNo());
								reservation.setItemNo(tmp.getPoLineNo());
								reservation.setQtyUom(purchaseOrderItem.getQtyUom());
								reservation.setSizeUom(purchaseOrderItem.getSizeUom());
								//reservation.setStockDetailId(111);
								reservation.setCatalogNo(purchaseOrderItem.getCatalogNo());
								if(purchaseOrderItem.getQuantity().equals(1)){
									reservation.setQty(1);
									reservation.setSize(Double.valueOf(size_));
									tmp.setQty(1);
									if(tmp.getSize()>Double.valueOf(size_)){
										tmp.setSize(Arith.sub(tmp.getSize(), Double.valueOf(size_)));
									}else{
										tmp.setSize(0.0);
									}
								}else{
									reservation.setQty(Integer.valueOf(quantity_));
									if(tmp.getQty()>Integer.valueOf(quantity_)){
										tmp.setQty(tmp.getQty()-Integer.valueOf(quantity_));
									}else{
										tmp.setQty(0);
									}
								}
							}
						}
						reservation.setReserveDate(tmp.getReceivingTime());
						reservation.setReservedBy(userId);
						this.reservationService.saveReservation(reservation);
						if((tmp.getQty().equals(1)&&tmp.getSize().equals(0.0))||tmp.getQty().equals(0)){
							/*Integer tmpPoNo = tmp.getPoNo();
							this.purchaseOrderItemService.delPoPeceiveTmp(tmp);
							List<PoReceivingTmp> tmpReceived = this.purchaseOrderItemService.searchReceiveTmp(tmpPoNo);
							if(tmpReceived==null||tmpReceived.isEmpty()){
								purchaseOrder.setStatus("Received");
								this.purchaseOrderService.savePO(purchaseOrder);
								//this.readReceiveXmlService.setInfoXML(purchaseOrder.getSrcSoNo(), purchaseOrder.getStatus(), "/tmp/received", "received/");
							}*/
							if(tmpId.equals("")){
								tmpId = tmp.getId()+"";
							}else{
								tmpId += (","+tmp.getId());
							}
							//this.purchaseOrderItemService.delPoPeceiveTmp(tmp);
							//List<PoReceivingTmp> tmpReceived = this.purchaseOrderItemService.searchReceiveTmp(tmpPoNo);
							String isAddTmpPO = "1";
							for(Integer tmpPoNo : tmpPoNos){
								if(tmpPoNo.equals(tmp.getPoNo())){
									isAddTmpPO="0";
								}
							}
							if(isAddTmpPO.equals("1")){
								tmpPoNos.add(tmp.getPoNo());
							}
						}else{
							this.purchaseOrderItemService.savePoPeceiveTmp(tmp);
						}
						
						/*List<ShipmentLine> shipmentLineList = this.shipmentLinesService.getLineByOrderNo(purchaseOrder.getSrcSoNo());
						if(shipmentLineList!=null&&!shipmentLineList.isEmpty()){
							for(ShipmentLine shipmentLine : shipmentLineList){
								if(!shipmentLine.getShipments().getStatus().equals("Invalid")){
									shipmentLine.getShipments().setModifyDate(new Date());
									shipmentLine.getShipments().setReceiveTime(new Date());
									this.shipmentsService.saveShipment(shipmentLine.getShipments());
									break;
								}
							}
						}*/
						String isAddSrcSoNo = "1";
						for(Integer srcSoNo : pSrcSo){
							if(purchaseOrder.getSrcSoNo().equals(srcSoNo)){
								isAddSrcSoNo="0";
							}
						}
						if(isAddSrcSoNo.equals("1")){
							pSrcSo.add(purchaseOrder.getSrcSoNo());
						}
						sentPackage += "<line><soNo>"+purchaseOrder.getSrcSoNo()+"</soNo>";
						/*String po = this.shipmentsService.getPOBySO(purchaseOrder.getOrderNo());
						sentPackage +="<po>"+po+"</po>";*/
						sentPackage +="<itemNo>"+tmp.getPoLineNo()+"</itemNo>";
						/*if(shipPackageLine.getSize()!=null){
							sentPackage +="<size>"+shipPackageLine.getSize()+"</size>";
						}else{
							sentPackage +="<size></size>";
						}*/
						//
						OrderItem orderItem = this.orderItemDao.getOrderItem(purchaseOrder.getSrcSoNo(), tmp.getPoLineNo());
						if("SERVICE".equals(orderItem.getType())&&orderItem.getClsId()==3&&"SC1010".equals(orderItem.getCatalogNo())){
							OrderGeneSynthesis orderGeneSynthesis = this.orderGeneSynthesisDao.getById(orderItem.getOrderItemId());
							if(orderGeneSynthesis!=null){
								sentPackage +="<qty>"+orderGeneSynthesis.getSeqLength()+"</qty>";
							}else{
								sentPackage +="<qty>"+quantity_+"</qty>";
							}
						}else{
							if(size_!=null&&!size_.equals("0")&&orderItem.getSize()!=null&&orderItem.getSize()!=0){
								Double sizeR = Arith.div(Double.valueOf(size_), Arith.mul(orderItem.getSize(),orderItem.getQuantity()));
								sentPackage +="<qty>"+sizeR+"</qty>";
							}else{
								sentPackage +="<qty>"+quantity_+"</qty>";
							}
						}
						
						OrderMain order = this.orderDao.getById(purchaseOrder.getSrcSoNo());
						String company = "";
						if(order!=null){
							//Customer customer = this.customerDao.getById(order.getCustNo());
							/*OrderAddress orderAddress = orderAddressDao.getAddrByOrderNoAndType(purchaseOrder.getOrderNo(), "BILL_TO");
							if(orderAddress!=null){
								company = billTerritoryDao.getAccountCode(orderAddress.getCountry(), orderAddress.getState(), orderAddress.getZipCode());
							}*/
							if(order.getGsCoId()==1){
								company = "GSUS";
							}
							if(order.getGsCoId()==2){
								company = "GSNJ";
							}
							if(order.getGsCoId()==3){
								company = "GSPK";
							}
							if(order.getGsCoId()==4){
								company = "GSHK";
							}
						}
						
						sentPackage +="<company>"+company+"</company></line>";
						
						shipPLine++;
					}
				}
			
			
				// 获取当前时间
				java.util.Date utildate = new java.util.Date();
				ReceivingLog rl = new ReceivingLog();
				// 把值set到ReceivingLogs中
				//rl.setOrderType(poi.getPurchaseOrder().getOrderType());
				rl.setOrderType("Purchase Order");
				rl.setTrackingNo(poi1.getTrackingNo());
				rl.setOrderNo(poi1.getPurchaseOrder().getOrderNo());
				rl.setItemNo(poi1.getItemNo());
				rl.setCatalogNo(poi1.getCatalogNo());
				rl.setQtyReceived(poi1.getQuantity());
				rl.setQtyUom(poi1.getQtyUom());
				rl.setSizeReceived(poi1.getSize());
				rl.setSizeUom(poi1.getSizeUom());

				ReceivingLog rlss = (ReceivingLog) Struts2Util.getSession()
						.getAttribute("rlll");
				rl.setStorageId(rlss.getStorageId());
				rl.setWarehouseId(rlss.getWarehouseId());
				rl.setLocationCode(rlss.getLocationCode());
				rl.setReceivingNote(rlss.getReceivingNote());
				rl.setReveivingDate(utildate);
				rl.setReceivedBy(SessionUtil.getUserId());
				// 执行添加方法
				this.receivingLogService.addReceivingLogs(rl);

				// 获取trackingNo、itemNo、orderNo
				String isAddRece = "1";
				for(ReceivingLog receivingLog :updatePObyReceList){
					if(receivingLog.getOrderNo().equals(rl.getOrderNo())){
						isAddRece = "0";
						break;
					}
				}
				if(isAddRece.equals("1")){
					updatePObyReceList.add(rl);
				}
				//String trackingNo = rl.getTrackingNo();
				/*Integer itemNo = rl.getItemNo();
				Integer orderNo = rl.getOrderNo();
				// 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
				boolean isTrue = this.purchaseOrderService.findReceiveLineFlag(orderNo);

				// 更新spk_lines状态
				if (isTrue) {
					//String orderNos = listtio.get(0);
					this.purchaseOrderService.updateReceiveFlag(orderNo);
				}*/
				/*boolean flg = true;
				String packageId = "";
				// ship_package_lines,ship_packages
				// 根据orderNo和itemNo查询，判断是否需要更新状态
				List listSpk_lines = this.shipPackageLineService.findSpk_lines(
						orderNo, itemNo);
				// for循环遍历
				for (int k = 0; k < listSpk_lines.size(); k++) {
					Object[] obj = (Object[]) listSpk_lines.get(k);
					// 判断状态
					if (!"Received".equals(obj[0] + "")) {
						flg = false;
						break;
					}
					// 获取packageId
					packageId = obj[1] + "";
				}
				// 当flg==true的时候，执行更新
				if (flg == true) {
					this.shipPackageLineService.updateShipPkReceive(packageId);
				}*/
				if(shipPLine>200){
					sentPackage +="</Package></Receive>";
					//sent xml
					this.readReceiveXmlService.setInfoStringToXML(sentPackage, fileService.getUploadPath()+"received", "received/");
					shipPLine=0;
					sentPackage="<?xml version=\"1.0\" encoding=\"utf-8\"?><Receive><Package>";
				}
			}
			if(shipPLine>0){
				sentPackage +="</Package></Receive>";
				//System.out.println("AAAAAAAAAAAAAA"+sentPackage);
				//sent xml
				this.readReceiveXmlService.setInfoStringToXML(sentPackage, fileService.getUploadPath()+"received", "received/");
				
				shipPLine=0;
				
				sentPackage="";
			}
			
			if(!tmpId.equals("")){
				this.purchaseOrderItemService.delPoPeceiveTmps(tmpId);
			}

			for(Integer tmpPoNo : tmpPoNos){
				List<PoReceivingTmp> tmpReceived = this.purchaseOrderItemService.searchReceiveTmp(tmpPoNo);
				if(tmpReceived==null||tmpReceived.isEmpty()){
					this.purchaseOrderService.updatePO(tmpPoNo,"Received");
				}
			}
			for(ReceivingLog rl: updatePObyReceList){
				Integer orderNo = rl.getOrderNo();
				// 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
				boolean isTrue = this.purchaseOrderService.findReceiveLineFlag(orderNo);

				// 更新spk_lines状态
				if (isTrue) {
					//String orderNos = listtio.get(0);
					this.purchaseOrderService.updateReceiveFlag(orderNo);
				}
			}
			List<ShipmentLine> shipmentLineList = this.shipmentsService.getShipmentByOrderNo(pSrcSo);
			if(shipmentLineList!=null&&!shipmentLineList.isEmpty()){
				List<Shipment> shipmentList = new ArrayList<Shipment>();
				for(ShipmentLine shipmentLine : shipmentLineList){
					String isUpdate = "1";
					for(Shipment shipment : shipmentList){
						if(shipmentLine.getShipments().getShipmentId().equals(shipment.getShipmentId())){
							isUpdate = "0";
						}
					}
					if(isUpdate .equals("1")){
						if(!shipmentLine.getShipments().getStatus().equals("Invalid")){
							shipmentLine.getShipments().setModifyDate(new Date());
							shipmentLine.getShipments().setReceiveTime(new Date());
							this.shipmentsService.saveShipment(shipmentLine.getShipments());
							shipmentList.add(shipmentLine.getShipments());
						}
					}
					
				}
			}
			/*}*/
			// 消除session中的receive1和rlll
			Struts2Util.getSession().removeAttribute("receive1");
			Struts2Util.getSession().removeAttribute("rlll");
			// 保存成功
			out.print("<script>alert('Data saved successfully!')</script>");

			// 后退一步
			//out.print("<script>history.back()</script>");
			out.print("<script>location.href='work_order!workOrderManageFrame.action'</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	

	/**
	 * WorkOrder SaveAll
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String workOrderAllSave() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			// 从session中获取list2
			List list = (List) Struts2Util.getSession().getAttribute("list2");
			// 判断list是否为空
			if (list != null && list.size() > 0) {
				// for循环
				for (int i = 0; i < list.size(); i++) {
					WorkOrderDTO poi = (WorkOrderDTO) list.get(i);
					// 获取当前时间
					java.util.Date utildate = new java.util.Date();
					ReceivingLog rl = new ReceivingLog();
					// 把值set到ReceivingLogs中
					//rl.setOrderType(poi.getType());
					rl.setOrderType("Work Order");
					rl.setOrderNo(poi.getOrderNo());
					rl.setCatalogNo(poi.getCatalogNo());
					rl.setQtyReceived(poi.getQuantity());
					rl.setQtyUom(poi.getQtyUom());
					rl.setSizeReceived(poi.getSize());
					rl.setSizeUom(poi.getSizeUom());
					rl.setTrackingNo(poi.getBatchNo());
					ReceivingLog rlss = (ReceivingLog) Struts2Util.getSession()
							.getAttribute("rllss");
					rl.setStorageId(rlss.getStorageId());
					rl.setWarehouseId(rlss.getWarehouseId());
					rl.setLocationCode(rlss.getLocationCode());
					rl.setReceivingNote(rlss.getReceivingNote());
					rl.setReveivingDate(utildate);
					rl.setReceivedBy(SessionUtil.getUserId());
					// 执行添加方法
					this.receivingLogService.addReceivingLogs(rl);

					// 获取batchNo、itemNo、orderNo
					String batchNo = rl.getTrackingNo();
					String orderNo = rl.getOrderNo() + "";
					// 查询到的order_no的状态将被更新
					List listtio = this.workOrderService
							.findWoBatchDetails(orderNo);
					String status = "Received";
					if (listtio != null && listtio.size() > 0) {
						// 获取orderNo
						Object[] obj = (Object[]) listtio.get(0);
						Integer orderNos = Integer.parseInt(obj[0] + "");
						this.workOrderService.updateWorkOrderStatus(orderNos,
								status);
					}
					boolean flg = true;
					Integer woBatchId = 0;
					// 根据batchNo判断是否要更新woBatch表
					List listWoBatchDetail = this.workOrderService
							.findWoBatchIdDetailss(batchNo);
					for (int k = 0; k < listWoBatchDetail.size(); k++) {
						Object[] obj = (Object[]) listWoBatchDetail.get(k);
						woBatchId = Integer.parseInt(obj[0] + "");
					}
					// 当flg==true的时候，执行更新方法
					if (flg == true) {
						this.workOrderService.updateWorkBatchStatus(woBatchId,
								status);
					}
				}
			}
			// 消除session中的receivewo2
			Struts2Util.getSession().removeAttribute("receivewo2");
			// 执行成功
			out.print("<script>alert('Data saved successfully!')</script>");
			//out.print("<script>history.back()</script>");
			String MANUFACTURING = "MANUFACTURING";
			out.print("<script>location.href='work_order!workOrderManageFrame.action?type="+MANUFACTURING+"'</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * 点击PurchaseOrder中orderNo进去中，显示的ViewPackage
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
		public String purchaseViewPackage() throws Exception {
		Integer loginName = SessionUtil.getUserId();
		String loginNames = SessionUtil.getUserName();
		try {
				// 获取itemNo
				Integer itemNo = Integer.parseInt(Struts2Util.getRequest()
						.getParameter("itemNo"));
				// 根据itemNo查询，判断是null
				String cks = Struts2Util.getRequest()
						.getParameter("cks");
				String[] ckss = cks.split(","); 
				if(ckss==null||ckss.length<2||ckss[1]==null||ckss[1].equals("")){
					return null;
				}
				ShipPackage sp = this.shipPackageLineService.getShippageByTracking(ckss[1]);
				if (sp != null) {
					Integer packageId = sp.getPackageId();
					// 实例化一个pageUtil对象
					PagerUtil<ShipPackageLineDTO> pagerUtil = new PagerUtil<ShipPackageLineDTO>();
					pageShipPackageLineDTO = pagerUtil.getRequestPage();
					pageShipPackageLineDTO.setPageSize(10);
					List<PropertyFilter> filters = WebUtil
							.buildPropertyFilters(Struts2Util.getRequest());
					Page<ShipPackageLines> retPage = this.dozer.map(
							pageShipPackageLineDTO, Page.class);
					// 判断filters
					if (filters == null || filters.isEmpty()) {
						// 执行分页方法
						pageShipPackageLineDTO = this.shipPackageLineService
								.getListSpl(retPage, packageId);
					}
					Integer count = pageShipPackageLineDTO.getResult().size();
					Struts2Util.getRequest().setAttribute("count", count);
					Struts2Util.getRequest().setAttribute("pagerInfo",
							pageShipPackageLineDTO);
					if(sp.getShippingClerk()!=null && sp.getShippingClerk()!=null){
						User user = this.shipPackageLineService.getByUserId(sp.getShippingClerk());
						if(user !=null){
							Struts2Util.getRequest().setAttribute("userName", user.getLoginName());
						}
					}
				}
				String poPk = "pk";
				Struts2Util.getRequest().setAttribute("poPk", poPk);
				Struts2Util.getRequest().setAttribute("sp", sp);
				Struts2Util.getRequest().setAttribute("loginName", loginName);
				Struts2Util.getRequest().setAttribute("loginNames", loginNames);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "package_information";
	}

	/**
	 * receive_order_po中Detail
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String getReceiveOrderPo(){
		try {
//			String chksShow = Struts2Util.getParameter("chksShow");
//			if(chksShow !=null && chksShow.length()>1){
//				chksShow = chksShow.substring(0,chksShow.length()-1);
//			}
			// 获取orderNo
			Integer orderNo = Integer.parseInt(Struts2Util.getRequest()
				.getParameter("orderNo"));
		
			// 实例化一个pageUtil对象
			PagerUtil<PurchaseOrderItemDTO> pagerUtil = new PagerUtil<PurchaseOrderItemDTO>();
			pagePurchaseOrderItemDTO = pagerUtil.getRequestPage();
			// 分页大小
			pagePurchaseOrderItemDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<PurchaseOrderItem> retPage = this.dozer.map(
					pagePurchaseOrderItemDTO, Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				pagePurchaseOrderItemDTO = this.purchaseOrderItemService
						.getPoPage(retPage, orderNo);
			}
			// 判断pagePurchaseOrderItemDTO是否为null
			Integer count = 0;
			if (pagePurchaseOrderItemDTO == null) {
				count = 0;
			} else {
				count = pagePurchaseOrderItemDTO.getResult().size();
			}
			// 把相应的数据放到request里面
			Struts2Util.getRequest().setAttribute("pagerInfo",
					pagePurchaseOrderItemDTO);
			Struts2Util.getRequest().setAttribute("poSize", count);
			//处理的时候用的list
			List list = this.purchaseOrderItemService
					.findPurchaseOrderDetail(orderNo);
			Struts2Util.getSession().setAttribute("list1", list);
			
			Struts2Util.getRequest().setAttribute("orderNo", orderNo);
//			Struts2Util.getRequest().setAttribute("chksShow", chksShow);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "receive_order_po";
	}

	/**
	 * receive_order_wo的Dtail
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String getReceiveOrderWo() throws Exception {
		// 获取orderNo
		Integer orderNo = Integer.parseInt(Struts2Util.getRequest()
				.getParameter("orderNo"));
		// 实例化WorkOrderDTo对象
		WorkOrderDTO wd = new WorkOrderDTO();
		// 把orderNo set进去
		wd.setOrderNo(orderNo);
		try {
			// 实例化一个pageUtil对象
			PagerUtil<WorkOrderDTO> pagerUtil = new PagerUtil<WorkOrderDTO>();
			pageWorkOrderDTO = pagerUtil.getRequestPage();
			pageWorkOrderDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<WorkOrder> retPage = this.dozer.map(pageWorkOrderDTO,
					Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				pageWorkOrderDTO = this.workOrderService.searchwo(retPage, wd);
			}
			// 判断pageWorkOrderDTO是否为null
			Integer count = 0;
			if (pageWorkOrderDTO == null) {
				count = 0;
			} else {
				count = pageWorkOrderDTO.getResult().size();
			}
			// 把相应的数据放在request或者session里面
			Struts2Util.getRequest().setAttribute("count", count);
			Struts2Util.getRequest()
					.setAttribute("pagerInfo", pageWorkOrderDTO);
			List list2 = this.workOrderService.findWorkOrderDetail(orderNo);
			Struts2Util.getSession().setAttribute("list2", list2);
			Struts2Util.getRequest().setAttribute("orderNo", orderNo);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "receive_order_wo";
	}

	/**
	 * Batch Order Recived进去的 view_package
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String getPackageInformation() throws Exception {
		// 获取trackingNo
		String trackingNo = Struts2Util.getRequest().getParameter("trackingNo");
		// 根据trackingNo查询
		ShipPackage sp = this.purchaseOrderService.getShipPageInfo(trackingNo);
		Integer loginName = SessionUtil.getUserId();
		String loginNames = SessionUtil.getUserName();
		try {
			if (sp != null) {
				// 获取packageId
				Integer packageId = sp.getPackageId();
				// 实例化一个pageUtil对象
				PagerUtil<ShipPackageLineDTO> pagerUtil = new PagerUtil<ShipPackageLineDTO>();
				pageShipPackageLineDTO = pagerUtil.getRequestPage();
				pageShipPackageLineDTO.setPageSize(10);
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<ShipPackageLines> retPage = this.dozer.map(
						pageShipPackageLineDTO, Page.class);
				// 判断filters
				if (filters == null || filters.isEmpty()) {
					pageShipPackageLineDTO = this.shipPackageLineService
							.getListSpl(retPage, packageId);
				}
				// 获取查询count的大小
				Integer count = pageShipPackageLineDTO.getResult().size();
				// 放到request里卖弄
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo",
						pageShipPackageLineDTO);
				if(sp.getShippingClerk()!=null && sp.getShippingClerk()!=null){
						User user = this.shipPackageLineService.getByUserId(sp.getShippingClerk());
						if(user !=null){
							Struts2Util.getRequest().setAttribute("userName", user.getLoginName());
						}
					}
			}
			Struts2Util.getRequest().setAttribute("sp", sp);
			Struts2Util.getRequest().setAttribute("loginName", loginName);
			Struts2Util.getRequest().setAttribute("loginNames", loginNames);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "package_information";
	}

	/**
	 * 模式对话框BatchOrderReceiving
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String batchOrderReceiving() throws Exception {
		// 获取warehouse
		String warehouses = Struts2Util.getRequest().getParameter("warehouse");
		// 获取chks
		String chks = Struts2Util.getRequest().getParameter("cks");

		String[] chkss = chks.split(",");
		String vendorName = "";
		boolean flg = true;
		List list = new ArrayList();
		chks = "";
		for (int i = 0; i < chkss.length; i++) {
			if (i % 2 == 0) {
				chks += chkss[i] + ",";
			} else {
				list.add(chkss[i]);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			vendorName = (String) list.get(0);
			if (!vendorName.equals(list.get(i) + "")) {
				flg = false;
				break;
			}
		}
		if (flg == false)
			vendorName = "";
		chks = chks.substring(0, chks.length() - 1);

		Warehouse wh = this.workOrderService.findByType(warehouses);
		String warehouse = wh.getName();
		String loginName = SessionUtil.getUserName();
		try {
			Struts2Util.getRequest().setAttribute("chks", chks);
			Struts2Util.getRequest().setAttribute("vendorName", vendorName);
			Struts2Util.getRequest().setAttribute("warehouses", warehouses);
			Struts2Util.getRequest().setAttribute("warehouse", warehouse);
			Struts2Util.getRequest().setAttribute("loginName", loginName);
			if (warehouses.equals("SALES")) {
				// 查询符合条件的TarckingNo
				List TnLists = this.purchaseOrderService.getListTrackingNo();
				Struts2Util.getRequest().setAttribute("TnLists", TnLists);
			} else if (warehouses.equals("MANUFACTURING")) {
				// 查询符合Batch ID
				List<WoBatche> BaList = this.workOrderService.getListBatchNo();
				// 放到request
				Struts2Util.getRequest().setAttribute("BaList", BaList);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "batch_order_receiving";
	}

	/**
	 * PurchaseOrder chks Process
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings( { "unchecked", "null" })
	public String getPurchaseReceivingDetail() {
		try {
//			String chksShow = Struts2Util.getRequest().getParameter("chksShow");
//			if(chksShow !=null && chksShow.length()>1){
//				chksShow = chksShow.substring(0,chksShow.length()-1);
//			}
			String wh = "ok";
			Struts2Util.getRequest().setAttribute("whSales", wh);
			// 获取值
			String chks = Struts2Util.getRequest().getParameter("chks")
					.toString();

			String[] chk2 = chks.split(",");
			List list1 = this.purchaseOrderItemService.searchPoIDetailAll(chk2);
			Struts2Util.getSession().setAttribute("list1", list1);
			Struts2Util.getRequest().setAttribute("list1Size", list1.size());
			
			// 实例化一个pageUtil对象
			PagerUtil<PurchaseOrderItemDTO> pagerUtil = new PagerUtil<PurchaseOrderItemDTO>();
			pagePurchaseOrderItemDTO = pagerUtil.getRequestPage();
			// 分页大小
			pagePurchaseOrderItemDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<PurchaseOrderItem> retPage = this.dozer.map(
					pagePurchaseOrderItemDTO, Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				pagePurchaseOrderItemDTO = this.purchaseOrderItemService
						.searchPoIDetailAll2(retPage, chks);
			}
			// 把相应的数据放到request里面
			Struts2Util.getRequest().setAttribute("pagerInfo",
					pagePurchaseOrderItemDTO);
			Struts2Util.getRequest().setAttribute("chks", chks);
//			Struts2Util.getRequest().setAttribute("chksShow", chksShow);
			
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "batch_order_receiving_po_detail";
	}

	/**
	 * PurchaseOrder trackingNo Process
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings( { "unchecked", "null" })
	public String getPurchaseReceivingTrackingNoDetail() {
		try {
			String trackingNo = Struts2Util.getRequest().getParameter(
					"trackingNo");
			String wh = "ok";
			Struts2Util.getRequest().setAttribute("whSales", wh);
			List list1 = this.purchaseOrderItemService
					.searchPoIDetails(trackingNo);
			Struts2Util.getSession().setAttribute("list1", list1);
			Struts2Util.getRequest().setAttribute("list1Size", list1.size());
			// 实例化一个pageUtil对象
			PagerUtil<PurchaseOrderItemDTO> pagerUtil = new PagerUtil<PurchaseOrderItemDTO>();
			pagePurchaseOrderItemDTO = pagerUtil.getRequestPage();
			// 分页大小
			pagePurchaseOrderItemDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<PurchaseOrderItem> retPage = this.dozer.map(
					pagePurchaseOrderItemDTO, Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				pagePurchaseOrderItemDTO = this.purchaseOrderItemService
						.searchPoIDetails2(retPage, trackingNo);
			}
			// 把相应的数据放到request里面
			Struts2Util.getRequest().setAttribute("pagerInfo",
					pagePurchaseOrderItemDTO);
			Struts2Util.getRequest().setAttribute("trackingNo", trackingNo);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "batch_order_receiving_po_trackingNo_detail";
	}

	/**
	 * WorkOrder chks Process
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings( { "unchecked", "null" })
	public String getWorkOrderReceivingDetail() {
		try {
			String wh = "ok";
			Struts2Util.getRequest().setAttribute("whManu", wh);
			// 获取值
			String chks = Struts2Util.getRequest().getParameter("chks")
					.toString();

			String[] chk2 = chks.split(",");
			List list2 = this.workOrderService.searchWoIDetailAll(chk2);
			Struts2Util.getSession().setAttribute("list2", list2);
			Struts2Util.getRequest().setAttribute("list2Size", list2.size());

			// 实例化一个pageUtil对象
			PagerUtil<WorkOrderDTO> pagerUtil = new PagerUtil<WorkOrderDTO>();
			pageWorkOrderDTO = pagerUtil.getRequestPage();
			// 分页大小
			pageWorkOrderDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<WorkOrder> retPage = this.dozer.map(pageWorkOrderDTO,
					Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				pageWorkOrderDTO = this.workOrderService.searchPoIDetailAll2(
						retPage, chks);
			}
			// 把相应的数据放到request里面
			Struts2Util.getRequest()
					.setAttribute("pagerInfo", pageWorkOrderDTO);
			Struts2Util.getRequest().setAttribute("chks", chks);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "batch_order_receiving_wo_detail";
	}

	/**
	 * WorkOrder BatchNo Process
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings( { "unchecked", "null" })
	public String getWorkOrderReceivingBatchNoDetail() {
		try {
			String batchNo = Struts2Util.getRequest().getParameter("batchNo");
			String wh = "ok";
			Struts2Util.getRequest().setAttribute("whManu", wh);
			List list2 = this.workOrderService.searchWoByBatchNo(batchNo);
			Struts2Util.getSession().setAttribute("list2", list2);
			Struts2Util.getRequest().setAttribute("list2Size", list2.size());

			// 实例化一个pageUtil对象
			PagerUtil<WorkOrderDTO> pagerUtil = new PagerUtil<WorkOrderDTO>();
			pageWorkOrderDTO = pagerUtil.getRequestPage();
			// 分页大小
			pageWorkOrderDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<WorkOrder> retPage = this.dozer.map(pageWorkOrderDTO,
					Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				pageWorkOrderDTO = this.workOrderService.searchWoByBatchNo2(
						retPage, batchNo);
			}
			// 把相应的数据放到request里面
			Struts2Util.getRequest()
					.setAttribute("pagerInfo", pageWorkOrderDTO);
			Struts2Util.getRequest().setAttribute("batchNo", batchNo);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "batch_order_receiving_wo_batchNo_detail";
	}

	/**
	 * 转发到每个方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getBatchReceiving() throws IOException {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			// 获取值
			String[] chks = Struts2Util.getRequest().getParameterValues("chks");
			String trackingNo = Struts2Util.getRequest().getParameter(
					"trackingNo");
			String batchNo = Struts2Util.getRequest().getParameter("batchNos");
			String warehouse = Struts2Util.getRequest().getParameter(
					"warehouse");

			String chks2 = Struts2Util.getRequest().getParameter("chks");
			Struts2Util.getSession().setAttribute("chks", chks2);
			Struts2Util.getSession().setAttribute("trackingNo", trackingNo);
			Struts2Util.getSession().setAttribute("batchNo", batchNo);
			Struts2Util.getSession().setAttribute("warehouse", warehouse);
			String wh = "ok";
			// 判断warehouse类型
			if (warehouse.equals("SALES")) {
				Struts2Util.getRequest().setAttribute("whSales", wh);
				// 当多选框没选,trackingNo为空的时候
				if ((!chks[0].equals("") && null != chks && chks.length > 0)
						&& (trackingNo == null || trackingNo.length() <= 0)) {
					out
							.print("<script>window.location='work_order!getPurchaseReceivingDetail.action?chks="
									+ chks2 + "';</script>");
				}
				// orderitem 未勾选，trackingNo 选择的时候
				else if ((null == chks || chks[0].equals("") || chks.length <= 0)
						&& (trackingNo != null && trackingNo.length() > 0)) {

					out
							.print("<script>window.location='work_order!getPurchaseReceivingTrackingNoDetail.action?trackingNo="
									+ trackingNo + "';</script>");
				}// chks和trackingNo都选择的时候
				else if ((null != chks && !chks[0].equals("") && chks.length > 0)
						&& (trackingNo != null || trackingNo.length() > 0)) {
					out
							.print("<script>window.location='work_order!getPurchaseReceivingTrackingNoDetail.action?trackingNo="
									+ trackingNo + "';</script>");
				}
			} else if (warehouse.equals("MANUFACTURING")) {
				Struts2Util.getRequest().setAttribute("whManu", wh);
				// 当多选框没选,trackingNo为空的时候
				if ((!chks[0].equals("") && null != chks && chks.length > 0)
						&& (batchNo == null || batchNo.length() <= 0)) {
					out
							.print("<script>window.location='work_order!getWorkOrderReceivingDetail.action?chks="
									+ chks2 + "';</script>");
				}
				// orderitem 未勾选，trackingNo 选择的时候
				else if ((chks[0].equals("") || null == chks || chks.length <= 0)
						&& (batchNo != null || batchNo.length() > 0)) {
					out
							.print("<script>window.location='work_order!getWorkOrderReceivingBatchNoDetail.action?batchNo="
									+ batchNo + "';</script>");
				}
				// chks和batchNo都选择的时候
				else if ((!chks[0].equals("") && null != chks && chks.length > 0)
						&& (batchNo != null && batchNo.length() > 0)) {
					out
							.print("<script>window.location='work_order!getWorkOrderReceivingBatchNoDetail.action?batchNo="
									+ batchNo + "';</script>");
				}
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * 模式对话框CreateReceivingReport
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String createReceivingReport() throws Exception {
		return "create_receiving_report";
	}

	/**
	 * Receiving搜索serach页面
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String workOrderSearch() throws Exception {
		try {
			Integer userId = SessionUtil.getUserId();
			System.out.println("searchuserID="+userId);
			User user = this.userDao.getById(userId);
			boolean isProductionManagerRole = false;
			if (!Constants.USERNAME_ADMIN.equals(user.getLoginName())) {
	            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_RECEIVING_MANAGER);
	        }else{
	        	isProductionManagerRole = true;
	        }
			if(isProductionManagerRole){
				userId = null;
			}
			String type = Struts2Util.getRequest().getParameter("type");
			// 执行方法
			List<Warehouse> whList = this.workOrderService.getListByWarehouse();
			// 查询用户信息
			
			List rcList = this.workOrderService.getListByReceiving(userId);
			// 查询po状态
			List<PurchaseOrder> polist = this.purchaseOrderService
					.getPoStatus();
			// 查询wo状态
			//List<WorkOrder> wolist = this.workOrderService.getWoStatus();
			// 把值放在requst
			Struts2Util.getRequest().setAttribute("userId", userId);
			Struts2Util.getRequest().setAttribute("isProductionManagerRole", isProductionManagerRole);
			Struts2Util.getRequest().setAttribute("polist", polist);
			//Struts2Util.getRequest().setAttribute("wolist", wolist);
			Struts2Util.getRequest().setAttribute("whList", whList);
			Struts2Util.getRequest().setAttribute("rcList", rcList);
			Struts2Util.getRequest().setAttribute("type", type);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "workOrder_search";
	}

	/**
	 * 点击放大镜，显示vendors列表
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String vendorManageList() throws Exception {
		try {
			// 实例化一个pageUtil对象
			PagerUtil<VendorDTO> pagerUtil = new PagerUtil<VendorDTO>();
			pageVendorDTO = pagerUtil.getRequestPage();
			// 执行pageSize的大小
			pageVendorDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil
					.buildPropertyFilters(Struts2Util.getRequest());
			Page<Vendor> retPage = this.dozer.map(pageVendorDTO, Page.class);
			// 判断filters
			if (filters == null || filters.isEmpty()) {
				VendorDTO srch = null;
				// 执行查询方法
				pageVendorDTO = this.vendorService.searchwo(retPage, srch);
			}
			// 获取count
			Integer count = pageVendorDTO.getResult().size();
			// 放到request
			Struts2Util.getRequest().setAttribute("count", count);
			Struts2Util.getRequest().setAttribute("pagerInfo", pageVendorDTO);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return "vendor_manage_list";
	}

	/**
	 * 点击select，把值放到文本框中
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String workOrderSearch2() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			// 获取vendorName值
			String vendorName = Struts2Util.getRequest().getParameter(
					"vendorName");
			// 查询warehouse返回一个list集合
			List<Warehouse> whList = this.workOrderService.getListByWarehouse();
			// 查询用户
			List<User> rcList = this.workOrderService.getListByReceiving(null);
			// 放到request
			Struts2Util.getRequest().setAttribute("whList", whList);
			Struts2Util.getRequest().setAttribute("rcList", rcList);
			Struts2Util.getSession().setAttribute("vendorNames", vendorName);
			// 刷新父窗口
			out
					.print("<script>parent.frames['searchBody'].location.reload();</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * 更新到shipPackage
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	public String updateShipPackage() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			// 获取对象，执行更新方法
			this.shipPackageService.saveSps(shipPackages);
			// 更新成功
			out.print("<script>alert('Data updated successfully!')</script>");
			out.print("<script>history.back();</script>");
//			out
//					.print("<script>window.location='work_order!workOrderManageFrame.action'</script>");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		return null;
	}

	/**
	 * 查询列表方法
	 * 
	 * @return String 返回类型
	 * @throws Exception
	 *             抛出异常
	 */
	@SuppressWarnings("unchecked")
	public String viewprintReceivingReport() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			// 直接查询(本地版本)
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String type = Struts2Util.getRequest().getParameter("type");
//			String fromDate = Struts2Util.getRequest().getParameter("fromDate");
//			String toDate = Struts2Util.getRequest().getParameter("toDate");
			
			// 获取页面时间(localhost)
//			String type = Struts2Util.getSession().getAttribute("type")+"";
//			Date fromDate = format.parse(Struts2Util.getSession().getAttribute("fromDate")+ "");
//			Date toDate = format.parse(Struts2Util.getSession().getAttribute("toDate")+ "");
			 
			// 调用打印方法(服务器版本)
			String type = Struts2Util.getSession().getAttribute("type")+"";
			String clerk = Struts2Util.getSession().getAttribute("clerk")+"";
			String fromDate = Struts2Util.getSession().getAttribute("fromDate")+ "";
			String toDate = Struts2Util.getSession().getAttribute("toDate")+ "";
			System.out.println("clerk="+clerk);
			// 调用方法
			List<ReceivingLog> listOrder = this.receivingLogService.getLogs(fromDate, toDate,type,clerk);
			String logSize = "";
			if (listOrder != null && listOrder.size() > 0) {
				String orderTemp ="";
				// 循环
				for (int i = 0; i < listOrder.size(); i++) {
					String orderNo = listOrder.get(i) + "";
					orderTemp +=orderNo+",";
				}
				if(orderTemp !=null && orderTemp.length()>1){
					orderTemp = orderTemp.substring(0, orderTemp.length()-1);
				}
				List listRl = this.receivingLogService.getLogsByOrderNo(fromDate, toDate, type, orderTemp,clerk);
				
				logSize += "Order Total: "+listOrder.size();
				if(listRl!=null&&!listRl.isEmpty()){
					logSize += " , Item Total: "+listRl.size();
				}else{
					logSize += " , Item Total: 0";
				}
				
				Struts2Util.getRequest().setAttribute("now", format.format(new Date()));
				Struts2Util.getRequest().setAttribute("listRl", listRl);
				Struts2Util.getSession().removeAttribute("fromDate");
				Struts2Util.getSession().removeAttribute("toDate");
				// 存放在request
				Struts2Util.getRequest().setAttribute("listOrder", listOrder);
				Struts2Util.getRequest().setAttribute("type", type);
				Struts2Util.getRequest().setAttribute("logSize", logSize);
				
			} 
			// 转向页面
			return "viewprintReceivingReport";
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			return null;
		}
		//return null;
	}

	/**
	 * 调用第三方组件执行转换PDF
	 * @return
	 */
	@SuppressWarnings("static-access")
	public void print() throws Exception {
		// 清session
		Struts2Util.getSession().removeAttribute("fromDate");
		Struts2Util.getSession().removeAttribute("toDate");
		Struts2Util.getSession().removeAttribute("type");
		Struts2Util.getSession().removeAttribute("clerk");
		// 获取fromDate和toDate时间
		String fromDate = Struts2Util.getRequest().getParameter("fromDate");
		String toDate = Struts2Util.getRequest().getParameter("toDate");
		String type = Struts2Util.getRequest().getParameter("type");
		String clerk = Struts2Util.getRequest().getParameter("clerk");
		Struts2Util.getSession().setAttribute("fromDate", fromDate);
		Struts2Util.getSession().setAttribute("toDate", toDate);
		Struts2Util.getSession().setAttribute("type", type);
		Struts2Util.getSession().setAttribute("clerk", clerk);
		HttpURLConnection con = null;
		HttpsURLConnection connection =null;
		URL url = null;
		Process p = null;
		String cmd = "html2pdf";
		String newip = this.hostip.getLocalIP();
		//System.out.print("newIP========================================================="+newip);
		try {
			String sessionid = Struts2Util.getRequest().getSession().getId();
			//System.out.println("sessionid==" + sessionid);
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
			url = new URL(basePath+"work_order!viewprintReceivingReport.action");
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
			File file = new File("/tmp/ok1.html");
			writer = new FileWriter(file);
			writer.write(strb.toString());
			//System.out.print("writer==" + writer);
			writer.flush();
			writer.close();
			System.out.println("打印页面的内容==" + strb.toString());
			ProcessBuilder pb = new ProcessBuilder(cmd, "/tmp/ok1.html","/tmp/ok1.pdf");
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
					+ "PrintReceivingReport" + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream("/tmp/ok1.pdf");
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
			p.destroy();
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			Struts2Util.renderText(exDTO.getMessageDetail()+"\n"+exDTO.getAction());
			System.out.println("Error exec!");
		}
	}
	
	public String searchOrderItem(){
		String orderNo = Struts2Util.getRequest().getParameter("orderNo");
		if(orderNo!=null){
			this.orderItemList=this.orderItemDao.getOrderCCItemList(Integer.valueOf(orderNo));
		}else{
			this.orderItemList = null;
		}
		Struts2Util.getRequest().setAttribute("orderNo", orderNo);
		return "printOrderItemDoc";
	}
	
	public List<Storage> getStorageList(){
		return this.storageService.searchStorageByType("LOCATION");
	}
	
	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	public WorkOrderService getWorkOrderService() {
		return workOrderService;
	}

	public void setWorkOrderService(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	public Page<WorkOrderDTO> getPageWorkOrderDTO() {
		return pageWorkOrderDTO;
	}

	public void setPageWorkOrderDTO(Page<WorkOrderDTO> pageWorkOrderDTO) {
		this.pageWorkOrderDTO = pageWorkOrderDTO;
	}

	public Page<WorkOrder> getPageWorkOrder() {
		return pageWorkOrder;
	}

	public void setPageWorkOrder(Page<WorkOrder> pageWorkOrder) {
		this.pageWorkOrder = pageWorkOrder;
	}

	public WorkOrderDTO getWorkOrderDTO() {
		return workOrderDTO;
	}

	public void setWorkOrderDTO(WorkOrderDTO workOrderDTO) {
		this.workOrderDTO = workOrderDTO;
	}

	public List<WorkOrderDTO> getListWorkOrder() {
		return listWorkOrder;
	}

	public void setListWorkOrder(List<WorkOrderDTO> listWorkOrder) {
		this.listWorkOrder = listWorkOrder;
	}

	public VendorService getVendorService() {
		return vendorService;
	}

	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	public Page<VendorDTO> getPageVendorDTO() {
		return pageVendorDTO;
	}

	public void setPageVendorDTO(Page<VendorDTO> pageVendorDTO) {
		this.pageVendorDTO = pageVendorDTO;
	}

	public Page<PurchaseOrderDTO> getPagePurchaseOrderDTO() {
		return pagePurchaseOrderDTO;
	}

	public void setPagePurchaseOrderDTO(
			Page<PurchaseOrderDTO> pagePurchaseOrderDTO) {
		this.pagePurchaseOrderDTO = pagePurchaseOrderDTO;
	}

	public PurchaseOrderService getPurchaseOrderService() {
		return purchaseOrderService;
	}

	public void setPurchaseOrderService(
			PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}

	public PurchaseOrderDTO getPurchaseOrderDTO() {
		return purchaseOrderDTO;
	}

	public void setPurchaseOrderDTO(PurchaseOrderDTO purchaseOrderDTO) {
		this.purchaseOrderDTO = purchaseOrderDTO;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public WorkGroup getWorkGroup() {
		return workGroup;
	}

	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}

	public ShipPackage getShipPackages() {
		return shipPackages;
	}

	public void setShipPackages(ShipPackage shipPackages) {
		this.shipPackages = shipPackages;
	}

	public Page<OrderItem> getPageOrderItems() {
		return pageOrderItems;
	}

	public void setPageOrderItems(Page<OrderItem> pageOrderItems) {
		this.pageOrderItems = pageOrderItems;
	}

	public Page<OrderItemDTO> getPageOrderItemDTO() {
		return pageOrderItemDTO;
	}

	public void setPageOrderItemDTO(Page<OrderItemDTO> pageOrderItemDTO) {
		this.pageOrderItemDTO = pageOrderItemDTO;
	}

	public ShipPackageLineService getShipPackageLineService() {
		return shipPackageLineService;
	}

	public void setShipPackageLineService(
			ShipPackageLineService shipPackageLineService) {
		this.shipPackageLineService = shipPackageLineService;
	}

	public Page<ShipPackageLineDTO> getPageShipPackageLineDTO() {
		return pageShipPackageLineDTO;
	}

	public void setPageShipPackageLineDTO(
			Page<ShipPackageLineDTO> pageShipPackageLineDTO) {
		this.pageShipPackageLineDTO = pageShipPackageLineDTO;
	}

	public PurchaseOrderItemService getPurchaseOrderItemService() {
		return purchaseOrderItemService;
	}

	public void setPurchaseOrderItemService(
			PurchaseOrderItemService purchaseOrderItemService) {
		this.purchaseOrderItemService = purchaseOrderItemService;
	}

	public Page<PurchaseOrderItemDTO> getPurchaseOrderItemDTO() {
		return purchaseOrderItemDTO;
	}

	public void setPurchaseOrderItemDTO(
			Page<PurchaseOrderItemDTO> purchaseOrderItemDTO) {
		this.purchaseOrderItemDTO = purchaseOrderItemDTO;
	}

	public Page<PurchaseOrderItem> getPagePurchaseOrderItems() {
		return pagePurchaseOrderItems;
	}

	public void setPagePurchaseOrderItems(
			Page<PurchaseOrderItem> pagePurchaseOrderItems) {
		this.pagePurchaseOrderItems = pagePurchaseOrderItems;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Page<PurchaseOrderItemDTO> getPagePurchaseOrderItemDTO() {
		return pagePurchaseOrderItemDTO;
	}

	public void setPagePurchaseOrderItemDTO(
			Page<PurchaseOrderItemDTO> pagePurchaseOrderItemDTO) {
		this.pagePurchaseOrderItemDTO = pagePurchaseOrderItemDTO;
	}

	public ReceivingLogService getReceivingLogsService() {
		return receivingLogService;
	}

	public void setReceivingLogsService(ReceivingLogService receivingLogService) {
		this.receivingLogService = receivingLogService;
	}

	public ReceivingLog getReceivingLogs() {
		return receivingLog;
	}

	public void setReceivingLogs(ReceivingLog receivingLog) {
		this.receivingLog = receivingLog;
	}

	public StorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	public ShipPackageService getShipPackageService() {
		return shipPackageService;
	}

	public void setShipPackageService(ShipPackageService shipPackageService) {
		this.shipPackageService = shipPackageService;
	}

	public FileService getFs() {
		return fs;
	}

	public void setFs(FileService fs) {
		this.fs = fs;
	}

	public ReceivingLogService getReceivingLogService() {
		return receivingLogService;
	}

	public void setReceivingLogService(ReceivingLogService receivingLogService) {
		this.receivingLogService = receivingLogService;
	}

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}

	public ReceivingLog getReceivingLog() {
		return receivingLog;
	}

	public void setReceivingLog(ReceivingLog receivingLog) {
		this.receivingLog = receivingLog;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
}
