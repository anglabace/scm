package com.genscript.gsscm.quoteorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.common.util.MoreDetailUtil;
import com.genscript.gsscm.olddb.dao.PackageDescriptionsDao;
import com.genscript.gsscm.olddb.entity.PackageDescriptions;
import com.genscript.gsscm.order.dao.StatusListDao;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.quoteorder.dto.OrderCustCloningDTO;
import com.genscript.gsscm.quoteorder.dto.OrderGeneSynthesisDTO;
import com.genscript.gsscm.quoteorder.dto.OrderPlasmidPreparationDTO;
import com.genscript.gsscm.quoteorder.dto.OrderServiceDTO;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.quoteorder.dto.TargetDateDTO;
import com.genscript.gsscm.serv.service.ServService;
import java.math.BigInteger;

/**
 * 
 * order quote 公用方法
 * 
 * @author zouyulu
 * 
 */
@Service
@Transactional
public class QuoteOrderUtil {
	@Autowired
	private StatusListDao statusListDao;
	@Autowired
	private PackageDescriptionsDao packageDescriptionsDao;
	@Autowired
	private ServService servService;
	
	/**
	 * 获得service的targetDate
	 * @return
	 */
	public TargetDateDTO getTargetDate(Integer clsId, Integer leadTime, String catalogNo, Set<String>catalogNoSet, OrderServiceDTO serviceDTO) {
		if(clsId == null){
			return null;
		}
		if (clsId.equals(3)) {//gene
			return QuoteOrderUtil.getGeneTargetDate(serviceDTO);
		} else if (clsId.equals(11) || clsId.equals(28)) {
			return QuoteOrderUtil.getPolyAntibodyTargetDate(catalogNo, leadTime);
		}else if(clsId.equals(12)){
			return QuoteOrderUtil.getMonAntibodyTargetDate(leadTime, catalogNoSet, catalogNo);
		}else if(clsId.equals(1)){
			return QuoteOrderUtil.getPeptideTargetDate(serviceDTO);
		}else if(MoreDetailUtil.isProteinGroupService(clsId)){
			return QuoteOrderUtil.getPkgServiceTargetDate(leadTime, catalogNo);
		}else if(clsId.equals(7) || clsId.equals(8)){
			return QuoteOrderUtil.getSirnaTargetDate();
		}else{
			return getOtherTargetDate(catalogNo);
		}
	}

