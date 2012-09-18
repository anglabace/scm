package com.genscript.gsscm.basedata.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.StateDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.CommonSearchService;
import com.genscript.gsscm.common.constant.SearchType;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;

/**
 * My Search Action
 * 
 * @author golf
 * 
 */
public class MySearchAction extends BaseAction<Object> {

	/**
*
*/
	private static final long serialVersionUID = 4317273704884198774L;

	private Integer mySrchId;
	private String mySrchName;
	private String searchType;
	private String countryCode;
	@Autowired
	private CommonSearchService commonSearchService;
	@Autowired
	private PublicService publicService;

	/**
	 * Get my search list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mySearchList() throws Exception {
		System.out.println("searchType : " + searchType);
		commonSearchService.mySearchList(SearchType.fromValue(searchType));
		return NONE;
	}

	/**
	 * Delete customer my search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delMysrch() throws Exception {
		commonSearchService.delMysrch(mySrchId);
		return NONE;
	}

	/**
	 * Save customer my search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveMysrch() throws Exception {
		commonSearchService.saveMysrch(SearchType.fromValue(searchType),
				mySrchName);
		return NONE;
	}

	public String stateListByCountry() throws Exception {
		List<StateDTO> stateDTOList = publicService
				.getStateListByCountry(countryCode);
		Struts2Util.renderJson(stateDTOList);
		return NONE;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	public Integer getMySrchId() {
		return mySrchId;
	}

	public void setMySrchId(Integer mySrchId) {
		this.mySrchId = mySrchId;
	}

	public String getMySrchName() {
		return mySrchName;
	}

	public void setMySrchName(String mySrchName) {
		this.mySrchName = mySrchName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
