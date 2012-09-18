package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;

@XmlType(name = "ERPOrderResponse", namespace = WsConstants.NS)
public class ErpOrderResponse extends WsResponse {
	private List<ServiceItemPiceDTO> serviceItemPiceDTOList;

	public List<ServiceItemPiceDTO> getServiceItemPiceDTOList() {
		return serviceItemPiceDTOList;
	}

	public void setServiceItemPiceDTOList(
			List<ServiceItemPiceDTO> serviceItemPiceDTOList) {
		this.serviceItemPiceDTOList = serviceItemPiceDTOList;
	}

	
}
