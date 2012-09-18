package com.genscript.gsscm.inventory.entity;

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
 * WAREHOUSES.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "warehouses", catalog="inventory")
public class Warehouse extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warehouse_id")
	private Integer warehouseId;
	private String name;
	private String costMethod;
	private String defaultStorage;
	private String type;
	private String defaultFlag;
	private Integer companyId;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "warehouse_id")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("storageId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Storage> storageList = new ArrayList<Storage>();
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCostMethod() {
		return costMethod;
	}
	public void setCostMethod(String costMethod) {
		this.costMethod = costMethod;
	}
	public String getDefaultStorage() {
		return defaultStorage;
	}
	public void setDefaultStorage(String defaultStorage) {
		this.defaultStorage = defaultStorage;
	}
	
	public List<Storage> getStorageList() {
		return storageList;
	}
	public void setStorageList(List<Storage> storageList) {
		this.storageList = storageList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
}
