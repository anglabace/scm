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
@Table(name = "peptide_amino_acid", catalog="biolib")
public class PeptideAminoAcid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4740820084042502118L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name;
	private String fullName;
	private Float molecularWeight;
	@Column(name="price_49")
	private Float price49;
	@Column(name="price_500")
	private Float price500;
	@Column(name="price_1000")
	private Float price1000;
	@Column(name="cost_49")
	private Float cost49;
	@Column(name="cost_500")
	private Float cost500;
	@Column(name="cost_1000")
	private Float cost1000;
	private Integer express;
	@Column(name="exp_price_49")
	private Float expPrice49;
	@Column(name="exp_price_500")
	private Float expPrice500;
	@Column(name="exp_price_1000")
	private Float expPrice1000;
	@Column(name="exp_cost_49")
	private Float expCost49;
	@Column(name="exp_cost_500")
	private Float expCost500;
	@Column(name="exp_cost_1000")
	private Float expCost1000;
	@Column(name="cost_9")
	private Float cost9;
	@Column(name="cost_19")
	private Float cost19;
	@Column(name="cost_29")
	private Float cost29;
	@Column(name="cost_39")
	private Float cost39;
	@Column(name="cost_100")
	private Float cost100;
	@Column(name="cost_300")
	private Float cost300;
	@Column(name="cost_700")
	private Float cost700;
	@Column(name="price_9")
	private Float price9;
	@Column(name="price_19")
	private Float price19;
	@Column(name="price_29")
	private Float price29;
	@Column(name="price_39")
	private Float price39;
	@Column(name="price_100")
	private Float price100;
	@Column(name="price_300")
	private Float price300;
	@Column(name="price_700")
	private Float price700;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Float getMolecularWeight() {
		return molecularWeight;
	}
	public void setMolecularWeight(Float molecularWeight) {
		this.molecularWeight = molecularWeight;
	}
	public Float getPrice49() {
		return price49;
	}
	public void setPrice49(Float price49) {
		this.price49 = price49;
	}
	public Float getPrice500() {
		return price500;
	}
	public void setPrice500(Float price500) {
		this.price500 = price500;
	}
	public Float getPrice1000() {
		return price1000;
	}
	public void setPrice1000(Float price1000) {
		this.price1000 = price1000;
	}
	public Float getCost49() {
		return cost49;
	}
	public void setCost49(Float cost49) {
		this.cost49 = cost49;
	}
	public Float getCost500() {
		return cost500;
	}
	public void setCost500(Float cost500) {
		this.cost500 = cost500;
	}
	public Float getCost1000() {
		return cost1000;
	}
	public void setCost1000(Float cost1000) {
		this.cost1000 = cost1000;
	}
	public Integer getExpress() {
		return express;
	}
	public void setExpress(Integer express) {
		this.express = express;
	}
	public Float getExpPrice49() {
		return expPrice49;
	}
	public void setExpPrice49(Float expPrice49) {
		this.expPrice49 = expPrice49;
	}
	public Float getExpPrice500() {
		return expPrice500;
	}
	public void setExpPrice500(Float expPrice500) {
		this.expPrice500 = expPrice500;
	}
	public Float getExpPrice1000() {
		return expPrice1000;
	}
	public void setExpPrice1000(Float expPrice1000) {
		this.expPrice1000 = expPrice1000;
	}
	public Float getExpCost49() {
		return expCost49;
	}
	public void setExpCost49(Float expCost49) {
		this.expCost49 = expCost49;
	}
	public Float getExpCost500() {
		return expCost500;
	}
	public void setExpCost500(Float expCost500) {
		this.expCost500 = expCost500;
	}
	public Float getExpCost1000() {
		return expCost1000;
	}
	public void setExpCost1000(Float expCost1000) {
		this.expCost1000 = expCost1000;
	}
	public Float getCost9() {
		return cost9;
	}
	public void setCost9(Float cost9) {
		this.cost9 = cost9;
	}
	public Float getCost19() {
		return cost19;
	}
	public void setCost19(Float cost19) {
		this.cost19 = cost19;
	}
	public Float getCost29() {
		return cost29;
	}
	public void setCost29(Float cost29) {
		this.cost29 = cost29;
	}
	public Float getCost39() {
		return cost39;
	}
	public void setCost39(Float cost39) {
		this.cost39 = cost39;
	}
	public Float getCost100() {
		return cost100;
	}
	public void setCost100(Float cost100) {
		this.cost100 = cost100;
	}
	public Float getCost300() {
		return cost300;
	}
	public void setCost300(Float cost300) {
		this.cost300 = cost300;
	}
	public Float getCost700() {
		return cost700;
	}
	public void setCost700(Float cost700) {
		this.cost700 = cost700;
	}
	public Float getPrice9() {
		return price9;
	}
	public void setPrice9(Float price9) {
		this.price9 = price9;
	}
	public Float getPrice19() {
		return price19;
	}
	public void setPrice19(Float price19) {
		this.price19 = price19;
	}
	public Float getPrice29() {
		return price29;
	}
	public void setPrice29(Float price29) {
		this.price29 = price29;
	}
	public Float getPrice39() {
		return price39;
	}
	public void setPrice39(Float price39) {
		this.price39 = price39;
	}
	public Float getPrice100() {
		return price100;
	}
	public void setPrice100(Float price100) {
		this.price100 = price100;
	}
	public Float getPrice300() {
		return price300;
	}
	public void setPrice300(Float price300) {
		this.price300 = price300;
	}
	public Float getPrice700() {
		return price700;
	}
	public void setPrice700(Float price700) {
		this.price700 = price700;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
