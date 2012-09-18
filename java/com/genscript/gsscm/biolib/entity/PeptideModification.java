package com.genscript.gsscm.biolib.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "peptide_modification", catalog="biolib")
public class PeptideModification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1966690057455347206L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String type;
	private Integer adtlSeqLength;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAdtlSeqLength() {
		return adtlSeqLength;
	}
	public void setAdtlSeqLength(Integer adtlSeqLength) {
		this.adtlSeqLength = adtlSeqLength;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
