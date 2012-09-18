package com.genscript.gsscm.accounting.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.dto.ArInvoicePaymentDTO;
import com.genscript.gsscm.accounting.dto.ArPaymentAmountDTO;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.entity.ArInvoiceAllocationView;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArInvoicePaymentView;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;

/**
 * @function invoice payment transaction
 * @version 1.0
 * @auther swg
 * @date 2010-11-16
 * 
 */

@Repository
public class ArInvoicePaymentDao extends
		HibernateDao<ArInvoicePayment, Integer> {
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;

	public String arTransactionsViewSql = "select t.*, u.login_name created_by_name, p.apply_amount, i.invoice_id, i.invoice_no, i.order_no, al.id, c.first_name, c.last_name, d.symbol from accounting.ar_transactions t "
			+ "left join "
			+ "( "
			+ "select ta.transaction_id, sum(ta.apply_amount) as apply_amount "
			+ "from accounting.ar_transaction_allocation ta "
			+ "group by ta.transaction_id "
			+ ") p on t.transaction_id = p.transaction_id "
			+ "left join "
			+ "( "
			+ "select transaction_id, min(id) id, invoice_id "
			+ "from accounting.ar_transaction_allocation "
			+ "group by transaction_id "
			+ ") al on t.transaction_id = al.transaction_id "
			+ "left join accounting.ar_invoices i on al.invoice_id = i.invoice_id "
			+ "left join system.users u on t.created_by=u.user_id "
			+ "left join customer.v_all_customers c on t.cust_no=c.cust_no "
			+ "left join common.currency d on t.currency=d.currency_code ";

	/**
	 * 根据查询条件查询发票交易结果
	 * 
	 * @param m
	 * @return
	 * @throws SQLException
	 */
	public List<ArInvoicePaymentView> list(Page<ArInvoicePaymentView> page,
			Map m) throws SQLException {
		List<ArInvoicePaymentView> list = new ArrayList<ArInvoicePaymentView>();
		Connection conn = this.getSession().connection();
		String sql = "select * from (" + arTransactionsViewSql
				+ ") a where 1=1 ";
		if (m != null) {
			// if(m.get("filter_LIKES_transactionNo")!=null &&
			// !m.get("filter_LIKES_transactionNo").equals("") ){
			// sql +=
			// " and a.transaction_no like '%"+m.get("filter_LIKES_transactionNo")+"%'";
			// }
			if (m.get("filter_LIKES_transactionNo") != null
					&& !m.get("filter_LIKES_transactionNo").equals("")) {
				sql += " and a.transaction_id = "
						+ m.get("filter_LIKES_transactionNo");
			}
			if (m.get("filter_EQI_orderNo") != null
					&& !m.get("filter_EQI_orderNo").equals("")) {
				sql += " and a.order_no =" + m.get("filter_EQI_orderNo");
			}
			if (m.get("filter_EQI_custNo") != null
					&& !m.get("filter_EQI_custNo").equals("")) {
				sql += " and a.cust_no =" + m.get("filter_EQI_custNo");
			}
			if (m.get("filter_EQI_balance") != null
					&& !m.get("filter_EQI_balance").equals("")) {
				sql += " and a.balance >" + m.get("filter_EQI_balance");
			}
			if (m.get("filter_EQS_status") != null
					&& !m.get("filter_EQS_status").equals("")) {
				sql += " and a.status='" + m.get("filter_EQS_status") + "'";
			} else {
				// sql += " and a.status != 'Completed'";
			}
			if (m.get("filter_GTD_transactionDate") != null
					&& !m.get("filter_GTD_transactionDate").equals("")) {
				sql += " and a.transaction_date>='"
						+ m.get("filter_GTD_transactionDate") + "'";
			}
			if (m.get("filter_LTD_transactionDate") != null
					&& !m.get("filter_LTD_transactionDate").equals("")) {
				sql += " and a.transaction_date<='"
						+ m.get("filter_LTD_transactionDate") + "'";
			}
		} else {
			// 初始查询过滤掉“Completed” Payment Transaction
			sql += " and a.status != 'Completed'";
		}
		sql += " order by a.transaction_id desc ";
		long start = (page.getPageNo() - 1) * page.getPageSize();
		sql += " limit " + start + ", " + page.getPageSize();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArInvoicePaymentView a = null;
		while (rs.next()) {
			a = new ArInvoicePaymentView();
			a.setAccountName(rs.getString("account_name"));
			a.setAccountNo(rs.getString("account_no"));
			a.setAmount(rs.getString("amount"));
			a.setBalance(rs.getString("balance"));
			a.setCcCardHolder(rs.getString("cc_card_holder"));
			a.setCcCvc(rs.getString("cc_cvc"));
			a.setCcExpiration(rs.getString("cc_expiration"));
			a.setCcType(rs.getString("cc_type"));
			a.setChkNo(rs.getString("chk_no"));
			a.setCreatedBy(rs.getInt("created_by"));
			a.setCreatedByName(rs.getString("created_by_name"));
			a.setCreationDate(new java.util.Date(rs.getTimestamp(
					"creation_date").getTime()));
			a.setCurrency(rs.getString("currency"));
			a.setCustNo(rs.getInt("cust_no"));
			a.setDescription(rs.getString("description"));
			a.setModifiedBy(rs.getInt("modified_by"));
			a.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date")
					.getTime()));
			a.setPaymentType(rs.getString("payment_type"));
			a.setRoutingNo(rs.getString("routing_no"));
			a.setStatus(rs.getString("status"));
			a.setTenderType(rs.getString("tender_type"));
			a.setTransactionDate(new java.util.Date(rs.getTimestamp(
					"transaction_date").getTime()));
			a.setTransactionFee(rs.getString("transaction_fee"));
			a.setTransactionId(rs.getInt("transaction_id"));
			a.setTransactionNo(rs.getString("transaction_no"));
			a.setTransactionType(rs.getString("transaction_type"));
			a.setInvoiceId(rs.getInt("invoice_id"));
			a.setInvoiceNo(rs.getString("invoice_no"));
			a.setOrderNo(rs.getString("order_no"));
			a.setApplyAmount(rs.getString("apply_amount"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setSymbol(rs.getString("symbol"));
			list.add(a);
		}
		rs.close();
		ps.close();
		conn.close();
		return list;
	}

	public long getTotalPage(Map m) {
		long total = 0;
		String sql = "select count(*) from (" + arTransactionsViewSql
				+ ") a where 1=1 ";
		if (m != null) {
			// if(m.get("filter_LIKES_transactionNo")!=null &&
			// !m.get("filter_LIKES_transactionNo").equals("") ){
			// sql +=
			// " and a.transaction_no like '%"+m.get("filter_LIKES_transactionNo")+"%'";
			// }
			if (m.get("filter_LIKES_transactionNo") != null
					&& !m.get("filter_LIKES_transactionNo").equals("")) {
				sql += " and a.transaction_id = "
						+ m.get("filter_LIKES_transactionNo");
			}
			if (m.get("filter_EQI_orderNo") != null
					&& !m.get("filter_EQI_orderNo").equals("")) {
				sql += " and a.order_no =" + m.get("filter_EQI_orderNo");
			}
			if (m.get("filter_EQI_custNo") != null
					&& !m.get("filter_EQI_custNo").equals("")) {
				sql += " and a.cust_no =" + m.get("filter_EQI_custNo");
			}
			if (m.get("filter_EQI_balance") != null
					&& !m.get("filter_EQI_balance").equals("")) {
				sql += " and a.balance >" + m.get("filter_EQI_balance");
			}
			if (m.get("filter_EQS_status") != null
					&& !m.get("filter_EQS_status").equals("")) {
				sql += " and a.status='" + m.get("filter_EQS_status") + "'";
			} else {
				// sql += " and a.status != 'Completed'";
			}
			if (m.get("filter_GTD_transactionDate") != null
					&& !m.get("filter_GTD_transactionDate").equals("")) {
				sql += " and a.transaction_date>='"
						+ m.get("filter_GTD_transactionDate") + "'";
			}
			if (m.get("filter_LTD_transactionDate") != null
					&& !m.get("filter_LTD_transactionDate").equals("")) {
				sql += " and a.transaction_date<='"
						+ m.get("filter_LTD_transactionDate") + "'";
			}
		} else {
			// 初始查询过滤掉“Completed” Payment Transaction
			sql += " and a.status != 'Completed'";
		}

		List list = this.getSession().createSQLQuery(sql).list();
		total = Long.parseLong(list.get(0).toString());
		return total;
	}

	/**
	 * swg 2010-11-17:逻辑删除发票交易记录
	 * 
	 * @param entitys
	 * @return
	 */
	public boolean doDelete(List<ArInvoicePayment> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			ArInvoicePayment entity = entitys.get(i);
			if (entity != null) {
				boolean flag = this
						.checkIsAllocation(entity.getTransactionId());
				if (!flag) { // 如果 已经有对账记录，则不更新
					entity.setStatus("Void");
					this.getSession().update(entity);
					this.getSession().flush();
				}
			} else
				return false;
		}

		return true;
	}

	/**
	 * 根据ID查询发票交易记录
	 * 
	 * @param id
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	public ArInvoicePaymentDTO getById(int id) throws IllegalAccessException,
			InvocationTargetException, SQLException {
		ArInvoicePaymentDTO arInvoicePaymentDto = new ArInvoicePaymentDTO();
		Connection conn = this.getSession().connection();
		String sql = "select * from (" + arTransactionsViewSql
				+ ") a where transaction_id=" + id;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			arInvoicePaymentDto = new ArInvoicePaymentDTO();
			arInvoicePaymentDto.setAccountName(rs.getString("account_name"));
			arInvoicePaymentDto.setAccountNo(rs.getString("account_no"));
			arInvoicePaymentDto.setAmount(rs.getString("amount"));
			arInvoicePaymentDto.setBalance(rs.getString("balance"));
			arInvoicePaymentDto.setCcCardHolder(rs.getString("cc_card_holder"));
			arInvoicePaymentDto.setCcCvc(rs.getString("cc_cvc"));
			arInvoicePaymentDto.setCcExpiration(rs.getString("cc_expiration"));
			arInvoicePaymentDto.setCcType(rs.getString("cc_type"));
			arInvoicePaymentDto.setChkNo(rs.getString("chk_no"));
			arInvoicePaymentDto.setCreatedBy(rs.getInt("created_by"));
			arInvoicePaymentDto.setCreatedByName(rs
					.getString("created_by_name"));
			arInvoicePaymentDto.setCreationDate(new java.util.Date(rs
					.getTimestamp("creation_date").getTime()));
			arInvoicePaymentDto.setCurrency(rs.getString("currency"));
			arInvoicePaymentDto.setCustNo(rs.getInt("cust_no"));
			arInvoicePaymentDto.setDescription(rs.getString("description"));
			arInvoicePaymentDto.setModifiedBy(rs.getInt("modified_by"));
			arInvoicePaymentDto.setModifyDate(new java.util.Date(rs
					.getTimestamp("modify_date").getTime()));
			arInvoicePaymentDto.setPaymentType(rs.getString("payment_type"));
			arInvoicePaymentDto.setRoutingNo(rs.getString("routing_no"));
			arInvoicePaymentDto.setStatus(rs.getString("status"));
			arInvoicePaymentDto.setTenderType(rs.getString("tender_type"));
			arInvoicePaymentDto.setTransactionDate(new java.util.Date(rs
					.getTimestamp("transaction_date").getTime()));
			arInvoicePaymentDto.setTransactionFee(rs
					.getString("transaction_fee"));
			arInvoicePaymentDto.setTransactionId(rs.getInt("transaction_id"));
			arInvoicePaymentDto
					.setTransactionNo(rs.getString("transaction_no"));
			arInvoicePaymentDto.setTransactionType(rs
					.getString("transaction_type"));
			arInvoicePaymentDto.setInvoiceId(rs.getInt("invoice_id"));
			arInvoicePaymentDto.setInvoiceNo(rs.getString("invoice_no"));
			arInvoicePaymentDto.setOrderNo(rs.getString("order_no"));
			arInvoicePaymentDto.setApplyAmount(rs.getString("apply_amount"));
			arInvoicePaymentDto.setStatusUpdReason(rs
					.getString("status_upd_reason"));
			arInvoicePaymentDto.setFirstName(rs.getString("first_name"));
			arInvoicePaymentDto.setLastName(rs.getString("last_name"));
			arInvoicePaymentDto.setSymbol(rs.getString("symbol"));
		}
		rs.close();
		ps.close();
		conn.close();

		return arInvoicePaymentDto;
	}

	/**
	 * 保存或修改发票交易记录
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveOrUpdate(ArInvoicePayment entity) {
		if (entity != null) {
			this.getSession().saveOrUpdate(entity);
			this.getSession().flush();
			return true;
		} else
			return false;
	}

	/**
	 * 保存或修改发票交易付款记录
	 * 
	 * @param entity
	 * @return
	 */
	public boolean saveAllocation(ArTransactionAllocation entity) {
		if (entity != null) {
			Session session = this.getSession();
			session.saveOrUpdate(entity);
			session.beginTransaction().commit();
			return true;
		} else
			return false;
	}

	public boolean checkTransactionNoExist(String transactionNo)
			throws IllegalAccessException, InvocationTargetException,
			SQLException {
		int count = 0;
		Connection conn = this.getSession().connection();
		String sql = "select count(*) as count from accounting.ar_transactions where transaction_no='"
				+ transactionNo + "'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		ps.close();
		conn.close();

		if (count > 0)
			return true;
		else
			return false;
	}

	/**
	 * 查询发票交易付款记录,只能查看审核通过的，allocation status = ‘approved’
	 * 
	 * @param m
	 * @return
	 * @throws SQLException
	 */
	public List<ArInvoiceAllocationView> allocationList(Map m)
			throws SQLException {
		List<ArInvoiceAllocationView> list = new ArrayList<ArInvoiceAllocationView>();
		// Connection conn = this.getSession().connection();
		String sql = "select a.id AS id,a.invoice_id ,a.transaction_id,a.apply_amount ,a.creation_date ,"
				+ " a.created_by ,a.modify_date ,a.modified_by ,b.transaction_no ,c.invoice_no ,c.order_no from "
				+ "((accounting.ar_transaction_allocation a join accounting.ar_transactions b) join accounting.ar_invoices c) "
				+ " where ((a.transaction_id = b.transaction_id) and (a.invoice_id = c.invoice_id)) and a.transaction_id="
				+ m.get("transactionId");
		sql += " and a.status = '" + Constant.STATUS_APPROVED + "'";
		list = this.getSession().createSQLQuery(sql)
				.addEntity(ArInvoiceAllocationView.class).list();
		// PreparedStatement ps = conn.prepareStatement(sql);
		// ResultSet rs = ps.executeQuery();
		// ArInvoiceAllocationView a = null;
		// while(rs.next()){
		// a = new ArInvoiceAllocationView();
		// a.setId(rs.getInt("id"));
		// a.setCreatedBy(rs.getInt("created_by"));
		// a.setCreationDate(new
		// java.util.Date(rs.getTimestamp("creation_date").getTime()));
		// a.setModifiedBy(rs.getInt("modified_by"));
		// a.setModifyDate(new
		// java.util.Date(rs.getTimestamp("modify_date").getTime()));
		// a.setTransactionId(rs.getInt("transaction_id"));
		// a.setTransactionNo(rs.getString("transaction_no"));
		// a.setInvoiceId(rs.getInt("invoice_id"));
		// a.setInvoiceNo(rs.getString("invoice_no"));
		// a.setOrderNo(rs.getString("order_no"));
		// a.setApplyAmount(rs.getString("apply_amount"));
		// list.add(a);
		// }
		// rs.close();
		// ps.close();
		// conn.close();
		return list;
	}

	public List<Map> getInvoiceList(int transactionId) throws SQLException {
		List<Map> list = new ArrayList<Map>();
		Connection conn = this.getSession().connection();
		String sql = "select i.invoice_id, i.invoice_no, i.order_no from accounting.ar_transaction_allocation a, accounting.ar_invoices i "
				+ "where a.invoice_id=i.invoice_id and a.transaction_id="
				+ transactionId;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map tempMap = new HashMap();
			tempMap.put("invoiceId", rs.getInt("invoice_id"));
			tempMap.put("invoiceNo", rs.getInt("invoice_no"));
			tempMap.put("orderNo", rs.getInt("order_no"));
			list.add(tempMap);
		}
		rs.close();
		ps.close();
		conn.close();
		return list;
	}

	public String makeTransactionNo() throws SQLException {
		String transactionNo = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String strdate = simpleDateFormat.format(new Date());
		Connection conn = this.getSession().connection();
		String sql = "select max(transaction_no) as transaction_no from accounting.ar_transactions"
				+ " where transaction_no like '" + strdate + "%' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			transactionNo = rs.getString("transaction_no");
		}
		int idx = 1;
		if (transactionNo != null) {
			idx = new Integer(transactionNo.substring(8)) + 1;
		}
		String sinx = ("0000" + idx);
		transactionNo = strdate + sinx.substring(sinx.length() - 4);

		return transactionNo;
	}

	/**
	 * 根据查询条件查询发票交易结果
	 * 
	 * @param m
	 * @return
	 * @throws SQLException
	 */
	public List<ArInvoicePaymentView> AuthorizationList(
			Page<ArInvoicePaymentView> page, Map m) throws SQLException {
		List<ArInvoicePaymentView> list = new ArrayList<ArInvoicePaymentView>();
		Connection conn = this.getSession().connection();
		String sql = "select * from (" + arTransactionsViewSql
				+ ") a where 1=1 ";
		if (m != null) {
			if (m.get("filter_LIKES_transactionNo") != null
					&& !m.get("filter_LIKES_transactionNo").equals("")) { // transcation
																			// id
				sql += " and a.transaction_id = "
						+ m.get("filter_LIKES_transactionNo");
			}
			if (m.get("filter_EQI_orderNo") != null
					&& !m.get("filter_EQI_orderNo").equals("")) {
				sql += " and a.order_no =" + m.get("filter_EQI_orderNo");
			}
			if (m.get("filter_EQI_custNo") != null
					&& !m.get("filter_EQI_custNo").equals("")) {
				sql += " and a.cust_no =" + m.get("filter_EQI_custNo");
			}

			if (m.get("filter_GTD_transactionDate") != null
					&& !m.get("filter_GTD_transactionDate").equals("")) {
				sql += " and a.transaction_date>='"
						+ m.get("filter_GTD_transactionDate") + "'";
			}
			if (m.get("filter_LTD_transactionDate") != null
					&& !m.get("filter_LTD_transactionDate").equals("")) {
				sql += " and a.transaction_date<='"
						+ m.get("filter_LTD_transactionDate") + "'";
			}

			if (m.get("filter_EQS_transactionType") != null
					&& !m.get("filter_EQS_transactionType").equals("")) {
				sql += " and a.transaction_type = '"
						+ m.get("filter_EQS_transactionType") + "'";
			}

			sql += " and (a.status='" + Constant.STATUS_DRAFT
					+ "'  or  a.payment_type='" + Constant.PAYMENTTYPE_CHARGED
					+ "' )";

			sql += " order by transaction_date desc ";

			long start = (page.getPageNo() - 1) * page.getPageSize();
			sql += " limit " + start + ", " + page.getPageSize();

		}
		// System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArInvoicePaymentView a = null;
		while (rs.next()) {
			a = new ArInvoicePaymentView();
			a.setAccountName(rs.getString("account_name"));
			a.setAccountNo(rs.getString("account_no"));
			a.setAmount(rs.getString("amount"));
			a.setBalance(rs.getString("balance"));
			a.setCcCardHolder(rs.getString("cc_card_holder"));
			a.setCcCvc(rs.getString("cc_cvc"));
			a.setCcExpiration(rs.getString("cc_expiration"));
			a.setCcType(rs.getString("cc_type"));
			a.setChkNo(rs.getString("chk_no"));
			a.setCreatedBy(rs.getInt("created_by"));
			a.setCreatedByName(rs.getString("created_by_name"));
			a.setCreationDate(new java.util.Date(rs.getTimestamp(
					"creation_date").getTime()));
			a.setCurrency(rs.getString("currency"));
			a.setCustNo(rs.getInt("cust_no"));
			a.setDescription(rs.getString("description"));
			a.setModifiedBy(rs.getInt("modified_by"));
			a.setModifyDate(new java.util.Date(rs.getTimestamp("modify_date")
					.getTime()));
			a.setPaymentType(rs.getString("payment_type"));
			a.setRoutingNo(rs.getString("routing_no"));
			a.setStatus(rs.getString("status"));
			a.setTenderType(rs.getString("tender_type"));
			a.setTransactionDate(new java.util.Date(rs.getTimestamp(
					"transaction_date").getTime()));
			a.setTransactionFee(rs.getString("transaction_fee"));
			a.setTransactionId(rs.getInt("transaction_id"));
			a.setTransactionNo(rs.getString("transaction_no"));
			a.setTransactionType(rs.getString("transaction_type"));
			a.setInvoiceId(rs.getInt("invoice_id"));
			a.setInvoiceNo(rs.getString("invoice_no"));
			a.setOrderNo(rs.getString("order_no"));
			a.setApplyAmount(rs.getString("apply_amount"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setSymbol(rs.getString("symbol"));
			list.add(a);
		}
		rs.close();
		ps.close();
		conn.close();
		return list;
	}

	public long getAuthorizationTotalPage(Map m) {
		long total = 0;
		String sql = "select count(*) from (" + arTransactionsViewSql
				+ ") a where 1=1 ";
		if (m != null) {
			if (m.get("filter_LIKES_transactionNo") != null
					&& !m.get("filter_LIKES_transactionNo").equals("")) {
				sql += " and a.transaction_id = "
						+ m.get("filter_LIKES_transactionNo");
			}
			if (m.get("filter_EQI_orderNo") != null
					&& !m.get("filter_EQI_orderNo").equals("")) {
				sql += " and a.order_no =" + m.get("filter_EQI_orderNo");
			}
			if (m.get("filter_EQI_custNo") != null
					&& !m.get("filter_EQI_custNo").equals("")) {
				sql += " and a.cust_no =" + m.get("filter_EQI_custNo");
			}

			if (m.get("filter_GTD_transactionDate") != null
					&& !m.get("filter_GTD_transactionDate").equals("")) {
				sql += " and a.transaction_date>='"
						+ m.get("filter_GTD_transactionDate") + "'";
			}
			if (m.get("filter_LTD_transactionDate") != null
					&& !m.get("filter_LTD_transactionDate").equals("")) {
				sql += " and a.transaction_date<='"
						+ m.get("filter_LTD_transactionDate") + "'";
			}

			if (m.get("filter_EQS_transactionType") != null
					&& !m.get("filter_EQS_transactionType").equals("")) {
				sql += " and a.transaction_type = '"
						+ m.get("filter_EQS_transactionType") + "'";
			}

			sql += " and (a.status='" + Constant.STATUS_DRAFT
					+ "'  or  a.payment_type='" + Constant.PAYMENTTYPE_CHARGED
					+ "' )";
		}
		System.out.println(sql);
		List list = this.getSession().createSQLQuery(sql).list();
		total = Long.parseLong(list.get(0).toString());
		return total;
	}

	public void updateStatus(Map paramter) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		String sql = "update  accounting.ar_transactions a "
				+ " set  a.status='" + paramter.get("status").toString().trim()
				+ "'";
		if (null != paramter.get("reason"))
			sql = sql + "  ,  a.status_upd_reason='"
					+ paramter.get("reason").toString().trim() + "'";

		sql = sql + "  where  a.transaction_id in ("
				+ paramter.get("ids").toString().trim() + ")";
		int result = session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		session = null;
	}

	// 检查orderNo是否存在
	public boolean checkOrderExist(String orderNo) {
		String sql = "select order_no from order.order where order_no = "
				+ orderNo;
		List list = this.getSession().createSQLQuery(sql).list();

		return (list.size() > 0) ? true : false;
	}

	// 检查invoiceNo是否存在
	public boolean checkInvoiceExist(String invoiceId) {
		String sql = "select invoice_id from accounting.ar_invoices where invoice_id = "
				+ invoiceId;
		List list = this.getSession().createSQLQuery(sql).list();

		return (list.size() > 0) ? true : false;
	}

	/**
	 * 检查invoice导入的invoice信息是否正确，判断invoiceId是否存在
	 * 判断orderNo是否是invoice的orderNo，并且custNo是否是她的custNo 并且剪掉balance的值
	 * 
	 * @param invoiceId
	 * @param orderNo
	 * @param custNo
	 * @return
	 */
	public boolean checkUploadPayment(int invoiceId, int orderNo, int custNo,
			String currency, String applyAmount) {
		boolean flag = false;
		Session session = this.getSession();
		boolean state = this.checkInvoiceExist(invoiceId + "");
		if (!state) {
			return false;
		}
		ArInvoice a = (ArInvoice) session.get(ArInvoice.class, invoiceId);
		if (currency.equals(a.getCurrency()) && custNo == a.getCustNo()
				&& a.getOrderNo() == orderNo) {
			flag = true;
			// ArInvoice a =
			// this.arInvoiceDao.get(Tools.String2Integer(invoiceId));
			float balace = a.getBalance();
			balace = balace - Tools.String2Float(applyAmount);
			balace = balace <= 0 ? 0 : balace;
			a.setBalance(balace);
			session.update(a);
			session.flush();
			session.beginTransaction().commit();
		}
		return flag;
	}

	public Integer getInvoiceIdByNo(String invoiceNo) {
		String sql = "select invoice_id from accounting.ar_invoices where invoice_no = '"
				+ invoiceNo + "'";
		List list = this.getSession().createSQLQuery(sql).list();
		if (list.size() > 0) {
			return new Integer(list.get(0).toString());
		} else {
			return null;
		}
	}

	/**
	 * 判断该发票是否有对账记录
	 * 
	 * @param transcationId
	 * @return
	 */
	public boolean checkIsAllocation(int transcationId) {
		boolean flag = false;
		List list = null;
		Criteria c = this.getSession().createCriteria(
				ArTransactionAllocation.class);
		Criterion cr = Restrictions.eq("transaction_id", transcationId);
		c.add(cr);
		list = c.list();
		if (list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Excel2007解析
	 * 
	 * @param sheet
	 * @return
	 */
	public static List<ArInvoicePaymentView> getExcelData(Sheet sheet) {
		Iterator<Row> rows = sheet.iterator();// 行集合
		String k = ""; // 用于接收每个单元格的数据。
		List<ArInvoicePaymentView> paymentList = new ArrayList<ArInvoicePaymentView>();

		if (rows.hasNext())
			rows.next();// 剔除标题行
		while (rows.hasNext()) { // 遍历所有的行.
			Row row = rows.next();
			ArInvoicePaymentView payment = new ArInvoicePaymentView();

			k = getCellValue(row.getCell(0)).trim();
			payment.setCustNo(new Integer(new Double(k).intValue()));
			k = getCellValue(row.getCell(1)).trim();
			if (k.equals("")) {
				payment.setOrderNo("");
			} else {
				payment.setOrderNo(new Integer(new Double(k).intValue()) + "");
			}
			k = getCellValue(row.getCell(2)).trim();
			if (k.equals("")) {
				payment.setInvoiceNo("");
			} else {
				payment.setInvoiceNo(new Integer(new Double(k).intValue()) + "");
			}
			k = getCellValue(row.getCell(3)).trim();
			if (!k.equals("")) {
				payment.setApplyAmount(Float.parseFloat(k) + "");
			}
			k = getCellValue(row.getCell(4)).trim();
			if (!k.equals("")) {
				payment.setBalance(Float.parseFloat(k) + "");
			}
			k = getCellValue(row.getCell(5)).trim();
			payment.setTransactionType(k);
			k = getCellValue(row.getCell(6)).trim();
			payment.setDescription(k);
			k = getCellValue(row.getCell(7)).trim();
			payment.setAmount(Float.parseFloat(k) + "");
			k = getCellValue(row.getCell(8)).trim();
			payment.setTransactionFee(k);
			k = getCellValue(row.getCell(9)).trim();
			payment.setCurrency(k);
			k = getCellValue(row.getCell(10)).trim();
			payment.setTenderType(k);
			k = getCellValue(row.getCell(11)).trim();
			payment.setPaymentType(k);
			if (payment.getTenderType().equals("Check")) {
				k = getCellValue(row.getCell(12)).trim();
				payment.setAccountName(k);
				k = getCellValue(row.getCell(13)).trim();
				payment.setAccountNo(k);
				k = getCellValue(row.getCell(14)).trim();
				payment.setRoutingNo(k);
				k = getCellValue(row.getCell(15)).trim();
				payment.setChkNo(k);
			} else if (payment.getTenderType().equals("Credit Card")) {
				k = getCellValue(row.getCell(16)).trim();
				payment.setAccountName(k);
				k = getCellValue(row.getCell(17)).trim();
				payment.setCcType(k);
				k = getCellValue(row.getCell(18)).trim();
				payment.setCcCardHolder(k);
				k = getCellValue(row.getCell(19)).trim();
				payment.setAccountNo(k);
				k = getCellValue(row.getCell(20)).trim();
				payment.setCcCvc(k);
				k = getCellValue(row.getCell(21)).trim();
				payment.setCcExpiration(k);
			} else {
				k = getCellValue(row.getCell(12)).trim();
				payment.setAccountName(k);
				k = getCellValue(row.getCell(13)).trim();
				payment.setAccountNo(k);
				k = getCellValue(row.getCell(14)).trim();
				payment.setRoutingNo(k);
			}

			paymentList.add(payment);
		}

		return paymentList;
	}

	public static String getCellValue(Cell c) {
		if (c == null)
			return "";
		String k = "";
		int type = c.getCellType(); // 得到单元格数据类型
		switch (type) { // 判断数据类型
		case Cell.CELL_TYPE_BLANK:
			k = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			k = c.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_ERROR:
			k = c.getErrorCellValue() + "";
			break;
		case Cell.CELL_TYPE_FORMULA:
			k = c.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// c.setCellType(Cell.CELL_TYPE_STRING);
			System.out
					.println("getNumericCellValue:" + c.getNumericCellValue());
			DecimalFormat df = new DecimalFormat("0.000");
			k = df.format(c.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			k = c.getStringCellValue();
			break;
		default:
			break;
		}

		return k;
	}

	public void updatePaymentState(int id) {
		this.getSession()
				.createQuery(
						"update ArInvoicePayment a set a.state = '"
								+ Constant.STATUS_CLOSED
								+ "' where a.transactionId=" + id)
				.executeUpdate();
	}

	public BigDecimal getAllAmountByOrderNo(int orderNo, String orderChency,
			Date orderDate) {
		BigDecimal paymentAll = new BigDecimal(0);
		System.out.println(orderChency); // 这个是目的货币
		String sql_payMent = "";
		String thiscurrency = "";
		BigDecimal ratit = new BigDecimal(0);// 转换比例
		BigDecimal amount = new BigDecimal(0);// 需要被转换的金额
		sql_payMent = "select d.`currency`,d.`amount`   FROM `accounting`.`ar_transactions` d where d.`order_no`="
				+ orderNo;
		System.out.println(sql_payMent);
		@SuppressWarnings("rawtypes")
		List list1 = this.getSession().createSQLQuery(sql_payMent).list();
		if (list1 != null && !"".equals(list1)) {
			if (list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					Object[] object = (Object[]) list1.get(i);
					if (object[0] != null && !"".equals(object[0])) {
						thiscurrency = object[0].toString();
					}
					System.out.println(thiscurrency);
					if (object[1] != null && !"".equals(object[1])) {
						amount = new BigDecimal(object[1].toString());
					}
					System.out.println(amount);
					ratit = new BigDecimal(
							this.exchRateByDateDao.getCurrencyRate(
									thiscurrency, orderChency, orderDate));
					System.out.println(ratit);
					if (amount != null && !"".equals(amount) && ratit != null
							&& !"".equals(ratit)) {
						BigDecimal result = amount.multiply(ratit);
						paymentAll = paymentAll.add(result);
					}
				}
			}
		}
		System.out.println(paymentAll);
		return paymentAll;
	}

	public BigDecimal getTotalRefund(String orderNo) {
		String sql = "select sum(d.`amount`) FROM `accounting`.`ar_transactions` d  where `order_no`='"
				+ orderNo + "' and `transaction_type`='Credit Card Refund' ";
		java.math.BigDecimal Payment = new BigDecimal(0);
		System.out.println(sql);
		@SuppressWarnings("rawtypes")
		List list1 = this.getSession().createSQLQuery(sql).list();
		if (list1 != null && !"".equals(list1)) {
			if (list1.size() > 0) {
				Payment = (java.math.BigDecimal) list1.get(0);
			}
		}
		return Payment;
	}

	public BigDecimal getTotalPayment(String orderNo) {
		java.math.BigDecimal Payment = new BigDecimal(0);
		String sql = "select sum(d.`amount`) FROM `accounting`.`ar_transactions` d  where `order_no`='"
				+ orderNo + "' and `transaction_type`='Credit Card Payment' ";
		System.out.println(sql);
		@SuppressWarnings("rawtypes")
		List list1 = this.getSession().createSQLQuery(sql).list();
		if (list1 != null && !"".equals(list1)) {
			if (list1.size() > 0) {
				Payment = (java.math.BigDecimal) list1.get(0);
			}
		}
		return Payment;
	}

	@SuppressWarnings("unchecked")
	public List<ArInvoicePayment> getALLpaymentsByOrderNo(int orderNo) {
		String sql = " select `transaction_no`,`cust_no`,`transaction_date`,`transaction_type`,`amount`,`balance` from `accounting`.`ar_transactions` where  `order_no`="
				+ orderNo /*+ " and `transaction_type`='Credit Card Payment'"*/;
		System.out.println(sql);
		@SuppressWarnings("rawtypes")
		List list1 = new ArrayList();
		list1 = this.getSession().createSQLQuery(sql).list();
		if (list1 != null && !"".equals(list1)) {
			return list1;
		}
		System.out.println(list1.size());
		return new ArrayList();
	}

	/*
	 * private static final String AMOUNT_BY_ORDER =
	 * "SELECT ar_transaction_allocation.apply_amount," +
	 * "ar_invoices.currency FROM accounting.ar_transaction_allocation, " +
	 * "accounting.ar_invoices WHERE ar_invoices.invoice_id = ar_transaction_allocation.invoice_id "
	 * + "AND ar_invoices.order_no=:orderNo";
	 */
	private static final String AMOUNT_BY_ORDER = "select  `amount`,`currency`  from `accounting`.`ar_transactions`  where  `order_no`=:orderNo"
			/*+ " and `transaction_type`='Credit Card Payment' "*/;
 
	public List<ArPaymentAmountDTO> getAmountByOrder(Integer orderNo) {
		List<ArPaymentAmountDTO> amountList = new ArrayList<ArPaymentAmountDTO>();
		@SuppressWarnings("rawtypes")
		List list = this.getSession().createSQLQuery(AMOUNT_BY_ORDER)
				.setInteger("orderNo", orderNo).list();
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = (Object[]) list.get(i);
			BigDecimal amount = (BigDecimal) objs[0];
			String currency = (String) objs[1];
			ArPaymentAmountDTO amountDTO = new ArPaymentAmountDTO();
			amountDTO.setAmount(amount);
			amountDTO.setCurrency(currency);
			amountList.add(amountDTO);
		}
		return amountList;
	}
	
	/**
	 * fangquan
	 * 2011-12-17
	 * 根据transaction_no和transaction_date判断paymen是否存在
	 */
	
	public Integer isCheckTransactionId(String transaction_no,Date transaction_date){
		String date=DateUtils.formatDate2Str(transaction_date);
		String hql="select a.transactionId from ArInvoicePayment a  where a.transactionNo='"+transaction_no+"' and  a.transactionDate='"+date+"'";
		return this.findUnique(hql);
	}

}
