package com.genscript.gsscm.epicorwebservice.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.log.Traced;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.customer.dao.AddressDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Address;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType.CustomerDataSet.Customer;
import com.genscript.gsscm.epicorwebservice.stub.customer.CustomerService;
import com.genscript.gsscm.epicorwebservice.stub.customer.CustomerServiceSoap;
import com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType;
import com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjService;
import com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjServiceSoap;
import com.genscript.gsscm.epicorwebservice.stub.lot.LotSelectUpdateDataSetType;
import com.genscript.gsscm.epicorwebservice.stub.lot.LotSelectUpdateService;
import com.genscript.gsscm.epicorwebservice.stub.lot.LotSelectUpdateServiceSoap;
import com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType.PartDataSet.Part;
import com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType.PartDataSet.PartWhse;
import com.genscript.gsscm.epicorwebservice.stub.part.PartService;
import com.genscript.gsscm.epicorwebservice.stub.part.PartServiceSoap;
import com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType;
import com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType.SalesOrderDataSet.OHOrderMsc;
import com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType.SalesOrderDataSet.OrderDtl;
import com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType.SalesOrderDataSet.OrderHed;
import com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderService;
import com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderServiceSoap;
import com.genscript.gsscm.order.dao.OrderNoteDao;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.OrderNote;
import com.genscript.gsscm.product.dto.ProductDTO;
import com.genscript.gsscm.product.dto.PurchaseOrderDTO;
import com.genscript.gsscm.shipment.dto.LotNoDTO;
import com.genscript.gsscm.shipment.dto.StorageLocationDTO;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;

@Service
@Transactional
public class ErpSalesOrderService implements CallbackHandler {

	@Autowired
	private OrderNoteDao orderNoteDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private BillTerritoryDao billTerritoryDao;
	private String erpUserName;
	private String erpPassword;

	@Traced
	public void createSaleOrder(OrderMainDTO orderDTO,
			Map<String, OrderItemDTO> itemMap, String poNumber) {
		SalesOrderService salesOrderService = new SalesOrderService();
		SalesOrderServiceSoap port = salesOrderService
				.getSalesOrderServiceSoap();
		assertUserNameToken(port);

		System.out.println("Invoking getNewOrderHed...");
		String companyID = "GSUS";
		SalesOrderDataSetType salesOrderDataSetType = null;
		com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType callContextIn = null;
		Holder<SalesOrderDataSetType> getNewOrderHedResult = new Holder<SalesOrderDataSetType>();
		Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> callContextOut = new Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.getNewOrderHed(companyID, salesOrderDataSetType, callContextIn,
				getNewOrderHedResult, callContextOut);
		System.out
				.println("getNewOrderHed._getNewOrderHed_getNewOrderHedResult="
						+ getNewOrderHedResult.value);
		System.out.println("getNewOrderHed._getNewOrderHed_callContextOut="
				+ callContextOut.value);

		System.out.println("Invoking onChangeofSoldToCreditCheck...");
		int iOrderNum = 0;
		String iCustID = "BARRISTON";
		Holder<SalesOrderDataSetType> onChangeofSoldToCreditCheckResult = new Holder<SalesOrderDataSetType>();
		Holder<String> cCreditLimitMessage = new Holder<String>();
		Holder<Boolean> lContinue = new Holder<Boolean>();
		Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _onChangeofSoldToCreditCheck_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.onChangeofSoldToCreditCheck(companyID, iOrderNum, iCustID,
				getNewOrderHedResult.value, callContextOut.value,
				onChangeofSoldToCreditCheckResult, cCreditLimitMessage,
				lContinue, _onChangeofSoldToCreditCheck_callContextOut);

		System.out
				.println("onChangeofSoldToCreditCheck._onChangeofSoldToCreditCheck_onChangeofSoldToCreditCheckResult="
						+ onChangeofSoldToCreditCheckResult.value);
		System.out
				.println("onChangeofSoldToCreditCheck._onChangeofSoldToCreditCheck_cCreditLimitMessage="
						+ cCreditLimitMessage.value);
		System.out
				.println("onChangeofSoldToCreditCheck._onChangeofSoldToCreditCheck_lContinue="
						+ lContinue.value);
		System.out
				.println("onChangeofSoldToCreditCheck._onChangeofSoldToCreditCheck_callContextOut="
						+ _onChangeofSoldToCreditCheck_callContextOut.value);

		System.out.println("Invoking changeSoldToID...");
		// com.genscript.gsscm.epicorwebservice.stub.salesorder.
		// SalesOrderDataSetType _changeSoldToID_ds = null;
		// com.genscript.gsscm.epicorwebservice.stub.salesorder.
		// CallContextDataSetType _changeSoldToID_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _changeSoldToID_changeSoldToIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _changeSoldToID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.changeSoldToID(companyID, onChangeofSoldToCreditCheckResult.value,
				_onChangeofSoldToCreditCheck_callContextOut.value,
				_changeSoldToID_changeSoldToIDResult,
				_changeSoldToID_callContextOut);

		System.out
				.println("changeSoldToID._changeSoldToID_changeSoldToIDResult="
						+ _changeSoldToID_changeSoldToIDResult.value);
		System.out.println("changeSoldToID._changeSoldToID_callContextOut="
				+ _changeSoldToID_callContextOut.value);

		System.out.println("Invoking changeCustomer...");
		// com.genscript.gsscm.epicorwebservice.stub.salesorder.
		// SalesOrderDataSetType _changeCustomer_ds = null;
		// com.genscript.gsscm.epicorwebservice.stub.salesorder.
		// CallContextDataSetType _changeCustomer_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _changeCustomer_changeCustomerResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _changeCustomer_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.changeCustomer(companyID,
				_changeSoldToID_changeSoldToIDResult.value,
				_changeSoldToID_callContextOut.value,
				_changeCustomer_changeCustomerResult,
				_changeCustomer_callContextOut);

		System.out
				.println("changeCustomer._changeCustomer_changeCustomerResult="
						+ _changeCustomer_changeCustomerResult.value);
		System.out.println("changeCustomer._changeCustomer_callContextOut="
				+ _changeCustomer_callContextOut.value);

		System.out.println("Invoking masterUpdate...");
		boolean _masterUpdate_lcheckForResponse = true;
		java.lang.String _masterUpdate_cTableName = "OrderHed";
		int _masterUpdate_iCustNum = 3;
		int _masterUpdate_iOrderNum = 0;
		boolean _masterUpdate_lweLicensed = false;

		OrderHed updateOrderHed = (OrderHed) _changeCustomer_changeCustomerResult.value
				.getSalesOrderDataSet()
				.getOrderHedOrOrderHedAttchOrOHOrderMsc().get(0);
		updateOrderHed.setCurrencyCode(orderDTO.getOrderCurrency());
