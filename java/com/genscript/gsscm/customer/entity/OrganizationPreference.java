package com.genscript.gsscm.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.genscript.core.orm.hibernate.BaseEntity;

/**
 * OrganizationPreference
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "organization_preferences")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganizationPreference extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8176678779271922870L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer orgId;
	private String custContactName;	
	private String representative;
	private String invoiceRule;
	private String invoiceSchedule;
	private String shipRule;
	private String shippingSchema;
	private String orderPriceCatalog;
	@Column(name="flat_disc_percernt")
	private Double flatDiscPercent;
	private String paymentMethod;
	private String orderPaymentTerm;
	private String returnPolicy;
	private String orderReference;
	private String orderDescription;
	private String invoicePrintFormat;
	private String orderPrintFormat;
	private String printedFlag;
	private String vendorContactName;
	private String poPaymentRule;
	private String poPaymentTerm;
	private String poPriceCatalog;
	private String poDiscountSchema;
	private String poReturnPolicy;
	@Column(name="sales_rep_falg")
	private String salesRepFlag;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getCustContactName() {
		return custContactName;
	}

	public void setCustContactName(String custContactName) {
		this.custContactName = custContactName;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getInvoiceSchedule() {
		return invoiceSchedule;
	}

	public void setInvoiceSchedule(String invoiceSchedule) {
		this.invoiceSchedule = invoiceSchedule;
	}

	public String getShipRule() {
		return shipRule;
	}

	public void setShipRule(String shipRule) {
		this.shipRule = shipRule;
	}

	public String getShippingSchema() {
		return shippingSchema;
	}

	public void setShippingSchema(String shippingSchema) {
		this.shippingSchema = shippingSchema;
	}

	public String getOrderPriceCatalog() {
		return orderPriceCatalog;
	}

	public void setOrderPriceCatalog(String orderPriceCatalog) {
		this.orderPriceCatalog = orderPriceCatalog;
	}

	public Double getFlatDiscPercent() {
		return flatDiscPercent;
	}

	public void setFlatDiscPercent(Double flatDiscPercent) {
		this.flatDiscPercent = flatDiscPercent;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderPaymentTerm() {
		return orderPaymentTerm;
	}

	public void setOrderPaymentTerm(String orderPaymentTerm) {
		this.orderPaymentTerm = orderPaymentTerm;
	}

	public String getReturnPolicy() {
		return returnPolicy;
	}

	public void setReturnPolicy(String returnPolicy) {
		this.returnPolicy = returnPolicy;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getInvoicePrintFormat() {
		return invoicePrintFormat;
	}

	public void setInvoicePrintFormat(String invoicePrintFormat) {
		this.invoicePrintFormat = invoicePrintFormat;
	}

	public String getOrderPrintFormat() {
		return orderPrintFormat;
	}

	public void setOrderPrintFormat(String orderPrintFormat) {
		this.orderPrintFormat = orderPrintFormat;
	}

	public String getPrintedFlag() {
		return printedFlag;
	}

	public void setPrintedFlag(String printedFlag) {
		this.printedFlag = printedFlag;
	}

	public String getVendorContactName() {
		return vendorContactName;
	}

	public void setVendorContactName(String vendorContactName) {
		this.vendorContactName = vendorContactName;
	}

	public String getPoPaymentRule() {
		return poPaymentRule;
	}

	public void setPoPaymentRule(String poPaymentRule) {
		this.poPaymentRule = poPaymentRule;
	}

	public String getPoPaymentTerm() {
		return poPaymentTerm;
	}

	public void setPoPaymentTerm(String poPaymentTerm) {
		this.poPaymentTerm = poPaymentTerm;
	}

	public String getPoPriceCatalog() {
		return poPriceCatalog;
	}

	public void setPoPriceCatalog(String poPriceCatalog) {
		this.poPriceCatalog = poPriceCatalog;
	}

	public String getPoDiscountSchema() {
		return poDiscountSchema;
	}

	public void setPoDiscountSchema(String poDiscountSchema) {
		this.poDiscountSchema = poDiscountSchema;
	}

	public String getPoReturnPolicy() {
		return poReturnPolicy;
	}

	public void setPoReturnPolicy(String poReturnPolicy) {
		this.poReturnPolicy = poReturnPolicy;
	}

	public String getSalesRepFlag() {
		return salesRepFlag;
	}

	public void setSalesRepFlag(String salesRepFlag) {
		this.salesRepFlag = salesRepFlag;
	}

	public String getInvoiceRule() {
		return invoiceRule;
	}

	public void setInvoiceRule(String invoiceRule) {
		this.invoiceRule = invoiceRule;
	}

	

}
