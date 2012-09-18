package com.genscript.gsscm.systemsetting.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbLanguage;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.order.entity.TaxRate;

@Repository
public class SalesTaxMainBeanDao extends HibernateDao<TaxRate, Integer> {
	
	
	public String getCountryCode(String countryName){
		String hql="select c from PbCountry c where 1 = 1 and c.name=?";
		PbCountry Obj=this.findUnique(hql,countryName);
		return Obj.getCountryCode();
	}
	
	public String getStateCode(String stateCode){
		String hql="select c from PbState c where 1 = 1 and c.name=?";
		PbState Obj=this.findUnique(hql,stateCode);
		return Obj.getStateCode();
	}
	
	public String getCurCode(String curName){
		String hql="select c from PbCurrency c where 1 = 1 and c.description=?";
		PbCurrency Obj=this.findUnique(hql,curName);
		return Obj.getCurrencyCode();
	}
	public String getLangCode(String langName){
		String hql="select c from PbLanguage c where 1 = 1 and c.name=?";
		PbLanguage Obj=this.findUnique(hql,langName);
		return Obj.getLangCode();
	}
	
	public List<PbState> getStateList(){
		List<PbState> stateList=null;
		String hql="from PbState s where 1=1 and s.stateCode!='' and s.name !=''";
		stateList=this.find(hql);
		return stateList;
	}
	
	public PbState findState(String stateCode){
		String hql="select s from PbState s where 1 = 1 and s.stateCode=?";
		PbState Obj=this.findUnique(hql,stateCode);
		return Obj;
	}
	
	public TaxRate getTax(TaxRate taxRate){
		String hql = "select t from TaxRate t where 1 = 1 and t.country=? and t.state=?";
		taxRate=this.findUnique(hql,taxRate.getCountry(),taxRate.getStateCode());
		return taxRate;
	}
	
	public TaxRate getSalesTax(TaxRate taxRate){
		String hql = "select t from TaxRate t where 1 = 1 and t.country=? and (t.state='' or t.state is null)";
		taxRate=this.findUnique(hql,taxRate.getCountry());
		return taxRate;
	}
	
	public PbLanguage getLanguage(String code,PbLanguage langObj){
		String hql = " select l from PbLanguage l where 1 = 1 and l.langCode=?";
		langObj=this.findUnique(hql,code);
		return langObj;
	}
	
	public PbCurrency getCurrency(String currencyCode){
		String hql = "from PbCurrency cc where 1 = 1 and cc.currencyCode=?";
		PbCurrency currency=this.findUnique(hql,currencyCode);
		return currency;
	}
	
	public PbCountry getCountry(String countryCode){
		String hql = "select c from PbCountry c where 1 = 1 and c.countryCode=?";
		PbCountry countryObj = this.findUnique(hql,countryCode);
		return countryObj;
	}
	
	public TaxRate getRateTaxInfo(String countryStr,String state,Integer rateId){
		String hql = "select c from PbCountry c where 1 = 1 and c.countryCode=?";
		String hql2="select s from TaxRate s where 1=1 and s.country=? and s.state =? and s.rateId=?";
		String hql3="from PbCurrency where 1=1 and currencyCode=?";
		String hql4="from PbLanguage where 1=1 and langCode=?";
		PbCountry countryObj;
		TaxRate salesTaxBean=null;
		countryObj=this.findUnique(hql,countryStr);
		if(state==null||state.length()<1){
			hql2="select s from TaxRate s where 1=1 and s.country=? and  s.rateId=?";
			salesTaxBean=this.findUnique(hql2,countryStr,rateId);
		}else if(rateId==null){
			hql2="select s from TaxRate s where 1=1 and s.country=? and  s.state=?";
			salesTaxBean=this.findUnique(hql2,countryStr,state);
		}
		else{
			salesTaxBean=this.findUnique(hql2,countryStr,state,rateId);
		}
		if(salesTaxBean!=null){
			salesTaxBean.setCountryName(countryObj.getName());
			salesTaxBean.setCountry(countryObj.getCountryCode());
			salesTaxBean.setContinent(countryObj.getContinent());
			salesTaxBean.setLanguageCode(countryObj.getLangCode());
			salesTaxBean.setCountryName(countryObj.getName());
			
			PbCurrency currency=findUnique(hql3,countryObj.getCurrencyCode());
			if(currency!=null){
			salesTaxBean.setCurrency(currency.getDescription());
			salesTaxBean.setCurrencyCode(currency.getCurrencyCode());
			}
			
			PbLanguage language=findUnique(hql4,countryObj.getLangCode());
			if(language!=null){
			salesTaxBean.setLanguage(language.getName());
			salesTaxBean.setLanguageCode(language.getLangCode());
			}
			}

		return salesTaxBean;
	}
	
