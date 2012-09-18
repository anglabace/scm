package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * QaClerk.
 * 
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "qa_clerks", catalog = "manufacturing")
public class QaClerk extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer userId;
	private Integer supervisor;
//	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JoinColumn(name = "group_id")
//	private QaGroup qaGroup;
	private Integer groupId;
	private String comment;
	private String status;
	// 显示用， 非数据库属性
	@Transient
	private String superName;
	@Transient
	private String modifyUser;
	@Transient
	private String groupName;
	@Transient
	private String productService;
	@Transient
	private String clerkName;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSuperName() {
		return superName;
	}
	public void setSuperName(String superName) {
		this.superName = superName;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getProductService() {
		return productService;
	}
	public void setProductService(String productService) {
		this.productService = productService;
	}
//	public QaGroup getQaGroup() {
//		return qaGroup;
//	}
//	public void setQaGroup(QaGroup qaGroup) {
//		this.qaGroup = qaGroup;
//	}
	public String getClerkName() {
		return clerkName;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
