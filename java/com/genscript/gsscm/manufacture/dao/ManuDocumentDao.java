package com.genscript.gsscm.manufacture.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.manufacture.entity.ManuDocument;

@Repository
public class ManuDocumentDao extends HibernateDao<ManuDocument, Integer> {

	public void delDocumentList(Object ids) {
		String hql = "delete from ManuDocument c where c.docId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(hql, map);
	}

	/**
	 * 根据外关联进的refId及docType获得Document list.
	 * 
	 * @param refId
	 * @param type
	 * @return
	 */
	public List<ManuDocument> getDocumentList(Integer refId, DocumentType type) {
		List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
		PropertyFilter refIdFilter = new PropertyFilter("EQI_refId", refId);
		PropertyFilter refTypeFilter = new PropertyFilter("EQS_refType", type
				.value());
		docFilterList.add(refIdFilter);
		docFilterList.add(refTypeFilter);
		List<ManuDocument> docList = this.find(docFilterList);
		return docList;
	}
	
	/**
	 * 根据外关联进的refId及docType获得Document list.
	 * 
	 * @param refId
	 * @param type
	 * @return
	 */
	public List<ManuDocument> getDocumentList(List<Integer> refId, DocumentType type) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Criterion criterionRefType = Restrictions.eq("refType", type.value());
		criterionList.add(criterionRefType);
		if (refId != null && !refId.isEmpty()) {
			Criterion criterion = Restrictions.in("refId", refId);
			criterionList.add(criterion);
		}
		List<ManuDocument> docList = this.find(criterionList.toArray(new Criterion[criterionList                                                        				.size()]));
		return docList;
	}
}
