package com.genscript.gsscm.accounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ArInvoiceLine;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;
import com.genscript.gsscm.accounting.entity.OrderItem;
import com.genscript.gsscm.accounting.util.Tools;


/**
 * @function 发票的R
 * @version  1.0
 * @auther   陈文擎
 * @date     2010-11-11
 *  
 */


@Repository
public class ArInvoiceLineDao extends HibernateDao {

	/**
	 * 根据orderNo 查询结果
	 * @return
	 * @throws SQLException 
	 */
	public List<OrderItem> queryInvoiceByOrderNo(int orderNo) throws SQLException{
		List<OrderItem> list = new ArrayList<OrderItem>();
		OrderItem o = null;
//		orderNo = 33588;
//		String hql = " from OrderItem o where o.orderNo = ?";
//	    list = this.getSession().createQuery(hql)
//		.setParameter(0, orderNo)
//		.list();
		
String sql = "SELECT order.order_items.order_no, order.order_items.item_no,order.order_items.catalog_no,order.order_items.name,shipping.ship_package_lines.quantity,shipping.ship_package_lines.qty_uom,shipping.ship_package_lines.size,shipping.ship_package_lines.size_uom,order.order_items.unit_price,order.order_items.amount * (shipping.ship_package_lines.quantity * shipping.ship_package_lines.size) / (order.order_items.quantity * order.order_items.size) amount,  "+
" order.order_items.discount * (shipping.ship_package_lines.quantity *shipping.ship_package_lines.size) / (order.order_items.quantity *order.order_items.size) discount,order.order_items.tax * (shipping.ship_package_lines.quantity *  shipping.ship_package_lines.size) / (order.order_items.quantity * order.order_items.size) tax ,order.order_items.creation_date,order.order_items.created_by,order.order_items.modify_date,order.order_items.modified_by " +
" FROM order.order_items,shipping.ship_package_lines,shipping.ship_packages WHERE ship_packages.package_id = ship_package_lines.package_id " +
" AND order_items.order_no = ship_package_lines.order_no AND order_items.item_no = ship_package_lines.item_no AND ship_packages.status = 'Shipped' AND ship_packages.invoiced_flag = 'N' AND order_items.order_no =  "+orderNo;
		Connection conn = this.getSession().connection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			o = new OrderItem();
			o.setOrderNo(rs.getInt("order_no"));
			o.setItemNo(rs.getInt("item_no"));
			o.setCatalogNo(rs.getString("catalog_no"));
			o.setName(rs.getString("name"));
			o.setQuantity(rs.getInt("quantity"));
			o.setQtyUom(rs.getString("qty_uom"));
			o.setSize(rs.getFloat("size"));
			o.setSizeUom(rs.getString("size_uom"));
			o.setUnitPrice(rs.getFloat("unit_price"));
			o.setAmount(rs.getFloat("amount"));
			o.setDiscount(rs.getFloat("discount"));
			o.setTax(rs.getFloat("tax"));
			o.setCreationDate(new java.util.Date(rs.getTimestamp("creation_date").getTime()));
			o.setCreatedBy(rs.getInt("created_by"));
			o.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date").getTime()));
			o.setModifiedBy(rs.getInt("modified_by"));
			list.add(o);
		}
		rs.close();
		ps.close();
        conn.close();
		return list;
	}
	
	
	public List<OrderItem> queryAutoInvoiceByOrderNo(int orderNo) throws SQLException{
		List<OrderItem> list = new ArrayList<OrderItem>();
		OrderItem o = null;	
	
		
		
		String sql = "select a.order_no,a.item_no,a.qty_uom,a.quantity,a.size,a.size_uom,a.created_by,a.creation_date,a.modified_by,a.modify_date,"+
		" b.catalog_no ,b.name,b.unit_price,b.amount,b.discount,b.tax " +
		"  from  shipping.ship_package_lines  a inner join  order.order_items b on a.order_no=b.order_no  and a.item_no=b.item_no  where   a.order_no= " +
		orderNo;
				Connection conn = this.getSession().connection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					o = new OrderItem();
					o.setOrderNo(rs.getInt("order_no"));
					o.setItemNo(rs.getInt("item_no"));
					o.setCatalogNo(rs.getString("catalog_no"));
					o.setName(rs.getString("name"));
					o.setQuantity(rs.getInt("quantity"));
					o.setQtyUom(rs.getString("qty_uom"));
					o.setSize(rs.getFloat("size"));
					o.setSizeUom(rs.getString("size_uom"));
					o.setUnitPrice(rs.getFloat("unit_price"));
					o.setAmount(rs.getFloat("amount"));
					o.setDiscount(rs.getFloat("discount"));
					o.setTax(rs.getFloat("tax"));
					o.setCreationDate(new java.util.Date(rs.getTimestamp("creation_date").getTime()));
					o.setCreatedBy(rs.getInt("created_by"));
					o.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date").getTime()));
					o.setModifiedBy(rs.getInt("modified_by"));
					list.add(o);
				}
				rs.close();
				ps.close();
		        conn.close();
				return list;
	}
	
	
	/**
	 * 根据invoiceId来查询结果
	 * @return
	 */
	public List<ArInvoiceLine> queryInvoiceByInvoiceId(int invoiceId){
		List<ArInvoiceLine> list = null;
		list = this.getSession().createCriteria(ArInvoiceLine.class).add(Expression.eq("invoiceId",invoiceId)).list();
		return list;
	}
	
	
	
	/**
	 * 根据invoiceId来查询结果
	 * @return
	 */
	public  ArInvoiceLine  queryInvoiceLine(int invoiceLineId){
		List<ArInvoiceLine> list = null;
		ArInvoiceLine ar = (ArInvoiceLine) this.getSession().get(ArInvoiceLine.class, invoiceLineId);		
		return ar;
	}
	
	
 
	
	/**
	 * 根据invoiceId来查询出所有的invoiceLine的数量，用于分页
	 * @param page
	 * @param invoiceId
	 * @return
	 */
	public long getTotalByInvoiceId(Page<ArInvoiceLine> page,int invoiceId){
		List<ArInvoiceLine> list = null;
		String hql = "select count(a) from ArInvoiceLine a where a.invoiceId = ? ";
		list = this.getSession().createQuery(hql)
                                .setParameter(0, invoiceId)
                                .setFirstResult( (page.getPageNo()-1)*page.getPageSize() )
    	                        .setMaxResults( page.getPageSize() )
                                .list();
		if(list.size()>0){
		return Long.parseLong(list.get(0).toString());
		}else{
			return 0;
		}
	}
	
	/**
	 * @function 根据invoiceId及 invoiceLineId批量删除invoiceline 
	 * @param InvoiceId
	 * @param InvoiceLineIds
	 * @return
	 */
	public int DeleteBatch(String InvoiceId,String InvoiceLineIds)
	{
		int result=0;
		String hql = "delete from accounting.ar_invoice_lines   where    invoice_line_id not in("+InvoiceLineIds+") and invoice_id ="+InvoiceId;		
// 		result=this.getSession().createSQLQuery(hql).setParameter(1, InvoiceId).setParameter(0, InvoiceLineIds).executeUpdate();
//		Connection conn = this.getSession().connection();
//		PreparedStatement ps = conn.prepareStatement(hql);
//		conn.close();
		result=this.getSession().createSQLQuery(hql).executeUpdate();
		return result; 
	}
	
	
	/**
	 * 修改invoice line前进行检查，判断qty或者size是否超标
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public OrderItem queryOrderItem(int orderNo,int itemNo){
		List list = 
		this.getSession().createSQLQuery("SELECT size,quantity from order.order_items  o where o.order_no = ? and item_no = ? ")
		                 .setParameter(0, orderNo)
		                 .setParameter(1, itemNo)
		                 .list();
		if(list.size()>0){
			Object[] obj = (Object[])list.get(0);
			OrderItem o = new OrderItem();
			o.setSize(Tools.String2Float( obj[0].toString()) );
			o.setQuantity(Tools.String2Integer(obj[1].toString()));
			return o;
		}else{
			return null;
		}
	
	}
	
	

}
