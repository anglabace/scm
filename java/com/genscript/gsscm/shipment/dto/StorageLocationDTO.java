package com.genscript.gsscm.shipment.dto;

import java.util.List;

public class StorageLocationDTO {
	private List<LotNoDTO> lotNoList;
	private String storageLocation;
	public List<LotNoDTO> getLotNoList() {
		return lotNoList;
	}
	public void setLotNoList(List<LotNoDTO> lotNoList) {
		this.lotNoList = lotNoList;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	
}
