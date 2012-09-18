package com.genscript.gsscm.quote.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "QuotationTxtPrintDTO", namespace = WsConstants.NS)
public class QuotationTxtPrintDTO implements Serializable {

	private static final long serialVersionUID = 3331174486402662729L;
	// QID
	private String qId;
	// INIT_DATE
	private String initDate;
	// EXP_DATE
	private String expDate;
	// FIRSTNAME
	private String firstName;
	// LASTNAME
	private String lastName;
	// EMAIL
	private String email;
	// DEPARTMENT
	private String department;
	// INSTITUTION
	private String institution;
	// STATE
	private String state;
	// COUNTRY
	private String country;
	// result
	private String result;
	// COMPANY_NAME
	private String companyName;
	// NAME
	private String name;
	// symbol
	private String symbol;
	// TURN_AROUND
	private String turnAround;
	// TABLE_BODY
	private String tableBody;
	// _SubtotalNAME
	private String subTotalName;
	// SUB_PRICE
	private String subprice;
	// SUB_DISCOUNT
	private String subDiscount;
	// SUB_TOTAL
	private String subTotal;
	// SHIP_PRICE_DEPARTMENT
	private String shipPriceDepartment;
	// TAX_DEPARTMENT
	private String taxDepartment;
	// _Total_QuoteNAME
	private String totalQuoteName;
	// total_price_desc
	private String totalPriceDesc;
	// TOTAL
	private String total;
	// TOTAL_INCLUDE_TAX
	private String totalIncludeTax;
	// turnaround_comments
	private String turnAroundComments;
	// detail
	private String detail;

	private boolean showChild = false;

	public QuotationTxtPrintDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getQId() {
		return qId;
	}

	public void setQId(String id) {
		qId = id == null ? "" : id;
	}

	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate == null ? "" : initDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate == null ? "" : expDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName == null ? "" : firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName == null ? "" : lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? "" : email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? "" : department;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution == null ? "" : institution;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? "" : state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? "" : country;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result == null ? "" : result;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? "" : companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? "" : name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol == null ? "" : symbol;
	}

	public String getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(String turnAround) {
		this.turnAround = turnAround == null ? "" : turnAround;
	}

	public String getTableBody() {
		return tableBody;
	}

	public void setTableBody(String tableBody) {
		this.tableBody = tableBody == null ? "" : tableBody;
	}

	public String getSubTotalName() {
		return subTotalName;
	}

	public void setSubTotalName(String subTotalName) {
		this.subTotalName = subTotalName == null ? "" : subTotalName;
	}

	public String getSubprice() {
		return subprice;
	}

	public void setSubprice(String subprice) {
		this.subprice = subprice == null ? "0.0" : subprice;
	}

	public String getSubDiscount() {
		return subDiscount;
	}

	public void setSubDiscount(String subDiscount) {
		this.subDiscount = subDiscount == null ? "0.0" : subDiscount;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal == null ? "0.0" : subTotal;
	}

	public String getShipPriceDepartment() {
		return shipPriceDepartment;
	}

	public void setShipPriceDepartment(String shipPriceDepartment) {
		this.shipPriceDepartment = shipPriceDepartment == null ? ""
				: shipPriceDepartment;
	}

	public String getTaxDepartment() {
		return taxDepartment;
	}

	public void setTaxDepartment(String taxDepartment) {
		this.taxDepartment = taxDepartment == null ? "" : taxDepartment;
	}

	public String getTotalQuoteName() {
		return totalQuoteName;
	}

	public void setTotalQuoteName(String totalQuoteName) {
		this.totalQuoteName = totalQuoteName == null ? "" : totalQuoteName;
	}

	public String getTotalPriceDesc() {
		return totalPriceDesc;
	}

	public void setTotalPriceDesc(String totalPriceDesc) {
		this.totalPriceDesc = totalPriceDesc == null ? "0.0" : totalPriceDesc;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total == null ? "0.0" : total;
	}

	public String getTotalIncludeTax() {
		return totalIncludeTax;
	}

	public void setTotalIncludeTax(String totalIncludeTax) {
		this.totalIncludeTax = totalIncludeTax == null ? "" : totalIncludeTax;
	}

	public String getTurnAroundComments() {
		return turnAroundComments;
	}

	public void setTurnAroundComments(String turnAroundComments) {
		this.turnAroundComments = turnAroundComments == null ? ""
				: turnAroundComments;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail == null ? "" : detail;
	}

	public boolean isShowChild() {
		return showChild;
	}

	public void setShowChild(boolean showChild) {
		this.showChild = showChild;
	}

}
