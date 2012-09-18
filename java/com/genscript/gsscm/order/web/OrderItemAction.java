package com.genscript.gsscm.order.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.MoreDetailUtil;
import com.genscript.gsscm.common.util.OrderSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.ItemOtherInfoForNewDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.order.service.OrderItemService;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.CustomServiceDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutationLibrariesDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.PkgServiceDTO;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;
import com.genscript.gsscm.quoteorder.dto.TargetDateDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.service.ShipPackageService;
import com.genscript.gsscm.ws.WSException;

/**
 * order item 相关操作
 * 
 * @author zouyulu
 * 
 */
@Results( {
		@Result(name = "item_detail", location = "order/order_item_detail.jsp"),
		@Result(name = "status_log_list", location = "order/order_item_status_log_list.jsp"),
		@Result(name = "detail_list", location = "order/order_item_detail_list.jsp"),
		@Result(name = "item_list", location = "order/order_item_list.jsp"),
		@Result(name = "show_update_schedule_shipment", location = "order/order_update_schedule_shipment.jsp")
		})
public class OrderItemAction extends BaseAction<OrderItemDTO> {
	private static final long serialVersionUID = -5562145711219715950L;
	@Autowired
	private PublicService publicService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ServService servService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ShipPackageService shipPackageService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired(required = false)
	private DozerBeanMapper dozer;

	private String itemId;
	private String unitPrice;
	private String sessOrderNo;
	private OrderItemDTO orderItem;
	private OrderItemDTO oldOrderItem;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private OrderMainDTO order;
	private Integer warehouseId;
	private List<DropDownDTO> pickLocationList;
	// 用于显示列表
	private Map<String, OrderItemDTO> itemMap;
	// private int triggerLast;
	// 用于更新item qty
	private Double amount;
	private Integer quantity;
	private String prmtCode;
	// 用于getItemOtherInfo
	private Integer storageId;
	// 用于item状态log
	private List<ProcessLogDTO> itemStatusLoglist;
	private String status;
	private String statusText;
	private String statusNote;
	private String statusReason;
	private Integer custNo;
	// 删除id字符串
	private String delItemIdStr;
	Map<Integer, String> shipMethodMap;
	Map<String, String> locationMap;
	// 移动items
	private String itemIdFrom;
	private String itemIdTo;
	private int purchaseOrderFlag;

	// batch gen order
	private String sequence;
	private OrderGeneSynthesisDTO geneSynthesis;
	private OrderCustCloningDTO custCloning;
	private OrderPlasmidPreparationDTO plasmidPreparation;

	//project manager
	private List<SalesRep> projectManagerList;
	private List<SalesRep> altProjectManagerList;
	
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private String shippingRoute;
	private List<ShipPackage> shipPackageList;
	//item detail页面，如果order在数据库中的status为CC，update item status按钮一样可用
	private String dbStatus;
    private Map<String, OrderItemDTO> itemListResultMap;
    private static final String _rtnMessage = "message";
    private static final String _msg_001 = "Failed to change the gift item.";
    private static final String _msg_002 = "Failed to change the item Unit Price, the item is not exists.";
    private static final String _msg_003 = "Failed to change the item Unit Price, only item type is 'Product' can be change.";
    private static final String _msg_004 = "Failed to change the item Unit Price, please enter the valid Unit Price.";
    private static final String _msg_005 = "Failed to change the item cost, the item is not exists.";
    private static final String _msg_006 = "Add new Item failure, The OrderNo is not exist.";
    private static final String _msg_007 = "Add new Item failure, The Order's status is 'CN'.";
    private static final String _msg_008 = "Add new Item failure, The parent Item's status is 'CN'.";
    private static final String PRODUCT = "PRODUCT";
    private List<PbDropdownListOptions> updateReasonSel;
    
	
	/**
	 * 删除item
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String delete() throws Exception {
		orderItemService.deleteItem(delItemIdStr, sessOrderNo);
		Map<String, OrderItemDTO> orderItemMap = (Map<String, OrderItemDTO>) SessionUtil
			.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		this.updateItemDiscount(orderItemMap);
		OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		return null;
	}

	/**
	 * 切换storage 的更新
	 * 
	 * @return
	 * @throws Exception
	 */
//	public String getItemOtherInfo() throws Exception {
//		prepareModel();
//		orderService.getItemOtherInfo(orderItem.getCatalogNo(), storageId,
//				QuoteItemType.valueOf(orderItem.getType()), OrderItemStatusType
//						.valueOf(orderItem.getStatus()), orderItem.getStatusReason(), null);
//		ItemOtherInfoForNewDTO infoForNewDTO = orderService
//				.getItemOtherInfoForNew(storageId, orderItem.getQuantity(),
//						orderItem.getCatalogNo(), QuoteItemType
//								.valueOf(orderItem.getType()));
//		// 更新session
//		orderItem.setStatus(infoForNewDTO.getItemStatus().toString());
//		orderItem.setStatusText(infoForNewDTO.getItemStatusText());
//		orderItem.setOtherInfo(infoForNewDTO.getOtherInfo());
////        orderItem.setUpdateFlag("Y");
//		SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(),
//				sessOrderNo, itemId, orderItem);
//		Struts2Util.renderJson(infoForNewDTO);
//		OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
//		return null;
//	}

