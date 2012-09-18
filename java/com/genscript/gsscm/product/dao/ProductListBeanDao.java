package com.genscript.gsscm.product.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.genscript.core.orm.PropertyFilter;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter.MatchType;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.product.entity.ProductListBean;

@Repository
public class ProductListBeanDao extends HibernateDao<ProductListBean, Integer> {

	@SuppressWarnings("rawtypes")
	public Page<ProductListBean> searchProductList_new(
			Page<ProductListBean> page, List<PropertyFilter> filters,
			String catalogNos) throws ParseException {
		List<ProductListBean> productListBeanlist = new ArrayList<ProductListBean>();
		String get_all_products = "SELECT "
				+ "`product`.`product`.`product_id` AS `product_id`,"
				+ "`product`.`product`.`catalog_no` AS `catalog_no`,"
				+ "`product`.`product`.`name` AS `name`,"
				+ "`product`.`product`.`size` AS `size`,"
				+ "`product`.`product_classification`.`name` AS `www`,"
				+ "`product`.`product`.`uom` AS `uom`,"
				+ "`product`.`product`.`status` AS `status`,"
				+ "`product`.`product`.`creation_date` AS `creation_date`,"
				+ "`product`.`product`.`modify_date` AS `modify_date`, "
				+ "`product`.`product`.`short_desc` AS `description`"
				+ " FROM `product`.`product`       "
				+ "  LEFT JOIN `product`.`product_price`  "
				+ " ON product.product_id = product_price.product_id    "
				+ "  AND product_price.catalog_id = product.get_base_catalog()  "
				+ "  STRAIGHT_JOIN `product`.`product_classification`    "
				+ "  ON `product`.`product`.`product_cls_id` =   "
				+ "  `product`.`product_classification`.`cls_id`";
		List firlelist = filters;
		if (firlelist != null) {
			for (int i = 0; i < firlelist.size(); i++) {
				PropertyFilter propertyFilter = (PropertyFilter) firlelist
						.get(i);
				if (propertyFilter.getPropertyName().equals("catalogNo")) {
					if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
						get_all_products += " and  `product`.`product`.`catalog_no` ='"
								+ propertyFilter.getPropertyValue() + "' ";
					}
					if (propertyFilter.getMatchType().equals(MatchType.PLIKE)) {
						get_all_products += " and  `product`.`product`.`catalog_no` like '"
								+ propertyFilter.getPropertyValue() + "%' ";
					}
					if (propertyFilter.getMatchType().equals(MatchType.SLIKE)) {
						get_all_products += " and  `product`.`product`.`catalog_no` like '%"
								+ propertyFilter.getPropertyValue() + "' ";
					}
					if (propertyFilter.getMatchType().equals(MatchType.LIKE)) {
						get_all_products += " and  `product`.`product`.`catalog_no` like '%"
								+ propertyFilter.getPropertyValue() + "%' ";
					}
				}

				if (propertyFilter.getPropertyName().equals("catalogNo2")) {
					get_all_products += " and  `product`.`product`.`catalog_no` like '"
							+ propertyFilter.getPropertyValue() + "%' ";
				}

				if (propertyFilter.getPropertyName().equals("name")) {
					if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
						get_all_products += " and `product`.`product`.`name`  = '"
								+ propertyFilter.getPropertyValue() + "'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.PLIKE)) {
						get_all_products += " and `product`.`product`.`name`  like '"
								+ propertyFilter.getPropertyValue() + "%'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.SLIKE)) {
						get_all_products += " and `product`.`product`.`name`  like '%"
								+ propertyFilter.getPropertyValue() + "'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.LIKE)) {
						get_all_products += " and `product`.`product`.`name`  like '%"
								+ propertyFilter.getPropertyValue() + "%'";
					}
				}
				if (propertyFilter.getPropertyName().equals("type")) {
					if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
						get_all_products += " and `product`.`product_classification`.`name` = '"
								+ propertyFilter.getPropertyValue() + "'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.PLIKE)) {
						get_all_products += " and `product`.`product_classification`.`name`  like '"
								+ propertyFilter.getPropertyValue() + "%'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.SLIKE)) {
						get_all_products += " and `product`.`product_classification`.`name`  like '%"
								+ propertyFilter.getPropertyValue() + "'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.LIKE)) {
						get_all_products += " and `product`.`product_classification`.`name` like '%"
								+ propertyFilter.getPropertyValue() + "%'";
					}
				}
				if (propertyFilter.getPropertyName().equals("description")) {
					if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
						get_all_products += " and `product`.`product`.`short_desc`  = '"
								+ propertyFilter.getPropertyValue() + "'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.PLIKE)) {
						get_all_products += " and `product`.`product`.`short_desc`  like '"
								+ propertyFilter.getPropertyValue() + "%'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.SLIKE)) {
						get_all_products += " and `product`.`product`.`short_desc`  like '%"
								+ propertyFilter.getPropertyValue() + "'";
					}
					if (propertyFilter.getMatchType().equals(MatchType.LIKE)) {
						get_all_products += " and `product`.`product`.`short_desc`  like '%"
								+ propertyFilter.getPropertyValue() + "%'";
					}
				}

				if (propertyFilter.getPropertyName().equals("status")) {
					get_all_products += " and `product`.`product`.`status`  = '"
							+ propertyFilter.getPropertyValue() + "'";
				}
			}
		}
		if (StringUtils.isNotBlank(catalogNos)) {
			get_all_products += " and `product`.`product`.`catalog_no` in("
					+ catalogNos + ") ";
		}
		Long counts = 0L;
		if (page.isAutoCount()) {
			String getallCount = "SELECT count(`product`.`product`.`product_id`)"
					+ " FROM `product`.`product`       "
					+ "  LEFT JOIN `product`.`product_price`  "
					+ " ON product.product_id = product_price.product_id    "
					+ "  AND product_price.catalog_id = product.get_base_catalog()  "
					+ "  STRAIGHT_JOIN `product`.`product_classification`    "
					+ "  ON `product`.`product`.`product_cls_id` =   "
					+ "  `product`.`product_classification`.`cls_id`";
			List firlelist2 = filters;
			if (firlelist2 != null) {
				for (int i = 0; i < firlelist2.size(); i++) {
					PropertyFilter propertyFilter = (PropertyFilter) firlelist2
							.get(i);
					if (propertyFilter.getPropertyName().equals("catalogNo")) {
						if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
							getallCount += " and  `product`.`product`.`catalog_no` ='"
									+ propertyFilter.getPropertyValue() + "' ";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.PLIKE)) {
							getallCount += " and  `product`.`product`.`catalog_no` like '"
									+ propertyFilter.getPropertyValue() + "%' ";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.SLIKE)) {
							getallCount += " and  `product`.`product`.`catalog_no` like '%"
									+ propertyFilter.getPropertyValue() + "' ";
						}
						if (propertyFilter.getMatchType()
								.equals(MatchType.LIKE)) {
							getallCount += " and  `product`.`product`.`catalog_no` like '%"
									+ propertyFilter.getPropertyValue() + "%' ";
						}
					}
					if (propertyFilter.getPropertyName().equals("catalogNo2")) {
						getallCount += " and  `product`.`product`.`catalog_no` like '"
								+ propertyFilter.getPropertyValue() + "%' ";
					}

					if (propertyFilter.getPropertyName().equals("name")) {
						if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
							getallCount += " and `product`.`product`.`name`  = '"
									+ propertyFilter.getPropertyValue() + "'";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.PLIKE)) {
							getallCount += " and `product`.`product`.`name`  like '"
									+ propertyFilter.getPropertyValue() + "%'";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.SLIKE)) {
							getallCount += " and `product`.`product`.`name`  like '%"
									+ propertyFilter.getPropertyValue() + "'";
						}
						if (propertyFilter.getMatchType()
								.equals(MatchType.LIKE)) {
							getallCount += " and `product`.`product`.`name`  like '%"
									+ propertyFilter.getPropertyValue() + "%'";
						}
					}
					if (propertyFilter.getPropertyName().equals("type")) {
						if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
							get_all_products += " and `product`.`product_classification`.`name` = '"
									+ propertyFilter.getPropertyValue() + "'";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.PLIKE)) {
							get_all_products += " and `product`.`product_classification`.`name`  like '"
									+ propertyFilter.getPropertyValue() + "%'";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.SLIKE)) {
							get_all_products += " and `product`.`product_classification`.`name`  like '%"
									+ propertyFilter.getPropertyValue() + "'";
						}
						if (propertyFilter.getMatchType()
								.equals(MatchType.LIKE)) {
							get_all_products += " and `product`.`product_classification`.`name` like '%"
									+ propertyFilter.getPropertyValue() + "%'";
						}
					}

					if (propertyFilter.getPropertyName().equals("description")) {
						if (propertyFilter.getMatchType().equals(MatchType.EQ)) {
							getallCount += " and `product`.`product`.`short_desc`  = '"
									+ propertyFilter.getPropertyValue() + "'";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.PLIKE)) {
							getallCount += " and `product`.`product`.`short_desc`  like '"
									+ propertyFilter.getPropertyValue() + "%'";
						}
						if (propertyFilter.getMatchType().equals(
								MatchType.SLIKE)) {
							getallCount += " and `product`.`product`.`short_desc`  like '%"
									+ propertyFilter.getPropertyValue() + "'";
						}
						if (propertyFilter.getMatchType()
								.equals(MatchType.LIKE)) {

							getallCount += " and `product`.`product`.`short_desc`  like '%"
									+ propertyFilter.getPropertyValue() + "%'";
						}
					}

					if (propertyFilter.getPropertyName().equals("status")) {
						getallCount += " and `product`.`product`.`status`  = '"
								+ propertyFilter.getPropertyValue() + "'";
					}
				}
			}
			if (StringUtils.isNotBlank(catalogNos)) {
				getallCount += " and `product`.`product`.`catalog_no` in("
						+ catalogNos + ") ";
			}
			System.out.println(getallCount);
			List list2 = this.getSession().createSQLQuery(getallCount).list();
			if (list2 != null) {
				counts = Long.parseLong(list2.get(0).toString());
			}
		}
		String endstr = "ORDER BY product.product_id DESC   LIMIT  "
				+ page.getPageSize() * (page.getPageNo() - 1) + ", "
				+ page.getPageSize() * (page.getPageNo()) + "";
		String sql = get_all_products + " " + endstr;
		System.out.println(sql); 
		ClassPathResource cr = new ClassPathResource("application.properties");
		Properties pros = new Properties();
		try {
			pros.load(cr.getInputStream());
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		String user = pros.getProperty("jdbc.username");
		String password = pros.getProperty("jdbc.password");
		String url = pros.getProperty("jdbc.url");
		String driver = pros.getProperty("jdbc.driver");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Page<ProductListBean> productListBeanPage = new Page<ProductListBean>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		ProductListBean pbean = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
					java.sql.ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				pbean = new ProductListBean();
				if (rs.getString(1) != null) {
					Integer productId = Integer.valueOf(rs.getString(1)
							.toString());
					pbean.setProductId(productId);
				}
				if (rs.getString(2) != null) {
					String catalog_no = rs.getString(2).toString();
					pbean.setCatalogNo(catalog_no);
				}
				if (rs.getString(3) != null) {
					String name = rs.getString(3).toString();
					pbean.setName(name);
				}
				if (rs.getString(4) != null) {
					Double size = Double.valueOf(rs.getString(4).toString());
					pbean.setSize(size);
				}

				if (rs.getString(6) != null) {
					String uom = rs.getString(6).toString();
					pbean.setUom(uom);
				}

				if (rs.getString(5) != null) {
					String classification_name = rs.getString(5).toString();
					pbean.setType(classification_name);
				}
				if (rs.getString(7) != null) {
					String status = rs.getString(7).toString();
					pbean.setStatus(status);
				}

				if (rs.getString(8) != null) {
					Date creation_date = simpleDateFormat.parse(rs.getString(8)
							.toString());
					pbean.setCreationDate(creation_date);
				}
				if (rs.getString(9) != null) {
					Date modify_date = simpleDateFormat.parse(rs.getString(9)
							.toString());
					pbean.setModifyDate(modify_date);
				}
				if (rs.getString(10) != null && !"".equals(rs.getString(10))
						&& !"none".equals(rs.getString(10))) {
					String description = rs.getString(10).toString();
					pbean.setDescription(description);
				}

				productListBeanlist.add(pbean);
				productListBeanPage.setResult(productListBeanlist);
				productListBeanPage.setPageNo(page.getPageNo());
				productListBeanPage.setTotalCount(Long.parseLong(counts + ""));
				productListBeanPage.setPageSize(15);
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("database driver has no  !");
			System.out.println(e1.toString());
		} catch (SQLException e2) {
			System.out.println("database is exception");
			System.out.println(e2.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	  
		productListBeanPage.setResult(productListBeanlist);
		productListBeanPage.setPageNo(page.getPageNo());
		productListBeanPage.setTotalCount(Long.parseLong(counts + ""));
		productListBeanPage.setPageSize(15);
 
		return productListBeanPage;
	}

	@SuppressWarnings("unchecked")
	public Page<ProductListBean> getProductList(Page<ProductListBean> page) {
		Assert.notNull(page, "page can not be null");
		Integer pageSize;
		Criterion criterion = Restrictions.eq("status", "ACTIVE");
		Criteria c = createCriteria(criterion);

		if (page.isAutoCount()) {
			Long count = 0L;
			String countHql = "select count(p.productId) from Product p where p.status='ACTIVE'";

			try {
				count = findUnique(countHql);
			} catch (Exception e) {
				throw new RuntimeException("hql can't be auto count, hql is:"
						+ countHql, e);
			}
			page.setTotalCount(Long.valueOf(count));
		}
		pageSize = page.getPageSize();
		if (pageSize != null && pageSize != 0)
			setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<ProductListBean> searchProductList(String catalogNo,
			String name, List<String> catalogNoList) {
		Criteria criteria = this.getSession().createCriteria(
				ProductListBean.class);
		if (catalogNo != null && catalogNo.trim().length() > 0) {
			criteria.add(Restrictions.like("catalogNo", "%" + catalogNo + "%"));
		}
		if (catalogNoList != null && !catalogNoList.isEmpty()) {
			criteria.add(Restrictions.not(Restrictions.in("catalogNo",
					catalogNoList)));
		}
		if (name != null && name.trim().length() > 0) {
			criteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		// add by Lichun Cui 2010-10-222
		criteria.add(Restrictions.eq("status", StatusType.ACTIVE.value()));
		return criteria.list();
	}

}
