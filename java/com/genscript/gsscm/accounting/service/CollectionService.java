package com.genscript.gsscm.accounting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.accounting.dao.FillSelectDao;
import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;


	/**
	 * @function     payment transaction
	 * @version      1.0
	 * @auther       swg
	 * @date         2010-11-17
	 *  
	 */

@Service
@Transactional
public class CollectionService {

	@Autowired
	PublicService publicService;
	@Autowired
	FillSelectDao fillSelectDao;
	
    public List<SelectBean> getCollection(String param){
    	List<SelectBean> list = new ArrayList<SelectBean>();
//    	String[] statusArray = null;// {"Closed","Completed","Draft","In Progress","Invalid","Reversed","Void"};
//    	String[] transactionTypeArray = null;// {"Adjustment","Bad Debt","Check Payment","Check Refund","Credit Card Payment","Direct Deposit"};
//    	String[] tenderTypeArray = null;//{"Check","Creadit Card","Direct Deposit"};
//    	String[] paymentTypeArray = null;//{"Standard","Prepayment"};
//    	String[] cardArray =null;// {"VISA","MASTER","JBC","CLUB"};
    	String[] monthArray = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    	String[] yearArray = {"2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
//    	String[] currencyArray = null;//{"USD","JPY","RMB"};
    	String[] tempArray = null;
    	
    	List<PbDropdownListOptions> l = null;
    	
    	if(param.equals("Status")){
    		 l = this.publicService.getDropdownList("AR_TRANSACTION_STATUS");
    	}else if(param.equals("Transaction_Type")){
    		 l = this.publicService.getDropdownList("AR_TRANSACTION_TYPE");
    	}else if(param.equals("Tender_Type")){
    	     l  = this.publicService.getDropdownList("TRX_TENDER_TYPE");
    	}else if(param.equals("Payment_Type")){// PAYMENT_TYPE
    	     l  = this.publicService.getDropdownList("PAYMENT_TYPE");
    	}else if(param.equals("Card")){
    		 l  = this.publicService.getDropdownList("CREDIT_CARD_TYPE");
    	}else if(param.equals("Month")){
    		tempArray = monthArray;
    	}else if(param.equals("Year")){
    		tempArray = yearArray;
    	}else if(param.equals("Currency")){
    		tempArray = this.getCurrency();
    	}
    	
    	if(tempArray != null){
    		for(int i=0; i<tempArray.length; i++){
    			SelectBean bean = new SelectBean();
    			bean.setKey(tempArray[i]);
    			bean.setValue(tempArray[i]);
    			list.add(bean);
    		}
    	}else{
    	for(PbDropdownListOptions p : l){
    		SelectBean bean = new SelectBean();
			bean.setKey(p.getText());
			bean.setValue(p.getValue());
			list.add(bean);
    	}
    	}
    	
    	return list;
    }
    
    public String outCollection(List<SelectBean> list, String value){
    	String str = "";
    	for(int i=0; i<list.size(); i++){
    		SelectBean bean = list.get(i);
    		if(value != null && value.equals(bean.getValue())){
    			str += "<option value='" + bean.getValue() + "' selected>" + bean.getKey() + "</option>";
    		}else{
    			str += "<option value='" + bean.getValue() + "'>" + bean.getKey() + "</option>";
    		}
    	}
    	
    	return str;
    }
    
    public String[] getCurrency(){
    	String sql = "select  common.currency.currency_code,  common.currency.currency_code l from   common.currency";
    	List<Map > list = this.fillSelectDao.getSelect(sql);
    	String[] arr = new String[list.size()];
    	int i = 0;
    	if(list!= null && list.size()>0){
    	 	for(Map m : list){
        		arr[i] = m.get("text").toString();
        		i++;
        	}
    	}else{
    		return null;
    	}
    	return arr;
    }
}
