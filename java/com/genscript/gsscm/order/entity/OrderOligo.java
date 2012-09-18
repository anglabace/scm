package com.genscript.gsscm.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * Order Oligo
 * @author Zhang Yong
 *
 */
@Entity
@Table(name = "order_oligo", catalog="order")
public class OrderOligo extends BaseEntity {
	@Id
	private Integer 	orderItemId;
	private Integer 	orderNo;
	private String 		oligoName;
	private Integer 	aliquotingInto;
	private String 		backbone;
	private String 		purification;
	private String 		synthesisScales;
	private String		sequence;
	private Integer 	seqLength;
	private String		modification5;
	private String 		internalModification;
	private String 		modification3;
	private String 		othermodificationFlag5;
	private String 		interOtherModificationFlag;
	private String		othermodificationFlag3;
	private String 		othermodification5;
	private String 		interOtherModification;
	private String 		othermodification3;
	private String		chimericBases;
	private String		standardMixedMases;
	private Integer		daPercent;
	private Integer  	dcPercent;
	private Integer		dgPercent;
	private Integer		dtPercent;
	private String		comments;
	private BigDecimal 	aliquotingSize;
	
	public OrderOligo() {
		super();
	}
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
	public String getOligoName() {
		return oligoName;
	}
	public void setOligoName(String oligoName) {
		this.oligoName = oligoName;
	}
	public Integer getAliquotingInto() {
		return aliquotingInto;
	}
	public void setAliquotingInto(Integer aliquotingInto) {
		this.aliquotingInto = aliquotingInto;
	}
	public String getBackbone() {
		return backbone;
	}
	public void setBackbone(String backbone) {
		this.backbone = backbone;
	}
	public String getPurification() {
		return purification;
	}
	public void setPurification(String purification) {
		this.purification = purification;
	}
	public String getSynthesisScales() {
		return synthesisScales;
	}
	public void setSynthesisScales(String synthesisScales) {
		this.synthesisScales = synthesisScales;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getModification5() {
		return modification5;
	}
	public void setModification5(String modification5) {
		this.modification5 = modification5;
	}
	public String getInternalModification() {
		return internalModification;
	}
	public void setInternalModification(String internalModification) {
		this.internalModification = internalModification;
	}
	public String getModification3() {
		return modification3;
	}
	public void setModification3(String modification3) {
		this.modification3 = modification3;
	}
	public String getOthermodificationFlag5() {
		return othermodificationFlag5;
	}
	public void setOthermodificationFlag5(String othermodificationFlag5) {
		this.othermodificationFlag5 = othermodificationFlag5;
	}
	public String getInterOtherModificationFlag() {
		return interOtherModificationFlag;
	}
	public void setInterOtherModificationFlag(String interOtherModificationFlag) {
		this.interOtherModificationFlag = interOtherModificationFlag;
	}
	public String getOthermodificationFlag3() {
		return othermodificationFlag3;
	}
	public void setOthermodificationFlag3(String othermodificationFlag3) {
		this.othermodificationFlag3 = othermodificationFlag3;
	}
	public String getOthermodification5() {
		return othermodification5;
	}
	public void setOthermodification5(String othermodification5) {
		this.othermodification5 = othermodification5;
	}
	public String getInterOtherModification() {
		return interOtherModification;
	}
	public void setInterOtherModification(String interOtherModification) {
		this.interOtherModification = interOtherModification;
	}
	public String getOthermodification3() {
		return othermodification3;
	}
	public void setOthermodification3(String othermodification3) {
		this.othermodification3 = othermodification3;
	}
	public String getChimericBases() {
		return chimericBases;
	}
	public void setChimericBases(String chimericBases) {
		this.chimericBases = chimericBases;
	}
	public String getStandardMixedMases() {
		return standardMixedMases;
	}
	public void setStandardMixedMases(String standardMixedMases) {
		this.standardMixedMases = standardMixedMases;
	}
	public Integer getDaPercent() {
		return daPercent;
	}
	public void setDaPercent(Integer daPercent) {
		this.daPercent = daPercent;
	}
	public Integer getDcPercent() {
		return dcPercent;
	}
	public void setDcPercent(Integer dcPercent) {
		this.dcPercent = dcPercent;
	}
	public Integer getDgPercent() {
		return dgPercent;
	}
	public void setDgPercent(Integer dgPercent) {
		this.dgPercent = dgPercent;
	}
	public Integer getDtPercent() {
		return dtPercent;
	}
	public void setDtPercent(Integer dtPercent) {
		this.dtPercent = dtPercent;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getSeqLength() {
		return seqLength;
	}
	public void setSeqLength(Integer seqLength) {
		this.seqLength = seqLength;
	}
	public BigDecimal getAliquotingSize() {
		return aliquotingSize;
	}
	public void setAliquotingSize(BigDecimal aliquotingSize) {
		this.aliquotingSize = aliquotingSize;
	}
	
	
}
