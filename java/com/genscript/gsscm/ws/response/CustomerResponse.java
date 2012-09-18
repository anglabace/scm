package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.customer.dto.AccessStatDTO;
import com.genscript.gsscm.customer.dto.AnalysisDTO;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustContactInfoDTO;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.CustSpecialPriceDTO;
import com.genscript.gsscm.customer.dto.DefaultAddressDTO;
import com.genscript.gsscm.customer.dto.PageViewDTO;
import com.genscript.gsscm.customer.dto.PaymentTermDTO;
import com.genscript.gsscm.customer.entity.CustInfoStat;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.customer.entity.CustomerCase;
import com.genscript.gsscm.customer.entity.CustomerGrants;
import com.genscript.gsscm.customer.entity.CustomerInterest;
import com.genscript.gsscm.customer.entity.CustomerPublications;
import com.genscript.gsscm.product.dto.ProductViewDTO;
import com.genscript.gsscm.serv.dto.ServiceViewDTO;

@XmlType(name = "CustomerResponse", namespace = WsConstants.NS)
public class CustomerResponse extends WsResponse {
	private PageDTO pagerDTO;
	private Page<CustomerBean> pagerCustomer;
	private CustomerBean[] customerList;
	private CustomerDTO customer;
	private Integer custNo;
	private Integer delTotal;
	private Integer delSuccessCount;
	List<CustAddrOperDTO> addrList;
	List<PaymentTermDTO> paymentTermList;
	private List<CustomerGrants> custGrantsList;
	private List<CustomerInterest> custIntList;
	private CustInfoStat custInfoStat;
	private List<CustomerCase> custCaseList;
	private List<ContactDTO> custContactList;
	private CustContactInfoDTO custContactInfo;
	private List<CustomerPublications> custPublicationsList;	
	private List<AnalysisDTO> analysisList;
	private ContactActivity contactActivity;
	private AccessStatDTO accessStat;
	private List<PageViewDTO> pageViewList;
	private List<ProductViewDTO> productViewList;
	private List<ServiceViewDTO> serviceViewList;
	private List<AnalysisReport> analysisReportList;
	private Page<CustSpecialPriceDTO> pdtSpecialPricePage;
	private Page<CustSpecialPriceDTO> srvSpecialPricePage;
	private CustSpecialPriceDTO specialPrice;
	private Integer paramId;
	private DefaultAddressDTO defaultAddressDTO;
	private List<CustNoteDTO> custNoteList;
	public CustInfoStat getCustInfoStat() {
		return custInfoStat;
	}
	public void setCustInfoStat(CustInfoStat custInfoStat) {
		this.custInfoStat = custInfoStat;
	}
	public List<CustomerGrants> getCustGrantsList() {
		return custGrantsList;
	}
	public void setCustGrantsList(List<CustomerGrants> custGrantsList) {
		this.custGrantsList = custGrantsList;
	}
	@XmlElementWrapper(name = "customerBeanList")
	@XmlElement(name = "CustomerBean")
	public CustomerBean[] getCustomerList() {
		return customerList;
	}
	public void setCustomerList(CustomerBean[] customerList) {
		this.customerList = customerList;
	}
	public Page<CustomerBean> getPagerCustomer() {
		return pagerCustomer;
	}
	public void setPagerCustomer(Page<CustomerBean> pagerCustomer) {
		this.pagerCustomer = pagerCustomer;
	}
	
