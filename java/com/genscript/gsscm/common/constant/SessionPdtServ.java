package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnumValue;

public enum SessionPdtServ {
	@XmlEnumValue("Catalog")
	Catalog("Catalog"),
	@XmlEnumValue("CatalogProductCategory")
	CatalogProductCategory("CatalogProductCategory"),
	@XmlEnumValue("DelCatalogProductCategory")
	DelCatalogProductCategory("DelCatalogProductCategory"),
	@XmlEnumValue("CatalogServiceCategory")
	CatalogServiceCategory("CatalogServiceCategory"),
	@XmlEnumValue("DelCatalogServiceCategory")
	DelCatalogServiceCategory("DelCatalogServiceCategory"),
	@XmlEnumValue("SubProductCategory")
	SubProductCategory("SubProductCategory"),
	@XmlEnumValue("DelSubProductCategory")
	DelSubProductCategory("DelSubProductCategory"),
	@XmlEnumValue("SubServiceCategory")
	SubServiceCategory("SubServiceCategory"),
	@XmlEnumValue("DelSubServiceCategory")
	DelSubServiceCategory("DelSubServiceCategory"),
	@XmlEnumValue("Product")
	Product("Product"),
	@XmlEnumValue("DelProduct")
	DelProduct("DelProduct"),
	@XmlEnumValue("Service")
	Service("Service"),
	@XmlEnumValue("DelService")
	DelService("DelService"),
	@XmlEnumValue("ServiceDelSubStep")
	ServiceDelSubStep("ServiceDelSubStep"),
        @XmlEnumValue("ProductStockStat")
        ProductStockStat("ProductStockStat"),
        @XmlEnumValue("RestrictShipList")
        RestrictShipList("RestrictShipList"),
        @XmlEnumValue("DelRestrictShipList")
        DelRestrictShipList("DelRestrictShipList"),
        @XmlEnumValue("ServiceRestrictShipList")
        ServiceRestrictShipList("ServiceRestrictShipList"),
        @XmlEnumValue("DelServiceRestrictShipList")
        DelServiceRestrictShipList("DelServiceRestrictShipList"),
	@XmlEnumValue("ServiceSubStep")
	ServiceSubStep("ServiceSubStep"),
	@XmlEnumValue("ProductSupplier")
	ProductSupplier("ProductSupplier"),
	@XmlEnumValue("DelProductSupplier")
	DelProductSupplier("DelProductSupplier"),
	@XmlEnumValue("ServiceSupplier")
	ServiceSupplier("ServiceSupplier"),
	@XmlEnumValue("ServicePricing")
	ServicePricing("ServicePricing"),
	@XmlEnumValue("ServiceSubStepPricing")
	ServiceSubStepPricing("ServiceSubStepPricing"),
        @XmlEnumValue("BreakdownList")
	BreakdownList("BreakdownList"),
	@XmlEnumValue("SpecailPrice")
	SpecailPrice("SpecailPrice"),
	@XmlEnumValue("DelSpecailPrice")
	DelSpecailPrice("DelSpecailPrice"),
        @XmlEnumValue("DelBreakdownList")
	DelBreakdownList("DelBreakdownList"),
	@XmlEnumValue("DelServiceSupplier")
	DelServiceSupplier("DelServiceSupplier"),
	@XmlEnumValue("FileProductMoreInfo")
	FileProductMoreInfo("FileProductMoreInfo"),
	@XmlEnumValue("DelFileProductMoreInfo")
	DelFileProductMoreInfo("DelFileProductMoreInfo"),
        @XmlEnumValue("ComponentList")
	ComponentList("ComponentList"),
	@XmlEnumValue("DelComponentList")
	DelComponentList("DelComponentList"),
	@XmlEnumValue("ProductRelation")
	ProductRelation("ProductRelation"),
	@XmlEnumValue("ServiceRelation")
	ServiceRelation("ServiceRelation"),
	@XmlEnumValue("CatalogApprovedName")
	CatalogApprovedName("CatalogApprovedName"),
	@XmlEnumValue("CatalogApprovedStatus")
	CatalogApprovedStatus("CatalogApprovedStatus"),
	@XmlEnumValue("CatalogApprovedNameReason")
	CatalogApprovedNameReason("CatalogApprovedNameReason"),
	@XmlEnumValue("CatalogApprovedStatusReason")
	CatalogApprovedStatusReason("CatalogApprovedStatusReason")
	
