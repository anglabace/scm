package com.genscript.gsscm.shipment.service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.inventory.dao.ReservationDao;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.dao.ShipPackageDao;
import com.genscript.gsscm.shipment.dao.ShipPackageLineDao;
import com.genscript.gsscm.shipment.dto.ShipPackageLineDTO;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageLines;

@Service
@Transactional
public class ShipPackageLineService {

	/**
	 * spring注入shipPackageLineDao对象
	 */
	@Autowired
	private ShipPackageLineDao shipPackageLineDao;
	@Autowired
	private ShipPackageDao shipPackagesDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private DozerBeanMapper dozer;
	private final static String ROOTNODE = "0000";
	@Autowired
	private ReservationDao reservationDao;
	/**
	 * 根据userId查询User
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getByUserId(Integer userId){
		User user = this.shipPackageLineDao.getByUserId(userId);
		return user;
	}
	/**
	 * 通过packageId更新或删除Reservation表
	 * 
	 * @param packageId
	 */
	public void uptReservationBypackageId(String[] packageId) {
		
		for (int i = 0; i < packageId.length; i++) {
			List<ShipPackageLines> shipPackageLineList = this.shipPackageLineDao.getShipPackageLineList(Integer.parseInt(packageId[i]));
			if (shipPackageLineList != null && shipPackageLineList.size() > 0) {
				for (ShipPackageLines shipPackageLine : shipPackageLineList) {
					OrderItem orderItem = this.orderItemDao.getOrderItem(shipPackageLine.getOrderNo(), shipPackageLine.getItemNo());
					String orderItemType = orderItem.getType();
					int shipQty = shipPackageLine.getQuantity();
					Double shipSize = shipPackageLine.getSize();
                    //取得reservation 集合
					List<Reservation> reservationList = reservationDao.getReservation(shipPackageLine.getOrderNo(), shipPackageLine.getItemNo());
                     //对于每个 Reservation 进行减少库存
                    int overQty = 0;
                    //判断是否已经计算库存
                    boolean flag = false;
                    for (Reservation reservation : reservationList) {
                        int reserQty = reservation.getQty();
                        Double reserSize = reservation.getSize();
                        if(overQty == 0 && !flag){
                            overQty = reserQty - shipQty;
                            flag = true;
                        }else if(overQty == 0 && flag){
                            break;
                        }else if(overQty < 0){
                            overQty =  reserQty + overQty;
                        }
                        if (reserSize == null) {
                            reserSize = 0.0;
                        }
                        Double overSize = Arith.sub(reserSize , shipSize);
                        if("PRODUCT".equals(orderItemType)){
                        	 if (overQty <= 0 ) {
                                 reservationDao.delete(reservation);
                                 continue;
                             }
                        	 reservation.setQty(overQty);
                             reservation.setSize(orderItem.getSize());
                        }
                        if("SERVICE".equals(orderItemType)){
                        	 if (overSize <= 0) {
                                 reservationDao.delete(reservation);
                                 continue;
                             }
                        	 reservation.setQty(1);
                             reservation.setSize(overSize);
                        }
                      /*  if (overQty < 0 && overSize > 0) {
                            reservation.setQty(0);
                            reservation.setSize(overSize);
                        } else if (overQty > 0 && overSize < 0) {
                            reservation.setQty(overQty);
                            reservation.setSize(0d);
                        } else {
                            reservation.setQty(overQty);
                            reservation.setSize(overSize);
                        }*/
                       
                        reservationDao.uptReservation(reservation);
                    }
				}
			}
		}
	}
	

	/**
	 * 通过packageId查询
	 * @param packageId
	 * @return
	 */
	public List<ShipPackage> getShipPackageList (Integer packageId) {
		return this.shipPackageLineDao.getShipPackageList(packageId);
	}

	/**
	 * 美国，根据Package No查询，返回一个list集合
	 * 
	 * @param packageId
	 * @return List
	 */
	public Page<ShipPackageLineDTO> getListSpl(Page<ShipPackageLines> page,
			Integer packageId) {
		Page<ShipPackageLineDTO> retPage = this.shipPackageLineDao.getListSpl(
				page, packageId);
		return retPage;
	}

