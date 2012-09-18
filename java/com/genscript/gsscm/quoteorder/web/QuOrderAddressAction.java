package com.genscript.gsscm.quoteorder.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.NamePfx;
import com.genscript.gsscm.common.constant.NameSfx;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.order.dto.OrderAddressDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.quote.dto.QuoteAddressDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;

/**
 * 
 * 处理order address相关
 */
@Results( { @Result(name = "address_select", location = "quoteorder/quoteorder_address_select.jsp") })
public class QuOrderAddressAction extends BaseAction<OrderAddress> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6424635079748382873L;

	@Autowired
	private AddressService addressService;
	private Integer custNo;
	private String quorderNo;
	/**
	 * 为order 或 quote
	 */
	private String codeType;
	/**
	 * 为区分保存Order Address 还是 item 的address
	 */
	private String saveType;
	private String mainAddrType;
	private List<CustAddrOperDTO> addrList;
	private Organization organization;
	private OrderAddress orderAddress;
	private QuoteAddress quoteAddress;
	private String itemId;
	private String[] itemIds;
	private NamePfx[] namePfxList;
	private NameSfx[] nameSfxList;
	private Integer addrId;
	private Integer shiptoAddrFlag;
	private String giftMsg;
	private String orgId;
	

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	 * 更新address 的message
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateGiftMsg() throws Exception {
		if (codeType.equalsIgnoreCase("order") && orderAddress != null) {
			orderAddress.setMessage(giftMsg);
			updateOrderAddress();
		} else if (codeType.equalsIgnoreCase("quote")) {
			quoteAddress.setMessage(giftMsg);
			updateQuoteAddress();
		}
		return null;
	}

	public void prepareUpdateGiftMsg() throws Exception {
		prepareModel();
	}

	/**
	 * 获得customer 的address
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCustAddr() throws Exception {
		CustAddrOperDTO custAddrOperDTO = addressService.getAddressDTO(addrId);
		orderAddress = (OrderAddress) ModelUtils.mergerModel(custAddrOperDTO,
				new OrderAddress());
	/*	if (custAddrOperDTO != null && custAddrOperDTO.getOrganization() != null) {
			orderAddress.setOrgId(custAddrOperDTO.getOrganization().getOrgId());
			orderAddress.setOrgName(custAddrOperDTO.getOrganization().getName());
		}*/
		if (custAddrOperDTO != null && custAddrOperDTO.getOrgId() != null) {
			orderAddress.setOrgName(custAddrOperDTO.getOrgName());
		}
		Struts2Util.renderJson(orderAddress);
		return null;
	}

	/**
	 * 显示customer address 列表
	 */
	@Override
	public String list() throws Exception {
		this.setNamePfxList(NamePfx.values());
		this.setNameSfxList(NameSfx.values());
		CustAddrSrchDTO addrSrchDTO = new CustAddrSrchDTO();
		addrSrchDTO.setCustNo(custNo);
		addrList = addressService.getAddrList(addrSrchDTO);
		/*if(addrList != null && addrList.size()>0){
			organization = addrList.get(0).getOrganization();
		}*/
		return "address_select";
	}

	public void prepareList() throws Exception {
		prepareModel();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (codeType.equalsIgnoreCase("order")) {
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
					SessionConstant.Order.value(), quorderNo);
			String tmpType = "";
			if (saveType.equalsIgnoreCase("main")) {
				if (mainAddrType.equalsIgnoreCase("BILL_TO")) {
					orderAddress = order.getOrderAddrList().getBillToAddr();
				} else if (mainAddrType.equalsIgnoreCase("SHIP_TO")) {
					orderAddress = order.getOrderAddrList().getShipToAddr();
				} else if (mainAddrType.equalsIgnoreCase("SOLD_TO")) {
					orderAddress = order.getOrderAddrList().getSoldToAddr();
				}
				tmpType = mainAddrType;
			} else {
				OrderItemDTO orderItem = (OrderItemDTO) SessionUtil.getOneRow(
						SessionConstant.OrderItemList.value(), quorderNo,
						itemId);
				orderAddress = orderItem.getShipToAddress();
				tmpType = AddressType.SHIP_TO.value();
			}
			if (orderAddress == null) {
				orderAddress = new OrderAddress();
				orderAddress.setAddrType(tmpType);
			} else {
				orderAddress.setAddrId(null);
			}
		} else {
			if (codeType.equalsIgnoreCase("quote")) {

				QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
						SessionConstant.Quote.value(), quorderNo);
				String tmpType = "";

				if (saveType.equalsIgnoreCase("main")) {

					if (mainAddrType.equalsIgnoreCase("BILL_TO")) {
						quoteAddress = quote.getQuoteAddrList().getBillToAddr();

					} else if (mainAddrType.equalsIgnoreCase("SHIP_TO")) {
						quoteAddress = quote.getQuoteAddrList().getShipToAddr();
					} else if (mainAddrType.equalsIgnoreCase("SOLD_TO")) {
						quoteAddress = quote.getQuoteAddrList().getSoldToAddr();
					}
					tmpType = mainAddrType;
				} else {

					QuoteItemDTO quoteItem = (QuoteItemDTO) SessionUtil
							.getOneRow(SessionConstant.QuoteItemList.value(),
									quorderNo, itemId);
					quoteAddress = quoteItem.getShipToAddress();
					tmpType = AddressType.SHIP_TO.value();
				}
				if (quoteAddress == null) {
					quoteAddress = new QuoteAddress();
					quoteAddress.setAddrType(tmpType);
				} else {
					quoteAddress.setAddrId(null);
				}
			}
			orderAddress = (OrderAddress) ModelUtils.mergerModel(quoteAddress,
					new OrderAddress());
		}

	}

	@SuppressWarnings("unchecked")
	private List<OrderAddress> getSelectedAddress() {
		// itemIds
		List<OrderAddress> retList = new ArrayList<OrderAddress>();
		if (codeType.equalsIgnoreCase("order")) {
			if (saveType.equalsIgnoreCase("main")) {
				OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
						SessionConstant.Order.value(), quorderNo);
				OrderAddress tmpAddress = null; 
				if(order.getOrderAddrList()!=null){
				if (mainAddrType.equalsIgnoreCase("BILL_TO")) {
					tmpAddress = order.getOrderAddrList().getBillToAddr();
				} else if (mainAddrType.equalsIgnoreCase("SHIP_TO")) {
					tmpAddress = order.getOrderAddrList().getShipToAddr();
				} else if (mainAddrType.equalsIgnoreCase("SOLD_TO")) { 
					tmpAddress = order.getOrderAddrList().getSoldToAddr(); 
				}
				}
				if (tmpAddress != null) {
					retList.add(tmpAddress);
				}
			} else {
				Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
						.getRow(SessionConstant.OrderItemList.value(),
								quorderNo);
				for (int i = 0; i < itemIds.length; i++) {
					if (itemMap.get(itemIds[i]).getShipToAddress() != null) {
						retList.add(itemMap.get(itemIds[i]).getShipToAddress());
					}
				}
			}
		}
		return retList;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public String saveAddress() throws Exception {
		quoteAddress = (QuoteAddress) ModelUtils.mergerModel(orderAddress,
				new QuoteAddress());
		System.out.println("saveAddress codeType===" + codeType);
		if (codeType.equalsIgnoreCase("order")) {
			
			updateOrderAddress();
		} else if (codeType.equalsIgnoreCase("quote")) {
			System.out.println("quote address: " + quoteAddress);
			quoteAddress = new QuoteAddress();

			quoteAddress = (QuoteAddress) ModelUtils.mergerModel(orderAddress,
					quoteAddress);

			updateQuoteAddress();
		}
		return null;
	}

	public void prepareSaveAddress() throws Exception {
		prepareModel();
	}

	public String changeShiptoAddrFlag() {
		if (codeType.equalsIgnoreCase("order")) {
			this.changeOrderShiptoAddrFlag();
		} else {
			this.changeQuoteShiptoAddrFlag();
		}
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	@SuppressWarnings("unchecked")
	private void changeQuoteShiptoAddrFlag() {
		QuoteMainDTO quoteMainDTO = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), quorderNo);
		QuoteAddress shipToAddress = quoteMainDTO.getQuoteAddrList()
				.getShipToAddr();
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), quorderNo);
		Integer oldShiptoAddrFlag = quoteMainDTO.getShiptoAddrFlag();
		boolean changeFlag = false;

		if (itemMap == null) {
			// 不做处理
		} else if (((oldShiptoAddrFlag.intValue() == 1 || oldShiptoAddrFlag
				.intValue() == 2) && shiptoAddrFlag.intValue() == 3)) {
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				String tmpId = entry.getKey();
				quoteItemDTO.setShipToAddress(null);
				itemMap.put(tmpId, quoteItemDTO);
			}
			changeFlag = true;
		} else if (oldShiptoAddrFlag.intValue() == 3
				&& (shiptoAddrFlag.intValue() == 1 || shiptoAddrFlag.intValue() == 2)) {
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				String tmpId = entry.getKey();
				quoteItemDTO.setShipToAddress(shipToAddress);
				itemMap.put(tmpId, quoteItemDTO);
			}
			changeFlag = true;
		}
		if (changeFlag == true) {
			SessionUtil.updateRow(SessionConstant.QuoteItemList.value(),
					quorderNo, itemMap);
		}
		quoteMainDTO.setShiptoAddrFlag(shiptoAddrFlag);
		SessionUtil.updateRow(SessionConstant.Quote.value(), quorderNo,
				quoteMainDTO);
	}

	@SuppressWarnings("unchecked")
	private void changeOrderShiptoAddrFlag() {
		OrderMainDTO orderMainDTO = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), quorderNo);
		OrderAddress shipToAddress = orderMainDTO.getOrderAddrList()
				.getShipToAddr();
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), quorderNo);
		Integer oldShiptoAddrFlag = orderMainDTO.getShiptoAddrFlag();
		if(oldShiptoAddrFlag == null){
			oldShiptoAddrFlag = 1;
			orderMainDTO.setShiptoAddrFlag(1);
		}
		boolean changeFlag = false;

		if (itemMap == null) {
			// 不做处理
		} else if (((oldShiptoAddrFlag.intValue() == 1 || oldShiptoAddrFlag
				.intValue() == 2) && shiptoAddrFlag.intValue() == 3)) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				String tmpId = entry.getKey();
				orderItemDTO.setShipToAddress(null);
				itemMap.put(tmpId, orderItemDTO);
			}
			changeFlag = true;
		} else if (oldShiptoAddrFlag.intValue() == 3
				&& (shiptoAddrFlag.intValue() == 1 || shiptoAddrFlag.intValue() == 2)) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				String tmpId = entry.getKey();
				orderItemDTO.setShipToAddress(shipToAddress);
				itemMap.put(tmpId, orderItemDTO);
			}
			changeFlag = true;
		}
		if (changeFlag == true) {
			SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
					quorderNo, itemMap);
		}
		orderMainDTO.setShiptoAddrFlag(shiptoAddrFlag);
		SessionUtil.updateRow(SessionConstant.Order.value(), quorderNo,
				orderMainDTO);
	}

	@SuppressWarnings("unchecked")
	private void updateOrderAddress() throws Exception {
		List<OrderAddress> selectedAddress = this.getSelectedAddress();
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), quorderNo);
		OrderAddressDTO orderAddressDTO = order.getOrderAddrList();
		boolean addressChangeFlag = false;
		if ((selectedAddress.contains(orderAddressDTO.getShipToAddr()) || selectedAddress.isEmpty())
				&& mainAddrType.equalsIgnoreCase(AddressType.SHIP_TO.value())) {
			orderAddress.setAddrType("SHIP_TO");
			orderAddressDTO.setShipToAddr(orderAddress);
			addressChangeFlag = true;
			//清除package
			SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), quorderNo);
		}
		if ((selectedAddress.contains(orderAddressDTO.getBillToAddr()) || selectedAddress.isEmpty())
				&& mainAddrType.equalsIgnoreCase(AddressType.BILL_TO.value())) {
			orderAddress.setAddrType("BILL_TO");
			orderAddressDTO.setBillToAddr(orderAddress);
			addressChangeFlag = true;
		}
		if ((selectedAddress.contains(orderAddressDTO.getSoldToAddr()) || selectedAddress.isEmpty())
				&& mainAddrType.equalsIgnoreCase("SOLD_TO")) {
			orderAddress.setAddrType("SOLD_TO");
			orderAddressDTO.setSoldToAddr(orderAddress);
			addressChangeFlag = true;
		}
		if (addressChangeFlag == true) {
			order.setOrderAddrList(orderAddressDTO);
			SessionUtil.updateRow(SessionConstant.Order.value(), quorderNo,
					order);
		}
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), quorderNo);
		boolean itemAddressChangeFlag = false;
		if (itemMap != null) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				String tmpId = entry.getKey();
				if (selectedAddress.contains(orderItemDTO.getShipToAddress())
						|| (itemIds != null && Arrays.asList(itemIds).contains(
								tmpId))) {
					orderItemDTO.setShipToAddress(orderAddress);
					itemMap.put(tmpId, orderItemDTO);
					itemAddressChangeFlag = true;
					//清除package
					SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), quorderNo);
				}
			}
		}
		if (itemAddressChangeFlag == true) {
			SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
					quorderNo, itemMap);
		}
	}

	@SuppressWarnings("unchecked")
	private void updateQuoteAddress() throws Exception {
		List<QuoteAddress> selectedAddress = this.getQuoteSelectedAddress();
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), quorderNo);
		QuoteAddressDTO quoteAddressDTO = quote.getQuoteAddrList();
		boolean addressChangeFlag = false;
		if ((selectedAddress.contains(quoteAddressDTO.getShipToAddr()) || selectedAddress.isEmpty())
				&& mainAddrType.equalsIgnoreCase("SHIP_TO")) {
			quoteAddressDTO.setShipToAddr(quoteAddress);
			addressChangeFlag = true;
			//清除package
			SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), quorderNo);
		}
		if ((selectedAddress.contains(quoteAddressDTO.getBillToAddr()) || selectedAddress.isEmpty())
				&& mainAddrType.equalsIgnoreCase("BILL_TO")) {
			quoteAddressDTO.setBillToAddr(quoteAddress);
			addressChangeFlag = true;
		}
		if ((selectedAddress.contains(quoteAddressDTO.getSoldToAddr()) || selectedAddress.isEmpty())
				&& mainAddrType.equalsIgnoreCase("SOLD_TO")) {
			quoteAddressDTO.setSoldToAddr(quoteAddress);
			addressChangeFlag = true;
		}
		if (addressChangeFlag == true) {
			quote.setQuoteAddrList(quoteAddressDTO);
			SessionUtil.updateRow(SessionConstant.Quote.value(), quorderNo,
					quote);
		}
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), quorderNo);
		boolean itemAddressChangeFlag = false;
		if (itemMap != null) {
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				String tmpId = entry.getKey();
				if (selectedAddress.contains(quoteItemDTO.getShipToAddress())
						|| (itemIds != null && Arrays.asList(itemIds).contains(
								tmpId))) {
					quoteItemDTO.setShipToAddress(quoteAddress);
					itemMap.put(tmpId, quoteItemDTO);
					itemAddressChangeFlag = true;
					//清除package
					SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), quorderNo);
				}
			}
		}
		if (itemAddressChangeFlag == true) {
			SessionUtil.updateRow(SessionConstant.QuoteItemList.value(),
					quorderNo, itemMap);
		}
	}

	@SuppressWarnings("unchecked")
	private List<QuoteAddress> getQuoteSelectedAddress() {
		// itemIds
		List<QuoteAddress> retList = new ArrayList<QuoteAddress>();
		if (codeType.equalsIgnoreCase("quote")) {
			if (saveType.equalsIgnoreCase("main")) {
				QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
						SessionConstant.Quote.value(), quorderNo);
				QuoteAddress tmpAddress = null;
				if (mainAddrType.equalsIgnoreCase("BILL_TO")) {
					tmpAddress = quote.getQuoteAddrList().getBillToAddr();
				} else if (mainAddrType.equalsIgnoreCase("SHIP_TO")) {
					tmpAddress = quote.getQuoteAddrList().getShipToAddr();
				} else if (mainAddrType.equalsIgnoreCase("SOLD_TO")) {
					tmpAddress = quote.getQuoteAddrList().getSoldToAddr();
				}
				if (tmpAddress != null) {
					retList.add(tmpAddress);
				}
			} else {
				Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
						.getRow(SessionConstant.QuoteItemList.value(),
								quorderNo);
				for (int i = 0; i < itemIds.length; i++) {
					if (itemMap.get(itemIds[i]).getShipToAddress() != null) {
						retList.add(itemMap.get(itemIds[i]).getShipToAddress());
					}
				}
			}
		}
		return retList;
	}

	public OrderAddress getModel() {
		return orderAddress;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public String getQuorderNo() {
		return quorderNo;
	}

	public void setQuorderNo(String quorderNo) {
		this.quorderNo = quorderNo;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public String getMainAddrType() {
		return mainAddrType;
	}

	public void setMainAddrType(String mainAddrType) {
		this.mainAddrType = mainAddrType;
	}

	public List<CustAddrOperDTO> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<CustAddrOperDTO> addrList) {
		this.addrList = addrList;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public NamePfx[] getNamePfxList() {
		return namePfxList;
	}

	public void setNamePfxList(NamePfx[] namePfxList) {
		this.namePfxList = namePfxList;
	}

	public NameSfx[] getNameSfxList() {
		return nameSfxList;
	}

	public void setNameSfxList(NameSfx[] nameSfxList) {
		this.nameSfxList = nameSfxList;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public String[] getItemIds() {
		return itemIds;
	}

	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}

	public Integer getShiptoAddrFlag() {
		return shiptoAddrFlag;
	}

	public void setShiptoAddrFlag(Integer shiptoAddrFlag) {
		this.shiptoAddrFlag = shiptoAddrFlag;
	}

	public String getGiftMsg() {
		return giftMsg;
	}

	public void setGiftMsg(String giftMsg) {
		this.giftMsg = giftMsg;
	}

	public QuoteAddress getQuoteAddress() {
		return quoteAddress;
	}

	public void setQuoteAddress(QuoteAddress quoteAddress) {
		this.quoteAddress = quoteAddress;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
