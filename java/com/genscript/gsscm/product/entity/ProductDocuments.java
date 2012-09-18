package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * DOCUMENTS.
 * 
 * 
 * @author mingrs
 */

@Entity
@Table(name = "product_documents", catalog="product")
public class ProductDocuments extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productDocId;
	private Integer docId;
	private Integer productId;
	public Integer getProductDocId() {
		return productDocId;
	}
	public void setProductDocId(Integer productDocId) {
		this.productDocId = productDocId;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

 
}
