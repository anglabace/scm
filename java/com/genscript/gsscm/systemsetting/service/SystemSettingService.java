package com.genscript.gsscm.systemsetting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.PbLanguageDao;
import com.genscript.gsscm.basedata.dao.PbStateDao;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SourceDTO;
import com.genscript.gsscm.basedata.dto.ZoneAndRateDTO;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.entity.PbLanguage;
import com.genscript.gsscm.basedata.entity.PbState;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.contact.dao.ContactContactHistDao;
import com.genscript.gsscm.contact.dao.ContactDao;
import com.genscript.gsscm.contact.entity.Contact;
import com.genscript.gsscm.contact.entity.ContactContactHistory;
import com.genscript.gsscm.customer.dao.CouponDao;
import com.genscript.gsscm.customer.dao.CustContactHistDao;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.dao.SourceDao;
import com.genscript.gsscm.customer.entity.Coupon;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.customer.entity.Source;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderPackageDao;
import com.genscript.gsscm.order.dao.OrderPromotionDao;
import com.genscript.gsscm.order.dao.PromotionBeanDao;
import com.genscript.gsscm.order.dao.PromotionDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.Promotion;
import com.genscript.gsscm.order.entity.PromotionBean;
import com.genscript.gsscm.order.entity.TaxRate;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductInCategoryBeanDao;
import com.genscript.gsscm.product.dao.ProductSpecialPriceDao;
import com.genscript.gsscm.product.entity.ProductInCategoryBean;
import com.genscript.gsscm.product.entity.ProductSpecialPrice;
import com.genscript.gsscm.quote.dao.QuoteDao;
import com.genscript.gsscm.quote.dao.QuoteItemDao;
import com.genscript.gsscm.quote.dao.QuotePackageDao;
import com.genscript.gsscm.quote.dao.QuotePromotionDao;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuotePackage;
import com.genscript.gsscm.quoteorder.dto.PromotionDTO;
import com.genscript.gsscm.serv.dao.ServiceOfServcategoryBeanDao;
import com.genscript.gsscm.serv.dao.ServiceSpecialPriceDao;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;
import com.genscript.gsscm.serv.entity.ServiceSpecialPrice;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.dao.ShipRateCustomerBasicDao;
import com.genscript.gsscm.shipment.dao.ShipRateDao;
import com.genscript.gsscm.shipment.dao.ShipRateTotalRangeDao;
import com.genscript.gsscm.shipment.dao.ShipRateWeightRangeDao;
import com.genscript.gsscm.shipment.dao.ShipZoneDao;
import com.genscript.gsscm.shipment.dao.StandardShipMethodDao;
import com.genscript.gsscm.shipment.dao.StandardShipRateDao;
import com.genscript.gsscm.shipment.dao.StandardShipZoneDetailDao;
import com.genscript.gsscm.shipment.dao.StandardShipZoneTypeDao;
import com.genscript.gsscm.shipment.dto.ShipMethodDTO;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipRateCustomerBasic;
import com.genscript.gsscm.shipment.entity.ShipRateTotalRange;
import com.genscript.gsscm.shipment.entity.ShipRateWeightRange;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.shipment.entity.StandardShipMethod;
import com.genscript.gsscm.shipment.entity.StandardShipRate;
import com.genscript.gsscm.shipment.entity.StandardShipZoneDetail;
import com.genscript.gsscm.shipment.entity.StandardShipZoneType;
import com.genscript.gsscm.systemsetting.dao.SalesTaxMainBeanDao;

@Service
@Transactional
public class SystemSettingService {

	@Autowired
	private SourceDao sourceDao;
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private PromotionBeanDao promotionBeanDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private ContactContactHistDao contactContactHistDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustContactHistDao custContactHistDao;
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private ProductSpecialPriceDao productSpecialPriceDao;
	@Autowired
	private ServiceSpecialPriceDao serviceSpecialPriceDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private ShipZoneDao shipZoneDao;
	@Autowired
	private ShipRateDao shipRateDao;
	@Autowired
	private ShipRateCustomerBasicDao shipRateCustomerBasicDao;
	@Autowired
	private ShipRateTotalRangeDao shipRateTotalRangeDao;
	@Autowired
	private ShipRateWeightRangeDao shipRateWeightRangeDao;
	@Autowired
	private StandardShipZoneDetailDao standardShipZoneDetailDao;
	@Autowired
	private StandardShipRateDao standardShipRateDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
	@Autowired
	private StandardShipZoneTypeDao standardShipZoneTypeDao;
	@Autowired
	private StandardShipMethodDao standardShipMethodDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private QuoteItemDao quoteItemDao;
	@Autowired
	private QuotePackageDao quotePackageDao;
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private ProductInCategoryBeanDao productInCategoryBeanDao;
	@Autowired
	private ServiceOfServcategoryBeanDao serviceOfServcategoryBeanDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private SalesTaxMainBeanDao salesTaxMainBeanDao;
	@Autowired
	private PbCountryDao countryDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private PbLanguageDao langDao;
	@Autowired
	private OrderPromotionDao orderPromotionDao;
	@Autowired
	private QuotePromotionDao quotePromotionDao;
	@Autowired
	private PbStateDao stateDao;
	@Autowired
	private QuoteDao quoteDao;
	
	public String getCountryCode(String countryName){
		String code=this.salesTaxMainBeanDao.getCountryCode(countryName);
		return code;
	}
	public String getStateCode(String stateName){
		String code=this.salesTaxMainBeanDao.getStateCode(stateName);
		return code;
	}
	
	public String getCurCode(String curName){
		String code=this.salesTaxMainBeanDao.getCurCode(curName);
		return code;
	}
	public String getLangCode(String langName){
		String code=this.salesTaxMainBeanDao.getLangCode(langName);
		return code;
	}
	
	public List<PbCountry> getCountryCodeList(List<PbCountry> countryCodeList) {
		countryCodeList = this.salesTaxMainBeanDao.getCountryList();
		return countryCodeList;
	}
	public List<PbCurrency> getCurrencyCodeList(List<PbCurrency> currencyCodeList) {
		currencyCodeList = this.salesTaxMainBeanDao.getCurrencyCodeList();
		return currencyCodeList;
	}
	
	public List<PbState> getStateCodeList( List<PbState> stateCodeList){
		stateCodeList=this.salesTaxMainBeanDao.getStateList();
		return stateCodeList;
	}
	
	public List<PbLanguage> getLangCodeList(List<PbLanguage> languageCodeList) {
		languageCodeList = this.salesTaxMainBeanDao.getLangCodeList();
		return languageCodeList;
	}
	

