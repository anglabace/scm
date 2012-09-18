package com.genscript.gsscm.system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * MESSAGE.
 * 
 * Use JPA annotation defines the relationship of ORM.
 * Use Hibernate annotation describe the definition of second-level cache.
 * 
 * @author Golf
 */
@Entity
// The table name and class name does not redefine the table with the same name.
//@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true)
@Table(name = "messages", catalog="system")
// The default cache strategy.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String code;
	private String description;
	private String severity;
//	private Integer modifiedBy;
//	private Date modifiedDate;
//	private Integer revisionNo;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "message_id")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MessageDetail> messageDetails = new ArrayList<MessageDetail>();
	
	@OneToMany(mappedBy = "messageId", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "message_id")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<MessageLog> messageLogs = new ArrayList<MessageLog>();

	public List<MessageLog> getMessageLogs() {
		return messageLogs;
	}

	public void setMessageLogs(List<MessageLog> messageLogs) {
		this.messageLogs = messageLogs;
	}

	public void setMessageDetails(List<MessageDetail> messageDetails) {
		this.messageDetails = messageDetails;
	}

	
	public List<MessageDetail> getMessageDetails() {
		return messageDetails;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
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