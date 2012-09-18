package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * WoBatchErrors entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wo_batch_errors", catalog = "manufacturing")
public class WoBatchError extends BaseEntity  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5644355512677618904L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_no")
	private WorkOrder workOrder;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wo_batch_id")
	private WoBatche woBatche;
	private Integer missingQty;
	private Double missingSize;
	private String reason;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	// Constructors

	/** default constructor */
	public WoBatchError() {
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public WorkOrder getWorkOrder() {
		return this.workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}


	public WoBatche getWoBatches() {
		return this.woBatche;
	}

	public void setWoBatches(WoBatche woBatche) {
		this.woBatche = woBatche;
	}

	public Integer getMissingQty() {
		return this.missingQty;
	}

	public void setMissingQty(Integer missingQty) {
		this.missingQty = missingQty;
	}

	public Double getMissingSize() {
		return this.missingSize;
	}

	public void setMissingSize(Double missingSize) {
		this.missingSize = missingSize;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}