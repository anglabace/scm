package com.genscript.gsscm.accounting.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ApInvoicePaymentDao;
import com.genscript.gsscm.accounting.dto.ApInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ApInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.accounting.service.CollectionService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionSupport;


@Results( {
	@Result(name = "forward_record_page", location = "accounting/authorization/record_aptransaction.jsp"),
	@Result(name = "unauthorize_transaction", location = "accounting/authorization/unauthorize_aptransaction.jsp"),
	@Result(name = "ap_transaction", location = "accounting/authorization/ap_transaction.jsp")
	
})

/**
 * @function 对Transaction进行审核 
 * @author   陈文擎
 * @Date     2010-11-24
 * @
 */

public class ApTransactionAction   extends ActionSupport {
	
	
	@Autowired
	ApInvoicePaymentDao apInvoicePaymentDao;
	@Autowired
	CollectionService collectionService;
    private Page<ApInvoicePaymentView> apInvoicePaymentPage;
	
	public Page<ApInvoicePaymentView> getApInvoicePaymentPage() {
		return apInvoicePaymentPage;
	}

	public void setApInvoicePaymentPage(Page<ApInvoicePaymentView> apInvoicePaymentPage) {
		this.apInvoicePaymentPage = apInvoicePaymentPage;
	}
	
	/**
	 * 分页查寻
	 */
	public String GetList()
	{
		PagerUtil<ApInvoicePaymentView> pagerUtil = new PagerUtil<ApInvoicePaymentView>();
		apInvoicePaymentPage = pagerUtil.getRequestPage();
		apInvoicePaymentPage.setPageSize(15);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ApInvoicePaymentView> paymentList = null;
		long total = 0;
		try {
			paymentList = apInvoicePaymentDao.AuthorizationList(apInvoicePaymentPage,m);
			total = this.apInvoicePaymentDao.getAuthorizationTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		apInvoicePaymentPage.setTotalCount(total);
		apInvoicePaymentPage.setResult(paymentList);
		List<SelectBean> transactionTypeList = collectionService.getCollection("Transaction_Type");
		Struts2Util.getRequest().setAttribute("pagerInfo", apInvoicePaymentPage);
		Struts2Util.getRequest().setAttribute("params", m);
		Struts2Util.getRequest().setAttribute("transactionTypeList", transactionTypeList);
		
		return "ap_transaction";
	}
	
	/**
	 * 通过审批
	 * @return
	 */
	public String Authorize()
	{
		String paymentIds= Struts2Util.getRequest().getParameter("paymentIds");
		if(null==paymentIds)
		paymentIds="0";
		
		
		Map paramter=new HashMap();		
		paramter.put("ids", paymentIds.trim());
		paramter.put("status", Constant.STATUS_INPROCESS);		
		apInvoicePaymentDao.updateStatus(paramter);
		
		 
		return"no";
		
		 
	}
	/**
	 * 拒绝
	 * @return
	 */
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
		apInvoicePaymentDao.updateStatus(paramter);
		
		 
		return null;
 
	}
	
	public String unauthorizeTransaction()
	{
		return "unauthorize_transaction";		
	}
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
}
