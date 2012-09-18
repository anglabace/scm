package com.genscript.gsscm.privilege.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "user_assignments", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assign_id")
	private Integer id;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;
	private String effFrom;
	private String effTo;
	private Integer assignedBy;
	private Date assignDate;
	@Column(insertable=true, updatable=false)
	private Date creationDate;
	@Column(insertable=true, updatable=false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

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
	 * @return the effFrom
	 */
	public String getEffFrom() {
		return effFrom;
	}

	/**
	 * @param effFrom the effFrom to set
	 */
	public void setEffFrom(String effFrom) {
		this.effFrom = effFrom;
	}

	/**
	 * @return the effTo
	 */
	public String getEffTo() {
		return effTo;
	}

	/**
	 * @param effTo the effTo to set
	 */
	public void setEffTo(String effTo) {
		this.effTo = effTo;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	


}
