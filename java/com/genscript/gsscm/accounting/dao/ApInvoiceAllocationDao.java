package com.genscript.gsscm.accounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ApInvoiceListView;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ArInvoiceListView;
import com.genscript.gsscm.accounting.util.Tools;

@Repository
public class ApInvoiceAllocationDao extends HibernateDao {
	
	/**
	 * 检索交易关联的发票
	 * @param m
	 * @return
	 * @throws SQLException 
	 */
	public  List<ApInvoiceView> list(Map m) throws SQLException{
		List<ApInvoiceView> list = new ArrayList<ApInvoiceView>();
//		String hql = "select b.invoice_id,b.invoice_no,b.amount,b.vendor_no, b.order_no, b.total_amount as invoice_amount, sum(c.apply_amount) as paid_amount, b.discount, b.balance, b.currency "
//				+" from   accounting.ap_invoice_view b left join  accounting.ap_transaction_allocation c on   b.invoice_id=c.invoice_id"
//				+"   where 1=1 ";
		
//		if(m.get("transaction_id")!= null && !m.get("transaction_id").equals("")){
//            hql += " and c.transaction_id = '"+m.get("transaction_id").toString().trim()+"'";
//		}
	       
		String hql = "select * from accounting.v_ap_invoice_list a where 1= 1 ";
		
		if(m.get("status")!= null && !m.get("status").equals("")){
             hql += " and a.status = '"+m.get("status").toString().trim()+"'";
		}
		if(m.get("invoiceNo")!=null &&  Tools.String2Integer(m.get("invoiceNo").toString())!= 0){
			hql += " and a.invoice_id = "+Tools.String2Integer(m.get("invoiceNo").toString());
		}
		if(m.get("vendorNo")!=null &&  Tools.String2Integer(m.get("vendorNo").toString())!= 0){
			hql += " and a.vendor_no = "+Tools.String2Integer(m.get("vendorNo").toString());
		}
		if(m.get("orderNo")!=null &&  Tools.String2Integer(m.get("orderNo").toString())!= 0){
			hql += " and a.order_no = "+Tools.String2Integer(m.get("orderNo").toString());
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
//		System.out.println(hql);
//		list = this.getSession().createSQLQuery(hql).addEntity(ApInvoiceListView.class).list();
		Connection conn = this.getSession().connection();
		PreparedStatement ps = conn.prepareStatement(hql);
    	ResultSet rs = ps.executeQuery();
    	ApInvoiceView  a = null;
    	while(rs.next()){
    		a = new ApInvoiceView();
    		a.setInvoiceId(rs.getInt("invoice_id"));
    	    a.setInvoiceNo(rs.getString("invoice_no"));
    	    a.setStatus(rs.getString("status"));
    	    a.setPaidAmt(rs.getFloat("paid_amt"));
    	    a.setTotalAmount(rs.getFloat("total_amount"));
    	    a.setBalance(rs.getFloat("balance"));
    	    a.setOrderNo(rs.getInt("order_no"));
    	    a.setShipmentId(rs.getInt("shipment_id"));
    	    a.setVendorNo(rs.getInt("vendor_no"));
    	    a.setInvoiceType(rs.getString("invoice_type"));
    	    a.setInvoiceDate(new java.util.Date(rs.getTimestamp("invoice_date").getTime()));
    	    a.setTotalAmount(rs.getFloat("total_amount"));
    	    a.setCurrency(rs.getString("currency"));
    	    list.add(a);
    	}
    	rs.close();
    	ps.close();
    	conn.close();
		return list;
	}
}
