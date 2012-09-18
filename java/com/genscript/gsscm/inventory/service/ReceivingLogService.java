package com.genscript.gsscm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.inventory.dao.ReceivingLogDao;
import com.genscript.gsscm.inventory.entity.ReceivingLog;
@Service
@Transactional
public class ReceivingLogService {
	
	/**
	 * spring注入purchaseOrderItemDao对象
	 */
	@Autowired
	private ReceivingLogDao receivingLogDao;

	/**
	 * 添加到receivingLogs数据
	 * 
	 * @param rl
	 */
	public void addReceivingLogs(ReceivingLog rl) {
		this.receivingLogDao.addReceivingLogs(rl);
	}
	/**
	 * 根据开始时间，和结束时间，查询receiving_logs中order_no
	 * @param reveivingDate
	 * @param reveivingDates
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLogs(String reveivingDate,String reveivingDates,String type,String clerk) {
		List list = this.receivingLogDao.getLogs(reveivingDate,reveivingDates,type,clerk);
		return list;
	}
	/**
	 * 根据orderNo查询ReceivingLogs数据
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLogsByOrderNo(String reveivingDate,String reveivingDates,String type,String orderNo,String clerk){
		List list = this.receivingLogDao.getLogsByOrderNo(reveivingDate,reveivingDates,type,orderNo,clerk);
		return list;
	}

	/**
	 * 根据trackingNo查询ReceivingLogs、ShipPackageLines
	 * @param trackingNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findReShipLines(String trackingNo){
		List list = this.receivingLogDao.findReShipLines(trackingNo);
		return list;
	}
	public ReceivingLogDao getReceivingLogsDao() {
		return receivingLogDao;
	}

	public void setReceivingLogsDao(ReceivingLogDao receivingLogDao) {
		this.receivingLogDao = receivingLogDao;
	}

}
