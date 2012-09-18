package com.genscript.gsscm.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * ORDER MAIN.
 * 
 * 
 * @author Golf
 */

@Entity
@Table(name = "order", catalog="order")
public class OrderMain extends BaseEntity implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private Integer orderNo;
	private String refOrderNo;
	private Integer custNo;
	private String orderType;
	@Column(updatable = false)
	private Date orderDate;
	private Date exprDate;
	private String priority;
	private Integer salesContact;
	private Integer techSupport;
	@Column(name = "alt_sales_contact")
	private Integer altContact;
	private String orderCurrency;
	private Double exchRate;
	@Column(updatable = false)
	private Date exchRateDate;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	private Double shipAmt;
	private BigDecimal amount;
	private Integer paymentTerm;
	private String orderMemo;
	private String srcQuoteNo;
	private Integer orderSrc;
	private Integer billtoAddrId;
	private Integer shiptoAddrId;
	private Integer soldtoAddrId;
	private String status;
	@Column(name = "status_upd_reason")
	private String statusReason;
	@Column(name = "company_id")
	private Integer gsCoId;
	private Integer shiptoAddrFlag;
	private Integer altTechSupport;
	private String baseCurrency;
	private Integer warehouseId;
	private String altOrderNo;
	private String shippingType;
	private String shippingRule;
	private Integer srcPoNo;
	private String shippingAccount;
	private String couponId;
	private Double couponValue;
	private String shipAmtChanged;
	private Integer projectManager;
	private Integer altProjectManager;
    private Integer keyInfoChanged;
    @Transient
    private String fromDate;
    @Transient
    private String toDate;
    @Transient
    private BigDecimal profit;
    @Transient
    private BigDecimal rate;
    @Transient
    private BigDecimal cost;
	//add by zhou gang
	@Transient
	private Double total;
	@Transient
	private String location;
    @Transient
    private Object dateInt;

    public Object getDateInt() {
        return dateInt;
    }

    public void setDateInt(Object dateInt) {
        this.dateInt = dateInt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Integer getSrcPoNo() {
		return srcPoNo;
	}
	public void setSrcPoNo(Integer srcPoNo) {
		this.srcPoNo = srcPoNo;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getCustNo() {
		return custNo;
	}
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getSalesContact() {
		return salesContact;
	}
	public void setSalesContact(Integer salesContact) {
		this.salesContact = salesContact;
	}
	public Integer getTechSupport() {
		return techSupport;
	}
	public void setTechSupport(Integer techSupport) {
		this.techSupport = techSupport;
	}
	public Integer getAltContact() {
		return altContact;
	}
	public void setAltContact(Integer altContact) {
		this.altContact = altContact;
	}

	public Double getExchRate() {
		return exchRate;
	}
	public void setExchRate(Double exchRate) {
		this.exchRate = exchRate;
	}
	public Date getExchRateDate() {
		return exchRateDate;
	}
	public void setExchRateDate(Date exchRateDate) {
		this.exchRateDate = exchRateDate;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public Double getShipAmt() {
		return shipAmt;
	}
	public void setShipAmt(Double shipAmt) {
		this.shipAmt = shipAmt;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	public String getSrcQuoteNo() {
		return srcQuoteNo;
	}
	public void setSrcQuoteNo(String srcQuoteNo) {
		this.srcQuoteNo = srcQuoteNo;
	}
	public Integer getBilltoAddrId() {
		return billtoAddrId;
	}
	public void setBilltoAddrId(Integer billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}
	public Integer getShiptoAddrId() {
		return shiptoAddrId;
	}
	public void setShiptoAddrId(Integer shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}
	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}
	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	public Integer getGsCoId() {
		return gsCoId;
	}
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getRefOrderNo() {
		return refOrderNo;
	}
	public void setRefOrderNo(String refOrderNo) {
		this.refOrderNo = refOrderNo;
	}
	public Integer getOrderSrc() {
		return orderSrc;
	}
	public void setOrderSrc(Integer orderSrc) {
		this.orderSrc = orderSrc;
	}
	/**
	 * @return the shiptoAddrFlag
	 */
	public Integer getShiptoAddrFlag() {
		return shiptoAddrFlag;
	}
	/**
	 * @param shiptoAddrFlag the shiptoAddrFlag to set
	 */
	public void setShiptoAddrFlag(Integer shiptoAddrFlag) {
		this.shiptoAddrFlag = shiptoAddrFlag;
	}
	/**
	 * @return the altTechSupport
	 */
	public Integer getAltTechSupport() {
		return altTechSupport;
	}
	/**
	 * @param altTechSupport the altTechSupport to set
	 */
	public void setAltTechSupport(Integer altTechSupport) {
		this.altTechSupport = altTechSupport;
	}
	/**
	 * @return the baseCurrency
	 */
	public String getBaseCurrency() {
		return baseCurrency;
	}
	/**
	 * @param baseCurrency the baseCurrency to set
	 */
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	/**
	 * @return the orderCurrency
	 */
	public String getOrderCurrency() {
		return orderCurrency;
	}
	/**
	 * @param orderCurrency the orderCurrency to set
	 */
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getAltOrderNo() {
		return altOrderNo;
	}
	public void setAltOrderNo(String altOrderNo) {
		this.altOrderNo = altOrderNo;
	}
	public Object clone()throws CloneNotSupportedException {
		OrderMain cloned = (OrderMain)super.clone();
		return cloned;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getShippingRule() {
		return shippingRule;
	}
	public void setShippingRule(String shippingRule) {
		this.shippingRule = shippingRule;
	}
	public String getShippingAccount() {
		return shippingAccount;
	}
	public void setShippingAccount(String shippingAccount) {
		this.shippingAccount = shippingAccount;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public Double getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(Double couponValue) {
		this.couponValue = couponValue;
	}
	public String getShipAmtChanged() {
		return shipAmtChanged;
	}
	public void setShipAmtChanged(String shipAmtChanged) {
		this.shipAmtChanged = shipAmtChanged;
	}
	public Integer getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(Integer projectManager) {
		this.projectManager = projectManager;
	}
	public Integer getAltProjectManager() {
		return altProjectManager;
	}
	public void setAltProjectManager(Integer altProjectManager) {
		this.altProjectManager = altProjectManager;
	}

    public Integer getKeyInfoChanged() {
        return keyInfoChanged;
    }

    public void setKeyInfoChanged(Integer keyInfoChanged) {
        this.keyInfoChanged = keyInfoChanged;
    }
}
