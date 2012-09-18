package com.genscript.gsscm.common;

import java.io.Serializable;

import com.genscript.gsscm.common.constant.OperationType;

public class SessionBaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2771780969170910010L;
	protected OperationType operationType;

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	
}
