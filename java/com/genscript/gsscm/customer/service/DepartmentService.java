package com.genscript.gsscm.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.customer.dao.DepartmentBillingDao;
import com.genscript.gsscm.customer.dao.DepartmentDao;
import com.genscript.gsscm.customer.dao.DepartmentFunctionDao;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationNoteDao;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.entity.DepartmentBilling;
import com.genscript.gsscm.customer.entity.DepartmentFunction;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.OrganizationNote;

@Service
@Transactional
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private NoteDocumentDao noteDocumentDao;
	@Autowired
	private OrganizationNoteDao organizationNoteDao;
	@Autowired
	private DepartmentBillingDao departmentBillingDao;
	@Autowired
	private DepartmentFunctionDao departmentFunctionDao;
	@Autowired
	private DozerBeanMapper dozer;
	
	/**
	 * 批量删除department, 也就是更新状态.
	 * 
	 * @param orgIdList
	 */
	public void delDepartment(List<Integer> idList) {
		this.departmentDao.batchUpdateDepartment(idList,
				StatusType.INACTIVE_FLAG.value);
	}

	/**
	 * 获得某个Department
	 * 
	 * @param id
	 * @return
	 */
	public Departments getDepartment(Integer id) {
		Departments obj = this.departmentDao.get(id);
		return obj;
	}

	/**
	 * 保存或更新 Department
	 * 
	 * @param department
	 * @param loginUserId
	 */
	public void saveDepartment(List<CustNoteDTO> noteList, Departments department, DepartmentBilling billing, Integer loginUserId) {
		department.setCreationDate(new Date());
		department.setCreatedBy(loginUserId);
		department.setModifyDate(new Date());
		department.setModifiedBy(loginUserId);
		this.departmentDao.save(department);
		this.attachNote(department, noteList);
		if (billing != null) {
			this.attachBilling(department, billing);
		}
	}
	
	/*
	 * 保存Departments时级联保存Billing.
	 * 
	 * @param department
	 * @param billing
	 */
	private void attachBilling(Departments department,
			DepartmentBilling billing) {
		billing.setDeptId(department.getDeptId());
		billing.setCreatedBy(department.getCreatedBy());
		billing.setModifiedBy(department.getCreatedBy());
		billing.setCreationDate(new Date());
		billing.setModifyDate(new Date());
		this.departmentBillingDao.save(billing);
	}
	
	/**
	 * 保存Departments的同时保存DepartmentsNotes.及其关联的多个document.
	 * 
	 * @param customer
	 * @param dtoList
	 * @return
	 */
	private void attachNote(Departments department, List<CustNoteDTO> dtoList) {
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
			note.setDeptId(department.getDeptId());
			note.setCreatedBy(department.getModifiedBy());
			note.setModifiedBy(department.getModifiedBy());
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
					doc.setCreatedBy(department.getModifiedBy());
					doc.setModifiedBy(department.getModifiedBy());
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
	 * 根据orgId获得所有关联的Department
	 * 
	 * @param page
	 * @param orgId
	 * @return
	 */
	public Page<Departments> searchDepartment(Page<Departments> page,
			Integer orgId) {
		return this.departmentDao.searchDepartment(page, orgId);
	}
	
	@Transactional(readOnly = true)
	public List<CustNoteDTO> getNoteList(Integer departmentId) {
		List<CustNoteDTO> dtoList = new ArrayList<CustNoteDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_deptId", departmentId);
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
	 * 获得一个Department的Billing.
	 * 
	 * @param departmentId
	 * @return
	 */
	public DepartmentBilling getBilling(Integer departmentId) {
		return this.departmentBillingDao.getBilling(departmentId);
	}
	
	/**
	 * 获得所有DepartmentFunction
	 * @return
	 */
	public List<DepartmentFunction> getAllDepartmentFunction() {
		return this.departmentFunctionDao.getAll();
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
