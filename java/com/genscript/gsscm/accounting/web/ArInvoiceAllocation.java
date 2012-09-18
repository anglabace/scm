package com.genscript.gsscm.accounting.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoiceAllocationDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceListViewDao;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.dto.ArInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ArInvoiceListView;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.accounting.service.ArInvoiceAllocationService;
import com.genscript.gsscm.accounting.service.ArInvoicePaymentService;
import com.genscript.gsscm.accounting.service.CollectionService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @function 发票对账
 * @author   陈文擎
 *
 */

@Results( {
	@Result(name = "forward_record_page", location = "accounting/allocation/record_transaction.jsp"),
	@Result(name = "apply_payment_amount", location = "accounting/allocation/apply_payment_amount.jsp"),
	@Result(name = "invoice_list", location = "accounting/allocation/invoice_list.jsp"),
	@Result(name = "payment_allcation", location = "accounting/allocation/payment_allcation.jsp"),
	@Result(name = "allocation_list", location = "accounting/allocation/allocation_list.jsp"),
	@Result(name = "afterReconcile", location = "accounting/allocation/afterReconcile.jsp"),
	
	@Result(name = "allocation_success", type="redirectAction",location = "ar_invoice_allocation!GetList.action")
})
public class ArInvoiceAllocation extends ActionSupport {

	public Page<ArInvoicePaymentView> arInvoicePaymentPage;
	public ArInvoicePaymentDTO arInvoicePayment=null; 
	public  List<ArInvoiceListView> invoiceList = null;
	public  ArInvoice arInvoice;
	public  ArTransactionAllocation arTransactionAllocation =new ArTransactionAllocation();

	
	public Page<ArInvoicePaymentView> getArInvoicePaymentPage() {
		return arInvoicePaymentPage;
	}

	public void setArInvoicePaymentPage(
			Page<ArInvoicePaymentView> arInvoicePaymentPage) {
		this.arInvoicePaymentPage = arInvoicePaymentPage;
	}
	
	public ArTransactionAllocation getArTransactionAllocation() {
		return arTransactionAllocation;
	}

	public void setArTransactionAllocation(
			ArTransactionAllocation arTransactionAllocation) {
		this.arTransactionAllocation = arTransactionAllocation;
	}
	
	
	
	
	@Autowired
	ArInvoiceDao arInvoiceDao;
	@Autowired
	ArInvoiceListViewDao arInvoiceListViewDao;
	@Autowired
	ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	ArInvoiceAllocationDao arInvoiceAllocationDao;
	@Autowired
	CurrencyDao currency_Dao;
	@Autowired
	ArInvoiceAllocationService arInvoiceAllocationService;
	/**
	 * @function 根据条件列出所有符合条件的In Progress状态的AR Transaction
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	 public String GetList()
		{
			PagerUtil<ArInvoicePaymentView> pagerUtil = new PagerUtil<ArInvoicePaymentView>();
			arInvoicePaymentPage = pagerUtil.getRequestPage();
			arInvoicePaymentPage.setPageSize(15);
			Map m = Tools.buildMap(Struts2Util.getRequest());
			m.put("filter_EQS_status", Constant.STATUS_INPROCESS);
			m.put("filter_EQI_balance", 0);
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
//			PageDTO pagerInfo = new Tools().formPage(arInvoicePaymentPage);
			Struts2Util.getRequest().setAttribute("pagerInfo", arInvoicePaymentPage);
			Struts2Util.getRequest().setAttribute("params", m);
			
			return "allocation_list";
		}


	
	/**
	 * @function AR Transaction对账功能
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	 
	public String Reconcile()
	{
		
		try{
			int id=Tools.String2Integer(Struts2Util.getRequest().getParameter("id").trim());
			arInvoicePayment=arInvoicePaymentDao.getById(id);
			String symbol=currency_Dao.getCurrencySymbol(arInvoicePayment.getCurrency());
			arInvoicePayment.setSymbol(symbol);
		}catch(Exception e)
		{
			e.printStackTrace();
			arInvoicePayment=new ArInvoicePaymentDTO();
		}
		return "payment_allcation";
	}
	
	/**
	 * @function 根据条件列出所有符合条件的In Progress状态的Invoice
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public String getInvoices()
	{
		Map m = Tools.buildMap(Struts2Util.getRequest());
		m.put("filter_EQI_balance", 0);
		try{
		  invoiceList = arInvoiceListViewDao.list(m);
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		  invoiceList=new ArrayList();
		}
		String symbol=currency_Dao.getCurrencySymbol(m.get("currency").toString().trim());
		arInvoicePayment=new ArInvoicePaymentDTO();
		arInvoicePayment.setSymbol(symbol);
		return "invoice_list";
	}
	/**
	 * @function 弹出对账窗口
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-20  
	 */
	public String applyPaymentAmount()
	{	
		arInvoicePayment=new ArInvoicePaymentDTO();
		int invoiceNo=Tools.String2Integer(Struts2Util.getRequest().getParameter("invoiceNo").trim());
		try{ 
		arInvoice=arInvoiceDao.getInvoiceById(""+invoiceNo);
		String symbol=currency_Dao.getCurrencySymbol(arInvoice.getCurrency());		
		arInvoicePayment.setSymbol(symbol);
		}catch(Exception e)
		{
			e.printStackTrace();
			arInvoice=new ArInvoice();
		}
		return "apply_payment_amount";
	}
	/**
	 * @function AR Transaction对账功能
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public String afterReconcile()
	{
	 
		arInvoiceAllocationService.afterReconcile(arTransactionAllocation); 
		return "allocation_success";
	}
 
	
	/**
	 * 
	 * copy
	 * 
	 */
	/**
	 * 跳转至新增、修改页面
	 */
	
