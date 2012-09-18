package com.genscript.gsscm.quote.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.OrderQuoteStautsType;
import com.genscript.gsscm.common.constant.OrderStatusType;
import com.genscript.gsscm.common.constant.QuoteInstructionType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.common.constant.SalesRepSalesRole;
import com.genscript.gsscm.common.constant.SequenceType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.constant.StrutsActionContant;
import com.genscript.gsscm.common.events.SendMailAtOnceEvent;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.AddressUtil;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.DateTypeConverter;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.PdfUtils;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.SoapUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.customer.dao.CouponDao;
import com.genscript.gsscm.customer.dao.CreditCardDao;
import com.genscript.gsscm.customer.dao.CreditRatingDao;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerNoteDao;
import com.genscript.gsscm.customer.dao.CustomerPriceBeanDao;
import com.genscript.gsscm.customer.dao.DepartmentDao;
import com.genscript.gsscm.customer.dao.NoteDocumentDao;
import com.genscript.gsscm.customer.dao.OrganizationDao;
import com.genscript.gsscm.customer.dao.SalesProjectManagerAssignmentDao;
import com.genscript.gsscm.customer.dao.SalesRepDao;
import com.genscript.gsscm.customer.dto.CustomerDTO;
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
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.order.dao.ExchRateByDateDao;
import com.genscript.gsscm.order.dao.OrderAddressDao;
import com.genscript.gsscm.order.dao.OrderAntibodyDao;
import com.genscript.gsscm.order.dao.OrderCustCloningDao;
import com.genscript.gsscm.order.dao.OrderDao;
import com.genscript.gsscm.order.dao.OrderDnaSequencingDao;
import com.genscript.gsscm.order.dao.OrderGeneSynthesisDao;
import com.genscript.gsscm.order.dao.OrderItemDao;
import com.genscript.gsscm.order.dao.OrderItemsPriceBeanDao;
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
import com.genscript.gsscm.order.dao.OrderServiceDao;
import com.genscript.gsscm.order.dao.OrderSirnaAndMirnaDao;
import com.genscript.gsscm.order.dao.OrderTemplateItemBeanDao;
import com.genscript.gsscm.order.dao.PromotionDao;
import com.genscript.gsscm.order.dao.RelationProductPriceBeanDao;
import com.genscript.gsscm.order.dao.ServiceOrderTemplateBeanDao;
import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.dao.TaxRateDao;
import com.genscript.gsscm.order.dto.ItemOtherInfoForNewDTO;
import com.genscript.gsscm.order.dto.QuoteMainBeanDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.entity.OrderAddress;
import com.genscript.gsscm.order.entity.OrderAntibody;
import com.genscript.gsscm.order.entity.OrderCustCloning;
import com.genscript.gsscm.order.entity.OrderDnaSequencing;
import com.genscript.gsscm.order.entity.OrderGeneSynthesis;
import com.genscript.gsscm.order.entity.OrderItem;
import com.genscript.gsscm.order.entity.OrderItemsPriceBean;
import com.genscript.gsscm.order.entity.OrderMain;
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
import com.genscript.gsscm.order.entity.OrderSirnaAndMirna;
import com.genscript.gsscm.order.entity.OrderTemplateItemBean;
import com.genscript.gsscm.order.entity.Promotion;
import com.genscript.gsscm.order.entity.ServiceOrderTemplateBean;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.orf.dao.Refseq2orfpriceDao;
import com.genscript.gsscm.orf.entity.Refseq2orfprice;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.dao.UserRoleDao;
import com.genscript.gsscm.privilege.entity.Employee;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.dao.ShipConditionDao;
import com.genscript.gsscm.product.dto.ProductItemPriceDTO;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.quote.dao.PreQuotationDao;
import com.genscript.gsscm.quote.dao.QuoteAddressDao;
import com.genscript.gsscm.quote.dao.QuoteAntibodyDao;
import com.genscript.gsscm.quote.dao.QuoteCustCloningDao;
import com.genscript.gsscm.quote.dao.QuoteDao;
import com.genscript.gsscm.quote.dao.QuoteDnaSequencingDao;
import com.genscript.gsscm.quote.dao.QuoteDsLabelsDao;
import com.genscript.gsscm.quote.dao.QuoteDsPlatesDao;
import com.genscript.gsscm.quote.dao.QuoteDsSeqDao;
import com.genscript.gsscm.quote.dao.QuoteGeneSynthesisDao;
import com.genscript.gsscm.quote.dao.QuoteItemDao;
import com.genscript.gsscm.quote.dao.QuoteItemsPriceBeanDao;
import com.genscript.gsscm.quote.dao.QuoteMailDao;
import com.genscript.gsscm.quote.dao.QuoteMainBeanDao;
import com.genscript.gsscm.quote.dao.QuoteMutagenesisDao;
import com.genscript.gsscm.quote.dao.QuoteMutationLibrariesDao;
import com.genscript.gsscm.quote.dao.QuoteNoteDao;
import com.genscript.gsscm.quote.dao.QuoteOligoDao;
import com.genscript.gsscm.quote.dao.QuoteOrfCloneDao;
import com.genscript.gsscm.quote.dao.QuotePackageDao;
import com.genscript.gsscm.quote.dao.QuotePackageItemDao;
import com.genscript.gsscm.quote.dao.QuotePaymentPlanDao;
import com.genscript.gsscm.quote.dao.QuotePeptideDao;
import com.genscript.gsscm.quote.dao.QuotePkgServiceDao;
import com.genscript.gsscm.quote.dao.QuotePlasmidPreparationDao;
import com.genscript.gsscm.quote.dao.QuoteProcessLogDao;
import com.genscript.gsscm.quote.dao.QuotePromotionDao;
import com.genscript.gsscm.quote.dao.QuoteProteinDao;
import com.genscript.gsscm.quote.dao.QuoteRnaDao;
import com.genscript.gsscm.quote.dao.QuoteServiceDao;
import com.genscript.gsscm.quote.dao.QuoteSirnaAndMirnaDao;
import com.genscript.gsscm.quote.dao.QuoteTemplateDao;
import com.genscript.gsscm.quote.dao.QuoteTemplateItemBeanDao;
import com.genscript.gsscm.quote.dao.ServiceQuoteTemplateBeanDao;
import com.genscript.gsscm.quote.dao.SrchQuoteTemplateItemBeanDao;
import com.genscript.gsscm.quote.dao.SrchServiceQuoteTemplateBeanDao;
import com.genscript.gsscm.quote.dto.QuotationPrintDTO;
import com.genscript.gsscm.quote.dto.QuoteAddressDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.dto.QuotePackageDTO;
import com.genscript.gsscm.quote.dto.QuotePackageItemDTO;
import com.genscript.gsscm.quote.dto.QuotePromotionDTO;
import com.genscript.gsscm.quote.entity.PreQuotation;
import com.genscript.gsscm.quote.entity.QuoteAddress;
import com.genscript.gsscm.quote.entity.QuoteAntibody;
import com.genscript.gsscm.quote.entity.QuoteCustCloning;
import com.genscript.gsscm.quote.entity.QuoteDnaSequencing;
import com.genscript.gsscm.quote.entity.QuoteDsLabels;
import com.genscript.gsscm.quote.entity.QuoteDsPlates;
import com.genscript.gsscm.quote.entity.QuoteDsSeq;
import com.genscript.gsscm.quote.entity.QuoteGeneSynthesis;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuoteItemsPriceBean;
import com.genscript.gsscm.quote.entity.QuoteMail;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuoteMainBean;
import com.genscript.gsscm.quote.entity.QuoteMutagenesis;
import com.genscript.gsscm.quote.entity.QuoteMutationLibraries;
import com.genscript.gsscm.quote.entity.QuoteNote;
import com.genscript.gsscm.quote.entity.QuoteOligo;
import com.genscript.gsscm.quote.entity.QuoteOrfClone;
import com.genscript.gsscm.quote.entity.QuotePackage;
import com.genscript.gsscm.quote.entity.QuotePackageItem;
import com.genscript.gsscm.quote.entity.QuotePaymentPlan;
import com.genscript.gsscm.quote.entity.QuotePeptide;
import com.genscript.gsscm.quote.entity.QuotePkgService;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;
import com.genscript.gsscm.quote.entity.QuoteProcessLog;
import com.genscript.gsscm.quote.entity.QuotePromotion;
import com.genscript.gsscm.quote.entity.QuoteRna;
import com.genscript.gsscm.quote.entity.QuoteSirnaAndMirna;
import com.genscript.gsscm.quote.entity.QuoteTemplate;
import com.genscript.gsscm.quote.entity.QuoteTemplateItemBean;
import com.genscript.gsscm.quote.entity.ServiceQuoteTemplateBean;
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
import com.genscript.gsscm.quoteorder.dto.TotalDTO;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.dao.ServiceShipConditionDao;
import com.genscript.gsscm.serv.entity.ServiceClass;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.system.dao.CompanyDao;
import com.genscript.gsscm.system.dao.MailTemplatesDao;
import com.genscript.gsscm.system.dao.UpdateRequestLogDao;
import com.genscript.gsscm.system.entity.Company;
import com.genscript.gsscm.system.entity.MailTemplates;
import com.genscript.gsscm.system.entity.UpdateRequestLog;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author user
 * 
 */
@Service
@Transactional
public class QuoteService implements ApplicationContextAware {
	@Autowired
	private FileService fileService;
	@Autowired
	private PublicService publicService; 
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerPriceBeanDao customerPriceBeanDao;
	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	private QuoteProcessLogDao quoteProcessLogDao;
	@Autowired
	private CreditCardDao creditCardDao;
	@Autowired
	protected QuoteMainBeanDao quoteMainDao;
	@Autowired
	private QuoteAddressDao quoteAddressDao;
	@Autowired
	private QuoteMailDao quoteMailDao;
	@Autowired
	private QuoteNoteDao quoteNoteDao;
	@Autowired
	private OrderNoteDao orderNoteDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private QuotePromotionDao quotePromotionDao;
	@Autowired
	private SalesRepDao salesRepDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private QuoteServiceDao quoteServiceDao;
	@Autowired
	private QuotePaymentPlanDao quotePaymentPlanDao;
	@Autowired
	private OrderTemplateItemBeanDao orderTemplateItemBeanDao;
	@Autowired
	private QuoteTemplateItemBeanDao quoteTemplateItemBeanDao;
	@Autowired
	private QuoteTemplateDao quoteTemplateDao;
	@Autowired
	private OrderMutationLibrariesDao orderMutationLibrariesDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private StatusListDao statusListDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private QuoteItemsPriceBeanDao quoteItemsPriceBeanDao;
	@Autowired
	private OrderItemsPriceBeanDao orderItemsPriceBeanDao;
	@Autowired
	private RelationProductPriceBeanDao relationProductPriceBeanDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PromotionDao promotionDao;
	@Autowired
	private QuoteAntibodyDao quoteAntibodyDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderAddressDao orderAddressDao;
	@Autowired
	private ExchRateByDateDao exchRateByDateDao;
	@Autowired
	private QuotePackageDao quotePackageDao;
	@Autowired
	private QuotePackageItemDao quotePackageItemDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TaxRateDao taxRateDao;
	@Autowired
	private ShipConditionDao shipConditionDao;
	@Autowired
	private QuoteCustCloningDao quoteCustCloningDao;
	@Autowired
	private QuoteGeneSynthesisDao quoteGeneSynthesisDao;
	@Autowired
	private QuotePlasmidPreparationDao quotePlasmidPreparationDao;
	@Autowired
	private QuoteRnaDao quoteRnaDao;
	@Autowired
	private QuoteOrfCloneDao quoteOrfCloneDao;
	@Autowired
	private QuotePeptideDao quotePeptideDao;
	@Autowired
	private QuoteProteinDao quoteProteinDao;
	@Autowired
	private QuoteMutagenesisDao quoteMutagenesisDao;
	@Autowired
	private OrderProcessLogDao orderProcessLogDao;
	@Autowired
	private QuoteMutationLibrariesDao quoteMutationLibrariesDao;
	@Autowired
	private QuoteSirnaAndMirnaDao quoteSirnaAndMirnaDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceShipConditionDao serviceShipConditionDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private CreditRatingDao creditRatingDao;
	@Autowired
	private QuotePkgServiceDao quotePkgServiceDao;
	@Autowired
	private OrderPlasmidPreparationDao orderPlasmidPreparationDao;
	@Autowired
	private OrderMutagenesisDao orderMutagenesisDao;
	@Autowired
	private OrderOrfCloneDao orderOrfCloneDao;
	@Autowired
	private OrderGeneSynthesisDao orderGeneSynthesisDao;
	@Autowired
	private OrderCustCloningDao orderCustCloningDao;
	@Autowired
	private OrderSirnaAndMirnaDao orderSirnaAndMirnaDao;
	@Autowired
	private OrderAntibodyDao orderAntibodyDao;
	@Autowired
	private OrderPkgServiceDao orderPkgServiceDao;
	@Autowired
	private OrderPeptideDao orderPeptideDao;
	@Autowired
	private QuoteOligoDao quoteOligoDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private PreQuotationDao preQuotationDao;
	@Autowired
	private PbCountryDao pbCountryDao;
	@Autowired
	private SpecDropDownListDao specDropDownListDao;
	@Autowired
	private OrderPromotionDao orderPromotionDao;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ServiceOrderTemplateBeanDao serviceOrderTemplateBeanDao;
	@Autowired
	private ServiceQuoteTemplateBeanDao serviceQuoteTemplateBeanDao;
	@Autowired
	private SrchQuoteTemplateItemBeanDao srchQuoteTemplateItemBeanDao;
	@Autowired
	private SrchServiceQuoteTemplateBeanDao srchServiceQuoteTemplateBeanDao;
	@Autowired
	private CustomerNoteDao customerNoteDao;
	@Autowired
	private NoteDocumentDao custNoteDocumentDao;
	@Autowired
	private QuoteShippingService quoteShippingService;
	@Autowired
	private OrderPackageDao orderPackageDao;
	@Autowired
	private OrderPackageItemDao orderPackageItemDao;
	@Autowired
	private SalesProjectManagerAssignmentDao salesProjectManagerAssignmentDao;
	@Autowired
	private QuoteItemDao quoteItemDao;
	@Autowired
	private OrderOligoDao orderOligoDao;
	@Autowired
	private ErpSalesOrderService erpSalesOrderService;
	@Autowired
	private BioInfoService bioInfoService;
	@Autowired
	private UpdateRequestLogDao updateRequestLogDao;
	@Autowired
	private MailTemplatesDao mailTemplatesDao;
	@Autowired
	private QuoteDnaSequencingDao quoteDnaSequencingDao;
	@Autowired
	private QuoteDsPlatesDao quoteDsPlatesDao;
	@Autowired
	private QuoteDsSeqDao quoteDsSeqDao;
	@Autowired
	private QuoteDsLabelsDao quoteDsLabelsDao;
	@Autowired
	private OrderDnaSequencingDao orderDnaSequencingDao;
	@Autowired
	private OrderServiceDao orderServiceDao;
	
	@Autowired
	private QuoteEmailService quoteEmailService;
	@Autowired
	private Refseq2orfpriceDao refseq2orfpriceDao;
	private static Logger logger = LoggerFactory.getLogger(QuoteService.class);
	private final static String FullLength = "Full Length";
	private final static String ORFSequence = "ORF Sequence";

	private ApplicationContext context;

