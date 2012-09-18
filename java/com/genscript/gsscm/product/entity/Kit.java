package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * product Detail..
 * 
 * 
 * @author Mingrs
 */
@Entity
@Table(name = "kit", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Kit extends BaseEntity {
	@Id
	private Integer productId;
	private String sensitivity;
	private String stability;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}

}
