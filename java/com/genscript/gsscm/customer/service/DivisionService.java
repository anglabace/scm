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
import com.genscript.gsscm.customer.dao.DivisionBillingDao;
import com.genscript.gsscm.customer.dao.DivisionDao;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationNoteDao;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.entity.DivisionBilling;
import com.genscript.gsscm.customer.entity.Divisions;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.OrganizationNote;

@Service
@Transactional
public class DivisionService {
	@Autowired
	private DivisionDao divisionDao;
	@Autowired
	private NoteDocumentDao noteDocumentDao;
	@Autowired
	private OrganizationNoteDao organizationNoteDao;
	@Autowired
	private DivisionBillingDao divisionBillingDao;
	@Autowired
	private DozerBeanMapper dozer;
	
	/**
	 * 批量删除division, 也就是更新状态.
	 * 
	 * @param orgIdList
	 */
	public void delDivision(List<Integer> orgIdList) {
		this.divisionDao.batchUpdateDivision(orgIdList,
				StatusType.INACTIVE_FLAG.value);
	}

	/**
	 * 获得某个Division
	 * 
	 * @param id
	 * @return
	 */
	public Divisions getDivision(Integer id) {
		Divisions obj = this.divisionDao.get(id);
		return obj;
	}

	/**
	 * 保存或更新 Division
	 * 
	 * @param division
	 * @param loginUserId
	 */
	public void saveDivision(List<CustNoteDTO> noteList, Divisions division, DivisionBilling billing, Integer loginUserId) {
		division.setCreationDate(new Date());
		division.setCreatedBy(loginUserId);
		division.setModifyDate(new Date());
		division.setModifiedBy(loginUserId);
		this.divisionDao.save(division);
		this.attachNote(division, noteList);
		if (billing != null) {
			this.attachBilling(division, billing);
		}
	}
	
	/*
	 * 保存Divisions时级联保存Billing.
	 * 
	 * @param division
	 * @param billing
	 */
	private void attachBilling(Divisions division,
			DivisionBilling billing) {
		billing.setDivisionId(division.getDivisionId());
		billing.setCreatedBy(division.getCreatedBy());
		billing.setModifiedBy(division.getCreatedBy());
		billing.setCreationDate(new Date());
		billing.setModifyDate(new Date());
		this.divisionBillingDao.save(billing);
	}
	
	/**
	 * 保存Divisions的同时保存DivisionsNotes.及其关联的多个document.
	 * 
	 * @param customer
	 * @param dtoList
	 * @return
	 */
	private void attachNote(Divisions division, List<CustNoteDTO> dtoList) {
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
			note.setDivisionId(division.getDivisionId());
			note.setCreatedBy(division.getModifiedBy());
			note.setModifiedBy(division.getModifiedBy());
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
					doc.setCreatedBy(division.getModifiedBy());
					doc.setModifiedBy(division.getModifiedBy());
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
	 * 根据orgId获得所有关联的Division
	 * 
	 * @param page
	 * @param orgId
	 * @return
	 */
	public Page<Divisions> searchDivision(Page<Divisions> page,
			Integer orgId) {
		return this.divisionDao.searchDivision(page, orgId);
	}
	
	@Transactional(readOnly = true)
	public List<CustNoteDTO> getNoteList(Integer divisionId) {
		List<CustNoteDTO> dtoList = new ArrayList<CustNoteDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_divisionId", divisionId);
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
	 * 获得一个Division的Billing.
	 * 
	 * @param divisionId
	 * @return
	 */
	public DivisionBilling getBilling(Integer divisionId) {
		return this.divisionBillingDao.getBilling(divisionId);
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
