package com.genscript.gsscm.shipment.dto;

import java.math.BigDecimal;

public class LotNoDTO {
	private String lotNo;
	private BigDecimal value;
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
