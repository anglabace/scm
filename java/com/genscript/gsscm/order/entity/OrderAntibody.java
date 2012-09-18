package com.genscript.gsscm.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * 
 * order_antibody entity
 * @author zouyulu
 *
 */
@Entity
@Table(name = "order_antibody", catalog = "order")
public class OrderAntibody extends BaseEntity {

	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private String antibodyName;
	private String antigenType;
	@Lob
	private String customSequence;
	private String comments;

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

	public String getAntibodyName() {
		return antibodyName;
	}

	public void setAntibodyName(String antibodyName) {
		this.antibodyName = antibodyName;
	}

	public String getAntigenType() {
		return antigenType;
	}

	public void setAntigenType(String antigenType) {
		this.antigenType = antigenType;
	}

	public String getCustomSequence() {
		return customSequence;
	}

	public void setCustomSequence(String customSequence) {
		this.customSequence = customSequence;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
