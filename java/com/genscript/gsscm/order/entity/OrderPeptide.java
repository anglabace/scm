package com.genscript.gsscm.order.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
/* ORDER PEPTIDE.
 * The persistent class for the order_peptide database table.
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_peptide", catalog="order")
public class OrderPeptide extends BaseEntity {
	@Id
	private Integer orderItemId;
	private Integer orderNo;
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
    private String realPurity;
    private String realQuantity;
	private Integer seqLength;
    @Lob()
	private String sequence;
    private String qcReport;
    private String synMembrane;
    private String deliveryFormat;
    private BigDecimal molecularWeight;
    private BigDecimal pi;

    public BigDecimal getPi() {
        return pi;
    }

    public void setPi(BigDecimal pi) {
        this.pi = pi;
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

    public String getRealPurity() {
        return realPurity;
    }

    public void setRealPurity(String realPurity) {
        this.realPurity = realPurity;
    }

    public String getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(String realQuantity) {
        this.realQuantity = realQuantity;
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

	public String getCTerminal() {
		return CTerminal;
	}

	public void setCTerminal(String cTerminal) {
		CTerminal = cTerminal;
	}

	public String getNTerminal() {
		return NTerminal;
	}

	public void setNTerminal(String nTerminal) {
		NTerminal = nTerminal;
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

	public BigDecimal getMolecularWeight() {
		return molecularWeight;
	}

	public void setMolecularWeight(BigDecimal molecularWeight) {
		this.molecularWeight = molecularWeight;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
