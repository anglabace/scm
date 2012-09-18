package com.genscript.gsscm.product.dao;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.CategorySearchBean;

import java.util.List;

@Repository
public class CategorySearchBeanDao extends HibernateDao<CategorySearchBean, Integer> {
    private static String search_all_Products = "SELECT\n" +
            "`product`.`product`.`product_id` AS `product_id`,\n" +
            "`product`.`product`.`catalog_no` AS `catalog_no`,\n" +
            "`product`.`product`.`name` AS `name`,\n" +
            "`product`.`product`.`size` AS `size`,\n" +
            "`product`.`product`.`uom` AS `uom`,\n" +
            "`product`.`product`.`short_desc` AS `description`,\n" +
            "`product`.`product_classification`.`name` AS `type`,\n" +
            "`product`.`product`.`status` AS `status`,\n" +
            "`product`.`product_price`.`standard_price` AS `unit_price`,\n" +
            "`product`.`product`.`creation_date` AS `creation_date`,\n" +
            "`product`.`product`.`modify_date` AS `modify_date`,\n" +
            "`product`.`product`.`created_by` AS `created_by`,\n" +
            "`product`.`product`.`modified_by` AS `modified_by`\n" +
            "FROM `product`.`product`\n" +
            "LEFT JOIN `product`.`product_price`\n" +
            "ON product.product_id = product_price.product_id\n" +
            "STRAIGHT_JOIN `product`.`product_classification`\n" +
            "ON `product`.`product`.`product_cls_id` = \n" +
            "`product`.`product_classification`.`cls_id`\n" +
            "WHERE product_price.catalog_id = product.get_base_catalog()\n" +
            "ORDER BY product_id DESC\n" +
            "LIMIT 20";

    public Page<CategorySearchBean> findMyALlProduct(Page page, List<PropertyFilter> filters) {
        return this.findPage(page, search_all_Products, filters);
    }

}
