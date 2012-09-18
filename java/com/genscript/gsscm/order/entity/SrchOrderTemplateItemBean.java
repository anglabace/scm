package com.genscript.gsscm.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ORDER TEMPLATE LIST VIEW.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "v_cust_product_price_of_order_tmpl", catalog="order")
public class SrchOrderTemplateItemBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -402185142587642996L;
	@Column(name="tmpl_name")
	@Id
	private String name;
	private Integer owner;
	private Integer srcOrderNo;
	private Integer itemNo;
	private Integer custNo;
	private String catalogNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getSrcOrderNo() {
		return srcOrderNo;
	}
	public void setSrcOrderNo(Integer srcOrderNo) {
		this.srcOrderNo = srcOrderNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
}
