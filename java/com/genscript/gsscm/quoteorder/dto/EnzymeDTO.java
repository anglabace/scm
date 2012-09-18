package com.genscript.gsscm.quoteorder.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

public class EnzymeDTO {

	private String name;
	private String sequence;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
