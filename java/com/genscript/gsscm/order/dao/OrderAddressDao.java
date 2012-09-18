package com.genscript.gsscm.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderAddress;

@Repository
public class OrderAddressDao extends HibernateDao<OrderAddress, Integer>{
	private static final String GET_ADDRESS_ID = "select o.addrId from OrderAddress o where o.orderNo=? and o.addrType=?";
	
	public List<Integer> getAddressIdByType(final Integer orderNo,final String addressType){
		return find(GET_ADDRESS_ID, orderNo, addressType);
	}
	
	public List<OrderAddress> getAddrByOrderNo(Integer orderNo) {
		String hql = "select o from OrderAddress o where o.orderNo=?";
		return this.find(hql, orderNo);
	}
	
	/**
	 * modify by Zhang Yong
	 * modify date 2011-11-11
	 * @param orderNo
	 * @param addrType
	 * @return
	 */
	public OrderAddress getAddrByOrderNoAndType(Integer orderNo,String addrType) {
		String hql = "select o from OrderAddress o where o.orderNo=? and addrType=?";
		return findUnique(hql, orderNo,addrType);
	}
	
	/**
	 * 通过主键查询
	 * @author zhangyong
	 * @param addrId
	 * @return
	 */
	public OrderAddress findByAddrId (Integer addrId) {
		return this.findUniqueBy("addrId", addrId);
	}
}
