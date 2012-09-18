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
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderDTO;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.opensymphony.xwork2.ActionSupport;

@Results( { @Result(name = "search", location = "purchase/cust_purchase_order_search_form.jsp"),
	@Result(location = "purchase/cust_purchase_order_search_result.jsp") })
public class CustPurchaseOrderSrchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2715676644273289277L;
	@Autowired
	private CommonSearchService commonSearchService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private PurchaseService PurchaseService;
	private List<CountryDTO> countryList;
	private List<SearchAttributeDTO> attrList;
	private List<MfgOrderDTO> orderList;
	private List<String> salesCenterList;
	
	@Override
	public String execute() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<MfgOrderDTO> pagerUtil = new PagerUtil<MfgOrderDTO>();
		Page<MfgOrderDTO> page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("orderNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		// end of modify 1;
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		//PropertyFilter propertyFilter = new PropertyFilter("EQS_status", "CC");
		//PropertyFilter propertyFilter2 = new PropertyFilter("EQI_companyId", 2);
		//filters.add(propertyFilter);
		//filters.add(propertyFilter2);
//		List<PropertyFilter> poNofilters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest(), "searcher_");
//		if(poNofilters != null && poNofilters.size() > 0){
//			Page<PaymentVoucher> pageOrderMain = new Page<PaymentVoucher>();
//			Set<Integer> orderNoSet = orderService.getOrderNoSetByPoNo(pageOrderMain, poNofilters);
//			if(!orderNoSet.isEmpty()){
//				page = orderService.searchOrder(page, filters, orderNoSet);
//			}else{
//				page.setTotalCount(0L);//
//			}
//		}else {
//			page = orderService.searchOrder(page, filters);
//		}
		System.out.println(">>>>>>>>>"+filters);
		page = PurchaseService.searchMfgOrderDTO(page, filters);
		orderList = page.getResult();
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		// end of modify 2;
		return super.execute();
	}
	
	public String search() throws Exception {
		salesCenterList = publicService.getAllCompanyName();
		attrList = commonSearchService
		.getSearchAttributeList(SearchType.ORDER);
		countryList = publicService.getCountryList();
		return "search";
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


	public List<MfgOrderDTO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MfgOrderDTO> orderList) {
		this.orderList = orderList;
	}

	public List<String> getSalesCenterList() {
		return salesCenterList;
	}

	public void setSalesCenterList(List<String> salesCenterList) {
		this.salesCenterList = salesCenterList;
	}

}
