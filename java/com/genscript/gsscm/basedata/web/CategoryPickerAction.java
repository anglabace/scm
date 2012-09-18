package com.genscript.gsscm.basedata.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.product.entity.ProductCategory;
import com.genscript.gsscm.product.entity.ProductCategoryCatalogBean;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.entity.ServiceCategory;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;
import com.genscript.gsscm.serv.service.ServService;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	@Result(name = "get_product_category", location = "basedata/product_category_list.jsp"),
	@Result(name = "get_service_category", location = "basedata/service_category_list.jsp"),
	@Result(name = "get_product_catalog_list", location = "basedata/product_catalog_list.jsp"),
	@Result(name = "get_service_catalog_list", location = "basedata/service_catalog_list.jsp"),
	@Result(name = "category_index", location = "basedata/category_index.jsp") })
public class CategoryPickerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7689531099159841491L;
	@Autowired
	private ProductService productService;
	@Autowired
	private ServService servService;
	private String type;
	private String categoryNo;
	
	private List<ProductCategory> productCategoryList;
	private List<ServiceCategory> serviceCategoryList;
	private List<ProductCategoryCatalogBean> productInCategoryList;
	private List<ServiceOfServcategoryBean> serviceInCategoryList;
	
	public String index() throws Exception {
		return "category_index";
	}
	
	public String getProductCategory() throws Exception {
		PagerUtil<ProductCategory> pagerUtil = new PagerUtil<ProductCategory>();
		Page<ProductCategory> productPage = pagerUtil.getRequestPage();
		if (!productPage.isOrderBySetted()) {
			productPage.setOrderBy("categoryId");
			productPage.setOrder(Page.ASC);
		}
		productPage.setPageSize(15);
		PageDTO pagerInfo = pagerUtil.formPage(productPage);
		productPage = productService.getAllProductCategory(productPage);
		productCategoryList = productPage.getResult();
		pagerInfo = pagerUtil.formPage(productPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "get_product_category";
	}
	
	public String getServiceCategory() throws Exception {
		PagerUtil<ServiceCategory> pagerUtil = new PagerUtil<ServiceCategory>();
		Page<ServiceCategory> servicePage = pagerUtil.getRequestPage();
		if (!servicePage.isOrderBySetted()) {
			servicePage.setOrderBy("categoryId");
			servicePage.setOrder(Page.ASC);
		}
		servicePage.setPageSize(15);
		PageDTO pagerInfo = pagerUtil.formPage(servicePage);
		servicePage = servService.getAllServiceCategory(servicePage);
		serviceCategoryList = servicePage.getResult();
		pagerInfo = pagerUtil.formPage(servicePage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "get_service_category";
	}

	public String getProductCatalogNoList(){
		PagerUtil<ProductCategoryCatalogBean> pagerUtil = new PagerUtil<ProductCategoryCatalogBean>();
		Page<ProductCategoryCatalogBean> pdtCategoryPage = pagerUtil.getRequestPage();
		if (!pdtCategoryPage.isOrderBySetted()) {
			pdtCategoryPage.setOrderBy("catalogNo");
			pdtCategoryPage.setOrder(Page.ASC);
		}
		pdtCategoryPage.setPageSize(15);
		PageDTO pagerInfo = pagerUtil.formPage(pdtCategoryPage);
		pdtCategoryPage = productService.getCatalogNoListByCategory(pdtCategoryPage, categoryNo);
		productInCategoryList = pdtCategoryPage.getResult();
		pagerInfo = pagerUtil.formPage(pdtCategoryPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "get_product_catalog_list";
	}
	
	public String getServiceCatalogNoList(){
		PagerUtil<ServiceOfServcategoryBean> pagerUtil = new PagerUtil<ServiceOfServcategoryBean>();
		Page<ServiceOfServcategoryBean> srvCategoryPage = pagerUtil.getRequestPage();
		if (!srvCategoryPage.isOrderBySetted()) {
			srvCategoryPage.setOrderBy("catalogNo");
			srvCategoryPage.setOrder(Page.ASC);
		}
		srvCategoryPage.setPageSize(15);
		PageDTO pagerInfo = pagerUtil.formPage(srvCategoryPage);
		srvCategoryPage = servService.getCatalogNoListByCategory(srvCategoryPage, categoryNo);
		serviceInCategoryList = srvCategoryPage.getResult();
		pagerInfo = pagerUtil.formPage(srvCategoryPage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "get_service_catalog_list";
	}
	
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

	public List<ServiceCategory> getServiceCategoryList() {
		return serviceCategoryList;
	}

	public void setServiceCategoryList(List<ServiceCategory> serviceCategoryList) {
		this.serviceCategoryList = serviceCategoryList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public List<ProductCategoryCatalogBean> getProductInCategoryList() {
		return productInCategoryList;
	}

	public void setProductInCategoryList(
			List<ProductCategoryCatalogBean> productInCategoryList) {
		this.productInCategoryList = productInCategoryList;
	}

	public List<ServiceOfServcategoryBean> getServiceInCategoryList() {
		return serviceInCategoryList;
	}

	public void setServiceInCategoryList(
			List<ServiceOfServcategoryBean> serviceInCategoryList) {
		this.serviceInCategoryList = serviceInCategoryList;
	}
	
}
