package com.genscript.gsscm.basedata.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.biolib.dao.PeptideAminoAcidDao;
import com.genscript.gsscm.common.BioInfoService;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.ServicePriceType;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.olddb.dao.PeptideBasePriceDao;
import com.genscript.gsscm.olddb.dao.ProductPeptideModificationDao;
import com.genscript.gsscm.quoteorder.dto.PeptideDTO;
import com.genscript.gsscm.serv.dto.ServiceItemPiceDTO;

@Service
@Transactional
public class PriceService {
	@Autowired
	private BioInfoService bioInfoService;
	@Autowired
	private PeptideAminoAcidDao peptideAminoAcidDao;
	@Autowired
	private ProductPeptideModificationDao productPeptideModificationDao;
	@Autowired
	private PeptideBasePriceDao peptideBasePriceDao;

    // add by zhanghuibin
	public ServiceItemPiceDTO getPeptidePrice(final ServiceItemPiceDTO orderServiceItemPiceDTO, boolean needGetDifcult, String currency) {
		ServiceItemPiceDTO result = new ServiceItemPiceDTO();
		String sequence;
		String servicePriceType = orderServiceItemPiceDTO.getServicePriceType();
		sequence = PublicService.getSequence(orderServiceItemPiceDTO, servicePriceType);
		PeptideDTO peptideDTO = orderServiceItemPiceDTO.getPeptideDTO();
		if (ServicePriceType.geneSynthesis.name().equals(servicePriceType)){
			//判断gene的sequence是否为难度序列，为难度序列则不计算价格，返回TBD
			Integer geneDifResult = bioInfoService.calculateGeneDifficulity(sequence);
			if (geneDifResult == null) {
			} else if (geneDifResult == 1) {
				result.setTBDFlag("1");
				return result;
			}
		} else if (ServicePriceType.peptide.name().equals(servicePriceType) && peptideDTO != null && needGetDifcult == true){
			String modification = (StringUtils.isBlank(peptideDTO.getNTerminal())?"":peptideDTO.getNTerminal())
				+ "; " + (StringUtils.isBlank(peptideDTO.getCTerminal())?"":peptideDTO.getCTerminal()) 
				+ "; " + (StringUtils.isBlank(peptideDTO.getModification())?"":peptideDTO.getModification().replace(";", "; "))
				+ "; Disulfide Bridge:" + (StringUtils.isBlank(peptideDTO.getDisulfideBridge())?"":peptideDTO.getDisulfideBridge())+ "; ";
			//判断peptide的sequence是否为难度序列，为难度序列则不计算价格，返回TBD
			Integer peptideDifResult = bioInfoService.calculatePeptideDifficulity(sequence, peptideDTO.getPurity(), modification);
			if (peptideDifResult == null) {
			} else if (peptideDifResult == 1) {
				BigDecimal zero = new BigDecimal(0);
				result.setPrice(zero);
				result.setCost(zero);
				result.setTBDFlag("1");
				return result;
			}else{
				System.out.println("*****************************"+result);
				result.setTBDFlag("0");
			}
		}
		if (ServicePriceType.peptide.name().equals(servicePriceType)){
			BigDecimal priceBd = getPeptidePrice(peptideDTO);
			BigDecimal costBd = getPeptideCost(peptideDTO);
            result.setPrice(priceBd);
			result.setCost(costBd);
			System.out.println("Price : "+priceBd.toString()+" , Cost : " + costBd.toString());
			result.setQuantity(orderServiceItemPiceDTO.getQuantity());
			return result;
		}
		
		return null;
	}


    public ServiceItemPiceDTO getPeptidePrice(final ServiceItemPiceDTO orderServiceItemPiceDTO) {
        return getPeptidePrice(orderServiceItemPiceDTO, false, CurrencyType.USD.value());
	}

