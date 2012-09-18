package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OrderReturnItemDTO", namespace = WsConstants.NS)
public class OrderReturnItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679752891076150202L;
	private Integer itemId;
	private Integer rmaNo;
	private Integer itemNo;
	private String catalogNo;
	private BigDecimal unitPrice;
	private BigDecimal discount;
	private BigDecimal tax;
	private Integer orderQty;
	private Integer shippedQty;
	private BigDecimal shipSize;
	private String shipUom;
	private Integer returnQty;
	private BigDecimal returnSize;
	private String returnUom;
	private String returnReason;
	private String exchangeFlag;
	private BigDecimal refund;
	private String name;
	private String description;
	private String status;
	protected Date creationDate = new Date();

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the rmaNo
	 */
	public Integer getRmaNo() {
		return rmaNo;
	}
	/**
	 * @param rmaNo the rmaNo to set
	 */
	public void setRmaNo(Integer rmaNo) {
		this.rmaNo = rmaNo;
	}
	/**
	 * @return the itemNo
	 */
	public Integer getItemNo() {
		return itemNo;
	}
	/**
	 * @param itemNo the itemNo to set
	 */
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
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
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	/**
	 * @return the orderQty
	 */
	public Integer getOrderQty() {
		return orderQty;
	}
	/**
	 * @param orderQty the orderQty to set
	 */
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	/**
	 * @return the shippedQty
	 */
	public Integer getShippedQty() {
		return shippedQty;
	}
	/**
	 * @param shippedQty the shippedQty to set
	 */
	public void setShippedQty(Integer shippedQty) {
		this.shippedQty = shippedQty;
	}
	/**
	 * @return the shipSize
	 */
	public BigDecimal getShipSize() {
		return shipSize;
	}
	/**
	 * @param shipSize the shipSize to set
	 */
	public void setShipSize(BigDecimal shipSize) {
		this.shipSize = shipSize;
	}
	/**
	 * @return the shipUom
	 */
	public String getShipUom() {
		return shipUom;
	}
	/**
	 * @param shipUom the shipUom to set
	 */
	public void setShipUom(String shipUom) {
		this.shipUom = shipUom;
	}
	/**
	 * @return the returnQty
	 */
	public Integer getReturnQty() {
		return returnQty;
	}
	/**
	 * @param returnQty the returnQty to set
	 */
	public void setReturnQty(Integer returnQty) {
		this.returnQty = returnQty;
	}
	/**
	 * @return the returnSize
	 */
	public BigDecimal getReturnSize() {
		return returnSize;
	}
	/**
	 * @param returnSize the returnSize to set
	 */
	public void setReturnSize(BigDecimal returnSize) {
		this.returnSize = returnSize;
	}
	/**
	 * @return the returnUom
	 */
	public String getReturnUom() {
		return returnUom;
	}
	/**
	 * @param returnUom the returnUom to set
	 */
	public void setReturnUom(String returnUom) {
		this.returnUom = returnUom;
	}
	/**
	 * @return the returnReason
	 */
	public String getReturnReason() {
		return returnReason;
	}
	/**
	 * @param returnReason the returnReason to set
	 */
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	/**
	 * @return the exchangeFlag
	 */
	public String getExchangeFlag() {
		return exchangeFlag;
	}
	/**
	 * @param exchangeFlag the exchangeFlag to set
	 */
	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}
	/**
	 * @return the refund
	 */
	public BigDecimal getRefund() {
		return refund;
	}
	/**
	 * @param refund the refund to set
	 */
	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
