package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * oligo_mixed_bases
 * @author zhang yong
 *
 */
@Entity
@Table(name = "oligo_mixed_bases", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OligoMixedBases extends BaseEntity {
	@Id
	private Integer id;
	private String sName;
	private String sCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSName() {
		return sName;
	}
	public void setSName(String name) {
		sName = name;
	}
	public String getSCode() {
		return sCode;
	}
	public void setSCode(String code) {
		sCode = code;
	}
	
}
