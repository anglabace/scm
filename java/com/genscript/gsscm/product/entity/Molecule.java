package com.genscript.gsscm.product.entity;

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
@Table(name = "molecule", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Molecule extends BaseEntity {
	@Id
	private Integer productId;
	private String catalogNo;
	private String source;
	private String species;
	private String tissue;
	private String formulation;
	private String molecularFormula;
	private String bandRange;
	private String cultureMedium;
	private String freezeMedium;
	private String clone;
	private String appearance;
	private String color;
	private String stability;
	private String purity;
	private String concentration; 
	private String calculation;
	private String reactionBuffer;
	private String reconstitution;
	private String specificActivity;
	private String specificity; 
	private String sterility;
	private String qualityControl;
	private String volume; 
	
	
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getTissue() {
		return tissue;
	}
	public void setTissue(String tissue) {
		this.tissue = tissue;
	}
	public String getFormulation() {
		return formulation;
	}
	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}
	public String getMolecularFormula() {
		return molecularFormula;
	}
	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}
	public String getBandRange() {
		return bandRange;
	}
	public void setBandRange(String bandRange) {
		this.bandRange = bandRange;
	}
	public String getCultureMedium() {
		return cultureMedium;
	}
	public void setCultureMedium(String cultureMedium) {
		this.cultureMedium = cultureMedium;
	}
	public String getFreezeMedium() {
		return freezeMedium;
	}
	public void setFreezeMedium(String freezeMedium) {
		this.freezeMedium = freezeMedium;
	}
	public String getClone() {
		return clone;
	}
	public void setClone(String clone) {
		this.clone = clone;
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
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	public String getReactionBuffer() {
		return reactionBuffer;
	}
	public void setReactionBuffer(String reactionBuffer) {
		this.reactionBuffer = reactionBuffer;
	}
	public String getReconstitution() {
		return reconstitution;
	}
	public void setReconstitution(String reconstitution) {
		this.reconstitution = reconstitution;
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
	public String getSterility() {
		return sterility;
	}
	public void setSterility(String sterility) {
		this.sterility = sterility;
	}
	public String getQualityControl() {
		return qualityControl;
	}
	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}

}
