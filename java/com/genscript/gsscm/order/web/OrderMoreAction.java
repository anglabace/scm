package com.genscript.gsscm.order.web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.biolib.entity.PeptideCode;
import com.genscript.gsscm.biolib.service.BiolibService;
import com.genscript.gsscm.common.FileService;
import com.genscript.gsscm.common.UrlUtil;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.Constants;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.FilePathConstant;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.ServicePriceType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.ExcelUtil;
import com.genscript.gsscm.common.util.FilelUtil;
import com.genscript.gsscm.common.util.ModelUtils;
import com.genscript.gsscm.common.util.MoreDetailUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.olddb.entity.Marker;
import com.genscript.gsscm.order.dto.OrderDsPlatesDTO;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.service.OrderItemService;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.dto.OligoBackbonesDTO;
import com.genscript.gsscm.product.entity.DsPrice;
import com.genscript.gsscm.product.entity.OligoChimericBases;
import com.genscript.gsscm.product.entity.OligoMixedBases;
import com.genscript.gsscm.product.entity.OligoModifications;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.CustomServiceDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutagenesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderMutationLibrariesDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOligoDTO;
import com.genscript.gsscm.quoteorder.dto.OrderOrfCloneDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.OrderServiceDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.PkgServiceDTO;
import com.genscript.gsscm.quoteorder.dto.RnaDTO;
import com.genscript.gsscm.quoteorder.dto.VectorInfoListDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.quoteorder.service.QuoteOrderUtil;
import com.genscript.gsscm.serv.dto.ServiceIntermediateDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;
import com.genscript.gsscm.serv.dto.ServiceSubStepsDTO;
import com.genscript.gsscm.serv.entity.Service;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;

/**
 * 处理more detail tab中的信息
 * 
 * @author YuluZou
 * 
 */
@Results({
		@Result(name = "more", location = "order/order_more.jsp"),
		@Result(name = "order_more_batch", location = "order/order_more_batch.jsp"),
		@Result(name = "order_cloning_batch", location = "order/order_more_cloning_batch.jsp"),
		@Result(name = "select_batch_order", location = "order/selectBatchService.jsp"),
		@Result(name = "quality_grade_help", location = "order/quality_grade_help.jsp") })