	public ArInvoicePaymentDTO arInvoicePaymentDto = new ArInvoicePaymentDTO();

	public ArInvoicePaymentDTO getArInvoicePaymentDto() {
		return arInvoicePaymentDto;
	}

	public void setArInvoicePaymentDto(ArInvoicePaymentDTO arInvoicePaymentDto) {
		this.arInvoicePaymentDto = arInvoicePaymentDto;
	}
	
	@Autowired
	CollectionService collectionService;
	
	/**
	 * 跳转至新增、修改页面
	 */
	public String forwardRecord() throws Exception {
		//Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		//BeanUtils.populate(arInvoicePaymentDto,transactionMap);  
		arInvoicePaymentDto = arInvoicePaymentDao.getById(arInvoicePaymentDto.getTransactionId());
		arInvoicePaymentDto.setTransactionTitle(arInvoicePaymentDto.getTransactionNo() == null?"Transaction No - New":"Transaction #"+arInvoicePaymentDto.getTransactionNo());
		
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
	
	
	@Autowired
	private ArInvoicePaymentService arInvoicePaymentService;
	/**
	 * 新增、修改交易记录
	 * @return
	 * @throws Exception 
	 */
	public String saveOrUpdate() throws Exception {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		ArInvoicePayment arInvoicePayment = new ArInvoicePayment();
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		//System.out.println("transactionMap:"+transactionMap.toString());
		
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		if(transactionMap.get("tendType").toString().equals("Credit Card")){
			transactionMap.put("ccExpiration", transactionMap.get("ccExpirationYear").toString() + "-" + transactionMap.get("ccExpirationMonth").toString());
		}
		if(!transactionMap.get("transactionId").toString().equals("0")){
			arInvoicePayment = arInvoicePaymentDao.getById(new Integer(transactionMap.get("transactionId").toString()));
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
			ArTransactionAllocation allocationEntity = new ArTransactionAllocation();
			allocationEntity.setTransaction_id(arInvoicePayment.getTransactionId());
			allocationEntity.setInvoice_id(new Integer(transactionMap.get("invoiceId").toString()));
			allocationEntity.setApply_amount(transactionMap.get("applyAmount").toString());
			allocationEntity.setCreated_by(SessionUtil.getUserId());
			allocationEntity.setCreation_date(date);
			allocationEntity.setModified_by(SessionUtil.getUserId());
			allocationEntity.setModify_date(date);
			bres2 = arInvoicePaymentService.saveAllocation(allocationEntity);
		}
		
		Struts2Util.getRequest().setAttribute("operate_result",(bres && bres2));
		
		return GetList();
	}
	
}
