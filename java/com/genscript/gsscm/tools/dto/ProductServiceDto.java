package com.genscript.gsscm.tools.dto;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 7/21/11
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductServiceDto {

    //private String itemIds;
    //每个product/service的price
    private Double price;
    private Integer clsId;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getClsId() {
        return clsId;
    }

    public void setClsId(Integer clsId) {
        this.clsId = clsId;
    }
}
