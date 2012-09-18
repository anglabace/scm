package com.genscript.gsscm.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.SearchAttributeDTO;
import com.genscript.gsscm.basedata.dto.SearchDTO;
import com.genscript.gsscm.basedata.entity.PbSearch;
import com.genscript.gsscm.basedata.entity.PbSearchAttribute;
import com.genscript.gsscm.basedata.entity.PbSearchCondition;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.basedata.service.SearchService;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dto.MySearchListDTO;

/**
 * Common Search Service
 * 
 * @author golf
 * 
 */
@Service
@Transactional
public class CommonSearchService {
	@Autowired
	private SearchService searchService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DozerBeanMapper dozer;
	
	/** 
	 * @param searchType
	 */
	public void mySearchList(final SearchType searchType) {
		List<SearchDTO> searchDTOList = searchService.getMySearchList(searchType, SessionUtil.getUserId());
		Integer maxSrchCount = getMySrchMaxCount();
		MySearchListDTO mySearchListDTO = new MySearchListDTO();
		mySearchListDTO.setMySearchList(searchDTOList);
		mySearchListDTO.setMySrchMaxCount(maxSrchCount);
		Struts2Util.renderJson(mySearchListDTO);
	}
	
	/**
	 * @return MySrchMaxCount
	 */
	private static Integer getMySrchMaxCount() {
		return 6;
	}
	
	/**
	 * @param searchType
	 * @param mySrchId
	 */
	public void delMysrch( final Integer mySrchId) {
		searchService.delMySearch(mySrchId, SessionUtil.getUserId());
		Struts2Util.renderText("Delete my search successfully!");
	}
	
	/**
	 * @param searchType
	 * @param mySrchName
	 */
	public void saveMysrch(final SearchType searchType, final String mySrchName) {
		Integer userId = SessionUtil.getUserId();
		PbSearch mySearch = new PbSearch();
		mySearch.setName(mySrchName);
		mySearch.setCreatedBy(userId);
		mySearch.setOwner(userId);
		PbSearchCondition[] searchConditions = WebUtil.buildMySrchConditions(ServletActionContext.getRequest());
		searchService.saveMySearch(mySearch, searchType, searchConditions);
		Struts2Util.renderText("Save my search successfully!!");
	}
	
	/**
	 * @param searchType
	 * @return
	 */
	public List<SearchAttributeDTO> getSearchAttributeList(final SearchType searchType){
		List<SearchAttributeDTO> attrList = new ArrayList<SearchAttributeDTO>();
		List<PbSearchAttribute> list = searchService.getSearchAtrbList(searchType);
		String dataSrcCode;	
		for (PbSearchAttribute attr : list) {
			dataSrcCode = attr.getDataSrcCode();
			List<DropDownDTO> dropDownDTOs = new ArrayList<DropDownDTO>();
			if(dataSrcCode != null){
				dropDownDTOs = publicService.getSpecDropDownList(SpecDropDownListName.fromValue(dataSrcCode));
			}
			SearchAttributeDTO attrDto = dozer.map(attr,
					SearchAttributeDTO.class);
			attrDto.setDropDownDTOs(dropDownDTOs);
			attrList.add(attrDto);
		}
		return attrList;
	}
}
