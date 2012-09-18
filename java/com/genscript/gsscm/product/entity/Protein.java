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
@Table(name = "protein", catalog = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Protein extends BaseEntity {
	@Id
	private Integer productId;
	private String accessionNo;
	private String casNo;
	private String species;
	private String source;
	private String specificActivity;
	private String reactionBuffer;
	private String specificity;
	private Double molecularWeight;
	private String measuredMolecularWeight;
	private String appearance;
	private String purity;
	private String dimers;
	private String purification;
	private String formulation;
	private String concentration;
	private String reconstitution;
	private String quantitation;
	private String solubility;
	private String component;
	private String endotoxinLevel;
	private String isoelectricPoint;
	private String sequence;
	private String sequenceAnalysis;
	private String uv;
	private String stability;
	private String qualityControl;
	private String substrate; 
	private String physicalAppearance;
	@Column(name="`usage`")
	private String usage;

	public String getPhysicalAppearance() {
		return physicalAppearance;
	}

	public void setPhysicalAppearance(String physicalAppearance) {
		this.physicalAppearance = physicalAppearance;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getAccessionNo() {
		return accessionNo;
	}

	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}

	public String getCasNo() {
		return casNo;
	}

	public void setCasNo(String casNo) {
		this.casNo = casNo;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSpecificActivity() {
		return specificActivity;
	}

	public void setSpecificActivity(String specificActivity) {
		this.specificActivity = specificActivity;
	}

	public String getReactionBuffer() {
		return reactionBuffer;
	}

	public void setReactionBuffer(String reactionBuffer) {
		this.reactionBuffer = reactionBuffer;
	}

	public String getSpecificity() {
		return specificity;
	}

	public void setSpecificity(String specificity) {
		this.specificity = specificity;
	}

	public Double getMolecularWeight() {
		return molecularWeight;
	}

	public void setMolecularWeight(Double molecularWeight) {
		this.molecularWeight = molecularWeight;
	}

	public String getMeasuredMolecularWeight() {
		return measuredMolecularWeight;
	}

	public void setMeasuredMolecularWeight(String measuredMolecularWeight) {
		this.measuredMolecularWeight = measuredMolecularWeight;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public String getDimers() {
		return dimers;
	}

	public void setDimers(String dimers) {
		this.dimers = dimers;
	}

	public String getPurification() {
		return purification;
	}

	public void setPurification(String purification) {
		this.purification = purification;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	public String getConcentration() {
		return concentration;
	}

	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}

	public String getReconstitution() {
		return reconstitution;
	}

	public void setReconstitution(String reconstitution) {
		this.reconstitution = reconstitution;
	}

	public String getQuantitation() {
		return quantitation;
	}

	public void setQuantitation(String quantitation) {
		this.quantitation = quantitation;
	}

	public String getSolubility() {
		return solubility;
	}

	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getEndotoxinLevel() {
		return endotoxinLevel;
	}

	public void setEndotoxinLevel(String endotoxinLevel) {
		this.endotoxinLevel = endotoxinLevel;
	}

	public String getIsoelectricPoint() {
		return isoelectricPoint;
	}

	public void setIsoelectricPoint(String isoelectricPoint) {
		this.isoelectricPoint = isoelectricPoint;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSequenceAnalysis() {
		return sequenceAnalysis;
	}

	public void setSequenceAnalysis(String sequenceAnalysis) {
		this.sequenceAnalysis = sequenceAnalysis;
	}

	public String getUv() {
		return uv;
	}

	public void setUv(String uv) {
		this.uv = uv;
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

	public String getSubstrate() {
		return substrate;
	}

	public void setSubstrate(String substrate) {
		this.substrate = substrate;
	}

}
