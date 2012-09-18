package com.genscript.gsscm.order.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.OrderSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.dto.OrderAddressDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.entity.OrderAddress;

/**
 * 
 * 处理order package 相关方法
 * 
 * @author zouyulu
 * 
 */
@Results( {
		@Result(name = "package_list", location = "order/order_package_list.jsp"),
		@Result(name = "package_edit", location = "order/order_package_edit.jsp") })
public class OrderPackageAction extends BaseAction<OrderPackageDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4809263487518529221L;
	@Autowired
	private PublicService publicService;
	private String sessOrderNo;
	private List<String> itemIdList;
	private Map<String, OrderPackageDTO> packageMap;
	private String sessPackageId;
	private OrderPackageDTO shipPackage;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private String quorderStatus;
	private String symbol;
	/**
	 * jquery ajax form 提交方法下length是关键字，有冲突，故特别处理
	 */
	private Double length1;
	private List<String> packageIdList;
	private String addressStr;

	/**
	 * 删除package
	 */
	@Override
	public String delete() throws Exception {
		if (packageIdList != null && !packageIdList.isEmpty()) {
			for (int i = 0; i < packageIdList.size(); i++) {
				SessionUtil.deleteOneRow(SessionConstant.OrderPackage.value(),
						sessOrderNo, packageIdList.get(i));
			}
		}
		OrderSessionUtil.deleteTotal(sessOrderNo);
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	/**
	 * 显示编辑页面
	 */
	@Override
	public String input() throws Exception {
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		this.addressStr = shipPackage.getShiptoAddress();
		return "package_edit";
	}

	/**
	 * package 列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		if (!StringUtils.isNumeric(sessOrderNo)) {
			return "package_list";
		}
		packageMap = (Map<String, OrderPackageDTO>) SessionUtil
				.getRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		if (packageMap == null || packageMap.isEmpty()) {
			return "package_list";
		}
		Iterator<Entry<String, OrderPackageDTO>> opkgpm = packageMap.entrySet().iterator();
		int i=0;
		while (opkgpm.hasNext()) {
			Entry<String, OrderPackageDTO> entry = opkgpm.next();
			OrderPackageDTO op = entry.getValue();
			op.setPkgBatchSeq(i+1);
			op.setPkgBatchCount(packageMap.size());
			i++;
		}
		return "package_list";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(sessPackageId)) {
			packageMap = (Map<String, OrderPackageDTO>) SessionUtil.getRow(
					SessionConstant.OrderPackage.value(), sessOrderNo);
			if (packageMap != null) {
				shipPackage = packageMap.get(sessPackageId);
			}
			if (shipPackage == null) {
				return;
			}
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
					SessionConstant.Order.value(), sessOrderNo);
			if(order != null){
				quorderStatus = order.getStatus();
				symbol = !StringUtils.isEmpty(order.getOrderCurrency())?
						publicService.searchSymbolByCode(order.getOrderCurrency()):"";
			}
			OrderAddressDTO orderAddressDTO = order.getOrderAddrList();
			OrderAddress orderAddress = orderAddressDTO.getShipToAddr();
			if (orderAddress != null) {
				shipPackage.setDeliveryType(orderAddress.getAddrClass());
			}
		}
	}

	public OrderPackageDTO getModel() {
		return shipPackage;
	}

	/**
	 * 保存package
	 */
	@Override
	public String save() throws Exception {
		shipPackage.setLength(length1);
		// checkbox
		if (!shipPackage.getAdditionalHandle().equalsIgnoreCase("Y")) {
			shipPackage.setAdditionalHandle("N");
		}
		if (!shipPackage.getDeliveryConfirm().equalsIgnoreCase("Y")) {
			shipPackage.setDeliveryConfirm("N");
		}
		if (!shipPackage.getNoteOnShip().equalsIgnoreCase("Y")) {
			shipPackage.setNoteOnShip("N");
		}
		if (!shipPackage.getNoteOnExcp().equalsIgnoreCase("Y")) {
			shipPackage.setNoteOnExcp("N");
		}
		if (!shipPackage.getNoteOnDelivery().equalsIgnoreCase("Y")) {
			shipPackage.setNoteOnDelivery("N");
		}
		if (!shipPackage.getHazardousMtl().equalsIgnoreCase("Y")) {
			shipPackage.setHazardousMtl("N");
		}
		if (!shipPackage.getSaturdayPickup().equalsIgnoreCase("Y")) {
			shipPackage.setSaturdayPickup("N");
		}
		SessionUtil.updateOneRow(SessionConstant.OrderPackage.value(),
				sessOrderNo, sessPackageId, shipPackage);
		OrderSessionUtil.deleteTotal(sessOrderNo);
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public List<String> getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(List<String> itemIdList) {
		this.itemIdList = itemIdList;
	}

	public Map<String, OrderPackageDTO> getPackageMap() {
		return packageMap;
	}

	public void setPackageMap(Map<String, OrderPackageDTO> packageMap) {
		this.packageMap = packageMap;
	}

	public OrderPackageDTO getShipPackage() {
		return shipPackage;
	}

	public void setShipPackage(OrderPackageDTO shipPackage) {
		this.shipPackage = shipPackage;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public Double getLength1() {
		return length1;
	}

	public void setLength1(Double length1) {
		this.length1 = length1;
	}

	public List<String> getPackageIdList() {
		return packageIdList;
	}

	public void setPackageIdList(List<String> packageIdList) {
		this.packageIdList = packageIdList;
	}

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}

	public String getQuorderStatus() {
		return quorderStatus;
	}

	public void setQuorderStatus(String quorderStatus) {
		this.quorderStatus = quorderStatus;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSessPackageId() {
		return sessPackageId;
	}

	public void setSessPackageId(String sessPackageId) {
		this.sessPackageId = sessPackageId;
	}

}