//		 if(orderDTO.getTax()!= null ){
//		 updateOrderHed.setRateGrpCode("RATE08");
//		 }
		//只有Checkbox01=true的时候，才转换SO去PO
		updateOrderHed.setCheckBox01(true);
		if (poNumber != null) {
			updateOrderHed.setPONum(poNumber);
		}
		List<OrderNote> shpList = orderNoteDao.getOrderNoteList(orderDTO
				.getOrderNo(), OrderInstructionType.SHIPMENT);
		if (shpList != null && !shpList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (OrderNote orderNote : shpList) {
				sb.append(orderNote.getDescription());
			}
			updateOrderHed.setShipComment(sb.toString());
		}
		List<OrderNote> accountList = orderNoteDao.getOrderNoteList(orderDTO
				.getOrderNo(), OrderInstructionType.ACCOUNTING);
		if (accountList != null && !accountList.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (OrderNote orderNote : accountList) {
				sb.append(orderNote.getDescription());
			}
			updateOrderHed.setInvoiceComment(sb.toString());
		}
		// com.genscript.gsscm.epicorwebservice.stub.salesorder.
		// SalesOrderDataSetType _masterUpdate_ds = null;
		// com.genscript.gsscm.epicorwebservice.stub.salesorder.
		// CallContextDataSetType _masterUpdate_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _masterUpdate_masterUpdateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
		javax.xml.ws.Holder<java.lang.Boolean> _masterUpdate_lContinue = new javax.xml.ws.Holder<java.lang.Boolean>();
		javax.xml.ws.Holder<java.lang.String> _masterUpdate_cResponseMsg = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.lang.String> _masterUpdate_cDisplayMsg = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<java.lang.String> _masterUpdate_cCompliantMsg = new javax.xml.ws.Holder<java.lang.String>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _masterUpdate_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.masterUpdate(companyID, _masterUpdate_lcheckForResponse,
				_masterUpdate_cTableName, _masterUpdate_iCustNum,
				_masterUpdate_iOrderNum, _masterUpdate_lweLicensed,
				_changeCustomer_changeCustomerResult.value,
				_changeCustomer_callContextOut.value,
				_masterUpdate_masterUpdateResult, _masterUpdate_lContinue,
				_masterUpdate_cResponseMsg, _masterUpdate_cDisplayMsg,
				_masterUpdate_cCompliantMsg, _masterUpdate_callContextOut);

		System.out.println("masterUpdate._masterUpdate_masterUpdateResult="
				+ _masterUpdate_masterUpdateResult.value);
		System.out.println("masterUpdate._masterUpdate_lContinue="
				+ _masterUpdate_lContinue.value);
		System.out.println("masterUpdate._masterUpdate_cResponseMsg="
				+ _masterUpdate_cResponseMsg.value);
		System.out.println("masterUpdate._masterUpdate_cDisplayMsg="
				+ _masterUpdate_cDisplayMsg.value);
		System.out.println("masterUpdate._masterUpdate_cCompliantMsg="
				+ _masterUpdate_cCompliantMsg.value);
		System.out.println("masterUpdate._masterUpdate_callContextOut="
				+ _masterUpdate_callContextOut.value);

		OrderHed orderHed = (OrderHed) _masterUpdate_masterUpdateResult.value
				.getSalesOrderDataSet()
				.getOrderHedOrOrderHedAttchOrOHOrderMsc().get(0);

		int i = 0;
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			//String sepChar = "-";
			//StringBuilder partNumSB = new StringBuilder();
			//partNumSB.append(orderDTO.getOrderNo()).append(sepChar);
			i++;
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			//partNumSB.append(orderItemDTO.getOrderItemId()).append(sepChar)
			//		.append(orderItemDTO.getCatalogNo());

			System.out.println("Invoking getNewOrderDtl...");
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// SalesOrderDataSetType _getNewOrderDtl_ds = null;
			// OrderHed orderHed =
			// (OrderHed)_masterUpdate_masterUpdateResult.value
			// .getSalesOrderDataSet
			// ().getOrderHedOrOrderHedAttchOrOHOrderMsc().get(0);
			int _getNewOrderDtl_orderNum = orderHed.getOrderNum();
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// CallContextDataSetType _getNewOrderDtl_callContextIn = null;
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _getNewOrderDtl_getNewOrderDtlResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _getNewOrderDtl_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
			port.getNewOrderDtl(companyID,
					_masterUpdate_masterUpdateResult.value,
					_getNewOrderDtl_orderNum,
					_masterUpdate_callContextOut.value,
					_getNewOrderDtl_getNewOrderDtlResult,
					_getNewOrderDtl_callContextOut);

			System.out
					.println("getNewOrderDtl._getNewOrderDtl_getNewOrderDtlResult="
							+ _getNewOrderDtl_getNewOrderDtlResult.value);
			System.out.println("getNewOrderDtl._getNewOrderDtl_callContextOut="
					+ _getNewOrderDtl_callContextOut.value);

			System.out.println("Invoking changePartNumMaster...");
			//java.lang.String _changePartNumMaster_partNumVal = partNumSB
			//		.toString();
			java.lang.String _changePartNumMaster_partNumVal = orderItemDTO.getCatalogNo();
			// 设置价格等信息
			OrderDtl changePriceOrderDtl = (OrderDtl) _getNewOrderDtl_getNewOrderDtlResult.value
					.getSalesOrderDataSet()
					.getOrderHedOrOrderHedAttchOrOHOrderMsc().get(i);
			//如果是多肽产品那么OrderHed.ShortChar01 = GSJK, 非多肽的话OrderHed.ShortChar01 = GSJR
			if("PRODUCT".equalsIgnoreCase(orderItemDTO.getType())){
				//product多肽
				if(orderItemDTO.getClsId() == 2){
					changePriceOrderDtl.setShortChar01("GSJK");
				}else{
					changePriceOrderDtl.setShortChar01("GSJR");
				}
			}else{
				if(orderItemDTO.getClsId() == 1){
					changePriceOrderDtl.setShortChar01("GSJK");
				}else{
					changePriceOrderDtl.setShortChar01("GSJR");
				}
			}
			changePriceOrderDtl.setUnitPrice(orderItemDTO.getUnitPrice());
			//changePriceOrderDtl.setDocDspUnitPrice(orderItemDTO.getUnitPrice());
			changePriceOrderDtl.setDocDspDiscount(orderItemDTO.getDiscount());
			if (orderItemDTO.getTargetDate() != null) {
				changePriceOrderDtl.setNeedByDate(DateUtils
						.convertToXMLGregorianCalendar(orderItemDTO
								.getTargetDate()));
			}
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_partNum = new javax.xml.ws.Holder<java.lang.String>(
					_changePartNumMaster_partNumVal);
			java.lang.Boolean _changePartNumMaster_lSubstitutePartExistVal = false;
			javax.xml.ws.Holder<java.lang.Boolean> _changePartNumMaster_lSubstitutePartExist = new javax.xml.ws.Holder<java.lang.Boolean>(
					_changePartNumMaster_lSubstitutePartExistVal);
			java.lang.Boolean _changePartNumMaster_lIsPhantomVal = false;
			javax.xml.ws.Holder<java.lang.Boolean> _changePartNumMaster_lIsPhantom = new javax.xml.ws.Holder<java.lang.Boolean>(
					_changePartNumMaster_lIsPhantomVal);
			java.lang.String _changePartNumMaster_uomCodeVal = "";
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_uomCode = new javax.xml.ws.Holder<java.lang.String>(
					_changePartNumMaster_uomCodeVal);
			java.lang.String _changePartNumMaster_sysRowID = "";
			java.lang.String _changePartNumMaster_rowType = "";
			boolean _changePartNumMaster_salesKitView = false;
			boolean _changePartNumMaster_removeKitComponents = false;
			boolean _changePartNumMaster_suppressUserPrompts = false;
			boolean _changePartNumMaster_getPartXRefInfo = false;
			boolean _changePartNumMaster_checkPartRevisionChange = false;
			boolean _changePartNumMaster_checkChangeKitParent = false;
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// SalesOrderDataSetType _changePartNumMaster_ds = null;
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// CallContextDataSetType _changePartNumMaster_callContextIn = null;
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _changePartNumMaster_changePartNumMasterResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_cDeleteComponentsMessage = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_questionString = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_cWarningMessage = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.Boolean> _changePartNumMaster_multipleMatch = new javax.xml.ws.Holder<java.lang.Boolean>();
			javax.xml.ws.Holder<java.lang.Boolean> _changePartNumMaster_promptToExplodeBOM = new javax.xml.ws.Holder<java.lang.Boolean>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_cConfigPartMessage = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_cSubPartMessage = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_explodeBOMerrMessage = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changePartNumMaster_cMsgType = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.Boolean> _changePartNumMaster_multiSubsAvail = new javax.xml.ws.Holder<java.lang.Boolean>();
			javax.xml.ws.Holder<java.lang.Boolean> _changePartNumMaster_runOutQtyAvail = new javax.xml.ws.Holder<java.lang.Boolean>();
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _changePartNumMaster_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
			port.changePartNumMaster(companyID, _changePartNumMaster_partNum,
					_changePartNumMaster_lSubstitutePartExist,
					_changePartNumMaster_lIsPhantom,
					_changePartNumMaster_uomCode,
					_changePartNumMaster_sysRowID,
					_changePartNumMaster_rowType,
					_changePartNumMaster_salesKitView,
					_changePartNumMaster_removeKitComponents,
					_changePartNumMaster_suppressUserPrompts,
					_changePartNumMaster_getPartXRefInfo,
					_changePartNumMaster_checkPartRevisionChange,
					_changePartNumMaster_checkChangeKitParent,
					_getNewOrderDtl_getNewOrderDtlResult.value,
					_getNewOrderDtl_callContextOut.value,
					_changePartNumMaster_changePartNumMasterResult,
					_changePartNumMaster_cDeleteComponentsMessage,
					_changePartNumMaster_questionString,
					_changePartNumMaster_cWarningMessage,
					_changePartNumMaster_multipleMatch,
					_changePartNumMaster_promptToExplodeBOM,
					_changePartNumMaster_cConfigPartMessage,
					_changePartNumMaster_cSubPartMessage,
					_changePartNumMaster_explodeBOMerrMessage,
					_changePartNumMaster_cMsgType,
					_changePartNumMaster_multiSubsAvail,
					_changePartNumMaster_runOutQtyAvail,
					_changePartNumMaster_callContextOut);

