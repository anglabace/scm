package com.genscript.gsscm.common.util;

import java.util.List;

import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
/**
 *  实现自动生成package的帮助类.
 * @author wangsf
 *
 */
public class PackageHelper {
	/**
	 * 该package的实际重量.
	 */
	private Double totalActWeight;
	/**
	 * 最低温度
	 */
	private Double lowestT;
	private OrderPackageDTO orderPackageDTO;
	private List<OrderItemDTO> orderPackageItemList;	
	private QuotePackageDTO quotePackageDTO;
	private List<QuoteItemDTO> quotePackageItemList;
	
	public QuotePackageDTO getQuotePackageDTO() {
		return quotePackageDTO;
	}
	public void setQuotePackageDTO(QuotePackageDTO quotePackageDTO) {
		this.quotePackageDTO = quotePackageDTO;
	}
	public List<QuoteItemDTO> getQuotePackageItemList() {
		return quotePackageItemList;
	}
	public void setQuotePackageItemList(List<QuoteItemDTO> quotePackageItemList) {
		this.quotePackageItemList = quotePackageItemList;
	}
	public Double getTotalActWeight() {
		return totalActWeight;
	}
	public void setTotalActWeight(Double totalActWeight) {
		this.totalActWeight = totalActWeight;
	}
	public OrderPackageDTO getOrderPackageDTO() {
		return orderPackageDTO;
	}
	public void setOrderPackageDTO(OrderPackageDTO orderPackageDTO) {
		this.orderPackageDTO = orderPackageDTO;
	}
	public List<OrderItemDTO> getOrderPackageItemList() {
		return orderPackageItemList;
	}
	public void setOrderPackageItemList(List<OrderItemDTO> orderPackageItemList) {
		this.orderPackageItemList = orderPackageItemList;
	}
	public Double getLowestT() {
		return lowestT;
	}
	public void setLowestT(Double lowestT) {
		this.lowestT = lowestT;
	}


}
