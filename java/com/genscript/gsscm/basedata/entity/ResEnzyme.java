package com.genscript.gsscm.basedata.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RES ENZYME.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "res_enzyme", catalog="biolib")
public class ResEnzyme implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8760940243283365997L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String name;
	private String protoType;
	private String sequence;
	private Integer cutSite5;
	private Integer cutSite3;
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
	public String getProtoType() {
		return protoType;
	}
	public void setProtoType(String protoType) {
		this.protoType = protoType;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Integer getCutSite5() {
		return cutSite5;
	}
	public void setCutSite5(Integer cutSite5) {
		this.cutSite5 = cutSite5;
	}
	public Integer getCutSite3() {
		return cutSite3;
	}
	public void setCutSite3(Integer cutSite3) {
		this.cutSite3 = cutSite3;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
