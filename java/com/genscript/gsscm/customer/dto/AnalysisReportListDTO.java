package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AnalysisReportListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895293423522523557L;
	private String DataFileName;
	private List<AnalysisReport> analysisReportList;
	public String getDataFileName() {
		return DataFileName;
	}
	public void setDataFileName(String dataFileName) {
		DataFileName = dataFileName;
	}
	public List<AnalysisReport> getAnalysisReportList() {
		return analysisReportList;
	}
	public void setAnalysisReportList(List<AnalysisReport> analysisReportList) {
		this.analysisReportList = analysisReportList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
