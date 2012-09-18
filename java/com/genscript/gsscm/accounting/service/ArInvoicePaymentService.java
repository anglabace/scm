package com.genscript.gsscm.accounting.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.dao.ArInvoiceViewDao;
import com.genscript.gsscm.accounting.entity.ApInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.accounting.entity.ArTransactionAllocation;

/**
 * @function payment transaction
 * @version 1.0
 * @auther swg
 * @date 2010-11-17
 * 
 */

@Service
@Transactional
public class ArInvoicePaymentService implements ApplicationContextAware {

	@Autowired
	private ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	private ArInvoiceDao arInvoiceDao;

	public boolean delete(List<ArInvoicePayment> entitys) {
		return arInvoicePaymentDao.doDelete(entitys);
	}

	public boolean saveOrUpdate(ArInvoicePayment entity) {
		return arInvoicePaymentDao.saveOrUpdate(entity);
	}

	public boolean saveAllocation(ArTransactionAllocation entity) {
		return arInvoicePaymentDao.saveAllocation(entity);
	}

	public List<Map> getInvoiceList(int transactionId) throws SQLException {
		return arInvoicePaymentDao.getInvoiceList(transactionId);
	}

	public String makeTransactionNo() throws SQLException {
		return arInvoicePaymentDao.makeTransactionNo();
	}

	// 对账完毕关闭invoice
	public void updateInvoiceState(int invoiceId) {
		this.arInvoiceDao.updateInvoiceState(invoiceId);
	}

	public void updatePaymentState(int id) {
		this.arInvoicePaymentDao.updatePaymentState(id);
	}

	public BigDecimal gettotalPaymentsByOrderNo(int OrderNo,
			String orderChency, Date orderDate) {

		return this.arInvoicePaymentDao.getAllAmountByOrderNo(OrderNo,
				orderChency, orderDate);
	}

	public void saveArInvoicePayment(ArInvoicePayment arInvoicePayment) {
		this.arInvoicePaymentDao.save(arInvoicePayment);

	}
	@Transactional(readOnly = true)
	public Page<ArInvoicePayment> GetAllPaymentpage(
			Page<ArInvoicePayment> tpage, String orderNo) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter Filter = new PropertyFilter("EQS_orderNo", orderNo);
		filterList.add(Filter);
		return this.arInvoicePaymentDao.findPage(tpage, filterList);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		 
		
	}

	public BigDecimal getTotalRefund(String orderNo) {
		
		return this.arInvoicePaymentDao.getTotalRefund(orderNo);
	}

	public BigDecimal getTotalPayment(String orderNo) {
		
		return this.arInvoicePaymentDao.getTotalPayment(orderNo);
	}
	
	/**
	 * fangquan
	 * 判断payment是新增还是更新
	 * 
	 */
	public void AddOrUpdate(ArInvoicePayment arInvoicePayment){
		Integer transactionId=arInvoicePaymentDao.isCheckTransactionId(arInvoicePayment.getTransactionNo(), arInvoicePayment.getTransactionDate());
		if(transactionId!=null){
			arInvoicePayment.setTransactionId(transactionId);
			arInvoicePaymentDao.getSession().update(arInvoicePayment);
		}else{
			arInvoicePaymentDao.save(arInvoicePayment);
		}
	}
	
}
