/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.genscript.gsscm.customer.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.EncrypeUtil;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Address;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.customer.service.CustomerService;

/**
 * @author jinsite
 */
@Results({
		@Result(name = "listAddress", location = "customer/customer_address_list.jsp"),
		@Result(name = "showCreAddrFormAct", location = "customer/customer_address_create_form.jsp"),
		@Result(name = "editAddress", location = "customer/customer_address_create_form.jsp"),
		@Result(name = "custAddrPickerAct", location = "customer/custAddrPickerAct.jsp") })
public class CustAddressAction extends BaseAction<CustAddrOperDTO> {

	/**
     *
     */
	private static final long serialVersionUID = 1084048542796783753L;
	@Autowired
	private PublicService publicService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DozerBeanMapper dozer;
	private int custNo;
	private String sessCustNo;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private List<CustAddrOperDTO> addrList;
	private CustAddrOperDTO addrData = new CustAddrOperDTO();
	private CustomerDTO customerDetail;
	private String effFrom;
	private String effTo;
	private Page<CustAddrOperDTO> page = new Page<CustAddrOperDTO>(20);
	// 保留Select Address 查询条件
	private String c_custNo;
	private String firstName;
	private String lastName;
	private String email;
	private String orgName;

	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		if (custNo != 0) {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(),
					Integer.toString(custNo));
		} else {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(), sessCustNo);
		}
		if (custNo != 0 && addrList == null) {
			CustAddrSrchDTO custAddrSrchDTO = new CustAddrSrchDTO();
			custAddrSrchDTO.setCustNo(custNo);
			addrList = addressService.getAddrList(custAddrSrchDTO);
			SessionUtil.insertRow(SessionConstant.CustAddressList.value(),
					Integer.toString(custNo), getAddrList());
		}
		return "listAddress";
	}

	/**
	 * Show the new Address form
	 * 
	 * @return showCreAddrFormAct
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String showAddrCreateForm() throws Exception {
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("ADDRESS_TYPE",
				publicService.getDropdownList("ADDRESS_TYPE"));
		if (custNo != 0) {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(),
					Integer.toString(custNo));
		} else {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(), sessCustNo);
		}
		int need_check_flag = 0;
		if (addrList != null) {
			for (int i = 0; i < addrList.size(); i++) {
				if (addrList.get(i).getDefaultFlag().equals("Y")) {
					ServletActionContext.getRequest().setAttribute(
							addrList.get(i).getAddrType(),
							addrList.get(i).getAddrId());
					need_check_flag = 1;
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("need_check_flag",
				need_check_flag);
		if (custNo != 0) {
			customerDetail = customerService.getCustomerDetail(custNo);
		} else {
			customerDetail = null;
		}
		// setCustomerDetail();
		if (StringUtils.isNotBlank(ServletActionContext.getRequest()
				.getParameter("copy_addrId"))
				&& StringUtils.isNotEmpty(ServletActionContext.getRequest()
						.getParameter("copy_addrId"))) {
			Address addr = addressService.getAddress(Integer
					.parseInt(ServletActionContext.getRequest().getParameter(
							"copy_addrId")));
			addrData = dozer.map(addr, CustAddrOperDTO.class);
			if (addrData.getEffFrom() != null) {
				addrData.setEffFrom(DateUtils.formatStr2Date(addrData
						.getEffFrom().toGMTString(), "yyyy-MM-dd"));
			}
			if (addrData.getEffTo() != null) {
				addrData.setEffTo(DateUtils.formatStr2Date(addrData.getEffTo()
						.toGMTString(), "yyyy-MM-dd"));
			}
			addrData.setAddrId(null);

		}
		addrData.setDefaultFlag("Y");
		return "showCreAddrFormAct";
	}

	/**
	 * Edit the Address, and show Edit form.
	 * 
	 * @return editAddress
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String edit() throws Exception {
		if (custNo != 0) {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(),
					Integer.toString(custNo));
		} else {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(), sessCustNo);
		}
		if (addrList != null) {
			int need_check_flag = 0;
			for (int i = 0; i < addrList.size(); i++) {
				if (addrList.get(i).getDefaultFlag().equals("Y")) {
					ServletActionContext.getRequest().setAttribute(
							addrList.get(i).getAddrType(),
							addrList.get(i).getAddrId());
					need_check_flag = 1;
				}
			}
			ServletActionContext.getRequest().setAttribute("need_check_flag",
					need_check_flag);
		}
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("ADDRESS_TYPE",
				publicService.getDropdownList("ADDRESS_TYPE"));
		setCustomerDetail((CustomerDTO) SessionUtil.getRow("customer",
				Integer.toString(custNo)));
		for (int i = 0; i < addrList.size(); i++) {
			if (ServletActionContext.getRequest().getParameter("addrId") != null
					&& !"".equals(ServletActionContext.getRequest()
							.getParameter("addrId"))
					&& ((CustAddrOperDTO) addrList.get(i)).getAddrId().equals(
							Integer.parseInt(ServletActionContext.getRequest()
									.getParameter("addrId")))) {
				addrData = (CustAddrOperDTO) addrList.get(i);
				System.out.print("<><><><><><><><<<<<<<<>>>>>>>>>     "+addrData.getOrgName());
				effFrom = DateUtils.formatDate2Str(addrData.getEffFrom(),
						"yyyy-MM-dd");
				effTo = DateUtils.formatDate2Str(addrData.getEffTo(),
						"yyyy-MM-dd");
			}
		}
		return "editAddress";
	}

	/**
	 * Show select Address Form and can check the Address
	 * 
	 * @return custAddrPickerAct
	 * @throws Exception
	 */
	public String custAddrPickerAct() throws Exception {
		CustAddrSrchDTO custAddrSrchDTO = new CustAddrSrchDTO();
		if (StringUtils.isNotEmpty(ServletActionContext.getRequest()
				.getParameter("c_custNo"))
				&& StringUtils.isNotBlank(ServletActionContext.getRequest()
						.getParameter("c_custNo"))) {
			try {
				custAddrSrchDTO.setCustNo(Integer.parseInt(ServletActionContext
						.getRequest().getParameter("c_custNo").trim()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			custAddrSrchDTO.setCustNo(0);
		}
		if (StringUtils.isNotEmpty(ServletActionContext.getRequest()
				.getParameter("firstName"))
				&& StringUtils.isNotBlank(ServletActionContext.getRequest()
						.getParameter("firstName"))) {
			custAddrSrchDTO.setFirstName(ServletActionContext.getRequest()
					.getParameter("firstName").trim());
		}
		if (StringUtils.isNotEmpty(ServletActionContext.getRequest()
				.getParameter("lastName"))
				&& StringUtils.isNotBlank(ServletActionContext.getRequest()
						.getParameter("lastName"))) {
			custAddrSrchDTO.setLastName(ServletActionContext.getRequest()
					.getParameter("lastName").trim());
		}
		if (StringUtils.isNotEmpty(ServletActionContext.getRequest()
				.getParameter("email"))
				&& StringUtils.isNotBlank(ServletActionContext.getRequest()
						.getParameter("email"))) {
			custAddrSrchDTO.setEmail(ServletActionContext.getRequest()
					.getParameter("email").trim());
		}
		if (StringUtils.isNotEmpty(ServletActionContext.getRequest()
				.getParameter("orgName"))
				&& StringUtils.isNotBlank(ServletActionContext.getRequest()
						.getParameter("orgName"))) {
			custAddrSrchDTO.setOrgName(ServletActionContext.getRequest()
					.getParameter("orgName").trim());
		}
		String orgId = ServletActionContext.getRequest().getParameter("orgId");
		if (StringUtils.isNotEmpty(orgId) && StringUtils.isNumeric(orgId)) {
			custAddrSrchDTO.setOrgId(Integer.parseInt(orgId.trim()));
		}
		// 获得分页请求相关数据：如第几页
		PagerUtil<CustAddrOperDTO> pagerUtil = new PagerUtil<CustAddrOperDTO>();
		page = pagerUtil.getRequestPage();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("addrId");
			page.setOrder(Page.ASC);
		}
		page.setPageSize(20);
		page = addressService.getAddrList(page, custAddrSrchDTO);
		// 把分页相关数据放入request作用域内
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "custAddrPickerAct";
	}

	@Override
	public String input() throws Exception {
		return save();
	}

	/**
	 * Save the Address Information to Session
	 * 
	 * @return NONE
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String save() throws Exception {
		if (custNo != 0) {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(),
					Integer.toString(custNo));
		} else {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(), sessCustNo);
		}
		if (addrList == null) {
			addrList = new ArrayList<CustAddrOperDTO>();
		}
		if (StringUtils.isBlank(addrData.getStatus())
				|| StringUtils.isEmpty(addrData.getStatus())) {
			addrData.setStatus("INACTIVE");
		}
		// System.out.println(effFrom);
		Integer validation_date = Integer.parseInt(ServletActionContext
				.getRequest().getParameter("validation_date"));
		// System.out.println(validation_date+"=============================validation_date");
		if (validation_date == 0) {
			addrData.setEffFrom(null);
		} else if (validation_date == 1) {
			if (StringUtils.isNotBlank(ServletActionContext.getRequest()
					.getParameter("effFrom"))
					&& StringUtils.isNotEmpty(ServletActionContext.getRequest()
							.getParameter("effFrom"))) {
				addrData.setEffFrom(DateUtils.formatStr2Date(
						ServletActionContext.getRequest().getParameter(
								"effFrom"), "yyyy-MM-dd"));
			}
		}
		// System.out.println(validation_date);

		if (StringUtils.isNotBlank(ServletActionContext.getRequest()
				.getParameter("effTo"))
				&& StringUtils.isNotEmpty(ServletActionContext.getRequest()
						.getParameter("effTo"))) {
			addrData.setEffTo(DateUtils.formatStr2Date(ServletActionContext
					.getRequest().getParameter("effTo"), "yyyy-MM-dd"));

		}
		if (StringUtils.isBlank(addrData.getDefaultFlag())
				|| StringUtils.isEmpty(addrData.getDefaultFlag())) {
			addrData.setDefaultFlag("N");
		}
		if (addrData.getDefaultFlag().equals("Y")) {
			for (int i = 0; i < addrList.size(); i++) {
				CustAddrOperDTO temp = addrList.get(i);
				if (temp.getAddrType().equals(addrData.getAddrType())) {
					if (temp.getDefaultFlag().equals("Y")) {
						temp.setDefaultFlag("N");
						if (temp.getOperateType() == null
								|| !(temp.getOperateType().equals(
										OperationType.ADD.value()) || temp
										.getOperateType().equals(
												OperationType.DEL.value()))) {
							temp.setOperateType(OperationType.EDIT.value());
						}
						addrList.remove(i);
						addrList.add(i, temp);
					}
				}
			}
		}
		addrData.setState(ServletActionContext.getRequest().getParameter(
				"state"));
		if (StringUtils.isBlank(addrData.getDefaultFlag())
				|| StringUtils.isEmpty(addrData.getDefaultFlag())) {
			addrData.setDefaultFlag("N");
		}
		if (addrData.getAddrId() == null || addrData.getAddrId() == 0) {
			addrData.setAddrId(Integer.parseInt(EncrypeUtil.get5Radom()));
			addrData.setOperateType(OperationType.ADD.value());
			if (custNo != 0) {
				addrData.setCustomer(dozer.map(
						customerService.getCustomerDetail(custNo),
						Customer.class));
			}
			addrList.add(addrData);
		} else {
			for (int i = 0; i < addrList.size(); i++) {
				if (((CustAddrOperDTO) addrList.get(i)).getAddrId().equals(
						addrData.getAddrId())) {
					addrList.remove(i);
					if (addrData.getOperateType() != null) {
						if (addrData.getOperateType().equals(
								OperationType.ADD.value())) {
							addrData.setOperateType(OperationType.ADD.value());
						}
					} else {
						addrData.setOperateType(OperationType.EDIT.value());
					}
					addrList.add(i, addrData);
				}
			}
		}
		if (custNo != 0) {
			SessionUtil.insertRow(SessionConstant.CustAddressList.value(),
					Integer.toString(custNo), addrList);
		} else {
			SessionUtil.insertRow(SessionConstant.CustAddressList.value(),
					sessCustNo, addrList);
		}
		return NONE;
	}

	/**
	 * Delete the new address from new
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String delete() throws Exception {
		if (custNo != 0) {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(),
					Integer.toString(custNo));
		} else {
			addrList = (List<CustAddrOperDTO>) SessionUtil.getRow(
					SessionConstant.CustAddressList.value(), sessCustNo);
		}
		String[] addrIds = ServletActionContext.getRequest()
				.getParameterValues("addrIds");
		if (addrList == null || addrList.isEmpty() || addrIds == null
				|| addrIds.length == 0) {
			return NONE;
		}
		for (int j = 0; j < addrList.size(); j++) {
			CustAddrOperDTO tempAddr = (CustAddrOperDTO) addrList.get(j);
			for (int i = 0; i < addrIds.length; i++) {
				if (tempAddr.getAddrId().toString().equals(addrIds[i].trim())) {
					if (tempAddr.getOperateType() == null
							|| !tempAddr.getOperateType().equals(
									OperationType.ADD.value())) {
						addrList.remove(j);
						tempAddr.setOperateType(OperationType.DEL.value());
						addrList.add(j, tempAddr);
					} else if (tempAddr.getOperateType().equals(
							OperationType.ADD.value())) {
						addrList.remove(j);
					}
				}
			}
		}
		return NONE;
	}

	@Override
	protected void prepareModel() throws Exception {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @return the custNo
	 */
	public int getCustNo() {
		return custNo;
	}

	/**
	 * @param custNo
	 *            the custNo to set
	 */
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}

	/**
	 * @return the dropDownList
	 */
	public Map<String, List<PbDropdownListOptions>> getDropDownList() {
		return dropDownList;
	}

	/**
	 * @param dropDownList
	 *            the dropDownList to set
	 */
	public void setDropDownList(
			Map<String, List<PbDropdownListOptions>> dropDownList) {
		this.dropDownList = dropDownList;
	}

	/**
	 * @return the addrList
	 */
	public List<CustAddrOperDTO> getAddrList() {
		return addrList;
	}

	/**
	 * @param addrList
	 *            the addrList to set
	 */
	public void setAddrList(List<CustAddrOperDTO> addrList) {
		this.addrList = addrList;
	}

	/**
	 * @return the addrData
	 */
	public CustAddrOperDTO getAddrData() {
		return addrData;
	}

	/**
	 * @param addrData
	 *            the addrData to set
	 */
	public void setAddrData(CustAddrOperDTO addrData) {
		this.addrData = addrData;
	}

	/**
	 * @return the sessCustNo
	 */
	public String getSessCustNo() {
		return sessCustNo;
	}

	/**
	 * @param sessCustNo
	 *            the sessCustNo to set
	 */
	public void setSessCustNo(String sessCustNo) {
		if (StringUtils.isNotBlank(sessCustNo)
				&& StringUtils.isNotEmpty(sessCustNo))
			this.sessCustNo = sessCustNo;
	}

	/**
	 * @return the customerDetail
	 */
	public CustomerDTO getCustomerDetail() {
		return customerDetail;
	}

	/**
	 * @param customerDetail
	 *            the customerDetail to set
	 */
	public void setCustomerDetail(CustomerDTO customerDetail) {
		this.customerDetail = customerDetail;
	}

	/**
	 * @return the effFrom
	 */
	public String getEffFrom() {
		return effFrom;
	}

	/**
	 * @param effFrom
	 *            the effFrom to set
	 */
	public void setEffFrom(String effFrom) {
		this.effFrom = effFrom;
	}

	/**
	 * @return the effTo
	 */
	public String getEffTo() {
		return effTo;
	}

	/**
	 * @param effTo
	 *            the effTo to set
	 */
	public void setEffTo(String effTo) {
		this.effTo = effTo;
	}

	public Page<CustAddrOperDTO> getPage() {
		return page;
	}

	public void setPage(Page<CustAddrOperDTO> page) {
		this.page = page;
	}

	public String getC_custNo() {
		return c_custNo;
	}

	public void setC_custNo(String no) {
		c_custNo = no;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public PublicService getPublicService() {
		return publicService;
	}

	public void setPublicService(PublicService publicService) {
		this.publicService = publicService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}
}
