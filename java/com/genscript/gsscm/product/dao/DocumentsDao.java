package com.genscript.gsscm.product.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.product.entity.Documents;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductBean;
import com.genscript.gsscm.product.entity.ProductDocuments;
import com.genscript.gsscm.product.entity.ProductListBean;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.entity.Service;

@Repository
public class DocumentsDao extends HibernateDao<Documents, Integer> {
	@Autowired
	private ProductDao productDao;

	/*
	 * 查询Product Documents
	 * 
	 * @param productId , page
	 * 
	 * @return page
	 */

	public Page<Documents> searchDocumentsPage(Page<Documents> page,
			String catalogNo, String name, String type, String dec) {
		if (name == null) {
			name = "";
		}
		String typeSql = "";
		if (type != null && !type.equals("")) {

			typeSql = " and c.docType='" + type + "' ";
		}
		if (dec == null) {
			dec = "";
		}
		String docnameSql = "";
		if (name != null && !"".equals(name)) {
			docnameSql = " and c.docName like '%" + name + "%' ";
		}
		String decSql = "";
		if (dec != null && !"".equals(dec)) {
			decSql = " and c.description like '%" + dec + "%'";
		}
		String pdtdoc = "";
		String catalogSql = "";
		catalogSql = " left join c.productList p ";
		String s = " and  p.catalogNo='" + catalogNo + "' ";
		pdtdoc = "select c  from Documents c where 1=1 ";
		if (catalogNo != null && !"".equals(catalogNo)) {
			pdtdoc = "select c  from Documents c " + catalogSql + " where 1=1 "
					+ s;
		}
		pdtdoc += docnameSql + typeSql + decSql;
		System.out.println(pdtdoc);
		return this.findPage(page, pdtdoc);
	}

	@SuppressWarnings("rawtypes")
	public List ExcuteSql(String sql) {
		Query query = this.getSession().createSQLQuery(sql);
		List Data = query.list();
		return Data;
	}

	public void DeleteOlderDocumentByproductId(Integer productId) {
		String pdtdoc = "from ProductDocuments where productId=" + productId;
		List<ProductDocuments> pdList = this.find(pdtdoc);
		if (pdList != null && pdList.size() > 0) { 
			for (int i = 0; i < pdList.size(); i++) {
				ProductDocuments pd = (ProductDocuments) pdList.get(i); 
				this.delete(pd.getDocId());
			} 
		}
		String sql1 = "delete from `product`.`product_documents` where   `product_id`='"
				+ productId + "'";
		Query qury = this.getSession().createSQLQuery(sql1);
		qury.executeUpdate();

	}

	 
	/*
	 * 查询Product Documents
	 * 
	 * @param productId
	 * 
	 * @return
	 */
	public List<Documents> searchProductDocuments(Integer productId) {
		String type = "";
		int flag = 0;
		String typeStr = "";// 获取当前product 的类型type 然后根据type 显示不同的产品 外加
		// DATASHEET

		String Stringtypesql = "select `product`.`product_classification`.`name` from product.`product`"
				+ "  STRAIGHT_JOIN `product`.`product_classification`    "
				+ "  ON `product`.`product`.`product_cls_id` =   "
				+ "  `product`.`product_classification`.`cls_id` and `product`.`product`.`product_id`="
				+ productId;

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
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
					java.sql.ResultSet.CONCUR_READ_ONLY); 
			rs = stmt.executeQuery(Stringtypesql);
			while (rs.next()) {
				if (rs.getString(1) != null) {
					typeStr = rs.getString(1).toString();

				}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
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
		String catalogValue = "";
		Product pbb = this.productDao.getById(productId);
		if (pbb != null) {
			catalogValue = pbb.getCatalogNo();
		} 
		if (catalogValue != null && !"".equals(catalogValue)) {
			if (catalogValue.substring(0, 1).equals("D")
					|| catalogValue.substring(0, 1).equals("B")
					|| typeStr.equals("Chemicals")) {
				flag = 1;
				type = "Document-DATASHEET";
			}
		}
		if (typeStr.equals("Peptide") || typeStr.equals("Antibody")
				|| typeStr.equals("Protein") || typeStr.equals("Chemicals")
				|| typeStr.equals("Molecule")) {
			// 全部显示DATASHEET
			flag = 1;
			type = "Document-DATASHEET";
		} else if (typeStr.equals("Enzyme")) {
			if (catalogValue.equals("E00007") || catalogValue == "E00007"
					|| catalogValue.equals("E00012")
					|| catalogValue == "E00012"
					|| catalogValue.equals("E00019")
					|| catalogValue == "E00019") {
				flag = 2;
			} else {
				flag = 1;
				type = "Document-DATASHEET";
			}
		}
		if (typeStr.equals("Vector")) {
			flag = 3;
			type = "Document-PRODUCTINFO";
		} else if (typeStr.equals("Kit Component") || typeStr.equals("Kit")) {
			flag = 4;
			type = "Document-PROTOCOL";
		}

		List<Documents> list = new ArrayList<Documents>();
		list = GetByFlagAndtype(flag, type, productId);
		return list;
	}