public class OrderMoreAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6358765823814588796L;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ServService servService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private BiolibService biolibService;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private ExcelUtil excelUtil;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private ProductService productService;
	@Autowired
	private QuoteOrderUtil quoteOrderUtil;

	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownMap;

	private String sessOrderNo;
	private String itemId;
	private OrderItemDTO orderItem;
	private Map<String, OrderItemDTO> itemMap;
	private String parentId;
	private Integer removeFlag;

	private String show;
	private String tab;
	private String disableTabStr;

	private OrderServiceDTO orderServiceDTO;
	private String serviceListStr;
	private OrderGeneSynthesisDTO geneSynthesis;
	private RnaDTO rna;
	private OrderCustCloningDTO custCloning;
	private OrderPlasmidPreparationDTO plasmidPreparation;
	private OrderOrfCloneDTO orfClone;
	private OrderOligoDTO oligo;
	private CustomServiceDTO customService;
	private OrderMutagenesisDTO mutagenesis;
	private OrderMutationLibrariesDTO mutaLib;
	private PeptideDTO peptide;
	private PkgServiceDTO orderPkgService;
	private List<ServiceIntermediateDTO> serviceSteps;
	private List<ServiceSubStepsDTO> subSteps;
	private AntibodyDTO antibody;
	private Map<String, PeptideDTO> peptideMap;
	private String antibodyItemId;
	private String antigenType;
	private Integer stepId;
	// 上传附件相关
	@Autowired
	private FileService fileService;
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String refType;
	private String serviceName;
	private String delFilePath;

	private List<String> modificationList;
	private List<PeptideCode> peptideCodeList;

	// plasmid
	private String antibioticResistance;
	private String fragmentSize;
	private String prepWeight;

	private List<OligoBackbonesDTO> oligoBackbonesList;
	private Map<String, List<OligoModifications>> omMap;
	private List<OligoChimericBases> oligoChimericBasesList;
	private List<OligoMixedBases> oligoMixedBasesList;

	private String quanty;
	private String uom;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	// 批量标志
	private boolean batchFlag = false;
	// 批量错误信息
	private Map<String, Object> batchMessage = new HashMap<String, Object>();
	private String batchCustNo;
	//DNA Sequencing
	private List<OrderDsPlatesDTO> dsPlatesList;
	private Map<String, OrderItemDTO> tubeMap;
	private Map<String, OrderItemDTO> plateMap;
	private String tubeComment;
	//ORF Clone
	private List<VectorInfoListDTO> vectorList;
	private List<String> orfCloneSiteList;
	//
	private String[] packageDescriptArray;
	private final static String Y = "Y";
	private final static String N = "N";
	private final static String pUC57 = "pUC57";
	private final static String tbd_0 = "0";
	private final static String tbd_1 = "1";
	private final static String Other = "Other";
	/**
	 * 显示order more detail
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showMoreDetail() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		if (itemId != null && itemId.contains(",")) {
			itemId = itemId.split(",")[0];
		}
		if (itemId == null || ("").equals(itemId.trim())) {
			return "more";
		}
		orderItem = itemMap.get(itemId);
		// PRODUCT类型不用继续
		if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(orderItem.getType())) {
			Struts2Util.renderHtml("");
			return null;
		}
		// 取得所有的相关服务
		if (orderItem.getOrderItemId() != null) {
			orderService.setItemAllMoreDetail(itemMap, orderItem);
		}
		OrderItemDTO parentOrderItemDTO = null;
		Integer parentClsId = null;
		String parentId = null;
		Integer clsId = orderItem.getClsId();
		Integer topClsId = clsId;
		if (StringUtils.isNotBlank(orderItem.getParentId())) {
			parentOrderItemDTO = itemMap.get(orderItem.getParentId());
			parentClsId = parentOrderItemDTO.getClsId();
			parentId = orderItem.getParentId();
			if (!StringUtils.isEmpty(parentOrderItemDTO.getParentId())) {
				OrderItemDTO parentParentOrderItemDTO = itemMap.get(parentOrderItemDTO.getParentId());
				topClsId = parentParentOrderItemDTO.getClsId();
			} else {
				topClsId = parentClsId;
			}
		}else{
			packageDescriptArray = quoteOrderUtil.getPackageDescriptions(orderItem.getCatalogNo()); 
		}
		initDropDownList();
		// Protein&Bioprocess&Pharmeceutical&Antibody drug
		if (MoreDetailUtil.isProteinGroupService(clsId)) {
			Map<String, OrderItemDTO> groupItemMap = this
					.getGroupItemMap(itemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			// 设置
			if (MoreDetailUtil.isProteinGroupParent(clsId)) {
				serviceSteps = this.servService.getServiceIntermediateAllList(
						orderItem.getCatalogNo(), orderItem.getCatalogId());
			} else {
				subSteps = this.servService.getServiceSubStepsList(
						orderItem.getCatalogNo(), orderItem.getCatalogId(),
						null);
			}
			// 设置jsp页面显示类型
			show = MoreDetailUtil.getShow(clsId);
		} else if ((clsId == 1 && parentOrderItemDTO == null)
				|| (clsId == 1 && parentOrderItemDTO.getClsId() == 1)) {
			// 有父item,则进入其他处理流程（antibody）。
			this.setDropdownForPeptide();

			orderServiceDTO = new OrderServiceDTO();
			if (orderItem.getPeptide() != null) {
				orderServiceDTO.setPeptide(orderItem.getPeptide());
				orderServiceDTO.setPeptideItemId(itemId);
			}
			peptide = orderItem.getPeptide();
			if (modificationList == null) {
				modificationList = new ArrayList<String>();
			}
			if (peptide != null) {
				String modifications = orderItem.getPeptide().getModification();

				if (StringUtils.isNotBlank(modifications)) {
					String[] modificationStrs = modifications.split(",");
					for (String str : modificationStrs) {
						modificationList.add(str);
					}
				}
			}
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		} else if ((clsId == 31 && parentOrderItemDTO == null)
				|| (clsId == 31 && parentOrderItemDTO.getClsId() == 31)) {
			// 有父item,则进入其他处理流程（antibody）。
			this.setDropdownForPeptide();

			if ("SC1177".equalsIgnoreCase(orderItem.getCatalogNo())) {
				dropDownMap.put("PEPTIDE_PURITY",
						publicService.getDropdownList("SC1177_PURITY"));
				dropDownMap.put("PEPTIDE_QUANTITY",
						publicService.getDropdownList("SC1177_QUANTITY"));
				dropDownMap.put("PEPTIDE_QC_REPORT",
						publicService.getDropdownList("SC1177_QC_REPORT"));
			} else if ("SC1487".equalsIgnoreCase(orderItem.getCatalogNo())) {
				dropDownMap.put("PEPTIDE_PURITY",
						publicService.getDropdownList("SC1487_PURITY"));
				dropDownMap.put("PEPTIDE_QUANTITY",
						publicService.getDropdownList("SC1487_QUANTITY"));
				dropDownMap.put("PEPTIDE_QC_REPORT",
						publicService.getDropdownList("SC1487_QC_REPORT"));
			}

			orderServiceDTO = new OrderServiceDTO();
			if (orderItem.getPeptide() != null) {
				orderServiceDTO.setPeptide(orderItem.getPeptide());
				orderServiceDTO.setPeptideItemId(itemId);
				if (StringUtils.isBlank(orderItem.getParentId())) {
					Iterator<Entry<String, OrderItemDTO>> it = itemMap
							.entrySet().iterator();
					StringBuilder sb = new StringBuilder();
					while (it.hasNext()) {
						Entry<String, OrderItemDTO> entry = it.next();
						OrderItemDTO orderItemDTO = entry.getValue();
						if (orderItemDTO != null
								&& orderItemDTO.getPeptide() != null
								&& orderItemDTO.getParentId() != null
								&& orderItemDTO.getParentId().equalsIgnoreCase(
										itemId)) {
							sb.append(orderItemDTO.getPeptide().getSequence())
									.append(",");
						}
					}
					Iterator<Entry<String, OrderItemDTO>> itTwo = itemMap
							.entrySet().iterator();
					while (itTwo.hasNext()) {
						Entry<String, OrderItemDTO> entry = itTwo.next();
						OrderItemDTO orderItemDTO = entry.getValue();
						if (orderItemDTO != null
								&& orderItemDTO.getPeptide() != null
								&& orderItemDTO.getParentId() != null
								&& orderItemDTO.getParentId().equalsIgnoreCase(
										itemId)) {
							PeptideDTO peptideDTO = orderServiceDTO
									.getPeptide();
							peptideDTO = (PeptideDTO) ModelUtils.mergerModel(
									orderItemDTO.getPeptide(),
									orderServiceDTO.getPeptide());
							orderServiceDTO.setPeptide(peptideDTO);
							orderServiceDTO.setPeptideItemId(itemId);
							break;
						}
					}
					String mutipleSeq;
					if (StringUtils.isNotEmpty(sb.toString())) {
						mutipleSeq = sb.substring(0, sb.length() - 1);
					} else {
						mutipleSeq = orderItem.getPeptide().getSequence();
					}

					orderServiceDTO.getPeptide().setSequence(mutipleSeq);
				}
			}

			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		} else if ((clsId == 30 && parentOrderItemDTO == null)
				|| (clsId == 30 && parentOrderItemDTO.getClsId() == 30)) {
			// 有父item,则进入其他处理流程（antibody）。
			this.setDropdownForPeptide();
			orderServiceDTO = new OrderServiceDTO();
			if (orderItem.getPeptide() != null) {
				orderServiceDTO.setPeptide(orderItem.getPeptide());
				orderServiceDTO.setPeptideItemId(itemId);
				if (StringUtils.isBlank(orderItem.getParentId())) {
					Iterator<Entry<String, OrderItemDTO>> it = itemMap
							.entrySet().iterator();
					StringBuilder sb = new StringBuilder();
					while (it.hasNext()) {
						Entry<String, OrderItemDTO> entry = it.next();
						OrderItemDTO orderItemDTO = entry.getValue();
						if (orderItemDTO != null
								&& orderItemDTO.getPeptide() != null
								&& orderItemDTO.getParentId() != null
								&& orderItemDTO.getParentId().equalsIgnoreCase(
										itemId)) {
							sb.append(orderItemDTO.getPeptide().getSequence())
									.append(",");
						}
					}
					Iterator<Entry<String, OrderItemDTO>> itTwo = itemMap
							.entrySet().iterator();
					while (itTwo.hasNext()) {
						Entry<String, OrderItemDTO> entry = itTwo.next();
						OrderItemDTO orderItemDTO = entry.getValue();
						if (orderItemDTO != null
								&& orderItemDTO.getPeptide() != null
								&& orderItemDTO.getParentId() != null
								&& orderItemDTO.getParentId().equalsIgnoreCase(
										itemId)) {
							PeptideDTO peptideDTO = orderServiceDTO
									.getPeptide();
							peptideDTO = (PeptideDTO) ModelUtils.mergerModel(
									orderItemDTO.getPeptide(),
									orderServiceDTO.getPeptide());
							orderServiceDTO.setPeptide(peptideDTO);
							orderServiceDTO.setPeptideItemId(itemId);
							break;
						}
					}
					String mutipleSeq;
					if (StringUtils.isNotEmpty(sb.toString())) {
						mutipleSeq = sb.substring(0, sb.length() - 1);
					} else {
						mutipleSeq = orderItem.getPeptide().getSequence();
					}
					orderServiceDTO.getPeptide().setSequence(mutipleSeq);
				}
			}
			disableTabStr = "librayPeptide";
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		} else if (clsId.intValue() == 11
				|| (parentClsId != null && parentClsId == 11)
				|| clsId.intValue() == 12
				|| (parentClsId != null && parentClsId == 12)
				|| clsId.intValue() == 28
				|| (parentClsId != null && parentClsId == 28)) {
			// antibody
			this.setDropdownForPeptide();
			if (parentClsId != null) {
				antibodyItemId = parentId;
				show = MoreDetailUtil.getShow(parentClsId);
			} else {
				antibodyItemId = itemId;
				show = MoreDetailUtil.getShow(clsId);
			}
			Map<String, OrderItemDTO> groupItemMap = this
					.getGroupItemMap(antibodyItemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			this.antibody = orderServiceDTO.getAntibody();
			if (this.antibody != null) {
				if (this.antibody.getAntigenType() == null) {
					this.antibody.setAntigenType(MoreDetailUtil
							.getDefaultAntigenType());
				}
			}
			OrderItemDTO specialItem = null;
			if (StringUtils.isEmpty(parentId)) {
				specialItem = orderItem;
			} else {
				specialItem = itemMap.get(parentId);
			}
			List<ServiceIntermediate> imdList = servService.getIntermediate(
					specialItem.getCatalogNo(), Y);
			if (imdList != null) {
				Map<String, String> specialCatalogNoMap = new HashMap<String, String>();
				Iterator<ServiceIntermediate> it = imdList.iterator();
				while (it.hasNext()) {
					ServiceIntermediate intermediate = it.next();
					specialCatalogNoMap.put(intermediate.getIntmdCatalogNo(),
							intermediate.getIntmdCatalogNo());
				}
				if (this.antibody != null && specialCatalogNoMap != null
						&& !"".equals(specialCatalogNoMap)) {
					antibody.setSpecialCatalogNoMap(specialCatalogNoMap);
				}
			}
			// Oligo
		} else if (clsId.intValue() == 34) {
			// 下拉列表
			dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
			// Synthesis Scales select
			dropDownMap.put("OLIGO_SYNTHESIS_SCALES", publicService.getDropdownList("OLIGO_SYNTHESIS_SCALES"));
			// RNA Purification select
			dropDownMap.put("OLIGO_RNA_PURIFICATION", publicService.getDropdownList("OLIGO_RNA_PURIFICATION"));
			// Other Purification select
			dropDownMap.put("OLIGO_OTHER_PURIFICATION", publicService.getDropdownList("OLIGO_OTHER_PURIFICATION"));
			// Backbone select
			oligoBackbonesList = orderService.findOligoBackbonesList();
			// 5' Modification and Internal Modification and 3' Modification
			// select
			omMap = orderService.findOligoModificationsList();
			// Chimeric Bases select
			oligoChimericBasesList = orderService.findOligoChimericBases(oligoBackbonesList);
			// Standard Mixed Bases select
			oligoMixedBasesList = orderService.findOligoMixedBases();
			Map<String, OrderItemDTO> groupItemMap = this.getGroupItemMap(itemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
			// DNA Sequencing
		} else if (clsId.intValue() == 40) {
			// 下拉列表
			dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
			// Sample Type select
			dropDownMap.put("DS_SAMPLE_TYPE", publicService.getDropdownList("DS_SAMPLE_TYPE"));
			// Primer Type select
			dropDownMap.put("DS_PRIMER_TYPE", publicService.getDropdownList("DS_PRIMER_TYPE"));
			// Special Request select
			dropDownMap.put("DS_SPECIAL_REQUEST", publicService.getDropdownList("DS_SPECIAL_REQUEST"));
			// Primer Name
			List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
			speclListName.add(SpecDropDownListName.GET_PRIMER_NAME);
			specDropDownMap = publicService.getSpecDropDownMap(speclListName);
			dsPlatesList = orderItemService.getDsPlateByOrderNo(itemMap, sessOrderNo);
			//获取所有的tube(Type为TUBE)
			tubeMap = orderItemService.getDNASeqItemByType(itemMap).get("tubeMap");
			if (tubeMap != null && !tubeMap.isEmpty()) {
				for (String key : tubeMap.keySet()) {
		            OrderItemDTO dto = tubeMap.get(key);
					tubeComment = dto.getDnaSequencing().getComment();
					break;
				}
			}
			//获取所有的plate(Type为PLATE)
			plateMap = orderItemService.getDNASeqItemByType(itemMap).get("plateMap");
			Map<String, OrderItemDTO> groupItemMap = this.getGroupItemMap(itemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		} else {
			// 下拉列表
			List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
			speclListName.add(SpecDropDownListName.HOST_EXPS_ORGANISM);
			speclListName.add(SpecDropDownListName.VECTOR);
			specDropDownMap = publicService.getSpecDropDownMap(speclListName);
			//
			boolean isOnlyParent = MoreDetailUtil.isOnlyParent(clsId);// 是否必定为父级显示方式
			boolean isChildExist = this.isChildItemExist(itemMap, itemId);
			Integer showClsId = null;
			boolean isParent = false;
			if (isOnlyParent == true || isChildExist == true || StringUtils.isEmpty(orderItem.getParentId())) {
				showClsId = clsId;
				isParent = true;
			} else {
				showClsId = parentClsId;
				tab = MoreDetailUtil.getShow(clsId);
			}
			if (clsId != null && clsId.intValue() == 9 && parentClsId != null && parentClsId == 3) {
				showClsId = parentClsId;
				tab = MoreDetailUtil.getShow(clsId);
				isParent = false;
			}
			show = MoreDetailUtil.getShow(showClsId);
			if (clsId == 7) {
				dropDownMap.put("VECTOR_LIST", publicService.getDropdownList("SIRNA_VECTOR_LIST"));
			} else if (clsId == 8) {
				dropDownMap.put("VECTOR_LIST", publicService.getDropdownList("MIRNA_VECTOR_LIST"));
			}
			if (show.equals("muta")) {
				String topShow = MoreDetailUtil.getShow(topClsId);
				if (topShow.equalsIgnoreCase("gene") || topShow.equalsIgnoreCase("cloning")) {
					disableTabStr = "muta";
					if (tab == null) {
						tab = "target";
					}
				}
			}
			Map<String, OrderItemDTO> groupItemMap = null;
			if (isParent == true) {
				groupItemMap = this.getChildItemMap(itemId);
			} else {
				groupItemMap = this.getChildItemMap(orderItem.getParentId());
			}
			this.setServicesByItemMap(groupItemMap);
			//更新session中ORF Clone服务的GenePrice、SubcloningPrice，前台显示用
			uptSessOrfServicePrice(topClsId, parentOrderItemDTO);
			serviceListStr = Struts2Util.conventJavaObjToJson(orderServiceDTO);
		}
		return "more";
	}

	// add by zhanghuibin Add batch Item

	public String showBachItem() {
		batchCustNo = ServletActionContext.getRequest().getParameter("custNo");
		return "order_more_batch";
	}

	public String showBatchType() {
		return "select_batch_order";
	}

	/**
	 * add by zhanghuibin 保存 geneSynthesis batch
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveBatchGeneSynthesis() {
		batchFlag = true;
		Map<String, Object> messageMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> idsList = new ArrayList<String>();
		try {
			String baseItemId = itemId;
			itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
			OrderItemDTO baseItemDto = itemMap.get(itemId);
			String[] seqCont = geneSynthesis.getSequenceContent().split(">");
			Iterator<String> it = itemMap.keySet().iterator();
			// cloning OrderItem
			OrderItemDTO cloningItemDto = null;
			String cloningId = "";
			OrderPlasmidPreparationDTO basePlasmidDto = dozer.map(plasmidPreparation, OrderPlasmidPreparationDTO.class);
			OrderCustCloningDTO baseCloningDto = null;
			while (it.hasNext()) {
				String keyid = it.next().toString();
				OrderItemDTO itemDTO = (OrderItemDTO) itemMap.get(keyid);
				if (itemId.equals(itemDTO.getParentId())) {
					cloningItemDto = itemDTO;
					cloningId = keyid;
					baseCloningDto = dozer.map(itemDTO.getCustCloning(), OrderCustCloningDTO.class);
					break;
				}
			}
			idsList.add(baseItemId);
			for (int i = 1; i < seqCont.length; i++) {
				// 对于每个geneSynthesis进行赋值
				seqCont[i] = seqCont[i].replaceFirst("\r|\n|\r\n|\\s", "@@");
				String geneName = seqCont[i].split("@@")[0];
				String squ = seqCont[i].substring(seqCont[i].indexOf("@@") + 2, seqCont[i].length());
                squ = squ.replaceAll("\r|\n|\r\n|\\s", "");
				if (i == 1) {
					geneSynthesis.setSequence(squ);
					geneSynthesis.setGeneName(geneName);
					// Direct Cloning
					if ("D".equals(geneSynthesis.getCloningFlag()) && cloningItemDto != null) {
						geneSynthesis.setStdVectorName(baseCloningDto.getTgtVector());
						geneSynthesis.setVectorCopyNo(baseCloningDto.getTgtCopyNo());
						geneSynthesis.setVectorMapDocFlag(baseCloningDto.getTgtMapDocFlag());
						geneSynthesis.setVectorResistance(baseCloningDto.getTgtResistance());
						geneSynthesis.setVectorSeq(baseCloningDto.getTgtVectorSeq());
						geneSynthesis.setVectorSize(baseCloningDto.getTgtVectorSize());
					}
					// 保存gene表单
					itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
					saveGeneSynthesis();
					// Direct Cloning 类型也要删除
					if (!Y.equals(geneSynthesis.getCloningFlag()) && cloningItemDto != null) {
						// 找出要删除的itemId
						itemId = cloningId;
						// 删除cloning的orderItem
						removeFlag = 1;
						itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
						saveCustCloning();
						if (!batchMessage.isEmpty()) {
							Struts2Util.renderJson(batchMessage);
							break;
						}
						removeFlag = null;
					} else if (Y.equals(geneSynthesis.getCloningFlag())) {
						idsList.add(cloningId);
					}
					if (!N.equals(geneSynthesis.getPlasmidPrepFlag())) {
						// 保存plasmid表单
						this.parentId = baseItemId;
						itemId = "";
						plasmidPreparation = dozer.map(basePlasmidDto, OrderPlasmidPreparationDTO.class);
						itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
						savePlasmid();
						if (!batchMessage.isEmpty()) {
							Struts2Util.renderJson(batchMessage);
							break;
						}
						idsList.add(itemId);
					}
				} else {
					// 复制gene的orderItem
					OrderGeneSynthesisDTO synthesisDTO = dozer.map(geneSynthesis, OrderGeneSynthesisDTO.class);
					synthesisDTO.setSequence(squ);
					synthesisDTO.setGeneName(geneName);
                    //计算seq length
                    String threeSequence = synthesisDTO.getSequence3() == null ? "" : synthesisDTO.getSequence3();
                    String fiveSequence = synthesisDTO.getSequence5() == null ? "" : synthesisDTO.getSequence5();
                    String sequenceStr = synthesisDTO.getSequence() == null ? "" : synthesisDTO.getSequence();
                    StringBuilder sequenceSB = new StringBuilder();
                    sequenceSB.append(threeSequence).append(fiveSequence).append(sequenceStr);
                    synthesisDTO.setSeqLength(sequenceSB.toString().length());
					OrderItemDTO itemDTO = dozer.map(baseItemDto, OrderItemDTO.class);
					itemDTO.setNameShow(itemDTO.getName() + ": " + synthesisDTO.getGeneName());
					itemDTO.setGeneSynthesis(synthesisDTO);
					// 新建对象加入session
					String id = SessionUtil.generateTempId();
					itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
                    /*
                    这里不需要 saveGeneSynthesis()方法，因为用 insertNewChild 方法 已可以把item加入到session中，而且每个gene只是sequence不同，
                    所以只把涉及到 sequence会影响到计算的属性改变即可
                    * */
                    orderItem =  itemDTO;
                    this.insertNewChild(itemMap, itemDTO.getPreParentId(), id, itemDTO);
                    /*gene 计算价格*/
                    // 计算基因分析类型Item的价格
                    ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(itemDTO);
                    orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.geneSynthesis.name());
                    orderServiceItemPiceDTO.setGeneSynthesisDTO(itemDTO.getGeneSynthesis());
                    orderItemService.returnOrderServiceItemPrice(sessOrderNo, itemDTO, orderServiceItemPiceDTO);
                    if (StringUtils.isNotBlank(itemDTO.getTbdFlag()) && itemDTO.getTbdFlag().equals("1")) {
                        itemDTO.getGeneSynthesis().setDfcltSeqFlag("Y");
                    } else {
                        itemDTO.getGeneSynthesis().setDfcltSeqFlag("N");
                    }
					idsList.add(id);
                    geneSynthesis = itemDTO.getGeneSynthesis();
					if (!N.equals(geneSynthesis.getPlasmidPrepFlag())) {
						itemId = "";
						this.parentId = id;
						// 复制plasmid的orderItem
						plasmidPreparation = dozer.map(basePlasmidDto, OrderPlasmidPreparationDTO.class);
						itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
						savePlasmid();
						if (!batchMessage.isEmpty()) {
							Struts2Util.renderJson(batchMessage);
							break;
						}
						idsList.add(itemId);
					}
					if (Y.equals(geneSynthesis.getCloningFlag())) {
						itemId = "";
						this.parentId = id;
						// 复制cloning的orderItem
						custCloning = dozer.map(baseCloningDto, OrderCustCloningDTO.class);
						itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
						saveCustCloning();
						if (!batchMessage.isEmpty()) {
							Struts2Util.renderJson(batchMessage);
							break;
						}
						idsList.add(itemId);
					}
				}
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(), "INTF0203", SessionUtil.getUserId());
			messageMap.put("hasException", Y);
			messageMap.put("exception", exDTO);
			Struts2Util.renderJson(messageMap);
			return null;
		}
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		String ids = "";
		ArrayList<OrderItemDTO> resList = new ArrayList<OrderItemDTO>();
		for (String id : idsList) {
			ids = ids + "," + id;
			resList.add(itemMap.get(id));
		}
		if (batchMessage.isEmpty()) {
			result.put("ids", ids);
			result.put("data", resList);
			Struts2Util.renderJson(result);
		}
		return null;
	}

	// gene 批量上传
	@SuppressWarnings("unchecked")
	public String batchUploadFile() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (orderItem == null) {
			Struts2Util.renderText("Fail to upload it, Please check the session");
			return null;
		}
		OrderCustCloningDTO custCloning = orderItem.getCustCloning();
		List<Document> docList = custCloning.getDocumentList();
		if (docList == null) {
			docList = new ArrayList<Document>();
		}
		List<Document> newDocList = new ArrayList<Document>();
		uploadOrderServiceFile(docList, newDocList);
		custCloning.setDocumentList(docList);
		orderItem.setCustCloning(custCloning);
		orderItem.setUpdateFlag(Y);
		Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		return null;
	}

	public String showBatchCloningStrategy() {
		// 下拉列表
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.HOST_EXPS_ORGANISM);
		speclListName.add(SpecDropDownListName.VECTOR);
		specDropDownMap = publicService.getSpecDropDownMap(speclListName);
        List<PbDropdownListOptions> list =  publicService.getDropdownList("VECTOR_RESISTANCE");
        List<PbDropdownListOptions> resistanceList = new ArrayList<PbDropdownListOptions>();
        resistanceList.add(new PbDropdownListOptions());
        for(PbDropdownListOptions option : list){
              resistanceList.add(dozer.map(option, PbDropdownListOptions.class));
        }
		dropDownMap.put("VECTOR_RESISTANCE", resistanceList);
		return "order_cloning_batch";
	}

	private void initDropDownList() {
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownMap.put("PEPTIDE_SYNTHESIS_MEMBRANE", publicService.getDropdownList("PEPTIDE_SYNTHESIS_MEMBRANE"));
		dropDownMap.put("PEPTIDE_DELIVERY_FORMAT", publicService.getDropdownList("PEPTIDE_DELIVERY_FORMAT"));
		dropDownMap.put("QC_REPORT", publicService.getDropdownList("QC_REPORT"));
		dropDownMap.put("PEPTIDE_QC_REPORT_ARRAY", publicService.getDropdownList("QC_REPORT_ARRAY"));
		dropDownMap.put("PEPTIDE_PURITY", publicService.getDropdownList("SC1177_PURITY"));
		dropDownMap.put("PEPTIDE_QUANTITY", publicService.getDropdownList("SC1177_QUANTITY"));
		dropDownMap.put("PEPTIDE_QC_REPORT", publicService.getDropdownList("SC1177_QC_REPORT"));
		dropDownMap.put("PEPTIDE_LIB_MOD", publicService.getDropdownList("PEPTIDE_LIB_MOD"));
		dropDownMap.put("PEPTIDE_ARRAY_MOD", publicService.getDropdownList("PEPTIDE_ARRAY_MOD"));
		dropDownMap.put("PLASMID_ANTIBIOTIC_RESISTANCE", publicService.getDropdownList("PLASMID_ANTIBIOTIC_RESISTANCE"));
		dropDownMap.put("PLASMID_STORAGE_CONDITION", publicService.getDropdownList("PLASMID_STORAGE_CONDITION"));
		dropDownMap.put("PLASMID_RESTRICTION_ANALYSIS", publicService.getDropdownList("PLASMID_RESTRICTION_ANALYSIS"));
		dropDownMap.put("PLASMID_FRAGMENT", publicService.getDropdownList("PLASMID_FRAGMENT"));
		dropDownMap.put("PLASMID_DESIRED_BUFFER", publicService.getDropdownList("PLASMID_DESIRED_BUFFER"));
		dropDownMap.put("PLASMID_ADDITIONAL_ANALYSIS", publicService.getDropdownList("PLASMID_ADDITIONAL_ANALYSIS"));
		dropDownMap.put("PLASMID_QUANTITY", publicService.getDropdownList("PLASMID_QUANTITY"));
	// dropDownMap.put("PLASMID_WEIGHT",
	// publicService.getDropdownList("PLASMID_WEIGHT"));
		dropDownMap.put("SEQ_POSITION_UOM", publicService.getDropdownList("SEQ_POSITION_UOM"));
		dropDownMap.put("VECTOR_RESISTANCE", publicService.getDropdownList("VECTOR_RESISTANCE"));
		dropDownMap.put("PLASMID_WT_UOM", publicService.getDropdownList("PLASMID_WT_UOM"));
		dropDownMap.put("PLASMID_ADTL_SERVICE", publicService.getDropdownList("PLASMID_ADTL_SERVICE"));
		dropDownMap.put("VECTOR_RESISTANCE", publicService.getDropdownList("VECTOR_RESISTANCE"));
		dropDownMap.put("ORF_CLONES_VECTOR_MAP", publicService.getDropdownList("ORF_CLONES_VECTOR_MAP"));
	}

	@SuppressWarnings("unchecked")
	public String checkOtherPeptide() throws Exception {
		Map<String, OrderItemDTO> batchSessionMap = (Map<String, OrderItemDTO>) SessionUtil
				.getRow(SessionConstant.OtherPeptideList.value(), sessOrderNo);
		if (batchSessionMap != null && !batchSessionMap.isEmpty() && batchSessionMap.containsKey(itemId)) {
			Struts2Util.renderText("otherPeptide");
		}
		return NONE;
	}

	public String checkRna() throws Exception {
		String code = Struts2Util.getParameter("code");
		String seq = Struts2Util.getParameter("seq");
		Marker marker = orderQuoteUtil.getRnaByCode(code);
		if (marker != null) {
			String re5Seq = marker.getRe5Seq();
			String re3Seq = marker.getRe3Seq();
			String pattern1 = "^" + re5Seq + "(A|C|G|T)*";
			String pattern2 = "(A|C|G|T)*" + re3Seq + "$";
			Pattern pat1 = Pattern.compile(pattern1);
			Pattern pat2 = Pattern.compile(pattern2);
			Matcher mat1 = pat1.matcher(seq);
			Matcher mat2 = pat2.matcher(seq);
			boolean cb1 = mat1.matches();
			boolean cb2 = mat2.matches();
			System.out.println(cb1 && cb2);
			if (cb1 && cb2) {
				Struts2Util.renderText("ok");
			} else {
				Struts2Util.renderText("The siRNA sequence should start with "
						+ re5Seq + " and end with " + re3Seq + ".");
			}
		}
		return NONE;
	}

	/**
	 * 保存TubeDNASequencing
	 * @author Zhang Yong
	 * add date 2011-11-04
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveTubeDNASequencing() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		OrderItemDTO srcDsItem = null;
		String srcDsItemKey = null;
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			if (40 == orderItemDTO.getClsId().intValue()) {
				srcDsItem = orderItemDTO;
				srcDsItemKey = entry.getKey();
				break;
			}
		}
		String currency = CurrencyType.USD.value();
		Double baseToRateDB = 1d;
		Double toBaseRateDB = 1d;
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		if (orderMain != null && StringUtils.isNotBlank(orderMain.getOrderCurrency())) {
			currency = orderMain.getOrderCurrency();
		}
		if (!CurrencyType.USD.value().equals(currency)) {
			baseToRateDB = publicService.getRateByBaseCurrencyToCurrency(currency, new Date());
            toBaseRateDB = publicService.getRateByCurrencyToBaseCurrency(currency, new Date());
        }
		String[] sampleNameArr = Struts2Util.getRequest().getParameterValues("sampleNameArr");
		String[] sampleTypeArr = Struts2Util.getRequest().getParameterValues("sampleTypeArr");
		String[] sampleConcArr = Struts2Util.getRequest().getParameterValues("sampleConcArr");
		String[] flagConcMeasArr = Struts2Util.getRequest().getParameterValues("flagConcMeasArr");
		String[] primerTypeArr = Struts2Util.getRequest().getParameterValues("primerTypeArr");
		String[] primerNameArr = Struts2Util.getRequest().getParameterValues("primerNameArr");
		String[] flagPowerReadArr = Struts2Util.getRequest().getParameterValues("flagPowerReadArr");
		String[] flagDataAnasArr = Struts2Util.getRequest().getParameterValues("flagDataAnasArr");
		String[] dataAnasArr = Struts2Util.getRequest().getParameterValues("dataAnasArr");
		String[] specialRequestArr = Struts2Util.getRequest().getParameterValues("specialRequestArr");
		String[] templateSizeArr = Struts2Util.getRequest().getParameterValues("templateSizeArr");
		String[] vectorNameArr = Struts2Util.getRequest().getParameterValues("vectorNameArr");
		String[] primerConcArr = Struts2Util.getRequest().getParameterValues("primerConcArr");
		String[] resistanceArr = Struts2Util.getRequest().getParameterValues("resistanceArr");
		String[] priceArr = Struts2Util.getRequest().getParameterValues("priceArr");
		String[] sessItemKeyArr = Struts2Util.getRequest().getParameterValues("sessItemKeyArr");
		String[] commentArr = Struts2Util.getRequest().getParameterValues("commentArr");
		//判断customer的Type是否为Internal,为True则计算后的价格除cost外都为0
		boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
		for (int i=0;i<sampleNameArr.length;i++) {
			DnaSequencingDTO tube = new DnaSequencingDTO();
			tube.setSampleName(sampleNameArr[i]);
			tube.setSampleType(sampleTypeArr[i]);
			tube.setSampleConc(sampleConcArr[i]);
			tube.setFlagConcMeas(StringUtils.isBlank(flagConcMeasArr[i])?0:Integer.parseInt(flagConcMeasArr[i]));
			tube.setPrimerType(primerTypeArr[i]);
			tube.setPrimerName(primerNameArr[i]);
			tube.setFlagPowerRead(StringUtils.isBlank(flagPowerReadArr[i])?0:Integer.parseInt(flagPowerReadArr[i]));
			tube.setFlagDataAnas(StringUtils.isBlank(flagDataAnasArr[i])?0:Integer.parseInt(flagDataAnasArr[i]));
			tube.setDataAnas(dataAnasArr[i]);
			tube.setSpecialRequest(specialRequestArr[i]);
			tube.setTemplateSize(StringUtils.isBlank(templateSizeArr[i])?0:Integer.parseInt(templateSizeArr[i]));
			tube.setVectorName(vectorNameArr[i]);
			tube.setPrimerConc(primerConcArr[i]);
			tube.setResistance(resistanceArr[i]);
			tube.setPrice(StringUtils.isBlank(priceArr[i])?null:new BigDecimal(priceArr[i]));
			tube.setSessItemKey(sessItemKeyArr[i]);
			tube.setComment(commentArr[i]);
			OrderItemDTO tubeItemDTO = null;
			if (StringUtils.isNotBlank(tube.getSessItemKey())) {
				tubeItemDTO = itemMap.get(tube.getSessItemKey());
				tube = (DnaSequencingDTO) ModelUtils.mergerModel(tube, tubeItemDTO.getDnaSequencing());
			} else if (StringUtils.isBlank(tube.getSessItemKey()) && srcDsItem.getDnaSequencing() != null 
					&& StringUtils.isBlank(srcDsItem.getDnaSequencing().getSampleName())
					&& StringUtils.isBlank(srcDsItem.getDnaSequencing().getCode())) {
				tube.setSessItemKey(srcDsItemKey);
				tubeItemDTO = srcDsItem;
			} else {
				String tmpId = SessionUtil.generateTempId();
				tube.setSessItemKey(tmpId);
				tubeItemDTO = new OrderItemDTO();
		        ModelUtils.mergerModel(srcDsItem, tubeItemDTO);
		        tubeItemDTO.setItemNo(itemMap.size() + 1);
		        tubeItemDTO.setOrderItemId(null);
		        tube.setCode(null);
			}
			if (StringUtils.isBlank(tube.getCode())) {
				tube.setCode(Constants.DS_SEQ_CODE_TUBE);
			}
			tube.setPlateId(0);
	        tubeItemDTO.setDnaSequencing(tube);
	        tubeItemDTO.setNameShow(tubeItemDTO.getName() + ": " + tube.getSampleName());
			// 开始计算DNA Sequencing Item的价格
			BigDecimal price = tube.getPrice();
			if (price != null) {
				tubeItemDTO.setUnitPrice(price);
				tubeItemDTO.setAmount(price.multiply(new BigDecimal(tubeItemDTO.getQuantity())));
				tubeItemDTO.setBasePrice(price.multiply(new BigDecimal(toBaseRateDB)));
			} else {
				if (isInternalOrder) {
					orderItemService.updatePriceByInternal(tubeItemDTO, sessOrderNo);
				} else {
					BigDecimal zero = new BigDecimal(0);
					DsPrice dsPrice = productService.getDsPrice(sampleTypeArr[i], primerTypeArr[i], sampleNameArr.length);
					if (dsPrice == null) {
						tubeItemDTO.setUnitPrice(zero);
						tubeItemDTO.setAmount(zero);
						tubeItemDTO.setBasePrice(zero);
					} else {
						BigDecimal unitPrice = new BigDecimal(dsPrice.getPrice());
						if (tube.getFlagPowerRead() == 1) {
							unitPrice = unitPrice.add(new BigDecimal(dsPrice.getPowerRead()==null?0:dsPrice.getPowerRead()));
						}
						if (tube.getFlagDataAnas() == 1) {
							unitPrice = unitPrice.add(new BigDecimal(dsPrice.getAnalysis()==null?0:dsPrice.getAnalysis()));
						}
						if (tube.getFlagConcMeas() == 1) {
							unitPrice = unitPrice.add(new BigDecimal(dsPrice.getConcentration()==null?0:dsPrice.getConcentration()));
						}
						tubeItemDTO.setBasePrice(unitPrice);
						tubeItemDTO.setUnitPrice(unitPrice.multiply(new BigDecimal(baseToRateDB)));
						tubeItemDTO.setAmount(tubeItemDTO.getUnitPrice().multiply(new BigDecimal(tubeItemDTO.getQuantity())));
					}
				}
			}
			//计算价格结束
			tubeItemDTO.setUpdateFlag(Y);
			itemMap.put(tube.getSessItemKey(), tubeItemDTO);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		Struts2Util.renderJson(map);
		return null;
	}
	
	/**
	 * 保存PlateDNASequencing
	 * @author Zhang Yong
	 * add date 2011-11-04
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String savePlateDNASequencing() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//检查数据
		String pName = Struts2Util.getRequest().getParameter("pName");
		String plateLayout = Struts2Util.getRequest().getParameter("plateLayout");
		String[] sampleNameArr = Struts2Util.getRequest().getParameterValues("sampleNameArr");
		if (StringUtils.isBlank(pName)) {
			map.put("message", "Please input plate name!");
		}
		if (StringUtils.isBlank(sampleNameArr[0])) {
			map.put("message", "Please input sample name!");
		}
		if (StringUtil.isNumeric(plateLayout) == false) {
			plateLayout = "1";
		}
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		OrderItemDTO srcDsItem = null;
		String srcDsItemKey = null;
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			if (40 == orderItemDTO.getClsId().intValue()) {
				srcDsItem = orderItemDTO;
				srcDsItemKey = entry.getKey();
				break;
			}
		}
		String currency = CurrencyType.USD.value();
		Double baseToRateDB = 1d;
		OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
		if (orderMain != null && StringUtils.isNotBlank(orderMain.getOrderCurrency())) {
			currency = orderMain.getOrderCurrency();
		}
		if (!CurrencyType.USD.value().equals(currency)) {
			baseToRateDB = publicService.getRateByBaseCurrencyToCurrency(currency, new Date());
        }
		String[] sampleTypeArr = Struts2Util.getRequest().getParameterValues("sampleTypeArr");
		String[] sampleConcArr = Struts2Util.getRequest().getParameterValues("sampleConcArr");
		String[] flagConcMeasArr = Struts2Util.getRequest().getParameterValues("flagConcMeasArr");
		String[] primerTypeArr = Struts2Util.getRequest().getParameterValues("primerTypeArr");
		String[] primerNameArr = Struts2Util.getRequest().getParameterValues("primerNameArr");
		String[] flagPowerReadArr = Struts2Util.getRequest().getParameterValues("flagPowerReadArr");
		String[] flagDataAnasArr = Struts2Util.getRequest().getParameterValues("flagDataAnasArr");
		String[] dataAnasArr = Struts2Util.getRequest().getParameterValues("dataAnasArr");
		String[] specialRequestArr = Struts2Util.getRequest().getParameterValues("specialRequestArr");
		String[] templateSizeArr = Struts2Util.getRequest().getParameterValues("templateSizeArr");
		String[] sessItemKeyArr = Struts2Util.getRequest().getParameterValues("sessItemKeyArr");
		String sessPlateId = null;
		for (String key : sessItemKeyArr) {
			if (StringUtils.isBlank(key) || !itemMap.containsKey(key)) {
				continue;
			}
			OrderItemDTO plItem = itemMap.get(key);
			if (plItem == null || plItem.getDnaSequencing() == null || StringUtils.isBlank(plItem.getDnaSequencing().getSessPlateId())) {
				continue;
			}
			sessPlateId = plItem.getDnaSequencing().getSessPlateId();
			break;
		}
		if (StringUtils.isBlank(sessPlateId)) {
			sessPlateId = SessionUtil.generateTempId();
		}
		//判断customer的Type是否为Internal,为True则计算后的价格除cost外都为0
		boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
		int mapSize = itemMap.size();
		for (int i=0;i<sampleNameArr.length;i++) {
			DnaSequencingDTO plate = new DnaSequencingDTO();
			plate.setSampleName(sampleNameArr[i]);
			plate.setSampleType(sampleTypeArr[i]);
			plate.setSampleConc(sampleConcArr[i]);
			plate.setFlagConcMeas(StringUtils.isBlank(flagConcMeasArr[i])?0:Integer.parseInt(flagConcMeasArr[i]));
			plate.setPrimerType(primerTypeArr[i]);
			plate.setPrimerName(primerNameArr[i]);
			plate.setFlagPowerRead(StringUtils.isBlank(flagPowerReadArr[i])?0:Integer.parseInt(flagPowerReadArr[i]));
			plate.setFlagDataAnas(StringUtils.isBlank(flagDataAnasArr[i])?0:Integer.parseInt(flagDataAnasArr[i]));
			plate.setDataAnas(dataAnasArr[i]);
			plate.setSpecialRequest(specialRequestArr[i]);
			plate.setTemplateSize(StringUtils.isBlank(templateSizeArr[i])?0:Integer.parseInt(templateSizeArr[i]));
			plate.setSessItemKey(sessItemKeyArr[i]);
			OrderItemDTO plateItemDTO = null;
			if (StringUtils.isNotBlank(plate.getSessItemKey())) {
				plateItemDTO = itemMap.get(plate.getSessItemKey());
				plate = (DnaSequencingDTO) ModelUtils.mergerModel(plate, plateItemDTO.getDnaSequencing());
				if (plate.getPlateId() != null) {
					sessPlateId = plate.getPlateId().toString();
				}
			} else if (StringUtils.isBlank(plate.getSessItemKey()) && srcDsItem.getDnaSequencing() != null 
					&& StringUtils.isBlank(srcDsItem.getDnaSequencing().getSampleName())
					&& StringUtils.isBlank(srcDsItem.getDnaSequencing().getCode())) {
				plate.setSessItemKey(srcDsItemKey);
				plate.setPlateId(null);
				plateItemDTO = srcDsItem;
			} else {
				String tmpId = SessionUtil.generateTempId();
				plate.setSessItemKey(tmpId);
				plateItemDTO = new OrderItemDTO();
		        ModelUtils.mergerModel(srcDsItem, plateItemDTO);
		        plateItemDTO.setItemNo(mapSize + 1);
		        mapSize++;
		        plateItemDTO.setOrderItemId(null);
		        plate.setCode(null);
		        plate.setPlateId(null);
		        plate.setSessPlateId(null);
		        plate.setVectorName(null);
		        plate.setPrimerConc(null);
		        plate.setResistance(null);
		        plate.setPrice(null);
		        plate.setComment(null);
			}
			if (StringUtils.isBlank(plate.getCode())) {
				plate.setCode(Constants.DS_SEQ_CODE_PLATE);
			}
			plate.setPlateNums(sampleNameArr.length);
			plate.setSessPlateId(sessPlateId);
			plate.setpName(pName);
			plate.setPlateLayout(Integer.parseInt(plateLayout));
			plateItemDTO.setDnaSequencing(plate);
			plateItemDTO.setNameShow(plateItemDTO.getName() + ": " + plate.getSampleName());
			// 开始计算DNA Sequencing Item的价格
			BigDecimal zero = new BigDecimal(0);
			DsPrice dsPrice = productService.getDsPrice(sampleTypeArr[i], primerTypeArr[i], sampleNameArr.length);
			if (dsPrice == null) {
				plateItemDTO.setUnitPrice(zero);
				plateItemDTO.setAmount(zero);
				plateItemDTO.setBasePrice(zero);
			} else {
				if (isInternalOrder) {
					orderItemService.updatePriceByInternal(plateItemDTO, sessOrderNo);
				} else {
					BigDecimal unitPrice = new BigDecimal(dsPrice.getPrice());
					if (plate.getFlagPowerRead() == 1) {
						unitPrice = unitPrice.add(new BigDecimal(dsPrice.getPowerRead()==null?0:dsPrice.getPowerRead()));
					}
					if (plate.getFlagDataAnas() == 1) {
						unitPrice = unitPrice.add(new BigDecimal(dsPrice.getAnalysis()==null?0:dsPrice.getAnalysis()));
					}
					if (plate.getFlagConcMeas() == 1) {
						unitPrice = unitPrice.add(new BigDecimal(dsPrice.getConcentration()==null?0:dsPrice.getConcentration()));
					}
					plateItemDTO.setBasePrice(unitPrice);
					plateItemDTO.setUnitPrice(unitPrice.multiply(new BigDecimal(baseToRateDB)));
					plateItemDTO.setAmount(plateItemDTO.getUnitPrice().multiply(new BigDecimal(plateItemDTO.getQuantity())));
				}
			}
			//计算价格结束
			plateItemDTO.setUpdateFlag(Y);
			itemMap.put(plate.getSessItemKey(), plateItemDTO);
		}
		Struts2Util.renderJson(map);
		return null;
	}

	/**
	 * 保存 geneSynthesis
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveGeneSynthesis() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		OrderGeneSynthesisDTO srcGeneSynthesis = orderItem.getGeneSynthesis();
		if (!StringUtils.isEmpty(geneSynthesis.getReadingFrame())) {
			if ((Y).equals(geneSynthesis.getReadingFrame())) {
				geneSynthesis.setReadingFrame(geneSynthesis.getReadingFrame()
						+ ":" + geneSynthesis.getGeneUsageText());
			}
		}
		OrderGeneSynthesisDTO targetGeneSynthesisDTO = (OrderGeneSynthesisDTO) ModelUtils
				.mergerModel(geneSynthesis, srcGeneSynthesis);
		String sequence = targetGeneSynthesisDTO.getSequence();
		String sequenceType = targetGeneSynthesisDTO.getSequenceType();

		if (geneSynthesis.getHostExpsOrganism() != null
				&& geneSynthesis.getHostExpsOrganism().contains("Other")
				&& !StringUtils.isEmpty(geneSynthesis.getHostExpsOrgOther())) {
			targetGeneSynthesisDTO.setHostExpsOrganism(geneSynthesis
					.getHostExpsOrganism()
					+ ";"
					+ geneSynthesis.getHostExpsOrgOther());
		}
		if (geneSynthesis.getScndExpsOrganism() != null
				&& geneSynthesis.getScndExpsOrganism().contains("Other")
				&& !StringUtils.isEmpty(geneSynthesis
						.getScndExpsOrganismOther())) {
			targetGeneSynthesisDTO.setScndExpsOrganism(geneSynthesis
					.getScndExpsOrganism()
					+ ";"
					+ geneSynthesis.getScndExpsOrganismOther());
		}
		if ("Length".equalsIgnoreCase(sequenceType)) {
			if (StringUtils.isNumeric(sequence)) {
				targetGeneSynthesisDTO.setSeqLength(Integer.parseInt(sequence));
			} else {
				targetGeneSynthesisDTO.setSeqLength(0);
			}
		} else if ("Protein".equalsIgnoreCase(sequenceType)) {
			targetGeneSynthesisDTO.setSeqLength(sequence.length() * 3);
		} else if ("DNA".equalsIgnoreCase(sequenceType)) {
			String threeSequence = targetGeneSynthesisDTO.getSequence3() == null ? ""
					: targetGeneSynthesisDTO.getSequence3();
			String fiveSequence = targetGeneSynthesisDTO.getSequence5() == null ? ""
					: targetGeneSynthesisDTO.getSequence5();
			String sequenceStr = targetGeneSynthesisDTO.getSequence() == null ? ""
					: targetGeneSynthesisDTO.getSequence();
			StringBuilder sequenceSB = new StringBuilder();
			sequenceSB.append(threeSequence).append(fiveSequence)
					.append(sequenceStr);
			targetGeneSynthesisDTO.setSeqLength(sequenceSB.toString().length());
		}
		if (StringUtils.isEmpty(orderItem.getParentId())) {
			orderItem.setNameShow(orderItem.getName() + ": "
					+ targetGeneSynthesisDTO.getGeneName());
		} else {
			orderItem.setNameShow(orderItem.getName());
		}
		orderItem.setGeneSynthesis(targetGeneSynthesisDTO);
		// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
		if (!isChanged) {
			// 计算基因分析类型Item的价格
			ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
			orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.geneSynthesis.name());
			orderServiceItemPiceDTO.setGeneSynthesisDTO(targetGeneSynthesisDTO);
			returnOrderServiceItemPrice(orderServiceItemPiceDTO);
			if (StringUtils.isNotBlank(orderItem.getTbdFlag()) && orderItem.getTbdFlag().equals("1")) {
				orderItem.getGeneSynthesis().setDfcltSeqFlag(Y);
			} else {
				orderItem.getGeneSynthesis().setDfcltSeqFlag(N);
			}
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		// add by zhanghuibin
		if (batchFlag) {
			return null;
		}
		Struts2Util.renderJson(orderItem);
		return null;
	}

    /*
    * gene计算价格
    * */


	/**
	 * 保存Orf Clone
	 * 
	 * @author zhang yong
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveOrfClone() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId) && orfClone != null && StringUtils.isNotBlank(orfClone.getPreOrfCloneId())
				&& itemMap.get(orfClone.getPreOrfCloneId()) != null && itemMap.get(orfClone.getPreOrfCloneId()).getClsId() == 6) {
			itemId = SessionUtil.generateTempId();
			//拷贝上一个ORF Clone信息
			OrderItemDTO preOrfClone = itemMap.get(orfClone.getPreOrfCloneId());
			orderItem = dozer.map(preOrfClone, OrderItemDTO.class);
			orderItem.setOrfClone(new OrderOrfCloneDTO());
			orderItem.setOrderItemId(null);
			orderItem.setCustCloning(null);
			orderItem.setPlasmidPreparation(null);
			orderItem.setItemNo(itemMap.size()+1);
		}
		OrderOrfCloneDTO srcOrfClone = orderItem.getOrfClone();
		OrderOrfCloneDTO targetOrfCloneDTO = (OrderOrfCloneDTO) ModelUtils.mergerModel(orfClone, srcOrfClone);
		String[] accessionInfo = targetOrfCloneDTO.getAccessionInfo().split("@@@");
		targetOrfCloneDTO.setAccessionNo(accessionInfo[0].split(":")[1]);
		targetOrfCloneDTO.setSeqType((accessionInfo[1].split(":")[1]).equals("Full Length")?"1":"0");
		orderItem.setUnitPrice(new BigDecimal(accessionInfo[2].split(":")[1]));
		orderItem.setBasePrice(orderItem.getUnitPrice());
		orderItem.setAmount(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity())));
		orderItem.setCost(new BigDecimal(accessionInfo[4].split(":")[1]));
		orderItem.setNameShow(orderItem.getName()+": "+targetOrfCloneDTO.getAccessionNo());
		boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
		if (isInternalOrder) {
			//判断customer的Type是否为Internal,为True则计算后的价格cost值赋给unitPrice
			orderItemService.updatePriceByInternal(orderItem, sessOrderNo);
		}
		orderItem.setOrfClone(targetOrfCloneDTO);
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		itemMap.put(itemId, orderItem);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 保存Oligo
	 * 
	 * @author zhang yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveOligo() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		OrderOligoDTO srcOligo = orderItem.getOligo();
		OrderOligoDTO targetOligoDTO = (OrderOligoDTO) ModelUtils.mergerModel(oligo, srcOligo);
		if (oligo.getDaPercent() == null || oligo.getDcPercent() == null || oligo.getDgPercent() == null || oligo.getDtPercent()== null) {
			targetOligoDTO.setDaPercent(null);
			targetOligoDTO.setDcPercent(null);
			targetOligoDTO.setDgPercent(null);
			targetOligoDTO.setDtPercent(null);
		}
		targetOligoDTO = orderService.getTargetOligoDTO(targetOligoDTO);
		if (StringUtils.isEmpty(orderItem.getParentId())) {
			orderItem.setNameShow(orderItem.getName() + ": " + targetOligoDTO.getOligoName());
		} else {
			orderItem.setNameShow(orderItem.getName());
		}
		if (StringUtils.isNotBlank(targetOligoDTO.getSequence())) {
			int length = targetOligoDTO.getSequence().replaceAll("\\/[^/]*\\/", "")
				.replaceAll("\\{.*\\}", "").replaceAll("\\(.*?\\)", "X").replaceAll(" ", "").length();
			targetOligoDTO.setSeqLength(length);
		}
		orderItem.setOligo(targetOligoDTO);
		// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
		if (!isChanged) {
			//方法里有对orderItem的价格设值
			quoteOrderService.getOligoPriceAndCost(sessOrderNo, orderItem);
			boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
			if (isInternalOrder) {
				orderItemService.updatePriceByInternal(orderItem, sessOrderNo);
			}
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		Struts2Util.renderJson(orderItem);
		return null;
	}

	/**
	 * 保存 rna
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveRna() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		RnaDTO srcRna = orderItem.getRna();
		if (!StringUtils.isEmpty(rna.getReadingFrame())) {
			if ((Y).equals(rna.getReadingFrame())) {
				rna.setReadingFrame(rna.getReadingFrame() + ":"
						+ rna.getGeneUsageText());
			}
		}
		RnaDTO targetRnaDTO = (RnaDTO) ModelUtils.mergerModel(rna, srcRna);
		String sequence = targetRnaDTO.getSequenceInsert();
		if (sequence != null) {
			targetRnaDTO.setSeqLength(sequence.length());
		}
		if (orderItem.getClsId().equals(7)) {
			targetRnaDTO.setType("siRNA");
		} else {
			targetRnaDTO.setType("miRNA");
		}
		if (StringUtils.isEmpty(orderItem.getParentId())) {
			orderItem.setNameShow(orderItem.getName() + ": "
					+ targetRnaDTO.getGeneName());
		} else {
			orderItem.setNameShow(orderItem.getName());
		}
		// add by zhanghuibin
		if ("Other".equals(targetRnaDTO.getVectorName())) {
			targetRnaDTO
					.setVectorName("Other:" + targetRnaDTO.getVectorOther());
		}
		orderItem.setRna(targetRnaDTO);
		// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
		if (!isChanged) {
			// 计算Rna类型Item的价格
			ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
			orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.rna
					.name());
			orderServiceItemPiceDTO.setRnaDTO(targetRnaDTO);
			returnOrderServiceItemPrice(orderServiceItemPiceDTO);
		}
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		Struts2Util.renderJson(orderItem);
		return null;
	}

	/**
	 * 保存 custCloning
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveCustCloning() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			try {
				orderItem = this.getNewChildSerItem(this.parentId, "custCloning", null);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				messageMap.put("hasException", Y);
				messageMap.put("exception", exDTO);
				// add by zhanghuibin
				if (batchFlag) {
					batchMessage = messageMap;
					return null;
				}
				Struts2Util.renderJson(messageMap);
				return null;
			}
			String preParentId = orderItem.getParentId();
			if (orderItem != null && orderItem.getParentId() != null && StringUtils.isNotEmpty(orderItem.getParentId())) {
				OrderItemDTO parentItem = null;
				if (StringUtil.isNumeric(orderItem.getParentId())) {
					parentItem = this.orderItemService.findById(Integer.parseInt(orderItem.getParentId()));
				} else {
					parentItem = (OrderItemDTO) itemMap.get(orderItem.getParentId());
				}
				if (parentItem != null) {
					String parentType = MoreDetailUtil.getServiceNameByClsId(parentItem.getClsId());
					if ("geneSynthesis".equals(parentType) || "mutagenesis".equals(parentType)) {
						orderItem.setSize(new Double(4));
						orderItem.setSizeUom("ug");
					}
				}
				// 从itemMap中获取新添加的Item的上一个Item的ItemId
				preParentId = orderItemService.getPreParentServiceId(itemMap, orderItem);
				orderItem.setPreParentId(preParentId);
			}
			this.insertNewChild(itemMap, preParentId, itemId, orderItem);
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		if (removeFlag != null && removeFlag.equals(1)) {
			// updateParentSizeByClone();
			if (StringUtils.isNumeric(itemId)) {
				List<Integer> delItemIdList = (List<Integer>) SessionUtil.getRow(SessionConstant.OrderItemDelList.value(), sessOrderNo);
				if (delItemIdList == null) {
					delItemIdList = new ArrayList<Integer>();
				}
				delItemIdList.add(Integer.parseInt(itemId));
				SessionUtil.insertRow(SessionConstant.OrderItemDelList.value(), sessOrderNo, delItemIdList);
				itemMap.remove(itemId);
			} else {
				itemMap.remove(itemId);
			}
			// add by zhanghuibin
			if (batchFlag) {
				return null;
			}
			retMap.put(itemId, new OrderItemDTO());
			Struts2Util.renderJson(retMap);
		} else {
			OrderCustCloningDTO srcCustCloning = orderItem.getCustCloning();
			if (srcCustCloning == null) {
				srcCustCloning = new OrderCustCloningDTO();
			}
			if (!StringUtils.isEmpty(custCloning.getReadingFrame())) {
				if (Y.equals(custCloning.getReadingFrame())) {
					custCloning.setReadingFrame(custCloning.getReadingFrame() + ":" + custCloning.getGeneUsageText());
				}
			}
			if (!StringUtils.isEmpty(custCloning.getTgtSeqSameFlag()) && Y.equals(custCloning.getTgtSeqSameFlag())) {
				srcCustCloning.setTgtSeqSameFlag(Y);
			} else {
				srcCustCloning.setTgtSeqSameFlag(N);
				custCloning.setTgtSeqSameFlag(N);
			}
			OrderCustCloningDTO targetCustCloningDTO = (OrderCustCloningDTO) ModelUtils.mergerModel(custCloning, srcCustCloning);
			if (custCloning.getTgtVector() != null && custCloning.getTgtVector().contains("Other")
					&& StringUtils.isNotBlank(custCloning.getTgtVectorOther())) {
				targetCustCloningDTO.setTgtVector("Other;" + custCloning.getTgtVectorOther());
				// Golf added 2011-10-21
				if(StringUtils.isNotBlank(custCloning.getTgtResistance())) {
					targetCustCloningDTO.setTgtResistance(custCloning.getTgtResistance());
				}
				if (custCloning.getTgtResistance() != null && custCloning.getTgtResistance().contains("Other")
						&& StringUtils.isNotBlank(custCloning.getTgtResistanceOther())) {
					targetCustCloningDTO.setTgtResistance("Other;" + custCloning.getTgtResistanceOther());
				}
				// End
			} else {
				targetCustCloningDTO.setTgtVectorSize(null);
				targetCustCloningDTO.setTgtResistance("");
				targetCustCloningDTO.setTgtVectorSeq("");
			}
			if (custCloning.getTmplVector() != null && custCloning.getTmplVector().contains("Other")
					&& StringUtils.isNotBlank(custCloning.getTmplVectorOther())) {
				targetCustCloningDTO.setTmplVector("Other;" + custCloning.getTmplVectorOther());
				if (StringUtils.isNotBlank(custCloning.getTmplResistance())) {
					targetCustCloningDTO.setTmplResistance(custCloning.getTmplResistance());
				}
				// Golf added 2011-10-21
				if (custCloning.getTmplResistance() != null && custCloning.getTmplResistance().contains("Other")
						&& StringUtils.isNotBlank(custCloning.getTmplResistanceOther())) {
					targetCustCloningDTO.setTmplResistance("Other;" + custCloning.getTmplResistanceOther());
				}
				// End
			} else {
				targetCustCloningDTO.setTmplVectorSize(null);
				targetCustCloningDTO.setTmplResistance("");
				targetCustCloningDTO.setTmplVectorSeq("");
			}
			if (("Gateway").equals(targetCustCloningDTO.getTgtCloningMethod())) {
				targetCustCloningDTO.setTgtCloningSite(null);
			}
			String parentId = orderItem.getParentId();
			if (StringUtils.isNotBlank(parentId)) {
				OrderItemDTO geneItem = itemMap.get(parentId);
				OrderGeneSynthesisDTO geneSynthesisDTO = geneItem.getGeneSynthesis();
				if (geneSynthesisDTO == null) {
					targetCustCloningDTO.setTgtSeqLength(0);
				}
				if (geneSynthesisDTO != null) {
					String seqTypeVal = geneSynthesisDTO.getSequenceType();
					int value;
					String sequenceStr = geneSynthesisDTO.getSequence();
					if (seqTypeVal.equals("DNA")) {
						String threeSequence = geneSynthesisDTO.getSequence3();
						String fiveSequence = geneSynthesisDTO.getSequence5();
						StringBuilder sequenceSB = new StringBuilder();
						sequenceSB.append(threeSequence).append(fiveSequence).append(sequenceStr);
						value = sequenceSB.toString().length();
					} else if (seqTypeVal.equals("Protein")) {
						value = sequenceStr.length() * 3;
					} else if (seqTypeVal.equals("Length")) {
						value = geneSynthesisDTO.getSeqLength();
					} else {
						throw new RuntimeException("no this gene sequence type exist");
					}
					targetCustCloningDTO.setTgtSeqLength(value);
				}
			} else {
				String tgtSequence = targetCustCloningDTO.getTgtSequence();
				if (Y.equals(targetCustCloningDTO.getTgtSeqSameFlag())) {
					tgtSequence = targetCustCloningDTO.getTmplSequence();
				}
				if (tgtSequence == null) {
					targetCustCloningDTO.setTgtSeqLength(0);
				} else {
					targetCustCloningDTO.setTgtSeqLength(tgtSequence.length());
				}
			}
			if (StringUtils.isEmpty(orderItem.getParentId())) {
				orderItem.setNameShow(orderItem.getName() + ": " + targetCustCloningDTO.getTmplInsertName());
			} else {
				orderItem.setNameShow(orderItem.getName());
			}
			if (StringUtils.isNotBlank(orderItem.getParentId())) {
				OrderItemDTO parentItemDTO = itemMap.get(orderItem.getParentId());
				targetCustCloningDTO.setParentClsId(parentItemDTO.getClsId());
			}
			orderItem.setCustCloning(targetCustCloningDTO);
			// updateParentSizeByClone();

			//add by Zhang Yong,父Item如果是ORF Clone，计算价格规则不同
			boolean isORFCloneSub = false;
			if (StringUtils.isNotBlank(orderItem.getParentId())) {
				OrderItemDTO parentItemDTO = itemMap.get(orderItem.getParentId());
				if (parentItemDTO.getClsId() == 6) {
					isORFCloneSub = true;
					if (StringUtils.isNotBlank(targetCustCloningDTO.getTgtVector()) && (pUC57.equals(targetCustCloningDTO.getTgtVector()) || targetCustCloningDTO.getTgtVector().startsWith(Other))) {
						BigDecimal zero = new BigDecimal(0);
						orderItem.setBasePrice(zero);
						orderItem.setUnitPrice(zero);
						orderItem.setAmount(zero);
						orderItem.setCost(zero);
						orderItem.setDiscount(zero);
						orderItem.setTbdFlag(tbd_1);
						if (pUC57.equals(targetCustCloningDTO.getTgtVector())) {
							orderItem.setTbdFlag(tbd_0);
						}
					} else {
						String[] accessionInfo = parentItemDTO.getOrfClone().getAccessionInfo().split("@@@");
						orderItem.setBasePrice(new BigDecimal(accessionInfo[3].split(":")[1]));
						orderItem.setUnitPrice(orderItem.getBasePrice());
						orderItem.setAmount(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity())));
						orderItem.setCost(new BigDecimal(accessionInfo[5].split(":")[1]));
						orderItem.setTbdFlag(tbd_0);
					}
					boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
					if (isInternalOrder) {
						//判断customer的Type是否为Internal,为True则计算后的价格cost值赋给unitPrice
						orderItemService.updatePriceByInternal(orderItem, sessOrderNo);
					}
				}
			}
			if (isORFCloneSub == false) {
				// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
				boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
				if (!isChanged) {
					// 计算CustCloning Item的价格
					ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
					orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.custCloning.name());
					orderServiceItemPiceDTO.setCustCloningDTO(targetCustCloningDTO);
					returnOrderServiceItemPrice(orderServiceItemPiceDTO);
				}
			}
			orderItem.setUpdateFlag(Y);
			// add by zhanghuibin
			if (batchFlag) {
				return null;
			}
			retMap.put(itemId, orderItem);
			Struts2Util.renderJson(retMap);
		}
		return null;
	}

	/**
	 * 如果是Clone子服务，如果同一parentItem的item中含有plassmit则更新clone的size和parent的size
	 * 
	 * @author Zhang Yong
	 */
	@SuppressWarnings("unused")
	private void updateParentSizeByClone() {
		if (StringUtils.isBlank(orderItem.getParentId())) {
			return;
		}
		OrderItemDTO parentDto = itemMap.get(orderItem.getParentId());
		if (parentDto == null || parentDto.getClsId() == null) {
			return;
		}
		OrderItemDTO plassmidDto = null;
		String plassmidKey = null;
		Iterator<Entry<String, OrderItemDTO>> iter = itemMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<String, OrderItemDTO> entry = iter.next();
			String key = entry.getKey();
			OrderItemDTO dto = entry.getValue();
			if (orderItem.getParentId().equals(dto.getParentId())
					&& dto.getClsId() == 10
					&& QuoteItemType.SERVICE.value().equalsIgnoreCase(
							dto.getType())) {
				plassmidDto = dto;
				plassmidKey = key;
				break;
			}
		}
		boolean containPlassmid = false;
		if (StringUtils.isNotBlank(plassmidKey) && plassmidDto != null) {
			containPlassmid = true;
		}
		if (removeFlag != null && removeFlag.equals(1)) {
			if (containPlassmid == true) {
				parentDto.setSize(orderItem.getSize());
				parentDto.setSizeUom(orderItem.getSizeUom());
				itemMap.put(orderItem.getParentId(), parentDto);
			}
		} else {
			if (containPlassmid == true
					&& plassmidDto.getPlasmidPreparation() != null) {
				OrderPlasmidPreparationDTO opdto = plassmidDto
						.getPlasmidPreparation();
				orderItem.setSize(opdto.getPrepWeight() != null ? opdto
						.getPrepWeight().doubleValue() : 0);
				orderItem.setSizeUom(opdto.getPrepWtUom());
				// parentItem的size和sizeUom重置
				List<Service> dbServicelist = servService
						.getServiceByCatalogNo(parentDto.getCatalogNo());
				if (dbServicelist != null && !dbServicelist.isEmpty()) {
					parentDto.setSize(dbServicelist.get(0).getSize());
					parentDto.setSizeUom(dbServicelist.get(0).getUom());
				} else {
					parentDto.setSize(0d);
					parentDto.setSizeUom("");
				}
				orderItem.setUpdateFlag(Y);
				itemMap.put(orderItem.getParentId(), parentDto);
			}
		}
	}

	/**
	 * 保存plasmid
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String savePlasmid() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		if (StringUtils.isNotBlank(itemId) && itemId.contains(",")) {
			itemId = itemId.substring(0, itemId.indexOf(","));
		}
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			try {
				orderItem = this.getNewChildSerItem(this.parentId,
						"plasmidPreparation", null);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				messageMap.put("hasException", Y);
				// add by zhanghuibin
				if (batchFlag) {
					batchMessage = messageMap;
					return null;
				}
				messageMap.put("exception", exDTO);
				Struts2Util.renderJson(messageMap);
				return null;
			}
			// 从itemMap中获取新添加的Item的上一个Item的ItemId
			String preParentId = orderItemService.getPreParentServiceId(itemMap,
					orderItem);
			orderItem.setPreParentId(preParentId);
			this.insertNewChild(itemMap, preParentId, itemId, orderItem);
		} else {
			orderItem = itemMap.get(itemId);
		}
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		if (removeFlag != null && removeFlag.equals(1)) {
			updateParentSizeByPlassmid();
			if (StringUtil.isNumeric(itemId)) {
				List<Integer> delItemIdList = (List<Integer>) SessionUtil.getRow(SessionConstant.OrderItemDelList.value(), sessOrderNo);
				if (delItemIdList == null) {
					delItemIdList = new ArrayList<Integer>();
				}
				delItemIdList.add(Integer.parseInt(itemId));
				SessionUtil.insertRow(SessionConstant.OrderItemDelList.value(), sessOrderNo, delItemIdList);
			}
			itemMap.remove(itemId);
			// add by zhanghuibin
			if (batchFlag) {
				return null;
			}
			// itemNo 顺序问题
			retMap.put(itemId, new OrderItemDTO());
			Struts2Util.renderJson(retMap);
		} else {
			OrderPlasmidPreparationDTO srcPlasmidPreparation = orderItem.getPlasmidPreparation();
			if (srcPlasmidPreparation == null) {
				srcPlasmidPreparation = new OrderPlasmidPreparationDTO();
			}
			if (StringUtils.isNotEmpty(plasmidPreparation.getSequence())) {
				postSeq(plasmidPreparation.getSequence());
			}
			if (plasmidPreparation.getBioBurdenAssay() == null || plasmidPreparation.getBioBurdenAssay().equals("false")) {
				plasmidPreparation.setBioBurdenAssay(N);
			}
			if (plasmidPreparation.getCustomSequencing() == null || plasmidPreparation.getCustomSequencing().equals("false")) {
				plasmidPreparation.setCustomSequencing(N);
			}
			if (StringUtils.isNotBlank(plasmidPreparation.getPrepWeightStr())) {
				String[] prepWeightStrs = plasmidPreparation.getPrepWeightStr()
						.trim().split(" ");
				if (prepWeightStrs.length == 2) {
					plasmidPreparation.setPrepWeight(BigDecimal.valueOf(Double.valueOf(prepWeightStrs[0])));
					plasmidPreparation.setPrepWtUom(prepWeightStrs[1]);
				} else {
					plasmidPreparation.setPrepWeight(BigDecimal.valueOf(Double.valueOf(prepWeightStrs[0])));
					plasmidPreparation.setPrepWtUom("mg");
				}
			}
			if (plasmidPreparation.getFragmentArray() != null) {
				plasmidPreparation.setFragment(StringUtil.implode(plasmidPreparation.getFragmentArray(), "::"));
			}
			OrderPlasmidPreparationDTO targetPlasmidPreparationDTO = (OrderPlasmidPreparationDTO) ModelUtils
					.mergerModel(plasmidPreparation, srcPlasmidPreparation);
			if (StringUtils.isBlank(orderItem.getParentId())) {
				orderItem.setNameShow(orderItem.getName() + ": " + targetPlasmidPreparationDTO.getPlasmidName());
			} else {
				orderItem.setNameShow(orderItem.getName());
				targetPlasmidPreparationDTO.setCopyNumber("High");
			}
			if (StringUtils.isBlank(targetPlasmidPreparationDTO.getCopyNumber())) {
				targetPlasmidPreparationDTO.setCopyNumber("High");
			}
			orderItem.setPlasmidPreparation(targetPlasmidPreparationDTO);
			if (targetPlasmidPreparationDTO.getPrepWeight() != null) {
				orderItem.setSize(targetPlasmidPreparationDTO.getPrepWeight().doubleValue());
				orderItem.setSizeUom(plasmidPreparation.getPrepWtUom());
			}
			// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
			boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
			if (!isChanged) {
				// 计算PlasmidPreparation Item的价格
				ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
				orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.plasmidPreparation.name());
				orderServiceItemPiceDTO.setPlasmidPreparationDTO(targetPlasmidPreparationDTO);
				returnOrderServiceItemPrice(orderServiceItemPiceDTO);
			}
			//add by Zhang Yong,父Item如果是ORF Clone且Plasmid Preparation weight为10ug，有些属性需赋为空
			if (StringUtils.isNotBlank(orderItem.getParentId())) {
				OrderItemDTO parentItemDTO = itemMap.get(orderItem.getParentId());
				if (parentItemDTO.getClsId() == 6) {
					if (StringUtils.isNotBlank(targetPlasmidPreparationDTO.getPrepWeightStr()) && ("10 ug".equals(targetPlasmidPreparationDTO.getPrepWeightStr()))) {
						targetPlasmidPreparationDTO.setQualityGrade(null);
					}
				}
			}
			orderItem.setUpdateFlag(Y);
			if (Y.equalsIgnoreCase(plasmidPreparation.getCustomSequencing())) {
				orderItem.setTbdFlag("1");
			}
			// add by zhanghuibin
			if (batchFlag) {
				return null;
			}
			retMap.put(itemId, orderItem);
			Struts2Util.renderJson(retMap);
		}
		return null;
	}

	/**
	 * 如果是Plassmit子服务，则更新parent的size或同一parentItem的clone的size
	 * 
	 * @author Zhang Yong
	 */
	private void updateParentSizeByPlassmid() {
		if (StringUtils.isBlank(orderItem.getParentId())) {
			return;
		}
		OrderItemDTO parentDto = itemMap.get(orderItem.getParentId());
		if (parentDto == null || parentDto.getClsId() == null) {
			return;
		}
		OrderItemDTO cloneDto = null;
		String cloneKey = null;
		Iterator<Entry<String, OrderItemDTO>> iter = itemMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<String, OrderItemDTO> entry = iter.next();
			String key = entry.getKey();
			OrderItemDTO dto = entry.getValue();
			if (orderItem.getParentId().equals(dto.getParentId())
					&& dto.getClsId() == 9
					&& QuoteItemType.SERVICE.value().equalsIgnoreCase(
							dto.getType())) {
				cloneDto = dto;
				cloneKey = key;
				break;
			}
		}
		boolean containClone = false;
		if (StringUtils.isNotBlank(cloneKey) && cloneDto != null) {
			containClone = true;
		}
		if (removeFlag != null && removeFlag.equals(1)) {
			if (containClone == true) {
				List<Service> dbServicelist = servService
						.getServiceByCatalogNo(cloneDto.getCatalogNo());
				if (dbServicelist != null && !dbServicelist.isEmpty()) {
					cloneDto.setSize(dbServicelist.get(0).getSize());
					cloneDto.setSizeUom(dbServicelist.get(0).getUom());
				} else {
					cloneDto.setSize(4d);
					cloneDto.setSizeUom("ug");
				}
				cloneDto.setUpdateFlag(Y);
				itemMap.put(cloneKey, cloneDto);
			} else {
				List<Service> dbServicelist = servService
						.getServiceByCatalogNo(parentDto.getCatalogNo());
				if (dbServicelist != null && !dbServicelist.isEmpty()) {
					parentDto.setSize(dbServicelist.get(0).getSize());
					parentDto.setSizeUom(dbServicelist.get(0).getUom());
				} else {
					parentDto.setSize(0d);
					parentDto.setSizeUom("");
				}
				parentDto.setUpdateFlag(Y);
				itemMap.put(orderItem.getParentId(), parentDto);
			}
		} else {
			if (containClone == true) {
				cloneDto.setSize(orderItem.getSize());
				cloneDto.setSizeUom(orderItem.getSizeUom());
				itemMap.put(cloneKey, cloneDto);
			} else {
				parentDto.setSize(orderItem.getSize());
				parentDto.setSizeUom(orderItem.getSizeUom());
				itemMap.put(orderItem.getParentId(), parentDto);
			}
			orderItem.setUpdateFlag(Y);
			orderItem.setSize(0d);
		}
	}

	/**
	 * 根据填入的sequence获得cloning_ready_flag和accession_no
	 */
	private void postSeq(String sequence) {
		StringBuffer url = new StringBuffer(
				"http://192.168.1.105/cgi-bin/blastsvr.cgi");
		String returnStr = "";
		StringBuffer param = new StringBuffer();
		param.append("dnaseq=").append(sequence);
		try {
			returnStr = UrlUtil.postReq(url.toString(), param.toString());
			if (StringUtils.isNotEmpty(returnStr)) {
				plasmidPreparation.setCloningReadyFlag(Y);
			} else {
				plasmidPreparation.setCloningReadyFlag(N);
				return;
			}
			String[] returnStrArray = returnStr.split("\t");
			if (returnStrArray != null) {
				if (returnStrArray.length >= 8) {
					plasmidPreparation.setAccessionNo(returnStrArray[6]);
				}
			}
		} catch (Exception e) {
			returnStr = "Error";
			e.printStackTrace();
		}

	}

	/**
	 * 保存mutagenesis
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveMuta() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			try {
				orderItem = this.getNewChildSerItem(this.parentId,
						"mutagenesis", null);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				messageMap.put("hasException", Y);
				messageMap.put("exception", exDTO);
				Struts2Util.renderJson(messageMap);
				return null;
			}
			String preParentId = orderItemService.getPreParentServiceId(itemMap,
					orderItem);
			orderItem.setPreParentId(preParentId);
			orderItem.setUpdateFlag(Y);
			this.insertNewChild(itemMap, preParentId, itemId, orderItem);
		}
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		if (removeFlag != null && removeFlag.equals(1)) {
			if (StringUtils.isNumeric(itemId)) {
				List<Integer> delItemIdList = (List<Integer>) SessionUtil
						.getRow(SessionConstant.OrderItemDelList.value(),
								sessOrderNo);
				if (delItemIdList == null) {
					delItemIdList = new ArrayList<Integer>();
				}
				delItemIdList.add(Integer.parseInt(itemId));
				SessionUtil.insertRow(SessionConstant.OrderItemDelList.value(),
						sessOrderNo, delItemIdList);
				itemMap.remove(itemId);
			} else {
				itemMap.remove(itemId);
			}
			// itemNo 顺序问题
			retMap.put(itemId, new OrderItemDTO());
			Struts2Util.renderJson(retMap);
		} else {
			OrderMutagenesisDTO srcMutagenesis = orderItem.getMutagenesis();
			if (srcMutagenesis == null) {
				srcMutagenesis = new OrderMutagenesisDTO();
			}
			if (!StringUtils.isEmpty(mutagenesis.getReadingFrame())) {
				if (Y.equals(mutagenesis.getReadingFrame())) {
					mutagenesis.setReadingFrame(mutagenesis.getReadingFrame()
							+ ":" + mutagenesis.getGeneUsageText());
				}
			}
			if (StringUtils.isNotBlank(mutagenesis.getVariantSequence())) {
				mutagenesis.setVariantSeqLength(mutagenesis.getVariantSequence().length());
			}
			OrderMutagenesisDTO targetMutagenesisDTO = (OrderMutagenesisDTO) ModelUtils
					.mergerModel(mutagenesis, srcMutagenesis);
			if (mutagenesis.getTmplVector() != null && mutagenesis.getTmplVector().contains("Other")
					&& !StringUtils.isEmpty(mutagenesis.getTmplVectorOther())) {
				targetMutagenesisDTO.setTmplVector(mutagenesis.getTmplVector() + ";" + mutagenesis.getTmplVectorOther());
				// Golf added 2011-10-21
				if (mutagenesis.getTmplResistance() != null && mutagenesis.getTmplResistance().contains("Other")
						&& !StringUtils.isEmpty(mutagenesis.getTmplResistanceOther())) {
					targetMutagenesisDTO.setTmplResistance(mutagenesis.getTmplResistance()
							+ ";"+ mutagenesis.getTmplResistanceOther());
				}
				// End
			} else {
				targetMutagenesisDTO.setTmplVectorSize(null);
				targetMutagenesisDTO.setTmplResistance(null);
				targetMutagenesisDTO.setTmplVectorSeq(null);
			}
			if (StringUtils.isEmpty(orderItem.getParentId())) {
				orderItem.setNameShow(orderItem.getName() + ": " + targetMutagenesisDTO.getVariantName());
			} else {
				orderItem.setNameShow(orderItem.getName());
			}
			orderItem.setMutagenesis(targetMutagenesisDTO);
			String pId = orderItem.getParentId();
			if (StringUtils.isNotBlank(pId)) {
				OrderItemDTO geneItem = itemMap.get(pId);
				String catalogId = geneItem.getCatalogId();
				orderItem.setCatalogId(catalogId);
			}
			// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
			boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
			if (!isChanged) {
				// 计算Mutagenesis Item的价格
				ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
				orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.mutagenesis.name());
				orderServiceItemPiceDTO.setMutagenesisDTO(targetMutagenesisDTO);
				returnOrderServiceItemPrice(orderServiceItemPiceDTO);
			}
			orderItem.setUpdateFlag(Y);
			retMap.put(itemId, orderItem);
			Struts2Util.renderJson(retMap);
		}
		return null;
	}
	
	/**
	 * 将mutaLib信息保存到session中
	 * @author Zhang Yong
	 * add date 2011-11-17
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveMutaLib () throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		//校验MutationLibraries数据是否正确
		Map<String, String> msgMap = orderItemService.checkMutaLib(mutaLib);
		if (!msgMap.isEmpty()) {
			Struts2Util.renderJson(msgMap);
			return null;
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		OrderMutationLibrariesDTO srcMutaLib = orderItem.getMutationLibraries();
		if (srcMutaLib == null) {
			srcMutaLib = new OrderMutationLibrariesDTO();
		}
		if (!StringUtils.isEmpty(mutaLib.getReadingFrame())) {
			if (Y.equals(mutaLib.getReadingFrame())) {
				mutaLib.setReadingFrame(mutaLib.getReadingFrame() + ":" + mutaLib.getGeneUsageText());
			}
		}
		OrderMutationLibrariesDTO targetMutaLibDTO = (OrderMutationLibrariesDTO) ModelUtils.mergerModel(mutaLib, srcMutaLib);
		if (Y.equals(mutaLib.getTmplFlag())&& StringUtils.isNotBlank(mutaLib.getTmplVector()) && mutaLib.getTmplVector().contains("Other")
				&& !StringUtils.isEmpty(mutaLib.getTmplVectorOther())) {
			targetMutaLibDTO.setTmplVector(mutaLib.getTmplVector() + ";" + mutaLib.getTmplVectorOther());
			if (mutaLib.getTmplResistance() != null && mutaLib.getTmplResistance().contains("Other")
					&& !StringUtils.isEmpty(mutaLib.getTmplResistanceOther())) {
				targetMutaLibDTO.setTmplResistance(mutaLib.getTmplResistance()+ ";"+ mutaLib.getTmplResistanceOther());
			}
		} else {
			targetMutaLibDTO.setTmplVectorSize(null);
			targetMutaLibDTO.setTmplResistance(null);
			targetMutaLibDTO.setTmplVectorSeq(null);
		}
		if (("Other").equalsIgnoreCase(mutaLib.getTgtVectorName())) {
			mutaLib.setCloningFlag(Y);
		} else {
			mutaLib.setCloningFlag(N);
		}
		orderItem.setNameShow(orderItem.getName() + ": " + targetMutaLibDTO.getConstructName());
		orderItem.setMutationLibraries(targetMutaLibDTO);
		BigDecimal zero = new BigDecimal(0);
		orderItem.setUnitPrice(zero);
		orderItem.setAmount(zero);
		orderItem.setCost(zero);
		orderItem.setTbdFlag("1");
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * @param orderServiceItemPiceDTO
	 * orderItemService中拷贝了此方法
	 */
	private void returnOrderServiceItemPrice(ServiceItemPiceDTO orderServiceItemPiceDTO) {
		orderItemService.returnOrderServiceItemPrice(sessOrderNo, orderItem, orderServiceItemPiceDTO);
	}

	@SuppressWarnings("unchecked")
	public String savePkgService() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			orderItem = this.getNewChildPkgSerItem(this.parentId, orderPkgService.getCatalogNo());
			String preParentId = orderItemService.getPreParentServiceId(itemMap, orderItem);
			orderItem.setPreParentId(preParentId);
			this.insertNewChild(itemMap, preParentId, itemId, orderItem);
		}
		PkgServiceDTO srcOrderPkgService = orderItem.getOrderPkgService();
		if (srcOrderPkgService == null) {
			srcOrderPkgService = new PkgServiceDTO();
		}
		PkgServiceDTO targetPkgServiceDTO = (PkgServiceDTO) ModelUtils.mergerModel(orderPkgService, srcOrderPkgService);
		targetPkgServiceDTO.setUpSymbol(orderItem.getUpSymbol());
		targetPkgServiceDTO.setCatalogNo(orderItem.getCatalogNo());
		targetPkgServiceDTO.setItemName(orderItem.getName());
		if (StringUtils.isEmpty(orderItem.getParentId()) && StringUtils.isNotBlank(targetPkgServiceDTO.getName())) {
			orderItem.setNameShow(orderItem.getName() + ": " + targetPkgServiceDTO.getName());
		} else {
			orderItem.setNameShow(orderItem.getName());
		}
		orderItem.setOrderPkgService(targetPkgServiceDTO);
		// 计算PkgService Item的价格
		if (MoreDetailUtil.isProteinGroupStep(orderItem.getClsId())) {
			if (StringUtils.isBlank(orderPkgService.getSeqFlag())
					|| !orderPkgService.getSeqFlag().equalsIgnoreCase(Y)
					|| (orderPkgService.getUnitPrice() != null && orderPkgService.getUnitPrice().doubleValue() != 0)
					|| (orderPkgService.getCost() != null && orderPkgService.getCost().doubleValue() != 0)) {
				orderItem.setUnitPrice(targetPkgServiceDTO.getUnitPrice() == null ? 
						new BigDecimal(0) : targetPkgServiceDTO.getUnitPrice());
				orderItem.setAmount(orderItem.getUnitPrice().multiply(
						new BigDecimal(orderItem.getQuantity())));
				orderItem.setCost(targetPkgServiceDTO.getCost() == null ? (orderItem
							.getUnitPrice().multiply(new BigDecimal(0.5))): targetPkgServiceDTO.getCost());
			} else {
				// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
				boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
				if (!isChanged) {
					// 计算PkgService Item的价格
					ServiceItemPiceDTO orderServiceItemPiceDTO = new ServiceItemPiceDTO();
					orderServiceItemPiceDTO.setServiceClsId(3);
					orderServiceItemPiceDTO.setCatalogId(orderItem.getCatalogId());
					orderServiceItemPiceDTO.setCatalogNo("SC1010");
					orderServiceItemPiceDTO.setQuantity(orderItem.getQuantity());
					orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.geneSynthesis.name());
					OrderGeneSynthesisDTO geneSynthesisDTO = new OrderGeneSynthesisDTO();
					String dnaSequence = targetPkgServiceDTO.getSequence();
					String proteinSeq = targetPkgServiceDTO.getProteinSeq();
					if (StringUtils.isNotBlank(dnaSequence)) {
						geneSynthesisDTO.setSequenceType("DNA");
						dnaSequence.replaceAll(" ", "");
						geneSynthesisDTO.setSequence(dnaSequence);
					} else {
						geneSynthesisDTO.setSequenceType("Protein");
						proteinSeq.replaceAll(" ", "");
						geneSynthesisDTO.setSequence(proteinSeq);
					}
					geneSynthesisDTO.setBpCost(targetPkgServiceDTO.getSeqBpCost());
					geneSynthesisDTO.setBpPrice(targetPkgServiceDTO.getSeqBpPrice());
					orderServiceItemPiceDTO.setGeneSynthesisDTO(geneSynthesisDTO);
					returnOrderServiceItemPrice(orderServiceItemPiceDTO);
					BigDecimal[] priceCost = servService.getPriceCost(orderItem.getCatalogNo(), 
							orderItem.getCatalogId(), targetPkgServiceDTO.getName(), Y);
					if (priceCost != null) {
						OrderMainDTO orderMain = (OrderMainDTO) SessionUtil.getRow(SessionConstant.Order.value(), sessOrderNo);
						Double rate = publicService.getRateByBaseCurrencyToCurrency(orderMain.getOrderCurrency(), new Date());
						if (rate == null) {
							throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
						}
						if (priceCost[0] != null) {
							orderItem.setBasePrice(orderItem.getBasePrice().add(priceCost[0]));
							orderItem.setUnitPrice(orderItem.getUnitPrice().add(priceCost[0].multiply(new BigDecimal(rate))));
							orderItem.setAmount(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity())));
						}
						if (priceCost[1] != null) {
							orderItem.setCost(orderItem.getCost().add(priceCost[1]));
						}
					}
					targetPkgServiceDTO.setUnitPrice(orderItem.getUnitPrice());
					targetPkgServiceDTO.setCost(orderItem.getCost());
				}
			}
			// 设置leadTime
			Integer parentStepServiceId = servService.getIntermediateParentServiceId(orderItem.getCatalogNo());
			if (parentStepServiceId != null) {
				orderItem.setShipSchedule(servService.getLeadTimeByServiceId(parentStepServiceId));
			}
			if (!StringUtils.isEmpty(parentId)) {
				Map<String, OrderItemDTO> childItemMap = quoteOrderService.getChildItemMap(itemMap, parentId);
				Integer totalLeadTime = quoteOrderService.getTotalLeadTime(childItemMap);
				OrderItemDTO parentItem = itemMap.get(parentId);
				parentItem.setShipSchedule(totalLeadTime);
			}
		}
		//判断customer的Type是否为Internal
		boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
		if (isInternalOrder) {
			//判断customer的Type是否为Internal,为True则计算后的价格cost值赋给unitPrice
			orderItemService.updatePriceByInternal(orderItem, sessOrderNo);
//			orderItem.getOrderPkgService().setUnitPrice(orderItem.getUnitPrice());
//			orderItem.getOrderPkgService().setCost(orderItem.getCost());
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String saveStdPeptide() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
		} else {
			orderItem = itemMap.get(itemId);
		}
		PeptideDTO srcOrderPeptide = null;
		if (orderItem != null) {
			srcOrderPeptide = orderItem.getPeptide();
		}
		if (srcOrderPeptide == null) {
			srcOrderPeptide = new PeptideDTO();
		}
		PeptideDTO targetPeptideDTO = (PeptideDTO) ModelUtils.mergerModel(peptide, srcOrderPeptide);
		if (StringUtils.isEmpty(targetPeptideDTO.getSequence())
				&& targetPeptideDTO.getSeqLength() != null) {
			StringBuffer seqBf = new StringBuffer();
			for (int i = 0; i < targetPeptideDTO.getSeqLength().intValue(); i++) {
				seqBf.append("A");
			}
			targetPeptideDTO.setSequence(seqBf.toString());
		}
		if (Y.equalsIgnoreCase(Struts2Util
				.getParameter("peptide.aminoChangeFlag"))
				&& !StringUtils.isNumeric(itemId)
				&& !targetPeptideDTO.isConvertFlag()) {
			String seqOrigin = targetPeptideDTO.getSequence();
			StringBuilder seqSb = new StringBuilder();
			String[] seqArray = seqOrigin.split("");
			for (String s : seqArray) {
				try {
					seqSb.append(biolibService.getPeptideCode(s));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			targetPeptideDTO.setSequence(seqSb.toString());
			targetPeptideDTO.setConvertFlag(true);
		}
		String pepSequence = targetPeptideDTO.getSequence() == null ? ""
				: targetPeptideDTO.getSequence();
		targetPeptideDTO.setSeqLength(StringUtil
				.calculateSeqLength(pepSequence));
		orderItem.setNameShow(orderItem.getName() + ": "
				+ targetPeptideDTO.getName());
		orderItem.setPeptide(targetPeptideDTO);

		// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
		if (!isChanged) {
			// 计算Peptide Item的价格
			ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
			orderServiceItemPiceDTO
					.setServicePriceType(ServicePriceType.peptide.name());
			orderServiceItemPiceDTO.setPeptideDTO(targetPeptideDTO);
			returnOrderServiceItemPrice(orderServiceItemPiceDTO);
		}
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		return NONE;
	}

	@SuppressWarnings("unchecked")
	public String saveBatchPeptide() throws Exception {
		Map<String, OrderItemDTO> retMap = new LinkedHashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
		} else {
			orderItem = itemMap.get(itemId);
		}
		newSequenceByInput(retMap);
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
		}
		return NONE;
	}

	// 批量生成Sequence并在Session中插入方法
	private void newSequenceByInput(Map<String, OrderItemDTO> retMap)
			throws IllegalAccessException, InvocationTargetException, Exception {
		String sequenceStr = peptide.getSequence();
		if (StringUtils.isNotEmpty(sequenceStr)) {
			String[] seqArray = sequenceStr.split("\n");
			int mapSize = itemMap.size();
			boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
			for (String str : seqArray) {
				String[] sArr = StringUtils.split(str, "|");
				if (sArr.length > 1) {
					PeptideDTO peptideDTO = new PeptideDTO();
					ModelUtils.mergerModel(peptide, peptideDTO);
					peptideDTO.setSequence(sArr[1]);
					peptideDTO.setName(sArr[0]);
					peptideDTO.setSeqLength(StringUtil.calculateSeqLength(peptideDTO.getSequence()));
					OrderItemDTO orderItemDTO = new OrderItemDTO();
					ModelUtils.mergerModel(orderItem, orderItemDTO);
					orderItemDTO.setItemNo(mapSize + 1);
                    mapSize++;
					// orderItemDTO.setItemNo(itemMap.size() + 1);
					orderItemDTO.setOrderItemId(null);
					orderItemDTO.setPeptide(peptideDTO);
					orderItemDTO.setParentId(itemId);
					orderItemDTO.setNameShow(orderItem.getName() + ":" + sArr[0]);

					// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
					boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
					if (!isChanged) {
						// 计算Peptide Item的价格
						ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
						orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
						orderServiceItemPiceDTO.setPeptideDTO(peptideDTO);
						this.returnOrderServiceItemPrice(orderServiceItemPiceDTO);
						orderItemDTO.setCost(orderServiceItemPiceDTO.getCost());
						orderItemDTO.setUnitPrice(orderServiceItemPiceDTO.getPrice());
						orderItemDTO.setAmount(orderServiceItemPiceDTO.getPrice().multiply(new BigDecimal(orderServiceItemPiceDTO.getQuantity())));
						if (isInternalOrder) {
							orderItemService.updatePriceByInternal(orderItemDTO, sessOrderNo);
						}
					}
					String tmpId = SessionUtil.generateTempId();
					orderItemDTO.setUpdateFlag(Y);
					retMap.put(tmpId, orderItemDTO);
					itemMap.put(tmpId, orderItemDTO);
				} else {
					Struts2Util.renderHtml("The sequence format is not valid.");
				}
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(), sessOrderNo, retMap);
		}
	}

	/**
	 * 保存antibody
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveAntibody() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		AntibodyDTO srcAntibody = orderItem.getAntibody();
		if (srcAntibody == null) {
			srcAntibody = new AntibodyDTO();
		}

		AntibodyDTO targetAntibodyDTO = ((AntibodyDTO) ModelUtils.mergerModel(
				antibody, srcAntibody));
		if (StringUtils.isEmpty(orderItem.getParentId())) {
			orderItem.setNameShow(orderItem.getName() + ": "
					+ targetAntibodyDTO.getAntibodyName());
		} else {
			orderItem.setNameShow(orderItem.getName());
		}
		orderItem.setAntibody(targetAntibodyDTO);
		// 计算Antibody Item的价格
		ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
		orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.antibody
				.name());
		orderServiceItemPiceDTO.setAntibodyDTO(targetAntibodyDTO);
		returnOrderServiceItemPrice(orderServiceItemPiceDTO);

		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String saveCustomService() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(orderItem);
			return null;
		}
		CustomServiceDTO srcCustomService = orderItem.getCustomService();
		if (srcCustomService == null) {
			srcCustomService = new CustomServiceDTO();
		}

		CustomServiceDTO targetCustomServiceDTO = ((CustomServiceDTO) ModelUtils.mergerModel(customService, srcCustomService));
		orderItem.setNameShow(orderItem.getName());
		orderItem.setCustomService(targetCustomServiceDTO);
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(orderItem);
		return null;
	}

	/**
	 * 切换 antigentype
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String changeAntigenType() throws Exception {
		// antibodyItemId
		// antigenType
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(antibodyItemId);
		antibody = orderItem.getAntibody();
		antibody.setAntigenType(antigenType);
		orderItem.setUpdateFlag(Y);
		Map<String, OrderItemDTO> groupItemMap = this
				.getGroupItemMap(antibodyItemId);
		Iterator<Entry<String, OrderItemDTO>> it = groupItemMap.entrySet()
				.iterator();
		List<String> itemIdList = new ArrayList<String>();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpId = entry.getKey();
			OrderItemDTO itemDTO = entry.getValue();
			if (itemDTO.getPeptide() != null) {
				itemIdList.add(tmpId);
				itemMap.remove(tmpId);
			}
		}
		Iterator<Entry<String, OrderItemDTO>> it2 = itemMap.entrySet()
				.iterator();
		int itemNo = 1;
		while (it2.hasNext()) {
			Entry<String, OrderItemDTO> entry = it2.next();
			OrderItemDTO itemDTO = entry.getValue();
			itemDTO.setItemNo(itemNo);
			itemNo++;
		}
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("message", "SUCCESS");
		retMap.put("itemIdList", itemIdList);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 保存antibody 子服务peptide
	 * 
	 * @author zouyulu
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String savePeptideForAntibody() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			try {
				orderItem = this.getNewChildSerItem(this.antibodyItemId, "Antibody-Pep", null);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				messageMap.put("hasException", Y);
				messageMap.put("exception", exDTO);
				Struts2Util.renderJson(messageMap);
				return null;
			}
			String preParentId = orderItem.getParentId();
			if (StringUtils.isNotEmpty(orderItem.getParentId())) {
				preParentId = orderItemService.getPreParentServiceId(itemMap, orderItem);
				orderItem.setPreParentId(preParentId);
			}
			this.insertNewChild(itemMap, preParentId, itemId, orderItem);
		}
		PeptideDTO srcPeptide = orderItem.getPeptide();
		if (srcPeptide == null) {
			srcPeptide = new PeptideDTO();
		}
		if (peptide == null) {
			peptide = new PeptideDTO();
		}
		PeptideDTO targetPeptideDTO = (PeptideDTO) ModelUtils.mergerModel(peptide, srcPeptide);
		if (StringUtils.isEmpty(targetPeptideDTO.getSequence()) && targetPeptideDTO.getSeqLength() != null) {
			StringBuffer seqBf = new StringBuffer();
			for (int i = 0; i < targetPeptideDTO.getSeqLength().intValue(); i++) {
				seqBf.append("A");
			}
			targetPeptideDTO.setSequence(seqBf.toString());
		}
		if (Y.equalsIgnoreCase(Struts2Util.getParameter("peptide.aminoChangeFlag"))
				&& !StringUtils.isNumeric(itemId) && !targetPeptideDTO.isConvertFlag()) {
			String seqOrigin = targetPeptideDTO.getSequence();
			StringBuilder seqSb = new StringBuilder();
			String[] seqArray = seqOrigin.split("");
			for (String s : seqArray) {
				try {
					seqSb.append(biolibService.getPeptideCode(s));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			targetPeptideDTO.setSequence(seqSb.toString());
			targetPeptideDTO.setConvertFlag(true);
		}
		String pepSequence = targetPeptideDTO.getSequence() == null ? "" : targetPeptideDTO.getSequence();
		targetPeptideDTO.setSeqLength(StringUtil.calculateSeqLength(pepSequence));
		orderItem.setNameShow(orderItem.getName() + ": " + (targetPeptideDTO.getName() == null ? "" : targetPeptideDTO.getName()));
		orderItem.setPeptide(targetPeptideDTO);
		if (!StringUtils.isEmpty(targetPeptideDTO.getSequence())) {
			// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
			boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
			if (!isChanged) {
				// 计算Peptide Item的价格
				ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
				orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
				orderServiceItemPiceDTO.setPeptideDTO(targetPeptideDTO);
				returnOrderServiceItemPrice(orderServiceItemPiceDTO);
			}
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		// }
		return null;
	}

	/**
	 * 检测当前Item的状态和parentItem的状态，如果为"CN"则返回
	 * 
	 * @param retMap
	 * @return
	 */
	private Map<String, OrderItemDTO> checkStatus(
			Map<String, OrderItemDTO> retMap) {
		if (StringUtils.isBlank(itemId) && removeFlag != null && removeFlag.intValue() == 1) {
			retMap.put(itemId, orderItem);
			return retMap;
		}
		if (!StringUtils.isEmpty(parentId)) {
			OrderItemDTO sessParentItem = (OrderItemDTO) itemMap.get(parentId);
			if (sessParentItem != null && (OrderItemStatusType.CN.value().equalsIgnoreCase(sessParentItem.getStatus()))) {
				if (StringUtils.isEmpty(itemId) || orderItem == null) {
					retMap.put(itemId, new OrderItemDTO());
				} else {
					retMap.put(itemId, orderItem);
				}
				return retMap;
			}
		}
		if ((StringUtils.isNotBlank(itemId) && orderItem == null) || (orderItem != null && OrderItemStatusType.CN.value().equalsIgnoreCase(orderItem.getStatus()))) {
			retMap.put(itemId, orderItem);
		}
		return retMap;
	}

	@SuppressWarnings("unchecked")
	public String calAntibodyPeptidePrice() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		if (StringUtils.isEmpty(itemId)) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		} else {
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter.hasNext();) {
				String key = iter.next();
				OrderItemDTO itemDto = itemMap.get(key);
				String parentId = itemDto.getParentId();
				if (itemDto.getClsId() != null && StringUtils.isNotEmpty(parentId)
						&& parentId.equals(itemId) && (itemDto.getClsId() == 1)) {
					orderItem = itemMap.get(key);
					itemId = key;
					break;
				}
			}
		}
		String parentId = orderItem.getParentId();
		if (StringUtils.isNotBlank(parentId)) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			OrderItemDTO orderItemDTO = null;
			StringBuilder sb = new StringBuilder();
			boolean bFlag = false;
			boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
			String secondPepKey = "";
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				orderItemDTO = entry.getValue();
				String key = entry.getKey();
				if (!(key.equals(itemId)) && orderItemDTO.getParentId() != null
						&& orderItemDTO.getParentId().equalsIgnoreCase(parentId)
						&& orderItemDTO.getClsId() == 1) {
					Double peptidePrice = orderItemDTO.getUnitPrice().doubleValue();
					sb.append(orderItem.getUnitPrice()).append(",").append(peptidePrice);
					bFlag = true;
					secondPepKey = key;
					break;
				}
			}
			if (bFlag == false) {
				sb.append(orderItem.getUnitPrice()).append(",").append(0);
			}
			ServiceItemPiceDTO serviceItemPiceDTO = getCostPrice(orderItem);
			serviceItemPiceDTO.setPriceStr(sb.toString());
			if (StringUtils.isNotBlank(orderItem.getParentId())) {
				OrderItemDTO parentDto = itemMap.get(orderItem.getParentId());
				if (parentDto != null) {
					serviceItemPiceDTO.setCatalogNo(parentDto.getCatalogNo());
				}
			}
			serviceItemPiceDTO = publicService.getAntibodyPeptidePrice(serviceItemPiceDTO);
			String priceStr = serviceItemPiceDTO.getPriceStr();
			String[] priceArr = priceStr.split(",");
			if (StringUtils.isNotBlank(secondPepKey)) {
				OrderItemDTO secondOrderItemDTO = itemMap.get(secondPepKey);
				secondOrderItemDTO.setUnitPrice(new BigDecimal(Double.parseDouble(priceArr[1])));
				secondOrderItemDTO.setAmount(new BigDecimal(ArithUtil.mul(Double.parseDouble(priceArr[1]), 
						serviceItemPiceDTO.getQuantity().doubleValue())));
				if (isInternalOrder) {
					orderItemService.updatePriceByInternal(secondOrderItemDTO, sessOrderNo);
				}
				retMap.put(secondPepKey, secondOrderItemDTO);
				itemMap.put(secondPepKey, secondOrderItemDTO);
			}
			orderItem.setUnitPrice(new BigDecimal(Double.parseDouble(priceArr[0])));
			orderItem.setAmount(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity())));
			if (isInternalOrder) {
				orderItemService.updatePriceByInternal(orderItem, sessOrderNo);
			}
		}
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		orderItem.setUpdateFlag(Y);
		retMap.put(itemId, orderItem);
		Struts2Util.renderJson(retMap);
		return NONE;
	}

	/**
	 * 上传附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String uploadFile() throws Exception {
		Map<String, OrderItemDTO> retMap = new LinkedHashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (orderItem == null) {
			Struts2Util.renderText("Fail to upload it, Please check the session");
			return null;
		}
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			OrderGeneSynthesisDTO geneSynthesis = orderItem.getGeneSynthesis();
			List<Document> docList = geneSynthesis.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			geneSynthesis.setDocumentList(docList);
			if (!docList.isEmpty()) {
				geneSynthesis.setServiceDocFlag(Y);
			}
			orderItem.setGeneSynthesis(geneSynthesis);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("rna")) {
			RnaDTO rna = orderItem.getRna();
			List<Document> docList = rna.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			rna.setDocumentList(docList);
			orderItem.setRna(rna);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			OrderCustCloningDTO custCloning = orderItem.getCustCloning();
			List<Document> docList = custCloning.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			custCloning.setDocumentList(docList);
			orderItem.setCustCloning(custCloning);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			OrderPlasmidPreparationDTO plasmidPreparation = orderItem.getPlasmidPreparation();
			List<Document> docList = plasmidPreparation.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			plasmidPreparation.setDocumentList(docList);
			orderItem.setPlasmidPreparation(plasmidPreparation);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			OrderMutagenesisDTO mutagenesis = orderItem.getMutagenesis();
			List<Document> docList = mutagenesis.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			mutagenesis.setDocumentList(docList);
			orderItem.setUpdateFlag(Y);
			orderItem.setMutagenesis(mutagenesis);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("libraryPeptide")) {
			PeptideDTO libraryPeptide = orderItem.getPeptide();
			if (libraryPeptide == null) {
				libraryPeptide = new PeptideDTO();
			}
			List<Document> docList = libraryPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			libraryPeptide.setDocumentList(docList);
			orderItem.setUpdateFlag(Y);
			orderItem.setPeptide(libraryPeptide);
			saveLibraryPeptide(retMap, docList);
			if (!retMap.isEmpty()) {
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("updateLibraryPeptide")) {
			if (StringUtils.isBlank(orderItem.getParentId())) {
				List<Integer> delItemIdList = new ArrayList<Integer>();
				Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, OrderItemDTO> entry = it.next();
					OrderItemDTO orderItemDTO = entry.getValue();
					String tmpItemId = entry.getKey();
					if (orderItemDTO != null && itemId.equalsIgnoreCase(orderItemDTO.getParentId())) {
						if (StringUtils.isNumeric(tmpItemId)) {
							delItemIdList.add(orderItemDTO.getOrderItemId());
							it.remove();
							itemMap.remove(tmpItemId);
						} else {
							it.remove();
							itemMap.remove(tmpItemId);
							// continue;
						}
					}
				}
				orderService.delOrderItemListForPeptide(delItemIdList);
			}
			PeptideDTO libraryPeptide = orderItem.getPeptide();
			if (libraryPeptide == null) {
				libraryPeptide = new PeptideDTO();
			}
			List<Document> docList = libraryPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			libraryPeptide.setDocumentList(docList);
			// orderItem.setPeptide(libraryPeptide);
			// itemId = orderItem.getParentId();
			// orderItem = itemMap.get(itemId);
			saveLibraryPeptide(retMap, docList);
			if (!retMap.isEmpty()) {
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("arrayPeptide")) {
			PeptideDTO arrayPeptide = orderItem.getPeptide();
			if (arrayPeptide == null) {
				arrayPeptide = new PeptideDTO();
			}
			List<Document> docList = arrayPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			arrayPeptide.setDocumentList(docList);
			orderItem.setUpdateFlag(Y);
			orderItem.setPeptide(arrayPeptide);
			saveArrayPeptide(retMap, docList);
			if (!retMap.isEmpty()) {
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("orfClone")) {
			OrderOrfCloneDTO orfClone = orderItem.getOrfClone();
			if (orfClone == null) {
				orfClone = new OrderOrfCloneDTO();
			}
			List<Document> docList = orfClone.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			orfClone.setDocumentList(docList);
			if (docList != null && !docList.isEmpty()) {
				orfClone.setServiceDocFlag(Y);
			} else {
				orfClone.setServiceDocFlag(N);
			}
			orderItem.setOrfClone(orfClone);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("updateArrayPeptide")) {
			if (StringUtils.isBlank(orderItem.getParentId())) {
				List<Integer> delItemIdList = new ArrayList<Integer>();
				Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, OrderItemDTO> entry = it.next();
					OrderItemDTO orderItemDTO = entry.getValue();
					String tmpItemId = entry.getKey();
					if (orderItemDTO.getParentId() != null && orderItemDTO.getParentId().equalsIgnoreCase(itemId)) {
						if (StringUtils.isNumeric(tmpItemId)) {
							delItemIdList.add(orderItemDTO.getOrderItemId());
							it.remove();
							itemMap.remove(tmpItemId);
						} else {
							it.remove();
							itemMap.remove(tmpItemId);
							// continue;
						}
					}
				}
				orderService.delOrderItemListForPeptide(delItemIdList);
			}
			PeptideDTO arrayPeptide = orderItem.getPeptide();
			if (arrayPeptide == null) {
				arrayPeptide = new PeptideDTO();
			}
			List<Document> docList = arrayPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			arrayPeptide.setDocumentList(docList);
			// itemId = orderItem.getParentId();
			// orderItem = itemMap.get(itemId);
			// orderItem.setPeptide(arrayPeptide);
			saveArrayPeptide(retMap, docList);

			if (!retMap.isEmpty()) {
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("oligo")) {
			oligo = orderItem.getOligo();
			if (oligo == null) {
				Struts2Util.renderText("Fail to batch oligo, Please check the session");
				return null;
			}
			String rtnMessage = saveBatchOligo(retMap);
			if (rtnMessage != null) {
				Struts2Util.renderText(rtnMessage);
			}
			if (rtnMessage == null && !retMap.isEmpty()) {
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("mutaLib")) {
			OrderMutationLibrariesDTO mutaLib = orderItem.getMutationLibraries();
			if (mutaLib == null) {
				mutaLib = new OrderMutationLibrariesDTO();
			}
			List<Document> docList = mutaLib.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadOrderServiceFile(docList, newDocList);
			mutaLib.setDocumentList(docList);
			if (docList != null && !docList.isEmpty()) {
				mutaLib.setServiceDocFlag(Y);
			} else {
				mutaLib.setServiceDocFlag(N);
			}
			orderItem.setUpdateFlag(Y);
			orderItem.setMutationLibraries(mutaLib);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String returnItemMap() throws Exception {
		Map<String, OrderItemDTO> retMap = new HashMap<String, OrderItemDTO>();
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (StringUtils.isNotBlank(orderItem.getParentId())) {
			Struts2Util.renderText("Fail to upload it, Please check the item");
			return null;
		}
		if (StringUtils.isBlank(orderItem.getParentId())) {
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				OrderItemDTO orderItemDTO = entry.getValue();
				String tmpItemId = entry.getKey();
				if (orderItemDTO != null && itemId.equalsIgnoreCase(orderItemDTO.getParentId())) {
					retMap.put(tmpItemId, orderItemDTO);
				}
			}
		}
		if (!retMap.isEmpty()) {
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
		}
		return null;
	}

	/**
	 * @param retMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 * @throws IOException
	 */
	private void saveArrayPeptide(Map<String, OrderItemDTO> retMap,
			List<Document> docList) throws IllegalAccessException,
			InvocationTargetException, Exception, IOException {
		SessionUtil.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		String peptideName = peptide.getName();
		if (StringUtils.isEmpty(peptideName)) {
			peptideName = "Peptide";
		}
		String sequence = peptide.getSequence();
		if (StringUtils.isNotEmpty(sequence)) {
			int peptideCount = 0;
			String[] seqArray = sequence.split("\r");
			int allCount = seqArray.length + 1;
			StringBuilder sb = new StringBuilder();
			int mapSize = itemMap.size();
			if (seqArray.length >= 48) {
				for (String str : seqArray) {
					int seqLength = checkSeqlength(str);
					if (seqLength >= 5 && seqLength <= 15) {
						peptideCount++;
						newMultipleSessionSequence(retMap, peptideName, peptideCount, allCount, str, "SC1507", mapSize);
						mapSize++;
						sb.append(str).append("\r");
					} else {
						Struts2Util.renderText("The peptide sequence length must be between 5 and 15.");
						return;
					}
				}
				getPeptideForArrAndLibPrice(retMap, peptideCount, "SC1507");
				createSeqFileForCopy(docList, sb);
			} else {
				Struts2Util.renderHtml("The minimal peptides number is 48.");
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessOrderNo, retMap);
		} else {
			int mapSize = itemMap.size();
			for (int i = 0; i < upload.size(); i++) {
				String uploadFileNameStr = uploadFileName.get(i);
				String fileNameSuffix = uploadFileNameStr
						.substring(uploadFileNameStr.lastIndexOf(".") + 1);
				// The upload file type is Excel type
				if (fileNameSuffix.equalsIgnoreCase("xls")
						|| fileNameSuffix.equalsIgnoreCase("xls")) {
					List<ArrayList<String>> excelList = excelUtil.read(
							upload.get(i), uploadFileName.get(i));
					System.out.println(excelList);
					if (excelList != null && !excelList.isEmpty()) {
						int peptideCount = 0;
						if (excelList.size() >= 48) {
							for (List<String> strList : excelList) {
								String seq = strList.get(0);
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 15) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount,
											excelList.size() + 1, seq,
											"SC1507", mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount,
									"SC1507");
						} else {
							Struts2Util
									.renderText("The minimal peptides number is 48.");
						}
					}
					// //The upload file type is TXT or CSV type
				} else if (fileNameSuffix.equalsIgnoreCase("txt")
						|| fileNameSuffix.equalsIgnoreCase("csv")) {
					List<String> strList = FilelUtil.getLineList(upload.get(i));
					if (strList != null && !strList.isEmpty()) {
						int peptideCount = 0;
						if (strList.size() >= 48) {
							for (String str : strList) {
								String seq = str;
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 15) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount,
											strList.size() + 1, seq, "SC1507",
											mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount,
									"SC1507");
						} else {
							Struts2Util
									.renderText("The minimal peptides number is 48.");
						}
					}
				} else {

				}
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessOrderNo, retMap);
		}
	}

	/**
	 * @param retMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 * @throws IOException
	 */
	private void saveLibraryPeptide(Map<String, OrderItemDTO> retMap,
			List<Document> docList) throws IllegalAccessException,
			InvocationTargetException, Exception, IOException {
		SessionUtil
				.deleteRow(SessionConstant.OrderPackage.value(), sessOrderNo);
		// String catalogNo = peptide.getCatalogNo();
		String catalogNo = itemMap.get(itemId).getCatalogNo();
		String peptideName = peptide.getName();
		String sequence = peptide.getSequence();
		if (StringUtils.isNotEmpty(sequence)) {
			String[] seqArray = sequence.split("\r");
			int allCount = seqArray.length + 1;
			StringBuilder sb = new StringBuilder();
			int peptideCount = 0;
			if (catalogNo.equals("SC1177") && seqArray.length >= 24) {
				int mapSize = itemMap.size();
				for (String str : seqArray) {
					int seqLength = checkSeqlength(str);
					if (seqLength >= 5 && seqLength <= 20) {
						sb.append(str).append("\r");
						peptideCount++;
						newMultipleSessionSequence(retMap, peptideName,
								peptideCount, allCount, str, catalogNo, mapSize);
						mapSize++;
					} else {
						Struts2Util
								.renderHtml("The peptide sequence length must be between 5 and 20.");
						return;
					}
				}

				getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
				createSeqFileForCopy(docList, sb);
			} else if (catalogNo.equals("SC1487") && seqArray.length >= 24) {
				int mapSize = itemMap.size();
				for (String str : seqArray) {
					int seqLength = checkSeqlength(str);
					if (seqLength >= 5 && seqLength <= 18) {
						sb.append(str).append("\r");
						peptideCount++;
						newMultipleSessionSequence(retMap, peptideName,
								peptideCount, seqArray.length + 1, str,
								catalogNo, mapSize);
						mapSize++;
					} else {
						Struts2Util
								.renderHtml("The peptide sequence length must be between 5 and 18.");
						return;
					}
				}

				getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
				createSeqFileForCopy(docList, sb);
			} else {
				Struts2Util.renderText("The minimal peptides number is 24.");
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessOrderNo, retMap);
		} else {
			int mapSize = itemMap.size();
			for (int i = 0; i < upload.size(); i++) {
				String uploadFileNameStr = uploadFileName.get(i);
				String fileNameSuffix = uploadFileNameStr
						.substring(uploadFileNameStr.lastIndexOf(".") + 1);
				// The upload file type is Excel type
				if (fileNameSuffix.equalsIgnoreCase("xls")
						|| fileNameSuffix.equalsIgnoreCase("xls")) {
					List<ArrayList<String>> excelList = excelUtil.read(
							upload.get(i), uploadFileName.get(i));
					System.out.println(excelList);
					if (excelList != null && !excelList.isEmpty()) {
						int peptideCount = 0;
						if (catalogNo.equals("SC1177")
								&& excelList.size() >= 24) {
							for (List<String> strList : excelList) {
								String seq = strList.get(0);
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 20) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount,
											excelList.size() + 1, seq,
											catalogNo, mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount,
									catalogNo);
						} else if (catalogNo.equals("SC1487")
								&& excelList.size() >= 24) {
							for (List<String> strList : excelList) {
								String seq = strList.get(0);
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 18) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount,
											excelList.size() + 1, seq,
											catalogNo, mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 18.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount,
									catalogNo);
						} else {
							Struts2Util
									.renderText("The minimal peptides number is 24.");
						}
					}
					// //The upload file type is TXT or CSV type
				} else if (fileNameSuffix.equalsIgnoreCase("txt")
						|| fileNameSuffix.equalsIgnoreCase("csv")) {
					List<String> strList = FilelUtil.getLineList(upload.get(i));
					if (strList != null && !strList.isEmpty()) {
						int peptideCount = 0;
						if (catalogNo.equals("SC1177") && strList.size() >= 24) {
							for (String str : strList) {
								String seq = str;
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 20) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount,
											strList.size() + 1, seq, catalogNo,
											mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount,
									catalogNo);
						} else if (catalogNo.equals("SC1487")
								&& strList.size() >= 24) {
							for (String str : strList) {
								String seq = str;
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 18) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount,
											strList.size() + 1, seq, catalogNo,
											mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 18.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount,
									catalogNo);
						} else {
							Struts2Util
									.renderText("The minimal peptides number is 24.");
						}
					}
				} else {

				}
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessOrderNo, retMap);
		}
	}

	/**
	 * 批量保存Oligo到session中
	 * 
	 * @param retMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 * @throws IOException
	 */
	public String saveBatchOligo(Map<String, OrderItemDTO> retMap)
			throws IllegalAccessException, InvocationTargetException,
			Exception, IOException {
		String rtnMessage = null;
		String batchBackbone = Struts2Util.getParameter("batchBackbone");
		if (StringUtils.isEmpty(batchBackbone)) {
			rtnMessage = "Batch oligo failure, Please select a Backbone.";
			return rtnMessage;
		}
		String batchSynthesisScales = Struts2Util.getParameter("batchSynthesisScales");
		String batchPurification = Struts2Util.getParameter("batchPurification");
		if (StringUtils.isEmpty(batchPurification)) {
			rtnMessage = "Batch oligo failure, Please select a Purification.";
			return rtnMessage;
		}
		String batchModification5 = Struts2Util.getParameter("batchModification5");
		String batchModification3 = Struts2Util.getParameter("batchModification3");
		String sequence = Struts2Util.getParameter("batchOligoSequence");
		
		if (batchBackbone.contains("@")) {
			batchBackbone = batchBackbone.replaceAll("@", "'");
		}
		String batchAliquotingInto = Struts2Util.getParameter("batchAliquotingInto");
		String batchAliquotingSize = Struts2Util.getParameter("batchAliquotingSize");
		String batchCustNo = Struts2Util.getParameter("batchCustNo");
		String excelUrl = Struts2Util.getParameter("excelUrl");
		Map<String, Object> rtnMap = orderItemService.batchOligo(itemId, sequence, batchCustNo, batchBackbone, 
				batchSynthesisScales, batchPurification, batchModification5, batchModification3, 
				batchAliquotingInto, batchAliquotingSize, excelUrl, upload, uploadFileName, 
				retMap, sessOrderNo);
		if (StringUtil.isNumeric(batchCustNo)) {
			Struts2Util.renderJson(rtnMap);
			return null;
		}
		if (rtnMap != null && rtnMap.containsKey("rtnMessage")) {
			rtnMessage = (String) rtnMap.get("rtnMessage");
		}
		return rtnMessage;
	}

	private void getPeptideForArrAndLibPrice(Map<String, OrderItemDTO> retMap,
			int peptideCount, String catalogNo) throws IllegalAccessException,
			InvocationTargetException {
		PeptideDTO peptideDTO = new PeptideDTO();
		peptideDTO.setPeptideCount(peptideCount);
		ModelUtils.mergerModel(peptide, peptideDTO);
		ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(orderItem);
		orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
		orderServiceItemPiceDTO.setPeptideDTO(peptideDTO);
		orderServiceItemPiceDTO.setCatalogNo(catalogNo);
		orderServiceItemPiceDTO = publicService.calculateServicePrice(orderServiceItemPiceDTO);
		boolean isInternalOrder = orderItemService.isInternalOrder(sessOrderNo);
		for (Iterator<String> iter = retMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			OrderItemDTO orderItemDTO = (OrderItemDTO) retMap.get(key);
			String itemName = orderItem.getName();
			int i = itemName.lastIndexOf("-");
			itemName = itemName.substring(0, i + 1) + catalogNo;
			orderItemDTO.setName(itemName);
			orderItemService.updateOrderItemPrice(orderItemDTO, orderServiceItemPiceDTO, sessOrderNo);
			if (isInternalOrder) {
				orderItemService.updatePriceByInternal(orderItemDTO, sessOrderNo);
			}
		}
	}

	/**
	 * @param docList
	 * @param sb
	 * @throws Exception
	 */
	private void createSeqFileForCopy(List<Document> docList, StringBuilder sb)
			throws Exception {
		if (sb.length() > 0) {
			String srcName = generateSequenceSourceName(refType, itemId);
			String targetName = exportFile(sb.substring(0, sb.length() - 1),
					srcName);
			Document doc = new Document();
			doc.setDocName(srcName);
			doc.setRefType(refType);
			doc.setFilePath(targetName);
			docList.add(doc);
		}
	}

	/**
	 * @param str
	 * @return
	 */
	private int checkSeqlength(String str) {
		Pattern pattern = Pattern.compile("\\{.+\\}");
		Matcher matcher = pattern.matcher(str);
		int seqLength = matcher.replaceAll("a").length();
		return seqLength;
	}

	/**
	 * Library Peptide和Array Peptide复制粘贴Sequence时所调用方法
	 * 
	 * @param retMap
	 * @param peptideName
	 * @param seq
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	private void newMultipleSessionSequence(Map<String, OrderItemDTO> retMap,
			String peptideName, int pepCount, int allCount, String seq,
			String catalogNo, int mapSize) throws IllegalAccessException,
			InvocationTargetException, Exception {
		PeptideDTO peptideDTO = new PeptideDTO();
		ModelUtils.mergerModel(peptide, peptideDTO);
		peptideDTO.setSequence(seq);
		if (StringUtils.isNotBlank(peptideName)) {
			//peptideDTO.setName(peptideName + "-" + (allCount - pepCount));
			peptideDTO.setName(peptideName + "-" + pepCount);
		} else {
			//peptideDTO.setName((allCount - pepCount) + "");
			peptideDTO.setName("Peptide" + "-" + pepCount);
		}
		peptideDTO.setSeqLength(StringUtil.calculateSeqLength(peptideDTO.getSequence()));
		// System.out.println(peptideDTO);
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		ModelUtils.mergerModel(orderItem, orderItemDTO);
		// orderItemDTO.setItemNo(itemMap.size() + 1);
		orderItemDTO.setItemNo(mapSize + 1);
		orderItemDTO.setOrderItemId(null);
		orderItemDTO.setPeptide(peptideDTO);
		orderItemDTO.setCatalogNo(catalogNo);
		orderItemDTO.setParentId(itemId);
		orderItemDTO.setNameShow(orderItem.getName());

		// 查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = this.orderItemService.checkPriceChage(orderItem);
		if (!isChanged) {
			// 计算Peptide Item的价格
			// ServiceItemPiceDTO orderServiceItemPiceDTO =
			// getCostPrice(orderItem);
			// orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
			// orderServiceItemPiceDTO.setPeptideDTO(peptideDTO);
			// orderServiceItemPiceDTO =
			// publicService.calculateServicePrice(orderServiceItemPiceDTO);
			//
			// orderItemDTO.setCost(orderServiceItemPiceDTO.getCost());
			// orderItemDTO.setUnitPrice(orderServiceItemPiceDTO.getPrice());
			// orderItemDTO.setAmount(orderServiceItemPiceDTO.getPrice().multiply(new
			// BigDecimal(orderServiceItemPiceDTO.getQuantity())));
		}

		String tmpId = SessionUtil.generateTempId();
		orderItemDTO.setUpdateFlag(Y);
		retMap.put(tmpId, orderItemDTO);
		itemMap.put(tmpId, orderItemDTO);
	}

	

	private String exportFile(final String sequenceContent,
			final String sourceFileName) throws Exception {
		FileWriter writer = null;
		File file = null;
		String fileName = generateSequenceTargetName(sourceFileName);
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(fileService.getUploadPath())
					.append(FilePathConstant.orderService.value()).append("/");
			file = new File(sb.toString(), fileName);
			writer = new FileWriter(file);
			writer.write(sequenceContent);
			writer.flush();
			writer.close();
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String generateSequenceTargetName(String srcFileName)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		return sb.append(SessionUtil.generateTempId()).append("_")
				.append(srcFileName).append(".").append("txt").toString();
	}

	private String generateSequenceSourceName(String refType, String refId) {
		String srcFileName = "sequence.txt";
		if (!StringUtils.isNumeric(itemId)) {
			return srcFileName;
		} else {
			StringBuilder sb = new StringBuilder();
			int rId = Integer.parseInt(refId);
			int count = orderService.getDocument(refType, rId).size();
			return sb.append(count + 1).append("_").append("sequence")
					.append(".txt").toString();
		}
	}

	private void uploadOrderServiceFile(List<Document> docList,
			List<Document> newDocList) throws Exception {
		if (upload != null && !upload.isEmpty()) {
			for (int i = 0; i < upload.size(); i++) {
				String srcFileName = uploadFileName.get(i);
				Document doc = new Document();
				doc.setDocName(srcFileName);
				uploadFileName.set(i, SessionUtil.generateTempId()
						+ srcFileName.substring(srcFileName.lastIndexOf(".")));
				doc.setRefType(refType);
				doc.setFilePath(FilePathConstant.orderService.value() + "/"
						+ uploadFileName.get(i));
				docList.add(doc);
				newDocList.add(doc);
			}
			fileService.uploadFile(upload, uploadContentType, uploadFileName,
					FilePathConstant.orderService.value());
		}
	}
	
	/**
	 * 更新session中ORF Clone服务的GenePrice、SubcloningPrice，前台显示用
	 * @author Zhang Yong
	 * add date 2011-12-04
	 * @param topClsId
	 * @param parentOrderItemDTO
	 */
	private void uptSessOrfServicePrice (Integer topClsId, OrderItemDTO parentOrderItemDTO) {
		if (topClsId.intValue() == 6) {
			if (orderItem.getClsId() == 6) {
				for (String key : itemMap.keySet()) {
					OrderItemDTO ot = itemMap.get(key);
					if (CatalogType.SERVICE.value().equals(ot.getType()) && itemId.equals(ot.getParentId()) && ot.getClsId().intValue() == 9) {
						orderItem.getOrfClone().setSubcloningPrice(ot.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						break;
					}
				}
				orderItem.getOrfClone().setGenePrice(orderItem.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				itemMap.put(itemId, orderItem);
				orderServiceDTO.setOrfClone(orderItem.getOrfClone());
			} else if (orderItem.getClsId() == 9) {
				parentOrderItemDTO.getOrfClone().setGenePrice(parentOrderItemDTO.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				parentOrderItemDTO.getOrfClone().setSubcloningPrice(orderItem.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				itemMap.put(orderItem.getParentId(), parentOrderItemDTO);
				orderServiceDTO.setOrfClone(parentOrderItemDTO.getOrfClone());
			} else if (orderItem.getClsId().intValue() == 10) {
				OrderItemDTO subCloning = null;
				for (String key : itemMap.keySet()) {
					OrderItemDTO ot = itemMap.get(key);
					if (CatalogType.SERVICE.value().equals(ot.getType()) && orderItem.getParentId().equals(ot.getParentId()) && ot.getClsId().intValue() == 9) {
						subCloning = ot;
						break;
					}
				}
				parentOrderItemDTO.getOrfClone().setGenePrice(parentOrderItemDTO.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if (subCloning != null && subCloning.getUnitPrice() != null) {
					parentOrderItemDTO.getOrfClone().setSubcloningPrice(subCloning.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				itemMap.put(orderItem.getParentId(), parentOrderItemDTO);
				orderServiceDTO.setOrfClone(parentOrderItemDTO.getOrfClone());
			}
			vectorList = quoteOrderService.getVectorList();
			orfCloneSiteList = quoteOrderService.getOrfCloneSite(vectorList, orderServiceDTO);
		}
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteFile() throws Exception {
		itemMap = (Map<String, OrderItemDTO>) SessionUtil.getRow(
				SessionConstant.OrderItemList.value(), sessOrderNo);
		orderItem = itemMap.get(itemId);
		if (orderItem == null) {
			Struts2Util
					.renderText("Fail to upload it, Please check the session");
			return null;
		}
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			OrderGeneSynthesisDTO geneSynthesis = orderItem.getGeneSynthesis();
			List<Document> docList = geneSynthesis.getDocumentList();
			List<Integer> delDocIdList = geneSynthesis.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				geneSynthesis.setDelDocIdList(delDocIdList);
			}
			orderItem.setGeneSynthesis(geneSynthesis);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		} else if (serviceName.equalsIgnoreCase("rna")) {
			RnaDTO rna = orderItem.getRna();
			List<Document> docList = rna.getDocumentList();
			List<Integer> delDocIdList = rna.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				rna.setDelDocIdList(delDocIdList);
			}
			orderItem.setRna(rna);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			OrderCustCloningDTO custCloning = orderItem.getCustCloning();
			List<Document> docList = custCloning.getDocumentList();
			List<Integer> delDocIdList = custCloning.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				custCloning.setDelDocIdList(delDocIdList);
			}
			orderItem.setCustCloning(custCloning);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			OrderPlasmidPreparationDTO plasmidPreparation = orderItem
					.getPlasmidPreparation();
			List<Document> docList = plasmidPreparation.getDocumentList();
			List<Integer> delDocIdList = plasmidPreparation.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				plasmidPreparation.setDelDocIdList(delDocIdList);
			}
			orderItem.setPlasmidPreparation(plasmidPreparation);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			OrderMutagenesisDTO mutagenesis = orderItem.getMutagenesis();
			List<Document> docList = mutagenesis.getDocumentList();
			List<Integer> delDocIdList = mutagenesis.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				mutagenesis.setDelDocIdList(delDocIdList);
			}
			orderItem.setMutagenesis(mutagenesis);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		} else if (serviceName.equalsIgnoreCase("orfClone")) {
			OrderOrfCloneDTO orfClone = orderItem.getOrfClone();
			List<Document> docList = orfClone.getDocumentList();
			List<Integer> delDocIdList = orfClone.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				orfClone.setDelDocIdList(delDocIdList);
			}
			orderItem.setOrfClone(orfClone);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		} else if (serviceName.equalsIgnoreCase("mutaLib")) {
			OrderMutationLibrariesDTO mutaLib = orderItem.getMutationLibraries();
			List<Document> docList = mutaLib.getDocumentList();
			List<Integer> delDocIdList = mutaLib.getDelDocIdList();
			Document delDoc = null;
			for (Document document : docList) {
				if (document.getFilePath().equals(this.delFilePath)) {
					delDoc = document;
					docList.remove(delDoc);
					break;
				}
			}
			if (delDoc == null) {
				Struts2Util.renderText("Fail to get the document");
				return null;
			}
			if (delDoc.getDocId() != null) {
				if (delDocIdList == null) {
					delDocIdList = new ArrayList<Integer>();
				}
				delDocIdList.add(delDoc.getDocId());
				mutaLib.setDelDocIdList(delDocIdList);
			}
			orderItem.setMutationLibraries(mutaLib);
			orderItem.setUpdateFlag(Y);
			Struts2Util.renderText("SUCCESS");
		}
		return null;
	}

	/**
	 * 为peptide 页面设置下拉数据
	 */
	private void setDropdownForPeptide() {
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.PEPTIDE_QUANTITY);
		speclListName.add(SpecDropDownListName.PEPTIDE_PURITY_ARRAY);
		speclListName.add(SpecDropDownListName.PEPTIDE_PURITY);
		speclListName.add(SpecDropDownListName.N_TERMINAL);
		speclListName.add(SpecDropDownListName.C_TERMINAL);
		speclListName.add(SpecDropDownListName.MODIFICATION);
		specDropDownMap = publicService.getSpecDropDownMap(speclListName);
	}

	/**
	 * 在session中增加新的item
	 * 
	 * @param itemMap
	 * @param afterItemId
	 * @param newItemId
	 * @param newItem
	 */
	private void insertNewChild(Map<String, OrderItemDTO> itemMap,
			String afterItemId, String newItemId, OrderItemDTO newItem) {
		if (StringUtils.isEmpty(afterItemId)) {
			Integer itemNo = itemMap.size() + 1;
			newItem.setItemNo(itemNo);
			newItem.setUpdateFlag(Y);
			itemMap.put(newItemId, newItem);
		} else {
			Map<String, OrderItemDTO> newItemMap = new LinkedHashMap<String, OrderItemDTO>();
			Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
					.iterator();
			OrderItemDTO afterItem = itemMap.get(afterItemId);
			Integer itemNo = afterItem.getItemNo();
			while (it.hasNext()) {
				Entry<String, OrderItemDTO> entry = it.next();
				String tmpKey = entry.getKey();
				OrderItemDTO tmpItemDTO = entry.getValue();
				Integer tmpItemNo = tmpItemDTO.getItemNo();
				newItem.setUpdateFlag(Y);
				if (tmpItemNo.intValue() == itemNo.intValue()) {
					newItemMap.put(tmpKey, tmpItemDTO);
					newItem.setItemNo(tmpItemNo.intValue() + 1);
					newItem.setUpdateFlag(Y);
					newItemMap.put(newItemId, newItem);
				} else {
					if (tmpItemNo.intValue() > itemNo.intValue()) {
						tmpItemDTO.setItemNo(tmpItemNo.intValue() + 1);
					}
					tmpItemDTO.setUpdateFlag(Y);
					newItemMap.put(tmpKey, tmpItemDTO);
				}
			}
			SessionUtil.deleteRow(SessionConstant.OrderItemList.value(),
					sessOrderNo);
			SessionUtil.updateRow(SessionConstant.OrderItemList.value(),
					sessOrderNo, newItemMap);
		}
	}

	/**
	 * 获得新的子item
	 * 
	 * @param parentId
	 * @param serviceName
	 * @param catalogNo
	 * @return
	 */
	private OrderItemDTO getNewChildSerItem(String parentId,
			String serviceName, String catalogNo) {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		Integer custNo = order.getCustNo();
		OrderItemDTO parentItem = itemMap.get(parentId);
		String intmdKeyword = MoreDetailUtil.getIntmdKeyword(serviceName);
		String parentCatalogNo = null;
		try {
			parentCatalogNo = servService.getIntermediaService(
					parentItem.getCatalogNo(), intmdKeyword);
		} catch (Exception ex) {
			throw new BussinessException(BussinessException.SUB_ITEM_IS_NULL);
		}
		OrderItemDTO itemInfo = this.getBaseInfoByCatalogNo(parentCatalogNo,
				custNo);
		itemInfo.setParentId(parentId);
		OrderItemDTO preItem = getLastItem(parentId);
		itemInfo = orderItemService.getServiceItemOtherInfo(serviceName,
				itemInfo, parentItem, preItem);
		itemInfo.setShipToAddress(parentItem.getShipToAddress());
		itemInfo.setShiptoAddrId(parentItem.getShiptoAddrId());
		itemInfo.setShipMethod(parentItem.getShipMethod());
		itemInfo.setUpSymbol(parentItem.getUpSymbol());
		itemInfo.setStatus("CM");
		itemInfo.setStatusText("Committed");
		itemInfo.setOtherInfo("");
		itemInfo.setCatalogId(parentItem.getCatalogId());
		itemInfo.setCatalogText(itemInfo.getCatalogText());
		itemInfo.setUpdateFlag(Y);
		return itemInfo;
	}

	/**
	 * 获取某父类下面的最后一个子类
	 */
	private OrderItemDTO getLastItem(String parentId) {
		Map<String, OrderItemDTO> groupMap = getGroupItemMap(parentId);
		Iterator<Entry<String, OrderItemDTO>> it = groupMap.entrySet()
				.iterator();
		OrderItemDTO lastItem = null;
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			OrderItemDTO tmpItemDTO = entry.getValue();
			String tempParentId = tmpItemDTO.getParentId();
			if (tmpKey.equals(parentId)) {
				lastItem = tmpItemDTO;
				lastItem.setSessionItemId(tmpKey);
			} else if (parentId.equals(tempParentId)) {
				lastItem = tmpItemDTO;
				lastItem.setSessionItemId(tmpKey);
			}
		}
		return lastItem;
	}

	/**
	 * 为order pkg service 生成新的item
	 * 
	 * @param parentId
	 * @return
	 */
	private OrderItemDTO getNewChildPkgSerItem(String parentId, String catalogNo) {
		OrderMainDTO order = (OrderMainDTO) SessionUtil.getRow(
				SessionConstant.Order.value(), sessOrderNo);
		OrderItemDTO parentItem = itemMap.get(parentId);
		OrderItemDTO itemInfo = this.getBaseInfoByCatalogNo(catalogNo,
				order.getCustNo());
		itemInfo.setParentId(parentId);
		itemInfo.setShipToAddress(parentItem.getShipToAddress());
		itemInfo.setShiptoAddrId(parentItem.getShiptoAddrId());
		itemInfo.setShipMethod(parentItem.getShipMethod());
		itemInfo.setUpSymbol(parentItem.getUpSymbol());
		itemInfo.setStatus("CM");
		itemInfo.setStatusText("Committed");
		itemInfo.setOtherInfo("");
		itemInfo.setCatalogId(parentItem.getCatalogId());
		itemInfo.setCatalogText(parentItem.getCatalogText());
		itemInfo.setUpdateFlag(Y);
		return itemInfo;
	}

	/**
	 * 根据catalogNo生成基本的item信息
	 * 
	 * @param catalogNo
	 * @param custNo
	 * @return
	 */
	private OrderItemDTO getBaseInfoByCatalogNo(String catalogNo, Integer custNo) {
		System.out
				.println("getBaseInfoByCatalogNo() catalog no:>>" + catalogNo);
		System.out.println("getBaseInfoByCatalogNo() custNo no:>>" + custNo);
		SearchItemDTO searchItemDTO = servService.getSearchItemInfo(catalogNo,
				custNo);
		System.out.println("getBaseInfoByCatalogNo() searchItemDTO:>>"
				+ searchItemDTO);
		OrderItemDTO orderItemDTO = new OrderItemDTO();
		orderItemDTO.setName(searchItemDTO.getName());
		orderItemDTO.setCatalogNo(searchItemDTO.getCatalogNo());
		orderItemDTO.setClsId(searchItemDTO.getClsId());
		if (searchItemDTO.getCost() == null) {
			orderItemDTO.setCost(new BigDecimal(0));
		} else {
			orderItemDTO.setCost(new BigDecimal(searchItemDTO.getCost()));
		}
		orderItemDTO.setType("SERVICE");
		orderItemDTO.setTypeText("SERVICE" + "-" + searchItemDTO.getClsName());
		orderItemDTO.setShortDesc(searchItemDTO.getDescription());
		orderItemDTO.setLongDesc(searchItemDTO.getFullDesc());
		orderItemDTO.setTaxStatus(searchItemDTO.getTaxStatus());
		orderItemDTO.setShipSchedule(searchItemDTO.getScheduleShip());
		orderItemDTO.setSellingNote(searchItemDTO.getSellingNote());
		orderItemDTO.setStorageId(searchItemDTO.getPrefStorage());
		orderItemDTO.setSizeUom(searchItemDTO.getUom());
		orderItemDTO.setSize(searchItemDTO.getSize());
		orderItemDTO.setQtyUom(searchItemDTO.getQtyUom());
		// orderItemDTO.setCatalogId(searchItemDTO.getCatalogId());//改为从父item取值
		orderItemDTO.setQuantity(1);// hard code
		orderItemDTO.setAmount(new BigDecimal(0.0));// hard code
		orderItemDTO.setDiscount(new BigDecimal(0.0));// hard code
		orderItemDTO.setTax(new BigDecimal(0.0));// hard code
		orderItemDTO.setUnitPrice(new BigDecimal(0.0));// hard code
		orderItemDTO.setBasePrice(BigDecimal.valueOf(0.0));
		orderItemDTO.setCost(new BigDecimal(0.0));
		orderItemDTO.setUpdateFlag(Y);
		return orderItemDTO;
	}

	/**
	 * 计算service价格
	 * 
	 * @param itemDTO
	 * @return
	 */
	private ServiceItemPiceDTO getCostPrice(OrderItemDTO itemDTO) {
		ServiceItemPiceDTO orderServiceItemPiceDTO = new ServiceItemPiceDTO();
		if (StringUtils.isNotBlank(orderItem.getParentId())) {
			OrderItemDTO baseItemDTO = itemMap.get(orderItem.getParentId());
			orderServiceItemPiceDTO.setParentClsId(baseItemDTO.getClsId());
		}
		orderServiceItemPiceDTO.setServiceClsId(orderItem.getClsId());
		orderServiceItemPiceDTO.setCatalogId(orderItem.getCatalogId());
		orderServiceItemPiceDTO.setCatalogNo(orderItem.getCatalogNo());
		orderServiceItemPiceDTO.setQuantity(orderItem.getQuantity());
		// orderServiceItemPiceDTO.setServicePriceType(servicePriceType);
		//
		// publicService.calculateServicePrice(orderServiceItemPiceDTO);
		//
		// orderServiceItemPiceDTO.setCost(88.88);// fake data 尚未确定 此处为伪代码
		// orderServiceItemPiceDTO.setPrice(88.88);// fake data 尚未确定
		return orderServiceItemPiceDTO;
	}

	/**
	 * 判断子item是否存在
	 * 
	 * @param itemMap
	 * @param itemId
	 * @return
	 */
	private boolean isChildItemExist(Map<String, OrderItemDTO> itemMap,
			String itemId) {
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			if (orderItemDTO == null) {
				it.remove();
				itemMap.remove(entry.getKey());
				continue;
			}
			String parentId = orderItemDTO.getParentId();
			if (!StringUtils.isEmpty(parentId)
					&& !parentId.equalsIgnoreCase("0")
					&& parentId.equalsIgnoreCase(itemId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得父子相关联的所有item
	 * 
	 * @param itemId
	 * @return
	 */
	private Map<String, OrderItemDTO> getGroupItemMap(String itemId) {
		Map<String, OrderItemDTO> newItemMap = new LinkedHashMap<String, OrderItemDTO>();
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		OrderItemDTO baseItemDTO = itemMap.get(itemId);
		String parentId = baseItemDTO.getParentId();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = orderItemDTO.getParentId();
			if (tmpId.equals(itemId)) {
				newItemMap.put(tmpId, orderItemDTO);
			}
			if (!StringUtils.isEmpty(parentId)
					&& !parentId.equalsIgnoreCase("0")
					&& (parentId.equals(tmpParentId) || parentId.equals(tmpId))) {
				newItemMap.put(tmpId, orderItemDTO);
			} else if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, orderItemDTO);
			}
		}
		return newItemMap;
	}

	/**
	 * 获得父子相关联的所有item
	 * 
	 * @param itemId
	 * @return
	 */
	private Map<String, OrderItemDTO> getChildItemMap(String itemId) {
		Map<String, OrderItemDTO> newItemMap = new HashMap<String, OrderItemDTO>();
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			OrderItemDTO orderItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = orderItemDTO.getParentId();
			if (tmpId.equals(itemId)) {
				newItemMap.put(tmpId, orderItemDTO);
			}
			if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, orderItemDTO);
			}
		}
		return newItemMap;
	}

	/**
	 * 根据item获得显示所需的service信息
	 * 
	 * @param itemMap
	 */
	private void setServicesByItemMap(Map<String, OrderItemDTO> itemMap) {
		orderServiceDTO = new OrderServiceDTO();
		if (orderItem.getGeneSynthesis() != null) {
			orderServiceDTO.setGeneSynthesis(orderItem.getGeneSynthesis());
			orderServiceDTO.setGeneSynthesisItemId(itemId);
		}
		if (orderItem.getRna() != null) {
			orderServiceDTO.setRna(orderItem.getRna());
			orderServiceDTO.setRnaItemId(itemId);
		}
		if (orderItem.getCustCloning() != null) {
			orderServiceDTO.setCustCloning(orderItem.getCustCloning());
			orderServiceDTO.setCustCloningItemId(itemId);
			orderServiceDTO.getCustCloning().setTbdFlag(orderItem.getTbdFlag());
		}
		if (orderItem.getPlasmidPreparation() != null) {
			orderServiceDTO.setPlasmidPreparation(orderItem
					.getPlasmidPreparation());
			orderServiceDTO.setPlasmidPreparationItemId(itemId);

			plasmidPreparation = orderServiceDTO.getPlasmidPreparation();
			if (plasmidPreparation == null) {
				plasmidPreparation = new OrderPlasmidPreparationDTO();
			}
			if (plasmidPreparation.getPrepWeight() != null) {
				Double preWeight = plasmidPreparation.getPrepWeight()
						.doubleValue();
				if (preWeight.doubleValue() == preWeight.intValue()) {
					plasmidPreparation.setPrepWeightStr(String
							.valueOf(preWeight.intValue() + " "
									+ plasmidPreparation.getPrepWtUom()));
				} else {
					plasmidPreparation.setPrepWeightStr(String
							.valueOf(preWeight.doubleValue() + " "
									+ plasmidPreparation.getPrepWtUom()));
				}
			}
			antibioticResistance = plasmidPreparation.getAntibioticResistance();
			String restrictionAnalysis = plasmidPreparation
					.getRestrictionAnalysis();
			if (restrictionAnalysis != null) {
				String[] restrictionAnalysisArray = restrictionAnalysis
						.split(",");
				List<PbDropdownListOptions> list = dropDownMap
						.get("PLASMID_RESTRICTION_ANALYSIS");
				List<PbDropdownListOptions> delList = new ArrayList<PbDropdownListOptions>();
				for (PbDropdownListOptions option : list) {
					for (String value : restrictionAnalysisArray) {
						if (option.getValue().equals(value)) {
							delList.add(option);
							break;
						}
					}
				}
				list.removeAll(delList);
				dropDownMap.put("PLASMID_RESTRICTION_ANALYSIS", list);
			}
			String[] fragmentArray = plasmidPreparation.getFragment() != null ? plasmidPreparation
					.getFragment().split("::") : null;
			plasmidPreparation.setFragmentArray(fragmentArray);
			if (fragmentArray != null) {
				fragmentSize = "e.g." + fragmentArray.length;
			}

		}
		if (orderItem.getMutagenesis() != null) {
			orderServiceDTO.setMutagenesis(orderItem.getMutagenesis());
			orderServiceDTO.setMutagenesisItemId(itemId);
			orderServiceDTO.setParentId(orderItem.getParentId());
		}
		if (orderItem.getMutationLibraries() != null) {
			orderServiceDTO.setMutationLibraries(orderItem.getMutationLibraries());
			orderServiceDTO.setMutationLibrariesItemId(itemId);
		}
		if (orderItem.getOrfClone() != null) {
			orderServiceDTO.setOrfClone(orderItem.getOrfClone());
			orderServiceDTO.setOrfCloneItemId(itemId);
		}
		if (orderItem.getOligo() != null) {
			orderServiceDTO.setOligo(orderItem.getOligo());
			orderServiceDTO.setOligoItemId(itemId);
		}
		if (orderItem.getCustomService() != null) {
			orderServiceDTO.setCustomService(orderItem.getCustomService());
			orderServiceDTO.setCustomServiceItemId(itemId);
		}
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet()
				.iterator();
		Map<String, OrderMutagenesisDTO> mutagenesisMap = new HashMap<String, OrderMutagenesisDTO>();
		Map<String, PkgServiceDTO> orderPkgServiceMap = new HashMap<String, PkgServiceDTO>();
		Map<String, PeptideDTO> peptideMap = new LinkedHashMap<String, PeptideDTO>();
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpId = entry.getKey();
			OrderItemDTO itemDTO = entry.getValue();
			if (itemDTO.getGeneSynthesis() != null
					&& orderServiceDTO.getGeneSynthesis() == null) {
				orderServiceDTO.setGeneSynthesis(itemDTO.getGeneSynthesis());
				orderServiceDTO.setGeneSynthesisItemId(tmpId);
			}
			if (itemDTO.getCustCloning() != null
					&& orderServiceDTO.getCustCloning() == null) {
				orderServiceDTO.setCustCloning(itemDTO.getCustCloning());
				orderServiceDTO.setCustCloningItemId(tmpId);
				orderServiceDTO.getCustCloning().setTbdFlag(itemDTO.getTbdFlag());
			}
			if (itemDTO.getPlasmidPreparation() != null
					&& orderServiceDTO.getPlasmidPreparation() == null) {
				orderServiceDTO.setPlasmidPreparation(itemDTO
						.getPlasmidPreparation());
				orderServiceDTO.setPlasmidPreparationItemId(tmpId);
				plasmidPreparation = orderServiceDTO.getPlasmidPreparation();
				if (plasmidPreparation == null) {
					plasmidPreparation = new OrderPlasmidPreparationDTO();
				}
				if (plasmidPreparation.getPrepWeight() != null) {
					Double preWeight = plasmidPreparation.getPrepWeight()
							.doubleValue();
					if (preWeight.doubleValue() == preWeight.intValue()) {
						plasmidPreparation.setPrepWeightStr(String
								.valueOf(preWeight.intValue() + " "
										+ plasmidPreparation.getPrepWtUom()));
					} else {
						plasmidPreparation.setPrepWeightStr(String
								.valueOf(preWeight.doubleValue() + " "
										+ plasmidPreparation.getPrepWtUom()));
					}
				}
				antibioticResistance = plasmidPreparation
						.getAntibioticResistance();
				String restrictionAnalysis = plasmidPreparation
						.getRestrictionAnalysis();
				if (restrictionAnalysis != null) {
					String[] restrictionAnalysisArray = restrictionAnalysis
							.split(",");
					List<PbDropdownListOptions> list = dropDownMap
							.get("PLASMID_RESTRICTION_ANALYSIS");
					List<PbDropdownListOptions> delList = new ArrayList<PbDropdownListOptions>();
					for (PbDropdownListOptions option : list) {
						for (String value : restrictionAnalysisArray) {
							if (option.getValue().equals(value)) {
								delList.add(option);
								break;
							}
						}
					}
					list.removeAll(delList);
					dropDownMap.put("PLASMID_RESTRICTION_ANALYSIS", list);
				}
				String[] fragmentArray = plasmidPreparation.getFragment() != null ? plasmidPreparation
						.getFragment().split("::") : null;
				plasmidPreparation.setFragmentArray(fragmentArray);
				if (fragmentArray != null) {
					fragmentSize = "e.g." + fragmentArray.length;
				}

			}
			if (itemDTO.getOrfClone() != null
					&& orderServiceDTO.getOrfClone() == null) {
				orderServiceDTO.setOrfClone(itemDTO.getOrfClone());
				orderServiceDTO.setOrfCloneItemId(tmpId);
			}
			if (itemDTO.getMutagenesis() != null) {
				if (orderServiceDTO.getMutagenesis() == null) {
					orderServiceDTO.setMutagenesis(itemDTO.getMutagenesis());
					orderServiceDTO.setMutagenesisItemId(tmpId);
					orderServiceDTO.setParentId(itemDTO.getParentId());
				}
				if (StringUtils.isEmpty(itemDTO.getMutagenesis()
						.getTmplInsertName())) {
					OrderMutagenesisDTO tmpMu = new OrderMutagenesisDTO();
					tmpMu.setTmplInsertName("Mutagenesis");
					mutagenesisMap.put(tmpId, tmpMu);
				} else {
					mutagenesisMap.put(tmpId, itemDTO.getMutagenesis());
				}
			}
			if (itemDTO.getPeptide() != null) {
				orderServiceDTO.setPeptide(itemDTO.getPeptide());
				orderServiceDTO.setPeptideItemId(tmpId);
				PeptideDTO pep = itemDTO.getPeptide();
				pep.setStatus(itemDTO.getStatus());
				peptideMap.put(tmpId, pep);
			}
			if (itemDTO.getOrderPkgService() != null) {
				String pkgServiceName = itemDTO.getOrderPkgService().getName();
				if (StringUtils.isNotBlank(pkgServiceName)) {
					String reStr = pkgServiceName.replaceAll("'", "@@")
							.replaceAll("\"", "~~").replaceAll("\n", "##");
					itemDTO.getOrderPkgService().setName(reStr);
				}
				String pkgDescription = itemDTO.getOrderPkgService()
						.getDescription();
				if (StringUtils.isNotBlank(pkgDescription)) {
					String reStr = pkgDescription.replaceAll("'", "@@")
							.replaceAll("\"", "~~").replaceAll("\n", "##");
					itemDTO.getOrderPkgService().setDescription(reStr);
				}
				orderPkgServiceMap.put(tmpId, itemDTO.getOrderPkgService());
				if (StringUtils.isBlank(itemDTO.getParentId())
						&& tmpId.equals(itemId)) {
					orderServiceDTO.setPkgServiceId(itemId);
				}
			}
			if (itemDTO.getAntibody() != null) {
				orderServiceDTO.setAntibody(itemDTO.getAntibody());
				orderServiceDTO.setAntibodyItemId(tmpId);
			}
			if (itemDTO.getRna() != null) {
				orderServiceDTO.setRna(itemDTO.getRna());
				orderServiceDTO.setRnaItemId(tmpId);
			}
			if (itemDTO.getOligo() != null) {
				orderServiceDTO.setOligo(itemDTO.getOligo());
				orderServiceDTO.setOligoItemId(tmpId);
			}
			if (itemDTO.getMutationLibraries() != null) {
				orderServiceDTO.setMutationLibraries(itemDTO.getMutationLibraries());
				orderServiceDTO.setMutationLibrariesItemId(tmpId);
			}
		}
		orderServiceDTO.setMutagenesisMap(mutagenesisMap);
		orderServiceDTO.setPkgServiceMap(orderPkgServiceMap);
		orderServiceDTO.setPeptideMap(peptideMap);
		this.peptideMap = peptideMap;
	}

	public String qualityGradeHelp() {
		return "quality_grade_help";
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSessOrderNo() {
		return sessOrderNo;
	}

	public void setSessOrderNo(String sessOrderNo) {
		this.sessOrderNo = sessOrderNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public OrderItemDTO getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItemDTO orderItem) {
		this.orderItem = orderItem;
	}

	public Map<String, OrderItemDTO> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, OrderItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getDisableTabStr() {
		return disableTabStr;
	}

	public void setDisableTabStr(String disableTabStr) {
		this.disableTabStr = disableTabStr;
	}

	public String getServiceListStr() {
        serviceListStr = serviceListStr.replaceAll("'", "\\\\'");
		return serviceListStr;
	}

	public void setServiceListStr(String serviceListStr) {
		this.serviceListStr = serviceListStr;
	}

	public OrderGeneSynthesisDTO getGeneSynthesis() {
		return geneSynthesis;
	}

	public void setGeneSynthesis(OrderGeneSynthesisDTO geneSynthesis) {
		this.geneSynthesis = geneSynthesis;
	}

	public OrderCustCloningDTO getCustCloning() {
		return custCloning;
	}

	public void setCustCloning(OrderCustCloningDTO custCloning) {
		this.custCloning = custCloning;
	}

	public OrderPlasmidPreparationDTO getPlasmidPreparation() {
		return plasmidPreparation;
	}

	public void setPlasmidPreparation(
			OrderPlasmidPreparationDTO plasmidPreparation) {
		this.plasmidPreparation = plasmidPreparation;
	}

	public OrderOrfCloneDTO getOrfClone() {
		return orfClone;
	}

	public void setOrfClone(OrderOrfCloneDTO orfClone) {
		this.orfClone = orfClone;
	}

	public OrderMutagenesisDTO getMutagenesis() {
		return mutagenesis;
	}

	public void setMutagenesis(OrderMutagenesisDTO mutagenesis) {
		this.mutagenesis = mutagenesis;
	}

	public PeptideDTO getPeptide() {
		return peptide;
	}

	public void setPeptide(PeptideDTO peptide) {
		this.peptide = peptide;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(
			Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownMap() {
		return specDropDownMap;
	}

	public void setSpecDropDownMap(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownMap) {
		this.specDropDownMap = specDropDownMap;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getRemoveFlag() {
		return removeFlag;
	}

	public void setRemoveFlag(Integer removeFlag) {
		this.removeFlag = removeFlag;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setDelFilePath(String delFilePath) {
		this.delFilePath = delFilePath;
	}

	public List<ServiceIntermediateDTO> getServiceSteps() {
		return serviceSteps;
	}

	public void setServiceSteps(List<ServiceIntermediateDTO> serviceSteps) {
		this.serviceSteps = serviceSteps;
	}

	public List<ServiceSubStepsDTO> getSubSteps() {
		return subSteps;
	}

	public void setSubSteps(List<ServiceSubStepsDTO> subSteps) {
		this.subSteps = subSteps;
	}

	public PkgServiceDTO getOrderPkgService() {
		return orderPkgService;
	}

	public void setOrderPkgService(PkgServiceDTO orderPkgService) {
		this.orderPkgService = orderPkgService;
	}

	public List<String> getModificationList() {
		return modificationList;
	}

	public void setModificationList(List<String> modificationList) {
		this.modificationList = modificationList;
	}

	public List<PeptideCode> getPeptideCodeList() {
		return peptideCodeList;
	}

	public void setPeptideCodeList(List<PeptideCode> peptideCodeList) {
		this.peptideCodeList = peptideCodeList;
	}

	public AntibodyDTO getAntibody() {
		return antibody;
	}

	public void setAntibody(AntibodyDTO antibody) {
		this.antibody = antibody;
	}

	public Map<String, PeptideDTO> getPeptideMap() {
		return peptideMap;
	}

	public void setPeptideMap(Map<String, PeptideDTO> peptideMap) {
		this.peptideMap = peptideMap;
	}

	public String getAntibodyItemId() {
		return antibodyItemId;
	}

	public void setAntibodyItemId(String antibodyItemId) {
		this.antibodyItemId = antibodyItemId;
	}

	public String getAntigenType() {
		return antigenType;
	}

	public void setAntigenType(String antigenType) {
		this.antigenType = antigenType;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public RnaDTO getRna() {
		return rna;
	}

	public void setRna(RnaDTO rna) {
		this.rna = rna;
	}

	public String getAntibioticResistance() {
		return antibioticResistance;
	}

	public void setAntibioticResistance(String antibioticResistance) {
		this.antibioticResistance = antibioticResistance;
	}

	public String getFragmentSize() {
		return fragmentSize;
	}

	public void setFragmentSize(String fragmentSize) {
		this.fragmentSize = fragmentSize;
	}

	public String getPrepWeight() {
		return prepWeight;
	}

	public void setPrepWeight(String prepWeight) {
		this.prepWeight = prepWeight;
	}

	public Map<String, List<OligoModifications>> getOmMap() {
		return omMap;
	}

	public void setOmMap(Map<String, List<OligoModifications>> omMap) {
		this.omMap = omMap;
	}

	public List<OligoBackbonesDTO> getOligoBackbonesList() {
		return oligoBackbonesList;
	}

	public void setOligoBackbonesList(List<OligoBackbonesDTO> oligoBackbonesList) {
		this.oligoBackbonesList = oligoBackbonesList;
	}

	public List<OligoChimericBases> getOligoChimericBasesList() {
		return oligoChimericBasesList;
	}

	public void setOligoChimericBasesList(
			List<OligoChimericBases> oligoChimericBasesList) {
		this.oligoChimericBasesList = oligoChimericBasesList;
	}

	public List<OligoMixedBases> getOligoMixedBasesList() {
		return oligoMixedBasesList;
	}

	public void setOligoMixedBasesList(List<OligoMixedBases> oligoMixedBasesList) {
		this.oligoMixedBasesList = oligoMixedBasesList;
	}

	public OrderOligoDTO getOligo() {
		return oligo;
	}

	public void setOligo(OrderOligoDTO oligo) {
		this.oligo = oligo;
	}

	public String getQuanty() {
		return quanty;
	}

	public void setQuanty(String quanty) {
		this.quanty = quanty;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public CustomServiceDTO getCustomService() {
		return customService;
	}

	public void setCustomService(CustomServiceDTO customService) {
		this.customService = customService;
	}

	public String getBatchCustNo() {
		return batchCustNo;
	}

	public void setBatchCustNo(String batchCustNo) {
		this.batchCustNo = batchCustNo;
	}
	
	public List<OrderDsPlatesDTO> getDsPlatesList() {
		return dsPlatesList;
	}

	public void setDsPlatesList(List<OrderDsPlatesDTO> dsPlatesList) {
		this.dsPlatesList = dsPlatesList;
	}

	/**
	 * @return the tubeMap
	 */
	public Map<String, OrderItemDTO> getTubeMap() {
		return tubeMap;
	}

	/**
	 * @param tubeMap the tubeMap to set
	 */
	public void setTubeMap(Map<String, OrderItemDTO> tubeMap) {
		this.tubeMap = tubeMap;
	}

	/**
	 * @return the plateMap
	 */
	public Map<String, OrderItemDTO> getPlateMap() {
		return plateMap;
	}

	/**
	 * @param plateMap the plateMap to set
	 */
	public void setPlateMap(Map<String, OrderItemDTO> plateMap) {
		this.plateMap = plateMap;
	}

	public String getTubeComment() {
		return tubeComment;
	}

	public void setTubeComment(String tubeComment) {
		this.tubeComment = tubeComment;
	}

	public OrderMutationLibrariesDTO getMutaLib() {
		return mutaLib;
	}

	public void setMutaLib(OrderMutationLibrariesDTO mutaLib) {
		this.mutaLib = mutaLib;
	}

	public String[] getPackageDescriptArray() {
		return packageDescriptArray;
	}

	public void setPackageDescriptArray(String[] packageDescriptArray) {
		this.packageDescriptArray = packageDescriptArray;
	}

	public List<VectorInfoListDTO> getVectorList() {
		return vectorList;
	}

	public void setVectorList(List<VectorInfoListDTO> vectorList) {
		this.vectorList = vectorList;
	}

	public List<String> getOrfCloneSiteList() {
		return orfCloneSiteList;
	}

	public void setOrfCloneSiteList(List<String> orfCloneSiteList) {
		this.orfCloneSiteList = orfCloneSiteList;
	}

}
