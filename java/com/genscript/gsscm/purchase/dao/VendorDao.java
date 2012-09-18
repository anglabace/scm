package com.genscript.gsscm.purchase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.entity.Vendor;

@Repository
public class VendorDao extends HibernateDao<Vendor, Integer> {
	/**
	 * Vendor 分页方法
	 */
	public Page<Vendor> searchVendor(Page<Vendor> page,
			List<PropertyFilter> filters) {
		return findPage(page, filters);
	}

	/**
	 * 查询vendor列表信息
	 * @param page
	 * @param srch
	 * @return
	 */
	public Page<Vendor> searchvo(Page<Vendor> page, VendorDTO srch) {
		if (srch == null) {
			srch = new VendorDTO();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Vendor";
		if (page.getOrder() != null && page.getOrder().trim().length() > 0) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		page = this.findPage(page, hql, map);
		return page;
	}
	/**
	 * 根据vendorNo查询Vendor
	 * @param  vendorNo
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public Vendor getVendorByNo(Integer vendorNo){
		String hql = "from Vendor where vendorNo= :vendorNo";
		List list = this.getSession().createQuery(hql).setInteger("vendorNo", vendorNo).list();
		Vendor vendor = null;
		if(list !=null && list.size()>0){
			vendor = (Vendor)list.get(0);
		}
		return vendor;
	}
}
