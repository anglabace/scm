package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "SalesRankingDTO", namespace = WsConstants.NS)
public class SalesRankingDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8806582583411373118L;
	private String salesContact;
	private Double sumQuantity;

	public String getSalesContact() {
		return salesContact;
	}
	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}
	public Double getSumQuantity() {
		return sumQuantity;
	}
	public void setSumQuantity(Double sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
	

}
