package com.genscript.gsscm.shipment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipPackageItem;

@Repository
public class ShipPackageItemDao extends HibernateDao<ShipPackageItem, Integer> {
	/**
	 * 根据orderNo和该order的几个item的itemNo list获得这几个OrderItem的ShipPackage.
	 * @param orderNo
	 * @param itemNoList
	 * @return
	 */
    public List<ShipPackage> getPackagesForItemList(Integer orderNo, Object itemNoList) {
		String hql = "from ShipPackage where packageId in (select packageId from ShipPackageItem where orderNo=:orderNo and itemNo in (:itemNoList) group by packageId)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("itemNoList", itemNoList);
		return this.find(hql, map);
    }
    
    /**
     * 删除一个Order下的所有PackageItem.
     * @param orderNo
     */
    public void delItemListByOrderNo(Integer orderNo) {
       String hql = "delete from ShipPackageItem where orderNo=:orderNo";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		this.batchExecute(hql, map);
    }
    /**
     * 删除一个Quote下的所有PackageItem.
     * @param quoteNo
     */
    public void delItemListByQuoteNo(Integer quoteNo) {
       String hql = "delete from ShipPackageItem where quoteNo=:quoteNo";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		this.batchExecute(hql, map);
    }
    
    /**
     * 通过 packageId list批量删除Package Item.
     * @param delPackageIdList
     */
    public void delItemListByPackageIdList(List<Integer> delPackageIdList) {
        String hql = "delete from ShipPackageItem where packageId in (:delPackageIdList)";	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delPackageIdList", delPackageIdList);
		this.batchExecute(hql, map);
    }
}
