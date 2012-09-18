package com.genscript.gsscm.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedex.ship.stub.Address;
import com.fedex.ship.stub.ClientDetail;
import com.fedex.ship.stub.Commodity;
import com.fedex.ship.stub.CompletedPackageDetail;
import com.fedex.ship.stub.CompletedShipmentDetail;
import com.fedex.ship.stub.Contact;
import com.fedex.ship.stub.CustomerReference;
import com.fedex.ship.stub.CustomerReferenceType;
import com.fedex.ship.stub.Dimensions;
import com.fedex.ship.stub.DropoffType;
import com.fedex.ship.stub.ExportDetail;
import com.fedex.ship.stub.InternationalDetail;
import com.fedex.ship.stub.InternationalDocumentContentType;
import com.fedex.ship.stub.LabelFormatType;
import com.fedex.ship.stub.LabelSpecification;
import com.fedex.ship.stub.LabelStockType;
import com.fedex.ship.stub.LinearUnits;
import com.fedex.ship.stub.Money;
import com.fedex.ship.stub.Notification;
import com.fedex.ship.stub.NotificationSeverityType;
import com.fedex.ship.stub.PackageRateDetail;
import com.fedex.ship.stub.PackageSpecialServicesRequested;
import com.fedex.ship.stub.PackagingType;
import com.fedex.ship.stub.Party;
import com.fedex.ship.stub.Payment;
import com.fedex.ship.stub.PaymentType;
import com.fedex.ship.stub.Payor;
import com.fedex.ship.stub.ProcessShipmentReply;
import com.fedex.ship.stub.ProcessShipmentRequest;
import com.fedex.ship.stub.RateRequestType;
import com.fedex.ship.stub.RequestedPackageDetailType;
import com.fedex.ship.stub.RequestedPackageLineItem;
import com.fedex.ship.stub.RequestedShipment;
import com.fedex.ship.stub.ServiceType;
import com.fedex.ship.stub.ShipPortType;
import com.fedex.ship.stub.ShipServiceLocator;
import com.fedex.ship.stub.ShippingDocument;
import com.fedex.ship.stub.ShippingDocumentImageType;
import com.fedex.ship.stub.ShippingDocumentPart;
import com.fedex.ship.stub.Surcharge;
import com.fedex.ship.stub.TrackingId;
import com.fedex.ship.stub.TrackingIdType;
import com.fedex.ship.stub.TransactionDetail;
import com.fedex.ship.stub.VersionId;
import com.fedex.ship.stub.WebAuthenticationCredential;
import com.fedex.ship.stub.WebAuthenticationDetail;
import com.fedex.ship.stub.Weight;
import com.fedex.ship.stub.WeightUnits;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.ExceptionOut;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.ws.WSException;

@Service
public class ShipWebServiceClient {
	@Autowired
	private ExceptionService exceptionUtil;

	@Autowired
	private FileService fileService;
	
