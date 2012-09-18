package com.genscript.gsscm.quote.service;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

import com.genscript.gsscm.quoteorder.dto.*;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.ProductDetailType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.ServiceDetailsType;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.service.OrderQuoteUtil;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.quote.dao.QuoteAntibodyDao;
import com.genscript.gsscm.quote.dao.QuoteCustCloningDao;
import com.genscript.gsscm.quote.dao.QuoteDnaSequencingDao;
import com.genscript.gsscm.quote.dao.QuoteDsPlatesDao;
import com.genscript.gsscm.quote.dao.QuoteGeneSynthesisDao;
import com.genscript.gsscm.quote.dao.QuoteItemDao;
import com.genscript.gsscm.quote.dao.QuoteMainBeanDao;
import com.genscript.gsscm.quote.dao.QuoteMutagenesisDao;
import com.genscript.gsscm.quote.dao.QuoteMutationLibrariesDao;
import com.genscript.gsscm.quote.dao.QuoteOligoDao;
import com.genscript.gsscm.quote.dao.QuoteOrfCloneDao;
import com.genscript.gsscm.quote.dao.QuotePeptideDao;
import com.genscript.gsscm.quote.dao.QuotePkgServiceDao;
import com.genscript.gsscm.quote.dao.QuotePlasmidPreparationDao;
import com.genscript.gsscm.quote.dao.QuoteRnaDao;
import com.genscript.gsscm.quote.dao.QuoteServiceDao;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteAntibody;
import com.genscript.gsscm.quote.entity.QuoteCustCloning;
import com.genscript.gsscm.quote.entity.QuoteDnaSequencing;
import com.genscript.gsscm.quote.entity.QuoteDsPlates;
import com.genscript.gsscm.quote.entity.QuoteGeneSynthesis;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuoteMutagenesis;
import com.genscript.gsscm.quote.entity.QuoteMutationLibraries;
import com.genscript.gsscm.quote.entity.QuoteOligo;
import com.genscript.gsscm.quote.entity.QuoteOrfClone;
import com.genscript.gsscm.quote.entity.QuotePeptide;
import com.genscript.gsscm.quote.entity.QuotePkgService;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;
import com.genscript.gsscm.quote.entity.QuoteRna;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.entity.ServiceClass;

@Service
@Transactional
public class QuoteEmailService {
	@Autowired
	protected QuoteMainBeanDao quoteMainDao;
	@Autowired
	private QuoteServiceDao quoteServiceDao;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private QuoteAntibodyDao quoteAntibodyDao;
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
	private QuoteMutagenesisDao quoteMutagenesisDao;
	@Autowired
	private QuoteMutationLibrariesDao quoteMutationLibrariesDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private QuotePkgServiceDao quotePkgServiceDao;
	@Autowired
	private QuoteOligoDao quoteOligoDao;
	@Autowired
	private OrderQuoteUtil orderQuoteUtil;
	@Autowired
	private QuoteItemDao quoteItemDao;
    @Autowired
    private QuoteService quoteService;

	@Autowired
	private QuoteDnaSequencingDao quoteDnaSequencingDao;
	@Autowired
	private QuoteDsPlatesDao quoteDsPlatesDao;

