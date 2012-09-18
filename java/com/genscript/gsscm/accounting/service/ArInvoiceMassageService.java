package com.genscript.gsscm.accounting.service;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.accounting.dao.ArInvoiceDao;
import com.genscript.gsscm.accounting.entity.ArInvoice;
import com.genscript.gsscm.accounting.util.Constant;
import com.genscript.gsscm.accounting.util.Tools;

@Service
@Transactional
public class ArInvoiceMassageService {
	@Autowired
	private ArInvoiceDao arInvoiceDao;
	public void getAddOrUpdate(ArInvoice arInvoice) throws Exception{
		// 根据抓取的发票数据的invoice_no判断是新增?更新
		try {
//			System.out
//					.println("----------------------------------------------------------");
			 Integer arInvoiceId = arInvoiceDao.isCheckInvoice(arInvoice.getInvoiceNo(),arInvoice.getModifyDate());
			 System.out.println("arInvoiceId================================="+arInvoiceId);
			// 付款期限
			Integer dutDays = arInvoiceDao.getDutDays(arInvoice.getCustNo());
			// 开票时间
			Date billingTime = arInvoice.getInvoiceDate();
			// 相差天数
			long days = Tools.CompareData(billingTime);
			// 如果付款期限大于相差天数
			if (days > dutDays && arInvoice.getBalance() > 0) {// 过期发票
				// 更新状态：over due invoice，余额：Balance
				arInvoice.setStatus(Constant.STATUS_OVERDUE);			
			} else {
				// 更新余额：ACTIVE
				arInvoice.setStatus("ACTIVE");
			}
			if(arInvoiceId!=null){
				arInvoice.setInvoiceId(arInvoiceId);
				arInvoiceDao.update(arInvoice);
			}else{
				arInvoiceDao.save(arInvoice);
			}
		} catch (SQLException e) {
						
		}
	}
	
	public Integer getCompanyId(String companyName){
		String hql="select c.companyId  from  Company c where c.name='"+companyName+"'";
		return arInvoiceDao.findUnique(hql);
	}

}
