package com.genscript.gsscm.olddb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.olddb.entity.PeptideBasePrice;

@Repository
public class PeptideBasePriceDao extends HibernateDao<PeptideBasePrice, Integer> {

	public Float getUnitPrice(String purity, String quantity, String sqllen){
		Float price = 0.0f;
		Integer aa = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getSession().connection();
			String sql = "select unit_price unitPrice from olddb.peptide_base_price where purity = '" + purity + "' and quantity = '" + quantity +"' "+ sqllen;
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getFloat("unitPrice");
				
			}
			// int count = rs.getInt(1);
			ps.executeBatch();
			rs.close();
			conn.commit();
			return price;
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
		return null;
	}
	
	public Float getUnitCost(String purity, String quantity, String sqllen){
		Float price = 0.0f;
		Integer aa = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getSession().connection();
			String sql = "select unit_cost unitCost from olddb.peptide_base_price where purity = '" + purity + "' and quantity = '" + quantity +"' "+ sqllen;
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getFloat("unitCost");
				
			}
			// int count = rs.getInt(1);
			ps.executeBatch();
			rs.close();
			conn.commit();
			return price;
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
		return null;
	}
}
