package com.genscript.gsscm.tools.web;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.purchase.dto.PurchaseOrderDTO;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.ItemBeanDTO;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.shipment.dto.ViewPackingSlipDTO;
import com.genscript.gsscm.shipment.service.ShippingService;
import com.genscript.gsscm.tools.dto.ProductServiceDto;
import com.genscript.gsscm.tools.dto.WarehousingShipmentDto;
import com.genscript.gsscm.tools.service.WarehousingShipmentService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.genscript.gsscm.serv.entity.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/20/11
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Results( {
		@Result(name = "warehousing_shipment", location = "tools/warehousing_shipment.jsp"),
		@Result(name = "showProductItemPrice", location = "tools/product_Service_Item.jsp"),
		@Result(name = "searchPurchaseOrderPrint", location = "tools/nj_shipping_management.jsp"),
		@Result(name = "viewPackingSlip", location = "shipping/view_packing_slip.jsp"),
		@Result(name = "searchPurchaseOrderItemPrint", location = "tools/nj_shipping_management_item.jsp")
		
})
public class WarehousingShipmentAction extends ActionSupport{

    private WarehousingShipmentDto warehousingShipmentDto;
    @Autowired
	private PublicService publicService;
    @Autowired
    private SpecDropDownListDao specDropDownListDao;
    @Autowired
    private WarehousingShipmentService warehousingShipmentService;
    @Autowired
    private PurchaseService purchaseService;
    
    private String searchClass;
    private String propertyValue1;
	private String searchOperator;
    private String searchType;
	private String propertyName;
    private String catalogNoList;
    private Page<PurchaseOrderDTO> page;
    private List<PurchaseOrderItem> purchaseOrderItemList;
    private List<ViewPackingSlipDTO> viewlist;
    private List<ProductServiceDto> productServiceDto;
    @Autowired
	private QuoteService quoteService;
    @Autowired
	private ErpSalesOrderService erpSalesOrderService;
    @Autowired
	private CustomerService customerService;
    private List<ItemBeanDTO> itemBeanDTOList;
    @Autowired
	private ServService servService;
    @Autowired
	private DozerBeanMapper dozer;
    @Autowired
    private ServiceDao serviceDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
	private ExceptionService exceptionUtil;
    @Autowired
	private ShippingService shippingService;
    

    private int custNo;

    public String calculate() {
        Map<String, Object> rt = new HashMap<String, Object>();
        try {
            warehousingShipmentDto = warehousingShipmentService.getRate(warehousingShipmentDto, productServiceDto);
            rt.put("deliveryDate", warehousingShipmentDto.getDeliveryDate());
            rt.put("carrierRate", warehousingShipmentDto.getCarrierRate());
            rt.put("genScriptRate", warehousingShipmentDto.getGenScriptRate());
        } catch (Exception ex) {
            if (ex instanceof BussinessException) {
                BussinessException bussinessException = (BussinessException) ex;
                rt.put("hasException", "Y");
                rt.put("exception", bussinessException.getReplaceParamValues().get(0));
            } else {
                WSException exDTO = exceptionUtil.getExceptionDetails(ex);
                exceptionUtil.logException(exDTO, this.getClass(), ex,
                        new Exception().getStackTrace()[0].getMethodName(),
                        "INTF0203", SessionUtil.getUserId());
                rt.put("hasException", "Y");
                rt.put("exception", exDTO.getMessage());
            }
        }
        Struts2Util.renderJson(rt);
        return null;
    }

    public String input(){
        return "warehousing_shipment";
    }
    
