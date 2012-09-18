package com.genscript.gsscm.shipment.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ship_package_errors", catalog = "shipping")
public class ShipPackageErrors implements java.io.Serializable {


	@Id
	@Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer pkgLineId;
	private Integer packageId;
	private Integer missingQty;
	private Double missingSize;
	private String reason;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;


	public ShipPackageErrors() {
	}

	public ShipPackageErrors(Integer id, ShipPackageLines shipPackageLines,
			Date creationDate, Integer createdBy, Date modifyDate,
			Integer modifiedBy) {
		this.id = id;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
	}

	public ShipPackageErrors(Integer id, ShipPackageLines shipPackageLines,
			String trackingNo, Integer missingQty, Double missingSize,
			String reason, Date creationDate, Integer createdBy,
			Date modifyDate, Integer modifiedBy) {
		this.id = id;
		this.missingQty = missingQty;
		this.missingSize = missingSize;
		this.reason = reason;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.modifyDate = modifyDate;
		this.modifiedBy = modifiedBy;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "missing_qty")
	public Integer getMissingQty() {
		return this.missingQty;
	}

	public void setMissingQty(Integer missingQty) {
		this.missingQty = missingQty;
	}

	@Column(name = "missing_size", precision = 11, scale = 3)
	public Double getMissingSize() {
		return this.missingSize;
	}

	public void setMissingSize(Double missingSize) {
		this.missingSize = missingSize;
	}

	@Column(name = "reason")
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "creation_date", nullable = false, length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "created_by", nullable = false)
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modify_date", nullable = false, length = 19)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "modified_by", nullable = false)
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
    @Column(name="pkg_line_id")
	public Integer getPkgLineId() {
		return pkgLineId;
	}

	public void setPkgLineId(Integer pkgLineId) {
		this.pkgLineId = pkgLineId;
	}
	@Column(name = "package_id")
	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

}