package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrderReturn.
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "order_return", catalog="order")
public class OrderReturn extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4797965204043212350L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rma_no")
	private Integer rmaNo;
	private Integer orderNo;
	private String status;
	private Date exprDate;
	private Integer processedBy;
	private Date processDate;
	private BigDecimal shipRefund;
	private BigDecimal totalRefund;
	private String note;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	 * @return the exprDate
	 */
	public Date getExprDate() {
		return exprDate;
	}
	/**
	 * @param exprDate the exprDate to set
	 */
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	/**
	 * @return the processedBy
	 */
	public Integer getProcessedBy() {
		return processedBy;
	}
	/**
	 * @param processedBy the processedBy to set
	 */
	public void setProcessedBy(Integer processedBy) {
		this.processedBy = processedBy;
	}
	/**
	 * @return the shipRefund
	 */
	public BigDecimal getShipRefund() {
		return shipRefund;
	}
	/**
	 * @param shipRefund the shipRefund to set
	 */
	public void setShipRefund(BigDecimal shipRefund) {
		this.shipRefund = shipRefund;
	}
	/**
	 * @return the totalRefund
	 */
	public BigDecimal getTotalRefund() {
		return totalRefund;
	}
	/**
	 * @param totalRefund the totalRefund to set
	 */
	public void setTotalRefund(BigDecimal totalRefund) {
		this.totalRefund = totalRefund;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the rmaNo
	 */
	public Integer getRmaNo() {
		return rmaNo;
	}

	/**
	 * @param rmaNo the rmaNo to set
	 */
	public void setRmaNo(Integer rmaNo) {
		this.rmaNo = rmaNo;
	}

	/**
	 * @return the processDate
	 */
	public Date getProcessDate() {
		return processDate;
	}

	/**
	 * @param processDate the processDate to set
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
}
