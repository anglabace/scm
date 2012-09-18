package com.genscript.gsscm.quoteorder.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Coupon;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.customer.service.CouponService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.OrderItemsPriceBean;
import com.genscript.gsscm.order.entity.OrderTemplateItemBean;
import com.genscript.gsscm.order.entity.ServiceOrderTemplateBean;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.orf.dto.Refseq2orfpriceDTO;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.dto.ProductRelationDTO;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteItemsPriceBean;
import com.genscript.gsscm.quote.entity.QuoteTemplateItemBean;
import com.genscript.gsscm.quote.entity.ServiceQuoteTemplateBean;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.ItemBeanDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceIntermediateDao;
import com.genscript.gsscm.serv.dto.ServiceRelationDTO;
import com.genscript.gsscm.serv.dto.ServiceRelationItemDTO;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = "showProductItemPriceForm", location = "quoteorder/quorder_product_item_price.jsp"),
		@Result(name = "showProductProductRelationForm", location = "quoteorder/quorder_item_select.jsp"),
		@Result(name = "catalog_detail", location = "quoteorder/quorder_item_catalog_detail.jsp"),
		@Result(name = "view_coupon", location = "quoteorder/view_coupon.jsp"),
		@Result(name="show_orf_clone_list", location="quoteorder/quorder_orf_clone_list.jsp")
		})
