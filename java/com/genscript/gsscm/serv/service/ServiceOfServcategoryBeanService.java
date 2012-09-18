package com.genscript.gsscm.serv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.serv.dao.ServiceOfServcategoryBeanDao;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;

@Service
@Transactional
public class ServiceOfServcategoryBeanService {
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private ServiceOfServcategoryBeanDao serviceOfServcategoryBeanDao;
	
	/**
	 * @author zhang yong
	 * @param page
	 * @param filters
	 * @param custNo
	 * @param listType
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<ServiceOfServcategoryBean> searchService(Page<ServiceOfServcategoryBean> page,
			List<PropertyFilter> filters, Integer custNo, String listType) {
		List<String> catalogNoList = customerSpecialPriceDao
				.getSpecialPriceCatalogNoList(custNo, listType);
		return serviceOfServcategoryBeanDao.findPageExceptCatalogNo(page, filters,
				catalogNoList);
	}
}