	/**
	 * 获得基因合成服务的targetDate
	 * @param serviceDTO
	 * @return
	 */
	public static TargetDateDTO getGeneTargetDate(OrderServiceDTO serviceDTO) {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		OrderGeneSynthesisDTO geneSynthesis = serviceDTO.getGeneSynthesis();
		int turnaround = 11;
		Integer seqLength = geneSynthesis!=null?geneSynthesis.getSeqLength():null;
		int length = 0;
		if (seqLength != null) {
			length = seqLength.intValue();
		}
		if(length > 3000){
			double d = Math.ceil((length - 3000)/1000.0);
			turnaround = 18 + 7*(int)d;
		} else if (length > 1000 && length <= 3000) {
			turnaround = 18;
		} else if (length <= 1000) {
			turnaround = 11;
		}
		OrderPlasmidPreparationDTO plasmidPreparation = serviceDTO
				.getPlasmidPreparation();
		OrderCustCloningDTO cloning = serviceDTO.getCustCloning();
		
		if(plasmidPreparation != null){
			BigDecimal prepWeight = plasmidPreparation.getPrepWeight();
                        if(prepWeight == null){
                            prepWeight = new BigDecimal(100);
                        }
			String prepWtUom = plasmidPreparation.getPrepWtUom();
                        if(prepWtUom == null){
                            prepWtUom = "ug";
                        }
			String prepWeightWithUom = prepWeight.intValue() + " " + prepWtUom;
			String qualityGrade = plasmidPreparation.getQualityGrade();
			String additionalAnalysis = plasmidPreparation
					.getAdditionalAnalysis();
			Integer extraPrice = 0;
			
			if("10 ug".equalsIgnoreCase(prepWeightWithUom)){
				turnaround += 1;
			}else if("20 ug".equalsIgnoreCase(prepWeightWithUom)){
				turnaround += 1;
			}else if("50 ug".equalsIgnoreCase(prepWeightWithUom)){
				turnaround += 3;
			}else if("100 ug".equalsIgnoreCase(prepWeightWithUom)){
				turnaround += 3;
			}else if("2 mg".equalsIgnoreCase(prepWeightWithUom)){
				turnaround += 3;
			}
			
			if(cloning != null){
				String copyNo = cloning.getTgtCopyNo();
				if (StringUtils.isEmpty(copyNo)) {
					copyNo = "High";
				}
				Map<String, String> rules = new HashMap<String, String>();
				// 格式为 "ppQ", "ppH:ppL:LEG:UEQ"
				rules.put("4 ug", "0:0:1:1");
				rules.put("100 ug", "3:3:1:1");
				rules.put("2 mg", "7:7:1:1");
				rules.put("10 mg", "14:14:3:3");
				rules.put("20 mg", "14:14:3:3");
				rules.put("50 mg", "14:14:5:5");
				rules.put("100 mg", "21:21:5:5");
				rules.put("200 ug", "7:7:1:1");
				rules.put("500 ug", "7:7:1:1");
				rules.put("1 mg", "7:7:1:1");
				if (rules.containsKey(prepWeightWithUom)) {
					String val = rules.get(prepWeightWithUom);
					String[] valArr = val.split(":");
					Integer ppH = Integer.parseInt(valArr[0]);
					Integer ppL = Integer.parseInt(valArr[1]);
					Integer LEG = Integer.parseInt(valArr[2]);
					Integer UEG = Integer.parseInt(valArr[3]);
					if (copyNo.equalsIgnoreCase("high")) {
						extraPrice = ppH;
					} else {
						extraPrice = ppL;
					}
					if ("Advanced SC Grade".equalsIgnoreCase(qualityGrade) || "Ultralow Endotoxin".equalsIgnoreCase(qualityGrade)) {
						extraPrice += UEG;
					} else if ("SC Grade".equalsIgnoreCase(qualityGrade) || "Low Endotoxin".equalsIgnoreCase(qualityGrade)) {
						extraPrice += LEG;
					}
					if ("Bio-Burden Assay".equalsIgnoreCase(additionalAnalysis)) {
						extraPrice += 1;
					}
				}
			}
			turnaround = turnaround + extraPrice;
		}else if(cloning != null){
			turnaround += 7;
		}
		
		targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(turnaround));
		targetDateDTO.setScheduleDays(turnaround);
		return targetDateDTO;
	}

	/**
	 * 获得多肽服务的targetDate
	 * @param catalogNo
	 * @param leadTime
	 * @return
	 */
	public static TargetDateDTO getPolyAntibodyTargetDate(String catalogNo,
			Integer leadTime) {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		if (leadTime == null) {
			leadTime = 0;
		}
		Map<String, Integer> rules = new HashMap<String, Integer>();
		rules.put("SC1030", 91);
		rules.put("SC1031", 112);
		rules.put("SC1044", 126);
		rules.put("SC1247", 98);
		rules.put("SC1248", 98);
		rules.put("SC1015", 112);
		rules.put("SC1045", 112);
		rules.put("SC1050", 112);
		rules.put("SC1051", 112);
		rules.put("SC1052", 91);
		rules.put("SC1367", 98);
		rules.put("SC1368", 112);
		rules.put("SC1369", 105);
		rules.put("SC1370", 112);
		rules.put("SC1055", 126);
		rules.put("SC1056", 133);
		rules.put("SC1178", 46);
		rules.put("SC1179", 60);
		rules.put("SC1180", 65);
		if (rules.containsKey(catalogNo)) {
			targetDateDTO.setScheduleDays(rules.get(catalogNo));
			targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(rules.get(catalogNo)));
			return targetDateDTO;
		} else {
			targetDateDTO.setScheduleDays(leadTime);
			targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(leadTime));
			return targetDateDTO;
		}
	}

	/**
	 * 获得单肽服务的targetDate
	 * @param leadTime
	 * @param catalogNoSet
	 * @return DATE
	 */
	public static TargetDateDTO getMonAntibodyTargetDate(Integer leadTime,
			Set<String> catalogNoSet, String catalogNo) {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		// 特殊情况处理
		Set<String> rule1 = new HashSet<String>();
		rule1.add("SC1041");
		rule1.add("SC1110");
		Set<String> rule2 = new HashSet<String>();
		rule2.add("SC1041");
		rule2.add("SC1110");
		rule2.add("SC1043");
		Set<String> rule3 = new HashSet<String>();
		rule3.add("SC1041");
		rule3.add("SC1117");
		Set<String> rule4 = new HashSet<String>();
		rule4.add("SC1041");
		rule4.add("SC1117");
		rule4.add("SC1227");

		if (catalogNoSet.containsAll(rule2) || catalogNoSet.containsAll(rule4)) {
			if("SC1043".equals(catalogNo)){
				leadTime = 29 * 7;// 29 week
			}else if("SC1110".equals(catalogNo)){
				leadTime = 28 * 7;// 28 week
			}else if("SC1117".equals(catalogNo)){
				leadTime = 28 * 7;// 28 week
			}else if("SC1227".equals(catalogNo)){
				leadTime = 29 * 7;// 29 week
			}
		}else if (catalogNoSet.containsAll(rule1)
				|| catalogNoSet.containsAll(rule3)) {
			if("SC1110".equals(catalogNo)){
				leadTime = 28 * 7;// 28 week
			}else if("SC1117".equals(catalogNo)){
				leadTime = 28 * 7;// 28 week
			}
		}else if (leadTime == null || leadTime == 0) {
			leadTime = 150;//150 day
		}
//		if (catalogNoSet.containsAll(rule2) || catalogNoSet.containsAll(rule4)) {
//			leadTime = 29 * 7;// 29 week
//		} else if (catalogNoSet.containsAll(rule1)
//				|| catalogNoSet.containsAll(rule3)) {
//			leadTime = 28 * 7;// 28 week
//		}else if (leadTime == null || leadTime == 0) {
//			leadTime = 150;//150 day
//		}
		targetDateDTO.setScheduleDays(leadTime);
		targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(leadTime));
		return targetDateDTO;
	}

	/**
	 * 取得peptide的targetDate
	 * @param serviceDTO
	 * @return
	 */
	public static TargetDateDTO getPeptideTargetDate(OrderServiceDTO serviceDTO) {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		PeptideDTO peptide = serviceDTO.getPeptide();
		if(peptide == null || StringUtils.isBlank(peptide.getSequence())){
			return null;
		}
		int turnaround = 8;
		Integer length = peptide!=null?peptide.getSeqLength():null;
		int len = 0;
		if (length != null) {
			len = length.intValue();
		}
		if (len < 10) {
			turnaround = 8;
		} else if (len < 20) {
			turnaround = 11;
		} else if (len < 30) {
			turnaround = 14;
		} else if (len < 40) {
			turnaround = 20;
		} else if (len < 50) {
			turnaround = 25;
		} else if (len < 60) {
			turnaround = 30;
		} else if (len < 100) {
			turnaround = 60;
		} else {
			turnaround = 90;
		}
		// 根据seq判断
		int turnaround2 = 0;
		String seq = peptide.getSequence();
		Pattern pattern = Pattern.compile("\\{pTHR\\}|\\{pSER\\}|\\{pTYR\\}",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(seq);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		if (count == 1) {
			turnaround2 = 5;
		} else if (count == 2) {
			turnaround2 = 12;
		} else if (count >= 3) {
			turnaround2 = 18;
		}
		turnaround += turnaround2;
		// 根据modification判断
		int turnaround3 = 0;
		String modification = peptide.getModification();
		String[] mods = modification.split(",");
		List<String> modsList = new ArrayList<String>();
		for(String mod : mods){
			modsList.add(mod);
		}
		String NTerminal = peptide.getNTerminal();
		String CTerminal = peptide.getCTerminal();
		if(StringUtils.isNotBlank(NTerminal)){
			modsList.add(NTerminal);
		}
		if(StringUtils.isNotBlank(CTerminal)){
			modsList.add(CTerminal);
		}
		
		String pepQuantity = peptide.getQuantity();
		Pattern pattern1 = Pattern
				.compile("FITC-Ahx \\(N-Terminal\\)", Pattern.CASE_INSENSITIVE);
		
		Pattern pattern2 = Pattern
				.compile(
						"p-Nitroanilide|AMC \\(C-Terminal\\)|Alcohol \\(C-Terminal\\)",
						Pattern.CASE_INSENSITIVE);
		Pattern pattern3 = Pattern
				.compile(
						"BSA Conjugation|KLH Conjugation|BSA \\(-NH2 of N terminal\\)|BSA \\(-COOH of C terminal\\)|KLH \\(-NH2 of N terminal\\)|KLH \\(-COOH of C terminal\\)",
						Pattern.CASE_INSENSITIVE);
//		Pattern pattern2 = Pattern
//				.compile(
//						"BSA Conjugation|KLH Conjugation|p-Nitroanilide|AMC (C-Terminal)",
//						Pattern.CASE_INSENSITIVE);
		Pattern pattern4 = Pattern.compile("Head to tail Cyclic|Aldehyde \\(C-Terminal\\)|Ester \\(OEt\\) \\(C-Terminal\\)|Ester \\(OMe\\) \\(C-Terminal\\)",
				Pattern.CASE_INSENSITIVE);
//		Pattern pattern5 = Pattern.compile("Disulfide Bridge",
//				Pattern.CASE_INSENSITIVE);
		Pattern pattern6 = Pattern.compile("\\d-\\d", Pattern.CASE_INSENSITIVE);
		for (String mod : modsList) {
			if (pattern1.matcher(mod).matches()) {
				turnaround3 += 2;
			} else if (pattern2.matcher(mod).matches()) {
				turnaround3 += 7;
			} else if (pattern3.matcher(mod).matches()) {
				int qty = 0;
				String regex = "(\\d+)-(\\d+).*";
				Pattern patternQty = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
				Matcher matcherQty = patternQty.matcher(pepQuantity);
				while (matcherQty.find()) {
					qty = Integer.parseInt(matcherQty.group(2));
				}
				String regex1 = "(\\d+) g";
				String regex2 = "(\\d+) mg";
				if(pepQuantity.matches(regex1)){
					
					Pattern patternQty1 = Pattern.compile(regex1);
					Matcher matcherQty1 = patternQty1.matcher(pepQuantity);
					while (matcherQty1.find()) {
						qty = Integer.parseInt(matcherQty1.group(1)) * 1000;
					}
				}else if(pepQuantity.matches(regex2)){
					Pattern patternQty2 = Pattern.compile(regex1);
					Matcher matcherQty2 = patternQty2.matcher(pepQuantity);
					while (matcherQty2.find()) {
						qty = Integer.parseInt(matcherQty2.group(1));
					}
				}
				
				if(qty > 30){
					turnaround3 += 9;
				}else{
					turnaround3 += 7;
				}
			} else if (pattern4.matcher(mod).matches()) {
				turnaround3 += 14;
			}
		}
		String disulfideBridge = peptide.getDisulfideBridge();
		if(StringUtils.isNotBlank(disulfideBridge)){
			int bridge_count = 0;
			Matcher maBridge = pattern6.matcher(disulfideBridge);
			while (maBridge.find()) {
				bridge_count++;
			}
			if (bridge_count == 1) {
				turnaround3 += 5;
			} else if (bridge_count == 2) {
				turnaround3 += 12;
			} else if (bridge_count >= 3) {
				turnaround3 += 30;
			}
		}
		turnaround += turnaround3;
		targetDateDTO.setScheduleDays(turnaround);
		targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(turnaround));
		return targetDateDTO;
	}

	/**
	 * 获得order_pkg_service 的target date
	 * @param leadTime
	 * @param catalogNo
	 * @return
	 */
	public static TargetDateDTO getPkgServiceTargetDate(Integer leadTime,
			String catalogNo) {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		int turnArount = 0;
		//特殊条件
		Map<String, Integer> ruleMap = new HashMap<String, Integer>();
		ruleMap.put("SC1023", 14);
		ruleMap.put("SC1191", 14);
		ruleMap.put("SC1252", 7);
		ruleMap.put("SC1253", 42);
		ruleMap.put("SC1256", 42);
		ruleMap.put("SC1257", 63);
		ruleMap.put("SC1258", 63);
		ruleMap.put("SC1261", 35);
		ruleMap.put("SC1346", 63);
		ruleMap.put("SC1347", 63);
		ruleMap.put("SC1484", 56);
		if (ruleMap.containsKey(catalogNo)) {
			Integer tmpInt = ruleMap.get(catalogNo);
			turnArount += tmpInt;
		}else{
			if (leadTime == null || leadTime.intValue() < 14) {
				turnArount = 14;
			}
		}
		targetDateDTO.setScheduleDays(turnArount);
		targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(turnArount));
		return targetDateDTO;
	}
	
	public static TargetDateDTO getSirnaTargetDate() {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		int turnArount = 10;
		targetDateDTO.setScheduleDays(turnArount);
		targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(turnArount));
		return targetDateDTO;
	}
	
	public TargetDateDTO getOtherTargetDate(String catalogNo) {
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		int turnArount = 0;
		List<com.genscript.gsscm.serv.entity.Service> serviceList = servService.getServiceByCatalogNo(catalogNo);
		if(serviceList != null && serviceList.size() > 0){
			com.genscript.gsscm.serv.entity.Service service = serviceList.get(0);
			turnArount = service.getLeadTime();
		}
		targetDateDTO.setScheduleDays(turnArount);
		targetDateDTO.setTargetDate(DateUtils.dayBefore2Date(turnArount));
		return targetDateDTO;
	}
	
	/**
	 * 通过Type查询Status列表
	 * @author Zhang Yong
	 * @param type
	 * @return
	 */
	public List<StatusList> getStatusListByType (String type) {
		if (StringUtils.isBlank(type)) {
			return null;
		}
		return this.statusListDao.getStatusListByType(type);
	}
	
	@Transactional(readOnly = true)
	public String[] getPackageDescriptions(String catalogNo){
		List<PackageDescriptions> pdList = packageDescriptionsDao.findBy("catNo", catalogNo);
		if(pdList != null && pdList.size() > 0){
			String[] arrStr;
			for(PackageDescriptions p : pdList){
				String desc = p.getDescription();
				arrStr = StringUtils.splitByWholeSeparatorPreserveAllTokens(desc, "|");
				return arrStr;
			}
		}
		return null;
	}
 
}