//			System.out
//					.println("changePartNumMaster._changePartNumMaster_partNum="
//							+ _changePartNumMaster_partNum.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_lSubstitutePartExist="
//							+ _changePartNumMaster_lSubstitutePartExist.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_lIsPhantom="
//							+ _changePartNumMaster_lIsPhantom.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_uomCode="
//							+ _changePartNumMaster_uomCode.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_changePartNumMasterResult="
//							+ _changePartNumMaster_changePartNumMasterResult.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_cDeleteComponentsMessage="
//							+ _changePartNumMaster_cDeleteComponentsMessage.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_questionString="
//							+ _changePartNumMaster_questionString.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_cWarningMessage="
//							+ _changePartNumMaster_cWarningMessage.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_multipleMatch="
//							+ _changePartNumMaster_multipleMatch.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_promptToExplodeBOM="
//							+ _changePartNumMaster_promptToExplodeBOM.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_cConfigPartMessage="
//							+ _changePartNumMaster_cConfigPartMessage.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_cSubPartMessage="
//							+ _changePartNumMaster_cSubPartMessage.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_explodeBOMerrMessage="
//							+ _changePartNumMaster_explodeBOMerrMessage.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_cMsgType="
//							+ _changePartNumMaster_cMsgType.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_multiSubsAvail="
//							+ _changePartNumMaster_multiSubsAvail.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_runOutQtyAvail="
//							+ _changePartNumMaster_runOutQtyAvail.value);
//			System.out
//					.println("changePartNumMaster._changePartNumMaster_callContextOut="
//							+ _changePartNumMaster_callContextOut.value);

			System.out.println("Invoking changeSellingQtyMaster...");
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// SalesOrderDataSetType _changeSellingQtyMaster_ds = null;
			java.math.BigDecimal _changeSellingQtyMaster_ipSellingQuantity = new java.math.BigDecimal(
					orderItemDTO.getQuantity());
			boolean _changeSellingQtyMaster_chkSellQty = false;
			boolean _changeSellingQtyMaster_negInvTest = false;
			boolean _changeSellingQtyMaster_chgSellQty = true;
			boolean _changeSellingQtyMaster_chgDiscPer = true;
			boolean _changeSellingQtyMaster_suppressUserPrompts = false;
			boolean _changeSellingQtyMaster_lKeepUnitPrice = false;
			java.lang.String _changeSellingQtyMaster_pcPartNum = _changePartNumMaster_partNum.value;
			java.lang.String _changeSellingQtyMaster_pcWhseCode = "";
			java.lang.String _changeSellingQtyMaster_pcBinNum = "";
			java.lang.String _changeSellingQtyMaster_pcLotNum = "";
			java.lang.String _changeSellingQtyMaster_pcDimCode = "EA";
			java.math.BigDecimal _changeSellingQtyMaster_pdDimConvFactor = new java.math.BigDecimal(
					"1");
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// CallContextDataSetType _changeSellingQtyMaster_callContextIn =
			// null;
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _changeSellingQtyMaster_changeSellingQtyMasterResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
			javax.xml.ws.Holder<java.lang.String> _changeSellingQtyMaster_pcMessage = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changeSellingQtyMaster_pcNeqQtyAction = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changeSellingQtyMaster_opWarningMsg = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<java.lang.String> _changeSellingQtyMaster_cSellingQuantityChangedMsgText = new javax.xml.ws.Holder<java.lang.String>();
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _changeSellingQtyMaster_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
			port.changeSellingQtyMaster(companyID,
					_changePartNumMaster_changePartNumMasterResult.value,
					_changeSellingQtyMaster_ipSellingQuantity,
					_changeSellingQtyMaster_chkSellQty,
					_changeSellingQtyMaster_negInvTest,
					_changeSellingQtyMaster_chgSellQty,
					_changeSellingQtyMaster_chgDiscPer,
					_changeSellingQtyMaster_suppressUserPrompts,
					_changeSellingQtyMaster_lKeepUnitPrice,
					_changeSellingQtyMaster_pcPartNum,
					_changeSellingQtyMaster_pcWhseCode,
					_changeSellingQtyMaster_pcBinNum,
					_changeSellingQtyMaster_pcLotNum,
					_changeSellingQtyMaster_pcDimCode,
					_changeSellingQtyMaster_pdDimConvFactor,
					_changePartNumMaster_callContextOut.value,
					_changeSellingQtyMaster_changeSellingQtyMasterResult,
					_changeSellingQtyMaster_pcMessage,
					_changeSellingQtyMaster_pcNeqQtyAction,
					_changeSellingQtyMaster_opWarningMsg,
					_changeSellingQtyMaster_cSellingQuantityChangedMsgText,
					_changeSellingQtyMaster_callContextOut);

