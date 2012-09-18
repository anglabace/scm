package com.genscript.gsscm.tools.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/20/11
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class WarehousingShipmentDto {

    private String fromCountry;
    private String fromZipCode;
    private String toCountry;
    private String toZipCode;
    private Integer weight;
    private String catalogNos;
    private String shippingCarrier;
    private Integer shippingMethod;
    private Date deliveryDate;
    private double carrierRate;
    private double genScriptRate;
    //类型product/service
    private String itemType;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getFromZipCode() {
        return fromZipCode;
    }

    public void setFromZipCode(String fromZipCode) {
        this.fromZipCode = fromZipCode;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public String getToZipCode() {
        return toZipCode;
    }

    public void setToZipCode(String toZipCode) {
        this.toZipCode = toZipCode;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getShippingCarrier() {
        return shippingCarrier;
    }

    public void setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
    }

    public Integer getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Integer shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getGenScriptRate() {
        return genScriptRate;
    }

    public void setGenScriptRate(double genScriptRate) {
        this.genScriptRate = genScriptRate;
    }

    public double getCarrierRate() {
        return carrierRate;
    }

    public void setCarrierRate(double carrierRate) {
        this.carrierRate = carrierRate;
    }

    public String getCatalogNos() {
        return catalogNos;
    }

    public void setCatalogNos(String catalogNos) {
        this.catalogNos = catalogNos;
    }
}
