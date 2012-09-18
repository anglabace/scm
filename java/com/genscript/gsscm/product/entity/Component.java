package com.genscript.gsscm.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * Component.
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "product_components", catalog = "product")
public class Component extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008369632411038932L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "component_id")
	private Integer id;
	private Integer productId;
	private String cpntCatalogNo;
	private Double quantity;
	private Integer listSeq;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the cpntCatalogNo
	 */
	public String getCpntCatalogNo() {
		return cpntCatalogNo;
	}

	/**
	 * @param cpntCatalogNo the cpntCatalogNo to set
	 */
	public void setCpntCatalogNo(String cpntCatalogNo) {
		this.cpntCatalogNo = cpntCatalogNo;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
