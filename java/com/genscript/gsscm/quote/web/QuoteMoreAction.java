package com.genscript.gsscm.quote.web;

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
import java.util.concurrent.ConcurrentHashMap;
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
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
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
import com.genscript.gsscm.quote.dto.QuoteDsPlatesDTO;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.service.QuoteItemService;
import com.genscript.gsscm.quote.service.QuoteService;
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
 * @author Zhangyang
 *
 */
@Results( {
        @Result(name = "more", location = "quote/quote_more.jsp"),
        @Result(name = "quote_more_batch", location = "quote/quote_more_batch.jsp"),
        @Result(name = "quote_cloning_batch", location = "quote/quote_more_cloning_batch.jsp"),
        @Result(name = "select_batch_quotation", location = "quote/selectBatchQuotation.jsp"),
	@Result(name="quality_grade_help",location="order/quality_grade_help.jsp")
	})
public class QuoteMoreAction extends BaseAction<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6358765823814588796L;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ServService servService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private BiolibService biolibService;
	@Autowired
	private ExcelUtil excelUtil;
	@Autowired
	private QuoteItemService quoteItemService;
	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private QuoteOrderUtil quoteOrderUtil;
    @Autowired(required = false)
	private DozerBeanMapper dozer;
	
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownMap;
	
	private String sessQuoteNo;
	private String itemId;
	private QuoteItemDTO quoteItem;
	private Map<String, QuoteItemDTO> itemMap;
	private String parentId;
	private Integer removeFlag;
	
	private String show;
	private String tab;
	private String disableTabStr;
	
	private OrderServiceDTO quoteServiceDTO;
	private String serviceListStr;
	private OrderGeneSynthesisDTO geneSynthesis;
	private RnaDTO rna;
	private OrderCustCloningDTO custCloning;
	private OrderPlasmidPreparationDTO plasmidPreparation;
	private OrderOrfCloneDTO orfClone;
	private OrderOligoDTO oligo;
	private OrderMutagenesisDTO mutagenesis;
	private PeptideDTO peptide;
	private OrderMutationLibrariesDTO mutaLib;
	private List<ServiceIntermediateDTO> serviceSteps;
	private List<ServiceSubStepsDTO> subSteps;
	//上传附件相关
	@Autowired
	private FileService fileService;
	private PkgServiceDTO quotePkgService;
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;
	private String refType;
	private String serviceName;
	private String delFilePath;
	private List<String> modificationList;
	private List<PeptideCode> peptideCodeList;
	private AntibodyDTO antibody;
	private Map<String, PeptideDTO> peptideMap;
	private String antibodyItemId;
	private String antigenType;
	private Integer stepId;
	
	//plasmid
	private String antibioticResistance;
	private String fragmentSize;
	private String prepWeight;

	private List<OligoBackbonesDTO> oligoBackbonesList;
	private Map<String, List<OligoModifications>> omMap;
	private List<OligoChimericBases> oligoChimericBasesList;
	private List<OligoMixedBases> oligoMixedBasesList;
	//DNA Sequencing
	private List<QuoteDsPlatesDTO> dsPlatesList;
	private Map<String, QuoteItemDTO> tubeMap;
	private Map<String, QuoteItemDTO> plateMap;
	private String tubeComment;
	private String[] packageDescriptArray;
	//ORF Clone
	private List<VectorInfoListDTO> vectorList;
	private List<String> orfCloneSiteList;
	
	private CustomServiceDTO customService;
    private Integer itemNum;
    private String batchCustNo;
    //批量标志
    private boolean batchFlag = false;
    //批量错误信息
    private Map<String, Object> batchMessage= new HashMap<String, Object>();
	private final static String Y = "Y";
	private final static String N = "N";
	private final static String pUC57 = "pUC57";
	private final static String tbd_0 = "0";
	private final static String tbd_1 = "1";
	private final static String Other = "Other";
	
	/**
	 * 显示quote more detail
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showMoreDetail() throws Exception{
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		// PRODUCT类型不用继续
		if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(quoteItem.getType())) {
			Struts2Util.renderHtml("");
			return null;
		}
        //add by zhanghuibin
        //取得所有的相关服务
        if (quoteItem.getQuoteItemId() != null) {
            quoteService.setItemAllMoreDetail(itemMap, quoteItem);
        }
		Integer clsId = quoteItem.getClsId();
		QuoteItemDTO parentQuoteItemDTO = null;
		Integer parentClsId = null;
		String parentId = null;
		Integer topClsId = clsId;
		if(StringUtils.isNotBlank(quoteItem.getParentId())){
			parentQuoteItemDTO = itemMap.get(quoteItem.getParentId());
			parentClsId = parentQuoteItemDTO.getClsId();
			parentId = quoteItem.getParentId();
			topClsId = parentClsId;
			if(!StringUtils.isEmpty(parentQuoteItemDTO.getParentId())){
				QuoteItemDTO parentParentQuoteItemDTO = itemMap.get(parentQuoteItemDTO.getParentId());
				topClsId = parentParentQuoteItemDTO.getClsId();
			}else{
				topClsId = parentClsId;
			}
		}else{
			packageDescriptArray = quoteOrderUtil.getPackageDescriptions(quoteItem.getCatalogNo()); 
		}
		initDropDownList();
		// Protein&Bioprocess&Pharmeceutical&Antibody drug
		if (MoreDetailUtil.isProteinGroupService(clsId)) {
			Map<String, QuoteItemDTO> groupItemMap = this
					.getGroupItemMap(itemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
			// 设置
			if (MoreDetailUtil.isProteinGroupParent(clsId)) {
				serviceSteps = this.servService
						.getServiceIntermediateAllList(quoteItem.getCatalogNo(), quoteItem.getCatalogId());
			} else {
				subSteps = this.servService.getServiceSubStepsList(quoteItem
						.getCatalogNo(), quoteItem.getCatalogId(), null);
			}
			// 设置jsp页面显示类型
			show = MoreDetailUtil.getShow(clsId);
		} else if ((clsId == 1 && parentQuoteItemDTO == null) || (clsId == 1 && parentQuoteItemDTO.getClsId() == 1)) {
			//有父item,则进入其他处理流程（antibody）。
			this.setDropdownForPeptide();
			quoteServiceDTO = new OrderServiceDTO();
			if (quoteItem.getPeptide() != null) {
				quoteServiceDTO.setPeptide(quoteItem.getPeptide());
				quoteServiceDTO.setPeptideItemId(itemId);
			}
			peptide = quoteItem.getPeptide();
			if (modificationList == null) {
				modificationList = new ArrayList<String>();
			}
			if (peptide != null) {
				String modifications = quoteItem.getPeptide().getModification();

				if (StringUtils.isNotBlank(modifications)) {
					String[] modificationStrs = modifications.split(",");
					for (String str : modificationStrs) {
						modificationList.add(str);
					}
				}
			}
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		}else if((clsId == 31 && parentQuoteItemDTO == null) || (clsId == 31 && parentQuoteItemDTO.getClsId() == 31)){
			//有父item,则进入其他处理流程（antibody）。
			this.setDropdownForPeptide();
			if("SC1177".equalsIgnoreCase(quoteItem.getCatalogNo())){
				dropDownMap.put("PEPTIDE_PURITY",
						publicService.getDropdownList("SC1177_PURITY"));
				dropDownMap.put("PEPTIDE_QUANTITY",
						publicService.getDropdownList("SC1177_QUANTITY"));
				dropDownMap.put("PEPTIDE_QC_REPORT",
						publicService.getDropdownList("SC1177_QC_REPORT"));
			}else if("SC1487".equalsIgnoreCase(quoteItem.getCatalogNo())){
				dropDownMap.put("PEPTIDE_PURITY",
						publicService.getDropdownList("SC1487_PURITY"));
				dropDownMap.put("PEPTIDE_QUANTITY",
						publicService.getDropdownList("SC1487_QUANTITY"));
				dropDownMap.put("PEPTIDE_QC_REPORT",
						publicService.getDropdownList("SC1487_QC_REPORT"));
			}
			quoteServiceDTO = new OrderServiceDTO();
			if (quoteItem.getPeptide() != null) {
				quoteServiceDTO.setPeptide(quoteItem.getPeptide());
				quoteServiceDTO.setPeptideItemId(itemId);
				
				if(StringUtils.isBlank(quoteItem.getParentId()) ){
				Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
				StringBuilder sb = new StringBuilder();
				while (it.hasNext()) {
					Entry<String, QuoteItemDTO> entry = it.next();
					QuoteItemDTO quoteItemDTO = entry.getValue();
					if(quoteItemDTO != null && quoteItemDTO.getPeptide() != null && quoteItemDTO.getParentId() != null &&  quoteItemDTO.getParentId().equalsIgnoreCase(itemId)){
						sb.append(quoteItemDTO.getPeptide().getSequence()).append(",");
					}
				}
				
				Iterator<Entry<String, QuoteItemDTO>> itTwo = itemMap.entrySet()
				.iterator();
				while (itTwo.hasNext()) {
					Entry<String, QuoteItemDTO> entry = itTwo.next();
					QuoteItemDTO quoteItemDTO = entry.getValue();
					if(quoteItemDTO != null && quoteItemDTO.getPeptide() != null && quoteItemDTO.getParentId() != null &&  quoteItemDTO.getParentId().equalsIgnoreCase(itemId)){
						PeptideDTO peptideDTO = quoteServiceDTO.getPeptide();
						peptideDTO = (PeptideDTO)ModelUtils
						.mergerModel(quoteItemDTO.getPeptide(), quoteServiceDTO.getPeptide());
						quoteServiceDTO.setPeptide(peptideDTO);
						quoteServiceDTO.setPeptideItemId(itemId);
						break;
					}
				}
				String mutipleSeq;
				if(StringUtils.isNotEmpty(sb.toString())){
					mutipleSeq = sb.substring(0, sb.length()-1);
				}else{
					mutipleSeq = quoteItem.getPeptide().getSequence();
				}
					quoteServiceDTO.getPeptide().setSequence(mutipleSeq);
				}
			}

			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		}else if((clsId == 30 && parentQuoteItemDTO == null) || (clsId == 30 && parentQuoteItemDTO.getClsId() == 30)){
			//有父item,则进入其他处理流程（antibody）。
			this.setDropdownForPeptide();
			quoteServiceDTO = new OrderServiceDTO();
			if (quoteItem.getPeptide() != null) {
				quoteServiceDTO.setPeptide(quoteItem.getPeptide());
				quoteServiceDTO.setPeptideItemId(itemId);
				if(StringUtils.isBlank(quoteItem.getParentId()) ){
				Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
				StringBuilder sb = new StringBuilder();
				while (it.hasNext()) {
					Entry<String, QuoteItemDTO> entry = it.next();
					QuoteItemDTO quoteItemDTO = entry.getValue();
					if(quoteItemDTO != null && quoteItemDTO.getPeptide() != null && quoteItemDTO.getParentId() != null && quoteItemDTO.getParentId().equalsIgnoreCase(itemId)){
						sb.append(quoteItemDTO.getPeptide().getSequence()).append(",");
					}
				}
				Iterator<Entry<String, QuoteItemDTO>> itTwo = itemMap.entrySet()
				.iterator();
				while (itTwo.hasNext()) {
					Entry<String, QuoteItemDTO> entry = itTwo.next();
					QuoteItemDTO quoteItemDTO = entry.getValue();
					if(quoteItemDTO != null && quoteItemDTO.getPeptide() != null && quoteItemDTO.getParentId() != null &&  quoteItemDTO.getParentId().equalsIgnoreCase(itemId)){
						PeptideDTO peptideDTO = quoteServiceDTO.getPeptide();
						peptideDTO = (PeptideDTO)ModelUtils
						.mergerModel(quoteItemDTO.getPeptide(), quoteServiceDTO.getPeptide());
						quoteServiceDTO.setPeptide(peptideDTO);
						quoteServiceDTO.setPeptideItemId(itemId);
						break;
					}
				}
				String mutipleSeq;
				if(StringUtils.isNotEmpty(sb.toString())){
					mutipleSeq = sb.substring(0, sb.length()-1);
				}else{
					mutipleSeq = quoteItem.getPeptide().getSequence();
				}
				quoteServiceDTO.getPeptide().setSequence(mutipleSeq);
				}
			}
			disableTabStr = "librayPeptide";
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
		}else if(clsId.intValue() == 11 || (parentClsId != null && parentClsId == 11 ) || clsId.intValue() == 12 || (parentClsId != null && parentClsId == 12 ) || clsId.intValue() == 28 || (parentClsId != null && parentClsId == 28 )){
			//antibody
			this.setDropdownForPeptide();
			if(parentClsId != null){
				antibodyItemId = parentId;
				show = MoreDetailUtil.getShow(parentClsId);
			}else{
				antibodyItemId = itemId;
				show = MoreDetailUtil.getShow(clsId);
			}
			Map<String, QuoteItemDTO> groupItemMap = this.getGroupItemMap(antibodyItemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
			this.antibody = quoteServiceDTO.getAntibody();
			if(this.antibody.getAntigenType() == null){
				this.antibody.setAntigenType(MoreDetailUtil.getDefaultAntigenType());
			}
			QuoteItemDTO specialItem = null;
			if (StringUtils.isEmpty(parentId)) {
				specialItem = quoteItem;
			} else {
				specialItem = itemMap.get(parentId);
			}
			List<ServiceIntermediate> imdList = servService.getIntermediate(specialItem.getCatalogNo(), Y);
			if (imdList != null) {
				Map<String, String> specialCatalogNoMap = new HashMap<String, String>();
				Iterator<ServiceIntermediate> it = imdList.iterator();
				while (it.hasNext()) {
					ServiceIntermediate intermediate = it.next();
					specialCatalogNoMap.put(intermediate.getIntmdCatalogNo(), intermediate.getIntmdCatalogNo());
				}
				antibody.setSpecialCatalogNoMap(specialCatalogNoMap);
			}
//Oligo				
		} else if (clsId.intValue() == 34) {
			// 下拉列表
			dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
			//Synthesis Scales select
			dropDownMap.put("OLIGO_SYNTHESIS_SCALES",
					publicService.getDropdownList("OLIGO_SYNTHESIS_SCALES"));
			
			//RNA  Purification select
			dropDownMap.put("OLIGO_RNA_PURIFICATION",
					publicService.getDropdownList("OLIGO_RNA_PURIFICATION"));
			//Other  Purification select
			dropDownMap.put("OLIGO_OTHER_PURIFICATION",
					publicService.getDropdownList("OLIGO_OTHER_PURIFICATION"));
			//Backbone select
			oligoBackbonesList = orderService.findOligoBackbonesList();
			//5' Modification and Internal Modification and 3' Modification select
			omMap = orderService.findOligoModificationsList();
			//Chimeric Bases select
			oligoChimericBasesList = orderService.findOligoChimericBases(oligoBackbonesList);
			//Standard Mixed Bases select
			oligoMixedBasesList = orderService.findOligoMixedBases();
			Map<String, QuoteItemDTO> groupItemMap = this.getGroupItemMap(itemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
			show = MoreDetailUtil.getShow(clsId);
//DNA Sequencing				
		} else if (clsId.intValue() == 40) {
			// 下拉列表
			dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
			//Sample Type select
			dropDownMap.put("DS_SAMPLE_TYPE", publicService.getDropdownList("DS_SAMPLE_TYPE"));
			//Primer Type select
			dropDownMap.put("DS_PRIMER_TYPE", publicService.getDropdownList("DS_PRIMER_TYPE"));
			//Special Request select
			dropDownMap.put("DS_SPECIAL_REQUEST", publicService.getDropdownList("DS_SPECIAL_REQUEST"));
			//Primer Name
			List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
			speclListName.add(SpecDropDownListName.GET_PRIMER_NAME);
			specDropDownMap = publicService.getSpecDropDownMap(speclListName);
			dsPlatesList = quoteItemService.getDsPlateByQuoteNo(itemMap, sessQuoteNo);
			//获取所有的tube(Type为TUBE)
			tubeMap = quoteItemService.getDNASeqItemByType(itemMap).get("tubeMap");
			if (tubeMap != null && !tubeMap.isEmpty()) {
				for (String key : tubeMap.keySet()) {
		            QuoteItemDTO dto = tubeMap.get(key);
					tubeComment = dto.getDnaSequencing().getComment();
					break;
				}
			}
			//获取所有的plate(Type为PLATE)
			plateMap = quoteItemService.getDNASeqItemByType(itemMap).get("plateMap");
			Map<String, QuoteItemDTO> groupItemMap = this.getGroupItemMap(itemId);
			this.setServicesByItemMap(groupItemMap);
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
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
			if (isOnlyParent == true || isChildExist == true
					|| StringUtils.isEmpty(quoteItem.getParentId())) {
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
			if(clsId == 7){
				dropDownMap.put("VECTOR_LIST", publicService.getDropdownList("SIRNA_VECTOR_LIST"));
			}else if(clsId == 8){
				dropDownMap.put("VECTOR_LIST", publicService.getDropdownList("MIRNA_VECTOR_LIST"));
			}
			if (show.equals("muta")) {
				String topShow = MoreDetailUtil.getShow(topClsId);
				if(topShow.equalsIgnoreCase("gene") || topShow.equalsIgnoreCase("cloning")){
					disableTabStr = "muta";
					if (tab == null) {
						tab = "target";
					}
				}
			}
			Map<String, QuoteItemDTO> groupItemMap = null;
			if (isParent == true) {
				groupItemMap = this.getChildItemMap(itemId);
			} else {
				groupItemMap = this.getChildItemMap(quoteItem.getParentId());
			}
			this.setServicesByItemMap(groupItemMap);
			//更新session中ORF Clone服务的GenePrice、SubcloningPrice，前台显示用
			uptSessOrfServicePrice(topClsId, parentQuoteItemDTO);
			serviceListStr = Struts2Util.conventJavaObjToJson(quoteServiceDTO);
		}
		return "more";
	}

    //add by zhanghuibin Add batch Item

    public String showBachItem(){
        batchCustNo = ServletActionContext.getRequest().getParameter("custNo");
        itemNum = 1;
        return "quote_more_batch";
    }

    public String showBatchType(){
        return "select_batch_quotation";
    }


    /**
     * add by zhanghuibin
	 * 保存 geneSynthesis batch
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveBatchGeneSynthesis()  {
        batchFlag = true;
        Map<String, Object> messageMap = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> idsList = new ArrayList<String>();
        try {
            String baseItemId = itemId;
            itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
            QuoteItemDTO baseItemDto = itemMap.get(itemId);
            String[] seqCont = geneSynthesis.getSequenceContent().split(">");
            Iterator<String> it = itemMap.keySet().iterator();
            //cloning quoteItem
            QuoteItemDTO cloningItemDto = null;
            String cloningId = "";
            OrderPlasmidPreparationDTO basePlasmidDto = dozer.map(plasmidPreparation, OrderPlasmidPreparationDTO.class);
            OrderCustCloningDTO baseCloningDto = null;
            while (it.hasNext()) {
                String keyid = it.next().toString();
                QuoteItemDTO itemDTO = (QuoteItemDTO) itemMap.get(keyid);
                if (itemId.equals(itemDTO.getParentId())) {
                    cloningItemDto = itemDTO;
                    cloningId = keyid;
                    baseCloningDto = dozer.map(itemDTO.getCustCloning(), OrderCustCloningDTO.class);
                    break;
                }
            }
            idsList.add(baseItemId);
            for (int i = 1; i < seqCont.length; i++) {
                //对于每个geneSynthesis进行赋值
                    seqCont[i] = seqCont[i].replaceFirst("\r|\n|\r\n|\\s", "@@");
                    String geneName = seqCont[i].split("@@")[0];
                    String squ = seqCont[i].substring(seqCont[i].indexOf("@@")+2, seqCont[i].length());
                    squ = squ.replaceAll("\r|\n|\r\n|\\s", "");
                if (i == 1) {
                    geneSynthesis.setSequence(squ);
                    geneSynthesis.setGeneName(geneName);
                    //Direct Cloning
                    if("D".equals(geneSynthesis.getCloningFlag()) && cloningItemDto != null){
                        geneSynthesis.setStdVectorName(baseCloningDto.getTgtVector());
                        geneSynthesis.setVectorCopyNo(baseCloningDto.getTgtCopyNo());
                        geneSynthesis.setVectorMapDocFlag(baseCloningDto.getTgtMapDocFlag());
                        geneSynthesis.setVectorResistance(baseCloningDto.getTgtResistance());
                        geneSynthesis.setVectorSeq(baseCloningDto.getTgtVectorSeq());
                        geneSynthesis.setVectorSize(baseCloningDto.getTgtVectorSize());
                    }
                    //保存gene表单
                    itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
                    saveGeneSynthesis();
                    //Direct Cloning 类型也要删除
                    if (!Y.equals(geneSynthesis.getCloningFlag()) && cloningItemDto != null) {
                        //找出要删除的itemId
                        itemId = cloningId;
                        //删除cloning的quoteItem
                        removeFlag = 1;
                        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
                        saveCustCloning();
                        if(!batchMessage.isEmpty()){
                            Struts2Util.renderJson(batchMessage);
                            break;
                        }
                        removeFlag = null;
                    }else if(Y.equals(geneSynthesis.getCloningFlag())){
                        idsList.add(cloningId);
                    }
                    if (!N.equals(geneSynthesis.getPlasmidPrepFlag())) {
                        //保存plasmid表单
                        this.parentId = baseItemId;
                        itemId = "";
                        plasmidPreparation = dozer.map(basePlasmidDto, OrderPlasmidPreparationDTO.class);
                        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
                        savePlasmid();
                        if(!batchMessage.isEmpty()){
                            Struts2Util.renderJson(batchMessage);
                            break;
                        }
                        idsList.add(itemId);
                    }
                } else {
                    //复制gene的quoteItem
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
                    QuoteItemDTO itemDTO = dozer.map(baseItemDto, QuoteItemDTO.class);
                    itemDTO.setNameShow(itemDTO.getName()+": "+synthesisDTO.getGeneName());
                    itemDTO.setGeneSynthesis(synthesisDTO);
                    //新建对象加入session
                    String id = SessionUtil.generateTempId();
                    itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
                    /*
                    这里不需要 saveGeneSynthesis()方法，因为用 insertNewChild 方法 已可以把item加入到session中，而且每个gene只是sequence不同，
                    所以只把涉及到 sequence会影响到计算的属性改变即可
                    * */
                    quoteItem =  itemDTO;
                    this.insertNewChild(itemMap, itemDTO.getPreParentId(), id, itemDTO);
                    //计算基因分析类型Item的价格
                    ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(itemDTO);
                    orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.geneSynthesis.name());
                    orderServiceItemPiceDTO.setGeneSynthesisDTO(itemDTO.getGeneSynthesis());
                    quoteItemService.returnQuoteServiceItemPrice(sessQuoteNo, itemDTO, orderServiceItemPiceDTO);
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
                        //复制plasmid的quoteItem
                       plasmidPreparation = dozer.map(basePlasmidDto, OrderPlasmidPreparationDTO.class);
                        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
                        savePlasmid();
                        if(!batchMessage.isEmpty()){
                            Struts2Util.renderJson(batchMessage);
                            break;
                        }
                        idsList.add(itemId);
                    }
                    if (Y.equals(geneSynthesis.getCloningFlag())) {
                        itemId = "";
                        this.parentId = id;
                        //复制cloning的quoteItem
                        custCloning = dozer.map(baseCloningDto, OrderCustCloningDTO.class);
                        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
                        saveCustCloning();
                        if(!batchMessage.isEmpty()){
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
                    new Exception().getStackTrace()[0].getMethodName(),
                    "INTF0203", SessionUtil.getUserId());
            messageMap.put("hasException", Y);
            messageMap.put("exception", exDTO);
            Struts2Util.renderJson(messageMap);
            return null;
        }
        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
        String ids = "";
        ArrayList<QuoteItemDTO> resList = new ArrayList<QuoteItemDTO>();
        for(String id : idsList){
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

   //gene 批量上传
    @SuppressWarnings("unchecked")
    public String batchUploadFile() throws Exception {
        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
        quoteItem = itemMap.get(itemId);
        if (quoteItem == null) {
            Struts2Util.renderText("Fail to upload it, Please check the session");
            return null;
        }
        OrderCustCloningDTO custCloning = quoteItem.getCustCloning();
        List<Document> docList = custCloning.getDocumentList();
        if (docList == null) {
            docList = new ArrayList<Document>();
        }
        List<Document> newDocList = new ArrayList<Document>();
        uploadQuoteServiceFile(docList, newDocList);
        custCloning.setDocumentList(docList);
        quoteItem.setCustCloning(custCloning);
        quoteItem.setUpdateFlag(Y);
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
        dropDownMap.put("VECTOR_RESISTANCE",resistanceList);
        return "quote_cloning_batch";
    }
	
	private void initDropDownList() {
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownMap.put("PEPTIDE_SYNTHESIS_MEMBRANE",
				publicService.getDropdownList("PEPTIDE_SYNTHESIS_MEMBRANE"));
		dropDownMap.put("PEPTIDE_DELIVERY_FORMAT",
				publicService.getDropdownList("PEPTIDE_DELIVERY_FORMAT"));
		dropDownMap.put("QC_REPORT",
				publicService.getDropdownList("QC_REPORT"));
		dropDownMap.put("PEPTIDE_PURITY",
				publicService.getDropdownList("SC1177_PURITY"));
		dropDownMap.put("PEPTIDE_QUANTITY",
				publicService.getDropdownList("SC1177_QUANTITY"));
		dropDownMap.put("PEPTIDE_QC_REPORT",
				publicService.getDropdownList("SC1177_QC_REPORT"));
		dropDownMap.put("PEPTIDE_QC_REPORT_ARRAY",
				publicService.getDropdownList("QC_REPORT_ARRAY"));
		dropDownMap.put("PEPTIDE_LIB_MOD",
				publicService.getDropdownList("PEPTIDE_LIB_MOD"));
		dropDownMap.put("PEPTIDE_ARRAY_MOD",
				publicService.getDropdownList("PEPTIDE_ARRAY_MOD"));
		
		dropDownMap.put("PLASMID_ANTIBIOTIC_RESISTANCE",
				publicService.getDropdownList("PLASMID_ANTIBIOTIC_RESISTANCE"));
		dropDownMap.put("PLASMID_STORAGE_CONDITION",
				publicService.getDropdownList("PLASMID_STORAGE_CONDITION"));
		dropDownMap.put("PLASMID_RESTRICTION_ANALYSIS",
				publicService.getDropdownList("PLASMID_RESTRICTION_ANALYSIS"));
		dropDownMap.put("PLASMID_FRAGMENT",
				publicService.getDropdownList("PLASMID_FRAGMENT"));
		dropDownMap.put("PLASMID_DESIRED_BUFFER",
				publicService.getDropdownList("PLASMID_DESIRED_BUFFER"));
		dropDownMap.put("PLASMID_ADDITIONAL_ANALYSIS",
				publicService.getDropdownList("PLASMID_ADDITIONAL_ANALYSIS"));
		dropDownMap.put("PLASMID_QUANTITY",
				publicService.getDropdownList("PLASMID_QUANTITY"));
		
//		dropDownMap.put("PLASMID_WEIGHT",
//				publicService.getDropdownList("PLASMID_WEIGHT"));

		dropDownMap.put("SEQ_POSITION_UOM",
				publicService.getDropdownList("SEQ_POSITION_UOM"));
		dropDownMap.put("VECTOR_RESISTANCE",
				publicService.getDropdownList("VECTOR_RESISTANCE"));
		dropDownMap.put("PLASMID_WT_UOM",
				publicService.getDropdownList("PLASMID_WT_UOM"));
		dropDownMap.put("PLASMID_ADTL_SERVICE",
				publicService.getDropdownList("PLASMID_ADTL_SERVICE"));
		dropDownMap.put("VECTOR_RESISTANCE",
				publicService.getDropdownList("VECTOR_RESISTANCE"));
		dropDownMap.put("ORF_CLONES_VECTOR_MAP",
				publicService.getDropdownList("ORF_CLONES_VECTOR_MAP"));
	}

	
	/**
	 * 保存antibody
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveAntibody() throws Exception{
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		AntibodyDTO srcAntibody = quoteItem.getAntibody();
		if (srcAntibody == null) {
			srcAntibody = new AntibodyDTO();
		}
		AntibodyDTO targetAntibodyDTO = ((AntibodyDTO) ModelUtils.mergerModel(antibody, srcAntibody));
		if (StringUtils.isEmpty(quoteItem.getParentId())) {
			quoteItem.setNameShow(quoteItem.getName()+": "+targetAntibodyDTO.getAntibodyName());
		} else {
			quoteItem.setNameShow(quoteItem.getName());
		}
		quoteItem.setAntibody(targetAntibodyDTO);
		// 计算Antibody Item的价格
		ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
		orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.antibody.name());
		orderServiceItemPiceDTO.setAntibodyDTO(targetAntibodyDTO);
		returnOrderServiceItemPrice(orderServiceItemPiceDTO);
		
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
        quoteItem.setUpdateFlag(Y);
		retMap.put(itemId, quoteItem);
		Struts2Util.renderJson(retMap);
		return null;
	}
	
	/**
	 * 保存Orf Clone
	 * @author Zhang Yong
	 * modify date 2011-12-06
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveOrfClone() throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if (StringUtils.isEmpty(itemId) && orfClone != null && StringUtils.isNotBlank(orfClone.getPreOrfCloneId())
				&& itemMap.get(orfClone.getPreOrfCloneId()) != null && itemMap.get(orfClone.getPreOrfCloneId()).getClsId() == 6) {
			itemId = SessionUtil.generateTempId();
			//拷贝上一个ORF Clone信息
			QuoteItemDTO preOrfClone = itemMap.get(orfClone.getPreOrfCloneId());
			quoteItem = dozer.map(preOrfClone, QuoteItemDTO.class);
			quoteItem.setOrfClone(new OrderOrfCloneDTO());
			quoteItem.setQuoteItemId(null);
			quoteItem.setCustCloning(null);
			quoteItem.setPlasmidPreparation(null);
			quoteItem.setItemNo(itemMap.size()+1);
		}
		OrderOrfCloneDTO srcOrfClone = quoteItem.getOrfClone();
		OrderOrfCloneDTO targetOrfCloneDTO = (OrderOrfCloneDTO) ModelUtils.mergerModel(orfClone, srcOrfClone);
		String[] accessionInfo = targetOrfCloneDTO.getAccessionInfo().split("@@@");
		targetOrfCloneDTO.setAccessionNo(accessionInfo[0].split(":")[1]);
		targetOrfCloneDTO.setSeqType((accessionInfo[1].split(":")[1]).equals("Full Length")?"1":"0");
		quoteItem.setUnitPrice(new BigDecimal(accessionInfo[2].split(":")[1]));
		quoteItem.setBasePrice(quoteItem.getUnitPrice());
		quoteItem.setAmount(quoteItem.getUnitPrice().multiply(new BigDecimal(quoteItem.getQuantity())));
		quoteItem.setCost(new BigDecimal(accessionInfo[4].split(":")[1]));
		quoteItem.setNameShow(quoteItem.getName()+": "+targetOrfCloneDTO.getAccessionNo());
		quoteItem.setOrfClone(targetOrfCloneDTO);
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		quoteItem.setUpdateFlag(Y);
		itemMap.put(itemId, quoteItem);
		retMap.put(itemId, quoteItem);
		Struts2Util.renderJson(retMap);
		return null;
	}
	
	/**
	 * 保存Oligo
	 * @author zhang yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveOligo () throws Exception {
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (quoteItem != null && QuoteItemStatusType.CN.value().equalsIgnoreCase(quoteItem.getStatus())) {
			Struts2Util.renderJson(quoteItem);
			return null;
		}
		OrderOligoDTO srcOligo = quoteItem.getOligo();
		OrderOligoDTO targetOligoDTO = (OrderOligoDTO) ModelUtils.mergerModel(oligo, srcOligo);
		if (oligo.getDaPercent() == null || oligo.getDcPercent() == null || oligo.getDgPercent() == null || oligo.getDtPercent()== null) {
			targetOligoDTO.setDaPercent(null);
			targetOligoDTO.setDcPercent(null);
			targetOligoDTO.setDgPercent(null);
			targetOligoDTO.setDtPercent(null);
		}
		targetOligoDTO = orderService.getTargetOligoDTO(targetOligoDTO);
		if (StringUtils.isEmpty(quoteItem.getParentId())) {
			quoteItem.setNameShow(quoteItem.getName()+": "+targetOligoDTO.getOligoName());
		} else {
			quoteItem.setNameShow(quoteItem.getName());
		}
		if (StringUtils.isNotBlank(targetOligoDTO.getSequence())) {
			int length = targetOligoDTO.getSequence().replaceAll("\\/[^/]*\\/", "")
				.replaceAll("\\{.*\\}", "").replaceAll("\\(.*?\\)", "X").replaceAll(" ", "").length();
			targetOligoDTO.setSeqLength(length);
		}
		quoteItem.setOligo(targetOligoDTO);
		//查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
		if (!isChanged) {
			//计算Oligo Item的价格,方法里有对quoteItem的价格设值
			quoteOrderService.getOligoPriceAndCost(sessQuoteNo, quoteItem);
		}
        quoteItem.setUpdateFlag(Y);
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		Struts2Util.renderJson(quoteItem);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String saveCustomService() throws Exception{
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(quoteItem);
			return NONE;
		}
		CustomServiceDTO srcCustomService = quoteItem.getCustomService();
		if (srcCustomService == null) {
			srcCustomService = new CustomServiceDTO();
		}
		CustomServiceDTO targetCustomServiceDTO = ((CustomServiceDTO) ModelUtils.mergerModel(customService, srcCustomService));
		quoteItem.setNameShow(quoteItem.getName());
		quoteItem.setCustomService(targetCustomServiceDTO);
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
        quoteItem.setUpdateFlag(Y);
		Struts2Util.renderJson(quoteItem);
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
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (quoteItem != null && QuoteItemStatusType.CN.value().equalsIgnoreCase(quoteItem.getStatus())) {
			Struts2Util.renderJson(quoteItem);
			return null;
		}
		RnaDTO srcRna = quoteItem.getRna();
		if (!StringUtils.isEmpty(rna.getReadingFrame())) {
			if ((Y).equals(rna.getReadingFrame())) {
				rna.setReadingFrame(rna.getReadingFrame()+":"+rna.getGeneUsageText());
			}
		}
		RnaDTO targetRnaDTO = (RnaDTO) ModelUtils
		.mergerModel(rna, srcRna);
		String sequence = targetRnaDTO.getSequenceInsert();
		if(sequence != null){
			targetRnaDTO.setSeqLength(sequence.length());
		}
		if(quoteItem.getClsId().equals(7)){
			targetRnaDTO.setType("siRNA");
		}else{
			targetRnaDTO.setType("miRNA");
		}
		if (StringUtils.isEmpty(quoteItem.getParentId())) {
			quoteItem.setNameShow(quoteItem.getName()+": "+targetRnaDTO.getGeneName());
		} else {
			quoteItem.setNameShow(quoteItem.getName());
		}
        //add by zhanghuibin
        if("Other".equals(targetRnaDTO.getVectorName())){
            targetRnaDTO.setVectorName("Other:"+targetRnaDTO.getVectorOther());
        }
		quoteItem.setRna(targetRnaDTO);
		//查询orderItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
		boolean isChanged = quoteItemService.checkPriceChage(quoteItem);
		if (!isChanged) {
			//计算Rna类型Item的价格
			ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
			orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.rna.name());
			orderServiceItemPiceDTO.setRnaDTO(targetRnaDTO);
			returnOrderServiceItemPrice(orderServiceItemPiceDTO);
		}
        quoteItem.setUpdateFlag(Y);
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		Struts2Util.renderJson(quoteItem);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String changeAntigenType() throws Exception{
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(antibodyItemId);
		antibody = quoteItem.getAntibody();
        quoteItem.setUpdateFlag(Y);
		antibody.setAntigenType(antigenType);
		Map<String, QuoteItemDTO> groupItemMap = this.getGroupItemMap(antibodyItemId);
		Iterator<Entry<String, QuoteItemDTO>> it = groupItemMap.entrySet().iterator();
		List<String> itemIdList = new ArrayList<String>();
		while(it.hasNext()){
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpId = entry.getKey();
			QuoteItemDTO itemDTO = entry.getValue();
			if(itemDTO.getPeptide() != null){
				itemIdList.add(tmpId);
				itemMap.remove(tmpId);
			}
		}
		Iterator<Entry<String, QuoteItemDTO>> it2 = itemMap.entrySet().iterator();
		int itemNo = 1;
		while(it2.hasNext()){
			Entry<String, QuoteItemDTO> entry = it2.next();
			QuoteItemDTO itemDTO = entry.getValue();
			itemDTO.setItemNo(itemNo);
			itemNo++;
		}
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("message", "SUCCESS");
		retMap.put("itemIdList", itemIdList);
		Struts2Util.renderJson(retMap);
		return null;
	}
	
	/**
	 * 保存antibody 子服务peptide
	 * @author zhangyang
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String savePeptideForAntibody() throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			try {
				quoteItem = this.getNewChildSerItem(this.antibodyItemId, "Antibody-Pep", null);
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
			String preParentId = quoteItemService.getPreParentServiceId(itemMap, quoteItem);
			quoteItem.setPreParentId(preParentId);
			this.insertNewChild(itemMap, preParentId, itemId, quoteItem);
		}
		PeptideDTO srcPeptide = quoteItem.getPeptide();
		if (srcPeptide == null) {
			srcPeptide = new PeptideDTO();
		}
		if(peptide == null){
			peptide = new PeptideDTO();
		}
		
		PeptideDTO targetPeptideDTO = (PeptideDTO) ModelUtils.mergerModel(peptide, srcPeptide);
		if (StringUtils.isEmpty(targetPeptideDTO.getSequence()) && targetPeptideDTO.getSeqLength() != null) {
			StringBuffer seqBf = new StringBuffer();
			for (int i=0;i<targetPeptideDTO.getSeqLength().intValue();i++) {
				seqBf.append("A");
			}
			targetPeptideDTO.setSequence(seqBf.toString());
		}
		if(Y.equalsIgnoreCase(Struts2Util.getParameter("peptide.aminoChangeFlag")) && !StringUtils.isNumeric(itemId) && !targetPeptideDTO.isConvertFlag()){
			String seqOrigin = targetPeptideDTO.getSequence();
			StringBuilder seqSb = new StringBuilder();
			String[] seqArray = seqOrigin.split("");

			for(String s : seqArray){
				seqSb.append(biolibService.getPeptideCode(s));
			}
			targetPeptideDTO.setSequence(seqSb.toString());
			targetPeptideDTO.setConvertFlag(true);
		}
		String pepSequence = targetPeptideDTO.getSequence()==null?"":targetPeptideDTO.getSequence();
		targetPeptideDTO.setSeqLength(StringUtil.calculateSeqLength(pepSequence));
		quoteItem.setNameShow(quoteItem.getName()+": "+(targetPeptideDTO.getName()==null?"":targetPeptideDTO.getName()));
		quoteItem.setPeptide(targetPeptideDTO);
		if(!StringUtils.isEmpty(targetPeptideDTO.getSequence())){
			//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
			boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
			if (!isChanged) {
			// 计算Peptide Item的价格
				ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
				orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
				orderServiceItemPiceDTO.setPeptideDTO(targetPeptideDTO);
				quoteItemService.returnQuoteServiceItemPrice(sessQuoteNo, quoteItem, orderServiceItemPiceDTO);
			}
		}
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
        quoteItem.setUpdateFlag(Y);
		retMap.put(itemId, quoteItem);
		Struts2Util.renderJson(retMap);
//		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String calAntibodyPeptidePrice() throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (StringUtils.isEmpty(itemId)) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		} else {
			for (Iterator<String> iter = itemMap.keySet().iterator(); iter
			.hasNext();) {
				String key = iter.next();
				QuoteItemDTO itemDto = itemMap.get(key);
				String parentId = itemDto.getParentId();
				if(itemDto.getClsId() != null && StringUtils.isNotEmpty(parentId) 
						&& parentId.equals(itemId) && (itemDto.getClsId() == 1)){
					quoteItem = itemMap.get(key);
					itemId = key;
					break;
				}
			}
		}
		String parentId = quoteItem.getParentId();
		if (StringUtils.isNotBlank(parentId)) {
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			QuoteItemDTO quoteItemDTO = null;
			StringBuilder sb = new StringBuilder();
			boolean bFlag = false;
			String secondPepKey = "";
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				quoteItemDTO = entry.getValue();
				String key = entry.getKey();
				if (!(key.equals(itemId)) && quoteItemDTO.getParentId() != null
						&& quoteItemDTO.getParentId().equalsIgnoreCase(parentId)
						&& (quoteItemDTO.getClsId() == 1)) {
					Double peptidePrice = quoteItemDTO.getUnitPrice().doubleValue();
					sb.append(quoteItem.getUnitPrice()).append(",").append(
							peptidePrice);
					bFlag = true;
					secondPepKey = key;
					break;
				}
			}
			if (bFlag == false) {
				sb.append(quoteItem.getUnitPrice()).append(",").append(0);
			}
			System.out.println(">>>>>>>>>>>>>>>" + sb.toString());
			ServiceItemPiceDTO serviceItemPiceDTO = getCostPrice(quoteItem);
			serviceItemPiceDTO.setPriceStr(sb.toString());
			if (StringUtils.isNotBlank(quoteItem.getParentId())) {
				QuoteItemDTO parentDto = itemMap.get(quoteItem.getParentId());
				if (parentDto != null) {
					serviceItemPiceDTO.setCatalogNo(parentDto.getCatalogNo());
				}
			}
			serviceItemPiceDTO = publicService
					.getAntibodyPeptidePrice(serviceItemPiceDTO);
			String priceStr = serviceItemPiceDTO.getPriceStr();
			System.out.println("**********************Price: " + priceStr);
			String[] priceArr = priceStr.split(",");
			if (StringUtils.isNotBlank(secondPepKey)) {
				QuoteItemDTO secondOrderItemDTO = itemMap.get(secondPepKey);
				secondOrderItemDTO
						.setUnitPrice(new BigDecimal(Double.parseDouble(priceArr[1])));
				secondOrderItemDTO.setAmount(new BigDecimal(ArithUtil.mul(Double
						.parseDouble(priceArr[1]), serviceItemPiceDTO
						.getQuantity().doubleValue())));
				retMap.put(secondPepKey, secondOrderItemDTO);
				itemMap.put(secondPepKey, secondOrderItemDTO);
			}
			quoteItem.setUnitPrice(new BigDecimal(Double.parseDouble(priceArr[0])));
			quoteItem.setAmount(quoteItem.getUnitPrice().multiply(new BigDecimal(quoteItem.getQuantity().doubleValue())));
		}
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
        quoteItem.setUpdateFlag(Y);
		retMap.put(itemId, quoteItem);
		Struts2Util.renderJson(retMap);
		return NONE;
	}
	
	/**
	 * 为peptide 页面设置下拉数据
	 */
	private void setDropdownForPeptide(){
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.PEPTIDE_QUANTITY);
		speclListName.add(SpecDropDownListName.PEPTIDE_PURITY);
		speclListName.add(SpecDropDownListName.PEPTIDE_PURITY_ARRAY);
		speclListName.add(SpecDropDownListName.N_TERMINAL);
		speclListName.add(SpecDropDownListName.C_TERMINAL);
		speclListName.add(SpecDropDownListName.MODIFICATION);
		specDropDownMap = publicService.getSpecDropDownMap(speclListName);
	}

	

	@SuppressWarnings("unchecked")
	public String savePkgService() throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
			quoteItem = this.getNewChildPkgSerItem(this.parentId, quotePkgService.getCatalogNo());
			String preParentId = quoteItemService.getPreParentServiceId(itemMap, quoteItem);
			quoteItem.setPreParentId(preParentId);
			this.insertNewChild(itemMap, preParentId, itemId, quoteItem);
		}
		PkgServiceDTO srcQuotePkgService = quoteItem.getQuotePkgService();
		if (srcQuotePkgService == null) {
			srcQuotePkgService = new PkgServiceDTO();
		}
		PkgServiceDTO ps = (PkgServiceDTO) ModelUtils.mergerModel(quotePkgService, srcQuotePkgService);
		ps.setUpSymbol(quoteItem.getUpSymbol());
		ps.setCatalogNo(quoteItem.getCatalogNo());
		ps.setItemName(quoteItem.getName());
		if (StringUtils.isEmpty(quoteItem.getParentId()) && StringUtils.isNotBlank(ps.getName())) {
			quoteItem.setNameShow(quoteItem.getName()+": "+ps.getName());
		} else {
			quoteItem.setNameShow(quoteItem.getName());
		}
		quoteItem.setQuotePkgService(ps);
		if(MoreDetailUtil.isProteinGroupStep(quoteItem.getClsId())){
			if (StringUtils.isBlank(ps.getSeqFlag()) || !ps.getSeqFlag().equalsIgnoreCase(Y)
					|| (ps.getUnitPrice() != null && ps.getUnitPrice().doubleValue()!=0)
					|| (ps.getCost() != null && ps.getCost().doubleValue()!=0)) {
				quoteItem.setUnitPrice(ps.getUnitPrice()==null?new BigDecimal(0):ps.getUnitPrice());
				quoteItem.setAmount(quoteItem.getUnitPrice().multiply(new BigDecimal(quoteItem.getQuantity())));
				quoteItem.setCost(ps.getCost()==null?(quoteItem.getUnitPrice().multiply(new BigDecimal(0.5))):ps.getCost());
			} else {
				// 查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
				boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
				if (!isChanged) {
					// 计算PkgService Item的价格
					ServiceItemPiceDTO quoteServiceItemPiceDTO = new ServiceItemPiceDTO();
					quoteServiceItemPiceDTO.setServiceClsId(3);
					quoteServiceItemPiceDTO.setCatalogId(quoteItem.getCatalogId());
					quoteServiceItemPiceDTO.setCatalogNo("SC1010");
					quoteServiceItemPiceDTO.setQuantity(quoteItem.getQuantity());
					quoteServiceItemPiceDTO.setServicePriceType(ServicePriceType.geneSynthesis.name());
					OrderGeneSynthesisDTO geneSynthesisDTO = new OrderGeneSynthesisDTO();
					String dnaSequence = ps.getSequence();
					String proteinSeq = ps.getProteinSeq();
					if (StringUtils.isNotBlank(dnaSequence)) {
						geneSynthesisDTO.setSequenceType("DNA");
						dnaSequence.replaceAll(" ", "");
						geneSynthesisDTO.setSequence(dnaSequence);
					} else {
						geneSynthesisDTO.setSequenceType("Protein");
						proteinSeq.replaceAll(" ", "");
						geneSynthesisDTO.setSequence(proteinSeq);
					}
					geneSynthesisDTO.setBpCost(ps.getSeqBpCost());
					geneSynthesisDTO.setBpPrice(ps.getSeqBpPrice());
					quoteServiceItemPiceDTO.setGeneSynthesisDTO(geneSynthesisDTO);
					returnOrderServiceItemPrice(quoteServiceItemPiceDTO);
					BigDecimal[] priceCost = servService.getPriceCost(quoteItem.getCatalogNo(), quoteItem.getCatalogId(), ps.getName(), Y);
					if (priceCost != null) {
						QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
						Double rate = publicService.getRateByBaseCurrencyToCurrency(quoteMain.getQuoteCurrency(), new Date());
						if (rate == null) {
							throw new BussinessException(BussinessException.EXCH_RATE_IS_NULL);
						}
						if (priceCost[0] != null) {
							quoteItem.setBasePrice(quoteItem.getBasePrice().add(priceCost[0]));
							quoteItem.setUnitPrice(quoteItem.getUnitPrice().add(priceCost[0].multiply(new BigDecimal(rate))));
							quoteItem.setAmount(quoteItem.getUnitPrice().multiply(new BigDecimal(quoteItem.getQuantity())));
						}
						if (priceCost[1] != null) {
							quoteItem.setCost(quoteItem.getCost().add(priceCost[1]));
						}
					}
					ps.setUnitPrice(quoteItem.getUnitPrice());
					ps.setCost(quoteItem.getCost());
				}
			}
		}
		//设置leadTime
		Integer parentStepServiceId = servService.getIntermediateParentServiceId(quoteItem.getCatalogNo());
		if(parentStepServiceId != null){
			quoteItem.setShipSchedule(servService.getLeadTimeByServiceId(parentStepServiceId));
		}
		if(!StringUtils.isEmpty(parentId)){
			Map<String, QuoteItemDTO> childItemMap = quoteOrderService.getChildItemMap(itemMap, parentId);
			Integer totalLeadTime = quoteOrderService.getTotalLeadTime(childItemMap);
			QuoteItemDTO parentItem = itemMap.get(parentId);
			parentItem.setShipSchedule(totalLeadTime);
		}
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
        quoteItem.setUpdateFlag(Y);
		retMap.put(itemId, quoteItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 为quotepkg service 生成新的item
	 *
	 * @param parentId
	 * @return
	 */
	private QuoteItemDTO getNewChildPkgSerItem(String parentId, String catalogNo) {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		QuoteItemDTO parentItem = itemMap.get(parentId);
		QuoteItemDTO itemInfo = this.getBaseInfoByCatalogNo(catalogNo, quote.getCustNo());
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
	 * 获得父子相关联的所有item
	 *
	 * @param itemId
	 * @return
	 */
	private Map<String, QuoteItemDTO> getChildItemMap(String itemId) {
		Map<String, QuoteItemDTO> newItemMap = new HashMap<String, QuoteItemDTO>();
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = quoteItemDTO.getParentId();
			if (tmpId.equals(itemId)) {
				newItemMap.put(tmpId, quoteItemDTO);
			}
			if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, quoteItemDTO);
			}
		}
		return newItemMap;
	}


	/**
	 * 保存 geneSynthesis
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveGeneSynthesis() {
		try{
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			if (quoteItem != null && QuoteItemStatusType.CN.value().equalsIgnoreCase(quoteItem.getStatus())) {
				Struts2Util.renderJson(quoteItem);
				return null;
			}
			OrderGeneSynthesisDTO srcGeneSynthesis = quoteItem.getGeneSynthesis();
            //add by zhanghuibin
            if(srcGeneSynthesis == null){
                srcGeneSynthesis = new OrderGeneSynthesisDTO();
            }
			if (!StringUtils.isEmpty(geneSynthesis.getReadingFrame())) {
				if ((Y).equals(geneSynthesis.getReadingFrame())) {
					geneSynthesis.setReadingFrame(geneSynthesis.getReadingFrame()+":"+geneSynthesis.getGeneUsageText());
				}
			}
			OrderGeneSynthesisDTO targetGeneSynthesisDTO = (OrderGeneSynthesisDTO) ModelUtils
			.mergerModel(geneSynthesis, srcGeneSynthesis);
			String sequence = targetGeneSynthesisDTO.getSequence();
			String sequenceType = targetGeneSynthesisDTO.getSequenceType();
			if (geneSynthesis.getHostExpsOrganism() != null
					&& geneSynthesis.getHostExpsOrganism().contains("Other")
					&& !StringUtils.isEmpty(geneSynthesis.getHostExpsOrgOther())) {
				targetGeneSynthesisDTO.setHostExpsOrganism(geneSynthesis.getHostExpsOrganism()
						+ ";" + geneSynthesis.getHostExpsOrgOther());
			}
			if (geneSynthesis.getScndExpsOrganism() != null
					&& geneSynthesis.getScndExpsOrganism().contains("Other")
					&& !StringUtils.isEmpty(geneSynthesis.getScndExpsOrganismOther())) {
				targetGeneSynthesisDTO.setScndExpsOrganism(geneSynthesis.getScndExpsOrganism()
						+ ";" + geneSynthesis.getScndExpsOrganismOther());
			}
			if("Length".equalsIgnoreCase(sequenceType)){
				if(StringUtils.isNumeric(sequence)){
					targetGeneSynthesisDTO.setSeqLength(Integer.parseInt(sequence));
				}else{
					targetGeneSynthesisDTO.setSeqLength(0);
				}
			}else if ("Protein".equalsIgnoreCase(sequenceType)) {
				targetGeneSynthesisDTO.setSeqLength(sequence.length()*3);
			} else if ("DNA".equalsIgnoreCase(sequenceType)){
				String threeSequence = targetGeneSynthesisDTO.getSequence3() == null ? "" : targetGeneSynthesisDTO.getSequence3();
				String fiveSequence = targetGeneSynthesisDTO.getSequence5() == null ? "" : targetGeneSynthesisDTO.getSequence5();
				String sequenceStr = targetGeneSynthesisDTO.getSequence() == null ? "" : targetGeneSynthesisDTO.getSequence();
				StringBuilder sequenceSB = new StringBuilder();
				sequenceSB.append(threeSequence).append(fiveSequence).append(sequenceStr);
				targetGeneSynthesisDTO.setSeqLength(sequenceSB.toString().length());
			}
			if (StringUtils.isEmpty(quoteItem.getParentId())) {
				quoteItem.setNameShow(quoteItem.getName()+": "+targetGeneSynthesisDTO.getGeneName());
			} else {
				quoteItem.setNameShow(quoteItem.getName());
			}
			quoteItem.setGeneSynthesis(targetGeneSynthesisDTO);

			//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
			boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
			if (!isChanged ) {
				//计算基因分析类型Item的价格
				ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
				orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.geneSynthesis.name());
				orderServiceItemPiceDTO.setGeneSynthesisDTO(targetGeneSynthesisDTO);
				returnOrderServiceItemPrice(orderServiceItemPiceDTO);
				if (StringUtils.isNotBlank(quoteItem.getTbdFlag()) && quoteItem.getTbdFlag().equals("1")) {
					quoteItem.getGeneSynthesis().setDfcltSeqFlag(Y);
				} else {
					quoteItem.getGeneSynthesis().setDfcltSeqFlag(N);
				}
			}
            quoteItem.setUpdateFlag(Y);
			SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
            if (batchFlag) {
                return null;
            }
			Struts2Util.renderJson(quoteItem);
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param quoteServiceItemPiceDTO
	 */
	private void returnOrderServiceItemPrice(ServiceItemPiceDTO quoteServiceItemPiceDTO) {
		quoteItemService.returnQuoteServiceItemPrice(sessQuoteNo, quoteItem, quoteServiceItemPiceDTO);
	}

	/**
	 * 保存 custCloning
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveCustCloning(){
		try{
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return null;
		}
		if(StringUtils.isEmpty(itemId)){
			itemId = SessionUtil.generateTempId();
			try {
				quoteItem = this.getNewChildSerItem(this.parentId, "custCloning", null);
			} catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				messageMap.put("hasException", Y);
				messageMap.put("exception", exDTO);
                //add by zhanghuibin
                if(batchFlag){
                    batchMessage = messageMap;
                    return null;
                }
				Struts2Util.renderJson(messageMap);
				return null;
			}
			String preParentId = quoteItem.getParentId();
			if(quoteItem!=null&&quoteItem.getParentId()!=null&&StringUtils.isNotEmpty(quoteItem.getParentId())) {
				QuoteItemDTO parentItem = null;
				if(StringUtil.isNumeric(quoteItem.getParentId())) {
					parentItem = this.quoteItemService.findById(Integer.parseInt(quoteItem.getParentId()));
				} else {
					parentItem = (QuoteItemDTO)itemMap.get(quoteItem.getParentId());
				}
				if(parentItem!=null) {
					String parentType = MoreDetailUtil.getServiceNameByClsId(parentItem.getClsId());
					if("geneSynthesis".equals(parentType)||"mutagenesis".equals(parentType)) {
						quoteItem.setSize(new Double(4));
						quoteItem.setSizeUom("ug");
					}
				}
				preParentId = quoteItemService.getPreParentServiceId(itemMap, quoteItem);
				quoteItem.setPreParentId(preParentId);
			}
			this.insertNewChild(itemMap, preParentId, itemId, quoteItem);
		}
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		if(removeFlag != null && removeFlag.equals(1)){
			//updateParentSizeByClone();
			if(StringUtils.isNumeric(itemId)){
				List<Integer> delItemIdList = (List<Integer>) SessionUtil.getRow(SessionConstant.QuoteItemDelList.value(), sessQuoteNo);
				if(delItemIdList == null){
					delItemIdList = new ArrayList<Integer>();
				}
				delItemIdList.add(Integer.parseInt(itemId));
				SessionUtil.insertRow(SessionConstant.QuoteItemDelList.value(), sessQuoteNo, delItemIdList);
				itemMap.remove(itemId);
			}else{
				itemMap.remove(itemId);
			}
            if (batchFlag) {
                return null;
            }
			retMap.put(itemId, new QuoteItemDTO());
			Struts2Util.renderJson(retMap);
		}else{
			OrderCustCloningDTO srcCustCloning = quoteItem.getCustCloning();
			if (srcCustCloning == null) {
				srcCustCloning = new OrderCustCloningDTO();
			}
			if (!StringUtils.isEmpty(custCloning.getReadingFrame())) {
				if ((Y).equals(custCloning.getReadingFrame())) {
					custCloning.setReadingFrame(custCloning.getReadingFrame()+":"+custCloning.getGeneUsageText());
				}
			}
			OrderCustCloningDTO targetCustCloningDTO = (OrderCustCloningDTO) ModelUtils.mergerModel(custCloning, srcCustCloning);
			if (custCloning.getTgtVector() != null && custCloning.getTgtVector().contains("Other")
					&& !StringUtils.isEmpty(custCloning.getTgtVectorOther())) {
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
			String parentId = quoteItem.getParentId();
			if(StringUtils.isNotBlank(parentId)){
				System.out.println("Custome Cloning item parent id is: " + parentId);
				QuoteItemDTO geneItem = itemMap.get(parentId);
				OrderGeneSynthesisDTO geneSynthesisDTO = geneItem.getGeneSynthesis();
				if(geneSynthesisDTO == null){
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
						sequenceSB.append(threeSequence).append(
								fiveSequence).append(sequenceStr);
						value = sequenceSB.toString().length();
					} else if (seqTypeVal.equals("Protein")) {
						value = sequenceStr.length() * 3;
					} else if (seqTypeVal.equals("Length")) {
						value = geneSynthesisDTO.getSeqLength();
					}else {
						throw new RuntimeException(
								"no this gene sequence type exist");
					}
					targetCustCloningDTO.setTgtSeqLength(value);
				}
			}else{
				String tgtSequence = targetCustCloningDTO.getTgtSequence();
				if ((Y).equals(targetCustCloningDTO.getTgtSeqSameFlag())) {
					tgtSequence = targetCustCloningDTO.getTmplSequence();
				}
				if (tgtSequence == null) {
					targetCustCloningDTO.setTgtSeqLength(0);
				} else {
					targetCustCloningDTO.setTgtSeqLength(tgtSequence.length());
				}
			}
			if (StringUtils.isEmpty(quoteItem.getParentId())) {
				quoteItem.setNameShow(quoteItem.getName()+": "+targetCustCloningDTO.getTmplInsertName());
			} else {
				quoteItem.setNameShow(quoteItem.getName());
			}
			if (StringUtils.isNotBlank(quoteItem.getParentId())) {
				QuoteItemDTO parentItemDTO = itemMap.get(quoteItem.getParentId());
				targetCustCloningDTO.setParentClsId(parentItemDTO.getClsId());
			}
			quoteItem.setCustCloning(targetCustCloningDTO);
			//updateParentSizeByClone();
			//add by Zhang Yong,父Item如果是ORF Clone，计算价格规则不同
			boolean isORFCloneSub = false;
			if (StringUtils.isNotBlank(quoteItem.getParentId())) {
				QuoteItemDTO parentItemDTO = itemMap.get(quoteItem.getParentId());
				if (parentItemDTO.getClsId() == 6) {
					isORFCloneSub = true;
					if (StringUtils.isNotBlank(targetCustCloningDTO.getTgtVector()) && (pUC57.equals(targetCustCloningDTO.getTgtVector()) || targetCustCloningDTO.getTgtVector().startsWith(Other))) {
						BigDecimal zero = new BigDecimal(0);
						quoteItem.setBasePrice(zero);
						quoteItem.setUnitPrice(zero);
						quoteItem.setAmount(zero);
						quoteItem.setCost(zero);
						quoteItem.setDiscount(zero);
						quoteItem.setTbdFlag(tbd_1);
						if (pUC57.equals(targetCustCloningDTO.getTgtVector())) {
							quoteItem.setTbdFlag(tbd_0);
						}
					} else {
						String[] accessionInfo = parentItemDTO.getOrfClone().getAccessionInfo().split("@@@");
						quoteItem.setBasePrice(new BigDecimal(accessionInfo[3].split(":")[1]));
						quoteItem.setUnitPrice(quoteItem.getBasePrice());
						quoteItem.setAmount(quoteItem.getUnitPrice().multiply(new BigDecimal(quoteItem.getQuantity())));
						quoteItem.setCost(new BigDecimal(accessionInfo[5].split(":")[1]));
						quoteItem.setTbdFlag(tbd_0);
					}
				}
			}
			if (isORFCloneSub == false) {
				//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
				boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
				if (!isChanged) {
					//计算CustCloning Item的价格
					ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
					orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.custCloning.name());
					orderServiceItemPiceDTO.setCustCloningDTO(targetCustCloningDTO);
					returnOrderServiceItemPrice(orderServiceItemPiceDTO);
				}
			}
            quoteItem.setUpdateFlag(Y);
            if (batchFlag) {
                return null;
            }
			retMap.put(itemId, quoteItem);
			Struts2Util.renderJson(retMap);
		}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 如果是Clone子服务，如果同一parentItem的item中含有plassmit则更新clone的size和parent的size
	 * @author Zhang Yong
	 */
	@SuppressWarnings("unused")
	private void updateParentSizeByClone () {
		if (StringUtils.isBlank(quoteItem.getParentId())) {
			return;
		}
		QuoteItemDTO parentDto = itemMap.get(quoteItem.getParentId());
		if (parentDto == null || parentDto.getClsId() == null) {
			return;
		}
		QuoteItemDTO plassmidDto = null;
		String plassmidKey = null;
		Iterator<Entry<String, QuoteItemDTO>> iter = itemMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, QuoteItemDTO> entry = iter.next();
			String key = entry.getKey();
			QuoteItemDTO dto = entry.getValue();
			if (quoteItem.getParentId().equals(dto.getParentId()) && dto.getClsId() == 10
					&& QuoteItemType.SERVICE.value().equalsIgnoreCase(dto.getType())) {
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
				parentDto.setSize(quoteItem.getSize());
				parentDto.setSizeUom(quoteItem.getSizeUom());
				itemMap.put(quoteItem.getParentId(), parentDto);
			}
		} else {
			if (containPlassmid == true && plassmidDto.getPlasmidPreparation() != null) {
				OrderPlasmidPreparationDTO opdto = plassmidDto.getPlasmidPreparation();
				quoteItem.setSize(opdto.getPrepWeight()!=null?opdto.getPrepWeight().doubleValue():0);
				quoteItem.setSizeUom(opdto.getPrepWtUom());
				//parentItem的size和sizeUom重置
				List<Service> dbServicelist = servService.getServiceByCatalogNo(parentDto.getCatalogNo());
				if (dbServicelist != null && !dbServicelist.isEmpty()) {
					parentDto.setSize(dbServicelist.get(0).getSize());
					parentDto.setSizeUom(dbServicelist.get(0).getUom());
				} else {
					parentDto.setSize(0d);
					parentDto.setSizeUom("");
				}
				itemMap.put(quoteItem.getParentId(), parentDto);
			}
		}
        parentDto.setUpdateFlag(Y);
	}

	/**
	 * 保存plasmid
	 *
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String savePlasmid() {
		try{
			Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
			Map<String, Object> messageMap = new HashMap<String, Object>();
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			if (StringUtils.isNotBlank(itemId) && itemId.contains(",")) {
				itemId = itemId.substring(0,itemId.indexOf(","));
			}
			quoteItem = itemMap.get(itemId);
			if (!checkStatus(retMap).isEmpty()) {
                 if(batchFlag){
                    return null;
                }
				Struts2Util.renderJson(retMap);
				return null;
			}
			if (StringUtils.isEmpty(itemId)) {
				itemId = SessionUtil.generateTempId();
				try {
					quoteItem = this.getNewChildSerItem(this.parentId, "plasmidPreparation", null);
				} catch (Exception e) {
					WSException exDTO = exceptionUtil.getExceptionDetails(e);
					exceptionUtil.logException(exDTO, this.getClass(), e,
							new Exception().getStackTrace()[0].getMethodName(),
							"INTF0203", SessionUtil.getUserId());
					messageMap.put("hasException", Y);
                    messageMap.put("exception", exDTO);
                    //add by zhanghuibin
                    if (batchFlag) {
                        batchMessage = messageMap;
                        return null;
                    }
					Struts2Util.renderJson(messageMap);
					return null;
				}
				String preParentId = quoteItemService.getPreParentServiceId(itemMap, quoteItem);
				quoteItem.setPreParentId(preParentId);
				this.insertNewChild(itemMap, preParentId, itemId, quoteItem);
			}
			SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
			if (removeFlag != null && removeFlag.equals(1)) {
				//updateParentSize();
				if (StringUtil.isNumeric(itemId)) {
					List<Integer> delItemIdList = (List<Integer>) SessionUtil
							.getRow(SessionConstant.QuoteItemDelList.value(), sessQuoteNo);
					if (delItemIdList == null) {
						delItemIdList = new ArrayList<Integer>();
					}
					delItemIdList.add(Integer.parseInt(itemId));
					SessionUtil.insertRow(SessionConstant.QuoteItemDelList.value(),
							sessQuoteNo, delItemIdList);
				}
                itemMap.remove(itemId);
                //add by zhanghuibin
                if (batchFlag) {
                    return null;
                }
				// itemNo 顺序问题
				retMap.put(itemId, new QuoteItemDTO());
				Struts2Util.renderJson(retMap);
			} else {
				OrderPlasmidPreparationDTO srcPlasmidPreparation = quoteItem
						.getPlasmidPreparation();
				if (srcPlasmidPreparation == null) {
					srcPlasmidPreparation = new OrderPlasmidPreparationDTO();
				}
				if(StringUtils.isNotEmpty(plasmidPreparation.getSequence())) {
					postSeq(plasmidPreparation.getSequence());
				}
				if(plasmidPreparation.getBioBurdenAssay()==null||plasmidPreparation.getBioBurdenAssay().equals("false")) {
					plasmidPreparation.setBioBurdenAssay(N);
				}
				if(plasmidPreparation.getCustomSequencing()==null||plasmidPreparation.getCustomSequencing().equals("false")) {
					plasmidPreparation.setCustomSequencing(N);
				}
				if (StringUtils.isNotBlank(plasmidPreparation.getPrepWeightStr())) {
					String[] prepWeightStrs = plasmidPreparation.getPrepWeightStr().trim().split(" ");
					if (prepWeightStrs.length == 2) {
						plasmidPreparation.setPrepWeight(BigDecimal.valueOf(Double.valueOf(prepWeightStrs[0])));
						plasmidPreparation.setPrepWtUom(prepWeightStrs[1]);
					} else {
						plasmidPreparation.setPrepWeight(BigDecimal.valueOf(Double.valueOf(prepWeightStrs[0])));
						plasmidPreparation.setPrepWtUom("mg");
					}
				}
				if(plasmidPreparation.getFragmentArray()!=null) {

					plasmidPreparation.setFragment(StringUtil.implode(plasmidPreparation.getFragmentArray(), "::"));
				}
				OrderPlasmidPreparationDTO targetPlasmidPreparationDTO = (OrderPlasmidPreparationDTO) ModelUtils
						.mergerModel(plasmidPreparation, srcPlasmidPreparation);
				if (StringUtils.isEmpty(quoteItem.getParentId())) {
					quoteItem.setNameShow(quoteItem.getName()+": "+targetPlasmidPreparationDTO.getPlasmidName());
				} else {
					quoteItem.setNameShow(quoteItem.getName());
					targetPlasmidPreparationDTO.setCopyNumber("High");
				}
				if (StringUtils.isBlank(targetPlasmidPreparationDTO.getCopyNumber())) {
					targetPlasmidPreparationDTO.setCopyNumber("High");
				}
				quoteItem.setPlasmidPreparation(targetPlasmidPreparationDTO);
				if(targetPlasmidPreparationDTO.getPrepWeight()!=null) {
					quoteItem.setSize(targetPlasmidPreparationDTO.getPrepWeight().doubleValue());
					quoteItem.setSizeUom(plasmidPreparation.getPrepWtUom());
					//updateParentSize();
				}
				//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
				boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
				if (!isChanged) {
					// 计算PlasmidPreparation Item的价格
					ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
					orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.plasmidPreparation.name());
					orderServiceItemPiceDTO.setPlasmidPreparationDTO(targetPlasmidPreparationDTO);
					returnOrderServiceItemPrice(orderServiceItemPiceDTO);
				}
				//add by Zhang Yong,父Item如果是ORF Clone且Plasmid Preparation weight为10ug，有些属性需赋为空
				if (StringUtils.isNotBlank(quoteItem.getParentId())) {
					QuoteItemDTO parentItemDTO = itemMap.get(quoteItem.getParentId());
					if (parentItemDTO.getClsId() == 6) {
						if (StringUtils.isNotBlank(targetPlasmidPreparationDTO.getPrepWeightStr()) && ("10 ug".equals(targetPlasmidPreparationDTO.getPrepWeightStr()))) {
							targetPlasmidPreparationDTO.setQualityGrade(null);
						}
					}
				}
                quoteItem.setUpdateFlag(Y);
                if(Y.equalsIgnoreCase(plasmidPreparation.getCustomSequencing())){
					quoteItem.setTbdFlag("1");
				}
                if (batchFlag) {
                    return null;
                }
				retMap.put(itemId, quoteItem);
				Struts2Util.renderJson(retMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 如果是Plassmit子服务，则更新parent的size或同一parentItem的clone的size
	 * @author Zhang Yong
	 */
	@SuppressWarnings("unused")
	private void updateParentSize () {
		if (StringUtils.isBlank(quoteItem.getParentId())) {
			return;
		}
		QuoteItemDTO parentDto = itemMap.get(quoteItem.getParentId());
		if (parentDto == null || parentDto.getClsId() == null) {
			return;
		}
		QuoteItemDTO cloneDto = null;
		String cloneKey = null;
		Iterator<Entry<String, QuoteItemDTO>> iter = itemMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, QuoteItemDTO> entry = iter.next();
			String key = entry.getKey();
			QuoteItemDTO dto = entry.getValue();
			if (quoteItem.getParentId().equals(dto.getParentId()) && dto.getClsId() == 9
					&& QuoteItemType.SERVICE.value().equalsIgnoreCase(dto.getType())) {
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
				List<Service> dbServicelist = servService.getServiceByCatalogNo(cloneDto.getCatalogNo());
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
				List<Service> dbServicelist = servService.getServiceByCatalogNo(parentDto.getCatalogNo());
				if (dbServicelist != null && !dbServicelist.isEmpty()) {
					parentDto.setSize(dbServicelist.get(0).getSize());
					parentDto.setSizeUom(dbServicelist.get(0).getUom());
				} else {
					parentDto.setSize(0d);
					parentDto.setSizeUom("");
				}
                parentDto.setUpdateFlag(Y);
				itemMap.put(quoteItem.getParentId(), parentDto);
			}
		} else {
			if (containClone == true) {
				cloneDto.setSize(quoteItem.getSize());
				cloneDto.setSizeUom(quoteItem.getSizeUom());
                cloneDto.setUpdateFlag(Y);
				itemMap.put(cloneKey, cloneDto);
			} else {
				parentDto.setSize(quoteItem.getSize());
				parentDto.setSizeUom(quoteItem.getSizeUom());
                parentDto.setUpdateFlag(Y);
				itemMap.put(quoteItem.getParentId(), parentDto);
			}
			quoteItem.setSize(0d);
		}
	}

	/**
	 * 根据填入的sequence获得cloning_ready_flag和accession_no
	 */
	private void postSeq(String sequence) {
		StringBuffer url = new StringBuffer("http://192.168.1.105/cgi-bin/blastsvr.cgi");
		String returnStr = "";
		StringBuffer param = new StringBuffer();
		param.append("dnaseq=").append(sequence);
		try {
			returnStr = UrlUtil.postReq(url.toString(),param.toString());
			if(StringUtils.isNotEmpty(returnStr)) {
				plasmidPreparation.setCloningReadyFlag(Y);
			} else {
				plasmidPreparation.setCloningReadyFlag(N);
				return;
			}
			String[] returnStrArray = returnStr.split("\t");
			if(returnStrArray!=null) {
				if(returnStrArray.length>=8) {
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
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveMuta(){
		try{
			Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
			Map<String, Object> messageMap = new HashMap<String, Object>();
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			if (!checkStatus(retMap).isEmpty()) {
				Struts2Util.renderJson(retMap);
				return null;
			}
			if(StringUtils.isEmpty(itemId)){
				itemId = SessionUtil.generateTempId();
				try {
					quoteItem = this.getNewChildSerItem(this.parentId, "mutagenesis", null);
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
				String preParentId = quoteItemService.getPreParentServiceId(itemMap, quoteItem);
				quoteItem.setPreParentId(preParentId);
				this.insertNewChild(itemMap, preParentId, itemId, quoteItem);
			}
			SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
			if(removeFlag != null && removeFlag.equals(1)){
				if(StringUtils.isNumeric(itemId)){
					List<Integer> delItemIdList = (List<Integer>) SessionUtil.getRow(SessionConstant.QuoteItemDelList.value(), sessQuoteNo);
					if(delItemIdList == null){
						delItemIdList = new ArrayList<Integer>();
					}
					delItemIdList.add(Integer.parseInt(itemId));
					SessionUtil.insertRow(SessionConstant.QuoteItemDelList.value(), sessQuoteNo, delItemIdList);
					itemMap.remove(itemId);
				}else{
					itemMap.remove(itemId);
				}
				//itemNo 顺序问题
				retMap.put(itemId, new QuoteItemDTO());
				Struts2Util.renderJson(retMap);
			}else{
				OrderMutagenesisDTO srcMutagenesis = quoteItem.getMutagenesis();
				if(srcMutagenesis == null){
					srcMutagenesis = new OrderMutagenesisDTO();
				}
				if (!StringUtils.isEmpty(mutagenesis.getReadingFrame())) {
					if ((Y).equals(mutagenesis.getReadingFrame())) {
						mutagenesis.setReadingFrame(mutagenesis.getReadingFrame()+":"+mutagenesis.getGeneUsageText());
					}
				}
				if (StringUtils.isNotBlank(mutagenesis.getVariantSequence())) {
					mutagenesis.setVariantSeqLength(mutagenesis.getVariantSequence().length());
				}
				OrderMutagenesisDTO targetMutagenesisDTO = (OrderMutagenesisDTO) ModelUtils
				.mergerModel(mutagenesis, srcMutagenesis);
				if (mutagenesis.getTmplVector() != null
						&& mutagenesis.getTmplVector().contains("Other")
						&& !StringUtils.isEmpty(mutagenesis.getTmplVectorOther())) {
					targetMutagenesisDTO.setTmplVector(mutagenesis.getTmplVector()
							+ ";" + mutagenesis.getTmplVectorOther());
				} else {
					targetMutagenesisDTO.setTmplVectorSize(null);
					targetMutagenesisDTO.setTmplResistance(null);
					targetMutagenesisDTO.setTmplVectorSeq(null);
				}
				//Golf added 2011-10-21
				if (mutagenesis.getTmplResistance() != null && mutagenesis.getTmplResistance().contains("Other")
						&& !StringUtils.isEmpty(mutagenesis.getTmplResistanceOther())) {
					targetMutagenesisDTO.setTmplResistance(mutagenesis.getTmplResistance()+";"+mutagenesis.getTmplResistanceOther());
				}
				//End
				if (StringUtils.isEmpty(quoteItem.getParentId())) {
					quoteItem.setNameShow(quoteItem.getName()+": "+targetMutagenesisDTO.getVariantName());
				} else {
					quoteItem.setNameShow(quoteItem.getName());
				}
				quoteItem.setMutagenesis(targetMutagenesisDTO);

				String pId = quoteItem.getParentId();
				if(StringUtils.isNotBlank(pId)){
					QuoteItemDTO geneItem = itemMap.get(pId);
					String catalogId = geneItem.getCatalogId();
					System.out.println(">>>>>>>>>>>>>>>>"  + catalogId);
					quoteItem.setCatalogId(catalogId);
				}
				//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
				boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
				if (!isChanged) {
					// 计算Mutagenesis Item的价格
					ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
					orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.mutagenesis.name());
					orderServiceItemPiceDTO.setMutagenesisDTO(targetMutagenesisDTO);
					returnOrderServiceItemPrice(orderServiceItemPiceDTO);
				}
                quoteItem.setUpdateFlag(Y);
				retMap.put(itemId, quoteItem);
				Struts2Util.renderJson(retMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将mutaLib信息保存到session中
	 * @author Zhang Yong
	 * add date 2011-11-22
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveMutaLib () throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
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
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		OrderMutationLibrariesDTO srcMutaLib = quoteItem.getMutationLibraries();
		if (srcMutaLib == null) {
			srcMutaLib = new OrderMutationLibrariesDTO();
		}
		if (!StringUtils.isEmpty(mutaLib.getReadingFrame())) {
			if ((Y).equals(mutaLib.getReadingFrame())) {
				mutaLib.setReadingFrame(mutaLib.getReadingFrame() + ":" + mutaLib.getGeneUsageText());
			}
		}
		OrderMutationLibrariesDTO targetMutaLibDTO = (OrderMutationLibrariesDTO) ModelUtils.mergerModel(mutaLib, srcMutaLib);
		if ((Y).equals(mutaLib.getTmplFlag())&& StringUtils.isNotBlank(mutaLib.getTmplVector()) && mutaLib.getTmplVector().contains("Other")
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
		quoteItem.setNameShow(quoteItem.getName() + ": " + targetMutaLibDTO.getConstructName());
		quoteItem.setMutationLibraries(targetMutaLibDTO);
		BigDecimal zero = new BigDecimal(0);
		quoteItem.setUnitPrice(zero);
		quoteItem.setAmount(zero);
		quoteItem.setCost(zero);
		quoteItem.setTbdFlag("1");
		quoteItem.setUpdateFlag(Y);
		retMap.put(itemId, quoteItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 检测当前Item的状态和parentItem的状态，如果为"CN"则返回
	 * @param retMap
	 * @return
	 */
	private Map<String, QuoteItemDTO> checkStatus (Map<String, QuoteItemDTO> retMap) {
		if (!StringUtils.isEmpty(parentId)) {
			QuoteItemDTO sessParentItem = (QuoteItemDTO)itemMap.get(parentId);
			if (sessParentItem != null) {
				if (QuoteItemStatusType.CN.value().equalsIgnoreCase(sessParentItem.getStatus())) {
					if (StringUtils.isEmpty(itemId) || quoteItem == null) {
						retMap.put(itemId, new QuoteItemDTO());
					} else {
                        quoteItem.setUpdateFlag(Y);
						retMap.put(itemId, quoteItem);
					}
					return retMap;
				}
			}
		}
		if (quoteItem != null && QuoteItemStatusType.CN.value().equalsIgnoreCase(quoteItem.getStatus())) {
            quoteItem.setUpdateFlag(Y);
			retMap.put(itemId, quoteItem);
		}
		return retMap;
	}

	@SuppressWarnings("unchecked")
	public String checkOtherPeptide() {
		try{
			Map<String, QuoteItemDTO> batchSessionMap = (Map<String, QuoteItemDTO>)SessionUtil.getRow(SessionConstant.OtherPeptideList.value(), sessQuoteNo);
			if(batchSessionMap != null && !batchSessionMap.isEmpty() && batchSessionMap.containsKey(itemId)){
				Struts2Util.renderText("otherPeptide");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return NONE;
	}

	public String checkRna() throws Exception {
		String code = Struts2Util.getParameter("code");
		String seq = Struts2Util.getParameter("seq");
		Marker marker = orderQuoteUtil.getRnaByCode(code);
		if(marker != null){
			String re5Seq = marker.getRe5Seq();
			String re3Seq = marker.getRe3Seq();
			String pattern1 = "^"+re5Seq+"(A|C|G|T)*";
			String pattern2 = "(A|C|G|T)*"+re3Seq+"$";
			Pattern pat1 = Pattern.compile(pattern1);
			Pattern pat2 = Pattern.compile(pattern2);
			Matcher mat1 = pat1.matcher(seq);
			Matcher mat2 = pat2.matcher(seq);
			boolean cb1 = mat1.matches();
			boolean cb2 = mat2.matches();
			System.out.println(cb1&&cb2);
			if(cb1&&cb2){
				Struts2Util.renderText("ok");
			}else{
				Struts2Util.renderText("The siRNA sequence should start with "+re5Seq+" and end with "+re3Seq+".");
			}
		}
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
		Map<String, QuoteItemDTO> retMap = new LinkedHashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (quoteItem == null) {
			Struts2Util
					.renderText("Fail to upload it, Please check the session");
			return null;
		}
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			OrderGeneSynthesisDTO geneSynthesis = quoteItem.getGeneSynthesis();
			List<Document> docList = geneSynthesis.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			geneSynthesis.setDocumentList(docList);
			quoteItem.setGeneSynthesis(geneSynthesis);
            quoteItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		}else if (serviceName.equalsIgnoreCase("rna")) {
			RnaDTO rna = quoteItem.getRna();
			List<Document> docList = rna.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			rna.setDocumentList(docList);
			quoteItem.setRna(rna);
            quoteItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			OrderCustCloningDTO custCloning = quoteItem.getCustCloning();
			List<Document> docList = custCloning.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			custCloning.setDocumentList(docList);
			quoteItem.setCustCloning(custCloning);
            quoteItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			OrderPlasmidPreparationDTO plasmidPreparation = quoteItem.getPlasmidPreparation();
			List<Document> docList = plasmidPreparation.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			plasmidPreparation.setDocumentList(docList);
			quoteItem.setPlasmidPreparation(plasmidPreparation);
            quoteItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			OrderMutagenesisDTO mutagenesis = quoteItem.getMutagenesis();
			List<Document> docList = mutagenesis.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			mutagenesis.setDocumentList(docList);
			quoteItem.setMutagenesis(mutagenesis);
            quoteItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("libraryPeptide")) {
			PeptideDTO libraryPeptide = quoteItem.getPeptide();
			if (libraryPeptide == null) {
				libraryPeptide = new PeptideDTO();
			}
			List<Document> docList = libraryPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			libraryPeptide.setDocumentList(docList);
			quoteItem.setPeptide(libraryPeptide);
			saveLibraryPeptide(retMap, docList);
            quoteItem.setUpdateFlag(Y);
			if(!retMap.isEmpty()){
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("updateLibraryPeptide")) {
			if(StringUtils.isBlank(quoteItem.getParentId())){
				List<Integer> delItemIdList = new ArrayList<Integer>();
				Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
				while (it.hasNext()) {
					Entry<String, QuoteItemDTO> entry = it.next();
					QuoteItemDTO quoteItemDTO = entry.getValue();
					String tmpItemId = entry.getKey();
					if(quoteItemDTO != null && itemId.equalsIgnoreCase(quoteItemDTO.getParentId())){

						if (StringUtils.isNumeric(tmpItemId)) {
							delItemIdList.add(quoteItemDTO.getQuoteItemId());
							it.remove();
							itemMap.remove(tmpItemId);
						}else{
							it.remove();
							itemMap.remove(tmpItemId);
							//continue;
						}
					}
				}
				quoteService.delQuoteItemListForPeptide(delItemIdList);
			}
			PeptideDTO libraryPeptide = quoteItem.getPeptide();
			if (libraryPeptide == null) {
				libraryPeptide = new PeptideDTO();
			}
			List<Document> docList = libraryPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
            quoteItem.setUpdateFlag(Y);
			uploadQuoteServiceFile(docList, newDocList);
			libraryPeptide.setDocumentList(docList);
			//itemId = quoteItem.getParentId();
			//quoteItem = itemMap.get(itemId);
			saveLibraryPeptide(retMap, docList);
			if(!retMap.isEmpty()){
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("arrayPeptide")) {
			PeptideDTO arrayPeptide = quoteItem.getPeptide();
			if (arrayPeptide == null) {
				arrayPeptide = new PeptideDTO();
			}
			List<Document> docList = arrayPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			arrayPeptide.setDocumentList(docList);
			quoteItem.setPeptide(arrayPeptide);
            quoteItem.setUpdateFlag(Y);
			saveArrayPeptide(retMap, docList);
			if(!retMap.isEmpty()){
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("orfClone")) {
			OrderOrfCloneDTO orfClone = quoteItem.getOrfClone();
			if (orfClone == null) {
				orfClone = new OrderOrfCloneDTO();
			}
			List<Document> docList = orfClone.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			orfClone.setDocumentList(docList);
			if (docList != null && !docList.isEmpty()) {
				orfClone.setServiceDocFlag(Y);
			} else {
				orfClone.setServiceDocFlag(N);
			}
			quoteItem.setOrfClone(orfClone);
            quoteItem.setUpdateFlag(Y);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		} else if (serviceName.equalsIgnoreCase("updateArrayPeptide")) {
			if(StringUtils.isBlank(quoteItem.getParentId())){
				List<Integer> delItemIdList = new ArrayList<Integer>();
				Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
				while (it.hasNext()) {
					Entry<String, QuoteItemDTO> entry = it.next();
					QuoteItemDTO quoteItemDTO = entry.getValue(
							);
					String tmpItemId = entry.getKey();
					if(quoteItemDTO.getParentId() != null && itemId.equalsIgnoreCase(quoteItemDTO.getParentId())){

						if (StringUtils.isNumeric(tmpItemId)) {
							delItemIdList.add(quoteItemDTO.getQuoteItemId());
							it.remove();
							itemMap.remove(tmpItemId);
						}else{
							it.remove();
							itemMap.remove(tmpItemId);
							//continue;
						}
					}
				}
				orderService.delOrderItemListForPeptide(delItemIdList);
			}
			PeptideDTO arrayPeptide = quoteItem.getPeptide();
			if (arrayPeptide == null) {
				arrayPeptide = new PeptideDTO();
			}
			List<Document> docList = arrayPeptide.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			arrayPeptide.setDocumentList(docList);
			//itemId = quoteItem.getParentId();
			//quoteItem = itemMap.get(itemId);
            quoteItem.setUpdateFlag(Y);
			saveArrayPeptide(retMap, docList);

			if(!retMap.isEmpty()){
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("oligo")) {
			oligo = quoteItem.getOligo();
			if (oligo == null) {
				Struts2Util.renderText("Fail to batch oligo, Please check the session");
				return null;
			}
            quoteItem.setUpdateFlag(Y);
			String rtnMessage = saveBatchOligo(retMap);
			if (rtnMessage != null ) {
				Struts2Util.renderText(rtnMessage);
			}
			if(rtnMessage == null && !retMap.isEmpty()){
				Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
			}
		} else if (serviceName.equalsIgnoreCase("mutaLib")) {
			OrderMutationLibrariesDTO mutaLib = quoteItem.getMutationLibraries();
			if (mutaLib == null) {
				mutaLib = new OrderMutationLibrariesDTO();
			}
			List<Document> docList = mutaLib.getDocumentList();
			if (docList == null) {
				docList = new ArrayList<Document>();
			}
			List<Document> newDocList = new ArrayList<Document>();
			uploadQuoteServiceFile(docList, newDocList);
			mutaLib.setDocumentList(docList);
			if (docList != null && !docList.isEmpty()) {
				mutaLib.setServiceDocFlag(Y);
			} else {
				mutaLib.setServiceDocFlag(N);
			}
			quoteItem.setUpdateFlag(Y);
			quoteItem.setMutationLibraries(mutaLib);
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(newDocList));
		}
		return null;
	}
	
	/**
	 * 更新session中ORF Clone服务的GenePrice、SubcloningPrice，前台显示用
	 * @author Zhang Yong
	 * add date 2011-12-06
	 * @param topClsId
	 * @param parentQuoteItemDTO
	 */
	private void uptSessOrfServicePrice (Integer topClsId, QuoteItemDTO parentQuoteItemDTO) {
		if (topClsId.intValue() == 6) {
			if (quoteItem.getClsId().intValue() == 6) {
				for (String key : itemMap.keySet()) {
					QuoteItemDTO qt = itemMap.get(key);
					if (CatalogType.SERVICE.value().equals(qt.getType()) && itemId.equals(qt.getParentId()) && qt.getClsId().intValue() == 9) {
						quoteItem.getOrfClone().setSubcloningPrice(qt.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						break;
					}
				}
				quoteItem.getOrfClone().setGenePrice(quoteItem.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				itemMap.put(itemId, quoteItem);
				quoteServiceDTO.setOrfClone(quoteItem.getOrfClone());
			} else if (quoteItem.getClsId().intValue() == 9) {
				parentQuoteItemDTO.getOrfClone().setGenePrice(parentQuoteItemDTO.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				parentQuoteItemDTO.getOrfClone().setSubcloningPrice(quoteItem.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				itemMap.put(quoteItem.getParentId(), parentQuoteItemDTO);
				quoteServiceDTO.setOrfClone(parentQuoteItemDTO.getOrfClone());
			} else if (quoteItem.getClsId().intValue() == 10) {
				QuoteItemDTO subCloning = null;
				for (String key : itemMap.keySet()) {
					QuoteItemDTO qt = itemMap.get(key);
					if (CatalogType.SERVICE.value().equals(qt.getType()) && quoteItem.getParentId().equals(qt.getParentId()) && qt.getClsId().intValue() == 9) {
						subCloning = qt;
						break;
					}
				}
				parentQuoteItemDTO.getOrfClone().setGenePrice(parentQuoteItemDTO.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if (subCloning != null && subCloning.getUnitPrice() != null) {
					parentQuoteItemDTO.getOrfClone().setSubcloningPrice(subCloning.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				itemMap.put(quoteItem.getParentId(), parentQuoteItemDTO);
				quoteServiceDTO.setOrfClone(parentQuoteItemDTO.getOrfClone());
			}
			vectorList = quoteOrderService.getVectorList();
			orfCloneSiteList = quoteOrderService.getOrfCloneSite(vectorList, quoteServiceDTO);
		}
	}

	@SuppressWarnings("unchecked")
	public String returnItemMap() throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (StringUtils.isNotBlank(quoteItem.getParentId())) {
			Struts2Util
					.renderText("Fail to upload it, Please check the item");
			return null;
		}
		if(StringUtils.isBlank(quoteItem.getParentId())){
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
			.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				String tmpItemId = entry.getKey();
				if(quoteItemDTO != null && itemId.equalsIgnoreCase(quoteItemDTO.getParentId())){
					retMap.put(tmpItemId, quoteItemDTO);
				}
			}
		}
		if(!retMap.isEmpty()){
			Struts2Util.renderHtml(Struts2Util.conventJavaObjToJson(retMap));
		}
		return null;
	}

	/**
	 * @param str
	 * @return
	 */
	private int checkSeqlength(String str) {
		Pattern pattern = Pattern.compile("\\{.+\\}");
		Matcher matcher = pattern.matcher(str);
		int seqLength = matcher.replaceAll("a")
				.length();
		return seqLength;
	}

	private String generateSequenceSourceName(String refType, String refId) {
		String srcFileName = "sequence.txt";
		if(!StringUtils.isNumeric(itemId)){
			return srcFileName;
		}else{
			StringBuilder sb = new StringBuilder();
			int rId = Integer.parseInt(refId);
			int count = orderService.getDocument(refType, rId).size();
			return sb.append(count+1).append("_").append("sequence").append(".txt").toString();
		}
	}
	private String exportFile(final String sequenceContent, final String sourceFileName) throws Exception {
		FileWriter writer = null;
		File file = null;
		String fileName = generateSequenceTargetName(sourceFileName);
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(fileService.getUploadPath()).append(FilePathConstant.quoteService.value()).append("/");
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

	private String generateSequenceTargetName(String srcFileName) throws Exception {
		StringBuilder sb = new StringBuilder();
		return sb.append(SessionUtil.generateTempId()).append("_").append(srcFileName)
				.append(".").append("txt").toString();
	}


	/**
	 * @param docList
	 * @param sb
	 * @throws Exception
	 */
	private void createSeqFileForCopy(List<Document> docList, StringBuilder sb)
			throws Exception {
		if(sb.length() > 0){
			String srcName = generateSequenceSourceName(refType, itemId);
			String targetName = exportFile(sb.substring(0, sb.length()-1), srcName);
			Document doc = new Document();
			doc.setDocName(srcName);
			doc.setRefType(refType);
			doc.setFilePath(targetName);
			docList.add(doc);
		}
	}

	/**
	 * @param retMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 * @throws IOException
	 */
	private void saveArrayPeptide(Map<String, QuoteItemDTO> retMap, List<Document> docList)
	throws IllegalAccessException, InvocationTargetException,
	Exception, IOException {
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		String peptideName = peptide.getName();
		if (StringUtils.isEmpty(peptideName)) {
			peptideName = "Peptide";
		}
		String sequence = peptide.getSequence();
		if (StringUtils.isNotEmpty(sequence)) {
			int peptideCount = 0;
			String[] seqArray = sequence.split("\r");
			StringBuilder sb = new StringBuilder();
			if (seqArray.length >= 48) {
				int mapSize = itemMap.size();
				for (String str : seqArray) {
					int seqLength = checkSeqlength(str);
					if (seqLength >= 5 && seqLength <= 15) {
						peptideCount++;
						newMultipleSessionSequence(retMap, peptideName, peptideCount, seqArray.length+1, str, "SC1507", mapSize);
						mapSize++;
						sb.append(str).append("\r");
					}
					 else {
							Struts2Util
									.renderText("The peptide sequence length must be between 5 and 15.");
							return;
						}
				}
				getPeptideForArrAndLibPrice(retMap, peptideCount, "SC1507");
				createSeqFileForCopy(docList, sb);
			}else {
				Struts2Util
				.renderHtml("The minimal peptides number is 48.");
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessQuoteNo, retMap);
		} else {
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
							int mapSize = itemMap.size();
							for (List<String> strList : excelList) {
								String seq = strList.get(0);
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 15) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount, excelList.size()+1, seq, "SC1507", mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount, "SC1507");
						}else{
							Struts2Util
							.renderText("The minimal peptides number is 48.");
						}
					}
						// //The upload file type is TXT or CSV type
					} else if (fileNameSuffix.equalsIgnoreCase("txt")
							|| fileNameSuffix.equalsIgnoreCase("csv")) {
						List<String> strList = FilelUtil.getLineList(upload
								.get(i));
						if (strList != null && !strList.isEmpty()) {
							int peptideCount = 0;
							if (strList.size() >= 48) {
								int mapSize = itemMap.size();
								for (String str : strList) {
									String seq = str;
									int seqLength = checkSeqlength(seq);
									if (seqLength >= 5 && seqLength <= 15) {
										peptideCount++;
										newMultipleSessionSequence(retMap,
												peptideName, peptideCount, strList.size()+1, seq, "SC1507", mapSize);
										mapSize++;
									} else {
										Struts2Util
												.renderText("The peptide sequence length must be between 5 and 20.");
										return;
									}
								}
								getPeptideForArrAndLibPrice(retMap, peptideCount, "SC1507");
							}else{
								Struts2Util
								.renderText("The minimal peptides number is 48.");
							}
						}
					} else {

					}
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessQuoteNo, retMap);
		}
	}


	/**
	 * @param retMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 * @throws IOException
	 */
	private void saveLibraryPeptide(Map<String, QuoteItemDTO> retMap, List<Document> docList)
	throws IllegalAccessException, InvocationTargetException,
	Exception, IOException {
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		String catalogNo = itemMap.get(itemId).getCatalogNo();
		String peptideName = peptide.getName();
		String sequence = peptide.getSequence();
		if (StringUtils.isNotEmpty(sequence)) {
			String[] seqArray = sequence.split("\r");
			StringBuilder sb = new StringBuilder();
			int peptideCount = 0;
			if (catalogNo.equals("SC1177")
					&& seqArray.length >= 24) {
				int mapSize = itemMap.size();
				for (String str : seqArray) {
					int seqLength = checkSeqlength(str);
					if (seqLength >= 5 && seqLength <= 20) {
						sb.append(str).append("\r");
						peptideCount++;
						newMultipleSessionSequence(retMap, peptideName, peptideCount, seqArray.length+1, str, catalogNo, mapSize);
						mapSize++;
					}else {
						Struts2Util
						.renderHtml("The peptide sequence length must be between 5 and 20.");
						return;
					}
				}
				getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
				createSeqFileForCopy(docList, sb);
			}else if (catalogNo.equals("SC1487")
					&& seqArray.length >= 24) {
				int mapSize = itemMap.size();
				for (String str : seqArray) {
					int seqLength = checkSeqlength(str);
					if (seqLength >= 5 && seqLength <= 18) {
						sb.append(str).append("\r");
						peptideCount++;
						newMultipleSessionSequence(retMap, peptideName, peptideCount, seqArray.length+1, str, catalogNo, mapSize);
						mapSize++;
					}else {
						Struts2Util
						.renderHtml("The peptide sequence length must be between 5 and 18.");
						return;
					}
				}
				getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
				createSeqFileForCopy(docList, sb);
			}else{
				Struts2Util
				.renderText("The minimal peptides number is 24.");
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(),
					sessQuoteNo, retMap);
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
											peptideName, peptideCount, excelList.size()+1, seq, catalogNo, mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
						} else if (catalogNo.equals("SC1487")
								&& excelList.size() >= 24) {
							for (List<String> strList : excelList) {
								String seq = strList.get(0);
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 18) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount, excelList.size()+1, seq, catalogNo, mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 18.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
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
											peptideName, peptideCount, strList.size()+1, seq, catalogNo, mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 20.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
						} else if (catalogNo.equals("SC1487")
								&& strList.size() >= 24) {
							for (String str : strList) {
								String seq = str;
								int seqLength = checkSeqlength(seq);
								if (seqLength >= 5 && seqLength <= 18) {
									peptideCount++;
									newMultipleSessionSequence(retMap,
											peptideName, peptideCount, strList.size()+1, seq, catalogNo, mapSize);
									mapSize++;
								} else {
									Struts2Util
											.renderText("The peptide sequence length must be between 5 and 18.");
									return;
								}
							}
							getPeptideForArrAndLibPrice(retMap, peptideCount, catalogNo);
						} else {
							Struts2Util
									.renderText("The minimal peptides number is 24.");
						}
					}
				} else {

				}
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(), sessQuoteNo, retMap);
		}
	}

	private void getPeptideForArrAndLibPrice(Map<String, QuoteItemDTO> retMap,
			int peptideCount, String catalogNo) throws IllegalAccessException,
			InvocationTargetException {
		PeptideDTO peptideDTO = new PeptideDTO();
		peptideDTO.setPeptideCount(peptideCount);
		ModelUtils.mergerModel(peptide, peptideDTO);
		ServiceItemPiceDTO quoteServiceItemPiceDTO = getCostPrice(quoteItem);
		quoteServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
		quoteServiceItemPiceDTO.setPeptideDTO(peptideDTO);
		quoteServiceItemPiceDTO.setCatalogNo(catalogNo);
        quoteServiceItemPiceDTO = publicService.calculateServicePrice(quoteServiceItemPiceDTO);
		for (Iterator<String> iter = retMap.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			QuoteItemDTO quoteItemDTO = (QuoteItemDTO) retMap.get(key);
			String itemName = quoteItem.getName();
			int i = itemName.lastIndexOf("-");
			itemName = itemName.substring(0, i+1)+catalogNo;
			quoteItemDTO.setName(itemName);
//			quoteItemService.returnQuoteServiceItemPrice(sessQuoteNo, quoteItemDTO, quoteServiceItemPiceDTO);
			quoteItemService.updateQuoteItemPrice(quoteItemDTO, quoteServiceItemPiceDTO, sessQuoteNo);
		}
	}

	/**
	 * @param retMap
	 * @param peptideName
	 * @param seq
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	private void newMultipleSessionSequence(Map<String, QuoteItemDTO> retMap,
			String peptideName, int pepCount, int allCount, String seq, String catalogNo, int mapSize) throws IllegalAccessException,
			InvocationTargetException, Exception {
		PeptideDTO peptideDTO = new PeptideDTO();
		ModelUtils.mergerModel(peptide, peptideDTO);
		peptideDTO.setSequence(seq);
		if(StringUtils.isNotBlank(peptideName)){
			//peptideDTO.setName(peptideName+"-"+(allCount-pepCount));
			peptideDTO.setName(peptideName+"-"+pepCount);
		}else{
			//peptideDTO.setName((allCount-pepCount)+"");
			peptideDTO.setName("Peptide"+"-"+pepCount);
		}
		peptideDTO.setSeqLength(StringUtil.calculateSeqLength(peptideDTO.getSequence()));
		QuoteItemDTO quoteItemDTO = new QuoteItemDTO();
		ModelUtils.mergerModel(quoteItem, quoteItemDTO);
		quoteItemDTO.setItemNo(mapSize + 1);
		quoteItemDTO.setQuoteItemId(null);
		quoteItemDTO.setPeptide(peptideDTO);
		quoteItemDTO.setCatalogNo(catalogNo);
		quoteItemDTO.setParentId(itemId);
		quoteItemDTO.setNameShow(quoteItem.getName());
        quoteItemDTO.setUpdateFlag(Y);
		String tmpId = SessionUtil.generateTempId();
		retMap.put(tmpId, quoteItemDTO);
		itemMap.put(tmpId, quoteItemDTO);
	}


	@SuppressWarnings("unchecked")
	public String deleteFile(){
		try{
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			if(quoteItem == null){
				Struts2Util.renderText("Fail to upload it, Please check the session");
				return null;
			}
			if(serviceName.equalsIgnoreCase("geneSynthesis")){
				OrderGeneSynthesisDTO geneSynthesis = quoteItem.getGeneSynthesis();
				List<Document> docList = geneSynthesis.getDocumentList();
				List<Integer> delDocIdList = geneSynthesis.getDelDocIdList();
				Document delDoc = null;
				for (Document document : docList) {
					try {
						String urlEncoding = StringUtil.getURLEncoding(delFilePath);
						delFilePath = new String(delFilePath.getBytes("iso-8859-1"), urlEncoding);
					} catch (Exception ex) {
					}
					if(document.getFilePath().equals(this.delFilePath)){
						delDoc = document;
						docList.remove(delDoc);
						break;
					}
				}
				if(delDoc == null){
					Struts2Util.renderText("Fail to get the document");
					return null;
				}
				if(delDoc.getDocId() != null){
					if(delDocIdList == null){
						delDocIdList = new ArrayList<Integer>();
					}
					delDocIdList.add(delDoc.getDocId());
					geneSynthesis.setDelDocIdList(delDocIdList);
				}
				quoteItem.setGeneSynthesis(geneSynthesis);
	            quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			}else if(serviceName.equalsIgnoreCase("rna")){
				RnaDTO rna = quoteItem.getRna();
				List<Document> docList = rna.getDocumentList();
				List<Integer> delDocIdList = rna.getDelDocIdList();
				Document delDoc = null;
				for (Document document : docList) {
					try {
						String urlEncoding = StringUtil.getURLEncoding(delFilePath);
						delFilePath = new String(delFilePath.getBytes("iso-8859-1"), urlEncoding);
					} catch (Exception ex) {
					}
					if(document.getFilePath().equals(this.delFilePath)){
						delDoc = document;
						docList.remove(delDoc);
						break;
					}
				}
				if(delDoc == null){
					Struts2Util.renderText("Fail to get the document");
					return null;
				}
				if(delDoc.getDocId() != null){
					if(delDocIdList == null){
						delDocIdList = new ArrayList<Integer>();
					}
					delDocIdList.add(delDoc.getDocId());
					rna.setDelDocIdList(delDocIdList);
				}
				quoteItem.setRna(rna);
	            quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			}else if(serviceName.equalsIgnoreCase("custCloning")){
				OrderCustCloningDTO custCloning = quoteItem.getCustCloning();
				List<Document> docList = custCloning.getDocumentList();
				List<Integer> delDocIdList = custCloning.getDelDocIdList();
				Document delDoc = null;
				for (Document document : docList) {
	
					if(document.getFilePath().equals(this.delFilePath)){
						delDoc = document;
						docList.remove(delDoc);
						break;
					}
				}
				if(delDoc == null){
					Struts2Util.renderText("Fail to get the document");
					return null;
				}
				if(delDoc.getDocId() != null){
					if(delDocIdList == null){
						delDocIdList = new ArrayList<Integer>();
					}
					delDocIdList.add(delDoc.getDocId());
					custCloning.setDelDocIdList(delDocIdList);
				}
				quoteItem.setCustCloning(custCloning);
	            quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			}else if(serviceName.equalsIgnoreCase("plasmidPreparation")){
				OrderPlasmidPreparationDTO plasmidPreparation = quoteItem.getPlasmidPreparation();
				List<Document> docList = plasmidPreparation.getDocumentList();
				List<Integer> delDocIdList = plasmidPreparation.getDelDocIdList();
				Document delDoc = null;
				for (Document document : docList) {
	
					if(document.getFilePath().equals(this.delFilePath)){
						delDoc = document;
						docList.remove(delDoc);
						break;
					}
				}
				if(delDoc == null){
					Struts2Util.renderText("Fail to get the document");
					return null;
				}
				if(delDoc.getDocId() != null){
					if(delDocIdList == null){
						delDocIdList = new ArrayList<Integer>();
					}
					delDocIdList.add(delDoc.getDocId());
					plasmidPreparation.setDelDocIdList(delDocIdList);
				}
				quoteItem.setPlasmidPreparation(plasmidPreparation);
	            quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			}else if(serviceName.equalsIgnoreCase("mutagenesis")){
				OrderMutagenesisDTO mutagenesis = quoteItem.getMutagenesis();
				List<Document> docList = mutagenesis.getDocumentList();
				List<Integer> delDocIdList = mutagenesis.getDelDocIdList();
				Document delDoc = null;
				for (Document document : docList) {
	
					if(document.getFilePath().equals(this.delFilePath)){
						delDoc = document;
						docList.remove(delDoc);
						break;
					}
				}
				if(delDoc == null){
					Struts2Util.renderText("Fail to get the document");
					return null;
				}
				if(delDoc.getDocId() != null){
					if(delDocIdList == null){
						delDocIdList = new ArrayList<Integer>();
					}
					delDocIdList.add(delDoc.getDocId());
					mutagenesis.setDelDocIdList(delDocIdList);
				}
				quoteItem.setMutagenesis(mutagenesis);
	            quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			} else if (serviceName.equalsIgnoreCase("orfClone")) {
				OrderOrfCloneDTO orfClone = quoteItem.getOrfClone();
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
				quoteItem.setOrfClone(orfClone);
	            quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			} else if (serviceName.equalsIgnoreCase("mutaLib")) {
				OrderMutationLibrariesDTO mutaLib = quoteItem.getMutationLibraries();
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
				quoteItem.setMutationLibraries(mutaLib);
				quoteItem.setUpdateFlag(Y);
				Struts2Util.renderText("SUCCESS");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在session中增加新的item
	 *
	 * @param itemMap
	 * @param afterItemId
	 * @param newItemId
	 * @param newItem
	 */
	private void insertNewChild(Map<String, QuoteItemDTO> itemMap,
			String afterItemId, String newItemId, QuoteItemDTO newItem) {
		if (StringUtils.isEmpty(afterItemId)) {
			Integer itemNo = itemMap.size() + 1;
			newItem.setItemNo(itemNo);
			itemMap.put(newItemId, newItem);
		} else {
			Map<String, QuoteItemDTO> newItemMap = new LinkedHashMap<String, QuoteItemDTO>();
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			QuoteItemDTO afterItem = itemMap.get(afterItemId);
			Integer itemNo = afterItem.getItemNo();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				String tmpKey = entry.getKey();
				QuoteItemDTO tmpItemDTO = entry.getValue();
				Integer tmpItemNo = tmpItemDTO.getItemNo();
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
			SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, newItemMap);
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
	private QuoteItemDTO getNewChildSerItem(String parentId,
			String serviceName, String catalogNo) {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		Integer custNo = quote.getCustNo();
		QuoteItemDTO parentItem = itemMap.get(parentId);
		String intmdKeyword = MoreDetailUtil.getIntmdKeyword(serviceName);
		String parentCatalogNo = null;
		try {
			parentCatalogNo = servService.getIntermediaService(parentItem
				.getCatalogNo(), intmdKeyword);
		} catch (Exception ex) {
			throw new BussinessException(BussinessException.SUB_ITEM_IS_NULL);
		}
		QuoteItemDTO itemInfo = this.getBaseInfoByCatalogNo(parentCatalogNo,
				custNo);
		itemInfo.setParentId(parentId);
		QuoteItemDTO preItem = getLastItem(parentId);
		itemInfo = quoteItemService.getServiceItemOtherInfo(serviceName, itemInfo,parentItem,preItem);
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
	 * 获取某父类下面的最后一个子类
	 */
	private QuoteItemDTO getLastItem(String parentId) {
		Map<String, QuoteItemDTO> groupMap = getGroupItemMap(parentId);
		Iterator<Entry<String, QuoteItemDTO>> it = groupMap.entrySet().iterator();
		QuoteItemDTO lastItem = null;
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			QuoteItemDTO tmpItemDTO = entry.getValue();
			String tempParentId = tmpItemDTO.getParentId();
			if (tmpKey.equals(parentId)) {
				lastItem = tmpItemDTO;
				lastItem.setSessionItemId(tmpKey);
			} else if(parentId.equals(tempParentId)) {
				lastItem = tmpItemDTO;
				lastItem.setSessionItemId(tmpKey);
			}
		}
        lastItem.setUpdateFlag(Y);
		return lastItem;
	}

	private QuoteItemDTO getBaseInfoByCatalogNo(String catalogNo, Integer custNo) {
		try{

		SearchItemDTO searchItemDTO = servService.getSearchItemInfo(catalogNo, custNo);

		QuoteItemDTO quoteItemDTO = new QuoteItemDTO();
		quoteItemDTO.setName(searchItemDTO.getName());
		quoteItemDTO.setCatalogNo(searchItemDTO.getCatalogNo());
		quoteItemDTO.setClsId(searchItemDTO.getClsId());
		if(searchItemDTO.getCost() == null){
			quoteItemDTO.setCost(new BigDecimal(0.0));
		}else{
			quoteItemDTO.setCost(new BigDecimal(searchItemDTO.getCost()));
		}
		quoteItemDTO.setType("SERVICE");
		quoteItemDTO.setTypeText("SERVICE"+"-" + searchItemDTO.getClsName());
		quoteItemDTO.setShortDesc(searchItemDTO.getDescription());
		quoteItemDTO.setLongDesc(searchItemDTO.getFullDesc());
		quoteItemDTO.setTaxStatus(searchItemDTO.getTaxStatus());
		quoteItemDTO.setShipSchedule(searchItemDTO.getScheduleShip());
		quoteItemDTO.setSellingNote(searchItemDTO.getSellingNote());
		quoteItemDTO.setStorageId(searchItemDTO.getPrefStorage());
		quoteItemDTO.setSizeUom(searchItemDTO.getUom());
		quoteItemDTO.setSize(searchItemDTO.getSize());
		quoteItemDTO.setQtyUom(searchItemDTO.getQtyUom());
		//quoteItemDTO.setCatalogId(searchItemDTO.getCatalogId());
		quoteItemDTO.setQuantity(1);//hard code
		quoteItemDTO.setAmount(new BigDecimal(0.0));//hard code
		quoteItemDTO.setDiscount(new BigDecimal(0.0));//hard code
		quoteItemDTO.setTax(new BigDecimal(0.0));//hard code
		quoteItemDTO.setUnitPrice(new BigDecimal(0.0));//hard code
		quoteItemDTO.setBasePrice(BigDecimal.valueOf(0.0));
		quoteItemDTO.setCost(new BigDecimal(0.0));
            quoteItemDTO.setUpdateFlag(Y);
		return quoteItemDTO;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private ServiceItemPiceDTO getCostPrice(QuoteItemDTO itemDTO){
		ServiceItemPiceDTO quoteServiceItemPiceDTO = new ServiceItemPiceDTO();
		if (StringUtils.isNotBlank(quoteItem.getParentId())) {
			QuoteItemDTO baseItemDTO = itemMap.get(quoteItem.getParentId());
			quoteServiceItemPiceDTO.setParentClsId(baseItemDTO.getClsId());
		}
		quoteServiceItemPiceDTO.setServiceClsId(quoteItem.getClsId());
		quoteServiceItemPiceDTO.setCatalogId(quoteItem.getCatalogId());
		quoteServiceItemPiceDTO.setCatalogNo(quoteItem.getCatalogNo());
		quoteServiceItemPiceDTO.setQuantity(quoteItem.getQuantity());
		return quoteServiceItemPiceDTO;
	}

	@SuppressWarnings("unused")
	private boolean isOnlyParent(Integer clsId) {
		String show = MoreDetailUtil.getShow(clsId);
		if (show.equals("muta") || show.equals("gene")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isChildItemExist(Map<String, QuoteItemDTO> itemMap, String itemId) {
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String parentId = quoteItemDTO.getParentId();
			if (!StringUtils.isEmpty(parentId)
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

	private Map<String, QuoteItemDTO> getGroupItemMap(String itemId) {
		Map<String, QuoteItemDTO> newItemMap = new LinkedHashMap<String, QuoteItemDTO>();
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		QuoteItemDTO baseItemDTO = itemMap.get(itemId);
		String parentId = baseItemDTO.getParentId();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			String tmpParentId = quoteItemDTO.getParentId();
            quoteItemDTO.setUpdateFlag(Y);
			if (tmpId.equals(itemId)) {
				newItemMap.put(tmpId, quoteItemDTO);
			}
			if (!StringUtils.isEmpty(parentId)
					&& !parentId.equalsIgnoreCase("0")
					&& (parentId.equals(tmpParentId) || parentId.equals(tmpId))) {
				newItemMap.put(tmpId, quoteItemDTO);
			} else if (!StringUtils.isEmpty(tmpParentId)
					&& !tmpParentId.equalsIgnoreCase("0")
					&& itemId.equals(tmpParentId)) {
				newItemMap.put(tmpId, quoteItemDTO);
			}
		}
		return newItemMap;
	}




	private void setServicesByItemMap(Map<String, QuoteItemDTO> itemMap) {
		quoteServiceDTO = new OrderServiceDTO();
		if (quoteItem.getGeneSynthesis() != null) {
			quoteServiceDTO.setGeneSynthesis(quoteItem.getGeneSynthesis());
			quoteServiceDTO.setGeneSynthesisItemId(itemId);
		}
		if (quoteItem.getRna() != null) {
			quoteServiceDTO.setRna(quoteItem.getRna());
			quoteServiceDTO.setRnaItemId(itemId);
		}
		if (quoteItem.getCustCloning() != null) {
			quoteServiceDTO.setCustCloning(quoteItem.getCustCloning());
			quoteServiceDTO.setCustCloningItemId(itemId);
			quoteServiceDTO.getCustCloning().setTbdFlag(quoteItem.getTbdFlag());
		}
		if (quoteItem.getPlasmidPreparation() != null) {
			quoteServiceDTO.setPlasmidPreparation(quoteItem
					.getPlasmidPreparation());
			quoteServiceDTO.setPlasmidPreparationItemId(itemId);

			plasmidPreparation = quoteServiceDTO.getPlasmidPreparation();
			if(plasmidPreparation==null) {
				plasmidPreparation = new OrderPlasmidPreparationDTO();
			}
//			prepWeight = String.valueOf(plasmidPreparation.getPrepWeight());
//			boolean flg = true;
//			if(prepWeight!=null) {
//				flg = false;
//				List<PbDropdownListOptions> list = dropDownMap.get("PLASMID_WEIGHT");
//				for(PbDropdownListOptions option:list) {
//					if(option.getValue().equals(prepWeight)) {
//						flg = true;
//						break;
//					}
//				}
//			}
//			if(!flg) {
//				prepWeight = "0";
//			}
			if(plasmidPreparation.getPrepWeight()!=null) {
				Double preWeight = plasmidPreparation.getPrepWeight().doubleValue();
				if (preWeight.doubleValue() == preWeight.intValue()) {
					plasmidPreparation.setPrepWeightStr(String
							.valueOf(preWeight.intValue()+" "+plasmidPreparation.getPrepWtUom()));
				} else {
					plasmidPreparation.setPrepWeightStr(String
							.valueOf(preWeight.doubleValue()+" "+plasmidPreparation.getPrepWtUom()));
				}
			}
			antibioticResistance = plasmidPreparation.getAntibioticResistance();
			String restrictionAnalysis = plasmidPreparation.getRestrictionAnalysis();
			if(restrictionAnalysis!=null) {
				String[] restrictionAnalysisArray = restrictionAnalysis.split(",");
				List<PbDropdownListOptions> list = dropDownMap.get("PLASMID_RESTRICTION_ANALYSIS");
				List<PbDropdownListOptions> delList = new ArrayList<PbDropdownListOptions>();
				for(PbDropdownListOptions option:list) {
					for(String value:restrictionAnalysisArray) {
						if(option.getValue().equals(value)) {
							delList.add(option);
							break;
						}
					}
				}
				list.removeAll(delList);
				dropDownMap.put("PLASMID_RESTRICTION_ANALYSIS", list);
			}
			String[] fragmentArray = plasmidPreparation.getFragment()!=null?plasmidPreparation.getFragment().split("::"):null;
			plasmidPreparation.setFragmentArray(fragmentArray);
			if(fragmentArray!=null) {
				fragmentSize = "e.g."+fragmentArray.length;
			}

		}
		if (quoteItem.getMutagenesis() != null) {
			quoteServiceDTO.setMutagenesis(quoteItem.getMutagenesis());
			quoteServiceDTO.setMutagenesisItemId(itemId);
			quoteServiceDTO.setParentId(quoteItem.getParentId());
		}
		if (quoteItem.getMutationLibraries() != null) {
			quoteServiceDTO.setMutationLibraries(quoteItem.getMutationLibraries());
			quoteServiceDTO.setMutationLibrariesItemId(itemId);
		}
		if (quoteItem.getOrfClone() != null) {
			quoteServiceDTO.setOrfClone(quoteItem.getOrfClone());
			quoteServiceDTO.setOrfCloneItemId(itemId);
		}
		if (quoteItem.getOligo() != null) {
			quoteServiceDTO.setOligo(quoteItem.getOligo());
			quoteServiceDTO.setOligoItemId(itemId);
		}
		if (quoteItem.getCustomService() != null) {
			quoteServiceDTO.setCustomService(quoteItem.getCustomService());
			quoteServiceDTO.setCustomServiceItemId(itemId);
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		Map<String, OrderMutagenesisDTO> mutagenesisMap = new HashMap<String, OrderMutagenesisDTO>();
		Map<String, PkgServiceDTO> quotePkgServiceMap = new HashMap<String, PkgServiceDTO>();
		Map<String, PeptideDTO> peptideMap = new LinkedHashMap<String, PeptideDTO>();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpId = entry.getKey();
			QuoteItemDTO itemDTO = entry.getValue();
			if (itemDTO.getGeneSynthesis() != null
					&& quoteServiceDTO.getGeneSynthesis() == null) {
				quoteServiceDTO.setGeneSynthesis(itemDTO.getGeneSynthesis());
				quoteServiceDTO.setGeneSynthesisItemId(tmpId);
			}
			if (itemDTO.getRna() != null
					&& quoteServiceDTO.getRna() == null) {
				quoteServiceDTO.setRna(itemDTO.getRna());
				quoteServiceDTO.setRnaItemId(tmpId);
			}
			if (itemDTO.getCustCloning() != null
					&& quoteServiceDTO.getCustCloning() == null) {
				quoteServiceDTO.setCustCloning(itemDTO.getCustCloning());
				quoteServiceDTO.setCustCloningItemId(tmpId);
				quoteServiceDTO.getCustCloning().setTbdFlag(itemDTO.getTbdFlag());
			}
			if (itemDTO.getPlasmidPreparation() != null
					&& quoteServiceDTO.getPlasmidPreparation() == null) {
				quoteServiceDTO.setPlasmidPreparation(itemDTO
						.getPlasmidPreparation());
				quoteServiceDTO.setPlasmidPreparationItemId(tmpId);
				plasmidPreparation = quoteServiceDTO.getPlasmidPreparation();
				if(plasmidPreparation==null) {
					plasmidPreparation = new OrderPlasmidPreparationDTO();
				}
				if(plasmidPreparation.getPrepWeight()!=null) {
					Double preWeight = plasmidPreparation.getPrepWeight().doubleValue();
					if (preWeight.doubleValue() == preWeight.intValue()) {
						plasmidPreparation.setPrepWeightStr(String
								.valueOf(preWeight.intValue()+" "+plasmidPreparation.getPrepWtUom()));
					} else {
						plasmidPreparation.setPrepWeightStr(String
								.valueOf(preWeight.doubleValue()+" "+plasmidPreparation.getPrepWtUom()));
					}
				}
				antibioticResistance = plasmidPreparation.getAntibioticResistance();
				String restrictionAnalysis = plasmidPreparation.getRestrictionAnalysis();
				if(restrictionAnalysis!=null) {
					String[] restrictionAnalysisArray = restrictionAnalysis.split(",");
					List<PbDropdownListOptions> list = dropDownMap.get("PLASMID_RESTRICTION_ANALYSIS");
					List<PbDropdownListOptions> delList = new ArrayList<PbDropdownListOptions>();
					for(PbDropdownListOptions option:list) {
						for(String value:restrictionAnalysisArray) {
							if(option.getValue().equals(value)) {
								delList.add(option);
								break;
							}
						}
					}
					list.removeAll(delList);
					dropDownMap.put("PLASMID_RESTRICTION_ANALYSIS", list);
				}
				String[] fragmentArray = plasmidPreparation.getFragment()!=null?plasmidPreparation.getFragment().split("::"):null;
				plasmidPreparation.setFragmentArray(fragmentArray);
				if(fragmentArray!=null) {
					fragmentSize = "e.g."+fragmentArray.length;
				}

			}
			if (itemDTO.getOrfClone() != null
					&& quoteServiceDTO.getOrfClone() == null) {
				quoteServiceDTO.setOrfClone(itemDTO.getOrfClone());
				quoteServiceDTO.setOrfCloneItemId(tmpId);
			}
			if (itemDTO.getOligo() != null) {
				quoteServiceDTO.setOligo(itemDTO.getOligo());
				quoteServiceDTO.setOligoItemId(tmpId);
			}
			if (itemDTO.getMutagenesis() != null) {
				if (quoteServiceDTO.getMutagenesis() == null) {
					quoteServiceDTO.setMutagenesis(itemDTO.getMutagenesis());
					quoteServiceDTO.setMutagenesisItemId(tmpId);
					quoteServiceDTO.setParentId(itemDTO.getParentId());
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
				quoteServiceDTO.setPeptide(itemDTO.getPeptide());
				quoteServiceDTO.setPeptideItemId(tmpId);
				PeptideDTO pep = itemDTO.getPeptide();
				pep.setStatus(itemDTO.getStatus());
				peptideMap.put(tmpId, pep);
			}
			if (itemDTO.getQuotePkgService() != null) {
				String pkgServiceName = itemDTO.getQuotePkgService().getName();
				if (StringUtils.isNotBlank(pkgServiceName)) {
					String reStr = pkgServiceName.replaceAll("'", "@@")
							.replaceAll("\"", "~~").replaceAll("\n", "##");
					itemDTO.getQuotePkgService().setName(reStr);
				}
				String pkgDescription = itemDTO.getQuotePkgService().getDescription();
				if (StringUtils.isNotBlank(pkgDescription)) {
					String reStr = pkgDescription.replaceAll("'", "@@")
							.replaceAll("\"", "~~").replaceAll("\n", "##");
					itemDTO.getQuotePkgService().setDescription(reStr);
				}
				quotePkgServiceMap.put(tmpId, itemDTO.getQuotePkgService());
				if (StringUtils.isBlank(itemDTO.getParentId()) && tmpId.equals(itemId)) {
					quoteServiceDTO.setPkgServiceId(itemId);
				}
			}
			if(itemDTO.getAntibody() != null){
				quoteServiceDTO.setAntibody(itemDTO.getAntibody());
				quoteServiceDTO.setAntibodyItemId(tmpId);
			}
			if (itemDTO.getMutationLibraries() != null) {
				quoteServiceDTO.setMutationLibraries(itemDTO.getMutationLibraries());
				quoteServiceDTO.setMutationLibrariesItemId(tmpId);
			}
		}
		quoteServiceDTO.setMutagenesisMap(mutagenesisMap);
		quoteServiceDTO.setPkgServiceMap(quotePkgServiceMap);
		quoteServiceDTO.setPeptideMap(peptideMap);
		this.peptideMap = peptideMap;
	}

	private void uploadQuoteServiceFile(List<Document> docList,
			List<Document> newDocList) throws Exception {
		if(upload != null && !upload.isEmpty()){
		for (int i = 0; i < upload.size(); i++) {
			String srcFileName = uploadFileName.get(i);
			Document doc = new Document();
			doc.setDocName(srcFileName);
			uploadFileName.set(i, SessionUtil.generateTempId()
					+ srcFileName.substring(srcFileName.lastIndexOf(".")));
			doc.setRefType(refType);
			doc.setFilePath(FilePathConstant.quoteService.value() + "/"
					+ uploadFileName.get(i));
			docList.add(doc);
			newDocList.add(doc);
		}
		fileService.uploadFile(upload, uploadContentType, uploadFileName,
				FilePathConstant.quoteService.value());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public String saveStdPeptide(){
		try{
			Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			if (!checkStatus(retMap).isEmpty()) {
				Struts2Util.renderJson(retMap);
				return NONE;
			}
			if (itemId==null||StringUtils.isEmpty(itemId)) {
				itemId = SessionUtil.generateTempId();
			}
			PeptideDTO srcQuotePeptide = null;
			if (quoteItem != null) {
				srcQuotePeptide = quoteItem.getPeptide();
			}
			if (srcQuotePeptide == null) {
				srcQuotePeptide = new PeptideDTO();
			}
			PeptideDTO targetPeptideDTO = (PeptideDTO) ModelUtils.mergerModel(peptide, srcQuotePeptide);
			if (StringUtils.isEmpty(targetPeptideDTO.getSequence()) && targetPeptideDTO.getSeqLength() != null) {
				StringBuffer seqBf = new StringBuffer();
				for (int i=0;i<targetPeptideDTO.getSeqLength().intValue();i++) {
					seqBf.append("A");
				}
				targetPeptideDTO.setSequence(seqBf.toString());
			}
			if(Y.equalsIgnoreCase(Struts2Util.getParameter("peptide.aminoChangeFlag")) && !StringUtils.isNumeric(itemId) && !targetPeptideDTO.isConvertFlag()){
				String seqOrigin = targetPeptideDTO.getSequence();
				StringBuilder seqSb = new StringBuilder();
				String[] seqArray = seqOrigin.split("");
				for(String s : seqArray){
					seqSb.append(biolibService.getPeptideCode(s));
				}
				targetPeptideDTO.setSequence(seqSb.toString());
				targetPeptideDTO.setConvertFlag(true);
			}
			String pepSequence = targetPeptideDTO.getSequence()==null?"":targetPeptideDTO.getSequence();
			targetPeptideDTO.setSeqLength(StringUtil.calculateSeqLength(pepSequence));
			quoteItem.setNameShow(quoteItem.getName()+": "+targetPeptideDTO.getName());
			quoteItem.setPeptide(targetPeptideDTO);
			
			//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
			boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
			if (!isChanged) {
				// 计算Peptide Item的价格
				ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
				orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
				orderServiceItemPiceDTO.setPeptideDTO(targetPeptideDTO);
				returnOrderServiceItemPrice(orderServiceItemPiceDTO);
			}
			SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
            quoteItem.setUpdateFlag(Y);
			retMap.put(itemId, quoteItem);
			Struts2Util.renderJson(retMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 保存TubeDNASequencing
	 * @author Zhang Yong
	 * add date 2011-11-14
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveTubeDNASequencing() throws Exception {
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		QuoteItemDTO srcDsItem = null;
		String srcDsItemKey = null;
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			if (40 == quoteItemDTO.getClsId().intValue()) {
				srcDsItem = quoteItemDTO;
				srcDsItemKey = entry.getKey();
				break;
			}
		}
		String currency = CurrencyType.USD.value();
		Double baseToRateDB = 1d;
		Double toBaseRateDB = 1d;
		QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if (quoteMain != null && StringUtils.isNotBlank(quoteMain.getQuoteCurrency())) {
			currency = quoteMain.getQuoteCurrency();
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
			QuoteItemDTO tubeItemDTO = null;
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
				tubeItemDTO = new QuoteItemDTO();
		        ModelUtils.mergerModel(srcDsItem, tubeItemDTO);
		        tubeItemDTO.setItemNo(itemMap.size() + 1);
		        tubeItemDTO.setQuoteItemId(null);
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
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		QuoteItemDTO srcDsItem = null;
		String srcDsItemKey = null;
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			if (40 == quoteItemDTO.getClsId().intValue()) {
				srcDsItem = quoteItemDTO;
				srcDsItemKey = entry.getKey();
				break;
			}
		}
		String currency = CurrencyType.USD.value();
		Double baseToRateDB = 1d;
		QuoteMainDTO quoteMain = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		if (quoteMain != null && StringUtils.isNotBlank(quoteMain.getQuoteCurrency())) {
			currency = quoteMain.getQuoteCurrency();
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
			QuoteItemDTO plItem = itemMap.get(key);
			if (plItem == null || plItem.getDnaSequencing() == null || StringUtils.isBlank(plItem.getDnaSequencing().getSessPlateId())) {
				continue;
			}
			sessPlateId = plItem.getDnaSequencing().getSessPlateId();
			break;
		}
		if (StringUtils.isBlank(sessPlateId)) {
			sessPlateId = SessionUtil.generateTempId();
		}
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
			QuoteItemDTO plateItemDTO = null;
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
				plateItemDTO = new QuoteItemDTO();
		        ModelUtils.mergerModel(srcDsItem, plateItemDTO);
		        plateItemDTO.setItemNo(mapSize + 1);
		        mapSize++;
		        plateItemDTO.setQuoteItemId(null);
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
			//计算价格结束
			plateItemDTO.setUpdateFlag(Y);
			itemMap.put(plate.getSessItemKey(), plateItemDTO);
		}
		Struts2Util.renderJson(map);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String saveBatchPeptide()throws Exception{
		//Map<String, QuoteItemDTO> retMap = new LinkedHashMap<String, QuoteItemDTO>();
		Map<String, QuoteItemDTO> retMap = new ConcurrentHashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
		}
		newSequenceByInput(retMap);
		SessionUtil.deleteRow(SessionConstant.QuotePackage.value(), sessQuoteNo);
		if(!retMap.isEmpty()){
			Struts2Util.renderJson(retMap);
		}
		return NONE;
	}
	
	/**
	 * 批量保存Oligo到session中
	 * @param retMap
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 * @throws IOException
	 */
	public String  saveBatchOligo(Map<String, QuoteItemDTO> retMap) 
		throws IllegalAccessException, InvocationTargetException,Exception, IOException  {
		String rtnMessage = null;
		String catalogNo = itemMap.get(itemId).getCatalogNo();
		if (StringUtils.isEmpty(catalogNo)) {
			rtnMessage = "Fail to batch oligo, Please check the session";
			return rtnMessage;
		}
		String batchBackbone = Struts2Util.getParameter("batchBackbone");
		if (StringUtils.isEmpty(batchBackbone)) {
			rtnMessage = "Fail to batch oligo, Please select a Backbone.";
			return rtnMessage;
		}
		String batchSynthesisScales = Struts2Util.getParameter("batchSynthesisScales");
		if (StringUtils.isEmpty(batchSynthesisScales)) {
			rtnMessage = "Fail to batch oligo, Please select a Synthesis Scales.";
			return rtnMessage;
		}
		String batchPurification = Struts2Util.getParameter("batchPurification");
		if (StringUtils.isEmpty(batchPurification)) {
			rtnMessage = "Fail to batch oligo, Please select a Purification.";
			return rtnMessage;
		}
		String batchModification5 = Struts2Util.getParameter("batchModification5");
		String batchModification3 = Struts2Util.getParameter("batchModification3");
		String sequence = Struts2Util.getParameter("batchOligoSequence");
		if (StringUtils.isEmpty(catalogNo) || (StringUtils.isEmpty(sequence) && (upload == null || upload.isEmpty()))) {
			rtnMessage = "Fail to batch oligo, Please check the session";
			return rtnMessage;
		}
		if (batchBackbone.contains("@")) {
			batchBackbone = batchBackbone.replaceAll("@", "'");
		}
		String batchAliquotingInto = Struts2Util.getParameter("batchAliquotingInto");
		String batchAliquotingSize = Struts2Util.getParameter("batchAliquotingSize");
		Map<String, Object> rtnMap = quoteItemService.batchOligo(itemId, sequence, batchBackbone, 
				batchSynthesisScales, batchPurification, batchModification5, batchModification3, 
				batchAliquotingInto, batchAliquotingSize, upload, uploadFileName, 
				retMap, sessQuoteNo);
		if (rtnMap != null && rtnMap.containsKey("rtnMessage")) {
			rtnMessage = (String) rtnMap.get("rtnMessage");
		}
		return rtnMessage;
	}
	private void newSequenceByInput(Map<String, QuoteItemDTO> retMap)
			throws IllegalAccessException, InvocationTargetException, Exception {
		String sequenceStr = peptide.getSequence();
		if (StringUtils.isNotEmpty(sequenceStr)) {
			String[] seqArray = sequenceStr.split("\n");
			List<Integer> itemNoList = new ArrayList<Integer>();
			int mapSize = itemMap.size();
			synchronized (seqArray) {
				for (String str : seqArray) {
					String[] sArr = StringUtils.split(str,"|");
					if(sArr.length > 1){
						PeptideDTO peptideDTO = new PeptideDTO();
						ModelUtils.mergerModel(peptide, peptideDTO);
						peptideDTO.setSequence(sArr[1]);
						peptideDTO.setName(sArr[0]);
						peptideDTO.setSeqLength(StringUtil.calculateSeqLength(peptideDTO.getSequence()));
						System.out.println(peptideDTO);
						QuoteItemDTO quoteItemDTO = new QuoteItemDTO();
						ModelUtils.mergerModel(quoteItem, quoteItemDTO);
						//quoteItemDTO.setItemNo(itemMap.size() + 1);
						quoteItemDTO.setItemNo(mapSize + 1);
						mapSize++;
						quoteItemDTO.setQuoteItemId(null);
						quoteItemDTO.setPeptide(peptideDTO);
						quoteItemDTO.setParentId(itemId);
						quoteItemDTO.setNameShow(quoteItem.getName()+":"+sArr[0]);
						if (itemNoList.contains(quoteItemDTO.getItemNo())) {
							System.out.println(quoteItemDTO.getItemNo());
						}else {
							itemNoList.add(quoteItemDTO.getItemNo());
						}
						//查询quoteItem的pricechange状态，为Y时代表unitprice修改过，不再计算价格
						boolean isChanged = this.quoteItemService.checkPriceChage(quoteItem);
						if (!isChanged) {
							// 计算Peptide Item的价格
							ServiceItemPiceDTO orderServiceItemPiceDTO = getCostPrice(quoteItem);
							orderServiceItemPiceDTO.setServicePriceType(ServicePriceType.peptide.name());
							orderServiceItemPiceDTO.setPeptideDTO(peptideDTO);
							quoteItemService.returnQuoteServiceItemPrice(sessQuoteNo, quoteItemDTO, orderServiceItemPiceDTO);
						}
						String tmpId = SessionUtil.generateTempId(); 
						quoteItemDTO.setUpdateFlag(Y);
						retMap.put(tmpId, quoteItemDTO);
						itemMap.put(tmpId, quoteItemDTO);
					}else{
						Struts2Util.renderHtml("The sequence format is not valid.");
					}
				}
			}
			SessionUtil.insertRow(SessionConstant.OtherPeptideList.value(), sessQuoteNo, retMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	public String saveLibraryPeptide() throws Exception {
		Map<String, QuoteItemDTO> retMap = new HashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (!checkStatus(retMap).isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
		}
		PeptideDTO peptideDTO = quoteItem.getPeptide();
		if(peptideDTO != null && !(peptideDTO.getDocumentList().isEmpty())){
			Document doc = peptideDTO.getDocumentList().get(0);
			List<ArrayList<String>> excelList = excelUtil.read(fileService.getUploadPath() + doc.getFilePath());
			System.out.println(excelList);
		}else{
			newSequenceByInput(retMap);
		}
		Struts2Util.renderJson(retMap);
		return NONE;
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

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public QuoteItemDTO getQuoteItem() {
		return quoteItem;
	}

	public void setQuoteItem(QuoteItemDTO quoteItem) {
		this.quoteItem = quoteItem;
	}

	public void setItemMap(Map<String, QuoteItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public void setPlasmidPreparation(OrderPlasmidPreparationDTO plasmidPreparation) {
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

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
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

	public OrderServiceDTO getQuoteServiceDTO() {
		return quoteServiceDTO;
	}

	public void setQuoteServiceDTO(OrderServiceDTO quoteServiceDTO) {
		this.quoteServiceDTO = quoteServiceDTO;
	}

	public Map<String, QuoteItemDTO> getItemMap() {
		return itemMap;
	}

	public String getDelFilePath() {
		return delFilePath;
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

	public PkgServiceDTO getQuotePkgService() {
		return quotePkgService;
	}


	public void setQuotePkgService(PkgServiceDTO quotePkgService) {
		this.quotePkgService = quotePkgService;
	}



	public List<PeptideCode> getPeptideCodeList() {
		return peptideCodeList;
	}



	public void setPeptideCodeList(List<PeptideCode> peptideCodeList) {
		this.peptideCodeList = peptideCodeList;
	}


	public List<String> getModificationList() {
		return modificationList;
	}


	public void setModificationList(List<String> modificationList) {
		this.modificationList = modificationList;
	}

	public String getAntibodyItemId() {
		return antibodyItemId;
	}

	public void setAntibodyItemId(String antibodyItemId) {
		this.antibodyItemId = antibodyItemId;
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

	public List<OligoBackbonesDTO> getOligoBackbonesList() {
		return oligoBackbonesList;
	}

	public void setOligoBackbonesList(List<OligoBackbonesDTO> oligoBackbonesList) {
		this.oligoBackbonesList = oligoBackbonesList;
	}

	public CustomServiceDTO getCustomService() {
		return customService;
	}

	public void setCustomService(CustomServiceDTO customService) {
		this.customService = customService;
	}

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public String getBatchCustNo() {
        return batchCustNo;
    }

    public void setBatchCustNo(String batchCustNo) {
        this.batchCustNo = batchCustNo;
    }

	/**
	 * @return the dsPlatesList
	 */
	public List<QuoteDsPlatesDTO> getDsPlatesList() {
		return dsPlatesList;
	}

	/**
	 * @param dsPlatesList the dsPlatesList to set
	 */
	public void setDsPlatesList(List<QuoteDsPlatesDTO> dsPlatesList) {
		this.dsPlatesList = dsPlatesList;
	}

	/**
	 * @return the tubeMap
	 */
	public Map<String, QuoteItemDTO> getTubeMap() {
		return tubeMap;
	}

	/**
	 * @param tubeMap the tubeMap to set
	 */
	public void setTubeMap(Map<String, QuoteItemDTO> tubeMap) {
		this.tubeMap = tubeMap;
	}

	/**
	 * @return the plateMap
	 */
	public Map<String, QuoteItemDTO> getPlateMap() {
		return plateMap;
	}

	/**
	 * @param plateMap the plateMap to set
	 */
	public void setPlateMap(Map<String, QuoteItemDTO> plateMap) {
		this.plateMap = plateMap;
	}

	/**
	 * @return the tubeComment
	 */
	public String getTubeComment() {
		return tubeComment;
	}

	/**
	 * @param tubeComment the tubeComment to set
	 */
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
