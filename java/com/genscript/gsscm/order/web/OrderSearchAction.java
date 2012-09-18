package com.genscript.gsscm.order.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.order.service.OrderService;

/**
 * Order Search Action
 * 
 * @author zouyulu
 * 
 */
@Results({ @Result(name = "search", location = "order/order_search_form.jsp"),
		@Result(location = "order/order_search_result.jsp") })
public class OrderSearchAction extends BaseAction<OrderMainBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8129372517770072808L;
	/**
	 * 
	 */
	private List<SearchAttributeDTO> attrList = new ArrayList<SearchAttributeDTO>();
	private List<CountryDTO> countryList;
	private List<OrderMainBean> orderList;
	private Map<Integer, String> addressMap;
	@Autowired
	private CommonSearchService commonSearchService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private OrderService orderService;
	private OrderMainBean order;
	private String internalCustomer;

	/**
	 * 搜索列表
	 * 
	 * @author zouyulu
	 */
	@Override
	public String list() throws Exception {
		//System.out.println(ServletActionContext.getRequest().getParameter("filter_EQI_orderNo"));
		//System.out.println(ServletActionContext.getRequest().getParameter("filter_EQI_custNo"));
		// 获得分页请求相关数据：如第几页
		PagerUtil<OrderMainBean> pagerUtil = new PagerUtil<OrderMainBean>();
		Page<OrderMainBean> page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("orderNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		List<PropertyFilter> poNofilters = WebUtil.buildPropertyFilters(
				ServletActionContext.getRequest(), "searcher_");

		if (poNofilters != null && poNofilters.size() > 0) {
			Page<PaymentVoucher> pageOrderMain = new Page<PaymentVoucher>();
			pageOrderMain.setPageSize(20);
			Set<Integer> orderNoSet = orderService.getOrderNoSetByPoNo(
					pageOrderMain, poNofilters);
			if (!orderNoSet.isEmpty()) {
				page = orderService.searchOrder(page, filters, orderNoSet);
			} else {
				page.setTotalCount(0L);//
			}
		} else {
			page = orderService.searchOrderByfilter(page, filters);
		}
		orderList = page.getResult();
		addressMap = new HashMap<Integer, String>();
		if (orderList != null && !orderList.isEmpty()) {
			for (int i = 0; i < orderList.size(); i++) {
				OrderMainBean tmpOrderMainBean = orderList.get(i);
				int orderNo = tmpOrderMainBean.getOrderNo();
				String address = AddressUtil.getFullAddress(
						tmpOrderMainBean.getAddrLine1(),
						tmpOrderMainBean.getAddrLine2(),
						tmpOrderMainBean.getAddrLine3(),
						tmpOrderMainBean.getCity(),
						tmpOrderMainBean.getState(),
						tmpOrderMainBean.getZipCode(),
						tmpOrderMainBean.getCountry());
				addressMap.put(orderNo, address);
			}
		}
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		// end of modify 2;
		return SUCCESS;
	}

	/**
	 * Search Order function
	 * 
	 * @return "search"
	 * @throws Exception
	 */
	public String search() throws Exception {
		attrList = commonSearchService.getSearchAttributeList(SearchType.ORDER);
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

	@Override
	public OrderMainBean getModel() {
		return order;
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

	public List<OrderMainBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderMainBean> orderList) {
		this.orderList = orderList;
	}

	public Map<Integer, String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<Integer, String> addressMap) {
		this.addressMap = addressMap;
	}

	public String getInternalCustomer() {
		return internalCustomer;
	}

	public void setInternalCustomer(String internalCustomer) {
		this.internalCustomer = internalCustomer;
	}
}
