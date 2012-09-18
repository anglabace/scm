package com.genscript.gsscm.accounting.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.dao.TransactionCurrencyDao;
import com.genscript.gsscm.accounting.dto.ArInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.accounting.service.ArInvoicePaymentService;
import com.genscript.gsscm.accounting.service.ArInvoiceService;
import com.genscript.gsscm.accounting.service.CollectionService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	   
		@Result(name = "enter", location = "accounting/payment/payment_list.jsp"),
		@Result(name = "forward_delete_page", location = "accounting/payment/delete_transaction.jsp"),
		@Result(name = "forward_record_page", location = "accounting/payment/record_transaction.jsp"),
		@Result(name = "allocation_page", location = "accounting/payment/allocation_history_page.jsp"),
		@Result(name = "invoice_page", location = "accounting/payment/invoice_page.jsp"),
		@Result(name = "order_page", location = "accounting/payment/order_page.jsp"),
		@Result(name = "forward_batch_record_page", location = "accounting/payment/record_batch_tran.jsp"),
		@Result(name = "allocation_list", location = "accounting/allocation/allocation_list.jsp")
})
public class ArInvoicePaymentAction extends ActionSupport {
	private File upload;// 实际上传文件
    private String uploadContentType; // 文件的内容类型
    private String uploadFileName; // 上传文件名
    
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

	//存储invoice line数据
	public Map param=new HashMap();
	
	private Page<ArInvoicePaymentView> arInvoicePaymentPage;
	
	public Page<ArInvoicePaymentView> getArInvoicePaymentPage() {
		return arInvoicePaymentPage;
	}

	public void setArInvoicePaymentPage(Page<ArInvoicePaymentView> arInvoicePaymentPage) {
		this.arInvoicePaymentPage = arInvoicePaymentPage;
	}
	
	private Page<ArInvoiceAllocationView> arInvoiceAllocationPage;
	
	public Page<ArInvoiceAllocationView> getArInvoiceAllocationPage() {
		return arInvoiceAllocationPage;
	}

	public void setArInvoiceAllocationPage(
			Page<ArInvoiceAllocationView> arInvoiceAllocationPage) {
		this.arInvoiceAllocationPage = arInvoiceAllocationPage;
	}

	public ArInvoicePaymentDTO arInvoicePaymentDto = new ArInvoicePaymentDTO();

	public ArInvoicePaymentDTO getArInvoicePaymentDto() {
		return arInvoicePaymentDto;
	}

	public void setArInvoicePaymentDto(ArInvoicePaymentDTO arInvoicePaymentDto) {
		this.arInvoicePaymentDto = arInvoicePaymentDto;
	}
	
	public ArInvoice arInvoice = new ArInvoice();

