package com.genscript.gsscm.quoteorder.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.order.entity.Document;


@XmlType(name = "OrderGeneSynthesisDTO", namespace = WsConstants.NS)
public class OrderGeneSynthesisDTO {
	private Integer quoteItemId;
	private Integer quoteNo;
	private Integer orderItemId;
	private Integer orderNo;
	private String geneName;
	private String sequence5;
	private String sequenceType;
	private Integer seqLength;
	private String sequence;
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
	private String geneUsageText;
	private String otherDescription;
	private String otherRequirement;
	private String serviceDocFlag;
	private Double bpPrice;
	private Double bpCost;
	private String dfcltSeqFlag;
	private String virusSeqFlag;
	//以下为业务属性
	private List<Document> documentList;
	private List<Integer> delDocIdList;
    //add by zhanghuibin
    private String sequenceContent;
    private String vectorSize;
    private String vectorResistance;
    private String vectorCopyNo;
    private String vectorSeq;
    private String vectorMapDocFlag;
    private String cloningReadyFlag;
    private String scndExpsOrganismOther;

    public String getVectorMapDocFlag() {
        return vectorMapDocFlag;
    }

    public void setVectorMapDocFlag(String vectorMapDocFlag) {
        this.vectorMapDocFlag = vectorMapDocFlag;
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

	private String hostExpsOrgOther;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getGeneName() {
		return geneName;
	}
	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}
	public String getSequence5() {
		return sequence5;
	}
	public void setSequence5(String sequence5) {
		this.sequence5 = sequence5;
	}
	public String getSequenceType() {
		return sequenceType;
	}
	public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getSequence3() {
		return sequence3;
	}
	public void setSequence3(String sequence3) {
		this.sequence3 = sequence3;
	}
	public String getCodeOptmzFlag() {
		return codeOptmzFlag;
	}
	public void setCodeOptmzFlag(String codeOptmzFlag) {
		this.codeOptmzFlag = codeOptmzFlag;
	}
	public String getHostExpsOrganism() {
		return hostExpsOrganism;
	}
	public void setHostExpsOrganism(String hostExpsOrganism) {
		this.hostExpsOrganism = hostExpsOrganism;
	}
	public String getScndExpsOrganism() {
		return scndExpsOrganism;
	}
	public void setScndExpsOrganism(String scndExpsOrganism) {
		this.scndExpsOrganism = scndExpsOrganism;
	}
	public Integer getOptimizationStart() {
		return optimizationStart;
	}
	public void setOptimizationStart(Integer optimizationStart) {
		this.optimizationStart = optimizationStart;
	}
	public String getOpStartPosUom() {
		return opStartPosUom;
	}
	public void setOpStartPosUom(String opStartPosUom) {
		this.opStartPosUom = opStartPosUom;
	}
	public Integer getOptimizationEnd() {
		return optimizationEnd;
	}
	public void setOptimizationEnd(Integer optimizationEnd) {
		this.optimizationEnd = optimizationEnd;
	}
	public String getOpEndPosUom() {
		return opEndPosUom;
	}
	public void setOpEndPosUom(String opEndPosUom) {
		this.opEndPosUom = opEndPosUom;
	}
	public Integer getOrfStart() {
		return orfStart;
	}
	public void setOrfStart(Integer orfStart) {
		this.orfStart = orfStart;
	}
	public String getOrfStartUom() {
		return orfStartUom;
	}
	public void setOrfStartUom(String orfStartUom) {
		this.orfStartUom = orfStartUom;
	}
	public Integer getOrfEnd() {
		return orfEnd;
	}
	public void setOrfEnd(Integer orfEnd) {
		this.orfEnd = orfEnd;
	}
	public String getOrfEndUom() {
		return orfEndUom;
	}
	public void setOrfEndUom(String orfEndUom) {
		this.orfEndUom = orfEndUom;
	}
	public String getRstSitesAvoid() {
		return rstSitesAvoid;
	}
	public void setRstSitesAvoid(String rstSitesAvoid) {
		this.rstSitesAvoid = rstSitesAvoid;
	}
	public String getRstSitesKeep() {
		return rstSitesKeep;
	}
	public void setRstSitesKeep(String rstSitesKeep) {
		this.rstSitesKeep = rstSitesKeep;
	}
	public String getStopCodonFlag() {
		return stopCodonFlag;
	}
	public void setStopCodonFlag(String stopCodonFlag) {
		this.stopCodonFlag = stopCodonFlag;
	}
	public String getStopCodon() {
		return stopCodon;
	}
	public void setStopCodon(String stopCodon) {
		this.stopCodon = stopCodon;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStdVectorName() {
		return stdVectorName;
	}
	public void setStdVectorName(String stdVectorName) {
		this.stdVectorName = stdVectorName;
	}
	public String getCloningSite() {
		return cloningSite;
	}
	public void setCloningSite(String cloningSite) {
		this.cloningSite = cloningSite;
	}
	public String getCloningFlag() {
		return cloningFlag;
	}
	public void setCloningFlag(String cloningFlag) {
		this.cloningFlag = cloningFlag;
	}
	public Integer getStdPlasmidWt() {
		return stdPlasmidWt;
	}
	public void setStdPlasmidWt(Integer stdPlasmidWt) {
		this.stdPlasmidWt = stdPlasmidWt;
	}
	public String getStdPlasmidWtUom() {
		return stdPlasmidWtUom;
	}
	public void setStdPlasmidWtUom(String stdPlasmidWtUom) {
		this.stdPlasmidWtUom = stdPlasmidWtUom;
	}
	public String getPlasmidPrepFlag() {
		return plasmidPrepFlag;
	}
	public void setPlasmidPrepFlag(String plasmidPrepFlag) {
		this.plasmidPrepFlag = plasmidPrepFlag;
	}
	public String getMutagenesisFlag() {
		return mutagenesisFlag;
	}
	public void setMutagenesisFlag(String mutagenesisFlag) {
		this.mutagenesisFlag = mutagenesisFlag;
	}
	public String getGeneUsage() {
		return geneUsage;
	}
	public void setGeneUsage(String geneUsage) {
		this.geneUsage = geneUsage;
	}
	public String getReadingFrame() {
		return readingFrame;
	}
	public void setReadingFrame(String readingFrame) {
		this.readingFrame = readingFrame;
	}
	public String getOtherDescription() {
		return otherDescription;
	}
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	public String getOtherRequirement() {
		return otherRequirement;
	}
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}
	public String getServiceDocFlag() {
		return serviceDocFlag;
	}
	public void setServiceDocFlag(String serviceDocFlag) {
		this.serviceDocFlag = serviceDocFlag;
	}
	public Integer getQuoteItemId() {
		return quoteItemId;
	}
	public void setQuoteItemId(Integer quoteItemId) {
		this.quoteItemId = quoteItemId;
	}
	public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public List<Document> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
	public List<Integer> getDelDocIdList() {
		return delDocIdList;
	}
	public void setDelDocIdList(List<Integer> delDocIdList) {
		this.delDocIdList = delDocIdList;
	}
	public Integer getSeqLength() {
		return seqLength;
	}
	public void setSeqLength(Integer seqLength) {
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
	public String getGeneUsageText() {
		return geneUsageText;
	}
	public void setGeneUsageText(String geneUsageText) {
		this.geneUsageText = geneUsageText;
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

    public String getSequenceContent() {
        return sequenceContent;
    }

    public void setSequenceContent(String sequenceContent) {
        this.sequenceContent = sequenceContent;
    }
	public String getHostExpsOrgOther() {
		return hostExpsOrgOther;
	}
	public void setHostExpsOrgOther(String hostExpsOrgOther) {
		this.hostExpsOrgOther = hostExpsOrgOther;
	}

	public String getCloningReadyFlag() {
		return cloningReadyFlag;
	}

	public void setCloningReadyFlag(String cloningReadyFlag) {
		this.cloningReadyFlag = cloningReadyFlag;
	}

	public String getScndExpsOrganismOther() {
		return scndExpsOrganismOther;
	}

	public void setScndExpsOrganismOther(String scndExpsOrganismOther) {
		this.scndExpsOrganismOther = scndExpsOrganismOther;
	}
}
