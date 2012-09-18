package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "resources", catalog="manufacturing")
public class Resource extends BaseEntity implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resourceId;
	private String resourceNo;
	private String name;
	private String description;
	@Column(name="`group`")
	private String group;
	private Double cost;
	private String costBasis;
	private String currency;
	private String uom;
	private String status;
	@Column(name="`user_dept`")
	private Integer deptId;
	@Column(name="`work_center_id`")
	private Integer userDept;
	//显示用， 非数据库属性
	@Transient
	private Date modifyDate;
	@Transient
	private String currencySymbol;
	@Transient
	private String modifyUser;
	@Transient
	private Integer workGroupId;
	@Transient
	private String userDeptName;

	public Object clone() {
		Resource cloneTemp = null;
		try {
			cloneTemp = (Resource) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cloneTemp;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceNo() {
		return resourceNo;
	}
	public void setResourceNo(String resourceNo) {
		this.resourceNo = resourceNo;
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
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getCostBasis() {
		return costBasis;
	}
	public void setCostBasis(String costBasis) {
		this.costBasis = costBasis;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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

	public Integer getUserDept() {
		return userDept;
	}

	public void setUserDept(Integer userDept) {
		this.userDept = userDept;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Integer getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(Integer workGroupId) {
		this.workGroupId = workGroupId;
	}

	public String getUserDeptName() {
		return userDeptName;
	}

	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}


	
}
