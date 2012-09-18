package com.genscript.gsscm.orf.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

/**
 * Refseq2orfpriceDTO
 * @author Zhang Yong
 * add date 2011-12-02
 */
@XmlType(name = "Refseq2orfpriceDTO", namespace = WsConstants.NS)
public class Refseq2orfpriceDTO implements Serializable {
	private static final long serialVersionUID = -148753079413239177L;
	private String 	accession;
	private Integer taxonomyId;
	private Integer startByte;
	private Integer filepart;
	private String 	CDSRegion;
	private String 	syntheticLevel;
	private Float	syntheticPrice;
	private Float	vectorPrice;
	private Float	otherVectorPrice;
	private Float	syntheticCost;
	private Float	vectorCost;
	private Float	otherVectorCost;
	private Float	syntheticPriceCDNA;
	private Float	vectorPriceCDNA;
	private Float	otherVectorPriceCDNA;
	private Float	syntheticCostCDNA;
	private Float	vectorCostCDNA;
	private Float	otherVectorCostCDNA;
	//非数据库字段
	private String	sequenceType;
	
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
	public String getSequenceType() {
		return sequenceType;
	}
	public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}
}
