package com.genscript.gsscm.systemsetting.web;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.shipment.dto.ShipCarriersDTO;
import com.genscript.gsscm.shipment.entity.ShipCarriers;
import com.genscript.gsscm.shipment.service.ShipCarriersService;
import com.genscript.gsscm.ws.WSException;

@Results({
		@Result(name = "createForm", location = "systemsetting/ShippingCarries/shipcarriers_create_form.jsp"),
		@Result(name = "editForm", location = "systemsetting/ShippingCarries/shipcarriers_create_form.jsp") })
public class ShippingCarriersAction extends BaseAction<ShipCarriersDTO> {
	private ShipCarriersDTO shipCarriers;
	private Integer carrierId;
	private String operation_method;
	private Map<String, String> statusMap;
	private String opType;
	private LinkedHashMap<String, String> billStatusMap;
	private String intlFlag2;
	private String intlFlag1;
	private String intlFlag3;
	private String state;
	private String country;
	private String zipCode;
	private String modifyByUsername;
	private String billid;
	
	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	@Autowired
	private UserDao userDao; 
	@Autowired
	private ExceptionService exceptionUtil;

	public String getModifyByUsername() {
		return modifyByUsername;
	}

	public void setModifyByUsername(String modifyByUsername) {
		this.modifyByUsername = modifyByUsername;
	}

	@Autowired
	private ShipCarriersService shipCarriersService;
	private static final long serialVersionUID = -4997234210266824017L;

	@Override
	public String input() throws Exception {
		if (carrierId != null) {
			String editUrl = "shipping_carriers!input.action?carrierId="
					+ carrierId;
			OrderLockRelease orderLockRelease = new OrderLockRelease();
			String byUser = orderLockRelease.checkOrderStatus(editUrl);
			if (byUser != null) {
				operation_method = byUser;
			}
			this.shipCarriers = this.shipCarriersService
					.getCarriersDetail(carrierId);
			billid=String.valueOf(shipCarriers.getBillid());
			state = shipCarriers.getState();
			country = shipCarriers.getCountry();
			System.out.println(country);
			String intlFlag = shipCarriers.getIntlFlag();
		if(intlFlag!=null && !"".equals(intlFlag)){
			String arr[] = intlFlag.split(":");
			intlFlag1 = arr[0];
			intlFlag2 = arr[1];
			intlFlag3 = arr[2];
		}
			modifyByUsername=userDao.getLoginName(SessionUtil.getUserId());
		} else {
			OrderLockRelease realeseOrderLock = new OrderLockRelease();
			realeseOrderLock.releaseOrderLock();
		}
		ServletActionContext.getRequest().setAttribute("opType", opType);
		billStatusMap = new LinkedHashMap<String, String>();
		billStatusMap.put("ACTIVE", "ACTIVE");
		billStatusMap.put("INACTIVE", "INACTIVE");
		statusMap = new LinkedHashMap<String, String>();
		statusMap.put("ACTIVE", "ACTIVE");
		statusMap.put("INACTIVE", "INACTIVE");
		return "editForm";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public ExceptionService getExceptionUtil() {
		return exceptionUtil;
	}

	public void setExceptionUtil(ExceptionService exceptionUtil) {
		this.exceptionUtil = exceptionUtil;
	}

	@Override
	public String save() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		ShipCarriers shipCarrierbean = null;
		try {
			shipCarriers.setModifyDate(new Date());
			shipCarriers.setCreatedBy(SessionUtil.getUserId());
			shipCarriers.setModifiedBy(SessionUtil.getUserId());
			Integer intlFw1 = 0;
			Integer intlFw2 = 0;
			Integer intlFw3 = 0;
			if (!"".equals(ServletActionContext.getRequest().getParameter(
					"intlFlag1"))
					&& ServletActionContext.getRequest().getParameter(
							"intlFlag1") != null) {
				intlFw1 = Integer.parseInt(ServletActionContext.getRequest()
						.getParameter("intlFlag1"));
			} else {
				intlFw1 = 0;
			}
			if (!"".equals(ServletActionContext.getRequest().getParameter(
					"intlFlag2"))
					&& ServletActionContext.getRequest().getParameter(
							"intlFlag2") != null) {
				intlFw2 = Integer.parseInt(ServletActionContext.getRequest()
						.getParameter("intlFlag2"));
			} else {
				intlFw2 = 0;
			}
			if (!"".equals(ServletActionContext.getRequest().getParameter(
					"intlFlag3"))
					&& ServletActionContext.getRequest().getParameter(
							"intlFlag3") != null) {
				intlFw3 = Integer.parseInt(ServletActionContext.getRequest()
						.getParameter("intlFlag3"));
			} else {
				intlFw3 = 0;
			}

			  state = ServletActionContext.getRequest().getParameter(
					"state");
			  billid=ServletActionContext.getRequest().getParameter(
			"billid");
			

			String intlFlag = intlFw1 + ":" + intlFw2 + ":" + intlFw3;
			shipCarriers.setIntlFlag(intlFlag);
			shipCarriers.setCreationDate(new Date());
			opType = "edit";
			shipCarrierbean = this.shipCarriersService.saveShipCarriers(
					shipCarriers, state,billid);
			rt.put("carrierId", shipCarrierbean.getCarrierId() + "");
			rt.put("message",
					"The shipping carrier #" + shipCarrierbean.getCarrierCode()
							+ " is saved.");

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

	public LinkedHashMap<String, String> getBillStatusMap() {
		return billStatusMap;
	}

	public void setBillStatusMap(LinkedHashMap<String, String> billStatusMap) {
		this.billStatusMap = billStatusMap;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	@Override
	public String delete() throws Exception {

		return null;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOperation_method() {
		return operation_method;
	}

	public void setOperation_method(String operation_method) {
		this.operation_method = operation_method;
	}

	@Override
	protected void prepareModel() throws Exception {
		shipCarriers = new ShipCarriersDTO();

	}

	public ShipCarriersDTO getShipCarriers() {
		return shipCarriers;
	}

	public void setShipCarriers(ShipCarriersDTO shipCarriersDTO) {
		this.shipCarriers = shipCarriersDTO;
	}

	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIntlFlag2() {
		return intlFlag2;
	}

	public void setIntlFlag2(String intlFlag2) {
		this.intlFlag2 = intlFlag2;
	}

	public String getIntlFlag1() {
		return intlFlag1;
	}

	public void setIntlFlag1(String intlFlag1) {
		this.intlFlag1 = intlFlag1;
	}

	public String getIntlFlag3() {
		return intlFlag3;
	}

	public void setIntlFlag3(String intlFlag3) {
		this.intlFlag3 = intlFlag3;
	}

	public ShipCarriersService getShipCarriersService() {
		return shipCarriersService;
	}

	public void setShipCarriersService(ShipCarriersService shipCarriersService) {
		this.shipCarriersService = shipCarriersService;
	}

}
