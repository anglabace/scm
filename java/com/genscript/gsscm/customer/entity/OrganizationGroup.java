package com.genscript.gsscm.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * organization_group.
 * 
 * 
 * @author wangsf
 */
@Entity
@Table(name = "organization_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class OrganizationGroup extends BaseEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 301296860310787130L;
	
	@Id
	@Column(name="group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String groupCode;
	private String name;
	private String status;
	private String updateStatusReason;
	private String description;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getUpdateStatusReason() {
		return updateStatusReason;
	}


	public void setUpdateStatusReason(String updateStatusReason) {
		this.updateStatusReason = updateStatusReason;
	}

}