	/**
	 * 取得可用的状态列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getItemStatusList() throws Exception {
		OrderMainDTO orderMainDTo = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		String orderStatus = orderMainDTo.getStatus();
		prepareModel();
		List<StatusList> statusLists = new ArrayList<StatusList>();
		if (StringUtils.isNumeric(sessOrderNo)) {
			System.out.println("------->>>>>>>>>>>>>>>status==" + status);
			System.out.println("------->>>>>>>>>>>>>>>orderStatus=="
					+ orderStatus);
			statusLists = publicService.getOrderOrQuoteStatusList("OI", status,
					orderStatus);
		}
		Struts2Util.renderJson(statusLists);
		return null;
	}

	/**
	 * 更新item 的状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateItemStatus() throws Exception {
		prepareModel();
		String oldStatus = orderItem.getStatus();
		Map<String, OrderItemDTO> orderItemMap = getGroupItemMap(itemId, orderItem.getParentId());
		Iterator<Entry<String, OrderItemDTO>> it = orderItemMap.entrySet().iterator();
		Map<String, ItemOtherInfoForNewDTO> rt = new HashMap<String, ItemOtherInfoForNewDTO>();
		// 如果旧状态为CN或者更新后的状态为CM，则需要判断库存，特别处理。
		if (oldStatus.equalsIgnoreCase(OrderItemStatusType.CN.value())
				&& status.equalsIgnoreCase(OrderItemStatusType.CM.value())) {
			OrderMainDTO orderMainDTo = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			String customerCompany = customerService.getCustomerCompany(orderMainDTo.getCustNo(), null);
			OrderItemDTO orderItemDTO = null;
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				orderItemDTO = entry.getValue();
				String tmpItemId = entry.getKey();
				String tmpCatalogNo = orderItemDTO.getCatalogNo();
				Integer tmpQuantity = orderItemDTO.getQuantity();
				String tmpType = orderItemDTO.getType();
				ItemOtherInfoForNewDTO itemOtherInfoForNewDTO = orderService
						.getItemOtherInfoForNew(tmpQuantity,tmpCatalogNo, QuoteItemType.valueOf(tmpType), customerCompany);
				orderItemDTO.setStatus(itemOtherInfoForNewDTO.getItemStatus().toString());
				orderItemDTO.setStatusText(itemOtherInfoForNewDTO.getItemStatusText());
				orderItemDTO.setOtherInfo(itemOtherInfoForNewDTO.getOtherInfo());
				orderItemDTO.setStatusNote(statusNote);
				orderItemDTO.setStatusReason(statusReason);
//                orderItemDTO.setUpdateFlag("Y");
				SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(), sessOrderNo, tmpItemId, orderItemDTO);
				rt.put(tmpItemId, itemOtherInfoForNewDTO);
			}
			if (orderItemDTO != null) {
				// 自动更新order 的状态
				orderItemService.autoChangeMainStatus(orderItemDTO.getStatus(), orderItemDTO.getStatusText(), sessOrderNo);
			}
		} else {
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				String tmpItemId = entry.getKey();
				OrderItemDTO orderItemDTO = entry.getValue();
				ItemOtherInfoForNewDTO itemOtherInfoForNewDTO = new ItemOtherInfoForNewDTO();
				itemOtherInfoForNewDTO.setItemStatus(status);
				itemOtherInfoForNewDTO.setItemStatusText(statusText);
				orderItemDTO.setStatus(status);
				orderItemDTO.setStatusText(statusText);
				orderItemDTO.setStatusNote(statusNote);
				orderItemDTO.setStatusReason(statusReason);
//                orderItemDTO.setUpdateFlag("Y");
				SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(), sessOrderNo, tmpItemId, orderItemDTO);
				rt.put(tmpItemId, itemOtherInfoForNewDTO);
			}
			// 自动更新order 的状态
			orderItemService.autoChangeMainStatus(status, statusText, sessOrderNo);
		}
		// 更改item discount 的条件CN和其他状态转换
		if (oldStatus.equalsIgnoreCase(OrderItemStatusType.CN.value()) || status.equalsIgnoreCase(OrderItemStatusType.CN.value())) {
			this.updateItemDiscount(itemMap);
		}
		OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 更新target date
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateTargetDate() {
		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		Date exprDate = null;
		Date firstConformDate = null;
		Date targetDate = null;
		if(StringUtils.isNumeric(sessOrderNo)){
			firstConformDate = orderItemService.getOrderFirstConfirmDate(Integer.parseInt(sessOrderNo));
		}
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String itemId = entry.getKey();
			OrderItemDTO itemDTO = entry.getValue();
			int shipSchedule = 0;
			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(itemDTO.getType())) {
				TargetDateDTO targetDateDTO = orderItemService.getTargetDate(itemId,
						itemMap);
				if(targetDateDTO != null){
					shipSchedule = targetDateDTO.getScheduleDays();
				}
				if(firstConformDate != null){
					targetDate = DateUtils.dayBefore2Date(firstConformDate,shipSchedule);
				}else{
					targetDate = DateUtils.dayBefore2Date(shipSchedule);
				}
				itemDTO.setTargetDate(targetDate);
				itemDTO.setShipSchedule(shipSchedule);
//                itemDTO.setUpdateFlag("Y");
				
			}else{
				int orderShipSch = 0;
				orderShipSch = itemDTO.getShipSchedule();
				if(firstConformDate != null){
					targetDate = DateUtils.dayBefore2Date(firstConformDate,orderShipSch);
				}else{
					targetDate = DateUtils.dayBefore2Date(orderShipSch);
				}
				itemDTO.setTargetDate(targetDate);
			}
			if (targetDate != null) {
				if (exprDate == null) {
					exprDate = targetDate;
				} else if (targetDate.compareTo(exprDate) == 1) {
					exprDate = targetDate;
				}
			}
		}
		order.setExprDate(exprDate);
		Struts2Util.renderText("SUCCESS");
		return null;
	}
	
	/**
	 * 计算target date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String calculateTargetDate() {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		OrderItemDTO itemDTO = itemMap.get(itemId);
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		if (QuoteItemType.SERVICE.value().equalsIgnoreCase(itemDTO.getType())) {
			targetDateDTO = orderItemService.getTargetDate(itemId,
					itemMap);
		}
		Struts2Util.renderJson(targetDateDTO);
		return null;
	}
	
	/**
	 * 更新Item的ScheduleShipment
	 * @author Zhang Yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateItemScheduleShipment () {
		Map<String, Object> rt = new HashMap<String, Object>();
		String newScheduleShipment = (String) ServletActionContext.getRequest().getParameter("newScheduleShipment");
		String update_reason_sel = ServletActionContext.getRequest().getParameter("update_reason_sel");
		String reason = (String) ServletActionContext.getRequest().getParameter("reason");
		if (StringUtils.isBlank(sessOrderNo) || StringUtils.isBlank(itemId)
				|| StringUtils.isBlank(newScheduleShipment) || !StringUtils.isNumeric(newScheduleShipment)
				|| StringUtils.isBlank(reason)) {
			Struts2Util.renderJson(rt);
			return null;
		}
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (orderItem == null) {
			Struts2Util.renderJson(rt);
			return null;
		}
		if (orderItem.getShipSchedule() == null || Integer.parseInt(newScheduleShipment) != orderItem.getShipSchedule()) {
			orderItem.setShipSchedule(Integer.parseInt(newScheduleShipment));
            if(update_reason_sel != null){
               orderItem.setReasonNote(update_reason_sel);
            }
			orderItem.setReason(reason);
		}
//        orderItem.setUpdateFlag("Y");
		rt.put("newScheduleShipment",newScheduleShipment);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 跳转到修改ItemScheduleShipment页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showItemScheduleShipment () {
		if (StringUtils.isNotBlank(sessOrderNo) || StringUtils.isNotBlank(itemId)) {
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
					SessionConstant.OrderItemList.value(), sessOrderNo);
			orderItem = itemMap.get(itemId);
            order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
            if (OrderStatusType.RV.name().equals(order.getStatus())) {
                updateReasonSel = publicService.getUpdateReasonDropdownList(orderItem.getType(), orderItem.getClsId());
            }
		}
		return "show_update_schedule_shipment";
	}

	/**
	 * 取得主服务下所有的item,主要用于service
	 * 
	 * @param orderItemId
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, OrderItemDTO> getGroupItemMap(String orderItemId,
			String parentId) {
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		Map<String, OrderItemDTO> itemGroupMap = new HashMap<String, OrderItemDTO>();
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			if (orderItemDTO.getParentId() == null) {
				orderItemDTO.setParentId("");
			}
			if (orderItemDTO.getParentId().equalsIgnoreCase(orderItemId)
					|| tmpId.equalsIgnoreCase(orderItemId)
					|| (!StringUtils.isEmpty(parentId)
							&& !parentId.equalsIgnoreCase("0") && (orderItemDTO
							.getParentId().equalsIgnoreCase(parentId) || tmpId
							.equalsIgnoreCase(parentId)))) {
				itemGroupMap.put(tmpId, orderItemDTO);
			}
		}
		return itemGroupMap;
	}

	/**
	 * 显示编辑item的form
	 */
	@Override
	public String input() throws Exception {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		if(order !=null){
			dbStatus = order.getDbStatus();
			warehouseId = order.getWarehouseId();
		}
		// 下拉列表
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		// speclListName.add(SpecDropDownListName.PICK_LOCATION);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		pickLocationList = publicService.getPickLocationList(warehouseId);
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownMap.put(DropDownListName.SHIPPING_ROUTE.value(),publicService.getDropdownList(DropDownListName.SHIPPING_ROUTE.value()));
		if (orderItem != null && orderItem.getOrderItemId() != null) {
			shipPackageList = shipPackageService.findByOrderNoAndItemNo(orderItem.getOrderNo(), orderItem.getItemNo());
		}
		//
		if(orderItem!=null&&QuoteItemType.SERVICE.value().equals(orderItem.getType())) {
			altProjectManagerList = this.orderItemService.getAllSalesRep();
		}
		if (orderItem != null && CatalogType.PRODUCT.value().equals(orderItem.getType()) && StringUtils.isBlank(orderItem.getOtherInfo())) {
			String itemOtherInfo = orderService.getItemOtherInfo(orderItem.getType(), orderItem.getStatus(), 
					orderItem.getCatalogNo(), orderItem.getStatusReason(), null, order.getCustNo());
			orderItem.setOtherInfo(itemOtherInfo);
		}
		return "item_detail";
	}

	/**
	 * 显示item 列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		itemListResultMap = new LinkedHashMap<String, OrderItemDTO>();
        ArrayList<String[]> resultTempList = new ArrayList<String[]>();
        if (itemMap != null && !itemMap.isEmpty()) {
            for (String key : itemMap.keySet()) {
                OrderItemDTO dto = itemMap.get(key);
                if (StringUtils.isBlank(dto.getParentId())) {
                    String[] keys = new String[2];
                    keys[0] = key;
                    resultTempList.add(keys);
                } else {
                    boolean re = setAllItemInArray(resultTempList, dto.getParentId(), key);
                    if(!re){
                        String[] keys = new String[2];
                        keys[0] = dto.getParentId();
                        resultTempList.add(keys);
                        setAllItemInArray(resultTempList, dto.getParentId(), key);
                    }
                }
            }
        }
        for (String[] keys : resultTempList) {
            itemListResultMap.put(keys[0], orderItemService.getItemMoreDetail(itemMap.get(keys[0])));
            String childKeys = keys[1];
            if(childKeys != null){
                childKeys = keys[1].substring(1, keys[1].length());
            }else {
                continue;
            }
            for(int i = 0; i < childKeys.split(",").length; i++){
                itemListResultMap.put(childKeys.split(",")[i], orderItemService.getItemMoreDetail(itemMap.get(childKeys.split(",")[i])));
            }
        }
		return "item_list";
	}

    /*
    * 在已经生成的Array中查找父item,
    * 返回false的情况为：
    * HashMap 先得到了子item，在Array里查找时候，父级item还没有被加入
    * */
    boolean setAllItemInArray(ArrayList<String[]> itemArray, String parentId, String ckey){
        if(parentId.equals(ckey)) return true;
        boolean resultFlag = false;
        for(int i = itemArray.size() -1; i >= 0 ; i--){
            String key = itemArray.get(i)[0];
            String childKeys = itemArray.get(i)[1] == null ? "" : itemArray.get(i)[1];
            if(key.equals(parentId) || childKeys.indexOf("," + parentId) >= 0){
                itemArray.get(i)[1] = childKeys + "," + ckey ;
                resultFlag = true;
            }
        }
        return resultFlag;
    }

