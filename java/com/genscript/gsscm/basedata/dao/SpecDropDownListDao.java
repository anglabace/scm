package com.genscript.gsscm.basedata.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.dto.AllCountryDTO;
import com.genscript.gsscm.basedata.dto.AllStateDTO;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.GetInfoByRegionDTO;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.TemplateType;
import com.genscript.gsscm.quoteorder.dto.EnzymeDTO;
import com.genscript.gsscm.shipment.dao.StandardShipRateDao;
import com.genscript.gsscm.shipment.dao.StandardShipZoneDetailDao;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.shipment.entity.StandardShipRate;
import com.genscript.gsscm.shipment.entity.StandardShipZoneDetail;

/**
 * @author user
 * 
 */
@Repository
public class SpecDropDownListDao extends HibernateDao<DropDownDTO, Integer> {

	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private StandardShipZoneDetailDao standardShipZoneDetailDao;
	@Autowired
	private StandardShipRateDao standardShipRateDao;

	private static final String CUSTOMER_ROLE = "select c.custRoleId,c.name from CustomerRole c";
	private static final String DEPARTMENT_FUNCTION = "select d.functionId,d.name from DepartmentFunction d";

	private static final String ORIGINAL_SOURCE = "select s.sourceId,s.name from Source s where s.status ='ACTIVE' order by s.sourceId desc";
	private static final String ORGANIZATION_CATEGORY = "select o.categoryId,o.name from OrganizationCategory o";
	private static final String ORGANIZATION_TYPE = "select o.orgTypeId,o.name from OrganizationType o";
	private static final String TIME_ZONE = "select p.timeZoneId,p.code,p.gmtCode,p.description from PbTimeZone p";
	private static final String LANGUAGE_CODE = "select p.langCode,p.langCode from PbLanguage p";
	private static final String COUNTRY_CODE = "select p.countryId,p.countryCode from PbCountry p";
	private static final String COUNTRY_NAME = "select p.countryCode,p.name from PbCountry p order by p.name";
	private static final String TERRITORY_BY_STATE = "select distinct(s.territoryId), st.territoryCode,st.territoryClassification from SalesTerritoryAssignment s, SalesTerritory st where s.countryCode=? and s.stateCode=? and s.territoryId = st.territoryId ";
	private static final String TERRITORY_BY_ZIP = "select distinct(st.territoryId), st.territoryCode,st.territoryClassification from SalesTerritoryAssignment s, SalesTerritory st where s.countryCode=? and s.stateCode=? and s.fromZip <= ? and s.toZip >= ? and s.territoryId = st.territoryId ";
	private static final String TERRITORY_BY_COUNTRY = "select distinct(s.territoryId), st.territoryCode,st.territoryClassification from SalesTerritoryAssignment s, SalesTerritory st where s.countryCode=? and s.territoryId = st.territoryId ";
	private static final String TERRITORY_BY_CUSTNO = "select st.territoryId, st.territoryCode,st.territoryClassification from Customer ct, SalesTerritory st where ct.custNo = ? and ct.salesTerritory = st.territoryId";
	private static final String TERRITORY_BY_CODE = "select s.territoryId,s.territoryCode,s.territoryClassification from SalesTerritory s, SalesResourceAssignTerritory srat, SalesRep sr where s.territoryId=? and s.territoryType<>? and s.territoryId = srat.salesTerritory.territoryId and srat.salesId = sr.salesId and sr.salesGroupId is not null";
	private static final String GROUP_BY_GROUPID = "from SalesGroup s where s.groupId in(?)";

	private static final String CONTACT_BY_GROUP = "select s.salesId,s.resourceName from SalesRep s where s.function = 'SALES_CONTACT' and s.salesGroupId=?";
	private static final String SUPPORT_BY_GROUP = "select s.salesId,s.resourceName from SalesRep s where s.function = 'TECH_SUPPORT' and s.salesGroupId=?";
	private static final String PROJECT_BY_GROUP = "select s.salesId,s.resourceName from SalesRep s where s.function = 'PROJECT_SUPPORT' and s.salesGroupId=?";
	private static final String CREDIT_LIMIT = "select c.crRatingId,c.crRatingCode from CreditRating c where c.crRatingCode <>'other'  order by crRatingId desc";
	private static final String SHIP_METHOD = "select s.methodId, s.name, s.carrier from ShipMethod s";
	private static final String COMPR_SHIP_METHOD = "select s.methodId, s.name,s.methodCode from ShipMethod s";
	private static final String CURRENCY = "select p.currencyId, p.currencyCode,p.symbol from PbCurrency p";
	private static final String CONTACT_INFO_BY_CUSTOMER = "select a.busEmail,a.busPhone,a.addrLine1,a.addrLine2,a.addrLine3,a.city,a.state,a.zipCode,a.country from Address a where a.addrType=? and a.custNo=?";

