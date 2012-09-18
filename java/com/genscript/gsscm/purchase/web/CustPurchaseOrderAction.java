package com.genscript.gsscm.purchase.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.genscript.gsscm.ws.WSException;

@Results( { @Result(name = "createPurchaseOrder", location = "purchase/create_purchase_order.jsp")
})
public class CustPurchaseOrderAction extends BaseAction<PurchaseOrderDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1846756223315833196L;
	private Integer id;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private PurchaseOrderDTO purchaseOrder;
	private String orderNoStr;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ExceptionService exceptionUtil;
	//获取从其他列表页面点过来的请求--Zhang Yong
	private String operation_method;
	
	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		//*********** Add By Zhang Yong Start *****************************//
		if (orderNoStr != null && !("").equals(orderNoStr)) {
			//*********** Add By Zhang Yong Start *****************************//
			//释放application中的订单锁
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock(); 
			//*********** Add By Zhang Yong End *****************************//
		} else {
			//判断将要修改的单号是否正在被操作
			String editUrl = "purchase/cust_purchase_order!input.action?orderNoStr="+orderNoStr;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			//*********** Add By Zhang Yong End *****************************//
		}
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.WAREHOUSE);
		speclListName.add(SpecDropDownListName.CURRENCY);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		if (StringUtils.isNotBlank(orderNoStr)) {
			String[] strs = orderNoStr.split(",");
			List<String> orderNoList = Arrays.asList(strs);
			Double cost = 0.0;
			for(String str : orderNoList){
				List<OrderItem> orderItemList = orderService.getOrderAllItemList(Integer.parseInt(str));
				if(orderItemList != null && orderItemList.size() > 0){
					for(OrderItem orderItem : orderItemList){
						cost += orderItem.getCost();
					}
				}
			}
			purchaseOrder.setSubTotal(cost);
		}
		return "createPurchaseOrder";
	}

	@Override
	public String save() throws Exception {
		System.out.println("==========="+purchaseOrder);
		System.out.println("="+orderNoStr);
		Map<String, Object> rt = new HashMap<String, Object>();
		//*********** Add By Zhang Yong Start *****************************//
		//校验当前对象是否正被其他人先编辑，有则不保存，跳转到编辑页面，防止用户通过URL方式保存订单
		if (orderNoStr != null && !("").equals(orderNoStr)) {
			String editUrl = "purchase/cust_purchase_order!input.action?orderNoStr="+orderNoStr;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
				rt.put("message", "Save purchase_order fail,the purchase_order is editing by "+operation_method);
				Struts2Util.renderJson(rt);
				return null;
			}
		}
		//*********** Add By Zhang Yong End *****************************//	
		List<Integer> orderNoList = new ArrayList<Integer>();
		try {
			if (StringUtils.isNotBlank(orderNoStr)) {
				String[] strs = orderNoStr.split(",");
				List<String> orderNoStrList = Arrays.asList(strs);
				for(String str : orderNoStrList){
					orderNoList.add(Integer.parseInt(str));
				}
			}
			purchaseService.savePurchaseOrder(purchaseOrder, orderNoList);
			rt.put("message", "Save purchase order sucessfully.");
		}catch (Exception ex) {
			WSException exDTO = exceptionUtil.getExceptionDetails(ex);
			exceptionUtil.logException(exDTO, this.getClass(), ex,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id == null){
			purchaseOrder = new PurchaseOrderDTO();
			purchaseOrder.setOrderedBy(SessionUtil.getUserName());
			purchaseOrder.setOrderDate(new Date());
		}else{
			PurchaseOrder pOrder = purchaseService.getPurchaseOrder(id);
			purchaseOrder = dozer.map(pOrder, PurchaseOrderDTO.class);
		}
		
	}

	@Override
	public PurchaseOrderDTO getModel() {
		return purchaseOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PurchaseOrderDTO getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public String getOrderNoStr() {
		return orderNoStr;
	}

	public void setOrderNoStr(String orderNoStr) {
		this.orderNoStr = orderNoStr;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operationMethod) {
		operation_method = operationMethod;
	}
}
