package com.genscript.gsscm.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderLineERPMapping.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_line_erp_mapping", catalog="order")
public class OrderLineErpMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8153630008012049135L;
	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private Integer itemNo;
	private String erpUsSo;
	private String erpUsSoLine;
	private String erpUsPo;
	private String erpUsPoLine;
	private String erpNjSo;
	private String erpNjSoLine;
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
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public String getErpUsSo() {
		return erpUsSo;
	}
	public void setErpUsSo(String erpUsSo) {
		this.erpUsSo = erpUsSo;
	}
	public String getErpUsSoLine() {
		return erpUsSoLine;
	}
	public void setErpUsSoLine(String erpUsSoLine) {
		this.erpUsSoLine = erpUsSoLine;
	}
	public String getErpUsPo() {
		return erpUsPo;
	}
	public void setErpUsPo(String erpUsPo) {
		this.erpUsPo = erpUsPo;
	}
	public String getErpUsPoLine() {
		return erpUsPoLine;
	}
	public void setErpUsPoLine(String erpUsPoLine) {
		this.erpUsPoLine = erpUsPoLine;
	}
	public String getErpNjSo() {
		return erpNjSo;
	}
	public void setErpNjSo(String erpNjSo) {
		this.erpNjSo = erpNjSo;
	}
	public String getErpNjSoLine() {
		return erpNjSoLine;
	}
	public void setErpNjSoLine(String erpNjSoLine) {
		this.erpNjSoLine = erpNjSoLine;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
