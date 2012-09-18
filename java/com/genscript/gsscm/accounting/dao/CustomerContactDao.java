package com.genscript.gsscm.accounting.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;

@Repository
public class CustomerContactDao extends HibernateDao<CustomerContactHistory, Integer>  {

	/**
	 * 获取用户最后一次发送的过期发票邮件
	 * @return
	 */
	
	public CustomerContactHistory getLastest(int custNo){
		List<CustomerContactHistory> list = null; 
		list = this.getSession().createQuery("from CustomerContactHistory c where c.custNo = ? and c.contentType='Invoice Overdue Message' order by c.contactId desc")
		                  .setParameter(0, custNo)
		                  .setFirstResult(0)
		                  .setMaxResults(1)
		                  .list();
	return list.get(0);
	}

	
//	public int insert(CustomerContactHistory c) throws SQLException{
//		int r = 0;
//		Connection conn = this.getSession().connection();
//		String sql = "insert into ";
//		conn.close();
//		return r;
//	}
//	
}
