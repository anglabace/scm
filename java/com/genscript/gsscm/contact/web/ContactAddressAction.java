package com.genscript.gsscm.contact.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionEmergeUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.ContactAddress;
import com.genscript.gsscm.contact.service.ContactService;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.service.AddressService;

/**
 * 处理Contact' Address相关请求的Action
 * 
 * @author wangsf
 */
@Results( {
		@Result(name = "listAddress", location = "contact/contact_address_list.jsp"),
		@Result(name = "editAddress", location = "contact/contact_address_edit.jsp"),
		@Result(name = "selectAddress", location = "contact/contact_address_select.jsp") })
public class ContactAddressAction extends BaseAction<ContactAddressDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	@Autowired
	private PublicService publicService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private DozerBeanMapper dozer;
	private Integer contactNo;
	private String sessContactNo;
	private Map<String, List<PbDropdownListOptions>> dropDownList;
	private ContactAddressDTO addrData;
	private ContactModelDTO contactDetail;
	private String effFrom;
	private String effTo;
	private Map<String, ContactAddressDTO> addressMap;// 用于返回列表页面时显示数据
	private Page<ContactAddress> addressPage;
	private String addressKey;// 在edit时用来
	private CustAddrSrchDTO addrSrch;// 弹出窗口用于查找address.
	private Integer fromAddrId;// 从"select address"中选出的.

	@Override
	public String list() throws Exception {
		  if (contactNo!=null&&contactNo != 0) {
			this.addressMap = (Map<String, ContactAddressDTO>) SessionUtil
					.getRow(SessionConstant.ContactAddress.value(), Integer
							.toString(contactNo));
		} else {
			this.addressMap = (Map<String, ContactAddressDTO>) SessionUtil
					.getRow(SessionConstant.ContactAddress.value(),
							sessContactNo);
		}
		if (contactNo!=null&&contactNo != 0 && (addressMap == null || addressMap.size() == 0)) {
			CustAddrSrchDTO addrSrchDTO = new CustAddrSrchDTO();
			addrSrchDTO.setCustNo(Integer.parseInt(this.sessContactNo));
			addressMap = new LinkedHashMap<String, ContactAddressDTO>();
			List<ContactAddressDTO> addrList = addressService
					.getContactAddrList(addrSrchDTO);
			for (ContactAddressDTO dto : addrList) {
				addressMap.put(dto.getAddrId() + "", dto);
			}
			SessionUtil.insertRow(SessionConstant.ContactAddress.value(),
					Integer.toString(contactNo), addressMap);
		}
		return "listAddress";
	}

	/**
	 * Show the new Address form
	 * 
	 * @return showCreAddrFormAct
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (StringUtils.isNumeric(this.sessContactNo)) {// ContactModel是已存在的
			contactDetail = contactService.getContactDetail(Integer
					.parseInt(this.sessContactNo));
		}
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("ADDRESS_TYPE", publicService
				.getDropdownList("ADDRESS_TYPE"));
		return "editAddress";
	}

	/**
	 * 修改Address时调用 应该传递addrData, addressKey,
	 * sessContactNo等属性过来.
	 * 
	 * @return editAddress
	 * @throws Exception
	 */
	public String edit() throws Exception {
		System.out.println("Contact addressKey: " + this.addressKey);
		Object obj = SessionUtil.getOneRow(SessionConstant.ContactAddress
				.value(), this.sessContactNo, this.addressKey);
		if (obj != null) {
			addrData = (ContactAddressDTO) obj;
		} else {
			addrData = this.dozer.map(addressService.getContactAddress(Integer
					.valueOf(this.addressKey)), ContactAddressDTO.class);
		}
		effFrom = DateUtils.formatDate2Str(addrData.getEffFrom(), "yyyy-MM-dd");
		effTo = DateUtils.formatDate2Str(addrData.getEffTo(), "yyyy-MM-dd");
		System.out.println("Contact addrData: " + addrData);
		if (StringUtils.isNumeric(this.sessContactNo)) {// ContactModel是已存在的
			contactDetail = contactService.getContactDetail(Integer
					.parseInt(this.sessContactNo));
		}
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("ADDRESS_TYPE", publicService
				.getDropdownList("ADDRESS_TYPE"));
		return "editAddress";
	}

	/**
	 * 在新增或修改Address时通过select address操作,选择某个Address后需要调用这个方法.
	 * 
	 * @return
	 */
	public String selectCallBack() {
		//从选中的address中带出数据.
		addrData = this.dozer.map(addressService.getContactAddress(fromAddrId),
				ContactAddressDTO.class);
		if (StringUtils.isBlank(addressKey)) {// key为空，即是新增address时操作了select address
			addrData.setAddrId(null);
		} else if (StringUtils.isNumeric(this.addressKey)) {
			// key为数字，即是对数据库原有address进行修改.
			addrData.setAddrId(Integer.valueOf(this.addressKey));
		}
		effFrom = DateUtils.formatDate2Str(addrData.getEffFrom(), "yyyy-MM-dd");
		effTo = DateUtils.formatDate2Str(addrData.getEffTo(), "yyyy-MM-dd");
		System.out.println("Contact addrData: " + addrData);
		dropDownList = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownList.put("ADDRESS_TYPE", publicService
				.getDropdownList("ADDRESS_TYPE"));
		return "editAddress";
	}

	/**
	 * Select Address操作
	 * 
	 * @return contactAddrPickerAct
	 * @throws Exception
	 */
	public String select() throws Exception {
		try {
			// 获得分页请求相关数据：如第几页
			PagerUtil<ContactAddress> pagerUtil = new PagerUtil<ContactAddress>();
			this.addressPage = pagerUtil.getRequestPage();
			// 设置默认排序
			if (!addressPage.isOrderBySetted()) {
				addressPage.setOrderBy("addrId");
				addressPage.setOrder(Page.DESC);
			}
			// 设置默认每页显示记录条数
			addressPage.setPageSize(15);
			addressPage = addressService.getContactAddrPage(addrSrch,addressPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					addressPage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "selectAddress";
	}

	@Override
	public String input() throws Exception {
		// throw new UnsupportedOperationException("Not supported yet.");
		return save();
	}

	/**
	 * Save the Address Information to Session
	 * 
	 * @return NONE
	 * @throws Exception
	 */
	@Override
	public String save() throws Exception {
		if (StringUtils.isBlank(addrData.getStatus())) {
			addrData.setStatus("INACTIVE ");
		}
		String validDate = ServletActionContext.getRequest().getParameter("validation_date");
		if (validDate.equals("N")) {
			addrData.setEffFrom(null);
			addrData.setEffTo(null);
		} else {
			if (StringUtils.isNotBlank(ServletActionContext.getRequest().getParameter("effFrom"))) {
				addrData.setEffFrom(DateUtils.formatStr2Date(ServletActionContext.getRequest()
						.getParameter("effFrom"), "yyyy-MM-dd"));
			}
			if (StringUtils.isNotBlank(ServletActionContext.getRequest().getParameter("effTo"))) {
				addrData.setEffTo(DateUtils.formatStr2Date(ServletActionContext.getRequest()
						.getParameter("effTo"), "yyyy-MM-dd"));

			}
		}
		System.out.println(ServletActionContext.getRequest().getParameter("orgname"));
		addrData.setOrgName(ServletActionContext.getRequest().getParameter("orgname"));
		addrData.setState(ServletActionContext.getRequest().getParameter("state"));
		if (StringUtils.isBlank(this.addressKey)) {
			if (addrData.getAddrId() == null
					|| addrData.getAddrId().intValue() == 0) {
				addressKey = SessionUtil.generateTempId();
			} else {
				addressKey = addrData.getAddrId() + "";
			}
		}
		// 保存或更新Contact模块下的Contacts标签.
		SessionUtil.updateOneRow(SessionConstant.ContactAddress.value(),
				sessContactNo, addressKey, addrData);
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
		// 取得要删除的contact id字符串
		String delIdStr = ServletActionContext.getRequest().getParameter("addr_id_str");
		System.out.println("delIdStr: " + delIdStr);
		if (StringUtils.isNotEmpty(delIdStr)) {
			String[] delKeyArray = delIdStr.split(",");
			this.addressMap = (Map<String, ContactAddressDTO>) SessionUtil
					.getRow(SessionConstant.ContactAddress.value(),
							sessContactNo);
			if (addressMap == null) {
				addressMap = new HashMap<String, ContactAddressDTO>();
				SessionUtil.insertRow(SessionConstant.ContactAddress.value(),
						sessContactNo, addressMap);
			}
			for (int i = 0; i < delKeyArray.length; i++) {
				if (StringUtils.isNumeric(delKeyArray[i])) {
					addressMap.put(delKeyArray[i], null);
				} else {
					addressMap.remove(delKeyArray[i]);
				}
			}
			SessionUtil.updateRow(SessionConstant.ContactAddress.value(),
					sessContactNo, addressMap);
			Struts2Util.renderText("success");
		}
		return NONE;
	}

	@SuppressWarnings("unchecked")
	private Map<String, ContactAddressDTO> getSessionAndDBAddrMap() {
		// 取得session中的contacts
		Map<String, ContactAddressDTO> resultMap = null;
		Map<String, ContactAddressDTO> dbAddrMap = null;
		if (StringUtils.isNumeric(this.sessContactNo)) {// ContactModel是已存在的
			CustAddrSrchDTO addrSrchDTO = new CustAddrSrchDTO();
			addrSrchDTO.setCustNo(Integer.parseInt(this.sessContactNo));
			dbAddrMap = new LinkedHashMap<String, ContactAddressDTO>();
			List<ContactAddressDTO> addrList = addressService
					.getContactAddrList(addrSrchDTO);
			for (ContactAddressDTO dto : addrList) {
				dbAddrMap.put(dto.getAddrId() + "", dto);
			}
		}
		Map<String, ContactAddressDTO> sessAddrMap = (Map<String, ContactAddressDTO>) SessionUtil
				.getRow(SessionConstant.ContactAddress.value(), sessContactNo);
		SessionEmergeUtil<ContactAddressDTO> sessionEmergeUtil = new SessionEmergeUtil<ContactAddressDTO>();
		// 获得当前list页面所有的Contacts 记录(包括数据库和当前session中的).
		resultMap = sessionEmergeUtil.mergeList(sessAddrMap, dbAddrMap, 1);
		return resultMap;
	}

	@Override
	protected void prepareModel() throws Exception {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public Integer getContactNo() {
		return contactNo;
	}

	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
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
	 * @return the addrData
	 */
	public ContactAddressDTO getAddrData() {
		return addrData;
	}

	/**
	 * @param addrData
	 *            the addrData to set
	 */
	public void setAddrData(ContactAddressDTO addrData) {
		this.addrData = addrData;
	}

	/**
	 * @return the sessContactNo
	 */
	public String getSessContactNo() {
		return sessContactNo;
	}

	/**
	 * @param sessContactNo
	 *            the sessContactNo to set
	 */
	public void setSessContactNo(String sessContactNo) {
		if (StringUtils.isNotBlank(sessContactNo)
				&& StringUtils.isNotEmpty(sessContactNo))
			this.sessContactNo = sessContactNo;
	}

	/**
	 * @return the contactDetail
	 */
	public ContactModelDTO getContactDetail() {
		return contactDetail;
	}

	/**
	 * @param contactDetail
	 *            the contactDetail to set
	 */
	public void setContactDetail(ContactModelDTO contactDetail) {
		this.contactDetail = contactDetail;
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

	public Map<String, ContactAddressDTO> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String, ContactAddressDTO> addressMap) {
		this.addressMap = addressMap;
	}

	public String getAddressKey() {
		return addressKey;
	}

	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}

	public CustAddrSrchDTO getAddrSrch() {
		return addrSrch;
	}

	public void setAddrSrch(CustAddrSrchDTO addrSrch) {
		this.addrSrch = addrSrch;
	}

	public Integer getFromAddrId() {
		return fromAddrId;
	}

	public void setFromAddrId(Integer fromAddrId) {
		this.fromAddrId = fromAddrId;
	}

	public Page<ContactAddress> getAddressPage() {
		return addressPage;
	}

	public void setAddressPage(Page<ContactAddress> addressPage) {
		this.addressPage = addressPage;
	}
}
