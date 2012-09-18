package com.genscript.gsscm.accounting.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ArCustomerBeanDao;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.customer.entity.CustomerBean;

@Service
@Transactional
public class ArCustomerService {

	@Autowired
	private ArCustomerBeanDao arCustomerBeanDao;
	

	@Autowired
	private MimeMailService mimeMailService;
	private static final String WEB_BEHAVIOR_DT = "yyyy-MM-dd HH:mm:ss";

	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page) {
		Page<CustomerBean> customerList = arCustomerBeanDao.getCustomerList(page);
		return customerList;
	}

	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page,
			List<PropertyFilter> filters) {
		Page<CustomerBean> customerList = arCustomerBeanDao.searchCustomer(page,
				filters);
		return customerList;
	}

	@Transactional(readOnly = true)
	public Page<CustomerBean> searchCustomer(Page<CustomerBean> page,
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
		return arCustomerBeanDao.findPage(page, filterList);
	}



}