	/**
	 * 移动item
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String exchangeItem() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		Map<String, OrderItemDTO> newItemMap = new LinkedHashMap<String, OrderItemDTO>();
		if (itemMap != null) {
			OrderItemDTO orderItemDTOFrom = itemMap.get(itemIdFrom);
			OrderItemDTO orderItemDTOTo = itemMap.get(itemIdTo);
			Integer fromNo = orderItemDTOFrom.getItemNo();
			Integer toNo = orderItemDTOTo.getItemNo();
			boolean downFlag = true;
			if (fromNo > toNo) {
				downFlag = false;
			}
			orderItemDTOTo.setItemNo(fromNo);
			orderItemDTOFrom.setItemNo(toNo);

			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				String tmpId = entry.getKey();
				OrderItemDTO tmpDTO = entry.getValue();
//                tmpDTO.setUpdateFlag("Y");
				if (tmpId.equalsIgnoreCase(itemIdTo)
						|| tmpId.equalsIgnoreCase(itemIdFrom)) {
					if (downFlag == true) {
						if (tmpId.equalsIgnoreCase(itemIdFrom)) {
							newItemMap.put(itemIdTo, orderItemDTOTo);
						} else {
							newItemMap.put(itemIdFrom, orderItemDTOFrom);
						}
					} else {
						if (tmpId.equalsIgnoreCase(itemIdTo)) {
							newItemMap.put(itemIdFrom, orderItemDTOFrom);
						} else {
							newItemMap.put(itemIdTo, orderItemDTOTo);
						}
					}
				} else {
//                    tmpDTO.setUpdateFlag("Y");
					newItemMap.put(tmpId, tmpDTO);
				}
			}
		}
		SessionUtil.insertRow(SessionConstant.OrderItemList.value(),
				sessOrderNo, newItemMap);
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	/**
	 * 显示所有item详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String detailList() throws Exception {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		warehouseId = order.getWarehouseId();
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);

		List<DropDownDTO> shipMethodList = specDropDownList.get(
				SpecDropDownListName.SHIP_METHOD).getDropDownDTOs();
		shipMethodMap = new HashMap<Integer, String>();
		String firstMethodName = "";
		if (shipMethodList != null && !shipMethodList.isEmpty()) {
			for (int i = 0; i < shipMethodList.size(); i++) {
				if (i == 0) {
					firstMethodName = shipMethodList.get(i).getName();
				}
				shipMethodMap.put(Integer.parseInt(shipMethodList.get(i)
						.getId()), shipMethodList.get(i).getName());
			}
		}
		pickLocationList = publicService.getPickLocationList(warehouseId);
		locationMap = new HashMap<String, String>();
		if (pickLocationList != null && !pickLocationList.isEmpty()) {
			for (int i = 0; i < pickLocationList.size(); i++) {
				locationMap.put(pickLocationList.get(i).getId(),
						pickLocationList.get(i).getName());
			}
		}
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		Struts2Util.getRequest().setAttribute("firstMethodName",
				firstMethodName);
		return "detail_list";
	}

	/**
	 * 显示item status log
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getItemStatusHist() throws Exception {
		if (StringUtils.isNumeric(itemId)) {
            if (itemId == "" || (itemId.length() < 1) || itemId == null) {
			} else {
			itemStatusLoglist = orderService.getItemStatusHist(Integer
					.parseInt(itemId));
            }
		}
		return "status_log_list";
	}

	/**
	 * 更新item的qty
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateItemQty() {
		Map<String, Object> rt = new HashMap<String, Object>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (quoteOrderService.isGifItem(orderItem, sessOrderNo)) {
			rt.put(_rtnMessage, _msg_001);
			Struts2Util.renderJson(rt);
			return null;
		}
		order = (OrderMainDTO)SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		orderItem.setQuantity(quantity);
		orderItem.setAmount(new BigDecimal(amount));
		if (unitPrice != null && !("").equals(unitPrice.trim())) {
			unitPrice = unitPrice.trim();
			orderItem.setUnitPrice(new BigDecimal(Double.parseDouble(unitPrice)));
		}
		String customerCompany = customerService.getCustomerCompany(order.getCustNo(), null);
		// Added for calculate status
		ItemOtherInfoForNewDTO itemOtherInfo = orderService.getItemOtherInfoForNew(orderItem.getQuantity(), 
				orderItem.getCatalogNo(), QuoteItemType.fromValue(orderItem.getType()), customerCompany);
		if (StringUtils.isNumeric(itemOtherInfo.getOtherInfo())) {
			orderItem.setOtherInfo("In Stock," + itemOtherInfo.getOtherInfo() + " Available");
		} else {
			orderItem.setOtherInfo(itemOtherInfo.getOtherInfo());
		}
		if (orderItem.getType().equalsIgnoreCase(PRODUCT)) {
			orderItem.setStatus(itemOtherInfo.getItemStatus());
			orderItem.setStatusText(itemOtherInfo.getItemStatusText());
		} else {
			orderItem.setStatus("CM");
			orderItem.setStatusText("Committed");
			this.setServiceForItem(orderItem, MoreDetailUtil.getServiceNameByClsId(orderItem.getClsId()));
		}
		orderItem = orderItemService.getOfferOrderItem(order, orderItem);
		// end
		itemMap.put(itemId, orderItem);
		// 判断是否可以更改
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		List<Double> amountList = new ArrayList<Double>();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			amountList.add(orderItemDTO.getAmount().doubleValue());
		}
		try {
			if (order.getOrderPromotion() != null && StringUtils.isNotEmpty(order.getOrderPromotion().getPrmtCode())) {
				if (orderService.checkOrderTotal(amountList, order.getOrderPromotion().getPrmtCode()) == false) {
					throw new BussinessException(BussinessException.INF_PROMOTION_CANNOT_APPLY);
				}
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
			return null;
		}
        orderItem.setUpdateFlag("Y");
		SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(), sessOrderNo, itemId, orderItem);
		this.updateItemDiscount(itemMap);
		Struts2Util.renderJson(this.renderDiscount());
		OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String updateItemPrice() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			orderItem = itemMap.get(itemId);
            unitPrice = unitPrice.trim();
            String validStr = updateItemPriceValid();
            if(!"".equals(validStr)){
                rt.put(_rtnMessage, validStr);
                Struts2Util.renderJson(rt);
				return null;
            }
            //add by zhanghuibin
            if (3 == orderItem.getClsId()) {
                if (Double.parseDouble(unitPrice) < 159) unitPrice = "159";
            }
            updateItemPriceMain(rt);
			Map<String, Double> discountMap = this.renderDiscount();
			rt.put("discountMap", discountMap);
			OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
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
    *价格更新验证 j
    * */
	private String updateItemPriceValid() {
        String message = "";
       if (orderItem == null) {
				 message = _msg_002;
			}else if (!QuoteItemType.SERVICE.value().equalsIgnoreCase(orderItem.getType())) {
				message = _msg_003;
			}else if (unitPrice == null || ("").equals(unitPrice) || !StringUtil.isNumeric(unitPrice.trim())) {
				message = _msg_004;
			}else if (quoteOrderService.isGifItem(orderItem, sessOrderNo)) {
				message = _msg_001;
			}
        return message;
    }