	public TaxRate getRateInfo(String country,String stateCode,Integer taxRateId){
		TaxRate salesTaxBean=null;
		String hql = "select s from TaxRate s where 1 = 1 and s.country=? and s.state=? and s.rateId=?";
			if(stateCode==null||stateCode.length()<1){
				hql="select s from TaxRate s where 1 = 1 and s.country=? and s.rateId=?";
				salesTaxBean=this.findUnique(hql,country,taxRateId);
			}else{
				salesTaxBean=this.findUnique(hql,country,stateCode,taxRateId);
			}
		
		String hql2="from PbCountry where 1 = 1 and  countryCode = ?";
			PbCountry countryObj = findUnique(hql2, country);
			if(countryObj!=null&&salesTaxBean!=null){
				salesTaxBean.setCountryName(countryObj.getName());
			}
			if(stateCode!=null||stateCode.length()>1){
			String hql3="from PbState s where 1 = 1 and s.stateCode=?";
			PbState stateObj = findUnique(hql3, stateCode);
			if (stateObj != null) {
				if(salesTaxBean!=null){
				salesTaxBean.setStateName(stateObj.getName());
				salesTaxBean.setStateCode(stateObj.getStateCode());
				}
			}
		}
		return salesTaxBean;
	}
	
	public void saveState(TaxRate salesTaxBean){
		String hql3="from PbState where 1 = 1 and stateCode=?";
		if(salesTaxBean!=null){
			if (salesTaxBean.getState() != null) {
				PbState stateObj = findUnique(hql3, salesTaxBean.getState());
				if (stateObj != null) {
					stateObj.setName(salesTaxBean.getStateName());
				}
			}
		}
	}
	
	public List<PbCountry> getCountryList(){
		List<PbCountry> list=null;
		String hql = "from PbCountry s where 1 = 1 order by s.name";
		//state和countryName条件都为空 
		list=this.find(hql);
		return list;
	}
	
	public List<PbCurrency> getCurrencyCodeList(){
		List<PbCurrency> list=null;
		String hql = "from PbCurrency s where 1 = 1";
		//state和countryName条件都为空 
		list=this.find(hql);
		return list;
	}
	public List<PbLanguage> getLangCodeList(){
		List<PbLanguage> list=null;
		String hql = "from PbLanguage s where 1 = 1";
		//state和countryName条件都为空 
		list=this.find(hql);
		return list;
	}
	public List<TaxRate> getSalesTaxList(List<TaxRate> list,String countryStr,String state)throws Exception{
		String hql = "from TaxRate s where 1 = 1 and s.country=? and s.state !=''";
		list=this.find(hql,countryStr);
		String hql3="from PbState where 1 = 1 and  stateCode = ?";
		PbState stateObj;
		for (TaxRate taxRate:list) {
			if(taxRate!=null&&taxRate.getState()!=null){
				stateObj=this.findUnique(hql3,taxRate.getState());
				if(stateObj!=null){
					taxRate.setState(stateObj.getStateCode());
					taxRate.setStateName(stateObj.getName());
				}
			}
		
		}
		
		return list;
	}
	
	public int delRate(Integer rateId){
		String hql = "delete from TaxRate where rateId =:rateId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rateId", rateId);
		int i=batchExecute(hql, map);
		return i;
	}

	public Page<TaxRate> getSalesTaxRateList(
			Page<TaxRate> page, String countryCode, String countryName,
			String status) throws Exception {
		StringBuffer hql = new StringBuffer("select s from TaxRate s,PbCountry c where 1=1");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(status)) {
			hql.append(" and s.status=:status");
			map.put("status", status);
		}
		if(StringUtils.isNotEmpty(countryCode)) {
			hql.append(" and c.countryCode=s.country and c.countryCode like'%'||:countryCode||'%'");
			map.put("countryCode", countryCode);
		}
		if(StringUtils.isNotEmpty(countryName)) {
			hql.append(" and c.countryCode=s.country and c.name like'%'||:countryName||'%'");
			map.put("countryName", countryName);
		}
		page = this.findPage(page, hql.toString(), map);
		String hql2="from PbCountry where 1=1 and  countryCode = ?";
		String hql3="from PbCurrency where 1=1 and currencyCode=?";
		String hql4="from PbLanguage where 1=1 and langCode=?";
		if(page!=null&&page.getResult()!=null&&page.getResult().size()>0) {
			for(TaxRate taxRate:page.getResult()) {
				PbCountry country = findUnique(hql2, taxRate.getCountry());
				if (country != null) {
					taxRate.setCountryName(country.getName());
					taxRate.setContinent(country.getContinent());
					taxRate.setCurrencyCode(country.getCurrencyCode());
					taxRate.setLanguageCode(country.getLangCode());
					PbCurrency currency=findUnique(hql3,country.getCurrencyCode());
					if(currency!=null){
						taxRate.setCurrency(currency.getDescription());
						taxRate.setCurrencyCode(country.getCurrencyCode());
					}
					PbLanguage language=findUnique(hql4,country.getLangCode());
					if(language!=null){
						taxRate.setLanguage(language.getName());
						taxRate.setLanguageCode(country.getLangCode());
					}
			   }
			}
		}
		return page;
	}

}
