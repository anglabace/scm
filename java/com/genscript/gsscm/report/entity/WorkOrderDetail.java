package com.genscript.gsscm.report.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/29/11
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "work_orders", catalog = "manufacturing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkOrderDetail implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
    private Integer orderNo;
    private String workCenterName;
    private String workGroupName;
    private String status;
    private String itemType;
    private String scheduleStart;
    private String scheduleEnd;
    private String modComDate;
    private String scdDate;
    private String scdReason;
    private String actualStart;
    private String actualEnd;
    private String delayDays;
    private String cancelledDate;
    private String cancelledReason ;
    private String operationName;
    private Integer typeId;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public String getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(String scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    public String getModComDate() {
        return modComDate;
    }

    public void setModComDate(String modComDate) {
        this.modComDate = modComDate;
    }

    public String getScdDate() {
        return scdDate;
    }

    public void setScdDate(String scdDate) {
        this.scdDate = scdDate;
    }

    public String getScdReason() {
        return scdReason;
    }

    public void setScdReason(String scdReason) {
        this.scdReason = scdReason;
    }

    public String getActualStart() {
        return actualStart;
    }

    public void setActualStart(String actualStart) {
        this.actualStart = actualStart;
    }

    public String getActualEnd() {
        return actualEnd;
    }

    public void setActualEnd(String actualEnd) {
        this.actualEnd = actualEnd;
    }

    public String getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(String delayDays) {
        this.delayDays = delayDays;
    }

    public String getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(String cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason) {
        this.cancelledReason = cancelledReason;
    }
}
