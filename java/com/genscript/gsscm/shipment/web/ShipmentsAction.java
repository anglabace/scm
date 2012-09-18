package com.genscript.gsscm.shipment.web;

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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.HostIpUtil;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.MyX509TrustManager;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.dto.ShipClerkDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.dto.ShipmentLinesDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsSrchDTO;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.entity.CanceledShipPackages;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageErrors;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.shipment.service.ShipmentLinesService;
import com.genscript.gsscm.shipment.service.ShipmentsService;
import com.genscript.gsscm.shipment.service.ShippingService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(location = "Shipments/shipments_frame.jsp"),
		@Result(name = "shipmentsList", location = "Shipments/shipments_list.jsp"),
		@Result(name = "shipmentsSearch", location = "Shipments/shipments_search.jsp"),
		@Result(name = "getPkgInfo", location = "Shipments/pck_conf_detail.jsp"),
		@Result(name = "getShipInfo", location = "Shipments/ship_conf_detail.jsp"),
		@Result(name = "getpackagesInfo", location = "Shipments/shippackages_list.jsp"),
		@Result(name = "getSlinedetail", location = "Shipments/package_line.jsp"),
		@Result(name = "getPackageline", location = "Shipments/package_line_china.jsp"),
		@Result(name = "appFrame", location = "Shipments/shipments_frame.jsp"),
		@Result(name = "cancelCHShipments", location = "Shipments/cancel_ch_shipment.jsp"),
		@Result(name = "combineShipments", location = "Shipments/combine_shipments.jsp"),
		@Result(name = "viewPackingSlip", location = "Shipments/view_packing_slip.jsp"),
		@Result(name = "combineView", location = "Shipments/combine_ship_conf_detail.jsp"),
		@Result(name = "order_reference_list", location = "Shipments/order_reference_list.jsp"),
		@Result(name = "cancelShipments", location = "Shipments/cancel_shipment.jsp"),
		@Result(name = "shipmentPackage", location = "Shipments/shipment_package.jsp")
		})
