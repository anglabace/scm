package com.genscript.gsscm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.inventory.dao.ReservationDao;
import com.genscript.gsscm.inventory.entity.Reservation;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	
	public List<Reservation> getReservationList(Integer orderNo, Integer itemNo){
		return this.reservationDao.getReservationList(orderNo, itemNo);
	}
	
	public void saveReservation(Reservation entity){
		this.reservationDao.save(entity);
	}
}
