package com.genscript.gsscm.quote.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.genscript.gsscm.common.constant.OrderQuoteStautsType;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.quote.entity.QuoteMainBean;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.service.QuoteOrderUtil;

@Results({ @Result(location = "quote/quote_search_form.jsp"),
		@Result(name = "quoteList", location = "quote/quote_list.jsp") })
public class QuoteSearchAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754834734991930682L;
	private List<SearchAttributeDTO> attrList = new ArrayList<SearchAttributeDTO>();
	private List<CountryDTO> countryList;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private CommonSearchService commonSearchService;
	@Autowired
	private QuoteOrderUtil quoteOrderUtil;
	private List<QuoteMainBean> quoteList;
	private Map<Integer, String> addressMap;
	private Map<Integer, String> followUpDateMap;
	Page<QuoteMainBean> page;
	private QuoteMainBean quote;

	/**
	 * 通过此方法转向quotation_processd页面
	 * 
	 * @return
	 * @author YangZhang
	 */
	public String execute() {
		attrList = commonSearchService.getSearchAttributeList(SearchType.QUOTE);
		countryList = publicService.getCountryList();
		return SUCCESS;
	}

	/**
	 * get quoteList
	 * 
	 * @return String
	 * @author YangZhang
	 */
	public String quoteList() throws Exception {

		// 获得分页请求相关数据：如第几页
		PagerUtil<QuoteMainBean> pagerUtil = new PagerUtil<QuoteMainBean>();

		page = pagerUtil.getRequestPage();

		if (!page.isOrderBySetted()) {
			page.setOrderBy("quoteNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		// 创建过滤器，过滤页面的属性值
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		String followUpDatestart = Struts2Util
				.getParameter("followUpDatestart");
		String followUpDateend = Struts2Util.getParameter("followUpDateend");
		if (StringUtils.isNotBlank(followUpDateend)) {
			PropertyFilter endDate = new PropertyFilter("LED_followUpDate",
					followUpDateend);
			filters.add(endDate);
		}
		if (StringUtils.isNotBlank(followUpDatestart)) {
			PropertyFilter startDate = new PropertyFilter("GED_followUpDate",
					followUpDatestart);
			filters.add(startDate);
		}
		page = quoteService.searchQuote(page, filters);
		quoteList = page.getResult();
		addressMap = new HashMap<Integer, String>();
		followUpDateMap = new HashMap<Integer, String>();
		// 组织address，把地址拼在一起
		if (quoteList != null && !quoteList.isEmpty()) {
			List<StatusList> statusList = quoteOrderUtil
					.getStatusListByType(OrderQuoteStautsType.QUOTE.value());
			Map<String, String> statusMap = new HashMap<String, String>();
			if (statusList != null && !statusList.isEmpty()) {
				for (StatusList status : statusList) {
					statusMap.put(status.getCode(), status.getName());
				}
			}
			for (int i = 0; i < quoteList.size(); i++) {
				QuoteMainBean tmpQuoteMainBean = quoteList.get(i);
				int quoteNo = tmpQuoteMainBean.getQuoteNo();
				String followUpdate = this.quoteService
						.getFollowUpDateByQuoteNo(quoteNo);
				if (statusMap.containsKey(tmpQuoteMainBean.getStatus())) {
					tmpQuoteMainBean.setFulStatus(statusMap
							.get(tmpQuoteMainBean.getStatus()));
				} else {
					tmpQuoteMainBean.setFulStatus(tmpQuoteMainBean.getStatus());
				}
				String address = AddressUtil.getFullAddress(
						tmpQuoteMainBean.getAddrLine1(),
						tmpQuoteMainBean.getAddrLine2(),
						tmpQuoteMainBean.getAddrLine3(),
						tmpQuoteMainBean.getCity(),
						tmpQuoteMainBean.getState(),
						tmpQuoteMainBean.getZipCode(),
						tmpQuoteMainBean.getCountry());
				addressMap.put(quoteNo, address);
				followUpDateMap.put(quoteNo, followUpdate);
			}
		}

		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		// end of modify 2;

		return "quoteList";
	}

	public QuoteMainBean getQuote() {
		return quote;
	}

	public Map<Integer, String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<Integer, String> addressMap) {
		this.addressMap = addressMap;
	}

	public Page<QuoteMainBean> getPage() {
		return page;
	}

	public void setPage(Page<QuoteMainBean> page) {
		this.page = page;
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

	public List<QuoteMainBean> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<QuoteMainBean> quoteList) {
		this.quoteList = quoteList;
	}

	public void setQuote(QuoteMainBean quote) {
		this.quote = quote;
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

	@Override
	public String list() throws Exception {
		attrList = commonSearchService.getSearchAttributeList(SearchType.QUOTE);
		countryList = publicService.getCountryList();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	public Map<Integer, String> getFollowUpDateMap() {
		return followUpDateMap;
	}

	public void setFollowUpDateMap(Map<Integer, String> followUpDateMap) {
		this.followUpDateMap = followUpDateMap;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
