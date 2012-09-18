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

import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.DefaultAddressDTO;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.OrderAddressDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderMultiAddrDTO;
import com.genscript.gsscm.order.entity.MessageTemplate;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.service.OrderService;

/**
 * address/mulitaddress tab相关处理
 * 
 * @author zouyulu
 */
@Results({
		@Result(name = "order_address", location = "order/order_address.jsp"),
		@Result(name = "order_address_multi", location = "order/order_address_multi.jsp") })
public class OrderAddressAction extends BaseAction<OrderAddress> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3625586001308634697L;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PublicService publicService;
	private String sessOrderNo;
	private OrderAddress billToAddr;
	private OrderAddress shipToAddr;
	private OrderAddress soldToAddr;
	private String billToAddrDisplay;
	private String shipToAddrDisplay;
	private String soldToAddrDisplay;
	private Integer shiptoAddrFlag;
	private String mainStatus;
	private Integer custNo;
	private String itemId;
	private OrderAddress orderAddress;
	private List<OrderMultiAddrDTO> addrList;
	private List<MessageTemplate> templateList;
	private int haveShipTo;

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
	 * 取得order的address
	 */
	@Override
	public String list() throws Exception {
		OrderMainDTO orderMainDTO = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		OrderAddressDTO orderAddressDTO = null;
		if (orderMainDTO != null && !"".equals(orderMainDTO)) {
			shiptoAddrFlag = orderMainDTO.getShiptoAddrFlag();
			mainStatus = orderMainDTO.getStatus();
			custNo = orderMainDTO.getCustNo();
			orderAddressDTO = orderMainDTO.getOrderAddrList();
		}
		// 为空则需要初始化address
		if (orderAddressDTO == null) {
			if (StringUtils.isNumeric(sessOrderNo)) {
				orderAddressDTO = orderService.getAddress(Integer
						.parseInt(sessOrderNo));
				billToAddr = orderAddressDTO.getBillToAddr();
				shipToAddr = orderAddressDTO.getShipToAddr();
			}
			if (orderAddressDTO == null) {
				orderAddressDTO = new OrderAddressDTO();
			}
			if (billToAddr == null || shipToAddr == null) {
				// 为空则从搜索customer相关address数据
				DefaultAddressDTO defaultAddressDTO = customerService
						.getDefaultAddress(custNo);
				CustAddrOperDTO defBillToAddr = defaultAddressDTO
						.getDefBillToAddr();
				CustAddrOperDTO defShipToAddr = defaultAddressDTO
						.getDefShipToAddr();
				if (billToAddr == null && defBillToAddr != null) {
					billToAddr = (OrderAddress) ModelUtils.mergerModel(
							defBillToAddr, new OrderAddress());
					billToAddr.setAddrId(null);
					if (defBillToAddr.getOrgId() != null) {
						billToAddr.setOrgName(defBillToAddr.getOrgName());
					}
					orderAddressDTO.setBillToAddr(billToAddr);
				}
				if (shipToAddr == null && defShipToAddr != null) {
					shipToAddr = (OrderAddress) ModelUtils.mergerModel(
							defShipToAddr, new OrderAddress());
					shipToAddr.setAddrId(null);
					if (defShipToAddr.getOrgId() != null) {
						shipToAddr.setOrgName(defShipToAddr.getOrgName());
					}
					orderAddressDTO.setShipToAddr(shipToAddr);
				}
			}
			if (orderMainDTO != null && orderAddressDTO != null) {
				orderMainDTO.setOrderAddrList(orderAddressDTO);
			
			SessionUtil.updateRow(SessionConstant.Order.value(), sessOrderNo,
					orderMainDTO);
			}
		}
		billToAddr = orderAddressDTO.getBillToAddr();
		shipToAddr = orderAddressDTO.getShipToAddr();
		soldToAddr = orderAddressDTO.getSoldToAddr();
		String notAvailable = "Not Available";
		if (billToAddr == null) {
			billToAddrDisplay = notAvailable;
		} else {
			billToAddrDisplay = this.getAddressDisplay(billToAddr);
		}
		if (shipToAddr == null) {
			shipToAddrDisplay = notAvailable;
		} else {
			if (shipToAddr.equals(billToAddr)) {
				shipToAddrDisplay = "Same as Bill-To Address";
			} else {
				shipToAddrDisplay = this.getAddressDisplay(shipToAddr);
			}
			this.haveShipTo = 1;
		}
		if (soldToAddr == null) {
			soldToAddrDisplay = notAvailable;
		} else {
			if (soldToAddr.equals(billToAddr)) {
				soldToAddrDisplay = "Same as Bill-To Address";
			} else if (soldToAddr.equals(shipToAddr)) {
				soldToAddrDisplay = "Same as Ship-To Address";
			}
			soldToAddrDisplay = this.getAddressDisplay(soldToAddr);
		}
		return "order_address";
	}

	/**
	 * 初始化multiaddress tab 相关
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String listMulti() throws Exception {
		templateList = publicService.getMessageTmplList();
		OrderMainDTO orderMainDTO = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		shiptoAddrFlag = orderMainDTO.getShiptoAddrFlag();
		mainStatus = orderMainDTO.getStatus();
		custNo = orderMainDTO.getCustNo();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (orderAddress == null) {
			shipToAddrDisplay = "Not Available";
		} else {
			shipToAddrDisplay = this.getAddressDisplay(orderAddress);
		}
		if (itemMap != null) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			addrList = new ArrayList<OrderMultiAddrDTO>();
			List<OrderAddress> orderAddrList = new ArrayList<OrderAddress>();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				OrderAddress tmpAddress = orderItemDTO.getShipToAddress();
				if (tmpAddress != null) {
					if (!orderAddrList.contains(tmpAddress)) {
						orderAddrList.add(tmpAddress);
						OrderMultiAddrDTO orderMultiAddrDTO = new OrderMultiAddrDTO();
						orderMultiAddrDTO.setAddrDisplay(this
								.getAddressDisplay(tmpAddress));
						orderMultiAddrDTO.setAddrStr(this
								.getAddrStr(tmpAddress));
						orderMultiAddrDTO.setItemIdStr(this
								.getItemIdsByAddress(itemMap, tmpAddress));
						orderMultiAddrDTO.setOrderAddress(tmpAddress);
						addrList.add(orderMultiAddrDTO);
					}
				}
			}
		}
		return "order_address_multi";
	}

	/**
	 * 根据address获得itemid。
	 * 
	 * @param itemMap
	 * @param address
	 * @return
	 */
	private String getItemIdsByAddress(Map<String, OrderItemDTO> itemMap,
			OrderAddress address) {
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		List<String> itemIdList = new ArrayList<String>();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			if (orderItemDTO.getShipToAddress() != null) {
				if (orderItemDTO.getShipToAddress().equals(address)) {
					itemIdList.add(tmpId);
				}
			}
		}
		return StringUtils.join(itemIdList.toArray(), ",");
	}

	/**
	 * 根据OrderAddress 获得address字符串。
	 * 
	 * @param address
	 * @return
	 */
	private String getAddrStr(OrderAddress address) {
		String firstName = address.getFirstName();
		String midName = address.getMidName();
		String lastName = address.getLastName();
		String state = address.getState();
		String country = address.getCountry();

		List<String> list = new ArrayList<String>();
		if (!StringUtils.isEmpty(firstName)) {
			list.add(firstName);
		}
		if (!StringUtils.isEmpty(midName)) {
			list.add(midName);
		}
		if (!StringUtils.isEmpty(lastName)) {
			list.add(lastName);
		}
		if (!StringUtils.isEmpty(state)) {
			list.add(state);
		}
		if (!StringUtils.isEmpty(country)) {
			list.add(country);
		}
		return StringUtils.join(list.toArray(), " ");
	}

	public void prepareListMulti() throws Exception {
		prepareModel();
	}

	/**
	 * 根据OrderAddress获得也没所需字符串组合。
	 * 
	 * @param orderAddress
	 * @return
	 */
	private String getAddressDisplay(OrderAddress orderAddress) {
		List<String> tmpList = new ArrayList<String>();
		tmpList.add(orderAddress.getFirstName() + " "
				+ orderAddress.getLastName());
		tmpList.add(orderAddress.getOrgName());
		String addrStr = "";
		if (!StringUtils.isEmpty(orderAddress.getAddrLine1())) {
			addrStr = orderAddress.getAddrLine1();
		}
		if (!StringUtils.isEmpty(orderAddress.getAddrLine2())) {
			addrStr = addrStr + ", " + orderAddress.getAddrLine2();
		}
		if (!StringUtils.isEmpty(orderAddress.getAddrLine3())) {
			addrStr = addrStr + ", " + orderAddress.getAddrLine3();
		}
		tmpList.add(addrStr);
		String cityStr = "";
		if (!StringUtils.isEmpty(orderAddress.getCity())) {
			cityStr = orderAddress.getCity();
		}
		if (!StringUtils.isEmpty(orderAddress.getState())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = orderAddress.getState() + " "
						+ orderAddress.getZipCode();
			} else {
				cityStr = cityStr + ", " + orderAddress.getState() + " "
						+ orderAddress.getZipCode();
			}
		}
		if (!StringUtils.isEmpty(orderAddress.getCountry())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = orderAddress.getCountry();
			} else {
				cityStr = cityStr + ", " + orderAddress.getCountry();
			}
		}
		tmpList.add(cityStr);
		return StringUtils.join(tmpList.toArray(), "<BR>");
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(itemId)) {
			OrderItemDTO orderItemDTO = (OrderItemDTO) SessionUtil.getOneRow(
					SessionConstant.OrderItemList.value(), sessOrderNo, itemId);
			if (orderItemDTO.getShipToAddress() != null) {
				orderAddress = orderItemDTO.getShipToAddress();
			}
		}
	}

	public OrderAddress getModel() {
		return orderAddress;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public OrderAddress getBillToAddr() {
		return billToAddr;
	}

	public void setBillToAddr(OrderAddress billToAddr) {
		this.billToAddr = billToAddr;
	}

	public OrderAddress getShipToAddr() {
		return shipToAddr;
	}

	public void setShipToAddr(OrderAddress shipToAddr) {
		this.shipToAddr = shipToAddr;
	}

	public OrderAddress getSoldToAddr() {
		return soldToAddr;
	}

	public void setSoldToAddr(OrderAddress soldToAddr) {
		this.soldToAddr = soldToAddr;
	}

	public String getBillToAddrDisplay() {
		return billToAddrDisplay;
	}

	public void setBillToAddrDisplay(String billToAddrDisplay) {
		this.billToAddrDisplay = billToAddrDisplay;
	}

	public String getShipToAddrDisplay() {
		return shipToAddrDisplay;
	}

	public void setShipToAddrDisplay(String shipToAddrDisplay) {
		this.shipToAddrDisplay = shipToAddrDisplay;
	}

	public String getSoldToAddrDisplay() {
		return soldToAddrDisplay;
	}

	public void setSoldToAddrDisplay(String soldToAddrDisplay) {
		this.soldToAddrDisplay = soldToAddrDisplay;
	}

	public Integer getShiptoAddrFlag() {
		return shiptoAddrFlag;
	}

	public void setShiptoAddrFlag(Integer shiptoAddrFlag) {
		this.shiptoAddrFlag = shiptoAddrFlag;
	}

	public String getMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public List<OrderMultiAddrDTO> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<OrderMultiAddrDTO> addrList) {
		this.addrList = addrList;
	}

	public List<MessageTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<MessageTemplate> templateList) {
		this.templateList = templateList;
	}

	public int getHaveShipTo() {
		return haveShipTo;
	}

	public void setHaveShipTo(int haveShipTo) {
		this.haveShipTo = haveShipTo;
	}

}
