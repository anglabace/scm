package com.genscript.gsscm.quoteorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.Arith;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerPriceBeanDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.olddb.dao.VectorInfoListDao;
import com.genscript.gsscm.olddb.entity.VectorInfoList;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.PromotionDao;
import com.genscript.gsscm.order.dao.TaxRateDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.Promotion;
import com.genscript.gsscm.orf.dao.Refseq2orfpriceDao;
import com.genscript.gsscm.orf.dto.Refseq2orfpriceDTO;
import com.genscript.gsscm.orf.entity.Refseq2orfprice;
import com.genscript.gsscm.product.dao.OligoDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ProductInCategoryBean2Dao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductInCategoryBean2;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderServiceDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.TotalDTO;
import com.genscript.gsscm.quoteorder.dto.VectorInfoListDTO;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceOfServcategoryBeanDao;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;
import com.genscript.gsscm.serv.entity.ServiceOfServcategoryBean;

@Service
@Transactional
public class QuoteOrderService {

	@Autowired
	private CustomerPriceBeanDao customerPriceBeanDao;
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private TaxRateDao taxRateDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private ProductDao  productDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceOfServcategoryBeanDao serviceOfServcategoryBeanDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductInCategoryBean2Dao productInCategoryBean2Dao;
	@Autowired
	private VectorInfoListDao vectorInfoListDao;
	@Autowired
	private Refseq2orfpriceDao refseq2orfpriceDao;
	@Autowired
	private OligoDao oligoDao;
	@Autowired
	private PublicService publicService;
	@Autowired
	private DozerBeanMapper dozer;
	private final static String Y = "Y";
	private final static String tbd_0 = "0";
	private final static String tbd_1 = "1";
	private final static String pUC57 = "pUC57";
	private final static String Other = "Other";
	private final static String FullLength = "Full Length";
	private final static String ORFSequence = "ORF Sequence";

	/**
	 * 获得order/quote 的 ship fee
	 * 
	 * @param <T>
	 * @param packageList
	 * @return
	 */
	public <T> Double getShipAmtFromPackage(List<T> packageList) {
		Double rt = null;
		Double shipFee = 0.0;
		if (packageList != null && packageList.size() > 0) {
			for (int i = 0; i < packageList.size(); i++) {
				T pac = packageList.get(i);
				if (pac instanceof OrderPackageDTO) {
					shipFee += ((OrderPackageDTO) pac).getCustomerCharge();
				} else if (pac instanceof QuotePackageDTO) {
					shipFee += ((QuotePackageDTO) pac).getCustomerCharge();
				} else if (pac instanceof OrderPackage){
					shipFee = Arith.add(shipFee,((OrderPackage) pac).getCustomerCharge());
				}
				if (shipFee != null
						&& (rt == null || shipFee.doubleValue() > rt
								.doubleValue())) {
					rt = shipFee;
				}
			}
		}
		return rt;
	}
	
