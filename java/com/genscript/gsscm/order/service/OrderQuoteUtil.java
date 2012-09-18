package com.genscript.gsscm.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.olddb.dao.MarkerDao;
import com.genscript.gsscm.olddb.entity.Marker;
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.shipment.dao.ShipMethodDao;
import com.genscript.gsscm.shipment.dao.ShipRateCustomerBasicDao;
import com.genscript.gsscm.shipment.dao.ShipRateDao;
import com.genscript.gsscm.shipment.entity.ShipMethod;
import com.genscript.gsscm.shipment.entity.ShipRate;
import com.genscript.gsscm.shipment.entity.ShipRateCustomerBasic;
/**
 * Order与Quote共同部分的相关方法的通用类.
 * @author wangsf
 *
 */
@Service
@Transactional
public class OrderQuoteUtil {
	@Autowired
	private ShipRateDao shipRateDao;
	@Autowired
	private ShipRateCustomerBasicDao shipRateCustomerBasicDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private ShipMethodDao shipMethodDao;
	@Autowired
	private MarkerDao markerDao;
	/**
	 * 获得在计算package时的baseCharge属性.
	 * @param shipMethod 所有package的shipMethod是一致的， 所以可选择第一个package的shipMethod
	 * @param warehouseId 所有package的warehouseId是一致的， 所以可选择第一个package的warehouseId
	 * @param packageBillableWeight 当前package的billableWeight
	 * @param packageZone 当前package的zone.
	 * @param packageSeq 当前产生的这个package在所有这些产生的package list中的序号.
	 * @return
	 */
	private BigDecimal genPackageBaseCharge(Integer shipMethod, Integer warehouseId, Double packageBillableWeight, String packageZone, int packageSeq) {
		// 获得Package's baseCharge.
		BigDecimal charge = null;
		ShipRate shipRate = null;
		System.out.println("shipMethod: " + shipMethod);
		System.out.println("warehouseId: " + warehouseId);
		System.out.println("packageBillableWeight: " + packageBillableWeight);
		System.out.println("packageZone: " + packageZone);
		try {
			shipRate = this.shipRateDao.getShipRate(shipMethod,
					warehouseId, packageBillableWeight, packageZone);
		} catch (HibernateException hibernateEx) {
			throw new BussinessException(
					BussinessException.ERR_CAL_PACKING_MORESHIPRATE);
		}
		if (shipRate != null) {
			charge = shipRate.getCharge();
		} else {
			throw new BussinessException(
					BussinessException.ERR_CAL_PACKING_NOSHIPRATE);
		}
		ShipRateCustomerBasic rateBasic = this.shipRateCustomerBasicDao
				.getById(shipMethod);
		if (rateBasic != null && rateBasic.getActualChrgTp() != null 
				&& rateBasic.getActualChrgPct() != null) {
			if (("ALL").equals(rateBasic.getActualChrgTp())
					|| (("FIRST").equals(rateBasic.getActualChrgTp()) && packageSeq == 1)) {
				charge = charge.multiply(rateBasic.getActualChrgPct()).divide(
						new BigDecimal(100));
			}
		}
		return charge;
	}
	
