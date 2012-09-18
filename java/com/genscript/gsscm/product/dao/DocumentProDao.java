package com.genscript.gsscm.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.product.entity.DocumentPro;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.ProductDocuments;

@Repository
public class DocumentProDao extends HibernateDao<DocumentPro, Integer> {

	public Documents getByProductId(Integer productId) {
		Documents documents = new Documents();
		String pdtdoc = "from ProductDocuments where productId=" + productId;
		List<ProductDocuments> pdList = this.find(pdtdoc);
		if (pdList != null && pdList.size() > 0) {
			for (ProductDocuments pd : pdList) {
				String sql = "select * from `product`.`documents` where   "
						+ "   doc_type='Document-DATASHEET' and validate_flag='1' AND doc_id="
						+ pd.getDocId();
				Query qq = this.getSession().createSQLQuery(sql);
				if (qq.list() != null) {
					if (qq.list().size() > 0) {
						Object[] obj = (Object[]) qq.list().get(0);
						if (obj != null) {
							if (obj[0] != null) {
								documents.setDocId(Integer.parseInt(obj[0]
										.toString()));
							}
							if (obj[1] != null) {
								documents.setDocName(obj[1].toString());
							}
							if (obj[2] != null) {
								documents.setVersion(obj[2].toString());
							}
							if (obj[3] != null) {
								documents.setDocType(obj[3].toString());
							}
							if (obj[4] != null) {
								documents.setDocFileType(obj[4].toString());
							}
							if (obj[5] != null) {
								documents.setDocFileName(obj[5].toString());
							}
							if (obj[6] != null) {
								documents.setDocFilePath(obj[6].toString());
							}
							if (obj[7] != null) {
								documents.setImageFileType(obj[7].toString());
							}
							if (obj[8] != null) {
								documents.setImageFileName(obj[8].toString());
							}
							if (obj[9] != null) {
								documents.setImageFilePath(obj[9].toString());
							}
							if (obj[10] != null) {
								documents.setDescription(obj[10].toString());
							}
							if (obj[11] != null) {
								documents.setOldFlag(obj[11].toString());
							}
							if (obj[12] != null) {
								documents.setNote(obj[12].toString());
							}
							if (obj[13] != null) {
								documents.setInternalFlag(obj[13].toString());
							}
							if (obj[14] != null) {
								documents.setValidateFlag(obj[14].toString());
							}
							if (obj[15] != null) {
								documents.setDispProp(obj[15].toString());
							}

						}

					}

				}

			}
		}
		return documents;
	}

}
