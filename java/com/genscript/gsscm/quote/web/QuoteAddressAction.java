package com.genscript.gsscm.quote.web;

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
import com.genscript.gsscm.order.entity.MessageTemplate;
import com.genscript.gsscm.quote.dto.QuoteAddressDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuoteMultiAddrDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;
import com.genscript.gsscm.quote.service.QuoteService;

@Results({
		@Result(name = "quote_address", location = "quote/quote_address.jsp"),
		@Result(name = "quote_address_multi", location = "quote/quote_address_multi.jsp") })
public class QuoteAddressAction extends BaseAction<QuoteAddress> {
	private String sessQuoteNo;
	private Integer shiptoAddrFlag;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private PublicService publicService;
	private QuoteAddress billToAddr;
	private QuoteAddress shipToAddr;
	private QuoteAddress soldToAddr;
	private String billToAddrDisplay;
	private String shipToAddrDisplay;
	private String soldToAddrDisplay;
	private String mainStatus;
	private Integer custNo;
	private String itemId;
	private int haveShipTo;
	private QuoteAddress quoteAddress;
	private List<QuoteMultiAddrDTO> addrList;
	private List<MessageTemplate> templateList;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7608302472668209208L;

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

	@Override
	public String list() throws Exception {

		QuoteMainDTO quoteMainDTO = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		shiptoAddrFlag = quoteMainDTO.getShiptoAddrFlag();
		mainStatus = quoteMainDTO.getStatus();
		custNo = quoteMainDTO.getCustNo();
		QuoteAddressDTO quoteAddressDTO = quoteMainDTO.getQuoteAddrList();
		// 为空则需要初始化address
		if (quoteAddressDTO == null) {
			if (StringUtils.isNumeric(sessQuoteNo)) {
				quoteAddressDTO = quoteService.getAddress(Integer
						.parseInt(sessQuoteNo));
				billToAddr = quoteAddressDTO.getBillToAddr();
				shipToAddr = quoteAddressDTO.getShipToAddr();

			}
			if (quoteAddressDTO == null) {
				quoteAddressDTO = new QuoteAddressDTO();

			}

			if (billToAddr == null || shipToAddr == null) {
				DefaultAddressDTO defaultAddressDTO = customerService
						.getDefaultAddress(custNo);
				CustAddrOperDTO defBillToAddr = defaultAddressDTO
						.getDefBillToAddr();
				CustAddrOperDTO defShipToAddr = defaultAddressDTO
						.getDefShipToAddr();
				if (billToAddr == null && defBillToAddr != null) {
					billToAddr = (QuoteAddress) ModelUtils.mergerModel(
							defBillToAddr, new QuoteAddress());
					billToAddr.setAddrId(null);
					if (defBillToAddr.getOrgId() != null) {
						billToAddr.setOrgName(defBillToAddr.getOrgName());
					}
					quoteAddressDTO.setBillToAddr(billToAddr);
				}
				if (shipToAddr == null && defShipToAddr != null) {
					shipToAddr = (QuoteAddress) ModelUtils.mergerModel(
							defShipToAddr, new QuoteAddress());
					shipToAddr.setAddrId(null);
					if (defShipToAddr.getOrgId() != null) {
						shipToAddr.setOrgName(defShipToAddr.getOrgName());
					}
					quoteAddressDTO.setShipToAddr(shipToAddr);
				}
			}
			quoteMainDTO.setQuoteAddrList(quoteAddressDTO);
			SessionUtil.updateRow(SessionConstant.Quote.value(), sessQuoteNo,
					quoteMainDTO);
		}
		billToAddr = quoteAddressDTO.getBillToAddr();
		shipToAddr = quoteAddressDTO.getShipToAddr();
		soldToAddr = quoteAddressDTO.getSoldToAddr();
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
		return "quote_address";
	}

