package com.genscript.gsscm.quote.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SERVICE QUOTE TEMPLATE LIST VIEW.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "v_cust_service_of_quote_tmpl", catalog="order")
public class ServiceQuoteTemplateBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4624981039415066428L;
	@Column(name="tmpl_name")
	private String name;
	private Integer owner;
	private Integer custNo;
	private Integer srcQuoteNo;
	@Id
	private Integer itemNo;
	private String type;
	private String catalogNo;
	private String qtyUom;
	private Integer size;
	private String uom;
	private String catalogId;
	@Column(name="name")
	private String serviceName;
	private String description;
	@Column(name="symbol")
	private String upSymbol;
	private String currencyCode;
	private Integer parentItemId;
	
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Integer getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(Integer srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
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
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpSymbol() {
		return upSymbol;
	}
	public void setUpSymbol(String upSymbol) {
		this.upSymbol = upSymbol;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Integer getParentItemId() {
		return parentItemId;
	}
	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
	}
}
