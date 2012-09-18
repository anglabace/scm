package com.genscript.gsscm.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.basedata.dto.LocationDTO;
import com.genscript.gsscm.basedata.dto.MailContentTemplateDTO;
import com.genscript.gsscm.basedata.dto.OptionItemDTO;
import com.genscript.gsscm.basedata.dto.PbDropdownListOptionsDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.dto.SearchDTO;
import com.genscript.gsscm.basedata.dto.StateDTO;
import com.genscript.gsscm.basedata.dto.ZipCodeDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.entity.PbSearch;
import com.genscript.gsscm.basedata.entity.PbSearchAttribute;
import com.genscript.gsscm.basedata.entity.PbSearchCondition;
import com.genscript.gsscm.basedata.entity.ZipCode;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.basedata.service.SearchService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.entity.ContentTemplate;
import com.genscript.gsscm.customer.dto.DeptDTO;
import com.genscript.gsscm.customer.dto.DivisionDTO;
import com.genscript.gsscm.customer.dto.OrganizationDTO;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.order.entity.MessageTemplate;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.quoteorder.dto.ChangeCurrencyDTO;
import com.genscript.gsscm.quoteorder.dto.SearchORFCloneGeneDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.PublicWebService;
import com.genscript.gsscm.ws.request.PublicRequest;
import com.genscript.gsscm.ws.response.PublicResponse;

@WebService(serviceName = "PublicService", portName = "PublicServicePort", endpointInterface = "com.genscript.gsscm.ws.api.PublicWebService", targetNamespace = WsConstants.NS)
public class PublicWebServiceImpl implements PublicWebService {

	// private static Logger logger =
	// LoggerFactory.getLogger(PublicWebServiceImpl.class);
	private final static String SEPARATE = ",";

	@Autowired
	private PublicService publicService;

	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private SearchService searchService;

	@Autowired
	private ExceptionService exceptionUtil;

	@Override
	public PublicResponse getDropdownListOptions(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		String optionsNames = publicRequest.getOptionsNames();
		String[] listNamesArray = StringUtils.split(optionsNames, SEPARATE);
		List<OptionItemDTO> itemsDTOList = new ArrayList<OptionItemDTO>();
		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(optionsNames, "list name can't be null");
			for (String listName : listNamesArray) {
				List<PbDropdownListOptions> pbOptionItemList = publicService
						.getDropdownList(listName);
				List<PbDropdownListOptionsDTO> pbOptionItemDTOList = new ArrayList<PbDropdownListOptionsDTO>();
				for (PbDropdownListOptions pbOptionItem : pbOptionItemList) {
					pbOptionItemDTOList.add(dozer.map(pbOptionItem,
							PbDropdownListOptionsDTO.class));
				}
				OptionItemDTO dto = new OptionItemDTO();
				dto.setName(listName);
				dto.setOptions(pbOptionItemDTOList);
				itemsDTOList.add(dto);
			}
			publicResponse.setItemsDTO(itemsDTOList);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1202", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.genscript.gsscm.ws.api.CustomerWebService#getCustSrchAttrList()
	 */
	@Override
	public PublicResponse getSrchAttrList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<PbSearchAttribute> list = this.searchService
					.getSearchAtrbList(publicRequest.getSearchType());
			String dataSrcCode;
			List<SearchAttributeDTO> attrList = new ArrayList<SearchAttributeDTO>();
			for (PbSearchAttribute attr : list) {
				dataSrcCode = attr.getDataSrcCode();
				List<DropDownDTO> dropDownDTOs = new ArrayList<DropDownDTO>();
				if (dataSrcCode != null) {
					dropDownDTOs = publicService
							.getSpecDropDownList(SpecDropDownListName
									.fromValue(dataSrcCode));
				}
				SearchAttributeDTO attrDto = dozer.map(attr,
						SearchAttributeDTO.class);
				attrDto.setDropDownDTOs(dropDownDTOs);
				attrList.add(attrDto);
			}
			publicResponse.setSrchAttrList(attrList);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1208", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.CustomerWebService#saveCustSrch(com.genscript
	 * .gsscm.ws.request.CustomerRequest)
	 */
	@Override
	public PublicResponse saveMySrch(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		System.out.println(publicRequest);
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			PbSearchCondition[] conditions = publicRequest.getSrchCondition();
			PbSearch mySearch = publicRequest.getMySearch();
			mySearch.setCreatedBy(userId);
			mySearch.setOwner(userId);
			this.searchService.saveMySearch(mySearch,
					publicRequest.getSearchType(), conditions);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1214", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	/**
	 * 
	 * @param publicRequest
	 * @return
	 */
	@Override
	public PublicResponse getMySrchList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchDTO> srchDTOList = this.searchService.getMySearchList(
					publicRequest.getSearchType(), publicRequest.getUserId());
			Integer maxSrchCount = this.getMySrchMaxCount();
			publicResponse.setMySrchMaxCount(maxSrchCount);
			publicResponse.setMySearchList(srchDTOList);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1201", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.CustomerWebService#delCustMySrch(com.genscript
	 * .gsscm.ws.request.CustomerRequest)
	 */
	@Override
	public PublicResponse delMySrch(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			Integer ownerId = publicRequest.getUserId();
			Integer searchId = publicRequest.getParamId();
			this.searchService.delMySearch(searchId, ownerId);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1215", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	private Integer getMySrchMaxCount() {
		return 6;
	}