	/**
	 * 根据itemNo查询并返回ShipPackages对象
	 * 
	 * @param itemNo
	 * @return
	 */
	public ShipPackage getShippageById(Integer id) {
		//ShipPackage sp = this.shipPackageLineDao.getShippageById(itemNo);
		ShipPackage sp = this.shipPackagesDao.getById(id);
		return sp;
	}
	/**
	 * 根据tracking查询并返回ShipPackages对象
	 * 
	 * @param itemNo
	 * @return
	 */
	public ShipPackage getShippageByTracking(String tracking) {
		//ShipPackage sp = this.shipPackageLineDao.getShippageById(itemNo);
		ShipPackage sp = this.shipPackagesDao.findUniqueBy("trackingNo", tracking);
		return sp;
	}

	/**
	 * 根据trackingNo和itemNo和orderNo查询shipPackageLines中的qty和size以及ReceivingLogs中的qty和size比较
	 * 
	 * @param trackingNo
	 * @param itemNo
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findShipReceiveLine(String trackingNo, Integer itemNo,
			Integer orderNo) {
		List list = this.shipPackageLineDao.findShipReceiveLine(trackingNo,
				itemNo, orderNo);
		return list;
	}

	/**
	 * 更新ShipPackageLInes状态
	 * 
	 * @param orderNo
	 * @param itemNo
	 */
	public void updateShipReceive(String orderNo, Integer itemNo) {
		this.shipPackageLineDao.updateShipReceive(orderNo, itemNo);
	}

	/**
	 * 更新ShipPackages状态
	 * 
	 * @param orderNo
	 * @param itemNo
	 */
	public void updateShipPkReceive(String packageId) {
		this.shipPackageLineDao.updateShipPkReceive(packageId);
	}

	/**
	 * 保存ShipPackageLines对象信息
	 * 
	 * @param spl
	 */
	public void saveShipPackageLines(ShipPackageLines spl) {
		this.shipPackageLineDao.saveShipPackageLines(spl);
	}

	/**
	 * 根据orderNo和itemNo查询，返回一个list集合
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findSpk_lines(Integer orderNo, Integer itemNo) {
		List list = this.getShipPackageLineDao().findSpk_lines(orderNo, itemNo);
		return list;
	}

	/**
	 * 根据packageId查询，返回一个ShipPackageLines对象
	 * 
	 * @param packageId
	 *            参数
	 * @return ShipPackageLines 实体
	 */
	public ShipPackageLines findPackagePackId(Integer packageId) {
		ShipPackageLines shipPackageLines = this.shipPackageLineDao.findPackagePackId(packageId);
		return shipPackageLines;
	}
	
	/**
	 * 根据packageId查询，返回ShipPackageLines对象
	 * 
	 * @param packageId
	 *            参数
	 * @return ShipPackageLines 实体
	 */
	public List<ShipPackageLines> findPackageLineList(String packageId) {
		List<ShipPackageLines> shipPackageLines = this.shipPackageLineDao.findPackageLineList(packageId);
		return shipPackageLines;
	}
	/**
	 * 根根据packageId查询，返回一个ShipPackageLines对象
	 * 
	 * @param orderNo
	 * @return
	 */
	public ShipPackageLines searchSplByPackageId(String packageId) {
		ShipPackageLines spl = this.shipPackageLineDao.findPackagePackId(packageId);
		return spl;
	}
	
	/*
	 * 根据orderNo 判断是否有打包的数据，有返回flase 无返回ture
	 */
	public boolean chkPackageLine(Integer orderNo){
		boolean chk = false;
		List<ShipPackageLines> linesList = this.shipPackageLineDao.findBy("orderNo", orderNo);
		if(linesList==null||linesList.isEmpty()){
			chk = true;
		}
		return chk;
	}
	public List<ShipPackageLines> searchSplByPackageId(Integer packageId) {
		List<ShipPackageLines> lines = this.shipPackageLineDao.findBy("shipPackages.packageId", packageId);
		return lines;
	}

	public ShipPackageLineDao getShipPackageLineDao() {
		return shipPackageLineDao;
	}

	public void setShipPackageLineDao(ShipPackageLineDao shipPackageLineDao) {
		this.shipPackageLineDao = shipPackageLineDao;
	}

	public DozerBeanMapper getDozer() {
		return dozer;
	}

	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}

	public static String getROOTNODE() {
		return ROOTNODE;
	}
}
