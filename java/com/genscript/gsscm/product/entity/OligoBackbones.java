package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * oligo_backbones
 * @author zhang yong
 *
 */
@Entity
@Table(name = "oligo_backbones", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OligoBackbones {
	@Id
	private Integer id;
	private String bName;
	private String availableLetter;
	private String validationRule;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBName() {
		return bName;
	}
	public void setBName(String name) {
		bName = name;
	}
	public String getAvailableLetter() {
		return availableLetter;
	}
	public void setAvailableLetter(String availableLetter) {
		this.availableLetter = availableLetter;
	}
	public String getValidationRule() {
		return validationRule;
	}
	public void setValidationRule(String validationRule) {
		this.validationRule = validationRule;
	}
}
