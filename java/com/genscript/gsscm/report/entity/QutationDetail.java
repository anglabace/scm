package com.genscript.gsscm.report.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * QUOTATION MAIN.
 *
 *
 * @author zhanghuibin
 */

@Entity
@Table(name = "quote", catalog="order")
public class QutationDetail extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quote_no")
	private Integer quoteNo;
	private Integer custNo;
	private String altQuoteNo;
	@Column(updatable = false)
	private Date quoteDate;
	private Date exprDate;
	private String quoteType;
	private String priority;
	private Integer salesContact;
	private Integer techSupport;
	@Column(name = "alt_sales_contact")
	private Integer altContact;
	private String quoteCurrency;
	private Double exchRate;
	@Column(updatable = false)
	private Date exchRateDate;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	@Column(name="ship_amt")
	private Double shipAmt;
	private String shipAmtChanged;
	private BigDecimal amount;
	private Integer paymentTerm;
	private String quoteMemo;
	private String refQuoteNo;
	private Integer quoteSrc;
	private String orderNo;
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
	@Column(name="warehouse_id")
	private Integer warehouseId;
    private String shippingAccount;
	private Integer projectManager;
	private Integer altProjectManager;

	private String couponId;
	private BigDecimal couponValue;
    @Transient
    private Double total;
    @Transient
    private String location;
    @Transient
    private Date quotationCloseDate;
    @Transient
    private BigDecimal closeRatio;
    @Transient
    private String fromDate;
    @Transient
    private String toDate;
    @Transient
    private Object dateInt;
    @Transient
    private String salesTerritoryName;
    @Transient
    private String salesContactName;
    @Transient
    private String techSupportName;
    @Transient
    private String projectManagerName;
    @Transient
    private String orgName;
    @Transient
    private String org_type_name;

    public String getOrg_type_name() {
        return org_type_name;
    }

    public void setOrg_type_name(String org_type_name) {
        this.org_type_name = org_type_name;
    }

    public Object getDateInt() {
        return dateInt;
    }

    public void setDateInt(Object dateInt) {
        this.dateInt = dateInt;
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

    public BigDecimal getCloseRatio() {
        return closeRatio;
    }

    public void setCloseRatio(BigDecimal closeRatio) {
        this.closeRatio = closeRatio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getQuotationCloseDate() {
        return quotationCloseDate;
    }

    public void setQuotationCloseDate(Date quotationCloseDate) {
        this.quotationCloseDate = quotationCloseDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	public Date getExprDate() {
		return exprDate;
	}
	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}
	/**
	 * @return the custNo
	 */
	public Integer getCustNo() {
		return custNo;
	}
	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the billtoAddrId
	 */
	public Integer getBilltoAddrId() {
		return billtoAddrId;
	}
	/**
	 * @param billtoAddrId the billtoAddrId to set
	 */
	public void setBilltoAddrId(Integer billtoAddrId) {
		this.billtoAddrId = billtoAddrId;
	}
	/**
	 * @return the shiptoAddrId
	 */
	public Integer getShiptoAddrId() {
		return shiptoAddrId;
	}
	/**
	 * @param shiptoAddrId the shiptoAddrId to set
	 */
	public void setShiptoAddrId(Integer shiptoAddrId) {
		this.shiptoAddrId = shiptoAddrId;
	}
	/**
	 * @return the soldtoAddrId
	 */
	public Integer getSoldtoAddrId() {
		return soldtoAddrId;
	}
	/**
	 * @param soldtoAddrId the soldtoAddrId to set
	 */
	public void setSoldtoAddrId(Integer soldtoAddrId) {
		this.soldtoAddrId = soldtoAddrId;
	}
	/**
	 * @return the gsCoId
	 */
	public Integer getGsCoId() {
		return gsCoId;
	}
	/**
	 * @param gsCoId the gsCoId to set
	 */
	public void setGsCoId(Integer gsCoId) {
		this.gsCoId = gsCoId;
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
		if(subTotal==null) {
			subTotal = new BigDecimal(0);
		}
		this.subTotal = subTotal;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		if(discount==null) {
			discount = new BigDecimal(0);
		}
		this.discount = discount;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		if(tax==null) {
			tax = new BigDecimal(0);
		}
		this.tax = tax;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		if(amount==null) {
			amount = new BigDecimal(0);
		}
		this.amount = amount;
	}
	public Integer getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(Integer paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getQuoteMemo() {
		return quoteMemo;
	}
	public void setQuoteMemo(String quoteMemo) {
		this.quoteMemo = quoteMemo;
	}
	public Integer getQuoteSrc() {
		return quoteSrc;
	}
	public void setQuoteSrc(Integer quoteSrc) {
		this.quoteSrc = quoteSrc;
	}
	public String getRefQuoteNo() {
		return refQuoteNo;
	}
	public void setRefQuoteNo(String refQuoteNo) {
		this.refQuoteNo = refQuoteNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}
	/**
	 * @param statusReason the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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
	public Double getShipAmt() {
		return shipAmt;
	}
	public void setShipAmt(Double shipAmt) {
		if(shipAmt==null) {
			shipAmt = 00.;
		}
		this.shipAmt = shipAmt;
	}
	/**
	 * @return the quoteCurrency
	 */
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	/**
	 * @param quoteCurrency the quoteCurrency to set
	 */
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	public String getAltQuoteNo() {
		return altQuoteNo;
	}
	public void setAltQuoteNo(String altQuoteNo) {
		this.altQuoteNo = altQuoteNo;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
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
	public BigDecimal getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(BigDecimal couponValue) {
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

    public String getTechSupportName() {
        return techSupportName;
    }

    public void setTechSupportName(String techSupportName) {
        this.techSupportName = techSupportName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getSalesContactName() {
        return salesContactName;
    }

    public void setSalesContactName(String salesContactName) {
        this.salesContactName = salesContactName;
    }

    public String getSalesTerritoryName() {
        return salesTerritoryName;
    }

    public void setSalesTerritoryName(String salesTerritoryName) {
        this.salesTerritoryName = salesTerritoryName;
    }
}

