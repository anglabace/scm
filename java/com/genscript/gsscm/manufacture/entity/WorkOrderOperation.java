package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
import com.genscript.gsscm.common.util.DateUtils;

/**
 * @author wangsf
 * WorkorderOperation entity. 
 */
@Entity
@Table(name = "wo_operations", catalog = "manufacturing")
public class WorkOrderOperation extends BaseEntity  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4495118119349096834L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "order_no")
	private Integer workOrderNo;
	private Integer seqNo;
	@JoinColumn(name = "src_operation_id")
	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Operation operation;
	private Date exptdStartDate;
	private Date exptdEndDate;
	private Date actualStartDate;
	private Date actualEndDate;
	private Date customStartDate;
	private Date customEndDate;
	private Integer internalOrderNo;
	private String optionalFlag;
	private String status;
	@Column(name="comments")
	private String comment;
	@Transient
	private List<WoOperationResource> woResourceList;
	@Transient
	private List<Integer> delWoResIdList;
	@Transient
	private List<WoOperationComponent> woComponentList;
	@Transient
	private List<Integer> delWoComIdList;
	@Transient
	private Map<String,AntibodyOprExperimentDatas> antibodyOprExperimentDatasMap;
	@Transient
	private Map<String,AntibodyOprPurificationResults> antibodyOprPurificationResultsMap;
	@Transient
	private String delResultIds;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	// Constructors

	/** default constructor */
	public WorkOrderOperation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Date getExptdStartDate() {
		return this.exptdStartDate;
	}

	public void setExptdStartDate(Date exptdStartDate) {
		this.exptdStartDate = exptdStartDate;
		if(exptdStartDate!=null) {
			if(this.getOperation()==null) {
				this.exptdEndDate = exptdStartDate;
				return;
			}
			String uom = this.getOperation().getUom();
			if(StringUtils.isEmpty(uom)) {
				uom = "Hour";
			}
			long exptdStartTime = exptdStartDate.getTime();
			Date date = exptdStartDate;
			BigDecimal totalAdd = new BigDecimal(0);
			if(this.getOperation().getRunTime()!=null) {
				totalAdd = this.getOperation().getRunTime();
			}
			if(this.getOperation().getSetupTime()!=null) {
				totalAdd = totalAdd.add(new BigDecimal(this.getOperation().getSetupTime()));
			}
			if(totalAdd.equals(new BigDecimal(0))) {
				this.exptdEndDate = exptdStartDate;
				return;
			}
			long exptdEndTime = 0;
			if(uom.toLowerCase().startsWith("hour")) {
				exptdEndTime = exptdStartTime+totalAdd.multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			} else if(uom.toLowerCase().startsWith("day")) {
				exptdEndTime = exptdStartTime+totalAdd.multiply(new BigDecimal(24)).multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			} else if(uom.toLowerCase().startsWith("month")) {
				date = new java.sql.Date(DateUtils.defineMonthBefore2Date(exptdStartDate,totalAdd.intValue()).getTime());
				date = (date==null?exptdStartDate:date);
				exptdEndTime = date.getTime()+totalAdd.subtract(new BigDecimal(totalAdd.intValue())).multiply(new BigDecimal(24)).multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			} else if(uom.toLowerCase().startsWith("year")) {
				date = new java.sql.Date(DateUtils.defineMonthBefore2Date(exptdStartDate,totalAdd.multiply(new BigDecimal(12)).intValue()).getTime());
				date = (date==null?exptdStartDate:date);
				exptdEndTime = date.getTime()+totalAdd.multiply(new BigDecimal(12)).subtract(totalAdd.multiply(new BigDecimal(12))).multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			}
			this.exptdEndDate = date;
		}
		
	}

	public Date getExptdEndDate() {
		return this.exptdEndDate;
	}

	public void setExptdEndDate(Date exptdEndDate) {
		this.exptdEndDate = exptdEndDate;
	}

	public Date getActualStartDate() {
		return this.actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return this.actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Integer getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(Integer workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getOptionalFlag() {
		return optionalFlag;
	}

	public void setOptionalFlag(String optionalFlag) {
		this.optionalFlag = optionalFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Integer> getDelWoResIdList() {
		return delWoResIdList;
	}

	public void setDelWoResIdList(List<Integer> delWoResIdList) {
		this.delWoResIdList = delWoResIdList;
	}

	public List<WoOperationResource> getWoResourceList() {
		return woResourceList;
	}

	public void setWoResourceList(List<WoOperationResource> woResourceList) {
		this.woResourceList = woResourceList;
	}

	public List<WoOperationComponent> getWoComponentList() {
		return woComponentList;
	}

	public void setWoComponentList(List<WoOperationComponent> woComponentList) {
		this.woComponentList = woComponentList;
	}

	public List<Integer> getDelWoComIdList() {
		return delWoComIdList;
	}

	public void setDelWoComIdList(List<Integer> delWoComIdList) {
		this.delWoComIdList = delWoComIdList;
	}

	public Integer getInternalOrderNo() {
		return internalOrderNo;
	}

	public void setInternalOrderNo(Integer internalOrderNo) {
		this.internalOrderNo = internalOrderNo;
	}

	public Date getCustomStartDate() {
		return customStartDate;
	}

	public void setCustomStartDate(Date customStartDate) {
		this.customStartDate = customStartDate;
		if(customStartDate!=null) {
			if(this.getOperation()==null) {
				this.customEndDate = customStartDate;
				return;
			}
			String uom = this.getOperation().getUom();
			if(StringUtils.isEmpty(uom)) {
				uom = "Hour";
			}
			long exptdStartTime = customStartDate.getTime();
			Date date = customStartDate;
			BigDecimal totalAdd = new BigDecimal(0);
			if(this.getOperation().getRunTime()!=null) {
				totalAdd = this.getOperation().getRunTime();
			}
			if(this.getOperation().getSetupTime()!=null) {
				totalAdd = totalAdd.add(new BigDecimal(this.getOperation().getSetupTime()));
			}
			if(totalAdd.equals(new BigDecimal(0))) {
				this.customEndDate = customStartDate;
				return;
			}
			long exptdEndTime = 0;
			if(uom.toLowerCase().startsWith("hour")) {
				exptdEndTime = exptdStartTime+totalAdd.multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			} else if(uom.toLowerCase().startsWith("day")) {
				exptdEndTime = exptdStartTime+totalAdd.multiply(new BigDecimal(24)).multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			} else if(uom.toLowerCase().startsWith("month")) {
				date = new java.sql.Date(DateUtils.defineMonthBefore2Date(customStartDate,totalAdd.intValue()).getTime());
				date = (date==null?customStartDate:date);
				exptdEndTime = date.getTime()+totalAdd.subtract(new BigDecimal(totalAdd.intValue())).multiply(new BigDecimal(24)).multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			} else if(uom.toLowerCase().startsWith("year")) {
				date = new java.sql.Date(DateUtils.defineMonthBefore2Date(customStartDate,totalAdd.multiply(new BigDecimal(12)).intValue()).getTime());
				date = (date==null?customStartDate:date);
				exptdEndTime = date.getTime()+totalAdd.multiply(new BigDecimal(12)).subtract(totalAdd.multiply(new BigDecimal(12))).multiply(new BigDecimal(3600)).multiply(new BigDecimal(1000)).longValue();
				date = new Date(exptdEndTime);
			}
			this.customEndDate = date;
		}
	}

	public Date getCustomEndDate() {
		return customEndDate;
	}

	public void setCustomEndDate(Date customEndDate) {
		this.customEndDate = customEndDate;
	}


	public Map<String, AntibodyOprExperimentDatas> getAntibodyOprExperimentDatasMap() {
		return antibodyOprExperimentDatasMap;
	}

	public void setAntibodyOprExperimentDatasMap(
			Map<String, AntibodyOprExperimentDatas> antibodyOprExperimentDatasMap) {
		this.antibodyOprExperimentDatasMap = antibodyOprExperimentDatasMap;
	}

	public Map<String, AntibodyOprPurificationResults> getAntibodyOprPurificationResultsMap() {
		return antibodyOprPurificationResultsMap;
	}

	public void setAntibodyOprPurificationResultsMap(
			Map<String, AntibodyOprPurificationResults> antibodyOprPurificationResultsMap) {
		this.antibodyOprPurificationResultsMap = antibodyOprPurificationResultsMap;
	}

	public String getDelResultIds() {
		return delResultIds;
	}

	public void setDelResultIds(String delResultIds) {
		this.delResultIds = delResultIds;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}