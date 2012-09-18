package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.OrderPackageItem;

@Repository
public class OrderPackageItemDao extends HibernateDao<OrderPackageItem, Integer> {
	/**
	 * 根据orderNo和该order的几个item的itemNo list获得这几个OrderItem的OrderPackage.
	 * @param orderNo
	 * @param itemNoList
	 * @return
	 */
    public List<OrderPackage> getPackagesForItemList(Integer orderNo, Object itemNoList) {
		String hql = "from OrderPackage where packageId in (select packageId from OrderPackageItem where orderNo=:orderNo and itemNo in (:itemNoList) group by packageId)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNoList", itemNoList);
		return this.find(hql, map);
    }
    
    /**
     * 通过packageId查询OrderPackageItem
     * @param packageId
     * @return
     */
    public List<OrderPackageItem> findPackageItemsByPkgId (Integer packageId) {
    	String hql = "from OrderPackageItem where packageId=:packageId";
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("packageId", packageId);
		return this.find(hql, map);
    }
    
    /**
     * 删除一个Order下的所有PackageItem.
     * @param orderNo
     */
    public void delItemListByOrderNo(Integer orderNo) {
       String hql = "delete from OrderPackageItem where orderNo=:orderNo";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		this.batchExecute(hql, map);
    }
    /**
     * 删除一个Quote下的所有PackageItem.
     * @param quoteNo
     */
    public void delItemListByQuoteNo(Integer quoteNo) {
       String hql = "delete from OrderPackageItem where quoteNo=:quoteNo";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		this.batchExecute(hql, map);
    }
    
    /**
     * 通过 packageId list批量删除Package Item.
     * @param delPackageIdList
     */
    public void delItemListByPackageIdList(List<Integer> delPackageIdList) {
        String hql = "delete from OrderPackageItem where packageId in (:delPackageIdList)";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delPackageIdList", delPackageIdList);
		this.batchExecute(hql, map);
    }
    
    /**
	 * 根据orderNo和itemNo 获得OrderPackageItem.
	 * @param orderNo
	 * @param itemNo
	 * @return
	 */
    public List<OrderPackageItem> getPackagesForItem(Integer orderNo, Integer itemNo) {
		String hql = "from OrderPackageItem where orderNo=:orderNo and itemNo = :itemNo group by packageId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNo", itemNo);
		return this.find(hql, map);
    }
}
