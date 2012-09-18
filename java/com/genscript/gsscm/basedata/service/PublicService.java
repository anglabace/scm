package com.genscript.gsscm.basedata.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.PbDropdownListDao;
import com.genscript.gsscm.basedata.dao.PbLanguageDao;
import com.genscript.gsscm.basedata.dao.PbStateDao;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dao.ZipCodeDao;
import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.CountryDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.basedata.dto.MailContentTemplateDTO;
import com.genscript.gsscm.basedata.dto.StateDTO;
import com.genscript.gsscm.basedata.dto.ZipCodeDTO;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbDropdownList;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.entity.PbLanguage;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.basedata.entity.ZipCode;
import com.genscript.gsscm.biolib.dao.PeptideModificationDao;
import com.genscript.gsscm.biolib.entity.PeptideModification;
import com.genscript.gsscm.common.BioInfoService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.ORFCloneSearchType;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.PeptideModificationType;
import com.genscript.gsscm.common.constant.SalesRepSalesRole;
import com.genscript.gsscm.common.constant.ServicePriceType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.ContentTemplate;
import com.genscript.gsscm.customer.dao.AddressDao;
import com.genscript.gsscm.customer.dao.BankDao;
import com.genscript.gsscm.customer.dao.ContactAddressDao;
import com.genscript.gsscm.customer.dao.ContentTemplateDao;
import com.genscript.gsscm.customer.dao.DepartmentDao;
import com.genscript.gsscm.customer.dao.DivisionDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.SalesGroupDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dao.SalesResourceAssignOrgDao;
import com.genscript.gsscm.customer.dao.SalesResourceAssignTerritoryDao;
import com.genscript.gsscm.customer.dao.SalesTerritoryAssignmentDao;
import com.genscript.gsscm.customer.dao.SalesTerritoryDao;
import com.genscript.gsscm.customer.dao.SourceDao;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.DeptDTO;
import com.genscript.gsscm.customer.dto.DivisionDTO;
import com.genscript.gsscm.customer.dto.OrganizationDTO;
import com.genscript.gsscm.customer.entity.Bank;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.SalesGroup;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.entity.SalesResourceAssignOrg;
import com.genscript.gsscm.customer.entity.SalesResourceAssignTerritory;
import com.genscript.gsscm.customer.entity.SalesTerritory;
import com.genscript.gsscm.customer.entity.SalesTerritoryAssignment;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.olddb.dao.ChargerDao;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.MessageTemplateDao;
import com.genscript.gsscm.order.dao.PromotionDao;
import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.entity.MessageTemplate;
import com.genscript.gsscm.order.entity.OrderMailContentTemplate;
import com.genscript.gsscm.order.entity.Promotion;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.privilege.dao.EmployeeDao;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.GeneDao;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Gene;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.ChangeCurrencyDTO;
import com.genscript.gsscm.quoteorder.dto.EnzymeDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;
import com.genscript.gsscm.quoteorder.dto.SearchORFCloneGeneDTO;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.system.dao.CompanyDao;
import com.genscript.gsscm.system.dao.DepartmentSystemDao;
import com.genscript.gsscm.system.dao.MailGroupDao;
import com.genscript.gsscm.system.dao.MailTemplatesDao;
import com.genscript.gsscm.system.dto.MailTemplatesDTO;
import com.genscript.gsscm.system.entity.DepartmentSystem;
import com.genscript.gsscm.system.entity.MailGroup;
import com.genscript.gsscm.system.entity.MailTemplates;

@Service
@Transactional
public class PublicService {
	private final static String SEPARATE = ";";
	@Autowired
	private PbDropdownListDao pbDropdownListDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private PbStateDao pbStateDao;
	@Autowired
	private SourceDao sourceDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private DivisionDao divisionDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ContentTemplateDao contentTemplateDao;
	@Autowired
	private MailTemplatesDao mailTemplatesDao;
	@Autowired
	private MessageTemplateDao messageTemplateDao;
	@Autowired
	private ZipCodeDao zipCodeDao;
	@Autowired
	private StatusListDao statusListDao;
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private GeneDao geneDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private com.genscript.gsscm.order.dao.OrderMailContentTemplateDao orderMailContentTplDao;
	@Autowired
	private MailGroupDao mailGroupDao;
	@Autowired
	private SalesGroupDao salesGroupDao;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	@Autowired
	private BioInfoService bioInfoService;
	@Autowired
	private PbLanguageDao pbLanguageDao;
	@Autowired
	private SalesResourceAssignOrgDao salesResourceAssignOrgDao;
	@Autowired
	private SalesResourceAssignTerritoryDao salesResourceAssignTerritoryDao;
	@Autowired
	private BankDao bankDao;
	@Autowired
	private SalesTerritoryDao salesTerritoryDao;
	@Autowired
	private ContactAddressDao contactAddressDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private SalesTerritoryAssignmentDao salesTerritoryAssignmentDao;
	@Autowired
	private PeptideModificationDao peptideModificationDao;
	@Autowired
	private DepartmentSystemDao departmentSystemDao;
	@Autowired
	private UserDao userDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ChargerDao chargerDao;

	/**
	 * check org for 是否已经存在了。
	 * 
	 * @param orgId
	 * @return true or false
	 */
	public boolean checkOrgs(Integer orgId) {
		return organizationDao.checkOrgName(orgId);
	}

	@Transactional(readOnly = true)
	public List<PbDropdownListOptions> getDropdownList(String listName) {
		PbDropdownList pbDropdownList = pbDropdownListDao.findUniqueBy(
				"listName", listName);
		if (pbDropdownList != null && pbDropdownList.getPbOptionItems() != null) {
			return pbDropdownList.getPbOptionItems();
		}
		return null;
	}

	@Transactional(readOnly = true)
	public PbDropdownListOptions getDropdownOpt(String listName, String optValue) {
		PbDropdownList pbDropdownList = pbDropdownListDao.findUniqueBy(
				"listName", listName);
		if (pbDropdownList != null && pbDropdownList.getPbOptionItems() != null) {
			for (PbDropdownListOptions option : pbDropdownList
					.getPbOptionItems()) {
				if (option.getValue().equals(optValue)) {
					return option;
				}
			}
		}
		return null;
	}

	/**
	 * get special Drop Down List except common Drop Down List
	 * 
	 * @param names
	 * @return the List of DropDownListDTO
	 */
	@Transactional(readOnly = true)
	public List<DropDownListDTO> getSpecDropDownList(
			List<SpecDropDownListName> names) {
		List<DropDownListDTO> dropDownList = new ArrayList<DropDownListDTO>();
		if (names != null) {
			for (SpecDropDownListName specDropDownListName : names) {
				List<DropDownDTO> list = specDropDownListDao
						.getSpecDropDownList(specDropDownListName.value());
				DropDownListDTO dropDownListDTO = new DropDownListDTO();
				dropDownListDTO.setName(specDropDownListName.value());
				dropDownListDTO.setDropDownDTOs(list);
				dropDownList.add(dropDownListDTO);
			}

			return dropDownList;
		} else {
			return null;
		}
	}

	/**
	 * get special Drop Down List except common Drop Down List
	 * 
	 * @param names
	 * @return the Map of DropDownListDTO
	 * @author zouyulu
	 */
	@Transactional(readOnly = true)
	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownMap(
			List<SpecDropDownListName> names) {
		Map<SpecDropDownListName, DropDownListDTO> dropDownList = new HashMap<SpecDropDownListName, DropDownListDTO>();
		if (names != null) {
			for (SpecDropDownListName specDropDownListName : names) {
				List<DropDownDTO> list = specDropDownListDao
						.getSpecDropDownList(specDropDownListName.value());
				DropDownListDTO dropDownListDTO = new DropDownListDTO();
				dropDownListDTO.setName(specDropDownListName.value());
				dropDownListDTO.setDropDownDTOs(list);
				dropDownList.put(specDropDownListName, dropDownListDTO);
			}
			return dropDownList;
		} else {
			return null;
		}
	}

