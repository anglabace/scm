package com.genscript.gsscm.systemsetting.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.PbLanguageDao;
import com.genscript.gsscm.basedata.dao.PbStateDao;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbLanguage;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.customer.dto.SalesGroupDTO;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.order.dao.TaxRateDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.entity.TaxRate;

/**
 * 
 * @Description:setting/accounting/Territories, Currencies & Taxes模块维护业务类
 * @Copyright: Copyright genscrpit (c)2010
 * @author: lizhang
 * @version: 1.0
 * @date:   2011-4-8
 * 
 * Modification History:
 * Date			Author			Version			Description
 * -------------------------------------------------------------
 *
 */
@Service
@Transactional
public class AccountSalesTaxService {
	@Autowired
	private TaxRateDao taxRateDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private PbLanguageDao pbLanguageDao;
	@Autowired
	private PbStateDao pbStateDao;
	/**
	 * 分页查找country
	 */
	public Page<PbCountry> searchCountryPage(Page<PbCountry> countryPage) {
		// 获得分页请求相关数据：如第几页
		PagerUtil<PbCountry> pagerUtil = new PagerUtil<PbCountry>();
		countryPage = pagerUtil.getRequestPage();
		// 设置默认排序
		if (!countryPage.isOrderBySetted()) {
			countryPage.setOrderBy("countryId");
			countryPage.setOrder(Page.DESC);
		}
		// 设置默认每页显示记录条数
		countryPage.setPageSize(15);
		// 获得查询条件
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		String status = Struts2Util.getRequest().getParameter("filter_EQS_status");
		List<String> countryCodeList = null;
		if(StringUtils.isNotEmpty(status)) {
			countryCodeList = this.taxRateDao.getCountryCode(status);
		}
		if(filters==null||filters.isEmpty()) {
			countryPage = this.pbCountryDao.getAll(countryPage);
		} else {
			countryPage = this.pbCountryDao.findPageBySomeFilter(countryPage, filters, countryCodeList);
		}
		if(countryPage!=null&&countryPage.getResult()!=null) {
			for(PbCountry pbCountry:countryPage.getResult()) {
				if(StringUtils.isNotEmpty(pbCountry.getCurrencyCode())) {
					pbCountry.setSymbol(this.currencyDao.getCurrencySymbol(pbCountry.getCurrencyCode()));
				}
				if(StringUtils.isNotEmpty(pbCountry.getLangCode())) {
					pbCountry.setLangName(pbLanguageDao.getLangName(pbCountry.getLangCode()));
				}
				TaxRate taxRate = this.taxRateDao.getTaxRate(pbCountry.getCountryCode());
				if(taxRate!=null) {
					pbCountry.setStatus(taxRate.getStatus());
					pbCountry.setNationalRate(taxRate.getTaxRate());
				}

			}
		}
		return countryPage;
	}
	
	/**
	 * 获得PbCountry对象
	 */
	public PbCountry findById(Integer countryId) {
		PbCountry pbCountry =  this.pbCountryDao.getById(countryId);
		if(StringUtils.isNotEmpty(pbCountry.getCurrencyCode())) {
			pbCountry.setSymbol(this.currencyDao.getCurrencySymbol(pbCountry.getCurrencyCode()));
		}
		if(StringUtils.isNotEmpty(pbCountry.getLangCode())) {
			pbCountry.setLangName(pbLanguageDao.getLangName(pbCountry.getLangCode()));
		}
		TaxRate taxRate = this.taxRateDao.getTaxRate(pbCountry.getCountryCode());
		if(taxRate!=null) {
			pbCountry.setStatus(taxRate.getStatus());
			pbCountry.setNationalRate(taxRate.getTaxRate());
			pbCountry.setNote(taxRate.getNote());
		}
		return pbCountry;
	}
	
	/**
	 * 获得PbState对象
	 */
	public PbState findStateById(Integer stateId) {
		PbState pbState =  this.pbStateDao.getById(stateId);
		
		if(pbState!=null) {
			PbCountry pbCountry = this.pbCountryDao.getById(pbState.getCountryId());
			if(pbCountry!=null) {
				TaxRate taxRate = this.taxRateDao.getTaxRate(pbCountry.getCountryCode(),pbState.getStateCode());
				pbState.setStatus(taxRate.getStatus());
				pbState.setNote(taxRate.getNote());
				pbState.setTaxRate(taxRate.getTaxRate());
			}
			
		}
		return pbState;
	}
	
	/**
	 * 批量删除pbState对象
	 */
	public void deleteState(String allChoiceVal,String sessionId) {
		Map<String,PbState> stateMap = (Map<String,PbState>)SessionUtil.getRow(SessionConstant.StateList.value(), sessionId);
		if(allChoiceVal!=null) {
			for(String stateId:allChoiceVal.split(",")) {
				if(stateMap.containsKey(stateId)) {
					/*if(StringUtils.isNumeric(stateId)) {
						stateMap.put(stateId,null);
					} else {
						stateMap.remove(stateId);
					}*/
                    stateMap.remove(stateId);
				}
				
			}
		}
		SessionUtil.updateRow(SessionConstant.StateList.value(), sessionId, stateMap);
	}
	
