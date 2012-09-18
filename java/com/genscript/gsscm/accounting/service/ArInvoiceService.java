package com.genscript.gsscm.accounting.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceViewDao;
import com.genscript.gsscm.accounting.dto.OrderNoDTO;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.order.dto.OrderItemOverSizeDTO;
import com.genscript.gsscm.order.service.OrderService;


	/**
	 * @function     发票的CURD
	 * @version      1.0
	 * @auther       陈文擎
	 * @date         2010-11-11
	 *  
	 */

@Service
@Transactional
public class ArInvoiceService {

    @Autowired
	ArInvoiceDao arInvoiceDao;
    
    @Autowired
    ArInvoiceViewDao arInvoiceViewDao;
    @Autowired
    ArInvoiceLineDao arInvoiceLineDao;
	@Autowired
	OrderService orderService;
    /**
     * @function 根据查询条件查询出invoice
     * @param    page
     * @param    srch
     * @return
     */
    
    public Page<ArInvoice> getInvoiceByOptions(Page<ArInvoice> page,List<PropertyFilter> srch)
    {
    	Page<ArInvoice> result=null;
    	System.out.print("=============size: " + srch.size() + "===" + srch);
    	result=arInvoiceDao.queryInvoices(page,srch);
    	return result;
    }
    
    /**
     * @function 根据参数，新增一个发票
     * @param    entity
     * @return
     */
    
    public int add(ArInvoice entity )
    {
//    	if(!arInvoiceDao.checkInvoiceNoExists(entity.getInvoiceNo()))
    	arInvoiceDao.save(entity);
//    	else
//    	return "0";
    	int parmId=0;
    	if(null!=entity)
    	parmId=entity.getInvoiceId(); 
    	
    	return parmId;
    }  
    
    
    
    public String edit(ArInvoice entity )
    {
//    	if(!arInvoiceDao.checkInvoiceNoExists(entity) )
    	if(null==entity)
    	return "";
    	
    	arInvoiceDao.save(entity);
//    	else
//    	return "0";
    	    	
    	return entity.getInvoiceNo();
    }  
    
    
    public int getInvoiceId(ArInvoice entity )
    {
    	if(null==entity)
        return 0;
    	int id=arInvoiceDao.getInvoiceIdByNo(entity.getInvoiceNo());    	
    	return id;
    }
    
    public ArInvoice  getInvoiceByNo(String InvoiceNo )
    {
    	 return arInvoiceDao.getInvoiceByNo(InvoiceNo); 
    }
    
    public ArInvoice  getInvoiceById(String InvoiceId )
    {
    	 return arInvoiceDao.getInvoiceById(InvoiceId); 
    }
    
