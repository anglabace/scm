package com.genscript.gsscm.systemsetting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "bill_territory_zone", catalog = "common")
public class BillTerritoryZone extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "zone_id")
	private Integer zoneId;
	private Integer territoryId;
	private String continent;
	private String country;
	private String state;
	private String zipFrom;
	private String zipTo;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "territory_id")
	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	@Column(name = "continent")
	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "zip_from")
	public String getZipFrom() {
		return zipFrom;
	}

	public void setZipFrom(String zipFrom) {
		this.zipFrom = zipFrom;
	}

	@Column(name = "zip_to")
	public String getZipTo() {
		return zipTo;
	}

	public void setZipTo(String zipTo) {
		this.zipTo = zipTo;
	}

}
