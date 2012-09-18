package com.genscript.gsscm.manufacture.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;
import com.genscript.gsscm.order.entity.OrderDnaSequencing;
@Entity
@Table(name = "ds_plate_items", catalog = "manufacturing")
public class DsPlateItems extends BaseEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5543674335265471484L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer workOrderNo;
	private String sampleBarcode;
	private String plateNo;
	private String platePosition;
	private Date receivedDate;
	private String concerntrationValue;
	private Integer plateEnd;
	private Integer fileAnalysisFlag;
	@Transient
	private Integer orderNo;
	@Transient
	private Integer itemNo;
	@Transient
	private OrderDnaSequencing orderDnaSequencing;
	@Transient
	private Map<String,ManuDocument> sequenceFileMap;
	@Transient
	private Map<String,ManuDocument> traceFileMap;
	@Transient
	private Map<String,ManuDocument> qvDataMap;
	@Transient
	private Map<String,ManuDocument>  alignmentFileMap;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWorkOrderNo() {
		return workOrderNo;
	}
	public void setWorkOrderNo(Integer workOrderNo) {
		this.workOrderNo = workOrderNo;
	}
	public String getSampleBarcode() {
		return sampleBarcode;
	}
	public void setSampleBarcode(String sampleBarcode) {
		this.sampleBarcode = sampleBarcode;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getPlatePosition() {
		return platePosition;
	}
	public void setPlatePosition(String platePosition) {
		this.platePosition = platePosition;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getConcerntrationValue() {
		return concerntrationValue;
	}
	public void setConcerntrationValue(String concerntrationValue) {
		this.concerntrationValue = concerntrationValue;
	}
	public Integer getPlateEnd() {
		return plateEnd;
	}
	public void setPlateEnd(Integer plateEnd) {
		this.plateEnd = plateEnd;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public OrderDnaSequencing getOrderDnaSequencing() {
		return orderDnaSequencing;
	}
	public void setOrderDnaSequencing(OrderDnaSequencing orderDnaSequencing) {
		this.orderDnaSequencing = orderDnaSequencing;
	}
	
	
	public Map<String, ManuDocument> getSequenceFileMap() {
		return sequenceFileMap;
	}
	public void setSequenceFileMap(Map<String, ManuDocument> sequenceFileMap) {
		this.sequenceFileMap = sequenceFileMap;
	}
	public Map<String, ManuDocument> getTraceFileMap() {
		return traceFileMap;
	}
	public void setTraceFileMap(Map<String, ManuDocument> traceFileMap) {
		this.traceFileMap = traceFileMap;
	}
	public Map<String, ManuDocument> getQvDataMap() {
		return qvDataMap;
	}
	public void setQvDataMap(Map<String, ManuDocument> qvDataMap) {
		this.qvDataMap = qvDataMap;
	}
	public Map<String, ManuDocument> getAlignmentFileMap() {
		return alignmentFileMap;
	}
	public void setAlignmentFileMap(Map<String, ManuDocument> alignmentFileMap) {
		this.alignmentFileMap = alignmentFileMap;
	}
	public Integer getFileAnalysisFlag() {
		return fileAnalysisFlag;
	}
	public void setFileAnalysisFlag(Integer fileAnalysisFlag) {
		this.fileAnalysisFlag = fileAnalysisFlag;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
