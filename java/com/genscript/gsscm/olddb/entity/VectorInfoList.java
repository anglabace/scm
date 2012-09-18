package com.genscript.gsscm.olddb.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * for ORF Clone
 * @author Zhang Yong
 * add date 2011-12-01
 *
 */
@Entity
@Table(name = "vector_info_list", catalog = "olddb")
public class VectorInfoList implements Serializable {
	private static final long serialVersionUID = -3475071853827742029L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String	vectorName;
	private String	resistance;
	private String	vectorLen;
	@Lob
	private String	cloningSites;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVectorName() {
		return vectorName;
	}
	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}
	public String getResistance() {
		return resistance;
	}
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	public String getVectorLen() {
		return vectorLen;
	}
	public void setVectorLen(String vectorLen) {
		this.vectorLen = vectorLen;
	}
	public String getCloningSites() {
		return cloningSites;
	}
	public void setCloningSites(String cloningSites) {
		this.cloningSites = cloningSites;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
