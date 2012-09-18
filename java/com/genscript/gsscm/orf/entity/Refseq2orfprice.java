package com.genscript.gsscm.orf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * refseq2orfprice
 * @author Zhang Yong
 * add date 2011-12-02
 */
@Entity
@Table(name = "refseq2orfprice", catalog="orf")
public class Refseq2orfprice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String 	accession;
	@Column(name = "taxonomy_id")
	private Integer taxonomyId;
	@Column(name = "start_byte")
	private Integer startByte;
	@Column(name = "filepart")
	private Integer filepart;
	@Column(name = "CDS_region")
	private String 	CDSRegion;
	@Column(name = "synthetic_level")
	private String 	syntheticLevel;
	@Column(name = "synthetic_price")
	private Float	syntheticPrice;
	@Column(name = "vector_price")
	private Float	vectorPrice;
	@Column(name = "other_vector_price")
	private Float	otherVectorPrice;
	@Column(name = "synthetic_cost")
	private Float	syntheticCost;
	@Column(name = "vector_cost")
	private Float	vectorCost;
	@Column(name = "other_vector_cost")
	private Float	otherVectorCost;
	@Column(name = "synthetic_price_cDNA")
	private Float	syntheticPriceCDNA;
	@Column(name = "vector_price_cDNA")
	private Float	vectorPriceCDNA;
	@Column(name = "other_vector_price_cDNA")
	private Float	otherVectorPriceCDNA;
	@Column(name = "synthetic_cost_cDNA")
	private Float	syntheticCostCDNA;
	@Column(name = "vector_cost_cDNA")
	private Float	vectorCostCDNA;
	@Column(name = "other_vector_cost_cDNA")
	private Float	otherVectorCostCDNA;
	
	public String getAccession() {
		return accession;
	}
	public void setAccession(String accession) {
		this.accession = accession;
	}
	public Integer getTaxonomyId() {
		return taxonomyId;
	}
	public void setTaxonomyId(Integer taxonomyId) {
		this.taxonomyId = taxonomyId;
	}
	public Integer getStartByte() {
		return startByte;
	}
	public void setStartByte(Integer startByte) {
		this.startByte = startByte;
	}
	public Integer getFilepart() {
		return filepart;
	}
	public void setFilepart(Integer filepart) {
		this.filepart = filepart;
	}
	public String getCDSRegion() {
		return CDSRegion;
	}
	public void setCDSRegion(String cDSRegion) {
		CDSRegion = cDSRegion;
	}
	public String getSyntheticLevel() {
		return syntheticLevel;
	}
	public void setSyntheticLevel(String syntheticLevel) {
		this.syntheticLevel = syntheticLevel;
	}
	public Float getSyntheticPrice() {
		return syntheticPrice;
	}
	public void setSyntheticPrice(Float syntheticPrice) {
		this.syntheticPrice = syntheticPrice;
	}
	public Float getVectorPrice() {
		return vectorPrice;
	}
	public void setVectorPrice(Float vectorPrice) {
		this.vectorPrice = vectorPrice;
	}
	public Float getOtherVectorPrice() {
		return otherVectorPrice;
	}
	public void setOtherVectorPrice(Float otherVectorPrice) {
		this.otherVectorPrice = otherVectorPrice;
	}
	public Float getSyntheticCost() {
		return syntheticCost;
	}
	public void setSyntheticCost(Float syntheticCost) {
		this.syntheticCost = syntheticCost;
	}
	public Float getVectorCost() {
		return vectorCost;
	}
	public void setVectorCost(Float vectorCost) {
		this.vectorCost = vectorCost;
	}
	public Float getOtherVectorCost() {
		return otherVectorCost;
	}
	public void setOtherVectorCost(Float otherVectorCost) {
		this.otherVectorCost = otherVectorCost;
	}
	public Float getSyntheticPriceCDNA() {
		return syntheticPriceCDNA;
	}
	public void setSyntheticPriceCDNA(Float syntheticPriceCDNA) {
		this.syntheticPriceCDNA = syntheticPriceCDNA;
	}
	public Float getVectorPriceCDNA() {
		return vectorPriceCDNA;
	}
	public void setVectorPriceCDNA(Float vectorPriceCDNA) {
		this.vectorPriceCDNA = vectorPriceCDNA;
	}
	public Float getOtherVectorPriceCDNA() {
		return otherVectorPriceCDNA;
	}
	public void setOtherVectorPriceCDNA(Float otherVectorPriceCDNA) {
		this.otherVectorPriceCDNA = otherVectorPriceCDNA;
	}
	public Float getSyntheticCostCDNA() {
		return syntheticCostCDNA;
	}
	public void setSyntheticCostCDNA(Float syntheticCostCDNA) {
		this.syntheticCostCDNA = syntheticCostCDNA;
	}
	public Float getVectorCostCDNA() {
		return vectorCostCDNA;
	}
	public void setVectorCostCDNA(Float vectorCostCDNA) {
		this.vectorCostCDNA = vectorCostCDNA;
	}
	public Float getOtherVectorCostCDNA() {
		return otherVectorCostCDNA;
	}
	public void setOtherVectorCostCDNA(Float otherVectorCostCDNA) {
		this.otherVectorCostCDNA = otherVectorCostCDNA;
	}
}
