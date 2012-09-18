package com.genscript.gsscm.accounting.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ApInvoiceLineDao;
import com.genscript.gsscm.accounting.entity.ApInvoiceLineView;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.OrderItem;
import com.genscript.gsscm.accounting.service.ApInvoiceLineService;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ApInvoiceLineAction extends ActionSupport {

private Page<ArInvoiceLine> apInvoiceLinePage;
	
	@Autowired
	ApInvoiceLineDao apInvoiceLineDao;
	@Autowired
	ApInvoiceLineService apInvoiceLineService;
	
	public int invoiceId;
	public int orderNo;
	public int itemNo;
	
	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	
	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * 根据order自带过来的List
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<ApInvoiceLineView> list = this.apInvoiceLineDao.queryInvoiceByOrderNo(this.getOrderNo());
		String currency = Struts2Util.getParameter("currency");
		String html = "";
		if(Struts2Util.getParameter("invoiceId")!=null){
		int invoiceIds = Tools.String2Integer(Struts2Util.getParameter("invoiceId"));
		html = this.apInvoiceLineService.fillTableByOrder(list,invoiceIds, currency);
		}else{
			html = this.apInvoiceLineService.fillTableByOrder(list,0,currency);
		}
		Struts2Util.renderHtml(html);
		return null;
	}


	
	/**
	 * 检查订单的qty或者size是否大于orderitem中的qty or size
	 * @return
	 */
	public String checkQtyOrSize(){
		int max = this.apInvoiceLineService.getMaxQtyOrSize(orderNo, itemNo);
		String result = "{max:"+max+"}";
		Struts2Util.renderJson(result);
		return null;
	}
	

}