	private BigDecimal getPeptidePrice(PeptideDTO peptideDTO) {
		double price = 0;
		int aa = 0;
		String sqlPrice = "price_1000";
		Integer minQty = null;
		//String sqlPrice;
		String qtyStr = peptideDTO.getQuantity();
		String minQtyRegex = "(\\d+)";
		Pattern pattern = Pattern.compile(minQtyRegex);
		Matcher matcher = pattern.matcher(qtyStr);
		if (matcher.find()){
			minQty = Integer.parseInt(matcher.group(1));
		}
		if(minQty == null){
			sqlPrice = "price_1000";
		}else if(qtyStr.indexOf("mg") > -1){
			if(minQty <= 9){
				sqlPrice = "price_9";
			}else if(minQty <= 19){
				sqlPrice = "price_19";
			}else if(minQty <= 29){
				sqlPrice = "price_29";
			}else if(minQty <= 39){
				sqlPrice = "price_39";
			}else if(minQty <= 49){
				sqlPrice = "price_49";
			}else if(minQty <= 100){
				sqlPrice = "price_100";
			}else if(minQty <= 300){
				sqlPrice = "price_300";
			}else if(minQty <= 500){
				sqlPrice = "price_500";
			}else if(minQty <= 700){
				sqlPrice = "price_700";
			}else{
				sqlPrice = "price_1000";
			}
		}else{
			if(minQty <= 1.5){
				sqlPrice = "price_1000*1.4";
			}else if(minQty <= 2.0){
				sqlPrice = "price_1000*1.7";
			}else if(minQty <= 3.0){
				sqlPrice = "price_1000*2.2";
			}else if(minQty <= 5.0){
				sqlPrice = "price_1000*3.0";
			}else if(minQty <= 10.0){
				sqlPrice = "price_1000*5.0";
			}else{
				sqlPrice = "price_1000*8.5";
			}
		}
		
		int phospho_num = 0;
		Map<String,int[]> multi_phospho_price = new HashMap<String, int[]>();
		int[] a1 = {160, 480};
		multi_phospho_price.put("price_9", a1);
		int[] a2 = {180, 540};
		multi_phospho_price.put("price_19", a2);
		int[] a3 = {200, 600};
		multi_phospho_price.put("price_29", a3);
		int[] a4 = {220, 660};
		multi_phospho_price.put("price_39", a4);
		int[] a5 = {250, 750};
		multi_phospho_price.put("price_49", a5);
		int[] a6 = {300, 900};
		multi_phospho_price.put("price_100", a6);
		int[] a7 = {350, 1050};
		multi_phospho_price.put("price_300", a7);
		int[] a8 = {400, 1200};
		multi_phospho_price.put("price_500", a8);
		int[] a9 = {450, 1350};
		multi_phospho_price.put("price_700", a9);
		int[] a10 = {500, 1500};
		multi_phospho_price.put("price_1000", a10);
		int[] a11 = {700, 2100};
		multi_phospho_price.put("price_1000*1.4", a11);
		int[] a12 = {850, 2550};
		multi_phospho_price.put("price_1000*1.7", a12);
		int[] a13 = {1100, 3300};
		multi_phospho_price.put("price_1000*2.2", a13);
		int[] a14 = {1500, 4500};
		multi_phospho_price.put("price_1000*3.0", a14);
		int[] a15 = {2500, 7500};
		multi_phospho_price.put("price_1000*5.0", a15);
		int[] a16 = {4250, 12750};
		multi_phospho_price.put("price_1000*8.5", a16);
		String peptideSeq = peptideDTO.getSequence();
		String pepRegex = "(\\{.*?\\})";
		Pattern pepPattern = Pattern.compile(pepRegex);
		Matcher pepMatcher = pepPattern.matcher(peptideSeq);	
		while(pepMatcher.find()){
			String code = pepMatcher.group(1);
			price +=  peptideAminoAcidDao.getPrice(sqlPrice, code);
			if(code.matches("(?i)\\{(pTHR|pSER|pTYR)\\}")){
				phospho_num++;
			}
		}
		
		if(phospho_num  > 1){
			price += multi_phospho_price.get(sqlPrice)[phospho_num - 2];
		}
		int aliquos = 0;
		Integer aliquoQty = peptideDTO.getAliquoteVialQty();
		if(aliquoQty != null && aliquoQty > 5){
			aliquos = aliquoQty * 2;
		}
		price = price + aliquos;
		StringBuilder modSB = new StringBuilder();
		String cTerminal = peptideDTO.getCTerminal();
		if(StringUtils.isNotBlank(cTerminal)){
			modSB.append(cTerminal).append(";");
		}
		String nTerminal = peptideDTO.getNTerminal();
		if(StringUtils.isNotBlank(nTerminal)){
			modSB.append(nTerminal).append(";");
		}
		String modification = peptideDTO.getModification();
		if(StringUtils.isNotBlank(modification)){
			modSB.append(modification).append(";");
		}
		
		String modStr = null;
		if(modSB.length()>0){
			modStr = modSB.substring(0, modSB.lastIndexOf(";"));
		}
		String[] modArr = null;
		if(modStr != null && modStr.indexOf(";") > -1){
			modArr = StringUtils.splitByWholeSeparator(modStr, ";");
		}else if(modStr != null && modStr.indexOf(",") > -1){
			modArr = StringUtils.splitByWholeSeparator(modStr, ",");
		}else if(StringUtils.isNotBlank(modStr)){
			String[] tmpArr = {modStr};
			modArr = tmpArr;
		}
		if(modArr != null){
			for(String mod : modArr){
				if(mod.matches("(?i)(KLH Conjugation on cysteine|KLH Conjugation)")){
					int klhprice = 248;
					int addrate = minQty/10;
					klhprice += 24 * addrate;
					price += klhprice;
//				}else if(!mod.matches("(?i)(Disulfide Bridge)")){
//					List<String> aplist = productPeptideModificationDao.getAaPrice(sqlPrice, mod);
//					if(aplist != null && aplist.size() > 0){
//						aa += Integer.parseInt(aplist.get(0)) ;
//						price += Double.parseDouble(aplist.get(1));
//					}
				}else{
					List<String> aplist = productPeptideModificationDao.getAaPrice(sqlPrice, mod);
					if(aplist != null && aplist.size() > 0){
						aa += Integer.parseInt(aplist.get(0)) ;
						price += Double.parseDouble(aplist.get(1));
					}
				}
			}
		}
		String disulfideBridge = peptideDTO.getDisulfideBridge();
		if(StringUtils.isNotBlank(disulfideBridge)){
			int bridgeCount = 0;
			String bridgeRegex = "\\d+-\\d+";
			Pattern bridgePattern = Pattern.compile(bridgeRegex);
			Matcher bridgeMatcher = bridgePattern.matcher(disulfideBridge);
			if(bridgeMatcher.find()){
				bridgeCount++;
			}
			String bridgeType = "";
			if(bridgeCount == 1){
				bridgeType = "Mono Disulfide Bridge";
			}else if(bridgeCount == 2){
				bridgeType = "Double Disulfide Bridge";
			}else if(bridgeCount == 3){
				bridgeType = "Triple Disulfide Bridge";
			}
			
			List<String> btlist = productPeptideModificationDao.getAaPrice(sqlPrice, bridgeType);
			if(btlist != null && btlist.size() > 0){
				aa += Integer.parseInt(btlist.get(0)) ;
				price += Double.parseDouble(btlist.get(1));
			}
		}
		String sqllen = "";
		Integer seqLen = StringUtil.calculateSeqLength(peptideDTO.getSequence());
		//Integer seqLen = peptideDTO.getSeqLength();
		if(seqLen <= 30){
			sqllen = "and length = 30";
		}else{
			sqllen = "and length = " + seqLen;
		}
		Float unitPrice = peptideBasePriceDao.getUnitPrice(peptideDTO.getPurity(), peptideDTO.getQuantity(), sqllen);
		if(unitPrice != null){
			price += ((seqLen + aa) * unitPrice);
			if(price < 120){
				price = 120.00;
			}
		}
		BigDecimal priceBd = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
		return priceBd;
	}
	
