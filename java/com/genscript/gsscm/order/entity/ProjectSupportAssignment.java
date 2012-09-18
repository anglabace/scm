package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author zhangyong
 */
@Entity
@Table(name = "project_support_assignment", catalog="order")
public class ProjectSupportAssignment implements Serializable {
	
	private static final long serialVersionUID = -4082680035638349126L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer serviceClsId;
	private Integer projectSupportId;
	private String comments;
	@Column(updatable = false)
	private Date creationDate;
	@Column(updatable = false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getServiceClsId() {
		return serviceClsId;
	}
	public void setServiceClsId(Integer serviceClsId) {
		this.serviceClsId = serviceClsId;
	}
	public Integer getProjectSupportId() {
		return projectSupportId;
	}
	public void setProjectSupportId(Integer projectSupportId) {
		this.projectSupportId = projectSupportId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
