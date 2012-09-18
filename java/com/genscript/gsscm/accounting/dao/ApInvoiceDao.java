package com.genscript.gsscm.accounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ApInvoice;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;

@Repository
public class ApInvoiceDao extends HibernateDao<ApInvoice ,Integer> {

	
	
	public void update(ApInvoice invoice){
		this.getSession().update(invoice);
	}
	
	
	public ApInvoice getById(int invoiceId){
		String hql = "from ApInvoice a where a.invoiceId=?";
		return (ApInvoice) this.getSession().createQuery(hql).setParameter(0, invoiceId).list().get(0);
	}
	
    /**
	 * @function  根据唯一的发票编号查询Invoice实体
	 * @param     invoiceNo
	 * @return    true 表示存在该发票号，false表示不存在
	 */
    public ApInvoice getInvoiceById(String invoiceId){
    	 
    	
    	String hql = "from ApInvoice a where a.invoiceId = ?";
    	List list = this.find(hql,Tools.String2Integer(invoiceId));
    	if(list == null || list.size() == 0){
    		return new ApInvoice();
    	}    	
    	ApInvoice apInvoice=(ApInvoice)list.get(0);    	
    	return apInvoice;
    }
    
    /**
     * 根据查询条件查询发票结果，从视图中查询
     * @param m
     * @return
     * @throws SQLException
     */
    public List<ApInvoiceView> list(Page<ApInvoiceView> page,Map m) throws SQLException{
    	List<ApInvoiceView> list = new ArrayList<ApInvoiceView>();
    	Connection conn = this.getSession().connection();
    	String sql = "select * from accounting.v_ap_invoice_list a where 1= 1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_invoiceNo")!=null && Tools.String2Integer(m.get("filter_LIKES_invoiceNo").toString())!=0 ){
    			sql += " and a.invoice_id ="+m.get("filter_LIKES_invoiceNo") ;
    		}
    		if(m.get("filter_LIKES_invoiceId")!=null && Tools.String2Integer(m.get("filter_LIKES_invoiceId").toString())!=0 ){
    			sql += " and a.invoice_id ="+Tools.String2Integer(m.get("filter_LIKES_invoiceId").toString());
    		}
     		if(m.get("filter_EQI_orderNo")!=null && Tools.String2Integer(m.get("filter_EQI_orderNo").toString())!=0 ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_vendorNo")!=null && Tools.String2Integer(m.get("filter_EQI_vendorNo").toString())!=0  ){
    			sql += " and a.vendor_no ="+m.get("filter_EQI_vendorNo");
    		}
     		if(m.get("filter_EQI_shipmentId")!=null && Tools.String2Integer(m.get("filter_EQI_shipmentId").toString())!=0  ){
    			sql += " and a.shipment_id = "+m.get("filter_EQI_shipmentId");
    		}
     		if(m.get("filter_EQS_status")!=null && !m.get("filter_EQS_status").equals("") ){
    			sql += " and a.status='"+m.get("filter_EQS_status")+"'";
    		}
     		if(m.get("filter_GTD_invoiceDate")!=null && !m.get("filter_GTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date>='"+m.get("filter_GTD_invoiceDate")+"'";
    		}
    		if(m.get("filter_LTD_invoiceDate")!=null && !m.get("filter_LTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date<='"+m.get("filter_LTD_invoiceDate")+"'";
    		}
    		if(m.get("filter_EQS_currency")!=null && !m.get("filter_EQS_currency").equals("") ){
    			sql += " and a.currency ='"+m.get("filter_EQS_currency")+"'";
    		}
    	}else{
			sql += " and a.status !='"+Constant.STATUS_OVERDUE+"'";
		}
    	sql += " order by a.invoice_id desc ";
    	long start =( page.getPageNo()-1) * page.getPageSize();
		sql += " limit "+start+", "+page.getPageSize();
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	ApInvoiceView  a = null;
    	while(rs.next()){
    		a = new ApInvoiceView();
    		a.setInvoiceId(rs.getInt("invoice_id"));
    	    a.setInvoiceNo(rs.getString("invoice_no"));
    	    a.setStatus(rs.getString("status"));
    	    a.setPaidAmt(rs.getFloat("paid_amt"));
    	    a.setBalance(rs.getFloat("balance"));
    	    a.setOrderNo(rs.getInt("order_no"));
    	    a.setShipmentId(rs.getInt("shipment_id"));
    	    a.setVendorNo(rs.getInt("vendor_no"));
    	    a.setInvoiceType(rs.getString("invoice_type"));
    	    a.setInvoiceDate(new java.util.Date(rs.getTimestamp("invoice_date").getTime()));
    	    a.setTotalAmount(rs.getFloat("total_amount"));
    	    a.setCurrency(rs.getString("currency"));
    	    a.setSymbol(rs.getString("symbol"));
    	    a.setPrecision(rs.getInt("precision"));
    	    list.add(a);
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	return list;
    }
    
    public long getTotalPage(Map m){
    	long total= 0;
    	String sql = "select count(*) from accounting.v_ap_invoice_list a where 1= 1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_invoiceNo")!=null && !m.get("filter_LIKES_invoiceNo").equals("") ){
    			sql += " and a.invoice_no like '%"+m.get("filter_LIKES_invoiceNo")+"%'";
    		}
    		if(m.get("filter_LIKES_invoiceId")!=null && Tools.String2Integer(m.get("filter_LIKES_invoiceId").toString())!=0 ){
    			sql += " and a.invoice_id ="+Tools.String2Integer(m.get("filter_LIKES_invoiceId").toString());
    		}
     		if(m.get("filter_EQI_orderNo")!=null && Tools.String2Integer(m.get("filter_EQI_orderNo").toString())!=0 ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_vendorNo")!=null && Tools.String2Integer(m.get("filter_EQI_vendorNo").toString())!=0  ){
    			sql += " and a.vendor_no ="+m.get("filter_EQI_vendorNo");
    		}
     		if(m.get("filter_EQI_shipmentId")!=null && Tools.String2Integer(m.get("filter_EQI_shipmentId").toString())!=0  ){
    			sql += " and a.shipment_id = "+m.get("filter_EQI_shipmentId");
    		}
     		if(m.get("filter_EQS_status")!=null && !m.get("filter_EQS_status").equals("") ){
    			sql += " and a.status='"+m.get("filter_EQS_status")+"'";
    		}
     		if(m.get("filter_GTD_invoiceDate")!=null && !m.get("filter_GTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date>='"+m.get("filter_GTD_invoiceDate")+"'";
    		}
    		if(m.get("filter_LTD_invoiceDate")!=null && !m.get("filter_LTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date<='"+m.get("filter_LTD_invoiceDate")+"'";
    		}
    		if(m.get("filter_EQS_currency")!=null && !m.get("filter_EQS_currency").equals("") ){
    			sql += " and a.currency ='"+m.get("filter_EQS_currency")+"'";
    		}
    	}else{
			sql += " and a.status !='"+Constant.STATUS_OVERDUE+"'";
		}
    	List list = this.getSession().createSQLQuery(sql).list();
    	total = Long.parseLong(list.get(0).toString());
    	return total;
    }

	public Page<ApInvoice> queryInvoices(Page<ApInvoice> page,
			List<PropertyFilter> srch) {
    	Page<ApInvoice>  result=this.findPage(page, srch);  	
    	
    	return result;
	}

	public int getInvoiceIdByNo(String invoiceNo) {
	  	String hql = "from ApInvoice a where a.invoiceNo = ?";
    	List list = this.find(hql,invoiceNo);
    	if(list == null || list.size() == 0){
    		return 0;
    	}    	
    	ArInvoice arInvoice=(ArInvoice)list.get(0);    	
    	return arInvoice.getInvoiceId();
	}

    /**
	 * @function  根据唯一的发票编号查询Invoice实体
	 * @param     invoiceNo
	 * @return    true 表示存在该发票号，false表示不存在
	 */
    public ApInvoice getInvoiceByNo(String invoiceNo){
    	String hql = "from ApInvoice a where a.invoiceNo = ?";
    	List<ApInvoice> list = this.find(hql,invoiceNo);
    	if(list == null || list.size() == 0){
    		return new ApInvoice();
    	}    	
    	ApInvoice apInvoice=list.get(0);    	
    	return apInvoice;
    }

    /**
     * 根据order_NO查询出所有的invoice
     * @param page
     * @param filters
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map searchInvoiceList(Page<ApInvoice> page,int orderNo,String invoiceNo) {
		Map m = new HashMap();
		String hql = " from ApInvoice a where a.orderNo = "+orderNo ;
		if(invoiceNo != null && !invoiceNo.trim().equals("")){
			hql += " and a.invoiceNo like '%"+invoiceNo+"%'";
		}
		hql += " order by a.invoiceId desc ";
		List list1 = this.getSession().createQuery(hql)
		                              .setFirstResult (( page.getPageNo()-1) * page.getPageSize() )
		                              .setMaxResults(page.getPageSize())
		                              .list();//获取结果
		hql = "select count(a) from ApInvoice a where a.orderNo = "+orderNo;
		if(invoiceNo != null && !invoiceNo.trim().equals("")){
			hql += " and a.invoiceNo like '%"+invoiceNo+"%'";
		}
		Object obj = this.getSession().createQuery(hql).uniqueResult();//获取数量
		long total = Long.parseLong(obj.toString());
		m.put("total", total);
		m.put("list", list1);
		return m;
	}

	/**
	 * 检查是否对过账
	 * @param invoiceId
	 * @return
	 */
	public boolean isAllocation(int invoiceId) {
	      boolean flag = false;
	      String sql = " from ApTransactionAllocation a where a.invoiceId = "+invoiceId;
	      List list =  this.getSession().createQuery(sql).list();
	      if(list != null && list.size()>0){
	        flag = true;
	      }
	      return flag;
	}
	
	/**
	 * 通过orderNo 获取companyId
	 * @param orderNo
	 * @return
	 */
	public int getCompanyId(int orderNo){
		 return (Integer)findUnique("select order.companyId from PurchaseOrder order where order.orderNo= ?", orderNo);
	}
    
	/**
	 * 获取通过order带过来的信息
	 * @param orderid
	 * @return
	 */
    public Map getInfoByOrder(int orderid){
        Map m = new HashMap();
    	String sql = "select system.users.login_name ,system.users.user_id ,purchase.purchase_orders.currency ,purchase.purchase_orders.ship_amt from   system.users right join purchase.purchase_orders on   system.users.user_id = purchase.purchase_orders.purchase_contact where purchase.purchase_orders.order_no= "+orderid;
    	List list = this.getSession().createSQLQuery(sql).list();
    	if(list.size()>0){
    	Object[] obj = (Object[]) list.get(0);
    	m.put("name",obj[0]== null ? "" :obj[0].toString());
    	m.put("saleId", obj[1] == null ? "0" : obj[1].toString());
    	m.put("currency", obj[2]==null ? "USD" :obj[2].toString());
    	if(obj[3]==null){
    		m.put("amt", 0);
    	}else{
    	m.put("amt", obj[3].toString());
    	}
    	sql= "select c.symbol from PbCurrency c where c.currencyCode=?";
    	String symbol = findUnique(sql, m.get("currency"));//货币符号
    	m.put("symbol", symbol);
    	}else{
    		System.out.println("没有改用户");
    	   	m.put("name","");
        	m.put("saleId", "");
        	m.put("currency", "");
        	m.put("symbol", "");
    	}
    	return m;
    }
    
    
    public void updateInvoiceState(int invoiceId){
    	this.getSession().createQuery("update ApInvoice a set a.state = '"+Constant.STATUS_CLOSED+"' where a.invocieId="+invoiceId).executeUpdate();
    }
}
