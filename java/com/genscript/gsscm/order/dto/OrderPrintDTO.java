package com.genscript.gsscm.order.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.common.constant.WsConstants;

@XmlType(name = "OrderPrintDTO", namespace = WsConstants.NS)
public class OrderPrintDTO implements Serializable {

	private static long serialVersionUID = 3331174486402662729L;
	//QID
	private String qId;
	//INIT_DATE
	private String initDate;
	//EXP_DATE
	private String expDate;
	//FIRSTNAME
	private String firstName;
	//LASTNAME
	private String lastName;
	//EMAIL
	private String email;
	//DEPARTMENT
	private String department;
	//INSTITUTION
	private String institution;
	//STATE
	private String state;
	//COUNTRY
	private String country;
	//result
	private String result;
	//COMPANY_NAME
	private String companyName;
	//NAME
	private String name;
	//symbol
	private String symbol;
	//TURN_AROUND
	private String turnAround;
	//TABLE_BODY
	private String tableBody;
	//_SubtotalNAME
	private String subTotalName;
	//SUB_PRICE
	private String subprice;
	//SUB_DISCOUNT
	private String subDiscount;
	//SUB_TOTAL
	private String subTotal;
	//SHIP_PRICE_DEPARTMENT
	private String shipPriceDepartment;
	//TAX_DEPARTMENT
	private String taxDepartment;
	//_Total_QuoteNAME
	private String totalQuoteName;
	//total_price_desc
	private String totalPriceDesc;
	//TOTAL
	private String total;
	//TOTAL_INCLUDE_TAX
	private String totalIncludeTax;
	//comments
	private String comments;
	//detail
	private String detail;
	//ship via
	private String shipVia;
	//prepare by
	private String address1;
	private String address2;
	private String telephone;
	private String fax;
	private String custEmail;
	private String web;
	private String preparedByName;
	private String techManager;
	
	private boolean showChild=false;
	
	public OrderPrintDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getQId() {
		return qId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public String getPreparedByName() {
		return preparedByName;
	}
	public void setPreparedByName(String preparedByName) {
		this.preparedByName = preparedByName;
	}
	public String getTechManager() {
		return techManager;
	}
	public void setTechManager(String techManager) {
		this.techManager = techManager;
	}
	public void setQId(String id) {
		qId = id==null?"":id;
	}
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate==null?"":initDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate==null?"":expDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName==null?"":firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName==null?"":lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email==null?"":email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department==null?"":department;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution==null?"":institution;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state==null?"":state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country==null?"":country;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result==null?"":result;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName==null?"":companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name==null?"":name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol==null?"":symbol;
	}
	public String getTurnAround() {
		return turnAround;
	}
	public void setTurnAround(String turnAround) {
		this.turnAround = turnAround==null?"":turnAround;
	}
	public String getTableBody() {
		return tableBody;
	}
	public void setTableBody(String tableBody) {
		this.tableBody = tableBody==null?"":tableBody;
	}
	public String getSubTotalName() {
		return subTotalName;
	}
	public void setSubTotalName(String subTotalName) {
		this.subTotalName = subTotalName==null?"":subTotalName;
	}
	public String getSubprice() {
		return subprice;
	}
	public void setSubprice(String subprice) {
		this.subprice = subprice==null?"0.0":subprice;
	}
	public String getSubDiscount() {
		return subDiscount;
	}
	public void setSubDiscount(String subDiscount) {
		this.subDiscount = subDiscount==null?"0.0":subDiscount;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal==null?"0.0":subTotal;
	}
	public String getShipPriceDepartment() {
		return shipPriceDepartment;
	}
	public void setShipPriceDepartment(String shipPriceDepartment) {
		this.shipPriceDepartment = shipPriceDepartment==null?"":shipPriceDepartment;
	}
	public String getTaxDepartment() {
		return taxDepartment;
	}
	public void setTaxDepartment(String taxDepartment) {
		this.taxDepartment = taxDepartment==null?"":taxDepartment;
	}
	public String getTotalQuoteName() {
		return totalQuoteName;
	}
	public void setTotalQuoteName(String totalQuoteName) {
		this.totalQuoteName = totalQuoteName==null?"":totalQuoteName;
	}
	public String getTotalPriceDesc() {
		return totalPriceDesc;
	}
	public void setTotalPriceDesc(String totalPriceDesc) {
		this.totalPriceDesc = totalPriceDesc==null?"0.0":totalPriceDesc;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total==null?"0.0":total;
	}
	public String getTotalIncludeTax() {
		return totalIncludeTax;
	}
	public void setTotalIncludeTax(String totalIncludeTax) {
		this.totalIncludeTax = totalIncludeTax==null?"":totalIncludeTax;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments==null?"":comments;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail==null?"":detail;
	}
	public boolean isShowChild() {
		return showChild;
	}
	public void setShowChild(boolean showChild) {
		this.showChild = showChild;
	}
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getShipVia() {
		return shipVia;
	}
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}


}
