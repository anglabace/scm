package com.genscript.gsscm.inventory.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "receiving_logs", catalog = "inventory")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReceivingLogDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String orderType;
	private String trackingNo;
	private Integer orderNo;
	private Integer itemNo;
	private String catalogNo;
	private Integer qtyReceived;
	private String qtyUom;
	private Double sizeReceived;
	private String sizeUom;
	private Integer warehouseId;
	private Integer storageId;
	private String locationCode;
	private String receivingNote;
	private Date reveivingDate;
	private Integer receivedBy;
	
	private String userName;
	private String name;

	private Integer sumQty;
	private Double sumSize;

	// Constructors

	public Integer getSumQty() {
		return sumQty;
	}

	public void setSumQty(Integer sumQty) {
		this.sumQty = sumQty;
	}

	public Double getSumSize() {
		return sumSize;
	}

	public void setSumSize(Double sumSize) {
		this.sumSize = sumSize;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** default constructor */
	public ReceivingLogDTO() {
	}

	/** minimal constructor */
	public ReceivingLogDTO(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ReceivingLogDTO(Integer id, String orderType, String trackingNo,
			Integer orderNo, Integer itemNo, String catalogNo,
			Integer qtyReceived, String qtyUom, Double sizeReceived,
			String sizeUom, Integer warehouseId, Integer storageId,
			String locationCode, String receivingNote, Date reveivingDate,
			Integer receivedBy) {
		this.id = id;
		this.orderType = orderType;
		this.trackingNo = trackingNo;
		this.orderNo = orderNo;
		this.itemNo = itemNo;
		this.catalogNo = catalogNo;
		this.qtyReceived = qtyReceived;
		this.qtyUom = qtyUom;
		this.sizeReceived = sizeReceived;
		this.sizeUom = sizeUom;
		this.warehouseId = warehouseId;
		this.storageId = storageId;
		this.locationCode = locationCode;
		this.receivingNote = receivingNote;
		this.reveivingDate = reveivingDate;
		this.receivedBy = receivedBy;
	}


	@Column(name = "order_type", length = 20)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "tracking_no", length = 30)
	public String getTrackingNo() {
		return this.trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	@Column(name = "order_no")
	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "item_no")
	public Integer getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	@Column(name = "catalog_no", length = 20)
	public String getCatalogNo() {
		return this.catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	@Column(name = "qty_received")
	public Integer getQtyReceived() {
		return this.qtyReceived;
	}

	public void setQtyReceived(Integer qtyReceived) {
		this.qtyReceived = qtyReceived;
	}

	@Column(name = "qty_uom", length = 10)
	public String getQtyUom() {
		return this.qtyUom;
	}

	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}

	@Column(name = "size_received", precision = 11, scale = 3)
	public Double getSizeReceived() {
		return this.sizeReceived;
	}

	public void setSizeReceived(Double sizeReceived) {
		this.sizeReceived = sizeReceived;
	}

	@Column(name = "size_uom", length = 10)
	public String getSizeUom() {
		return this.sizeUom;
	}

	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}

	@Column(name = "warehouse_id")
	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Column(name = "storage_id")
	public Integer getStorageId() {
		return this.storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	@Column(name = "location_code", length = 20)
	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	@Column(name = "receiving_note")
	public String getReceivingNote() {
		return this.receivingNote;
	}

	public void setReceivingNote(String receivingNote) {
		this.receivingNote = receivingNote;
	}


	@Column(name = "received_by")
	public Integer getReceivedBy() {
		return this.receivedBy;
	}

	public void setReceivedBy(Integer receivedBy) {
		this.receivedBy = receivedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getReveivingDate() {
		return reveivingDate;
	}

	public void setReveivingDate(Date reveivingDate) {
		this.reveivingDate = reveivingDate;
	}

}