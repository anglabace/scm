package com.genscript.gsscm.olddb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.olddb.entity.Charger;

@Repository
public class ChargerDao extends HibernateDao<Charger, Integer> {

	@SuppressWarnings("deprecation")
	public String getAuthNo(String ccNumber, String ccHolder, Date tranDate){
		Connection conn = null;
		PreparedStatement ps = null;
		String authNo = "";
		String tranDateStr = DateUtils.formatDate2Str(tranDate, "yyyy-MM-dd");
		try {
			conn = getSession().connection();
			String sql = "select authno from olddb.charger where cc_number=" + ccNumber + " and cc_holder=" + ccHolder + "  and authno like 'AUTH%' and to_days(now()) - to_days(" + tranDateStr +")<7";
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				authNo = rs.getString("authno");
				
			}
			// int count = rs.getInt(1);
			ps.executeBatch();
			rs.close();
			conn.commit();
			return authNo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authNo;
	}
}
