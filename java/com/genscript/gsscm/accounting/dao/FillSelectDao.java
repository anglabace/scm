package com.genscript.gsscm.accounting.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContentTemplate;

/**
 * 填充下拉选择框数据
 * @author 周勇
 *
 */
@Repository
public class FillSelectDao extends HibernateDao{

	/**
	 * 根据传过来的sql 填充下拉选矿数据
	 * @param sql
	 * @return
	 */
	public List<Map> getInvoiceState(String sqlField){
		List<Map> list = new ArrayList<Map>();
		String sql = "";
		if(sqlField.equals("PAYMENTTERM")){
		sql = "select name,term_id from customer.payment_terms";
		}
		if(sqlField.equals("EMAILADDRESS")){
			
		}
		if(sqlField.equals("CURRENCY")){
			sql = "select  common.currency.currency_code,  common.currency.currency_code l from   common.currency";
		}
		List list2 = this.getSession().createSQLQuery(sql).list();
		Map m = null;
		for(int i=0;i<list2.size();i++){
			Object[] obj = (Object[]) list2.get(i);
			m = new HashMap();
			m.put("text", obj[0]);
			m.put("value", obj[1]);
			list.add(m);
		}
		return list;
	}
	
	public List<Map> getSelect(String sql){
		List<Map> list = new ArrayList<Map>();
		List list2 = this.getSession().createSQLQuery(sql).list();
		Map m = null;
		for(int i=0;i<list2.size();i++){
			Object[] obj = (Object[]) list2.get(i);
			m = new HashMap();
			m.put("text", obj[0]);
			m.put("value", obj[1]);
			list.add(m);
		}
		return list;
	}
	
	/**
	 * 根据实体填充下拉选矿数据
	 * @return
	 */
	public List<Map> queryEntity(){
		List<Map> list = new ArrayList<Map>();
		List<ContentTemplate> list2 = this.getSession().createQuery("from ContentTemplate a where a.type = 'Invoice Overdue Message' ").list();
		Map m = null;
		for(int i=0;i<list2.size();i++){
			m = new HashMap();
			m.put("text", list2.get(i).getName());
			m.put("value", list2.get(i).getContent());
			list.add(m);
		}
		return list;
	}
}