	public String saveRate(TaxRate salesTaxBean, String temp)throws Exception{
		try{
		PbCountry tmpCountry = null;
		PbState tmpState=null;
		TaxRate newSalesTax = null;
		if (salesTaxBean != null) {
			tmpCountry = this.salesTaxMainBeanDao.getCountry(salesTaxBean
					.getCountry());
					tmpState=this.salesTaxMainBeanDao.findState(salesTaxBean.getStateCode());
					if(tmpState!=null){
						 tmpState.setStateCode(salesTaxBean.getStateCode());
						 tmpState.setDescription(salesTaxBean.getNote());
						 tmpState.setName(salesTaxBean.getStateName());
						 tmpState.setModifyDate(new Date());
						 this.stateDao.save(tmpState);
					}else{
						 tmpState=new PbState();
						 tmpState.setCountryId(tmpCountry.getCountryId());
						 tmpState.setStateCode(salesTaxBean.getStateCode());
						 tmpState.setDescription(salesTaxBean.getNote());
						 tmpState.setName(salesTaxBean.getStateName());
						 tmpState.setCreatedBy(SessionUtil.getUserId());
						 tmpState.setCreationDate(new Date());
						 tmpState.setModifiedBy(SessionUtil.getUserId());
						 tmpState.setModifyDate(new Date());
						 this.stateDao.save(tmpState);
					}
					newSalesTax=this.salesTaxMainBeanDao
					.getTax(salesTaxBean);
					if (newSalesTax != null) {
						newSalesTax.setModifyDate(new Date());
						newSalesTax.setCountry(salesTaxBean.getCountry());
						newSalesTax.setState(salesTaxBean.getStateCode());
						newSalesTax.setStatus(salesTaxBean.getStatus());
						newSalesTax.setTaxRate(salesTaxBean.getTaxRate());
						newSalesTax.setNote(salesTaxBean.getNote());
						newSalesTax.setModifyDate(new Date());
						this.salesTaxMainBeanDao.save(newSalesTax);
					} else {
						newSalesTax = new TaxRate();
						newSalesTax.setCountry(salesTaxBean.getCountry());
						newSalesTax.setState(salesTaxBean.getStateCode());
						newSalesTax.setStatus(salesTaxBean.getStatus());
						newSalesTax.setTaxRate(salesTaxBean.getTaxRate());
						newSalesTax.setNote(salesTaxBean.getNote());
						newSalesTax.setModifyDate(new Date());
						newSalesTax.setCreationDate(new Date());
						newSalesTax.setCreatedBy(SessionUtil.getUserId());
						newSalesTax.setModifiedBy(SessionUtil.getUserId());
						this.salesTaxMainBeanDao.save(newSalesTax);
					}
		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
					 return salesTaxBean.getStateCode();
		
	}

	public String saveOrUpdateCountry(TaxRate salesTaxBean, String temp) {
		PbCountry tmpCountry = null;
		TaxRate newSalesTax = null;
		 PbState tmpState=null;
			if (salesTaxBean != null) {
//				// 保存Country
				tmpCountry = this.salesTaxMainBeanDao.getCountry(salesTaxBean
						.getCountry());
				tmpCountry.setContinent(salesTaxBean.getContinent());
				tmpCountry.setCurrencyCode(salesTaxBean.getCurrencyCode());
				tmpCountry.setLangCode(salesTaxBean.getLanguageCode());
				tmpCountry.setDescription(salesTaxBean.getNote());
//				tmpLanguage = this.salesTaxMainBeanDao.getLanguage(salesTaxBean
//						.getLanguageCode(), tmpLanguage);
//				if (tmpLanguage != null) {
//					tmpLanguage.setLangCode(salesTaxBean.getLanguageCode());
//					tmpLanguage.setName(salesTaxBean.getLanguage());
//					this.langDao.save(tmpLanguage);
//				}
//				if (tmpCountry != null) {
//					tmpCountry.setCountryCode(salesTaxBean.getCountry());
//					tmpCountry.setName(salesTaxBean.getCountryName());
//					tmpCountry.setModifiedBy(SessionUtil.getUserId());
//					tmpCountry.setCreatedBy(SessionUtil.getUserId());
//					tmpCountry.setCreationDate(new Date());
//					tmpCountry.setModifyDate(new Date());
//					tmpCountry.setCurrencyCode(salesTaxBean.getCurrencyCode());
//					tmpCountry.setContinent(salesTaxBean.getContinent());
//				}
//				this.countryDao.save(tmpCountry);
//				// 保存Currency
////				tmpCurrency = this.salesTaxMainBeanDao.getCurrency(salesTaxBean
////						.getCurrencyCode(), tmpCurrency);
////				if (tmpCurrency != null) {
////					tmpCurrency.setDescription(salesTaxBean.getCurrency());
////					tmpCurrency.setCurrencyCode(salesTaxBean.getCurrencyCode());
////				} else {
////					tmpCurrency = new PbCurrency();
////					tmpCurrency.setDescription(salesTaxBean.getCurrency());
////					tmpCurrency.setCurrencyCode(salesTaxBean.getCurrencyCode());
////				}
////				this.currencyDao.save(tmpCurrency);
//				// 保存Language
//				tmpLanguage = this.salesTaxMainBeanDao.getLanguage(salesTaxBean
//						.getLanguageCode(), tmpLanguage);
//				if (tmpLanguage != null) {
//					tmpLanguage.setLangCode(salesTaxBean.getLanguageCode());
//					tmpLanguage.setName(salesTaxBean.getLanguage());
//				} else {
//					tmpLanguage = new PbLanguage();
//					tmpLanguage.setLangCode(salesTaxBean.getLanguageCode());
//					tmpLanguage.setName(salesTaxBean.getLanguage());
//					tmpLanguage.setCreationDate(new Date());
//					tmpLanguage.setModifyDate(new Date());
//					tmpLanguage.setModifiedBy(SessionUtil.getUserId());
//					tmpLanguage.setCreatedBy(SessionUtil.getUserId());
//				}
//				this.langDao.save(tmpLanguage);
				//保存state
				List<PbState> stateList=this.salesTaxMainBeanDao.getStateList();
				for(PbState state:stateList){
					if(state.getStateCode()!=null&&salesTaxBean.getState()!=null){
					if(salesTaxBean.getState().equalsIgnoreCase(state.getStateCode())){
						return "same";
					}
					}
				}
//				tmpState=this.salesTaxMainBeanDao.findState(salesTaxBean.getState());
//				if(tmpState!=null){
//				 tmpState.setStateCode(salesTaxBean.getState());
//				 tmpState.setName(salesTaxBean.getStateName());
//				 tmpState.setModifyDate(new Date());
//				}else{
//					 tmpState=new PbState();
//					 tmpState.setCountryId(tmpCountry.getCountryId());
//					 tmpState.setStateCode(salesTaxBean.getState());
//					 tmpState.setName(tmpCountry.getName());
//					 tmpState.setCreatedBy(SessionUtil.getUserId());
//					 tmpState.setCreationDate(new Date());
//					 tmpState.setModifiedBy(SessionUtil.getUserId());
//					 tmpState.setModifyDate(new Date());
//				}
//				 this.stateDao.save(tmpState);
				newSalesTax = this.salesTaxMainBeanDao
						.getSalesTax(salesTaxBean);
				if (newSalesTax != null) {
					newSalesTax.setModifyDate(new Date());
					newSalesTax.setCountry(salesTaxBean.getCountry());
					newSalesTax.setState(salesTaxBean.getState());
					newSalesTax.setStatus(salesTaxBean.getStatus());
					newSalesTax.setTaxRate(salesTaxBean.getTaxRate());
					newSalesTax.setNote(salesTaxBean.getNote());
					newSalesTax.setModifyDate(new Date());
					this.salesTaxMainBeanDao.save(newSalesTax);
				} else {
					newSalesTax = new TaxRate();
					newSalesTax.setCountry(salesTaxBean.getCountry());
					newSalesTax.setState(salesTaxBean.getState());
					newSalesTax.setStatus(salesTaxBean.getStatus());
					newSalesTax.setTaxRate(salesTaxBean.getTaxRate());
					newSalesTax.setNote(salesTaxBean.getNote());
					newSalesTax.setModifyDate(new Date());
					newSalesTax.setCreationDate(new Date());
					newSalesTax.setCreatedBy(SessionUtil.getUserId());
					newSalesTax.setModifiedBy(SessionUtil.getUserId());
					this.salesTaxMainBeanDao.save(newSalesTax);
				}

			}
		
		return salesTaxBean.getCountry();
	}

	@Transactional(readOnly = true)
	public TaxRate getRateTaxInfo( String country,String state,Integer taxRateId) {
		TaxRate salesTaxBean = this.salesTaxMainBeanDao.getRateTaxInfo( country,state,taxRateId);
		if (salesTaxBean != null) {
			User temp = this.userDao.getById(salesTaxBean.getModifiedBy());
			if (temp != null) {
				salesTaxBean.setModifyName(temp.getLoginName());
			}
		}
		return salesTaxBean;

	}

	public TaxRate getRateInfo(String country,String stateCode,Integer taxRateId) {
		TaxRate salesTaxBean = this.salesTaxMainBeanDao.getRateInfo(country,stateCode,taxRateId);
		if (salesTaxBean != null) {
			User temp = this.userDao.getById(salesTaxBean.getModifiedBy());
			if (temp != null) {
				salesTaxBean.setModifyName(temp.getLoginName());
			}
		}
		return salesTaxBean;

	}

	public int delRateList(String[] rateNoArray) {
		int i = 0;
		for (String rateId : rateNoArray) {
			Integer rateNo = Integer.parseInt(rateId);
			i = this.salesTaxMainBeanDao.delRate(rateNo);
		}
		return i;
	}

	public Page<TaxRate> searchRateTax(Page<TaxRate> page, String countryCode,
			String countryName, String status) throws Exception {
		page = this.salesTaxMainBeanDao.getSalesTaxRateList(page, countryCode,
				countryName, status);
		return page;
	}

	@Transactional(readOnly = true)
	public List<TaxRate> getRateTaxList(String countryStr,String state)throws Exception {
		List<TaxRate> list = null;
		list = this.salesTaxMainBeanDao.getSalesTaxList(list,countryStr,state);
		return list;
	}

	@Transactional(readOnly = true)
	public Source getSourceDetail(int sourceId) {
		return sourceDao.getSourceDetail(sourceId);
	}

	@Transactional(readOnly = true)
	public PromotionBean getPromotionDetail(Integer promotionId) {
		return promotionBeanDao.getPromotionDetail(promotionId);
	}

	@Transactional(readOnly = true)
	public Page<Source> searchSource(Page<Source> page,
			List<PropertyFilter> filters) {
		return sourceDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<Source> searchSource(Page<Source> page) {
		return sourceDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<Source> searchSource(Page<Source> page,
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
		return sourceDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<PromotionBean> searchPromotion(Page<PromotionBean> page,
			List<PropertyFilter> filters) {
		return promotionBeanDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<PromotionBean> searchPromotion(Page<PromotionBean> page) {
		return promotionBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<PromotionBean> searchPromotion(Page<PromotionBean> page,
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
		return promotionBeanDao.findPage(page, filterList);
	}

	/**
	 * @author zhangyong
	 * @param page
	 * @param filters
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Page<Coupon> searchCoupon(Page<Coupon> page,
			List<PropertyFilter> filters) throws Exception {
		page = couponDao.findPage(page, filters);
		return page;
	}

	public List<String> saveSource(List<SourceDTO> sourceDTOList,
			List<Integer> delIds, Integer userId) {
		List<String> cannotDelIds = checkNotDeleteSourceList(delIds);
		if (sourceDTOList != null && sourceDTOList.size() > 0) {
			for (SourceDTO sourceDTO : sourceDTOList) {
				Source source = dozer.map(sourceDTO, Source.class);
				source.setCreatedBy(userId);
				source.setModifiedBy(userId);
				Date now = new Date();
				source.setCreationDate(now);
				source.setModifyDate(now);
				sourceDao.save(source);
			}
		}
		return cannotDelIds;
	}

	public List<String> savePromotion(List<PromotionDTO> promotionDTOList,
			List<Integer> delIds, Integer userId) {
		List<String> cannotDelIds = checkNotDeletePromotionList(delIds);
		if (promotionDTOList != null && promotionDTOList.size() > 0) {
			for (PromotionDTO promotionDTO : promotionDTOList) {
				Promotion promotion = dozer.map(promotionDTO, Promotion.class);
				promotion.setCreatedBy(userId);
				promotion.setModifiedBy(userId);
				Date now = new Date();
				promotion.setCreationDate(now);
				promotion.setModifyDate(now);
				promotionDao.save(promotion);
			}
		}
		return cannotDelIds;
	}

	public List<String> checkNotDeletePromotionList(List<Integer> prmtIdList) {
		if (prmtIdList != null && prmtIdList.size() > 0) {
			List<Integer> delIds = new ArrayList<Integer>();
			List<String> deletePromotionList = new ArrayList<String>();
			for (Integer prmtId : prmtIdList) {
				if(isPromotionApprove(prmtId)) {
					Promotion promotion = promotionDao.getById(prmtId);
					deletePromotionList.add(promotion.getPrmtCode());
				} else {
					delIds.add(prmtId);
				}
			}
			if (delIds != null && delIds.size() > 0) {
				promotionDao.delPromotions(delIds);
			}
			return deletePromotionList;
		}
		return null;
	}
	
	public List<String> checkNotDeleteSourceList(List<Integer> sourceIdList) {
		if (sourceIdList != null && sourceIdList.size() > 0) {
			List<Integer> delIds = new ArrayList<Integer>();
			List<String> deleteSourceList = new ArrayList<String>();
			for (Integer sourceId : sourceIdList) {
				if (checkSourceDependency(sourceId)) {
					Source source = sourceDao.get(sourceId);
					deleteSourceList.add(source.getCode());
				} else {
					delIds.add(sourceId);
				}
			}
			if (delIds != null && delIds.size() > 0) {
				sourceDao.delSources(delIds);
			}
			return deleteSourceList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	private Boolean checkSourceDependency(Integer sourceId) {
		boolean flag = false;
		List<Contact> contactList = contactDao.findBy("source", sourceId);
		if (contactList != null && contactList.size() > 0) {
			flag = true;
			return flag;
		}
		List<ContactContactHistory> contactHistoryList = contactContactHistDao
				.findBy("source", sourceId);
		if (contactHistoryList != null && contactHistoryList.size() > 0) {
			flag = true;
			return flag;
		}
		List<Customer> customerList = customerDao.findBy("source", sourceId);
		if (customerList != null && customerList.size() > 0) {
			flag = true;
			return flag;
		}
		List<CustomerContactHistory> customerHistoryList = custContactHistDao
				.findBy("source", sourceId);
		if (customerHistoryList != null && customerHistoryList.size() > 0) {
			flag = true;
			return flag;
		}
		List<CustomerSpecialPrice> customerSpecialPriceList = customerSpecialPriceDao
				.findBy("source", sourceId);
		if (customerSpecialPriceList != null
				&& customerSpecialPriceList.size() > 0) {
			flag = true;
			return flag;
		}
		List<Promotion> promotionList = promotionDao.findBy("orderSource",
				sourceId);
		if (promotionList != null && promotionList.size() > 0) {
			flag = true;
			return flag;
		}
		List<ProductSpecialPrice> productSpecialPriceList = productSpecialPriceDao
				.findBy("sourceId", sourceId);
		if (productSpecialPriceList != null
				&& productSpecialPriceList.size() > 0) {
			flag = true;
			return flag;
		}
		List<ServiceSpecialPrice> serviceSpecialPriceList = serviceSpecialPriceDao
				.findBy("sourceId", sourceId);
		if (serviceSpecialPriceList != null
				&& serviceSpecialPriceList.size() > 0) {
			flag = true;
			return flag;
		}
		return flag;
	}

	@Transactional(readOnly = true)
	public Page<ShipMethod> searchShipMethod(Page<ShipMethod> page,
			List<PropertyFilter> filters) {
		return shipMethodDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<ShipMethod> searchShipMethod(Page<ShipMethod> page) {
		return shipMethodDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<ShipMethod> searchShipMethod(Page<ShipMethod> page,
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
		return shipMethodDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public ShipMethodDTO getShipMethodDetail(final Integer methodId) {
		ShipMethodDTO shipMethodDTO = new ShipMethodDTO();
		ShipMethod shipMethod = shipMethodDao.getById(methodId);
		shipMethodDao.getSession().evict(shipMethod);
		if (shipMethod != null) {
			Integer modifiedBy = shipMethod.getModifiedBy();
			String modifiedUserName = userDao.getLoginName(modifiedBy);
			Integer standardZone = shipMethod.getStandardZone();
			Integer standardRate = shipMethod.getStandardRate();

			shipMethodDTO = dozer.map(shipMethod, ShipMethodDTO.class);
			if (standardZone != null) {
				StandardShipZoneType standardShipZoneType = standardShipZoneTypeDao
						.getById(standardZone);
				if (standardShipZoneType != null) {
					String standardZoneValue = standardShipZoneType.getName();
					shipMethodDTO.setStandardZoneValue(standardZoneValue);
				}
			}
			shipMethodDTO.setModifiedByUser(modifiedUserName);
			if (standardRate != null) {
				StandardShipMethod standardShipMethod = standardShipMethodDao
						.getById(standardRate);
				if (standardShipMethod != null) {
					String standardRateValue = standardShipMethod
							.getMethodName();
					shipMethodDTO.setStandardRateValue(standardRateValue);
				}
			}

		}
		List<ShipZone> shipZoneList = shipZoneDao.findBy("shipMethodId",
				methodId);
		if (shipZoneList != null && shipZoneList.size() > 0) {
			shipMethodDTO.setShipZoneList(shipZoneList);
		}
		List<ShipRate> shipRateList = shipRateDao.findBy("shipMethodId",
				methodId);
		if (shipRateList != null && shipRateList.size() > 0) {
			shipMethodDTO.setShipRateList(shipRateList);
		}

		ShipRateCustomerBasic shipRateCustomerBasic = shipRateCustomerBasicDao
				.getById(methodId);
		if (shipRateCustomerBasic != null) {
			shipMethodDTO.setShipRateCustomerBasic(shipRateCustomerBasic);
		}

		List<ShipRateTotalRange> shipRateTotalRangeList = shipRateTotalRangeDao
				.getShipRateTotalRangeList(methodId);
		if (shipRateTotalRangeList != null && shipRateTotalRangeList.size() > 0) {
			shipMethodDTO.setShipRateTotalRangeList(shipRateTotalRangeList);
		}
		List<ShipRateWeightRange> shipRateWeightRangeList = shipRateWeightRangeDao
				.getShipRateWeightRangeList(methodId);
		if (shipRateWeightRangeList != null
				&& shipRateWeightRangeList.size() > 0) {
			shipMethodDTO.setShipRateWeightRangeList(shipRateWeightRangeList);
		}
		return shipMethodDTO;
	}

	@Transactional(readOnly = true)
	public ZoneAndRateDTO getZoneAndRateList(Page<ShipZone> pageShipZone,
			Page<ShipRate> pageShipRate, final String type,
			final Integer warehouseId, final Integer shipMethodId) {
		if (warehouseId != null) {
			ZoneAndRateDTO zoneAndRateDTO = new ZoneAndRateDTO();
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter warhouseFilter = new PropertyFilter(
					"EQI_warehouseId", warehouseId);
			PropertyFilter methodIdFilter = new PropertyFilter(
					"EQI_shipMethodId", shipMethodId);
			filterList.add(warhouseFilter);
			filterList.add(methodIdFilter);
			if (type.equals("SHIP_ZONE")) {
				Page<ShipZone> pgShipZone = shipZoneDao.findPage(pageShipZone,
						filterList);
				if (pgShipZone != null) {
					zoneAndRateDTO.setPageShipZone(pgShipZone);
				}
			}
			if (type.equals("SHIP_RATE")) {
				Page<ShipRate> pgShipRate = shipRateDao.findPage(pageShipRate,
						filterList);
				if (pgShipRate != null) {
					zoneAndRateDTO.setPageShipRate(pgShipRate);
				}
			}
			return zoneAndRateDTO;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<DropDownListDTO> getShipRateByZone(final Integer zoneId) {
		return specDropDownListDao.getShipRateByZone(zoneId);
	}

	public Integer saveShipMethod(ShipMethodDTO shipMethodDTO,
			List<ShipZone> zoneList, List<ShipRate> rateList,
			List<Integer> shipZoneDelIdList, List<Integer> shipRateDelIdList,
			Integer userId) {
		// Determine and delete the previous shipZone
		Date now = new Date();
		if (shipMethodDTO != null) {
			ShipRateCustomerBasic shipRateCustomerBasic = shipMethodDTO
					.getShipRateCustomerBasic();
			List<ShipRateTotalRange> shipRateTotalRangeList = shipMethodDTO
					.getShipRateTotalRangeList();
			List<ShipRateWeightRange> shipRateWeightRangeList = shipMethodDTO
					.getShipRateWeightRangeList();
			List<Integer> totalRangeDelIdList = shipMethodDTO
					.getTotalRangeDelIdList();
			List<Integer> weightRangeDelIdList = shipMethodDTO
					.getWeightRangeDelIdList();
			// Integer standardZone = shipMethodDTO.getStandardZone();
			// Integer standardRate = shipMethodDTO.getStandardRate();
			// Integer shipMethodId = shipMethodDTO.getMethodId();
			ShipMethod shipMethod = null;
			if (shipMethodDTO.getMethodId() != null) {
				shipMethod = shipMethodDao.get(shipMethodDTO.getMethodId());
			} else {
				shipMethod = new ShipMethod();
			}

			shipMethod = dozer.map(shipMethodDTO, ShipMethod.class);

			// if(shipMethodId != null){
			// ShipMethod dbShipMethod = shipMethodDao.get(shipMethodId);
			// Integer dbShipMethodZone = dbShipMethod.getStandardZone();
			// Integer dbShipRate = dbShipMethod.getStandardRate();
			// if(!standardZone.equals(dbShipMethodZone)){
			// List<ShipZone> shipZoneList = shipZoneDao.findBy("shipMethodId",
			// shipMethodId);
			// if(shipZoneList != null && shipZoneList.size() > 0){
			// for(ShipZone shipZone : shipZoneList){
			// shipZoneDao.delete(shipZone);
			// }
			// }
			// }
			// if(!standardRate.equals(dbShipRate)){
			// List<ShipRate> shipRateList = shipRateDao.findBy("shipMethodId",
			// shipMethodId);
			// if(shipRateList != null && shipRateList.size() > 0){
			// for(ShipRate shipRate : shipRateList){
			// shipRateDao.delete(shipRate);
			// }
			// }
			// }
			// shipMethodDao.getSession().evict(dbShipMethod);
			// }
			shipMethod.setCreationDate(now);
			shipMethod.setModifyDate(now);
			shipMethod.setCreatedBy(userId);
			shipMethod.setModifiedBy(userId);
			System.out.println("shipMethod: " + shipMethod);
			shipMethodDao.save(shipMethod);
			Integer methodId = shipMethod.getMethodId();
			// if(shipMethodId != null){
			// if(shipMethod.getStandardZone().equals(standardZone))
			// {
			// }else{
			// copyStandardZoneTypeToShipZone(userId, now, standardZone,
			// shipMethod);
			// }
			// }else{
			// copyStandardZoneTypeToShipZone(userId, now, standardZone,
			// shipMethod);
			// }
			//			
			// if(shipMethodId != null){
			// if(standardRate != null &&
			// standardRate.equals(shipMethod.getStandardRate())){
			//					
			// }else{
			// copyStandardShipRateToShipRate(userId, now, shipMethod);
			// }
			// }else{
			// copyStandardShipRateToShipRate(userId, now, shipMethod);
			// }

			attachSaveShipZone(zoneList, shipZoneDelIdList, userId, now,
					methodId);

			attachSaveShipRate(rateList, shipRateDelIdList, userId, now,
					methodId);

			attachSaveShipRateCustomerBasic(userId, now, shipRateCustomerBasic,
					methodId);

			attachSaveShipRateTotalRange(userId, now, shipRateTotalRangeList,
					totalRangeDelIdList, methodId);

			attachSaveShipWeightRange(userId, now, shipRateWeightRangeList,
					weightRangeDelIdList, methodId);
			return methodId;
		}
		return null;
	}

	public List<String> delShipMethodList(List<Integer> shipMethodDelIdList) {
		if (shipMethodDelIdList != null && shipMethodDelIdList.size() > 0) {
			List<String> deleteShipMethodList = new ArrayList<String>();
			for (Integer methodId : shipMethodDelIdList) {
				if (checkShipMethodDependency(methodId)) {
					ShipMethod shipMethod = shipMethodDao.get(methodId);
					deleteShipMethodList.add(shipMethod.getMethodCode());
				} else {
					delCascadeShipMethod(methodId);
				}
			}

			return deleteShipMethodList;
		}
		return null;
	}

	// Cascade delete ShipMethod related object
	private void delCascadeShipMethod(Integer methodId) {
		List<Integer> shipZoneIdList = shipZoneDao.getZoneIdList(methodId);
		if (shipZoneIdList != null && shipZoneIdList.size() > 0) {
			shipZoneDao.delShipZoneList(shipZoneIdList);
		}
		List<Integer> shipRateIdList = shipRateDao.getRateIdList(methodId);
		if (shipRateIdList != null && shipRateIdList.size() > 0) {
			shipRateDao.delShipRateList(shipRateIdList);
		}
		List<Integer> shipRateCustomerBasic = shipRateCustomerBasicDao
				.getMethodIdList(methodId);
		if (shipRateCustomerBasic != null && shipRateCustomerBasic.size() > 0) {
			shipRateCustomerBasicDao.delShipMethodList(shipRateCustomerBasic);
		}
		List<Integer> shipRateTotalRangeIdList = shipRateTotalRangeDao
				.getRateTotalRangeIdList(methodId);
		if (shipRateTotalRangeIdList != null
				&& shipRateTotalRangeIdList.size() > 0) {
			shipRateTotalRangeDao.delTotalRangeList(shipRateTotalRangeIdList);
		}
		List<Integer> shipRateWeightRangeIdList = shipRateWeightRangeDao
				.getWeightTotalRangeIdList(methodId);
		if (shipRateWeightRangeIdList != null
				&& shipRateWeightRangeIdList.size() > 0) {
			shipRateWeightRangeDao
					.delWeightRangeList(shipRateWeightRangeIdList);
		}
		shipMethodDao.delete(methodId);
	}

	// Relevance Save ShipRate
	private void attachSaveShipRate(List<ShipRate> rateList,
			List<Integer> delIdList, Integer userId, Date now,
			Integer shipMethodId) {
		if (delIdList != null && delIdList.size() > 0) {
			shipRateDao.delShipRateList(delIdList);
		}
		if (rateList != null && rateList.size() > 0) {
			for (ShipRate rate : rateList) {
				rate.setShipMethodId(shipMethodId);
				rate.setModifiedBy(userId);
				rate.setCreatedBy(userId);
				rate.setCreationDate(now);
				rate.setModifyDate(now);
				shipRateDao.save(rate);
			}
		}
	}

	// Relevance Save ShipZone
	private void attachSaveShipZone(List<ShipZone> zoneList,
			List<Integer> delIdList, Integer userId, Date now,
			Integer shipMethodId) {
		if (delIdList != null && delIdList.size() > 0) {
			shipZoneDao.delShipZoneList(delIdList);
		}
		if (zoneList != null && zoneList.size() > 0) {
			for (ShipZone zone : zoneList) {
				zone.setShipMethodId(shipMethodId);
				zone.setModifiedBy(userId);
				zone.setCreatedBy(userId);
				zone.setCreationDate(now);
				zone.setModifyDate(now);
				shipZoneDao.save(zone);
			}
		}
	}

	// Relevance Save ShipRateCustomerBasic
	private void attachSaveShipRateCustomerBasic(Integer userId, Date now,
			ShipRateCustomerBasic shipRateCustomerBasic, Integer shipMethodId) {
		if (shipRateCustomerBasic != null) {
			shipRateCustomerBasic.setShipMethodId(shipMethodId);
			shipRateCustomerBasic.setModifiedBy(userId);
			shipRateCustomerBasic.setCreatedBy(userId);
			shipRateCustomerBasic.setCreationDate(now);
			shipRateCustomerBasic.setModifyDate(now);
			shipRateCustomerBasicDao.save(shipRateCustomerBasic);
		}
	}

	// Relevance Save ShipRateTotalRange
	private void attachSaveShipRateTotalRange(Integer userId, Date now,
			List<ShipRateTotalRange> shipRateTotalRangeList,
			List<Integer> delIdList, Integer shipMethodId) {
		if (delIdList != null && delIdList.size() > 0) {
			shipRateTotalRangeDao.delTotalRangeList(delIdList);
		}
		if (shipRateTotalRangeList != null && shipRateTotalRangeList.size() > 0) {
			for (ShipRateTotalRange shipRateTotalRange : shipRateTotalRangeList) {
				shipRateTotalRange.setShipMethodId(shipMethodId);
				shipRateTotalRange.setModifiedBy(userId);
				shipRateTotalRange.setCreatedBy(userId);
				shipRateTotalRange.setCreationDate(now);
				shipRateTotalRange.setModifyDate(now);
				shipRateTotalRangeDao.save(shipRateTotalRange);
			}
		}
	}

	// Relevance Save ShipWeightRange
	private void attachSaveShipWeightRange(Integer userId, Date now,
			List<ShipRateWeightRange> shipRateWeightRangeList,
			List<Integer> delIdList, Integer shipMethodId) {
		if (delIdList != null && delIdList.size() > 0) {
			shipRateWeightRangeDao.delWeightRangeList(delIdList);
		}
		if (shipRateWeightRangeList != null
				&& shipRateWeightRangeList.size() > 0) {
			for (ShipRateWeightRange shipRateWeightRange : shipRateWeightRangeList) {
				shipRateWeightRange.setShipMethodId(shipMethodId);
				shipRateWeightRange.setModifiedBy(userId);
				shipRateWeightRange.setCreatedBy(userId);
				shipRateWeightRange.setCreationDate(now);
				shipRateWeightRange.setModifyDate(now);
				shipRateWeightRangeDao.save(shipRateWeightRange);
			}
		}
	}

	// Copy StandardZoneTypeDetail data to ShipZone
	@SuppressWarnings("unused")
	private void copyStandardZoneTypeToShipZone(Integer userId, Date now,
			Integer standardZone, ShipMethod shipMethod) {
		Integer shipMethodId;
		shipMethodId = shipMethod.getMethodId();
		List<StandardShipZoneDetail> standardShipZoneDetailList = standardShipZoneDetailDao
				.findBy("zoneType", standardZone);
		if (standardShipZoneDetailList != null
				&& standardShipZoneDetailList.size() > 0) {
			for (StandardShipZoneDetail standardShipZoneDetail : standardShipZoneDetailList) {
				ShipZone shipZone = dozer.map(standardShipZoneDetail,
						ShipZone.class);
				shipZone.setShipMethodId(shipMethodId);
				shipZone.setId(null);
				shipZone.setCreationDate(now);
				shipZone.setModifyDate(now);
				shipZone.setCreatedBy(userId);
				shipZone.setModifiedBy(userId);
				shipZoneDao.save(shipZone);
			}
		}
	}

	// Copy StandardShipRate data to ShipRate
	@SuppressWarnings("unused")
	private void copyStandardShipRateToShipRate(Integer userId, Date now,
			ShipMethod shipMethod) {
		Integer shipMethodId;
		shipMethodId = shipMethod.getMethodId();
		List<StandardShipRate> standardShipRateList = standardShipRateDao
				.findBy("shipMethodId", shipMethodId);
		if (standardShipRateList != null && standardShipRateList.size() > 0) {
			for (StandardShipRate standardShipRate : standardShipRateList) {
				ShipRate shipRate = dozer.map(standardShipRate, ShipRate.class);
				shipRate.setShipMethodId(shipMethodId);
				shipRate.setId(null);
				shipRate.setCreationDate(now);
				shipRate.setModifyDate(now);
				shipRate.setCreatedBy(userId);
				shipRate.setModifiedBy(userId);
				shipRateDao.save(shipRate);
			}
		}
	}

	@Transactional(readOnly = true)
	private Boolean checkShipMethodDependency(Integer methodId) {
		boolean flag = false;
		List<OrderItem> orderItemList = orderItemDao.findBy("shipMethod",
				methodId);
		if (orderItemList != null && orderItemList.size() > 0) {
			flag = true;
			return flag;
		}
		List<Promotion> promotionList = promotionDao.findBy("shipMethod",
				methodId);
		if (promotionList != null && promotionList.size() > 0) {
			flag = true;
			return flag;
		}
		List<QuoteItem> quoteItemList = quoteItemDao.findBy("shipMethod",
				methodId);
		if (quoteItemList != null && quoteItemList.size() > 0) {
			flag = true;
			return flag;
		}
		List<QuotePackage> quotePackageList = quotePackageDao.findBy(
				"shipMethod", methodId);
		if (quotePackageList != null && quotePackageList.size() > 0) {
			flag = true;
			return flag;
		}
		List<OrderPackage> shipPackageList = orderPackageDao.findBy(
				"shipMethod", methodId);
		if (shipPackageList != null && shipPackageList.size() > 0) {
			flag = true;
			return flag;
		}
		return flag;
	}

	/**
	 * 根据一些条件, 获取对应的所有Promotion Code.
	 * 
	 * @author golf
	 * @since 2010-11-11
	 * @param sourceId
	 * @param type
	 *            order or quote
	 * @return List<String> mod by lizhang 增加过滤条件
	 */
	@Transactional(readOnly = true)
	public List<String> getPrmtCdListBySourceId(Integer sourceId,String quOrderNo,Integer custNo,String type){
		List<String> prmtCodeList = promotionBeanDao.getPrmtCdListBySourceId(sourceId);
		
//		List<String> prmtCodeFilterList = new ArrayList<String>();
//		if(prmtCodeList!=null&&prmtCodeList.size()>0) {
//			for(String prmtCode:prmtCodeList) {
//				Promotion promotion = promotionDao.findUniqueBy("prmtCode", prmtCode);
//				String applyType = promotion==null?null:promotion.getApplyType();
//				if(applyType!=null&&"Once per Customer".equals(applyType)) {
//					//Once per Customer
//					if(this.orderDao.isHasPrmtForCust(custNo,prmtCode)) {
//						prmtCodeFilterList.add(prmtCode);
//						continue;
//					}
//				}
//				if (applyType != null && "First Order".equals(applyType)) {
//					// firstOrder
//					if(this.orderDao.isHasCreateOrder(custNo,quOrderNo)) {
//						prmtCodeFilterList.add(prmtCode);
//						continue;
//					}
//				}
//				if("order".equals(type)) {
//					
//					/****add by lizhang 增加order/quote过滤条件***/
//					if(promotion.getOrderEffFrom()!=null&&promotion.getOrderEffTo()!=null) {
//						OrderMainDTO orderMain = (OrderMainDTO)SessionUtil.getRow(SessionConstant.Order.value(), quOrderNo);
//						if(orderMain.getOrderDate()==null&&(new Date().after(promotion.getOrderEffTo())||
//								new Date().before(promotion.getOrderEffFrom()))) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//						if(orderMain.getOrderDate()!=null
//								&&(orderMain.getOrderDate().after(promotion.getOrderEffTo())||
//										orderMain.getOrderDate().before(promotion.getOrderEffFrom()))) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//					}
//					if(StringUtils.isNotEmpty(promotion.getOrderCatalogNo())) {
//						boolean flg = false;
//						Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>)SessionUtil.getRow(SessionConstant.OrderItemList.value(),
//								quOrderNo);
//						if(itemMap!=null) {
//							List<OrderItemDTO> orderItemList = SessionUtil.convertMap2List(itemMap);
//							for(OrderItemDTO orderItemDTO:orderItemList) {
//								if(promotion.getOrderCatalogNo().equals(orderItemDTO.getCatalogNo())&&!OrderStatusType.CN.value().equals(orderItemDTO.getStatus())) {
//									flg = true;
//									break;
//								}
//							}
//						}
//						if(!flg) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//					} else if(promotion.getOrderItemCategory()!=null&&!"".equals(promotion.getOrderItemCategory().trim())) {
//						boolean flg = false;
//						Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>)SessionUtil.getRow(SessionConstant.OrderItemList.value(),
//								quOrderNo);
//						if(itemMap!=null) {
//							List<OrderItemDTO> orderItemList = SessionUtil.convertMap2List(itemMap);
//							for(OrderItemDTO orderItemDTO:orderItemList) {
//								String catagoryNo = null;
//								if(QuoteItemType.PRODUCT.value().equals(orderItemDTO.getType())) {
//									ProductInCategoryBean productInCategoryBean = this.productInCategoryBeanDao
//															.getBeanByCatalog(orderItemDTO.getCatalogNo(), orderItemDTO.getCatalogId());
//									catagoryNo = productInCategoryBean!=null?productInCategoryBean.getCategoryNo():null;
//								
//								} else if(QuoteItemType.SERVICE.value().equals(orderItemDTO.getType())) {
//									ServiceOfServcategoryBean serviceOfServcategoryBean = this.serviceOfServcategoryBeanDao
//															.getBeanByCatalog(orderItemDTO.getCatalogNo(), orderItemDTO.getCatalogId());
//									catagoryNo = serviceOfServcategoryBean!=null?serviceOfServcategoryBean.getCategoryNo():null;
//								}
//								
//								if(catagoryNo!=null&&promotion.getOrderItemCategory().equals(catagoryNo)&&!OrderStatusType.CN.value().equals(orderItemDTO.getStatus())) {
//									flg = true;
//									break;
//								}
//							}
//						}
//						if(!flg) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//					}
//				
//					/**end**/
//				} else {
//					
//					/****add by lizhang 增加order/quote过滤条件***/
//					if(promotion.getOrderEffFrom()!=null&&promotion.getOrderEffTo()!=null) {
//						QuoteMainDTO quoteMain = (QuoteMainDTO)SessionUtil.getRow(SessionConstant.Quote.value(), quOrderNo);
//						if(quoteMain.getQuoteDate()==null&&(new Date().after(promotion.getOrderEffTo())||
//								new Date().before(promotion.getOrderEffFrom()))) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//						if(quoteMain.getQuoteDate()!=null
//								&&(quoteMain.getQuoteDate().after(promotion.getOrderEffTo())||
//										quoteMain.getQuoteDate().before(promotion.getOrderEffFrom()))) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//					}
//					if(StringUtils.isNotEmpty(promotion.getOrderCatalogNo())) {
//						boolean flg = false;
//						Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>)SessionUtil.getRow(SessionConstant.QuoteItemList.value(),
//								quOrderNo);
//						if(itemMap!=null) {
//							List<QuoteItemDTO> quoteItemList = SessionUtil.convertMap2List(itemMap);
//							for(QuoteItemDTO quoteItemDTO:quoteItemList) {
//								if(promotion.getOrderCatalogNo().equals(quoteItemDTO.getCatalogNo())&&!OrderStatusType.CN.value().equals(quoteItemDTO.getStatus())) {
//									flg = true;
//									break;
//								}
//							}
//						}
//						if(!flg) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//					} else if(promotion.getOrderItemCategory()!=null&&!"".equals(promotion.getOrderItemCategory().trim())) {
//						boolean flg = false;
//						Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>)SessionUtil.getRow(SessionConstant.QuoteItemList.value(),
//								quOrderNo);
//						if(itemMap!=null) {
//							List<QuoteItemDTO> quoteItemList = SessionUtil.convertMap2List(itemMap);
//							for(QuoteItemDTO quoteItemDTO:quoteItemList) {
//								String catagoryNo = null;
//								if(QuoteItemType.PRODUCT.value().equals(quoteItemDTO.getType())) {
//									ProductInCategoryBean productInCategoryBean = this.productInCategoryBeanDao
//															.getBeanByCatalog(quoteItemDTO.getCatalogNo(), quoteItemDTO.getCatalogId());
//									catagoryNo = productInCategoryBean!=null?productInCategoryBean.getCategoryNo():null;
//								
//								} else if(QuoteItemType.SERVICE.value().equals(quoteItemDTO.getType())) {
//									ServiceOfServcategoryBean serviceOfServcategoryBean = this.serviceOfServcategoryBeanDao
//															.getBeanByCatalog(quoteItemDTO.getCatalogNo(), quoteItemDTO.getCatalogId());
//									catagoryNo = serviceOfServcategoryBean!=null?serviceOfServcategoryBean.getCategoryNo():null;
//								}
//								
//								if(catagoryNo!=null&&promotion.getOrderItemCategory().equals(catagoryNo)&&!OrderStatusType.CN.value().equals(quoteItemDTO.getStatus())) {
//									flg = true;
//									break;
//								}
//							}
//						}
//						if(!flg) {
//							prmtCodeFilterList.add(prmtCode);
//							continue;
//						}
//					}
//				
//					/**end**/
//				}
//				/**add by lizhang 增加customer过滤条件**/
//				if(promotion.getCustNo()==null||(promotion.getCustNo()!=null&&custNo.equals(promotion.getCustNo()))) {
//					if(!this.customerDao.findBySomeCondition(promotion)) {
//						prmtCodeFilterList.add(prmtCode);
//						continue;
//					}
//				} else if(!custNo.equals(promotion.getCustNo())) {
//					prmtCodeFilterList.add(prmtCode);
//					continue;
//				} 
//				/**end**/
//				
//				
//			}
//			prmtCodeList.removeAll(prmtCodeFilterList);
//		}
	
		
		return prmtCodeList;
	}
	
	/**
	 * promation应用判断
	 */
	public boolean isPrmtOk(Integer custNo,String prmtCode,String quOrderNo,String type) {
		if(prmtCode==null||!StringUtils.isNotEmpty(prmtCode)) {
			return true;
		}
		Promotion promotion = promotionDao.findUniqueBy("prmtCode", prmtCode);
		if(promotion==null) {
			return true;
		}
		String applyType = promotion==null?null:promotion.getApplyType();
		if(applyType!=null&&"Once per Customer".equals(applyType)) {
			//Once per Customer
			if(this.orderDao.isHasPrmtForCust(custNo,prmtCode)) {
				return false;
			}
		}
		if (applyType != null && "First Order".equals(applyType)) {
			// firstOrder
			if(this.orderDao.isHasCreateOrder(custNo,quOrderNo)) {
				return false;
			}
		}
		if("order".equals(type)) {
			
			/****add by lizhang 增加order/quote过滤条件***/
			if(promotion.getOrderEffFrom()!=null&&promotion.getOrderEffTo()!=null) {
				OrderMainDTO orderMain = (OrderMainDTO)SessionUtil.getRow(SessionConstant.Order.value(), quOrderNo);
				if(orderMain.getOrderDate()==null&&(new Date().after(promotion.getOrderEffTo())||
						new Date().before(promotion.getOrderEffFrom()))) {
					return false;
				}
				if(orderMain.getOrderDate()!=null
						&&(orderMain.getOrderDate().after(promotion.getOrderEffTo())||
								orderMain.getOrderDate().before(promotion.getOrderEffFrom()))) {
					return false;
				}
			}
			if(StringUtils.isNotEmpty(promotion.getOrderCatalogNo())) {
				boolean flg = false;
				Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>)SessionUtil.getRow(SessionConstant.OrderItemList.value(),
						quOrderNo);
				if(itemMap!=null) {
					List<OrderItemDTO> orderItemList = SessionUtil.convertMap2List(itemMap);
					for(OrderItemDTO orderItemDTO:orderItemList) {
						if(promotion.getOrderCatalogNo().equals(orderItemDTO.getCatalogNo())&&!OrderStatusType.CN.value().equals(orderItemDTO.getStatus())) {
							flg = true;
							break;
						}
					}
				}
				if(!flg) {
					return false;
				}
			} else if(promotion.getOrderItemCategory()!=null&&!"".equals(promotion.getOrderItemCategory().trim())) {
				boolean flg = false;
				Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>)SessionUtil.getRow(SessionConstant.OrderItemList.value(),
						quOrderNo);
				if(itemMap!=null) {
					List<OrderItemDTO> orderItemList = SessionUtil.convertMap2List(itemMap);
					for(OrderItemDTO orderItemDTO:orderItemList) {
						String catagoryNo = null;
						if(QuoteItemType.PRODUCT.value().equals(orderItemDTO.getType())) {
							ProductInCategoryBean productInCategoryBean = this.productInCategoryBeanDao
													.getBeanByCatalog(orderItemDTO.getCatalogNo(), orderItemDTO.getCatalogId());
							catagoryNo = productInCategoryBean!=null?productInCategoryBean.getCategoryNo():null;
						
						} else if(QuoteItemType.SERVICE.value().equals(orderItemDTO.getType())) {
							ServiceOfServcategoryBean serviceOfServcategoryBean = this.serviceOfServcategoryBeanDao
													.getBeanByCatalog(orderItemDTO.getCatalogNo(), orderItemDTO.getCatalogId());
							catagoryNo = serviceOfServcategoryBean!=null?serviceOfServcategoryBean.getCategoryNo():null;
						}
						
						if(catagoryNo!=null&&promotion.getOrderItemCategory().equals(catagoryNo)&&!OrderStatusType.CN.value().equals(orderItemDTO.getStatus())) {
							flg = true;
							break;
						}
					}
				}
				if(!flg) {
					return false;
				}
			}
		
			/**end**/
		} else {
			
			/****add by lizhang 增加order/quote过滤条件***/
			if(promotion.getOrderEffFrom()!=null&&promotion.getOrderEffTo()!=null) {
				QuoteMainDTO quoteMain = (QuoteMainDTO)SessionUtil.getRow(SessionConstant.Quote.value(), quOrderNo);
				if(quoteMain.getQuoteDate()==null&&(new Date().after(promotion.getOrderEffTo())||
						new Date().before(promotion.getOrderEffFrom()))) {
					return false;
				}
				if(quoteMain.getQuoteDate()!=null
						&&(quoteMain.getQuoteDate().after(promotion.getOrderEffTo())||
								quoteMain.getQuoteDate().before(promotion.getOrderEffFrom()))) {
					return false;
				}
			}
			if(StringUtils.isNotEmpty(promotion.getOrderCatalogNo())) {
				boolean flg = false;
				Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>)SessionUtil.getRow(SessionConstant.QuoteItemList.value(),
						quOrderNo);
				if(itemMap!=null) {
					List<QuoteItemDTO> quoteItemList = SessionUtil.convertMap2List(itemMap);
					for(QuoteItemDTO quoteItemDTO:quoteItemList) {
						if(promotion.getOrderCatalogNo().equals(quoteItemDTO.getCatalogNo())&&!OrderStatusType.CN.value().equals(quoteItemDTO.getStatus())) {
							flg = true;
							break;
						}
					}
				}
				if(!flg) {
					return false;
				}
			} else if(promotion.getOrderItemCategory()!=null&&!"".equals(promotion.getOrderItemCategory().trim())) {
				boolean flg = false;
				Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>)SessionUtil.getRow(SessionConstant.QuoteItemList.value(),
						quOrderNo);
				if(itemMap!=null) {
					List<QuoteItemDTO> quoteItemList = SessionUtil.convertMap2List(itemMap);
					for(QuoteItemDTO quoteItemDTO:quoteItemList) {
						String catagoryNo = null;
						if(QuoteItemType.PRODUCT.value().equals(quoteItemDTO.getType())) {
							ProductInCategoryBean productInCategoryBean = this.productInCategoryBeanDao
													.getBeanByCatalog(quoteItemDTO.getCatalogNo(), quoteItemDTO.getCatalogId());
							catagoryNo = productInCategoryBean!=null?productInCategoryBean.getCategoryNo():null;
						
						} else if(QuoteItemType.SERVICE.value().equals(quoteItemDTO.getType())) {
							ServiceOfServcategoryBean serviceOfServcategoryBean = this.serviceOfServcategoryBeanDao
													.getBeanByCatalog(quoteItemDTO.getCatalogNo(), quoteItemDTO.getCatalogId());
							catagoryNo = serviceOfServcategoryBean!=null?serviceOfServcategoryBean.getCategoryNo():null;
						}
						
						if(catagoryNo!=null&&promotion.getOrderItemCategory().equals(catagoryNo)&&!OrderStatusType.CN.value().equals(quoteItemDTO.getStatus())) {
							flg = true;
							break;
						}
					}
				}
				if(!flg) {
					return false;
				}
			}
		
			/**end**/
		}
		/**add by lizhang 增加customer过滤条件**/
		if(promotion.getCustNo()==null||(promotion.getCustNo()!=null&&custNo.equals(promotion.getCustNo()))) {
			if(!this.customerDao.findBySomeCondition(promotion)) {
				return false;
			}
		} else if(!custNo.equals(promotion.getCustNo())) {
			return false;
		} 
		/**end**/
		return true;
	}

	/**
	 * 2011-01-17 By wangsf 查找promotionCode为MerckPromotion的promotion
	 * Merck的客户Promotion自动应用到这个Promotion
	 * 
	 * @return
	 */
	public Promotion getMerckPromotion() {
		Promotion promotion = promotionDao.findUniqueBy("prmtCode",
				"MerckPromotion");
		return promotion;
	}
	/**
	 * 根据promotionCode查找promotionBean
	 * 
	 * @param prmtCode
	 */
	public PromotionBean findBypromotionCode(String prmtCode) {
		PromotionBean promotionBean = promotionBeanDao.findUniqueBy("prmtCode",
				prmtCode);
		return promotionBean;
	}

	public boolean isPromotionApprove(String prmtCode){
		if (orderPromotionDao.findPromotionByCode(prmtCode) > 0 || quotePromotionDao.findPromotionByCode(prmtCode) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isPromotionApprove(Integer prmtId){
		Promotion promotion = promotionDao.getById(prmtId);
		return isPromotionApprove(promotion.getPrmtCode());
	}
}
