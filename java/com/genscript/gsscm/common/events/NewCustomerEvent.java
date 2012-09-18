package com.genscript.gsscm.common.events;

import org.springframework.context.ApplicationEvent;

import com.genscript.gsscm.customer.dto.CustomerDTO;

public class NewCustomerEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4860312011162896286L;
	private CustomerDTO customerDTO;
	private String custNoStr;
	public NewCustomerEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public NewCustomerEvent(Object source, CustomerDTO customerDTO, String custNoStr) {
		super(source);
		this.custNoStr = custNoStr;
		this.customerDTO = customerDTO;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public String getCustNoStr() {
		return custNoStr;
	}
	
}
