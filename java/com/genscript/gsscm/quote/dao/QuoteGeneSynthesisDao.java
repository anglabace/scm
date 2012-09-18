package com.genscript.gsscm.quote.dao;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.quote.entity.QuoteGeneSynthesis;

@Repository
public class QuoteGeneSynthesisDao extends
		HibernateDao<QuoteGeneSynthesis, Integer> {
	

	public String getGeneSynthesisByItemId(Integer ItemNo, String itemId, String quoteNo,String name) {
		String txt = "";
		String sql = "select q from QuoteGeneSynthesis q where q.quoteItemId=? and q.quoteNo=? ";

		QuoteGeneSynthesis quoteGeneSynthesis = this.findUnique(sql,Integer.parseInt(itemId),
				Integer.parseInt(quoteNo));
		if(name.equals("")){
			txt +=">item "+ItemNo+" GeneSynthesis";
		}
		else{
			txt +=">item "+ItemNo+" "+name +"--GeneSynthesis";
		}
		if (quoteGeneSynthesis != null && !"".equals(quoteGeneSynthesis)) {
			if (quoteGeneSynthesis.getGeneName() != null
					&& !"".equals(quoteGeneSynthesis.getGeneName())) {
				txt += "Gene name :" + quoteGeneSynthesis.getGeneName()
						+ ",";
			}

			if (quoteGeneSynthesis.getSeqLength() != null
					&& !"".equals(quoteGeneSynthesis.getSeqLength())) {
				txt += " Length :" + quoteGeneSynthesis.getSeqLength() + ",";
			}

			if (quoteGeneSynthesis.getStdVectorName() != null
					&& !"".equals(quoteGeneSynthesis.getStdVectorName())) {
				txt += " Vector name :" + quoteGeneSynthesis.getStdVectorName()
						+ ",";
			}

			if (quoteGeneSynthesis.getSequence5() != null
					&& !"".equals(quoteGeneSynthesis.getSequence5())) {
				txt += " Additional 5' sequence :"
						+ quoteGeneSynthesis.getSequence5() + ",";
			}

			if (quoteGeneSynthesis.getSequence3() != null
					&& !"".equals(quoteGeneSynthesis.getSequence3())) {
				txt += " Additional 3' sequence:"
						+ quoteGeneSynthesis.getSequence3() + ",";
			}

			if (quoteGeneSynthesis.getCodeOptmzFlag() != null
					&& !"".equals(quoteGeneSynthesis.getCodeOptmzFlag())) {
				txt += "Codon optimization ："
						+ quoteGeneSynthesis.getCodeOptmzFlag() + ",";
			}

			if (quoteGeneSynthesis.getHostExpsOrganism() != null
					&& !"".equals(quoteGeneSynthesis.getHostExpsOrganism())) {
				txt += "Host expression organism :"
						+ quoteGeneSynthesis.getHostExpsOrganism() + ",";
			}

			if (quoteGeneSynthesis.getScndExpsOrganism() != null
					&& !"".equals(quoteGeneSynthesis.getScndExpsOrganism())) {
				txt += "Secondary expression organism :"
						+ quoteGeneSynthesis.getScndExpsOrganism() + ",";
			}

			if (quoteGeneSynthesis.getOpStartPosUom() != null
					&& !"".equals(quoteGeneSynthesis.getOpStartPosUom())) {
				txt += "Optimization start position :"
						+ quoteGeneSynthesis.getOpStartPosUom() + ",";
			}

			if (quoteGeneSynthesis.getOrfEnd() != null
					&& !"".equals(quoteGeneSynthesis.getOrfEnd())) {
				txt += "End position :" + quoteGeneSynthesis.getOrfEnd() + ",";
			}

			if (quoteGeneSynthesis.getRstSitesAvoid() != null
					&& !"".equals(quoteGeneSynthesis.getRstSitesAvoid())) {
				txt += "Restriction sites to avoid :"
						+ quoteGeneSynthesis.getRstSitesAvoid() + ",";
			}
			if (quoteGeneSynthesis.getRstSitesKeep() != null
					&& !"".equals(quoteGeneSynthesis.getRstSitesKeep())) {
				txt += "Restriction sites to keep :"
						+ quoteGeneSynthesis.getRstSitesKeep() + ",";
			}
			if (quoteGeneSynthesis.getStopCodonFlag() != null
					&& !"".equals(quoteGeneSynthesis.getStopCodonFlag())) {
				txt += "Stop codon needed :"
						+ quoteGeneSynthesis.getStopCodonFlag() + ",";
			}
			if (quoteGeneSynthesis.getCloningFlag() != null
					&& !"".equals(quoteGeneSynthesis.getCloningSite())) {
				if(quoteGeneSynthesis.getCloningFlag().equals("Y")){
				txt += "Cloning strategy : other ";
				}else{
					txt += "Cloning strategy : NULL ";
				}
			}
			if (quoteGeneSynthesis.getCloningSite() != null
					&& !"".equals(quoteGeneSynthesis.getCloningSite())) {
				txt += "Cloning site :"
						+ quoteGeneSynthesis.getCloningSite();
			}
			if (quoteGeneSynthesis.getPlasmidPrepFlag() != null
					&& !"".equals(quoteGeneSynthesis.getPlasmidPrepFlag())) {
				if(quoteGeneSynthesis.getPlasmidPrepFlag().equals("N")){
				txt += " Plasmid preparation :Standard delivery: "
						+ quoteGeneSynthesis.getStdPlasmidWt()+""+quoteGeneSynthesis.getStdPlasmidWtUom()+" (Free of charge)";
				}else{
					txt += " Plasmid preparation :"
							+ quoteGeneSynthesis.getStdPlasmidWt()+""+quoteGeneSynthesis.getStdPlasmidWtUom();
				}
			}
			
			if (quoteGeneSynthesis.getSequence() != null
					&& !"".equals(quoteGeneSynthesis.getSequence())) {
				txt += "\r\n Sequence : " + System.getProperty("line.separator")
						+ quoteGeneSynthesis.getSequence();
			}

		}
		return txt;
	}

}
