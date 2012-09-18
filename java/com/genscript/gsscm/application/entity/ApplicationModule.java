package com.genscript.gsscm.application.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * APPLICATION_MODULE.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "application_modules", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationModule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String name;
	private String code;
	private String masterFlag;
	private Boolean localFlag;
	private Integer parentId;
	private String description;
//	private Integer modifiedBy;
//	private Date modifiedDate;
//	private Integer revisionNo;
	@Column(name = "app_id")
	private Integer appId;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "app_mod_id")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ApplicationInterface> applicationInterfaces = new ArrayList<ApplicationInterface>();
	
	public List<ApplicationInterface> getApplicationInterfaces() {
		return applicationInterfaces;
	}
	public void setApplicationInterfaces(
			List<ApplicationInterface> applicationInterfaces) {
		this.applicationInterfaces = applicationInterfaces;
	}
	public String getName() {
		return name;
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
	public String getMasterFlag() {
		return masterFlag;
	}
	public void setMasterFlag(String masterFlag) {
		this.masterFlag = masterFlag;
	}
	public Boolean getLocalFlag() {
		return localFlag;
	}
	public void setLocalFlag(Boolean localFlag) {
		this.localFlag = localFlag;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}