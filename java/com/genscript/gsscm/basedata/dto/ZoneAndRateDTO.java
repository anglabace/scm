package com.genscript.gsscm.basedata.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipZone;

@XmlType(name = "ZoneAndRateDTO", namespace = WsConstants.NS)
public class ZoneAndRateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021150428842638362L;
	private Page<ShipZone> pageShipZone;
	private Page<ShipRate> pageShipRate;
	public Page<ShipZone> getPageShipZone() {
		return pageShipZone;
	}
	public void setPageShipZone(Page<ShipZone> pageShipZone) {
		this.pageShipZone = pageShipZone;
	}
	public Page<ShipRate> getPageShipRate() {
		return pageShipRate;
	}
	public void setPageShipRate(Page<ShipRate> pageShipRate) {
		this.pageShipRate = pageShipRate;
	}
	
}
