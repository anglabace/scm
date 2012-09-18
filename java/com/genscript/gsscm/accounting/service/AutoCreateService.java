package com.genscript.gsscm.accounting.service;

/**
 * @function     自动生成发票 
 * @version      1.0
 * @auther       陈文擎
 * @date         2010-11-30
 *  
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;


import java.text.Annotation;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.hibernate.impl.SessionFactoryImpl;

import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceLineDao;
import com.genscript.gsscm.accounting.dao.AutoCreateDao;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.OrderItem;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.order.service.OrderService;


@Service
@Transactional
public class AutoCreateService {
	
	     

//   private  SessionFactory factory;



    public  void init(SessionFactoryImpl  FactoryBean)
    {
		try {
	       
		 
//			factory = FactoryBean;
		}catch(Exception e){
		e.printStackTrace();
		}
	}
    
    
    
    
    @Autowired
    public  ArInvoiceLineDao Dao;
    
    @Autowired
	OrderService orderService;
    @Autowired
    ArInvoiceDao arInvoiceDao;
    @Autowired
	ArInvoiceLineDao arInvoiceLineDao;
    @Autowired
    AutoCreateDao autoCreateDao;
    
	public  void testCase1()
	{
//		String result =factory.getCurrentSession().toString();
//		System.out.println("<<<<<<<<<<<<<<<<:"+result);
//		System.out.println(">>>>>>>>>>>>>>>>:"+result);
////		ArInvoiceLineDao Dao=new ArInvoiceLineDao();
//		Dao.setSessionFactory(factory);
//		Dao.initEntity(ArInvoiceLine.class);
		try{
			List list=Dao.queryInvoiceByOrderNo(33588);
			int length=list.size();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public  void testCase2()
	{
		
	}
	
	
	public  void CreateService(Connection conn)///////////////////////调用这个方法
	{
		 
		try{
//			conn=arInvoiceDao.getSession().connection();
			conn.setAutoCommit(false);			
			this.CreateALLInvoice(conn);		    
			conn.commit();	    
		    
		}catch(Exception e)
		{
			e.printStackTrace();
			try{ 
			conn.rollback();
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		    
		}finally
		{ try{
			conn.close();
			conn=null;
		     }catch(Exception e)
		     {
		    	 e.printStackTrace();
		     }
		}
	}
	
	public  final static  String  status="Shipped";
	
	/**
	 * @function 通过查询过滤出符合要求的  shipment 
	 * @param    conn
	 * @throws   Exception
	 */
	
	public  void CreateALLInvoice(Connection conn) throws Exception
	{
//		StringBuffer sql=new StringBuffer();
//		sql.append("select   distinct( a.shipment_id ) as shipment_id    from   shipping.ship_packages a where  a.status='Shipped'  ");
//		sql.append("  and  a.invoiced_flag='N' ");
//		PreparedStatement ps= conn.prepareStatement(sql.toString());
//		ResultSet rs=ps.executeQuery();
//		java.util.Vector v = new  java.util.Vector();
//		while(rs.next())
//		{
//			int shipmentId=rs.getInt(1);
//			this.CreateInvoice( shipmentId,conn );
//			v.addElement( new  Integer( shipmentId ));			
//		}
//		rs.close();
//		rs=null;
//		ps.close();
//	    ps=null;
//	    String s = v.toString();
//		s = s.substring(1,s.length()-1);
//		String updateSQL="update  shipping.ship_packages  a  set a.invoiced_flag ='Y'  where a.shipment_id in("+s+")";
//		try{
//		ps= conn.prepareStatement(updateSQL);
//////////////////////////////////////////////////		ps.setObject( 1,v);
//		
//		int rows=ps.executeUpdate();
//		System.out.println("影响的行数 ："+rows);
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		sql=null;
//		ps.close();
//	    ps=null;
	  
	    java.util.Vector v = new  java.util.Vector();
	    List list=autoCreateDao.getshipment();
	    for(Object Element:list)
	    {
	    	int shipmentId=Tools.String2Integer(Element.toString());
			this.CreateInvoice( shipmentId,conn );
			v.addElement( new  Integer( shipmentId ));	
	    }
	    String s = v.toString();
		s = s.substring(1,s.length()-1);
	  String updateSQL="update  shipping.ship_packages  a  set a.invoiced_flag ='Y'  where a.shipment_id in("+s+")";
	  autoCreateDao.updateStatus(updateSQL);	    
	}
	
	public  void  CreateInvoice( int shipmentId,Connection conn ) throws Exception
	{
		StringBuilder sql=new StringBuilder();
		sql.append("select   distinct(a.order_no) from   shipping.ship_package_lines  a    inner join      shipping.ship_packages    b  on  b.package_id= a.package_id   and b.shipment_id =");
		sql.append(shipmentId);
		sql.append("   order by order_no");
		PreparedStatement ps= conn.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int orderNo=rs.getInt(1);
			this.CreateInvoiceLine(shipmentId,orderNo,conn);
			
		}
		sql=null;
		ps.close();
	    ps=null;		
	}
	
	public  void  CreateInvoiceLine( int shipmentId,int orderNo,Connection conn ) throws Exception
	{
//		StringBuilder sql=new StringBuilder();
//		sql.append("select a.* from   shipping.ship_package_lines  a    where a.order_no=");
//		sql.append(orderNo);
//		PreparedStatement ps= conn.prepareStatement(sql.toString());
//		ResultSet rs=ps.executeQuery();
		
		ArInvoice arInvoice=new ArInvoice();		
		
		Map param=AutoCreateService.getOrder(orderNo,conn );
		Date day=new Date();
		arInvoice.setComment("");
		arInvoice.setCreatedBy(0);
		arInvoice.setCreationDate(day);
		if(null==param.get("order_currency"))
		return;
		arInvoice.setCurrency(param.get("order_currency").toString());
		if(null==param.get("cust_no"))
		return;
	    arInvoice.setCustNo((Integer)param.get("cust_no"));
		arInvoice.setDescription("");
		Calendar calendar=Calendar.getInstance();		
		calendar.set(day.getYear()+1900,day.getMonth()+1,day.getDate());
		arInvoice.setExprDate(calendar.getTime());
		arInvoice.setInvoiceDate(day);
		arInvoice.setInvoiceType(Constant.INVOICE_TYPE);
		arInvoice.setModifiedBy(0);
		arInvoice.setModifyDate(day);
		arInvoice.setOrderNo(orderNo);
		arInvoice.setPaidAmount(0);
		if(null== param.get("payment_method"))
		return;
		arInvoice.setPaymentMethod( param.get("payment_method").toString());
		if(null== param.get("payment_term"))
		return;
		arInvoice.setPaymentTerm((Integer)param.get("payment_term"));
		arInvoice.setPrintedFlag("");
		if(null== param.get("sales_contact"))
		return;		
		arInvoice.setSalesContact((Integer)param.get("sales_contact"));
		arInvoice.setShipmentId(shipmentId);
		if(null== param.get("payment_term"))
		return;
		arInvoice.setShipping(Tools.String2Float(param.get("payment_term").toString()) );
		arInvoice.setStatus(Constant.STATUS_INPROCESS);
		int companyId = arInvoiceDao.getCompanyId(orderNo);
		arInvoice.setCompanyId(companyId);
		arInvoice.setSubTotal(0f);
		arInvoice.setTax(0f);
		arInvoice.setTotalAmount(0f);
		arInvoice.setBalance(0f);
		arInvoice.setDiscount(0f);
		
//		ArInvoiceDao arInvoiceDao=new ArInvoiceDao();
//		arInvoiceDao.setSessionFactory(factory);
		
		 
		arInvoiceDao.save(arInvoice);
		
		ArInvoiceLine insertLine=null;
		OrderItem oderItem=null;
//		ArInvoiceLineDao arInvoiceLineDao=new ArInvoiceLineDao();
//		arInvoiceLineDao.setSessionFactory(factory);
//		arInvoiceLineDao.initEntity(ArInvoiceLine.class);
		
		float SubTotal=0;
		float Tax=0;
		float Discount=0;
		
		float TotalAmount=0;
		float Balance=0;
		
		
		List<OrderItem> oderItems=arInvoiceLineDao.queryAutoInvoiceByOrderNo(orderNo);		
		for(int i=0;i<oderItems.size();i++)
		{
			insertLine=new ArInvoiceLine();
			oderItem=oderItems.get(i);
			insertLine.setInvoiceId(arInvoice.getInvoiceId());
			insertLine.setOrderNo(arInvoice.getOrderNo());
			
			insertLine.setAmount( oderItem.getAmount());
			SubTotal=SubTotal+oderItem.getAmount();			
			insertLine.setDiscount(oderItem.getDiscount());	
			Discount=Discount+oderItem.getDiscount();
			insertLine.setTax(oderItem.getTax());
			Tax=Tax+oderItem.getTax();
			
			
			
			insertLine.setQty(oderItem.getQuantity());
			insertLine.setSize(oderItem.getSize());
		
			orderService.uptOrderItemStatus(orderNo, oderItem.getItemNo(), oderItem.getQuantity(),
					oderItem.getSize(), 0, 0,false);//改状态
			
			insertLine.setUnitPrice(oderItem.getUnitPrice());
			insertLine.setCatalogNo(oderItem.getCatalogNo());
			insertLine.setCreatedBy(SessionUtil.getUserId());
			insertLine.setCreationDate(new Date());			
			insertLine.setItemNo(oderItem.getItemNo());			 
			insertLine.setLineNo(i+1);
			insertLine.setModifiedBy(SessionUtil.getUserId());
			insertLine.setModifyDate(new Date());
			insertLine.setName(oderItem.getName());			
			insertLine.setQtyUom(oderItem.getQtyUom());			
			insertLine.setSizeUom("");			
			insertLine.setStatus(Constant.STATUS_INPROCESS);
			arInvoiceLineDao.save(insertLine);
			
		}
		float shipping=arInvoice.getShipping();
		
		arInvoice.setSubTotal(SubTotal);
		arInvoice.setTax(Tax);
		arInvoice.setTotalAmount((shipping+SubTotal+Tax-Discount));
		arInvoice.setBalance((shipping+SubTotal+Tax-Discount));
		arInvoice.setDiscount(Discount);
		arInvoice.setInvoiceNo(""+arInvoice.getInvoiceId());
		
		
		arInvoiceDao.getSession().saveOrUpdate(arInvoice);		
	}
	
	public static Map getOrder(int orderNo,Connection conn) throws Exception
	{
		StringBuilder sql=new StringBuilder();
		sql.append("select  a.sales_contact , a.order_currency,a.ship_amt,a.payment_term ,a.cust_no   ,c.pref_payment_mthd  from  order.order  a  , customer.customer c  where    a.cust_no = c.cust_no    and  a.order_no =");
		sql.append(orderNo);
		Map result=new HashMap();
		PreparedStatement ps= conn.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			 int sales_contact=rs.getInt(1);
			 String order_currency=rs.getString(2);
			 float ship_amt=rs.getFloat(3);
			 int payment_term=rs.getInt(4);
			 int cust_no=rs.getInt(5);
			 String PaymentMethod=rs.getString(6);
			 
			 if(null==order_currency)
				 order_currency="";
			 if(null==PaymentMethod)
				 PaymentMethod="";
			 
			 result.put("sales_contact", sales_contact);
			 result.put("order_currency",order_currency );
			 result.put("ship_amt",ship_amt );
			 result.put("payment_term",payment_term );
			 result.put("cust_no", cust_no);
			 result.put("payment_method", PaymentMethod);
		}
		sql=null;
		rs.close();
		ps.close();
	    ps=null;	
		return result;
	}
	
	
	
	public static void main(String[] agrs)
	{
		Calendar calendar=Calendar.getInstance();
		Date day=new Date();
		calendar.set(day.getYear()+1900,day.getMonth()+1,day.getDate());
		System.out.println(day.toLocaleString());
		System.out.println(calendar.getTime().toLocaleString());
		
	}
	

}
