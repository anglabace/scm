package com.genscript.gsscm.quote.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteMutagenesis;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;

@Repository
public class QuoteMutagenesisDao extends
		HibernateDao<QuoteMutagenesis, Integer> {
	private QuoteMutagenesis quoteMutagenesis;

	@Autowired
	private QuotePlasmidPreparationDao quotePlasmidPreparationDao;
	@Autowired
	private QuoteItemDao quoteItemDao;

	public String getMutaggenesisByItemId(Integer ItemNo, String itemId, String quoteNo, String name) {
		String txt = "";
		String sqls = "select q from QuoteMutagenesis q where q.quoteItemId=? and q.quoteNo=?";
		if (name.equals("")) {
			txt += ">Item " + ItemNo + "Mutagenesis";
		} else {
			txt += ">Item " + ItemNo + "" + name + "--Mutagenesis";
		}
		quoteMutagenesis = this.findUnique(sqls,Integer.parseInt(itemId), Integer.parseInt(quoteNo));
		if (quoteMutagenesis != null) {
			if (quoteMutagenesis.getTmplInsertName() != null && !"".equals(quoteMutagenesis.getTmplInsertName())) {
				txt += "Template information : Insert name ：" + quoteMutagenesis.getTmplInsertName() + ",";
			}
			if (quoteMutagenesis.getTmplVectorSize() != null && !"".equals(quoteMutagenesis.getTmplVectorSize())) {
				txt += "  Insert size ：" + quoteMutagenesis.getTmplVectorSize() + ",";
			}
			if (quoteMutagenesis.getTmplVector() != null && !"".equals(quoteMutagenesis.getTmplVector())) {
				txt += "Vector name：" + quoteMutagenesis.getTmplVector() + ",";
			}

			if (quoteMutagenesis.getTmplSequence() != null && !"".equals(quoteMutagenesis.getTmplSequence())) {
				txt += "Insert sequence ： " + System.getProperty("line.separator")
						+ quoteMutagenesis.getTmplSequence() + System.getProperty("line.separator");
			}

			if (quoteMutagenesis.getVariantName() != null && !"".equals(quoteMutagenesis.getVariantName())) {
				txt += "Mutagenesis instruction ：  Variant name: " + quoteMutagenesis.getVariantName() + ",";
			}
			if (quoteMutagenesis.getPlasmidPrepFlag() != null && !"".equals(quoteMutagenesis.getPlasmidPrepFlag())) {
				if (quoteMutagenesis.getPlasmidPrepFlag().equals("Y")) {
					Integer parentId = quoteItemDao.getById(Integer.parseInt(itemId)).getParentId();
					if (parentId != null) {
						QuotePlasmidPreparation quotePlasmidPreparation = quotePlasmidPreparationDao.getById(parentId);
						String vectorName = quotePlasmidPreparation.getPlasmidName();
						txt += " Target vector: " + vectorName + ", Plasmid preparation:Standard delivery:"
								+ quotePlasmidPreparation.getPrepWeight() + quotePlasmidPreparation.getPrepWtUom();
					}
				}
			}
			if (quoteMutagenesis.getVariantSequence() != null && !"".equals(quoteMutagenesis.getVariantSequence())) {
				txt += "Variant sequence ：" + System.getProperty("line.separator") + quoteMutagenesis.getVariantSequence();
			}
		}
		return txt;
	}

}
