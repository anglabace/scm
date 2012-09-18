package com.genscript.gsscm.accounting.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.accounting.dao.ApInvoiceDao;
import com.genscript.gsscm.accounting.dao.ApInvoicePaymentDao;
import com.genscript.gsscm.accounting.entity.ApInvoicePayment;
import com.genscript.gsscm.accounting.entity.ApTransactionAllocation;


	/**
	 * @function     payment transaction
	 * @version      1.0
	 * @auther       swg
	 * @date         2010-11-17
	 *  
	 */

@Service
@Transactional
public class ApInvoicePaymentService {

    @Autowired
	ApInvoicePaymentDao apInvoicePaymentDao;
    @Autowired
    ApInvoiceDao apInvoiceDao;
    public boolean delete(List<ApInvoicePayment> entitys){
    	return apInvoicePaymentDao.doDelete(entitys);
    } 
    
    public boolean saveOrUpdate(ApInvoicePayment entity){
    	return apInvoicePaymentDao.saveOrUpdate(entity);
    } 
    
    public boolean saveAllocation(ApTransactionAllocation entity){
    	return apInvoicePaymentDao.saveAllocation(entity);
    } 
    
    public List<Map> getInvoiceList(int transactionId) throws SQLException{
    	return apInvoicePaymentDao.getInvoiceList(transactionId);
    }
    
    public String makeTransactionNo() throws SQLException{
    	return apInvoicePaymentDao.makeTransactionNo();
    }
    
  //对账完毕关闭invoice
	public void updateInvoiceState(int invoiceId){
		this.apInvoiceDao.updateInvoiceState(invoiceId);
	}
	
	public void updatePaymentState(int id){
		this.apInvoicePaymentDao.updatePaymentState(id);
	}


}
