package com.genscript.gsscm.systemsetting.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.customer.entity.Source;

public class QuoteOrderSourceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227596468895544278L;
//	private List<Source> list;
	private Map<String, Source> map;
	private String pager;
	private int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
//	public List<Source> getList() {
//		return list;
//	}
//	public void setList(List<Source> list) {
//		this.list = list;
//	}
	public String getPager() {
		return pager;
	}
	public void setPager(String pager) {
		this.pager = pager;
	}
	public Map<String, Source> getMap() {
		return map;
	}
	public void setMap(Map<String, Source> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
