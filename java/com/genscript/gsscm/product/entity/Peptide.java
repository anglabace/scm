package com.genscript.gsscm.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * product Detail.
 * 
 * 
 * @author Mingrs
 */
@Entity
@Table(name = "peptide", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Peptide extends BaseEntity {
	@Id
	private Integer productId;
	private String casNo;
	private String sequence;
	private String sequenceShortening;
	private String molecularFormula;
	private String species;
	private String specificActivity;
	private String specificity;
	private String purity;
	@Column(name = "c_terminal")
	private String cterminal;
	@Column(name = "n_terminal")
	private String nterminal;
	private String disulfideBridge;
	private String formatBridge;
	private String residueNo;
	private Double measuredMolecularWeight;
	private Double molecularWeight;
	private String endotoxinLevel;
	private String appearance; 
	private String color;
	private String concentration;
	private String solubility;
	private String stability;
	private String qualityControl;
	private Integer gmpFlag;
	private String americanpeptideCatNo;
	private String bachemCatNo;
	private String anaspecCatNo;
	private String phoenixpeptideCatNo;
	private String dimers;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getCasNo() {
		return casNo;
	}
	public void setCasNo(String casNo) {
		this.casNo = casNo;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getSequenceShortening() {
		return sequenceShortening;
	}
	public void setSequenceShortening(String sequenceShortening) {
		this.sequenceShortening = sequenceShortening;
	}
	public String getMolecularFormula() {
		return molecularFormula;
	}
	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getSpecificActivity() {
		return specificActivity;
	}
	public void setSpecificActivity(String specificActivity) {
		this.specificActivity = specificActivity;
	}
	public String getSpecificity() {
		return specificity;
	}
	public void setSpecificity(String specificity) {
		this.specificity = specificity;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getCterminal() {
		return cterminal;
	}
	public void setCterminal(String cterminal) {
		this.cterminal = cterminal;
	}
	public String getNterminal() {
		return nterminal;
	}
	public void setNterminal(String nterminal) {
		this.nterminal = nterminal;
	}
	public String getDisulfideBridge() {
		return disulfideBridge;
	}
	public void setDisulfideBridge(String disulfideBridge) {
		this.disulfideBridge = disulfideBridge;
	}
	public String getFormatBridge() {
		return formatBridge;
	}
	public void setFormatBridge(String formatBridge) {
		this.formatBridge = formatBridge;
	}
	public String getResidueNo() {
		return residueNo;
	}
	public void setResidueNo(String residueNo) {
		this.residueNo = residueNo;
	}
	public Double getMeasuredMolecularWeight() {
		return measuredMolecularWeight;
	}
	public void setMeasuredMolecularWeight(Double measuredMolecularWeight) {
		this.measuredMolecularWeight = measuredMolecularWeight;
	}
	public Double getMolecularWeight() {
		return molecularWeight;
	}
	public void setMolecularWeight(Double molecularWeight) {
		this.molecularWeight = molecularWeight;
	}
	public String getEndotoxinLevel() {
		return endotoxinLevel;
	}
	public void setEndotoxinLevel(String endotoxinLevel) {
		this.endotoxinLevel = endotoxinLevel;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	public String getSolubility() {
		return solubility;
	}
	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	public String getQualityControl() {
		return qualityControl;
	}
	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}
	public Integer getGmpFlag() {
		return gmpFlag;
	}
	public void setGmpFlag(Integer gmpFlag) {
		this.gmpFlag = gmpFlag;
	}
	public String getAmericanpeptideCatNo() {
		return americanpeptideCatNo;
	}
	public void setAmericanpeptideCatNo(String americanpeptideCatNo) {
		this.americanpeptideCatNo = americanpeptideCatNo;
	}
	public String getBachemCatNo() {
		return bachemCatNo;
	}
	public void setBachemCatNo(String bachemCatNo) {
		this.bachemCatNo = bachemCatNo;
	}
	public String getAnaspecCatNo() {
		return anaspecCatNo;
	}
	public void setAnaspecCatNo(String anaspecCatNo) {
		this.anaspecCatNo = anaspecCatNo;
	}
	public String getPhoenixpeptideCatNo() {
		return phoenixpeptideCatNo;
	}
	public void setPhoenixpeptideCatNo(String phoenixpeptideCatNo) {
		this.phoenixpeptideCatNo = phoenixpeptideCatNo;
	}
	public String getDimers() {
		return dimers;
	}
	public void setDimers(String dimers) {
		this.dimers = dimers;
	}
	
}
