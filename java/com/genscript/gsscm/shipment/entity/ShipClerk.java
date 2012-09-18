/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genscript.gsscm.shipment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.gsscm.privilege.entity.User;

/**
 *
 * @author jinsite
 */
@Entity
@Table(name = "shipping_clerks", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShipClerk implements Serializable {

    @Id
    @Column(name = "clerk_id")
    private Integer clerkId;
    private String clerkFunction;
    private Integer supervisor;
    private String comment;
    private String status;
    private Date creationDate;
    private Integer createdBy;
    private Date modifyDate;
    private Integer modifiedBy;
    @Transient
    private String typeNames;
    @Transient
    private String name;
    @Transient 
    private Integer employeeId;

    @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="clerk_id",referencedColumnName="user_id")
    private User user;
    
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
     * @return the clerkFunction
     */
    public String getClerkFunction() {
        return clerkFunction;
    }

    /**
     * @param clerkFunction the clerkFunction to set
     */
    public void setClerkFunction(String clerkFunction) {
        this.clerkFunction = clerkFunction;
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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
}
