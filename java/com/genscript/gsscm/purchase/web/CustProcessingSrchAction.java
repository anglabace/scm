package com.genscript.gsscm.purchase.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.CommonSearchService;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.purchase.entity.PurchaseOrderBean;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.opensymphony.xwork2.ActionSupport;

@Results( { @Result(name = "search", location = "purchase/proc_purchase_order_search_form.jsp"),
	@Result(location = "purchase/proc_purchase_order_search_result.jsp") })
public class CustProcessingSrchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4036887741443348045L;
	@Autowired
	private CommonSearchService commonSearchService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private PurchaseService purchaseService;
	
	private List<PurchaseOrderBean> purchaseOrderList;
	private List<SearchAttributeDTO> attrList;
	private List<CountryDTO> countryList;
	
	@Override
	public String execute() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<PurchaseOrderBean> pagerUtil = new PagerUtil<PurchaseOrderBean>();
		Page<PurchaseOrderBean> page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("orderNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		// end of modify 1;
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		
		if(filters != null && filters.size() > 0){
				page = purchaseService.searchPurchaseOrderBean(page, filters);
		}else {
			page = purchaseService.searchPurchaseOrderBean(page);
		}
		purchaseOrderList = page.getResult();
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		// end of modify 2;
		return super.execute();
	}
	
	public String search() throws Exception {
		attrList = commonSearchService
		.getSearchAttributeList(SearchType.CustPurchaseOrder);
		countryList = publicService.getCountryList();
		return "search";
	}

	public List<PurchaseOrderBean> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrderBean> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public List<SearchAttributeDTO> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<SearchAttributeDTO> attrList) {
		this.attrList = attrList;
	}

	public List<CountryDTO> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDTO> countryList) {
		this.countryList = countryList;
	}
	
}
