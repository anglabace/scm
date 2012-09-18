package com.genscript.gsscm.quote.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;

@Repository
public class QuotePlasmidPreparationDao extends
		HibernateDao<QuotePlasmidPreparation, Integer> {

	public String getPlasmidByItemId(Integer ItemNo, String itemId,
			String quoteNo, String name) {
		String sqls = "select q from QuotePlasmidPreparation q where q.quoteItemId=? and q.quoteNo=?";

		QuotePlasmidPreparation quotePlasmidPreparation = this.findUnique(sqls,
				Integer.parseInt(itemId), Integer.parseInt(quoteNo));
		String txt = "";
		if (name.equals("")) {
			txt += ">Item" + ItemNo + " PlasmidPreparation";
		} else {
			txt += ">Item" + ItemNo + "" + name + "--PlasmidPreparation";
		}
		if (quotePlasmidPreparation != null) {
			if (quotePlasmidPreparation.getPlasmidName() != null
					&& !"".equals(quotePlasmidPreparation.getPlasmidName())) {
				txt += " Name :" + quotePlasmidPreparation.getPlasmidName()
						+ ",";
			}
			if (quotePlasmidPreparation.getStartingMaterical() != null
					&& !"".equals(quotePlasmidPreparation
							.getStartingMaterical())) {
				txt += " Starting Material :"
						+ quotePlasmidPreparation.getStartingMaterical() + ",";
			}
			if (quotePlasmidPreparation.getPlasmidSize() != null
					&& !"".equals(quotePlasmidPreparation.getPlasmidSize())) {
				txt += " Size :" + quotePlasmidPreparation.getPlasmidSize()
						+ ",";
			}
			if (quotePlasmidPreparation.getAntibioticResistance() != null
					&& !"".equals(quotePlasmidPreparation
							.getAntibioticResistance())) {

				txt += " Resistance :"
						+ quotePlasmidPreparation.getAntibioticResistance()
						+ ",";
			}

			if (quotePlasmidPreparation.getCopyNumber() != null
					&& !"".equals(quotePlasmidPreparation.getCopyNumber())) {

				txt += " Copy number :"
						+ quotePlasmidPreparation.getCopyNumber() + ",";
			}

			if (quotePlasmidPreparation.getTypicalYield() != null
					&& !"".equals(quotePlasmidPreparation.getTypicalYield())) {

				txt += " Typical yield: "
						+ quotePlasmidPreparation.getTypicalYield() + ",";
			}

			if (quotePlasmidPreparation.getRestrictionAnalysis() != null
					&& !"".equals(quotePlasmidPreparation
							.getRestrictionAnalysis())) {

				txt += " Restriction Analysis: "
						+ quotePlasmidPreparation.getRestrictionAnalysis()
						+ ",";
			}

			if (quotePlasmidPreparation.getFragment() != null
					&& !"".equals(quotePlasmidPreparation.getFragment())) {

				txt += " Fragment: " + quotePlasmidPreparation.getFragment()
						+ ",";
			}
			// 没有quality

			if (quotePlasmidPreparation.getQualityGrade() != null
					&& !"".equals(quotePlasmidPreparation.getQualityGrade())) {

				txt += " Quality grade: "
						+ quotePlasmidPreparation.getQualityGrade() + ",";
			}

			if (quotePlasmidPreparation.getAdditionalAnalysis() != null
					&& !"".equals(quotePlasmidPreparation
							.getAdditionalAnalysis())) {

				txt += "  Additional Analysis: "
						+ quotePlasmidPreparation.getAdditionalAnalysis() + ",";
			}

			if (quotePlasmidPreparation.getStorageCondition() != null
					&& !"".equals(quotePlasmidPreparation.getStorageCondition())) {

				txt += "  Storage condition: "
						+ quotePlasmidPreparation.getStorageCondition() + ",";
			}

			if (quotePlasmidPreparation.getDesiredBuffer() != null
					&& !"".equals(quotePlasmidPreparation.getDesiredBuffer())) {

				txt += "  Desired Buffer : "
						+ quotePlasmidPreparation.getDesiredBuffer() + ",";
			}

			if (quotePlasmidPreparation.getRequiredConcentration() != null
					&& !"".equals(quotePlasmidPreparation
							.getRequiredConcentration())) {

				txt += "  Required concentration : "
						+ quotePlasmidPreparation.getRequiredConcentration()
						+ ",";
			}

			if (quotePlasmidPreparation.getAliquotSamples() != null
					&& !"".equals(quotePlasmidPreparation.getAliquotSamples())) {

				txt += " Aliquot samples : "
						+ quotePlasmidPreparation.getAliquotSamples() + ",";
			}

			if (quotePlasmidPreparation.getOtherRequirement() != null
					&& !"".equals(quotePlasmidPreparation.getOtherRequirement())) {

				txt += " Other requirements: "
						+ quotePlasmidPreparation.getOtherRequirement() + ",\n";
			}

			if (quotePlasmidPreparation.getSequence() != null
					&& !"".equals(quotePlasmidPreparation.getSequence())) {

				txt += System.getProperty("line.separator") + " Sequence:  "
						+ System.getProperty("line.separator")
						+ quotePlasmidPreparation.getSequence();
			}

		}

		return txt;
	}

}
