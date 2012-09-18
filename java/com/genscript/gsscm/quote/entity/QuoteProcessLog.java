package com.genscript.gsscm.quote.entity;

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
 * QUOTATION PROCESS LOG.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_status_history", catalog="order")
public class QuoteProcessLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_history_id")
	private Integer logId;
	private Integer quoteNo;
	private Integer quoteItemId;
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
	
	
	public Integer getLogId() {
		return logId;
	}


	public void setLogId(Integer logId) {
		this.logId = logId;
	}


	public Integer getQuoteNo() {
		return quoteNo;
	}


	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}


	public Integer getQuoteItemId() {
		return quoteItemId;
	}


	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
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


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	/**
	 * @return the processedBy
	 */
	public User getProcessedBy() {
		return processedBy;
	}


	/**
	 * @param processedBy the processedBy to set
	 */
	public void setProcessedBy(User processedBy) {
		this.processedBy = processedBy;
	}
}
