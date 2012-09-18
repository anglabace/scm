package com.genscript.gsscm.manufacture.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.inventory.entity.Warehouse;
import com.genscript.gsscm.manufacture.dao.WoBatchErrorDao;
import com.genscript.gsscm.manufacture.dao.WoBatcheDao;
import com.genscript.gsscm.manufacture.dao.WorkGroupDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderDao;
import com.genscript.gsscm.manufacture.dao.WorkOrderGroupDao;
import com.genscript.gsscm.manufacture.dto.WorkOrderDTO;
import com.genscript.gsscm.manufacture.entity.WoBatchError;
import com.genscript.gsscm.manufacture.entity.WoBatche;
import com.genscript.gsscm.manufacture.entity.WorkGroup;
import com.genscript.gsscm.manufacture.entity.WorkOrder;
import com.genscript.gsscm.manufacture.entity.WorkOrderGroup;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.shipment.dao.ShipPackageErrorsDao;
import com.genscript.gsscm.shipment.entity.ShipPackageErrors;
@Service
@Transactional
public class WorkOrderService {
	
	/**
	 * spring注入workOrderDao对象
	 */
	@Autowired
	private WorkOrderDao workOrderDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ShipPackageErrorsDao shipPackageErrorDao;
	@Autowired
	private WoBatcheDao woBatchDao;
	@Autowired
	private WoBatchErrorDao woBatchErrorDao;
	@Autowired
	private WorkOrderGroupDao workOrderGroupDao;
	@Autowired
	private WorkGroupDao workGroupDao;
	private final static String ROOTNODE = "0000";
	
	public void saveShipPackageError(ShipPackageErrors spe){
		this.shipPackageErrorDao.save(spe);
	}
	public void saveWoBatchError(WoBatchError wobe){
		this.woBatchErrorDao.save(wobe);
	}
	public WoBatche getWoBatcheById(Integer id){
		return this.woBatchDao.get(id);
	}
	
