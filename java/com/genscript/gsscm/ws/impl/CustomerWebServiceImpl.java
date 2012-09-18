package com.genscript.gsscm.ws.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.contact.dto.ContactActivity;
import com.genscript.gsscm.contact.dto.ContactDTO;
import com.genscript.gsscm.customer.dto.AccessStatDTO;
import com.genscript.gsscm.customer.dto.AnalysisDTO;
import com.genscript.gsscm.customer.dto.AnalysisReport;
import com.genscript.gsscm.customer.dto.AnalysisReportSrchDTO;
import com.genscript.gsscm.customer.dto.AnalysisSrchDTO;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustAddrSrchDTO;
import com.genscript.gsscm.customer.dto.CustContactInfoDTO;
import com.genscript.gsscm.customer.dto.CustNoteDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.CustSpecialPriceDTO;
import com.genscript.gsscm.customer.dto.DefaultAddressDTO;
import com.genscript.gsscm.customer.dto.PageViewDTO;
import com.genscript.gsscm.customer.dto.PaymentTermDTO;
import com.genscript.gsscm.customer.entity.AccessAnalysis;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.entity.CustInfoStat;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerBean;
import com.genscript.gsscm.customer.entity.CustomerCase;
import com.genscript.gsscm.customer.entity.CustomerContactHistory;
import com.genscript.gsscm.customer.entity.CustomerGrants;
import com.genscript.gsscm.customer.entity.CustomerInterest;
import com.genscript.gsscm.customer.entity.CustomerPublications;
import com.genscript.gsscm.customer.entity.CustomerSpecialPrice;
import com.genscript.gsscm.customer.service.AddressService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.product.dto.ProductViewDTO;
import com.genscript.gsscm.serv.dto.ServiceViewDTO;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.CustomerWebService;
import com.genscript.gsscm.ws.request.CustomerRequest;
import com.genscript.gsscm.ws.response.CustomerResponse;

@WebService(serviceName = "CustomerService", portName = "CustomerServicePort", endpointInterface = "com.genscript.gsscm.ws.api.CustomerWebService", targetNamespace = WsConstants.NS)
public class CustomerWebServiceImpl implements CustomerWebService {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private ExceptionService exceptionUtil;
	private final static String SEPARATE = ",";

	// @Autowired
	// private DozerBeanMapper dozer;

