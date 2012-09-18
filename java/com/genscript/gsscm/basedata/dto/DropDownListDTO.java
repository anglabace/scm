package com.genscript.gsscm.basedata.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.shipment.entity.ShipZone;

@XmlType(name = "DropDownListDTO", namespace = WsConstants.NS)
public class DropDownListDTO {

	private String name;
	private List<DropDownDTO> dropDownDTOs;
	private List<ShipZone> shipZoneList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DropDownDTO> getDropDownDTOs() {
		return dropDownDTOs;
	}
	public void setDropDownDTOs(List<DropDownDTO> dropDownDTOs) {
		this.dropDownDTOs = dropDownDTOs;
	}
	public List<ShipZone> getShipZoneList() {
		return shipZoneList;
	}
	public void setShipZoneList(List<ShipZone> shipZoneList) {
		this.shipZoneList = shipZoneList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
