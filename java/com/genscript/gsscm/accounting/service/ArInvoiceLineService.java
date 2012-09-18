package com.genscript.gsscm.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;

import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.OrderItem;
import com.genscript.gsscm.accounting.util.Tools;



	/**
	 * @function     发票的CURD
	 * @version      1.0
	 * @auther       陈文擎
	 * @date         2010-11-11
	 *  
	 */

@Service
@Transactional
public class ArInvoiceLineService {

    @Autowired
	ArInvoiceLineDao arInvoiceLineDao;
    
     
     
    
    /**
     * @function 根据参数，新增一个发票
     * @param    entity
     * @return
     */
    
    public void add(ArInvoiceLine entity )
    {
    	arInvoiceLineDao.save(entity);
    }   
//    
//    public String fillTable(List<ArInvoiceLine> list){
//		StringBuffer sb = new StringBuffer();
//		for(ArInvoiceLine ar : list){
//			sb.append("<tr>");
//			sb.append("<td width=\"46\"><input type=\"checkbox\" value=\"checkbox\"  /></td>");
//			sb.append("<td width=\"65\" ><a  onclick=\"editInvoiceLine("+ar.getLineNo()+")\" >80</a><input type='hidden' name='param.lineNo' value='"+ar.getLineNo()+"'></td>");
//			sb.append(" <td width=\"88\">"+ar.getItemNo()+"<input type='hidden' name='param.itemNo' value='"+ar.getItemNo()+"'></td>");
//			sb.append("<td width=\"81\">"+ar.getCatalogNo()+"<input type='hidden' name='param.catalogNo' value='"+ar.getCatalogNo()+"'></td>");
//			sb.append("<td width=\"151\">"+ar.getName()+"<input type='hidden' name='param.name' value='"+ar.getName()+"'></td>");
//			sb.append("<td width=\"75\">"+ar.getQty()+"<input type='hidden' name='param.qty' value='"+ar.getQty()+"'></td>");
//			sb.append("<td width=\"74\">"+ar.getQtyUom()+"<input type='hidden' name='param.qtyUom' value='"+ar.getQtyUom()+"'></td>");
//			sb.append("<td width=\"64\">"+ar.getSize()+"<input type='hidden' name='param.size' value='"+ar.getSize()+"'></td>");
//			sb.append("<td width=\"81\">"+ar.getAmount()+"<input type='hidden' name='param.unitPrice' value='"+ar.getAmount()+"'></td>");
//			sb.append("<td>"+ar.getTax()+"<input type='hidden' name='param.tax' value='"+ar.getTax()+"'></td>");
//			sb.append("</tr>");
//		}
//		
//		return sb.toString();
//    }
    
    
    public String fillTableByOrder(List<OrderItem> list,int invoiceId,String symbol){
		StringBuffer sb = new StringBuffer();
		int i=1;
		for(OrderItem ar : list){
			sb.append("<tr>");
			sb.append("<td width=\"46\"><input type=\"checkbox\" name=\"checkbox\"  /></td>");
			sb.append("<td width=\"65\" ><a style='cursor:pointer'  onclick=\"editInvoiceLine("+i+")\" >"+i+"</a><input type='hidden' name='param.lineNo' value='"+i+"'></td>");
			sb.append(" <td width=\"88\">"+ar.getItemNo()+"<input type='hidden' name='param.itemNo' value='"+ar.getItemNo()+"'></td>");
			sb.append("<td width=\"81\">"+ar.getCatalogNo()+"<input type='hidden' name='param.catalogNo' value='"+ar.getCatalogNo()+"'></td>");
			sb.append("<td width=\"151\">"+ar.getName()+"<input type='hidden' name='param.name' value='"+ar.getName()+"'></td>");
			sb.append("<td width=\"75\">"+ar.getQuantity()+"<input type='hidden' name='param.qty' value='"+ar.getQuantity()+"'></td>");
			sb.append("<td width=\"74\">"+ar.getQtyUom()+"<input type='hidden' name='param.qtyUom' value='"+ar.getQtyUom()+"'></td>");
			sb.append("<td width=\"64\">"+ar.getSize()+"<input type='hidden' name='param.size' value='"+ar.getSize()+"'></td>");
			sb.append("<td width=\"81\" name='p_price'>"+symbol+Tools.formatFloat(ar.getUnitPrice())+"<input type='hidden' name='param.unitPrice' value='"+Tools.formatFloat(ar.getUnitPrice())+"'></td>");
			sb.append("<td width=\"81\" name='p_amount'>"+symbol+Tools.formatFloat(ar.getAmount())+"<input type='hidden' name='param.amount' value='"+Tools.formatFloat(ar.getAmount())+"'></td>");
			sb.append("<td name='p_tax'>"+symbol+Tools.formatFloat(ar.getTax())+"<input type='hidden' name='param.tax' value='"+Tools.formatFloat(ar.getTax())+"'>");
			sb.append("<input type='hidden' name='param.discount' value='"+Tools.formatFloat(ar.getDiscount())+"'>");
			sb.append("<input type='hidden' name='param.creationDate' value='"+Tools.formatDate( ar.getCreationDate() )+"'>");
			sb.append("<input type='hidden' name='param.createdBy' value='"+ar.getCreatedBy()+"'>");
			sb.append("<input type='hidden' name='param.modifyDate' value='"+Tools.formatDate(ar.getModifyDate())+"'>");
			sb.append("<input type='hidden' name='param.modifiedBy' value='"+ar.getModifiedBy()+"'>");
			
			//如果是修改页面，则需要多传递些数据
			if(invoiceId != 0){
				sb.append("<input type='hidden' name='param.invoiceLineIds' value='0'>");
				sb.append("<input type='hidden' name='param.invoiceIds' value='"+invoiceId+"'>");
			}
			
			sb.append("</td>");
			sb.append("</tr>");
			i++;
		}
		return sb.toString();
    }
    
    public float getMaxQtyOrSize(int orderNo,int itemNo){
    	float f = 0f;
    	OrderItem o = this.arInvoiceLineDao.queryOrderItem(orderNo, itemNo);
    	if(o!=null){
    		f = o.getQuantity()>o.getSize() ? o.getQuantity() : o.getSize();
    	}
    	return f;
    }
    
  
}
