package com.genscript.gsscm.basedata.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "vendor_picker", location = "basedata/vendor_manage_list.jsp")
})
public class VendorPickerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6682473604240362403L;
	@Autowired
	private PurchaseService purchaseService;
	private String searchName;
	private List<VendorDTO> vendorDTOList;
	
	@Override
	public String execute() throws Exception {
		PagerUtil<VendorDTO> pagerUtil = new PagerUtil<VendorDTO>();
		Page<VendorDTO> vendorPage = pagerUtil.getRequestPage();
		if (!vendorPage.isOrderBySetted()) {
			vendorPage.setOrderBy("vendorNo");
			vendorPage.setOrder(Page.ASC);
		}
		vendorPage.setPageSize(20);
		PageDTO pagerInfo = pagerUtil.formPage(vendorPage);
		vendorPage = purchaseService.getVendorList(pagerInfo, searchName);
		vendorDTOList = vendorPage.getResult();
		pagerInfo = pagerUtil.formPage(vendorPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "vendor_picker";
	}

	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<VendorDTO> getVendorDTOList() {
		return vendorDTOList;
	}

	public void setVendorDTOList(List<VendorDTO> vendorDTOList) {
		this.vendorDTOList = vendorDTOList;
	}
	
}
