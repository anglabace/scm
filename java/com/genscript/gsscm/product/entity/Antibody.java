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
@Table(name = "antibody", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Antibody extends BaseEntity {
	@Id
	private Integer productId;
	private String accessionNo;
	private String antigenSpecies;
	private String immunogen;
	private String hostSpecies;
	private String subclass;
	private String cloneId; 
	private String fusionPartner;
	private String appearance;
	private String concentration;
	private String purification;
	private String conjugation; 
	private String endotoxinLevel;
	private String reconstitution;
	private String qualityControl;
	private String specificity;
	private String speciesReactivity;
	private String crossReactivity;
	private String sensitivity;
	private String predictedBandSize;
	private String observedBandSize;
	private String supplierCloneId;
	private String stability;
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
	public String getAntigenSpecies() {
		return antigenSpecies;
	}
	public void setAntigenSpecies(String antigenSpecies) {
		this.antigenSpecies = antigenSpecies;
	}
	public String getImmunogen() {
		return immunogen;
	}
	public void setImmunogen(String immunogen) {
		this.immunogen = immunogen;
	}
	public String getHostSpecies() {
		return hostSpecies;
	}
	public void setHostSpecies(String hostSpecies) {
		this.hostSpecies = hostSpecies;
	}
	public String getSubclass() {
		return subclass;
	}
	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}
	public String getCloneId() {
		return cloneId;
	}
	public void setCloneId(String cloneId) {
		this.cloneId = cloneId;
	}
	public String getFusionPartner() {
		return fusionPartner;
	}
	public void setFusionPartner(String fusionPartner) {
		this.fusionPartner = fusionPartner;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	public String getPurification() {
		return purification;
	}
	public void setPurification(String purification) {
		this.purification = purification;
	}
	public String getConjugation() {
		return conjugation;
	}
	public void setConjugation(String conjugation) {
		this.conjugation = conjugation;
	}
	public String getEndotoxinLevel() {
		return endotoxinLevel;
	}
	public void setEndotoxinLevel(String endotoxinLevel) {
		this.endotoxinLevel = endotoxinLevel;
	}
	public String getReconstitution() {
		return reconstitution;
	}
	public void setReconstitution(String reconstitution) {
		this.reconstitution = reconstitution;
	}
	public String getQualityControl() {
		return qualityControl;
	}
	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}
	public String getSpecificity() {
		return specificity;
	}
	public void setSpecificity(String specificity) {
		this.specificity = specificity;
	}
	public String getSpeciesReactivity() {
		return speciesReactivity;
	}
	public void setSpeciesReactivity(String speciesReactivity) {
		this.speciesReactivity = speciesReactivity;
	}
	public String getCrossReactivity() {
		return crossReactivity;
	}
	public void setCrossReactivity(String crossReactivity) {
		this.crossReactivity = crossReactivity;
	}
	public String getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}
	public String getPredictedBandSize() {
		return predictedBandSize;
	}
	public void setPredictedBandSize(String predictedBandSize) {
		this.predictedBandSize = predictedBandSize;
	}
	public String getObservedBandSize() {
		return observedBandSize;
	}
	public void setObservedBandSize(String observedBandSize) {
		this.observedBandSize = observedBandSize;
	}
	public String getSupplierCloneId() {
		return supplierCloneId;
	}
	public void setSupplierCloneId(String supplierCloneId) {
		this.supplierCloneId = supplierCloneId;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	
	
}
