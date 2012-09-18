package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "wo_operation_resources", catalog = "manufacturing")
public class WoOperationResource extends BaseEntity implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016643701390627231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer woOperationId;
	@Column(name="sequence_no")
	private Integer seqNo;
	@JoinColumn(name = "resourceId", referencedColumnName = "resourceId")
	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Resource resource;
	private String location;
	private Integer perItemQty;
	private Integer qtyIssued;
	private Integer qtyUsed;
	private String status;
	private Date scheduleStartDate;
	private Date scheduleCompleteDate;
	private Date actualStartDate;
	private Date actualCompleteDate;
	@Transient
	private String modifyUser;
	
	public Object clone() {
		WoOperationResource cloneTemp = null;
		try {
			cloneTemp = (WoOperationResource) super.clone();
            if (this.resource != null) {
            	cloneTemp.resource = (Resource) this.resource.clone();
            }
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloneTemp;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

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

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPerItemQty() {
		return perItemQty;
	}

	public void setPerItemQty(Integer perItemQty) {
		this.perItemQty = perItemQty;
	}

	public Integer getQtyIssued() {
		return qtyIssued;
	}

	public void setQtyIssued(Integer qtyIssued) {
		this.qtyIssued = qtyIssued;
	}

	public Integer getQtyUsed() {
		return qtyUsed;
	}

	public void setQtyUsed(Integer qtyUsed) {
		this.qtyUsed = qtyUsed;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getScheduleCompleteDate() {
		return scheduleCompleteDate;
	}

	public void setScheduleCompleteDate(Date scheduleCompleteDate) {
		this.scheduleCompleteDate = scheduleCompleteDate;
	}

	public Date getActualCompleteDate() {
		return actualCompleteDate;
	}

	public void setActualCompleteDate(Date actualCompleteDate) {
		this.actualCompleteDate = actualCompleteDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}


}
