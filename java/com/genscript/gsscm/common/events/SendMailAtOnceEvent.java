package com.genscript.gsscm.common.events;

import org.springframework.context.ApplicationEvent;

public class SendMailAtOnceEvent extends ApplicationEvent {

	public SendMailAtOnceEvent(Object source) {
		super(source);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
