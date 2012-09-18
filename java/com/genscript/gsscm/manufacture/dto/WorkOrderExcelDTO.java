package com.genscript.gsscm.manufacture.dto;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.manufacture.entity.WorkOrderLot;
import com.genscript.gsscm.manufacture.entity.WorkOrderOperation;

@XmlType(name = "WorkOrderDTO", namespace = WsConstants.NS)
public class WorkOrderExcelDTO{

	private String orderNo;
    private String itemDesc;
    private String workGroupName;
    private String confirmDate;
    private String targetDate;
    private String squenceLength;
    private String cost;
    private String tam;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getWorkGroupName() {
        return workGroupName;
    }

    public void setWorkGroupName(String workGroupName) {
        this.workGroupName = workGroupName;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getSquenceLength() {
        return squenceLength;
    }

    public void setSquenceLength(String squenceLength) {
        this.squenceLength = squenceLength;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }
}

