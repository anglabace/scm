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
import com.genscript.gsscm.accounting.dao.ApInvoicePaymentDao;
import com.genscript.gsscm.accounting.dao.TransactionCurrencyDao;
import com.genscript.gsscm.accounting.dto.ApInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ApInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ApInvoicePayment;
import com.genscript.gsscm.accounting.entity.ApInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.ApTransactionAllocation;
import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.accounting.service.ApInvoicePaymentService;
import com.genscript.gsscm.accounting.service.CollectionService;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	   
		@Result(name = "enter", location = "accounting/payable/payments/payment_list.jsp"),
		@Result(name = "forward_delete_page", location = "accounting/payable/payments/delete_transaction.jsp"),
		@Result(name = "forward_record_page", location = "accounting/payable/payments/record_transaction.jsp"),
		@Result(name = "allocation_page", location = "accounting/payable/payments/allocation_history_page.jsp"),
		@Result(name = "invoice_page", location = "accounting/payable/payments/invoice_page.jsp"),
		@Result(name = "order_page", location = "accounting/payable/payments/order_page.jsp"),
		@Result(name = "forward_batch_record_page", location = "accounting/payable/payments/record_batch_tran.jsp")
		
})
public class ApInvoicePaymentAction extends ActionSupport {
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
	
	private Page<ApInvoicePaymentView> apInvoicePaymentPage;
	
	public Page<ApInvoicePaymentView> getApInvoicePaymentPage() {
		return apInvoicePaymentPage;
	}

	public void setApInvoicePaymentPage(Page<ApInvoicePaymentView> apInvoicePaymentPage) {
		this.apInvoicePaymentPage = apInvoicePaymentPage;
	}
	
	private Page<ApInvoiceAllocationView> apInvoiceAllocationPage;
	
	public Page<ApInvoiceAllocationView> getApInvoiceAllocationPage() {
		return apInvoiceAllocationPage;
	}

	public void setApInvoiceAllocationPage(
			Page<ApInvoiceAllocationView> apInvoiceAllocationPage) {
		this.apInvoiceAllocationPage = apInvoiceAllocationPage;
	}

	public ApInvoicePaymentDTO apInvoicePaymentDto = new ApInvoicePaymentDTO();

	public ApInvoicePaymentDTO getApInvoicePaymentDto() {
		return apInvoicePaymentDto;
	}

	public void setApInvoicePaymentDto(ApInvoicePaymentDTO apInvoicePaymentDto) {
		this.apInvoicePaymentDto = apInvoicePaymentDto;
	}
	
