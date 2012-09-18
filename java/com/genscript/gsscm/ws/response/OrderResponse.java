package com.genscript.gsscm.ws.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.order.dto.ItemOtherInfoForNewDTO;
import com.genscript.gsscm.order.dto.OrderAddressDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.OrderReturnDTO;
import com.genscript.gsscm.order.dto.OrderReturnItemDTO;
import com.genscript.gsscm.order.dto.PaymentVoucherDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.entity.OrderTemplate;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.quoteorder.dto.ItemBeanDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.quoteorder.dto.TotalDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;

@XmlType(name = "OrderResponse", namespace = WsConstants.NS)
public class OrderResponse extends WsResponse {

	private PageDTO pageDTO;
	private List<OrderMainBean> orderMainList;
	private List<ProcessLogDTO> processLogDTOList;
	private OrderAddressDTO orderAddressDTO;
	private List<ItemDiscountDTO> itemDiscountDTOList;
	private List<OrderAddress> addrList;
	private List<InstructionDTO> instructionList;
	private List<ProductItemPriceDTO> productItemDTOList;
    private OrderMainDTO orderMainDTO;	
    private Integer orderNo;
    private List<PaymentVoucherDTO> paymentVoucherList;
    private Integer preferPaymentMthd;
    private String paymentMthdType;
    private Integer itemStockQty;
    private String otherInfo;
    private Integer maxMyTemplateCount;
    private Integer curtMyTemplateCount;
    private List<OrderItemDTO> orderItemList;
    private List<OrderReturnDTO> orderReturnList;
    private TotalDTO totalDTO;
    private List<CreditCard> cardList;
	private OrderReturnDTO orderReturn;
	private Float returnRefund;
	private List<ItemBeanDTO> itemBeanDTOList;
	private Date expirationDate;    
	private Integer rmaNo;
	private List<OrderReturnItemDTO> returnItemList;
	private Long totalProducts;
	private List<CurrencyChangeDTO> currencyChangeDTOList;
	private String symbol;
	private List<OrderPackageDTO> shipPackageList;
	private OrderPackageDTO shipPackage;
	private ShippingTotalDTO shippingTotal;
	private String orderItemStatus;
	private ItemOtherInfoForNewDTO itemOtherInfoForNewDTO;	
	private OrderItemDTO orderItemDTO;
	private List<OrderTemplate> orderTemplateList;
	private BigDecimal prePayment;
	private BigDecimal exchRate;
	
	private ServiceItemPiceDTO servicePriceDTO;
	
