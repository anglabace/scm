package com.genscript.gsscm.privilege.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.entity.Employee;


@XmlType(name = "UserDTO", namespace = WsConstants.NS)
public class UserDTO {
	private Integer userId;
	private String loginName;
	private Employee employee;
	private String effFrom;
	private String effTo;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private List<Integer> roleIdList;
	private List<Integer> pvlgIdList;
	private Integer lastLoginId;
	private Integer companyId;
	private String firstName;
	private String lastName;
	private Integer deptId;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	 * @return the roleIdList
	 */
	public List<Integer> getRoleIdList() {
		return roleIdList;
	}
	/**
	 * @param roleIdList the roleIdList to set
	 */
	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
	/**
	 * @return the pvlgIdList
	 */
	public List<Integer> getPvlgIdList() {
		return pvlgIdList;
	}
	/**
	 * @param pvlgIdList the pvlgIdList to set
	 */
	public void setPvlgIdList(List<Integer> pvlgIdList) {
		this.pvlgIdList = pvlgIdList;
	}
	/**
	 * @return the lastLoginId
	 */
	public Integer getLastLoginId() {
		return lastLoginId;
	}
	/**
	 * @param lastLoginId the lastLoginId to set
	 */
	public void setLastLoginId(Integer lastLoginId) {
		this.lastLoginId = lastLoginId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}


}
