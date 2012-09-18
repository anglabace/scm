package com.genscript.gsscm.customer.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionEmergeUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustSpecialPriceDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;
import com.genscript.gsscm.serv.service.ServiceOfServcategoryBeanService;

import javax.sound.midi.SysexMessage;

/**
 * 处理customer special price相关action
 *
 * @author zouyulu
 */
@Results({
        @Result(name = "list", location = "customer/customer_price_list.jsp"),
        @Result(name = "list_pdt", location = "customer/customer_price_list_pdt.jsp"),
        @Result(name = "edit", location = "customer/customer_price_edit.jsp"),
        @Result(name = "pick", location = "customer/customer_price_catalog_pick.jsp"),
        @Result(name = "list_ser", location = "customer/customer_price_list_ser.jsp"),
        @Result(name = "showSpecialPrice", location = "customer/cust_special_price_edit.jsp")})
public class CustSpclPrcAction extends BaseAction<CustSpecialPriceDTO> {

    /**
     *
     */
    private static final long serialVersionUID = 1970129914583903972L;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ServiceOfServcategoryBeanService serviceOfServcategoryBeanService;
    @Autowired
    private PublicService publicService;
    private String sessCustNo;
    private Integer custNo;
    private List<Catalog> catalogList;
    private List<Source> sourceList;
    private Map<Integer, Source> sourceMap;
    private String listType;
    private Map<String, CustSpecialPriceDTO> priceMap;
    private List<String> delPriceIdList;
    private CustSpecialPriceDTO custSpecialPrice;

    private CustSpecialPriceDTO result;
    private String priceCatalogId;

    private BigDecimal specialDiscount;
    private Date discEffFrom;
    private Date discEffTo;
    private Double unappliedCredit;

    // catalog picker
    private List<ProductBean> productList;
    private List<ServiceOfServcategoryBean> serviceOfServcategoryBeanList;
    private String searchOperator;
    private String orderBy;
    private String propertyValue1;
    private String propertyName;
    private String catalogId;

    private String catalogNo;


    /**
     * 删除记录
     *
     * @author zouyulu
     */
    @SuppressWarnings("unchecked")
    @Override
    public String delete() throws Exception {
        if (delPriceIdList != null && !delPriceIdList.isEmpty()) {
            priceMap = (Map<String, CustSpecialPriceDTO>) SessionUtil.getRow(
                    SessionConstant.CustPriceList.value(), sessCustNo);
            if (priceMap == null) {
                priceMap = new HashMap<String, CustSpecialPriceDTO>();
                SessionUtil.insertRow(SessionConstant.CustPriceList.value(),
                        sessCustNo, priceMap);
            }
            for (int i = 0; i < delPriceIdList.size(); i++) {
                String delId = delPriceIdList.get(i);
                if (StringUtils.isNumeric(delId)) {
                    priceMap.put(delId, null);
                } else {
                    priceMap.remove(delId);
                }
            }
            SessionUtil.updateRow(SessionConstant.CustPriceList.value(),
                    sessCustNo, priceMap);
        }
        Struts2Util.renderText("SUCCESS");
        return null;
    }

    /**
     * 显示编辑页面
     *
     * @author zouyulu
     */
    @Override
    public String input() throws Exception {
        if (StringUtils.isEmpty(priceCatalogId)) {
            priceCatalogId = productService.getBaseCatalogId();
        }
        sourceList = publicService.getSourceDetail();
        return "edit";
    }

    public String getCatalogNo() {
        return catalogNo;
    }

    public void setCatalogNo(String catalogNo) {
        this.catalogNo = catalogNo;
    }

    /**
     * catalog 查询选择页面
     *
     * @return
     * @throws Exception
     * @author zouyulu
     */
    @SuppressWarnings("unchecked")
    public String pickCatalog() throws Exception {
        PagerUtil<ProductBean> pagerUtil = new PagerUtil<ProductBean>();
        Page page = pagerUtil.getRequestPage();
        List<PropertyFilter> filters = WebUtil.buildPropertyFilters(ServletActionContext.getRequest());
        if (!StringUtils.isEmpty(orderBy)) {
            if (!page.isOrderBySetted()) {
                page.setOrderBy(orderBy);
                page.setOrder(Page.ASC);
                page.setPageSize(50);
            }
        } else {
            if (!page.isOrderBySetted()) {
                page.setOrderBy("catalogNo");
                page.setOrder(Page.ASC);
                page.setPageSize(50);
            }
        }
        PropertyFilter pf = new PropertyFilter("EQS_catalogId", catalogId);
        filters.add(pf);
        if (CatalogType.SERVICE.value().equals(listType)) {
            Page<ServiceOfServcategoryBean> servicePage = serviceOfServcategoryBeanService.searchService(page,
                    filters, custNo, listType);
            serviceOfServcategoryBeanList = servicePage.getResult();

            // 把分页相关数据放入request作用域内
            ServletActionContext.getRequest().setAttribute("pagerInfo", servicePage);
        } else {
            Page<ProductBean> productPage = productService.searchProduct(page,
                    filters, custNo, listType);
            productList = productPage.getResult();

            // 把分页相关数据放入request作用域内
            ServletActionContext.getRequest().setAttribute("pagerInfo", productPage);
        }
        return "pick";
    }