	public PageDTO getPageDTO() {
		return pageDTO;
	}
	public List<ItemDiscountDTO> getItemDiscountDTOList() {
		return itemDiscountDTOList;
	}
	public void setItemDiscountDTOList(List<ItemDiscountDTO> itemDiscountDTOList) {
		this.itemDiscountDTOList = itemDiscountDTOList;
	}
	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}
	public List<OrderMainBean> getOrderMainList() {
		return orderMainList;
	}
	public void setOrderMainList(List<OrderMainBean> orderMainList) {
		this.orderMainList = orderMainList;
	}
	public List<ProcessLogDTO> getProcessLogDTOList() {
		return processLogDTOList;
	}
	public void setProcessLogDTOList(List<ProcessLogDTO> processLogDTOList) {
		this.processLogDTOList = processLogDTOList;
	}

	/**
	 * @return the orderAddressDTO
	 */
	public OrderAddressDTO getOrderAddressDTO() {
		return orderAddressDTO;
	}
	/**
	 * @param orderAddressDTO the orderAddressDTO to set
	 */
	public void setOrderAddressDTO(OrderAddressDTO orderAddressDTO) {
		this.orderAddressDTO = orderAddressDTO;
	}

	/**
	 * @return the addrList
	 */
	public List<OrderAddress> getAddrList() {
		return addrList;
	}
	/**
	 * @param addrList the addrList to set
	 */
	public void setAddrList(List<OrderAddress> addrList) {
		this.addrList = addrList;
	}
	/**
	 * @return the instructionList
	 */
	public List<InstructionDTO> getInstructionList() {
		return instructionList;
	}
	/**
	 * @param instructionList the instructionList to set
	 */
	public void setInstructionList(List<InstructionDTO> instructionList) {
		this.instructionList = instructionList;
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
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public List<PaymentVoucherDTO> getPaymentVoucherList() {
		return paymentVoucherList;
	}
	public void setPaymentVoucherList(List<PaymentVoucherDTO> paymentVoucherList) {
		this.paymentVoucherList = paymentVoucherList;
	}
	public Integer getPreferPaymentMthd() {
		return preferPaymentMthd;
	}
	public void setPreferPaymentMthd(Integer preferPaymentMthd) {
		this.preferPaymentMthd = preferPaymentMthd;
	}
	public String getPaymentMthdType() {
		return paymentMthdType;
	}
	public void setPaymentMthdType(String paymentMthdType) {
		this.paymentMthdType = paymentMthdType;
	}
	/**
	 * @return the itemStockQty
	 */
	public Integer getItemStockQty() {
		return itemStockQty;
	}
	/**
	 * @param itemStockQty the itemStockQty to set
	 */
	public void setItemStockQty(Integer itemStockQty) {
		this.itemStockQty = itemStockQty;
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
	 * @return the maxMyTemplateCount
	 */
	public Integer getMaxMyTemplateCount() {
		return maxMyTemplateCount;
	}
	/**
	 * @param maxMyTemplateCount the maxMyTemplateCount to set
	 */
	public void setMaxMyTemplateCount(Integer maxMyTemplateCount) {
		this.maxMyTemplateCount = maxMyTemplateCount;
	}
	/**
	 * @return the curtMyTemplateCount
	 */
	public Integer getCurtMyTemplateCount() {
		return curtMyTemplateCount;
	}
	/**
	 * @param curtMyTemplateCount the curtMyTemplateCount to set
	 */
	public void setCurtMyTemplateCount(Integer curtMyTemplateCount) {
		this.curtMyTemplateCount = curtMyTemplateCount;
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
	 * @return the orderReturnList
	 */
	public List<OrderReturnDTO> getOrderReturnList() {
		return orderReturnList;
	}
	/**
	 * @param orderReturnList the orderReturnList to set
	 */
	public void setOrderReturnList(List<OrderReturnDTO> orderReturnList) {
		this.orderReturnList = orderReturnList;
	}
	public TotalDTO getTotalDTO() {
		return totalDTO;
	}
	public void setTotalDTO(TotalDTO totalDTO) {
		this.totalDTO = totalDTO;
	}
	/**
	 * @return the cardList
	 */
	public List<CreditCard> getCardList() {
		return cardList;
	}
	/**
	 * @param cardList the cardList to set
	 */
	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
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
	public Float getReturnRefund() {
		return returnRefund;
	}
	public void setReturnRefund(Float returnRefund) {
		this.returnRefund = returnRefund;
	}
	public List<ItemBeanDTO> getItemBeanDTOList() {
		return itemBeanDTOList;
	}
	public void setItemBeanDTOList(List<ItemBeanDTO> itemBeanDTOList) {
		this.itemBeanDTOList = itemBeanDTOList;
	}
	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
	/**
	 * @return the returnItemList
	 */
	public List<OrderReturnItemDTO> getReturnItemList() {
		return returnItemList;
	}
	/**
	 * @param returnItemList the returnItemList to set
	 */
	public void setReturnItemList(List<OrderReturnItemDTO> returnItemList) {
		this.returnItemList = returnItemList;
	}
	public Long getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(Long totalProducts) {
		this.totalProducts = totalProducts;
	}
	public List<CurrencyChangeDTO> getCurrencyChangeDTOList() {
		return currencyChangeDTOList;
	}
	public void setCurrencyChangeDTOList(
			List<CurrencyChangeDTO> currencyChangeDTOList) {
		this.currencyChangeDTOList = currencyChangeDTOList;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the shipPackageList
	 */
	public List<OrderPackageDTO> getShipPackageList() {
		return shipPackageList;
	}
	/**
	 * @param shipPackageList the shipPackageList to set
	 */
	public void setShipPackageList(List<OrderPackageDTO> shipPackageList) {
		this.shipPackageList = shipPackageList;
	}
	/**
	 * @return the shipPackage
	 */
	public OrderPackageDTO getShipPackage() {
		return shipPackage;
	}
	/**
	 * @param shipPackage the shipPackage to set
	 */
	public void setShipPackage(OrderPackageDTO shipPackage) {
		this.shipPackage = shipPackage;
	}
	/**
	 * @return the shippingTotal
	 */
	public ShippingTotalDTO getShippingTotal() {
		return shippingTotal;
	}
	/**
	 * @param shippingTotal the shippingTotal to set
	 */
	public void setShippingTotal(ShippingTotalDTO shippingTotal) {
		this.shippingTotal = shippingTotal;
	}
	/**
	 * @return the orderItemStatus
	 */
	public String getOrderItemStatus() {
		return orderItemStatus;
	}
	/**
	 * @param orderItemStatus the orderItemStatus to set
	 */
	public void setOrderItemStatus(String orderItemStatus) {
		this.orderItemStatus = orderItemStatus;
	}
	/**
	 * @return the itemOtherInfoForNewDTO
	 */
	public ItemOtherInfoForNewDTO getItemOtherInfoForNewDTO() {
		return itemOtherInfoForNewDTO;
	}
	/**
	 * @param itemOtherInfoForNewDTO the itemOtherInfoForNewDTO to set
	 */
	public void setItemOtherInfoForNewDTO(
			ItemOtherInfoForNewDTO itemOtherInfoForNewDTO) {
		this.itemOtherInfoForNewDTO = itemOtherInfoForNewDTO;
	}
	public List<ProductItemPriceDTO> getProductItemDTOList() {
		return productItemDTOList;
	}
	public void setProductItemDTOList(List<ProductItemPriceDTO> productItemDTOList) {
		this.productItemDTOList = productItemDTOList;
	}
	/**
	 * @return the orderItemDTO
	 */
	public OrderItemDTO getOrderItemDTO() {
		return orderItemDTO;
	}
	/**
	 * @param orderItemDTO the orderItemDTO to set
	 */
	public void setOrderItemDTO(OrderItemDTO orderItemDTO) {
		this.orderItemDTO = orderItemDTO;
	}
	public List<OrderTemplate> getOrderTemplateList() {
		return orderTemplateList;
	}
	public void setOrderTemplateList(List<OrderTemplate> orderTemplateList) {
		this.orderTemplateList = orderTemplateList;
	}
	public BigDecimal getPrePayment() {
		return prePayment;
	}
	public void setPrePayment(BigDecimal prePayment) {
		this.prePayment = prePayment;
	}
	public ServiceItemPiceDTO getServicePriceDTO() {
		return servicePriceDTO;
	}
	public void setServicePriceDTO(ServiceItemPiceDTO servicePriceDTO) {
		this.servicePriceDTO = servicePriceDTO;
	}
	public BigDecimal getExchRate() {
		return exchRate;
	}
	public void setExchRate(BigDecimal exchRate) {
		this.exchRate = exchRate;
	}

}
