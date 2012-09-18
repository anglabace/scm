package com.genscript.gsscm.tools.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.tools.entity.FollowUp;

@Repository
public class FollowUpDao extends HibernateDao<FollowUp, Integer> {

	private static final String GET_BY_ORDERNO = "FROM FollowUp where orderNo=? and STATUS ='open' ";

	public List<FollowUp> getAllByorderNo(Integer orderNo) {
		return this.find(GET_BY_ORDERNO, orderNo);
	}

	public FollowUp getLastOnByOrderNo1(Integer orderNo) {
		/*
		 * String GET_LAST_ONE =
		 * " select id,order_no,status, next_followup_date, from order.order_followup where order_no ="
		 * + orderNo + "  AND STATUS='OPEN'  ";
		 */

		String GET_LAST_ONE = " from FollowUp where orderNo=? AND STATUS='open'";
		return this.findUnique(GET_LAST_ONE, orderNo);
		/*
		 * Query q = this.getSession().createSQLQuery(GET_LAST_ONE); List l =
		 * q.list(); FollowUp fu = new FollowUp(); if (l != null && l.size() >
		 * 0) { for (int i = 0; i < l.size(); i++) { fu = (FollowUp) l.get(i); }
		 * 
		 * }
		 */
		// return fu;
	}

	public List getLastOnByOrderNo2(Integer orderNo) {
		String sql = "select id, order_no,status, next_followup_date,message,followup_date"
				+ " followup_by,next_followup_task from order.order_followup where status ='open'  and order_no= "
				+ orderNo
				+ " and followup_date =(select max(followup_date) from order.order_followup ) ";
		Query q = this.getSession().createSQLQuery(sql);
		List l = q.list();
		return l;
		/*
		 * String GET_LAST_ONE =
		 * "  from FollowUp  where orderNo =? AND STATUS='OPEN'" +
		 * "and followupDate =(select max(followupDate)  from FollowUp)";
		 */
		// return this.findUnique(GET_LAST_ONE, orderNo);
	}

	public String getptdateStr(Integer orderNo) {
		String sql_target_date = " select   Max(oi.target_date) from order.order_items oi where   "
				+ "oi.status <>'CN' and   oi.order_no ='" + orderNo + "'";
		Query q = this.getSession().createSQLQuery(sql_target_date);
		List l = q.list();
		String str = "";
		if (l != null) {
			if (l.get(0) != null) {
				str = l.get(0).toString();
			}
		}
		System.out.println(str);
		return str;
	}

}
