package com.genscript.gsscm.accounting.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.dto.ArPaymentAmountDTO;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.order.entity.ExchRateByDate;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.common.util.DateUtils;

	/**
	 * @function 发票的CURD
	 * @version  1.0
	 * @auther   陈文擎
	 * @date     2010-11-11
	 *  
	 */


@Repository
public class ArInvoiceDao extends HibernateDao<ArInvoice, Integer> {

	
	
	private static final String AMOUNT_BY_ORDER = "SELECT ar_transaction_allocation.apply_amount,ar_invoices.currency FROM accounting.ar_transaction_allocation, accounting.ar_invoices WHERE ar_invoices.invoice_id = ar_transaction_allocation.invoice_id AND ar_invoices.order_no=:orderNo";
	
	@SuppressWarnings("unchecked")
	public List<ArPaymentAmountDTO> getAmountByOrder(Integer orderNo){
		List<ArPaymentAmountDTO> amountList = new ArrayList<ArPaymentAmountDTO>();
		List list = this.getSession().createSQLQuery(AMOUNT_BY_ORDER).setInteger("orderNo", orderNo).list();
		for(int i=0; i<list.size(); i++){
			Object[] objs = (Object[]) list.get(i);
			BigDecimal amount = (BigDecimal) objs[0];
			String currency = (String) objs[1];
			ArPaymentAmountDTO amountDTO = new ArPaymentAmountDTO();
			amountDTO.setAmount(amount);
			amountDTO.setCurrency(currency);
			amountList.add(amountDTO);
		}
		return amountList;
	}
	
	
	/**
	 * @function  检查发票编号是否存在
	 * @param     invoiceNo
	 * @return    true 表示存在该发票号，false表示不存在
	 */
    public boolean checkInvoiceNoExists(String invoiceNo){
    	
    	boolean flag = true;
    	String hql = "from ArInvoice a where a.invoiceNo = ?";
    	List list = this.find(hql,invoiceNo);
    	if(list == null || list.size() == 0){
    		flag = false;
    		
    	}
    	return flag;
    }
    
    /**
	 * @function  检查发票编号是否存在
	 * @param     invoiceNo
	 * @return    true 表示存在该发票号，false表示不存在
	 */
    public boolean checkInvoiceNoExists(ArInvoice entity){
    	boolean flag = true;
    	
    	String hql = "from ArInvoice a where a.invoiceNo = ? and a.invoiceId!=?";
    	List list = this.find(hql,entity.getInvoiceNo(),entity.getInvoiceId());
    	if(list == null || list.size() == 0){
    		flag = false;
    	}
    	return flag;
    }
    
