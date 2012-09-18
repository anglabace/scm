package com.genscript.gsscm.customer.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.AmPm;
import com.genscript.gsscm.common.constant.CallTime;
import com.genscript.gsscm.common.constant.ContactMethod;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.SessionEmergeUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.contact.entity.ContentTemplate;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.productservice.dto.CatalogDTO;

/**
 * Customer Contact Action
 *
 * @author Golf
 */
@Results({
        @Result(name = "listCustCntct", location = "customer/customer_contact_list.jsp"),
        @Result(name = "custCntctCreForm", location = "customer/customer_contact_create_form.jsp"),
        @Result(name = "custCntctEditForm", location = "customer/customer_contact_edit_form.jsp")})
public class CustContactAction extends BaseAction<ContactDTO> {

    /**
     *
     */
    private static final long serialVersionUID = 6534283335416418911L;
    private Integer custAddrsCount = 0;
    private Integer shipAddrsCount = 0;
    private Integer billAddrsCount = 0;
    private Integer soldAddrsCount = 0;
    private String saveType;
    private Integer phoneCount = 0;
    private Integer emailCount = 0;
    private Integer faxCount = 0;
    private Integer mailCount = 0;
    private Integer meetingCount = 0;
    private String callTimeFromAPM;
    private String callTimeFrom;
    private String callTimeToAPM;
    private String callTimeTo;
    private String fax;
    private String faxExt;
    private String bstCallTmzn;
    private String addrType;
    private String email;
    private String firstName;
    private String lastName;
    private CallTime[] callTimeList;
    private AmPm[] amPmList;
    private String sessCustNo;
    private String sessContactId;
    private List<CustAddrOperDTO> custAddrs;
    private List<CustAddrOperDTO> billAddrs;
    private List<CustAddrOperDTO> shipAddrs;
    private List<CustAddrOperDTO> soldAddrs;
    private Integer orgId = 0;
    private Integer contactId;
    private Integer statusFlag = 0;
    private String contactIdStr;
    private String status;
    private int custNo;
    private ContactDTO allData;
    private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
    private List<ContentTemplate> contentTmplList;
    private List<CatalogDTO> catalogDTOList;
    private Page<ContactDTO> pageContactDTO;
    private Date contactDateFrom;
    private Date contactDateTo;
    private Date contactDateDefault = new Date();
    private String contactMethod;
    private String contactBy;
    private List<ContactDTO> retContactList;
    private List<String> contactUserNameList;
    private List<DropDownListDTO> specDropDownist;

    @Autowired
    private AddressService addressService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DozerBeanMapper dozer;

    /**
     * 删除customer contact方法
     *
     * @return
     * @throws Exception
     */
    @Override
    public String delete() throws Exception {
        SessionEmergeUtil<ContactDTO> sessionEmergeUtil = new SessionEmergeUtil<ContactDTO>();
        sessionEmergeUtil.deleteSessionObj("contact_id_str", custNo,
                sessCustNo, SessionConstant.CustContact,
                "com.genscript.gsscm.contact.dto.ContactDTO");
        Struts2Util.renderText("success");
        return NONE;
    }

    /**
     * 编辑已有customer contact方法
     *
     * @return "custCntctEditForm"
     * @throws Exception
     */
    public String showCustCntctEditForm() throws Exception {
        System.out.println("contactIdStr: " + contactIdStr);
        Map<String, Object> sessContactMap = null;
        sessContactMap = SessionUtil.getRow(
                SessionConstant.CustContact.value(), custNo, sessCustNo);
        if (sessContactMap != null && !sessContactMap.isEmpty()) {
            for (Iterator<String> iter = sessContactMap.keySet().iterator(); iter
                    .hasNext();) {
                String key = iter.next();
                if (key.equals(contactIdStr)) {
                    allData = (ContactDTO) sessContactMap.get(key);
                }
            }
        }
        if (allData.getStatus() != null
                && allData.getStatus().indexOf("UN") == -1) {
            statusFlag = 1;
        }
        System.out.println(">>>>>>>>>>>>>>>>>>" + allData.getSubject());
        System.out.println(">>>>>>>>>>>>>>>>>>" + allData);
        /*if(custNo != 0 && allData != null){
              System.out.println("Call Time: "+allData.getCallTime());
              Struts2Util.getRequest().setAttribute("callTimeAPM",
                      DateUtils.getAPM(allData.getCallTime()));
              Struts2Util.getRequest()
                      .setAttribute(
                              "callTime",
                              DateUtils.getAPMTime(allData.getCallTime(), 5));
          }*/
        showContactAddEdit();
        return "custCntctEditForm";
    }