	@SuppressWarnings("unchecked")
	public String listMulti() {
		templateList = publicService.getMessageTmplList();
		QuoteMainDTO quoteMainDTO = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		shiptoAddrFlag = quoteMainDTO.getShiptoAddrFlag();
		mainStatus = quoteMainDTO.getStatus();
		custNo = quoteMainDTO.getCustNo();
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (quoteAddress == null) {
			shipToAddrDisplay = "Not Available";
		} else {
			shipToAddrDisplay = this.getAddressDisplay(quoteAddress);
		}
		if (itemMap != null) {
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			addrList = new ArrayList<QuoteMultiAddrDTO>();
			List<QuoteAddress> quoteAddrList = new ArrayList<QuoteAddress>();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				QuoteAddress tmpAddress = quoteItemDTO.getShipToAddress();
				if (tmpAddress != null) {
					if (!quoteAddrList.contains(tmpAddress)) {
						quoteAddrList.add(tmpAddress);
						QuoteMultiAddrDTO quoteMultiAddrDTO = new QuoteMultiAddrDTO();
						quoteMultiAddrDTO.setAddrDisplay(this
								.getAddressDisplay(tmpAddress));
						quoteMultiAddrDTO.setAddrStr(this
								.getAddrStr(tmpAddress));
						quoteMultiAddrDTO.setItemIdStr(this
								.getItemIdsByAddress(itemMap, tmpAddress));
						quoteMultiAddrDTO.setQuoteAddress(tmpAddress);
						addrList.add(quoteMultiAddrDTO);
					}
				}
			}
		}
		return "quote_address_multi";
	}

	private String getItemIdsByAddress(Map<String, QuoteItemDTO> itemMap,
			QuoteAddress address) {
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		List<String> itemIdList = new ArrayList<String>();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			if (quoteItemDTO.getShipToAddress() != null) {
				if (quoteItemDTO.getShipToAddress().equals(address)) {
					itemIdList.add(tmpId);
				}
			}
		}
		return StringUtils.join(itemIdList.toArray(), ",");
	}

	private String getAddrStr(QuoteAddress address) {
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

	private String getAddressDisplay(QuoteAddress quoteAddress) {
		List<String> tmpList = new ArrayList<String>();
		tmpList.add(quoteAddress.getFirstName() + " "
				+ quoteAddress.getLastName());
		// tmpList.add(quoteAddress.getOrganization().getName());
		System.out.println(quoteAddress.getOrgName());
		tmpList.add(quoteAddress.getOrgName());// 添加了orgname 后 直接调用orgname
		String addrStr = "";
		if (!StringUtils.isEmpty(quoteAddress.getAddrLine1())) {
			addrStr = quoteAddress.getAddrLine1();
		}
		if (!StringUtils.isEmpty(quoteAddress.getAddrLine2())) {
			addrStr = addrStr + ", " + quoteAddress.getAddrLine2();
		}
		if (!StringUtils.isEmpty(quoteAddress.getAddrLine3())) {
			addrStr = addrStr + ", " + quoteAddress.getAddrLine3();
		}
		tmpList.add(addrStr);
		String cityStr = "";
		if (!StringUtils.isEmpty(quoteAddress.getCity())) {
			cityStr = quoteAddress.getCity();
		}
		if (!StringUtils.isEmpty(quoteAddress.getState())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = quoteAddress.getState() + " "
						+ quoteAddress.getZipCode();
			} else {
				cityStr = cityStr + ", " + quoteAddress.getState() + " "
						+ quoteAddress.getZipCode();
			}
		}
		if (!StringUtils.isEmpty(quoteAddress.getCountry())) {
			if (cityStr.equalsIgnoreCase("")) {
				cityStr = quoteAddress.getCountry();
			} else {
				cityStr = cityStr + ", " + quoteAddress.getCountry();
			}
		}
		tmpList.add(cityStr);
		return StringUtils.join(tmpList.toArray(), "<BR>");
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(itemId)) {
			QuoteItemDTO quoteItemDTO = (QuoteItemDTO) SessionUtil.getOneRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo, itemId);
			if (quoteItemDTO != null && quoteItemDTO.getShipToAddress() != null) {
				quoteAddress = quoteItemDTO.getShipToAddress();
			}
		}
	}

	public QuoteAddress getModel() {
		return quoteAddress;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public Integer getShiptoAddrFlag() {
		return shiptoAddrFlag;
	}

	public void setShiptoAddrFlag(Integer shiptoAddrFlag) {
		this.shiptoAddrFlag = shiptoAddrFlag;
	}

	public QuoteAddress getBillToAddr() {
		return billToAddr;
	}

	public void setBillToAddr(QuoteAddress billToAddr) {
		this.billToAddr = billToAddr;
	}

	public QuoteAddress getShipToAddr() {
		return shipToAddr;
	}

	public void setShipToAddr(QuoteAddress shipToAddr) {
		this.shipToAddr = shipToAddr;
	}

	public QuoteAddress getSoldToAddr() {
		return soldToAddr;
	}

	public void setSoldToAddr(QuoteAddress soldToAddr) {
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

	public QuoteAddress getQuoteAddress() {
		return quoteAddress;
	}

	public void setQuoteAddress(QuoteAddress quoteAddress) {
		this.quoteAddress = quoteAddress;
	}

	public List<QuoteMultiAddrDTO> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<QuoteMultiAddrDTO> addrList) {
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
