package com.genscript.gsscm.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.PbLanguageDao;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationBillingDao;
import com.genscript.gsscm.customer.dao.OrganizationCategoryDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.OrganizationGroupDao;
import com.genscript.gsscm.customer.dao.OrganizationNoteDao;
import com.genscript.gsscm.customer.dao.OrganizationPreferenceDao;
import com.genscript.gsscm.customer.dao.OrganizationTypeDao;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.OrganizationBilling;
import com.genscript.gsscm.customer.entity.OrganizationCategory;
import com.genscript.gsscm.customer.entity.OrganizationGroup;
import com.genscript.gsscm.customer.entity.OrganizationNote;
import com.genscript.gsscm.customer.entity.OrganizationPreference;
import com.genscript.gsscm.customer.entity.OrganizationType;

@Service
@Transactional
public class OrganizationService {

	@Autowired
	private OrganizationGroupDao organizationGroupDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private OrganizationTypeDao organizationTypeDao;
	@Autowired
	private OrganizationCategoryDao organizationCategoryDao;
	@Autowired
	private OrganizationNoteDao organizationNoteDao;
	@Autowired
	private NoteDocumentDao noteDocumentDao;
	@Autowired
	private OrganizationBillingDao organizationBillingDao;
	@Autowired
	private OrganizationPreferenceDao organizationPreferenceDao;
	@Autowired
	private PbLanguageDao pbLanguageDao;
	@Autowired
	private DozerBeanMapper dozer;

	private StringBuffer sl = new StringBuffer();

	public StringBuffer getSl() {
		return sl;
	}

	public void setSl(StringBuffer sl) {
		this.sl = sl;
	}

	public Page<OrganizationGroup> searchOrganizationGroup(
			Page<OrganizationGroup> page, List<PropertyFilter> filters) {
		if (filters == null || filters.isEmpty()) {// 没有搜索条件
			page = this.organizationGroupDao.getAll(page);
		} else {
			page = this.organizationGroupDao.findPage(page, filters);
		}
		return page;
	}

