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

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.QuoteSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.quote.dto.QuoteAddressDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;

/**
 * delPackageImg
 * 处理order package 相关方法
 * 
 * @author zhangyang
 * 
 */
@Results( {
		@Result(name = "package_list", location = "quote/quote_package_list.jsp"),
		@Result(name = "package_edit", location = "quote/quote_package_edit.jsp") })
public class QuotePackageAction extends BaseAction<QuotePackageDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4809263487518529221L;
	@Autowired
	private PublicService publicService;
	private String sessQuoteNo;
	private List<String> itemIdList;
	private Map<String, QuotePackageDTO> packageMap;
	private String sessPackageId;
	private QuotePackageDTO quotePackageDTO;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private OrderPackageDTO shipPackage;
	private String tempStatusStr;
	private String symbol;
	/**
	 * jquery ajax form 提交方法下length是关键字，有冲突，故特别处理
	 */
	private Double length1;
	private List<String> packageIdList;
	private String addressStr;
	private Map<Integer , String >addressStrs;

	/**
	 * 删除package
	 */
	@Override
	public String delete() throws Exception {
		if (packageIdList != null && !packageIdList.isEmpty()) {
			for (int i = 0; i < packageIdList.size(); i++) {
				SessionUtil.deleteOneRow(SessionConstant.QuotePackage.value(),
						sessQuoteNo, packageIdList.get(i));
			}
		}
		QuoteSessionUtil.deleteTotal(sessQuoteNo);
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
//		OrderAddress orderAddress = shipPackage.getShipToAddr();
		this.addressStr = quotePackageDTO.getShiptoAddress();
		//获取quote的状态
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.
			getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if (quote != null) {
			tempStatusStr = quote.getStatus();
		}
		return "package_edit";
	}

	/**
	 * package 列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list()  {
		if (!StringUtils.isNumeric(sessQuoteNo)) {
			return "package_list";
		}
		packageMap = (Map<String, QuotePackageDTO>) SessionUtil
				.getRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		if (packageMap == null || packageMap.isEmpty()) {
			return "package_list";
		}
		Iterator<Entry<String, QuotePackageDTO>> it = packageMap.entrySet().iterator();
		int i=0;
		while (it.hasNext()) {
			Entry<String, QuotePackageDTO> entry = it.next();
			QuotePackageDTO packge = entry.getValue();
			packge.setPkgBatchSeq(i+1);
			packge.setPkgBatchCount(packageMap.size());
			i++;
		}
		return "package_list";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(sessPackageId)) {
			packageMap = (Map<String, QuotePackageDTO>) SessionUtil.getRow(
					SessionConstant.QuotePackage.value(), sessQuoteNo);
			if (packageMap != null) {
				quotePackageDTO = packageMap.get(sessPackageId);
			}
			if (quotePackageDTO == null) {
				return;
			}
			QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
					SessionConstant.Quote.value(), sessQuoteNo);
			if (quote != null) {
				symbol = !StringUtils.isEmpty(quote.getQuoteCurrency())?
						publicService.searchSymbolByCode(quote.getQuoteCurrency()):"";
				QuoteAddressDTO quoteAddressDTO = quote.getQuoteAddrList();
				QuoteAddress quoteAddress = quoteAddressDTO.getShipToAddr();
				if (quoteAddress != null) {
					quotePackageDTO.setDeliveryType(quoteAddress.getAddrClass());
				}		
			}
		}
	}

	public QuotePackageDTO getModel() {
		return quotePackageDTO;
	}

	@Override
	public String save() throws Exception {
		quotePackageDTO.setLength(length1);
		// checkbox
		if (!quotePackageDTO.getAdditionalHandle().equalsIgnoreCase("Y")) {
			quotePackageDTO.setAdditionalHandle("N");
		}
		if (!quotePackageDTO.getDeliveryConfirm().equalsIgnoreCase("Y")) {
			quotePackageDTO.setDeliveryConfirm("N");
		}
		if (!quotePackageDTO.getNoteOnShip().equalsIgnoreCase("Y")) {
			quotePackageDTO.setNoteOnShip("N");
		}
		if (!quotePackageDTO.getNoteOnExcp().equalsIgnoreCase("Y")) {
			quotePackageDTO.setNoteOnExcp("N");
		}
		if (!quotePackageDTO.getNoteOnDelivery().equalsIgnoreCase("Y")) {
			quotePackageDTO.setNoteOnDelivery("N");
		}
		if (!quotePackageDTO.getHazardousMtl().equalsIgnoreCase("Y")) {
			quotePackageDTO.setHazardousMtl("N");
		}
		if (!quotePackageDTO.getSaturdayPickup().equalsIgnoreCase("Y")) {
			quotePackageDTO.setSaturdayPickup("N");
		}
		SessionUtil.updateOneRow(SessionConstant.QuotePackage.value(),
				sessQuoteNo, sessPackageId, quotePackageDTO);
		QuoteSessionUtil.deleteTotal(sessQuoteNo);
		Struts2Util.renderText("SUCCESS");
		return null;
	}



	public QuotePackageDTO getQuotePackageDTO() {
		return quotePackageDTO;
	}

	public void setQuotePackageDTO(QuotePackageDTO quotePackageDTO) {
		this.quotePackageDTO = quotePackageDTO;
	}

	public void setPackageMap(Map<String, QuotePackageDTO> packageMap) {
		this.packageMap = packageMap;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public List<String> getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(List<String> itemIdList) {
		this.itemIdList = itemIdList;
	}

	public String getSessPackageId() {
		return sessPackageId;
	}


	public void setSessPackageId(String sessPackageId) {
		this.sessPackageId = sessPackageId;
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

	public Map<String, QuotePackageDTO> getPackageMap() {
		return packageMap;
	}

	public OrderPackageDTO getShipPackage() {
		return shipPackage;
	}

	public void setShipPackage(OrderPackageDTO shipPackage) {
		this.shipPackage = shipPackage;
	}

	public Map<Integer, String> getAddressStrs() {
		return addressStrs;
	}

	public void setAddressStrs(Map<Integer, String> addressStrs) {
		this.addressStrs = addressStrs;
	}

	public String getTempStatusStr() {
		return tempStatusStr;
	}

	public void setTempStatusStr(String tempStatusStr) {
		this.tempStatusStr = tempStatusStr;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
