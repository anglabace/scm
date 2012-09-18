package com.genscript.gsscm.ws.request;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.application.entity.Application;

public class WsRequest {

	private Integer userId;
	private Application application;
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
