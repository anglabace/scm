package com.genscript.gsscm.common.util;

public class MoreDetailUtil {
	private static final String customService = "customService";
	private static final String peptide = "peptide";
	private static final String gene = "gene";
	private static final String muta = "muta";
	private static final String mutaLib = "mutaLib";
	private static final String orfClone = "orfClone";
	private static final String rna = "rna";
	private static final String cloning = "cloning";
	private static final String plasmid = "plasmid";
	private static final String antibody = "antibody";
	private static final String arrayPeptide = "arrayPeptide";
	private static final String librayPeptide = "librayPeptide";
	private static final String oligo = "oligo";
	private static final String sequencing = "sequencing";
	private static final String pro = "pro";
	private static final String prosub = "prosub";
	private static final String geneSynthesis = "geneSynthesis";
	private static final String mutagenesis = "mutagenesis";
	private static final String custCloning = "custCloning";
	private static final String pkgService = "pkgService";
	private static final String plasmidPreparation = "plasmidPreparation";
	private static final String Antibody_Pep = "Antibody-Pep";
	
	public static String getShow(Integer clsId) {
		if (MoreDetailUtil.isProteinGroupService(clsId)) {
			if(MoreDetailUtil.isProteinGroupParent(clsId)){
				return pro;
			}else{
				return prosub;
			}
		}
		String show = "";
		switch (clsId) {
		case -1:
			show = customService;
			break;
		case 1:
			show = peptide;
			break;
		case 3:
			show = gene;
			break;
		case 4:
			show = muta;
			break;
		case 5:
			show = mutaLib;
			break;	
		case 6:
			show = orfClone;
			break;	
		case 7:
			show = rna;
			break;
		case 8:
			show = rna;
			break;
		case 9:
			show = cloning;
			break;
		case 10:
			show = plasmid;
			break;
		case 11:
			show = antibody;
			break;
		case 12:
			show = antibody;
			break;
		case 28:
			show = antibody;
			break;
		case 29:
			show = customService;
			break;	
		case 30:
			show = arrayPeptide;
			break;
		case 31:
			show = librayPeptide;
			break;
		case 34:
			show = oligo;
			break;
		case 35:
			show = customService;
			break;
		case 36:
			show = customService;
			break;
		case 37:
			show = customService;
			break;
		case 38:
			show = customService;
			break;
		case 39:
			show = customService;
			break;
		case 40:
			show = sequencing;
			break;
		case 42:
			show = customService;
			break;
		default:
			break;
		}
		return show;
	}

	// 判断是否是属于Protein&Bioprocess&Pharmeceutical&Antibody drug
	public static boolean isProteinGroupService(Integer clsId) {
		if (clsId == null) {
			return false;
		}
		int clsIdIntValue = clsId.intValue();
		if (clsIdIntValue == 2 || clsIdIntValue == 13 || clsIdIntValue == 14
				|| clsIdIntValue == 15 || clsIdIntValue == 16
				|| clsIdIntValue == 17 || clsIdIntValue == 18
				|| clsIdIntValue == 19 || clsIdIntValue == 32
				|| clsIdIntValue == 33) {
			return true;
		} else {
			return false;
		}
	}

	// 判断是否是属于Protein&Bioprocess&Pharmeceutical&Antibody drug
	public static boolean isProteinGroupParent(Integer clsId) {
		if (clsId == null) {
			return false;
		}
		int clsIdIntValue = clsId.intValue();
		if (clsIdIntValue == 2 || clsIdIntValue == 14 || clsIdIntValue == 16
				|| clsIdIntValue == 18) {
			return true;
		} else {
			return false;
		}
	}
	
	// 判断是否是属于Protein&Bioprocess&Pharmeceutical&Antibody drug  step
	public static boolean isProteinGroupStep(Integer clsId) {
		if (clsId == null) {
			return false;
		}
		int clsIdIntValue = clsId.intValue();
		if (clsIdIntValue == 13 || clsIdIntValue == 15 || clsIdIntValue == 17
				|| clsIdIntValue == 19) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否是父item
	 * 
	 * @param clsId
	 * @return
	 */
	public static boolean isOnlyParent(Integer clsId) {
		//判断是否是属于Protein&Bioprocess&Pharmeceutical&Antibody drug 的父级service
		if(MoreDetailUtil.isProteinGroupParent(clsId)){
			return true;
		}
		String show = MoreDetailUtil.getShow(clsId);
		if (show.equals(mutaLib) || show.equals(muta) || show.equals(gene) || show.equals(rna)
				 || show.equals(oligo) || show.equals(sequencing) || show.equals(orfClone)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getServiceNameByClsId(Integer clsId) {
		if (MoreDetailUtil.isProteinGroupService(clsId)) {
			return pkgService;
		}
		String serviceName = "";
		switch (clsId) {
		case -1:
			serviceName = customService;
			break;
		case 1:
			serviceName = peptide;
			break;
		case 3:
			serviceName = geneSynthesis;
			break;
		case 4:
			serviceName = mutagenesis;
			break;
		case 5:
			serviceName = mutaLib;
			break;	
		case 6:
			serviceName = orfClone;
			break;	
		case 7:
			serviceName = rna;
			break;
		case 8:
			serviceName = rna;
			break;
		case 9:
			serviceName = custCloning;
			break;
		case 10:
			serviceName = plasmidPreparation;
			break;
		case 11:
			serviceName = antibody;
			break;
		case 12:
			serviceName = antibody;
			break;
		case 28:
			serviceName = antibody;
			break;
		case 29:
			serviceName = customService;
			break;		
		case 30:
			serviceName = arrayPeptide;
			break;	
		case 31:
			serviceName = librayPeptide;
			break;	
		case 34:
			serviceName = oligo;
			break;
		case 35:
			serviceName = customService;
			break;
		case 36:
			serviceName = customService;
			break;
		case 37:
			serviceName = customService;
			break;
		case 38:
			serviceName = customService;
			break;
		case 39:
			serviceName = customService;
			break;
		case 40:
			serviceName = sequencing;
			break;
		case 42:
			serviceName = customService;
			break;	
		default:
			break;
		}
		return serviceName;
	}

	public static String getIntmdKeyword(String serviceName) {
		String intmdKeyword = "";
		if (serviceName.equals(peptide)) {
			intmdKeyword = peptide;
		} else if (serviceName.equals(geneSynthesis)) {
			intmdKeyword = "";
		} else if (serviceName.equals(mutagenesis)) {
			intmdKeyword = "";
		} else if (serviceName.equals(custCloning)) {
			intmdKeyword = "CloningStrategy";
		} else if (serviceName.equals(plasmidPreparation)) {
			intmdKeyword = plasmidPreparation;
		} else if(serviceName.equals(antibody)){
			intmdKeyword = antibody;
		} else if(serviceName.equals(Antibody_Pep)){
			intmdKeyword = Antibody_Pep;
		} else if(serviceName.equals(oligo)){
			intmdKeyword = oligo;
		} else if(serviceName.equals(customService)){
			intmdKeyword = "Custom Service";	
		} else if(serviceName.equals("PRE-IMMUNE")){
			intmdKeyword = "PRE-IMMUNE";
		} else if (serviceName.equals(mutaLib)) {
			intmdKeyword = "Mutation Libraries";
		} 
		return intmdKeyword;
	}
	
	public static String getDefaultAntigenType(){
		return "GenScript Synthesized Peptide";
	}

}
