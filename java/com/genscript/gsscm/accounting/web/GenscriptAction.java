package com.genscript.gsscm.accounting.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ApInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dto.OrderNoDTO;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;
import com.genscript.gsscm.accounting.service.ArInvoiceService;
import com.genscript.gsscm.accounting.service.ArCustomerService;
import com.genscript.gsscm.accounting.service.ArShipmentLinesService;
import com.genscript.gsscm.accounting.service.ArShipmentService;
import com.genscript.gsscm.accounting.service.ArProductService;
import com.genscript.gsscm.accounting.service.ArServService;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.RequestApproveType;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.service.VendorService;
import com.genscript.gsscm.serv.dao.ServiceListBeanDao;
import com.genscript.gsscm.serv.entity.ServiceListBean;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.opensymphony.xwork2.ActionSupport;


@Results({
	@Result(name="customer_list",location="accounting/receive/customer_list.jsp"),
	@Result(name="shipment_list",location="accounting/receive/shipment_list.jsp"),
	@Result(name="order_list",location="accounting/receive/order_list.jsp"),
	@Result(name="invoice_list",location="accounting/receive/invoice_list_dlg.jsp"),
	@Result(name="vendor_list",location="accounting/payable/invoice/vendor_no_search.jsp"),
	@Result(name="serv_catalog_no_search",location="accounting/receive/serv_catalog_no_search.jsp"),
	@Result(name="catalog_no_search",location="accounting/receive/catalog_no_search.jsp"),
	@Result(name="ap_order_no_search",location="accounting/payable/invoice/aporder_list.jsp"),
	@Result(name="ar_order_no_search",location="accounting/receive/ar_order_no_list.jsp"),
	@Result(name="ap_invoice_list",location="accounting/payable/invoice/apinvoice_list_dlg.jsp")
	})
public class GenscriptAction extends ActionSupport {

	
private Page<CustomerBean> page = new Page<CustomerBean>(20);
	

@Autowired
private ArCustomerService arCustomerService;

@Autowired
private ArShipmentService arShipmentService;

@Autowired
private ArShipmentLinesService arShipmentLinesService;

	@Autowired
	private ArInvoiceService arInvoiceService;
	
	@Autowired
	ArInvoiceDao arInvoiceDao;
	@Autowired
	ServiceListBeanDao serviceListBeanDao;
	
	@Autowired
	private ArProductService arProductService;
	@Autowired
	private ArServService arServService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private OrderService orderService;
	@Autowired
	ApInvoiceDao apInvoiceDao;

	private Page<Shipment> shipmentPage = new Page<Shipment>();
	
	private Page<OrderNoDTO> shipmentLinesPage = new Page<OrderNoDTO>();
	
	private Page<ArInvoiceView> invoiceListPage = new Page<ArInvoiceView>();
	
	private Page<Vendor> vendorPage ;
	
	private CustomerBean customer;
	
	private String filter_EQI_custNo;
	
	private String filter_EQS_shipmentNo;
	
	private String filter_EQI_orderNo;

	private Integer filter_EQI_vendorNo;
	
	private Page<Product> ppage;
	private Page<ProductListBean> productPageBean;
	private Page<ServiceListBean> servicePageBean;
	private List<Integer> statusOfapproved;//
	public Map param;
	private Page<OrderMainBean> orderMainBeanPage;
	
	private Page<PurchaseOrder> purchaseOrderPage;
	
	private Page<ApInvoiceView> apinvoiceListPage;
	
	
	public Page<ApInvoiceView> getApinvoiceListPage() {
		return apinvoiceListPage;
	}

	public void setApinvoiceListPage(Page<ApInvoiceView> apinvoiceListPage) {
		this.apinvoiceListPage = apinvoiceListPage;
	}

	public Page<OrderMainBean> getOrderMainBeanPage() {
		return orderMainBeanPage;
	}

	public void setOrderMainBeanPage(Page<OrderMainBean> orderMainBeanPage) {
		this.orderMainBeanPage = orderMainBeanPage;
	}

	public Page<PurchaseOrder> getPurchaseOrderPage() {
		return purchaseOrderPage;
	}

	public void setPurchaseOrderPage(Page<PurchaseOrder> purchaseOrderPage) {
		this.purchaseOrderPage = purchaseOrderPage;
	}

