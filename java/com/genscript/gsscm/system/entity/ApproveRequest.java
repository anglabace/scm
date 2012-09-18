package com.genscript.gsscm.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Approve Request.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "approve_requests", catalog="system")
public class ApproveRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Integer requestId;
	private String tableName;
	private Integer objectId;
	private String approveStatus;
	private String rejectReason;
	private Integer requestedBy;
	private Date requestDate;
	private Integer processedBy;
	private Date processDate;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "requestId")
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ApproveRequestDetail> approveRequestDetails = new ArrayList<ApproveRequestDetail>();
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Integer getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Integer getProcessedBy() {
		return processedBy;
	}
	public void setProcessedBy(Integer processedBy) {
		this.processedBy = processedBy;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public List<ApproveRequestDetail> getApproveRequestDetails() {
		return approveRequestDetails;
	}
	public void setApproveRequestDetails(
			List<ApproveRequestDetail> approveRequestDetails) {
		this.approveRequestDetails = approveRequestDetails;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
