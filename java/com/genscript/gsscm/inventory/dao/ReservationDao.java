package com.genscript.gsscm.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.inventory.entity.Reservation;

@Repository
public class ReservationDao extends HibernateDao<Reservation, Integer> {
	
	/**
	 * 通过orderNo和itemNo查询唯一记录的Reservation
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public List<Reservation> getReservation (Integer orderNo, Integer itemNo) {
		String hql = "FROM Reservation where orderNo = :orderNo and itemNo=:itemNo order by id";
        List<Reservation> list = this.getSession().createQuery(hql).setInteger("orderNo", orderNo).setInteger("itemNo", itemNo).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationList(Integer orderNo, Integer itemNo){
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("orderNo", orderNo));
		criteria.add(Restrictions.eq("itemNo", itemNo));
		return criteria.list();
	}
	
	/**
	 * 更新reservation
	 * @param reservation
	 */
	public void uptReservation (Reservation reservation) {
		this.save(reservation);
	}
	
	/**
	 * 删除reservation
	 * @param reservation
	 */
	public void delReservation (Reservation reservation) {
		this.delete(reservation);
	}
}
