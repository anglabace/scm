package com.genscript.gsscm.manufacture.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "ProteinLabelsDTO", namespace = WsConstants.NS)
public class ProteinLabelsDTO implements Serializable{
	private static final long serialVersionUID = -2955680858169218875L;
	private String orderNo;
	private String itemNo;
	private String lotNo;
	private String proteinName;
	private boolean lyophilizedFlg;
	private double concentration;
	private double finalPrep;
	private String purity;
	private String fusedWith;
	private String plasmid1;
	private String plasmid2;
	private String plasmid3;
	private String plasmid4;
	private String plasmid5;
	private String plasmid6;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getProteinName() {
		return proteinName;
	}
	public void setProteinName(String proteinName) {
		this.proteinName = proteinName;
	}
	public boolean isLyophilizedFlg() {
		return lyophilizedFlg;
	}
	public void setLyophilizedFlg(boolean lyophilizedFlg) {
		this.lyophilizedFlg = lyophilizedFlg;
	}
	public double getConcentration() {
		return concentration;
	}
	public void setConcentration(double concentration) {
		this.concentration = concentration;
	}
	public double getFinalPrep() {
		return finalPrep;
	}
	public void setFinalPrep(double finalPrep) {
		this.finalPrep = finalPrep;
	}
	public String getPurity() {
		return purity;
	}
	public void setPurity(String purity) {
		this.purity = purity;
	}
	public String getFusedWith() {
		return fusedWith;
	}
	public void setFusedWith(String fusedWith) {
		this.fusedWith = fusedWith;
	}
	public String getPlasmid1() {
		return plasmid1;
	}
	public void setPlasmid1(String plasmid1) {
		this.plasmid1 = plasmid1;
	}
	public String getPlasmid2() {
		return plasmid2;
	}
	public void setPlasmid2(String plasmid2) {
		this.plasmid2 = plasmid2;
	}
	public String getPlasmid3() {
		return plasmid3;
	}
	public void setPlasmid3(String plasmid3) {
		this.plasmid3 = plasmid3;
	}
	public String getPlasmid4() {
		return plasmid4;
	}
	public void setPlasmid4(String plasmid4) {
		this.plasmid4 = plasmid4;
	}
	public String getPlasmid5() {
		return plasmid5;
	}
	public void setPlasmid5(String plasmid5) {
		this.plasmid5 = plasmid5;
	}
	public String getPlasmid6() {
		return plasmid6;
	}
	public void setPlasmid6(String plasmid6) {
		this.plasmid6 = plasmid6;
	}
	

}
