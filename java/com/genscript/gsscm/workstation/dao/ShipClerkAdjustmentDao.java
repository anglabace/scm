package com.genscript.gsscm.workstation.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.workstation.entity.ShippingClerkAdjustment;

@Repository
public class ShipClerkAdjustmentDao extends HibernateDao<ShippingClerkAdjustment, Integer>{
	/**
	 * 更新ShippingClerkAdjustment
	 * @param  adjustment 参数
	 * @return String     返回String类型
	 */
	public String updateClerk(ShippingClerkAdjustment adjustment)throws Exception{
		String flag = "0";
		try {
			this.save(adjustment);
			flag = "1";
		} catch (Exception ex) {
			throw ex;
		}
		return flag;
	}
}
