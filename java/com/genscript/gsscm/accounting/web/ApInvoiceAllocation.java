package com.genscript.gsscm.accounting.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ApInvoiceAllocationDao;
import com.genscript.gsscm.accounting.dao.ApInvoiceDao;
import com.genscript.gsscm.accounting.dao.ApInvoicePaymentDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceListViewDao;
import com.genscript.gsscm.accounting.dto.ApInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ApInvoice;
import com.genscript.gsscm.accounting.entity.ApInvoiceListView;
import com.genscript.gsscm.accounting.entity.ApInvoicePayment;
import com.genscript.gsscm.accounting.entity.ApInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ApTransactionAllocation;
import com.genscript.gsscm.accounting.service.ApInvoicePaymentService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @function 我方付款发票对账
 * @author   swg
 *
 */

@Results( {
	@Result(name = "forward_record_page", location = "accounting/payable/allocation/record_transaction.jsp"),
	@Result(name = "apply_payment_amount", location = "accounting/payable/allocation/apply_payment_amount.jsp"),
	@Result(name = "invoice_list", location = "accounting/payable/allocation/invoice_list.jsp"),
	@Result(name = "payment_allcation", location = "accounting/payable/allocation/payment_allcation.jsp"),
	@Result(name = "allocation_list", location = "accounting/payable/allocation/allocation_list.jsp"),
	@Result(name = "afterReconcile", location = "accounting/payable/allocation/afterReconcile.jsp")
})
public class ApInvoiceAllocation extends ActionSupport {

	@Autowired
	CurrencyDao currency_Dao;
	public Page<ApInvoicePaymentView> apInvoicePaymentPage;
	public ApInvoicePaymentDTO apInvoicePayment=null; 
	public  List<ApInvoiceView> invoiceList = null;
	public  ApInvoice apInvoice;
	public  ApTransactionAllocation apTransactionAllocation =new ApTransactionAllocation();

	
	public Page<ApInvoicePaymentView> getApInvoicePaymentPage() {
		return apInvoicePaymentPage;
	}

	public void setApInvoicePaymentPage(
			Page<ApInvoicePaymentView> apInvoicePaymentPage) {
		this.apInvoicePaymentPage = apInvoicePaymentPage;
	}
	
	public ApTransactionAllocation getApTransactionAllocation() {
		return apTransactionAllocation;
	}

	public void setApTransactionAllocation(
			ApTransactionAllocation apTransactionAllocation) {
		this.apTransactionAllocation = apTransactionAllocation;
	}
	
	
	
	
	@Autowired
	ApInvoiceDao apInvoiceDao;
	@Autowired
	ApInvoicePaymentDao apInvoicePaymentDao;
	@Autowired
	ApInvoiceAllocationDao apInvoiceAllocationDao;
	