	/**
	 * 批量删除OrganizationGroup.
	 * 
	 * @param idList
	 */
	public void delOrganizationGroup(List<Integer> idList, String note) {
		if (idList != null && !idList.isEmpty()) {
			this.organizationDao.detachOrgGroup(idList);
			System.out.println(idList.size());
			for (int o = 0; o < idList.size(); o++) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>"
						+ idList.get(o).toString());
			}
			this.organizationGroupDao.batchUpdateStatus(idList,
					StatusType.INACTIVE.value, note);
		}
	}

	/**
	 * 批量删除organization, 也就是更新状态.
	 * 
	 * @param orgIdList
	 */
	public void delOrganization(List<Integer> orgIdList, String note) {
		this.organizationDao.batchUpdateOrganization(orgIdList,
				StatusType.INACTIVE_FLAG.value, note);
	}

	/**
	 * 获得某个具体的OrganizationGroup.
	 * 
	 * @param id
	 * @return
	 */
	public OrganizationGroup getOrganizationGroup(Integer id) {
		return this.organizationGroupDao.get(id);
	}

	/**
	 * 获得某个Organization
	 * 
	 * @param id
	 * @return
	 */
	public Organization getOrganization(Integer id) {
		Organization obj = this.organizationDao.getById(id);
		if (obj.getParentOrgId() != null) {
			Organization parentObj = this.organizationDao.getById(obj
					.getParentOrgId());
			obj.setParentOrganization(parentObj);
		}
		if (obj.getTypeId() != null) {
			OrganizationType orgType = organizationTypeDao.getById(obj
					.getTypeId());
			obj.setOrganizationType(orgType);
		}
		if (obj.getOrgGroupId() != null) {
			OrganizationGroup orgGroup = organizationGroupDao.getById(obj
					.getOrgGroupId());
			obj.setOrganizationGroup(orgGroup);
		}

		if (obj.getLangCode() != null
				&& StringUtils.isNotEmpty(obj.getLangCode())) {
			obj.setLangName(this.pbLanguageDao.getLangName(obj.getLangCode()));
		}
		return obj;
	}

	/**
	 * 新增或更新OrganizationGroup
	 * 
	 * @param orgGroup
	 */
	public void saveOrgGroup(OrganizationGroup orgGroup, Integer loginUserId,
			List<Integer> detachOrgIdList) {
		orgGroup.setCreationDate(new Date());
		orgGroup.setCreatedBy(loginUserId);
		orgGroup.setModifyDate(new Date());
		orgGroup.setModifiedBy(loginUserId);
		this.organizationGroupDao.save(orgGroup);
		if (detachOrgIdList != null && !detachOrgIdList.isEmpty()) {
			this.organizationDao.detachOrgGroup(detachOrgIdList);
		}
	}

	/**
	 * 保存或更新 Organization, billing info, instructions/notes
	 * 
	 * @param organization
	 * @param loginUserId
	 */
	public void saveOrganization(List<CustNoteDTO> noteList,
			Organization organization, OrganizationBilling billing,
			OrganizationPreference preference, Integer loginUserId) {
		organization.setCreationDate(new Date());
		organization.setCreatedBy(loginUserId);
		organization.setModifyDate(new Date());
		organization.setModifiedBy(loginUserId);
		this.organizationDao.save(organization);
		if (organization.getSubOrgIdList() != null
				&& !organization.getSubOrgIdList().isEmpty()) {
			// 批量新增一组organization作为子级列表
			this.organizationDao.batchUpdateParent(organization.getOrgId(),
					organization.getSubOrgIdList());
		}
		if (organization.getDelSubOrgIdList() != null
				&& !organization.getDelSubOrgIdList().isEmpty()) {
			// 批量删除一组子级列表, 即去除关联关系.
			this.organizationDao.batchDetachParent(organization.getOrgId(),
					organization.getDelSubOrgIdList());
		}

		this.attachNote(organization, noteList);
		if (billing != null) {
			this.attachBilling(organization, billing);
		}
		if (preference != null) {
			this.attachPreference(organization, preference);
		}

	}

	/**
	 * 保存Customer的同时保存CustomerNotes.及其关联的多个document.
	 * 
	 * @param organization
	 * @param dtoList
	 * @return
	 */
	private void attachNote(Organization organization, List<CustNoteDTO> dtoList) {
		if (dtoList == null || dtoList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (CustNoteDTO dto : dtoList) {
			if (dto.getDelDocIdList() != null
					&& !dto.getDelDocIdList().isEmpty()) {
				this.noteDocumentDao.delDocList(dto.getDelDocIdList());
			}
			OrganizationNote note = this.dozer.map(dto, OrganizationNote.class);
			note.setOrgId(organization.getOrgId());
			note.setCreatedBy(organization.getModifiedBy());
			note.setModifiedBy(organization.getModifiedBy());
			note.setCreationDate(now);
			note.setModifyDate(now);
			boolean bDocFlag = false;
			if (dto.getDocumentList() != null
					&& !dto.getDocumentList().isEmpty()) {
				bDocFlag = true;
				note.setDocFlag("Y");
			}
			this.organizationNoteDao.save(note);
			if (bDocFlag) {
				for (NoteDocument doc : dto.getDocumentList()) {
					doc.setCreatedBy(organization.getModifiedBy());
					doc.setModifiedBy(organization.getModifiedBy());
					doc.setCreationDate(now);
					doc.setModifyDate(now);
					doc.setRefId(note.getId());
					doc.setRefType(DocumentType.ORGANIZATION_NOTE.value());
					this.noteDocumentDao.save(doc);
				}
			}

		}
	}

	/**
	 * 保存Organization时级联保存Billing.
	 * 
	 * @param organization
	 * @param billing
	 */
	private void attachBilling(Organization organization,
			OrganizationBilling billing) {
		billing.setOrgId(organization.getOrgId());
		billing.setCreatedBy(organization.getCreatedBy());
		billing.setModifiedBy(organization.getCreatedBy());
		billing.setCreationDate(new Date());
		billing.setModifyDate(new Date());
		this.organizationBillingDao.save(billing);
	}

	/**
	 * 保存Organization时级联保存order preference.
	 * 
	 * @param organization
	 * @param preference
	 * @author wangsf
	 * @serialData 2011-03-03
	 */
	private void attachPreference(Organization organization,
			OrganizationPreference preference) {
		preference.setOrgId(organization.getOrgId());
		preference.setCreatedBy(organization.getCreatedBy());
		preference.setModifiedBy(organization.getCreatedBy());
		preference.setCreationDate(new Date());
		preference.setModifyDate(new Date());
		this.organizationPreferenceDao.save(preference);
	}

	/**
	 * 获得一个Organization的所有note， 每个Note又包含documentList.
	 * 
	 * @param orgId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CustNoteDTO> getNoteList(Integer orgId) {
		List<CustNoteDTO> dtoList = new ArrayList<CustNoteDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orgId", orgId);
		filterList.add(quoteFilter);
		List<OrganizationNote> noteList = this.organizationNoteDao
				.find(filterList);
		if (noteList != null) {
			for (OrganizationNote note : noteList) {
				CustNoteDTO dto = dozer.map(note, CustNoteDTO.class);
				List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
				PropertyFilter refIdFilter = new PropertyFilter("EQI_refId",
						note.getId());
				PropertyFilter refTypeFilter = new PropertyFilter(
						"EQS_refType", DocumentType.ORGANIZATION_NOTE.value());
				docFilterList.add(refIdFilter);
				docFilterList.add(refTypeFilter);
				List<NoteDocument> docList = this.noteDocumentDao
						.find(docFilterList);
				dto.setDocumentList(docList);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 根据orgGroupId获得所有关联的Organization
	 * 
	 * @param page
	 * @param orgGroupId
	 * @return
	 */
	public Page<Organization> searchOrganization(Page<Organization> page,
			Integer orgGroupId) {
		page = this.organizationDao.searchOrganization(page, orgGroupId);
		if (page.getResult() != null) {
			for (Organization org : page.getResult()) {
				if (org.getParentOrgId() != null) {
					Organization parentObj = organizationDao.get(org
							.getParentOrgId());
					org.setParentOrganization(parentObj);
				}
				if (org.getTypeId() != null) {
					OrganizationType type = organizationTypeDao.get(org
							.getTypeId());
					org.setOrganizationType(type);
				}
				if (org.getOrgGroupId() != null) {
					OrganizationGroup group = this.organizationGroupDao.get(org
							.getOrgGroupId());
					org.setOrganizationGroup(group);
				}
				if (org.getLangCode() != null
						&& StringUtils.isNotEmpty(org.getLangCode())) {
					org.setLangName(this.pbLanguageDao.getLangName(org
							.getLangCode()));
				}
			}
		}
		return page;
	}

	/**
	 * 分页查找一个Organization的子级列表
	 * 
	 * @param page
	 * @param orgId
	 * @return
	 * @author wangsf
	 * @serialData 2011-03-03
	 */
	public Page<Organization> searchSubList(Page<Organization> page,
			Integer orgId) {
		page = this.organizationDao.searchSubList(page, orgId);
		if (page.getResult() != null) {
			for (Organization org : page.getResult()) {
				if (org.getParentOrgId() != null) {
					Organization parentObj = organizationDao.get(org
							.getParentOrgId());
					org.setParentOrganization(parentObj);
				}
				if (org.getTypeId() != null) {
					OrganizationType type = organizationTypeDao.get(org
							.getTypeId());
					org.setOrganizationType(type);
				}
				if (org.getOrgGroupId() != null) {
					OrganizationGroup group = this.organizationGroupDao.get(org
							.getOrgGroupId());
					org.setOrganizationGroup(group);
				}
				if (org.getLangCode() != null
						&& StringUtils.isNotEmpty(org.getLangCode())) {
					org.setLangName(this.pbLanguageDao.getLangName(org
							.getLangCode()));
				}
			}
		}
		return page;
	}

	/**
	 * 分页查找一个Organization的不是它子级的列表
	 * 
	 * @param page
	 * @param orgId
	 * @return
	 * @author wangsf
	 * @serialData 2011-03-11
	 */
	public Page<Organization> searchUnSubList(Page<Organization> page,
			Integer orgId) {
		page = this.organizationDao.searchUnSubList(page, orgId);
		if (page.getResult() != null) {
			for (Organization org : page.getResult()) {
				if (org.getTypeId() != null) {
					OrganizationType type = organizationTypeDao.get(org
							.getTypeId());
					org.setOrganizationType(type);
				}
				if (org.getOrgGroupId() != null) {
					OrganizationGroup group = this.organizationGroupDao.get(org
							.getOrgGroupId());
					org.setOrganizationGroup(group);
				}
				if (org.getLangCode() != null
						&& StringUtils.isNotEmpty(org.getLangCode())) {
					org.setLangName(this.pbLanguageDao.getLangName(org
							.getLangCode()));
				}
			}
		}
		return page;
	}

	/**
	 * 根据Organization的name, 或OrganizationGroup的name属性查找Organization
	 * 
	 * @param page
	 * @param orgName
	 * @param orgGroupId
	 * @return
	 */
	public Page<Organization> searchOrganization(Page<Organization> page,
			String orgName, String orgGroupId) {
		page = this.organizationDao.searchOrganization(page, orgName,
				orgGroupId);
		if (page.getResult() != null) {
			for (Organization obj : page.getResult()) {
				if (obj.getParentOrgId() != null) {
					Organization parentObj = organizationDao.getById(obj
							.getParentOrgId());
					if (parentObj != null && !"".equals(parentObj)) {
						obj.setParentOrganization(parentObj);
					}
				}
				if (obj.getTypeId() != null) {
					OrganizationType orgType = organizationTypeDao.get(obj
							.getTypeId());
					obj.setOrganizationType(orgType);
				}
				if (obj.getOrgGroupId() != null) {
					OrganizationGroup orgGroup = organizationGroupDao.get(obj
							.getOrgGroupId());
					obj.setOrganizationGroup(orgGroup);
				}
				if (obj.getLangCode() != null
						&& StringUtils.isNotEmpty(obj.getLangCode())) {
					obj.setLangName(this.pbLanguageDao.getLangName(obj
							.getLangCode()));
				}
			}
		}
		return page;
	}

	/**
	 * 根据orgGroupCode获得OrgGroup
	 */
	public OrganizationGroup findByOrgGroupCode(String orgGroupCode) {
		if (orgGroupCode == null || StringUtils.isEmpty(orgGroupCode)) {
			return null;
		}
		return this.organizationGroupDao
				.findUniqueBy("groupCode", orgGroupCode);
	}

	/**
	 * 获得所有OrganizationType
	 * 
	 * @return
	 */
	public List<OrganizationType> getAllOrganizationType() {
		return this.organizationTypeDao.getAll();
	}

	/**
	 * 获得所有OrganizationCategory
	 * 
	 * @return
	 */
	public List<OrganizationCategory> getAllOrganizationCategory() {
		return this.organizationCategoryDao.getAll();
	}

	/**
	 * 获得所有OrganizationGroup
	 * 
	 * @return
	 */
	public List<OrganizationGroup> getAllOrganizationGroup() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter statusF = new PropertyFilter("EQS_status",
				StatusType.ACTIVE.value);
		filters.add(statusF);
		return this.organizationGroupDao.find(filters);
	}

	/**
	 * 获得一个Organization的Billing.
	 * 
	 * @param orgId
	 * @return
	 */
	public OrganizationBilling getBilling(Integer orgId) {
		return this.organizationBillingDao.getBilling(orgId);
	}

	/**
	 * 获得一个Organization的Order Preference.
	 * 
	 * @param orgId
	 * @return
	 */
	public OrganizationPreference getPreference(Integer orgId) {
		return this.organizationPreferenceDao.getPreference(orgId);
	}

	enum StatusType {
		ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), ACTIVE_FLAG("Y"), INACTIVE_FLAG(
				"N");
		private final String value;

		StatusType(String v) {
			value = v;
		}

		public String getValue() {
			return this.value;
		}
	}

}
