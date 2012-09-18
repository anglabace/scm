package com.genscript.gsscm.purchase.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * poReceivingTmp.
 * 
 * 
 * @author mingrs.
 */

@Entity
@Table(name = "po_receiving_tmp", catalog="purchase")
public class PoReceivingTmp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer poNo;
	@Column(name = "po_item_no")
	private Integer poLineNo;
	private Double size;
	private Integer qty;
	private Date receivingTime;
	private String trackingNo;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPoNo() {
		return poNo;
	}
	public void setPoNo(Integer poNo) {
		this.poNo = poNo;
	}
	public Integer getPoLineNo() {
		return poLineNo;
	}
	public void setPoLineNo(Integer poLineNo) {
		this.poLineNo = poLineNo;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Date getReceivingTime() {
		return receivingTime;
	}
	public void setReceivingTime(Date receivingTime) {
		this.receivingTime = receivingTime;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	} 
	
}
