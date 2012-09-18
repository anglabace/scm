package com.genscript.gsscm.product.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductDocuments;
import com.genscript.gsscm.product.entity.ProductOfPdtcategoryBean;

@Repository
public class ProductOfPdtcategoryBeanDao extends HibernateDao<ProductOfPdtcategoryBean, Integer> {
	
	public ProductOfPdtcategoryBean getProductOfPdtCategoryBeanByProductIdAndCategoryId(Integer productId,Integer categoryId){
		String hql = "from ProductOfPdtcategoryBean where productId="+productId+" and categoryId="+categoryId;
		return this.findUnique(hql);
	}
	
	/*
	 * 查询Product Documents
	 * @param doc id
	 * @return
	 */
	public Page<ProductOfPdtcategoryBean> searchNotInDocumentsProduct(Page<ProductOfPdtcategoryBean> page,Integer docId,String catalogNo,String name,String categoryName){
		String pdtdoc = "from ProductDocuments where docId="+docId;
 		List<ProductDocuments> pdList = this.find(pdtdoc);
 		List<Integer> ids = new ArrayList<Integer>();
 		for(ProductDocuments pd:pdList){
 			//String docSql = "from Documents where docId = "+pd.getDocId();
 			ids.add(pd.getProductId());
 		}
 		String catalogNoHql = "";
 		String nameHql = "";
 		String productIdHql ="";
 		String categoryHql = "";
 		if(catalogNo!=null){
 			catalogNoHql = " catalogNo like '%" +catalogNo+"%'";
 		}
 		if(name!=null){
 			if(catalogNo!=null){
 				nameHql = " and name like '%" +name+"%'";
 			}else{
 				nameHql = " name like '%" +name+"%'";
 			}
 		}
 		if(!ids.isEmpty()){
 			if(catalogNo!=null||name!=null){
 				productIdHql= " and productId not in (:ids)";
 			}else{
 				productIdHql= " productId not in (:ids)";
 			}
 		}
 		if(categoryName!=null){
 			if(catalogNo!=null||name!=null||!ids.isEmpty()){
 				categoryHql = " and categoryName like '%" +categoryName+"%'";
 			}else{
 				categoryHql = " categoryName like '%" +categoryName+"%'";
 			}
 		}
 		String hql="";
 		if(catalogNo==null&&name==null&&ids.isEmpty()){
 			hql = "from ProductOfPdtcategoryBean";
 		}else{
 			hql = "from ProductOfPdtcategoryBean where "+catalogNoHql +" "+nameHql +" "+productIdHql+" "+categoryHql;
 		}
 		hql += " ";
 		System.out.println(hql);
		Map<String, Object> map = Collections.singletonMap("ids", (Object)ids);
 		return this.findPage(page, hql, map);
	}
//----------add by zhougang 2011 6 21
	public List<ProductOfPdtcategoryBean> getAllcategorylist(Integer sessionCategoryId) {
		String hql="from  ProductOfPdtcategoryBean where categoryId= ?";		
		return this.find(hql,sessionCategoryId);
	}
}
