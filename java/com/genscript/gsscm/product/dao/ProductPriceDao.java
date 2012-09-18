package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductPrice;

@Repository
public class ProductPriceDao extends HibernateDao<ProductPrice, Integer> {

    /**
     * 获得该category是否有相关的Product.
     *
     * @param categoryId
     * @return
     */
    public Long getCountByCategoryId(Integer categoryId) {
        String hql = "select count(*) from ProductPrice where categoryId=?";
        return this.findUnique(hql, categoryId);
    }
    
    public Long checkAllproductIdandcatalogId(String catalogId,Integer productId){
        String hql = "select count(*) from ProductPrice where catalogId = ? AND productId=?";
        return this.findUnique(hql, catalogId, productId);
    }

    public ProductPrice getProductPrice(String catalogId, Integer categoryId, Integer productId) {
        String hql = "from ProductPrice where catalogId = ? AND categoryId=? AND productId=?";
        return this.findUnique(hql, catalogId, categoryId, productId);
    }

    public List<ProductPrice> getProductPriceByCategoryIdAndCatalogId(String catalogId, Integer categoryId) {
        String hql = "from ProductPrice where catalogId = ? AND categoryId=?";
        return this.find(hql, catalogId, categoryId);
    }

    public void delPriceList(Integer productId, Object catalogIdList) {
        String hql = "delete from ProductPrice c where c.productId=:productId and c.catalogId in (:catalogIdList)";
        Map<String, Object> map = Collections.singletonMap("catalogIdList", catalogIdList);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("productId", productId);
        values.put("catalogIdList", map.get("catalogIdList"));
        batchExecute(hql, values);
    }

    /**
     * 批量删除ProductPrice.
     *
     * @param categoryId
     * @param productIdList
     */
    public void delProductPriceList(Integer categoryId, Object productIdList) {
        String hql = "delete from ProductPrice c where c.categoryId=:categoryId and c.productId in (:productIdList)";
        Map<String, Object> map = Collections.singletonMap("productIdList", productIdList);
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("categoryId", categoryId);
        values.put("productIdList", map.get("productIdList"));
        batchExecute(hql, values);
    }

    public List<ProductPrice> getPdtPriceListByCategoryId(Integer categoryId) {
        String hql = "from ProductPrice where categoryId=?";
        return this.find(hql, categoryId);
    }

    public List<ProductPrice> getPdtPriceListWithCatalog() {
        String hql = "select p from ProductPrice p,Catalog c where c.status='ACTIVE' AND c.catalogId = p.catalogId";
        return this.find(hql);
    }

    public ProductPrice getObjectByID(Integer objectId) {
        String hql = "from  ProductPrice where priceId=?";
        return this.findUnique(hql, objectId);

    }
}