	@Override
	public CustomerResponse searchCustomerList(CustomerRequest customerRequest) {
		Page<CustomerBean> page = null;
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		Map<String, String> fiterMap = new HashMap<String, String>();
		Page<CustomerBean> pageCustom = customerRequest.getPageCustomerBean();
		if (!pageCustom.isOrderBySetted()) {
			pageCustom.setOrderBy("id");
			pageCustom.setOrder(Page.ASC);
		}
		try {
			List<SearchProperty> srchPropList = customerRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = customerService.searchCustomer(pageCustom);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propertyName = searchProperty.getPropertyName();
					String searchOperator = searchProperty.getSearchOperator()
							.name();
					String propertyType = searchProperty
							.getSearchPropertyType().name();
					String propertyValue1 = searchProperty.getPropertyValue1();
					String propertyValue2 = searchProperty.getPropertyValue2();
					if ("BETWEEN".equals(searchOperator)) {
						StringBuilder searchString1 = new StringBuilder();
						StringBuilder searchString2 = new StringBuilder();
						searchString1.append("GE").append(propertyType).append(
								"_").append(propertyName);
						searchString2.append("LE").append(propertyType).append(
								"_").append(propertyName);
						fiterMap.put(searchString1.toString(), propertyValue1
								.toString());
						fiterMap.put(searchString2.toString(), propertyValue2
								.toString());
					} else {
						StringBuilder searchString = new StringBuilder();
						searchString.append(searchOperator)
								.append(propertyType).append("_").append(
										propertyName);
						fiterMap.put(searchString.toString(), propertyValue1
								.toString());
					}
				}
				page = customerService.searchCustomer(pageCustom, fiterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			customerResponse.setPagerDTO(pageDTO);
			List<CustomerBean> list = page.getResult();
			final int size = list.size();
			customerResponse.setCustomerList(page.getResult().toArray(
					new CustomerBean[size]));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0110",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.genscript.gsscm.ws.api.CustomerWebService#saveCustomer(com.genscript
	 * .gsscm.ws.request.CustomerRequest)
	 */
	@Override
	public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		try {
			Integer userId = customerRequest.getUserId();
			CustomerDTO custOperDTO = customerRequest.getCustOperDTO();
			custOperDTO.setModifiedBy(userId);
			Customer cust = customerService.saveCustomer(custOperDTO);
			customerResponse.setHasException(false);
			customerResponse.setCustNo(cust.getCustNo());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0109",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustmerDetail(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer customerNo = customerRequest.getCustomerNo();
		Integer loginUserId = customerRequest.getUserId();
		try {
			CustomerDTO customerDTO = customerService
					.getCustomerDetail(customerNo);
			customerResponse.setCustomer(customerDTO);
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0108",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse delCustomer(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		String customerNos = customerRequest.getCustomerNoStr();
		String[] customerNoArray = StringUtils.split(customerNos, SEPARATE);
		int delSuccessCount = 0;
		int delTotal = 0;
		try {
			if (customerNoArray != null) {
				delTotal = customerNoArray.length;
			}
			Integer userId = customerRequest.getUserId();
			for (String customerNo : customerNoArray) {
				Integer custNo = Integer.valueOf(customerNo);
				delSuccessCount += customerService.delCustomer(userId, custNo);
			}
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0107",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		customerResponse.setDelSuccessCount(delSuccessCount);
		customerResponse.setDelTotal(delTotal);
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustAddrList(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		try {
			CustAddrSrchDTO addrSrchDTO = customerRequest.getCustAddrSrchDTO();
			if (addrSrchDTO == null) {
				addrSrchDTO = new CustAddrSrchDTO();
			}
			List<CustAddrOperDTO> dtoList = this.addressService
					.getAddrList(addrSrchDTO);
			customerResponse.setAddrList(dtoList);
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0102",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getTermsList(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		try {
			List<PaymentTermDTO> dtoList = this.customerService
					.getPaymentTermList();
			customerResponse.setPaymentTermList(dtoList);
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0112",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustGrantsList(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		Page<CustomerGrants> page;
		PageDTO pageCustGrants;
		List<CustomerGrants> custGrantsList;
		try {
			Integer custNo = customerRequest.getCustomerNo();
			page = customerRequest.getPageCustGrants();
			page = customerService.listCustGrantsOld(page, custNo);
			custGrantsList = page.getResult();
			pageCustGrants = (PageDTO) dozer.map(page, PageDTO.class);
			customerResponse.setCustGrantsList(custGrantsList);
			customerResponse.setPagerDTO(pageCustGrants);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0105",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustInfoStat(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		CustInfoStat custInfoStat;
		Integer custNo;
		try {
			custNo = customerRequest.getCustomerNo();
			custInfoStat = customerService.getCustInfoStat(custNo);
			customerResponse.setCustInfoStat(custInfoStat);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0103",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustPublicationsList(
			CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		Page<CustomerPublications> page;
		PageDTO pageCustGrants;
		List<CustomerPublications> custPubsList;
		try {
			Integer custNo = customerRequest.getCustomerNo();
			page = customerRequest.getPageCustPubs();
			page = customerService.getCustPublicationsListOld(page, custNo);
			custPubsList = page.getResult();
			pageCustGrants = (PageDTO) dozer.map(page, PageDTO.class);
			customerResponse.setCustPublicationsList(custPubsList);
			customerResponse.setPagerDTO(pageCustGrants);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0106",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustCaseList(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<CustomerCase> page = customerRequest.getPageCustCase();
		if (!page.isOrderBySetted()) {
			page.setOrderBy("caseId");
			page.setOrder(Page.ASC);
		}
		try {
			List<SearchProperty> srchPropList = customerRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = this.customerService.searchCustCase(page);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();
					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = this.customerService.searchCustCase(page, filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			customerResponse.setCustCaseList(page.getResult());
			customerResponse.setPagerDTO(pageDTO);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0104",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getCustIntList(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		List<CustomerInterest> custIntList;
		try {
			Integer custNo = customerRequest.getCustomerNo();
			custIntList = this.customerService.getCustIntList(custNo);
			customerResponse.setCustIntList(custIntList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0113",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerResponse getCustContactList(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse customerResponse = new CustomerResponse();
		try {
			
			Integer custNo = customerRequest.getCustomerNo();
			PageDTO pageParam = customerRequest.getPageParam();
			Page<CustomerContactHistory> page = this.dozer.map(pageParam, Page.class);
			Page<ContactDTO> dtoPage = customerService.getCustContactList(custNo, page);
			List<ContactDTO> dtoList = dtoPage.getResult();
			PageDTO pageResp = (PageDTO) dozer.map(dtoPage, PageDTO.class);						
			customerResponse.setCustContactList(dtoList);
			customerResponse.setPagerDTO(pageResp);
			CustContactInfoDTO dto = this.customerService
					.getContactInfo(custNo);
			customerResponse.setCustContactInfo(dto);
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0101",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse getAccessStat(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			AccessStatDTO accessStat = this.customerService
					.getAccessStat(custNo);
			response.setAccessStat(accessStat);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerResponse searchAccess(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			PageDTO pageDTO = customerRequest.getPageParam();
			AnalysisSrchDTO analysisSrch = customerRequest.getAnalysisSrch();
			Page<AccessAnalysis> page = dozer.map(pageDTO, Page.class);
			Page<AnalysisDTO> pageAccessList = this.customerService
					.searchAccess(analysisSrch, page);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageAccessList,
					PageDTO.class);
			response.setPagerDTO(pagerInfo);
			response.setAnalysisList(pageAccessList.getResult());
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public CustomerResponse getContactHistory(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			ContactActivity contactActivity = this.customerService
					.getContactActivity(custNo);
			response.setHasException(Boolean.FALSE);
			response.setContactActivity(contactActivity);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerResponse getPageAccess(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			PageDTO pageDTO = customerRequest.getPageParam();
			Page<AccessAnalysis> page = dozer.map(pageDTO, Page.class);
			Page<PageViewDTO> pageView = this.customerService
					.getAccessPageView(page, custNo);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageView, PageDTO.class);
			response.setPageViewList(pageView.getResult());
			response.setPagerDTO(pagerInfo);
			response.setHasException(false);
		} catch (Exception e) {
			e.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerResponse getProductAccess(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			PageDTO pageDTO = customerRequest.getPageParam();
			Page<AccessAnalysis> page = dozer.map(pageDTO, Page.class);
			Page<ProductViewDTO> pageView = this.customerService
					.getAccessProductView(page, custNo);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageView, PageDTO.class);
			response.setProductViewList(pageView.getResult());
			response.setPagerDTO(pagerInfo);
			response.setHasException(false);
		} catch (Exception e) {
			e.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerResponse getServiceAccess(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			PageDTO pageDTO = customerRequest.getPageParam();
			Page<AccessAnalysis> page = dozer.map(pageDTO, Page.class);
			Page<ServiceViewDTO> pageView = this.customerService
					.getAccessServiceView(page, custNo);
			PageDTO pagerInfo = (PageDTO) dozer.map(pageView, PageDTO.class);
			response.setServiceViewList(pageView.getResult());
			response.setPagerDTO(pagerInfo);
			response.setHasException(false);
		} catch (Exception e) {
			e.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public CustomerResponse searchAnalysis(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			PageDTO pageDTO = customerRequest.getPageParam();
			AnalysisReportSrchDTO srchDTO = customerRequest
					.getAnalysisReportSrch();
			List<AnalysisReport> reportList = this.customerService
					.searchAnalysis(pageDTO, srchDTO);
			pageDTO.setTotalCount(0L+reportList.size());
			response.setPagerDTO(pageDTO);
			response.setAnalysisReportList(reportList);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerResponse getSpecialPriceList(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			PageDTO pageParam = customerRequest.getPageParam();
			Page<CustomerSpecialPrice> page = this.dozer.map(pageParam, Page.class);
			Page<CustSpecialPriceDTO> pdtSpecialPricePage = customerService.getProductSpecialPriceList(custNo, page);
			//Special Price of Service.
			Page<CustomerSpecialPrice> srvPageParam = customerRequest.getPageSrvSpecialPrice();
			Page<CustSpecialPriceDTO> srvSpecialPricePage = customerService.getServiceSpecialPriceList(custNo, srvPageParam);
			response.setPdtSpecialPricePage(pdtSpecialPricePage);
			response.setSrvSpecialPricePage(srvSpecialPricePage);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public CustomerResponse getSpecialPrice(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			String catalogNo = customerRequest.getCatalogNo();
			CustSpecialPriceDTO specialPrice = this.customerService.getSpecialPrice(custNo, catalogNo);
            response.setSpecialPrice(specialPrice);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	/**
	 * 保存一个Customer的CreditCard.
	 */
	@Override
	public CustomerResponse saveCreditCard(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			CreditCard creditCard = customerRequest.getCreditCard();
			Integer id = this.customerService.saveCreditCard(creditCard, loginUserId);
			response.setParamId(id);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	/**
	 * INACTIVE一个Customer的CreditCard.
	 */
	@Override
	public CustomerResponse delCreditCard(CustomerRequest customerRequest) {
		Integer loginUserId = customerRequest.getUserId();
		CustomerResponse response = new CustomerResponse();
		try {
			Integer id = customerRequest.getParamId();
			this.customerService.delCreditCard(id, loginUserId);
			response.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0204",
					loginUserId);
			response.setHasException(true);
			response.setWsException(exDTO);
		}
		return response;
	}

	@Override
	public CustomerResponse getDefaultAddress(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		DefaultAddressDTO defaultAddressDTO;
		Integer custNo;
		try {
			custNo = customerRequest.getCustomerNo();
			defaultAddressDTO = customerService.getDefaultAddress(custNo);
			customerResponse.setDefaultAddressDTO(defaultAddressDTO);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0103",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	/**
	 * 获得一个Customer的所有note， 每个Note又包含documentList.
	 */
	@Override
	public CustomerResponse getNoteList(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		try {
			Integer custNo = customerRequest.getCustomerNo();
			List<CustNoteDTO> custNoteList = this.customerService.getNoteList(custNo);
			customerResponse.setCustNoteList(custNoteList);
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0103",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}

	/**
	 * 合并多个Customer.
	 */
	@Override
	public CustomerResponse combineCustomer(CustomerRequest customerRequest) {
		CustomerResponse customerResponse = new CustomerResponse();
		Integer loginUserId = customerRequest.getUserId();
		try {
		    List<Integer> slaveNoList = customerRequest.getFromCustNoList();
		    Integer masterNo = customerRequest.getToCustNo();
			this.customerService.combineCustomer(masterNo, slaveNoList, loginUserId);
			customerResponse.setCustNo(masterNo);
			customerResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e, new Exception()
					.getStackTrace()[0].getMethodName(), "INTF0103",
					loginUserId);
			customerResponse.setHasException(true);
			customerResponse.setWsException(exDTO);
		}
		return customerResponse;
	}
}
