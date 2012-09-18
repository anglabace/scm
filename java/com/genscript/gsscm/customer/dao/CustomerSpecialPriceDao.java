package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;

@Repository
public class CustomerSpecialPriceDao extends HibernateDao<CustomerSpecialPrice, Integer> {
	private static final String DEL_SPECIAL_PRICES = "delete from CustomerSpecialPrice c where c.priceId in (:ids)";
	private static final String CATALOGNO_LIST = "select c.catalogNo from CustomerSpecialPrice c where c.custNo=? and c.type=?";


	public void delSpecialPriceList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_SPECIAL_PRICES, map);
	}
	
	public List<CustomerSpecialPrice> getListByCust(Integer custNo) {
    	String hql = "from CustomerSpecialPrice where custNo=?";
    	return this.find(hql, custNo);
    }
	
	@SuppressWarnings("unchecked")
	public List<String> getSpecialPriceCatalogNoList(Integer custNo, String listType){
		if(custNo==null)
			return null;
		List list = null;
		if (CatalogType.SERVICE.value().equals(listType)) {
			list = createQuery(CATALOGNO_LIST, custNo, CatalogType.SERVICE.value()).list();
		} else {
			list = createQuery(CATALOGNO_LIST, custNo, CatalogType.PRODUCT.value()).list();
		}
		if(list != null){
			return (List<String>)list;
		}
		return null;
	}
	
	
	/**
	 * 根据custNo和catalogNo唯一获得一个CustomerSpecialPrice.
	 * @param custNo
	 * @param catalogNo
	 * @return
	 */
	public CustomerSpecialPrice getSpecialPrice(Integer custNo, String catalogNo) {
		CustomerSpecialPrice specialPrice = null;
    	String hql = "from CustomerSpecialPrice where custNo=:custNo and catalogNo=:catalogNo";
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("custNo", custNo);
    	map.put("catalogNo", catalogNo);
    	List<CustomerSpecialPrice> list = this.find(hql, map);
    	if (list!=null && !list.isEmpty()) {
    		specialPrice = list.get(0);
    	}
    	return specialPrice;
	}
}
