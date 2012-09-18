package com.genscript.gsscm.shipment.dto;

import java.util.List;

public class PrintPickListDTO {
	//up
	private String billTo;
	private String shipTo;
	private String shipVia;
	private Double weight;
	private String pickerName;
	private String warehouse;
	private String fulfillmentInformation;
	//un
	private Integer packageId;
	private String storageLocation;
	private String name;
	private String catNo;
	private Integer qtyToPick;
	private String qtypicked;
	private String qtyUom;
	private String comment;
	private String sizeToPick;
	private String sizePick;
	private String sizeUom;
	private String lotNo;
	private String locationCode;
	private String lineId;
	private String status;
	private String type;
	private Integer orderNo;
	private List<Integer> qty;
	public PrintPickListDTO() {
		super();
	}
	
	
	public Integer getPackageId() {
		return packageId;
	}


	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}


	public List<Integer> getQty() {
		return qty;
	}


	public void setQty(List<Integer> qty) {
		this.qty = qty;
	}


	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQtypicked() {
		return qtypicked;
	}
	public void setQtypicked(String qtypicked) {
		this.qtypicked = qtypicked;
	}
	public Integer getQtyToPick() {
		return qtyToPick;
	}
	public void setQtyToPick(Integer qtyToPick) {
		this.qtyToPick = qtyToPick;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getFulfillmentInformation() {
		return fulfillmentInformation;
	}

	public void setFulfillmentInformation(String fulfillmentInformation) {
		this.fulfillmentInformation = fulfillmentInformation;
	}

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getShipVia() {
		return shipVia;
	}

	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}


	public String getSizeToPick() {
		return sizeToPick;
	}


	public void setSizeToPick(String sizeToPick) {
		this.sizeToPick = sizeToPick;
	}


	public String getSizePick() {
		return sizePick;
	}


	public void setSizePick(String sizePick) {
		this.sizePick = sizePick;
	}


	public String getLotNo() {
		return lotNo;
	}


	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	public String getLineId() {
		return lineId;
	}


	public void setLineId(String lineId) {
		this.lineId = lineId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getQtyUom() {
		return qtyUom;
	}


	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}


	public String getSizeUom() {
		return sizeUom;
	}


	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	
}
