/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.system.entity;

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
@Table(name="marketing_group_assigned",catalog="product")
public class EmarketingGroupAssigned {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private String itemType;
    private Integer clsId;
    private Integer marketingGroupId;
    private Date creationDate;
    private Integer createdBy;
    private Date modifyDate;
    private Integer modifiedBy;
    @Transient
    private String clsName;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the marketingGroupId
     */
    public Integer getMarketingGroupId() {
        return marketingGroupId;
    }

    /**
     * @param marketingGroupId the marketingGroupId to set
     */
    public void setMarketingGroupId(Integer marketingGroupId) {
        this.marketingGroupId = marketingGroupId;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the createdBy
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the modifyDate
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate the modifyDate to set
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @return the modifiedBy
     */
    public Integer getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
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