    @Override
    public String input() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 创建customer contact表单方法
     *
     * @return "custCntctCreForm"
     * @throws Exception
     */
    public String showCustCntctCreForm() throws Exception {
        showContactAddEdit();
        return "custCntctCreForm";
    }

    /**
     * 新建和保存customer contact所调用公共方法
     */
    private void showContactAddEdit() {
        catalogDTOList = new ArrayList<CatalogDTO>();
        custAddrs = new ArrayList<CustAddrOperDTO>();
        billAddrs = new ArrayList<CustAddrOperDTO>();
        shipAddrs = new ArrayList<CustAddrOperDTO>();
        soldAddrs = new ArrayList<CustAddrOperDTO>();
        CustomerDTO sessCustomer = (CustomerDTO) SessionUtil.getRow("customer",
                String.valueOf(custNo));
     //   System.out.println("sessCustomer: " + sessCustomer);
        if (sessCustomer != null) {
            callTimeFromAPM = DateUtils.getAPM(sessCustomer
                    .getBstCallTimeFrom());
            System.out.println("callTimeFromAPM: " + callTimeFromAPM);
            callTimeFrom = DateUtils.getAPMTime(
                    sessCustomer.getBstCallTimeFrom(), 5);
            System.out.println("callTimeFrom: " + callTimeFrom);
            callTimeToAPM = DateUtils.getAPM(sessCustomer.getBstCallTimeTo());
            System.out.println("callTimeToAPM: " + callTimeToAPM);
            callTimeTo = DateUtils.getAPMTime(sessCustomer.getBstCallTimeTo(),
                    5);
            System.out.println("callTimeTo: " + callTimeTo);
            bstCallTmzn = sessCustomer.getBstCallTmzn();
            fax = sessCustomer.getFax();
            faxExt = sessCustomer.getFaxExt();
        }
        // 对call time列表进行设值
        setCallTimeList(CallTime.values());
        // 对AM，PM列表设值
        setAmPmList(AmPm.values());
        List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
        speclListName.add(SpecDropDownListName.ORIGINAL_SOURCE);
        speclListName.add(SpecDropDownListName.TIME_ZONE);
        // 获得Source和time zone下拉框数值
        specDropDownist = publicService.getSpecDropDownList(speclListName);
        CustAddrSrchDTO addrSrchDTO = new CustAddrSrchDTO();
        addrSrchDTO.setCustNo(custNo);
        addrSrchDTO.setAddrType(addrType);
        addrSrchDTO.setEmail(email);
        addrSrchDTO.setFirstName(firstName);
        addrSrchDTO.setLastName(lastName);
        addrSrchDTO.setOrgId(orgId);
        // 获得address列表
        List<CustAddrOperDTO> addrdtoList = null;
        //如果不是新增的customer
        if (custNo != 0) {
            addrdtoList = addressService
                    .getAddrList(addrSrchDTO);
        }
        if (addrdtoList != null && addrdtoList.size() > 0) {
            for (CustAddrOperDTO custAddDTO : addrdtoList) {
                String fullAddr = AddressUtil.getFullAddress(
                        custAddDTO.getAddrLine1(), custAddDTO.getAddrLine2(),
                        custAddDTO.getAddrLine3(), custAddDTO.getCity(),
                        custAddDTO.getState(), custAddDTO.getZipCode(),
                        custAddDTO.getCountry());
                String addrType = custAddDTO.getAddrType();
                if (StringUtils.isNotEmpty(addrType)) {
                    if (addrType.equals(AddressType.CONTACT.value())) {
                        custAddrsCount++;
                        custAddrs.add(custAddDTO);
                    } else if (addrType.equals(AddressType.BILL_TO.value())) {
                        billAddrsCount++;
                        billAddrs.add(custAddDTO);
                    } else if (addrType.equals(AddressType.SHIP_TO.value())) {
                        shipAddrsCount++;
                        shipAddrs.add(custAddDTO);
                    } else if (addrType.equals(AddressType.SOLD_TO.value())) {
                        soldAddrsCount++;
                        soldAddrs.add(custAddDTO);
                    }
                }
                custAddDTO.setFullAddrLine(fullAddr);
            }
        }
        // 获得content template下拉框数值
        contentTmplList = publicService.getContentTmplList();
        List<Catalog> catalogList = productService.getSpecailCatalogList();
        for (Catalog po : catalogList) {
            CatalogDTO dto = new CatalogDTO();
            dto.setCatalogId(po.getCatalogId());
            dto.setCatalogName(po.getCatalogName());
            catalogDTOList.add(dto);
        }
    }


