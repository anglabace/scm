package com.genscript.gsscm.quote.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * QUOTATION ITEM.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_items", catalog="order")
public class QuoteItem extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quote_item_id")
	private Integer quoteItemId;
	@Column(name = "quote_no")
	private Integer quoteNo;
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
	private Double tax;
	private Double discount;
	private Integer billtoAddrId;
	private Integer shiptoAddrId;
	private Integer soldtoAddrId;
	private Integer shipMethod;
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
	private String shippingRoute;
	
	public Integer getQuoteItemId() {
		return quoteItemId;
	}
	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
	}
	
	public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	//	public QuoteItem(){}
//	
//	public QuoteItem(QuoteItemPK id) {
//		this.id = id ;
//	}
//	
//	public QuoteItem(QuoteMain quoteMain, Integer itemNo) {
//		this.id = new QuoteItemPK(quoteMain, itemNo);
//	}
//
//	public QuoteItemPK getId() {
//		return id;
//	}
//	public void setId(QuoteItemPK id) {
//		this.id = id;
//	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}

	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
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

	public Integer getClsId() {
		return clsId;
	}
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	/**
	 * @return the taxStatus
	 */
	public String getTaxStatus() {
		return taxStatus;
	}

	/**
	 * @param taxStatus the taxStatus to set
	 */
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	/**
	 * @return the soldtoAddrId
	 */
	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}

	/**
	 * @param soldtoAddrId the soldtoAddrId to set
	 */
	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}

	/**
	 * @return the shipMethod
	 */
	public Integer getShipMethod() {
		return shipMethod;
	}

	/**
	 * @param shipMethod the shipMethod to set
	 */
	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}

	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 * @param statusReason the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public void setName(String name) {
		this.name = name;
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
	public Integer getShipSchedule() {
		return shipSchedule;
	}
	public void setShipSchedule(Integer shipSchedule) {
		this.shipSchedule = shipSchedule;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
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
}
