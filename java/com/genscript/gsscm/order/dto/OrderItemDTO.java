package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.CustomServiceDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutationLibrariesDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.PkgServiceDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;

@XmlType(name = "OrderItemDTO", namespace = WsConstants.NS)
public class OrderItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5655236840132703484L;
	private Integer orderItemId;
	private Integer orderNo;
	private Integer itemNo;
	private String type;
	private Integer clsId;
	private String catalogNo;
	private String catalogId;
	private String name;
	private String nameShow;
	private String shortDesc;
	private String longDesc;
	private String taxStatus;
	private Integer shipSchedule;
	private Integer quantity;
	private String qtyUom;
	private Double size;
	private String sizeUom;
	private BigDecimal unitPrice;
	private String priceChanged;
	private String tbdFlag;
	private BigDecimal amount;
	private BigDecimal tax;
	private BigDecimal discount;
	private Integer billtoAddrId;
	private Integer shiptoAddrId;
	private Integer soldtoAddrId;
	private Integer shipMethod;
	private String trackingNo;
	private String parentId;
	private String preParentId;
	private Integer storageId;
	private String comment;
	private String sellingNote;
	private String status;
	private String statusReason;
	private BigDecimal basePrice;
    private BigDecimal cost;
    private Date targetDate;
	//add by lizhang
	private Integer projectManager;
	private Integer altProjectManager;
	// 非数据库
	private OrderAddress shipToAddress;
	private String otherInfo;
	private String statusText;
	private String catalogText;
	private String typeText;
	private String upSymbol;
	private Integer upPrecision;
	private String srcItemNo;
	// 测试用:
	private Integer shippedQty;
	private BigDecimal shipSize;
	private String shipUom;
	// 用来设置状态历史的note信息.
	private String statusNote;
	// 非数据库
	private String packageType;
	private Date shipDate;
	private List<OrderItemDTO> subItemList;
	private OrderGeneSynthesisDTO geneSynthesis;
	private OrderCustCloningDTO custCloning;
	private OrderPlasmidPreparationDTO plasmidPreparation;
	private OrderOrfCloneDTO orfClone;
	private OrderMutagenesisDTO mutagenesis;
	private OrderOligoDTO oligo;
	private DnaSequencingDTO dnaSequencing;
	private PeptideDTO peptide;
	private PkgServiceDTO orderPkgService;
	private AntibodyDTO antibody;
	private RnaDTO rna;
	private CustomServiceDTO customService;
	private OrderMutationLibrariesDTO mutationLibraries;
	// add by jarvinia
	private Integer warehouseId;
	private String statusUpdReason;
	private String relationType;
	private Date orderDate;
	private Date exprDate;
	private Integer salesContact;
	private String salesContactName;
	private String warehouseName;
	private Integer poOrderNo;
	private String vendorName;
	private String orderType;
	private String currencyCode;
	private Integer shippmentId;
	private String templateType;
	private String shippingRule;
	private String shippingRoute;
	//add by lizhang
	private String sessionItemId;
	//add by golf
	private String changeStatus;
	private String vtRatioStr;
	private String virusSeqFlag;
	//add by Zhang Yong
	private String newScheduleShipment;
	private String reason;
	private String usPoNo;
    //add by zhanghuibin
    private String updateFlag; //是否更新操作
    private String reasonNote;
    private boolean mailFlag;

    public boolean isMailFlag() {
        return mailFlag;
    }

    public void setMailFlag(boolean mailFlag) {
        this.mailFlag = mailFlag;
    }

    public String getReasonNote() {
        return reasonNote;
    }

    public void setReasonNote(String reasonNote) {
        this.reasonNote = reasonNote;
    }

    private Double temperature;//shipping pack（）时候用。

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getStatusUpdReason() {
		return statusUpdReason;
	}

	public void setStatusUpdReason(String statusUpdReason) {
		this.statusUpdReason = statusUpdReason;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getExprDate() {
		return exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public Integer getSalesContact() {
		return salesContact;
	}

	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Integer getPoOrderNo() {
		return poOrderNo;
	}

	public void setPoOrderNo(Integer poOrderNo) {
		this.poOrderNo = poOrderNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getClsId() {
		return clsId;
	}

	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public Integer getShipSchedule() {
		return shipSchedule;
	}

	public void setShipSchedule(Integer shipSchedule) {
		this.shipSchedule = shipSchedule;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getSizeUom() {
		return sizeUom;
	}

	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}

	public BigDecimal getUnitPrice() {
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if(unitPrice == null) {
			unitPrice = new BigDecimal(0);
		}
		if (isJpy) {
			return unitPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			return unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		if(unitPrice == null){
			this.unitPrice = new BigDecimal(0);
		}
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if (isJpy) {
			this.unitPrice = unitPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			this.unitPrice = unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public BigDecimal getAmount() {
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if(amount == null) {
			amount = new BigDecimal(0);
		}
		if (isJpy) {
			return amount.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public void setAmount(BigDecimal amount) {
		if(amount == null){
			this.amount = new BigDecimal(0);
		}
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if (isJpy) {
			this.amount = amount.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public BigDecimal getTax() {
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if(tax == null) {
			tax = new BigDecimal(0);
		}
		if (isJpy) {
			return tax.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			return tax.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public void setTax(BigDecimal tax) {
		if(tax == null){
			this.tax = new BigDecimal(0);
		}
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if (isJpy) {
			this.tax = tax.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			this.tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public BigDecimal getDiscount() {
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if(discount == null) {
			discount = new BigDecimal(0);
		}
		if (isJpy) {
			return discount.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			return discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public void setDiscount(BigDecimal discount) {
		if(discount == null){
			this.discount = new BigDecimal(0);
		}
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if (isJpy) {
			this.discount = discount.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			this.discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public BigDecimal getCost() {
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if(cost == null) {
			cost = new BigDecimal(0);
		}
		if (isJpy) {
			return cost.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			return cost.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public void setCost(BigDecimal cost) {
		if(cost == null){
			this.cost = new BigDecimal(0);
		}
		boolean isJpy = currencyCode != null && ("JPY").equals(currencyCode)?true:false;
		if (isJpy) {
			this.cost = cost.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			this.cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	public Integer getBilltoAddrId() {
		return billtoAddrId;
	}

	public void setBilltoAddrId(Integer billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}

	public Integer getShiptoAddrId() {
		return shiptoAddrId;
	}

	public void setShiptoAddrId(Integer shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}

	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}

	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}

	public Integer getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public OrderAddress getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(OrderAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getCatalogText() {
		return catalogText;
	}

	public void setCatalogText(String catalogText) {
		this.catalogText = catalogText;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	public String getUpSymbol() {
		return upSymbol;
	}

	public void setUpSymbol(String upSymbol) {
		this.upSymbol = upSymbol;
	}

	public Integer getUpPrecision() {
		return upPrecision;
	}

	public void setUpPrecision(Integer upPrecision) {
		this.upPrecision = upPrecision;
	}

	public Integer getShippedQty() {
		return shippedQty;
	}

	public void setShippedQty(Integer shippedQty) {
		this.shippedQty = shippedQty;
	}

	public BigDecimal getShipSize() {
		return shipSize;
	}

	public void setShipSize(BigDecimal shipSize) {
		this.shipSize = shipSize;
	}

	public String getShipUom() {
		return shipUom;
	}

	public void setShipUom(String shipUom) {
		this.shipUom = shipUom;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public List<OrderItemDTO> getSubItemList() {
		return subItemList;
	}

	public void setSubItemList(List<OrderItemDTO> subItemList) {
		this.subItemList = subItemList;
	}

	public OrderGeneSynthesisDTO getGeneSynthesis() {
		return geneSynthesis;
	}

	public void setGeneSynthesis(OrderGeneSynthesisDTO geneSynthesis) {
		this.geneSynthesis = geneSynthesis;
	}

	public OrderCustCloningDTO getCustCloning() {
		return custCloning;
	}

	public void setCustCloning(OrderCustCloningDTO custCloning) {
		this.custCloning = custCloning;
	}

	public OrderPlasmidPreparationDTO getPlasmidPreparation() {
		return plasmidPreparation;
	}

	public void setPlasmidPreparation(OrderPlasmidPreparationDTO plasmidPreparation) {
		this.plasmidPreparation = plasmidPreparation;
	}

	public OrderOrfCloneDTO getOrfClone() {
		return orfClone;
	}

	public void setOrfClone(OrderOrfCloneDTO orfClone) {
		this.orfClone = orfClone;
	}

	public OrderMutagenesisDTO getMutagenesis() {
		return mutagenesis;
	}

	public void setMutagenesis(OrderMutagenesisDTO mutagenesis) {
		this.mutagenesis = mutagenesis;
	}

	public PeptideDTO getPeptide() {
		return peptide;
	}

	public void setPeptide(PeptideDTO peptide) {
		this.peptide = peptide;
	}

	public PkgServiceDTO getOrderPkgService() {
		return orderPkgService;
	}

	public void setOrderPkgService(PkgServiceDTO orderPkgService) {
		this.orderPkgService = orderPkgService;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public AntibodyDTO getAntibody() {
		return antibody;
	}

	public void setAntibody(AntibodyDTO antibody) {
		this.antibody = antibody;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getPriceChanged() {
		return priceChanged;
	}

	public void setPriceChanged(String priceChanged) {
		this.priceChanged = priceChanged;
	}

	public RnaDTO getRna() {
		return rna;
	}

	public void setRna(RnaDTO rna) {
		this.rna = rna;
	}

	public String getSrcItemNo() {
		return srcItemNo;
	}

	public void setSrcItemNo(String srcItemNo) {
		this.srcItemNo = srcItemNo;
	}

	public String getTbdFlag() {
		return tbdFlag;
	}

	public void setTbdFlag(String tbdFlag) {
		this.tbdFlag = tbdFlag;
	}

	public OrderOligoDTO getOligo() {
		return oligo;
	}

	public void setOligo(OrderOligoDTO oligo) {
		this.oligo = oligo;
	}

	public Integer getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Integer projectManager) {
		this.projectManager = projectManager;
	}

	public Integer getAltProjectManager() {
		return altProjectManager;
	}

	public void setAltProjectManager(Integer altProjectManager) {
		this.altProjectManager = altProjectManager;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getNameShow() {
		return nameShow;
	}

	public void setNameShow(String nameShow) {
		this.nameShow = nameShow;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public Integer getShippmentId() {
		return shippmentId;
	}

	public void setShippmentId(Integer shippmentId) {
		this.shippmentId = shippmentId;
	}

	public String getShippingRule() {
		return shippingRule;
	}

	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}

	public CustomServiceDTO getCustomService() {
		return customService;
	}

	public void setCustomService(CustomServiceDTO customService) {
		this.customService = customService;
	}

	public String getPreParentId() {
		return preParentId;
	}

	public void setPreParentId(String preParentId) {
		this.preParentId = preParentId;
	}

	public String getSessionItemId() {
		return sessionItemId;
	}

	public void setSessionItemId(String sessionItemId) {
		this.sessionItemId = sessionItemId;
	}

	public String getSalesContactName() {
		return salesContactName;
	}

	public void setSalesContactName(String salesContactName) {
		this.salesContactName = salesContactName;
	}

	public String getShippingRoute() {
		return shippingRoute;
	}

	public void setShippingRoute(String shippingRoute) {
		this.shippingRoute = shippingRoute;
	}

	public String getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}

	public String getVtRatioStr() {
		return vtRatioStr;
	}

	public void setVtRatioStr(String vtRatioStr) {
		this.vtRatioStr = vtRatioStr;
	}

	public String getVirusSeqFlag() {
		return virusSeqFlag;
	}

	public void setVirusSeqFlag(String virusSeqFlag) {
		this.virusSeqFlag = virusSeqFlag;
	}

	public String getNewScheduleShipment() {
		return newScheduleShipment;
	}

	public void setNewScheduleShipment(String newScheduleShipment) {
		this.newScheduleShipment = newScheduleShipment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUsPoNo() {
		return usPoNo;
	}

	public void setUsPoNo(String usPoNo) {
		this.usPoNo = usPoNo;
	}

	public DnaSequencingDTO getDnaSequencing() {
		return dnaSequencing;
	}

	public void setDnaSequencing(DnaSequencingDTO dnaSequencing) {
		this.dnaSequencing = dnaSequencing;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public OrderMutationLibrariesDTO getMutationLibraries() {
		return mutationLibraries;
	}

	public void setMutationLibraries(OrderMutationLibrariesDTO mutationLibraries) {
		this.mutationLibraries = mutationLibraries;
	}
	
	
}
