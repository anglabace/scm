package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.ZoneAndRateDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.shipment.dto.ShipMethodDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;

@XmlType(name = "SysSetResponse", namespace = WsConstants.NS)
public class SysSetResponse extends WsResponse {
	private List<Source> sourceList;
	private List<PromotionBean> promotionList;
	private PageDTO pageDTO;
	private Integer delSize;
	private List<String> cannotDelIdList;
	private List<ShipMethod> shipMethodList;
	private ZoneAndRateDTO zoneAndRateDTO;
	private List<DropDownListDTO> dropDownListDTOList;
	private ShipMethodDTO shipMethodDTO;
	private Integer shipMethodId;
	public List<Source> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<Source> sourceList) {
		this.sourceList = sourceList;
	}
	public PageDTO getPageDTO() {
		return pageDTO;
	}
	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}
	public List<PromotionBean> getPromotionList() {
		return promotionList;
	}
	public void setPromotionList(List<PromotionBean> promotionList) {
		this.promotionList = promotionList;
	}
	public Integer getDelSize() {
		return delSize;
	}
	public void setDelSize(Integer delSize) {
		this.delSize = delSize;
	}
	public List<String> getCannotDelIdList() {
		return cannotDelIdList;
	}
	public void setCannotDelIdList(List<String> cannotDelIdList) {
		this.cannotDelIdList = cannotDelIdList;
	}
	public List<ShipMethod> getShipMethodList() {
		return shipMethodList;
	}
	public void setShipMethodList(List<ShipMethod> shipMethodList) {
		this.shipMethodList = shipMethodList;
	}
	public ZoneAndRateDTO getZoneAndRateDTO() {
		return zoneAndRateDTO;
	}
	public void setZoneAndRateDTO(ZoneAndRateDTO zoneAndRateDTO) {
		this.zoneAndRateDTO = zoneAndRateDTO;
	}
	public List<DropDownListDTO> getDropDownListDTOList() {
		return dropDownListDTOList;
	}
	public void setDropDownListDTOList(List<DropDownListDTO> dropDownListDTOList) {
		this.dropDownListDTOList = dropDownListDTOList;
	}
	public ShipMethodDTO getShipMethodDTO() {
		return shipMethodDTO;
	}
	public void setShipMethodDTO(ShipMethodDTO shipMethodDTO) {
		this.shipMethodDTO = shipMethodDTO;
	}
	public Integer getShipMethodId() {
		return shipMethodId;
	}
	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}
	
	
}
