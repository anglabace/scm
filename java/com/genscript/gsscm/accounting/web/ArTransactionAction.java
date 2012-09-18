package com.genscript.gsscm.accounting.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.dto.ArInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ArInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.accounting.service.CollectionService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;


@Results( {
	@Result(name = "forward_record_page", location = "accounting/authorization/record_transaction.jsp"),
	@Result(name = "no", location = "accounting/authorization/no.jsp"),   
	@Result(name = "unauthorize_transaction", location = "accounting/authorization/unauthorize_transaction.jsp"),
	@Result(name = "ar_transaction", location = "accounting/authorization/ar_transaction.jsp")
	
})

/**
 * @function 对Transaction进行审核 
 * @author   陈文擎
 * @Date     2010-11-24
 * @
 */

public class ArTransactionAction  extends ActionSupport {
	
	
	@Autowired
	ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	CollectionService collectionService;
    private Page<ArInvoicePaymentView> arInvoicePaymentPage;
	
	public Page<ArInvoicePaymentView> getArInvoicePaymentPage() {
		return arInvoicePaymentPage;
	}

	public void setArInvoicePaymentPage(Page<ArInvoicePaymentView> arInvoicePaymentPage) {
		this.arInvoicePaymentPage = arInvoicePaymentPage;
	}
	
	/**
	 * 分页查寻
	 */
	public String GetList()
	{
		PagerUtil<ArInvoicePaymentView> pagerUtil = new PagerUtil<ArInvoicePaymentView>();
		arInvoicePaymentPage = pagerUtil.getRequestPage();
		arInvoicePaymentPage.setPageSize(15);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ArInvoicePaymentView> paymentList = null;
		long total = 0;
		try {
			paymentList = arInvoicePaymentDao.AuthorizationList(arInvoicePaymentPage,m);
			total = this.arInvoicePaymentDao.getAuthorizationTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		arInvoicePaymentPage.setTotalCount(total);
		arInvoicePaymentPage.setResult(paymentList);
		List<SelectBean> transactionTypeList = collectionService.getCollection("Transaction_Type");
		Struts2Util.getRequest().setAttribute("pagerInfo", arInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("params", m);
		Struts2Util.getRequest().setAttribute("transactionTypeList", transactionTypeList);
 
		return "ar_transaction";
	}
	
	public String Authorize()
	{
		String paymentIds= Struts2Util.getRequest().getParameter("paymentIds");
		if(null==paymentIds)
		paymentIds="0";
		
		
		Map paramter=new HashMap();		
		paramter.put("ids", paymentIds.trim());
		paramter.put("status", Constant.STATUS_INPROCESS);		
		arInvoicePaymentDao.updateStatus(paramter);
		
		 
		return"no";
		
		 
	}
	
	public String Unauthorize()
	{
		String paymentIds= Struts2Util.getRequest().getParameter("paymentIds");
		if(null==paymentIds)
		paymentIds="0";
		
		String reason= Struts2Util.getRequest().getParameter("reason");
		if(null==reason)
		reason="";
		
		
		Map paramter=new HashMap();		
		paramter.put("ids", paymentIds.trim());
		paramter.put("reason", reason.trim());
		paramter.put("status", Constant.STATUS_INVALID);		
		arInvoicePaymentDao.updateStatus(paramter);
		
		 
		return"no";
 
	}
	
	public String unauthorizeTransaction()
	{
		return "unauthorize_transaction";		
	}
	
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
	
	public String forwardRecord() throws Exception {
		//Map transactionMap = Tools.buildMap(Struts2Util.getRequest());
		//BeanUtils.populate(arInvoicePaymentDto,transactionMap);  
		arInvoicePaymentDto = arInvoicePaymentDao.getById(arInvoicePaymentDto.getTransactionId());
		arInvoicePaymentDto.setTransactionTitle(arInvoicePaymentDto.getTransactionNo() == null?"Transaction No - New":"Transaction #"+arInvoicePaymentDto.getTransactionNo());
		
		return "forward_record_page";
	}
	
	
}
