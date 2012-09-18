package com.genscript.gsscm.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.constant.SalesRepSalesRole;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.entity.SalesRep;

/**
 * @author zhangyong
 *
 */
@Service
@Transactional
public class SalesRepService {
	@Autowired
	private SalesRepDao salesRepDao;

	/**
	 * 查询所有ProjectSupport的SalesRep
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<SalesRep> findProjectSupportSalesRep () {
		return this.salesRepDao.findSalesRep(SalesRepSalesRole.PROJECT_SUPPORT.value());
	}
}
