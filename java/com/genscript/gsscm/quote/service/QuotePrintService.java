package com.genscript.gsscm.quote.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.product.dao.ProductClassDao;
import com.genscript.gsscm.product.dao.ProductDao;
import com.genscript.gsscm.product.entity.Product;
import com.genscript.gsscm.product.entity.ProductClass;
import com.genscript.gsscm.quote.dao.QuoteCustCloningDao;
import com.genscript.gsscm.quote.dao.QuoteDao;
import com.genscript.gsscm.quote.dao.QuoteGeneSynthesisDao;
import com.genscript.gsscm.quote.dao.QuoteItemDao;
import com.genscript.gsscm.quote.dao.QuoteMainBeanDao;
import com.genscript.gsscm.quote.dao.QuoteMutagenesisDao;
import com.genscript.gsscm.quote.dao.QuoteMutationLibrariesDao;
import com.genscript.gsscm.quote.dao.QuoteOrfCloneDao;
import com.genscript.gsscm.quote.dao.QuotePkgServiceDao;
import com.genscript.gsscm.quote.dao.QuotePlasmidPreparationDao;
import com.genscript.gsscm.quote.dao.QuoteSirnaAndMirnaDao;
import com.genscript.gsscm.quote.entity.QuoteCustCloning;
import com.genscript.gsscm.quote.entity.QuoteGeneSynthesis;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuoteMutagenesis;
import com.genscript.gsscm.quote.entity.QuoteMutationLibraries;
import com.genscript.gsscm.quote.entity.QuoteOrfClone;
import com.genscript.gsscm.quote.entity.QuotePkgService;
import com.genscript.gsscm.quote.entity.QuotePlasmidPreparation;
import com.genscript.gsscm.quote.entity.QuoteSirnaAndMirna;
import com.genscript.gsscm.serv.dao.ServiceClassDao;
import com.genscript.gsscm.serv.dao.ServiceDao;
import com.genscript.gsscm.serv.entity.ServiceClass;

@Service
@Transactional
public class QuotePrintService {

	@Autowired
	private QuoteDao quoteDao;
	@Autowired
	protected QuoteMainBeanDao quoteMainDao;
	@Autowired
	private ProductClassDao productClassDao;
	@Autowired
	private QuoteOrfCloneDao quoteOrfCloneDao;

	@Autowired
	private ProductDao productDao;
	@Autowired
	private QuoteCustCloningDao quoteCustCloningDao;
	@Autowired
	private QuoteGeneSynthesisDao quoteGeneSynthesisDao;
	@Autowired
	private QuotePlasmidPreparationDao quotePlasmidPreparationDao;
	@Autowired
	private QuoteMutagenesisDao quoteMutagenesisDao;
	@Autowired
	private QuoteSirnaAndMirnaDao quoteSirnaAndMirnaDao;
	@Autowired
	private QuoteMutationLibrariesDao quoteMutationLibrariesdao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ServiceClassDao serviceClassDao;
	@Autowired
	private QuotePkgServiceDao quotePkgServiceDao;
	@Autowired
	private QuoteItemDao quoteItemDao;