	public List<Documents> GetByFlagAndtype(int flag, String type,
			Integer productId) {
		List<Documents> list = new ArrayList<Documents>();
		String sql2 = "";
		String pdtdoc = "from ProductDocuments where productId=" + productId;
		List<ProductDocuments> pdList = this.find(pdtdoc);
		if (flag != 2) {
			if (pdList != null && pdList.size() > 0) {
				for (ProductDocuments pd : pdList) {
					Documents documents = new Documents();
					String sql = "select * from `product`.`documents` where 1=1   "
							+ " AND doc_type='"
							+ type
							+ "' AND doc_id="
							+ pd.getDocId();
					System.out.println(sql);

					if (flag == 1) {
						sql2 = sql + " and validate_flag='1'";
					}
					if (flag == 3 || flag == 4) {
						sql2 = sql;
					}
					Query qq = this.getSession().createSQLQuery(sql2);
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
									documents.setImageFileType(obj[7]
											.toString());
								}
								if (obj[8] != null) {
									documents.setImageFileName(obj[8]
											.toString());
								}
								if (obj[9] != null) {
									documents.setImageFilePath(obj[9]
											.toString());
								}
								if (obj[10] != null) {
									documents
											.setDescription(obj[10].toString());
								}
								if (obj[11] != null) {
									documents.setOldFlag(obj[11].toString());
								}
								if (obj[12] != null) {
									documents.setNote(obj[12].toString());
								}
								if (obj[13] != null) {
									documents.setInternalFlag(obj[13]
											.toString());
								}
								if (obj[14] != null) {
									documents.setValidateFlag(obj[14]
											.toString());
								}
								if (obj[15] != null) {
									documents.setDispProp(obj[15].toString());
								}

							}
							list.add(documents);
						}
					}

				}
			}
		}
		if (flag == 2) {
			if (pdList != null && pdList.size() > 0) {
				for (ProductDocuments pd : pdList) {
					String sql1 = "select * from `product`.`documents` where `doc_type` ='Document-DATASHEET' AND `validate_flag`='1' AND `doc_id`="
							+ pd.getDocId();
					Query query = this.getSession().createSQLQuery(sql1);
					List lista = query.list();
					Documents documents = new Documents();
					if (lista != null && lista.size() > 0) {
						Object[] obj = (Object[]) lista.get(0);
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
							list.add(documents);
						}

					}
					String sql_other = "select * from `product`.`documents` where `doc_type` !='Document-DATASHEET'  and `doc_type` ='Document-PROTOCOL' and `doc_id`="
							+ pd.getDocId();
					Query query2 = this.getSession().createSQLQuery(sql_other);
					List lista2 = query2.list();
					if (lista2 != null && lista2.size() > 0) {
						for (int a = 0; a < lista2.size(); a++) {
							Object[] obj = (Object[]) lista2.get(0);
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
									documents.setImageFileType(obj[7]
											.toString());
								}
								if (obj[8] != null) {
									documents.setImageFileName(obj[8]
											.toString());
								}
								if (obj[9] != null) {
									documents.setImageFilePath(obj[9]
											.toString());
								}
								if (obj[10] != null) {
									documents
											.setDescription(obj[10].toString());
								}
								if (obj[11] != null) {
									documents.setOldFlag(obj[11].toString());
								}
								if (obj[12] != null) {
									documents.setNote(obj[12].toString());
								}
								if (obj[13] != null) {
									documents.setInternalFlag(obj[13]
											.toString());
								}
								if (obj[14] != null) {
									documents.setValidateFlag(obj[14]
											.toString());
								}
								if (obj[15] != null) {
									documents.setDispProp(obj[15].toString());
								}
							}
							list.add(documents);
						}

					}
				}
			}
		}
		System.out.println(list.size());
		return list;
	}

	/*
	 * 删除一串Documents
	 * 
	 * @param List<Integer> ids
	 * 
	 * @return
	 */
	public void delPdtDocList(Object ids) {
		String del_pdtdoc = "delete from Documents c where c.docId in (:ids)";
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(del_pdtdoc, map);
	}

	public List<Documents> getProductDocumentByOrderItemList(
			OrderItem orderItem, String type) {
		if (type.equals("'Document-PROTOCOL','Document-DATASHEET'")) {
			String hql = "select doc from Documents doc,Product product,ProductDocuments pds "
					+ "where product.productId = pds.productId and pds.docId = doc.docId and "
					+ "(doc.docType ='Document-PROTOCOL' or (doc.docType ='Document-DATASHEET' and doc.validateFlag='1')) and ";

			hql += " product.catalogNo ='" + orderItem.getCatalogNo() + "'";

			return this.find(hql);
		} else {
			String hql = "select doc from Documents doc,Product product,ProductDocuments pds "
					+ "where product.productId = pds.productId and pds.docId = doc.docId and doc.docType in ("
					+ type + ") and ";
			hql += " product.catalogNo ='" + orderItem.getCatalogNo() + "'";
			if (type != null && type.equals("'Document-DATASHEET'")) {
				hql += " and doc.validateFlag='1'";
			}
			return this.find(hql);

		}

	}
}
