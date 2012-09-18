package com.genscript.gsscm.accounting.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.OrderItem;
import com.genscript.gsscm.accounting.service.ArInvoiceLineService;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
@Results( {
	   
	@Result(name = "query", location = "accounting/receive/invoice_list.jsp")
})
public class ArInvoiceLineAction extends ActionSupport {

	private Page<ArInvoiceLine> arInvoiceLinePage;
	
	@Autowired
	ArInvoiceLineDao arInvoiceLineDao;
	@Autowired
	ArInvoiceLineService arInvoiceLineService;
	@Autowired
	CurrencyDao currency_Dao;
	
	
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


	
//	/**
//	 * 该功能不用分页,根据invoice查询
//	 * @return
//	 * @throws Exception
//	 */
//	public String list() throws Exception {
//		
////		PagerUtil<ArInvoiceLine> pagerUtil = new PagerUtil<ArInvoiceLine>();
////		arInvoiceLinePage = pagerUtil.getRequestPage();
////		arInvoiceLinePage.setPageSize(15);
////		long total = this.arInvoiceLineDao.getTotalByInvoiceId(arInvoiceLinePage, invoiceId);
////		List<ArInvoiceLine> list = this.arInvoiceLineDao.queryInvoiceByInvoiceId(arInvoiceLinePage, invoiceId);
////		arInvoiceLinePage.setTotalCount(total);
////		arInvoiceLinePage.setResult(list);
////		PageDTO pagerInfo = new Tools().formPage(arInvoiceLinePage);
////		Struts2Util.getRequest().setAttribute("pagerInfo", pagerInfo);
//		
//		List<ArInvoiceLine> list = this.arInvoiceLineDao.queryInvoiceByInvoiceId(this.getInvoiceId());
//		String html = "";
//		if(list !=null)
//		html = this.arInvoiceLineService.fillTable(list);
//		Struts2Util.renderHtml(html);
//		return null;
//	}

	/**
	 * 根据order自带过来的List
	 * @return
	 * @throws Exception
	 */
	public String list2() throws Exception {
		List<OrderItem> list = this.arInvoiceLineDao.queryInvoiceByOrderNo(this.getOrderNo());
		String html = "";
		String currency=Struts2Util.getParameter("currency");
		String symbol=currency_Dao.getCurrencySymbol(currency );
		if(Struts2Util.getParameter("invoiceId")!=null){
		int invoiceIds = Tools.String2Integer(Struts2Util.getParameter("invoiceId"));		
		html = this.arInvoiceLineService.fillTableByOrder(list,invoiceIds,symbol);
		}else{
			html = this.arInvoiceLineService.fillTableByOrder(list,0,symbol);
		}
		Struts2Util.renderHtml(html);
		return null;
	}


	
	/**
	 * 检查订单的qty或者size是否大于orderitem中的qty or size
	 * @return
	 */
	public String checkQtyOrSize(){
		float max = this.arInvoiceLineService.getMaxQtyOrSize(orderNo, itemNo);
		String result = "{max:"+max+"}";
		Struts2Util.renderJson(result);
		return null;
	}
}
