package com.genscript.gsscm.customer.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.genscript.gsscm.common.util.StringUtil;

import org.apache.commons.lang.StringUtils;
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
import com.genscript.gsscm.common.util.OrderLockRelease;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.service.OrderService;

/**
 * Customer Search Action
 * 
 * @author golf
 * 
 */
@Results({
		@Result(name = "search", location = "customer/customer_search_form.jsp"),
		@Result(location = "customer/customer_search_result.jsp") })
public class CustomerSrchListAction extends BaseAction<CustomerBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1560308429760807606L;
	private List<SearchAttributeDTO> attrList = new ArrayList<SearchAttributeDTO>();
	private List<CountryDTO> countryList;
	private Page<CustomerBean> page = new Page<CustomerBean>(20);
	@Autowired
	private CommonSearchService commonSearchService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private OrderService orderService;
	private CustomerBean customer;
	@Autowired
	private CustomerService customerService;

	StringUtil su = new StringUtil();

	/**
	 * @
	 */
	@Override
	public String list() throws Exception {
		// *********** Add By Zhang Yong Start *****************************//
		// 释放application中的订单锁，主要针对从其他模块中新建此对象时
		OrderLockRelease realeseOrderLock = new OrderLockRelease();
		realeseOrderLock.releaseOrderLock();
		// *********** Add By Zhang Yong End *****************************//

		// modify by wangsf 2010-08-19 10:55 1. begin
		// 获得分页请求相关数据：如第几页
		PagerUtil<CustomerBean> pagerUtil = new PagerUtil<CustomerBean>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("custNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		// end of modify 1;
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		String followUpDatestart = Struts2Util
				.getParameter("creationDatestart");
		String followUpDateend = Struts2Util.getParameter("creationDateend");
		if (StringUtils.isNotBlank(followUpDateend)) {
			PropertyFilter endDate = new PropertyFilter("LED_creationDate",
					followUpDateend);
			filters.add(endDate);
		}
		if (StringUtils.isNotBlank(followUpDatestart)) {
			PropertyFilter startDate = new PropertyFilter("GED_creationDate",
					followUpDatestart);
			filters.add(startDate);
		}
		List<PropertyFilter> orderNofilters = WebUtil.buildPropertyFilters(
				ServletActionContext.getRequest(), "searcher_");
		if (filters == null && orderNofilters == null) {
			page = customerService.searchCustomer(page, filters);
		} else if (orderNofilters != null && orderNofilters.size() > 0) {
			Page<OrderMain> pageOrderMain = new Page<OrderMain>();
			pageOrderMain.setPageSize(10000);
			pageOrderMain = orderService.searchOrderMain(pageOrderMain,
					orderNofilters);
			List<OrderMain> orderMainList = pageOrderMain.getResult();
			Set<Integer> custNoSet = new HashSet<Integer>();
			if (orderMainList != null && orderMainList.size() > 0) {
				for (OrderMain orderMain : orderMainList) {
					custNoSet.add(orderMain.getCustNo());
				}
			}
			page = customerService.advSearchCustomer(page, filters, custNoSet);
		} else {
			page = customerService.searchCustomer(page, filters);
		}
		ServletActionContext.getRequest().setAttribute("su", new StringUtil());

		// modify by wangsf 2010-08-19 10:55 2. begin
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		// end of modify 2;
		return SUCCESS;
	}

	public StringUtil getSu() {
		return su;
	}

	public void setSu(StringUtil su) {
		this.su = su;
	}

	/**
	 * Search Customer function
	 * 
	 * @return "search"
	 * @throws Exception
	 */
	public String search() throws Exception {
		attrList = commonSearchService
				.getSearchAttributeList(SearchType.CUSTOMER);
		countryList = publicService.getCountryList();
		return "search";
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	public Page<CustomerBean> getPage() {
		return page;
	}

	@Override
	public CustomerBean getModel() {
		return customer;
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
}
