/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.shipment.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jinsite
 */

@Entity
@Table(name="shipping_clerk_assigned",catalog="shipping")
public class ShipClerkAssigned implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_id")
    private Integer assignId;
    private Integer warehouseId;
    private String itemType;
    private Integer clsId;
    private Integer shippingClerk;
    private Date assignDate;
    private Integer assignedBy;
    @Transient
    private String clsName;

    /**
     * @return the assignId
     */
    public Integer getAssignId() {
        return assignId;
    }

    /**
     * @param assignId the assignId to set
     */
    public void setAssignId(Integer assignId) {
        this.assignId = assignId;
    }

    /**
     * @return the warehouseId
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * @param warehouseId the warehouseId to set
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the clsId
     */
    public Integer getClsId() {
        return clsId;
    }

    /**
     * @param clsId the clsId to set
     */
    public void setClsId(Integer clsId) {
        this.clsId = clsId;
    }

    /**
     * @return the shippingClerk
     */
    public Integer getShippingClerk() {
        return shippingClerk;
    }

    /**
     * @param shippingClerk the shippingClerk to set
     */
    public void setShippingClerk(Integer shippingClerk) {
        this.shippingClerk = shippingClerk;
    }

    /**
     * @return the assignDate
     */
    public Date getAssignDate() {
        return assignDate;
    }

    /**
     * @param assignDate the assignDate to set
     */
    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    /**
     * @return the assignedBy
     */
    public Integer getAssignedBy() {
        return assignedBy;
    }

    /**
     * @param assignedBy the assignedBy to set
     */
    public void setAssignedBy(Integer assignedBy) {
        this.assignedBy = assignedBy;
    }

    /**
     * @return the clsName
     */
    public String getClsName() {
        return clsName;
    }

    /**
     * @param clsName the clsName to set
     */
    public void setClsName(String clsName) {
        this.clsName = clsName;
    }


}