	public List<Integer> getStatusOfapproved() {
		return statusOfapproved;
	}

	public void setStatusOfapproved(List<Integer> statusOfapproved) {
		this.statusOfapproved = statusOfapproved;
	}

	public Page<Product> getPpage() {
		return ppage;
	}

	public void setPpage(Page<Product> ppage) {
		this.ppage = ppage;
	}

	public Page<ProductListBean> getProductPageBean() {
		return productPageBean;
	}

	public void setProductPageBean(Page<ProductListBean> productPageBean) {
		this.productPageBean = productPageBean;
	}

	public Page<ServiceListBean> getServicePageBean() {
		return servicePageBean;
	}

	public void setServicePageBean(Page<ServiceListBean> servicePageBean) {
		this.servicePageBean = servicePageBean;
	}

	public Page<ArInvoiceView> getInvoiceListPage() {
		return invoiceListPage;
	}

	public void setInvoiceListPage(Page<ArInvoiceView> invoiceListPage) {
		this.invoiceListPage = invoiceListPage;
	}

	public Page<CustomerBean> getPage() {
		return page;
	}

	public void setPage(Page<CustomerBean> page) {
		this.page = page;
	}

	public Page<Shipment> getShipmentPage() {
		return shipmentPage;
	}

	public void setShipmentPage(Page<Shipment> shipmentPage) {
		this.shipmentPage = shipmentPage;
	}

	public Page<OrderNoDTO> getShipmentLinesPage() {
		return shipmentLinesPage;
	}

	public void setShipmentLinesPage(Page<OrderNoDTO> shipmentLinesPage) {
		this.shipmentLinesPage = shipmentLinesPage;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public String getFilter_EQI_custNo() {
		return filter_EQI_custNo;
	}

	public void setFilter_EQI_custNo(String filter_EQI_custNo) {
		this.filter_EQI_custNo = filter_EQI_custNo;
	}

	public String getFilter_EQS_shipmentNo() {
		return filter_EQS_shipmentNo;
	}

	public void setFilter_EQS_shipmentNo(String filter_EQS_shipmentNo) {
		this.filter_EQS_shipmentNo = filter_EQS_shipmentNo;
	}

	public String getFilter_EQI_orderNo() {
		return filter_EQI_orderNo;
	}

	public void setFilter_EQI_orderNo(String filter_EQI_orderNo) {
		this.filter_EQI_orderNo = filter_EQI_orderNo;
	}
	

	
	public Page<Vendor> getVendorPage() {
		return vendorPage;
	}

	public void setVendorPage(Page<Vendor> vendorPage) {
		this.vendorPage = vendorPage;
	}
	

	public Integer getFilter_EQI_vendorNo() {
		return filter_EQI_vendorNo;
	}

	public void setFilter_EQI_vendorNo(Integer filter_EQI_vendorNo) {
		this.filter_EQI_vendorNo = filter_EQI_vendorNo;
	}

	
	
	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}

