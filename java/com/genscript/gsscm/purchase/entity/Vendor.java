package com.genscript.gsscm.purchase.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * PurchaseOrder.
 * 
 * 
 * @author Wangsf.
 */

@Entity
@Table(name = "vendors", catalog="purchase")
public class Vendor extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vendorNo;
	private String altNo;
	private String vendorName;
	private String description;
	private String vendorType;
	private BigDecimal minOrderAmount;
	private Integer paymentTerm;
	private String paymentMethod;
	private String paymentPriority;
	private String shipMethod;
	private String currencyCode;
	private Integer custNo;
	private Integer orgId;
	private Integer divisionId;
	private Integer deptId;
	private String status;
	private Integer companyId;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the vendorNo
	 */
	public Integer getVendorNo() {
		return vendorNo;
	}
	/**
	 * @param vendorNo the vendorNo to set
	 */
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	/**
	 * @return the altNo
	 */
	public String getAltNo() {
		return altNo;
	}
	/**
	 * @param altNo the altNo to set
	 */
	public void setAltNo(String altNo) {
		this.altNo = altNo;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	/**
	 * @return the vendorType
	 */
	public String getVendorType() {
		return vendorType;
	}
	/**
	 * @param vendorType the vendorType to set
	 */
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	/**
	 * @return the minOrderAmount
	 */
	public BigDecimal getMinOrderAmount() {
		return minOrderAmount;
	}
	/**
	 * @param minOrderAmount the minOrderAmount to set
	 */
	public void setMinOrderAmount(BigDecimal minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}
	/**
	 * @return the paymentTerm
	 */
	public Integer getPaymentTerm() {
		return paymentTerm;
	}
	/**
	 * @param paymentTerm the paymentTerm to set
	 */
	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	/**
	 * @return the paymentPriority
	 */
	public String getPaymentPriority() {
		return paymentPriority;
	}
	/**
	 * @param paymentPriority the paymentPriority to set
	 */
	public void setPaymentPriority(String paymentPriority) {
		this.paymentPriority = paymentPriority;
	}
	/**
	 * @return the shipMethod
	 */
	public String getShipMethod() {
		return shipMethod;
	}
	/**
	 * @param shipMethod the shipMethod to set
	 */
	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	/**
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}
	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the divisionId
	 */
	public Integer getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId the divisionId to set
	 */
	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
