package com.genscript.gsscm.accounting.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;

import com.genscript.gsscm.common.util.StringUtil;

public class CreateBean {

	

	static Connection conn = null;
	static Statement st = null;
	static Vector<String> tableName = new Vector<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] tableNames = {"v_ap_invoice_line" };
//		String[] tableNames = {"customer"};
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				conn = java.sql.DriverManager
						.getConnection(
								"jdbc:mysql://localhost/accounting?useUnicode=true&characterEncoding=UTF-8",
								"root", "root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			st = conn.createStatement();
			for (int i = 0; i < tableNames.length; i++) {
				createTableADO(tableNames[i], conn);
			}
			conn.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createTableADO(String tableName, Connection conn)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		String lineSeparator = System.getProperty("line.separator");
		java.sql.PreparedStatement ps = conn.prepareStatement("select * from "
				+ tableName);
		ResultSetMetaData rm = ps.getMetaData();
		String columnName = "";
		int columnCount = rm.getColumnCount();
		String columnType = "";
		
		sb.append("package uranus.oa.dao;" + lineSeparator);

		sb.append("/*");
		sb.append(" * Created on " + com.genscript.gsscm.common.util.DateUtils.formatDate2Str() + lineSeparator);
		sb.append(" * by zhouyong" + lineSeparator);
		sb.append(" * for what" + lineSeparator);
		sb.append(" */" + lineSeparator);

		sb.append("" + lineSeparator);

		sb.append("" + lineSeparator);
		sb.append("import javax.persistence.Column;"+lineSeparator+"import javax.persistence.Entity;" +
				"import javax.persistence.GeneratedValue;" +lineSeparator+
				"import javax.persistence.GenerationType;" +lineSeparator+
				"import javax.persistence.Id;" +lineSeparator+
				"import javax.persistence.Table;" +lineSeparator+
				"import org.apache.commons.lang.builder.ToStringBuilder;" +lineSeparator+
				"import org.hibernate.annotations.Cache;" +lineSeparator+
				"import org.hibernate.annotations.CacheConcurrencyStrategy;");
//		sb.append("import java.sql.*;" + lineSeparator);
		sb.append("" + lineSeparator);
		sb.append("public class " + tableName + " {" + lineSeparator);
		for (int i = 1; i < columnCount + 1; i++) {
			columnName = rm.getColumnName(i).toLowerCase();
			columnType = typeToStr(rm.getColumnType(i));
			columnName  = FirstBig(columnName); 
//			StringUtils.substringBefore(str, separator)
			sb.append(" public "+columnType + " " + columnName + ";" + lineSeparator);
//			sb.append("public void set" + columnName + "(" + columnType + " p"
//					+ columnName + ") {this." + columnName + " = " + " p"+ columnName
//					+ "; }" + lineSeparator);
//			
//			
//			
//			sb.append("public " + columnType + " get" + columnName
//					+ "() { return this." + columnName + "; }" + lineSeparator);
		}

//		// insert
//		// -------------------------------------------------------------------------------------------
//		sb.append("" + lineSeparator);
//
//		sb.append("public int insert" + tableName
//				+ "(Connection conn) throws SQLException {" + lineSeparator);
//		sb
//				.append("PreparedStatement ps = conn .prepareStatement(\"insert into "
//						+ tableName + "(");
//		String columns = "";
//		String questions = "";
//		for (int i = 0; i < columnCount; i++) {
//			columns += rm.getColumnName(i + 1) + ",";
//			questions += "?,";
//		}
//		columns = columns.substring(0, columns.length() - 1);
//		questions = questions.substring(0, questions.length() - 1);
//		sb.append(columns + ") values(" + questions
//				+ ")\"" );
//		sb.append("); " + lineSeparator);
//		for (int i = 0; i < columnCount; i++) {
//			sb.append("ps.set" + typeToStr(rm.getColumnType(i + 1)) + "("
//					+ (i + 1) + ", this.get"
//					+ rm.getColumnName(i + 1).toUpperCase() + "());"
//					+ lineSeparator);
//		}
//
//		sb.append("int a = ps.executeUpdate();" + lineSeparator);
//		sb.append("if (ps!=null)ps.close();" + lineSeparator);
//		sb.append("return a;" + lineSeparator);
//
//		sb.append("}" + lineSeparator);
//		sb.append("" + lineSeparator);
//
//		// update
//		// --------------------------------------------------------------------------------------------------
//		sb.append("" + lineSeparator);
//		sb.append("public int update" + tableName
//				+ "(Connection conn) throws SQLException {" + lineSeparator);
//		sb.append("PreparedStatement ps = conn.prepareStatement(\"update "
//				+ tableName + " set \"" + lineSeparator);
//
//		for (int i = 0; i < columnCount-1; i++) {
//			sb.append("+ \"" + rm.getColumnName(i + 1).toUpperCase() + "=?,\" "
//					+ lineSeparator);
//		}
//		sb.append("+ \"" + rm.getColumnName(columnCount).toUpperCase() + "=? \" "
//				+ lineSeparator);
//		sb.append("+ \" where " + rm.getColumnName(1).toUpperCase() + "=? \");"
//				+ lineSeparator);
//		for (int i = 0; i < columnCount; i++) {
//			sb.append("ps.set" + typeToStr(rm.getColumnType(i + 1)) + "("
//					+ (i + 1) + ", this.get"
//					+ rm.getColumnName(i + 1).toUpperCase() + "());"
//					+ lineSeparator);
//		}
//		sb.append("ps.set" + typeToStr(rm.getColumnType(1)) + "("
//				+ (columnCount + 1) + ", this.get"
//				+ rm.getColumnName(1).toUpperCase() + "());" + lineSeparator);
//		sb.append("int a = ps.executeUpdate();" + lineSeparator);
//		sb.append("if (ps!=null)ps.close();" + lineSeparator);
//		sb.append("return a;" + lineSeparator);
//		sb.append("}" + lineSeparator);
//		sb.append("" + lineSeparator);
//
//		// delete
//		// -------------------------------------------------------------------------------------------------
//		sb.append("" + lineSeparator);
//		sb.append("public int delete" + tableName + "(Connection conn, "
//				+ typeToStr(rm.getColumnType(1)) + " id) throws SQLException {"
//				+ lineSeparator);
//		sb.append("PreparedStatement ps = conn" + lineSeparator);
//		sb
//				.append(".prepareStatement(\"delete from " + tableName + "  where "
//						+ rm.getColumnName(1).toUpperCase() + "=? \");"
//						+ lineSeparator);
//		sb.append("ps.set" + typeToStr(rm.getColumnType(1)) + "(1, id);" + lineSeparator);
//		sb.append("int a = ps.executeUpdate();" + lineSeparator);
//		sb.append("if (ps!=null)ps.close();" + lineSeparator);
//		sb.append("return a;" + lineSeparator);
//		sb.append("}" + lineSeparator);

		sb.append("}" + lineSeparator);
		String result = sb.toString();
		result=result.replaceAll("setint", "setInt");
		createADOFile("D:/" + tableName + ".java", result);

	}

	private static String typeToStr(int columnTypeInt) {
		String columnType = "String";
		switch (columnTypeInt) {
		case java.sql.Types.VARCHAR:
		case java.sql.Types.CHAR:
			columnType = "String";
			break;
		case java.sql.Types.INTEGER:
		case java.sql.Types.TINYINT:
			columnType="Integer"; break;
		case java.sql.Types.FLOAT:
		case java.sql.Types.DOUBLE:
			columnType = "Float";
			break;
		case java.sql.Types.DECIMAL:
			columnType = "Float";
		     break;
		case java.sql.Types.DATE:
		case java.sql.Types.TIME:
		case java.sql.Types.TIMESTAMP:
			columnType = "Date";
			break;
		case java.sql.Types.BIT:
			columnType="boolean";
			break;
		default:
			columnType = "String";
		}
		return columnType;
	}

	private static void createADOFile(String fileName, String conent) {

		BufferedWriter out = null;
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName, false)));
			out.write(conent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static String FirstBig(String s ){
		if(s.indexOf("_")==-1){
			return s;
		}else{
		String s1 = org.apache.commons.lang.StringUtils.substringBefore(s, "_");
		String s2 = org.apache.commons.lang.StringUtils.substringAfter(s, "_");
		String s3 = s2.substring(0,1);
		s3 = s3.toUpperCase();
		String s4 = s2.substring(1);
		s2 = s3+s4;
		s = s1+s2;
		return s;
		}
	}
	
}
