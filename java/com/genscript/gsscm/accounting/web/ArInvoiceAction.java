package com.genscript.gsscm.accounting.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArInvoiceAllocationDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;
import com.genscript.gsscm.accounting.dao.TransactionCurrencyDao;
import com.genscript.gsscm.accounting.dto.ArInvoiceDTO;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.service.ArInvoiceService;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.order.dto.OrderItemOverSizeDTO;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.order.service.OrderService;
import com.lowagie.text.DocumentException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Results( {
	   
		@Result(name = "success", location = "accounting/receive/invoice_list.jsp"),
		@Result(name = "enter", location = "accounting/receive/invoice_list.jsp"),
		@Result(name = "create_invoice", location = "accounting/receive/create_invoice.jsp"),
		@Result(name = "edit_invoice", location = "accounting/receive/edit_invoice.jsp"),
		@Result(name = "edit_invoiceline",location = "accounting/receive/edit_invoiceline.jsp"),
		@Result(name = "add_invoiceline",location = "accounting/receive/add_invoiceline.jsp"),
		@Result(name = "delete_invoice_page", location = "accounting/receive/delete_invoice.jsp"),
		@Result(name = "view_overdue_invoice", location = "accounting/receive/view_overdue_invoice.jsp"),
		@Result(name = "notify_customer", location = "accounting/receive/notify_customer.jsp"),
		@Result(name = "print_invoice", location = "accounting/receive/print_invoice.jsp"),
		@Result(name = "modify_invoice_curr", location = "accounting/receive/modify_invoice_curr.jsp"),
		@Result(name = "view_allocation_history", location = "accounting/receive/view_allocation_history.jsp"),
		@Result(name = "print_error_page", location = "accounting/receive/print_error_page.jsp")
})
public class ArInvoiceAction extends ActionSupport {

	private Page<ArInvoiceView> arInvoicePage;
	
	private Page<ArInvoice> overDuePage;

	public ArInvoice arInvoice = new ArInvoice();
	
	public ArInvoiceDTO arInvoiceDto=new ArInvoiceDTO();

    public ArInvoiceLine arInvoiceLine=new ArInvoiceLine();
    
    
    public OrderItemOverSizeDTO OrderItemOverSizeDTO;
	
    List<ArTransactionAllocation> arInvoiceAllocationList;

	public String invoiceNo;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Page<ArInvoiceView> getArInvoicePage() {
		return arInvoicePage;
	}

	public void setArInvoicePage(Page<ArInvoiceView> arInvoicePage) {
		this.arInvoicePage = arInvoicePage;
	}

	
	public Page<ArInvoice> getOverDuePage() {
		return overDuePage;
	}

	@Autowired
	private ArInvoiceService arInvoiceService;
	@Autowired
	ArInvoiceDao arInvoiceDao;
	@Autowired
	ArInvoiceLineDao arInvoiceLineDao;
	@Autowired
	ArInvoiceAllocationDao arInvoiceAllocationDao;
	@Autowired
	OrderService orderService;
	@Autowired
	TransactionCurrencyDao currencyDao; 
	@Autowired
	CurrencyDao currency_Dao;
	
	

	public String delete() throws Exception {
		Struts2Util.getRequest().setAttribute("invoiceIds", arInvoiceDto.getInvoiceIds());
		return "delete_invoice_page";
	}