	/**
	 * @function 根据条件列出所有符合条件的In Progress状态的AR Transaction
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	 public String GetList()
		{
			PagerUtil<ApInvoicePaymentView> pagerUtil = new PagerUtil<ApInvoicePaymentView>();
			apInvoicePaymentPage = pagerUtil.getRequestPage();
			apInvoicePaymentPage.setPageSize(15);
			Map m = Tools.buildMap(Struts2Util.getRequest());
			m.put("filter_EQS_status", Constant.STATUS_INPROCESS);
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
//			PageDTO pagerInfo = new Tools().formPage(apInvoicePaymentPage);
			Struts2Util.getRequest().setAttribute("pagerInfo", apInvoicePaymentPage);
			Struts2Util.getRequest().setAttribute("params", m);
			
			return "allocation_list";
		}


	
	/**
	 * @function AP Transaction对账功能
	 * @author   swg
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public String Reconcile()
	{
		
		try{
			int id=Tools.String2Integer(Struts2Util.getRequest().getParameter("id").trim());
			apInvoicePayment=apInvoicePaymentDao.getById(id);
			String symbol=currency_Dao.getCurrencySymbol(apInvoicePayment.getCurrency());
			apInvoicePayment.setSymbol(symbol);
		}catch(Exception e)
		{
			e.printStackTrace();
			apInvoicePayment=new ApInvoicePaymentDTO();
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
		
		try{
		String symbol=currency_Dao.getCurrencySymbol(m.get("currency").toString().trim());
		  invoiceList = apInvoiceAllocationDao.list(m);
		  Struts2Util.getRequest().setAttribute("symbol", symbol);
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		  invoiceList=new ArrayList();
		}
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
		int invoiceNo=Tools.String2Integer(Struts2Util.getRequest().getParameter("invoiceNo").trim());
		try{ 
		apInvoice=apInvoiceDao.getInvoiceById(""+invoiceNo);
		}catch(Exception e)
		{
			e.printStackTrace();
			apInvoice=new ApInvoice();
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
		String  Bad_Debt=Struts2Util.getRequest().getParameter("BadDebt").trim();
		String   Overpayment=Struts2Util.getRequest().getParameter("Overpayment").trim();
		
		apTransactionAllocation.setCreatedBy(SessionUtil.getUserId());
		apTransactionAllocation.setCreationDate(new Date());
		apTransactionAllocation.setModifiedBy(SessionUtil.getUserId());
		apTransactionAllocation.setModifyDate(new Date());
		
		apInvoiceAllocationDao.save(apTransactionAllocation);
		
		float  PaymentBalance=Tools.String2Float(Struts2Util.getRequest().getParameter("PaymentApply").trim());
	    int    PaymentId=apTransactionAllocation.getTransactionId();
	   
	    
	    float  InvoiceBalance=Tools.String2Float(Struts2Util.getRequest().getParameter("InvoiceBalance").trim());
	    int    InvoiceId=apTransactionAllocation.getInvoiceId();
	    
	    
	    ApInvoice Invoice=apInvoiceDao.getInvoiceById(""+InvoiceId);
	    ApInvoicePayment Paymentar=apInvoicePaymentDao.getById(new Integer(PaymentId));
	    
	    
		if(Constant.INVOICE_BAD_DEBT.equals(Bad_Debt))  //invoice  余额 坏账处理
		{
			this.autoGeneraeBadPayment(Paymentar,InvoiceBalance);
			Invoice.setBalance(new Float("0.000"));
			Invoice.setStatus(Constant.STATUS_CLOSED);
		}
		else         //invoice  余额 记单处理
		{
			Invoice.setBalance(new Float(""+InvoiceBalance));
			if(InvoiceBalance>0)
			Invoice.setStatus(Constant.STATUS_INPROCESS);
			else
			Invoice.setStatus(Constant.STATUS_COMPLETED);			
		}
		
		if(Constant.INVOICE_OVERPAYMENT.equals(Overpayment))  //生成预付款单
		{
			this.autoGeneraePrePayment(Paymentar,PaymentBalance);
			Paymentar.setBalance("0");
			Paymentar.setStatus(Constant.STATUS_CLOSED);
		}
		else
		{
			Paymentar.setBalance(""+PaymentBalance);
			if(PaymentBalance>0)
			Paymentar.setStatus(Constant.STATUS_INPROCESS);			
			else
			Paymentar.setStatus(Constant.STATUS_COMPLETED);			
		}
		
		Invoice.setModifiedBy(SessionUtil.getUserId());
		Invoice.setModifyDate(new Date());		
		
		
		Paymentar.setModifiedBy(SessionUtil.getUserId());
		Paymentar.setModifyDate(new Date());
		
		
		apInvoiceDao.save(Invoice);
		apInvoicePaymentDao.save(Paymentar);
		
		
		return GetList();
	}
	
	/**
	 * @function 自动生成一条坏账Payment
	  * @author  陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public void autoGeneraeBadPayment(ApInvoicePayment Paymentar,float  InvoiceBalance)
	{
		ApInvoicePayment BadPaymentar=new ApInvoicePayment();
		BadPaymentar.setTransactionNo("");
		BadPaymentar.setVendorNo(Paymentar.getVendorNo());
		BadPaymentar.setTransactionType(Constant.TRANSACTION_TYPE);
		BadPaymentar.setTransactionDate(new Date());
		BadPaymentar.setCurrency(Paymentar.getCurrency());
		BadPaymentar.setAmount(""+InvoiceBalance);
		BadPaymentar.setTransactionFee("0");
		BadPaymentar.setBalance("0");
		BadPaymentar.setStatus(Constant.STATUS_COMPLETED);
		BadPaymentar.setCreatedBy(SessionUtil.getUserId());
		BadPaymentar.setCreationDate(new Date());
		BadPaymentar.setModifiedBy(SessionUtil.getUserId());
		BadPaymentar.setModifyDate(new Date());
		BadPaymentar.setPaymentType(Constant.PAYMENTTYPE_STANDARD);
		apInvoicePaymentDao.save(BadPaymentar);
		
	}
   
	/**
	 * @function 自动生成一条prePayment
	  * @author  陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public void autoGeneraePrePayment(ApInvoicePayment Paymentar,float  PaymentBalance)
	{
		ApInvoicePayment PrePayment=new ApInvoicePayment();
		PrePayment.setTransactionNo("");
		PrePayment.setVendorNo(Paymentar.getVendorNo());
		PrePayment.setTransactionType(Paymentar.getTransactionType());
		PrePayment.setTransactionDate(new Date());
		PrePayment.setCurrency(Paymentar.getCurrency());
		PrePayment.setAmount(""+PaymentBalance);
		PrePayment.setTransactionFee("0");
		PrePayment.setBalance(""+PaymentBalance);
		PrePayment.setStatus(Constant.STATUS_INPROCESS);
		PrePayment.setCreatedBy(SessionUtil.getUserId());
		PrePayment.setCreationDate(new Date());
		PrePayment.setModifiedBy(SessionUtil.getUserId());
		PrePayment.setModifyDate(new Date());
		PrePayment.setPaymentType(Constant.PAYMENTTYPE_PREPAYMENT);
		apInvoicePaymentDao.save(PrePayment);
	}
	
	/**
	 * 
	 * copy
	 * 
	 */
	/**
	 * 跳转至新增、修改页面
	 */
	
