package com.genscript.gsscm.basedata.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.shipment.entity.ShipRate;

@XmlType(name = "DropDownDTO", namespace = WsConstants.NS)
public class DropDownDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6893996521370499000L;
	private String id;
	private String name;
	private String value;
	private List<ShipRate> shipRateList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<ShipRate> getShipRateList() {
		return shipRateList;
	}
	public void setShipRateList(List<ShipRate> shipRateList) {
		this.shipRateList = shipRateList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
