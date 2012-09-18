package com.genscript.gsscm.quote.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteCustCloning;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;

@Repository
public class QuoteCustCloningDao extends
		HibernateDao<QuoteCustCloning, Integer> {

	@Autowired
	private QuotePlasmidPreparationDao quotePlasmidPreparationDao;
	@Autowired
	private QuoteItemDao quoteItemDao;

	public String getCustomercloningByItemId(Integer ItemNo, String itemId,
			String quoteNo, String name) {
		String txt = "";
		String sql = "select q from QuoteCustCloning q where q.quoteItemId=? and q.quoteNo=? ";
		QuoteCustCloning quoteCustCloning = this.findUnique(sql, itemId,
				quoteNo);
		if (name.equals("")) {
			txt += ">Item " + ItemNo + " " + name + "--custom_cloning";
		} else {
			txt += ">Item " + ItemNo + " custom_cloning";
		}

		if (quoteCustCloning != null && !"".equals(quoteCustCloning)) {
			if (quoteCustCloning.getTgtVector() != null
					&& !"".equals(quoteCustCloning.getTgtVector())) {
				txt += " Vector name: " + quoteCustCloning.getTgtVector() + ",";
			}

			if (quoteCustCloning.getTgtVectorSize() != null
					&& !"".equals(quoteCustCloning.getTgtVectorSize())) {
				txt += " Vector size: " + quoteCustCloning.getTgtVectorSize()
						+ ",";
			}

			if (quoteCustCloning.getTgtResistance() != null
					&& !"".equals(quoteCustCloning.getTgtResistance())) {
				txt += " Resistance: " + quoteCustCloning.getTgtResistance()
						+ "," + System.getProperty("line.separator");
			}
			QuotePlasmidPreparation quotePlasmidPreparation = null;
			String Customplasmidpreparation = "";
			if (quoteCustCloning.getPlasmidPrepFlag() != null
					&& !"".equals(quoteCustCloning.getPlasmidPrepFlag())) {
				txt += " Plasmid preparation : "
						+ quoteCustCloning.getPlasmidPrepFlag() + ",";
				if (quoteCustCloning.getPlasmidPrepFlag().equals("Y")) {
					// 从Plasmid preparation表中去数据
					QuoteItem quoteItems2 = quoteItemDao.getById(Integer.parseInt(itemId));

					if (quoteItems2 != null) {
						if (quoteItems2.getParentId() != null
								&& !"".equals(quoteItems2.getParentId())) {
							quotePlasmidPreparation = quotePlasmidPreparationDao
									.getById(quoteItems2.getParentId());
						}
						if (quotePlasmidPreparation != null) {
							Customplasmidpreparation = " Custom plasmid preparation: "
									+ quotePlasmidPreparation.getPrepWeight()
									+ quotePlasmidPreparation.getPrepWtUom()
									+ ", Quality grade :"
									+ quotePlasmidPreparation.getQualityGrade()
									+ ", Additional analysis :"
									+ quotePlasmidPreparation
											.getAdditionalAnalysis();
						}
					}

					txt += Customplasmidpreparation;
				}

			}

		}
		return txt;
	}
}
