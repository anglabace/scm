package com.genscript.gsscm.shipment.dto;

import com.genscript.gsscm.shipment.entity.ShipClerk;

public class ShipClerkDTO {
	private ShipClerk shipClerk;
	private String name;
	public ShipClerk getShipClerk() {
		return shipClerk;
	}
	public void setShipClerk(ShipClerk shipClerk) {
		this.shipClerk = shipClerk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
