package com.genscript.gsscm.basedata.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VECTOR.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "vector", catalog="biolib")
public class Vector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3947917359504775747L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vector_id")
	private Integer vectorId;
	private String vectorName;
	public Integer getVectorId() {
		return vectorId;
	}
	public void setVectorId(Integer vectorId) {
		this.vectorId = vectorId;
	}
	public String getVectorName() {
		return vectorName;
	}
	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}
	
}
