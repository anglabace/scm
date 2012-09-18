package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.Coupon;

@Repository
public class CouponDao extends HibernateDao<Coupon, Integer> {

	private static final String DEL_COUPONS = "delete from Coupon c where c.id in (:ids)";
	private static final String SELECT_COUPONS_BY_CODE = "from Coupon c where code = ?)";
	private static final String SELECT_AVALIABLE_COUPONS = "from Coupon c where c.custNo is null and upper(c.status) = 'ACTIVE' group by c.value";
	private static final String SELECT_BY_CUSTNO = "from Coupon where custNo = ? order by exprDate desc";
	
	/**
	 * 通过主键Id批量删除coupon
	 * @param ids
	 * @return
	 */
	public int delCoupons(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		return batchExecute(DEL_COUPONS, map);
	}
	
	/**
	 * 通过code查询coupon
	 * @param code
	 * @return
	 */
	public List<Coupon> findCouponByCode (String code) {
		return this.find(SELECT_COUPONS_BY_CODE, code);
	}
	
	/**
	 * 查询可供兑换的coupon
	 * @author zhangyong
	 * @return
	 */
	public List<Coupon> findAvaliableCoupon () {
		return this.find(SELECT_AVALIABLE_COUPONS);
	}
	
	/**
	 * 通过custNo查询
	 * @author zhangyong
	 * @param custNo
	 * @return
	 */
	public List<Coupon> findCouponByCustNo (Integer custNo) {
		return this.find(SELECT_BY_CUSTNO,custNo);
	}

    public List<Coupon> findCouponByIds (String ids) {
        Query query = this.getSession().createQuery("from Coupon where id in(" + ids + ")");
		return query.list();
	}
}
