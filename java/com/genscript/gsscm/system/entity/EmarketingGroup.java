/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author jinsite
 */
@Entity
@Table(name="marketing_groups",catalog="product")
public class EmarketingGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer groupId;
    private String groupName;
    private String description;
    private Integer supervisor;
    private String status;
    private Date creationDate;
    private Integer createdBy;
    private Date modifyDate;
    private Integer modifiedBy;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="marketingGroup")
    @Fetch(FetchMode.SUBSELECT)
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private List<EmarketingClerk> clerkList;
    @Transient
    private String typeNames;
    @Transient
    private String name;




    /**
     * @return the groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * @return the supervisor
     */
    public Integer getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
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
     * @return the clerkList
     */
    public List<EmarketingClerk> getClerkList() {
        return clerkList;
    }

    /**
     * @param clerkList the clerkList to set
     */
    public void setClerkList(List<EmarketingClerk> clerkList) {
        this.setClerkList(clerkList);
    }

    /**
     * @return the typeNames
     */
    public String getTypeNames() {
        return typeNames;
    }

    /**
     * @param typeNames the typeNames to set
     */
    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
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
     * @return the crteatedBy
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param crteatedBy the crteatedBy to set
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
   
}
