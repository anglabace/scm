package com.genscript.gsscm.serv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * price formulas
 * 
 * 
 * @author mingrs
 */
@Entity
@Table(name = "price_formulas", catalog="product")
public class PriceFormulas extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer ruleId;
	private String formulaBrief;
	private String desccription;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public String getDesccription() {
		return desccription;
	}
	public void setDesccription(String desccription) {
		this.desccription = desccription;
	}
	public String getFormulaBrief() {
		return formulaBrief;
	}
	public void setFormulaBrief(String formulaBrief) {
		this.formulaBrief = formulaBrief;
	}
	
}
