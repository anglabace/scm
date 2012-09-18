package com.genscript.gsscm.accounting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ArInvoiceListView;
import com.genscript.gsscm.accounting.util.Tools;

@Repository
public class ArInvoiceListViewDao extends HibernateDao<ArInvoiceListView,Integer>{

	
	public  List<ArInvoiceListView> list(Map m){
		List<ArInvoiceListView> list = null;
		String hql = "select a.* from accounting.v_ar_invoice_list a, accounting.ar_invoices b, accounting.ar_transactions c where a.currency = c.currency  and b.cust_no = c.cust_no and a.invoice_id=b.invoice_id ";
		
//		hql += " and b.status = 'In Progress' ";
		
		if(m.get("transaction_id")!= null && !m.get("transaction_id").equals("")){
            hql += " and c.transaction_id = '"+m.get("transaction_id").toString().trim()+"'";
		}
        		
		if(m.get("status")!= null && !m.get("status").equals("")){
             hql += " and b.status = '"+m.get("status").toString().trim()+"'";
		}
		if(m.get("invoiceNo")!=null &&  Tools.String2Integer(m.get("invoiceNo").toString())!= 0){
			hql += " and a.invoice_id = "+Tools.String2Integer(m.get("invoiceNo").toString());
		}
		if(m.get("customerNo")!=null &&  Tools.String2Integer(m.get("customerNo").toString())!= 0){
			hql += " and c.cust_no = "+Tools.String2Integer(m.get("customerNo").toString());
		}
		if(m.get("orderNo")!=null &&  Tools.String2Integer(m.get("orderNo").toString())!= 0){
			hql += " and a.order_no = "+Tools.String2Integer(m.get("orderNo").toString());
		}
		if(m.get("filter_EQI_balance")!=null && !m.get("filter_EQI_balance").equals("") ){
			hql += " and a.balance >"+m.get("filter_EQI_balance");
		}
		if(m.get("currency")!=null && !m.get("currency").toString().equals("")){
			hql += " and a.currency = '"+m.get("currency")+"'";
		}
		if(m.get("dateFrom")!=null &&  !m.get("dateFrom").toString().equals("")){
			hql += " and a.invoice_id >= '"+m.get("dateFrom")+"'";
		}
		if(m.get("dateTo")!=null &&  !m.get("dateTo").toString().equals("")){
			hql += " and a.invoice_id <= '"+m.get("dateTo")+"'";
		}
		hql += " order by a.invoice_id desc ";
		System.out.println(hql);
		list = this.getSession().createSQLQuery(hql).addEntity(ArInvoiceListView.class).list();
		return list;
	}
	
	
	
}
