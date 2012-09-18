package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * oligo_modifications
 * @author zhang yong
 *
 */
@Entity
@Table(name = "oligo_modifications", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OligoModifications extends BaseEntity {
	@Id
	private Integer id;
	private String mType;
	private String mParentName;
	private String mName;
	private String mCode;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMType() {
		return mType;
	}
	public void setMType(String type) {
		mType = type;
	}
	public String getMParentName() {
		return mParentName;
	}
	public void setMParentName(String parentName) {
		mParentName = parentName;
	}
	public String getMName() {
		return mName;
	}
	public void setMName(String name) {
		mName = name;
	}
	public String getMCode() {
		return mCode;
	}
	public void setMCode(String code) {
		mCode = code;
	}
	
}
