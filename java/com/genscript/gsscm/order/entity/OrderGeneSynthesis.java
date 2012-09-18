package com.genscript.gsscm.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrderGeneSynthesis
 * 
 * 
 * @author Wangsf
 */

@Entity
@Table(name = "order_gene_synthesis", catalog = "order")
public class OrderGeneSynthesis extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3547609753621318701L;
	@Id
	private Integer orderItemId;
	private Integer orderNo;
	private String geneName;
	@Column(name = "5sequence")
	private String sequence5;
	private String sequenceType;
	private String sequence;
	private String seqLength;
	@Column(name = "3sequence")
	private String sequence3;
	private String codeOptmzFlag;
	private String hostExpsOrganism;
	private String scndExpsOrganism;
	private Integer optimizationStart;
	private String opStartPosUom;
	private Integer optimizationEnd;
	private String opEndPosUom;
	private Integer orfStart;
	private String orfStartUom;
	private Integer orfEnd;
	private String orfEndUom;
	private String rstSitesAvoid;
	private String rstSitesKeep;
	private String stopCodonFlag;
	private String stopCodon;
	private String comment;
	private String stdVectorName;
	private String cloningSite;
	private String cloningFlag;
	private Integer stdPlasmidWt;
	private String stdPlasmidWtUom;
	private String plasmidPrepFlag;
	private String mutagenesisFlag;
	private String geneUsage;
	private String readingFrame;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;
	private Double bpPrice;
	private Double bpCost;
	private String dfcltSeqFlag;
	private String virusSeqFlag;
    private String vectorSize;
    private String vectorResistance;
    private String vectorCopyNo;
    private String vectorSeq;
    private String vectorMapDocFlag;
    private String cloningReadyFlag;
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	/**
	 * @return the orderItemId
	 */
	public Integer getOrderItemId() {
		return orderItemId;
	}
	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

    public String getVectorSize() {
        return vectorSize;
    }

    public void setVectorSize(String vectorSize) {
        this.vectorSize = vectorSize;
    }

    public String getVectorResistance() {
        return vectorResistance;
    }

    public void setVectorResistance(String vectorResistance) {
        this.vectorResistance = vectorResistance;
    }

    public String getVectorCopyNo() {
        return vectorCopyNo;
    }

    public void setVectorCopyNo(String vectorCopyNo) {
        this.vectorCopyNo = vectorCopyNo;
    }

    public String getVectorSeq() {
        return vectorSeq;
    }

    public void setVectorSeq(String vectorSeq) {
        this.vectorSeq = vectorSeq;
    }

    public String getVectorMapDocFlag() {
        return vectorMapDocFlag;
    }

    public void setVectorMapDocFlag(String vectorMapDocFlag) {
        this.vectorMapDocFlag = vectorMapDocFlag;
    }

    /**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the geneName
	 */
	public String getGeneName() {
		return geneName;
	}
	/**
	 * @param geneName the geneName to set
	 */
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}
	/**
	 * @return the sequence5
	 */
	public String getSequence5() {
		return sequence5;
	}
	/**
	 * @param sequence5 the sequence5 to set
	 */
	public void setSequence5(String sequence5) {
		this.sequence5 = sequence5;
	}
	/**
	 * @return the sequenceType
	 */
	public String getSequenceType() {
		return sequenceType;
	}
	/**
	 * @param sequenceType the sequenceType to set
	 */
	public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}
	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the sequence3
	 */
	public String getSequence3() {
		return sequence3;
	}
	/**
	 * @param sequence3 the sequence3 to set
	 */
	public void setSequence3(String sequence3) {
		this.sequence3 = sequence3;
	}
	/**
	 * @return the codeOptmzFlag
	 */
	public String getCodeOptmzFlag() {
		return codeOptmzFlag;
	}
	/**
	 * @param codeOptmzFlag the codeOptmzFlag to set
	 */
	public void setCodeOptmzFlag(String codeOptmzFlag) {
		this.codeOptmzFlag = codeOptmzFlag;
	}
	/**
	 * @return the hostExpsOrganism
	 */
	public String getHostExpsOrganism() {
		return hostExpsOrganism;
	}
	/**
	 * @param hostExpsOrganism the hostExpsOrganism to set
	 */
	public void setHostExpsOrganism(String hostExpsOrganism) {
		this.hostExpsOrganism = hostExpsOrganism;
	}
	/**
	 * @return the scndExpsOrganism
	 */
	public String getScndExpsOrganism() {
		return scndExpsOrganism;
	}
	/**
	 * @param scndExpsOrganism the scndExpsOrganism to set
	 */
	public void setScndExpsOrganism(String scndExpsOrganism) {
		this.scndExpsOrganism = scndExpsOrganism;
	}
	/**
	 * @return the optimizationStart
	 */
	public Integer getOptimizationStart() {
		return optimizationStart;
	}
	/**
	 * @param optimizationStart the optimizationStart to set
	 */
	public void setOptimizationStart(Integer optimizationStart) {
		this.optimizationStart = optimizationStart;
	}
	/**
	 * @return the opStartPosUom
	 */
	public String getOpStartPosUom() {
		return opStartPosUom;
	}
	/**
	 * @param opStartPosUom the opStartPosUom to set
	 */
	public void setOpStartPosUom(String opStartPosUom) {
		this.opStartPosUom = opStartPosUom;
	}
	/**
	 * @return the optimizationEnd
	 */
	public Integer getOptimizationEnd() {
		return optimizationEnd;
	}
	/**
	 * @param optimizationEnd the optimizationEnd to set
	 */
	public void setOptimizationEnd(Integer optimizationEnd) {
		this.optimizationEnd = optimizationEnd;
	}
	/**
	 * @return the opEndPosUom
	 */
	public String getOpEndPosUom() {
		return opEndPosUom;
	}
	/**
	 * @param opEndPosUom the opEndPosUom to set
	 */
	public void setOpEndPosUom(String opEndPosUom) {
		this.opEndPosUom = opEndPosUom;
	}
	/**
	 * @return the orfStart
	 */
	public Integer getOrfStart() {
		return orfStart;
	}
	/**
	 * @param orfStart the orfStart to set
	 */
	public void setOrfStart(Integer orfStart) {
		this.orfStart = orfStart;
	}
	/**
	 * @return the orfStartUom
	 */
	public String getOrfStartUom() {
		return orfStartUom;
	}
	/**
	 * @param orfStartUom the orfStartUom to set
	 */
	public void setOrfStartUom(String orfStartUom) {
		this.orfStartUom = orfStartUom;
	}
	/**
	 * @return the orfEnd
	 */
	public Integer getOrfEnd() {
		return orfEnd;
	}
	/**
	 * @param orfEnd the orfEnd to set
	 */
	public void setOrfEnd(Integer orfEnd) {
		this.orfEnd = orfEnd;
	}
	/**
	 * @return the orfEndUom
	 */
	public String getOrfEndUom() {
		return orfEndUom;
	}
	/**
	 * @param orfEndUom the orfEndUom to set
	 */
	public void setOrfEndUom(String orfEndUom) {
		this.orfEndUom = orfEndUom;
	}
	/**
	 * @return the rstSitesAvoid
	 */
	public String getRstSitesAvoid() {
		return rstSitesAvoid;
	}
	/**
	 * @param rstSitesAvoid the rstSitesAvoid to set
	 */
	public void setRstSitesAvoid(String rstSitesAvoid) {
		this.rstSitesAvoid = rstSitesAvoid;
	}
	/**
	 * @return the rstSitesKeep
	 */
	public String getRstSitesKeep() {
		return rstSitesKeep;
	}
	/**
	 * @param rstSitesKeep the rstSitesKeep to set
	 */
	public void setRstSitesKeep(String rstSitesKeep) {
		this.rstSitesKeep = rstSitesKeep;
	}
	/**
	 * @return the stopCodonFlag
	 */
	public String getStopCodonFlag() {
		return stopCodonFlag;
	}
	/**
	 * @param stopCodonFlag the stopCodonFlag to set
	 */
	public void setStopCodonFlag(String stopCodonFlag) {
		this.stopCodonFlag = stopCodonFlag;
	}
	/**
	 * @return the stopCodon
	 */
	public String getStopCodon() {
		return stopCodon;
	}
	/**
	 * @param stopCodon the stopCodon to set
	 */
	public void setStopCodon(String stopCodon) {
		this.stopCodon = stopCodon;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the stdVectorName
	 */
	public String getStdVectorName() {
		return stdVectorName;
	}
	/**
	 * @param stdVectorName the stdVectorName to set
	 */
	public void setStdVectorName(String stdVectorName) {
		this.stdVectorName = stdVectorName;
	}
	/**
	 * @return the cloningSite
	 */
	public String getCloningSite() {
		return cloningSite;
	}
	/**
	 * @param cloningSite the cloningSite to set
	 */
	public void setCloningSite(String cloningSite) {
		this.cloningSite = cloningSite;
	}
	/**
	 * @return the cloningFlag
	 */
	public String getCloningFlag() {
		return cloningFlag;
	}
	/**
	 * @param cloningFlag the cloningFlag to set
	 */
	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}
	/**
	 * @return the stdPlasmidWt
	 */
	public Integer getStdPlasmidWt() {
		return stdPlasmidWt;
	}
	/**
	 * @param stdPlasmidWt the stdPlasmidWt to set
	 */
	public void setStdPlasmidWt(Integer stdPlasmidWt) {
		this.stdPlasmidWt = stdPlasmidWt;
	}
	/**
	 * @return the stdPlasmidWtUom
	 */
	public String getStdPlasmidWtUom() {
		return stdPlasmidWtUom;
	}
	/**
	 * @param stdPlasmidWtUom the stdPlasmidWtUom to set
	 */
	public void setStdPlasmidWtUom(String stdPlasmidWtUom) {
		this.stdPlasmidWtUom = stdPlasmidWtUom;
	}
	/**
	 * @return the plasmidPrepFlag
	 */
	public String getPlasmidPrepFlag() {
		return plasmidPrepFlag;
	}
	/**
	 * @param plasmidPrepFlag the plasmidPrepFlag to set
	 */
	public void setPlasmidPrepFlag(String plasmidPrepFlag) {
		this.plasmidPrepFlag = plasmidPrepFlag;
	}
	/**
	 * @return the mutagenesisFlag
	 */
	public String getMutagenesisFlag() {
		return mutagenesisFlag;
	}
	/**
	 * @param mutagenesisFlag the mutagenesisFlag to set
	 */
	public void setMutagenesisFlag(String mutagenesisFlag) {
		this.mutagenesisFlag = mutagenesisFlag;
	}
	/**
	 * @return the geneUsage
	 */
	public String getGeneUsage() {
		return geneUsage;
	}
	/**
	 * @param geneUsage the geneUsage to set
	 */
	public void setGeneUsage(String geneUsage) {
		this.geneUsage = geneUsage;
	}
	/**
	 * @return the readingFrame
	 */
	public String getReadingFrame() {
		return readingFrame;
	}
	/**
	 * @param readingFrame the readingFrame to set
	 */
	public void setReadingFrame(String readingFrame) {
		this.readingFrame = readingFrame;
	}
	/**
	 * @return the otherDescription
	 */
	public String getOtherDescription() {
		return otherDescription;
	}
	/**
	 * @param otherDescription the otherDescription to set
	 */
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	/**
	 * @return the otherRequirement
	 */
	public String getOtherRequirement() {
		return otherRequirement;
	}
	/**
	 * @param otherRequirement the otherRequirement to set
	 */
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}
	/**
	 * @return the serviceDocFlag
	 */
	public String getServiceDocFlag() {
		return serviceDocFlag;
	}
	/**
	 * @param serviceDocFlag the serviceDocFlag to set
	 */
	public void setServiceDocFlag(String serviceDocFlag) {
		this.serviceDocFlag = serviceDocFlag;
	}
	public String getSeqLength() {
		return seqLength;
	}
	public void setSeqLength(String seqLength) {
		this.seqLength = seqLength;
	}
	public Double getBpPrice() {
		return bpPrice;
	}
	public void setBpPrice(Double bpPrice) {
		this.bpPrice = bpPrice;
	}
	public Double getBpCost() {
		return bpCost;
	}
	public void setBpCost(Double bpCost) {
		this.bpCost = bpCost;
	}
	public String getDfcltSeqFlag() {
		return dfcltSeqFlag;
	}
	public void setDfcltSeqFlag(String dfcltSeqFlag) {
		this.dfcltSeqFlag = dfcltSeqFlag;
	}
	public String getVirusSeqFlag() {
		return virusSeqFlag;
	}
	public void setVirusSeqFlag(String virusSeqFlag) {
		this.virusSeqFlag = virusSeqFlag;
	}
	public String getCloningReadyFlag() {
		return cloningReadyFlag;
	}
	public void setCloningReadyFlag(String cloningReadyFlag) {
		this.cloningReadyFlag = cloningReadyFlag;
	}
}
