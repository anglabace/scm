package com.genscript.gsscm.accounting.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.dto.ApInvoicePaymentDTO;
import com.genscript.gsscm.accounting.entity.ApInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ApInvoicePayment;
import com.genscript.gsscm.accounting.entity.ApInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.ApTransactionAllocation;
import com.genscript.gsscm.accounting.entity.ArInvoicePaymentView;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;

	/**
	 * @function invoice payment transaction
	 * @version  1.0
	 * @auther   swg
	 * @date     2010-11-16
	 *  
	 */


@Repository
public class ApInvoicePaymentDao extends HibernateDao<ApInvoicePayment, Integer> {
	
	public String apTransactionsViewSql 
	= "select t.*, u.login_name created_by_name, p.apply_amount, i.invoice_id, i.invoice_no, i.order_no, al.id, c.first_name, c.last_name, d.symbol from accounting.ap_transactions t "
		+"left join "
		+"( "       
			+"select ta.transaction_id, sum(ta.apply_amount) as apply_amount " 
			+"from accounting.ap_transaction_allocation ta "  
			+"group by ta.transaction_id " 
		+") p on t.transaction_id = p.transaction_id "
		+"left join "
		+"( "
			+"select transaction_id, min(id) id, invoice_id " 
			+"from accounting.ap_transaction_allocation " 
			+"group by transaction_id " 
		+") al on t.transaction_id = al.transaction_id "
		+"left join accounting.ap_invoices i on al.invoice_id = i.invoice_id "
		+"left join system.users u on t.created_by=u.user_id "
		+"left join customer.v_all_customers c on t.vendor_no=c.cust_no "
		+"left join common.currency d on t.currency=d.currency_code "; 