	public ApInvoicePaymentDTO apInvoicePaymentDto = new ApInvoicePaymentDTO();

	public ApInvoicePaymentDTO getApInvoicePaymentDto() {
		return apInvoicePaymentDto;
	}

	public void setApInvoicePaymentDto(ApInvoicePaymentDTO apInvoicePaymentDto) {
		this.apInvoicePaymentDto = apInvoicePaymentDto;
	}
	
	public String forwardRecord() throws Exception {
		//Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		//BeanUtils.populate(arInvoicePaymentDto,transactionMap);  
		apInvoicePaymentDto = apInvoicePaymentDao.getById(apInvoicePaymentDto.getTransactionId());
		apInvoicePaymentDto.setTransactionTitle(apInvoicePaymentDto.getTransactionNo() == null?"Transaction No - New":"Transaction #"+apInvoicePaymentDto.getTransactionNo());
		
		return "forward_record_page";
	}
	
	@Autowired
	private ApInvoicePaymentService apInvoicePaymentService;
	/**
	 * 新增、修改交易记录
	 * @return
	 * @throws Exception 
	 */
	public String saveOrUpdate() throws Exception {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		ApInvoicePayment apInvoicePayment = new ApInvoicePayment();
		Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		System.out.println("transactionMap:"+transactionMap.toString());
		
		transactionMap.put("transactionDate", simpleDateFormat.parse(transactionMap.get("transactionDate").toString()));
		if(transactionMap.get("tendType").toString().equals("Creadit Card")){
			transactionMap.put("ccExpiration", transactionMap.get("ccExpirationYear").toString() + "-" + transactionMap.get("ccExpirationMonth").toString());
		}
		if(!transactionMap.get("transactionId").toString().equals("0")){
			apInvoicePayment = apInvoicePaymentDao.getById(new Integer(transactionMap.get("transactionId").toString()));
		}else{
			//检测编号是否存在
			if(apInvoicePaymentDao.checkTransactionNoExist(transactionMap.get("transactionNo").toString())){
				Struts2Util.getRequest().setAttribute("isExist", true);
				BeanUtils.populate(apInvoicePayment,transactionMap);
				return "forward_record_page";
			}
			apInvoicePayment.setCreatedBy(SessionUtil.getUserId());
			apInvoicePayment.setCreationDate(date);
		}
		BeanUtils.populate(apInvoicePayment,transactionMap);
		apInvoicePayment.setModifiedBy(SessionUtil.getUserId());
		apInvoicePayment.setModifyDate(date);
		
		boolean bres = apInvoicePaymentService.saveOrUpdate(apInvoicePayment);
		
		boolean bres2 = true;
		if(transactionMap.get("transactionId").toString().equals("0")){
			//保存付款信息
			ApTransactionAllocation allocationEntity = new ApTransactionAllocation();
			allocationEntity.setTransactionId(apInvoicePayment.getTransactionId());
			allocationEntity.setInvoiceId(new Integer(transactionMap.get("invoiceId").toString()));
			allocationEntity.setApplyAmount(Tools.String2Float(transactionMap.get("applyAmount").toString()) );
			allocationEntity.setCreatedBy(SessionUtil.getUserId());
			allocationEntity.setCreationDate(date);
			allocationEntity.setModifiedBy(SessionUtil.getUserId());
			allocationEntity.setModifyDate(date);
			bres2 = apInvoicePaymentService.saveAllocation(allocationEntity);
		}
		
		Struts2Util.getRequest().setAttribute("operate_result",(bres && bres2));
		
		return GetList();
	}
	
}
