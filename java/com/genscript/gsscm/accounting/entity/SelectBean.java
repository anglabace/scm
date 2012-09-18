package com.genscript.gsscm.accounting.entity;

/* * Created on 2010-11-17
 * by swg
 * for what
 */

import java.io.Serializable;

public class SelectBean implements Serializable{

	String key;
	String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
