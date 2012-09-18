package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "antibody_opr_experiment_datas", catalog = "manufacturing")
public class AntibodyOprExperimentDatas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1106759505998524519L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer woOperationId;
	private String experimentDataType;
	private String hostName;
	private Integer hostAmount;
	private String hostNo;
	private String experimentalResult;
	private BigDecimal volume;
	private String location;
	private String remains;
	private String remainsLocation;
	private String comment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getHostNo() {
		return hostNo;
	}
	public void setHostNo(String hostNo) {
		this.hostNo = hostNo;
	}
	public String getExperimentalResult() {
		return experimentalResult;
	}
	public void setExperimentalResult(String experimentalResult) {
		this.experimentalResult = experimentalResult;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRemains() {
		return remains;
	}
	public void setRemains(String remains) {
		this.remains = remains;
	}
	public String getRemainsLocation() {
		return remainsLocation;
	}
	public void setRemainsLocation(String remainsLocation) {
		this.remainsLocation = remainsLocation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	
}
