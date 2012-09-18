package com.genscript.gsscm.biolib.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "peptide_purity", catalog="biolib")
public class PeptidePurity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4804162282741321879L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String purity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
