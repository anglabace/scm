package com.genscript.gsscm.accounting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ApInvoiceView;
import com.genscript.gsscm.accounting.entity.ArInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.entity.AuthorizeAllocationView;

@Repository
public class ArInvoiceAllocationDao extends
		HibernateDao<ArTransactionAllocation, Integer> {

	/**
	 * 查询状态为Unapproved 并且transcation!=draft的allocation
	 * 
	 * @return
	 */
	public Page<AuthorizeAllocationView> queryUnapproviedList(
			Page<AuthorizeAllocationView> page, Map m) {
		List<AuthorizeAllocationView> list = null;


//		String hql = " from AuthorizeAllocationView a where 1=1 ";
		String hql = "SELECT a.*,u.login_name FROM accounting.v_ar_allocation a LEFT join system.users u ON a.created_by = u.user_id ";
		       hql += " where  a.transaction_type='Check Payment'  and a.status = 'Unapproved' "; 
		String hql2 = "";

		if (m.get("transactionNo") != null
				&& !m.get("transactionNo").equals("")) {
//			hql2 += " and a.transactionId = " + m.get("transactionNo");
			hql2 += " and a.transaction_id = " + m.get("transactionNo");
		}
		if (m.get("invoiceNo") != null && !m.get("invoiceNo").equals("")) {
//			hql2 += " and a.invoiceId = " + m.get("invoiceNo");
			hql2 += " and a.invoice_id = " + m.get("invoiceNo");
		}
		if (m.get("custNo") != null && !m.get("custNo").equals("")) {
//			hql2 += " and a.custNo = " + m.get("custNo");
			hql2 += " and a.cust_no = " + m.get("custNo");
		}
		if (m.get("transcationType") != null
				&& !m.get("transcationType").equals("")) {
//			hql2 += " and a.transcationType = '" + m.get("transcationType")+"'";
			hql2 += " and a.transaction_type = '" + m.get("transcationType")+"'";
		}
		
//		list = this.getSession().createQuery(hql + hql2 +" order by a.id desc ").setFirstResult(
//				(page.getPageNo() - 1) * page.getPageSize()).setMaxResults(
//				page.getPageSize()).list();
		
		list = this.getSession().createSQLQuery(hql + hql2 +" order by a.id desc ")
		.addEntity(AuthorizeAllocationView.class)
		.setFirstResult(
		(page.getPageNo() - 1) * page.getPageSize()).setMaxResults(
		page.getPageSize()).list();
		
        long total = 0l;
        List l2 = this.getSession().createSQLQuery(" select count(*) from accounting.v_ar_allocation a where 1=1 and a.transaction_type='Check Payment'  and a.status = 'Unapproved' "+ hql2).list();
        total =Long.parseLong(l2.get(0).toString());
        page.setResult(list);
		page.setTotalCount(total);
		return page;
	}

}
