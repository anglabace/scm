package com.genscript.gsscm.accounting.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.privilege.web.LoginAction;
import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name="success1",location="/success.jsp"),
	@Result(name="EL",location="/test.jsp"),
	@Result(name="error1",location="/error.jsp")})
public class TestAction extends ActionSupport {
 
	String msg = "";
	@Autowired
	ArInvoiceDao arInvoiceDao;
	@Autowired
	ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	ArInvoiceLineDao arInvoiceLineDao;
	
	@Autowired
	private FileService fileService;

	   private File upload;// 实际上传文件

	    private String uploadContentType; // 文件的内容类型

	    private String uploadFileName; // 上传文件名

	    String mail ;
	    
	    public Map param;
	    
	    
        public List<File>  uploadList;
        public List<String> emails;
	    
        
        
	public List<File> getUploadList() {
			return uploadList;
		}

		public void setUploadList(List<File> uploadList) {
			this.uploadList = uploadList;
		}

		public List<String> getEmails() {
			return emails;
		}

		public void setEmails(List<String> emails) {
			this.emails = emails;
		}

	public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

	public String getMsg() {
		
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getJson(){
		msg = "{name : \"zhouyong\"}";
		com.genscript.gsscm.common.util.Struts2Util.renderJson(msg);
		return null;
	}
	
	public String execute(){
//		String fromCurrency = Struts2Util.getParameter("fromCurrency");
//		String toCurrency = Struts2Util.getParameter("toCurrency");
//       double rate = this.arInvoiceDao.getCurrency(null, fromCurrency, toCurrency, new Date());
		boolean a = arInvoicePaymentDao.checkUploadPayment(1,388,2,"USD","23");
		   String msg = "{rate:'"+a+"'}";
        Struts2Util.renderJson(msg);
		return null;
	}

	public String EL(){
		List<ArInvoiceLine> arInvoiceLines=arInvoiceLineDao.queryInvoiceByInvoiceId(1);
int length=arInvoiceLines.size();
		
		String[] itemNos = new String[length];
		String[] invoiceLineIds = new String[length];
		String[] invoiceIds = new String[length];
		String[] catalogNo = new String[length];
		String[] names = new String[length];
		String[] qtys = new String[length];
		String[] qtyUoms = new String[length];
		String[] unitPrices = new String[length];
		String[] amounts = new String[length];
		String[] taxs = new String[length];
		String[] sizes = new String[length];
        String[] lineNo = new String[length];
        String[] discounts = new String[length];
        String[] createDate = new String[length];
		String[] createBy = new String[length];
		String[] modifyDate = new String[length];
		String[] modifyBy = new String[length];
        ArInvoiceLine InvoiceLine=null;
        
		for(int i=0;i<arInvoiceLines.size();i++ )
		{
			InvoiceLine=arInvoiceLines.get(i);
			invoiceLineIds[i] = ""+InvoiceLine.getInvoiceLineId();
			invoiceIds[i]=""+InvoiceLine.getInvoiceId(); 
			amounts[i]=""+InvoiceLine.getAmount();			 
	        catalogNo[i]=InvoiceLine.getCatalogNo();	       
	        itemNos[i]=""+InvoiceLine.getItemNo();	       
	        names[i]=InvoiceLine.getName();	    
	        qtys[i]=""+InvoiceLine.getQty();	       
	        qtyUoms[i]=""+InvoiceLine.getQtyUom();	        
	        unitPrices[i]=""+InvoiceLine.getUnitPrice();	      
	        taxs[i]=""+InvoiceLine.getTax();	        
	        sizes[i]=""+InvoiceLine.getSize();	         
	        lineNo[i]=""+InvoiceLine.getLineNo();	
	        discounts[i] = ""+InvoiceLine.getDiscount();
	        createDate[i] = ""+InvoiceLine.getCreationDate();
	        createBy[i] = ""+InvoiceLine.getCreatedBy();
	        modifyDate[i] = ""+InvoiceLine.getModifyDate();
	        modifyBy[i] = ""+InvoiceLine.getModifiedBy();
		}
		param = new HashMap();
		param.put("invoiceLineIds",invoiceLineIds);
		param.put("invoiceIds",invoiceIds);
		param.put("itemNo",itemNos);
		param.put("catalogNo",catalogNo);
		param.put("name",names);
		param.put("qty",qtys);
		param.put("qtyUom",qtyUoms);
		param.put("unitPrice",unitPrices);
		param.put("amount",amounts);
		param.put("tax",taxs);
		param.put("size",sizes);
        param.put("lineNo",lineNo);
		param.put("discount", discounts);
		param.put("creationDate", createDate);
		param.put("createdBy", createBy);
		param.put("modifyDate", modifyDate);
		param.put("modifiedBy", modifyBy);
	Struts2Util.getRequest().setAttribute("paramEL", param);
		return "EL";
	}
	
	public String upload(){
//		fileService.uploadFile(uploadlist, uploadContent, fileList,
//		"d:\\documents");
//		
		System.out.println(this.getMail());
		
		//
        String targetDirectory = "d:\\documents";
        String targetFileName = uploadFileName;
        File target = new File(targetDirectory, targetFileName);
        FileInputStream fin = null;
        try{
        	try {
    			fin = new FileInputStream(upload);
    			long a = fin.available();
    			int b =  (int) (a/(1024*1024)); //mb
    			System.out.println(a+"    "+b);
    			fin.close();
    		} catch (FileNotFoundException e1) {
    			e1.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
			FileUtils.copyFile(upload, target);
			setUploadFileName(target.getPath());//保存文件的存放路径
		} catch (IOException e) {
			e.printStackTrace();
		}            
        
        setUploadFileName(target.getPath());//保存文件的存放路径

		return "success1";
	}


	public String upload2(){

		System.out.println(this.getEmails());
		System.out.println(this.getUpload());
		return "success1";
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

//	public Map getParam() {
//		return param;
//	}
//
//	public void setParam(Map param) {
//		this.param = param;
//	}

	
}
