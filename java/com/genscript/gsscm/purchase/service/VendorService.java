package com.genscript.gsscm.purchase.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.dto.VendorDTO;
import com.genscript.gsscm.purchase.entity.Vendor;
@Service
@Transactional
public class VendorService {
	/**
	 * spring注入vendorDao对象
	 */
	@Autowired
	private VendorDao vendorDao;
	@Autowired
	private DozerBeanMapper dozer;

	private final static String ROOTNODE = "0000";
	
	/**
	 * 根据条件查询vendor对象
	 * @param page
	 * @param srch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<VendorDTO> searchwo(Page<Vendor> page, VendorDTO srch)
	{
		Page<VendorDTO> retPage = new Page<VendorDTO>();
		List<VendorDTO> dtoList = new ArrayList<VendorDTO>();
		page = this.vendorDao.searchvo(page, srch);
		if (page.getResult() != null) {
			for (Vendor user : page.getResult()) {
				VendorDTO dto = dozer.map(user, VendorDTO.class);
				dtoList.add(dto);
			}
		}
		page.setResult(null);
		retPage = dozer.map(page, Page.class);
		retPage.setResult(dtoList);
		return retPage;
	}
	
	public Page<Vendor> searchVendor (Page<Vendor> page, List<PropertyFilter> filterList) {
		return this.vendorDao.findPage(page, filterList);
	}
	
	public DozerBeanMapper getDozer() {
		return dozer;
	}
	public void setDozer(DozerBeanMapper dozer) {
		this.dozer = dozer;
	}
	public static String getROOTNODE() {
		return ROOTNODE;
	}
	public VendorDao getVendorDao() {
		return vendorDao;
	}
	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}
}
