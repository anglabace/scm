package com.genscript.gsscm.ws.response;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.quote.dto.QuoteAddressDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;
import com.genscript.gsscm.quote.entity.QuoteMainBean;
import com.genscript.gsscm.quote.entity.QuotePaymentPlan;
import com.genscript.gsscm.quote.entity.QuoteTemplate;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.quoteorder.dto.ItemBeanDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.quoteorder.dto.TotalDTO;

@XmlType(name = "QuoteResponse", namespace = WsConstants.NS)
public class QuoteResponse extends WsResponse {
	
	private PageDTO pageDTO;
	private List<QuoteMainBean> quoteMainList;
	private List<ItemBeanDTO> itemBeanDTOList;
	private List<ProcessLogDTO> processLogDTOList;
	private QuoteAddressDTO quoteAddressDTO;
	private List<QuoteAddress> addrList;
	private List<InstructionDTO> instructionList;
    private QuoteMainDTO quoteMainDTO;
    private List<ProductItemPriceDTO> productItemDTOList;
    private Integer quoteNo;
    private List<ItemDiscountDTO> itemDiscountDTOList;
    private List<QuotePaymentPlan> quotePaymentPlanList;
    private Integer preferPaymentMthd;
    private String paymentMthdType;
    private String otherInfo;
    private Integer maxMyTemplateCount;
    private Integer curtMyTemplateCount;
    private TotalDTO totalDTO;
    private Long totalProducts;
    private Integer orderNo;
    private List<CurrencyChangeDTO> currencyChangeDTOList;
    private String symbol;
    private List<QuotePackageDTO> quotePackageList;
    private ShippingTotalDTO shippingTotal;
    private List<QuoteTemplate> quoteTemplateList;
    private BigDecimal exchRate;
	
	public List<ItemBeanDTO> getItemBeanDTOList() {
		return itemBeanDTOList;
	}
	public void setItemBeanDTOList(List<ItemBeanDTO> itemBeanDTOList) {
		this.itemBeanDTOList = itemBeanDTOList;
	}
	public PageDTO getPageDTO() {
		return pageDTO;
	}
	public List<ItemDiscountDTO> getItemDiscountDTOList() {
		return itemDiscountDTOList;
	}
	public void setItemDiscountDTOList(List<ItemDiscountDTO> itemDiscountDTOList) {
		this.itemDiscountDTOList = itemDiscountDTOList;
	}
	public List<ProcessLogDTO> getProcessLogDTOList() {
		return processLogDTOList;
	}
	public void setProcessLogDTOList(List<ProcessLogDTO> processLogDTOList) {
		this.processLogDTOList = processLogDTOList;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}
	
	public List<QuotePaymentPlan> getQuotePaymentPlanList() {
		return quotePaymentPlanList;
	}
	public void setQuotePaymentPlanList(List<QuotePaymentPlan> quotePaymentPlanList) {
		this.quotePaymentPlanList = quotePaymentPlanList;
	}
	
	public List<QuoteMainBean> getQuoteMainList() {
		return quoteMainList;
	}
	public void setQuoteMainList(List<QuoteMainBean> quoteMainList) {
		this.quoteMainList = quoteMainList;
	}
	/**
	 * @return the quoteAddressDTO
	 */
	public QuoteAddressDTO getQuoteAddressDTO() {
		return quoteAddressDTO;
	}
	/**
	 * @param quoteAddressDTO the quoteAddressDTO to set
	 */
	public void setQuoteAddressDTO(QuoteAddressDTO quoteAddressDTO) {
		this.quoteAddressDTO = quoteAddressDTO;
	}
	/**
	 * @return the addrList
	 */
	public List<QuoteAddress> getAddrList() {
		return addrList;
	}
	/**
	 * @param addrList the addrList to set
	 */
	public void setAddrList(List<QuoteAddress> addrList) {
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
	public TotalDTO getTotalDTO() {
		return totalDTO;
	}
	public void setTotalDTO(TotalDTO totalDTO) {
		this.totalDTO = totalDTO;
	}
	public Long getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(Long totalProducts) {
		this.totalProducts = totalProducts;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	 * @return the quotePackageList
	 */
	public List<QuotePackageDTO> getQuotePackageList() {
		return quotePackageList;
	}
	/**
	 * @param quotePackageList the quotePackageList to set
	 */
	public void setQuotePackageList(List<QuotePackageDTO> quotePackageList) {
		this.quotePackageList = quotePackageList;
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
	public List<ProductItemPriceDTO> getProductItemDTOList() {
		return productItemDTOList;
	}
	public void setProductItemDTOList(List<ProductItemPriceDTO> productItemDTOList) {
		this.productItemDTOList = productItemDTOList;
	}
	public List<QuoteTemplate> getQuoteTemplateList() {
		return quoteTemplateList;
	}
	public void setQuoteTemplateList(List<QuoteTemplate> quoteTemplateList) {
		this.quoteTemplateList = quoteTemplateList;
	}
	public BigDecimal getExchRate() {
		return exchRate;
	}
	public void setExchRate(BigDecimal exchRate) {
		this.exchRate = exchRate;
	}
}
