package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.customer.dto.AnalysisReportSrchDTO;
import com.genscript.gsscm.customer.dto.AnalysisSrchDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.customer.entity.CustomerCase;
import com.genscript.gsscm.customer.entity.CustomerGrants;
import com.genscript.gsscm.customer.entity.CustomerPublications;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;

@XmlType(name = "CustomerRequest", namespace = WsConstants.NS)
public class CustomerRequest extends WsRequest {

	private Page<CustomerBean> pageCustomerBean;
	private List<SearchProperty> searchPropertyList;
	private CustAddrSrchDTO custAddrSrchDTO;
	private CustomerDTO custOperDTO;	
	private Integer customerNo;
    private String customerNoStr;    
    private CustomerGrants custGrants;
    private Page<CustomerGrants> pageCustGrants;
    private Page<CustomerPublications> pageCustPubs;    
    private Page<CustomerCase> pageCustCase;  
    private List<Integer> custGrantsIdList;
    private PageDTO pageParam;  
    private AnalysisSrchDTO analysisSrch;
    private AnalysisReportSrchDTO analysisReportSrch;
    private Page<CustomerSpecialPrice> pageSrvSpecialPrice;
    private String catalogNo;
    private CreditCard creditCard;
    private Integer paramId;
    private List<Integer> fromCustNoList;
    private Integer toCustNo;
	public List<Integer> getCustGrantsIdList() {
		return custGrantsIdList;
	}
	public void setCustGrantsIdList(List<Integer> custGrantsIdList) {
		this.custGrantsIdList = custGrantsIdList;
	}
	public CustomerGrants getCustGrants() {
		return custGrants;
	}
	public void setCustGrants(CustomerGrants custGrants) {
		this.custGrants = custGrants;
	}
	public Page<CustomerGrants> getPageCustGrants() {
		return pageCustGrants;
	}
	public void setPageCustGrants(Page<CustomerGrants> pageCustGrants) {
		this.pageCustGrants = pageCustGrants;
	}
	public String getCustomerNoStr() {
		return customerNoStr;
	}
	public void setCustomerNoStr(String customerNoStr) {
		this.customerNoStr = customerNoStr;
	}
	
	public Integer getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(Integer customerNo) {
		this.customerNo = customerNo;
	}
	
	public CustomerDTO getCustOperDTO() {
		return custOperDTO;
	}
	public void setCustOperDTO(CustomerDTO custOperDTO) {
		this.custOperDTO = custOperDTO;
	}
	public Page<CustomerBean> getPageCustomerBean() {
		return pageCustomerBean;
	}
	public void setPageCustomerBean(Page<CustomerBean> pageCustomerBean) {
		this.pageCustomerBean = pageCustomerBean;
	}
	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}
	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
	}
	/**
	 * @return the custAddrSrchDTO
	 */
	public CustAddrSrchDTO getCustAddrSrchDTO() {
		return custAddrSrchDTO;
	}
	/**
	 * @param custAddrSrchDTO the custAddrSrchDTO to set
	 */
	public void setCustAddrSrchDTO(CustAddrSrchDTO custAddrSrchDTO) {
		this.custAddrSrchDTO = custAddrSrchDTO;
	}
	/**
	 * @return the pageCustPubs
	 */
	public Page<CustomerPublications> getPageCustPubs() {
		return pageCustPubs;
	}
	/**
	 * @param pageCustPubs the pageCustPubs to set
	 */
	public void setPageCustPubs(Page<CustomerPublications> pageCustPubs) {
		this.pageCustPubs = pageCustPubs;
	}
	/**
	 * @return the pageCustCase
	 */
	public Page<CustomerCase> getPageCustCase() {
		return pageCustCase;
	}
	/**
	 * @param pageCustCase the pageCustCase to set
	 */
	public void setPageCustCase(Page<CustomerCase> pageCustCase) {
		this.pageCustCase = pageCustCase;
	}
	/**
	 * @return the pageParam
	 */
	public PageDTO getPageParam() {
		return pageParam;
	}
	/**
	 * @param pageParam the pageParam to set
	 */
	public void setPageParam(PageDTO pageParam) {
		this.pageParam = pageParam;
	}
	/**
	 * @return the analysisSrch
	 */
	public AnalysisSrchDTO getAnalysisSrch() {
		return analysisSrch;
	}
	/**
	 * @param analysisSrch the analysisSrch to set
	 */
	public void setAnalysisSrch(AnalysisSrchDTO analysisSrch) {
		this.analysisSrch = analysisSrch;
	}
	/**
	 * @return the analysisReportSrch
	 */
	public AnalysisReportSrchDTO getAnalysisReportSrch() {
		return analysisReportSrch;
	}
	/**
	 * @param analysisReportSrch the analysisReportSrch to set
	 */
	public void setAnalysisReportSrch(AnalysisReportSrchDTO analysisReportSrch) {
		this.analysisReportSrch = analysisReportSrch;
	}
	/**
	 * @return the pageSrvSpecialPrice
	 */
	public Page<CustomerSpecialPrice> getPageSrvSpecialPrice() {
		return pageSrvSpecialPrice;
	}
	/**
	 * @param pageSrvSpecialPrice the pageSrvSpecialPrice to set
	 */
	public void setPageSrvSpecialPrice(
			Page<CustomerSpecialPrice> pageSrvSpecialPrice) {
		this.pageSrvSpecialPrice = pageSrvSpecialPrice;
	}
	/**
	 * @return the catalogNo
	 */
	public String getCatalogNo() {
		return catalogNo;
	}
	/**
	 * @param catalogNo the catalogNo to set
	 */
	public void setCatalogNo(String catalogNo) {
		this.catalogNo = catalogNo;
	}
	/**
	 * @return the creditCard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
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
	/**
	 * @return the fromCustNoList
	 */
	public List<Integer> getFromCustNoList() {
		return fromCustNoList;
	}
	/**
	 * @param fromCustNoList the fromCustNoList to set
	 */
	public void setFromCustNoList(List<Integer> fromCustNoList) {
		this.fromCustNoList = fromCustNoList;
	}
	/**
	 * @return the toCustNo
	 */
	public Integer getToCustNo() {
		return toCustNo;
	}
	/**
	 * @param toCustNo the toCustNo to set
	 */
	public void setToCustNo(Integer toCustNo) {
		this.toCustNo = toCustNo;
	}

}
