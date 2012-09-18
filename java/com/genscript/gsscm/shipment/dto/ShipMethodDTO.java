package com.genscript.gsscm.shipment.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipRateCustomerBasic;
import com.genscript.gsscm.shipment.entity.ShipRateTotalRange;
import com.genscript.gsscm.shipment.entity.ShipRateWeightRange;
import com.genscript.gsscm.shipment.entity.ShipZone;

@XmlType(name = "ShipMethodDTO", namespace = WsConstants.NS)
public class ShipMethodDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6395863223852670260L;
	private Integer methodId;
	private String methodCode;
	private String name;
	private String carrier;
	private Integer standardZone;
	private Integer standardRate;
	private String description;
	private String shippingAcount;
	private String insuranceBase;
	private String printFlag;
	private String externalFlag;
	private String trackUrl;
	private Integer cmprMethod1;
	private Integer cmprMethod2;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	
	//ADD
	private String modifiedByUser;
	private String standardZoneValue;
	private String standardRateValue;
	private ShipRateCustomerBasic shipRateCustomerBasic;
	private List<ShipRateTotalRange> shipRateTotalRangeList;
	private List<ShipRateWeightRange> shipRateWeightRangeList;
	private List<Integer> totalRangeDelIdList;
	private List<Integer> weightRangeDelIdList;
	private List<ShipZone> shipZoneList;
	private List<ShipRate> shipRateList;
	private String name_;

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Integer getStandardZone() {
		return standardZone;
	}

	public void setStandardZone(Integer standardZone) {
		this.standardZone = standardZone;
	}

	public Integer getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(Integer standardRate) {
		this.standardRate = standardRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShippingAcount() {
		return shippingAcount;
	}

	public void setShippingAcount(String shippingAcount) {
		this.shippingAcount = shippingAcount;
	}

	public String getInsuranceBase() {
		return insuranceBase;
	}

	public void setInsuranceBase(String insuranceBase) {
		this.insuranceBase = insuranceBase;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getExternalFlag() {
		return externalFlag;
	}

	public void setExternalFlag(String externalFlag) {
		this.externalFlag = externalFlag;
	}

	public String getTrackUrl() {
		return trackUrl;
	}

	public void setTrackUrl(String trackUrl) {
		this.trackUrl = trackUrl;
	}

	public Integer getCmprMethod1() {
		return cmprMethod1;
	}

	public void setCmprMethod1(Integer cmprMethod1) {
		this.cmprMethod1 = cmprMethod1;
	}

	public Integer getCmprMethod2() {
		return cmprMethod2;
	}

	public void setCmprMethod2(Integer cmprMethod2) {
		this.cmprMethod2 = cmprMethod2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}
	
	public ShipRateCustomerBasic getShipRateCustomerBasic() {
		return shipRateCustomerBasic;
	}

	public void setShipRateCustomerBasic(ShipRateCustomerBasic shipRateCustomerBasic) {
		this.shipRateCustomerBasic = shipRateCustomerBasic;
	}

	public List<ShipRateTotalRange> getShipRateTotalRangeList() {
		return shipRateTotalRangeList;
	}

	public void setShipRateTotalRangeList(
			List<ShipRateTotalRange> shipRateTotalRangeList) {
		this.shipRateTotalRangeList = shipRateTotalRangeList;
	}

	public List<ShipRateWeightRange> getShipRateWeightRangeList() {
		return shipRateWeightRangeList;
	}

	public void setShipRateWeightRangeList(
			List<ShipRateWeightRange> shipRateWeightRangeList) {
		this.shipRateWeightRangeList = shipRateWeightRangeList;
	}

	public List<Integer> getTotalRangeDelIdList() {
		return totalRangeDelIdList;
	}

	public void setTotalRangeDelIdList(List<Integer> totalRangeDelIdList) {
		this.totalRangeDelIdList = totalRangeDelIdList;
	}

	public List<Integer> getWeightRangeDelIdList() {
		return weightRangeDelIdList;
	}

	public void setWeightRangeDelIdList(List<Integer> weightRangeDelIdList) {
		this.weightRangeDelIdList = weightRangeDelIdList;
	}

	public String getStandardZoneValue() {
		return standardZoneValue;
	}

	public void setStandardZoneValue(String standardZoneValue) {
		this.standardZoneValue = standardZoneValue;
	}

	public String getStandardRateValue() {
		return standardRateValue;
	}

	public void setStandardRateValue(String standardRateValue) {
		this.standardRateValue = standardRateValue;
	}

	public List<ShipZone> getShipZoneList() {
		return shipZoneList;
	}

	public void setShipZoneList(List<ShipZone> shipZoneList) {
		this.shipZoneList = shipZoneList;
	}

	public List<ShipRate> getShipRateList() {
		return shipRateList;
	}

	public void setShipRateList(List<ShipRate> shipRateList) {
		this.shipRateList = shipRateList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}
}
