package com.genscript.gsscm.accounting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.accounting.dao.ArShipmentLinesDao;
import com.genscript.gsscm.accounting.dto.OrderNoDTO;

@Service
@Transactional
public class ArShipmentLinesService {

	@Autowired
	private ArShipmentLinesDao arShipmentLinesDao;
	
	/**
	 * 根据shipmentNo查询相关联的shipmentLines
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<OrderNoDTO> searchShipmentLinesByShipmentNo(Page<OrderNoDTO> page,
			String filter_EQS_shipmentNo, String filter_EQI_orderNo,String custNo) throws Exception {
		Integer shipmentNo = 0;
		Integer orderNo = 0;
		Integer newcustNo = 0;
		if (null != filter_EQS_shipmentNo && !("").equals(filter_EQS_shipmentNo)) {
			shipmentNo = Integer.parseInt(filter_EQS_shipmentNo);
		}
		if (null != filter_EQI_orderNo && !("").equals(filter_EQI_orderNo)) {
			orderNo = Integer.parseInt(filter_EQI_orderNo);
		}
		if (null != custNo && !("").equals(custNo)) {
			newcustNo = Integer.parseInt(custNo);
		}
		return arShipmentLinesDao.findPage(page, shipmentNo, orderNo,newcustNo);
	}
}
