package com.genscript.gsscm.report.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 6/30/11
 * Time: 5:26 PM
 * report 模块统一DTO
 */
public class ReportDataDto {
    private String dataRange;
    private String dataFrom;
    private String dataTo;
    private String periodType;
    private List<String> status;
    private String currency;
    private List<String> column;
    private List<String> columnName;
    private List reportData;
    //Quotation Detail 添加
    private String TaxingScheme;
    private String customer;
    private String sortBy1;
    private String sortBy2;
    private String sortBy3;
    private String reportPicName;
    private BigDecimal closeRatio;
    //quotation or order
    private String type;
    //Territory
    private String territory;
    //Sales Department
    private String salesDepartment;
    //Sales Representative
    private String SalesRepresentative;
    //Organization Type
    private String orgType;
    //Organization Name
    private String orgName;
    private String orgId;
    //Country
    private String country;
    //Product/Service Sales Summary
    private String segmentType;
    private String busUnitId;
    private String categoryId;
    private String psLineId;
    private String psId;
    private String psName;
    private Integer orderSource;
    //Purchase Order Detail
    private Integer vendorId;
    //Work Order Summary
    private Integer workCenterId;
    private String source;
    private String psType;
    private Integer delayFrom;
    private Integer delayTo;
    private String delayType;
    //Work Order Detail
    private Integer workGroupId;
    //Receipt Detail
    private Integer receivingClerk;
    //Sales Manager
    private String salesManager;
    //Technical Account Manager
    private String techManager;
    //Project Manager
    private String projectManager;
    //sale type
    private String reportType;
    //Receipt Detail
    private String purchaseOrderNo;
    private String salesOrderNo;
    private Integer shippingCarrierId;
    private String trackingNo;
    //work order summary
    private String targetDateFrom;
    private String targetDateTo;
    private Integer shipperClerk;
    //Includes step items
    private String stepFlag;

    public String getStepFlag() {
        return stepFlag;
    }

    public void setStepFlag(String stepFlag) {
        this.stepFlag = stepFlag;
    }

    public Integer getShipperClerk() {
        return shipperClerk;
    }

    public void setShipperClerk(Integer shipperClerk) {
        this.shipperClerk = shipperClerk;
    }

    public String getTargetDateFrom() {
        return targetDateFrom;
    }

    public void setTargetDateFrom(String targetDateFrom) {
        this.targetDateFrom = targetDateFrom;
    }

    public String getTargetDateTo() {
        return targetDateTo;
    }

    public void setTargetDateTo(String targetDateTo) {
        this.targetDateTo = targetDateTo;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public Integer getReceivingClerk() {
        return receivingClerk;
    }

    public void setReceivingClerk(Integer receivingClerk) {
        this.receivingClerk = receivingClerk;
    }

    public Integer getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(Integer workGroupId) {
        this.workGroupId = workGroupId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPsType() {
        return psType;
    }

    public void setPsType(String psType) {
        this.psType = psType;
    }

    public Integer getDelayFrom() {
        return delayFrom;
    }

    public void setDelayFrom(Integer delayFrom) {
        this.delayFrom = delayFrom;
    }

    public Integer getDelayTo() {
        return delayTo;
    }

    public void setDelayTo(Integer delayTo) {
        this.delayTo = delayTo;
    }

    public String getDelayType() {
        return delayType;
    }

    public void setDelayType(String delayType) {
        this.delayType = delayType;
    }

    public Integer getWorkCenterId() {
        return workCenterId;
    }

    public void setWorkCenterId(Integer workCenterId) {
        this.workCenterId = workCenterId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public String getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }

    public String getBusUnitId() {
        return busUnitId;
    }

    public void setBusUnitId(String busUnitId) {
        this.busUnitId = busUnitId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPsLineId() {
        return psLineId;
    }

    public void setPsLineId(String psLineId) {
        this.psLineId = psLineId;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReportPicName() {
        return reportPicName;
    }

    public void setReportPicName(String reportPicName) {
        this.reportPicName = reportPicName;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    public String getDataTo() {
        return dataTo;
    }

    public void setDataTo(String dataTo) {
        this.dataTo = dataTo;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    public List getReportData() {
        return reportData;
    }

    public void setReportData(List reportData) {
        this.reportData = reportData;
    }

    public String getTaxingScheme() {
        return TaxingScheme;
    }

    public void setTaxingScheme(String taxingScheme) {
        TaxingScheme = taxingScheme;
    }

    public String getSortBy1() {
        return sortBy1;
    }

    public void setSortBy1(String sortBy1) {
        this.sortBy1 = sortBy1;
    }

    public String getSortBy2() {
        return sortBy2;
    }

    public void setSortBy2(String sortBy2) {
        this.sortBy2 = sortBy2;
    }

    public String getSortBy3() {
        return sortBy3;
    }

    public void setSortBy3(String sortBy3) {
        this.sortBy3 = sortBy3;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getSalesDepartment() {
        return salesDepartment;
    }

    public void setSalesDepartment(String salesDepartment) {
        this.salesDepartment = salesDepartment;
    }

    public String getSalesRepresentative() {
        return SalesRepresentative;
    }

    public void setSalesRepresentative(String salesRepresentative) {
        SalesRepresentative = salesRepresentative;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getCloseRatio() {
        return closeRatio;
    }

    public void setCloseRatio(BigDecimal closeRatio) {
        this.closeRatio = closeRatio;
    }

    public String getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(String salesManager) {
        this.salesManager = salesManager;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getTechManager() {
        return techManager;
    }

    public void setTechManager(String techManager) {
        this.techManager = techManager;
    }

    public Integer getShippingCarrierId() {
        return shippingCarrierId;
    }

    public void setShippingCarrierId(Integer shippingCarrierId) {
        this.shippingCarrierId = shippingCarrierId;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
}