    /**
     * 根据查询条件查询发票交易结果
     * @param m
     * @return
     * @throws SQLException
     */
    public List<ApInvoicePaymentView> list(Page<ApInvoicePaymentView> page,Map m) throws SQLException{
    	List<ApInvoicePaymentView> list = new ArrayList<ApInvoicePaymentView>();
    	Connection conn = this.getSession().connection();
    	String sql = "select * from (" + apTransactionsViewSql + ") a where 1=1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_transactionNo")!=null && !m.get("filter_LIKES_transactionNo").equals("") ){
    			sql += " and a.transaction_id ="+m.get("filter_LIKES_transactionNo");
    		}
     		if(m.get("filter_EQI_orderNo")!=null && !m.get("filter_EQI_orderNo").equals("") ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_vendorNo")!=null && !m.get("filter_EQI_vendorNo").equals("") ){
    			sql += " and a.vendor_no ="+m.get("filter_EQI_vendorNo");
    		}
     		if(m.get("filter_EQS_status")!=null && !m.get("filter_EQS_status").equals("") ){
    			sql += " and a.status='"+m.get("filter_EQS_status")+"'";
    		}
     		if(m.get("filter_GTD_transactionDate")!=null && !m.get("filter_GTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date>='"+m.get("filter_GTD_transactionDate")+"'";
    		}
    		if(m.get("filter_LTD_transactionDate")!=null && !m.get("filter_LTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date<='"+m.get("filter_LTD_transactionDate")+"'";
    		}
    	}
    	sql +=" order by a.transaction_id desc ";
		long start =( page.getPageNo()-1) * page.getPageSize();
		sql += " limit "+start+", "+page.getPageSize();
//     System.out.println(sql); 	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	ApInvoicePaymentView  a = null;
    	while(rs.next()){
    		a = new ApInvoicePaymentView();
    		a.setAccountName(rs.getString("account_name"));
    		a.setAccountNo(rs.getString("account_no"));
    		a.setAmount(rs.getFloat("amount"));
    		a.setBalance(rs.getFloat("balance"));
    		a.setCcCardHolder(rs.getString("cc_card_holder"));
    		a.setCcCvc(rs.getString("cc_cvc"));
    		a.setCcExpiration(rs.getString("cc_expiration"));
    		a.setCcType(rs.getString("cc_type"));
    		a.setChkNo(rs.getString("chk_no"));
    		a.setCreatedBy(rs.getInt("created_by"));
    		a.setCreatedByName(rs.getString("created_by_name"));
    		a.setCreationDate(new java.util.Date(rs.getTimestamp("creation_date").getTime()));
    		a.setCurrency(rs.getString("currency"));
    		a.setVendorNo(rs.getInt("vendor_no"));
    		a.setDescription(rs.getString("description"));
    		a.setModifiedBy(rs.getInt("modified_by"));
    		a.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date").getTime()));
    		a.setPaymentType(rs.getString("payment_type"));
    		a.setRoutingNo(rs.getString("routing_no"));
    		a.setStatus(rs.getString("status"));
    		a.setTenderType(rs.getString("tender_type"));
    		a.setTransactionDate(new java.util.Date(rs.getTimestamp("transaction_date").getTime()));
    		a.setTransactionFee(rs.getString("transaction_fee"));
    		a.setTransactionId(rs.getInt("transaction_id"));
    		a.setTransactionNo(rs.getString("transaction_no"));
    		a.setTransactionType(rs.getString("transaction_type"));
    		a.setInvoiceId(rs.getInt("invoice_id"));
    		a.setInvoiceNo(rs.getString("invoice_no"));
    		a.setOrderNo(rs.getString("order_no"));
    		a.setApplyAmount(rs.getFloat("apply_amount"));
    		a.setFirstName(rs.getString("first_name"));
    		a.setLastName(rs.getString("last_name"));
    		a.setSymbol(rs.getString("symbol"));
    	    list.add(a);
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	return list;
    }
    
    public long getTotalPage(Map m){
    	long total= 0;
    	String sql = "select count(*) from (" + apTransactionsViewSql + ") a where 1=1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_transactionNo")!=null && !m.get("filter_LIKES_transactionNo").equals("") ){
    			sql += " and a.transaction_no like '%"+m.get("filter_LIKES_transactionNo")+"%'";
    		}
     		if(m.get("filter_EQI_orderNo")!=null && !m.get("filter_EQI_orderNo").equals("") ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_vendorNo")!=null && !m.get("filter_EQI_vendorNo").equals("") ){
    			sql += " and a.vendor_no ="+m.get("filter_EQI_vendorNo");
    		}
     		if(m.get("filter_EQS_status")!=null && !m.get("filter_EQS_status").equals("") ){
    			sql += " and a.status='"+m.get("filter_EQS_status")+"'";
    		}
     		if(m.get("filter_GTD_transactionDate")!=null && !m.get("filter_GTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date>='"+m.get("filter_GTD_transactionDate")+"'";
    		}
    		if(m.get("filter_LTD_transactionDate")!=null && !m.get("filter_LTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date<='"+m.get("filter_LTD_transactionDate")+"'";
    		}
    	}

    	List list = this.getSession().createSQLQuery(sql).list();
    	total = Long.parseLong(list.get(0).toString());
    	return total;
    }
    
    /**
     * swg 2010-11-17:逻辑删除发票交易记录
     * @param entitys
     * @return
     */
    public boolean doDelete(List<ApInvoicePayment> entitys){
    	for(int i=0; i<entitys.size(); i++){
    		ApInvoicePayment entity = entitys.get(i);
    		if(entity != null){
        		this.getSession().update(entity);
        		this.getSession().flush();
        	}else return false;
    	}
    	
        return true;	    	
    }
    
    /**
     * 根据ID查询发票交易记录
     * @param id
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SQLException
     */
    public ApInvoicePaymentDTO getById(int id) throws IllegalAccessException, InvocationTargetException, SQLException{
    	ApInvoicePaymentDTO apInvoicePaymentDto = new ApInvoicePaymentDTO();
    	Connection conn = this.getSession().connection();
    	String sql = "select * from (" + apTransactionsViewSql + ") a where transaction_id="+id;
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()){
    		apInvoicePaymentDto = new ApInvoicePaymentDTO();
    		apInvoicePaymentDto.setAccountName(rs.getString("account_name"));
    		apInvoicePaymentDto.setAccountNo(rs.getString("account_no"));
    		apInvoicePaymentDto.setAmount(rs.getString("amount"));
    		apInvoicePaymentDto.setBalance(rs.getString("balance"));
    		apInvoicePaymentDto.setCcCardHolder(rs.getString("cc_card_holder"));
    		apInvoicePaymentDto.setCcCvc(rs.getString("cc_cvc"));
    		apInvoicePaymentDto.setCcExpiration(rs.getString("cc_expiration"));
    		apInvoicePaymentDto.setCcType(rs.getString("cc_type"));
    		apInvoicePaymentDto.setChkNo(rs.getString("chk_no"));
    		apInvoicePaymentDto.setCreatedBy(rs.getInt("created_by"));
    		apInvoicePaymentDto.setCreatedByName(rs.getString("created_by_name"));
    		apInvoicePaymentDto.setCreationDate(new java.util.Date(rs.getTimestamp("creation_date").getTime()));
    		apInvoicePaymentDto.setCurrency(rs.getString("currency"));
    		apInvoicePaymentDto.setVendorNo(rs.getInt("vendor_no"));
    		apInvoicePaymentDto.setDescription(rs.getString("description"));
    		apInvoicePaymentDto.setModifiedBy(rs.getInt("modified_by"));
    		apInvoicePaymentDto.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date").getTime()));
    		apInvoicePaymentDto.setPaymentType(rs.getString("payment_type"));
    		apInvoicePaymentDto.setRoutingNo(rs.getString("routing_no"));
    		apInvoicePaymentDto.setStatus(rs.getString("status"));
    		apInvoicePaymentDto.setTenderType(rs.getString("tender_type"));
    		apInvoicePaymentDto.setTransactionDate(new java.util.Date(rs.getTimestamp("transaction_date").getTime()));
    		apInvoicePaymentDto.setTransactionFee(rs.getString("transaction_fee"));
    		apInvoicePaymentDto.setTransactionId(rs.getInt("transaction_id"));
    		apInvoicePaymentDto.setTransactionNo(rs.getString("transaction_no"));
    		apInvoicePaymentDto.setTransactionType(rs.getString("transaction_type"));
    		apInvoicePaymentDto.setInvoiceId(rs.getInt("invoice_id"));
    		apInvoicePaymentDto.setInvoiceNo(rs.getString("invoice_no"));
    		apInvoicePaymentDto.setOrderNo(rs.getString("order_no"));
    		apInvoicePaymentDto.setApplyAmount(rs.getString("apply_amount"));
    		apInvoicePaymentDto.setStatusUpdReason(rs.getString("status_upd_reason"));
    		apInvoicePaymentDto.setFirstName(rs.getString("first_name"));
    		apInvoicePaymentDto.setLastName(rs.getString("last_name"));
    		apInvoicePaymentDto.setSymbol(rs.getString("symbol"));
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	
    	return apInvoicePaymentDto;
    }
    
    /**
     * 保存或修改发票交易记录
     * @param entity
     * @return
     */
    public boolean saveOrUpdate(ApInvoicePayment entity){
    	if(entity != null){
    		this.getSession().saveOrUpdate(entity);
    		this.getSession().flush();
    		return true;
    	}else return false;
    }
    
    /**
     * 保存或修改发票交易付款记录
     * @param entity
     * @return
     */
    public boolean saveAllocation(ApTransactionAllocation entity){
    	if(entity != null){
    		this.getSession().saveOrUpdate(entity);
    		this.getSession().flush();
    		return true;
    	}else return false;
    }
    
    public boolean checkTransactionNoExist(String transactionNo) throws IllegalAccessException, InvocationTargetException, SQLException{
    	int count = 0;
    	Connection conn = this.getSession().connection();
    	String sql = "select count(*) as count from accounting.ap_transactions where transaction_no='"+transactionNo + "'";
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	
    	while(rs.next()){
    		count = rs.getInt("count");
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	
    	if(count > 0)return true;
		else return false;
    }
    
    /**
     * 查询发票交易付款记录
     * @param m
     * @return
     * @throws SQLException
     */
    public List<ApInvoiceAllocationView> allocationList(Map m) throws SQLException{
    	List<ApInvoiceAllocationView> list = new ArrayList<ApInvoiceAllocationView>();
    	Connection conn = this.getSession().connection();
    	String sql = "select * from accounting.ap_transaction_allocation_view a where a.transaction_id="+m.get("transactionId");
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	ApInvoiceAllocationView  a = null;
    	while(rs.next()){
    		a = new ApInvoiceAllocationView();
    		a.setId(rs.getInt("id"));
    		a.setCreatedBy(rs.getInt("created_by"));
    		a.setCreationDate(new java.util.Date(rs.getTimestamp("creation_date").getTime()));
    		a.setModifiedBy(rs.getInt("modified_by"));
    		a.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date").getTime()));
    		a.setTransactionId(rs.getInt("transaction_id"));
    		a.setTransactionNo(rs.getString("transaction_no"));
    		a.setInvoiceId(rs.getInt("invoice_id"));
    		a.setInvoiceNo(rs.getString("invoice_no"));
    		a.setOrderNo(rs.getString("order_no"));
    		a.setApplyAmount(rs.getString("apply_amount"));
    	    list.add(a);
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	return list;
    }
    
    public List<Map> getInvoiceList(int transactionId) throws SQLException{
    	List<Map> list = new ArrayList<Map>();
    	Connection conn = this.getSession().connection();
    	String sql = "select i.invoice_id, i.invoice_no, i.order_no from accounting.ap_transaction_allocation a, accounting.ap_invoices i "
    				+"where a.invoice_id=i.invoice_id and a.transaction_id="+transactionId;
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()){
    		Map tempMap = new HashMap(); 
    		tempMap.put("invoiceId", rs.getInt("invoice_id"));
    		tempMap.put("invoiceNo", rs.getInt("invoice_no"));
    		tempMap.put("orderNo", rs.getInt("order_no"));
    	    list.add(tempMap);
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	return list;
    }
    
    public String makeTransactionNo() throws SQLException{
    	String transactionNo = null;
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    	String strdate = simpleDateFormat.format(new Date());
    	Connection conn = this.getSession().connection();
    	String sql = "select max(transaction_no) as transaction_no from accounting.ap_transactions" +
    			" where transaction_no like '" + strdate + "%' "; 
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	while(rs.next()){
    		transactionNo = rs.getString("transaction_no");
    	}
    	int idx = 1;
    	if(transactionNo != null){
    		idx = new Integer(transactionNo.substring(8))+1;
    	}
    	String sinx = ("0000" + idx);
    	transactionNo = strdate + sinx.substring(sinx.length()-4);	

    	return transactionNo;
    }
    
    /**
     * 根据查询条件查询发票交易结果
     * @param m
     * @return
     * @throws SQLException
     */
    public List<ApInvoicePaymentView> AuthorizationList(Page<ApInvoicePaymentView> page,Map m) throws SQLException{
    	List<ApInvoicePaymentView> list = new ArrayList<ApInvoicePaymentView>();
    	Connection conn = this.getSession().connection();
    	String sql = "select * from (" + apTransactionsViewSql+ ") a where 1=1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_transactionNo")!=null && !m.get("filter_LIKES_transactionNo").equals("") ){
    			sql += " and a.transaction_id = "+m.get("filter_LIKES_transactionNo");
    		}
     		if(m.get("filter_EQI_orderNo")!=null && !m.get("filter_EQI_orderNo").equals("") ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_vendorNo")!=null && !m.get("filter_EQI_vendorNo").equals("") ){
    			sql += " and a.vendor_no ="+m.get("filter_EQI_vendorNo");
    		}
     		
     		if(m.get("filter_GTD_transactionDate")!=null && !m.get("filter_GTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date>='"+m.get("filter_GTD_transactionDate")+"'";
    		}
    		if(m.get("filter_LTD_transactionDate")!=null && !m.get("filter_LTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date<='"+m.get("filter_LTD_transactionDate")+"'";
    		}
    		
    		if(m.get("filter_EQS_transactionType")!=null && !m.get("filter_EQS_transactionType").equals("") ){
    			sql += " and a.transaction_type ='"+m.get("filter_EQS_transactionType")+"'";
    		}
    		 
    	    sql += " and (a.status='"+Constant.STATUS_DRAFT+"'  or  a.payment_type='"+Constant.PAYMENTTYPE_CHARGED+"' )";
    		 
    	    sql +=" order by transaction_date desc ";
    	    
    		long start =( page.getPageNo()-1) * page.getPageSize();
    		sql += " limit "+start+", "+page.getPageSize();
     		
    	}
//   System.out.println(sql); 	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	ApInvoicePaymentView  a = null;
    	while(rs.next()){
    		a = new ApInvoicePaymentView();
    		a.setAccountName(rs.getString("account_name"));
    		a.setAccountNo(rs.getString("account_no"));
    		a.setAmount(Tools.String2Float(rs.getString("amount")));
    		a.setBalance(Tools.String2Float(rs.getString("balance")));
    		a.setCcCardHolder(rs.getString("cc_card_holder"));
    		a.setCcCvc(rs.getString("cc_cvc"));
    		a.setCcExpiration(rs.getString("cc_expiration"));
    		a.setCcType(rs.getString("cc_type"));
    		a.setVendorNo(Tools.String2Integer(rs.getString("vendor_no")));
    		a.setCreatedBy(rs.getInt("created_by"));
    		a.setCreatedByName(rs.getString("created_by_name"));
    		a.setCreationDate(new java.util.Date(rs.getTimestamp("creation_date").getTime()));
    		a.setCurrency(rs.getString("currency"));
    		a.setChkNo(rs.getString("chk_no"));
    		a.setDescription(rs.getString("description"));
    		a.setModifiedBy(rs.getInt("modified_by"));
    		a.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date").getTime()));
    		a.setPaymentType(rs.getString("payment_type"));
    		a.setRoutingNo(rs.getString("routing_no"));
    		a.setStatus(rs.getString("status"));
    		a.setTenderType(rs.getString("tender_type"));
    		a.setTransactionDate(new java.util.Date(rs.getTimestamp("transaction_date").getTime()));
    		a.setTransactionFee(rs.getString("transaction_fee"));
    		a.setTransactionId(rs.getInt("transaction_id"));
    		a.setTransactionNo(rs.getString("transaction_no"));
    		a.setTransactionType(rs.getString("transaction_type"));
    		a.setInvoiceId(rs.getInt("invoice_id"));
    		a.setInvoiceNo(rs.getString("invoice_no"));
    		a.setOrderNo(rs.getString("order_no"));
    		a.setApplyAmount(rs.getFloat("apply_amount"));
    		a.setFirstName(rs.getString("first_name"));
    		a.setLastName(rs.getString("last_name"));
    		a.setSymbol(rs.getString("symbol"));
    	    list.add(a);
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	return list;
    }
   
    public long getAuthorizationTotalPage(Map m){
    	long total= 0;
    	String sql = "select count(*) from (" + apTransactionsViewSql + ") a where 1=1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_transactionNo")!=null && !m.get("filter_LIKES_transactionNo").equals("") ){
    			sql += " and a.transaction_id = "+m.get("filter_LIKES_transactionNo");
    		}
     		if(m.get("filter_EQI_orderNo")!=null && !m.get("filter_EQI_orderNo").equals("") ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_vendorNo")!=null && !m.get("filter_EQI_vendorNo").equals("") ){
    			sql += " and a.vendor_no ="+m.get("filter_EQI_vendorNo");
    		}
     		 
     		if(m.get("filter_GTD_transactionDate")!=null && !m.get("filter_GTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date>='"+m.get("filter_GTD_transactionDate")+"'";
    		}
    		if(m.get("filter_LTD_transactionDate")!=null && !m.get("filter_LTD_transactionDate").equals("") ){
    			sql += " and a.transaction_date<='"+m.get("filter_LTD_transactionDate")+"'";
    		}
    	   
    		if(m.get("filter_EQS_transactionType")!=null && !m.get("filter_EQS_transactionType").equals("") ){
    			sql += " and a.transaction_type = '"+m.get("filter_EQS_transactionType")+"'";
    		}
    		
    	    sql += " and (a.status='"+Constant.STATUS_DRAFT+"'  or  a.payment_type='"+Constant.PAYMENTTYPE_CHARGED+"' )";
    	}
        System.out.println(sql); 	
    	List list = this.getSession().createSQLQuery(sql).list();
    	total = Long.parseLong(list.get(0).toString());
    	return total;
    }
    
    public void updateStatus(Map paramter)
    {
    	Session session=this.getSession();
    	Transaction tx = session.beginTransaction();

    	String sql="update  accounting.ap_transactions a "
    	+" set  a.status='"+paramter.get("status").toString().trim()+"'";
    	if(null!=paramter.get("reason"))
    	sql=sql+"  ,  a.status_upd_reason='"+paramter.get("reason").toString().trim()+"'";
    	
    	sql=sql+"  where  a.transaction_id in ("+paramter.get("ids").toString().trim()
    	+")";
    	int result=session.createSQLQuery(sql).executeUpdate();
    	tx.commit();
    	session=null;
    	 
    	
    }
    
	/**
	 * 更新balance
	 * @param invoiceNo
	 */
	public void updateBalance(int transcationNo,String balance){
		int r = this.getSession().createQuery("update ApInvoicePayment a set a.balance = ? where a.transactionId = ?")
		.setParameter(0, balance)
		.setParameter(1,transcationNo)
		.executeUpdate();
		System.out.println(r);
	}
	
	public void updatePaymentState(int id) {
		this.getSession().createQuery("update ApInvoicePayment a set a.state = '"+Constant.STATUS_CLOSED+"' where a.transactionId="+id).executeUpdate();
	}

	public BigDecimal getalltotalPaymentsByOrderNo(int parseInt) {
		 String sql="select sum(ap.amount) from ApInvoicePayment ap order by ";
		return null;
	}
}
