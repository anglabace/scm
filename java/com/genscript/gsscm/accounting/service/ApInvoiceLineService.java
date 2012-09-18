package com.genscript.gsscm.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.accounting.dao.ApInvoiceLineDao;
import com.genscript.gsscm.accounting.entity.ApInvoiceLineView;
import com.genscript.gsscm.accounting.entity.OrderItem;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;


@Service
@Transactional
public class ApInvoiceLineService {

	@Autowired 
	CurrencyDao currencyDao;
	@Autowired
	ApInvoiceLineDao apInvoiceLineDao;
	/**
	 * 选中order后，自动填充表格数据
	 * @param list
	 * @param invoiceIds
	 * @return
	 */
	public String fillTableByOrder(List<ApInvoiceLineView> list, int invoiceId,String currency) {
		StringBuffer sb = new StringBuffer();
		String symbol = this.currencyDao.getCurrencySymbol(currency);
		int i=1;
		for(ApInvoiceLineView ar : list){
			sb.append("<tr>");
			sb.append("<td width=\"46\"><input type=\"checkbox\" name=\"checkbox\"  /></td>");
			sb.append("<td width=\"65\" ><a style='cursor:pointer' onclick=\"editInvoiceLine("+i+")\" >"+i+"</a><input type='hidden' name='param.lineNo' value='"+i+"'></td>");
			sb.append("<td width=\"88\">"+ar.getItemNo()+"<input type='hidden' name='param.itemNo' value='"+ar.getItemNo()+"'></td>");
			sb.append("<td width=\"121\">"+ar.getCatalogNo()+"<input type='hidden' name='param.catalogNo' value='"+ar.getCatalogNo()+"'></td>");
			String name = (ar.getName() == null ? "" : ar.getName() );
			sb.append("<td width=\"131\">"+name+"<input type='hidden' name='param.name' value='"+name+"'></td>");
			sb.append("<td width=\"75\">"+ar.getQuantity()+"<input type='hidden' name='param.qty' value='"+ar.getQuantity()+"'></td>");
			sb.append("<td width=\"74\">"+ar.getQtyUom()+"<input type='hidden' name='param.qtyUom' value='"+ar.getQtyUom()+"'></td>");
			sb.append("<td width=\"64\">"+ar.getSize()+"<input type='hidden' name='param.size' value='"+ar.getSize()+"'></td>");
			sb.append("<td width=\"81\" name='p_price'>"+symbol+Tools.formatFloat2(ar.getUnitPrice())+"<input type='hidden' name='param.unitPrice' value='"+Tools.formatFloat2(ar.getUnitPrice())+"'></td>");
			sb.append("<td width=\"81\" name='p_amount'>"+symbol+Tools.formatFloat2(ar.getAmount())+"<input type='hidden' name='param.amount' value='"+Tools.formatFloat2(ar.getAmount())+"'></td>");
			sb.append("<td name='p_tax'>"+symbol+Tools.formatFloat2(ar.getTax())+"<input type='hidden' name='param.tax' value='"+Tools.formatFloat2(ar.getTax())+"'>");
			sb.append("<input type='hidden' name='param.discount' value='"+Tools.formatFloat2(ar.getDiscount())+"'>");
			sb.append("<input type='hidden' name='param.creationDate' value='2010-11-15'>");  //不保存到数据库 
			sb.append("<input type='hidden' name='param.createdBy' value='33'>");//不保存到数据库
//			sb.append("<input type='hidden' name='param.modifyDate' value='"+Tools.formatDate(ar.getModifyDate())+"'>");
//			sb.append("<input type='hidden' name='param.modifiedBy' value='"+ar.getModifiedBy()+"'>");
			
			//如果是修改页面，则需要多传递些数据
			if(invoiceId != 0){
				sb.append("<input type='hidden' name='param.invoiceLineIds' value='0'>");
				sb.append("<input type='hidden' name='param.invoiceIds' value='"+invoiceId+"'>");
			}
			
			sb.append("</td>");
			sb.append("</tr>");
			i++;
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}

	public int getMaxQtyOrSize(int orderNo, int itemNo) {
		int f = 0;
		PurchaseOrderItem o = this.apInvoiceLineDao.queryOrderItem(orderNo, itemNo);
    	if(o!=null){
    		o.getSize().intValue();
    		f= o.getQuantity() > o.getSize().intValue() ? o.getQuantity() : o.getSize().intValue();
    	}
    	return f;
	}

	
}
