package com.genscript.gsscm.biolib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.biolib.entity.PeptideAminoAcid;

@Repository
public class PeptideAminoAcidDao extends HibernateDao<PeptideAminoAcid, Integer>{
	
	public float getPrice(String sqlPrice, String code) {
		Float price = 0.0f;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getSession().connection();
			String sql = "select "+ sqlPrice + " sqlPrice from biolib.peptide_amino_acid where code = '" + code +"'";
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getFloat("sqlPrice");
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
		return 0;
	}
}