	/*
	 * 组装txt打印信息
	 */
	public StringBuffer getStrByQuoteNo(String sessQuoteNo) {
		Integer quoteNo = 0;
		if (sessQuoteNo != null && !"".equals(sessQuoteNo)) {
			quoteNo = Integer.parseInt(sessQuoteNo);
		} else {
			return null;
		}
		boolean isHasItem = false;// quoteNo所对应的quote是否有子item
		// 3,4,5,6,7,8,9,10,38 15070721936

		StringBuffer sb = new StringBuffer();
		List<QuoteItem> quoteItemList = null;
		List<String> itemStatus = new ArrayList<String>();
		itemStatus.add("CN");
		QuoteMain quote = this.quoteDao.getById(quoteNo);

		quoteItemList = quote.getQuoteNo() == null ? null : this.quoteItemDao
				.getItemListForPrintTXT(quoteNo, itemStatus);
		if (quoteItemList != null && quoteItemList.size() > 0) {
			sb.append("Item Details:\r\n");
			for (QuoteItem quoteItem : quoteItemList) {
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
					if (clsId != null && clsId == 16) {
						isHasItem = true;
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

				if (StringUtils.isEmpty(quoteItemType)) {
					continue;
				}
				sb.append("\r\n>Item ").append(quoteItem.getItemNo() + " ");
				StringBuffer itemDesc = new StringBuffer();
				String quoteItemGene = quoteItem.getName();
				String description = null;
				String vector = null;
				String Length = null;
				String quanitity = "";

				if (quoteItemType.toLowerCase().startsWith("gene")) {// gen 类型
																		// 只是独立的
					QuoteGeneSynthesis quoteGeneSynthesis = quoteGeneSynthesisDao
							.getById(quoteItem.getQuoteItemId());

					description = quoteGeneSynthesis == null ? ""
							: quoteGeneSynthesis.getSequence();
					vector = quoteGeneSynthesis.getStdVectorName();
					if (StringUtils.isEmpty(vector)) {
						vector = "PUC57";
					}
					quanitity = quoteGeneSynthesis.getStdPlasmidWt()
							+ quoteGeneSynthesis.getStdPlasmidWtUom();
					Length = quoteGeneSynthesis != null
							&& quoteGeneSynthesis.getSeqLength() != null ? quoteGeneSynthesis
							.getSeqLength().toString() : "";
					itemDesc.append(quoteItemGene).append(": \r\n");
					itemDesc.append("\r\nGene name: ").append(
							quoteGeneSynthesis.getGeneName() == null ? ""
									: quoteGeneSynthesis.getGeneName());
					itemDesc.append(", Length: ").append(Length)
							.append("bp, Vector name: ").append(vector);
					if (quoteGeneSynthesis.getStdVectorName() != null
							&& !"".equals(quoteGeneSynthesis.getStdVectorName())) {
						itemDesc.append(", Cloning strategy: ").append(
								quoteGeneSynthesis.getStdVectorName());
					} else {
						itemDesc.append(", Cloning strategy: ").append(vector);
					}
					if (quoteGeneSynthesis.getCloningSite() != null
							&& !"".equals(quoteGeneSynthesis.getCloningSite())) {
						itemDesc.append(", Cloning Site: ").append(
								quoteGeneSynthesis.getCloningSite());
					}
					if (quoteGeneSynthesis.getSequence5() != null
							&& !"".equals(quoteGeneSynthesis.getSequence5())) {
						itemDesc.append(", Additional 5' sequence: ").append(
								quoteGeneSynthesis.getSequence5());
					}
					if (quoteGeneSynthesis.getSequence3() != null
							&& !"".equals(quoteGeneSynthesis.getSequence3())) {
						itemDesc.append(", Additional 3' sequence: ").append(
								quoteGeneSynthesis.getSequence3());
					}

					if (quoteGeneSynthesis.getHostExpsOrganism() != null
							&& !"".equals(quoteGeneSynthesis
									.getHostExpsOrganism())) {
						itemDesc.append(", Host expression organism: ").append(
								quoteGeneSynthesis.getHostExpsOrganism());
					}
					if (quoteGeneSynthesis.getScndExpsOrganism() != null
							&& !"".equals(quoteGeneSynthesis
									.getScndExpsOrganism())) {
						itemDesc.append(", Secondary expression organism: ")
								.append(quoteGeneSynthesis
										.getScndExpsOrganism());
					}
					if (quoteGeneSynthesis.getOpStartPosUom() != null
							&& !"".equals(quoteGeneSynthesis.getOpStartPosUom())) {
						itemDesc.append(", Optimization start position: ")
								.append(quoteGeneSynthesis.getOpStartPosUom());
					}
					if (quoteGeneSynthesis.getOrfEnd() != null
							&& !"".equals(quoteGeneSynthesis.getOrfEnd())) {
						itemDesc.append(", End position: ").append(
								quoteGeneSynthesis.getOrfEnd());
					}
					if (quoteGeneSynthesis.getRstSitesAvoid() != null
							&& !"".equals(quoteGeneSynthesis.getRstSitesAvoid())) {
						itemDesc.append(", Restriction sites to avoid: ")
								.append(quoteGeneSynthesis.getRstSitesAvoid());
					}
					if (quoteGeneSynthesis.getRstSitesKeep() != null
							&& !"".equals(quoteGeneSynthesis.getRstSitesKeep())) {
						itemDesc.append(", Restriction sites to keep: ")
								.append(quoteGeneSynthesis.getRstSitesKeep());
					}
					if (quoteGeneSynthesis.getStopCodonFlag() != null
							&& !"".equals(quoteGeneSynthesis.getStopCodonFlag())) {
						itemDesc.append(", Stop codon needed: ").append(
								quoteGeneSynthesis.getStopCodonFlag());
					}
					if (quoteGeneSynthesis.getCloningSite() != null
							&& !"".equals(quoteGeneSynthesis.getCloningSite())) {
						itemDesc.append(", Quantity: ").append(quanitity)
								.append(", ");
					}
					if (quoteGeneSynthesis.getPlasmidPrepFlag() != null
							&& !"".equals(quoteGeneSynthesis
									.getPlasmidPrepFlag())) {
						if (quoteGeneSynthesis.getPlasmidPrepFlag().equals("N")) {
							if (quoteGeneSynthesis.getStdPlasmidWt() != null
									&& !"".equals(quoteGeneSynthesis
											.getStdPlasmidWt())) {
								itemDesc.append(
										" \r\nPlasmid preparation: Standard delivery: ")
										.append(quoteGeneSynthesis
												.getStdPlasmidWt())
										.append(quoteGeneSynthesis
												.getStdPlasmidWtUom())
										.append("(Free of charge)");
							}
						} else {
							if (quoteGeneSynthesis.getStdPlasmidWt() != null
									&& !"".equals(quoteGeneSynthesis
											.getStdPlasmidWt())) {
								itemDesc.append(", Plasmid preparation: ")
										.append(quoteGeneSynthesis
												.getStdPlasmidWt())
										.append(quoteGeneSynthesis
												.getStdPlasmidWtUom());
							}
						}
					}
					if ("D".equals(quoteGeneSynthesis.getCloningFlag())) {
						itemDesc.append(", Direct Cloning: ")
								.append(quoteGeneSynthesis != null
										&& quoteGeneSynthesis.getVectorSize() != null ? quoteGeneSynthesis
										.getVectorSize() : " ")
								.append(", ")
								.append(quoteGeneSynthesis != null ? quoteGeneSynthesis
										.getVectorResistance() : " ")
								.append(", ")
								.append(quoteGeneSynthesis != null ? quoteGeneSynthesis
										.getVectorCopyNo() : " ");
					}
					if (description != null && !"".equals(description)) {
						itemDesc.append("\r\nSequence: \r\n")
								.append(description).append("\r\n")
								.append("\r\n");
					}

				} else

				if (quoteItemType.toLowerCase().startsWith("custom cloning")) {// custom
																				// cloning
																				// 可以是独立的
																				// 也可以属于
																				// gene
																				// ,plasmid
																				// preparation,Mutagenesis
					QuoteCustCloning quoteCustCloning = quoteCustCloningDao
							.getById(quoteItem.getQuoteItemId());

					if (quoteCustCloning == null) {
						continue;
					}
					if (quoteItem.getParentId() != null
							&& quoteItem.getParentId().intValue() != 0) {// 判断是否有父类。
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
											"mutation libraries")) {// 当前有父类
																	// 如果父类是mutation
																	// libraries

								QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesdao
										.getById(parentItem.getQuoteItemId());

								itemDesc.append("Clone name: ")
										.append(quoteMutationLibraries == null ? ""
												: quoteMutationLibraries
														.getConstructName())
										.append("-Subcloning: ");
								itemDesc.append("\r\n\r\nVector name: ")

								.append(quoteCustCloning.getTgtVector());
								if (quoteCustCloning.getTgtCloningMethod() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningMethod())) {
									itemDesc.append(", Cloning method: ")
											.append(quoteCustCloning
													.getTgtCloningMethod());
								}

								if (quoteCustCloning.getTgtCloningSite() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningSite())) {
									itemDesc.append(", Cloning site: ")
											.append(quoteCustCloning
													.getTgtCloningSite())
											.append("\r\n");
								}
								if (quoteCustCloning.getPlasmidPrepFlag() != null
										&& !"".equals(quoteCustCloning
												.getPlasmidPrepFlag())) {
									if (quoteCustCloning.getPlasmidPrepFlag()
											.equals("N")) {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													" \r\nPlasmid preparation: Standard delivery: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append(" (Free of charge)")
													.append("\r\n");
										}
									}
								}
							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"gene")) {// 当前有父类 如果父类是gen

								QuoteGeneSynthesis quoteGeneSynthesis = quoteGeneSynthesisDao
										.getById(parentItem.getQuoteItemId());
								itemDesc.append("Clone name: ")
										.append(quoteGeneSynthesis == null ? null
												: quoteGeneSynthesis
														.getGeneName())
										.append("-Subcloning: ");
								itemDesc.append("\r\n\r\nVector name: ")

								.append(quoteCustCloning.getTgtVector());
								if (quoteCustCloning.getTgtCloningMethod() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningMethod())) {
									itemDesc.append(", Cloning method: ")
											.append(quoteCustCloning
													.getTgtCloningMethod());
								}

