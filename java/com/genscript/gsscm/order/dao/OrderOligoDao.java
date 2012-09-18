package com.genscript.gsscm.order.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.order.entity.OrderOligo;

@Repository
public class OrderOligoDao extends HibernateDao<OrderOligo, Integer> {

	/**
	 * 通过sequence和backbone调用存储过程获取o_5modification、
	 * o_inter_modification、o_3modification、o_chimeric_bases、o_standard_mixed_mases信息
	 * @author zhang yong
	 * @param sequence
	 * @param backbone
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] getOrderOligoMoreDetail(final String sequence, final String backbone){
		final String[] obj;
		HibernateTemplate hibernateTemplate = new HibernateTemplate(this.sessionFactory);
		obj = (String[]) hibernateTemplate.execute(new HibernateCallback() {
			@SuppressWarnings("deprecation")
			@Override
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Connection conn = s.connection();
				CallableStatement cstmt = conn.prepareCall("{call order.sp_get_order_oligo_more_detail(?,?,?,?,?,?,?)}");
				cstmt.setString(1, sequence);
				cstmt.setString(2, backbone);
				cstmt.registerOutParameter("o_5modification", java.sql.Types.VARCHAR);
				cstmt.registerOutParameter("o_inter_modification", java.sql.Types.VARCHAR);
				cstmt.registerOutParameter("o_3modification", java.sql.Types.VARCHAR);
				cstmt.registerOutParameter("o_chimeric_bases", java.sql.Types.VARCHAR);
				cstmt.registerOutParameter("o_standard_mixed_mases", java.sql.Types.VARCHAR);
				cstmt.execute();
				String[] strs = new String[5];
				strs[0] = cstmt.getString("o_5modification");
				strs[1] = cstmt.getString("o_inter_modification");
				strs[2] = cstmt.getString("o_3modification");
				strs[3] = cstmt.getString("o_chimeric_bases");
				strs[4] = cstmt.getString("o_standard_mixed_mases");
				return strs;
			}
		});
		return obj;
	}
	
	/**
	 * 获取Oligo和Item信息
	 *  @author Zhang Yong
	 *  add date 2011-10-08
	 * @param custNo
	 * @param orderNo
	 * @return
	 */
	public List<Object[]> getOligoInfo(Integer custNo, Integer orderNo) {
		String hql = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if (custNo != null) {
			hql = "SELECT a, b FROM OrderOligo a, OrderItem b, OrderMain c WHERE c.custNo=:custNo AND c.orderNo = b.orderNo AND b.type='"+CatalogType.SERVICE.value()+"' AND b.status !='"+OrderItemStatusType.CN.value()+"' AND b.orderItemId = a.orderItemId ORDER BY b.orderNo, a.orderItemId";
			map.put("custNo", custNo);
		} else if (orderNo != null) {
			hql = "SELECT a, b FROM OrderOligo a, OrderItem b WHERE b.orderNo=:orderNo AND b.type='"+CatalogType.SERVICE.value()+"' AND b.status !='"+OrderItemStatusType.CN.value()+"' AND b.orderItemId = a.orderItemId ORDER BY b.orderNo, a.orderItemId";
			map.put("orderNo", orderNo);
		}
		if (hql != null) {
			return this.find(hql, map);
		}
		return null;
	}
}
