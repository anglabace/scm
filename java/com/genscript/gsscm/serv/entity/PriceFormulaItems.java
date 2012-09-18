package com.genscript.gsscm.serv.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * price formaula items
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "price_formula_items", catalog="product")
public class PriceFormulaItems implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6534949519442655583L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer seqNo;
	private Integer formulaId;
	private String type;
	private String value;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public Integer getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(Integer formulaId) {
		this.formulaId = formulaId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
