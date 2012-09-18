package com.genscript.gsscm.privilege.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "privilege_list", catalog = "system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Privilege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "privilege_id")
	private Integer privilegeId;
	private String privilegeCode;
	private String parentCode;
	private String privilegeType;
	private String privilegeName;
	@Column(name = "privilege_link")
	private String privilegeAttr;
	private String relatedPrivilege;
	private String privilegeAction;
	private String description;
	@Column(insertable = true, updatable = false)
	private Date creationDate;
	@Column(insertable = true, updatable = false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the privilegeId
	 */
	public Integer getPrivilegeId() {
		return privilegeId;
	}

	/**
	 * @param privilegeId
	 *            the privilegeId to set
	 */
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	/**
	 * @return the privilegeCode
	 */
	public String getPrivilegeCode() {
		return privilegeCode;
	}

	/**
	 * @param privilegeCode
	 *            the privilegeCode to set
	 */
	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode
	 *            the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the privilegeType
	 */
	public String getPrivilegeType() {
		return privilegeType;
	}

	/**
	 * @param privilegeType
	 *            the privilegeType to set
	 */
	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	/**
	 * @return the privilegeName
	 */
	public String getPrivilegeName() {
		return privilegeName;
	}

	/**
	 * @param privilegeName
	 *            the privilegeName to set
	 */
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	/**
	 * @return the privilegeAttr
	 */
	public String getPrivilegeAttr() {
		return privilegeAttr;
	}

	/**
	 * @param privilegeAttr
	 *            the privilegeAttr to set
	 */
	public void setPrivilegeAttr(String privilegeAttr) {
		this.privilegeAttr = privilegeAttr;
	}

	/**
	 * @return the relatedPrivilege
	 */
	public String getRelatedPrivilege() {
		return relatedPrivilege;
	}

	/**
	 * @param relatedPrivilege
	 *            the relatedPrivilege to set
	 */
	public void setRelatedPrivilege(String relatedPrivilege) {
		this.relatedPrivilege = relatedPrivilege;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param modifyDate
	 *            the modifyDate to set
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
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the privilegeAction
	 */
	public String getPrivilegeAction() {
		return privilegeAction;
	}

	/**
	 * @param privilegeAction the privilegeAction to set
	 */
	public void setPrivilegeAction(String privilegeAction) {
		this.privilegeAction = privilegeAction;
	}

}
