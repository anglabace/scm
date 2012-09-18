package com.genscript.gsscm.ws.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.OrderReturnDTO;
import com.genscript.gsscm.order.entity.OrderItemsPriceBean;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.entity.OrderTemplate;
import com.genscript.gsscm.order.entity.OrderTemplateItemBean;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.quote.entity.QuoteItemsPriceBean;
import com.genscript.gsscm.quote.entity.QuoteTemplateItemBean;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.serv.entity.ServiceItemBean;

@XmlType(name = "OrderRequest", namespace = WsConstants.NS)
public class OrderRequest extends WsRequest {

	private List<SearchProperty> searchPropertyList;
	private Integer custNo;
	private Integer orderNo;
	private String statusReason;
	private String comment;
	private Page<OrderMainBean> pageOrderMainBean;
	private Integer orderItemId;
	private String catalogNo;
//	private Integer quantity;
	private BigDecimal amount;
	private Date orderDate;
	private AddressType addrType;
	private OrderInstructionType instructionType;	
    private OrderMainDTO orderMainDTO;	
    private List<String> catalogNoList;
    private Integer storageId;
    private Integer warehouseId;
    private OrderTemplate orderTemplate;
    private QuoteItemType itemType;
    private OrderItemStatusType status;
    private String reason;
//    private Boolean isProductRelation = Boolean.FALSE;
//    private Integer mainProductId;
//    private Integer productId;
	private OrderReturnDTO orderReturn;
	private Integer rmaNo;
	private Integer returnQty;
	private Double returnSize;
	private Integer orderItemNo;
	private Integer precision;
	private ItemSearchType itemSearchType;
	private TemplateType templateItemType;
	private Page<CustomerPriceBean> pageCustomerPriceBean;
	private Page<QuoteTemplateItemBean> pageQuoteTmplItemBean;
	private Page<OrderTemplateItemBean> pageOrderTmplItemBean;
	private Page<QuoteItemsPriceBean> pageQuoteItemsPriceBean;
	private Page<OrderItemsPriceBean> pageOrderItemsPriceBean;
	private Page<ServiceItemBean> pageServiceItemBean;
	private Date entryDate;
	private Double discount;
	private Double tax;
	private Double customerCharge;
	private String fromCurrency;
	private String toCurrency;
    private String statusNote;
    private List<CurrencyChangeDTO> currencyChangeDTOList;
    private List<OrderItemDTO> orderItemList;
    private List<Integer> itemNoList;
    private Integer packageId;
    private Integer itemNeedCount;
    private List<ItemDiscountDTO> itemDiscountDTOList;
    private String promotionCode;
    private String shipAccount;
    private List<OrderPackageDTO> packageList;    
    private OrderStatusType orderStatus;
    private String overrideFlag;
    private List<ProductItemPriceDTO> productItemDTOList;
    private List<Integer> tmplIdList;
    private String orderCurrency;
    private Double orderTotal;
    private Double exchRate;
    private Integer serviceId;
    