    public String searchPurchaseOrderPrint(){
    	PagerUtil<PurchaseOrderDTO> pagerUtil = new PagerUtil<PurchaseOrderDTO>();
    	page = pagerUtil.getRequestPage();
		page.setPageSize(20);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("modifyDate");
			page.setOrder(Page.DESC);
		}
		page = this.purchaseService.searchPurchaseOrderOfPrint(page);
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
    	return "searchPurchaseOrderPrint";
    }
    
    public String searchPurchaseOrderItemPrint(){
    	String orderNo = (String) Struts2Util.getParameter("orderNo");
    	String srcSoNo = (String) Struts2Util.getParameter("srcSoNo");
    	if(orderNo!=null&&!orderNo.equals("")){
    		this.purchaseOrderItemList=this.purchaseService.searchPurchaseOrderItemOfPrint(Integer.valueOf(orderNo));
    	}else{
    		this.purchaseOrderItemList = new ArrayList<PurchaseOrderItem>();
    	}
    	Struts2Util.getRequest().setAttribute("orderNo", orderNo);
    	Struts2Util.getRequest().setAttribute("srcSoNo", srcSoNo);
    	return "searchPurchaseOrderItemPrint";
    }
    
    public String viewPackSlip(){
    	String orderNo = (String) Struts2Util.getParameter("orderNo");
    	String itemNo = (String) Struts2Util.getParameter("itemNo");
    	this.viewlist = this.shippingService.ViewPackingSlip(orderNo, itemNo);
    	return "viewPackingSlip";
    }
    

    /**
	 * Add New Item时所调用方法
	 *
	 * @return
	 * @throws Exception
	 */
	public String showProductItemPrice() throws Exception {
		String propType;
		if (StringUtils.isBlank(searchClass)) {
			return "showProductItemPrice";
		}
		Map<String, String> filterMap = new HashMap<String, String>();
		propertyValue1 = StringUtils.strip(propertyValue1);
		if (!StringUtils.isBlank(propertyValue1)) {
			if (propertyName.equals("orderNo") || propertyName.equals("quoteNo")) {
				propType = "I";
			} else {
				propType = "S";
			}
			StringBuilder srchStr = new StringBuilder();
			srchStr.append(searchOperator).append(propType).append("_").append(propertyName);
			filterMap.put(srchStr.toString(), propertyValue1);
		}
		if (searchClass.equals(ItemSearchType.PRODUCT_ITEM.value())) {
			PagerUtil<CustomerPriceBean> pagerUtil = new PagerUtil<CustomerPriceBean>();
			Page<CustomerPriceBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("custNo");
				page.setOrder(Page.DESC);
			}
			if (StringUtils.isBlank(propertyValue1)) {
				page = quoteService.searchProductItemPrice(page, custNo);
			} else {
				filterMap.put("EQI_custNo", custNo + "");
				filterMap.put("EQS_status", StatusType.ACTIVE.value());
				page = quoteService.searchProductItemPrice(page, filterMap,null);
			}
			List<CustomerPriceBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (CustomerPriceBean customerPriceBean : priceList) {
				ItemBeanDTO itemBeanDTO = dozer.map(customerPriceBean, ItemBeanDTO.class);
				String catalogNo = itemBeanDTO.getCatalogNo();
				BigDecimal unitInStock = erpSalesOrderService.getPartStorageNumber(catalogNo);
				if(unitInStock == null){
					itemBeanDTO.setUnitInStock(null);
				}else{
					itemBeanDTO.setUnitInStock(unitInStock.intValue());
				}
//				itemBeanDTO.setUnitPrice(unitPrice);
				itemBeanDTOList.add(itemBeanDTO);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.SERVICE_ITEM.value())) {
			CustomerDTO customerDTO = customerService.getCustomerBase(custNo);
			if(customerDTO != null ){
				if (customerDTO.getPriceCatalogId() != null && !("").equals(customerDTO.getPriceCatalogId().trim())) {
					filterMap.put("EQS_catalogId", customerDTO.getPriceCatalogId());
				} else {
					filterMap.put("EQS_status", StatusType.ACTIVE.value());
					filterMap.put("EQS_catalogStatus", StatusType.ACTIVE.value());
					filterMap.put("EQS_baseCatalog", QuoteItemStatusType.Y.value());
				}
			} else {
				filterMap.put("EQS_status", StatusType.ACTIVE.value());
				filterMap.put("EQS_catalogStatus", StatusType.ACTIVE.value());
				filterMap.put("EQS_baseCatalog", QuoteItemStatusType.Y.value());
			}
			PagerUtil<ServiceItemBean> pagerUtil = new PagerUtil<ServiceItemBean>();
			Page<ServiceItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			page = servService.searchItemPrice(page, filterMap);
			List<ServiceItemBean> serviceItemBeanList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
				ItemBeanDTO itemBean = dozer.map(serviceItemBean, ItemBeanDTO.class);
				itemBean.setUpSymbol(serviceItemBean.getSymbol());
				itemBeanDTOList.add(itemBean);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		}
		return "showProductItemPrice";
	}

    public String getPSData(){
        List list = new ArrayList();
        if(catalogNoList!=null && catalogNoList.split(",")!=null && catalogNoList.split(",").length > 0){
            if (searchClass.equals(ItemSearchType.SERVICE_ITEM.value())) {
                List serviceList = serviceDao.createQuery("from Service where catalogNo in (" + catalogNoList + ")").list();
                for (Object serviceTemp : serviceList) {
                    Service service = (Service)serviceTemp;
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("catalogNo", service.getCatalogNo());
                    map.put("clsId", ""+service.getServiceClsId());
                    list.add(map);
                }
            }else if (searchClass.equals(ItemSearchType.PRODUCT_ITEM.value())){
                List productList = productDao.createQuery("from Product where catalogNo in (" + catalogNoList + ")").list();
                for (Object productTemp : productList) {
                    Product product = (Product)productTemp;
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("catalogNo", product.getCatalogNo());
                    map.put("clsId", ""+product.getProductClsId());
                    list.add(map);
                }
            }
        }
        Struts2Util.renderJson(list);
        return null;
    }

    public WarehousingShipmentDto getWarehousingShipmentDto() {
        return warehousingShipmentDto;
    }

    public void setWarehousingShipmentDto(WarehousingShipmentDto warehousingShipmentDto) {
        this.warehousingShipmentDto = warehousingShipmentDto;
    }

    public List<CountryDTO> getCountry(){
		return this.publicService.getCountryList();
	}

    public List<DropDownDTO> getShipCarriesList(){
        return specDropDownListDao.getSpecDropDownList("SHIP_CARRIERS");
    }

    public List<DropDownDTO> getShipMethodList(){
        return specDropDownListDao.getSpecDropDownList("SHIP_METHOD", null);
    }


    public String getSearchClass() {
        return searchClass;
    }

    public void setSearchClass(String searchClass) {
        this.searchClass = searchClass;
    }

    public String getSearchOperator() {
        return searchOperator;
    }

    public void setSearchOperator(String searchOperator) {
        this.searchOperator = searchOperator;
    }

    public String getPropertyValue1() {
        return propertyValue1;
    }

    public void setPropertyValue1(String propertyValue1) {
        this.propertyValue1 = propertyValue1;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getCustNo() {
        return custNo;
    }

    public void setCustNo(int custNo) {
        this.custNo = custNo;
    }

    public List<ItemBeanDTO> getItemBeanDTOList() {
        return itemBeanDTOList;
    }

    public void setItemBeanDTOList(List<ItemBeanDTO> itemBeanDTOList) {
        this.itemBeanDTOList = itemBeanDTOList;
    }

    public String getCatalogNoList() {
        return catalogNoList;
    }

    public void setCatalogNoList(String catalogNoList) {
        this.catalogNoList = catalogNoList;
    }

    public List<ProductServiceDto> getProductServiceDto() {
        return productServiceDto;
    }

    public void setProductServiceDto(List<ProductServiceDto> productServiceDto) {
        this.productServiceDto = productServiceDto;
    }



	public Page<PurchaseOrderDTO> getPage() {
		return page;
	}

	public void setPage(Page<PurchaseOrderDTO> page) {
		this.page = page;
	}

	public List<PurchaseOrderItem> getPurchaseOrderItemList() {
		return purchaseOrderItemList;
	}

	public void setPurchaseOrderItemList(
			List<PurchaseOrderItem> purchaseOrderItemList) {
		this.purchaseOrderItemList = purchaseOrderItemList;
	}

	public List<ViewPackingSlipDTO> getViewlist() {
		return viewlist;
	}

	public void setViewlist(List<ViewPackingSlipDTO> viewlist) {
		this.viewlist = viewlist;
	}

}