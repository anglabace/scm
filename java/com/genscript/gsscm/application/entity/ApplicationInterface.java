package com.genscript.gsscm.application.entity;

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
 * APPLICATION_INTERFACE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "application_interfaces", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationInterface extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String name;
	private String code;
	private String description;
	@Column(name = "app_mod_id")
	private Integer appModId;
	
//	private Integer modifiedBy;
//	private Date modifiedDate;
//	private Integer revisionNo;
	
	
	public String getName() {
		return name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Integer getAppModId() {
		return appModId;
	}
	public void setAppModId(Integer appModId) {
		this.appModId = appModId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}