package com.genscript.gsscm.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderERPMapping.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order_erp_mapping", catalog="order")
public class OrderErpMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7555380943259223582L;
	@Id
	private Integer orderNo;
	private String erpUsSo;
	private String erpUsPo;
	private String erpNjSo;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getErpUsSo() {
		return erpUsSo;
	}
	public void setErpUsSo(String erpUsSo) {
		this.erpUsSo = erpUsSo;
	}
	public String getErpUsPo() {
		return erpUsPo;
	}
	public void setErpUsPo(String erpUsPo) {
		this.erpUsPo = erpUsPo;
	}
	public String getErpNjSo() {
		return erpNjSo;
	}
	public void setErpNjSo(String erpNjSo) {
		this.erpNjSo = erpNjSo;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