	@Override
	public PublicResponse getSpecDropDownList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<DropDownListDTO> list = publicService
					.getSpecDropDownList(publicRequest
							.getSpecDropDownListNames());
			publicResponse.setDropDownListDTOs(list);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1203", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getCountryList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<CountryDTO> dtoList = this.publicService.getCountryList();
			publicResponse.setCountryList(dtoList);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1204", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getStateList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			String countryCode = publicRequest.getCountryCode();
			List<StateDTO> dtoList = this.publicService
					.getStateListByCountry(countryCode);
			publicResponse.setStateList(dtoList);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1205", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getTerritoryByCountry(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		List<GetInfoByRegionDTO> getInfoByRegionDTOs;
		try {
			Assert.notNull(userId, "user id can't be null");
			String countryCode = publicRequest.getCountryCode();
			String regionLevel = publicRequest.getRegionLevel();
//			getInfoByRegionDTOs = publicService.getTerritoryByCountry(
//					regionLevel, countryCode, null, null, null);
//			publicResponse.setGetInfoByRegionDTOs(getInfoByRegionDTOs);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1210", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getSourceDetail(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();

		List<Source> sourceDetail;
		try {
			Assert.notNull(userId, "user id can't be null");
			sourceDetail = publicService.getSourceDetail();
			publicResponse.setSources(sourceDetail);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1212", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getContactInfo(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		List<DropDownListDTO> list;
		Integer custNo = publicRequest.getCustNo();
		String addrType = publicRequest.getAddrType();
		if (addrType == null) {
			addrType = "CONTACT";
		}

		try {
			Assert.notNull(userId, "user id can't be null");
			Assert.notNull(custNo, "customerNo can't be null");
			list = publicService.getContactInfo(addrType, custNo);
			publicResponse.setDropDownListDTOs(list);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1211", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getDepartmentList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			String name = publicRequest.getSearchValue();
			Integer orgId = publicRequest.getOrgId();
			Integer divId = publicRequest.getDivisionId();
			PageDTO pagerParm = publicRequest.getPagerParm();
			Page<DeptDTO> pageDeptList = this.publicService.getDeptList(
					pagerParm, name, orgId, divId);
			PageDTO pagerInfo = (PageDTO) dozer
					.map(pageDeptList, PageDTO.class);
			publicResponse.setDeptList(pageDeptList.getResult());
			publicResponse.setPagerDTO(pagerInfo);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1207", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getDivisionList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			String name = publicRequest.getSearchValue();
			Integer orgId = publicRequest.getOrgId();
			PageDTO pagerParm = publicRequest.getPagerParm();
			Page<DivisionDTO> pageDivList = this.publicService.getDivisionList(
					pagerParm, name, orgId);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageDivList, PageDTO.class);
			publicResponse.setDivisionList(pageDivList.getResult());
			publicResponse.setPagerDTO(pagerInfo);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1213", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getOrganizationList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			String name = publicRequest.getSearchValue();
			PageDTO pagerParm = publicRequest.getPagerParm();
			@SuppressWarnings("unchecked")
			Page<OrganizationDTO> orgPageDTo = dozer.map(pagerParm, Page.class);
			Page<OrganizationDTO> pagerOrgList = this.publicService
					.getOrganizationList(orgPageDTo, null);
			PageDTO pagerInfo = (PageDTO) dozer
					.map(pagerOrgList, PageDTO.class);
			publicResponse.setOrgList(pagerOrgList.getResult());
			publicResponse.setPagerDTO(pagerInfo);
			publicResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1202", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getContentTmplList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		try {
			Assert.notNull(userId, "user id can't be null");
			List<ContentTemplate> contentTmplList = this.publicService
					.getContentTmplList();
			publicResponse.setContentTmplList(contentTmplList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1206", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getAllCountryState(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		List<AllCountryDTO> allCountryDTOs = new ArrayList<AllCountryDTO>();
		try {
			Assert.notNull(userId, "user id can't be null");
			allCountryDTOs = publicService.getAllCountryState();
			publicResponse.setAllCountryDTOs(allCountryDTOs);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1204", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getDeptLocation(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			Integer paramId = publicRequest.getParamId();
			Departments dept = this.publicService.getDeptLocation(paramId);
			LocationDTO location = dozer.map(dept, LocationDTO.class);
			publicResponse.setLocation(location);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1204", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getDivLocation(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			Integer paramId = publicRequest.getParamId();
			Divisions div = this.publicService.getDivLocation(paramId);
			LocationDTO location = dozer.map(div, LocationDTO.class);
			publicResponse.setLocation(location);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1204", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getOrgLocation(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			Integer paramId = publicRequest.getParamId();
			Organization org = this.publicService.getOrgLocation(paramId);
			LocationDTO location = dozer.map(org, LocationDTO.class);
			publicResponse.setLocation(location);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1204", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getMessageTmplList(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			List<MessageTemplate> tmplList = this.publicService
					.getMessageTmplList();
			publicResponse.setMessageTmplList(tmplList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getTemplateNameList(PublicRequest publicRequest) {
		PublicResponse publicResponse = new PublicResponse();
		Integer userId = publicRequest.getUserId();
		TemplateType templateType = publicRequest.getTemplateType();
		List<DropDownListDTO> dropDownDTOList;
		try {
			Assert.notNull(userId, "user id can't be null");

			dropDownDTOList = publicService.getTemplateNameList(templateType,
					userId);
			publicResponse.setDropDownListDTOs(dropDownDTOList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF1210", userId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	/**
	 * 根据CountryCode list或（和）stateCode list获得zipCode list.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PublicResponse getZipCodeList(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			List<String> countryCodeList = publicRequest.getCountryCodeList();
			List<String> stateCodeList = publicRequest.getStateCodeList();
			PageDTO pageDTO = publicRequest.getPagerParm();
			Page<ZipCode> page = dozer.map(pageDTO, Page.class);
			Page<ZipCodeDTO> pageZipCode = this.publicService.getZipCodeList(
					page, countryCodeList, stateCodeList);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageZipCode, PageDTO.class);
			publicResponse.setPagerDTO(pagerInfo);
			publicResponse.setZipCodeList(pageZipCode.getResult());
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	/**
	 * 获得Order, Quote, OrderItem , OrderItem从现有状态可以更新到status的列表.
	 */
	@Override
	public PublicResponse getStatusDropDownList(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			Assert.notNull(loginUserId, "user id can't be null");
			String type = publicRequest.getStatusType();
			String code = publicRequest.getStatusCode();
			String orderStatus = publicRequest.getOrderStatus();
			List<StatusList> statusLists = publicService
					.getOrderOrQuoteStatusList(type, code, orderStatus);
			publicResponse.setStatusLists(statusLists);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getPrmtCdBySource(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		Integer sourceId = publicRequest.getSourceId();
		Date currentDate = publicRequest.getCurrentDate();
		try {
			Assert.notNull(loginUserId, "user id can't be null");
			Assert.notNull(currentDate, "current date can't be null");
			List<DropDownDTO> dropDownDTOList = publicService
					.getPrmtCdBySource(sourceId, currentDate);
			publicResponse.setPromotionDTOList(dropDownDTOList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse isPromotionExist(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		String promotionCode = publicRequest.getPromotionCode();
		Date currentDate = publicRequest.getCurrentDate();
		try {
			Assert.notNull(loginUserId, "user id can't be null");
			Assert.notNull(promotionCode, "promotion code can't be null");
			Assert.notNull(currentDate, "current date can't be null");
			Integer promotionExistFlag = publicService.isPromotionExist(
					promotionCode, currentDate);
			publicResponse.setPromotionExistFlag(promotionExistFlag);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getCustMailContentTemplateList(
			PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			Assert.notNull(loginUserId, "user id can't be null");

//			List<MailContentTemplateDTO> templateDTOList = publicService
//					.getCustMailContentTemplateList();
//			publicResponse.setMailContentTemplateDTOList(templateDTOList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse getOrderMailContentTemplateList(
			PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		try {
			Assert.notNull(loginUserId, "user id can't be null");

			List<MailContentTemplateDTO> templateDTOList = publicService
					.getOrderMailContentTemplateList();
			publicResponse.setMailContentTemplateDTOList(templateDTOList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse changeCurrency(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		List<ChangeCurrencyDTO> currencyDTOList = publicRequest
				.getChangeCurrencyDTOList();
		try {
			Assert.notNull(loginUserId, "user id can't be null");

			List<ChangeCurrencyDTO> changeCurrencyDTOList = publicService
					.changeCurrency(currencyDTOList);
			publicResponse.setChangeCurrencyDTOList(changeCurrencyDTOList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse searchORFCloneGene(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		String searchORFCloneType = publicRequest.getSearchORFCloneType();
		String searchORFCloneValue = publicRequest.getSearchORFCloneValue();
		try {
			Assert.notNull(loginUserId, "user id can't be null");
			List<SearchORFCloneGeneDTO> searchORFCloneDTOList = publicService
					.searchORFCloneGene(searchORFCloneType, searchORFCloneValue);
			publicResponse.setSearchORFCloneDTOList(searchORFCloneDTOList);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF1206", loginUserId);
			publicResponse.setHasException(true);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

	@Override
	public PublicResponse calculateServicePrice(PublicRequest publicRequest) {
		Integer loginUserId = publicRequest.getUserId();
		PublicResponse publicResponse = new PublicResponse();
		ServiceItemPiceDTO orderServiceItemPiceDTO = publicRequest
				.getServiceItemPiceDTO();
		try {
			Assert.notNull(loginUserId, "user id can't be null");
			Assert.notNull(orderServiceItemPiceDTO,
					"orderServiceItemPiceDTO can't be null");
			Assert.notNull(orderServiceItemPiceDTO.getCatalogNo(),
					"catalog no can't be null");
			Assert.notNull(orderServiceItemPiceDTO.getCatalogId(),
					"catalog id can't be null");
			Assert.notNull(orderServiceItemPiceDTO.getServicePriceType(),
					"service price type can't be null");
			ServiceItemPiceDTO servicePriceDTO = publicService
					.calculateServicePrice(orderServiceItemPiceDTO);
			publicResponse.setServicePriceDTO(servicePriceDTO);
			publicResponse.setHasException(Boolean.FALSE);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0101", loginUserId);
			publicResponse.setHasException(Boolean.TRUE);
			publicResponse.setWsException(exDTO);
		}
		return publicResponse;
	}

}