	/**
	 * 计算Shipping fee
	 * @param shipMethod
	 * @param warehouseId
	 * @param packageBillableWeight
	 * @param packageZone
	 * @param clsId
	 * @param totalItem
	 * @param orderAmount
	 * @param packageSeq
	 * @return
	 */
	public BigDecimal getShippingFee (Integer shipMethod, Integer warehouseId, Double packageBillableWeight, 
			String packageZone, Integer clsId, String itemType, int totalQty, Double orderAmount, int packageSeq) {
		Double shippingFee = getHandlingFee(clsId, itemType, packageBillableWeight, totalQty, orderAmount);
		ShipMethod sm = shipMethodDao.getById(shipMethod);
		if (!("Fedex").equalsIgnoreCase(sm.getCarrier())) {
			return new BigDecimal(shippingFee);
		}
		packageBillableWeight = packageBillableWeight==null?0.0:packageBillableWeight;
		BigDecimal charge = genPackageBaseCharge(shipMethod, warehouseId, packageBillableWeight, packageZone, packageSeq);
		Double fedexPrice = charge==null?0.0:charge.doubleValue();
		Double profit = 0.0;
		//Fedex Ground service
		if (shipMethod.intValue() == 6 || shipMethod.intValue() == 7) {
			profit = 1.1;
        	if (1 == packageBillableWeight.doubleValue() || 5 == packageBillableWeight.doubleValue()) {
        		shippingFee += fedexPrice*profit;
        	}
        //Fedex Express Service	
        } else {
        	if (1 == packageBillableWeight.doubleValue()) {
        		profit = 2.2;
        		if (totalQty > 10) {
        			shippingFee += fedexPrice*profit*(1+(totalQty-10)*0.02);
        		} else {
        			shippingFee += fedexPrice*profit;
        		}
        	} else if (3 == packageBillableWeight.doubleValue() || 5 == packageBillableWeight.doubleValue()){
        		profit = 3.2;
        		if (totalQty > 5) {
        			shippingFee += fedexPrice*profit*(1+(totalQty-5)*0.1);
        		} else {
        			shippingFee += fedexPrice*profit;
        		}
        	} else if (15 == packageBillableWeight.doubleValue() || 25 == packageBillableWeight.doubleValue()) {
        		profit = 3.2;
        		if (totalQty > 1) {
        			shippingFee += fedexPrice*profit*(1+(totalQty-1)*0.3);
        		} else {
        			shippingFee += fedexPrice*profit;
        		}
        	}
        }
		return new BigDecimal(shippingFee);
	}
	
