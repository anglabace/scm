package com.genscript.gsscm.shipment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ShipRateCustomerBasic.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "ship_rate_custom_basic", catalog = "shipping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShipRateCustomerBasic extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339227207901902549L;

	@Id
	private Integer shipMethodId;
	private BigDecimal perBoxChrg;
	private String perBoxAppTp;
	private BigDecimal perItemChrg;
	private String perItemAppTp;
	private BigDecimal perPndChrg;
	private String wtChargeType;
	private String wtChrgAppTp;
	private String sepTotForAddr;
	private BigDecimal actualChrgPct;
	private String actualChrgTp;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the shipMethodId
	 */
	public Integer getShipMethodId() {
		return shipMethodId;
	}
	/**
	 * @param shipMethodId the shipMethodId to set
	 */
	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}
	/**
	 * @return the perBoxChrg
	 */
	public BigDecimal getPerBoxChrg() {
		return perBoxChrg;
	}
	/**
	 * @param perBoxChrg the perBoxChrg to set
	 */
	public void setPerBoxChrg(BigDecimal perBoxChrg) {
		this.perBoxChrg = perBoxChrg;
	}
	/**
	 * @return the perBoxAppTp
	 */
	public String getPerBoxAppTp() {
		return perBoxAppTp;
	}
	/**
	 * @param perBoxAppTp the perBoxAppTp to set
	 */
	public void setPerBoxAppTp(String perBoxAppTp) {
		this.perBoxAppTp = perBoxAppTp;
	}
	/**
	 * @return the perItemChrg
	 */
	public BigDecimal getPerItemChrg() {
		return perItemChrg;
	}
	/**
	 * @param perItemChrg the perItemChrg to set
	 */
	public void setPerItemChrg(BigDecimal perItemChrg) {
		this.perItemChrg = perItemChrg;
	}
	/**
	 * @return the perItemAppTp
	 */
	public String getPerItemAppTp() {
		return perItemAppTp;
	}
	/**
	 * @param perItemAppTp the perItemAppTp to set
	 */
	public void setPerItemAppTp(String perItemAppTp) {
		this.perItemAppTp = perItemAppTp;
	}
	/**
	 * @return the perPndChrg
	 */
	public BigDecimal getPerPndChrg() {
		return perPndChrg;
	}
	/**
	 * @param perPndChrg the perPndChrg to set
	 */
	public void setPerPndChrg(BigDecimal perPndChrg) {
		this.perPndChrg = perPndChrg;
	}
	/**
	 * @return the wtChargeType
	 */
	public String getWtChargeType() {
		return wtChargeType;
	}
	/**
	 * @param wtChargeType the wtChargeType to set
	 */
	public void setWtChargeType(String wtChargeType) {
		this.wtChargeType = wtChargeType;
	}
	/**
	 * @return the wtChrgAppTp
	 */
	public String getWtChrgAppTp() {
		return wtChrgAppTp;
	}
	/**
	 * @param wtChrgAppTp the wtChrgAppTp to set
	 */
	public void setWtChrgAppTp(String wtChrgAppTp) {
		this.wtChrgAppTp = wtChrgAppTp;
	}
	/**
	 * @return the sepTotForAddr
	 */
	public String getSepTotForAddr() {
		return sepTotForAddr;
	}
	/**
	 * @param sepTotForAddr the sepTotForAddr to set
	 */
	public void setSepTotForAddr(String sepTotForAddr) {
		this.sepTotForAddr = sepTotForAddr;
	}
	/**
	 * @return the actualChrgPct
	 */
	public BigDecimal getActualChrgPct() {
		return actualChrgPct;
	}
	/**
	 * @param actualChrgPct the actualChrgPct to set
	 */
	public void setActualChrgPct(BigDecimal actualChrgPct) {
		this.actualChrgPct = actualChrgPct;
	}
	/**
	 * @return the actualChrgTp
	 */
	public String getActualChrgTp() {
		return actualChrgTp;
	}
	/**
	 * @param actualChrgTp the actualChrgTp to set
	 */
	public void setActualChrgTp(String actualChrgTp) {
		this.actualChrgTp = actualChrgTp;
	}
}
