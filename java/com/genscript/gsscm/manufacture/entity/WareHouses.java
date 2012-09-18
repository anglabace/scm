package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
/**
 * WareHouses.
 * 
 * 
 * @author lizhang.
 */

@Entity
@Table(name = "warehouses", catalog="manufacturing")
public class WareHouses extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warehouses_id")
	private Integer warehousesId;
	private String name;
	private String costMethod;
	private String defaultStorage;
	private String type;
	private String defaultFlg;
	private Integer companyId;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public WareHouses() {
	}

	public Integer getWarehousesId() {
		return warehousesId;
	}

	public void setWarehousesId(Integer warehousesId) {
		this.warehousesId = warehousesId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultFlg() {
		return defaultFlg;
	}

	public void setDefaultFlg(String defaultFlg) {
		this.defaultFlg = defaultFlg;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



}
