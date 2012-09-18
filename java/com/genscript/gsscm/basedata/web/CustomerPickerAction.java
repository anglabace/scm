package com.genscript.gsscm.basedata.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.CustomerPickerDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "customer_picker", location = "basedata/customer_manage_list.jsp")
})
public class CustomerPickerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4108968207488269419L;
	@Autowired
	private PurchaseService purchaseService;
	private List<CustomerPickerDTO> customerDTOList;
	private String custName;
	private Integer custNo;
	
	@Override
	public String execute() throws Exception {
		PagerUtil<CustomerPickerDTO> pagerUtil = new PagerUtil<CustomerPickerDTO>();
		Page<CustomerPickerDTO> customerPage = pagerUtil.getRequestPage();
		if (!customerPage.isOrderBySetted()) {
			customerPage.setOrderBy("custNo");
			customerPage.setOrder(Page.ASC);
		}
		customerPage.setPageSize(20);
		PageDTO pagerInfo = pagerUtil.formPage(customerPage);
		if(StringUtils.isBlank(custName) && custNo == null){
			customerPage = purchaseService.getCustomerList(pagerInfo);
		}else{
			customerPage = purchaseService.getCustomerList(pagerInfo, custName, custNo);
		}
		
		customerDTOList = customerPage.getResult();
		pagerInfo = pagerUtil.formPage(customerPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "customer_picker";
	}

	public List<CustomerPickerDTO> getCustomerDTOList() {
		return customerDTOList;
	}

	public void setCustomerDTOList(List<CustomerPickerDTO> customerDTOList) {
		this.customerDTOList = customerDTOList;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}


}
