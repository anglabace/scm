package com.genscript.gsscm.customer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.customer.entity.NoteDocument;

@Repository
public class NoteDocumentDao extends HibernateDao<NoteDocument, Integer> {

	public void delDocList(Object ids) {
		String hql = "delete from NoteDocument c where c.docId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}
	
	/**
	 * 根据外关联进的refId及docType获得Document list.
	 * @param refId
	 * @param type
	 * @return
	 */
	public List<NoteDocument> getDocumentList(Integer refId, DocumentType type) {
		List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
		PropertyFilter refIdFilter = new PropertyFilter(
				"EQI_refId", refId);
		PropertyFilter refTypeFilter = new PropertyFilter(
				"EQS_refType", type.value());
		docFilterList.add(refIdFilter);
		docFilterList.add(refTypeFilter);
		List<NoteDocument> docList = this.find(docFilterList);
		return docList;
	}
}
