package com.genscript.gsscm.olddb.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "peptide_base_price", catalog = "olddb")
public class PeptideBasePrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9211485644020881803L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer length;
	private String purity;
	private String quantity;
	private Float unitPrice;
	private Float unitCost;
	private Float expPrice;
	private Float expCost;
	private Integer express;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Float getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}
	public Float getExpPrice() {
		return expPrice;
	}
	public void setExpPrice(Float expPrice) {
		this.expPrice = expPrice;
	}
	public Float getExpCost() {
		return expCost;
	}
	public void setExpCost(Float expCost) {
		this.expCost = expCost;
	}
	public Integer getExpress() {
		return express;
	}
	public void setExpress(Integer express) {
		this.express = express;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