	/**
	 * 查询customer表所有数据
	 * @return
	 */
	public String customerlist () {
		// 获得分页请求相关数据：如第几页
		PagerUtil<CustomerBean> pagerUtil = new PagerUtil<CustomerBean>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("custNo");
			page.setOrder(Page.DESC);
		}
		page.setPageSize(5);
		// end of modify 1;
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		List<PropertyFilter> orderNofilters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest(), "searcher_");
		if (filters == null && orderNofilters == null) {
			page = arCustomerService.searchCustomer(page);
		} else {	
			page = arCustomerService.searchCustomer(page, filters);
		}
		// modify by wangsf 2010-08-19 10:55 2. begin
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		// end of modify 2;
		return "customer_list";
	}
	
	/**
	 * 根据customerID查询相关联的shipment
	 * @return
	 */
	public String shipmentlist () {
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if (filters != null) {
			// 获得分页请求相关数据：如第几页
			PagerUtil<Shipment> pagerUtil = new PagerUtil<Shipment>();
			shipmentPage = pagerUtil.getRequestPage();
			if (!shipmentPage.isOrderBySetted()) {
				shipmentPage.setOrderBy("shipmentNo");
				shipmentPage.setOrder(Page.DESC);
			}
			shipmentPage.setPageSize(5);
			shipmentPage = arShipmentService.searchshipmentsByCustmomerNo(shipmentPage, filters);
		}
		// modify by wangsf 2010-08-19 10:55 2. begin
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", shipmentPage);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		// end of modify 2;
		return "shipment_list";
	}
	
	/**
	 * 根据shipmentID查询相关联的order
	 * @return
	 */
	public String orderlist () {
		try {
			List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
			if (filters != null) {
				// 获得分页请求相关数据：如第几页
				PagerUtil<OrderNoDTO> pagerUtil = new PagerUtil<OrderNoDTO>();
				shipmentLinesPage = pagerUtil.getRequestPage();
				if (!shipmentLinesPage.isOrderBySetted()) {
	//				shipmentLinesPage.setOrderBy("a.order.orderNo");
					shipmentLinesPage.setOrderBy("a.order.orderNo");
					shipmentLinesPage.setOrder(Page.DESC);
				}
				shipmentLinesPage.setPageSize(5);
				String custNo = Struts2Util.getParameter("filter_EQS_custNo");
				try {
					shipmentLinesPage = arShipmentLinesService.searchShipmentLinesByShipmentNo(shipmentLinesPage, filter_EQS_shipmentNo, filter_EQI_orderNo,custNo);
				} catch (Exception ex) {
					shipmentLinesPage.setTotalCount(0l);
					shipmentLinesPage.setPageNo(1);
				}
			}
			// modify by wangsf 2010-08-19 10:55 2. begin
			// 把分页相关数据放入request作用域内
			ServletActionContext.getRequest().setAttribute("pagerInfo", shipmentLinesPage);
			param = Tools.buildMap(ServletActionContext.getRequest());
			ServletActionContext.getRequest().setAttribute("param", param);
			// end of modify 2
		} catch (Exception ex) {
			return "order_list";
		}
		return "order_list";
	}
	
	
	public String invoicelist(){
		PagerUtil<ArInvoiceView> pagerUtil = new PagerUtil<ArInvoiceView>();
		invoiceListPage = pagerUtil.getRequestPage();
		invoiceListPage.setPageSize(5);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ArInvoiceView> invoiceList = null;
		long total = 0;
		try {
			invoiceList = arInvoiceDao.list(invoiceListPage,m);
			total = this.arInvoiceDao.getTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		invoiceListPage.setTotalCount(total);
		invoiceListPage.setResult(invoiceList);
		Struts2Util.getRequest().setAttribute("pagerInfo", invoiceListPage);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		return "invoice_list";
	}
	
	/**
	 * 查询所有vendor
	 * @return
	 */
	public String vendorList(){
		PagerUtil<Vendor> pagerUtil = new PagerUtil<Vendor>();
		vendorPage = pagerUtil.getRequestPage();
		vendorPage.setPageSize(5);
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(Struts2Util
				.getRequest());
		vendorPage = this.vendorService.searchVendor(vendorPage, filters);
		Struts2Util.getRequest().setAttribute("pagerInfo", vendorPage);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		return "vendor_list";
	}
	
	public String catalogNoSearch () {
		//productService.getProductList(page, filters);
		// 获得分页请求相关数据：如第几页
		PagerUtil<ProductListBean> pagerUtil = new PagerUtil<ProductListBean>();
		productPageBean = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (productPageBean.getPageSize() == null
				|| productPageBean.getPageSize().intValue() < 1) {
			productPageBean.setPageSize(10);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if (!productPageBean.isOrderBySetted()) {
			productPageBean.setOrderBy("modifyDate");
			productPageBean.setOrder(Page.DESC);
		}
		productPageBean = this.arProductService.searchProductList(productPageBean,filters);
//		this.statusOfapproved = this.arProductService.getApprovedRequestListByTableTypeStatus(RequestApproveType.Product.name());
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(productPageBean);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		return "catalog_no_search";
	}
	
	public String purchaseOrderNoSearch () {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<PurchaseOrder> pagerUtil = new PagerUtil<PurchaseOrder>();
			purchaseOrderPage = pagerUtil.getRequestPage();
			purchaseOrderPage.setPageSize(5);
			List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
			purchaseOrderPage = orderService.searchOrderByVendorNo(purchaseOrderPage,filters, filter_EQI_vendorNo);
			// 把分页相关数据放入request作用域内
			PageDTO pagerInfo = pagerUtil.formPage(purchaseOrderPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
			param = Tools.buildMap(ServletActionContext.getRequest());
			ServletActionContext.getRequest().setAttribute("param", param);
			// end of modify 2;
		} catch (Exception ex) {
			return "ap_order_no_search";
		}
		return "ap_order_no_search";
	}
	
	public String servCatalogNoSearch () {
		// 获得分页请求相关数据：如第几页
		PagerUtil<ServiceListBean> pagerUtil = new PagerUtil<ServiceListBean>();
		servicePageBean = pagerUtil.getRequestPage();
		// 设置默认每页显示记录条数
		if (servicePageBean.getPageSize() == null
				|| servicePageBean.getPageSize().intValue() < 1) {
			servicePageBean.setPageSize(10);
		}
		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
		if (!servicePageBean.isOrderBySetted()) {
			servicePageBean.setOrderBy("modifyDate");
			servicePageBean.setOrder(Page.DESC);
		}
		servicePageBean = this.arServService.searchServiceList(servicePageBean,filters);
//		this.statusOfapproved = this.arServService.getApprovedRequestListByTableTypeStatus(RequestApproveType.Service.name());
//		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(servicePageBean);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		return "serv_catalog_no_search";
	}

//	public String vendorNoSearch () {
//		List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
//		PagerUtil<Vendor> pagerUtil = new PagerUtil<Vendor>();
//		vendorPage = pagerUtil.getRequestPage();
//		// 设置默认每页显示记录条数
//		if (vendorPage.getPageSize() == null
//				|| vendorPage.getPageSize().intValue() < 1) {
//			vendorPage.setPageSize(1);
//		}
//		if (!vendorPage.isOrderBySetted()) {
//			vendorPage.setOrderBy("vendorNo");
//			vendorPage.setOrder(Page.ASC);
//		}
//		vendorPage = vendorService.searchVendor(vendorPage, filters);
//		PageDTO pagerInfo = pagerUtil.formPage(vendorPage);
//		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
//		return "vendor_no_search";
//	}
	
	public String orderNoSearch () {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<OrderMainBean> pagerUtil = new PagerUtil<OrderMainBean>();
			orderMainBeanPage = pagerUtil.getRequestPage();
			List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
			orderMainBeanPage = orderService.searchOrderByCustomerNo(orderMainBeanPage, filters);
			// 把分页相关数据放入request作用域内
			PageDTO pagerInfo = pagerUtil.formPage(orderMainBeanPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
			param = Tools.buildMap(ServletActionContext.getRequest());
			ServletActionContext.getRequest().setAttribute("param", param);
		} catch (Exception ex) {
			if (orderMainBeanPage == null) {
				orderMainBeanPage = new Page<OrderMainBean>();
			}
			orderMainBeanPage.setTotalCount(0l);
			if (orderMainBeanPage.getResult() == null) {
				orderMainBeanPage.setResult(new ArrayList<OrderMainBean>());
			}
			PagerUtil<OrderMainBean> pagerUtil = new PagerUtil<OrderMainBean>();
			// 把分页相关数据放入request作用域内
			PageDTO pagerInfo = pagerUtil.formPage(orderMainBeanPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
			param = Tools.buildMap(ServletActionContext.getRequest());
			ServletActionContext.getRequest().setAttribute("param", param);
			return "ar_order_no_search";
		}
		return "ar_order_no_search";
	}
	
	
	public String ApInvoiceList(){
		PagerUtil<ApInvoiceView> pagerUtil = new PagerUtil<ApInvoiceView>();
		apinvoiceListPage = pagerUtil.getRequestPage();
		apinvoiceListPage.setPageSize(5);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ApInvoiceView> invoiceList = null;
		long total = 0;
		try {
			invoiceList = apInvoiceDao.list(apinvoiceListPage,m);
			total = this.apInvoiceDao.getTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		apinvoiceListPage.setTotalCount(total);
		apinvoiceListPage.setResult(invoiceList);
		Struts2Util.getRequest().setAttribute("pagerInfo", apinvoiceListPage);
		param = Tools.buildMap(ServletActionContext.getRequest());
		ServletActionContext.getRequest().setAttribute("param", param);
		return "ap_invoice_list";
	}
	
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page){
		return serviceListBeanDao.findPage(page);
	}
}

