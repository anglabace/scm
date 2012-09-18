package com.genscript.gsscm.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * The persistent class for the order_sirna_and_mirna database table.
 * 
 * @author Golf
 */
@Entity
@Table(name="order_sirna_and_mirna", catalog="order")
public class OrderSirnaAndMirna extends BaseEntity {
	@Id
	private Integer orderItemId;
	@Lob()
	private String comments;
	private String geneName;
	private Integer orderNo;
	private String quantity;
    @Lob()
	private String sequenceInsert;
	private String type;
	private String vectorName;
	private Integer seqLength;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVectorName() {
		return vectorName;
	}

	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}
	public Integer getSeqLength() {
		return seqLength;
	}

	public void setSeqLength(Integer seqLength) {
		this.seqLength = seqLength;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
