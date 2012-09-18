package com.genscript.gsscm.manufacture.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.gsscm.manufacture.entity.WorkOrder;


@Entity
@Table(name = "wo_batch_detail", catalog = "manufacturing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WoBatchDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7937965669514561623L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_no")
	private WorkOrder workOrders;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "wo_batch_id")
	private WoBatche woBatche;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WorkOrder getWorkOrders() {
		return workOrders;
	}
	public void setWorkOrders(WorkOrder workOrders) {
		this.workOrders = workOrders;
	}
	public WoBatche getWoBatches() {
		return woBatche;
	}
	public void setWoBatches(WoBatche woBatche) {
		this.woBatche = woBatche;
	}


}