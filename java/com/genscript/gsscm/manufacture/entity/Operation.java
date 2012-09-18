package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Operation.
 * 
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "operations", catalog = "manufacturing")
public class Operation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_id")
	private Integer id;
	private String name;
	private String description;
	private Integer workCenterId;
	private Integer supervisor;
	private String comment;
	private Integer setupTime;
	@Column(name = "run_time_per_unit")
	private BigDecimal runTime;
	private String uom;
	private String status;
	private String type;
	// 显示用， 非数据库属性
	@Transient
	private String superName;
	@Transient
	private String modifyUser;
	@Transient
	private String workCenterName;
	@Transient
	private List<Integer> delOperationResIdList;
	@Transient
	private List<OperationResource> operationResList;
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

	public Integer getWorkCenterId() {
		return workCenterId;
	}

	public void setWorkCenterId(Integer workCenterId) {
		this.workCenterId = workCenterId;
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

	public Integer getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(Integer setupTime) {
		this.setupTime = setupTime;
	}

	public BigDecimal getRunTime() {
		return runTime;
	}

	public void setRunTime(BigDecimal runTime) {
		this.runTime = runTime;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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

	public String getWorkCenterName() {
		return workCenterName;
	}

	public void setWorkCenterName(String workCenterName) {
		this.workCenterName = workCenterName;
	}

	public List<Integer> getDelOperationResIdList() {
		return delOperationResIdList;
	}

	public void setDelOperationResIdList(List<Integer> delOperationResIdList) {
		this.delOperationResIdList = delOperationResIdList;
	}

	public List<OperationResource> getOperationResList() {
		return operationResList;
	}

	public void setOperationResList(List<OperationResource> operationResList) {
		this.operationResList = operationResList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
