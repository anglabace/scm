package com.genscript.gsscm.common.constant;

import javax.xml.bind.annotation.XmlEnumValue;

public enum SessionConstant {
        @XmlEnumValue("ShipClerkAssigned")
        ShipClerkAssigned("ShipClerkAssigned"),
        @XmlEnumValue("QaGroupAssigned")
        QaGroupAssigned("QaGroupAssigned"),
        @XmlEnumValue("WorkCenterAssigned")
        WorkCenterAssigned("WorkCenterAssigned"),
        @XmlEnumValue("DelShipClerkAssigned")
        DelShipClerkAssigned("DelShipClerkAssigned"),
         @XmlEnumValue("EmarketingGroupAssigned")
        EmarketingGroupAssigned("EmarketingGroupAssigned"),
        @XmlEnumValue("DelEmarketingGroupAssigned")
        DelEmarketingGroupAssigned("DelEmarketingGroupAssigned"),
         @XmlEnumValue("EmarketingClerkList")
        EmarketingClerkList("EmarketingClerkList"),
        @XmlEnumValue("DelEmarketingClerkList")
        DelEmarketingClerkList("DelEmarketingClerkList"),
	@XmlEnumValue("Contact")
	Contact("Contact"), @XmlEnumValue("ContactContact")
	ContactContact("ContactContact"), @XmlEnumValue("ContactAddress")
	ContactAddress("ContactAddress"), @XmlEnumValue("ContactGrant")
	ContactGrants("ContactGrants"), @XmlEnumValue("ContactPubs")
	ContactPubs("ContactPubs"), @XmlEnumValue("CustContact")
	CustContact("CustContact"), @XmlEnumValue("CustInterestList")
	CustInterestList("CustInterestList"), @XmlEnumValue("CustGrantList")
	CustGrantList("CustGrantList"), @XmlEnumValue("CustAddressList")
	CustAddressList("CustAddressList"), @XmlEnumValue("CustPubList")
	CustPubList("CustPubList"), @XmlEnumValue("CustGrantList")
	CustNoteList("CustNoteList"), @XmlEnumValue("CustPriceList")
	CustPriceList("CustPriceList"), @XmlEnumValue("CustCaseList")
	CustCaseList("CustCaseList"), @XmlEnumValue("Customer")
	Customer("customer"), @XmlEnumValue("PersonalInfo")
	PersonalInfo("PersonalInfo"), @XmlEnumValue("Order")
	Order("Order"),@XmlEnumValue("OrderItemList")
	OrderItemList("OrderItemList"),
	@XmlEnumValue("CustConfirmMailTemplate")
	CustConfirmMailTemplate("CustConfirmMailTemplate"),
	@XmlEnumValue("TemplateOrderItem")
	TemplateOrderItem("TemplateOrderItem"),
	@XmlEnumValue("TemplateQuoteItem")
	TemplateQuoteItem("TemplateQuoteItem"),
	@XmlEnumValue("StateList")
	StateList("StateList"),
	@XmlEnumValue("AssignmentRules")
	AssignmentRules("AssignmentRules"),
	@XmlEnumValue("SalesGroup")
	SalesGroup("SalesGroup"),
	@XmlEnumValue("SalesResourceList")
	SalesResourceList("SalesResourceList"),
	@XmlEnumValue("AssignProcessList")
	AssignProcessList("AssignProcessList"),
	@XmlEnumValue("OrderItemDelList")
	OrderItemDelList("OrderItemDelList"),
	@XmlEnumValue("QuoteItemDelList")
	QuoteItemDelList("QuoteItemDelList"),
	@XmlEnumValue("PromotionSetting")
    PromotionSetting("PromotionSetting"),
    @XmlEnumValue("CouponSetting")
    CouponSetting("CouponSetting"),
    @XmlEnumValue("SourceSetting")
    SourceSetting("SourceSetting"),
    @XmlEnumValue("ZoneListSetting")
    ZoneListSetting("ZoneListSetting"),
    @XmlEnumValue("UpdateZoneListSetting")
    UpdateZoneListSetting("UpdateZoneListSetting"),
    @XmlEnumValue("RateListSetting")
    UpdateRateListSetting("UpdateRateListSetting"),
    @XmlEnumValue("UpdateRateListSetting")
    RateListSetting("RateListSetting"),
    @XmlEnumValue("ShippingMethodSetting")
    ShippingMethodSetting("ShippingMethodSetting"),
    @XmlEnumValue("standardRateValue")
    standardRateValue("standardRateValue"),
    @XmlEnumValue("standardZoneValue")
    standardZoneValue("standardZoneValue"),
	@XmlEnumValue("OrderNote")
	OrderNote("OrderNote"),
	@XmlEnumValue("QuoteNote")
	QuoteNote("QuoteNote"),
	@XmlEnumValue("Catalog")
	Catalog("Catalog"),
	@XmlEnumValue("Quote")
	Quote("Quote"),
	@XmlEnumValue("QuoteItemList")
	QuoteItemList("QuoteItemList"),
	@XmlEnumValue("OrderBillingTotal")
	OrderBillingTotal("OrderBillingTotal"),
	@XmlEnumValue("QuoteBillingTotal")
	QuoteBillingTotal("QuoteBillingTotal"),
	@XmlEnumValue("OrderBillingTotalFlag")
	OrderBillingTotalFlag("OrderBillingTotalFlag"),
	@XmlEnumValue("QuoteBillingTotalFlag")
	QuoteBillingTotalFlag("QuoteBillingTotalFlag"),
	@XmlEnumValue("CatalogProductCategory")
	CatalogProductCategory("CatalogProductCategory"),
	@XmlEnumValue("CatalogServiceCategory")
	CatalogServiceCategory("CatalogServiceCategory"),
	@XmlEnumValue("SubProductCategory")
	SubProductCategory("SubProductCategory"),
	@XmlEnumValue("SubServiceCategory")
	SubServiceCategory("SubServiceCategory"),
	@XmlEnumValue("Product")
	Product("Product"),
	@XmlEnumValue("ServiceSubStep")
	ServieSubStep("ServiceSubStep"),
	@XmlEnumValue("QuotePackage")
	QuotePackage("QuotePackage"),
	@XmlEnumValue("OrderPackage")
	OrderPackage("OrderPackage"),
	@XmlEnumValue("OrderPayment")
	OrderPayment("OrderPayment"),
	@XmlEnumValue("QuotePayment")
	QuotePayment("QuotePayment"),
	@XmlEnumValue("ProductPricing")
	ProductPricing("ProductPricing"),
	@XmlEnumValue("OrderShippingTotal")
	OrderShippingTotal("OrderShippingTotal"),
	@XmlEnumValue("QuoteShippingTotal")
	QuoteShippingTotal("QuoteShippingTotal"),
        @XmlEnumValue("ShipRateTotalRangeList")
        ShipRateTotalRangeList("ShipRateTotalRangeList"),
        @XmlEnumValue("DelShipRateTotalRangeList")
        DelShipRateTotalRangeList("DelShipRateTotalRangeList"),
        @XmlEnumValue("ShipRateWeightRangeList")
        ShipRateWeightRangeList("ShipRateWeightRangeList"),
        @XmlEnumValue("DelShipRateWeightRangeList")
        DelShipRateWeightRangeList("DelShipRateWeightRangeList"),
        @XmlEnumValue("OrderReturnItem")
    	OrderReturnItem("OrderReturnItem"),
	@XmlEnumValue("OtherPeptideList")
	OtherPeptideList("OtherPeptideList"),
	@XmlEnumValue("OtherOligoList")
	OtherOligoList("OtherOligoList"),
	GroupResource("GroupResource"),
	CenterGroup("CenterGroup"),
	OperationResource("OperationResource"),
	RouteOperation("RouteOperation"),
	WorkOrderOperation("WorkOrderOperation"),
	WorkOrderDocument("WorkOrderDocument"),
	OrderLicenceDocument("OrderLicenceDocument"),
	WorkOrder("WorkOrder"),
	WorkOrderLot("WorkOrderLot"),
	WorkOrderLotTemp("WorkOrderLotTemp"),
	WOResource("WOResource"),
	WOTempResource("WOTempResource"),
	WOComponent("WOComponent"),
	WOTempComponent("WOTempComponent"),
	QACLERKLIST("QACLERKLIST"),
	@XmlEnumValue("RedeemPointCustomerManagement")
	RedeemPointCustomerManagement("RedeemPointCustomerManagement"),
	NewAddOrderItem("NewAddOrderItem"),
	NewAddQuoteItem("NewAddQuoteItem"),
	@XmlEnumValue("ProjectSupportAssignment")
	ProjectSupportAssignment("ProjectSupportAssignment"),
	@XmlEnumValue("ZoneList")
	ZoneList("ZoneList"),
	@XmlEnumValue("SalesTerritory")
	SalesTerritory("SalesTerritory"),
	@XmlEnumValue("SalesRep")
	SalesRep("SalesRep"),
	@XmlEnumValue("OrgAssignedList")
	OrgAssignedList("OrgAssignedList"),
	@XmlEnumValue("TerrAssignedList")
	TerrAssignedList("TerrAssignedList"),
	OrganizationNoteList("OrganizationNoteList"),
	DivisionNoteList("DivisionNoteList"),
	DepartmentNoteList("DepartmentNoteList"),
	OrganizationSubList("OrganizationSubList"),
	@XmlEnumValue("BillTerritory")
	BillTerritory("BillTerritory"),
	@XmlEnumValue("BillTerritoryZoneList")
	BillTerritoryZoneList("BillTerritoryZoneList"),
	CustCaseDocument("CustCaseDocument"),
	@XmlEnumValue("UpdateRequestLog")
	UpdateRequestLog("UpdateRequestLog"),
	@XmlEnumValue("DataAnalysisMap")
	DataAnalysisMap("DataAnalysisMap"),
	@XmlEnumValue("DsPlateItemsList")
	DsPlateItemsList("DsPlateItemsList")
	;

	private final String value;

	SessionConstant(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static SessionConstant fromValue(String v) {
		for (SessionConstant c : SessionConstant.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
