package com.genscript.gsscm.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.application.dao.AppIntfaceDao;
import com.genscript.gsscm.application.dao.AppModDao;
import com.genscript.gsscm.application.dao.ApplicationDao;
import com.genscript.gsscm.application.entity.Application;
import com.genscript.gsscm.application.entity.ApplicationInterface;
import com.genscript.gsscm.application.entity.ApplicationModule;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private AppIntfaceDao appIntfaceDao;
	@Autowired 
	private AppModDao appModDao;
	
	@Transactional(readOnly = true)
	public Application getApplication(Integer id) {
		return applicationDao.get(id);
	}
	
	public void saveApplication(Application entity) {
		applicationDao.save(entity);
	}
	
	public void deleteApplication(Integer id) {
		applicationDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Application> listApplication(final Page<Application> page) {
		return applicationDao.getAll(page);
	}
	
	@Transactional(readOnly = true)
	public ApplicationInterface getAppIntface(Integer id) {
		return appIntfaceDao.get(id);
	}
	
	public void saveAppIntface(ApplicationInterface entity) {
		appIntfaceDao.save(entity);
	}
	
	public void deleteAppIntface(Integer id) {
		appIntfaceDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Page<ApplicationInterface> listAppIntface(final Page<ApplicationInterface> page) {
		return appIntfaceDao.getAll(page);
	}
	
	@Transactional(readOnly = true)
	public ApplicationModule getAppMod(Integer id) {
		return appModDao.get(id);
	}
	
	public void saveAppMod(ApplicationModule entity) {
		appModDao.save(entity);
	}
	
	public void deleteAppMod(Integer id) {
		appModDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Page<ApplicationModule> listAppMod(final Page<ApplicationModule> page) {
		return appModDao.getAll(page);
	}
}
