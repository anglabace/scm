package com.genscript.gsscm.customer.entity;

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
 * CustomerStatusHistory
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "customer_status_history")
public class CustomerStatusHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_history_id")
	private Integer logId;
	private Integer custNo;
	private String priorStat;
	private String currentStat;
	private Date updateDate;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name="updated_by")
	private User updatedBy;
	private String updateReason;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
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
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdateReason() {
		return updateReason;
	}
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

}
