package com.genscript.gsscm.ws.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.QuoteInstructionType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.entity.OrderItemsPriceBean;
import com.genscript.gsscm.order.entity.OrderTemplateItemBean;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.entity.QuoteItemsPriceBean;
import com.genscript.gsscm.quote.entity.QuoteMainBean;
import com.genscript.gsscm.quote.entity.QuoteTemplate;
import com.genscript.gsscm.quote.entity.QuoteTemplateItemBean;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.serv.entity.ServiceItemBean;

@XmlType(name = "QuoteRequest", namespace = WsConstants.NS)
public class QuoteRequest extends WsRequest {

	private List<SearchProperty> searchPropertyList;
	private Integer custNo;
	private Integer quoteNo;
	private String statusReason;
	private String comment;
	private Page<QuoteMainBean> pageQuoteMainBean;
	private Page<CustomerPriceBean> pageCustomerPriceBean;
	private Page<OrderTemplateItemBean> pageOrderTmplItemBean;
	private Page<QuoteTemplateItemBean> pageQuoteTmplItemBean;
	private Page<QuoteItemsPriceBean> pageQuoteItemsPriceBean;
	private Page<OrderItemsPriceBean> pageOrderItemsPriceBean;
	private Page<ServiceItemBean> pageServiceItemBean;
	private Integer quoteItemId;
	private String catalogNo;
//	private Integer quantity;
	private BigDecimal amount;
	private Date quoteDate;
	private AddressType addrType;
	private QuoteInstructionType instructionType;
	private List<String> catalogNoList;
    private QuoteMainDTO quoteMainDTO;
    private ItemSearchType itemSearchType;
    private TemplateType templateItemType;
//    private Boolean isProductRelation = Boolean.FALSE;
//    private Integer mainProductId;
//    private Integer productId;
    private QuoteStatusType quoteStatus;
    private Integer storageId;
    private QuoteItemType quoteItemType;
    private QuoteItemStatusType quoteItemStatus;
    private String reason;
    private QuoteTemplate quoteTemplate;
    private Double discount;
	private Double tax;
	private Double customerCharge;
	private String fromCurrency;
	private String toCurrency;
    private String statusNote;
    private List<CurrencyChangeDTO> currencyChangeDTOList;
    private List<ItemDiscountDTO> itemDiscountDTOList;
    private String promotionCode;
    private List<Integer> itemNoList;
    private List<QuoteItemDTO> quoteItemList;
    private String shipAccount;
    private List<QuotePackageDTO> packageList;
    private String overrideFlag;
    private List<ProductItemPriceDTO> productItemDTOList;
    private List<Integer> tmplIdList;
    private String quoteCurrency;
    private Double exchRate;
    private Integer serviceId;
    
