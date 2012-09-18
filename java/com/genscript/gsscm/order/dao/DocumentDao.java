package com.genscript.gsscm.order.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.order.entity.Document;

@Repository
public class DocumentDao extends HibernateDao<Document, Integer>{
	private static final String DEL_DOCLIST = "delete from Document c where c.docId in (:ids)";
	private static final String GET_DOCUMENT = "from Document c where c.refType = ? and c.refId = ?";
	private static final String GET_DOCUMENT_INS = "from Document c where c.refType = ? and c.refId = ?";
	public void delDocumentList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_DOCLIST, map);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Document> getDocument(String type, Integer id){
		List list = createQuery(GET_DOCUMENT, type, id).list();
		if(list != null){
			return (List<Document>)list;
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Document> getDocuments(String type, String id){
		List list = createQuery(GET_DOCUMENT, type, id).list();
		if(list != null){
			return (List<Document>)list;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Document> getDocumentIns(String type, Integer id){
		List list = createQuery(GET_DOCUMENT_INS, type, id).list();
		if(list != null){
			return (List<Document>)list;
		}
		return null;
	}
	
	/**
	 * 根据外关联进的refId及docType获得Document list.
	 * @param refId
	 * @param type
	 * @return
	 */
	public List<Document> getDocumentList(Integer refId, DocumentType type) {
		List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
		PropertyFilter refIdFilter = new PropertyFilter(
				"EQI_refId", refId);
		PropertyFilter refTypeFilter = new PropertyFilter(
				"EQS_refType", type.value());
		docFilterList.add(refIdFilter);
		docFilterList.add(refTypeFilter);
		List<Document> docList = this.find(docFilterList);
		return docList;
	}

    //add by zhanghuibin
    public List<Document> getBatchDocumentList(String refIds, DocumentType type){
        String sql = "from Document where refId in (" + refIds + ") and refType=:type";
        Query query = this.getSession().createQuery(sql);
        query.setParameter("type", type.value());
        return query.list();
    }
}
