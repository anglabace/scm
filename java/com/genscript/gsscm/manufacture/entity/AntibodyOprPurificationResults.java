package com.genscript.gsscm.manufacture.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "antibody_opr_purification_results", catalog = "manufacturing")
public class AntibodyOprPurificationResults {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer refId;
	private Date experimentDate;
	private String hostNo;
	private String loading;
	private String remainsGel;
	private String concentration;
	private BigDecimal volume;
	private BigDecimal quantity;
	private String comment;
	private String titer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public Date getExperimentDate() {
		return experimentDate;
	}
	public void setExperimentDate(Date experimentDate) {
		this.experimentDate = experimentDate;
	}
	public String getHostNo() {
		return hostNo;
	}
	public void setHostNo(String hostNo) {
		this.hostNo = hostNo;
	}
	public String getLoading() {
		return loading;
	}
	public void setLoading(String loading) {
		this.loading = loading;
	}
	public String getRemainsGel() {
		return remainsGel;
	}
	public void setRemainsGel(String remainsGel) {
		this.remainsGel = remainsGel;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getTiter() {
		return titer;
	}
	public void setTiter(String titer) {
		this.titer = titer;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