	public PageDTO getPagerDTO() {
		return pagerDTO;
	}
	public void setPagerDTO(PageDTO pagerDTO) {
		this.pagerDTO = pagerDTO;
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
	/**
	 * @return the delTotal
	 */
	public Integer getDelTotal() {
		return delTotal;
	}
	/**
	 * @param delTotal the delTotal to set
	 */
	public void setDelTotal(Integer delTotal) {
		this.delTotal = delTotal;
	}
	/**
	 * @return the delSuccessCount
	 */
	public Integer getDelSuccessCount() {
		return delSuccessCount;
	}
	/**
	 * @param delSuccessCount the delSuccessCount to set
	 */
	public void setDelSuccessCount(Integer delSuccessCount) {
		this.delSuccessCount = delSuccessCount;
	}
	/**
	 * @return the customer
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	/**
	 * @return the addrList
	 */
	public List<CustAddrOperDTO> getAddrList() {
		return addrList;
	}
	/**
	 * @param addrList the addrList to set
	 */
	public void setAddrList(List<CustAddrOperDTO> addrList) {
		this.addrList = addrList;
	}
	/**
	 * @return the paymentTermList
	 */
	public List<PaymentTermDTO> getPaymentTermList() {
		return paymentTermList;
	}
	/**
	 * @param paymentTermList the paymentTermList to set
	 */
	public void setPaymentTermList(List<PaymentTermDTO> paymentTermList) {
		this.paymentTermList = paymentTermList;
	}
	/**
	 * @return the custPublicationsList
	 */
	public List<CustomerPublications> getCustPublicationsList() {
		return custPublicationsList;
	}
	/**
	 * @param custPublicationsList the custPublicationsList to set
	 */
	public void setCustPublicationsList(
			List<CustomerPublications> custPublicationsList) {
		this.custPublicationsList = custPublicationsList;
	}
	/**
	 * @return the custCaseList
	 */
	public List<CustomerCase> getCustCaseList() {
		return custCaseList;
	}
	/**
	 * @param custCaseList the custCaseList to set
	 */
	public void setCustCaseList(List<CustomerCase> custCaseList) {
		this.custCaseList = custCaseList;
	}
	/**
	 * @return the custIntList
	 */
	public List<CustomerInterest> getCustIntList() {
		return custIntList;
	}
	/**
	 * @param custIntList the custIntList to set
	 */
	public void setCustIntList(List<CustomerInterest> custIntList) {
		this.custIntList = custIntList;
	}
	/**
	 * @return the custContactList
	 */
	public List<ContactDTO> getCustContactList() {
		return custContactList;
	}
	/**
	 * @param custContactList the custContactList to set
	 */
	public void setCustContactList(List<ContactDTO> custContactList) {
		this.custContactList = custContactList;
	}
	/**
	 * @return the custContactInfo
	 */
	public CustContactInfoDTO getCustContactInfo() {
		return custContactInfo;
	}
	/**
	 * @param custContactInfo the custContactInfo to set
	 */
	public void setCustContactInfo(CustContactInfoDTO custContactInfo) {
		this.custContactInfo = custContactInfo;
	}
	/**
	 * @return the analysisList
	 */
	public List<AnalysisDTO> getAnalysisList() {
		return analysisList;
	}
	/**
	 * @param analysisList the analysisList to set
	 */
	public void setAnalysisList(List<AnalysisDTO> analysisList) {
		this.analysisList = analysisList;
	}
	/**
	 * @return the contactActivity
	 */
	public ContactActivity getContactActivity() {
		return contactActivity;
	}
	/**
	 * @param contactActivity the contactActivity to set
	 */
	public void setContactActivity(ContactActivity contactActivity) {
		this.contactActivity = contactActivity;
	}
	/**
	 * @return the accessStat
	 */
	public AccessStatDTO getAccessStat() {
		return accessStat;
	}
	/**
	 * @param accessStat the accessStat to set
	 */
	public void setAccessStat(AccessStatDTO accessStat) {
		this.accessStat = accessStat;
	}
	/**
	 * @return the pageViewList
	 */
	public List<PageViewDTO> getPageViewList() {
		return pageViewList;
	}
	/**
	 * @param pageViewList the pageViewList to set
	 */
	public void setPageViewList(List<PageViewDTO> pageViewList) {
		this.pageViewList = pageViewList;
	}
	/**
	 * @return the productViewList
	 */
	public List<ProductViewDTO> getProductViewList() {
		return productViewList;
	}
	/**
	 * @param productViewList the productViewList to set
	 */
	public void setProductViewList(List<ProductViewDTO> productViewList) {
		this.productViewList = productViewList;
	}
	/**
	 * @return the serviceViewList
	 */
	public List<ServiceViewDTO> getServiceViewList() {
		return serviceViewList;
	}
	/**
	 * @param serviceViewList the serviceViewList to set
	 */
	public void setServiceViewList(List<ServiceViewDTO> serviceViewList) {
		this.serviceViewList = serviceViewList;
	}
	/**
	 * @return the analysisReportList
	 */
	public List<AnalysisReport> getAnalysisReportList() {
		return analysisReportList;
	}
	/**
	 * @param analysisReportList the analysisReportList to set
	 */
	public void setAnalysisReportList(List<AnalysisReport> analysisReportList) {
		this.analysisReportList = analysisReportList;
	}
	/**
	 * @return the pdtSpecialPricePage
	 */
	public Page<CustSpecialPriceDTO> getPdtSpecialPricePage() {
		return pdtSpecialPricePage;
	}
	/**
	 * @param pdtSpecialPricePage the pdtSpecialPricePage to set
	 */
	public void setPdtSpecialPricePage(Page<CustSpecialPriceDTO> pdtSpecialPricePage) {
		this.pdtSpecialPricePage = pdtSpecialPricePage;
	}
	/**
	 * @return the srvSpecialPricePage
	 */
	public Page<CustSpecialPriceDTO> getSrvSpecialPricePage() {
		return srvSpecialPricePage;
	}
	/**
	 * @param srvSpecialPricePage the srvSpecialPricePage to set
	 */
	public void setSrvSpecialPricePage(Page<CustSpecialPriceDTO> srvSpecialPricePage) {
		this.srvSpecialPricePage = srvSpecialPricePage;
	}
	/**
	 * @return the specialPrice
	 */
	public CustSpecialPriceDTO getSpecialPrice() {
		return specialPrice;
	}
	/**
	 * @param specialPrice the specialPrice to set
	 */
	public void setSpecialPrice(CustSpecialPriceDTO specialPrice) {
		this.specialPrice = specialPrice;
	}
	/**
	 * @return the paramId
	 */
	public Integer getParamId() {
		return paramId;
	}
	/**
	 * @param paramId the paramId to set
	 */
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	public DefaultAddressDTO getDefaultAddressDTO() {
		return defaultAddressDTO;
	}
	public void setDefaultAddressDTO(DefaultAddressDTO defaultAddressDTO) {
		this.defaultAddressDTO = defaultAddressDTO;
	}
	/**
	 * @return the custNoteList
	 */
	public List<CustNoteDTO> getCustNoteList() {
		return custNoteList;
	}
	/**
	 * @param custNoteList the custNoteList to set
	 */
	public void setCustNoteList(List<CustNoteDTO> custNoteList) {
		this.custNoteList = custNoteList;
	}

	
	
}
