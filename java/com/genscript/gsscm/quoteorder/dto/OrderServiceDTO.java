package com.genscript.gsscm.quoteorder.dto;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ServiceDTO", namespace = WsConstants.NS)
public class OrderServiceDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4345158454466789791L;
	/**
	 * 
	 */
	private OrderGeneSynthesisDTO geneSynthesis;
	private String geneSynthesisItemId;
	private OrderCustCloningDTO custCloning;
	private String custCloningItemId;
	private OrderPlasmidPreparationDTO plasmidPreparation;
	private String plasmidPreparationItemId;
	private OrderOrfCloneDTO orfClone;
	private CustomServiceDTO customService;
	private String orfCloneItemId;
	private OrderMutagenesisDTO mutagenesis;
	private String mutagenesisItemId;
	private OrderMutationLibrariesDTO mutationLibraries;
	private String mutationLibrariesItemId;
	private PeptideDTO peptide;
	private String peptideItemId;
	private Map<String, OrderMutagenesisDTO> mutagenesisMap;
	private Map<String, PkgServiceDTO> pkgServiceMap;
	private String pkgServiceId;
	private String antibodyItemId;
	private AntibodyDTO antibody;
	private Map<String, PeptideDTO> peptideMap;
	private RnaDTO rna;
	private String rnaItemId;
	private OrderOligoDTO oligo;
	private String oligoItemId;
	private String customServiceItemId;
	private String parentId;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public OrderGeneSynthesisDTO getGeneSynthesis() {
		return geneSynthesis;
	}

	public void setGeneSynthesis(OrderGeneSynthesisDTO geneSynthesis) {
		this.geneSynthesis = geneSynthesis;
	}

	public String getGeneSynthesisItemId() {
		return geneSynthesisItemId;
	}

	public void setGeneSynthesisItemId(String geneSynthesisItemId) {
		this.geneSynthesisItemId = geneSynthesisItemId;
	}

	public OrderCustCloningDTO getCustCloning() {
		return custCloning;
	}

	public void setCustCloning(OrderCustCloningDTO custCloning) {
		this.custCloning = custCloning;
	}

	public String getCustCloningItemId() {
		return custCloningItemId;
	}

	public void setCustCloningItemId(String custCloningItemId) {
		this.custCloningItemId = custCloningItemId;
	}

	public OrderPlasmidPreparationDTO getPlasmidPreparation() {
		return plasmidPreparation;
	}

	public void setPlasmidPreparation(OrderPlasmidPreparationDTO plasmidPreparation) {
		this.plasmidPreparation = plasmidPreparation;
	}

	public String getPlasmidPreparationItemId() {
		return plasmidPreparationItemId;
	}

	public void setPlasmidPreparationItemId(String plasmidPreparationItemId) {
		this.plasmidPreparationItemId = plasmidPreparationItemId;
	}

	public OrderOrfCloneDTO getOrfClone() {
		return orfClone;
	}

	public void setOrfClone(OrderOrfCloneDTO orfClone) {
		this.orfClone = orfClone;
	}

	public String getOrfCloneItemId() {
		return orfCloneItemId;
	}

	public void setOrfCloneItemId(String orfCloneItemId) {
		this.orfCloneItemId = orfCloneItemId;
	}

	public OrderMutagenesisDTO getMutagenesis() {
		return mutagenesis;
	}

	public void setMutagenesis(OrderMutagenesisDTO mutagenesis) {
		this.mutagenesis = mutagenesis;
	}

	public String getMutagenesisItemId() {
		return mutagenesisItemId;
	}

	public void setMutagenesisItemId(String mutagenesisItemId) {
		this.mutagenesisItemId = mutagenesisItemId;
	}

	public PeptideDTO getPeptide() {
		return peptide;
	}

	public void setPeptide(PeptideDTO peptide) {
		this.peptide = peptide;
	}

	public String getPeptideItemId() {
		return peptideItemId;
	}

	public void setPeptideItemId(String peptideItemId) {
		this.peptideItemId = peptideItemId;
	}

	public Map<String, OrderMutagenesisDTO> getMutagenesisMap() {
		return mutagenesisMap;
	}

	public void setMutagenesisMap(Map<String, OrderMutagenesisDTO> mutagenesisMap) {
		this.mutagenesisMap = mutagenesisMap;
	}

	public String getAntibodyItemId() {
		return antibodyItemId;
	}

	public void setAntibodyItemId(String antibodyItemId) {
		this.antibodyItemId = antibodyItemId;
	}

	public AntibodyDTO getAntibody() {
		return antibody;
	}

	public void setAntibody(AntibodyDTO antibody) {
		this.antibody = antibody;
	}

	public Map<String, PeptideDTO> getPeptideMap() {
		return peptideMap;
	}

	public void setPeptideMap(Map<String, PeptideDTO> peptideMap) {
		this.peptideMap = peptideMap;
	}

	public Map<String, PkgServiceDTO> getPkgServiceMap() {
		return pkgServiceMap;
	}

	public void setPkgServiceMap(Map<String, PkgServiceDTO> pkgServiceMap) {
		this.pkgServiceMap = pkgServiceMap;
	}

	public RnaDTO getRna() {
		return rna;
	}

	public void setRna(RnaDTO rna) {
		this.rna = rna;
	}

	public String getRnaItemId() {
		return rnaItemId;
	}

	public void setRnaItemId(String rnaItemId) {
		this.rnaItemId = rnaItemId;
	}

	public OrderOligoDTO getOligo() {
		return oligo;
	}

	public void setOligo(OrderOligoDTO oligo) {
		this.oligo = oligo;
	}

	public String getOligoItemId() {
		return oligoItemId;
	}

	public void setOligoItemId(String oligoItemId) {
		this.oligoItemId = oligoItemId;
	}

	public CustomServiceDTO getCustomService() {
		return customService;
	}

	public void setCustomService(CustomServiceDTO customService) {
		this.customService = customService;
	}

	public String getCustomServiceItemId() {
		return customServiceItemId;
	}

	public void setCustomServiceItemId(String customServiceItemId) {
		this.customServiceItemId = customServiceItemId;
	}

	public String getPkgServiceId() {
		return pkgServiceId;
	}

	public void setPkgServiceId(String pkgServiceId) {
		this.pkgServiceId = pkgServiceId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the mutationLibraries
	 */
	public OrderMutationLibrariesDTO getMutationLibraries() {
		return mutationLibraries;
	}

	/**
	 * @param mutationLibraries the mutationLibraries to set
	 */
	public void setMutationLibraries(OrderMutationLibrariesDTO mutationLibraries) {
		this.mutationLibraries = mutationLibraries;
	}

	/**
	 * @return the mutationLibrariesItemId
	 */
	public String getMutationLibrariesItemId() {
		return mutationLibrariesItemId;
	}

	/**
	 * @param mutationLibrariesItemId the mutationLibrariesItemId to set
	 */
	public void setMutationLibrariesItemId(String mutationLibrariesItemId) {
		this.mutationLibrariesItemId = mutationLibrariesItemId;
	}
	
}
