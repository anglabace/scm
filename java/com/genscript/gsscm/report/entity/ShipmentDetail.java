package com.genscript.gsscm.report.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/29/11
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ship_package_lines", catalog="shipping")
public class ShipmentDetail implements Serializable{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
    private Integer id;
    private String salesOrderNo;
    private String salesOrderDate;
    private String customerName;
    private String organizationName;
    @Column(name = "itemNo")
    private String itemNo;
    private Integer qtyShipped;
    private Integer sizeShipped;
    private String trackingNo;
    private String shipmentDate;
    private String shippingCarrier;
    private String shipClerk;
    private String shipTo;
    private String shipper;

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(String salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(Integer qtyShipped) {
        this.qtyShipped = qtyShipped;
    }

    public Integer getSizeShipped() {
        return sizeShipped;
    }

    public void setSizeShipped(Integer sizeShipped) {
        this.sizeShipped = sizeShipped;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShippingCarrier() {
        return shippingCarrier;
    }

    public void setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getShipClerk() {
        return shipClerk;
    }

    public void setShipClerk(String shipClerk) {
        this.shipClerk = shipClerk;
    }
}
