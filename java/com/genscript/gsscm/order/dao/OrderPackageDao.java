package com.genscript.gsscm.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderPackage;

@Repository
public class OrderPackageDao extends HibernateDao<OrderPackage, Integer> {
	/**
	 * 根据orderNo获得orderPackage list.
	 * @param orderNo
	 * @return
	 */
    public List<OrderPackage> getOrderPackageList(Integer orderNo) {
		String hql = "from OrderPackage where orderNo=:orderNo order by packageId ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		return this.find(hql, map);
    }
    
    /**
	 * 根据orderNo获得orderPackage list.
	 * @param orderNo
	 * @return
	 */
    public List<OrderPackage> getOrderPackageList1(String orderNo) {
		String hql = "from OrderPackage where orderNo in("+orderNo+") order by packageId ASC";
		return this.find(hql);
    }
    
    /**
     * 获得一个Order所有Package的数量.
     * @param orderNo
     * @return
     */
    public Long getOrderPackageCount(Integer orderNo) {
		String hql = "select count(*) from OrderPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
    }

    /**
     * 获得一个Order所有Package中zone的类别数.
     * @param orderNo
     * @return
     */
    public Long getOrderPackageZoneCount(Integer orderNo) {
		String hql = "select count(distinct zone) from OrderPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
    }
    
    /**
     * 获得一个Order所有Package中ship method的类别数.
     * @param orderNo
     * @return
     */
    public Long getOrderPackageShipMethodCount(Integer orderNo) {
		String hql = "select count(distinct shipMethod) from OrderPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
    }
    
    /**
     * 获得一个Order所有Package的ship method list.
     * @param orderNo
     * @return
     */
    public List<Integer> getOrderShipMethodList(Integer orderNo) {
    	String hql = "select shipMethod from OrderPackage where orderNo=?";
    	return this.find(hql, orderNo);
    }
    
    /**
     * 获得一个Order的第一个Package.
     * @param orderNo
     * @return
     */
    public OrderPackage getOrderFirstPackage(Integer orderNo) {
    	Page<OrderPackage> page = new Page<OrderPackage>();
    	page.setPageNo(1);
    	page.setPageSize(1);
    	String hql = "from OrderPackage where orderNo=? order by packageId asc";
    	List<OrderPackage> list =  this.findPage(page, hql, orderNo).getResult();
    	if (list != null && !list.isEmpty()) {
    		return list.get(0);
    	}
    	return null;
    }
    
    
    
    /**
     * 获得一个Order所有Package的weight之和.
     * @param orderNo
     * @return
     */
    public Double getOrderPackageWeight(Integer orderNo) {
		String hql = "select sum(billableWeight) from OrderPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
    }
    
    /**
     * 获得一个Order所有Package的Customer charge之和.
     * @param orderNo
     * @return
     */
    public Double getOrderPackageCost(Integer orderNo) {
		String hql = "select sum(customerCharge) from OrderPackage where orderNo=?";
		return this.findUnique(hql, orderNo);
    }
    
    /**
     * 删除一个Order所有的Package list.
     * @param orderNo
     */
    public void delOrderPackageListByOrderNo(Integer orderNo) {
        String hql = "delete from OrderPackage where orderNo=:orderNo";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("orderNo", orderNo);
 		this.batchExecute(hql, map);
     }
    /**
     * 删除一个Quote所有的Package list.
     * @param quoteNo
     */
    public void delOrderPackageListByQuoteNo(Integer quoteNo) {
        String hql = "delete from OrderPackage where quoteNo=:quoteNo";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("quoteNo", quoteNo);
 		this.batchExecute(hql, map);
     }
    
    
    /**
     * 批量删除多个OrderPackage.
     * @param delPackageIdList
     */
    public void delOrderPackageList(List<Integer> delPackageIdList) {
        String hql = "delete from OrderPackage where packageId in (:delPackageIdList)";	
 		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("delPackageIdList", delPackageIdList);
 		this.batchExecute(hql, map);
    }
    
    /**
     * 获得一个package的总的quantity(所有packageItem quantity的总和).
     * @param packageId
     * @return
     */
    public Long getTotalQty(Integer packageId) {
    	String hql = "select sum(quantity) from OrderPackageItem where packageId=?";
    	return this.findUnique(hql, packageId);
    }
    
    /*
     * 根据orderNo 获得一串的
     */
    public List<OrderPackage> getOrderPackageByList(List<Integer> orderNo) {
		String hql = "from OrderPackage where orderNo in (:orderNo) order by packageId ASC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		return this.find(hql, map);
    }
}
