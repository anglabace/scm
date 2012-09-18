package com.genscript.gsscm.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

@Entity
@Table(name = "vector", catalog = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SDVector extends BaseEntity {
	@Id
	private Integer productId;
	private Integer polylinkerStart;
	private Integer polylinkerEnd;
	@Column(name = "u6_start")
	private Integer u6Start;
	@Column(name = "u6_end")
	private Integer u6End;
	@Column(name = "h1_start")
	private Integer h1Start;
	@Column(name = "h1_end")
	private Integer h1End;
	private Integer cmvStart;
	private Integer cmvEnd;
	private Integer cgfpStart;
	private Integer cgfpEnd;
	@Column(name = "sv40_start")
	private Integer sv40Start;
	@Column(name = "sv40_end")
	private Integer sv40End;
	private Integer zeoStart;
	private Integer zeoEnd;
	private Integer hygroStart;
	private Integer hygroEnd;
	private Integer neoStart;
	private Integer neoEnd;
	private Integer pucOriStart;
	private Integer pucOriEnd;
	private Integer ampStart;
	private Integer ampEnd;
	@Column(name = "t7_start")
	private Integer t7Start;
	@Column(name = "t7_end")
	private Integer t7End;
	@Column(name = "p10_start")
	private Integer p10Start;
	@Column(name = "p10_end")
	private Integer p10End;
	@Column(name = "f1_start")
	private Integer f1Start;
	@Column(name = "f1_end")
	private Integer f1End;
	@Column(name = "t7_term_start")
	private Integer t7TermStart;
	@Column(name = "t7_term_end")
	private Integer t7TermEnd;
	@Column(name = "orf603_start")
	private Integer orf603Start;
	@Column(name = "orf603_end")
	private Integer orf603End;
	@Column(name = "orf1629_start")
	private Integer orf1629Start;
	@Column(name = "orf1629_end")
	private Integer orf1629End;
	@Column(name = "5_LTR_start")
	private Integer l5TRStart;
	@Column(name = "5_LTR_end")
	private Integer l5TREnd;
	@Column(name = "3_LTR_start")
	private Integer l3TRStart;
	@Column(name = "3_LTR_end")
	private Integer l3TREnd;
	private Integer psiStart;
	private Integer psiEnd;
	private Integer puromycinStart;
	private Integer puromycinEnd;
	@Column(name = "Gentamicin_start")
	private Integer gentamicinStart;
	@Column(name = "Gentamicin_end")
	private Integer gentamicinEnd;
	private Integer cmvIeStart;
	private Integer cmvIeEnd;
	@Column(name = "pbr322_origin_start")
	private Integer pbr322OriginStart;
	@Column(name = "pbr322_origin_end")
	private Integer pbr322OriginEnd;
	private Integer kanamycinStart;
	private Integer kanamycinEnd;// /
	private Integer siflucStart;
	private Integer siflucEnd;
	@Column(name = "cole1_ori_start")
	private Integer cole1OriStart;
	@Column(name = "cole1_ori_end")
	private Integer cole1OriEnd;
	@Column(name = "sv40_ori_promoter_start")
	private Integer sv40OriPromoterStart;
	@Column(name = "sv40_ori_promoter_end")
	private Integer sv40OriPromoterEnd;
	private Integer iresStart;
	private Integer iresEnd;
	private Integer mcsStart1;
	private Integer mcsEnd1;
	private String forwardPrimer;
	private String reversePrimer;
	private String sequence;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPolylinkerStart() {
		return polylinkerStart;
	}

	public void setPolylinkerStart(Integer polylinkerStart) {
		this.polylinkerStart = polylinkerStart;
	}

	public Integer getPolylinkerEnd() {
		return polylinkerEnd;
	}

	public void setPolylinkerEnd(Integer polylinkerEnd) {
		this.polylinkerEnd = polylinkerEnd;
	}

	public Integer getU6Start() {
		return u6Start;
	}

	public void setU6Start(Integer u6Start) {
		this.u6Start = u6Start;
	}

	public Integer getU6End() {
		return u6End;
	}

	public void setU6End(Integer u6End) {
		this.u6End = u6End;
	}

	public Integer getH1Start() {
		return h1Start;
	}

	public void setH1Start(Integer h1Start) {
		this.h1Start = h1Start;
	}

	public Integer getH1End() {
		return h1End;
	}

	public void setH1End(Integer h1End) {
		this.h1End = h1End;
	}

	public Integer getCmvStart() {
		return cmvStart;
	}

	public void setCmvStart(Integer cmvStart) {
		this.cmvStart = cmvStart;
	}

	public Integer getCmvEnd() {
		return cmvEnd;
	}

	public void setCmvEnd(Integer cmvEnd) {
		this.cmvEnd = cmvEnd;
	}

	public Integer getCgfpStart() {
		return cgfpStart;
	}

	public void setCgfpStart(Integer cgfpStart) {
		this.cgfpStart = cgfpStart;
	}

	public Integer getCgfpEnd() {
		return cgfpEnd;
	}

	public void setCgfpEnd(Integer cgfpEnd) {
		this.cgfpEnd = cgfpEnd;
	}

	public Integer getSv40Start() {
		return sv40Start;
	}

	public void setSv40Start(Integer sv40Start) {
		this.sv40Start = sv40Start;
	}

	public Integer getSv40End() {
		return sv40End;
	}

	public void setSv40End(Integer sv40End) {
		this.sv40End = sv40End;
	}

	public Integer getZeoStart() {
		return zeoStart;
	}

	public void setZeoStart(Integer zeoStart) {
		this.zeoStart = zeoStart;
	}

	public Integer getZeoEnd() {
		return zeoEnd;
	}

	public void setZeoEnd(Integer zeoEnd) {
		this.zeoEnd = zeoEnd;
	}

	public Integer getHygroStart() {
		return hygroStart;
	}

	public void setHygroStart(Integer hygroStart) {
		this.hygroStart = hygroStart;
	}

	public Integer getHygroEnd() {
		return hygroEnd;
	}

	public void setHygroEnd(Integer hygroEnd) {
		this.hygroEnd = hygroEnd;
	}

	public Integer getNeoStart() {
		return neoStart;
	}

	public void setNeoStart(Integer neoStart) {
		this.neoStart = neoStart;
	}

	public Integer getNeoEnd() {
		return neoEnd;
	}

	public void setNeoEnd(Integer neoEnd) {
		this.neoEnd = neoEnd;
	}

	public Integer getPucOriStart() {
		return pucOriStart;
	}

	public void setPucOriStart(Integer pucOriStart) {
		this.pucOriStart = pucOriStart;
	}

	public Integer getPucOriEnd() {
		return pucOriEnd;
	}

	public void setPucOriEnd(Integer pucOriEnd) {
		this.pucOriEnd = pucOriEnd;
	}

	public Integer getAmpStart() {
		return ampStart;
	}

	public void setAmpStart(Integer ampStart) {
		this.ampStart = ampStart;
	}

	public Integer getAmpEnd() {
		return ampEnd;
	}

	public void setAmpEnd(Integer ampEnd) {
		this.ampEnd = ampEnd;
	}

	public Integer gett7Start() {
		return t7Start;
	}

	public Integer getT7Start() {
		return t7Start;
	}

	public void setT7Start(Integer t7Start) {
		this.t7Start = t7Start;
	}

	public void setT7End(Integer t7End) {
		this.t7End = t7End;
	}

	public Integer getT7End() {
		return t7End;
	}

	public Integer getP10Start() {
		return p10Start;
	}

	public void setP10Start(Integer p10Start) {
		this.p10Start = p10Start;
	}

	public Integer getP10End() {
		return p10End;
	}

	public void setP10End(Integer p10End) {
		this.p10End = p10End;
	}

	public Integer getF1Start() {
		return f1Start;
	}

	public void setF1Start(Integer f1Start) {
		this.f1Start = f1Start;
	}

	public Integer getF1End() {
		return f1End;
	}

	public void setF1End(Integer f1End) {
		this.f1End = f1End;
	}

	public Integer getOrf603Start() {
		return orf603Start;
	}

	public void setOrf603Start(Integer orf603Start) {
		this.orf603Start = orf603Start;
	}

	public Integer getOrf603End() {
		return orf603End;
	}

	public void setOrf603End(Integer orf603End) {
		this.orf603End = orf603End;
	}

	public Integer getOrf1629Start() {
		return orf1629Start;
	}

	public void setOrf1629Start(Integer orf1629Start) {
		this.orf1629Start = orf1629Start;
	}

	public Integer getOrf1629End() {
		return orf1629End;
	}

	public void setOrf1629End(Integer orf1629End) {
		this.orf1629End = orf1629End;
	}

	public Integer getL5TRStart() {
		return l5TRStart;
	}

	public void setL5TRStart(Integer l5trStart) {
		l5TRStart = l5trStart;
	}

	public Integer getL5TREnd() {
		return l5TREnd;
	}

	public void setL5TREnd(Integer l5trEnd) {
		l5TREnd = l5trEnd;
	}

	public Integer getL3TRStart() {
		return l3TRStart;
	}

	public void setL3TRStart(Integer l3trStart) {
		l3TRStart = l3trStart;
	}

	public Integer getL3TREnd() {
		return l3TREnd;
	}

	public void setL3TREnd(Integer l3trEnd) {
		l3TREnd = l3trEnd;
	}

	public Integer getPsiStart() {
		return psiStart;
	}

	public void setPsiStart(Integer psiStart) {
		this.psiStart = psiStart;
	}

	public Integer getPsiEnd() {
		return psiEnd;
	}

	public void setPsiEnd(Integer psiEnd) {
		this.psiEnd = psiEnd;
	}

	public Integer getPuromycinStart() {
		return puromycinStart;
	}

	public void setPuromycinStart(Integer puromycinStart) {
		this.puromycinStart = puromycinStart;
	}

	public Integer getPuromycinEnd() {
		return puromycinEnd;
	}

	public void setPuromycinEnd(Integer puromycinEnd) {
		this.puromycinEnd = puromycinEnd;
	}

	public Integer getT7TermStart() {
		return t7TermStart;
	}

	public void setT7TermStart(Integer t7TermStart) {
		this.t7TermStart = t7TermStart;
	}

	public Integer getT7TermEnd() {
		return t7TermEnd;
	}

	public void setT7TermEnd(Integer t7TermEnd) {
		this.t7TermEnd = t7TermEnd;
	}

	public Integer getGentamicinStart() {
		return gentamicinStart;
	}

	public void setGentamicinStart(Integer gentamicinStart) {
		this.gentamicinStart = gentamicinStart;
	}

	public Integer getGentamicinEnd() {
		return gentamicinEnd;
	}

	public void setGentamicinEnd(Integer gentamicinEnd) {
		this.gentamicinEnd = gentamicinEnd;
	}

	public Integer getCmvIeStart() {
		return cmvIeStart;
	}

	public void setCmvIeStart(Integer cmvIeStart) {
		this.cmvIeStart = cmvIeStart;
	}

	public Integer getCmvIeEnd() {
		return cmvIeEnd;
	}

	public void setCmvIeEnd(Integer cmvIeEnd) {
		this.cmvIeEnd = cmvIeEnd;
	}

	public Integer getPbr322OriginStart() {
		return pbr322OriginStart;
	}

	public void setPbr322OriginStart(Integer pbr322OriginStart) {
		this.pbr322OriginStart = pbr322OriginStart;
	}

	public Integer getPbr322OriginEnd() {
		return pbr322OriginEnd;
	}

	public void setPbr322OriginEnd(Integer pbr322OriginEnd) {
		this.pbr322OriginEnd = pbr322OriginEnd;
	}

	public Integer getKanamycinStart() {
		return kanamycinStart;
	}

	public void setKanamycinStart(Integer kanamycinStart) {
		this.kanamycinStart = kanamycinStart;
	}

	public Integer getKanamycinEnd() {
		return kanamycinEnd;
	}

	public void setKanamycinEnd(Integer kanamycinEnd) {
		this.kanamycinEnd = kanamycinEnd;
	}

	public Integer getSiflucStart() {
		return siflucStart;
	}

	public void setSiflucStart(Integer siflucStart) {
		this.siflucStart = siflucStart;
	}

	public Integer getSiflucEnd() {
		return siflucEnd;
	}

	public void setSiflucEnd(Integer siflucEnd) {
		this.siflucEnd = siflucEnd;
	}

	public Integer getCole1OriStart() {
		return cole1OriStart;
	}

	public void setCole1OriStart(Integer cole1OriStart) {
		this.cole1OriStart = cole1OriStart;
	}

	public Integer getCole1OriEnd() {
		return cole1OriEnd;
	}

	public void setCole1OriEnd(Integer cole1OriEnd) {
		this.cole1OriEnd = cole1OriEnd;
	}

	public Integer getSv40OriPromoterStart() {
		return sv40OriPromoterStart;
	}

	public void setSv40OriPromoterStart(Integer sv40OriPromoterStart) {
		this.sv40OriPromoterStart = sv40OriPromoterStart;
	}

	public Integer getSv40OriPromoterEnd() {
		return sv40OriPromoterEnd;
	}

	public void setSv40OriPromoterEnd(Integer sv40OriPromoterEnd) {
		this.sv40OriPromoterEnd = sv40OriPromoterEnd;
	}

	public Integer getIresStart() {
		return iresStart;
	}

	public void setIresStart(Integer iresStart) {
		this.iresStart = iresStart;
	}

	public Integer getIresEnd() {
		return iresEnd;
	}

	public void setIresEnd(Integer iresEnd) {
		this.iresEnd = iresEnd;
	}

	public Integer getMcsStart1() {
		return mcsStart1;
	}

	public void setMcsStart1(Integer mcsStart1) {
		this.mcsStart1 = mcsStart1;
	}

	public Integer getMcsEnd1() {
		return mcsEnd1;
	}

	public void setMcsEnd1(Integer mcsEnd1) {
		this.mcsEnd1 = mcsEnd1;
	} 
 

	public String getForwardPrimer() {
		return forwardPrimer;
	}

	public void setForwardPrimer(String forwardPrimer) {
		this.forwardPrimer = forwardPrimer;
	}

	public String getReversePrimer() {
		return reversePrimer;
	}

	public void setReversePrimer(String reversePrimer) {
		this.reversePrimer = reversePrimer;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}