	// ----------------------------add by zhougang ----start ----
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public Page<QuoteMainBeanDTO> searchQuote_new(final Integer custNo,
			final Page<QuoteMainBean> page, final List<PropertyFilter> filters)
			throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		// 判断当前用户是否含有销售经理角色
		boolean salesManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);

		Page<QuoteMainBeanDTO> quoteMainBeanDTOPage = new Page<QuoteMainBeanDTO>();

		if (Constants.USERNAME_ADMIN.equals(userName) || salesManager) {
			Page<QuoteMainBean> pages = quoteMainDao.findPage(page, filters);
			List newBean = new ArrayList();
			List ls = pages.getResult();
			for (int i = 0; i < ls.size(); i++) {
				QuoteMainBeanDTO quoteMainBeanDTO = null;
				QuoteMainBean quoteMainBean = (QuoteMainBean) ls.get(i);
				quoteMainBeanDTO = this.dozer.map(quoteMainBean,
						QuoteMainBeanDTO.class);
				Integer orderno = quoteMainBean.getQuoteNo();
				Integer qty = quoteItemDao.getQuoteItemsCountByQuoteNo(orderno);
				quoteMainBeanDTO.setQty(qty);
				System.out.println(qty);
				newBean.add(quoteMainBeanDTO);
			}
			quoteMainBeanDTOPage.setResult(newBean);
			return quoteMainBeanDTOPage;

		}
		Map<String, Object> salesRoleAndUserIdsMap = salesRepDao
				.getSameGroupUser();
		if (salesRoleAndUserIdsMap.get("function") != null
				&& salesRoleAndUserIdsMap.get("User_Ids") != null) {
			String salesRole = salesRoleAndUserIdsMap.get("function")
					.toString();
			Integer[] userIdsArr = (Integer[]) salesRoleAndUserIdsMap
					.get("User_Ids");

			List<Criterion> criterionList = new ArrayList<Criterion>();
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
			List newBean = new ArrayList();
			Page<QuoteMainBeanDTO> quoteMainBeanDTOPage2 = new Page<QuoteMainBeanDTO>();
			Page<QuoteMainBean> page1 = quoteMainDao.getQuotesByFilter(page,
					criterionList, filters);
			List ll = page1.getResult();
			for (int i = 0; i < ll.size(); i++) {
				QuoteMainBeanDTO quoteMainBeanDTO = null;
				QuoteMainBean quoteMainBean = (QuoteMainBean) ll.get(i);
				quoteMainBeanDTO = dozer.map(quoteMainBean,
						QuoteMainBeanDTO.class);
				Integer qty = quoteItemDao
						.getQuoteItemsCountByQuoteNo(quoteMainBean.getQuoteNo());
				quoteMainBeanDTO.setQty(qty);
				System.out.println(qty);
				newBean.add(quoteMainBeanDTO);
			}
			quoteMainBeanDTOPage2.setResult(newBean);
			return quoteMainBeanDTOPage2;
		}
		return quoteMainBeanDTOPage;
	}

	// ----------------------------end --------------------

	@Transactional(readOnly = true)
	public BigDecimal getPrePayment(final Integer custNo,
			final Integer quoteNo, final Double quoteTotal,
			final String toCurrency) {
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		if (quoteTotal != null && custNo != null) {
			// Double currentBalance =
			// this.custInfoStatDao.getCurrentBalance(custNo);
			Customer customer = customerDao.get(custNo);
			Integer crRatingId = customer.getCrRatingId();
			if (crRatingId != null) {
				CreditRating creditRating = creditRatingDao.get(crRatingId);
				Double crLimit = creditRating.getCrLimit();

				Date quoteDate;
				if (quoteNo != null) {
					QuoteMain quote = quoteDao.get(quoteNo);
					quoteDate = quote.getExchRateDate();
				} else {
					quoteDate = new Date();
				}
				Double rate = exchRateByDateDao.getCurrencyRate("USD",
						toCurrency, quoteDate);
				BigDecimal prePaymentBD;
				if (rate != null) {
					crLimit = crLimit * rate;
					// currentBalance = currentBalance* rate;
				}
				prePaymentBD = new BigDecimal(quoteTotal)
						.subtract(new BigDecimal(crLimit));
				// .subtract(new BigDecimal(currentBalance));
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
	 * 查询Customer Confirm Email Template
	 * 
	 * @author zhou gang
	 * @return MailTemplates
	 */
	@Transactional(readOnly = true)
	public MailTemplates findConfirmTemp(Integer quoteNo) throws Exception {
		if (quoteNo == null) {
			return null;
		}
		List<MailTemplates> mailTemplateslist = mailTemplatesDao.findbyFuncName(Constants.Follow_Up_Email);
		if (mailTemplateslist == null || mailTemplateslist.size() <= 0) {
			return null;
		}
		QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), quoteNo.toString());
		if (quoteMain == null) {
			return null;
		}
		Customer customer = this.customerDao.findByCustNo(quoteMain.getCustNo());
		if (customer == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, QuoteItemDTO> quoteItemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(),
						quoteNo.toString());
		MailTemplates mt = mailTemplateslist.get(0);
		MailTemplates newMt = new MailTemplates();
		newMt = this.dozer.map(mt, MailTemplates.class);
		String subject = newMt.getSubject();
		newMt.setSubject(subject == null ? "" : subject.replace("&quote_no", ""+ quoteMain.getQuoteNo()));
		String content = newMt.getContent();
		content = content == null ? "" : content.replace("&last_name",customer.getLastName());
		content = content.replace("&quote_no", quoteMain.getQuoteNo().toString());
		String upSymbol = "";
		if (quoteItemMap != null && quoteItemMap.size() > 0) {
			for (String key : quoteItemMap.keySet()) {
				QuoteItemDTO quoteItemDTO = quoteItemMap.get(key);
				if (quoteItemDTO != null && StringUtils.isNotBlank(quoteItemDTO.getUpSymbol())) {
					upSymbol = quoteItemDTO.getUpSymbol();
					break;
				}
			}
		}
		content = content.replace("&sub_total", " " + upSymbol + quoteMain.getSubTotal() + "<br/>");
		content = content.replace("&ship_amount", " " + upSymbol + quoteMain.getShipAmt() + "<br/>");
		content = content.replace("&order_total", " " + upSymbol + quoteMain.getAmount());

		StringBuffer sbf = new StringBuffer();
		if (quoteItemMap != null && quoteItemMap.size() > 0) {
			Map<Integer, ServiceClass> serviceMap = new HashMap<Integer, ServiceClass>();
			Map<Integer, ProductClass> productMap = new HashMap<Integer, ProductClass>();
			ServiceClass serviceClass = null;
			ProductClass productClass = null;
			for (String key : quoteItemMap.keySet()) {
				QuoteItemDTO quoteItemDto = quoteItemMap.get(key);
				Integer clsId = quoteItemDto.getClsId();
				if (clsId == null) {
					continue;
				}
				if (CatalogType.SERVICE.value().equalsIgnoreCase(quoteItemDto.getType())) {
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
				sbf.append(this.quoteEmailService.getMoreItemsInfoForTemp(quoteItemMap, quoteMain, key, quoteItemDto, serviceClass, productClass));
			}
            //重置MailFlag，防止再次浏时候没有内容
            for (String key : quoteItemMap.keySet()) {
                quoteItemMap.get(key).setMailFlag(false);
            }
		}
		content = content.replace("&item_detail", sbf.toString() + "<br/>");
		User user = this.userDao.findByUserId(SessionUtil.getUserId());
		content = content.replace("&signature", null == user.getSignature() || ("null").equals(user.getSignature().trim())
						|| ("").equals(user.getSignature().trim()) ? " <br/>" : user.getSignature() + "<br/><br/><br/>");
		newMt.setContent(content);
		return newMt;
	}



	

	/**
	 * 判断是否能够自动calculate shipping package. warehouseId, shipMethod, packageType,
	 * quoteAddress.四个元素是否完全一致. 并且设置packageType
	 * 
	 * @param itemDTOList
	 */
	public void checkAutoCalPackage(final List<QuoteItemDTO> itemDTOList,
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
		for (int i = 1; i < itemDTOList.size(); i++) {
			QuoteItemDTO dto = itemDTOList.get(i);
			typeSet.add(dto.getType().toLowerCase());
			shipMethodSet.add(dto.getShipMethod());
			QuoteAddress quoteAddress = dto.getShipToAddress();
			if (quoteAddress == null) {
				addrSet.add(null);
			} else {
				addrSet.add(AddressUtil.getUniqueAddress(
						quoteAddress.getFirstName(), quoteAddress.getMidName(),
						quoteAddress.getLastName(), quoteAddress.getState(),
						quoteAddress.getCountry()));
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
						"ProductShipCondition's shipPackageType");
			}
			dto.setPackageType(dtoPackageType);
			packageTypeSet.add(dtoPackageType);
		}
		int typeCount = typeSet.size();
		if (shipMethodSet.size() > typeCount || typeCount == 0
				|| shipMethodSet.contains(null)) {// typeCount为0说明itemList为空，
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"QuoteItem's shipMethod");
		}
		if (addrSet.size() > typeCount || addrSet.contains(null)) {
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"QuoteItem's shipToAddress");
		}
		if (packageTypeSet.size() > typeCount || packageTypeSet.contains(null)) {
			throw new BussinessException(
					BussinessException.INF_CAL_PACKING_DIFF,
					"ProductShipCondition's shipPackageType");
		}
	}

	/**
	 * 根据quoteNo获得shipPackage基本信息 list.
	 * 
	 * @param quoteNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<QuotePackageDTO> getBaseQuoteShipPackageList(Integer quoteNo) {
		List<QuotePackageDTO> dtoList = new ArrayList<QuotePackageDTO>();
		List<QuotePackage> srcList = this.quotePackageDao.getQuotePackageList(quoteNo);
		if (srcList != null && !srcList.isEmpty()) {
			for (QuotePackage source : srcList) {
				QuotePackageDTO dto = new QuotePackageDTO();
				dto = getPackageDetail(source.getPackageId());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 判断quantity 和 promotion是否可以更改
	 * 
	 * @param amountList
	 * @param promotionCode
	 * @return
	 */
	public boolean checkQuoteTotal(List<Double> amountList, String promotionCode) {
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;

	}

	/**
	 * 获得一个package的详细信息.
	 * 
	 * @param packageId
	 * @return
	 */
	@Transactional(readOnly = true)
	public QuotePackageDTO getPackageDetail(Integer packageId) {
		QuotePackageDTO dto = this.dozer.map(
				this.quotePackageDao.getById(packageId), QuotePackageDTO.class);
		QuoteAddress shipToAddress = this.quoteAddressDao.getById(dto
				.getShipAddrId());
		QuoteAddress temp = new QuoteAddress();
		if (shipToAddress != null) {
			temp.setFirstName(shipToAddress.getFirstName());
			temp.setMidName(shipToAddress.getMidName());
			temp.setLastName(shipToAddress.getLastName());
			temp.setCountry(shipToAddress.getCountry());
			temp.setState(shipToAddress.getState());

			List<String> tmpList = new ArrayList<String>();
			tmpList.add(shipToAddress.getFirstName() + " "
					+ shipToAddress.getLastName());
			tmpList.add(shipToAddress.getOrgName());
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
					cityStr = cityStr + ", " + shipToAddress.getState() + " "
							+ shipToAddress.getZipCode();
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
			dto.setShiptoAddress(StringUtils.join(tmpList.toArray(), "\n"));
		}
		dto.setShipToAddr(temp);
		dto.setShipVia(this.shipMethodDao.getById(dto.getShipMethod())
				.getName());
		dto.setTotalQty(this.quotePackageDao.getTotalQty(dto.getPackageId())
				.intValue());
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
		List<QuotePackageItem> packageItemList = quotePackageItemDao
				.findPackageItemsByPkgId(packageId);
		if (packageItemList != null && !packageItemList.isEmpty()) {
			List<QuotePackageItemDTO> packageItemDTOList = new ArrayList<QuotePackageItemDTO>();
			for (QuotePackageItem packageItem : packageItemList) {
				QuotePackageItemDTO packageItemDto = dozer.map(packageItem,
						QuotePackageItemDTO.class);
				packageItemDto.setPkgItemId(null);
				packageItemDto.setPackageId(null);
				packageItemDTOList.add(packageItemDto);
			}
			dto.setPackageItemList(packageItemDTOList);
		}
		return dto;
	}

	public List<QuoteMain> getAllQuoteList() {
		return quoteMainDao.find("from QuoteMain");
	}

	@Transactional(readOnly = true)
	public Page<QuoteMainBean> searchQuote(final Page<QuoteMainBean> page,
			final List<PropertyFilter> filters) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userName = session.get(StrutsActionContant.USER_NAME);
		List<Criterion> criterionList = new ArrayList<Criterion>();
		// 判断当前用户是否含有销售经理角色
		boolean salesManager = userRoleDao
				.checkIsContainsManagerRole(Constants.ROLE_SALES_MANAGER);
		if (Constants.USERNAME_ADMIN.equals(userName) || salesManager) {
			return quoteMainDao.getQuotesByFilter(page, criterionList, filters);
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
			return quoteMainDao.getQuotesByFilter(page, criterionList, filters);
		}
		return page;
	}
	


	@Transactional(readOnly = true)
	public Page<QuoteMainBean> searchQuote(final Page<QuoteMainBean> page,
			final Map<String, String> filterParamMap) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return quoteMainDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<QuoteMainBean> searchQuote(final Page<QuoteMainBean> page) {
		return quoteMainDao.findPage(page);
	}

	@Transactional(readOnly = true)
	public Page<CustomerPriceBean> searchProductItemPrice(
			final Page<CustomerPriceBean> page,
			final Map<String, String> filterParamMap,
			final List<String> catalogNoList) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return customerPriceBeanDao.findPage(page, filterList, catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<CustomerPriceBean> searchProductItemPrice(
			final Page<CustomerPriceBean> page, final Integer custNo,
			final List<String> catalogNoList) {
		return customerPriceBeanDao.searchProductItemPrice(page, custNo,
				catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<CustomerPriceBean> searchProductItemPrice(
			final Page<CustomerPriceBean> page, final Integer custNo) {
		return customerPriceBeanDao.searchProductItemPrice(page, custNo,
				StatusType.ACTIVE.value());
	}

	@Transactional(readOnly = true)
	public Page<OrderTemplateItemBean> searchOrderTemplateItemPrice(
			final Page<OrderTemplateItemBean> page,
			final Map<String, String> filterParamMap,
			final List<String> catalogNoList) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return orderTemplateItemBeanDao.findPage(page, filterList,
				catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<ServiceOrderTemplateBean> searchServiceOrderTemplateItem(
			final Page<ServiceOrderTemplateBean> page,
			final Map<String, String> filterParamMap, PropertyFilter myFilter) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		if (myFilter != null) {
			filterList.add(myFilter);
		}
		return serviceOrderTemplateBeanDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<QuoteTemplateItemBean> searchQuoteTemplateItemPrice(
			final Page<QuoteTemplateItemBean> page,
			final Map<String, String> filterParamMap,
			final List<String> catalogNoList) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return quoteTemplateItemBeanDao.findPage(page, filterList,
				catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<ServiceQuoteTemplateBean> searchServiceQuoteTemplateItem(
			final Page<ServiceQuoteTemplateBean> page,
			final Map<String, String> filterParamMap, PropertyFilter myFilter) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		if (myFilter != null) {
			filterList.add(myFilter);
		}
		return serviceQuoteTemplateBeanDao.findPage(page, filterList);
	}

	@Transactional(readOnly = true)
	public Page<OrderItemsPriceBean> searchOrderItemPrice(
			final Page<OrderItemsPriceBean> page,
			final Map<String, String> filterParamMap,
			final List<String> catalogNoList) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return orderItemsPriceBeanDao.findPage(page, filterList, catalogNoList);
	}

	@Transactional(readOnly = true)
	public Page<QuoteItemsPriceBean> searchQuoteItemPrice(
			final Page<QuoteItemsPriceBean> page,
			final Map<String, String> filterParamMap,
			final List<String> catalogNoList) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return quoteItemsPriceBeanDao.findPage(page, filterList, catalogNoList);
	}

	@Transactional(readOnly = true)
	public ProductItemPriceDTO calculateQuoteRelationItemPrice(
			Integer mainProductId, Integer productId, Integer quoteNo,
			String catalogNo, Integer custNo, Integer quantity,
			BigDecimal amount, Date quoteDate) {
		return relationProductPriceBeanDao.calculateQuoteRelationItemPrice(
				mainProductId, productId, quoteNo, catalogNo, custNo, quantity,
				amount, quoteDate);
	}

	public void delQuote(Integer quoteNo, String status, String statusReason, Integer userId,
			String comment) {
		quoteDao.delQuote(quoteNo, status, statusReason, userId, comment);
	}

	@Transactional(readOnly = true)
	public List<ProcessLogDTO> getItemStatusHist(Integer quoteItemId) {
		return quoteProcessLogDao.getItemStatusHist(quoteItemId);
	}

	@Transactional(readOnly = true)
	public List<ProcessLogDTO> getQuoteStatusHist(Integer quoteNo) {
		List<ProcessLogDTO> processLogList = quoteProcessLogDao
				.getQuoteStatusHist(quoteNo);
		for (ProcessLogDTO processLogDTO : processLogList) {
			StatusList statusList = this.statusListDao.findByTypeAndCode("Q",
					processLogDTO.getPriorStat());
			processLogDTO.setPriorStatName(statusList != null ? statusList
					.getName() : null);
			statusList = this.statusListDao.findByTypeAndCode("Q",
					processLogDTO.getCurrentStat());
			processLogDTO.setCurrentStatName(statusList != null ? statusList
					.getName() : null);

		}
		return processLogList;
	}

	/**
	 * 保存QuoteItem同时保存其相关的Service.
	 * 
	 * @param itemDTO
	 * @param item
	 * @param userId
	 */
	private void attachServiceOfItem(QuoteItemDTO itemDTO, QuoteItem item,
			Integer userId, Integer custNo, List<QuoteItemDTO> dtoList) {
		if (!CatalogType.SERVICE.value().equals(itemDTO.getType())) {
			return;
		}
		Date now = new Date();
		if (itemDTO.getClsId().intValue() == 9 && itemDTO.getCustCloning() != null) {
			QuoteCustCloning custCloning = this.dozer.map(itemDTO.getCustCloning(), QuoteCustCloning.class);
			custCloning.setQuoteNo(item.getQuoteNo());
			custCloning.setQuoteItemId(item.getQuoteItemId());
			custCloning.setCreatedBy(userId);
			custCloning.setModifiedBy(userId);
			custCloning.setCreationDate(now);
			custCloning.setModifyDate(now);
			this.quoteCustCloningDao.save(custCloning);
			List<Document> documentList = itemDTO.getCustCloning().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(), DocumentType.QIM_CUSTCLONING, 
					itemDTO.getCustCloning().getDelDocIdList(), userId);
		}
		if (itemDTO.getClsId().intValue() == 3 && itemDTO.getGeneSynthesis() != null) {
			OrderGeneSynthesisDTO geneSynthesisDTO = itemDTO.getGeneSynthesis();
			if (StringUtils.isBlank(itemDTO.getParentId()) && (geneSynthesisDTO == null || StringUtils.isBlank(geneSynthesisDTO.getGeneName()))) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "GeneName"));
			}
			QuoteGeneSynthesis geneSynthesis = this.dozer.map(geneSynthesisDTO, QuoteGeneSynthesis.class);
			geneSynthesis.setQuoteNo(item.getQuoteNo());
			geneSynthesis.setQuoteItemId(item.getQuoteItemId());
			geneSynthesis.setCreatedBy(userId);
			geneSynthesis.setModifiedBy(userId);
			geneSynthesis.setCreationDate(now);
			geneSynthesis.setModifyDate(now);
			this.quoteGeneSynthesisDao.save(geneSynthesis);
			try {
				if (SequenceType.DNA.value().equalsIgnoreCase(geneSynthesis.getSequenceType())
						|| SequenceType.Protein.value().equalsIgnoreCase(geneSynthesis.getSequenceType())) {
					QuoteMain qm = quoteDao.getById(geneSynthesis.getQuoteNo());
					if (qm != null && qm.getCustNo() != null) {
						bioInfoService.calculateGeneDangerous(geneSynthesis.getQuoteItemId().toString(), 
								qm.getCustNo().toString(), "quote");
					}
				}
			} catch (Exception ex) {
				try {
					throw ex;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<Document> documentList = itemDTO.getGeneSynthesis().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(),
					DocumentType.QIM_GENE, itemDTO.getGeneSynthesis().getDelDocIdList(), userId);
		}
		if (itemDTO.getRna() != null) {
			QuoteRna rna = this.dozer.map(itemDTO.getRna(), QuoteRna.class);
			rna.setQuoteNo(item.getQuoteNo());
			rna.setQuoteItemId(item.getQuoteItemId());
			rna.setCreatedBy(userId);
			rna.setModifiedBy(userId);
			rna.setCreationDate(now);
			rna.setModifyDate(now);
			this.quoteRnaDao.save(rna);
			List<Document> documentList = itemDTO.getRna().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(), DocumentType.QIM_RNA, 
					itemDTO.getRna().getDelDocIdList(), userId);
		}
		if (itemDTO.getPlasmidPreparation() != null) {
			QuotePlasmidPreparation plasmid = this.dozer.map(itemDTO.getPlasmidPreparation(), QuotePlasmidPreparation.class);
			plasmid.setQuoteNo(item.getQuoteNo());
			plasmid.setQuoteItemId(item.getQuoteItemId());
			plasmid.setCreatedBy(userId);
			plasmid.setModifiedBy(userId);
			plasmid.setCreationDate(now);
			plasmid.setModifyDate(now);
			if (item.getParentId() != null) {
				plasmid.setAntibioticResistance(null);
				plasmid.setCopyNumber("High");
			}
			this.quotePlasmidPreparationDao.save(plasmid);
			List<Document> documentList = itemDTO.getPlasmidPreparation().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(), DocumentType.QIM_PLASMID, 
					itemDTO.getPlasmidPreparation().getDelDocIdList(), userId);
		}
		if (itemDTO.getClsId().intValue() == 6 && itemDTO.getOrfClone() != null) {
			QuoteOrfClone quoteOrfClone = this.dozer.map(itemDTO.getOrfClone(), QuoteOrfClone.class);
			if (StringUtils.isBlank(itemDTO.getParentId()) && (quoteOrfClone == null || StringUtils.isBlank(quoteOrfClone.getAccessionNo()))) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "AccessionNo"));
			}
			quoteOrfClone.setQuoteNo(item.getQuoteNo());
			quoteOrfClone.setQuoteItemId(item.getQuoteItemId());
			if (quoteOrfClone.getCreatedBy() == null) {
				quoteOrfClone.setCreatedBy(userId);
			}
			if (quoteOrfClone.getCreationDate() == null) {
				quoteOrfClone.setCreationDate(now);
			}
			quoteOrfClone.setModifiedBy(userId);
			quoteOrfClone.setModifyDate(now);
			quoteOrfCloneDao.save(quoteOrfClone);
			List<Document> documentList = itemDTO.getOrfClone().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(), DocumentType.QIM_ORFCLONE, 
					itemDTO.getOrfClone().getDelDocIdList(), userId);
		}
		if (itemDTO.getClsId().intValue() == 34 && itemDTO.getOligo() != null) {
			OrderOligoDTO orderOligoDTO = itemDTO.getOligo();
			if (StringUtils.isBlank(itemDTO.getParentId()) && (orderOligoDTO == null || StringUtils.isBlank(orderOligoDTO.getOligoName()))) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "OligoName"));
			}
			QuoteOligo quoteOligo = this.dozer.map(orderOligoDTO, QuoteOligo.class);
			quoteOligo.setQuoteNo(item.getQuoteNo());
			quoteOligo.setQuoteItemId(item.getQuoteItemId());
			quoteOligo.setCreatedBy(userId);
			quoteOligo.setModifiedBy(userId);
			quoteOligo.setCreationDate(now);
			quoteOligo.setModifyDate(now);
			quoteOligoDao.save(quoteOligo);
		}
		if (itemDTO.getClsId().intValue() == 4 && itemDTO.getMutagenesis() != null) {
			OrderMutagenesisDTO mutageneDTO = itemDTO.getMutagenesis();
			if (StringUtils.isBlank(itemDTO.getParentId()) && (mutageneDTO == null || StringUtils.isBlank(mutageneDTO.getVariantName()))) {
				throw new BussinessException("SE0203", getErrorMessage(
						itemDTO.getItemNo(), "Mutagenesis Target Insert Name"));
			}
			QuoteMutagenesis mutagene = this.dozer.map(mutageneDTO, QuoteMutagenesis.class);
			mutagene.setQuoteNo(item.getQuoteNo());
			mutagene.setQuoteItemId(item.getQuoteItemId());
			mutagene.setCreatedBy(userId);
			mutagene.setModifiedBy(userId);
			mutagene.setCreationDate(now);
			mutagene.setModifyDate(now);
			this.quoteMutagenesisDao.save(mutagene);
			List<Document> documentList = itemDTO.getMutagenesis().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(), DocumentType.QIM_MUTA, 
					itemDTO.getMutagenesis().getDelDocIdList(), userId);
		}
		if (itemDTO.getAntibody() != null) {
			QuoteAntibody antibody = this.dozer.map(itemDTO.getAntibody(), QuoteAntibody.class);
			antibody.setQuoteNo(item.getQuoteNo());
			antibody.setQuoteItemId(item.getQuoteItemId());
			antibody.setCreatedBy(userId);
			antibody.setModifiedBy(userId);
			antibody.setCreationDate(now);
			antibody.setModifyDate(now);
			this.quoteAntibodyDao.save(antibody);
		}
		if (itemDTO.getPeptide() != null) {
			PeptideDTO peptideDTO = itemDTO.getPeptide();
			if (StringUtils.isBlank(itemDTO.getParentId()) && (peptideDTO == null || StringUtils.isBlank(peptideDTO.getName()))) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "Peptide Name"));
			}
			QuotePeptide peptide = this.dozer.map(peptideDTO, QuotePeptide.class);
			peptide.setQuoteNo(item.getQuoteNo());
			peptide.setQuoteItemId(item.getQuoteItemId());
			peptide.setCreatedBy(userId);
			peptide.setModifiedBy(userId);
			peptide.setCreationDate(now);
			peptide.setModifyDate(now);
			this.quotePeptideDao.save(peptide);
			List<Document> documentList = itemDTO.getPeptide().getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(), DocumentType.QIM_PEPTIED, 
					itemDTO.getPeptide().getDelDocIdList(), userId);
		}
		if (itemDTO.getQuotePkgService() != null) {
			QuotePkgService pkgService = this.dozer.map(itemDTO.getQuotePkgService(), QuotePkgService.class);
			pkgService.setQuoteNo(item.getQuoteNo());
			pkgService.setQuoteItemId(item.getQuoteItemId());
			pkgService.setCreatedBy(userId);
			pkgService.setModifiedBy(userId);
			pkgService.setCreationDate(now);
			pkgService.setModifyDate(now);
			this.quotePkgServiceDao.save(pkgService);
		}
		if (itemDTO.getDnaSequencing() != null) {
			QuoteDnaSequencing dnaSeq = dozer.map(itemDTO.getDnaSequencing(), QuoteDnaSequencing.class);
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(dnaSeq.getSampleName())) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "DNA Sequencing Sample Name"));
			}
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(dnaSeq.getCode())) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "DNA Sequencing code"));
			}
			dnaSeq.setQuoteNo(item.getQuoteNo());
			dnaSeq.setQuoteItemId(item.getQuoteItemId());
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
				if (!Constants.DS_SEQ_CODE_TUBE.equalsIgnoreCase(dnaSeq.getCode())) {
					QuoteDsSeq srcDsSeq = quoteDsSeqDao.getByTypeAndLastSeq(Constants.DS_SEQ_TYPE_TUBE,
								Integer.parseInt(dnaSeq.getCode().replace("T", "")));
					if (srcDsSeq != null) {
						inDB = true;
					}
				}
				if (!inDB) {
					lastSeq = quoteDsSeqDao.getDsSeqLast(Constants.DS_SEQ_TYPE_TUBE);
					dnaSeq.setCode("T" + String.format("%07d", (lastSeq + 1)));
				}
				dnaSeq.setPlateId(0);
			} else if (dnaSeq.getCode().startsWith("P")) {
				QuoteDsPlates quoteDsPlates = null;
				seqType = Constants.DS_SEQ_TYPE_PLATE;
				if (!Constants.DS_SEQ_CODE_PLATE.equalsIgnoreCase(dnaSeq.getCode())) {
					QuoteDsSeq srcDsSeq = quoteDsSeqDao.getByTypeAndLastSeq(Constants.DS_SEQ_TYPE_PLATE,
								Integer.parseInt(dnaSeq.getCode().replace("P", "")));
					if (srcDsSeq != null) {
						if (dnaSeq.getPlateId() == null) {
							quoteDsSeqDao.delete(srcDsSeq);
						} else {
							quoteDsPlates = quoteDsPlatesDao.getById(dnaSeq.getPlateId());
							if (quoteDsPlates != null) {
								inDB = true;
							}
						}
					}
				}
				if (!inDB) {
					lastSeq = quoteDsSeqDao.getDsSeqLast(Constants.DS_SEQ_TYPE_PLATE);
					dnaSeq.setCode("P" + String.format("%06d", (lastSeq + 1)));
					// 保存order_ds_plates信息
					quoteDsPlates = new QuoteDsPlates();
					quoteDsPlates.setQuoteNo(item.getQuoteNo());
					quoteDsPlates.setCustNo(custNo);
					quoteDsPlates.setCreatedBy(userId);
					quoteDsPlates.setCreationDate(now);
				}
				if (quoteDsPlates != null) {
					quoteDsPlates.setName(itemDTO.getDnaSequencing().getpName());
					quoteDsPlates.setNums(itemDTO.getDnaSequencing().getPlateNums());
					quoteDsPlates.setLayout(itemDTO.getDnaSequencing().getPlateLayout());
					quoteDsPlates.setCode(dnaSeq.getCode());
					quoteDsPlates.setModifiedBy(userId);
					quoteDsPlates.setModifyDate(now);
					quoteDsPlatesDao.getSession().evict(quoteDsPlates);
					quoteDsPlatesDao.save(quoteDsPlates);
					dnaSeq.setPlateId(quoteDsPlates.getId());
				}
			}
			if (Constants.DS_SEQ_PRIMERTYPE_ENCLOSED.equalsIgnoreCase(dnaSeq.getPrimerType())) {
				// OrderDnaSequencing
				// PrimerType="Enclosed"则保存相关信息到orderDsLabels、orderDsSeq表
				saveDsLabels(dnaSeq, item.getQuoteNo(), custNo, userId, now);
			}
			// 保存lastSeq信息
			if (!inDB) {
				QuoteDsSeq quoteDsSeq = new QuoteDsSeq();
				quoteDsSeq.setType(seqType);
				quoteDsSeq.setLastSeq(lastSeq + 1);
				quoteDsSeqDao.save(quoteDsSeq);
			}
			quoteDnaSequencingDao.save(dnaSeq);
			if (dnaSeq.getCode().startsWith("P")) {
				updateDsSeqPlateId(itemDTO.getDnaSequencing().getSessPlateId(), dnaSeq.getPlateId(), dnaSeq.getCode(), dtoList);
			}
		}
		if (itemDTO.getCustomService() != null) {
			com.genscript.gsscm.quote.entity.QuoteService customService = dozer.map(itemDTO.getCustomService(),
							com.genscript.gsscm.quote.entity.QuoteService.class);
			if (StringUtils.isBlank(itemDTO.getParentId()) && StringUtils.isBlank(customService.getCustomDesc())) {
				throw new BussinessException("SE0203", getErrorMessage(itemDTO.getItemNo(), "Custom Service Description"));
			}
			customService.setQuoteNo(item.getQuoteNo());
			customService.setQuoteItemId(item.getQuoteItemId());
			customService.setCreatedBy(userId);
			customService.setModifiedBy(userId);
			customService.setCreationDate(now);
			customService.setModifyDate(now);
			quoteServiceDao.save(customService);
		}
		if (itemDTO.getMutationLibraries() != null) {
			QuoteMutationLibraries mutaLib = dozer.map(
					itemDTO.getMutationLibraries(),
					QuoteMutationLibraries.class);
			mutaLib.setQuoteNo(item.getQuoteNo());
			mutaLib.setQuoteItemId(item.getQuoteItemId());
			if (mutaLib.getCreatedBy() == null) {
				mutaLib.setCreatedBy(userId);
			}
			if (mutaLib.getCreationDate() == null) {
				mutaLib.setCreationDate(now);
			}
			mutaLib.setModifiedBy(userId);
			mutaLib.setModifyDate(now);
			quoteMutationLibrariesDao.save(mutaLib);
			List<Document> documentList = itemDTO.getMutationLibraries()
					.getDocumentList();
			this.saveServiceDocument(documentList, item.getQuoteItemId(),
					DocumentType.QIM_MUTALIB, itemDTO.getMutationLibraries()
							.getDelDocIdList(), userId);
		}
	}

	/**
	 * 更新集合中的PlateDnaSequencing
	 * 
	 * @author Zhang Yong add date 2011-11-14
	 * @param sessPlateId
	 * @param plateId
	 * @param code
	 * @param dtoList
	 */
	private void updateDsSeqPlateId(String sessPlateId, Integer plateId,
			String code, List<QuoteItemDTO> dtoList) {
		for (QuoteItemDTO dto : dtoList) {
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
	 * QuoteDnaSequencing PrimerType="Enclosed"则保存相关信息到quoteDsLabels、quoteDsSeq表
	 * 
	 * @author Zhang Yong add date 2011-11-14
	 * @param dnaSeq
	 * @param quoteNo
	 * @param custNo
	 * @param userId
	 * @param now
	 */
	private void saveDsLabels(QuoteDnaSequencing dnaSeq, Integer quoteNo,
			Integer custNo, Integer userId, Date now) {
		// 保存到OrderDsSeq
		Integer lastSeq = quoteDsSeqDao
				.getDsSeqLast(Constants.DS_SEQ_TYPE_PRIMER);
		// 保存到OrderDsLabels
		QuoteDsLabels quoteDsLabels = new QuoteDsLabels();
		quoteDsLabels.setName(dnaSeq.getPrimerName());
		quoteDsLabels.setCode("LAB" + String.format("%04d", (lastSeq + 1)));
		quoteDsLabels.setQuoteNo(quoteNo);
		quoteDsLabels.setCustNo(custNo);
		quoteDsLabels.setStatus(StatusType.ACTIVE.value());
		quoteDsLabels.setCreatedBy(userId);
		quoteDsLabels.setCreationDate(now);
		quoteDsLabels.setModifiedBy(userId);
		quoteDsLabels.setModifyDate(now);
		quoteDsLabelsDao.save(quoteDsLabels);

		QuoteDsSeq quoteDsSeq = new QuoteDsSeq();
		quoteDsSeq.setType(Constants.DS_SEQ_TYPE_PRIMER);
		quoteDsSeq.setLastSeq(lastSeq + 1);
		quoteDsSeqDao.save(quoteDsSeq);
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
	 * 获取ShippingRoute
	 * 
	 * @author Zhang Yong
	 * @param quote
	 * @param dtoList
	 * @return
	 */
	private String getShippingRoute(QuoteMain quote, List<QuoteItemDTO> dtoList, String dtoType) {
		String shippingRoute = CountryCode.US.value();
		QuoteAddress shipToAddr = this.quoteAddressDao.getById(quote
				.getShiptoAddrId());
		String country = shipToAddr.getCountry();
		String orgName = shipToAddr.getOrgName();
		Integer custNo = quote.getCustNo();
		boolean containGenePeptide = false;
		boolean isOnlyGenePeptide = true;
		boolean containProduct = false;
		Iterator<QuoteItemDTO> item = dtoList.iterator();
		while (item.hasNext()) {
			QuoteItemDTO itemDTO = item.next();
			if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(
					itemDTO.getType())) {
				containProduct = true;
			}
			if (itemDTO.getClsId() == 1 || itemDTO.getClsId() == 3 || itemDTO.getClsId() == 30 || itemDTO.getClsId() == 31 || itemDTO.getClsId() == 7 || itemDTO.getClsId() == 8) {
				containGenePeptide = true;
			} else {
				isOnlyGenePeptide = false;
			}
		}
		if ((custNo == 410 || custNo == 70374) && "SERVICE".equalsIgnoreCase(dtoType)) {
			shippingRoute = CountryCode.CN.value();
		}
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
	 * 保存Quote的同时保存Item, 并保存该Item关联的Address.
	 * 
	 * @param quote
	 * @param dtoList
	 */
	private void attachItems(final QuoteMain quote,
			final List<QuoteItemDTO> dtoList) {
		if (dtoList == null || dtoList.isEmpty()) {
			return;
		}
		String shippingRoute = null;
		// 先更新所有的itemNo.
		// this.quoteItemDao.updateQuoteItemNo(quote.getQuoteNo(), 10000);
		// this.quoteItemDao.getSession().flush();
		for (QuoteItemDTO dto : dtoList) {
				shippingRoute = getShippingRoute(quote, dtoList, dto.getType());
				dto.setShippingRoute(shippingRoute);
				if (dto.getSubItemList() != null && !dto.getSubItemList().isEmpty()) {
					for (QuoteItemDTO subDTO : dto.getSubItemList()) {
						shippingRoute = getShippingRoute(quote, dtoList, subDTO.getType());
						subDTO.setShippingRoute(shippingRoute);
					}
				}
//			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(dto.getType())) {
//				QuoteAddress shipAddr = dto.getShipToAddress();
//				if (shipAddr != null && "CN".equals(shipAddr.getCountry())
//						&& "jiangsu".equalsIgnoreCase(shipAddr.getState())
//						&& "nanjing".equalsIgnoreCase(shipAddr.getCity())) {
//					dto.setShippingRoute(CountryCode.CN.value());
//				}
//			}
			this.saveAttachItems(quote, dto, dtoList);
		}
		/*
		 * this.orderItemDao.getSession().flush();//先提交至数据库 //后处理新增 for
		 * (OrderItemDTO dto : dtoList) { if(dto.getOrderItemId() == null){
		 * this.saveAttachItems(order, dto); } }
		 */
	}

	private void saveAttachItems(final QuoteMain quote, QuoteItemDTO dto,
			final List<QuoteItemDTO> dtoList) {
		Date now = new Date();
		Integer loginUserId = quote.getModifiedBy();
		if (!StringUtils.isNumeric(dto.getParentId())) {
			dto.setParentId(null);
		}
		QuoteItem item = this.dozer.map(dto, QuoteItem.class);
		Integer originItemId = item.getQuoteItemId();
		item.setCreatedBy(loginUserId);
		item.setCreationDate(now);
		item.setModifyDate(now);
		item.setModifiedBy(loginUserId);
		item.setQuoteNo(quote.getQuoteNo());
		// 处理Integer值为0的bug.
		item.setClsId(SoapUtil.getIntegerFromSOAP(item.getClsId()));
		String status = item.getStatus();
		// 记录更新ShipSchedule log
		UpdateRequestLog updateRequestLog = new UpdateRequestLog();
		updateRequestLog.setObjectEntity("QuoteItem");
		updateRequestLog.setField("ScheduledDelivery");
		if (SoapUtil.getIntegerFromSOAP(originItemId) == null) {// 新增时.
			updateRequestLog
					.setOriginalValue(item.getShipSchedule() == null ? null
							: item.getShipSchedule().toString());
		} else {// 修改时查找数据库, 看status有无发生变化.
			QuoteItem dbItem = this.quoteItemDao.findUniqueBy("quoteItemId",
					item.getQuoteItemId());
			String dbStatus = dbItem.getStatus();
			if (!status.equals(dbStatus)) {
				QuoteProcessLog log = new QuoteProcessLog();
				log.setQuoteNo(quote.getQuoteNo());
				log.setQuoteItemId(item.getQuoteItemId());
				log.setPriorStat(dbItem.getStatus());
				log.setCurrentStat(item.getStatus());
				log.setReason(item.getStatusReason());
				log.setNote(dto.getStatusNote());
				log.setProcessDate(now);
				User processUser = new User();
				processUser.setUserId(quote.getModifiedBy());
				log.setProcessedBy(processUser);
				this.quoteProcessLogDao.save(log);
			}
			this.quoteItemDao.getSession().evict(dbItem);
			if (dbItem.getBasePrice() != null
					&& dbItem.getBasePrice().doubleValue() != 0.0) {
				item.setBasePrice(dbItem.getBasePrice());
			}
			updateRequestLog
					.setOriginalValue(dbItem.getShipSchedule() == null ? null
							: dbItem.getShipSchedule().toString());
		}
		this.quoteItemDao.save(item);// 先保存item.
		if (dto.getShipSchedule() != null
				&& StringUtils.isNotBlank(dto.getReason())) {
			updateRequestLog.setObjectId(item.getQuoteItemId());
			updateRequestLog.setNewValue(dto.getShipSchedule() == null ? null
					: dto.getShipSchedule().toString());
			updateRequestLog.setReason(dto.getReason());
			updateRequestLog.setRequestDate(now);
			updateRequestLog.setRequestedBy(loginUserId);
			updateRequestLogDao.save(updateRequestLog);
		}
		if (SoapUtil.getIntegerFromSOAP(originItemId) == null) {// 新增时写Order_status_history.
			QuoteProcessLog log = new QuoteProcessLog();
			log.setQuoteNo(quote.getQuoteNo());
			log.setQuoteItemId(item.getQuoteItemId());
			log.setCurrentStat(item.getStatus());
			log.setReason(item.getStatusReason());
			log.setNote(dto.getStatusNote());
			log.setProcessDate(now);
			User processUser = new User();
			processUser.setUserId(quote.getModifiedBy());
			log.setProcessedBy(processUser);
			this.quoteProcessLogDao.save(log);
		}
		// 保存More Detail中的相关Service信息. add by zhanghuibin
		// if ("Y".equals(dto.getUpdateFlag())) {
		this.attachServiceOfItem(dto, item, quote.getModifiedBy(),
				quote.getCustNo(), dtoList);
		// }
		// 保存它的子级OrderItem是递归的.
		if (dto.getSubItemList() != null && !dto.getSubItemList().isEmpty()) {
			// 先处理更新，因为item no唯一性的原因
			for (QuoteItemDTO subDTO : dto.getSubItemList()) {
				subDTO.setParentId(item.getQuoteItemId().toString());
				subDTO.setShiptoAddrId(item.getShiptoAddrId());
				this.saveAttachItems(quote, subDTO, dtoList);
			}
		}
	}

	@Transactional(readOnly = true)
	public QuoteMainDTO getQuoteDetail(final Integer quoteNo) {
		QuoteMain quote = quoteDao.getById(quoteNo);
		QuoteMainDTO dto = this.dozer.map(quote, QuoteMainDTO.class);
		try {
			List<QuoteItem> dbItemList = quoteItemDao
					.getQuoteAllItemList(quoteNo);
			List<QuoteItemDTO> itemDTOList = this
					.getItemList(quote, dbItemList);
			// modify by zhanghuibin 优化，只取得第一个item的 ItemMoreDetail ,
			if (itemDTOList != null && itemDTOList.size() > 0) {
				QuoteItemDTO itemDTO = itemDTOList.get(0);
				this.getItemMoreDetail(itemDTOList, itemDTO,
						itemDTO.getQuoteItemId());
				/*
				 * for (QuoteItemDTO itemDTO : itemDTOList) {
				 * this.getItemMoreDetail(itemDTO, itemDTO.getQuoteItemId()); }
				 */
				dto.setItemList(itemDTOList);
			}
			Customer customer = customerDao.get(quote.getCustNo());
			dto.setCustAltNo(customer.getAltNo());
			dto.setCustPrefShipMethod(customer.getPrefShipMthd());
			// Get Promotion
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter quoteFilter = new PropertyFilter("EQI_quoteNo",
					quoteNo);
			filterList.add(quoteFilter);
			List<QuotePromotion> quotePromList = this.quotePromotionDao
					.find(filterList);
			if (quotePromList != null && !quotePromList.isEmpty()) {
				QuotePromotion quotePromotion = quotePromList.get(0);
				QuotePromotionDTO quotePromotionDTO = this.dozer.map(
						quotePromotion, QuotePromotionDTO.class);
				dto.setQuotePromotion(quotePromotionDTO);
				dto.setPromotionId(quotePromotion.getId());
			}

			// Get couponCode
			String couponIds = dto.getCouponId();
			String couponCodes = "";
			if (StringUtils.isNotBlank(couponIds)) {
				for (String couponId : couponIds.split(",")) {
					Coupon coupon = this.couponDao.getById(Integer
							.parseInt(couponId));
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
			if(quote!=null && !"".equals(quote))
			{
				custNo=quote.getCustNo(); 
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
			if (quote.getSalesContact() != null) {
				User user = userDao.getById(quote.getSalesContact());
				if (user != null) {
					dto.setSalesContactEmail(user.getEmail());
				}
				}
				/*User salesContact = this.userDao.getById(quote
						.getSalesContact());
				if (salesContact != null && salesContact.getEmployee() != null) {
					dto.setSalesContactUser(salesContact.getEmployee()
							.getEmployeeName());
				}*/
				
				
				
				
			
			/*if (quote.getTechSupport() != null) {
				User techSupport = this.userDao.getById(quote.getTechSupport());
				if (techSupport != null && techSupport.getEmployee() != null) {
					dto.setTechSupportUser(techSupport.getEmployee()
							.getEmployeeName());
				}
			}*/
			dto.setStatusText(this.getQuoteStatusText(quote.getStatus()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * 获得一个Quote的itemList.
	 * 
	 * @param quoteNo
	 * @return
	 */
	@Transactional(readOnly = true)
	protected List<QuoteItemDTO> getItemList(QuoteMain quote, List<QuoteItem> itemList) {
		List<QuoteItemDTO> dtoList = new ArrayList<QuoteItemDTO>();
		PbCurrency pbCurrency = this.currencyDao.getByCurrencyCode(quote.getQuoteCurrency());
		String currencySymbolText = null;
		Integer precision = null;
		if (pbCurrency != null) {
			currencySymbolText = pbCurrency.getSymbol();
			precision = pbCurrency.getPrecision();
		}
		if (itemList != null) {
			Map<String, String> catalogMap = new HashMap<String, String>();
			List<Object[]> catalogList = quoteItemDao.getCatalogByOrderNo(quote.getQuoteNo());
			for (Object[] catInfo : catalogList) {
				// key=catalogId, value=catalogId : catalogName
				catalogMap.put(catInfo[0].toString(), catInfo[1] == null ? "" : catInfo[1].toString());
			}
			Map<String, String> clsMap = new HashMap<String, String>();
			List<Integer> serviceClsIdList = new ArrayList<Integer>();
			List<Integer> productClsIdList = new ArrayList<Integer>();
			Iterator<QuoteItem> qiIter = itemList.iterator();
			while (qiIter.hasNext()) {
				QuoteItem qi = qiIter.next();
				if (clsMap.containsKey(qi.getType() + "-" + qi.getClsId())) {
					continue;
				}
				clsMap.put(qi.getType() + "-" + qi.getClsId(), null);
				if (qi.getClsId() != null && CatalogType.SERVICE.value().equalsIgnoreCase(qi.getType())) {
					serviceClsIdList.add(qi.getClsId());
				}
				if (qi.getClsId() != null && CatalogType.PRODUCT.value().equalsIgnoreCase(qi.getType())) {
					productClsIdList.add(qi.getClsId());
				}
			}
			if (!serviceClsIdList.isEmpty()) {
				List<ServiceClass> serviceClassList = serviceClassDao.findByIds(serviceClsIdList);
				for (ServiceClass sc : serviceClassList) {
					clsMap.put(CatalogType.SERVICE.value() + "-" + sc.getClsId(), CatalogType.SERVICE.value() + " - " + sc.getName());
				}
			}
			if (!productClsIdList.isEmpty()) {
				List<ProductClass> productClassList = productClassDao.findByIds(productClsIdList);
				for (ProductClass pc : productClassList) {
					clsMap.put(CatalogType.PRODUCT.value() + "-" + pc.getClsId(), CatalogType.PRODUCT.value() + " - " + pc.getName());
				}
			}
			Map<Integer, QuoteAddress> addrMap = new HashMap<Integer, QuoteAddress>();
			List<Integer> shipToAddrIds = new ArrayList<Integer>();
			// quote的ShiptoAddrFlag==3表示每一个Item都有自己的ShipToAddress，其他的值则Item都使用Quote的ShipToAddress
			if (SoapUtil.getIntegerFromSOAP(quote.getShiptoAddrFlag()) != null && quote.getShiptoAddrFlag().intValue() == 3) {
				Iterator<QuoteItem> iter = itemList.iterator();
				while (iter.hasNext()) {
					QuoteItem qi = iter.next();
					if (qi.getShiptoAddrId() != null && !addrMap.containsKey(qi.getShiptoAddrId())) {
						addrMap.put(qi.getShiptoAddrId(), null);
						shipToAddrIds.add(qi.getShiptoAddrId());
					}
				}
			} else {
				shipToAddrIds.add(quote.getShiptoAddrId());
			}
			// 根据所有的ShipToAddressId一次性查出来，优化查询
			if (!shipToAddrIds.isEmpty()) {
				List<QuoteAddress> addrList = quoteAddressDao.findByIds(shipToAddrIds);
				if (addrList != null && !addrList.isEmpty()) {
					for (QuoteAddress addr : addrList) {
						addrMap.put(addr.getAddrId(), addr);
					}
				}
			}
			for (int itemIndex = 0; itemIndex < itemList.size(); itemIndex++) {
				QuoteItem item = itemList.get(itemIndex);
				this.quoteItemDao.getSession().evict(item);
				QuoteItemDTO dto = this.dozer.map(item, QuoteItemDTO.class);
				dto.setCurrencyCode(quote.getQuoteCurrency());
				dto = this.dozer.map(dto, QuoteItemDTO.class);
				if (SoapUtil.getIntegerFromSOAP(item.getShiptoAddrId()) != null && addrMap.containsKey(item.getShiptoAddrId())) {
					dto.setShipToAddress(addrMap.get(item.getShiptoAddrId()));
				}
				// 设置页面中显示用的otherInfo.
				if (itemIndex == 0) {
					dto.setOtherInfo(getItemOtherInfo(item.getType(), item.getStatus(), item.getCatalogNo(), item.getStatusReason(), null, quote.getCustNo()));
				} else {
					dto.setOtherInfo("");
				}
				// 设置页面显示用的catalog
				if (StringUtils.isNotBlank(dto.getCatalogId())
						&& catalogMap.containsKey(dto.getCatalogId())) {
					dto.setCatalogText(catalogMap.get(dto.getCatalogId()));
				}
				// 设置页面中显示用的type
				if (item.getType().equals(QuoteItemType.PRODUCT.value())) {
					if (clsMap.containsKey(CatalogType.PRODUCT.value() + "-"
							+ item.getClsId())) {
						dto.setTypeText(clsMap.get(CatalogType.PRODUCT.value()
								+ "-" + item.getClsId()));
					}
				} else {
					if (clsMap.containsKey(CatalogType.SERVICE.value() + "-"
							+ item.getClsId())) {
						dto.setTypeText(clsMap.get(CatalogType.SERVICE.value()
								+ "-" + item.getClsId()));
					}
				}
				// 设置页面中显示用的status
				dto.setStatusText(this.getQuoteItemStatusText(item.getStatus()));
				dto.setUpSymbol(currencySymbolText);
				dto.setUpPrecision(precision);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 获得某个Product在某个Storage中的Other info.
	 */
	@Transactional(readOnly = true)
	public String getItemOtherInfo(String itemType, String status, String catalogNo, String statusReason, String customerCompany, Integer custNo) {
		String ret = "";
		if (QuoteItemType.PRODUCT.value().equals(itemType)) {
			if (OrderItemStatusType.CN.value().equals(status)) {
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
	 * Update list 1. 2011-01-10 wangsf 计算产品或服务的库存改为从StockDetail中取
	 * 
	 */
	/**
	 * 获得某个产品在storage中的库存信息.
	 * 
	 * @param storageId
	 * @param warehouseId
	 * @param catalogNo
	 * @return
	 */
	public Integer getItemStockQty(String catalogNo, String customerCompany) {
		BigDecimal unitInStock = erpSalesOrderService.getPartStorageNumber(
				catalogNo, customerCompany);
		return unitInStock != null ? unitInStock.intValue() : null;
	}

	/**
	 * For getQuoteDetail
	 * 
	 * @param strStatus
	 * @return
	 */
	private String getQuoteStatusText(String strStatus) {
		String text = null;
		if (strStatus == null || strStatus.trim().length() < 1) {
			return null;
		}
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filterType = new PropertyFilter("EQS_type",
				OrderQuoteStautsType.QUOTE.value());
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
	 * 根据QuoteItem Status的缩写获得该QuoteItem status的全称.
	 * 
	 * @param strStatus
	 * @return
	 */
	private String getQuoteItemStatusText(String strStatus) {
		String text = null;
		if (strStatus == null || strStatus.trim().length() < 1) {
			return null;
		}
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filterType = new PropertyFilter("EQS_type",
				OrderQuoteStautsType.QUOTEITEM.value());
		filterList.add(filterType);
		PropertyFilter filterCode = new PropertyFilter("EQS_code", strStatus);
		filterList.add(filterCode);
		List<StatusList> statusList = this.statusListDao.find(filterList);
		if (statusList != null && !statusList.isEmpty()) {
			text = statusList.get(0).getName();
		}
		return text;
	}

	public QuoteAddressDTO getAddress(Integer quoteNo) {
		QuoteAddressDTO addr = new QuoteAddressDTO();
		QuoteMain quote = this.quoteDao.getById(quoteNo);
		if (quote.getBilltoAddrId() != null
				&& quote.getBilltoAddrId().intValue() > 0) {
			QuoteAddress billToAddr = this.quoteAddressDao.getById(quote
					.getBilltoAddrId());
			if (billToAddr != null) {
				// Organization billOrg = billToAddr.getOrganization();
				// Organization newBillOrg = new Organization();
				// newBillOrg.setOrgId(billOrg.getOrgId());
				// billToAddr.setOrganization(newBillOrg);
				addr.setBillToAddr(billToAddr);
			}
		}
		if (quote.getShiptoAddrId() != null
				&& quote.getShiptoAddrId().intValue() > 0) {
			QuoteAddress shipToAddr = this.quoteAddressDao.getById(quote
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
		if (quote.getSoldtoAddrId() != null
				&& quote.getSoldtoAddrId().intValue() > 0) {
			QuoteAddress soldToAddr = this.quoteAddressDao.getById(quote
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

	public List<QuoteAddress> getAddressListByType(Integer quoteNo,
			AddressType addrType) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_quoteNo", quoteNo);
		filterList.add(quoteFilter);
		PropertyFilter typefilter = new PropertyFilter("EQS_addrType",
				addrType.value());
		filterList.add(typefilter);
		List<QuoteAddress> addrList = this.quoteAddressDao.find(filterList);
		return addrList;
	}

	@SuppressWarnings("unchecked")
	public List<InstructionDTO> getInstructionList(Integer quoteNo,
			QuoteInstructionType type) {
		List<InstructionDTO> dtoList = new ArrayList<InstructionDTO>();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter quoteFilter = new PropertyFilter("EQI_quoteNo", quoteNo);
		filterList.add(quoteFilter);
		boolean bEmail = false;
		if (type == null || type.equals(QuoteInstructionType.FOLLOWUP_EMAIL)) {
			if (type == null) {
				bEmail = false;
			} else {
				bEmail = true;
			}
			if (type != null) {
				PropertyFilter typeFilter = new PropertyFilter("EQS_type", type);
				filterList.add(typeFilter);
			}
			List<QuoteMail> mailList = this.quoteMailDao.find(filterList);
			Date instDate = null;
			if (mailList != null) {
				for (QuoteMail mail : mailList) {
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
					List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
					PropertyFilter refIdFilter = new PropertyFilter(
							"EQI_refId", mail.getId());
					PropertyFilter refTypeFilter = new PropertyFilter(
							"EQS_refType", DocumentType.QUOTE_INST_MAIL.value());
					docFilterList.add(refIdFilter);
					docFilterList.add(refTypeFilter);
					List<Document> docList = this.documentDao
							.find(docFilterList);
					dto.setDocumentList(docList);
					dtoList.add(dto);
				}
			}
		}
		if (!bEmail) {
			if (type != null) {
				PropertyFilter typeFilter = new PropertyFilter("EQS_type",
						type.value());
				filterList.add(typeFilter);
			}
			List<QuoteNote> noteList = this.quoteNoteDao.find(filterList);
			if (noteList != null) {
				for (QuoteNote note : noteList) {
					InstructionDTO dto = dozer.map(note, InstructionDTO.class);
					dto.setInstructionDate(note.getCreationDate());
					User createUser = this.userDao.getById(note.getCreatedBy());
					if (createUser != null) {
						dto.setCreateUser(createUser.getLoginName());
					}
					List<PropertyFilter> docFilterList = new ArrayList<PropertyFilter>();
					PropertyFilter refIdFilter = new PropertyFilter(
							"EQI_refId", note.getId());
					PropertyFilter refTypeFilter = new PropertyFilter(
							"EQS_refType", DocumentType.QUOTE_INST_NOTE.value());
					docFilterList.add(refIdFilter);
					docFilterList.add(refTypeFilter);
					List<Document> docList = this.documentDao
							.find(docFilterList);
					dto.setDocumentList(docList);
					dtoList.add(dto);
				}
			}
		}
		Collections.sort(dtoList, new BeanComparator("instructionDate"));
		return dtoList;
	}

	/**
	 * 通过quoteNo查询对应Customer Note中数据
	 * 
	 * @author Zhang Yong
	 * @param sessQuoteNo
	 * @param type
	 * @return
	 */
	public List<InstructionDTO> getInstructionListInCustomer(Integer custNo,
			String sessQuoteNo, String type) {
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
			dbDTO.setCustNoteId(QuoteInstructionType.CUSTOMER.value() + "-"
					+ customerNote.getId());
			if (StringUtils.isNumeric(sessQuoteNo)) {
				dbDTO.setQuoteNo(Integer.parseInt(sessQuoteNo));
			}
			dbDTO.setType(customerNote.getType());
			dbDTO.setDescription(customerNote.getDescription());
			dbDTO.setDocFlag(customerNote.getDocFlag());
			dbDTO.setNoteDate(customerNote.getCreationDate());
			dbDTO.setInstructionDate(customerNote.getCreationDate());
			dbDTO.setSource(QuoteInstructionType.CUSTOMER.value());
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
	 * 保存Quote的同时保存QuoteMails and QuoteNotes.及其关联的多个document.
	 * 
	 * @param quote
	 * @param dtoList
	 * @return
	 */
	private List<Integer> attachInstruction(QuoteMain quote,
			List<InstructionDTO> dtoList, String dbAccountNo) {
		List<Integer> sendMailIdList = new ArrayList<Integer>();
		String strDateFormat = "yyyy-MM-dd";
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		// 非CN状态的item中有shipMethod name不是FedEx的，默认添加一条SHIPMENT类型的Note
		List<QuoteItem> notFexItemList = quoteItemDao.getNotFedExItem(
				quote.getQuoteNo(), "FedEx");
		if (notFexItemList != null && !notFexItemList.isEmpty()) {
			QuoteNote note = new QuoteNote();
			note.setNoteDate(now);
			note.setQuoteNo(quote.getQuoteNo());
			note.setCreatedBy(quote.getModifiedBy());
			note.setModifiedBy(quote.getModifiedBy());
			note.setCreationDate(now);
			note.setModifyDate(now);
			note.setDescription("Ship methods of some order items are not fedex. ");
			note.setType(QuoteInstructionType.SHIPMENT.value());
			this.quoteNoteDao.save(note);
		}
		if (dtoList == null || dtoList.isEmpty()) {
			String shipAccount = quote.getShippingAccount();
			if (StringUtils.isNotBlank(shipAccount)
					&& (StringUtils.isBlank(dbAccountNo) || !shipAccount.trim()
							.equals(dbAccountNo.trim()))) {
				QuoteNote note = new QuoteNote();
				note.setNoteDate(now);
				note.setQuoteNo(quote.getQuoteNo());
				note.setCreatedBy(quote.getModifiedBy());
				note.setModifiedBy(quote.getModifiedBy());
				note.setCreationDate(now);
				note.setModifyDate(now);
				note.setDescription("Account No:" + shipAccount.trim());
				note.setType(QuoteInstructionType.SHIPMENT.value());
				this.quoteNoteDao.save(note);
			}
			return sendMailIdList;
		}
		if (dtoList == null || dtoList.isEmpty()) {
			return sendMailIdList;
		}
		for (InstructionDTO dto : dtoList) {
			// QuoteInstructionType type = dto.getQuoteInstructionType();
			QuoteInstructionType type = QuoteInstructionType.fromValue(dto
					.getType());
			if (type == null) {
				continue;
			}
			if (dto.getDelDocIdList() != null
					&& !dto.getDelDocIdList().isEmpty()) {
				this.documentDao.delDocumentList(dto.getDelDocIdList());
			}

			if (type.equals(QuoteInstructionType.FOLLOWUP_EMAIL)) {
				QuoteMail mail = this.dozer.map(dto, QuoteMail.class);
				if (mail.getId() != null && mail.getId().intValue() < 1) {
					mail.setId(null);
				}
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					mail.setDocFlag("Y");
				} else {
					mail.setDocFlag("N");
				}
				mail.setQuoteNo(quote.getQuoteNo());
				mail.setCreatedBy(quote.getModifiedBy());
				mail.setModifiedBy(quote.getModifiedBy());
				mail.setCreationDate(now);
				mail.setModifyDate(now);

				this.quoteMailDao.save(mail);
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					for (Document doc : dto.getDocumentList()) {
						doc.setCreatedBy(quote.getModifiedBy());
						doc.setModifiedBy(quote.getModifiedBy());
						doc.setCreationDate(now);
						doc.setModifyDate(now);
						doc.setRefId(mail.getId());
						doc.setRefType(DocumentType.QUOTE_INST_MAIL.value());
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

				QuoteNote note = this.dozer.map(dto, QuoteNote.class);

				if (SoapUtil.getIntegerFromSOAP(note.getId()) == null) {
					note.setId(null);
					if (note.getNoteDate() == null) {
						note.setNoteDate(now);
					}
				}
				note.setQuoteNo(quote.getQuoteNo());
				note.setCreatedBy(quote.getModifiedBy());
				note.setModifiedBy(quote.getModifiedBy());
				note.setCreationDate(now);
				note.setModifyDate(now);
				this.quoteNoteDao.save(note);
				if (dto.getDocumentList() != null
						&& !dto.getDocumentList().isEmpty()) {
					note.setDocFlag("Y");
					for (Document doc : dto.getDocumentList()) {
						doc.setCreatedBy(quote.getModifiedBy());
						doc.setModifiedBy(quote.getModifiedBy());
						doc.setCreationDate(now);
						doc.setModifyDate(now);
						doc.setRefId(note.getId());
						doc.setRefType(DocumentType.QUOTE_INST_NOTE.value());

						this.documentDao.save(doc);
					}
				}
			}

		}
		return sendMailIdList;
	}

	/**
	 * Save or update the QuoteAddress, it can be the Quote's
	 * ship-to|bill-to|sold-to Address and also can be the QuoteItem's ship-to
	 * Address.
	 * 
	 * @param quote
	 * @param quoteAddr
	 * @return
	 */
	private QuoteAddress attachAddr(final QuoteMain quote,
			QuoteAddress quoteAddr) {
		if (quoteAddr.getAddrId() == null
				|| quoteAddr.getAddrId().intValue() == 0) {
			quoteAddr.setAddrId(null);
		}
		quoteAddr.setQuoteNo(quote.getQuoteNo());
		Date now = new Date();
		quoteAddr.setCreatedBy(quote.getModifiedBy());
		quoteAddr.setCreationDate(now);
		quoteAddr.setModifiedBy(quote.getModifiedBy());
		quoteAddr.setModifyDate(now);
		this.quoteAddressDao.save(quoteAddr);
		return quoteAddr;
	}

	public void saveOrUpdateQuote(QuoteMain quote, String statusNote) {
		Integer originQuoteNo = quote.getQuoteNo();
		Date now = new Date();
		quote.setCreatedBy(quote.getModifiedBy());
		quote.setCreationDate(now);
		quote.setModifyDate(now);
		if (quote.getExchRate() == null) {
			quote.setExchRate(1d);
		}
		if (SoapUtil.getIntegerFromSOAP(originQuoteNo) == null) {// 新增时写Quote_status_history.
			Customer cust = this.customerDao.getById(quote.getCustNo());
			// quote.setBaseCurrency(cust.getPaymentCurrency());
			quote.setBaseCurrency(CurrencyType.USD.value());
			quote.setQuoteCurrency(cust.getPaymentCurrency());
			this.customerDao.getSession().evict(cust);
			quote.setQuoteDate(new Date());
			quote.setExchRateDate(new Date());
		} else {
			String status = quote.getStatus();
			if (status != null && status.trim().length() > 0) {
				QuoteMain dbQuote = this.quoteDao.getById(quote.getQuoteNo());
				String dbStatus = dbQuote.getStatus();
				if (status.equals(dbStatus)) {

				} else {
					QuoteProcessLog log = new QuoteProcessLog();
					log.setQuoteNo(quote.getQuoteNo());
					log.setPriorStat(dbQuote.getStatus());
					log.setCurrentStat(quote.getStatus());
					log.setReason(quote.getStatusReason());
					log.setNote(statusNote);
					log.setProcessDate(now);
					User processUser = new User();
					processUser.setUserId(quote.getModifiedBy());
					log.setProcessedBy(processUser);
					this.quoteProcessLogDao.save(log);
				}
				this.quoteDao.getSession().evict(dbQuote);
				quote.setBaseCurrency(dbQuote.getBaseCurrency());
			}
		}
		this.quoteDao.save(quote);// 先保存Quote.
		if (SoapUtil.getIntegerFromSOAP(originQuoteNo) == null) {// 新增时写Quote_status_history.
			QuoteProcessLog log = new QuoteProcessLog();
			log.setQuoteNo(quote.getQuoteNo());
			log.setCurrentStat(quote.getStatus());
			log.setReason(quote.getStatusReason());
			log.setNote(statusNote);
			log.setProcessDate(now);
			User processUser = new User();
			processUser.setUserId(quote.getModifiedBy());
			log.setProcessedBy(processUser);
			this.quoteProcessLogDao.save(log);
		}
	}

	public void saveQuote(QuoteMain quote) {
		this.quoteDao.save(quote);
	}

	/**
	 * 保存Quote的同时保存Quote的shipTo, billTo, soldTo地址.
	 * 
	 * @param quote
	 * @param quoteAddrList
	 */
	@SuppressWarnings("unused")
	private void attachMainAddr(QuoteMain quote, QuoteAddressDTO quoteAddrList,
			Map<String, Object> map) {
		if (quoteAddrList != null) {
			QuoteAddress shipTo = quoteAddrList.getShipToAddr();
			if (shipTo != null) {
				if (shipTo.getAddrId() != null
						&& shipTo.getAddrId().intValue() < 0) {// 为负数.
					String key = "itemId" + shipTo.getAddrId().intValue();
					if (map.containsKey(key)) {// 这个负数值的address在前面已经添加过了.
						quote.setShiptoAddrId((Integer) map.get(key));
					} else {
						shipTo.setAddrId(null);
						shipTo = this.attachAddr(quote, shipTo);
						quote.setShiptoAddrId(shipTo.getAddrId());
						map.put(key, shipTo.getAddrId());
					}
				} else {
					QuoteAddress temp = this.attachAddr(quote, shipTo);
					quote.setShiptoAddrId(temp.getAddrId());
				}
			}
			QuoteAddress billTo = quoteAddrList.getBillToAddr();
			if (billTo != null) {
				if (billTo.getAddrId() != null
						&& billTo.getAddrId().intValue() < 0) {// 为负数.
					String key = "itemId" + billTo.getAddrId().intValue();
					if (map.containsKey(key)) {// 这个负数值的address在前面已经添加过了.
						quote.setBilltoAddrId((Integer) map.get(key));
					} else {
						billTo.setAddrId(null);
						billTo = this.attachAddr(quote, billTo);
						quote.setBilltoAddrId(billTo.getAddrId());
						map.put(key, billTo.getAddrId());
					}
				} else {
					QuoteAddress temp = this.attachAddr(quote, billTo);
					quote.setBilltoAddrId(temp.getAddrId());
				}
			}
			QuoteAddress soldTo = quoteAddrList.getSoldToAddr();
			if (soldTo != null) {
				if (soldTo.getAddrId() != null
						&& soldTo.getAddrId().intValue() < 0) {// 为负数.
					String key = "itemId" + soldTo.getAddrId().intValue();
					if (map.containsKey(key)) {// 这个负数值的address在前面已经添加过了.
						quote.setSoldtoAddrId((Integer) map.get(key));
					} else {
						soldTo.setAddrId(null);
						soldTo = this.attachAddr(quote, soldTo);
						quote.setSoldtoAddrId(soldTo.getAddrId());
						map.put(key, soldTo.getAddrId());
					}
				} else {
					QuoteAddress temp = this.attachAddr(quote, soldTo);
					quote.setSoldtoAddrId(temp.getAddrId());
				}
			}
		}
	}

	private void attachPromotion(QuoteMain quote, QuotePromotionDTO dto,
			Integer promotionId) {
		if (dto == null) {
			return;
		}
		if (dto.getPrmtCode() == null || dto.getPrmtCode().trim().length() < 1) {
			// Get Promotion
			List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
			PropertyFilter quoteFilter = new PropertyFilter("EQI_quoteNo",
					quote.getQuoteNo());
			filterList.add(quoteFilter);
			List<QuotePromotion> quotePromList = this.quotePromotionDao
					.find(filterList);
			if (quotePromList != null && !quotePromList.isEmpty()) {
				QuotePromotion quotePromotion = quotePromList.get(0);
				this.quotePromotionDao.delete(quotePromotion);
			}
		} else {
			QuotePromotion quotePromotion = this.dozer.map(dto,
					QuotePromotion.class);
			if (promotionId != null && promotionId.intValue() == 0) {
				quotePromotion.setId(null);
			} else {
				quotePromotion.setId(promotionId);

			}
			quotePromotion.setQuoteNo(quote.getQuoteNo());
			Date now = new Date();
			quotePromotion.setCreatedBy(quote.getModifiedBy());
			quotePromotion.setCreationDate(now);
			quotePromotion.setModifiedBy(quote.getModifiedBy());
			quotePromotion.setModifyDate(now);
			this.quotePromotionDao.save(quotePromotion);
		}
	}

	/**
	 * 保存Quote的同时保存新增或修改的PaymentPlan, 删除 delIdList中包含的PaymentPlan
	 * 
	 * @param quote
	 * @param delIdList
	 * @param planList
	 */
	@SuppressWarnings("unused")
	private void attachPaymentPlan(QuoteMain quote, List<Integer> delIdList,
			List<QuotePaymentPlan> planList) {
		if (delIdList != null && !delIdList.isEmpty()) {
			this.quotePaymentPlanDao.delPaymentPlanList(delIdList);
		}
		if (planList == null || planList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (QuotePaymentPlan plan : planList) {
			plan.setPlanId(SoapUtil.getIntegerFromSOAP(plan.getPlanId()));
			plan.setChkNo(SoapUtil.getIntegerFromSOAP(plan.getChkNo()));
			plan.setPoPaymentTerm(SoapUtil.getIntegerFromSOAP(plan
					.getPoPaymentTerm()));
			plan.setQuoteNo(quote.getQuoteNo());
			plan.setCreationDate(now);
			plan.setModifyDate(now);
			plan.setCreatedBy(quote.getModifiedBy());
			plan.setModifiedBy(quote.getModifiedBy());
			this.quotePaymentPlanDao.save(plan);
		}
	}

	/**
	 * 保存Quote的同时保存，修改和删除ShipPackage list.
	 * 
	 * @param quote
	 * @param quotePackageList
	 * @param delShipPackageIdList
	 */
	private void attachShipPackage(QuoteMain quote,
			List<QuotePackageDTO> quotePackageList,
			List<Integer> delQuotePackageIdList) {
		this.quotePackageItemDao.delItemListByQuoteNo(quote.getQuoteNo());
		this.quotePackageDao.delQuotePackageListByQuoteNo(quote.getQuoteNo());
		if (quotePackageList == null || quotePackageList.get(0) == null) {
			return;
		}
		Integer userId = SessionUtil.getUserId();
		Date now = new Date();
		for (QuotePackageDTO dto : quotePackageList) {
			QuotePackage shipPackage = this.dozer.map(dto, QuotePackage.class);
			shipPackage.setQuoteNo(quote.getQuoteNo());
			shipPackage.setCreatedBy(userId);
			shipPackage.setCreationDate(now);
			shipPackage.setModifyDate(now);
			shipPackage.setModifiedBy(quote.getModifiedBy());
			this.quotePackageDao.save(shipPackage);
			if (dto.getPackageItemList() != null) {
				for (QuotePackageItemDTO pkgItemDTO : dto.getPackageItemList()) {
					QuotePackageItem pckItem = this.dozer.map(pkgItemDTO,
							QuotePackageItem.class);
					pckItem.setPackageId(shipPackage.getPackageId());
					pckItem.setCreatedBy(userId);
					pckItem.setModifiedBy(userId);
					pckItem.setCreationDate(now);
					pckItem.setModifyDate(now);
					this.quotePackageItemDao.save(pckItem);
				}
			}
		}
	}

	/**
	 * 保存Quote及QuoteItem的Address. 对于Quote及QuoteItem的多个Address必须要返回， 否则会执行删除.
	 * Zhang Yong
	 * 
	 * @param quote
	 * @param addrMap
	 * @return
	 */
	private Map<String, QuoteAddress> saveAddrMap(QuoteMain quote,
			Map<String, QuoteAddress> addrMap) {
		List<QuoteAddress> dbAddrList = this.quoteAddressDao
				.getAddrByQuoteNo(quote.getQuoteNo());
		Map<String, QuoteAddress> sourceAddrMap = null;
		if (dbAddrList != null && !dbAddrList.isEmpty()) {
			sourceAddrMap = SessionUtil.convertList2Map(dbAddrList, "addrId");
		}
		Iterator<Entry<String, QuoteAddress>> iterator = addrMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, QuoteAddress> entry = iterator.next();
			String key = entry.getKey();
			QuoteAddress srcAddr = entry.getValue();
			if (srcAddr.getAddrId() != null) {
				if (sourceAddrMap != null
						&& sourceAddrMap.containsKey(srcAddr.getAddrId()
								.toString())) {
					sourceAddrMap.put(srcAddr.getAddrId().toString(), null);
				}
				for (QuoteAddress evictAddr : dbAddrList) {
					if (srcAddr.getAddrId().intValue() == evictAddr.getAddrId()
							.intValue()) {
						this.quoteAddressDao.getSession().evict(evictAddr);
						break;
					}
				}
			}
			srcAddr = this.attachAddr(quote, srcAddr);
			addrMap.put(key, srcAddr);
		}
		if (sourceAddrMap != null && !sourceAddrMap.isEmpty()) {
			for (String key : sourceAddrMap.keySet()) {
				if (key != null && sourceAddrMap.get(key) != null) {
					this.quoteAddressDao.delete(Integer.parseInt(key));
				}
			}
		}
		return addrMap;
	}

	/**
	 * 保存Quote基本信息及QuoteItems, QuoteMails, QuoteNotes, PaymentPlans, promotion.
	 * 
	 * @param quoteDTO
	 * @param userId
	 * @return 返回当天发邮件的mailId list.
	 */
	public QuoteMainDTO saveQuote(QuoteMainDTO quoteDTO, Integer userId) {
		String dbAccountNo = null;
		if (quoteDTO.getQuoteNo() != null) {
			dbAccountNo = quoteDao.getById(quoteDTO.getQuoteNo())
					.getShippingAccount();
		}
		QuoteMainDTO retDTO = new QuoteMainDTO();
		QuoteMain quote = this.dozer.map(quoteDTO, QuoteMain.class);
		quote.setModifiedBy(userId);
		if (quoteDTO.getQuoteNo() == null && quoteDTO.getItemList() != null
				&& !quoteDTO.getItemList().isEmpty()) {
			Integer firstServiceClsId = null;
			for (QuoteItemDTO quoDTO : quoteDTO.getItemList()) {
				if (QuoteItemType.SERVICE.value().equals(quoDTO.getType())
						&& quoDTO.getClsId() != null) {
					firstServiceClsId = quoDTO.getClsId();
					break;
				}
			}
			if (firstServiceClsId != null) {
				List<User> userList = salesProjectManagerAssignmentDao
						.findProManagerByClsId(firstServiceClsId);
				if (userList != null && !userList.isEmpty()) {
					quote.setProjectManager(userList.get(0).getUserId());
				}
			}
			if (quote.getProjectManager() == null
					|| quote.getProjectManager() == -1) {
				List<User> proUserList = salesRepDao
						.findUserByFunction(SalesRepSalesRole.PROJECT_SUPPORT
								.value());
				if (proUserList != null && !proUserList.isEmpty()) {
					quote.setAltProjectManager(proUserList.get(0).getUserId());
				}
			}
		}
		this.saveOrUpdateQuote(quote, quoteDTO.getStatusNote());
		// 1. 提取所有Address(Quote, QuoteItem及彼此重复的)
		Map<String, QuoteAddress> addrMap = new HashMap<String, QuoteAddress>();
		if (quoteDTO.getQuoteAddrList() != null) {
			QuoteAddress quoteBillToAddr = quoteDTO.getQuoteAddrList()
					.getBillToAddr();
			QuoteAddress quoteShipToAddr = quoteDTO.getQuoteAddrList()
					.getShipToAddr();
			Integer qbaId = null;
			Integer qsaId = null;
			if (quoteBillToAddr != null) {
				qbaId = quoteBillToAddr.getAddrId();
			}
			if (quoteShipToAddr != null) {
				qsaId = quoteShipToAddr.getAddrId();
			}
			if (qbaId != null && qsaId != null
					&& qbaId.intValue() == qsaId.intValue()) {
				QuoteAddress newQuoteBillToAddr = dozer.map(quoteBillToAddr,
						QuoteAddress.class);
				newQuoteBillToAddr.setAddrId(null);
				if (AddressType.BILL_TO.value().equalsIgnoreCase(
						newQuoteBillToAddr.getAddrType())) {
					quoteDTO.getQuoteAddrList().setShipToAddr(
							newQuoteBillToAddr);
				} else if (AddressType.SHIP_TO.value().equalsIgnoreCase(
						newQuoteBillToAddr.getAddrType())) {
					quoteDTO.getQuoteAddrList().setBillToAddr(
							newQuoteBillToAddr);
				}
			}
			if (quoteBillToAddr != null) {
				quoteDTO.getQuoteAddrList().getBillToAddr()
						.setAddrType(AddressType.BILL_TO.value());
				addrMap.put("QUOTE_BILL_ADDR", quoteDTO.getQuoteAddrList()
						.getBillToAddr());
			}
			if (quoteShipToAddr != null) {
				quoteDTO.getQuoteAddrList().getShipToAddr()
						.setAddrType(AddressType.SHIP_TO.value());
				addrMap.put("QUOTE_SHIP_ADDR", quoteDTO.getQuoteAddrList()
						.getShipToAddr());
			}
		}
		if (quote.getShiptoAddrFlag() != null
				&& quote.getShiptoAddrFlag().intValue() == 3
				&& quoteDTO.getItemList() != null
				&& !quoteDTO.getItemList().isEmpty()) {
			for (QuoteItemDTO dto : quoteDTO.getItemList()) {
				if (dto.getShipToAddress() != null) {
					addrMap.put("QI" + dto.getItemNo(), dto.getShipToAddress());
				}
			}
		}
		// 依次保存各不重复的QuoteAddress进数据库并返回该list.
		Map<String, QuoteAddress> dbAddrMap = this.saveAddrMap(quote, addrMap);
		// 重新关联Quote对应的3个Address.
		if (quoteDTO.getQuoteAddrList() != null) {
			if (quoteDTO.getQuoteAddrList().getShipToAddr() != null) {
				quote.setShiptoAddrId(dbAddrMap.get("QUOTE_SHIP_ADDR")
						.getAddrId());
			}
			if (quoteDTO.getQuoteAddrList().getBillToAddr() != null) {
				quote.setBilltoAddrId(dbAddrMap.get("QUOTE_BILL_ADDR")
						.getAddrId());
			}
			/*
			 * if (quoteDTO.getQuoteAddrList().getSoldToAddr() != null) {
			 * quote.setSoldtoAddrId(dbAddrMap.get("QUOTE_SOLD_ADDR")
			 * .getAddrId()); }
			 */
			this.quoteDao.save(quote);// 更新一下它的几个地址信息.
		}
		// 删除QuoteItem(都是子Item， Service)
		if (quoteDTO.getDelItemIdList() != null
				&& quoteDTO.getDelItemIdList().size() > 0) {
			this.delSubQuoteItem(quote, quoteDTO.getDelItemIdList());
		}
		// 给QuoteItem依次关联上对应的quoteAddress.
		List<QuoteItemDTO> itemList = new ArrayList<QuoteItemDTO>();
		if (quote.getShiptoAddrFlag() != null
				&& quote.getShiptoAddrFlag().intValue() == 3) {
			for (QuoteItemDTO itemDTO : quoteDTO.getItemList()) {
				itemDTO.setShiptoAddrId(dbAddrMap.get(
						"QI" + itemDTO.getItemNo()).getAddrId());
				itemList.add(itemDTO);
			}
		} else {
			for (QuoteItemDTO itemDTO : quoteDTO.getItemList()) {
				itemDTO.setShiptoAddrId(quote.getShiptoAddrId());
				itemList.add(itemDTO);
			}
		}
		this.attachItems(quote, itemList);

		List<Integer> sendMailIdList = this.attachInstruction(quote,
				quoteDTO.getInstructionList(), dbAccountNo);
		// this.attachPayment(quote, quoteDTO.getDelPaymentPlanIdList(),
		// quoteDTO
		// .getPaymentPlanList());
		this.attachPromotion(quote, quoteDTO.getQuotePromotion(),
				quoteDTO.getPromotionId());
		this.attachCustCardList(quote, quoteDTO.getCardList(),
				quoteDTO.getDelCardIdList(), userId);
		// For shipPackage
		this.attachShipPackage(quote, quoteDTO.getQuotePackageList(),
				quoteDTO.getDelQuotePackageIdList());
		retDTO.setSendMailIdList(sendMailIdList);
		retDTO.setQuoteNo(quote.getQuoteNo());
		return retDTO;
	}

	/**
	 * 保存Quote的同时保存该Quote所属customer的creditcard list.
	 * 
	 * @param quote
	 * @param cardList
	 * @param delIdList
	 * @param userId
	 */
	public void attachCustCardList(final QuoteMain quote,
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
			card.setCustNo(quote.getCustNo());
			card.setCreatedBy(userId);
			card.setModifiedBy(userId);
			card.setCreationDate(now);
			card.setModifyDate(now);
			card.setStatus("ACTIVE");
			this.creditCardDao.save(card);
		}
	}

	@Transactional(readOnly = true)
	public List<ItemDiscountDTO> getItemDiscount(
			List<ItemDiscountDTO> itemList, String promotionCode) {
		List<ItemDiscountDTO> itemDiscountDTOList = new ArrayList<ItemDiscountDTO>();
		if (itemList != null) {
			Double sum = 0.0;
			BigDecimal zero = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
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
					if (item.getStatus().equals(QuoteItemStatusType.CN.name())) {
						itemDiscountDTO.setDiscount(zero);
					} else {
						BigDecimal discountBD = discPercent.multiply(
								item.getAmount()).divide(new BigDecimal(100));
						itemDiscountDTO.setDiscount(discountBD);
					}
					itemDiscountDTO.setAmount(item.getAmount());
					itemDiscountDTO.setItemId(item.getItemId());
					itemDiscountDTOList.add(itemDiscountDTO);
				}
			} else {
				throw new BussinessException(
						BussinessException.INF_PROMOTION_CANNOT_APPLY);
			}
		}
		return itemDiscountDTOList;
	}

	@Transactional(readOnly = true)
	public List<ProductItemPriceDTO> getItemDiscountForCal(
			List<ProductItemPriceDTO> itemList, String promotionCode,
			Integer custNo) {
		List<ProductItemPriceDTO> itemDiscountDTOList = new ArrayList<ProductItemPriceDTO>();
		if (itemList != null) {
			Double sum = 0.0;
			BigDecimal zero = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			for (ProductItemPriceDTO item : itemList) {

				Double amount = item.getAmount();
				sum += amount;
			}
			Promotion promotion = promotionDao.findUniqueBy("prmtCode",
					promotionCode);
			if (promotion != null) {
				String discType = promotion.getDiscType();
				BigDecimal discPercent = promotion.getDiscPercent();
				BigDecimal orderTotalMin = promotion.getOrderTotalMin1();
				if (discType.equals("1")
						&& orderTotalMin.compareTo(new BigDecimal(sum)) < 0) {
					for (ProductItemPriceDTO item : itemList) {
						if (item.getStatus().equals(
								QuoteItemStatusType.CN.name())) {
							item.setDiscount(zero.doubleValue());
						} else {
							BigDecimal discountBD = discPercent.multiply(
									new BigDecimal(item.getAmount())).divide(
									new BigDecimal(100));
							item.setDiscount(discountBD.setScale(2,
									BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						itemDiscountDTOList.add(item);
					}
				} else {
					throw new BussinessException(
							BussinessException.INF_PROMOTION_CANNOT_APPLY);
				}
			} else {
				throw new BussinessException(
						BussinessException.INF_PROMOTION_CANNOT_APPLY);

			}
		}
		return itemDiscountDTOList;
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
	public List<QuotePaymentPlan> getPayInfoList(Integer quoteNo) {
		List<QuotePaymentPlan> quotePaymentPlanList;
		quotePaymentPlanList = quotePaymentPlanDao
				.getQuotePaymentPlanList(quoteNo);
		if (quotePaymentPlanList != null) {
			return quotePaymentPlanList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Integer getPreferPayId(List<QuotePaymentPlan> quotePaymentPlanList,
			String preferPaymentMthd) {
		if (quotePaymentPlanList != null) {
			return quotePaymentPlanDao.getFirstPaymentPlan(preferPaymentMthd);
		}
		return null;
	}

	public void sendQuoteMailJob() {
		List<QuoteMail> quoteMailList = null;
		quoteMailList = quoteMailDao.queryMailSend();
		if (quoteMailList != null && !quoteMailList.isEmpty()) {
			User techAccountUser = quoteDao.getUserByCustNo(quoteMailList
					.get(0).getQuoteNo());
			for (QuoteMail quoteMail : quoteMailList) {
				String mailTo = quoteMail.getReceipt();
				String content = quoteMail.getContent();
				String subject = quoteMail.getSubject();
				List<Document> docList = null;
				if (quoteMail.getDocFlag().equals("Y")) {
					Integer quoteMailId = quoteMail.getId();
					docList = documentDao.getDocument(
							DocumentType.QUOTE_INST_MAIL.value(), quoteMailId);
				}
				mimeMailService.sendMails(mailTo, subject, content,
						techAccountUser.getEmail(), docList);
				quoteMail.setSendDate(new Date());
				quoteMail.setStatus("COMPLETE");
				quoteMailDao.save(quoteMail);
				logger.debug("The quote mail has sended");
			}
		}
	}

	/**
	 * 更新Quote的状态.
	 * 
	 * @param quoteNo
	 * @param statusReason
	 * @param status
	 * @param loginUserId
	 */
	public void updateQuoteStatus(Integer quoteNo, String statusReason,
			String status, Integer loginUserId, String statusNote) {
		Date now = new Date();
		QuoteMain quote = this.quoteDao.getById(quoteNo);
		String dbStatus = quote.getStatus();
		if (status.equals(dbStatus)) {
		} else {
			updateQuoteProcessLog(statusReason, status, loginUserId,
					statusNote, now, quote, dbStatus);
		}
		quote.setStatus(status);
		quote.setStatusReason(statusReason);
		this.quoteDao.save(quote);
	}

	private void updateQuoteProcessLog(String statusReason, String status,
			Integer loginUserId, String statusNote, Date now, QuoteMain quote,
			String dbStatus) {
		QuoteProcessLog log = new QuoteProcessLog();
		log.setQuoteNo(quote.getQuoteNo());
		log.setPriorStat(dbStatus);
		log.setCurrentStat(status);
		log.setReason(statusReason);
		log.setNote(statusNote);
		log.setProcessDate(now);
		User processUser = new User();
		processUser.setUserId(loginUserId);
		log.setProcessedBy(processUser);
		this.quoteProcessLogDao.save(log);
	}

	/*
	 * 获得某个用户目前已保存的MyTemplate的数量
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	public Integer getMyTemplateCount(Integer userId) {
		Integer count = 0;
		Long retCount = this.quoteTemplateDao.getMyTemplateCount(userId);
		if (retCount != null) {
			count = retCount.intValue();
		}
		return count;
	}

	/**
	 * 保存Quote Template
	 * 
	 * @param quoteTemplate
	 * @param userId
	 */
	public void saveQuoteTemplate(QuoteTemplate quoteTemplate, Integer userId,
			String overrideFlag) {
		QuoteTemplate dbTmpl = this.quoteTemplateDao.getQuoteTemplate(userId,
				quoteTemplate.getTmplName());
		if (dbTmpl != null) {
			this.quoteTemplateDao.getSession().evict(dbTmpl);
			if (overrideFlag != null && overrideFlag.equals("Y")) {
				quoteTemplate.setTmplId(dbTmpl.getTmplId());
			} else {
				throw new BussinessException(
						BussinessException.ORDER_TMPL_DULP_NAME_CODE);
			}
		}
		Integer tmplCount = this.getMyTemplateCount(userId);
		if (overrideFlag != null && overrideFlag.equals("Y")) {
			if (tmplCount.intValue() > 6) {
				throw new BussinessException(
						BussinessException.ERR_QUOTETMPL_COUNT, 6 + "");
			}
		} else {
			if (tmplCount.intValue() >= 6) {
				throw new BussinessException(
						BussinessException.ERR_QUOTETMPL_COUNT, 6 + "");
			}
		}
		Date now = new Date();
		quoteTemplate.setCreatedBy(userId);
		quoteTemplate.setModifiedBy(userId);
		quoteTemplate.setCreationDate(now);
		quoteTemplate.setModifyDate(now);
		quoteTemplate.setOwner(userId);
		this.quoteTemplateDao.save(quoteTemplate);
	}

	public TotalDTO getQuoteTotals(final Integer quoteNo, final Double amount,
			final Double discount, final Double tax,
			final Double customerCharge, final String toCurrency) {
		TotalDTO quoteTotalDTO = new TotalDTO();
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal amountBD = (amount != null) ? new BigDecimal(amount) : zero;
		BigDecimal discountBD = (discount != null) ? new BigDecimal(discount)
				: zero;
		BigDecimal taxBD = (tax != null) ? new BigDecimal(tax) : zero;
		BigDecimal customerChargeBD = (customerCharge != null) ? new BigDecimal(
				customerCharge) : zero;

		quoteTotalDTO.setQuorderSubtotal(amountBD.setScale(2,
				BigDecimal.ROUND_HALF_UP));
		quoteTotalDTO.setQuorderDiscount(discountBD.setScale(2,
				BigDecimal.ROUND_HALF_UP));
		quoteTotalDTO
				.setQuorderTax(taxBD.setScale(2, BigDecimal.ROUND_HALF_UP));
		BigDecimal shipAmtBD = zero;

		shipAmtBD = customerChargeBD;

		quoteTotalDTO.setQuorderShipAmt(shipAmtBD.setScale(2,
				BigDecimal.ROUND_HALF_UP));
		BigDecimal totalBD = amountBD.subtract(discountBD).add(shipAmtBD)
				.add(taxBD);
		quoteTotalDTO.setQuorderTotal(totalBD.setScale(2,
				BigDecimal.ROUND_HALF_UP));

		return quoteTotalDTO;
	}

	@Transactional(readOnly = true)
	public Long getTotalProductsQuoted(Integer custNo, List<Integer> quoteNoList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNoList", quoteNoList);
		map.put("custNo", custNo);
		map.put("type", "PRODUCT");
		return quoteDao.getTotalProductsQuoted(map);
	}

	@Transactional(readOnly = true)
	public Long getTotalServicesQuoted(Integer custNo, List<Integer> quoteNoList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNoList", quoteNoList);
		map.put("custNo", custNo);
		map.put("type", "SERVICE");
		return quoteDao.getTotalProductsQuoted(map);
	}

	public Integer convertToOrder(final Integer quoteNo, final Integer userId)
			throws Exception {
		QuoteMain quoteMain = quoteDao.get(quoteNo);
		List<QuoteItem> quoteItemList = quoteItemDao.getItemList(quoteNo);
		List<QuoteAddress> quoteAddressList = quoteAddressDao
				.getQuoteAddressList(quoteNo);
		if (quoteMain != null) {
			OrderMain srcOrderMain = new OrderMain();
			Date now = new Date();
			OrderMain orderMain = dozer.map(quoteMain, OrderMain.class);
			orderMain.setOrderDate(now);
			orderMain.setOrderNo(null);
			orderMain.setOrderType(quoteMain.getQuoteType());
			orderMain.setOrderCurrency(quoteMain.getQuoteCurrency());
			orderMain.setOrderMemo(quoteMain.getQuoteMemo());
			orderMain.setOrderSrc(quoteMain.getQuoteSrc());
			orderMain.setSrcQuoteNo(quoteMain.getQuoteNo().toString());
			orderMain.setStatusReason(null);
			orderMain.setStatus(OrderStatusType.NW.name());
			orderMain.setCreationDate(now);
			orderMain.setModifyDate(now);
			OrderMain targetOrderMain = (OrderMain) ModelUtils.mergerModel(
					orderMain, srcOrderMain);
			targetOrderMain.setModifiedBy(userId);
			targetOrderMain.setCreatedBy(userId);
			orderDao.save(targetOrderMain);
			orderDao.getSession().flush();
			Integer orderNo = targetOrderMain.getOrderNo();

			saveOrderProcessLog(userId, now, orderNo);

			copyToOrderNote(quoteNo, userId, now, orderNo,
					QuoteInstructionType.ACCOUNTING.name());
			copyToOrderNote(quoteNo, userId, now, orderNo,
					QuoteInstructionType.PRODUCTION.name());
			copyToOrderNote(quoteNo, userId, now, orderNo,
					QuoteInstructionType.SALES_NOTES.name());
			copyToOrderNote(quoteNo, userId, now, orderNo,
					QuoteInstructionType.CROSS_SELLING.name());
			copyToOrderNote(quoteNo, userId, now, orderNo,
					QuoteInstructionType.SHIPMENT.name());
			dealwithPromotion(quoteNo, quoteMain.getCustNo(), orderNo);
			dealwithCoupon(quoteNo, quoteMain.getCustNo(), orderNo);
			String priorStatus = quoteMain.getStatus();
			quoteMain.setStatus(QuoteStatusType.CW.name());
			quoteMain.setOrderNo(orderNo.toString());

			saveQuoteProcessLog(quoteNo, userId, now, priorStatus);

			Map<String, Integer> addrMap = null;
			if (quoteAddressList != null) {
				addrMap = new HashMap<String, Integer>();
				for (QuoteAddress quoteAddress : quoteAddressList) {
					OrderAddress orderAddress = dozer.map(quoteAddress,
							OrderAddress.class);
					orderAddress.setAddrId(null);
					orderAddress.setOrderNo(orderNo);
					orderAddress.setCreatedBy(userId);
					orderAddress.setCreationDate(now);
					orderAddress.setModifiedBy(userId);
					orderAddress.setModifyDate(now);
					orderAddressDao.save(orderAddress);
					addrMap.put(quoteAddress.getAddrId() + "",
							orderAddress.getAddrId());
				}
				if (quoteMain.getBilltoAddrId() != null
						&& addrMap.containsKey(quoteMain.getBilltoAddrId()
								.toString())) {
					targetOrderMain.setBilltoAddrId(addrMap.get(quoteMain
							.getBilltoAddrId().toString()));
				}
				if (quoteMain.getShiptoAddrId() != null
						&& addrMap.containsKey(quoteMain.getShiptoAddrId()
								.toString())) {
					targetOrderMain.setShiptoAddrId(addrMap.get(quoteMain
							.getShiptoAddrId().toString()));
				}
				if (quoteMain.getSoldtoAddrId() != null
						&& addrMap.containsKey(quoteMain.getSoldtoAddrId()
								.toString())) {
					targetOrderMain.setSoldtoAddrId(addrMap.get(quoteMain
							.getSoldtoAddrId().toString()));
				}
			}
			// 保存OrderPackage和OrderPackageItem
			saveOrderPackge(quoteMain, orderNo, now, userId);
			if (quoteItemList != null) {
				String customerCompany = customerDao.getCustomerCompany(
						quoteMain.getCustNo(), null);
				Map<String, String> itemIdMap = new HashMap<String, String>();
				for (QuoteItem quoteItem : quoteItemList) {
					if (StringUtils.isNotBlank(quoteItem.getTbdFlag())
							&& quoteItem.getTbdFlag().equals("1")) {
						throw new BussinessException(
								BussinessException.CONVERT_TBD_ERROR);
					}
					if (quoteItem.getStatus() != null
							&& quoteItem.getStatus().equals("CN")) {
						continue;
					}
					OrderItem orderItem = dozer.map(quoteItem, OrderItem.class);
					orderItem.setOrderNo(orderNo);
					ItemOtherInfoForNewDTO otherInfo = this.orderService
							.getItemOtherInfoForNew(orderItem.getQuantity(),
									orderItem.getCatalogNo(), QuoteItemType
											.fromValue(orderItem.getType()),
									customerCompany);
					orderItem.setStatus(otherInfo.getItemStatus());
					orderItem.setStatusReason(null);
					orderItem.setCreatedBy(userId);
					orderItem.setCreationDate(now);
					orderItem.setModifiedBy(userId);
					orderItem.setModifyDate(now);

					if (addrMap != null && !addrMap.isEmpty()) {
						if (quoteItem.getBilltoAddrId() != null
								&& addrMap.containsKey(quoteItem
										.getBilltoAddrId().toString())) {
							orderItem.setBilltoAddrId(addrMap.get(quoteItem
									.getBilltoAddrId().toString()));
						}
						if (quoteItem.getShiptoAddrId() != null
								&& addrMap.containsKey(quoteItem
										.getShiptoAddrId().toString())) {
							orderItem.setShiptoAddrId(addrMap.get(quoteItem
									.getShiptoAddrId().toString()));
						}
						if (quoteItem.getSoldtoAddrId() != null
								&& addrMap.containsKey(quoteItem
										.getSoldtoAddrId().toString())) {
							orderItem.setSoldtoAddrId(addrMap.get(quoteItem
									.getSoldtoAddrId().toString()));
						}
					}
					orderItemDao.save(orderItem);
					itemIdMap.put(quoteItem.getQuoteItemId().toString(), orderItem.getOrderItemId().toString());
					saveOrderItemProcessLog(userId, now, orderNo, orderItem, otherInfo);
					copyServiceItemToOrder(userId, now, quoteNo, orderNo, orderItem.getOrderItemId(), quoteItem);
				}
				List<OrderItem> orderItemList = orderItemDao
						.getOrderAllItemList(orderNo);
				for (OrderItem orderItem : orderItemList) {
					Integer parentId = orderItem.getParentId();
					if (parentId != null) {
						String parentIdStr = itemIdMap.get(parentId.toString());
						Integer orderParentId = Integer.parseInt(parentIdStr);
						orderItem.setParentId(orderParentId);
					}
				}
			}
			return orderNo;
		}
		return null;
	}

	private void saveOrderPackge(QuoteMain quoteMain, Integer orderNo,
			Date now, Integer userId) {
		List<QuotePackage> quotePackageList = quotePackageDao
				.getQuotePackageList(quoteMain.getQuoteNo());
		if (quotePackageList != null && !quotePackageList.isEmpty()) {
			for (QuotePackage quotePackage : quotePackageList) {
				OrderPackage orderPackage = new OrderPackage();
				orderPackage = dozer.map(quotePackage, OrderPackage.class);
				orderPackage.setPackageId(null);
				orderPackage.setOrderNo(orderNo);
				orderPackage.setCreatedBy(userId);
				orderPackage.setCreationDate(now);
				orderPackage.setModifiedBy(userId);
				orderPackage.setModifyDate(now);
				if (quoteMain.getShiptoAddrId() != null) {
					QuoteAddress quoteAddress = quoteAddressDao
							.getById(quoteMain.getShiptoAddrId());
					if (quoteAddress != null) {
						StringBuffer quoteShipToAddr = new StringBuffer();
						quoteShipToAddr.append(quoteAddress.getFirstName())
								.append(" ").append(quoteAddress.getMidName())
								.append(" ").append(quoteAddress.getLastName())
								.append(" ").append(quoteAddress.getState())
								.append(" ").append(quoteAddress.getCountry());
						orderPackage.setShiptoAddress(quoteShipToAddr
								.toString());
					}
				}
				orderPackageDao.save(orderPackage);

				List<QuotePackageItem> quotePackageItemList = quotePackageItemDao
						.findPackageItemsByPkgId(quotePackage.getPackageId());
				if (quotePackageItemList == null
						|| quotePackageItemList.isEmpty()) {
					continue;
				}
				for (QuotePackageItem quotePackageItem : quotePackageItemList) {
					OrderPackageItem orderPackageItem = new OrderPackageItem();
					orderPackageItem = dozer.map(quotePackageItem,
							OrderPackageItem.class);
					orderPackageItem.setPkgItemId(null);
					orderPackageItem.setPackageId(orderPackage.getPackageId());
					orderPackageItem.setOrderNo(orderNo);
					orderPackageItem.setCreatedBy(userId);
					orderPackageItem.setCreationDate(now);
					orderPackageItem.setModifiedBy(userId);
					orderPackageItem.setModifyDate(now);
					orderPackageItemDao.save(orderPackageItem);
				}
			}
		}
	}

	private void saveOrderItemProcessLog(final Integer userId, Date now,
			Integer orderNo, OrderItem orderItem,
			ItemOtherInfoForNewDTO otherInfo) {
		String priStat = orderItem.getStatus();
		String curStat = otherInfo.getItemStatus();
		// if (!curStat.equalsIgnoreCase(priStat)) {
		User user = userDao.get(userId);
		OrderProcessLog orderProcessLog = new OrderProcessLog();
		orderProcessLog.setCurrentStat(curStat);
		orderProcessLog.setOrderNo(orderNo);
		orderProcessLog.setPriorStat(priStat);
		orderProcessLog.setProcessDate(now);
		orderProcessLog.setProcessedBy(user);
		orderProcessLog.setLogId(null);
		orderProcessLog.setOrderItemId(orderItem.getOrderItemId());
		orderProcessLogDao.save(orderProcessLog);
		// }
	}

	private void saveQuoteProcessLog(final Integer quoteNo,
			final Integer userId, Date now, String priorStatus) {
		User user = userDao.get(userId);
		QuoteProcessLog quoteProcessLog = new QuoteProcessLog();
		quoteProcessLog.setCurrentStat(QuoteStatusType.CW.name());
		quoteProcessLog.setLogId(null);
		quoteProcessLog.setPriorStat(priorStatus);
		quoteProcessLog.setProcessDate(now);
		quoteProcessLog.setProcessedBy(user);
		quoteProcessLog.setQuoteNo(quoteNo);
		quoteProcessLogDao.save(quoteProcessLog);
	}

	private void saveOrderProcessLog(final Integer userId, Date now,
			Integer orderNo) {
		User user = userDao.get(userId);
		OrderProcessLog orderProcessLog = new OrderProcessLog();
		orderProcessLog.setCurrentStat(OrderStatusType.NW.name());
		orderProcessLog.setOrderNo(orderNo);
		orderProcessLog.setLogId(null);
		orderProcessLog.setPriorStat(null);
		orderProcessLog.setProcessDate(now);
		orderProcessLog.setProcessedBy(user);
		orderProcessLogDao.save(orderProcessLog);
	}

	/**
	 * 将Quote的moreDetail信息复制到Order的MoreDetail中
	 * modify by Zhang Yong 
	 * modify date 2011-11-29
	 * @param userId
	 * @param now
	 * @param quoteNo
	 * @param orderNo
	 * @param orderItemId
	 * @param quoteItemId
	 */
	private void copyServiceItemToOrder(final Integer userId, Date now, Integer quoteNo, Integer orderNo, 
			Integer orderItemId, QuoteItem quoteItem) {
		Integer quoteItemId = quoteItem.getQuoteItemId();
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter1 = new PropertyFilter("EQI_quoteNo", quoteNo);
		PropertyFilter filter2 = new PropertyFilter("EQI_quoteItemId", quoteItemId);
		filterList.add(filter1);
		filterList.add(filter2);
		if (quoteItem.getClsId().intValue() == -1 || quoteItem.getClsId().intValue() == 29 
				|| quoteItem.getClsId().intValue() == 35 || quoteItem.getClsId().intValue() == 36
				|| quoteItem.getClsId().intValue() == 37 || quoteItem.getClsId().intValue() == 38
				|| quoteItem.getClsId().intValue() == 39 || quoteItem.getClsId().intValue() == 42) {
			List<com.genscript.gsscm.quote.entity.QuoteService> quoteServiceList = quoteServiceDao.find(filterList);
			if (quoteServiceList != null && !quoteServiceList.isEmpty()) {
				com.genscript.gsscm.quote.entity.QuoteService qService = quoteServiceList.get(0);
				com.genscript.gsscm.order.entity.OrderService oService = dozer.map(qService, com.genscript.gsscm.order.entity.OrderService.class);
				oService.setOrderItemId(orderItemId);
				oService.setOrderNo(orderNo);
				oService.setCreatedBy(userId);
				oService.setModifiedBy(userId);
				oService.setCreationDate(now);
				oService.setModifyDate(now);
				orderServiceDao.save(oService);
			}
		} else if (quoteItem.getClsId().intValue() == 1 || quoteItem.getClsId().intValue() == 30 
				|| quoteItem.getClsId().intValue() == 31) {
			List<QuotePeptide> quotePeptideList = quotePeptideDao.find(filterList);
			if (quotePeptideList != null && !quotePeptideList.isEmpty()) {
				QuotePeptide quotePeptide = quotePeptideList.get(0);
				OrderPeptide orderPeptide = dozer.map(quotePeptide, OrderPeptide.class);
				orderPeptide.setOrderItemId(orderItemId);
				orderPeptide.setOrderNo(orderNo);
				orderPeptide.setCreatedBy(userId);
				orderPeptide.setModifiedBy(userId);
				orderPeptide.setCreationDate(now);
				orderPeptide.setModifyDate(now);
				orderPeptideDao.save(orderPeptide);
			}
		} else if (quoteItem.getClsId().intValue() == 2 || quoteItem.getClsId().intValue() == 13 
				|| quoteItem.getClsId().intValue() == 14 || quoteItem.getClsId().intValue() == 15
				|| quoteItem.getClsId().intValue() == 16 || quoteItem.getClsId().intValue() == 17 
				|| quoteItem.getClsId().intValue() == 18 || quoteItem.getClsId().intValue() == 19
				|| quoteItem.getClsId().intValue() == 32 || quoteItem.getClsId().intValue() == 33) {
			List<QuotePkgService> quotePkgServiceList = quotePkgServiceDao.find(filterList);
			if (quotePkgServiceList != null && !quotePkgServiceList.isEmpty()) {
				QuotePkgService quotePkgService = quotePkgServiceList.get(0);
				OrderPkgService orderPkgService = dozer.map(quotePkgService, OrderPkgService.class);
				orderPkgService.setOrderItemId(orderItemId);
				orderPkgService.setOrderNo(orderNo);
				orderPkgService.setCreatedBy(userId);
				orderPkgService.setModifiedBy(userId);
				orderPkgService.setCreationDate(now);
				orderPkgService.setModifyDate(now);
				orderPkgServiceDao.save(orderPkgService);
			}
		} else if (quoteItem.getClsId().intValue() == 3) {
			List<QuoteGeneSynthesis> quoteGeneSynthesisList = quoteGeneSynthesisDao.find(filterList);
			if (quoteGeneSynthesisList != null && !quoteGeneSynthesisList.isEmpty()) {
				QuoteGeneSynthesis quoteGeneSynthesis = quoteGeneSynthesisList.get(0);
				OrderGeneSynthesis orderGeneSynthesis = dozer.map(quoteGeneSynthesis, OrderGeneSynthesis.class);
				orderGeneSynthesis.setOrderItemId(orderItemId);
				orderGeneSynthesis.setOrderNo(orderNo);
				orderGeneSynthesis.setCreatedBy(userId);
				orderGeneSynthesis.setModifiedBy(userId);
				orderGeneSynthesis.setCreationDate(now);
				orderGeneSynthesis.setModifyDate(now);
				orderGeneSynthesisDao.save(orderGeneSynthesis);
				DocumentType[] docTypeList = { DocumentType.QIM_GENE };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_GENE.name());
			}
		} else if (quoteItem.getClsId().intValue() == 4) {
			List<QuoteMutagenesis> quoteMutagenesisList = quoteMutagenesisDao.find(filterList);
			if (quoteMutagenesisList != null && !quoteMutagenesisList.isEmpty()) {
				QuoteMutagenesis quoteMutagenesis = quoteMutagenesisList.get(0);
				OrderMutagenesis orderMutagenesis = dozer.map(quoteMutagenesis, OrderMutagenesis.class);
				orderMutagenesis.setOrderItemId(orderItemId);
				orderMutagenesis.setOrderNo(orderNo);
				orderMutagenesis.setCreatedBy(userId);
				orderMutagenesis.setModifiedBy(userId);
				orderMutagenesis.setCreationDate(now);
				orderMutagenesis.setModifyDate(now);
				orderMutagenesisDao.save(orderMutagenesis);
				DocumentType[] docTypeList = { DocumentType.QIM_MUTA };
				DocumentType[] docTypeListSelf = { DocumentType.QIM_MUTA_SELF };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_MUTA.name());
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeListSelf, DocumentType.OIM_MUTA_SELF.name());
			}
		} else if (quoteItem.getClsId().intValue() == 5) {
			List<QuoteMutationLibraries> quoteMutationLibrariesList = quoteMutationLibrariesDao.find(filterList);
			if (quoteMutationLibrariesList != null && !quoteMutationLibrariesList.isEmpty()) {
				QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesList.get(0);
				OrderMutationLibraries orderMutationLibraries = dozer.map(quoteMutationLibraries, OrderMutationLibraries.class);
				orderMutationLibraries.setOrderItemId(orderItemId);
				orderMutationLibraries.setOrderNo(orderNo);
				orderMutationLibraries.setCreatedBy(userId);
				orderMutationLibraries.setModifiedBy(userId);
				orderMutationLibraries.setCreationDate(now);
				orderMutationLibraries.setModifyDate(now);
				orderMutationLibrariesDao.save(orderMutationLibraries);
				DocumentType[] docTypeList = { DocumentType.QIM_MUTALIB };
				DocumentType[] docTypeListSelf = { DocumentType.QIM_MUTALIB_SELF };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_MUTALIB.name());
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeListSelf, DocumentType.OIM_MUTALIB_SELF.name());
			}
		} else if (quoteItem.getClsId().intValue() == 6) {
			List<QuoteOrfClone> quoteOrfCloneList = quoteOrfCloneDao.find(filterList);
			if (quoteOrfCloneList != null && !quoteOrfCloneList.isEmpty()) {
				QuoteOrfClone quoteOrfClone = quoteOrfCloneList.get(0);
				OrderOrfClone orderOrfClone = dozer.map(quoteOrfClone, OrderOrfClone.class);
				orderOrfClone.setOrderItemId(orderItemId);
				orderOrfClone.setOrderNo(orderNo);
				orderOrfClone.setCreatedBy(userId);
				orderOrfClone.setModifiedBy(userId);
				orderOrfClone.setCreationDate(now);
				orderOrfClone.setModifyDate(now);
				orderOrfCloneDao.save(orderOrfClone);
				DocumentType[] docTypeList = { DocumentType.QIM_ORFCLONE };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_ORFCLONE.name());
			}
		} else if (quoteItem.getClsId().intValue() == 7 || quoteItem.getClsId().intValue() == 8) {
			List<QuoteSirnaAndMirna> quoteSirnaAndMirnaList = quoteSirnaAndMirnaDao.find(filterList);
			if (quoteSirnaAndMirnaList != null && !quoteSirnaAndMirnaList.isEmpty()) {
				QuoteSirnaAndMirna quoteSirnaAndMirna = quoteSirnaAndMirnaList.get(0);
				OrderSirnaAndMirna orderSirnaAndMirna = dozer.map(quoteSirnaAndMirna, OrderSirnaAndMirna.class);
				orderSirnaAndMirna.setOrderItemId(orderItemId);
				orderSirnaAndMirna.setOrderNo(orderNo);
				orderSirnaAndMirna.setCreatedBy(userId);
				orderSirnaAndMirna.setModifiedBy(userId);
				orderSirnaAndMirna.setCreationDate(now);
				orderSirnaAndMirna.setModifyDate(now);
				orderSirnaAndMirnaDao.save(orderSirnaAndMirna);
				DocumentType[] docTypeList = { DocumentType.QIM_RNA };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_RNA.name());
			}
		} else if (quoteItem.getClsId().intValue() == 9) {
			List<QuoteCustCloning> quoteCustCloningList = quoteCustCloningDao.find(filterList);
			if (quoteCustCloningList != null && !quoteCustCloningList.isEmpty()) {
				QuoteCustCloning quoteCustCloning = quoteCustCloningList.get(0);
				OrderCustCloning orderCustCloning = dozer.map(quoteCustCloning, OrderCustCloning.class);
				orderCustCloning.setOrderItemId(orderItemId);
				orderCustCloning.setOrderNo(orderNo);
				orderCustCloning.setCreatedBy(userId);
				orderCustCloning.setModifiedBy(userId);
				orderCustCloning.setCreationDate(now);
				orderCustCloning.setModifyDate(now);
				orderCustCloningDao.save(orderCustCloning);
				DocumentType[] docTypeList = { DocumentType.QIM_CUSTCLONING };
				DocumentType[] docTypeListCs = { DocumentType.QIM_CUSTCLONING_CS };
				DocumentType[] docTypeListSelf = { DocumentType.QIM_CUSTCLONING_SELF };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_CUSTCLONING.name());
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeListCs, DocumentType.OIM_CUSTCLONING_CS.name());
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeListSelf, DocumentType.OIM_CUSTCLONING_SELF.name());
			}
		} else if (quoteItem.getClsId().intValue() == 10) {
			List<QuotePlasmidPreparation> quotePlasmidPreparationList = quotePlasmidPreparationDao.find(filterList);
			if (quotePlasmidPreparationList != null && !quotePlasmidPreparationList.isEmpty()) {
				QuotePlasmidPreparation quotePlasmidPreparation = quotePlasmidPreparationList.get(0);
				OrderPlasmidPreparation orderPlasmidPreparation = dozer.map(quotePlasmidPreparation, OrderPlasmidPreparation.class);
				orderPlasmidPreparation.setOrderItemId(orderItemId);
				orderPlasmidPreparation.setOrderNo(orderNo);
				orderPlasmidPreparation.setCreatedBy(userId);
				orderPlasmidPreparation.setModifiedBy(userId);
				orderPlasmidPreparation.setCreationDate(now);
				orderPlasmidPreparation.setModifyDate(now);
				orderPlasmidPreparationDao.save(orderPlasmidPreparation);
				DocumentType[] docTypeList = { DocumentType.QIM_PLASMID };
				DocumentType[] docTypeListSelf = { DocumentType.QIM_PLASMID_SELF };
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeList, DocumentType.OIM_PLASMID.name());
				this.saveCopyDoc(quoteItemId, orderItemId, userId, orderNo, docTypeListSelf, DocumentType.OIM_PLASMID_SELF.name());
			}
		} else if (quoteItem.getClsId().intValue() == 11 || quoteItem.getClsId().intValue() == 12 
				|| quoteItem.getClsId().intValue() == 22 || quoteItem.getClsId().intValue() == 28) {
			List<QuoteAntibody> quoteAntibodyList = quoteAntibodyDao.find(filterList);
			if (quoteAntibodyList != null && !quoteAntibodyList.isEmpty()) {
				QuoteAntibody quoteAntibody = quoteAntibodyList.get(0);
				OrderAntibody orderAntibody = dozer.map(quoteAntibody, OrderAntibody.class);
				orderAntibody.setOrderItemId(orderItemId);
				orderAntibody.setOrderNo(orderNo);
				orderAntibody.setCreatedBy(userId);
				orderAntibody.setModifiedBy(userId);
				orderAntibody.setCreationDate(now);
				orderAntibody.setModifyDate(now);
				orderAntibodyDao.save(orderAntibody);
			}
		} else if (quoteItem.getClsId().intValue() == 34) {
			List<QuoteOligo> quoteOligoList = quoteOligoDao.find(filterList);
			if (quoteOligoList != null && !quoteOligoList.isEmpty()) {
				QuoteOligo quoteOligo = quoteOligoList.get(0);
				OrderOligo orderOligo = dozer.map(quoteOligo, OrderOligo.class);
				orderOligo.setOrderItemId(orderItemId);
				orderOligo.setOrderNo(orderNo);
				orderOligo.setCreatedBy(userId);
				orderOligo.setModifiedBy(userId);
				orderOligo.setCreationDate(now);
				orderOligo.setModifyDate(now);
				orderOligoDao.save(orderOligo);
			}
		} else if (quoteItem.getClsId().intValue() == 40) {
			List<QuoteDnaSequencing> dnaSeqList = quoteDnaSequencingDao.find(filterList);
			if (dnaSeqList != null && !dnaSeqList.isEmpty()) {
				QuoteDnaSequencing quoteDnaSeq = dnaSeqList.get(0);
				OrderDnaSequencing orderDnaSeq = dozer.map(quoteDnaSeq, OrderDnaSequencing.class);
				orderDnaSeq.setOrderItemId(orderItemId);
				orderDnaSeq.setOrderNo(orderNo);
				orderDnaSeq.setCreatedBy(userId);
				orderDnaSeq.setModifiedBy(userId);
				orderDnaSeq.setCreationDate(now);
				orderDnaSeq.setModifyDate(now);
				orderDnaSequencingDao.save(orderDnaSeq);
			}
		}
	}

	/**
	 * 保存ConvertToOrder的Document记录
	 * 
	 * @author Zhang Yong
	 * @param quoteItemId
	 * @param orderItemId
	 * @param userId
	 * @param orderNo
	 * @param docTypeList
	 * @param refType
	 */
	private void saveCopyDoc(Integer quoteItemId, Integer orderItemId,
			Integer userId, Integer orderNo, DocumentType[] docTypeList,
			String refType) {
		List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId,
				docTypeList);
		if (docList != null && !docList.isEmpty()) {
			for (Document geneDoc : docList) {
				Document orderDocument = new Document();
				orderDocument.setCreatedBy(userId);
				orderDocument.setModifiedBy(userId);
				orderDocument.setDescription(geneDoc.getDescription());
				orderDocument.setDocName(replaceStringName(
						geneDoc.getDocName(), orderNo));
				orderDocument.setDocType(geneDoc.getDocType());
				orderDocument.setFilePath(geneDoc.getFilePath());
				orderDocument.setFileType(geneDoc.getFileType());
				orderDocument.setRefId(orderItemId);
				orderDocument.setRefType(refType);
				documentDao.save(orderDocument);
			}
		}
	}

	private void copyToOrderNote(final Integer quoteNo, final Integer userId,
			Date now, Integer orderNo, String quoteInstructionType) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		PropertyFilter filter1 = new PropertyFilter("EQI_quoteNo", quoteNo);
		PropertyFilter filter2 = new PropertyFilter("EQS_type",
				quoteInstructionType);
		filterList.add(filter1);
		filterList.add(filter2);
		List<QuoteNote> quoteNoteList = quoteNoteDao.find(filterList);
		if (quoteNoteList != null && quoteNoteList.size() > 0) {
			for (QuoteNote quoteNote : quoteNoteList) {
				String docFlag = quoteNote.getDocFlag();
				if (StringUtils.isBlank(docFlag)) {
					docFlag = "N";
				}
				OrderNote orderNote = new OrderNote();
				orderNote.setOrderNo(orderNo);
				orderNote.setDocFlag(docFlag);
				orderNote.setCreatedBy(userId);
				orderNote.setModifiedBy(userId);
				orderNote.setSource(quoteNote.getSource());
				orderNote.setDescription(quoteNote.getDescription());
				orderNote.setType(quoteNote.getType());
				orderNote.setNoteDate(now);
				orderNoteDao.save(orderNote);
				if (docFlag.equals("Y")) {
					List<Document> documentList = documentDao.getDocumentIns(
							DocumentType.QUOTE_INST_NOTE.name(),
							quoteNote.getId());
					if (documentList != null && documentList.size() > 0) {
						for (Document document : documentList) {
							Document orderDocument = new Document();
							orderDocument.setCreatedBy(userId);
							orderDocument.setModifiedBy(userId);
							orderDocument.setDescription(document
									.getDescription());
							orderDocument.setDocName(replaceStringName(
									document.getDocName(), orderNo));
							orderDocument.setDocType(document.getDocType());
							orderDocument.setFilePath(document.getFilePath());
							orderDocument.setFileType(document.getFileType());
							orderDocument.setRefId(orderNote.getId());
							orderDocument
									.setRefType(DocumentType.ORDER_INST_NOTE
											.name());
							documentDao.save(orderDocument);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		String str = "quote_notes/ab60729bcbd8293eb5f31e5077c29049.txt";
		String orderFilePath = "order_notes"
				+ str.substring(str.lastIndexOf("/"), str.length());
		System.out.println(orderFilePath);
	}

	/**
	 * @param str
	 * @param no
	 * @return
	 */
	private String replaceStringName(String str, Integer no) {
		int i = str.lastIndexOf(".");
		String s1 = str.substring(i);
		String s2 = str.substring(0, i);
		StringBuilder sb = new StringBuilder();
		return sb.append(s2).append("_").append(no).append(s1).toString();
	}

	@Transactional(readOnly = true)
	public List<CurrencyChangeDTO> changeCurrency(final Integer quoteNo,
			final List<CurrencyChangeDTO> currencyDTOList,
			final String fromCurrency, final String toCurrency) {

		List<CurrencyChangeDTO> currencyList = new ArrayList<CurrencyChangeDTO>();
		if (currencyDTOList != null) {
			for (CurrencyChangeDTO currencyDTO : currencyDTOList) {
				CurrencyChangeDTO currencyChangeDTO = changeCurrency(quoteNo,
						fromCurrency, toCurrency, currencyDTO);
				currencyList.add(currencyChangeDTO);
			}
		}
		return currencyList;
	}

	private CurrencyChangeDTO changeCurrency(final Integer quoteNo,
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

		Date quoteDate;
		if (quoteNo != null) {
			QuoteMain quote = quoteDao.get(quoteNo);
			quoteDate = quote.getExchRateDate();
		} else {
			quoteDate = new Date();
		}
		BigDecimal rateBD = zero;
		Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency,
				toCurrency, quoteDate);
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

	public BigDecimal getQuoteChangeCurrencyRate(final Integer quoteNo,
			final String fromCurrency, final String toCurrency) {
		Date quoteDate;
		BigDecimal zero = new BigDecimal(1).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		if (quoteNo != null) {
			QuoteMain quote = quoteDao.get(quoteNo);
			quoteDate = quote.getExchRateDate();
		} else {
			quoteDate = new Date();
		}
		BigDecimal rateBD = zero;
		Double rate = exchRateByDateDao.getCurrencyRate(fromCurrency,
				toCurrency, quoteDate);
		if (rate != null) {
			rateBD = new BigDecimal(rate);
			return rateBD.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return zero;
	}

	/*
	 * 根据quoteNo和该Quote的几个item的itemNo list获得这几个QuoteItem的QuotePackage.
	 * 
	 * @param quoteNo
	 * 
	 * @param itemNoList
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<QuotePackageDTO> getPackageListForItemList(Integer quoteNo,
			List<Integer> itemNoList) {
		if (quoteNo == null || itemNoList == null || itemNoList.isEmpty()) {
			return null;
		}
		List<QuotePackageDTO> dtoList = new ArrayList<QuotePackageDTO>();
		List<QuotePackage> srcList = quotePackageItemDao
				.getPackagesForItemList(quoteNo, itemNoList);
		if (srcList != null) {
			QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(
					SessionConstant.Quote.value(), quoteNo.toString());
			boolean isJp = false;
			Double rate = null;
			if (quoteMain.getQuoteAddrList() != null
					&& quoteMain.getQuoteAddrList().getShipToAddr() != null) {
				String country = quoteMain.getQuoteAddrList().getShipToAddr()
						.getCountry();
				if (("JP").equals(country)) {
					isJp = true;
				}
				if (!StringUtils.isEmpty(quoteMain.getQuoteCurrency())
						&& !StringUtils.isEmpty(quoteMain.getBaseCurrency())
						&& quoteMain.getExchRateDate() != null) {
					rate = exchRateByDateDao.getCurrencyRate(
							quoteMain.getBaseCurrency(),
							quoteMain.getQuoteCurrency(),
							quoteMain.getExchRateDate());
				}
			}
			if (rate == null) {
				throw new BussinessException(
						BussinessException.EXCH_RATE_IS_NULL);
			}
			for (QuotePackage source : srcList) {
				QuotePackageDTO dto = this.dozer.map(source,
						QuotePackageDTO.class);
				dto.setTotalQty(this.quotePackageDao.getTotalQty(
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
				QuoteAddress shipToAddress = this.quoteAddressDao
						.getById(source.getShipAddrId());
				if (shipToAddress != null) {
					List<String> tmpList = new ArrayList<String>();
					tmpList.add(shipToAddress.getFirstName() + " "
							+ shipToAddress.getLastName());
					tmpList.add(shipToAddress.getOrgName());
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
							cityStr = cityStr + ", "
									+ shipToAddress.getCountry();
						}
					}
					tmpList.add(cityStr);
					dto.setShiptoAddress(StringUtils.join(tmpList.toArray(),
							"\n"));
				}
				dto.setWarehouseName(this.warehouseDao.getById(
						source.getWarehouseId()).getName());
				// Quote的ShipToAddress的country为JP的时候，item的存储温度不低于20度地应该是1500日元，低于20度地应该是3000日元
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
							quoteMain.getQuoteCurrency())) {
						Double jpyRate = exchRateByDateDao.getCurrencyRate(
								CurrencyType.JPY.value(),
								quoteMain.getBaseCurrency(),
								quoteMain.getExchRateDate());
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
						quoteMain.getQuoteCurrency()) ? 2 : 0;
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

	/**
	 * 判断package是否存在
	 * 
	 * @param quoteNo
	 * @return
	 */
	public Boolean isPackageExist(Integer quoteNo) {
		List<QuotePackage> srcList = quotePackageDao
				.getQuotePackageList(quoteNo);
		if (srcList == null || srcList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * 根据quoteNo 获得QuotePackage.
	 * 
	 * @param quoteNo
	 * 
	 * @param itemNoList
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<QuotePackageDTO> getPackageList(Integer quoteNo) {
		if (quoteNo == null) {
			return null;
		}
		List<QuotePackageDTO> dtoList = new ArrayList<QuotePackageDTO>();
		List<QuotePackage> srcList = quotePackageDao
				.getQuotePackageList(quoteNo);
		if (srcList != null) {
			for (QuotePackage source : srcList) {
				QuotePackageDTO dto = this.dozer.map(source,
						QuotePackageDTO.class);
				dto.setTotalQty(this.quotePackageDao.getTotalQty(
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
				QuoteAddress shipToAddr = this.quoteAddressDao.getById(source
						.getShipAddrId());
				QuoteAddress temp = new QuoteAddress();
				if (shipToAddr != null) {
					temp.setFirstName(shipToAddr.getFirstName());
					temp.setMidName(shipToAddr.getMidName());
					temp.setLastName(shipToAddr.getLastName());
					temp.setCountry(shipToAddr.getCountry());
					temp.setState(shipToAddr.getState());
					dto.setShiptoAddress(AddressUtil.getUniqueAddress(
							shipToAddr.getFirstName(), shipToAddr.getMidName(),
							shipToAddr.getLastName(), shipToAddr.getState(),
							shipToAddr.getCountry()));
				}
				dto.setShipToAddr(temp);
				dto.setWarehouseName(this.warehouseDao.getById(
						source.getWarehouseId()).getName());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	/**
	 * 根据页面上传来的值计算shippingTotal.(目前Order可能是不存在的)
	 * 
	 * @param quoteCurrency
	 *            页面上传过来的orderCurrency
	 * @param packageList
	 * @return
	 */
	@Transactional(readOnly = true)
	public ShippingTotalDTO getShippingTotalByPage(String quoteCurrency,
			List<QuotePackageDTO> packageList, Double exchRate,
			Date exchRateDate) {

		ShippingTotalDTO dto = new ShippingTotalDTO();
		try {
			if (packageList == null || packageList.size() < 1) {
				return dto;
			}
			// 获得Package Total.
			dto.setPackageTotal(packageList.size());
			// 获得Weight Total;
			BigDecimal billableWeightTotal = BigDecimal.valueOf(0.0);
			BigDecimal costTotal = BigDecimal.valueOf(0.0);
			BigDecimal handlingFee = BigDecimal.valueOf(0.0);
			for (QuotePackageDTO spDTO : packageList) {
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
			// // 进行汇率转换(costTotal始终为为"USD")
			// costTotal = orderService.getOrderChangeCurrency(costTotal,
			// exchRateDate, "USD",
			// quoteCurrency);
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
				if (CurrencyType.JPY.value().equals(quoteCurrency)) {
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
			dto.setCurrency(quoteCurrency);
			PbCurrency pbCurrency = this.currencyDao.findUniqueBy(
					"currencyCode", quoteCurrency);
			if (pbCurrency != null) {
				dto.setCurrencySymbol(pbCurrency.getSymbol());
			}
			// 获得Zone;
			QuotePackageDTO firstPackage = packageList.get(0);
			dto.setZone(firstPackage.getZone());
			if (packageList.size() > 1) {// 有多个Package
				String packageZone = firstPackage.getZone();
				for (int i = 1; i < packageList.size(); i++) {
					QuotePackageDTO spDTO = packageList.get(i);
					if (packageZone == null) {
						packageZone = spDTO.getZone();
						continue;
					}
					if (!packageZone.equals(spDTO.getZone())) {
						dto.setZone(null);
						break;
					}
				}
			}
			// 获得ShipVia;
			Integer shipMethod = firstPackage.getShipMethod();
			List<Integer> shipMethodList = new ArrayList<Integer>();
			dto.setShipVia(shipMethod);
			for (int i = 1; i < packageList.size(); i++) {
				QuotePackageDTO spDTO = packageList.get(i);
				if (!shipMethod.equals(spDTO.getShipMethod())) {
					dto.setShipVia(null);
					shipMethodList.add(spDTO.getShipMethod());
				}
			}
			// 获得ShipAccountFlag.
			boolean shipAccountFlag = false;
			if (dto.getShipVia() != null) {
				shipAccountFlag = true;
			}
			dto.setShipAccountFlag(shipAccountFlag);
			// 获得shipAccount.
			if (!shipAccountFlag) {
				if (shipMethodList != null && !shipMethodList.isEmpty()) {
					Long carrierCount = this.shipMethodDao
							.getShipMethodCarrierCount(shipMethodList);
					// 虽然有多个Package而且有多个shipMethod,
					// 但只要这些shipMethod对应的carrier的个数是1,
					// 则carrier肯定是一致的.
					if (carrierCount.intValue() == 1) {
						shipAccountFlag = true;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dto;

	}

	/**
	 * 获得Shipping Total信息 for Quote Total Tab's Shipping.
	 * 
	 * @param quoteNo
	 * @return
	 */
	public ShippingTotalDTO getShippingTotal(Integer quoteNo) {
		ShippingTotalDTO dto = new ShippingTotalDTO();
		// 获得Package Total.
		Integer packageTotal = this.quotePackageDao.getQuotePackageCount(
				quoteNo).intValue();
		dto.setPackageTotal(packageTotal);
		// 获得Weight Total;
		Double billableWeightTotal = this.quotePackageDao
				.getQuotePackageWeight(quoteNo);
		dto.setBillableWeightTotal(billableWeightTotal);
		// 获得Cost Total;
		Double costTotal = this.quotePackageDao.getQuotePackageCost(quoteNo);
		QuoteMain quote = this.quoteDao.get(quoteNo);
		BigDecimal zero = new BigDecimal(0).setScale(2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal bigCost = zero;
		BigDecimal rateBD = null;
		if (quote != null && quote.getExchRateDate() != null) {
			Double rate = exchRateByDateDao.getCurrencyRate("USD",
					quote.getQuoteCurrency(), quote.getExchRateDate());
			rateBD = new BigDecimal(rate);
			bigCost = new BigDecimal(costTotal).multiply(rateBD).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		}
		dto.setCostTotal(bigCost.doubleValue());
		dto.setCurrency(quote.getQuoteCurrency());
		PbCurrency pbCurrency = this.currencyDao.findUniqueBy("currencyCode",
				quote.getQuoteCurrency());
		if (pbCurrency != null) {
			dto.setCurrencySymbol(pbCurrency.getSymbol());
		}
		// 获得Zone;
		String zone = null;
		boolean bOnlyZone = false;
		QuotePackage firstPackage = this.quotePackageDao
				.getQuoteFirstPackage(quoteNo);
		if (firstPackage == null) {
			return dto;
		}
		if (packageTotal.intValue() == 1) {// 只有一个Package
			bOnlyZone = true;
		} else {
			Long zoneDistinctCount = this.quotePackageDao
					.getQuotePackageZoneCount(quoteNo);
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
		if (packageTotal.intValue() < 1) {// 该Quote还没有package.
			Customer customer = this.customerDao.getById(this.quoteDao.getById(
					quoteNo).getCustNo());
			shipVia = customer.getPrefShipMthd();
		} else {
			shipMethodDistinctCount = this.quotePackageDao
					.getQuotePackageShipMethodCount(quoteNo);
			if (shipMethodDistinctCount.intValue() == 1) {// 如果该Quote的多种Package的shipMethod为同一种.
				shipVia = firstPackage.getShipMethod();
			}
		}
		dto.setShipVia(shipVia);

		// 获得ShipAccountFlag.
		boolean shipAccountFlag = false;
		// 该Quote的package数小于等于1 其carrier肯定是一致的.
		if (packageTotal.intValue() <= 1) {
			shipAccountFlag = true;//
		} else {
			if (shipMethodDistinctCount == null) {
				shipMethodDistinctCount = this.quotePackageDao
						.getQuotePackageShipMethodCount(quoteNo);
			}
			// 虽然有多个Package但只有一种shipMethod, 其carrier肯定是一致的.
			if (shipMethodDistinctCount.intValue() == 1) {
				shipAccountFlag = true;
			}
		}
		if (!shipAccountFlag) {
			List<Integer> shipMethodList = this.quotePackageDao
					.getQuoteShipMethodList(quoteNo);
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

	@Transactional(readOnly = true)
	public List<QuoteTemplate> getTemplateList(final Integer userNo) {
		return quoteTemplateDao.findBy("owner", userNo);
	}

	/**
	 * 获得新的地址的newAddrId. 如果原addrId为空则直接返回null, 如果原addrId在addrIdMap里找到相应的key,
	 * 则返回该Key对应的value即newAddrId， 否则新增一条并返回其主健.
	 * 
	 * @param dbAddrId
	 *            原数据库里的addrId.
	 * @param newQuoteNo
	 *            新产生的Order的OrderNo.
	 * @param userId
	 * @param addrIdMap
	 *            <suffix+原数据库addrId, newAddrId>,
	 *            用于解决Quote及QuoteItem之间共享某个address.
	 * @return
	 */
	private Integer getNewQuoteAddress(Integer dbAddrId, Integer newQuoteNo,
			Integer userId, Map<String, Integer> addrIdMap) {
		Integer newAddrId = null;
		String suffix = "addrId";
		if (dbAddrId != null) {
			if (addrIdMap.containsKey(suffix + dbAddrId)) {
				return addrIdMap.get(suffix + dbAddrId);
			}
			QuoteAddress shipToAddr = this.quoteAddressDao.getById(dbAddrId);
			if (shipToAddr != null) {
				this.quoteAddressDao.getSession().evict(shipToAddr);
				shipToAddr.setAddrId(null);
				shipToAddr.setQuoteNo(newQuoteNo);
				shipToAddr.setCreatedBy(userId);
				shipToAddr.setModifiedBy(userId);
				shipToAddr.setCreationDate(new Date());
				shipToAddr.setModifyDate(new Date());
				this.quoteAddressDao.save(shipToAddr);
				newAddrId = shipToAddr.getAddrId();
				addrIdMap.put(suffix + dbAddrId, newAddrId);
			}
		}
		return newAddrId;
	}

	/**
	 * 用于对一个Quote进行repeat生成一个供客户端使用的临时quoteDTO. modify by Zhang Yong
	 * 
	 * @param srcQuoteNo
	 * @return
	 */
	public Integer repeatQuote(Integer srcQuoteNo, Integer userId) {
		QuoteMain quote = quoteDao.getById(srcQuoteNo);
		List<QuoteItem> dbItemList = quoteItemDao.getItemListForRepeat(srcQuoteNo);
		String repeatCurrency = quote.getQuoteCurrency();
		if (dbItemList == null || dbItemList.isEmpty()) {
			return null;
		}
		quoteDao.getSession().evict(quote);
		// Copy QuoteMain.
		QuoteMain dbQuote = quote;
		dbQuote.setQuoteNo(null);
		dbQuote.setOrderNo(null);
		dbQuote.setQuoteDate(null);
		dbQuote.setStatus(OrderStatusType.NW.value());
		dbQuote.setModifiedBy(userId);
		dbQuote.setQuoteDate(new Date());
		dbQuote.setRefQuoteNo(srcQuoteNo.toString());
		this.saveOrUpdateQuote(dbQuote, dbQuote.getStatus());
		dbQuote.setQuoteCurrency(repeatCurrency);
		// Copy QuoteMainAddress.
		Integer newQuoteNo = dbQuote.getQuoteNo();
		Map<String, Integer> addrIdMap = new HashMap<String, Integer>();
		Integer newShipAddrId = this.getNewQuoteAddress(dbQuote.getShiptoAddrId(), newQuoteNo, userId, addrIdMap);
		Integer newSoldAddrId = this.getNewQuoteAddress(dbQuote.getSoldtoAddrId(), newQuoteNo, userId, addrIdMap);
		Integer newBillAddrId = this.getNewQuoteAddress(dbQuote.getBilltoAddrId(), newQuoteNo, userId, addrIdMap);
		if (newShipAddrId != null || newSoldAddrId != null || newBillAddrId != null) {
			dbQuote.setShiptoAddrId(newShipAddrId);
			dbQuote.setSoldtoAddrId(newSoldAddrId);
			dbQuote.setBilltoAddrId(newBillAddrId);
			this.quoteDao.save(dbQuote);
		}
		// Copy QuoteItems and quoteItem's Address.
		List<QuoteItem> newItemList = this.repeatQuoteItem(newQuoteNo, dbItemList, addrIdMap, userId);
		// Date now = new Date();
		// repeatServiceItem(userId, now, newItemList, srcQuoteNo, newQuoteNo);
		// Copy notes
		List<QuoteNote> shpList = this.quoteNoteDao.getQuoteNoteList(srcQuoteNo, QuoteInstructionType.SHIPMENT);
		List<QuoteNote> pdtList = this.quoteNoteDao.getQuoteNoteList(srcQuoteNo, QuoteInstructionType.PRODUCTION);
		List<QuoteNote> accountList = this.quoteNoteDao.getQuoteNoteList(srcQuoteNo, QuoteInstructionType.ACCOUNTING);
		List<QuoteNote> saleNotesList = this.quoteNoteDao.getQuoteNoteList(srcQuoteNo, QuoteInstructionType.SALES_NOTES);
		List<QuoteNote> crossSaleNotesList = this.quoteNoteDao.getQuoteNoteList(srcQuoteNo, QuoteInstructionType.CROSS_SELLING);
		this.repeatQuoteInstruction(newQuoteNo, shpList, userId);
		this.repeatQuoteInstruction(newQuoteNo, pdtList, userId);
		this.repeatQuoteInstruction(newQuoteNo, accountList, userId);
		this.repeatQuoteInstruction(newQuoteNo, saleNotesList, userId);
		this.repeatQuoteInstruction(newQuoteNo, crossSaleNotesList, userId);
		// Copy shipping information.
		try {
			if (newItemList != null && newItemList.size() > 0) {
				List<QuoteItemDTO> itemDTOList = this.getItemList(quote, newItemList);
				this.quoteShippingService.calShippingPackage(itemDTOList, newQuoteNo, userId, 
						dbQuote.getGsCoId(), quote.getWarehouseId());
			}
		} catch (Exception ex) {
			System.out.println("Auto calculate shipping package failure!");
		}
		return newQuoteNo;
	}

	/**
	 * 用于对一个Quote进行repeat生成一个供客户端使用的临时quoteDTO.
	 * 
	 * @param srcQuoteNo
	 * @return
	 */
	public Integer repeatQuoteTwo(Integer srcQuoteNo, Integer userId) {
		QuoteMain quote = quoteDao.getById(srcQuoteNo);
		quoteDao.getSession().evict(quote);
		// Copy QuoteMain.
		QuoteMain dbQuote = quote;
		dbQuote.setQuoteNo(null);
		dbQuote.setOrderNo(null);
		dbQuote.setQuoteDate(null);
		dbQuote.setStatus(QuoteStatusType.NW.value());
		dbQuote.setModifiedBy(userId);
		dbQuote.setQuoteDate(new Date());
		dbQuote.setRefQuoteNo(srcQuoteNo.toString());
		this.saveOrUpdateQuote(dbQuote, dbQuote.getStatus());
		// Copy QuoteMainAddress.
		Integer newQuoteNo = dbQuote.getQuoteNo();
		Map<String, Integer> addrIdMap = new HashMap<String, Integer>();
		Integer newShipAddrId = this.getNewQuoteAddress(
				dbQuote.getShiptoAddrId(), newQuoteNo, userId, addrIdMap);
		Integer newSoldAddrId = this.getNewQuoteAddress(
				dbQuote.getSoldtoAddrId(), newQuoteNo, userId, addrIdMap);
		Integer newBillAddrId = this.getNewQuoteAddress(
				dbQuote.getBilltoAddrId(), newQuoteNo, userId, addrIdMap);
		if (newShipAddrId != null || newSoldAddrId != null
				|| newBillAddrId != null) {
			dbQuote.setShiptoAddrId(newShipAddrId);
			dbQuote.setSoldtoAddrId(newSoldAddrId);
			dbQuote.setBilltoAddrId(newBillAddrId);
			this.quoteDao.save(dbQuote);
		}

		// Copy QuoteItems and quoteItem's Address.
		List<QuoteItem> dbItemList = quoteItemDao
				.getItemListForRepeat(srcQuoteNo);
		List<QuoteItem> newItemList = this.repeatQuoteItem(newQuoteNo,
				dbItemList, addrIdMap, userId);

		// Copy notes
		List<QuoteNote> shpList = this.quoteNoteDao.getQuoteNoteList(
				srcQuoteNo, QuoteInstructionType.SHIPMENT);
		List<QuoteNote> pdtList = this.quoteNoteDao.getQuoteNoteList(
				srcQuoteNo, QuoteInstructionType.PRODUCTION);
		List<QuoteNote> accountList = this.quoteNoteDao.getQuoteNoteList(
				srcQuoteNo, QuoteInstructionType.ACCOUNTING);
		List<QuoteNote> saleNotesList = this.quoteNoteDao.getQuoteNoteList(
				srcQuoteNo, QuoteInstructionType.SALES_NOTES);
		List<QuoteNote> crossSaleNotesList = this.quoteNoteDao
				.getQuoteNoteList(srcQuoteNo,
						QuoteInstructionType.CROSS_SELLING);
		this.repeatQuoteInstruction(newQuoteNo, shpList, userId);
		this.repeatQuoteInstruction(newQuoteNo, pdtList, userId);
		this.repeatQuoteInstruction(newQuoteNo, accountList, userId);
		this.repeatQuoteInstruction(newQuoteNo, saleNotesList, userId);
		this.repeatQuoteInstruction(newQuoteNo, crossSaleNotesList, userId);
		// Copy shipping information.
		try {
			if (newItemList != null && newItemList.size() > 0) {
				List<QuoteItemDTO> itemDTOList = this.getItemList(quote,
						newItemList);
				this.quoteShippingService.calShippingPackage(itemDTOList,
						newQuoteNo, userId, dbQuote.getGsCoId(),
						quote.getWarehouseId());
			}
		} catch (Exception ex) {
			System.out.println("Auto calculate shipping package failure!");
		}
		return newQuoteNo;
	}

	/**
	 * Copy QuoteNotes and QuoteMails to the New Quote.
	 * 
	 * @param quoteNo
	 * @param noteList
	 * @param userId
	 */
	private void repeatQuoteInstruction(Integer quoteNo,
			List<QuoteNote> noteList, Integer userId) {
		if (noteList == null || noteList.isEmpty()) {
			return;
		}
		Date now = new Date();
		for (QuoteNote note : noteList) {
			Integer originId = note.getId();
			this.quoteNoteDao.getSession().evict(note);
			note.setId(null);
			note.setNoteDate(now);
			note.setQuoteNo(quoteNo);
			note.setCreatedBy(userId);
			note.setModifiedBy(userId);
			note.setCreationDate(now);
			note.setModifyDate(now);
			this.quoteNoteDao.save(note);
			List<Document> docList = this.documentDao.getDocumentList(originId,
					DocumentType.QUOTE_INST_NOTE);
			if (docList != null && docList.size() > 0) {
				for (Document doc : docList) {
					this.documentDao.getSession().evict(doc);
					doc.setDocId(null);
					// 更新docName
					String name = doc.getDocName();
					int index = name.lastIndexOf(".");
					name = name.substring(0, index) + "_" + quoteNo
							+ name.substring(index);
					doc.setDocName(name);
					// 更新filePath
					String filePath = doc.getFilePath();
					index = filePath.lastIndexOf(".");
					filePath = filePath.substring(0, index) + "_" + quoteNo
							+ filePath.substring(index);
					doc.setFilePath(filePath);
					doc.setCreatedBy(userId);
					doc.setModifiedBy(userId);
					doc.setCreationDate(now);
					doc.setModifyDate(now);
					doc.setRefId(note.getId());
					doc.setRefType(DocumentType.QUOTE_INST_NOTE.value());
					this.documentDao.save(doc);
				}
			}
		}
	}

	/**
	 * 2010-01-13 by wangsf repeat quote item时原来item的status是什么，
	 * 新repeat出来的就是什么status, 而不是以前通过库存计算得来的.
	 */
	/**
	 * Copy QuoteItem list to the New Quote.
	 * 
	 * @param newQuoteNo
	 * @param dbItemList
	 * @param addrIdMap
	 * @param userId
	 */
	private List<QuoteItem> repeatQuoteItem(Integer newQuoteNo,
			List<QuoteItem> dbItemList, Map<String, Integer> addrIdMap,
			Integer userId) {
		List<QuoteItem> retItemList = null;
		Map<Integer, Integer> itemIdMap = new HashMap<Integer, Integer>();
		if (dbItemList != null) {
			int seq = 1;
			retItemList = new ArrayList<QuoteItem>();
			for (QuoteItem dbItem : dbItemList) {
				Integer dbItemId = dbItem.getQuoteItemId();
				this.orderItemDao.getSession().evict(dbItem);
				QuoteItem item = dozer.map(dbItem, QuoteItem.class);
				item.setDiscount(0.0);
				item.setStatus(dbItem.getStatus());
				item.setQuoteItemId(null);
				item.setQuoteNo(newQuoteNo);
				item.setItemNo(seq);
				item.setCreatedBy(userId);
				item.setModifiedBy(userId);
				item.setCreationDate(new Date());
				item.setModifyDate(new Date());
				this.quoteItemDao.save(item);
				// 以下用于关联地址.
				Integer itemShipAddrId = this.getNewQuoteAddress(item.getShiptoAddrId(), newQuoteNo, userId, addrIdMap);
				Integer itemSoldAddrId = this.getNewQuoteAddress(item.getSoldtoAddrId(), newQuoteNo, userId, addrIdMap);
				Integer itemBillAddrId = this.getNewQuoteAddress(item.getBilltoAddrId(), newQuoteNo, userId, addrIdMap);
				if (itemShipAddrId != null || itemSoldAddrId != null || itemBillAddrId != null) {
					item.setShiptoAddrId(itemShipAddrId);
					item.setSoldtoAddrId(itemSoldAddrId);
					item.setBilltoAddrId(itemBillAddrId);
					this.quoteItemDao.save(item);// 关联地址后重新保存.
				}
				itemIdMap.put(dbItemId, item.getQuoteItemId());
				Date now = new Date();
				// 关联Service部分信息.
				this.repeatItemMoreDetail(dbItem, newQuoteNo, item.getQuoteItemId(), userId, now);

				seq++;
				retItemList.add(item);
				// 记录QuoteItem status history.
				QuoteProcessLog itemLog = new QuoteProcessLog();
				itemLog.setPriorStat(null);
				itemLog.setCurrentStat(item.getStatus());
				itemLog.setNote(null);
				itemLog.setProcessDate(new Date());
				if (userId != null) {
					itemLog.setProcessedBy(userDao.getById(userId));
				}
				itemLog.setQuoteNo(newQuoteNo);
				itemLog.setReason("For Repeat");
				quoteProcessLogDao.save(itemLog);
			}
			// 更新parentId
			for (QuoteItem newItem : retItemList) {
				if (newItem.getParentId() != null) {
					Integer newParentId = itemIdMap.get(newItem.getParentId());
					newItem.setParentId(newParentId);
					quoteItemDao.save(newItem);
					quoteItemDao.getSession().flush();
				}
			}
		}
		return retItemList;
	}

	/**
	 * Repeat Item时关联其service及document list.
	 * 
	 * @param dbItem
	 * @param newQuoteNo
	 * @param newItemId
	 * @param userId
	 */
	private void repeatItemMoreDetail(final QuoteItem dbItem,
			final Integer newQuoteNo, final Integer newItemId,
			final Integer userId, final Date now) {
		if (dbItem == null || dbItem.getClsId() == null) {
			return;
		}
		Integer clsId = dbItem.getClsId();
		Integer quoteItemId = dbItem.getQuoteItemId();
		List<Document> docList = null;
		if (clsId.intValue() == -1 || clsId.intValue() == 29 || clsId.intValue() == 35 
				|| clsId.intValue() == 36 || clsId.intValue() == 37 || clsId.intValue() == 38
				|| clsId == 39 || clsId.intValue() == 42) {
			com.genscript.gsscm.quote.entity.QuoteService qService = quoteServiceDao.getById(quoteItemId);
			if (qService != null) {
				quoteServiceDao.getSession().evict(qService);
				qService.setQuoteItemId(newItemId);
				qService.setQuoteNo(newQuoteNo);
				qService.setCreatedBy(userId);
				qService.setModifiedBy(userId);
				qService.setCreationDate(now);
				qService.setModifyDate(now);
				quoteServiceDao.save(qService);
			}
		} else if (clsId == 1 || clsId == 30 || clsId == 31) {
			QuotePeptide quotePeptide = this.quotePeptideDao.getById(quoteItemId);
			if (quotePeptide != null) {
				quotePeptideDao.getSession().evict(quotePeptide);
				quotePeptide.setQuoteItemId(newItemId);
				quotePeptide.setQuoteNo(newQuoteNo);
				quotePeptide.setCreatedBy(userId);
				quotePeptide.setModifiedBy(userId);
				quotePeptide.setCreationDate(now);
				quotePeptide.setModifyDate(now);
				quotePeptideDao.save(quotePeptide);
			}
		} else if (clsId == 2 || clsId == 13 || clsId == 14 || clsId == 15
				|| clsId == 16 || clsId == 17 || clsId == 18 || clsId == 19
				|| clsId == 32 || clsId == 33) {
			QuotePkgService quotePkgService = this.quotePkgServiceDao.getById(quoteItemId);
			if (quotePkgService != null) {
				quotePkgServiceDao.getSession().evict(quotePkgService);
				quotePkgService.setQuoteItemId(newItemId);
				quotePkgService.setQuoteNo(newQuoteNo);
				quotePkgService.setCreatedBy(userId);
				quotePkgService.setModifiedBy(userId);
				quotePkgService.setCreationDate(now);
				quotePkgService.setModifyDate(now);
				quotePkgServiceDao.save(quotePkgService);
			}
		} else if (clsId == 3) {
			QuoteGeneSynthesis quoteGeneSynthesis = quoteGeneSynthesisDao.getById(quoteItemId);
			if (quoteGeneSynthesis != null) {
				quoteGeneSynthesisDao.getSession().evict(quoteGeneSynthesis);
				quoteGeneSynthesis.setQuoteItemId(newItemId);
				quoteGeneSynthesis.setQuoteNo(newQuoteNo);
				quoteGeneSynthesis.setCreatedBy(userId);
				quoteGeneSynthesis.setModifiedBy(userId);
				quoteGeneSynthesis.setCreationDate(now);
				quoteGeneSynthesis.setModifyDate(now);
				quoteGeneSynthesisDao.save(quoteGeneSynthesis);
				DocumentType[] docTypeList = { DocumentType.QIM_GENE };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 4) {
			QuoteMutagenesis quoteMutagenesis = this.quoteMutagenesisDao.getById(quoteItemId);
			if (quoteMutagenesis != null) {
				quoteMutagenesisDao.getSession().evict(quoteMutagenesis);
				quoteMutagenesis.setQuoteItemId(newItemId);
				quoteMutagenesis.setQuoteNo(newQuoteNo);
				quoteMutagenesis.setCreatedBy(userId);
				quoteMutagenesis.setModifiedBy(userId);
				quoteMutagenesis.setCreationDate(now);
				quoteMutagenesis.setModifyDate(now);
				quoteMutagenesisDao.save(quoteMutagenesis);
				DocumentType[] docTypeList = { DocumentType.QIM_MUTA, DocumentType.QIM_MUTA_SELF };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 5) {
			// quote_mutation_libraries
			QuoteMutationLibraries mutaLib = this.quoteMutationLibrariesDao.getById(quoteItemId);
			if (mutaLib != null) {
				quoteMutationLibrariesDao.getSession().evict(mutaLib);
				mutaLib.setQuoteItemId(newItemId);
				mutaLib.setQuoteNo(newQuoteNo);
				mutaLib.setCreatedBy(userId);
				mutaLib.setModifiedBy(userId);
				mutaLib.setCreationDate(now);
				mutaLib.setModifyDate(now);
				quoteMutationLibrariesDao.save(mutaLib);
				DocumentType[] docTypeList = { DocumentType.QIM_MUTALIB, DocumentType.QIM_MUTALIB_SELF };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 6) {
			QuoteOrfClone quoteOrfClone = this.quoteOrfCloneDao.getById(quoteItemId);
			if (quoteOrfClone != null) {
				quoteOrfCloneDao.getSession().evict(quoteOrfClone);
				quoteOrfClone.setQuoteItemId(newItemId);
				quoteOrfClone.setQuoteNo(newQuoteNo);
				quoteOrfClone.setCreatedBy(userId);
				quoteOrfClone.setModifiedBy(userId);
				quoteOrfClone.setCreationDate(now);
				quoteOrfClone.setModifyDate(now);
				quoteOrfCloneDao.save(quoteOrfClone);
				DocumentType[] docTypeList = { DocumentType.QIM_ORFCLONE };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 7 || clsId == 8) {
			QuoteSirnaAndMirna quoteSirnaAndMirna = this.quoteSirnaAndMirnaDao.getById(quoteItemId);
			if (quoteSirnaAndMirna != null) {
				quoteSirnaAndMirnaDao.getSession().evict(quoteSirnaAndMirna);
				quoteSirnaAndMirna.setQuoteItemId(newItemId);
				quoteSirnaAndMirna.setQuoteNo(newQuoteNo);
				quoteSirnaAndMirna.setCreatedBy(userId);
				quoteSirnaAndMirna.setModifiedBy(userId);
				quoteSirnaAndMirna.setCreationDate(now);
				quoteSirnaAndMirna.setModifyDate(now);
				quoteSirnaAndMirnaDao.save(quoteSirnaAndMirna);
				DocumentType[] docTypeList = { DocumentType.QIM_RNA };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 9) {
			QuoteCustCloning quoteCustCloning = quoteCustCloningDao.getById(quoteItemId);
			if (quoteCustCloning != null) {
				quoteCustCloningDao.getSession().evict(quoteCustCloning);
				quoteCustCloning.setQuoteItemId(newItemId);
				quoteCustCloning.setQuoteNo(newQuoteNo);
				quoteCustCloning.setCreatedBy(userId);
				quoteCustCloning.setModifiedBy(userId);
				quoteCustCloning.setCreationDate(now);
				quoteCustCloning.setModifyDate(now);
				quoteCustCloningDao.save(quoteCustCloning);
				DocumentType[] docTypeList = { DocumentType.QIM_CUSTCLONING, DocumentType.QIM_CUSTCLONING_CS,
						DocumentType.QIM_CUSTCLONING_SELF };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 10) {
			QuotePlasmidPreparation quotePlasmidPreparation = quotePlasmidPreparationDao.getById(quoteItemId);
			if (quotePlasmidPreparation != null) {
				quotePlasmidPreparationDao.getSession().evict(quotePlasmidPreparation);
				quotePlasmidPreparation.setQuoteItemId(newItemId);
				quotePlasmidPreparation.setQuoteNo(newQuoteNo);
				quotePlasmidPreparation.setCreatedBy(userId);
				quotePlasmidPreparation.setModifiedBy(userId);
				quotePlasmidPreparation.setCreationDate(now);
				quotePlasmidPreparation.setModifyDate(now);
				quotePlasmidPreparationDao.save(quotePlasmidPreparation);
				DocumentType[] docTypeList = { DocumentType.QIM_PLASMID, DocumentType.QIM_PLASMID_SELF };
				docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
			}
		} else if (clsId == 11 || clsId == 12 || clsId == 22 || clsId == 28) {
			QuoteAntibody quoteAntibody = this.quoteAntibodyDao.getById(quoteItemId);
			if (quoteAntibody != null) {
				quoteAntibodyDao.getSession().evict(quoteAntibody);
				quoteAntibody.setQuoteItemId(newItemId);
				quoteAntibody.setQuoteNo(newQuoteNo);
				quoteAntibody.setCreatedBy(userId);
				quoteAntibody.setModifiedBy(userId);
				quoteAntibody.setCreationDate(now);
				quoteAntibody.setModifyDate(now);
				quoteAntibodyDao.save(quoteAntibody);
			}
		} else if (clsId == 34) {
			QuoteOligo quoteOligo = this.quoteOligoDao.getById(quoteItemId);
			if (quoteOligo != null) {
				quoteOligoDao.getSession().evict(quoteOligo);
				quoteOligo.setQuoteItemId(newItemId);
				quoteOligo.setQuoteNo(newQuoteNo);
				quoteOligo.setCreatedBy(userId);
				quoteOligo.setModifiedBy(userId);
				quoteOligo.setCreationDate(now);
				quoteOligo.setModifyDate(now);
				quoteOligoDao.save(quoteOligo);
			}
		} else if (clsId == 40) {
			QuoteDnaSequencing dnaSeq = this.quoteDnaSequencingDao.getById(quoteItemId);
			if (dnaSeq != null) {
				quoteDnaSequencingDao.getSession().evict(dnaSeq);
				dnaSeq.setQuoteItemId(newItemId);
				dnaSeq.setQuoteNo(newQuoteNo);
				dnaSeq.setCreatedBy(userId);
				dnaSeq.setModifiedBy(userId);
				dnaSeq.setCreationDate(now);
				dnaSeq.setModifyDate(now);
				quoteDnaSequencingDao.save(dnaSeq);
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
	 * @param newQuoteItemId
	 * @param dbDocList
	 * @param loginUserId
	 */
	private void copyDocsForRepeat(Integer newQuoteItemId,
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
						+ FilePathConstant.quoteService.value();
				String shortName = SessionUtil.generateTempId() + "_"
						+ dbDoc.getDocName();
				File tagFile = new File(tagDir + "/" + shortName);
				// 拷贝文件
				FileUtils.copyFile(srcFile, tagFile);

				doc.setDocId(null);
				doc.setRefId(newQuoteItemId);
				doc.setFilePath(FilePathConstant.quoteService.value() + "/"
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

	// 取得一个item服务相关的所有服务，即，子服务的主服务和所有的子服务
	public void setItemAllMoreDetail(Map<String, QuoteItemDTO> itemMap, QuoteItemDTO quoteItem) {
		// 设置一个HashMap，用来存取Mutagensis的item，因为Mutagensis的作为gene的下级，有可能还有下级的item
		List<QuoteItemDTO> mutaGensisList = new ArrayList<QuoteItemDTO>();
		setItemMoreDetailByClsId(quoteItem);
		String parentId = quoteItem.getParentId();
		if (4 == quoteItem.getClsId()) {
			mutaGensisList.add(quoteItem);
		}
		if (parentId != null && !"".equals(parentId)) {
			Iterator<QuoteItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				QuoteItemDTO dto = it.next();
				// 子服务
				if (quoteItem.getParentId().equals(dto.getParentId()) && quoteItem.getItemNo().intValue() != dto.getItemNo().intValue()) {
					if (4 == dto.getClsId()) {
						mutaGensisList.add(dto);
					}
					setItemMoreDetailByClsId(dto);
				} else if (quoteItem.getParentId().equals("" + dto.getQuoteItemId())) {// 主服务
					setItemMoreDetailByClsId(dto);
				}
			}
			// 处理DNA Sequencing add by Zhang Yong
		} else if (StringUtils.isBlank(parentId)
				&& quoteItem.getClsId() != null
				&& 40 == quoteItem.getClsId().intValue()) {
			Iterator<QuoteItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				QuoteItemDTO dto = it.next();
				if (40 == dto.getClsId().intValue() && dto.getDnaSequencing() == null) {
					setItemMoreDetailByClsId(dto);
				}
			}
		} else {
			Iterator<QuoteItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				QuoteItemDTO dto = it.next();
				/*
				 * orderItem.getOrderItemId()
				 * 为空在时候是新增尚未保存到数据库中的item，所以无需取这种item的moreDetail
				 */
				if (dto.getParentId() != null
						&& dto.getParentId().equals(
								"" + quoteItem.getQuoteItemId())) {
					setItemMoreDetailByClsId(dto);
				}
			}
		}
		// 处理mutagensis
		for (QuoteItemDTO mutaDTO : mutaGensisList) {
			Iterator<QuoteItemDTO> it = itemMap.values().iterator();
			while (it.hasNext()) {
				QuoteItemDTO dto = it.next();
				if (String.valueOf(mutaDTO.getQuoteItemId()).equals(dto.getParentId()) && (mutaDTO.getItemNo().intValue() != dto.getItemNo().intValue())) {
					setItemMoreDetailByClsId(dto);
				}
			}
		}
	}

	public void setItemMoreDetailByClsId(QuoteItemDTO quoteItem) {
		// modify by zhanghuibin 如果此Order有相应的服务，则不再取值
		Integer clsId = quoteItem.getClsId();
		if ((clsId == 1 || clsId == 30 || clsId == 31)
				&& quoteItem.getPeptide() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if ((clsId == 2 || clsId == 13 || clsId == 14 || clsId == 15
				|| clsId == 16 || clsId == 17 || clsId == 18 || clsId == 19
				|| clsId == 32 || clsId == 33)
				&& quoteItem.getQuotePkgService() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 3 && quoteItem.getGeneSynthesis() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 4 && quoteItem.getMutagenesis() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 5 && quoteItem.getMutationLibraries() == null) {
			// quote_mutation_libraries
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 6 && quoteItem.getOrfClone() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if ((clsId == 7 || clsId == 8) && quoteItem.getRna() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 9 && quoteItem.getCustCloning() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 10 && quoteItem.getPlasmidPreparation() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if ((clsId == 11 || clsId == 12 || clsId == 22 || clsId == 28)
				&& quoteItem.getAntibody() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 29) {
			// order_service
		} else if (clsId == 34 && quoteItem.getOligo() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if ((clsId == -1 || clsId == 35 || clsId == 36 || clsId == 37
				|| clsId == 38 || clsId == 39 || clsId == 42)
				&& quoteItem.getCustomService() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		} else if (clsId == 40 && quoteItem.getDnaSequencing() == null) {
			getItemMoreDetail(null, quoteItem, quoteItem.getQuoteItemId());
		}
	}

	private QuoteItemDTO getItemMoreDetail(List<QuoteItemDTO> itemDTOList, QuoteItemDTO itemDTO, Integer quoteItemId) {
		Integer clsId = itemDTO.getClsId();
		if (clsId == null || clsId == 0 || quoteItemId == null) {
			return itemDTO;
		}
		if (clsId == -1 || clsId == 29 || clsId == 35 || clsId == 36 || clsId == 37
				|| clsId == 38 || clsId == 39 || clsId == 42) {
			com.genscript.gsscm.quote.entity.QuoteService customService = this.quoteServiceDao.getById(quoteItemId);
			if (customService != null && customService.getQuoteItemId() != null) {
				CustomServiceDTO dto = this.dozer.map(customService, CustomServiceDTO.class);
				itemDTO.setCustomService(dto);
			} else {
				itemDTO.setCustomService(new CustomServiceDTO());
			}
		} else if (clsId == 1 || clsId == 30 || clsId == 31) {
			QuotePeptide peptideTemp = this.quotePeptideDao.getById(quoteItemId);
			if (peptideTemp != null && peptideTemp.getQuoteItemId() != null) {
				PeptideDTO peptide = this.dozer.map(peptideTemp, PeptideDTO.class);
				itemDTO.setPeptide(peptide);
			} else {
				itemDTO.setPeptide(new PeptideDTO());
			}
		} else if (clsId == 2 || clsId == 13 || clsId == 14 || clsId == 15 || clsId == 16 || clsId == 17 
				|| clsId == 18 || clsId == 19 || clsId == 32 || clsId == 33) {
			QuotePkgService pkgTmp = this.quotePkgServiceDao.getById(quoteItemId);
			if (pkgTmp != null && pkgTmp.getQuoteItemId() != null) {
				PkgServiceDTO pkgService = this.dozer.map(pkgTmp, PkgServiceDTO.class);
				pkgService.setCatalogNo(itemDTO.getCatalogNo());
				pkgService.setItemName(itemDTO.getName());
				pkgService.setCost(itemDTO.getCost());
				pkgService.setUnitPrice(itemDTO.getUnitPrice());
				pkgService.setUpSymbol(itemDTO.getUpSymbol());
				if (StringUtils.isNotBlank(pkgTmp.getDescription())) {
					pkgService.setDescription(pkgTmp.getDescription() .replaceAll("'", "@@")
							.replaceAll("\"", "~~").replaceAll("\n", "##"));
				}
				itemDTO.setQuotePkgService(pkgService);
			} else {
				itemDTO.setQuotePkgService(new PkgServiceDTO());
			}
		} else if (clsId == 3) {
			QuoteGeneSynthesis geneTemp = quoteGeneSynthesisDao.getById(quoteItemId);
			if (geneTemp != null && geneTemp.getQuoteItemId() != null) {
				OrderGeneSynthesisDTO geneSynthesis = dozer.map(geneTemp, OrderGeneSynthesisDTO.class);
				DocumentType[] docTypeList = { DocumentType.QIM_GENE };
				List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
				geneSynthesis.setDocumentList(docList);
				itemDTO.setGeneSynthesis(geneSynthesis);
				itemDTO.setVirusSeqFlag(geneSynthesis.getVirusSeqFlag());
			} else {
				itemDTO.setGeneSynthesis(new OrderGeneSynthesisDTO());
			}
		} else if (clsId == 4) {
			QuoteMutagenesis mutageneTemp = this.quoteMutagenesisDao.getById(quoteItemId);
			if (mutageneTemp != null && mutageneTemp.getQuoteItemId() != null) {
				OrderMutagenesisDTO mutagene = this.dozer.map(mutageneTemp, OrderMutagenesisDTO.class);
				DocumentType[] docTypeList = { DocumentType.QIM_MUTA, DocumentType.QIM_MUTA_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
				mutagene.setDocumentList(docList);
				itemDTO.setMutagenesis(mutagene);
			} else {
				itemDTO.setMutagenesis(new OrderMutagenesisDTO());
			}
		} else if (clsId == 5) {
			// quote_mutation_libraries
			QuoteMutationLibraries mutaLibTemp = this.quoteMutationLibrariesDao.getById(quoteItemId);
			if (mutaLibTemp != null && mutaLibTemp.getQuoteItemId() != null) {
				OrderMutationLibrariesDTO mutageneLibraries = this.dozer.map(mutaLibTemp, OrderMutationLibrariesDTO.class);
				DocumentType[] docTypeList = { DocumentType.QIM_MUTALIB, DocumentType.QIM_MUTALIB_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
				mutageneLibraries.setDocumentList(docList);
				itemDTO.setMutationLibraries(mutageneLibraries);
			} else {
				itemDTO.setMutationLibraries(new OrderMutationLibrariesDTO());
			}
		} else if (clsId == 6) {
			QuoteOrfClone quoteOrfCloneTemp = this.quoteOrfCloneDao.getById(quoteItemId);
			if (quoteOrfCloneTemp != null && quoteOrfCloneTemp.getQuoteItemId() != null) {
				OrderOrfCloneDTO quoteOrfCloneDto = this.dozer.map(quoteOrfCloneTemp, OrderOrfCloneDTO.class);
				QuoteItem cloneItem = null;
				List<QuoteItem> subItemList = quoteItemDao.getByParentId(quoteItemId);
				if (subItemList != null && !subItemList.isEmpty()) {
					for (QuoteItem subItem : subItemList) {
						if (subItem.getClsId() == 9) {
							cloneItem = subItem;
							break;
						}
					}
				}
				boolean isFullLength = ("1").equals(quoteOrfCloneTemp.getSeqType())?true:false;
				StringBuffer accessionInfo = new StringBuffer();
				accessionInfo.append("accession:"+quoteOrfCloneTemp.getAccessionNo());
				accessionInfo.append("@@@seqType:"+(isFullLength?FullLength:ORFSequence));
				accessionInfo.append("@@@genePrice:"+itemDTO.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if (cloneItem == null) {
					accessionInfo.append("@@@subcloningPrice:0.00@@@geneCost:"+itemDTO.getCost().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"@@@subcloningCost:0.00");
				} else {
					accessionInfo.append("@@@subcloningPrice:"+(new BigDecimal(cloneItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"@@@geneCost:"+itemDTO.getCost().setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"@@@subcloningCost:"+(new BigDecimal(cloneItem.getCost())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				Refseq2orfprice refseq2orfprice = refseq2orfpriceDao.getByAccessionNo(quoteOrfCloneTemp.getAccessionNo().trim());
				if (refseq2orfprice != null) {
					accessionInfo.append("@@@realGenePrice:"+(new BigDecimal(isFullLength?refseq2orfprice.getSyntheticPriceCDNA():refseq2orfprice.getSyntheticPrice())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					accessionInfo.append("@@@realSubCloningPrice:"+(new BigDecimal(isFullLength?(refseq2orfprice.getVectorPriceCDNA()-refseq2orfprice.getSyntheticPriceCDNA()):(refseq2orfprice.getVectorPrice()-refseq2orfprice.getSyntheticPrice()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					accessionInfo.append("@@@realGeneCost:"+(new BigDecimal(isFullLength?refseq2orfprice.getSyntheticCostCDNA():refseq2orfprice.getSyntheticCost())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					accessionInfo.append("@@@realSubcloningCost:"+(new BigDecimal(isFullLength?(refseq2orfprice.getVectorCostCDNA()-refseq2orfprice.getSyntheticCostCDNA()):(refseq2orfprice.getVectorCost()-refseq2orfprice.getSyntheticCost()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				quoteOrfCloneDto.setAccessionInfo(accessionInfo.toString());
				DocumentType[] docTypeList = { DocumentType.QIM_ORFCLONE };
				List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
				quoteOrfCloneDto.setDocumentList(docList);
				itemDTO.setOrfClone(quoteOrfCloneDto);
			} else {
				itemDTO.setOrfClone(new OrderOrfCloneDTO());
			}
		} else if (clsId == 7 || clsId == 8) {
			QuoteRna rnaTemp = this.quoteRnaDao.getById(quoteItemId);
			if (rnaTemp != null && rnaTemp.getQuoteItemId() != null) {
				RnaDTO rna = dozer.map(rnaTemp, RnaDTO.class);
				DocumentType[] docTypeList = { DocumentType.QIM_RNA };
				List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
				rna.setDocumentList(docList);
				itemDTO.setRna(rna);
			} else {
				itemDTO.setRna(new RnaDTO());
			}
		} else if (clsId == 9) {
			QuoteCustCloning custTemp = quoteCustCloningDao.getById(quoteItemId);
			if (custTemp != null && custTemp.getQuoteItemId() != null) {
				OrderCustCloningDTO custCloning = dozer.map(custTemp, OrderCustCloningDTO.class);
				DocumentType[] docTypeList = { DocumentType.QIM_CUSTCLONING, DocumentType.QIM_CUSTCLONING_CS,
						DocumentType.QIM_CUSTCLONING_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList( quoteItemId, docTypeList);
				custCloning.setDocumentList(docList);
				itemDTO.setCustCloning(custCloning);
			} else {
				itemDTO.setCustCloning(new OrderCustCloningDTO());
			}
		} else if (clsId == 10) {
			QuotePlasmidPreparation plasTemp = quotePlasmidPreparationDao.getById(quoteItemId);
			if (plasTemp != null && plasTemp.getQuoteItemId() != null) {
				OrderPlasmidPreparationDTO plasmid = dozer.map(plasTemp, OrderPlasmidPreparationDTO.class);
				DocumentType[] docTypeList = { DocumentType.QIM_PLASMID, DocumentType.QIM_PLASMID_SELF };
				List<Document> docList = orderQuoteUtil.getItemDocList(quoteItemId, docTypeList);
				plasmid.setDocumentList(docList);
				itemDTO.setPlasmidPreparation(plasmid);
			} else {
				itemDTO.setPlasmidPreparation(new OrderPlasmidPreparationDTO());
			}
		} else if (clsId == 11 || clsId == 12 || clsId == 22 || clsId == 28) {
			QuoteAntibody antibodyTemp = this.quoteAntibodyDao.getById(quoteItemId);
			if (antibodyTemp != null && antibodyTemp.getQuoteItemId() != null) {
				AntibodyDTO antibody = this.dozer.map(antibodyTemp, AntibodyDTO.class);
				itemDTO.setAntibody(antibody);
			} else {
				itemDTO.setAntibody(new AntibodyDTO());
			}
		} else if (clsId == 34) {
			QuoteOligo oligoTemp = this.quoteOligoDao.getById(quoteItemId);
			if (oligoTemp != null && oligoTemp.getQuoteItemId() != null) {
				OrderOligoDTO oligo = this.dozer.map(oligoTemp, OrderOligoDTO.class);
				itemDTO.setOligo(oligo);
			} else {
				itemDTO.setOligo(new OrderOligoDTO());
			}
		} else if (clsId == 40) {
			QuoteDnaSequencing quoteDnaSequencing = this.quoteDnaSequencingDao.getById(quoteItemId);
			if (quoteDnaSequencing != null && quoteDnaSequencing.getQuoteItemId() != null) {
				DnaSequencingDTO dto = this.dozer.map(quoteDnaSequencing, DnaSequencingDTO.class);
				if (dto.getPlateId() != null) {
					QuoteDsPlates quoteDsPlate = quoteDsPlatesDao.getById(dto.getPlateId());
					if (quoteDsPlate != null) {
						dto.setSessPlateId(dto.getPlateId() + "");
						dto.setpName(quoteDsPlate.getName());
						dto.setPlateLayout(quoteDsPlate.getLayout());
						dto.setPlateNums(quoteDsPlate.getNums());
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
	 * 批量删除QuoteTemplate.
	 * 
	 * @param tmplIdList
	 */
	public void delQuoteTemplate(List<Integer> tmplIdList) {
		if (tmplIdList != null && !tmplIdList.isEmpty()) {
			this.quoteTemplateDao.delTemplateList(tmplIdList);
		}
	}

	/**
	 * 判断两个Address是否相同.
	 * 
	 * @param srcAddr
	 * @param tagAddr
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isSameShippingAddr(QuoteAddress srcAddr,
			QuoteAddress tagAddr) {
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

	private void delSubQuoteItem(final QuoteMain quote,
			List<Integer> subQuoteItemIdList) {
		if (subQuoteItemIdList != null) {
			for (Integer itemId : subQuoteItemIdList) {
				QuoteItem item = this.quoteItemDao.getById(itemId);
				Integer itemShipAddrId = item.getShiptoAddrId();
				if (item.getType().equals(QuoteItemType.SERVICE.value())) {
					int clsId = item.getClsId();
					if (clsId == 1) {
						this.quotePeptideDao.delete(itemId);
					} else if (clsId == 2) {
						this.quoteProteinDao.delete(itemId);
					} else if (clsId == 2) {
						this.quoteProteinDao.delete(itemId);
					} else if (clsId == 3) {
						this.quoteGeneSynthesisDao.delete(itemId);
					} else if (clsId == 4) {
						this.quoteMutagenesisDao.delete(itemId);
					} else if (clsId == 5) {
						this.quoteMutationLibrariesDao.delete(itemId);
					} else if (clsId == 6) {
						this.quoteOrfCloneDao.delete(itemId);
					} else if (clsId == 7) {
						this.quoteSirnaAndMirnaDao.delete(itemId);
					} else if (clsId == 8) {
						this.quoteSirnaAndMirnaDao.delete(itemId);
					} else if (clsId == 9) {
						this.quoteCustCloningDao.delete(itemId);
					} else if (clsId == 10) {
						this.quotePlasmidPreparationDao.delete(itemId);
					}
					this.quoteItemDao.delete(itemId);
					// 如果该QuoteItem's shiptoAddrId和Quote的shiptoAddrId不一样，
					// 则判断有无其它QuoteItem关联到这个地址，如果没有则删除该QuoteItem关联的地址.
					if (!itemShipAddrId.equals(quote.getShiptoAddrId())) {
						if (this.quoteItemDao.getQuoteItemCountByAddrId(
								quote.getQuoteNo(), itemShipAddrId).intValue() < 1) {
							this.quoteAddressDao.delete(itemShipAddrId);
						}
					}
				}
			}
		}
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
						|| "".equalsIgnoreCase(document.getRefType())) {
					document.setRefType(docType.value());
				}
				this.documentDao.save(document);
			}
		}
		if (delDocIdList != null) {
			this.documentDao.delDocumentList(delDocIdList);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String calGiftItemDiscount(List<QuoteItemDTO> itemList,
			String orderNo, String promotionCode, Integer custNo,
			Integer warehouseId) throws Exception {
		if (promotionCode == null || StringUtils.isEmpty(promotionCode)) {
			return "PromotionCode is null";
		}
		int itemSize = 0;
		Double sum = 0.0;// 所有item的discount的总和
		Promotion promotion = promotionDao.findUniqueBy("prmtCode",
				promotionCode);
		for (QuoteItemDTO item : itemList) {
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
				QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
						SessionConstant.Quote.value(), orderNo);
				if (quote.getQuotePromotion() != null
						&& StringUtils.isNotBlank(quote.getQuotePromotion()
								.getPrmtCode())) {
					return null;
				}
				QuoteItemDTO quoteItemDTO = createQuoteItemDTO(
						promotion.getDiscProd(), custNo,
						promotion.getDiscProdQty(), itemSize, warehouseId);
				if (promotion.getDiscPrice() != null) {
					quoteItemDTO.setUnitPrice(promotion.getDiscPrice());
					quoteItemDTO.setAmount(new BigDecimal(ArithUtil.mul(
							quoteItemDTO.getUnitPrice().doubleValue(), Double
									.valueOf(String.valueOf(quoteItemDTO
											.getQuantity())))));
				} else if (promotion.getSpecialDiscPercent() != null) {
					quoteItemDTO.setDiscount(promotion
							.getSpecialDiscPercent()
							.multiply(quoteItemDTO.getAmount())
							.divide(new BigDecimal(100), 2,
									BigDecimal.ROUND_HALF_UP));
				} else {
					quoteItemDTO.setUnitPrice(new BigDecimal(0));
					quoteItemDTO.setAmount(new BigDecimal(0));
				}
				Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
						.getRow(SessionConstant.QuoteItemList.value(), orderNo);
				itemMap.put(SessionUtil.generateTempId(), quoteItemDTO);
				SessionUtil.updateRow(SessionConstant.QuoteItemList.value(),
						orderNo, itemMap);
				return null;
			} else {
				return "Promotion is not apply!";
			}
		} else {
			return "Promotion is not apply!";
		}
	}

	/**
	 * 获得具体的QuoteMail或QuoteNote
	 * 
	 * @param id
	 *            QuoteMail的主健或QuoteNote的主健
	 * @param instructionType
	 *            具体类型， 不能为空.
	 * @return
	 */
	public InstructionDTO getInstructionDTO(Integer id, String instructionType) {
		InstructionDTO dto = null;
		if (instructionType.equals(QuoteInstructionType.FOLLOWUP_EMAIL.value())) {
			QuoteMail mail = this.quoteMailDao.get(id);
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
					mail.getId(), DocumentType.QUOTE_INST_MAIL);
			dto.setDocumentList(docList);

		} else {
			QuoteNote note = this.quoteNoteDao.get(id);
			dto = dozer.map(note, InstructionDTO.class);
			dto.setInstructionDate(note.getNoteDate());
			User createUser = this.userDao.getById(note.getCreatedBy());
			if (createUser != null) {
				dto.setCreateUser(createUser.getLoginName());
			}
			List<Document> docList = this.documentDao.getDocumentList(
					note.getId(), DocumentType.QUOTE_INST_NOTE);
			dto.setDocumentList(docList);
		}
		return dto;
	}

	/**
	 * 获得Quotation打印对象QuotationPrintDTO
	 * 
	 * @author lizhang
	 * @param quoteNo
	 */
	public QuotationPrintDTO getQuotationPrintDTO(Integer quoteNo)
			throws Exception {
		QuotationPrintDTO quotationPrintDTO = new QuotationPrintDTO();
		boolean isHasItem = false;// quoteNo所对应的quote是否有子item
		int leadTime = 0;
		String quoteType = null;// 保存第一个quoteItem的itemType;
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

		QuoteMain quote = this.quoteDao.getById(quoteNo);
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
		List<QuoteItem> quoteItemList = null;
		if (quote != null) {
			customer = quote.getCustNo() == null ? null : this.customerDao
					.getById(quote.getCustNo());
			currency = quote.getQuoteCurrency() == null ? null
					: this.currencyDao.findUniqueBy("currencyCode",
							quote.getQuoteCurrency());
			userSupport = quote.getTechSupport() == null ? null : this.userDao
					.getById(quote.getTechSupport());
			userSale = quote.getSalesContact() == null ? null : this.userDao
					.getById(quote.getSalesContact());
			// employeeSupport =
			// quote.getTechSupport()==null?null:this.employeeDao.getById(quote.getTechSupport());
			List<String> itemStatus = new ArrayList<String>();
			itemStatus.add("CN");
			quoteItemList = quote.getQuoteNo() == null ? null
					: this.quoteItemDao.getItemListNotInType(quoteNo,
							itemStatus);
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
			quotationPrintDTO.setCompanyName(companyName);
			quotationPrintDTO.setAddress1("860 Centennial Ave.");
			quotationPrintDTO.setAddress2("Piscataway, NJ 08854, USA");
			quotationPrintDTO.setTelephone("Tel:732-885-9188 ext"
					+ (employeeSupport == null ? "" : employeeSupport
							.getPhoneExt()));
			quotationPrintDTO.setFax("Fax:732-210-0262");
		} else {
			companyName = "GenScript";
			quotationPrintDTO.setCompanyName(companyName);
			quotationPrintDTO.setAddress1("Wheelock House, Level 18");
			quotationPrintDTO
					.setAddress2("20 Pedder Street, Central, Hong Kong");
			quotationPrintDTO.setTelephone("Tel:+852-800-964-684 ext"
					+ (employeeSupport == null ? "" : employeeSupport
							.getPhoneExt()));
			quotationPrintDTO.setFax("Fax:+852-3015-1539");
		}
		quotationPrintDTO.setCustEmail(userSupport == null ? "" : userSupport
				.getEmail());
		quotationPrintDTO.setWeb("Web: www.genscript.com");
		Integer tmpShipMethod = 0;
		if (quoteItemList != null && quoteItemList.size() > 0) {
			int i = 0;
			boolean shipViaConsistent = true;
			for (QuoteItem quoteItem : quoteItemList) {
				i++;
				String quoteItemType = null;
				Integer clsId = null;
				if (!isHasItem) {
					if (QuoteItemType.PRODUCT.value().equals(
							quoteItem.getType())) {
						Product product = this.productDao.findUniqueBy(
								"catalogNo", quoteItem.getCatalogNo());
						clsId = product != null ? product.getProductClsId()
								: null;

					} else if (QuoteItemType.SERVICE.value().equals(
							quoteItem.getType())) {
						com.genscript.gsscm.serv.entity.Service service = this.serviceDao
								.findUniqueBy("catalogNo",
										quoteItem.getCatalogNo());
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
							&& quoteItem.getShipMethod() != null
							&& !tmpShipMethod.equals(quoteItem.getShipMethod())) {
						tmpShipMethod = 0;
						shipViaConsistent = false;
					} else {
						tmpShipMethod = quoteItem.getShipMethod();
					}
				}
				// 获取quoteItem类型
				if (QuoteItemType.PRODUCT.value().equals(quoteItem.getType())) {
					ProductClass pdtClass = this.productClassDao
							.getById(quoteItem.getClsId());
					quoteItemType = pdtClass == null ? null : pdtClass
							.getName();
				} else {
					ServiceClass servClass = this.serviceClassDao
							.getById(quoteItem.getClsId());
					quoteItemType = servClass == null ? null : servClass
							.getName();
				}
				if (i == 1) {
					// 以第一个item的type作为quote的type
					quoteType = quoteItemType;
				}
				if (StringUtils.isEmpty(quoteItemType)) {
					continue;
				}
				if (quoteItem.getParentId() != null
						&& quoteItem.getParentId().intValue() != 0) {
					QuoteItem parentItem = this.quoteItemDao.getById(quoteItem
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
					if ((quoteItemType.toLowerCase().equals("peptide")
							&& parentItemType != null && !parentItemType
							.toLowerCase().equals("peptide"))
							|| quoteItemType.toLowerCase().endsWith("step")) {
						continue;
					}
					if ((quoteItemType.toLowerCase().startsWith(
							"custom cloning") || quoteItemType.toLowerCase()
							.startsWith("plasmid preparation"))
							&& parentItemType != null
							&& parentItemType.toLowerCase().startsWith(
									"mutagenesis")) {
						continue;
					}
				}
				if (quoteItem.getShipSchedule() != null
						&& quoteItem.getShipSchedule() > leadTime) {
					leadTime = quoteItem.getShipSchedule();
				}
				tableBody.append("<tr><td align='center'>")
						.append(quoteItem.getQuantity()).append("</td>");
			/* tableBody.append("<td align='center'>")
				 .append(quoteItem.getItemNo()).append("</td>");*/
				
				tableBody.append("<td>"); 

				StringBuffer itemDetail = new StringBuffer();
				StringBuffer itemDesc = new StringBuffer();
				StringBuffer itemSeq = new StringBuffer();
				String quoteItemGene = quoteItem.getName();
				String description = null;
				String descript = null;
				String vector = null;
				String Name = null;
				String Length = null;
				String quanitity = "";
				String cloningSite = "";
				Double unitPriceD = quoteItem.getUnitPrice();
				if (quoteItemType.toLowerCase().startsWith("gene")) {
					QuoteGeneSynthesis quoteGeneSynthesis = quoteGeneSynthesisDao
							.getById(quoteItem.getQuoteItemId());
					quoteItemGene = quoteItemGene
							+ ": "
							+ (quoteGeneSynthesis == null ? null
									: quoteGeneSynthesis.getGeneName());
					description = quoteGeneSynthesis == null ? null
							: quoteGeneSynthesis.getSequence();
					if (quoteGeneSynthesis != null) {
						vector = quoteGeneSynthesis.getStdVectorName();
						if (StringUtils.isEmpty(vector)) {
							vector = "PUC57";
						}

						quanitity = quoteGeneSynthesis.getStdPlasmidWt()
								+ quoteGeneSynthesis.getStdPlasmidWtUom();
						Length = quoteGeneSynthesis != null
								&& quoteGeneSynthesis.getSeqLength() != null ? quoteGeneSynthesis
								.getSeqLength().toString() : "";
						cloningSite = quoteGeneSynthesis != null ? quoteGeneSynthesis
								.getCloningSite() : "";
						itemDesc.append(quoteItemGene);

						itemDesc.append(",Len: ").append(Length)
								.append("bp,Vector: ").append(vector);

						if (!cloningSite.equals("")) {
							itemDesc.append(",Cloning Site:").append(
									cloningSite);
						}

						itemDesc.append(",Quantity: ").append(quanitity);
						if ("D".equals(quoteGeneSynthesis.getCloningFlag())) {
							itemDesc.append(";Direct Cloning: size: ")
									.append(quoteGeneSynthesis != null
											&& quoteGeneSynthesis
													.getVectorSize() != null ? quoteGeneSynthesis
											.getVectorSize() : "")
									.append(",")
									.append(quoteGeneSynthesis != null ? quoteGeneSynthesis
											.getVectorResistance() : "")
									.append(",")
									.append(quoteGeneSynthesis != null ? quoteGeneSynthesis
											.getVectorCopyNo() : "");
						}
					}
				} else if (quoteItemType.toLowerCase().startsWith(
						"custom cloning")) {
					QuoteCustCloning quoteCustCloning = quoteCustCloningDao
							.getById(quoteItem.getQuoteItemId());
					if (quoteItem.getParentId() != null
							&& quoteItem.getParentId().intValue() != 0) {
						QuoteItem parentItem = this.quoteItemDao
								.getById(quoteItem.getParentId());
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
								QuoteGeneSynthesis quoteGeneSynthesis = quoteGeneSynthesisDao
										.getById(parentItem.getQuoteItemId());
								if (quoteGeneSynthesis != null) {
									itemDesc.append("Custom Cloning: ");
									itemDesc.append(
											quoteGeneSynthesis == null ? null
													: quoteGeneSynthesis
															.getGeneName())
											.append("-Subcloning: ");
								//	itemDesc.append("Vector name: ").append(
											itemDesc.append("Vector: ").append(
											quoteCustCloning.getTgtVector());
								}

							}else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"mutation libraries")) {
								QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesDao
										.getById(parentItem.getQuoteItemId());
								if (quoteMutationLibraries != null) {
									itemDesc.append("Custom Cloning: ");
									itemDesc.append(
											quoteMutationLibraries == null ? null
													: quoteMutationLibraries
															.getConstructName())
											.append("-Subcloning: ");
								//	itemDesc.append("Vector name: ").append(
											itemDesc.append("Vector: ").append(
											quoteCustCloning.getTgtVector());
								}
									
							}else {
							
								itemDesc.append(quoteItem.getName())
										.append(":")
										.append(quoteCustCloning
												.getTgtInsertName()==null?"":quoteCustCloning
														.getTgtInsertName())
										.append(",Vector: ");

							}
							itemDesc.append(", Cloning method: ")
									.append(quoteCustCloning
											.getTgtCloningMethod() == null ? ""
											: quoteCustCloning
													.getTgtCloningMethod());
							if (quoteCustCloning != null) {
								itemDesc.append(", Cloning site: ").append(
										quoteCustCloning.getTgtCloningSite());
							}

							if ("N".equals(quoteCustCloning.getCloningFlag())) {
								itemDesc.append(quoteCustCloning.getTgtVector() != null ? "Vector: "
										+ quoteCustCloning.getTgtVector()
										: "");
							} else {
								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								QuoteCustCloning childQuoteCustCloning = null;
								if (itemList != null && !itemList.isEmpty()) {
									for (QuoteItem item : itemList) {
										childQuoteCustCloning = quoteCustCloningDao
												.getById(item.getQuoteItemId());
										if (childQuoteCustCloning != null) {
											break;
										}
									}
									itemDesc.append(childQuoteCustCloning != null ? "Vector: "
											+ childQuoteCustCloning
													.getTgtVector()
											: "");
								}
							}
							if ("N".equals(quoteCustCloning
									.getPlasmidPrepFlag())) {
								itemDesc.append(",Quantity: 4 ug");
								 
							} else {

								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								if (itemList != null && !itemList.isEmpty()) {
									QuotePlasmidPreparation childQuotePlasmidPreparation = null;
									 
									for (QuoteItem item : itemList) {
										childQuotePlasmidPreparation = quotePlasmidPreparationDao
												.getById(item.getQuoteItemId());
										if (childQuotePlasmidPreparation != null) {
											break;
										}
									}
									itemDesc.append(childQuotePlasmidPreparation != null ? (",Quantity: "
											+ childQuotePlasmidPreparation
													.getPrepWeight() + childQuotePlasmidPreparation
											.getPrepWtUom()) : "");
									if(childQuotePlasmidPreparation!=null){
									itemDesc.append(", Grade: ")
											.append(childQuotePlasmidPreparation
													.getQualityGrade() == null ? ""
													: childQuotePlasmidPreparation
															.getQualityGrade())
											.append("<br/>");
									}
								}
							}

						}

					} else {
						itemDesc.append("").append(quoteItemGene).append(": ");
					//	itemDesc.append(" Insert name: ")
							itemDesc.append(" Insert: ")
								.append(quoteCustCloning.getTmplInsertName() == null ? ""
										: quoteCustCloning.getTmplInsertName());
						itemDesc.append(", Cloning method: ")
								.append(quoteCustCloning.getTmplCloningMethod() == null ? ""
										: quoteCustCloning
												.getTmplCloningMethod());
						//itemDesc.append(", Gene Name: ").append(quoteItemGene);
						itemDesc.append(", Gene: ").append(quoteItemGene);
						if (quoteCustCloning != null) {
							itemDesc.append(", Cloning site: ").append(
									quoteCustCloning.getTmplCloningSite());
						}
						itemDesc.append(",Quantity: 4 ug");
					}

				} else if (quoteItemType.toLowerCase().startsWith(
						"plasmid preparation")) {
					QuotePlasmidPreparation quotePlasmidPreparation = quotePlasmidPreparationDao
							.getById(quoteItem.getQuoteItemId());

					if (quoteItem.getParentId() != null
							&& quoteItem.getParentId().intValue() != 0) {
						QuoteItem parentItem = this.quoteItemDao
								.getById(quoteItem.getParentId());
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
								itemDesc.append(" Custom Plasmid Preparation: ");
								itemDesc.append("Quantity: ")
										.append(quotePlasmidPreparation
												.getPrepWeight())
										.append(quotePlasmidPreparation
												.getPrepWtUom());
								itemDesc.append(", Grade: ")
										.append(quotePlasmidPreparation
												.getQualityGrade() == null ? ""
												: quotePlasmidPreparation
														.getQualityGrade())
										.append("");

							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"mutation libraries")) {
								QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesDao
										.getById(parentItem.getQuoteItemId());
								if (quoteMutationLibraries != null) {
								itemDesc.append(quoteMutationLibraries.getConstructName()+"- Custom Plasmid Preparation: ");
								itemDesc.append("Quantity: ")
										.append(quotePlasmidPreparation
												.getPrepWeight())
										.append(quotePlasmidPreparation
												.getPrepWtUom());
								itemDesc.append(", Grade: ")
										.append(quotePlasmidPreparation
												.getQualityGrade() == null ? ""
												: quotePlasmidPreparation
														.getQualityGrade())
										.append("");
								}
									
							}else {
								itemDesc.append(quoteItem.getName())
										.append(":");
								itemDesc.append("Quantity: ")
										.append(quotePlasmidPreparation
												.getPrepWeight())
										.append(quotePlasmidPreparation
												.getPrepWtUom());
								itemDesc.append(", Grade: ")
										.append(quotePlasmidPreparation
												.getQualityGrade() == null ? ""
												: quotePlasmidPreparation
														.getQualityGrade())
										.append("");
							}
						}

					} else {
						itemDesc.append(quoteItem.getName()).append(": ");

						//itemDesc.append("Plasmid name: ")
						itemDesc.append("Plasmid: ")
								.append(quotePlasmidPreparation
										.getPlasmidName() == null ? ""
										: quotePlasmidPreparation
												.getPlasmidName());
						itemDesc.append(", Plasmid Size: ")
								.append(quotePlasmidPreparation
										.getPlasmidSize() == null ? ""
										: quotePlasmidPreparation
												.getPlasmidSize());
						itemDesc.append(", Quantity: ")
								.append(quotePlasmidPreparation.getPrepWeight())
								.append(quotePlasmidPreparation.getPrepWtUom());
						itemDesc.append(", Grade: ")
								.append(quotePlasmidPreparation
										.getQualityGrade() == null ? ""
										: quotePlasmidPreparation
												.getQualityGrade()).append("");
					}
				} else if (quoteItemType.toLowerCase()
						.startsWith("mutagenesis")) {

					if (quoteItem.getParentId() != null
							&& quoteItem.getParentId().intValue() != 0) {
						QuoteItem genItem = this.quoteItemDao.getById(quoteItem
								.getParentId());
						itemDesc = new StringBuffer(
								genItem != null ? genItem.getName() : "")
								.append("-Mutagenesis: ");
						QuoteMutagenesis quoteMutagenesis = quoteMutagenesisDao
								.getById(quoteItem.getQuoteItemId());
						if (quoteMutagenesis == null) {
							continue;
						}
						if ("N".equals(quoteMutagenesis.getCloningFlag())) {
							itemDesc.append(",Vector: pUC57");
						} else {
							List<QuoteItem> itemList = this.quoteItemDao
									.findBy("parentId",
											quoteItem.getQuoteItemId());
							if (itemList != null && !"".equals(itemList)
									&& !itemList.isEmpty()) {
								QuoteCustCloning quoteCustCloning = null;
								for (QuoteItem item : itemList) {
									quoteCustCloning = quoteCustCloningDao
											.getById(item.getQuoteItemId());
									if (quoteCustCloning != null) {
										break;
									}
								}
								itemDesc.append(quoteCustCloning != null ? ",Vector: "
										+ quoteCustCloning.getTgtVector()
										: "");
							}
						}
						if ("N".equals(quoteMutagenesis.getPlasmidPrepFlag())) {
							itemDesc.append(",Quantity: 4 ug");
						} else {
							List<QuoteItem> itemList = this.quoteItemDao
									.findBy("parentId",
											quoteItem.getQuoteItemId());
							if (itemList != null && !"".equals(itemList)
									&& !itemList.isEmpty()) {
								QuotePlasmidPreparation quotePlasmidPreparation = null;
								for (QuoteItem item : itemList) {
									quotePlasmidPreparation = quotePlasmidPreparationDao
											.getById(item.getQuoteItemId());
									if (quotePlasmidPreparation != null) {
										break;
									}
								}
								itemDesc.append(quotePlasmidPreparation != null ? (",Quantity: "
										+ quotePlasmidPreparation
												.getPrepWeight() + quotePlasmidPreparation
										.getPrepWtUom()) : "");
							}
						}
					} else {
						QuoteMutagenesis quoteMutagenesis = quoteMutagenesisDao
								.getById(quoteItem.getQuoteItemId());
						if (quoteMutagenesis != null
								&& !"".equals(quoteMutagenesis)) {
							itemDesc.append(quoteItem.getName()).append(": ");
							//itemDesc.append("Insert name: ")
							itemDesc.append("Insert: ")
									.append(quoteMutagenesis
											.getTmplInsertName() == null ? ""
											: quoteMutagenesis
													.getTmplInsertName());
							itemDesc.append(", Cloning method: ")
									.append(quoteMutagenesis
											.getTmplCloningMethod() == null ? ""
											: quoteMutagenesis
													.getTmplCloningMethod());
							if (quoteMutagenesis.getTmplCloningSite() != null
									&& !"".equals(quoteMutagenesis
											.getTmplCloningSite())) {
								itemDesc.append(", Cloning site:  ")
										.append(quoteMutagenesis
												.getTmplCloningSite() == null ? ""
												: quoteMutagenesis
														.getTmplCloningSite());
							}
							if ("N".equals(quoteMutagenesis
									.getPlasmidPrepFlag())) {
								itemDesc.append(",Quantity: 4 ug");
							} else {
								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								if (itemList != null && !itemList.isEmpty()) {
									QuotePlasmidPreparation quotePlasmidPreparation = null;
									for (QuoteItem item : itemList) {
										quotePlasmidPreparation = quotePlasmidPreparationDao
												.getById(item.getQuoteItemId());
										if (quotePlasmidPreparation != null) {
											break;
										}
									}
									itemDesc.append(",Quantity: ")
											.append(quotePlasmidPreparation != null ? (quotePlasmidPreparation
													.getPrepWeight() + quotePlasmidPreparation
													.getPrepWtUom())
													: "");
								}
							}

						}
					}
					List<QuoteItem> childItemList = this.quoteItemDao.findBy(
							"parentId", quoteItem.getQuoteItemId());
					if (childItemList != null && childItemList.size() > 0) {
						for (int j = 0; j < childItemList.size(); j++) {
							QuoteItem child = childItemList.get(j);
							unitPriceD = unitPriceD + child.getUnitPrice();
						}
					}

				}

				else if (quoteItemType.toLowerCase().indexOf("antibody") != -1
						&& quoteItemType.toLowerCase().indexOf("antibody drug") == -1) {
					QuoteAntibody quoteAntibody = this.quoteAntibodyDao
							.getById(quoteItem.getQuoteItemId());
					description = quoteAntibody != null ? quoteAntibody
							.getCustomSequence() : "";
					itemDesc.append(quoteItem.getCatalogNo())
							.append(": ")
							.append(quoteItem.getName())
							.append("(Price ")
							.append(currency == null ? "" : currency
									.getSymbol()).append(quoteItem.getAmount())
							.append(")<br>");
					List<QuoteItem> childItemList = this.quoteItemDao.findBy(
							"parentId", quoteItem.getQuoteItemId());
					if (childItemList != null && !"".equals(childItemList)
							&& !childItemList.isEmpty()) {
						for (int j = 0; j < childItemList.size(); j++) {
							QuoteItem child = childItemList.get(j);
							String childItemType = null;
							// 获取quoteItem类型
							if (QuoteItemType.PRODUCT.value().equals(
									child.getType())) {
								ProductClass pdtClass = this.productClassDao
										.getById(child.getClsId());
								childItemType = pdtClass == null ? null
										: pdtClass.getName();
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
										.append(quoteAntibody != null ? quoteAntibody
												.getAntigenType() : "")
										.append(":<br>");
								QuotePeptide quotePeptide = quotePeptideDao
										.getById(child.getQuoteItemId());
								if (quotePeptide == null) {
									quotePeptide = new QuotePeptide();
								}
								itemDesc.append(quotePeptide.getName()).append(
										": ");
								if (StringUtils.isNotEmpty(quotePeptide
										.getQuantity())) {
									itemDesc.append(quotePeptide.getQuantity())
											.append(", ");
								}
								if (StringUtils.isNotEmpty(quotePeptide
										.getPurity())) {
									itemDesc.append(quotePeptide.getPurity())
											.append(", ");
								}
								if (quotePeptide.getSeqLength() != null) {
									itemDesc.append(quotePeptide.getSeqLength())
											.append("aa, ");
								}

								if (StringUtils.isNotEmpty(quotePeptide
										.getNTerminal())) {
									itemDesc.append(quotePeptide.getNTerminal())
											.append("; ");
								}
								if (StringUtils.isNotEmpty(quotePeptide
										.getCTerminal())) {
									itemDesc.append(quotePeptide.getCTerminal())
											.append("; ");
								}
								if (StringUtils.isNotEmpty(quotePeptide
										.getModification())) {
									itemDesc.append(
											quotePeptide.getModification())
											.append("; ");
								}
								if (StringUtils.isNotEmpty(quotePeptide
										.getDisulfideBridge())) {
									itemDesc.append(";Disulfide Bridge: ")
											.append(quotePeptide
													.getDisulfideBridge())
											.append("; ");
								}
								if (quotePeptide.getAliquoteVialQty() != null) {
									itemDesc.append(
											quotePeptide.getAliquoteVialQty())
											.append("vials");
								}
								if (child.getAmount() != null) {
									itemDesc.append("(Price ")
											.append(currency == null ? ""
													: currency.getSymbol())
											.append(child.getAmount())
											.append(")<br>");
								}

								itemDesc.append(quotePeptide == null ? null
										: quotePeptide.getSequence());
							}
						}
					}

				} else if (quoteItemType != null
						&& quoteItemType.toLowerCase().startsWith(
								"non-standard service")) {
					itemDesc.append(quoteItem.getCatalogNo()).append(": ")
							.append(quoteItem.getName());
					if (quoteItem.getParentId() != null) {
						QuoteItem parentItem = this.quoteItemDao
								.getById(quoteItem.getParentId());
						QuoteAntibody quoteAntibody = parentItem != null ? this.quoteAntibodyDao
								.getById(parentItem.getQuoteItemId()) : null;
						itemDesc.append("(ship with antibody -")
								.append(quoteAntibody != null ? quoteAntibody
										.getAntibodyName() : " ").append(")");
					}
					itemDesc.append(";");

				} else if (quoteItemType.toLowerCase().startsWith("peptide")) {
					QuotePeptide quotePeptide = quotePeptideDao
							.getById(quoteItem.getQuoteItemId());
					if (quotePeptide == null) {
						quotePeptide = new QuotePeptide();
					}
					itemDesc.append(quoteItem.getName())
							.append(": ")
							.append(quotePeptide == null ? null : quotePeptide
									.getName());
					description = quotePeptide == null ? null : quotePeptide
							.getSequence();
					itemDesc.append("; ");
					if (quotePeptide.getSeqLength() != null) {
						itemDesc.append(quotePeptide.getSeqLength()).append(
								"aa, ");
					}
					if (StringUtils.isNotEmpty(quotePeptide.getQuantity())) {
						itemDesc.append(quotePeptide.getQuantity())
								.append(", ");
					}
					if (StringUtils.isNotEmpty(quotePeptide.getPurity())) {
						itemDesc.append(quotePeptide.getPurity()).append(", ");
					}
					if (StringUtils.isNotEmpty(quotePeptide.getNTerminal())) {
						itemDesc.append(quotePeptide.getNTerminal()).append(
								"; ");
					}
					if (StringUtils.isNotEmpty(quotePeptide.getCTerminal())) {
						itemDesc.append(quotePeptide.getCTerminal()).append(
								"; ");
					}
					if (StringUtils.isNotEmpty(quotePeptide.getModification())) {
						itemDesc.append(quotePeptide.getModification()).append(
								"; ");
					}
					if (StringUtils.isNotEmpty(quotePeptide
							.getDisulfideBridge())) {
						itemDesc.append("Disulfide Bridge: ")
								.append(quotePeptide.getDisulfideBridge())
								.append("; ");
					}
					if (quotePeptide.getAliquoteVialQty() != null) {
						itemDesc.append(quotePeptide.getAliquoteVialQty())
								.append("vials");
					}
					if (quoteItem.getComment() != null
							&& !"".equals(quoteItem.getComment())) {
						itemDesc.append("<br>").append(quoteItem.getComment());
					}
					itemDesc.append("<br>").append(
							quotePeptide == null ? null : quotePeptide
									.getSequence());
				} else if (quoteItemType.toLowerCase().startsWith("oligo")) {
					QuoteOligo quoteOligo = this.quoteOligoDao
							.getById(quoteItem.getQuoteItemId());
					if (quoteOligo != null && !"".equals(quoteOligo)) {
						itemDesc.append(quoteItemGene)
								.append(": ")
								.append("Aliquoting into: ")
								.append(quoteOligo.getAliquotingInto() != null ? quoteOligo
										.getAliquotingInto() : " 1 ")
								.append(", Backbone: "); 
						itemDesc.append(quoteOligo.getBackbone())
								.append(", Length: ")
								.append(quoteOligo != null ? quoteOligo
										.getSeqLength() : "")
								.append(", Purification: ")
								.append(quoteOligo != null ? quoteOligo
										.getPurification() : "")
								.append(", Synthesis Scales:")
								.append(quoteOligo != null ? quoteOligo
										.getSynthesisScales() : "");
					}

				} else if (quoteItemType.toLowerCase().startsWith(
						"mutation libraries")) {
					QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesDao
							.getById(quoteItem.getQuoteItemId());
					if(quoteMutationLibraries!=null){ 
						itemDesc.append(quoteItem.getName()).append(": ");
						
						itemDesc.append(quoteMutationLibraries.getConstructName()==null?"":quoteMutationLibraries.getConstructName());
						itemDesc.append("<br/>Sequence of interest below: <br/>").append(quoteMutationLibraries.getInterestSequence()==null?"":quoteMutationLibraries.getInterestSequence());
				
					}else{
						continue;
					}
					
				} else if (quoteItemType.toLowerCase().startsWith("sirna")
						|| quoteItemType.toLowerCase().startsWith("mirna")) {
					QuoteSirnaAndMirna quoteSirnaAndMirna = quoteSirnaAndMirnaDao
							.getById(quoteItem.getQuoteItemId());
					if (quoteSirnaAndMirna == null) {
						continue;
					}
					quoteItemGene = quoteItemGene
							+ ": "
							+ (quoteSirnaAndMirna == null ? null
									: quoteSirnaAndMirna.getGeneName());
					itemDesc.append(quoteItem.getCatalogNo()).append(": ")
							.append(quoteItemGene).append(": ")
							.append(quoteSirnaAndMirna.getQuantity())
							.append(", ")
							.append(quoteSirnaAndMirna.getVectorName())
							.append("<br/>")
							.append(quoteSirnaAndMirna.getSequenceInsert());
				} else if (quoteItemType.toLowerCase().startsWith(
						"custom services")) {
					itemDesc.append(quoteItem.getCatalogNo()).append(": ")
							.append(quoteItem.getName());
					com.genscript.gsscm.quote.entity.QuoteService quoteService = this.quoteServiceDao
							.getById(quoteItem.getQuoteItemId());
					itemDesc.append("; ").append(
							quoteService != null ? quoteService.getCustomDesc()
									: "");
				} else {
					QuotePkgService quotePkgService = this.quotePkgServiceDao
							.getById(quoteItem.getQuoteItemId());
					Name = quotePkgService == null ? null : quotePkgService
							.getName();
					descript = quotePkgService == null ? null : quotePkgService
							.getDescription();
 

				}

				if (quoteItemType.toLowerCase().startsWith("protein")
						|| quoteItemType.toLowerCase().startsWith("bioprocess")
						|| quoteItemType.toLowerCase().startsWith(
								"antibody drug")
						|| quoteItemType.toLowerCase().startsWith(
								"pharmaceutical")
						|| quoteItemType.toLowerCase().startsWith("biomarker")) {
					QuotePkgService quotePkgService1 = this.quotePkgServiceDao
							.getById(quoteItem.getQuoteItemId());
					description = quotePkgService1 != null ? quotePkgService1
							.getSequence() : "";
					List<QuoteItem> childItemList = this.quoteItemDao.findBy(
							"parentId", quoteItem.getQuoteItemId());
					if (childItemList == null) {
						childItemList = new ArrayList<QuoteItem>();
					}
					for (int j = 0; j < childItemList.size(); j++) {
						QuoteItem child = childItemList.get(j);
						unitPriceD = unitPriceD + child.getUnitPrice();
					}
					itemDetail.append("<tr><td  colspan='5' align='left'>");
					itemDetail
							.append(">Item")
							.append(quoteItem.getItemNo())
							.append(": ")
							.append(quoteItem.getCatalogNo())
							.append("(Unit Price: ")
							.append(currency.getSymbol())
							.append(new BigDecimal(String.valueOf(unitPriceD))
									.setScale(2, BigDecimal.ROUND_HALF_UP));
					if (quoteItem.getQuantity() != null) {
						itemDetail.append("; Quantity: ")
								.append(quoteItem.getQuantity())
								.append(")<br>");
					}
					itemDesc.append(quoteItem.getCatalogNo()).append(":")
							.append(quoteItem.getName()).append(", ")
							.append("" + Name).append(", ")
							.append(descript).append("<br>");
					itemDetail.append(itemDesc);

					for (int j = 0; j < childItemList.size(); j++) {
						QuoteItem child = childItemList.get(j);
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
						QuotePkgService quotePkgService = this.quotePkgServiceDao
								.getById(child.getQuoteItemId());
						StringBuilder sequenceBd = new StringBuilder();
						if (StringUtils.isNotBlank(quotePkgService
								.getSequence())) {
							sequenceBd.append("Gene sequence: ")
									.append(quotePkgService.getSequence())
									.append("<br>");
						}
						if (StringUtils.isNotBlank(quotePkgService
								.getProteinSeq())) {
							sequenceBd.append("Protein sequence: ").append("<div style='width:600px;align:left;word-break:break-all;word-wrap:break-all'>")
									.append(quotePkgService.getProteinSeq())
									.append("</div><br>");
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
								.append(quotePkgService != null ? quotePkgService
										.getDescription() : "").append("<br>")
								.append(sequenceBd.toString());
					}

				} else if (!quoteItemType.toLowerCase().startsWith("service")) {
					itemSeq = new StringBuffer(description == null ? ""
							: description);
				}

				if (!StringUtils.isEmpty(itemSeq.toString())) {
					String unitPrice = currency.getSymbol()
							+ new BigDecimal(String.valueOf(unitPriceD))
									.setScale(2, BigDecimal.ROUND_HALF_UP);
					;
					if (quoteItemType != null
							&& (quoteItemType.toLowerCase()
									.startsWith("peptide"))) {
						if ("New Request".equals(quote.getQuoteType())) {
							if (SessionUtil.getUserId() > 20000
									&& (quoteItem.getCatalogNo().equals(
											"SC1177")
											|| quoteItem.getCatalogNo().equals(
													"SC1487") || quoteItem
											.getCatalogNo().equals("SC1507"))) {
								unitPrice = "TBD";
							}
							if (quoteItem.getCatalogNo().equals("SC1208")
									&& !StringUtils.isEmpty(quoteItem
											.getComment())) {
								unitPrice = "TBD";
							}
						}
						itemDetail = new StringBuffer();
						itemDetail.append("<tr><td  colspan='5' align='left'>");
						itemDetail.append(">Item")
								.append(quoteItem.getItemNo()).append(": ")
								.append(quoteItem.getCatalogNo())
								.append("(Unit Price: ").append(unitPrice);
						if (quoteItem.getQuantity() != null) {
							itemDetail.append("; Quantity: ")
									.append(quoteItem.getQuantity())
									.append(")<br>");
						}
						itemDetail.append(quoteItem.getShortDesc())
								.append("  ").append(itemSeq.toString());

					} else {
						itemDetail = new StringBuffer();
						itemDetail.append("<tr><td  colspan='5' align='left'>");
						itemDetail
								.append(">Item")
								.append(quoteItem.getItemNo())
								.append(": ")
								.append(quoteItem.getCatalogNo())
								.append("(Unit Price: ")
								.append(new BigDecimal(String
										.valueOf(unitPriceD)).setScale(2,
										BigDecimal.ROUND_HALF_UP));
						if (quoteItem.getQuantity() != null) {
							itemDetail.append("; Quantity: ")
									.append(quoteItem.getQuantity())
									.append(")<br>");
						}
						itemDetail.append(quoteItem.getShortDesc())
								.append("  ").append(quoteItem.getComment())
								.append("<br>").append(itemSeq.toString());
					}

				} else if (quoteItemType.toLowerCase().startsWith("protein")
						|| quoteItemType.toLowerCase().startsWith("bioprocess")
						|| quoteItemType.toLowerCase().startsWith(
								"antibody drug")
						|| quoteItemType.toLowerCase().startsWith(
								"pharmaceutical")
						|| quoteItemType.toLowerCase().startsWith("biomarker")) {

				} else {
					itemDetail = new StringBuffer();
					itemDetail.append("<tr><td  colspan='5' align='left'>");
					itemDetail
							.append(">Item")
							.append(quoteItem.getItemNo())
							.append(": ")
							.append(quoteItem.getCatalogNo())
							.append("(Unit Price: ")
							.append(currency.getSymbol())
							.append(new BigDecimal(String.valueOf(unitPriceD))
									.setScale(2, BigDecimal.ROUND_HALF_UP));
					if (quoteItem.getQuantity() != null) {
						itemDetail.append("; Quantity: ")
								.append(quoteItem.getQuantity())
								.append(")<br>");
					}
					itemDetail.append(quoteItem.getShortDesc()).append("<br>")
							.append(quoteItem.getComment()).append("<br>");
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
						.append(new BigDecimal(String.valueOf(quoteItem
								.getDiscount())).setScale(2,
								BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td>");
				tableBody
						.append("<td align='right'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(quoteItem == null ? ""
								: new BigDecimal(
										String.valueOf(Double.parseDouble(String
												.valueOf(quoteItem
														.getQuantity()))
												* (unitPriceD - quoteItem
														.getDiscount())))
										.setScale(2, BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></tr>");
				detail.append(detailStr);

			}
		}
		if (tmpShipMethod == 0) {
			quotationPrintDTO.setShipVia("");
		} else {
			DropDownDTO dropDownDTO = specDropDownListDao.getSpecDropDown(
					SpecDropDownListName.SHIP_METHOD.value(), tmpShipMethod);
			quotationPrintDTO.setShipVia(dropDownDTO != null ? dropDownDTO
					.getValue() : "");
		}
		if (turnRound == null) {
			turnRound = String.valueOf(leadTime);
		}
		if (leadTime != 0) {
			turnaround_comments
					.append("The turnaround time may vary depending on the size and complexity of the project.");
		}
		if (quote != null) {
			if (leadTime == 0) {
				if (quoteType.toLowerCase().startsWith("gene synthesis")) {
					turnRound = "12-17 business days";
				} else if (quoteType.toLowerCase()
						.startsWith("express peptide")) {
					turnRound = "8 business days";
				} else if (quoteType.toLowerCase().startsWith(
						"chemical peptide")) {
					turnRound = "10-15  business days";
				} else if (quoteType.toLowerCase().startsWith(
						"polyclonal antibody")) {
					turnRound = "15-17  weeks";
				} else if (quoteType.toLowerCase().startsWith(
						"monoclonal antibody")) {
					turnRound = "5-7 months";
				} else if (quoteType.toLowerCase().startsWith("sirna")) {
					turnRound = "5-10 days";
				}
			}
			if (quoteType != null
					&& quoteType.toLowerCase().startsWith("gene synthesis")) {
				turnaround_comments = new StringBuffer();
				turnaround_comments
						.append("       The turnaround time may vary depending on the size and complexity of the project")
						.append(".*Typical turnaround time can be expected only for sequences that are non-toxic for E. coli.")
						.append("In rare cases, some genes might be toxic and/or genetically unstable,")
						.append("which may be delivered in reduced quantity, cloned subfragments with sequencing data.")
						.append("     **In some rare cases, gene may be toxic and/or genetically unstable in customer's vector,")
						.append(" which makes impossible to deliver gene in required vector.")
						.append(" In this case, gene will be delivered in GenScript standard vector and subcloning fee is waived.");
			}
			if (quote.getSalesContact() == 78) {
				turnaround_comments
						.append("     We guarantee that your polyclonal antibody produced by us in any host will have an ELISA titer of 1:32,")
						.append("000 or better if peptide antigens were designed, synthesized, and conjugated by GenScript.")
						.append(" For any protein antigen (3 mg, >85% purity, 90% preferred)")
						.append(" we guarantee a polyclonal antibody with positive Western blot results and an ELISA titer of 1:32,000")
						.append(" or better for any host.")
						.append("All antisera or purified antibodies are provided as lyophilized form except special requirements.")
						.append(" Lyophilization extends the shelf life of your antibodies,")
						.append(" eliminates leaking problems and loosens up the stringent temperature requirement during shipping,")
						.append(" and reduces the overall weight of antibodies to be shipped.");
			}
			total = quote.getSubTotal().add(quote.getTax()).doubleValue()
					+ quote.getShipAmt();
			if (quote.getQuoteNo() < 800000) {// 逻辑问题(以前的quotation)
				total = quote.getAmount().doubleValue();

			}
			if (quote.getDiscount().intValue() != 0) {
				total = total - quote.getDiscount().doubleValue();
			}
			if (quote.getExchRate() != null
					&& "JPY".equalsIgnoreCase(currency.getCurrencyCode())) {
				total_price_desc = "(Net of Tax)";
				QuoteAddress quoteAddress = this.quoteAddressDao
						.getAddrByQuoteNoAndType(quoteNo, "BILL_TO");
				if (quoteAddress != null
						&& "Japan".equalsIgnoreCase(quoteAddress.getCountry())) {
					total_price_desc1 = "(Including Tax)";
					total = total - quote.getTax().doubleValue();
				}
			}
			if (quote.getDiscount().intValue() != 0) {
				sub_discount_department
						.append("<tr><td colspan='5' align='right'  width='84%'>Total Discount(")
						.append(currency == null ? "" : currency
								.getDescription())
						.append(")&nbsp;</td><td align='right'  width='16%'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(quote == null ? "" : new BigDecimal(String
								.valueOf(quote.getDiscount())).setScale(2,
								BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></TR>");
			}
			if (quote.getTax().intValue() > 0) {
				tax_department
						.append("<tr><td colspan='5' align='right'  width='84%'>Estimated Tax(")
						.append(currency == null ? "" : currency
								.getDescription())
						.append(")&nbsp;</td>")
						.append("<td align='right'  width='16%'>")
						.append(currency == null ? "" : currency.getSymbol())
						.append(quote == null ? "" : quote.getTax().setScale(2,
								BigDecimal.ROUND_HALF_UP))
						.append("&nbsp;</td></TR>");
				total_include_tax
						.append("<TR><td colspan='5' align='right'  width='84%'>Total Quote(")
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
				.append("<tr><td colspan='4' align='right'  width='84%'>Estimated Shipping/Handling(")
				.append(currency == null ? "" : currency.getDescription())
				.append(")&nbsp;</td>")
				.append("<td align='right'  width='16%'>")
				.append(currency == null ? "" : currency.getSymbol())
				.append(quote == null ? "" : new BigDecimal(String
						.valueOf(quote.getShipAmt())).setScale(2,
						BigDecimal.ROUND_HALF_UP)).append("&nbsp;</td></TR>");

		/******** 以下为构建QuotationPrintDTO对象 *********************************/
		quotationPrintDTO.setQId(String.valueOf(quoteNo));
		quotationPrintDTO.setCompanyName(companyName);
		if (quote != null) {
			quotationPrintDTO.setInitDate(DateTypeConverter.convertToString(
					quote.getQuoteDate(),
					DateTypeConverter.ACCEPT_DATE_FORMATS[1]));
			quotationPrintDTO.setExpDate(DateTypeConverter.convertToString(
					quote.getExprDate(),
					DateTypeConverter.ACCEPT_DATE_FORMATS[1]));
			quotationPrintDTO.setSubTotal(String.valueOf(new BigDecimal(String
					.valueOf(quote.getSubTotal())).setScale(2,
					BigDecimal.ROUND_HALF_UP)));
			quotationPrintDTO.setSubprice(String.valueOf(new BigDecimal(String
					.valueOf(quote.getSubTotal().doubleValue() + 2
							* quote.getDiscount().doubleValue())).setScale(2,
					BigDecimal.ROUND_HALF_UP)));
			quotationPrintDTO.setSubDiscount(String.valueOf(new BigDecimal(
					String.valueOf(quote.getDiscount())).setScale(2,
					BigDecimal.ROUND_HALF_UP)));
		}
		if (customer != null) {
			quotationPrintDTO.setFirstName(customer.getFirstName());
			quotationPrintDTO.setLastName(customer.getLastName());
			quotationPrintDTO
					.setEmail(customer.getBusEmail() != null ? customer
							.getBusEmail().replace(",", "<br>") : "");
			quotationPrintDTO.setState(customer.getState());
		}
		if (country != null) {
			quotationPrintDTO.setCountry(country.getName());
		}
		if (department != null) {
			quotationPrintDTO.setDepartment(department.getName());
		}
		if (organization != null) {
			String orgname = organization.getName();
			quotationPrintDTO.setInstitution(orgname);
		}
		User user = new User();
		System.out.println(quoteNo);
		if (quote != null) {
			Integer teachManage = quote.getTechSupport();
			user = this.userDao.findByUserId(teachManage);
			if (user != null) {
				System.out.println(user.getFirstName() + user.getLastName());
				String preparedByName = user.getFirstName() + " "
						+ user.getLastName();
				String techManager = user.getPosition();
				quotationPrintDTO.setPreparedByName(preparedByName);
				quotationPrintDTO.setTechManager(techManager);
			}
		}
		if (currency != null) {
			quotationPrintDTO.setName(currency.getDescription());
			quotationPrintDTO.setSubTotalName(currency.getDescription());
			quotationPrintDTO.setTotalQuoteName(currency.getDescription());
			quotationPrintDTO.setSymbol(currency.getSymbol());
		}
		quotationPrintDTO.setTurnAround(turnRound.toString().equals("0") ? ""
				: turnRound.toString());
		quotationPrintDTO.setTotalPriceDesc(total_price_desc);
		quotationPrintDTO.setResult(result.toString().replace("null", ""));

		quotationPrintDTO
				.setTableBody(tableBody.toString().replace("null", ""));
		quotationPrintDTO.setShipPriceDepartment(ship_price_department
				.toString().replace("null", ""));
		quotationPrintDTO.setTaxDepartment(tax_department.toString().replace(
				"null", ""));
		quotationPrintDTO.setTotalIncludeTax(total_include_tax.toString()
				.replace("null", ""));
		System.out.println(turnaround_comments);
		quotationPrintDTO.setComments(turnaround_comments.toString().replace(
				"null", ""));

		quotationPrintDTO.setTotal(String.valueOf(new BigDecimal(String
				.valueOf(total)).setScale(2, BigDecimal.ROUND_HALF_UP)));
		// 有没有child.pdf.htm)
		List<PreQuotation> list = this.preQuotationDao.findBy("quotationId",
				quoteNo);// lddb.pre_quotation表中是否有一条记录的quoteNo值和quote.quoteNo相等
		if (quote != null
				&& (quoteType.toLowerCase().startsWith("protein")
						|| quoteType.toLowerCase().startsWith("bioprocess")
						|| quoteType.toLowerCase().startsWith("antibody drug")
						|| quoteType.toLowerCase().startsWith("pharmaceutical") || quoteType
						.toLowerCase().startsWith("biomarker")) || isHasItem
				|| (list != null && list.size() > 0)) {
			quotationPrintDTO.setDetail(detail.toString().replace("null", ""));
			quotationPrintDTO.setShowChild(true);
		}
		System.out.println(quotationPrintDTO.getComments());
		return quotationPrintDTO;
	}

	/**
	 * 合并pdf文件(quotation print)
	 */
	@SuppressWarnings("unused")
	public String mergePdfFiles(Integer quoteNo,
			QuotationPrintDTO quotationPrintDTO, ServletContext context) {
		String retPdfFullName = null;
		if (quoteNo != null) {
			QuoteMain quote = this.quoteDao.getById(quoteNo);
			String requestPath = "quote/quote!showPrintPage.action";
			StringBuffer param = new StringBuffer();
			param.append("quoteNo=").append(quoteNo)
					.append("&quotationPrintDTO.email=")
					.append(quotationPrintDTO.getEmail())
					.append("&quotationPrintDTO.department=")
					.append(quotationPrintDTO.getDepartment())
					.append("&quotationPrintDTO.institution=")
					.append(quotationPrintDTO.getInstitution())
					.append("&quotationPrintDTO.state=")
					.append(quotationPrintDTO.getState())
					.append("&quotationPrintDTO.country=")
					.append(quotationPrintDTO.getCountry())
					.append("&quotationPrintDTO.comments=")
					.append(quotationPrintDTO.getComments())
					.append("&quotationPrintDTO.companyName=")
					.append(quotationPrintDTO.getCompanyName())
					.append("&quotationPrintDTO.address1=")
					.append(quotationPrintDTO.getAddress1())
					.append("&quotationPrintDTO.address2=")
					.append(quotationPrintDTO.getAddress2())
					.append("&quotationPrintDTO.telephone=")
					.append(quotationPrintDTO.getTelephone())
					.append("&quotationPrintDTO.fax=")
					.append(quotationPrintDTO.getFax())
					.append("&quotationPrintDTO.custEmail=")
					.append(quotationPrintDTO.getCustEmail())
					.append("&quotationPrintDTO.web=")
					.append(quotationPrintDTO.getWeb());
			// 1. 通过JSP生成原始的PDF，没有合并子Item, 没有加水印
			String pdfName = PdfUtils.convertServerUrl2Pdf(requestPath,
					param.toString());

			String port = "";
			if (Struts2Util.getRequest().getServerPort() != 80) {
				port = ":" + Struts2Util.getRequest().getServerPort();
			}
			String basePath = Struts2Util.getRequest().getScheme() + "://"
					+ Struts2Util.getRequest().getServerName() + port
					+ Struts2Util.getRequest().getContextPath() + "/";
			HashMap<String, String> moreInfo = new HashMap<String, String>();

			try {
				// 加水印，头尾logo
				// pdfUtil.extraPdf(moreInfo,basePath+"images/confidential.gif",basePath+"images/header.jpg",basePath+"images/footer.jpg");
				String advPdfFilePath = null;// 要合并的广告pdf文件
				if (quote != null) {
					String quoteType = null;
					List<QuoteItem> quoteItemList = this.quoteItemDao.findBy(
							"quoteNo", quote.getQuoteNo());
					if (quoteItemList != null && quoteItemList.size() > 0) {
						for (QuoteItem quoteItem : quoteItemList) {
							String quoteItemType = null;
							// 获取quoteItem类型
							if (QuoteItemType.PRODUCT.value().equals(
									quoteItem.getType())) {
								ProductClass pdtClass = this.productClassDao
										.getById(quoteItem.getClsId());
								quoteItemType = pdtClass == null ? null
										: pdtClass.getName();
							} else {
								ServiceClass servClass = this.serviceClassDao
										.getById(quoteItem.getClsId());
								quoteItemType = servClass == null ? null
										: servClass.getName();
							}
							if (quoteItemType != null) {
								quoteType = quoteItemType;
								break;
							}
						}
					}
					if (quoteType != null) {
						/*
						 * if (quoteType.toLowerCase().startsWith("peptide")) {
						 * advPdfFilePath = "Peptide_Services.pdf"; } else if
						 * (quoteType.toLowerCase().startsWith("gene") ||
						 * quoteType.toLowerCase().startsWith( "synthesis")) {
						 * advPdfFilePath = "GeneServices.pdf"; } else if
						 * (quoteType.toLowerCase() .startsWith("protein") ||
						 * quoteType.toLowerCase().startsWith( "bioprocess") ||
						 * quoteType.toLowerCase().startsWith(
						 * "pharmaceutical")) { advPdfFilePath =
						 * "Protein_Service.pdf"; } else if
						 * (quoteType.toLowerCase().startsWith( "biomarker") ||
						 * quoteType.toLowerCase().startsWith(
						 * "custom services-animal model")) { advPdfFilePath =
						 * "Animal_model.pdf"; } else if
						 * (quoteType.toLowerCase().startsWith(
						 * "antibody drug")) { advPdfFilePath =
						 * "Antibody_drug.pdf"; } // else
						 * if(quoteType.startsWith("Custom Service")) //
						 * {//暂时没有该类型 // // } else {
						 */
						// advPdfFilePath = "Quotation.pdf";
						// } 现在全部换成了quotation 的 通用广告。

						advPdfFilePath = context
								.getRealPath("pdfAdv/Quotation.pdf");
					}

				}
				// 合并pdf
				String names[] = { pdfName };
				if (advPdfFilePath != null) {
					names = new String[] { pdfName, advPdfFilePath /*
																	 * ,
																	 * context.
																	 * getRealPath
																	 * (
																	 * "pdfAdv/all_service.pdf"
																	 * )
																	 */};
				}
				retPdfFullName = PdfUtils.getTempFileShortName() + ".pdf";
				PdfUtils.mergePdfFiles(names, retPdfFullName);
				File file = new File(pdfName);
				file.delete();
				// pdfName = "c:\\tmp\\Protein_Service.pdf";
				// retPdfFullName = pdfName;
				PdfUtils pdfUtil = new PdfUtils(retPdfFullName, retPdfFullName);
				// 加水印，头尾logo

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
	 * 创建一个新的quoteitem该item为赠品
	 * 
	 * @author lizhang
	 * @param catelogNo
	 * @param qty
	 *            数量
	 * @param itemSize
	 *            所属order已含有的item数量
	 * @param warehouseId
	 *            quote的warehouseId
	 */
	private QuoteItemDTO createQuoteItemDTO(String catelogNo, Integer custNo,
			int qty, int itemSize, int warehouseId) throws Exception {
		CustomerPriceBean customerPriceBean = this.customerPriceBeanDao
				.findUnique(Restrictions.and(
						Restrictions.eq("catalogNo", catelogNo),
						Restrictions.eq("custNo", custNo)));
		if (customerPriceBean == null) {
			throw new RuntimeException("Product Item not existed.");
		}
		Product product = this.productDao.findUniqueBy("catalogNo", catelogNo);
		QuoteItemDTO quoteItemDTO = new QuoteItemDTO();
		ItemBeanDTO itemBeanDTO = dozer.map(customerPriceBean, ItemBeanDTO.class);
		quoteItemDTO.setCatalogNo(catelogNo);
		quoteItemDTO.setName(product.getName());
		quoteItemDTO.setQuantity(qty);
		quoteItemDTO.setSize(itemBeanDTO.getSize());
		quoteItemDTO.setUnitPrice(itemBeanDTO.getUnitPrice());
		quoteItemDTO.setUpSymbol(itemBeanDTO.getUpSymbol());
		quoteItemDTO.setSizeUom(itemBeanDTO.getUom());
		quoteItemDTO.setQtyUom(itemBeanDTO.getQtyUom());
		quoteItemDTO.setDiscount(new BigDecimal(0));
		quoteItemDTO.setTax(new BigDecimal(0));
		quoteItemDTO.setItemNo(itemSize + 1);
		quoteItemDTO.setAmount(new BigDecimal(ArithUtil.mul(itemBeanDTO
				.getUnitPrice().doubleValue(), Double.valueOf(String.valueOf(qty)))));
		quoteItemDTO.setType(QuoteItemType.PRODUCT.value());
		quoteItemDTO.setCatalogId(itemBeanDTO.getCatalogId());
		StringBuffer catalogTex = new StringBuffer();
		catalogTex.append(itemBeanDTO.getCatalogId()).append(":").append(itemBeanDTO.getCatalogName());
		quoteItemDTO.setCatalogText(catalogTex.toString());
		quoteItemDTO.setParentId("");
		quoteItemDTO.setTypeText(quoteItemDTO.getType() + " - " + productClassDao.get(product.getProductClsId()).getName());
		quoteItemDTO.setClsId(product.getProductClsId());
		quoteItemDTO.setTaxStatus(product.getTaxable());
		quoteItemDTO.setShipSchedule(product.getLeadTime());
		quoteItemDTO.setShortDesc(product.getShortDesc());
		quoteItemDTO.setLongDesc(product.getLongDesc());
		quoteItemDTO.setSellingNote(product.getSellingNote());

		quoteItemDTO.setCost(new BigDecimal(0.0));
		quoteItemDTO.setBasePrice(new BigDecimal(0));
		// 为了能保存OrderItem设置的shipTOAddrId假数据
		quoteItemDTO.setShiptoAddrId(6276);
		quoteItemDTO.setStatus(QuoteItemStatusType.CM.value());
		quoteItemDTO.setStatusText(getQuoteItemStatusText(QuoteItemStatusType.CM.value()));
		String itemOtherInfo = getItemOtherInfo(quoteItemDTO.getTaxStatus(), quoteItemDTO.getStatus(), quoteItemDTO.getCatalogNo(), quoteItemDTO.getStatusReason(), null, custNo);
		quoteItemDTO.setOtherInfo(itemOtherInfo);
		List<DropDownDTO> list = specDropDownListDao.getSpecDropDownList(SpecDropDownListName.SHIP_METHOD.value());
		if (list != null && list.size() > 0) {
			quoteItemDTO.setShipMethod(Integer.parseInt(list.get(0).getId()));
		}
		List<DropDownDTO> list2 = specDropDownListDao.getPickLocationList(warehouseId);
		if (list2 != null && list2.size() > 0) {
			quoteItemDTO.setStorageId(Integer.parseInt(list2.get(0).getId()));
		}
		return quoteItemDTO;
	}

	/**
	 * quote转成order时promotion的处理
	 */
	private void dealwithPromotion(Integer quoteNo, Integer custNo, Integer orderNo) {
		QuotePromotion quotePromotion = this.quotePromotionDao.findUniqueBy("quoteNo", quoteNo);
		Promotion promotion = null;
		if (quotePromotion != null) {
			promotion = this.promotionDao.findUniqueBy("prmtCode",
					quotePromotion.getPrmtCode());
			String applyType = promotion == null ? null : promotion
					.getApplyType();
			if (applyType != null) {
				if (("Once per Customer".equals(applyType) && orderDao
						.isHasPrmtForCust(custNo, quotePromotion.getPrmtCode()))) {
					return;
				} else if (("First Order".equals(applyType) && this.orderDao
						.isHasCreateOrder(custNo, String.valueOf(orderNo)))) {
					return;
				} else {
					OrderPromotion orderPromotion = new OrderPromotion();
					orderPromotion.setCreatedBy(SessionUtil.getUserId());
					orderPromotion.setModifiedBy(SessionUtil.getUserId());
					orderPromotion.setOrderNo(orderNo);
					orderPromotion.setPrmtCode(promotion.getPrmtCode());
					this.orderPromotionDao.save(orderPromotion);
				}
			}
		}
	}

	/**
	 * 查询busEmail
	 * 
	 * @author zhangyong
	 * @param custNo
	 * @param quoteNo
	 * @return
	 */
	public String findBusEmailByNo(Integer custNo, Integer quoteNo) {
		if (custNo != null) {
			Customer customer = this.customerDao.findByCustNo(custNo);
			if (customer != null) {
				return customer.getBusEmail();
			}
		} else if (quoteNo != null) {
			return this.quoteDao.findBusEmailByQuoteNo(quoteNo);
		}
		return null;
	}

	/**
	 * 把coupon的code和VALUE赋值给quote
	 */
	public void applyCoupon(String couponId, String sessOrderNo)
			throws Exception {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessOrderNo);
		String[] couponIdArray = StringUtils.isNotBlank(couponId) ? couponId
				.split(",") : new String[] {};
		Double couponValues = 0.0;
		StringBuffer couponCodes = new StringBuffer();
		for (String couponId1 : couponIdArray) {
			Coupon coupon = this.couponDao.getById(Integer.parseInt(couponId1));
			if (coupon != null) {
				couponValues += coupon.getValue();
				if ("".equals(couponCodes.toString())) {
					couponCodes.append(coupon.getCode());
				} else {
					couponCodes.append(",").append(coupon.getCode());
				}
			}

		}
		quote.setCouponCode(couponCodes.toString());
		quote.setCouponId(couponId);
		quote.setCouponValue(new BigDecimal(couponValues));
		SessionUtil
				.updateRow(SessionConstant.Quote.value(), sessOrderNo, quote);
	}

	/**
	 * 去除因promotion产生的giftitem
	 * 
	 * @param prmtCode
	 */
	@SuppressWarnings("unchecked")
	public void deleteGifItem(String prmtCode, String quoteNo) {
		Promotion promotion = this.promotionDao.findUniqueBy("prmtCode",
				prmtCode);
		if (promotion != null) {
			String[] discType = promotion.getDiscType().split(";");
			if (discType.length == 4 && "1".equals(discType[2])) {
				String catalogNo = promotion.getDiscProd();
				Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
						.getRow(SessionConstant.QuoteItemList.value(), quoteNo);
				Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
						.iterator();
				int i = 1;
				List<String> delItemsId = new ArrayList<String>();
				while (it.hasNext()) {
					Entry<String, QuoteItemDTO> entry = it.next();
					QuoteItemDTO quoteItemDTO = entry.getValue();
					quoteItemDTO.setItemNo(i);
					String tmpItemId = entry.getKey();
					if (catalogNo.equals(quoteItemDTO.getCatalogNo())) {
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
					this.quoteItemDao.delete(Integer.parseInt(id));
				}
				SessionUtil.updateRow(SessionConstant.QuoteItemList.value(),
						quoteNo, itemMap);
			}

		}
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), quoteNo);
		if (quote.getQuotePromotion() != null
				&& StringUtils.isNotBlank(quote.getQuotePromotion()
						.getPrmtCode())) {
			quote.setQuotePromotion(null);
		}
		SessionUtil.updateRow(SessionConstant.Quote.value(), quoteNo, quote);
	}

	/**
	 * 通过orderNo查询QuoteMain
	 * 
	 * @author zhangyong
	 * @param orderNo
	 * @return
	 */
	public QuoteMain findByOrderNo(Integer orderNo) {
		return quoteDao.findByOrderNo(orderNo);
	}

	@Transactional(readOnly = true)
	public List<QuoteItem> getQuoteAllItemList(Integer quoteNo) {
		return quoteItemDao.getQuoteAllItemList(quoteNo);
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
	public List getQuoteProductTempList(int owner, int custNo) {
		return srchQuoteTemplateItemBeanDao.getQuoteProductTempList(owner,
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
	public List getQuoteServiceTempList(int owner, int custNo) {
		return srchServiceQuoteTemplateBeanDao.getQuoteServiceTempList(owner,
				custNo);
	}

	public void delQuoteItemListForPeptide(List<Integer> ids) {
		if (ids != null && ids.size() > 0) {
			quotePeptideDao.delQuoteItemList(ids);
			quoteItemDao.delQuoteItemList(ids);
		}
	}

	// ----------------------------------add by -----------------------
	/**
	 * 通过noteId查询customer Note信息
	 * 
	 * @author Zhang Yong
	 * @param noteKey
	 * @param sessQuoteNo
	 * @return
	 */
	public InstructionDTO getInstructByCustNoteId(String noteKey,
			String sessQuoteNo) {
		InstructionDTO instructionDTO = null;
		if (noteKey.contains(QuoteInstructionType.CUSTOMER.value() + "-")) {
			String custNoteId = noteKey.substring(
					(QuoteInstructionType.CUSTOMER.value().length() + 1),
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
			instructionDTO.setCustNoteId(QuoteInstructionType.CUSTOMER.value()
					+ "-" + customerNote.getId());
			if (StringUtils.isNumeric(sessQuoteNo)) {
				instructionDTO.setOrderNo(Integer.parseInt(sessQuoteNo));
			} else {
				instructionDTO.setOrderNo(null);
			}
			instructionDTO.setType(customerNote.getType());
			instructionDTO.setDescription(customerNote.getDescription());
			instructionDTO.setDocFlag(customerNote.getDocFlag());
			instructionDTO.setNoteDate(customerNote.getCreationDate());
			instructionDTO.setInstructionDate(customerNote.getCreationDate());
			instructionDTO.setSource(QuoteInstructionType.CUSTOMER.value());
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

	// ----------
	/**
	 * quote转成order时Coupon的处理
	 */
	private void dealwithCoupon(Integer quoteNo, Integer custNo, Integer orderNo) {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), String.valueOf(quoteNo));
		OrderMain order = this.orderDao.getById(orderNo);
		String couponIds = quote.getCouponId();
		StringBuffer strId = new StringBuffer();
		Integer couponValue = 0;
		if (couponIds != null && !"".equals(couponIds)) {
			for (String couponId : couponIds.split(",")) {
				Coupon coupon = this.couponDao.getById(Integer
						.parseInt(couponId));
				if (!this.orderDao.couponIsUsed(couponId, orderNo)) {
					strId.append(couponId).append(",");
					couponValue = couponValue + coupon.getValue();
				}
			}
			if (!"".equals(strId.toString())) {
				order.setCouponId(strId.substring(0,
						strId.toString().length() - 1));
			} else {
				order.setCouponId(null);
			}
			order.setCouponValue(couponValue.doubleValue());
			this.orderDao.save(order);
		}
	}

	@Transactional(readOnly = true)
	public QuoteItem getQuoteItemByItemId(Integer itemId) {
		return quoteItemDao.findUniqueBy("quoteItemId", itemId);
	}

	@Transactional(readOnly = true)
	public QuoteMain getQuote(Integer quoteNo) {
		return quoteDao.getById(quoteNo);
	}

	/**
	 * 获取Project Manager填充到SalesInformation tab 中
	 * 
	 * @author Zhang Yong
	 * @param quoteDTO
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<User> getProManagerList(QuoteMainDTO quoteDTO) {
		if (quoteDTO != null && quoteDTO.getItemList() != null
				&& !quoteDTO.getItemList().isEmpty()) {
			Integer firstServiceClsId = null;
			for (QuoteItemDTO quoDTO : quoteDTO.getItemList()) {
				if (QuoteItemType.SERVICE.value().equals(quoDTO.getType())
						&& null != quoDTO.getClsId()) {
					firstServiceClsId = quoDTO.getClsId();
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
	@Transactional(readOnly = true)
	public List<User> getAltProManagerList() {
		return salesRepDao.findUserByFunction(SalesRepSalesRole.PROJECT_SUPPORT
				.value());
	}

	/**
	 * 通过类型和Code查询status详细信息
	 * 
	 * @author Zhang Yong
	 * @param type
	 * @param code
	 * @return
	 */
	public StatusList getStatusDetailByTypeAndCode(String type, String code) {
		return statusListDao.findByTypeAndCode(type, code);
	}

	public String getFollowUpDateByQuoteNo(int quoteNo) { 
		return this.quoteNoteDao.getFollowUpDate(quoteNo);
	} 
}