								if (quoteCustCloning.getTgtCloningSite() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningSite())) {
									itemDesc.append(", Cloning site: ")
											.append(quoteCustCloning
													.getTgtCloningSite())
											.append("\r\n");
								}
								if (quoteCustCloning.getPlasmidPrepFlag() != null
										&& !"".equals(quoteCustCloning
												.getPlasmidPrepFlag())) {
									if (quoteCustCloning.getPlasmidPrepFlag()
											.equals("N")) {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													" \r\nPlasmid preparation: Standard delivery: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append(" (Free of charge)")
													.append("\r\n");
										}

									} else {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													", Plasmid preparation: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append("\r\n\r\n");
										}
									}
								}

							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"plasmid preparation")) {// 当前有父类
																		// 如果父类是plasmid
																		// preparation

								QuotePlasmidPreparation quotePlasmidPreparation = quotePlasmidPreparationDao
										.getById(parentItem.getQuoteItemId());
								itemDesc.append("Clone name: ")
										.append(quotePlasmidPreparation == null ? ""
												: quotePlasmidPreparation
														.getPlasmidName())
										.append("-SubPlasmid: ");

								itemDesc.append("\r\n\r\nVector name: ")

								.append(quoteCustCloning.getTgtVector());
								if (quoteCustCloning.getTgtCloningMethod() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningMethod())) {
									itemDesc.append(", Cloning method: ")
											.append(quoteCustCloning
													.getTgtCloningMethod());
								}
								if (quotePlasmidPreparation.getQualityGrade() != null
										&& !"".equals(quotePlasmidPreparation
												.getQualityGrade())) {
									itemDesc.append(", Quality grade:")
											.append(quotePlasmidPreparation
													.getQualityGrade())
											.append(",<br/>");
								}

								if (quoteCustCloning.getTgtCloningSite() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningSite())) {
									itemDesc.append(", Cloning site: ")
											.append(quoteCustCloning
													.getTgtCloningSite())
											.append("\r\n");
								}
								if (quoteCustCloning.getPlasmidPrepFlag() != null
										&& !"".equals(quoteCustCloning
												.getPlasmidPrepFlag())) {
									if (quoteCustCloning.getPlasmidPrepFlag()
											.equals("N")) {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													" \r\nPlasmid preparation: Standard delivery: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append(" (Free of charge)")
													.append("\r\n");
										}
									} else {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													", Plasmid preparation: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append("\r\n");
										}

									}
								}

							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"mutagenesis")) {// 当前有父类
																// 如果父类是mutagenesis
								QuoteMutagenesis quoteMutagenesis = quoteMutagenesisDao
										.getById(parentItem.getQuoteItemId());
								itemDesc.append("Clone name: ")
										.append(quoteMutagenesis == null ? null
												: quoteMutagenesis
														.getVariantName())
										.append("-Subcloning: ");
								itemDesc.append("\r\n\r\nVector name: ")

								.append(quoteCustCloning.getTgtVector());
								if (quoteCustCloning.getTgtCloningMethod() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningMethod())) {
									itemDesc.append(", Cloning method: ")
											.append(quoteCustCloning
													.getTgtCloningMethod());
								}

								if (quoteCustCloning.getTgtCloningSite() != null
										&& !"".equals(quoteCustCloning
												.getTgtCloningSite())) {
									itemDesc.append(", Cloning site: ").append(
											quoteCustCloning
													.getTgtCloningSite());
								}
								if (quoteCustCloning.getPlasmidPrepFlag() != null
										&& !"".equals(quoteCustCloning
												.getPlasmidPrepFlag())) {
									if (quoteCustCloning.getPlasmidPrepFlag()
											.equals("N")) {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													" \r\nPlasmid preparation: Standard delivery: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append(" (Free of charge)")
													.append("\r\n\r\n");
										}
									} else {
										if (quoteCustCloning.getStdPlasmidWt() != null
												&& !"".equals(quoteCustCloning
														.getStdPlasmidWt())) {
											itemDesc.append(
													", Plasmid preparation: ")
													.append(quoteCustCloning
															.getStdPlasmidWt())
													.append(quoteCustCloning
															.getStdPlasmidWtUom())
													.append("\r\n\r\n");
										}
									}
								}

							}

						}

					} else {// 没有父类 就是独立的
						itemDesc.append(quoteItemGene).append(": \r\n");
						itemDesc.append("Template information: ")
								.append("Insert name: ")
								.append(quoteCustCloning.getTgtInsertName() == null ? ""
										: quoteCustCloning.getTgtInsertName())
								.append(", ");
						itemDesc.append("Insert size: ")
								.append(quoteCustCloning.getTgtVectorSize() == null ? ""
										: quoteCustCloning.getTgtVectorSize())
								.append(", ");
						itemDesc.append("Cloning method:  Cloning site: ")
								.append(quoteCustCloning.getTgtCloningSite() == null ? ""
										: quoteCustCloning.getTgtCloningSite())
								.append(", ");
						itemDesc.append("Vector name: ")
								.append(quoteCustCloning.getTgtVector() == null ? ""
										: quoteCustCloning.getTgtVector())
								.append(", ");
						itemDesc.append("Insert sequence: ")
								.append(quoteCustCloning.getTgtSequence() == null ? ""
										: quoteCustCloning.getTgtSequence())
								.append(", \r\n  ");
						itemDesc.append(
								"Target construct: Target insert name: ")
								.append(quoteCustCloning.getTgtInsertName() == null ? ""
										: quoteCustCloning.getTgtInsertName())
								.append(", ");

						itemDesc.append(
								"Target Cloning method:  Cloning site: ")
								.append(quoteCustCloning.getTgtCloningSite() == null ? ""
										: quoteCustCloning.getTgtCloningSite())
								.append(", ");
						itemDesc.append("Target vector: other, Vector name: ")
								.append(quoteCustCloning.getTgtVector() == null ? ""
										: quoteCustCloning.getTgtVector());

						itemDesc.append(", Gene usage: ").append(
								quoteCustCloning.getGeneUsage() == null ? ""
										: quoteCustCloning.getGeneUsage());

						itemDesc.append("Target insert sequence: ")
								.append(quoteCustCloning.getTgtSequence() == null ? ""
										: quoteCustCloning.getTgtSequence())
								.append(", \r\n  ");
						if (quoteCustCloning.getPlasmidPrepFlag().equals("N")) {
							if (quoteCustCloning.getStdPlasmidWt() != null
									&& !"".equals(quoteCustCloning
											.getStdPlasmidWt())) {
								itemDesc.append(
										" \r\nPlasmid preparation: Standard delivery: ")
										.append(quoteCustCloning
												.getStdPlasmidWt())
										.append(quoteCustCloning
												.getStdPlasmidWtUom())
										.append(" (Free of charge)")
										.append("\r\n\r\n");
							}
						} else {
							if (quoteCustCloning.getStdPlasmidWt() != null
									&& !"".equals(quoteCustCloning
											.getStdPlasmidWt())) {
								itemDesc.append(", Plasmid preparation: ")
										.append(quoteCustCloning
												.getStdPlasmidWt())
										.append(quoteCustCloning
												.getStdPlasmidWtUom())
										.append("\r\n\r\n");
							}
						}
					}

				} else

				if (quoteItemType.toLowerCase().startsWith(
						"plasmid preparation")) {// plasmid preparation 类型 如果有父类
													// 那么肯定属于gene类。还可以属于
													// mutagenesis, custom
													// cloning
					QuotePlasmidPreparation quotePlasmidPreparation = quotePlasmidPreparationDao
							.getById(quoteItem.getQuoteItemId());
					if (quotePlasmidPreparation == null) {
						continue;
					}
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
											"mutation libraries")) {// 当前有父类
																	// 如果父类是mutation
																	// libraries
								QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesdao
										.getById(parentItem.getQuoteItemId());

								itemDesc.append(
										"Plasmid preparation " + parentItem != null ? parentItem
												.getName() +" \r\n\r\n": "");/*.append(
										"-Subplasmid: \r\n\r\n");*/

								if (quotePlasmidPreparation != null
										&& quotePlasmidPreparation
												.getPrepWeight() != null) {
									itemDesc.append("  Quantity: ")
											.append(quotePlasmidPreparation
													.getPrepWeight())
											.append(quotePlasmidPreparation
													.getPrepWtUom());
								}
								if (quotePlasmidPreparation.getQualityGrade() != null
										&& !"".equals(quotePlasmidPreparation
												.getQualityGrade())) {
									itemDesc.append(", Quality grade:")
											.append(quotePlasmidPreparation
													.getQualityGrade())
											.append("<br/>");
								}
								if (quotePlasmidPreparation.getPlasmidSize() != null
										&& !"".equals(quotePlasmidPreparation
												.getPlasmidSize())) {
									itemDesc.append(", Plasmid Size: ")
											.append(quotePlasmidPreparation
													.getPlasmidSize())
											.append("\r\n");
								}
								if (quotePlasmidPreparation
										.getAntibioticResistance() != null
										&& !"".equals(quotePlasmidPreparation
												.getAntibioticResistance())) {

									itemDesc.append(", Antibiotic Resistance: ")
											.append(quotePlasmidPreparation
													.getAntibioticResistance())
											.append("\r\n");
								}
								if (quotePlasmidPreparation.getSequence() != null
										&& !"".equals(quotePlasmidPreparation
												.getSequence())) {
									itemDesc.append(" \r\n, Sequence: \r\n")
											.append(" "
													+ quotePlasmidPreparation
															.getSequence())
											.append("\r\n").append("\r\n");
								}
							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"gene")) {// 只能是gene
								itemDesc.append(
										"Plasmid preparation " + parentItem != null ? parentItem
												.getName() +" \r\n\r\n": "");/*.append(
										"-Subplasmid: \r\n\r\n");*/

								if (quotePlasmidPreparation != null
										&& quotePlasmidPreparation
												.getPrepWeight() != null) {
									itemDesc.append("  Quantity: ")
											.append(quotePlasmidPreparation
													.getPrepWeight())
											.append(quotePlasmidPreparation
													.getPrepWtUom());
								}
								if (quotePlasmidPreparation.getQualityGrade() != null
										&& !"".equals(quotePlasmidPreparation
												.getQualityGrade())) {
									itemDesc.append(", Quality grade:")
											.append(quotePlasmidPreparation
													.getQualityGrade())
											.append("<br/>");
								}
								if (quotePlasmidPreparation.getPlasmidSize() != null
										&& !"".equals(quotePlasmidPreparation
												.getPlasmidSize())) {
									itemDesc.append(", Plasmid Size: ")
											.append(quotePlasmidPreparation
													.getPlasmidSize())
											.append("\r\n");
								}
								if (quotePlasmidPreparation
										.getAntibioticResistance() != null
										&& !"".equals(quotePlasmidPreparation
												.getAntibioticResistance())) {

									itemDesc.append(", Antibiotic Resistance: ")
											.append(quotePlasmidPreparation
													.getAntibioticResistance())
											.append("\r\n");
								}
								if (quotePlasmidPreparation.getSequence() != null
										&& !"".equals(quotePlasmidPreparation
												.getSequence())) {
									itemDesc.append(" \r\n, Sequence: \r\n")
											.append(" "
													+ quotePlasmidPreparation
															.getSequence())
											.append("\r\n").append("\r\n");
								}

							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"mutagenesis")) { // 也有可能属于mutagenesis
								itemDesc.append(
										"Plasmid preparation " + parentItem != null ? parentItem
												.getName() +" \r\n\r\n": "");/*.append(
										"-Subplasmid: \r\n\r\n");*/
								if (quotePlasmidPreparation != null
										&& quotePlasmidPreparation
												.getPrepWeight() != null) {
									itemDesc.append(" plasmid preparation: ")
											.append(quotePlasmidPreparation
													.getPrepWeight())
											.append(quotePlasmidPreparation
													.getPrepWtUom());
								}
								if (quotePlasmidPreparation.getPlasmidSize() != null
										&& !"".equals(quotePlasmidPreparation
												.getPlasmidSize())) {
									itemDesc.append(", Quality grade: ")
											.append(quotePlasmidPreparation
													.getPlasmidSize())
											.append(", ");
								}
							} else if (parentItemType != null
									&& parentItemType.toLowerCase().startsWith(
											"custom cloning")) { // 也有可能属于mutagenesis
								itemDesc.append(
										parentItem != null ? parentItem
												.getName() : "").append(
										"-custom cloning-Subplasmid: \r\n");
								if (quotePlasmidPreparation != null
										&& quotePlasmidPreparation
												.getPrepWeight() != null) {
									itemDesc.append(" plasmid preparation: ")
											.append(quotePlasmidPreparation
													.getPrepWeight())
											.append(quotePlasmidPreparation
													.getPrepWtUom());
								}
								if (quotePlasmidPreparation.getPlasmidSize() != null
										&& !"".equals(quotePlasmidPreparation
												.getPlasmidSize())) {
									itemDesc.append(",Quality grade: ")
											.append(quotePlasmidPreparation
													.getQualityGrade())
											.append("\r\n\r\n");
								}
							}

						}
					} else {// 单独的 就要显示很多信息：

						itemDesc.append(quoteItemGene).append(": \r\n");
						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPlasmidName() != null) {
							itemDesc.append(" Plasmid name: ")
									.append(quotePlasmidPreparation
											.getPlasmidName());
						}
						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPrepWeight() != null) {
							itemDesc.append(", Plasmid Size: ")
									.append(quotePlasmidPreparation
											.getPlasmidSize());
						}

						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPrepWeight() != null) {
							itemDesc.append(", Starting Material: ")
									.append(quotePlasmidPreparation
											.getStartingMaterical())
									;
						}
						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPrepWeight() != null) {
							itemDesc.append(", Antibiotic Resistance: ")
									.append(quotePlasmidPreparation
											.getAntibioticResistance())
									 ;
						}
						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPrepWeight() != null) {
							itemDesc.append(", Copy Number: ")
									.append(quotePlasmidPreparation
											.getCopyNumber());
						}

						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPrepWeight() != null) {
							itemDesc.append(", Restriction Analysis: QC Enzyme ")
									.append(quotePlasmidPreparation
											.getPrepWeight())
									.append(quotePlasmidPreparation
											.getPrepWtUom()).append("  \r\n ");
						}

						if (quotePlasmidPreparation != null
								&& quotePlasmidPreparation.getPrepWeight() != null) {
							itemDesc.append(", Quantity: ")
									.append(quotePlasmidPreparation
											.getPrepWeight())
									.append(quotePlasmidPreparation
											.getPrepWtUom()).append(" \r\n ");
						}
						if (quotePlasmidPreparation.getPlasmidSize() != null
								&& !"".equals(quotePlasmidPreparation
										.getPlasmidSize())) {
							itemDesc.append(", Quality grade: ")
									.append(quotePlasmidPreparation
											.getQualityGrade())
									.append("\r\n\r\n ");
						}
					}

				} else

				if (quoteItemType.toLowerCase().startsWith("mutagenesis")) {// 这个类
																			// 如果有父类
																			// 那么可能属于：plasmid
																			// preparation

					if (quoteItem.getParentId() != null
							&& quoteItem.getParentId().intValue() != 0) {// 说明有父类。
						QuoteItem genItem = this.quoteItemDao.getById(quoteItem
								.getParentId());
						itemDesc.append(
								genItem != null ? genItem.getName() : "")
								.append("-Mutagenesis: \r\n\r\n");

						QuoteMutagenesis quoteMutagenesis = quoteMutagenesisDao
								.getById(quoteItem.getQuoteItemId());
						if (quoteMutagenesis == null) {
							continue;
						}

						if (quoteMutagenesis.getVariantName() != null
								&& !"".equals(quoteMutagenesis.getVariantName())) {
							itemDesc.append(" Variant name: ")
									.append(quoteMutagenesis.getVariantName())
									.append(", ");
						}

						if (quoteMutagenesis.getTmplVector() != null
								&& !"".equals(quoteMutagenesis.getTmplVector())) {
							itemDesc.append("Vector: ")
									.append(quoteMutagenesis.getTmplVector())
									.append(", ");
						}
						// Plasmid preparation
						if (quoteMutagenesis.getPlasmidPrepFlag() != null
								&& !"".equals(quoteMutagenesis
										.getPlasmidPrepFlag())) {
							if (quoteMutagenesis.getPlasmidPrepFlag().equals(
									"N")) {
								if (quoteMutagenesis.getStdPlasmidWt() != null
										&& !"".equals(quoteMutagenesis
												.getStdPlasmidWt())) {
									itemDesc.append(
											" \r\nPlasmid preparation: Standard delivery: ")
											.append(quoteMutagenesis
													.getStdPlasmidWt())
											.append(quoteMutagenesis
													.getStdPlasmidWtUom())
											.append(" (Free of charge)")
											.append("\r\n");
								}
							} else {
								if (quoteMutagenesis.getStdPlasmidWt() != null
										&& !"".equals(quoteMutagenesis
												.getStdPlasmidWt())) {
									itemDesc.append(", Plasmid preparation: ")
											.append(quoteMutagenesis
													.getStdPlasmidWt())
											.append(quoteMutagenesis
													.getStdPlasmidWtUom())
											.append("\r\n");
								}
							}

							if (quoteMutagenesis.getVariantSequence() != null
									&& !"".equals(quoteMutagenesis
											.getVariantSequence())) {
								itemDesc.append("Variant sequence: ")
										.append("\r\n")
										.append(quoteMutagenesis
												.getVariantSequence())
										.append("\r\n");
							}

							if ("N".equals(quoteMutagenesis.getCloningFlag())) {
								itemDesc.append("Vector: pUC57 ");
							} else {
								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								QuoteCustCloning quoteCustCloning = null;
								for (QuoteItem item : itemList) {
									quoteCustCloning = quoteCustCloningDao
											.getById(item.getQuoteItemId());
									itemDesc.append(quoteCustCloning != null ? ",Vector:"
											+ quoteCustCloning.getTgtVector()
											: "");
								}

							}
							if ("N".equals(quoteMutagenesis
									.getPlasmidPrepFlag())) {
								itemDesc.append("Quantity: 4 ug ").append(", ");
							} else {
								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								QuotePlasmidPreparation quotePlasmidPreparation = null;
								for (QuoteItem item : itemList) {
									quotePlasmidPreparation = quotePlasmidPreparationDao
											.getById(item.getQuoteItemId());
									itemDesc.append(quotePlasmidPreparation != null ? (", Quantity: "
											+ quotePlasmidPreparation
													.getPrepWeight() + quotePlasmidPreparation
											.getPrepWtUom()) : "");
								}

							}
						}
					} else {
						QuoteMutagenesis quoteMutagenesis = quoteMutagenesisDao
								.getById(quoteItem.getQuoteItemId());
						if (quoteMutagenesis != null) {
							itemDesc.append(quoteItemGene).append(": \r\n");
							if (quoteMutagenesis.getTmplInsertName() != null
									&& !"".equals(quoteMutagenesis
											.getTmplInsertName())) {
								itemDesc.append(
										"Template information: Insert name: ")
										.append(quoteMutagenesis
												.getTmplInsertName())
										.append(", ");
							}

							if (quoteMutagenesis.getTmplVectorSize() != null
									&& !"".equals(quoteMutagenesis
											.getTmplVectorSize())) {
								itemDesc.append("Insert size: ")
										.append(quoteMutagenesis
												.getTmplVectorSize())
										.append(", ");
							}

							if (quoteMutagenesis.getTmplVector() != null
									&& !"".equals(quoteMutagenesis
											.getTmplVector())) {
								itemDesc.append("Vector name: ")
										.append(quoteMutagenesis
												.getTmplVector()).append(", ");
							}

							if (quoteMutagenesis.getTmplSequence() != null
									&& !"".equals(quoteMutagenesis
											.getTmplSequence())) {
								itemDesc.append("Insert sequence: ")
										.append(System
												.getProperty("line.separator"))
										.append(quoteMutagenesis
												.getTmplSequence()
												+ System.getProperty("line.separator"))
										.append("\r\n");
							}

							if (quoteMutagenesis.getVariantName() != null
									&& !"".equals(quoteMutagenesis
											.getVariantName())) {
								itemDesc.append(
										"Mutagenesis instruction Variant name: ")
										.append(quoteMutagenesis
												.getVariantName()).append(", ");
							}

							if ("N".equals(quoteMutagenesis.getCloningFlag())) {
								itemDesc.append(quoteMutagenesis
										.getTmplCloningSite());
							} else {
								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								QuoteCustCloning quoteCustCloning = null;
								for (QuoteItem item : itemList) {
									quoteCustCloning = quoteCustCloningDao
											.getById(item.getQuoteItemId());
									itemDesc.append(";")
											.append(quoteCustCloning != null ? quoteCustCloning
													.getTgtCloningSite() : "");
								}

							}
							if ("N".equals(quoteMutagenesis
									.getPlasmidPrepFlag())) {
								itemDesc.append("Miniprep 4 ug").append(", ");
							} else {
								List<QuoteItem> itemList = this.quoteItemDao
										.findBy("parentId",
												quoteItem.getQuoteItemId());
								QuotePlasmidPreparation quotePlasmidPreparation = null;
								for (QuoteItem item : itemList) {
									quotePlasmidPreparation = quotePlasmidPreparationDao
											.getById(item.getQuoteItemId());
									itemDesc.append("Miniprep: ")
											.append(quotePlasmidPreparation != null ? (quotePlasmidPreparation
													.getPrepWeight() + quotePlasmidPreparation
													.getPrepWtUom())
													: "").append(", ");
								}

							}
							if (quoteMutagenesis.getVariantSequence() != null
									&& !"".equals(quoteMutagenesis
											.getVariantSequence())) {
								itemDesc.append(" \r\nSequence: \r\n")
										.append(" "
												+ quoteMutagenesis
														.getVariantSequence())
										.append("\r\n").append("\r\n");
							}
						}
					}
				} else if (quoteItemType.toLowerCase().startsWith(
						"mutation libraries")) {// 如果有父类 那么肯定属于gene
					itemDesc.append(quoteItemGene).append(": \r\n");
					QuoteMutationLibraries quoteMutationLibraries = quoteMutationLibrariesdao
							.getById(quoteItem.getQuoteItemId());
					if (quoteMutationLibraries != null) {
						itemDesc.append(
								" Construct name: "
										+ quoteMutationLibraries
												.getConstructName() == null ? ""
										: quoteMutationLibraries
												.getConstructName()).append(
								", ");
						itemDesc.append(
								"Library type: "
										+ quoteMutationLibraries
												.getLibraryType() == null ? ""
										: quoteMutationLibraries
												.getLibraryType()).append(", ");
						if (quoteMutationLibraries.getCloningFlag() != null) {
							if (quoteMutationLibraries.getCloningFlag().equals(
									"N")) {
								itemDesc.append("Target vector: No ");
							} else {
								itemDesc.append("Target vector: Yes ");
							}
						}
						itemDesc.append(
								"Vector name: "
										+ quoteMutationLibraries
												.getTgtVectorName() == null ? ""
										: quoteMutationLibraries
												.getTgtVectorName()).append(
								", ");
						itemDesc.append("Cloning method: ");
						itemDesc.append(
								"Cloning site: "
										+ quoteMutationLibraries
												.getTmplCloningSite() == null ? "BamHI-HindIII"
										: quoteMutationLibraries
												.getTmplCloningSite()).append(
								", ");

						itemDesc.append(
								"Position and type of degenerated sites: "
										+ quoteMutationLibraries
												.getDegeneratedSites() == null ? ""
										: quoteMutationLibraries
												.getDegeneratedSites()).append(
								", ");
						itemDesc.append(
								"Other requirements: "
										+ quoteMutationLibraries
												.getOtherRequirement() == null ? ""
										: quoteMutationLibraries
												.getOtherRequirement()).append(
								", ");
						if (quoteMutationLibraries.getPlasmidPrepFlag() != null
								&& !"".equals(quoteMutationLibraries
										.getPlasmidPrepFlag())) {
							if (quoteMutationLibraries.getStdPlasmidWt() != null
									&& !"".equals(quoteMutationLibraries
											.getStdPlasmidWt())) {
								itemDesc.append(
										"Plasmid preparation: Standard delivery: ")
										.append(quoteMutationLibraries
												.getStdPlasmidWt())
										.append(" ")
										.append(quoteMutationLibraries
												.getStdPlasmidWtUom())
										.append(" (Free of charge)\r\n\r\n");
							}
						}
						itemDesc.append("Sequence of interest: \r\n"
								+ quoteMutationLibraries.getInterestSequence()
								+ "\r\n\r\n");

					} else {
						continue;
					}
				} else

				if (quoteItemType.toLowerCase().startsWith("sirna")) {// 如果有
																		// 那么就是独立的。
					QuoteSirnaAndMirna quoteSirnaAndMirna = quoteSirnaAndMirnaDao
							.getById(quoteItem.getQuoteItemId());
					if (quoteSirnaAndMirna == null) {
						continue;
					}
					itemDesc.append(quoteItemGene).append(": \r\n")
							.append("\r\n");
					itemDesc.append("  GeneName: ").append(
							quoteSirnaAndMirna == null ? ""
									: quoteSirnaAndMirna.getGeneName());
					if (quoteSirnaAndMirna.getQuantity() != null
							&& !"".equals(quoteSirnaAndMirna.getQuantity())) {
						itemDesc.append(", Quantity: ").append(
								quoteSirnaAndMirna.getQuantity());
					}
					if (quoteSirnaAndMirna.getVectorName() != null
							&& !"".equals(quoteSirnaAndMirna.getVectorName())) {
						itemDesc.append(", VectorName: ")
								.append(quoteSirnaAndMirna.getVectorName())
								.append("\r\n").append("\r\n");
					}

				} else if (quoteItemType.toLowerCase().startsWith("mirna")) {// 如果有
																				// 那么就是独立的。
					QuoteSirnaAndMirna quoteSirnaAndMirna = quoteSirnaAndMirnaDao
							.getById(quoteItem.getQuoteItemId());
					if (quoteSirnaAndMirna == null) {
						continue;
					}
					itemDesc.append(quoteItemGene).append(": \r\n")
							.append("\r\n");
					itemDesc.append("  GeneName: ").append(
							quoteSirnaAndMirna == null ? ""
									: quoteSirnaAndMirna.getGeneName());

					if (quoteSirnaAndMirna.getQuantity() != null
							&& !"".equals(quoteSirnaAndMirna.getQuantity())) {
						itemDesc.append(", Quantity: ").append(
								quoteSirnaAndMirna.getQuantity());
					}
					if (quoteSirnaAndMirna.getVectorName() != null
							&& !"".equals(quoteSirnaAndMirna.getVectorName())) {
						itemDesc.append(", VectorName: ")
								.append(quoteSirnaAndMirna.getVectorName())
								.append("\r\n").append("\r\n");
					}
				} else if (quoteItemType.toLowerCase().equals("orf clones")) {
					QuoteOrfClone quoteOrfClone = quoteOrfCloneDao
							.getById(quoteItem.getQuoteItemId());
					if (quoteOrfClone != null) {
//						if (quoteOrfClone.getVectorName() != null
//								&& !"".equals(quoteOrfClone.getVectorName())) {
//							itemDesc.append(", Vector Name: ").append(
//									quoteOrfClone.getVectorName());
//						}
//
//						if (quoteOrfClone.getGeneUsage() != null
//								&& !"".equals(quoteOrfClone.getGeneUsage())) {
//							itemDesc.append(", Gene Usage: ").append(
//									quoteOrfClone.getGeneUsage());
//						}
//						if (quoteOrfClone.getPlasmidPrepFlag() != null
//								&& !"".equals(quoteOrfClone
//										.getPlasmidPrepFlag())) {
//							if (quoteOrfClone.getPlasmidPrepFlag().equals("N")) {
//								if (quoteOrfClone.getStdPlasmidWt() != null
//										&& !"".equals(quoteOrfClone
//												.getStdPlasmidWt())) {
//									itemDesc.append(
//											" \r\nPlasmid preparation: Standard delivery: ")
//											.append(quoteOrfClone
//													.getStdPlasmidWt())
//											.append(quoteOrfClone
//													.getStdPlasmidWtUom())
//											.append(" (Free of charge)")
//											.append("\r\n");
//								}
//							} else {
//								if (quoteOrfClone.getStdPlasmidWt() != null
//										&& !"".equals(quoteOrfClone
//												.getStdPlasmidWt())) {
//									itemDesc.append(", Plasmid preparation: ")
//											.append(quoteOrfClone
//													.getStdPlasmidWt())
//											.append(quoteOrfClone
//													.getStdPlasmidWtUom())
//											.append("\r\n");
//								}
//							}
//						}

					}
				} else {
					QuotePkgService quotePkgService = this.quotePkgServiceDao
							.getById(quoteItem.getQuoteItemId());
					description = quotePkgService == null ? ""
							: quotePkgService.getDescription();
					if (quoteItem.getShortDesc() != null
							&& !"".equals(quoteItem.getShortDesc())) {
						itemDesc.append(", ShortDesc: "
								+ quoteItem.getShortDesc());
					}
					if (description != null && !"".equals(description)) {
						itemDesc.append(", description: ").append(description)
								.append("\r\n").append("\r\n");
					}
				}

				sb.append(itemDesc);
			}

		}

		return sb;
	}

}
