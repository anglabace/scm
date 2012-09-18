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
import com.genscript.gsscm.accounting.dao.ApInvoiceDao;
import com.genscript.gsscm.accounting.dao.ApInvoiceLineDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dto.OrderNoDTO;
import com.genscript.gsscm.accounting.entity.ApInvoice;
import com.genscript.gsscm.accounting.entity.ApInvoiceLine;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
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
public class ApInvoiceService {

    @Autowired
	ApInvoiceDao apInvoiceDao;
    @Autowired
	ArInvoiceDao arInvoiceDao;
    @Autowired
    ApInvoiceLineDao apInvoiceLineDao;
	@Autowired
	OrderService orderService;
	
    /**
     * @function 根据查询条件查询出invoice
     * @papam    page
     * @papam    srch
     * @return
     */
    public Page<ApInvoice> getInvoiceByOptions(Page<ApInvoice> page,List<PropertyFilter> srch)
    {
    	Page<ApInvoice> result=null;
    	System.out.print("=============size: " + srch.size() + "===" + srch);
    	result=apInvoiceDao.queryInvoices(page,srch);
    	return result;
    }
    
    /**
     * @function 根据参数，新增一个发票
     * @papam    entity
     * @return
     */
    
    public int add(ApInvoice entity )
    {
    	apInvoiceDao.save(entity);
    	return entity.getInvoiceId();
    }  
    
    
    
    public String edit(ApInvoice entity )
    {
//    	if(!apInvoiceDao.checkInvoiceNoExists(entity) )
    	apInvoiceDao.save(entity);
//    	else
//    	return "0";
    	    	
    	return entity.getInvoiceNo();
    }  
    
    
//    public int getInvoiceId(ApInvoice entity )
//    {
//    	if(null==entity)
//        return 0;
//    	int id=apInvoiceDao.getInvoiceIdByNo(entity.getInvoiceNo());    	
//    	return id;
//    }
    
    public ApInvoice  getInvoiceByNo(String InvoiceNo )
    {
    	 return apInvoiceDao.getInvoiceByNo(InvoiceNo); 
    }
    
    
    public boolean delete(List<ApInvoice> entitys){
//    	return apInvoiceDao.doDelete(entitys);
    	return doDelete(entitys);
    }   
    
    
    /**
	 * swg 2010-11-16:逻辑删除发票， 只有状态为New以及In Progress的Invoice能够删除 如果删除之前状态为New,
	 * 可以直接将状态改为”Void” 如果删除之前状态为”In Progress”,
	 * 删除的时候需要先删除与该Invoice相关的allocation记录 如果已经有allocation记录, 则不能删除,
	 * 删除的时候需要调用接口update purchase order item状态.
	 * 
	 * @param entitys
	 * @return modified by zhouyong
	 */
	public boolean doDelete(List<ApInvoice> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			ApInvoice entity = entitys.get(i);
			if (entity != null) {
				if (entity.getOldStatus().equals(Constant.STATUS_INPROCESS)) {
					// 如果未对过账，则执行更新，否则不能更新
					boolean flag = this.apInvoiceDao.isAllocation(entity
							.getInvoiceId());
					if (flag) {
						return false;
					} else {
						List<ApInvoiceLine> list = this.apInvoiceLineDao
								.queryInvoiceByInvoiceId(entity.getInvoiceId());
						for (ApInvoiceLine a : list) {
							// 处理退票
							OrderItemOverSizeDTO dto = this
									.checkOderItem(a.getInvoiceId(), a
											.getOrderNo(), a.getItemNo(), a
											.getQty(), a.getSize(), true);
							// if(dto!=null){//如果检查数量超标，则dto不为空，此时更新失败，提示用户
							// return false;
							// }
						}
						this.apInvoiceDao.update(entity);
					}
				} else {
					this.apInvoiceDao.update(entity);
				}
			} else
				return false;
		}

