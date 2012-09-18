package com.genscript.gsscm.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrderMutagenesis
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "order_mutagenesis", catalog = "order")
public class OrderMutagenesis extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3547609753621318701L;
	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private String tmplInsertName;
	private String tmplCloningMethod;
	private String tmplCloningSite;
	private String tmplSequence;
	private String tmplVector;
	private String tmplVectorSize;
	private String tmplResistance;
	private String tmplCopyNo;
	private String tmplVectorSeq;
	private String tmplMapDocFlag;
	private String variantName;
	private String variantSequence;
	private String cloningFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String geneUsage;
	private String readingFrame;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the orderItemId
	 */
	public Integer getOrderItemId() {
		return orderItemId;
	}

	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the tmplInsertName
	 */
	public String getTmplInsertName() {
		return tmplInsertName;
	}

	/**
	 * @param tmplInsertName the tmplInsertName to set
	 */
	public void setTmplInsertName(String tmplInsertName) {
		this.tmplInsertName = tmplInsertName;
	}


	/**
	 * @return the tmplCloningSite
	 */
	public String getTmplCloningSite() {
		return tmplCloningSite;
	}

	/**
	 * @param tmplCloningSite the tmplCloningSite to set
	 */
	public void setTmplCloningSite(String tmplCloningSite) {
		this.tmplCloningSite = tmplCloningSite;
	}

	/**
	 * @return the tmplSequence
	 */
	public String getTmplSequence() {
		return tmplSequence;
	}

	/**
	 * @param tmplSequence the tmplSequence to set
	 */
	public void setTmplSequence(String tmplSequence) {
		this.tmplSequence = tmplSequence;
	}

	/**
	 * @return the tmplVector
	 */
	public String getTmplVector() {
		return tmplVector;
	}

	/**
	 * @param tmplVector the tmplVector to set
	 */
	public void setTmplVector(String tmplVector) {
		this.tmplVector = tmplVector;
	}

	/**
	 * @return the tmplVectorSize
	 */
	public String getTmplVectorSize() {
		return tmplVectorSize;
	}

	/**
	 * @param tmplVectorSize the tmplVectorSize to set
	 */
	public void setTmplVectorSize(String tmplVectorSize) {
		this.tmplVectorSize = tmplVectorSize;
	}

	/**
	 * @return the tmplResistance
	 */
	public String getTmplResistance() {
		return tmplResistance;
	}

	/**
	 * @param tmplResistance the tmplResistance to set
	 */
	public void setTmplResistance(String tmplResistance) {
		this.tmplResistance = tmplResistance;
	}

	/**
	 * @return the tmplCopyNo
	 */
	public String getTmplCopyNo() {
		return tmplCopyNo;
	}

	/**
	 * @param tmplCopyNo the tmplCopyNo to set
	 */
	public void setTmplCopyNo(String tmplCopyNo) {
		this.tmplCopyNo = tmplCopyNo;
	}

	/**
	 * @return the tmplVectorSeq
	 */
	public String getTmplVectorSeq() {
		return tmplVectorSeq;
	}

	/**
	 * @param tmplVectorSeq the tmplVectorSeq to set
	 */
	public void setTmplVectorSeq(String tmplVectorSeq) {
		this.tmplVectorSeq = tmplVectorSeq;
	}

	/**
	 * @return the tmplMapDocFlag
	 */
	public String getTmplMapDocFlag() {
		return tmplMapDocFlag;
	}

	/**
	 * @param tmplMapDocFlag the tmplMapDocFlag to set
	 */
	public void setTmplMapDocFlag(String tmplMapDocFlag) {
		this.tmplMapDocFlag = tmplMapDocFlag;
	}

	/**
	 * @return the variantName
	 */
	public String getVariantName() {
		return variantName;
	}

	/**
	 * @param variantName the variantName to set
	 */
	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	/**
	 * @return the variantSequence
	 */
	public String getVariantSequence() {
		return variantSequence;
	}

	/**
	 * @param variantSequence the variantSequence to set
	 */
	public void setVariantSequence(String variantSequence) {
		this.variantSequence = variantSequence;
	}

	/**
	 * @return the cloningFlag
	 */
	public String getCloningFlag() {
		return cloningFlag;
	}

	/**
	 * @param cloningFlag the cloningFlag to set
	 */
	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}

	/**
	 * @return the stdPlasmidWt
	 */
	public Integer getStdPlasmidWt() {
		return stdPlasmidWt;
	}

	/**
	 * @param stdPlasmidWt the stdPlasmidWt to set
	 */
	public void setStdPlasmidWt(Integer stdPlasmidWt) {
		this.stdPlasmidWt = stdPlasmidWt;
	}

	/**
	 * @return the stdPlasmidWtUom
	 */
	public String getStdPlasmidWtUom() {
		return stdPlasmidWtUom;
	}

	/**
	 * @param stdPlasmidWtUom the stdPlasmidWtUom to set
	 */
	public void setStdPlasmidWtUom(String stdPlasmidWtUom) {
		this.stdPlasmidWtUom = stdPlasmidWtUom;
	}

	/**
	 * @return the plasmidPrepFlag
	 */
	public String getPlasmidPrepFlag() {
		return plasmidPrepFlag;
	}

	/**
	 * @param plasmidPrepFlag the plasmidPrepFlag to set
	 */
	public void setPlasmidPrepFlag(String plasmidPrepFlag) {
		this.plasmidPrepFlag = plasmidPrepFlag;
	}

	/**
	 * @return the geneUsage
	 */
	public String getGeneUsage() {
		return geneUsage;
	}

	/**
	 * @param geneUsage the geneUsage to set
	 */
	public void setGeneUsage(String geneUsage) {
		this.geneUsage = geneUsage;
	}

	/**
	 * @return the readingFrame
	 */
	public String getReadingFrame() {
		return readingFrame;
	}

	/**
	 * @param readingFrame the readingFrame to set
	 */
	public void setReadingFrame(String readingFrame) {
		this.readingFrame = readingFrame;
	}

	/**
	 * @return the otherDescription
	 */
	public String getOtherDescription() {
		return otherDescription;
	}

	/**
	 * @param otherDescription the otherDescription to set
	 */
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}

	/**
	 * @return the otherRequirement
	 */
	public String getOtherRequirement() {
		return otherRequirement;
	}

	/**
	 * @param otherRequirement the otherRequirement to set
	 */
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	/**
	 * @return the serviceDocFlag
	 */
	public String getServiceDocFlag() {
		return serviceDocFlag;
	}

	/**
	 * @param serviceDocFlag the serviceDocFlag to set
	 */
	public void setServiceDocFlag(String serviceDocFlag) {
		this.serviceDocFlag = serviceDocFlag;
	}

	/**
	 * @return the tmplCloningMethod
	 */
	public String getTmplCloningMethod() {
		return tmplCloningMethod;
	}

	/**
	 * @param tmplCloningMethod the tmplCloningMethod to set
	 */
	public void setTmplCloningMethod(String tmplCloningMethod) {
		this.tmplCloningMethod = tmplCloningMethod;
	}
}