	/**
	 * 进入invoice_list页面
	 */
	public String input()  {
		PagerUtil<ArInvoiceView> pagerUtil = new PagerUtil<ArInvoiceView>();
		arInvoicePage = pagerUtil.getRequestPage();
		arInvoicePage.setPageSize(10);
//		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ArInvoiceView> invoiceList = null;
		long total = 0;
		try {
			invoiceList = arInvoiceDao.list(arInvoicePage,null);
			total = this.arInvoiceDao.getTotalPage(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		arInvoicePage.setTotalCount(total);
		arInvoicePage.setResult(invoiceList);
//		PageDTO pagerInfo = new Tools().formPage(arInvoicePage);
		Struts2Util.getRequest().setAttribute("pagerInfo", arInvoicePage);
//		Struts2Util.getRequest().setAttribute("params", m);
		return "enter";
	}

	/**
	 * 分页查寻
	 */
	public String list() {
		PagerUtil<ArInvoiceView> pagerUtil = new PagerUtil<ArInvoiceView>();
		arInvoicePage = pagerUtil.getRequestPage();
		arInvoicePage.setPageSize(10);
		Map m = Tools.buildMap(Struts2Util.getRequest());
		List<ArInvoiceView> invoiceList = null;
		long total = 0;
		try {
			invoiceList = arInvoiceDao.list(arInvoicePage,m);
			total = this.arInvoiceDao.getTotalPage(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		arInvoicePage.setTotalCount(total);
		arInvoicePage.setResult(invoiceList);
//		PageDTO pagerInfo = new Tools().formPage(arInvoicePage);
		Struts2Util.getRequest().setAttribute("pagerInfo", arInvoicePage);
		Struts2Util.getRequest().setAttribute("params", m);
		return "success";

	}




	public void setOverDuePage(Page<ArInvoice> overDuePage) {
		this.overDuePage = overDuePage;
	}

	/**
	 * @author 陈文擎
	 * @function 发票的新增
	 * 
	 */
	public String save(){
		// TODO Auto-generated method stub
		//return "save_success";
		try
		{   
			//设置发票创建人
			arInvoice.setCreatedBy(SessionUtil.getUserId()); 
			//设置发票创建时间
			arInvoice.setCreationDate(new Date());
			//设置发票修改人
			arInvoice.setModifiedBy(SessionUtil.getUserId());
			//设置发票修改时间
			arInvoice.setModifyDate(new Date());
			
			//获取与发票关联的Order对应的Company 主键 
			int companyId = this.arInvoiceDao.getCompanyId(arInvoice.getOrderNo());
			arInvoice.setCompanyId(companyId);
			
		    //将新增发票的主表数据并保存，并返回主键
			int InvoiceId = arInvoiceService.add(arInvoice);
			
//		    int InvoiceId=arInvoiceService.getInvoiceId(arInvoice);
			
			/*
			 *创建发票的从表数据，param参数包含发票的所有从表数据 			 *
			 *其中param 是从页面上传入的，由ognl截获并传入到后台
			 *param中的数据都是以String数组的形式导入
			 *			 * 
			 */
			
		    if(0!=param.size())
		    {
		    	//从order表带过来的ItemNo
		    	String[] itemNos = param.get("itemNo") == null ? null : (String[])param.get("itemNo");
		    	//物品的编号
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
				ArInvoiceLine insertLine=null;
				
				for(int i=0;i<itemNos.length;i++)
				{
					insertLine=new ArInvoiceLine();
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
					insertLine.setOrderNo(arInvoice.getOrderNo());
					insertLine.setQty(Tools.String2Integer(qtys[i]));
					insertLine.setQtyUom(qtyUoms[i]);
					insertLine.setSize(Tools.String2Float(sizes[i]));
					insertLine.setSizeUom("");
					insertLine.setTax(Tools.String2Float(taxs[i]));
					insertLine.setUnitPrice(Tools.String2Float(unitPrices[i]));
					//新增的数据状态必定是NEW的
					insertLine.setStatus(Constant.STATUS_NEW);
					//将发票的从表数据保存到数据库
					arInvoiceLineDao.save(insertLine);
				}				
		    }
		    
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		ActionContext ctx =ActionContext.getContext();
		// 将session 中的不需要的属性，从session中删除
		ctx.getSession().remove("arInvoice");
		ctx.getSession().remove("param");
		//返回到首页
		return input();
	} 
	
	/**
	 * 创建发票
	 * 
	 * @return
	 */
	
	public String add() {
		//进入新增页面时，设置状态为New ，
        //在修改InvoiceLine时根据 OldStatus 来判断InvoiceLine的操作
	    //只有 OldStatus 的状态为New时可以修改，否则只能查看
		arInvoice.setOldStatus(Constant.STATUS_NEW);
		return "create_invoice";
	}

	/**
	 *  进入到 修改发票 的页面前预先做的操作，
	 *  将数据提取到相应的实体类中 ，将实体类通过valuestack 暴露给 修改页面 
	 *  并通过EL表达式和 标签 来显示数据 
	 */
	public String edit() {
		
		String InvoiceNo =Struts2Util.getRequest().getParameter("invoiceNo");
		//获取页面请求中传过来的数据
		int invoiceId = Tools.String2Integer(Struts2Util.getParameter("invoiceId") );
		
		List<ArInvoiceLine> arInvoiceLines=null;
		try{
		//根据主键 获取发票的实体
		arInvoice = this.arInvoiceDao.get(invoiceId);
		//System.out.println(arInvoice.getStatus());
		//设置OldStatus 的状态，来判断InvoiceLine的操作
		arInvoice.setOldStatus(arInvoice.getStatus());
		//通过汇率类型来，获取汇率的符号
		String symbol=currency_Dao.getCurrencySymbol(arInvoice.getCurrency());
		arInvoice.setSymbol(symbol);
	    //根据主表的主键关联出从表查询
		arInvoiceLines=arInvoiceLineDao.queryInvoiceByInvoiceId(arInvoice.getInvoiceId());
		//System.out.println("@@@@@@@:"+arInvoiceLines.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//如果查询出的从表数据不存在，则直接返回到修改页面
		if((null==arInvoiceLines)||(0==arInvoiceLines.size()))
	    return "edit_invoice";
		
		//获取从表的总记录数
		int length=arInvoiceLines.size();
		
		//将数据从查询的结果集中放到 String 数组中去
	    //再将String数组放到param中，然后再页面上显示
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
        ArInvoiceLine InvoiceLine=null;
        
		for(int i=0;i<arInvoiceLines.size();i++ )
		{
			InvoiceLine=arInvoiceLines.get(i);
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
		//返回到编辑页面
		return "edit_invoice";
	}
	
	/**
	 * @function   检查invoice line 的 OderItem 是否超标
	 * @param      invoiceId
	 * @param      orderNo
	 * @param      itemNo
	 * @param      quantity
	 * @param      size
	 * @return
	 */
	public OrderItemOverSizeDTO checkOderItem(int invoiceId, int orderNo, int itemNo,
			int quantity, double size,boolean isDelete )
	{
		Map data=arInvoiceDao.getItemNum( orderNo, itemNo);
		
		int oldQuantity=(Integer)data.get("qty");
		double oldSize=(Float)data.get("size");
		OrderItemOverSizeDTO orderItemOverSizeDTO=null;
	   try{
		   orderItemOverSizeDTO=orderService.uptOrderItemStatus(orderNo, itemNo, quantity, size, oldQuantity, oldSize,isDelete );
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderItemOverSizeDTO;
	}
	
	/**
	 * 保存修改后的发票
	 */
	public String saveEdit() {
		ActionContext ctx =ActionContext.getContext();
		try
		{   
						
			ArInvoice oldarInvoice=arInvoiceDao.getById(arInvoice.getInvoiceId());
			arInvoice.setModifiedBy(SessionUtil.getUserId());
			arInvoice.setModifyDate(new Date());			
			//如果原来状态既非  new  也非  inprocess 则不能 修改内容，只能修改状态
			if(  (!Constant.STATUS_INPROCESS.equals( oldarInvoice.getStatus()))&&(!Constant.STATUS_NEW.equals( oldarInvoice.getStatus()))  )
			{      
				   String status=oldarInvoice.getStatus(); //获取原来数据的状态	               
				      //以手动更改为Invalid 手动改为Void
				   if(Constant.STATUS_VOID.equals(status)||Constant.STATUS_INVALID.equals(status)) //如果原来的状态是Void 和 Invalid 的数据可以
				   {
					   oldarInvoice.setStatus(arInvoice.getStatus());
					   status=arInvoice.getStatus();
					 //如果要变成 New的状态的，可以直接变更
					   if(Constant.STATUS_NEW.equals(status))
					   {
						   arInvoiceDao.getSession().clear();
						   arInvoiceService.edit(oldarInvoice);
					   }//如果要变更为In Progress 修要对数据进行 Check 
					   else if(Constant.STATUS_INPROCESS.equals(status))
					   {   //如果数据不能通过check 的返回 编辑页面
						   if(this.checkOver())
						   return "edit_invoice";
						   arInvoiceDao.getSession().clear();
						   //如果通过check的 则修改它的状态
						   arInvoiceService.edit(oldarInvoice);
					   }
				   }
				
				   ctx.getSession().remove("arInvoice");
				   ctx.getSession().remove("param");				
				   return input();
			}
			//
			
			
			boolean flag = this.arInvoiceDao.isAllocation(arInvoice.getInvoiceId());//没对过账才能修改，对过账的不能修改
			boolean changeStatus=false;
			
			String status=arInvoice.getStatus(); //手动更改为Invalid 手动改为Void			
			if(Constant.STATUS_VOID.equals(status)||Constant.STATUS_INVALID.equals(status)&&(!flag))
			{
				oldarInvoice.setStatus(status);			
				changeStatus=true;	// 表示状态已经变更 
			    
				if(Constant.STATUS_INPROCESS.equals( oldarInvoice.getStatus()))
				{
					  this.changeOderItem(oldarInvoice);
					  arInvoiceDao.getSession().clear();
					  arInvoiceService.edit(oldarInvoice);
					  ctx.getSession().remove("arInvoice");
					  ctx.getSession().remove("param");
					  return input();
					 
				}
				 
		   }
			
			/*
			 * 如果状态变更过后,就不进行汇率的变更
			 * 
			 */			
			
			if((!oldarInvoice.getCurrency().equals(arInvoice.getCurrency()))&&(!flag)&&(!changeStatus))//没对过账 Currency更改 没手动更改为Invalid 、Void	
//			if((!oldarInvoice.getCurrency().equals(arInvoice.getCurrency()))&&(!flag))//没对过账 Currency更改 没手动更改为Invalid 、Void	
			{				 
				 if(Constant.STATUS_INPROCESS.equals( oldarInvoice.getStatus()))  //原本就是inprocess
				 {
					  arInvoiceService.copyNewInvoice(arInvoice);
					  arInvoice.setCurrency(oldarInvoice.getCurrency());
					  arInvoice.setStatus(Constant.STATUS_VOID);
					  arInvoiceDao.getSession().clear();
					  arInvoiceService.edit(arInvoice);
					  ctx.getSession().remove("arInvoice");
					  ctx.getSession().remove("param");
					  return input();
				 }	
				 // 原来状态是new的不需要
			 }		
		    
			
			if( !Constant.STATUS_INPROCESS.equals( oldarInvoice.getStatus()))
			{
				 arInvoiceDao.getSession().clear();
				 arInvoiceService.edit(arInvoice);
				 arInvoiceDao.getSession().clear();
				 ctx.getSession().remove("arInvoice");
				 ctx.getSession().remove("param");				
				 return input();
			}   
		    
		   
		    	    
		    int InvoiceId=arInvoice.getInvoiceId();
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
				if(Constant.STATUS_INPROCESS.equals( arInvoice.getStatus())&&(null!=itemNos))
				{
					for(int i=0;i<itemNos.length;i++)
					{
	 					int itemNo=0;
						int quantity=0;
						double size=0;
						
						itemNo=Tools.String2Integer(itemNos[i]);
						quantity=Tools.String2Integer(qtys[i]);
						size=Tools.String2Float(sizes[i]);
						
 						OrderItemOverSizeDTO = this.arInvoiceService.checkOderItem( arInvoice.getInvoiceId(), 
 					           arInvoice.getOrderNo(),itemNo,quantity, size,false );
 					   
 						if(null!=OrderItemOverSizeDTO)
 						{
 							//arInvoiceService.edit(oldarInvoice);// 将 主表的数据 退回 原值
 							arInvoice.setOldStatus(Constant.STATUS_NEW);
 							return "edit_invoice";
 						}						
					}//for 循环结束
					 arInvoiceDao.getSession().clear();
					 arInvoiceService.edit(arInvoice);
				}
				/**
				 * 对invoice line 进行check  结束
				 */
				
				 
				
				
				//////////////在状态为New时。InvoiceLine 执行删除////////////////
//				if(Constant.STATUS_NEW.equals(arInvoice.getStatus()))
//				{
					String InvoiceLineIds="0";
					String invoiceId=""+InvoiceId;
					for(int i=0;i<invoiceLineIds.length;i++)
					{
						InvoiceLineIds=InvoiceLineIds+","+invoiceLineIds[i];						 
					}
					try
					{
						arInvoiceLineDao.DeleteBatch(invoiceId,InvoiceLineIds);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
//				}
				
				////////////////////////////////
				
				
				ArInvoiceLine insertLine=null;
				for(int i=0;i<itemNos.length;i++)
				{   
					insertLine=arInvoiceLineDao.queryInvoiceLine(Tools.String2Integer(invoiceLineIds[i]).intValue());
					
					if(null==insertLine)
					{
						insertLine=new ArInvoiceLine();
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
//					insertLine.setLineNo(linno);
					insertLine.setLineNo(i+1);
					
					insertLine.setModifiedBy(Tools.String2Integer(modified_by[i]));
					insertLine.setModifyDate(Tools.getFormatDate(modify_dates[i]));
					
					insertLine.setCreatedBy(Tools.String2Integer(created_by[i]));
					insertLine.setCreationDate(Tools.getFormatDate(creation_dates[i]));
					
					insertLine.setName(names[i]);
					insertLine.setOrderNo(arInvoice.getOrderNo());
					insertLine.setQty(Tools.String2Integer(qtys[i]));
					insertLine.setQtyUom(qtyUoms[i]);
					insertLine.setSize(Tools.String2Float(sizes[i]));
					insertLine.setSizeUom("");
					insertLine.setTax(Tools.String2Float(taxs[i]));
					insertLine.setUnitPrice(Tools.String2Float(unitPrices[i]));
					insertLine.setStatus(arInvoice.getStatus());
					
					arInvoiceLineDao.save(insertLine);
				}				
		    }
		    
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 
		ctx.getSession().remove("arInvoice");
		ctx.getSession().remove("param");
		
		return input();
	}

	/**
	 * @function 修改
	 * @author   陈文擎
	 * @
	 */
	
	public Map param=new HashMap();
	public String edit_invoiceline()
	{
		ActionContext ctx =ActionContext.getContext();
		String symbol=currency_Dao.getCurrencySymbol(arInvoice.getCurrency());
		arInvoice.setSymbol(symbol);
		ctx.getSession().put("arInvoice", arInvoice);
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
        arInvoiceLine.setAmount(Tools.String2Float(amounts[i]));
        arInvoiceLine.setCatalogNo(catalogNo[i]);
        arInvoiceLine.setItemNo(Tools.String2Integer(itemNos[i]));
        arInvoiceLine.setName(names[i]);
        arInvoiceLine.setQty(Tools.String2Integer(qtys[i]));
        arInvoiceLine.setQtyUom(qtyUoms[i]);
        arInvoiceLine.setUnitPrice(Tools.String2Float(unitPrices[i]));
        arInvoiceLine.setTax(Tools.String2Float(taxs[i]));
        arInvoiceLine.setSize(Tools.String2Float(sizes[i]));
        arInvoiceLine.setLineNo(Tools.String2Integer(lineNo[i]));     
		return "edit_invoiceline";
	}

/**
 * 
 * @return
 */
	 public String afteredit_invoiceline() {
			
			ActionContext ctx =ActionContext.getContext();		
			String dispatch = Struts2Util.getRequest().getParameter("dispatch").trim();
			if(null!=ctx.getSession().get("arInvoice"))
			{
				arInvoice=(ArInvoice)ctx.getSession().get("arInvoice");
				param = (Map) ctx.getSession().get("param");			
//				ctx.getSession().remove("arInvoice");
			}
			String LineNo =""+arInvoiceLine.getLineNo();
			
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
	        String[] modify_dates = param.get("modifyDate")== null ? null : (String[])param.get("modifyDate");
			String[] modified_by = param.get("modifiedBy")== null ? null : (String[])param.get("modifiedBy");
//	        ctx.getSession().remove("param");
	        int i=0;
	        for( i=0;i<lineNo.length;i++)
	        {
	        	if(LineNo.equals(lineNo[i]))
	        	{
	        		break;
	        	}
	        }        
	        amounts[i]=""+arInvoiceLine.getAmount();
	        catalogNo[i]=arInvoiceLine.getCatalogNo();
	        itemNos[i]=""+arInvoiceLine.getItemNo();
	        names[i]=arInvoiceLine.getName();
	        qtys[i]=""+arInvoiceLine.getQty();
	        qtyUoms[i]=""+arInvoiceLine.getQtyUom();
	        unitPrices[i]=""+arInvoiceLine.getUnitPrice();
	        taxs[i]=""+arInvoiceLine.getTax();
	        sizes[i]=""+arInvoiceLine.getSize();
	        lineNo[i]=""+arInvoiceLine.getLineNo();
	        modify_dates[i]=Tools.getCurrentTime("yyyy-MM-dd");
	        modified_by[i]=""+SessionUtil.getUserId();
	        
	       Struts2Util.getRequest().setAttribute("paramEL", param);
			return dispatch;
		}

	/**
	 * @function 修改
	 * @author   陈文擎
	 * @
	 */
   
	public String add_invoiceline()
	{
		ActionContext ctx =ActionContext.getContext();
		String symbol=currency_Dao.getCurrencySymbol(arInvoice.getCurrency());
		arInvoice.setSymbol(symbol);
		ctx.getSession().put("arInvoice", arInvoice);
		ctx.getSession().put("param", param);
		
		Struts2Util.getRequest().setAttribute("paramEL", param);
		
		java.util.Set set= param.keySet();
		Object[] objs=set.toArray();
	
		int maxLineNo = 0;
	    if(param.get("lineNo") == null){
			maxLineNo = 1;
		}else{
			String[] lineNo = (String[]) param.get("lineNo");
			maxLineNo = Integer.parseInt(lineNo[lineNo.length-1] )+1;
		}
//		for(int  i=0;i<set.size();i++)
//		{
//			System.out.printlnln(objs[i].toString()+":"+((String[])param.get(objs[i]))[0]);
//		}
	    System.out.println("$$$$$$$$$$$:"+maxLineNo);
		arInvoiceLine.setLineNo(maxLineNo);
		
		return "add_invoiceline";
	}

	
	/**
	 * 新增一个发票点击save后，但是数据还没有保存到数据库
	 * @return
	 */
     public String afteradd_invoiceline() {
		
		ActionContext ctx =ActionContext.getContext();		
		String dispatch = Struts2Util.getRequest().getParameter("dispatch").trim();
		
		if(null!=ctx.getSession().get("arInvoice"))
		{
			arInvoice=(ArInvoice)ctx.getSession().get("arInvoice");
//			ctx.getSession().remove("arInvoice");
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
			
//			ctx.getSession().remove("param");
			
			
			String[] invoiceLineIds1 = Tools.add(invoiceLineIds, "0");
			String[] invoiceIds1 = Tools.add(invoiceIds, "0");
			String itemNo1[] = Tools.add(itemNos, this.arInvoiceLine.getItemNo()+"");
			String catalogNo1[] = Tools.add(catalogNo, this.arInvoiceLine.getCatalogNo());
			String names1[] = Tools.add(names, this.arInvoiceLine.getName()); //name 未填
			String qtys1[] = Tools.add(qtys, this.arInvoiceLine.getQty()+"");
			String qtyUoms1[] = Tools.add(qtyUoms, this.arInvoiceLine.getQtyUom());
			String unitPrices1[] = Tools.add(unitPrices, this.arInvoiceLine.getUnitPrice()+"");
			String amounts1[] = Tools.add(amounts, this.arInvoiceLine.getAmount()+"");
			String taxs1[] = Tools.add(taxs,this.arInvoiceLine.getTax()+"");
			String[] sizes1 = Tools.add(sizes, this.arInvoiceLine.getSize()+"");
			String[] lineNo1 = Tools.add(lineNo, this.arInvoiceLine.getLineNo()+"");
			String[] discounts1 = Tools.add(discounts, this.arInvoiceLine.getDiscount()+"");
			String[] creation_dates1 = Tools.add(creation_dates,Tools.getCurrentTime("yyyy-MM-dd"));
			String[] created_by1 = Tools.add(created_by, this.arInvoiceLine.getCreatedBy()+"");
			String[] modify_dates1 = Tools.add(modify_dates, Tools.getCurrentTime("yyyy-MM-dd")+"");
			String[] modified_by1 = Tools.add(modified_by, this.arInvoiceLine.getModifiedBy()+"");
			
			
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
			
//			ctx.getSession().put("param", param);
		}else{
			String[] invoiceLineIds = new String[1];
			invoiceLineIds[0]="0";
			String[] invoiceIds = new String[1];
			invoiceIds[0]="0";
			String itemNo[] = new String[1];
			itemNo[0] = this.arInvoiceLine.getItemNo()+"";
			String catalogNo[] = new String[1];
			catalogNo[0] = arInvoiceLine.getCatalogNo();
			String names[] = new String[1];
			names[0]= arInvoiceLine.getName();  //还未给值
			String qtys[] = new String[1];
			qtys[0] = arInvoiceLine.getQty()+"";
			String qtyUoms[] = new String[1];
			qtyUoms[0] = arInvoiceLine.getQtyUom();
			String unitPrices[] = new String[1];
			unitPrices[0] = this.arInvoiceLine.getUnitPrice()+"";
			String amounts[] = new String[1];
			amounts[0] = this.arInvoiceLine.getAmount()+"";
			String taxs[] = new String[1];
			taxs[0] = this.arInvoiceLine.getTax()+"";
			String[] sizes = new String[1];
			sizes[0] = this.arInvoiceLine.getSize()+"";
			String[] lineNo = new String[1];
			lineNo[0] = this.arInvoiceLine.getLineNo()+"";
			String[] discount = new String[1];
			discount[0] = this.arInvoiceLine.getDiscount()+"";
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
//			ctx.getSession().put("param", param);
		}
		
//		java.util.Set set= param.keySet();
//		Object[] objs=set.toArray();
//		for(int  i=0;i<set.size();i++)
//		{
//			System.out.printlnln(objs[i].toString()+":"+((String[])param.get(objs[i])).length);
//		}
		
		
		 Struts2Util.getRequest().setAttribute("paramEL", param);
		return dispatch;
	}
	
	
     public String cancel() {
			
			ActionContext ctx =ActionContext.getContext();
			String dispatch = Struts2Util.getRequest().getParameter("dispatch").trim();
			
			if(null!=ctx.getSession().get("arInvoice"))
			{
				arInvoice=(ArInvoice)ctx.getSession().get("arInvoice");
				param = (Map) ctx.getSession().get("param");
				String symbol=currency_Dao.getCurrencySymbol(arInvoice.getCurrency());
				arInvoice.setSymbol(symbol);
//				ctx.getSession().remove("arInvoice");
//				ctx.getSession().remove("param");
				 Struts2Util.getRequest().setAttribute("paramEL", param);
			}
			
			return dispatch;
     }
     
     
     
	/**
	 * 进入过期发票页面
	 * 
	 * @return
	 */
     public String viewOverDueInvoice() {
 		PagerUtil<ArInvoice> pagerUtil = new PagerUtil<ArInvoice>();
 		overDuePage = pagerUtil.getRequestPage();
 		overDuePage.setPageSize(10);
 		overDuePage = this.arInvoiceDao.queryOverDueInvoice(overDuePage);
// 		PageDTO pagerInfo = new Tools().formPage(overDuePage);
 		Struts2Util.getRequest().setAttribute("pagerInfo", overDuePage);
 		return "view_overdue_invoice";
 	}


	
	/**
	 * 判断invoiceNo是否存在
	 * @param invoiceNo 发票编号
	 * @return true表示存在该编号
	 */
	public String checkInvoiceNo(){
		boolean flag = this.arInvoiceDao.checkInvoiceNoExists(this.getInvoiceNo());
		Struts2Util.renderJson("{status:'"+flag+"'}");
		return null;
	}

/**
 * 修改汇率
 * @return
 */public String modify(){
       return "modify_invoice_curr";
	}
 
	
	/**
	 * 发邮件通知用户
	 * @return
	 */
	public String notifyCustomer(){
		return "notify_customer";
	}


	public ArInvoiceDTO getArInvoiceDto() {
		return arInvoiceDto;
	}


	public void setArInvoiceDto(ArInvoiceDTO arInvoiceDto) {
		this.arInvoiceDto = arInvoiceDto;
	}


	public ArInvoice getArInvoice() {
		return arInvoice;
	}


	public void setArInvoice(ArInvoice arInvoice) {
		this.arInvoice = arInvoice;
	}
	
	public String print(){
		return "print_invoice";
	}


	public Map getParam() {
		return param;
	}


	public void setParam(Map param) {
		this.param = param;
	}
	
	public String doDelete() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest req = Struts2Util.getRequest();
		String[] invoiceIds = req.getParameter("invoiceIds").split(",");
//System.out.println("SessionUtil.getUserId():"+SessionUtil.getUserId());
//System.out.println("statusUpReason:"+req.getParameter("statusUpReason"));
		
		List<ArInvoice> list = new ArrayList();
		for(int i=0; i<invoiceIds.length; i++){
			ArInvoice entity = arInvoiceDao.get(new Integer(invoiceIds[i]));
			entity.setOldStatus(entity.getStatus()); //原始状态
			entity.setStatus(Constant.STATUS_VOID);//修改后的状态
			entity.setModifiedBy(SessionUtil.getUserId());
			entity.setModifyDate(new Date());
			entity.setStatusUpdReason(req.getParameter("statusUpReason"));
			list.add(entity);
		}

		boolean bres = arInvoiceService.delete(list);
		Struts2Util.getRequest().setAttribute("operate_result",bres);
		
		return "delete_invoice_page";
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
		Map m = this.arInvoiceDao.getUserName(id);
		String symbol=currency_Dao.getCurrencySymbol(m.get("currency").toString());
		msg = "{ state:'success', currency:'"+m.get("currency")+"', name : '"+m.get("name")+"',saleId:'"+m.get("saleId")+"',amt:"+m.get("amt")+",currency:'"+m.get("currency")+"',symbol:'"+symbol+"' }";
		}catch(Exception e){
			e.printStackTrace();
		    msg = "{ state:'error',msg:'"+e.getMessage()+"' }";
		}
		Struts2Util.renderJson(msg);
		return null;
	}
	
	/**
	 * 获取saler的名称
	 * @return
	 */
	public String getSalerNameById(){
		int id = 0;
		String msg = "";
		try{
		id = Integer.parseInt(Struts2Util.getParameter("salerNo"));
		String name  = this.arInvoiceDao.getLoginNameById(id);
		msg = "{ state:'success',name : '"+name+"' }";
		}catch(Exception e){
			e.printStackTrace();
		    msg = "{ state:'Query saler name error',msg:'"+e.getMessage()+"' }";
		}
		Struts2Util.renderJson(msg);
		return null;
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
		boolean flag = this.arInvoiceDao.isAllocation(invoiceId);
	    msg = "{ state:'success',flag : "+flag +"}";
		}catch(Exception e){
			e.printStackTrace();
			 msg = "{ state:'error',msg:'"+e.getMessage()+"' }";
		}
		Struts2Util.renderJson(msg);
		return null;
	}
	
	
	
/**
 * 浏览历史付款
 * @return
 */
	public String viewAllocationHistory(){
		int id = Tools.String2Integer(Struts2Util.getParameter("invoiceId"));
		if(id!=0){
			String hql = "from ArTransactionAllocation a where a.invoice_id= ? ";
		    arInvoiceAllocationList = (List<ArTransactionAllocation>) this.arInvoiceAllocationDao.createQuery(hql, id).list();	
		}
		return "view_allocation_history";
	}


public List<ArTransactionAllocation> getArInvoiceAllocationList() {
	return arInvoiceAllocationList;
}


public void setArInvoiceAllocationList(
		List<ArTransactionAllocation> arInvoiceAllocationList) {
	this.arInvoiceAllocationList = arInvoiceAllocationList;
}


public OrderItemOverSizeDTO getOrderItemOverSizeDTO() {
	return OrderItemOverSizeDTO;
}


public void setOrderItemOverSizeDTO(OrderItemOverSizeDTO orderItemOverSizeDTO) {
	OrderItemOverSizeDTO = orderItemOverSizeDTO;
}

/**
 *  退票
 * 
 * 
 * 
 */

public void  changeOderItem(ArInvoice arInvoice)
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
			
			this.arInvoiceService.checkOderItem( arInvoice.getInvoiceId(), 
			           arInvoice.getOrderNo(),itemNo,quantity, size,true );
			   
				 				
		}//for 循环结束					
	}
	/**
	 * 对invoice line 进行check  结束
	 */
}

public String printInvoice() throws IOException, DocumentException, ParseException{
	HttpServletRequest req = Struts2Util.getRequest();
	String path=req.getRealPath( "/ ");
	String[] invoiceIds = arInvoiceDto.getInvoiceIds().split(",");
	String inputFile = path + "invoice.html";  
	OutputStream os1 = new FileOutputStream(inputFile);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	
	String str = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>" +
		"<html xmlns='http://www.w3.org/1999/xhtml'>" +
		"<head>" +
		"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" +
		"<title>test</title>" +
		"<style>" +
		"body{" +
			"font-family:Arial, Helvetica, sans-serif;" +
			"font-size:12px;" +
			"}" +
		".table-style{" +
			"border:1px solid #3b5e91; " +
			"border-collapse:collapse;" +
			"}" +
		".table-style th{" +
			"line-height:25px; " +
			"background-color:#dbe5f1; " +
			"border-left:1px solid #3b5e91; " +
			"border-bottom:1px solid #3b5e91;" +
			"}" +
		".table-style td{" +
			"border-left:1px solid #3b5e91; " +
			"border-bottom:1px solid #3b5e91;" +
			"}" +
		"</style>" +
		"</head>" +
		"<body>";
	
	try{//捕获检索数据异常 
		for(int i=invoiceIds.length-1; i>=0; i--){
			String invoiceId = invoiceIds[i];
			arInvoice = this.arInvoiceDao.get(new Integer(invoiceId));
			int orderNo = arInvoice.getOrderNo();
			PbCurrency pbCurrency = currencyDao.getCurrencySymbol(arInvoice.getCurrency());
			String symbol = pbCurrency.getSymbol();
			
			//BILL TO:
			//SHIP TO：
			OrderAddress billTo = arInvoiceDao.getOrderAddress(orderNo,"BILL_TO");
			OrderAddress shipTo = arInvoiceDao.getOrderAddress(orderNo,"SHIP_TO");
			//Organization billToOrg = billTo.getOrganization();
			//Organization shipToOrg = shipTo.getOrganization();
			
			String billToLine1 = billTo.getFirstName() + " "+ ((billTo.getMidName()==null)?"":(billTo.getMidName() + " ")) + billTo.getLastName();
		//	String billToLine2 = billToOrg.getName();
			String billToLine2 =billTo.getOrgName();
			String billToLine3 = billTo.getAddrLine1() + " " + billTo.getAddrLine2() + " " +  billTo.getAddrLine3();
			String billToLine4 = billTo.getCity() + ", " + billTo.getState() + " " + billTo.getZipCode() + " " + billTo.getCountry();
			
			String shipToLine1 = shipTo.getFirstName() + " "+ ((shipTo.getMidName()==null)?"":(shipTo.getMidName() + " ")) + shipTo.getLastName();
		//	String shipToLine2 = shipToOrg.getName();
			String shipToLine2 =shipTo.getOrgName();
			String shipToLine3 = shipTo.getAddrLine1() + " " + shipTo.getAddrLine2() + " " +  shipTo.getAddrLine3();
			String shipToLine4 = shipTo.getCity() + ", " + shipTo.getState() + " " + shipTo.getZipCode() + " " + shipTo.getCountry();
			
			//PAYMENT:
			PaymentVoucher paymentVoucher = arInvoiceDao.getPaymentVoucher(orderNo);
			String payment = "";
			if(paymentVoucher.getPaymentType().equals("PO")){
				payment = "PO: " + paymentVoucher.getPoNumber();
			}else{//CC
				String ccNo = paymentVoucher.getCcNo();
				if(ccNo == null)ccNo = "";
				else if(ccNo.length() > 4)ccNo = ccNo.substring(ccNo.length()-4 );//显示后4位
				payment = arInvoiceDao.getCreditCardText(paymentVoucher.getCcType()) + ":" + ccNo;
			}
			
			Map<String,String> trackMap = arInvoiceDao.getTrackingInfo(invoiceId);
			//Shipping Date
			String shippingDate = simpleDateFormat.format(simpleDateFormat.parse(trackMap.get("shippingDate").toString()));
			//Terms
			Map<String,String> termsMap = arInvoiceDao.getTermsAndOrderDate(orderNo);
			String terms = termsMap.get("terms").toString();
			String orderDate = simpleDateFormat.format(simpleDateFormat.parse(termsMap.get("orderDate").toString()));
			
			//Overdue:
			String overdue = "";
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(new Date());
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(arInvoice.getExprDate());
			long millis =calendar2.getTimeInMillis() - calendar1.getTimeInMillis();
			if(millis > 0)overdue = millis/(24*60*60*1000)+"";
			
			//Tracking NO
			String trackingNo = trackMap.get("trackingNo").toString();
			String carrier = trackMap.get("carrier").toString();
			
			str +=	"<table width='100%' border='0' cellspacing='0' cellpadding='2' style='border-top:2px solid #3b5e91; background:url(bg.gif) repeat-x;'>" +
			 			"<tr>" +
			 				"<td width='50%'><img src='genscript_logo.gif' alt='' name='logo' id='logo' /></td>" +
			 				"<td width='50%' align='right' style='font-size:26px; font-weight:bold; color:#3b5e91;'>ORIGINAL INVOICE </td>" +
			 			"</tr>" +
			 			"<tr>" +
			 				"<td>GenScript Corporation</td>" +
			 				"<td align='right'>INVOICE #: " + arInvoice.getInvoiceId() + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 				"<td>860 Centennial Ave., Piscataway, NJ 08854, USA</td>" +
			 				"<td align='right'>ORDER #: " + arInvoice.getOrderNo() + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 				"<td>Tel: 732-885-9188 Fax: 732-210-0262</td>" +
			 				"<td align='right'>INVOICE DATE: " + simpleDateFormat.format(arInvoice.getInvoiceDate()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 				"<td>Email: accounting@genscript.com</td>" +
			 				"<td>&nbsp;</td>" +
			 			"</tr>" +
			 		"</table>" +
			 		"<br />" +
			 		"<table class='table-style' width='100%' border='0' cellspacing='0' cellpadding='2'>" +
			 			"<tr>" +
			 				"<th align='center'>BILL TO</th>" +
			 				"<th align='center'>SHIP TO</th>" +
			 			"</tr>" +
			 			"<tr>" +
			 				"<td>" +
			 					billToLine1 + "<br />" +
			 					billToLine2 + "<br/>" +
			 					billToLine3 + "<br/>" +
			 					billToLine4 + "<br/>" +
			 				"</td>" +
			 				"<td>" +
				 				shipToLine1 + "<br />" +
				 				shipToLine2 + "<br/>" +
				 				shipToLine3 + "<br/>" +
				 				shipToLine4 + "<br/>" +
			 				"</td>" +
			 			"</tr>" +
			 		"</table>" +
			 		"<br />" +
			 		"<table class='table-style' width='100%' border='0' cellspacing='0' cellpadding='2'>" +
			 			"<tr>" +
			 				"<th align='center'>PAYMENT</th>" +
			 				"<th align='center'>SHIPPING DATE</th>" +
			 				"<th align='center'>TERMS</th>	" +
			 				"<th align='center'>ORDER DATE</th>" +
			 				"<th align='center'>OVERDUE</th>" +
			 				"<th align='center'>" + carrier + " TRACKING NO</th>" +
			 			"</tr>" +
			 			"<tr>" +
			 				"<td>" + payment + "</td>" +
			 				"<td>" + shippingDate + "</td>" +
			 				"<td>" + terms + "</td>" +
			 				"<td>" + orderDate + "</td>" +
			 				"<td>" + overdue + "</td>" +
			 				"<td>" + trackingNo + "</td>" +
			 			"</tr>" +
			 		"</table>" +
			 		"<br />" +
			 		"<table class='table-style' width='100%' border='0' cellspacing='0' cellpadding='2'>" +
			 			"<tr>" +
			 				"<th align='center'>QTY</th>" +
			 				"<th align='center'>DESCRIPTION</th>" +
			 				"<th align='center'>UNIT PRICE</th>	" +
			 				"<th align='center'>EXTENDED PRICE</th>" +
			 			"</tr>";
			
			List<ArInvoiceLine> lines = arInvoiceLineDao.queryInvoiceByInvoiceId(new Integer(invoiceId));
			for(int j=0; j<lines.size(); j++){
				ArInvoiceLine arInvoiceLine = (ArInvoiceLine)lines.get(j); 
				int qty = arInvoiceLine.getQty();
				String description = arInvoiceLine.getName();
				String unitPrice = symbol + arInvoiceLine.getUnitPrice();
				String extendedPrice = symbol + arInvoiceLine.getAmount();
				String discount = symbol + arInvoice.getDiscount();
				String tax = symbol + arInvoice.getTax();
				String shipping = symbol + arInvoice.getShipping();
				String previousBalance = symbol + "0.00";
				String totalAmount = symbol + arInvoice.getTotalAmount();
				String paidAmount = symbol + arInvoiceDao.getPaidAmount(Integer.parseInt(invoiceId));
				String balance = "";
				String amountDue = "";
				if(arInvoice.getBalance() < 0)balance = "0.000";
				else balance = arInvoice.getBalance()+"";
				if(arInvoice.getInvoiceType().equals("AR Prepayment")){
					amountDue = symbol + (new Float(balance)*0.5);
				}else{
					amountDue = symbol + balance;
				}
				balance = symbol + balance;
				
				str += 
				"<tr>" +
					"<td>"+ qty + "</td>" +
					"<td>"+ description + "</td>" +
					"<td align='right'>"+ unitPrice + "</td>" +
					"<td align='right'>"+ extendedPrice + "</td>" +
				"</tr>" +
				"<tr>" +
					"<td colspan='2' rowspan='8'>" +
						"<span style='font-weight:bold;'>Important Reminder:</span><br />" +
						"1. Service fees associated with wire transfers or direct deposits are the" +
						"sole responsibility of the remitting party.<br />" +
						"2. A late fee of 1.5% per month (18% per annum) will be applied to all accounts over 30 days old from the date of invoice.<br />" +
						"3. When making payments, please indicate clearly GenScript's invoice number on the check, or wire transfer, or deposit.<br />" +
						"<span style='font-weight:bold;'>Bank Information:</span><br />" +
						"Beneficiary Name: Bank of America Domestic Deposits 5687; <br />" +
						"Account#: 606495687013<br />" +
						"Beneficiary Bank Name: Bank of America Tokyo; Beneficiary Bank<br />" +
						"Swift Code:BOFAJPJX<br />" +
						"Reference (<span style='font-weight:bold;'>Required</span>): GENSCRIPT CORPORATION; Referential" +
						"Account #: 12106029<br />" +
						"Bank of America Tokyo, Sanno Park Tower 15F, 2-11-1 Nagata-cho, Chiyoda-ku, Tokyo 100-6115 Japan" +
					"</td>" +
					"<td align='right' style='font-weight:bold;'>DISCOUNT</td>" +
					"<td align='right'>"+ discount + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>TAX</td>" +
					"<td align='right'>"+ tax + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>SHIPPING</td>" +
					"<td align='right'>"+ shipping + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>PREVIOUS BALANCE</td>" +
					"<td align='right'>"+ previousBalance + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>TOTAL AMOUNT</td>" +
					"<td align='right'>"+ totalAmount + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>PAID AMOUNT</td>" +
					"<td align='right'>"+ paidAmount + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>BALANCE</td>" +
					"<td align='right'>"+ balance + "</td>" +
				"</tr>" +
				"<tr>  " +
					"<td align='right' style='font-weight:bold;'>AMOUNT DUE (JPY)</td>" +
					"<td align='right'>"+ amountDue + "</td>" +
				"</tr>";
			}
			
			str += 	"</table>" +
			 		"<br />" +
			 		"<p><div align='center' style='font-size:18px; font-weight:bold;'>THANK YOU FOR YOUR BUSINESS!</div></p>";
		 	if(i>0){
		 		str += "<div style='PAGE-BREAK-AFTER:always'></div>";
		 	}	
		}
	
	}catch(Exception ex){
		ex.printStackTrace();
		return "print_error_page";
	}
	 str +=	"</body></html>";
     os1.write(str.getBytes());
	
    String url = new File(inputFile).toURI().toURL().toString();  
//    String outputFile = path+"invoice.pdf";  
//    OutputStream os = new FileOutputStream(outputFile);
    ITextRenderer renderer = new ITextRenderer();  
    renderer.setDocument(url);  

    // 解决中文支持问题  
    ITextFontResolver fontResolver = renderer.getFontResolver();  
//    fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  

    // 解决图片的相对路径问题  
    String[] paths = path.split("\\\\");
    String prefixPath = "file:/";
    for(int i=0; i<paths.length-1; i++){
    	prefixPath += paths[i] + "/";
    }
    prefixPath += "images/";
    renderer.getSharedContext().setBaseURL(prefixPath);  
    renderer.layout();

    this.arInvoiceService.updatePrintFlag(invoiceIds);//更新打印
    try{
    	OutputStream os = Struts2Util.getResponse().getOutputStream();
        renderer.createPDF(os);  
        os.close(); 
        Struts2Util.getResponse().reset();
    }catch(IllegalStateException ex){
    	//ex.printStackTrace();
    }
	
	return null;
}

/**
 * 获取汇率
 * @return
 */
 
public String getCurrencyRate(){
	String fromCurrency = Struts2Util.getParameter("fromCurrency");
	String toCurrency = Struts2Util.getParameter("toCurrency");
	Date d = new Date();
	double rate = this.arInvoiceDao.getCurrency(null, fromCurrency, toCurrency, d);
	String symbol=currency_Dao.getCurrencySymbol(toCurrency );
	String msg = "{state:'success',rate:"+rate+",symbol:'"+symbol+"'}";
	Struts2Util.renderJson(msg);
	return null;
}


public boolean checkOver()
{
	if(0!=param.size())
    {
		return false;
    }
	
	
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
		if(null!=itemNos)
		{
			
			return false;
		}
	   for(int i=0;i<itemNos.length;i++)
	   {
			int itemNo=0;
		    int quantity=0;
			double size=0;
				
			itemNo=Tools.String2Integer(itemNos[i]);
			quantity=Tools.String2Integer(qtys[i]);
			size=Tools.String2Float(sizes[i]);
				
		    OrderItemOverSizeDTO = this.arInvoiceService.checkOderItem( arInvoice.getInvoiceId(), 
				           arInvoice.getOrderNo(),itemNo,quantity, size,false );
				   
			if(null!=OrderItemOverSizeDTO)
			{
				return true;					
			}						
	 }//for 循环结束					
	 
	   return false;
}
 
	
}