		return true;
	}
    
	/**
	 * 根据orderNo 查询相应的invoice
	 * @papam page
	 * @papam filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<ApInvoice> seapchInvoiceList(Page<ApInvoice> page,
			int orderNo,String invoiceNo) {
//		return apInvoiceDao.findPage(page, orderNo);
		Map m = this.apInvoiceDao.searchInvoiceList(page,orderNo,invoiceNo);
		List<ApInvoice> list  =null;
		if(m.get("list")!=null){
			list = (List<ApInvoice>) m.get("list");
		}
		long total = (Long) m.get("total");
		page.setResult(list);
		page.setTotalCount(total);
		return page;
	}
    
    
    /**
	 * 修改汇率时创建新的invoice  将原invoice状态修改，然后再copy一份，只是将状态改为In Progerss状态，同时*汇率
	 * @papam invoiceId
	 * @return
	 */
	public void copyNewInvoice(ApInvoice oldInvoice){
		
		ApInvoice newInvoice = new ApInvoice();
		 int invoiceId = oldInvoice.getInvoiceId();
		 newInvoice.setCurrency(oldInvoice.getCurrency());
		 newInvoice.setStatusUpdReason("");
		 String fromcurrency = "";
		 String toCurrency = "";
		 float f =0;
		 try{
		 f = (float)this.arInvoiceDao.getCurrency(null,fromcurrency,toCurrency,new Date());
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 newInvoice.setBalance(oldInvoice.getBalance()*f) ;
		 newInvoice.setDiscount(oldInvoice.getDiscount()*f );
		 newInvoice.setShipping(oldInvoice.getShipping()* f  );
		 newInvoice.setTax(oldInvoice.getTax() * f );
//		 newInvoice.setPaidAmount(oldInvoice.getPaidAmount()*f );
		 newInvoice.setSubTotal(newInvoice.getSubTotal() *f);
		 newInvoice.setTotalAmount(oldInvoice.getTotalAmount()*f );
		 //保存修改汇率后的invoice
		 this.apInvoiceDao.save(newInvoice);
		 //保存invoiceline信息
         List<ApInvoiceLine> list = this.apInvoiceLineDao.queryInvoiceByInvoiceId(invoiceId);
		 for(ApInvoiceLine line : list){
//			 line.setAmount(line.getAmount() * f);
//			 line.setDiscount(line.getDiscount()*f);
//			 line.setTax(line.getTax()*f);
//			 line.setUnitPrice(line.getUnitPrice()*f);
			 this.apInvoiceLineDao.save(line);
		 }  
		 

		 
	}
		    
	
	
//	  
//    /**
//     * swg 2010-11-16:逻辑删除发票，
//只有状态为New以及In Progress的Invoice能够删除
//如果删除之前状态为New, 可以直接将状态改为”Void”
//如果删除之前状态为”In Progress”, 删除的时候需要先删除与该Invoice相关的allocation记录
// 如果已经有allocation记录, 则不能删除, 删除的时候需要调用接口update purchase order item状态.
//     * @papam entitys
//     * @return
//     * modified by zhouyong
//     */
//    public boolean doDelete(List<ApInvoice> entitys){
//    	for(int i=0; i<entitys.size(); i++){
//    		ApInvoice entity = entitys.get(i);
//    		if(entity != null){
//    			if(entity.getOldStatus().equals(Constant.STATUS_INPROCESS)){
////    				this.getSession().createSQLQuery(" delete  from  accounting.ap_transaction_allocation  where accounting.ap_transaction_allocation.invoice_id = ?").setPapameter(0, entity.getInvoiceId()).executeUpdate();
//    				//如果未对过账，则执行更新，否则不能更新
//    				boolean flag = this.apInvoiceDao.isAllocation(entity.getInvoiceId()); 
//    				if(flag){
//    					return false;
//    				}else{
//    					List<ApInvoiceLine> list = this.apInvoiceLineDao.queryInvoiceByInvoiceId(entity.getInvoiceId());
//                        for(ApInvoiceLine a : list){
//                        	//处理退票
//                        	OrderItemOverSizeDTO dto =  this.checkOderItem(a.getInvoiceId(), a.getOrderNo(), a.getItemNo(), a.getQty(), a.getSize(), true);                           
////                             if(dto!=null){//如果检查数量超标，则dto不为空，此时更新失败，提示用户
////                            	return false;
////                             }
//                        }
//    					this.apInvoiceDao.update(entity);
//    				}
//    			}else{
//        		this.apInvoiceDao.update(entity);
//    			}
//        	}else return false;
//    	}
//    	
//        return true;	    	
//    }
    
    

	
	
	/**
	 * @function 检查invoice line 的 OderItem 是否超标
	 * @param invoiceId
	 * @param orderNo
	 * @param itemNo
	 * @param quantity
	 * @param size
	 * @return
	 */
	public OrderItemOverSizeDTO checkOderItem(int invoiceId, int orderNo,
			int itemNo, int quantity, double size, boolean isDelete) {
		Map data = arInvoiceDao.getItemNum(orderNo, itemNo);

		int oldQuantity = (Integer) data.get("qty");
		double oldSize = (new Double(data.get("size").toString())) ;
		OrderItemOverSizeDTO orderItemOverSizeDTO = null;
		try {
			orderItemOverSizeDTO = orderService.uptOrderItemStatus(orderNo,
					itemNo, quantity, size, oldQuantity, oldSize, isDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderItemOverSizeDTO;
	}
	

}
