package com.genscript.gsscm.accounting.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ApInvoiceAllocationDao;
import com.genscript.gsscm.accounting.dao.ApInvoiceDao;
import com.genscript.gsscm.accounting.dao.ApInvoiceLineDao;
import com.genscript.gsscm.accounting.dto.ArInvoiceDTO;
import com.genscript.gsscm.accounting.entity.ApInvoice;
import com.genscript.gsscm.accounting.entity.ApInvoiceLine;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ApTransactionAllocation;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.service.ApInvoiceService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	   
	@Result(name = "success", location = "accounting/payable/invoice/invoice_list.jsp"),
	@Result(name = "enter", location = "accounting/payable/invoice/invoice_list.jsp"),
	@Result(name = "create_invoice", location = "accounting/payable/invoice/create_invoice.jsp"),
	@Result(name = "edit_invoice", location = "accounting/payable/invoice/edit_invoice.jsp"),
	@Result(name = "add_invoiceline",location = "accounting/payable/invoice/ap_add_invoiceline.jsp"),
	@Result(name = "edit_invoiceline",location = "accounting/payable/invoice/ap_edit_invoiceline.jsp"),
	@Result(name = "delete_invoice_page", location = "accounting/payable/invoice/delete_apinvoice.jsp"),
	@Result(name = "modify_invoice_curr", location = "accounting/payable/invoice/modify_invoice_curr.jsp"),
	@Result(name = "view_allocation_history", location = "accounting/payable/invoice/view_apallocation_history.jsp")
})
public class ApInvoiceAction extends ActionSupport {

	@Autowired
	ApInvoiceDao apInvoiceDao;
	
	@Autowired
	ApInvoiceLineDao apInvoiceLineDao;
	@Autowired
	ApInvoiceService apInvoiceService;
	@Autowired
	ApInvoiceAllocationDao apInvoiceAllocationDao;
	@Autowired
	CurrencyDao currency_Dao;
	
	public ApInvoice apInvoice = new ApInvoice();
	
	public ArInvoiceDTO arInvoiceDto=new ArInvoiceDTO();
	public ApInvoiceLine apInvoiceLine = new ApInvoiceLine();
	
	public Map param=new HashMap();
	
	private Page<ApInvoiceView> apInvoicePage;

	List<ApTransactionAllocation>  apInvoiceAllocationList  ;

	public Page<ApInvoiceView> getApInvoicePage() {
		return apInvoicePage;
	}

	public void setApInvoicePage(Page<ApInvoiceView> apInvoicePage) {
		this.apInvoicePage = apInvoicePage;
	}
	
	
	public List<ApTransactionAllocation> getApInvoiceAllocationList() {
		return apInvoiceAllocationList;
	}

	public void setApInvoiceAllocationList(
			List<ApTransactionAllocation> apInvoiceAllocationList) {
		this.apInvoiceAllocationList = apInvoiceAllocationList;
	}

	public ApInvoice getApInvoice() {
		return apInvoice;
	}

	public void setApInvoice(ApInvoice apInvoice) {
		this.apInvoice = apInvoice;
	}

	public ApInvoiceLine getApInvoiceLine() {
		return apInvoiceLine;
	}

