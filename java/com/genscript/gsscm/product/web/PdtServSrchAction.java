package com.genscript.gsscm.product.web;

import java.util.HashMap;
import java.util.Map;

import com.genscript.gsscm.common.util.PagerUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@Results({
        @Result(name = "settingsSourcePdtList", location = "product/pdtServ_forsSettingsSource_list.jsp")})
public class PdtServSrchAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 8795612906400917618L;
    private String propName;
    private String srchOperator;
    private String propValue1;
    private Page<Product> pageProduct;
    @Autowired
    private ProductService productService;


    public String settingsSourcePdtList() throws Exception {
        PagerUtil<Product> pagerUtil = new PagerUtil<Product>();
        pageProduct = pagerUtil.getRequestPage();
        if (pageProduct.getPageSize() == null
                || pageProduct.getPageSize().intValue() < 1) {
            pageProduct.setPageSize(20);
        }
        if (!pageProduct.isOrderBySetted()) {
            pageProduct.setOrderBy("catalogNo");
            pageProduct.setOrder(Page.DESC);
        }
        if (StringUtils.isBlank(propValue1)) {
            pageProduct = productService.getProductList(pageProduct);
        } else {
            StringBuilder srchStr = new StringBuilder();
            srchStr.append(srchOperator).append("S").append("_")
                    .append(propName);
            Map<String, String> filterMap = new HashMap<String, String>();
            filterMap.put(srchStr.toString(), propValue1);
            pageProduct = productService.getProductList(pageProduct, filterMap);
        }
        return "settingsSourcePdtList";
    }


    public String getPropName() {
        return propName;
    }


    public void setPropName(String propName) {
        this.propName = propName;
    }


    public String getSrchOperator() {
        return srchOperator;
    }


    public void setSrchOperator(String srchOperator) {
        this.srchOperator = srchOperator;
    }


    public String getPropValue1() {
        return propValue1;
    }


    public void setPropValue1(String propValue1) {
        this.propValue1 = propValue1;
    }


    public Page<Product> getPageProduct() {
        return pageProduct;
    }

    public void setPageProduct(Page<Product> pageProduct) {
        this.pageProduct = pageProduct;
    }

}
