package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.OrderLineErpMapping;

@XmlType(name = "OrderErpMappingDTO", namespace = WsConstants.NS)
public class OrderErpMappingDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5615373652273939861L;
	private Integer orderNo;
//	private Integer itemNo;
	private String erpUsSo;
//	private String erpUsSoLine;
	private String erpUsPo;
//	private String erpUsPoLine;
	private String erpNjSo;
//	private String erpNjSoLine;
	private List<OrderLineErpMapping> orderLineList;
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public List<OrderLineErpMapping> getOrderLineList() {
		return orderLineList;
	}
	public void setOrderLineList(List<OrderLineErpMapping> orderLineList) {
		this.orderLineList = orderLineList;
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
