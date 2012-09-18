package com.genscript.gsscm.common.constant;




public enum ServiceDetailsType {
	ANTIBODY("antibody"),
	OLIGO("oligo"),
	ANTIBODYDRUG("antibody drug"),
	ANTIBODYDRUGSTEP("antibody drug step"),
	BIOMARKER("biomarker"),
	BIOMARKERSTEP("biomarker Step"),
	BIOPROCESS("bioprocess"),
	BIOPROCESSSTEP("bioprocess step"),
	CUSTOMCLONING("custom cloning"),
	GENE("gene synthesis"),
	MIRNA("mirna"),
	MONOCLONALANTIBODY("monoclonal antibody"),
	MUTAGENESIS("mutagenesis"),
	MUTATIONLIBRARIES("mutation libraries"),
	ORFCLONE("orf clone"),
	PEPTIDE("peptide"),
	PHARMACEUTICAL("pharmaceutical"),
	PHARMACEUTICALSTEP("pharmaceutical step"),
	PLASMIDPREPARATION("plasmid preparation"),
	POLYCLONALANTIBODYFORMONOCLONALGROUP("polyclonal antibody for monoclonal group"),
	POLYCLONALANTIBODYFORPOLYCLONALGROUP("polyclonal antibody for polyclonal group"),
	PROTEIN("protein"),
	PROTEINSTEP("protein step"),
	SIRNA("sirna"),
	customServicesAnimalModel("custom services-animal model"),
	customServicesAntibody("custom services-antibody"),
	customServicesBiomarker("custom services-biomarker"),
	customServicesGene("custom services-gene"),
	biomarker("biomarker"),
	customServicesPeptide("custom services-peptide")
	;
	private final String value;

	ServiceDetailsType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ServiceDetailsType fromValue(String v) {
		for (ServiceDetailsType c : ServiceDetailsType.values()) {
			if (c.value.equals(v)) {
				return c;

			}
		}
		throw new IllegalArgumentException(v);
	}
}