	@Autowired
	private ArInvoicePaymentService arInvoicePaymentService;
	@Autowired
	ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	private ArInvoiceService arInvoiceService;
	@Autowired
	ArInvoiceLineDao arInvoiceLineDao;
	@Autowired
	CollectionService collectionService;
	@Autowired
	TransactionCurrencyDao currencyDao; 
	@Autowired
	ArInvoiceDao arInvoiceDao;
	/**
	 * 进入invoice_payment页面
	 */
	public String input() throws Exception {
		PagerUtil<ArInvoicePaymentView> pagerUtil = new PagerUtil<ArInvoicePaymentView>();
		arInvoicePaymentPage = pagerUtil.getRequestPage();
		arInvoicePaymentPage.setPageSize(10);
		List<ArInvoicePaymentView> paymentList = null;
		long total = 0;
		try {
			paymentList = arInvoicePaymentDao.list(arInvoicePaymentPage,null);
			total = this.arInvoicePaymentDao.getTotalPage(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		arInvoicePaymentPage.setTotalCount(total);
		arInvoicePaymentPage.setResult(paymentList);
//		PageDTO pagerInfo = new Tools().formPage(arInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("pagerInfo", arInvoicePaymentPage);
		
		return "enter";
	}
	
	/**
	 * 分页查寻
	 */
	public String list() {
		PagerUtil<ArInvoicePaymentView> pagerUtil = new PagerUtil<ArInvoicePaymentView>();
		arInvoicePaymentPage = pagerUtil.getRequestPage();
		arInvoicePaymentPage.setPageSize(10);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ArInvoicePaymentView> paymentList = null;
		long total = 0;
		try {
			paymentList = arInvoicePaymentDao.list(arInvoicePaymentPage,m);
			total = this.arInvoicePaymentDao.getTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		arInvoicePaymentPage.setTotalCount(total);
		arInvoicePaymentPage.setResult(paymentList);
//		PageDTO pagerInfo = new Tools().formPage(arInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("pagerInfo", arInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("params", m);
		
		return "enter";
	}
	
	/**
	 * 跳转至删除页面
	 */
	public String forwardDelete() throws Exception {
		Struts2Util.getRequest().setAttribute("transactionIds", arInvoicePaymentDto.getTransactionIds());
		return "forward_delete_page";
	}
	
	/**
	 * 删除响应
	 * @return
	 */
	public String doDelete() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest req = Struts2Util.getRequest();
		String[] transactionIds = req.getParameter("transactionIds").split(",");
		
		List<ArInvoicePayment> list = new ArrayList();
		for(int i=0; i<transactionIds.length; i++){
			ArInvoicePayment entity = arInvoicePaymentDao.get(new Integer(transactionIds[i]));
			entity.setModifiedBy(SessionUtil.getUserId());
			entity.setModifyDate(new Date());
			entity.setStatusUpdReason(req.getParameter("statusUpReason"));
			list.add(entity);
		}

		boolean bres = arInvoicePaymentService.delete(list);
		Struts2Util.getRequest().setAttribute("operate_result",bres);
		
		return "forward_delete_page";
	}
	
	/**
	 * 跳转至新增、修改页面
	 */
	public String forwardRecord() throws Exception {
		//Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		//BeanUtils.populate(arInvoicePaymentDto,transactionMap);  
		arInvoicePaymentDto = arInvoicePaymentDao.getById(arInvoicePaymentDto.getTransactionId());
		arInvoicePaymentDto.setTransactionTitle(arInvoicePaymentDto.getTransactionNo() == null?"Transaction No - New":"Transaction #"+arInvoicePaymentDto.getTransactionNo());
		String currency = arInvoicePaymentDto.getCurrency();
		if(currency == null || currency.equals("")){
			currency = "USD";
		}
		PbCurrency pbCurrency = currencyDao.getCurrencySymbol(currency);
		arInvoicePaymentDto.setSymbol(pbCurrency.getSymbol());
		
		String status = arInvoicePaymentDto.getStatus();
		//获取Ststus列表 
		List<SelectBean> statusList = new ArrayList<SelectBean>();
		if(status!=null){
			if(status.equals("Draft")){
				String[] arrStatus = {"Draft","Void","Invalid","Reversed"};
				for(int i=0; i<arrStatus.length; i++){
					SelectBean bean = new SelectBean();
					bean.setKey(arrStatus[i]);
					bean.setValue(arrStatus[i]);
					statusList.add(bean);
				}
			}else if(status.equals("Void") || status.equals("Invalid")){
				String[] arrStatus = {status,"Draft"};
				for(int i=0; i<arrStatus.length; i++){
					SelectBean bean = new SelectBean();
					bean.setKey(arrStatus[i]);
					bean.setValue(arrStatus[i]);
					statusList.add(bean);
				}
			}else{
				SelectBean bean = new SelectBean();
				bean.setKey(status);
				bean.setValue(status);
				statusList.add(bean);
			}
		}else{
			statusList = collectionService.getCollection("Status");
		}
		arInvoicePaymentDto.setStatusList(statusList);
		
		//获取Transaction_Type列表
		List<SelectBean> transactionTypeList = new ArrayList<SelectBean>();
		if(status != null && !status.equals("Draft")){
			SelectBean bean = new SelectBean();
			bean.setKey(arInvoicePaymentDto.getTransactionType());
			bean.setValue(arInvoicePaymentDto.getTransactionType());
			transactionTypeList.add(bean);
		}else{
			transactionTypeList = collectionService.getCollection("Transaction_Type");
		}
		arInvoicePaymentDto.setTransactionTypeList(transactionTypeList);
		
		//获取Currency列表
		List<SelectBean> currencyList = new ArrayList<SelectBean>();
		if(status != null && !status.equals("Draft")){
			SelectBean bean = new SelectBean();
			bean.setKey(arInvoicePaymentDto.getCurrency());
			bean.setValue(arInvoicePaymentDto.getCurrency());
			currencyList = new ArrayList<SelectBean>();
			currencyList.add(bean);
		}else{
			currencyList = collectionService.getCollection("Currency");
		}
		arInvoicePaymentDto.setCurrencyList(currencyList);
		
		//获取Tender_Type,Payment_Type,Card,Month,Year列表
		List<SelectBean> tenderTypeList = new ArrayList<SelectBean>();
		List<SelectBean> paymentTypeList = new ArrayList<SelectBean>();
		List<SelectBean> cardList = new ArrayList<SelectBean>();
		List<SelectBean> monthList = new ArrayList<SelectBean>();
		List<SelectBean> yearList = new ArrayList<SelectBean>();
		if(status == null || status.equals("Draft")){
			tenderTypeList = collectionService.getCollection("Tender_Type");
			paymentTypeList = collectionService.getCollection("Payment_Type");
			cardList = collectionService.getCollection("Card");
			monthList = collectionService.getCollection("Month");
			yearList = collectionService.getCollection("Year");
			arInvoicePaymentDto.setReadonly("");
		}else{
			arInvoicePaymentDto.setReadonly("readonly");
			//
			SelectBean bean = new SelectBean();
			bean.setKey(arInvoicePaymentDto.getTenderType());
			bean.setValue(arInvoicePaymentDto.getTenderType());
			tenderTypeList.add(bean);
			//
			bean = new SelectBean();
			bean.setKey(arInvoicePaymentDto.getPaymentType());
			bean.setValue(arInvoicePaymentDto.getPaymentType());
			paymentTypeList.add(bean);
			
			if(arInvoicePaymentDto.getTenderType()!=null && arInvoicePaymentDto.getTenderType().equals("Credit Card")){
				//
				bean = new SelectBean();
				bean.setKey(arInvoicePaymentDto.getCcType());
				bean.setValue(arInvoicePaymentDto.getCcType());
				cardList.add(bean);
				//
				bean = new SelectBean();
				bean.setKey(arInvoicePaymentDto.getCcExpiration().substring(5,7));
				bean.setValue(arInvoicePaymentDto.getCcExpiration().substring(5,7));
				monthList.add(bean);
				//
				bean = new SelectBean();
				bean.setKey(arInvoicePaymentDto.getCcExpiration().substring(0,4));
				bean.setValue(arInvoicePaymentDto.getCcExpiration().substring(0,4));
				yearList.add(bean);
			}
		}
		arInvoicePaymentDto.setTenderTypeList(tenderTypeList);
		arInvoicePaymentDto.setPaymentTypeList(paymentTypeList);
		arInvoicePaymentDto.setCardList(cardList);
		arInvoicePaymentDto.setMonthList(monthList);
		arInvoicePaymentDto.setYearList(yearList);
		
		String ccExpiration = arInvoicePaymentDto.getCcExpiration();
    	if(ccExpiration != null && !ccExpiration.equals("")){
    		arInvoicePaymentDto.setYear(ccExpiration.substring(0,4));
    		arInvoicePaymentDto.setMonth(ccExpiration.substring(5,7));
    	}
		
		List<Map> invoiceList = new ArrayList<Map>();
		if(arInvoicePaymentDto.getTransactionNo() != null)	
			invoiceList = arInvoicePaymentService.getInvoiceList(arInvoicePaymentDto.getTransactionId());
		arInvoicePaymentDto.setInvoiceList(invoiceList);
		
		arInvoicePaymentDto.setUserId(SessionUtil.getUserId());
		arInvoicePaymentDto.setUserName(SessionUtil.getUserName());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		arInvoicePaymentDto.setSysDate(simpleDateFormat.format(new Date()));
		
		
		return "forward_record_page";
	}
	
	public String checkTransactionNoExist() throws IllegalAccessException, InvocationTargetException, SQLException, IOException {
		HttpServletRequest req = Struts2Util.getRequest();
		if(arInvoicePaymentDao.checkTransactionNoExist(req.getParameter("transactionNo").toString())){
			Struts2Util.renderJson("{result:1}");
		}else{
			Struts2Util.renderJson("{result:0}");
		}
		
		return null;
	}
	
	/**
	 * 新增、修改交易记录
	 * @return
	 * @throws Exception 
	 */
	public String saveOrUpdate() throws Exception {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		ArInvoicePayment arInvoicePayment = new ArInvoicePayment();
//		arInvoicePayment.setStatus("Approved");//创建的时候status为approved ，批量导入的时候为unapproved
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		String forwardUrl = transactionMap.get("forwardUrl").toString();
		//System.out.println("transactionMap:"+transactionMap.toString());
		
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		if(transactionMap.get("tenderType").toString().equals("Credit Card")){
			transactionMap.put("ccExpiration", transactionMap.get("ccExpirationYear").toString() + "-" + transactionMap.get("ccExpirationMonth").toString());
		}
		if(!transactionMap.get("transactionId").toString().equals("0")){
			arInvoicePayment = arInvoicePaymentDao.getById(new Integer(transactionMap.get("transactionId").toString()));
		
	  /////////////////////////////////////////////////////////////////
	    
	   
	    if(null!=transactionMap.get("balance"))
		{
	    	float balance=Tools.String2Float(transactionMap.get("balance").toString().trim());
	    	if(  0==balance )
	    	{
	    		arInvoicePayment.setStatus(Constant.STATUS_CLOSED);
	    	}
		}
	    
	   
	    ////////////////////////////////////////////////////////
	    
	    
	    
			//检测编号是否存在
			/*
			if(arInvoicePaymentDao.checkTransactionNoExist(transactionMap.get("transactionNo").toString())){
				Struts2Util.getRequest().setAttribute("isExist", true);
				BeanUtils.populate(arInvoicePayment,transactionMap);
				return "forward_record_page";
			}
			*/
		}else{
			transactionMap.put("transactionNo", arInvoicePaymentService.makeTransactionNo());
			arInvoicePayment.setCreatedBy(SessionUtil.getUserId());
			arInvoicePayment.setCreationDate(date);
		}
		BeanUtils.populate(arInvoicePayment,transactionMap);
		arInvoicePayment.setModifiedBy(SessionUtil.getUserId());
		arInvoicePayment.setModifyDate(date);
		
		boolean bres = arInvoicePaymentService.saveOrUpdate(arInvoicePayment);
		
		boolean bres2 = true;
		List<ArInvoiceAllocationView> allocationList = arInvoicePaymentDao.allocationList(transactionMap);
		if(allocationList.size() == 0 && transactionMap.get("invoiceId") != null){
		//if(transactionMap.get("transactionId").toString().equals("0") && !transactionMap.get("invoiceId").toString().equals("")){
			//保存付款信息
			////////////////////////////////////////////////////
			
			 float applyAmount= Tools.String2Float(transactionMap.get("applyAmount").toString().trim());
			 ArInvoice arInvoice= arInvoiceService.getInvoiceById(transactionMap.get("invoiceId").toString().trim());
			   
			 arInvoice.setBalance(arInvoice.getBalance()-applyAmount);
			if(arInvoice.getBalance()<=applyAmount)
			{
			   arInvoice.setStatus(Constant.STATUS_CLOSED);
			}
			arInvoiceService.edit(arInvoice); 
			//////////////////////////////////////////////////
			ArTransactionAllocation allocationEntity = new ArTransactionAllocation();
			allocationEntity.setTransaction_id(arInvoicePayment.getTransactionId());
			allocationEntity.setInvoice_id(new Integer(transactionMap.get("invoiceId").toString()));
			allocationEntity.setApply_amount(transactionMap.get("applyAmount").toString());
			allocationEntity.setStatus(Constant.STATUS_APPROVED);
			allocationEntity.setCreated_by(SessionUtil.getUserId());
			allocationEntity.setCreation_date(date);
			allocationEntity.setModified_by(SessionUtil.getUserId());
			allocationEntity.setModify_date(date);
			bres2 = arInvoicePaymentService.saveAllocation(allocationEntity);
		}
		
		Struts2Util.getRequest().setAttribute("operate_result",(bres && bres2));
		
		return null;
	}
	
	/**
	 * 查看付款记录
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	public String viewAllocationHistory() throws IllegalAccessException, InvocationTargetException, ParseException{
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		//transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		//BeanUtils.populate(arInvoicePaymentDto,transactionMap);
		//System.out.println("transactionMap:"+transactionMap.toString());
		transactionMap.put("transactionId", arInvoicePaymentDto.getTransactionId());
		
		PagerUtil<ArInvoiceAllocationView> pagerUtil = new PagerUtil<ArInvoiceAllocationView>();
		arInvoiceAllocationPage = pagerUtil.getRequestPage();
		arInvoiceAllocationPage.setPageSize(1000);
		try {
			arInvoiceAllocationPage.setResult(arInvoicePaymentDao.allocationList(transactionMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "allocation_page";
	}
	
	/**
	 * 查看发票信息
	 * @return
	 * @throws Exception 
	 */
	public String viewInvoice() throws Exception{
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		BeanUtils.populate(arInvoicePaymentDto,transactionMap);
		
		String InvoiceNo =Struts2Util.getRequest().getParameter("invoiceNo");
		List<ArInvoiceLine> arInvoiceLines=null;
		try{
		arInvoice =arInvoiceService.getInvoiceByNo(InvoiceNo);
		arInvoiceLines=arInvoiceLineDao.queryInvoiceByInvoiceId(arInvoice.getInvoiceId());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		if((null==arInvoiceLines)||(0==arInvoiceLines.size()))
	    return "invoice_page";
		
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
		
		return "invoice_page";
	}
	
	public String viewOrder() throws IllegalAccessException, InvocationTargetException, ParseException{
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		BeanUtils.populate(arInvoicePaymentDto,transactionMap);
		
//		String InvoiceNo =Struts2Util.getRequest().getParameter("invoiceNo");
//		List<ArInvoiceLine> arInvoiceLines=null;
//		try{
//		arInvoice =arInvoiceService.getInvoiceByNo(InvoiceNo);
//		arInvoiceLines=arInvoiceLineDao.queryInvoiceByInvoiceId(arInvoice.getInvoiceId());
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		return "order_page";
	}
	
	/**
	 * 跳转至批处理页面
	 */
	public String batchRecord() throws Exception {
		arInvoicePaymentDto.setTransactionTypeList(collectionService.getCollection("Transaction_Type"));
		return "forward_batch_record_page";
	}
	
	public String btachUpload() throws BiffException{
//        String targetDirectory = "d:\\documents";
//        String targetFileName = uploadFileName;
        
        arInvoicePaymentDto.setTransactionType(Struts2Util.getRequest().getParameter("transactionType").toString());
        arInvoicePaymentDto.setTransactionTypeList(collectionService.getCollection("Transaction_Type"));
//        File target = new File(targetDirectory,targetFileName);
        try {
        	PagerUtil<ArInvoicePaymentView> pagerUtil = new PagerUtil<ArInvoicePaymentView>();
			arInvoicePaymentPage = pagerUtil.getRequestPage();
			arInvoicePaymentPage.setPageSize(100000);
			List<ArInvoicePaymentView> paymentList = new ArrayList<ArInvoicePaymentView>();
			
        	//FileUtils.copyFile(upload, target);
        	java.io.FileInputStream is = new FileInputStream(upload);
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			int iRows = rs.getRows();
			for (int i = 1; i < iRows; i++){
				Cell cell[] = rs.getRow(i);
				ArInvoicePaymentView payment = new ArInvoicePaymentView();
				payment.setCustNo(Integer.parseInt(cell[0].getContents().trim()));
				payment.setOrderNo(Integer.parseInt(cell[1].getContents().trim()) +"" );
				payment.setInvoiceNo(Integer.parseInt(cell[2].getContents().trim() )+"" );
				payment.setApplyAmount( Float.parseFloat( cell[3].getContents().trim() )+"" );
				payment.setBalance(Float.parseFloat( cell[4].getContents().trim())+"" );
				payment.setTransactionType(cell[5].getContents().trim());
				payment.setDescription(cell[6].getContents().trim());
				payment.setAmount(Float.parseFloat( cell[7].getContents().trim())+"" );
				payment.setTransactionFee(cell[8].getContents().trim());
				payment.setCurrency(cell[9].getContents().trim());
				payment.setTenderType(cell[10].getContents().trim());
				payment.setPaymentType(cell[11].getContents().trim());
				if(payment.getTenderType().equals("Check")){
					payment.setAccountName(cell[12].getContents().trim());
					payment.setAccountNo(cell[13].getContents().trim());
					payment.setRoutingNo(cell[14].getContents().trim());
					payment.setChkNo(cell[15].getContents().trim());
				}else if(payment.getTenderType().equals("Credit Card")){
					payment.setAccountName(cell[16].getContents().trim());
					payment.setCcType(cell[17].getContents().trim());
					payment.setCcCardHolder(cell[18].getContents().trim());
					payment.setAccountNo(cell[19].getContents().trim());
					payment.setCcCvc(cell[20].getContents().trim());
					payment.setCcExpiration(cell[21].getContents().trim());
				}else{
					payment.setAccountName(cell[12].getContents().trim());
					payment.setAccountNo(cell[13].getContents().trim());
					payment.setRoutingNo(cell[14].getContents().trim());
				}
				
				paymentList.add(payment);
			}
			rwb.close();
			is.close();
			
			arInvoicePaymentPage.setResult(paymentList);
			
		} catch(NumberFormatException e){
			Struts2Util.getRequest().setAttribute("error", "NumberFormatException,Please Check the excel file!InvoiceNo,custNo,orderNo and money can not be string");
		}
		catch (Exception e) {
			Struts2Util.getRequest().setAttribute("error", "Please Select A Right File ");
			e.printStackTrace();
		}            
        
		return "forward_batch_record_page";
	}
	
	public String btachSave() throws Exception {
		Date date = new Date();
		HttpServletRequest req = Struts2Util.getRequest();
		
		List<ArInvoicePayment> paymentList = new ArrayList<ArInvoicePayment>();
		ArInvoicePayment arInvoicePayment = new ArInvoicePayment();
		
		JSONArray jsonArray = JSONArray.fromObject(req.getParameter("payments"));
		paymentList = JSONArray.toList(jsonArray, ArInvoicePayment.class);
		boolean bres = true;
		for(int i=0; i<paymentList.size(); i++){
			arInvoicePayment = paymentList.get(i);
			arInvoicePayment.setStatus("Draft");
			arInvoicePayment.setTransactionDate(date);
			arInvoicePayment.setCreatedBy(SessionUtil.getUserId());
			arInvoicePayment.setCreationDate(date);
			arInvoicePayment.setModifiedBy(SessionUtil.getUserId());
			arInvoicePayment.setModifyDate(date);
			arInvoicePayment.setApprovedBy(SessionUtil.getUserId());
			arInvoicePayment.setApproveDate(date);
			if(arInvoicePayment.getBalance().equals(""))arInvoicePayment.setBalance("0.000");
			arInvoicePayment.setTransactionNo(arInvoicePaymentService.makeTransactionNo());
			
			//payment balance 减去相应的值
//			arInvoicePayment.setBalance(arInvoice.getBalance() - Tools.String2Float(source))
			
			bres = arInvoicePaymentService.saveOrUpdate(arInvoicePayment);
//			int transcationId = arInvoicePayment.getTransactionId();
			//
			String orderNo = arInvoicePayment.getOrderNo();
			String invoiceId = arInvoicePayment.getInvoiceNo();
			String applyAmount = arInvoicePayment.getApplyAmount();
			String balance = arInvoicePayment.getBalance();
//			this.arInvoice
			
			if(!orderNo.equals("") && !invoiceId.equals("")){
//				if(arInvoicePaymentDao.checkOrderExist(orderNo) && arInvoicePaymentDao.checkInvoiceExist(invoiceId)){
			//判断invoice信息是否正确，如果正确，则要用balance-applyAmount,相应的payment也要剪掉applyamount
				boolean flag = arInvoicePaymentDao.checkUploadPayment(Tools.String2Integer(invoiceId), Tools.String2Integer(orderNo), 
						arInvoicePayment.getCustNo(),arInvoicePayment.getCurrency(), arInvoicePayment.getApplyAmount());	
				if(flag){
					//相应的payment也要剪掉applyamount
                    String newBalance = "";
                    newBalance  =  (Tools.String2Float(balance) - Tools.String2Float(applyAmount) ) +"";
                    //balace减去applyamount
					arInvoicePayment.setBalance(newBalance)	;
					arInvoicePaymentService.saveOrUpdate(arInvoicePayment);
					
					//插入到allocation中
					ArTransactionAllocation arTransactionAllocation = new ArTransactionAllocation();
					arTransactionAllocation.setTransaction_id(arInvoicePayment.getTransactionId());
					arTransactionAllocation.setApply_amount(applyAmount);
					//arTransactionAllocation.setInvoice_id(arInvoicePaymentDao.getInvoiceIdByNo(invoiceNo));
					arTransactionAllocation.setInvoice_id(new Integer(invoiceId));
					arTransactionAllocation.setCreated_by(SessionUtil.getUserId());
					arTransactionAllocation.setCreation_date(date);
					arTransactionAllocation.setModified_by(SessionUtil.getUserId());
					arTransactionAllocation.setModify_date(date);
					arTransactionAllocation.setStatus("Unapproved");
					bres = arInvoicePaymentDao.saveAllocation(arTransactionAllocation);
				}
			}
		}
		
		arInvoicePaymentDto.setTransactionTypeList(collectionService.getCollection("Transaction_Type"));
		Struts2Util.getRequest().setAttribute("operate_result",bres);
		return "forward_batch_record_page";
	}
	
	/**
	 * 回退至新增、修改页面
	 */
	public String backRecord() throws Exception {
		
		return "forward_record_page";
	}
	
	/**
	 * 计算Amount
	 * @return
	 */
	public String calculateBalance(){
		Map map = Tools.buildMap(Struts2Util.getRequest());
		PbCurrency pbCurrency = currencyDao.getCurrencySymbol(map.get("currency").toString());
		Float balance;
		Float amount = new Float(map.get("amount").toString());
		Float applyAmount = new Float(map.get("applyAmount").toString());
		Float transactionFee = new Float(map.get("transactionFee").toString());
		Float overpaymentAmount = new Float(map.get("overpaymentAmount").toString());
		String isApplied = map.get("isApplied").toString();
		if(isApplied.equals("true")){
			balance = amount - transactionFee - applyAmount - overpaymentAmount;
			if(overpaymentAmount > 0 && balance < 0){
				overpaymentAmount = new Float("0.0");
				balance = amount - transactionFee - applyAmount - overpaymentAmount;
			}
			if(balance < 0)balance = new Float("0.0");
			
		}else{
			balance = amount - transactionFee - applyAmount;
		}
		
		int precision = new Integer(pbCurrency.getPrecision());
		String strformat = "0.";
		for(int i=0; i<precision; i++){
			strformat += "0";
		}
		DecimalFormat df = new DecimalFormat(strformat);
		Struts2Util.renderJson("{amount:'"+df.format(amount)+"',applyAmount:'"+df.format(applyAmount)+"',transactionFee:'"+df.format(transactionFee)+"',overpaymentAmount:'"+df.format(overpaymentAmount)+"',balance:'"+df.format(balance)+"'}");
		
		return null;
	}
	
	/**
	 * 计算Apply Balance
	 * @return
	 */
	public String calculateApplyBalance(){
		Map map = Tools.buildMap(Struts2Util.getRequest());
		PbCurrency pbCurrency = currencyDao.getCurrencySymbol(map.get("currency").toString());
		Float balance = new Float(map.get("balance").toString());
		Float overpaymentAmount = new Float(map.get("overpaymentAmount").toString());
		
		if(overpaymentAmount > balance){
			Struts2Util.renderJson("{success:'false'}");
			return null;
		}else{
			balance = balance - overpaymentAmount;
			int precision = new Integer(pbCurrency.getPrecision());
			String strformat = "0.";
			for(int i=0; i<precision; i++){
				strformat += "0";
			}
			DecimalFormat df = new DecimalFormat(strformat);
			Struts2Util.renderJson("{success:'true',overpaymentAmount:'"+df.format(overpaymentAmount)+"',balance:'"+df.format(balance)+"'}");
			
			return null;
		}
	}
	
}
