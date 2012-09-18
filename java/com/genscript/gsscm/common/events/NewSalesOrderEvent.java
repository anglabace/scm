package com.genscript.gsscm.common.events;

import org.springframework.context.ApplicationEvent;

import com.genscript.gsscm.order.entity.OrderMain;

public class NewSalesOrderEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderMain orderMain;
	public NewSalesOrderEvent(Object source) {
		super(source);
	}

	public NewSalesOrderEvent(Object source, OrderMain orderMain) {
		super(source);
		this.orderMain = orderMain;
	}

	public OrderMain getOrderMain() {
		return orderMain;
	}

}
