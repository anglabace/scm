package com.genscript.gsscm.shipment.dto;

import java.util.List;

import com.genscript.gsscm.shipment.entity.ShipPackage;

public class PrintPickListsDTO {
	private ShipPackage shipPackage;
	private List<PrintPickListDTO> printPickListDTOList;
	private String warehouseName;
	private String billToAddress;
	private String shipToAddress;
	private String pickerName;
	private String shipVia;
	
	
	public String getShipVia() {
		return shipVia;
	}
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public ShipPackage getShipPackage() {
		return shipPackage;
	}
	public void setShipPackage(ShipPackage shipPackage) {
		this.shipPackage = shipPackage;
	}

	public List<PrintPickListDTO> getPrintPickListDTOList() {
		return printPickListDTOList;
	}
	public void setPrintPickListDTOList(List<PrintPickListDTO> printPickListDTOList) {
		this.printPickListDTOList = printPickListDTOList;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(String billToAddress) {
		this.billToAddress = billToAddress;
	}
	public String getPickerName() {
		return pickerName;
	}
	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}
	
}
