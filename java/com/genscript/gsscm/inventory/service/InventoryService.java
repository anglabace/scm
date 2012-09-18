package com.genscript.gsscm.inventory.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.inventory.dao.ReservationDao;
import com.genscript.gsscm.inventory.dao.StockDetailDao;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.entity.Reservation;
import com.genscript.gsscm.inventory.entity.StockDetail;
import com.genscript.gsscm.inventory.entity.Warehouse;
@Service
@Transactional
public class InventoryService {
  @Autowired
  private WarehouseDao warehouseDao;
  @Autowired
  private StockDetailDao stockDetailDao;
  @Autowired
  private ReservationDao reservationDao;
  
  /**
   * 获得系统中所有的warehouse.
   * @return
   */
  
  public List<Warehouse> getAllWarehouse() {
	  return this.warehouseDao.getAll();
  }
  
  /**
   * 获得一个公司的默认的warehouse.
   * @param companyId
   * @return
   */
  public Warehouse getCompanyDefaultWarehouse(Integer companyId) {
	  return this.warehouseDao.getCompanyDefaultWarehouse(companyId);	  
  }
  
  /**
   * 取得库存
   * @param warehouseId
   * @param storageId
   * @param catalogNo
   * @return
   */
  public Integer getStockSum(Integer warehouseId, Integer storageId, String catalogNo){
	  List<StockDetail> stockList = this.stockDetailDao.getStockDetailList(storageId, warehouseId, catalogNo);
	  Integer sum = 0;
	  if(stockList != null && stockList.size() > 0){
		  for(StockDetail stock: stockList){
			  //onhand_qty - reserved_qty
			  Integer onhandQty = stock.getOnhandQty();
			  Integer reserverdQty = stock.getReservedQty();
			  if(onhandQty == null){
				  onhandQty = 0;
			  }
			  if(reserverdQty == null){
				  reserverdQty = 0;
			  }
			  sum += (onhandQty - reserverdQty);
		  }
	  }
	  return sum;
  }
  
  /**
   * confirm订单时候消耗库存
   * @param warehouseId
   * @param storageId
   * @param catalogNo
   * @param qty
   */
  public void reserveQty(Integer orderNo, Integer itemNo, Integer warehouseId, Integer storageId, 
		  String catalogNo, Integer qty, Integer userId, String qtyUom, Double size, String sizeUom){
	  //数据检查
	  if(warehouseId == null || catalogNo == null ||qty == null || qty.intValue() == 0){
		  return;
	  }
	  //构造库存记录
	  Reservation newReservation = new Reservation();
	  newReservation.setCatalogNo(catalogNo);
	  newReservation.setOrderNo(orderNo);
	  newReservation.setItemNo(itemNo);
	  newReservation.setQty(qty);
	  newReservation.setQtyUom(qtyUom);
	  newReservation.setSize(size);
	  newReservation.setSizeUom(sizeUom);
	  newReservation.setReserveDate(new Date());
	  newReservation.setReservedBy(userId);
	  this.reservationDao.save(newReservation);
  }
  
  /**
   * 取消消耗掉的库存
   * @param orderNo
   * @param itemNo
   */
  public void cancelReserveQty(Integer orderNo, Integer itemNo){
	  List<Reservation> resList = this.reservationDao.getReservationList(orderNo, itemNo);
	  if(resList != null && resList.size() > 0){
		  for(Reservation res : resList){
			  this.reservationDao.delete(res.getId());
		  }
	  }
  }
  
}
