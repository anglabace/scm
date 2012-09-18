package com.genscript.gsscm.quote.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteMutationLibraries;

@Repository
public class QuoteMutationLibrariesDao extends
		HibernateDao<QuoteMutationLibraries, Integer> {
	public String getMutationByItemId(Integer ItemNo, String itemId,
			String quoteNo, String name) {
		String sqls = "select q from QuoteMutationLibraries q where q.quoteItemId=? and q.quoteNo=?";
		String txt = "";
		if (name.equals("")) {
			txt += ">Item " + ItemNo + " MutationLibraries";
		} else {
			txt += ">Item " + ItemNo + "" + name + "--MutationLibraries";
		}

		QuoteMutationLibraries quoteMutationLibraries = this.findUnique(sqls,
				Integer.parseInt(itemId), Integer.parseInt(quoteNo));
		if (quoteMutationLibraries != null
				&& !"".equals(quoteMutationLibraries)) {
			if (quoteMutationLibraries.getConstructName() != null
					&& !"".equals(quoteMutationLibraries.getConstructName())) {
				txt += " Construct name :"
						+ quoteMutationLibraries.getConstructName() + ", ";
			}
			if (quoteMutationLibraries.getLibraryType() != null
					&& !"".equals(quoteMutationLibraries.getLibraryType())) {
				txt += " Library type :"
						+ quoteMutationLibraries.getLibraryType() + ", ";
			}
			if (quoteMutationLibraries.getOtherRequirement() != null
					&& !"".equals(quoteMutationLibraries.getOtherRequirement())) {
				txt += " Other requirements :"
						+ quoteMutationLibraries.getOtherRequirement() + ", ";
			}
			if (quoteMutationLibraries.getInterestSequence() != null
					&& !"".equals(quoteMutationLibraries.getInterestSequence())) {
				txt += System.getProperty("line.separator")
						+ " Sequence of interest: "
						+ System.getProperty("line.separator")
						+ quoteMutationLibraries.getInterestSequence();

			}

		}
		return txt;
	}
}
