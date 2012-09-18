package com.genscript.gsscm.report.entity;

import com.genscript.core.orm.hibernate.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhanghuibin
 * User: Administrator
 * Date: 8/1/11
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "purchase_orders", catalog="purchase")
public class PurchaseOrderReport implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private Integer orderNo;
	private Integer vendorNo;
    private String vendorName;
	private Date orderDate;
	private String status;
	private Integer srcSoNo;
	private Double subTotal;
    private Double total;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the vendorNo
	 */
	public Integer getVendorNo() {
		return vendorNo;
	}
	/**
	 * @param vendorNo the vendorNo to set
	 */
	public void setVendorNo(Integer vendorNo) {
		this.vendorNo = vendorNo;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	public Integer getSrcSoNo() {
		return srcSoNo;
	}

	public void setSrcSoNo(Integer srcSoNo) {
		this.srcSoNo = srcSoNo;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

