package com.genscript.gsscm.accounting.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.accounting.dao.FillSelectDao;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.util.Struts2Util;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 自动填充select选择框
 * @author Administrator
 *
 */
public class FillSelectAction extends ActionSupport {

	/**
	 * 自动填充select选择框,利用ajax
	 * @return
	 */
	public String sql ;
	
	public String field ;
	
	@Autowired
	FillSelectDao fillSelectDao;
	@Autowired
	PublicService publicService;
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * 从数据库中查询出数据
	 * @return
	 */
	public String fillSelect(){
		List<Map> list = this.fillSelectDao.getSelect(this.getSql());
		StringBuffer sb = new StringBuffer();
		for(Map m: list){
			sb.append("<option value='"+m.get("value")+"'>"+m.get("text")+"</option>");
		}
		sb.toString();
		Struts2Util.renderHtml(sb.toString());
		return null;
	}
	
	/**
	 * 从数据库中查询出数据
	 * @return
	 */
	public String fillSelect2(){
		String sqlField = Struts2Util.getParameter("sqlField");
		List<Map> list = this.fillSelectDao.getInvoiceState(sqlField);
		StringBuffer sb = new StringBuffer();
		for(Map m: list){
			sb.append("<option value='"+m.get("value")+"'>"+m.get("text")+"</option>");
		}
		sb.toString();
		Struts2Util.renderHtml(sb.toString());
		return null;
	}
	
	/**
	 * 从接口中获取list
	 * @return
	 */
	public String getList(){
		List<PbDropdownListOptions> list = 
		this.publicService.getDropdownList(this.getField());
		StringBuffer sb = new StringBuffer();
		for(PbDropdownListOptions o : list){
			sb.append("<option value='"+o.getValue()+"'>"+o.getText()+"</option>");
		}
		Struts2Util.renderHtml(sb.toString());
	    return null;
	}
	
	/**
	 * 
	 * 初始化content to send
	 * @return
	 */
	public String initContentToSend(){
		List<Map> list = this.fillSelectDao.queryEntity();
		StringBuffer sb = new StringBuffer();
		sb.append("<option value=''>other</option>");
		for(Map m: list){
			sb.append("<option value='"+m.get("value")+"'>"+m.get("text")+"</option>");
		}
		sb.toString();
		Struts2Util.renderHtml(sb.toString());
		return null;
	}
	
	
}
