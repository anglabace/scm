package com.genscript.gsscm.serv.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;


@XmlType(name = "ServiceViewDTO", namespace = WsConstants.NS)
public class ServiceViewDTO {
	private String url;
	private String serviceName;
	private Long viewTimes;
	private String firstView;
	private String lastView;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the viewTimes
	 */
	public Long getViewTimes() {
		return viewTimes;
	}
	/**
	 * @param viewTimes the viewTimes to set
	 */
	public void setViewTimes(Long viewTimes) {
		this.viewTimes = viewTimes;
	}
	/**
	 * @return the firstView
	 */
	public String getFirstView() {
		return firstView;
	}
	/**
	 * @param firstView the firstView to set
	 */
	public void setFirstView(String firstView) {
		this.firstView = firstView;
	}
	/**
	 * @return the lastView
	 */
	public String getLastView() {
		return lastView;
	}
	/**
	 * @param lastView the lastView to set
	 */
	public void setLastView(String lastView) {
		this.lastView = lastView;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
