package com.genscript.gsscm.shipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.shipment.dao.ShipPackageDao;
import com.genscript.gsscm.shipment.dao.ShipPackageLineDao;
import com.genscript.gsscm.shipment.dao.ShipmentLinesDao;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;

@Service
@Transactional
public class ShipPackageService {
	/**
	 * spring注入orderItemDao
	 */
	@Autowired
	private ShipPackageDao shipPackageDao;
	@Autowired
	private ShipPackageLineDao shipPackageLineDao;
	@Autowired
	private ShipmentLinesDao shipmentlinesdao;

	/**
	 * 更新到shipPackages数据
	 * @param sps
	 */
	public void saveSps(ShipPackage sps){
		this.shipPackageDao.save(sps);
	}
	public ShipPackageDao getShipPackageDao() {
		return shipPackageDao;
	}
	public void setShipPackageDao(ShipPackageDao shipPackageDao) {
		this.shipPackageDao = shipPackageDao;
	}
	
	public void updatePackageByTrackingNumber(String trNo){
		List<ShipPackageLines> packageLines = this.shipPackageLineDao.findLineByPackagetrNo(trNo);
		if(packageLines!=null){
			this.shipPackageLineDao.getSession().evict(packageLines);
			//List<Integer> shipmentLineList = new ArrayList<Integer>();
			for(ShipPackageLines packageLine:packageLines){
				packageLine.getShipmentLines().setStatus("Drafted");
				this.shipmentlinesdao.save(packageLine.getShipmentLines());
			}
		}
		this.shipPackageDao.updateShipPackagesByrackingNo(trNo);
	}
	
	public String getShipClerkIdOfShipPackage(Integer shipmentId){
		
		return null;
	}
	
	/**
	 * 通过orderNo和Item查询
	 * @author Zhang Yong
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	public List<ShipPackage> findByOrderNoAndItemNo (Integer orderNo, Integer itemNo) {
		return this.shipPackageLineDao.findByOrderNoAndItemNo(orderNo, itemNo);
	}
}
