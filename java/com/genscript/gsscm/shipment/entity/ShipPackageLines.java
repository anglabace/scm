package com.genscript.gsscm.shipment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "ship_package_lines", catalog = "shipping")
public class ShipPackageLines implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Integer reservationId;
    @Transient
    private Double unitPrice; //orderItem的UnitPrice
    @Transient
    private String shortDesc;  //orderItem的shortDesc
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="pkgLineId")
	private List<ShipPackageErrors> shipPackageErrorList;
	
	@Column(name = "package_id")
	public Integer getPkgLineId() {
		return pkgLineId;
	}
	public void setPkgLineId(Integer pkgLineId) {
		this.pkgLineId = pkgLineId;
	}
	public ShipPackage getShipPackages() {
		return shipPackages;
	}
	public void setShipPackages(ShipPackage shipPackages) {
		this.shipPackages = shipPackages;
	}
	public ShipmentLine getShipmentLines() {
		return shipmentLines;
	}
	public void setShipmentLines(ShipmentLine shipmentLines) {
		this.shipmentLines = shipmentLines;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
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
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public List<ShipPackageErrors> getShipPackageErrorList() {
		return shipPackageErrorList;
	}
	public void setShipPackageErrorList(List<ShipPackageErrors> shipPackageErrorList) {
		this.shipPackageErrorList = shipPackageErrorList;
	}

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
}