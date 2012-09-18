package com.genscript.gsscm.report.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/1/11
 * Time: 10:09 AM
 *只是用来在reports的Sales Points Summary 显示之用，所以字段会有所不同
 */
public class SalesPoints {
    private String orgTypeName;
    private String fromDate;
    private String toDate;
    private String custName;
    private String orderNo;
    private Integer eaPoints;
    private Integer redPoints;
    private Integer balancePoints;
    private Object dateInt;

    public Object getDateInt() {
        return dateInt;
    }

    public void setDateInt(Object dateInt) {
        this.dateInt = dateInt;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getEaPoints() {
        return eaPoints;
    }

    public void setEaPoints(Integer eaPoints) {
        this.eaPoints = eaPoints;
    }

    public Integer getRedPoints() {
        return redPoints;
    }

    public void setRedPoints(Integer redPoints) {
        this.redPoints = redPoints;
    }

    public Integer getBalancePoints() {
        return balancePoints;
    }

    public void setBalancePoints(Integer balancePoints) {
        this.balancePoints = balancePoints;
    }
}
