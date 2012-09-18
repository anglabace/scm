package com.genscript.gsscm.manufacture.dto;

import java.math.BigDecimal;

/**
 * zhanghuibin
 * User: Administrator
 * Date: 10/26/11
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PeptideTemplateDTO {
    private String orderId;
    private String wo;
    private String lotNo;
    private String name;
    private String pm;
    private String quantity;
    private String aliq;
    private String purity;
    private String deliveryDate;
    private String sequence;
    private String modification;
    private String comments;
    private String hyd;
    private BigDecimal mw;
    private String realPurity;
    private String realQuantity;
    private String appearance;
    private String destination;

    public String getHyd() {
        return hyd;
    }

    public void setHyd(String hyd) {
        this.hyd = hyd;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWo() {
        return wo;
    }

    public void setWo(String wo) {
        this.wo = wo;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAliq() {
        return aliq;
    }

    public void setAliq(String aliq) {
        this.aliq = aliq;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

	public BigDecimal getMw() {
		return mw;
	}

	public void setMw(BigDecimal mw) {
		this.mw = mw;
	}

	public String getRealPurity() {
		return realPurity;
	}

	public void setRealPurity(String realPurity) {
		this.realPurity = realPurity;
	}

	public String getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(String realQuantity) {
		this.realQuantity = realQuantity;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
