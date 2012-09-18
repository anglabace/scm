package com.genscript.gsscm.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.dao.CustomerPriceBeanDao;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.product.dto.ProductRelationItemDTO;
import com.genscript.gsscm.product.entity.ProductRelation;

@Repository
public class ProductRelationDao extends HibernateDao<ProductRelation, Integer> {

	@Autowired
	private CustomerPriceBeanDao customerPriceBeanDao;

	public List<ProductRelationItemDTO> getRelateProductBean(Integer productId,Integer custNo,List<String> catalogNoList) {
		List<ProductRelation> productRelations = findBy("productId", productId);
		List<ProductRelationItemDTO> productRelationItemList = new ArrayList<ProductRelationItemDTO>();
		if (productRelations != null) {
			ProductRelationItemDTO productRelationItemDTO = null;
			for (ProductRelation pr : productRelations) {
				
				Integer prId = pr.getRltProductId();
				Criterion criterion1,criterion2,criterion3;
				List<CustomerPriceBean> customerPriceBeans;
				criterion1 = Restrictions.eq("productId",prId);
				criterion2 = Restrictions.eq("custNo",custNo);
				if(catalogNoList!= null){
					criterion3 = Restrictions.not(Restrictions.in("catalogNo", catalogNoList));
					customerPriceBeans = customerPriceBeanDao
					.find(criterion1,criterion2,criterion3);
				}else{
					customerPriceBeans = customerPriceBeanDao
					.find(criterion1,criterion2);
				}
				
				for (CustomerPriceBean customerPrice : customerPriceBeans) {
					productRelationItemDTO = new ProductRelationItemDTO();
					productRelationItemDTO.setCatalogNo(customerPrice
							.getCatalogNo());
					productRelationItemDTO.setItemName(customerPrice.getName());
					productRelationItemDTO.setProductId(customerPrice
							.getProductId());
					productRelationItemDTO.setRelateInfo(pr.getRelateInfo());
					productRelationItemDTO
							.setRelationType(pr.getRelationType());
					productRelationItemDTO.setSize(customerPrice.getSize());
					productRelationItemDTO.setUom(customerPrice.getUom());
					productRelationItemDTO.setUnitPrice(customerPrice
							.getUnitPrice());
					productRelationItemDTO.setSpecialPrice(customerPrice
							.getSpecialPrice());
					productRelationItemDTO.setUpCatalogId(customerPrice.getUpCatalogId());
					productRelationItemDTO.setType(customerPrice.getType());
					productRelationItemDTO.setUpCurrency(customerPrice.getUpCurrency());
					productRelationItemDTO.setUpPrecision(customerPrice.getUpPrecision());
					productRelationItemDTO.setUpSymbol(customerPrice.getUpSymbol());
					productRelationItemDTO.setUpCatalogName(customerPrice.getUpCatalogName());
					productRelationItemDTO.setSpCurrency(customerPrice.getSpCurrency());
					productRelationItemDTO.setSpCatalogId(customerPrice.getSpCatalogId());
					productRelationItemDTO.setSpCatalogName(customerPrice.getSpCatalogName());
					productRelationItemDTO.setSpPrecision(customerPrice.getSpPrecision());
					productRelationItemDTO.setSpSymbol(customerPrice.getSpSymbol());
					productRelationItemDTO.setQtyUom(customerPrice.getQtyUom());
					productRelationItemList.add(productRelationItemDTO);
				}
			}
			
		}
		return productRelationItemList;
	}
}
