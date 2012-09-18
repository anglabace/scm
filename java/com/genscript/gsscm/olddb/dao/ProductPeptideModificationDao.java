package com.genscript.gsscm.olddb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.olddb.entity.ProductPeptideModification;

@Repository
public class ProductPeptideModificationDao extends HibernateDao<ProductPeptideModification, Integer>  {
	
	public List<String> getAaPrice(String sqlPrice, String name) {
		List<String> list = new ArrayList<String>();
		Float price = 0.0f;
		Integer aa = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getSession().connection();
			String sql = "select aa, "+ sqlPrice + " sqlPrice from olddb.product_peptide_modification where name = '" + name +"'";
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getFloat("sqlPrice");
				aa = rs.getInt(1);
				list.add(aa.toString());
				list.add(price.toString());
				
			}
			// int count = rs.getInt(1);
			ps.executeBatch();
			rs.close();
			conn.commit();
			return list;
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
		return list;
	}
}
