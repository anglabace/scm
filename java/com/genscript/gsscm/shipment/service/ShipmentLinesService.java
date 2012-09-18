package com.genscript.gsscm.shipment.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.dao.ShipPackageDao;
import com.genscript.gsscm.shipment.dao.ShipmentLinesDao;
import com.genscript.gsscm.shipment.dao.ShipmentsDao;
import com.genscript.gsscm.shipment.dto.ShipPackageDTO;
import com.genscript.gsscm.shipment.dto.ShipmentLinesDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsDTO;
import com.genscript.gsscm.shipment.dto.ShipmentsSrchDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;


@Service
@Transactional
public class ShipmentLinesService{
	@Autowired
	private ShipmentsDao shipmentsdao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShipPackageDao packagesdao;
	@Autowired
	private ShipmentLinesDao shipmentlinesdao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	public ShipmentsDao getShipmentsDaodao() {
		return shipmentsdao;
	}

	public void setShipmentsDaodao(ShipmentsDao shipmentsdao) {
		this.shipmentsdao = shipmentsdao;
	}

	/**
	 * 根据条件查询Shipment并返回Page对象
	 * @param page
	 * @param shipschdto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ShipmentsDTO> searchShipments(Page<Shipment> page, ShipmentsSrchDTO shipschdto) {
		Page<ShipmentsDTO> retPage = new Page<ShipmentsDTO>();
		List<ShipmentsDTO> dtoList = new ArrayList<ShipmentsDTO>();
		page = this.shipmentsdao.searchShipments(page, shipschdto);
		if (page.getResult() != null) {
			for (Shipment ship : page.getResult()) {
				ShipmentsDTO dto = dozer.map(ship, ShipmentsDTO.class);
				dto.setWarehouse(ship.getWareHouse());
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/**
	 * 根据条件查询Shipment并返回Page对象
	 * @param page
	 * @param filters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<ShipmentsDTO> searchShipments(Page<Shipment> page,
			List<PropertyFilter> filters) {
		Page<ShipmentsDTO> retPage = new Page<ShipmentsDTO>();
		List<ShipmentsDTO> dtoList = new ArrayList<ShipmentsDTO>();
		page = this.shipmentsdao.searchShipments(page, filters);
		if (page.getResult() != null) {
			for (Shipment ship : page.getResult()) {
				ShipmentsDTO dto = dozer.map(ship, ShipmentsDTO.class);
				dto.setWarehouse(ship.getWareHouse());
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	/**
	 * 根据packageId查询细节(中国仓库)
	 * @param packageId
	 * @return
	 */
	public ShipPackageDTO getPckById(Integer packageId) {
		ShipPackageDTO pDto = null;
		ShipPackage app = this.packagesdao.findUniqueBy("packageId", packageId);
		if (app != null) {
			pDto = dozer.map(app, ShipPackageDTO.class);
		}
		this.shipmentsdao.getSession().evict(app);
		return pDto;
	}
	
	/**
	 * 根据shipmentNo查询细节(美国仓库)
	 * @param shipmentNo
	 * @return
	 */
	public ShipmentsDTO getShipById(String shipmentNo) {
		ShipmentsDTO sDto = null;
		Shipment app = this.shipmentsdao.findUniqueBy("shipmentNo", shipmentNo);
		if (app != null) {
			sDto = dozer.map(app, ShipmentsDTO.class);
		}
		this.shipmentsdao.getSession().evict(app);
		return sDto;
	}
	
	/**
	 * 根据shipmentNo查询并返回ShipmentLines对象
	 * @param shipmentNo
	 * @return
	 */
	public ShipmentLinesDTO getLineById(String shipmentNo) {
		ShipmentLinesDTO slDto = null;
		ShipmentLine app = this.shipmentlinesdao.findUniqueBy("shipmentNo", shipmentNo);
		if (app != null){
			slDto = dozer.map(app, ShipmentLinesDTO.class);
		}
		this.shipmentlinesdao.getSession().evict(app);
		return  slDto;
	}
	
