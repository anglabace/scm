package com.genscript.gsscm.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ds_price
 * @author Zhang Yong
 * add date 2011-11-08
 *
 */
@Entity
@Table(name = "ds_price", catalog="product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsPrice {
	@Id
	private Integer id;
	private String 	sampleType;
	private String 	primerType;
	private Integer	start;
	private Integer end;
	private Float	price;
	private Float	analysis;
	private Float	concentration;
	private Float	powerRead;
	private Float	shiping;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSampleType() {
		return sampleType;
	}
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	public String getPrimerType() {
		return primerType;
	}
	public void setPrimerType(String primerType) {
		this.primerType = primerType;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getAnalysis() {
		return analysis;
	}
	public void setAnalysis(Float analysis) {
		this.analysis = analysis;
	}
	public Float getConcentration() {
		return concentration;
	}
	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}
	public Float getPowerRead() {
		return powerRead;
	}
	public void setPowerRead(Float powerRead) {
		this.powerRead = powerRead;
	}
	public Float getShiping() {
		return shiping;
	}
	public void setShiping(Float shiping) {
		this.shiping = shiping;
	}
}
