package com.genscript.gsscm.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * 
 * order_rna entity
 * 
 * @author zouyulu
 * 
 */
@Entity
@Table(name = "order_sirna_and_mirna", catalog = "order")
public class OrderRna extends BaseEntity {

	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private String type;
	private String geneName;
	private String vectorName;
	private String quantity;
	private String sequenceInsert;
	private Integer seqLength;
	private String comments;
	private String geneUsage;
	private String readingFrame;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	
	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getVectorName() {
		return vectorName;
	}

	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSequenceInsert() {
		return sequenceInsert;
	}

	public void setSequenceInsert(String sequenceInsert) {
		this.sequenceInsert = sequenceInsert;
	}

	public Integer getSeqLength() {
		return seqLength;
	}

	public void setSeqLength(Integer seqLength) {
		this.seqLength = seqLength;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGeneUsage() {
		return geneUsage;
	}

	public void setGeneUsage(String geneUsage) {
		this.geneUsage = geneUsage;
	}

	public String getReadingFrame() {
		return readingFrame;
	}

	public void setReadingFrame(String readingFrame) {
		this.readingFrame = readingFrame;
	}

	public String getOtherDescription() {
		return otherDescription;
	}

	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}

	public String getOtherRequirement() {
		return otherRequirement;
	}

	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}

	public String getServiceDocFlag() {
		return serviceDocFlag;
	}

	public void setServiceDocFlag(String serviceDocFlag) {
		this.serviceDocFlag = serviceDocFlag;
	}

	public Integer getStdPlasmidWt() {
		return stdPlasmidWt;
	}

	public void setStdPlasmidWt(Integer stdPlasmidWt) {
		this.stdPlasmidWt = stdPlasmidWt;
	}

	public String getStdPlasmidWtUom() {
		return stdPlasmidWtUom;
	}

	public void setStdPlasmidWtUom(String stdPlasmidWtUom) {
		this.stdPlasmidWtUom = stdPlasmidWtUom;
	}

	public String getPlasmidPrepFlag() {
		return plasmidPrepFlag;
	}

	public void setPlasmidPrepFlag(String plasmidPrepFlag) {
		this.plasmidPrepFlag = plasmidPrepFlag;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