	@Autowired
	private ApInvoicePaymentService apInvoicePaymentService;
	@Autowired
	ApInvoicePaymentDao apInvoicePaymentDao;
	@Autowired
	CollectionService collectionService;
	@Autowired
	TransactionCurrencyDao currencyDao; 

	
	/**
	 * 进入invoice_payment页面
	 */
	public String input() throws Exception {
		PagerUtil<ApInvoicePaymentView> pagerUtil = new PagerUtil<ApInvoicePaymentView>();
		apInvoicePaymentPage = pagerUtil.getRequestPage();
		apInvoicePaymentPage.setPageSize(15);
		List<ApInvoicePaymentView> paymentList = null;
		long total = 0;
		try {
			paymentList = apInvoicePaymentDao.list(apInvoicePaymentPage,null);
			total = this.apInvoicePaymentDao.getTotalPage(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		apInvoicePaymentPage.setTotalCount(total);
		apInvoicePaymentPage.setResult(paymentList);
//		PageDTO pagerInfo = new Tools().formPage(apInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("pagerInfo", apInvoicePaymentPage);
		
		return "enter";
	}
	
	/**
	 * 分页查寻
	 */
	public String list() {
		PagerUtil<ApInvoicePaymentView> pagerUtil = new PagerUtil<ApInvoicePaymentView>();
		apInvoicePaymentPage = pagerUtil.getRequestPage();
		apInvoicePaymentPage.setPageSize(15);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ApInvoicePaymentView> paymentList = null;
		long total = 0;
		try {
			paymentList = apInvoicePaymentDao.list(apInvoicePaymentPage,m);
			total = this.apInvoicePaymentDao.getTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		apInvoicePaymentPage.setTotalCount(total);
		apInvoicePaymentPage.setResult(paymentList);
//		PageDTO pagerInfo = new Tools().formPage(apInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("pagerInfo", apInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("params", m);
		
		return "enter";
	}
	
	/**
	 * 跳转至删除页面
	 */
	public String forwardDelete() throws Exception {
		Struts2Util.getRequest().setAttribute("transactionIds", apInvoicePaymentDto.getTransactionIds());
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
		
		List<ApInvoicePayment> list = new ArrayList();
		for(int i=0; i<transactionIds.length; i++){
			ApInvoicePayment entity = apInvoicePaymentDao.get(new Integer(transactionIds[i]));
			entity.setStatus("Void");
			entity.setModifiedBy(SessionUtil.getUserId());
			entity.setModifyDate(new Date());
			entity.setStatusUpdReason(req.getParameter("statusUpReason"));
			list.add(entity);
		}

		boolean bres = apInvoicePaymentService.delete(list);
		Struts2Util.getRequest().setAttribute("operate_result",bres);
		
		return "forward_delete_page";
	}
	
	/**
	 * 跳转至新增、修改页面
	 */
	public String forwardRecord() throws Exception {
		apInvoicePaymentDto = apInvoicePaymentDao.getById(apInvoicePaymentDto.getTransactionId());
		apInvoicePaymentDto.setTransactionTitle(apInvoicePaymentDto.getTransactionNo() == null?"Transaction No - New":"Transaction #"+apInvoicePaymentDto.getTransactionNo());
		
		String currency = apInvoicePaymentDto.getCurrency();
		if(currency == null || currency.equals("")){
			currency = "USD";
		}
		if(apInvoicePaymentDto.getCurrency() == null){
			apInvoicePaymentDto.setCurrency("USD");
		}
		PbCurrency pbCurrency = currencyDao.getCurrencySymbol(apInvoicePaymentDto.getCurrency());
		apInvoicePaymentDto.setSymbol(pbCurrency.getSymbol());
		
		String status = apInvoicePaymentDto.getStatus();
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
		apInvoicePaymentDto.setStatusList(statusList);
		
		//获取Transaction_Type列表
		List<SelectBean> transactionTypeList = new ArrayList<SelectBean>();
		if(status != null && !status.equals("Draft")){
			SelectBean bean = new SelectBean();
			bean.setKey(apInvoicePaymentDto.getTransactionType());
			bean.setValue(apInvoicePaymentDto.getTransactionType());
			transactionTypeList.add(bean);
		}else{
			transactionTypeList = collectionService.getCollection("Transaction_Type");
		}
		apInvoicePaymentDto.setTransactionTypeList(transactionTypeList);
		
		//获取Currency列表
		List<SelectBean> currencyList = new ArrayList<SelectBean>();
		if(status != null && !status.equals("Draft")){
			SelectBean bean = new SelectBean();
			bean.setKey(apInvoicePaymentDto.getCurrency());
			bean.setValue(apInvoicePaymentDto.getCurrency());
			currencyList = new ArrayList<SelectBean>();
			currencyList.add(bean);
		}else{
			currencyList = collectionService.getCollection("Currency");
		}
		apInvoicePaymentDto.setCurrencyList(currencyList);
		
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
			apInvoicePaymentDto.setReadonly("");
		}else{
			apInvoicePaymentDto.setReadonly("readonly");
			//
			SelectBean bean = new SelectBean();
			bean.setKey(apInvoicePaymentDto.getTenderType());
			bean.setValue(apInvoicePaymentDto.getTenderType());
			tenderTypeList.add(bean);
			//
			bean = new SelectBean();
			bean.setKey(apInvoicePaymentDto.getPaymentType());
			bean.setValue(apInvoicePaymentDto.getPaymentType());
			paymentTypeList.add(bean);
			
			if(apInvoicePaymentDto.getTenderType()!=null && apInvoicePaymentDto.getTenderType().equals("Credit Card")){
				//
				bean = new SelectBean();
				bean.setKey(apInvoicePaymentDto.getCcType());
				bean.setValue(apInvoicePaymentDto.getCcType());
				cardList.add(bean);
				//
				bean = new SelectBean();
				bean.setKey(apInvoicePaymentDto.getCcExpiration().substring(5,7));
				bean.setValue(apInvoicePaymentDto.getCcExpiration().substring(5,7));
				monthList.add(bean);
				//
				bean = new SelectBean();
				bean.setKey(apInvoicePaymentDto.getCcExpiration().substring(0,4));
				bean.setValue(apInvoicePaymentDto.getCcExpiration().substring(0,4));
				yearList.add(bean);
			}
		}
		apInvoicePaymentDto.setTenderTypeList(tenderTypeList);
		apInvoicePaymentDto.setPaymentTypeList(paymentTypeList);
		apInvoicePaymentDto.setCardList(cardList);
		apInvoicePaymentDto.setMonthList(monthList);
		apInvoicePaymentDto.setYearList(yearList);
		
		String ccExpiration = apInvoicePaymentDto.getCcExpiration();
    	if(ccExpiration != null && !ccExpiration.equals("")){
    		apInvoicePaymentDto.setYear(ccExpiration.substring(0,4));
    		apInvoicePaymentDto.setMonth(ccExpiration.substring(5,7));
    	}
		
		List<Map> invoiceList = new ArrayList<Map>();
		if(apInvoicePaymentDto.getTransactionNo() != null)	
			invoiceList = apInvoicePaymentService.getInvoiceList(apInvoicePaymentDto.getTransactionId());
		apInvoicePaymentDto.setInvoiceList(invoiceList);
		
		apInvoicePaymentDto.setUserId(SessionUtil.getUserId());
		apInvoicePaymentDto.setUserName(SessionUtil.getUserName());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		apInvoicePaymentDto.setSysDate(simpleDateFormat.format(new Date()));
		
		return "forward_record_page";
	}
	
	public String checkTransactionNoExist() throws IllegalAccessException, InvocationTargetException, SQLException, IOException {
		HttpServletRequest req = Struts2Util.getRequest();
		if(apInvoicePaymentDao.checkTransactionNoExist(req.getParameter("transactionNo").toString())){
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
		ApInvoicePayment apInvoicePayment = new ApInvoicePayment();
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		String forwardUrl = transactionMap.get("forwardUrl").toString();
		
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		if(transactionMap.get("tenderType").toString().equals("Creadit Card")){
			transactionMap.put("ccExpiration", transactionMap.get("ccExpirationYear").toString() + "-" + transactionMap.get("ccExpirationMonth").toString());
		}
		if(!transactionMap.get("transactionId").toString().equals("0")){
			apInvoicePayment = apInvoicePaymentDao.getById(new Integer(transactionMap.get("transactionId").toString()));
			//检测编号是否存在
			/*
			if(apInvoicePaymentDao.checkTransactionNoExist(transactionMap.get("transactionNo").toString())){
				Struts2Util.getRequest().setAttribute("isExist", true);
				BeanUtils.populate(apInvoicePayment,transactionMap);
				return "forward_record_page";
			}
			*/
		}else{
			transactionMap.put("transactionNo", apInvoicePaymentService.makeTransactionNo());
			apInvoicePayment.setCreatedBy(SessionUtil.getUserId());
			apInvoicePayment.setCreationDate(date);
		}
		BeanUtils.populate(apInvoicePayment,transactionMap);
		apInvoicePayment.setModifiedBy(SessionUtil.getUserId());
		apInvoicePayment.setModifyDate(date);
		
		

		
//		if( Tools.String2Float(apInvoicePayment.getBalance())==0 ){
//			apInvoicePayment.setStatus(Constant.STATUS_COMPLETED );
//		}
		
		
		boolean bres = apInvoicePaymentService.saveOrUpdate(apInvoicePayment);
		
		boolean bres2 = true;
		List<ApInvoiceAllocationView> allocationList = apInvoicePaymentDao.allocationList(transactionMap);
		if(allocationList.size() == 0 && transactionMap.get("invoiceId") != null){
			//保存付款信息
			ApTransactionAllocation allocationEntity = new ApTransactionAllocation();
			allocationEntity.setTransactionId(apInvoicePayment.getTransactionId());
			int invoiceId = new Integer(transactionMap.get("invoiceId").toString());
			allocationEntity.setInvoiceId(invoiceId);
			allocationEntity.setApplyAmount(Tools.String2Float(transactionMap.get("applyAmount").toString()) );
			allocationEntity.setCreatedBy(SessionUtil.getUserId());
			allocationEntity.setCreationDate(date);
			allocationEntity.setModifiedBy(SessionUtil.getUserId());
			allocationEntity.setModifyDate(date);
			
			String invoiceClose = Struts2Util.getParameter("invoiceClose");//
			if(invoiceClose != null && !"".equals(invoiceClose)){
				apInvoicePaymentService.updateInvoiceState(invoiceId);
			}
			bres2 = apInvoicePaymentService.saveAllocation(allocationEntity);
		}
		
		Struts2Util.getRequest().setAttribute("operate_result",(bres && bres2));
		
		if(forwardUrl.equals("ar-invoice-payment-list")){
			return input();
		}else{
			return forwardUrl;
		}
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		BeanUtils.populate(apInvoicePaymentDto,transactionMap);
		
		PagerUtil<ApInvoiceAllocationView> pagerUtil = new PagerUtil<ApInvoiceAllocationView>();
		apInvoiceAllocationPage = pagerUtil.getRequestPage();
		apInvoiceAllocationPage.setPageSize(1000);
		try {
			apInvoiceAllocationPage.setResult(apInvoicePaymentDao.allocationList(transactionMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "allocation_page";
	}
	
	/**
	 * 跳转至批处理页面
	 */
	public String batchRecord() throws Exception {
		apInvoicePaymentDto.setTransactionTypeList(collectionService.getCollection("Transaction_Type"));
		return "forward_batch_record_page";
	}
	
	public String btachUpload() throws BiffException{
        String targetDirectory = "d:\\documents";
        String targetFileName = uploadFileName;
        
        apInvoicePaymentDto.setTransactionType(Struts2Util.getRequest().getParameter("transactionType").toString());
        apInvoicePaymentDto.setTransactionTypeList(collectionService.getCollection("Transaction_Type"));
//        File target = new File(targetDirectory,targetFileName);
        try {
        	PagerUtil<ApInvoicePaymentView> pagerUtil = new PagerUtil<ApInvoicePaymentView>();
			apInvoicePaymentPage = pagerUtil.getRequestPage();
			apInvoicePaymentPage.setPageSize(100000);
			List<ApInvoicePaymentView> paymentList = new ArrayList<ApInvoicePaymentView>();
			
        	//FileUtils.copyFile(upload, target);
        	java.io.InputStream is = new FileInputStream(upload);
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			int iRows = rs.getRows();
			for (int i = 1; i < iRows; i++){
				Cell cell[] = rs.getRow(i);
				ApInvoicePaymentView payment = new ApInvoicePaymentView();
				payment.setVendorNo(Integer.parseInt(cell[0].getContents().trim()));
				payment.setTransactionType(cell[1].getContents().trim());
				payment.setDescription(cell[2].getContents().trim());
				payment.setAmount(Float.parseFloat((cell[3].getContents().trim()) ));
				payment.setTransactionFee(Float.parseFloat(cell[4].getContents().trim())+"" );
				payment.setCurrency(cell[5].getContents().trim());
				payment.setTenderType(cell[6].getContents().trim());
				payment.setPaymentType(cell[7].getContents().trim());
				if(payment.getTenderType().equals("Check")){
					payment.setAccountName(cell[8].getContents().trim());
					payment.setAccountNo(cell[9].getContents().trim());
					payment.setRoutingNo(cell[10].getContents().trim());
					payment.setChkNo(cell[11].getContents().trim());
				}else if(payment.getTenderType().equals("Credit Card")){
					payment.setAccountName(cell[12].getContents().trim());
					payment.setCcType(cell[13].getContents().trim());
					payment.setCcCardHolder(cell[14].getContents().trim());
					payment.setAccountNo(cell[15].getContents().trim());
					payment.setCcCvc(cell[16].getContents().trim());
					payment.setCcExpiration(cell[17].getContents().trim());
				}else{
					payment.setAccountName(cell[8].getContents().trim());
					payment.setAccountNo(cell[9].getContents().trim());
					payment.setRoutingNo(cell[10].getContents().trim());
				}
				
				paymentList.add(payment);
			}
			rwb.close();
			is.close();
			
			apInvoicePaymentPage.setResult(paymentList);
			
		} catch(NumberFormatException e){
			Struts2Util.getRequest().setAttribute("error", "NumberFormatException,Please Check the excel file,custNo and money can not be string");
		}
        catch (Exception e) {
        	Struts2Util.getRequest().setAttribute("error", e.getMessage());
			e.printStackTrace();
		}            
        
		return "forward_batch_record_page";
	}
	
	public String btachSave() throws Exception {
		Date date = new Date();
		HttpServletRequest req = Struts2Util.getRequest();
//		System.out.println("jsonstr:"+req.getParameter("payments"));
		
		List<ApInvoicePayment> paymentList = new ArrayList<ApInvoicePayment>();
		ApInvoicePayment apInvoicePayment = new ApInvoicePayment();
		
		JSONArray jsonArray = JSONArray.fromObject(req.getParameter("payments"));
		paymentList = JSONArray.toList(jsonArray, ApInvoicePayment.class);
		boolean bres = true;
		float balance = 0f;
		for(int i=0; i<paymentList.size(); i++){
			apInvoicePayment = paymentList.get(i);
			balance = Tools.String2Float(apInvoicePayment.getAmount()) - Tools.String2Float(apInvoicePayment.getTransactionFee());
			apInvoicePayment.setBalance(Tools.formatFloat(balance) );
			apInvoicePayment.setStatus("Draft");
			apInvoicePayment.setTransactionDate(date);
			apInvoicePayment.setCreatedBy(SessionUtil.getUserId());
			apInvoicePayment.setCreationDate(date);
			apInvoicePayment.setModifiedBy(SessionUtil.getUserId());
			apInvoicePayment.setModifyDate(date);
			apInvoicePayment.setTransactionNo(apInvoicePaymentService.makeTransactionNo());
			
			bres = apInvoicePaymentService.saveOrUpdate(apInvoicePayment);
		}
		
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
	 * 获取货币信息
	 * @return
	 */
	public String getCurrencyInfo(){
		Map map = Tools.buildMap(Struts2Util.getRequest());
		PbCurrency pbCurrency = currencyDao.getCurrencySymbol(map.get("currency").toString());
		Struts2Util.renderJson("{currencyCode:'"+pbCurrency.getCurrencyCode()+"',symbol:'"+pbCurrency.getSymbol()+"',precision:'"+pbCurrency.getPrecision()+"'}");
		
		return null;
	}
	
	/**
	 * 计算Amount
	 * @return
	 */
	public String calculateBalance(){
		Map map = Tools.buildMap(Struts2Util.getRequest());
		PbCurrency pbCurrency = currencyDao.getCurrencySymbol(map.get("currency").toString());
		Float amount = new Float(map.get("amount").toString());
		Float applyAmount = new Float(map.get("applyAmount").toString());
		Float balance = amount - applyAmount;
		
		int precision = new Integer(pbCurrency.getPrecision());
		String strformat = "0.";
		for(int i=0; i<precision; i++){
			strformat += "0";
		}
		DecimalFormat df = new DecimalFormat(strformat);
		Struts2Util.renderJson("{amount:'"+df.format(amount)+"',applyAmount:'"+df.format(applyAmount)+"',balance:'"+df.format(balance)+"'}");
		
		return null;
	}
	
	
}
