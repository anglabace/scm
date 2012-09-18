package com.genscript.gsscm.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrderReturn.
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "order_return_items", catalog="order")
public class OrderReturnItem extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4797965204043212350L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private String status;
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
