package com.genscript.gsscm.accounting.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ApInvoiceLine;
import com.genscript.gsscm.accounting.entity.ApInvoiceLineView;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;

@Repository
public class ApInvoiceLineDao extends HibernateDao<ApInvoiceLine,Integer> {

	
	/**
	 * 通过invoiceId获取到InvoiceLine信息
	 * @param invoiceId
	 * @return
	 */
	public List<ApInvoiceLine> queryInvoiceByInvoiceId(int invoiceId){
		List<ApInvoiceLine> list = null;
		list = this.getSession().createCriteria(ApInvoiceLine.class).add(Expression.eq("invoiceId",invoiceId)).list();
		return list;
	}
	
	/**
	 * 根据invoiceLineId来查询invoiceLine
	 * @return
	 */
	public  ApInvoiceLine  queryInvoiceLine(int invoiceLineId){
		List<ApInvoiceLine> list = null;
		ApInvoiceLine ap = (ApInvoiceLine) this.getSession().get(ApInvoiceLine.class, invoiceLineId);		
		return ap;
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
		String hql = "delete from accounting.ap_invoice_lines   where    invoice_line_id not in("+InvoiceLineIds+") and invoice_id ="+InvoiceId;		
		result=this.getSession().createSQLQuery(hql).executeUpdate();
		return result; 
	}

	public List<ApInvoiceLineView> queryInvoiceByOrderNo(int orderNo) throws SQLException {
		List<ApInvoiceLineView> list = null;
//		list = this.getSession().createQuery("from ApInvoiceLineView a where a.orderNo = ?")
//		.setParameter(0, orderNo)
//		.list();
		String sql = "select purchase.purchase_order_items.order_no,purchase.purchase_order_items.item_no ," +
				" purchase.purchase_order_items.catalog_no ,purchase.purchase_order_items.name ," +
				" purchase.purchase_order_items.quantity ,purchase.purchase_order_items.qty_uom ," +
				" purchase.purchase_order_items.size,purchase.purchase_order_items.size_uom ," +
				" purchase.purchase_order_items.unit_price ," +
				" (purchase.purchase_order_items.quantity * purchase.purchase_order_items.unit_price) AS amount," +
				" purchase.purchase_order_items.discount,purchase.purchase_order_items.tax " +
				" from purchase.purchase_order_items where purchase.purchase_order_items.order_no = ?";
		list = this.getSession().createSQLQuery(sql).addEntity(ApInvoiceLineView.class)
		.setParameter(0, orderNo)
		.list();
		return list;
	}

	public PurchaseOrderItem queryOrderItem(int orderNo, int itemNo) {
		String hql = "from PurchaseOrderItem a where a.orderNo = ? and a.itemNo = ?";
		PurchaseOrderItem o =  this.findUnique(hql, orderNo,itemNo);
		return o;
	}
	
	
}
