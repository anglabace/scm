package com.genscript.gsscm.order.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderReturnDTO;
import com.genscript.gsscm.order.dto.OrderReturnItemDTO;
import com.genscript.gsscm.order.entity.OrderReturn;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.ws.WSException;

/**
 * order return 相关
 * 
 * @author zouyulu
 * 
 */
@Results( { @Result(name = "return_list", location = "order/order_return_list.jsp") })
public class OrderReturnAction extends BaseAction<OrderReturnDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429814658530692688L;
	private static final String SUCCESS = "SUCCESS";
	private static final String ERROR = "ERROR";
	@Autowired
	private OrderService orderService;
	@Autowired
	private ExceptionService exceptionUtil;

	private String sessOrderNo;
	private Integer rmaNo;
	private List<OrderReturnDTO> returnList;
	private OrderReturnDTO orderReturn;
	private Map<String, OrderReturnItemDTO> orderReturnItemMap;
	private Date today;
	private String userName;
	// 保存orderReturn
	private String saveType;
	private Date exprDate;
	private BigDecimal shipRefund;
	private BigDecimal totalRefund;
	private String note;
	private List<String> itemIdList;
	// 保存return Item
	private String itemId;
	private Integer returnQty;
	private BigDecimal returnSize;
	private BigDecimal refund;
	private String returnReason;
	private String exchangeFlag;
	private Integer itemNo;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 显示管理order return页面
	 */
	@Override
	public String list() throws Exception {
		this.today = new Date();
		this.userName = SessionUtil.getUserName();
		if (!StringUtils.isNumeric(sessOrderNo)) {
			return "return_list";
		}
		returnList = orderService.getUnProcessedReturnList(Integer
				.parseInt(sessOrderNo));
		// 没有returnList或者选择值为 -1（new）
		if (returnList == null || returnList.size() == 0
				|| (this.rmaNo != null && this.rmaNo.intValue() == -1)) {
			Date expirationDate = DateUtils.dayBefore2Date(new Date(), 14);
			this.orderReturn = new OrderReturnDTO();
			this.orderReturn.setExprDate(expirationDate);
			this.orderReturn.setOrderNo(Integer.parseInt(sessOrderNo));
			this.orderReturn.setStatus("UNPROCESSED");
			this.orderReturn.setCreateUser(this.userName);
			this.orderReturn.setCreationDate(this.today);
			this.orderReturn.setProcessDate(this.today);
			this.orderReturn.setProcessUser(this.userName);
			this.orderReturn.setTotalRefund(BigDecimal.valueOf(0));
			List<OrderItemDTO> dtoList = this.orderService
					.getOrderUnRTItemList(Integer.parseInt(sessOrderNo));
			if (dtoList != null && dtoList.size() > 0) {
				orderReturnItemMap = new LinkedHashMap<String, OrderReturnItemDTO>();
				for (int i = 0; i < dtoList.size(); i++) {
					OrderItemDTO orderItemDTO = dtoList.get(i);
					OrderReturnItemDTO orderReturnItem = new OrderReturnItemDTO();
					String tmpId = SessionUtil.generateTempId();
					orderReturnItem.setItemNo(orderItemDTO.getItemNo());
					orderReturnItem.setCatalogNo(orderItemDTO.getCatalogNo());
					orderReturnItem.setName(orderItemDTO.getName());
					orderReturnItem.setTax(orderItemDTO
							.getTax());
					orderReturnItem.setUnitPrice(orderItemDTO.getUnitPrice());
					orderReturnItem.setDiscount(orderItemDTO
							.getDiscount());
					orderReturnItem.setShipUom(orderItemDTO.getShipUom());
					orderReturnItem.setShipSize(orderItemDTO.getShipSize());
					orderReturnItem.setShippedQty(orderItemDTO.getShippedQty());
					orderReturnItem.setReturnQty(orderItemDTO.getShippedQty());
					orderReturnItem.setReturnSize(orderItemDTO.getShipSize());
					orderReturnItem.setExchangeFlag("Y");
					orderReturnItem.setRefund(BigDecimal.valueOf(0));
					orderReturnItemMap.put(tmpId, orderReturnItem);
				}
				SessionUtil.insertRow(SessionConstant.OrderReturnItem.value(),
						sessOrderNo, orderReturnItemMap);
			}
		} else {
			for (OrderReturnDTO orderReturnDTO : returnList) {
				if (this.rmaNo != null
						&& orderReturnDTO.getRmaNo().intValue() == this.rmaNo
								.intValue()) {
					this.orderReturn = orderReturnDTO;
				}
			}
			// 如果没有指定rmaNo，则默认取最后一个orderReturn
			if (this.rmaNo == null) {
				this.orderReturn = returnList.get(returnList.size() - 1);
			}
			if (this.orderReturn.getReturnItemList() != null
					&& this.orderReturn.getReturnItemList().size() > 0) {
				orderReturnItemMap = SessionUtil.convertList2Map(
						this.orderReturn.getReturnItemList(), "itemId");
				SessionUtil.insertRow(SessionConstant.OrderReturnItem.value(),
						sessOrderNo, orderReturnItemMap);
			}
		}
		return "return_list";
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public String save() throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 新增
		if (saveType.equalsIgnoreCase("modify")) {
			this.today = new Date();
			this.userName = SessionUtil.getUserName();
			if (this.rmaNo != null && this.rmaNo.intValue() != -1) {
				this.orderReturn = orderService.getUnProcessedReturn(rmaNo);
			} else {
				this.orderReturn = new OrderReturnDTO();
			}
			this.orderReturn.setExprDate(exprDate);
			this.orderReturn.setShipRefund(shipRefund);
			this.orderReturn.setTotalRefund(totalRefund);
			this.orderReturn.setNote(note);
			this.orderReturn.setOrderNo(Integer.parseInt(sessOrderNo));
			this.orderReturn.setStatus("UNPROCESSED");
			this.orderReturn.setCreateUser(this.userName);
			this.orderReturn.setCreationDate(this.today);
			this.orderReturn.setProcessDate(this.today);
			this.orderReturn.setProcessUser(this.userName);
			this.orderReturn.setProcessedBy(SessionUtil.getUserId());
			List<OrderReturnItemDTO> itemList = this
					.getReturnItemListById(itemIdList);
			this.orderReturn.setReturnItemList(itemList);
		} else if (saveType.equalsIgnoreCase("del")) {
			// 删除已存在的order return
			this.orderReturn = orderService.getUnProcessedReturn(rmaNo);
			this.orderReturn.setStatus("INVALID");
		} else if (saveType.equalsIgnoreCase("process")) {
			this.orderReturn = orderService.getUnProcessedReturn(rmaNo);
			this.orderReturn.setStatus("PROCESSED");
			this.orderReturn.setProcessDate(this.today);
			this.orderReturn.setProcessedBy(SessionUtil.getUserId());
		}

		// 处理过的数据存入数据库
		try {
			if (saveType.equalsIgnoreCase("process")) {
				OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
						SessionConstant.Order.value(), sessOrderNo);
				Integer warehouseId = order.getWarehouseId();
				orderService.processOrderReturn(orderReturn, warehouseId,
						SessionUtil.getUserId());
			} else {
				OrderReturn oReturn = orderService.saveOrderReturn(orderReturn,
						SessionUtil.getUserId());
				retMap.put("rmaNo", oReturn.getRmaNo());
			}
			retMap.put("status", SUCCESS);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			retMap.put("status", ERROR);
			retMap.put("msg", exDTO.getMessageDetail() + "\n"
					+ exDTO.getAction());
		}
		Struts2Util.renderJson(retMap);
		return null;
	}

	public String updateReturnItem() throws Exception {
		OrderReturnItemDTO orderReturnItemDTO = (OrderReturnItemDTO) SessionUtil
				.getOneRow(SessionConstant.OrderReturnItem.value(),
						sessOrderNo, itemId);
		orderReturnItemDTO.setReturnQty(returnQty);
		orderReturnItemDTO.setReturnSize(returnSize);
		orderReturnItemDTO.setRefund(refund);
		orderReturnItemDTO.setReturnReason(returnReason);
		orderReturnItemDTO.setExchangeFlag(exchangeFlag);
		Struts2Util.renderText(SUCCESS);
		return null;
	}

	// 计算价格
	public String calReturnRefund() throws Exception {
		Float retVal = orderService.calculateReturnRefund(returnQty, returnSize
				.doubleValue(), Integer.parseInt(sessOrderNo), itemNo, 2);
		Struts2Util.renderText(retVal.toString());
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<OrderReturnItemDTO> getReturnItemListById(List<String> idList) {
		List<OrderReturnItemDTO> retList = new ArrayList<OrderReturnItemDTO>();
		orderReturnItemMap = (Map<String, OrderReturnItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderReturnItem.value(), sessOrderNo);
		if (orderReturnItemMap != null && idList != null) {
			for (int i = 0; i < idList.size(); i++) {
				if (orderReturnItemMap.containsKey(idList.get(i))) {
					retList.add(orderReturnItemMap.get(idList.get(i)));
				}
			}
		}
		return retList;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public Integer getRmaNo() {
		return rmaNo;
	}

	public void setRmaNo(Integer rmaNo) {
		this.rmaNo = rmaNo;
	}

	public OrderReturnDTO getOrderReturn() {
		return orderReturn;
	}

	public void setOrderReturn(OrderReturnDTO orderReturn) {
		this.orderReturn = orderReturn;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, OrderReturnItemDTO> getOrderReturnItemMap() {
		return orderReturnItemMap;
	}

	public void setOrderReturnItemMap(
			Map<String, OrderReturnItemDTO> orderReturnItemMap) {
		this.orderReturnItemMap = orderReturnItemMap;
	}

	public Date getExprDate() {
		return exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public BigDecimal getShipRefund() {
		return shipRefund;
	}

	public void setShipRefund(BigDecimal shipRefund) {
		this.shipRefund = shipRefund;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<String> getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(List<String> itemIdList) {
		this.itemIdList = itemIdList;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public BigDecimal getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(BigDecimal totalRefund) {
		this.totalRefund = totalRefund;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<OrderReturnDTO> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<OrderReturnDTO> returnList) {
		this.returnList = returnList;
	}

	public Integer getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(Integer returnQty) {
		this.returnQty = returnQty;
	}

	public BigDecimal getReturnSize() {
		return returnSize;
	}

	public void setReturnSize(BigDecimal returnSize) {
		this.returnSize = returnSize;
	}

	public BigDecimal getRefund() {
		return refund;
	}

	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getExchangeFlag() {
		return exchangeFlag;
	}

	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

}
