package com.genscript.gsscm.report.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/26/11
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "work_orders", catalog = "manufacturing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkOrderSummary implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
    private Integer orderNo;
    private String workCenterName;
    private String status;
    private Integer orderNum;
    private String baseNum;
    private BigDecimal total;
    private Double successRatio;
    private Double DelayedRatio;
    private Double failedRatio;
    private String fromDate;
    private String toDate;
    private String workGroupName;
    private Integer delieryNum;
    private Integer delayedNum;
    @Transient
    private Object dateInt;

    public Integer getDelieryNum() {
        return delieryNum;
    }

    public void setDelieryNum(Integer delieryNum) {
        this.delieryNum = delieryNum;
    }

    public Integer getDelayedNum() {
        return delayedNum;
    }

    public void setDelayedNum(Integer delayedNum) {
        this.delayedNum = delayedNum;
    }

    public Object getDateInt() {
        return dateInt;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public void setDateInt(Object dateInt) {
        this.dateInt = dateInt;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(String baseNum) {
        this.baseNum = baseNum;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Double getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(Double successRatio) {
        this.successRatio = successRatio;
    }

    public Double getDelayedRatio() {
        return DelayedRatio;
    }

    public void setDelayedRatio(Double delayedRatio) {
        DelayedRatio = delayedRatio;
    }

    public Double getFailedRatio() {
        return failedRatio;
    }

    public void setFailedRatio(Double failedRatio) {
        this.failedRatio = failedRatio;
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
}
