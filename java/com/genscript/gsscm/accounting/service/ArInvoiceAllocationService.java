package com.genscript.gsscm.accounting.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.accounting.dao.ArInvoiceAllocationDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceListViewDao;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;


	/**
	 * @function 发票的对账 
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19
	 * 
	 */


@Service
@Transactional
public class ArInvoiceAllocationService {


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
	
	/**
	 * @function AR Transaction对账功能
	 * @author   陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public String afterReconcile(ArTransactionAllocation arTransactionAllocation)
	{
		 
		String  Bad_Debt=Struts2Util.getRequest().getParameter("BadDebt").trim();
		String   Overpayment=Struts2Util.getRequest().getParameter("Overpayment").trim();
		
		arTransactionAllocation.setCreated_by(SessionUtil.getUserId());
		arTransactionAllocation.setCreation_date(new Date());
		arTransactionAllocation.setModified_by(SessionUtil.getUserId());
		arTransactionAllocation.setModify_date(new Date());
		arTransactionAllocation.setStatus(Constant.STATUS_APPROVED);
		
		
		float  PaymentBalance=Tools.String2Float(Struts2Util.getRequest().getParameter("PaymentApply").trim());
	    int    PaymentId=arTransactionAllocation.getTransaction_id();
	   
	    
	    float  InvoiceBalance=Tools.String2Float(Struts2Util.getRequest().getParameter("InvoiceBalance").trim());
	    int    InvoiceId=arTransactionAllocation.getInvoice_id();
	    
	    
	    ArInvoice Invoice=arInvoiceDao.getInvoiceById(""+InvoiceId);
	    ArInvoicePayment Paymentar=arInvoicePaymentDao.getById(new Integer(PaymentId));
	    
	    
		if(Constant.INVOICE_BAD_DEBT.equals(Bad_Debt))  //invoice  余额 坏账处理
		{
			this.autoGeneraeBadPayment(Paymentar,InvoiceBalance,InvoiceId);
			Invoice.setBalance(0f);
			Invoice.setStatus(Constant.STATUS_INPROCESS);//STATUS_CLOSED
			arTransactionAllocation.setStatus(Constant.STATUS_UNAPPROVED);
		}
		else         //invoice  余额 记单处理
		{
			Invoice.setBalance(InvoiceBalance);
			if(InvoiceBalance>0)
			Invoice.setStatus(Constant.STATUS_INPROCESS);
			else
			Invoice.setStatus(Constant.STATUS_CLOSED);  //STATUS_COMPLETED			
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
			Paymentar.setStatus(Constant.STATUS_CLOSED);//STATUS_COMPLETED			
		}
		
		Invoice.setModifiedBy(SessionUtil.getUserId());
		Invoice.setModifyDate(new Date());		
		
		
		Paymentar.setModifiedBy(SessionUtil.getUserId());
		Paymentar.setModifyDate(new Date());
		
		 
		
		arInvoiceAllocationDao.save(arTransactionAllocation);
		arInvoiceAllocationDao.getSession().flush();
		arInvoiceDao.save(Invoice);
		arInvoiceDao.getSession().flush();
		arInvoicePaymentDao.save(Paymentar);
		arInvoicePaymentDao.getSession().flush();
//		 
		 
//		return GetList();
		return "allocation_success";
	}
	
	/**
	 * @function 自动生成一条坏账Payment
	  * @author  陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public void autoGeneraeBadPayment(ArInvoicePayment Paymentar,float  InvoiceBalance,int invoice_id)
	{
		ArInvoicePayment BadPaymentar=new ArInvoicePayment();
		BadPaymentar.setTransactionNo("");
		BadPaymentar.setCustNo(Paymentar.getCustNo());
		BadPaymentar.setTransactionType(Constant.TRANSACTION_TYPE);
		BadPaymentar.setTransactionDate(new Date());
		BadPaymentar.setCurrency(Paymentar.getCurrency());
		BadPaymentar.setAmount(""+InvoiceBalance);
		BadPaymentar.setTransactionFee("0");
		BadPaymentar.setBalance("0");
		BadPaymentar.setStatus(Constant.STATUS_DRAFT);//Constant.STATUS_COMPLETED
		BadPaymentar.setCreatedBy(SessionUtil.getUserId());
		BadPaymentar.setCreationDate(new Date());
		BadPaymentar.setModifiedBy(SessionUtil.getUserId());
		BadPaymentar.setModifyDate(new Date());
		BadPaymentar.setPaymentType(Constant.PAYMENTTYPE_STANDARD);		
		
		BadPaymentar.setAccountName("");
		BadPaymentar.setAccountNo("");
		BadPaymentar.setCcCardHolder("");
		BadPaymentar.setCcCvc("");
		BadPaymentar.setCcExpiration("");
		BadPaymentar.setDescription("");
		BadPaymentar.setRoutingNo("");
		BadPaymentar.setTenderType("Check");

		
		
		
		
		arInvoicePaymentDao.save(BadPaymentar);
		
		ArTransactionAllocation  TransactionAllocation =new ArTransactionAllocation();
		
		 TransactionAllocation.setApply_amount(""+InvoiceBalance);
		 TransactionAllocation.setInvoice_id(invoice_id);
		 TransactionAllocation.setTransaction_id(BadPaymentar.getTransactionId());
		 TransactionAllocation.setCreated_by(SessionUtil.getUserId());
		 TransactionAllocation.setCreation_date(new Date());
		 TransactionAllocation.setModified_by(SessionUtil.getUserId());
		 TransactionAllocation.setModify_date(new Date());
		 TransactionAllocation.setStatus(Constant.STATUS_UNAPPROVED);
		 System.out.println("@@@@@@@@@@@:"+BadPaymentar.getTransactionId());
		 arInvoiceAllocationDao.save(TransactionAllocation);
		
	}
   
	/**
	 * @function 自动生成一条prePayment
	  * @author  陈文擎
	 * @version  1.0
	 * @date     2010-11-19  
	 */
	public void autoGeneraePrePayment(ArInvoicePayment Paymentar,float  PaymentBalance)
	{
		ArInvoicePayment PrePayment=new ArInvoicePayment();
		PrePayment.setTransactionNo("");
		PrePayment.setCustNo(Paymentar.getCustNo());
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
		
		PrePayment.setAccountName(Paymentar.getAccountName());
		PrePayment.setAccountNo(Paymentar.getAccountNo());
		PrePayment.setCcCardHolder(Paymentar.getCcCardHolder());
		PrePayment.setCcCvc(Paymentar.getCcCvc());
		PrePayment.setCcExpiration(Paymentar.getCcExpiration());
		PrePayment.setDescription(Paymentar.getDescription());
//		PrePayment.setPaymentType("");
		PrePayment.setRoutingNo(Paymentar.getRoutingNo());
		PrePayment.setTenderType(Paymentar.getTenderType());
		
		
		arInvoicePaymentDao.save(PrePayment);
	}
	
	
	
}