	/**
	 * 计算Handling Fee
	 * @author Zhang Yong
	 * @param clsId
	 * @param packageBillableWeight
	 * @param totalItem
	 * @param orderAmount
	 * @return
	 */
	public Double getHandlingFee (Integer clsId, String itemType, Double packageBillableWeight, int totalQty, Double orderAmount) {
		Double handlingFee = 0.0;
		Double firstHandlingFee = 0.0;
		Double secondHandlingFee = 0.0;
		orderAmount = orderAmount== null?0.0:orderAmount;
		if (QuoteItemType.SERVICE.value().equals(itemType)) {
			Map<String, String> geneMap = new HashMap<String,String>();
				geneMap.put("3", null);
				geneMap.put("4", null);
				geneMap.put("5", null);
				geneMap.put("6", null);
				geneMap.put("7", null);
				geneMap.put("8", null);
				geneMap.put("9", null);
				geneMap.put("10", null);
				geneMap.put("38", null);
			Map<String, String> peptideMap = new HashMap<String,String>();
				peptideMap.put("1", null);
				peptideMap.put("30", null);
				peptideMap.put("31", null);
				peptideMap.put("39", null);
			Map<String, String> oligoMap = new HashMap<String,String>();
				oligoMap.put("34", null);
			Map<String, String> antibodyMap = new HashMap<String,String>();
				antibodyMap.put("11", null);
				antibodyMap.put("12", null);
				antibodyMap.put("28", null);
				antibodyMap.put("36", null);
			Map<String, String> proteinMap = new HashMap<String,String>();
				proteinMap.put("2", null);
			Map<String, String> bioprocessMap = new HashMap<String,String>();
				bioprocessMap.put("14", null);
			Map<String, String> assayMap = new HashMap<String,String>();
				assayMap.put("16", null);
			Map<String, String> cellLinesMap = new HashMap<String,String>();
				cellLinesMap.put("14", null);
			//method1	
			if (geneMap.containsKey(clsId.toString()) || peptideMap.containsKey(clsId.toString()) 
					|| oligoMap.containsKey(clsId.toString())) {
				firstHandlingFee = totalQty>10?((totalQty-10)*0.5+10):10;
			} else if (antibodyMap.containsKey(clsId.toString()) || proteinMap.containsKey(clsId.toString())
					|| bioprocessMap.containsKey(clsId.toString()) || assayMap.containsKey(clsId.toString())) {
				firstHandlingFee = totalQty>3?((totalQty-3)*5.0+10):10;
			}
			//method2
			if (geneMap.containsKey(clsId.toString()) || peptideMap.containsKey(clsId.toString()) 
					|| oligoMap.containsKey(clsId.toString())) {
				secondHandlingFee = orderAmount.doubleValue()>2000?
						((orderAmount.doubleValue()-2000)%3000==0?
								(((orderAmount.doubleValue()-2000)/3000)*10+10)
								:((Math.floor((orderAmount.doubleValue()-2000)/3000)+1)*10+10))
						:10;
			} else if (antibodyMap.containsKey(clsId.toString()) || proteinMap.containsKey(clsId.toString()) 
					|| bioprocessMap.containsKey(clsId.toString()) || assayMap.containsKey(clsId.toString())) {
				secondHandlingFee = orderAmount.doubleValue()>5000?
						((orderAmount.doubleValue()-5000)%5000==0?
								(((orderAmount.doubleValue()-5000)/5000)*10+10)
								:((Math.floor((orderAmount.doubleValue()-5000)/5000)+1)*10+10))
						:10;
			}
		} else {
			if (packageBillableWeight.doubleValue() == 1) {
				firstHandlingFee = totalQty>10?((totalQty-10)*0.5+10):10;
				secondHandlingFee = orderAmount.doubleValue()>2000?
						((orderAmount.doubleValue()-2000)%3000==0?
								(((orderAmount.doubleValue()-2000)/3000)*10+10)
								:((Math.floor((orderAmount.doubleValue()-2000)/3000)+1)*10+10))
						:10;
			} else if (packageBillableWeight.doubleValue() == 3 
					|| packageBillableWeight.doubleValue() == 5 
					|| packageBillableWeight.doubleValue() == 15
					|| packageBillableWeight.doubleValue() == 25) {
				firstHandlingFee = totalQty>3?((totalQty-3)*3.0+10):10;
				secondHandlingFee = orderAmount.doubleValue()>2000?
						((orderAmount.doubleValue()-2000)%2000==0?
								(((orderAmount.doubleValue()-2000)/2000)*10+10)
								:((Math.floor((orderAmount.doubleValue()-2000)/2000)+1)*10+10))
						:10;
			}
		}
		handlingFee = firstHandlingFee > secondHandlingFee?firstHandlingFee:secondHandlingFee;
		return handlingFee;
	}
	
	/**
	 * 获得OrderItem或QuoteItem Service部分的文档.
	 * @param itemId
	 * @param docTypeList
	 * @return
	 */
	public List<Document> getItemDocList(Integer itemId,
			DocumentType[] docTypeList) {
		List<Document> docList = new ArrayList<Document>();
		for (int i = 0; i < docTypeList.length; i++) {
			List<Document> tempList = documentDao.getDocumentList(itemId,
					docTypeList[i]);
			if (tempList != null) {
				docList.addAll(tempList);
			}
		}
		return docList;
	}

    //add by zhanghuibin 取得批量的orderitem或是quoteItem文档
    public Map<Integer, List<Document>> getBatchItemDocList(String itemIds, DocumentType[] docTypeList) {
		List<Document> docList = new ArrayList<Document>();
        Map<Integer, List<Document>> documentMap = new HashMap<Integer, List<Document>>();
		for (int i = 0; i < docTypeList.length; i++) {
			List<Document> tempList = documentDao.getBatchDocumentList(itemIds, docTypeList[i]);
			if (tempList != null) {
				docList.addAll(tempList);
			}
		}
        for(Document document : docList){
            List<Document> mapList = documentMap.get(document.getRefId());
            if(mapList == null){
                List<Document> docItemList = new ArrayList<Document>();
                docItemList.add(document);
                documentMap.put(document.getRefId(), docItemList);
            }else{
                mapList.add(document);
            }
        }
		return documentMap;
	}
	
	@Transactional(readOnly = true)
	public Marker getRnaByCode(String code){
		return markerDao.findUniqueBy("code", code);
	}
}