	private static final String PB_DEPARTMENT = "select p.deptId,p.deptName from PbDepartment p ORDER BY p.deptName ASC";
	private static final String INTEREST_AREA = "select p.areaId,p.name from PbInterestArea p where p.type=? and p.name !='Other'";

	private static final String ALL_COUNTRY = "select p.countryId,p.countryCode,p.name from PbCountry p ORDER BY p.name ASC";
	private static final String ALL_STATE = "select ps.stateCode, ps.name, ps.countryId from PbState ps";

	private static final String SALES_TERRITORY = "select s.territoryCode,s.territoryCode from SalesTerritory s ORDER BY s.territoryCode ASC";
	private static final String SALES_TERRITORY_OTHER = "select s.territoryId,s.territoryCode from SalesTerritory s ORDER BY s.territoryCode ASC";

	private static final String SALES_GROUP = "select s.groupCode,s.groupCode from SalesGroup s ORDER BY s.groupCode ASC";

	private static final String QUOTE_MEMO_TEMPLATE = "select m.templateName,m.content from MemoTemplate m where m.templateType='QUOTE'";
	private static final String ORDER_MEMO_TEMPLATE = "select m.templateName,m.content from MemoTemplate m where m.templateType='ORDER'";
	private static final String SALES_CONTACT = "select s.salesId,s.resourceName from SalesRep s where s.function='SALES_CONTACT'";
	private static final String TECH_SUPPORT = "select s.salesId,s.resourceName from SalesRep s where s.function='TECH_SUPPORT'";
	private static final String PROJECT_SUPPORT = "select s.salesId,s.resourceName from SalesRep s where s.function='PROJECT_SUPPORT'";
	private static final String PROMOTION_CODE = "select p.prmtId,p.prmtCode from Promotion p";
	private static final String PICK_LOCATION = "select s.storageId ,concat(concat(w.name,' - '),s.name)  from Storage s,Warehouse w where s.warehouse=w.warehouseId AND w.warehouseId=?";
	private static final String PRODUCT_CLASSIFICATION = "select p.clsId,p.name from ProductClass p";
	private static final String SERVICE_CLASSIFICATION = "select p.clsId,p.name from ServiceClass p group by name";
	private static final String TAX_STATUS_NATIONAL = "select t.classId,t.name from TaxClass t where t.classType='NATIONAL'";
	private static final String TAX_STATUS_STATE = "select t.classId,t.name from TaxClass t where t.classType='STATE'";
	private static final String TAX_STATUS_COUNTRY = "select t.classId,t.name from TaxClass t where t.classType='COUNTRY'";
	private static final String ORDER_TEMPLATE_NAME = "select t.tmplId,t.tmplName from OrderTemplate t where t.owner=?";
	private static final String QUOTE_TEMPLATE_NAME = "select t.tmplId,t.tmplName from QuoteTemplate t where t.owner=?";
	private static final String PRICE_CATALOG = "select c.id,c.catalogId from Catalog c where c.status = 'ACTIVE'";
	private static final String DROPDOWN_TYPE = "select p.listId from PbDropdownList p where p.listName =?";
	private static final String DROPDOWN_LIST_TYPE = "select o.value, o.text from PbDropdownListOptions o where o.listId =?";
	private static final String SEQUENCE = "select r.name, r.sequence from ResEnzyme r ORDER BY r.name ASC";
	private static final String STATUS_LIST = "select s.statusId,s.code from StatusList s where s.type=?";
	private static final String RFM_VALUE = "select r.rfmRatingId,r.rfmRatingCd from RfmRating r";
	private static final String TERRITORY = "select s.territoryId,s.territoryCode from SalesTerritory s";
	private static final String CATEGORY = "select p.categoryId,p.categoryNo from ProductCategory p where p.catalogId =(select c.catalogId from Catalog c where c.defaultFlag='Y' and c.status='ACTIVE') and p.status='ACTIVE'";
	private static final String HOST_EXPS_ORGANISM = "select h.id,h.name from HostExpsOrganism h ";
	private static final String VECTOR = "select v.vectorId,v.vectorName from Vector v";
	private static final String CATALOG = "select c.catalogId, c.catalogId from Catalog c where c.status='ACTIVE'";
	private static final String SHIP_CARRIERS = "select s.name, s.description, s.trackingUrl from ShipCarriers s";
	private static final String SHIP_ZONE = "select s.id, s.name from StandardShipZoneType s";
	private static final String STANDARD_SHIP_METHOD = "select s.methodId, s.methodName from StandardShipMethod s where s.zoneType=?";
	private static final String WAREHOUSE = "select w.warehouseId, w.name from Warehouse w";
	private static final String PEPTIDE_QUANTITY = "select p.id, p.quantity from PeptideQuantity p";
	private static final String PEPTIDE_PURITY = "select p.id, p.purity from PeptidePurity p";
	private static final String PEPTIDE_PURITY_ARRAY = "select p.id, p.purity from PeptidePurity p where p.purity = 'Crude'";
	private static final String TERMINAL_MODIFICATION = "select p.id, p.name from PeptideModification p where p.type=?";

