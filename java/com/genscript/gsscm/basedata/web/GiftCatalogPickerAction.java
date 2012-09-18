package com.genscript.gsscm.basedata.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "gift_catalog_picker", location = "basedata/gift_catalog_list.jsp")
})
public class GiftCatalogPickerAction extends ActionSupport {
	@Autowired
	private ProductService productService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6608746902166494585L;
	private List<Product> productList;
	@Override
	public String execute() throws Exception {
		PagerUtil<Product> pagerUtil = new PagerUtil<Product>();
		Page<Product> productPage = pagerUtil.getRequestPage();
		if (!productPage.isOrderBySetted()) {
			productPage.setOrderBy("productId");
			productPage.setOrder(Page.ASC);
		}
		productPage.setPageSize(20);
		PageDTO pagerInfo = pagerUtil.formPage(productPage);
		productPage = productService.getGiftableCatalog(productPage);
		productList = productPage.getResult();
		pagerInfo = pagerUtil.formPage(productPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "gift_catalog_picker";
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
