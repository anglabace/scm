package com.genscript.gsscm.accounting.entity;

/* * Created on 2010-11-11 11:06:33
 * by cwq
 * for what
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "ar_invoice_lines", catalog="accounting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArInvoiceLine extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2148457189718392147L;
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) //生成主键策略
	@Column(name="invoice_line_id")
	int invoiceLineId;
	@Column(name="invoice_id")
	int invoiceId;
	@Column(name="line_no")
	int lineNo;
	@Column(name="order_no")
	int orderNo;
	@Column(name="item_no")
	int itemNo;
	@Column(name="catalog_no")
	String catalogNo;
	int qty;
	@Column(name="qty_uom")
	String qtyUom;
	String name;
	float size;
	@Column(name="size_uom")
	String sizeUom;
	@Column(name="unit_price")
	float unitPrice;
	float amount;
	float discount;
	float tax;
	String status;
	public int getInvoiceLineId() {
		return invoiceLineId;
	}
	public void setInvoiceLineId(int invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getLineNo() {
		return lineNo;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public String getSizeUom() {
		return sizeUom;
	}
	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
