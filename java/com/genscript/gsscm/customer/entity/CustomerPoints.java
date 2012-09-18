package com.genscript.gsscm.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DIVISIONS.
 * 
 * 
 * @author Golf
 */
@Entity
@Table(name = "customer_points")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerPoints implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 985134634326832438L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "division_id")
//	private Integer divisionId;
	@Id
	private Integer custNo;
	private Integer pointsHistory;
	private Integer pointsAvailable;
	private Integer pointsUsed;
	
	@Column(insertable = true, updatable = false)
	private Date creationDate;
	@Column(insertable = true, updatable = false)
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}

	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	/**
	 * @return the pointsHistory
	 */
	public Integer getPointsHistory() {
		return pointsHistory;
	}

	/**
	 * @param pointsHistory the pointsHistory to set
	 */
	public void setPointsHistory(Integer pointsHistory) {
		this.pointsHistory = pointsHistory;
	}

	/**
	 * @return the pointsAvailable
	 */
	public Integer getPointsAvailable() {
		return pointsAvailable;
	}

	/**
	 * @param pointsAvailable the pointsAvailable to set
	 */
	public void setPointsAvailable(Integer pointsAvailable) {
		this.pointsAvailable = pointsAvailable;
	}

	/**
	 * @return the pointsUsed
	 */
	public Integer getPointsUsed() {
		return pointsUsed;
	}

	/**
	 * @param pointsUsed the pointsUsed to set
	 */
	public void setPointsUsed(Integer pointsUsed) {
		this.pointsUsed = pointsUsed;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
