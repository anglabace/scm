package com.genscript.gsscm.order.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.dao.CustomerSpecialPriceDao;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.order.entity.RelationProductPriceBean;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.quote.dao.QuoteDao;

@Repository
public class RelationProductPriceBeanDao extends HibernateDao<RelationProductPriceBean, Integer>{
	
	@Autowired
	private CustomerSpecialPriceDao customerSpecialPriceDao;
	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private OrderDao orderDao;
	
	public ProductItemPriceDTO calculateQuoteRelationItemPrice(Integer mainProductId, Integer productId, Integer quoteNo, String catalogNo, Integer custNo, Integer quantity, BigDecimal amount,Date quoteDate){
		RelationProductPriceBean relationProductPriceBean;
		Criterion criterion = Restrictions.and(Restrictions.eq("custNo", custNo), Restrictions.eq("productId", productId));
		relationProductPriceBean = this.findUnique(Restrictions.and(Restrictions.eq("mainProductId", mainProductId), criterion));
		if(relationProductPriceBean != null){
			ProductItemPriceDTO productItemPriceDTO = new ProductItemPriceDTO();
			Double specialPrice = relationProductPriceBean.getSpecialPrice().doubleValue();
//			BigDecimal catalogPrice = relationProductPriceBean.getCatalogPrice();
			Double unitPrice = relationProductPriceBean.getUnitPrice().doubleValue();
			String upCatalogId = relationProductPriceBean.getUpCatalogId();
			String spCatalogId = relationProductPriceBean.getSpCatalogId();
//			String cpCatalogId = relationProductPriceBean.getCpCatalogId();
//			String cpCatalogName = relationProductPriceBean.getCpCatalogName();
			Double catalogPrice = new BigDecimal(0).doubleValue();
			String cpCatalogId = "";
			String cpCatalogName = "";
			String unitPriceCatalogName = relationProductPriceBean.getUpCatalogName();
			String spCatalogName = relationProductPriceBean.getSpCatalogName();
			String upCatalogName = relationProductPriceBean.getUpCatalogName();
			Date qDate;
			if(quoteDate != null){
				qDate = quoteDate;
			}else{
				qDate = quoteDao.get(quoteNo).getQuoteDate();
			}
			if(specialPrice != null){
				CustomerSpecialPrice customerSpecialPrice = customerSpecialPriceDao.findUnique(Restrictions.and(Restrictions.eq("catalogNo", catalogNo), Restrictions.eq("custNo", custNo)));
				Integer minQty = customerSpecialPrice.getMinQty();
				BigDecimal orderTotal = customerSpecialPrice.getOrderTotal();
				Date effFrom = customerSpecialPrice.getEffFrom();
				Date effTo = customerSpecialPrice.getEffTo();
				BigDecimal totalPrice = new BigDecimal(unitPrice).add(amount);
				if(quantity >= minQty && totalPrice.compareTo(orderTotal) >= 0 && qDate.compareTo(effFrom) >= 0 && qDate.compareTo(effTo) <= 0){
					if(catalogPrice == null && unitPrice == null ){
						productItemPriceDTO.setUnitPrice(specialPrice.doubleValue());
						productItemPriceDTO.setCatalogId(upCatalogId);
						productItemPriceDTO.setCatalogName(upCatalogName);
					}else if(catalogPrice != null && unitPrice == null ){
						boolean bool = specialPrice.compareTo(catalogPrice)<0;
						productItemPriceDTO.setUnitPrice(bool?specialPrice:catalogPrice);
						productItemPriceDTO.setCatalogId(bool?spCatalogId:cpCatalogId);
						productItemPriceDTO.setCatalogName(bool?spCatalogName:cpCatalogName);
					}else if(catalogPrice == null && unitPrice != null ){
						boolean bool = new BigDecimal(specialPrice).compareTo(new BigDecimal(unitPrice))<0;
						productItemPriceDTO.setUnitPrice(bool?specialPrice:unitPrice);
						productItemPriceDTO.setCatalogId(bool?spCatalogId:upCatalogId);
						productItemPriceDTO.setCatalogName(bool?spCatalogName:unitPriceCatalogName);
					}else{
						if(catalogPrice != null && catalogPrice.compareTo(unitPrice)<0){
							boolean bool = specialPrice.compareTo(catalogPrice)<0;
							productItemPriceDTO.setUnitPrice(bool?specialPrice:catalogPrice);
							productItemPriceDTO.setCatalogId(bool?spCatalogId:cpCatalogId);
							productItemPriceDTO.setCatalogName(bool?spCatalogName:cpCatalogName);
						}else{
							boolean bool = specialPrice.compareTo(unitPrice)<0;
							productItemPriceDTO.setUnitPrice(bool?specialPrice:unitPrice);
							productItemPriceDTO.setCatalogId(bool?spCatalogId:upCatalogId);
							productItemPriceDTO.setCatalogName(bool?spCatalogName:unitPriceCatalogName);
						}
					}
					return productItemPriceDTO;
				}else if(catalogPrice != null && unitPrice == null){
					productItemPriceDTO.setUnitPrice(catalogPrice);
					productItemPriceDTO.setCatalogId(cpCatalogId);
					productItemPriceDTO.setCatalogName(cpCatalogName);
				}else if(catalogPrice == null && unitPrice != null){
					productItemPriceDTO.setUnitPrice(unitPrice);
					productItemPriceDTO.setCatalogId(upCatalogId);
					productItemPriceDTO.setCatalogName(unitPriceCatalogName);
				}else{
					if(catalogPrice != null && catalogPrice.compareTo(unitPrice)<0){
						productItemPriceDTO.setUnitPrice(catalogPrice);
						productItemPriceDTO.setCatalogId(cpCatalogId);
						productItemPriceDTO.setCatalogName(cpCatalogName);
					}else{
						productItemPriceDTO.setUnitPrice(unitPrice);
						productItemPriceDTO.setCatalogId(upCatalogId);
						productItemPriceDTO.setCatalogName(unitPriceCatalogName);
					}
				}
				return productItemPriceDTO;
			}else if(catalogPrice != null && unitPrice == null){
				productItemPriceDTO.setUnitPrice(catalogPrice);
				productItemPriceDTO.setCatalogId(cpCatalogId);
				productItemPriceDTO.setCatalogName(cpCatalogName);
			}else if(catalogPrice == null && unitPrice != null){
				productItemPriceDTO.setUnitPrice(unitPrice);
				productItemPriceDTO.setCatalogId(upCatalogId);
				productItemPriceDTO.setCatalogName(unitPriceCatalogName);
			}else{
				if(catalogPrice != null && catalogPrice.compareTo(unitPrice)<0){
					productItemPriceDTO.setUnitPrice(catalogPrice);
					productItemPriceDTO.setCatalogId(cpCatalogId);
					productItemPriceDTO.setCatalogName(cpCatalogName);
				}else{
					productItemPriceDTO.setUnitPrice(unitPrice);
					productItemPriceDTO.setCatalogId(upCatalogId);
					productItemPriceDTO.setCatalogName(unitPriceCatalogName);
				}
			}
			return productItemPriceDTO;
		}
		return null;
	}
	
