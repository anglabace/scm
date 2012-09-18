package com.genscript.gsscm.accounting.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.dao.CustomerContactDao;
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.opensymphony.xwork2.ActionSupport;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;
import com.genscript.gsscm.customer.entity.NoteDocument;
/**
 * 发送邮件
 * @author zhouyong
 */
@Results( {
	@Result(name = "success", location = "accounting/receive/notify_customer.jsp")
})
public class SendMailAction extends ActionSupport {

	
	@Autowired
	MimeMailService mimeMailService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	DocumentDao documentDao;
	@Autowired
	CustomerContactDao customerContactDao;
	
	private List<File> files;
	private String uploadContentType;
	private String uploadFileName;
	
	public File upload;
	public String To;
	public String Cc;
	public String Subject;
	public String Content;
	public int custNo;
	
	
//	public List<String> attach;
//	
//
//	public List<String> getAttach() {
//		return attach;
//	}
//
//
//	public void setAttach(List<String> attach) {
//		this.attach = attach;
//	}


//	public String sendMail(){
//		this.mimeMailService.sendMail(this.getTo(), this.getSubject(), this.getSubject());
////		return "success";
//		System.out.println(this.getTo()+"   "+this.getSubject()+"  "+this.getContent()+"  "+this.getAttach());
//		Struts2Util.renderJson("{status:'Mail send success!'}");
//		return null;
//	}
	

	public String getTo() {
		return To;
	}

	public void setTo(String to) {
		To = to;
	}


	public String getCc() {
		return Cc;
	}

	public void setCc(String cc) {
		Cc = cc;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String sendMail(){
		List<File> lf = new ArrayList<File>();
		List<String> filenames = new ArrayList<String>();
		List<String> uploadContentTypes = new ArrayList<String>();
		int userId = SessionUtil.getUserId();
		CustomerContactHistory c = new CustomerContactHistory();
		int cout_no = this.getCustNo();
		c.setCustNo(cout_no);
		c.setContactBy(userId);
		c.setContactDate(new Date());
		c.setContactName(SessionUtil.getUserName());
		c.setContactMethod("Email");
		String email = ""; //5105
		email = this.getTo() ;
		if(!this.getCc().equals("")){
			email += ("," + this.getCc()) ;
		}
		
		c.setEmail(email);
		this.setTo(email);
		c.setSubject(this.getSubject());
		c.setContentType("Invoice Overdue Message");
		c.setReferName(this.getContent());
		c.setStatus("COMPLETED");
		c.setCreatedBy(userId);
		c.setCreationDate(new Date());
		c.setModifiedBy(userId);
		c.setModifyDate(new Date());
		
		if(this.getUpload()!=null){
//			FileInputStream fin = null;
//		try {
//			fin = new FileInputStream(upload);
//			long a = fin.available();
//			System.out.println(a);
//			if(a>= 2097152 ){
//				Struts2Util.getRequest().setAttribute("foo", "Mail send error! File too big!Please upload a file less than 2MB!");
//				return "success";
//			}
//			fin.close();
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
			
		lf.add(this.getUpload());
		filenames.add(this.getUploadFileName());
		uploadContentTypes.add(this.getUploadContentType());
		try{
		this.fileService.uploadFile(lf, uploadContentTypes, filenames, "");
		}catch(Exception e){
			System.out.println(e.getMessage()+"文件太大");
			Struts2Util.getRequest().setAttribute("foo", "File too big error");
		}
		this.mimeMailService.sendMail(this.getTo(), this.getSubject(), this.getSubject(),filenames);
		
		this.customerContactDao.save(c);
	    c = this.customerContactDao.getLastest(cout_no);
		int ref_id = c.getContactId();
		
		NoteDocument doc = new NoteDocument();
		doc.setCreationDate(new Date());
		doc.setCreatedBy(userId);
		doc.setModifyDate(new Date());
		doc.setModifiedBy(userId);
		doc.setRefType("CUSTOMER_NOTE_TYPE");
		doc.setRefId(ref_id);
		doc.setDocName(this.getUploadFileName());
		doc.setDocType("Attachment");
		String fileType = this.getUploadFileName();
		fileType = fileType.substring(fileType.indexOf(".")+1);
		doc.setFileType(fileType);
		doc.setFilePath(Constant.MAILATTACHPATH);
		this.documentDao.getSession().save(doc);
		
		}else{
			this.mimeMailService.sendMail(this.getTo(), this.getSubject(), this.getSubject(),"scm_admin@genscriptcorp.com");
			this.customerContactDao.save(c);
		}
		Struts2Util.getRequest().setAttribute("foo", "success");
		return "success";
	}

	public int getCustNo() {
		return custNo;
	}

	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	
	

	
}
