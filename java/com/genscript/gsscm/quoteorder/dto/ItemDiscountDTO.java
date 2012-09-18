package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ItemDiscountDTO", namespace = WsConstants.NS)
public class ItemDiscountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String catalogNo;
	private BigDecimal amount;
	private BigDecimal discount;
	private String status;
	private String itemId;
	public BigDecimal getAmount() {
		if(amount == null)
			return null;
		return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setAmount(BigDecimal amount) {
		if(amount == null){
			this.amount = null;
		}else{
			this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public BigDecimal getDiscount() {
		if(discount == null)
			return null;
		return discount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setDiscount(BigDecimal discount) {
		if(discount == null){
			this.discount = null;
		}else{
			this.discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	
}
