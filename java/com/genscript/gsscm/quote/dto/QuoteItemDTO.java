package com.genscript.gsscm.quote.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.quote.entity.QuoteAddress;
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


@XmlType(name = "QuoteItemDTO", namespace = WsConstants.NS)
public class QuoteItemDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
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
	private String itemStatus;
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
	private String parentId;
	private String preParentId;
	private Integer warehouseId;
	private Integer storageId;
	private String comment;
	private String sellingNote;
	private String status;
	private String statusReason;
	private BigDecimal basePrice;
    private BigDecimal cost;
	//add by lizhang
	private Integer projectManager;
	private Integer altProjectManager;
	// 非数据库
	private QuoteAddress shipToAddress;
	private String otherInfo;
	private String statusText;
	private String catalogText;
	private String typeText;
	private String upSymbol;
	private Integer upPrecision;
	private String srcItemNo;
	//用来设置状态历史的note信息.
	private String statusNote;
	//非数据库
	private String packageType;
	private List<QuoteItemDTO> subItemList;
	private OrderGeneSynthesisDTO geneSynthesis;
	private OrderCustCloningDTO custCloning;
	private OrderPlasmidPreparationDTO plasmidPreparation;
	private OrderOrfCloneDTO orfClone;
	private OrderOligoDTO oligo;
	private OrderMutagenesisDTO mutagenesis;
	private PeptideDTO peptide;
	private PkgServiceDTO quotePkgService;
	private AntibodyDTO antibody;
	private RnaDTO rna;
	private CustomServiceDTO customService;
	private DnaSequencingDTO dnaSequencing;
	private OrderMutationLibrariesDTO mutationLibraries;
	private String currencyCode;
	private String templateType;
	//add by lizhang
	private String sessionItemId;
	private String shippingRoute;
	private String virusSeqFlag;
	//add by Zhang Yong
	private String newScheduleShipment;
	private String reason;
    //add by zhanghuibin
    private String updateFlag;
    //用于发送邮件的标识，
    private boolean mailFlag;

    public boolean isMailFlag() {
        return mailFlag;
    }

    public void setMailFlag(boolean mailFlag) {
        this.mailFlag = mailFlag;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	/**
	 * @return the quoteNo
	 */
	public Integer getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	/**
	 * @return the itemNo
	 */
	public Integer getItemNo() {
		return itemNo;
	}
	/**
	 * @param itemNo the itemNo to set
	 */
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the clsId
	 */
	public Integer getClsId() {
		return clsId;
	}
	/**
	 * @param clsId the clsId to set
	 */
	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	/**
	 * @return the catalogId
	 */
	public String getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
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
	/**
	 * @return the taxStatus
	 */
	public String getTaxStatus() {
		return taxStatus;
	}
	/**
	 * @param taxStatus the taxStatus to set
	 */
	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}
	/**
	 * @return the shipSchedule
	 */
	public Integer getShipSchedule() {
		return shipSchedule;
	}
	/**
	 * @param shipSchedule the shipSchedule to set
	 */
	public void setShipSchedule(Integer shipSchedule) {
		this.shipSchedule = shipSchedule;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the qtyUom
	 */
	public String getQtyUom() {
		return qtyUom;
	}
	/**
	 * @param qtyUom the qtyUom to set
	 */
	public void setQtyUom(String qtyUom) {
		this.qtyUom = qtyUom;
	}
	/**
	 * @return the size
	 */
	public Double getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Double size) {
		this.size = size;
	}
	/**
	 * @return the sizeUom
	 */
	public String getSizeUom() {
		return sizeUom;
	}
	/**
	 * @param sizeUom the sizeUom to set
	 */
	public void setSizeUom(String sizeUom) {
		this.sizeUom = sizeUom;
	}
	/**
	 * @return the unitPrice
	 */
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
	/**
	 * @return the billtoAddrId
	 */
	public Integer getBilltoAddrId() {
		return billtoAddrId;
	}
	/**
	 * @param billtoAddrId the billtoAddrId to set
	 */
	public void setBilltoAddrId(Integer billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}
	/**
	 * @return the shiptoAddrId
	 */
	public Integer getShiptoAddrId() {
		return shiptoAddrId;
	}
	/**
	 * @param shiptoAddrId the shiptoAddrId to set
	 */
	public void setShiptoAddrId(Integer shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}
	/**
	 * @return the soldtoAddrId
	 */
	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}
	/**
	 * @param soldtoAddrId the soldtoAddrId to set
	 */
	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}
	/**
	 * @return the shipMethod
	 */
	public Integer getShipMethod() {
		return shipMethod;
	}
	/**
	 * @param shipMethod the shipMethod to set
	 */
	public void setShipMethod(Integer shipMethod) {
		this.shipMethod = shipMethod;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}
	/**
	 * @param statusReason the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	/**
	 * @return the shipToAddress
	 */
	public QuoteAddress getShipToAddress() {
		return shipToAddress;
	}
	/**
	 * @param shipToAddress the shipToAddress to set
	 */
	public void setShipToAddress(QuoteAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	/**
	 * @return the otherInfo
	 */
	public String getOtherInfo() {
		return otherInfo;
	}
	/**
	 * @param otherInfo the otherInfo to set
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	/**
	 * @return the warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}
	/**
	 * @param warehouseId the warehouseId to set
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	/**
	 * @return the storageId
	 */
	public Integer getStorageId() {
		return storageId;
	}
	/**
	 * @param storageId the storageId to set
	 */
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}
	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}
	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	/**
	 * @return the catalogText
	 */
	public String getCatalogText() {
		return catalogText;
	}
	/**
	 * @param catalogText the catalogText to set
	 */
	public void setCatalogText(String catalogText) {
		this.catalogText = catalogText;
	}
	/**
	 * @return the typeText
	 */
	public String getTypeText() {
		return typeText;
	}
	/**
	 * @param typeText the typeText to set
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
	/**
	 * @return the upSymbol
	 */
	public String getUpSymbol() {
		return upSymbol;
	}
	/**
	 * @param upSymbol the upSymbol to set
	 */
	public void setUpSymbol(String upSymbol) {
		this.upSymbol = upSymbol;
	}
	/**
	 * @return the upPrecision
	 */
	public Integer getUpPrecision() {
		return upPrecision;
	}
	/**
	 * @param upPrecision the upPrecision to set
	 */
	public void setUpPrecision(Integer upPrecision) {
		this.upPrecision = upPrecision;
	}
	/**
	 * @return the basePrice
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * @return the quoteItemId
	 */
	public Integer getQuoteItemId() {
		return quoteItemId;
	}
	/**
	 * @param quoteItemId the quoteItemId to set
	 */
	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
	}
	/**
	 * @return the statusNote
	 */
	public String getStatusNote() {
		return statusNote;
	}
	/**
	 * @param statusNote the statusNote to set
	 */
	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}
	/**
	 * @return the packageType
	 */
	public String getPackageType() {
		return packageType;
	}
	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	/**
	 * @return the subItemList
	 */
	public List<QuoteItemDTO> getSubItemList() {
		return subItemList;
	}
	/**
	 * @param subItemList the subItemList to set
	 */
	public void setSubItemList(List<QuoteItemDTO> subItemList) {
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
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public PkgServiceDTO getQuotePkgService() {
		return quotePkgService;
	}
	public void setQuotePkgService(PkgServiceDTO quotePkgService) {
		this.quotePkgService = quotePkgService;
	}
	public AntibodyDTO getAntibody() {
		return antibody;
	}
	public void setAntibody(AntibodyDTO antibody) {
		this.antibody = antibody;
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
	public String getShippingRoute() {
		return shippingRoute;
	}
	public void setShippingRoute(String shippingRoute) {
		this.shippingRoute = shippingRoute;
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

	/**
	 * @return the dnaSequencing
	 */
	public DnaSequencingDTO getDnaSequencing() {
		return dnaSequencing;
	}

	/**
	 * @param dnaSequencing the dnaSequencing to set
	 */
	public void setDnaSequencing(DnaSequencingDTO dnaSequencing) {
		this.dnaSequencing = dnaSequencing;
	}

	public OrderMutationLibrariesDTO getMutationLibraries() {
		return mutationLibraries;
	}

	public void setMutationLibraries(OrderMutationLibrariesDTO mutationLibraries) {
		this.mutationLibraries = mutationLibraries;
	}

	 
}
