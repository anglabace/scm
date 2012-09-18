package com.genscript.gsscm.biolib.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "peptide_code", catalog="biolib")
public class PeptideCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8139410922693948279L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code1;
	private String code3;
	private String dcode;
	private String name;
	private Float mw;
	private String image;
	@Column(name="IE")
	private Float ie;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode1() {
		return code1;
	}
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	public String getCode3() {
		return code3;
	}
	public void setCode3(String code3) {
		this.code3 = code3;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getMw() {
		return mw;
	}
	public void setMw(Float mw) {
		this.mw = mw;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Float getIe() {
		return ie;
	}
	public void setIe(Float ie) {
		this.ie = ie;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
