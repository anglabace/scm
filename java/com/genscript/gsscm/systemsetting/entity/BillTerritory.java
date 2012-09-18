package com.genscript.gsscm.systemsetting.entity;

import java.io.Serializable;

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


@Entity
@Table(name = "bill_territory", catalog="common")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillTerritory extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 3465386394404418781L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "territory_id")
	private Integer territoryId;
	@Column(name = "territory_code")
	private String territoryCode;
	@Column(name = "territory_name")
	private String territoryName;
	@Column(name = "description")
	private String description;
	@Column(name = "account_code")
	private String accountCode;
	@Column(name = "status")
	private String status;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	public String getTerritoryCode() {
		return territoryCode;
	}

	public void setTerritoryCode(String territoryCode) {
		this.territoryCode = territoryCode;
	}

	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
}