    private void updateItemPriceMain(Map<String, Object> rt){
			order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			String sessUnitPrice = "";
			String sessAmount = "";
			boolean isJPY = CurrencyType.JPY.value().equals(order.getOrderCurrency())?true:false;
			int small = isJPY?0:2;
			sessUnitPrice = orderItem.getUnitPrice()== null?(isJPY?"0":"0.00"):
				orderItem.getUnitPrice().setScale(small, BigDecimal.ROUND_HALF_UP).toString();
			sessAmount = orderItem.getAmount()== null?(isJPY?"0":"0.00"):
				orderItem.getAmount().setScale(small, BigDecimal.ROUND_HALF_UP).toString();
			rt.put("unitPrice",sessUnitPrice);
			rt.put("amount",sessAmount);
			rt.put("cost",orderItem.getCost().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			if(StringUtils.isNumeric(itemId)){
				OrderItem dbItem = orderService.getOrderItemByItemId(Integer.parseInt(itemId));
				//------------------Add By Zhang Yong Start -----------------------------//
				Double dbUnitPrice = dbItem.getUnitPrice();
				Double paramUnitPrice = orderItem.getUnitPrice().doubleValue();
				//UnitPrice有做修改时，更新orderItem的PriceChanged状态为Y，代表unitprice修改过，不再计算价格
				if (dbUnitPrice != null && paramUnitPrice != null && (dbUnitPrice.doubleValue() != paramUnitPrice.doubleValue())) {
					orderItem.setPriceChanged(OrderItemStatusType.Y.value());
				}
			}
			//------------------Add By Zhang Yong End -----------------------------//
			// 判断是否可以更改
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			List<Double> amountList = new ArrayList<Double>();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				amountList.add(orderItemDTO.getAmount().doubleValue());
			}
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			if (order.getOrderPromotion() != null && StringUtils.isNotEmpty(order.getOrderPromotion().getPrmtCode())) {
				if (!orderService.checkOrderTotal(amountList, order.getOrderPromotion().getPrmtCode())) {
					throw new BussinessException(
							BussinessException.INF_PROMOTION_CANNOT_APPLY);
				}
			}
			orderItem.setUnitPrice(new BigDecimal(Double.parseDouble(unitPrice)).setScale(small, BigDecimal.ROUND_HALF_UP));
			if (orderItem.getBasePrice() == null || orderItem.getBasePrice().intValue() == 0) {
				BigDecimal basePrice = orderService.getOrderChangeCurrency(orderItem.getUnitPrice(), null, order.getOrderCurrency(), CurrencyType.USD.value());
				orderItem.setBasePrice(basePrice);
			}
			orderItem.setAmount(new BigDecimal(Double.parseDouble(unitPrice)*orderItem.getQuantity()).setScale(small, BigDecimal.ROUND_HALF_UP));
            //add by zhanghuibin gene需要计算 price
            if(orderItem.getClsId() == 3 && orderItem.getGeneSynthesis() != null){
                Double seql = Double.valueOf(orderItem.getGeneSynthesis().getSeqLength());
                Double uprice = orderItem.getUnitPrice().doubleValue();
                orderItem.getGeneSynthesis().setBpPrice(ArithUtil.div(uprice, seql, 2));
            }
            rt.put("unitPrice",orderItem.getUnitPrice().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			rt.put("amount",orderItem.getAmount().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			rt.put("cost",orderItem.getCost().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			orderItem.setTbdFlag("0");
            orderItem.setUpdateFlag("Y");
            this.updateItemDiscount(itemMap);
    }

    /*
    * add by zhanghuibin
    * Sales Information tab 中更新TBD Unit Price价格
    * */
    @SuppressWarnings("unchecked")
	public String updateOrderUnitPrice() {
        itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
        unitPrice = unitPrice.trim();
        Map<String, Object> rt = new HashMap<String, Object>();
        if (unitPrice != null && !"".equals(unitPrice)) {
            @SuppressWarnings("rawtypes")
			Iterator it = itemMap.keySet().iterator();
            while (it.hasNext()) {
                String key = (String)it.next();
                OrderItemDTO item = itemMap.get(key);
                if ("1".equals(item.getTbdFlag())) {
                    itemId = key;
                    orderItem = itemMap.get(itemId);
                    String validStr = updateItemPriceValid();
                    if (!"".equals(validStr)) {
                        rt.put(_rtnMessage, validStr);
                        break;
                    }
                    if(3 == item.getClsId()){
                        if(Double.parseDouble(unitPrice) < 159) unitPrice = "159";
                    }
                    updateItemPriceMain(rt);
                }
            }
            rt.put(_rtnMessage, "ok");
        } else {
            rt.put(_rtnMessage, "fail");
        }
        Struts2Util.renderJson(rt);
        return null;
    }

	/**
	 * Item列表中修改Item的Cost
	 * @author Zhang Yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateItemCost () {
		Map<String, Object> rt = new HashMap<String, Object>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (orderItem == null) {
			rt.put(_rtnMessage, _msg_005);
			Struts2Util.renderJson(rt);
			return null;
		}
		order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		boolean isJPY = CurrencyType.JPY.value().equals(order.getOrderCurrency())?true:false;
		int small = isJPY?0:2;
		String sessCost = orderItem.getCost()== null?(isJPY?"0":"0.00"):
			orderItem.getCost().setScale(small, BigDecimal.ROUND_HALF_UP).toString();
		rt.put("cost",sessCost);
		if (quoteOrderService.isGifItem(orderItem, sessOrderNo)) {
			rt.put(_rtnMessage, _msg_001);
			Struts2Util.renderJson(rt);
			return null;
		}
		String cost = Struts2Util.getParameter("cost");
		if (StringUtils.isBlank(cost) || !StringUtil.isNumeric(cost.trim())) {
			rt.put(_rtnMessage, "Failed to change the item cost, please enter the valid cost.");
			Struts2Util.renderJson(rt);
			return null;
		}
		orderItem.setCost(new BigDecimal(Double.parseDouble(cost)).setScale(small, BigDecimal.ROUND_HALF_UP));
        //若是内部订单，则把cost值赋给unitprice，(pkgService服务有cost和unitprice，这里为给服务赋值)
		if (orderItemService.isInternalOrder(sessOrderNo)) {
        	orderItemService.updatePriceByInternal(orderItem, sessOrderNo);
        }
    	rt.put("unitPrice",orderItem.getUnitPrice().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
    	rt.put("amount",orderItem.getAmount().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
        orderItem.setTbdFlag("0");
		orderItem.setUpdateFlag("Y");
		OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		rt.put("cost",orderItem.getCost().toString());
		Struts2Util.renderJson(rt);
		return null;
	}

	/**
	 * 返回item的discount列表（json格式）
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Double> renderDiscount() {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		Map<String, Double> jsonMap = new HashMap<String, Double>();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpItemId = entry.getKey();
			OrderItemDTO orderItemDTO = entry.getValue();
			Double discount = orderItemDTO.getDiscount().doubleValue();
			jsonMap.put(tmpItemId, discount);
		}
		return jsonMap;
	}

	/**
	 * 更新item 的discount
	 * 
	 * @param passItemMap
	 */
	private void updateItemDiscount(Map<String, OrderItemDTO> passItemMap) {
		List<ItemDiscountDTO> discountDTOList = new ArrayList<ItemDiscountDTO>();
		Map<String, Object> rt = new HashMap<String, Object>();
		Iterator<Entry<String, OrderItemDTO>> it = passItemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			ItemDiscountDTO itemDiscountDTO = new ItemDiscountDTO();
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpItemId = entry.getKey();
			OrderItemDTO orderItemDTO = entry.getValue();
			// 取消状态的特殊处理
			if (orderItemDTO.getStatus().equalsIgnoreCase(
					OrderItemStatusType.CN.value())) {
				continue;
			}
			itemDiscountDTO.setAmount(orderItemDTO.getAmount());
			itemDiscountDTO.setStatus(orderItemDTO.getStatus());
			itemDiscountDTO.setItemId(tmpItemId);
			itemDiscountDTO.setCatalogNo(orderItemDTO.getCatalogNo());
			discountDTOList.add(itemDiscountDTO);
		}
		if (StringUtils.isNotEmpty(prmtCode)) {
			try {
				discountDTOList = orderService.getItemDiscount(discountDTOList,
						prmtCode);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				rt.put("hasException", "Y");
				rt.put("exception", exDTO);
				Struts2Util.renderJson(rt);
			}
			for (int i = 0; i < discountDTOList.size(); i++) {
				String tmpId = discountDTOList.get(i).getItemId();
				Double tmpDiscount = discountDTOList.get(i).getDiscount()
						.doubleValue();
				OrderItemDTO orderItemDTO = passItemMap.get(tmpId);
				orderItemDTO.setDiscount(new BigDecimal(tmpDiscount));
//                orderItemDTO.setUpdateFlag("Y");
				passItemMap.put(tmpId, orderItemDTO);
			}
			SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
					sessOrderNo, passItemMap);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(itemId)) {
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
					SessionConstant.OrderItemList.value(), sessOrderNo);
			orderItem = itemMap.get(itemId);
			// 初始化保存前的orderitem，用于比较
			this.oldOrderItem = new OrderItemDTO();
			this.oldOrderItem.setShipMethod(orderItem.getShipMethod());
			this.oldOrderItem.setStatus(orderItem.getStatus());
			this.oldOrderItem.setStorageId(orderItem.getStorageId());
			this.oldOrderItem.setShipToAddress(orderItem.getShipToAddress());
			this.oldOrderItem.setQuantity(orderItem.getQuantity());
			if(QuoteItemType.SERVICE.value().equals(orderItem.getType())&&orderItem.getClsId()!=null) {
				projectManagerList = this.orderItemService.getSalesRepByClsId(orderItem.getClsId());
			}
		}
	}

