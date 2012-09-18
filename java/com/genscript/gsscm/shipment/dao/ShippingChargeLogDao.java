package com.genscript.gsscm.shipment.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.shipment.entity.ShippingChargeLog;

@Repository
public class ShippingChargeLogDao extends HibernateDao<ShippingChargeLog,Integer>{
	private static final String LAST_CHARGE_DATE = "FROM ShippingChargeLog s where s.custNo=? and s.chargeAccType=? and s.chargeAccNo=? ORDER BY s.chargeDate DESC LIMIT 1";
	public Date getChargeLastDate(Integer custNo, String chargeAccType, String chargeAccNo) {
        OrderProcessLog log = (OrderProcessLog) this.createQuery(LAST_CHARGE_DATE, custNo, chargeAccType, chargeAccNo).setMaxResults(1).uniqueResult();
        if (log != null) {
            return log.getProcessDate();
        } else {
            return null;
        }
    }
}
