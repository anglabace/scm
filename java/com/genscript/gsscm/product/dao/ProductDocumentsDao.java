package com.genscript.gsscm.product.dao;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.ProductDocuments;

@Repository
public class ProductDocumentsDao extends HibernateDao<ProductDocuments, Integer> {
	/*
	 * 删除一串Product Documents 关联关系
	 * @param List<Integer> ids
	 * @return
	 */
	public void delPdtDocList(Object ids,Integer docId){
		String del_pdtdoc = "delete from ProductDocuments c where c.docId ="+docId+" and  c.productId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_pdtdoc, map);
	}
	
	/*
	 * 删除一串Product Documents 关联关系 根据productId
	 * @param List<Integer> ids
	 * @return
	 */
	public void delPdtDocByProductIdList(Object ids){
		String del_pdtdoc = "delete from ProductDocuments c where c.productId in (:ids)";
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_pdtdoc, map);
	}

	public ProductDocuments findByproductId(Integer productId,Integer docId) {
		String one_pdtdoc = "from ProductDocuments c where c.productId ="+productId+" and c.docId ="+docId+"";
		return this.findUnique(one_pdtdoc);
	}
}
