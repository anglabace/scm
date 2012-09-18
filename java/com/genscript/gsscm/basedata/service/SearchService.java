package com.genscript.gsscm.basedata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.basedata.dao.PbSearchAttributeDao;
import com.genscript.gsscm.basedata.dao.SearchConditionDao;
import com.genscript.gsscm.basedata.dao.SearchDao;
import com.genscript.gsscm.basedata.dao.SearchTypeDao;
import com.genscript.gsscm.basedata.dto.SearchConditionDTO;
import com.genscript.gsscm.basedata.dto.SearchDTO;
import com.genscript.gsscm.basedata.entity.PbSearch;
import com.genscript.gsscm.basedata.entity.PbSearchAttribute;
import com.genscript.gsscm.basedata.entity.PbSearchCondition;
import com.genscript.gsscm.common.constant.SearchType;

@Service
@Transactional
public class SearchService {

	@Autowired
	private SearchDao searchDao;
	@Autowired
	private PbSearchAttributeDao searchAttributeDao;
	@Autowired
	private SearchConditionDao searchConditionDao;
	@Autowired
	private SearchTypeDao searchTypeDao;

	@Autowired
	private DozerBeanMapper dozer;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PbSearchAttribute> getSearchAtrbList(SearchType searchType) {
		@SuppressWarnings("rawtypes")
		List list = this.searchAttributeDao.getListByType(searchType.value());
		return list;
	}

	public void saveMySearch(PbSearch search, SearchType searchType,
			PbSearchCondition[] conditionList) {
		if (search.getPbSearchType() == null) {
			search.setPbSearchType(searchTypeDao.getSearchType(searchType
					.value()));
		}
		Integer searchTypeId = search.getPbSearchType().getTypeId();
		Integer searchOwnerId = search.getOwner();
		String searchNameVal = search.getName();
		if (!searchDao.isMySrchUnique(searchTypeId, searchOwnerId,
				searchNameVal)) {
			search = searchDao.getMySrch(searchTypeId, searchOwnerId,
					searchNameVal);
			searchConditionDao.delBySearch(search);
		}
		Date now = new Date();
		search.setCreationDate(now);
		search.setModifyDate(now);
		search.setModifiedBy(search.getCreatedBy());
		search.setOwner(search.getCreatedBy());
		this.searchDao.save(search);
		
		for (PbSearchCondition condition : conditionList) {
			condition.setPbSearch(search);
			condition.setCreatedBy(search.getCreatedBy());
			condition.setCreationDate(now);
			condition.setModifyDate(now);
			condition.setModifiedBy(search.getCreatedBy());
			this.searchConditionDao.save(condition);
		}
		
	}

	@Transactional
	public void delMySearch(Integer searchId, Integer ownerId) {
		PbSearch pbSearch = searchDao.getSearch(searchId, ownerId);
		searchConditionDao.delBySearch(pbSearch);
		searchDao.delete(pbSearch);
	}

	public List<SearchDTO> getMySearchList(SearchType searchType, Integer owerId) {
		List<PbSearch> searchList = this.searchDao.getList(searchType.value(),
				owerId);
		List<SearchDTO> srchDTOList = new ArrayList<SearchDTO>();
		for (PbSearch search : searchList) {
			SearchDTO dto = dozer.map(search, SearchDTO.class);
			List<PbSearchCondition> condList = searchConditionDao
					.getListBySearch(search);
			List<SearchConditionDTO> attrList = new ArrayList<SearchConditionDTO>();
			for (PbSearchCondition cond : condList) {
				SearchConditionDTO condDto = dozer.map(cond,
						SearchConditionDTO.class);
				condDto.setId(cond.getPbSearchAttribute().getAttributeId());
				attrList.add(condDto);
			}
			dto.setAttrList(attrList);
			srchDTOList.add(dto);
		}
		return srchDTOList;
	}

}
