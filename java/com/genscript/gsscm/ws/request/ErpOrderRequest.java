package com.genscript.gsscm.ws.request;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.dto.OrderErpMappingDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;

@XmlType(name = "ERPOrderRequest", namespace = WsConstants.NS)
public class ErpOrderRequest extends WsRequest {
	private OrderErpMappingDTO orderErpMappingDTO;
	private Integer custNo;
	private List<ServiceItemPiceDTO> orderServiceItemPiceDTOList;
	//private Integer customerId;
	private Date orderQuoteDate;
	
	public OrderErpMappingDTO getOrderErpMappingDTO() {
		return orderErpMappingDTO;
	}

	public void setOrderErpMappingDTO(OrderErpMappingDTO orderErpMappingDTO) {
		this.orderErpMappingDTO = orderErpMappingDTO;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public List<ServiceItemPiceDTO> getOrderServiceItemPiceDTOList() {
		return orderServiceItemPiceDTOList;
	}

	public void setOrderServiceItemPiceDTOList(
			List<ServiceItemPiceDTO> orderServiceItemPiceDTOList) {
		this.orderServiceItemPiceDTOList = orderServiceItemPiceDTOList;
	}

	public Date getOrderQuoteDate() {
		return orderQuoteDate;
	}

	public void setOrderQuoteDate(Date orderQuoteDate) {
		this.orderQuoteDate = orderQuoteDate;
	}
	
	
}
