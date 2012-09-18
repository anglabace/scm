package com.genscript.gsscm.systemsetting.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.shipment.entity.ShipCarriers;
import com.genscript.gsscm.shipment.service.ShipCarriersBillingService;
import com.genscript.gsscm.shipment.service.ShipCarriersService;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "searchForm", location = "systemsetting/ShippingCarries/shipcarriers_srch.jsp"),
		@Result(location = "systemsetting/ShippingCarries/shipcarriers_list.jsp"),
		@Result(name = "list", location = "systemsetting/ShippingCarries/shipcarriers_list.jsp"),
		@Result(name = "input", location = "systemsetting/ShippingCarries/shipcarriers_input.jsp") })
public class ShippingCarriersSrchAction extends ActionSupport {
	@Autowired
	private ShipCarriersService shipCarriersService; 

	private Page<ShipCarriers> page;
	private static final long serialVersionUID = -8040681295817614363L;

	public String searchForm() {
		return "searchForm";
	}

	@Override
	public String execute() {
		PagerUtil<ShipCarriers> pagerUtil = new PagerUtil<ShipCarriers>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("creationDate");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(20);
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(Struts2Util
				.getRequest());

		if (filters == null) {
			page = shipCarriersService.searchShipCarriers(page);
		} else {
			page = shipCarriersService.searchShipCarriers(page, filters);
		}

		List<ShipCarriers> list = page.getResult();
		page.setResult(list);
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "list";
	}

	public String delete() {
		String carrierIds = Struts2Util.getParameter("carrierIds");
		if (carrierIds != null && !carrierIds.equals("")) {
			String[] carrierArr = carrierIds.split(",");
			for (String carrierId : carrierArr) {
				//shipCarriersBillingService.delShipCarrierBilling(Integer
				//		.parseInt(carrierId));
				// shipCarriersService.delShipCarrier(Integer.parseInt(carrierId));
				shipCarriersService.deleshipCarriers(Integer
						.parseInt(carrierId));
			}
		}
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	public Page<ShipCarriers> getPage() {
		return page;
	}

	public void setPage(Page<ShipCarriers> page) {
		this.page = page;
	}

}
