package com.genscript.gsscm.systemsetting.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	@Result(name = "showMain", location = "systemsetting/shipping_method_search.jsp"),
	@Result(location = "systemsetting/shipping_method_list.jsp")
  }
)
public class ShippingMethodSrchAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7248947586980029929L;
	@Autowired
	private SystemSettingService systemSettingService;
	private Page<ShipMethod> page;
	
	@Override
	public String execute() throws Exception {
		PagerUtil<ShipMethod> pagerUtil = new PagerUtil<ShipMethod>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("methodCode");
			page.setOrder(Page.ASC);
		}
		page.setPageSize(12);
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(Struts2Util.getRequest());
		if(filters == null){
			page = systemSettingService.searchShipMethod(page);
		}else{
			page = systemSettingService.searchShipMethod(page, filters);
		}
		Struts2Util.getRequest().setAttribute("pagerInfo", page);
		return SUCCESS;
	}
	
	public String showMain() throws Exception {
		return "showMain";
	}

	public Page<ShipMethod> getPage() {
		return page;
	}

	public void setPage(Page<ShipMethod> page) {
		this.page = page;
	}
}
