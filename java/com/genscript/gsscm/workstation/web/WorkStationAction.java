package com.genscript.gsscm.workstation.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.SessionUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.systemsetting.service.ShipClerkService;
import com.genscript.gsscm.workstation.dto.OrderItemSrchDTO;
import com.genscript.gsscm.workstation.entity.ShippingClerkAdjustment;
import com.genscript.gsscm.workstation.service.WorkStationService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(location = "workstation/mana_Work_Frame.jsp"),
		@Result(name = "mana_Work_Frame", location = "workstation/mana_Work_Frame.jsp"),
		@Result(name = "manaSearch", location = "workstation/mana_search.jsp"),
		@Result(name = "shipClerk", location = "workstation/ship_clerk_assi_k.jsp"),
		@Result(name = "assignedToUpdate", location = "workstation/mana_work_list.jsp"),
		@Result(name = "mwSearch", location = "workstation/mana_work_list.jsp"),
		@Result(name = "manaList", location = "workstation/mana_work_list.jsp") })
public class WorkStationAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private WorkStationService workStationService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ShipClerkService shipClerkService;
	private Page<OrderItemDTO> pageOrderItemDTO;
	private OrderItemSrchDTO orderItemSrchDTO;
	private OrderMain order = null;
	private Warehouse warehouse = null;
	private Integer sign;

	/**
	 * 
	 * 显示frame页面
	 * 
	 * @return String
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 
	 * 显示列表信息
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String manaList() {
		try {
			PagerUtil<OrderItemDTO> pagerUtil = new PagerUtil<OrderItemDTO>();
			pageOrderItemDTO = pagerUtil.getRequestPage();
			pageOrderItemDTO.setPageSize(10);
			List<PropertyFilter> filters = WebUtil.buildPropertyFilters(Struts2Util.getRequest());
			Page<OrderItem> retPage = this.dozer.map(pageOrderItemDTO,
					Page.class);

			if (filters == null || filters.isEmpty()) {
				OrderItemSrchDTO srch = null;
				pageOrderItemDTO = this.workStationService.searchOrderItemOfShipment(
						retPage, srch);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>.");

			} else {
				pageOrderItemDTO = this.workStationService.searchOrderItemOfShipment(
						retPage, filters);
				System.out.println("<<<<<<<<<<<<<<<<<<<<.");
			}
			pageOrderItemDTO.setOrder("asc");
			pageOrderItemDTO.setOrderBy("orderNo");
			Integer count = pageOrderItemDTO.getResult().size();
			Struts2Util.getRequest().setAttribute("count", count);
			Struts2Util.getRequest()
					.setAttribute("pagerInfo", pageOrderItemDTO);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "manaList";

	}

	/**
	 * 显示搜索信息
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String mwSearch() {
		try {
			String whName = Struts2Util.getRequest().getParameter("orderItemSrchDTO.warehouseName");
			String typeName = Struts2Util.getRequest().getParameter("typeName");
			if(typeName!=null&&!typeName.equals("")){
				String[] typeNames = typeName.split(":");
				orderItemSrchDTO.setClsId(Integer.valueOf(typeNames[2]));
				orderItemSrchDTO.setType(typeNames[3]);
			}else if(orderItemSrchDTO!=null){
				orderItemSrchDTO.setClsId(null);
				orderItemSrchDTO.setType(null);
			}
			
			Struts2Util.getRequest().setAttribute("whName", whName);
			List<OrderItem> dtoList = new ArrayList<OrderItem>();
			pageOrderItemDTO = new Page<OrderItemDTO>();
			for (OrderItemDTO orderItemDTO : this.pageOrderItemDTO.getResult()) {
				OrderItem dto = dozer.map(orderItemDTO, OrderItem.class);
				dtoList.add(dto);
			}
			// 实例化一个pageUtil对象
			PagerUtil<OrderItemDTO> pagerUtil = new PagerUtil<OrderItemDTO>();
			pageOrderItemDTO = pagerUtil.getRequestPage();
			pageOrderItemDTO.setPageSize(10);

			Page<OrderItem> orderItemPage = this.dozer.map(pageOrderItemDTO,
					Page.class);
			pageOrderItemDTO = this.workStationService.searchOrderItemOfShipment(
					orderItemPage, orderItemSrchDTO);
			if (pageOrderItemDTO.getResult() == null
					|| (pageOrderItemDTO.getResult().size() < 1)) {
				sign = 1;
				Struts2Util.getRequest().setAttribute("sign", sign);
			}
			Integer count = pageOrderItemDTO.getResult().size();
			Struts2Util.getRequest().setAttribute("count", count);
			Struts2Util.getRequest()
					.setAttribute("pagerInfo", pageOrderItemDTO);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "mwSearch";
	}

	/**
	 * 显示select列表信息
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String manaSearch() {
		try {
			List pList = this.workStationService.getListByProductName();
			List sList = this.workStationService.getListByServiceName();
			List wList = this.workStationService.getListByWarehouseName();
			Map<String, String> productAndServiceCls = shipClerkService
			.getProductAndService();
			//List tList = this.workStationService.getListByType();
			Struts2Util.getRequest().setAttribute("allcls", productAndServiceCls);
			Struts2Util.getRequest().setAttribute("wList", wList);
			Struts2Util.getRequest().setAttribute("pList", pList);
			Struts2Util.getRequest().setAttribute("sList", sList);
			//Struts2Util.getRequest().setAttribute("tList", tList);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "manaSearch";
	}

	/**
	 * 弹出 Assign Shipping Clerk 页面
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String shipClerk() {
		try {
			List shipList = this.workStationService
					.getListByShippingClerkAssign();
			Struts2Util.getRequest().setAttribute("shipList", shipList);
			String cks_ = Struts2Util.getRequest().getParameter("cks");
			@SuppressWarnings("unused")
			String[] cks = null;
			if (cks_ != null && cks_.trim().length() > 0) {
				cks = cks_.split(",");
				Struts2Util.getRequest().setAttribute("cks", cks_);
			}
			String itemNo = Struts2Util.getRequest().getParameter("itemNo");
			Struts2Util.getRequest().setAttribute("itemNo", itemNo);
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		return "shipClerk";
	}

	/**
	 * 对shippingClerk 进行修改和添加
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String assignedToUpdate() throws Exception {
		PrintWriter out = Struts2Util.getResponse().getWriter();
		String chks_ = Struts2Util.getRequest().getParameter("cks");
		String shipClerk = Struts2Util.getParameter("shipClerk");
		String[] chks = chks_.split(",");
		OrderItemDTO item = null;
		ShippingClerkAdjustment adjustment = null;
		try {
			String itemNo = Struts2Util.getRequest().getParameter("itemNo");
			
			if (itemNo != null &&!itemNo.equals("") && itemNo.trim().length() > 0) {
				item = this.workStationService.getOrderItem(itemNo);
				if(item == null ){
					out.print("<script>alert('Object is null!')</script>");
					out.print("<script>parent.$('#shipClerk').dialog('close')</script>");
					out.print("<script>window.location='work_station!manaList.action'</script>");
				}else{
					adjustment = new ShippingClerkAdjustment();
					adjustment.setOrderNo(item.getOrderNo());
					adjustment.setItemNo(item.getItemNo());
					User user = this.workStationService.getAssignedTo(item.getOrderNo(),item.getItemNo(),item.getWarehouseId(),item.getType(),item.getClsId());
					if (user != null)
						adjustment.setOldClerk(user.getUserId());
					adjustment.setNewClerk(Integer.parseInt(shipClerk));
					adjustment.setAdjustDate(new Date());
					adjustment.setAdjustedBy(SessionUtil.getUserId());
					adjustment.setReason(item.getStatusUpdReason());
					this.workStationService.updateClerk(adjustment);
				}
			}
			
			for (int i = 0; chks != null && !"".equals(chks[0])
					&& i < chks.length; i++) {
					Integer warehouseId = null;
					
					OrderItem items = this.workStationService.getOrderItemById(chks[i]);
					if(items!=null){
						List<OrderItem> itemList = this.workStationService.getListByOrderNo(items.getOrderNo());
						for(OrderItem orderItem : itemList){
							System.out.println(chks[i]+"<<<<<<>>>>>>>>>>>>>>>");
							if(orderItem == null ){
								System.out.println("><><><<<<<<<<<<<<<<<<<<<,");
								out.print("<script>alert('Object is null!')</script>");
								out.print("<script>parent.$('#shipClerk').dialog('close')</script>");
								out.print("<script>window.location='work_station!manaList.action'</script>");
							}else{
								adjustment = new ShippingClerkAdjustment();
								adjustment.setOrderNo(orderItem.getOrderNo());
								adjustment.setItemNo(orderItem.getItemNo());
								User user = this.workStationService.getAssignedTo(orderItem.getOrderNo(),orderItem.getItemNo(),warehouseId,orderItem.getType(),orderItem.getClsId());
								if (user != null)
									adjustment.setOldClerk(user.getUserId());
								adjustment.setNewClerk(Integer.parseInt(shipClerk));
								adjustment.setAdjustDate(new Date());
								adjustment.setAdjustedBy(SessionUtil.getUserId());
								//adjustment.setReason(orderItem.getStatusUpdReason());
								this.workStationService.updateClerk(adjustment);
							}
						}
					
					}
					
				
			}
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		out.print("<script>parent.$('#shipClerk').dialog('close')</script>");
		//out.print("<script>window.location.reload();</script>");
		out.print("<script>parent.location.href=parent.location.href</script>");
		return null;
	}

	/**
	 * 点击unassign数据
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String unassign() {
		try {
			Integer userId = SessionUtil.getUserId();
			String shipClerk = Struts2Util.getParameter("shipClerk");
			if(shipClerk!=null&&shipClerk.equals("")){
				shipClerk=null;
			}
			//System.out.println(shipClerk+">>>>>>>>>>>>>>>>");
			PrintWriter out = Struts2Util.getResponse().getWriter();
		    String chk = Struts2Util.getRequest().getParameter("chk");
			String[] chks = chk.split(",");
			System.out.println(chks.length+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
			for (int i = 0; chks != null && i < chks.length; i++) {
				
					this.workStationService.updateShipmentById(Integer.valueOf(chks[i]),shipClerk,userId);
				
			}
			if(shipClerk!=null){
				out.print("<script>parent.$('#shipClerk').dialog('close')</script>");
				//out.print("<script>window.location.reload();</script>");
				out.print("<script>parent.window.location='work_station.action'</script>");
				return null;
			}
			

			/*OrderItemDTO item = null;
			ShippingClerkAdjustment adjustment = null;
			for (int i = 0; chks != null && i < chks.length; i++) {
				OrderItem items = this.workStationService.getOrderItemById(chks[i]);
				if(items == null ){
					out.print("<script>alert('Object is null!')</script>");
					out.print("<script>window.location='workstation/work_station.action'</script>");
				}else{
					adjustment = new ShippingClerkAdjustment();
					adjustment.setOrderNo(items.getOrderNo());
					adjustment.setItemNo(items.getItemNo());
					User user = this.workStationService.getAssignedTo(items.getOrderNo(),items.getItemNo(),null,items.getType(),items.getClsId());
					if (user != null)
						adjustment.setOldClerk(user.getUserId());
					adjustment.setNewClerk(null);
					adjustment.setAdjustDate(new Date());
					adjustment.setAdjustedBy(SessionUtil.getUserId());
					//adjustment.setReason(item.getStatusUpdReason());
					this.workStationService.updateClerk(adjustment);
				}
			}*/
			
		} catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
		}
		
		return "mana_Work_Frame";
		
	}

	public Page<OrderItemDTO> getPageOrderItemDTO() {
		return pageOrderItemDTO;
	}

	public void setPageOrderItemDTO(Page<OrderItemDTO> pageOrderItemDTO) {
		this.pageOrderItemDTO = pageOrderItemDTO;
	}

	public OrderItemSrchDTO getOrderItemSrchDTO() {
		return orderItemSrchDTO;
	}

	public void setOrderItemSrchDTO(OrderItemSrchDTO orderItemSrchDTO) {
		this.orderItemSrchDTO = orderItemSrchDTO;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public WorkStationService getWorkStationService() {
		return workStationService;
	}

	public void setWorkStationService(WorkStationService workStationService) {
		this.workStationService = workStationService;
	}

	public OrderMain getOrder() {
		return order;
	}

	public void setOrder(OrderMain order) {
		this.order = order;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}
}