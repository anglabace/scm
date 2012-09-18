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
@Table(name = "chemical", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chemical extends BaseEntity {
	@Id
	private Integer productId;
	private String casNo;
	private String molecularFormula;
	private String residue;
	private Double molecularWeight;
	private String amine;
	private String chloride;
	private String sulfate;
	private String color;
	private String appearance;
	private String formulation;
	private String tlcAnalysis;
	private String purity;
	private String boilingPoint;
	private String meltingPoint;
	private String flashPoint; 
	private String e0;
	private String ca;
	private String heavyMetals;
	private String fe;
	private String pb;
	private String formazan;
	private String conductivity;
	private String dyeContent;
	private String infrared;
	private String lossOnDrying;
	private String moisture; 
	private String molarAbso;
	private String opticPurity;
	private String pka;
	private String ph;
	private String usablePhRange;
	private String sensitivity;
	private String solubility;
	private String refractive;
	private String stability; 
	private String transmittancy;
	private String uv;
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
	public String getMolecularFormula() {
		return molecularFormula;
	}
	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}
	public String getResidue() {
		return residue;
	}
	public void setResidue(String residue) {
		this.residue = residue;
	}
	public Double getMolecularWeight() {
		return molecularWeight;
	}
	public void setMolecularWeight(Double molecularWeight) {
		this.molecularWeight = molecularWeight;
	}
	public String getAmine() {
		return amine;
	}
	public void setAmine(String amine) {
		this.amine = amine;
	}
	public String getChloride() {
		return chloride;
	}
	public void setChloride(String chloride) {
		this.chloride = chloride;
	}
	public String getSulfate() {
		return sulfate;
	}
	public void setSulfate(String sulfate) {
		this.sulfate = sulfate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getFormulation() {
		return formulation;
	}
	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}
	public String getTlcAnalysis() {
		return tlcAnalysis;
	}
	public void setTlcAnalysis(String tlcAnalysis) {
		this.tlcAnalysis = tlcAnalysis;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getBoilingPoint() {
		return boilingPoint;
	}
	public void setBoilingPoint(String boilingPoint) {
		this.boilingPoint = boilingPoint;
	}
	public String getMeltingPoint() {
		return meltingPoint;
	}
	public void setMeltingPoint(String meltingPoint) {
		this.meltingPoint = meltingPoint;
	}
	public String getFlashPoint() {
		return flashPoint;
	}
	public void setFlashPoint(String flashPoint) {
		this.flashPoint = flashPoint;
	}
	public String getE0() {
		return e0;
	}
	public void setE0(String e0) {
		this.e0 = e0;
	}
	public String getCa() {
		return ca;
	}
	public void setCa(String ca) {
		this.ca = ca;
	}
	public String getHeavyMetals() {
		return heavyMetals;
	}
	public void setHeavyMetals(String heavyMetals) {
		this.heavyMetals = heavyMetals;
	}
	public String getFe() {
		return fe;
	}
	public void setFe(String fe) {
		this.fe = fe;
	}
	public String getPb() {
		return pb;
	}
	public void setPb(String pb) {
		this.pb = pb;
	}
	public String getFormazan() {
		return formazan;
	}
	public void setFormazan(String formazan) {
		this.formazan = formazan;
	}
	public String getConductivity() {
		return conductivity;
	}
	public void setConductivity(String conductivity) {
		this.conductivity = conductivity;
	}
	public String getDyeContent() {
		return dyeContent;
	}
	public void setDyeContent(String dyeContent) {
		this.dyeContent = dyeContent;
	}
	public String getInfrared() {
		return infrared;
	}
	public void setInfrared(String infrared) {
		this.infrared = infrared;
	}
	public String getLossOnDrying() {
		return lossOnDrying;
	}
	public void setLossOnDrying(String lossOnDrying) {
		this.lossOnDrying = lossOnDrying;
	}
	public String getMoisture() {
		return moisture;
	}
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}
	public String getMolarAbso() {
		return molarAbso;
	}
	public void setMolarAbso(String molarAbso) {
		this.molarAbso = molarAbso;
	}
	public String getOpticPurity() {
		return opticPurity;
	}
	public void setOpticPurity(String opticPurity) {
		this.opticPurity = opticPurity;
	}
	public String getPka() {
		return pka;
	}
	public void setPka(String pka) {
		this.pka = pka;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getUsablePhRange() {
		return usablePhRange;
	}
	public void setUsablePhRange(String usablePhRange) {
		this.usablePhRange = usablePhRange;
	}
	public String getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}
	public String getSolubility() {
		return solubility;
	}
	public void setSolubility(String solubility) {
		this.solubility = solubility;
	}
	public String getRefractive() {
		return refractive;
	}
	public void setRefractive(String refractive) {
		this.refractive = refractive;
	}
	public String getStability() {
		return stability;
	}
	public void setStability(String stability) {
		this.stability = stability;
	}
	public String getTransmittancy() {
		return transmittancy;
	}
	public void setTransmittancy(String transmittancy) {
		this.transmittancy = transmittancy;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
}