	public ProductItemPriceDTO calculateOrderRelationItemPrice(Integer mainProductId, Integer productId, Integer quoteNo, String catalogNo, Integer custNo, Integer quantity, BigDecimal amount,Date orderDate){
		RelationProductPriceBean relationProductPriceBean;
		Criterion criterion = Restrictions.and(Restrictions.eq("custNo", custNo), Restrictions.eq("productId", productId));
		relationProductPriceBean = this.findUnique(Restrictions.and(Restrictions.eq("mainProductId", mainProductId), criterion));
		if(relationProductPriceBean != null){
			ProductItemPriceDTO productItemPriceDTO = new ProductItemPriceDTO();
			Double specialPrice = relationProductPriceBean.getSpecialPrice().doubleValue();
//			BigDecimal catalogPrice = relationProductPriceBean.getCatalogPrice();
			Double unitPrice = relationProductPriceBean.getUnitPrice().doubleValue();
			String upCatalogId = relationProductPriceBean.getUpCatalogId();
			String spCatalogId = relationProductPriceBean.getSpCatalogId();
//			String cpCatalogId = relationProductPriceBean.getCpCatalogId();
//			String cpCatalogName = relationProductPriceBean.getCpCatalogName();
			Double catalogPrice = new BigDecimal(0).doubleValue();
			String cpCatalogId = "";
			String cpCatalogName = "";
			String unitPriceCatalogName = relationProductPriceBean.getUpCatalogName();
			String spCatalogName = relationProductPriceBean.getSpCatalogName();
			String upCatalogName = relationProductPriceBean.getUpCatalogName();
			Date oDate;
			if(orderDate != null){
				oDate = orderDate;
			}else{
				oDate = orderDao.get(quoteNo).getOrderDate();
			}
			if(specialPrice != null){
				CustomerSpecialPrice customerSpecialPrice = customerSpecialPriceDao.findUnique(Restrictions.and(Restrictions.eq("catalogNo", catalogNo), Restrictions.eq("custNo", custNo)));
				Integer minQty = customerSpecialPrice.getMinQty();
				BigDecimal orderTotal = customerSpecialPrice.getOrderTotal();
				Date effFrom = customerSpecialPrice.getEffFrom();
				Date effTo = customerSpecialPrice.getEffTo();
				BigDecimal totalPrice = new BigDecimal(unitPrice).add(amount);
				if(quantity >= minQty && totalPrice.compareTo(orderTotal) >= 0 && oDate.compareTo(effFrom) >= 0 && oDate.compareTo(effTo) <= 0){
					if(catalogPrice == null && unitPrice == null ){
						productItemPriceDTO.setUnitPrice(specialPrice);
						productItemPriceDTO.setCatalogId(upCatalogId);
						productItemPriceDTO.setCatalogName(upCatalogName);
					}else if(catalogPrice != null && unitPrice == null ){
						boolean bool = new BigDecimal(specialPrice).compareTo(new BigDecimal(catalogPrice))<0;
						productItemPriceDTO.setUnitPrice(bool?specialPrice:catalogPrice);
						productItemPriceDTO.setCatalogId(bool?spCatalogId:cpCatalogId);
						productItemPriceDTO.setCatalogName(bool?spCatalogName:cpCatalogName);
					}else if(catalogPrice == null && unitPrice != null ){
						boolean bool = new BigDecimal(specialPrice).compareTo(new BigDecimal(unitPrice))<0;
						productItemPriceDTO.setUnitPrice(bool?specialPrice:unitPrice);
						productItemPriceDTO.setCatalogId(bool?spCatalogId:upCatalogId);
						productItemPriceDTO.setCatalogName(bool?spCatalogName:unitPriceCatalogName);
					}else{
						if(catalogPrice != null && catalogPrice.compareTo(unitPrice)<0){
							boolean bool = specialPrice.compareTo(catalogPrice)<0;
							productItemPriceDTO.setUnitPrice(bool?specialPrice:catalogPrice);
							productItemPriceDTO.setCatalogId(bool?spCatalogId:cpCatalogId);
							productItemPriceDTO.setCatalogName(bool?spCatalogName:cpCatalogName);
						}else{
							boolean bool = specialPrice.compareTo(unitPrice)<0;
							productItemPriceDTO.setUnitPrice(bool?specialPrice:unitPrice);
							productItemPriceDTO.setCatalogId(bool?spCatalogId:upCatalogId);
							productItemPriceDTO.setCatalogName(bool?spCatalogName:unitPriceCatalogName);
						}
					}
					return productItemPriceDTO;
				}else if(catalogPrice != null && unitPrice == null){
					productItemPriceDTO.setUnitPrice(catalogPrice);
					productItemPriceDTO.setCatalogId(cpCatalogId);
					productItemPriceDTO.setCatalogName(cpCatalogName);
				}else if(catalogPrice == null && unitPrice != null){
					productItemPriceDTO.setUnitPrice(unitPrice);
					productItemPriceDTO.setCatalogId(upCatalogId);
					productItemPriceDTO.setCatalogName(unitPriceCatalogName);
				}else{
					if(catalogPrice != null && catalogPrice.compareTo(unitPrice)<0){
						productItemPriceDTO.setUnitPrice(catalogPrice);
						productItemPriceDTO.setCatalogId(cpCatalogId);
						productItemPriceDTO.setCatalogName(cpCatalogName);
					}else{
						productItemPriceDTO.setUnitPrice(unitPrice);
						productItemPriceDTO.setCatalogId(upCatalogId);
						productItemPriceDTO.setCatalogName(unitPriceCatalogName);
					}
				}
				return productItemPriceDTO;
			}else if(catalogPrice != null && unitPrice == null){
				productItemPriceDTO.setUnitPrice(catalogPrice);
				productItemPriceDTO.setCatalogId(cpCatalogId);
				productItemPriceDTO.setCatalogName(cpCatalogName);
			}else if(catalogPrice == null && unitPrice != null){
				productItemPriceDTO.setUnitPrice(unitPrice);
				productItemPriceDTO.setCatalogId(upCatalogId);
				productItemPriceDTO.setCatalogName(unitPriceCatalogName);
			}else{
				if(catalogPrice != null && catalogPrice.compareTo(unitPrice)<0){
					productItemPriceDTO.setUnitPrice(catalogPrice);
					productItemPriceDTO.setCatalogId(cpCatalogId);
					productItemPriceDTO.setCatalogName(cpCatalogName);
				}else{
					productItemPriceDTO.setUnitPrice(unitPrice);
					productItemPriceDTO.setCatalogId(upCatalogId);
					productItemPriceDTO.setCatalogName(unitPriceCatalogName);
				}
			}
			return productItemPriceDTO;
		}
		return null;
	}
}
