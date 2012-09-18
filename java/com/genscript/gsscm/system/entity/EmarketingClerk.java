/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.system.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jinsite
 */
@Entity
@Table(name="marketing_clerks",catalog="product")
public class EmarketingClerk {
    @Id
    @Column(name="clerk_id")
    private Integer clerkId;
    private Integer marketingGroup;
    private String description;
    private String status;
    private Date creationDate;
    private Integer createdBy;
    private Date modifyDate;
    private Integer modifiedBy;
    @Transient
    private String name;

    /**
     * @return the clerkId
     */
    public Integer getClerkId() {
        return clerkId;
    }

    /**
     * @param clerkId the clerkId to set
     */
    public void setClerkId(Integer clerkId) {
        this.clerkId = clerkId;
    }

  
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the marketingGroup
     */
    public Integer getMarketingGroup() {
        return marketingGroup;
    }

    /**
     * @param marketingGroup the marketingGroup to set
     */
    public void setMarketingGroup(Integer marketingGroup) {
        this.marketingGroup = marketingGroup;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

   
}
