package com.genscript.gsscm.inventory.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name="StorageDTO", namespace=WsConstants.NS)
public class StorageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3359883723599595370L;
	private Integer storageId;
	
	private WarehouseDTO warehouse;
	private String name;
	public Integer getStorageId() {
		return storageId;
	}
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}
	@XmlTransient
	public WarehouseDTO getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(WarehouseDTO warehouse) {
		this.warehouse = warehouse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
