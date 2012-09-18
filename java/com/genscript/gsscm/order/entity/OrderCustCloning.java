package com.genscript.gsscm.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrderCustCloning
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "order_custom_cloning", catalog = "order")
public class OrderCustCloning extends BaseEntity implements Serializable {

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
	private String tgtInsertName;
	private String tgtCloningMethod;
	private String tgtCloningSite;
	private String tgtCloningSite3;
	private String tgtSequence;
	private String tgtVector;
	private String tgtVectorSize;
	private String tgtResistance;
	private String tgtCopyNo;
	private String tgtVectorSeq;
	private String tgtMapDocFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String geneUsage;
	private String readingFrame;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;
	private String cloningFlag;
	private Integer parentClsId;
	private Integer tgtSeqLength;
	private String tgtSeqSameFlag;
	
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
	 * @return the tgtInsertName
	 */
	public String getTgtInsertName() {
		return tgtInsertName;
	}
	/**
	 * @param tgtInsertName the tgtInsertName to set
	 */
	public void setTgtInsertName(String tgtInsertName) {
		this.tgtInsertName = tgtInsertName;
	}
	/**
	 * @return the tgtCloningMethod
	 */
	public String getTgtCloningMethod() {
		return tgtCloningMethod;
	}
	/**
	 * @param tgtCloningMethod the tgtCloningMethod to set
	 */
	public void setTgtCloningMethod(String tgtCloningMethod) {
		this.tgtCloningMethod = tgtCloningMethod;
	}
	/**
	 * @return the tgtCloningSite
	 */
	public String getTgtCloningSite() {
		return tgtCloningSite;
	}
	/**
	 * @param tgtCloningSite the tgtCloningSite to set
	 */
	public void setTgtCloningSite(String tgtCloningSite) {
		this.tgtCloningSite = tgtCloningSite;
	}
	/**
	 * @return the tgtSequence
	 */
	public String getTgtSequence() {
		return tgtSequence;
	}
	/**
	 * @param tgtSequence the tgtSequence to set
	 */
	public void setTgtSequence(String tgtSequence) {
		this.tgtSequence = tgtSequence;
	}
	/**
	 * @return the tgtVector
	 */
	public String getTgtVector() {
		return tgtVector;
	}
	/**
	 * @param tgtVector the tgtVector to set
	 */
	public void setTgtVector(String tgtVector) {
		this.tgtVector = tgtVector;
	}
	/**
	 * @return the tgtVectorSize
	 */

    public String getTgtVectorSize() {
        return tgtVectorSize;
    }

    public void setTgtVectorSize(String tgtVectorSize) {
        this.tgtVectorSize = tgtVectorSize;
    }

    /**
	 * @return the tgtResistance
	 */
	public String getTgtResistance() {
		return tgtResistance;
	}
	/**
	 * @param tgtResistance the tgtResistance to set
	 */
	public void setTgtResistance(String tgtResistance) {
		this.tgtResistance = tgtResistance;
	}
	/**
	 * @return the tgtCopyNo
	 */
	public String getTgtCopyNo() {
		return tgtCopyNo;
	}
	/**
	 * @param tgtCopyNo the tgtCopyNo to set
	 */
	public void setTgtCopyNo(String tgtCopyNo) {
		this.tgtCopyNo = tgtCopyNo;
	}
	/**
	 * @return the tgtVectorSeq
	 */
	public String getTgtVectorSeq() {
		return tgtVectorSeq;
	}
	/**
	 * @param tgtVectorSeq the tgtVectorSeq to set
	 */
	public void setTgtVectorSeq(String tgtVectorSeq) {
		this.tgtVectorSeq = tgtVectorSeq;
	}
	/**
	 * @return the tgtMapDocFlag
	 */
	public String getTgtMapDocFlag() {
		return tgtMapDocFlag;
	}
	/**
	 * @param tgtMapDocFlag the tgtMapDocFlag to set
	 */
	public void setTgtMapDocFlag(String tgtMapDocFlag) {
		this.tgtMapDocFlag = tgtMapDocFlag;
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
	public String getCloningFlag() {
		return cloningFlag;
	}
	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}
	public Integer getParentClsId() {
		return parentClsId;
	}
	public void setParentClsId(Integer parentClsId) {
		this.parentClsId = parentClsId;
	}
	public Integer getTgtSeqLength() {
		return tgtSeqLength;
	}
	public void setTgtSeqLength(Integer tgtSeqLength) {
		this.tgtSeqLength = tgtSeqLength;
	}
	public String getTgtSeqSameFlag() {
		return tgtSeqSameFlag;
	}
	public void setTgtSeqSameFlag(String tgtSeqSameFlag) {
		this.tgtSeqSameFlag = tgtSeqSameFlag;
	}
	public String getTgtCloningSite3() {
		return tgtCloningSite3;
	}
	public void setTgtCloningSite3(String tgtCloningSite3) {
		this.tgtCloningSite3 = tgtCloningSite3;
	}

}