	/**
	 * 保存item
	 */
	@Override
	public String save() throws Exception {
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
		}
		if(orderItem != null){
            //add by zhanghuibin
//            orderItem.setUpdateFlag("Y");
			if (StringUtils.isBlank(orderItem.getNameShow())) {
				orderItem.setNameShow(orderItem.getName());
//                orderItem.setUpdateFlag("Y");
			}
			SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(), sessOrderNo, itemId, orderItem);
		}
		if (this.checkIfRemovePackages(this.oldOrderItem, orderItem) == true) {
			OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		}
		return null;
	}

	/**
	 * 判断是否要remove package
	 * @param oldItemDTO
	 * @param newItemDTO
	 * @return
	 */
	private boolean checkIfRemovePackages(OrderItemDTO oldItemDTO,
			OrderItemDTO newItemDTO) {
		if (oldItemDTO == null || newItemDTO == null) {
			return false;
		}
		if (!oldItemDTO.getStatus().equalsIgnoreCase(newItemDTO.getStatus())) {
			return true;
		}
		if (oldItemDTO.getShipMethod().intValue() != newItemDTO.getShipMethod()
				.intValue()) {
			return true;
		}
		if (oldItemDTO.getStorageId().intValue() != newItemDTO.getStorageId()
				.intValue()) {
			return true;
		}
		if (oldItemDTO.getQuantity().intValue() != newItemDTO.getQuantity()
				.intValue()) {
			return true;
		}
		OrderAddress oldAddress = oldItemDTO.getShipToAddress();
		OrderAddress newAddress = newItemDTO.getShipToAddress();
		if ((oldAddress == null && newAddress != null)
				|| (oldAddress != null && newAddress == null)) {
			return true;
		} else if (oldAddress != null && newAddress != null) {
			if (!oldAddress.getCountry().equalsIgnoreCase(
					newAddress.getCountry())) {
				return true;
			}
			if (!oldAddress.getZipCode().equalsIgnoreCase(
					newAddress.getZipCode())) {
				return true;
			}
		}
		return false;
	}

     /**
     * add by zhanghuibin
	 * 用于batch新增item
	 * quote_item!saveSessionItem.action?sessQuoteNo=964763&custNo=90669&srcQuOrderNo=
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public String saveBatchSessionItem() throws Exception {
		//传入参数：custNo、sessOrderNo、itemNum、catalogNo，itemNum默认赋值为1
        Integer itemNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("itemNum"));
        String catalogNo = ServletActionContext.getRequest().getParameter("catalogNo");
		order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		Map<String, String> retMap = new HashMap<String, String>();
		if (order == null) {
			retMap.put(_rtnMessage, _msg_006);
		} else {
			if (OrderStatusType.CN.value().equalsIgnoreCase(order.getStatus())) {
				retMap.put(_rtnMessage, _msg_007);
			}
		}
		if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		ServiceItemBean serviceItemBean = servService.getGeneServiceItem(catalogNo);
        List<ServiceItemBean> serviceItems = new ArrayList<ServiceItemBean>();
        for(int i=0;i<itemNum;i++){
            ServiceItemBean itemTemp = dozer.map(serviceItemBean, ServiceItemBean.class);
            itemTemp.setServiceId(null);
            itemTemp.setUnitPrice(itemTemp.getUnitPrice()==null?"0":itemTemp.getUnitPrice());
            serviceItems.add(itemTemp);
        }
		if (serviceItems != null && serviceItems.size() > 0) {
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			Map<String, OrderItemDTO> tempItemMap = new HashMap<String, OrderItemDTO>();
			if (itemMap == null) {
				itemMap = new LinkedHashMap<String, OrderItemDTO>();
				SessionUtil.insertRow(SessionConstant.OrderItemList.value(),sessOrderNo, itemMap);
			}
			order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			Integer shiptoAddrFlag = order.getShiptoAddrFlag();
			OrderAddress defaultAddress = null;
			if (shiptoAddrFlag != null && (shiptoAddrFlag.equals(1) || shiptoAddrFlag.equals(2))) {
				if (order.getOrderAddrList() != null) {
					defaultAddress = order.getOrderAddrList().getShipToAddr();
				}
			}
			CustomerDTO customer = this.customerService.getCustomerBase(custNo);
			String custTaxStatus = customer.getTaxExemptFlag();
			String taxId = customer.getTaxId();
			Integer defalutStorageId = this.getDefaultStorageId(order.getWarehouseId());
			Integer defalutShipMethod = this.getDefaultShipMethod();
			Map<String, String> toItemNoIdMap = new HashMap<String, String>();
			a:for (int i = 0; i < serviceItems.size(); i++) {
				itemId = SessionUtil.generateTempId();
				orderItem = dozer.map(serviceItems.get(i), OrderItemDTO.class);
                //处理数据
                orderItem.setItemNo(itemMap.size());
                orderItem.setQuantity(1);
                orderItem.setUnitPrice(new BigDecimal(0));
                orderItem.setAmount(orderItem.getUnitPrice());
                orderItem.setType(CatalogType.SERVICE.value());
                orderItem.setCost(new BigDecimal(0));
                orderItem.setUpSymbol("$");
                orderItem.setSizeUom(serviceItems.get(i).getUom());
                orderItem.setParentId("");
                orderItem.setPreParentId("");
                orderItem.setCatalogText(serviceItems.get(i).getCatalogId() + ":");
                orderItem.setSrcItemNo("");
                orderItem.setTemplateType("");
				if (orderItem.getTax() == null || ("").equals(orderItem.getTax().toString())) {
					orderItem.setTax(new BigDecimal(0));
				}
				tempItemMap.put(itemId, orderItem);
				String srcOrderItemNo = orderItem.getSrcItemNo();
				if (StringUtils.isNotEmpty(srcOrderItemNo)) {
					toItemNoIdMap.put(srcOrderItemNo, itemId);
				}
				SearchItemDTO searchItemDTO = servService.getSearchItemInfo(orderItem.getCatalogNo(), custNo);
				orderItem.setCost(new BigDecimal(0.0));
				orderItem.setTypeText(orderItem.getType() + " - " + searchItemDTO.getClsName());
				orderItem.setClsId(searchItemDTO.getClsId());
				orderItem.setBasePrice(orderItem.getUnitPrice());
				if(orderItem.getBasePrice()==null) {
					orderItem.setBasePrice(new BigDecimal(0));
				}
				if ("Y".equalsIgnoreCase(custTaxStatus) && !StringUtils.isEmpty(taxId)) {
					orderItem.setTaxStatus("N");
				} else {
					orderItem.setTaxStatus(searchItemDTO.getTaxStatus());
				}
				orderItem.setShortDesc(searchItemDTO.getDescription());
				orderItem.setLongDesc(searchItemDTO.getFullDesc());
				orderItem.setSellingNote(searchItemDTO.getSellingNote());
			    orderItem.setOtherInfo("In Stock, Available");
				orderItem.setStatus("CM");
				orderItem.setStatusText("Committed");
				orderItem.setShipMethod(defalutShipMethod);
				orderItem.setStorageId(defalutStorageId);
				orderItem.setShipToAddress(defaultAddress);
				orderItem.setNameShow(orderItem.getName());
				if (!StringUtils.isEmpty(order.getOrderCurrency())) {
					orderItem.setCurrencyCode(order.getOrderCurrency());
					orderItem = dozer.map(orderItem, OrderItemDTO.class);
					Double rate = publicService.getRateByCurrencyToBaseCurrency(order.getOrderCurrency(), order.getExchRateDate());
					if (rate != null && orderItem.getUnitPrice() != null) {
						int small = CurrencyType.JPY.value().equalsIgnoreCase(order.getOrderCurrency())?0:2;
						orderItem.setBasePrice(orderItem.getUnitPrice().multiply(new BigDecimal(rate)).setScale(small, BigDecimal.ROUND_HALF_UP));
					}
				}
                //如果其余服务类型增加需修改这里
                if (searchItemDTO.getClsId() == 3) {
                	//gene
                    orderItem.setGeneSynthesis(new OrderGeneSynthesisDTO());
                } else if (searchItemDTO.getClsId() == 34) {
                	//oligo
                    orderItem.setOligo(new OrderOligoDTO());
                }
                if (searchItemDTO.getClsId() == 34) {
                	itemId = orderItemService.insertSpecialService(itemId, orderItem, sessOrderNo, itemMap);
                } else {
    				this.insertNewChild(itemMap, null, itemId, orderItem);
                }
                //返回itemId
                if (retMap.get("itemNOs") == null) {
                    retMap.put("itemNOs", itemId);
                } else{
                    retMap.put("itemNOs", retMap.get("itemNOs") + "," + itemId);
                }
			}
			this.updateItemDiscount(itemMap);
			OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		}
        if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
		}
		return null;
	}

	/**
	 * 用于新增item
	 *
     * 修改此方法要同时修改 saveBatchSessionItem 方法，两个方法基本相同
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveSessionItem() throws Exception {
		order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		Map<String, String> retMap = new HashMap<String, String>();
		if (order == null) {
			retMap.put(_rtnMessage, _msg_006);
		} else {
			if (OrderStatusType.CN.value().equalsIgnoreCase(order.getStatus())) {
				retMap.put(_rtnMessage, _msg_007);
			}
		}
		if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		String srcQuOrderNo = Struts2Util.getParameter("srcQuOrderNo");
		String[] itemsJsonString = Struts2Util.getRequest().getParameterValues("items");
		if (itemsJsonString != null && itemsJsonString.length > 0) {
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			Map<String, OrderItemDTO> tempItemMap = new HashMap<String, OrderItemDTO>();
			if (itemMap == null) {
				itemMap = new LinkedHashMap<String, OrderItemDTO>();
				SessionUtil.insertRow(SessionConstant.OrderItemList.value(),sessOrderNo, itemMap);
			}
			Integer defalutShipMethod = this.getDefaultShipMethod();
			Integer shiptoAddrFlag = order.getShiptoAddrFlag();
			OrderAddress defaultAddress = null;
			if (shiptoAddrFlag != null && (shiptoAddrFlag.equals(1) || shiptoAddrFlag.equals(2))) {
				if (order.getOrderAddrList() != null) {
					defaultAddress = order.getOrderAddrList().getShipToAddr();
				}
			}
			custNo = order.getCustNo();
			CustomerDTO customer = this.customerService.getCustomerBase(custNo);
			String custTaxStatus = customer.getTaxExemptFlag();
			String taxId = customer.getTaxId();
			String customerCompany = customerService.getCustomerCompany(custNo, customer);
			Integer defalutStorageId = this.getDefaultStorageId(order.getWarehouseId());

			Map<String, String> toItemNoIdMap = new HashMap<String, String>();
			a: for (int i = 0; i < itemsJsonString.length; i++) {
				itemId = SessionUtil.generateTempId();
				orderItem = Struts2Util.conventJsonToJavaObj(itemsJsonString[i], OrderItemDTO.class);
				if (!StringUtils.isEmpty(orderItem.getParentId())) {
					OrderItemDTO sessParentDTO = itemMap.get(orderItem.getParentId());
					if (OrderItemStatusType.CN.value().equalsIgnoreCase(sessParentDTO.getStatus())) {
						retMap.put(_rtnMessage, _msg_008);
						Struts2Util.renderJson(retMap);
						return NONE;
					}
				}
				////////////////////////////////
				//添加ServiceTemplate Item
				if (ItemSearchType.SERVICE_TEMPLATE_ITEM.value().equals(orderItem.getTemplateType())) {
					itemMap = orderItemService.saveTempItemsItem(itemMap, order, orderItem, srcQuOrderNo, customer, 
							defalutShipMethod, defaultAddress, sessOrderNo);
					this.updateItemDiscount(itemMap);
					OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
					if (i == (itemsJsonString.length - 1)) {
						return NONE;
					} else {
						continue;
					}
				}
				////////////////////////////////
				if (orderItem.getTax() == null || ("").equals(orderItem.getTax().toString())) {
					orderItem.setTax(new BigDecimal(0));
				}
				if (orderItem.getType().equalsIgnoreCase(PRODUCT)) {
					for (Iterator<String> iter = itemMap.keySet().iterator(); iter.hasNext();) {
						String key = iter.next();
						OrderItemDTO val = (OrderItemDTO) itemMap.get(key);
						if (val != null && val.getCatalogNo().equals(orderItem.getCatalogNo())) {
							val.setQuantity(val.getQuantity()+ orderItem.getQuantity());
							val.setAmount(val.getUnitPrice().multiply(new BigDecimal(val.getQuantity())));
//							orderItem = orderItemService.getOfferOrderItem(order, val);
							Integer stockQty = orderService.getItemStockQty(val.getCatalogNo(), customerCompany);
							if (stockQty != null && (stockQty.intValue() == 0 || stockQty.intValue()<val.getQuantity().intValue())) {
								val.setStatus(OrderItemStatusType.BO.value());
							}
							break a;
						}
					}
				}
//				orderItem = orderItemService.getOfferOrderItem(order, orderItem);
				tempItemMap.put(itemId, orderItem);
				String srcQuoteItemNo = orderItem.getSrcItemNo();
				if (StringUtils.isNotEmpty(srcQuoteItemNo)) {
					toItemNoIdMap.put(srcQuoteItemNo.toString(), itemId);
				}
				SearchItemDTO searchItemDTO = null;
				if (orderItem.getType().equalsIgnoreCase(PRODUCT)) {
					searchItemDTO = productService.getSearchItemInfo(orderItem.getCatalogNo());
					BigDecimal unitCost = productService.getUnitCost(orderItem.getCatalogNo());
					if (unitCost == null)
						unitCost = new BigDecimal(0.0);
					orderItem.setCost(unitCost);
				} else {
					searchItemDTO = servService.getSearchItemInfo(orderItem.getCatalogNo(), custNo);
					orderItem.setCost(new BigDecimal(0.0));
				}
				orderItem.setTypeText(orderItem.getType() + " - " + searchItemDTO.getClsName());
				orderItem.setClsId(searchItemDTO.getClsId());
				orderItem.setBasePrice(orderItem.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
				if(orderItem.getBasePrice()==null) {
					orderItem.setBasePrice(new BigDecimal(0));
				}
				// 为了能保存OrderItem设置的shipTOAddrId假数据
				// orderItem.setShiptoAddrId(6276);
				if ("Y".equalsIgnoreCase(custTaxStatus) && !StringUtils.isEmpty(taxId)) {
					orderItem.setTaxStatus("N");
				} else {
					orderItem.setTaxStatus(searchItemDTO.getTaxStatus());
				}
				
				orderItem.setShortDesc(searchItemDTO.getDescription());
				orderItem.setLongDesc(searchItemDTO.getFullDesc());
				orderItem.setSellingNote(searchItemDTO.getSellingNote());
				orderItem.setStorageId(defalutStorageId);
				ItemOtherInfoForNewDTO itemOtherInfo = orderService.getItemOtherInfoForNew(orderItem.getQuantity(), 
						orderItem.getCatalogNo(), QuoteItemType.fromValue(orderItem.getType()), customerCompany);
				orderItem.setOtherInfo(itemOtherInfo.getOtherInfo());
				String preParentId = orderItem.getParentId();
				if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(orderItem.getType())) {
					orderItem.setStatus(itemOtherInfo.getItemStatus());
					orderItem.setStatusText(itemOtherInfo.getItemStatusText());
					//只在production时付给数据库中取出的值
					orderItem.setShipSchedule(searchItemDTO.getScheduleShip());
				} else {
					orderItem.setStatus("CM");
					orderItem.setStatusText("Committed");
					this.setServiceForItem(orderItem, MoreDetailUtil.getServiceNameByClsId(orderItem.getClsId()));
					if(orderItem!=null&&orderItem.getParentId()!=null&&StringUtils.isNotEmpty(orderItem.getParentId())) {
						OrderItemDTO parentItem = null;
						if(StringUtil.isNumeric(orderItem.getParentId())) {
							parentItem = this.orderItemService.findById(Integer.parseInt(orderItem.getParentId()));
						} else {
							parentItem = (OrderItemDTO)itemMap.get(orderItem.getParentId());
						}
						if(parentItem!=null) {
							String parentType = MoreDetailUtil.getServiceNameByClsId(parentItem.getClsId());
							if("geneSynthesis".equals(parentType)) {
								orderItem.setSize(new Double(4));
								orderItem.setSizeUom("ug");
							}
						}
						preParentId = orderItemService.getPreParentServiceId(itemMap, orderItem);
						orderItem.setPreParentId(preParentId);
					}
				}
				orderItem.setShipMethod(defalutShipMethod);
				orderItem.setShipToAddress(defaultAddress);
				orderItem.setNameShow(orderItem.getName());
				if (!StringUtils.isEmpty(order.getOrderCurrency())) {
					orderItem.setCurrencyCode(order.getOrderCurrency());
					orderItem = dozer.map(orderItem, OrderItemDTO.class);
					Double rate = publicService.getRateByCurrencyToBaseCurrency(order.getOrderCurrency(), order.getExchRateDate());
					if (rate != null && orderItem.getUnitPrice() != null) {
						int small = CurrencyType.JPY.value().equalsIgnoreCase(order.getOrderCurrency())?0:2;
						orderItem.setBasePrice(orderItem.getUnitPrice().multiply(new BigDecimal(rate)).setScale(small, BigDecimal.ROUND_HALF_UP));
					}
				}
				orderItem.setAmount(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity())));
				//
				this.insertNewChild(itemMap, preParentId, itemId, orderItem);
				//如果添加的Service的Quantity大于1，例如Gene下添加Muta
//				if (QuoteItemType.SERVICE.value().equalsIgnoreCase(orderItem.getType()) && orderItem.getQuantity() >1) {
//					String morePreParentId = null;
//					for (int qtyNum=1;qtyNum<orderItem.getQuantity();qtyNum++) {
//						if (morePreParentId == null) {
//							morePreParentId = itemId;
//						}
//						String moreItemId = SessionUtil.generateTempId();
//						OrderItemDTO moreOrderItem = dozer.map(orderItem, OrderItemDTO.class);
//						moreOrderItem.setItemNo(null);
//						moreOrderItem.setQuantity(1);
//						itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
//						this.insertNewChild(itemMap, morePreParentId, moreItemId, moreOrderItem);
//						morePreParentId = moreItemId;
//					}
//				}
				if (QuoteItemType.SERVICE.value().equals(orderItem.getType()) && StringUtils.isEmpty(orderItem.getParentId()) 
						&& (orderItem.getClsId() == 11 || orderItem.getClsId() == 12 || orderItem.getClsId() == 28)) {
					//如果是单抗和多抗就需要自动添加其子服务。
					this.getNewChildSerItem(itemId, orderItem, customer);
				}
			}

			if (StringUtils.isNotEmpty(srcQuOrderNo)) {
				Map<String, OrderItem> itemNoMap = new HashMap<String, OrderItem>();
				Map<String, String> itemIdMap = new HashMap<String, String>();
				Integer srcQuoteNo = Integer.parseInt(srcQuOrderNo);
				List<OrderItem> srcOrderItemList = orderService.getOrderAllItemList(srcQuoteNo);
				for (OrderItem orderItem : srcOrderItemList) {
					itemNoMap.put(orderItem.getItemNo().toString(), orderItem);
					itemIdMap.put(orderItem.getOrderItemId().toString(),orderItem.getItemNo().toString());
				}
				Iterator<Entry<String, OrderItemDTO>> it = tempItemMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, OrderItemDTO> entry = it.next();
					OrderItemDTO tmpItemDTO = entry.getValue();
//                    tmpItemDTO.setUpdateFlag("Y");
					String srcOrderItemNo = tmpItemDTO.getSrcItemNo();
					if (StringUtils.isNotEmpty(srcOrderItemNo)) {
						OrderItem srcOrderItem = itemNoMap.get(srcOrderItemNo);
						Integer parentId = srcOrderItem.getParentId();
						if (parentId != null) {
							String parentItemNoStr = itemIdMap.get(parentId.toString());
							String parentItemId = toItemNoIdMap.get(parentItemNoStr);
							if (StringUtils.isNotEmpty(parentItemId)) {
								OrderItemDTO parentItem = tempItemMap.get(parentItemId);
								if (parentItem.getClsId() == 3 && tmpItemDTO.getClsId() == 9) {
									parentItem.getGeneSynthesis().setCloningFlag("Y");
								}
								if (parentItem.getClsId() == 3 && tmpItemDTO.getClsId() == 10) {
									parentItem.getGeneSynthesis().setPlasmidPrepFlag("Y");
								}
								if (parentItem.getClsId() == 3 && tmpItemDTO.getClsId() == 4) {
									parentItem.getGeneSynthesis().setMutagenesisFlag("Y");
								}
								if (parentItem.getClsId() == 4 && tmpItemDTO.getClsId() == 9) {
									parentItem.getMutagenesis().setCloningFlag("Y");
								}
								if (parentItem.getClsId() == 4 && tmpItemDTO.getClsId() == 10) {
									parentItem.getMutagenesis().setPlasmidPrepFlag("Y");
								}
								if (parentItem.getClsId() == 11 && tmpItemDTO.getClsId() == 1) {
									PeptideDTO peptide = new PeptideDTO();
									tmpItemDTO.setPeptide(peptide);
									if (parentItem.getSubItemList() != null && parentItem.getSubItemList().size() > 0) {
										parentItem.getSubItemList().add(tmpItemDTO);
									} else {
										parentItem.setSubItemList(new ArrayList<OrderItemDTO>());
									}
								}
								if ((parentItem.getClsId() == 8 && tmpItemDTO.getClsId() == 10) 
										|| (parentItem.getClsId() == 7 && tmpItemDTO.getClsId() == 10)) {
									parentItem.getRna().setPlasmidPrepFlag("Y");
								}
								tmpItemDTO.setParentId(parentItemId);
								// toItemNoIdMap.remove(parentItemNoStr);
							}
						}
					}
				}
				tempItemMap.clear();
			}
			this.updateItemDiscount(itemMap);
			OrderSessionUtil.removeAllPackages(orderService, sessOrderNo);
		}
		return NONE;
	}

	/**
	 * 在session中增加新的item
	 * 
	 * @param itemMap
	 * @param afterItemId
	 * @param newItemId
	 * @param newItem
	 */
	private void insertNewChild(Map<String, OrderItemDTO> itemMap,
			String afterItemId, String newItemId, OrderItemDTO newItem) {
		if (StringUtils.isBlank(afterItemId)) {
			Integer itemNo = itemMap.size() + 1;
			newItem.setItemNo(itemNo);
			itemMap.put(newItemId, newItem);
		} else {
			Map<String, OrderItemDTO> newItemMap = new LinkedHashMap<String, OrderItemDTO>();
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			OrderItemDTO afterItem = itemMap.get(afterItemId);
			Integer itemNo = afterItem.getItemNo();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				String tmpKey = entry.getKey();
				OrderItemDTO tmpItemDTO = entry.getValue();
				Integer tmpItemNo = tmpItemDTO.getItemNo();
				if (tmpItemNo.intValue() == itemNo.intValue()) {
					newItemMap.put(tmpKey, tmpItemDTO);
					newItem.setItemNo(tmpItemNo.intValue() + 1);
//                    newItem.setUpdateFlag("Y");
					newItemMap.put(newItemId, newItem);
				} else {
					if (tmpItemNo.intValue() > itemNo.intValue()) {
						tmpItemDTO.setItemNo(tmpItemNo.intValue() + 1);
					}
//                    tmpItemDTO.setUpdateFlag("Y");
					newItemMap.put(tmpKey, tmpItemDTO);
				}
			}
			SessionUtil.updateRow(SessionConstant.OrderItemList.value(), sessOrderNo, newItemMap);
		}
	}
	
	/**
	 * 获得新的子item
	 * @author Zhang Yong
	 * @param parentId
	 * @param parentItem
	 * @param customer
	 */
	private void getNewChildSerItem(String parentId, OrderItemDTO parentItem, CustomerDTO customer) {
		List<ServiceIntermediate> imdList = servService.getIntermediate(parentItem.getCatalogNo(), "Y");
		if (imdList == null) {
			return;
		}
		for (ServiceIntermediate imd : imdList) {
			String intmdCalogNo = imd.getIntmdCatalogNo();
			OrderItemDTO itemInfo = this.getBaseInfoByCatalogNo(intmdCalogNo, customer.getCustNo());
			if (itemInfo == null) {
				continue;
			}
			itemInfo.setParentId(parentId);
			itemInfo.setShipToAddress(parentItem.getShipToAddress());
			itemInfo.setShiptoAddrId(parentItem.getShiptoAddrId());
			itemInfo.setShipMethod(parentItem.getShipMethod());
			itemInfo.setUpSymbol(parentItem.getUpSymbol());
			itemInfo.setStatus("CM");
			itemInfo.setStatusText("Committed");
			itemInfo.setOtherInfo("");
			itemInfo.setCatalogId(parentItem.getCatalogId());
			itemInfo.setCatalogText(parentItem.getCatalogText());
//            itemInfo.setUpdateFlag("Y");
			String subItemId;
			try {
				subItemId = SessionUtil.generateTempId();
				this.insertNewChild(itemMap, null, subItemId, itemInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private OrderItemDTO getBaseInfoByCatalogNo(String catalogNo, Integer custNo) {
		try{
			SearchItemDTO searchItemDTO = servService.getSearchItemInfo(catalogNo, custNo);
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setName(searchItemDTO.getName());
			orderItemDTO.setNameShow(searchItemDTO.getName());
			orderItemDTO.setCatalogNo(searchItemDTO.getCatalogNo());
			orderItemDTO.setClsId(searchItemDTO.getClsId());
			if(searchItemDTO.getCost() == null){
				orderItemDTO.setCost(new BigDecimal(0.0));
			}else{
				orderItemDTO.setCost(new BigDecimal(searchItemDTO.getCost()));
			}
			orderItemDTO.setType("SERVICE");
			orderItemDTO.setTypeText("SERVICE"+"-" + searchItemDTO.getClsName());
			orderItemDTO.setShortDesc(searchItemDTO.getDescription());
			orderItemDTO.setLongDesc(searchItemDTO.getFullDesc());
			orderItemDTO.setTaxStatus(searchItemDTO.getTaxStatus());
			orderItemDTO.setShipSchedule(searchItemDTO.getScheduleShip());
			orderItemDTO.setSellingNote(searchItemDTO.getSellingNote());
			orderItemDTO.setStorageId(searchItemDTO.getPrefStorage());
			orderItemDTO.setSizeUom(searchItemDTO.getUom());
			orderItemDTO.setSize(searchItemDTO.getSize());
			orderItemDTO.setQtyUom(searchItemDTO.getQtyUom());
			//quoteItemDTO.setCatalogId(searchItemDTO.getCatalogId());
			orderItemDTO.setQuantity(1);//hard code
			orderItemDTO.setAmount(new BigDecimal(0.0));//hard code
			orderItemDTO.setDiscount(new BigDecimal(0.0));//hard code
			orderItemDTO.setTax(new BigDecimal(0.0));//hard code
			orderItemDTO.setUnitPrice(new BigDecimal(0.0));//hard code
			orderItemDTO.setBasePrice(BigDecimal.valueOf(0.0));
			orderItemDTO.setCost(new BigDecimal(0.0));
//            orderItemDTO.setUpdateFlag("Y");
			return orderItemDTO;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 初始化OrderItem的Service
	 * 
	 * @param orderItem
	 * @param serviceName
	 */
	private void setServiceForItem(OrderItemDTO orderItem, String serviceName) {
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			orderItem.setGeneSynthesis(new OrderGeneSynthesisDTO());
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			orderItem.setMutagenesis(new OrderMutagenesisDTO());
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			orderItem.setCustCloning(new OrderCustCloningDTO());
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			orderItem.setPlasmidPreparation(new OrderPlasmidPreparationDTO());
		} else if (serviceName.equalsIgnoreCase("pkgService")) {
			orderItem.setOrderPkgService(new PkgServiceDTO());
		} else if (serviceName.equals("antibody")) {
			orderItem.setAntibody(new AntibodyDTO());
		} else if (serviceName.equals("rna")) {
			orderItem.setRna(new RnaDTO());
		} else if (serviceName.equals("orfClone")) {
			orderItem.setOrfClone(new OrderOrfCloneDTO());
		} else if (serviceName.equals("oligo")) {
			orderItem.setOligo(new OrderOligoDTO());
		} else if (serviceName.equals("customService")) {
			orderItem.setCustomService(new CustomServiceDTO());
		} else if (serviceName.equals("sequencing")) {
			orderItem.setDnaSequencing(new DnaSequencingDTO());
		} else if (serviceName.equals("mutaLib")) {
			orderItem.setMutationLibraries(new OrderMutationLibrariesDTO());
		}
	}

	/**
	 * 获得默认shipmethod
	 * 
	 * @return
	 */
	private Integer getDefaultShipMethod() {
		Integer id = null;
		if (itemMap != null && !itemMap.isEmpty()) {
			Iterator<Entry<String, OrderItemDTO>> item = itemMap.entrySet().iterator();
			while (item.hasNext()) {
				Entry<String, OrderItemDTO> entry = item.next();
				OrderItemDTO orderItemEntry = entry.getValue();
				if (orderItemEntry != null && !OrderItemStatusType.CN.value().equals(orderItemEntry.getStatus()) 
						&& orderItemEntry.getShipMethod() != null) {
					id = orderItemEntry.getShipMethod();
					break;
				}
			}
		}
		if (id == null && order != null && order.getCustNo() != null) {
			CustomerDTO cust = customerService.getCustomerBase(order.getCustNo());
			if (cust != null) {
				id = cust.getPrefShipMthd();
			}
		}
		if (id == null) {
			List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
			speclListName.add(SpecDropDownListName.SHIP_METHOD);
			Map<SpecDropDownListName, DropDownListDTO> tmpMap = publicService
					.getSpecDropDownMap(speclListName);
			id = Integer.parseInt(tmpMap.get(
					SpecDropDownListName.SHIP_METHOD).getDropDownDTOs().get(0)
					.getId());
		}
		return id;
	}

	/**
	 * 获得默认StorageId
	 * 
	 * @return
	 */
	private Integer getDefaultStorageId(Integer warehouseId) {
		if (warehouseId == null) {
			return null;
		}
		pickLocationList = publicService.getPickLocationList(warehouseId);
		if (pickLocationList != null && !pickLocationList.isEmpty()) {
			String idStr = pickLocationList.get(0).getId();
			return Integer.parseInt(idStr);
		}
		return null;
	}
	
	/**
	 * 对于特殊的服务有Copy功能
	 * @author Zhang Yong
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String copyItem () throws Exception {
		Map<String, OrderItemDTO> retMap = new LinkedHashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (StringUtils.isBlank(itemId) || itemMap == null || itemMap.isEmpty()) {
			return null;
		}
		OrderItemDTO moduleItem = itemMap.get(itemId);
		if (moduleItem == null || StringUtils.isNotBlank(moduleItem.getParentId())) {
			return null;
		}
		Map<String, OrderItemDTO> sameGroupItemMap = getGroupItemMap(itemId, null);
		if (sameGroupItemMap == null || sameGroupItemMap.isEmpty()) {
			return null;
		}
		String parentId = null;
		int itemNum = moduleItem.getItemNo();
		for (int i=0;i<sameGroupItemMap.size();i++) {
			Iterator<Entry<String, OrderItemDTO>> iterator = sameGroupItemMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, OrderItemDTO> entry = iterator.next();
				OrderItemDTO sourceItemDTO = entry.getValue();
				OrderItemDTO itemDTO = dozer.map(sourceItemDTO, OrderItemDTO.class);
				if (itemNum == itemDTO.getItemNo().intValue()) {
					itemDTO.setOrderItemId(null);
					itemDTO.setItemNo(null);
					String newItemId = SessionUtil.generateTempId();
					if (StringUtils.isBlank(itemDTO.getParentId())) {
						parentId = newItemId;
					} else {
						itemDTO.setParentId(parentId);
					}
					itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
					this.insertNewChild(itemMap, null, newItemId, itemDTO);
					itemNum = sourceItemDTO.getItemNo() + 1;
					break;
				}
			}
		}
		retMap.put(itemId, moduleItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	public String batchGenOrder() {
		List<String> geneNameList = new ArrayList<String>();
		List<String> sequenceList = new ArrayList<String>();
		if (StringUtils.isNotEmpty(sequence)) {
			String[] geneItemArray = sequence.split(">");
			for (String str : geneItemArray) {
				if (StringUtils.isNotEmpty(str)) {
					String[] array = str.split("\n", 2);
					if (array.length == 2) {
						geneNameList.add(array[0]);
						sequenceList.add(array[1]);
					}
				}
			}
		}
		return NONE;
	}

	public OrderItemDTO getModel() {
		return orderItem;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public OrderItemDTO getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItemDTO orderItem) {
		this.orderItem = orderItem;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getDelItemIdStr() {
		return delItemIdStr;
	}

	public void setDelItemIdStr(String delItemIdStr) {
		this.delItemIdStr = delItemIdStr;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public Map<String, OrderItemDTO> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, OrderItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public OrderMainDTO getOrder() {
		return order;
	}

	public void setOrder(OrderMainDTO order) {
		this.order = order;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPrmtCode() {
		return prmtCode;
	}

	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}

	public void setItemStatusLoglist(List<ProcessLogDTO> itemStatusLoglist) {
		this.itemStatusLoglist = itemStatusLoglist;
	}

	public List<ProcessLogDTO> getItemStatusLoglist() {
		return itemStatusLoglist;
	}

	public Map<String, String> getLocationMap() {
		return locationMap;
	}

	public void setLocationMap(Map<String, String> locationMap) {
		this.locationMap = locationMap;
	}

	public Map<Integer, String> getShipMethodMap() {
		return shipMethodMap;
	}

	public void setShipMethodMap(Map<Integer, String> shipMethodMap) {
		this.shipMethodMap = shipMethodMap;
	}

	public String getItemIdFrom() {
		return itemIdFrom;
	}

	public void setItemIdFrom(String itemIdFrom) {
		this.itemIdFrom = itemIdFrom;
	}

	public String getItemIdTo() {
		return itemIdTo;
	}

	public void setItemIdTo(String itemIdTo) {
		this.itemIdTo = itemIdTo;
	}

	/*
	 * public int getTriggerLast() { return triggerLast; }
	 * 
	 * public void setTriggerLast(int triggerLast) { this.triggerLast =
	 * triggerLast; }
	 */

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public List<DropDownDTO> getPickLocationList() {
		return pickLocationList;
	}

	public void setPickLocationList(List<DropDownDTO> pickLocationList) {
		this.pickLocationList = pickLocationList;
	}

	public int getPurchaseOrderFlag() {
		return purchaseOrderFlag;
	}

	public void setPurchaseOrderFlag(int purchaseOrderFlag) {
		this.purchaseOrderFlag = purchaseOrderFlag;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public OrderGeneSynthesisDTO getGeneSynthesis() {
		return geneSynthesis;
	}

	public void setGeneSynthesis(OrderGeneSynthesisDTO geneSynthesis) {
		this.geneSynthesis = geneSynthesis;
	}

	public OrderPlasmidPreparationDTO getPlasmidPreparation() {
		return plasmidPreparation;
	}

	public void setPlasmidPreparation(
			OrderPlasmidPreparationDTO plasmidPreparation) {
		this.plasmidPreparation = plasmidPreparation;
	}

	public OrderCustCloningDTO getCustCloning() {
		return custCloning;
	}

	public void setCustCloning(OrderCustCloningDTO custCloning) {
		this.custCloning = custCloning;
	}

	public List<SalesRep> getProjectManagerList() {
		return projectManagerList;
	}

	public void setProjectManagerList(List<SalesRep> projectManagerList) {
		this.projectManagerList = projectManagerList;
	}

	public List<SalesRep> getAltProjectManagerList() {
		return altProjectManagerList;
	}

	public void setAltProjectManagerList(List<SalesRep> altProjectManagerList) {
		this.altProjectManagerList = altProjectManagerList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getShippingRoute() {
		return shippingRoute;
	}

	public void setShippingRoute(String shippingRoute) {
		this.shippingRoute = shippingRoute;
	}

	public List<ShipPackage> getShipPackageList() {
		return shipPackageList;
	}

	public void setShipPackageList(List<ShipPackage> shipPackageList) {
		this.shipPackageList = shipPackageList;
	}

	/**
	 * @return the dbStatus
	 */
	public String getDbStatus() {
		return dbStatus;
	}

	/**
	 * @param dbStatus the dbStatus to set
	 */
	public void setDbStatus(String dbStatus) {
		this.dbStatus = dbStatus;
	}

    public Map<String, OrderItemDTO> getItemListResultMap() {
        return itemListResultMap;
    }

    public void setItemListResultMap(Map<String, OrderItemDTO> itemListResultMap) {
        this.itemListResultMap = itemListResultMap;
    }

    public List<PbDropdownListOptions> getUpdateReasonSel() {
        return updateReasonSel;
    }

    public void setUpdateReasonSel(List<PbDropdownListOptions> updateReasonSel) {
        this.updateReasonSel = updateReasonSel;
    }
}
