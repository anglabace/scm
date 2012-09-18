package com.genscript.gsscm.purchase.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
import com.genscript.gsscm.shipment.entity.Shipment;

@Entity
@Table(name = "purchase_order_items", catalog = "purchase")
public class PurchaseOrderItemDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer orderItemId;
	private PurchaseOrder purchaseOrder;
	private Integer itemNo;
	private String catalogNo;
	private Integer quantity;
	private String qtyUom;
	private Integer receQty;
	private Double size;
	private Double receSize;
	private String sizeUom;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private String trackingNo;
	private String packageId;
	private String pkgLineId;
	private String locationCode;
	private String name;
	private Integer quantityTemp;
	private Integer receId;
	private PoReceivingTmp tmp;
	private PurchaseOrderItem porderItem;
	private OrderMain order;
	private OrderItem orderItem;
	private Shipment shipment;

	
	public PoReceivingTmp getTmp() {
		return tmp;
	}


	public void setTmp(PoReceivingTmp tmp) {
		this.tmp = tmp;
	}


	public Integer getReceId() {
		return receId;
	}


	public void setReceId(Integer receId) {
		this.receId = receId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	public String getTrackingNo() {
		return trackingNo;
	}


	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}


	public PurchaseOrderItemDTO() {
	}


	@Id
	@Column(name = "order_item_id", unique = true, nullable = false)
	public Integer getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	@Column(name = "item_no", nullable = false)
	public Integer getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	@Column(name = "catalog_no", nullable = false, length = 20)
	public String getCatalogNo() {
		return this.catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "status", nullable = false, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "creation_date", nullable = false, length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "created_by", nullable = false)
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modify_date", nullable = false, length = 19)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "modified_by", nullable = false)
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_no")
	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}


	public String getQtyUom() {
		return qtyUom;
	}


	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}


	public Double getSize() {
		return size;
	}


	public void setSize(Double size) {
		this.size = size;
	}


	public String getSizeUom() {
		return sizeUom;
	}


	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}


	public Integer getQuantityTemp() {
		return quantityTemp;
	}


	public void setQuantityTemp(Integer quantityTemp) {
		this.quantityTemp = quantityTemp;
	}


	public String getPackageId() {
		return packageId;
	}


	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}


	public String getPkgLineId() {
		return pkgLineId;
	}


	public void setPkgLineId(String pkgLineId) {
		this.pkgLineId = pkgLineId;
	}


	public Integer getReceQty() {
		return receQty;
	}


	public void setReceQty(Integer receQty) {
		this.receQty = receQty;
	}


	public Double getReceSize() {
		return receSize;
	}


	public void setReceSize(Double receSize) {
		this.receSize = receSize;
	}


	public PurchaseOrderItem getPorderItem() {
		return porderItem;
	}


	public void setPorderItem(PurchaseOrderItem porderItem) {
		this.porderItem = porderItem;
	}


	public OrderMain getOrder() {
		return order;
	}


	public void setOrder(OrderMain order) {
		this.order = order;
	}


	public OrderItem getOrderItem() {
		return orderItem;
	}


	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}


	public Shipment getShipment() {
		return shipment;
	}


	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	
}