	private static final String SALESRESOURCEASSIGNTERRITORY_LIST = "from SalesResourceAssignTerritory where salesTerritory.territoryId = ?";
	private static final String SALES_REP_LIST = "from SalesRep where salesId in (?)";

	private static final String ALL_DEPARTMENT = "select  d.id,d.name FROM DepartmentSystem d";
	private static final String CASE_SEND_EMAILTO = "select d.groupId,d.groupName from EmailGroup d ";
	private static final String GET_ALLORDER_STATUS = "SELECT code,name from StatusList where type='o' ";

	private static final String GET_ALLPROJECT_MANAGER = " select s.salesId,s.resourceName from SalesRep s where s.function = 'PROJECT_SUPPORT' ";
	private static final String GET_ALLSALES_MANAGER = "select s.salesId,s.resourceName from SalesRep s where s.function = 'SALES_CONTACT' ";
	private static final String GET_ALLTECHACCOUNT_MANAGER = "select s.salesId,s.resourceName from SalesRep s where s.function = 'TECH_SUPPORT'";
	private static final String GET_PRIMER_NAME = "select id, name from DsPrimerName order by name";
	
	 
	/**
	 * @author Golf
	 * @param name
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public List<DropDownDTO> getSpecDropDownList(String name) {
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		if (name != null) {
			if(name.equals(SpecDropDownListName.ALL_COUNTRY.value())){
				query = createQuery(COUNTRY_NAME);
			}
			if (name.equals(SpecDropDownListName.CUSTOMER_ROLE.value())) {
				query = createQuery(CUSTOMER_ROLE);
			}
			if (name.equals(SpecDropDownListName.GET_ALLORDER_STATUS.value())) {
				query = createQuery(GET_ALLORDER_STATUS);
			}
			if (name.equals(SpecDropDownListName.GET_ALLPROJECT_MANAGER.value())) {
				query = createQuery(GET_ALLPROJECT_MANAGER);
			}
			if (name.equals(SpecDropDownListName.GET_ALLSALES_MANAGER.value())) {
				query = createQuery(GET_ALLSALES_MANAGER);
			}
			if (name.equals(SpecDropDownListName.GET_ALLTECHACCOUNT_MANAGER
					.value())) {
				query = createQuery(GET_ALLTECHACCOUNT_MANAGER);
			}

			if (name.equals(SpecDropDownListName.CATALOG.value())) {
				query = createQuery(CATALOG);
			}
			if (name.equals(SpecDropDownListName.COUNTRY_NAME.value())) {
				query = createQuery(COUNTRY_NAME);
			}
			if (name.equals(SpecDropDownListName.SEQUENCE.value())) {
				query = createQuery(SEQUENCE);
			}
			if (name.equals(SpecDropDownListName.CASE_SEND_EMAILTO.value())) {
				query = createQuery(CASE_SEND_EMAILTO);
			}
			if (name.equals(SpecDropDownListName.RFM_VALUE.value())) {
				query = createQuery(RFM_VALUE);
			}
			if (name.equals(SpecDropDownListName.VECTOR.value())) {
				query = createQuery(VECTOR);
			}
			if (name.equals(SpecDropDownListName.TERRITORY.value())) {
				query = createQuery(TERRITORY);
			}
			if (name.equals(SpecDropDownListName.CATEGORY.value())) {
				query = createQuery(CATEGORY);
			}
			if (name.equals(SpecDropDownListName.SHIP_ZONE.value())) {
				query = createQuery(SHIP_ZONE);
			}
			if (name.equals(SpecDropDownListName.COMPR_SHIP_METHOD.value())) {
				query = createQuery(COMPR_SHIP_METHOD);
			}
			if (name.equals(SpecDropDownListName.WAREHOUSE.value())) {
				query = createQuery(WAREHOUSE);
			}
			if (name.equals(SpecDropDownListName.QUOTE_STATUS_LIST.value())) {
				query = createQuery(STATUS_LIST, "Q");
			}
			if (name.equals(SpecDropDownListName.ORDER_STATUS_LIST.value())) {
				query = createQuery(STATUS_LIST, "O");
			}
			if (name.equals(SpecDropDownListName.DEPARTMENT_FUNCTION.value())) {
				query = createQuery(DEPARTMENT_FUNCTION);
			}
			if (name.equals(SpecDropDownListName.PRODUCT_CLASSIFICATION.value())) {
				query = createQuery(PRODUCT_CLASSIFICATION);
			}
			if (name.equals(SpecDropDownListName.SERVICE_CLASSIFICATION.value())) {
				query = createQuery(SERVICE_CLASSIFICATION);
			}
			if (name.equals(SpecDropDownListName.N_TERMINAL.value())) {
				query = createQuery(TERMINAL_MODIFICATION, "N-Terminal");
			}
			if (name.equals(SpecDropDownListName.C_TERMINAL.value())) {
				query = createQuery(TERMINAL_MODIFICATION, "C-Terminal");
			}
			if (name.equals(SpecDropDownListName.MODIFICATION.value())) {
				query = createQuery(TERMINAL_MODIFICATION, "Normal");
			}
			if (name.equals(SpecDropDownListName.QUOTE_TYPE.value())) {
				Integer listId = findUnique(DROPDOWN_TYPE, "QUOTE_TYPE");
				query = createQuery(DROPDOWN_LIST_TYPE, listId);
			}
			if (name.equals(SpecDropDownListName.ORDER_TYPE.value())) {
				Integer listId = findUnique(DROPDOWN_TYPE, "ORDER_TYPE");
				query = createQuery(DROPDOWN_LIST_TYPE, listId);
			}
			if (name.equals(SpecDropDownListName.TAX_STATUS_NATIONAL.value())) {
				query = createQuery(TAX_STATUS_NATIONAL);
			}
			if (name.equals(SpecDropDownListName.TAX_STATUS_STATE.value())) {
				query = createQuery(TAX_STATUS_STATE);
			}
			if (name.equals(SpecDropDownListName.TAX_STATUS_COUNTRY.value())) {
				query = createQuery(TAX_STATUS_COUNTRY);
			}
			if (name.equals(SpecDropDownListName.COUNTRY_CODE.value())) {
				query = createQuery(COUNTRY_CODE);
			}
			if (name.equals(SpecDropDownListName.HOST_EXPS_ORGANISM.value())) {
				query = createQuery(HOST_EXPS_ORGANISM);
			}
			if (name.equals(SpecDropDownListName.PRICE_CATALOG.value())) {
				query = createQuery(PRICE_CATALOG);
			}
			if (name.equals(SpecDropDownListName.TECH_SUPPORT.value())) {
				query = createQuery(TECH_SUPPORT);
			}
			if (name.equals(SpecDropDownListName.PROJECT_SUPPORT.value())) {
				query = createQuery(PROJECT_SUPPORT);
			}
			if (name.equals(SpecDropDownListName.ORIGINAL_SOURCE.value())) {
				query = createQuery(ORIGINAL_SOURCE);
			}
			if (name.equals(SpecDropDownListName.PICK_LOCATION.value())) {
				query = createQuery(PICK_LOCATION);
			}
			if (name.equals(SpecDropDownListName.SALES_TERRITORY.value())) {
				query = createQuery(SALES_TERRITORY);
			}else if (name.equals(SpecDropDownListName.SALES_TERRITORY_OTHER.value())) {
				query = createQuery(SALES_TERRITORY_OTHER);
			}

			if (name.equals(SpecDropDownListName.SALES_GROUP.value())) {
				query = createQuery(SALES_GROUP);
			}
			if (name.equals(SpecDropDownListName.ORGANIZATION_CATEGORY.value())) {
				query = createQuery(ORGANIZATION_CATEGORY);
			}
			if (name.equals(SpecDropDownListName.ORGANIZATION_TYPE.value())) {
				query = createQuery(ORGANIZATION_TYPE);
			}
			if (name.equals(SpecDropDownListName.LANGUAGE_CODE.value())) {
				query = createQuery(LANGUAGE_CODE);
			}
			if (name.equals(SpecDropDownListName.CREDIT_LIMIT.value())) {
				query = createQuery(CREDIT_LIMIT);
			}
			if (name.equals(SpecDropDownListName.SHIP_METHOD.value())) {
				query = createQuery(SHIP_METHOD);
			}
			if (name.equals(SpecDropDownListName.SHIP_CARRIERS.value())) {

				query = createQuery(SHIP_CARRIERS);
			}
			if (name.equals(SpecDropDownListName.SALES_CONTACT.value())) {
				query = createQuery(SALES_CONTACT);
			}
			if (name.equals(SpecDropDownListName.PB_DEPARTMENT.value())) {
				query = createQuery(PB_DEPARTMENT);
			}
			if (name.equals(SpecDropDownListName.QUOTE_MEMO_TEMPLATE.value())) {
				query = createQuery(QUOTE_MEMO_TEMPLATE);
			}
			if (name.equals(SpecDropDownListName.ORDER_MEMO_TEMPLATE.value())) {
				query = createQuery(ORDER_MEMO_TEMPLATE);
			}
			if (name.equals(SpecDropDownListName.CURRENCY.value())) {
				query = createQuery(CURRENCY);
			}
			if (name.equals(SpecDropDownListName.PROMOTION_CODE.value())) {
				query = createQuery(PROMOTION_CODE);
			}
			if (name.equals(SpecDropDownListName.DECIPLINE_INTEREST.value())) {
				query = createQuery(INTEREST_AREA, "DR");
			}
			if (name.equals(SpecDropDownListName.APPLICATION_INTEREST.value())) {
				query = createQuery(INTEREST_AREA, "AT");
			}
			if (name.equals(SpecDropDownListName.PEPTIDE_QUANTITY.value())) {
				query = createQuery(PEPTIDE_QUANTITY);
			}
			if (name.equals(SpecDropDownListName.PEPTIDE_PURITY.value())) {
				query = createQuery(PEPTIDE_PURITY);
			}
			if (name.equals(SpecDropDownListName.PEPTIDE_PURITY_ARRAY.value())) {
				query = createQuery(PEPTIDE_PURITY_ARRAY);
			}
			if (name.equals(SpecDropDownListName.MAIL_GROUP_FUNCTION.value())) {
				Integer listId = findUnique(DROPDOWN_TYPE,
						"MAIL_GROUP_FUNCTION");
				query = createQuery(DROPDOWN_LIST_TYPE, listId);
			}
			if (name.equals(SpecDropDownListName.GET_PRIMER_NAME.value())) {
				query = createQuery(GET_PRIMER_NAME);
			}

			if (name.equals(SpecDropDownListName.ALL_DEPARTMENTS.value())) {
				query = createQuery(ALL_DEPARTMENT);
				list = query.list();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						DropDownDTO downListDTO = new DropDownDTO();
						Object[] obj = (Object[]) list.get(i);
						downListDTO.setId(obj[0].toString());
						downListDTO.setName((String) obj[1]);
						dropDownLists.add(downListDTO);
					}
				}
			}

			if (name.equals(SpecDropDownListName.TIME_ZONE.value())) {
				query = createQuery(TIME_ZONE);
				list = query.list();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Object[] obj = (Object[]) list.get(i);
						String code = (String) obj[1];
						String gmtCode = (String) obj[2];
						String description = (String) obj[3];
						StringBuilder sb = new StringBuilder();
						sb.append(code).append(" [").append(gmtCode)
								.append("] ").append(description);
						DropDownDTO downListDTO = new DropDownDTO();
						downListDTO.setId(code);
						downListDTO.setName(sb.toString());
						dropDownLists.add(downListDTO);
					}
				}
			} else {
				list = query.list();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Object[] obj = (Object[]) list.get(i);
						DropDownDTO downListDTO = new DropDownDTO();
						downListDTO.setId(obj[0].toString());
						downListDTO.setName((String) obj[1]);
						if (name.equals(SpecDropDownListName.SHIP_METHOD
								.value())
								|| name.equals(SpecDropDownListName.CURRENCY
										.value())
								|| name.equals(SpecDropDownListName.SHIP_CARRIERS
										.value())
								|| name.equals(SpecDropDownListName.COMPR_SHIP_METHOD
										.value())) {
							downListDTO.setValue((String) obj[2]);
						}
						dropDownLists.add(downListDTO);
					}
				}
			}
			return dropDownLists;
		}
		return null;
	}

	/**
	 * @author Golf
	 * @param name
	 * @param countryCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GetInfoByRegionDTO> getTerritoryByCountry(String countryCode,
			String stateCode, String zipCode) {
		List list = null;
		List<GetInfoByRegionDTO> getInfoByRegionDTOs = new ArrayList<GetInfoByRegionDTO>();
		Query query = null;
		if (countryCode == null || ("").equals(countryCode)) {
			return null;
		}
		if (stateCode != null && !("").equals(stateCode)) {
			if (zipCode != null && !("").equals(zipCode)) {
				query = createQuery(TERRITORY_BY_ZIP, countryCode, stateCode,
						zipCode, zipCode);
				list = query.list();
			}
			if (list == null || list.isEmpty()) {
				query = createQuery(TERRITORY_BY_STATE, countryCode, stateCode);
				list = query.list();
				if (list == null || list.isEmpty()) {
					query = createQuery(TERRITORY_BY_COUNTRY, countryCode);
					list = query.list();
				}
			}
		} else {
			query = createQuery(TERRITORY_BY_COUNTRY, countryCode);
			list = query.list();
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				GetInfoByRegionDTO getInfoByRegionDTO = new GetInfoByRegionDTO();
				Object[] objs = (Object[]) obj;
				getInfoByRegionDTO.setId(objs[0].toString());
				getInfoByRegionDTO.setName(objs[1].toString());
				getInfoByRegionDTO
						.setTerritoryClassification(objs[2] == null ? null
								: objs[2].toString());
				getInfoByRegionDTOs.add(getInfoByRegionDTO);
			}
		}
		return getInfoByRegionDTOs;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownListDTO> getContactInfo(String addrType, Integer custNo) {
		List list = null;
		List<DropDownDTO> dropDowns1;
		List<DropDownDTO> dropDowns2;
		List<DropDownDTO> dropDowns3;
		List<DropDownListDTO> dropDownLists = new ArrayList<DropDownListDTO>();
		Query query = null;
		if (custNo != null) {
			query = createQuery(CONTACT_INFO_BY_CUSTOMER, addrType, custNo);
			list = query.list();
			if (list != null && list.size() > 0) {
				dropDowns1 = new ArrayList<DropDownDTO>();
				dropDowns2 = new ArrayList<DropDownDTO>();
				dropDowns3 = new ArrayList<DropDownDTO>();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					String busEmail = (String) obj[0];
					String busPhone = (String) obj[1];
					String addrLine1 = (String) obj[2];
					String addrLine2 = (String) obj[3];
					String addrLine3 = (String) obj[4];
					String city = (String) obj[5];
					String state = (String) obj[6];
					String zipCode = (String) obj[7];
					String country = (String) obj[8];
					DropDownDTO downListDTO1 = new DropDownDTO();
					downListDTO1.setId(busEmail);
					downListDTO1.setName(busEmail);
					dropDowns1.add(downListDTO1);
					DropDownDTO downListDTO2 = new DropDownDTO();
					downListDTO2.setId(busPhone);
					downListDTO2.setName(busPhone);
					dropDowns2.add(downListDTO2);
					DropDownDTO downListDTO3 = new DropDownDTO();
					StringBuilder sb = new StringBuilder();
					if (addrLine1 != null && addrLine2 == null
							&& addrLine3 == null) {
						sb.append(addrLine1).append(", ");
					} else if (addrLine1 == null && addrLine2 != null
							&& addrLine3 == null) {
						sb.append(addrLine2).append(", ");
					} else if (addrLine1 != null && addrLine2 != null
							&& addrLine3 == null) {
						sb.append(addrLine1).append(" ").append(addrLine2)
								.append(", ");
					} else {
						sb.append(addrLine1 == null ? "" : (addrLine1 + " "))
								.append(addrLine2 == null ? ""
										: (addrLine2 + " "))
								.append(addrLine3 == null ? ""
										: (addrLine3 + ", "));
					}
					if (city != null && state == null) {
						sb.append(city).append(" ");
					} else {
						sb.append(city == null ? "" : (city + ", ")).append(
								state == null ? "" : (state + " "));
					}
					if (zipCode != null && country == null) {
						sb.append(zipCode);
					} else {
						sb.append(zipCode == null ? "" : (zipCode + ", "))
								.append(country == null ? "" : (country));
					}
					downListDTO3.setId(sb.toString());
					downListDTO3.setName(sb.toString());
					dropDowns3.add(downListDTO3);
				}
				DropDownListDTO listDTO1 = new DropDownListDTO();
				listDTO1.setName("CONTACT_EMAIL");
				listDTO1.setDropDownDTOs(dropDowns1);
				dropDownLists.add(listDTO1);
				DropDownListDTO listDTO2 = new DropDownListDTO();
				listDTO2.setName("CONTACT_PHONE");
				listDTO2.setDropDownDTOs(dropDowns2);
				dropDownLists.add(listDTO2);
				DropDownListDTO listDTO3 = new DropDownListDTO();
				listDTO3.setName("CONTACT_ADDRESS");
				listDTO3.setDropDownDTOs(dropDowns3);
				dropDownLists.add(listDTO3);
			}
			return dropDownLists;
		}
		return null;
	}

	
	@SuppressWarnings("rawtypes")
	public List<AllCountryDTO> getAllCountryState() {
		List<AllCountryDTO> allCountryDTOs = new ArrayList<AllCountryDTO>();
		Query query = null;
		query = createQuery(ALL_COUNTRY);
		List list = query.list();
		query = createQuery(ALL_STATE);
		List stateList = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Integer countryId = Integer.parseInt((obj[0] == null) ? "0"
						: obj[0].toString());
				String countryCode = (String) obj[1];
				String countryName = (String) obj[2];
				AllCountryDTO countryDTO = new AllCountryDTO();

				countryDTO.setCountryCode(countryCode);
				countryDTO.setName(countryName);

				List<AllStateDTO> allStateDTOs = new ArrayList<AllStateDTO>();
				if (stateList != null && stateList.size() > 0) {
					for (int i1 = 0; i1 < stateList.size(); i1++) {
						Object[] obj1 = (Object[]) stateList.get(i1);
						String stateCode = (obj1[0].toString());
						String stateName = (obj1[1].toString());
						Integer stateCountryId = Integer
								.parseInt((obj1[2] == null) ? "0" : obj1[2]
										.toString());
						if (stateCountryId.intValue() == countryId.intValue()) {
							AllStateDTO stateDTO = new AllStateDTO();
							stateDTO.setStateCode(stateCode);
							stateDTO.setName(stateName);
							allStateDTOs.add(stateDTO);
							stateList.remove(i1);// 匹配后从list中删除
							i1 = i1 - 1;
						}
					}
					countryDTO.setAllStateDTOs(allStateDTOs);

				}
				allCountryDTOs.add(countryDTO);
			}
		}
		return allCountryDTOs;
	}

	@SuppressWarnings("rawtypes")
	public List<DropDownDTO> getTemplateNameList(TemplateType templateType,
			Integer userId) {
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		if (templateType.equals(TemplateType.QUOTE)) {
			query = createQuery(QUOTE_TEMPLATE_NAME, userId);
		} else if (templateType.equals(TemplateType.ORDER)) {
			query = createQuery(ORDER_TEMPLATE_NAME, userId);
		}
		list = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				DropDownDTO downListDTO = new DropDownDTO();
				downListDTO.setId(obj[0].toString());
				downListDTO.setName(obj[1].toString());
				dropDownLists.add(downListDTO);
			}
		}
		return dropDownLists;
	}

	@SuppressWarnings("rawtypes")
	public List<DropDownDTO> getPickLocationList(Integer warehouseId) {
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = createQuery(PICK_LOCATION, warehouseId);
		List list = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				DropDownDTO downListDTO = new DropDownDTO();
				downListDTO.setId(obj[0].toString());
				downListDTO.setName((String) obj[1]);
				dropDownLists.add(downListDTO);
			}
		}
		return dropDownLists;
	}

	@SuppressWarnings("rawtypes")
	public List<DropDownListDTO> getShipRateByZone(final Integer zoneId) {
		List<DropDownListDTO> dropDownLists = new ArrayList<DropDownListDTO>();
		Query query = null;
		List list = null;
		List<DropDownDTO> dropDowns;
		if (zoneId != null) {
			query = createQuery(STANDARD_SHIP_METHOD, zoneId);
			list = query.list();
			if (list != null && list.size() > 0) {
				dropDowns = new ArrayList<DropDownDTO>();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					String methodId = obj[0].toString();
					String methodName = (String) obj[1];
					DropDownDTO downListDTO1 = new DropDownDTO();
					downListDTO1.setId(methodId);
					downListDTO1.setName(methodName);
					List<StandardShipRate> standardShipRateList = standardShipRateDao
							.findBy("shipMethodId", Integer.parseInt(methodId));
					if (standardShipRateList != null
							&& standardShipRateList.size() > 0) {
						List<ShipRate> rateList = new ArrayList<ShipRate>();
						for (StandardShipRate standardShipRate : standardShipRateList) {
							ShipRate shipRate = dozer.map(standardShipRate,
									ShipRate.class);
							shipRate.setId(null);
							rateList.add(shipRate);
						}
						downListDTO1.setShipRateList(rateList);
					}
					dropDowns.add(downListDTO1);
				}
				DropDownListDTO listDTO1 = new DropDownListDTO();
				listDTO1.setName(zoneId.toString());
				List<StandardShipZoneDetail> standardShipZoneDetailList = standardShipZoneDetailDao
						.findBy("zoneType", zoneId);
				if (standardShipZoneDetailList != null
						&& standardShipZoneDetailList.size() > 0) {
					List<ShipZone> zoneList = new ArrayList<ShipZone>();
					for (StandardShipZoneDetail standardShipZoneDetail : standardShipZoneDetailList) {
						ShipZone shipZone = dozer.map(standardShipZoneDetail,
								ShipZone.class);
						shipZone.setId(null);
						zoneList.add(shipZone);
					}
					listDTO1.setShipZoneList(zoneList);
				}
				listDTO1.setDropDownDTOs(dropDowns);
				dropDownLists.add(listDTO1);
			}
			return dropDownLists;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<EnzymeDTO> getEnzymeSequence(){
		List<EnzymeDTO> enzymeList = new ArrayList<EnzymeDTO>();
		Query query = createQuery(SEQUENCE);
		List list = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				EnzymeDTO enzymeDTO = new EnzymeDTO();
				enzymeDTO.setName(obj[0].toString());
				enzymeDTO.setSequence((String) obj[1]);
				enzymeList.add(enzymeDTO);
			}
		}
		return enzymeList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object testProc(final Integer in) {
		final Object obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				this.sessionFactory);
		obj = hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = s.createSQLQuery("{call discountavg(?)}");
				query.setParameter(0, in);
				BigDecimal d = (BigDecimal) query.uniqueResult();
				return d;
			}
		});
		return obj;
	}

	// add by zhanghuibin
	public List<DropDownDTO> getSpecDropDownList(String name, String cri) {
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		if (cri == null) {
			query = createQuery("select s.methodId, s.name, s.carrier from ShipMethod s ");
		} else {
			query = createQuery(
					"select s.methodId, s.name, s.carrier from ShipMethod s where s.carrier=?",
					cri);
		}
		list = query.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				DropDownDTO downListDTO = new DropDownDTO();
				downListDTO.setId(obj[0].toString());
				downListDTO.setName((String) obj[1]);
				if (name.equals(SpecDropDownListName.SHIP_METHOD.value())) {
					downListDTO.setValue((String) obj[2]);
				}
				dropDownLists.add(downListDTO);
			}
		}
		return dropDownLists;
	}
	//add by lizhang
	public DropDownDTO getSpecDropDown(String name, Integer id) {
		List list = null;
		List<DropDownDTO> dropDownLists = new ArrayList<DropDownDTO>();
		Query query = null;
		query = createQuery("select s.methodId, s.name, s.carrier from ShipMethod s where s.methodId="+id);
		list = query.list();
		DropDownDTO downListDTO = new DropDownDTO();
		if (list != null && list.size() > 0) {
			Object[] obj = (Object[]) list.get(0);
			downListDTO.setId(obj[0].toString());
			downListDTO.setName((String) obj[1]);
			if (name.equals(SpecDropDownListName.SHIP_METHOD.value())) {
				downListDTO.setValue((String) obj[2]);
			}
		}
		return downListDTO;
	}
}
