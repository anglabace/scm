package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * product Detail.
 * 
 * 
 * @author Mingrs
 */
@Entity
@Table(name = "oligo", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Oligo extends BaseEntity {
	@Id
	private Integer productId;
	private String sequence;
	private String modification;
	private String stability;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}

}