    public boolean delete(List<ArInvoice> entitys){
//    	return arInvoiceDao.doDelete(entitys);
        if( (null==entitys)||(0==entitys.size()) )
        return false;
    	return doDelete(entitys);
    }   
    
//	/**
//	 * 根据orderNo 查询相应的invoice
//	 * @param page
//	 * @param filters
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public Page<ArInvoice> searchInvoiceList(Page<ArInvoice> page,
//			int orderNo,String invoiceNo) {
////		return arInvoiceDao.findPage(page, orderNo);
//		Map m = this.arInvoiceDao.searchInvoiceList(page,orderNo,invoiceNo);
//		List<ArInvoice> list  =null;
//		if(m.get("list")!=null){
//			list = (List<ArInvoice>) m.get("list");
//		}
//		long total = (Long) m.get("total");
//		page.setResult(list);
//		page.setTotalCount(total);
//		return page;
//	}
    
    
    /**
	 * 修改汇率时创建新的invoice  将原invoice状态修改，然后再copy一份，只是将状态改为In Progerss状态，同时*汇率
	 * @param invoiceId
	 * @return
	 */
	public void copyNewInvoice(ArInvoice oldInvoice){
		
		ArInvoice newInvoice = new ArInvoice();
		 int invoiceId = oldInvoice.getInvoiceId();
		 newInvoice.setCurrency(oldInvoice.getCurrency());
		 newInvoice.setStatusUpdReason("");
		 String fromcurrency = "";
		 String toCurrency = "";
		 float f = (float)this.arInvoiceDao.getCurrency(oldInvoice,fromcurrency,toCurrency,new Date());
		 newInvoice.setBalance(oldInvoice.getBalance()*f );
		 newInvoice.setDiscount(oldInvoice.getDiscount()*f );
		 newInvoice.setShipping(oldInvoice.getShipping()* f  );
		 newInvoice.setTax(oldInvoice.getTax() * f );
//		 newInvoice.setPaidAmount(oldInvoice.getPaidAmount()*f );
		 newInvoice.setSubTotal(oldInvoice.getSubTotal()*f);
		 newInvoice.setTotalAmount(oldInvoice.getTotalAmount()*f );
		 newInvoice.setCreatedBy(SessionUtil.getUserId());
		 newInvoice.setCreationDate(new Date());
		 newInvoice.setModifiedBy(SessionUtil.getUserId());
		 newInvoice.setModifyDate(new Date());
		 newInvoice.setCustNo(oldInvoice.getCustNo());
		 newInvoice.setOrderNo(oldInvoice.getOrderNo());
		 newInvoice.setSalesContact(oldInvoice.getSalesContact());
		 newInvoice.setShipmentId(oldInvoice.getShipmentId());
		 newInvoice.setDescription(oldInvoice.getDescription());
		 newInvoice.setInvoiceType(oldInvoice.getInvoiceType());
		 newInvoice.setInvoiceDate(oldInvoice.getInvoiceDate());
		 newInvoice.setExprDate(oldInvoice.getExprDate());
		 newInvoice.setPaymentMethod(oldInvoice.getPaymentMethod());
		 newInvoice.setCustomerNote(oldInvoice.getCustomerNote());
		 newInvoice.setPaymentTerm(oldInvoice.getPaymentTerm());
		 newInvoice.setPrintedFlag(oldInvoice.getPrintedFlag());
		 newInvoice.setComment(oldInvoice.getComment());
		 newInvoice.setStatus(oldInvoice.getStatus());
		 //保存修改汇率后的invoice
		 newInvoice.setCompanyId(oldInvoice.getCompanyId());
		 this.arInvoiceDao.save(newInvoice);
		 //保存invoiceline信息
         List<ArInvoiceLine> list = this.arInvoiceLineDao.queryInvoiceByInvoiceId(invoiceId);
		 for(ArInvoiceLine line : list){
			 line.setAmount(line.getAmount() * f);
			 line.setDiscount(line.getDiscount()*f);
			 line.setTax(line.getTax()*f);
			 line.setUnitPrice(line.getUnitPrice()*f);
			 this.arInvoiceLineDao.save(line);
		 }  
		 

		 
	}
		    
	
	
	  
    /**
     * swg 2010-11-16:逻辑删除发票，
只有状态为New以及In Progress的Invoice能够删除
如果删除之前状态为New, 可以直接将状态改为”Void”
如果删除之前状态为”In Progress”, 删除的时候需要先删除与该Invoice相关的allocation记录
 如果已经有allocation记录, 则不能删除, 删除的时候需要调用接口update purchase order item状态.
     * @param entitys
     * @return
     * modified by zhouyong
     */
    public boolean doDelete(List<ArInvoice> entitys){
    	for(int i=0; i<entitys.size(); i++){
    		ArInvoice entity = entitys.get(i);
    		if(entity != null){
    			if(entity.getOldStatus().equals(Constant.STATUS_INPROCESS)){
//    				this.getSession().createSQLQuery(" delete  from  accounting.ar_transaction_allocation  where accounting.ar_transaction_allocation.invoice_id = ?").setParameter(0, entity.getInvoiceId()).executeUpdate();
    				//如果未对过账，则执行更新，否则不能更新
    				boolean flag = this.arInvoiceDao.isAllocation(entity.getInvoiceId()); 
    				if(flag){
    					return false;
    				}else{
    					List<ArInvoiceLine> list = this.arInvoiceLineDao.queryInvoiceByInvoiceId(entity.getInvoiceId());
                        for(ArInvoiceLine a : list){
                        	//处理退票
                        	OrderItemOverSizeDTO dto =  this.checkOderItem(a.getInvoiceId(), a.getOrderNo(), a.getItemNo(), a.getQty(), a.getSize(), true);                           
//                             if(dto!=null){//如果检查数量超标，则dto不为空，此时更新失败，提示用户
//                            	return false;
//                             }
                        }
    					this.arInvoiceDao.update(entity);
    				}
    			}else{
        		this.arInvoiceDao.update(entity);
    			}
        	}else return false;
    	}
    	
        return true;	    	
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
	 * 发票打印后更新状态
	 * @param ids
	 */
	public void updatePrintFlag(String[] ids){
		this.arInvoiceDao.updatePrintFlag(ids);
	}
}
