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
 * OrganizationBilling
 * 
 * 
 * @author Wangsf
 */
@Entity
@Table(name = "organization_billings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganizationBilling extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8176678779271922870L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Integer id;
	private Integer orgId;
	private String contactName;
	private String creditCard;
	private String number;
	private String expMonth;
	private String expYear;
	private String accountUsage;
	private String bankAccountType;
	private String bank;
	private String accountNo;
	private String routingNo;
	private String iban;
	private String bban;
	private String accountName;
	private String accountStreet;
	private String accountCity;
	private String accountZip;
	private String accountState;
	private String accountCountry;
	private String driveLicense;
	private String socialSecurityNo;
	private String accountEmail;
	private String addressVerified;

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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getAccountUsage() {
		return accountUsage;
	}

	public void setAccountUsage(String accountUsage) {
		this.accountUsage = accountUsage;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBban() {
		return bban;
	}

	public void setBban(String bban) {
		this.bban = bban;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountStreet() {
		return accountStreet;
	}

	public void setAccountStreet(String accountStreet) {
		this.accountStreet = accountStreet;
	}

	public String getAccountCity() {
		return accountCity;
	}

	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
	}

	public String getAccountZip() {
		return accountZip;
	}

	public void setAccountZip(String accountZip) {
		this.accountZip = accountZip;
	}

	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	public String getAccountCountry() {
		return accountCountry;
	}

	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}

	public String getDriveLicense() {
		return driveLicense;
	}

	public void setDriveLicense(String driveLicense) {
		this.driveLicense = driveLicense;
	}

	public String getSocialSecurityNo() {
		return socialSecurityNo;
	}

	public void setSocialSecurityNo(String socialSecurityNo) {
		this.socialSecurityNo = socialSecurityNo;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAddressVerified() {
		return addressVerified;
	}

	public void setAddressVerified(String addressVerified) {
		this.addressVerified = addressVerified;
	}



}