    /**
	 * @function  根据唯一的发票编号查询InvoiceId
	 * @param     invoiceNo
	 * @return    true 表示存在该发票号，false表示不存在
	 */
    public int getInvoiceIdByNo(String invoiceNo){
    	
    	String hql = "from ArInvoice a where a.invoiceNo = ?";
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
    public ArInvoice getInvoiceByNo(String invoiceNo){
    	 
    	
    	String hql = "from ArInvoice a where a.invoiceNo = ?";
    	List list = this.find(hql,invoiceNo);
    	if(list == null || list.size() == 0){
    		return new ArInvoice();
    	}    	
    	ArInvoice arInvoice=(ArInvoice)list.get(0);    	
    	return arInvoice;
    }
    
    /**
	 * @function  根据唯一的发票编号查询Invoice实体
	 * @param     invoiceNo
	 * @return    true 表示存在该发票号，false表示不存在
	 */
    public ArInvoice getInvoiceById(String invoiceId){
    	 
    	
    	String hql = "from ArInvoice a where a.invoiceId = ?";
    	List list = this.find(hql,Tools.String2Integer(invoiceId));
    	if(list == null || list.size() == 0){
    		return new ArInvoice();
    	}    	
    	ArInvoice arInvoice=(ArInvoice)list.get(0);    	
    	return arInvoice;
    }
    
    /**
     * 根据查询条件查询发票结果，从视图中查询
     * @param m
     * @return
     * @throws SQLException
     */
    public List<ArInvoiceView> list(Page<ArInvoiceView> page,Map m) throws SQLException{
    	List<ArInvoiceView> list = new ArrayList<ArInvoiceView>();
    	Connection conn = this.getSession().connection();
    	String sql = "select * from accounting.v_ar_invoice_list a where 1= 1 ";
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
     		if(m.get("filter_EQI_custNo")!=null && Tools.String2Integer(m.get("filter_EQI_custNo").toString())!=0  ){
    			sql += " and a.cust_no ="+m.get("filter_EQI_custNo");
    		}
     		if(m.get("filter_EQI_shipmentId")!=null && Tools.String2Integer(m.get("filter_EQI_shipmentId").toString())!=0  ){
    			sql += " and a.shipment_id = "+m.get("filter_EQI_shipmentId");
    		}
     		if(m.get("filter_EQS_status")!=null && !m.get("filter_EQS_status").equals("") ){
    			sql += " and a.status='"+m.get("filter_EQS_status")+"'";
    		}
     		if(m.get("filter_EQS_currency")!=null && !m.get("filter_EQS_currency").equals("") ){
    			sql += " and a.currency='"+m.get("filter_EQS_currency")+"'";
    		}
     		if(m.get("filter_GTD_invoiceDate")!=null && !m.get("filter_GTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date>='"+m.get("filter_GTD_invoiceDate")+"'";
    		}
    		if(m.get("filter_LTD_invoiceDate")!=null && !m.get("filter_LTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date<='"+m.get("filter_LTD_invoiceDate")+"'";
    		}
    	}else{
//			sql += " and a.status !='"+Constant.STATUS_OVERDUE+"'";
		}
    	sql += " order by a.invoice_id desc ";
    	long start =( page.getPageNo()-1) * page.getPageSize();
		sql += " limit "+start+", "+page.getPageSize();
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	ArInvoiceView  a = null;
    	while(rs.next()){
    		a = new ArInvoiceView();
    		a.setInvoiceId(rs.getInt("invoice_id"));
    	    a.setInvoiceNo(rs.getString("invoice_no"));
    	    a.setStatus(rs.getString("status"));
    	    a.setPaidAmt(rs.getFloat("paid_amt"));
    	    a.setBalance(rs.getFloat("balance"));
    	    a.setOrderNo(rs.getInt("order_no"));
    	    a.setShipmentId(rs.getInt("shipment_id"));
    	    a.setCustNo(rs.getInt("cust_no"));
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
    	String sql = "select count(*) from accounting.v_ar_invoice_list a where 1= 1 ";
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
     		if(m.get("filter_EQI_custNo")!=null && Tools.String2Integer(m.get("filter_EQI_custNo").toString())!=0  ){
    			sql += " and a.cust_no ="+m.get("filter_EQI_custNo");
    		}
     		if(m.get("filter_EQI_shipmentId")!=null && Tools.String2Integer(m.get("filter_EQI_shipmentId").toString())!=0  ){
    			sql += " and a.shipment_id = "+m.get("filter_EQI_shipmentId");
    		}
     		if(m.get("filter_EQS_status")!=null && !m.get("filter_EQS_status").equals("") ){
    			sql += " and a.status='"+m.get("filter_EQS_status")+"'";
    		}
     		if(m.get("filter_EQS_currency")!=null && !m.get("filter_EQS_currency").equals("") ){
    			sql += " and a.currency='"+m.get("filter_EQS_currency")+"'";
    		}
     		if(m.get("filter_GTD_invoiceDate")!=null && !m.get("filter_GTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date>='"+m.get("filter_GTD_invoiceDate")+"'";
    		}
    		if(m.get("filter_LTD_invoiceDate")!=null && !m.get("filter_LTD_invoiceDate").equals("") ){
    			sql += " and a.invoice_date<='"+m.get("filter_LTD_invoiceDate")+"'";
    		}
    	}else{
//			sql += " and a.status !='"+Constant.STATUS_OVERDUE+"'";
		}
    	List list = this.getSession().createSQLQuery(sql).list();
    	total = Long.parseLong(list.get(0).toString());
    	return total;
    }
    
   /**
    * @从表 Ar_Invoices 中按条件查询数据
    * @param page
    * @param filters
    * @return
    */
    public Page<ArInvoice> queryInvoices(Page<ArInvoice> page, List<PropertyFilter> filters){
    	
    	Page<ArInvoice>  result=this.findPage(page, filters);  	
    	
    	return result;
    }
    
    /**
     * 查询过期发票,关联customer、order、invoice表
     * @return
     */
    public Page<ArInvoice> queryOverDueInvoice(Page<ArInvoice> page){
    	Page<ArInvoice>  result = page;
        Long total = 0l;
         List list = this.getSession().createQuery("select count(a) from ArInvoice a where a.status in ('New','In Progress','Overdue') and a.exprDate <= ? ").setDate(0, new java.util.Date()).list();
        if(list!=null && list.size()>0){
    		total = Long.parseLong(list.get(0).toString());
    	}
    	//String hql = "from ArInvoice a where a.status in ('New','In Progress','Overdue') and a.exprDate <= ? "; //a.invoice_id,a.invoice_no,a.order_no,a.comment,a.expr_date
    	String sql = "select a.invoice_id,a.invoice_no,a.order_no,a.comment,a.expr_date,b.order_date,c.bus_email,c.cust_no from accounting.ar_invoices a,order.order b,customer.customer_addresses c " +
    			" where a.order_no = b.order_no and c.cust_no = b.cust_no and c.addr_type = 'CONTACT' and a.status in ('New','In Progress','Overdue') and a.expr_date <= '"+Tools.getCurrentTime("yyyy-MM-dd")+"'  order by invoice_id desc";
       System.out.println(sql);
    	List list1 = this.getSession().createSQLQuery(sql)
    	                                         .setFirstResult( (page.getPageNo()-1)*page.getPageSize() )
    	                                         .setMaxResults( page.getPageSize() )
    	                                         .list();
    
       List<ArInvoice> list2 = new ArrayList<ArInvoice>();
       ArInvoice ar = null;
       for(int i=0;i<list1.size();i++){
    	   ar = new ArInvoice();
    	   Object[] obj = (Object[])list1.get(i);
    	   ar.setInvoiceId((Integer)obj[0]);
    	   ar.setInvoiceNo(obj[1] == null ? "": obj[1].toString());
    	   ar.setOrderNo((Integer)obj[2]);
    	   ar.setComment(obj[3].toString());
    	   ar.setExprDate((Date)obj[4]);
    	   ar.setOrderDate((Date)obj[5]);
    	   ar.setBusEmail(obj[6].toString());
    	   ar.setCustNo(Tools.String2Integer((obj[7].toString()) ) );
    	   list2.add(ar);
       }
    
       page.setTotalCount(total);
       page.setResult(list2);
    	return result;
    }
//    
//    /**
//     * swg 2010-11-16:逻辑删除发票
//     * @param entitys
//     * @return
//     * modified by zhouyong
//     */
//    public boolean doDelete(List<ArInvoice> entitys){
//    	for(int i=0; i<entitys.size(); i++){
//    		ArInvoice entity = entitys.get(i);
//    		if(entity != null){
//    			if(entity.getOldStatus().equals(Constant.STATUS_INPROCESS)){
////    				this.getSession().createSQLQuery(" delete  from  accounting.ar_transaction_allocation  where accounting.ar_transaction_allocation.invoice_id = ?").setParameter(0, entity.getInvoiceId()).executeUpdate();
//    				//如果未对过账，则执行更新，否则不能更新
//    				boolean flag = isAllocation(entity.getInvoiceId()); 
//    				if(flag){
//    					return false;
//    				}else{
////    					this.getItemNum(entity.getInvoiceId(), entity.getOrderNo(), entity.geti);//进行check接口检查
//    					List<ArInvoiceLine> list = null;
//    					list = this.getSession().createCriteria(ArInvoiceLine.class).add(Expression.eq("invoiceId",entity.getInvoiceId())).list();
//                        for(ArInvoiceLine a : list){
//                        	
//                        }
//    					this.getSession().update(entity);
//    				}
//    			}else{
//        		this.getSession().update(entity);
//    			}
//        		this.getSession().flush();
//        	}else return false;
//    	}
//    	
//        return true;	    	
//    }
    
    
    /**
     * 根据查询条件查询发票结果，从视图中查询
     * @param m
     * @return
     * @throws SQLException
     */
    public List<ArInvoiceView> list(Map m) throws SQLException{
    	List<ArInvoiceView> list = new ArrayList<ArInvoiceView>();
    	Connection conn = this.getSession().connection();
//    	String sql = "select * from accounting.invoiceView a where 1= 1 ";
    	String sql = "select * from accounting.v_ar_invoice_list a where 1= 1 ";
    	if(m!=null){
    		if(m.get("filter_LIKES_invoiceNo")!=null && !m.get("filter_LIKES_invoiceNo").equals("") ){
    			sql += " and a.invoice_no like '%"+m.get("filter_LIKES_invoiceNo")+"%'";
    		}
     		if(m.get("filter_EQI_orderNo")!=null && !m.get("filter_EQI_orderNo").equals("") ){
    			sql += " and a.order_no ="+m.get("filter_EQI_orderNo");
    		}
     		if(m.get("filter_EQI_custNo")!=null && !m.get("filter_EQI_custNo").equals("") ){
    			sql += " and a.cust_no ="+m.get("filter_EQI_custNo");
    		}
     		if(m.get("filter_EQI_shipmentId")!=null && !m.get("filter_EQI_shipmentId").equals("") ){
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
    		 
    	}
    	PreparedStatement ps = null;
    		ResultSet rs = null;
    	ArInvoiceView  a = null;
    	try{
    	ps = conn.prepareStatement(sql);
    	rs =ps.executeQuery();
    	while(rs.next()){
    		a = new ArInvoiceView();
    		a.setInvoiceId(rs.getInt("invoice_id"));
    	    a.setInvoiceNo(rs.getString("invoice_no"));
    	    a.setStatus(rs.getString("status"));
    	    a.setPaidAmt(rs.getFloat("paid_amt"));
    	    a.setBalance(rs.getFloat("balance"));
    	    a.setOrderNo(rs.getInt("order_no"));
    	    a.setShipmentId(rs.getInt("shipment_id"));
    	    a.setCustNo(rs.getInt("cust_no"));
    	    a.setInvoiceType(rs.getString("invoice_type"));
    	    a.setInvoiceDate(new java.util.Date(rs.getTimestamp("invoice_date").getTime()));
    	    a.setTotalAmount(rs.getFloat("total_amount"));
    	    a.setCurrency(rs.getString("currency"));
    	    list.add(a);
    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    	rs.close();
    	ps.close();
    	conn.close();
    	}
    	return list;
    }
    
    /**
     * 通过id获取用户名称和id
     * @param id
     * @return
     */
    public Map getUserName(int orderid){
        Map m = new HashMap();
    	String sql = "select system.users.login_name ,system.users.user_id ,order.order.order_currency ,order.order.ship_amt from   system.users right join order.order on  system.users.user_id = order.order.sales_contact  where order.order.order_no ="+orderid;
    	List list = this.getSession().createSQLQuery(sql).list();
    	if(list.size()>0){
    	Object[] obj = (Object[]) list.get(0);
    	m.put("name",obj[0] == null ? "" : obj[0].toString());
    	m.put("saleId", obj[1] == null ? "0" : obj[1].toString());
    	m.put("currency",obj[2] == null ? "USD" : obj[2].toString());
    	m.put("amt", obj[3] == null ? "0.0" : obj[3].toString());
    	}else{
    		System.out.println("没有查询到该用户信息");
        	m.put("name","");
        	m.put("saleId", "");
        	m.put("currency","");
        	m.put("amt","");
    	}
    	return m;
    }
    
    /**
     * 通过userId获取username，在invoice中显示
     * @param userId
     * @return
     */
    public String getLoginNameById(int userId){
    	String name = "";
    	String sql = "select login_name from system.users where user_id = "+userId;
    	Object obj = this.getSession().createSQLQuery(sql).uniqueResult();
    	name = obj== null?"":obj.toString();
    	return name;
    }
    
    /**
     * 判断改发票是否已经对过账
     * @param invoiceId 发票Id
     * @return true 表明已经对过账，否则未对过账
     */
    public boolean isAllocation(int invoiceId){
      boolean flag = false;
      String sql = " select * from accounting.ar_transaction_allocation where invoice_id = "+invoiceId;
      List list =  this.getSession().createSQLQuery(sql).list();
      if(list != null && list.size()>0){
        flag = true;
      }
      return flag;
    }

    /**
     * 根据order_NO查询出所有的invoice
     * @param page
     * @param filters
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map searchInvoiceList(Page<ArInvoice> page,int orderNo,String invoiceNo) {
		Map m = new HashMap();
		String hql = " from ArInvoice a where a.orderNo = "+orderNo ;
		if(invoiceNo != null && !invoiceNo.trim().equals("")){
			hql += " and a.invoiceNo like '%"+invoiceNo+"%'";
		}
		hql += " order by a.invoiceId desc ";
		List list1 = this.getSession().createQuery(hql)
		                              .setFirstResult (( page.getPageNo()-1) * page.getPageSize() )
		                              .setMaxResults(page.getPageSize())
		                              .list();//获取结果
		hql = "select count(a) from ArInvoice a where a.orderNo = "+orderNo;
		if(invoiceNo != null && !invoiceNo.trim().equals("")){
			hql += " and a.invoiceNo like '%"+invoiceNo+"%'";
		}
		Object obj = this.getSession().createQuery(hql).uniqueResult();//获取数量
		long total = Long.parseLong(obj.toString());
		m.put("total", total);
		m.put("list", list1);
		return m;
	}
	
	public Map getItemNum(int orderNo, int itemNo)
	{
		Map result=new HashMap();
		String sql="  select sum(qty) as qty ,sum(size) as size ,count(qty) as num from accounting.ar_invoice_lines a "+
		           " inner  join   accounting.ar_invoices b on a.invoice_id=b.invoice_id"
			      +" where  "
		          +" a.order_no="+orderNo 
		          +" and "
		          +" a.item_no="+itemNo
		          +" and "
		          +" b.status in ('"+Constant.STATUS_INPROCESS+"','"+Constant.STATUS_CLOSED+"','"+Constant.STATUS_COMPLETED+"')"
		          +"  group by a.item_no";
		try{
			List list = this.getSession().createSQLQuery(sql).list();
			
			
			result.put("qty", 0);
			result.put("size", 0.0f); 
			
			if(list.size()>0)
			{			 
				Object[] obj = (Object[]) list.get(0);
				int qty = Tools.String2Integer(obj[0].toString());
				float size = Tools.String2Float(obj[1].toString());	
				int num = Tools.String2Integer(obj[2].toString());
				if(num==qty)
				qty=1;
				result.put("qty", qty);
				result.put("size", size); 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.put("qty", Tools.String2Integer("0"));
			result.put("size", Tools.String2Float("0")); 
		}
		
		return result;
	}
	
	/**
	 * 将状态自动更改为overdue
	 * @return
	 * @throws SQLException 
	 * @throws SQLException 
	 */
	public int  overDue(Connection conn) throws SQLException{
		int r = 0;
		String sql = "update accounting.ar_invoices set accounting.ar_invoices.status = '"+Constant.STATUS_OVERDUE+"' where accounting.ar_invoices.status in ('"+Constant.STATUS_NEW+"', '"+Constant.STATUS_INPROCESS+"' ) and accounting.ar_invoices.expr_date < '"+Tools.getCurrentTime("yyyy-MM-dd")+"'";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			System.out.println(sql);
			r = ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(ps!=null){
				ps.close();
				ps = null;
			}
		}
		return r;
	}

	
	/**
	 * 获取汇率 通过invoiceId获取orderno，再获取order表中的
	 * 原理： 首先查询是否有直接匹配的汇率，如果没有取得该货币对美圆的汇率，然后再用美圆找到要转化的汇率，因为每种货币都有对美圆的汇率，如果找不到提示信息给
	 * @return
	 */
	public double getCurrency(ArInvoice oldInvoice,String fromCurrency,String toCurrency,Date d){
		ExchRateByDate e = new ExchRateByDate();
		double rate = 0;
		//取得直接匹配的汇率
		String hql = "from ExchRateByDate e where e.fromCurrency = ? and toCurrency = ?  and e.effFrom <= ? and effTo >=? order by e.creationDate desc";
		List<ExchRateByDate> list = this.getSession().createQuery(hql)
		                             .setParameter(0, fromCurrency)
		                             .setParameter(1, toCurrency)
		                             .setParameter(2, d)
		                             .setParameter(3, d)
		                             .list();
		if(list.size()>0){
		   e = list.get(0);
		   rate = e.getRate();
		}else{
			//找不到直接匹配的汇率,先找到对美元的汇率
			 hql = "from ExchRateByDate e where e.fromCurrency = ? and toCurrency = 'USD'  and e.effFrom <= ? and effTo >=?  order by e.creationDate desc ";
		     list = this.getSession().createQuery(hql)
             .setParameter(0, fromCurrency)
             .setParameter(1, d)
		     .setParameter(2, d)
             .list();
		     double f1 = 0;
		     double f2 = 0;
			 if(list.size()>0){
				  e = list.get(0);
				  f1 = e.getRate();
				  hql = "from ExchRateByDate e where e.fromCurrency = 'USD' and toCurrency = ?  and e.effFrom <= ? and effTo >=?  order by e.creationDate desc ";
				  list = this.getSession().createQuery(hql)
		             .setParameter(0, toCurrency)
		             .setParameter(1, d)
				     .setParameter(2, d)
		             .list();  
				  if(list.size()>0){
				  f2 = list.get(0).getRate();
				  rate = f1 * f2;
				  }else{
					  rate = 0;
				  }
			 }else{
				return 0; //找不到对应美元的汇率，返回0 提示给用户
			 }
		}
		DecimalFormat df2  = new DecimalFormat("###.000");//保留3为精度
		String s = df2.format(rate);
		if(s.indexOf(".")==0){
			s = "0"+s;
		}
		rate = Double.parseDouble(s);
		return rate;
	}
	
	
	public void update(ArInvoice invoice){
		this.getSession().update(invoice);
	}
	
	
	/**
	 * 通过orderNo 获取companyId
	 * @param orderNo
	 * @return
	 */
	public int getCompanyId(int orderNo){
		return (Integer)findUnique("select order.gsCoId from OrderMain order where order.orderNo= ?", orderNo);
	}
	
	public OrderAddress getOrderAddress(int orderNo, String addrType){
		String hql = "from OrderAddress a where a.orderNo=" + orderNo + " and addrType='"+addrType+"'";
		return (OrderAddress)this.getSession().createQuery(hql).uniqueResult();
	}
	
	public Organization getOrganization(int orgId){
		String hql = "from Organization a where a.orgId=" + orgId;
		return (Organization)this.getSession().createQuery(hql).uniqueResult();
	}
	
	public PaymentVoucher getPaymentVoucher(int orderNo){
		String hql = "from ArPaymentVoucher a where a.orderNo=" + orderNo;
		return (PaymentVoucher)this.getSession().createQuery(hql).uniqueResult();
	}
	
	public String getCreditCardText(String ccType){
		String sql = "select text from common.dropdown_list_options where value = '" + ccType + "'";
		List list = this.getSession().createSQLQuery(sql).list();
		if(list.size() > 0){
			return list.get(0).toString();
		}else{
			return "";
		}
	}
	
	public Map<String,String> getTrackingInfo(String invoiceId){
		String sql = "select c.shipment_date, d.carrier, c.tracking_no " +
			"from accounting.ar_invoice_lines a " +
			"left join shipping.ship_package_lines b on a.order_no=b.order_no and a.item_no=b.item_no " +
			"left join shipping.ship_packages c on b.package_id=c.package_id and c.status='Shipped' " +
			"left join shipping.ship_method d on c.ship_method=d.method_id " +
			"order by c.shipment_date desc";
		List list = this.getSession().createSQLQuery(sql).list();
		Map<String,String> map = new HashMap<String,String>();
		if(list.size() > 0){
			Object[] objs = (Object[])list.get(0); 
			map.put("shippingDate", objs[0].toString());
			map.put("carrier", objs[1].toString());
			map.put("trackingNo", objs[2].toString());
			return map;
		}else{
			map.put("shippingDate", "N/A");
			map.put("carrier", "");
			map.put("trackingNo", "");
			return map;
		}
	}
	
	public Map<String,String> getTermsAndOrderDate(int orderNo){
		String sql = "select distinct b.name, a.order_date from order.order a " +
			"left join customer.payment_terms b on a.payment_term=b.term_id " +
			"where order_no="+orderNo;
		List list = this.getSession().createSQLQuery(sql).list();
		Map<String,String> map = new HashMap<String,String>();
		if(list.size() > 0){
			Object[] objs = (Object[])list.get(0); 
			map.put("terms", objs[0].toString());
			map.put("orderDate", objs[1].toString());
			return map;
		}else{
			map.put("terms", "");
			map.put("orderDate", "");
			return map;
		}
	}
	
	
	public void updatePrintFlag(String[] ids){
		String hql = "update ArInvoice a set a.printedFlag = 'Y' where a.invoiceId in (";
		for(int i=0;i<ids.length;i++){
			hql += ids[i]+",";
		}
		hql += ")";
		hql = hql.replace(",)", ")");
		System.out.println(hql);
		this.getSession().createQuery(hql).executeUpdate();
	}


	public void updateInvoiceState(int invoiceId) {
		this.getSession().createQuery("update ArInvoice a set a.state = '"+Constant.STATUS_CLOSED+"' where a.invoiceId="+invoiceId).executeUpdate();		
	}
	
	public float getPaidAmount(int invoiceId){
		String hql = " select sum(apply_amount) from accounting.ar_transaction_allocation a where a.invoice_id ="+invoiceId;
		List list = this.getSession().createSQLQuery(hql).list();
		return Tools.String2Float(list.get(0).toString());
	}
	
//	/**
//	 * 
//	 * @author fangquan
//	 * 2011-12-02
//	 * 根据cust_No号查询付款期限
//	 *
//	 */
	public Integer getDutDays(Integer cust_No){
		
		String hql="select dueDays from PaymentTerm where termId=(select c.prefPaymentTerm from Customer c where c.custNo="+cust_No+")";
		return this.findUnique(hql);
	}
	

	/**
	 * fangquan
	 * 2011-12-08
	 * 根据日期条件返回客户ID和余额
	 */
	 
	public List<Object[]> getCustomerIdOrBalance(String dutDays,String startBalance,String endBalance,String custNo){
		String hql="select a.custNo, sum(a.balance) from ArInvoice a where a.status='"+Constant.STATUS_OVERDUE+"'";
		//dutDays=0是All，1是<120,2是>=120
		if(dutDays.equals("<120")){
			hql+=" and to_days(now())-to_days(a.exprDate)<120 ";
		}else if(dutDays.equals(">=120")){
			hql+=" and to_days(now())-to_days(a.exprDate)>=120 ";
		}
		//判断客户号
		if(!"".equals(custNo)){
			hql+=" and a.custNo like '%"+custNo+"%'";
		}
		//判断余额
		if(!"".equals(startBalance)){
			hql+=" and a.balance>="+startBalance+"";
		}
		if( !"".equals(endBalance)){
			hql+=" and a.balance<="+endBalance+"";
		}
		hql+=" group by a.custNo";
		return this.find(hql);
	}
	
	/**
	 * fangquan
	 * 2011-12-09
	 * 根据customerNo查询过期invoiceNo号和公司ID
	 */
	
	public List<String[]> getInvoiceNo(Integer custNo){
		String hql="select a.invoiceNo,a.companyId  from ArInvoice a where a.custNo="+custNo+" and a.status='"+Constant.STATUS_OVERDUE+"' group by a.invoiceNo,a.companyId,a.status ";
		return this.find(hql);
	}
	
	/**
	 * fangquan
	 * 2011-12-13
	 * 根据invoiceNo和modifyDate判断invoice是否存在
	 */
	
	public Integer isCheckInvoice(String invoiceNo,Date modifyDate){
		String date=DateUtils.formatDate2Str(modifyDate);
		String hql="select a.invoiceId from ArInvoice a  where a.invoiceNo='"+invoiceNo+"' and  a.modifyDate='"+date+"'";
		return this.findUnique(hql);
	}

	
	
}