	/**
	 * 根据一组Dropdown name, 获得对应的list, 而组成的Map.
	 * 
	 * @param names
	 * @return
	 */
	@Transactional(readOnly = true)
	public Map<String, List<PbDropdownListOptions>> getDropDownMap(
			List<DropDownListName> names) {
		Map<String, List<PbDropdownListOptions>> dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		if (names != null) {
			for (DropDownListName listName : names) {
				List<PbDropdownListOptions> optionsList = this
						.getDropdownList(listName.value());
				if(optionsList==null) {
					optionsList = new ArrayList<PbDropdownListOptions>();
				}
				dropDownMap.put(listName.value(), optionsList);
			}
			return dropDownMap;
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<DropDownListDTO> getTemplateNameList(TemplateType templateType,
			Integer userId) {
		if (templateType != null && userId != null) {
			List<DropDownListDTO> dropDownList = new ArrayList<DropDownListDTO>();
			List<DropDownDTO> list = specDropDownListDao.getTemplateNameList(
					templateType, userId);
			DropDownListDTO dropDownListDTO = new DropDownListDTO();
			dropDownListDTO.setName(templateType.value());
			dropDownListDTO.setDropDownDTOs(list);
			dropDownList.add(dropDownListDTO);
			return dropDownList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<String> getAllCompanyName() {
		return companyDao.getAllCompanyName();
	}

	@Transactional(readOnly = true)
	public List<DropDownDTO> getPickLocationList(Integer warehouseId) {
		return specDropDownListDao.getPickLocationList(warehouseId);
	}

	@Transactional(readOnly = true)
	public List<DropDownDTO> getSpecDropDownList(SpecDropDownListName name) {
		List<DropDownDTO> list = new ArrayList<DropDownDTO>();
		if (name != null) {
			// try {
			list = specDropDownListDao.getSpecDropDownList(name.value());
			// } catch (Exception e) {
			// throw new RuntimeException("Drop down list name invaild!");
			// }
			return list;
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<AllCountryDTO> getAllCountryState() {
		return specDropDownListDao.getAllCountryState();
	}

	/**
	 * Get all country List;
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<CountryDTO> getCountryList() {
		List<CountryDTO> dtoList = new ArrayList<CountryDTO>();
		List list = this.pbCountryDao.getListWithState();
		for (int i = 0; list != null && i < list.size(); i++) {
			Object[] strs = (Object[]) list.get(i);
			CountryDTO dto = new CountryDTO();
			dto.setCountryCode(strs[0].toString());
			dto.setName(strs[1].toString());
			dto.setStateCount(Integer.parseInt(strs[2].toString()));
			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * Get a Country's State List.
	 * 
	 * @param countryCode
	 * @return
	 */
	public List<StateDTO> getStateListByCountry(String countryCode) {
		List<StateDTO> dtoList = new ArrayList<StateDTO>();
		PbCountry pbCountry = this.pbCountryDao.findUniqueBy("countryCode",
				countryCode);
		List<PbState> pbList = null;
		if (pbCountry != null) {
			pbList = this.pbStateDao.getListByCountry(pbCountry.getCountryId());
		} else {
			pbList = this.pbStateDao.getAll();
		}
		for (PbState state : pbList) {
			StateDTO dto = new StateDTO();
			dto.setStateCode(state.getStateCode());
			dto.setName(state.getName());
			dtoList.add(dto);
		}
		return dtoList;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<GetInfoByRegionDTO> getTerritoryByCustNo(String type,
			String custNo, String sessCustNo) {
		if (type == null || StringUtils.isEmpty(type)) {
			return null;
		}
		if (!("customer").equals(type) && !("contact").equals(type)) {
			return null;
		}

		if ((StringUtils.isNotEmpty(custNo) && StringUtils.isNumeric(custNo))
				&& Integer.parseInt(custNo) != 0) {
			sessCustNo = custNo;
		}
		if (sessCustNo == null || StringUtils.isEmpty(sessCustNo)) {
			return null;
		}
		Integer defTerrId = null;
		Integer orgId = null;
		String countryCode = null;
		String stateCode = null;
		String zipCode = null;
		// 获得默认并且是ACTIVE状态的ship to address
		if (("contact").equals(type)) {
			// 从session中获得contact信息
			ContactModelDTO sessContact = (ContactModelDTO) SessionUtil.getRow(
					SessionConstant.Contact.value(), sessCustNo);
			if (sessContact != null) {
				defTerrId = sessContact.getSalesTerritory();
			}
			// 获得customer Address
			Map<String, ContactAddressDTO> sessAddrMap = (Map<String, ContactAddressDTO>) SessionUtil
					.getRow(SessionConstant.ContactAddress.value(), sessCustNo);
			if (sessAddrMap != null && sessAddrMap.size() > 0) {
				ContactAddressDTO shipToAddr = null;
				for (String contactAddrId : sessAddrMap.keySet()) {
					ContactAddressDTO addrDto = sessAddrMap.get(contactAddrId);
					if (AddressType.SHIP_TO.value().equals(
							addrDto.getAddrType())
							&& ("Y").equals(addrDto.getDefaultFlag())
							&& StatusType.ACTIVE.value().equals(
									addrDto.getStatus())) {
						shipToAddr = addrDto;
						break;
					}
				}
				// 如果默认的ship to address为空则获取第一个ACTIVE状态的ship to address
				if (shipToAddr == null) {
					for (String contactAddrId : sessAddrMap.keySet()) {
						ContactAddressDTO addrDto = sessAddrMap
								.get(contactAddrId);
						if (AddressType.SHIP_TO.value().equals(
								addrDto.getAddrType())
								&& StatusType.ACTIVE.value().equals(
										addrDto.getStatus())) {
							shipToAddr = addrDto;
							break;
						}
					}
				}
				if (shipToAddr != null) {
					// orgId = shipToAddr.getOrganization() != null ?
					// shipToAddr.getOrganization().getOrgId() : null;
					orgId = shipToAddr.getOrgId() != null ? shipToAddr
							.getOrgId() : null;
					countryCode = shipToAddr.getCountry();
					stateCode = shipToAddr.getState();
					zipCode = shipToAddr.getZipCode();
				}
			}
		} else if (("customer").equals(type)) {
			// 从session中获得customer信息
			CustomerDTO sessCustomer = (CustomerDTO) SessionUtil.getRow(
					"customer", sessCustNo);
			if (sessCustomer != null) {
				defTerrId = sessCustomer.getSalesTerritory();
			}
			// 获得customer Address
			List<CustAddrOperDTO> custAddrList = (List<CustAddrOperDTO>) SessionUtil
					.getRow(SessionConstant.CustAddressList.value(), sessCustNo);
			if (custAddrList == null || custAddrList.isEmpty()) {
				return null;
			}
			CustAddrOperDTO shipToAddr = null;
			for (CustAddrOperDTO addrDto : custAddrList) {
				if (AddressType.SHIP_TO.value().equals(addrDto.getAddrType())
						&& ("Y").equals(addrDto.getDefaultFlag())
						&& StatusType.ACTIVE.value()
								.equals(addrDto.getStatus())
						&& !OperationType.DEL.value().equals(
								addrDto.getOperateType())) {
					shipToAddr = addrDto;
					break;
				}
			}
			// 如果默认的ship to address为空则获取第一个ACTIVE状态的ship to address
			if (shipToAddr == null) {
				for (CustAddrOperDTO addrDto : custAddrList) {
					if (AddressType.SHIP_TO.value().equals(
							addrDto.getAddrType())
							&& StatusType.ACTIVE.value().equals(
									addrDto.getStatus())
							&& !OperationType.DEL.value().equals(
									addrDto.getOperateType())) {
						shipToAddr = addrDto;
						break;
					}
				}
			}
			if (shipToAddr != null) {
				orgId = shipToAddr.getOrgId() != null ? shipToAddr.getOrgId()
						: null;
				// orgId = shipToAddr.getOrganization() != null ?
				// shipToAddr.getOrganization().getOrgId() : null;
				countryCode = shipToAddr.getCountry();
				stateCode = shipToAddr.getState();
				zipCode = shipToAddr.getZipCode();
			}
		}
		List<GetInfoByRegionDTO> infoList = getReginInfoList(defTerrId, orgId,
				countryCode, stateCode, zipCode);
		return infoList;
	}

	@SuppressWarnings("unchecked")
	public List<GetInfoByRegionDTO> getReginInfoList(Integer defTerrId,
			Integer orgId, String countryCode, String stateCode, String zipCode) {
		GetInfoByRegionDTO getInfoByCustNo = null;
		Integer custGroupId = null;
		if (defTerrId != null) {
			SalesTerritory st = salesTerritoryDao.getById(defTerrId);
			if (st != null && st.getTerritoryId() != null) {
				getInfoByCustNo = new GetInfoByRegionDTO();
				getInfoByCustNo.setId(st.getTerritoryId().toString());
				getInfoByCustNo.setName(st.getTerritoryCode());
				getInfoByCustNo.setTerritoryClassification(st
						.getTerritoryClassification());
			}
		}
		// 优先根据organization 中来获取。
		List<GetInfoByRegionDTO> infoList = null;
		if (orgId != null) {
			Organization org = this.organizationDao.get(orgId);
			if (org != null && org.getOrgId() != null) {
				infoList = salesResourceAssignOrgDao.findTerrByOrgId(orgId,
						custGroupId);
			}
		}
		List<GetInfoByRegionDTO> infoListByCountry = specDropDownListDao
				.getTerritoryByCountry(countryCode, stateCode, zipCode);
		if (infoList == null || infoList.isEmpty()) {
			infoList = infoListByCountry;
			if (infoList == null || infoList.isEmpty()) {
				infoList = new ArrayList<GetInfoByRegionDTO>();
			}
		}
		boolean flg = false;
		if (getInfoByCustNo != null) {
			for (GetInfoByRegionDTO getInfoByRegionDTOByOrgId : infoList) {
				if (getInfoByRegionDTOByOrgId != null
						&& getInfoByRegionDTOByOrgId.getId() == getInfoByCustNo
								.getId()) {
					flg = true;
					break;
				}
			}
		} else {
			flg = true;
		}
		if (!flg) {
			infoList.add(getInfoByCustNo);
		}
		if (infoListByCountry != null && infoListByCountry.size() > 0) {
			Map<String, String> getInfoByRegionDTOByOrgMap = new HashMap<String, String>();
			for (GetInfoByRegionDTO getInfoByRegionDTOByOrgId : infoList) {
				getInfoByRegionDTOByOrgMap.put(
						getInfoByRegionDTOByOrgId.getId(), null);
			}
			for (GetInfoByRegionDTO getInfoByRegionDTOByCountry : infoListByCountry) {
				if (!getInfoByRegionDTOByOrgMap
						.containsKey(getInfoByRegionDTOByCountry.getId())) {
					infoList.add(getInfoByRegionDTOByCountry);
				}
			}
		}
		if (infoList == null || infoList.isEmpty()) {
			infoList = null;
		} else {
			infoList = StringUtil.removeDuplicateWithOrder(infoList);
		}
		return infoList;
	}

	@Transactional(readOnly = true)
	public List<DropDownListDTO> getContactInfo(String addrType, Integer custNo) {
		return specDropDownListDao.getContactInfo(addrType, custNo);
	}

	@Transactional(readOnly = true)
	public List<Source> getSourceDetail() {
		return sourceDao.getAll();
	}

	// -------------- add by zhou gang ----//

	public Source getSourceById(Integer sourceId) {

		return sourceDao.getSourceDetail(sourceId);
	}

	// ---------------------//

	@SuppressWarnings("unchecked")
	public Page<OrganizationDTO> getOrganizationList(Page<OrganizationDTO> pagerDTO, List<PropertyFilter> filters) {
		Page<OrganizationDTO> orgDTOPager = new Page<OrganizationDTO>();
		Page<Organization> orgPager = dozer.map(pagerDTO, Page.class);
		orgPager.setOrderBy("orgGroupId");
		if (filters == null || filters.isEmpty()) {
			filters = new ArrayList<PropertyFilter>();
		}
		filters.add(new PropertyFilter("EQI_orgGroupId", 1));
		orgPager = this.organizationDao.findPage(orgPager, filters);
		List<Organization> orgList = orgPager.getResult();
		List<OrganizationDTO> dtoList = new ArrayList<OrganizationDTO>();
		for (Organization org : orgList) {
			OrganizationDTO dto = dozer.map(org, OrganizationDTO.class);
			dtoList.add(dto);
		}
		orgPager.setResult(null);
		orgDTOPager = dozer.map(orgPager, Page.class);
		orgDTOPager.setResult(dtoList);
		return orgDTOPager;
	}

	@SuppressWarnings("unchecked")
	public Page<DivisionDTO> getDivisionList(PageDTO pagerDTO, String name,
			Integer orgId) {
		Page<DivisionDTO> divDTOPager = new Page<DivisionDTO>();
		Page<Divisions> divPager = dozer.map(pagerDTO, Page.class);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (name == null || name.trim().length() < 1) {
		} else {
			PropertyFilter filter = new PropertyFilter("LIKES_name", name);
			filterList.add(filter);
		}
		if (orgId != null && orgId.intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_orgId", orgId);
			filterList.add(orgFilter);
		}
		divPager = this.divisionDao.findPage(divPager, filterList);
		List<Divisions> divList = divPager.getResult();
		List<DivisionDTO> dtoList = new ArrayList<DivisionDTO>();
		for (Divisions div : divList) {
			DivisionDTO dto = dozer.map(div, DivisionDTO.class);
			dtoList.add(dto);
		}
		divPager.setResult(null);
		divDTOPager = dozer.map(divPager, Page.class);
		divDTOPager.setResult(dtoList);
		return divDTOPager;
	}

	@SuppressWarnings("unchecked")
	public Page<DeptDTO> getDeptList(PageDTO pagerDTO, String name,
			Integer orgId, Integer divId) {
		Page<DeptDTO> deptDTOPager = new Page<DeptDTO>();
		Page<Departments> deptPager = dozer.map(pagerDTO, Page.class);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		if (name == null || name.trim().length() < 1) {
		} else {
			PropertyFilter filter = new PropertyFilter("LIKES_name", name);
			filterList.add(filter);
		}
		if (orgId != null && orgId.intValue() != 0) {
			PropertyFilter orgFilter = new PropertyFilter("EQI_orgId", orgId);
			filterList.add(orgFilter);
		}
		if (divId != null && divId.intValue() != 0) {
			PropertyFilter divFilter = new PropertyFilter("EQI_divisionId",
					divId);
			filterList.add(divFilter);
		}
		deptPager = this.departmentDao.findPage(deptPager, filterList);
		List<Departments> deptList = deptPager.getResult();
		List<DeptDTO> dtoList = new ArrayList<DeptDTO>();
		for (Departments dept : deptList) {
			DeptDTO dto = dozer.map(dept, DeptDTO.class);
			dtoList.add(dto);
		}
		deptPager.setResult(null);
		deptDTOPager = dozer.map(deptPager, Page.class);
		deptDTOPager.setResult(dtoList);
		return deptDTOPager;
	}

	/**
	 * Order|Quote Note中获得Content template list.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MailContentTemplateDTO> getContentTmplDTOList() {
		List<ContentTemplate> templateList = contentTemplateDao.getAll();
		if (templateList != null && templateList.size() > 0) {
			List<MailContentTemplateDTO> templateDTOList = new ArrayList<MailContentTemplateDTO>();
			for (ContentTemplate template : templateList) {
				templateDTOList.add(dozer.map(template,
						MailContentTemplateDTO.class));
			}
			return templateDTOList;
		}
		return null;
	}

	/**
	 * 获得mail_template 中Content template list.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MailTemplatesDTO> getMailTmplDTOList(String functionName) {
		List<MailTemplates> templateList = mailTemplatesDao.findBy(
				"functionName", functionName);
		if (templateList != null && templateList.size() > 0) {
			List<MailTemplatesDTO> templateDTOList = new ArrayList<MailTemplatesDTO>();
			for (MailTemplates template : templateList) {
				MailTemplatesDTO mailTemplatesDTO = dozer.map(template,
						MailTemplatesDTO.class);
				templateDTOList.add(mailTemplatesDTO);
			}
			return templateDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<ContentTemplate> getContentTmplList() {
		return this.contentTemplateDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<MessageTemplate> getMessageTmplList() {
		return this.messageTemplateDao.getAll();
	}

	public Organization getOrgLocation(Integer orgId) {
		Organization org = this.organizationDao.get(orgId);
		return org;
	}

	public Divisions getDivLocation(Integer divId) {
		Divisions div = this.divisionDao.get(divId);
		return div;
	}

	public Departments getDeptLocation(Integer deptId) {
		Departments dept = this.departmentDao.get(deptId);
		return dept;
	}

	/**
	 * 根据Country和State获得ZipCode list.
	 * 
	 * @param countryCodeList
	 * @param stateCodeList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ZipCodeDTO> getZipCodeList(Page<ZipCode> page,
			List<String> countryCodeList, List<String> stateCodeList) {
		Page<ZipCodeDTO> retPage = new Page<ZipCodeDTO>();
		page = this.zipCodeDao.getZipCodeList(page, countryCodeList,
				stateCodeList);
		Map<String, String> countryMap = new HashMap<String, String>();
		Map<String, String> stateMap = new HashMap<String, String>();
		List<ZipCodeDTO> dtoList = new ArrayList<ZipCodeDTO>();
		if (page.getResult() != null) {
			for (ZipCode zipCode : page.getResult()) {
				ZipCodeDTO dto = this.dozer.map(zipCode, ZipCodeDTO.class);
				if (countryMap.containsKey(zipCode.getCountry())) {
					dto.setCountryName(countryMap.get(zipCode.getCountry()));
				} else {
					PbCountry country = this.pbCountryDao.findUniqueBy(
							"countryCode", zipCode.getCountry());
					if (country != null) {
						dto.setCountryName(country.getName());
					}
					countryMap.put(country.getCountryCode(), country.getName());
				}
				if (stateMap.containsKey(zipCode.getState())) {
					dto.setStateName(stateMap.get(zipCode.getState()));
				} else {
					PbState state = this.pbStateDao.findUniqueBy("stateCode",
							zipCode.getState());
					if (state != null) {
						dto.setStateName(state.getName());
					}
					stateMap.put(state.getStateCode(), state.getName());
				}
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	/**
	 * 获得Order, Quote, OrderItem , OrderItem从现有状态可以更新到status的列表.
	 * 
	 * @param type
	 * @param code
	 * @param orderStauts
	 * @return
	 */
	public List<StatusList> getOrderOrQuoteStatusList(final String type,
			final String code, final String orderStauts) {
		return statusListDao.getStatusDropDownList(type, code, orderStauts);
	}

	@Transactional(readOnly = true)
	public String getCurrencySymbol(String currencyCode) {
		return currencyDao.getCurrencySymbol(currencyCode);
	}

	@Transactional(readOnly = true)
	public List<DropDownDTO> getPrmtCdBySource(final Integer sourceId,
			final Date currentDate) {
		if (sourceId != null) {
			List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
			List<PropertyFilter> filter1List = new ArrayList<PropertyFilter>();
			PropertyFilter prmtFilter1 = new PropertyFilter("EQI_orderSource",
					sourceId);
			PropertyFilter prmtFilter2 = new PropertyFilter("EQS_status",
					"ACTIVE");
			PropertyFilter prmtFilter3 = new PropertyFilter("LED_orderEffFrom",
					currentDate);
			PropertyFilter prmtFilter4 = new PropertyFilter("GED_orderEffTo",
					currentDate);
			filter1List.add(prmtFilter1);
			filter1List.add(prmtFilter2);
			filter1List.add(prmtFilter3);
			filter1List.add(prmtFilter4);
			List<Promotion> promotionList = promotionDao.find(filter1List);
			if (promotionList != null && promotionList.size() > 0) {

				for (Promotion promotion : promotionList) {
					Integer prmtId = promotion.getPrmtId();
					String prmtCode = promotion.getPrmtCode();
					DropDownDTO dropDownDTO = new DropDownDTO();
					dropDownDTO.setId(prmtId.toString());
					dropDownDTO.setName(prmtCode);
					dropDownDTOList.add(dropDownDTO);
				}

			}
			List<PropertyFilter> filter2List = new ArrayList<PropertyFilter>();
			PropertyFilter pFilter1 = new PropertyFilter("NULLI_orderSource",
					null);
			PropertyFilter pFilter2 = new PropertyFilter("EQS_status", "ACTIVE");
			PropertyFilter pFilter3 = new PropertyFilter("LED_orderEffFrom",
					currentDate);
			PropertyFilter pFilter4 = new PropertyFilter("GED_orderEffTo",
					currentDate);
			filter2List.add(pFilter1);
			filter2List.add(pFilter2);
			filter2List.add(pFilter3);
			filter2List.add(pFilter4);
			List<Promotion> nullPromotionList = promotionDao.find(filter2List);
			if (nullPromotionList != null && nullPromotionList.size() > 0) {
				for (Promotion promotion : nullPromotionList) {
					Integer prmtId = promotion.getPrmtId();
					String prmtCode = promotion.getPrmtCode();
					DropDownDTO dropDownDTO = new DropDownDTO();
					dropDownDTO.setId(prmtId.toString());
					dropDownDTO.setName(prmtCode);
					dropDownDTOList.add(dropDownDTO);
				}
			}
			return dropDownDTOList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Integer isPromotionExist(final String promotionCode,
			final Date currentDate) {
		List<PropertyFilter> filter1List = new ArrayList<PropertyFilter>();
		PropertyFilter prmtFilter1 = new PropertyFilter("EQS_prmtCode",
				promotionCode);
		PropertyFilter prmtFilter2 = new PropertyFilter("EQS_status", "ACTIVE");
		PropertyFilter prmtFilter3 = new PropertyFilter("LED_orderEffFrom",
				currentDate);
		PropertyFilter prmtFilter4 = new PropertyFilter("GED_orderEffTo",
				currentDate);
		filter1List.add(prmtFilter1);
		filter1List.add(prmtFilter2);
		filter1List.add(prmtFilter3);
		filter1List.add(prmtFilter4);
		List<Promotion> promotionList = promotionDao.find(filter1List);
		if (promotionList != null && promotionList.size() > 0) {
			for (Promotion promotion : promotionList) {
				return promotion.getPrmtId();
			}
		}
		return 0;
	}

	@Transactional(readOnly = true)
	public List<MailContentTemplateDTO> getOrderMailContentTemplateList() {
		List<OrderMailContentTemplate> templateList = orderMailContentTplDao
				.getAll();
		if (templateList != null && templateList.size() > 0) {
			List<MailContentTemplateDTO> templateDTOList = new ArrayList<MailContentTemplateDTO>();
			for (OrderMailContentTemplate template : templateList) {
				templateDTOList.add(dozer.map(template,
						MailContentTemplateDTO.class));
			}
			return templateDTOList;
		}
		return null;
	}

	/**
	 * Order/Quote 的instruction部分发邮件时， 获得已配置的用户邮件地址 modify by Zhang Yong
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MailGroup> getMailGroupList(String groupFunction) {
		if (StringUtils.isNotBlank(groupFunction)) {
			return this.mailGroupDao.findByGroupFunction(groupFunction);
		}
		return null;
	}

	/**
	 * 获得系统中所有的Currency。
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PbCurrency> getCurrencyList() {
		return this.currencyDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<ChangeCurrencyDTO> changeCurrency(
			List<ChangeCurrencyDTO> currencyDTOList) {
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal rateBD = zero;
		List<ChangeCurrencyDTO> changeCurrencyList = new ArrayList<ChangeCurrencyDTO>();
		if (currencyDTOList != null && currencyDTOList.size() > 0) {
			for (ChangeCurrencyDTO changeCurrency : currencyDTOList) {
				Double price = changeCurrency.getPrice();

				String fromCurrency = changeCurrency.getFromCurrency();
				String toCurrency = changeCurrency.getToCurrency();
				Date currencyDate = changeCurrency.getCurrencyDate();
				Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency,
						toCurrency, currencyDate);
				if (rate != null) {
					rateBD = new BigDecimal(rate);
					BigDecimal amountBD = new BigDecimal(price)
							.multiply(rateBD).setScale(2,
									BigDecimal.ROUND_HALF_UP);
					String symbol = currencyDao.getCurrencySymbol(toCurrency);
					changeCurrency.setPrice(amountBD.doubleValue());
					changeCurrency.setSymbol(symbol);
					changeCurrency.setFromCurrency(null);
					changeCurrency.setCurrencyDate(null);
					changeCurrencyList.add(changeCurrency);
				}
			}
		}
		return changeCurrencyList;
	}

	@Transactional(readOnly = true)
	public List<SearchORFCloneGeneDTO> searchORFCloneGene(
			final String searchType, final String searchValue) {
		List<SearchORFCloneGeneDTO> searchORFCloneDTOList;
		if (searchType.equals(ORFCloneSearchType.REFSEQ.name())) {
			searchORFCloneDTOList = new ArrayList<SearchORFCloneGeneDTO>();
			List<Gene> geneList = geneDao.findBy("refseqid", searchValue);
			if (geneList != null && geneList.size() > 0) {
				for (Gene gene : geneList) {
					Integer productId = gene.getProductId();
					String refseqId = gene.getRefseqid();
					if (productId != null) {
						Product product = productDao.get(productId);
						String geneId = product.getCatalogNo();
						String name = product.getName();
						String definition = product.getLongDesc();
						SearchORFCloneGeneDTO searchORFCloneDTO = new SearchORFCloneGeneDTO();
						searchORFCloneDTO.setRefseqId(refseqId);
						searchORFCloneDTO.setGenscriptGene(geneId);
						searchORFCloneDTO.setName(name);
						searchORFCloneDTO.setDefinition(definition);
						searchORFCloneDTOList.add(searchORFCloneDTO);
					}
				}
				return searchORFCloneDTOList;
			}
		} else if (searchType.equals(ORFCloneSearchType.GENE.name())) {
			searchORFCloneDTOList = new ArrayList<SearchORFCloneGeneDTO>();
			Product product = productDao.findUniqueBy("catalogNo", searchValue);
			if (product != null) {
				Integer productId = product.getProductId();
				String geneId = product.getCatalogNo();
				String name = product.getName();
				String definition = product.getLongDesc();
				List<Gene> geneList = geneDao.findBy("productId", productId);
				if (geneList != null && geneList.size() > 0) {
					for (Gene gene : geneList) {
						String refseqId = gene.getRefseqid();
						SearchORFCloneGeneDTO searchORFCloneDTO = new SearchORFCloneGeneDTO();
						searchORFCloneDTO.setRefseqId(refseqId);
						searchORFCloneDTO.setGenscriptGene(geneId);
						searchORFCloneDTO.setName(name);
						searchORFCloneDTO.setDefinition(definition);
						searchORFCloneDTOList.add(searchORFCloneDTO);
					}
				}
			}
			return searchORFCloneDTOList;
		} else if (searchType.equals(ORFCloneSearchType.KEYWORDS.name())) {
			searchORFCloneDTOList = new ArrayList<SearchORFCloneGeneDTO>();
			ProductClass productClass = productClassDao.findUniqueBy("name",
					"Gene");
			Integer clsId = productClass.getClsId();
			List<PropertyFilter> keyWordFilterList = new ArrayList<PropertyFilter>();
			PropertyFilter clsIdFilter = new PropertyFilter("EQI_productClsId",
					clsId);
			PropertyFilter nameOrLongDescFilter = new PropertyFilter(
					"LIKES_name_OR_longDesc", searchValue);
			keyWordFilterList.add(clsIdFilter);
			keyWordFilterList.add(nameOrLongDescFilter);
			List<Product> productList = productDao.find(keyWordFilterList);
			if (productList != null && productList.size() > 0) {
				for (Product product : productList) {
					Integer productId = product.getProductId();
					Gene gene = geneDao.getById(productId);
					String geneId = product.getCatalogNo();
					String name = product.getName();
					String definition = product.getLongDesc();
					String refseqId = null;
					if (gene != null)
						refseqId = gene.getRefseqid();
					SearchORFCloneGeneDTO searchORFCloneDTO = new SearchORFCloneGeneDTO();
					searchORFCloneDTO.setRefseqId(refseqId);
					searchORFCloneDTO.setGenscriptGene(geneId);
					searchORFCloneDTO.setName(name);
					searchORFCloneDTO.setDefinition(definition);
					searchORFCloneDTOList.add(searchORFCloneDTO);
				}
			}
			return searchORFCloneDTOList;
		}
		return null;
	}

	public ServiceItemPiceDTO getAntibodyPeptidePrice(
			final ServiceItemPiceDTO orderServiceItemPiceDTO) {
		String catalogId = orderServiceItemPiceDTO.getCatalogId();
		String catalogNo = orderServiceItemPiceDTO.getCatalogNo();
		// String servicePriceType =
		// orderServiceItemPiceDTO.getServicePriceType();
		String peptidePrice = orderServiceItemPiceDTO.getPriceStr();
		Integer quanty = orderServiceItemPiceDTO.getQuantity();
		long start = System.nanoTime();
		String price = (String) serviceDao.getAntibodyPepPrice(catalogNo,
				catalogId, peptidePrice);
		long end = System.nanoTime();
		System.out.println("Invoke get Antibody service price procedure took "
				+ (end - start) + " nanoseconds");
		ServiceItemPiceDTO result = new ServiceItemPiceDTO();
		result.setPriceStr(price);
		result.setQuantity(quanty);
		return result;
	}

	@SuppressWarnings("unchecked")
	public ServiceItemPiceDTO calculateServicePrice(final ServiceItemPiceDTO orderServiceItemPiceDTO) {
		ServiceItemPiceDTO result = new ServiceItemPiceDTO();
		String sequence;
		String catalogId = orderServiceItemPiceDTO.getCatalogId();
		String catalogNo = orderServiceItemPiceDTO.getCatalogNo();
		String servicePriceType = orderServiceItemPiceDTO.getServicePriceType();
		Integer quanty = orderServiceItemPiceDTO.getQuantity();
		sequence = getSequence(orderServiceItemPiceDTO, servicePriceType);
		OrderGeneSynthesisDTO geneSynthesisDTO = orderServiceItemPiceDTO.getGeneSynthesisDTO();
		PeptideDTO peptideDTO = orderServiceItemPiceDTO.getPeptideDTO();
		if (ServicePriceType.geneSynthesis.name().equals(servicePriceType)){
			//判断gene的sequence是否为难度序列，为难度序列则不计算价格，返回TBD
			Integer geneDifResult = bioInfoService.calculateGeneDifficulity(sequence);
			if (geneDifResult == null) {
			} else if (geneDifResult == 1) {
				result.setTBDFlag("1");
				if (geneSynthesisDTO != null && geneSynthesisDTO.getBpPrice() != null && orderServiceItemPiceDTO.getServiceClsId() == 3) {
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+result);
					result.setTBDFlag("0");
				} else {
					return result;
				}
			}else{
				System.out.println("*****************************"+result);
				result.setTBDFlag("0");
			}
		} else {
			result.setTBDFlag("0");
		}
		
		String attributeValue;
		// Integer serviceClsId = orderServiceItemPiceDTO.getServiceClsId();
		String attributes = (String) serviceDao.getServicePriceAttributes(catalogNo, catalogId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + attributes+ ", catalogNo " + catalogNo + " ,catalogId " + catalogId);
		if (attributes != null) {
			String[] attributeArray = StringUtils.split(attributes, SEPARATE);
			StringBuilder sb = new StringBuilder();
			OrderCustCloningDTO custCloningDTO = orderServiceItemPiceDTO.getCustCloningDTO();
			OrderMutagenesisDTO mutagenesisDTO = orderServiceItemPiceDTO.getMutagenesisDTO();
			OrderPlasmidPreparationDTO plasmidPreparationDTO = orderServiceItemPiceDTO.getPlasmidPreparationDTO();
			AntibodyDTO antibodyDTO = orderServiceItemPiceDTO.getAntibodyDTO();
			RnaDTO rnaDTO = orderServiceItemPiceDTO.getRnaDTO();
			OrderOrfCloneDTO orderOrfCloneDTO = orderServiceItemPiceDTO.getOrfCloneDTO();
			OrderOligoDTO orderOligoDTO = orderServiceItemPiceDTO.getOligoDTO();
			for (String attribute : attributeArray) {
				try {
					String value;
					if (attribute.equals("catalogNo") || attribute.equals("parentClsId")) {
						value = BeanUtils.getProperty(orderServiceItemPiceDTO, attribute);
						// Calculate service price for GeneSynthesis type
					} else if (ServicePriceType.geneSynthesis.name().equals(servicePriceType)) {
						if (attribute.equals("seqLength")) {
							String seqTypeVal = BeanUtils.getProperty(geneSynthesisDTO, "sequenceType");
							if (seqTypeVal.equals("DNA")) {
								String threeSequence = geneSynthesisDTO.getSequence3() == null ? "" : geneSynthesisDTO.getSequence3();
								String fiveSequence = geneSynthesisDTO.getSequence5() == null ? "" : geneSynthesisDTO.getSequence5();
								String sequenceStr = geneSynthesisDTO.getSequence() == null ? "" : geneSynthesisDTO.getSequence();
								StringBuilder sequenceSB = new StringBuilder();
								sequenceSB.append(threeSequence).append(fiveSequence).append(sequenceStr);
								value = String.valueOf(sequenceSB.toString().length());
							} else if (seqTypeVal.equals("Protein")) {
								value = String.valueOf(sequence.length() * 3);
							} else if (seqTypeVal.equals("Length")) {
								value = BeanUtils.getProperty(geneSynthesisDTO, "seqLength");
								if (value == null) {
									value = "";
								}
							} else {
								throw new RuntimeException("no this gene sequence type exist");
							}
						} else {
							value = BeanUtils.getProperty(geneSynthesisDTO, attribute);
							if (value == null) {
								value = "";
							}
						}
						// Calculate service price for CustomCloning type
					} else if (ServicePriceType.custCloning.name().equals(servicePriceType)) {
						if (attribute.equals("tgtSeqLength")) {
							value = sequence;
						} else {
							value = BeanUtils.getProperty(custCloningDTO, attribute);
							if (value == null) {
								value = "";
							}
						}
						// Calculate service price for Peptide type
					} else if (ServicePriceType.peptide.name().equals(servicePriceType)) {
						if (attribute.equals("seqLength")) {
							value = String.valueOf(StringUtil.calculateSeqLength(sequence));
						} else if (("newSeqLength").equals(attribute)) {
							Integer modificationLength = getPeptideModifiLength(peptideDTO);
							value = String.valueOf(StringUtil.calculateSeqLength(sequence) + modificationLength.intValue());
						} else {
							value = BeanUtils.getProperty(peptideDTO, attribute);
							if (value == null) {
								value = "";
							}
						}
						// Calculate service price for Mutagenesis type
					} else if (ServicePriceType.mutagenesis.name().equals(servicePriceType)) {
						value = BeanUtils.getProperty(mutagenesisDTO, attribute);
						if (value == null) {
							value = "";
						}
						// Calculate service price for Plasimid type
					} else if (ServicePriceType.plasmidPreparation.name().equals(servicePriceType)) {
						if (attribute.equals("prepWeight")) {
							value = null;
							if (plasmidPreparationDTO.getPrepWeight() != null) {
								Double preWit = plasmidPreparationDTO.getPrepWeight().doubleValue();
								if (preWit.doubleValue() == preWit.intValue()) {
									value = String.valueOf(preWit.intValue());
								} else {
									value = String.valueOf(preWit.doubleValue());
								}
							}
						} else {
							value = BeanUtils.getProperty(plasmidPreparationDTO, attribute);
						}
						if (value == null) {
							value = "";
						}
					} else if (ServicePriceType.antibody.name().equals(servicePriceType)) {
						value = BeanUtils.getProperty(antibodyDTO, attribute);
						if (value == null) {
							value = "";
						}
					} else if (ServicePriceType.orfClone.name().equals(servicePriceType)) {
						value = BeanUtils.getProperty(orderOrfCloneDTO, attribute);
						if (value == null) {
							value = "";
						}
					} else if (ServicePriceType.rna.name().equals(servicePriceType)) {
						if (!("customerType").equals(attribute) && !("vector").equals(attribute)) {
							value = BeanUtils.getProperty(rnaDTO, attribute);
						} else {
							value = null;
						}
						if (value == null) {
							value = "";
						}
					} else if (ServicePriceType.oligo.name().equals(servicePriceType)) {
						if (attribute.equals("seqLength")) {
							String sequenceStr = orderOligoDTO.getSequence();
							int length = sequenceStr.replaceAll("\\/[^/]*\\/", "")
									.replaceAll("\\{.*\\}", "").replaceAll("\\(.*?\\)", "X").length();
							value = length + "";
						} else {
							value = BeanUtils.getProperty(orderOligoDTO, attribute);
							if (value == null) {
								value = "";
							}
						}
					} else {
						value = "";
					}
					sb.append(value).append(SEPARATE);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			System.out.println("**************************" + sb.toString());
			if (StringUtils.isNotEmpty(sb.toString())) {
				attributeValue = sb.substring(0, sb.length() - 1);
			} else {
				attributeValue = "";
			}
		} else {
			attributeValue = "";
		}
		List<Double> costAndPrice = new ArrayList<Double>();
		costAndPrice = (List<Double>) serviceDao.CalculateServicePrice(catalogNo, catalogId, attributeValue);
		Double cost = costAndPrice.get(0);
		BigDecimal price = costAndPrice.get(1) == null ? null : new BigDecimal(costAndPrice.get(1)) ;
        if ((cost != null && cost.doubleValue() < 0) || (price != null && price.doubleValue() < 0)) {
			result.setTBDFlag("1");
			result.setCost(cost == null?(new BigDecimal(0)):(new BigDecimal(cost < 0 ? 0 : cost)));
			result.setPrice(price == null?(new BigDecimal(0)):(price.doubleValue() < 0 ? new BigDecimal(0) : price));
			result.setQuantity(quanty);
			return result;
		}
		result.setCost(cost == null?(new BigDecimal(0)):(new BigDecimal(cost)));
		result.setPrice(price == null?(new BigDecimal(0)):price);
		result.setQuantity(quanty);
		return result;
	}

	public static String getSequence(
			final ServiceItemPiceDTO orderServiceItemPiceDTO,
			final String servicePriceType) {
		String sequence;
		if (ServicePriceType.geneSynthesis.name().equals(servicePriceType)) {
			OrderGeneSynthesisDTO geneSynthesisDTO = orderServiceItemPiceDTO
					.getGeneSynthesisDTO();
			sequence = geneSynthesisDTO.getSequence();
		} else if (ServicePriceType.custCloning.name().equals(servicePriceType)) {
			OrderCustCloningDTO custCloningDTO = orderServiceItemPiceDTO
					.getCustCloningDTO();
			sequence = custCloningDTO.getTgtSeqLength().toString();
		} else if (ServicePriceType.peptide.name().equals(servicePriceType)) {
			PeptideDTO peptideDTO = orderServiceItemPiceDTO.getPeptideDTO();
			sequence = peptideDTO.getSequence();
		} else if (ServicePriceType.mutagenesis.name().equals(servicePriceType)) {
			OrderMutagenesisDTO mutagenesisDTO = orderServiceItemPiceDTO
					.getMutagenesisDTO();
			sequence = mutagenesisDTO.getVariantSequence();
		} else if (ServicePriceType.plasmidPreparation.name().equals(
				servicePriceType)) {
			OrderPlasmidPreparationDTO plasmidPreparationDTO = orderServiceItemPiceDTO
					.getPlasmidPreparationDTO();
			sequence = plasmidPreparationDTO.getSequence();
		} else if (ServicePriceType.oligo.name().equals(servicePriceType)) {
			OrderOligoDTO oligoDTO = orderServiceItemPiceDTO.getOligoDTO();
			sequence = oligoDTO.getSequence();
		} else {
			// throw new RuntimeException("get sequence error");
			sequence = "";
		}
		return sequence;
	}

	/**
	 * 获取peptide的modification的长度
	 * 
	 * @param peptide
	 * @return
	 * @author Zhang Yong
	 */
	private Integer getPeptideModifiLength(PeptideDTO peptide) {
		Integer peptideModifiLength = 0;
		if (peptide == null) {
			return peptideModifiLength;
		}
		if (StringUtils.isNotEmpty(peptide.getNTerminal())) {
			List<PeptideModification> peptideModifiList = peptideModificationDao
					.findByNameAndType(peptide.getNTerminal(),
							PeptideModificationType.NTerminal.value());
			if (peptideModifiList != null && !peptideModifiList.isEmpty()) {
				for (PeptideModification pm : peptideModifiList) {
					peptideModifiLength = peptideModifiLength.intValue()
							+ (pm.getAdtlSeqLength() == null ? 0 : pm
									.getAdtlSeqLength().intValue());
				}
			}
		}
		if (StringUtils.isNotEmpty(peptide.getCTerminal())) {
			List<PeptideModification> peptideModifiList = peptideModificationDao
					.findByNameAndType(peptide.getCTerminal(),
							PeptideModificationType.CTerminal.value());
			if (peptideModifiList != null && !peptideModifiList.isEmpty()) {
				for (PeptideModification pm : peptideModifiList) {
					peptideModifiLength = peptideModifiLength.intValue()
							+ (pm.getAdtlSeqLength() == null ? 0 : pm
									.getAdtlSeqLength().intValue());
				}
			}
		}
		if (StringUtils.isNotEmpty(peptide.getModification())) {
			String[] modifications = peptide.getModification().split(",");
			for (String modification : modifications) {
				List<PeptideModification> peptideModifiList = peptideModificationDao
						.findByNameAndType(modification,
								PeptideModificationType.Normal.value());
				if (peptideModifiList != null && !peptideModifiList.isEmpty()) {
					for (PeptideModification pm : peptideModifiList) {
						peptideModifiLength = peptideModifiLength.intValue()
								+ (pm.getAdtlSeqLength() == null ? 0 : pm
										.getAdtlSeqLength().intValue());
					}
				}
			}
		}
		return peptideModifiLength;
	}



	/**
	 * @return
	 * @author wangsf
	 * @serialData 2011-02-21 14:55 获得所有Language
	 */
	public List<PbLanguage> getAllLangList() {
		return this.pbLanguageDao.getAll();
	}


	/**
	 * 通过TerritoryId查询sales及sales对应group相关信息
	 * 
	 * @param salesTerrId
	 * @return salesMap
	 * @author zhang yong
	 */
	public Map<String, Object> searchSalesByTerritory(String salesTerrId,
			String defSalesRepId, String defaultSalesGroup) {
		Map<String, Object> salesMap = new HashMap<String, Object>();
		List<SalesResourceAssignTerritory> sratlist = null;
		if (salesTerrId != null) {
			sratlist = salesResourceAssignTerritoryDao
					.findByTerritoryId(Integer.parseInt(salesTerrId));
		}
		SalesGroup salesGroup = null;
		List<Integer> salesIdList = new ArrayList<Integer>();
		if (sratlist != null) {
			for (SalesResourceAssignTerritory srat : sratlist) {
				salesIdList.add(srat.getSalesId());
			}
		}
		List<SalesRep> salesRepList = null;
		if (!salesIdList.isEmpty()) {
			salesIdList = new ArrayList<Integer>(new HashSet<Integer>(
					salesIdList));
			salesRepList = salesRepDao.findByIds(salesIdList);
		}
		SalesRep defaultSalesRep = null;
		if (defSalesRepId != null && StringUtil.isNumeric(defSalesRepId)) {
			defaultSalesRep = salesRepDao.getById(Integer
					.parseInt(defSalesRepId));
			if (defaultSalesRep != null) {
				if (salesRepList == null) {
					salesRepList = new ArrayList<SalesRep>();
				}
				if (!salesRepList.contains(defaultSalesRep)) {
					salesRepList.add(defaultSalesRep);
				}
			}
		}
		if (defaultSalesGroup != null
				&& StringUtil.isNumeric(defaultSalesGroup)) {
			salesGroup = salesGroupDao.getById(Integer
					.parseInt(defaultSalesGroup));
		}
		if (salesRepList != null && !salesRepList.isEmpty()) {
			salesMap.put("salesRepListBySearch", salesRepList);
			if (salesGroup == null) {
				SalesRep salesRep = salesRepList.get(0);
				if (salesRep != null && salesRep.getSalesGroupId() != null) {
					salesGroup = salesGroupDao.getById(salesRep
							.getSalesGroupId());
				}
			}
			salesMap.put("salesGroupBySearch", salesGroup);
		}
		return salesMap;
	}

	public Map<String, DropDownListDTO> searchSalesByTerritoryWS(
			String salesTerrId, String defSalesRepId, String defaultSalesGroup) {
		Map<String, DropDownListDTO> salesMap = new HashMap<String, DropDownListDTO>();
		List<SalesResourceAssignTerritory> sratlist = null;
		if (salesTerrId != null && StringUtil.isNumeric(salesTerrId)) {
			sratlist = salesResourceAssignTerritoryDao
					.findByTerritoryId(Integer.parseInt(salesTerrId));
		}
		SalesGroup salesGroup = null;
		List<Integer> salesIdList = new ArrayList<Integer>();
		if (sratlist != null) {
			for (SalesResourceAssignTerritory srat : sratlist) {
				salesIdList.add(srat.getSalesId());
			}
		}
		for (SalesResourceAssignTerritory srat : sratlist) {
			salesIdList.add(srat.getSalesId());
		}
		List<SalesRep> salesRepList = null;
		if (!salesIdList.isEmpty()) {
			salesIdList = new ArrayList<Integer>(new HashSet<Integer>(
					salesIdList));
			salesRepList = salesRepDao.findByIds(salesIdList);
		}
		SalesRep defaultSalesRep = null;
		if (defSalesRepId != null && StringUtil.isNumeric(defSalesRepId)) {
			defaultSalesRep = salesRepDao.getById(Integer
					.parseInt(defSalesRepId));
			if (defaultSalesRep != null) {
				if (salesRepList == null) {
					salesRepList = new ArrayList<SalesRep>();
				}
				if (!salesRepList.contains(defaultSalesRep)) {
					salesRepList.add(defaultSalesRep);
				}
			}
		}
		if (defaultSalesGroup != null
				&& StringUtil.isNumeric(defaultSalesGroup)) {
			salesGroup = salesGroupDao.getById(Integer
					.parseInt(defaultSalesGroup));
		}
		List<DropDownDTO> salesList = new ArrayList<DropDownDTO>();
		if (salesRepList != null && !salesRepList.isEmpty()) {
			for (SalesRep salesrep : salesRepList) {
				DropDownDTO dropDownDTO = new DropDownDTO();
				dropDownDTO.setId(salesrep.getSalesId().toString());
				dropDownDTO.setName(salesrep.getResourceName());
				dropDownDTO.setValue(salesrep.getSalesGroupId().toString());
				salesList.add(dropDownDTO);
			}
		}
		if (!salesList.isEmpty()) {
			DropDownListDTO sropDownListDTO = new DropDownListDTO();
			DropDownListDTO pDownListDTO = new DropDownListDTO();
			sropDownListDTO.setDropDownDTOs(salesList);
			salesMap.put("salesRepListBySearch", sropDownListDTO);
			if (salesGroup == null) {
				DropDownDTO salesContact = salesList.get(0);
				salesGroup = salesGroupDao.getById(Integer
						.parseInt(salesContact.getValue()));
				// DropDownListDTO dopDownListDTO = new DropDownListDTO();
				if (salesGroup != null) {
					DropDownDTO dropDownDTO = new DropDownDTO();
					dropDownDTO.setId(salesGroup.getGroupId().toString());
					dropDownDTO.setName(salesGroup.getGroupCode());
					List<DropDownDTO> dropDownDTOs = new ArrayList<DropDownDTO>();
					dropDownDTOs.add(dropDownDTO);
					pDownListDTO.setDropDownDTOs(dropDownDTOs);
				}
			}
			salesMap.put("salesGroupBySearch", pDownListDTO);
		}
		return salesMap;
	}

	/**
	 * 通过salesId查询其对应group信息
	 * 
	 * @param salesId
	 * @return salesGroup
	 * @author zhang yong
	 */
	public SalesGroup searchGroupBySalesId(String salesId) {
		SalesGroup salesGroup = null;
		if (salesId != null && StringUtil.isNumeric(salesId)) {
			SalesRep salesRep = salesRepDao.getById(Integer.parseInt(salesId));
			if (salesRep != null) {
				salesGroup = salesGroupDao.getById(salesRep.getSalesGroupId());
			}
		}
		return salesGroup;
	}

	/**
	 * 获得所有bank.
	 * 
	 * @return
	 * @serialData 2011-03-02
	 * @author wangsf
	 */
	public List<Bank> getAllBank() {
		return this.bankDao.getAll();
	}

	/**
	 * 分页查找Bank
	 * 
	 * @param page
	 * @param filterList
	 * @return
	 * @author wangsf
	 * @since 2011-03-02
	 */
	public Page<Bank> searchBank(Page<Bank> page,
			List<PropertyFilter> filterList) {
		return this.bankDao.findPage(page, filterList);
	}

	/**
	 * 通过currencyCode查询货币符号
	 * 
	 * @param currencyCode
	 * @return
	 * @author Zhang Yong
	 */
	public String searchSymbolByCode(String currencyCode) {
		return this.currencyDao.getCurrencySymbol(currencyCode);
	}

	/**
	 * 获得当前的Currency兑BaseCurrency USD的汇率
	 * 
	 * @param currency
	 * @param exchRateDate
	 * @return
	 */
	public Double getRateByCurrencyToBaseCurrency(String currency,
			Date exchRateDate) {
		if (exchRateDate == null) {
			exchRateDate = new Date();
		}
		return exchRateByDateDao.getCurrencyRate(currency,
				CurrencyType.USD.value(), exchRateDate);
	}

    public Double getRateByBaseCurrencyToCurrency(String currency,
			Date exchRateDate) {
		if (exchRateDate == null) {
			exchRateDate = new Date();
		}
		return exchRateByDateDao.getCurrencyRate(CurrencyType.USD.value(), currency, exchRateDate);
	}

	/**
	 * 给当前修改的customer或contact寻找salesManager和techAcountManager
	 * 
	 * @param type
	 *            customer/contact
	 * @param sessNo
	 *            标示唯一当前修改的对象
	 * @author lizhang
	 */
	public Map<String, List<SalesRep>> searchManager_Cust_Contact(String type,
			String sessNo) {
		Map<String, List<SalesRep>> map = new HashMap<String, List<SalesRep>>();
		Integer orgId = null;
		String countryCode = null;
		String stateCode = null;
		String zipCode = null;
		if (type == null || "".equals(type)) {
			return null;
		}
		if (type.equalsIgnoreCase("customer")) {
			CustAddrOperDTO shipAddress = addressDao
					.searchAddressForSession(sessNo);
			if (shipAddress != null) {
				orgId = shipAddress.getOrgId() != null ? shipAddress.getOrgId()
						: null;
				// orgId = shipAddress.getOrganization() != null ?
				// shipAddress.getOrganization().getOrgId() : null;
				countryCode = shipAddress.getCountry();
				stateCode = shipAddress.getState();
				zipCode = shipAddress.getZipCode();
			}
		} else if (type.equalsIgnoreCase("contact")) {
			ContactAddressDTO shipAddress = contactAddressDao
					.searchAddressForSession(sessNo);
			if (shipAddress != null) {
				orgId = shipAddress.getOrgId() != null ? shipAddress.getOrgId()
						: null;
				// orgId = shipAddress.getOrganization() != null ?
				// shipAddress.getOrganization().getOrgId() : null;
				countryCode = shipAddress.getCountry();
				stateCode = shipAddress.getState();
				zipCode = shipAddress.getZipCode();
			}
		}
		List<SalesResourceAssignOrg> _sales_rep_Org_list = salesResourceAssignOrgDao
				.findByOrgId(orgId);
		List<SalesTerritoryAssignment> _sales_terr_assign_list = salesTerritoryAssignmentDao
				.find_country_State_zip(countryCode, stateCode, zipCode);
		List<SalesRep> salesManagerList = new ArrayList<SalesRep>();
		List<SalesRep> techAcountManagerList = new ArrayList<SalesRep>();
		boolean is_Org_has_BD = false;// 标示该customer/contact对应的组织是否派专员
		if (_sales_rep_Org_list != null && _sales_rep_Org_list.size() > 0) {
			List<SalesRep> _salesManagerList = new ArrayList<SalesRep>();
			List<SalesRep> _techAccountManagerList = new ArrayList<SalesRep>();
			for (SalesResourceAssignOrg salesResourceAssignOrg : _sales_rep_Org_list) {
				SalesRep salesRep = this.salesRepDao
						.getById(salesResourceAssignOrg.getSalesId());
				if (salesRep != null
						&& SalesRepSalesRole.SALES_CONTACT.value().equals(
								salesRep.getFunction())) {
					_salesManagerList.add(salesRep);
					is_Org_has_BD = true;
				}
				if (salesRep != null
						&& SalesRepSalesRole.TECH_SUPPORT.value().equals(
								salesRep.getFunction())) {
					_techAccountManagerList.add(salesRep);
				}
			}
			if (is_Org_has_BD) {
				if(_salesManagerList.size()==1) {
					salesManagerList.add(_salesManagerList.get(0));
				} else {
					a: for (SalesRep salesRep : _salesManagerList) {
						if (_sales_terr_assign_list != null
								&& _sales_terr_assign_list.size() > 0) {
							for (SalesTerritoryAssignment salesTerritoryAssignment : _sales_terr_assign_list) {
								if (salesResourceAssignTerritoryDao
										.findBy_terrId_salesId(
												salesTerritoryAssignment
														.getTerritoryId(), salesRep
														.getSalesId()) != null
										&& salesManagerList.size() == 0) {
									salesManagerList.add(salesRep);
									break a;
								}
							}
						}
					}
					if(salesManagerList.size()==0) {
						salesManagerList.add(_salesManagerList.get(0));
					}
				}
			}
			if (_techAccountManagerList.size() > 0) {
				b: for (SalesRep salesRep : _techAccountManagerList) {
					if (_sales_terr_assign_list != null
							&& _sales_terr_assign_list.size() > 0) {
						for (SalesTerritoryAssignment salesTerritoryAssignment : _sales_terr_assign_list) {
							if (salesResourceAssignTerritoryDao
									.findBy_terrId_salesId(
											salesTerritoryAssignment
													.getTerritoryId(), salesRep
													.getSalesId()) != null
									&& techAcountManagerList.size() == 0) {
								techAcountManagerList.add(salesRep);
								break b;
							}
						}
					}
				}
			}
		}
		if (_sales_terr_assign_list != null
				&& _sales_terr_assign_list.size() > 0) {
			c: for (SalesTerritoryAssignment salesTerritoryAssignment : _sales_terr_assign_list) {
				if (salesTerritoryAssignment != null
						&& salesTerritoryAssignment.getTerritoryId() != null) {
					List<SalesResourceAssignTerritory> _sales_rep_terr_list = salesResourceAssignTerritoryDao
							.findByTerritoryId(salesTerritoryAssignment
									.getTerritoryId());
					if (_sales_rep_terr_list != null) {
						for (SalesResourceAssignTerritory salesResourceAssignTerritory : _sales_rep_terr_list) {
							if (salesResourceAssignTerritory.getSalesId() != null) {
								SalesRep salesRep = this.salesRepDao
										.getById(salesResourceAssignTerritory
												.getSalesId());
								if (salesManagerList.size()==0&&SalesRepSalesRole.SALES_CONTACT
										.value().equals(salesRep.getFunction())) {
									salesManagerList.add(salesRep);
								} else if (SalesRepSalesRole.TECH_SUPPORT
										.value().equals(salesRep.getFunction())
										&& techAcountManagerList.size() == 0) {
									techAcountManagerList.add(salesRep);
								} else if (salesManagerList.size() > 0&& techAcountManagerList.size()>0) {
									break c;
								}
							}
						}
					}

				}
			}
		}
		for(SalesRep saleRep:salesManagerList) {
			User user = this.userDao.getById(saleRep.getSalesId());
			Employee employee = null;
            if(user != null && user.getEmployee() != null){
                employee = this.employeeDao.getById(user.getEmployee().getEmployeeId());
            }
			saleRep.setUserName(employee!=null?employee.getEmployeeName():"");
		}
		for(SalesRep saleRep:techAcountManagerList) {
			User user = this.userDao.getById(saleRep.getSalesId());
			Employee employee = user!=null?this.employeeDao.getById(user.getEmployee().getEmployeeId()):null;
			saleRep.setUserName(employee!=null?employee.getEmployeeName():"");
		}
		map.put("salesManager", salesManagerList);
		map.put("techAcountManager", techAcountManagerList);
		return map;
	}

	@Transactional(readOnly = true)
	public List<DepartmentSystem> findAllDept() {
		return this.departmentSystemDao.findAll(Page.ASC, "name");
	}

	@Transactional(readOnly = true)
	public List<EnzymeDTO> getEnzymeSequence() {
		return specDropDownListDao.getEnzymeSequence();
	}

	@SuppressWarnings("rawtypes")
	class ContentComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			PbDropdownListOptions c1 = (PbDropdownListOptions) o1;
			PbDropdownListOptions c2 = (PbDropdownListOptions) o2;
			if (c1.getText().compareToIgnoreCase(c2.getText()) > 0) {
				return 1;
			} else {
				if (c1.getText().equals(c2.getText())) {
					return 0;
				} else {
					return -1;
				}
			}
		}
	}

	public List<ProductClass> getProductTypeList() { 
		return this.productClassDao.findAlls();
	}
	public List<ServiceClass> getServiceTypeList() { 
		return this.serviceClassDao.findAlls();
	}
	
	@Transactional(readOnly = true)
	public String getAuthNo(String ccNumber, String ccHolder, Date tranDate){
		return chargerDao.getAuthNo(ccNumber, ccHolder, tranDate);
	}

    @Transactional(readOnly = true)
	public List<PbDropdownListOptions> getUpdateReasonDropdownList(String type, Integer cls_id) {
		List<PbDropdownListOptions> pbDropdownLists = pbDropdownListDao.getUpdateReasonDropdownList(type, cls_id);
		if (pbDropdownLists == null) {
			return new ArrayList<PbDropdownListOptions>();
		}
		return pbDropdownLists;
	}

}
