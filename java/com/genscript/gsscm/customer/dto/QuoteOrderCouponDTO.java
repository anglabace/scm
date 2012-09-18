package com.genscript.gsscm.customer.dto;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.customer.entity.Coupon;

/**
 * @author zhangyong
 * @author user
 *
 */
public class QuoteOrderCouponDTO implements Serializable {

	private static final long serialVersionUID = 4342436144276685017L;
	private Map<String, Coupon> map;
	private String pager;
	private int total;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public Map<String, Coupon> getMap() {
		return map;
	}
	public void setMap(Map<String, Coupon> map) {
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
}
