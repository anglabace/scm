package com.genscript.gsscm.shipment.dto;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageErrors;
import com.genscript.gsscm.shipment.entity.ShipmentLine;

@Entity
@Table(name = "ship_package_lines", catalog = "shipping")
public class ShipPackageLineDTO implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer pkgLineId;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id")
	private ShipPackage shipPackages;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_line_id")
	private ShipmentLine shipmentLines;
	private Integer orderNo;
	private Integer itemNo;
	private Integer quantity;
	private Double size;
	
	private String qtyUom;
	private String sizeUom;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private String status;
	private Integer modifiedBy;
	private String trackingNo;
	private String trackingNo_;
	private String name;
	private Integer missingQty;
	private Double missingSize;
	private String vector;
	private String len;
	
	private Integer packageId;
	private Integer orderItemQty;
	private String itemName;
	private String lineStr;
	private Integer reservationId;

	private String temperature; 

	private Double extendedPrice;
	private Double unitPrice;
	private Integer qtyShipped;	
	private Integer qtyOrdered;
	private Double disc;
	private String description;
	
	private String lotNo;
	private String catalogNO;
	
	private String giftFlag;
	private String orderNoFrm;
	private String itemNoFrm;
	
	
	
	private OrderItem orderItem;
	private Reservation reservation;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="pkgLineId")
	private List<ShipPackageErrors> shipPackageErrorList;

	public List<ShipPackageErrors> getShipPackageErrorList() {
		return shipPackageErrorList;
	}

	public void setShipPackageErrorList(List<ShipPackageErrors> shipPackageErrorList) {
			this.shipPackageErrorList = shipPackageErrorList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMissingQty() {
		return missingQty;
	}

	public void setMissingQty(Integer missingQty) {
		this.missingQty = missingQty;
	}

	public Double getMissingSize() {
		return missingSize;
	}

	public void setMissingSize(Double missingSize) {
		this.missingSize = missingSize;
	}

	public ShipPackageLineDTO() {
	}

	public ShipPackageLineDTO(Integer pkgLineId, Date creationDate,
			Integer createdBy, Date modifyDate, Integer modifiedBy) {
		this.pkgLineId = pkgLineId;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
	}

	public ShipPackageLineDTO(Integer pkgLineId, ShipPackage shipPackages,
			ShipmentLine shipmentLines, Integer orderNo, Integer itemNo,
			Integer quantity, Double size, String sizeUom, Date creationDate,
			Integer createdBy, Date modifyDate, Integer modifiedBy,
			Set<ShipPackageErrors> shipPackageErrorses) {
		this.pkgLineId = pkgLineId;
		this.shipPackages = shipPackages;
		this.shipmentLines = shipmentLines;
		this.orderNo = orderNo;
		this.itemNo = itemNo;
		this.quantity = quantity;
		this.size = size;
		this.sizeUom = sizeUom;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
//		this.shipPackageErrorses = shipPackageErrorses;
	}

	@Id
	@Column(name = "pkg_line_id", unique = true, nullable = false)
	public Integer getPkgLineId() {
		return this.pkgLineId;
	}

	public void setPkgLineId(Integer pkgLineId) {
		this.pkgLineId = pkgLineId;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "package_id")
	public ShipPackage getShipPackages() {
		return this.shipPackages;
	}

	public void setShipPackages(ShipPackage shipPackages) {
		this.shipPackages = shipPackages;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_line_id")
	public ShipmentLine getShipmentLines() {
		return this.shipmentLines;
	}

	public void setShipmentLines(ShipmentLine shipmentLines) {
		this.shipmentLines = shipmentLines;
	}


	@Column(name = "item_no")
	public Integer getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "size", precision = 11, scale = 3)
	public Double getSize() {
		return this.size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	@Column(name = "created_by", nullable = false)
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	@Column(name = "modified_by", nullable = false)
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shipPackageLines")
//	public Set<ShipPackageErrors> getShipPackageErrorses() {
//		return this.shipPackageErrorses;
//	}
//
//	public void setShipPackageErrorses(
//			Set<ShipPackageErrors> shipPackageErrorses) {
//		this.shipPackageErrorses = shipPackageErrorses;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getOrderItemQty() {
		return orderItemQty;
	}

	public void setOrderItemQty(Integer orderItemQty) {
		this.orderItemQty = orderItemQty;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLineStr() {
		return lineStr;
	}

	public void setLineStr(String lineStr) {
		this.lineStr = lineStr;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Double getExtendedPrice() {
		return extendedPrice;
	}

	public void setExtendedPrice(Double extendedPrice) {
		this.extendedPrice = extendedPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQtyShipped() {
		return qtyShipped;
	}

	public void setQtyShipped(Integer qtyShipped) {
		this.qtyShipped = qtyShipped;
	}

	public Integer getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(Integer qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public Double getDisc() {
		return disc;
	}

	public void setDisc(Double disc) {
		this.disc = disc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getTrackingNo_() {
		return trackingNo_;
	}

	public void setTrackingNo_(String trackingNo_) {
		this.trackingNo_ = trackingNo_;
	}

	public String getCatalogNO() {
		return catalogNO;
	}

	public void setCatalogNO(String catalogNO) {
		this.catalogNO = catalogNO;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public String getOrderNoFrm() {
		return orderNoFrm;
	}

	public void setOrderNoFrm(String orderNoFrm) {
		this.orderNoFrm = orderNoFrm;
	}

	public String getItemNoFrm() {
		return itemNoFrm;
	}

	public void setItemNoFrm(String itemNoFrm) {
		this.itemNoFrm = itemNoFrm;
	}
	
	
}