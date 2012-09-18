package com.genscript.gsscm.product.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductDocuments;

@Repository
public class ProductDao extends HibernateDao<Product, Integer> {

	/**
	 * 根据catalogNo获得Product
	 * 
	 * @param catalogNo
	 * @return
	 */
	public Product getProductByCatalogNo(String catalogNo) {
		String hql = "from Product where catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		return this.findUnique(hql, map);
	}

	/**
	 * 根据catalogNo 和 giftFlag获得 Product
	 * 
	 * @param catalogNo
	 * @param
	 * @return
	 */
	public Product getGiftProductByCatalogNo(String catalogNo, String GiftFlag) {
		String hql = "from Product where catalogNo=:catalogNo and giftFlag=:giftFlag";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		map.put("giftFlag", GiftFlag);
		return this.findUnique(hql, map);
	}

	public Integer getProductIdByCatalogNo(String catalogNo) {
		Product pro = this.findUnique("from Product where catalogNo=?",
				catalogNo);
		if (pro != null) {
			return pro.getProductId();
		}
		return null;
	}

	private static final String GET_STATE_TAX = "select p.stateTaxCls from Product p where p.catalogNo=? and p.taxable=?";
	private static final String GET_COST = "select p.unitCost from Product p where p.catalogNo=?";
	private static final String GET_GIFT_CATALOG = "from Product p where p.giftFlag=?  and p.status=?";

	public Integer getStateTaxClass(final String catalogNo) {
		return findUnique(GET_STATE_TAX, catalogNo, "Y");
	}

	public Page<Product> getGiftableCatalog(Page<Product> page) {
		return findPage(page, GET_GIFT_CATALOG, "Y", "ACTIVE");
	}

	public BigDecimal getUnitCost(String catalogNo) {
		return findUnique(GET_COST, catalogNo);
	}

	/*
	 * 查询Product Documents
	 * 
	 * @param doc id
	 * 
	 * @return
	 */
	public List<Product> searchDocumentsProduct(Integer docId) {
		String pdtdoc = "from ProductDocuments where docId=" + docId;
		List<Product> list = new ArrayList<Product>();
		List<ProductDocuments> pdList = this.find(pdtdoc);
		for (ProductDocuments pd : pdList) {
			// String docSql = "from Documents where docId = "+pd.getDocId();
			Product product = this.getById(pd.getProductId());
			list.add(product);
		}
		return list;
	}

	/*
	 * 查询Product Documents
	 * 
	 * @param doc id
	 * 
	 * @return
	 */
	public Page<Product> searchNotInDocumentsProduct(Page<Product> page,
			Integer docId, String catalogNo, String name, String categoryName) {
		String pdtdoc = "from ProductDocuments where docId=" + docId;
		List<ProductDocuments> pdList = this.find(pdtdoc);
		List<Integer> ids = new ArrayList<Integer>();
		for (ProductDocuments pd : pdList) {
			// String docSql = "from Documents where docId = "+pd.getDocId();
			ids.add(pd.getProductId());
		}
		String catalogNoHql = "";
		String nameHql = "";
		String productIdHql = "";
		String categoryHql = "";
		if (catalogNo != null && !"".equals(catalogNo)) {
			catalogNoHql = "  and p.catalogNo like '%" + catalogNo + "%'";
		}
		if (name != null && !"".equals(name)) {
			if (catalogNo != null && !"".equals(catalogNo)) {
				nameHql = "  and p.name like '%" + name + "%'";
			} else {
				nameHql = "  and p.name like '%" + name + "%'";
			}
		}
		if (!ids.isEmpty() && ids != null && !"".equals(ids)) {
			if (catalogNo != null || name != null) {
				productIdHql = "  and p.productId not in (:ids)";
			} else {
				productIdHql = "   and p.productId not in (:ids)";
			}
		}
		if (categoryName != null && !"".equals(categoryName)) {
			if (catalogNo != null || name != null || !ids.isEmpty()) {
				categoryHql = "  and pc.categoryNo like '%"
						+ categoryName
						+ "%' and p.productId = pp.productId and pp.categoryId = pc.categoryId";
			} else {
				categoryHql = "and pc.categoryNo like '%" + categoryName + "%'";
			}
		}
		String hql = "";
		if (catalogNo == null && name == null && ids.isEmpty()
				&& categoryName == null ) {
			hql = "from Product";
		} else {
			if (catalogNo == null || "".equals(catalogNo)) {
				if(categoryName!=null && !"".equals(categoryName)) {
					hql = "select p from Product p,ProductPrice pp,ProductCategory pc where 1=1 "
						+ catalogNoHql + nameHql + productIdHql + categoryHql;
				}else{
					hql = "from Product p where 1=1 " + catalogNoHql + " " + nameHql
					+ " " + productIdHql;
			}
				
			} else {
				hql = "select p from Product p,ProductPrice pp,ProductCategory pc where 1=1 and p.productId = pp.productId and pp.categoryId = pc.categoryId "
						+ catalogNoHql + nameHql + productIdHql + categoryHql;
			}

		}
		System.out.println(hql);
		Map<String, Object> map = Collections.singletonMap("ids", (Object) ids);
		return this.findPage(page, hql, map);
	}

	/**
	 * 通过catalogNo查找温度
	 * 
	 * @param catalogNo
	 * @return
	 */
	public Double getTemperatureByCatalogNo(String catalogNo) {
		String hql = "select psc.temperature from ProductShipCondition psc, Product p where psc.productId = p.productId and p.catalogNo=:catalogNo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNo", catalogNo);
		List<Double> temperatureList = this.find(hql, map);
		if (temperatureList != null && !temperatureList.isEmpty()) {
			return temperatureList.get(0);
		}
		return null;
	}

	/**
	 * 通过clsId查询CatalogNo列表
	 * 
	 * @author Zhang Yong
	 * @param clsIdList
	 * @return
	 */
	public List<String> getCatalogNoByClsId(List<Integer> clsIdList) {
		String hql = "SELECT catalogNo FROM Product WHERE productClsId in(:clsIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clsIdList", clsIdList);
		return this.find(hql, map);
	}

    public Integer getGiftProductNumByNos(List<String> catalogNos) {
		String hql = "select catalogNo FROM Product WHERE catalogNo in(:catalogNos) and giftFlag='Y'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catalogNos", catalogNos);
        List list = this.find(hql, map);
        if(list == null){
            return 0;
        }else{
            return list.size();
        }
	}

    public Integer getGiftProductTimeByNos(int custNo) {
		String hql = "select count(*) from ( " +
                "select oitem.order_item_id as item_id,'order' as item_type,oitem.catalog_no from order.order od,order.order_items oitem where oitem.order_no=od.order_no and oitem.TYPE='PRODUCT' AND cust_no=:custNo AND YEAR(od.order_date) =YEAR(CURDATE())" +
                "union " +
                "select qitem.quote_item_id as item_id,'quote' as item_type,qitem.catalog_no from order.quote qu,order.quote_items qitem where qitem.quote_no=qu.quote_no and qitem.TYPE='PRODUCT' AND cust_no=:custNo AND  YEAR(qu.quote_date) =YEAR(CURDATE())) as temp_data," +
                "(select catalog_no from product.product where gift_flag='Y') as pro " +
                "where temp_data.catalog_no=pro.catalog_no";
        Query query = this.getSession().createSQLQuery(hql).setParameter("custNo", custNo);
        List list = query.list();
        if(list == null || list.get(0) == null){
            return 0;
        }else{
            return Integer.valueOf(list.get(0).toString());
        }
	}
}