	,
	@XmlEnumValue("ProductCategoryApprovedName")
	ProductCategoryApprovedName("ProductCategoryApprovedName"),
	@XmlEnumValue("ProductCategoryApprovedStatus")
	ProductCategoryApprovedStatus("ProductCategoryApprovedStatus"),
	@XmlEnumValue("ProductCategoryApprovedNameReason")
	ProductCategoryApprovedNameReason("ProductCategoryApprovedNameReason"),
	@XmlEnumValue("ProductCategoryApprovedStatusReason")
	ProductCategoryApprovedStatusReason("ProductCategoryApprovedStatusReason")
	
	,
	@XmlEnumValue("ServiceCategoryApprovedName")
	ServiceCategoryApprovedName("ServiceCategoryApprovedName"),
	@XmlEnumValue("ServiceCategoryApprovedStatus")
	ServiceCategoryApprovedStatus("ServiceCategoryApprovedStatus"),
	@XmlEnumValue("ServiceCategoryApprovedNameReason")
	ServiceCategoryApprovedNameReason("ServiceCategoryApprovedNameReason"),
	@XmlEnumValue("ServiceCategoryApprovedStatusReason")
	ServiceCategoryApprovedStatusReason("ServiceCategoryApprovedStatusReason")
	
	,
	@XmlEnumValue("ProductApprovedName")
	ProductApprovedName("ProductApprovedName"),
	@XmlEnumValue("ProductApprovedStatus")
	ProductApprovedStatus("ProductApprovedStatus"),
	@XmlEnumValue("ApprovedUnitCost")
	ApprovedUnitCost("ApprovedUnitCost"),
	@XmlEnumValue("ProductApprovedNameReason")
	ProductApprovedNameReason("ProductApprovedNameReason"),
	@XmlEnumValue("ProductApprovedStatusReason")
	ProductApprovedStatusReason("ProductApprovedStatusReason"),
	@XmlEnumValue("ApprovedUnitCostReason")
	ApprovedUnitCostReason("ApprovedUnitCostReason")
	
	
	,
	@XmlEnumValue("ServiceApprovedName")
	ServiceApprovedName("ServiceApprovedName"),
	@XmlEnumValue("ServiceApprovedStatus")
	ServiceApprovedStatus("ServiceApprovedStatus"),
	@XmlEnumValue("ServiceApprovedNameReason")
	ServiceApprovedNameReason("ServiceApprovedNameReason"),
	@XmlEnumValue("ServiceApprovedStatusReason")
	ServiceApprovedStatusReason("ServiceApprovedStatusReason"),
	
	@XmlEnumValue("DelProductPrice")
    DelProductPrice("DelProductPrice"),
	@XmlEnumValue("ProductPriceApproved")
	ProductPriceApproved("ProductPriceApproved"),
	@XmlEnumValue("ProductPriceApprovedReason")
	ProductPriceApprovedReason("ProductPriceApprovedReason"),
	

	@XmlEnumValue("ServicePriceRule")
	ServicePriceRule("ServicePriceRule"),
	@XmlEnumValue("PriceFormulaItem")
	PriceFormulaItem("PriceFormulaItem"),
	@XmlEnumValue("DelPriceFormulaItem")
	DelPriceFormulaItem("DelPriceFormulaItem"),
	@XmlEnumValue("Criterias")
	Criterias("Criterias"),
	@XmlEnumValue("DelCriterias")
	DelCriterias("DelCriterias"),
	@XmlEnumValue("ServicePriceOfRuleGroup")
	ServicePriceOfRuleGroup("ServicePriceOfRuleGroup"),
	@XmlEnumValue("ServiceDelPriceOfRuleGroup")
	ServiceDelPriceOfRuleGroup("ServiceDelPriceOfRuleGroup"),
	
	@XmlEnumValue("ServicePriceApproved")
	ServicePriceApproved("ServicePriceApproved"),
	@XmlEnumValue("ServiceSubPriceApproved")
	ServiceSubPriceApproved("ServiceSubPriceApproved")
	;

	
	private final String value;

	SessionPdtServ(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static SessionPdtServ fromValue(String v) {
		for (SessionPdtServ c : SessionPdtServ.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