    /**
     * 显示customer下的所有contact列表
     *
     * @return "listCustCntct"
     * @throws Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public String list() throws Exception {
        Map<String, ContactDTO> contactMap = null;
        Map<String, Object> custContactList = null;
        retContactList = new ArrayList<ContactDTO>();
        pageContactDTO = new Page<ContactDTO>();
        pageContactDTO.setPageSize(10000);
        contactUserNameList = new ArrayList<String>();
        // 取得session中的contacts
        System.out.println("custNo, sessCustNo: " + custNo + ", " + sessCustNo);
        custContactList = SessionUtil.getRow(
                SessionConstant.CustContact.value(), custNo, sessCustNo);
        SessionEmergeUtil<ContactDTO> sessionEmergeUtil = new SessionEmergeUtil<ContactDTO>();
        contactMap = sessionEmergeUtil.convertMap(custContactList);
        Page<CustomerContactHistory> page = dozer.map(pageContactDTO,
                Page.class);
        page.setOrder(Page.DESC); 
        page.setOrderBy("contactId");
        System.out.println("Page : " + page.getOrder() + ", "
                + page.getOrderBy());
        // 取得从数据库中实际查询出的所有contact
        pageContactDTO = customerService.getCustContactList(custNo, page);
        Map<String, ContactDTO> dbContactMap = new LinkedHashMap<String, ContactDTO>();
        List<ContactDTO> contactList = pageContactDTO.getResult();
        System.out.println("contactList : " + contactList);
        for (int i = 0; i < contactList.size(); i++) {
            dbContactMap.put(String.valueOf(contactList.get(i).getContactId()),
                    contactList.get(i));
        }
        // 把session里的contact和数据库中的contact进行合并操作
        dbContactMap = sessionEmergeUtil.mergeList(contactMap, dbContactMap, 1);
        if (!dbContactMap.isEmpty()) {
            for (Iterator<String> iter = dbContactMap.keySet().iterator(); iter
                    .hasNext();) {
                String key = iter.next();
                ContactDTO val = (ContactDTO) dbContactMap.get(key);
                if (val != null) {
                    if (StringUtil.isNumeric(key)) {
                        val.setContactIdStr(String.valueOf(val.getContactId()));
                    } else {
                        val.setContactIdStr(key);
                    }
                }
                System.out.println("key: " + key + " , val: " + val);
                dbContactMap.put(key, val);
            }
            SessionUtil.insertRow(SessionConstant.CustContact.value(),
                    custNo, sessCustNo, dbContactMap);
        }
        // 根据contact搜索条件进行循环
        if (!dbContactMap.isEmpty()) {
            for (Iterator<String> iter = dbContactMap.keySet().iterator(); iter
                    .hasNext();) {
                String key = iter.next();
                ContactDTO val = (ContactDTO) dbContactMap.get(key);
                if (val != null) {
                    if (val.getContactMethod().equals(
                            ContactMethod.Email.value())) {
                        emailCount++;
                    } else if (val.getContactMethod().equals(
                            ContactMethod.Fax.value())) {
                        faxCount++;
                    } else if (val.getContactMethod().equals(
                            ContactMethod.Mail.value())) {
                        mailCount++;
                    } else if (val.getContactMethod().equals(
                            ContactMethod.Phone.value())) {
                        phoneCount++;
                    } else if (val.getContactMethod().equals(ContactMethod.Meeting.value())) {
                        meetingCount++;
                    }

                    if (StringUtils.isNotEmpty(contactMethod)
                            && !(val.getContactMethod().equals(contactMethod))) {
                        continue;
                    }

                    if (StringUtils.isNotEmpty(status)
                            && !(val.getStatus().equals(status))) {
                        continue;
                    }

                    if (StringUtils.isNotEmpty(contactBy) && val.getContactUserName() != null
                            && val.getContactUserName().indexOf(contactBy) == -1) {
                        continue;
                    }

                    if (contactDateFrom != null && val.getContactDate() != null
                            && val.getContactDate().before(contactDateFrom)) {
                        continue;
                    }

                    if (contactDateTo != null && val.getContactDate() != null
                            && val.getContactDate().after(contactDateTo)) {
                        continue;
                    }
                    if (!contactUserNameList.contains(val.getContactUserName())) {
                        contactUserNameList.add(val.getContactUserName());
                    }

                    System.out.println("KEY : " + key);
                    System.out.println("VAl : " + val);
                    if (key.equals("null")) {
                        continue;
                    }
                    if (val != null
                            && val.getOperationType() != null
                            && (val.getOperationType()
                            .equals(OperationType.DEL))) {
                        continue;
                    }
                    retContactList.add(val);
                }
            }
            System.out.println("retContactList: " + retContactList);
            pageContactDTO.setResult(retContactList);
        }
        return "listCustCntct";
    }

    @Override
    protected void prepareModel() throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 保存customer contact并写入Session方法
     *
     * @return
     * @throws Exception
     */
    @Override
    public String save() throws Exception {
        allData = new ContactDTO();
        String saveType = ServletActionContext.getRequest().getParameter("saveType");
        String contactIdStr = ServletActionContext.getRequest().getParameter("contactIdStr");
        if (StringUtils.isNotEmpty(contactIdStr)
                && StringUtils.isNumeric(contactIdStr)) {
            contactId = Integer.parseInt(contactIdStr);
        }
        if (StringUtils.isNotEmpty(saveType) && saveType.equals("update")
                && StringUtils.isNumeric(contactIdStr)) {
            if (contactId == null
                    && StringUtils.isNumeric(allData.getContactIdStr())) {
                throw new RuntimeException("data error!");
            }
            if (contactId == null
                    && !StringUtils.isNumeric(allData.getContactIdStr())) {
                allData.setOperationType(OperationType.ADD);
            }
            if (contactId != null) {
                allData.setOperationType(OperationType.EDIT);
                allData.setContactId(contactId);
                allData.setContactIdStr(String.valueOf(contactId));
            } else {
                allData.setOperationType(OperationType.ADD);
            }
        } else if (StringUtils.isNotEmpty(saveType)
                && saveType.equals("update")
                && !StringUtils.isNumeric(contactIdStr)) {
            allData.setOperationType(OperationType.ADD);
            allData.setContactIdStr(contactIdStr);
        } else {
            sessContactId = SessionUtil.generateTempId();
            allData.setOperationType(OperationType.ADD);
            allData.setContactIdStr(sessContactId);
        }
        String source = ServletActionContext.getRequest().getParameter("source");
        if (StringUtils.isNotEmpty(source)) {
            allData.setSource(Integer.parseInt(source));
            allData.setSourceName(ServletActionContext.getRequest().getParameter("sourceName"));
        }
        allData.setContactName(ServletActionContext.getRequest().getParameter("contactName"));
        allData.setCustNo(custNo);
        String scheduleDateStr = ServletActionContext.getRequest().getParameter("scheduleDate");
        if (StringUtils.isNotEmpty(scheduleDateStr)) {
            allData.setScheduleDate(DateUtils.formatStr2Date(scheduleDateStr,
                    "yyyy-MM-dd"));
        }
        String contactDateStr = ServletActionContext.getRequest().getParameter("contactDate");
        if (StringUtils.isNotEmpty(contactDateStr)) {
            allData.setContactDate(DateUtils.formatStr2Date(contactDateStr,
                    "yyyy-MM-dd"));
        }
        allData.setContactMethod(ServletActionContext.getRequest().getParameter("contactMethod"));

        String cntctMethod = allData.getContactMethod();
        if (cntctMethod.equals(ContactMethod.Email.value())) {
            allData.setEmail(ServletActionContext.getRequest().getParameter("email"));
            System.out.println("EmailSubject: "
                    + ServletActionContext.getRequest().getParameter("EmailSubject"));
            allData.setSubject(ServletActionContext.getRequest().getParameter("EmailSubject"));
            allData.setContent(ServletActionContext.getRequest().getParameter("EmailContent"));
        } else if (cntctMethod.equals(ContactMethod.Fax.value())) {
            allData.setFax(ServletActionContext.getRequest().getParameter("fax"));
            allData.setFaxExt(ServletActionContext.getRequest().getParameter("faxExt"));
            allData.setSubject(ServletActionContext.getRequest().getParameter("FaxSubject"));
        } else if (cntctMethod.equals(ContactMethod.Mail.value())) {
            String rec = ServletActionContext.getRequest().getParameter("recipient");
            if (StringUtils.isNotEmpty(rec)) {
                allData.setAddress(ServletActionContext.getRequest().getParameter(rec));
            }
            allData.setSubject(ServletActionContext.getRequest().getParameter("MailSubject"));
            allData.setContent(ServletActionContext.getRequest().getParameter("MailContent"));
        } else if (cntctMethod.equals(ContactMethod.Phone.value())) {
            allData.setPhone(ServletActionContext.getRequest().getParameter("phoneNumber"));
            allData.setSubject(ServletActionContext.getRequest().getParameter("PhoneSubject"));
            allData.setContent(ServletActionContext.getRequest().getParameter("PhoneContent"));
            allData.setInterestScore(Integer.parseInt(ServletActionContext.getRequest()
                    .getParameter("interestScore")));
            String callTime = ServletActionContext.getRequest().getParameter("callTime");
            String callTimeAmPm = ServletActionContext.getRequest().getParameter("callTimeAmPm");
            allData.setCallTime(DateUtils.apmStr2Time(callTime, callTimeAmPm));
            allData.setCallTmzn(ServletActionContext.getRequest().getParameter("callTmzn"));
        } else if (cntctMethod.equals(ContactMethod.Meeting.value())) {
            allData.setSubject(ServletActionContext.getRequest().getParameter("meeting_name"));
            allData.setContent(ServletActionContext.getRequest().getParameter("meeting_content"));
        }
        if (cntctMethod.equals(ContactMethod.Email.value())
                || cntctMethod.equals(ContactMethod.Mail.value())) {
            String tmpContentType = ServletActionContext.getRequest().getParameter("contactMethod")
                    + "ContentType";
            if (StringUtils.isNotEmpty(ServletActionContext.getRequest()
                    .getParameter(tmpContentType))) {
                Integer contentType = Integer.parseInt(ServletActionContext.getRequest()
                        .getParameter(tmpContentType));

                List<ContentTemplate> contentTmplList = publicService
                        .getContentTmplList();
                if (contentTmplList != null && contentTmplList.size() > 0) {
                    for (ContentTemplate tpl : contentTmplList) {
                        Integer tplId = tpl.getTemplateId();
                        if (tplId.compareTo(contentType) == 0) {
                            allData.setContentType(tpl.getName());
                            String tmpReferName = cntctMethod + "ReferName"
                                    + tpl.getTemplateId();
                            allData.setReferName(tmpReferName);
                        }
                    }
                }
            }
        }
        if (statusFlag == 1) {
            allData.setStatus(ServletActionContext.getRequest().getParameter("status_name"));
        } else {
            allData.setStatus("UN" + ServletActionContext.getRequest().getParameter("status_name"));
        }
        if (cntctMethod.equals(ContactMethod.Meeting)) {
            allData.setStatus("COMPLETED");
        }
        allData.setContactBy(SessionUtil.getUserId());
        allData.setContactUserName(SessionUtil.getUserName());
        Map<String, ContactDTO> sessContact;
        Map<String, Object> sessionContact = SessionUtil.getRow(SessionConstant.CustContact.value(), custNo, sessCustNo);
        SessionEmergeUtil<ContactDTO> sessionEmergeUtil = new SessionEmergeUtil<ContactDTO>();
        sessContact = sessionEmergeUtil.convertMap(sessionContact);
        if (sessContact == null) {
            sessContact = new LinkedHashMap<String, ContactDTO>();
        }
        sessContact.put(allData.getContactIdStr(), allData);
        SessionUtil.insertRow(SessionConstant.CustContact.value(), custNo, sessCustNo, sessContact);
        return NONE;
    }

