package com.genscript.gsscm.customer.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerInterestDTO;
import com.genscript.gsscm.customer.entity.CustomerInterest;
import com.genscript.gsscm.customer.service.CustomerService;

@Results( { @Result(name = "viewCustAreaInterest", location = "customer/customer_more_info_interest.jsp") })
public class CustAreaInterestAction extends BaseAction<CustomerInterestDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -292522637543905313L;
	private List<String> decInterestList;
	private List<String> appInterestList;
	private int custNo;
	private String sessCustNo;
	private List<CustomerInterestDTO> myDRList;
	private List<CustomerInterestDTO> myATList;
	private List<DropDownListDTO> specDropDownist;
	private String custmerName;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		System.out.println("decInterestList: " + decInterestList);
		// 页面上提交的所有Area Interest列表
		Map<String, CustomerInterestDTO> custInterestMap = new HashMap<String, CustomerInterestDTO>();
		if (decInterestList != null && decInterestList.size() > 0) {
			for (String decStr : decInterestList) {
				String str[] = decStr.split(":");
				CustomerInterestDTO custIntDTO = new CustomerInterestDTO();
				custIntDTO.setAreaId(Integer.parseInt(str[0]));
				custIntDTO.setName(str[1]);
				custIntDTO.setType("DR");
				custInterestMap.put(str[0], custIntDTO);
			}
		}
		if (appInterestList != null && appInterestList.size() > 0) {
			for (String decStr : appInterestList) {
				String str[] = decStr.split(":");
				CustomerInterestDTO custIntDTO = new CustomerInterestDTO();
				custIntDTO.setAreaId(Integer.parseInt(str[0]));
				custIntDTO.setName(str[1]);
				custIntDTO.setType("AT");
				custInterestMap.put(str[0], custIntDTO);
			}
		}

		Map<String, Object> custAreaInterests = null;
		// 取得session中的Area Interests
		System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
		custAreaInterests = SessionUtil.getRow(SessionConstant.CustInterestList.value(), custNo, sessCustNo);
		Map<String, Object> newMap = new HashMap<String, Object>();
		Set<String> areaIdSet = new HashSet<String>();
		if (custAreaInterests != null && !custAreaInterests.isEmpty()) {
			for (Iterator<String> iter = custAreaInterests.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				CustomerInterestDTO val = (CustomerInterestDTO) custAreaInterests
						.get(key);
				areaIdSet.add(String.valueOf(val.getAreaId()));
				if (custInterestMap != null
						&& custInterestMap.containsKey(String.valueOf(val
								.getAreaId()))) {
					newMap.put(key, val);
				} else {
					if (!StringUtil.isNumeric(key)) {

					} else {
						val.setOperationType(OperationType.DEL);
						newMap.put(key, val);
					}
				}
			}

		}
		// Map<String, Object> resMap = new HashMap<String, Object>();
		if (custInterestMap != null && !custInterestMap.isEmpty()) {
			for (Iterator<String> iter = custInterestMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				CustomerInterestDTO val = (CustomerInterestDTO) custInterestMap
						.get(key);
				if (areaIdSet != null
						&& !areaIdSet.contains(String.valueOf(val.getAreaId()))) {
					val.setOperationType(OperationType.ADD);
					newMap.put(SessionUtil.generateTempId(), val);
				}
			}
		}
		System.out.println("newMap: " + newMap);
		SessionUtil.insertRow(SessionConstant.CustInterestList.value(), custNo, sessCustNo, newMap);
		return NONE;
	}

	public String viewCustAreaInterest() throws Exception {
		Map<String, Object> custAreaInterests = null;
		myDRList = new ArrayList<CustomerInterestDTO>();
		myATList = new ArrayList<CustomerInterestDTO>();
		// 取得session中的Area Interests
		System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
		custAreaInterests = SessionUtil.getRow(SessionConstant.CustInterestList.value(), custNo, sessCustNo);
		custmerName = (String)SessionUtil.getRow("customerName", sessCustNo);
		if (custAreaInterests == null || custAreaInterests.isEmpty()) {
			if (custNo != 0) {
				List<CustomerInterest> custIntList = customerService
						.getCustIntList(custNo);
				if (custIntList != null && !custIntList.isEmpty()) {
					Map<String, Object> sessCustIntMap = new LinkedHashMap<String, Object>();
					for (CustomerInterest custInt : custIntList) {
						CustomerInterestDTO custIntDTO = dozer.map(custInt,
								CustomerInterestDTO.class);
						sessCustIntMap.put(String.valueOf(custInt
								.getInterestId()), custIntDTO);
					}
					SessionUtil.insertRow(SessionConstant.CustInterestList.value(), String
							.valueOf(custNo), sessCustIntMap);
				}
			}
		}
		
		custAreaInterests = SessionUtil.getRow(SessionConstant.CustInterestList.value(), custNo, sessCustNo);
		if (custAreaInterests != null && !custAreaInterests.isEmpty()) {
			for (Iterator<String> iter = custAreaInterests.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				CustomerInterestDTO val = (CustomerInterestDTO) custAreaInterests
						.get(key);
				if (val != null && val.getOperationType() != null
						&& val.getOperationType().equals(OperationType.DEL)) {
					continue;
				}
				if (val != null && val.getType().equalsIgnoreCase("dr")) {
					myDRList.add(val);
				}
				if (val != null && val.getType().equalsIgnoreCase("at")) {
					myATList.add(val);
				}
			}
		}
		System.out.println("myDRList: " + myDRList);
		System.out.println("myATList: " + myATList);
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.DECIPLINE_INTEREST);
		speclListName.add(SpecDropDownListName.APPLICATION_INTEREST);
		// 获得DECIPLINE_INTEREST和APPLICATION_INTEREST下拉框数值
		specDropDownist = publicService.getSpecDropDownList(speclListName);
		return "viewCustAreaInterest";
	}

	public List<String> getDecInterestList() {
		return decInterestList;
	}

	public void setDecInterestList(List<String> decInterestList) {
		this.decInterestList = decInterestList;
	}

	public List<String> getAppInterestList() {
		return appInterestList;
	}

	public void setAppInterestList(List<String> appInterestList) {
		this.appInterestList = appInterestList;
	}

	public List<CustomerInterestDTO> getMyDRList() {
		return myDRList;
	}

	public List<CustomerInterestDTO> getMyATList() {
		return myATList;
	}

	public List<DropDownListDTO> getSpecDropDownist() {
		return specDropDownist;
	}
	public void setSessCustNo(String sessCustNo) {
		this.sessCustNo = sessCustNo;
	}

	public String getSessCustNo() {
		return sessCustNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public String getCustmerName() {
		return custmerName;
	}

	public void setCustmerName(String custmerName) {
		this.custmerName = custmerName;
	}
}
