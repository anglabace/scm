package com.genscript.gsscm.order.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.privilege.entity.User;

/**
 * ORDER PROCESS LOG.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_status_history", catalog="order")
public class OrderProcessLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_history_id")
	private Integer logId;
	private Integer orderNo;
	private Integer orderItemId;
	private String priorStat;
	private String currentStat;
	@Column(name = "update_date")
	private Date processDate;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name="updated_by")
	private User processedBy;
	private String note;
	@Column(name = "update_reason")
	private String reason;
	private String orderDetail;
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public String getPriorStat() {
		return priorStat;
	}
	public void setPriorStat(String priorStat) {
		this.priorStat = priorStat;
	}
	public String getCurrentStat() {
		return currentStat;
	}
	public void setCurrentStat(String currentStat) {
		this.currentStat = currentStat;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	public User getProcessedBy() {
		return processedBy;
	}
	public void setProcessedBy(User processedBy) {
		this.processedBy = processedBy;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