    public String getQuoteCurrency() {
		return quoteCurrency;
	}

	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}

	public List<Integer> getTmplIdList() {
		return tmplIdList;
	}

	public void setTmplIdList(List<Integer> tmplIdList) {
		this.tmplIdList = tmplIdList;
	}

	public List<String> getCatalogNoList() {
		return catalogNoList;
	}

	public void setCatalogNoList(List<String> catalogNoList) {
		this.catalogNoList = catalogNoList;
	}

	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}

	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
	}

	public Date getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}

	public Integer getQuoteItemId() {
		return quoteItemId;
	}

	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
	}

	public String getCatalogNo() {
		return catalogNo;
	}

	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public Page<CustomerPriceBean> getPageCustomerPriceBean() {
		return pageCustomerPriceBean;
	}

	public void setPageCustomerPriceBean(
			Page<CustomerPriceBean> pageCustomerPriceBean) {
		this.pageCustomerPriceBean = pageCustomerPriceBean;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public Page<QuoteMainBean> getPageQuoteMainBean() {
		return pageQuoteMainBean;
	}

	public void setPageQuoteMainBean(Page<QuoteMainBean> pageQuoteMainBean) {
		this.pageQuoteMainBean = pageQuoteMainBean;
	}

	/**
	 * @return the addrType
	 */
	public AddressType getAddrType() {
		return addrType;
	}

	/**
	 * @param addrType the addrType to set
	 */
	public void setAddrType(AddressType addrType) {
		this.addrType = addrType;
	}

	/**
	 * @return the instructionType
	 */
	public QuoteInstructionType getInstructionType() {
		return instructionType;
	}

	/**
	 * @param instructionType the instructionType to set
	 */
	public void setInstructionType(QuoteInstructionType instructionType) {
		this.instructionType = instructionType;
	}

	/**
	 * @return the quoteMainDTO
	 */
	public QuoteMainDTO getQuoteMainDTO() {
		return quoteMainDTO;
	}

	/**
	 * @param quoteMainDTO the quoteMainDTO to set
	 */
	public void setQuoteMainDTO(QuoteMainDTO quoteMainDTO) {
		this.quoteMainDTO = quoteMainDTO;
	}

	public ItemSearchType getItemSearchType() {
		return itemSearchType;
	}

	public void setItemSearchType(ItemSearchType itemSearchType) {
		this.itemSearchType = itemSearchType;
	}

	public Page<OrderTemplateItemBean> getPageOrderTmplItemBean() {
		return pageOrderTmplItemBean;
	}

	public void setPageOrderTmplItemBean(
			Page<OrderTemplateItemBean> pageOrderTmplItemBean) {
		this.pageOrderTmplItemBean = pageOrderTmplItemBean;
	}

	public Page<QuoteTemplateItemBean> getPageQuoteTmplItemBean() {
		return pageQuoteTmplItemBean;
	}

	public void setPageQuoteTmplItemBean(
			Page<QuoteTemplateItemBean> pageQuoteTmplItemBean) {
		this.pageQuoteTmplItemBean = pageQuoteTmplItemBean;
	}

	public TemplateType getTemplateItemType() {
		return templateItemType;
	}

	public void setTemplateItemType(TemplateType templateItemType) {
		this.templateItemType = templateItemType;
	}

	public Page<QuoteItemsPriceBean> getPageQuoteItemsPriceBean() {
		return pageQuoteItemsPriceBean;
	}

	public void setPageQuoteItemsPriceBean(
			Page<QuoteItemsPriceBean> pageQuoteItemsPriceBean) {
		this.pageQuoteItemsPriceBean = pageQuoteItemsPriceBean;
	}

	public Page<OrderItemsPriceBean> getPageOrderItemsPriceBean() {
		return pageOrderItemsPriceBean;
	}

	public void setPageOrderItemsPriceBean(
			Page<OrderItemsPriceBean> pageOrderItemsPriceBean) {
		this.pageOrderItemsPriceBean = pageOrderItemsPriceBean;
	}


	/**
	 * @return the quoteStatus
	 */
	public QuoteStatusType getQuoteStatus() {
		return quoteStatus;
	}

	/**
	 * @param quoteStatus the quoteStatus to set
	 */
	public void setQuoteStatus(QuoteStatusType quoteStatus) {
		this.quoteStatus = quoteStatus;
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
	 * @return the quoteItemType
	 */
	public QuoteItemType getQuoteItemType() {
		return quoteItemType;
	}

	/**
	 * @param quoteItemType the quoteItemType to set
	 */
	public void setQuoteItemType(QuoteItemType quoteItemType) {
		this.quoteItemType = quoteItemType;
	}

	/**
	 * @return the quoteItemStatus
	 */
	public QuoteItemStatusType getQuoteItemStatus() {
		return quoteItemStatus;
	}

	/**
	 * @param quoteItemStatus the quoteItemStatus to set
	 */
	public void setQuoteItemStatus(QuoteItemStatusType quoteItemStatus) {
		this.quoteItemStatus = quoteItemStatus;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the quoteTemplate
	 */
	public QuoteTemplate getQuoteTemplate() {
		return quoteTemplate;
	}

	/**
	 * @param quoteTemplate the quoteTemplate to set
	 */
	public void setQuoteTemplate(QuoteTemplate quoteTemplate) {
		this.quoteTemplate = quoteTemplate;
	}
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getCustomerCharge() {
		return customerCharge;
	}

	public void setCustomerCharge(Double customerCharge) {
		this.customerCharge = customerCharge;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
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

	public List<CurrencyChangeDTO> getCurrencyChangeDTOList() {
		return currencyChangeDTOList;
	}

	public void setCurrencyChangeDTOList(
			List<CurrencyChangeDTO> currencyChangeDTOList) {
		this.currencyChangeDTOList = currencyChangeDTOList;
	}

	public List<ItemDiscountDTO> getItemDiscountDTOList() {
		return itemDiscountDTOList;
	}

	public void setItemDiscountDTOList(List<ItemDiscountDTO> itemDiscountDTOList) {
		this.itemDiscountDTOList = itemDiscountDTOList;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	/**
	 * @return the quoteItemList
	 */
	public List<QuoteItemDTO> getQuoteItemList() {
		return quoteItemList;
	}

	/**
	 * @param quoteItemList the quoteItemList to set
	 */
	public void setQuoteItemList(List<QuoteItemDTO> quoteItemList) {
		this.quoteItemList = quoteItemList;
	}

	/**
	 * @return the itemNoList
	 */
	public List<Integer> getItemNoList() {
		return itemNoList;
	}

	/**
	 * @param itemNoList the itemNoList to set
	 */
	public void setItemNoList(List<Integer> itemNoList) {
		this.itemNoList = itemNoList;
	}

	/**
	 * @return the shipAccount
	 */
	public String getShipAccount() {
		return shipAccount;
	}

	/**
	 * @param shipAccount the shipAccount to set
	 */
	public void setShipAccount(String shipAccount) {
		this.shipAccount = shipAccount;
	}

	

	/**
	 * @return the overrideFlag
	 */
	public String getOverrideFlag() {
		return overrideFlag;
	}

	/**
	 * @param overrideFlag the overrideFlag to set
	 */
	public void setOverrideFlag(String overrideFlag) {
		this.overrideFlag = overrideFlag;
	}

	public Page<ServiceItemBean> getPageServiceItemBean() {
		return pageServiceItemBean;
	}

	public void setPageServiceItemBean(Page<ServiceItemBean> pageServiceItemBean) {
		this.pageServiceItemBean = pageServiceItemBean;
	}

	public List<ProductItemPriceDTO> getProductItemDTOList() {
		return productItemDTOList;
	}

	public void setProductItemDTOList(List<ProductItemPriceDTO> productItemDTOList) {
		this.productItemDTOList = productItemDTOList;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Double getExchRate() {
		return exchRate;
	}

	public void setExchRate(Double exchRate) {
		this.exchRate = exchRate;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public List<QuotePackageDTO> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<QuotePackageDTO> packageList) {
		this.packageList = packageList;
	}


}
