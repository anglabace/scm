package com.genscript.gsscm.common.events;

import org.springframework.context.ApplicationEvent;

import com.genscript.gsscm.product.dto.ProductDTO;

public class NewPartEvent extends ApplicationEvent {
	private ProductDTO productDTO;
	public NewPartEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public NewPartEvent(Object source, ProductDTO productDTO) {
		super(source);
		this.productDTO = productDTO;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2912934818825959986L;
	public ProductDTO getProductDTO() {
		return productDTO;
	}

}
