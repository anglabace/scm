package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * oligo_chimeric_bases
 * @author zhang yong
 *
 */
@Entity
@Table(name = "oligo_chimeric_bases", catalog="product")
public class OligoChimericBases extends BaseEntity  {
	@Id
	private Integer id;
	private Integer bId;
	private String cBase;
	private String cCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBId() {
		return bId;
	}
	public void setBId(Integer id) {
		bId = id;
	}
	public String getCBase() {
		return cBase;
	}
	public void setCBase(String base) {
		cBase = base;
	}
	public String getCCode() {
		return cCode;
	}
	public void setCCode(String code) {
		cCode = code;
	}
}
