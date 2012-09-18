package com.genscript.gsscm.manufacture.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;


public class AntibodyOprExperimentDatasDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106759505998524519L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<Integer> id;
	private Integer woOperationId;
	private String experimentDataType;
	private String hostName;
	private Integer hostAmount;
	private List<String> hostNo;
	private List<String> experimentalResult;
	private List<BigDecimal> volume;
	private List<String> location;
	private List<String> remains;
	private List<String> remainsLocation;
	private List<String> comment;
	
	
	public List<Integer> getId() {
		return id;
	}


	public void setId(List<Integer> id) {
		this.id = id;
	}


	public Integer getWoOperationId() {
		return woOperationId;
	}


	public void setWoOperationId(Integer woOperationId) {
		this.woOperationId = woOperationId;
	}


	public String getExperimentDataType() {
		return experimentDataType;
	}


	public void setExperimentDataType(String experimentDataType) {
		this.experimentDataType = experimentDataType;
	}


	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public Integer getHostAmount() {
		return hostAmount;
	}


	public void setHostAmount(Integer hostAmount) {
		this.hostAmount = hostAmount;
	}


	public List<String> getHostNo() {
		return hostNo;
	}


	public void setHostNo(List<String> hostNo) {
		this.hostNo = hostNo;
	}


	public List<String> getExperimentalResult() {
		return experimentalResult;
	}


	public void setExperimentalResult(List<String> experimentalResult) {
		this.experimentalResult = experimentalResult;
	}


	public List<BigDecimal> getVolume() {
		return volume;
	}


	public void setVolume(List<BigDecimal> volume) {
		this.volume = volume;
	}


	public List<String> getLocation() {
		return location;
	}


	public void setLocation(List<String> location) {
		this.location = location;
	}


	public List<String> getRemains() {
		return remains;
	}


	public void setRemains(List<String> remains) {
		this.remains = remains;
	}


	public List<String> getRemainsLocation() {
		return remainsLocation;
	}


	public void setRemainsLocation(List<String> remainsLocation) {
		this.remainsLocation = remainsLocation;
	}


	public List<String> getComment() {
		return comment;
	}


	public void setComment(List<String> comment) {
		this.comment = comment;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	
}
