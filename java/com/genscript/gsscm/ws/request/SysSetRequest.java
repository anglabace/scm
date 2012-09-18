package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.SourceDTO;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.quoteorder.dto.PromotionDTO;
import com.genscript.gsscm.shipment.dto.ShipMethodDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipZone;

@XmlType(name = "SysSetRequest", namespace = WsConstants.NS)
public class SysSetRequest extends WsRequest {

	private Page<Source> pageSource;
	private Page<PromotionBean> pagePromotion;
	private List<SearchProperty> searchPropertyList;
	private List<SourceDTO> sourceDTOList;
	private List<PromotionDTO> promotionDTOList;
	private List<Integer> delIdList;
	private Page<ShipMethod> pageShipMethod;
	private Integer warehouseId;
	private Integer zoneId;
	private Page<ShipZone> pageShipZone;
	private Page<ShipRate> pageShipRate;
	private Integer shipMethodId;
	private List<Integer> shipMethodDelIdList;
	private ShipMethodDTO shipMethodDTO;
	private List<ShipZone> shipZoneList;
	private List<ShipRate> shipRateList;
	private List<Integer> shipZoneDelIdList;
	private List<Integer> shipRateDelIdList;
	private String type;
	
	public Page<Source> getPageSource() {
		return pageSource;
	}

	public void setPageSource(Page<Source> pageSource) {
		this.pageSource = pageSource;
	}

	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}

	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
	}

	public List<SourceDTO> getSourceDTOList() {
		return sourceDTOList;
	}

	public void setSourceDTOList(List<SourceDTO> sourceDTOList) {
		this.sourceDTOList = sourceDTOList;
	}

	public List<PromotionDTO> getPromotionDTOList() {
		return promotionDTOList;
	}

	public void setPromotionDTOList(List<PromotionDTO> promotionDTOList) {
		this.promotionDTOList = promotionDTOList;
	}

	public Page<PromotionBean> getPagePromotion() {
		return pagePromotion;
	}

	public void setPagePromotion(Page<PromotionBean> pagePromotion) {
		this.pagePromotion = pagePromotion;
	}

	public List<Integer> getDelIdList() {
		return delIdList;
	}

	public void setDelIdList(List<Integer> delIdList) {
		this.delIdList = delIdList;
	}

	public Page<ShipMethod> getPageShipMethod() {
		return pageShipMethod;
	}

	public void setPageShipMethod(Page<ShipMethod> pageShipMethod) {
		this.pageShipMethod = pageShipMethod;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Page<ShipZone> getPageShipZone() {
		return pageShipZone;
	}

	public void setPageShipZone(Page<ShipZone> pageShipZone) {
		this.pageShipZone = pageShipZone;
	}

	public Page<ShipRate> getPageShipRate() {
		return pageShipRate;
	}

	public void setPageShipRate(Page<ShipRate> pageShipRate) {
		this.pageShipRate = pageShipRate;
	}

	public Integer getShipMethodId() {
		return shipMethodId;
	}

	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}

	public List<Integer> getShipMethodDelIdList() {
		return shipMethodDelIdList;
	}

	public void setShipMethodDelIdList(List<Integer> shipMethodDelIdList) {
		this.shipMethodDelIdList = shipMethodDelIdList;
	}

	public ShipMethodDTO getShipMethodDTO() {
		return shipMethodDTO;
	}

	public void setShipMethodDTO(ShipMethodDTO shipMethodDTO) {
		this.shipMethodDTO = shipMethodDTO;
	}

	public List<ShipZone> getShipZoneList() {
		return shipZoneList;
	}

	public void setShipZoneList(List<ShipZone> shipZoneList) {
		this.shipZoneList = shipZoneList;
	}

	public List<ShipRate> getShipRateList() {
		return shipRateList;
	}

	public void setShipRateList(List<ShipRate> shipRateList) {
		this.shipRateList = shipRateList;
	}

	public List<Integer> getShipZoneDelIdList() {
		return shipZoneDelIdList;
	}

	public void setShipZoneDelIdList(List<Integer> shipZoneDelIdList) {
		this.shipZoneDelIdList = shipZoneDelIdList;
	}

	public List<Integer> getShipRateDelIdList() {
		return shipRateDelIdList;
	}

	public void setShipRateDelIdList(List<Integer> shipRateDelIdList) {
		this.shipRateDelIdList = shipRateDelIdList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
