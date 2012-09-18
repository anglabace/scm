package com.genscript.gsscm.order.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrderDnaSequencing
 * @author Zhang Yong
 */
@Entity
@Table(name = "order_dna_sequencing", catalog = "order")
public class OrderDnaSequencing extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1928699039469894254L;
	@Id
	private Integer 	orderItemId;
	private Integer 	orderNo;
	private Integer 	plateId;
	private String 		code;
	private String 		sampleName;
	private String 		sampleType;
	private String 		sampleConc;
	private Integer 	flagConcMeas; 
	private String 		primerType;
	private String 		primerName;
	private Integer 	flagPowerRead; 
	private Integer 	flagDataAnas;
	private String 		dataAnas;
	private String 		specialRequest;
	private Integer 	templateSize; 
	private String 		vectorName;
	private String 		primerConc;
	private String 		resistance;
	private String 		comment;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPlateId() {
		return plateId;
	}

	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getSampleConc() {
		return sampleConc;
	}

	public void setSampleConc(String sampleConc) {
		this.sampleConc = sampleConc;
	}

	public Integer getFlagConcMeas() {
		return flagConcMeas;
	}

	public void setFlagConcMeas(Integer flagConcMeas) {
		this.flagConcMeas = flagConcMeas;
	}

	public String getPrimerType() {
		return primerType;
	}

	public void setPrimerType(String primerType) {
		this.primerType = primerType;
	}

	public String getPrimerName() {
		return primerName;
	}

	public void setPrimerName(String primerName) {
		this.primerName = primerName;
	}

	public Integer getFlagPowerRead() {
		return flagPowerRead;
	}

	public void setFlagPowerRead(Integer flagPowerRead) {
		this.flagPowerRead = flagPowerRead;
	}

	public Integer getFlagDataAnas() {
		return flagDataAnas;
	}

	public void setFlagDataAnas(Integer flagDataAnas) {
		this.flagDataAnas = flagDataAnas;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public Integer getTemplateSize() {
		return templateSize;
	}

	public void setTemplateSize(Integer templateSize) {
		this.templateSize = templateSize;
	}

	public String getVectorName() {
		return vectorName;
	}

	public void setVectorName(String vectorName) {
		this.vectorName = vectorName;
	}

	public String getPrimerConc() {
		return primerConc;
	}

	public void setPrimerConc(String primerConc) {
		this.primerConc = primerConc;
	}

	public String getResistance() {
		return resistance;
	}

	public void setResistance(String resistance) {
		this.resistance = resistance;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDataAnas() {
		return dataAnas;
	}

	public void setDataAnas(String dataAnas) {
		this.dataAnas = dataAnas;
	}
}
