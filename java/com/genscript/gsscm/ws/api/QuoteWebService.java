package com.genscript.gsscm.ws.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.ws.request.QuoteRequest;
import com.genscript.gsscm.ws.response.QuoteResponse;

@WebService(name = "QuoteService", targetNamespace = WsConstants.NS)
public interface QuoteWebService {
	
	public QuoteResponse searchQuoteList(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse searchItemPrice(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getQuoteDetail(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);	
	public QuoteResponse delQuote(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);	
	public QuoteResponse getItemStatusHist(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getQuoteAddress(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);	
	public QuoteResponse getQuoteStatusHist(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse calculateQuoteItemPrice(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getAddressListByType(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getInstructionList(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse saveQuote(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getItemDiscount(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getPayInfoList(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getItemOtherInfo(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse saveQuoteTemplate(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse updateQuoteStatus(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getQuoteTotals(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getTotalProducts(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse convertToOrder(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse changeCurrency(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse calShippingPackage(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getPackageListForItemList(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getShippingTotal(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getShippingTotalByPage(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse repeatQuote(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse delQuoteTemplate(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
	public QuoteResponse getTemplateList(@WebParam(name="quoteRequest")QuoteRequest quoteRequest);
}
