package com.genscript.gsscm.common.events;

import org.springframework.context.ApplicationEvent;

import com.genscript.gsscm.order.entity.OrderMain;

public class CancelOrderEvent extends ApplicationEvent {

	public CancelOrderEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5152685771320612277L;
	public CancelOrderEvent(Object source, OrderMain orderMain) {
		super(source);
		this.orderMain = orderMain;
	}
	private OrderMain orderMain;
	public OrderMain getOrderMain() {
		return orderMain;
	}
}
