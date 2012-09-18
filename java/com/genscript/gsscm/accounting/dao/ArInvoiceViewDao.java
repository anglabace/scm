package com.genscript.gsscm.accounting.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.accounting.entity.ArInvoiceView;


	/**
	 * @function 发票的R
	 * @version  1.0
	 * @auther   陈文擎
	 * @date     2010-11-11
	 *  
	 */


@Repository
public class ArInvoiceViewDao  extends HibernateDao<ArInvoiceView, Integer> {

	 /**
     * @function  根据查询条件查询出invoice
     * @param     invoiceNo
     * @param     orderNo
     * @param     custNo
     * @param     shipmentId
     * @param     dateFrom
     * @param     dateTo
     * @param     status
     * @return
     */
    public Page<ArInvoiceView> queryInvoices(Page<ArInvoiceView> page, List<PropertyFilter> filters){
    	
    	Page<ArInvoiceView>  result=this.findPage(page, filters);  	
    	
    	return result;
    }

	
}
