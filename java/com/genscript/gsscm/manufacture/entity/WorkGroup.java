package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * Resource.
 * 
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "work_groups", catalog="manufacturing")
public class WorkGroup extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="work_group_id")
	private Integer id;
	private Integer workCenterId;
	private String name;
	private String description;
	private Integer supervisor;
	private String comment;
	private String abrCode;
	private Integer abrNo;
	private String status;
	//显示用， 非数据库属性
	@Transient
	private String superName;
	@Transient
	private String modifyUser;
	//保存WorkGroup 同时保存关联的Group Resource.
	@Transient
	private List<Integer> delGroupResIdList;
	@Transient
	private List<GroupResource> groupResList;
	
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

	public Integer getWorkCenterId() {
		return workCenterId;
	}

	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Integer> getDelGroupResIdList() {
		return delGroupResIdList;
	}

	public void setDelGroupResIdList(List<Integer> delGroupResIdList) {
		this.delGroupResIdList = delGroupResIdList;
	}

	public List<GroupResource> getGroupResList() {
		return groupResList;
	}

	public void setGroupResList(List<GroupResource> groupResList) {
		this.groupResList = groupResList;
	}

	public String getAbrCode() {
		return abrCode;
	}

	public void setAbrCode(String abrCode) {
		this.abrCode = abrCode;
	}

	public Integer getAbrNo() {
		return abrNo;
	}

	public void setAbrNo(Integer abrNo) {
		this.abrNo = abrNo;
	}

}
