package com.genscript.gsscm.accounting.web;


import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.accounting.util.Constant;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoiceAllocationDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.entity.AuthorizeAllocationView;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionSupport;


@Results( {
	@Result(name="authorize_allocation_list",location="accounting/authorization/authorize_allocation_list.jsp"),
	@Result(name="un_auth_page",location="accounting/authorization/unauthorize_allocation.jsp")
})

public class ArApproveAllocationAction extends ActionSupport {
	
	@Autowired
	ArInvoiceAllocationDao arInvoiceAllocationDao;
	@Autowired 
	ArInvoiceDao arInvoiceDao;
	@Autowired
	ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	PublicService publicService;
	
	
    public String dispatch="";
	public  ArTransactionAllocation arTransactionAllocation=new ArTransactionAllocation(); 

    Page<AuthorizeAllocationView> allocationPage;
    
    List<PbDropdownListOptions> dropDownList;
    
    String select;
    
    public String StatusUpdReason="";

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public Page<AuthorizeAllocationView> getAllocationPage() {
			return allocationPage;
		}

		public void setAllocationPage(Page<AuthorizeAllocationView> allocationPage) {
			this.allocationPage = allocationPage;
		}
	public ArTransactionAllocation getArTransactionAllocation() {
		return arTransactionAllocation;
	}


	public void setArTransactionAllocation(
			ArTransactionAllocation arTransactionAllocation) {
		this.arTransactionAllocation = arTransactionAllocation;
	}

	public String getDispatch() {
		return dispatch;
	}


	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
	

	public String getAllctions() {
		return allctions;
	}

	public void setAllctions(String allctions) {
		this.allctions = allctions;
	}

	
	/**
	 * @function  获取unapproved'的allocation记录,并且 allocation记录 对应的transaction 状态必须不为 Draft
	 * 
	 */
	public String getList()
	{
		PagerUtil<AuthorizeAllocationView> pagerUtil = new PagerUtil<AuthorizeAllocationView>();
		allocationPage = pagerUtil.getRequestPage();
		allocationPage.setPageSize(10);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		allocationPage = this.arInvoiceAllocationDao.queryUnapproviedList(allocationPage,m);
		dropDownList = publicService.getDropdownList("AR_TRANSACTION_TYPE");
		Struts2Util.getRequest().setAttribute("pagerInfo", allocationPage);
		Struts2Util.getRequest().setAttribute("param", m);
//		Struts2Util.getRequest().setAttribute("dropDownList", dropDownList);
		StringBuffer sb = new StringBuffer();
		for(PbDropdownListOptions o : dropDownList){
			sb.append("<option value='"+o.getValue()+"'>"+o.getText()+"</option>");
		}
		select = sb.toString();
		return "authorize_allocation_list";
	}
	

	
	

	public void approveAllocation()
	{   
		
		int id=arTransactionAllocation.getId();
		List<ArTransactionAllocation>ta_list =arInvoiceAllocationDao.find(Restrictions.eq("id",new Integer(id)));
		arTransactionAllocation=ta_list.get(0);
		
		int invoiceId=arTransactionAllocation.getInvoice_id();
		List<ArInvoice>inv_list=null;
		try{
	    inv_list =(List<ArInvoice>)arInvoiceDao.find(Restrictions.eq("invoiceId", new Integer(invoiceId))
	    		                                               ,Restrictions.eq("status", Constant.STATUS_INPROCESS));  //Expression
		}catch(Exception e){e.printStackTrace();}
		
		 if(0==inv_list.size())      //  如果 它对应的 in process 状态 的ArInvoice的不存在 
		    {	
		    	result=id+" "+result;
		    	String msg = "{ msg:'"+result+"' }";
		    	return;
				//Struts2Util.renderJson(msg);
		    }		 
		
	    ArInvoice arInvoice=inv_list.get(0);
	    
	    int paymentId=arTransactionAllocation.getTransaction_id();
	    List<ArInvoicePayment>pay_list =null;
	    try{
	    pay_list =(List<ArInvoicePayment>)arInvoicePaymentDao.find(Restrictions.eq("transactionId",new Integer(paymentId)));
	    }catch(Exception e){e.printStackTrace();}
	    ArInvoicePayment arInvoicePayment=pay_list.get(0);
	    	    
	   
	    
	    float appled_mount= Tools.String2Float(arTransactionAllocation.getApply_amount());
	    float invoice_balance=arInvoice.getBalance();
	    if(invoice_balance>0)
	    {
	    	
	    }
	    else
	    {
//	    	arInvoiceDao.getSession().clear();
	    	arInvoice.setStatus(Constant.STATUS_CLOSED);
	    	Session  session=arInvoiceDao.getSessionFactory().openSession();
	    	session.beginTransaction().begin();
	    	session.update(arInvoice);
	    	session.flush();
	    	session.beginTransaction().commit();
	    	session.close();
//	    	arInvoiceDao.save(arInvoice);
	    }
        float payment_balance=Tools.String2Float(arInvoicePayment.getBalance());
	    if(payment_balance>0)
	    {
	    	
	    }
	    else
	    {
//	    	arInvoicePaymentDao.getSession().clear();
	    	arInvoicePayment.setStatus(Constant.STATUS_CLOSED);
	    	Session  session=arInvoicePaymentDao.getSessionFactory().openSession();
	    	session.beginTransaction().begin();
	    	session.update(arInvoicePayment);
	    	session.flush();
	        session.beginTransaction().commit();
	    	session.close();
//	    	arInvoicePaymentDao.save(arInvoicePayment);
	    }
	    
//	    arInvoiceAllocationDao.getSession().clear();
	    arTransactionAllocation.setStatus(Constant.STATUS_APPROVED);
	    Session  session=arInvoiceAllocationDao.getSessionFactory().openSession();
	    session.beginTransaction().begin();
	    session.update(arTransactionAllocation);
//	    session.createQuery(" update ArTransactionAllocation a set a.");
	    session.flush();
	    session.beginTransaction().commit();
    	session.close();
//	    arInvoiceAllocationDao.save(arTransactionAllocation);
	    
	       
//		return "sucesss";
	}
    
