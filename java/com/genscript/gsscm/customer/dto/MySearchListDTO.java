package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.List;

import com.genscript.gsscm.basedata.dto.SearchDTO;

public class MySearchListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -255814642915477326L;
	private Integer mySrchMaxCount;
	private List<SearchDTO> mySearchList;
	public Integer getMySrchMaxCount() {
		return mySrchMaxCount;
	}
	public void setMySrchMaxCount(Integer mySrchMaxCount) {
		this.mySrchMaxCount = mySrchMaxCount;
	}
	public List<SearchDTO> getMySearchList() {
		return mySearchList;
	}
	public void setMySearchList(List<SearchDTO> mySearchList) {
		this.mySearchList = mySearchList;
	}
	
}