public class ShipmentsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShipmentsService shipmentsService;
	@Autowired
	private ShipmentLinesService shipmentLinesService;
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ShipmentLinesService shipmentLineService;
	@Autowired
	private FileService fileService;
	private HostIpUtil hostip = new HostIpUtil();

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}

	private Page<ShipmentsDTO> pageShipmentsDTO;
	private Page<ShipPackageDTO> packagesDTO;
	private Page<ShipmentLinesDTO> pageslDTO;
	public Page<ShipPackageLineDTO> pageShipPackageLinesDTO;
	public Page<OrderItemDTO> pageoiDTO;
	private Page<OrderMainDTO> pageOrderDTO;
	private Page<ViewPackingSlipDTO> pageView;
	private ViewPackingSlipDTO viewPackingSlip;
	private ShipmentsDTO sDto;
	public ShipmentsSrchDTO shipschDTO;
	public ShipmentLinesDTO slDto;
	public ShipPackageLineDTO splDto;
	private OrderService orderService;
	private Integer sign;
	private ShipPackageDTO pDto = new ShipPackageDTO();
	private List<OrderMain> orderList;
	private String productService;
	private String shippeQuantity;
	private String orderQuantity;
	private String orderSize;
	private String shipSize;
	private ShipmentsDTO so;
	private Integer shipmentId;
	private HashMap<String, Object> notesMap;
	private List<String> orderLists;
	private String	show_tab;
	
	/**
	 * 此方法返回shipmentFrame框架
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String appFrame() {
		try {
			Integer userId = SessionUtil.getUserId();
			User user = this.userDao.getById(userId);
			boolean isProductionManagerRole = false;
			if (!Constants.USERNAME_ADMIN.equals(user.getLoginName())) {
	            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_SHIPMENT_PROCESSING_MANAGER);
	        }else{
	        	isProductionManagerRole = true;
	        }
			if(isProductionManagerRole){
				userId = null;
			}
			List sList = this.shipmentsService.getListByClert(); // 取shipments字段
			List wList = this.shipmentsService.getListByWareHouse();// 取仓库名称
			List nList = this.shipmentsService.getLoginName(userId); // 取人员名称
			//List tList = this.shipmentsService.getStatus(); // 取shipments状态
			List spList = this.shipmentsService.getspStatus(); // 取package状态
			Struts2Util.getRequest().setAttribute("userId", userId);
			Struts2Util.getRequest().setAttribute("isProductionManagerRole", isProductionManagerRole);
			Struts2Util.getRequest().setAttribute("sList", sList);
			Struts2Util.getRequest().setAttribute("wList", wList);
			Struts2Util.getRequest().setAttribute("nList", nList);
			//Struts2Util.getRequest().setAttribute("tList", tList);
			Struts2Util.getRequest().setAttribute("spList", spList);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "appFrame";
	}

	/**
	 * 初始化并分页查询中国仓库与美国仓库装运列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String shipmentsList() throws Exception {
		try {
			String type = Struts2Util.getParameter("type");
			String cu = "ok";
			Integer userId = SessionUtil.getUserId();
			String userName = SessionUtil.getUserName();
			if (type == null || type.equals("") || type.equals("SALES")) {
				// 实例化一个pageUtil对象
				PagerUtil<ShipmentsDTO> pagerUtil = new PagerUtil<ShipmentsDTO>();
				pageShipmentsDTO = pagerUtil.getRequestPage();
				pageShipmentsDTO.setPageSize(10);
				@SuppressWarnings("unused")
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<Shipment> retPage = this.dozer.map(pageShipmentsDTO,
						Page.class);
				ShipmentsDTO srch = null;
				if (type == null || type.equals("")) {// 判断传过来的类型type为空时默认美国的shipments信息
					pageShipmentsDTO = this.shipmentsService.searchu(retPage,
							srch,userId,userName);
					Struts2Util.getRequest().setAttribute("usa", cu);
				} else if (type.equals("SALES")) {// 判断传过来的类型是SALES
					pageShipmentsDTO = this.shipmentsService.searchu(retPage,
							srch,userId,userName);
					Struts2Util.getRequest().setAttribute("usa", cu);
				}
				Integer count = pageShipmentsDTO.getResult().size();
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo",
						pageShipmentsDTO);
			} else if (type.equals("MANUFACTURING")) {
				// 实例化一个pageUtil对象
				PagerUtil<ShipPackageDTO> pagerUtil = new PagerUtil<ShipPackageDTO>();
				packagesDTO = pagerUtil.getRequestPage();
				packagesDTO.setPageSize(10);

				@SuppressWarnings("unused")
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<ShipPackage> retPage = this.dozer.map(packagesDTO,
						Page.class);
				ShipPackageDTO srch = null;

				if (type.equals("MANUFACTURING")) {// 判断传过来的类型是MANUFACTURING
					packagesDTO = this.shipmentsService.searchch(retPage, srch);
					Struts2Util.getRequest().setAttribute("china", cu);
				}
				// 分页
				Integer count = packagesDTO.getResult().size();
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo", packagesDTO);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "shipmentsList";
	}

	/**
	 * 按条件查询shipments数据并返回页面显示
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String shipmentListSearch() throws Exception {
		Integer userId = SessionUtil.getUserId();
		try {
			if (shipschDTO == null)
				shipschDTO = new ShipmentsSrchDTO();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String type = Struts2Util.getParameter("type"); // 获取仓库type
			String error = Struts2Util.getParameter("error");// 获取error信息
			String cu = "ok";
			// 根据仓库type来判断
			if (type == null || type.equals("") || type.equals("SALES")) {
				// 实例化一个pageUtil对象
				PagerUtil<ShipmentsDTO> pagerUtil = new PagerUtil<ShipmentsDTO>();
				pageShipmentsDTO = pagerUtil.getRequestPage();
				pageShipmentsDTO.setPageSize(10);
				if (!pageShipmentsDTO.isOrderBySetted()) {
					pageShipmentsDTO.setOrderBy("modifyDate");
					pageShipmentsDTO.setOrder(Page.DESC);
				}
				@SuppressWarnings("unused")
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<Shipment> retPage = this.dozer.map(pageShipmentsDTO,
						Page.class);
				// 获取页面查询条件
				ShipmentsDTO srch = new ShipmentsDTO();
				srch.setStatus(shipschDTO.getStatus());
				srch.setShipDate(shipschDTO.getShipDate());
				srch.setCustNo(shipschDTO.getCustNo());
				srch.setOrderNo(shipschDTO.getOrderNo());
				srch.setShippingClerk(shipschDTO.getShippingClerk());
				srch.setOrderStatus(shipschDTO.getOrderStatus());
				srch.setPoNo(shipschDTO.getPoNo());
				srch.setSendBys(shipschDTO.getSendBy());
				//人员
				User user = this.userDao.getById(userId);
				boolean isProductionManagerRole = false;
				if (!Constants.USERNAME_ADMIN.equals(user.getLoginName())) {
		            isProductionManagerRole = userRoleDao.checkIsContainsManagerRole(Constants.ROLE_SHIPMENT_PROCESSING_MANAGER);
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
				//System.out.println("userId "+srch.getShippingClerk());
				if (type == null || type.equals("")) {// 判断传过来的类型type为空时默认美国的shipments信息
					pageShipmentsDTO = this.shipmentsService.searchu(retPage,
							srch, error);
					Struts2Util.getRequest().setAttribute("usa", cu);
				} else if (type.equals("SALES")) {// 判断传过来的类型是SALES
					pageShipmentsDTO = this.shipmentsService.searchu(retPage,srch, error);
					Struts2Util.getRequest().setAttribute("usa", cu);

				}
				// 分页
				Integer count = 0;
				if(pageShipmentsDTO.getResult()!=null){
					count = pageShipmentsDTO.getResult().size();
				}
				
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo",pageShipmentsDTO);
			} else if (type.equals("MANUFACTURING")) {// 判断传过来的类型为MANUFACTURING

				// 实例化一个pageUtil对象
				PagerUtil<ShipPackageDTO> pagerUtil = new PagerUtil<ShipPackageDTO>();
				packagesDTO = pagerUtil.getRequestPage();
				packagesDTO.setPageSize(10);
				@SuppressWarnings("unused")
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<ShipPackage> retPage = this.dozer.map(packagesDTO,
						Page.class);
				// 获取页面输入的查询条件
				ShipPackageDTO srch = new ShipPackageDTO();
				srch.setStatus(shipschDTO.getStatus());
				if (shipschDTO.getShipDate() != null
						&& shipschDTO.getShipDate().trim().length() > 0)
					srch.setShipmentDate(format.parse(shipschDTO.getShipDate()));
				else
					srch.setShipmentDate(null);
				if (shipschDTO.getOrderNo() != null
						&& shipschDTO.getOrderNo().trim().length() > 0) {
					srch.setOrderNo(Integer.parseInt(shipschDTO.getOrderNo()));
				}
				srch.setShippingClerk(shipschDTO.getShippingClerk());
				
				// 调用service的查询中国仓库的方法 并返回packagesDTO对象
				packagesDTO = this.shipmentsService.searchch(retPage, srch,
						error);
				Struts2Util.getRequest().setAttribute("china", cu);
				// 分页
				Integer count = packagesDTO.getResult().size();
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo", packagesDTO);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "shipmentsList";
	}

	/**
	 * 中国仓库:根据页面获取的PkgNo,转向查询和保存的细节页面
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getPkgInfo() {
		try {
			// 获取页面的packageId
			String pkgId = Struts2Util.getParameter("packageId");
			List pList = this.shipmentsService.getPriority(); // 获取priority属性

			// 判断当pkgId不为null,调用service的getPckById查询shipments表的细节数据
			if (pkgId != null) {
				Integer packageId = Integer.parseInt(pkgId);
				pDto = this.shipmentsService.getPckById(packageId);

				// 实例化一个pageUtil对象
				PagerUtil<ShipPackageLineDTO> pagerUtil = new PagerUtil<ShipPackageLineDTO>();
				pageShipPackageLinesDTO = pagerUtil.getRequestPage();
				pageShipPackageLinesDTO.setPageSize(10);
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<ShipPackageLines> retPage = this.dozer.map(
						pageShipPackageLinesDTO, Page.class);

				// 根据获取的packageId查询ShipPackageLines数据并返回Page对象
				if (filters == null || filters.isEmpty()) {
					ShipPackageLineDTO shipPackageLinesdto = new ShipPackageLineDTO();
					ShipPackage sp = new ShipPackage();
					sp.setPackageId(pDto.getPackageId());
					shipPackageLinesdto.setShipPackages(sp);
					pageShipPackageLinesDTO = this.shipmentsService
							.searchShipPackageLines(retPage,
									shipPackageLinesdto);
				} else {
					pageShipPackageLinesDTO = this.shipmentsService
							.searchShipPackageLines(retPage, filters);
				}
				if(pDto.getTrackingNo()!=null&&!pDto.getTrackingNo().equals("")){
					File file = new File(this.fileService.getUploadPath()+pDto.getTrackingNo()+".zip");
					if(file.exists()){
						pDto.setIsPrintShippingLabel("1");
					}else{
						pDto.setIsPrintShippingLabel("0");
					}
				}else{
					pDto.setIsPrintShippingLabel("0");
				}

				// 如果返回值为null,记入标记值
				if (pageShipPackageLinesDTO.getResult() == null
						|| (pageShipPackageLinesDTO.getResult().size() < 1)) {
					sign = 1;
					Struts2Util.getRequest().setAttribute("sign", sign);
				}
				// 分页
				Integer count = pageShipPackageLinesDTO.getResult().size();
				Struts2Util.getRequest().setAttribute("pList", pList);
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo",
						pageShipPackageLinesDTO);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "getPkgInfo";
	}

	/**
	 * 修改并保存ShipPackages数据
	 * 
	 * @return String
	 */
	public String saveShipPackager() {
		// 获取response
		HttpServletResponse response = Struts2Util.getResponse();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		java.util.Date date = pDto.getShipmentDate();
//		
		// 保存数据操作
		this.shipmentsService.saveShipPackages(pDto);
		try {
			// 提示保存成功
			response
					.getWriter()
					.print(
							"<script>history.go(-1);alert('save successful!');</script>");
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
	 * 修改并保存ShipPackagelines数据
	 * 
	 * @return String
	 */
	public String saveShipPackagelines() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			// 获取packageId
			Integer pkgLineId = Integer.parseInt(Struts2Util
					.getParameter("pkgLineId"));
			// 新建ShipPackageErrors对象
			ShipPackageErrors shipPackError = new ShipPackageErrors();
			// 判断页面的missingQty值是否为空,并作处理
			if (Struts2Util.getParameter("missingQty") == null
					|| Struts2Util.getParameter("missingQty").trim().length() <= 0)
				shipPackError.setMissingQty(null);
			else
				shipPackError.setMissingQty(Integer.parseInt(Struts2Util
						.getParameter("missingQty")));
			// 判断页面的missingSize值是否为空,并作处理
			if (Struts2Util.getParameter("missingSize") == null
					|| Struts2Util.getParameter("missingSize").trim().length() <= 0)
				shipPackError.setMissingSize(null);
			else
				shipPackError.setMissingSize(Double.parseDouble(Struts2Util
						.getParameter("missingSize")));
			// 获取errorId并设置id
			String errorid = Struts2Util.getParameter("errorid");
			if (errorid.trim().length() > 0)
				shipPackError.setId(Integer.parseInt(Struts2Util
						.getParameter("errorid")));
			else
				shipPackError.setId(null);
			// 判断reason是否为空,并作处理
			if (Struts2Util.getParameter("reason").trim().length() <= 0)
				shipPackError.setReason(null);
			else
				shipPackError.setReason(Struts2Util.getParameter("reason"));
			shipPackError.setPkgLineId(splDto.getPkgLineId());
			shipPackError.setCreatedBy(new Integer(0));
			shipPackError.setModifiedBy(new Integer(0));
			shipPackError.setPkgLineId(pkgLineId);
			shipPackError.setCreationDate(new java.sql.Date(1l));
			shipPackError.setModifyDate(new java.sql.Date(1l));
			shipPackError.setPackageId(splDto.getShipPackages().getPackageId());
			List<ShipPackageErrors> listShipPackageErrors = new ArrayList<ShipPackageErrors>();

			listShipPackageErrors.add(shipPackError);
			splDto.setShipPackageErrorList(listShipPackageErrors);
			// 调用service层的保存方法
			this.shipmentsService.saveShipPackagelines(splDto, pkgLineId);
			// 提示保存成功信息
//			out
//					.print("<script>alert('Save successful');location.href='shipments!getPackageline.action?pkgLineId="
//							+ pkgLineId + " '</script>");
			out.print("<script>alert('Save successful!');</script>");
			out.print("<script>javascript:history.go(-1);</script>");
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
	 * 美国仓库:根据页面获取的shipmentNo,转向细节页面
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getShipInfo() {
		//System.out.println("come  On>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		try {
			
			// 根据shipmentNo查询shipments表的细节
			String shipmentNo = Struts2Util.getRequest().getParameter(
					"shipmentNo");
			sDto = this.shipmentsService.getShipmentsById(shipmentNo);
			//sDto = this.shipmentsService.getShipById(shipmentNo);
			
			//System.out.println("sDto="+sDto.getCurrency());
			// 加载list值
			/*List pList = this.shipmentsService.getPriority();
			List rList = this.shipmentsService.getShippingrule();
			List tList = this.shipmentsService.getShippingtype();
			List cList = this.shipmentsService.getCurrency();*/
			List nList = this.shipmentsService.getLoginName(null); // 获取有权限的人员列表
			List list1 = new ArrayList();
			// 分页查询和shipments表的shipmentId相关的shipmentLines数据
			if (sDto != null) {
				Integer shipmentId = sDto.getShipmentId();
				String error1 = this.shipmentsService
						.getErrorByShipmentId(shipmentId + "");
				Struts2Util.getRequest().setAttribute("error", error1);
				// 实例化一个PagerUtil对象
				PagerUtil<ShipmentLinesDTO> pagerUtil = new PagerUtil<ShipmentLinesDTO>();
				PagerUtil<ShipPackageDTO> pagerUtil1 = new PagerUtil<ShipPackageDTO>();
				Page<ShipPackageDTO> pageslDTO1 = pagerUtil1.getRequestPage();
				pageslDTO1.setPageSize(20);
				pageslDTO = pagerUtil.getRequestPage();
				pageslDTO.setPageSize(10);
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<ShipmentLine> retPage = this.dozer.map(pageslDTO,
						Page.class);
				Page<ShipPackage> retPage1 = this.dozer.map(pageslDTO1,
						Page.class);
				System.out.println("?????????????");
				// 调用service层的查询lines方法 和 packages方法
				if (filters == null || filters.isEmpty()) {
					pageslDTO = this.shipmentsService.searchLine(retPage,
							shipmentId);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					list1 = this.shipmentsService.searchPackage(retPage1,
							shipmentId);
					System.out.println("><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
				String orderNos = this.shipmentLineService.getOrderByShipmentId(shipmentId);
				orderLists = new ArrayList<String>();
				if(orderNos!=null&&!orderNos.equals("")){
					String[] sessOrderNos = orderNos.split(",");
					for(String sessOrderNo : sessOrderNos){
						SessionUtil.deleteRow(SessionConstant.OrderNote.value(),
								sessOrderNo);
						orderLists.add(sessOrderNo);
					}
				}
				notesMap = this.shippingService.getOrderNotesByOrderNosS(orderNos);
				System.out.println("++++++++++++++++++++++++++++");
				// 分页
				Integer count = pageslDTO.getResult().size();
				Integer count1 = pageslDTO1.getResult().size();
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("count1", count1);
				Struts2Util.getRequest().setAttribute("pagerInfo", pageslDTO);
				Struts2Util.getRequest().setAttribute("PagerInfo1", pageslDTO1);
				List list = pageslDTO.getResult();
				//List list1 = pageslDTO1.getResult();

				// 设置获取的一些list的值
				Struts2Util.getRequest().setAttribute("list", list);
				Struts2Util.getRequest().setAttribute("list1", list1);
				/*Struts2Util.getRequest().setAttribute("cList", cList);
				Struts2Util.getRequest().setAttribute("pList", pList);
				Struts2Util.getRequest().setAttribute("rList", rList);
				Struts2Util.getRequest().setAttribute("tList", tList);*/
				Struts2Util.getRequest().setAttribute("nList", nList);

			}
			Struts2Util.getRequest().setAttribute("sDto", sDto);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "getShipInfo";
	}
	
/*	
	 * shipment package
	 
	public String searchPackageShipment(){
		String shipmentId = Struts2Util.getRequest().getParameter("shipmentId");
		PagerUtil<ShipPackageDTO> pagerUtil = new PagerUtil<ShipPackageDTO>();
		Page<ShipPackageDTO> pageslDTO1 = pagerUtil.getRequestPage();
		pageslDTO1.setPageSize(20);
		Page<ShipPackage> retPage1 = this.dozer.map(pageslDTO1,Page.class);
		if(shipmentId!=null){
			pageslDTO1 = this.shipmentsService.searchPackage(retPage1,
					Integer.valueOf(shipmentId));
		}else{
			pageslDTO1 = new Page<ShipPackageDTO>();
		}
		Integer count1 = pageslDTO1.getResult().size();
		Struts2Util.getRequest().setAttribute("count", count1);
		Struts2Util.getRequest().setAttribute("PagerInfo", pageslDTO1);
		List list1 = pageslDTO1.getResult();

		// 设置获取的一些list的值
		Struts2Util.getRequest().setAttribute("list1", list1);
		return "shipmentPackage";
	}*/
	
	/**
	 * 美国仓库:返回shipmentLines细节页面
	 * 
	 * @return String
	 */
	public String getSlinedetail() {
		try {
			Integer lineId = Integer.parseInt(Struts2Util.getRequest().getParameter("lineId"));
			if (lineId.toString() != null) {
				// 调用service的getLineById查询细节数据
				slDto = this.shipmentsService.getLineById(lineId);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "getSlinedetail";
	}

	/**
	 * 根据lineId查询shipmentLines对象,并返回shipmentsLines细节页面
	 * 
	 * @return String
	 */
	public String getShipmentsline() {
		try {
			Integer lineId = Integer.parseInt(Struts2Util.getRequest()
					.getParameter("lineId"));
			if (lineId.toString() != null) {
				slDto = this.shipmentsService.getLineById(lineId);
			}
			Struts2Util.getSession().setAttribute("lineId", lineId);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "getPackageline";
	}

	/**
	 * 根据pkgLineId查询shipmentPackageLines对象,并返回shipmentPackageLines细节页面
	 * 
	 * @return String
	 */
	public String getPackageline() {
		try {
			Integer pkgLineId = Integer.parseInt(Struts2Util.getRequest()
					.getParameter("pkgLineId"));
			if (pkgLineId.toString() != null) {
				splDto = this.shipmentsService.getLineByIdchina(pkgLineId);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "getPackageline";
	}

	/**
	 * 返回搜索框架页面的条件
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public void shipmentsSearch() {
		try {
			List sList = this.shipmentsService.getListByClert(); // 取shipments字段
			List wList = this.shipmentsService.getListByWareHouse();// 取仓库名称
			List nList = this.shipmentsService.getLoginName(null); // 取人员名称
			//List tList = this.shipmentsService.getStatus(); // 取状态

			// 将值放在Request中
			Struts2Util.getRequest().setAttribute("sList", sList);
			Struts2Util.getRequest().setAttribute("wList", wList);
			Struts2Util.getRequest().setAttribute("nList", nList);
			//Struts2Util.getRequest().setAttribute("tList", tList);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}

	}

//	/**
//	 * 合并装运的功能(只针对美国仓库) 前提:status不是shipped的
//	 * 
//	 * @return String
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public String combineShipments() throws Exception {
//		try {
//			PrintWriter out = Struts2Util.getResponse().getWriter();
//
//			// 获取复选框的数组值
//			String[] cks_ = Struts2Util.getRequest().getParameterValues("cks");
//			// 以逗号分割的方式
//			String[] cks = cks_[0].split(",");
//			// 定义一个字符串idStr来存放这些值
//			String idStr = "";
//			List soList = new ArrayList();
//
//			// 如果cks有值
//			if (null != cks && cks.length > 0) {
//				for (int i = 0; i < cks.length; i++) {
//					String shipmentId = cks[i];
//					// 调用service的getShipmentsById方法并返回Shipement对象
//					Shipment so = this.shipmentsService.getShipmentsById(Integer.parseInt(shipmentId));
//					if (so != null) {
//						soList.add(so);
//						idStr += "," + so.getShipmentId();
//					} else {
//						// out.print("<script>alert('Sorry,Shipped or Invalid cannot combine!');</script>");
//					}
//				}
//			}
//			// 根据勾选的条件返回本次合并装运的shipments的列表
//			List<Shipment> list = this.shipmentsService.getShipmengsByList(idStr.substring(1));
//			if (list != null && list.size() != 0) {
//				// 循环这个list
//				for (Shipment shipment : list) {
//					// 判断数据是否符合装运要求 *Invalid和Shipped两种状态不能进行合并装运操作,并弹出对话框提示*
//					if (shipment.getStatus() != null && (shipment.getStatus().equals("Invalid") || shipment.getStatus().equals("Shipped"))) {
//						out.print("<script>parent.$('#combine1').dialog('close')</script>");
//						out.print("<script>alert('Sorry,Shipped or Invalid cannot combine! ')</script>");
//						return null;
//					}
//				}
//			}
//			// 将值放在Request中
//			Struts2Util.getRequest().setAttribute("cks", cks);
//			Struts2Util.getRequest().setAttribute("soList", soList);
//			Struts2Util.getRequest().setAttribute("idStr", idStr.substring(1));
//			
//		} catch (Exception ex) {
//			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
//			exceptionUtil.logException(exDTO, this.getClass(), ex,
//					new Exception().getStackTrace()[0].getMethodName(),
//					"INTF0203", SessionUtil.getUserId());
//			ExceptionOut.printException(exDTO);
//		}
//		return "combineShipments";
//	}
//
//	/**
//	 * 合并装运功能：获取相应的值并保存到session[点击confirm按钮调用此方法]
//	 * 
//	 * @return String
//	 * @throws Exception
//	 */
//	public String combineSession() throws Exception {
//		try {
//			PrintWriter out = Struts2Util.getResponse().getWriter();
//			// 获取相应的值
//			String shipmentId = Struts2Util.getRequest().getParameter("shipmentId"); // 主shipmentId
//			String idStr = Struts2Util.getRequest().getParameter("idStr"); // shipmentNo拼接
//			String reason = Struts2Util.getRequest().getParameter("reason"); // 填写的合并装运原因reason
//			// 将值保存到session中
//			Struts2Util.getSession().setAttribute("shipmentId", shipmentId);
//			Struts2Util.getSession().setAttribute("idStr", idStr);
//			Struts2Util.getSession().setAttribute("reason", reason);
//
//			// 根据主的shipmentId返回本次装运的shipments的状态[主状态]
//			String zstatus = this.shipmentsService.getListStatusByIdList(shipmentId);
//			if (zstatus != null && zstatus.trim().length() > 0){
//				if(zstatus == "Drafted" ){
//					// 根据勾选的shipmentId返回本次合并装运的shipments的列表
//					List<Shipment> list = this.shipmentsService.getShipmengsByList(idStr);
//					if (list != null && list.size() != 0) {
//						// 循环这个Shipment的list
//						for (Shipment shipment : list) {
//							// 如果状态一致,并且都是Drafted,继续合并操作
//							if (shipment.getStatus() != null && (shipment.getStatus().equals("Drafted"))){
//								// 关闭窗口
//								out.print("<script>parent.$('#combine1').dialog('close')</script>");
//								// 转向session预览的save页面
//								out.print("<script>parent.location.href='shipments!combineView.action'</script>");
//							}
//							else{
//								// 关闭窗口
//								out.print("<script>alert('Sorry,shipping cannot combine to Drafted! ');</script>");
//								// 后退一步
//								out.print("<script>history.back()</script>");
//							}
//						}
//					}
//				}
//				if(zstatus == "Shipping"){
//					// 根据勾选的shipmentId返回本次合并装运的shipments的列表
//					List<Shipment> list = this.shipmentsService.getShipmengsByList(idStr);
//					
//					if (list != null && list.size() != 0) {
//						// 循环这个Shipment的list
//						for (Shipment shipment : list) {
//							// 如果状态一致,并且都是Drafted,继续合并操作
//							if (shipment.getStatus() != null && (shipment.getStatus().equals("Shipping") || shipment.getStatus().equals("Drafted"))){
//								// 关闭窗口
//								out.print("<script>parent.$('#combine1').dialog('close')</script>");
//								// 转向session预览的save页面
//								out.print("<script>parent.location.href='shipments!combineView.action'</script>");
//							}
//							else{
//								// 关闭窗口
//								out.print("<script>alert('Sorry,shipping cannot combine to Drafted!');</script>");
//								// 后退一步
//								out.print("<script>history.back()</script>");
//							}
//						}
//					}
//				}
//		
//			}
//		} catch (Exception ex) {
//			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
//			exceptionUtil.logException(exDTO, this.getClass(), ex,
//					new Exception().getStackTrace()[0].getMethodName(),
//					"INTF0203", SessionUtil.getUserId());
//			ExceptionOut.printException(exDTO);
//		}
//		return null;
//	}
//
//	/**
//	 * 合并装运功能：转向预览的save页面
//	 * 
//	 * @return String
//	 */
//	@SuppressWarnings( { "unchecked", "null", "static-access" })
//	public String combineView() {
//		try {
//			// 获取提交后保存在session中的几个关键数据
//			Integer shipmentId = Integer.parseInt((String) Struts2Util
//					.getSession().getAttribute("shipmentId"));
//			String idStr = (String) Struts2Util.getSession().getAttribute("idStr");
//			@SuppressWarnings("unused")
//			String reason = (String) Struts2Util.getSession().getAttribute("reason");
//
//			// 获取人员和状态列表
//			List nList = this.shipmentsService.getLoginName(); // 获取有权限的人员列表
//			List pList = this.shipmentsService.getPriority(); // 获取Priority值
//
//			// 将值保存到request
//			Struts2Util.getRequest().setAttribute("nList", nList);
//			Struts2Util.getRequest().setAttribute("pList", pList);
//
//			// 根据主shipmentId查询对应的shipments的细节数据(上半部分)
//			if (shipmentId != null || shipmentId.SIZE > 0) {
//				Shipment so = this.shipmentsService.getshipmentDetail(shipmentId);
//				Struts2Util.getSession().setAttribute("so", so);
//			}
//
//			// 根据combine页面列出的数据对shipmentLine表进行分页查询(下半部分)
//			if (shipmentId != null) {
//				// 实例化一个pageUtil对象
//				PagerUtil<ShipmentLinesDTO> pagerUtil = new PagerUtil<ShipmentLinesDTO>();
//				pageslDTO = pagerUtil.getRequestPage();
//				pageslDTO.setPageSize(10);
//				List<PropertyFilter> filters = WebUtil
//						.buildPropertyFilters(Struts2Util.getRequest());
//				Page<ShipmentLine> retPage = this.dozer.map(pageslDTO,
//						Page.class);
//
//				// 判断filters
//				if (filters == null || filters.isEmpty()) {
//					// 美国仓库:根据一个或多个shipmentId查询(idStr)，返回一个Page对象
//					pageslDTO = this.shipmentsService.searchShipmentLine(
//							retPage, idStr);
//				}
//				// 分页
//				Integer count = pageslDTO.getResult().size();
//				Struts2Util.getRequest().setAttribute("count", count);
//				Struts2Util.getRequest().setAttribute("pagerInfo", pageslDTO);
//				List list = pageslDTO.getResult();
//				Struts2Util.getRequest().setAttribute("list", list);
//			}
//		} catch (Exception ex) {
//			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
//			exceptionUtil.logException(exDTO, this.getClass(), ex,
//					new Exception().getStackTrace()[0].getMethodName(),
//					"INTF0203", SessionUtil.getUserId());
//			ExceptionOut.printException(exDTO);
//		}
//		return "combineView";
//	}
//
//	/**
//	 * 合并装运功能：将已经合并的数据保存至数据库
//	 * 
//	 * @return String
//	 * @throws Exception
//	 */
//	public String combineSave() throws Exception {
//		try {
//			PrintWriter out = Struts2Util.getResponse().getWriter();
//
//			// 获取session中的几个关键数据
//			String shipmentId = (String) Struts2Util.getSession().getAttribute(
//					"shipmentId"); // ShipementId
//			String idStr = (String) Struts2Util.getSession().getAttribute(
//					"idStr"); // 拼接的字符串
//			String reason = (String) Struts2Util.getSession().getAttribute(
//					"reason"); // 填写的合并装运原因
//
//			// 调用service的保存装运方法
//			if (this.shipmentsService.saveCombineInfo(shipmentId, idStr, reason)) {
//				// 提示保存成功信息
//				out.print("<script>alert('Save successful!');location.href='shipments!appFrame.action'</script>");
//				return null;
//			}
//			// 提示保存失败信息
//			out
//					.print("<script>alert('Save failure!');location.href='shipments!appFrame.action';</script>");
//		} catch (Exception ex) {
//			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
//			exceptionUtil.logException(exDTO, this.getClass(), ex,
//					new Exception().getStackTrace()[0].getMethodName(),
//					"INTF0203", SessionUtil.getUserId());
//			ExceptionOut.printException(exDTO);
//		}
//
//		return null;
//	}
	/**
	 * 合并装运的功能(只针对美国仓库) 前提:status不是shipped的
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String combineShipments() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();

			// 获取复选框的数组值
			String[] cks_ = Struts2Util.getRequest().getParameterValues("cks");
			// 以逗号分割的方式
			String[] cks = cks_[0].split(",");
			// 定义一个字符串idStr来存放这些值
			String idStr = "";
			List soList = new ArrayList();

			// 如果cks有值
			if (null != cks && cks.length > 0) {
				for (int i = 0; i < cks.length; i++) {
					String shipmentId = cks[i];
					// 调用service的getShipmentsById方法并返回Shipement对象
					Shipment so = this.shipmentsService
							.getShipmentsById(Integer.parseInt(shipmentId));
					if (so != null) {
						soList.add(so);
						idStr += "," + so.getShipmentId();
					} else {
						// out.print("<script>alert('Sorry,Shipped or Invalid cannot combine!');</script>");
					}
				}
			}
			// 根据勾选的条件返回本次合并装运的shipments的列表
			List<Shipment> list = this.shipmentsService
					.getShipmengsByList(idStr.substring(1));
			if (list != null && list.size() != 0) {
				// 循环这个list
				for (Shipment shipment : list) {
					// 判断数据是否符合装运要求 *Invalid和Shipped两种状态不能进行合并装运操作,并弹出对话框提示*
					if (shipment.getStatus() != null && (shipment.getStatus().equals("Invalid") ||shipment.getStatus().equals("Ready To Ship")|| shipment.getStatus().equals("Shipped")||shipment.getStatus().equals("Partial Shipped")||shipment.getStatus().equals("Partial Shipped Ready To Ship"))) {
						out.print("<script>parent.$('#combine1').dialog('close')</script>");
						out.print("<script>alert('Sorry,"+shipment.getStatus()+" cannot combine.')</script>");
						return null;
					}
				}
			}
			List<OrderMain> orderList = this.shipmentsService.getOrderByShipmentId(idStr.substring(1));
			if(orderList!= null && orderList.size() != 0){
				for (OrderMain order : orderList) {
					// 判断数据是否符合装运要求 *Invalid和Shipped两种状态不能进行合并装运操作,并弹出对话框提示*
					if (order.getStatus()==null||!order.getStatus().equals("CC")) {
						out.print("<script>parent.$('#combine1').dialog('close')</script>");
						out.print("<script>alert('Sorry,The order "+order.getOrderNo() +" must be CC status. ')</script>");
						return null;
					}
				}
				if(orderList.size()>6){
					out.print("<script>parent.$('#combine1').dialog('close')</script>");
					out.print("<script>alert('You can combine at most six orders.')</script>");
					return null;
				}
			}
			// 将值放在Request中
			Struts2Util.getRequest().setAttribute("cks", cks);
			Struts2Util.getRequest().setAttribute("soList", soList);
			Struts2Util.getRequest().setAttribute("idStr", idStr.substring(1));
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "combineShipments";
	}

	/**
	 * 合并装运功能：获取相应的值并保存到session[点击confirm按钮调用此方法]
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String combineSession() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			// 获取相应的值
			String shipmentId = Struts2Util.getRequest().getParameter("shipmentId"); // 主shipmentId
			String idStr = Struts2Util.getRequest().getParameter("idStr"); // shipmentNo拼接
			String reason = Struts2Util.getRequest().getParameter("reason"); // 填写的合并装运原因reason
			// 将值保存到session中
			Struts2Util.getSession().setAttribute("shipmentId", shipmentId);
			Struts2Util.getSession().setAttribute("idStr", idStr);
			Struts2Util.getSession().setAttribute("reason", reason);

			// 根据主的shipmentId返回本次装运的shipments的状态[主状态]
			String zstatus = this.shipmentsService.getListStatusByIdList(shipmentId);
			// 根据主的shipmentId返回本次装运的shipments的custNo[主客户编号]
			int custNo = this.shipmentsService.getListCustByIdList(shipmentId);

			if (zstatus != null && zstatus.trim().length() > 0){
				if(zstatus.equals("Drafted")){
					// 根据勾选的shipmentId返回本次合并装运的shipments的列表
					List<Shipment> list = this.shipmentsService.getShipmengsByList(idStr);
					if (list != null && list.size() != 0) {
						// 循环这个Shipment的list
						String SmStatus = "";
						for (Shipment shipment : list) {
							SmStatus += shipment.getStatus()+",";
							if(shipment.getCustNo() != custNo){
								// 提示custNo必须相同才能合并
								out.print("<script>alert('Sorry,custNo is different! ');</script>");
								// 后退一步
								out.print("<script>history.back()</script>");
							}
						}
						SmStatus = SmStatus.substring(0, SmStatus.length()-1);
						// 如果状态一致,并且都是Drafted,继续合并操作
							if (SmStatus.indexOf("Ready To Ship")> -1){
								// 关闭窗口
								out.print("<script>alert('Sorry,shipping cannot combine to Drafted! ');</script>");
								// 后退一步
								out.print("<script>history.back()</script>");
							}
							else{
								// 关闭窗口
								out.print("<script>parent.$('#combine1').dialog('close')</script>");
								// 转向session预览的save页面
								out.print("<script>parent.location.href='shipments!combineView.action'</script>");
							}
					}
				}
				if(zstatus.equals("Ready To Ship")){
					// 根据勾选的shipmentId返回本次合并装运的shipments的列表
					List<Shipment> list = this.shipmentsService.getShipmengsByList(idStr);
					for (Shipment shipment : list) {
						if(shipment.getCustNo() != custNo){
							// 提示custNo必须相同才能合并
							out.print("<script>alert('Sorry,custNo is different! ');</script>");
							// 关闭窗口
							out.print("<script>parent.$('#combine1').dialog('close')</script>");
							return null;
						}
					}
					// 关闭窗口
					out.print("<script>parent.$('#combine1').dialog('close')</script>");
					// 转向session预览的save页面
					out.print("<script>parent.location.href='shipments!combineView.action'</script>");
					}		
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
	 * 合并装运功能：转向预览的save页面
	 * 
	 * @return String
	 */
	@SuppressWarnings( { "unchecked", "null", "static-access" })
	public String combineView() {
		try {
			// 获取提交后保存在session中的几个关键数据
			Integer shipmentId = Integer.parseInt((String) Struts2Util
					.getSession().getAttribute("shipmentId"));
			String idStr = (String) Struts2Util.getSession().getAttribute(
					"idStr");
			@SuppressWarnings("unused")
			String reason = (String) Struts2Util.getSession().getAttribute(
					"reason");

			// 获取人员和状态列表
			List nList = this.shipmentsService.getLoginName(null); // 获取有权限的人员列表
			List pList = this.shipmentsService.getPriority(); // 获取Priority值

			// 将值保存到request
			Struts2Util.getRequest().setAttribute("nList", nList);
			Struts2Util.getRequest().setAttribute("pList", pList);

			// 根据主shipmentId查询对应的shipments的细节数据(上半部分)
			if (shipmentId != null || shipmentId.SIZE > 0) {
				so = this.shipmentsService.getShipmentsById(shipmentId.toString());
				String shipRule = so.getShippingRule();
			    SimpleDateFormat strToDate = new SimpleDateFormat ("yyyy-MM-dd");
			    List<Shipment> shipmentList = this.shipmentsService.getShipmengsByList(idStr);
			    if(shipmentList!=null&&!shipmentList.isEmpty()){
			    	for(Shipment shipment:shipmentList){ 
			    		if(!"Item Completed".equals(shipRule)){
			    			if("Order Completed".equals(shipment.getShippingRule())){
			    				shipRule = shipment.getShippingRule();
			    			}else if(!"Order Completed".equals(shipRule)){
			    				if("Payment Completed".equals(shipment.getShippingRule())){
			    					shipRule = shipment.getShippingRule();
			    				}else if(!"Payment Completed".equals(shipRule)){
			    					shipRule = shipment.getShippingRule();
			    				}
			    			}
			    		}
			    	}
			    }
			    if(so.getModifyDate()!=null){
			    	so.setModifyDateStr(strToDate.format(so.getModifyDate()));
			    }
			    if(so.getCreationDate()!=null){
			    	so.setCreationDateStr(strToDate.format(so.getCreationDate()));
			    }
			    
			    
				//Struts2Util.getSession().setAttribute("so", so);
			}

			// 根据combine页面列出的数据对shipmentLine表进行分页查询(下半部分)
			if (shipmentId != null) {

				// 实例化一个pageUtil对象
				PagerUtil<ShipmentLinesDTO> pagerUtil = new PagerUtil<ShipmentLinesDTO>();
				pageslDTO = pagerUtil.getRequestPage();
				pageslDTO.setPageSize(10);
				List<PropertyFilter> filters = WebUtil
						.buildPropertyFilters(Struts2Util.getRequest());
				Page<ShipmentLine> retPage = this.dozer.map(pageslDTO,
						Page.class);

				// 判断filters
				if (filters == null || filters.isEmpty()) {
					// 美国仓库:根据一个或多个shipmentId查询(idStr)，返回一个Page对象
					/*pageslDTO = this.shipmentsService.searchLine(retPage,
							shipmentId);*/
					pageslDTO = this.shipmentsService.searchShipmentLine(
							retPage, idStr);
				}
				// 分页
				Integer count = pageslDTO.getResult().size();
				Struts2Util.getRequest().setAttribute("count", count);
				Struts2Util.getRequest().setAttribute("pagerInfo", pageslDTO);
				List list = pageslDTO.getResult();
				Struts2Util.getRequest().setAttribute("list", list);
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "combineView";
	}

	/**
	 * 合并装运功能：将已经合并的数据保存至数据库
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String combineSave() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();

			// 获取session中的几个关键数据
			String shipmentId = (String) Struts2Util.getSession().getAttribute(
					"shipmentId"); // ShipementId
			String idStr = (String) Struts2Util.getSession().getAttribute(
					"idStr"); // 拼接的字符串
			String reason = (String) Struts2Util.getSession().getAttribute(
					"reason"); // 填写的合并装运原因

			// 调用service的保存装运方法
			if (this.shipmentsService
					.saveCombineInfo(shipmentId, idStr, reason)) {
				// 提示保存成功信息
				out
						.print("<script>alert('Save successful!');location.href='shipments!appFrame.action'</script>");
				return null;
			}
			// 提示保存失败信息
			out
					.print("<script>alert('Save failure!');location.href='shipments!appFrame.action';</script>");
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
	 * 取消装运功能(只针对美国仓库)
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String cancelShipments() throws Exception {
		try {
			// 接收页面提交数据
			String idsStr = Struts2Util.getRequest().getParameter("cks");
			Struts2Util.getSession().setAttribute("cks", idsStr);
			List<Integer> ids = new ArrayList<Integer>();

			// 将页面传入的“字符串数组”拆开放入list
			for (int i = 0; i < idsStr.split(",").length; i++) {
				ids.add(Integer.parseInt(idsStr.split(",")[i]));
			}

			// 根据ids中的id查出对应的对象并放入列表
			List<ShipPackage> trlist = this.shipmentsService.getListByTr(ids);
			Map<Integer, ShipPackage> trlist1 = new HashMap<Integer, ShipPackage>();

			// 除去列表中ShipPackages类的trackingNo为空的对象
			for (ShipPackage shipPackages2 : trlist) {
				if (shipPackages2.getTrackingNo() != null
						&& shipPackages2.getTrackingNo() != "")
					trlist1.put(shipPackages2.getPackageId(), shipPackages2);
			}
			// 清空trlist
			trlist.clear();
			for (Map.Entry<Integer, ShipPackage> e : trlist1.entrySet()) {
				trlist.add(e.getValue());
			}

			PrintWriter out = Struts2Util.getResponse().getWriter();

			// 如果列表中所有的ShipPackages类的trackingNo都为空，提示当前选中No TrackingNo
			if (trlist == null || trlist.size() == 0) {
				out
						.print("<script>parent.$('#cancel1').dialog('close');</script>");
				out.print("<script>alert('No TrackingNo!');</script>");
				return null;
			}

			// 如果选中中存在shipments的状态除ready to ship和shipped之外的状态，给予提示，并停留在当前页面
			for (ShipPackage shipPackages : trlist) {
				String status = this.shipmentsService.getShipmentsById(
						shipPackages.getShipments().getShipmentId())
						.getStatus(); // 20101026
				if (!status.equals("Ready To Ship") && !status.equals("Shipped")&&!status.equals("Partial Ready To Ship")&&!status.equals("Partial Shipped")) {
					out
							.print("<script>parent.$('#cancel1').dialog('close');</script>");
					out
							.print("<script>alert('Exist status that cannot cancel shipments!');</script>");
					return null;
				}
			}
			// 以上条件满足的情况下将trlist保存在request中并跳到cancelshipments页面
			Struts2Util.getRequest().setAttribute("trlist", trlist);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "cancelShipments";
	}

	/**
	 * 保存和更新取消装运的数据信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doCancelShipment() throws Exception {
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
			this.shipmentsService.changeShipPackagesByPackageId(packageId,
					reason,this.shipmentId);
			out.print("<script>parent.$('#cancel1').dialog('close');</script>");
			out
					.print("<script>parent.location.href=parent.location.href;</script>");
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
	 * 点击放大镜查询order列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String order_reference_list() throws Exception {
		try {
			
			String orderNos = Struts2Util.getParameter("orderNos");
			if (orderNos!=null&&!"undefined".equals(orderNos)) {
				List<String> orderNoList = Arrays.asList(orderNos.split(","));
				orderList = this.orderService.searchOrder(orderNoList);
			}
			//System.out.println(orderList.toString());
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}

		return "order_reference_list";
	}

	/**
	 * 点击select将值放到文本框中
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String shipmentListSelect() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();

			// 获取orderNo值
			String orderNo = Struts2Util.getRequest().getParameter("orderNos");
			Struts2Util.getSession().setAttribute("orderNo", orderNo);

			// 刷新父窗口
			out.print("<script>parent.location.reload();</script>");
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
	 * 查询打印的数据
	 * 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String viewPackingSlip() {
		try {
			// 获取packageId
			//String packageId = "3187";
			String packageId = Struts2Util.getRequest().getParameter("packageId");
//			String packageId = Struts2Util.getRequest().getParameter("packageId")+"";
			// 判断packageId是否为空
			System.out.println(">>>>>>>>>>"+packageId);
			if (packageId != null && packageId != "") {
				// 定义一个PagerUtil对象
				viewPackingSlip = this.shipmentsService.getViewPackingSlip(
						packageId);
				System.out.println(">>>>>>>>>>"+packageId);
			}
			System.out.println(">>>>>>>>>>"+packageId);
			Integer userId = SessionUtil.getUserId();
			User user = this.userDao.getById(userId);
			if(user!=null){
				Struts2Util.getSession().setAttribute("email", user.getEmail());
			}
			// 清除session
			Struts2Util.getSession().removeAttribute("packageId");
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "viewPackingSlip";
	}

	/**
	 * 调用第三方组件进行转换PDF
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public void print() throws IOException {
		// 获取packageId 并保存至session
		String packageId = Struts2Util.getRequest().getParameter("packageId");
		Struts2Util.getSession().setAttribute("packageId", packageId);
		System.out.println("packageId===========" + packageId);
		HttpURLConnection con = null;
		HttpsURLConnection connection =null;
		URL url = null;
		Process p = null;
		String cmd = "html2pdf";
		String newip = this.hostip.getLocalIP();
		System.out.print("newIP========================================================="+newip);
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
			url = new URL(basePath+"shipments!viewPackingSlip.action");
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
			File file = new File("/tmp/ok.html");
			writer = new FileWriter(file);
			writer.write(strb.toString());
			System.out.print("writer===========" + writer);
			writer.flush();
			writer.close();
			System.out.println("strb=============" + strb.toString());
			ProcessBuilder pb = new ProcessBuilder(cmd, "/tmp/ok.html",
					"/tmp/ok.pdf");
			pb.redirectErrorStream(true);
			p = pb.start();
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println("line============="+line);
			}
			// 提示下载
			HttpServletResponse response = Struts2Util.getResponse();
			response.setContentType("APPLICATION/DOWNLOAD;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ "PackingSlip" + (new Random()).nextInt() + ".pdf");// PackingSlip是文件名
			java.io.OutputStream os = response.getOutputStream();
			java.io.FileInputStream fis = new java.io.FileInputStream("/tmp/ok.pdf");
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
			} else {
				connection.disconnect();
			}
		} catch (Exception ex) {
			//p.destroy();
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
	}

	/**
	 * PackagesDetails页面取消装运功能
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String cancelCHShipment() throws Exception {
		try {
			PrintWriter out = Struts2Util.getResponse().getWriter();
			// 获取页面的packageId
			String packageId = Struts2Util.getRequest().getParameter(
					"packageId");
			// 判断packageId是否为空
			if (packageId != null && !packageId.equals("")) {
				ShipPackage sp = this.shipmentsService.getShipPackagesByid(Integer.parseInt(packageId));
				if("Shipped".equals(sp.getShipments().getStatus())||"Partial Shipped".equals(sp.getShipments().getStatus())){
					out.print("<script>parent.$('#cancel_ship').dialog('close');</script>");
					out.print("<script>alert('The shipment status is Shipped or Partial Shipped');</script>");
					out.print("<script>parent.location.href=parent.location.href;</script>");
					return null;
				}
				
				if (sp != null && (sp.getTrackingNo() == null || sp.getTrackingNo().equals(""))) {
					out.print("<script>parent.$('#cancel_ship').dialog('close');</script>");
					out.print("<script>alert('No TrackingNo!');</script>");
					out.print("<script>parent.location.href=parent.location.href;</script>");
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
		return "cancelCHShipments";
	}

	/**
	 * 保存取消装运信息功能(confirm)
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String doCancelCHShipment() throws Exception {
		try {
			Integer userId = SessionUtil.getUserId();
			// 获取页面数据并处理
			String packageIdStr = Struts2Util.getParameter("packageId");
			String methodId = Struts2Util.getParameter("methodId");
			String reason = Struts2Util.getRequest().getParameter("reason");
			String trackingNo = Struts2Util.getRequest().getParameter("trackingNo");
			String result ="";
			int packageId = 0;
			if (packageIdStr != null && packageIdStr != "") {
				packageId = Integer.parseInt(packageIdStr);
			}
			CanceledShipPackages canceledShipPackage = new CanceledShipPackages();
			canceledShipPackage.setCanceledBy(userId);
			canceledShipPackage.setCanceledReason(reason);
			canceledShipPackage.setCanceledTime(new Date());
			canceledShipPackage.setPackageId(packageId);
			canceledShipPackage.setTrackingNo(trackingNo);
			ShipMethod shipMethod = this.shipMethodDao.getById(Integer.valueOf(methodId));
			System.out.println("carrierr ="+shipMethod.getCarrier());
			if(trackingNo!=null){
				if(shipMethod.getCarrier().equals("Fedex")){
					StringBuffer url = new StringBuffer("https://www.genscriptcorp.com/fedexapi/DeleteShipment.php?trackingNumber="+trackingNo);
					System.out.println("url 1="+url);
					result = this.shipmentsService.getReq(url.toString());
				}
				String[] returnStrs = result.split("success");
				if(returnStrs.length>1||!shipMethod.getCarrier().equals("Fedex")){
					this.shipmentsService.changeShipPackagesByPackageId(packageId,
							reason,shipmentId);
				}
				System.out.println("result="+result);
			}
			
			
			PrintWriter out = Struts2Util.getResponse().getWriter();
			
			// 调用service层方法执行存储并跳回父页面
			
			if(result!=null){
				String[] re = result.split("cancel successful");
				if(re.length>1){
					this.shipmentsService.saveCanceledShipPackages(canceledShipPackage);
					out.print("<script>parent.$('#cancel_ship').dialog('close');alert('TrackingNo canceled successfully.');</script>");
				}else if(!shipMethod.getCarrier().equals("Fedex")){
					this.shipmentsService.saveCanceledShipPackages(canceledShipPackage);
					out.print("<script>parent.$('#cancel_ship').dialog('close');alert('TrackingNo canceled successfully.');</script>");
				}else{
					out.print("<script>parent.$('#cancel_ship').dialog('close');alert('TrackingNo canceled error.');</script>");
				}
				
			}else{
				out.print("<script>parent.$('#cancel_ship').dialog('close');alert('TrackingNo canceled error.');</script>");
			}
			
//			out
//					.print("<script>parent.location.href=shipments!getPkgInfo.action?packageId="
//							+ packageId + ";</script>");
			out.print("<script>parent.location.href=parent.location.href;</script>");
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
	 * 更新shipmentLines对象信息
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String updateShipmentLine() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		try {
			// 获取description
			String description = Struts2Util.getParameter("description");
			Integer lineId = Integer.parseInt(Struts2Util
					.getParameter("lineId"));
			ShipmentLine line = new ShipmentLine();
			line.setLineId(lineId);
			line.setDescription(description);
			line.setModifiedBy(SessionUtil.getUserId());
			java.util.Date d = new java.util.Date();
			line.setModifyDate(new Date());
			@SuppressWarnings("unused")
			// 调用service的更新方法进行数据更新操作
			String flag = this.shipmentLinesService.updateDescription(line);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		out.print("<script>alert('Save successful!');</script>");
		out.print("<script>javascript:history.go(-1);</script>");
		return null;
	}

	/**
	 * 更新并保存shipments表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateShipment() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		Integer shipmentId = Integer.parseInt(Struts2Util
				.getParameter("shipmentId"));
		try {
			String orderReferences = Struts2Util.getParameter("orderReference");
			String priority = Struts2Util.getParameter("priority");
			String currency = Struts2Util.getParameter("currency");
			String shippingType = Struts2Util.getParameter("shippingType");
			String shippingRule = Struts2Util.getParameter("shippingRule");
			String description = Struts2Util.getParameter("description");
			
			Shipment shipment = new Shipment();
			shipment.setPriority(priority);
			shipment.setCurrency(currency);
			shipment.setShippingType(shippingType);
			shipment.setShippingRule(shippingRule);
			shipment.setDescription(description);
			shipment.setModifiedBy(SessionUtil.getUserId());
			// util格式日期转Sql格式
			//java.util.Date d = new java.util.Date();
			shipment.setModifyDate(new Date());
			shipment.setShipmentId(shipmentId);
			List<InstructionDTO> instructionDTOLists = new ArrayList<InstructionDTO>();
			if(orderReferences!=null&&!orderReferences.equals("")){
				for(String orderReference : orderReferences.split(",")){
					List<InstructionDTO> instructionDTOList = this.attachNote(orderReference);
					if(instructionDTOList!=null&&!instructionDTOList.isEmpty()){
						instructionDTOLists.addAll(instructionDTOList);
					}
				}
			}
			// 调用service层的更新方法进行操作
			@SuppressWarnings("unused")
			String flag = this.shipmentsService.updateShipments(shipment,instructionDTOLists);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		out.print("<script>alert('Save successful.');</script>");
		out.print("<script>location.href = 'shipments!getShipInfo.action?shipmentNo="+shipmentId+"'</script>");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<InstructionDTO> attachNote(String orderNo) {
		List<InstructionDTO> noteList = new ArrayList<InstructionDTO>();
		List<Integer> delIdList = new ArrayList<Integer>();
		Map<String, InstructionDTO> sessAddrMap = (Map<String, InstructionDTO>) SessionUtil
				.getRow(SessionConstant.OrderNote.value(), orderNo);
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
					tmpDTO.setOrderNo(Integer.valueOf(orderNo));
					noteList.add(tmpDTO);
				}
			}
		}
		return noteList;
	}
	
	public String refresh(){
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			Integer userId = SessionUtil.getUserId();
			User user = this.userDao.getById(userId);
			if("admin".equals(user.getLoginName())){
				this.shipmentsService.getStatusOfFedex(fileService.getUploadPath()+"shipped", "shipped/","");
			}
			
			
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		rt.put("message", SUCCESS);
		Struts2Util.renderJson(rt);
		return null;
	}

	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	public ShipmentsService getShipmentsService() {
		return shipmentsService;
	}

	public void setShipmentsService(ShipmentsService shipmentsService) {
		this.shipmentsService = shipmentsService;
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

	public ShipmentLinesDTO getSlDto() {
		return slDto;
	}

	public void setSlDto(ShipmentLinesDTO slDto) {
		this.slDto = slDto;
	}

	public Page<ShipmentLinesDTO> getPageslDTO() {
		return pageslDTO;
	}

	public void setPageslDTO(Page<ShipmentLinesDTO> pageslDTO) {
		this.pageslDTO = pageslDTO;
	}

	public ShipPackageLineDTO getSplDto() {
		return splDto;
	}

	public void setSplDto(ShipPackageLineDTO splDto) {
		this.splDto = splDto;
	}

	public ShipmentLinesService getShipmentLinesService() {
		return shipmentLinesService;
	}

	public void setShipmentLinesService(
			ShipmentLinesService shipmentLinesService) {
		this.shipmentLinesService = shipmentLinesService;
	}

	public Page<ShipPackageDTO> getPackagesDTO() {
		return packagesDTO;
	}

	public void setPackagesDTO(Page<ShipPackageDTO> packagesDTO) {
		this.packagesDTO = packagesDTO;
	}

	public ShipPackageDTO getPDto() {
		return pDto;
	}

	public void setPDto(ShipPackageDTO dto) {
		pDto = dto;
	}

	public Page<ViewPackingSlipDTO> getPageView() {
		return pageView;
	}

	public void setPageView(Page<ViewPackingSlipDTO> pageView) {
		this.pageView = pageView;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Page<OrderMainDTO> getPageOrderDTO() {
		return pageOrderDTO;
	}

	public void setPageOrderDTO(Page<OrderMainDTO> pageOrderDTO) {
		this.pageOrderDTO = pageOrderDTO;
	}

	public Page<ShipPackageLineDTO> getPageShipPackageLinesDTO() {
		return pageShipPackageLinesDTO;
	}

	public void setPageShipPackageLinesDTO(
			Page<ShipPackageLineDTO> pageShipPackageLinesDTO) {
		this.pageShipPackageLinesDTO = pageShipPackageLinesDTO;
	}

	public Page<OrderItemDTO> getPageoiDTO() {
		return pageoiDTO;
	}

	public void setPageoiDTO(Page<OrderItemDTO> pageoiDTO) {
		this.pageoiDTO = pageoiDTO;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	public String getProductService() {
		return productService;
	}

	public void setProductService(String productService) {
		this.productService = productService;
	}

	/*
	 * 页面获取的get数据。
	 */
	public List<PbDropdownListOptions> getAllShipType(){
		return this.publicService.getDropdownList("SHIPPING_TYPE");
	}
	
	public List<PbDropdownListOptions> getAllShipPriority(){
		return this.publicService.getDropdownList("SHIPMENT_PRIORITY");
	}
	
	public List<PbDropdownListOptions> getAllShippingRule(){
		return this.publicService.getDropdownList("SHIPPING_RULE");
	}
	
	public List<DropDownDTO> getAllCurrency(){
		return publicService.getSpecDropDownList(SpecDropDownListName.CURRENCY);
	}
	
	public List<ShipClerkDTO> getAllShipClerkList(){
		return this.shipmentsService.getAllShipClerk();
	}

	public String getShippeQuantity() {
		return shippeQuantity;
	}

	public void setShippeQuantity(String shippeQuantity) {
		this.shippeQuantity = shippeQuantity;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public List<OrderMain> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderMain> orderList) {
		this.orderList = orderList;
	}

	public String getOrderSize() {
		return orderSize;
	}

	public void setOrderSize(String orderSize) {
		this.orderSize = orderSize;
	}

	public String getShipSize() {
		return shipSize;
	}

	public void setShipSize(String shipSize) {
		this.shipSize = shipSize;
	}

	public ShipmentsDTO getSo() {
		return so;
	}

	public void setSo(ShipmentsDTO so) {
		this.so = so;
	}

	public ViewPackingSlipDTO getViewPackingSlip() {
		return viewPackingSlip;
	}

	public void setViewPackingSlip(ViewPackingSlipDTO viewPackingSlip) {
		this.viewPackingSlip = viewPackingSlip;
	}

	public Integer getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	public Integer getuserId(){
		return SessionUtil.getUserId();
	}

	public HashMap<String, Object> getNotesMap() {
		return notesMap;
	}

	public void setNotesMap(HashMap<String, Object> notesMap) {
		this.notesMap = notesMap;
	}

	public List<String> getOrderLists() {
		return orderLists;
	}

	public void setOrderLists(List<String> orderLists) {
		this.orderLists = orderLists;
	}

	public String getShow_tab() {
		return show_tab;
	}

	public void setShow_tab(String show_tab) {
		this.show_tab = show_tab;
	}
}