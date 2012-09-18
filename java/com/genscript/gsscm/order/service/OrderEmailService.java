package com.genscript.gsscm.order.service;

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
import com.genscript.gsscm.common.constant.ProductDetailType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.ServiceDetailsType;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.order.dto.OrderItemDTO;
import com.genscript.gsscm.order.dto.OrderMainDTO;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.quote.dao.QuoteOrfCloneDao;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.entity.ServiceClass;

@Service
@Transactional
public class OrderEmailService {
	@Autowired
	private MimeMailService mimeMailService;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private OrderService orderService;
    @Autowired(required = false)
	private DozerBeanMapper dozer;

	/*
	 * 组装email
	 */

	public StringBuffer getMoreItemsInfoForTemp(Map<String, OrderItemDTO> orderItemMap, OrderMainDTO orderMain,
			String itemId, OrderItemDTO orderItem, ServiceClass sc, ProductClass pc, int flag) {
         StringBuffer sbf = new StringBuffer("");
         OrderItemDTO orderSummaryItem = new OrderItemDTO();
         List extendedList = new ArrayList();
		// Protein Step是protein的子服务，内容已在父Item中体现，在邮件中不用显示
        //isMailFlag 此item是否已经发送邮件,主要用于区分在父级item中把子级item做为内容发送的item
		if (CatalogType.SERVICE.value().equalsIgnoreCase(orderItem.getType()) && orderItem.getClsId() != null && 13 == orderItem.getClsId()
				&& StringUtils.isNotBlank(orderItem.getParentId())) {
			return sbf;
		}
        if(orderItem.isMailFlag()){
            return sbf;
        }
        if(sc == null){
           sc = new ServiceClass();
        }
		if (flag == 1) {
            sc.setLineSymbol("\r\n");
		} else {
			sc.setLineSymbol("<br/>");
		}
        sc.setShowPriceType("SERVICE");
        String symbol = orderItem.getUpSymbol();
		try {
				Map<String, OrderItemDTO> quoteSubItemMap = null;
                orderItem.setMailFlag(true);
                orderSummaryItem = dozer.map(orderItem, OrderItemDTO.class);
				if (sc == null) {
					sbf.append(orderItem.getName()).append("<br/>");
					sbf.append("Price: ");
					sbf.append(symbol);
					sbf.append(orderItem.getAmount());
					sbf.append("<br/><br/>");
					return sbf;
				}
				// antibody drug
				if (18 == sc.getClsId()) {
                    quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                    sbf.append("<br/>" + orderItem.getItemNo());
					sbf.append(": >");
					sbf.append(orderItem.getName() == null ? "" : orderItem.getName()).append("<br/><br/>");
					sbf.append("antibody_drug: <br/>");
					if (quoteSubItemMap != null && quoteSubItemMap.size() > 0) {
						int i = 1;
						for (String key : quoteSubItemMap.keySet()) {
                            OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            subQuoteItem.setMailFlag(true);
							PkgServiceDTO ops = subQuoteItem.getOrderPkgService();
							if (ops == null && StringUtil.isNumeric(itemId)) {
								orderService.setItemMoreDetailByClsId(subQuoteItem);
								ops = subQuoteItem.getOrderPkgService();
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
                    sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// antibody drug step
				} else if (19 == sc.getClsId()) {
                    sbf.append("<br/>" + orderItem.getItemNo());
					sbf.append(": >");
					sbf.append(orderItem.getName() == null ? "" : orderItem.getName());
					sbf.append("<br/>");
					sbf.append(sc.getName() == null ? "" : sc.getName()).append(":<br/>");
					if (StringUtils.isNotBlank(orderItem.getParentId())) {
						OrderItemDTO parentQuoteItem = orderItemMap.get(orderItem.getParentId());
						sbf.append("Step of Item ").append(parentQuoteItem.getItemNo()).append("<br/>");
					}
                    sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// biomarker
				} else if (32 == sc.getClsId()) {
                    quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                    sbf.append("<br/>" + orderItem.getItemNo());
					sbf.append(": >");
					if (orderItem.getName() != null && !"".equals(orderItem.getName())) {
						sbf.append(orderItem.getName());
					}
					sbf.append("<br/>");
					sbf.append("biomarker: <br/>");
					if (quoteSubItemMap != null && quoteSubItemMap.size() > 0) {
						int i = 1;
                        for (String key : quoteSubItemMap.keySet()) {
                            OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            subQuoteItem.setMailFlag(true);
                            orderService.setItemMoreDetailByClsId(subQuoteItem);
                            PkgServiceDTO ops = quoteSubItemMap.get(key).getOrderPkgService();
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
                    sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// biomarker Step
				} else if (33 == sc.getClsId() && 34 == sc.getClsId() && 14 == sc.getClsId() && 15 == sc.getClsId()) {
                    sbf.append("<br/>" + orderItem.getItemNo());
					sbf.append(": >");
					if (orderItem.getName() != null && !"".equals(orderItem.getName())) {
						sbf.append(orderItem.getName());
					}
					sbf.append("<br/>");
                    sbf.append(orderItem.getComment()).append("<br/>");
                     sc.setShowPriceType("TABLE");
					// custom cloning
				} else if (9 == sc.getClsId()) {
					OrderCustCloningDTO orderCustCloning = orderItem.getCustCloning();
                    if(orderCustCloning == null){
                        orderService.setItemMoreDetailByClsId(orderItem);
                        orderCustCloning = orderItem.getCustCloning();
                    }
                    if(orderCustCloning != null){
                        orderCustCloning = dozer.map(orderCustCloning, OrderCustCloningDTO.class);
                        String  readingFrame = orderCustCloning.getReadingFrame();
                        if(readingFrame != null && !"".equals(readingFrame) && (readingFrame.split(":").length > 1)){
                           orderCustCloning.setReadingFrame(readingFrame.split(":")[1]);
                        }
                        orderSummaryItem.setCustCloning(orderCustCloning);
                         if("Y".equals(orderCustCloning.getPlasmidPrepFlag()) || "Y".equals(orderCustCloning.getCloningFlag())){
                             quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                             for (String key : quoteSubItemMap.keySet()) {
                                 OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                 orderService.setItemMoreDetailByClsId(subQuoteItem);
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
					OrderMutationLibrariesDTO orderMutationLibraries = orderItem.getMutationLibraries();
					if (null == orderMutationLibraries && StringUtil.isNumeric(itemId)) {
                        orderService.setItemMoreDetailByClsId(orderItem);
						orderMutationLibraries = orderItem.getMutationLibraries();
					}
                    if(orderMutationLibraries != null){
                        orderSummaryItem.setMutationLibraries(orderMutationLibraries);
                        if("Y".equals(orderMutationLibraries.getPlasmidPrepFlag())){
                            quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                             for (String key : quoteSubItemMap.keySet()) {
                                 OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                 orderService.setItemMoreDetailByClsId(subQuoteItem);
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
					OrderGeneSynthesisDTO orderGeneSynthesis = orderItem.getGeneSynthesis();
					if (null == orderGeneSynthesis && StringUtil.isNumeric(itemId)) {
						orderService.setItemMoreDetailByClsId(orderItem);
						orderGeneSynthesis = orderItem.getGeneSynthesis();
					}
                    if (null != orderGeneSynthesis) {
                        orderSummaryItem.setGeneSynthesis(orderGeneSynthesis);
                        if ("Y".equals(orderGeneSynthesis.getPlasmidPrepFlag()) || "Y".equals(orderGeneSynthesis.getCloningFlag())) {
                            quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                            for (String key : quoteSubItemMap.keySet()) {
                                OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                orderService.setItemMoreDetailByClsId(subQuoteItem);
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
					AntibodyDTO oa = orderItem.getAntibody();
					if (null == oa && StringUtil.isNumeric(itemId)) {
						orderService.setItemMoreDetailByClsId(orderItem);
						oa = orderItem.getAntibody();
					}
					if (null != oa) {
                        orderSummaryItem.setAntibody(oa);
                        quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                        for (String key : quoteSubItemMap.keySet()) {
                            OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            if(subQuoteItem.getPeptide() != null){
                                 subQuoteItem.setMailFlag(true);
                                 extendedList.add(subQuoteItem.getPeptide());
                            }
                        }
                    }
                    sc.setShowPriceType("SERVICE");
					// mutagenesis
				} else if (4 == sc.getClsId()) {
					OrderMutagenesisDTO orderMutagenesis = orderItem.getMutagenesis();
					if (null == orderMutagenesis && StringUtil.isNumeric(itemId)) {
                        orderService.setItemMoreDetailByClsId(orderItem);
						orderMutagenesis = orderItem.getMutagenesis();
					}
					if (orderMutagenesis != null) {
                        orderSummaryItem.setMutagenesis(orderMutagenesis);
                        if("Y".equals(orderMutagenesis.getPlasmidPrepFlag()) || "Y".equals(orderMutagenesis.getCloningFlag())){
                             quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                             for (String key : quoteSubItemMap.keySet()) {
                                 OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                                 orderService.setItemMoreDetailByClsId(subQuoteItem);
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
					OrderOrfCloneDTO orderOrfClone =  orderItem.getOrfClone();
                    if(null == orderOrfClone){
                        orderService.setItemMoreDetailByClsId(orderItem);
                        orderOrfClone =  orderItem.getOrfClone();
                    }
                    if (orderOrfClone != null) {
                        orderOrfClone = dozer.map(orderOrfClone, OrderOrfCloneDTO.class);
                        String readingFrame = orderOrfClone.getReadingFrame();
                        if(readingFrame != null && !"".equals(readingFrame) && (readingFrame.split(":").length > 1)){
                           orderOrfClone.setReadingFrame(readingFrame.split(":")[1]);
                        }
                        orderSummaryItem.setOrfClone(orderOrfClone);
                        quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                        for (String key : quoteSubItemMap.keySet()) {
                            OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            orderService.setItemMoreDetailByClsId(subQuoteItem);
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
                    PeptideDTO peptideDTO = orderItem.getPeptide();
                    if(null == peptideDTO){
                        orderService.setItemMoreDetailByClsId(orderItem);
                        peptideDTO = orderItem.getPeptide();
                    }
                    if(null != peptideDTO){
                        orderSummaryItem.setPeptide(peptideDTO);
                    }
                    sc.setShowPriceType("SERVICE");
					// pharmaceutical
				} else if (16 == sc.getClsId()) {
					sbf.append(": >");
					sbf.append(orderItem.getName() == null ? "" : orderItem.getName());
					sbf.append("<br/>");
					sbf.append("pharmaceutical: <br/>");
                    orderService.setItemMoreDetailByClsId(orderItem);
                    quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
					if ((quoteSubItemMap != null) && (quoteSubItemMap.size() > 0)) {
						int i = 1;
						for (String key : quoteSubItemMap.keySet()) {
							PkgServiceDTO ops = quoteSubItemMap.get(key).getOrderPkgService();
							if (null == ops && StringUtil.isNumeric(itemId)) {
                                orderService.setItemMoreDetailByClsId(quoteSubItemMap.get(key));
								ops = orderItem.getOrderPkgService();
							}
							if (ops != null) {
								sbf.append("Step ").append(i).append(": ").append(ops.getName()).append("<br/>");
								sbf.append(ops.getDescription()).append("<br/>");
								i += 1;
							}
						}
					}
					sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// pharmaceutical step
				} else if (17 == sc.getClsId()) {
					sbf.append(": >").append(orderItem.getName()).append("<br/>");
					sbf.append(sc.getName()).append(":<br/>");
					OrderItemDTO parentQuoteItem = orderItemMap.get(orderItem.getParentId());
					if (parentQuoteItem != null) {
						sbf.append("Step of Item ").append(parentQuoteItem.getItemNo()).append("<br/>");
					}
					sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// plasmid preparation
				} else if (10 == sc.getClsId()) {
					OrderPlasmidPreparationDTO orderPlasmidPreparation = orderItem.getPlasmidPreparation();
					if (null == orderPlasmidPreparation && StringUtil.isNumeric(itemId)) {
						orderService.setItemMoreDetailByClsId(orderItem);
						orderPlasmidPreparation = orderItem.getPlasmidPreparation();
                        orderSummaryItem.setPlasmidPreparation(orderPlasmidPreparation);
					}
					if (null != orderItem.getPlasmidPreparation()) {
                        orderSummaryItem.setPlasmidPreparation(orderPlasmidPreparation);
					}
                    sc.setShowPriceType("SERVICE");
					// polyclonal antibody for monoclonal group
				} else if (28 == sc.getClsId()) {
					AntibodyDTO oa = orderItem.getAntibody();
					if (null == oa && StringUtil.isNumeric(itemId)) {
						orderService.setItemMoreDetailByClsId(orderItem);
						oa = orderItem.getAntibody();
					}
					if (null != oa) {
						orderSummaryItem.setAntibody(oa);
                        quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
                        for (String key : quoteSubItemMap.keySet()) {
                            OrderItemDTO subQuoteItem = quoteSubItemMap.get(key);
                            if(subQuoteItem.getPeptide() != null){
                                 subQuoteItem.setMailFlag(true);
                                 extendedList.add(subQuoteItem.getPeptide());
                            }
                        }
					}
					sc.setShowPriceType("TABLE");
					// polyclonal antibody for polyclonal group
				} else if (2 == sc.getClsId()) {
					PkgServiceDTO ps = orderItem.getOrderPkgService();
					if (null == ps && StringUtil.isNumeric(itemId)) {
						orderService.setItemMoreDetailByClsId(orderItem);
						ps = orderItem.getOrderPkgService();
					}
					if (ps != null) {
						sbf.append(": >");
						sbf.append(orderItem.getName() == null ? "" : orderItem.getName()).append("<br/>");
						sbf.append("protein:");
						sbf.append(ps.getName() == null ? "" : ps.getName()).append("<br/>");

					}
                    quoteSubItemMap = getAllFirstStepChildrenService(orderItemMap, itemId);
					if (quoteSubItemMap != null && quoteSubItemMap.size() > 0) {
						int i = 1;
						for (String key : quoteSubItemMap.keySet()) {
							PkgServiceDTO ops = quoteSubItemMap.get(key).getOrderPkgService();
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
					sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// protein step
				} else if (13 == sc.getClsId()) {
					sbf.append(": >");
					sbf.append(orderItem.getName()).append("<br/>");
					sbf.append(sc.getName()).append(":<br/>");
					OrderItemDTO parentQuoteItem = orderItemMap.get(orderItem.getParentId());
					if (null != parentQuoteItem) {
						sbf.append("Step of Item ").append(parentQuoteItem.getItemNo()).append("<br/>");
					}
                    sbf.append(showPrice(symbol, orderItem));
                    return sbf;
					// sirna or mirna
				} else if (7 == sc.getClsId() || 8 == sc.getClsId()) {
                    RnaDTO rna = orderItem.getRna();
                    if(rna == null){
                        orderService.setItemMoreDetailByClsId(orderItem);
                        rna = orderItem.getRna();
                    }
                    if(rna != null){
                        orderSummaryItem.setRna(rna);
                    }
                    sc.setShowPriceType("TABLE");
				} else if (29 == sc.getClsId()) {
					sbf.append(" : " + orderItem.getCatalogNo());
					sbf.append(" " + orderItem.getName() == null ? "" : orderItem.getName()).append("<br/>").append(";");
					sbf.append("  Qty: ");
					sbf.append(orderItem.getQuantity() == null ? "" : orderItem.getQuantity()).append(" ;");
					sbf.append("  Unit Price: ");
					sbf.append(orderItem.getUnitPrice() == null ? "" : new BigDecimal(String.valueOf(orderItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)).append( "<br/>");
					sbf.append("Price: ");
					sbf.append(symbol);
					sbf.append(orderItem.getAmount() == null ? "" : orderItem.getAmount()).append("<br/><br/>");
                    return sbf;
				}
            String quoteSummary = mimeMailService.buildOrderSummaryContent(orderItemMap, orderMain, itemId, orderSummaryItem, sbf, sc, pc, extendedList);
			sbf.append(quoteSummary);
		} catch (Exception ex) {
			sbf.append(": ").append(orderItem.getName() == null ? "" : orderItem.getName()).append(";<br/> ");
			sbf.append("Qty: ");
			sbf.append(orderItem.getQuantity() == null ? "" : orderItem.getQuantity()).append(" ;");
			sbf.append(" Unit Price: ");
			sbf.append(orderItem.getUnitPrice() == null ? "" : new BigDecimal(String.valueOf(orderItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP)).append("<br/>");
			sbf.append("Price: ");
			sbf.append(symbol);
			sbf.append(orderItem.getAmount() == null ? "" : orderItem.getAmount()).append("<br/><br/>");
		}
		return sbf;
	}

    /*
        获取主服务的所有第一级子服务  ，即 直接相关联的item
    */
	public Map<String, OrderItemDTO> getAllFirstStepChildrenService(Map<String, OrderItemDTO> itemMap, String itemId) {
		Map<String, OrderItemDTO> childrenServiceMap = new LinkedHashMap<String, OrderItemDTO>();
		if (itemMap == null || itemMap.isEmpty()) {
			return childrenServiceMap;
		}
		Iterator<Entry<String, OrderItemDTO>> it = itemMap.entrySet().iterator();
		// 获取主服务的所有第一级子服务
		while (it.hasNext()) {
			Entry<String, OrderItemDTO> entry = it.next();
			String tmpKey = entry.getKey();
			OrderItemDTO tmpItemDTO = entry.getValue();
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
    private String showPrice(String symbol, OrderItemDTO orderItem){
        StringBuffer priceStr = new StringBuffer("");
        priceStr.append("Unit Price: ").append(symbol).append(
                new BigDecimal(String.valueOf(orderItem.getUnitPrice())).setScale(2, BigDecimal.ROUND_HALF_UP))
							.append("; Quantity: ").append(orderItem.getQuantity()).append(";<br/>");
        return priceStr.toString();
    }

}
