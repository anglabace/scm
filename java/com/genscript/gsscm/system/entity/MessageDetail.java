package com.genscript.gsscm.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * MESSAGE_DETAIL.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "message_details", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageDetail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String severityLocal;
	private String summary;
	private String detail;
	private String action;
	private String language;
	@Column(name = "message_id")
	private Integer msgId;
//	private Integer modifiedBy;
//	private Date modifiedDate;
//	private Integer revisionNo;
	
	public String getSeverityLocal() {
		return severityLocal;
	}
	public void setSeverityLocal(String severityLocal) {
		this.severityLocal = severityLocal;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}