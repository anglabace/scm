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
 * APPLICATION.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "applications", catalog="system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Application extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String name;
	private String description;
	private String versionNo;
//	private Integer modifiedBy;
//	private Date modifiedDate;
//	private Integer revisionNo;
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "app_id")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ApplicationModule> applicationModules = new ArrayList<ApplicationModule>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<ApplicationModule> getApplicationModules() {
		return applicationModules;
	}
	public void setApplicationModules(List<ApplicationModule> applicationModules) {
		this.applicationModules = applicationModules;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