    public Integer getPhoneCount() {
        return phoneCount;
    }

    public Integer getEmailCount() {
        return emailCount;
    }

    public Integer getFaxCount() {
        return faxCount;
    }

    public Integer getMailCount() {
        return mailCount;
    }

    public Integer getCustAddrsCount() {
        return custAddrsCount;
    }

    public Integer getShipAddrsCount() {
        return shipAddrsCount;
    }

    public Integer getBillAddrsCount() {
        return billAddrsCount;
    }

    public Integer getSoldAddrsCount() {
        return soldAddrsCount;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getSaveType() {
        return saveType;
    }

    public String getSessContactId() {
        return sessContactId;
    }

    public void setSessContactId(String sessContactId) {
        this.sessContactId = sessContactId;
    }

    public String getContactIdStr() {
        return contactIdStr;
    }

    public void setContactIdStr(String contactIdStr) {
        this.contactIdStr = contactIdStr;
    }

    public ContactDTO getAllData() {
        return allData;
    }

    public void setAllData(ContactDTO allData) {
        this.allData = allData;
    }

    public String getCallTimeFromAPM() {
        return callTimeFromAPM;
    }

    public void setCallTimeFromAPM(String callTimeFromAPM) {
        this.callTimeFromAPM = callTimeFromAPM;
    }

    public String getCallTimeFrom() {
        return callTimeFrom;
    }

    public void setCallTimeFrom(String callTimeFrom) {
        this.callTimeFrom = callTimeFrom;
    }

    public String getCallTimeToAPM() {
        return callTimeToAPM;
    }

    public void setCallTimeToAPM(String callTimeToAPM) {
        this.callTimeToAPM = callTimeToAPM;
    }

    public String getCallTimeTo() {
        return callTimeTo;
    }

    public void setCallTimeTo(String callTimeTo) {
        this.callTimeTo = callTimeTo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFaxExt() {
        return faxExt;
    }

    public void setFaxExt(String faxExt) {
        this.faxExt = faxExt;
    }

    public String getBstCallTmzn() {
        return bstCallTmzn;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setCallTimeList(CallTime[] callTimeList) {
        this.callTimeList = callTimeList;
    }

    public CallTime[] getCallTimeList() {
        return callTimeList;
    }

    public void setAmPmList(AmPm[] amPmList) {
        this.amPmList = amPmList;
    }

    public AmPm[] getAmPmList() {
        return amPmList;
    }

    public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
        return specDropDownList;
    }

    public void setSpecDropDownList(
            Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
        this.specDropDownList = specDropDownList;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<CustAddrOperDTO> getCustAddrs() {
        return custAddrs;
    }

    public void setCustAddrs(List<CustAddrOperDTO> custAddrs) {
        this.custAddrs = custAddrs;
    }

    public List<CustAddrOperDTO> getBillAddrs() {
        return billAddrs;
    }

    public List<CustAddrOperDTO> getShipAddrs() {
        return shipAddrs;
    }

    public List<CustAddrOperDTO> getSoldAddrs() {
        return soldAddrs;
    }

    public List<ContentTemplate> getContentTmplList() {
        return contentTmplList;
    }

    public void setContentTmplList(List<ContentTemplate> contentTmplList) {
        this.contentTmplList = contentTmplList;
    }

    public List<CatalogDTO> getCatalogDTOList() {
        return catalogDTOList;
    }

    public Page<ContactDTO> getPageContactDTO() {
        return pageContactDTO;
    }

    public void setPageContactDTO(Page<ContactDTO> pageContactDTO) {
        this.pageContactDTO = pageContactDTO;
    }

    public Date getContactDateFrom() {
        return contactDateFrom;
    }

    public void setContactDateFrom(Date contactDateFrom) {
        this.contactDateFrom = contactDateFrom;
    }

    public Date getContactDateTo() {
        return contactDateTo;
    }

    public void setContactDateTo(Date contactDateTo) {
        this.contactDateTo = contactDateTo;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getContactBy() {
        return contactBy;
    }

    public void setContactBy(String contactBy) {
        this.contactBy = contactBy;
    }

    public List<ContactDTO> getRetContactList() {
        return retContactList;
    }

    public void setRetContactList(List<ContactDTO> retContactList) {
        this.retContactList = retContactList;
    }

    public List<String> getContactUserNameList() {
        return contactUserNameList;
    }

    public void setContactUserNameList(List<String> contactUserNameList) {
        this.contactUserNameList = contactUserNameList;
    }

    public Date getContactDateDefault() {
        return contactDateDefault;
    }

    public void setContactDateDefault(Date contactDateDefault) {
        this.contactDateDefault = contactDateDefault;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCustNo() {
        return custNo;
    }

    public Integer getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(Integer meetingCount) {
        this.meetingCount = meetingCount;
    }
}