	/**
	 * 根据orderNo查询并返回ShipmentLines对象
	 * @param orderNo
	 * @return
	 */
	public List<ShipmentLine> getLineByOrderNo(Integer orderNo) {
		
		return  this.shipmentlinesdao.findBy("order.orderNo", orderNo);
	}
	
/*	public List<Shipment> getShipmentByOrderNo(List<Integer> orderNo){
		if(orderNo!=null&&!orderNo.isEmpty()){
			return this.shipmentlinesdao.searchShipment(orderNo);
		}else{
			return null;
		}
		
	}*/

	/**
	 * 查询shipment对象并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByClert() {
		List list = this.shipmentsdao.getListByClert();
		return list;
	}
	
	/**
	 * 查询有操作权限的用户信息并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLoginName() {
		List list = this.shipmentsdao.getLoginName(null);
		return list;
	}
	
	/**
	 * 查询status并返回List
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public List getStatus(){
		List list = this.shipmentsdao.getStatus();
		return list;
	}*/
	
	/**
	 * 查询Warehouse对象并返回List
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getListByWareHouse() {
		List list = this.shipmentsdao.getListByWarehouse();
		return list;
	}
	
	/**
	 * 根据warehouseId查询Warehouse对象信息
	 * @param shipmentNo
	 * @return
	 */
	public Warehouse findById(Integer warehouseId) {
		Warehouse wh = this.shipmentsdao.findById(warehouseId);
		return wh;
	}
	
	/**
	 * 针对美国仓库的查询列表,参数error,用于判断yes/no
	 * @param page
	 * @param srch
	 * @param error
	 * @return
	 */
	public Page<ShipmentsDTO> searchu(Page<Shipment> page, ShipmentsDTO srch,String error)
	{
		List<ShipMethod> shipmethodList = this.shipMethodDao.getAll();
		Page<ShipmentsDTO> retPage = new Page<ShipmentsDTO>();
		retPage = this.shipmentsdao.searchu(page, srch, error,shipmethodList);
		return retPage;
	}
	
	/**
	 * 针对中国仓库的查询列表,参数error,用于判断yes/no
	 * @param page
	 * @param srch
	 * @param error
	 * @return
	 */
	public Page<ShipPackageDTO> searchch(Page<ShipPackage> page, ShipPackageDTO srch,String error)throws Exception{
		Page<ShipPackageDTO> retPage = new Page<ShipPackageDTO>();
		retPage = this.packagesdao.searchch(page, srch,error);
		return retPage;
	}
	
	/*
	 * save shipment line
	 */
	public void saveShipmentLine(ShipmentLine shipmentLine){
		this.shipmentlinesdao.save(shipmentLine);
	}
	
	/**
	 * 根据line对象更新shipmentLine对象的description信息
	 * @param line
	 * @return String
	 */
	public String updateDescription(ShipmentLine line){
		try {
			return this.shipmentlinesdao.updateDescription(line);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "0";
	}
	
	public String getOrderByShipmentId(Integer shipmentId){
		List<Integer> orderNos = this.shipmentlinesdao.getOrderNo(shipmentId);
		String orderNo="";
		if(orderNos!=null&&!orderNos.isEmpty()){
			for(Integer no:orderNos){
				if(orderNo.equals("")){
					orderNo+=no;
				}else{
					orderNo +=","+no;
				}
			}
		}
		return orderNo;
	}

	public ShipPackageDao getPackagesdao() {
		return packagesdao;
	}

	public void setPackagesdao(ShipPackageDao packagesdao) {
		this.packagesdao = packagesdao;
	}

	public ShipmentsDao getShipmentsdao() {
		return shipmentsdao;
	}

	public void setShipmentsdao(ShipmentsDao shipmentsdao) {
		this.shipmentsdao = shipmentsdao;
	}

	public ShipmentLinesDao getShipmentlinesdao() {
		return shipmentlinesdao;
	}

	public void setShipmentlinesdao(ShipmentLinesDao shipmentlinesdao) {
		this.shipmentlinesdao = shipmentlinesdao;
	}
}