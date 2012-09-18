package com.genscript.gsscm.customer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SIMPLE CUSTOMER PRODUCT PRICE VIEW.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "v_simp_cust_product_price")
public class SimpCustProductPriceBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3367612758323499689L;
	private Integer custNo;
	@Id
	private Integer productId;
	private String upCatalogId;
	private Integer categoryId;
	private String categoryNo;
	private String categoryName;
	private String status;
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getUpCatalogId() {
		return upCatalogId;
	}
	public void setUpCatalogId(String upCatalogId) {
		this.upCatalogId = upCatalogId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