	/**
	 * 保存或更新PbCountry对象
	 */
	public void saveCountry(PbCountry pbCountry,String sessionId) {
		Map<String,PbState> stateMap = (Map<String,PbState>)SessionUtil.getRow(SessionConstant.StateList.value(), sessionId);
		pbCountry.setModifiedBy(SessionUtil.getUserId());
		this.pbCountryDao.save(pbCountry);
		TaxRate taxRate2 = this.taxRateDao.getTaxRate(pbCountry.getCountryCode());
		if(taxRate2==null) {
			taxRate2 = new TaxRate();
			taxRate2.setCountry(pbCountry.getCountryCode());
			taxRate2.setCreatedBy(SessionUtil.getUserId());
			taxRate2.setCreationDate(new Date());
			taxRate2.setNote(pbCountry.getNote());
			taxRate2.setStatus(pbCountry.getStatus());
		}
		taxRate2.setTaxRate(pbCountry.getNationalRate());
		taxRate2.setModifiedBy(SessionUtil.getUserId());
		taxRate2.setModifyDate(new Date());
		this.taxRateDao.save(taxRate2);
		if(stateMap!=null) {
			Iterator<Entry<String, PbState>> it = stateMap.entrySet()
			.iterator();
			while (it.hasNext()) {
				Entry<String, PbState> entry = it.next();
				PbState state = entry.getValue();
				String key =  entry.getKey();
				if(state==null&&StringUtils.isNumeric(key)) {
					state = this.pbStateDao.getById(Integer.parseInt(key));
					TaxRate taxRate = this.taxRateDao.getTaxRate(pbCountry.getCountryCode(), state.getStateCode());
					if(taxRate!=null) {
						this.taxRateDao.delete(taxRate);
					}
					this.pbStateDao.delete(state);
					
				} else {
					state.setCountryId(pbCountry.getCountryId());
					state.setModifiedBy(SessionUtil.getUserId());
					this.pbStateDao.save(state);
					TaxRate taxRate = this.taxRateDao.getTaxRate(pbCountry.getCountryCode(),state.getStateCode());
					if(taxRate==null) {
						taxRate = new TaxRate();
						taxRate.setCountry(pbCountry.getCountryCode());
						taxRate.setState(state.getStateCode());
						taxRate.setCreatedBy(SessionUtil.getUserId());
						taxRate.setCreationDate(new Date());
						taxRate.setNote(state.getNote());
					}
					taxRate.setStatus(state.getStatus());
					taxRate.setModifiedBy(SessionUtil.getUserId());
					taxRate.setModifyDate(new Date());
					taxRate.setTaxRate(state.getTaxRate());
					this.taxRateDao.save(taxRate);
					
				}
			}
		}
		
	}
	
	/**
	 * 批量删除PbCountry对象
	 */
	public void deleteCountry(String allChoiceVal) {
		if(allChoiceVal!=null) {
			for(String countryId:allChoiceVal.split(",")) {
				PbCountry pbCountry =  this.pbCountryDao.getById(Integer.parseInt(countryId));
				this.taxRateDao.deleteByCountry(pbCountry.getCountryCode());
				this.pbStateDao.deleteByCountryId(Integer.parseInt(countryId));
				this.pbCountryDao.delete(pbCountry);
			}
		}
	}
	
	/**
	 * 获取PbState对象列表
	 */
	public List<PbState> getStateByCountryId(Integer countryId) {
		PbCountry pbCountry =  this.pbCountryDao.getById(countryId);
		List<PbState> stateList = this.pbStateDao.findBy("countryId", countryId);
		if(stateList!=null) {
			for(PbState pbState:stateList) {
				TaxRate taxRate = this.taxRateDao.getTaxRate(pbCountry.getCountryCode(), pbState.getStateCode());
				pbState.setStatus(taxRate!=null?taxRate.getStatus():null);
				pbState.setTaxRate(taxRate!=null?taxRate.getTaxRate():null);
			}
		}
		return stateList;
	}
	/*
	 * 获取TaxRate
	 */
	public TaxRate getTaxRate(String country,String state){
		return this.taxRateDao.getTaxRate(country, state);
	}
	/**
	 * 获取所有PbCountry对象
	 */
	public List<PbCountry> getAllCountry() {
		return this.pbCountryDao.getAll();
	}
	
	/**
	 * 获取所有PbCurrency对象
	 */
	public List<PbCurrency> getAllCurrency() {
		return this.currencyDao.getAll();
	}
	
	/**
	 * 获取所有PbLanguage对象
	 */
	public List<PbLanguage> getAllLanguage() {
		return this.pbLanguageDao.getAll();
	}

}
