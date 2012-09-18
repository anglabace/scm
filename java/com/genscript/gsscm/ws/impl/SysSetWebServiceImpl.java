package com.genscript.gsscm.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SourceDTO;
import com.genscript.gsscm.basedata.dto.ZoneAndRateDTO;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.quoteorder.dto.PromotionDTO;
import com.genscript.gsscm.shipment.dto.ShipMethodDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.systemsetting.service.SystemSettingService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.SysSetWebService;
import com.genscript.gsscm.ws.request.SysSetRequest;
import com.genscript.gsscm.ws.response.SysSetResponse;

@WebService(serviceName = "SysSetService", portName = "SysSetServicePort", endpointInterface = "com.genscript.gsscm.ws.api.SysSetWebService", targetNamespace = WsConstants.NS)
public class SysSetWebServiceImpl implements SysSetWebService {

	private static Logger logger = LoggerFactory
	.getLogger(SysSetWebServiceImpl.class);
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private SystemSettingService systemSettingService;
	
	@Override
	public SysSetResponse searchSourceList(SysSetRequest sysSetRequest) {
		Page<Source> page = null;
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<Source> pageSource = sysSetRequest.getPageSource();
		if (!pageSource.isOrderBySetted()) {
			pageSource.setOrderBy("sourceId");
			pageSource.setOrder(Page.ASC);
		}try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = sysSetRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = systemSettingService.searchSource(pageSource);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = systemSettingService.searchSource(pageSource, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<Source> sourceList = page.getResult();
			sysSetResponse.setHasException(Boolean.FALSE);
			sysSetResponse.setSourceList(sourceList);
			sysSetResponse.setPageDTO(pageDTO);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse searchPromotionList(SysSetRequest sysSetRequest) {
		Page<PromotionBean> page = null;
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<PromotionBean> pagePromotion = sysSetRequest.getPagePromotion();
		if (!pagePromotion.isOrderBySetted()) {
			pagePromotion.setOrderBy("prmtId");
			pagePromotion.setOrder(Page.ASC);
		}try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = sysSetRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = systemSettingService.searchPromotion(pagePromotion);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = systemSettingService.searchPromotion(pagePromotion, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<PromotionBean> promotionList = page.getResult();
			sysSetResponse.setHasException(Boolean.FALSE);
			sysSetResponse.setPromotionList(promotionList);
			sysSetResponse.setPageDTO(pageDTO);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse saveSource(SysSetRequest sysSetRequest) {
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		List<SourceDTO> sourceDTOList = sysSetRequest.getSourceDTOList();
		List<Integer> delIds = sysSetRequest.getDelIdList();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<String> cannotDelIdList = systemSettingService.saveSource(sourceDTOList, delIds, userId);
			sysSetResponse.setCannotDelIdList(cannotDelIdList);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse savePromotion(SysSetRequest sysSetRequest) {
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		List<PromotionDTO> promotionDTOList = sysSetRequest.getPromotionDTOList();
		List<Integer> delIds = sysSetRequest.getDelIdList();
		try {
			Assert.notNull(userId, "user id can't be null");
			systemSettingService.savePromotion(promotionDTOList, delIds, userId);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse searchShipMethodList(SysSetRequest sysSetRequest) {
		Page<ShipMethod> page = null;
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<ShipMethod> pageShipMethod = sysSetRequest.getPageShipMethod();
		if (!pageShipMethod.isOrderBySetted()) {
			pageShipMethod.setOrderBy("methodCode");
			pageShipMethod.setOrder(Page.ASC);
		}try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = sysSetRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = systemSettingService.searchShipMethod(pageShipMethod);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = systemSettingService.searchShipMethod(pageShipMethod, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<ShipMethod> shipMethodList = page.getResult();
			sysSetResponse.setHasException(Boolean.FALSE);
			sysSetResponse.setShipMethodList(shipMethodList);
			sysSetResponse.setPageDTO(pageDTO);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse getZoneAndRateList(SysSetRequest sysSetRequest) {
		Page<ShipZone> pageZone = null;
		Page<ShipRate> pageRate = null;
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		Integer warehouseId = sysSetRequest.getWarehouseId();
		Integer shipMethodId = sysSetRequest.getShipMethodId();
		String type = sysSetRequest.getType();
		pageZone = sysSetRequest.getPageShipZone();
		pageRate = sysSetRequest.getPageShipRate();
		try {
			Assert.notNull(userId, "user id can't be null");
			ZoneAndRateDTO zoneAndRateDTO = systemSettingService.getZoneAndRateList(pageZone, pageRate, type, warehouseId, shipMethodId);
			sysSetResponse.setZoneAndRateDTO(zoneAndRateDTO);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse getShipRateByZone(SysSetRequest sysSetRequest) {
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		Integer zoneId = sysSetRequest.getZoneId();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<DropDownListDTO> dropDownListDTOList = systemSettingService.getShipRateByZone(zoneId);
			sysSetResponse.setDropDownListDTOList(dropDownListDTOList);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse getShipMethodDetail(SysSetRequest sysSetRequest) {
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		Integer methodId = sysSetRequest.getShipMethodId();
		try {
			Assert.notNull(userId, "user id can't be null");
			ShipMethodDTO shipMethodDTO = systemSettingService.getShipMethodDetail(methodId);
			sysSetResponse.setShipMethodDTO(shipMethodDTO);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse delShipMethod(SysSetRequest sysSetRequest) {
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		List<Integer> shipMethodDelIdList = sysSetRequest.getShipMethodDelIdList();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<String> cannotDelIdList = systemSettingService.delShipMethodList(shipMethodDelIdList);
			sysSetResponse.setCannotDelIdList(cannotDelIdList);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

	@Override
	public SysSetResponse saveShipMethod(SysSetRequest sysSetRequest) {
		SysSetResponse sysSetResponse = new SysSetResponse();
		Integer userId = sysSetRequest.getUserId();
		ShipMethodDTO shipMethodDTO = sysSetRequest.getShipMethodDTO();
		List<ShipZone> shipZoneList = sysSetRequest.getShipZoneList();
		List<ShipRate> shipRateList = sysSetRequest.getShipRateList();
		List<Integer> shipZoneDelIdList = sysSetRequest.getShipZoneDelIdList();
		List<Integer> shipRateDelIdList = sysSetRequest.getShipRateDelIdList();
		try {
			Assert.notNull(userId, "user id can't be null");
			Integer shipMethodId = systemSettingService.saveShipMethod(shipMethodDTO, shipZoneList, shipRateList, shipZoneDelIdList, shipRateDelIdList, userId);
			sysSetResponse.setShipMethodId(shipMethodId);
			sysSetResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101", userId);
			sysSetResponse.setHasException(Boolean.TRUE);
			sysSetResponse.setWsException(exDTO);
			logger.error(e.getLocalizedMessage());
		}
		return sysSetResponse;
	}

}