	public String allctions="";
	
	public String result="";
	
	
	public String approveBatch()
	{
		String[] all=allctions.split(",");
		
		if(null==all)
		return "sucesss";
		
		result="";
		
		for(int i=0;i<all.length;i++)
		{
			int id=Tools.String2Integer(all[i].trim());
			if(0==id)
			continue;
			arTransactionAllocation.setId(id);
			
			approveAllocation();
		}
		
		 String msg = "{ msg:'"+result.trim()+"' }";
		 System.out.println(result);
		 Struts2Util.renderJson("{ msg:'"+result.trim()+"' }");
		 result="";
		return null;
	}
    
	
	
	public String unapprove()
	{
		
		int id=arTransactionAllocation.getId();
		List<ArTransactionAllocation>ta_list =arInvoiceAllocationDao.find(Restrictions.eq("id",new Integer(id)));
		arTransactionAllocation=ta_list.get(0);
		
		int invoiceId=arTransactionAllocation.getInvoice_id();
		List<ArInvoice>inv_list=null;
		try{
	    inv_list =(List<ArInvoice>)arInvoiceDao.find(Restrictions.eq("invoiceId", new Integer(invoiceId)));  
		}catch(Exception e){e.printStackTrace();}
		
	    ArInvoice arInvoice=inv_list.get(0);
	    
	    int paymentId=arTransactionAllocation.getTransaction_id();
	    List<ArInvoicePayment>pay_list =null;
	    try{
	    pay_list =(List<ArInvoicePayment>)arInvoicePaymentDao.find(Restrictions.eq("transactionId",new Integer(paymentId)));
	    }catch(Exception e){e.printStackTrace();}
	    ArInvoicePayment arInvoicePayment=pay_list.get(0);
	    	    
	   
	    
	    float appled_mount= Tools.String2Float(arTransactionAllocation.getApply_amount());
	    float invoice_balance=arInvoice.getBalance();
	    float payment_balance=Tools.String2Float(arInvoicePayment.getBalance());
	    
	    arInvoice.setBalance(appled_mount+invoice_balance);
    	Session  session=arInvoiceDao.getSessionFactory().openSession();
    	session.beginTransaction().begin();
    	session.update(arInvoice);
    	session.flush();
    	session.beginTransaction().commit();
    	session.close();
    	
    	
    	
    	arInvoicePayment.setBalance(""+(appled_mount+payment_balance));
    	session=arInvoicePaymentDao.getSessionFactory().openSession();
    	session.beginTransaction().begin();
    	session.update(arInvoicePayment);
    	session.flush();
        session.beginTransaction().commit();
    	session.close();
    	
    	arTransactionAllocation.setStatus(Constant.STATUS_REJECTED);//Rejected
    	arTransactionAllocation.setStatusUpdReason( StatusUpdReason );
	    session=arInvoiceAllocationDao.getSessionFactory().openSession();
	    session.beginTransaction().begin();
	    session.update(arTransactionAllocation);
//	    session.createQuery(" update ArTransactionAllocation a set a.");
	    session.flush();
	    session.beginTransaction().commit();
    	session.close();
		
		return null;
	}
   
	public String unapproveBatch()
	{
		allctions=Struts2Util.getRequest().getParameter("paymentIds").trim();
		String[] all=allctions.split(",");
		
		if(null==all)
		return "sucesss";
		
		result="";
		
		for(int i=0;i<all.length;i++)
		{
			int id=Tools.String2Integer(all[i].trim());
			if(0==id)
			continue;
			arTransactionAllocation.setId(id);
			
			unapprove();
		}
		
		 String msg = "{ msg:'"+result.trim()+"' }";
		 System.out.println(result);
		 Struts2Util.renderJson(msg);
		 result="";
		return "sucesss";
	}

	public String unAuthPage(){
		return "un_auth_page";
	}
	
	public String getStatusUpdReason() {
		return StatusUpdReason;
	}

	public void setStatusUpdReason(String statusUpdReason) {
		StatusUpdReason = statusUpdReason;
	}
	
	
}