	/**
	 * 获得所有子item
	 * 
	 * @param itemId
	 * @return
	 */
	public <T> Map<String, T> getChildItemMap(Map<String, T> itemMap, String itemId) {
		Map<String, T> newItemMap = new HashMap<String, T>();
		Iterator<Entry<String, T>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, T> entry = it.next();
			T itemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = null;
			if(itemDTO instanceof OrderItemDTO){
				tmpParentId = ((OrderItemDTO) itemDTO).getParentId();
			}else if(itemDTO instanceof QuoteItemDTO){
				tmpParentId = ((QuoteItemDTO) itemDTO).getParentId();
			}
			if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, itemDTO);
			}
		}
		return newItemMap;
	}
	
	/**
	 * 计算leadime和
	 * @param <T>
	 * @param itemMap
	 * @return
	 */
	public <T> Integer getTotalLeadTime(Map<String, T> itemMap){
		if(itemMap == null){
			return null;
		}
		Integer totalTime = 0;
		Iterator<Entry<String, T>> it = itemMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, T> entry = it.next();
			T itemDTO = entry.getValue();
			Integer leadTime = null;
			if(itemDTO instanceof OrderItemDTO){
				leadTime = ((OrderItemDTO) itemDTO).getShipSchedule();
			}else if(itemDTO instanceof QuoteItemDTO){
				leadTime = ((QuoteItemDTO) itemDTO).getShipSchedule();
			}
			if(leadTime == null){
				leadTime = 0;
			}
			totalTime += leadTime;
		}
		return totalTime;
	}
	
	/**
	 * 计算 order/quote 价格
	 * @param <T>
	 * @param itemList
	 * @param custNo
	 * @param promotionCode
	 * @param fromCurrency
	 * @param toCurrency
	 */
	@Transactional(readOnly = true)
	public <T> String calProductItemPrice(List<T> itemList, Integer custNo, String promotionCode,
			String fromCurrency, String toCurrency, String country, String state,Date orderQuoteDate){
		String result = null;
		Double amountTotal = this.getAmountTotalByUnitPrice(itemList, custNo);
		//计算价格 unitprice amount
		this.calProductItemPrice(itemList, custNo, amountTotal);
		Customer customer = this.customerDao.getById(custNo);
		if(customer!=null&&customer.getSpecialDiscount()!=null) {
			this.calItemDiscount(itemList,customer,orderQuoteDate);
		}
		//计算discount
		if(StringUtils.isNotBlank(promotionCode)) {
			result = this.calProductItemDiscount(itemList, custNo, promotionCode, amountTotal);
		}

		//计算tax
		this.calProductItemTax(itemList,country, state, custNo);
		//计算汇率
		//this.changeItemCurrency(itemList, fromCurrency, toCurrency);
		return result;
	}

	/*
	 * 为product 类型的item计算价格
	 */
	private <T> void calProductItemPrice(List<T> itemList, Integer custNo, Double amountTotal) {
		if (itemList == null || itemList.size() == 0) {
			return;
		}
		// 查看是否可以用customer specail price
		for (int i = 0; i < itemList.size(); i++) {
			T itemDTO = itemList.get(i);
			String catalogNo = null;
			Integer quantity = null;
			String status = null;
			String type = null;
			if (itemDTO instanceof OrderItemDTO) {
				catalogNo = ((OrderItemDTO) itemDTO).getCatalogNo();
				quantity = ((OrderItemDTO) itemDTO).getQuantity();
				status = ((OrderItemDTO) itemDTO).getStatus();
				type = ((OrderItemDTO) itemDTO).getType();
			} else if (itemDTO instanceof QuoteItemDTO) {
				catalogNo = ((QuoteItemDTO) itemDTO).getCatalogNo();
				quantity = ((QuoteItemDTO) itemDTO).getQuantity();
				status = ((QuoteItemDTO) itemDTO).getStatus();
				type = ((QuoteItemDTO) itemDTO).getType();
			}
			if (status.equalsIgnoreCase("CN")
					|| !"PRODUCT".equalsIgnoreCase(type)) {
				continue;
			}
			Double customerSpecialPrice = this.customerPriceBeanDao
					.getSpecialPrice(custNo, catalogNo, quantity, amountTotal);
			if (customerSpecialPrice != null) {
				Double amount = ArithUtil.mul(customerSpecialPrice, quantity.doubleValue(), 2);
				if (itemDTO instanceof OrderItemDTO) {
					((OrderItemDTO) itemDTO).setUnitPrice(new BigDecimal(customerSpecialPrice));
					((OrderItemDTO) itemDTO).setBasePrice(new BigDecimal(customerSpecialPrice));
					((OrderItemDTO) itemDTO).setAmount(new BigDecimal(amount));
				} else if (itemDTO instanceof QuoteItemDTO) {
					((QuoteItemDTO) itemDTO).setUnitPrice(new BigDecimal(customerSpecialPrice));
					((QuoteItemDTO) itemDTO).setBasePrice(new BigDecimal(customerSpecialPrice));
					((QuoteItemDTO) itemDTO).setAmount(new BigDecimal(amount));
				}
			}
		}
	}
	
	/**
	 * 计算折扣
	 * @param <T>
	 * @param itemList
	 * @param custNo
	 * @param promotionCode
	 * @param amountTotal
	 * @return
	 */
	private <T> String calProductItemDiscount(List<T> itemList,Integer custNo, String promotionCode, Double amountTotal){
		//数据检查***begin
		if(itemList == null || itemList.size() == 0){
			return "Items is  null!";
		}
		if(promotionCode == null || StringUtils.isEmpty(promotionCode)){
			return "PromotionCode is  null!";
		}
		Promotion promotion = promotionDao.findUniqueBy("prmtCode", promotionCode);
		if(promotion == null){
			return "Promotion is not exist!";
		}
		Double amountTotal2 = 0.0;
		String discType = promotion.getDiscType();
		String[] discTypes = discType==null?new String[]{"0","0","0","0"}:discType.split(";");
		if(discTypes.length == 1){
			discTypes = new String[]{discTypes[0],"0","0","0"};
		}
		if("1".equals(discTypes[3])) {
			amountTotal2 = this.getAmountTotal(itemList, custNo, promotion.getDiscCategoryType(),promotion.getDiscCategory());
		}
		//局部变量
		String result = null;
		BigDecimal discPercent = promotion.getDiscPercent();
		BigDecimal orderTotalMin1 = promotion.getOrderTotalMin1();
		BigDecimal orderTotalMin2 = promotion.getOrderTotalMin2();
		BigDecimal discOrderTotal = promotion.getDiscOrderTotal();
		BigDecimal discAmount = promotion.getDiscAmount();
		BigDecimal discCatePercent = promotion.getDiscCatePercent();
		BigDecimal discCateAmount = promotion.getDiscCateAmount();
		boolean flg = false;
		//设置itemList
		for (T itemDTO : itemList) {
			String status = null;
			String type = null;
			Double amount = null;
			String catalogNo = null;
			String catalogId = null;
			String catagoryNo = null;
			Double itemDiscount = 0.0;
			
			if (itemDTO instanceof OrderItemDTO) {
				status = ((OrderItemDTO) itemDTO).getStatus();
				type = ((OrderItemDTO) itemDTO).getType();
				amount = ((OrderItemDTO) itemDTO).getAmount().doubleValue();
				catalogNo = ((OrderItemDTO) itemDTO).getCatalogNo();
				catalogId = ((OrderItemDTO) itemDTO).getCatalogId();
				itemDiscount = ((OrderItemDTO) itemDTO).getDiscount().doubleValue();
			} else if (itemDTO instanceof QuoteItemDTO) {
				status = ((QuoteItemDTO) itemDTO).getStatus();
				type = ((QuoteItemDTO) itemDTO).getType();
				amount = ((QuoteItemDTO) itemDTO).getAmount().doubleValue();
				catalogNo = ((QuoteItemDTO) itemDTO).getCatalogNo();
				catalogId = ((QuoteItemDTO) itemDTO).getCatalogId();
				itemDiscount = ((QuoteItemDTO) itemDTO).getDiscount().doubleValue();
			}
			if(QuoteItemType.PRODUCT.value().equals(type)) {
				ProductInCategoryBean2 productInCategoryBean = this.productInCategoryBean2Dao.getBeanByCatalog(catalogNo, catalogId);
				catagoryNo = productInCategoryBean!=null?productInCategoryBean.getCategoryNo():null;
			} else if (QuoteItemType.SERVICE.value().equals(type)) {
				ServiceOfServcategoryBean serviceOfServcategoryBean = this.serviceOfServcategoryBeanDao.getBeanByCatalog(catalogNo, catalogId);
				catagoryNo = serviceOfServcategoryBean!=null?serviceOfServcategoryBean.getCategoryNo():null;
			}
			if (status.equalsIgnoreCase("CN")) {
				continue;
			}
			Double discount = 0.0;
			
			if (discTypes[0].equals("1") && orderTotalMin1 != null && discPercent != null
					&& orderTotalMin1.compareTo(new BigDecimal(amountTotal)) < 0){
				flg = true;
				Double disPercent2 =  discPercent.doubleValue();
				discount = ArithUtil.div(ArithUtil.mul(disPercent2.doubleValue(), amount), 100.0);
			}
			if(discTypes[1].equals("1")
					&& orderTotalMin2 != null
					&& orderTotalMin2.compareTo(new BigDecimal(amountTotal)) < 0 
					&& discAmount != null){
				flg = true;
				Double discount2 = ArithUtil.mul(amount, ArithUtil.div(discAmount.doubleValue(), amountTotal));
				discount = ArithUtil.add(discount, discount2);
			}
			if(discTypes[3].equals("1") 
					&& type.equalsIgnoreCase(promotion.getDiscCategoryType()) 
					&& catagoryNo!=null&&catagoryNo.equalsIgnoreCase(promotion.getDiscCategory())&&discOrderTotal.compareTo(new BigDecimal(amountTotal)) < 0 ){
				flg = true;
				Double discount3 = null;
				if(discCatePercent!=null) {
					Double discCatePercent2 = discCatePercent.doubleValue();
					discount3 = ArithUtil.div(ArithUtil.mul(discCatePercent2.doubleValue(), amount),100.0);
				} else if(discCateAmount != null&&amountTotal2!=null&&amountTotal2>0) {
					discount3 = (amount.doubleValue()*discCateAmount.doubleValue()/amountTotal2.doubleValue());
//					discount3 = ArithUtil.mul(amount, ArithUtil.div(discCateAmount.doubleValue(), amountTotal2), 2);
				}
				discount = discount+discount3;
			}
			discount = discount+itemDiscount;
			if(flg) {
				if (itemDTO instanceof OrderItemDTO) {
					((OrderItemDTO) itemDTO).setDiscount(new BigDecimal(discount).setScale(2,BigDecimal.ROUND_HALF_UP));
				} else if (itemDTO instanceof QuoteItemDTO) {
					((QuoteItemDTO) itemDTO).setDiscount(new BigDecimal(discount).setScale(2,BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		if(!flg) {
			result = "Failed to apply the promotion.";
			return result;
		}
		return result;
	}
	
	/**
	 * 由customer的discount给customer下的订单或报价单打折扣
	 */
	
	@SuppressWarnings("unused")
	public void calItemDiscountPice(ServiceItemPiceDTO itemPiceDTO,ServiceItemPiceDTO serviceItemDTO,Customer customer,Date orderQuoteDate){
		Double amount = null;
		double discount = 0.0;
		serviceItemDTO.setAmount(new BigDecimal(ArithUtil.sub(itemPiceDTO.getPrice().doubleValue(),Double.valueOf(serviceItemDTO.getQty()))));
		
			PeptideDTO peptideDTO = serviceItemDTO.getPeptideDTO();
			Integer seqLen = peptideDTO.getSeqLength();
			String purity = peptideDTO.getPurity();		
			if(serviceItemDTO.getServiceClsId() == 1 && seqLen <= 20){
				discount = getDiscountByPurity(discount, purity);
			}else if(serviceItemDTO.getServiceClsId() == 31 && seqLen <= 20){
				discount = getLibPepDiscountByPurity(discount, purity);
			}
			amount = serviceItemDTO.getAmount().doubleValue();
			serviceItemDTO.setDiscount(ArithUtil.mul(discount, amount, 2));
		
		discount = 0.0;
		if(customer.getSpecialDiscount()!=null) {
			discount = customer.getSpecialDiscount().doubleValue();
		}
		Date startDate = customer.getDiscEffFrom();
		Date endDate = customer.getDiscEffTo();
		if(!((endDate!=null&&orderQuoteDate.after(endDate))||(startDate!=null&&orderQuoteDate.before(startDate)))) {
			if(orderQuoteDate==null) {
				orderQuoteDate = new Date();
			}
			amount = serviceItemDTO.getAmount().doubleValue();
			BigDecimal oldDiscount = new BigDecimal(serviceItemDTO.getDiscount());
			if(oldDiscount != null){
				itemPiceDTO.setDiscount(ArithUtil.add(oldDiscount, new BigDecimal(ArithUtil.div(ArithUtil.mul(discount, amount), 100.0, 2))).doubleValue());
			}else{
				itemPiceDTO.setDiscount(ArithUtil.div(ArithUtil.mul(discount, amount), 100.0, 2));
			}
		}
	}
	
	/**
	 * 由customer的discount给customer下的所有订单或报价单打折扣
	 */
	public <T> void calItemDiscount(List<T> itemList,Customer customer,Date orderQuoteDate) {
		//peptide或者library peptide满足一定条件时打折扣
		for (T itemDTO : itemList) {
			Double amount = null;
			double discount = 0.0;
			if (itemDTO instanceof OrderItemDTO) {
				OrderItemDTO orderItemDTO = (OrderItemDTO)itemDTO;
				if(QuoteItemType.SERVICE.value().equals(orderItemDTO.getType()) && orderItemDTO.getPeptide() != null){
					PeptideDTO peptideDTO = orderItemDTO.getPeptide();
					Integer seqLen = peptideDTO.getSeqLength();
					String purity = peptideDTO.getPurity();
					if(orderItemDTO.getClsId() == 1 && seqLen <= 20){
						discount = getDiscountByPurity(discount, purity);
					}else if(orderItemDTO.getClsId() == 31 && seqLen <= 20){
						discount = getLibPepDiscountByPurity(discount, purity);
					}
					amount = orderItemDTO.getAmount().doubleValue();
					orderItemDTO.setDiscount(new BigDecimal(ArithUtil.mul(discount, amount, 2)));
				}
			} else if (itemDTO instanceof QuoteItemDTO) {
				QuoteItemDTO quoteItemDTO = (QuoteItemDTO)itemDTO;
				if(QuoteItemType.SERVICE.value().equals(quoteItemDTO.getType()) && quoteItemDTO.getPeptide() != null){
					PeptideDTO peptideDTO = quoteItemDTO.getPeptide();
					Integer seqLen = peptideDTO.getSeqLength();
					String purity = peptideDTO.getPurity();		
					if(quoteItemDTO.getClsId() == 1 && seqLen <= 20){
						discount = getDiscountByPurity(discount, purity);
					}else if(quoteItemDTO.getClsId() == 31 && seqLen <= 20){
						discount = getLibPepDiscountByPurity(discount, purity);
					}
					amount = quoteItemDTO.getAmount().doubleValue();
					quoteItemDTO.setDiscount(new BigDecimal(ArithUtil.mul(discount, amount, 2)));
				}
			}
		}
		Double discount = 0.0;
		if(customer.getSpecialDiscount()!=null) {
			discount = customer.getSpecialDiscount().doubleValue();
		}
		Date startDate = customer.getDiscEffFrom();
		Date endDate = customer.getDiscEffTo();
		if(orderQuoteDate==null) {
			orderQuoteDate = new Date();
		}
		if(!((endDate!=null&&orderQuoteDate.after(endDate))||(startDate!=null&&orderQuoteDate.before(startDate)))) {
			for (T itemDTO : itemList) {
				Double amount = null;
				if (itemDTO instanceof OrderItemDTO) {
					amount = ((OrderItemDTO) itemDTO).getAmount().doubleValue();
					BigDecimal oldDiscount = ((OrderItemDTO) itemDTO).getDiscount();
					if(oldDiscount != null){
						((OrderItemDTO) itemDTO).setDiscount(ArithUtil.add(oldDiscount, new BigDecimal(ArithUtil.div(ArithUtil.mul(discount, amount), 100.0, 2))));
					}else{
						((OrderItemDTO) itemDTO).setDiscount(new BigDecimal(ArithUtil.div(ArithUtil.mul(discount, amount), 100.0, 2)));
					}
				} else if (itemDTO instanceof QuoteItemDTO) {
					amount = ((QuoteItemDTO) itemDTO).getAmount().doubleValue();
					BigDecimal oldDiscount = ((QuoteItemDTO) itemDTO).getDiscount();
					if(oldDiscount != null){
						((QuoteItemDTO) itemDTO).setDiscount(ArithUtil.add(oldDiscount, new BigDecimal(ArithUtil.div(ArithUtil.mul(discount, amount), 100.0, 2))));
					}else{
						((QuoteItemDTO) itemDTO).setDiscount(new BigDecimal(ArithUtil.div(ArithUtil.mul(discount, amount), 100.0, 2)));
					}
				}
			}
		}
	
	}

	private double getDiscountByPurity(double discount, String purity) {
		if("Crude".equalsIgnoreCase(purity) || "Desalt".equalsIgnoreCase(purity)){
			discount = 0.22;
		}else if(">70%".equals(purity) || ">75%".equals(purity) || ">80%".equals(purity)){
			discount = 0.47;
		}else if(">85%".equals(purity)){
			discount = 0.50;
		}else if(">90%".equals(purity)){
			discount = 0.45;
		}else if(">95%".equals(purity) || ">98%".equals(purity)){
			discount = 0.43;
		}
		return discount;
	}
	
	private double getLibPepDiscountByPurity(double discount, String purity) {
		if("Crude".equalsIgnoreCase(purity) || "Desalt".equalsIgnoreCase(purity) || ">70%".equals(purity) || ">75%".equals(purity) || ">80%".equals(purity) || ">85%".equals(purity) || ">90%".equals(purity)){
			discount = 0.50;
		}else if(">95%".equals(purity) || ">98%".equals(purity)){
			discount = 0.40;
		}
		return discount;
	}

	/**
	 * 求出amount Total，unitprice必须要从数据库中取才准确。
	 * 
	 * @param <T>
	 * @param itemList
	 * @param custNo
	 * @return
	 */
	@SuppressWarnings("unused")
	private <T> Double getAmountTotal(List<T> itemList, Integer custNo) {
		Double amountTotal = null;
		for (int i = 0; i < itemList.size(); i++) {
			T itemDTO = itemList.get(i);
			String catalogNo = null;
			Integer quantity = null;
			String status = null;
			String type = null;
			Double price = null;
			if (itemDTO instanceof OrderItemDTO) {
				catalogNo = ((OrderItemDTO) itemDTO).getCatalogNo();
				quantity = ((OrderItemDTO) itemDTO).getQuantity();
				status = ((OrderItemDTO) itemDTO).getStatus();
				type = ((OrderItemDTO) itemDTO).getType();
				price = ((OrderItemDTO) itemDTO).getBasePrice().doubleValue();
			} else if (itemDTO instanceof QuoteItemDTO) {
				catalogNo = ((QuoteItemDTO) itemDTO).getCatalogNo();
				quantity = ((QuoteItemDTO) itemDTO).getQuantity();
				status = ((QuoteItemDTO) itemDTO).getStatus();
				type = ((QuoteItemDTO) itemDTO).getType();
				price = ((QuoteItemDTO) itemDTO).getBasePrice().doubleValue();
			}
			if (status.equalsIgnoreCase("CN")) {
				continue;
			}
			Double unitPrice = null;
			if ("PRODUCT".equalsIgnoreCase(type)&&this.productDao.getGiftProductByCatalogNo(catalogNo, "Y")==null) {
				unitPrice = customerPriceBeanDao
						.getUnitPrice(custNo, catalogNo);
			} else {
				unitPrice = price;
			}
			amountTotal = ArithUtil.add(amountTotal,
					ArithUtil.mul(unitPrice, quantity.doubleValue()));
		}
		return amountTotal;
	}
	
	/**
	 * 求出amount Total，unitprice必须要从数据库中取才准确。
	 * 
	 * @param <T>
	 * @param itemList
	 * @param custNo
	 * @return
	 */
	private <T> Double getAmountTotalByUnitPrice(List<T> itemList, Integer custNo) {
		Double amountTotal = null;
		for (int i = 0; i < itemList.size(); i++) {
			T itemDTO = itemList.get(i);
			String catalogNo = null;
			Integer quantity = null;
			String status = null;
			String type = null;
			Double price = null;
			if (itemDTO instanceof OrderItemDTO) {
				catalogNo = ((OrderItemDTO) itemDTO).getCatalogNo();
				quantity = ((OrderItemDTO) itemDTO).getQuantity();
				status = ((OrderItemDTO) itemDTO).getStatus();
				type = ((OrderItemDTO) itemDTO).getType();
				price = ((OrderItemDTO) itemDTO).getUnitPrice().doubleValue();
			} else if (itemDTO instanceof QuoteItemDTO) {
				catalogNo = ((QuoteItemDTO) itemDTO).getCatalogNo();
				quantity = ((QuoteItemDTO) itemDTO).getQuantity();
				status = ((QuoteItemDTO) itemDTO).getStatus();
				type = ((QuoteItemDTO) itemDTO).getType();
				price = ((QuoteItemDTO) itemDTO).getUnitPrice().doubleValue();
			}
			if (status.equalsIgnoreCase("CN")) {
				continue;
			}
			Double unitPrice = null;
			if ("PRODUCT".equalsIgnoreCase(type)&&this.productDao.getGiftProductByCatalogNo(catalogNo, "Y")==null) {
				unitPrice = customerPriceBeanDao
						.getUnitPrice(custNo, catalogNo);
			} else {
				unitPrice = price;
			}
			amountTotal = ArithUtil.add(amountTotal,
					ArithUtil.mul(unitPrice, quantity.doubleValue()));
		}
		return amountTotal;
	}
	
	/**
	 * 求出某种type下的amount Total，unitprice必须要从数据库中取才准确。
	 * 
	 * @param <T>
	 * @param itemList
	 * @param custNo
	 * @return
	 */
	private <T> Double getAmountTotal(List<T> itemList, Integer custNo, String baseType,String catelogNo) {
		Double amountTotal = null;
		for (int i = 0; i < itemList.size(); i++) {
			T itemDTO = itemList.get(i);
			String catalogNo = null;
			Integer quantity = null;
			String status = null;
			String type = null;
			Double price = null;
			String catagoryNo = null;
			String catalogId = null;
			if (itemDTO instanceof OrderItemDTO) {
				catalogNo = ((OrderItemDTO) itemDTO).getCatalogNo();
				quantity = ((OrderItemDTO) itemDTO).getQuantity();
				status = ((OrderItemDTO) itemDTO).getStatus();
				type = ((OrderItemDTO) itemDTO).getType();
				price = ((OrderItemDTO) itemDTO).getUnitPrice().doubleValue();
				catalogId = ((OrderItemDTO) itemDTO).getCatalogId();
			} else if (itemDTO instanceof QuoteItemDTO) {
				catalogNo = ((QuoteItemDTO) itemDTO).getCatalogNo();
				quantity = ((QuoteItemDTO) itemDTO).getQuantity();
				status = ((QuoteItemDTO) itemDTO).getStatus();
				type = ((QuoteItemDTO) itemDTO).getType();
				price = ((QuoteItemDTO) itemDTO).getUnitPrice().doubleValue();
				catalogId = ((QuoteItemDTO) itemDTO).getCatalogId();
			}
			if(QuoteItemType.PRODUCT.value().equals(type)) {
				ProductInCategoryBean2 productInCategoryBean = this.productInCategoryBean2Dao.getBeanByCatalog(catalogNo, catalogId);
				catagoryNo = productInCategoryBean!=null?productInCategoryBean.getCategoryNo():null;
			} else if (QuoteItemType.SERVICE.value().equals(type)) {
				ServiceOfServcategoryBean serviceOfServcategoryBean = this.serviceOfServcategoryBeanDao.getBeanByCatalog(catalogNo, catalogId);
				catagoryNo = serviceOfServcategoryBean!=null?serviceOfServcategoryBean.getCategoryNo():null;
			}
			if (status.equalsIgnoreCase("CN") || !baseType.equalsIgnoreCase(type)||!catelogNo.equalsIgnoreCase(catagoryNo)) {
				continue;
			}
			Double unitPrice = null;
			if ("PRODUCT".equalsIgnoreCase(type)) {
				unitPrice = customerPriceBeanDao
						.getUnitPrice(custNo, catalogNo);
			} else {
				unitPrice = price;
			}
			amountTotal = ArithUtil.add(amountTotal,
					ArithUtil.mul(unitPrice, quantity.doubleValue()));
		}
		return amountTotal;
	}
	
	@Transactional(readOnly = true)
	private <T> void calProductItemTax(List<T> itemList, String country, String state, Integer custNo) {
		if (itemList != null && itemList.size() > 0) {
			Double taxRate = this.taxRateDao.getCountryStateRate(country, state);
			if("JP".equalsIgnoreCase(country)){
				taxRate = 0.05;
			}
			for (T itemDTO : itemList) {
				Double amount = null;
				String taxStatus = null;
				String catalogNo;
				double temptaxRate = taxRate == null ? 0 :taxRate.doubleValue();
				if (itemDTO instanceof OrderItemDTO) {
					amount = ((OrderItemDTO) itemDTO).getAmount().doubleValue();
					//taxStatus = ((OrderItemDTO) itemDTO).getTaxStatus();
					catalogNo = ((OrderItemDTO) itemDTO).getCatalogNo();
					String pdtSrvTaxable;
					if("PRODUCT".equalsIgnoreCase(((OrderItemDTO) itemDTO).getType())){
						Product product = productDao.findUniqueBy("catalogNo", catalogNo);
						pdtSrvTaxable = product.getTaxable();
					}else{
						com.genscript.gsscm.serv.entity.Service service = serviceDao.findUniqueBy("catalogNo", catalogNo);
						pdtSrvTaxable = service.getTaxable();
					}
					Customer customer = customerDao.findUniqueBy("custNo", custNo);
					String custTaxStatus = customer.getTaxExemptFlag();
					String taxId = customer.getTaxId();
					if ("Y".equalsIgnoreCase(custTaxStatus)
							&& !StringUtils.isEmpty(taxId)) {
						taxStatus = "N";
					}else{
						taxStatus = pdtSrvTaxable;
					}
				} else if (itemDTO instanceof QuoteItemDTO) {
					amount = ((QuoteItemDTO) itemDTO).getAmount().doubleValue();
					//taxStatus = ((QuoteItemDTO) itemDTO).getTaxStatus();
					catalogNo = ((QuoteItemDTO) itemDTO).getCatalogNo();
					String pdtSrvTaxable;
					if("PRODUCT".equalsIgnoreCase(((QuoteItemDTO) itemDTO).getType())){
						Product product = productDao.findUniqueBy("catalogNo", catalogNo);
						pdtSrvTaxable = product.getTaxable();
					}else{
						com.genscript.gsscm.serv.entity.Service service = serviceDao.findUniqueBy("catalogNo", catalogNo);
						pdtSrvTaxable = service.getTaxable();
					}
					Customer customer = customerDao.findUniqueBy("custNo", custNo);
					String custTaxStatus = customer.getTaxExemptFlag();
					String taxId = customer.getTaxId();
					if ("Y".equalsIgnoreCase(custTaxStatus)
							&& !StringUtils.isEmpty(taxId)) {
						taxStatus = "N";
					}else{
						taxStatus = pdtSrvTaxable;
					}
				}
				if (temptaxRate == 0 || "N".equalsIgnoreCase(taxStatus)) {
					temptaxRate = 0.0;
				}
				Double tax = ArithUtil.mul(amount, temptaxRate, 2);
				if (itemDTO instanceof OrderItemDTO) {
					((OrderItemDTO) itemDTO).setTax(new BigDecimal(tax));
				} else if (itemDTO instanceof QuoteItemDTO) {
					((QuoteItemDTO) itemDTO).setTax(new BigDecimal(tax));
				}
			}
		}
	}
	
	public BigDecimal changeTotalCurrency(BigDecimal orderTotal, final String fromCurrency, final String toCurrency){
		Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency, toCurrency, new Date());
		if(rate == null){
			throw new BussinessException(BussinessException.EXCH_RATE_NOT_FOUNTD);
		}
		int scale = 2;
		if (toCurrency.equals("JPY")) {
			scale = 0;
		}
		Double total = ArithUtil.mul(orderTotal.doubleValue(), rate, scale);
		return new BigDecimal(total);
	}
	
	/**
	 * Change single current currency to another currency
	 * 
	 * @param orderNo
	 * @param fromCurrency
	 * @param toCurrency
	 * @param productItemDTO
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> void changeItemCurrency(List<T> itemList, final String fromCurrency, final String toCurrency) {
		if(itemList == null || itemList.size() == 0 || StringUtils.isEmpty(fromCurrency) || StringUtils.isEmpty(toCurrency)){
			return;
		}
		Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency, toCurrency, new Date());
		if(rate == null){
			throw new BussinessException(BussinessException.EXCH_RATE_NOT_FOUNTD);
		}
		int scale = 2;
		if (toCurrency.equals("JPY")) {
			scale = 0;
		}
		for(T itemDTO : itemList){
			Double amount = null;
			Double discount = null;
			Double tax = null;
			Double unitPrice = null;
			if (itemDTO instanceof OrderItemDTO) {
				amount = ((OrderItemDTO) itemDTO).getAmount().doubleValue();
				discount = ((OrderItemDTO) itemDTO).getDiscount().doubleValue();
				tax = ((OrderItemDTO) itemDTO).getTax().doubleValue();
				if (((OrderItemDTO) itemDTO).getBasePrice() == null 
						|| ((OrderItemDTO) itemDTO).getBasePrice().doubleValue() <= 0) {
					((OrderItemDTO) itemDTO).setBasePrice(((OrderItemDTO) itemDTO).getUnitPrice());
					unitPrice = ((OrderItemDTO) itemDTO).getUnitPrice().doubleValue();
				} else {
					unitPrice = ((OrderItemDTO) itemDTO).getBasePrice().doubleValue();
				}
			} else if (itemDTO instanceof QuoteItemDTO) {
				amount = ((QuoteItemDTO) itemDTO).getAmount().doubleValue();
				discount = ((QuoteItemDTO) itemDTO).getDiscount().doubleValue();
				tax = ((QuoteItemDTO) itemDTO).getTax().doubleValue();
				if (((QuoteItemDTO) itemDTO).getBasePrice() == null 
						|| ((QuoteItemDTO) itemDTO).getBasePrice().doubleValue() <= 0) {
					((QuoteItemDTO) itemDTO).setBasePrice(((QuoteItemDTO) itemDTO).getUnitPrice());
					unitPrice = ((QuoteItemDTO) itemDTO).getUnitPrice().doubleValue();
				} else {
					unitPrice = ((QuoteItemDTO) itemDTO).getBasePrice().doubleValue();
				}
			}
			amount = ArithUtil.mul(amount, rate, scale);
			discount = ArithUtil.mul(discount, rate, scale);
			tax = ArithUtil.mul(tax, rate, scale);
			unitPrice = ArithUtil.mul(unitPrice, rate, scale);
			if (itemDTO instanceof OrderItemDTO) {
				((OrderItemDTO) itemDTO).setAmount(new BigDecimal(amount));
				((OrderItemDTO) itemDTO).setDiscount(new BigDecimal(discount));
				((OrderItemDTO) itemDTO).setTax(new BigDecimal(tax));
				((OrderItemDTO) itemDTO).setUnitPrice(new BigDecimal(unitPrice));
			} else if (itemDTO instanceof QuoteItemDTO) {
				((QuoteItemDTO) itemDTO).setAmount(new BigDecimal(amount));
				((QuoteItemDTO) itemDTO).setDiscount(new BigDecimal(discount));
				((QuoteItemDTO) itemDTO).setTax(new BigDecimal(tax));
				((QuoteItemDTO) itemDTO).setUnitPrice(new BigDecimal(unitPrice));
			}
		}
	}
	
	@Transactional(readOnly = true)
	public TotalDTO getMyTotals(final Double amount, final Double subToal, final Double discount, final Double tax, 
			final Double customerCharge, String handlingFee, final String toCurrency, final BigDecimal payments, 
			final Double couponValue, boolean isJPCountry) {
		//精度
		int scale = 2;
		if (CurrencyType.JPY.value().equalsIgnoreCase(toCurrency)) {
			scale = 0;
		}
		TotalDTO orderTotalDTO = new TotalDTO();
		//默认值初始化
		BigDecimal zero = new BigDecimal(0).setScale(scale, BigDecimal.ROUND_HALF_UP);
//		BigDecimal amountBD = (amount != null) ? new BigDecimal(amount) : zero;
		BigDecimal subTotalBD = (subToal != null) ? new BigDecimal(subToal) : zero;
		BigDecimal discountBD = (discount != null) ? new BigDecimal(discount): zero;
		BigDecimal taxBD = (tax != null) ? new BigDecimal(tax) : zero;
		BigDecimal customerChargeBD = (customerCharge != null) ? new BigDecimal(customerCharge) : zero;
		BigDecimal couponValueBD = (couponValue != null) ? new BigDecimal(couponValue) : zero;
		BigDecimal handlingFeeBD = (handlingFee != null) ? new BigDecimal(handlingFee) : zero;
		//货币符号
		orderTotalDTO.setSymbol(currencyDao.getCurrencySymbol(toCurrency));
		orderTotalDTO.setCurrency(toCurrency);
		//
		orderTotalDTO.setQuorderSubtotal(subTotalBD.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderSubtotalShow(subTotalBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		orderTotalDTO.setQuorderDiscount(discountBD.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderDiscountShow(discountBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		orderTotalDTO.setQuorderTax(taxBD.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderTaxShow(taxBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		BigDecimal orderTotalBD = subTotalBD.subtract(discountBD).subtract(couponValueBD).add(customerChargeBD).add(taxBD);
		orderTotalDTO.setQuorderShipAmt(customerChargeBD.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderShipAmtShow(customerChargeBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		orderTotalDTO.setQuorderTotal(orderTotalBD.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderTotalShow(orderTotalBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		orderTotalDTO.setQuorderTotalPayments(payments.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderTotalPaymentsShow(payments.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		orderTotalDTO.setQuorderTotalCouponValue(couponValueBD.setScale(scale, BigDecimal.ROUND_HALF_UP));
		orderTotalDTO.setQuorderTotalCouponValueShow(couponValueBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		orderTotalDTO.setHandlingFee(handlingFeeBD.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
		return orderTotalDTO;
	}
	
	/**
	 * 查询非CN状态的QuoteItem的Cost的和
	 * @author Zhang Yong
	 * add date 2011-11-28
	 * @param sessQuoteNo
	 * @return
	 */
	public String getQuoteTotalCost (String sessQuoteNo) {
		BigDecimal totalCost = new BigDecimal(0);
		@SuppressWarnings("unchecked")
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
			.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (itemMap != null && itemMap.size() > 0) {
			for (String key : itemMap.keySet()) {
				QuoteItemDTO quoteItemDTO = itemMap.get(key);
				if (!QuoteItemStatusType.CN.equals(quoteItemDTO.getStatus()) && quoteItemDTO.getCost() != null) {
					totalCost = totalCost.add(quoteItemDTO.getCost());
				}
			}
		}
		return totalCost.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	/**
	 * 获得 total
	 * @param amount
	 * @param discount
	 * @param tax
	 * @param customerCharge
	 * @param toCurrency
	 * @return
	 */
	public BigDecimal getTotal(final Double amount, final Double discount, final Double tax, 
			final Double customerCharge, final String toCurrency){
		//精度
		int scale = 2;
		if (toCurrency.equals("JPY")) {
			scale = 0;
		}
		//默认值初始化
		BigDecimal zero = new BigDecimal(0).setScale(scale, BigDecimal.ROUND_HALF_UP);
		BigDecimal amountBD = (amount != null) ? new BigDecimal(amount) : zero;
		BigDecimal discountBD = (discount != null) ? new BigDecimal(discount): zero;
		BigDecimal taxBD = (tax != null) ? new BigDecimal(tax) : zero;
		BigDecimal customerChargeBD = (customerCharge != null) ? new BigDecimal(customerCharge) : zero;
		
		BigDecimal orderTotalBD = amountBD.subtract(discountBD).add(customerChargeBD).add(taxBD);
		return orderTotalBD;
	}
	
	public boolean isGiftItem(String catalogNo, String type){
		if("PRODUCT".equalsIgnoreCase(type)){
			return this.productDao.getGiftProductByCatalogNo(catalogNo, "Y")!=null;
		}else{
			return this.serviceDao.getGiftServiceByCatalogNo(catalogNo, "Y")!=null;
		}
	}
	
	/**
	 * 当前item是否礼品
	 */
	public <T> boolean isGifItem(T itemDTO,String sessOrderNo) {
		if (itemDTO instanceof OrderItemDTO) {
			OrderItemDTO orderItemDTO = (OrderItemDTO)itemDTO;
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
			if(order.getOrderPromotion()!=null) {
				Promotion promotion = this.promotionDao.findUniqueBy("prmtCode", order.getOrderPromotion().getPrmtCode());
				if(promotion!=null) {
					String[] discType = promotion.getDiscType().split(";");
					if(discType.length==4&&"1".equals(discType[2])) {
						String catalogNo = promotion.getDiscProd();
						if(catalogNo.equals(orderItemDTO.getCatalogNo())) {
							return true;
						}
					}
				}
			}
		} else if(itemDTO instanceof QuoteItemDTO) {
			QuoteItemDTO quoteItemDTO = (QuoteItemDTO)itemDTO;
			QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessOrderNo);
			if(quote.getQuotePromotion()!=null) {
				Promotion promotion = this.promotionDao.findUniqueBy("prmtCode", quote.getQuotePromotion().getPrmtCode());
				if(promotion!=null) {
					String[] discType = promotion.getDiscType().split(";");
					if(discType.length==4&&"1".equals(discType[2])) {
						String catalogNo = promotion.getDiscProd();
						if(catalogNo.equals(quoteItemDTO.getCatalogNo())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 通过accessionNo查询集合
	 * @author Zhang Yong
	 * modify date 2011-12-02
	 * @param accessionNo
	 * @return
	 */
	public List<Refseq2orfpriceDTO> searchOrfClone (String accessionNo) {
		List<Refseq2orfpriceDTO> refseq2orfpriceDTOList = new ArrayList<Refseq2orfpriceDTO>();
		if (StringUtils.isBlank(accessionNo)) {
			return refseq2orfpriceDTOList;
		}
		Refseq2orfprice refseq2orfprice = refseq2orfpriceDao.getByAccessionNo(accessionNo.trim());
		if (refseq2orfprice != null) {
			Refseq2orfpriceDTO fefseq2orfprice1 = dozer.map(refseq2orfprice, Refseq2orfpriceDTO.class);
			fefseq2orfprice1.setSequenceType(FullLength);
			refseq2orfpriceDTOList.add(fefseq2orfprice1);
			Refseq2orfpriceDTO fefseq2orfprice2 = dozer.map(refseq2orfprice, Refseq2orfpriceDTO.class);
			fefseq2orfprice2.setSequenceType(ORFSequence);
			refseq2orfpriceDTOList.add(fefseq2orfprice2);
		}
		return refseq2orfpriceDTOList;
	}
	
	/**
	 * 查询Vector信息，主要针对ORF Clone服务
	 * @author Zhang Yong
	 * add date 2011-12-01
	 * @return
	 */
	public List<VectorInfoListDTO> getVectorList () {
		List<VectorInfoListDTO> vInfoDTOList = new ArrayList<VectorInfoListDTO>();
		List<VectorInfoList> vInfoList = vectorInfoListDao.getAll();
		if (vInfoList != null && !vInfoList.isEmpty()) {
			for (VectorInfoList vInfo : vInfoList) {
				VectorInfoListDTO vInfoDTO = dozer.map(vInfo, VectorInfoListDTO.class);
				vInfoDTO.setVectorNameShow(vInfo.getVectorName()+","+vInfo.getResistance()+","+vInfo.getVectorLen());
				if (StringUtils.isNotBlank(vInfo.getCloningSites())) {
					//过滤重复元素
					List<VectorInfoListDTO> viList = new ArrayList<VectorInfoListDTO>();
					viList.add(vInfoDTO);
					vInfoDTO.setCloningSites(StringUtil.listToString(getOrfCloneSiteList(vInfoDTO.getVectorName(), viList), ", "));
				}	
				vInfoDTOList.add(vInfoDTO);
			}
		}
		//获取VectorName为Other时的clone site
		List<String> orfCloneSiteList = getOrfCloneSiteList(Other, vInfoDTOList);
		VectorInfoListDTO otherVector = new VectorInfoListDTO();
		otherVector.setVectorNameShow(Other);
		otherVector.setVectorName(Other);
		otherVector.setCloningSites(StringUtil.listToString(orfCloneSiteList, ", "));
		vInfoDTOList.add(otherVector);
		return vInfoDTOList;
	}

	/**
	 * 获取cloneSite
	 * @author Zhang Yong
	 * add date 2011-12-01
	 * @param itemDTO
	 * @param vectorList
	 * @return
	 */
	public List<String> getOrfCloneSite (List<VectorInfoListDTO> vectorList, OrderServiceDTO serviceDTO) {
		List<String> orfCloneSiteList = new ArrayList<String>();
		if (!vectorList.isEmpty()) {
			String vectorName = pUC57;
			if (serviceDTO != null && serviceDTO.getCustCloning() != null && StringUtils.isNotBlank(serviceDTO.getCustCloning().getTgtVector())) {
				vectorName = serviceDTO.getCustCloning().getTgtVector();
				if (vectorName.startsWith(Other)) {
					vectorName = Other;
				}
			}
			orfCloneSiteList = getOrfCloneSiteList(vectorName, vectorList);
		}
		return orfCloneSiteList;
	}
	
	/**
	 * 获取cloneSite
	 * @author Zhang Yong
	 * add date 2011-12-01
	 * @param vectorName
	 * @param vectorList
	 * @return
	 */
	private List<String> getOrfCloneSiteList (String vectorName, List<VectorInfoListDTO> vectorList) {
		List<String> orfCloneSiteList = new ArrayList<String>();
		if (vectorList == null || vectorList.isEmpty()) {
			return orfCloneSiteList;
		}
		List<String> _orfCloneSiteList = new ArrayList<String>();
		boolean other = (StringUtils.isNotBlank(vectorName) && vectorName.startsWith(Other))?true:false;
		for (VectorInfoListDTO vInfo : vectorList) {
			if (!other) {
				if (StringUtils.isNotBlank(vectorName) && vectorName.equals(vInfo.getVectorName())) {
					_orfCloneSiteList.add(vInfo.getCloningSites());
					break;
				}
			} else {
				_orfCloneSiteList.add(vInfo.getCloningSites());
			}
		}
		for (String _cloneSite : _orfCloneSiteList) {
			if (StringUtils.isNotBlank(_cloneSite)) {
				List<String> csList = Arrays.asList(_cloneSite.split(", "));
				orfCloneSiteList.addAll(csList);
			}
		}
		orfCloneSiteList = new ArrayList<String>(new HashSet<String>(orfCloneSiteList));
		Collections.sort(orfCloneSiteList);
		return orfCloneSiteList;
	}
	
	/**
	 * 计算Oligo价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param <T>
	 * @param itemDTO
	 */
	public <T> void getOligoPriceAndCost (String sessNo, T itemDTO) {
		OrderOligoDTO oligo = null;
		OrderItemDTO orderItem = null;
		QuoteItemDTO quoteItem = null;
		String currency = CurrencyType.USD.value();
		BigDecimal qty = null;
		if (itemDTO instanceof OrderItemDTO) {
			orderItem = (OrderItemDTO) itemDTO;
			oligo = orderItem.getOligo();
			if (StringUtils.isNotBlank(sessNo)) {
				OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessNo);
				if (orderMain != null && StringUtils.isNotBlank(orderMain.getOrderCurrency())) {
					currency = orderMain.getOrderCurrency();
				}
			}
			qty = new BigDecimal(orderItem.getQuantity());
		} else if (itemDTO instanceof QuoteItemDTO) {
			quoteItem = ((QuoteItemDTO) itemDTO);
			oligo = quoteItem.getOligo();
			if (StringUtils.isNotBlank(sessNo)) {
				QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessNo);
				if (quoteMain != null && StringUtils.isNotBlank(quoteMain.getQuoteCurrency())) {
					currency = quoteMain.getQuoteCurrency();
				}
			}
			qty = new BigDecimal(quoteItem.getQuantity());
		}
		if (oligo == null) {
			return;
		}
		//OthermodificationFlag3、OthermodificationFlag5、InterOtherModificationFlag任何一个值为"Y"时，价格为TBD
		//DaPercent、DcPercent、DgPercent、DtPercent任何一个有值时，价格为TBD
		int point = CurrencyType.JPY.value().equals(currency)?0:2;
		BigDecimal zero = new BigDecimal(0).setScale(point, BigDecimal.ROUND_HALF_UP);
		if (Y.equals(oligo.getOthermodificationFlag3()) || Y.equals(oligo.getOthermodificationFlag5()) || Y.equals(oligo.getInterOtherModificationFlag())
				|| oligo.getDaPercent() != null || oligo.getDcPercent() != null || oligo.getDgPercent() != null || oligo.getDtPercent() != null) {
			if (orderItem != null) {
				orderItem.setTbdFlag(tbd_1);
				orderItem.setUnitPrice(zero);
				orderItem.setAmount(zero);
			} else if (quoteItem != null) {
				quoteItem.setTbdFlag(tbd_1);
				quoteItem.setUnitPrice(zero);
				quoteItem.setAmount(zero);
			}
			return;
		}
		String modificationIds = getModificationIds(oligo);
		String chimericBasesCodes = getChimericBasesCodes(oligo);
		String mixedBasesCodesCodes = getMixedBasesCodesCodes(oligo);
		//获取美元的Price和Cost
		Float[] oligoPriceAndCost = getOligoPrice(oligo.getBackbone(), oligo.getAliquotingInto(), oligo.getPurification(), 
				oligo.getSynthesisScales(), oligo.getSeqLength(), modificationIds, chimericBasesCodes, 
				mixedBasesCodesCodes);
		if (oligoPriceAndCost == null) {
			if (orderItem != null) {
				orderItem.setTbdFlag(tbd_0);
				orderItem.setUnitPrice(zero);
				orderItem.setAmount(zero);
			} else if (quoteItem != null) {
				quoteItem.setTbdFlag(tbd_0);
				quoteItem.setUnitPrice(zero);
				quoteItem.setAmount(zero);
			}
			return;
		}
		Double rate = publicService.getRateByBaseCurrencyToCurrency(currency, new Date());
		if (oligoPriceAndCost.length < 2 || (oligoPriceAndCost[0] == null && oligoPriceAndCost[1] == null)) {
			if (orderItem != null) {
				orderItem.setTbdFlag(tbd_0);
				if (oligoPriceAndCost[0] == null) {
					orderItem.setBasePrice(zero);
				} else if (oligoPriceAndCost[0].floatValue() <0) {
					orderItem.setTbdFlag(tbd_1);
					orderItem.setBasePrice(zero);
				} else {
					orderItem.setBasePrice(new BigDecimal(oligoPriceAndCost[0]).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				orderItem.setCost(zero);
				orderItem.setUnitPrice(orderItem.getBasePrice().multiply(new BigDecimal(rate)).setScale(point, BigDecimal.ROUND_HALF_UP));
				orderItem.setAmount(orderItem.getBasePrice().multiply(new BigDecimal(rate)).multiply(qty).setScale(point, BigDecimal.ROUND_HALF_UP));
			} else if (quoteItem != null) {
				quoteItem.setTbdFlag(tbd_0);
				if (oligoPriceAndCost[0] == null) {
					quoteItem.setBasePrice(zero);
				} else if (oligoPriceAndCost[0].floatValue() <0) {
					quoteItem.setTbdFlag(tbd_1);
					quoteItem.setBasePrice(zero);
				} else {
					quoteItem.setBasePrice(new BigDecimal(oligoPriceAndCost[0]).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				quoteItem.setCost(zero);
				quoteItem.setUnitPrice(quoteItem.getBasePrice().multiply(new BigDecimal(rate)).setScale(point, BigDecimal.ROUND_HALF_UP));
				quoteItem.setAmount(quoteItem.getBasePrice().multiply(new BigDecimal(rate)).multiply(qty).setScale(point, BigDecimal.ROUND_HALF_UP));
			}
			return;
		}
		if (oligoPriceAndCost[0].floatValue() <0 || oligoPriceAndCost[1].floatValue() <0) {
			if (orderItem != null) {
				orderItem.setTbdFlag(tbd_1);
				orderItem.setBasePrice(zero);
				orderItem.setCost(zero);
				orderItem.setUnitPrice(zero);
				orderItem.setAmount(zero);
			} else if (quoteItem != null) {
				quoteItem.setTbdFlag(tbd_1);
				quoteItem.setBasePrice(zero);
				quoteItem.setCost(zero);
				quoteItem.setUnitPrice(zero);
				quoteItem.setAmount(zero);
			}
			return;
		}
		if (orderItem != null) {
			orderItem.setTbdFlag(tbd_0);
			orderItem.setBasePrice(new BigDecimal(oligoPriceAndCost[0]).setScale(2, BigDecimal.ROUND_HALF_UP));
			orderItem.setCost(new BigDecimal(oligoPriceAndCost[1]).setScale(2, BigDecimal.ROUND_HALF_UP));
			orderItem.setUnitPrice(orderItem.getBasePrice().multiply(new BigDecimal(rate)).setScale(point, BigDecimal.ROUND_HALF_UP));
			orderItem.setAmount(orderItem.getBasePrice().multiply(new BigDecimal(rate)).multiply(qty).setScale(point, BigDecimal.ROUND_HALF_UP));
		} else if (quoteItem != null) {
			quoteItem.setTbdFlag(tbd_0);
			quoteItem.setBasePrice(new BigDecimal(oligoPriceAndCost[0]).setScale(2, BigDecimal.ROUND_HALF_UP));
			quoteItem.setCost(new BigDecimal(oligoPriceAndCost[1]).setScale(2, BigDecimal.ROUND_HALF_UP));
			quoteItem.setUnitPrice(quoteItem.getBasePrice().multiply(new BigDecimal(rate)).setScale(point, BigDecimal.ROUND_HALF_UP));
			quoteItem.setAmount(quoteItem.getBasePrice().multiply(new BigDecimal(rate)).multiply(qty).setScale(point, BigDecimal.ROUND_HALF_UP));
		}
	}
	
	//获取oligo ModificationIds，返回值内容类似于(43,43,44)
	private String getModificationIds (OrderOligoDTO oligo) {
		String modificationIds = "";
		String modifiIds = "";
		if (StringUtils.isNotBlank(oligo.getModification5())) {
			modifiIds += oligo.getModification5();
		}
		if (StringUtils.isNotBlank(oligo.getModification3())) {
			modifiIds += "," + oligo.getModification3();
		}
		if (StringUtils.isNotBlank(oligo.getInternalModification())) {
			modifiIds += "," + oligo.getInternalModification();
		}
		if (StringUtils.isBlank(modifiIds)) {
			return modificationIds;
		}
		String[] modificationIdArr = modifiIds.split(",");
		Map<String, String> modIdMap = new HashMap<String, String>();
		for (String modId : modificationIdArr) {
			if (StringUtils.isNotBlank(modId) && !modIdMap.containsKey(modId)) {
				modIdMap.put(modId, modId);
				modificationIds += modId+",";
			}
		}
		if (StringUtils.isNotBlank(modificationIds) && modificationIds.endsWith(",")) {
			modificationIds = modificationIds.substring(0, modificationIds.length()-1);
		}
		return modificationIds;
	}
	
	//获取oligo ChimericBasesCodes，返回值内容类似于('/[dC]/','/[dC]/')
	private String getChimericBasesCodes (OrderOligoDTO oligo) {
		String chimericBasesCodes = "";
		if (StringUtils.isBlank(oligo.getChimericBases())) {
			return chimericBasesCodes;
		}
		String[] cmbCodesArr = oligo.getChimericBases().split(",");
		Map<String, String> cmbCodesMap = new HashMap<String, String>();
		for (String cmbCode : cmbCodesArr) {
			if (StringUtils.isNotBlank(cmbCode) && !cmbCodesMap.containsKey(cmbCode)) {
				cmbCodesMap.put(cmbCode, cmbCode);
				chimericBasesCodes += "'"+cmbCode+"',";
			}
		}
		if (StringUtils.isNotBlank(chimericBasesCodes) && chimericBasesCodes.endsWith(",")) {
			chimericBasesCodes = chimericBasesCodes.substring(0, chimericBasesCodes.length()-1);
		}
		return chimericBasesCodes;
	}
	
	//获取oligo mixedBasesCodes，返回值内容类似于('/|D|/')
	private String getMixedBasesCodesCodes (OrderOligoDTO oligo) {
		String mixedBasesCodes = "";
		if (StringUtils.isBlank(oligo.getStandardMixedMases())) {
			return mixedBasesCodes;
		}
		String[] mixedBasesCodesArr = oligo.getStandardMixedMases().split(",");
		Map<String, String> mixedBasesCodesMap = new HashMap<String, String>();
		for (String mbCode : mixedBasesCodesArr) {
			if (StringUtils.isNotBlank(mbCode) && !mixedBasesCodesMap.containsKey(mbCode)) {
				mixedBasesCodesMap.put(mbCode, mbCode);
				mixedBasesCodes += "'"+mbCode+"',";
			}
		}
		if (StringUtils.isNotBlank(mixedBasesCodes) && mixedBasesCodes.endsWith(",")) {
			mixedBasesCodes = mixedBasesCodes.substring(0, mixedBasesCodes.length()-1);
		}
		return mixedBasesCodes;
	}
	
	/**
	 * 计算oligo价格，给外部的接口
	 * @author Zhang Yong
	 * add date 2011-12-17
	 * @param orderServiceItemPiceDTO
	 * @return
	 */
	public ServiceItemPiceDTO getOligoPrice(final ServiceItemPiceDTO orderServiceItemPiceDTO) {
		ServiceItemPiceDTO result = new ServiceItemPiceDTO();
		if (orderServiceItemPiceDTO == null || orderServiceItemPiceDTO.getOligoDTO() == null) {
			OrderOligoDTO oligo = orderServiceItemPiceDTO.getOligoDTO();
			String modificationIds = getModificationIds(oligo);
			String chimericBasesCodes = getChimericBasesCodes(oligo);
			String mixedBasesCodesCodes = getMixedBasesCodesCodes(oligo);
	        Float[] returnPrice = getOligoPrice(oligo.getBackbone(), oligo.getAliquotingInto(), oligo.getPurification(), oligo.getSynthesisScales(),
	        		oligo.getSeqLength(), modificationIds, chimericBasesCodes, mixedBasesCodesCodes);
	        if (returnPrice != null) {
	        	result.setPrice(returnPrice[0]==null?null:(new BigDecimal(returnPrice[0]).setScale(2, BigDecimal.ROUND_HALF_UP)));
	        	result.setCost((returnPrice.length<2 || returnPrice[1]==null)?null:(new BigDecimal(returnPrice[1]).setScale(2, BigDecimal.ROUND_HALF_UP)));
	        }
		}
        return result;
	}
	
	/**
	 * @计算Oligo价格
	 * @author Zhang Yong
	 * add date 2011-12-12
	 * @param backboneName
	 * @param purification
	 * @param synthesisScales
	 * @param seqLength
	 * @param modificationIds 内容类似于(43,43,44)
	 * @param chimericBasesCodes 内容类似于('/[dC]/','/[dC]/')
	 * @param mixedBasesCodesCodes 内容类似于('/|D|/')
	 * @return
	 */
	public Float[] getOligoPrice (String backboneName, Integer aliquotingInto, String purification, String synthesisScales, 
			Integer seqLength, String modificationIds, String chimericBasesCodes, String mixedBasesCodesCodes) {
		Float[] oligoPriceAndCost = new Float[2];
		oligoPriceAndCost[0] = 0f;
		oligoPriceAndCost[1] = 0f;
		if (StringUtils.isBlank(synthesisScales) || StringUtils.isBlank(backboneName)) {
			return oligoPriceAndCost;
		}
		String[] scale = synthesisScales.split(" ");
		if (scale.length !=2 || scale[1].length() <2) {
			return oligoPriceAndCost;
		}
		//重组后的newScale值类似于newScale=200nm
		String newScale = scale[0]+scale[1].substring(0, 2);
		//查询Oligo purifications价格
		Float[] purificationsPrice = oligoDao.getPurificationsPrice(purification, newScale);
		if (purificationsPrice != null && purificationsPrice.length == 2) {
			if (purificationsPrice[0].floatValue() <0 || purificationsPrice[1].floatValue() <0) {
				return purificationsPrice;
			} else {
				oligoPriceAndCost[0] += purificationsPrice[0].floatValue();
				oligoPriceAndCost[1] += purificationsPrice[1].floatValue();
			}
		}
		//查询Oligo sequence length价格
		if (seqLength != null && seqLength.intValue()>0) {
			Float[] seqLengthPrice = oligoDao.getLengthPrice(backboneName, seqLength, newScale);
			if (seqLengthPrice == null || (seqLengthPrice.length == 1 && seqLengthPrice[0] == null) || (seqLengthPrice.length == 2 && (seqLengthPrice[0] == null || seqLengthPrice[1] == null))) {
				seqLengthPrice = new Float[2];
				seqLengthPrice[0] = -1f;
				seqLengthPrice[1] = -1f;
				return seqLengthPrice;
			} else if (seqLengthPrice.length == 2) {
				if (seqLengthPrice[0].floatValue() <0 || seqLengthPrice[1].floatValue() <0) {
					return seqLengthPrice;
				} else {
					oligoPriceAndCost[0] += ((seqLengthPrice[0].floatValue())*(seqLength.intValue()));
					oligoPriceAndCost[1] += ((seqLengthPrice[1].floatValue())*(seqLength.intValue()));
				}
			}
		}
		//查询Oligo Modifications价格
		if (StringUtils.isNotBlank(modificationIds)) {
			Float[] modificationsPrice = oligoDao.getModificationsPrice(modificationIds, newScale);
			if (modificationsPrice != null && modificationsPrice.length == 2) {
				if (modificationsPrice[0].floatValue() <0 || modificationsPrice[1].floatValue() <0) {
					return modificationsPrice;
				} else {
					oligoPriceAndCost[0] += modificationsPrice[0].floatValue();
					oligoPriceAndCost[1] += modificationsPrice[1].floatValue();
				}
			}
		}
		//查询Oligo chimericBases价格
		if (StringUtils.isNotBlank(chimericBasesCodes)) {
			Float[] chimericBasesPrice = oligoDao.getChimericBasesPrice(chimericBasesCodes, newScale);
			if (chimericBasesPrice != null && chimericBasesPrice.length == 2) {
				if (chimericBasesPrice[0].floatValue() <0 || chimericBasesPrice[1].floatValue() <0) {
					return chimericBasesPrice;
				} else {
					oligoPriceAndCost[0] += chimericBasesPrice[0].floatValue();
					oligoPriceAndCost[1] += chimericBasesPrice[1].floatValue();
				}
			}
		}
		//查询Oligo mixedBases价格
		if (StringUtils.isNotBlank(mixedBasesCodesCodes)) {
			Float[] mixedBasesPrice = oligoDao.getMixedBasesPrice(mixedBasesCodesCodes, newScale);
			if (mixedBasesPrice != null && mixedBasesPrice.length == 2) {
				if (mixedBasesPrice[0].floatValue() <0 || mixedBasesPrice[1].floatValue() <0) {
					return mixedBasesPrice;
				} else {
					oligoPriceAndCost[0] += mixedBasesPrice[0].floatValue();
					oligoPriceAndCost[1] += mixedBasesPrice[1].floatValue();
				}
			}
		}
		if (aliquotingInto != null && aliquotingInto.intValue()>5) {
			oligoPriceAndCost[0] += (aliquotingInto.intValue()-5)*0.2f;
		}
		return oligoPriceAndCost;
	}
}