	public static void main(String[] args) {
		ProcessShipmentRequest request = buildRequest(); // Build a request
		// object
		// ProcessShipmentRequest request =
		// buildExpressDomesticRequest(serviceType, packagingType,
		// shipPersonName, recpPersonName, recpCompanyName, recpPhoneNumber,
		// recpStreetLine, recpCity, recpStateCode, recpPostalCode,
		// recpCountryCode, weight, dimLength, dimWidth, dimHeight,
		// customerReference, customsValueAmount, numberOfPieces, description,
		// unitPriceAmount, customsCommAmount);
		//
		try {
			// Initialize the service
			ShipServiceLocator service;
			ShipPortType port;
			//
			service = new ShipServiceLocator();
			updateEndPoint(service);
			port = service.getShipServicePort();
			//
			ProcessShipmentReply reply = port.processShipment(request); // This
			// is
			// the
			// call
			// to
			// the
			// ship
			// web
			// service
			// passing
			// in a
			// request
			// object
			// and
			// returning
			// a
			// reply
			// object
			//
			if (isResponseOk(reply.getHighestSeverity())) // check if the call
			// was successful
			{
				writeServiceOutput(reply,"shippingLable");
			}

			printNotifications(reply.getNotifications());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getFexdexTrackingNumberM(boolean bGround,
			ServiceType serviceType, PackagingType packagingType,
			String shipPersonName, OrderAddress orderAddress,
			Shipment shipment, ShipPackage shipPackage, Integer packageCount,
			String poNo,String shippingLable) {
		ProcessShipmentRequest request;
		String trackingNumber = "";

		request = buildGroundDomesticMPSRequestM(serviceType, packagingType,
				shipPersonName, orderAddress, shipment, shipPackage,
				packageCount, poNo);
		try {
			// Initialize the service
			ShipServiceLocator service;
			ShipPortType port;
			//
			service = new ShipServiceLocator();
			updateEndPoint(service);
			port = service.getShipServicePort();
			//
			ProcessShipmentReply reply = port.processShipment(request); // This
			// is
			// the
			// call
			// to
			// the
			// ship
			// web
			// service
			// passing
			// in a
			// request
			// object
			// and
			// returning
			// a
			// reply
			// object
			//
			if (isResponseOk(reply.getHighestSeverity())) // check if the call
			// was successful
			{
				writeServiceOutput(reply,shippingLable);
			}
			trackingNumber = reply.getCompletedShipmentDetail()
					.getCompletedPackageDetails(0).getTrackingIds(0)
					.getTrackingNumber();
			printNotifications(reply.getNotifications());
			return trackingNumber;
		} catch (Exception e) {
			e.printStackTrace();
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			ExceptionOut.printException(exDTO);
			// System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * @param orderAddress
	 * @param packages
	 * @param poNo
	 * @param bGround
	 * @param serviceType
	 * @param packagingType
	 * @param dropoffType
	 * @param shipPersonName
	 * @param recpPersonName
	 * @param recpCompanyName
	 * @param recpPhoneNumber
	 * @param recpStreetLine
	 * @param recpCity
	 * @param recpStateCode
	 * @param recpPostalCode
	 * @param recpCountryCode
	 * @param packageCount
	 * @param weight
	 * @param dimLength
	 * @param dimWidth
	 * @param dimHeight
	 * @param customerReference
	 * @param masterTrackingId
	 * @param totalWeight
	 * @param customsValueAmount
	 * @param numberOfPieces
	 * @param description
	 * @param unitPriceAmount
	 * @param customsCommAmount
	 * @param shippingLable
	 * @param amount
	 * @param currency
	 * @param qty
	 * @param uom
	 * @param ciItemDesc
	 * @return
	 */
	public String getFexdexTrackingNumber(
			ShipPackage packages, String poNo, boolean bGround,
			ServiceType serviceType, PackagingType packagingType,DropoffType dropoffType,
			String shipPersonName, String recpPersonName,
			String recpCompanyName, String recpPhoneNumber,
			String recpStreetLine, String recpCity, String recpStateCode,
			String recpPostalCode, String recpCountryCode, String packageCount,
			double weight, String dimLength, String dimWidth, String dimHeight,
			String customerReference, String masterTrackingId,
			double totalWeight, double customsValueAmount,
			String numberOfPieces, String description, double unitPriceAmount,
			double customsCommAmount,String shippingLable,Double amount, String currency,Integer qty,String uom,String ciItemDesc) {
		ProcessShipmentRequest request;
		String trackingNumber = "";
		if (bGround) {
			if (packageCount != null && Integer.parseInt(packageCount) > 1) {
				request = buildGroundDomesticMPSRequest(
						serviceType, packagingType, shipPersonName,
						recpPersonName, recpCompanyName, recpPhoneNumber,
						recpStreetLine, recpCity, recpStateCode,
						recpPostalCode, recpCountryCode, packageCount, weight,
						dimLength, dimWidth, dimHeight, customerReference,
						masterTrackingId, totalWeight);
			} else {
				request = buildGroundDomesticRequest(serviceType,
						packagingType, shipPersonName, recpPersonName,
						recpCompanyName, recpPhoneNumber, recpStreetLine,
						recpCity, recpStateCode, recpPostalCode,
						recpCountryCode, totalWeight, dimLength, dimWidth,
						dimHeight, customerReference);
			}
		} else {
			System.out.println(serviceType.toString());
			
			request = buildExpressDomesticRequest(serviceType, packagingType,dropoffType,
					shipPersonName, recpPersonName, recpCompanyName,
					recpPhoneNumber, recpStreetLine, recpCity, recpStateCode,
					recpPostalCode, recpCountryCode, totalWeight, dimLength,
					dimWidth, dimHeight, customerReference, customsValueAmount,
					numberOfPieces, description, unitPriceAmount,
					customsCommAmount, packages, poNo,amount,currency,qty,uom,ciItemDesc);
		}
		try {
			// Initialize the service
			ShipServiceLocator service;
			ShipPortType port;
			//
			System.out.println("<><><><...");
			service = new ShipServiceLocator();
			updateEndPoint(service);
			port = service.getShipServicePort();
			//
			//packages.setCiItemDesc(ciItemDesc);
			System.out.println(">>>>>>>>>>>>>>>>...");
			ProcessShipmentReply reply = port.processShipment(request); // This
			System.out.println("<<<<<<<<<<<<<<<<<<...");
			// is
			// the
			// call
			// to
			// the
			// ship
			// web
			// service
			// passing
			// in a
			// request
			// object
			// and
			// returning
			// a
			// reply
			// object
			//
			if (isResponseOk(reply.getHighestSeverity())) // check if the call
			// was successful
			{
				trackingNumber = reply.getCompletedShipmentDetail()
				.getCompletedPackageDetails(0).getTrackingIds(0)
				.getTrackingNumber();
				System.out.println("11111111111111111111");
				writeServiceOutput(reply,fileService.getUploadPath() +trackingNumber);
				writeServiceOutput(reply,fileService.getUploadPath() +shippingLable);
			}else if(reply.getNotifications().length>0){
				trackingNumber = "ERROR:"+reply.getNotifications()[0].getMessage();
			}else{
				trackingNumber = "ERROR:(503)Service Temporarily Unavailable.";
			}
			
			printNotifications(reply.getNotifications());
			return trackingNumber;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	//
	private static ProcessShipmentRequest buildRequest() {
		ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build
		// a
		// request
		// object

		request.setClientDetail(createClientDetail());
		request.setWebAuthenticationDetail(createWebAuthenticationDetail());
		//
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail
				.setCustomerTransactionId("java sample - Domestic Ground Shipment"); // The
		// client
		// will
		// get
		// the
		// same
		// value
		// back
		// in
		// the
		// response
		request.setTransactionDetail(transactionDetail);
		//
		VersionId versionId = new VersionId("ship", 7, 0, 0);
		request.setVersion(versionId);
		//
		RequestedShipment requestedShipment = new RequestedShipment();
		requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date
		// and time
		requestedShipment.setServiceType(ServiceType.FEDEX_GROUND); // Service
		// types are
		// STANDARD_OVERNIGHT,
		// PRIORITY_OVERNIGHT,
		// FEDEX_GROUND
		// ...
		requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP);
		requestedShipment.setPackagingType(PackagingType.YOUR_PACKAGING); // Packaging
		// type
		// FEDEX_BOX,
		// FEDEX_PAK,
		// FEDEX_TUBE,
		// YOUR_PACKAGING,
		// ...

		//
		Party shipper = new Party();
		Contact contactShip = new Contact();
		contactShip.setCompanyName("Sender Company Name");
		contactShip.setPhoneNumber("0805522713");
		shipper.setContact(contactShip);
		Address addressShip = new Address();
		addressShip.setStreetLines(new String[] { "Address Line 1" });
		addressShip.setCity("Collierville");
		addressShip.setStateOrProvinceCode("TN");
		addressShip.setPostalCode("38017");
		addressShip.setCountryCode("US");
		shipper.setAddress(addressShip);
		requestedShipment.setShipper(shipper); // Sender information
		//
		Party recipient = new Party(); // Recipient information
		Contact contactRecip = new Contact();
		contactRecip.setCompanyName("Recipient Company Name");
		contactRecip.setPhoneNumber("9012637906");
		recipient.setContact(contactRecip);
		//
		Address addressRecip = new Address();
		addressRecip.setStreetLines(new String[] { "W 34th Street" });
		addressRecip.setCity("Austin");
		addressRecip.setStateOrProvinceCode("TX");
		addressRecip.setPostalCode("78705");
		addressRecip.setCountryCode("US");
		addressRecip.setResidential(new Boolean(false));
		recipient.setAddress(addressRecip);
		requestedShipment.setRecipient(recipient);

		//
		Payment scp = new Payment(); // Payment information
		scp.setPaymentType(PaymentType.RECIPIENT);
		scp.setPayor(new Payor());
		scp.getPayor().setAccountNumber(getPayorAccountNumber());
		scp.getPayor().setCountryCode("US");
		requestedShipment.setShippingChargesPayment(scp);
		//

		LabelSpecification labelSpecification = new LabelSpecification(); // Label
		// specification
		labelSpecification.setImageType(ShippingDocumentImageType.PDF);// Image
		// types
		// PDF,
		// PNG,
		// DPL,
		// ...
		labelSpecification.setLabelFormatType(LabelFormatType.COMMON2D);
		requestedShipment.setLabelSpecification(labelSpecification);

		//
		RateRequestType[] rrt = new RateRequestType[] { RateRequestType.ACCOUNT }; // Rate
		// types
		// requested
		// LIST,
		// MULTIWEIGHT,
		// ...
		requestedShipment.setRateRequestTypes(rrt);
		requestedShipment.setPackageCount(new NonNegativeInteger("1"));
		requestedShipment
				.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);

		//
		RequestedPackageLineItem[] rp = new RequestedPackageLineItem[] { new RequestedPackageLineItem() };
		rp[0].setWeight(new Weight()); // Package weight information
		rp[0].getWeight().setValue(new BigDecimal(50.5));
		rp[0].getWeight().setUnits(WeightUnits.LB);
		rp[0].setDimensions(new Dimensions());
		rp[0].getDimensions().setLength(new NonNegativeInteger("108"));
		rp[0].getDimensions().setWidth(new NonNegativeInteger("5"));
		rp[0].getDimensions().setHeight(new NonNegativeInteger("5"));
		rp[0].getDimensions().setUnits(LinearUnits.IN);
		rp[0].setCustomerReferences(new CustomerReference[] {
				new CustomerReference(), new CustomerReference(),
				new CustomerReference() }); // Reference details
		rp[0].getCustomerReferences()[0]
				.setCustomerReferenceType(CustomerReferenceType.CUSTOMER_REFERENCE);
		rp[0].getCustomerReferences()[0].setValue("GR4567892");
		rp[0].getCustomerReferences()[1]
				.setCustomerReferenceType(CustomerReferenceType.INVOICE_NUMBER);
		rp[0].getCustomerReferences()[1].setValue("INV4567892");
		rp[0].getCustomerReferences()[2]
				.setCustomerReferenceType(CustomerReferenceType.P_O_NUMBER);
		rp[0].getCustomerReferences()[2].setValue("PO4567892");
		rp[0].setSequenceNumber(new PositiveInteger("1"));
		//
		requestedShipment.setRequestedPackageLineItems(rp);
		//
		request.setRequestedShipment(requestedShipment);
		//
		return request;
	}

	//
	private static void writeServiceOutput(ProcessShipmentReply reply,String shippingLable)
			throws Exception {
		try {
			CompletedShipmentDetail csd = reply.getCompletedShipmentDetail();
			CompletedPackageDetail cpd[] = csd.getCompletedPackageDetails();
			System.out.println("Package details\n");
			for (int i = 0; i < cpd.length; i++) { // Package details / Rating
				// information for each
				// package
				String trackingNumber = cpd[i].getTrackingIds(0)
						.getTrackingNumber();
				System.out.println("Tracking #: " + trackingNumber
						+ " Form ID: " + cpd[i].getTrackingIds(0).getFormId());
				System.out.println("\nRate details\n");
				//
				
				if(cpd[i].getPackageRating()!=null){
					PackageRateDetail[] prd = cpd[i].getPackageRating()
					.getPackageRateDetails();
					for (int j = 0; j < prd.length; j++) {
						if (prd[j].getBillingWeight() != null) {
							System.out.println("Billing weight: "
									+ prd[j].getBillingWeight().getValue() + " "
									+ prd[j].getBillingWeight().getUnits());
						}
						System.out.println("Base charge: "
								+ prd[j].getBaseCharge().getAmount() + " "
								+ prd[j].getBaseCharge().getCurrency());
						System.out.println("Net charge: "
								+ prd[j].getNetCharge().getAmount() + " "
								+ prd[j].getBaseCharge().getCurrency());
						if (null != prd[j].getSurcharges()) {
							Surcharge[] s = prd[j].getSurcharges();
							for (int k = 0; k < s.length; k++) {
								System.out.println(s[k].getSurchargeType()
										+ " surcharge "
										+ s[k].getAmount().getAmount() + " "
										+ s[k].getAmount().getCurrency());
							}
						}
						System.out.println("Total surcharge: "
								+ prd[j].getTotalSurcharges().getAmount() + " "
								+ prd[j].getTotalSurcharges().getCurrency());
						System.out.println("\nRouting details\n");
						System.out.println("URSA prefix: "
								+ csd.getRoutingDetail().getUrsaPrefixCode()
								+ " suffix: "
								+ csd.getRoutingDetail().getUrsaSuffixCode());
						System.out.println("Service commitment: "
								+ csd.getRoutingDetail().getCommitDay()
								+ " Airport ID: "
								+ csd.getRoutingDetail().getAirportId());
						System.out.println("Delivery day: "
								+ csd.getRoutingDetail().getDeliveryDay());

					}
				}
				
				// Write label buffer to file
				ShippingDocument sd = cpd[i].getLabel();
				saveLabelToFile(sd, trackingNumber,shippingLable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
		}
	}

	private static void saveLabelToFile(ShippingDocument shippingDocument,
			String trackingNumber,String shippingLable) throws Exception {
		ShippingDocumentPart[] sdparts = shippingDocument.getParts();
		for (int a = 0; a < sdparts.length; a++) {
			File file = new File( shippingLable);
			if (!file.exists()) {// 如果不存在该文件夹
				file.mkdir();// 新建

			}
			
			ShippingDocumentPart sdpart = sdparts[a];
			String labelFileName = new String(
					shippingLable+"/shipping_label." + trackingNumber + "."
							+ a + ".pdf");
			System.out.println("\nlabel file name " + labelFileName);
			File labelFile = new File(labelFileName);
			FileOutputStream fos = new FileOutputStream(labelFile);
			fos.write(sdpart.getImage());
			fos.close();
		}
	}

	private static String getPayorAccountNumber() {
		// See if payor account number is set as system property,
		// if not default it to "XXX"
		String payorAccountNumber = System.getProperty("Payor.AccountNumber");
		if (payorAccountNumber == null) {
			payorAccountNumber = "510087925"; // Replace "XXX" with the payor
			// account number
		}
		return payorAccountNumber;
	}

	private static boolean isResponseOk(
			NotificationSeverityType notificationSeverityType) {
		if (notificationSeverityType == null) {
			return false;
		}
		if (notificationSeverityType.equals(NotificationSeverityType.WARNING)
				|| notificationSeverityType
						.equals(NotificationSeverityType.NOTE)
				|| notificationSeverityType
						.equals(NotificationSeverityType.SUCCESS)) {
			return true;
		}
		return false;
	}

	private static ClientDetail createClientDetail() {
		ClientDetail clientDetail = new ClientDetail();
		String accountNumber = System.getProperty("accountNumber");
		String meterNumber = System.getProperty("meterNumber");

		//
		// See if the accountNumber and meterNumber properties are set,
		// if set use those values, otherwise default them to "XXX"
		//
		if (accountNumber == null) {
			accountNumber = "510087925"; // Replace "XXX" with clients account
			// number
		}
		if (meterNumber == null) {
			meterNumber = "100011387"; // Replace "XXX" with clients meter
			// number
		}
		clientDetail.setAccountNumber(accountNumber);
		clientDetail.setMeterNumber(meterNumber);
		return clientDetail;
	}

/*	@SuppressWarnings("unused")
	private static ProcessShipmentRequest buildExpressDomesticRequest(
			ServiceType serviceType, PackagingType packagingType,
			String shipPersonName, String shipCompanyName,
			String shipPhoneNumber, String shipStreetLine, String shipCity,
			String shipStateCode, String shipPostalCode,
			String shipCountryCode, String recpPersonName,
			String recpCompanyName, String recpPhoneNumber,
			String recpStreetLine, String recpCity, String recpStateCode,
			String recpPostalCode, String recpCountryCode, double weight,
			String dimLength, String dimWidth, String dimHeight,
			String customerReference, double customsValueAmount,
			String customsValueCurrency, String numberOfPieces,
			String description, double unitPriceAmount, double customsCommAmount) {
		ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build
		// a111
		// request
		// object

		request.setClientDetail(createClientDetail());
		request.setWebAuthenticationDetail(createWebAuthenticationDetail());
		//
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail
				.setCustomerTransactionId("java sample - Domestic Express Ship Request"); // The
		// client
		// will
		// get
		// the
		// same
		// value
		// back
		// in
		// the
		// response
		request.setTransactionDetail(transactionDetail);

		//
		VersionId versionId = new VersionId("ship", 7, 0, 0);
		request.setVersion(versionId);
		//
		RequestedShipment requestedShipment = new RequestedShipment();
		requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date
		// and time
		requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP); // Dropoff
		// Types
		// are
		// BUSINESS_SERVICE_CENTER,
		// DROP_BOX,
		// REGULAR_PICKUP,
		// REQUEST_COURIER,
		// STATION
		requestedShipment.setServiceType(serviceType); // Service types are
		// STANDARD_OVERNIGHT,
		// PRIORITY_OVERNIGHT,
		// FEDEX_GROUND ...
		requestedShipment.setPackagingType(packagingType); // Packaging type
		// FEDEX_BOX,
		// FEDEX_PAK,
		// FEDEX_TUBE,
		// YOUR_PACKAGING,
		// ...
		//
		// Weight weight = new Weight(); // Total weight information
		// weight.setValue(new BigDecimal(50.0));
		// weight.setUnits(WeightUnits.LB);
		// requestedShipment.setTotalWeight(weight);

		// Shiper
		Party shipperParty = new Party(); // Sender information
		Contact shipperContact = new Contact();
		shipperContact.setPersonName(shipPersonName);
		if (StringUtils.isEmpty(shipCompanyName)) {
			shipCompanyName = "Genscript USA Inc.";
		}
		shipperContact.setCompanyName(shipCompanyName);
		if (StringUtils.isEmpty(shipPhoneNumber)) {
			shipPhoneNumber = "7328859188";
		}
		shipperContact.setPhoneNumber(shipPhoneNumber);
		Address shipperAddress = new Address();
		if (StringUtils.isEmpty(shipStreetLine)) {
			shipStreetLine = "860 Centennial Ave.";
		}
		shipperAddress.setStreetLines(new String[] { shipStreetLine });
		if (StringUtils.isEmpty(shipCity)) {
			shipCity = "Piscataway";
		}
		shipperAddress.setCity(shipCity);
		if (StringUtils.isEmpty(shipStateCode)) {
			shipStateCode = "NJ";
		}
		shipperAddress.setStateOrProvinceCode(shipStateCode);
		if (StringUtils.isEmpty(shipPostalCode)) {
			shipPostalCode = "08854";
		}
		shipperAddress.setPostalCode(shipPostalCode);
		if (StringUtils.isEmpty(shipCountryCode)) {
			shipCountryCode = "US";
		}
		shipperAddress.setCountryCode(shipCountryCode);
		shipperParty.setContact(shipperContact);
		shipperParty.setAddress(shipperAddress);
		requestedShipment.setShipper(shipperParty);

		// Recp
		Party recipientParty = new Party(); // Recipient information
		Contact recipientContact = new Contact();
		recipientContact.setPersonName(recpPersonName);
		recipientContact.setCompanyName(recpCompanyName);
		recipientContact.setPhoneNumber(recpPhoneNumber);
		Address recipientAddress = new Address();
		recipientAddress.setStreetLines(new String[] { recpStreetLine });
		recipientAddress.setCity(recpCity);
		recipientAddress.setStateOrProvinceCode(recpStateCode);
		recipientAddress.setPostalCode(recpPostalCode);
		recipientAddress.setCountryCode(recpCountryCode);
		recipientAddress.setResidential(Boolean.valueOf(true));
		recipientParty.setContact(recipientContact);
		recipientParty.setAddress(recipientAddress);
		requestedShipment.setRecipient(recipientParty);

		// ship payment
		Payment payment = new Payment(); // Payment information
		payment.setPaymentType(PaymentType.SENDER);
		Payor payor = new Payor();
		payor.setAccountNumber(getPayorAccountNumber());
		payor.setCountryCode(shipCountryCode);
		payment.setPayor(payor);
		requestedShipment.setShippingChargesPayment(payment);

		//
		// requestedShipment.setRequestedPackageLineItems(new
		// RequestedPackageLineItem[] {new RequestedPackageLineItem()});
		// requestedShipment.getRequestedPackageLineItems(0).setSequenceNumber(new
		// PositiveInteger("1"));
		// requestedShipment.getRequestedPackageLineItems(0).setSpecialServicesRequested(new
		// PackageSpecialServicesRequested());
		// requestedShipment.getRequestedPackageLineItems(0).getSpecialServicesRequested().setDryIceWeight(new
		// Weight(WeightUnits.LB, new BigDecimal(10.0)));

		LabelSpecification labelSpecification = getLabelSpecification();
		// labelSpecification.setLabelPrintingOrientation(LabelPrintingOrientationType.TOP_EDGE_OF_TEXT_FIRST);
		requestedShipment.setLabelSpecification(labelSpecification);
		//
		RateRequestType rateRequestType[] = new RateRequestType[1];
		rateRequestType[0] = RateRequestType.ACCOUNT; // Rate types requested
		// LIST, MULTIWEIGHT,
		// ...
		requestedShipment.setRateRequestTypes(rateRequestType);
		requestedShipment.setPackageCount(new NonNegativeInteger("1"));
		requestedShipment
				.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
		//
		RequestedPackageLineItem[] rp = new RequestedPackageLineItem[] { new RequestedPackageLineItem() };
		rp[0].setWeight(new Weight()); // Package weight information
		rp[0].getWeight().setValue(new BigDecimal(weight));
		rp[0].getWeight().setUnits(WeightUnits.LB);
		if (StringUtils.isNotEmpty(dimLength)) {
			rp[0].setDimensions(new Dimensions());
			rp[0].getDimensions().setLength(new NonNegativeInteger(dimLength));
			rp[0].getDimensions().setWidth(new NonNegativeInteger(dimWidth));
			rp[0].getDimensions().setHeight(new NonNegativeInteger(dimHeight));
			rp[0].getDimensions().setUnits(LinearUnits.IN);
		}
		rp[0]
				.setCustomerReferences(new CustomerReference[] { new CustomerReference() });
		rp[0].getCustomerReferences()[0]
				.setCustomerReferenceType(CustomerReferenceType.CUSTOMER_REFERENCE);
		rp[0].getCustomerReferences()[0].setValue(customerReference);
		//
		requestedShipment.setRequestedPackageLineItems(rp);

		if (!recpCountryCode.equals("US")) {
			InternationalDetail intd = new InternationalDetail(); // International
			// details
			intd.setDutiesPayment(payment);
			intd
					.setDocumentContent(InternationalDocumentContentType.NON_DOCUMENTS);
			intd.setCustomsValue(new Money());
			intd.getCustomsValue().setAmount(
					new java.math.BigDecimal(customsValueAmount));
			if (StringUtils.isEmpty(customsValueCurrency)) {
				customsValueCurrency = "USD";
			}
			intd.getCustomsValue().setCurrency(customsValueCurrency);
			Commodity[] commodities = new Commodity[] { new Commodity() }; // Commodity
			// details
			commodities[0].setNumberOfPieces(new NonNegativeInteger(
					numberOfPieces));
			commodities[0].setDescription(description);
			commodities[0].setCountryOfManufacture("US");
			commodities[0].setWeight(new Weight());
			commodities[0].getWeight().setValue(new BigDecimal(0.1));
			commodities[0].getWeight().setUnits(WeightUnits.LB);
			commodities[0].setQuantity(new NonNegativeInteger(numberOfPieces));
			commodities[0].setQuantityUnits(WeightUnits.LB.getValue());
			commodities[0].setUnitPrice(new Money());
			commodities[0].getUnitPrice().setAmount(
					new java.math.BigDecimal(unitPriceAmount));
			commodities[0].getUnitPrice().setCurrency("USD");
			commodities[0].setCustomsValue(new Money());
			commodities[0].getCustomsValue().setAmount(
					new java.math.BigDecimal(customsCommAmount));
			commodities[0].getCustomsValue().setCurrency("USD");
			intd.setCommodities(commodities);
			requestedShipment.setInternationalDetail(intd);
		}

		//
		request.setRequestedShipment(requestedShipment);
		//
		return request;
	}*/

	private static ProcessShipmentRequest buildExpressDomesticRequest(
			ServiceType serviceType, PackagingType packagingType,DropoffType dropoffType,
			String shipPersonName, String recpPersonName,
			String recpCompanyName, String recpPhoneNumber,
			String recpStreetLine, String recpCity, String recpStateCode,
			String recpPostalCode, String recpCountryCode, double weight,
			String dimLength, String dimWidth, String dimHeight,
			String customerReference, double customsValueAmount,
			String numberOfPieces, String description, double unitPriceAmount,
			double customsCommAmount, 
			ShipPackage packages, String poNo,Double amount,String currency,Integer qty,String uom,String ciItemDesc) {
		ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build
																		// a
																		// request
																		// object

		request.setClientDetail(createClientDetail());
		request.setWebAuthenticationDetail(createWebAuthenticationDetail());
		// 
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail
				.setCustomerTransactionId("java sample - Domestic Express Ship Request"); // The
																							// client
																							// will
																							// get
																							// the
																							// same
																							// value
																							// back
																							// in
																							// the
																							// response
		request.setTransactionDetail(transactionDetail);

		//
		VersionId versionId = new VersionId("ship", 7, 0, 0);
		request.setVersion(versionId);
		//
		RequestedShipment requestedShipment = new RequestedShipment();
		requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date
																	// and time
		requestedShipment.setDropoffType(dropoffType); // Dropoff
																		// Types
																		// are
																		// BUSINESS_SERVICE_CENTER,
																		// DROP_BOX,
																		// REGULAR_PICKUP,
																		// REQUEST_COURIER,
																		// STATION
		
		requestedShipment.setServiceType(serviceType);
		// Service types are STANDARD_OVERNIGHT, PRIORITY_OVERNIGHT,
		// FEDEX_GROUND ...
		requestedShipment.setPackagingType(packagingType); // Packaging
																			// type
																			// FEDEX_BOX,
																			// FEDEX_PAK,
																			// FEDEX_TUBE,
																			// YOUR_PACKAGING,
																			// ...
		//
		Weight weight1 = new Weight(); // Total weight information
		weight1.setValue(new BigDecimal(packages.getActualWeight()));
		weight1.setUnits(WeightUnits.LB);
		requestedShipment.setTotalWeight(weight1);
		//
		Party shipperParty = getShipper(shipPersonName);
		requestedShipment.setShipper(shipperParty);
		//
		Party recipientParty = getRecpoentM(packages);
		requestedShipment.setRecipient(recipientParty);

		//
		Payment payment = new Payment(); // Payment information
		payment.setPaymentType(PaymentType.RECIPIENT);
		Payor payor = new Payor();
		payor.setAccountNumber(getPayorAccountNumber());
		payor.setCountryCode("US");
		payment.setPayor(payor);
		requestedShipment.setShippingChargesPayment(payment);
		//	    	    	  
		/*
		 * requestedShipment.setRequestedPackageLineItems(new
		 * RequestedPackageLineItem[] {new RequestedPackageLineItem()});
		 * requestedShipment
		 * .getRequestedPackageLineItems(0).setSequenceNumber(new
		 * PositiveInteger("1"));
		 * requestedShipment.getRequestedPackageLineItems(
		 * 0).setSpecialServicesRequested(new
		 * PackageSpecialServicesRequested());
		 * requestedShipment.getRequestedPackageLineItems
		 * (0).getSpecialServicesRequested().setDryIceWeight(new
		 * Weight(WeightUnits.LB, new BigDecimal(10.0)));
		 */
		//	    
		LabelSpecification labelSpecification = new LabelSpecification(); // Label
																			// specification
		labelSpecification.setImageType(ShippingDocumentImageType.PDF);// Image
																		// types
																		// PDF,
																		// PNG,
																		// DPL,
																		// ...
		labelSpecification.setLabelFormatType(LabelFormatType.COMMON2D); // LABEL_DATA_ONLY,
																			// COMMON2D
	    labelSpecification.setLabelStockType(LabelStockType.value4); //
		//STOCK_4X6.75_LEADING_DOC_TAB
		// labelSpecification.setLabelPrintingOrientation(LabelPrintingOrientationType.TOP_EDGE_OF_TEXT_FIRST);
		requestedShipment.setLabelSpecification(labelSpecification);
		//
		RateRequestType rateRequestType[] = new RateRequestType[1];
		rateRequestType[0] = RateRequestType.ACCOUNT; // Rate types requested
														// LIST, MULTIWEIGHT,
														// ...
		requestedShipment.setRateRequestTypes(rateRequestType);
		requestedShipment.setPackageCount(new NonNegativeInteger("1"));
		requestedShipment
				.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
		//
		
		 RequestedPackageLineItem rp[] = new RequestedPackageLineItem[1];
		    Weight rpWeight = new Weight(); // Package weight information
		    rpWeight.setValue(new BigDecimal(packages.getActualWeight()));
		    rpWeight.setUnits(WeightUnits.LB);
		    rp[0] = new RequestedPackageLineItem();
		    rp[0].setWeight(rpWeight);
		    rp[0].setSequenceNumber(new PositiveInteger("1"));
		    rp[0].setCustomerReferences(new CustomerReference[] {
					new CustomerReference(), new CustomerReference(),
					new CustomerReference() }); // Reference details
			rp[0].getCustomerReferences()[0]
					.setCustomerReferenceType(CustomerReferenceType.CUSTOMER_REFERENCE);
			rp[0].getCustomerReferences()[0].setValue(customerReference);
			rp[0].getCustomerReferences()[1]
					.setCustomerReferenceType(CustomerReferenceType.INVOICE_NUMBER);
			if(packages.getInvoiceNo()!=null){
				rp[0].getCustomerReferences()[1].setValue(packages.getInvoiceNo().toString());
			}else{
				rp[0].getCustomerReferences()[1].setValue("");
			}
			
			rp[0].getCustomerReferences()[2]
					.setCustomerReferenceType(CustomerReferenceType.P_O_NUMBER);
			if(poNo!=null){
				rp[0].getCustomerReferences()[2].setValue(poNo);
			}else{
				rp[0].getCustomerReferences()[2].setValue("");
			}
			
/*			rp[0].getCustomerReferences()[3]
			        .setCustomerReferenceType(CustomerReferenceType.DEPARTMENT_NUMBER);
			if(orderAddress.getDeptId()!=null){
				rp[0].getCustomerReferences()[3].setValue(orderAddress.getDeptId().toString());
			}else{
				rp[0].getCustomerReferences()[3].setValue("");
			}*/
			
			rp[0].setSequenceNumber(new PositiveInteger("1"));
			//
		    //
		    requestedShipment.setRequestedPackageLineItems(rp);
		    if (packages.getRcpCountry().equals( "US")) {
			} else {
/*				InternationalDetail internationalDetail = new InternationalDetail();
				
				internationalDetail.setDutiesPayment(payment);
				internationalDetail.setDocumentContent(InternationalDocumentContentType.NON_DOCUMENTS);
				
				requestedShipment.setInternationalDetail(internationalDetail);*/
				/*$requestedShipment['InternationalDetail'] = array(
		        'DutiesPayment' =>$ship_payment,
				'DocumentContent' => 'NON_DOCUMENTS',//'DOCUMENTS_ONLY',  
				'CustomsValue' => array('Amount' => getValue($trade,"1411"), 'Currency' => getValue($trade,"68")),
		                                                                  'Commodities' => array('0' => array('NumberOfPieces' => getValue($trade,"76"),
		                                                                                                       'Description' => getValue($trade,"79"),
		                                                                                                       'CountryOfManufacture' => getValue($trade,"80"),
		                                                                                                       'Weight' => array('Value' => getValue($trade,"1407"), 'Units' => getValue($trade,"414")),
		                                                                                                       'Quantity' => getValue($trade,"82"),
		                                                                                                       'QuantityUnits' => getValue($trade,"414"),
		                                                                                                       'UnitPrice' => array('Amount' => getValue($trade,"1408"), 'Currency' => getValue($trade,"68")),
		                                                                                                       'CustomsValue' => array('Amount' => getValue($trade,"1410"), 'Currency' => getValue($trade,"68")))));  
				InternationalDetail intd = new InternationalDetail(); // International
				// details
				intd.setDutiesPayment(payment);
				intd.setDocumentContent(InternationalDocumentContentType.NON_DOCUMENTS);
				intd.setCustomsValue(new Money());
				intd.getCustomsValue().setAmount(
						new java.math.BigDecimal(customsValueAmount));
				String customsValueCurrency = null;
				if (StringUtils.isEmpty(customsValueCurrency)) {
					customsValueCurrency = "USD";
				}
				intd.getCustomsValue().setCurrency(customsValueCurrency);
				Commodity[] commodities = new Commodity[] { new Commodity() }; // Commodity
				// details
				commodities[0].setNumberOfPieces(new NonNegativeInteger(
						numberOfPieces));
				commodities[0].setDescription(description);
				commodities[0].setCountryOfManufacture("US");
				commodities[0].setWeight(new Weight());
				commodities[0].getWeight().setValue(new BigDecimal(0.1));
				commodities[0].getWeight().setUnits(WeightUnits.LB);
				commodities[0].setQuantity(new NonNegativeInteger(numberOfPieces));
				commodities[0].setQuantityUnits(WeightUnits.LB.getValue());
				commodities[0].setUnitPrice(new Money());
				commodities[0].getUnitPrice().setAmount(
						new java.math.BigDecimal(unitPriceAmount));
				commodities[0].getUnitPrice().setCurrency("USD");
				commodities[0].setCustomsValue(new Money());
				commodities[0].getCustomsValue().setAmount(
						new java.math.BigDecimal(customsCommAmount));
				commodities[0].getCustomsValue().setCurrency("USD");
				intd.setCommodities(commodities);
				requestedShipment.setInternationalDetail(intd);*/
				
		        InternationalDetail intd = new InternationalDetail(); // International details
		        intd.setDutiesPayment(new Payment());
		        intd.getDutiesPayment().setPaymentType(PaymentType.RECIPIENT);
		       /* intd.getDutiesPayment().setPayor(new Payor());
				intd.getDutiesPayment().getPayor().setAccountNumber(getPayorAccountNumber());
		        intd.getDutiesPayment().getPayor().setCountryCode(orderAddress.getCountry());
*/
		        intd.setCustomsValue(new Money());
		        intd.getCustomsValue().setAmount(packages.getInsuranceValue());
		        intd.getCustomsValue().setCurrency(currency);
		        
		        intd.setDocumentContent(InternationalDocumentContentType.DOCUMENTS_ONLY);
		        //
		        Commodity[] commodities = new Commodity[] { new Commodity() }; // Commodity details
		        commodities[0].setNumberOfPieces(new NonNegativeInteger("1"));
		        if(ciItemDesc!=null&&ciItemDesc.length()>50){
		        	commodities[0].setDescription(ciItemDesc.substring(0, 50)+"......");
		        }else{
		        	commodities[0].setDescription(ciItemDesc);
		        }
		        commodities[0].setCountryOfManufacture("US");
		        commodities[0].setWeight(new Weight());
		        commodities[0].getWeight().setValue(new BigDecimal(packages.getActualWeight()));
		        commodities[0].getWeight().setUnits(WeightUnits.LB);
		        commodities[0].setQuantity(new NonNegativeInteger(qty.toString()));
		        commodities[0].setQuantityUnits(uom);
		        commodities[0].setUnitPrice(new Money());
		        commodities[0].getUnitPrice().setAmount(new java.math.BigDecimal(amount));
		        commodities[0].getUnitPrice().setCurrency(currency);
		        commodities[0].setCustomsValue(new Money());
		        commodities[0].getCustomsValue().setAmount(packages.getInsuranceValue());
		        commodities[0].getCustomsValue().setCurrency(currency);
		        intd.setCommodities(commodities);        
		        System.out.println(commodities[0].getDescription()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		       /* // Set export detail - To be used for Shipments that fall under AES Compliance
		        ExportDetail exportDetail = new ExportDetail();
		        exportDetail.setExportComplianceStatement("AES X20080909123456");
		        intd.setExportDetail(exportDetail);*/
		        
		        //
		        requestedShipment.setInternationalDetail(intd);
			}
		// RequestedPackageLineItem[] rp =
		// getRequestedPackageLineItemM(packages,dept,poNo);
		// requestedShipment.setRequestedPackageLineItems(rp);
		//
		request.setRequestedShipment(requestedShipment);
		//
		return request;
	}

	/**
	 * @return
	 */
	private static LabelSpecification getLabelSpecification() {
		LabelSpecification labelSpecification = new LabelSpecification(); // Label
		// specification
		labelSpecification.setImageType(ShippingDocumentImageType.PNG);// Image
		// types
		// PDF,
		// PNG,
		// DPL,
		// ...
		labelSpecification.setLabelFormatType(LabelFormatType.COMMON2D); // LABEL_DATA_ONLY,
		// COMMON2D
		labelSpecification.setLabelStockType(LabelStockType.value4); // STOCK_4X6.75_LEADING_DOC_TAB
		return labelSpecification;
	}

	/**
	 * @return
	 */
	private static Payment getPayment() {
		Payment payment = new Payment(); // Payment information
		payment.setPaymentType(PaymentType.RECIPIENT);
		Payor payor = new Payor();
		payor.setAccountNumber(getPayorAccountNumber());

		payor.setCountryCode("US");
		payment.setPayor(payor);
		return payment;
	}

	/**
	 * @param shipPersonName
	 * @return
	 */
	private static Party getShipper(String shipPersonName) {
		Party shipperParty = new Party(); // Sender information
		Contact shipperContact = new Contact();
		shipperContact.setPersonName(shipPersonName);

		shipperContact.setCompanyName("Genscript USA Inc.");
		shipperContact.setPhoneNumber("7328859188");
		shipperContact.setFaxNumber("7322100262");
		shipperContact.setEMailAddress("accounting@genscript.com");
		Address shipperAddress = new Address();
		shipperAddress.setStreetLines(new String[] { "860 Centennial Ave." });
		shipperAddress.setCity("Piscataway");
		shipperAddress.setStateOrProvinceCode("NJ");
		shipperAddress.setPostalCode("08854");
		shipperAddress.setCountryCode("US");
		shipperParty.setContact(shipperContact);
		shipperParty.setAddress(shipperAddress);
		return shipperParty;
	}

	private static ProcessShipmentRequest buildGroundDomesticRequest(
			ServiceType serviceType,
			PackagingType packagingType, String shipPersonName,
			String recpPersonName, String recpCompanyName,
			String recpPhoneNumber, String recpStreetLine, String recpCity,
			String recpStateCode, String recpPostalCode,
			String recpCountryCode, double weight, String dimLength,
			String dimWidth, String dimHeight, String customerReference) {
		ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build
		// a
		// request
		// object

		request.setClientDetail(createClientDetail());
		request.setWebAuthenticationDetail(createWebAuthenticationDetail());
		//
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail
				.setCustomerTransactionId("java sample - Domestic Ground Shipment"); // The
		// client
		// will
		// get
		// the
		// same
		// value
		// back
		// in
		// the
		// response
		request.setTransactionDetail(transactionDetail);
		//
		VersionId versionId = new VersionId("ship", 7, 0, 0);
		request.setVersion(versionId);
		//
		RequestedShipment requestedShipment = new RequestedShipment();
		requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date
		// and time
		requestedShipment.setServiceType(serviceType); // Service types are
		// STANDARD_OVERNIGHT,
		// PRIORITY_OVERNIGHT,
		// FEDEX_GROUND ...
		requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP);
		requestedShipment.setPackagingType(packagingType); // Packaging type
		// FEDEX_BOX,
		// FEDEX_PAK,
		// FEDEX_TUBE,
		// YOUR_PACKAGING,
		// ...

		//
		Party shipperParty = getShipper(shipPersonName);
		requestedShipment.setShipper(shipperParty); // Sender information

		//
		Party recipientParty = getRecpoent(recpPersonName, recpCompanyName,
				recpPhoneNumber, recpStreetLine, recpCity, recpStateCode,
				recpPostalCode, recpCountryCode);
		requestedShipment.setRecipient(recipientParty);

		//
		Payment payment = getPayment();
		requestedShipment.setShippingChargesPayment(payment);
		//

		LabelSpecification labelSpecification = getLabelSpecification();
		requestedShipment.setLabelSpecification(labelSpecification);

		//
		RateRequestType[] rrt = new RateRequestType[] { RateRequestType.ACCOUNT }; // Rate
		// types
		// requested
		// LIST,
		// MULTIWEIGHT,
		// ...
		requestedShipment.setRateRequestTypes(rrt);
		requestedShipment.setPackageCount(new NonNegativeInteger("1"));
		requestedShipment
				.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);

		//
		RequestedPackageLineItem[] rp = getRequestedPackageLineItem(weight,
				dimLength, dimWidth, dimHeight, customerReference);
		//
		requestedShipment.setRequestedPackageLineItems(rp);
		//
		request.setRequestedShipment(requestedShipment);
		//
		return request;
	}

	/**
	 * @param weight
	 * @param dimLength
	 * @param dimWidth
	 * @param dimHeight
	 * @param customerReference
	 * @return
	 */
	private static RequestedPackageLineItem[] getRequestedPackageLineItem(
			double weight, String dimLength, String dimWidth, String dimHeight,
			String customerReference) {
		RequestedPackageLineItem[] rp = new RequestedPackageLineItem[] { new RequestedPackageLineItem() };
		rp[0].setWeight(new Weight()); // Package weight information
		rp[0].getWeight().setValue(new BigDecimal(weight));
		rp[0].getWeight().setUnits(WeightUnits.LB);
		if (StringUtils.isNotEmpty(dimLength)) {
			rp[0].setDimensions(new Dimensions());
			rp[0].getDimensions().setLength(new NonNegativeInteger(dimLength));
			rp[0].getDimensions().setWidth(new NonNegativeInteger(dimWidth));
			rp[0].getDimensions().setHeight(new NonNegativeInteger(dimHeight));
			rp[0].getDimensions().setUnits(LinearUnits.IN);
		}
		rp[0]
				.setCustomerReferences(new CustomerReference[] { new CustomerReference() });
		rp[0].getCustomerReferences()[0]
				.setCustomerReferenceType(CustomerReferenceType.CUSTOMER_REFERENCE);
		rp[0].getCustomerReferences()[0].setValue(customerReference);
		rp[0].setSequenceNumber(new PositiveInteger("1"));
		return rp;
	}

	private static RequestedPackageLineItem[] getRequestedPackageLineItemM(
			ShipPackage shipPackage, String dept, String poNo) {
		RequestedPackageLineItem[] rp = new RequestedPackageLineItem[] { new RequestedPackageLineItem() };
		rp[0].setWeight(new Weight()); // Package weight information
		rp[0].getWeight().setValue(
				new BigDecimal(shipPackage.getBillableWeight()));
		rp[0].getWeight().setUnits(WeightUnits.LB);
		rp[0].setDimensions(new Dimensions());
		if (shipPackage.getLength() != null) {
			rp[0].getDimensions().setLength(
					new NonNegativeInteger(shipPackage.getLength().toString()));
		}
		if (shipPackage.getWidth() != null) {
			rp[0].getDimensions().setWidth(
					new NonNegativeInteger(shipPackage.getWidth().toString()));
		}
		if (shipPackage.getHeight() != null) {
			rp[0].getDimensions().setHeight(
					new NonNegativeInteger(shipPackage.getHeight().toString()));
		}
		rp[0].getDimensions().setUnits(LinearUnits.IN);
		rp[0].setCustomerReferences(new CustomerReference[] {
				new CustomerReference(), new CustomerReference(),
				new CustomerReference(), new CustomerReference() });
		if (shipPackage.getCustomerCharge() != null) {
			rp[0].getCustomerReferences()[0]
					.setCustomerReferenceType(CustomerReferenceType.CUSTOMER_REFERENCE);
			rp[0].getCustomerReferences()[0].setValue(shipPackage
					.getCustomerCharge().toString());
		}
		if (shipPackage.getInvoiceNo() != null) {
			rp[0].getCustomerReferences()[1]
					.setCustomerReferenceType(CustomerReferenceType.INVOICE_NUMBER);
			rp[0].getCustomerReferences()[1].setValue(shipPackage
					.getInvoiceNo().toString());
		}
		if (poNo != null) {
			rp[0].getCustomerReferences()[2]
					.setCustomerReferenceType(CustomerReferenceType.P_O_NUMBER);
			rp[0].getCustomerReferences()[2].setValue(poNo);
		}
		if (dept != null) {
			rp[0].getCustomerReferences()[3]
					.setCustomerReferenceType(CustomerReferenceType.DEPARTMENT_NUMBER);
			rp[0].getCustomerReferences()[3].setValue(dept);
		}

		rp[0].setSequenceNumber(new PositiveInteger("1"));
		return rp;
	}

	/**
	 * @param recpPersonName
	 * @param recpCompanyName
	 * @param recpPhoneNumber
	 * @param recpStreetLine
	 * @param recpCity
	 * @param recpStateCode
	 * @param recpPostalCode
	 * @param recpCountryCode
	 * @return
	 */
	private static Party getRecpoent(String recpPersonName,
			String recpCompanyName, String recpPhoneNumber,
			String recpStreetLine, String recpCity, String recpStateCode,
			String recpPostalCode, String recpCountryCode) {
		Party recipientParty = new Party(); // Recipient information
		Contact recipientContact = new Contact();
		recipientContact.setPersonName(recpPersonName);
		recipientContact.setCompanyName(recpCompanyName);
		recipientContact.setPhoneNumber(recpPhoneNumber);
		Address recipientAddress = new Address();
		recipientAddress.setStreetLines(new String[] { recpStreetLine });
		recipientAddress.setCity(recpCity);
		recipientAddress.setStateOrProvinceCode(recpStateCode);
		recipientAddress.setPostalCode(recpPostalCode);
		recipientAddress.setCountryCode(recpCountryCode);
		recipientParty.setContact(recipientContact);
		recipientParty.setAddress(recipientAddress);
		return recipientParty;
	}

	private static Party getRecpoentM(ShipPackage packages) {
		Party recipientParty = new Party(); // Recipient information
		Contact recipientContact = new Contact();
		recipientContact.setPersonName(packages.getRcpFirstName() + " "
				+ packages.getRcpLastName());
		// recipientContact.setCompanyName("");
		recipientContact.setPhoneNumber(packages.getRcpPhone());
		recipientContact.setEMailAddress(packages.getRcpBusEmail());
		recipientContact.setFaxNumber(packages.getRcpFax());
		// recipientContact.setPagerNumber(orderAddress.getp)
		recipientContact.setTitle(packages.getRcpTitle());
		Address recipientAddress = new Address();
		List<String> lines = new ArrayList<String>();
		if (!StringUtils.isEmpty(packages.getRcpAddrLine1())) {
			lines.add(packages.getRcpAddrLine1());
		}
		if (!StringUtils.isEmpty(packages.getRcpAddrLine2())) {
			lines.add(packages.getRcpAddrLine2());
		}
		if (!StringUtils.isEmpty(packages.getRcpAddrLine3())) {
			lines.add(packages.getRcpAddrLine3());
		}
		recipientAddress.setStreetLines((String[]) lines
				.toArray(new String[lines.size()]));
		recipientAddress.setCity(packages.getRcpCity());
		recipientAddress.setStateOrProvinceCode(packages.getRcpState());
		recipientAddress.setPostalCode(packages.getRcpZipCode());
		recipientAddress.setCountryCode(packages.getRcpCountry());
		if (packages.getRcpAddrClass() != null
				&& packages.getRcpAddrClass().equals("Residential")) {
			recipientAddress.setResidential(true);
		} else if (packages.getRcpAddrClass() != null) {
			recipientAddress.setResidential(false);
		}

		// recipientAddress.setUrbanizationCode(urbanizationCode)
		recipientParty.setContact(recipientContact);
		recipientParty.setAddress(recipientAddress);
		return recipientParty;
	}

	private static ProcessShipmentRequest buildGroundDomesticMPSRequestM(
			ServiceType serviceType, PackagingType packagingType,
			String shipPersonName, OrderAddress orderAddress,
			Shipment shipment, ShipPackage shipPackage, Integer packageCount,
			String poNo) {
		ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build
		// a
		// request
		// object

		request.setClientDetail(createClientDetail());
		request.setWebAuthenticationDetail(createWebAuthenticationDetail());
		// 
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail
				.setCustomerTransactionId("java sample - Domestic MPS Ground Shipment - Master"); // The
		// client
		// will
		// get
		// the
		// same
		// value
		// back
		// in
		// the
		// response
		request.setTransactionDetail(transactionDetail);
		//
		VersionId versionId = new VersionId("ship", 7, 0, 0);
		request.setVersion(versionId);

		//        
		RequestedShipment requestedShipment = new RequestedShipment();
		requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date
		// and time
		requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP); // Dropoff
		// Types
		// are
		// BUSINESS_SERVICE_CENTER,
		// DROP_BOX,
		// REGULAR_PICKUP,
		// REQUEST_COURIER,
		// STATION
		requestedShipment.setServiceType(serviceType); // Service types are
		// STANDARD_OVERNIGHT,
		// PRIORITY_OVERNIGHT,
		// FEDEX_GROUND ...
		requestedShipment.setPackagingType(packagingType); // Packaging type
		// FEDEX_BOX,
		// FEDEX_PAK,
		// FEDEX_TUBE,
		// YOUR_PACKAGING,
		// ...

		//
		Party shipperParty = getShipper(shipPersonName);
		requestedShipment.setShipper(shipperParty); // Sender information
		//
		Party recipientParty = getRecpoentM(shipPackage);
		requestedShipment.setRecipient(recipientParty);

		//
		Payment payment = getPayment();
		requestedShipment.setShippingChargesPayment(payment);
		//
		LabelSpecification labelSpecification = getLabelSpecification();
		requestedShipment.setLabelSpecification(labelSpecification);
		//
		RateRequestType rateRequestType[] = new RateRequestType[2];
		rateRequestType[0] = RateRequestType.ACCOUNT; // Rate types requested
		// LIST, MULTIWEIGHT,
		// ...
		rateRequestType[1] = RateRequestType.LIST;
		requestedShipment.setRateRequestTypes(rateRequestType);
		requestedShipment.setPackageCount(new NonNegativeInteger(packageCount
				.toString()));
		requestedShipment
				.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
		//
		String dept = null;
		if (orderAddress.getDeptId() != null) {
			dept = orderAddress.getDeptId().toString();
		}
		RequestedPackageLineItem[] rp = getRequestedPackageLineItemM(
				shipPackage, dept, poNo);

		//
		requestedShipment.setRequestedPackageLineItems(rp);

		if (StringUtils.isEmpty(shipPackage.getTrackingNo())) {
			Weight tweight = new Weight(); // Total weight information
			if (shipPackage.getActualWeight() != null) {
				tweight.setValue(new BigDecimal(shipPackage.getActualWeight()));
			}
			tweight.setUnits(WeightUnits.LB);
			requestedShipment.setTotalWeight(tweight);
		} else {
			TrackingId trackingId = new TrackingId();
			trackingId.setTrackingNumber(shipPackage.getTrackingNo());
			trackingId.setTrackingIdType(TrackingIdType.GROUND);
			requestedShipment.setMasterTrackingId(trackingId);
		}
		//
		request.setRequestedShipment(requestedShipment);
		//
		return request;
	}

	private static ProcessShipmentRequest buildGroundDomesticMPSRequest(
			ServiceType serviceType,
			PackagingType packagingType, String shipPersonName,
			String recpPersonName, String recpCompanyName,
			String recpPhoneNumber, String recpStreetLine, String recpCity,
			String recpStateCode, String recpPostalCode,
			String recpCountryCode, String packageCount, double weight,
			String dimLength, String dimWidth, String dimHeight,
			String customerReference, String masterTrackingId,
			double totalWeight) {
		ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build
		// a
		// request
		// object

		request.setClientDetail(createClientDetail());
		request.setWebAuthenticationDetail(createWebAuthenticationDetail());
		// 
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail
				.setCustomerTransactionId("java sample - Domestic MPS Ground Shipment - Master"); // The
		// client
		// will
		// get
		// the
		// same
		// value
		// back
		// in
		// the
		// response
		request.setTransactionDetail(transactionDetail);
		//
		VersionId versionId = new VersionId("ship", 7, 0, 0);
		request.setVersion(versionId);

		//        
		RequestedShipment requestedShipment = new RequestedShipment();
		requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date
		// and time
		requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP); // Dropoff
		// Types
		// are
		// BUSINESS_SERVICE_CENTER,
		// DROP_BOX,
		// REGULAR_PICKUP,
		// REQUEST_COURIER,
		// STATION
		requestedShipment.setServiceType(serviceType); // Service types are
		// STANDARD_OVERNIGHT,
		// PRIORITY_OVERNIGHT,
		// FEDEX_GROUND ...
		requestedShipment.setPackagingType(packagingType); // Packaging type
		// FEDEX_BOX,
		// FEDEX_PAK,
		// FEDEX_TUBE,
		// YOUR_PACKAGING,
		// ...

		//
		Party shipperParty = getShipper(shipPersonName);
		requestedShipment.setShipper(shipperParty); // Sender information
		//
		//Party recipientParty = getRecpoentM(orderAddress);
		//requestedShipment.setRecipient(recipientParty);

		//
		Payment payment = getPayment();
		requestedShipment.setShippingChargesPayment(payment);
		//
		LabelSpecification labelSpecification = getLabelSpecification();
		requestedShipment.setLabelSpecification(labelSpecification);
		//
		RateRequestType rateRequestType[] = new RateRequestType[2];
		rateRequestType[0] = RateRequestType.ACCOUNT; // Rate types requested
		// LIST, MULTIWEIGHT,
		// ...
		rateRequestType[1] = RateRequestType.LIST;
		requestedShipment.setRateRequestTypes(rateRequestType);
		requestedShipment.setPackageCount(new NonNegativeInteger(packageCount));
		requestedShipment
				.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
		//
		RequestedPackageLineItem[] rp = getRequestedPackageLineItem(weight,
				dimLength, dimWidth, dimHeight, customerReference);
		//
		requestedShipment.setRequestedPackageLineItems(rp);

		if (StringUtils.isEmpty(masterTrackingId)) {
			Weight tweight = new Weight(); // Total weight information
			tweight.setValue(new BigDecimal(totalWeight));
			tweight.setUnits(WeightUnits.LB);
			requestedShipment.setTotalWeight(tweight);
		} else {
			TrackingId trackingId = new TrackingId();
			trackingId.setTrackingNumber(masterTrackingId);
			trackingId.setTrackingIdType(TrackingIdType.GROUND);
			requestedShipment.setMasterTrackingId(trackingId);
		}
		//
		request.setRequestedShipment(requestedShipment);
		//
		return request;
	}

	private static WebAuthenticationDetail createWebAuthenticationDetail() {
		WebAuthenticationCredential wac = new WebAuthenticationCredential();
		String key = System.getProperty("key");
		String password = System.getProperty("password");

		//
		// See if the key and password properties are set,
		// if set use those values, otherwise default them to "XXX"
		//
		if (key == null) {
			key = "rGAMZIvdcXp0gFT3"; // Replace "XXX" with clients key
		}
		if (password == null) {
			password = "DMrnSgLlw9bKNEvB9bsChy2a9"; // Replace "XXX" with
			// clients password
		}
		wac.setKey(key);
		wac.setPassword(password);
		return new WebAuthenticationDetail(wac);
	}

	private static void printNotifications(Notification[] notifications) {
		System.out.println("Notifications:");
		if (notifications == null || notifications.length == 0) {
			System.out.println("  No notifications returned");
		}
		for (int i = 0; i < notifications.length; i++) {
			Notification n = notifications[i];
			System.out.print("  Notification no. " + i + ": ");
			if (n == null) {
				System.out.println("null");
				continue;
			} else {
				System.out.println("");
			}
			NotificationSeverityType nst = n.getSeverity();

			System.out.println("    Severity: "
					+ (nst == null ? "null" : nst.getValue()));
			System.out.println("    Code: " + n.getCode());
			System.out.println("    Message: " + n.getMessage());
			System.out.println("    Source: " + n.getSource());
		}
	}

	private static void updateEndPoint(ShipServiceLocator serviceLocator) {
		String endPoint = System.getProperty("endPoint");
		if (endPoint != null) {
			serviceLocator.setShipServicePortEndpointAddress(endPoint);
		}
	}
	/*$accountNumber =  "510087925";
	$meterNumber = "100011387";
	$key = "rGAMZIvdcXp0gFT3";
	$password = "DMrnSgLlw9bKNEvB9bsChy2a9";
	$payorAccountNumber = "510087925";


	// $accountNumber =  "476613965";
	// $meterNumber = "101268390";
	// $key = "Mki3uCvXJOO0kl48"; 
	// $password = "iGftVNvR999bhJ4dCTRXHvzbH";
	// $payorAccountNumber = "476613965";
*/}