    public String showSpecialPrice() throws Exception {
        String catalogNo = Struts2Util.getParameter("priceCatalogId");
        // System.out.println(catalogNo);
        // System.out.println(custNo);
        custSpecialPrice = customerService.getSpecialPrice(custNo, catalogNo);
        return "showSpecialPrice";
    }

    public CustSpecialPriceDTO getResult() {
        return result;
    }

    public void setResult(CustSpecialPriceDTO result) {
        this.result = result;
    }

    public String showSpecialPrice_2() throws Exception {
        String priceCatalogNO = Struts2Util.getParameter("priceCatalogNO");
        String priceCatalogId = Struts2Util.getParameter("priceCatalogId");
        System.out.println(priceCatalogNO);
        System.out.println(priceCatalogId);
        priceCatalogId = priceCatalogNO;
    //    if (StringUtils.isEmpty(priceCatalogId)) {
    //        priceCatalogId = productService.getBaseCatalogId();
     //   }
        sourceList = publicService.getSourceDetail();
        custSpecialPrice = customerService.getSpecialPrice(custNo, priceCatalogNO);
        return "showSpecialPrice";
    }

    /**
     * 显示special price list
     *
     * @return
     * @author zouyulu
     */
    @SuppressWarnings("unchecked")
    @Override
    public String list() throws Exception {
        CustomerDTO customer = (CustomerDTO) SessionUtil.getRow(
                SessionConstant.Customer.value(), sessCustNo);
        if (customer != null) {
            priceCatalogId = customer.getPriceCatalogId();
            specialDiscount = customer.getSpecialDiscount();
            discEffFrom = customer.getDiscEffFrom();
            discEffTo = customer.getDiscEffTo();
            if (customer.getCustInfoStat() == null) {
                unappliedCredit = 0.0;
            } else {
                unappliedCredit = customer.getCustInfoStat().getUnappliedCredit();
            }
        }

        catalogList = productService.getSpecailCatalogList();
        List<Source> sourceList = publicService.getSourceDetail();
        sourceMap = new HashMap<Integer, Source>();
        if (sourceList != null && !sourceList.isEmpty()) {
            for (int i = 0; i < sourceList.size(); i++) {
                sourceMap.put(sourceList.get(i).getSourceId(), sourceList
                        .get(i));
            }
        }
        if (listType == null || StringUtils.isEmpty(listType)) {
            return "list";
        }
        String rt = "list_pdt";
        List<CustSpecialPriceDTO> dbPriceList = new ArrayList<CustSpecialPriceDTO>();
        Map<String, CustSpecialPriceDTO> dbPriceMap = new LinkedHashMap<String, CustSpecialPriceDTO>();
        priceMap = new HashMap<String, CustSpecialPriceDTO>();
        // 查询page
        PagerUtil<CustomerSpecialPrice> pagerUtil = new PagerUtil<CustomerSpecialPrice>();
        Page<CustomerSpecialPrice> page = pagerUtil.getRequestPage();
        page.setOrder("DESC");
        page.setOrderBy("creationDate");
        page.setPageSize(10);
        // 结果page
        PagerUtil<CustSpecialPriceDTO> pagerUtil2 = new PagerUtil<CustSpecialPriceDTO>();
        Page<CustSpecialPriceDTO> page2 = pagerUtil2.getRequestPage();
        page2.setPageNo(page.getPageNo());
        if (custNo != null && custNo > 0) {
            PageDTO pagerInfo = null;
            if (StringUtils.isEmpty(listType)
                    || listType.equalsIgnoreCase("PRODUCT")) {
                Page<CustSpecialPriceDTO> pdtPage = customerService
                        .getProductSpecialPriceList(custNo, page);
                dbPriceList = pdtPage.getResult();
                pagerInfo = pagerUtil2.formPage(pdtPage);
                rt = "list_pdt";
            } else {
                Page<CustSpecialPriceDTO> serPage = customerService
                        .getServiceSpecialPriceList(custNo, page);
                dbPriceList = serPage.getResult();
                pagerInfo = pagerUtil2.formPage(serPage);
                rt = "list_ser";
            }
            ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
        }
        for (CustSpecialPriceDTO custSpecialPriceDTO : dbPriceList) {
            dbPriceMap.put(Integer.toString(custSpecialPriceDTO.getPriceId()),
                    custSpecialPriceDTO);
        }
        Map<String, CustSpecialPriceDTO> sessMap = (Map<String, CustSpecialPriceDTO>) SessionUtil
                .getRow(SessionConstant.CustPriceList.value(), sessCustNo);
        Map<String, CustSpecialPriceDTO> newSessMap = new LinkedHashMap<String, CustSpecialPriceDTO>();
        if (sessMap != null && !sessMap.isEmpty()) {
            Iterator<Entry<String, CustSpecialPriceDTO>> it = sessMap
                    .entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, CustSpecialPriceDTO> entry = it.next();
                String tmpKey = entry.getKey();
                CustSpecialPriceDTO tmpCustSpecialPriceDTO = entry.getValue();
                if (tmpCustSpecialPriceDTO == null) {
                    newSessMap.put(tmpKey, null);
                } else if (tmpCustSpecialPriceDTO.getType().equalsIgnoreCase(
                        listType)) {
                    newSessMap.put(tmpKey, tmpCustSpecialPriceDTO);
                }
            }
        }
        SessionEmergeUtil<CustSpecialPriceDTO> sessionEmergeUtil = new SessionEmergeUtil<CustSpecialPriceDTO>();
        priceMap = sessionEmergeUtil.mergeList(newSessMap, dbPriceMap, page
                .getPageNo());
        return rt;
    }

    @Override
    protected void prepareModel() throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 保存
     *
     * @author zouyulu
     */
    @Override
    public String save() throws Exception {
        SessionUtil.updateOneRow(SessionConstant.CustPriceList.value(),
                sessCustNo, SessionUtil.generateTempId(), custSpecialPrice);
        Struts2Util.renderText("SUCCESS");
        return null;
    }

    public String getSessCustNo() {
        return sessCustNo;
    }

    public void setSessCustNo(String sessCustNo) {
        this.sessCustNo = sessCustNo;
    }

    public Integer getCustNo() {
        return custNo;
    }

    public void setCustNo(Integer custNo) {
        this.custNo = custNo;
    }

    public Map<String, CustSpecialPriceDTO> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, CustSpecialPriceDTO> priceMap) {
        this.priceMap = priceMap;
    }

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public Map<Integer, Source> getSourceMap() {
        return sourceMap;
    }

    public void setSourceMap(Map<Integer, Source> sourceMap) {
        this.sourceMap = sourceMap;
    }

    public List<String> getDelPriceIdList() {
        return delPriceIdList;
    }

    public void setDelPriceIdList(List<String> delPriceIdList) {
        this.delPriceIdList = delPriceIdList;
    }

    public CustSpecialPriceDTO getCustSpecialPrice() {
        return custSpecialPrice;
    }

    public void setCustSpecialPrice(CustSpecialPriceDTO custSpecialPrice) {
        this.custSpecialPrice = custSpecialPrice;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ProductBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductBean> productList) {
        this.productList = productList;
    }

    public String getSearchOperator() {
        return searchOperator;
    }

    public void setSearchOperator(String searchOperator) {
        this.searchOperator = searchOperator;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getPropertyValue1() {
        return propertyValue1;
    }

    public void setPropertyValue1(String propertyValue1) {
        this.propertyValue1 = propertyValue1;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public void setPriceCatalogId(String priceCatalogId) {
        this.priceCatalogId = priceCatalogId;
    }

    public String getPriceCatalogId() {
        return priceCatalogId;
    }

    public void setDiscEffFrom(Date discEffFrom) {
        this.discEffFrom = discEffFrom;
    }

    public Date getDiscEffFrom() {
        return discEffFrom;
    }

    public void setDiscEffTo(Date discEffTo) {
        this.discEffTo = discEffTo;
    }

    public Date getDiscEffTo() {
        return discEffTo;
    }

    public void setUnappliedCredit(Double unappliedCredit) {
        this.unappliedCredit = unappliedCredit;
    }

    public Double getUnappliedCredit() {
        return unappliedCredit;
    }

    public void setSpecialDiscount(BigDecimal specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public BigDecimal getSpecialDiscount() {
        return specialDiscount;
    }

    public List<ServiceOfServcategoryBean> getServiceOfServcategoryBeanList() {
        return serviceOfServcategoryBeanList;
    }

    public void setServiceOfServcategoryBeanList(
            List<ServiceOfServcategoryBean> serviceOfServcategoryBeanList) {
        this.serviceOfServcategoryBeanList = serviceOfServcategoryBeanList;
    }

}
