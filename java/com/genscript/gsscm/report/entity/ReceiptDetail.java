package com.genscript.gsscm.report.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/29/11
 * Time: 2:41 PM
 * To Receipt Detail
 */

@Entity
@Table(name = "receiving_logs", catalog="inventory")
public class ReceiptDetail implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
    private String purchaseOrderNo;
    private String purchaseOrderDate;
    private String salesOrderNo;
    private String salesOrderDate;
    private String vendorName;
    private String itemNo;
    private Integer qtyReceived;
    private Integer sizeReceived;
    private String storageName;
    private String trackingNo;
    private String receivingDate;
    private String receivingClerk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public String getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(Integer qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public Integer getSizeReceived() {
        return sizeReceived;
    }

    public void setSizeReceived(Integer sizeReceived) {
        this.sizeReceived = sizeReceived;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    public String getReceivingClerk() {
        return receivingClerk;
    }

    public void setReceivingClerk(String receivingClerk) {
        this.receivingClerk = receivingClerk;
    }
}
