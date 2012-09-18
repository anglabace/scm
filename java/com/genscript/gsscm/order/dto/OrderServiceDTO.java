package com.genscript.gsscm.order.dto;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;

@XmlType(name = "OrderServiceDTO", namespace = WsConstants.NS)
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
	private String orfCloneItemId;
	private OrderMutagenesisDTO mutagenesis;
	private String mutagenesisItemId;
	private PeptideDTO peptide;
	private String peptideItemId;
	private Map<String, OrderMutagenesisDTO> mutagenesisMap;
	private Map<String, OrderPkgServiceDTO> orderPkgServiceMap;
	private String antibodyItemId;
	private AntibodyDTO antibody;
	private Map<String, PeptideDTO> peptideMap;

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

	public Map<String, OrderPkgServiceDTO> getOrderPkgServiceMap() {
		return orderPkgServiceMap;
	}

	public void setOrderPkgServiceMap(
			Map<String, OrderPkgServiceDTO> orderPkgServiceMap) {
		this.orderPkgServiceMap = orderPkgServiceMap;
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
}
