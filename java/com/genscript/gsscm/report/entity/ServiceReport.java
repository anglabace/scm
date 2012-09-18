package com.genscript.gsscm.report.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/29/11
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="v_service_structure", catalog = "product")
public class ServiceReport implements Cloneable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
    private Integer productId;
    private String catalogNo;
    private String name;
    private String productType;
    private String uom;
    private String catalogId;
    private String segment;
    private String businessUnit;
    @Column(name="business_unit_id")
    private Integer businessUnitId;
    private String category;
    private Integer categoryId;
    private String productLine;
    @Column(name="product_line_id")
    private Integer productLineId;
    private Integer quantity;
    private BigDecimal amount;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCatalogNo() {
        return catalogNo;
    }

    public void setCatalogNo(String catalogNo) {
        this.catalogNo = catalogNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Integer getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(Integer businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }
}
