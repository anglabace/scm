package com.genscript.gsscm.serv.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.purchase.dto.VendorServiceDTO;
import com.genscript.gsscm.serv.entity.ServicePrice;
import com.genscript.gsscm.serv.entity.ServiceRestrictShip;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.serv.entity.ServiceSpecialPrice;
import com.genscript.gsscm.serv.entity.ServiceStorageCondition;
import com.genscript.gsscm.serv.entity.ServiceSubStepPrice;
import com.genscript.gsscm.serv.entity.ServiceSubSteps;

@XmlType(name = "ServDTO", namespace = WsConstants.NS)
public class ServiceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer serviceId;
	private String catalogNo;
	private String name;
	private String fullName;
	private Integer serviceClsId;
	private String shortDesc;
	private String longDesc;
	private String synonyms;
	private String qtyUom;
	private Double size;
	private String uom;
	private Double altSize;
	private String altUom;
	private Double length;
	private Double width;
	private Double height;
	private String 	dimUom;
	private String abst;
	private Integer inventoryId;
	private String purchasable;
	private String shippable;
	private String quotable;
	private String sellable;
	private String giftFlag;
	private String returnable;
	private String discountable;
	private String invoiceable;
	private String stockable;
	private String taxable;
	private Integer federalTaxCls;
	private Integer stateTaxCls;
	private Integer countryTaxCls;
	private String breakdownFlag;
	private String compositeFlag;
	private String documentFlag;
	private String lotControlFlag;
	private String serialControlFlag;
	private Integer versionNum;
	private String upcCode;
	private Integer prefWarehouse;
	private String altWarehouseFlag;
	private Integer prefStorage;
	private Integer saftyStock;
	private Integer minOrderQty;
	private BigDecimal unitCost;
	
	private BigDecimal sellingPriceCmsn;
	private BigDecimal grossProfitCmsn;
	private BigDecimal unitRateCmsn;
	
	private Integer returnPoints;
	private Integer priceByPoints;
	private String noticeSendType;
	private String noticeGenerateTime;
	private String customerInfo;
	private Integer leadTime;
	private String sellingNote;
	private String url;
	private String status;
	private Date creationDate;
	private Integer createdBy;
	private Date modifyDate;
	private Integer modifiedBy;
	private Date publishDate;
    private String vtRatio;
    private String btRatio;
	//以下为业务需要
	private String type;
	private String modifyByText;
	private String createdByText;
	private ServiceShipCondition serviceShipCondition;
	private ServiceStorageCondition serviceStorageCondition;
	private ServiceShipCondition shipCondition;
	private ServiceStorageCondition storageCondition;
	private List<ServiceIntermediateDTO> intmdList;
	private List<Integer> delIntmdIdList;
	private List<ServiceComponentDTO> componentList;
	private List<Integer> delComIdList;
	private List<ServiceRestrictShip> restrictShipList;
	private List<Integer> delRestrictShipIdList;
	private List<ServiceSpecialPrice> specialPriceList;
	private List<Integer> delSpecialPriceIdList;
	private List<VendorServiceDTO> vendorServiceList;
	private List<Integer> delVendorServiceIdList;
	private List<Integer> delSubStep;
	private List<ServiceSubSteps> subStepList;
	//Service的General Tab中Cross相关信息.
	private List<ServiceRelationDTO> servRelationList;
	//Product misc Tab的一些数据.
	private RoyaltyServiceDTO royaltyService;
	//ADDED BUSINESS PROPERTIES
	private String nameApprove;
	private String nameReason;
	private String statusApprove;
	private String statusReason;
	
	//service pricing
	private List<ServicePrice> servicePrice;
	private List<ServiceSubStepPrice> subStepPrice;
	private List<ServicePriceDTO> servicePriceApproveList;
	private List<ServicePriceDTO> serviceSubPriceApproveList;
	
	public ServiceDTO(){
		this.shipCondition = new ServiceShipCondition();
		this.storageCondition = new ServiceStorageCondition();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

    public String getVtRatio() {
        return vtRatio;
    }

    public void setVtRatio(String vtRatio) {
        this.vtRatio = vtRatio;
    }

    public String getBtRatio() {
        return btRatio;
    }

    public void setBtRatio(String btRatio) {
        this.btRatio = btRatio;
    }

    public String getNameApprove() {
		return nameApprove;
	}


	public void setNameApprove(String nameApprove) {
		this.nameApprove = nameApprove;
	}


	public String getNameReason() {
		return nameReason;
	}


	public void setNameReason(String nameReason) {
		this.nameReason = nameReason;
	}


	public String getStatusApprove() {
		return statusApprove;
	}


	public void setStatusApprove(String statusApprove) {
		this.statusApprove = statusApprove;
	}


	public String getStatusReason() {
		return statusReason;
	}


	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
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


	public List<ServiceRelationDTO> getServRelationList() {
		return servRelationList;
	}



	public void setServRelationList(List<ServiceRelationDTO> servRelationList) {
		this.servRelationList = servRelationList;
	}



	public RoyaltyServiceDTO getRoyaltyService() {
		return royaltyService;
	}

	public void setRoyaltyService(RoyaltyServiceDTO royaltyService) {
		this.royaltyService = royaltyService;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public ServiceShipCondition getShipCondition() {
		return shipCondition;
	}


	public void setShipCondition(ServiceShipCondition shipCondition) {
		this.shipCondition = shipCondition;
	}


	public ServiceStorageCondition getStorageCondition() {
		return storageCondition;
	}


	public void setStorageCondition(ServiceStorageCondition storageCondition) {
		this.storageCondition = storageCondition;
	}
	public List<ServiceIntermediateDTO> getIntmdList() {
		return intmdList;
	}

	public void setIntmdList(List<ServiceIntermediateDTO> intmdList) {
		this.intmdList = intmdList;
	}

	public List<Integer> getDelIntmdIdList() {
		return delIntmdIdList;
	}

	public void setDelIntmdIdList(List<Integer> delIntmdIdList) {
		this.delIntmdIdList = delIntmdIdList;
	}

	public List<ServiceComponentDTO> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<ServiceComponentDTO> componentList) {
		this.componentList = componentList;
	}

	public List<Integer> getDelComIdList() {
		return delComIdList;
	}

	public void setDelComIdList(List<Integer> delComIdList) {
		this.delComIdList = delComIdList;
	}

	public List<ServiceRestrictShip> getRestrictShipList() {
		return restrictShipList;
	}

	public void setRestrictShipList(List<ServiceRestrictShip> restrictShipList) {
		this.restrictShipList = restrictShipList;
	}

	public List<Integer> getDelRestrictShipIdList() {
		return delRestrictShipIdList;
	}

	public void setDelRestrictShipIdList(List<Integer> delRestrictShipIdList) {
		this.delRestrictShipIdList = delRestrictShipIdList;
	}
	public List<ServiceSpecialPrice> getSpecialPriceList() {
		return specialPriceList;
	}

	public void setSpecialPriceList(List<ServiceSpecialPrice> specialPriceList) {
		this.specialPriceList = specialPriceList;
	}

	public List<Integer> getDelSpecialPriceIdList() {
		return delSpecialPriceIdList;
	}

	public void setDelSpecialPriceIdList(List<Integer> delSpecialPriceIdList) {
		this.delSpecialPriceIdList = delSpecialPriceIdList;
	}

	public List<VendorServiceDTO> getVendorServiceList() {
		return vendorServiceList;
	}

	public void setVendorServiceList(List<VendorServiceDTO> vendorServiceList) {
		this.vendorServiceList = vendorServiceList;
	}

	public List<Integer> getDelVendorServiceIdList() {
		return delVendorServiceIdList;
	}

	public void setDelVendorServiceIdList(List<Integer> delVendorServiceIdList) {
		this.delVendorServiceIdList = delVendorServiceIdList;
	}

	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getServiceClsId() {
		return serviceClsId;
	}
	public void setServiceClsId(Integer serviceClsId) {
		this.serviceClsId = serviceClsId;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	public String getQtyUom() {
		return qtyUom;
	}
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Double getAltSize() {
		return altSize;
	}
	public void setAltSize(Double altSize) {
		this.altSize = altSize;
	}
	public String getAltUom() {
		return altUom;
	}
	public void setAltUom(String altUom) {
		this.altUom = altUom;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getDimUom() {
		return dimUom;
	}
	public void setDimUom(String dimUom) {
		this.dimUom = dimUom;
	}
	public String getAbst() {
		return abst;
	}
	public void setAbst(String abst) {
		this.abst = abst;
	}
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getPurchasable() {
		return purchasable;
	}
	public void setPurchasable(String purchasable) {
		this.purchasable = purchasable;
	}
	public String getShippable() {
		return shippable;
	}
	public void setShippable(String shippable) {
		this.shippable = shippable;
	}
	public String getQuotable() {
		return quotable;
	}
	public void setQuotable(String quotable) {
		this.quotable = quotable;
	}
	public String getSellable() {
		return sellable;
	}
	public void setSellable(String sellable) {
		this.sellable = sellable;
	}
	public String getGiftFlag() {
		return giftFlag;
	}
	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}
	public String getReturnable() {
		return returnable;
	}
	public void setReturnable(String returnable) {
		this.returnable = returnable;
	}
	public String getDiscountable() {
		return discountable;
	}
	public void setDiscountable(String discountable) {
		this.discountable = discountable;
	}
	public String getInvoiceable() {
		return invoiceable;
	}
	public void setInvoiceable(String invoiceable) {
		this.invoiceable = invoiceable;
	}
	public String getStockable() {
		return stockable;
	}
	public void setStockable(String stockable) {
		this.stockable = stockable;
	}
	public String getTaxable() {
		return taxable;
	}
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}
	public Integer getFederalTaxCls() {
		return federalTaxCls;
	}
	public void setFederalTaxCls(Integer federalTaxCls) {
		this.federalTaxCls = federalTaxCls;
	}
	public Integer getStateTaxCls() {
		return stateTaxCls;
	}
	public void setStateTaxCls(Integer stateTaxCls) {
		this.stateTaxCls = stateTaxCls;
	}
	public Integer getCountryTaxCls() {
		return countryTaxCls;
	}
	public void setCountryTaxCls(Integer countryTaxCls) {
		this.countryTaxCls = countryTaxCls;
	}
	public String getBreakdownFlag() {
		return breakdownFlag;
	}
	public void setBreakdownFlag(String breakdownFlag) {
		this.breakdownFlag = breakdownFlag;
	}
	public String getCompositeFlag() {
		return compositeFlag;
	}
	public void setCompositeFlag(String compositeFlag) {
		this.compositeFlag = compositeFlag;
	}
	public String getDocumentFlag() {
		return documentFlag;
	}
	public void setDocumentFlag(String documentFlag) {
		this.documentFlag = documentFlag;
	}
	public String getLotControlFlag() {
		return lotControlFlag;
	}
	public void setLotControlFlag(String lotControlFlag) {
		this.lotControlFlag = lotControlFlag;
	}
	public String getSerialControlFlag() {
		return serialControlFlag;
	}
	public void setSerialControlFlag(String serialControlFlag) {
		this.serialControlFlag = serialControlFlag;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	public String getUpcCode() {
		return upcCode;
	}
	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}
	public Integer getPrefWarehouse() {
		return prefWarehouse;
	}
	public void setPrefWarehouse(Integer prefWarehouse) {
		this.prefWarehouse = prefWarehouse;
	}
	public String getAltWarehouseFlag() {
		return altWarehouseFlag;
	}
	public void setAltWarehouseFlag(String altWarehouseFlag) {
		this.altWarehouseFlag = altWarehouseFlag;
	}
	public Integer getPrefStorage() {
		return prefStorage;
	}
	public void setPrefStorage(Integer prefStorage) {
		this.prefStorage = prefStorage;
	}
	public Integer getSaftyStock() {
		return saftyStock;
	}
	public void setSaftyStock(Integer saftyStock) {
		this.saftyStock = saftyStock;
	}
	public Integer getMinOrderQty() {
		return minOrderQty;
	}
	public void setMinOrderQty(Integer minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	public BigDecimal getSellingPriceCmsn() {
		return sellingPriceCmsn;
	}
	public void setSellingPriceCmsn(BigDecimal sellingPriceCmsn) {
		this.sellingPriceCmsn = sellingPriceCmsn;
	}
	public BigDecimal getGrossProfitCmsn() {
		return grossProfitCmsn;
	}
	public void setGrossProfitCmsn(BigDecimal grossProfitCmsn) {
		this.grossProfitCmsn = grossProfitCmsn;
	}
	public BigDecimal getUnitRateCmsn() {
		return unitRateCmsn;
	}
	public void setUnitRateCmsn(BigDecimal unitRateCmsn) {
		this.unitRateCmsn = unitRateCmsn;
	}
	public Integer getReturnPoints() {
		return returnPoints;
	}
	public void setReturnPoints(Integer returnPoints) {
		this.returnPoints = returnPoints;
	}
	public Integer getPriceByPoints() {
		return priceByPoints;
	}
	public void setPriceByPoints(Integer priceByPoints) {
		this.priceByPoints = priceByPoints;
	}
	public String getNoticeSendType() {
		return noticeSendType;
	}
	public void setNoticeSendType(String noticeSendType) {
		this.noticeSendType = noticeSendType;
	}
	public String getNoticeGenerateTime() {
		return noticeGenerateTime;
	}
	public void setNoticeGenerateTime(String noticeGenerateTime) {
		this.noticeGenerateTime = noticeGenerateTime;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public Integer getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}
	public String getSellingNote() {
		return sellingNote;
	}
	public void setSellingNote(String sellingNote) {
		this.sellingNote = sellingNote;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ServiceShipCondition getServiceShipCondition() {
		return serviceShipCondition;
	}
	public void setServiceShipCondition(ServiceShipCondition serviceShipCondition) {
		this.serviceShipCondition = serviceShipCondition;
	}
	
	public ServiceStorageCondition getServiceStorageCondition() {
		return serviceStorageCondition;
	}
	public void setServiceStorageCondition(
			ServiceStorageCondition serviceStorageCondition) {
		this.serviceStorageCondition = serviceStorageCondition;
	}
	public String getModifyByText() {
		return modifyByText;
	}
	public void setModifyByText(String modifyByText) {
		this.modifyByText = modifyByText;
	}
	public String getCreatedByText() {
		return createdByText;
	}
	public void setCreatedByText(String createdByText) {
		this.createdByText = createdByText;
	}

	public List<Integer> getDelSubStep() {
		return delSubStep;
	}

	public void setDelSubStep(List<Integer> delSubStep) {
		this.delSubStep = delSubStep;
	}

	public List<ServiceSubSteps> getSubStepList() {
		return subStepList;
	}

	public void setSubStepList(List<ServiceSubSteps> subStepList) {
		this.subStepList = subStepList;
	}

	public List<ServicePrice> getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(List<ServicePrice> servicePrice) {
		this.servicePrice = servicePrice;
	}

	public List<ServiceSubStepPrice> getSubStepPrice() {
		return subStepPrice;
	}

	public void setSubStepPrice(List<ServiceSubStepPrice> subStepPrice) {
		this.subStepPrice = subStepPrice;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ServicePriceDTO> getServicePriceApproveList() {
		return servicePriceApproveList;
	}

	public void setServicePriceApproveList(
			List<ServicePriceDTO> servicePriceApproveList) {
		this.servicePriceApproveList = servicePriceApproveList;
	}

	public List<ServicePriceDTO> getServiceSubPriceApproveList() {
		return serviceSubPriceApproveList;
	}

	public void setServiceSubPriceApproveList(
			List<ServicePriceDTO> serviceSubPriceApproveList) {
		this.serviceSubPriceApproveList = serviceSubPriceApproveList;
	}

	
}