	public WorkOrder getWorkOrderById(Integer id){
		return this.workOrderDao.getById(id);
	}
	/**
	 * wo根据条件查询
	 * 
	 * @param page
	 * @param srch
	 * @return
	 */
	public Page<WorkOrderDTO> searchwo(Page<WorkOrder> page, WorkOrderDTO srch) {
		Page<WorkOrderDTO> pageDto = this.getWorkOrderDao().searchwo(page, srch);
		if(pageDto!=null&&pageDto.getResult()!=null&&pageDto.getResult().size()>0) {
			for(WorkOrderDTO dto:pageDto.getResult()) {
				List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", dto.getOrderNo());
				if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
					StringBuffer workGroupNames = null;
					for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
						if (workOrderGroup.getWorkGroupId() != null) {
							WorkGroup workGroup = workGroupDao.get(workOrderGroup
									.getWorkGroupId());
							if (workGroup != null&&workGroupNames==null) {
								workGroupNames = new StringBuffer();
								workGroupNames.append(workGroup.getName());
							} else if(workGroup!=null){
								workGroupNames.append(",").append(workGroup.getName());
							}
						}
					}
					dto.setWorkGroupName(workGroupNames.toString());
				}
			}
		}
		return pageDto;
	}

	/**
	 * wo分页
	 * @param page
	 * @param filters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<WorkOrderDTO> searchWorkOrder(Page<WorkOrder> page,
			List<PropertyFilter> filters) {
		Page<WorkOrderDTO> retPage = new Page<WorkOrderDTO>();
		List<WorkOrderDTO> dtoList = new ArrayList<WorkOrderDTO>();
		page = workOrderDao.searchWorkOrder(page, filters);
		if (page.getResult() != null) {
			for (WorkOrder user : page.getResult()) {
				WorkOrderDTO dto = dozer.map(user, WorkOrderDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}

	/**
	 * 查询warehouse数据
	 * 
	 * @return
	 */
	public List<Warehouse> getListByWarehouse() {
		List<Warehouse> list = this.workOrderDao.getListByWarehouse();
		return list;
	}

	/**
	 * 根据orderNo查询workorder表
	 * @param orderNo
	 * @return
	 */
	public List<WorkOrder> getListWo(Integer orderNo) {
		List<WorkOrder> list = this.workOrderDao.getListWo(orderNo);
		return list;
	}

	/**
	 * 根据orderNo查询，返回一个workOrder
	 * 
	 * @param orderNo
	 * @return
	 */
	public WorkOrderDTO searchWoByOrderNo(Integer orderNo,String batchNo) {
		WorkOrderDTO wo = this.workOrderDao.searchWoByOrderNo(orderNo,batchNo);
		return wo;
	}

	/**
	 * 根据type查询warehouse表
	 * 
	 * @param warehouseId
	 * @return
	 */
	public Warehouse findByType(String warehouseId) {
		Warehouse wh = this.workOrderDao.findByType(warehouseId);
		return wh;
	}

	/**
	 * 根据条件查询用户
	 * 
	 * @return
	 */
	public List<User> getListByReceiving(Integer userId) {
		List<User> list = this.workOrderDao.getListByReceiving(userId);
		return list;
	}

	/**
	 * 查询batchId
	 * 
	 * @return
	 */
	public List<WoBatche> getListBatchNo() {
		List<WoBatche> list = this.workOrderDao.getListBatchNo();
		return list;
	}

	/**
	 * WorkOrder Received All
	 * @param page 分页对象
	 * @param srch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List searchWoIDetailAll(String[] orderNo) {
		List list = this.workOrderDao.searchwoIDetailAll(orderNo);
		return list;
	}

	/**
	 * 查询wo状态
	 * @param trackingNo
	 * @return ShipPackages
	 */
	public List<WorkOrder> getWoStatus() {
		List<WorkOrder> list = this.workOrderDao.getWoStatus();
		return list;
	}
	/**
	 * workOrderItems
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List searchWoByBatchNo(String batchNo) {
		List list = this.workOrderDao.searchWoByBatchNo(batchNo);
		return list;
	}
	/**
	 * 更新WorkOrder的status状态
	 * @param orderNo
	 * @param status
	 */
	public void updateWorkOrderStatus(Integer orderNo, String status) {
		this.workOrderDao.updateWorkOrderStatus(orderNo, status);
	}
	/**
	 * 更新wo_batch的status状态
	 * @param id
	 * @param status
	 */
	public void updateWorkBatchStatus(Integer id, String status) {
		this.workOrderDao.updateWorkBatchStatus(id, status);
	}
	/**
	 * 根据orderNo和itemNo查询，返回一个list集合
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findWoBatchIdDetailss(String batchNo){
		List list = this.workOrderDao.findWoBatchIdDetailss(batchNo);
		return list;
	}
	/**
	 * 查询到的order_no的状态将被更新
	 * @param trackingNo
	 * @param itemNo
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findWoBatchDetails(String orderNo){
		List list = this.workOrderDao.findWoBatchDetails(orderNo);
		return list;
	}
	
	/**
	 * 根据orderNo查询WorkOrder的Detail
	 * @param  orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findWorkOrderDetail(Integer orderNo) {
		List list = this.workOrderDao.findWorkOrderDetail(orderNo);
		for(WorkOrderDTO dto:(List<WorkOrderDTO>)list) {
			List<WorkOrderGroup> workOrderGroupList = this.workOrderGroupDao.findBy("orderNo", dto.getOrderNo());
			if(workOrderGroupList!=null&&workOrderGroupList.size()>0) {
				StringBuffer workGroupNames = null;
				for(WorkOrderGroup workOrderGroup:workOrderGroupList) {
					if (workOrderGroup.getWorkGroupId() != null) {
						WorkGroup workGroup = workGroupDao.get(workOrderGroup
								.getWorkGroupId());
						if (workGroup != null&&workGroupNames==null) {
							workGroupNames = new StringBuffer();
							workGroupNames.append(workGroup.getName());
						} else if(workGroup!=null){
							workGroupNames.append(",").append(workGroup.getName());
						}
					}
				}
				dto.setWorkGroupName(workGroupNames.toString());
			}
		}
		
		return list;
	}
	
	/**
	 * WorkOrder Receive 2
	 * @param page     分页对象
	 * @param orderNo  参数
	 * @return Page   
	 */
	public Page<WorkOrderDTO> searchPoIDetailAll2(Page<WorkOrder> page,String orderNo){
		return this.getWorkOrderDao().searchPoIDetailAll2(page, orderNo);
	}
	
	/**
	 * WorkOrder Receive 2
	 * @param page    分页对象
	 * @param orderNo 参数
	 * @return Page   
	 */
	@SuppressWarnings("unchecked")
	public Page<WorkOrderDTO> searchWoByBatchNo2(Page<WorkOrder> page,String batchNo){
		return this.workOrderDao.searchWoByBatchNo2(page, batchNo);
	}
	
	public WorkOrderDao getWorkOrderDao() {
		return workOrderDao;
	}

	public void setWorkOrderDao(WorkOrderDao workOrderDao) {
		this.workOrderDao = workOrderDao;
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

	public ShipPackageErrorsDao getShipPackageErrorDao() {
		return shipPackageErrorDao;
	}

	public void setShipPackageErrorDao(ShipPackageErrorsDao shipPackageErrorDao) {
		this.shipPackageErrorDao = shipPackageErrorDao;
	}

	public WoBatcheDao getWoBatchDao() {
		return woBatchDao;
	}

	public void setWoBatchDao(WoBatcheDao woBatchDao) {
		this.woBatchDao = woBatchDao;
	}

	public WoBatchErrorDao getWoBatchErrorDao() {
		return woBatchErrorDao;
	}

	public void setWoBatchErrorDao(WoBatchErrorDao woBatchErrorDao) {
		this.woBatchErrorDao = woBatchErrorDao;
	}
}