	public StringBuffer getMoreItemsInfoForTemp(Map<String, QuoteItemDTO> quoteItemMap, QuoteMainDTO quoteMain,
			String itemId, QuoteItemDTO quoteItem, ServiceClass sc, ProductClass pc) {
         StringBuffer sbf = new StringBuffer("");
         QuoteItemDTO orderSummaryItem = new QuoteItemDTO();
         List extendedList = new ArrayList();
		// Protein Step是protein的子服务，内容已在父Item中体现，在邮件中不用显示
        //isMailFlag 此item是否已经发送邮件,主要用于区分在父级item中把子级item做为内容发送的item
		if (CatalogType.SERVICE.value().equalsIgnoreCase(quoteItem.getType()) && quoteItem.getClsId() != null && 13 == quoteItem.getClsId()
				&& StringUtils.isNotBlank(quoteItem.getParentId())) {
			return sbf;
		}
        if(quoteItem.isMailFlag()){
            return sbf;
        }
        sc.setLineSymbol("<br/>");
        sc.setShowPriceType("SERVICE");
        String symbol = quoteItem.getUpSymbol();
		try {
				Map<String, QuoteItemDTO> quoteSubItemMap = null;
                quoteItem.setMailFlag(true);
                orderSummaryItem = dozer.map(quoteItem, QuoteItemDTO.class);
				if (sc == null) {
					sbf.append(quoteItem.getName()).append("<br/>");
					sbf.append("Price: ");
					sbf.append(symbol);
					sbf.append(quoteItem.getAmount());
					sbf.append("<br/><br/>");
					return sbf;
				}
				// antibody drug
				if (18 == sc.getClsId()) {
                    quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                    sbf.append("<br/>" + quoteItem.getItemNo());
					sbf.append(": >");
					sbf.append(quoteItem.getName() == null ? "" : quoteItem.getName()).append("<br/><br/>");
					sbf.append("antibody_drug: <br/>");
					if (quoteSubItemMap != null && quoteSubItemMap.size() > 0) {
						int i = 1;
						for (String key : quoteSubItemMap.keySet()) {
                            QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            subQuoteItem.setMailFlag(true);
							PkgServiceDTO ops = subQuoteItem.getQuotePkgService();
							if (ops == null && StringUtil.isNumeric(itemId)) {
								quoteService.setItemMoreDetailByClsId(subQuoteItem);
								ops = subQuoteItem.getQuotePkgService();
							}
							if (ops != null) {
								sbf.append("Step ").append(i).append(": ");
								sbf.append(ops.getName() == null ? "" : ops.getName()).append("<br/>");
								sbf.append(ops.getDescription() == null ? "" : ops.getDescription());
								sbf.append("<br/>");
								i += 1;
							}
						}
					}
                    sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// antibody drug step
				} else if (19 == sc.getClsId()) {
                    sbf.append("<br/>" + quoteItem.getItemNo());
					sbf.append(": >");
					sbf.append(quoteItem.getName() == null ? "" : quoteItem.getName());
					sbf.append("<br/>");
					sbf.append(sc.getName() == null ? "" : sc.getName()).append(":<br/>");
					if (StringUtils.isNotBlank(quoteItem.getParentId())) {
						QuoteItemDTO parentQuoteItem = quoteItemMap.get(quoteItem.getParentId());
						sbf.append("Step of Item ").append(parentQuoteItem.getItemNo()).append("<br/>");
					}
                    sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// biomarker
				} else if (32 == sc.getClsId()) {
                    quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                    sbf.append("<br/>" + quoteItem.getItemNo());
					sbf.append(": >");
					if (quoteItem.getName() != null && !"".equals(quoteItem.getName())) {
						sbf.append(quoteItem.getName());
					}
					sbf.append("<br/>");
					sbf.append("biomarker: <br/>");
					if (quoteSubItemMap != null && quoteSubItemMap.size() > 0) {
						int i = 1;
                        for (String key : quoteSubItemMap.keySet()) {
                            QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            subQuoteItem.setMailFlag(true);
                            quoteService.setItemMoreDetailByClsId(subQuoteItem);
                            PkgServiceDTO ops = quoteSubItemMap.get(key).getQuotePkgService();
                            if (ops != null) {
                                sbf.append("Step ").append(i).append(": ");
                                if (ops.getName() != null && !"".equals(ops.getName())) {
                                    sbf.append(ops.getName()).append("<br/>");
                                }
                                if (ops.getDescription() != null && !"".equals(ops.getDescription())) {
                                    sbf.append(ops.getDescription());
                                }
                                sbf.append("<br/>");
                                i += 1;
                            }
                        }
					}
                    sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// biomarker Step
				} else if (33 == sc.getClsId() && 34 == sc.getClsId() && 14 == sc.getClsId() && 15 == sc.getClsId()) {
                    sbf.append("<br/>" + quoteItem.getItemNo());
					sbf.append(": >");
					if (quoteItem.getName() != null && !"".equals(quoteItem.getName())) {
						sbf.append(quoteItem.getName());
					}
					sbf.append("<br/>");
                    sbf.append(quoteItem.getComment()).append("<br/>");
                     sc.setShowPriceType("TABLE");
					// custom cloning
				} else if (9 == sc.getClsId()) {
					OrderCustCloningDTO orderCustCloning = quoteItem.getCustCloning();
                    if(orderCustCloning == null){
                        quoteService.setItemMoreDetailByClsId(quoteItem);
                        orderCustCloning = quoteItem.getCustCloning();
                    }
                    if(orderCustCloning != null){
                        orderCustCloning = dozer.map(orderCustCloning, OrderCustCloningDTO.class);
                        String  readingFrame = orderCustCloning.getReadingFrame();
                        if(readingFrame != null && !"".equals(readingFrame) && (readingFrame.split(":").length > 1)){
                           orderCustCloning.setReadingFrame(readingFrame.split(":")[1]);
                        }
                        orderSummaryItem.setCustCloning(orderCustCloning);
                         if("Y".equals(orderCustCloning.getPlasmidPrepFlag()) || "Y".equals(orderCustCloning.getCloningFlag())){
                             quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                             for (String key : quoteSubItemMap.keySet()) {
                                 QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                 quoteService.setItemMoreDetailByClsId(subQuoteItem);
                                 if(subQuoteItem.getPlasmidPreparation() != null){
                                     subQuoteItem.setMailFlag(true);
                                     orderSummaryItem.setPlasmidPreparation(subQuoteItem.getPlasmidPreparation());
                                 }
                             }
                         }
                    }
                    sc.setShowPriceType("SERVICE");
					//Mutation Libraries
				} else if (5 == sc.getClsId()) {
					OrderMutationLibrariesDTO orderMutationLibraries = quoteItem.getMutationLibraries();
					if (null == orderMutationLibraries && StringUtil.isNumeric(itemId)) {
                        quoteService.setItemMoreDetailByClsId(quoteItem);
						orderMutationLibraries = quoteItem.getMutationLibraries();
					}
                    if(orderMutationLibraries != null){
                        orderSummaryItem.setMutationLibraries(orderMutationLibraries);
                        if("Y".equals(orderMutationLibraries.getPlasmidPrepFlag())){
                            quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                             for (String key : quoteSubItemMap.keySet()) {
                                 QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                 quoteService.setItemMoreDetailByClsId(subQuoteItem);
                                 if(subQuoteItem.getPlasmidPreparation() != null){
                                     subQuoteItem.setMailFlag(true);
                                     orderSummaryItem.setPlasmidPreparation(subQuoteItem.getPlasmidPreparation());
                                 }
                             }
                        }
                    }
                    sc.setShowPriceType("SERVICE");
					// gene synthesis
				} else if (3 == sc.getClsId()) {
					OrderGeneSynthesisDTO orderGeneSynthesis = quoteItem.getGeneSynthesis();
					if (null == orderGeneSynthesis && StringUtil.isNumeric(itemId)) {
						quoteService.setItemMoreDetailByClsId(quoteItem);
						orderGeneSynthesis = quoteItem.getGeneSynthesis();
					}
                    if (null != orderGeneSynthesis) {
                        orderSummaryItem.setGeneSynthesis(orderGeneSynthesis);
                        if ("Y".equals(orderGeneSynthesis.getPlasmidPrepFlag()) || "Y".equals(orderGeneSynthesis.getCloningFlag())) {
                            quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                            for (String key : quoteSubItemMap.keySet()) {
                                QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                quoteService.setItemMoreDetailByClsId(subQuoteItem);
                                if (subQuoteItem.getCustCloning() != null) {
                                    subQuoteItem.setMailFlag(true);
                                    orderSummaryItem.setCustCloning(subQuoteItem.getCustCloning());
                                } else if (subQuoteItem.getPlasmidPreparation() != null) {
                                    subQuoteItem.setMailFlag(true);
                                    orderSummaryItem.setPlasmidPreparation(subQuoteItem.getPlasmidPreparation());
                                }
                            }
                        }
                    }
                    sc.setShowPriceType("SERVICE");
				} else if (12 == sc.getClsId()) {
					AntibodyDTO oa = quoteItem.getAntibody();
					if (null == oa && StringUtil.isNumeric(itemId)) {
						quoteService.setItemMoreDetailByClsId(quoteItem);
						oa = quoteItem.getAntibody();
					}
					if (null != oa) {
                        orderSummaryItem.setAntibody(oa);
                        quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                        for (String key : quoteSubItemMap.keySet()) {
                            QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            if(subQuoteItem.getPeptide() != null){
                                 subQuoteItem.setMailFlag(true);
                                 extendedList.add(subQuoteItem.getPeptide());
                            }
                        }
                    }
                    sc.setShowPriceType("SERVICE");
					// mutagenesis
				} else if (4 == sc.getClsId()) {
					OrderMutagenesisDTO orderMutagenesis = quoteItem.getMutagenesis();
					if (null == orderMutagenesis && StringUtil.isNumeric(itemId)) {
                        quoteService.setItemMoreDetailByClsId(quoteItem);
						orderMutagenesis = quoteItem.getMutagenesis();
					}
					if (orderMutagenesis != null) {
                        orderSummaryItem.setMutagenesis(orderMutagenesis);
                        if("Y".equals(orderMutagenesis.getPlasmidPrepFlag()) || "Y".equals(orderMutagenesis.getCloningFlag())){
                             quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                             for (String key : quoteSubItemMap.keySet()) {
                                 QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                 quoteService.setItemMoreDetailByClsId(subQuoteItem);
                                 if(subQuoteItem.getCustCloning() != null){
                                     subQuoteItem.setMailFlag(true);
                                     orderSummaryItem.setCustCloning(subQuoteItem.getCustCloning());
                                 }else if(subQuoteItem.getPlasmidPreparation() != null){
                                     subQuoteItem.setMailFlag(true);
                                     orderSummaryItem.setPlasmidPreparation(subQuoteItem.getPlasmidPreparation());
                                 }
                             }
                         }
                    }
                    sc.setShowPriceType("SERVICE");
				} else if (6 == sc.getClsId()) {
					OrderOrfCloneDTO orderOrfClone =  quoteItem.getOrfClone();
                    if(null == orderOrfClone){
                        quoteService.setItemMoreDetailByClsId(quoteItem);
                        orderOrfClone =  quoteItem.getOrfClone();
                    }
                    if (orderOrfClone != null) {
                        orderOrfClone = dozer.map(orderOrfClone, OrderOrfCloneDTO.class);
                        String readingFrame = orderOrfClone.getReadingFrame();
                        if(readingFrame != null && !"".equals(readingFrame) && (readingFrame.split(":").length > 1)){
                           orderOrfClone.setReadingFrame(readingFrame.split(":")[1]);
                        }
                        orderSummaryItem.setOrfClone(orderOrfClone);
                        quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                        for (String key : quoteSubItemMap.keySet()) {
                            QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            quoteService.setItemMoreDetailByClsId(subQuoteItem);
                            if (subQuoteItem.getCustCloning() != null) {
                                subQuoteItem.setMailFlag(true);
                                orderSummaryItem.setCustCloning(subQuoteItem.getCustCloning());
                            } else if (subQuoteItem.getPlasmidPreparation() != null) {
                                orderOrfClone.setPlasmidPrepFlag("Y");
                                subQuoteItem.setMailFlag(true);
                                orderSummaryItem.setPlasmidPreparation(subQuoteItem.getPlasmidPreparation());
                            }
                        }
                    }
                    sc.setShowPriceType("SERVICE");
					// peptide
				} else if (1 == sc.getClsId()) {
                    PeptideDTO peptideDTO = quoteItem.getPeptide();
                    if(null == peptideDTO){
                        quoteService.setItemMoreDetailByClsId(quoteItem);
                        peptideDTO = quoteItem.getPeptide();
                    }
                    if(null != peptideDTO){
                        orderSummaryItem.setPeptide(peptideDTO);
                    }
                    sc.setShowPriceType("SERVICE");
					// pharmaceutical
				} else if (16 == sc.getClsId()) {
					sbf.append(": >");
					sbf.append(quoteItem.getName() == null ? "" : quoteItem.getName());
					sbf.append("<br/>");
					sbf.append("pharmaceutical: <br/>");
                    quoteService.setItemMoreDetailByClsId(quoteItem);
                    quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
					if ((quoteSubItemMap != null) && (quoteSubItemMap.size() > 0)) {
						int i = 1;
						for (String key : quoteSubItemMap.keySet()) {
							PkgServiceDTO ops = quoteSubItemMap.get(key).getQuotePkgService();
							if (null == ops && StringUtil.isNumeric(itemId)) {
                                quoteService.setItemMoreDetailByClsId(quoteSubItemMap.get(key));
								ops = quoteItem.getQuotePkgService();
							}
							if (ops != null) {
								sbf.append("Step ").append(i).append(": ").append(ops.getName()).append("<br/>");
								sbf.append(ops.getDescription()).append("<br/>");
								i += 1;
							}
						}
					}
					sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// pharmaceutical step
				} else if (17 == sc.getClsId()) {
					sbf.append(": >").append(quoteItem.getName()).append("<br/>");
					sbf.append(sc.getName()).append(":<br/>");
					QuoteItemDTO parentQuoteItem = quoteItemMap.get(quoteItem.getParentId());
					if (parentQuoteItem != null) {
						sbf.append("Step of Item ").append(parentQuoteItem.getItemNo()).append("<br/>");
					}
					sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// plasmid preparation
				} else if (10 == sc.getClsId()) {
					OrderPlasmidPreparationDTO orderPlasmidPreparation = quoteItem.getPlasmidPreparation();
					if (null == orderPlasmidPreparation && StringUtil.isNumeric(itemId)) {
						quoteService.setItemMoreDetailByClsId(quoteItem);
						orderPlasmidPreparation = quoteItem.getPlasmidPreparation();
                        orderSummaryItem.setPlasmidPreparation(orderPlasmidPreparation);
					}
					if (null != quoteItem.getPlasmidPreparation()) {
                        orderSummaryItem.setPlasmidPreparation(orderPlasmidPreparation);
					}
                    sc.setShowPriceType("SERVICE");
					// polyclonal antibody for monoclonal group
				} else if (28 == sc.getClsId()) {
					AntibodyDTO oa = quoteItem.getAntibody();
					if (null == oa && StringUtil.isNumeric(itemId)) {
						quoteService.setItemMoreDetailByClsId(quoteItem);
						oa = quoteItem.getAntibody();
					}
					if (null != oa) {
						orderSummaryItem.setAntibody(oa);
                        quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
                        for (String key : quoteSubItemMap.keySet()) {
                            QuoteItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            if(subQuoteItem.getPeptide() != null){
                                 subQuoteItem.setMailFlag(true);
                                 extendedList.add(subQuoteItem.getPeptide());
                            }
                        }
					}
					sc.setShowPriceType("TABLE");
					// polyclonal antibody for polyclonal group
				} else if (2 == sc.getClsId()) {
					PkgServiceDTO ps = quoteItem.getQuotePkgService();
					if (null == ps && StringUtil.isNumeric(itemId)) {
						quoteService.setItemMoreDetailByClsId(quoteItem);
						ps = quoteItem.getQuotePkgService();
					}
					if (ps != null) {
						sbf.append(": >");
						sbf.append(quoteItem.getName() == null ? "" : quoteItem.getName()).append("<br/>");
						sbf.append("protein:");
						sbf.append(ps.getName() == null ? "" : ps.getName()).append("<br/>");

					}
                    quoteSubItemMap = getAllFirstStepChildrenService(quoteItemMap, itemId);
					if (quoteSubItemMap != null && quoteSubItemMap.size() > 0) {
						int i = 1;
						for (String key : quoteSubItemMap.keySet()) {
							PkgServiceDTO ops = quoteSubItemMap.get(key)
									.getQuotePkgService();
							if (ops != null) {
								sbf.append("Step ").append(i).append(": ").append(ops.getName()).append("<br/>");
								sbf.append("Description: " + ops.getDescription()).append(",<br/><br/>");
								sbf.append(" Sequence: ");
								sbf.append(ops.getSequence() == null ? "" : ops.getSequence()).append(",<br/><br/>");
								sbf.append(" Protein Sequence:");
								sbf.append(ops.getProteinSeq() == null ? "" : ops.getProteinSeq());
								sbf.append("<br/>");
								i += 1;
							}
						}
					}
					sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// protein step
				} else if (13 == sc.getClsId()) {
					sbf.append(": >");
					sbf.append(quoteItem.getName()).append("<br/>");
					sbf.append(sc.getName()).append(":<br/>");
					QuoteItemDTO parentQuoteItem = quoteItemMap.get(quoteItem.getParentId());
					if (null != parentQuoteItem) {
						sbf.append("Step of Item ").append(parentQuoteItem.getItemNo()).append("<br/>");
					}
                    sbf.append(showPrice(symbol, quoteItem));
                    return sbf;
					// sirna or mirna
				} else if (7 == sc.getClsId() || 8 == sc.getClsId()) {
                    RnaDTO rna = quoteItem.getRna();
                    if(rna == null){
                        quoteService.setItemMoreDetailByClsId(quoteItem);
                        rna = quoteItem.getRna();
                    }
                    if(rna != null){
                        orderSummaryItem.setRna(rna);
                    }
                    sc.setShowPriceType("TABLE");
				} else if (29 == sc.getClsId()) {
					sbf.append(" : " + quoteItem.getCatalogNo());
					sbf.append(" " + quoteItem.getName() == null ? "" : quoteItem.getName()).append("<br/>").append(";");
					sbf.append("  Qty: ");
					sbf.append(quoteItem.getQuantity() == null ? "" : quoteItem.getQuantity()).append(" ;");
					sbf.append("  Unit Price: ");
					sbf.append(quoteItem.getUnitPrice() == null ? "" : new BigDecimal(String.valueOf(quoteItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)).append( "<br/>");
					sbf.append("Price: ");
					sbf.append(symbol);
					sbf.append(quoteItem.getAmount() == null ? "" : quoteItem.getAmount()).append("<br/><br/>");
                    return sbf;
				}
            String quoteSummary = mimeMailService.buildOrderSummaryContent(quoteItemMap, quoteMain, itemId, orderSummaryItem, sbf, sc, pc, extendedList);
			sbf.append(quoteSummary);
		} catch (Exception ex) {
			sbf.append(": ").append(quoteItem.getName() == null ? "" : quoteItem.getName()).append(";<br/> ");
			sbf.append("Qty: ");
			sbf.append(quoteItem.getQuantity() == null ? "" : quoteItem.getQuantity()).append(" ;");
			sbf.append(" Unit Price: ");
			sbf.append(quoteItem.getUnitPrice() == null ? "" : new BigDecimal(String.valueOf(quoteItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)).append("<br/>");
			sbf.append("Price: ");
			sbf.append(symbol);
			sbf.append(quoteItem.getAmount() == null ? "" : quoteItem.getAmount()).append("<br/><br/>");
		}
		return sbf;
	}

    /*
        获取主服务的所有第一级子服务  ，即 直接相关联的item
    */
	public Map<String, QuoteItemDTO> getAllFirstStepChildrenService(Map<String, QuoteItemDTO> itemMap, String itemId) {
		Map<String, QuoteItemDTO> childrenServiceMap = new LinkedHashMap<String, QuoteItemDTO>();
		if (itemMap == null || itemMap.isEmpty()) {
			return childrenServiceMap;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
		// 获取主服务的所有第一级子服务
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			QuoteItemDTO tmpItemDTO = entry.getValue();
			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(tmpItemDTO.getType()) && itemId.equals(tmpItemDTO.getParentId())) {
				childrenServiceMap.put(tmpKey, tmpItemDTO);
			}
		}
		return childrenServiceMap;
	}

    private String getEndotoxin(String qualityGrade) {
        if ("Ultralow Endotoxin".equals(qualityGrade) || "Advanced SC Grade".equals(qualityGrade)){
            return "Advanced SC Grade";
        } else if("Low Endotoxin".equals(qualityGrade) || "SC Grade".equals(qualityGrade)){
            return "SC Grade";
        } else {
            return "";
        }
    }

    //显示的Item的price信息
    private String showPrice(String symbol, QuoteItemDTO quoteItem){
        StringBuffer priceStr = new StringBuffer("");
        priceStr.append("Unit Price: ").append(symbol).append(
                new BigDecimal(String.valueOf(quoteItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP))
							.append("; Quantity: ").append(quoteItem.getQuantity()).append(";<br/>");
        return priceStr.toString();
    }
}
