package com.genscript.gsscm.product.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PRODUCT RELATION VIEW.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "v_related_products", catalog="product")
public class ProductRelationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5878975344471602155L;
	@Id
	private Integer relationId;
	private Integer productId;
	private String name;
	private String relationType;
	private String catalogNo;
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
