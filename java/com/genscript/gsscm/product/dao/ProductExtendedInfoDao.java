package com.genscript.gsscm.product.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;

@Repository
public class ProductExtendedInfoDao  extends HibernateDao<ProductExtendedInfo, Integer>{

    private static final String getUrlByProductsql = " from ProductExtendedInfo where productId=? ";

    public ProductExtendedInfo getUrlByProductId(Integer productID) {
        return  findUnique(getUrlByProductsql, productID);
    }

}
