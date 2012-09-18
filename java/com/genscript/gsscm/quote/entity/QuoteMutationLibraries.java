package com.genscript.gsscm.quote.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name="quote_mutation_libraries", catalog = "order")
public class QuoteMutationLibraries extends BaseEntity implements Serializable {
	@Id
	private Integer quoteItemId;
	private Integer quoteNo;
	private String constructName;
	private String libraryType;
	private String tmplFlag;
	private String tmplInsertName;
	private String tmplCloningMethod;
	private String tmplCloningSite;
	@Lob
	private String tmplSequence;
	private String tmplVector;
	private String tmplVectorSize;
	private String tmplResistance;
	private String tmplCopyNo;
	@Lob
	private String tmplVectorSeq;
	private String tmplMapDocFlag;
	@Lob
	private String interestSequence;
	private String degeneratedSites;
	private String cloningFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String geneUsage;
	private String readingFrame;
	private String otherDescription;
	@Lob
	private String otherRequirement;
	private String serviceDocFlag;
	private String tgtVectorName;

	private static final long serialVersionUID = 1L;

	public QuoteMutationLibraries() {
		super();
	}

	public String getConstructName() {
		return this.constructName;
	}

	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}

	public String getLibraryType() {
		return this.libraryType;
	}

	public void setLibraryType(String libraryType) {
		this.libraryType = libraryType;
	}

	public String getTmplFlag() {
		return tmplFlag;
	}

	public void setTmplFlag(String tmplFlag) {
		this.tmplFlag = tmplFlag;
	}

	public String getTmplInsertName() {
		return this.tmplInsertName;
	}

	public void setTmplInsertName(String tmplInsertName) {
		this.tmplInsertName = tmplInsertName;
	}

	public String getTmplCloningMethod() {
		return this.tmplCloningMethod;
	}

	public void setTmplCloningMethod(String tmplCloningMethod) {
		this.tmplCloningMethod = tmplCloningMethod;
	}

	public String getTmplCloningSite() {
		return this.tmplCloningSite;
	}

	public void setTmplCloningSite(String tmplCloningSite) {
		this.tmplCloningSite = tmplCloningSite;
	}

	public String getTmplSequence() {
		return this.tmplSequence;
	}

	public void setTmplSequence(String tmplSequence) {
		this.tmplSequence = tmplSequence;
	}

	public String getTmplVector() {
		return this.tmplVector;
	}

	public void setTmplVector(String tmplVector) {
		this.tmplVector = tmplVector;
	}

	public String getTmplVectorSize() {
		return this.tmplVectorSize;
	}

	public void setTmplVectorSize(String tmplVectorSize) {
		this.tmplVectorSize = tmplVectorSize;
	}

	public String getTmplResistance() {
		return this.tmplResistance;
	}

	public void setTmplResistance(String tmplResistance) {
		this.tmplResistance = tmplResistance;
	}

	public String getTmplCopyNo() {
		return this.tmplCopyNo;
	}

	public void setTmplCopyNo(String tmplCopyNo) {
		this.tmplCopyNo = tmplCopyNo;
	}

	public String getTmplVectorSeq() {
		return this.tmplVectorSeq;
	}

	public void setTmplVectorSeq(String tmplVectorSeq) {
		this.tmplVectorSeq = tmplVectorSeq;
	}

	public String getTmplMapDocFlag() {
		return this.tmplMapDocFlag;
	}

	public void setTmplMapDocFlag(String tmplMapDocFlag) {
		this.tmplMapDocFlag = tmplMapDocFlag;
	}

	public String getCloningFlag() {
		return this.cloningFlag;
	}

	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}

	public Integer getStdPlasmidWt() {
		return this.stdPlasmidWt;
	}

	public void setStdPlasmidWt(Integer stdPlasmidWt) {
		this.stdPlasmidWt = stdPlasmidWt;
	}

	public String getStdPlasmidWtUom() {
		return this.stdPlasmidWtUom;
	}

	public void setStdPlasmidWtUom(String stdPlasmidWtUom) {
		this.stdPlasmidWtUom = stdPlasmidWtUom;
	}

	public String getPlasmidPrepFlag() {
		return this.plasmidPrepFlag;
	}

	public void setPlasmidPrepFlag(String plasmidPrepFlag) {
		this.plasmidPrepFlag = plasmidPrepFlag;
	}

	public String getGeneUsage() {
		return this.geneUsage;
	}

	public void setGeneUsage(String geneUsage) {
		this.geneUsage = geneUsage;
	}

	public String getReadingFrame() {
		return this.readingFrame;
	}

	public void setReadingFrame(String readingFrame) {
		this.readingFrame = readingFrame;
	}

	public String getOtherDescription() {
		return this.otherDescription;
	}

	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}

	public String getOtherRequirement() {
		return this.otherRequirement;
	}

	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	public String getServiceDocFlag() {
		return this.serviceDocFlag;
	}

	public void setServiceDocFlag(String serviceDocFlag) {
		this.serviceDocFlag = serviceDocFlag;
	}

	/**
	 * @return the interestSequence
	 */
	public String getInterestSequence() {
		return interestSequence;
	}

	/**
	 * @param interestSequence the interestSequence to set
	 */
	public void setInterestSequence(String interestSequence) {
		this.interestSequence = interestSequence;
	}

	public Integer getQuoteItemId() {
		return quoteItemId;
	}

	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
	}

	public Integer getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}

	public String getDegeneratedSites() {
		return degeneratedSites;
	}

	public void setDegeneratedSites(String degeneratedSites) {
		this.degeneratedSites = degeneratedSites;
	}

	public String getTgtVectorName() {
		return tgtVectorName;
	}

	public void setTgtVectorName(String tgtVectorName) {
		this.tgtVectorName = tgtVectorName;
	}

}
