package com.genscript.gsscm.quoteorder.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "SearchORFCloneGeneDTO", namespace = WsConstants.NS)
public class SearchORFCloneGeneDTO {
	private String genscriptGene;
	private String name;
	private String refseqId;
	private String definition;
	private String geneFunction;
	private Double price;
	public String getGenscriptGene() {
		return genscriptGene;
	}
	public void setGenscriptGene(String genscriptGene) {
		this.genscriptGene = genscriptGene;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRefseqId() {
		return refseqId;
	}
	public void setRefseqId(String refseqId) {
		this.refseqId = refseqId;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getGeneFunction() {
		return geneFunction;
	}
	public void setGeneFunction(String geneFunction) {
		this.geneFunction = geneFunction;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
