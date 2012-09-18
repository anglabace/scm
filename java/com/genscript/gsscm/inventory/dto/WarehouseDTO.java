package com.genscript.gsscm.inventory.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "WarehouseDTO", namespace = WsConstants.NS)
public class WarehouseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7902519375026024584L;
	private Integer warehouseId;
	private String name;
	private String costMethod;
	private String defaultStorage;
	private List<StorageDTO> storageList = new ArrayList<StorageDTO>();
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
	public List<StorageDTO> getStorageList() {
		return storageList;
	}
	public void setStorageList(List<StorageDTO> storageList) {
		this.storageList = storageList;
	}
	
	
}