//			System.out
//					.println("changeSellingQtyMaster._changeSellingQtyMaster_changeSellingQtyMasterResult="
//							+ _changeSellingQtyMaster_changeSellingQtyMasterResult.value);
//			System.out
//					.println("changeSellingQtyMaster._changeSellingQtyMaster_pcMessage="
//							+ _changeSellingQtyMaster_pcMessage.value);
//			System.out
//					.println("changeSellingQtyMaster._changeSellingQtyMaster_pcNeqQtyAction="
//							+ _changeSellingQtyMaster_pcNeqQtyAction.value);
//			System.out
//					.println("changeSellingQtyMaster._changeSellingQtyMaster_opWarningMsg="
//							+ _changeSellingQtyMaster_opWarningMsg.value);
//			System.out
//					.println("changeSellingQtyMaster._changeSellingQtyMaster_cSellingQuantityChangedMsgText="
//							+ _changeSellingQtyMaster_cSellingQuantityChangedMsgText.value);
//			System.out
//					.println("changeSellingQtyMaster._changeSellingQtyMaster_callContextOut="
//							+ _changeSellingQtyMaster_callContextOut.value);

			// System.out.println("Invoking changeUnitPrice...");
			//			
			// //设置价格等信息
			// OrderDtl changePriceOrderDtl =
			// (OrderDtl)_changeSellingQtyMaster_changeSellingQtyMasterResult
			// .value
			// .getSalesOrderDataSet().getOrderHedOrOrderHedAttchOrOHOrderMsc
			// ().get(i);
			// changePriceOrderDtl.setUnitPrice(orderItemDTO.getUnitPrice());
			//changePriceOrderDtl.setDocDspUnitPrice(orderItemDTO.getUnitPrice()
			// );
			//changePriceOrderDtl.setDocDspDiscount(orderItemDTO.getDiscount());
			// if(orderItemDTO.getTargetDate() != null){
			// changePriceOrderDtl.setNeedByDate(DateUtils.
			// convertToXMLGregorianCalendar(orderItemDTO.getTargetDate()));
			// }
			// javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.
			// salesorder.SalesOrderDataSetType>
			// _changeUnitPrice_changeUnitPriceResult = new
			// javax.xml.ws.Holder<com
			// .genscript.gsscm.epicorwebservice.stub.salesorder
			// .SalesOrderDataSetType>();
			// javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.
			// salesorder.CallContextDataSetType>
			// _changeUnitPrice_callContextOut = new
			// javax.xml.ws.Holder<com.genscript
			// .gsscm.epicorwebservice.stub.salesorder
			// .CallContextDataSetType>();
			// port.changeUnitPrice(companyID,
			// _changeSellingQtyMaster_changeSellingQtyMasterResult.value,
			// _changeSellingQtyMaster_callContextOut.value,
			// _changeUnitPrice_changeUnitPriceResult,
			// _changeUnitPrice_callContextOut);
			//
			// System.out
			//.println("changeUnitPrice._changeUnitPrice_changeUnitPriceResult="
			// + _changeUnitPrice_changeUnitPriceResult.value);
			// System.out
			// .println("changeUnitPrice._changeUnitPrice_callContextOut="
			// + _changeUnitPrice_callContextOut.value);

			System.out.println("Invoking masterUpdate...");
			_masterUpdate_lcheckForResponse = true;
			_masterUpdate_cTableName = "OrderDtl";
			_masterUpdate_iCustNum = 3;
			_masterUpdate_lweLicensed = false;
			OrderDtl updateOrderDtl = (OrderDtl) _changeSellingQtyMaster_changeSellingQtyMasterResult.value
					.getSalesOrderDataSet()
					.getOrderHedOrOrderHedAttchOrOHOrderMsc().get(i);
			//updateOrderDtl.setLineDesc(orderItemDTO.getShortDesc());
			updateOrderDtl.setLineDesc(orderItemDTO.getName());
			if ("Item Completed".equalsIgnoreCase(orderItemDTO
					.getShippingRule())) {
				updateOrderDtl.setCheckBox02(true);
			}

			updateOrderDtl.setPartNum(orderItemDTO.getCatalogNo());
			_masterUpdate_iOrderNum = updateOrderDtl.getOrderNum();

			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// SalesOrderDataSetType _masterUpdate_ds = null;
			// com.genscript.gsscm.epicorwebservice.stub.salesorder.
			// CallContextDataSetType _masterUpdate_callContextIn = null;
			_masterUpdate_masterUpdateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
			_masterUpdate_lContinue = new javax.xml.ws.Holder<java.lang.Boolean>();
			_masterUpdate_cResponseMsg = new javax.xml.ws.Holder<java.lang.String>();
			_masterUpdate_cDisplayMsg = new javax.xml.ws.Holder<java.lang.String>();
			_masterUpdate_cCompliantMsg = new javax.xml.ws.Holder<java.lang.String>();
			_masterUpdate_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
			port.masterUpdate(companyID, _masterUpdate_lcheckForResponse,
					_masterUpdate_cTableName, _masterUpdate_iCustNum,
					_masterUpdate_iOrderNum, _masterUpdate_lweLicensed,
					_changeSellingQtyMaster_changeSellingQtyMasterResult.value,
					_changeSellingQtyMaster_callContextOut.value,
					_masterUpdate_masterUpdateResult, _masterUpdate_lContinue,
					_masterUpdate_cResponseMsg, _masterUpdate_cDisplayMsg,
					_masterUpdate_cCompliantMsg, _masterUpdate_callContextOut);

			System.out.println("masterUpdate._masterUpdate_masterUpdateResult="
					+ _masterUpdate_masterUpdateResult.value);
			System.out.println("masterUpdate._masterUpdate_lContinue="
					+ _masterUpdate_lContinue.value);
			System.out.println("masterUpdate._masterUpdate_cResponseMsg="
					+ _masterUpdate_cResponseMsg.value);
			System.out.println("masterUpdate._masterUpdate_cDisplayMsg="
					+ _masterUpdate_cDisplayMsg.value);
			System.out.println("masterUpdate._masterUpdate_cCompliantMsg="
					+ _masterUpdate_cCompliantMsg.value);
			System.out.println("masterUpdate._masterUpdate_callContextOut="
					+ _masterUpdate_callContextOut.value);

		}

		System.out.println("Invoking getNewOHOrderMsc...");

		_masterUpdate_iOrderNum = orderHed.getOrderNum();
		int _getNewOHOrderMsc_orderLine = 0;

		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType> _getNewOHOrderMsc_getNewOHOrderMscResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType> _getNewOHOrderMsc_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.getNewOHOrderMsc(companyID,
				_masterUpdate_masterUpdateResult.value,
				_masterUpdate_iOrderNum, _getNewOHOrderMsc_orderLine,
				_masterUpdate_callContextOut.value,
				_getNewOHOrderMsc_getNewOHOrderMscResult,
				_getNewOHOrderMsc_callContextOut);

		System.out
				.println("getNewOHOrderMsc._getNewOHOrderMsc_getNewOHOrderMscResult="
						+ _getNewOHOrderMsc_getNewOHOrderMscResult.value);
		System.out.println("getNewOHOrderMsc._getNewOHOrderMsc_callContextOut="
				+ _getNewOHOrderMsc_callContextOut.value);

		OHOrderMsc changeOHOrderMsc = (OHOrderMsc) _getNewOHOrderMsc_getNewOHOrderMscResult.value
				.getSalesOrderDataSet()
				.getOrderHedOrOrderHedAttchOrOHOrderMsc().get(1);

		if (orderDTO.getShipAmt() != null) {
			changeOHOrderMsc.setMiscAmt(new BigDecimal(orderDTO.getShipAmt()));
		}
		changeOHOrderMsc.setMiscCode("TRAV");
		changeOHOrderMsc.setFreqCode("E");

		_masterUpdate_cTableName = "OrderMsc";
		_masterUpdate_masterUpdateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.SalesOrderDataSetType>();
		_masterUpdate_lContinue = new javax.xml.ws.Holder<java.lang.Boolean>();
		_masterUpdate_cResponseMsg = new javax.xml.ws.Holder<java.lang.String>();
		_masterUpdate_cDisplayMsg = new javax.xml.ws.Holder<java.lang.String>();
		_masterUpdate_cCompliantMsg = new javax.xml.ws.Holder<java.lang.String>();
		_masterUpdate_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.salesorder.CallContextDataSetType>();
		port.masterUpdate(companyID, _masterUpdate_lcheckForResponse,
				_masterUpdate_cTableName, _masterUpdate_iCustNum,
				_masterUpdate_iOrderNum, _masterUpdate_lweLicensed,
				_getNewOHOrderMsc_getNewOHOrderMscResult.value,
				_getNewOHOrderMsc_callContextOut.value,
				_masterUpdate_masterUpdateResult, _masterUpdate_lContinue,
				_masterUpdate_cResponseMsg, _masterUpdate_cDisplayMsg,
				_masterUpdate_cCompliantMsg, _masterUpdate_callContextOut);

	}

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		// set the password for our message.
		pc.setPassword("TSKAGT");
	}

	private void assertUserNameToken(Object service) {
		Client client = ClientProxy.getClient(service);
		Endpoint cxfEndpoint = client.getEndpoint();

		//cxfEndpoint.getInInterceptors().add(new LoggingInInterceptor());
		//cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());

		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN + " "
						+ WSHandlerConstants.TIMESTAMP);
		outProps.put(WSHandlerConstants.USER, erpUserName);
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);

		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ErpSalesOrderService.class
				.getName());
		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		cxfEndpoint.getOutInterceptors().add(wssOut);
	}
	
	@Traced
	public Integer createCustomer(CustomerDTO customerDTO, String customerNo){
		CustomerService customerService = new CustomerService();
		CustomerServiceSoap port = customerService
				.getCustomerServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getNewCustomer...");
		String _update_companyID = "GSUS";
//		if(customerDTO.getCompanyId() == 1){
//			_update_companyID = "GSUS";
//		}else if(customerDTO.getCompanyId() == 4) {
//			_update_companyID = "GSHK";
//		}
		String accountCode = billTerritoryDao.getAccountCode(customerDTO.getCountry(), customerDTO.getState(), customerDTO.getZipCode());
		if("HK".equalsIgnoreCase(accountCode)){
			_update_companyID = "GSHK";
		}
		return createCustByCompany(customerDTO, customerNo, port,
					_update_companyID);        
        
	}
	
	@Traced
	public Integer createCustomer(CustomerDTO customerDTO, String customerNo, String company){
		CustomerService customerService = new CustomerService();
		CustomerServiceSoap port = customerService
				.getCustomerServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getNewCustomer...");
		String _update_companyID = company;
		//return createCustByCompany(customerDTO, customerNo, port,
		//			_update_companyID);
		Customer cust;
		Address shipToAddr = addressDao.getDefaultShipTOAddress(Integer.parseInt(customerNo));
		
		boolean _getByCustID_withShipTo = false;
		com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType _getByCustID_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _getByCustID_getByCustIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _getByCustID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		try{
			port.getByCustID(_update_companyID, customerNo, _getByCustID_withShipTo, _getByCustID_callContextIn, _getByCustID_getByCustIDResult, _getByCustID_callContextOut);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(_getByCustID_getByCustIDResult.value == null){
			com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType _getNewCustomer_ds = null;
		    com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType _getNewCustomer_callContextIn = null;
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _getNewCustomer_getNewCustomerResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _getNewCustomer_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		    port.getNewCustomer(_update_companyID, _getNewCustomer_ds, _getNewCustomer_callContextIn, _getNewCustomer_getNewCustomerResult, _getNewCustomer_callContextOut);

			System.out.println("Invoking update...");
			Customer customer = (Customer)_getNewCustomer_getNewCustomerResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
			customer.setCompany(_update_companyID);
			customer.setName(AddressUtil.getFullName(customerDTO.getFirstName(), customerDTO.getMidName(), customerDTO.getLastName()));
			customer.setCustID(customerNo);
			customer.setAddress1(StringUtil.subStringByLength(shipToAddr.getAddrLine1(), 50));
			customer.setAddress2(StringUtil.subStringByLength(shipToAddr.getAddrLine2(), 50));
			customer.setAddress3(StringUtil.subStringByLength(shipToAddr.getAddrLine3(), 50));
			customer.setCity(shipToAddr.getCity());
			customer.setState(shipToAddr.getState());
			customer.setZip(shipToAddr.getZipCode());
			customer.setCountry(shipToAddr.getCountry());
			customer.setTerritoryID("Default");
			customer.setTermsCode(customerDTO.getPrefPaymentTerm()+"");
			customer.setCustomerType("CUS");
			//customer.setShipViaCode("9999");
			customer.setCreditHold(false);
			customer.setAllowOTS(true);
			customer.setShortChar01(customerDTO.getBusEmail());
			Organization organization = customerDTO.getOrganization();
			if(organization != null){
				String orgName = organization.getName();
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "Norvartis")){
					customer.setTermsCode("3");
				}
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "IRBM Science Park")){
					customer.setTermsCode("5");
				}
				
			}
		    boolean _update_continueProcessingOnError = false;

		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _update_updateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		    javax.xml.ws.Holder<java.lang.Boolean> _update_errorsOccurred = new javax.xml.ws.Holder<java.lang.Boolean>();
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _update_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		    port.update(_update_companyID, _getNewCustomer_getNewCustomerResult.value, _update_continueProcessingOnError, _getNewCustomer_callContextOut.value, _update_updateResult, _update_errorsOccurred, _update_callContextOut);
		    cust = (Customer)_update_updateResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
		}else{
			System.out.println("Invoking update...");
			Customer customer = (Customer)_getByCustID_getByCustIDResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
			customer.setCompany(_update_companyID);
			customer.setName(AddressUtil.getFullName(customerDTO.getFirstName(), customerDTO.getMidName(), customerDTO.getLastName()));
			customer.setCustID(customerNo);
			customer.setAddress1(StringUtil.subStringByLength(shipToAddr.getAddrLine1(), 50));
			customer.setAddress2(StringUtil.subStringByLength(shipToAddr.getAddrLine2(), 50));
			customer.setAddress3(StringUtil.subStringByLength(shipToAddr.getAddrLine3(), 50));
			customer.setCity(shipToAddr.getCity());
			customer.setState(shipToAddr.getState());
			customer.setZip(customerDTO.getZipCode());
			customer.setCountry(shipToAddr.getCountry());
			customer.setTerritoryID("Default");
			customer.setTermsCode(customerDTO.getPrefPaymentTerm()+"");
			customer.setCustomerType("CUS");
			//customer.setShipViaCode("9999");
			customer.setCreditHold(false);
			customer.setAllowOTS(true);
			customer.setShortChar01(customerDTO.getBusEmail());
			Organization organization = customerDTO.getOrganization();
			if(organization != null){
				String orgName = organization.getName();
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "Norvartis")){
					customer.setTermsCode("3");
				}
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "IRBM Science Park")){
					customer.setTermsCode("5");
				}
			}
		    boolean _update_continueProcessingOnError = false;

		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _update_updateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		    javax.xml.ws.Holder<java.lang.Boolean> _update_errorsOccurred = new javax.xml.ws.Holder<java.lang.Boolean>();
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _update_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		    port.update(_update_companyID, _getByCustID_getByCustIDResult.value, _update_continueProcessingOnError, _getByCustID_callContextOut.value, _update_updateResult, _update_errorsOccurred, _update_callContextOut);
		    cust = (Customer)_update_updateResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
		}
		 return cust.getCustNum();
        
	}

	@Traced
	private Integer createCustByCompany(CustomerDTO customerDTO,
			String customerNo, CustomerServiceSoap port,
			String _update_companyID) {
		Customer cust;
		Address shipToAddr = addressDao.getDefaultShipTOAddress(Integer.parseInt(customerNo));
		
		boolean _getByCustID_withShipTo = false;
		com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType _getByCustID_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _getByCustID_getByCustIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _getByCustID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		try{
			port.getByCustID(_update_companyID, customerNo, _getByCustID_withShipTo, _getByCustID_callContextIn, _getByCustID_getByCustIDResult, _getByCustID_callContextOut);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(_getByCustID_getByCustIDResult.value == null){
			com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType _getNewCustomer_ds = null;
		    com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType _getNewCustomer_callContextIn = null;
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _getNewCustomer_getNewCustomerResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _getNewCustomer_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		    port.getNewCustomer(_update_companyID, _getNewCustomer_ds, _getNewCustomer_callContextIn, _getNewCustomer_getNewCustomerResult, _getNewCustomer_callContextOut);

			System.out.println("Invoking update...");
			Customer customer = (Customer)_getNewCustomer_getNewCustomerResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
			customer.setCompany(_update_companyID);
			customer.setName(AddressUtil.getFullName(customerDTO.getFirstName(), customerDTO.getMidName(), customerDTO.getLastName()));
			customer.setCustID(customerNo);
			customer.setAddress1(StringUtil.subStringByLength(shipToAddr.getAddrLine1(), 50));
			customer.setAddress2(StringUtil.subStringByLength(shipToAddr.getAddrLine2(), 50));
			customer.setAddress3(StringUtil.subStringByLength(shipToAddr.getAddrLine3(), 50));
			customer.setCity(shipToAddr.getCity());
			customer.setState(shipToAddr.getState());
			customer.setZip(shipToAddr.getZipCode());
			customer.setCountry(shipToAddr.getCountry());
			customer.setTerritoryID("Default");
			customer.setTermsCode(customerDTO.getPrefPaymentTerm()+"");
			customer.setCustomerType("CUS");
			//customer.setShipViaCode("9999");
			customer.setCreditHold(false);
			customer.setAllowOTS(true);
			customer.setShortChar01(customerDTO.getBusEmail());
			Organization organization = customerDTO.getOrganization();
			if(organization != null){
				String orgName = organization.getName();
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "Norvartis")){
					customer.setTermsCode("3");
				}
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "IRBM Science Park")){
					customer.setTermsCode("5");
				}
			}
		    boolean _update_continueProcessingOnError = false;

		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _update_updateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		    javax.xml.ws.Holder<java.lang.Boolean> _update_errorsOccurred = new javax.xml.ws.Holder<java.lang.Boolean>();
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _update_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		    port.update(_update_companyID, _getNewCustomer_getNewCustomerResult.value, _update_continueProcessingOnError, _getNewCustomer_callContextOut.value, _update_updateResult, _update_errorsOccurred, _update_callContextOut);
		    cust = (Customer)_update_updateResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
		}else{
			System.out.println("Invoking update...");
			Customer customer = (Customer)_getByCustID_getByCustIDResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
			customer.setCompany(_update_companyID);
			customer.setName(AddressUtil.getFullName(customerDTO.getFirstName(), customerDTO.getMidName(), customerDTO.getLastName()));
			customer.setCustID(customerNo);
			customer.setAddress1(StringUtil.subStringByLength(shipToAddr.getAddrLine1(), 50));
			customer.setAddress2(StringUtil.subStringByLength(shipToAddr.getAddrLine2(), 50));
			customer.setAddress3(StringUtil.subStringByLength(shipToAddr.getAddrLine3(), 50));
			customer.setCity(shipToAddr.getCity());
			customer.setState(shipToAddr.getState());
			customer.setZip(customerDTO.getZipCode());
			customer.setCountry(shipToAddr.getCountry());
			customer.setTerritoryID("Default");
			customer.setTermsCode(customerDTO.getPrefPaymentTerm()+"");
			customer.setCustomerType("CUS");
			//customer.setShipViaCode("9999");
			customer.setCreditHold(false);
			customer.setAllowOTS(true);
			customer.setShortChar01(customerDTO.getBusEmail());
			Organization organization = customerDTO.getOrganization();
			if(organization != null){
				String orgName = organization.getName();
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "Norvartis")){
					customer.setTermsCode("3");
				}
				if(orgName != null && StringUtils.containsIgnoreCase(orgName, "IRBM Science Park")){
					customer.setTermsCode("5");
				}
			}
		    boolean _update_continueProcessingOnError = false;

		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _update_updateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
		    javax.xml.ws.Holder<java.lang.Boolean> _update_errorsOccurred = new javax.xml.ws.Holder<java.lang.Boolean>();
		    javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _update_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
		    port.update(_update_companyID, _getByCustID_getByCustIDResult.value, _update_continueProcessingOnError, _getByCustID_callContextOut.value, _update_updateResult, _update_errorsOccurred, _update_callContextOut);
		    cust = (Customer)_update_updateResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
		}
		 return cust.getCustNum();
	}
	
	public void createPart(ProductDTO product){
		PartService partService = new PartService();
		PartServiceSoap port = partService.getPartServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getByID...");
		java.lang.String _getByID_companyID = "GSUS";
		creatPartByCompanyId(product, port, _getByID_companyID);
		_getByID_companyID = "GSHK";
		creatPartByCompanyId(product, port, _getByID_companyID);
	}

	@Traced
	private void creatPartByCompanyId(ProductDTO product, PartServiceSoap port,
			java.lang.String _getByID_companyID) {
		java.lang.String _getByID_partNum = product.getCatalogNo();
		com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType _getByID_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _getByID_getByIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _getByID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
		try{
			port.getByID(_getByID_companyID, _getByID_partNum,
				_getByID_callContextIn, _getByID_getByIDResult,
				_getByID_callContextOut);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(_getByID_getByIDResult.value == null){
			System.out.println("Invoking getNewPart...");
			java.lang.String _getNewPart_companyID = _getByID_companyID;
			com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType _getNewPart_ds = null;
			com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType _getNewPart_callContextIn = null;
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _getNewPart_getNewPartResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _getNewPart_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
			port.getNewPart(_getNewPart_companyID, _getNewPart_ds,
					_getNewPart_callContextIn, _getNewPart_getNewPartResult,
					_getNewPart_callContextOut);

			
			System.out.println("Invoking update...");
			Part part = (Part)_getNewPart_getNewPartResult.value.getPartDataSet().getPartOrPartAttchOrPartCOO().get(0);
			part.setCompany(_getNewPart_companyID);
			part.setPartNum(product.getCatalogNo());
			if(product.getCatalogNo().length() > 8){
				part.setSearchWord(product.getCatalogNo().substring(0, 8));
			}else{
				part.setSearchWord(product.getCatalogNo());
			}
			part.setPartDescription(product.getName());
			if(StringUtils.isNotBlank(product.getVtRatio())){
				part.setNumber02(new BigDecimal(product.getVtRatio()));
			}
			if(StringUtils.isNotBlank(product.getBtRatio())){
				part.setNumber03(new BigDecimal(product.getBtRatio()));
			}
			//part.setPurchasingFactor(new BigDecimal(1));
			if("SC1010".equalsIgnoreCase(product.getCatalogNo())){
				part.setPUM("BP");
				part.setIUM("BP");
				part.setSalesUM("BP");
			}else{
				part.setPUM("EA");
				part.setIUM("EA");
				part.setSalesUM("EA");
			}
			
			//part.setUOMClassID(product.getQtyUom());
			part.setUOMClassID("ALL");
			part.setTypeCode("P");
			//part.setUnitPrice(new BigDecimal(1500));
			//part.setPricePerCode("C");
			//part.setInternalUnitPrice(new BigDecimal(900));
			if("Y".equalsIgnoreCase(product.getLotControlFlag())){
				part.setTrackLots(true);
			}else if("N".equalsIgnoreCase(product.getLotControlFlag())){
				part.setTrackLots(false);
			}
			//part.setInternalPricePerCode("C");
			part.setCostMethod("S");
			if(product.getExpirationDays() != null){
				part.setNumber01(new BigDecimal(product.getExpirationDays()));
			}
			if(product.getRetestDays() != null){
				part.setNumber02(new BigDecimal(product.getRetestDays()));
			}
			
			boolean _update_continueProcessingOnError = false;
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _update_updateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
			javax.xml.ws.Holder<java.lang.Boolean> _update_errorsOccurred = new javax.xml.ws.Holder<java.lang.Boolean>();
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _update_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
			port.update(_getNewPart_companyID, _getNewPart_getNewPartResult.value,
					_update_continueProcessingOnError, _getNewPart_callContextOut.value,
					_update_updateResult, _update_errorsOccurred,
					_update_callContextOut);

			System.out.println("update._update_updateResult="
					+ _update_updateResult.value);
			System.out.println("update._update_errorsOccurred="
					+ _update_errorsOccurred.value);
			System.out.println("update._update_callContextOut="
					+ _update_callContextOut.value);
		}else{
			System.out.println("Invoking update...");
			Part part = (Part)_getByID_getByIDResult.value.getPartDataSet().getPartOrPartAttchOrPartCOO().get(0);
			part.setCompany(_getByID_companyID);
			part.setPartNum(product.getCatalogNo());
			if(product.getCatalogNo().length() > 8){
				part.setSearchWord(product.getCatalogNo().substring(0, 8));
			}else{
				part.setSearchWord(product.getCatalogNo());
			}
			part.setPartDescription(product.getName());
			if(StringUtils.isNotBlank(product.getVtRatio())){
				part.setNumber02(new BigDecimal(product.getVtRatio()));
			}
			if(StringUtils.isNotBlank(product.getBtRatio())){
				part.setNumber03(new BigDecimal(product.getBtRatio()));
			}
			//part.setPurchasingFactor(new BigDecimal(1));
			if("SC1010".equalsIgnoreCase(product.getCatalogNo())){
				part.setPUM("BP");
				part.setIUM("BP");
				part.setSalesUM("BP");
			}else{
				part.setPUM("EA");
				part.setIUM("EA");
				part.setSalesUM("EA");
			}
			
			//part.setUOMClassID(product.getQtyUom());
			part.setUOMClassID("ALL");
			part.setTypeCode("P");
			//part.setUnitPrice(new BigDecimal(1500));
			//part.setPricePerCode("C");
			//part.setInternalUnitPrice(new BigDecimal(900));
			if("Y".equalsIgnoreCase(product.getLotControlFlag())){
				part.setTrackLots(true);
			}else if("N".equalsIgnoreCase(product.getLotControlFlag())){
				part.setTrackLots(false);
			}
			//part.setInternalPricePerCode("C");
			part.setCostMethod("S");
			if(product.getExpirationDays() != null){
				part.setNumber01(new BigDecimal(product.getExpirationDays()));
			}
			if(product.getRetestDays() != null){
				part.setNumber02(new BigDecimal(product.getRetestDays()));
			}
			
			boolean _update_continueProcessingOnError = false;
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _update_updateResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
			javax.xml.ws.Holder<java.lang.Boolean> _update_errorsOccurred = new javax.xml.ws.Holder<java.lang.Boolean>();
			javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _update_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
			port.update(_getByID_companyID, _getByID_getByIDResult.value,
					_update_continueProcessingOnError, _getByID_callContextOut.value,
					_update_updateResult, _update_errorsOccurred,
					_update_callContextOut);
		}
	}
	
//	public BigDecimal getPartStorageNumber(String catalogNo){
//		PartService partService = new PartService();
//		PartServiceSoap port = partService.getPartServiceSoap();
//		assertUserNameToken(port);
//		
//		System.out.println("Invoking getByID...");
//		java.lang.String _getByID_companyID = "GSUS";
//		java.lang.String _getByID_partNum = catalogNo;
//		com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType _getByID_callContextIn = null;
//		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _getByID_getByIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
//		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _getByID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
//		try{
//		port.getByID(_getByID_companyID, _getByID_partNum,
//				_getByID_callContextIn, _getByID_getByIDResult,
//				_getByID_callContextOut);
//		}catch(Exception e){
//			e.printStackTrace();
//			//throw new BussinessException(BussinessException.ADD_ITEM_ERROR);
//		}
//		System.out.println("getByID._getByID_getByIDResult="
//				+ _getByID_getByIDResult.value);
//		System.out.println("getByID._getByID_callContextOut="
//				+ _getByID_callContextOut.value);
//		if(_getByID_getByIDResult.value == null){
//			return null;
//		}
//		PartWhse partWhse = (PartWhse)_getByID_getByIDResult.value.getPartDataSet().getPartOrPartAttchOrPartCOO().get(2);
//		return partWhse.getOnHandQty().subtract(partWhse.getDemandQty());
//	}
	
//	public BigDecimal getPartStorageNumberForAddItem(String catalogNo){
//		PartService partService = new PartService();
//		PartServiceSoap port = partService.getPartServiceSoap();
//		assertUserNameToken(port);
//		
//		System.out.println("Invoking getByID...");
//		java.lang.String _getByID_companyID = "GSUS";
//		java.lang.String _getByID_partNum = catalogNo;
//		com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType _getByID_callContextIn = null;
//		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _getByID_getByIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
//		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _getByID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
//		try{
//		port.getByID(_getByID_companyID, _getByID_partNum,
//				_getByID_callContextIn, _getByID_getByIDResult,
//				_getByID_callContextOut);
//		}catch(Exception e){
//			//e.printStackTrace();
//			throw new BussinessException(BussinessException.ADD_ITEM_ERROR);
//		}
//		System.out.println("getByID._getByID_getByIDResult="
//				+ _getByID_getByIDResult.value);
//		System.out.println("getByID._getByID_callContextOut="
//				+ _getByID_callContextOut.value);
//		if(_getByID_getByIDResult.value == null){
//			return null;
//		}
//		PartWhse partWhse = (PartWhse)_getByID_getByIDResult.value.getPartDataSet().getPartOrPartAttchOrPartCOO().get(2);
//		return partWhse.getOnHandQty().subtract(partWhse.getDemandQty());
//	}
	
	@Traced
	public BigDecimal getCustomerTotOpenCredit(int custNo){
		CustomerService customerService = new CustomerService();
		CustomerServiceSoap port = customerService
				.getCustomerServiceSoap();
		assertUserNameToken(port);
        
        System.out.println("Invoking getCustomer...");
        java.lang.String _getCustomer_companyID = "GSUS";
        java.lang.String _getCustomer_custID = custNo+"";
        com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType _getCustomer_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType> _getCustomer_getCustomerResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CustomerDataSetType>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType> _getCustomer_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.customer.CallContextDataSetType>();
        try{
        	port.getCustomer(_getCustomer_companyID, _getCustomer_custID, _getCustomer_callContextIn, _getCustomer_getCustomerResult, _getCustomer_callContextOut);
        }catch(Exception e){
			e.printStackTrace();
		}
        if(_getCustomer_getCustomerResult.value == null){
			return null;
		}
        Customer customer = (Customer)_getCustomer_getCustomerResult.value.getCustomerDataSet().getCustomerOrCustomerAttchOrCustomCrdPool().get(0);
        return customer.getTotOpenCredit();
	}
	
	@Traced
	public PartWhse getPartInfoByCatalogNo(String catalogNo){
		PartService partService = new PartService();
		PartServiceSoap port = partService.getPartServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getByID...");
		java.lang.String _getByID_companyID = "GSUS";
		java.lang.String _getByID_partNum = catalogNo;
		com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType _getByID_callContextIn = null;
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _getByID_getByIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _getByID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
		try{
		port.getByID(_getByID_companyID, _getByID_partNum,
				_getByID_callContextIn, _getByID_getByIDResult,
				_getByID_callContextOut);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("getByID._getByID_getByIDResult="
				+ _getByID_getByIDResult.value);
		System.out.println("getByID._getByID_callContextOut="
				+ _getByID_callContextOut.value);
		if(_getByID_getByIDResult.value == null){
			return null;
		}
		PartWhse partWhse = (PartWhse)_getByID_getByIDResult.value.getPartDataSet().getPartOrPartAttchOrPartCOO().get(2);
		System.out.println("getByID._getByID_Storage="
				+ partWhse.getOnHandQty());
		return partWhse;
	}
	
	@Traced
	public List<String> getInventoryLotNumber(String catalogNo){
		InventoryQtyAdjService inventoryQtyAdjService = new InventoryQtyAdjService();
		InventoryQtyAdjServiceSoap port = inventoryQtyAdjService.getInventoryQtyAdjServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getInventoryQtyAdjBrw...");
        java.lang.String _getInventoryQtyAdjBrw_companyID = "GSUS";
        java.lang.String _getInventoryQtyAdjBrw_partNum = catalogNo;
        java.lang.String _getInventoryQtyAdjBrw_wareHouseCode = "US";
        com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType _getInventoryQtyAdjBrw_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType> _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType>();
        javax.xml.ws.Holder<java.lang.String> _getInventoryQtyAdjBrw_primaryBin = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType> _getInventoryQtyAdjBrw_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType>();
        try{
        	port.getInventoryQtyAdjBrw(_getInventoryQtyAdjBrw_companyID, _getInventoryQtyAdjBrw_partNum, _getInventoryQtyAdjBrw_wareHouseCode, _getInventoryQtyAdjBrw_callContextIn, _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult, _getInventoryQtyAdjBrw_primaryBin, _getInventoryQtyAdjBrw_callContextOut);
        }catch(Exception e){
        	e.printStackTrace();
        }
        if(_getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value == null){
			return null;
		}
        List<String> lotNumList = new ArrayList<String>();
        for(Object obj : _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value.getInventoryQtyAdjBrwDataSet().getInventoryQtyAdjBrwOrWebServiceErrors()){
        	InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw inventoryQtyAdjBrw = (InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw)obj;
        	lotNumList.add(inventoryQtyAdjBrw.getLotNum());
        }
        return lotNumList;
	}
	
	@Traced
	public BigDecimal getPartStorageNumber(String catalogNo){
		InventoryQtyAdjService inventoryQtyAdjService = new InventoryQtyAdjService();
		InventoryQtyAdjServiceSoap port = inventoryQtyAdjService.getInventoryQtyAdjServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getInventoryQtyAdjBrw...");
        java.lang.String _getInventoryQtyAdjBrw_companyID = "GSUS";
        java.lang.String _getInventoryQtyAdjBrw_partNum = catalogNo;
        java.lang.String _getInventoryQtyAdjBrw_wareHouseCode = "US";
        com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType _getInventoryQtyAdjBrw_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType> _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType>();
        javax.xml.ws.Holder<java.lang.String> _getInventoryQtyAdjBrw_primaryBin = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType> _getInventoryQtyAdjBrw_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType>();
        try{
        	port.getInventoryQtyAdjBrw(_getInventoryQtyAdjBrw_companyID, _getInventoryQtyAdjBrw_partNum, _getInventoryQtyAdjBrw_wareHouseCode, _getInventoryQtyAdjBrw_callContextIn, _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult, _getInventoryQtyAdjBrw_primaryBin, _getInventoryQtyAdjBrw_callContextOut);
        }catch(Exception e){
        	
        }
        if(_getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value == null){
			return null;
		}
        List<BigDecimal> qtyList = new ArrayList<BigDecimal>();
        for(Object obj : _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value.getInventoryQtyAdjBrwDataSet().getInventoryQtyAdjBrwOrWebServiceErrors()){
        	InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw inventoryQtyAdjBrw = (InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw)obj;
        	qtyList.add(inventoryQtyAdjBrw.getBaseOnHandQty());
        }
        BigDecimal maxQty = new BigDecimal(0);
        for(BigDecimal d: qtyList){
        	if(d.compareTo(maxQty) > 0 ){
        		maxQty = d;
        	}
        }
        return maxQty;
	}
	
	@Traced
	public BigDecimal getPartStorageNumber(String catalogNo, String company){
		InventoryQtyAdjService inventoryQtyAdjService = new InventoryQtyAdjService();
		InventoryQtyAdjServiceSoap port = inventoryQtyAdjService.getInventoryQtyAdjServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getInventoryQtyAdjBrw...");
        java.lang.String _getInventoryQtyAdjBrw_companyID = company;
        java.lang.String _getInventoryQtyAdjBrw_partNum = catalogNo;
        java.lang.String _getInventoryQtyAdjBrw_wareHouseCode = "US";
        if(company.equalsIgnoreCase("GSHK")){
        	_getInventoryQtyAdjBrw_wareHouseCode = "HK";
        }
        com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType _getInventoryQtyAdjBrw_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType> _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType>();
        javax.xml.ws.Holder<java.lang.String> _getInventoryQtyAdjBrw_primaryBin = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType> _getInventoryQtyAdjBrw_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType>();
        try{
        	port.getInventoryQtyAdjBrw(_getInventoryQtyAdjBrw_companyID, _getInventoryQtyAdjBrw_partNum, _getInventoryQtyAdjBrw_wareHouseCode, _getInventoryQtyAdjBrw_callContextIn, _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult, _getInventoryQtyAdjBrw_primaryBin, _getInventoryQtyAdjBrw_callContextOut);
        }catch(Exception e){
        	
        }
        if(_getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value == null){
			return null;
		}
        List<BigDecimal> qtyList = new ArrayList<BigDecimal>();
        for(Object obj : _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value.getInventoryQtyAdjBrwDataSet().getInventoryQtyAdjBrwOrWebServiceErrors()){
        	InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw inventoryQtyAdjBrw = (InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw)obj;
        	qtyList.add(inventoryQtyAdjBrw.getBaseOnHandQty());
        }
        BigDecimal maxQty = new BigDecimal(0);
        for(BigDecimal d: qtyList){
        	if(d.compareTo(maxQty) > 0 ){
        		maxQty = d;
        	}
        }
        return maxQty;
	}

//	public BigDecimal getPartStorageNumberForAddItem(String catalogNo){
//		BigDecimal bd = new BigDecimal(200);
//		return bd;
//	}
	
	@Traced
	public BigDecimal getPartStorageNumberForAddItem(String catalogNo){
        InventoryQtyAdjService inventoryQtyAdjService = new InventoryQtyAdjService();
		InventoryQtyAdjServiceSoap port = inventoryQtyAdjService.getInventoryQtyAdjServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getInventoryQtyAdjBrw...");
        java.lang.String _getInventoryQtyAdjBrw_companyID = "GSUS";
        java.lang.String _getInventoryQtyAdjBrw_partNum = catalogNo;
        java.lang.String _getInventoryQtyAdjBrw_wareHouseCode = "US";
        com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType _getInventoryQtyAdjBrw_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType> _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType>();
        javax.xml.ws.Holder<java.lang.String> _getInventoryQtyAdjBrw_primaryBin = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType> _getInventoryQtyAdjBrw_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType>();
        try{
        	port.getInventoryQtyAdjBrw(_getInventoryQtyAdjBrw_companyID, _getInventoryQtyAdjBrw_partNum, _getInventoryQtyAdjBrw_wareHouseCode, _getInventoryQtyAdjBrw_callContextIn, _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult, _getInventoryQtyAdjBrw_primaryBin, _getInventoryQtyAdjBrw_callContextOut);
        }catch(Exception e){
        	throw new BussinessException(BussinessException.ADD_ITEM_ERROR);
        }
        if(_getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value == null){
			return null;
		}
        List<BigDecimal> qtyList = new ArrayList<BigDecimal>();
        for(Object obj : _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value.getInventoryQtyAdjBrwDataSet().getInventoryQtyAdjBrwOrWebServiceErrors()){
        	InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw inventoryQtyAdjBrw = (InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw)obj;
        	qtyList.add(inventoryQtyAdjBrw.getBaseOnHandQty());
        }
        BigDecimal maxQty = new BigDecimal(0);
        for(BigDecimal d: qtyList){
        	if(d.compareTo(maxQty) > 0 ){
        		maxQty = d;
        	}
        }
        return maxQty;
	}
	
	@Traced
	public String getPartStorageLocation(String catalogNo, String company){
//		PartService partService = new PartService();
//		PartServiceSoap port = partService.getPartServiceSoap();
//		assertUserNameToken(port);
//		
//		System.out.println("Invoking getByID...");
//		java.lang.String _getByID_companyID = "GSUS";
//		java.lang.String _getByID_partNum = catalogNo;
//		com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType _getByID_callContextIn = null;
//		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType> _getByID_getByIDResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.PartDataSetType>();
//		javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType> _getByID_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.part.CallContextDataSetType>();
//		try{
//		port.getByID(_getByID_companyID, _getByID_partNum,
//				_getByID_callContextIn, _getByID_getByIDResult,
//				_getByID_callContextOut);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		if(_getByID_getByIDResult.value == null){
//			return null;
//		}
//		PartWhse partWhse = (PartWhse)_getByID_getByIDResult.value.getPartDataSet().getPartOrPartAttchOrPartCOO().get(2);
//		return partWhse.getWarehouseCode()+"-"+partWhse.getPrimBinNum();
		InventoryQtyAdjService inventoryQtyAdjService = new InventoryQtyAdjService();
		InventoryQtyAdjServiceSoap port = inventoryQtyAdjService.getInventoryQtyAdjServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getInventoryQtyAdjBrw...");
        java.lang.String _getInventoryQtyAdjBrw_companyID = company;
        java.lang.String _getInventoryQtyAdjBrw_partNum = catalogNo;
        
        java.lang.String _getInventoryQtyAdjBrw_wareHouseCode = "US";
        if(company.equalsIgnoreCase("GSHK")){
        	_getInventoryQtyAdjBrw_wareHouseCode = "HK";
        }
        com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType _getInventoryQtyAdjBrw_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType> _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType>();
        javax.xml.ws.Holder<java.lang.String> _getInventoryQtyAdjBrw_primaryBin = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType> _getInventoryQtyAdjBrw_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType>();
        try{
        	port.getInventoryQtyAdjBrw(_getInventoryQtyAdjBrw_companyID, _getInventoryQtyAdjBrw_partNum, _getInventoryQtyAdjBrw_wareHouseCode, _getInventoryQtyAdjBrw_callContextIn, _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult, _getInventoryQtyAdjBrw_primaryBin, _getInventoryQtyAdjBrw_callContextOut);
        }catch(Exception e){
        	e.printStackTrace();
        }
        if(_getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value == null){
			return null;
		}
        List<BigDecimal> qtyList = new ArrayList<BigDecimal>();
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        for(Object obj : _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value.getInventoryQtyAdjBrwDataSet().getInventoryQtyAdjBrwOrWebServiceErrors()){
        	InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw inventoryQtyAdjBrw = (InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw)obj;
        	System.out.println(inventoryQtyAdjBrw.toString());
        	qtyList.add(inventoryQtyAdjBrw.getBaseOnHandQty());
        	map1.put(inventoryQtyAdjBrw.getBaseOnHandQty()+"", inventoryQtyAdjBrw.getWareHseCode());
        	map2.put(inventoryQtyAdjBrw.getBaseOnHandQty()+"", inventoryQtyAdjBrw.getBinNum());
        }
        BigDecimal maxQty = new BigDecimal(0);
        for(BigDecimal d: qtyList){
        	if(d.compareTo(maxQty) > 0 ){
        		maxQty = d;
        	}
        }
        return map1.get(maxQty.toString())+"-"+map2.get(maxQty.toString());
	}
	
	@Traced
	public List<StorageLocationDTO> getPartStorageLocationList(String catalogNo, String company){
		InventoryQtyAdjService inventoryQtyAdjService = new InventoryQtyAdjService();
		InventoryQtyAdjServiceSoap port = inventoryQtyAdjService.getInventoryQtyAdjServiceSoap();
		assertUserNameToken(port);
		
		System.out.println("Invoking getInventoryQtyAdjBrw...");
        java.lang.String _getInventoryQtyAdjBrw_companyID = company;
        java.lang.String _getInventoryQtyAdjBrw_partNum = catalogNo;
        
        java.lang.String _getInventoryQtyAdjBrw_wareHouseCode = "US";
        if(company.equalsIgnoreCase("GSHK")){
        	_getInventoryQtyAdjBrw_wareHouseCode = "HK";
        }
        com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType _getInventoryQtyAdjBrw_callContextIn = null;
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType> _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.InventoryQtyAdjBrwDataSetType>();
        javax.xml.ws.Holder<java.lang.String> _getInventoryQtyAdjBrw_primaryBin = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType> _getInventoryQtyAdjBrw_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.inventory.CallContextDataSetType>();
        try{
        	port.getInventoryQtyAdjBrw(_getInventoryQtyAdjBrw_companyID, _getInventoryQtyAdjBrw_partNum, _getInventoryQtyAdjBrw_wareHouseCode, _getInventoryQtyAdjBrw_callContextIn, _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult, _getInventoryQtyAdjBrw_primaryBin, _getInventoryQtyAdjBrw_callContextOut);
        }catch(Exception e){
        	e.printStackTrace();
        }
        if(_getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value == null){
			return null;
		}
        List<StorageLocationDTO> storageLocationList = new ArrayList<StorageLocationDTO>();
        for(Object obj : _getInventoryQtyAdjBrw_getInventoryQtyAdjBrwResult.value.getInventoryQtyAdjBrwDataSet().getInventoryQtyAdjBrwOrWebServiceErrors()){
        	InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw inventoryQtyAdjBrw = (InventoryQtyAdjBrwDataSetType.InventoryQtyAdjBrwDataSet.InventoryQtyAdjBrw)obj;
            String storageLocateonValue =  inventoryQtyAdjBrw.getWareHseCode()+"-"+inventoryQtyAdjBrw.getBinNum();
        	LotNoDTO lotDTO = new LotNoDTO();
        	lotDTO.setLotNo(inventoryQtyAdjBrw.getLotNum());
        	lotDTO.setValue(inventoryQtyAdjBrw.getBaseOnHandQty());
        	String isAdd = "1";
        	if(storageLocationList!=null&&!storageLocationList.isEmpty()){
        		for(StorageLocationDTO dto : storageLocationList){
        			if(dto.getStorageLocation().equals(storageLocateonValue)){
        				dto.getLotNoList().add(lotDTO);
        				isAdd= "0";
        			}
        		}
        	}
        	if(isAdd.equals("1")){
        		StorageLocationDTO dto = new StorageLocationDTO();
        		dto.setStorageLocation(storageLocateonValue);
        		dto.setLotNoList(new ArrayList<LotNoDTO>());
        		dto.getLotNoList().add(lotDTO);
        		storageLocationList.add(dto);
        		 
        	}
        }
        return storageLocationList;
	}
	
	@Traced
	public String getLotNumber(Integer quantity, String catalogNo, String company) throws DatatypeConfigurationException{
		LotSelectUpdateService ss = new LotSelectUpdateService();
        LotSelectUpdateServiceSoap port = ss.getLotSelectUpdateServiceSoap();
        
		 assertUserNameToken(port);
		 System.out.println("Invoking getRows...");
	     java.lang.String _getRows_companyID = company;
	     java.lang.String _getRows_whereClausePartLot = "PartNum='" + catalogNo + "' by PartLot.MfgDt";
	     java.lang.String _getRows_whereClausePartLotAttch = "";
	     int _getRows_pageSize = 0;
	     int _getRows_absolutePage = 0;
	     com.genscript.gsscm.epicorwebservice.stub.lot.CallContextDataSetType _getRows_callContextIn = null;
	     javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.lot.LotSelectUpdateDataSetType> _getRows_getRowsResult = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.lot.LotSelectUpdateDataSetType>();
	     javax.xml.ws.Holder<java.lang.Boolean> _getRows_morePages = new javax.xml.ws.Holder<java.lang.Boolean>();
	     javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.lot.CallContextDataSetType> _getRows_callContextOut = new javax.xml.ws.Holder<com.genscript.gsscm.epicorwebservice.stub.lot.CallContextDataSetType>();
	     try{
	    	 port.getRows(_getRows_companyID, _getRows_whereClausePartLot, _getRows_whereClausePartLotAttch, _getRows_pageSize, _getRows_absolutePage, _getRows_callContextIn, _getRows_getRowsResult, _getRows_morePages, _getRows_callContextOut);
	     }catch(Exception e){
	        	e.printStackTrace();
	     }
	     if(_getRows_getRowsResult.value == null){
				return null;
			}
	     String result = null;
	     for(Object obj : _getRows_getRowsResult.value.getLotSelectUpdateDataSet().getPartLotOrPartLotAttchOrWebServiceErrors()){
	    	 LotSelectUpdateDataSetType.LotSelectUpdateDataSet.PartLot lot = (LotSelectUpdateDataSetType.LotSelectUpdateDataSet.PartLot)obj;
	    	 String lotNum = lot.getLotNum();
	    	 //System.out.println(lot.getMfgDt().toString()+"----"+lotNum+">>>>"+lot.getOnHandQty());
	    	 if(lot.getOnHandQty()!=null && ((lot.getOnHandQty()).compareTo(new BigDecimal(quantity)) >= 0)){
	    		 //System.out.println(lot.getMfgDt().toString()+"----"+lotNum+">>>>"+lot.getOnHandQty());
	    		 System.out.println(">>>>>>>>>>>"+lotNum);
	    		 result = lotNum;
    			break;
	    		 
	    	 }

	     }
	     return result;
	}

	public String getErpUserName() {
		return erpUserName;
	}

	public void setErpUserName(String erpUserName) {
		this.erpUserName = erpUserName;
	}

	public String getErpPassword() {
		return erpPassword;
	}

	public void setErpPassword(String erpPassword) {
		this.erpPassword = erpPassword;
	}

	public List<PurchaseOrderDTO> getPurchaseOrderList(String catalogNo) {
		 
		return null;
	}

}
