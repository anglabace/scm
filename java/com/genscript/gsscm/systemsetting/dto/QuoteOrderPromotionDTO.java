package com.genscript.gsscm.systemsetting.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.order.entity.PromotionBean;

public class QuoteOrderPromotionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892988093504219865L;
	private Map<String, PromotionBean> map;
	private String pager;
	private int total;
	public Map<String, PromotionBean> getMap() {
		return map;
	}
	public void setMap(Map<String, PromotionBean> map) {
		this.map = map;
	}
	public String getPager() {
		return pager;
	}
	public void setPager(String pager) {
		this.pager = pager;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
