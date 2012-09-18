package com.genscript.gsscm.tools.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.basedata.dto.MailContentTemplateDTO;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dto.CustomerBeanDTO;
import com.genscript.gsscm.customer.dto.CustomerNoteDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.epicorwebservice.service.GetInvoicePaymentListJob;
import com.genscript.gsscm.manufacture.service.OperationNameService;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.system.dao.MailTemplatesDao;
import com.genscript.gsscm.system.entity.MailGroup;
import com.genscript.gsscm.system.entity.MailTemplates;
import com.genscript.gsscm.system.service.CompanyService;
import com.genscript.gsscm.tools.service.CustomerInvoiceService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "customer_monitor_list", location = "tools/customer_monitor.jsp"),
		@Result(name = "customer_edit", location = "tools/invoice_customer_status.jsp") })
public class CustomerInvoiceAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1494645302777722965L;

	/**
	 * 
	 */

	@Autowired
	private CustomerInvoiceService customerInvoiceService;
	@Autowired
	private ArInvoiceDao arInvoiceDao;
	@Autowired
	private PublicService publicService;
	@Autowired
	private MailTemplatesDao mailTemplatesDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private GetInvoicePaymentListJob getInvoicePaymentListJob;
	// 文件处理
	@Autowired
	private FileService fileService;
	@Autowired
	private OperationNameService operationNameService;
	@Autowired
	private CompanyService companyService;
	private Page<CustomerBeanDTO> page;
	private List<Object[]> arInvoiceList;
	private String dutDays;
	private String endBalance;
	private String startBalance;
	private String custNo;
	private String status;
	private List<MailContentTemplateDTO> mailTmplList;
	private List<MailGroup> mailGroupList;
	private MailTemplates custConfirmMailTemplate;
	private String sendBool;
	private CustomerNoteDTO customerNote;
	private String oldStatus;

	/**
	 * 实际上传文件
	 */
	private List<File> upload;
	/**
	 * 文件的内容类型
	 */
	private List<String> uploadContentType;
	/**
	 * 上传文件名
	 */
	private List<String> uploadFileName;


	public String getSendBool() {
		return sendBool;
	}
	
	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public void setSendBool(String sendBool) {
		this.sendBool = sendBool;
	}



	public CustomerNoteDTO getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(CustomerNoteDTO customerNote) {
		this.customerNote = customerNote;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public MailTemplates getCustConfirmMailTemplate() {
		return custConfirmMailTemplate;
	}

	public void setCustConfirmMailTemplate(MailTemplates custConfirmMailTemplate) {
		this.custConfirmMailTemplate = custConfirmMailTemplate;
	}

	public List<MailContentTemplateDTO> getMailTmplList() {
		return mailTmplList;
	}

	public void setMailTmplList(List<MailContentTemplateDTO> mailTmplList) {
		this.mailTmplList = mailTmplList;
	}

	public List<MailGroup> getMailGroupList() {
		return mailGroupList;
	}

	public void setMailGroupList(List<MailGroup> mailGroupList) {
		this.mailGroupList = mailGroupList;
	}

	public Page<CustomerBeanDTO> getPage() {
		return page;
	}

	public void setPage(Page<CustomerBeanDTO> page) {
		this.page = page;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object[]> getArInvoiceList() {
		return arInvoiceList;
	}

	public void setArInvoiceList(List<Object[]> arInvoiceList) {
		this.arInvoiceList = arInvoiceList;
	}

	public String getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(String endBalance) {
		this.endBalance = endBalance;
	}

	public String getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}

	public String getDutDays() {
		return dutDays;
	}

	public void setDutDays(String dutDays) {
		this.dutDays = dutDays;
	}

	/**
	 * 分页
	 * 
	 * @return
	 * @throws Exception
	 */

	public String list() throws Exception {
//		String bb=operationNameService.getOperationName(18,6);
//		System.out.println("==========================="+bb);
//		getInvoicePaymentListJob.execute();
//		dutDays = Struts2Util.getParameter("dutDays");
//		System.out.println("dutDays================"+dutDays);
//		startBalance = Struts2Util.getParameter("startBalance");
//		endBalance = Struts2Util.getParameter("endBalance");
//		custNo = Struts2Util.getParameter("custNo");
//		status = Struts2Util.getParameter("status");
//		System.out.println("dutDays================"+dutDays);
//		System.out.println("startBalance================"+startBalance);
//		System.out.println("endBalance================"+endBalance);
//		System.out.println("status================"+status);
		// 在ar_invoices根据日期和余额条件的用户ID、余额得到
		arInvoiceList = arInvoiceDao.getCustomerIdOrBalance(ZH(dutDays),
				ZH(startBalance), ZH(endBalance), ZH(custNo));
		page = customerInvoiceService.searchCustomerBeanPage(page,
				arInvoiceList, ZH(status));
		// 存入request
		ServletActionContext.getRequest().setAttribute("pagerInfo", page);
		return "customer_monitor_list";
	}

	/**
	 * 更新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateStatus() {
		sendBool = Struts2Util.getParameter("sendBool");
		oldStatus= Struts2Util.getParameter("oldStatus");
		String oldCustNo= Struts2Util.getParameter("oldCustNo");
//		System.out.println("sendBool=============="+sendBool);
//		System.out.println("oldStatus=============="+oldStatus);
//		System.out.println("oldCustNo=============="+oldCustNo);
		try {
			List<Document> documentList = new ArrayList<Document>();
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			Object userId = session.get(StrutsActionContant.USER_ID);
			if (sendBool.equals("true")) {
				if (upload != null && !upload.isEmpty()) {
					for (int i = 0; i < upload.size(); i++) {
						Document doc = new Document();
						doc.setDocName(uploadFileName.get(i));
						String srcFileName = uploadFileName.get(i);
						uploadFileName.set(
								i,
								SessionUtil.generateTempId()
										+ srcFileName.substring(srcFileName
												.lastIndexOf(".")));
						doc.setFilePath("customer_invoice/"
								+ uploadFileName.get(i));
						documentList.add(doc);
					}
				}
				fileService.uploadFile(upload, uploadContentType,
						uploadFileName, "customer_invoice");
				// 发送邮件
				mimeMailService.sendQuoteFollowUpMail(customerNote.getSender(),
						customerNote.getSubject(), customerNote.getContent(),
						documentList);
			}
			String msg=customerInvoiceService.LogAndUpdate(customerNote,oldCustNo,oldStatus,userId);		
			Struts2Util.renderText(msg);		
		} catch (Exception e) {
			Struts2Util.renderText("error");
			e.printStackTrace();
		}
		return null;
	}

	public String edit() throws Exception {
		System.out.println("edit ==================");

		this.mailGroupList = publicService
				.getMailGroupList(Constants.MAIL_GROUP_ORDER_CONFIRMATION);// 获得已配置的邮箱列表模板
		this.mailTmplList = publicService.getContentTmplDTOList();// 获得已配置的邮箱内容列表.
		// 获取invoice模板  
		custConfirmMailTemplate = this.mailTemplatesDao.findby("Template for Invoice without PO Number","Overdue Invoice Mail");
		Integer custmoerNo = Integer.parseInt(Struts2Util.getParameter("custmoerNo"));
		// 获取customer对象
		Customer new_customer = customerDao.getById(custmoerNo);
		// 获取customer所有的invoiceNo
		List<String[]> invoiceNoList = this.arInvoiceDao
				.getInvoiceNo(custmoerNo);
		String invoiceNos ="";
		String compayNames="";
		String comanyId="";
		for (int i = 0; i < invoiceNoList.size(); i++) {
			Object[] ob = (Object[]) invoiceNoList.get(i);
			invoiceNos += ob[0].toString() + ",";
			String temporary =ob[1].toString();
			if(comanyId.equals("")){
				//如果存放的Id为空把临时的值赋予给他
				comanyId=temporary;
				compayNames+=companyService.getCompanyName(Integer.parseInt(comanyId))+",";
			}else if(comanyId.equals(temporary)){
				
			}else{
				comanyId=temporary;
				compayNames+=companyService.getCompanyName(Integer.parseInt(comanyId))+",";
			}
		}
		invoiceNos = invoiceNos.substring(0, invoiceNos.length() - 1);
		compayNames=compayNames.substring(0,compayNames.length()-1);
		customerNote = new CustomerNoteDTO();
		customerNote.setType(OrderInstructionType.SUPPLY_CONFIRM_EMAIL.value());
		customerNote.setFunctionName(custConfirmMailTemplate.getFunctionName());
		customerNote.setTemplateId(custConfirmMailTemplate.getId());
		customerNote.setCustNo(new_customer != null ? new_customer.getCustNo() : null);
		customerNote.setSender(new_customer != null ? new_customer.getBusEmail() : null);
		customerNote.setCustName(new_customer != null ? new_customer.getFirstName()
				+ " " + new_customer.getMidName() + " " + new_customer.getLastName()
				: null);
		customerNote.setStatus(new_customer != null ? new_customer.getStatus() : null);
		String content = custConfirmMailTemplate.getContent();
		String subject = custConfirmMailTemplate.getSubject();
		subject = subject == null ? "" : subject.replace("$invoice_id",
				invoiceNos);
		content = content == null ? "" : content.replace("$ACCOUNT_LASTNAME",
				new_customer.getLastName());
		content = content.replace("$company_name_str", new_customer.getCustNo()
				.toString());
		content = content.replace("$invoice_id", invoiceNos);
		content = content.replace("$signature", compayNames);
		customerNote.setContent(content);	
		customerNote.setSubject(subject);
		return "customer_edit";
	}

	public String ZH(String values) throws Exception {
		if (values == null) {
			return "";
		} else {
			return values;
		}
	}

}