	private BigDecimal getPeptideCost(PeptideDTO peptideDTO) {
		double price = 0;
		int aa = 0;
		String sqlPrice = "";
		Integer minQty = null;
		//String sqlPrice;
		String qtyStr = peptideDTO.getQuantity();
		String minQtyRegex = "(\\d+)";
		Pattern pattern = Pattern.compile(minQtyRegex);
		Matcher matcher = pattern.matcher(qtyStr);
		if (matcher.find()){
			minQty = Integer.parseInt(matcher.group(1));
		}
		if(minQty == null){
			sqlPrice = "cost_1000";
		}else if(qtyStr.indexOf("mg") > -1){
			if(minQty <= 9){
				sqlPrice = "cost_9";
			}else if(minQty <= 19){
				sqlPrice = "cost_19";
			}else if(minQty <= 29){
				sqlPrice = "cost_29";
			}else if(minQty <= 39){
				sqlPrice = "cost_39";
			}else if(minQty <= 49){
				sqlPrice = "cost_49";
			}else if(minQty <= 100){
				sqlPrice = "cost_100";
			}else if(minQty <= 300){
				sqlPrice = "cost_300";
			}else if(minQty <= 500){
				sqlPrice = "cost_500";
			}else if(minQty <= 700){
				sqlPrice = "cost_700";
			}else{
				sqlPrice = "cost_1000";
			}
		}else{
			if(minQty <= 1.5){
				sqlPrice = "cost_1000*1.4";
			}else if(minQty <= 2.0){
				sqlPrice = "cost_1000*1.7";
			}else if(minQty <= 3.0){
				sqlPrice = "cost_1000*2.2";
			}else if(minQty <= 5.0){
				sqlPrice = "cost_1000*3.0";
			}else if(minQty <= 10.0){
				sqlPrice = "cost_1000*5.0";
			}else{
				sqlPrice = "cost_1000*8.5";
			}
		}
		
		int phospho_num = 0;
		Map<String,int[]> multi_phospho_cost = new HashMap<String, int[]>();
		int[] a1 = {80, 240};
		multi_phospho_cost.put("cost_9", a1);
		int[] a2 = {90, 270};
		multi_phospho_cost.put("cost_19", a2);
		int[] a3 = {100, 300};
		multi_phospho_cost.put("cost_29", a3);
		int[] a4 = {110, 330};
		multi_phospho_cost.put("cost_39", a4);
		int[] a5 = {125, 375};
		multi_phospho_cost.put("cost_49", a5);
		int[] a6 = {150, 450};
		multi_phospho_cost.put("cost_100", a6);
		int[] a7 = {175, 525};
		multi_phospho_cost.put("cost_300", a7);
		int[] a8 = {200, 600};
		multi_phospho_cost.put("cost_500", a8);
		int[] a9 = {225, 675};
		multi_phospho_cost.put("cost_700", a9);
		int[] a10 = {250, 750};
		multi_phospho_cost.put("cost_1000", a10);
		int[] a11 = {350, 1050};
		multi_phospho_cost.put("cost_1000*1.4", a11);
		int[] a12 = {425, 1275};
		multi_phospho_cost.put("cost_1000*1.7", a12);
		int[] a13 = {550, 1650};
		multi_phospho_cost.put("cost_1000*2.2", a13);
		int[] a14 = {750, 2250};
		multi_phospho_cost.put("cost_1000*3.0", a14);
		int[] a15 = {1250, 3750};
		multi_phospho_cost.put("cost_1000*5.0", a15);
		int[] a16 = {2125, 6375};
		multi_phospho_cost.put("cost_1000*8.5", a16);
		String peptideSeq = peptideDTO.getSequence();
		String pepRegex = "(\\{.*?\\})";
		Pattern pepPattern = Pattern.compile(pepRegex);
		Matcher pepMatcher = pepPattern.matcher(peptideSeq);	
		while(pepMatcher.find()){
			String code = pepMatcher.group(1);
			price +=  peptideAminoAcidDao.getPrice(sqlPrice, code);
			if(code.matches("(?i)\\{(pTHR|pSER|pTYR)\\}")){
				phospho_num++;
			}
		}
		
		if(phospho_num  > 1){
			price += multi_phospho_cost.get(sqlPrice)[phospho_num - 2];
		}
		int aliquos = 0;
		Integer aliquoQty = peptideDTO.getAliquoteVialQty();
		if(aliquoQty != null && aliquoQty > 5){
			aliquos = aliquoQty * 2;
		}
		price = price + aliquos;
		
		StringBuilder modSB = new StringBuilder();
		String cTerminal = peptideDTO.getCTerminal();
		if(StringUtils.isNotBlank(cTerminal)){
			modSB.append(cTerminal).append(";");
		}
		String nTerminal = peptideDTO.getNTerminal();
		if(StringUtils.isNotBlank(nTerminal)){
			modSB.append(nTerminal).append(";");
		}
		String modification = peptideDTO.getModification();
		if(StringUtils.isNotBlank(modification)){
			modSB.append(modification).append(";");
		}
		String modStr = null;
		if(modSB.length()>0){
			modStr = modSB.substring(0, modSB.lastIndexOf(";"));
		}
		String[] modArr = null;
		if(modStr != null && modStr.indexOf(";") > -1){
			modArr = StringUtils.splitByWholeSeparator(modStr, ";");
		}else if(modStr != null && modStr.indexOf(",") > -1){
			modArr = StringUtils.splitByWholeSeparator(modStr, ",");
		}else if(StringUtils.isNotBlank(modStr)){
			String[] tmpArr = {modStr};
			modArr = tmpArr;
		}
		if(modArr != null){
			for(String mod : modArr){
				if(mod.matches("(?i)(KLH Conjugation on cysteine|KLH Conjugation)")){
					int klhprice = 248;
					int addrate = minQty/10;
					klhprice += 15 * addrate;
					price += klhprice;
//				}else if(!mod.matches("(?i)(Disulfide Bridge)")){
//					List<String> aplist = productPeptideModificationDao.getAaPrice(sqlPrice, mod);
//					if(aplist != null && aplist.size() > 0){
//						if(!mod.equals("Acetylation (N-Terminal)") && !mod.equals("Fmoc(N-Terminal)") && !mod.equals("Amidation (C-Terminal)")){
//							aa += Integer.parseInt(aplist.get(0)) ;
//						}
//						price += Double.parseDouble(aplist.get(1));
//					}
				}else{
					List<String> aplist = productPeptideModificationDao.getAaPrice(sqlPrice, mod);
					if(aplist != null && aplist.size() > 0){
						if(!mod.equals("Acetylation (N-Terminal)") && !mod.equals("Fmoc(N-Terminal)") && !mod.equals("Amidation (C-Terminal)")){
							aa += Integer.parseInt(aplist.get(0)) ;
						}
						price += Double.parseDouble(aplist.get(1));
					}
				}
			}
		}
		String disulfideBridge = peptideDTO.getDisulfideBridge();
		if(StringUtils.isNotBlank(disulfideBridge)){
			int bridgeCount = 0;
			String bridgeRegex = "\\d+-\\d+";
			Pattern bridgePattern = Pattern.compile(bridgeRegex);
			Matcher bridgeMatcher = bridgePattern.matcher(disulfideBridge);
			if(bridgeMatcher.find()){
				bridgeCount++;
			}
			String bridgeType = "";
			if(bridgeCount == 1){
				bridgeType = "Mono Disulfide Bridge";
			}else if(bridgeCount == 2){
				bridgeType = "Double Disulfide Bridge";
			}else if(bridgeCount == 3){
				bridgeType = "Triple Disulfide Bridge";
			}
			
			List<String> btlist = productPeptideModificationDao.getAaPrice(sqlPrice, bridgeType);
			if(btlist != null && btlist.size() > 0){
				aa += Integer.parseInt(btlist.get(0)) ;
				price += Double.parseDouble(btlist.get(1));
			}
		}
		String sqllen = "";
		Integer seqLen = StringUtil.calculateSeqLength(peptideDTO.getSequence());
		if(seqLen <= 30){
			sqllen = "and length = 30";
		}else{
			sqllen = "and length = " + seqLen;
		}
		Float unitPrice = peptideBasePriceDao.getUnitCost(peptideDTO.getPurity(), peptideDTO.getQuantity(), sqllen);
		if(unitPrice == null){
			unitPrice = 0.0f;
		}else{
			price += ((seqLen + aa) * unitPrice);
//			if(price < 120){
//				price = 120.00;
//			}
		}
		String purity = peptideDTO.getPurity();
		if(purity.equalsIgnoreCase("Crude")){
			if(price < 15)
				price = 15.00;
		}else if(purity.equalsIgnoreCase("Desalt")){
			if(price < 25)
				price = 25.00;
		}else if(purity.contains("70")){
			if(price < 30)
				price = 30.00;
		}else if(purity.contains("75")){
			if(price < 33)
				price = 33.00;
		}else if(purity.contains("80")){
			if(price < 36)
				price = 36.00;
		}else if(purity.contains("85")){
			if(price < 40)
				price = 40.00;
		}else if(purity.contains("90")){
			if(price < 45)
				price = 45.00;
		}else if(purity.contains("95")){
			if(price < 49)
				price = 49.00;
		}else if(purity.contains("98")){
			if(price < 60)
				price = 60.00;
		}
		BigDecimal priceBd = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
		return priceBd;
	}
}
