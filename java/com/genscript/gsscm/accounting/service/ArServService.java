package com.genscript.gsscm.accounting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;

import com.genscript.gsscm.serv.dao.ServiceListBeanDao;
import com.genscript.gsscm.serv.entity.ServiceListBean;



@Service
@Transactional
public class ArServService {
	@Autowired
	private ServiceListBeanDao serviceListBeanDao;
	

	@Transactional(readOnly = true)
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page){
		return serviceListBeanDao.findPage(page);
	}
	@Transactional(readOnly = true)
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page,
			List<PropertyFilter> filters) {
		return serviceListBeanDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<ServiceListBean> searchServiceList(Page<ServiceListBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return serviceListBeanDao.findPage(page, filterList);
	}
	
	public List<Integer> getApprovedRequestListByTableTypeStatus(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