    public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public List<Integer> getTmplIdList() {
		return tmplIdList;
	}
	public void setTmplIdList(List<Integer> tmplIdList) {
		this.tmplIdList = tmplIdList;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	public Integer getOrderNo() {
		return orderNo;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public String getCatalogNo() {
		return catalogNo;
	}
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public Page<OrderMainBean> getPageOrderMainBean() {
		return pageOrderMainBean;
	}
	public void setPageOrderMainBean(Page<OrderMainBean> pageOrderMainBean) {
		this.pageOrderMainBean = pageOrderMainBean;
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
	public OrderInstructionType getInstructionType() {
		return instructionType;
	}
	/**
	 * @param instructionType the instructionType to set
	 */
	public void setInstructionType(OrderInstructionType instructionType) {
		this.instructionType = instructionType;
	}
	/**
	 * @return the orderMainDTO
	 */
	public OrderMainDTO getOrderMainDTO() {
		return orderMainDTO;
	}
	/**
	 * @param orderMainDTO the orderMainDTO to set
	 */
	public void setOrderMainDTO(OrderMainDTO orderMainDTO) {
		this.orderMainDTO = orderMainDTO;
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
	 * @return the orderTemplate
	 */
	public OrderTemplate getOrderTemplate() {
		return orderTemplate;
	}
	/**
	 * @param orderTemplate the orderTemplate to set
	 */
	public void setOrderTemplate(OrderTemplate orderTemplate) {
		this.orderTemplate = orderTemplate;
	}
	/**
	 * @return the itemType
	 */
	public QuoteItemType getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(QuoteItemType itemType) {
		this.itemType = itemType;
	}
	/**
	 * @return the status
	 */
	public OrderItemStatusType getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderItemStatusType status) {
		this.status = status;
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
	 * @return the orderReturn
	 */
	public OrderReturnDTO getOrderReturn() {
		return orderReturn;
	}
	/**
	 * @param orderReturn the orderReturn to set
	 */
	public void setOrderReturn(OrderReturnDTO orderReturn) {
		this.orderReturn = orderReturn;
	}
	/**
	 * @return the rmaNo
	 */
	public Integer getRmaNo() {
		return rmaNo;
	}
	/**
	 * @param rmaNo the rmaNo to set
	 */
	public void setRmaNo(Integer rmaNo) {
		this.rmaNo = rmaNo;
	}
	public Integer getReturnQty() {
		return returnQty;
	}
	public void setReturnQty(Integer returnQty) {
		this.returnQty = returnQty;
	}
	public Double getReturnSize() {
		return returnSize;
	}
	public void setReturnSize(Double returnSize) {
		this.returnSize = returnSize;
	}
	public Integer getOrderItemNo() {
		return orderItemNo;
	}
	public void setOrderItemNo(Integer orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	public Integer getPrecision() {
		return precision;
	}
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}
	public ItemSearchType getItemSearchType() {
		return itemSearchType;
	}
	public void setItemSearchType(ItemSearchType itemSearchType) {
		this.itemSearchType = itemSearchType;
	}
	public TemplateType getTemplateItemType() {
		return templateItemType;
	}
	public void setTemplateItemType(TemplateType templateItemType) {
		this.templateItemType = templateItemType;
	}
	public Page<CustomerPriceBean> getPageCustomerPriceBean() {
		return pageCustomerPriceBean;
	}
	public void setPageCustomerPriceBean(
			Page<CustomerPriceBean> pageCustomerPriceBean) {
		this.pageCustomerPriceBean = pageCustomerPriceBean;
	}
	public Page<QuoteTemplateItemBean> getPageQuoteTmplItemBean() {
		return pageQuoteTmplItemBean;
	}
	public void setPageQuoteTmplItemBean(
			Page<QuoteTemplateItemBean> pageQuoteTmplItemBean) {
		this.pageQuoteTmplItemBean = pageQuoteTmplItemBean;
	}
	public Page<OrderTemplateItemBean> getPageOrderTmplItemBean() {
		return pageOrderTmplItemBean;
	}
	public void setPageOrderTmplItemBean(
			Page<OrderTemplateItemBean> pageOrderTmplItemBean) {
		this.pageOrderTmplItemBean = pageOrderTmplItemBean;
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
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
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
	/**
	 * @return the orderItemList
	 */
	public List<OrderItemDTO> getOrderItemList() {
		return orderItemList;
	}
	/**
	 * @param orderItemList the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItemDTO> orderItemList) {
		this.orderItemList = orderItemList;
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
	 * @return the packageId
	 */
	public Integer getPackageId() {
		return packageId;
	}
	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	/**
	 * @return the itemNeedCount
	 */
	public Integer getItemNeedCount() {
		return itemNeedCount;
	}
	/**
	 * @param itemNeedCount the itemNeedCount to set
	 */
	public void setItemNeedCount(Integer itemNeedCount) {
		this.itemNeedCount = itemNeedCount;
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
	 * @return the packageList
	 */
	public List<OrderPackageDTO> getPackageList() {
		return packageList;
	}
	/**
	 * @param packageList the packageList to set
	 */
	public void setPackageList(List<OrderPackageDTO> packageList) {
		this.packageList = packageList;
	}
	/**
	 * @return the orderStatus
	 */
	public OrderStatusType getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(OrderStatusType orderStatus) {
		this.orderStatus = orderStatus;
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
	public Double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
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
}