public class QuOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private int custNo;
	private String quorderNo;
	private String codeType;
	private String searchClass;
	private String searchType;
	private String propertyName;
	private String propertyValue1;
	private String searchOperator;
	private Integer serviceId;
	private List<ItemBeanDTO> itemBeanDTOList;
	private Map<String, String> itemSearchTypes;
	@SuppressWarnings("rawtypes")
	private List productTemplateList;
	@SuppressWarnings("rawtypes")
	private List serviceTemplateList;
	private String name;
	private int moreDetailFlag;
	private Integer productId;
	@SuppressWarnings("rawtypes")
	private List itemList;
	private String prmtCode;
	private String sessOrderNo;
	private String sessQuoteNo;
	private String erpCheckStockException;
	// 用于显示catalog detail
	private String catalogId;
	private CatalogDTO catalog;
	// 用于添加mutagenesis
	private String parentId;
	private String geneCatalogNo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ServService servService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceIntermediateDao serviceIntermediateDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	
	//coupon
	private Page<Coupon> couponPage;
	private Integer couponId;
	//ORF Clone
	private List<Refseq2orfpriceDTO> refseq2orfpriceList;

	private static final long serialVersionUID = -7078085189539735075L;

	/**
	 * Add New Item时所调用方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showProductItemPriceForm() throws Exception {
		if (codeType.equalsIgnoreCase("order")) {
			sessOrderNo = quorderNo;
		} else {
			sessQuoteNo = quorderNo;
		}
		String propType;
		if (StringUtils.isBlank(searchClass)) {
			return "showProductItemPriceForm";
		}
		Integer userId = SessionUtil.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		propertyValue1 = StringUtils.strip(propertyValue1);
		if (!StringUtils.isBlank(propertyValue1)) {
			if (propertyName.equals("orderNo")
					|| propertyName.equals("quoteNo")) {
				propType = "I";
			} else {
				propType = "S";
			}
			StringBuilder srchStr = new StringBuilder();
			srchStr.append(searchOperator).append(propType).append("_").append(
					propertyName);
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
				page = quoteService.searchProductItemPrice(page, filterMap,
						null);
			}
			List<CustomerPriceBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			String customerCompany = customerService.getCustomerCompany(custNo, null);
			for (CustomerPriceBean customerPriceBean : priceList) {
				ItemBeanDTO itemBeanDTO = dozer.map(customerPriceBean, ItemBeanDTO.class);
				String catalogNo = itemBeanDTO.getCatalogNo();
				BigDecimal unitInStock = null;
				try {
					unitInStock = erpSalesOrderService.getPartStorageNumber(catalogNo, customerCompany);
				} catch (Exception be) {
					unitInStock = null;
					erpCheckStockException = "Could not retrieve inventory data.";
				}
				if(unitInStock == null){
					itemBeanDTO.setUnitInStock(null);
				}else{
					itemBeanDTO.setUnitInStock(unitInStock.intValue());
				}
//				itemBeanDTO.setUnitPrice(unitPrice);
				itemBeanDTOList.add(itemBeanDTO);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.TEMPLATE_ITEM.value())
				&& codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			PagerUtil<OrderTemplateItemBean> pagerUtil = new PagerUtil<OrderTemplateItemBean>();
			Page<OrderTemplateItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("custNo");
				page.setOrder(Page.DESC);
			}
			filterMap.put("EQI_custNo", custNo + "");
			filterMap.put("EQI_owner", userId.toString());
			page = quoteService.searchOrderTemplateItemPrice(page, filterMap, null);
			List<OrderTemplateItemBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (OrderTemplateItemBean templateItemBean : priceList) {
				ItemBeanDTO itemBeanDTO = dozer.map(templateItemBean, ItemBeanDTO.class);
				itemBeanDTO.setSrcOrderNo(null);
				itemBeanDTO.setTemplateType(ItemSearchType.TEMPLATE_ITEM.value());
				itemBeanDTOList.add(itemBeanDTO);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.SERVICE_TEMPLATE_ITEM.value())
				&& codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			PagerUtil<ServiceOrderTemplateBean> pagerUtil = new PagerUtil<ServiceOrderTemplateBean>();
			Page<ServiceOrderTemplateBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("itemNo");
				page.setOrder(Page.ASC);
			}
			filterMap.put("EQI_custNo", custNo + "");
			filterMap.put("EQI_owner", userId.toString());
			PropertyFilter filter = new PropertyFilter("NULLI_parentItemId", null);
			page = quoteService.searchServiceOrderTemplateItem(page, filterMap, filter);
			List<ServiceOrderTemplateBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceOrderTemplateBean templateItemBean : priceList) {
				ItemBeanDTO itemBeanDTO = dozer.map(templateItemBean, ItemBeanDTO.class);
				itemBeanDTO.setTemplateType(ItemSearchType.SERVICE_TEMPLATE_ITEM.value());
				itemBeanDTOList.add(itemBeanDTO);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.TEMPLATE_ITEM.value())
				&& codeType.equalsIgnoreCase(TemplateType.QUOTE.value())) {
			PagerUtil<QuoteTemplateItemBean> pagerUtil = new PagerUtil<QuoteTemplateItemBean>();
			Page<QuoteTemplateItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("custNo");
				page.setOrder(Page.DESC);
			}
			filterMap.put("EQI_custNo", custNo + "");
			filterMap.put("EQI_owner", userId.toString());
			page = quoteService.searchQuoteTemplateItemPrice(page, filterMap, null);
			List<QuoteTemplateItemBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (QuoteTemplateItemBean templateItemBean : priceList) {
				ItemBeanDTO itemBeanDTO = dozer.map(templateItemBean,
						ItemBeanDTO.class);
				itemBeanDTO.setSrcQuoteNo(null);
				itemBeanDTO.setTemplateType(ItemSearchType.TEMPLATE_ITEM.value());
				itemBeanDTOList.add(itemBeanDTO);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.SERVICE_TEMPLATE_ITEM.value())
				&& codeType.equalsIgnoreCase(TemplateType.QUOTE.value())) {
			PagerUtil<ServiceQuoteTemplateBean> pagerUtil = new PagerUtil<ServiceQuoteTemplateBean>();
			Page<ServiceQuoteTemplateBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("itemNo");
				page.setOrder(Page.ASC);
			}
			filterMap.put("EQI_custNo", custNo + "");
			filterMap.put("EQI_owner", userId.toString());
			PropertyFilter filter = new PropertyFilter("NULLI_parentItemId", null);
			page = quoteService.searchServiceQuoteTemplateItem(page, filterMap, filter);
			List<ServiceQuoteTemplateBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceQuoteTemplateBean templateItemBean : priceList) {
				ItemBeanDTO itemBeanDTO = dozer.map(templateItemBean, ItemBeanDTO.class);
				itemBeanDTO.setTemplateType(ItemSearchType.SERVICE_TEMPLATE_ITEM.value());
				itemBeanDTOList.add(itemBeanDTO);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.QUOTE_ITEM.value())) {
			PagerUtil<QuoteItemsPriceBean> pagerUtil = new PagerUtil<QuoteItemsPriceBean>();
			Page<QuoteItemsPriceBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("custNo");
				page.setOrder(Page.DESC);
			}
			filterMap.put("EQI_custNo", custNo + "");
			filterMap.put("EQS_status", StatusType.ACTIVE.value());
			page = quoteService.searchQuoteItemPrice(page, filterMap, null);
			List<QuoteItemsPriceBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (QuoteItemsPriceBean quoteItemBean : priceList) {
				itemBeanDTOList
						.add(dozer.map(quoteItemBean, ItemBeanDTO.class));
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.ORDER_ITEM.value())) {
			PagerUtil<OrderItemsPriceBean> pagerUtil = new PagerUtil<OrderItemsPriceBean>();
			Page<OrderItemsPriceBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("custNo");
				page.setOrder(Page.DESC);
			}
			filterMap.put("EQI_custNo", custNo + "");
			filterMap.put("EQS_status", StatusType.ACTIVE.value());
			page = quoteService.searchOrderItemPrice(page, filterMap, null);
			List<OrderItemsPriceBean> priceList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (OrderItemsPriceBean quoteItemBean : priceList) {
				itemBeanDTOList
						.add(dozer.map(quoteItemBean, ItemBeanDTO.class));
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.SERVICE_ITEM.value())) {
			CustomerDTO customerDTO = customerService.getCustomerBase(custNo);
			if(customerDTO != null ){
				if (customerDTO.getPriceCatalogId() != null && !("").equals(customerDTO.getPriceCatalogId().trim())) {
					filterMap.put("EQS_catalogId", customerDTO.getPriceCatalogId());
				} else { 
					filterMap.put("EQS_catalogStatus", StatusType.ACTIVE.value());
					filterMap.put("EQS_baseCatalog", QuoteItemStatusType.Y.value());
				}
			} else {
				filterMap.put("EQS_catalogStatus", StatusType.ACTIVE.value());
				filterMap.put("EQS_baseCatalog", QuoteItemStatusType.Y.value());
			}
			filterMap.put("EQS_status", StatusType.ACTIVE.value());
			PagerUtil<ServiceItemBean> pagerUtil = new PagerUtil<ServiceItemBean>();
			Page<ServiceItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(100);
			page = servService.searchItemPrice(page, filterMap);
			List<ServiceItemBean> serviceItemBeanList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
				ItemBeanDTO itemBean = dozer.map(serviceItemBean,
						ItemBeanDTO.class);
				itemBean.setUpSymbol(serviceItemBean.getSymbol());
				itemBeanDTOList.add(itemBean);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else if (searchClass.equals(ItemSearchType.SERVICE_ASSOCIATED_ITEM
				.value())) {
			PagerUtil<ServiceItemBean> pagerUtil = new PagerUtil<ServiceItemBean>();
			Page<ServiceItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			page = servService.searchAssociatedItem(page, filterMap, serviceId);
			List<ServiceItemBean> serviceItemBeanList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
				ItemBeanDTO itemBean = dozer.map(serviceItemBean,
						ItemBeanDTO.class);
				itemBean.setUpSymbol(serviceItemBean.getSymbol());
				itemBeanDTOList.add(itemBean);
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		}
		return "showProductItemPriceForm";
	}

	public String getPreImmuneItem() throws Exception {
		String catalogNo = Struts2Util.getParameter("catalogNo");
		com.genscript.gsscm.serv.entity.Service serv = serviceDao.findUniqueBy("catalogNo", catalogNo);
		if(serv != null){
			Integer clsId = serv.getServiceClsId();
			Integer serviceId = serv.getServiceId();
			if(clsId == 11 || clsId == 28){
				ServiceIntermediate servIntmd = serviceIntermediateDao.getIntmd(serviceId, "PRE-IMMUNE");
				if(servIntmd != null){
					String intmdCatalogNo = servIntmd.getIntmdCatalogNo();
					
					Map<String, String> filterMap = new HashMap<String, String>();
					filterMap.put("EQS_catalogNo", intmdCatalogNo);
					PagerUtil<ServiceItemBean> pagerUtil = new PagerUtil<ServiceItemBean>();
					Page<ServiceItemBean> page = pagerUtil.getRequestPage();
					page.setPageSize(10);
					page = servService.searchItemPrice(page, filterMap);
					List<ServiceItemBean> serviceItemBeanList = page.getResult();
					itemBeanDTOList = new ArrayList<ItemBeanDTO>();
					for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
						ItemBeanDTO itemBean = dozer.map(serviceItemBean,
								ItemBeanDTO.class);
						itemBean.setUpSymbol(serviceItemBean.getSymbol());
						itemBeanDTOList.add(itemBean);
					}
//					ServiceItemBean bean = serviceItemBeanList.get(0);
//					StringBuilder sb = new StringBuilder();
//					sb.append("name:").append(bean.getName()).append(",type:").append(bean.getType()).append(",taxStatus:,scheduleShip:,fullDesc:,comment:,clsId:,clsName:,sellingNote:,catalogId:").append(bean.getCatalogId()).append(",catalogName:");
					Struts2Util.renderJson(itemBeanDTOList.get(0));
				}
			}else{
				Struts2Util.renderJson("None");
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String testProductProductRelationForm() throws Exception {
        String qtys = ServletActionContext.getRequest().getParameter("qty")==null? "0" : ServletActionContext.getRequest().getParameter("qty");
        String productIds = ServletActionContext.getRequest().getParameter("productIds");
		if (codeType.equalsIgnoreCase("order")) {
			sessOrderNo = quorderNo;
		} else {
			sessQuoteNo = quorderNo;
		}
		if (moreDetailFlag == 1) {
			PagerUtil<ServiceItemBean> pagerUtil = new PagerUtil<ServiceItemBean>();
			Page<ServiceItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("catalogNo");
				page.setOrder(Page.DESC);
			}
			Map<String, String> filterMap = new HashMap<String, String>();
			serviceId = this.servService.getServiceIdByCatalogNo(geneCatalogNo); 
			page = servService.searchAssociatedItem(page, filterMap, serviceId);
			if (page == null) {
				Struts2Util.renderText("true");
				return null;
			}
			List<ServiceItemBean> serviceItemBeanList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
				ItemBeanDTO itemBean = dozer.map(serviceItemBean,
						ItemBeanDTO.class);
				itemBean.setUpSymbol(serviceItemBean.getSymbol());
				itemBeanDTOList.add(itemBean);
			}
			if (itemBeanDTOList != null && !itemBeanDTOList.isEmpty()) {
				itemList = new ArrayList<ServiceRelationItemDTO>();
				for (ItemBeanDTO dto : itemBeanDTOList) {
					ServiceRelationItemDTO serviceItemDTO = new ServiceRelationItemDTO();
					serviceItemDTO.setServiceId(dto.getServiceId());
					serviceItemDTO.setItemName(dto.getName());
					serviceItemDTO.setCatalogNo(dto.getCatalogNo());
					serviceItemDTO.setSize(dto.getSize());
					serviceItemDTO.setUom(dto.getUom());
					serviceItemDTO.setQtyUom(dto.getQtyUom());
					serviceItemDTO.setType(dto.getType());
					itemList.add(serviceItemDTO);
				}
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else {
			if (searchClass.equals(ItemSearchType.SERVICE_ITEM.value())) {
				ServiceItemBean serviceItemBean = new ServiceItemBean();
				serviceItemBean.setName(name);
				serviceItemBean.setServiceId(productId);
				ServiceRelationDTO serviceRelationDTO = servService
						.getServiceRelations(serviceItemBean);
				itemList = serviceRelationDTO.getServiceRelationItemDTOList();
			} else {
                String result = isFreeSample(quorderNo, productIds, custNo, codeType, qtys);
                if(!"".equals(result)){
                    Struts2Util.renderText(result);
                    return null;
                }
				CustomerPriceBean customerPriceBean = new CustomerPriceBean();
				customerPriceBean.setProductId(productId);
				customerPriceBean.setName(name);
				ProductRelationDTO productRelationDTO = productService.getProductRelations(customerPriceBean, custNo, null);
				itemList = productRelationDTO.getProductRelationItemDTOList();
			}
		}
		if(itemList.size()<1||null==itemList){
			Struts2Util.renderText("true");
            return null;
		}
        Struts2Util.renderText("false");
		return null;
	}

    /*
    * add by zhanghuibin
    * 判断product 是否是free sample的类型，如果是free sample，需遵守：
    * 1，	一年内，只能订购三次；
        2，	已经订购的free sample 加上cart里的正在订购的，不能大于三
        3，	一次订购不能订购两个相同的free sample

        Qty:item订购数量
    * */
    @SuppressWarnings("unchecked")
    private String isFreeSample(String orderno, String productIds, int custNo, String codeType, String qtys) {
        String result = "";
        for (int i = 0; i < productIds.split(",").length; i++) {
            String prodId = productIds.split(",")[i];
            ProductDTO productDto = productService.getProductById(Integer.parseInt(prodId));
            if ("Y".equals(productDto.getGiftFlag())) {
                if (Integer.parseInt(qtys.split(",")[i]) > 1) {
                    result = "Not allow add the same of 'free sample' once time!";
                    return result;
                }
                List<String> ids = new ArrayList<String>();
                if (codeType.equalsIgnoreCase("order")) {
                    Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), orderno);
                    for (OrderItemDTO dto : itemMap.values()) {
                        if ("PRODUCT".equals(dto.getType())) {
                            if (productDto.getCatalogNo().equals(dto.getCatalogNo())) {
                                result = "Not allow add the same of 'free sample' once time!";
                                return result;
                            }
                            ids.add(dto.getCatalogNo());
                        }
                    }
                } else {
                    Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), orderno);
                    for (QuoteItemDTO dto : itemMap.values()) {
                        if ("PRODUCT".equals(dto.getType())) {
                            if (productDto.getCatalogNo().equals(dto.getCatalogNo())) {
                                result = "Not allow add the same of 'free sample' once time!";
                                return result;
                            }
                            ids.add(dto.getCatalogNo());
                        }
                    }
                }
                if (ids.size() > 0) {
                    Integer productNum = productService.getGiftProductNumByNos(ids);
                    if (productNum > 2) {
                        result = "Not allow add the number of 'free sample' than three items!";
                        return result;
                    }
                }
                Integer productTimes = productService.getGiftProductTimesByNos(custNo);
                if (productTimes > 2) {
                    result = "The free sample product of the number of times not allow more than three !";
                    return result;
                }
            }
        }
        return result;
    }
	
	@SuppressWarnings("unchecked")
	public String showProductProductRelationForm() throws Exception {
		if (codeType.equalsIgnoreCase("order")) {
			sessOrderNo = quorderNo;
		} else {
			sessQuoteNo = quorderNo;
		}
		if (moreDetailFlag == 1) {
			PagerUtil<ServiceItemBean> pagerUtil = new PagerUtil<ServiceItemBean>();
			Page<ServiceItemBean> page = pagerUtil.getRequestPage();
			page.setPageSize(10);
			if (!page.isOrderBySetted()) {
				page.setOrderBy("catalogNo");
				page.setOrder(Page.DESC);
			}
			Map<String, String> filterMap = new HashMap<String, String>();
			serviceId = this.servService.getServiceIdByCatalogNo(geneCatalogNo); 
			page = servService.searchAssociatedItem(page, filterMap, serviceId);
			if (page == null) {
				return "showProductProductRelationForm";
			}
			List<ServiceItemBean> serviceItemBeanList = page.getResult();
			itemBeanDTOList = new ArrayList<ItemBeanDTO>();
			for (ServiceItemBean serviceItemBean : serviceItemBeanList) {
				ItemBeanDTO itemBean = dozer.map(serviceItemBean,
						ItemBeanDTO.class);
				itemBean.setUpSymbol(serviceItemBean.getSymbol());
				itemBeanDTOList.add(itemBean);
			}
			if (itemBeanDTOList != null && !itemBeanDTOList.isEmpty()) {
				itemList = new ArrayList<ServiceRelationItemDTO>();
				for (ItemBeanDTO dto : itemBeanDTOList) {
					ServiceRelationItemDTO serviceItemDTO = new ServiceRelationItemDTO();
					serviceItemDTO.setServiceId(dto.getServiceId());
					serviceItemDTO.setItemName(dto.getName());
					serviceItemDTO.setCatalogNo(dto.getCatalogNo());
					serviceItemDTO.setCatalogId(dto.getCatalogId());
					serviceItemDTO.setSize(dto.getSize());
					serviceItemDTO.setUom(dto.getUom());
					serviceItemDTO.setQtyUom(dto.getQtyUom());
					serviceItemDTO.setType(dto.getType());
					serviceItemDTO.setUpSymbol(dto.getUpSymbol());
					itemList.add(serviceItemDTO);
				}
			}
			ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		} else {
			if (searchClass.equals(ItemSearchType.SERVICE_ITEM.value())) {
				ServiceItemBean serviceItemBean = new ServiceItemBean();
				serviceItemBean.setName(name);
				serviceItemBean.setServiceId(productId);
				ServiceRelationDTO serviceRelationDTO = servService
						.getServiceRelations(serviceItemBean);
				itemList = serviceRelationDTO.getServiceRelationItemDTOList();
			} else {
				CustomerPriceBean customerPriceBean = new CustomerPriceBean();
				customerPriceBean.setProductId(productId);
				customerPriceBean.setName(name);
				ProductRelationDTO productRelationDTO = productService
						.getProductRelations(customerPriceBean, custNo, null);
				itemList = productRelationDTO.getProductRelationItemDTOList();
			}
		}
		return "showProductProductRelationForm";
	}

	public String getDiscount() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		Integer userId = SessionUtil.getUserId();
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			getOrderDiscount(rt, userId);
		} else {
			getQuoteDiscount(rt, userId);
		}
		return NONE;
	}

	public String clearDiscount() throws Exception {
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			clearOrderDiscount(quorderNo);
		} else {
			clearQuoteDiscount(quorderNo);
		}
		return NONE;
	}
	
	/**
	 * 清除order添加的gift
	 */
	public String clearGift() {
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			clearOrderGift(quorderNo);
		} else {
			clearQuoteGift(quorderNo);
		}
		return NONE;
	}
	
	/**
	 * 清除coupon
	 */
	public String clearCoupon() {
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			clearOrderCoupon(quorderNo);
		} else {
			clearQuoteCoupon(quorderNo);
		}
		return NONE;
	}

	/**
	 * @param sessNoVal
	 */
	@SuppressWarnings("unchecked")
	private void clearQuoteDiscount(String sessNoVal) {
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessNoVal);
		if (itemMap != null && !itemMap.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				QuoteItemDTO val = (QuoteItemDTO) itemMap.get(key);
				val.setDiscount(new BigDecimal(0.0));
				SessionUtil.updateOneRow(SessionConstant.QuoteItemList.value(),
						sessNoVal, key, val);
				sb.append(val.getCatalogNo()).append("@@0||");
			}
		}
		SessionUtil.insertRow(SessionConstant.QuoteBillingTotalFlag.value(),
				sessNoVal, 0);
	}

	/**
	 * @param sessNoVal
	 */
	@SuppressWarnings("unchecked")
	private void clearOrderDiscount(String sessNoVal) {
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), sessNoVal);
		if (itemMap != null && !itemMap.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				OrderItemDTO val = (OrderItemDTO) itemMap.get(key);
				val.setDiscount(new BigDecimal(0.0));
				SessionUtil.updateOneRow(SessionConstant.OrderItemList.value(),
						sessNoVal, key, val);
				sb.append(val.getCatalogNo()).append("@@0||");
			}
		}
		SessionUtil.insertRow(SessionConstant.OrderBillingTotalFlag.value(),
				sessNoVal, 0);
	}
	
	/**
	 * @param sessNoVal
	 */
	private void clearOrderGift(String sessNoVal) {
		this.orderService.deleteGifItem(this.prmtCode,sessNoVal);
	}
	
	/**
	 * @param sessNoVal
	 */
	private void clearQuoteGift(String sessNoVal) {
		this.quoteService.deleteGifItem(this.prmtCode, sessNoVal);
	}
	
	private void clearQuoteCoupon(String sessNoVal) {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessNoVal);
		if(this.couponId.equals(quote.getCouponId())) {
			quote.setCouponCode(null);
			quote.setCouponId(null);
			quote.setCouponValue(null);
		}
		SessionUtil.updateRow(SessionConstant.Quote.value(), sessNoVal,quote);
	}
	
	private void clearOrderCoupon(String sessNoVal) {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessNoVal);
		if(this.couponId.equals(order.getCouponId())) {
			order.setCouponCode(null);
			order.setCouponId(null);
			order.setCouponValue(null);
		}
		SessionUtil.updateRow(SessionConstant.Order.value(), sessNoVal,order);
	}

	/**
	 * @param rt
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	private void getQuoteDiscount(Map<String, Object> rt, Integer userId) {
		List<ItemDiscountDTO> itemDiscountList = new ArrayList<ItemDiscountDTO>();
		// QuoteMainDTO quote = (QuoteMainDTO)
		// SessionUtil.getRow(SessionConstant.Quote.value(), sessNoVal);
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), quorderNo);
		if (itemMap != null && !itemMap.isEmpty()) {
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				QuoteItemDTO val = (QuoteItemDTO) itemMap.get(key);
				ItemDiscountDTO itemDiscountDTO = new ItemDiscountDTO();
				itemDiscountDTO.setCatalogNo(val.getCatalogNo());
				itemDiscountDTO.setItemId(key);
				itemDiscountDTO.setAmount(val.getAmount());
				itemDiscountDTO.setStatus(val.getStatus());
				itemDiscountList.add(itemDiscountDTO);
			}
		}
		try {
			itemDiscountList = quoteService.getItemDiscount(itemDiscountList,
					prmtCode);
			if (itemDiscountList != null && !itemDiscountList.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				for (ItemDiscountDTO dto : itemDiscountList) {
					if (itemMap == null) {
						itemMap = new HashMap<String, QuoteItemDTO>();
					}

					QuoteItemDTO quoteItem = itemMap.get(dto.getItemId());
					quoteItem.setDiscount(dto.getDiscount());
					SessionUtil.updateOneRow(SessionConstant.QuoteItemList
							.value(), quorderNo, dto.getItemId(), quoteItem);
					sb.append(dto.getCatalogNo()).append("@@").append(
							dto.getDiscount()).append("||");
				}
				SessionUtil.insertRow(SessionConstant.QuoteBillingTotalFlag
						.value(), quorderNo, 0);
				Struts2Util.renderText(sb.toString());
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", userId);
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
	}

	/**
	 * @param rt
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	private void getOrderDiscount(Map<String, Object> rt, Integer userId) {
		List<ItemDiscountDTO> itemDiscountList = new ArrayList<ItemDiscountDTO>();
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), quorderNo);
		if (order.getOrderPromotion() != null) {
			prmtCode = order.getOrderPromotion().getPrmtCode();
		}
		System.out.println("prmtCode: " + prmtCode);
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), quorderNo);
		if (itemMap != null && !itemMap.isEmpty()) {
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				OrderItemDTO val = (OrderItemDTO) itemMap.get(key);
				ItemDiscountDTO itemDiscountDTO = new ItemDiscountDTO();
				System.out.println("key: " + key);
				System.out.println("val: " + val);
				itemDiscountDTO.setCatalogNo(val.getCatalogNo());
				itemDiscountDTO.setItemId(key);
				itemDiscountDTO.setAmount(val.getAmount());
				itemDiscountDTO.setStatus(val.getStatus());
				itemDiscountList.add(itemDiscountDTO);
			}
		}
		StringBuilder sb = new StringBuilder();
		// try {
		if (StringUtils.isNotBlank(prmtCode)) {
			List<ItemDiscountDTO> discountList = orderService.getItemDiscount(
					itemDiscountList, prmtCode);

			if (itemDiscountList != null && !discountList.isEmpty()) {
				for (ItemDiscountDTO dto : discountList) {
					if (itemMap == null) {
						itemMap = new HashMap<String, OrderItemDTO>();
					}

					OrderItemDTO orderItem = itemMap.get(dto.getItemId());
					orderItem.setDiscount(dto.getDiscount());
					SessionUtil.updateOneRow(SessionConstant.OrderItemList
							.value(), quorderNo, dto.getItemId(), orderItem);
					sb.append(dto.getCatalogNo()).append("@@").append(
							dto.getDiscount()).append("||");
				}
				SessionUtil.insertRow(SessionConstant.OrderBillingTotalFlag
						.value(), quorderNo, 0);
			}
			Struts2Util.renderText(sb.substring(0, -2));
			// } catch (Exception e) {
			// WSException exDTO = exceptionUtil.getExceptionDetails(e);
			// exceptionUtil.logException(exDTO, this.getClass(), e, new
			// Exception()
			// .getStackTrace()[0].getMethodName(), "INTF0101", userId);
			// rt.put("hasException", "Y");
			// rt.put("exception", exDTO);
			// }
		}
		Struts2Util.renderText("");
	}

	/**
	 * 获得catalog 详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showCatalogDetail() throws Exception {
		catalogId = Struts2Util.getRequest().getParameter("catalogId");
		if (StringUtils.isEmpty(catalogId)) {
			return "catalog_detail";
		}
		catalog = productService.getCatalogDetail(catalogId);
		return "catalog_detail";
	}
	
	/**
	 * 获得coupon列表
	 * @return
	 */
	public String viewCoupon() {
		PagerUtil<Coupon> pagerUtil = new PagerUtil<Coupon>();
		couponPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!couponPage.isOrderBySetted()) {
			couponPage.setOrderBy("id");
			couponPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		couponPage.setPageSize(20);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		couponPage = couponService.findCouponList(couponPage, filters);
		ServletActionContext.getRequest().setAttribute("pagerInfo",
				couponPage);
		return "view_coupon";
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	public String getQuorderNo() {
		return quorderNo;
	}

	public void setQuorderNo(String quorderNo) {
		this.quorderNo = quorderNo;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getSearchClass() {
		return searchClass;
	}

	public void setSearchClass(String searchClass) {
		this.searchClass = searchClass;
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

	public String getPropertyValue1() {
		return propertyValue1;
	}

	public void setPropertyValue1(String propertyValue1) {
		this.propertyValue1 = propertyValue1;
	}

	public String getSearchOperator() {
		return searchOperator;
	}

	public void setSearchOperator(String searchOperator) {
		this.searchOperator = searchOperator;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public List<ItemBeanDTO> getItemBeanDTOList() {
		return itemBeanDTOList;
	}

	public void setItemBeanDTOList(List<ItemBeanDTO> itemBeanDTOList) {
		this.itemBeanDTOList = itemBeanDTOList;
	}

	public Map<String, String> getItemSearchTypes() {
		itemSearchTypes = new LinkedHashMap<String, String>();
		itemSearchTypes.put("SERVICE_ITEM", "Service Item");
		itemSearchTypes.put("PRODUCT_ITEM", "Product Item");
		itemSearchTypes.put("ORDER_ITEM", "Order Item");
		itemSearchTypes.put("QUOTE_ITEM", "Quote Item");
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			itemSearchTypes.put("TEMPLATE_ITEM", "Product Order Template Item");
			itemSearchTypes.put("SERVICE_TEMPLATE_ITEM", "Service Order Template Item");
		} else {
			itemSearchTypes.put("TEMPLATE_ITEM", "Product Quote Template Item");
			itemSearchTypes.put("SERVICE_TEMPLATE_ITEM", "Service Quote Template Item");
		}
		return itemSearchTypes;
	}

	public void setItemSearchTypes(Map<String, String> itemSearchTypes) {
		this.itemSearchTypes = itemSearchTypes;
	}

	@SuppressWarnings("rawtypes")
	public List getProductTemplateList() {
		Integer userId = SessionUtil.getUserId();
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			productTemplateList = orderService.getOrderProductTempList(userId, custNo);
		}else{
			productTemplateList = quoteService.getQuoteProductTempList(userId, custNo);
		}
		return productTemplateList;
	}

	@SuppressWarnings("rawtypes")
	public List getServiceTemplateList() {
		Integer userId = SessionUtil.getUserId();
		if (codeType.equalsIgnoreCase(TemplateType.ORDER.value())) {
			serviceTemplateList = orderService.getOrderServiceTempList(userId, custNo);
		}else{
			serviceTemplateList = quoteService.getQuoteServiceTempList(userId, custNo);
		}
		return serviceTemplateList;
	}


	
	/**
	 * order more detail 页面中服务类型为ORF Clone的tab页面中查询
	 * @author Zhang Yong
	 * @return
	 */
	public String searchOrfClone () {
		String accessionNo = Struts2Util.getParameter("accessionNo");
		refseq2orfpriceList = this.quoteOrderService.searchOrfClone(accessionNo);
		return "show_orf_clone_list";
	}
	
	public String getName() {
		return name;
	}

	@SuppressWarnings("rawtypes")
	public void setProductTemplateList(List productTemplateList) {
		this.productTemplateList = productTemplateList;
	}

	@SuppressWarnings("rawtypes")
	public void setServiceTemplateList(List serviceTemplateList) {
		this.serviceTemplateList = serviceTemplateList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoreDetailFlag() {
		return moreDetailFlag;
	}

	public void setMoreDetailFlag(int moreDetailFlag) {
		this.moreDetailFlag = moreDetailFlag;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@SuppressWarnings("rawtypes")
	public List getItemList() {
		return itemList;
	}

	@SuppressWarnings("rawtypes")
	public void setItemList(List itemList) {
		this.itemList = itemList;
	}

	public String getPrmtCode() {
		return prmtCode;
	}

	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public CatalogDTO getCatalog() {
		return catalog;
	}

	public void setCatalog(CatalogDTO catalog) {
		this.catalog = catalog;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Page<Coupon> getCouponPage() {
		return couponPage;
	}

	public void setCouponPage(Page<Coupon> couponPage) {
		this.couponPage = couponPage;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getGeneCatalogNo() {
		return geneCatalogNo;
	}

	public void setGeneCatalogNo(String geneCatalogNo) {
		this.geneCatalogNo = geneCatalogNo;
	}

	public String getErpCheckStockException() {
		return erpCheckStockException;
	}

	public void setErpCheckStockException(String erpCheckStockException) {
		this.erpCheckStockException = erpCheckStockException;
	}

	public List<Refseq2orfpriceDTO> getRefseq2orfpriceList() {
		return refseq2orfpriceList;
	}

	public void setRefseq2orfpriceList(List<Refseq2orfpriceDTO> refseq2orfpriceList) {
		this.refseq2orfpriceList = refseq2orfpriceList;
	}
}
