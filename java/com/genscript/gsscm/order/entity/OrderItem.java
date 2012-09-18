package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.transaction.annotation.Transactional;

/**
 * ORDER ITEM.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_items", catalog = "order")
public class OrderItem implements Cloneable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2874760001437212284L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Integer orderItemId;
	@Column(name = "order_no")
	private Integer orderNo;
	private Integer itemNo;
	private String type;
	private Integer clsId;
	private String catalogNo;
	private String catalogId;
	private String name;
	private String nameShow;
	private String shortDesc;
	private String longDesc;
	private String taxStatus;
	@Column(name = "lead_time")
	private Integer shipSchedule;
	private Integer quantity;
	private String qtyUom;
	private Double size;
	private String sizeUom;
	private Double unitPrice;
	private String priceChanged;
	private String tbdFlag;
	private Double amount;
	private Double tax = 0.0;
	private Double discount = 0.0;
	private Integer billtoAddrId;
	private Integer shiptoAddrId;
	private Integer soldtoAddrId;
	private Integer shipMethod;
	private String trackingNo;
	@Column(name = "parent_item_id")
	private Integer parentId;
	private Integer storageId;
	private String comment;
	private String sellingNote;
	private String status;
	@Column(name = "status_upd_reason")
	private String statusReason;
	private BigDecimal basePrice;
    private Double cost;
    private Date targetDate;
	@Column(insertable = true, updatable = false)
	private Date creationDate;
	@Column(insertable = true, updatable = false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	// add by jarvinia
	private String relationType;
	private String shippingRule;
	private String shippingRoute;
    //add by zhanghuibin
    @Transient
    private String typeText;
    @Transient
    private String catalogText;
    @Transient
    private String shipDate;
    @Transient
    private String statusText;
    @Transient
    private Integer mfgOrderNo;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getCatalogText() {
        return catalogText;
    }

    public void setCatalogText(String catalogText) {
        this.catalogText = catalogText;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public Integer getShipSchedule() {
		return shipSchedule;
	}

	public void setShipSchedule(Integer shipSchedule) {
		this.shipSchedule = shipSchedule;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getBilltoAddrId() {
		return billtoAddrId;
	}

	public void setBilltoAddrId(Integer billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}

	public Integer getShiptoAddrId() {
		return shiptoAddrId;
	}

	public void setShiptoAddrId(Integer shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}

	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}

	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}

	public Integer getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSellingNote() {
		return sellingNote;
	}

	public void setSellingNote(String sellingNote) {
		this.sellingNote = sellingNote;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	/**
	 * @return the storageId
	 */
	public Integer getStorageId() {
		return storageId;
	}

	/**
	 * @param storageId the storageId to set
	 */
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	/**
	 * @return the catalogId
	 */
	public String getCatalogId() {
		return catalogId;
	}

	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	/**
	 * @return the basePrice
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Object clone()throws CloneNotSupportedException {
		OrderItem cloned = (OrderItem)super.clone();
		return cloned;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getPriceChanged() {
		return priceChanged;
	}

	public void setPriceChanged(String priceChanged) {
		this.priceChanged = priceChanged;
	}

	public String getTbdFlag() {
		return tbdFlag;
	}

	public void setTbdFlag(String tbdFlag) {
		this.tbdFlag = tbdFlag;
	}

	public String getShippingRule() {
		return shippingRule;
	}

	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}

	public String getShippingRoute() {
		return shippingRoute;
	}

	public void setShippingRoute(String shippingRoute) {
		this.shippingRoute = shippingRoute;
	}

	public String getNameShow() {
		return nameShow;
	}

	public void setNameShow(String nameShow) {
		this.nameShow = nameShow;
	}

	public Integer getMfgOrderNo() {
		return mfgOrderNo;
	}

	public void setMfgOrderNo(Integer mfgOrderNo) {
		this.mfgOrderNo = mfgOrderNo;
	}
}
