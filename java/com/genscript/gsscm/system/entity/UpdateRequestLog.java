package com.genscript.gsscm.system.entity;

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


@Entity
@Table(name = "update_request_log", catalog="system")
public class UpdateRequestLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 985894062787719491L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(name="object")
	private String objectEntity;
	private String field;
	private Integer objectId;
	private String originalValue;
	private String newValue;
	private String reason;
	private Date requestDate;
    private String note;
	private Integer requestedBy;
	@Transient
	private String requestedByName;
	@Transient
	private String parentId;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
	public String getObjectEntity() {
		return objectEntity;
	}
	public void setObjectEntity(String objectEntity) {
		this.objectEntity = objectEntity;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Integer getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getRequestedByName() {
		return requestedByName;
	}
	public void setRequestedByName(String requestedByName) {
		this.requestedByName = requestedByName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
