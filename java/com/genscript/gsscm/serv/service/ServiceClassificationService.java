package com.genscript.gsscm.serv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.serv.dao.ServiceClassificationDao;
import com.genscript.gsscm.serv.entity.ServiceClassification;

/**
 * @author zhangyong
 */
@Service
@Transactional
public class ServiceClassificationService {

	@Autowired
	private ServiceClassificationDao serviceClassificationDao;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<ServiceClassification> findAll () {
		return this.serviceClassificationDao.findAll();
	}
}
