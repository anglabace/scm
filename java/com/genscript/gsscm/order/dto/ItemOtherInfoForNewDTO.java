package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ItemOtherInfoForNewDTO", namespace = WsConstants.NS)
public class ItemOtherInfoForNewDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8883030210139231738L;
	private String itemStatus;
	private String itemStatusText;
	private String otherInfo;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the itemStatus
	 */
	public String getItemStatus() {
		return itemStatus;
	}

	/**
	 * @param itemStatus
	 *            the itemStatus to set
	 */
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	/**
	 * @return the itemStatusText
	 */
	public String getItemStatusText() {
		return itemStatusText;
	}

	/**
	 * @param itemStatusText
	 *            the itemStatusText to set
	 */
	public void setItemStatusText(String itemStatusText) {
		this.itemStatusText = itemStatusText;
	}

	/**
	 * @return the otherInfo
	 */
	public String getOtherInfo() {
		return otherInfo;
	}

	/**
	 * @param otherInfo
	 *            the otherInfo to set
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
}
