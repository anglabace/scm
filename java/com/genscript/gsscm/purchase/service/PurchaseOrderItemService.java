package com.genscript.gsscm.purchase.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.purchase.dao.PoReceiveingTmpDao;
import com.genscript.gsscm.purchase.dao.PurchaseOrderItemDao;
import com.genscript.gsscm.purchase.dto.PurchaseOrderItemDTO;
import com.genscript.gsscm.purchase.entity.PoReceivingTmp;
import com.genscript.gsscm.purchase.entity.PurchaseOrderItem;
@Service
@Transactional
public class PurchaseOrderItemService {
	/**
	 * spring注入purchaseOrderItemDao对象
	 */
	@Autowired
	private PurchaseOrderItemDao purchaseOrderItemDao;
    @Autowired(required = false)
    private DozerBeanMapper dozer;
	@Autowired
	private PoReceiveingTmpDao poReceiveingTmpDao;
	/**
	 * 根据orderNO查询，返回list集合
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<PurchaseOrderItemDTO> searchPoIDetailAll2(Page<PurchaseOrderItem> page,String orderNo)
	{
		return this.purchaseOrderItemDao.searchPoIDetailAll2(page, orderNo);
	}
	/**
	 * 根据orderNO查询，返回list集合
	 * @param orderNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List searchPoIDetailAll(String[] orderNo) {
		List list = this.purchaseOrderItemDao.searchPoIDetailAll(orderNo);
		return list;
	}
	/**
	 * PurchaseOrderItems 
	 * @param page 分页对象
	 * @param trackingNo
	 * @return
	 */
	public Page<PurchaseOrderItemDTO> searchPoIDetails2(Page<PurchaseOrderItem> page,String trackingNo){
		return this.purchaseOrderItemDao.searchPoIDetails2(page, trackingNo);
	}

	/**
	 * 根据orderNo分页查询
	 * @param page
	 * @param orderNo
	 * @return
	 */
	public Page<PurchaseOrderItemDTO> getPoPage(Page<PurchaseOrderItem> page,Integer orderNo){
		return this.purchaseOrderItemDao.getPoPage(page, orderNo);
	}
	/**
	 * PurchaseOrderItems
	 * @param page 分页对象
	 * @param srch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List searchPoIDetails(String trackingNo) {
		List list = this.purchaseOrderItemDao.searchPoIDetails(trackingNo);
		return list;
	}
	/**
	 * 根据orderItemId查询，返回一个PurchaseOrderItems实体对象
	 * @param orderItemId
	 * @return
	 */
	public PurchaseOrderItemDTO getPurchaseByItemId(Integer orderItemId){
		PurchaseOrderItemDTO po = this.purchaseOrderItemDao.getPurchaseByItemId(orderItemId);
		return po;
	}
	
	public List<PurchaseOrderItemDTO> searchPurchaseByItemId(String orderItemId){
		List<PurchaseOrderItemDTO> po = this.purchaseOrderItemDao.searchPurchaseByItemIdList(orderItemId);
		return po;
	}
	/**
	 * 根据orderNO查询，返回一个对象
	 * @param orderNo
	 * @return
	 */
	public PurchaseOrderItem findByNoAndItemNo(Integer orderNo,Integer itemNo){
		PurchaseOrderItem poi = this.purchaseOrderItemDao.findByNoAndItemNo(orderNo, itemNo);
		return poi;
	}
	/**
	 * 根据orderNo查询PurchaseOrder的Detail
	 * @param  orderNo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findPurchaseOrderDetail(Integer orderNo) {
		List<PurchaseOrderItemDTO> list = this.purchaseOrderItemDao.findPurchaseOrderDetail(orderNo);
		//List<PurchaseOrderItem> list = this.purchaseOrderItemDao.findBy("orderNo", orderNo);
/*		List<PurchaseOrderItemDTO> listDto = new ArrayList<PurchaseOrderItemDTO>();
		for(PurchaseOrderItemDTO poi:list){
			PurchaseOrderItemDTO dto = null;
			dto = this.dozer.map(poi, PurchaseOrderItemDTO.class);
			dto.setOrderItemId(poi.getId());
			listDto.add(dto);
		}*/
		return list;
	}
	
	public void savePOitem(PurchaseOrderItem item){
		this.purchaseOrderItemDao.save(item);
	}
	
	public List<PoReceivingTmp> searchReceiveTmp(Integer poNo){
		return this.poReceiveingTmpDao.findBy("poNo", poNo);
	}
	
	public PoReceivingTmp getPoReceiveTmp(Integer id){
		return this.poReceiveingTmpDao.getById(id);
	}
	
	public void delPoPeceiveTmp(PoReceivingTmp tmp ){
		this.poReceiveingTmpDao.delete(tmp);
	}
	
	public void delPoPeceiveTmps(String tmpIds){
		this.poReceiveingTmpDao.delTmp(tmpIds);
	}
	
	public void savePoPeceiveTmp(PoReceivingTmp tmp ){
		this.poReceiveingTmpDao.save(tmp);
	}
}
