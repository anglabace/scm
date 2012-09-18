package com.genscript.gsscm.order.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.dozer.DozerBeanMapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.accounting.dao.ArInvoicePaymentDao;
import com.genscript.gsscm.accounting.dto.ArPaymentAmountDTO;
import com.genscript.gsscm.accounting.entity.ArInvoicePayment;
import com.genscript.gsscm.basedata.dao.CurrencyDao;
import com.genscript.gsscm.basedata.dao.PbCountryDao;
import com.genscript.gsscm.basedata.dao.SpecDropDownListDao;
import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.entity.PbCountry;
import com.genscript.gsscm.basedata.entity.PbCurrency;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.BioInfoService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.CountryCode;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.OligoModificationsType;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderQuoteStautsType;
import com.genscript.gsscm.common.constant.OrderReturnStatusType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.SalesRepSalesRole;
import com.genscript.gsscm.common.constant.SequenceType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.events.CancelOrderEvent;
import com.genscript.gsscm.common.events.NewSalesOrderEvent;
import com.genscript.gsscm.common.events.SendMailAtOnceEvent;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.DateTypeConverter;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.FtpClient;
import com.genscript.gsscm.common.util.PackageHelper;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.SoapUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dao.CouponDao;
import com.genscript.gsscm.customer.dao.CreditCardDao;
import com.genscript.gsscm.customer.dao.CreditRatingDao;
import com.genscript.gsscm.customer.dao.CustInfoStatDao;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerNoteDao;
import com.genscript.gsscm.customer.dao.CustomerPriceBeanDao;
import com.genscript.gsscm.customer.dao.DepartmentDao;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.SalesProjectManagerAssignmentDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.SampleRequestDTO;
import com.genscript.gsscm.customer.entity.Coupon;
import com.genscript.gsscm.customer.entity.CreditCard;
import com.genscript.gsscm.customer.entity.CreditRating;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerNote;
import com.genscript.gsscm.customer.entity.CustomerPriceBean;
import com.genscript.gsscm.customer.entity.Departments;
import com.genscript.gsscm.customer.entity.NoteDocument;
import com.genscript.gsscm.customer.entity.Organization;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.epicorwebservice.service.ErpSalesOrderService;
import com.genscript.gsscm.inventory.dao.WarehouseDao;
import com.genscript.gsscm.inventory.service.InventoryService;
import com.genscript.gsscm.manufacture.entity.ManuDocument;
import com.genscript.gsscm.olddb.dao.ChargerDao;
import com.genscript.gsscm.olddb.entity.Charger;
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.MfgOrderDao;
import com.genscript.gsscm.order.dao.MfgOrderItemDao;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderAntibodyDao;
import com.genscript.gsscm.order.dao.OrderCustCloningDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderDnaSequencingDao;
import com.genscript.gsscm.order.dao.OrderDsLabelsDao;
import com.genscript.gsscm.order.dao.OrderDsPlatesDao;
import com.genscript.gsscm.order.dao.OrderDsSeqDao;
import com.genscript.gsscm.order.dao.OrderErpMappingDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderMailDao;
import com.genscript.gsscm.order.dao.OrderMainBeanDao;
import com.genscript.gsscm.order.dao.OrderMainBeanDtoDao;
import com.genscript.gsscm.order.dao.OrderMutagenesisDao;
import com.genscript.gsscm.order.dao.OrderMutationLibrariesDao;
import com.genscript.gsscm.order.dao.OrderNoteDao;
import com.genscript.gsscm.order.dao.OrderOligoDao;
import com.genscript.gsscm.order.dao.OrderOrfCloneDao;
import com.genscript.gsscm.order.dao.OrderPackageDao;
import com.genscript.gsscm.order.dao.OrderPackageItemDao;
import com.genscript.gsscm.order.dao.OrderPeptideDao;
import com.genscript.gsscm.order.dao.OrderPkgServiceDao;
import com.genscript.gsscm.order.dao.OrderPlasmidPreparationDao;
import com.genscript.gsscm.order.dao.OrderProcessLogDao;
import com.genscript.gsscm.order.dao.OrderPromotionDao;
import com.genscript.gsscm.order.dao.OrderProteinDao;
import com.genscript.gsscm.order.dao.OrderReturnDao;
import com.genscript.gsscm.order.dao.OrderReturnItemDao;
import com.genscript.gsscm.order.dao.OrderRnaDao;
import com.genscript.gsscm.order.dao.OrderServiceDao;
import com.genscript.gsscm.order.dao.OrderSirnaAndMirnaDao;
import com.genscript.gsscm.order.dao.OrderTemplateDao;
import com.genscript.gsscm.order.dao.PaymentVoucherDao;
import com.genscript.gsscm.order.dao.PromotionDao;
import com.genscript.gsscm.order.dao.RelationProductPriceBeanDao;
import com.genscript.gsscm.order.dao.SrchOrderTemplateItemBeanDao;
import com.genscript.gsscm.order.dao.SrchServiceOrderTemplateBeanDao;
import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.dao.TaxRateDao;
import com.genscript.gsscm.order.dto.ErpOrderDtlDTO;
import com.genscript.gsscm.order.dto.ItemOtherInfoForNewDTO;
import com.genscript.gsscm.order.dto.OrderAddressDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderItemOverSizeDTO;
import com.genscript.gsscm.order.dto.OrderMainBeanDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.dto.OrderPackageDTO;
import com.genscript.gsscm.order.dto.OrderPackageItemDTO;
import com.genscript.gsscm.order.dto.OrderPrintDTO;
import com.genscript.gsscm.order.dto.OrderPromotionDTO;
import com.genscript.gsscm.order.dto.OrderReturnDTO;
import com.genscript.gsscm.order.dto.OrderReturnItemDTO;
import com.genscript.gsscm.order.dto.PaymentVoucherDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.MfgOrder;
import com.genscript.gsscm.order.entity.MfgOrderDTO2;
import com.genscript.gsscm.order.entity.MfgOrderItem;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderAntibody;
import com.genscript.gsscm.order.entity.OrderCustCloning;
import com.genscript.gsscm.order.entity.OrderDnaSequencing;
import com.genscript.gsscm.order.entity.OrderDsLabels;
import com.genscript.gsscm.order.entity.OrderDsPlates;
import com.genscript.gsscm.order.entity.OrderDsSeq;
import com.genscript.gsscm.order.entity.OrderErpMapping;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderMail;
import com.genscript.gsscm.order.entity.OrderMain;
import com.genscript.gsscm.order.entity.OrderMainBean;
import com.genscript.gsscm.order.entity.OrderMutagenesis;
import com.genscript.gsscm.order.entity.OrderMutationLibraries;
import com.genscript.gsscm.order.entity.OrderNote;
import com.genscript.gsscm.order.entity.OrderOligo;
import com.genscript.gsscm.order.entity.OrderOrfClone;
import com.genscript.gsscm.order.entity.OrderPackage;
import com.genscript.gsscm.order.entity.OrderPackageItem;
import com.genscript.gsscm.order.entity.OrderPeptide;
import com.genscript.gsscm.order.entity.OrderPkgService;
import com.genscript.gsscm.order.entity.OrderPlasmidPreparation;
import com.genscript.gsscm.order.entity.OrderProcessLog;
import com.genscript.gsscm.order.entity.OrderPromotion;
import com.genscript.gsscm.order.entity.OrderReturn;
import com.genscript.gsscm.order.entity.OrderReturnItem;
import com.genscript.gsscm.order.entity.OrderRna;
import com.genscript.gsscm.order.entity.OrderSirnaAndMirna;
import com.genscript.gsscm.order.entity.OrderTemplate;
import com.genscript.gsscm.order.entity.PaymentVoucher;
import com.genscript.gsscm.order.entity.Promotion;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.orf.dao.Refseq2orfpriceDao;
import com.genscript.gsscm.orf.entity.Refseq2orfprice;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.CatalogDao;
import com.genscript.gsscm.product.dao.OligoBackbonesDao;
import com.genscript.gsscm.product.dao.OligoChimericBasesDao;
import com.genscript.gsscm.product.dao.OligoMixedBasesDao;
import com.genscript.gsscm.product.dao.OligoModificationsDao;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ProductExtendedInfoDao;
import com.genscript.gsscm.product.dao.ShipConditionDao;
import com.genscript.gsscm.product.dto.OligoBackbonesDTO;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.product.entity.Catalog;
import com.genscript.gsscm.product.entity.OligoBackbones;
import com.genscript.gsscm.product.entity.OligoChimericBases;
import com.genscript.gsscm.product.entity.OligoMixedBases;
import com.genscript.gsscm.product.entity.OligoModifications;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.product.entity.ProductExtendedInfo;
import com.genscript.gsscm.product.entity.ShipCondition;
import com.genscript.gsscm.purchase.dao.PurchaseOrderDao;
import com.genscript.gsscm.purchase.dao.VendorDao;
import com.genscript.gsscm.purchase.entity.PurchaseOrder;
import com.genscript.gsscm.purchase.entity.Vendor;
import com.genscript.gsscm.purchase.service.PurchaseService;
import com.genscript.gsscm.quote.dao.PreQuotationDao;
import com.genscript.gsscm.quote.entity.PreQuotation;
import com.genscript.gsscm.quote.entity.QuoteMutationLibraries;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.CurrencyChangeDTO;
import com.genscript.gsscm.quoteorder.dto.CustomServiceDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
import com.genscript.gsscm.quoteorder.dto.InstructionDTO;
import com.genscript.gsscm.quoteorder.dto.ItemBeanDTO;
import com.genscript.gsscm.quoteorder.dto.ItemDiscountDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutationLibrariesDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.PkgServiceDTO;
import com.genscript.gsscm.quoteorder.dto.ProcessLogDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;
import com.genscript.gsscm.quoteorder.dto.ShippingTotalDTO;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceShipConditionDao;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.serv.entity.ServiceShipCondition;
import com.genscript.gsscm.shipment.dao.ShipClerkAssignDao;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.dao.ShipZoneDao;
import com.genscript.gsscm.shipment.dao.ShipmentLinesDao;
import com.genscript.gsscm.shipment.dao.ShipmentsDao;
import com.genscript.gsscm.shipment.dao.ShippingChargeLogDao;
import com.genscript.gsscm.shipment.dao.ShippingClerkShipmentAdjustmentDao;
import com.genscript.gsscm.shipment.entity.ShipClerkAssigned;
import com.genscript.gsscm.shipment.entity.ShipPackage;
import com.genscript.gsscm.shipment.entity.ShipZone;
import com.genscript.gsscm.shipment.entity.Shipment;
import com.genscript.gsscm.shipment.entity.ShipmentLine;
import com.genscript.gsscm.shipment.entity.ShippingClerkShipmentAdjustment;
import com.genscript.gsscm.system.dao.CompanyDao;
import com.genscript.gsscm.system.dao.MailTemplatesDao;
import com.genscript.gsscm.system.dao.UpdateRequestLogDao;
import com.genscript.gsscm.system.entity.Company;
import com.genscript.gsscm.system.entity.MailTemplates;
import com.genscript.gsscm.system.entity.UpdateRequestLog;
import com.genscript.gsscm.systemsetting.dao.BillTerritoryDao;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class OrderService implements ApplicationContextAware {

	@Autowired
	private PublicService publicService; 
	@Autowired
	private CustomerService customerService;
	// 文件处理
	@Autowired
	private FileService fileService;
	@Autowired
	private OrderMainBeanDao orderMainBeanDao;
	// new add
	@Autowired
	private OrderMainBeanDtoDao orderMainBeanDtoDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderProcessLogDao orderProcessLogDao;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private CustomerPriceBeanDao customerPriceBeanDao;
	@Autowired
	private OrderMailDao orderMailDao;
	@Autowired
	private OrderNoteDao orderNoteDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private OrderServiceDao orderServiceDao;
	@Autowired
	private OrderPromotionDao orderPromotionDao;
	@Autowired
	private SalesRepDao salesRepDao;
	// @Autowired
	// private OrderPaymentPlanDao orderPaymentPlanDao;
	@Autowired
	private PaymentVoucherDao paymentVoucherDao;
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private OrderTemplateDao orderTemplateDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private RelationProductPriceBeanDao relationProductPriceBeanDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	@Autowired
	private OrderReturnItemDao orderReturnItemDao;
	@Autowired
	private OrderReturnDao orderReturnDao;
	@Autowired
	private StatusListDao statusListDao;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private CreditCardDao creditCardDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private ShipConditionDao shipConditionDao;
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private OrderPackageItemDao orderPackageItemDao;
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private ArInvoicePaymentDao arInvoicePaymentDao;
	@Autowired
	private ShipZoneDao shipZoneDao;
	@Autowired
	private TaxRateDao taxRateDao;
	@Autowired
	private CreditRatingDao creditRatingDao;
	@Autowired
	private OrderCustCloningDao orderCustCloningDao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private OrderRnaDao orderRnaDao;
	@Autowired
	private OrderPlasmidPreparationDao orderPlasmidPreparationDao;
	@Autowired
	private OrderOrfCloneDao orderOrfCloneDao;
	@Autowired
	private OrderPeptideDao orderPeptideDao;
	@Autowired
	private OrderAntibodyDao orderAntibodyDao;
	@Autowired
	private OrderProteinDao orderProteinDao;
	@Autowired
	private OrderPkgServiceDao orderPkgServiceDao;
	@Autowired
	private OrderMutagenesisDao orderMutagenesisDao;
	@Autowired
	private OrderMutationLibrariesDao orderMutationLibrariesDao;
	@Autowired
	private OrderSirnaAndMirnaDao orderSirnaAndMirnaDao;
	@Autowired
	private OrderOligoDao orderOligoDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceShipConditionDao serviceShipConditionDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private PurchaseOrderDao purchaseOrderDao;
	@Autowired
	private VendorDao vendorDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private CustInfoStatDao custInfoStatDao;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private SrchOrderTemplateItemBeanDao srchOrderTemplateItemBeanDao;
	@Autowired
	private SrchServiceOrderTemplateBeanDao srchServiceOrderTemplateBeanDao;
	@Autowired
	private MailTemplatesDao mailTemplatesDao;
	@Autowired
	private PreQuotationDao preQuotationDao;
	@Autowired
	private CustomerNoteDao customerNoteDao;
	@Autowired
	private NoteDocumentDao custNoteDocumentDao;
	@Autowired
	private OligoBackbonesDao oligoBackbonesDao;
	@Autowired
	private OligoModificationsDao oligoModificationsDao;
	@Autowired
	private OligoChimericBasesDao oligoChimericBasesDao;
	@Autowired
	private OligoMixedBasesDao oligoMixedBasesDao;
	@Autowired
	private ShipmentLinesDao shipmentLinesDao;
	@Autowired
	private ShipmentsDao shipmentDao;
	@Autowired
	private ShipClerkAssignDao shipClerkAssignDao;
	@Autowired
	private SalesProjectManagerAssignmentDao salesProjectManagerAssignmentDao;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private BioInfoService bioInfoService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private OrderErpMappingDao orderErpMappingDao;
	@Autowired
	private MfgOrderDao mfgOrderDao;
	@Autowired
	private MfgOrderItemDao mfgOrderItemDao;
	@Autowired
	private BillTerritoryDao billTerritoryDao;
	@Autowired
	private UpdateRequestLogDao updateRequestLogDao;
	@Autowired
	private OrderDnaSequencingDao orderDnaSequencingDao;
	@Autowired
	private ShippingClerkShipmentAdjustmentDao shippingClerkShipmentAdjustmentDao;
	@Autowired
	private OrderDsSeqDao orderDsSeqDao;
	@Autowired
	private OrderDsLabelsDao orderDsLabelsDao;
	@Autowired
	private OrderEmailService orderEmailService;
	@Autowired
	private OrderDsPlatesDao orderDsPlatesDao;
	@Autowired
	private ShippingChargeLogDao shippingChargeLogDao;
	@Autowired
	private ChargerDao chargerDao;
	@Autowired
	private Refseq2orfpriceDao refseq2orfpriceDao;

	private static Logger logger = LoggerFactory.getLogger(OrderService.class);

	private ApplicationContext context;
	private boolean eventFlag;
	private final static String FullLength = "Full Length";
	private final static String ORFSequence = "ORF Sequence";

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;

	}

	public OrderMainBeanDtoDao getOrderMainBeanDtoDao() {
		return orderMainBeanDtoDao;
	}

	public void setOrderMainBeanDtoDao(OrderMainBeanDtoDao orderMainBeanDtoDao) {
		this.orderMainBeanDtoDao = orderMainBeanDtoDao;
	}

	// ---add by zhougang --

	/*
	 * 
	 * zhou gang
	 * 
	 * @param page
	 * 
	 * @param custNo
	 * 
	 * @return
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true)
	public Page<SampleRequestDTO> getSampleRequestDTOPage(
			Page<SampleRequestDTO> page, Integer custNo) {

		page = this.orderDao.getAllSampleResquestList(page, custNo);

		System.out.println(page.getTotalCount());

		List<SampleRequestDTO> sampleRequestDTOList = new ArrayList<SampleRequestDTO>();
		List list = page.getResult();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				SampleRequestDTO sampleRequestDTO = new SampleRequestDTO();
				if (!"".equals(obj[0]) && obj[0] != null) {
					sampleRequestDTO.setSeqNo(Integer.parseInt(obj[0]
							.toString()));
				}
				if (!"".equals(obj[6]) && obj[6] != null) {
					sampleRequestDTO.setDeliveryDate(obj[6].toString());
				}
				if (!"".equals(obj[2]) && obj[2] != null) {
					sampleRequestDTO.setRequestDate(obj[2].toString());
				}

				if (!"".equals(obj[1]) && obj[1] != null) {
					sampleRequestDTO.setSampleName(obj[1].toString());
				}
				if (!"".equals(obj[7]) && obj[7] != null) { // product_id -->url
					System.out.println(obj[7]);
					sampleRequestDTO.setUrl(getUrlByProductId(Integer
							.parseInt(obj[7].toString())));
				}
				if (!"".equals(obj[3]) && obj[3] != null) {
					sampleRequestDTO.setNote(obj[3].toString());
				}
				sampleRequestDTOList.add(sampleRequestDTO);
			}
		}
		page.setResult(null);
		Page<SampleRequestDTO> pagesampleRequestDTO = dozer.map(page,
				Page.class);
		pagesampleRequestDTO.setResult(sampleRequestDTOList);
		return pagesampleRequestDTO;
	}

	@Autowired
	private ProductExtendedInfoDao productExtendedInfoDao;

	/*
	 * zskang * getUrlByProductId
	 * 
	 * @param pId
	 * 
	 * @return
	 */

	@Transactional(readOnly = true)
	public String getUrlByProductId(Integer pId) {
		String url = "";
		ProductExtendedInfo productExtendedInfo = this.productExtendedInfoDao
				.getUrlByProductId(pId);
		if (productExtendedInfo != null) {
			url = productExtendedInfo.getUrl();
		}
		return url;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderMainBeanDao getOrderMainBeanDao() {
		return orderMainBeanDao;
	}

	public void setOrderMainBeanDao(OrderMainBeanDao orderMainBeanDao) {
		this.orderMainBeanDao = orderMainBeanDao;
	}

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public OrderMain getOrderMain(Integer cust_no) {
		return this.orderDao.getOrderMain(cust_no);
	}

	public String getStatusName(String code) {
		return this.orderDao.getStatusBycode(code).getName();
	}

	public String getEnteredbyName(Integer enteredby) {
		return userDao.getLoginName(enteredby);
	}

	public String getSoldbyName(Integer soldby) {
		return userDao.getLoginName(soldby);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public Page<OrderMainBeanDTO> searchOrder_new(final Integer custNo,
			final Page<OrderMainBean> page, final List<PropertyFilter> filters) {
		Page<OrderMainBeanDTO> orderMainBeanPage = new Page<OrderMainBeanDTO>();
		Page<OrderMainBean> page1 = orderMainBeanDao.findPage(page, filters);

		List newBean = new ArrayList();
		List ls = page1.getResult();
		for (int i = 0; i < ls.size(); i++) {
			OrderMainBeanDTO orderMainBeanDTO = null;
			OrderMainBean orderMainBean = (OrderMainBean) ls.get(i);
			orderMainBeanDTO = this.dozer.map(orderMainBean,
					OrderMainBeanDTO.class);
			Integer orderno = orderMainBean.getOrderNo();
			Integer qty = orderItemDao.getOrderItemsCountByOrderNo(orderno);
			orderMainBeanDTO.setQty(qty);
			newBean.add(orderMainBeanDTO);
		}
		orderMainBeanPage.setResult(newBean);
		return orderMainBeanPage;
	}

	// -

	@Transactional(readOnly = true)
	public Page<OrderMainBean> searchOrder(final Page<OrderMainBean> page,
			final List<PropertyFilter> filters) {
		Page<OrderMainBean> pageBean = orderMainBeanDao.findPage(page, filters);
		return getPageOrderMainBeanAndNjSo(pageBean);
	}

	@Transactional(readOnly = true)
	public Page<OrderMainBean> searchOrder(final Page<OrderMainBean> page,
			List<PropertyFilter> filters, Set<Integer> orderNoSet)
			throws Exception {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		// 判断当前用户是否含有内部订单管理员角色，是，则查询customer No=5的Order
		boolean internalOrderManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_INTERNAL_ORDER_MANAGER);
		if (filters == null) {
			filters = new ArrayList<PropertyFilter>();
		}
		Integer userId = (Integer) ActionContext.getContext().getSession()
				.get(StrutsActionContant.USER_ID);
		List<Integer> orderNoList = orderMainBeanDao
				.getInternalOrderNoByUserId(userId);
		if (internalOrderManager && !Constants.USERNAME_ADMIN.equals(userName)) {
			if (orderNoList != null && !orderNoList.isEmpty()) {
				criterionList.add(Restrictions.in("orderNo", orderNoList));
			} else {
				criterionList.add(Restrictions.isNull("orderNo"));
			}
		} else if (!Constants.USERNAME_ADMIN.equals(userName)) {
			filters.add(new PropertyFilter("NES_custType",
					Constants.INTERNAL_TYPE_CUSTOMER));
		}
		// 判断当前用户是否含有销售经理角色
		boolean salesManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);
		boolean pmo = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_PMO);
		if (Constants.USERNAME_ADMIN.equals(userName) || salesManager
				|| internalOrderManager || pmo) {
			Page<OrderMainBean> pageBean = orderMainBeanDao.advSearchOrder(
					page, filters, orderNoSet, criterionList);
			return pageBean;
		}
		Map<String, Object> salesRoleAndUserIdsMap = salesRepDao
				.getSameGroupUser();
		if (salesRoleAndUserIdsMap.get("function") != null
				&& salesRoleAndUserIdsMap.get("User_Ids") != null) {
			String salesRole = salesRoleAndUserIdsMap.get("function")
					.toString();
			Integer[] userIdsArr = (Integer[]) salesRoleAndUserIdsMap
					.get("User_Ids");
			if (SalesRepSalesRole.SALES_CONTACT.value().equals(salesRole)) {
				Criterion criterionSalesContact = Restrictions.in(
						"salesContactId", userIdsArr);
				Criterion altCriterionSalesContact = Restrictions.in(
						"altSalesContactId", userIdsArr);
				Criterion allCriterionSalesContact = Restrictions.or(
						criterionSalesContact, altCriterionSalesContact);
				criterionList.add(allCriterionSalesContact);
			} else if (SalesRepSalesRole.TECH_SUPPORT.value().equals(salesRole)) {
				Criterion criterionSalesContact = Restrictions.in(
						"techSupportId", userIdsArr);
				Criterion altCriterionSalesContact = Restrictions.in(
						"altTechSupportId", userIdsArr);
				Criterion allCriterionSalesContact = Restrictions.or(
						criterionSalesContact, altCriterionSalesContact);
				criterionList.add(allCriterionSalesContact);
			} else if (SalesRepSalesRole.PROJECT_SUPPORT.value().equals(
					salesRole)) {
				Criterion criterionSalesContact = Restrictions.in(
						"projectSupportId", userIdsArr);
				Criterion altCriterionSalesContact = Restrictions.in(
						"altProjectSupportId", userIdsArr);
				Criterion allCriterionSalesContact = Restrictions.or(
						criterionSalesContact, altCriterionSalesContact);
				criterionList.add(allCriterionSalesContact);
			}
			if (criterionList != null && criterionList.size() > 0) {
				Page<OrderMainBean> pageBean = orderMainBeanDao.advSearchOrder(
						page, filters, orderNoSet, criterionList);
				return pageBean;
			}
		}
		return null;
	}

	private Page<OrderMainBean> getPageOrderMainBeanAndNjSo(
			Page<OrderMainBean> pageBean) {
		List<OrderMainBean> list = pageBean.getResult();
		List<OrderMainBean> retList = new LinkedList<OrderMainBean>();
		if (list != null && list.size() > 0) {
			for (OrderMainBean bean : list) {
				Integer srcOrderNo = bean.getOrderNo();
				OrderErpMapping orderErpMapping = orderErpMappingDao
						.getById(srcOrderNo);
				if (orderErpMapping != null
						&& orderErpMapping.getErpUsPo() != null) {
					bean.setErpNjSo(orderErpMapping.getErpNjSo());
				}
				retList.add(bean);
			}
			pageBean.setResult(retList);
		}
		return pageBean;
	}

	@Transactional(readOnly = true)
	public Page<OrderMain> searchOrderMain(final Page<OrderMain> page,
			final List<PropertyFilter> filters) {
		return orderDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public Page<OrderMainBean> searchOrder(final Page<OrderMainBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return orderMainBeanDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<OrderMainBean> searchOrder(final Page<OrderMainBean> page) {
		return orderMainBeanDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public OrderMain getOrder(Integer orderNo) {
		System.out.println(orderNo);
		return orderDao.getById(orderNo);
	}

	@Transactional(readOnly = true)
	public OrderMainDTO getOrderDetail(final Integer orderNo) {
		OrderMainDTO dto = null;
		try {
			OrderMain order = orderDao.getById(orderNo);
			if (order == null) {
				return null;
			}
			dto = this.dozer.map(order, OrderMainDTO.class);
			dto.setDbStatus(order.getStatus());
			// List<OrderItem> dbItemList =
			// orderItemDao.getOrderAllItemList(orderNo);
			List<OrderItem> dbItemList = orderItemDao
					.getOrderAllItemDataList(orderNo);
			List<OrderItemDTO> itemDTOList = this
					.getItemList(order, dbItemList);
			// modify by zhanghuibin 优化，只取得第一个item的 ItemMoreDetail ,
			if (itemDTOList != null && itemDTOList.size() > 0) {
				OrderItemDTO itemDTO = itemDTOList.get(0);
				this.getItemMoreDetail(itemDTOList, itemDTO,
						itemDTO.getOrderItemId());
				// 如果item中还有子服务，同时加载子服务
				/*
				 * for (OrderItemDTO itemDTO : itemDTOList) {
				 * this.getItemMoreDetail(itemDTO, itemDTO.getOrderItemId()); }
				 */
				// setItemAllChildMoreDetail(itemDTOList, itemDTO);
				dto.setItemList(itemDTOList);
			}
			Customer customer = customerDao.get(order.getCustNo());
			dto.setCustAltNo(customer.getAltNo());
			dto.setCustPrefShipMethod(customer.getPrefShipMthd());
			dto.setCustType(customer.getCustType());
			// Get Promotion
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo",
					orderNo);
			filterList.add(quoteFilter);
			List<OrderPromotion> orderPromList = this.orderPromotionDao
					.find(filterList);
			if (orderPromList != null && !orderPromList.isEmpty()) {
				OrderPromotion orderPromotion = orderPromList.get(0);
				OrderPromotionDTO orderPromotionDTO = this.dozer.map(
						orderPromotion, OrderPromotionDTO.class);
				dto.setOrderPromotion(orderPromotionDTO);
				dto.setPromotionId(orderPromotion.getId());
			}
			// Get couponCode
			String couponIds = dto.getCouponId();
			String couponCodes = "";
			if (StringUtils.isNotBlank(couponIds)) {
				List<Coupon> couponList = this.couponDao
						.findCouponByIds(couponIds);
				for (Coupon coupon : couponList) {
					if ("".equals(couponCodes)) {
						couponCodes = coupon.getCode();
					} else {
						couponCodes = couponCodes + "," + coupon.getCode();
					}
				}
			}
			dto.setCouponCode(couponCodes);
			 Integer salesContact=0;
			 Integer techSupport=0;
			 String salesManagerName="";
			 String techManagerName="";
			 Integer custNo=0;
			 CustomerDTO	customerDetail=null;
			if(order!=null && !"".equals(order))
			{
				custNo=order.getCustNo(); 
			}
		    if(custNo!=null&&!"".equals(custNo)){
			 	customerDetail = customerService.getCustomerDetail(custNo);
		      }
			if (customerDetail != null) {
				salesContact = customerDetail.getSalesContact();
				techSupport = customerDetail.getTechSupport();
			} 
		   if (salesContact == null || techSupport == null) {
			  if(custNo!=null && !"".equals(custNo)){ 
			  Map<String, List<SalesRep>> salesMap = publicService.searchManager_Cust_Contact("customer", String.valueOf(custNo));
			  if (salesContact == null && salesMap != null && salesMap.get("salesManager") != null && salesMap.get("salesManager").size() > 0) {
	                salesContact = salesMap.get("salesManager").get(0).getSalesId();
	                if(salesContact!=null  && !"".equals(salesContact)){
		            	salesManagerName=salesRepDao.getById(salesContact).getResourceName();
		            	if(salesManagerName!=null&&!"".equals(salesManagerName)){
		            		dto.setSalesContactUser(salesManagerName);
		            	}
		            }
	            }
	            if (techSupport == null && salesMap != null && salesMap.get("techAcountManager") != null && salesMap.get("techAcountManager").size() > 0) {
	                techSupport = salesMap.get("techAcountManager").get(0).getSalesId();
	                if(techSupport!=null && !"".equals(techSupport)){
		            	techManagerName=salesRepDao.getById(techSupport).getResourceName();
		            	if(techManagerName!=null&&!"".equals(techManagerName)){
		            		dto.setTechSupportUser(techManagerName);
		            	}
		            }
	            }
			}
			 } else{
				  if(salesContact!=null  && !"".equals(salesContact)){
		            	salesManagerName=salesRepDao.getById(salesContact).getResourceName();
		            	if(salesManagerName!=null&&!"".equals(salesManagerName)){
		            		dto.setSalesContactUser(salesManagerName);
		            	}
		            }
				    if(techSupport!=null && !"".equals(techSupport)){
		            	techManagerName=salesRepDao.getById(techSupport).getResourceName();
		            	if(techManagerName!=null&&!"".equals(techManagerName)){
		            		dto.setTechSupportUser(techManagerName);
		            	}
		            }
			 }
		 
				 
			/*if (order.getSalesContact() != null) {
				User salesContact = this.userDao.getById(order
						.getSalesContact());
				if (salesContact != null && salesContact.getEmployee() != null) {
					dto.setSalesContactUser(salesContact.getEmployee()
							.getEmployeeName());
				}
			}
			if (order.getTechSupport() != null) {
				User techSupport = this.userDao.getById(order.getTechSupport());
				if (techSupport != null && techSupport.getEmployee() != null) {
					dto.setTechSupportUser(techSupport.getEmployee()
							.getEmployeeName());
				}
			}*/
			dto.setStatusText(this.getOrderStatusText(order.getStatus()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * For getOrderDetail
	 * 
	 * @param strStatus
	 * @return
	 */
	@Transactional(readOnly = true)
	private String getOrderStatusText(String strStatus) {
		String text = null;
		if (strStatus == null || strStatus.trim().length() < 1) {
			return null;
		}
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filterType = new PropertyFilter("EQS_type",
				OrderQuoteStautsType.ORDER.value());
		filterList.add(filterType);
		PropertyFilter filterCode = new PropertyFilter("EQS_code", strStatus);
		filterList.add(filterCode);
		List<StatusList> statusList = this.statusListDao.find(filterList);
		if (statusList != null && !statusList.isEmpty()) {
			text = statusList.get(0).getName();
		}
		return text;
	}

	/**
	 * 根据OrderItem Status的缩写获得该OrderItem status的全称.
	 * 
	 * @param strStatus
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getOrderItemStatusText(String strStatus) {
		String text = null;
		if (strStatus == null || strStatus.trim().length() < 1) {
			return null;
		}
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filterType = new PropertyFilter("EQS_type",
				OrderQuoteStautsType.ORDERITEM.value());
		filterList.add(filterType);
		PropertyFilter filterCode = new PropertyFilter("EQS_code", strStatus);
		filterList.add(filterCode);
		List<StatusList> statusList = this.statusListDao.find(filterList);
		if (statusList != null && !statusList.isEmpty()) {
			text = statusList.get(0).getName();
		}
		return text;
	}

	/**
	 * 获得一个Order 的Item list(状态不为RT)单独为一个WS接口提供数据服务.
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderItemDTO> getOrderUnRTItemList(Integer orderNo) {
		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		List<OrderItem> itemList = orderItemDao.getOrderUnRTItemList(orderNo);
		if (itemList != null) {
			for (OrderItem item : itemList) {
				OrderItemDTO dto = this.dozer.map(item, OrderItemDTO.class);
				dto.setShippedQty(1);// TODO 测试用.
				dto.setShipSize(BigDecimal.valueOf(32.65d));// TODO 测试用.
				dto.setShipUom(item.getSizeUom());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 获得一个Order的itemList.
	 * 
	 * @param order
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	protected List<OrderItemDTO> getItemList(OrderMain order, List<OrderItem> itemList) {
		List<OrderItemDTO> dtoList = new ArrayList<OrderItemDTO>();
		PbCurrency pbCurrency = this.currencyDao.getByCurrencyCode(order.getOrderCurrency());
		String currencySymbolText = null;
		Integer precision = null;
		if (pbCurrency != null) {
			currencySymbolText = pbCurrency.getSymbol();
			precision = pbCurrency.getPrecision();
		}
		@SuppressWarnings("rawtypes")
		HashMap noMap = new HashMap();
		StringBuffer itemIds = new StringBuffer("");
		if (itemList != null) {
			Map<Integer, OrderAddress> addrMap = new HashMap<Integer, OrderAddress>();
			for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
				OrderItem item = itemList.get(itemIndex);
				// modify by zhanghuibin 优化处理，只去加载第一个item
				this.orderItemDao.getSession().evict(item);
				OrderItemDTO dto = this.dozer.map(item, OrderItemDTO.class);
				dto.setCurrencyCode(order.getOrderCurrency());
				dto = this.dozer.map(dto, OrderItemDTO.class);
				noMap.put(dto.getOrderNo(), "Y");
				if (SoapUtil.getIntegerFromSOAP(item.getShiptoAddrId()) != null) {
					OrderAddress shipToAddr = addrMap.get(item.getShiptoAddrId());
					if (shipToAddr == null) {
						shipToAddr = this.orderAddressDao.getById(item.getShiptoAddrId());
						addrMap.put(item.getShiptoAddrId(), shipToAddr);
					}
					dto.setShipToAddress(shipToAddr);
				}
				// 设置页面中显示用的otherInfo，初始化时只加载第一条的OtherInfo，其他的在点击时再加载，减少进入Order的时间
				if (itemIndex == 0) {
					dto.setOtherInfo(getItemOtherInfo(item.getType(), item.getStatus(), item.getCatalogNo(), item.getStatusReason(), null, order.getCustNo()));
				} else {
					dto.setOtherInfo("");
				}
				// 对于clsId = 3的特殊处理
				if (dto.getClsId() == 3 && StringUtils.isBlank(dto.getParentId())) {
					itemIds.append("," + dto.getOrderItemId());
				}
				if (itemIndex != 0 && noMap.get(dto.getParentId()) == null) {
					dto.setUpSymbol(currencySymbolText);
					dto.setUpPrecision(precision);
					dtoList.add(dto);
					continue;
				}
				// 设置页面显示用的catalog
				/*
				 * List<PropertyFilter> filterList = new
				 * ArrayList<PropertyFilter>(); PropertyFilter quoteFilter = new
				 * PropertyFilter("EQS_catalogId", item.getCatalogId());
				 * filterList.add(quoteFilter); List<Catalog> catalogList =
				 * this.catalogDao.find(filterList); if (catalogList != null &&
				 * !catalogList.isEmpty()) { Catalog temp = catalogList.get(0);
				 * dto.setCatalogText(temp.getCatalogId() + " : " +
				 * temp.getCatalogName()); }
				 */
				// 设置页面中显示用的type
				/*
				 * if (item.getType().equals(QuoteItemType.PRODUCT.value())) {
				 * ProductClass pdtClass =
				 * this.productClassDao.getById(item.getClsId()); if (pdtClass
				 * != null) { dto.setTypeText(item.getType() + " - " +
				 * pdtClass.getName()); } } else { ServiceClass servClass =
				 * this.serviceClassDao.getById(item.getClsId()); if (servClass
				 * != null) { dto.setTypeText(item.getType() + " - " +
				 * servClass.getName()); } }
				 */
				// 设置页面中显示用的status
				// dto.setStatusText(this.getOrderItemStatusText(item.getStatus()));
				dto.setUpSymbol(currencySymbolText);
				dto.setUpPrecision(precision);
				// 查找shipDate
				/*
				 * ShipmentLine shipmentLine =
				 * this.shipmentLineDao.getShipmentLine(item.getOrderNo(),
				 * item.getItemNo()); if (shipmentLine != null) {
				 * dto.setShipDate(shipmentLine.getShipDate()); }
				 */
				dtoList.add(dto);
			}
			// 取得需要特殊处理的clsId=3的item，取得gene并更新item
			if (!"".equals(itemIds.toString())) {
				String ids = itemIds.toString().substring(1, itemIds.length());
				DocumentType[] docTypeList = { DocumentType.OIM_GENE };
				Map<Integer, List<Document>> docMapList = orderQuoteUtil.getBatchItemDocList(ids, docTypeList);
				Map<Integer, OrderGeneSynthesisDTO> geneDTOMap = new HashMap<Integer, OrderGeneSynthesisDTO>();
				List<OrderGeneSynthesis> geneSynthesisList = orderGeneSynthesisDao.getBatchOrderGeneByIds(ids);
				for (OrderGeneSynthesis geneTemp : geneSynthesisList) {
					geneDTOMap.put(geneTemp.getOrderItemId(), dozer.map(geneTemp, OrderGeneSynthesisDTO.class));
				}
				// 更新item
				for (OrderItemDTO itemDto : dtoList) {
					OrderGeneSynthesisDTO geneSynthesisDto = geneDTOMap.get(itemDto.getOrderItemId());
					if (geneSynthesisDto != null) {
						geneSynthesisDto.setDocumentList(docMapList.get(itemDto.getOrderItemId()));
						itemDto.setGeneSynthesis(geneSynthesisDto);
						itemDto.setVirusSeqFlag(geneSynthesisDto.getVirusSeqFlag());
					}
				}
			}
		}
		return dtoList;
	}

	/**
	 * 获得OrderItem的otherInfo，如果customerCompany和custNo都为空，则查customerCompany=GSUS美国库存
	 * modify by Zhang Yong
	 * modify date 2011-12-17
	 * @param type
	 * @param status
	 * @param catalogNo
	 * @param customerCompany
	 * @param statusReason
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getItemOtherInfo(String type, String status, String catalogNo, String statusReason, String customerCompany, Integer custNo) {
		String ret = "";
		if (QuoteItemType.PRODUCT.value().equals(type)) {
			if (OrderItemStatusType.CN.value().equals(status) || OrderItemStatusType.OH.value().equals(status)) {
				ret = statusReason;
			} else {
				Integer count = null;
				try {
					if (StringUtils.isBlank(customerCompany)) {
						customerCompany = customerDao.getCustomerCompany(custNo, null);
					}
					count = this.getItemStockQty(catalogNo, customerCompany);
				} catch (Exception ex) {
					count = 0;
				}
				if (count != null && count.intValue() > 0) {
					ret = "In Stock," + count + " Available";// 库存信息.
				} else {
					ret = "Not in stock";
				}
			}
		}
		return ret;
	}

	/**
	 * 在编辑Order时， 当新增一个OrderItem时， 获得其Status. in: storageId, needCount,
	 * catalogNo; out: status(BO, PB, CM) processed: 库存数为X; 1. X==0或不存在返回BO; 2.
	 * 0< X <needCount, 返回PB; 3. X >= needCount, 返回CM.
	 * 
	 * @param needCount
	 *            OrderItem该产品需要的数量
	 * @param catalogNo
	 *            Product's catalogNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public ItemOtherInfoForNewDTO getItemOtherInfoForNew(Integer needCount,
			String catalogNo, QuoteItemType itemType, String customerCompany) {
		ItemOtherInfoForNewDTO dto = new ItemOtherInfoForNewDTO();
		OrderItemStatusType itemStatus = null;
		Integer count = null;
		try {
			count = QuoteItemType.PRODUCT.value().equalsIgnoreCase(
					itemType.toString()) ? this.getItemStockQty(catalogNo,
					customerCompany) : null;
		} catch (Exception ex) {
			count = 0;
		}
		String otherInfo = "";
		if (!QuoteItemType.PRODUCT.value()
				.equalsIgnoreCase(itemType.toString())) {
			// 如果不是product,则默认有库存。
			itemStatus = OrderItemStatusType.CM;
		} else if (count == null || count.intValue() <= 0
				|| count.intValue() < needCount) {
			itemStatus = OrderItemStatusType.BO;
			otherInfo = "Not in stock";
		} else {
			itemStatus = OrderItemStatusType.CM;
			otherInfo = "In Stock," + count + " Available";// 库存信息.
		}
		String itemStatusText = this.getOrderItemStatusText(itemStatus.value());
		dto.setOtherInfo(otherInfo);
		dto.setItemStatus(itemStatus.value());
		dto.setItemStatusText(itemStatusText);
		return dto;
	}

	/**
	 * Update list 1. 2011-01-10 wangsf 计算产品或服务的库存改为从StockDetail中取
	 * 
	 */
	/**
	 * 获得某个产品在storage中的库存信息. modify by Zhang Yong modify date 2011-11-04
	 * 
	 * @param catalogNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getItemStockQty(String catalogNo, String customerCompany) {
		BigDecimal unitInStock = erpSalesOrderService.getPartStorageNumber(
				catalogNo, customerCompany);
		return unitInStock != null ? unitInStock.intValue() : null;
	}

	/**
	 * 获得某个用户目前已保存的MyTemplate的数量
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Integer getMyTemplateCount(Integer userId) {
		Integer count = 0;
		Long retCount = this.orderTemplateDao.getMyTemplateCount(userId);
		if (retCount != null) {
			count = retCount.intValue();
		}
		return count;
	}

	public void delOrder(Integer orderNo, String statusReason, Integer userId,
			String comment, OrderMainDTO order) {
		this.reserveQty(order, userId);
		orderDao.delOrder(orderNo, statusReason, userId, comment);
		OrderProcessLog lastConfirmLog = orderProcessLogDao
				.getOrderLastConfirm(order.getOrderNo());
		if (lastConfirmLog != null) {
			OrderMain orderMain = dozer.map(order, OrderMain.class);
			ApplicationEvent cancelEvt = new CancelOrderEvent(this, orderMain);
			context.publishEvent(cancelEvt);
		}
	}

	@Transactional(readOnly = true)
	public List<ProcessLogDTO> getItemStatusHist(Integer orderItemId) {
		return orderProcessLogDao.getItemStatusHist(orderItemId);
	}

	@Transactional(readOnly = true)
	public List<ProcessLogDTO> getOrderStatusHist(Integer orderNo) {
		List<ProcessLogDTO> processLogList = orderProcessLogDao
				.getOrderStatusHist(orderNo);
		for (ProcessLogDTO processLogDTO : processLogList) {
			StatusList statusList = this.statusListDao.findByTypeAndCode("O",
					processLogDTO.getPriorStat());
			processLogDTO.setPriorStatName(statusList != null ? statusList
					.getName() : null);
			statusList = this.statusListDao.findByTypeAndCode("O",
					processLogDTO.getCurrentStat());
			processLogDTO.setCurrentStatName(statusList != null ? statusList
					.getName() : null);
		}
		return processLogList;
	}

	/**
	 * Calculate the price of more than one order relation item
	 * 
	 * @param mainProductId
	 * @param productId
	 * @param orderNo
	 * @param catalogNo
	 * @param custNo
	 * @param quantity
	 * @param amount
	 * @param orderDate
	 * @return the array list of ProductItemPriceDTO
	 */
	@Transactional(readOnly = true)
	public ProductItemPriceDTO calculateOrderRelationItemPrice(
			Integer mainProductId, Integer productId, Integer orderNo,
			String catalogNo, Integer custNo, Integer quantity,
			BigDecimal amount, Date orderDate) {
		return relationProductPriceBeanDao.calculateOrderRelationItemPrice(
				mainProductId, productId, orderNo, catalogNo, custNo, quantity,
				amount, orderDate);
	}

	@Transactional(readOnly = true)
	public OrderAddressDTO getAddress(Integer orderNo) {
		OrderAddressDTO addr = new OrderAddressDTO();
		OrderMain order = this.orderDao.getById(orderNo);
		if (order.getBilltoAddrId() != null
				&& order.getBilltoAddrId().intValue() > 0) {
			OrderAddress billToAddr = this.orderAddressDao.getById(order
					.getBilltoAddrId());
			if (billToAddr != null) {
				/*
				 * Organization billOrg = billToAddr.getOrganization();
				 * Organization newBillOrg = new Organization();
				 * newBillOrg.setOrgId(billOrg.getOrgId());
				 * billToAddr.setOrganization(newBillOrg);
				 */
				addr.setBillToAddr(billToAddr);
			}
		}
		if (order.getShiptoAddrId() != null
				&& order.getShiptoAddrId().intValue() > 0) {
			OrderAddress shipToAddr = this.orderAddressDao.getById(order
					.getShiptoAddrId());
			if (shipToAddr != null) {
				/*
				 * Organization shipToOrg = shipToAddr.getOrganization();
				 * Organization newShipToOrg = new Organization();
				 * newShipToOrg.setOrgId(shipToOrg.getOrgId());
				 * shipToAddr.setOrganization(newShipToOrg);
				 */
				addr.setShipToAddr(shipToAddr);
			}
		}
		if (order.getSoldtoAddrId() != null
				&& order.getSoldtoAddrId().intValue() > 0) {
			OrderAddress soldToAddr = this.orderAddressDao.getById(order
					.getSoldtoAddrId());
			if (soldToAddr != null) {
				/*
				 * Organization soldToOrg = soldToAddr.getOrganization();
				 * Organization newSoldToOrg = new Organization();
				 * newSoldToOrg.setOrgId(soldToOrg.getOrgId());
				 * soldToAddr.setOrganization(newSoldToOrg);
				 */
				addr.setSoldToAddr(soldToAddr);
			}
		}
		return addr;
	}

	@Transactional(readOnly = true)
	public List<OrderAddress> getAddressListByType(Integer orderNo,
			AddressType addrType) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo", orderNo);
		filterList.add(quoteFilter);
		PropertyFilter typefilter = new PropertyFilter("EQS_addrType",
				addrType.value());
		filterList.add(typefilter);
		List<OrderAddress> addrList = this.orderAddressDao.find(filterList);
		return addrList;
	}

	/**
	 * 获得具体的OrderMail或OrderNote
	 * 
	 * @param id
	 *            OrderMail的主健或OrderNote的主健
	 * @param instructionType
	 *            具体类型， 不能为空.
	 * @return
	 */
	public InstructionDTO getInstructionDTO(Integer id, String instructionType) {
		InstructionDTO dto = null;
		if (instructionType.equals(OrderInstructionType.CUST_CONFIRM_EMAIL
				.value())
				|| instructionType
						.equals(OrderInstructionType.VENDOR_CONFIRM_EMAIL
								.value())) {
			OrderMail mail = this.orderMailDao.get(id);
			Date instDate = null;
			dto = dozer.map(mail, InstructionDTO.class);
			if (mail.getSendDate() != null) {
				instDate = mail.getSendDate();
			} else if (mail.getScheduleDate() != null) {
				instDate = mail.getScheduleDate();
			}
			dto.setDescription(mail.getContent());
			dto.setInstructionDate(instDate);
			User createUser = this.userDao.getById(mail.getCreatedBy());
			if (createUser != null) {
				dto.setCreateUser(createUser.getLoginName());
			}
			List<Document> docList = this.documentDao.getDocumentList(
					mail.getId(), DocumentType.ORDER_INST_MAIL);
			dto.setDocumentList(docList);

		} else {
			OrderNote note = this.orderNoteDao.get(id);
			dto = dozer.map(note, InstructionDTO.class);
			dto.setInstructionDate(note.getNoteDate());
			User createUser = this.userDao.getById(note.getCreatedBy());
			if (createUser != null) {
				dto.setCreateUser(createUser.getLoginName());
			}
			List<Document> docList = this.documentDao.getDocumentList(
					note.getId(), DocumentType.ORDER_INST_NOTE);
			dto.setDocumentList(docList);
		}
		return dto;
	}

	/**
	 * 获得某Order的InstuctionList, it contains OrderMails and OrderNotes.
	 * 
	 * @param orderNo
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<InstructionDTO> getInstructionList(Integer orderNo,
			OrderInstructionType type) {
		List<InstructionDTO> dtoList = new ArrayList<InstructionDTO>();
		boolean bEmail = false;
		if (type == null
				|| type.equals(OrderInstructionType.CUST_CONFIRM_EMAIL)
				|| type.equals(OrderInstructionType.VENDOR_CONFIRM_EMAIL)) {
			if (type == null) {
				bEmail = false;
			} else {
				bEmail = true;
			}
			List<OrderMail> mailList = this.orderMailDao.getOrderMailList(
					orderNo, type);
			Date instDate = null;
			if (mailList != null) {
				for (OrderMail mail : mailList) {
					InstructionDTO dto = dozer.map(mail, InstructionDTO.class);
					if (mail.getSendDate() != null) {
						instDate = mail.getSendDate();
					} else if (mail.getScheduleDate() != null) {
						instDate = mail.getScheduleDate();
					}
					dto.setDescription(mail.getContent());
					dto.setInstructionDate(instDate);
					User createUser = this.userDao.getById(mail.getCreatedBy());
					if (createUser != null) {
						dto.setCreateUser(createUser.getLoginName());
					}
					List<Document> docList = this.documentDao.getDocumentList(
							mail.getId(), DocumentType.ORDER_INST_MAIL);
					dto.setDocumentList(docList);
					dtoList.add(dto);
				}
			}

		}
		if (!bEmail) {
			List<OrderNote> noteList = this.orderNoteDao.getOrderNoteList(
					orderNo, type);
			if (noteList != null) {
				for (OrderNote note : noteList) {
					InstructionDTO dto = dozer.map(note, InstructionDTO.class);
					dto.setInstructionDate(note.getNoteDate());
					User createUser = this.userDao.getById(note.getCreatedBy());
					if (createUser != null) {
						dto.setCreateUser(createUser.getLoginName());
					}
					List<Document> docList = this.documentDao.getDocumentList(
							note.getId(), DocumentType.ORDER_INST_NOTE);
					dto.setDocumentList(docList);
					dtoList.add(dto);
				}
			}
		}
		Collections.sort(dtoList, new BeanComparator("instructionDate"));
		return dtoList;
	}

	/**
	 * 通过orderNo查询对应Customer Note中数据
	 * 
	 * @param
	 * @param type
	 * @return
	 * @author Zhang Yong
	 */
	public List<InstructionDTO> getInstructionListInCustomer(Integer custNo,
			String sessOrderNo, String type) {
		List<InstructionDTO> dtoList = null;
		List<CustomerNote> customerNoteList = customerNoteDao.findByCustNoType(
				custNo, type);
		if (customerNoteList == null || customerNoteList.size() == 0) {
			return dtoList;
		}
		dtoList = new ArrayList<InstructionDTO>();
		for (CustomerNote customerNote : customerNoteList) {
			InstructionDTO dbDTO = new InstructionDTO();
			dbDTO.setId(customerNote.getId());
			dbDTO.setCustNoteId(OrderInstructionType.CUSTOMER.value() + "-"
					+ customerNote.getId());
			if (sessOrderNo != null && StringUtils.isNumeric(sessOrderNo)) {
				dbDTO.setOrderNo(Integer.parseInt(sessOrderNo));
			}
			dbDTO.setType(customerNote.getType());
			dbDTO.setDescription(customerNote.getDescription());
			dbDTO.setDocFlag(customerNote.getDocFlag());
			dbDTO.setNoteDate(customerNote.getCreationDate());
			dbDTO.setInstructionDate(customerNote.getCreationDate());
			dbDTO.setSource(OrderInstructionType.CUSTOMER.value());
			User createUser = this.userDao.getById(customerNote.getCreatedBy());
			if (createUser != null) {
				dbDTO.setCreateUser(createUser.getLoginName());
			}
			List<NoteDocument> docList = this.custNoteDocumentDao
					.getDocumentList(customerNote.getId(),
							DocumentType.CUSTOMER_NOTE_TYPE);
			dbDTO.setCustNoteDocumentList(docList);
			dtoList.add(dbDTO);
		}
		return dtoList;
	}

	/**
	 * 保存Order的同时保存新增或修改的PaymentVoucher, 删除 delIdList中包含的PaymentVoucher
	 * 
	 * @param order
	 * @param delIdList
	 * @param
	 */

	private void attachPayment(OrderMain order, List<Integer> delIdList,
			List<PaymentVoucherDTO> paymentList) {
		if (delIdList != null && !delIdList.isEmpty()) {
			this.paymentVoucherDao.delPaymentVoucherList(delIdList);
		}
		if (paymentList == null || paymentList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (PaymentVoucherDTO dto : paymentList) {
			PaymentVoucher payment = this.dozer.map(dto, PaymentVoucher.class);
			if (dto.getDocument() != null) {
				payment.setPoDocFlag("Y");
			}
			payment.setPoPaymentTerm(SoapUtil.getIntegerFromSOAP(payment
					.getPoPaymentTerm()));
			payment.setOrderNo(order.getOrderNo());
			payment.setCreationDate(now);
			payment.setModifyDate(now);
			payment.setCreatedBy(order.getModifiedBy());
			payment.setModifiedBy(order.getModifiedBy());
			this.paymentVoucherDao.save(payment);
			if (dto.getDelDocId() != null && dto.getDelDocId().intValue() != 0) {
				ArrayList<Integer> delDocIdList = new ArrayList<Integer>();
				delDocIdList.add(dto.getDelDocId());
				this.documentDao.delDocumentList(delDocIdList);
			}
			if (dto.getDocument() != null) {
				Document doc = dto.getDocument();
				doc.setCreatedBy(order.getModifiedBy());
				doc.setModifiedBy(order.getModifiedBy());
				doc.setCreationDate(now);
				doc.setModifyDate(now);
				doc.setRefId(payment.getVoucherId());
				doc.setRefType(DocumentType.ORDER_PO.value());
				this.documentDao.save(doc);
			}

		}
	}

	/**
	 * 保存Order的同时保存OrderMails and OrderNotes.及其关联的多个document.
	 */
	private List<Integer> attachInstruction(OrderMain order,
			List<InstructionDTO> dtoList, String dbAccountNo) {
		List<Integer> sendMailIdList = new ArrayList<Integer>();
		String strDateFormat = "yyyy-MM-dd";
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		if (dtoList == null || dtoList.isEmpty()) {
			// dbAccountNo与Order的shipAccount不同时添加一条SHIPMENT Note
			saveDifAccountNoNote(order, now, dbAccountNo);
			// 非CN状态的item中有shipMethod name不是FedEx的，默认添加一条SHIPMENT类型的Note
			saveUnFedExNote(order, now);
			return sendMailIdList;
		}
		for (InstructionDTO dto : dtoList) {
			OrderInstructionType type = OrderInstructionType.fromValue(dto
					.getType());
			if (type == null) {
				continue;
			}
			if (dto.getDelDocIdList() != null
					&& !dto.getDelDocIdList().isEmpty()) {
				this.documentDao.delDocumentList(dto.getDelDocIdList());
			}
			if (type.equals(OrderInstructionType.CUST_CONFIRM_EMAIL)
					|| type.equals(OrderInstructionType.VENDOR_CONFIRM_EMAIL)
					|| type.equals(OrderInstructionType.ORDER_CHANGE_NOTIFICATION)) {
				OrderMail mail = this.dozer.map(dto, OrderMail.class);
				if (mail.getId() != null && mail.getId().intValue() < 1) {
					mail.setId(null);
				}
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					mail.setDocFlag("Y");
				} else {
					mail.setDocFlag("N");
				}
				mail.setOrderNo(order.getOrderNo());
				mail.setCreatedBy(order.getModifiedBy());
				mail.setModifiedBy(order.getModifiedBy());
				mail.setCreationDate(now);
				mail.setModifyDate(now);
				/*
				 * System.out.println("-----===="+mail.getContent()); String
				 * content=mail.getContent(); content=content.replace("",
				 * "<br/>"); mail.setContent(content);
				 */
				this.orderMailDao.save(mail);
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					for (Document doc : dto.getDocumentList()) {
						doc.setCreatedBy(order.getModifiedBy());
						doc.setModifiedBy(order.getModifiedBy());
						doc.setCreationDate(now);
						doc.setModifyDate(now);
						doc.setRefId(mail.getId());
						doc.setRefType(DocumentType.ORDER_INST_MAIL.value());
						this.documentDao.save(doc);
					}
				}
				if (mail.getScheduleDate() != null
						&& dateFormat.format(mail.getScheduleDate()).equals(
								dateFormat.format(now))) {
					sendMailIdList.add(mail.getId());
					ApplicationEvent evt = new SendMailAtOnceEvent(this);
					context.publishEvent(evt);
				}
			} else {
				OrderNote note = this.dozer.map(dto, OrderNote.class);
				if (SoapUtil.getIntegerFromSOAP(note.getId()) == null) {
					note.setId(null);
					if (note.getNoteDate() == null) {
						note.setNoteDate(now);
					}
				}
				note.setOrderNo(order.getOrderNo());
				note.setCreatedBy(order.getModifiedBy());
				note.setModifiedBy(order.getModifiedBy());
				note.setCreationDate(now);
				note.setModifyDate(now);
				this.orderNoteDao.save(note);
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					for (Document doc : dto.getDocumentList()) {
						doc.setCreatedBy(order.getModifiedBy());
						doc.setModifiedBy(order.getModifiedBy());
						doc.setCreationDate(now);
						doc.setModifyDate(now);
						doc.setRefId(note.getId());
						doc.setRefType(DocumentType.ORDER_INST_NOTE.value());
						this.documentDao.save(doc);
					}
				}
			}
		}
		// dbAccountNo与Order的shipAccount不同时添加一条SHIPMENT Note
		saveDifAccountNoNote(order, now, dbAccountNo);
		// 非CN状态的item中有shipMethod name不是FedEx的，默认添加一条SHIPMENT类型的Note
		saveUnFedExNote(order, now);
		return sendMailIdList;
	}

	/**
	 * dbAccountNo与Order的shipAccount不同时添加一条SHIPMENT Note
	 * 
	 * @author Zhang Yong modify date 2011-11-04
	 * @param order
	 * @param now
	 */
	private void saveDifAccountNoNote(OrderMain order, Date now,
			String dbAccountNo) {
		String shipAccount = order.getShippingAccount();
		if (StringUtils.isNotBlank(shipAccount)
				&& (StringUtils.isBlank(dbAccountNo) || !shipAccount.trim()
						.equals(dbAccountNo.trim()))) {
			String description = "Account No:" + shipAccount.trim();
			boolean result = orderNoteDao.checkIsConteinNote(
					order.getOrderNo(), OrderInstructionType.SHIPMENT.value(),
					description);
			if (result) {
				return;
			}
			OrderNote note = new OrderNote();
			note.setNoteDate(now);
			note.setOrderNo(order.getOrderNo());
			note.setCreatedBy(order.getModifiedBy());
			note.setModifiedBy(order.getModifiedBy());
			note.setCreationDate(now);
			note.setModifyDate(now);
			note.setDescription(description);
			note.setType(OrderInstructionType.SHIPMENT.value());
			orderNoteDao.save(note);
		}
	}

	/**
	 * 非CN状态的item中有shipMethod name不是FedEx的，默认添加一条SHIPMENT类型的Note
	 * 
	 * @author Zhang Yong add date 2011-11-04
	 * @param order
	 * @param now
	 */
	private void saveUnFedExNote(OrderMain order, Date now) {
		// 非CN状态的item中有shipMethod name不是FedEx的，默认添加一条SHIPMENT类型的Note
		List<OrderItem> notFexItemList = orderItemDao.getNotFedExItem(
				order.getOrderNo(), "FedEx");
		if (notFexItemList != null && !notFexItemList.isEmpty()) {
			String description = "Ship methods of some order items are not fedex. ";
			boolean result = orderNoteDao.checkIsConteinNote(
					order.getOrderNo(), OrderInstructionType.SHIPMENT.value(),
					description);
			if (result) {
				return;
			}
			OrderNote note = new OrderNote();
			note.setNoteDate(now);
			note.setOrderNo(order.getOrderNo());
			note.setCreatedBy(order.getModifiedBy());
			note.setModifiedBy(order.getModifiedBy());
			note.setCreationDate(now);
			note.setModifyDate(now);
			note.setDescription(description);
			note.setType(OrderInstructionType.SHIPMENT.value());
			orderNoteDao.save(note);
		}
	}

	/**
	 * 保存OrderItem同时保存其相关的Service.
	 * 
	 * @param itemDTO
	 * @param item
	 * @param userId
	 */
	private void attachServiceOfItem(OrderItemDTO itemDTO, OrderItem item,
			Integer userId, Integer custNo, List<OrderItemDTO> dtoList) {
		if (!CatalogType.SERVICE.value().equals(itemDTO.getType())) {
			return;
		}
		Date now = new Date();
		if (itemDTO.getClsId().intValue() == 9 && itemDTO.getCustCloning() != null) {
			OrderCustCloning custCloning = this.dozer.map(
					itemDTO.getCustCloning(), OrderCustCloning.class);
			custCloning.setOrderNo(item.getOrderNo());
			custCloning.setOrderItemId(item.getOrderItemId());
			if (custCloning.getCreatedBy() == null) {
				custCloning.setCreatedBy(userId);
			}
			if (custCloning.getCreationDate() == null) {
				custCloning.setCreationDate(now);
			}
			custCloning.setModifiedBy(userId);
			custCloning.setModifyDate(now);
			this.orderCustCloningDao.save(custCloning);
			List<Document> documentList = itemDTO.getCustCloning()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_CUSTCLONING, itemDTO.getCustCloning()
							.getDelDocIdList(), userId);
		}
		if (itemDTO.getClsId().intValue() == 3
				&& itemDTO.getGeneSynthesis() != null) {
			OrderGeneSynthesisDTO geneSynthesisDTO = itemDTO.getGeneSynthesis();
			if (StringUtils.isBlank(itemDTO.getParentId()) && (geneSynthesisDTO == null
					|| StringUtils.isBlank(geneSynthesisDTO.getGeneName()))) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "GeneName"));
			}
			OrderGeneSynthesis geneSynthesis = this.dozer.map(geneSynthesisDTO,
					OrderGeneSynthesis.class);
			geneSynthesis.setOrderNo(item.getOrderNo());
			geneSynthesis.setOrderItemId(item.getOrderItemId());
			if (geneSynthesis.getCreatedBy() == null) {
				geneSynthesis.setCreatedBy(userId);
			}
			if (geneSynthesis.getCreationDate() == null) {
				geneSynthesis.setCreationDate(now);
			}
			geneSynthesis.setModifiedBy(userId);
			geneSynthesis.setModifyDate(now);
			this.orderGeneSynthesisDao.save(geneSynthesis);
			try {
				if (SequenceType.DNA.value().equalsIgnoreCase(
						geneSynthesis.getSequenceType())
						|| SequenceType.Protein.value().equalsIgnoreCase(
								geneSynthesis.getSequenceType())) {
					OrderMain om = orderDao.getById(geneSynthesis.getOrderNo());
					if (om != null && om.getCustNo() != null) {
						bioInfoService.calculateGeneDangerous(geneSynthesis
								.getOrderItemId().toString(), om.getCustNo()
								.toString(), "order");
					}
				}
			} catch (Exception ex) {
				try {
					throw ex;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<Document> documentList = itemDTO.getGeneSynthesis()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_GENE, itemDTO.getGeneSynthesis()
							.getDelDocIdList(), userId);
		}
		if (itemDTO.getRna() != null) {
			OrderRna rna = this.dozer.map(itemDTO.getRna(), OrderRna.class);
			rna.setOrderNo(item.getOrderNo());
			rna.setOrderItemId(item.getOrderItemId());
			if (rna.getCreatedBy() == null) {
				rna.setCreatedBy(userId);
			}
			if (rna.getCreationDate() == null) {
				rna.setCreationDate(now);
			}
			rna.setModifiedBy(userId);
			rna.setModifyDate(now);
			this.orderRnaDao.save(rna);
			List<Document> documentList = itemDTO.getRna().getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_RNA, itemDTO.getRna().getDelDocIdList(),
					userId);
		}
		if (itemDTO.getPlasmidPreparation() != null) {
			OrderPlasmidPreparation plasmid = this.dozer.map(
					itemDTO.getPlasmidPreparation(),
					OrderPlasmidPreparation.class);
			plasmid.setOrderNo(item.getOrderNo());
			plasmid.setOrderItemId(item.getOrderItemId());
			if (plasmid.getCreatedBy() == null) {
				plasmid.setCreatedBy(userId);
			}
			if (plasmid.getCreationDate() == null) {
				plasmid.setCreationDate(now);
			}
			plasmid.setModifiedBy(userId);
			plasmid.setModifyDate(now);
			if (item.getParentId() != null) {
				plasmid.setAntibioticResistance(null);
				plasmid.setCopyNumber("High");
			}
			this.orderPlasmidPreparationDao.save(plasmid);
			List<Document> documentList = itemDTO.getPlasmidPreparation()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_PLASMID, itemDTO.getPlasmidPreparation()
							.getDelDocIdList(), userId);
		}
		if (itemDTO.getClsId().intValue() == 6 && itemDTO.getOrfClone() != null) {
			OrderOrfClone orderOrfClone = this.dozer.map(itemDTO.getOrfClone(), OrderOrfClone.class);
			if (StringUtils.isBlank(itemDTO.getParentId()) && (orderOrfClone == null || StringUtils.isBlank(orderOrfClone.getAccessionNo()))) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "AccessionNo"));
			}
			orderOrfClone.setOrderNo(item.getOrderNo());
			orderOrfClone.setOrderItemId(item.getOrderItemId());
			if (orderOrfClone.getCreatedBy() == null) {
				orderOrfClone.setCreatedBy(userId);
			}
			if (orderOrfClone.getCreationDate() == null) {
				orderOrfClone.setCreationDate(now);
			}
			orderOrfClone.setModifiedBy(userId);
			orderOrfClone.setModifyDate(now);
			orderOrfCloneDao.save(orderOrfClone);
			List<Document> documentList = itemDTO.getOrfClone()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_ORFCLONE, itemDTO.getOrfClone()
							.getDelDocIdList(), userId);
		}
		if (itemDTO.getClsId().intValue() == 34 && itemDTO.getOligo() != null) {
			OrderOligoDTO orderOligoDTO = itemDTO.getOligo();
			if (StringUtils.isBlank(itemDTO.getParentId()) && (orderOligoDTO == null
					|| StringUtils.isBlank(orderOligoDTO.getOligoName()))) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "OligoName"));
			}
			OrderOligo orderOligo = this.dozer.map(orderOligoDTO,
					OrderOligo.class);
			orderOligo.setOrderNo(item.getOrderNo());
			orderOligo.setOrderItemId(item.getOrderItemId());
			if (orderOligo.getCreatedBy() == null) {
				orderOligo.setCreatedBy(userId);
			}
			if (orderOligo.getCreationDate() == null) {
				orderOligo.setCreationDate(now);
			}
			orderOligo.setModifiedBy(userId);
			orderOligo.setModifyDate(now);
			orderOligoDao.save(orderOligo);
		}
		if (itemDTO.getClsId().intValue() == 4
				&& itemDTO.getMutagenesis() != null) {
			OrderMutagenesisDTO mutageneDTO = itemDTO.getMutagenesis();
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(mutageneDTO.getVariantName())) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "Mutagenesis Target Insert Name"));
			}
			OrderMutagenesis mutagene = this.dozer.map(mutageneDTO,
					OrderMutagenesis.class);
			mutagene.setOrderNo(item.getOrderNo());
			mutagene.setOrderItemId(item.getOrderItemId());
			if (mutagene.getCreatedBy() == null) {
				mutagene.setCreatedBy(userId);
			}
			if (mutagene.getCreationDate() == null) {
				mutagene.setCreationDate(now);
			}
			mutagene.setModifiedBy(userId);
			mutagene.setModifyDate(now);
			this.orderMutagenesisDao.save(mutagene);
			List<Document> documentList = itemDTO.getMutagenesis()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_MUTA, itemDTO.getMutagenesis()
							.getDelDocIdList(), userId);
		}
		if (itemDTO.getAntibody() != null) {
			OrderAntibody antibody = this.dozer.map(itemDTO.getAntibody(),
					OrderAntibody.class);
			antibody.setOrderNo(item.getOrderNo());
			antibody.setOrderItemId(item.getOrderItemId());
			if (antibody.getCreatedBy() == null) {
				antibody.setCreatedBy(userId);
			}
			if (antibody.getCreationDate() == null) {
				antibody.setCreationDate(now);
			}
			antibody.setModifiedBy(userId);
			antibody.setModifyDate(now);
			this.orderAntibodyDao.save(antibody);
		}
		if (itemDTO.getPeptide() != null) {
			PeptideDTO peptideDTO = itemDTO.getPeptide();
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(peptideDTO.getName())) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "Peptide Name"));
			}
			OrderPeptide peptide = this.dozer.map(peptideDTO,
					OrderPeptide.class);
			peptide.setOrderNo(item.getOrderNo());
			peptide.setOrderItemId(item.getOrderItemId());
			if (peptide.getCreatedBy() == null) {
				peptide.setCreatedBy(userId);
			}
			if (peptide.getCreationDate() == null) {
				peptide.setCreationDate(now);
			}
			peptide.setModifiedBy(userId);
			peptide.setModifyDate(now);
			this.orderPeptideDao.save(peptide);
			List<Document> documentList = itemDTO.getPeptide()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_PEPTIED, itemDTO.getPeptide()
							.getDelDocIdList(), userId);
		}
		if (itemDTO.getOrderPkgService() != null) {
			OrderPkgService pkgService = this.dozer.map(
					itemDTO.getOrderPkgService(), OrderPkgService.class);
			pkgService.setOrderNo(item.getOrderNo());
			pkgService.setOrderItemId(item.getOrderItemId());
			if (pkgService.getCreatedBy() == null) {
				pkgService.setCreatedBy(userId);
			}
			if (pkgService.getCreationDate() == null) {
				pkgService.setCreationDate(now);
			}
			pkgService.setModifiedBy(userId);
			pkgService.setModifyDate(now);
			this.orderPkgServiceDao.save(pkgService);
		}
		if (itemDTO.getDnaSequencing() != null) {
			OrderDnaSequencing dnaSeq = dozer.map(itemDTO.getDnaSequencing(),
					OrderDnaSequencing.class);
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(dnaSeq.getSampleName())) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "DNA Sequencing Sample Name"));
			}
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(dnaSeq.getCode())) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "DNA Sequencing code"));
			}
			dnaSeq.setOrderNo(item.getOrderNo());
			dnaSeq.setOrderItemId(item.getOrderItemId());
			dnaSeq.setModifiedBy(userId);
			dnaSeq.setModifyDate(now);
			if (dnaSeq.getCreatedBy() == null) {
				dnaSeq.setCreatedBy(userId);
			}
			if (dnaSeq.getCreationDate() == null) {
				dnaSeq.setCreationDate(now);
			}
			String seqType = null;
			Integer lastSeq = 0;
			boolean inDB = false;
			if (dnaSeq.getCode().startsWith("T")) {
				seqType = Constants.DS_SEQ_TYPE_TUBE;
				if (!Constants.DS_SEQ_CODE_TUBE.equalsIgnoreCase(dnaSeq
						.getCode())) {
					OrderDsSeq srcDsSeq = orderDsSeqDao
							.getByTypeAndLastSeq(
									Constants.DS_SEQ_TYPE_TUBE,
									Integer.parseInt(dnaSeq.getCode().replace(
											"T", "")));
					if (srcDsSeq != null) {
						inDB = true;
					}
				}
				if (!inDB) {
					lastSeq = orderDsSeqDao
							.getDsSeqLast(Constants.DS_SEQ_TYPE_TUBE);
					dnaSeq.setCode("T" + String.format("%07d", (lastSeq + 1)));
				}
				dnaSeq.setPlateId(0);
			} else if (dnaSeq.getCode().startsWith("P")) {
				OrderDsPlates orderDsPlates = null;
				seqType = Constants.DS_SEQ_TYPE_PLATE;
				if (!Constants.DS_SEQ_CODE_PLATE.equalsIgnoreCase(dnaSeq
						.getCode())) {
					OrderDsSeq srcDsSeq = orderDsSeqDao
							.getByTypeAndLastSeq(
									Constants.DS_SEQ_TYPE_PLATE,
									Integer.parseInt(dnaSeq.getCode().replace(
											"P", "")));
					if (srcDsSeq != null) {
						if (dnaSeq.getPlateId() == null) {
							orderDsSeqDao.delete(srcDsSeq);
						} else {
							orderDsPlates = orderDsPlatesDao.getById(dnaSeq
									.getPlateId());
							if (orderDsPlates != null) {
								inDB = true;
							}
						}
					}
				}
				if (!inDB) {
					lastSeq = orderDsSeqDao
							.getDsSeqLast(Constants.DS_SEQ_TYPE_PLATE);
					dnaSeq.setCode("P" + String.format("%06d", (lastSeq + 1)));
					// 保存order_ds_plates信息
					orderDsPlates = new OrderDsPlates();
					orderDsPlates.setOrderNo(item.getOrderNo());
					orderDsPlates.setCustNo(custNo);
					orderDsPlates.setCreatedBy(userId);
					orderDsPlates.setCreationDate(now);
				}
				if (orderDsPlates != null) {
					orderDsPlates
							.setName(itemDTO.getDnaSequencing().getpName());
					orderDsPlates.setNums(itemDTO.getDnaSequencing()
							.getPlateNums());
					orderDsPlates.setLayout(itemDTO.getDnaSequencing()
							.getPlateLayout());
					orderDsPlates.setCode(dnaSeq.getCode());
					orderDsPlates.setModifiedBy(userId);
					orderDsPlates.setModifyDate(now);
					orderDsPlatesDao.getSession().evict(orderDsPlates);
					orderDsPlatesDao.save(orderDsPlates);
					dnaSeq.setPlateId(orderDsPlates.getId());
				}
			}
			if (Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equalsIgnoreCase(dnaSeq
					.getPrimerType())) {
				// OrderDnaSequencing
				// PrimerType="Enclosed"则保存相关信息到orderDsLabels、orderDsSeq表
				saveDsLabels(dnaSeq, item.getOrderNo(), custNo, userId, now);
			}
			// OrderDnaSequencing srcDnaSeq = null;
			// if (dnaSeq.getOrderItemId() != null) {
			// srcDnaSeq =
			// orderDnaSequencingDao.getById(dnaSeq.getOrderItemId());
			// if ((srcDnaSeq != null &&
			// Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equals(srcDnaSeq.getPrimerType()))
			// ||
			// Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equalsIgnoreCase(dnaSeq.getPrimerType()))
			// {
			// //OrderDnaSequencing
			// PrimerType="Enclosed"则保存相关信息到orderDsLabels、orderDsSeq表
			// saveDsLabels(srcDnaSeq, dnaSeq, item.getOrderNo(), custNo,
			// userId, now);
			// }
			// } else if
			// (Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equalsIgnoreCase(dnaSeq.getPrimerType()))
			// {
			// //OrderDnaSequencing
			// PrimerType="Enclosed"则保存相关信息到orderDsLabels、orderDsSeq表
			// saveDsLabels(srcDnaSeq, dnaSeq, item.getOrderNo(), custNo,
			// userId, now);
			// }
			// 保存lastSeq信息
			if (!inDB) {
				OrderDsSeq orderDsSeq = new OrderDsSeq();
				orderDsSeq.setType(seqType);
				orderDsSeq.setLastSeq(lastSeq + 1);
				orderDsSeqDao.save(orderDsSeq);
			}
			orderDnaSequencingDao.save(dnaSeq);
			if (dnaSeq.getCode().startsWith("P")) {
				updateDsSeqPlateId(itemDTO.getDnaSequencing().getSessPlateId(),
						dnaSeq.getPlateId(), dnaSeq.getCode(), dtoList);
			}
		}
		if (itemDTO.getCustomService() != null) {
			com.genscript.gsscm.order.entity.OrderService customService = dozer
					.map(itemDTO.getCustomService(),
							com.genscript.gsscm.order.entity.OrderService.class);
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(customService.getCustomDesc())) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "Custom Service Description"));
			}
			customService.setOrderNo(item.getOrderNo());
			customService.setOrderItemId(item.getOrderItemId());
			if (customService.getCreatedBy() == null) {
				customService.setCreatedBy(userId);
			}
			if (customService.getCreationDate() == null) {
				customService.setCreationDate(now);
			}
			customService.setModifiedBy(userId);
			customService.setModifyDate(now);
			orderServiceDao.save(customService);
		}
		if (itemDTO.getMutationLibraries() != null) {
			OrderMutationLibraries mutaLib = dozer.map(
					itemDTO.getMutationLibraries(),
					OrderMutationLibraries.class);
			mutaLib.setOrderNo(item.getOrderNo());
			mutaLib.setOrderItemId(item.getOrderItemId());
			if (mutaLib.getCreatedBy() == null) {
				mutaLib.setCreatedBy(userId);
			}
			if (mutaLib.getCreationDate() == null) {
				mutaLib.setCreationDate(now);
			}
			mutaLib.setModifiedBy(userId);
			mutaLib.setModifyDate(now);
			orderMutationLibrariesDao.save(mutaLib);
			List<Document> documentList = itemDTO.getMutationLibraries()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getOrderItemId(),
					DocumentType.OIM_MUTALIB, itemDTO.getMutationLibraries()
							.getDelDocIdList(), userId);
		}
	}

	/**
	 * 更新集合中的PlateDnaSequencing
	 * 
	 * @author Zhang Yong add date 2011-11-11
	 * @param sessPlateId
	 * @param plateId
	 * @param code
	 * @param dtoList
	 */
	private void updateDsSeqPlateId(String sessPlateId, Integer plateId,
			String code, List<OrderItemDTO> dtoList) {
		for (OrderItemDTO dto : dtoList) {
			if (dto.getDnaSequencing() == null
					|| !(sessPlateId).equals(dto.getDnaSequencing()
							.getSessPlateId())) {
				continue;
			}
			dto.getDnaSequencing().setCode(code);
			dto.getDnaSequencing().setPlateId(plateId);
		}
	}

	/**
	 * 提示必填信息
	 * 
	 * @author Zhang Yong
	 * @param itemNo
	 * @param filedName
	 * @return
	 */
	private String getErrorMessage(Integer itemNo, String filedName) {
		return "Save failed, the ItemNo " + itemNo + ": " + filedName
				+ " cannot be empty.";
	}

	/**
	 * OrderDnaSequencing PrimerType="Enclosed"则保存相关信息到orderDsLabels、orderDsSeq表
	 * 
	 * @author Zhang Yong add date 2011-11-09
	 * @param dnaSeq
	 * @param orderNo
	 * @param custNo
	 * @param userId
	 * @param now
	 */
	private void saveDsLabels(OrderDnaSequencing dnaSeq, Integer orderNo,
			Integer custNo, Integer userId, Date now) {
		// private void saveDsLabels (OrderDnaSequencing srcDnaSeq,
		// OrderDnaSequencing dnaSeq,
		// Integer orderNo, Integer custNo, Integer userId, Date now) {
		// String srcPrimerName = null;
		// String srcPrimerType = null;
		// if (srcDnaSeq != null) {
		// srcPrimerName = srcDnaSeq.getPrimerName();
		// srcPrimerType = srcDnaSeq.getPrimerType();
		// }
		// 保存到OrderDsSeq
		Integer lastSeq = orderDsSeqDao
				.getDsSeqLast(Constants.DS_SEQ_TYPE_PRIMER);
		// 保存到OrderDsLabels
		OrderDsLabels orderDsLabels = null;
		// if (StringUtils.isBlank(srcPrimerType) ||
		// StringUtils.isBlank(srcPrimerName)) {
		// orderDsLabels = new OrderDsLabels();
		// orderDsLabels.setName(dnaSeq.getPrimerName());
		// orderDsLabels.setCode("LAB"+String.format("%04d", (lastSeq+1)));
		// orderDsLabels.setOrderNo(orderNo);
		// orderDsLabels.setCustNo(custNo);
		// orderDsLabels.setCreatedBy(userId);
		// orderDsLabels.setCreationDate(now);
		// orderDsLabels.setModifiedBy(userId);
		// orderDsLabels.setModifyDate(now);
		// orderDsLabelsDao.save(orderDsLabels);
		// } else {
		// if
		// (!Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equalsIgnoreCase(srcPrimerType))
		// {
		// orderDsLabels = new OrderDsLabels();
		// orderDsLabels.setName(dnaSeq.getPrimerName());
		// orderDsLabels.setCode("LAB"+String.format("%04d", (lastSeq+1)));
		// orderDsLabels.setOrderNo(orderNo);
		// orderDsLabels.setCustNo(custNo);
		// orderDsLabels.setCreatedBy(userId);
		// orderDsLabels.setCreationDate(now);
		// orderDsLabels.setModifiedBy(userId);
		// orderDsLabels.setModifyDate(now);
		// orderDsLabelsDao.save(orderDsLabels);
		// } else if
		// (!Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equalsIgnoreCase(dnaSeq.getPrimerType()))
		// {
		// List<OrderDsLabels> dsLabellist =
		// orderDsLabelsDao.findByName(srcPrimerName, orderNo, custNo,
		// StatusType.ACTIVE.value());
		// if (dsLabellist != null && !dsLabellist.isEmpty()) {
		// orderDsLabels = dsLabellist.get(0);
		// orderDsLabels.setStatus(StatusType.INACTIVE.value());
		// orderDsLabels.setModifiedBy(userId);
		// orderDsLabels.setModifyDate(now);
		// orderDsLabelsDao.save(orderDsLabels);
		// }
		// } else if (!dnaSeq.getPrimerName().equals(srcPrimerName)) {
		// List<OrderDsLabels> dsLabellist =
		// orderDsLabelsDao.findByName(srcPrimerName, orderNo, custNo, null);
		// if (dsLabellist != null && !dsLabellist.isEmpty()) {
		// orderDsLabels = dsLabellist.get(0);
		// orderDsLabels.setStatus(StatusType.ACTIVE.value());
		// orderDsLabels.setName(dnaSeq.getPrimerName());
		// orderDsLabels.setModifiedBy(userId);
		// orderDsLabels.setModifyDate(now);
		// orderDsLabelsDao.save(orderDsLabels);
		// }
		// }
		// }
		orderDsLabels = new OrderDsLabels();
		orderDsLabels.setName(dnaSeq.getPrimerName());
		orderDsLabels.setCode("LAB" + String.format("%04d", (lastSeq + 1)));
		orderDsLabels.setOrderNo(orderNo);
		orderDsLabels.setCustNo(custNo);
		orderDsLabels.setStatus(StatusType.ACTIVE.value());
		orderDsLabels.setCreatedBy(userId);
		orderDsLabels.setCreationDate(now);
		orderDsLabels.setModifiedBy(userId);
		orderDsLabels.setModifyDate(now);
		orderDsLabelsDao.save(orderDsLabels);

		OrderDsSeq orderDsSeq = new OrderDsSeq();
		orderDsSeq.setType(Constants.DS_SEQ_TYPE_PRIMER);
		orderDsSeq.setLastSeq(lastSeq + 1);
		orderDsSeqDao.save(orderDsSeq);
	}

	private void saveServiceDocument(List<Document> docList,
			Integer orderItemId, DocumentType docType,
			List<Integer> delDocIdList, Integer userId) {
		if (docList != null && docList.size() > 0) {
			for (Document document : docList) {
				document.setCreatedBy(userId);
				document.setModifiedBy(userId);
				document.setCreationDate(new Date());
				document.setModifyDate(new Date());
				document.setRefId(orderItemId);
				if (document.getRefType() == null
						|| document.getRefType().equalsIgnoreCase("")) {
					document.setRefType(docType.value());
				}
				this.documentDao.save(document);
			}
		}
		if (delDocIdList != null) {
			this.documentDao.delDocumentList(delDocIdList);
		}
	}

	/**
	 * 获取ShippingRoute
	 * 
	 * @author Zhang Yong
	 * @param order
	 * @param itemList
	 * @return
	 */
	private String getShippingRoute(OrderMain order, List<OrderItemDTO> itemList, String dtoType) {
		String shippingRoute = CountryCode.US.value();
		OrderAddress shipToAddr = this.orderAddressDao.getById(order
				.getShiptoAddrId());
		String country = shipToAddr.getCountry();
		Integer custNo = order.getCustNo();
		// String orgName = shipToAddr.getOrganization().getName();
		boolean containGenePeptide = false;
		boolean isOnlyGenePeptide = true;
		boolean containProduct = false;
		Iterator<OrderItemDTO> item = itemList.iterator();
		while (item.hasNext()) {
			OrderItemDTO itemDTO = item.next();
			if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(
					itemDTO.getType())) {
				containProduct = true;
			}
			if (itemDTO.getClsId() == 1 || itemDTO.getClsId() == 3
					|| itemDTO.getClsId() == 30 || itemDTO.getClsId() == 31
					|| itemDTO.getClsId() == 7 || itemDTO.getClsId() == 8) {
				containGenePeptide = true;
			} else {
				isOnlyGenePeptide = false;
			}
		}
		if ((custNo == 410 || custNo == 70374) && "SERVICE".equalsIgnoreCase(dtoType)) {
			shippingRoute = CountryCode.CN.value();
		}
		String orgName = shipToAddr.getOrgName();
		if (CountryCode.JP.value().equalsIgnoreCase(country)) {	
			if (!StringUtils.contains(orgName, "Funakoshi") && containProduct == false
					&& isOnlyGenePeptide) {
				shippingRoute = CountryCode.Customer.value();
				return shippingRoute;
			}
			if (!StringUtils.contains(orgName, "Funakoshi") && containProduct == false
					&& containGenePeptide == false) {
				shippingRoute = CountryCode.US.value();
				return shippingRoute;
			}
		} else if (CountryCode.CN.value().equalsIgnoreCase(country) && "SERVICE".equalsIgnoreCase(dtoType)) {
			shippingRoute = CountryCode.CN.value();
			return shippingRoute;
		}
		return shippingRoute;
	}

	/**
	 * 保存Order的同时保存Item, 并保存该Item关联的Address.
	 * 
	 * @param order
	 * @param dtoList
	 */
	private void attachItems(final OrderMain order,
			final List<OrderItemDTO> dtoList) {
		if (dtoList == null || dtoList.isEmpty()) {
			return;
		}
		String shippingRoute = null;
		// 先更新所有的itemNo.
		// this.orderItemDao.updateOrderItemNo(order.getOrderNo(), 10000);
		// this.orderItemDao.getSession().flush();
		for (OrderItemDTO dto : dtoList) {
				shippingRoute = getShippingRoute(order, dtoList, dto.getType());
				dto.setShippingRoute(shippingRoute);
				if (dto.getSubItemList() != null && !dto.getSubItemList().isEmpty()) {
					for (OrderItemDTO subDTO : dto.getSubItemList()) {
						shippingRoute = getShippingRoute(order, dtoList, subDTO.getType());
						subDTO.setShippingRoute(shippingRoute);
					}
			}
//			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(dto.getType())) {
//				OrderAddress shipAddr = dto.getShipToAddress();
//				if (shipAddr != null && "CN".equals(shipAddr.getCountry())
//						&& "jiangsu".equalsIgnoreCase(shipAddr.getState())
//						&& "nanjing".equalsIgnoreCase(shipAddr.getCity())) {
//					dto.setShippingRoute(CountryCode.CN.value());
//				}
//			}
			this.saveAttachItems(order, dto, dtoList);
		}
		/*
		 * this.orderItemDao.getSession().flush();//先提交至数据库 //后处理新增 for
		 * (OrderItemDTO dto : dtoList) { if(dto.getOrderItemId() == null){
		 * this.saveAttachItems(order, dto); } }
		 */
	}

	/**
	 * 保存Order的同时保存OrderItem, More Detail等信息.
	 * 
	 * @param order
	 * @param dto
	 */
	private void saveAttachItems(final OrderMain order, OrderItemDTO dto,
			List<OrderItemDTO> dtoList) {
		Date now = new Date();
		Integer loginUserId = order.getModifiedBy();
		if (!StringUtils.isNumeric(dto.getParentId())) {
			dto.setParentId(null);
		}
		OrderItem item = this.dozer.map(dto, OrderItem.class);
		Integer originItemId = item.getOrderItemId();
		item.setCreatedBy(loginUserId);
		item.setCreationDate(now);
		item.setModifyDate(now);
		item.setModifiedBy(loginUserId);
		item.setOrderNo(order.getOrderNo());
		// 处理Integer值为0的bug.
		item.setClsId(SoapUtil.getIntegerFromSOAP(item.getClsId()));
		String status = item.getStatus();
		// 记录更新ShipSchedule log
		UpdateRequestLog updateRequestLog = new UpdateRequestLog();
		updateRequestLog.setObjectEntity("OrderItem");
		updateRequestLog.setField("ScheduledDelivery");
		if (SoapUtil.getIntegerFromSOAP(originItemId) == null) {// 新增时.
			updateRequestLog
					.setOriginalValue(item.getShipSchedule() == null ? null
							: item.getShipSchedule().toString());
		} else {// 修改时查找数据库, 看status有无发生变化.
			OrderItem dbItem = this.orderItemDao.findUniqueBy("orderItemId",
					item.getOrderItemId());

			String dbStatus = dbItem.getStatus();
			if (!status.equals(dbStatus)) {
				OrderProcessLog log = new OrderProcessLog();
				log.setOrderNo(order.getOrderNo());
				log.setOrderItemId(item.getOrderItemId());
				log.setPriorStat(dbItem.getStatus());
				log.setCurrentStat(item.getStatus());
				log.setReason(item.getStatusReason());
				log.setNote(dto.getStatusNote());
				log.setProcessDate(now);
				User processUser = new User();
				processUser.setUserId(order.getModifiedBy());
				log.setProcessedBy(processUser);
				this.orderProcessLogDao.save(log);
				if (status.equalsIgnoreCase("BO")) {
					StringBuilder sb = new StringBuilder();
					sb.append("The product ")
							.append(item.getCatalogNo())
							.append(" is back ordered in the sales order ")
							.append(order.getOrderNo())
							.append("<br>Please place the purchase order in ERP system ASAP");
					mimeMailService
							.sendMail(
									"Rita.Tsai@genscript.com,jiajia.zhang@genscript.com,fenxia.wei@genscript.com,lifeng.gu@genscript.com",
									"Back Order", sb.toString(),
									"scm_admin@genscriptcorp.com");
				}
			}
			this.orderItemDao.getSession().evict(dbItem);
			item.setBasePrice(dbItem.getBasePrice());
			updateRequestLog
					.setOriginalValue(dbItem.getShipSchedule() == null ? null
							: dbItem.getShipSchedule().toString());
		}
		this.orderItemDao.save(item);// 先保存item.
		// 保存ScheduledDelivery修改记录
		if (dto.getShipSchedule() != null
				&& StringUtils.isNotBlank(dto.getReason())) {
			updateRequestLog.setObjectId(item.getOrderItemId());
			updateRequestLog.setNewValue(dto.getShipSchedule() == null ? null
					: dto.getShipSchedule().toString());
			updateRequestLog.setReason(dto.getReason());
			updateRequestLog.setRequestDate(now);
            updateRequestLog.setNote(dto.getReasonNote());
			updateRequestLog.setRequestedBy(loginUserId);
			updateRequestLogDao.save(updateRequestLog);
		}
		if (SoapUtil.getIntegerFromSOAP(originItemId) == null) {// 新增时写Order_status_history.
			OrderProcessLog log = new OrderProcessLog();
			log.setOrderNo(order.getOrderNo());
			log.setOrderItemId(item.getOrderItemId());
			log.setCurrentStat(item.getStatus());
			log.setReason(item.getStatusReason());
			log.setNote(dto.getStatusNote());
			log.setProcessDate(now);
			User processUser = new User();
			processUser.setUserId(order.getModifiedBy());
			log.setProcessedBy(processUser);
			this.orderProcessLogDao.save(log);
		}
		// 保存More Detail中的相关Service信息. add by zhanghuibin
		// if ("Y".equals(dto.getUpdateFlag())) {
		this.attachServiceOfItem(dto, item, order.getModifiedBy(),
				order.getCustNo(), dtoList);
		// }
		// 保存它的子级OrderItem是递归的.
		if (dto.getSubItemList() != null && !dto.getSubItemList().isEmpty()) {
			// 先处理更新，因为item no唯一性的原因
			for (OrderItemDTO subDTO : dto.getSubItemList()) {
				subDTO.setParentId(item.getOrderItemId().toString());
				subDTO.setShiptoAddrId(item.getShiptoAddrId());
				this.saveAttachItems(order, subDTO, dtoList);
			}
		}
	}

	private OrderAddress attachAddr(final OrderMain order,
			OrderAddress orderAddr) {
		if (orderAddr.getAddrId() == null
				|| orderAddr.getAddrId().intValue() == 0) {
			orderAddr.setAddrId(null);
		}
		orderAddr.setOrderNo(order.getOrderNo());
		Date now = new Date();
		orderAddr.setCreatedBy(order.getModifiedBy());
		orderAddr.setCreationDate(now);
		orderAddr.setModifiedBy(order.getModifiedBy());
		orderAddr.setModifyDate(now);
		this.orderAddressDao.save(orderAddr);
		return orderAddr;
	}

	/**
	 * 处理库存
	 * 
	 * @param order
	 */
	private void reserveQty(OrderMainDTO order, Integer userId) {
		if (SoapUtil.getIntegerFromSOAP(order.getOrderNo()) == null) {
			return;
		}
		OrderMain dbOrder = this.orderDao.getById(order.getOrderNo());
		String oldStatus = dbOrder.getStatus();
		String newStatus = order.getStatus();
		if (newStatus.equalsIgnoreCase(oldStatus)) {
			return;
		}
		String reserveFlag = null;
		// 消耗库存
		if (newStatus.equalsIgnoreCase(OrderStatusType.CC.value())) {
			reserveFlag = "0";
		} else if (oldStatus.equalsIgnoreCase(OrderStatusType.CC.value())
				&& (newStatus.equalsIgnoreCase(OrderStatusType.NW.value())
						|| newStatus.equalsIgnoreCase(OrderStatusType.RV
								.value()) || newStatus
							.equalsIgnoreCase(OrderStatusType.CN.value()))) {
			reserveFlag = "1";
		}
		if (reserveFlag != null) {
			if (order.getItemList() != null && !order.getItemList().isEmpty()) {
				for (OrderItemDTO dto : order.getItemList()) {
					if (QuoteItemType.SERVICE.value().equalsIgnoreCase(
							dto.getType())
							|| !OrderItemStatusType.CC.value()
									.equalsIgnoreCase(dto.getStatus())) {
						continue;
					}
					if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(
							dto.getType())) {
						Product prod = productDao.getProductByCatalogNo(dto
								.getCatalogNo());
						if (prod != null
								&& ("N").equalsIgnoreCase(prod.getShippable())) {
							continue;
						}
					}
					if ("0".equalsIgnoreCase(reserveFlag)) {
						this.inventoryService.reserveQty(order.getOrderNo(),
								dto.getItemNo(), order.getWarehouseId(),
								dto.getStorageId(), dto.getCatalogNo(),
								dto.getQuantity(), userId, dto.getQtyUom(),
								dto.getSize(), dto.getSizeUom());
					} else {
						this.inventoryService.cancelReserveQty(
								order.getOrderNo(), dto.getItemNo());
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void saveOrUpdateOrder(OrderMain order, String statusNote, OrderProcessLog orderProcessLog) {
		Integer originOrderNo = order.getOrderNo();
		Date now = new Date();
		order.setCreatedBy(order.getModifiedBy());
		order.setCreationDate(now);
		order.setModifyDate(now);
		order.setOrderDate(now);
		order.setExchRateDate(now);
		Customer cust = this.customerDao.getById(order.getCustNo());
		int internalOrderFlag = 0;
		if (Constants.INTERNAL_TYPE_CUSTOMER.equalsIgnoreCase(cust.getCustType())) {
			internalOrderFlag = 1;
		}
		if (order.getExchRate() == null) {
			order.setExchRate(1d);
		}
		if (SoapUtil.getIntegerFromSOAP(originOrderNo) == null) {// 新增时初始化currency相关值.
			
			order.setExchRateDate(order.getOrderDate());
			order.setBaseCurrency(CurrencyType.USD.value());
			// order.setBaseCurrency(cust.getPaymentCurrency());
			order.setOrderCurrency(cust.getPaymentCurrency());
			this.customerDao.getSession().evict(cust);
		} else {
			String status = order.getStatus();
			if (status != null && status.trim().length() > 0) {
				OrderMain dbOrder = this.orderDao.getById(order.getOrderNo());
				String dbStatus = dbOrder.getStatus();
				OrderProcessLog lastConfirmLog = orderProcessLogDao
						.getOrderLastConfirm(order.getOrderNo());
				if (status.equals(dbStatus)) {
					if (status.equals(OrderStatusType.RV.name())) {
						if (lastConfirmLog != null) {
							// Reopen状态
							Integer userId = SessionUtil.getUserId();
							saveOrderChangeLog(order, now, dbOrder, userId);
							saveOrderItemChangeLog(order, now, userId);
						}
					}
				} else {
					// 判断是否是customer confirm时更新ERP状态
					if (status.equals(OrderStatusType.CC.name())
							&& dbOrder.getKeyInfoChanged() != 1) {
						// List<OrderItem> orderItemList = orderItemDao
						// .getOrderAllItemList(order.getOrderNo());
						Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
								.getRow(SessionConstant.OrderItemList.value(),
										order.getOrderNo().toString());
						String vtRatioStr = "0";
						HashMap<String, ErpOrderDtlDTO> hashMap = new HashMap<String, ErpOrderDtlDTO>();
						if (itemMap != null && itemMap.size() > 0) {
							org.dom4j.Document document = bulidOrderDtlXmlStr(
									itemMap, vtRatioStr);
							String orderDetailStr = document.asXML();
							// ApplicationEvent evt = new
							// NewSalesOrderEvent(this, order);
							if (lastConfirmLog == null) {
								// context.publishEvent(evt);
								Integer billAddrToId = order.getBilltoAddrId();
								OrderAddress billToAddr = orderAddressDao
										.findByAddrId(billAddrToId);
								String accountCode = billTerritoryDao
										.getAccountCode(
												billToAddr.getCountry(),
												billToAddr.getState(),
												billToAddr.getZipCode());
								if ("HK".equalsIgnoreCase(accountCode)) {
									order.setGsCoId(4);
								} else {
									order.setGsCoId(1);
								}
								boolean typeFlag = false;
								for (Iterator<String> iter = itemMap.keySet()
										.iterator(); iter.hasNext();) {
									String key = iter.next();
									OrderItemDTO orderItem = (OrderItemDTO) itemMap
											.get(key);
									if ("PRODUCT".equalsIgnoreCase(orderItem
											.getType())) {
									} else {
										typeFlag = true;
									}
								}
								if (typeFlag && internalOrderFlag == 0) {
									Integer srcPoNo = purchaseService
											.savePurchaseOrder(order,
													order.getOrderNo());
									purchaseService.saveNanjingSalesOrder(
											order, order.getOrderNo(), srcPoNo);
								}
								// judgeUsOrHkCompany(order, dbOrder, itemMap);
								eventFlag = true;
							} else {
								String orderDetailXml = lastConfirmLog
										.getOrderDetail();
								if (orderDetailXml != null) {
									convertXmlStr2Map(hashMap, orderDetailXml);
									boolean modifyFlag = false;
									for (Iterator<String> iter = itemMap
											.keySet().iterator(); iter
											.hasNext();) {
										String key = iter.next();
										OrderItemDTO orderItem = (OrderItemDTO) itemMap
												.get(key);
										ErpOrderDtlDTO lastOrderDtlDTO = hashMap
												.get(orderItem.getItemNo()
														.toString());
										if ("CN".equals(orderItem.getStatus())
												&& lastOrderDtlDTO != null
												&& !"CN".equals(lastOrderDtlDTO
														.getStatus())) {
											orderItem.setChangeStatus("Delete");
											modifyFlag = true;
											continue;
										}
										if (hashMap.containsKey(orderItem
												.getItemNo().toString())) {
											ErpOrderDtlDTO dtlDTO = hashMap
													.get(orderItem.getItemNo()
															.toString());
											if (orderItem
													.getUnitPrice()
													.compareTo(
															new BigDecimal(
																	dtlDTO.getUnitPrice())) == 0) {
											} else {
												orderItem
														.setChangeStatus("Modify");
												modifyFlag = true;
											}
										} else {
											orderItem.setChangeStatus("Add");
											modifyFlag = true;
										}
									}
									if (modifyFlag) {
										// context.publishEvent(evt);
										eventFlag = true;
									}
								}
							}
							OrderProcessLog log = new OrderProcessLog();
							log.setOrderNo(order.getOrderNo());
							log.setPriorStat(dbOrder.getStatus());
							log.setCurrentStat(order.getStatus());
							log.setReason(order.getStatusReason());
							log.setNote(statusNote);
							log.setProcessDate(now);
							if(orderProcessLog != null){
								log.setReason(orderProcessLog.getReason());
								log.setNote(orderProcessLog.getNote());
							}
							User processUser = new User();
							processUser.setUserId(order.getModifiedBy());
							log.setProcessedBy(processUser);
							log.setOrderDetail(orderDetailStr);
							this.orderProcessLogDao.save(log);
							// updateOrderProcessLog(order.getStatusReason(),
							// status, loginUserId,
							// statusNote, now, order1, dbStatus,
							// orderDetailStr);
						}
					} else {
						//如果order状态为CN
						if (lastConfirmLog != null && status.equals(OrderStatusType.CN.name())) {
							OrderMain orderMain = dozer.map(order, OrderMain.class);
							ApplicationEvent cancelEvt = new CancelOrderEvent(this, orderMain);
							context.publishEvent(cancelEvt);
						}
						OrderProcessLog log = new OrderProcessLog();
						log.setOrderNo(order.getOrderNo());
						log.setPriorStat(dbOrder.getStatus());
						log.setCurrentStat(order.getStatus());
						log.setReason(order.getStatusReason());
						log.setNote(statusNote);
						log.setProcessDate(now);
						User processUser = new User();
						processUser.setUserId(order.getModifiedBy());
						log.setProcessedBy(processUser);
						this.orderProcessLogDao.save(log);
					}
				}
				this.orderDao.getSession().evict(dbOrder);
				order.setBaseCurrency(dbOrder.getBaseCurrency());
			}
		}
		this.orderDao.save(order);// 先保存Order.
		if (SoapUtil.getIntegerFromSOAP(originOrderNo) == null) {// 新增时写Order_status_history.
			OrderProcessLog log = new OrderProcessLog();
			log.setOrderNo(order.getOrderNo());
			log.setCurrentStat(order.getStatus());
			log.setReason(order.getStatusReason());
			log.setNote(statusNote);
			log.setProcessDate(now);
			User processUser = new User();
			processUser.setUserId(order.getModifiedBy());
			log.setProcessedBy(processUser);
			this.orderProcessLogDao.save(log);
		}
		if(internalOrderFlag == 1){
			eventFlag = false;
		}
	}

	private void saveOrderItemChangeLog(OrderMain order, Date now,
			Integer userId) {
		@SuppressWarnings("unchecked")
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(), order
						.getOrderNo().toString());
		if (itemMap != null && itemMap.size() > 0) {
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
					.hasNext();) {
				String key = iter.next();
				OrderItemDTO orderItem = (OrderItemDTO) itemMap.get(key);
				BigDecimal newPrice = orderItem.getUnitPrice();
				BigDecimal newCost = orderItem.getCost();
				BigDecimal newDiscount = orderItem.getDiscount();
				UpdateRequestLog updateRequestLog1 = new UpdateRequestLog();
				UpdateRequestLog updateRequestLog2 = new UpdateRequestLog();
				UpdateRequestLog updateRequestLog3 = new UpdateRequestLog();
				updateRequestLog1.setField("Price");
				updateRequestLog1.setObjectEntity("orderItem");
				updateRequestLog1.setObjectId(orderItem.getOrderItemId());
				updateRequestLog1.setNewValue(newPrice.toString());
				updateRequestLog1.setRequestDate(now);
				updateRequestLog1.setRequestedBy(userId);
				updateRequestLog2.setField("Cost");
				updateRequestLog2.setObjectEntity("orderItem");
				updateRequestLog2.setObjectId(orderItem.getOrderItemId());
				updateRequestLog2.setNewValue(newCost.toString());
				updateRequestLog2.setRequestDate(now);
				updateRequestLog2.setRequestedBy(userId);
				updateRequestLog3.setField("Discount");
				updateRequestLog3.setObjectEntity("orderItem");
				updateRequestLog3.setObjectId(orderItem.getOrderItemId());
				updateRequestLog3.setNewValue(newDiscount.toString());
				updateRequestLog3.setRequestDate(now);
				updateRequestLog3.setRequestedBy(userId);

				OrderItem oldOrderItem = orderItemDao.getOrderItem(
						order.getOrderNo(), orderItem.getItemNo());
				if (oldOrderItem != null) {
					double oldPrice = oldOrderItem.getUnitPrice();
					double oldCost = oldOrderItem.getCost();
					double oldDiscount = oldOrderItem.getDiscount();
					updateRequestLog1.setOriginalValue(oldPrice + "");
					updateRequestLog1.setOriginalValue(oldCost + "");
					updateRequestLog1.setOriginalValue(oldDiscount + "");
				}
				updateRequestLogDao.save(updateRequestLog1);
				updateRequestLogDao.save(updateRequestLog2);
				updateRequestLogDao.save(updateRequestLog3);
			}
		}
	}

	private void saveOrderChangeLog(OrderMain order, Date now,
			OrderMain dbOrder, Integer userId) {
		UpdateRequestLog updateRequestLog1 = new UpdateRequestLog();
		UpdateRequestLog updateRequestLog2 = new UpdateRequestLog();
		UpdateRequestLog updateRequestLog3 = new UpdateRequestLog();
		UpdateRequestLog updateRequestLog4 = new UpdateRequestLog();
		UpdateRequestLog updateRequestLog5 = new UpdateRequestLog();
		UpdateRequestLog updateRequestLog6 = new UpdateRequestLog();

		BigDecimal newTotal = order.getSubTotal();
		BigDecimal oldTotal = dbOrder.getSubTotal();
		updateRequestLog1.setField("Subtotal");
		updateRequestLog1.setObjectEntity("order");
		updateRequestLog1.setObjectId(order.getOrderNo());
		updateRequestLog1.setNewValue(newTotal.toString());
		updateRequestLog1.setOriginalValue(oldTotal.toString());
		updateRequestLog1.setRequestDate(now);
		updateRequestLog1.setRequestedBy(userId);
		updateRequestLogDao.save(updateRequestLog1);
		double newShipAmt = order.getShipAmt();
		double oldShipAmt = dbOrder.getShipAmt();
		updateRequestLog2.setField("Shipping");
		updateRequestLog2.setObjectEntity("order");
		updateRequestLog2.setObjectId(order.getOrderNo());
		updateRequestLog2.setNewValue(newShipAmt + "");
		updateRequestLog2.setOriginalValue(oldShipAmt + "");
		updateRequestLog2.setRequestDate(now);
		updateRequestLog2.setRequestedBy(userId);
		updateRequestLogDao.save(updateRequestLog2);
		BigDecimal newTax = order.getTax();
		BigDecimal oldTax = dbOrder.getTax();
		updateRequestLog3.setField("Tax");
		updateRequestLog3.setObjectEntity("order");
		updateRequestLog3.setObjectId(order.getOrderNo());
		updateRequestLog3.setNewValue(newTax.toString());
		updateRequestLog3.setOriginalValue(oldTax.toString());
		updateRequestLog3.setRequestDate(now);
		updateRequestLog3.setRequestedBy(userId);
		updateRequestLogDao.save(updateRequestLog3);
		BigDecimal newDiscount = order.getDiscount();
		BigDecimal oldDiscount = dbOrder.getDiscount();
		updateRequestLog4.setField("Discount");
		updateRequestLog4.setObjectEntity("order");
		updateRequestLog4.setObjectId(order.getOrderNo());
		updateRequestLog4.setNewValue(newDiscount.toString());
		updateRequestLog4.setOriginalValue(oldDiscount.toString());
		updateRequestLog4.setRequestDate(now);
		updateRequestLog4.setRequestedBy(userId);
		updateRequestLogDao.save(updateRequestLog4);
		double newCouponVal = order.getCouponValue();
		double oldCouponVal = dbOrder.getCouponValue();
		updateRequestLog5.setField("GiftCard");
		updateRequestLog5.setObjectEntity("order");
		updateRequestLog5.setObjectId(order.getOrderNo());
		updateRequestLog5.setNewValue(newCouponVal + "");
		updateRequestLog5.setOriginalValue(oldCouponVal + "");
		updateRequestLog5.setRequestDate(now);
		updateRequestLog5.setRequestedBy(userId);
		updateRequestLogDao.save(updateRequestLog5);

		updateRequestLog6.setField("Handling");
		updateRequestLog6.setObjectEntity("order");
		updateRequestLog6.setObjectId(order.getOrderNo());
		updateRequestLog6.setRequestDate(now);
		updateRequestLog6.setRequestedBy(userId);

		@SuppressWarnings("unchecked")
		Map<String, OrderPackageDTO> packageMap = (Map<String, OrderPackageDTO>) SessionUtil
				.getRow(SessionConstant.OrderPackage.value(), order
						.getOrderNo().toString());
		if (packageMap != null) {
			double handingTotal = 0;
			Iterator<Entry<String, OrderPackageDTO>> it = packageMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, OrderPackageDTO> entry = it.next();
				OrderPackageDTO orderPackageDTO = entry.getValue();
				handingTotal += orderPackageDTO.getHandlingFee();
			}
			updateRequestLog6.setNewValue(handingTotal + "");
		}
		List<OrderPackage> orderPackageList = orderPackageDao
				.getOrderPackageList(order.getOrderNo());
		if (orderPackageList != null && orderPackageList.size() > 0) {
			double oldHandingTotal = 0;
			for (OrderPackage orderPackage : orderPackageList) {
				oldHandingTotal += orderPackage.getHandlingFee();
			}
			updateRequestLog6.setOriginalValue(oldHandingTotal + "");
		}
		updateRequestLogDao.save(updateRequestLog6);
	}

	@SuppressWarnings("unused")
	private void judgeUsOrHkCompany(OrderMain order, OrderMain dbOrder,
			Map<String, OrderItemDTO> itemMap) {
		Integer companyId = dbOrder.getGsCoId();
		if (companyId != null) {
			if (companyId == 4) {
				String customerCompany = customerDao.getCustomerCompany(
						order.getCustNo(), null);
				for (Iterator<String> iter = itemMap.keySet().iterator(); iter
						.hasNext();) {
					String key = iter.next();
					OrderItemDTO orderItem = (OrderItemDTO) itemMap.get(key);
					if ("Product".equalsIgnoreCase(orderItem.getType())) {
						Integer qty = orderItem.getQuantity();
						BigDecimal prodQuantity = new BigDecimal(0);
						try {
							prodQuantity = erpSalesOrderService
									.getPartStorageNumber(
											orderItem.getCatalogNo(),
											customerCompany);
						} catch (Exception be) {
							prodQuantity = new BigDecimal(0);
						}
						if (prodQuantity.intValue() < qty) {
							order.setGsCoId(1);
							break;
						}
					}
				}
			}
		}
	}

	private org.dom4j.Document bulidOrderDtlXmlStr(
			Map<String, OrderItemDTO> itemMap, String vtRatioStr) {
		org.dom4j.Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("SalesOrderDataSet");
		for (Iterator<String> iter = itemMap.keySet().iterator(); iter
				.hasNext();) {
			String key = iter.next();
			OrderItemDTO orderItem = (OrderItemDTO) itemMap.get(key);
			// if ("CN".equals(orderItem.getStatus())) {
			// continue;
			// }
			Element orderDtlElement = rootElement.addElement("OrderDtl");
			Element dtlItemNumElement = orderDtlElement.addElement("ItemNum");
			dtlItemNumElement.addText(orderItem.getItemNo().toString());
			Element dtlUnitPriceElement = orderDtlElement
					.addElement("UnitPrice");
			dtlUnitPriceElement.addText(orderItem.getUnitPrice().toString());
			Element dtlCostElement = orderDtlElement.addElement("Cost");
			dtlCostElement.addText(orderItem.getCost().toString());
			Element dtlItemStatusElement = orderDtlElement.addElement("Status");
			dtlItemStatusElement.addText(orderItem.getStatus());
			// Element dtlVtRatioElement =
			// orderDtlElement.addElement("VtRatio");
			// Element dtlBtRatioElement =
			// orderDtlElement.addElement("BtRatio");
			// if ("PRODUCT".equalsIgnoreCase(orderItem.getType())) {
			// Product product = productDao.getProductByCatalogNo(orderItem
			// .getCatalogNo());
			// if (product != null && product.getVtRatio() == null
			// || product.getBtRatio() == null) {
			// dtlVtRatioElement.addText("1");
			// dtlBtRatioElement.addText("0");
			// vtRatioStr = "1";
			// } else {
			// dtlVtRatioElement.addText(product.getVtRatio());
			// dtlBtRatioElement.addText(product.getBtRatio());
			// vtRatioStr = product.getVtRatio();
			// }
			// } else {
			// com.genscript.gsscm.serv.entity.Service serv = serviceDao
			// .getServiceByCatalogNo(orderItem.getCatalogNo());
			// if (serv != null && serv.getVtRatio() == null
			// || serv.getBtRatio() == null) {
			// dtlVtRatioElement.addText("1");
			// dtlBtRatioElement.addText("0");
			// vtRatioStr = "1";
			// } else {
			// dtlVtRatioElement.addText(serv.getVtRatio());
			// dtlBtRatioElement.addText(serv.getBtRatio());
			// vtRatioStr = serv.getVtRatio();
			// }
			// }
			// orderItem.setVtRatioStr(vtRatioStr);

		}
		return document;
	}

	private void convertXmlStr2Map(HashMap<String, ErpOrderDtlDTO> hashMap,
			String orderDetailXml) {
		try {
			org.dom4j.Document ndocument = DocumentHelper
					.parseText(orderDetailXml);
			Element nrootElement = ndocument.getRootElement();

			for (@SuppressWarnings("rawtypes")
			Iterator i = nrootElement.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();// 取得各条记录
				ErpOrderDtlDTO erpOrderDtlDTO = new ErpOrderDtlDTO();

				for (@SuppressWarnings("rawtypes")
				Iterator j = element.elementIterator(); j.hasNext();) {
					Element ele = (Element) j.next();// 取得各条记录里的各个字段
					Field field;
					try {
						field = erpOrderDtlDTO.getClass().getDeclaredField(
								ele.getName());
						field.setAccessible(true);
						try {
							field.set(erpOrderDtlDTO, ele.getStringValue()
									.trim());
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// System.out.print("name="+ele.getName()+";  ");
					// System.out.println("value="+ele.getStringValue());
				}
				hashMap.put(erpOrderDtlDTO.getItemNum().toString(),
						erpOrderDtlDTO);
				System.out.println(erpOrderDtlDTO);
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	public List<OrderTemplate> getTemplateList(final Integer userId) {
		return orderTemplateDao.findBy("owner", userId);
	}

	private void attachPromotion(OrderMain order, OrderPromotionDTO dto,
			Integer promotionId) {
		if (dto == null) {
			// Get Promotion
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo",
					order.getOrderNo());
			filterList.add(quoteFilter);
			List<OrderPromotion> orderPromList = this.orderPromotionDao
					.find(filterList);
			if (orderPromList != null && !orderPromList.isEmpty()) {
				OrderPromotion orderPromotion = orderPromList.get(0);
				this.orderPromotionDao.delete(orderPromotion);
			}
			return;
		}
		if (dto.getPrmtCode() == null || dto.getPrmtCode().trim().length() < 1) {
			// Get Promotion
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo",
					order.getOrderNo());
			filterList.add(quoteFilter);
			List<OrderPromotion> orderPromList = this.orderPromotionDao
					.find(filterList);
			if (orderPromList != null && !orderPromList.isEmpty()) {
				OrderPromotion orderPromotion = orderPromList.get(0);
				this.orderPromotionDao.delete(orderPromotion);
			}
		} else {
			OrderPromotion orderPromotion = this.dozer.map(dto,
					OrderPromotion.class);
			if (promotionId == null || promotionId.intValue() == 0) {
				orderPromotion.setId(null);
			} else {
				orderPromotion.setId(promotionId);
			}
			orderPromotion.setOrderNo(order.getOrderNo());
			Date now = new Date();
			orderPromotion.setCreatedBy(order.getModifiedBy());
			orderPromotion.setCreationDate(now);
			orderPromotion.setModifiedBy(order.getModifiedBy());
			orderPromotion.setModifyDate(now);
			this.orderPromotionDao.save(orderPromotion);
		}
	}

	/**
	 * 保存Customer的同时保存，修改和删除OrderPackage list.
	 * 
	 * @param orderPackageList
	 * @param delOrderPackageIdList
	 */
	protected void attachOrderPackage(OrderMain order,
			List<OrderPackageDTO> orderPackageList,
			List<Integer> delOrderPackageIdList) {
		this.orderPackageItemDao.delItemListByOrderNo(order.getOrderNo());
		this.orderPackageDao.delOrderPackageListByOrderNo(order.getOrderNo());
		if (orderPackageList == null || orderPackageList.get(0) == null) {
			return;
		}
		Integer userId = SessionUtil.getUserId();
		Date now = new Date();
		for (OrderPackageDTO dto : orderPackageList) {
			OrderPackage orderPackage = this.dozer.map(dto, OrderPackage.class);
			orderPackage.setCreatedBy(userId);
			orderPackage.setCreationDate(now);
			orderPackage.setModifyDate(now);
			orderPackage.setModifiedBy(order.getModifiedBy());
			this.orderPackageDao.save(orderPackage);
			if (dto.getPackageItemList() != null) {
				for (OrderPackageItemDTO pkgItemDTO : dto.getPackageItemList()) {
					OrderPackageItem pckItem = this.dozer.map(pkgItemDTO,
							OrderPackageItem.class);
					pckItem.setPackageId(orderPackage.getPackageId());
					pckItem.setCreatedBy(userId);
					pckItem.setModifiedBy(userId);
					pckItem.setCreationDate(now);
					pckItem.setModifyDate(now);
					this.orderPackageItemDao.save(pckItem);
				}
			}
		}
	}

	/**
	 * 保存Order及OrderItem的Address. 对于Order及OrderItem的多个Address必须要返回， 否则会执行删除.
	 * Zhang Yong
	 * 
	 * @param order
	 * @param addrMap
	 * @return
	 */
	private Map<String, OrderAddress> saveAddrMap(OrderMain order,
			Map<String, OrderAddress> addrMap) {
		List<OrderAddress> dbAddrList = this.orderAddressDao
				.getAddrByOrderNo(order.getOrderNo());
		Map<String, OrderAddress> sourceAddrMap = null;
		if (dbAddrList != null && !dbAddrList.isEmpty()) {
			sourceAddrMap = SessionUtil.convertList2Map(dbAddrList, "addrId");
		}
		Iterator<Entry<String, OrderAddress>> iterator = addrMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, OrderAddress> entry = iterator.next();
			String key = entry.getKey();
			OrderAddress srcAddr = entry.getValue();
			if (srcAddr.getAddrId() != null) {
				if (sourceAddrMap != null
						&& sourceAddrMap.containsKey(srcAddr.getAddrId()
								.toString())) {
					sourceAddrMap.put(srcAddr.getAddrId().toString(), null);
				}
				for (OrderAddress evictAddr : dbAddrList) {
					if (srcAddr.getAddrId().intValue() == evictAddr.getAddrId()
							.intValue()) {
						this.orderAddressDao.getSession().evict(evictAddr);
						break;
					}
				}
			}
			srcAddr = this.attachAddr(order, srcAddr);
			addrMap.put(key, srcAddr);
		}
		if (sourceAddrMap != null && !sourceAddrMap.isEmpty()) {
			for (String key : sourceAddrMap.keySet()) {
				if (key != null && sourceAddrMap.get(key) != null) {
					this.orderAddressDao.delete(Integer.parseInt(key));
				}
			}
		}
		return addrMap;
	}

	/**
	 * 保存Order基本信息及OrderItems, OrderMails, OrderNotes, PaymentPlans, promotion.
	 * 
	 * @param orderDTO
	 * @param userId
	 * @return
	 */
	public OrderMainDTO saveOrder(OrderMainDTO orderDTO, Integer userId) {
		eventFlag = false;
		this.reserveQty(orderDTO, userId);// 消耗库存
		String dbAccountNo = null;
		if (orderDTO.getOrderNo() != null) {
			dbAccountNo = orderDao.getById(orderDTO.getOrderNo())
					.getShippingAccount();
		}
		OrderMainDTO retDTO = new OrderMainDTO();
		OrderMain order = this.dozer.map(orderDTO, OrderMain.class);
		order.setModifiedBy(userId);
		if (order.getOrderNo() == null && orderDTO.getItemList() != null
				&& !orderDTO.getItemList().isEmpty()) {
			Integer firstServiceClsId = null;
			for (OrderItemDTO ordDTO : orderDTO.getItemList()) {
				if (QuoteItemType.SERVICE.value().equals(ordDTO.getType())
						&& ordDTO.getClsId() != null) {
					firstServiceClsId = ordDTO.getClsId();
					break;
				}
			}
			if (firstServiceClsId != null) {
				List<User> userList = salesProjectManagerAssignmentDao
						.findProManagerByClsId(firstServiceClsId);
				if (userList != null && !userList.isEmpty()) {
					order.setProjectManager(userList.get(0).getUserId());
				}
			}
			if (order.getProjectManager() == null
					|| order.getProjectManager() == -1) {
				List<User> proUserList = salesRepDao
						.findUserByFunction(SalesRepSalesRole.PROJECT_SUPPORT
								.value());
				if (proUserList != null && !proUserList.isEmpty()) {
					order.setAltProjectManager(proUserList.get(0).getUserId());
				}
			}
		}
		this.saveOrUpdateOrder(order, orderDTO.getStatusNote(), orderDTO.getOrderProcessLog());
		// 1. 提取所有Address(Order, OrderItem及彼此重复的)
		Map<String, OrderAddress> addrMap = new HashMap<String, OrderAddress>();
		if (orderDTO.getOrderAddrList() != null) {
			OrderAddress orderBillToAddr = orderDTO.getOrderAddrList()
					.getBillToAddr();
			OrderAddress orderShipToAddr = orderDTO.getOrderAddrList()
					.getShipToAddr();
			Integer obaId = null;
			Integer osaId = null;
			if (orderBillToAddr != null) {
				obaId = orderBillToAddr.getAddrId();
			}
			if (orderShipToAddr != null) {
				osaId = orderShipToAddr.getAddrId();
			}
			if (obaId != null && osaId != null
					&& obaId.intValue() == osaId.intValue()) {
				OrderAddress newOrderBillToAddr = dozer.map(orderBillToAddr,
						OrderAddress.class);
				newOrderBillToAddr.setAddrId(null);
				if (AddressType.BILL_TO.value().equalsIgnoreCase(
						newOrderBillToAddr.getAddrType())) {
					orderDTO.getOrderAddrList().setShipToAddr(
							newOrderBillToAddr);
				} else if (AddressType.SHIP_TO.value().equalsIgnoreCase(
						newOrderBillToAddr.getAddrType())) {
					orderDTO.getOrderAddrList().setBillToAddr(
							newOrderBillToAddr);
				}
			}
			if (orderBillToAddr != null) {
				orderDTO.getOrderAddrList().getBillToAddr()
						.setAddrType(AddressType.BILL_TO.value());
				addrMap.put("ORDER_BILL_ADDR", orderDTO.getOrderAddrList()
						.getBillToAddr());
			}
			if (orderShipToAddr != null) {
				orderDTO.getOrderAddrList().getShipToAddr()
						.setAddrType(AddressType.SHIP_TO.value());
				addrMap.put("ORDER_SHIP_ADDR", orderDTO.getOrderAddrList()
						.getShipToAddr());
			}
		}
		if (order.getShiptoAddrFlag() != null
				&& order.getShiptoAddrFlag().intValue() == 3
				&& orderDTO.getItemList() != null
				&& !orderDTO.getItemList().isEmpty()) {
			for (OrderItemDTO dto : orderDTO.getItemList()) {
				if (dto.getShipToAddress() != null) {
					addrMap.put("OI" + dto.getItemNo(), dto.getShipToAddress());
				}
			}
		}
		// 依次保存各不重复的OrderAddress进数据库并返回该list.
		Map<String, OrderAddress> dbAddrMap = this.saveAddrMap(order, addrMap);
		// 重新关联Order对应的3个Address.
		if (orderDTO.getOrderAddrList() != null) {
			if (orderDTO.getOrderAddrList().getShipToAddr() != null) {
				order.setShiptoAddrId(dbAddrMap.get("ORDER_SHIP_ADDR")
						.getAddrId());
			}
			if (orderDTO.getOrderAddrList().getBillToAddr() != null) {
				order.setBilltoAddrId(dbAddrMap.get("ORDER_BILL_ADDR")
						.getAddrId());
			}
			this.orderDao.save(order);// 更新一下它的几个地址信息.
		}
		// 删除OrderItem(都是子Item， Service)
		if (orderDTO.getDelItemIdList() != null
				&& orderDTO.getDelItemIdList().size() > 0) {
			this.delSubOrderItem(order, orderDTO.getDelItemIdList());
		}
		// 给OrderItem依次关联上对应的orderAddress.
		List<OrderItemDTO> itemList = new ArrayList<OrderItemDTO>();
		if (order.getShiptoAddrFlag() != null
				&& order.getShiptoAddrFlag().intValue() == 3) {
			for (OrderItemDTO itemDTO : orderDTO.getItemList()) {
				itemDTO.setShiptoAddrId(dbAddrMap.get(
						"OI" + itemDTO.getItemNo()).getAddrId());
				itemList.add(itemDTO);
			}
		} else {
			for (OrderItemDTO itemDTO : orderDTO.getItemList()) {
				itemDTO.setShiptoAddrId(order.getShiptoAddrId());
				itemList.add(itemDTO);
			}
		}
		this.attachItems(order, itemList);
		List<Integer> sendMailIdList = this.attachInstruction(order,
				orderDTO.getInstructionList(), dbAccountNo);
		this.attachPayment(order, orderDTO.getDelPaymentPlanIdList(),
				orderDTO.getPaymentPlanList());
		this.attachPromotion(order, orderDTO.getOrderPromotion(),
				orderDTO.getPromotionId());
		this.attachCustCardList(order, orderDTO.getCardList(),
				orderDTO.getDelCardIdList(), userId);
		// For orderPackage
		this.attachOrderPackage(order, orderDTO.getOrderPackageList(),
				orderDTO.getDelOrderPackageIdList());
		orderDTO.setSendMailIdList(sendMailIdList);
		orderDTO.setOrderNo(order.getOrderNo());
		retDTO.setSendMailIdList(sendMailIdList);
		retDTO.setOrderNo(order.getOrderNo());
		// 根据order和Item新增shipment和shipment line
		attachShipment(order, userId);

		{
			List<Integer> delDocIdList = orderDTO.getDelDocumentsList();
			if (delDocIdList != null && !delDocIdList.isEmpty()) {
				this.documentDao.delDocumentList(delDocIdList);
			}
			String docType = DocumentType.LICENCE_ORDER.value();
			for (Document ors : orderDTO.getDocumentList()) {
				ors.setCreatedBy(SessionUtil.getUserId());
				ors.setModifiedBy(SessionUtil.getUserId());
				ors.setCreationDate(new Date());
				ors.setModifyDate(new Date());
				ors.setRefType(docType);
				ors.setRefId(order.getOrderNo());
				this.documentDao.save(ors);
			}
		}

		if (eventFlag) {
			try {
				ApplicationEvent evt = new NewSalesOrderEvent(this, order);
				context.publishEvent(evt);
			} catch (Exception ex) {
				throw new BussinessException("SE0203", ex.getMessage());
			}
		}
		return orderDTO;
	}

	public void uploadExcelToFtp(OrderMainDTO orderDTO, String couponId,
			String remotePath) {
		try {
			if (StringUtils.isEmpty(orderDTO.getCouponId())) {
				return;
			}
			List<String> newAddCoupon = new ArrayList<String>();
			String[] newCouponIds = orderDTO.getCouponId().split(",");
			for (String coupon : newCouponIds) {
				newAddCoupon.add(coupon);
			}
			String[] oldCouponIds = couponId != null ? couponId.split(",")
					: new String[] {};
			for (String coupon : oldCouponIds) {
				if (newAddCoupon.contains(coupon)) {
					newAddCoupon.remove(coupon);
				}
			}
			FtpClient.getInstance().setWorkDirectory(remotePath);
			String random = SessionUtil.generateTempId();
			String shortName = "/tmp/genExcel/";
			String separator = System.getProperty("file.separator");
			if (separator.equals("\\")) {// windows
				shortName = "C:" + shortName;
			} else {// linux etc.

			}
			File xmlFile = new File(shortName + "coupon_" + random + ".xls");
			if (!xmlFile.getParentFile().exists()) {
				if (!xmlFile.getParentFile().mkdirs()) {
					System.out.println("create parent directory fail");
					return;
				}
			}
			create(xmlFile, "Coupon", newAddCoupon, orderDTO);
			FtpClient.getInstance().upload(shortName,
					"coupon_" + random + ".xls", "coupon_" + random + ".xls");
			xmlFile.deleteOnExit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void create(File file, String title, List<String> couponIdList,
			OrderMainDTO orderDTO) throws Exception {
		String orderNo = orderDTO.getOrderNo().toString();
		String customNo = orderDTO.getCustNo().toString();
		FileOutputStream out = new FileOutputStream(file);
		WritableWorkbook wbook = Workbook.createWorkbook(out);
		WritableSheet wsheet = wbook.createSheet(title, 0); // 工作表名称
		// 设置Excel字体
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,
				WritableFont.BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		WritableCellFormat titleFormat = new WritableCellFormat(wfont);

		String[] title2 = { "Check Number", "Customer ID", "Receipt Amount",
				"Payment Method", "Payment Date", "On Account" };
		wsheet.setColumnView(1, 100);
		wsheet.setColumnView(2, 100);
		wsheet.setColumnView(3, 100);
		wsheet.setColumnView(4, 100);
		wsheet.setColumnView(5, 100);
		wsheet.setColumnView(6, 100);
		// 设置Excel表头
		for (int i = 0; i < title2.length; i++) {

			Label excelTitle = new Label(i, 0, title2[i], titleFormat);
			wsheet.addCell(excelTitle);
		}
		int c = 1; // 用于循环时Excel的行号
		for (String couponId : couponIdList) {
			Coupon coupon = this.couponDao.getById(Integer.parseInt(couponId));
			Label content1 = new Label(0, c, orderNo);
			Label content2 = new Label(1, c, customNo);
			Label content3 = new Label(2, c, coupon != null ? coupon.getValue()
					.toString() : "");
			Label content4 = new Label(3, c, "VIP Coupon");
			Label content5 = new Label(4, c, DateUtils.formatDate2Str(
					new Date(), DateUtils.C_DATE_PATTON_DEFAULT));
			Label content6 = new Label(5, c, "Yes");
			wsheet.addCell(content1);
			wsheet.addCell(content2);
			wsheet.addCell(content3);
			wsheet.addCell(content4);
			wsheet.addCell(content5);
			wsheet.addCell(content6);
			c++;
		}
		wbook.write(); // 写入文件
		wbook.close();
		out.close();
	}

	@SuppressWarnings("unused")
	private void generateDocument(OrderMainDTO orderDTO, File file)
			throws Exception {
		String orderNo = orderDTO.getOrderNo().toString();
		String customNo = orderDTO.getCustNo().toString();
		if (StringUtils.isNotEmpty(orderDTO.getCouponId())) {
			org.dom4j.Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("Coupons");
			for (String couponId : orderDTO.getCouponId().split(",")) {
				Coupon coupon = this.couponDao.getById(Integer
						.parseInt(couponId));
				Element couponElement = rootElement.addElement("coupon");
				Element orderNoElement = couponElement
						.addElement("Check Number");
				orderNoElement.setText(orderNo);
				Element custElement = couponElement.addElement("Customer ID");
				custElement.setText(customNo);
				Element amoutElement = couponElement
						.addElement("Receipt Amount");
				amoutElement.setText(coupon != null ? coupon.getValue()
						.toString() : "");
				Element paymentMethodElement = couponElement
						.addElement("Payment Method");
				paymentMethodElement.setText("VIP Coupon");
				Element paymentDateElement = couponElement
						.addElement("Payment Date");
				paymentDateElement.setText(DateUtils.formatDate2Str(new Date(),
						DateUtils.C_DATE_PATTON_DEFAULT));
				Element onAmountElement = couponElement
						.addElement("On Account");
				onAmountElement.setText("Yes");
			}
			XMLWriter output = new XMLWriter(new FileWriter(file));
			output.write(document);
			output.close();
		}
	}

	/**
	 * 根据order和Item新增shipment和shipment line,此方法是从OrderTotalAction中拷贝过来
	 * 
	 * @author Zhang Yong
	 * @param order
	 * @param userId
	 */
	private void attachShipment(OrderMain order, Integer userId) {
		if (!OrderStatusType.CN.value().equals(order.getStatus())) {
			OrderAddress orderAddress = this.orderAddressDao
					.getAddrByOrderNoAndType(order.getOrderNo(), "SHIP_TO");
			boolean isClerk = true;
			if (orderAddress != null) {
				if (orderAddress.getCity() != null
						&& orderAddress.getCity().toLowerCase()
								.equals("nanjing")) {
					isClerk = false;
				}
			}
			List<OrderItem> items = new ArrayList<OrderItem>();
			List<OrderItem> prodItemList = orderItemDao.getProdItemList(order
					.getOrderNo());
			if (prodItemList != null && !prodItemList.isEmpty()) {
				items.addAll(prodItemList);
			}
			List<OrderItem> servItemList = orderItemDao.getServItemList(order
					.getOrderNo());
			if (servItemList != null && !servItemList.isEmpty()) {
				items.addAll(servItemList);
			}
			if (items.isEmpty()) {
				return;
			}
			List<ShipmentLine> shipmentLineList = new ArrayList<ShipmentLine>();
			Date now = new Date();
			for (OrderItem item : items) {
				ShipmentLine shipmentLine = new ShipmentLine();
				if (item.getOrderItemId() != null
						&& !OrderItemStatusType.CN.value().equals(
								item.getStatus())) {
					shipmentLine.setOrder(order);
					shipmentLine.setItemNo(item.getItemNo());
					shipmentLine.setStatus("Drafted");
					shipmentLine.setCreationDate(now);
					shipmentLine.setModifyDate(now);
					shipmentLine.setModifiedBy(userId);
					shipmentLine.setCreatedBy(userId);
					shipmentLineList.add(shipmentLine);
				}
			}
			Shipment shipment = new Shipment();
			if (!shipmentLineList.isEmpty()) {
				List<ShipmentLine> shipmentLines = this.shipmentLinesDao
						.getLineByOrderNo(order.getOrderNo());
				if (shipmentLines == null || shipmentLines.isEmpty()) {
					shipment.setShipmentNo(order.getOrderNo().toString());
					shipment.setCustNo(order.getCustNo());
					shipment.setPriority("Medium");
					shipment.setShippingType(order.getShippingType());
					shipment.setShippingRule(order.getShippingRule());
					shipment.setCurrency(order.getOrderCurrency());
					shipment.setWareHouse(warehouseDao.getById(order
							.getWarehouseId()));
					shipment.setCompanyId(order.getGsCoId());
					shipment.setStatus("Drafted");
					shipment.setShippingRule(order.getShippingRule());
					shipment.setModifiedBy(userId);
					shipment.setCreatedBy(userId);
					shipment.setCreationDate(now);
					shipment.setModifyDate(now);
					List<ShipClerkAssigned> list = null;
					if (items.get(0).getType() != null
							&& items.get(0).getClsId() != null) {
						list = shipClerkAssignDao
								.getShipClerkAssingByItemTypeAndClsId(items
										.get(0).getType(), items.get(0)
										.getClsId());
						if (list != null && !list.isEmpty()
								&& list.get(0) != null
								&& list.get(0).getShippingClerk() != null) {
							shipment.setShippingClerk(list.get(0)
									.getShippingClerk());
						}
					}
					this.shipmentDao.save(shipment);
					this.shipmentDao.flush();
					if (list != null && !list.isEmpty()) {
						if (isClerk) {
							for (ShipClerkAssigned sca : list) {
								ShippingClerkShipmentAdjustment shippingClerk = new ShippingClerkShipmentAdjustment();
								shippingClerk.setAdjustDate(new Date());
								shippingClerk.setAdjustedBy(userId);
								shippingClerk.setReasion("");
								shippingClerk.setShipmentId(shipment
										.getShipmentId());
								shippingClerk.setShippingClerk(sca
										.getShippingClerk());
								this.shippingClerkShipmentAdjustmentDao
										.save(shippingClerk);
							}
						} else {
							ShippingClerkShipmentAdjustment shippingClerk = new ShippingClerkShipmentAdjustment();
							User user = this.userDao.findByLoginName("lucyw");
							shippingClerk.setAdjustDate(new Date());
							shippingClerk.setAdjustedBy(userId);
							shippingClerk.setReasion("");
							shippingClerk.setShipmentId(shipment
									.getShipmentId());
							if (user != null) {
								shippingClerk
										.setShippingClerk(user.getUserId());
								this.shippingClerkShipmentAdjustmentDao
										.save(shippingClerk);
							}

						}
					}

				} else {
					shipment = this.shipmentDao.getshipmentDetail(shipmentLines
							.get(0).getShipments().getShipmentId());
					if (!shipment.getShippingRule().equals(
							order.getShippingRule())) {
						shipment.setShippingRule(order.getShippingRule());
						this.shipmentDao.save(shipment);
					}
				}
				for (ShipmentLine shipmentLine : shipmentLineList) {
					String isShipmentLine = "y";
					for (ShipmentLine shipmentL : shipmentLines) {
						if (shipmentLine.getOrder().getOrderNo()
								.equals(shipmentL.getOrder().getOrderNo())
								&& shipmentLine.getItemNo().equals(
										shipmentL.getItemNo())) {
							isShipmentLine = "N";
						}
					}
					if (isShipmentLine.equals("y")) {
						shipmentLine.setShipments(shipment);
						this.shipmentLinesDao.save(shipmentLine);
					}
				}
			}
		}
	}

	/**
	 * 更新Order的状态.
	 * 
	 * @param orderNo
	 * @param statusReason
	 * @param status
	 * @param loginUserId
	 */
	public void updateOrderStatus(Integer orderNo, String statusReason,
			String status, Integer loginUserId, String statusNote,
			OrderMainDTO orderDTO) {
		Date now = new Date();
		this.reserveQty(orderDTO, loginUserId);
		OrderMain order1 = this.orderDao.get(orderNo);
		String dbStatus = order1.getStatus();
		OrderMain order2 = this.orderDao.get(orderNo);
		if (status.equals(dbStatus)) {

		} else if (status.equals(OrderStatusType.CC.name())
				|| status.equals(OrderStatusType.VC.name())) {
			List<OrderItem> orderItemList = orderItemDao
					.getOrderAllItemList(orderNo);
			if (orderItemList != null && orderItemList.size() > 0) {
				for (OrderItem orderItem : orderItemList) {
					if (!orderItem.equals(OrderItemStatusType.CN.name()))
						orderItem.setStatus(status);
				}
			}
			// add by zhanghuibin
			if (status.equals(OrderStatusType.CC.name())) {
				order2.setKeyInfoChanged(0);
			}
			updateOrderProcessLog(statusReason, status, loginUserId,
					statusNote, now, order1, dbStatus);
		} else {
			updateOrderProcessLog(statusReason, status, loginUserId,
					statusNote, now, order1, dbStatus);
		}
		order2.setStatus(status);
		order2.setStatusReason(statusReason);
		this.orderDao.save(order2);
	}

	private void updateOrderProcessLog(String statusReason, String status,
			Integer loginUserId, String statusNote, Date now, OrderMain order,
			String dbStatus) {
		OrderProcessLog log = new OrderProcessLog();
		log.setOrderNo(order.getOrderNo());
		log.setPriorStat(dbStatus);
		log.setCurrentStat(status);
		log.setReason(statusReason);
		log.setNote(statusNote);
		log.setProcessDate(now);
		User processUser = new User();
		processUser.setUserId(loginUserId);
		log.setProcessedBy(processUser);
		this.orderProcessLogDao.save(log);
	}

	/**
	 * 保存或新
	 * 
	 * @param orderTemplate
	 * @param userId
	 * @param overrideFlag
	 */
	public void saveOrderTemplate(OrderTemplate orderTemplate, Integer userId,
			String overrideFlag) {
		OrderTemplate dbTmpl = this.orderTemplateDao.getOrderTemplate(userId,
				orderTemplate.getTmplName());
		if (dbTmpl != null) {
			this.orderTemplateDao.getSession().evict(dbTmpl);
			System.out.println(">>>>>>>>>>>>>>>overrrideFlag=" + overrideFlag);
			if (overrideFlag != null && overrideFlag.equals("Y")) {
				orderTemplate.setTmplId(dbTmpl.getTmplId());
			} else {
				throw new BussinessException(
						BussinessException.ORDER_TMPL_DULP_NAME_CODE);
			}
		}
		Integer tmplCount = this.getMyTemplateCount(userId);
		if (overrideFlag != null && overrideFlag.equals("Y")) {
			if (tmplCount.intValue() > 6) {
				throw new BussinessException(
						BussinessException.ERR_ORDERTMPL_COUNT, 6 + "");
			}
		} else {
			if (tmplCount.intValue() >= 6) {
				throw new BussinessException(
						BussinessException.ERR_ORDERTMPL_COUNT, 6 + "");
			}
		}
		Date now = new Date();
		orderTemplate.setCreatedBy(userId);
		orderTemplate.setModifiedBy(userId);
		orderTemplate.setCreationDate(now);
		orderTemplate.setModifyDate(now);
		orderTemplate.setOwner(userId);
		this.orderTemplateDao.save(orderTemplate);
	}

	@Transactional(readOnly = true)
	public List<ItemDiscountDTO> getItemDiscount(
			List<ItemDiscountDTO> itemList, String promotionCode) {
		List<ItemDiscountDTO> itemDiscountDTOList = new ArrayList<ItemDiscountDTO>();
		if (itemList != null) {
			BigDecimal zero = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			Double sum = 0.0;
			for (ItemDiscountDTO item : itemList) {

				Double amount = item.getAmount().doubleValue();
				sum += amount;
			}
			Promotion promotion = promotionDao.findUniqueBy("prmtCode",
					promotionCode);
			String discType = promotion.getDiscType();
			BigDecimal discPercent = promotion.getDiscPercent();
			BigDecimal orderTotalMin = promotion.getOrderTotalMin1();
			if (discType.equals("1")
					&& orderTotalMin.compareTo(new BigDecimal(sum)) < 0) {
				for (ItemDiscountDTO item : itemList) {
					ItemDiscountDTO itemDiscountDTO = new ItemDiscountDTO();
					itemDiscountDTO.setCatalogNo(item.getCatalogNo());
					if (item.getStatus().equals(OrderItemStatusType.CN.name())) {
						itemDiscountDTO.setDiscount(zero);
					} else {
						BigDecimal discountBD = discPercent.multiply(
								item.getAmount()).divide(new BigDecimal(100));
						itemDiscountDTO.setDiscount(discountBD.setScale(2,
								BigDecimal.ROUND_HALF_UP));
					}
					itemDiscountDTO.setItemId(item.getItemId());
					itemDiscountDTO.setAmount(item.getAmount());
					itemDiscountDTOList.add(itemDiscountDTO);
				}
			} else {
				throw new BussinessException(
						BussinessException.INF_PROMOTION_CANNOT_APPLY);
			}
		}
		return itemDiscountDTOList;
	}

	/**
	 * 判断quantity 和 promotion是否可以更改
	 * 
	 * @param promotionCode
	 * @return
	 */
	public boolean checkOrderTotal(List<Double> amountList, String promotionCode) {
		Double sum = 0.0;
		for (Double amount : amountList) {
			sum += amount;
		}
		if (StringUtils.isNotEmpty(promotionCode)) {
			Promotion promotion = promotionDao.findUniqueBy("prmtCode",
					promotionCode);

			String discType = promotion.getDiscType();
			String[] discTypes = discType == null ? new String[] { "0", "0",
					"0", "0" } : discType.split(";");
			if (discTypes.length == 1) {
				discTypes = new String[] { discTypes[0], "0", "0", "0" };
			}
			BigDecimal orderTotalMin1 = promotion.getOrderTotalMin1();
			if (discTypes[0].equals("1")
					&& orderTotalMin1.compareTo(new BigDecimal(sum)) < 0) {
				return true;
			}
			BigDecimal orderTotalMin2 = promotion.getOrderTotalMin2();
			if (discTypes[1].equals("1")
					&& orderTotalMin2.compareTo(new BigDecimal(sum)) < 0) {
				return true;
			}
		}
		return false;

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String calGiftItemDiscount(List<OrderItemDTO> itemList,
			String orderNo, String promotionCode, Integer custNo,
			Integer warehouseId) throws Exception {
		if (promotionCode == null || StringUtils.isEmpty(promotionCode)) {
			return "PromotionCode is null";
		}
		int itemSize = 0;
		Double sum = 0.0;// 所有item的discount的总和
		Promotion promotion = promotionDao.findUniqueBy("prmtCode",
				promotionCode);
		for (OrderItemDTO item : itemList) {
			itemSize++;
			Double amount = item.getUnitPrice().doubleValue()
					* item.getQuantity();
			sum += amount;
		}
		if (promotion == null || promotion.getDiscType() == null) {
			return "Promotion is not apply!";
		}
		String[] discTypes = promotion.getDiscType().split(";");
		if (discTypes.length != 4) {
			return "Promotion is not apply!";
		}
		if ("1".equals(discTypes[2])) {
			BigDecimal orderTotalMin3 = promotion.getOrderTotalMin3();
			if (orderTotalMin3.compareTo(new BigDecimal(sum)) < 0) {
				OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
						SessionConstant.Order.value(), orderNo);
				if (order.getOrderPromotion() != null
						&& StringUtils.isNotBlank(order.getOrderPromotion()
								.getPrmtCode())) {
					return null;
				}
				OrderItemDTO orderItemDTO = createOrderItemDTO(
						promotion.getDiscProd(), custNo,
						promotion.getDiscProdQty(), itemSize, warehouseId);
				if (promotion.getDiscPrice() != null) {
					orderItemDTO.setUnitPrice(promotion.getDiscPrice());
					orderItemDTO.setAmount(new BigDecimal(ArithUtil.mul(
							orderItemDTO.getUnitPrice().doubleValue(), Double
									.valueOf(String.valueOf(orderItemDTO
											.getQuantity())))));
				} else if (promotion.getSpecialDiscPercent() != null) {
					orderItemDTO.setDiscount(promotion
							.getSpecialDiscPercent()
							.multiply(orderItemDTO.getAmount())
							.divide(new BigDecimal(100), 2,
									BigDecimal.ROUND_HALF_UP));
				} else {
					orderItemDTO.setUnitPrice(new BigDecimal(0));
					orderItemDTO.setAmount(new BigDecimal(0));
				}
				Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
						.getRow(SessionConstant.OrderItemList.value(), orderNo);
				itemMap.put(SessionUtil.generateTempId(), orderItemDTO);
				SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
						orderNo, itemMap);
				return null;
			} else {
				return "Failed to applay the promotion.";
			}
		} else {
			return "Failed to applay the promotion.";
		}
	}

	@Transactional(readOnly = true)
	public List<ProductItemPriceDTO> getItemTaxForCal(
			List<ProductItemPriceDTO> itemList, String taxCountryType) {
		List<ProductItemPriceDTO> itemDTOList = new ArrayList<ProductItemPriceDTO>();
		if (itemList != null && itemList.size() > 0) {
			for (ProductItemPriceDTO productItemPriceDTO : itemList) {
				Double amount = productItemPriceDTO.getAmount();
				String state = productItemPriceDTO.getState();
				Double taxRate = this.taxRateDao.getCountryStateRate(
						taxCountryType, state);
				if (taxRate == null
						|| "N".equalsIgnoreCase(productItemPriceDTO
								.getTaxStatus())) {
					taxRate = 0.0;
				}
				if ("JP".equalsIgnoreCase(taxCountryType)) {
					taxRate = 5.0;
				}
				Double tax = new BigDecimal(amount)
						.multiply(new BigDecimal(taxRate))
						.divide(new BigDecimal(100))
						.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				productItemPriceDTO.setTax(tax);
				itemDTOList.add(productItemPriceDTO);
			}
		}
		return itemDTOList;
	}

	@Transactional(readOnly = true)
	public List<PaymentVoucherDTO> getPayInfoList(Integer orderNo) {
		List<PaymentVoucherDTO> dtoList = new ArrayList<PaymentVoucherDTO>();
		List<PaymentVoucher> paymentPlanList = paymentVoucherDao
				.getPaymentVoucherList(orderNo);
		if (paymentPlanList != null && !paymentPlanList.isEmpty()) {
			for (PaymentVoucher payment : paymentPlanList) {
				PaymentVoucherDTO dto = this.dozer.map(payment,
						PaymentVoucherDTO.class);
				List<Document> documentList = this.documentDao.getDocumentList(
						payment.getVoucherId(), DocumentType.ORDER_PO);
				if (documentList != null && !documentList.isEmpty()) {
					dto.setDocument(documentList.get(0));
				}
				dto.setCcExprDateStr(DateUtils.formatDate2Str(
						dto.getCcExprDate(), "yyyy-MM-dd"));
				dto.setPoDueDateStr(DateUtils.formatDate2Str(
						dto.getPoDueDate(), "yyyy-MM-dd"));
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Transactional(readOnly = true)
	public PaymentVoucherDTO getPaymentVoucherList(Integer orderNo) {
		List<PaymentVoucher> paymentPlanList = paymentVoucherDao
				.getPaymentVoucherList(orderNo);
		PaymentVoucherDTO dto = null;
		if (paymentPlanList != null && paymentPlanList.size() > 0) {
			dto = this.dozer.map(paymentPlanList.get(0),
					PaymentVoucherDTO.class);
		}
		return dto;
	}

	@Transactional(readOnly = true)
	public Boolean isPoCCexixt(Integer orderNo) {
		return paymentVoucherDao.isPoCCexixt(orderNo);
	}

	/**
	 * 判断邮件是否发送 Zhang Yong
	 * 
	 * @param orderNo
	 * @return
	 */
	public Boolean isCustMailSent(Integer orderNo, String mailType) {
		OrderMail mail = null;
		if (orderNo != null
				&& Constants.CUST_CONFIRM_EMAIL.equalsIgnoreCase(mailType)) {
			Date lastConfirmDate = this.orderProcessLogDao
					.getOrderLastConfirmDate(orderNo);
			if (lastConfirmDate != null) {
				List<OrderMail> mailList = this.orderMailDao
						.getOrderCompleteMail(orderNo, lastConfirmDate,
								Constants.CUST_CONFIRM_EMAIL);
				if (mailList != null && !mailList.isEmpty()) {
					mail = mailList.get(0);
				}
			} else {
				mail = this.orderMailDao.getCustConfirmCompleteMail(orderNo);
			}
		} else if (orderNo != null
				&& Constants.ORDER_CHANGE_NOTIFICATION
						.equalsIgnoreCase(mailType)) {
			List<OrderProcessLog> allConfirmRecords = this.orderProcessLogDao
					.getOrderStatus(orderNo, OrderStatusType.CC.value());
			if (allConfirmRecords != null && !allConfirmRecords.isEmpty()) {
				List<OrderMail> mailList = this.orderMailDao
						.getOrderCompleteMail(orderNo, allConfirmRecords.get(0)
								.getProcessDate(),
								Constants.ORDER_CHANGE_NOTIFICATION);
				if (mailList != null && !mailList.isEmpty()) {
					mail = mailList.get(0);
				}
			} else {
				return true;
			}
		}
		if (mail == null) {
			return false;
		} else {
			return true;
		}
	}

	@Transactional(readOnly = true)
	public PaymentVoucherDTO getPayInfo(Integer voucherId) {
		PaymentVoucher paymentVoucher = paymentVoucherDao.get(voucherId);
		if (paymentVoucher == null) {
			return null;
		}
		PaymentVoucherDTO dto = this.dozer.map(paymentVoucher,
				PaymentVoucherDTO.class);
		List<Document> documentList = this.documentDao.getDocumentList(
				paymentVoucher.getVoucherId(), DocumentType.ORDER_PO);
		if (documentList != null && !documentList.isEmpty()) {
			dto.setDocument(documentList.get(0));
		}
		dto.setCcExprDateStr(DateUtils.formatDate2Str(dto.getCcExprDate(),
				"yyyy-MM-dd"));
		dto.setPoDueDateStr(DateUtils.formatDate2Str(dto.getPoDueDate(),
				"yyyy-MM-dd"));
		return dto;
	}

	@Transactional(readOnly = true)
	public Integer getPreferPayId(List<PaymentVoucherDTO> orderPaymentPlanList,
			String preferPaymentMthd) {
		if (orderPaymentPlanList != null) {
			return paymentVoucherDao.getFirstPaymentPlan(preferPaymentMthd);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Set<Integer> getOrderNoSetByPoNo(Page<PaymentVoucher> paymentPage,
			List<PropertyFilter> filterList) {
		Set<Integer> retSet = new HashSet<Integer>();
		Page<PaymentVoucher> page = paymentVoucherDao.searchPayment(
				paymentPage, filterList);
		List<PaymentVoucher> pageList = page.getResult();
		if (pageList != null && !pageList.isEmpty()) {
			for (int i = 0; i < pageList.size(); i++) {
				retSet.add(pageList.get(i).getOrderNo());
			}
		}
		return retSet;
	}

	public void sendOrderMailJob() {
		List<OrderMail> orderMailList = null;
		orderMailList = orderMailDao.queryMailSend();
		if (orderMailList != null && !orderMailList.isEmpty()) {
			User techAccountUser = orderDao.getUserByCustNo(orderMailList
					.get(0).getOrderNo());
			for (OrderMail orderMail : orderMailList) {
				String mailTo = orderMail.getReceipt();
				String content = orderMail.getContent();
				String subject = orderMail.getSubject();
				List<Document> docList = null;
				if (orderMail.getDocFlag().equals("Y")) {
					Integer orderMailId = orderMail.getId();
					docList = documentDao.getDocument(
							DocumentType.ORDER_INST_MAIL.value(), orderMailId);
				}
				mimeMailService.sendMails(mailTo, subject, content,
						techAccountUser.getEmail(), docList);
				orderMail.setSendDate(new Date());
				orderMail.setStatus("COMPLETE");
				orderMailDao.save(orderMail);
				logger.debug("The order mail has sended");
			}
		}
	}

	/**
	 * 获得一个Order的Order-Return list(其中status 为'UNPROCESSED').
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderReturnDTO> getUnProcessedReturnList(Integer orderNo) {
		List<OrderReturnDTO> dtoList = new ArrayList<OrderReturnDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo", orderNo);
		filterList.add(quoteFilter);
		PropertyFilter statusFilter = new PropertyFilter("EQS_status",
				"UNPROCESSED");
		filterList.add(statusFilter);
		List<OrderReturn> orList = this.orderReturnDao.find(filterList);
		if (orList == null || orList.isEmpty()) {
			return dtoList;
		}
		for (OrderReturn orderReturn : orList) {
			OrderReturnDTO dto = this.dozer.map(orderReturn,
					OrderReturnDTO.class);
			if (orderReturn.getCreatedBy() != null) {
				User creator = this.userDao.getById(orderReturn.getCreatedBy());
				if (creator != null) {
					dto.setCreateUser(creator.getLoginName());
				}
			}
			if (orderReturn.getProcessedBy() != null) {
				User temp = this.userDao.getById(orderReturn.getProcessedBy());
				if (temp != null) {
					dto.setProcessUser(temp.getLoginName());
				}
			}
			List<OrderReturnItem> itemList = this.orderReturnItemDao.findBy(
					"rmaNo", dto.getRmaNo());
			if (itemList != null) {
				List<OrderReturnItemDTO> itemDTOList = new ArrayList<OrderReturnItemDTO>();
				for (OrderReturnItem returnItem : itemList) {
					OrderReturnItemDTO itemDTO = this.dozer.map(returnItem,
							OrderReturnItemDTO.class);
					Product temp = this.productDao.findUniqueBy("catalogNo",
							returnItem.getCatalogNo());
					if (temp != null) {
						itemDTO.setName(temp.getName());
						itemDTO.setDescription(temp.getLongDesc());
					}
					itemDTOList.add(itemDTO);
				}
				dto.setReturnItemList(itemDTOList);
			}
			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * 获得一个Order的Order-Return(其中status 为'UNPROCESSED').
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public OrderReturnDTO getUnProcessedReturn(Integer rmaNo) {
		OrderReturnDTO dto = null;
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_rmaNo", rmaNo);
		filterList.add(quoteFilter);
		PropertyFilter statusFilter = new PropertyFilter("EQS_status",
				"UNPROCESSED");
		filterList.add(statusFilter);
		List<OrderReturn> orList = this.orderReturnDao.find(filterList);
		if (orList == null || orList.isEmpty()) {
			return null;
		}
		OrderReturn orderReturn = orList.get(0);
		this.orderReturnDao.getSession().evict(orderReturn);// 清除session
		dto = this.dozer.map(orderReturn, OrderReturnDTO.class);
		if (orderReturn.getCreatedBy() != null) {
			User creator = this.userDao.getById(orderReturn.getCreatedBy());
			if (creator != null) {
				dto.setCreateUser(creator.getLoginName());
			}
		}
		if (orderReturn.getProcessedBy() != null) {
			User temp = this.userDao.getById(orderReturn.getProcessedBy());
			if (temp != null) {
				dto.setProcessUser(temp.getLoginName());
			}
		}
		List<OrderReturnItem> itemList = this.orderReturnItemDao.findBy(
				"rmaNo", dto.getRmaNo());
		if (itemList != null) {
			List<OrderReturnItemDTO> itemDTOList = new ArrayList<OrderReturnItemDTO>();
			for (OrderReturnItem returnItem : itemList) {
				OrderReturnItemDTO itemDTO = this.dozer.map(returnItem,
						OrderReturnItemDTO.class);
				Product temp = this.productDao.findUniqueBy("catalogNo",
						returnItem.getCatalogNo());
				if (temp != null) {
					itemDTO.setName(temp.getName());
					itemDTO.setDescription(temp.getLongDesc());
				}
				itemDTOList.add(itemDTO);
				this.orderReturnItemDao.getSession().evict(returnItem);// 清除session
			}
			dto.setReturnItemList(itemDTOList);
		}
		return dto;
	}

	/**
	 * Process OrderReturn, 实际操作为更新status为'PROCESSED'.
	 * 并把OrderReturmItem生成OrderItem。
	 * 
	 * @param returnDTO
	 * @param userId
	 */
	public List<OrderItemDTO> processOrderReturn(
			final OrderReturnDTO returnDTO, Integer warehouseId, Integer userId) {
		this.saveOrderReturn(returnDTO, userId);
		this.orderReturnItemDao.processReturnItemList(returnDTO.getRmaNo(), userId);
		this.orderReturnDao.processOrderReturnList(returnDTO.getRmaNo(), userId);
		List<OrderItemDTO> itemList = new ArrayList<OrderItemDTO>();
		List<OrderReturnItemDTO> itemDTOList = returnDTO.getReturnItemList();
		if (itemDTOList != null) {
			Integer maxItemNo = this.orderItemDao.getLastItemByItemNo(returnDTO.getOrderNo()).getItemNo();
			for (OrderReturnItemDTO itemDTO : itemDTOList) {
				maxItemNo += 1;
				OrderItem srcItem = this.orderItemDao.getOrderItem(returnDTO.getOrderNo(), itemDTO.getItemNo());
				this.orderItemDao.getSession().evict(srcItem);
				OrderItem item = srcItem;
				item.setQuantity(itemDTO.getReturnQty());
				item.setSize(itemDTO.getReturnSize().doubleValue());
				item.setBasePrice(srcItem.getBasePrice().negate());
				item.setUnitPrice(srcItem.getUnitPrice() * -1);
				BigDecimal temp = BigDecimal.valueOf(srcItem.getAmount() + srcItem.getTax() - srcItem.getDiscount());
				double amount = itemDTO.getReturnQty()* itemDTO.getReturnSize().multiply(temp).doubleValue()
						/ (srcItem.getQuantity() * srcItem.getSize());
				item.setAmount(amount * -1);
				item.setDiscount(itemDTO.getDiscount().negate().doubleValue());
				item.setTax(itemDTO.getTax().negate().doubleValue());
				item.setStatus("RT");
				// 重写以下属性.
				item.setOrderItemId(null);
				item.setItemNo(maxItemNo);
				item.setCreatedBy(userId);
				item.setModifiedBy(userId);
				item.setCreationDate(new Date());
				item.setModifyDate(new Date());
				this.orderItemDao.save(item);// 从OrderReturmItem生成OrderItem.

				OrderMain order = this.orderDao.getById(returnDTO.getOrderNo());
				PbCurrency pbCurrency = this.currencyDao.getByCurrencyCode(order.getOrderCurrency());
				String currencySymbolText = null;
				Integer precision = null;
				if (pbCurrency != null) {
					currencySymbolText = pbCurrency.getSymbol();
					precision = pbCurrency.getPrecision();
				}
				OrderItemDTO dto = this.dozer.map(item, OrderItemDTO.class);
				// 设置页面中显示用的otherInfo.
				dto.setOtherInfo(getItemOtherInfo(item.getType(), item.getStatus(), item.getCatalogNo(), item.getStatusReason(), null, order.getCustNo()));
				// 设置页面显示用的catalog
				List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
				PropertyFilter quoteFilter = new PropertyFilter("EQS_catalogId", item.getCatalogId());
				filterList.add(quoteFilter);
				List<Catalog> catalogList = this.catalogDao.find(filterList);
				if (catalogList != null && !catalogList.isEmpty()) {
					Catalog catalogTemp = catalogList.get(0);
					dto.setCatalogText(catalogTemp.getCatalogId() + " : " + catalogTemp.getCatalogName());
				}
				// 设置页面中显示用的type
				if (item.getType().equals(QuoteItemType.PRODUCT.value())) {
					ProductClass pdtClass = this.productClassDao.getById(item.getClsId());
					if (pdtClass != null) {
						dto.setTypeText(item.getType() + "-" + pdtClass.getName());
					}
				} else {
					ServiceClass servClass = this.serviceClassDao.getById(item.getClsId());
					if (servClass != null) {
						dto.setTypeText(item.getType() + "-" + servClass.getName());
					}
				}
				// 设置页面中显示用的status
				dto.setStatusText(this.getOrderItemStatusText(item.getStatus()));
				dto.setUpSymbol(currencySymbolText);
				dto.setUpPrecision(precision);
				itemList.add(dto);

			}
		}
		return itemList;
	}

	/**
	 * 删除OrderReturn, 实际操作为更新status为'INVALID'. (先更新其基本信息及其item list信息).
	 * 
	 * @param userId
	 */
	public void delOrderReturn(final OrderReturnDTO returnDTO, Integer userId) {
		this.saveOrderReturn(returnDTO, userId);
		this.orderReturnDao.delOrderReturnList(returnDTO.getRmaNo(), userId);
	}

	/**
	 * 保存或更新OrderReturn及其item list.
	 * 
	 * @param returnDTO
	 * @param userId
	 */
	public OrderReturn saveOrderReturn(final OrderReturnDTO returnDTO,
			Integer userId) {
		if (returnDTO.getDelItemIdList() != null
				&& returnDTO.getDelItemIdList().get(0) != null) {
			this.orderReturnItemDao.delItemList(returnDTO.getDelItemIdList());
		}
		Date now = new Date();
		OrderReturn orderReturn = this.dozer.map(returnDTO, OrderReturn.class);
		orderReturn.setCreatedBy(userId);
		orderReturn.setModifiedBy(userId);
		orderReturn.setCreationDate(now);
		orderReturn.setModifyDate(now);
		if (SoapUtil.getIntegerFromSOAP(orderReturn.getRmaNo()) == null) {
			orderReturn.setStatus("UNPROCESSED");
		}
		this.orderReturnDao.save(orderReturn);
		List<OrderReturnItemDTO> itemDTOList = returnDTO.getReturnItemList();
		if (itemDTOList != null) {
			for (OrderReturnItemDTO itemDTO : itemDTOList) {
				OrderReturnItem item = this.dozer.map(itemDTO,
						OrderReturnItem.class);
				item.setRmaNo(orderReturn.getRmaNo());
				item.setCreatedBy(userId);
				item.setModifiedBy(userId);
				item.setCreationDate(now);
				item.setModifyDate(now);
				this.orderReturnItemDao.save(item);
			}
		}
		return orderReturn;
	}

	/**
	 * 获得已付总和
	 * 
	 * @param orderNo
	 * @param toCurrency
	 * @param orderDate
	 * @return
	 */
	/*
	 * public BigDecimal getTotalPaymentsAmount(final Integer orderNo, final
	 * String toCurrency, final Date orderDate) { int scale = 2; if
	 * (CurrencyType.JPY.value().equalsIgnoreCase(toCurrency)) { scale = 0; }
	 * List<ArPaymentAmountDTO> paymentAmountList = arInvoiceDao
	 * .getAmountByOrder(orderNo); BigDecimal paymentsBD = new BigDecimal(0.0);
	 * BigDecimal rateBD = new BigDecimal(0.0); if (paymentAmountList != null &&
	 * paymentAmountList.size() > 0) { for (ArPaymentAmountDTO arPayment :
	 * paymentAmountList) { String currency = arPayment.getCurrency();
	 * BigDecimal arAmount = arPayment.getAmount(); Double rate =
	 * exchRateByDateDao.getCurrencyRate(currency, toCurrency, orderDate); if
	 * (rate != null) { rateBD = new BigDecimal(rate); paymentsBD =
	 * paymentsBD.add(arAmount.multiply(rateBD)); } } } return
	 * paymentsBD.setScale(scale, BigDecimal.ROUND_HALF_UP); }
	 */

	public BigDecimal getTotalPaymentsAmount_new(final Integer orderNo,
			final String toCurrency, final Date orderDate) {
		int scale = 2;
		if (CurrencyType.JPY.value().equalsIgnoreCase(toCurrency)) {
			scale = 0;
		}
		List<ArPaymentAmountDTO> paymentAmountList = arInvoicePaymentDao
				.getAmountByOrder(orderNo);
		BigDecimal paymentsBD = new BigDecimal(0.0);
		BigDecimal rateBD = new BigDecimal(0.0);
		if (paymentAmountList != null && paymentAmountList.size() > 0) {
			for (ArPaymentAmountDTO arPayment : paymentAmountList) {
				String currency = arPayment.getCurrency();
				BigDecimal arAmount = arPayment.getAmount();
				Double rate = exchRateByDateDao.getCurrencyRate(currency,
						toCurrency, orderDate);
				if (rate != null) {
					rateBD = new BigDecimal(rate);
					paymentsBD = paymentsBD.add(arAmount.multiply(rateBD));
				}
			}
		}
		return paymentsBD.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 获得一个customer有效的CreditCard list.
	 * 
	 * @param custNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CreditCard> getCustActiveCardList(Integer custNo) {
		List<CreditCard> cardList = this.creditCardDao
				.getCustActiveCardList(custNo);
		if (cardList == null) {
			return new ArrayList<CreditCard>();
		}
		return cardList;
	}

	/**
	 * 保存Order的同时保存该Order所属customer的creditcard list.
	 * 
	 * @param order
	 * @param cardList
	 * @param delIdList
	 * @param userId
	 */
	public void attachCustCardList(final OrderMain order,
			List<CreditCard> cardList, List<Integer> delIdList, Integer userId) {
		if (delIdList != null && !delIdList.isEmpty()) {
			for (Integer delId : delIdList) {
				this.creditCardDao.delCard(userId, delId);
			}
		}
		if (cardList == null || cardList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (CreditCard card : cardList) {
			card.setCustNo(order.getCustNo());
			card.setCreatedBy(userId);
			card.setModifiedBy(userId);
			card.setCreationDate(now);
			card.setModifyDate(now);
			card.setStatus("ACTIVE");
			this.creditCardDao.save(card);
		}
	}

	@Transactional(readOnly = true)
	public Float calculateReturnRefund(final Integer returnQty,
			final Double returnSize, final Integer orderNo,
			final Integer orderItemNo, final Integer precision) {
		OrderItem orderItem = orderItemDao.getOrderItem(orderNo, orderItemNo);
		Double amount = orderItem.getAmount();
		Double tax = orderItem.getTax();
		Double discount = orderItem.getDiscount();
		Integer quantity = orderItem.getQuantity();
		Double size = orderItem.getSize();
		Double refund = returnQty * returnSize * (amount + tax - discount)
				/ (quantity * size);
		BigDecimal bdRefund = new BigDecimal(refund);
		if (precision == null) {
			return bdRefund.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		} else {
			return bdRefund.setScale(precision, BigDecimal.ROUND_HALF_UP)
					.floatValue();
		}
	}

	/**
	 * 获得一个Order的Order-Return list(其中status 为'PROCESSED').
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderReturnItemDTO> getProcessedReturnItemList(Integer orderNo) {
		List<OrderReturnItemDTO> itemDTOList = new ArrayList<OrderReturnItemDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo", orderNo);
		filterList.add(quoteFilter);
		PropertyFilter statusFilter = new PropertyFilter("EQS_status",
				"PROCESSED");
		filterList.add(statusFilter);
		List<OrderReturn> orList = this.orderReturnDao.find(filterList);
		if (orList == null || orList.isEmpty()) {
			return null;
		}
		for (OrderReturn orderReturn : orList) {
			OrderReturnDTO dto = this.dozer.map(orderReturn,
					OrderReturnDTO.class);
			List<OrderReturnItem> itemList = this.orderReturnItemDao.findBy(
					"rmaNo", dto.getRmaNo());
			if (itemList != null) {
				for (OrderReturnItem returnItem : itemList) {
					OrderReturnItemDTO itemDTO = this.dozer.map(returnItem,
							OrderReturnItemDTO.class);
					Product temp = this.productDao.findUniqueBy("catalogNo",
							returnItem.getCatalogNo());
					if (temp != null) {
						itemDTO.setName(temp.getName());
						itemDTO.setDescription(temp.getLongDesc());
					}
					itemDTOList.add(itemDTO);
				}
			}
		}
		return itemDTOList;
	}

	/**
	 * 根据多个Item进行按照业务规则生成ShippingPackage. 如果orderNo为null, 则生成临时的， 否则要保存进数据库.
	 * 
	 * @param itemDTOList
	 */
	public List<OrderPackageDTO> calShippingPackage(
			final List<OrderItemDTO> itemDTOList, Integer orderNo,
			Integer userId, Integer companyId, Integer warehouseId) {
		// 判断是否能自动计算生成package.
		this.checkAutoCalPackage(itemDTOList, warehouseId, companyId);
		// 对OrderItem list进行分组.
		Map<String, List<OrderItemDTO>> packageMap = new HashMap<String, List<OrderItemDTO>>();
		int size = itemDTOList.size();
		for (int i = 0; i < size; i++) {
			OrderItemDTO itemDTO = itemDTOList.get(i);
			String key = itemDTO.getType() + itemDTO.getClsId();
			if (QuoteItemType.PRODUCT.value().equals(itemDTO.getType())) {
				if (itemDTO.getShipSchedule() == null) {
					itemDTO.setShipSchedule(productDao.getProductByCatalogNo(
							itemDTO.getCatalogNo()).getLeadTime());
				}
			} else {
				if (itemDTO.getShipSchedule() == null) {
					itemDTO.setShipSchedule(serviceDao.getServiceByCatalogNo(
							itemDTO.getCatalogNo()).getLeadTime());
				}
				if (StringUtils.isNotBlank(itemDTO.getParentId())) {
					String parentKey = getParentKey(orderNo,
							itemDTO.getParentId());
					if (StringUtils.isNotBlank(parentKey)) {
						key = parentKey;
					} else {
						break;
					}
				}
			}
			if (packageMap.containsKey(key)) {// 更改已存在Package中的OrderItem list.
				List<OrderItemDTO> packageItemList = packageMap.get(key);
				packageItemList.add(itemDTO);
				packageMap.put(key, packageItemList);
			} else {// 新增一个Package.
				List<OrderItemDTO> packageItemList = new ArrayList<OrderItemDTO>();
				packageItemList.add(itemDTO);
				packageMap.put(key, packageItemList);
			}
		}
		// 先产生内存中的各单个Package及PackageItem list.
		List<PackageHelper> packageTempList = new ArrayList<PackageHelper>();
		Iterator<Entry<String, List<OrderItemDTO>>> it = packageMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, List<OrderItemDTO>> entry = it.next();
			List<OrderItemDTO> packageItemList = entry.getValue();
			PackageHelper packageHelper = this.genPackage(packageItemList,
					warehouseId);
			packageTempList.add(packageHelper);
		}
		// 产生内存中已排序的多个Package list.
		List<OrderPackageDTO> packageList = this.genPackageList(
				packageTempList, orderNo, companyId, warehouseId, null);
		return packageList;
	}

	/**
	 * 获取最高父级的Type和ClsId
	 * 
	 * @author Zhang Yong
	 * @param orderNo
	 * @param parentId
	 * @return
	 */
	private String getParentKey(Integer orderNo, String parentId) {
		String key = null;
		@SuppressWarnings("unchecked")
		Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(),
						orderNo.toString());
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO itemDTO = entry.getValue();
			String id = entry.getKey();
			if (parentId.equals(id)
					&& !OrderItemStatusType.CN.value().equalsIgnoreCase(
							itemDTO.getStatus())) {
				if (StringUtils.isNotBlank(itemDTO.getParentId())) {
					key = getParentKey(orderNo, itemDTO.getParentId());
					return key;
				} else {
					key = itemDTO.getType() + itemDTO.getClsId();
					return key;
				}
			}
		}
		return key;
	}

	/**
	 * 按照顺序保存.
	 * 
	 * @param packageTempList
	 * @param orderNo
	 */
	public ShipPackage genPackageListPublic(
			List<PackageHelper> packageTempList, Integer companyId,
			Integer warehouseId, String country, String currency, Double rate,
			Date exchRateDate, Double orderAmount, String baseCurrency) {

		int packageSeq = 1;
		BigDecimal carrCharges = new BigDecimal(0);
		BigDecimal handlingFee = new BigDecimal(0);
		for (PackageHelper packageTemp : packageTempList) {
			List<OrderItemDTO> pkgOrderItemList = packageTemp
					.getOrderPackageItemList();
			int totalQty = 0;
			for (OrderItemDTO pkgItem : pkgOrderItemList) {
				totalQty += pkgItem.getQuantity();
			}
			Double handlingRate = null;
			if (!StringUtils.isEmpty(currency)
					&& !StringUtils.isEmpty(currency) && exchRateDate != null) {
				rate = exchRateByDateDao.getCurrencyRate(baseCurrency,
						currency, exchRateDate);
				handlingRate = exchRateByDateDao.getCurrencyRate(currency,
						baseCurrency, exchRateDate);
			}
			System.out.println("rate=" + rate);
			System.out.println("handlingRate=" + handlingRate);
			System.out.println("currency=" + currency);
			System.out.println("baseCurrency=" + baseCurrency);
			System.out.println("exchRateDate=" + exchRateDate);
			if (rate == null || handlingRate == null) {
				throw new BussinessException(
						BussinessException.EXCH_RATE_IS_NULL);
			}
			orderAmount = orderAmount * handlingRate;
			OrderItemDTO groupFirst = pkgOrderItemList.get(0);
			OrderPackageDTO orderPackage = packageTemp.getOrderPackageDTO();
			// orderPackage.setOrderNo(orderNo);
			orderPackage.setBillableWeight(orderPackage.getActualWeight());
			orderPackage.setShiptoAddress(getPkgShiptoAddress(groupFirst));
			orderPackage.setWarehouseName(this.warehouseDao
					.getById(warehouseId).getName());
			// 获得Package's zone.
			String zipCode = groupFirst.getShipToAddress().getZipCode();
			ShipZone shipZone = this.shipZoneDao.getShipZone(
					groupFirst.getShipMethod(), warehouseId, zipCode, country);
			if (shipZone != null) {
				orderPackage.setZone(shipZone.getZoneCode());
			}
			orderPackage.setPkgBatchSeq(1);
			orderPackage.setPkgBatchCount(1);
			orderPackage.setShipMethod(groupFirst.getShipMethod());
			orderPackage.setPackageType(groupFirst.getPackageType());
			// 获得Package's baseCharge.
			System.out.println("1111111111");
			System.out.println(groupFirst.getShipMethod());// 1
			System.out.println(warehouseId);// 1
			System.out.println(orderPackage.getBillableWeight());// 15
			System.out.println(orderPackage.getZone());// -11
			System.out.println(groupFirst.getClsId());// 16
			System.out.println(groupFirst.getType());// service
			System.out.println(totalQty);// 12
			System.out.println(orderAmount);// 6000
			BigDecimal charge = this.orderQuoteUtil.getShippingFee(
					groupFirst.getShipMethod(), warehouseId,
					orderPackage.getBillableWeight(), orderPackage.getZone(),
					groupFirst.getClsId(), groupFirst.getType(), totalQty,
					orderAmount, packageSeq);
			System.out.println("Charge=" + charge);
			orderPackage.setBaseCharge(charge.doubleValue());
			orderPackage.setDeliveryConfirmFee(0.0);
			orderPackage.setPackagingFee(0.0);
			// 获得 actlCarrCharge.
			Double actlCarrCharge = BigDecimal
					.valueOf(orderPackage.getBaseCharge())
					.add(BigDecimal.valueOf(orderPackage
							.getDeliveryConfirmFee()))
					.add(BigDecimal.valueOf(orderPackage.getPackagingFee()))
					.doubleValue();
			orderPackage.setActlCarrCharge(actlCarrCharge);
			orderPackage.setInsuranceCharge(0.0);
			orderPackage.setAdtlCustomerCharge(0.0);
			// 获得 carrierCharge.
			BigDecimal carrCharge = null;
			handlingFee = new BigDecimal(orderQuoteUtil.getHandlingFee(
					groupFirst.getClsId(), groupFirst.getType(),
					orderPackage.getBillableWeight(), totalQty, orderAmount));
			handlingFee = handlingFee.multiply(new BigDecimal(rate));
			boolean isJp = CountryCode.JP.value().equals(country) ? true
					: false;
			if (isJp) {

				Double lowestT = packageTemp.getLowestT();
				if (lowestT == null || lowestT.doubleValue() >= 20) {
					carrCharge = new BigDecimal(1500);
				} else {
					carrCharge = new BigDecimal(3000);
				}
				Double jpRate = exchRateByDateDao.getCurrencyRate(
						CurrencyType.JPY.value(), CurrencyType.USD.value(),
						new Date());
				carrCharge = CurrencyType.JPY.value().equals(currency) ? carrCharge
						: carrCharge.multiply(new BigDecimal(jpRate))
								.multiply(new BigDecimal(rate))
								.add(handlingFee);

			} else {
				carrCharge = BigDecimal
						.valueOf(orderPackage.getActlCarrCharge())
						.add(BigDecimal.valueOf(orderPackage
								.getInsuranceCharge()))
						.add(BigDecimal.valueOf(orderPackage
								.getAdtlCustomerCharge()));
				carrCharge = carrCharge.multiply(new BigDecimal(rate));
			}
			carrCharge = CurrencyType.JPY.value().equals(currency) ? carrCharge
					.setScale(0, BigDecimal.ROUND_HALF_UP) : carrCharge
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			carrCharges = carrCharges.add(carrCharge);
			System.out.println(carrCharge);
			System.out.println(carrCharges.doubleValue());
		}
		handlingFee = CurrencyType.JPY.value().equals(currency) ? handlingFee
				.setScale(0, BigDecimal.ROUND_HALF_UP) : handlingFee.setScale(
				2, BigDecimal.ROUND_HALF_UP);
		ShipPackage shipPackage = new ShipPackage();
		shipPackage.setHandingFee(handlingFee);
		shipPackage.setCustomerCharge(carrCharges);
		return shipPackage;
	}

	@SuppressWarnings("unchecked")
	private List<OrderPackageDTO> genPackageList(
			List<PackageHelper> packageTempList, Integer orderNo,
			Integer companyId, Integer warehouseId, String country) {
		List<OrderPackageDTO> packageList = new ArrayList<OrderPackageDTO>();
		Collections.sort(packageTempList, new BeanComparator("totalActWeight"));
		Map<String, OrderPackageDTO> packageMap = new LinkedHashMap<String, OrderPackageDTO>();
		OrderMainDTO sessOrder = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), orderNo.toString());
		country = sessOrder.getOrderAddrList().getShipToAddr().getCountry();
		boolean isInternalOrder = false;
		if (sessOrder.getCustNo() != null) {
			Customer customer = customerDao.getById(sessOrder.getCustNo());
			if (Constants.INTERNAL_TYPE_CUSTOMER.equalsIgnoreCase(customer
					.getCustType())) {
				isInternalOrder = true;
			}
		}
		boolean isJp = CountryCode.JP.value().equals(country) ? true : false;
		Double rate = null;
		Double handlingRate = null;
		if (!StringUtils.isEmpty(sessOrder.getOrderCurrency())
				&& !StringUtils.isEmpty(sessOrder.getBaseCurrency())
				&& sessOrder.getExchRateDate() != null) {
			rate = exchRateByDateDao.getCurrencyRate(
					sessOrder.getBaseCurrency(), sessOrder.getOrderCurrency(),
					sessOrder.getExchRateDate());
			handlingRate = exchRateByDateDao.getCurrencyRate(
					sessOrder.getOrderCurrency(), sessOrder.getBaseCurrency(),
					sessOrder.getExchRateDate());
		}
		if (rate == null || handlingRate == null) {
			throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
		}
		int packageSeq = 1;
		for (PackageHelper packageTemp : packageTempList) {
			String tempPackageId = null;
			try {
				tempPackageId = SessionUtil.generateTempId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<OrderItemDTO> pkgOrderItemList = packageTemp
					.getOrderPackageItemList();
			int totalQty = 0;
			Double orderAmount = 0.0;
			OrderItemDTO groupFirst = null;
			for (OrderItemDTO pkgItem : pkgOrderItemList) {
				if (OrderItemStatusType.CN.value().equals(pkgItem.getStatus())) {
					continue;
				}
				if (QuoteItemType.SERVICE.value().equalsIgnoreCase(
						pkgItem.getType())) {
					if (StringUtils.isBlank(pkgItem.getParentId())) {
						totalQty += pkgItem.getQuantity();
						if (groupFirst == null) {
							groupFirst = pkgItem;
						}
					}
				} else {
					if (groupFirst == null) {
						groupFirst = pkgItem;
					}
					totalQty += pkgItem.getQuantity();
				}
				if (pkgItem.getAmount() == null
						|| pkgItem.getAmount().doubleValue() <= 0) {
					continue;
				}
				orderAmount += pkgItem.getAmount().doubleValue();
			}
			orderAmount = orderAmount * handlingRate;
			OrderPackageDTO orderPackage = packageTemp.getOrderPackageDTO();
			orderPackage.setOrderNo(orderNo);
			orderPackage.setBillableWeight(orderPackage.getActualWeight());
			orderPackage.setShiptoAddress(getPkgShiptoAddress(groupFirst));
			orderPackage.setWarehouseName(this.warehouseDao
					.getById(warehouseId).getName());
			// 获得Package's zone.
			String zipCode = groupFirst.getShipToAddress().getZipCode();
			ShipZone shipZone = this.shipZoneDao.getShipZone(
					groupFirst.getShipMethod(), warehouseId, zipCode, country);
			if (shipZone != null) {
				orderPackage.setZone(shipZone.getZoneCode());
			}
			orderPackage.setPkgBatchSeq(1);
			orderPackage.setPkgBatchCount(1);
			orderPackage.setShipMethod(groupFirst.getShipMethod());
			orderPackage.setPackageType(groupFirst.getPackageType());
			// 获得package的Handding Fee
			BigDecimal handlingFee = new BigDecimal(0);
			if (!isInternalOrder) {
				handlingFee = new BigDecimal(
						orderQuoteUtil.getHandlingFee(groupFirst.getClsId(),
								groupFirst.getType(),
								orderPackage.getBillableWeight(), totalQty,
								orderAmount));
			}
			// 获得Package's baseCharge.
			BigDecimal charge = new BigDecimal(0);
			if (!isJp && !isInternalOrder) {
				charge = this.orderQuoteUtil
						.getShippingFee(groupFirst.getShipMethod(),
								warehouseId, orderPackage.getBillableWeight(),
								orderPackage.getZone(), groupFirst.getClsId(),
								groupFirst.getType(), totalQty, orderAmount,
								packageSeq);
			}
			orderPackage.setBaseCharge(charge.doubleValue());
			orderPackage.setDeliveryConfirmFee(0.0);
			orderPackage.setPackagingFee(0.0);
			// 获得 actlCarrCharge.
			Double actlCarrCharge = BigDecimal
					.valueOf(orderPackage.getBaseCharge())
					.add(BigDecimal.valueOf(orderPackage
							.getDeliveryConfirmFee()))
					.add(BigDecimal.valueOf(orderPackage.getPackagingFee()))
					.doubleValue();
			orderPackage.setActlCarrCharge(actlCarrCharge);
			orderPackage.setInsuranceCharge(0.0);
			orderPackage.setAdtlCustomerCharge(0.0);
			// 获得 carrierCharge.
			BigDecimal carrCharge = null;
			handlingFee = handlingFee.multiply(new BigDecimal(rate));
			if (!isInternalOrder) {
				if (isJp) {
					Double lowestT = packageTemp.getLowestT();
					if (lowestT == null || lowestT.doubleValue() >= 20) {
						carrCharge = new BigDecimal(1500);
					} else {
						carrCharge = new BigDecimal(3000);
					}
					Double jpRate = exchRateByDateDao.getCurrencyRate(
							CurrencyType.JPY.value(), CurrencyType.USD.value(),
							new Date());
					carrCharge = (CurrencyType.JPY.value().equals(
							sessOrder.getOrderCurrency()) ? carrCharge
							: carrCharge.multiply(new BigDecimal(jpRate))
									.multiply(new BigDecimal(rate)))
							.add(handlingFee);
				} else {
					carrCharge = BigDecimal
							.valueOf(orderPackage.getActlCarrCharge())
							.add(BigDecimal.valueOf(orderPackage
									.getInsuranceCharge()))
							.add(BigDecimal.valueOf(orderPackage
									.getAdtlCustomerCharge()));
					carrCharge = carrCharge.multiply(new BigDecimal(rate));
				}
			} else {
				handlingFee = new BigDecimal(0);
				carrCharge = new BigDecimal(0);
			}
			handlingFee = CurrencyType.JPY.value().equals(
					sessOrder.getOrderCurrency()) ? handlingFee.setScale(0,
					BigDecimal.ROUND_HALF_UP) : handlingFee.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			orderPackage.setHandlingFee(handlingFee.doubleValue());
			carrCharge = CurrencyType.JPY.value().equals(
					sessOrder.getOrderCurrency()) ? carrCharge.setScale(0,
					BigDecimal.ROUND_HALF_UP) : carrCharge.setScale(2,
					BigDecimal.ROUND_HALF_UP);
			orderPackage.setBaseCharge(carrCharge.doubleValue());
			orderPackage.setActlCarrCharge(carrCharge.doubleValue());
			orderPackage.setCarrierCharge(carrCharge.doubleValue());
			orderPackage.setCustomerCharge(orderPackage.getCarrierCharge());
			orderPackage.setCompanyId(companyId);
			packageSeq++;
			List<OrderPackageItemDTO> packageItemList = new ArrayList<OrderPackageItemDTO>();
			// int totalPackageItem = 0;
			for (int m = 0; m < pkgOrderItemList.size(); m++) {
				OrderItemDTO orderItem = pkgOrderItemList.get(m);// 获得原先传过来的某个OrderItem
				if (QuoteItemType.SERVICE.value().equalsIgnoreCase(
						orderItem.getType())
						&& StringUtils.isNotBlank(orderItem.getParentId())) {
					continue;
				}
				OrderPackageItemDTO packageItem = new OrderPackageItemDTO();
				packageItem.setOrderNo(orderNo);
				packageItem.setItemNo(orderItem.getItemNo());
				packageItem.setSize(orderItem.getSize());
				packageItem.setUom(orderItem.getSizeUom());
				packageItem.setQuantity(orderItem.getQuantity());
				packageItemList.add(packageItem);
				// totalPackageItem++;
			}
			orderPackage.setPackageItemList(packageItemList);
			orderPackage.setTotalQty(totalQty);
			packageList.add(orderPackage);
			packageMap.put(tempPackageId, orderPackage);
		}
		SessionUtil.insertRow(SessionConstant.OrderPackage.value(),
				orderNo.toString(), packageMap);
		return packageList;
	}

	/**
	 * 获得ShipToAddress
	 * 
	 * @author Zhang Yong
	 * @param orderItem
	 * @return
	 */
	private String getPkgShiptoAddress(OrderItemDTO orderItem) {
		String shipToAddr = null;
		if (orderItem != null && orderItem.getShipToAddress() != null) {
			List<String> tmpList = new ArrayList<String>();
			OrderAddress shipToAddress = orderItem.getShipToAddress();
			if (shipToAddress != null) {
				tmpList.add(shipToAddress.getFirstName() + " "
						+ shipToAddress.getLastName());

				/*
				 * if(shipToAddress.getOrganization()!=null){
				 * tmpList.add(shipToAddress.getOrganization().getName()); }
				 */

				if (shipToAddress.getOrgName() != null
						&& !"".equals(shipToAddress.getOrgName())) {
					tmpList.add(shipToAddress.getOrgName());
				}
				String addrStr = "";
				if (!StringUtils.isEmpty(shipToAddress.getAddrLine1())) {
					addrStr = shipToAddress.getAddrLine1();
				}
				if (!StringUtils.isEmpty(shipToAddress.getAddrLine2())) {
					addrStr = addrStr + ", " + shipToAddress.getAddrLine2();
				}
				if (!StringUtils.isEmpty(shipToAddress.getAddrLine3())) {
					addrStr = addrStr + ", " + shipToAddress.getAddrLine3();
				}
				tmpList.add(addrStr);
				String cityStr = "";
				if (!StringUtils.isEmpty(shipToAddress.getCity())) {
					cityStr = shipToAddress.getCity();
				}
				if (!StringUtils.isEmpty(shipToAddress.getState())) {
					if (cityStr.equalsIgnoreCase("")) {
						cityStr = shipToAddress.getState() + " "
								+ shipToAddress.getZipCode();
					} else {
						cityStr = cityStr + ", " + shipToAddress.getState()
								+ " " + shipToAddress.getZipCode();
					}
				}
				if (!StringUtils.isEmpty(shipToAddress.getCountry())) {
					if (cityStr.equalsIgnoreCase("")) {
						cityStr = shipToAddress.getCountry();
					} else {
						cityStr = cityStr + ", " + shipToAddress.getCountry();
					}
				}
				tmpList.add(cityStr);
				shipToAddr = StringUtils.join(tmpList.toArray(), "\n");
			}

		}
		return shipToAddr;
	}

	/**
	 * 判断是否能够自动calculate shipping package. warehouseId, shipMethod, packageType,
	 * orderAddress.四个元素是否完全一致. 并且设置packageType
	 * 
	 * @param itemDTOList
	 */
	public void checkAutoCalPackage(final List<OrderItemDTO> itemDTOList,
			Integer warehouseId, Integer companyId) {
		if (itemDTOList == null || itemDTOList.size() == 0) {
			return;
		}
		if (warehouseId == null) {
			throw new BussinessException(
					BussinessException.SHIP_PKG_ERROR_WAREHOUSEID);
		}
		// 通过set判断唯一集的数量
		Set<String> typeSet = new HashSet<String>();
		Set<String> packageTypeSet = new HashSet<String>();
		Set<String> addrSet = new HashSet<String>();
		Set<Integer> shipMethodSet = new HashSet<Integer>();
		for (int i = 0; i < itemDTOList.size(); i++) {
			OrderItemDTO dto = itemDTOList.get(i);
			typeSet.add(dto.getType().toLowerCase());
			shipMethodSet.add(dto.getShipMethod());
			OrderAddress orderAddress = dto.getShipToAddress();
			if (orderAddress == null) {
				addrSet.add(null);
			} else {
				addrSet.add(AddressUtil.getUniqueAddress(
						orderAddress.getFirstName(), orderAddress.getMidName(),
						orderAddress.getLastName(), orderAddress.getState(),
						orderAddress.getCountry()));
			}
			// 获得packageType
			String dtoPackageType = null;
			try {
				if (dto.getType().equals(QuoteItemType.SERVICE.value())) {
					Integer serviceId = this.serviceDao.getServiceByCatalogNo(
							dto.getCatalogNo()).getServiceId();
					dtoPackageType = this.serviceShipConditionDao
							.getShipCondition(serviceId).getShipPkgType();
				} else {
					Integer productId = this.productDao.getProductByCatalogNo(
							dto.getCatalogNo()).getProductId();
					dtoPackageType = this.shipConditionDao.getShipCondition(
							productId).getShipPkgType();
				}
			} catch (Exception e) {
				throw new BussinessException(
						BussinessException.INF_CAL_PACKING_DIFF,
						"ProductShipCondition's orderPackageType");
			}
			dto.setPackageType(dtoPackageType);
			packageTypeSet.add(dtoPackageType);
		}
		int typeCount = typeSet.size();
		if (shipMethodSet.size() > typeCount || typeCount == 0
				|| shipMethodSet.contains(null)) {// typeCount为0说明itemList为空，
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"OrderItem's shipMethod");
		}
		if (addrSet.size() > typeCount || addrSet.contains(null)) {
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"OrderItem's shipToAddress");
		}
		if (packageTypeSet.size() > typeCount || packageTypeSet.contains(null)) {
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"ProductShipCondition's orderPackageType");
		}
	}

	/**
	 * 在内存中产生临时的orderPackage, 根据totalActWeight排序后才能进行真正的保存.
	 * 
	 * @return
	 */
	public PackageHelper genPackagePublic(List<OrderItemDTO> packageItemList,
			Integer warehouseId) {
		return this.genPackage(packageItemList, warehouseId);
	}

	private PackageHelper genPackage(List<OrderItemDTO> packageItemList,
			Integer warehouseId) {
		OrderItemDTO groupFirst = packageItemList.get(0);
		PackageHelper packageHelper = new PackageHelper();
		OrderPackageDTO orderPackage = new OrderPackageDTO();
		orderPackage.setShiptoAddress(getPkgShiptoAddress(groupFirst));
		orderPackage.setWarehouseId(warehouseId);
		orderPackage.setStatus("Ready");
		Double totalActWeight = null;
		Double lowestT = null;
		try {
			totalActWeight = this.getPackageTotalWeight(packageItemList);
			lowestT = this.getPackageLowestTemperature(packageItemList);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BussinessException(
					BussinessException.ERR_CAL_PACKING_PKGWEIGHT);
		}
		orderPackage.setActualWeight(totalActWeight);
		orderPackage.setDeliveryType(groupFirst.getShipToAddress()
				.getAddrClass());
		packageHelper.setTotalActWeight(totalActWeight);
		packageHelper.setLowestT(lowestT);
		packageHelper.setOrderPackageDTO(orderPackage);
		packageHelper.setOrderPackageItemList(packageItemList);
		return packageHelper;
	}

	/**
	 * 获得生成的某个package的totalWeight.
	 * 
	 * @param packageItemsList
	 *            该Package包含的OrderItem list.
	 * @return
	 */
	@Transactional(readOnly = true)
	private Double getPackageTotalWeight(List<OrderItemDTO> packageItemsList) {
		OrderItemDTO groupFirst = packageItemsList.get(0);
		Double biggestActWeight = 0.0;
		String shipToCountry = groupFirst.getShipToAddress().getCountry();
		final String usaCountry = "US";
		if (shipToCountry.equals(usaCountry)) {// shipTo是美国.
			for (int m = 0; m < packageItemsList.size(); m++) {
				OrderItemDTO itemTemp = packageItemsList.get(m);// 获得原先传过来的某个OrderItem在itemDTOList中的序号.
				if (itemTemp.getType().equals(QuoteItemType.PRODUCT.value())) {
					Product product = this.productDao.findUniqueBy("catalogNo",
							itemTemp.getCatalogNo());
					ShipCondition scTemp = this.shipConditionDao
							.getById(product.getProductId());
					if (scTemp.getDomShipWeight().doubleValue() > biggestActWeight
							.doubleValue()) {
						biggestActWeight = scTemp.getDomShipWeight();
					}
				} else {
					Integer serviceId = this.serviceDao.getServiceByCatalogNo(
							itemTemp.getCatalogNo()).getServiceId();
					ServiceShipCondition scTemp = this.serviceShipConditionDao
							.getShipCondition(serviceId);
					if (scTemp.getDomShipWeight().doubleValue() > biggestActWeight
							.doubleValue()) {
						biggestActWeight = scTemp.getDomShipWeight();
					}
				}
			}
		} else {
			for (int m = 0; m < packageItemsList.size(); m++) {
				OrderItemDTO itemTemp = packageItemsList.get(m);// 获得原先传过来的某个OrderItem在itemDTOList中的序号.
				if (itemTemp.getType().equals(QuoteItemType.PRODUCT.value())) {
					Product product = this.productDao.findUniqueBy("catalogNo",
							itemTemp.getCatalogNo());
					ShipCondition scTemp = this.shipConditionDao
							.getById(product.getProductId());
					if (scTemp.getIntlShipWeight().doubleValue() > biggestActWeight
							.doubleValue()) {
						biggestActWeight = scTemp.getIntlShipWeight();
					}
				} else {
					Integer serviceId = this.serviceDao.getServiceByCatalogNo(
							itemTemp.getCatalogNo()).getServiceId();
					ServiceShipCondition scTemp = this.serviceShipConditionDao
							.getShipCondition(serviceId);
					if (scTemp.getIntlShipWeight().doubleValue() > biggestActWeight
							.doubleValue()) {
						biggestActWeight = scTemp.getIntlShipWeight();
					}
				}
			}
		}
		return biggestActWeight;
	}

	/**
	 * 获得生成的某个package的totalWeight.
	 * 
	 * @param packageItemsList
	 *            该Package包含的OrderItem list.
	 * @return
	 */
	@Transactional(readOnly = true)
	private Double getPackageLowestTemperature(
			List<OrderItemDTO> packageItemsList) {
		Double lowestT = null;
		for (int i = 0; i < packageItemsList.size(); i++) {
			OrderItemDTO tmpDTO = packageItemsList.get(i);
			if ("PRODUCT".equalsIgnoreCase(tmpDTO.getType())) {
				Integer productId = this.productDao
						.getProductIdByCatalogNo(tmpDTO.getCatalogNo());
				if (productId != null) {
					ShipCondition shipCon = this.shipConditionDao
							.get(productId);
					if (shipCon != null && shipCon.getTemperature() != null) {
						if (lowestT == null
								|| shipCon.getTemperature().doubleValue() < lowestT) {
							lowestT = shipCon.getTemperature().doubleValue();
						}
					}
				}
			} else {
				Integer serviceId = this.serviceDao
						.getServiceIdByCatalogNo(tmpDTO.getCatalogNo());
				if (serviceId != null) {
					ServiceShipCondition shipCon = this.serviceShipConditionDao
							.get(serviceId);
					if (shipCon != null && shipCon.getTemperature() != null) {
						if (lowestT == null
								|| shipCon.getTemperature().doubleValue() < lowestT) {
							lowestT = shipCon.getTemperature().doubleValue();
						}
					}
				}
			}
		}
		return lowestT;
	}

	/**
	 * 判断两个Address是否相同.
	 * 
	 * @param tagAddr
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isSameShippingAddr(OrderAddress srcAddr,
			OrderAddress tagAddr) {
		boolean bSame = false;
		if (srcAddr == null || tagAddr == null) {
			return false;
		}
		String source = srcAddr.getCountry() + srcAddr.getState()
				+ srcAddr.getFirstName() + srcAddr.getMidName()
				+ srcAddr.getLastName();
		String target = tagAddr.getCountry() + tagAddr.getState()
				+ tagAddr.getFirstName() + tagAddr.getMidName()
				+ tagAddr.getLastName();
		if (source.equals(target)) {
			bSame = true;
		}
		return bSame;
	}

	@Transactional(readOnly = true)
	public Long getTotalProductsOrdered(final Integer custNo,
			List<Integer> orderNoList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNoList", orderNoList);
		map.put("custNo", custNo);
		map.put("type", "PRODUCT");
		return orderDao.getTotalProductsOrdered(map);
	}

	@Transactional(readOnly = true)
	public Long getTotalServicesOrdered(final Integer custNo,
			List<Integer> orderNoList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNoList", orderNoList);
		map.put("custNo", custNo);
		map.put("type", "SERVICE");
		return orderDao.getTotalProductsOrdered(map);
	}

	/**
	 * 根据orderNo进行退货. 1. 根据orderNo查找状态为PROCESSED的所有OrderReturn. 2.
	 * 遍历每一个OrderReturn的状态为PROCESSED, exchangeFlag为Y的所有OrderReturnItem.
	 * 
	 * @param orderNo
	 */
	public Integer replaceOrder(Integer orderNo, Integer userId) {
		OrderMain dbOrder = this.orderDao.getById(orderNo);
		this.orderDao.getSession().evict(dbOrder);
		dbOrder.setOrderNo(null);
		dbOrder.setCreatedBy(userId);
		dbOrder.setCreationDate(new Date());
		dbOrder.setModifyDate(new Date());
		dbOrder.setModifiedBy(userId);
		dbOrder.setStatus(OrderStatusType.NW.value());
		this.orderDao.save(dbOrder);
		Integer newOrderNo = dbOrder.getOrderNo();
		Map<String, Integer> addrIdMap = new HashMap<String, Integer>();
		Integer newShipAddrId = this.getNewOrderAddress(
				dbOrder.getShiptoAddrId(), newOrderNo, userId, addrIdMap);
		Integer newSoldAddrId = this.getNewOrderAddress(
				dbOrder.getSoldtoAddrId(), newOrderNo, userId, addrIdMap);
		Integer newBillAddrId = this.getNewOrderAddress(
				dbOrder.getBilltoAddrId(), newOrderNo, userId, addrIdMap);
		if (newShipAddrId != null || newSoldAddrId != null
				|| newBillAddrId != null) {
			dbOrder.setShiptoAddrId(newShipAddrId);
			dbOrder.setSoldtoAddrId(newSoldAddrId);
			dbOrder.setBilltoAddrId(newBillAddrId);
			this.orderDao.save(dbOrder);
		}

		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_orderNo", orderNo);
		filterList.add(quoteFilter);
		PropertyFilter statusFilter = new PropertyFilter("EQS_status",
				OrderReturnStatusType.PROCESSED.value());
		filterList.add(statusFilter);
		List<OrderReturn> orList = this.orderReturnDao.find(filterList);
		for (OrderReturn orderReturn : orList) {
			List<OrderReturnItem> itemList = this.orderReturnItemDao
					.getReplaceItem(orderReturn.getRmaNo());
			if (itemList != null) {
				Integer maxItemNo = 1;
				for (OrderReturnItem returnItem : itemList) {
					OrderItem srcItem = this.orderItemDao.getOrderItem(orderNo,
							returnItem.getItemNo());
					this.orderItemDao.getSession().evict(srcItem);
					OrderItem item = srcItem;
					item.setQuantity(returnItem.getReturnQty());
					item.setSize(returnItem.getReturnSize().doubleValue());
					item.setDiscount(returnItem.getDiscount().doubleValue());
					item.setTax(returnItem.getTax().doubleValue());
					item.setStatus(OrderItemStatusType.CM.value());
					// 重写以下属性.
					item.setOrderItemId(null);
					item.setOrderNo(newOrderNo);
					item.setItemNo(maxItemNo);
					item.setCreatedBy(userId);
					item.setModifiedBy(userId);
					item.setCreationDate(new Date());
					item.setModifyDate(new Date());
					item.setOrderItemId(null);
					maxItemNo++;
					this.orderItemDao.save(item);// 从OrderReturmItem和OrderItem生成OrderItem.
					returnItem.setStatus(OrderReturnStatusType.EXCHANGED
							.value());
					this.orderReturnItemDao.save(returnItem);// 更新ReturnItem为exchanged.
					// 以下用于关联地址.
					Integer itemShipAddrId = this.getNewOrderAddress(
							item.getShiptoAddrId(), newOrderNo, userId,
							addrIdMap);
					Integer itemSoldAddrId = this.getNewOrderAddress(
							item.getSoldtoAddrId(), newOrderNo, userId,
							addrIdMap);
					Integer itemBillAddrId = this.getNewOrderAddress(
							item.getBilltoAddrId(), newOrderNo, userId,
							addrIdMap);
					if (itemShipAddrId != null || itemSoldAddrId != null
							|| itemBillAddrId != null) {
						item.setShiptoAddrId(itemShipAddrId);
						item.setSoldtoAddrId(itemSoldAddrId);
						item.setBilltoAddrId(itemBillAddrId);
						this.orderItemDao.save(item);// 关联地址后重新保存.
					}
				}
			}
		}
		return newOrderNo;
	}

	/**
	 * 获得新的地址的newAddrId. 如果原addrId为空则直接返回null, 如果原addrId在addrIdMap里找到相应的key,
	 * 则返回该Key对应的value即newAddrId， 否则新增一条并返回其主健.
	 * 
	 * @param dbAddrId
	 *            原数据库里的addrId.
	 * @param newOrderNo
	 *            新产生的Order的OrderNo.
	 * @param userId
	 * @param addrIdMap
	 *            <suffix+原数据库addrId, newAddrId>,
	 *            用于解决Order及OrderItem之间共享某个address.
	 * @return
	 */
	private Integer getNewOrderAddress(Integer dbAddrId, Integer newOrderNo,
			Integer userId, Map<String, Integer> addrIdMap) {
		Integer newAddrId = null;
		String suffix = "addrId";
		if (dbAddrId != null) {
			if (addrIdMap.containsKey(suffix + dbAddrId)) {
				return addrIdMap.get(suffix + dbAddrId);
			}
			OrderAddress shipToAddr = this.orderAddressDao.getById(dbAddrId);
			if (shipToAddr != null) {
				this.orderAddressDao.getSession().evict(shipToAddr);
				shipToAddr.setAddrId(null);
				shipToAddr.setOrderNo(newOrderNo);
				shipToAddr.setCreatedBy(userId);
				shipToAddr.setModifiedBy(userId);
				shipToAddr.setCreationDate(new Date());
				shipToAddr.setModifyDate(new Date());
				this.orderAddressDao.save(shipToAddr);
				newAddrId = shipToAddr.getAddrId();
				addrIdMap.put(suffix + dbAddrId, newAddrId);
			}
		}
		return newAddrId;
	}

	/**
	 * Change current currency to another currency
	 * 
	 * @param currencyDTOList
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CurrencyChangeDTO> changeCurrency(Date exchRateDate,
			final List<CurrencyChangeDTO> currencyDTOList,
			final String fromCurrency, final String toCurrency) {

		List<CurrencyChangeDTO> currencyList = new ArrayList<CurrencyChangeDTO>();
		if (currencyDTOList != null) {
			for (CurrencyChangeDTO currencyDTO : currencyDTOList) {
				CurrencyChangeDTO currencyChangeDTO = changeCurrency(
						exchRateDate, fromCurrency, toCurrency, currencyDTO);
				currencyList.add(currencyChangeDTO);
			}
		}
		return currencyList;
	}

	/**
	 * @param fromCurrency
	 * @param toCurrency
	 * @param currencyDTO
	 * @return
	 */
	@Transactional(readOnly = true)
	private CurrencyChangeDTO changeCurrency(Date exchRateDate,
			final String fromCurrency, final String toCurrency,
			CurrencyChangeDTO currencyDTO) {
		CurrencyChangeDTO currencyChangeDTO = new CurrencyChangeDTO();
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		Integer quantity = currencyDTO.getQuantity();
		Double discount = currencyDTO.getDiscount();
		Double unitPrice = currencyDTO.getBasePrice();
		Double taxRate = this.taxRateDao.getCountryStateRate(
				currencyDTO.getCountry(), currencyDTO.getState());
		if (taxRate == null) {
			taxRate = 0.0;
		}
		BigDecimal amountBD = new BigDecimal(unitPrice)
				.multiply(new BigDecimal(quantity));
		BigDecimal discountBD = (discount != null) ? new BigDecimal(discount)
				: zero;
		Double tax = amountBD.multiply(new BigDecimal(taxRate))
				.divide(new BigDecimal(100))
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal taxBD = (tax != null) ? new BigDecimal(tax) : zero;
		BigDecimal unitPriceBD = (unitPrice != null) ? new BigDecimal(unitPrice)
				: zero;

		/*
		 * Date orderDate; if (orderNo != null) { OrderMain order =
		 * orderDao.get(orderNo); orderDate = order.getExchRateDate(); } else {
		 * orderDate = new Date(); }
		 */
		BigDecimal rateBD = zero;
		Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency,
				toCurrency, exchRateDate);
		if (rate != null) {
			rateBD = new BigDecimal(rate);
			amountBD = amountBD.multiply(rateBD);
			discountBD = discountBD.multiply(rateBD);
			taxBD = taxBD.multiply(rateBD);
			unitPriceBD = unitPriceBD.multiply(rateBD);
		}
		int small = toCurrency.equals("JPY") ? 0 : 2;
		currencyChangeDTO.setAmount(amountBD.setScale(small,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		currencyChangeDTO.setDiscount(discountBD.setScale(small,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		currencyChangeDTO.setTax(taxBD
				.setScale(small, BigDecimal.ROUND_HALF_UP).doubleValue());
		currencyChangeDTO.setUnitPrice(unitPriceBD.setScale(small,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		currencyChangeDTO.setCatalogNo(currencyDTO.getCatalogNo());
		currencyChangeDTO.setItemId(currencyDTO.getItemId());
		return currencyChangeDTO;
	}

	/**
	 * 根据日期,转化的币种,算出转化后的金额数目.
	 * 
	 * @param money
	 * @param rateDate
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	public BigDecimal getOrderChangeCurrency(BigDecimal money, Date rateDate,
			final String fromCurrency, final String toCurrency) {
		if (rateDate == null) {
			rateDate = new Date();// 如果日期为空则取当前日期.
		}

		Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency,
				toCurrency, rateDate);
		BigDecimal rateBD = null;
		if (rate != null) {
			rateBD = new BigDecimal(rate);
		} else {
			rateBD = new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		money = money.multiply(rateBD);
		if (toCurrency.equals("JPY")) {
			money = money.setScale(0, BigDecimal.ROUND_HALF_UP);
		} else {
			money = money.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return money;
	}

	/**
	 * 用于对一个Order进行repeat生成一个供客户端使用的临时orderDTO.
	 * 
	 * @return
	 */
	public Integer repeatOrder(Integer srcOrderNo, Integer userId) {
		OrderMain order = orderDao.getById(srcOrderNo);
		String repeatCurrency = order.getOrderCurrency();
		List<OrderItem> dbItemList = orderItemDao
				.getItemListForRepeat(srcOrderNo);
		if (dbItemList == null || dbItemList.size() == 0) {
			return null;
		}
		orderDao.getSession().evict(order);
		// Copy OrderMain.
		OrderMain dbOrder = order;
		dbOrder.setOrderNo(null);
		dbOrder.setSrcQuoteNo(null);
		dbOrder.setOrderDate(null);
		dbOrder.setStatus(OrderStatusType.NW.value());
		dbOrder.setModifiedBy(userId);
		dbOrder.setOrderDate(new Date());
		dbOrder.setRefOrderNo(srcOrderNo.toString());
		this.saveOrUpdateOrder(dbOrder, dbOrder.getStatus(), null);
		// Copy OrderMainAddress.
		dbOrder.setOrderCurrency(repeatCurrency);
		Integer newOrderNo = dbOrder.getOrderNo();
		Map<String, Integer> addrIdMap = new HashMap<String, Integer>();
		Integer newShipAddrId = this.getNewOrderAddress(
				dbOrder.getShiptoAddrId(), newOrderNo, userId, addrIdMap);
		Integer newSoldAddrId = this.getNewOrderAddress(
				dbOrder.getSoldtoAddrId(), newOrderNo, userId, addrIdMap);
		Integer newBillAddrId = this.getNewOrderAddress(
				dbOrder.getBilltoAddrId(), newOrderNo, userId, addrIdMap);
		if (newShipAddrId != null || newSoldAddrId != null
				|| newBillAddrId != null) {
			dbOrder.setShiptoAddrId(newShipAddrId);
			dbOrder.setSoldtoAddrId(newSoldAddrId);
			dbOrder.setBilltoAddrId(newBillAddrId);
			this.orderDao.save(dbOrder);
		}

		// Copy OrderItems and orderItem's Address.
		List<OrderItem> newItemList = this.repeatOrderItem(newOrderNo,
				dbItemList, addrIdMap, userId);

		// Date now = new Date();
		// repeatServiceItem(userId, now, newItemList, srcOrderNo, newOrderNo);

		// Copy notes
		List<OrderNote> shpList = this.orderNoteDao.getOrderNoteList(
				srcOrderNo, OrderInstructionType.SHIPMENT);
		List<OrderNote> pdtList = this.orderNoteDao.getOrderNoteList(
				srcOrderNo, OrderInstructionType.PRODUCTION);
		List<OrderNote> accountList = this.orderNoteDao.getOrderNoteList(
				srcOrderNo, OrderInstructionType.ACCOUNTING);
		List<OrderNote> saleNotesList = this.orderNoteDao.getOrderNoteList(
				srcOrderNo, OrderInstructionType.SALES_NOTES);
		List<OrderNote> crossSaleNotesList = this.orderNoteDao
				.getOrderNoteList(srcOrderNo,
						OrderInstructionType.CROSS_SELLING);
		this.repeatOrderInstruction(newOrderNo, shpList, userId);
		this.repeatOrderInstruction(newOrderNo, pdtList, userId);
		this.repeatOrderInstruction(newOrderNo, accountList, userId);
		this.repeatOrderInstruction(newOrderNo, saleNotesList, userId);
		this.repeatOrderInstruction(newOrderNo, crossSaleNotesList, userId);
		// Copy shipping information.
		try {
			if (newItemList != null && newItemList.size() > 0) {
				List<OrderItemDTO> itemDTOList = this.getItemList(dbOrder,
						newItemList);
				this.calShippingPackage(itemDTOList, newOrderNo, userId,
						dbOrder.getGsCoId(), order.getWarehouseId());
			}
		} catch (Exception ex) {
			System.out.println("Auto calculate shipping package failure!");
		}
		return newOrderNo;
	}

	/**
	 * Copy OrderNotes and OrderMails to the New Order.
	 * 
	 * @param orderNo
	 * @param noteList
	 * @param userId
	 */
	private void repeatOrderInstruction(Integer orderNo,
			List<OrderNote> noteList, Integer userId) {
		if (noteList == null || noteList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (OrderNote note : noteList) {
			Integer originId = note.getId();
			this.orderNoteDao.getSession().evict(note);
			note.setId(null);
			note.setNoteDate(now);
			note.setOrderNo(orderNo);
			note.setCreatedBy(userId);
			note.setModifiedBy(userId);
			note.setCreationDate(now);
			note.setModifyDate(now);
			this.orderNoteDao.save(note);
			List<Document> docList = this.documentDao.getDocumentList(originId,
					DocumentType.ORDER_INST_NOTE);
			if (docList != null && docList.size() > 0) {
				for (Document doc : docList) {
					this.documentDao.getSession().evict(doc);
					doc.setDocId(null);
					// 更新docName
					String name = doc.getDocName();
					int index = name.lastIndexOf(".");
					name = name.substring(0, index) + "_" + orderNo
							+ name.substring(index);
					doc.setDocName(name);
					// 更新filePath
					String filePath = doc.getFilePath();
					index = filePath.lastIndexOf(".");
					filePath = filePath.substring(0, index) + "_" + orderNo
							+ filePath.substring(index);
					doc.setFilePath(filePath);
					doc.setCreatedBy(userId);
					doc.setModifiedBy(userId);
					doc.setCreationDate(now);
					doc.setModifyDate(now);
					doc.setRefId(note.getId());
					doc.setRefType(DocumentType.ORDER_INST_NOTE.value());
					this.documentDao.save(doc);
				}
			}
		}
	}

	public void saveDocument(Document doc, int refId) {
		// this.documentDao.getSession().evict(doc);
		doc.setDocId(null);
		int userId = SessionUtil.getUserId();
		Date now = new Date();
		doc.setCreatedBy(userId);
		doc.setModifiedBy(userId);
		doc.setCreationDate(now);
		doc.setModifyDate(now);
		doc.setRefId(refId);
		this.documentDao.save(doc);
	}

	@Transactional(readOnly = true)
	public BigDecimal getPrePayment(final Integer custNo,
			final Integer orderNo, final Double orderTotal,
			final String toCurrency) {
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		if (orderTotal != null && custNo != null) {
			Double currentBalance = this.custInfoStatDao
					.getCurrentBalance(custNo);
			Customer customer = customerDao.get(custNo);
			Integer crRatingId = customer.getCrRatingId();
			if (crRatingId != null) {
				CreditRating creditRating = creditRatingDao.get(crRatingId);
				Double crLimit = creditRating.getCrLimit();

				Date orderDate;
				if (orderNo != null) {
					OrderMain order = orderDao.get(orderNo);
					orderDate = order.getExchRateDate();
				} else {
					orderDate = new Date();
				}
				Double rate = exchRateByDateDao.getCurrencyRate("USD",
						toCurrency, orderDate);
				BigDecimal prePaymentBD;
				if (rate != null) {
					crLimit = crLimit * rate;
					currentBalance = currentBalance * rate;
				}
				prePaymentBD = new BigDecimal(orderTotal).subtract(
						new BigDecimal(crLimit)).add(
						new BigDecimal(currentBalance));
				if (prePaymentBD.compareTo(zero) < 0) {
					if (toCurrency.equals("JPY")) {
						return zero.setScale(0, BigDecimal.ROUND_HALF_UP);
					} else {
						return zero;
					}
				} else {
					if (toCurrency.equals("JPY")) {
						return prePaymentBD.setScale(0,
								BigDecimal.ROUND_HALF_UP);
					} else {
						return prePaymentBD.setScale(2,
								BigDecimal.ROUND_HALF_UP);
					}
				}
			}
		}
		return zero;
	}

	/**
	 * Copy OrderItem list to the New Order.
	 */
	private List<OrderItem> repeatOrderItem(Integer newOrderNo,
			List<OrderItem> dbItemList, Map<String, Integer> addrIdMap,
			Integer userId) {
		List<OrderItem> retItemList = null;
		Map<Integer, Integer> itemIdMap = new HashMap<Integer, Integer>();
		if (dbItemList != null) {
			int seq = 1;
			retItemList = new ArrayList<OrderItem>();
			String customerCompany = customerDao.getCustomerCompany(orderDao
					.getById(newOrderNo).getCustNo(), null);
			for (OrderItem dbItem : dbItemList) {
				Integer dbItemId = dbItem.getOrderItemId();
				this.orderItemDao.getSession().evict(dbItem);
				OrderItem item = dozer.map(dbItem, OrderItem.class);
				ItemOtherInfoForNewDTO otherInfo = this.getItemOtherInfoForNew(
						item.getQuantity(), item.getCatalogNo(),
						QuoteItemType.fromValue(item.getType()),
						customerCompany);
				item.setDiscount(0.0);
				item.setStatus(otherInfo.getItemStatus());
				item.setOrderItemId(null);
				item.setOrderNo(newOrderNo);
				item.setItemNo(seq);
				item.setCreatedBy(userId);
				item.setModifiedBy(userId);
				item.setCreationDate(new Date());
				item.setModifyDate(new Date());
				this.orderItemDao.save(item);
				itemIdMap.put(dbItemId, item.getOrderItemId());
				// 以下用于关联地址.
				Integer itemShipAddrId = this.getNewOrderAddress(
						item.getShiptoAddrId(), newOrderNo, userId, addrIdMap);
				Integer itemSoldAddrId = this.getNewOrderAddress(
						item.getSoldtoAddrId(), newOrderNo, userId, addrIdMap);
				Integer itemBillAddrId = this.getNewOrderAddress(
						item.getBilltoAddrId(), newOrderNo, userId, addrIdMap);
				if (itemShipAddrId != null || itemSoldAddrId != null
						|| itemBillAddrId != null) {
					item.setShiptoAddrId(itemShipAddrId);
					item.setSoldtoAddrId(itemSoldAddrId);
					item.setBilltoAddrId(itemBillAddrId);
					this.orderItemDao.save(item);// 关联地址后重新保存.
				}
				Date now = new Date();
				// 关联Service部分信息.
				this.repeatItemMoreDetail(dbItem, newOrderNo,
						item.getOrderItemId(), userId, now);

				seq++;
				retItemList.add(item);
				// 记录OrderItem status history.
				OrderProcessLog itemLog = new OrderProcessLog();
				itemLog.setPriorStat(null);
				itemLog.setCurrentStat(item.getStatus());
				itemLog.setNote(null);
				itemLog.setProcessDate(new Date());
				if (userId != null) {
					itemLog.setProcessedBy(userDao.getById(userId));
				}
				itemLog.setOrderNo(newOrderNo);
				itemLog.setReason("For Repeat");
				orderProcessLogDao.save(itemLog);
			}
			for (OrderItem orderItem : retItemList) {
				Integer parentId = orderItem.getParentId();
				if (parentId != null) {
					Integer newParentId = itemIdMap.get(parentId);
					orderItem.setParentId(newParentId);
					this.orderItemDao.save(orderItem);// 关联地址后重新保存.
					orderItemDao.getSession().flush();
				}
			}
		}
		return retItemList;
	}

	/**
	 * 赋值moreDetail信息 modify by Zhang Yong modify date 2011-11-29
	 * 
	 * @param dbItem
	 * @param newOrderNo
	 * @param newItemId
	 * @param userId
	 * @param now
	 */
	private void repeatItemMoreDetail(final OrderItem dbItem,
			final Integer newOrderNo, final Integer newItemId,
			final Integer userId, final Date now) {
		if (dbItem == null || dbItem.getClsId() == null) {
			return;
		}
		Integer clsId = dbItem.getClsId();
		Integer orderItemId = dbItem.getOrderItemId();
		List<Document> docList = null;
		if (clsId.intValue() == -1 || clsId.intValue() == 29
				|| clsId.intValue() == 35 || clsId.intValue() == 36
				|| clsId.intValue() == 37 || clsId.intValue() == 38
				|| clsId == 39 || clsId.intValue() == 42) {
			com.genscript.gsscm.order.entity.OrderService oService = orderServiceDao
					.getById(orderItemId);
			if (oService != null) {
				orderServiceDao.getSession().evict(oService);
				oService.setOrderItemId(newItemId);
				oService.setOrderNo(newOrderNo);
				oService.setCreatedBy(userId);
				oService.setModifiedBy(userId);
				oService.setCreationDate(now);
				oService.setModifyDate(now);
				orderServiceDao.save(oService);
			}
		} else if (clsId == 1 || clsId == 30 || clsId == 31) {
			OrderPeptide orderPeptide = this.orderPeptideDao
					.getById(orderItemId);
			if (orderPeptide != null) {
				orderPeptideDao.getSession().evict(orderPeptide);
				orderPeptide.setOrderItemId(newItemId);
				orderPeptide.setOrderNo(newOrderNo);
				orderPeptide.setCreatedBy(userId);
				orderPeptide.setModifiedBy(userId);
				orderPeptide.setCreationDate(now);
				orderPeptide.setModifyDate(now);
				orderPeptideDao.save(orderPeptide);
			}
		} else if (clsId == 2 || clsId == 13 || clsId == 14 || clsId == 15
				|| clsId == 16 || clsId == 17 || clsId == 18 || clsId == 19
				|| clsId == 32 || clsId == 33) {
			OrderPkgService orderPkgService = this.orderPkgServiceDao
					.getById(orderItemId);
			if (orderPkgService != null) {
				orderPkgServiceDao.getSession().evict(orderPkgService);
				orderPkgService.setOrderItemId(newItemId);
				orderPkgService.setOrderNo(newOrderNo);
				orderPkgService.setCreatedBy(userId);
				orderPkgService.setModifiedBy(userId);
				orderPkgService.setCreationDate(now);
				orderPkgService.setModifyDate(now);
				orderPkgServiceDao.save(orderPkgService);
			}
		} else if (clsId == 3) {
			OrderGeneSynthesis orderGeneSynthesis = orderGeneSynthesisDao
					.getById(orderItemId);
			if (orderGeneSynthesis != null) {
				orderGeneSynthesisDao.getSession().evict(orderGeneSynthesis);
				orderGeneSynthesis.setOrderItemId(newItemId);
				orderGeneSynthesis.setOrderNo(newOrderNo);
				orderGeneSynthesis.setCreatedBy(userId);
				orderGeneSynthesis.setModifiedBy(userId);
				orderGeneSynthesis.setCreationDate(now);
				orderGeneSynthesis.setModifyDate(now);
				orderGeneSynthesisDao.save(orderGeneSynthesis);
				DocumentType[] docTypeList = { DocumentType.OIM_GENE };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 4) {
			OrderMutagenesis orderMutagenesis = this.orderMutagenesisDao
					.getById(orderItemId);
			if (orderMutagenesis != null) {
				orderMutagenesisDao.getSession().evict(orderMutagenesis);
				orderMutagenesis.setOrderItemId(newItemId);
				orderMutagenesis.setOrderNo(newOrderNo);
				orderMutagenesis.setCreatedBy(userId);
				orderMutagenesis.setModifiedBy(userId);
				orderMutagenesis.setCreationDate(now);
				orderMutagenesis.setModifyDate(now);
				orderMutagenesisDao.save(orderMutagenesis);
				DocumentType[] docTypeList = { DocumentType.OIM_MUTA,
						DocumentType.OIM_MUTA_SELF };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 5) {
			// order_mutation_libraries
			OrderMutationLibraries mutaLib = this.orderMutationLibrariesDao
					.getById(orderItemId);
			if (mutaLib != null) {
				orderMutationLibrariesDao.getSession().evict(mutaLib);
				mutaLib.setOrderItemId(newItemId);
				mutaLib.setOrderNo(newOrderNo);
				mutaLib.setCreatedBy(userId);
				mutaLib.setModifiedBy(userId);
				mutaLib.setCreationDate(now);
				mutaLib.setModifyDate(now);
				orderMutationLibrariesDao.save(mutaLib);
				DocumentType[] docTypeList = { DocumentType.OIM_MUTALIB,
						DocumentType.OIM_MUTALIB_SELF };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 6) {
			OrderOrfClone orderOrfClone = this.orderOrfCloneDao
					.getById(orderItemId);
			if (orderOrfClone != null) {
				orderOrfCloneDao.getSession().evict(orderOrfClone);
				orderOrfClone.setOrderItemId(newItemId);
				orderOrfClone.setOrderNo(newOrderNo);
				orderOrfClone.setCreatedBy(userId);
				orderOrfClone.setModifiedBy(userId);
				orderOrfClone.setCreationDate(now);
				orderOrfClone.setModifyDate(now);
				orderOrfCloneDao.save(orderOrfClone);
				DocumentType[] docTypeList = { DocumentType.OIM_ORFCLONE };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 7 || clsId == 8) {
			OrderSirnaAndMirna orderSirnaAndMirna = this.orderSirnaAndMirnaDao
					.getById(orderItemId);
			if (orderSirnaAndMirna != null) {
				orderSirnaAndMirnaDao.getSession().evict(orderSirnaAndMirna);
				orderSirnaAndMirna.setOrderItemId(newItemId);
				orderSirnaAndMirna.setOrderNo(newOrderNo);
				orderSirnaAndMirna.setCreatedBy(userId);
				orderSirnaAndMirna.setModifiedBy(userId);
				orderSirnaAndMirna.setCreationDate(now);
				orderSirnaAndMirna.setModifyDate(now);
				orderSirnaAndMirnaDao.save(orderSirnaAndMirna);
				DocumentType[] docTypeList = { DocumentType.OIM_RNA };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 9) {
			OrderCustCloning orderCustCloning = orderCustCloningDao
					.getById(orderItemId);
			if (orderCustCloning != null) {
				orderCustCloningDao.getSession().evict(orderCustCloning);
				orderCustCloning.setOrderItemId(newItemId);
				orderCustCloning.setOrderNo(newOrderNo);
				orderCustCloning.setCreatedBy(userId);
				orderCustCloning.setModifiedBy(userId);
				orderCustCloning.setCreationDate(now);
				orderCustCloning.setModifyDate(now);
				orderCustCloningDao.save(orderCustCloning);
				DocumentType[] docTypeList = { DocumentType.OIM_CUSTCLONING,
						DocumentType.OIM_CUSTCLONING_CS,
						DocumentType.OIM_CUSTCLONING_SELF };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 10) {
			OrderPlasmidPreparation orderPlasmidPreparation = orderPlasmidPreparationDao
					.getById(orderItemId);
			if (orderPlasmidPreparation != null) {
				orderPlasmidPreparationDao.getSession().evict(
						orderPlasmidPreparation);
				orderPlasmidPreparation.setOrderItemId(newItemId);
				orderPlasmidPreparation.setOrderNo(newOrderNo);
				orderPlasmidPreparation.setCreatedBy(userId);
				orderPlasmidPreparation.setModifiedBy(userId);
				orderPlasmidPreparation.setCreationDate(now);
				orderPlasmidPreparation.setModifyDate(now);
				orderPlasmidPreparationDao.save(orderPlasmidPreparation);
				DocumentType[] docTypeList = { DocumentType.OIM_PLASMID,
						DocumentType.OIM_PLASMID_SELF };
				docList = orderQuoteUtil.getItemDocList(orderItemId,
						docTypeList);
			}
		} else if (clsId == 11 || clsId == 12 || clsId == 22 || clsId == 28) {
			OrderAntibody orderAntibody = this.orderAntibodyDao
					.getById(orderItemId);
			if (orderAntibody != null) {
				orderAntibodyDao.getSession().evict(orderAntibody);
				orderAntibody.setOrderItemId(newItemId);
				orderAntibody.setOrderNo(newOrderNo);
				orderAntibody.setCreatedBy(userId);
				orderAntibody.setModifiedBy(userId);
				orderAntibody.setCreationDate(now);
				orderAntibody.setModifyDate(now);
				orderAntibodyDao.save(orderAntibody);
			}
		} else if (clsId == 34) {
			OrderOligo orderOligo = this.orderOligoDao.getById(orderItemId);
			if (orderOligo != null) {
				orderOligoDao.getSession().evict(orderOligo);
				orderOligo.setOrderItemId(newItemId);
				orderOligo.setOrderNo(newOrderNo);
				orderOligo.setCreatedBy(userId);
				orderOligo.setModifiedBy(userId);
				orderOligo.setCreationDate(now);
				orderOligo.setModifyDate(now);
				orderOligoDao.save(orderOligo);
			}
		} else if (clsId == 40) {
			OrderDnaSequencing dnaSeq = this.orderDnaSequencingDao
					.getById(orderItemId);
			if (dnaSeq != null) {
				orderDnaSequencingDao.getSession().evict(dnaSeq);
				dnaSeq.setOrderItemId(newItemId);
				dnaSeq.setOrderNo(newOrderNo);
				dnaSeq.setCreatedBy(userId);
				dnaSeq.setModifiedBy(userId);
				dnaSeq.setCreationDate(now);
				dnaSeq.setModifyDate(now);
				orderDnaSequencingDao.save(dnaSeq);
			}
		}
		if (docList != null && !docList.isEmpty()) {
			// 复制文件
			this.copyDocsForRepeat(newItemId, docList, userId);
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param newOrderItemId
	 * @param dbDocList
	 * @param loginUserId
	 */
	private void copyDocsForRepeat(Integer newOrderItemId,
			List<Document> dbDocList, Integer loginUserId) {
		if (dbDocList == null || dbDocList.isEmpty()) {
			return;
		}
		for (Document dbDoc : dbDocList) {
			documentDao.getSession().evict(dbDoc);
			Document doc = dbDoc;
			try {
				File srcFile = new File(fileService.getUploadPath() + "/"
						+ dbDoc.getFilePath());
				String tagDir = fileService.getUploadPath() + "/"
						+ FilePathConstant.orderService.value();
				String shortName = SessionUtil.generateTempId() + "_"
						+ dbDoc.getDocName();
				File tagFile = new File(tagDir + "/" + shortName);
				// 拷贝文件
				try {
					FileUtils.copyFile(srcFile, tagFile);
				} catch (Exception ex) {
					System.out
							.println("==========java.io.FileNotFoundException======(OrderService.java-- copyDocsForRepeat()=======");
				}
				doc.setDocId(null);
				doc.setRefId(newOrderItemId);
				doc.setFilePath(FilePathConstant.orderService.value() + "/"
						+ shortName);
				doc.setCreatedBy(loginUserId);
				doc.setModifiedBy(loginUserId);
				doc.setCreationDate(new Date());
				doc.setModifyDate(new Date());
				documentDao.save(doc);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 根据orderNo获得orderPackage list.
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderPackageDTO> getOrderOrderPackageList(Integer orderNo) {
		List<OrderPackageDTO> dtoList = new ArrayList<OrderPackageDTO>();
		List<OrderPackage> srcList = this.orderPackageDao
				.getOrderPackageList(orderNo);
		if (srcList != null) {
			for (OrderPackage source : srcList) {
				OrderPackageDTO dto = new OrderPackageDTO();
				dto.setPackageId(source.getPackageId());
				dto.setTotalQty(this.orderPackageDao.getTotalQty(
						source.getPackageId()).intValue());
				if (source.getLength() != null && source.getWidth() != null
						&& source.getBillableWeight() != null) {
					BigDecimal width = BigDecimal.valueOf(source.getWidth());
					BigDecimal length = BigDecimal.valueOf(source.getLength());
					BigDecimal billWeight = BigDecimal.valueOf(source
							.getBillableWeight());
					dto.setSize(length.multiply(width.multiply(billWeight))
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());

				}
				dto.setPkgBatchSeq(source.getPkgBatchSeq());
				dto.setPkgBatchCount(source.getPkgBatchCount());
				dto.setStatus(source.getStatus());
				dto.setInvoiceNo(source.getInvoiceNo());
				dto.setShipVia(this.shipMethodDao.getById(
						source.getShipMethod()).getName());
				dto.setShiptoAddress(source.getShiptoAddress());
				dto.setWarehouseName(this.warehouseDao.getById(
						source.getWarehouseId()).getName());
				dto.setTrackingNo(source.getTrackingNo());
				dto.setBillableWeight(source.getBillableWeight());
				dto.setCustomerCharge(source.getCustomerCharge());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 根据orderNo获得orderPackage基本信息 list.
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OrderPackageDTO> getBaseOrderOrderPackageList(Integer orderNo) {
		List<OrderPackageDTO> dtoList = new ArrayList<OrderPackageDTO>();
		List<OrderPackage> srcList = this.orderPackageDao
				.getOrderPackageList(orderNo);
		// OrderMainDTO orderMain = (OrderMainDTO)
		// SessionUtil.getRow(SessionConstant.Order.value(),
		// orderNo.toString());
		// boolean isJp = false;
		// Double rate = null;
		// if (orderMain.getOrderAddrList() != null &&
		// orderMain.getOrderAddrList().getShipToAddr() != null) {
		// String country =
		// orderMain.getOrderAddrList().getShipToAddr().getCountry();
		// if (CountryCode.JP.value().equals(country)) {
		// isJp = true;
		// }
		// if (!StringUtils.isEmpty(orderMain.getOrderCurrency())
		// && !StringUtils.isEmpty(orderMain.getBaseCurrency())
		// && orderMain.getExchRateDate() != null) {
		// rate = exchRateByDateDao.getCurrencyRate(orderMain.getBaseCurrency(),
		// orderMain.getOrderCurrency(), orderMain.getExchRateDate());
		// }
		// //else中的内容是处理OrderMain的OrderAddrList未加载出来而直接到数据库中查询
		// } else {
		// OrderAddress orderAddress = null;
		// if (orderMain.getShiptoAddrId() != null) {
		// orderAddress = orderAddressDao.getById(orderMain.getShiptoAddrId());
		// } else {
		// OrderMain dbOrder = orderDao.getById(orderMain.getOrderNo());
		// orderAddress = orderAddressDao.getById(dbOrder.getShiptoAddrId());
		// }
		// String country = orderAddress.getCountry();
		// if (CountryCode.JP.value().equals(country)) {
		// isJp = true;
		// }
		// if (!StringUtils.isEmpty(orderMain.getOrderCurrency())
		// && !StringUtils.isEmpty(orderMain.getBaseCurrency())
		// && orderMain.getExchRateDate() != null) {
		// rate = exchRateByDateDao.getCurrencyRate(orderMain.getBaseCurrency(),
		// orderMain.getOrderCurrency(), orderMain.getExchRateDate());
		// }
		// }
		if (srcList != null && !srcList.isEmpty()) {
			// if (rate == null) {
			// throw new
			// BussinessException(BussinessException.EXCH_RATE_IS_NULL);
			// }
			for (OrderPackage source : srcList) {
				OrderPackageDTO dto = new OrderPackageDTO();
				dto = getPackageDetail(source.getPackageId());
				// BigDecimal customerCharge = null;
				// BigDecimal baseCharge = null;
				// BigDecimal deliveryConfirmFee = null;
				// BigDecimal packagingFee = null;
				// BigDecimal actlCarrCharge = null;
				// BigDecimal adtlCustomerCharge = null;
				// BigDecimal carrierCharge = null;
				// BigDecimal insuranceCharge = null;
				// BigDecimal handlingFee = null;
				// //Order的ShipToAddress的country为JP的时候，item的存储温度不低于20度地应该是1500日元，低于20度地应该是3000日元
				// if (isJp) {
				// customerCharge = new BigDecimal(source.getCustomerCharge() !=
				// null?source.getCustomerCharge():0);
				// baseCharge = new BigDecimal(source.getBaseCharge() !=
				// null?source.getBaseCharge():0);
				// deliveryConfirmFee = new
				// BigDecimal(source.getDeliveryConfirmFee() !=
				// null?source.getDeliveryConfirmFee():0);
				// packagingFee = new BigDecimal(source.getPackagingFee() !=
				// null?source.getPackagingFee():0);
				// actlCarrCharge = new BigDecimal(source.getActlCarrCharge() !=
				// null?source.getActlCarrCharge():0);
				// adtlCustomerCharge = new
				// BigDecimal(source.getAdtlCustomerCharge() !=
				// null?source.getAdtlCustomerCharge():0);
				// carrierCharge = new BigDecimal(source.getCarrierCharge() !=
				// null?source.getCarrierCharge():0);
				// insuranceCharge = new BigDecimal(source.getInsuranceCharge()
				// != null?source.getInsuranceCharge():0);
				// handlingFee = new BigDecimal(source.getHandlingFee() !=
				// null?source.getHandlingFee():0);
				// if
				// (!CurrencyType.JPY.value().equals(orderMain.getOrderCurrency()))
				// {
				// Double jpyRate =
				// exchRateByDateDao.getCurrencyRate(CurrencyType.JPY.value(),
				// orderMain.getBaseCurrency(), orderMain.getExchRateDate());
				// if (jpyRate == null) {
				// throw new
				// BussinessException(BussinessException.EXCH_RATE_IS_NULL);
				// }
				// customerCharge = customerCharge.multiply(new
				// BigDecimal(jpyRate)).multiply(new BigDecimal(rate));
				// }
				// } else {
				// customerCharge = new BigDecimal(source.getCustomerCharge() !=
				// null?source.getCustomerCharge():0);
				// baseCharge = new BigDecimal(source.getBaseCharge() !=
				// null?source.getBaseCharge():0);
				// deliveryConfirmFee = new
				// BigDecimal(source.getDeliveryConfirmFee() !=
				// null?source.getDeliveryConfirmFee():0);
				// packagingFee = new BigDecimal(source.getPackagingFee() !=
				// null?source.getPackagingFee():0);
				// actlCarrCharge = new BigDecimal(source.getActlCarrCharge() !=
				// null?source.getActlCarrCharge():0);
				// adtlCustomerCharge = new
				// BigDecimal(source.getAdtlCustomerCharge() !=
				// null?source.getAdtlCustomerCharge():0);
				// carrierCharge = new BigDecimal(source.getCarrierCharge() !=
				// null?source.getCarrierCharge():0);
				// insuranceCharge = new BigDecimal(source.getInsuranceCharge()
				// != null?source.getInsuranceCharge():0);
				// handlingFee = new BigDecimal(source.getHandlingFee() !=
				// null?source.getHandlingFee():0);
				// }
				// int pot =
				// !CurrencyType.JPY.value().equals(orderMain.getOrderCurrency())?2:0;
				// customerCharge = customerCharge != null ? customerCharge:new
				// BigDecimal(0);
				// customerCharge = customerCharge.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setCustomerCharge(customerCharge.doubleValue());
				// //
				// baseCharge = baseCharge != null ? baseCharge:new
				// BigDecimal(0);
				// baseCharge = baseCharge.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setBaseCharge(baseCharge.doubleValue());
				// //
				// deliveryConfirmFee = deliveryConfirmFee != null ?
				// deliveryConfirmFee:new BigDecimal(0);
				// deliveryConfirmFee = deliveryConfirmFee.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setDeliveryConfirmFee(deliveryConfirmFee.doubleValue());
				// //
				// packagingFee = packagingFee != null ? packagingFee:new
				// BigDecimal(0);
				// packagingFee = packagingFee.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setPackagingFee(packagingFee.doubleValue());
				// //
				// actlCarrCharge = actlCarrCharge != null ? actlCarrCharge:new
				// BigDecimal(0);
				// actlCarrCharge = actlCarrCharge.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setActlCarrCharge(actlCarrCharge.doubleValue());
				// //
				// adtlCustomerCharge = adtlCustomerCharge != null ?
				// adtlCustomerCharge:new BigDecimal(0);
				// adtlCustomerCharge = adtlCustomerCharge.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setAdtlCustomerCharge(adtlCustomerCharge.doubleValue());
				// //
				// carrierCharge = carrierCharge != null ? carrierCharge:new
				// BigDecimal(0);
				// carrierCharge = carrierCharge.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setCarrierCharge(carrierCharge.doubleValue());
				// //
				// insuranceCharge = insuranceCharge != null ?
				// insuranceCharge:new BigDecimal(0);
				// insuranceCharge = insuranceCharge.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setInsuranceCharge(insuranceCharge.doubleValue());
				// //
				// handlingFee = handlingFee != null ? handlingFee:new
				// BigDecimal(0);
				// handlingFee = handlingFee.setScale(pot,
				// BigDecimal.ROUND_HALF_UP);
				// dto.setHandlingFee(handlingFee.doubleValue());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/*
	 * 根据orderNo和该order的几个item的itemNo list获得这几个OrderItem的OrderPackage.
	 * 
	 * @param orderNo @param itemNoList @return
	 */
	@Transactional(readOnly = true)
	public List<OrderPackageDTO> getPackageListForItemList(Integer orderNo,
			List<Integer> itemNoList) {
		if (orderNo == null || itemNoList == null || itemNoList.isEmpty()) {
			return null;
		}
		List<OrderPackageDTO> dtoList = new ArrayList<OrderPackageDTO>();
		List<OrderPackage> srcList = this.orderPackageItemDao
				.getPackagesForItemList(orderNo, itemNoList);
		if (srcList != null) {
			OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(
					SessionConstant.Order.value(), orderNo.toString());
			boolean isJp = false;
			Double rate = null;
			if (orderMain.getOrderAddrList() != null
					&& orderMain.getOrderAddrList().getShipToAddr() != null) {
				String country = orderMain.getOrderAddrList().getShipToAddr()
						.getCountry();
				if (CountryCode.JP.value().equals(country)) {
					isJp = true;
				}
				if (!StringUtils.isEmpty(orderMain.getOrderCurrency())
						&& !StringUtils.isEmpty(orderMain.getBaseCurrency())
						&& orderMain.getExchRateDate() != null) {
					rate = exchRateByDateDao.getCurrencyRate(
							orderMain.getBaseCurrency(),
							orderMain.getOrderCurrency(),
							orderMain.getExchRateDate());
				}
			}
			if (rate == null) {
				throw new BussinessException(
						BussinessException.EXCH_RATE_IS_NULL);
			}
			for (OrderPackage source : srcList) {
				OrderPackageDTO dto = this.dozer.map(source,
						OrderPackageDTO.class);
				dto.setTotalQty(this.orderPackageDao.getTotalQty(
						source.getPackageId()).intValue());
				if (source.getLength() != null && source.getWidth() != null
						&& source.getBillableWeight() != null) {
					dto.setSize(BigDecimal
							.valueOf(source.getLength())
							.multiply(
									BigDecimal
											.valueOf(source.getWidth())
											.multiply(
													BigDecimal.valueOf(source
															.getBillableWeight())))
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
				}
				dto.setShipVia(this.shipMethodDao.getById(
						source.getShipMethod()).getName());
				dto.setShiptoAddress(source.getShiptoAddress());
				dto.setWarehouseName(this.warehouseDao.getById(
						source.getWarehouseId()).getName());
				BigDecimal customerCharge = null;
				BigDecimal baseCharge = null;
				BigDecimal deliveryConfirmFee = null;
				BigDecimal packagingFee = null;
				BigDecimal actlCarrCharge = null;
				BigDecimal adtlCustomerCharge = null;
				BigDecimal carrierCharge = null;
				BigDecimal insuranceCharge = null;
				// Order的ShipToAddress的country为JP的时候，item的存储温度不低于20度地应该是1500日元，低于20度地应该是3000日元
				if (isJp) {
					customerCharge = new BigDecimal(
							source.getCustomerCharge() != null ? source
									.getCustomerCharge() : 0);
					baseCharge = new BigDecimal(
							source.getBaseCharge() != null ? source
									.getBaseCharge() : 0);
					deliveryConfirmFee = new BigDecimal(
							source.getDeliveryConfirmFee() != null ? source
									.getDeliveryConfirmFee() : 0);
					packagingFee = new BigDecimal(
							source.getPackagingFee() != null ? source
									.getPackagingFee() : 0);
					actlCarrCharge = new BigDecimal(
							source.getActlCarrCharge() != null ? source
									.getActlCarrCharge() : 0);
					adtlCustomerCharge = new BigDecimal(
							source.getAdtlCustomerCharge() != null ? source
									.getAdtlCustomerCharge() : 0);
					carrierCharge = new BigDecimal(
							source.getCarrierCharge() != null ? source
									.getCarrierCharge() : 0);
					insuranceCharge = new BigDecimal(
							source.getInsuranceCharge() != null ? source
									.getInsuranceCharge() : 0);
					if (!CurrencyType.JPY.value().equals(
							orderMain.getOrderCurrency())) {
						Double jpyRate = exchRateByDateDao.getCurrencyRate(
								CurrencyType.JPY.value(),
								orderMain.getBaseCurrency(),
								orderMain.getExchRateDate());
						if (jpyRate == null) {
							throw new BussinessException(
									BussinessException.EXCH_RATE_IS_NULL);
						}
						customerCharge = customerCharge.multiply(
								new BigDecimal(jpyRate)).multiply(
								new BigDecimal(rate));
					}
				} else {
					customerCharge = new BigDecimal(
							source.getCustomerCharge() != null ? source
									.getCustomerCharge() : 0);
					baseCharge = new BigDecimal(
							source.getBaseCharge() != null ? source
									.getBaseCharge() : 0);
					deliveryConfirmFee = new BigDecimal(
							source.getDeliveryConfirmFee() != null ? source
									.getDeliveryConfirmFee() : 0);
					packagingFee = new BigDecimal(
							source.getPackagingFee() != null ? source
									.getPackagingFee() : 0);
					actlCarrCharge = new BigDecimal(
							source.getActlCarrCharge() != null ? source
									.getActlCarrCharge() : 0);
					adtlCustomerCharge = new BigDecimal(
							source.getAdtlCustomerCharge() != null ? source
									.getAdtlCustomerCharge() : 0);
					carrierCharge = new BigDecimal(
							source.getCarrierCharge() != null ? source
									.getCarrierCharge() : 0);
					insuranceCharge = new BigDecimal(
							source.getInsuranceCharge() != null ? source
									.getInsuranceCharge() : 0);
				}
				int pot = !CurrencyType.JPY.value().equals(
						orderMain.getOrderCurrency()) ? 2 : 0;
				customerCharge = customerCharge != null ? customerCharge
						: new BigDecimal(0);
				customerCharge = customerCharge.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setCustomerCharge(customerCharge.doubleValue());
				//
				baseCharge = baseCharge != null ? baseCharge
						: new BigDecimal(0);
				baseCharge = baseCharge.setScale(pot, BigDecimal.ROUND_HALF_UP);
				dto.setBaseCharge(baseCharge.doubleValue());
				//
				deliveryConfirmFee = deliveryConfirmFee != null ? deliveryConfirmFee
						: new BigDecimal(0);
				deliveryConfirmFee = deliveryConfirmFee.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setDeliveryConfirmFee(deliveryConfirmFee.doubleValue());
				//
				packagingFee = packagingFee != null ? packagingFee
						: new BigDecimal(0);
				packagingFee = packagingFee.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setPackagingFee(packagingFee.doubleValue());
				//
				actlCarrCharge = actlCarrCharge != null ? actlCarrCharge
						: new BigDecimal(0);
				actlCarrCharge = actlCarrCharge.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setActlCarrCharge(actlCarrCharge.doubleValue());
				//
				adtlCustomerCharge = adtlCustomerCharge != null ? adtlCustomerCharge
						: new BigDecimal(0);
				adtlCustomerCharge = adtlCustomerCharge.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setAdtlCustomerCharge(adtlCustomerCharge.doubleValue());
				//
				carrierCharge = carrierCharge != null ? carrierCharge
						: new BigDecimal(0);
				carrierCharge = carrierCharge.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setCarrierCharge(carrierCharge.doubleValue());
				//
				insuranceCharge = insuranceCharge != null ? insuranceCharge
						: new BigDecimal(0);
				insuranceCharge = insuranceCharge.setScale(pot,
						BigDecimal.ROUND_HALF_UP);
				dto.setInsuranceCharge(insuranceCharge.doubleValue());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/*
	 * 根据orderNo获得OrderPackage.
	 * 
	 * @param orderNo @param itemNoList @return
	 */
	@Transactional(readOnly = true)
	public List<OrderPackageDTO> getPackageList(Integer orderNo) {
		if (orderNo == null) {
			return null;
		}
		List<OrderPackageDTO> dtoList = new ArrayList<OrderPackageDTO>();
		List<OrderPackage> srcList = this.orderPackageDao
				.getOrderPackageList(orderNo);
		if (srcList != null) {
			for (OrderPackage source : srcList) {
				OrderPackageDTO dto = this.dozer.map(source,
						OrderPackageDTO.class);
				dto.setTotalQty(this.orderPackageDao.getTotalQty(
						source.getPackageId()).intValue());
				if (source.getLength() != null && source.getWidth() != null
						&& source.getBillableWeight() != null) {
					dto.setSize(BigDecimal
							.valueOf(source.getLength())
							.multiply(
									BigDecimal
											.valueOf(source.getWidth())
											.multiply(
													BigDecimal.valueOf(source
															.getBillableWeight())))
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
				}
				dto.setShipVia(this.shipMethodDao.getById(
						source.getShipMethod()).getName());
				dto.setShiptoAddress(source.getShiptoAddress());
				dto.setWarehouseName(this.warehouseDao.getById(
						source.getWarehouseId()).getName());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 获得一个package的详细信息.
	 * 
	 * @param packageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public OrderPackageDTO getPackageDetail(Integer packageId) {
		OrderPackageDTO dto = this.dozer.map(
				this.orderPackageDao.getById(packageId), OrderPackageDTO.class);
		dto.setShipVia(this.shipMethodDao.getById(dto.getShipMethod())
				.getName());
		Long totalQty = this.orderPackageDao.getTotalQty(packageId);
		dto.setTotalQty(totalQty == null ? 0 : totalQty.intValue());
		if (dto.getLength() != null && dto.getWidth() != null
				&& dto.getBillableWeight() != null) {
			dto.setSize(BigDecimal
					.valueOf(dto.getLength())
					.multiply(
							BigDecimal.valueOf(dto.getWidth())
									.multiply(
											BigDecimal.valueOf(dto
													.getBillableWeight())))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		dto.setWarehouseName(this.warehouseDao.getById(dto.getWarehouseId())
				.getName());
		List<OrderPackageItem> packageItemList = orderPackageItemDao
				.findPackageItemsByPkgId(packageId);
		if (packageItemList != null && !packageItemList.isEmpty()) {
			List<OrderPackageItemDTO> packageItemDTOList = new ArrayList<OrderPackageItemDTO>();
			for (OrderPackageItem packageItem : packageItemList) {
				OrderPackageItemDTO packageItemDto = dozer.map(packageItem,
						OrderPackageItemDTO.class);
				packageItemDto.setPkgItemId(null);
				packageItemDto.setPackageId(null);
				packageItemDTOList.add(packageItemDto);
			}
			dto.setPackageItemList(packageItemDTOList);
		}
		return dto;
	}

	/**
	 * 根据页面上传来的值计算shippingTotal.(目前Order可能是不存在的)
	 * 
	 * @param orderCurrency
	 *            页面上传过来的orderCurrency
	 * @param packageList
	 * @return
	 */
	@Transactional(readOnly = true)
	public ShippingTotalDTO getShippingTotalByPage(String orderCurrency,
			List<OrderPackageDTO> packageList, Double exchRate,
			Date exchRateDate) {
		ShippingTotalDTO dto = new ShippingTotalDTO();
		if (packageList == null || packageList.size() < 1) {
			return dto;
		}
		// 获得Package Total.
		dto.setPackageTotal(packageList.size());
		// 获得Weight Total;
		BigDecimal billableWeightTotal = BigDecimal.valueOf(0.0);
		BigDecimal costTotal = BigDecimal.valueOf(0.0);
		BigDecimal handlingFee = BigDecimal.valueOf(0.0);
		for (OrderPackageDTO spDTO : packageList) {
			if (spDTO.getBillableWeight() == null) {
				billableWeightTotal = billableWeightTotal.add(BigDecimal
						.valueOf(0));
			} else {
				billableWeightTotal = billableWeightTotal.add(BigDecimal
						.valueOf(spDTO.getBillableWeight()));
			}
			if (spDTO.getCustomerCharge() != null) {
				costTotal = costTotal.add(BigDecimal.valueOf(spDTO
						.getCustomerCharge()));
			}
			if (spDTO.getHandlingFee() != null) {
				handlingFee = handlingFee.add(new BigDecimal(spDTO
						.getHandlingFee()));
			}
		}
		dto.setBillableWeightTotal(billableWeightTotal.setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		// 获得Cost Total;
		BigDecimal rateBD = new BigDecimal(exchRate);
		costTotal = costTotal.multiply(rateBD).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		handlingFee = handlingFee.multiply(rateBD).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		// //进行汇率转换(costTotal始终为为"USD")
		// costTotal = this.getOrderChangeCurrency(costTotal, exchRateDate,
		// "USD",
		// orderCurrency);
		dto.setCostTotal(costTotal.doubleValue());
		if (!StringUtils.isEmpty(dto.getCurrencySymbol())) {
			if (("¥").equals(dto.getCurrencySymbol())) {
				dto.setCostTotalShow(costTotal.setScale(0,
						BigDecimal.ROUND_HALF_UP).toString());
				dto.setHandlingFee(handlingFee.setScale(0,
						BigDecimal.ROUND_HALF_UP).toString());
			} else {
				dto.setCostTotalShow(costTotal.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());
				dto.setHandlingFee(handlingFee.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());
			}
		} else {
			if (CurrencyType.JPY.value().equals(orderCurrency)) {
				dto.setCostTotalShow(costTotal.setScale(0,
						BigDecimal.ROUND_HALF_UP).toString());
				dto.setHandlingFee(handlingFee.setScale(0,
						BigDecimal.ROUND_HALF_UP).toString());
			} else {
				dto.setCostTotalShow(costTotal.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());
				dto.setHandlingFee(handlingFee.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());
			}
		}
		// Cost Currency
		dto.setCurrency(orderCurrency);
		PbCurrency pbCurrency = this.currencyDao.findUniqueBy("currencyCode",
				orderCurrency);
		if (pbCurrency != null) {
			dto.setCurrencySymbol(pbCurrency.getSymbol());
		}
		// 获得Zone;
		OrderPackageDTO firstPackage = packageList.get(0);
		dto.setZone(firstPackage.getZone());
		// 获得ShipVia;
		Integer shipMethod = firstPackage.getShipMethod();
		dto.setShipVia(shipMethod);
		// 获得ShipAccountFlag.
		boolean shipAccountFlag = false;
		if (dto.getShipVia() != null) {
			shipAccountFlag = true;
		}
		dto.setShipAccountFlag(shipAccountFlag);
		return dto;
	}

	/**
	 * 获得Shipping Total信息 for Order Total Tab's Shipping.
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public ShippingTotalDTO getShippingTotal(Integer orderNo) {
		ShippingTotalDTO dto = new ShippingTotalDTO();
		// 获得Package Total.
		Integer packageTotal = this.orderPackageDao.getOrderPackageCount(
				orderNo).intValue();
		dto.setPackageTotal(packageTotal);
		// 获得Weight Total;
		Double billableWeightTotal = this.orderPackageDao
				.getOrderPackageWeight(orderNo);
		dto.setBillableWeightTotal(billableWeightTotal);
		// 获得Cost Total;
		Double costTotal = this.orderPackageDao.getOrderPackageCost(orderNo);
		OrderMain order = orderDao.get(orderNo);
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal bigCost = zero;
		BigDecimal rateBD = null;
		if (order != null && order.getExchRateDate() != null) {
			Double rate = exchRateByDateDao.getCurrencyRate("USD",
					order.getOrderCurrency(), order.getExchRateDate());
			rateBD = new BigDecimal(rate);
			bigCost = new BigDecimal(costTotal).multiply(rateBD).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		}
		dto.setCostTotal(bigCost.doubleValue());
		dto.setCurrency(order.getOrderCurrency());
		PbCurrency pbCurrency = this.currencyDao.findUniqueBy("currencyCode",
				order.getOrderCurrency());
		if (pbCurrency != null) {
			dto.setCurrencySymbol(pbCurrency.getSymbol());
		}
		// 获得Zone;
		String zone = null;
		boolean bOnlyZone = false;
		OrderPackage firstPackage = this.orderPackageDao
				.getOrderFirstPackage(orderNo);
		if (firstPackage == null) {
			return dto;
		}
		if (packageTotal.intValue() == 1) {// 只有一个Package
			bOnlyZone = true;
		} else {
			Long zoneDistinctCount = this.orderPackageDao
					.getOrderPackageZoneCount(orderNo);
			if (zoneDistinctCount.intValue() == 1) {// 多个Package但它们的zone是一样的;
				bOnlyZone = true;
			}
		}
		if (bOnlyZone) {
			zone = firstPackage.getZone();
		}
		dto.setZone(zone);
		// 获得ShipVia;
		Integer shipVia = null;
		Long shipMethodDistinctCount = null;
		if (packageTotal.intValue() < 1) {// 该Order还没有package.
			Customer customer = this.customerDao.getById(this.orderDao.getById(
					orderNo).getCustNo());
			shipVia = customer.getPrefShipMthd();
		} else {
			shipMethodDistinctCount = this.orderPackageDao
					.getOrderPackageShipMethodCount(orderNo);
			if (shipMethodDistinctCount.intValue() == 1) {// 如果该Order的多种Package的shipMethod为同一种.
				shipVia = firstPackage.getShipMethod();
			}
		}
		dto.setShipVia(shipVia);

		// 获得ShipAccountFlag.
		boolean shipAccountFlag = false;
		// 该Order的package数小于等于1 其carrier肯定是一致的.
		if (packageTotal.intValue() <= 1) {
			shipAccountFlag = true;//
		} else {
			if (shipMethodDistinctCount == null) {
				shipMethodDistinctCount = this.orderPackageDao
						.getOrderPackageShipMethodCount(orderNo);
			}
			// 虽然有多个Package但只有一种shipMethod, 其carrier肯定是一致的.
			if (shipMethodDistinctCount.intValue() == 1) {
				shipAccountFlag = true;
			}
		}
		if (!shipAccountFlag) {
			List<Integer> shipMethodList = this.orderPackageDao
					.getOrderShipMethodList(orderNo);
			if (shipMethodList != null && !shipMethodList.isEmpty()) {
				Long carrierCount = this.shipMethodDao
						.getShipMethodCarrierCount(shipMethodList);
				// 虽然有多个Package而且有多个shipMethod, 但只要这些shipMethod对应的carrier的个数是1,
				// 则carrier肯定是一致的.
				if (carrierCount.intValue() == 1) {
					shipAccountFlag = true;
				}
			}
		}
		dto.setShipAccountFlag(shipAccountFlag);
		return dto;
	}

	/*
	 * add by zhanghuibin 取得一个item主服务的子服务
	 */
	public void setItemAllChildMoreDetail(List<OrderItemDTO> itemDTOList,
			OrderItemDTO itemDto) {
		for (OrderItemDTO dto : itemDTOList) {
			if (itemDto.getOrderItemId().toString().equals(dto.getParentId())) {
				getItemMoreDetail(dto.getOrderItemId());
			}
		}
	}

	// 取得一个item服务相关的所有服务，即，子服务的主服务和所有的子服务
	public void setItemAllMoreDetail(Map<String, OrderItemDTO> itemMap, OrderItemDTO orderItem) {
		// 设置一个HashMap，用来存取Mutagensis的item，因为Mutagensis的作为gene的下级，有可能还有下级的item
		List<OrderItemDTO> mutaGensisList = new ArrayList<OrderItemDTO>();
		setItemMoreDetailByClsId(orderItem);
		if (4 == orderItem.getClsId()) {
			mutaGensisList.add(orderItem);
		}
		String parentId = orderItem.getParentId();
		if (StringUtils.isNotBlank(parentId)) {
			Iterator<OrderItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				OrderItemDTO dto = it.next();
				// 子服务
				if (orderItem.getParentId().equals(dto.getParentId())
						&& orderItem.getItemNo().intValue() != dto.getItemNo()
								.intValue()) {
					if (4 == dto.getClsId()) {
						mutaGensisList.add(dto);
					}
					setItemMoreDetailByClsId(dto);
					// orderItem.getOrderItemId()
					// 为空在时候是新增尚未保存到数据库中的item，所以无需取这种item的moreDetail
				} else if (orderItem.getParentId().equals(
						"" + dto.getOrderItemId())) {// 主服务 ,这里
					setItemMoreDetailByClsId(dto);
				}
			}
			// 处理DNA Sequencing add by Zhang Yong
		} else if (StringUtils.isBlank(parentId)
				&& orderItem.getClsId() != null
				&& 40 == orderItem.getClsId().intValue()) {
			Iterator<OrderItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				OrderItemDTO dto = it.next();
				if (40 == dto.getClsId().intValue()
						&& dto.getDnaSequencing() == null) {
					setItemMoreDetailByClsId(dto);
				}
			}
		} else {
			Iterator<OrderItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				OrderItemDTO dto = it.next();
				/*
				 * orderItem.getOrderItemId()
				 * 为空在时候是新增尚未保存到数据库中的item，所以无需取这种item的moreDetail
				 */
				if (dto.getParentId() != null && dto.getParentId().equals("" + orderItem.getOrderItemId())) {
					setItemMoreDetailByClsId(dto);
				}
			}
		}
		// 处理mutagensis
		for (OrderItemDTO mutaDTO : mutaGensisList) {
			Iterator<OrderItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				OrderItemDTO dto = it.next();
				if (String.valueOf(mutaDTO.getOrderItemId()).equals(
						dto.getParentId())
						&& mutaDTO.getItemNo().intValue() != dto.getItemNo()
								.intValue()) {
					setItemMoreDetailByClsId(dto);
				}
			}
		}
	}

	public void setItemMoreDetailByClsId(OrderItemDTO orderItem) {
		// modify by zhanghuibin 如果此Order有相应的服务，则不再取值
		Integer clsId = orderItem.getClsId();
		if ((clsId == 1 || clsId == 30 || clsId == 31)
				&& orderItem.getPeptide() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if ((clsId == 2 || clsId == 13 || clsId == 14 || clsId == 15
				|| clsId == 16 || clsId == 17 || clsId == 18 || clsId == 19
				|| clsId == 32 || clsId == 33)
				&& orderItem.getOrderPkgService() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 3 && orderItem.getGeneSynthesis() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 4 && orderItem.getMutagenesis() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 5 && orderItem.getMutationLibraries() == null) {
			// order_mutation_libraries
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 6 && orderItem.getOrfClone() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if ((clsId == 7 || clsId == 8) && orderItem.getRna() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 9 && orderItem.getCustCloning() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 10 && orderItem.getPlasmidPreparation() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if ((clsId == 11 || clsId == 12 || clsId == 22 || clsId == 28)
				&& orderItem.getAntibody() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 29) {
			// order_service
		} else if (clsId == 34 && orderItem.getOligo() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if ((clsId == -1 || clsId == 35 || clsId == 36 || clsId == 37
				|| clsId == 38 || clsId == 39 || clsId == 42)
				&& orderItem.getCustomService() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		} else if (clsId == 40 && orderItem.getDnaSequencing() == null) {
			getItemMoreDetail(null, orderItem, orderItem.getOrderItemId());
		}
	}

	/**
	 * 获得一个Service类型的Item的Servcie相关信息.
	 * 
	 * @param orderItemId
	 * @return
	 */
	@Transactional(readOnly = true)
	public OrderItemDTO getItemMoreDetail(Integer orderItemId) {
		OrderItemDTO itemDTO = new OrderItemDTO();
		itemDTO = this.getItemMoreDetail(null, itemDTO, orderItemId);
		return itemDTO;
	}

	@Transactional(readOnly = true)
	public OrderItemDTO getItemMoreDetail(List<OrderItemDTO> itemDTOList,
			OrderItemDTO itemDTO, Integer orderItemId) {
		if (orderItemId == null || itemDTO.getClsId() == null || itemDTO.getClsId() == 0) {
			return itemDTO;
		}
		Integer clsId = itemDTO.getClsId();
		boolean noParentId = StringUtils.isBlank(itemDTO.getParentId());
		// add by zhanghuin 如果这里的clsId再加新的类型，请同步修改OrderMoreAction中的
		// showMoreDetail 方法判断，以保证程序优化
		if (clsId == -1 || clsId == 29 || clsId == 35 || clsId == 36
				|| clsId == 37 || clsId == 38 || clsId == 39 || clsId == 42) {
			com.genscript.gsscm.order.entity.OrderService customService = this.orderServiceDao
					.getById(orderItemId);
			if (customService != null && customService.getOrderItemId() != null) {
				CustomServiceDTO dto = this.dozer.map(customService,
						CustomServiceDTO.class);
				itemDTO.setCustomService(dto);
			} else {
				itemDTO.setCustomService(new CustomServiceDTO());
			}
		} else if (clsId == 1 || clsId == 30 || clsId == 31) {
			OrderPeptide peptideTemp = this.orderPeptideDao
					.getById(orderItemId);
			if (peptideTemp != null && peptideTemp.getOrderItemId() != null) {
				PeptideDTO peptide = this.dozer.map(peptideTemp,
						PeptideDTO.class);
				itemDTO.setPeptide(peptide);
			} else {
				itemDTO.setPeptide(new PeptideDTO());
			}
		} else if (clsId == 2 || clsId == 13 || clsId == 14 || clsId == 15
				|| clsId == 16 || clsId == 17 || clsId == 18 || clsId == 19
				|| clsId == 32 || clsId == 33) {
			OrderPkgService pkgTmp = this.orderPkgServiceDao
					.getById(orderItemId);
			if (pkgTmp != null && pkgTmp.getOrderItemId() != null) {
				PkgServiceDTO pkgService = this.dozer.map(pkgTmp,
						PkgServiceDTO.class);
				pkgService.setCatalogNo(itemDTO.getCatalogNo());
				pkgService.setItemName(itemDTO.getName());
				pkgService.setCost(itemDTO.getCost());
				pkgService.setUnitPrice(itemDTO.getUnitPrice());
				pkgService.setUpSymbol(itemDTO.getUpSymbol());
				if (StringUtils.isNotBlank(pkgTmp.getDescription())) {
					pkgService.setDescription(pkgTmp.getDescription()
							.replaceAll("'", "@@").replaceAll("\"", "~~")
							.replaceAll("\n", "##"));
				}
				itemDTO.setOrderPkgService(pkgService);
			} else {
				itemDTO.setOrderPkgService(new PkgServiceDTO());
			}
		} else if (clsId == 3) {
			OrderGeneSynthesis geneTemp = orderGeneSynthesisDao
					.getById(orderItemId);
			if (geneTemp != null && geneTemp.getOrderItemId() != null) {
				OrderGeneSynthesisDTO geneSynthesis = dozer.map(geneTemp,
						OrderGeneSynthesisDTO.class);
				DocumentType[] docTypeList = { DocumentType.OIM_GENE };
				List<Document> docList = orderQuoteUtil.getItemDocList(
						orderItemId, docTypeList);
				geneSynthesis.setDocumentList(docList);
				itemDTO.setGeneSynthesis(geneSynthesis);
				if (noParentId) {
					itemDTO.setVirusSeqFlag(geneSynthesis.getVirusSeqFlag());
				}
			} else {
				itemDTO.setGeneSynthesis(new OrderGeneSynthesisDTO());
			}
		} else if (clsId == 4) {
			OrderMutagenesis mutageneTemp = this.orderMutagenesisDao
					.getById(orderItemId);
			if (mutageneTemp != null && mutageneTemp.getOrderItemId() != null) {
				OrderMutagenesisDTO mutagene = this.dozer.map(mutageneTemp,
						OrderMutagenesisDTO.class);
				DocumentType[] docTypeList = { DocumentType.OIM_MUTA,
						DocumentType.OIM_MUTA_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(
						orderItemId, docTypeList);
				mutagene.setDocumentList(docList);
				itemDTO.setMutagenesis(mutagene);
			} else {
				itemDTO.setMutagenesis(new OrderMutagenesisDTO());
			}
		} else if (clsId == 5) {
			// order_mutation_libraries
			OrderMutationLibraries mutaLibTemp = this.orderMutationLibrariesDao
					.getById(orderItemId);
			if (mutaLibTemp != null && mutaLibTemp.getOrderItemId() != null) {
				OrderMutationLibrariesDTO mutageneLibraries = this.dozer.map(
						mutaLibTemp, OrderMutationLibrariesDTO.class);
				DocumentType[] docTypeList = { DocumentType.OIM_MUTALIB,
						DocumentType.OIM_MUTALIB_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(
						orderItemId, docTypeList);
				mutageneLibraries.setDocumentList(docList);
				itemDTO.setMutationLibraries(mutageneLibraries);
			} else {
				itemDTO.setMutationLibraries(new OrderMutationLibrariesDTO());
			}
		} else if (clsId == 6) {
			OrderOrfClone orderOrfCloneTemp = this.orderOrfCloneDao .getById(orderItemId);
			if (orderOrfCloneTemp != null && orderOrfCloneTemp.getOrderItemId() != null) {
				OrderOrfCloneDTO orderOrfCloneDto = this.dozer.map(orderOrfCloneTemp, OrderOrfCloneDTO.class);
				OrderItem cloneItem = null;
				List<OrderItem> subItemList = orderItemDao.searchOrderItemByParentItemId(orderItemId);
				if (subItemList != null && !subItemList.isEmpty()) {
					for (OrderItem subItem : subItemList) {
						if (subItem.getClsId().intValue() == 9) {
							cloneItem = subItem;
							break;
						}
					}
				}
				boolean isFullLength = ("1").equals(orderOrfCloneTemp.getSeqType())?true:false;
				StringBuffer accessionInfo = new StringBuffer();
				accessionInfo.append("accession:"+orderOrfCloneTemp.getAccessionNo());
				accessionInfo.append("@@@seqType:"+(isFullLength?FullLength:ORFSequence));
				accessionInfo.append("@@@genePrice:"+itemDTO.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if (cloneItem == null) {
					accessionInfo.append("@@@subcloningPrice:0.00@@@geneCost:"+itemDTO.getCost().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"@@@subcloningCost:0.00");
				} else {
					accessionInfo.append("@@@subcloningPrice:"+(new BigDecimal(cloneItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"@@@geneCost:"+itemDTO.getCost().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"@@@subcloningCost:"+(new BigDecimal(cloneItem.getCost())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				Refseq2orfprice refseq2orfprice = refseq2orfpriceDao.getByAccessionNo(orderOrfCloneTemp.getAccessionNo().trim());
				if (refseq2orfprice != null) {
					accessionInfo.append("@@@realGenePrice:"+(new BigDecimal(isFullLength?refseq2orfprice.getSyntheticPriceCDNA():refseq2orfprice.getSyntheticPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					accessionInfo.append("@@@realSubCloningPrice:"+(new BigDecimal(isFullLength?(refseq2orfprice.getVectorPriceCDNA()-refseq2orfprice.getSyntheticPriceCDNA()):(refseq2orfprice.getVectorPrice()-refseq2orfprice.getSyntheticPrice()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					accessionInfo.append("@@@realGeneCost:"+(new BigDecimal(isFullLength?refseq2orfprice.getSyntheticCostCDNA():refseq2orfprice.getSyntheticCost())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					accessionInfo.append("@@@realSubcloningCost:"+(new BigDecimal(isFullLength?(refseq2orfprice.getVectorCostCDNA()-refseq2orfprice.getSyntheticCostCDNA()):(refseq2orfprice.getVectorCost()-refseq2orfprice.getSyntheticCost()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				orderOrfCloneDto.setAccessionInfo(accessionInfo.toString());
				DocumentType[] docTypeList = { DocumentType.OIM_ORFCLONE };
				List<Document> docList = orderQuoteUtil.getItemDocList(orderItemId, docTypeList);
				orderOrfCloneDto.setDocumentList(docList);
				itemDTO.setOrfClone(orderOrfCloneDto);
			} else {
				itemDTO.setOrfClone(new OrderOrfCloneDTO());
			}
		} else if (clsId == 7 || clsId == 8) {
			OrderRna rnaTemp = this.orderRnaDao.getById(orderItemId);
			if (rnaTemp != null && rnaTemp.getOrderItemId() != null) {
				RnaDTO rna = dozer.map(rnaTemp, RnaDTO.class);
				DocumentType[] docTypeList = { DocumentType.OIM_RNA };
				List<Document> docList = orderQuoteUtil.getItemDocList(
						orderItemId, docTypeList);
				rna.setDocumentList(docList);
				itemDTO.setRna(rna);
			} else {
				itemDTO.setRna(new RnaDTO());
			}
		} else if (clsId == 9) {
			OrderCustCloning custTemp = orderCustCloningDao
					.getById(orderItemId);
			if (custTemp != null && custTemp.getOrderItemId() != null) {
				OrderCustCloningDTO custCloning = dozer.map(custTemp,
						OrderCustCloningDTO.class);
				DocumentType[] docTypeList = { DocumentType.OIM_CUSTCLONING,
						DocumentType.OIM_CUSTCLONING_CS,
						DocumentType.OIM_CUSTCLONING_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(
						orderItemId, docTypeList);
				custCloning.setDocumentList(docList);
				itemDTO.setCustCloning(custCloning);
			} else {
				itemDTO.setCustCloning(new OrderCustCloningDTO());
			}
		} else if (clsId == 10) {
			OrderPlasmidPreparation plasTemp = orderPlasmidPreparationDao.getById(orderItemId);
			if (plasTemp != null && plasTemp.getOrderItemId() != null) {
				OrderPlasmidPreparationDTO plasmid = dozer.map(plasTemp, OrderPlasmidPreparationDTO.class);
				DocumentType[] docTypeList = { DocumentType.OIM_PLASMID, DocumentType.OIM_PLASMID_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(orderItemId, docTypeList);
				plasmid.setDocumentList(docList);
				itemDTO.setPlasmidPreparation(plasmid);
			} else {
				itemDTO.setPlasmidPreparation(new OrderPlasmidPreparationDTO());
			}
		} else if (clsId == 11 || clsId == 12 || clsId == 22 || clsId == 28) {
			OrderAntibody antibodyTemp = this.orderAntibodyDao
					.getById(orderItemId);
			if (antibodyTemp != null && antibodyTemp.getOrderItemId() != null) {
				AntibodyDTO antibody = this.dozer.map(antibodyTemp,
						AntibodyDTO.class);
				itemDTO.setAntibody(antibody);
			} else {
				itemDTO.setAntibody(new AntibodyDTO());
			}
		} else if (clsId == 34) {
			OrderOligo oligoTemp = this.orderOligoDao.getById(orderItemId);
			if (oligoTemp != null && oligoTemp.getOrderItemId() != null) {
				OrderOligoDTO oligo = this.dozer.map(oligoTemp,
						OrderOligoDTO.class);
				itemDTO.setOligo(oligo);
			} else {
				itemDTO.setOligo(new OrderOligoDTO());
			}
		} else if (clsId == 40) {
			OrderDnaSequencing orderDnaSequencing = this.orderDnaSequencingDao
					.getById(orderItemId);
			if (orderDnaSequencing != null
					&& orderDnaSequencing.getOrderItemId() != null) {
				DnaSequencingDTO dto = this.dozer.map(orderDnaSequencing,
						DnaSequencingDTO.class);
				if (dto.getPlateId() != null) {
					OrderDsPlates orderDsPlate = orderDsPlatesDao.getById(dto
							.getPlateId());
					if (orderDsPlate != null) {
						dto.setSessPlateId(dto.getPlateId() + "");
						dto.setpName(orderDsPlate.getName());
						dto.setPlateLayout(orderDsPlate.getLayout());
						dto.setPlateNums(orderDsPlate.getNums());
					}
				}
				itemDTO.setDnaSequencing(dto);
			} else {
				itemDTO.setDnaSequencing(new DnaSequencingDTO());
			}
		}
		return itemDTO;
	}
	
	/**
	 * 批量删除OrderTemplate.
	 * 
	 * @param tmplIdList
	 */
	public void delOrderTemplate(List<Integer> tmplIdList) {
		if (tmplIdList != null && !tmplIdList.isEmpty()) {
			this.orderTemplateDao.delTemplateList(tmplIdList);
		}
	}

	private void delSubOrderItem(final OrderMain order,
			List<Integer> subOrderItemIdList) {
		if (subOrderItemIdList != null) {
			for (Integer itemId : subOrderItemIdList) {
				OrderItem item = this.orderItemDao.getById(itemId);
				Integer itemShipAddrId = item.getShiptoAddrId();
				if (item.getType().equals(QuoteItemType.SERVICE.value())) {
					int clsId = item.getClsId();
					if (clsId == 1) {
						this.orderPeptideDao.delete(itemId);
					} else if (clsId == 2) {
						this.orderProteinDao.delete(itemId);
					} else if (clsId == 2) {
						this.orderProteinDao.delete(itemId);
					} else if (clsId == 3) {
						this.orderGeneSynthesisDao.delete(itemId);
					} else if (clsId == 4) {
						this.orderMutagenesisDao.delete(itemId);
					} else if (clsId == 5) {
						this.orderMutationLibrariesDao.delete(itemId);
					} else if (clsId == 6) {
						this.orderOrfCloneDao.delete(itemId);
					} else if (clsId == 7) {
						this.orderSirnaAndMirnaDao.delete(itemId);
					} else if (clsId == 8) {
						this.orderSirnaAndMirnaDao.delete(itemId);
					} else if (clsId == 9) {
						this.orderCustCloningDao.delete(itemId);
					} else if (clsId == 10) {
						this.orderPlasmidPreparationDao.delete(itemId);
					}
					this.orderItemDao.delete(itemId);
					// 如果该OrderItem's shiptoAddrId和Order的shiptoAddrId不一样，
					// 则判断有无其它OrderItem关联到这个地址，如果没有则删除该OrderItem关联的地址.
					if (!itemShipAddrId.equals(order.getShiptoAddrId())) {
						if (this.orderItemDao.getOrderItemCountByAddrId(
								order.getOrderNo(), itemShipAddrId).intValue() < 1) {
							this.orderAddressDao.delete(itemShipAddrId);
						}
					}
				}
			}
		}
	}

	@Transactional(readOnly = true)
	public List<OrderItem> getOrderAllItemList(Integer orderNo) {
		return orderItemDao.getOrderAllItemList(orderNo);
	}

	/**
	 * 获得一个Order中item status为VC的所有Item.
	 * 
	 * @param orderNo
	 * @return
	 * @author wangsf
	 * @since 2010-10-28
	 */
	@Transactional(readOnly = true)
	public List<MfgOrderItem> getVendorConfirmItemList(Integer orderNo) {
		return mfgOrderItemDao.getItemListByType(orderNo,
				OrderItemStatusType.VC);
	}

	/**
	 * 获得一个order中item status不为CN的所有Item
	 */
	@Transactional(readOnly = true)
	public List<OrderItem> getItemListNotInType(Integer orderNo) {
		List<String> itemStatus = new ArrayList<String>();
		itemStatus.add("CN");
		return orderItemDao.getItemListNotInType(orderNo, itemStatus);
	}

	/**
	 * 获得一个order中item status不为CN的所有Item
	 */
	@Transactional(readOnly = true)
	public List<MfgOrderItem> getMfgItemListNotInType(Integer orderNo) {
		List<String> itemStatus = new ArrayList<String>();
		itemStatus.add("CN");
		return mfgOrderItemDao.getItemListNotInType(orderNo, itemStatus);
	}

	/**
	 * 根据orderNo, itemNo唯一确定一个OrderItem.
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @return
	 * @author wangsf
	 * @since 2010-10-28
	 */
	public OrderItem getItemByItemNo(Integer orderNo, Integer itemNo) {
		return this.orderItemDao.getOrderItem(orderNo, itemNo);
	}

	/**
	 * 生成默认的生产订单
	 * 
	 * @param orderNo
	 *            供应商确认订单号
	 * @author lizhang
	 */
	// @SuppressWarnings("unchecked")
	// public boolean saveDefaultWorkOrders(String orderNo) throws Exception {
	// boolean flg = false;
	// Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>)
	// SessionUtil
	// .getRow(SessionConstant.OrderItemList.value(), orderNo);
	// Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
	// .iterator();
	// OrderMain order = this.orderDao.get(Integer.parseInt(orderNo));
	// while (it.hasNext()) {
	// Entry<String, OrderItemDTO> entry = it.next();
	// OrderItemDTO orderItemDTO = entry.getValue();
	// WorkOrder workOrder = this.workOrderDao.findBySNAndSIN(Integer
	// .parseInt(orderNo), orderItemDTO.getItemNo());
	// if (workOrder == null) {
	// workOrder = new WorkOrder();
	// workOrder.setType("Standard");
	// workOrder.setStatus("New");
	// workOrder.setSource("SALES ORDER");
	// workOrder.setSoNo(Integer.parseInt(orderNo));
	// workOrder.setSoItemNo(orderItemDTO.getItemNo());
	// String type = orderItemDTO.getType();
	// Integer clsId = orderItemDTO.getClsId();
	// WorkCenterAssigned workCenterAssigned = this.workCenterAssignedDao
	// .findByTypeAndCId(type, clsId, order.getWarehouseId());
	// workOrder
	// .setWorkCenterId(workCenterAssigned != null
	// && workCenterAssigned.getWorkCenter() != null ? workCenterAssigned
	// .getWorkCenter().getId()
	// : 0);
	// workOrder.setWarehouseId(order.getWarehouseId());
	// workOrder.setItemType(type);
	// workOrder.setClsId(clsId);
	// workOrder.setOrderDate(new Date());
	// workOrder.setExprDate(DateUtils.dayBefore2Date(15));
	// workOrder.setPriority("Medium");
	// workOrder.setCatalogNo(orderItemDTO.getCatalogNo());
	// workOrder.setItemName(orderItemDTO.getName());
	// workOrder.setQuantity(orderItemDTO.getQuantity());
	// workOrder.setQtyUom(orderItemDTO.getQtyUom());
	// workOrder.setSize(orderItemDTO.getSize());
	// workOrder.setSizeUom(orderItemDTO.getSizeUom());
	// if (order.getGsCoId() != null) {
	// workOrder.setCompanyId(Short.parseShort(String
	// .valueOf(order.getGsCoId())));
	// }
	// workOrder.setCreatedBy(SessionUtil.getUserId());
	// }
	// workOrder.setSeqNo((workOrder.getSeqNo() == null ? 0 : workOrder
	// .getSeqNo()) + 1);
	// workOrder.setModifyDate(new Date());
	// workOrder.setModifiedBy(SessionUtil.getUserId());
	// this.workOrderDao.save(workOrder);
	// }
	// flg = true;
	// return flg;
	// }
	@Transactional(readOnly = true)
	public List<Document> getDocument(String type, Integer id) {
		return documentDao.getDocument(type, id);
	}

	/**
	 * 根据salesRepId唯一确定一个SalesRep, 很多时候需要显示的是其fullName属性.
	 * 
	 * @param salesRepId
	 * @return
	 * @author wangsf
	 * @since 2010-11-24
	 */
	public SalesRep getSalesRep(Integer salesRepId) {
		SalesRep techSupport = this.salesRepDao.getById(salesRepId);
		return techSupport;
	}

	/**
	 * 查询订单信息
	 * 
	 * @return
	 */
	public List<OrderMain> searchOrder(List<String> orderNo) {
		List<OrderMain> dtoList = this.orderDao.searchOrder(orderNo);
		return dtoList;
	}

	// ------ Add By Zhang Yong Start
	// --//

	/**
	 * 根据传进来的orderNo和ItemNo以及对应的新旧quantity和size
	 * 更新order表和orderItem表，如果大于原始数量，则更新失败并返回超过的数量
	 * 
	 * @param orderNo
	 * @param itemNo
	 * @param quantity
	 * @param size
	 * @param oldQuantity
	 * @param oldSize
	 * @param isRefund
	 *            true为退票，false为开票
	 */
	public OrderItemOverSizeDTO uptOrderItemStatus(int orderNo, int itemNo,
			int quantity, double size, int oldQuantity, double oldSize,
			boolean isRefund) throws Exception {
		OrderItemOverSizeDTO orderItemOverSizeDTO = null;
		OrderItem orderItem = orderItemDao.getOrderItem(orderNo, itemNo);
		if (orderItem == null) {
			return orderItemOverSizeDTO;
		}
		int sourceQuantity = orderItem.getQuantity();
		double sourceSize = orderItem.getSize();
		// 退票
		if (isRefund) {
			if (sourceQuantity == 1) {
				// 退的size比原始size大
				if ((size + oldSize) > sourceSize) {
					// 返回orderItemOverSizeDTO
					orderItemOverSizeDTO = new OrderItemOverSizeDTO();
					orderItemOverSizeDTO.setOrderNo(orderNo);
					orderItemOverSizeDTO.setItemNo(itemNo);
					orderItemOverSizeDTO.setQuantity(null);
					orderItemOverSizeDTO.setSize((size + oldSize) - sourceSize);
					return orderItemOverSizeDTO;
				} else if ((size + oldSize) == sourceSize) {
					// 更新 orderItem状态为上一状态
					OrderProcessLog orderProcessLog = orderProcessLogDao
							.getOrderHistoryStatus(orderItem.getOrderItemId());
					if (orderProcessLog != null
							&& orderProcessLog.getCurrentStat() != null) {
						orderItem.setStatus(orderProcessLog.getCurrentStat());
					}
				} else {
					// 更新 orderItem状态
					orderItem.setStatus("PI");
				}
			} else {
				// 退的quantity比原始quantity大
				if ((quantity + oldQuantity) > sourceQuantity) {
					// 返回orderItemOverSizeDTO
					orderItemOverSizeDTO = new OrderItemOverSizeDTO();
					orderItemOverSizeDTO.setOrderNo(orderNo);
					orderItemOverSizeDTO.setItemNo(itemNo);
					orderItemOverSizeDTO.setQuantity((quantity + oldQuantity)
							- sourceQuantity);
					orderItemOverSizeDTO.setSize(null);
					return orderItemOverSizeDTO;
				} else if ((quantity + oldQuantity) == sourceQuantity) {
					// 如果传过来的参数中的size不和原始size相同
					if (size != sourceSize) {
						// 返回orderItemOverSizeDTO
						orderItemOverSizeDTO = new OrderItemOverSizeDTO();
						orderItemOverSizeDTO.setOrderNo(orderNo);
						orderItemOverSizeDTO.setItemNo(itemNo);
						orderItemOverSizeDTO.setQuantity(null);
						orderItemOverSizeDTO.setSize(size - sourceSize);
						return orderItemOverSizeDTO;
					}
					// 更新 orderItem状态为上一状态
					OrderProcessLog orderProcessLog = orderProcessLogDao
							.getOrderHistoryStatus(orderItem.getOrderItemId());
					if (orderProcessLog != null
							&& orderProcessLog.getCurrentStat() != null) {
						orderItem.setStatus(orderProcessLog.getCurrentStat());
					}
				} else {
					// 更新 orderItem状态
					orderItem.setStatus("PI");
				}
			}
			orderItemDao.save(orderItem);
			// 更新Order
			uptRefundOrderByOrderItem(orderItem);
		} else {
			// 开票
			if (sourceQuantity == 1) {
				// 原始的size不够减
				if ((size + oldSize) > sourceSize) {
					// 返回orderItemOverSizeDTO
					orderItemOverSizeDTO = new OrderItemOverSizeDTO();
					orderItemOverSizeDTO.setOrderNo(orderNo);
					orderItemOverSizeDTO.setItemNo(itemNo);
					orderItemOverSizeDTO.setQuantity(null);
					orderItemOverSizeDTO.setSize((size + oldSize) - sourceSize);
					return orderItemOverSizeDTO;
				} else if ((size + oldSize) == sourceSize) {
					// 更新 orderItem状态
					orderItem.setStatus("IS");
				} else {
					// 更新 orderItem状态
					orderItem.setStatus("PI");
				}
			} else {
				// 原始的quantity不够减
				if ((quantity + oldQuantity) > sourceQuantity) {
					// 返回orderItemOverSizeDTO
					orderItemOverSizeDTO = new OrderItemOverSizeDTO();
					orderItemOverSizeDTO.setOrderNo(orderNo);
					orderItemOverSizeDTO.setItemNo(itemNo);
					orderItemOverSizeDTO.setQuantity((quantity + oldQuantity)
							- sourceQuantity);
					orderItemOverSizeDTO.setSize(null);
					return orderItemOverSizeDTO;
				} else if ((quantity + oldQuantity) == sourceQuantity) {
					// 如果传过来的参数中的size不和原始size相同
					if (size != sourceSize) {
						// 返回orderItemOverSizeDTO
						orderItemOverSizeDTO = new OrderItemOverSizeDTO();
						orderItemOverSizeDTO.setOrderNo(orderNo);
						orderItemOverSizeDTO.setItemNo(itemNo);
						orderItemOverSizeDTO.setQuantity(null);
						orderItemOverSizeDTO.setSize(size - sourceSize);
						return orderItemOverSizeDTO;
					}
					// 更新 orderItem状态
					orderItem.setStatus("IS");
				} else {
					// 更新 orderItem状态
					orderItem.setStatus("PI");
				}
			}
			orderItemDao.save(orderItem);
			// 更新Order
			uptOrderByOrderItem(orderItem);
		}
		return orderItemOverSizeDTO;
	}

	/**
	 * 开票时根据orderNo获取它所有orderItem，如果有一个或一个以上orderItem的状态为PI时，
	 * 则更新order的状态为PI，如果orderItem的状态全为IS时，则order的状态更新为IS，
	 * 如果全都不是PI状态但含有IS状态的则更新order的状态为PI； 如果全都不是PI状态且不含有IS状态的则更新order的状态为上一状态
	 * 
	 * @param paramOrderItem
	 */
	public void uptOrderByOrderItem(OrderItem paramOrderItem) throws Exception {
		OrderMain order = orderDao.get(paramOrderItem.getOrderNo());
		if (order == null) {
			return;
		}
		// 根据orderNo获取它所有orderItem
		List<OrderItem> orderItemList = orderItemDao
				.getOrderAllItemList(paramOrderItem.getOrderNo());
		if (orderItemList == null || orderItemList.size() == 0) {
			return;
		}
		int countIS = 0;
		int countNotPI = 0;
		for (OrderItem orderItem : orderItemList) {
			if (("PI").equals(orderItem.getStatus())) {
				order.setStatus("PI");
				orderDao.save(order);
				return;
			} else {
				if (("IS").equals(orderItem.getStatus())) {
					countIS++;
				}
				countNotPI++;
			}
		}
		// 如果全是IS状态则更新order状态为IS状态
		if (orderItemList.size() == countIS) {
			order.setStatus("IS");
			orderDao.save(order);
			return;
		}

		if (orderItemList.size() == countNotPI) {
			// 如果全都不是PI状态但含有IS状态的则更新order的状态为PI
			if (countIS != 0) {
				order.setStatus("PI");
				orderDao.save(order);
				return;
			}
			// 如果全都不是PI状态且不含有IS状态的则更新order的状态为上一状态
			this.uptOrderHistoryStatus(paramOrderItem);
		}
	}

	/**
	 * 退票时根据orderNo获取它所有orderItem， 如果有一个或一个以上orderItem的状态为PI时， 则更新order的状态为PI
	 * 如果全都不是PI状态的则更新order的状态为上一状态
	 * 
	 * @param paramOrderItem
	 */
	public void uptRefundOrderByOrderItem(OrderItem paramOrderItem)
			throws Exception {
		OrderMain order = orderDao.get(paramOrderItem.getOrderNo());
		if (order == null) {
			return;
		}
		// 根据orderNo获取它所有orderItem
		List<OrderItem> orderItemList = orderItemDao
				.getOrderAllItemList(paramOrderItem.getOrderNo());
		if (orderItemList == null || orderItemList.size() == 0) {
			return;
		}
		for (OrderItem orderItem : orderItemList) {
			if (("PI").equals(orderItem.getStatus())) {
				order.setStatus("PI");
				orderDao.save(order);
				return;
			} else {
				// 如果全都不是PI状态的则更新order的状态为上一状态
				this.uptOrderHistoryStatus(paramOrderItem);
			}
		}
	}

	/**
	 * 根据orderItemId查询order的上一状态，并更新order
	 * 
	 * @throws Exception
	 */
	public void uptOrderHistoryStatus(OrderItem paramOrderItem)
			throws Exception {
		OrderProcessLog orderProcessLog = orderProcessLogDao
				.getOrderHistoryStatus(paramOrderItem.getOrderItemId());
		if (orderProcessLog != null && orderProcessLog.getCurrentStat() != null) {
			OrderMain orderMain = orderDao.get(paramOrderItem.getOrderNo());
			if (orderMain != null) {
				orderMain.setStatus(orderProcessLog.getCurrentStat());
				orderDao.save(orderMain);
			}
		}
	}

	@Transactional(readOnly = true)
	public Page<OrderMainBean> searchOrderByCustomerNo(
			Page<OrderMainBean> orderMainBeanPage, List<PropertyFilter> filters)
			throws Exception {
		// 获得分页请求相关数据：如第几页
		if (!orderMainBeanPage.isOrderBySetted()) {
			orderMainBeanPage.setOrderBy("orderNo");
			orderMainBeanPage.setOrder(Page.DESC);
		}
		orderMainBeanPage.setPageSize(1);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				filterMap.put(filters.get(i).getPropertyName(), filters.get(i)
						.getPropertyValue());
			}
		}
		if (filterMap.size() > 0) {
			Map<String, List<Integer>> resultMap = orderMainBeanDao
					.searchOrderByCustomerNo(orderMainBeanPage, filterMap);
			if (resultMap != null
					&& resultMap.containsKey("orderMainBeanAllList")
					&& resultMap.containsKey("orderMainBeanlist")) {
				List<Integer> orderMainBeanAllList = resultMap
						.get("orderMainBeanAllList");
				List<Integer> orderMainBeanlist = resultMap
						.get("orderMainBeanlist");
				if (orderMainBeanAllList != null
						&& orderMainBeanAllList.size() > 0) {
					long totalCount = Long.valueOf(orderMainBeanAllList.size());
					orderMainBeanPage.setTotalCount(totalCount);
					int first = orderMainBeanPage.getFirst();
					if (first % orderMainBeanPage.getPageSize() == 0) {
						if (first / orderMainBeanPage.getPageSize() != 0) {
							orderMainBeanPage.setPageNo(Integer
									.valueOf((int) (first / orderMainBeanPage
											.getPageSize())));
						} else {
							orderMainBeanPage.setPageNo(1);
						}
					} else {
						if (first / orderMainBeanPage.getPageSize() != 0) {
							orderMainBeanPage.setPageNo(Integer
									.valueOf((int) (first / orderMainBeanPage
											.getPageSize())) + 1);
						} else {
							orderMainBeanPage.setPageNo(1);
						}
					}
				}
				if (orderMainBeanlist != null && orderMainBeanlist.size() > 0) {
					List<OrderMainBean> filterOrderMainBean = new ArrayList<OrderMainBean>();
					for (int i = 0; i < orderMainBeanlist.size(); i++) {
						OrderMainBean orderMainBean = new OrderMainBean();
						orderMainBean.setOrderNo(orderMainBeanlist.get(i));
						filterOrderMainBean.add(orderMainBean);
					}
					orderMainBeanPage.setResult(filterOrderMainBean);
				}
			}
			return orderMainBeanPage;
		} else {
			return orderMainBeanPage;
		}
	}

	@Transactional(readOnly = true)
	public Page<PurchaseOrder> searchOrderByVendorNo(
			final Page<PurchaseOrder> purchaseOrderBeanPage,
			List<PropertyFilter> filters, Integer filter_EQI_vendorNo)
			throws Exception {
		Vendor vendor = null;
		Map<String, Object> filterMap = new HashMap<String, Object>();
		if (filter_EQI_vendorNo != null && filter_EQI_vendorNo > 0) {
			vendor = vendorDao.get(filter_EQI_vendorNo);
		}
		if (vendor == null || vendor.getVendorNo() == null
				|| vendor.getVendorNo() <= 0) {
			return null;
		} else {
			filterMap.put("vendorNo", vendor.getVendorNo());
		}
		purchaseOrderBeanPage.setPageSize(1);
		if (filters != null && filters.size() > 0) {
			for (int i = 0; i < filters.size(); i++) {
				filterMap.put(filters.get(i).getPropertyName(), filters.get(i)
						.getPropertyValue());
			}
		}
		if (filterMap.size() > 0) {
			Map<String, List<Integer>> resultMap = purchaseOrderDao
					.searchOrderByVendorNo(purchaseOrderBeanPage, filterMap);
			if (resultMap != null
					&& resultMap.containsKey("purchaseOrderBeanAllList")
					&& resultMap.containsKey("purchaseOrderBeanlist")) {
				List<Integer> purchaseOrderBeanAllList = resultMap
						.get("purchaseOrderBeanAllList");
				List<Integer> purchaseOrderBeanlist = resultMap
						.get("purchaseOrderBeanlist");
				if (purchaseOrderBeanAllList != null
						&& purchaseOrderBeanAllList.size() > 0) {
					long totalCount = Long.valueOf(purchaseOrderBeanAllList
							.size());
					purchaseOrderBeanPage.setTotalCount(totalCount);
					int first = purchaseOrderBeanPage.getFirst();
					if (first % purchaseOrderBeanPage.getPageSize() == 0) {
						if (first / purchaseOrderBeanPage.getPageSize() != 0) {
							purchaseOrderBeanPage
									.setPageNo(Integer
											.valueOf((int) (first / purchaseOrderBeanPage
													.getPageSize())));
						} else {
							purchaseOrderBeanPage.setPageNo(1);
						}
					} else {
						if (first / purchaseOrderBeanPage.getPageSize() != 0) {
							purchaseOrderBeanPage
									.setPageNo(Integer
											.valueOf((int) (first / purchaseOrderBeanPage
													.getPageSize())) + 1);
						} else {
							purchaseOrderBeanPage.setPageNo(1);
						}
					}
				}
				if (purchaseOrderBeanlist != null
						&& purchaseOrderBeanlist.size() > 0) {
					List<PurchaseOrder> filterOrderMainBean = new ArrayList<PurchaseOrder>();
					for (int i = 0; i < purchaseOrderBeanlist.size(); i++) {
						PurchaseOrder purchaseOrderBean = new PurchaseOrder();
						purchaseOrderBean.setOrderNo(purchaseOrderBeanlist
								.get(i));
						filterOrderMainBean.add(purchaseOrderBean);
					}
					purchaseOrderBeanPage.setResult(filterOrderMainBean);
				}
			}
			return purchaseOrderBeanPage;
		} else {
			return purchaseOrderBeanPage;
		}
	}

	@Transactional(readOnly = true)
	public Page<OrderMainBean> searchOrderByfilter(
			final Page<OrderMainBean> page, final List<PropertyFilter> filters)
			throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		List<Criterion> criterionList = new ArrayList<Criterion>();
		// 判断当前用户是否含有内部订单管理员角色，是，则查询customer type为Internal的Order
		boolean internalOrderManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_INTERNAL_ORDER_MANAGER);
		// 判断当前用户是否含有销售经理角色
		boolean salesManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);
		boolean pmo = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_PMO);
		if (Constants.USERNAME_ADMIN.equals(userName) || salesManager
				|| internalOrderManager || pmo) {
			return orderMainBeanDao.getOrderByfilter(page, criterionList,
					filters, internalOrderManager);
		}
		Map<String, Object> salesRoleAndUserIdsMap = salesRepDao
				.getSameGroupUser();
		if (salesRoleAndUserIdsMap.get("function") != null
				&& salesRoleAndUserIdsMap.get("User_Ids") != null) {
			String salesRole = salesRoleAndUserIdsMap.get("function")
					.toString();
			Integer[] userIdsArr = (Integer[]) salesRoleAndUserIdsMap
					.get("User_Ids");
			if (SalesRepSalesRole.SALES_CONTACT.value().equals(salesRole)) {
				Criterion criterionSalesContact = Restrictions.in(
						"salesContactId", userIdsArr);
				Criterion altCriterionSalesContact = Restrictions.in(
						"altSalesContactId", userIdsArr);
				Criterion allCriterionSalesContact = Restrictions.or(
						criterionSalesContact, altCriterionSalesContact);
				criterionList.add(allCriterionSalesContact);
			} else if (SalesRepSalesRole.TECH_SUPPORT.value().equals(salesRole)) {
				Criterion criterionSalesContact = Restrictions.in(
						"techSupportId", userIdsArr);
				Criterion altCriterionSalesContact = Restrictions.in(
						"altTechSupportId", userIdsArr);
				Criterion allCriterionSalesContact = Restrictions.or(
						criterionSalesContact, altCriterionSalesContact);
				criterionList.add(allCriterionSalesContact);
			} else if (SalesRepSalesRole.PROJECT_SUPPORT.value().equals(
					salesRole)) {
				Criterion criterionSalesContact = Restrictions.in(
						"projectSupportId", userIdsArr);
				Criterion altCriterionSalesContact = Restrictions.in(
						"altProjectSupportId", userIdsArr);
				Criterion allCriterionSalesContact = Restrictions.or(
						criterionSalesContact, altCriterionSalesContact);
				criterionList.add(allCriterionSalesContact);
			}
			return orderMainBeanDao.getOrderByfilter(page, criterionList,
					filters, internalOrderManager);
		}
		return page;
	}

	
	// ------ Add By Zhang Yong End --//

	/**
	 * 获得Order打印对象OrderPrintDTO
	 * 
	 * @param orderNo
	 * @author lizhang
	 */
	public OrderPrintDTO getOrderPrintDTO(Integer orderNo) throws Exception {
		OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
		boolean isHasItem = false;// orderNo所对应的order是否有子item
		int leadTime = 0;
		String orderType = null;// 保存第一个orderItem的itemType;
		String total_price_desc = null;
		String companyName = "";
		@SuppressWarnings("unused")
		String total_price_desc1 = null;
		StringBuffer result = new StringBuffer();
		StringBuffer detail = new StringBuffer();
		String turnRound = null;
		StringBuffer tableBody = new StringBuffer();
		StringBuffer sub_discount_department = new StringBuffer();
		StringBuffer ship_price_department = new StringBuffer();
		StringBuffer tax_department = new StringBuffer();
		StringBuffer total_include_tax = new StringBuffer();
		StringBuffer turnaround_comments = new StringBuffer();
		Double total = 0.0;

		OrderMain order = this.orderDao.getById(orderNo);
		Customer customer = null;
		User userSupport = null;
		User userSale = null;
		PbCurrency currency = null;
		@SuppressWarnings("unused")
		Employee employeeSale = null;
		Employee employeeSupport = null;
		Departments department = null;
		Organization organization = null;
		@SuppressWarnings("unused")
		Company companySupport = null;
		@SuppressWarnings("unused")
		Company companySale = null;
		@SuppressWarnings("unused")
		Company company = null;
		PbCountry country = null;
		List<OrderItem> orderItemList = null;
		if (order != null) {
			customer = order.getCustNo() == null ? null : this.customerDao
					.getById(order.getCustNo());
			currency = order.getOrderCurrency() == null ? null
					: this.currencyDao.findUniqueBy("currencyCode",
							order.getOrderCurrency());
			userSupport = order.getTechSupport() == null ? null : this.userDao
					.getById(order.getTechSupport());
			userSale = order.getSalesContact() == null ? null : this.userDao
					.getById(order.getSalesContact());
			List<String> itemStatus = new ArrayList<String>();
			itemStatus.add("CN");
			orderItemList = order.getOrderNo() == null ? null
					: this.orderItemDao.getItemListNotInType(
							order.getOrderNo(), itemStatus);
		}
		if (customer != null) {
			department = customer.getDeptId() == null ? null
					: this.departmentDao.getById(customer.getDeptId());
			organization = customer.getOrgId() == null ? null
					: this.organizationDao.getById(customer.getOrgId());
			company = customer.getCompanyId() == null ? null : this.companyDao
					.getById(customer.getCompanyId());
			country = this.pbCountryDao.findUniqueBy("countryCode",
					customer.getCountry());
		}
		if (userSupport != null) {
			companySupport = userSupport.getCompanyId() == null ? null
					: this.companyDao.getById(userSupport.getCompanyId());
			employeeSupport = userSupport.getEmployee();
		}
		if (userSale != null) {
			companySale = userSale.getCompanyId() == null ? null
					: this.companyDao.getById(userSale.getCompanyId());
			employeeSale = userSale.getEmployee();
		}
		turnaround_comments
				.append("This is only for reference, not an order confirmation. All payments must be made in")
				.append(currency != null ? currency.getDescription() : "")
				.append(". This quotation may not include incidental charges, such as shipping and tax. No sales tax is charged for all international orders. The total charge will be determined at the time the order is placed. ");
		result.append(employeeSupport == null ? "" : employeeSupport
				.getEmployeeName());
		result.append("<br/>");
		result.append(userSupport == null ? "" : userSupport.getPosition());
		result.append("<br/>");

		if (country != null
				&& (country.getContinent().startsWith("North_America") || country
						.getContinent().startsWith("South_America"))) {
			companyName = "GenScript USA Inc.";
			orderPrintDTO.setCompanyName(companyName);
			orderPrintDTO.setAddress1("860 Centennial Ave.");
			orderPrintDTO.setAddress2("Piscataway, NJ 08854, USA");
			orderPrintDTO.setTelephone("Tel:732-885-9188 ext"
					+ (employeeSupport == null ? "" : employeeSupport
							.getPhoneExt()));
			orderPrintDTO.setFax("Fax:732-210-0262");
		} else {
			companyName = "GenScript";
			orderPrintDTO.setCompanyName(companyName);
			orderPrintDTO.setAddress1("Wheelock House, Level 18");
			orderPrintDTO.setAddress2("20 Pedder Street, Central, Hong Kong");
			orderPrintDTO.setTelephone("Tel:+852-800-964-684 ext"
					+ (employeeSupport == null ? "" : employeeSupport
							.getPhoneExt()));
			orderPrintDTO.setFax("Fax:+852-3015-1539");
		}
		orderPrintDTO.setCustEmail(userSupport == null ? "" : userSupport
				.getEmail());
		orderPrintDTO.setWeb("Web: www.genscript.com");
		Integer tmpShipMethod = 0;
		if (orderItemList != null && orderItemList.size() > 0) {
			int i = 0;
			boolean shipViaConsistent = true;
			for (OrderItem orderItem : orderItemList) {
				if (orderItem == null) {
					continue;
				}
				i++;
				String orderItemType = null;
				Integer clsId = null;
				if (!isHasItem) {
					if (QuoteItemType.PRODUCT.value().equals(
							orderItem.getType())) {
						Product product = this.productDao.findUniqueBy(
								"catalogNo", orderItem.getCatalogNo());
						clsId = product != null ? product.getProductClsId()
								: null;

					} else if (QuoteItemType.SERVICE.value().equals(
							orderItem.getType())) {
						com.genscript.gsscm.serv.entity.Service service = this.serviceDao
								.findUniqueBy("catalogNo",
										orderItem.getCatalogNo());
						clsId = service != null ? service.getServiceClsId()
								: null;
					}
					if (clsId != null && clsId == 16) {// category =
														// "Services-Pharmaceutical"
						isHasItem = true;
					}
				}
				// ship via
				if (shipViaConsistent) {
					if (tmpShipMethod != null && tmpShipMethod.intValue() != 0
							&& orderItem.getShipMethod() != null
							&& !tmpShipMethod.equals(orderItem.getShipMethod())) {
						tmpShipMethod = 0;
						shipViaConsistent = false;
					} else {
						tmpShipMethod = orderItem.getShipMethod();
					}
				}

				// 获取quoteItem类型
				if (QuoteItemType.PRODUCT.value().equals(orderItem.getType())) {
					ProductClass pdtClass = this.productClassDao
							.getById(orderItem.getClsId());
					orderItemType = pdtClass == null ? null : pdtClass
							.getName();
				} else {
					ServiceClass servClass = this.serviceClassDao
							.getById(orderItem.getClsId());
					orderItemType = servClass == null ? null : servClass
							.getName();
				}
				if (i == 1) {
					// 以第一个item的type作为quote的type
					orderType = orderItemType;
				}
				if (StringUtils.isEmpty(orderItemType)) {
					continue;
				}
				if (orderItem.getParentId() != null
						&& orderItem.getParentId().intValue() != 0) {
					OrderItem parentItem = this.orderItemDao.getById(orderItem
							.getParentId());
					String parentItemType = null;
					if (parentItem != null) {
						if (QuoteItemType.PRODUCT.value().equals(
								parentItem.getType())) {
							ProductClass pdtClass = this.productClassDao
									.getById(parentItem.getClsId());
							parentItemType = pdtClass == null ? null : pdtClass
									.getName();
						} else {
							ServiceClass servClass = this.serviceClassDao
									.getById(parentItem.getClsId());
							parentItemType = servClass == null ? null
									: servClass.getName();
						}
					}
					if ((orderItemType.toLowerCase().equals("peptide")
							&& parentItemType != null && !parentItemType
							.toLowerCase().equals("peptide"))
							|| orderItemType.toLowerCase().endsWith("step")) {
						continue;
					}
					if ((orderItemType.toLowerCase().startsWith(
							"custom cloning") || orderItemType.toLowerCase()
							.startsWith("plasmid preparation"))
							&& parentItemType != null
							&& parentItemType.toLowerCase().startsWith(
									"mutagenesis")) {
						continue;
					}
				}
				if (orderItem.getShipSchedule() != null
						&& orderItem.getShipSchedule() > leadTime) {
					leadTime = orderItem.getShipSchedule();
				}
				tableBody.append("<tr><td align='center'>")
						.append(orderItem.getQuantity()).append("</td>");
				tableBody.append("<td align='center'>")
						.append(orderItem.getItemNo()).append("</td>");
				tableBody.append("<td>");

				StringBuffer itemDetail = new StringBuffer();
				StringBuffer itemDesc = new StringBuffer();
				StringBuffer itemSeq = new StringBuffer();
				String orderItemGene = orderItem.getName();
				String description = null;
				String vector = null;
				String Length = null;
				String quanitity = "";
				Double unitPriceD = orderItem.getUnitPrice();
				if (orderItemType.toLowerCase().startsWith("gene")) {
					OrderGeneSynthesis orderGeneSynthesis = orderGeneSynthesisDao
							.getById(orderItem.getOrderItemId());
					orderItemGene = orderItemGene
							+ ": "
							+ (orderGeneSynthesis == null ? null
									: orderGeneSynthesis.getGeneName());
					description = orderGeneSynthesis == null ? null
							: orderGeneSynthesis.getSequence();
					vector = orderGeneSynthesis.getStdVectorName();
					if (StringUtils.isEmpty(vector)) {
						vector = "PUC57";
					}
					quanitity = orderGeneSynthesis.getStdPlasmidWt()
							+ orderGeneSynthesis.getStdPlasmidWtUom();
					Length = orderGeneSynthesis != null
							&& orderGeneSynthesis.getSeqLength() != null ? orderGeneSynthesis
							.getSeqLength() : "";
					itemDesc = new StringBuffer(orderItemGene);
					itemDesc.append(",Len:").append(Length)
							.append("bp,Vector:").append(vector);
					if (StringUtils.isNotEmpty(quanitity)) {
						itemDesc.append(",Quantity:").append(quanitity);
					}
					if ("D".equals(orderGeneSynthesis.getCloningFlag())) {
						itemDesc.append(";Direct Cloning:")
								.append(orderGeneSynthesis != null
										&& orderGeneSynthesis.getVectorSize() != null ? orderGeneSynthesis
										.getVectorSize() : "")
								.append(",")
								.append(orderGeneSynthesis != null ? orderGeneSynthesis
										.getVectorResistance() : "")
								.append(",")
								.append(orderGeneSynthesis != null ? orderGeneSynthesis
										.getVectorCopyNo() : "");
					}

				} else if (orderItemType.toLowerCase().startsWith(
						"custom cloning")) {
					OrderCustCloning orderCustCloning = orderCustCloningDao
							.getById(orderItem.getOrderItemId());
					if (orderItem.getParentId() != null
							&& orderItem.getParentId().intValue() != 0) {
						OrderItem parentItem = this.orderItemDao
								.getById(orderItem.getParentId());
						if (parentItem != null) {
							String parentItemType = null;
							if (QuoteItemType.PRODUCT.value().equals(
									parentItem.getType())) {
								ProductClass pdtClass = this.productClassDao
										.getById(parentItem.getClsId());
								parentItemType = pdtClass == null ? null
										: pdtClass.getName();
							} else {
								ServiceClass servClass = this.serviceClassDao
										.getById(parentItem.getClsId());
								parentItemType = servClass == null ? null
										: servClass.getName();
							}
							if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"gene")) {
								OrderGeneSynthesis orderGeneSynthesis = orderGeneSynthesisDao
										.getById(parentItem.getOrderItemId());
								itemDesc = new StringBuffer(
										parentItem != null ? parentItem
												.getName() : "")
										.append(": ")
										.append(orderGeneSynthesis == null ? null
												: orderGeneSynthesis
														.getGeneName())
										.append("-Subcloning:");
							}else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"mutation libraries")) {
								OrderMutationLibraries orderMutationLibraries = orderMutationLibrariesDao
										.getById(parentItem.getOrderItemId());
								if (orderMutationLibraries != null) {
									itemDesc.append("Custom Cloning: ");
									itemDesc.append(
											orderMutationLibraries == null ? null
													: orderMutationLibraries
															.getConstructName())
											.append("-Subcloning: ");
								//	itemDesc.append("Vector name: ").append(
											itemDesc.append("Vector: ").append(
											orderCustCloning.getTgtVector());
								}
									
							} else {
								/*itemDesc.append(orderItem.getName())
										.append(":")
										.append(orderCustCloning
												.getTgtInsertName())
										.append(",");*/
								
								
								itemDesc.append(orderItem.getName())
								.append(":")
								.append(orderCustCloning
										.getTgtInsertName()==null?"":orderCustCloning
												.getTgtInsertName())
								.append(",Vector: ");
							}
							itemDesc.append(", Cloning method: ")
							.append(orderCustCloning
									.getTgtCloningMethod() == null ? ""
									: orderCustCloning
											.getTgtCloningMethod());
					if (orderCustCloning != null) {
						itemDesc.append(", Cloning site: ").append(
								orderCustCloning.getTgtCloningSite());
					}
							if ("N".equals(orderCustCloning.getCloningFlag())) {
								itemDesc.append(orderCustCloning.getTgtVector() != null ? "Vector:"
										+ orderCustCloning.getTgtVector()
										: "");
							} else {
								List<OrderItem> itemList = this.orderItemDao
										.findBy("parentId",
												orderItem.getOrderItemId());
								OrderCustCloning childorderCustCloning = null;
								for (OrderItem item : itemList) {
									childorderCustCloning = orderCustCloningDao
											.getById(item.getOrderItemId());
									if (childorderCustCloning != null) {
										break;
									}
								}
								itemDesc.append(childorderCustCloning != null ? "Vector:"
										+ childorderCustCloning.getTgtVector()
										: "");
							}
							if ("N".equals(orderCustCloning
									.getPlasmidPrepFlag())) {
								itemDesc.append(",Quantity: 4 ug");
							} else {
								List<OrderItem> itemList = this.orderItemDao
										.findBy("parentId",
												orderItem.getOrderItemId());
								OrderPlasmidPreparation childOrderPlasmidPreparation = null;
								for (OrderItem item : itemList) {
									childOrderPlasmidPreparation = orderPlasmidPreparationDao
											.getById(item.getOrderItemId());
									if (childOrderPlasmidPreparation != null) {
										break;
									}
								}
								if (childOrderPlasmidPreparation != null) {
									itemDesc.append(",Quantity: ")
											.append(childOrderPlasmidPreparation
													.getPrepWeight()
													+ childOrderPlasmidPreparation
															.getPrepWtUom());
								}
							}
						}

					} else {
						itemDesc.append("").append(orderItemGene).append(": ");
						itemDesc.append(" Insert: ")
						.append(orderCustCloning.getTmplInsertName() == null ? ""
								: orderCustCloning.getTmplInsertName());
				itemDesc.append(", Cloning method: ")
						.append(orderCustCloning.getTmplCloningMethod() == null ? ""
								: orderCustCloning
										.getTmplCloningMethod()); 
				itemDesc.append(", Gene: ").append(orderItemGene);
				if (orderItemGene != null) {
					itemDesc.append(", Cloning site: ").append(
							orderCustCloning.getTmplCloningSite());
				}
				itemDesc.append(",Quantity: 4 ug");
						
						
			/*			
						itemDesc.append(orderItem.getName()).append(":")
								.append(orderCustCloning.getTgtInsertName())
								.append(",");
						if ("N".equals(orderCustCloning.getCloningFlag())) {
							itemDesc.append(orderCustCloning.getTgtVector() != null ? "Vector:"
									+ orderCustCloning.getTgtVector()
									: "");
						} else {
							List<OrderItem> itemList = this.orderItemDao
									.findBy("parentId",
											orderItem.getOrderItemId());
							OrderCustCloning childOrderCustCloning = null;
							for (OrderItem item : itemList) {
								childOrderCustCloning = orderCustCloningDao
										.getById(item.getOrderItemId());
								if (childOrderCustCloning != null) {
									break;
								}
							}
							itemDesc.append(childOrderCustCloning != null ? "Vector:"
									+ childOrderCustCloning.getTgtVector()
									: "");
						}
						if ("N".equals(orderCustCloning.getPlasmidPrepFlag())) {
							itemDesc.append(",Quantity: 4 ug");
						} else {
							List<OrderItem> itemList = this.orderItemDao
									.findBy("parentId",
											orderItem.getOrderItemId());
							OrderPlasmidPreparation childOrderPlasmidPreparation = null;
							for (OrderItem item : itemList) {
								childOrderPlasmidPreparation = orderPlasmidPreparationDao
										.getById(item.getOrderItemId());
								if (childOrderPlasmidPreparation != null) {
									break;
								}
							}
							if (childOrderPlasmidPreparation != null) {
								itemDesc.append(",Quantity: ").append(
										childOrderPlasmidPreparation
												.getPrepWeight()
												+ childOrderPlasmidPreparation
														.getPrepWtUom());
							}
						}

					}*/
					}

				} else if (orderItemType.toLowerCase().startsWith(
						"plasmid preparation")) {
					OrderPlasmidPreparation orderPlasmidPreparation = orderPlasmidPreparationDao
							.getById(orderItem.getOrderItemId());

					if (orderItem.getParentId() != null
							&& orderItem.getParentId().intValue() != 0) {
						OrderItem parentItem = this.orderItemDao
								.getById(orderItem.getParentId());
						if (parentItem != null) {
							String parentItemType = null;
							if (QuoteItemType.PRODUCT.value().equals(
									parentItem.getType())) {
								ProductClass pdtClass = this.productClassDao
										.getById(parentItem.getClsId());
								parentItemType = pdtClass == null ? null
										: pdtClass.getName();
							} else {
								ServiceClass servClass = this.serviceClassDao
										.getById(parentItem.getClsId());
								parentItemType = servClass == null ? null
										: servClass.getName();
							}
							if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"gene")) {
								itemDesc = new StringBuffer(
										parentItem != null ? parentItem
												.getName() : "")
										.append("-Subplasmid:");
								if (orderPlasmidPreparation != null
										&& orderPlasmidPreparation
												.getPrepWeight() != null) {
									itemDesc.append("Quantity:")
											.append(orderPlasmidPreparation
													.getPrepWeight())
											.append(orderPlasmidPreparation
													.getPrepWtUom());
								}
							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"mutation libraries")) {
								OrderMutationLibraries orderMutationLibraries = orderMutationLibrariesDao
										.getById(parentItem.getOrderItemId());
								if (orderMutationLibraries != null) {
								itemDesc.append(orderMutationLibraries.getConstructName()+"- Custom Plasmid Preparation: ");
								itemDesc.append("Quantity: ")
										.append(orderPlasmidPreparation.getPrepWeight())
										.append(orderPlasmidPreparation.getPrepWtUom());
								itemDesc.append(", Grade: ")
										.append(orderPlasmidPreparation
												.getQualityGrade() == null ? ""
												: orderPlasmidPreparation
														.getQualityGrade())
										.append("");
								}
									
							}else {
								/*itemDesc.append(orderItem.getCatalogNo())
										.append(":")
										.append(orderItem.getName());
								itemDesc.append("<br>")
										.append("Plasmid Size:")
										.append(orderPlasmidPreparation
												.getPlasmidSize());
								itemDesc.append("<br>")
										.append("Antibiotic Resistance:")
										.append(orderPlasmidPreparation
												.getAntibioticResistance());
								itemDesc.append("<br>").append(
										"Bacterial Strain:");
								itemDesc.append("<br>")
										.append("Comment:")
										.append(orderPlasmidPreparation
												.getComments());*/
								itemDesc.append(orderItem.getName())
								.append(":");
						itemDesc.append("Quantity: ")
								.append(orderPlasmidPreparation
										.getPrepWeight())
								.append(orderPlasmidPreparation
										.getPrepWtUom());
						itemDesc.append(", Grade: ")
								.append(orderPlasmidPreparation
										.getQualityGrade() == null ? ""
										: orderPlasmidPreparation
												.getQualityGrade())
								.append("");
							}
						}

					} else {
						/*itemDesc.append(orderItem.getCatalogNo()).append(":")
								.append(orderItem.getName());
						itemDesc.append("<br>")
								.append("Plasmid Size:")
								.append(orderPlasmidPreparation
										.getPlasmidSize());
						itemDesc.append("<br>")
								.append("Antibiotic Resistance:")
								.append(orderPlasmidPreparation
										.getAntibioticResistance());
						itemDesc.append("<br>").append("Bacterial Strain:");
						itemDesc.append("<br>").append("Comment:")
								.append(orderPlasmidPreparation.getComments());*/
						 
						
						itemDesc.append(orderItem.getName()).append(": "); 
						itemDesc.append("Plasmid: ")
								.append(orderPlasmidPreparation
										.getPlasmidName() == null ? ""
										: orderPlasmidPreparation
												.getPlasmidName());
						itemDesc.append(", Plasmid Size: ")
								.append(orderPlasmidPreparation
										.getPlasmidSize() == null ? ""
										: orderPlasmidPreparation
												.getPlasmidSize());
						itemDesc.append(", Quantity: ")
								.append(orderPlasmidPreparation.getPrepWeight())
								.append(orderPlasmidPreparation.getPrepWtUom());
						itemDesc.append(", Grade: ")
								.append(orderPlasmidPreparation
										.getQualityGrade() == null ? ""
										: orderPlasmidPreparation
												.getQualityGrade()).append("");
					}
				} else if (orderItemType.toLowerCase()
						.startsWith("mutagenesis")) {
					if (orderItem.getParentId() != null
							&& orderItem.getParentId().intValue() != 0) {
						OrderItem genItem = this.orderItemDao.getById(orderItem
								.getParentId());
						itemDesc = new StringBuffer(
								genItem != null ? genItem.getName() : "")
								.append("-Mutagenesis:");
						OrderMutagenesis orderMutagenesis = orderMutagenesisDao
								.getById(orderItem.getOrderItemId());
						if (orderMutagenesis == null) {
							continue;
						}
						itemDesc.append(orderMutagenesis.getVariantName());
						if ("N".equals(orderMutagenesis.getCloningFlag())) {
							itemDesc.append(",Vector: pUC57");
						} else {
							List<OrderItem> itemList = this.orderItemDao
									.findBy("parentId",
											orderItem.getOrderItemId());
							OrderCustCloning orderCustCloning = null;
							for (OrderItem item : itemList) {
								orderCustCloning = orderCustCloningDao
										.getById(item.getOrderItemId());
								if (orderCustCloning != null) {
									break;
								}
							}
							itemDesc.append(orderCustCloning != null ? ",Vector:"
									+ orderCustCloning.getTgtVector()
									: "");
						}
						if ("N".equals(orderMutagenesis.getPlasmidPrepFlag())) {
							itemDesc.append(",Quantity: 4 ug");
						} else {
							List<OrderItem> itemList = this.orderItemDao
									.findBy("parentId",
											orderItem.getOrderItemId());
							OrderPlasmidPreparation orderPlasmidPreparation = null;
							for (OrderItem item : itemList) {
								orderPlasmidPreparation = orderPlasmidPreparationDao
										.getById(item.getOrderItemId());
								if (orderPlasmidPreparation != null) {
									break;
								}
							}
							if (orderPlasmidPreparation != null) {
								itemDesc.append(",Quantity: ").append(
										orderPlasmidPreparation.getPrepWeight()
												+ orderPlasmidPreparation
														.getPrepWtUom());
							}
						}
						List<OrderItem> childItemList = this.orderItemDao
								.findBy("parentId", orderItem.getOrderItemId());
						for (int j = 0; j < childItemList.size(); j++) {
							OrderItem child = childItemList.get(j);
							unitPriceD = unitPriceD + child.getUnitPrice();
						}
					} else {
						itemDesc.append(orderItem.getCatalogNo()).append(":")
								.append(orderItem.getName());
						OrderMutagenesis orderMutagenesis = orderMutagenesisDao
								.getById(orderItem.getOrderItemId());
						if (orderMutagenesis == null) {
							continue;
						}
						if ("N".equals(orderMutagenesis.getCloningFlag())) {
							itemDesc.append(";").append(
									orderMutagenesis.getTmplCloningSite());
						} else {
							List<OrderItem> itemList = this.orderItemDao
									.findBy("parentId",
											orderItem.getOrderItemId());
							OrderCustCloning orderCustCloning = null;
							for (OrderItem item : itemList) {
								orderCustCloning = orderCustCloningDao
										.getById(item.getOrderItemId());
								if (orderCustCloning != null) {
									break;
								}
							}
							itemDesc.append(";").append(
									orderCustCloning != null ? orderCustCloning
											.getTgtCloningSite() : "");
						}
						itemDesc.append(" ").append(
								orderMutagenesis.getVariantName());
						if ("N".equals(orderMutagenesis.getPlasmidPrepFlag())) {
							itemDesc.append(",Quantity: 4 ug");
						} else {
							List<OrderItem> itemList = this.orderItemDao
									.findBy("parentId",
											orderItem.getOrderItemId());
							OrderPlasmidPreparation orderPlasmidPreparation = null;
							for (OrderItem item : itemList) {
								orderPlasmidPreparation = orderPlasmidPreparationDao
										.getById(item.getOrderItemId());
								if (orderPlasmidPreparation != null) {
									break;
								}
							}
							itemDesc.append(",Quantity: ")
									.append(orderPlasmidPreparation != null ? (orderPlasmidPreparation
											.getPrepWeight() + orderPlasmidPreparation
											.getPrepWtUom())
											: "");
						}
					}
					List<OrderItem> childItemList = this.orderItemDao.findBy(
							"parentId", orderItem.getOrderItemId());
					if (childItemList != null && childItemList.size() > 0) {
						for (int j = 0; j < childItemList.size(); j++) {
							OrderItem child = childItemList.get(j);
							unitPriceD = unitPriceD + child.getUnitPrice();
						}
					}

				} else if (orderItemType != null
						&& orderItemType.toLowerCase().indexOf("antibody") != -1
						&& orderItemType.toLowerCase().indexOf("antibody drug") == -1) {
					OrderAntibody orderAntibody = this.orderAntibodyDao
							.getById(orderItem.getOrderItemId());
					description = orderAntibody != null ? orderAntibody
							.getCustomSequence() : "";
					itemDesc.append(orderItem.getCatalogNo())
							.append(":")
							.append(orderItem.getName())
							.append("(Price ")
							.append(currency == null ? "" : currency
									.getSymbol()).append(orderItem.getAmount())
							.append(")<br>");
					List<OrderItem> childItemList = this.orderItemDao.findBy(
							"parentId", orderItem.getOrderItemId());
					for (int j = 0; j < childItemList.size(); j++) {
						OrderItem child = childItemList.get(j);
						String childItemType = null;
						// 获取orderItem类型
						if (QuoteItemType.PRODUCT.value().equals(
								child.getType())) {
							ProductClass pdtClass = this.productClassDao
									.getById(child.getClsId());
							childItemType = pdtClass == null ? null : pdtClass
									.getName();
						} else {
							ServiceClass servClass = this.serviceClassDao
									.getById(child.getClsId());
							childItemType = servClass == null ? null
									: servClass.getName();
						}
						if (StringUtils.isNotEmpty(childItemType)
								&& childItemType.toLowerCase().startsWith(
										"peptide")) {
							unitPriceD = unitPriceD + child.getUnitPrice();
							// peptide类型
							itemDesc.append("<br>")
									.append(orderAntibody != null ? orderAntibody
											.getAntigenType() : "")
									.append(":<br>");
							OrderPeptide orderPeptide = orderPeptideDao
									.getById(child.getOrderItemId());
							if (orderPeptide == null) {
								orderPeptide = new OrderPeptide();
							}
							itemDesc.append(orderPeptide.getName())
									.append(": ");
							if (StringUtils.isNotEmpty(orderPeptide
									.getQuantity())) {
								itemDesc.append(orderPeptide.getQuantity())
										.append(",");
							}
							if (StringUtils
									.isNotEmpty(orderPeptide.getPurity())) {
								itemDesc.append(orderPeptide.getPurity())
										.append(",");
							}
							if (orderPeptide.getSeqLength() != null) {
								itemDesc.append(orderPeptide.getSeqLength())
										.append("aa,");
							}
							if (StringUtils.isNotEmpty(orderPeptide
									.getNTerminal())) {
								itemDesc.append(orderPeptide.getNTerminal())
										.append(";");
							}
							if (StringUtils.isNotEmpty(orderPeptide
									.getCTerminal())) {
								itemDesc.append(orderPeptide.getCTerminal())
										.append(";");
							}
							if (StringUtils.isNotEmpty(orderPeptide
									.getModification())) {
								itemDesc.append(orderPeptide.getModification())
										.append(";");
							}
							if (StringUtils.isNotEmpty(orderPeptide
									.getDisulfideBridge())) {
								itemDesc.append(";Disulfide Bridge:")
										.append(orderPeptide
												.getDisulfideBridge())
										.append(";");
							}
							if (orderPeptide.getAliquoteVialQty() != null) {
								itemDesc.append(
										orderPeptide.getAliquoteVialQty())
										.append("vials");
							}
							if (child.getAmount() != null) {
								itemDesc.append("(Price ")
										.append(currency == null ? ""
												: currency.getSymbol())
										.append(child.getAmount())
										.append(")<br>");
							}

							itemDesc.append(orderPeptide == null ? null
									: orderPeptide.getSequence());
						}
					}

				} else if (orderItemType != null
						&& orderItemType.toLowerCase().startsWith(
								"non-standard service")) {
					if (orderItem.getCatalogNo() != null) {
						itemDesc.append(orderItem.getCatalogNo()).append(":")
								.append(orderItem.getName());
					}
					if (orderItem.getParentId() != null) {
						OrderItem parentItem = this.orderItemDao
								.getById(orderItem.getParentId());
						OrderAntibody orderAntibody = parentItem != null ? this.orderAntibodyDao
								.getById(parentItem.getOrderItemId()) : null;
						itemDesc.append("(ship with antibody anti-")
								.append(orderAntibody != null ? orderAntibody
										.getAntibodyName() : "").append(")");
						itemDesc.append(";");
					}

				} else if (orderItemType != null
						&& orderItemType.toLowerCase().startsWith("peptide")) {
					itemDesc.append(orderItem.getCatalogNo()).append(":");
					OrderPeptide orderPeptide = orderPeptideDao
							.getById(orderItem.getOrderItemId());
					itemDesc.append(orderItem.getName())
							.append(": ")
							.append(orderPeptide == null ? null : orderPeptide
									.getName());
					description = orderPeptide == null ? null : orderPeptide
							.getSequence();
					itemDesc.append(";");
					if (orderPeptide.getSeqLength() != null) {
						itemDesc.append(orderPeptide.getSeqLength()).append(
								"aa,");
					}
					if (StringUtils.isNotEmpty(orderPeptide.getQuantity())) {
						itemDesc.append(orderPeptide.getQuantity()).append(",");
					}
					if (StringUtils.isNotEmpty(orderPeptide.getPurity())) {
						itemDesc.append(orderPeptide.getPurity()).append(",");
					}
					if (StringUtils.isNotEmpty(orderPeptide.getNTerminal())) {
						itemDesc.append(orderPeptide.getNTerminal())
								.append(";");
					}
					if (StringUtils.isNotEmpty(orderPeptide.getCTerminal())) {
						itemDesc.append(orderPeptide.getCTerminal())
								.append(";");
					}
					if (StringUtils.isNotEmpty(orderPeptide.getModification())) {
						itemDesc.append(orderPeptide.getModification()).append(
								";");
					}
					if (StringUtils.isNotEmpty(orderPeptide
							.getDisulfideBridge())) {
						itemDesc.append("Disulfide Bridge:")
								.append(orderPeptide.getDisulfideBridge())
								.append(";");
					}
					if (orderPeptide.getAliquoteVialQty() != null) {
						itemDesc.append(orderPeptide.getAliquoteVialQty())
								.append("vials");
					}
					itemDesc.append("<br>")
							.append(orderItem.getComment())
							.append("<br>")
							.append(orderPeptide == null ? null : orderPeptide
									.getSequence());
				} else if (orderItemType.toLowerCase().startsWith("oligo")) {
					OrderOligo orderOligo = this.orderOligoDao
							.getById(orderItem.getOrderItemId());
					itemDesc.append(orderItemGene)
							.append(":")
							.append("Aliquoting into:")
							.append(orderOligo != null ? orderOligo
									.getAliquotingInto() : "")
							.append(",Backbone:");
					String bbone = "";
					if (orderOligo != null) {
						bbone = orderOligo.getBackbone();
					}
					itemDesc.append(bbone)
							.append(",Purification:")
							.append(orderOligo != null ? orderOligo
									.getPurification() : "")
							.append(",Synthesis Scales:")
							.append(orderOligo != null ? orderOligo
									.getSynthesisScales() : "");

				} else if (orderItemType.toLowerCase().startsWith("sirna")
						|| orderItemType.toLowerCase().startsWith("mirna")) {
					OrderSirnaAndMirna orderSirnaAndMirna = orderSirnaAndMirnaDao
							.getById(orderItem.getOrderItemId());
					if (orderSirnaAndMirna == null) {
						continue;
					}
					orderItemGene = orderItemGene
							+ ": "
							+ (orderSirnaAndMirna == null ? null
									: orderSirnaAndMirna.getGeneName());
					itemDesc.append(orderItem.getCatalogNo()).append(":")
							.append(orderItemGene).append(":")
							.append(orderSirnaAndMirna.getQuantity())
							.append(",")
							.append(orderSirnaAndMirna.getVectorName());
				} else if (orderItemType.toLowerCase().startsWith("sirna")
						|| orderItemType.toLowerCase().startsWith("mirna")) {
					OrderSirnaAndMirna orderSirnaAndMirna = orderSirnaAndMirnaDao
							.getById(orderItem.getOrderItemId());
					if (orderSirnaAndMirna == null) {
						continue;
					}
					orderItemGene = orderItemGene
							+ ": "
							+ (orderSirnaAndMirna == null ? null
									: orderSirnaAndMirna.getGeneName());
					itemDesc.append(orderItem.getCatalogNo()).append(": ")
							.append(orderItemGene).append(": ")
							.append(orderSirnaAndMirna.getQuantity())
							.append(", ")
							.append(orderSirnaAndMirna.getVectorName())
							.append("<br/>")
							.append(orderSirnaAndMirna.getSequenceInsert());
				}else if (orderItemType.toLowerCase().startsWith(
						"custom services")) {
					itemDesc.append(orderItem.getCatalogNo()).append(":")
							.append(orderItem.getName());
					com.genscript.gsscm.order.entity.OrderService orderService = this.orderServiceDao
							.getById(orderItem.getOrderItemId());
					itemDesc.append(";").append(
							orderService != null ? orderService.getCustomDesc()
									: "");
				} else {
					OrderPkgService orderPkgService = this.orderPkgServiceDao
							.getById(orderItem.getOrderItemId());
					description = orderPkgService == null ? null
							: orderPkgService.getDescription();
					itemDesc.append(orderItem.getCatalogNo()).append(":")
							.append(description);
				}

				if (orderItemType.toLowerCase().startsWith("protein")
						|| orderItemType.toLowerCase().startsWith("bioprocess")
						|| orderItemType.toLowerCase().startsWith(
								"antibody drug")
						|| orderItemType.toLowerCase().startsWith(
								"pharmaceutical")
						|| orderItemType.toLowerCase().startsWith("biomarker")) {
					OrderPkgService orderPkgService1 = this.orderPkgServiceDao
							.getById(orderItem.getOrderItemId());
					description = orderPkgService1 != null ? orderPkgService1
							.getSequence() : "";
					List<OrderItem> childItemList = this.orderItemDao.findBy(
							"parentId", orderItem.getOrderItemId());
					if (childItemList == null) {
						childItemList = new ArrayList<OrderItem>();
					}
					for (int j = 0; j < childItemList.size(); j++) {
						OrderItem child = childItemList.get(j);
						unitPriceD = unitPriceD + child.getUnitPrice();
					}
					itemDetail.append("<tr><td  colspan='5' align='left'>");
					itemDetail
							.append(">Item")
							.append(orderItem.getOrderItemId())
							.append(":")
							.append(orderItem.getCatalogNo())
							.append("(Unit Price: ")
							.append(currency.getSymbol())
							.append(new BigDecimal(String.valueOf(unitPriceD))
									.setScale(2, BigDecimal.ROUND_HALF_UP));
					if (orderItem.getQuantity() != null) {
						itemDetail.append("; Quantity: ")
								.append(orderItem.getQuantity())
								.append(")<br>");
					}
					itemDesc.append(orderItem.getCatalogNo());
					itemDesc.append(":");

					if (orderItem.getShortDesc() != null
							&& !"".equals(orderItem.getShortDesc())) {
						itemDesc.append(orderItem.getShortDesc())
								.append("<br>");
					}
					if (orderItem.getName() != null
							&& !"".equals(orderItem.getName())) {
						itemDesc.append(orderItem.getName());
					}
					if (orderItem.getComment() != null
							&& !"".equals(orderItem.getComment())) {
						itemDesc.append(":").append(orderItem.getComment())
								.append("<br>");
					}
					itemDetail.append(itemDesc);
					for (int j = 0; j < childItemList.size(); j++) {
						OrderItem child = childItemList.get(j);
						@SuppressWarnings("unused")
						String childItemType = null;
						// 获取quoteItem类型
						if (QuoteItemType.PRODUCT.value().equals(
								child.getType())) {
							ProductClass pdtClass = this.productClassDao
									.getById(child.getClsId());
							childItemType = pdtClass == null ? null : pdtClass
									.getName();
						} else {
							ServiceClass servClass = this.serviceClassDao
									.getById(child.getClsId());
							childItemType = servClass == null ? null
									: servClass.getName();
						}

						itemDesc.append("Step ")
								.append(j + 1)
								.append(":")
								.append(child.getName())
								.append("(")
								.append(currency == null ? "" : currency
										.getSymbol())
								.append(child.getUnitPrice()).append(")<br>");
						OrderPkgService orderPkgService = this.orderPkgServiceDao
								.getById(child.getOrderItemId());
						StringBuilder sequenceBd = new StringBuilder();
						if (StringUtils.isNotBlank(orderPkgService
								.getSequence())) {
							sequenceBd.append("Gene sequence:")
									.append(orderPkgService.getSequence())
									.append("<br>");
						}
						if (StringUtils.isNotBlank(orderPkgService
								.getProteinSeq())) {
							sequenceBd.append("Protein sequence:")
									.append(orderPkgService.getProteinSeq())
									.append("<br>");
						}
						itemDetail
								.append("<br>Step ")
								.append(j + 1)
								.append(":")
								.append(child.getName())
								.append("(")
								.append(currency == null ? "" : currency
										.getSymbol())
								.append(child.getUnitPrice())
								.append(")<br>")
								.append(orderPkgService != null ? orderPkgService
										.getDescription() : "").append("<br>")
								.append(sequenceBd.toString());
					}

				} else if (!orderItemType.toLowerCase().startsWith("service")) {
					itemSeq = new StringBuffer(description == null ? ""
							: description);
				}

				if (!StringUtils.isEmpty(itemSeq.toString())) {
					String unitPrice = currency.getSymbol()
							+ new BigDecimal(String.valueOf(unitPriceD))
									.setScale(2, BigDecimal.ROUND_HALF_UP);
					if (orderItemType != null
							&& (orderItemType.toLowerCase()
									.startsWith("peptide"))) {
						if ("New Request".equals(order.getOrderType())) {
							if (SessionUtil.getUserId() > 20000
									&& (orderItem.getCatalogNo().equals(
											"SC1177")
											|| orderItem.getCatalogNo().equals(
													"SC1487") || orderItem
											.getCatalogNo().equals("SC1507"))) {
								unitPrice = "TBD";
							}
							if (orderItem.getCatalogNo().equals("SC1208")
									&& !StringUtils.isEmpty(orderItem
											.getComment())) {
								unitPrice = "TBD";
							}
						}
						itemDetail = new StringBuffer();
						itemDetail.append("<tr><td  colspan='5' align='left'>");
						itemDetail.append(">Item")
								.append(orderItem.getOrderItemId()).append(":")
								.append(orderItem.getCatalogNo())
								.append("(Unit Price: ").append(unitPrice);
						if (orderItem.getQuantity() != null) {
							itemDetail.append("; Quantity: ")
									.append(orderItem.getQuantity())
									.append(")<br>");
						}

						itemDetail.append(orderItem.getShortDesc())
								.append("  ").append(itemSeq.toString());

					} else {
						itemDetail = new StringBuffer();
						itemDetail.append("<tr><td  colspan='5' align='left'>");
						itemDetail.append(">Item")
								.append(orderItem.getOrderItemId()).append(":")
								.append(orderItem.getCatalogNo())
								.append("(Unit Price: ").append(unitPrice);
						if (orderItem.getQuantity() != null) {
							itemDetail.append("; Quantity: ")
									.append(orderItem.getQuantity())
									.append(")<br>");
						}

						itemDetail.append(orderItem.getShortDesc())
								.append("  ").append(orderItem.getComment())
								.append("<br>").append(itemSeq.toString());
					}

				} else if (orderItemType.toLowerCase().startsWith("protein")
						|| orderItemType.toLowerCase().startsWith("bioprocess")
						|| orderItemType.toLowerCase().startsWith(
								"antibody drug")
						|| orderItemType.toLowerCase().startsWith(
								"pharmaceutical")
						|| orderItemType.toLowerCase().startsWith("biomarker")) {

				} else {
					itemDetail = new StringBuffer();
					itemDetail.append("<tr><td  colspan='5' align='left'>");
					itemDetail
							.append(">Item")
							.append(orderItem.getOrderItemId())
							.append(":")
							.append(orderItem.getCatalogNo())
							.append("(Unit Price: ")
							.append(currency.getSymbol())
							.append(new BigDecimal(String.valueOf(unitPriceD))
									.setScale(2, BigDecimal.ROUND_HALF_UP));
					if (orderItem.getQuantity() != null) {
						itemDetail.append("; Quantity: ")
								.append(orderItem.getQuantity())
								.append(")<br>");
					}

					itemDetail.append(orderItem.getShortDesc()).append("<br>")
							.append(orderItem.getComment()).append("<br>");
				}
				StringBuffer descStr = new StringBuffer();
				String[] lineStrArray = itemDesc.toString().split("<br>");
				if (lineStrArray != null && lineStrArray.length > 0) {
					for (String lineStr : lineStrArray) {
						if (lineStr.length() > 52) {
							String lineStr2 = "";
							int length = lineStr.length();
							int j = 0;
							while (length > 52) {
								lineStr2 = lineStr.substring(j, 52 + j);
								if (lineStr2.charAt(51) != ' '
										&& lineStr2.charAt(51) != ','
										&& lineStr2.charAt(51) != ';') {
									int index = lineStr2.substring(0, 52)
											.lastIndexOf(" ");
									if (index < lineStr2.substring(0, 52)
											.lastIndexOf(",")) {
										index = lineStr2.substring(0, 52)
												.lastIndexOf(",");
									}
									if (index < lineStr2.substring(0, 52)
											.lastIndexOf(";")) {
										index = lineStr2.substring(0, 52)
												.lastIndexOf(";");
									}
									if (index == -1) {
										index = 51;
									}
									descStr.append(lineStr2.substring(0,
											index + 1));
									descStr.append("<br>");
									length = length - index - 1;
									j = j + index + 1;
								} else {
									descStr.append(lineStr2);
									descStr.append("<br>");
									length = length - 52;
									j = j + 52;
								}
							}
							descStr.append(lineStr.substring(lineStr.length()
									- length));
						} else {
							descStr.append(lineStr);
						}
						descStr.append("<br>");

					}
				}
				String itemDetailStr = itemDetail.toString().replace("##",
						"<br>");
				StringBuffer detailStr = new StringBuffer();
				String[] detailLineStrArray = itemDetailStr.split("<br>");
				if (detailLineStrArray != null && detailLineStrArray.length > 0) {
					for (String lineStr : detailLineStrArray) {
						if (lineStr.length() > 120) {
							String lineStr2 = "";
							int length = lineStr.length();
							int j = 0;
							while (length > 120) {
								lineStr2 = lineStr.substring(j, 120 + j);
								if (lineStr2.charAt(119) != ' '
										&& lineStr2.charAt(119) != ','
										&& lineStr2.charAt(119) != ';') {
									int index = lineStr2.substring(0, 120)
											.lastIndexOf(" ");
									if (index < lineStr2.substring(0, 120)
											.lastIndexOf(",")) {
										index = lineStr2.substring(0, 120)
												.lastIndexOf(",");
									}
									if (index < lineStr2.substring(0, 120)
											.lastIndexOf(";")) {
										index = lineStr2.substring(0, 120)
												.lastIndexOf(";");
									}
									if (index == -1) {
										index = 119;
									}
									detailStr.append(lineStr2.substring(0,
											index + 1));
									detailStr.append("<br>");
									length = length - index - 1;
									j = j + index + 1;
								} else {
									detailStr
											.append(lineStr2.substring(0, 120));
									detailStr.append("<br>");
									length = length - 120;
									j = j + 120;
								}
							}
							detailStr.append(lineStr.substring(lineStr.length()
									- length));
						} else {
							detailStr.append(lineStr);
						}
						detailStr.append("<br>");
					}
				}
				tableBody.append(descStr);
				tableBody.append("</td>");
				tableBody
						.append("<td align='right'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(new BigDecimal(String.valueOf(unitPriceD))
								.setScale(2, BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td>");
				tableBody
						.append("<td align='right'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(new BigDecimal(String.valueOf(orderItem
								.getDiscount())).setScale(2,
								BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td>");
				tableBody
						.append("<td align='right'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(orderItem == null ? ""
								: new BigDecimal(
										String.valueOf(Double.parseDouble(String
												.valueOf(orderItem
														.getQuantity()))
												* (unitPriceD - orderItem
														.getDiscount())))
										.setScale(2, BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></tr>");
				detail.append(detailStr);

			}
		}
		if (tmpShipMethod == 0) {
			orderPrintDTO.setShipVia("");
		} else {
			DropDownDTO dropDownDTO = specDropDownListDao.getSpecDropDown(
					SpecDropDownListName.SHIP_METHOD.value(), tmpShipMethod);
			orderPrintDTO.setShipVia(dropDownDTO != null ? dropDownDTO
					.getValue() : "");
		}

		if (turnRound == null) {
			turnRound = String.valueOf(leadTime);
		}
		if (leadTime != 0) {
			turnaround_comments
					.append("The turnaround time may vary depending on the size and complexity of the project.");
		}

		if (order != null) {
			if (leadTime == 0) {
				if (orderType.toLowerCase().startsWith("gene synthesis")) {
					turnRound = "12-17 business days";
				} else if (orderType.toLowerCase()
						.startsWith("express peptide")) {
					turnRound = "8 business days";
				} else if (orderType.toLowerCase().startsWith(
						"chemical peptide")) {
					turnRound = "10-15  business days";
				} else if (orderType.toLowerCase().startsWith(
						"polyclonal antibody")) {
					turnRound = "15-17  weeks";
				} else if (orderType.toLowerCase().startsWith(
						"monoclonal antibody")) {
					turnRound = "5-7 months";
				} else if (orderType.toLowerCase().startsWith("sirna")) {
					turnRound = "5-10 days";
				}
			}
			if (orderType != null
					&& orderType.toLowerCase().startsWith("gene synthesis")) {
				turnaround_comments = new StringBuffer();
				turnaround_comments
						.append("The turnaround time may vary depending on the size and complexity of the project")
						.append(".*Typical turnaround time can be expected only for sequences that are non-toxic for E. coli.")
						.append("In rare cases, some genes might be toxic and/or genetically unstable,")
						.append("which may be delivered in reduced quantity, cloned subfragments with sequencing data.")
						.append("**In some rare cases, gene may be toxic and/or genetically unstable in customer's vector,")
						.append(" which makes impossible to deliver gene in required vector.")
						.append(" In this case, gene will be delivered in GenScript standard vector and subcloning fee is waived.");
			}
			if (order.getSalesContact() == 78) {
				turnaround_comments
						.append("We guarantee that your polyclonal antibody produced by us in any host will have an ELISA titer of 1:32,")
						.append("000 or better if peptide antigens were designed, synthesized, and conjugated by GenScript.")
						.append(" For any protein antigen (3 mg, >85% purity, 90% preferred)")
						.append(" we guarantee a polyclonal antibody with positive Western blot results and an ELISA titer of 1:32,000")
						.append(" or better for any host.")
						.append("All antisera or purified antibodies are provided as lyophilized form except special requirements.")
						.append(" Lyophilization extends the shelf life of your antibodies,")
						.append(" eliminates leaking problems and loosens up the stringent temperature requirement during shipping,")
						.append(" and reduces the overall weight of antibodies to be shipped.");
			}
			total = order.getSubTotal().add(order.getTax()).doubleValue()
					+ order.getShipAmt();
			if (order.getOrderNo() < 800000) {// 逻辑问题(以前的quotation)
				total = order.getAmount().doubleValue();
				if (order.getDiscount().intValue() != 0) {
					total = total - order.getDiscount().doubleValue();
				}
			}
			System.out.println("total........>>>>>" + total);
			if (order.getExchRate() != null
					&& "JPY".equalsIgnoreCase(currency.getCurrencyCode())) {
				total_price_desc = "(Net of Tax)";
				OrderAddress orderAddress = this.orderAddressDao
						.getAddrByOrderNoAndType(orderNo, "BILL_TO");
				if (orderAddress != null
						&& "Japan".equalsIgnoreCase(orderAddress.getCountry())) {
					total_price_desc1 = "(Including Tax)";
					total = total - order.getTax().doubleValue();
				}
			}
			if (order.getDiscount().intValue() != 0) {
				sub_discount_department
						.append("<TR><td colspan='5' align='right'  width='84%'>Total Discount(")
						.append(currency == null ? "" : currency
								.getDescription())
						.append(")&nbsp;</td><td align='right'  width='16%'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(order == null ? "" : new BigDecimal(String
								.valueOf(order.getDiscount())).setScale(2,
								BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></TR>");
			}
			if (order.getTax().intValue() > 0) {
				tax_department
						.append("<TR><td colspan='5' align='right'  width='84%'>Estimated Tax(")
						.append(currency == null ? "" : currency
								.getDescription())
						.append(")&nbsp;</td>")
						.append("<td align='right'  width='16%'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(order == null ? "" : order.getTax().setScale(2,
								BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></TR>");
				total_include_tax
						.append("<TR><td colspan='5' align='right'  width='84%'>Total order(")
						.append(currency == null ? "" : currency
								.getDescription())
						.append(")&nbsp;</td>")
						.append("<td align='right'  width='16%'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(new BigDecimal(String.valueOf(total)).setScale(
								2, BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></TR>");
			}
		}
		ship_price_department
				.append("<TR><td colspan='5' align='right'  width='84%'>Estimated Shipping/Handling(")
				.append(currency == null ? "" : currency.getDescription())
				.append(")&nbsp;</td>")
				.append("<td align='right'  width='16%'>")
				.append(currency == null ? "" : currency.getSymbol())
				.append(order == null ? "" : new BigDecimal(String
						.valueOf(order.getShipAmt())).setScale(2,
						BigDecimal.ROUND_HALF_UP)).append("&nbsp;</td></TR>");

		/******** 以下为构建OrderPrintDTO对象 *********************************/
		orderPrintDTO.setQId(String.valueOf(orderNo));
		orderPrintDTO.setCompanyName(companyName);
		if (order != null) {
			orderPrintDTO.setInitDate(DateTypeConverter.convertToString(
					order.getOrderDate(),
					DateTypeConverter.ACCEPT_DATE_FORMATS[1]));
			orderPrintDTO.setExpDate(DateTypeConverter.convertToString(
					order.getExprDate(),
					DateTypeConverter.ACCEPT_DATE_FORMATS[1]));
			orderPrintDTO.setSubTotal(String.valueOf(new BigDecimal(String
					.valueOf(order.getSubTotal())).setScale(2,
					BigDecimal.ROUND_HALF_UP)));
			orderPrintDTO.setSubprice(String.valueOf(new BigDecimal(String
					.valueOf(order.getSubTotal().doubleValue() + 2
							* order.getDiscount().doubleValue())).setScale(2,
					BigDecimal.ROUND_HALF_UP)));
			orderPrintDTO.setSubDiscount(String.valueOf(new BigDecimal(String
					.valueOf(order.getDiscount())).setScale(2,
					BigDecimal.ROUND_HALF_UP)));
		}
		if (customer != null) {
			orderPrintDTO.setFirstName(customer.getFirstName());
			orderPrintDTO.setLastName(customer.getLastName());
			orderPrintDTO.setEmail(customer.getBusEmail() != null ? customer
					.getBusEmail().replace(",", "<br>") : "");
			orderPrintDTO.setState(customer.getState());
		}
		if (country != null) {
			orderPrintDTO.setCountry(country.getName());
		}
		if (department != null) {
			orderPrintDTO.setDepartment(department.getName());
		}
		if (organization != null) {
			String orgname = organization.getName();
			orderPrintDTO.setInstitution(orgname);
		}

		User user = new User();
		System.out.println(orderNo);
		if (order != null) {
			Integer teachManage = order.getTechSupport();
			user = this.userDao.findByUserId(teachManage);
			if (user != null) {
				System.out.println(user.getFirstName() + user.getLastName());
				String preparedByName = user.getFirstName() + " "
						+ user.getLastName();
				String techManager = user.getPosition();
				orderPrintDTO.setPreparedByName(preparedByName);
				orderPrintDTO.setTechManager(techManager);
			}
		}
		if (currency != null) {
			orderPrintDTO.setName(currency.getDescription());
			orderPrintDTO.setSubTotalName(currency.getDescription());
			orderPrintDTO.setTotalQuoteName(currency.getDescription());
			orderPrintDTO.setSymbol(currency.getSymbol());
		}
		orderPrintDTO.setTurnAround(turnRound.toString().equals("0") ? ""
				: turnRound.toString());
		orderPrintDTO.setTotalPriceDesc(total_price_desc);
		orderPrintDTO.setResult(result.toString().replace("null", ""));

		orderPrintDTO.setTableBody(tableBody.toString().replace("null", ""));
		orderPrintDTO.setShipPriceDepartment(ship_price_department.toString()
				.replace("null", ""));
		orderPrintDTO.setTaxDepartment(tax_department.toString().replace(
				"null", ""));
		orderPrintDTO.setTotalIncludeTax(total_include_tax.toString().replace(
				"null", ""));
		orderPrintDTO.setComments(turnaround_comments.toString().replace(
				"null", ""));
		orderPrintDTO.setTotal(String.valueOf(new BigDecimal(String
				.valueOf(total)).setScale(2, BigDecimal.ROUND_HALF_UP)));
		// 有没有child.pdf.htm)
		List<PreQuotation> list = this.preQuotationDao.findBy("quotationId",
				orderNo);// olddb.pre_quotation表中是否有一条记录的quoteNo值和order.orderNo相等
		if (order != null
				&& (orderType.toLowerCase().startsWith("protein")
						|| orderType.toLowerCase().startsWith("bioprocess")
						|| orderType.toLowerCase().startsWith("antibody drug")
						|| orderType.toLowerCase().startsWith("pharmaceutical") || orderType
						.toLowerCase().startsWith("biomarker")) || isHasItem
				|| (list != null && list.size() > 0)) {
			orderPrintDTO.setDetail(detail.toString().replace("null", ""));
			orderPrintDTO.setShowChild(true);
		}
		return orderPrintDTO;
	}

	/**
	 * 合并pdf文件(quotation print)
	 */
	@SuppressWarnings("unused")
	public String mergePdfFiles(Integer orderNo, OrderPrintDTO orderPrintDTO,
			ServletContext context) {
		String retPdfFullName = null;
		if (orderNo != null) {
			OrderMain order = this.orderDao.getById(orderNo);
			String requestPath = "order/order!showPrintPage.action";
			StringBuffer param = new StringBuffer();
			param.append("orderNo=").append(orderNo)
					.append("&orderPrintDTO.email=")
					.append(orderPrintDTO.getEmail())
					.append("&orderPrintDTO.department=")
					.append(orderPrintDTO.getDepartment())
					.append("&orderPrintDTO.institution=")
					.append(orderPrintDTO.getInstitution())
					.append("&orderPrintDTO.state=")
					.append(orderPrintDTO.getState())
					.append("&orderPrintDTO.country=")
					.append(orderPrintDTO.getCountry())
					.append("&orderPrintDTO.comments=")
					.append(orderPrintDTO.getComments())
					.append("&orderPrintDTO.companyName=")
					.append(orderPrintDTO.getCompanyName())
					.append("&orderPrintDTO.address1=")
					.append(orderPrintDTO.getAddress1())
					.append("&orderPrintDTO.address2=")
					.append(orderPrintDTO.getAddress2())
					.append("&orderPrintDTO.telephone=")
					.append(orderPrintDTO.getTelephone())
					.append("&orderPrintDTO.fax=")
					.append(orderPrintDTO.getFax())
					.append("&orderPrintDTO.custEmail=")
					.append(orderPrintDTO.getCustEmail())
					.append("&orderPrintDTO.web=")
					.append(orderPrintDTO.getWeb());
			// 1. 通过JSP生成原始的PDF，没有合并子Item, 没有加水印
			String pdfName = PdfUtils.convertServerUrl2Pdf(requestPath,
					param.toString());
			System.out.println(pdfName);
			HashMap<String, String> moreInfo = new HashMap<String, String>();
			String port = "";
			if (Struts2Util.getRequest().getServerPort() != 80) {
				port = ":" + Struts2Util.getRequest().getServerPort();
			}
			String basePath = Struts2Util.getRequest().getScheme() + "://"
					+ Struts2Util.getRequest().getServerName() + port
					+ Struts2Util.getRequest().getContextPath() + "/";
			try {
				// 加水印，头尾logo
				// PdfUtils pdfUtil = new PdfUtils(pdfName, pdfName);
				// pdfUtil.extraPdf(moreInfo, null,
				// basePath+"images/header.jpg",basePath+"images/footer.jpg");

				String advPdfFilePath = null;// 要合并的广告pdf文件
				if (order != null) {
					String orderType = null;
					List<OrderItem> orderItemList = this.orderItemDao.findBy(
							"orderNo", order.getOrderNo());
					if (orderItemList != null && orderItemList.size() > 0) {
						for (OrderItem orderItem : orderItemList) {
							String orderItemType = null;
							// 获取quoteItem类型
							if (QuoteItemType.PRODUCT.value().equals(
									orderItem.getType())) {
								ProductClass pdtClass = this.productClassDao
										.getById(orderItem.getClsId());
								orderItemType = pdtClass == null ? null
										: pdtClass.getName();
							} else {
								ServiceClass servClass = this.serviceClassDao
										.getById(orderItem.getClsId());
								orderItemType = servClass == null ? null
										: servClass.getName();
							}
							if (orderItemType != null) {
								orderType = orderItemType;
								break;
							}
						}
					}
				/*	System.out
							.println("##################orderType##########################="
									+ orderType);
					if (orderType != null) {
						if (orderType.toLowerCase().startsWith("peptide")) {
							advPdfFilePath = "Peptide_Services.pdf";
						} else if (orderType.toLowerCase().startsWith("gene")
								|| orderType.toLowerCase().startsWith(
										"synthesis")) {
							advPdfFilePath = "GeneServices.pdf";
						} else if (orderType.toLowerCase()
								.startsWith("protein")
								|| orderType.toLowerCase().startsWith(
										"bioprocess")
								|| orderType.toLowerCase().startsWith(
										"pharmaceutical")) {
							advPdfFilePath = "Protein_Service.pdf";
						} else if (orderType.toLowerCase().startsWith(
								"biomarker")
								|| orderType.toLowerCase().startsWith(
										"custom services-animal model")) {
							advPdfFilePath = "Animal_model.pdf";
						} else if (orderType.toLowerCase().startsWith(
								"antibody drug")) {
							advPdfFilePath = "Antibody_drug.pdf";
						}
						// else if(quoteType.startsWith("Custom Service"))
						// {//暂时没有该类型
						//
						// }
						else {
							advPdfFilePath = "GeneServices.pdf";
						}*/

					   advPdfFilePath = context
							.getRealPath("pdfAdv/Quotation.pdf");
					/*	System.out
								.println("##################advPdfFilePath##########################="
										+ advPdfFilePath);
*/
				//	}

				}
				// 合并pdf
				String names[] = { pdfName };
				if (advPdfFilePath != null) {
					names = new String[] { pdfName, advPdfFilePath/*,
							context.getRealPath("pdfAdv/all_service.pdf"*/};
				}
				retPdfFullName = PdfUtils.getTempFileShortName() + ".pdf";
				/*System.out
						.println("##################retPdfFullName##########################="
								+ retPdfFullName);*/
				PdfUtils.mergePdfFiles(names, retPdfFullName);
				File file = new File(pdfName);
				file.delete();
				PdfUtils pdfUtil = new PdfUtils(retPdfFullName, retPdfFullName);
				// pdfUtil.extraPdf(moreInfo,
				// context.getRealPath("images/confidential.gif"),
				// context.getRealPath("images/header.jpg"),
				// context.getRealPath("images/footer.jpg"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return retPdfFullName;
	}

	/**
	 * 更新mfgorder的status和expDate
	 * 
	 * @author lizhang
	 */
	public void updateMfgOrder(Integer orderNo, Date targetDate) {
		if (orderNo != null) {
			boolean flg = true;
			List<MfgOrderItem> orderItemList = mfgOrderItemDao.findBy(
					"orderNo", orderNo);
			if (orderItemList != null && orderItemList.size() > 0) {
				for (MfgOrderItem orderItem : orderItemList) {
					if (!"VC".equals(orderItem.getStatus())
							&& !"CN".equals(orderItem.getStatus())) {
						flg = false;
						break;
					}
				}
			}
			MfgOrder orderMain = this.mfgOrderDao.getById(orderNo);
			if (flg) {
				if (orderMain != null) {
					orderMain.setStatus("VC");
					orderMain.setModifiedBy(SessionUtil.getUserId());
					orderMain.setModifyDate(new Date());
				}
			}
			if (targetDate != null
					&& (orderMain.getExprDate() == null || orderMain
							.getExprDate().compareTo(targetDate) < 0)) {
				orderMain.setExprDate(targetDate);
			}
			mfgOrderDao.save(orderMain);
		}
	}

	/**
	 * 更新order的status和expDate
	 * 
	 * @author lizhang
	 */
	public void updateOrder(Integer orderNo, Date targetDate) {
		if (orderNo != null) {
			boolean flg = true;
			List<OrderItem> orderItemList = orderItemDao.findBy("orderNo",
					orderNo);
			if (orderItemList != null && orderItemList.size() > 0) {
				for (OrderItem orderItem : orderItemList) {
					if (!"VC".equals(orderItem.getStatus())
							&& !"CN".equals(orderItem.getStatus())) {
						flg = false;
						break;
					}
				}
			}
			OrderMain orderMain = this.orderDao.getById(orderNo);
			if (flg) {
				if (orderMain != null) {
					orderMain.setStatus("VC");
					orderMain.setModifiedBy(SessionUtil.getUserId());
					orderMain.setModifyDate(new Date());
				}
			}
			if (targetDate != null
					&& (orderMain.getExprDate() == null || orderMain
							.getExprDate().compareTo(targetDate) < 0)) {
				orderMain.setExprDate(targetDate);
			}
			orderDao.save(orderMain);
		}
	}

	/**
	 * 把coupon的code和VALUE赋值给order
	 */
	public String applyCoupon(String couponId, String sessOrderNo)
			throws Exception {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		String[] couponIdArray = StringUtils.isNotBlank(couponId) ? couponId
				.split(",") : new String[] {};
		Double couponValues = 0.0;
		StringBuffer couponCodes = new StringBuffer();
		StringBuffer couponIds = new StringBuffer();
		StringBuffer results = new StringBuffer();
		for (String couponId1 : couponIdArray) {
			Coupon coupon = this.couponDao.getById(Integer.parseInt(couponId1));
			if ((StringUtil.isNumeric(sessOrderNo) && !this.orderDao
					.couponIsUsed(couponId1, Integer.parseInt(sessOrderNo)))
					|| (!StringUtil.isNumeric(sessOrderNo) && !this.orderDao
							.couponIsUsed(couponId1, null))) {
				couponIds.append(couponId1).append(",");
				if (coupon != null) {
					couponValues += coupon.getValue();
					if ("".equals(couponCodes.toString())) {
						couponCodes.append(coupon.getCode());
					} else {
						couponCodes.append(",").append(coupon.getCode());
					}
				}
			} else {
				results.append("The ").append(coupon.getCode())
						.append(" has been used.").append("\n");
			}
		}
		order.setCouponCode(couponCodes.toString());
		if (StringUtils.isNotEmpty(couponIds.toString())) {
			order.setCouponId(couponIds.substring(0, couponIds.toString()
					.length() - 1));
		} else {
			order.setCouponId("");
		}
		order.setCouponValue(couponValues);
		SessionUtil
				.updateRow(SessionConstant.Order.value(), sessOrderNo, order);
		return results.toString();
	}

	/**
	 * 创建一个新的orderitem该item为赠品
	 * 
	 * @param catelogNo
	 * @param qty
	 *            数量
	 * @param itemSize
	 *            所属order已含有的item数量
	 * @param warehouseId
	 *            order的warehouseId
	 * @author lizhang
	 */
	private OrderItemDTO createOrderItemDTO(String catelogNo, Integer custNo,
			int qty, int itemSize, int warehouseId) throws Exception {
		CustomerPriceBean customerPriceBean = this.customerPriceBeanDao
				.findUnique(Restrictions.and(
						Restrictions.eq("catalogNo", catelogNo),
						Restrictions.eq("custNo", custNo)));
		if (customerPriceBean == null) {
			throw new BussinessException("SO0012",
					"Product not relate to product lines or the customer.");
		}
		Product product = this.productDao.findUniqueBy("catalogNo", catelogNo);
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		ItemBeanDTO itemBeanDTO = dozer.map(customerPriceBean,
				ItemBeanDTO.class);
		orderItemDTO.setCatalogNo(catelogNo);
		orderItemDTO.setName(product.getName());
		orderItemDTO.setNameShow(product.getName());
		orderItemDTO.setQuantity(qty);
		orderItemDTO.setSize(itemBeanDTO.getSize());
		orderItemDTO.setUnitPrice(itemBeanDTO.getUnitPrice());
		orderItemDTO.setUpSymbol(itemBeanDTO.getUpSymbol());
		orderItemDTO.setSizeUom(itemBeanDTO.getUom());
		orderItemDTO.setQtyUom(itemBeanDTO.getQtyUom());
		orderItemDTO.setDiscount(new BigDecimal(0));
		orderItemDTO.setTax(new BigDecimal(0));
		orderItemDTO.setItemNo(itemSize + 1);
		orderItemDTO.setAmount(new BigDecimal(ArithUtil.mul(itemBeanDTO
				.getUnitPrice().doubleValue(), Double.valueOf(String
				.valueOf(qty)))));
		orderItemDTO.setType("PRODUCT");
		orderItemDTO.setCatalogId(itemBeanDTO.getCatalogId());
		StringBuffer catalogTex = new StringBuffer();
		catalogTex.append(itemBeanDTO.getCatalogId()).append(":")
				.append(itemBeanDTO.getCatalogName());
		orderItemDTO.setCatalogText(catalogTex.toString());
		orderItemDTO.setParentId("");
		orderItemDTO.setTypeText(orderItemDTO.getType() + " - "
				+ productClassDao.get(product.getProductClsId()).getName());
		orderItemDTO.setClsId(product.getProductClsId());
		orderItemDTO.setTaxStatus(product.getTaxable());
		orderItemDTO.setShipSchedule(product.getLeadTime());
		orderItemDTO.setShortDesc(product.getShortDesc());
		orderItemDTO.setLongDesc(product.getLongDesc());
		orderItemDTO.setSellingNote(product.getSellingNote());

		orderItemDTO.setCost(new BigDecimal(0.0));
		orderItemDTO.setBasePrice(new BigDecimal(0));
		// 为了能保存OrderItem设置的shipTOAddrId假数据
		orderItemDTO.setShiptoAddrId(6276);

		String customerCompany = customerDao.getCustomerCompany(custNo, null);
		ItemOtherInfoForNewDTO itemOtherInfo = getItemOtherInfoForNew(
				orderItemDTO.getQuantity(), orderItemDTO.getCatalogNo(),
				QuoteItemType.fromValue(orderItemDTO.getType()),
				customerCompany);
		orderItemDTO.setOtherInfo(itemOtherInfo.getOtherInfo());
		orderItemDTO.setStatus(itemOtherInfo.getItemStatus());
		orderItemDTO.setStatusText(itemOtherInfo.getItemStatusText());
		List<DropDownDTO> list = specDropDownListDao
				.getSpecDropDownList(SpecDropDownListName.SHIP_METHOD.value());
		if (list != null && list.size() > 0) {
			orderItemDTO.setShipMethod(Integer.parseInt(list.get(0).getId()));
		}
		List<DropDownDTO> list2 = specDropDownListDao
				.getPickLocationList(warehouseId);
		if (list2 != null && list2.size() > 0) {
			orderItemDTO.setStorageId(Integer.parseInt(list2.get(0).getId()));
		}
		return orderItemDTO;
	}

	/**
	 * 通过orderNo或custNo查询Customer
	 * 
	 * @param custNo
	 * @param orderNo
	 * @return
	 * @author zhangyong
	 */
	public Customer findBusEmailByNo(Integer custNo, Integer orderNo) {
		if (custNo != null) {
			return this.customerDao.findByCustNo(custNo);
		} else if (orderNo != null) {
			return this.orderDao.findBusEmailByOrderNo(orderNo);
		}
		return null;
	}

	/**
	 * 去除因promotion产生的giftitem
	 * 
	 * @param prmtCode
	 */
	@SuppressWarnings("unchecked")
	public void deleteGifItem(String prmtCode, String orderNo) {
		Promotion promotion = this.promotionDao.findUniqueBy("prmtCode",
				prmtCode);
		if (promotion != null) {
			String[] discType = promotion.getDiscType().split(";");
			if (discType.length == 4 && "1".equals(discType[2])) {
				String catalogNo = promotion.getDiscProd();
				Map<String, OrderItemDTO> itemMap = (Map<String, OrderItemDTO>) SessionUtil
						.getRow(SessionConstant.OrderItemList.value(), orderNo);
				Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
						.iterator();
				int i = 1;
				List<String> delItemsId = new ArrayList<String>();
				while (it.hasNext()) {
					Entry<String, OrderItemDTO> entry = it.next();
					OrderItemDTO orderItemDTO = entry.getValue();
					orderItemDTO.setItemNo(i);
					String tmpItemId = entry.getKey();
					if (catalogNo.equals(orderItemDTO.getCatalogNo())) {
						if (StringUtils.isNumeric(tmpItemId)) {
							it.remove();
							itemMap.remove(tmpItemId);
							delItemsId.add(tmpItemId);
							continue;
						} else {
							it.remove();
							itemMap.remove(tmpItemId);
							continue;
						}
					}
					i++;
				}
				for (String id : delItemsId) {
					this.orderItemDao.delete(Integer.parseInt(id));
				}
				SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
						orderNo, itemMap);

			}
		}
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), orderNo);
		if (order.getOrderPromotion() != null
				&& StringUtils.isNotBlank(order.getOrderPromotion()
						.getPrmtCode())) {
			order.setOrderPromotion(null);
		}
		SessionUtil.updateRow(SessionConstant.Order.value(), orderNo, order);
	}

	/**
	 * reopen Order
	 * 
	 * @author Zhang Yong
	 * @param statusReason
	 * @param orderStatus
	 * @param statusNote
	 * @param sessOrderNo
	 * @return
	 * @throws Exception
	 */
	public String reopenOrder(String statusReason, String orderStatus,
			String statusNote, String sessOrderNo) throws Exception {
		String result = null;
		if (StringUtils.isBlank(sessOrderNo)
				|| !StringUtils.isNumeric(sessOrderNo)) {
			result = "Please save the order, then reopen it.";
			return result;
		}
		Integer orderNo = Integer.parseInt(sessOrderNo);
		// 如果该Order之前有过CC操作，则给customer和Tech account Manager发送邮件
		OrderProcessLog lastConfirmLog = orderProcessLogDao
				.getOrderLastConfirm(orderNo);
		if (lastConfirmLog != null) {
			MailTemplates custConfirmMailTemplate = findConfirmTemp(orderNo);
			if (custConfirmMailTemplate == null) {
				result = "Send Email to cutomer and Tech Account Manager failure, can not find customer confirm Email Template.";
				return result;
			}
			OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
					SessionConstant.Order.value(), sessOrderNo);
			if (order.getCustNo() == null) {
				result = "Send Email to cutomer and Tech Account Manager failure, the Order:"
						+ sessOrderNo + " its custNo is null.";
				return result;
			}
			Customer customer = this.customerDao
					.findByCustNo(order.getCustNo());
			if (customer == null) {
				result = "Send Email to cutomer and Tech Account Manager failure, can not found the customer:"
						+ order.getCustNo();
				return result;
			}
			if (customer.getTechSupport() == null) {
				result = "Send Email to cutomer and Tech Account Manager failure, customer Tech Account Manager can not be null.";
				return result;
			}
			String mailTo = customer.getBusEmail();
			User techAccountManager = userDao.findByUserId(customer
					.getTechSupport());
			if (techAccountManager == null) {
				result = "Send Email to cutomer and Tech Account Manager failure, can not find customer Tech Account Manager.";
				return result;
			}
			if (techAccountManager == null
					|| StringUtils.isBlank(techAccountManager.getEmail())) {
				result = "Send Email to cutomer and Tech Account Manager failure, can not find customer Tech Account Manager's Email.";
				return result;
			}
			mailTo += "," + techAccountManager.getEmail();
			mimeMailService.sendMail(mailTo,
					custConfirmMailTemplate.getSubject(),
					custConfirmMailTemplate.getContent(),
					"scm_admin@genscriptcorp.com");
		}
		String status = OrderStatusType.fromValue(orderStatus).value();// 验证status
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		order.setStatus(status);
		order.setStatusReason(statusReason);
		order.setStatusNote(statusNote);
		updateOrderStatus(orderNo, statusReason, status,
				SessionUtil.getUserId(), statusNote, order);
		result = "successful";
		return result;
	}

	/**
	 * 查询Customer Confirm Email Template
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	@Transactional(readOnly = true)
	public MailTemplates findConfirmTemp(Integer orderNo) throws Exception {
		if (orderNo == null) {
			return null;
		}
		MailTemplates geneMailTemplate = null;
		MailTemplates peptideMailTemplate = null;
		List<MailTemplates> mailTemplateslist = mailTemplatesDao
				.findbyFuncName(Constants.MAIL_TEMP_CUST_CONFIRM);
		if (mailTemplateslist == null || mailTemplateslist.size() <= 0) {
			return null;
		}else{
			for(MailTemplates mailTemplate : mailTemplateslist){
				String templateName = mailTemplate.getTemplateName();
				if("Customer Confirm Email Template 1".equals(templateName)){
					geneMailTemplate = dozer.map(mailTemplate, MailTemplates.class);
				}else if("Customer Confirm Email Template Peptide".equals(templateName)){
					peptideMailTemplate = dozer.map(mailTemplate, MailTemplates.class);
				}
			}
		}
		
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), orderNo.toString());
		if (orderMain == null) {
			return null;
		}
		Customer customer = this.customerDao
				.findByCustNo(orderMain.getCustNo());
		if (customer == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, OrderItemDTO> orderItemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(),
						orderNo.toString());
//		MailTemplates mt = mailTemplateslist.get(0);
		MailTemplates newMt = new MailTemplates();
//		newMt = this.dozer.map(mt, MailTemplates.class);
		boolean genFlag = false;
		if (orderItemMap != null && orderItemMap.size() > 0) {
			for (String key : orderItemMap.keySet()) {
				OrderItemDTO orderItemDto = orderItemMap.get(key);
				if (orderItemDto != null
						&& orderItemDto.getClsId() == 3 || orderItemDto.getClsId() == 4 || orderItemDto.getClsId() == 5 || orderItemDto.getClsId() == 6 || orderItemDto.getClsId() == 7 || orderItemDto.getClsId() == 8 || orderItemDto.getClsId() == 9 || orderItemDto.getClsId() == 10 || orderItemDto.getClsId() == 38) {
					genFlag = true;
					break;
				}
			}
		}
		if(genFlag){
			newMt = geneMailTemplate;
		}else{
			newMt = peptideMailTemplate;
		}
		String subject = newMt.getSubject();
		if (StringUtils.isNotBlank(orderMain.getSrcQuoteNo())) {
			newMt.setSubject(subject == null ? "" : subject.replace(
					"&order_no", orderMain.getOrderNo().toString()
							+ " for quotation " + orderMain.getSrcQuoteNo()));
		} else {
			newMt.setSubject(subject == null ? "" : subject.replace(
					"&order_no", orderMain.getOrderNo().toString()));
		}
		String content = newMt.getContent();
		System.out.println(content);
		content = content == null ? "" : content.replace("&last_name",
				customer.getLastName());
		content = content.replace("&cust_no", customer.getCustNo().toString()
				+ "<br/>");
		content = content.replace("&customer_email", customer.getBusEmail()
				+ "<br/>");
		content = content.replace("&order_no", orderMain.getOrderNo()
				.toString());
		String upSymbol = "";
		if (orderItemMap != null && orderItemMap.size() > 0) {
			for (String key : orderItemMap.keySet()) {
				OrderItemDTO orderItemDto = orderItemMap.get(key);
				if (orderItemDto != null
						&& StringUtils.isNotBlank(orderItemDto.getUpSymbol())) {
					upSymbol = orderItemDto.getUpSymbol();
					break;
				}
			}
		}
		StringBuffer sbf = new StringBuffer();
		if (orderItemMap != null && orderItemMap.size() > 0) {
			Map<Integer, ServiceClass> serviceMap = new HashMap<Integer, ServiceClass>();
			Map<Integer, ProductClass> productMap = new HashMap<Integer, ProductClass>();
			ServiceClass serviceClass = null;
			ProductClass productClass = null;
			for (String key : orderItemMap.keySet()) {
				OrderItemDTO orderItemDto = orderItemMap.get(key);
				Integer clsId = orderItemDto.getClsId();
				if (clsId == null) {
					continue;
				}
				if (CatalogType.SERVICE.value().equalsIgnoreCase(
						orderItemDto.getType())) {
					if (!serviceMap.containsKey(clsId)) {
						serviceClass = serviceClassDao.getById(clsId);
						serviceMap.put(clsId, serviceClass);
					} else {
						serviceClass = serviceMap.get(clsId);
					}
				} else {
					if (!productMap.containsKey(clsId)) {
						productClass = productClassDao.getById(clsId);
						productMap.put(clsId, productClass);
					} else {
						productClass = productMap.get(clsId);
					}
				}
				sbf.append(this.orderEmailService.getMoreItemsInfoForTemp(orderItemMap, orderMain, key, orderItemDto, serviceClass, productClass, 3));
			}
            //重置MailFlag，防止再次浏时候没有内容
            for (String key : orderItemMap.keySet()) {
                orderItemMap.get(key).setMailFlag(false);
            }
		}
		content = content.replace("&item_detail", sbf.toString() + "<br/>");
		// content = content.replace("&discount", orderMain.getDiscount()

		// + "<br/>");
		content = content.replace("&ship_amount",
				" " + upSymbol + orderMain.getShipAmt() + "<br/>");
		content = content.replace("&order_total",
				" " + upSymbol + orderMain.getAmount() + "<br/><br/>");
		@SuppressWarnings("unchecked")
		Map<String, PaymentVoucherDTO> sessMap = (Map<String, PaymentVoucherDTO>) SessionUtil
				.getRow(SessionConstant.OrderPayment.value(),
						orderNo.toString());
		String payment = "";
		StringBuilder paymentSB = new StringBuilder();

		if (sessMap != null && !sessMap.isEmpty()) {
			Iterator<Entry<String, PaymentVoucherDTO>> it = sessMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, PaymentVoucherDTO> entry = it.next();
				PaymentVoucherDTO itemDTO = entry.getValue();
				if (itemDTO.getPaymentType().equalsIgnoreCase("PO")) {
					paymentSB.append("PO#").append(itemDTO.getPoNumber());
					break;
				} else if (itemDTO.getPaymentType().equalsIgnoreCase("CC")) {
					paymentSB.append("CC#").append(itemDTO.getCcNo());
					break;
				}
			}
			payment = paymentSB.toString();
		} else {
			List<PaymentVoucher> paymentPlanList = paymentVoucherDao
					.getPaymentVoucherList(orderNo);
			for (PaymentVoucher paymentVoucher : paymentPlanList) {
				if (paymentVoucher.getPaymentType().equalsIgnoreCase("PO")) {
					paymentSB.append("PO#")
							.append(paymentVoucher.getPoNumber());
					break;
				} else if (paymentVoucher.getPaymentType().equalsIgnoreCase(
						"CC")) {
					paymentSB.append("CC#").append(paymentVoucher.getCcNo());
					break;
				}
			}
			payment = paymentSB.toString();
		}
		content = content.replace("&payment", " " + payment + "<br/><br/>");
		OrderAddress shipToAddr = null;
		if (orderMain.getOrderAddrList() != null
				&& orderMain.getOrderAddrList().getShipToAddr() != null) {
			shipToAddr = orderMain.getOrderAddrList().getShipToAddr();
		} else if (orderMain.getShiptoAddrId() != null
				&& orderMain.getShiptoAddrId() > 0) {
			shipToAddr = this.orderAddressDao.findByAddrId(orderMain
					.getShiptoAddrId());
		}
		if (shipToAddr != null) {
			StringBuffer shipsbf = new StringBuffer();
			shipsbf.append("<br/>" + shipToAddr.getFirstName() + " ");
			shipsbf.append(shipToAddr.getMidName() + " ");
			shipsbf.append(shipToAddr.getLastName() + "<br/>");
			String OrgName = "";
			OrgName = shipToAddr.getOrgName();
			// Organization org = shipToAddr.getOrganization();
			if (OrgName != null && !"".equals(OrgName)) {
				shipsbf.append(OrgName + "<br/>");
			}
			shipsbf.append(shipToAddr.getAddrLine1() + "<br/>");
			shipsbf.append(shipToAddr.getCity() + "," + shipToAddr.getState()
					+ ",");
			shipsbf.append(shipToAddr.getZipCode() + ","
					+ shipToAddr.getCountry() + "<br/>");
			content = content.replace("&shiping_address", shipsbf.toString()
					+ "<br/><br/>");
		}
		OrderAddress billToAddr = null;
		if (orderMain.getOrderAddrList() != null
				&& orderMain.getOrderAddrList().getBillToAddr() != null) {
			billToAddr = orderMain.getOrderAddrList().getBillToAddr();
		} else if (orderMain.getBilltoAddrId() != null
				&& orderMain.getBilltoAddrId() > 0) {
			billToAddr = this.orderAddressDao.findByAddrId(orderMain
					.getBilltoAddrId());
		}
		if (billToAddr != null) {
			StringBuffer billsbf = new StringBuffer();
			billsbf.append("<br/>" + billToAddr.getFirstName() + " ");
			billsbf.append(billToAddr.getMidName() + " ");
			billsbf.append(billToAddr.getLastName() + "<br/>");
			String orgName = billToAddr.getOrgName();
			// Organization org = billToAddr.getOrganization();
			if (orgName != null) {
				billsbf.append(orgName + "<br/>");
			}
			billsbf.append(billToAddr.getAddrLine1() + "<br/>");
			billsbf.append(billToAddr.getCity() + "," + billToAddr.getState()
					+ ",");
			billsbf.append(billToAddr.getZipCode() + ","
					+ billToAddr.getCountry() + "<br/>");
			content = content.replace("&billing_address", billsbf.toString()
					+ "<br/><br/>");
		}
		User user = customerDao.getUserByCustNo(customer.getCustNo());
		content = content
				.replace(
						"&signature",
						(user == null || StringUtils.isBlank(user
								.getSignature())) ? " <br/>" : (user
								.getSignature() + "<br/><br/><br/>"));

		newMt.setContent(content);
		return newMt;
	}

	

	/**
	 * 查询product template item 不为空的template name
	 * 
	 * @param owner
	 * @param custNo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List getOrderProductTempList(int owner, int custNo) {
		return srchOrderTemplateItemBeanDao.getOrderProductTempList(owner,
				custNo);
	}

	/**
	 * 查询product template item 不为空的template name
	 * 
	 * @param owner
	 * @param custNo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List getOrderServiceTempList(int owner, int custNo) {
		return srchServiceOrderTemplateBeanDao.getOrderServiceTempList(owner,
				custNo);
	}

	public void delOrderItemListForPeptide(List<Integer> ids) {
		if (ids != null && ids.size() > 0) {
			orderPeptideDao.delOrderItemList(ids);
			orderItemDao.delOrderItemList(ids);
		}
	}

	/**
	 * 查询某段时间内创建的status为(NW,RV,BO,PB,CC,VC)的所有order
	 * 
	 * @param fromDate
	 *            start date
	 * @param toDate
	 *            end date
	 */
	public List<OrderMain> searchOrderForCreateWO(Date fromDate, Date toDate) {
		List<OrderMain> orderList = this.orderDao.findOrderByDate(fromDate,
				toDate);
		List<OrderMain> returnOrderList = new ArrayList<OrderMain>();
		for (OrderMain order : orderList) {
			if ("NW".equals(order.getStatus())
					|| "RV".equals(order.getStatus())
					|| "BO".equals(order.getStatus())
					|| "PB".equals(order.getStatus())
					|| "CC".equals(order.getStatus())
					|| "VC".equals(order.getStatus())) {
				returnOrderList.add(order);
			}
		}
		return returnOrderList;
	}

	/**
	 * 通过noteId查询customer Note信息
	 * 
	 * @param noteKey
	 * @param sessOrderNo
	 * @return
	 * @author Zhang Yong
	 */
	public InstructionDTO getInstructByCustNoteId(String noteKey,
			String sessOrderNo) {
		InstructionDTO instructionDTO = null;
		if (noteKey.contains(OrderInstructionType.CUSTOMER.value() + "-")) {
			String custNoteId = noteKey.substring(
					(OrderInstructionType.CUSTOMER.value().length() + 1),
					noteKey.length());
			if (!StringUtils.isNumeric(custNoteId)) {
				return instructionDTO;
			}
			CustomerNote customerNote = customerNoteDao.getById(Integer
					.parseInt(custNoteId));
			if (customerNote == null) {
				return instructionDTO;
			}
			instructionDTO = new InstructionDTO();
			instructionDTO.setId(customerNote.getId());
			instructionDTO.setCustNoteId(OrderInstructionType.CUSTOMER.value()
					+ "-" + customerNote.getId());
			if (StringUtils.isNumeric(sessOrderNo)) {
				instructionDTO.setOrderNo(Integer.parseInt(sessOrderNo));
			} else {
				instructionDTO.setOrderNo(null);
			}
			instructionDTO.setType(customerNote.getType());
			instructionDTO.setDescription(customerNote.getDescription());
			instructionDTO.setDocFlag(customerNote.getDocFlag());
			instructionDTO.setNoteDate(customerNote.getCreationDate());
			instructionDTO.setInstructionDate(customerNote.getCreationDate());
			instructionDTO.setSource(OrderInstructionType.CUSTOMER.value());
			User createUser = this.userDao.getById(customerNote.getCreatedBy());
			if (createUser != null) {
				instructionDTO.setCreateUser(createUser.getLoginName());
			}
			List<NoteDocument> docList = this.custNoteDocumentDao
					.getDocumentList(customerNote.getId(),
							DocumentType.CUSTOMER_NOTE_TYPE);
			instructionDTO.setCustNoteDocumentList(docList);
		}
		return instructionDTO;
	}

	/**
	 * 查询所有的OligoBackbones
	 * 
	 * @return
	 * @author zhang yong
	 */
	public List<OligoBackbonesDTO> findOligoBackbonesList() {
		List<OligoBackbonesDTO> newObbDTOlist = new ArrayList<OligoBackbonesDTO>();
		List<OligoBackbones> obblist = oligoBackbonesDao.getAll();
		if (obblist != null && !obblist.isEmpty()) {
			for (OligoBackbones obb : obblist) {
				OligoBackbonesDTO newObbDTO = dozer.map(obb,
						OligoBackbonesDTO.class);
				if (!StringUtils.isEmpty(obb.getBName())
						&& obb.getBName().contains("'")) {
					newObbDTO.setBNameShow(newObbDTO.getBName());
					newObbDTO.setBName(newObbDTO.getBName()
							.replaceAll("'", "@"));
					newObbDTOlist.add(newObbDTO);
				} else {
					newObbDTO.setBNameShow(newObbDTO.getBName());
					newObbDTOlist.add(newObbDTO);
				}
			}
		}
		return newObbDTOlist;
	}

	/**
	 * 查询所有所有的OligoModifications
	 * 
	 * @return oligoModificationsListMap
	 * @author zhang yong
	 */
	public Map<String, List<OligoModifications>> findOligoModificationsList() {
		Map<String, List<OligoModifications>> oligoModificationsListMap = new HashMap<String, List<OligoModifications>>();
		List<OligoModifications> oligoModificationsList = oligoModificationsDao
				.getAll();
		if (oligoModificationsList == null || oligoModificationsList.isEmpty()) {
			return oligoModificationsListMap;
		}
		List<OligoModifications> rna5ModificationList = new ArrayList<OligoModifications>();
		List<OligoModifications> rnaInternalModificationList = new ArrayList<OligoModifications>();
		List<OligoModifications> rna3ModificationList = new ArrayList<OligoModifications>();
		List<OligoModifications> dna5ModificationList = new ArrayList<OligoModifications>();
		List<OligoModifications> dnaInternalModificationList = new ArrayList<OligoModifications>();
		List<OligoModifications> dna3ModificationList = new ArrayList<OligoModifications>();
		for (OligoModifications om : oligoModificationsList) {
			if (OligoModificationsType.RNA5MODIFICATION.getValue().equals(
					om.getMType())) {
				rna5ModificationList.add(om);
			}
			if (OligoModificationsType.RNAINTERNALMODIFICATION.getValue()
					.equals(om.getMType())) {
				rnaInternalModificationList.add(om);
			}
			if (OligoModificationsType.RNA3MODIFICATION.getValue().equals(
					om.getMType())) {
				rna3ModificationList.add(om);
			}
			if (OligoModificationsType.DNA5MODIFICATION.getValue().equals(
					om.getMType())) {
				dna5ModificationList.add(om);
			}
			if (OligoModificationsType.DNAINTERNALMODIFICATION.getValue()
					.equals(om.getMType())) {
				dnaInternalModificationList.add(om);
			}
			if (OligoModificationsType.DNA3MODIFICATION.getValue().equals(
					om.getMType())) {
				dna3ModificationList.add(om);
			}
		}
		if (!rna5ModificationList.isEmpty()) {
			getNewModificationList(rna5ModificationList,
					OligoModificationsType.RNA5MODIFICATION.toString(),
					oligoModificationsListMap);
		}
		if (!rnaInternalModificationList.isEmpty()) {
			getNewModificationList(rnaInternalModificationList,
					OligoModificationsType.RNAINTERNALMODIFICATION.toString(),
					oligoModificationsListMap);
		}
		if (!rna3ModificationList.isEmpty()) {
			getNewModificationList(rna3ModificationList,
					OligoModificationsType.RNA3MODIFICATION.toString(),
					oligoModificationsListMap);
		}
		if (!dna5ModificationList.isEmpty()) {
			getNewModificationList(dna5ModificationList,
					OligoModificationsType.DNA5MODIFICATION.toString(),
					oligoModificationsListMap);
		}
		if (!dnaInternalModificationList.isEmpty()) {
			getNewModificationList(dnaInternalModificationList,
					OligoModificationsType.DNAINTERNALMODIFICATION.toString(),
					oligoModificationsListMap);
		}
		if (!dna3ModificationList.isEmpty()) {
			getNewModificationList(dna3ModificationList,
					OligoModificationsType.DNA3MODIFICATION.toString(),
					oligoModificationsListMap);
		}
		return oligoModificationsListMap;
	}

	/**
	 * 重新整理modificationList
	 * 
	 * @param modificationList
	 * @param mapKey
	 * @param oligoModificationsListMap
	 * @return oligoModificationsListMap
	 * @author zhang yong
	 */
	public Map<String, List<OligoModifications>> getNewModificationList(
			List<OligoModifications> modificationList, String mapKey,
			Map<String, List<OligoModifications>> oligoModificationsListMap) {
		Map<String, String> parentNameMap = new HashMap<String, String>();
		for (OligoModifications om : modificationList) {
			if (om.getMParentName() != null
					&& !parentNameMap.containsKey(om.getMParentName())) {
				parentNameMap.put(om.getMParentName(), om.getMParentName());
			}
		}
		if (!parentNameMap.isEmpty()) {
			List<OligoModifications> newModificationList = new ArrayList<OligoModifications>();
			for (String key : parentNameMap.keySet()) {
				OligoModifications parentOM = new OligoModifications();
				parentOM.setMName(key);
				newModificationList.add(parentOM);
				for (OligoModifications om : modificationList) {
					if (key.equals(om.getMParentName())) {
						newModificationList.add(om);
					}
				}
			}
			oligoModificationsListMap.put(mapKey, newModificationList);
		}
		return oligoModificationsListMap;
	}

	/**
	 * 查询所有的OligoChimericBases
	 * 
	 * @param oligoBackbonesList
	 * @return
	 * @author zhang yong
	 */
	public List<OligoChimericBases> findOligoChimericBases(
			List<OligoBackbonesDTO> oligoBackbonesList) {
		List<OligoChimericBases> ocbDTOList = new ArrayList<OligoChimericBases>();
		if (oligoBackbonesList == null || oligoBackbonesList.isEmpty()) {
			return ocbDTOList;
		}
		List<OligoChimericBases> ocbList = oligoChimericBasesDao.getAll();
		if (ocbList == null || ocbList.isEmpty()) { 
			return ocbDTOList;
		}
		for (OligoBackbonesDTO ob : oligoBackbonesList) {
			OligoChimericBases obOligoChimericBases = new OligoChimericBases();
			obOligoChimericBases.setCBase(ob.getBNameShow());
			ocbDTOList.add(obOligoChimericBases);
			for (OligoChimericBases oligoChimericBases : ocbList) {
				if (oligoChimericBases.getBId() == null) {
					continue;
				}
				if (ob.getId().toString()
						.equals(oligoChimericBases.getBId().toString())) {
					ocbDTOList.add(oligoChimericBases);
				}
			}
		}
		return ocbDTOList;
	}

	/**
	 * 查询所有的OligoMixedBases
	 * 
	 * @return
	 * @author zhang yong
	 */
	public List<OligoMixedBases> findOligoMixedBases() {
		return oligoMixedBasesDao.getAll();
	}

	/**
	 * 处理oligo信息
	 * 
	 * @param targetOligoDTO
	 * @return targetOligoDTO
	 * @author zhang yong
	 */
	public OrderOligoDTO getTargetOligoDTO(OrderOligoDTO targetOligoDTO)
			throws Exception {
		String sequence = targetOligoDTO.getSequence();
		String backbone = targetOligoDTO.getBackbone();
		if (sequence == null || sequence.isEmpty() || backbone == null
				|| backbone.isEmpty()) {
			throw new Exception();
		}
		try {
			if (targetOligoDTO.getBackbone().contains("@")) {
				targetOligoDTO.setBackbone(targetOligoDTO.getBackbone()
						.replaceAll("@", "'"));
			}
			if (targetOligoDTO.getBackbone().contains("RNA")) {
				targetOligoDTO.setPurification(targetOligoDTO.getPurification()
						.split(",")[0].trim());
			} else {
				if (targetOligoDTO.getPurification().contains(",")) {
					targetOligoDTO.setPurification(targetOligoDTO
							.getPurification().split(",")[1].trim());
				} else {
					targetOligoDTO.setPurification(null);
				}
			}
			if (("on").equals(targetOligoDTO.getOthermodificationFlag5())) {
				targetOligoDTO.setOthermodificationFlag5("Y");
			} else {
				targetOligoDTO.setOthermodificationFlag5("N");
			}
			if (("on").equals(targetOligoDTO.getInterOtherModificationFlag())) {
				targetOligoDTO.setInterOtherModificationFlag("Y");
			} else {
				targetOligoDTO.setInterOtherModificationFlag("N");
			}
			if (("on").equals(targetOligoDTO.getOthermodificationFlag3())) {
				targetOligoDTO.setOthermodificationFlag3("Y");
			} else {
				targetOligoDTO.setOthermodificationFlag3("N");
			}
			String[] moreInfos = orderOligoDao.getOrderOligoMoreDetail(
					sequence, backbone);
			if (moreInfos != null && moreInfos.length == 5) {
				targetOligoDTO.setModification5(moreInfos[0]);
				targetOligoDTO.setInternalModification(moreInfos[1]);
				targetOligoDTO.setModification3(moreInfos[2]);
				targetOligoDTO.setChimericBases(moreInfos[3]);
				targetOligoDTO.setStandardMixedMases(moreInfos[4]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return targetOligoDTO;
	}

	@Transactional(readOnly = true)
	public OrderItem getOrderItemByItemId(Integer itemId) {
		return orderItemDao.findUniqueBy("orderItemId", itemId);
	}

	/**
	 * customer confirm时检查product库存
	 * 
	 * @author Zhang Yong modify date 2011-10-26
	 * @param catlogNoWareId
	 *            catlogNo和WarehouseId中间以","分隔
	 * @param qty
	 * @return
	 */
	public boolean checkOrderItemProductStock(String catlogNoWareId,
			Integer qty, String customerCompany) {
		boolean hasStock = false;
		String[] strs = catlogNoWareId.split(",");
		BigDecimal unitInStock = erpSalesOrderService.getPartStorageNumber(
				strs[0], customerCompany);
		if (unitInStock == null) {
			hasStock = false;
			return hasStock;
		}
		Integer virtualAmount = unitInStock.intValue();
		if (virtualAmount != null && virtualAmount.intValue() >= qty.intValue()) {
			hasStock = true;
		}
		return hasStock;
	}

	@Transactional(readOnly = true)
	public String getOrderPoNumber(Integer orderNo) {
		return paymentVoucherDao.getPoNumber(orderNo);
	}

	/**
	 * 获取Project Manager填充到SalesInformation tab 中
	 * 
	 * @author Zhang Yong
	 * @param orderDTO
	 * @return
	 */
	public List<User> getProManagerList(OrderMainDTO orderDTO) {
		if (orderDTO != null && orderDTO.getItemList() != null
				&& !orderDTO.getItemList().isEmpty()) {
			Integer firstServiceClsId = null;
			for (OrderItemDTO ordDTO : orderDTO.getItemList()) {
				if (QuoteItemType.SERVICE.value().equals(ordDTO.getType())
						&& null != ordDTO.getClsId()) {
					firstServiceClsId = ordDTO.getClsId();
					break;
				}
			}
			if (firstServiceClsId != null) {
				return salesProjectManagerAssignmentDao
						.findProManagerByClsId(firstServiceClsId);
			}
		}
		return null;
	}

	/**
	 * 获取Alt Project Manager 填充到SalesInformation tab 中
	 * 
	 * @author Zhang Yong
	 * @return
	 */
	public List<User> getAltProManagerList() {
		return salesRepDao.findUserByFunction(SalesRepSalesRole.PROJECT_SUPPORT
				.value());
	}

	/**
	 * 获取由us order变成的china order
	 * 
	 * @return
	 */
	public Page<MfgOrderDTO2> searchMfgOrderDTOPage(Page<MfgOrderDTO2> page,
			List<PropertyFilter> filters) {
		return this.mfgOrderDao.searchMfgOrderDTO2(page, filters);
	}

	public MfgOrder getMfgOrderDetail(Integer orderNo) {
		MfgOrder mfgOrder = this.mfgOrderDao.getById(orderNo);
		if (mfgOrder != null) {
			List<MfgOrderItem> list = this.mfgOrderItemDao.findBy("orderNo",
					mfgOrder.getOrderNo());
			mfgOrder.setMfgOrderItemList(list);
		}
		return mfgOrder;
	}

	public MfgOrder getMfgOrder(Integer orderNo) {
		MfgOrder mfgOrder = this.mfgOrderDao.getById(orderNo);
		return mfgOrder;
	}

	public boolean isEventFlag() {
		return eventFlag;
	}

	public void setEventFlag(boolean eventFlag) {
		this.eventFlag = eventFlag;
	}

	/**
	 * 查看所有Item的MoreDetail信息 modify by Zhang Yong
	 * 
	 * @param sessOrderNo
	 * @return
	 */
	public String getAllItemsDetail(String sessOrderNo, int a) {
		@SuppressWarnings("unchecked")
		Map<String, OrderItemDTO> orderItemMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OrderItemList.value(),
						sessOrderNo.toString());
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo.toString());
		if (orderMain == null) {
			return null;
		}
		StringBuffer sbf = new StringBuffer();
		String tr = "";
		if (a == 1) {
			tr = "\r\n";
		} else {
			tr = "<br/>";
		}
		if (orderItemMap != null && !orderItemMap.isEmpty()) {
			sbf.append(tr);
			Map<Integer, ServiceClass> serviceMap = new HashMap<Integer, ServiceClass>();
			Map<Integer, ProductClass> productMap = new HashMap<Integer, ProductClass>();
			ServiceClass serviceClass = null;
			ProductClass productClass = null;
			for (String key : orderItemMap.keySet()) {
				OrderItemDTO orderItemDto = orderItemMap.get(key);
				Integer clsId = orderItemDto.getClsId();
				if (clsId == null) {
					continue;
				}
				if (CatalogType.SERVICE.value().equalsIgnoreCase(
						orderItemDto.getType())) {
					if (!serviceMap.containsKey(clsId)) {
						serviceClass = serviceClassDao.getById(clsId);
						serviceMap.put(clsId, serviceClass);
					} else {
						serviceClass = serviceMap.get(clsId);
					}
				} else {
					if (!productMap.containsKey(clsId)) {
						productClass = productClassDao.getById(clsId);
						productMap.put(clsId, productClass);
					} else {
						productClass = productMap.get(clsId);
					}
				}
				sbf.append(this.orderEmailService.getMoreItemsInfoForTemp(orderItemMap, orderMain, key, orderItemDto, serviceClass, productClass, a));
			}
		}
		return sbf.toString();
	}

	/**
	 * 通过OrderNo或CustNo查询Oligo信息:custNo有值则查其所有Order的Oligo信息，orderNo有值则查单个Oligo信息
	 * 
	 * @author Zhang Yong add date 2011-09-27
	 * @param paramCustNo
	 * @param paramOrderNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public WritableSheet getOrderOligoInfo(Set<Integer> orderNoSet,
			List<PropertyFilter> filters, WritableSheet sheet) {
		try {
			// 一些临时变量，用于写到excel中
			Label l = null;
			jxl.write.Number n = null;
			jxl.write.DateTime d = null;

			// 预定义的一些字体和格式，同一个Excel中最好不要有太多格式
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLUE);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);

			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

			WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat detFormat = new WritableCellFormat(detFont);

			NumberFormat nf = new NumberFormat("0.00"); // 用于Number的格式
			WritableCellFormat priceFormat = new WritableCellFormat(detFont, nf);

			DateFormat df = new DateFormat("yyyy-MM-dd");// 用于日期的
			WritableCellFormat dateFormat = new WritableCellFormat(detFont, df);

			// 剩下的事情，就是用上面的内容和格式创建一些单元格，再加到sheet中
			l = new Label(0, 0, "Excel", headerFormat);
			sheet.addCell(l);

			// add Title
			int column = 0;
			l = new Label(column++, 0, "客户", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "订货日期", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "组别", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "单号", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "合成部编号", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "基因部编号", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "序列(5'-3')", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "纯化方式", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "碱基数", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "OD值", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "管数", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "备注", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "分子量", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "摩尔数/管", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "单价", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "总价", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 0, "正常发货日期", titleFormat);
			sheet.addCell(l);

			// add detail
			int i = 0;
			Page<OrderMainBean> pageOrderMain = new Page<OrderMainBean>();
			pageOrderMain.setPageSize(20000000);
			pageOrderMain = searchOrder(pageOrderMain, filters, orderNoSet);
			if (pageOrderMain != null && pageOrderMain.getResult() != null) {
				List<Object[]> oligoItemList = null;
				Map<Integer, String> userInfoMap = new HashMap<Integer, String>();
				for (OrderMainBean omb : pageOrderMain.getResult()) {
					oligoItemList = orderOligoDao.getOligoInfo(null,
							omb.getOrderNo());
					if (oligoItemList == null || oligoItemList.isEmpty()) {
						continue;
					}
					String userName = null;
					for (Object[] objs : oligoItemList) {
						OrderOligo oligo = (OrderOligo) objs[0];
						OrderItem orderItem = (OrderItem) objs[1];
						// --处理逻辑部分start--//
						Integer createBy = oligo.getCreatedBy();
						if (createBy != null) {
							if (userInfoMap.containsKey(createBy)) {
								userName = userInfoMap.get(createBy);
							} else {
								User user = userDao.getById(oligo
										.getCreatedBy());
								userName = (user == null
										|| user.getFirstName() == null ? ""
										: user.getFirstName())
										+ " "
										+ (user == null
												|| user.getLastName() == null ? ""
												: user.getLastName());
							}
						} else {
							userName = "";
						}
						String seq = oligo.getSequence();
						String newSeq = StringUtils.isBlank(seq) ? "" : seq
								.replaceAll("\\/[^/]*\\/", "")
								.replaceAll("\\{.*\\}", "").replaceAll(" ", "");
						Integer seqLength = oligo.getSeqLength();
						if (StringUtils.isNotBlank(seq) && seqLength == null) {
							seqLength = newSeq.replaceAll("\\(.*?\\)", "X")
									.length();
						}
						column = 0;
						// 管数
						Integer vials = oligo.getAliquotingInto() == null
								|| oligo.getAliquotingInto() < 1 ? 1 : oligo
								.getAliquotingInto();
						// 每管的mol数
						Double molPerVial = oligo.getAliquotingSize() == null
								|| oligo.getAliquotingSize().doubleValue() == 0 ? 5
								: oligo.getAliquotingSize().doubleValue();
						// 分子量
						Double mw = getMolecularWeight(newSeq);
						// 每管的OD值
						Double od = molPerVial * 1.2 * mw / 33000;
						// 单价和总价
						String[] p1Price = getP1Price(oligo.getPurification(),
								od * vials, seqLength);
						// 正常发货日期
						Date now = new Date();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(now);
						calendar.set(Calendar.DAY_OF_MONTH,
								calendar.get(Calendar.DAY_OF_MONTH) + 2);// 让当前日期加2
						Date today2 = calendar.getTime();// 加2 天之后的日期
						calendar.set(Calendar.DAY_OF_MONTH,
								calendar.get(Calendar.DAY_OF_MONTH) + 1);// 让当前日期加3
						Date today3 = calendar.getTime();// 加3 天之后的日期
						Date shipDate = (seqLength == null || seqLength
								.intValue() <= 60) ? today2 : today3;
						// --处理逻辑部分end--//
						// 写入excel
						// 客户
						l = new Label(column++, i + 1, userName, detFormat);
						sheet.addCell(l);
						// 订货日期
						d = new DateTime(column++, i + 1, now, dateFormat);
						sheet.addCell(d);
						// 组别
						l = new Label(column++, i + 1, "", detFormat);
						sheet.addCell(l);
						// 单号
						l = new Label(column++, i + 1, orderItem.getOrderNo()
								+ "", detFormat);
						sheet.addCell(l);
						// 合成部编号
						l = new Label(column++, i + 1, orderItem.getOrderNo()
								+ "_" + orderItem.getItemNo(), detFormat);
						sheet.addCell(l);
						// 基因部编号
						l = new Label(column++, i + 1, oligo.getOligoName(),
								detFormat);
						sheet.addCell(l);
						// 序列(5'-3')
						l = new Label(column++, i + 1, seq, detFormat);
						sheet.addCell(l);
						// 纯化方式
						l = new Label(column++, i + 1, oligo.getPurification(),
								detFormat);
						sheet.addCell(l);
						// 碱基数
						l = new Label(column++, i + 1, (seqLength == null ? ""
								: seqLength + ""), detFormat);
						sheet.addCell(l);
						// OD值
						l = new Label(column++, i + 1,
								String.valueOf(new BigDecimal(od).setScale(1,
										BigDecimal.ROUND_HALF_UP)), detFormat);
						sheet.addCell(l);
						// 管数
						l = new Label(column++, i + 1, vials + "", detFormat);
						sheet.addCell(l);
						// 备注
						l = new Label(column++, i + 1, oligo.getComments(),
								detFormat);
						sheet.addCell(l);
						// 分子量
						n = new jxl.write.Number(column++, i + 1, mw,
								priceFormat);
						sheet.addCell(n);
						// 摩尔数/管
						n = new jxl.write.Number(column++, i + 1, molPerVial,
								priceFormat);
						sheet.addCell(n);
						// 单价
						l = new Label(column++, i + 1, p1Price[0], detFormat);
						sheet.addCell(l);
						// 总价
						l = new Label(column++, i + 1, p1Price[1], detFormat);
						sheet.addCell(l);
						// 正常发货日期
						d = new DateTime(column++, i + 1, shipDate, dateFormat);
						sheet.addCell(d);
						i++;
					}
				}
			}
			// 设置列的宽度
			column = 0;
			sheet.setColumnView(column++, 18);
			sheet.setColumnView(column++, 15);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 15);
			sheet.setColumnView(column++, 20);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 20);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 20);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 10);
			sheet.setColumnView(column++, 15);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 获取OD值
	 * 
	 * @author Zhang Yong add date 2011-10-09
	 * @param seq
	 *            序列
	 * @param mol
	 *            每管的mol数
	 * @return
	 */
	// private Double getOD (String seq, Double mol) {
	// Double JQFZL = getMolecularWeight(seq);
	// Double od = mol*1.2*JQFZL/33000;
	// return od;
	// }

	/**
	 * 获取分子量
	 * 
	 * @author Zhang Yong add date 2011-10-10
	 * @param seq
	 *            序列
	 * @return
	 */
	private Double getMolecularWeight(String newSeq) {
		Double JQFZL = 0d;
		if (StringUtils.isBlank(newSeq)) {
			return JQFZL;
		}
		Integer countA = 0;
		Integer countC = 0;
		Integer countG = 0;
		Integer countT = 0;
		Integer countU = 0;
		for (int i = 0; i < newSeq.length(); i++) {
			char oneChar = newSeq.charAt(i);
			if (oneChar == 'A' || oneChar == 'a') {
				countA += 1;
			} else if (oneChar == 'C' || oneChar == 'c') {
				countC += 1;
			} else if (oneChar == 'G' || oneChar == 'g') {
				countG += 1;
			} else if (oneChar == 'T' || oneChar == 't') {
				countT += 1;
			} else if (oneChar == 'U' || oneChar == 'u') {
				countU += 1;
			}
		}
		JQFZL = countA * 313.21 + countC * 289.18 + countG * 329.21 + countT
				* 304.2 + countU * 290.17 - 61.96;
		return JQFZL < 0 ? 0 : JQFZL;
	}

	/**
	 * 获取单价,总价
	 * 
	 * @author Zhang Yong add date 2011-10-10
	 * @param purificationMethod
	 *            纯化方式
	 * @param totalOd
	 *            总的OD值
	 * @param seqLength
	 *            序列长度
	 * @return
	 */
	public String[] getP1Price(String purificationMethod, Double totalOd,
			Integer seqLength) {
		String[] price = new String[2];
		Double p1Price = 0.52;
		if (StringUtils.isBlank(purificationMethod)
				|| !purificationMethod.equalsIgnoreCase("PAGE")) {
			if (totalOd <= 5) {
				p1Price = 0.52;
			} else if (totalOd > 5 && totalOd <= 10) {
				p1Price = 0.85;
			} else if (totalOd > 10 && totalOd <= 20) {
				p1Price = 1.6;
			} else if (totalOd > 20 && totalOd <= 30) {
				p1Price = 2.4;
			} else if (totalOd > 30 && totalOd <= 40) {
				p1Price = 3.0;
			} else if (totalOd > 40 && totalOd <= 50) {
				p1Price = 3.6;
			} else if (totalOd > 50 && totalOd <= 60) {
				p1Price = 4.3;
			} else if (totalOd > 60 && totalOd <= 70) {
				p1Price = 5.1;
			} else if (totalOd > 70 && totalOd <= 80) {
				p1Price = 5.8;
			} else if (totalOd > 80 && totalOd <= 90) {
				p1Price = 6.5;
			} else if (totalOd > 90 && totalOd <= 100) {
				p1Price = 7.0;
			} else {
				price[0] = "TBD";
			}
		} else {
			p1Price = 0.80;
			if (seqLength != null && seqLength.intValue() < 109) {
				if (totalOd <= 2) {
					p1Price = 0.8;
				} else if (totalOd > 2 && totalOd <= 5) {
					p1Price = 1.0;
				} else if (totalOd > 5 && totalOd <= 10) {
					p1Price = 1.5;
				} else if (totalOd > 10 && totalOd <= 20) {
					p1Price = 2.5;
				}
			}
		}
		if (!price.equals("TBD")) {
			price[0] = "￥" + p1Price;
			Double totalPrice = new BigDecimal(p1Price
					* (seqLength == null ? 0 : seqLength)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			price[1] = "￥" + totalPrice;
		} else {
			price[1] = "TBD";
		}
		return price;

	}

	public HashMap<Integer, HashMap<Integer, List<ManuDocument>>> getAllItemsDocumentsByorderNo(
			int sessOrderNo) {
		return (HashMap<Integer, HashMap<Integer, List<ManuDocument>>>) this.orderDao
				.getAllItemsDocumentsByorderNo(sessOrderNo);
	}

	public HashMap<Integer, List<ManuDocument>> getAllItemsDocumentsByorderNo2(
			int sessOrderNo) {
		return (HashMap<Integer, List<ManuDocument>>) this.orderDao
				.getAllItemsDocumentsByorderNo2(sessOrderNo);
	}

	public List<Document> getALLDocumentByREFType(String strtype, Integer refId) {
		return this.documentDao.getDocument(strtype, refId);
	}

	public List<ArInvoicePayment> getTotalPaymentsList(int orderNo) {
		System.out.println(orderNo);
		return this.arInvoicePaymentDao.getALLpaymentsByOrderNo(orderNo);
	}

	@Transactional(readOnly = true)
	public Date getChargeLastDate(Integer custNo, String chargeAccType,
			String chargeAccNo) {
		return shippingChargeLogDao.getChargeLastDate(custNo, chargeAccType,
				chargeAccNo);
	}

	public void saveCharger(Charger charger) {
		chargerDao.save(charger);
	}

    /*
    * add by zhanghuibin 添加reopen历史记录
    * */
    public boolean updateReopenHistory(OrderProcessLog orderProcessLog, Integer userId){
        boolean result = true;
        if (userId != null) {
            orderProcessLog.setProcessedBy(userDao.getById(userId));
        }
        orderProcessLogDao.save(orderProcessLog);
        return result;
    }
}