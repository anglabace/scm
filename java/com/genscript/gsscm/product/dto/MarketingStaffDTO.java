package com.genscript.gsscm.product.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.entity.MarketingStaff;

@XmlType(name = "MarketingStaffDTO", namespace = WsConstants.NS)
public class MarketingStaffDTO {
	private MarketingStaff marketingStaff;
	private String userName;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public MarketingStaff getMarketingStaff() {
		return marketingStaff;
	}

	public void setMarketingStaff(MarketingStaff marketingStaff) {
		this.marketingStaff = marketingStaff;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
