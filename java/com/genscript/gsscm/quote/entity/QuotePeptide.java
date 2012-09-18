package com.genscript.gsscm.quote.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
/* QuotePeptide
 * The persistent class for the order_peptide database table.
 * 
 * @author Golf
 */

@Entity
@Table(name = "quote_peptide", catalog="order")
public class QuotePeptide extends BaseEntity {
	@Id
	private Integer quoteItemId;
	private Integer quoteNo;
	private String name;
	private Integer aliquoteVialQty;
	private String aminoChangeFlag;
	@Column(name="c_terminal")
	private String CTerminal;
	private String comments;
	private String disulfideBridge;
	private String modification;
	@Column(name="n_terminal")
	private String NTerminal;
	private String purity;
	private String quantity;
	private Integer seqLength;
	private String qcReport;
	private String synMembrane;
	private String deliveryFormat;
    @Lob()
	private String sequence;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAliquoteVialQty() {
		return aliquoteVialQty;
	}

	public void setAliquoteVialQty(Integer aliquoteVialQty) {
		this.aliquoteVialQty = aliquoteVialQty;
	}

	public String getAminoChangeFlag() {
		return aminoChangeFlag;
	}

	public void setAminoChangeFlag(String aminoChangeFlag) {
		this.aminoChangeFlag = aminoChangeFlag;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDisulfideBridge() {
		return disulfideBridge;
	}

	public void setDisulfideBridge(String disulfideBridge) {
		this.disulfideBridge = disulfideBridge;
	}

	public String getModification() {
		return modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

    public Integer getSeqLength() {
		return seqLength;
	}

	public void setSeqLength(Integer seqLength) {
		this.seqLength = seqLength;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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

	public String getCTerminal() {
		return CTerminal;
	}

	public void setCTerminal(String terminal) {
		CTerminal = terminal;
	}

	public String getNTerminal() {
		return NTerminal;
	}

	public void setNTerminal(String terminal) {
		NTerminal = terminal;
	}

	public String getQcReport() {
		return qcReport;
	}

	public void setQcReport(String qcReport) {
		this.qcReport = qcReport;
	}

	public String getSynMembrane() {
		return synMembrane;
	}

	public void setSynMembrane(String synMembrane) {
		this.synMembrane = synMembrane;
	}

	public String getDeliveryFormat() {
		return deliveryFormat;
	}

	public void setDeliveryFormat(String deliveryFormat) {
		this.deliveryFormat = deliveryFormat;
	}

}
