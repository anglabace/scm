package com.genscript.gsscm.accounting.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.entity.SelectBean;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.opensymphony.xwork2.ActionSupport;

@Results( {
	@Result(name = "success", location = "accounting/payment/record_transaction.jsp")
})

public class SelectAction extends ActionSupport {
	
	@Autowired
	PublicService publicService;
	
	 public List<SelectBean> statusList;
	 
	 public void setStatusList(List<SelectBean> statusList){
		 this.statusList = statusList;
	 }
	 
	 public List<SelectBean> getStatusList(){
		 return statusList;
	 }
	 
	 @Override
	 public String execute() throws Exception {
		 List<SelectBean> statusList = new ArrayList<SelectBean>();
		 String[] status = null; //{"Closed", "Completed", "Drafted","In Progress","Invalid","Reversed","Void"};
		 List<PbDropdownListOptions> list =  this.publicService.getDropdownList("AR_TRANSACTION_STATUS");
		 for(PbDropdownListOptions o : list){
//				sb.append("<option value='"+o.getValue()+"'>"+o.getText()+"</option>");
			 SelectBean bean = new SelectBean();
			 bean.setKey(o.getText());
			 bean.setValue(o.getValue());
			 statusList.add(bean);	
		 }
//		 for(int i=0; i<status.length; i++){
//			 SelectBean bean = new SelectBean();
//			 bean.setKey(status[i]);
//			 bean.setValue(status[i]);
//			 statusList.add(bean);
//		 }
		 setStatusList(statusList);
		 
		 return "success";
	 }
	 	
}