	public void setApInvoiceLine(ApInvoiceLine apInvoiceLine) {
		this.apInvoiceLine = apInvoiceLine;
	}
	
	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}
	

	public String delete() throws Exception {
		String ids = Struts2Util.getParameter("invoiceIds");
		Struts2Util.getRequest().setAttribute("invoiceIds", ids);
		return "delete_invoice_page";
	}
	
	/**
	 * 进入创建页面
	 * @return
	 */
	public String add(){
		return "create_invoice";
	}
	
	/**
	 * 进入invoice_list页面
	 */
	@Override
	public String input()  {
		// TODO Auto-generated method stub
		PagerUtil<ApInvoiceView> pagerUtil = new PagerUtil<ApInvoiceView>();
		apInvoicePage = pagerUtil.getRequestPage();
		apInvoicePage.setPageSize(10);
		List<ApInvoiceView> invoiceList = null;
		long total = 0;
		try {
			invoiceList = apInvoiceDao.list(apInvoicePage,null);
			total = this.apInvoiceDao.getTotalPage(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		apInvoicePage.setTotalCount(total);
		apInvoicePage.setResult(invoiceList);
		
//		ApInvoiceLine a = this.apInvoiceLineDao.get(1);
		
		Struts2Util.getRequest().setAttribute("pagerInfo", apInvoicePage);
		
		return "enter";
	}

	/**
	 * 分页查询 
	 */
	public String list() throws Exception {
		PagerUtil<ApInvoiceView> pagerUtil = new PagerUtil<ApInvoiceView>();
		apInvoicePage = pagerUtil.getRequestPage();
		apInvoicePage.setPageSize(10);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ApInvoiceView> invoiceList = null;
		long total = 0;
		try {
			invoiceList = apInvoiceDao.list(apInvoicePage,m);
			total = this.apInvoiceDao.getTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		apInvoicePage.setTotalCount(total);
		apInvoicePage.setResult(invoiceList);
		Struts2Util.getRequest().setAttribute("pagerInfo", apInvoicePage);
		Struts2Util.getRequest().setAttribute("params", m);
		return "success";
	}
	
	/**
	 * 浏览历史付款
	 * @return
	 */
		public String viewAllocationHistory(){
			int id = Tools.String2Integer(Struts2Util.getParameter("invoiceId"));
			if(id!=0){
				String hql = "from ApTransactionAllocation a where a.invoiceId= ? ";
			    apInvoiceAllocationList = (List<ApTransactionAllocation>) this.apInvoiceAllocationDao.createQuery(hql, id).list();	
			}
			return "view_allocation_history";
		}
		
	/**
	 * 添加invoice，跳转到页面
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try
		{
			apInvoice.setCreatedBy(SessionUtil.getUserId());
			apInvoice.setCreationDate(new Date());
			apInvoice.setModifiedBy(SessionUtil.getUserId());
			apInvoice.setModifyDate(new Date());
			apInvoice.setPrintedFlag("N");
			int companyId = this.apInvoiceDao.getCompanyId(apInvoice.getOrderNo());
			apInvoice.setCompanyId(companyId);//测试
			int InvoiceId = apInvoiceService.add(apInvoice);
		    if(0!=param.size())
		    {
		    	String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
				String[] catalogNo = param.get("catalogNo") == null ? null : (String[])param.get("catalogNo");
				String[] names = param.get("name") == null ? null : (String[])param.get("name");
				String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
				String[] qtyUoms = param.get("qtyUom") == null ? null : (String[])param.get("qtyUom");
				String[] unitPrices = param.get("unitPrice") == null ? null : (String[])param.get("unitPrice");
				String[] amounts = param.get("amount") == null ? null : (String[])param.get("amount");
				String[] taxs = param.get("tax") == null ? null : (String[])param.get("tax");
				String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
				String[] lineNo = param.get("lineNo") == null ? null : (String[])param.get("lineNo");
				String[] discounts = param.get("discount") == null ? null : (String[])param.get("discount");
				ApInvoiceLine insertLine=null;
				for(int i=0;i<itemNos.length;i++)
				{
					insertLine=new ApInvoiceLine();
					insertLine.setAmount(Tools.String2Float(amounts[i]));
					insertLine.setCatalogNo(catalogNo[i]);
					insertLine.setCreatedBy(SessionUtil.getUserId());
					insertLine.setCreationDate(new Date());
					insertLine.setDiscount(Tools.String2Float(discounts[i]));
					insertLine.setInvoiceId(InvoiceId);
					insertLine.setItemNo(Tools.String2Integer(itemNos[i]) );
					int linno=Tools.String2Integer(lineNo[i]);
//					insertLine.setLineNo(linno);
					insertLine.setLineNo(i+1);
					insertLine.setModifiedBy(SessionUtil.getUserId());
					insertLine.setModifyDate(new Date());
					insertLine.setName(names[i]);
					insertLine.setOrderNo(apInvoice.getOrderNo());
					insertLine.setQty(Tools.String2Integer(qtys[i]));
					insertLine.setQtyUom(qtyUoms[i]);
					insertLine.setSize(Tools.String2Float(sizes[i]));
					insertLine.setSizeUom("");
					insertLine.setTax(Tools.String2Float(taxs[i]));
					insertLine.setUnitPrice(Tools.String2Float(unitPrices[i]));
					insertLine.setStatus(Constant.STATUS_NEW);
					apInvoiceLineDao.save(insertLine);
				}				
		    }
		    
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		ActionContext ctx =ActionContext.getContext();	
		ctx.getSession().remove("apInvoice");
		ctx.getSession().remove("param");
		
		return input();
	}
	
	/**
	 * 修改invocie
	 * @return
	 */
	public String edit(){
		int invoiceId = Tools.String2Integer(Struts2Util.getParameter("invoiceId") );
		List<ApInvoiceLine> apInvoiceLines=null;
		try{
			apInvoice = new ApInvoice();
		apInvoice = (ApInvoice) this.apInvoiceDao.getSession().get(ApInvoice.class, invoiceId);
		apInvoice.setOldStatus(apInvoice.getStatus());
		apInvoiceLines=apInvoiceLineDao.queryInvoiceByInvoiceId(apInvoice.getInvoiceId());
		String symbol=currency_Dao.getCurrencySymbol(apInvoice.getCurrency());
		apInvoice.setSymbol(symbol);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if((null==apInvoiceLines)||(0==apInvoiceLines.size())){
	    return "edit_invoice";
		}
		int length=apInvoiceLines.size();
		
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
        ApInvoiceLine InvoiceLine=null;
        
		for(int i=0;i<apInvoiceLines.size();i++ )
		{
			InvoiceLine=apInvoiceLines.get(i);
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
		
		Struts2Util.getRequest().setAttribute("paramEL", param);
		return "edit_invoice";
	}

	/**
	 * @function 创建invoiceline信息 
	 * @author   周勇
	 * @
	 */
   
	public String add_invoiceline()
	{
		ActionContext ctx =ActionContext.getContext();
		String symbol=currency_Dao.getCurrencySymbol(apInvoice.getCurrency());
		apInvoice.setSymbol(symbol);
		ctx.getSession().put("apInvoice", apInvoice);
		ctx.getSession().put("param", param);
	
		Struts2Util.getRequest().setAttribute("paramEL", param);
		
		int maxLineNo = 0;
	    if(param.get("lineNo") == null){
			maxLineNo = 1;
		}else{
			String[] lineNo = (String[]) param.get("lineNo");
//			maxLineNo = Integer.parseInt(lineNo[lineNo.length-1] )+1;
			maxLineNo = lineNo.length +1;
		}
		apInvoiceLine.setLineNo(maxLineNo);
		return "add_invoiceline";
	}

	/**
	 * 修改汇率
	 * @return
	 */public String modify(){
	       return "modify_invoice_curr";
		}
	 
	 
	 /**
		 * @function 修改
		 * @author   陈文擎
		 * @
		 */
		
		public String edit_invoiceline()
		{
			ActionContext ctx =ActionContext.getContext();
			String symbol=currency_Dao.getCurrencySymbol(apInvoice.getCurrency());
			apInvoice.setSymbol(symbol);
			ctx.getSession().put("apInvoice", apInvoice);
			ctx.getSession().put("param", param);
			
			Struts2Util.getRequest().setAttribute("paramEL", param);
			
			String LineNo =Struts2Util.getRequest().getParameter("LineNo").trim();
			if(0==param.size())
			return "edit_invoiceline";
			
			String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
			String[] catalogNo = param.get("catalogNo") == null ? null : (String[])param.get("catalogNo");
			String[] names = param.get("name") == null ? null : (String[])param.get("name");
			String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
			String[] qtyUoms = param.get("qtyUom") == null ? null : (String[])param.get("qtyUom");
			String[] unitPrices = param.get("unitPrice") == null ? null : (String[])param.get("unitPrice");
			String[] amounts = param.get("amount") == null ? null : (String[])param.get("amount");
			String[] taxs = param.get("tax") == null ? null : (String[])param.get("tax");
			String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
	        String[] lineNo = param.get("lineNo") == null ? null : (String[])param.get("lineNo");
	        
	        int i=0;
	        for( i=0;i<lineNo.length;i++)
	        {
	        	if(LineNo.equals(lineNo[i]))
	        	{
	        		break;
	        	}
	        }        
	        apInvoiceLine.setAmount(Tools.String2Float(amounts[i]));
	        apInvoiceLine.setCatalogNo(catalogNo[i]);
	        apInvoiceLine.setItemNo(Tools.String2Integer(itemNos[i]));
	        apInvoiceLine.setName(names[i]);
	        apInvoiceLine.setQty(Tools.String2Integer(qtys[i]));
	        apInvoiceLine.setQtyUom(qtyUoms[i]);
	        apInvoiceLine.setUnitPrice(Tools.String2Float(unitPrices[i]));
	        apInvoiceLine.setTax(Tools.String2Float(taxs[i]));
	        apInvoiceLine.setSize(Tools.String2Float(sizes[i]));
	        apInvoiceLine.setLineNo(Tools.String2Integer(lineNo[i]));     
			return "edit_invoiceline";
		}


		/**
		 * 
		 * @return
		 */
			 public String afteredit_invoiceline() {
					
					ActionContext ctx =ActionContext.getContext();		
					String dispatch = Struts2Util.getRequest().getParameter("dispatch").trim();
					if(null!=ctx.getSession().get("apInvoice"))
					{
						apInvoice=(ApInvoice)ctx.getSession().get("apInvoice");
						param = (Map) ctx.getSession().get("param");			
//						ctx.getSession().remove("apInvoice");
					}
					String LineNo =""+apInvoiceLine.getLineNo();
					
					String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
					String[] catalogNo = param.get("catalogNo") == null ? null : (String[])param.get("catalogNo");
					String[] names = param.get("name") == null ? null : (String[])param.get("name");
					String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
					String[] qtyUoms = param.get("qtyUom") == null ? null : (String[])param.get("qtyUom");
					String[] unitPrices = param.get("unitPrice") == null ? null : (String[])param.get("unitPrice");
					String[] amounts = param.get("amount") == null ? null : (String[])param.get("amount");
					String[] taxs = param.get("tax") == null ? null : (String[])param.get("tax");
					String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
			        String[] lineNo = param.get("lineNo") == null ? null : (String[])param.get("lineNo");
			        String[]  creationDate = param.get("creationDate")== null ? null : (String[])param.get("creationDate");
					String[] creationBy = param.get("createdBy")== null ? null : (String[])param.get("createdBy");
//			        String[] modify_dates = param.get("modifyDate")== null ? null : (String[])param.get("modifyDate");
//					String[] modified_by = param.get("modifiedBy")== null ? null : (String[])param.get("modifiedBy");
//			        ctx.getSession().remove("param");
			        int i=0;
			        for( i=0;i<lineNo.length;i++)
			        {
			        	if(LineNo.equals(lineNo[i]))
			        	{
			        		break;
			        	}
			        }        
			        amounts[i]=""+apInvoiceLine.getAmount();
			        catalogNo[i]=apInvoiceLine.getCatalogNo();
			        itemNos[i]=""+apInvoiceLine.getItemNo();
			        names[i]=apInvoiceLine.getName();
			        qtys[i]=""+apInvoiceLine.getQty();
			        qtyUoms[i]=""+apInvoiceLine.getQtyUom();
			        unitPrices[i]=""+apInvoiceLine.getUnitPrice();
			        taxs[i]=""+apInvoiceLine.getTax();
			        sizes[i]=""+apInvoiceLine.getSize();
			        lineNo[i]=""+apInvoiceLine.getLineNo();
			        creationDate[i]=Tools.getCurrentTime("yyyy-MM-dd");
			        creationBy[i]=""+SessionUtil.getUserId();
			        
//			        modify_dates[i]=Tools.getCurrentTime("yyyy-MM-dd");
//			        modified_by[i]=""+SessionUtil.getUserId();
			        
Struts2Util.getRequest().setAttribute("paramEL", param);			        
					return dispatch;
				}

			 /**
			  * 添加或修改invoiceline信息的时候，点击cancel触发的事件
			  * @return
			  */
		     public String cancel() {
					
					ActionContext ctx =ActionContext.getContext();
					String dispatch = Struts2Util.getRequest().getParameter("dispatch").trim();
					
					if(null!=ctx.getSession().get("apInvoice"))
					{
						apInvoice=(ApInvoice)ctx.getSession().get("apInvoice");
						param = (Map) ctx.getSession().get("param");			
//						ctx.getSession().remove("arInvoice");
//						ctx.getSession().remove("param");
					}
					Struts2Util.getRequest().setAttribute("paramEL", param);
					return dispatch;
		     }
		     
		     /**
		 	 * 新增一个发票点击save后，但是数据还没有保存到数据库
		 	 * @return
		 	 */
		      public String afteradd_invoiceline() {
		 		
		 		ActionContext ctx =ActionContext.getContext();		
		 		String dispatch = Struts2Util.getRequest().getParameter("dispatch").trim();
		 		
		 		if(null!=ctx.getSession().get("apInvoice"))
		 		{
		 			apInvoice=(ApInvoice)ctx.getSession().get("apInvoice");
//		 			ctx.getSession().remove("apInvoice");
		 		}
		 		
		 		
		 		if(null!=ctx.getSession().get("param"))
		 		{
		 			param=(Map)ctx.getSession().get("param");
		 			
		 			String[] invoiceLineIds = param.get("invoiceLineIds") == null ? null : (String[])param.get("invoiceLineIds");
		 			String[] invoiceIds = param.get("invoiceIds") == null ? null : (String[])param.get("invoiceIds");
		 			String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
		 			String[] catalogNo = param.get("catalogNo") == null ? null : (String[])param.get("catalogNo");
		 			String[] names = param.get("name") == null ? null : (String[])param.get("name");
		 			String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
		 			String[] qtyUoms = param.get("qtyUom") == null ? null : (String[])param.get("qtyUom");
		 			String[] unitPrices = param.get("unitPrice") == null ? null : (String[])param.get("unitPrice");
		 			String[] amounts = param.get("amount") == null ? null : (String[])param.get("amount");
		 			String[] taxs = param.get("tax") == null ? null : (String[])param.get("tax");
		 			String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
		 			String[] lineNo = param.get("lineNo") == null ? null : (String[])param.get("lineNo");
		 			String[] discounts = param.get("discount") == null ? null : (String[])param.get("discount");
		 			String[] creation_dates = param.get("creationDate")== null ? null : (String[])param.get("creationDate");
		 			String[] created_by = param.get("createdBy")== null ? null : (String[])param.get("createdBy");
		 			String[] modify_dates = param.get("modifyDate")== null ? null : (String[])param.get("modifyDate");
		 			String[] modified_by = param.get("modifiedBy")== null ? null : (String[])param.get("modifiedBy");
		 			
//		 			ctx.getSession().remove("param");
		 			
		 			
		 			String[] invoiceLineIds1 = Tools.add(invoiceLineIds, "0");
		 			String[] invoiceIds1 = Tools.add(invoiceIds, "0");
		 			String itemNo1[] = Tools.add(itemNos, this.apInvoiceLine.getItemNo()+"");
		 			String catalogNo1[] = Tools.add(catalogNo, this.apInvoiceLine.getCatalogNo());
		 			String names1[] = Tools.add(names, this.apInvoiceLine.getName()); //name 未填
		 			String qtys1[] = Tools.add(qtys, this.apInvoiceLine.getQty()+"");
		 			String qtyUoms1[] = Tools.add(qtyUoms, this.apInvoiceLine.getQtyUom());
		 			String unitPrices1[] = Tools.add(unitPrices, this.apInvoiceLine.getUnitPrice()+"");
		 			String amounts1[] = Tools.add(amounts, this.apInvoiceLine.getAmount()+"");
		 			String taxs1[] = Tools.add(taxs,this.apInvoiceLine.getTax()+"");
		 			String[] sizes1 = Tools.add(sizes, this.apInvoiceLine.getSize()+"");
		 			String[] lineNo1 = Tools.add(lineNo, this.apInvoiceLine.getLineNo()+"");
		 			String[] discounts1 = Tools.add(discounts, this.apInvoiceLine.getDiscount()+"");
		 			String[] creation_dates1 = Tools.add(creation_dates,Tools.getCurrentTime("yyyy-MM-dd"));
		 			String[] created_by1 = Tools.add(created_by, SessionUtil.getUserId()+"");
		 			String[] modify_dates1 = Tools.add(modify_dates, Tools.getCurrentTime("yyyy-MM-dd")+"");
		 			String[] modified_by1 = Tools.add(modified_by, SessionUtil.getUserId()+"");
		 			
		 			
		 			param.put("invoiceLineIds",invoiceLineIds1);
		 			param.put("invoiceIds",invoiceIds1);
		 			param.put("itemNo", itemNo1);
		 			param.put("catalogNo", catalogNo1);
		 			param.put("name", names1);
		 			param.put("qty", qtys1);
		 			param.put("qtyUom", qtyUoms1);
		 			param.put("amount", amounts1);
		 			param.put("tax", taxs1);
		 			param.put("unitPrice", unitPrices1);
		 			param.put("size", sizes1);
		 			param.put("lineNo", lineNo1);
		 			param.put("discount", discounts1);
		 			param.put("creationDate", creation_dates1);
		 			param.put("createdBy", created_by1);
		 			param.put("modifyDate", modify_dates1);
		 			param.put("modifiedBy", modified_by1);
		 			
//		 			ctx.getSession().put("param", param);
		 		}else{
		 			String[] invoiceLineIds = new String[1];
		 			invoiceLineIds[0]="0";
		 			String[] invoiceIds = new String[1];
		 			invoiceIds[0]="0";
		 			String itemNo[] = new String[1];
		 			itemNo[0] = this.apInvoiceLine.getItemNo()+"";
		 			String catalogNo[] = new String[1];
		 			catalogNo[0] = apInvoiceLine.getCatalogNo();
		 			String names[] = new String[1];
		 			names[0]= apInvoiceLine.getName();  //还未给值
		 			String qtys[] = new String[1];
		 			qtys[0] = apInvoiceLine.getQty()+"";
		 			String qtyUoms[] = new String[1];
		 			qtyUoms[0] = apInvoiceLine.getQtyUom();
		 			String unitPrices[] = new String[1];
		 			unitPrices[0] = this.apInvoiceLine.getUnitPrice()+"";
		 			String amounts[] = new String[1];
		 			amounts[0] = this.apInvoiceLine.getAmount()+"";
		 			String taxs[] = new String[1];
		 			taxs[0] = this.apInvoiceLine.getTax()+"";
		 			String[] sizes = new String[1];
		 			sizes[0] = this.apInvoiceLine.getSize()+"";
		 			String[] lineNo = new String[1];
		 			lineNo[0] = this.apInvoiceLine.getLineNo()+"";
		 			String[] discount = new String[1];
		 			discount[0] = this.apInvoiceLine.getDiscount()+"";
		 			String[] creation_dates = Tools.add(null, Tools.getCurrentTime("yyyy-MM-dd")+"");
		 			String[] created_by = Tools.add(null, SessionUtil.getUserId()+"");
		 			String[] modify_dates = Tools.add(null, Tools.getCurrentTime("yyyy-MM-dd")+"");
		 			String[] modified_by = Tools.add(null, SessionUtil.getUserId()+"");
		 			
		 			param.put("invoiceLineIds",invoiceLineIds);
		 			param.put("invoiceIds",invoiceIds);
		 			param.put("itemNo", itemNo);
		 			param.put("catalogNo", catalogNo);
		 			param.put("name", names);
		 			param.put("qty", qtys);
		 			param.put("qtyUom", qtyUoms);
		 			param.put("amount", amounts);
		 			param.put("tax", taxs);
		 			param.put("unitPrice", unitPrices);
		 			param.put("size", sizes);
		 			param.put("lineNo", lineNo);
		 			param.put("discount", discount);
		 			param.put("creationDate", creation_dates);
		 			param.put("createdBy", created_by);
		 			param.put("modifyDate", modify_dates);
		 			param.put("modifiedBy", modified_by);
//		 			ctx.getSession().put("param", param);
		 		}
		 		
		 		Struts2Util.getRequest().setAttribute("paramEL", param);
		 		return dispatch;
		 	}
	
		  	/**
		  	 * 保存修改invoiceLine后的发票
		  	 */
		  	public String saveEdit() {
		  		ActionContext ctx =ActionContext.getContext();
		  		try
		  		{   
		  						
		  			ApInvoice oldApInvoice=apInvoiceDao.getById(apInvoice.getInvoiceId());
		  			apInvoice.setModifiedBy(SessionUtil.getUserId());
		  			apInvoice.setModifyDate(new Date());			
		  			//如果原来状态既非  new  也非  inprocess 则不能 修改内容，只能修改状态
		  			if(  (!Constant.STATUS_INPROCESS.equals( oldApInvoice.getStatus()))&&(!Constant.STATUS_NEW.equals( oldApInvoice.getStatus()))  )
		  			{      
		  				   String status=oldApInvoice.getStatus(); //以手动更改为Invalid 手动改为Void	               
		  				 
		  				   if(Constant.STATUS_VOID.equals(status)||Constant.STATUS_INVALID.equals(status))
		  				   {
		  					   oldApInvoice.setStatus(apInvoice.getStatus());
		  					   status=apInvoice.getStatus();
		  					   if(Constant.STATUS_INPROCESS.equals(status)||Constant.STATUS_NEW.equals(status))
		  					   {
		  						   apInvoiceDao.getSession().clear();
		  						   apInvoiceService.edit(oldApInvoice);
		  					   }
		  				   }
		  				
		  				   ctx.getSession().remove("apInvoice");
		  				   ctx.getSession().remove("param");				
		  				   return input();
		  			}
		  			//
		  			
		  			
		  			boolean flag = this.apInvoiceDao.isAllocation(apInvoice.getInvoiceId());//没对过账，对过账的不能修改
		  			boolean changeStatus=false;
		  			
		  			String status=apInvoice.getStatus(); //手动更改为Invalid 手动改为Void			
		  			if(Constant.STATUS_VOID.equals(status)||Constant.STATUS_INVALID.equals(status)&&(!flag))
		  			{
		  				oldApInvoice.setStatus(status);			
		  				changeStatus=true;	 
		  			    
		  				if(Constant.STATUS_INPROCESS.equals( oldApInvoice.getStatus()))
		  				{
		  					  this.changeOderItem(oldApInvoice);
		  					  apInvoiceDao.getSession().clear();
		  					  apInvoiceService.edit(oldApInvoice);
		  					  ctx.getSession().remove("apInvoice");
		  					  ctx.getSession().remove("param");
		  					  return input();
		  					 
		  				}
		  				 
		  		   }
		  			
		  			
		  			
		  			
		  			if((!oldApInvoice.getCurrency().equals(apInvoice.getCurrency()))&&(!flag)&&(!changeStatus))//没对过账 Currency更改 没手动更改为Invalid 、Void	
		  			{				 
		  				 if(Constant.STATUS_INPROCESS.equals( oldApInvoice.getStatus()))  //原本就是inprocess
		  				 {
		  					  apInvoiceService.copyNewInvoice(apInvoice);
		  					  apInvoice.setCurrency(oldApInvoice.getCurrency());
		  					  apInvoice.setStatus(Constant.STATUS_VOID);
		  					  apInvoiceDao.getSession().clear();
		  					  apInvoiceService.edit(apInvoice);
		  					  ctx.getSession().remove("apInvoice");
		  					  ctx.getSession().remove("param");
		  					  return input();
		  				 }	
		  				 // 原来状态是new的不需要
		  			 }		
		  		    
		  			
		  			
		  			apInvoiceDao.getSession().clear();
		  		    apInvoiceService.edit(apInvoice);
		  		    
		  		     if(!Constant.STATUS_NEW.equals( oldApInvoice.getStatus()) )
		  			 {		    	
		  					ctx.getSession().remove("apInvoice");
		  					ctx.getSession().remove("param");				
		  					return input();
		  			 }
		  		    
		  		   
		  		    	    
		  		    int InvoiceId=apInvoice.getInvoiceId();
		  		    if(0!=param.size())
		  		    {
		  		    	String[] invoiceLineIds = param.get("invoiceLineIds") == null ? null : (String[])param.get("invoiceLineIds");
		  				String[] invoiceIds = param.get("invoiceIds") == null ? null : (String[])param.get("invoiceIds");
		  		    	String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
		  				String[] catalogNo = param.get("catalogNo") == null ? null : (String[])param.get("catalogNo");
		  				String[] names = param.get("name") == null ? null : (String[])param.get("name");
		  				String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
		  				String[] qtyUoms = param.get("qtyUom") == null ? null : (String[])param.get("qtyUom");
		  				String[] unitPrices = param.get("unitPrice") == null ? null : (String[])param.get("unitPrice");
		  				String[] amounts = param.get("amount") == null ? null : (String[])param.get("amount");
		  				String[] taxs = param.get("tax") == null ? null : (String[])param.get("tax");
		  				String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
		  				String[] lineNo = param.get("lineNo") == null ? null : (String[])param.get("lineNo");
		  				String[] discounts = param.get("discount") == null ? null : (String[])param.get("discount");
		  				String[] creation_dates = param.get("creationDate")== null ? null : (String[])param.get("creationDate");
		  				String[] created_by = param.get("createdBy")== null ? null : (String[])param.get("createdBy");
		  				String[] modify_dates = param.get("modifyDate")== null ? null : (String[])param.get("modifyDate");
		  				String[] modified_by = param.get("modifiedBy")== null ? null : (String[])param.get("modifiedBy");
		  				
		  				
		  				/**
		  				 * 对从invoice 状态从new 变为 inprocess的 invoice line的审核
		  				 * 对invoice line 进行check 开始
		  				 * 
		  				 */
		  				if(Constant.STATUS_INPROCESS.equals( apInvoice.getStatus())&&(null!=itemNos))
		  				{
		  					for(int i=0;i<itemNos.length;i++)
		  					{
		  	 					int itemNo=0;
		  						int quantity=0;
		  						double size=0;
		  						
		  						itemNo=Tools.String2Integer(itemNos[i]);
		  						quantity=Tools.String2Integer(qtys[i]);
		  						size=Tools.String2Float(sizes[i]);
//		  						
//		   						OrderItemOverSizeDTO = this.apInvoiceService.checkOderItem( apInvoice.getInvoiceId(), 
//		   					           apInvoice.getOrderNo(),itemNo,quantity, size,false );
//		   					   
//		   						if(null!=OrderItemOverSizeDTO)
//		   						{
//		   							apInvoiceService.edit(oldApInvoice);// 将 主表的数据 退回 原值
//		   							return "edit_invoice";
//		   						}						
		  					}//for 循环结束					
		  				}
		  				/**
		  				 * 对invoice line 进行check  结束
		  				 */
		  				
		  				 
		  				
		  				
		  				//////////////在状态为New时。InvoiceLine 执行删除////////////////
//		  				if(Constant.STATUS_NEW.equals(apInvoice.getStatus()))
//		  				{
		  					String InvoiceLineIds="0";
		  					String invoiceId=""+InvoiceId;
		  					for(int i=0;i<invoiceLineIds.length;i++)
		  					{
		  						InvoiceLineIds=InvoiceLineIds+","+invoiceLineIds[i];						 
		  					}
		  					try
		  					{
		  						apInvoiceLineDao.DeleteBatch(invoiceId,InvoiceLineIds);
		  					}
		  					catch(Exception e)
		  					{
		  						e.printStackTrace();
		  					}
		  					
//		  				}
		  				
		  				////////////////////////////////
		  				
		  				
		  				ApInvoiceLine insertLine=null;
		  				for(int i=0;i<itemNos.length;i++)
		  				{   
		  					insertLine=apInvoiceLineDao.queryInvoiceLine(Tools.String2Integer(invoiceLineIds[i]).intValue());
		  					
		  					if(null==insertLine)
		  					{
		  						insertLine=new ApInvoiceLine();
		  					}
		  					else
		  					{
		  						insertLine.setInvoiceLineId(Tools.String2Integer(invoiceLineIds[i]));
		  					}										
		  					insertLine.setAmount(Tools.String2Float(amounts[i]));
		  					insertLine.setCatalogNo(catalogNo[i]);
		  					 
		  					insertLine.setDiscount(Tools.String2Float(discounts[i]));;
		  					insertLine.setInvoiceId(InvoiceId);
		  					insertLine.setItemNo(Tools.String2Integer(itemNos[i]));
		  					int linno=Tools.String2Integer(lineNo[i]);
//		  					insertLine.setLineNo(linno);
		  					insertLine.setLineNo(i+1);
		  					
		  					insertLine.setModifiedBy(SessionUtil.getUserId());
		  					insertLine.setModifyDate(new Date());
		  					
		  					insertLine.setCreatedBy(Tools.String2Integer(created_by[i]));
		  					insertLine.setCreationDate(Tools.getFormatDate(creation_dates[i]));
		  					
		  					insertLine.setName(names[i]);
		  					insertLine.setOrderNo(apInvoice.getOrderNo());
		  					insertLine.setQty(Tools.String2Integer(qtys[i]));
		  					insertLine.setQtyUom(qtyUoms[i]);
		  					insertLine.setSize(Tools.String2Float(sizes[i]));
		  					insertLine.setSizeUom("");
		  					insertLine.setTax(Tools.String2Float(taxs[i]));
		  					insertLine.setUnitPrice(Tools.String2Float(unitPrices[i]));
		  					insertLine.setStatus(apInvoice.getStatus());
		  					
		  					apInvoiceLineDao.save(insertLine);
		  				}				
		  		    }
		  		    
		  		}catch(Exception e)
		  		{
		  			e.printStackTrace();
		  		}
		  		
		  		 
		  		ctx.getSession().remove("apInvoice");
		  		ctx.getSession().remove("param");
		  		
		  		return input();
		  	}

	
		  	
		  	/**
		  	 *  退票
		  	 * 
		  	 * 
		  	 * 
		  	 */

		  	public void  changeOderItem(ApInvoice apInvoice)
		  	{
		  		if(0==param.size())
		  		return;
		  		
		  		String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");	 
		  		String[] qtys = param.get("qty") == null ? null : (String[])param.get("qty");
		  		String[] sizes = param.get("size") == null ? null : (String[])param.get("size");
		  		if(null!=itemNos)
		  		{
		  			for(int i=0;i<itemNos.length;i++)
		  			{
		  					int itemNo=0;
		  				int quantity=0;
		  				double size=0;
		  				
		  				itemNo=Tools.String2Integer(itemNos[i]);
		  				quantity=Tools.String2Integer(qtys[i]);
		  				size=Tools.String2Float(sizes[i]);
		  				
		  				this.apInvoiceService.checkOderItem( apInvoice.getInvoiceId(), 
		  				           apInvoice.getOrderNo(),itemNo,quantity, size,true );
		  				   
		  					 				
		  			}//for 循环结束					
		  		}
		  		/**
		  		 * 对invoice line 进行check  结束
		  		 */
		  	}
		  	
		  	
		  	/**
			 * 获取saler的名称
			 * @return
			 */
			public String getSalerName(){
				int id = 0;
				String msg = "";
				try{
				id = Integer.parseInt(Struts2Util.getParameter("orderNo"));
				Map m = this.apInvoiceDao.getInfoByOrder(id);
				msg = "{ state:'success', currency:'"+m.get("currency")+"', name : '"+m.get("name")+"',saleId:'"+m.get("saleId")+"',amt:"+m.get("amt")+",currency:'"+m.get("currency")+"',symbol:'"+m.get("symbol")+"' }";
				}catch(Exception e){
					e.printStackTrace();
				    msg = "{ state:'error',msg:'"+e.getMessage()+"' }";
				}
				Struts2Util.renderJson(msg);
				return null;
			}
			

			public String doDelete() {
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest req = Struts2Util.getRequest();
				String[] invoiceIds = req.getParameter("invoiceIds").split(",");
		//System.out.println("SessionUtil.getUserId():"+SessionUtil.getUserId());
		//System.out.println("statusUpReason:"+req.getParameter("statusUpReason"));
				
				List<ApInvoice> list = new ArrayList();
				for(int i=0; i<invoiceIds.length; i++){
					ApInvoice entity = (ApInvoice) apInvoiceDao.getSession().get(ApInvoice.class, new Integer(invoiceIds[i]));//apInvoiceDao.get(new Integer(invoiceIds[i]));
					entity.setOldStatus(entity.getStatus()); //原始状态
					entity.setStatus(Constant.STATUS_VOID);//修改后的状态
					entity.setModifiedBy(SessionUtil.getUserId());
					entity.setModifyDate(new Date());
					entity.setStatusUpdReason(req.getParameter("statusUpReason"));
					list.add(entity);
				}

				boolean bres = apInvoiceService.delete(list);
				Struts2Util.getRequest().setAttribute("operate_result",bres);
				
				return "delete_invoice_page";
//				return input();
			}
			
			/**
			 * 检查该发票是否对过账，如果已经对过账，则不能修改currency
			 * @return
			 */
			public String checkInvoiceIsAllocatied(){
				int invoiceId = 0;
				String msg = "";
				try{
					invoiceId = Integer.parseInt(Struts2Util.getParameter("invoiceId"));
					
				boolean flag = this.apInvoiceDao.isAllocation(invoiceId);
			    msg = "{ state:'success',flag : "+flag +"}";
				}catch(Exception e){
					e.printStackTrace();
					 msg = "{ state:'error',msg:'"+e.getMessage()+"' }";
				}
				Struts2Util.renderJson(msg);
				return null;
			}
			
}
