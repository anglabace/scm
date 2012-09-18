package com.genscript.gsscm.quote.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.basedata.dto.DropDownDTO;
import com.genscript.gsscm.basedata.dto.DropDownListDTO;
import com.genscript.gsscm.basedata.dto.SearchItemDTO;
import com.genscript.gsscm.basedata.entity.PbDropdownListOptions;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.basedata.service.PublicService;
import com.genscript.gsscm.common.constant.CatalogType;
import com.genscript.gsscm.common.constant.CurrencyType;
import com.genscript.gsscm.common.constant.DropDownListName;
import com.genscript.gsscm.common.constant.ItemSearchType;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemStatusType;
import com.genscript.gsscm.common.constant.QuoteItemType;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.SpecDropDownListName;
import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.util.ArithUtil;
import com.genscript.gsscm.common.util.MoreDetailUtil;
import com.genscript.gsscm.common.util.QuoteSessionUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.StringUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.SalesRep;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.order.dto.ItemOtherInfoForNewDTO;
import com.genscript.gsscm.order.entity.StatusList;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.product.service.ProductService;
import com.genscript.gsscm.quote.dto.QuoteItemDTO;
import com.genscript.gsscm.quote.dto.QuoteMainDTO;
import com.genscript.gsscm.quote.entity.QuoteAddress;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.service.QuoteItemService;
import com.genscript.gsscm.quote.service.QuoteService;
import com.genscript.gsscm.quoteorder.dto.AntibodyDTO;
import com.genscript.gsscm.quoteorder.dto.CustomServiceDTO;
import com.genscript.gsscm.quoteorder.dto.DnaSequencingDTO;
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
import com.genscript.gsscm.quoteorder.dto.TargetDateDTO;
import com.genscript.gsscm.quoteorder.service.QuoteOrderService;
import com.genscript.gsscm.serv.entity.ServiceIntermediate;
import com.genscript.gsscm.serv.entity.ServiceItemBean;
import com.genscript.gsscm.serv.service.ServService;
import com.genscript.gsscm.ws.WSException;

@Results( {
		@Result(name = "item_detail", location = "quote/quote_item_detail.jsp"),
		@Result(name = "detail_list", location = "quote/quote_item_detail_list.jsp"),
		@Result(name = "quote_item_list", location = "quote/quote_item_list.jsp"),
		@Result(name = "status_log_list", location = "quote/quote_item_status_log_list.jsp"),
		@Result(name = "show_update_schedule_shipment", location = "quote/quote_update_schedule_shipment.jsp")
		})
public class QuoteItemAction extends BaseAction<QuoteItemDTO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2178686698893191046L;
	private String itemId;
	private Integer custNo;
	private QuoteItemDTO quoteItem;
	private String sessQuoteNo;
	private QuoteItemDTO oldQuoteItem;
	// 用于更新item qty
	private Double amount;
	private Integer quantity;
	private String prmtCode;
	// 删除id字符串
	private String delItemIdStr;

	ItemOtherInfoForNewDTO itemOtherInfoForNewDTO;
	@Autowired
	private PublicService publicService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ServService servService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private QuoteOrderService quoteOrderService;
	@Autowired
	private QuoteItemService quoteItemService;
	@Autowired(required = false)
	private DozerBeanMapper dozer;
	// 用于显示列表
	private Map<String, QuoteItemDTO> itemMap;
	// 用于getItemOtherInfo
	private Integer storageId;
	private String status;
	// 删除id字符串
	Map<Integer, String> shipMethodMap;
	Map<String, String> locationMap;
	// 移动items
	private String itemIdFrom;
	private String itemIdTo;
	private Integer warehouseId;
	private String statusText;
	private QuoteMainDTO quote;
	private String statusNote;
	private String statusReason;
	private List<DropDownDTO> pickLocationList;
	private String tempStatusStr;
	// 用于item状态log
	private List<ProcessLogDTO> itemStatusLoglist;
	@Autowired
	private ExceptionService exceptionUtil;
	private Map<SpecDropDownListName, DropDownListDTO> specDropDownList;
	private int purchaseQuoteFlag;

	private String unitPrice;

	//project manager
	private List<SalesRep> projectManagerList;
	private List<SalesRep> altProjectManagerList;
	private Map<String, List<PbDropdownListOptions>> dropDownMap;
	private String shippingRoute;
    //add by zhanghuibin
    private Map<String, QuoteItemDTO> itemListResultMap;
	/**
	 * 更新item的qty
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateItemQty() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			if(quoteOrderService.isGifItem(quoteItem,sessQuoteNo)) {
				rt.put("message","Failed to change the gift item.");
				Struts2Util.renderJson(rt);
				return null;
			}
			quoteItem.setQuantity(quantity);
			quoteItem.setAmount(new BigDecimal(amount));
			if (unitPrice != null && !("").equals(unitPrice.trim())) {
				unitPrice = unitPrice.trim();
				quoteItem.setUnitPrice(new BigDecimal(Double
						.parseDouble(unitPrice)));
			}
			quote = (QuoteMainDTO)SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
			quoteItem = quoteItemService.getOfferQuoteItem(quote, quoteItem);
			itemMap.put(itemId, quoteItem);
			// 判断是否可以更改
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			List<Double> amountList = new ArrayList<Double>();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				amountList.add(quoteItemDTO.getAmount().doubleValue());
			}

			QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
					SessionConstant.Quote.value(), sessQuoteNo);
			if (quote.getQuotePromotion()!=null&&StringUtils.isNotEmpty(quote.getQuotePromotion().getPrmtCode())) {
				if (quoteService.checkQuoteTotal(amountList, quote.getQuotePromotion().getPrmtCode()) == false) {
					throw new BussinessException(
							BussinessException.INF_PROMOTION_CANNOT_APPLY);
				}
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());

			
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
			return null;
		}
        quoteItem.setUpdateFlag("Y");
		SessionUtil.updateOneRow(SessionConstant.QuoteItemList.value(),
				sessQuoteNo, itemId, quoteItem);
		this.updateItemDiscount(itemMap);
		Struts2Util.renderJson(this.renderDiscount());
		QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String updateItemPrice() {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			unitPrice = unitPrice.trim();
            String validStr = updateItemPriceValid();
            if(!"".equals(validStr)){
                rt.put("message", validStr);
                Struts2Util.renderJson(rt);
				return null;
            }
            //add by zhanghuibin
            if (3 == quoteItem.getClsId()) {
                if (Double.parseDouble(unitPrice) < 159) unitPrice = "159";
            }
            updateItemPriceMain(rt);
			Map<String, Double> discountMap = this.renderDiscount();
			rt.put("discountMap", discountMap);
			QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
			Struts2Util.renderJson(rt);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());

			
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
			Struts2Util.renderJson(rt);
			return null;
		}
		return null;
	}

    private String updateItemPriceValid() {
        String message = "";
        if (quoteItem == null) {
            message = "Failed to change the item Unit Price, the item is not exists.";
        } else if (!QuoteItemType.SERVICE.value().equalsIgnoreCase(quoteItem.getType())) {
            message = "Failed to change the item Unit Price, only item type is 'Product' can be change.";
        } else if (unitPrice == null || ("").equals(unitPrice) || !StringUtil.isNumeric(unitPrice.trim())) {
            message = "Failed to change the item Unit Price, please enter the valid Unit Price.";
        } else if (quoteOrderService.isGifItem(quoteItem, sessQuoteNo)) {
            message = "Failed to change the gift item.";
        }
        return message;
    }
     /*
     * zhanghuibin
     * 价格更新主方法
     * */
    private void updateItemPriceMain(Map<String, Object> rt){
			quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
			String sessUnitPrice = "";
			String sessAmount = "";
			boolean isJPY = CurrencyType.JPY.value().equals(quote.getQuoteCurrency())?true:false;
			int small = isJPY?0:2;
			sessUnitPrice = quoteItem.getUnitPrice()== null?(isJPY?"0":"0.00"):
				quoteItem.getUnitPrice().setScale(small, BigDecimal.ROUND_HALF_UP).toString();
			sessAmount = quoteItem.getAmount()== null?(isJPY?"0":"0.00"):
				quoteItem.getAmount().setScale(small, BigDecimal.ROUND_HALF_UP).toString();
			rt.put("unitPrice",sessUnitPrice);
			rt.put("amount",sessAmount);
			rt.put("cost",quoteItem.getCost().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			if(StringUtils.isNumeric(itemId)){
				QuoteItem dbItem = quoteService.getQuoteItemByItemId(Integer.parseInt(itemId));
				//------------------Add By Zhang Yong Start -----------------------------//
				Double dbUnitPrice = dbItem.getUnitPrice();
				Double paramUnitPrice = quoteItem.getUnitPrice().doubleValue();
				//UnitPrice有做修改时，更新orderItem的PriceChanged状态为Y，代表unitprice修改过，不再计算价格
				if (dbUnitPrice != null && paramUnitPrice != null
						&& (dbUnitPrice.doubleValue() != paramUnitPrice.doubleValue())) {
					quoteItem.setPriceChanged(OrderItemStatusType.Y.value());
				}
			}
			//------------------Add By Zhang Yong End -----------------------------//
			itemMap.put(itemId, quoteItem);
			// 判断是否可以更改
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
			List<Double> amountList = new ArrayList<Double>();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				amountList.add(quoteItemDTO.getAmount().doubleValue());
			}
			if (quote.getQuotePromotion()!=null&&StringUtils.isNotEmpty(quote.getQuotePromotion().getPrmtCode())) {
				if (!quoteService.checkQuoteTotal(amountList, quote.getQuotePromotion().getPrmtCode())) {
					throw new BussinessException(
							BussinessException.INF_PROMOTION_CANNOT_APPLY);
				}
			}
			quoteItem.setUnitPrice(new BigDecimal(Double.parseDouble(unitPrice)).setScale(small, BigDecimal.ROUND_HALF_UP));
			if (quoteItem.getBasePrice() == null || quoteItem.getBasePrice().intValue() == 0) {
				BigDecimal basePrice = orderService.getOrderChangeCurrency(quoteItem.getUnitPrice(), null, quote.getQuoteCurrency(), CurrencyType.USD.value());
				quoteItem.setBasePrice(basePrice);
			}
			quoteItem.setAmount(new BigDecimal(Double.parseDouble(unitPrice)*quoteItem.getQuantity()).setScale(small, BigDecimal.ROUND_HALF_UP));
            //add by zhanghuibin gene需要计算 price
            if(quoteItem.getClsId() == 3 && quoteItem.getGeneSynthesis() != null){
                Double seql = Double.valueOf(quoteItem.getGeneSynthesis().getSeqLength());
                Double uprice = quoteItem.getUnitPrice().doubleValue();
                quoteItem.getGeneSynthesis().setBpPrice(ArithUtil.div(uprice, seql, 2));
            }
            rt.put("unitPrice",quoteItem.getUnitPrice().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			rt.put("amount",quoteItem.getAmount().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			rt.put("cost",quoteItem.getCost().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
			quoteItem.setTbdFlag("0");
            quoteItem.setUpdateFlag("Y");
            this.updateItemDiscount(itemMap);
    }

    /*
    * add by zhanghuibin
    * Sales Information tab 中更新TBD Unit Price价格
    * */
    @SuppressWarnings("unchecked")
	public String updateQuoteUnitPrice() {
        itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
        unitPrice = unitPrice.trim();
        Map<String, Object> rt = new HashMap<String, Object>();
        if (unitPrice != null && !"".equals(unitPrice)) {
            @SuppressWarnings("rawtypes")
			Iterator it = itemMap.keySet().iterator();
            while (it.hasNext()) {
                String key = (String)it.next();
                QuoteItemDTO item = itemMap.get(key);
                if ("1".equals(item.getTbdFlag())) {
                    itemId = key;
                    quoteItem = itemMap.get(key);
                    String validStr = updateItemPriceValid();
                    if (!"".equals(validStr)) {
                        rt.put("message", validStr);
                        break;
                    }
                    if( 3 == item.getClsId()){
                        if(Double.parseDouble(unitPrice) < 159) unitPrice = "159";
                    }
                    updateItemPriceMain(rt);
                }
            }
            rt.put("message", "ok");
        } else {
            rt.put("message", "fail");
        }
        Struts2Util.renderJson(rt);
        return null;
    }

	/**
	 * Item列表中修改Item的Cost
	 * @author Zhang Yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateItemCost () {
		Map<String, Object> rt = new HashMap<String, Object>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (quoteItem == null) {
			rt.put("message", "Failed to change the item cost, the item is not exists.");
			Struts2Util.renderJson(rt);
			return null;
		}
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		String sessCost = "";
		boolean isJPY = CurrencyType.JPY.value().equals(quote.getQuoteCurrency())?true:false;
		int small = isJPY?0:2;
		sessCost = quoteItem.getCost()== null?(isJPY?"0":"0.00"):
			quoteItem.getCost().setScale(small, BigDecimal.ROUND_HALF_UP).toString();
		rt.put("cost",sessCost);
		if (quoteOrderService.isGifItem(quoteItem, sessQuoteNo)) {
			rt.put("message", "Failed to change the gift item.");
			Struts2Util.renderJson(rt);
			return null;
		}
		String cost = Struts2Util.getParameter("cost");
		if (cost == null || ("").equals(cost) || !StringUtil.isNumeric(cost.trim())) {
			rt.put("message", "Failed to change the item cost, please enter the valid cost.");
			Struts2Util.renderJson(rt);
			return null;
		}
		quoteItem.setCost(new BigDecimal(Double.parseDouble(cost)).setScale(small, BigDecimal.ROUND_HALF_UP));
        quoteItem.setTbdFlag("0");
		quoteItem.setUpdateFlag("Y");
		QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		rt.put("cost",quoteItem.getCost().toString());
		rt.put("unitPrice",quoteItem.getUnitPrice().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
    	rt.put("amount",quoteItem.getAmount().setScale(small, BigDecimal.ROUND_HALF_UP).toString());
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 更新Item的ScheduleShipment
	 * @author Zhang Yong
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateItemScheduleShipment () {
		Map<String, Object> rt = new HashMap<String, Object>();
		String newScheduleShipment = (String) ServletActionContext.getRequest().getParameter("newScheduleShipment");
		String reason = (String) ServletActionContext.getRequest().getParameter("reason");
		if (StringUtils.isBlank(sessQuoteNo) || StringUtils.isBlank(itemId) 
				|| StringUtils.isBlank(newScheduleShipment) || !StringUtils.isNumeric(newScheduleShipment)
				|| StringUtils.isBlank(reason)) {
			Struts2Util.renderJson(rt);
			return null;
		}
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		quoteItem = itemMap.get(itemId);
		if (quoteItem == null) {
			Struts2Util.renderJson(rt);
			return null;
		}
		if (quoteItem.getShipSchedule() == null || Integer.parseInt(newScheduleShipment) != quoteItem.getShipSchedule()) {
			quoteItem.setShipSchedule(Integer.parseInt(newScheduleShipment));
			quoteItem.setReason(reason);
		}
//        quoteItem.setUpdateFlag("Y");
		rt.put("newScheduleShipment",newScheduleShipment);
		Struts2Util.renderJson(rt);
		return null;
	}
	
	/**
	 * 跳转到修改ItemScheduleShipment页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showItemScheduleShipment () {
		if (StringUtils.isNotBlank(sessQuoteNo) || StringUtils.isNotBlank(itemId)) {
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
		}
		return "show_update_schedule_shipment";
	}
	
	/**
	 * 切换storage 的更新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getItemOtherInfo() throws Exception {
		prepareModel();
		String tempStatus = quoteItem.getStatus();
		if (tempStatus.equals(OrderItemStatusType.BO.value())) {
			quoteItem.setStatus(QuoteItemStatusType.CM.value());
			quoteItem.setStatusText("Committed");
		}
		quoteItem.setStorageId(this.storageId);
		QuoteMainDTO quoteMainDTO = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		String otherInfo = quoteService.getItemOtherInfo(quoteItem.getType(), quoteItem.getStatus(), quoteItem.getCatalogNo(), quoteItem.getStatusReason(), null, quoteMainDTO.getCustNo());
		// 更新session
		quoteItem.setOtherInfo(otherInfo);
		quoteItem.setItemStatus(quoteItem.getStatus());
//        quoteItem.setUpdateFlag("Y");
		SessionUtil.updateOneRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, itemId, quoteItem);
		Struts2Util.renderJson(quoteItem);
		return null;

	}

	/**
	 * 显示item 列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Exception {
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
        itemListResultMap = new LinkedHashMap<String, QuoteItemDTO>();
        ArrayList<String[]> resultTempList = new ArrayList<String[]>();
        if (itemMap != null && !itemMap.isEmpty()) {
            for (String key : itemMap.keySet()) {
                QuoteItemDTO dto = itemMap.get(key);
                if (StringUtils.isBlank(dto.getParentId())) {
                    String[] keys = new String[2];
                    keys[0] = key;
                    resultTempList.add(keys);
                } else {
                    boolean re = setAllItemInArray(resultTempList, dto.getParentId(), key);
                    if(!re){
                        String[] keys = new String[2];
                        keys[0] = dto.getParentId();
                        resultTempList.add(keys);
                        setAllItemInArray(resultTempList, dto.getParentId(), key);
                    }
                }
            }
        }
        for (String[] keys : resultTempList) {
        	itemListResultMap.put(keys[0], quoteItemService.getItemMoreDetail(itemMap.get(keys[0])));
            String childKeys = keys[1];
            if(childKeys != null){
                childKeys = keys[1].substring(1, keys[1].length());
            }else {
                continue;
            }
            for(int i = 0; i < childKeys.split(",").length; i++){
                itemListResultMap.put(childKeys.split(",")[i], quoteItemService.getItemMoreDetail(itemMap.get(childKeys.split(",")[i])));
            }
        }
		return "quote_item_list";
	}


    /*
    * 在已经生成的Array中查找父item,
    * 返回false的情况为：
    * HashMap 先得到了子item，在Array里查找时候，父级item还没有被加入
    * */
    boolean setAllItemInArray(ArrayList<String[]> itemArray, String parentId, String ckey){
        if(parentId.equals(ckey)) return true;
        boolean resultFlag = false;
        for(int i = itemArray.size() -1; i >= 0 ; i--){
            String key = itemArray.get(i)[0];
            String childKeys = itemArray.get(i)[1] == null ? "" : itemArray.get(i)[1];
            if(key.equals(parentId) || childKeys.indexOf("," + parentId) >= 0){
                itemArray.get(i)[1] = childKeys + "," + ckey ;
                resultFlag = true;
            }
        }
        return resultFlag;
    }

    public void setAllItemInStack(Stack<QuoteItemDTO> itemStack, String key){
        QuoteItemDTO itemDTO = itemMap.get(key);
        if(itemDTO.getParentId() == null || "".equals(itemDTO.getParentId())){
            itemStack.push(itemDTO);
        }else{
            itemStack.push(itemDTO);
            setAllItemInStack(itemStack, itemMap.get(key).getParentId());
        }
    }

	/**
	 * 更新item 的状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateItemStatus() {
		try {
			prepareModel();
			Map<String, QuoteItemDTO> quoteItemMap = getGroupItemMap(itemId, quoteItem.getParentId());
			Iterator<Entry<String, QuoteItemDTO>> it = quoteItemMap.entrySet().iterator();
			Map<String, ItemOtherInfoForNewDTO> rt = new HashMap<String, ItemOtherInfoForNewDTO>();
			QuoteMainDTO quoteMainDTO = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
			String customerCompany = customerService.getCustomerCompany(quoteMainDTO.getCustNo(), null);
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				String tmpItemId = entry.getKey();
				QuoteItemDTO quoteItemDTO = entry.getValue();
				itemOtherInfoForNewDTO = new ItemOtherInfoForNewDTO();
				String otherInfo = quoteService.getItemOtherInfo(QuoteItemType.valueOf(quoteItemDTO.getType()).value(), QuoteItemStatusType.fromValue(quoteItem.getStatus()).value(), quoteItem.getCatalogNo(), quoteItem.getStatusReason(), customerCompany, quoteMainDTO.getCustNo());
				itemOtherInfoForNewDTO.setItemStatus(status);
				itemOtherInfoForNewDTO.setItemStatusText(statusText);
				itemOtherInfoForNewDTO.setOtherInfo(otherInfo);
				quoteItemDTO.setOtherInfo(otherInfo);
				quoteItemDTO.setStatus(status);
				quoteItemDTO.setStatusText(statusText);
				quoteItemDTO.setStatusNote(statusNote);
				quoteItemDTO.setStatusReason(statusReason);
//                quoteItemDTO.setUpdateFlag("Y");
				SessionUtil.updateOneRow(SessionConstant.QuoteItemList.value(),
						sessQuoteNo, tmpItemId, quoteItemDTO);
				rt.put(tmpItemId, itemOtherInfoForNewDTO);
			}
			this.autoChangeMainStatus(status, statusText);
			Struts2Util.renderJson(rt);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据item的状态更新quote的状态
	 *
	 * @param mainStatus
	 * @param mainStatusText
	 */
	@SuppressWarnings("unchecked")
	private void autoChangeMainStatus(String mainStatus, String mainStatusText) throws Exception {
		Map<String, QuoteItemDTO> quoteItemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (quoteItemMap == null || quoteItemMap.size() == 0
				|| mainStatus == null) {
			return;
		}
		Iterator<Entry<String, QuoteItemDTO>> it = quoteItemMap.entrySet()
				.iterator();
		boolean changeFlag = true;
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			if (!quoteItemDTO.getStatus().equalsIgnoreCase(mainStatus)) {
				changeFlag = false;
				break;
			}
		}
		if (changeFlag == true) {
			StatusList statusList = null;
			quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote
					.value(), sessQuoteNo);
			if (mainStatus.equals("CM")) {
				statusList = quoteService.getStatusDetailByTypeAndCode("Q", "OP");
				if (statusList == null) {
					throw new NullPointerException();
				}
				quote.setStatus("OP");
				quote.setStatusText(statusList.getName());
			} else if (mainStatus.equalsIgnoreCase("CN")) {
				statusList = quoteService.getStatusDetailByTypeAndCode("Q", QuoteStatusType.CO.value());
				if (statusList == null) {
					throw new NullPointerException();
				}
				quote.setStatus(QuoteStatusType.CO.value());
				quote.setStatusText(statusList.getName());
			} else {
				statusList = quoteService.getStatusDetailByTypeAndCode("Q", mainStatus);
				if (statusList == null) {
					throw new NullPointerException();
				}
				quote.setStatus(mainStatus);
				quote.setStatusText(statusList.getName());
			}

		}
	}

	/**
	 * 取得相同group的item,主要用于service
	 * 
	 * @param quoteItemId
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, QuoteItemDTO> getGroupItemMap(String quoteItemId,
			String parentId) {
		Map<String, QuoteItemDTO> itemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Map<String, QuoteItemDTO> itemGroupMap = new HashMap<String, QuoteItemDTO>();
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			String tmpId = entry.getKey();
			if (quoteItemDTO.getParentId() == null) {
				quoteItemDTO.setParentId("");
			}
			if (quoteItemDTO.getParentId().equalsIgnoreCase(quoteItemId)
					|| tmpId.equalsIgnoreCase(quoteItemId)
					|| (!StringUtils.isEmpty(parentId)
							&& !parentId.equalsIgnoreCase("0") && (quoteItemDTO
							.getParentId().equalsIgnoreCase(parentId) || tmpId
							.equalsIgnoreCase(parentId)))) {
				itemGroupMap.put(tmpId, quoteItemDTO);
			}
		}
		return itemGroupMap;
	}

	/**
	 * 更新item 的discount
	 * 
	 * @param passItemMap
	 */
	private void updateItemDiscount(Map<String, QuoteItemDTO> passItemMap) {
		List<ItemDiscountDTO> discountDTOList = new ArrayList<ItemDiscountDTO>();
		Map<String, Object> rt = new HashMap<String, Object>();
		Iterator<Entry<String, QuoteItemDTO>> it = passItemMap.entrySet().iterator();
		while (it.hasNext()) {
			ItemDiscountDTO itemDiscountDTO = new ItemDiscountDTO();
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpItemId = entry.getKey();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			// 取消状态的特殊处理
			if (quoteItemDTO.getStatus().equalsIgnoreCase(
					OrderItemStatusType.CN.value())) {
				continue;
			}
			itemDiscountDTO.setAmount(quoteItemDTO.getAmount());
			itemDiscountDTO.setStatus(quoteItemDTO.getStatus());
			itemDiscountDTO.setItemId(tmpItemId);
			itemDiscountDTO.setCatalogNo(quoteItemDTO.getCatalogNo());
			discountDTOList.add(itemDiscountDTO);
		}
		if (StringUtils.isNotEmpty(prmtCode)) {
			try{
				discountDTOList = quoteService.getItemDiscount(discountDTOList, prmtCode);
			}catch (Exception e) {
				WSException exDTO = exceptionUtil.getExceptionDetails(e);
				exceptionUtil.logException(exDTO, this.getClass(), e,
						new Exception().getStackTrace()[0].getMethodName(),
						"INTF0203", SessionUtil.getUserId());
				rt.put("hasException", "Y");
				rt.put("exception", exDTO);
				Struts2Util.renderJson(rt);
			}
			for (int i = 0; i < discountDTOList.size(); i++) {
				String tmpId = discountDTOList.get(i).getItemId();
				Double tmpDiscount = discountDTOList.get(i).getDiscount()
						.doubleValue();
				QuoteItemDTO quoteItemDTO = passItemMap.get(tmpId);
				quoteItemDTO.setDiscount(new BigDecimal(tmpDiscount));
//                quoteItemDTO.setUpdateFlag("Y");
				passItemMap.put(tmpId, quoteItemDTO);
			}
			SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, passItemMap);
		}
	}

	/**
	 * 返回item的discount列表（json格式）
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Double> renderDiscount() {
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		Map<String, Double> jsonMap = new HashMap<String, Double>();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String tmpItemId = entry.getKey();
			QuoteItemDTO quoteItemDTO = entry.getValue();
			Double discount = quoteItemDTO.getDiscount().doubleValue();
			jsonMap.put(tmpItemId, discount);
		}
		return jsonMap;
	}

	/**
	 * 显示item status log
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getItemStatusHist() {
        System.out.println("--------------------------getItemStatusHist"+itemId);
		if (StringUtils.isNumeric(itemId.trim())) {
			if (itemId == "" || (itemId.length() < 1) || itemId == null) {
			} else {
				itemStatusLoglist = quoteService.getItemStatusHist(Integer
						.parseInt(itemId));
			}

		}
		return "status_log_list";
	}

	/**
	 * 显示所有item详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String detailList() throws Exception {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		warehouseId = quote.getWarehouseId();
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);

		List<DropDownDTO> shipMethodList = specDropDownList.get(
				SpecDropDownListName.SHIP_METHOD).getDropDownDTOs();
		shipMethodMap = new HashMap<Integer, String>();
		String firstMethodName = "";
		if (shipMethodList != null && !shipMethodList.isEmpty()) {
			for (int i = 0; i < shipMethodList.size(); i++) {
				if (i == 0) {
					firstMethodName = shipMethodList.get(i).getName();
				}
				shipMethodMap.put(Integer.parseInt(shipMethodList.get(i)
						.getId()), shipMethodList.get(i).getName());
			}
		}
		pickLocationList = publicService.getPickLocationList(warehouseId);
		locationMap = new HashMap<String, String>();
		if (pickLocationList != null && !pickLocationList.isEmpty()) {
			for (int i = 0; i < pickLocationList.size(); i++) {
				locationMap.put(pickLocationList.get(i).getId(),
						pickLocationList.get(i).getName());
			}
		}
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Struts2Util.getRequest().setAttribute("firstMethodName",
				firstMethodName);
		return "detail_list";
	}

	/**
	 * 取得可用的状态列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getItemStatusList() throws Exception {
		QuoteMainDTO quoteMainDTo = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		String quoteStatus = quoteMainDTo.getStatus();
		prepareModel();
		List<StatusList> statusLists = new ArrayList<StatusList>();
		if (StringUtils.isNumeric(sessQuoteNo)) {
			statusLists = publicService.getOrderOrQuoteStatusList("QI", status,
					quoteStatus);
		}
		Struts2Util.renderJson(statusLists);
		return null;

	}

	/**
	 * 删除item
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String delete() throws Exception {
		try {
			quoteItemService.deleteItem(delItemIdStr, sessQuoteNo);
			Map<String, QuoteItemDTO> quoteItemMap = (Map<String, QuoteItemDTO>) SessionUtil
				.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			this.autoChangeMainStatus("CN", "Canceled");
			this.updateItemDiscount(quoteItemMap);
//            quoteItem.setUpdateFlag("Y");
			QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得默认shipmethod
	 * 
	 * @return
	 */
	private Integer getDefaultShipMethod() {
		Integer id = null;
		if (itemMap != null && !itemMap.isEmpty()) {
			Iterator<Entry<String, QuoteItemDTO>> item = itemMap.entrySet().iterator();
			while (item.hasNext()) {
				Entry<String, QuoteItemDTO> entry = item.next();
				QuoteItemDTO quoteItemEntry = entry.getValue();
				if (quoteItemEntry != null && !QuoteItemStatusType.CN.value().equals(quoteItemEntry.getStatus()) 
						&& quoteItemEntry.getShipMethod() != null) {
					id = quoteItemEntry.getShipMethod();
					break;
				}
			}
		}
		if (id == null && quote != null && quote.getCustNo() != null) {
			CustomerDTO cust = customerService.getCustomerBase(quote.getCustNo());
			if (cust != null) {
				id = cust.getPrefShipMthd();
			}
		}
		if (id == null) {
			List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
			speclListName.add(SpecDropDownListName.SHIP_METHOD);
			Map<SpecDropDownListName, DropDownListDTO> tmpMap = publicService
					.getSpecDropDownMap(speclListName);
			id = Integer.parseInt(tmpMap.get(
					SpecDropDownListName.SHIP_METHOD).getDropDownDTOs().get(0)
					.getId());
		}
		return id;
	}

	/**
	 * 获得默认StorageId
	 * 
	 * @return
	 */
	private Integer getDefaultStorageId(Integer warehouseId) {
		if (warehouseId == null) {
			return null;
		}
		pickLocationList = publicService.getPickLocationList(warehouseId);
		if (pickLocationList != null && !pickLocationList.isEmpty()) {
			String idStr = pickLocationList.get(0).getId();
			return Integer.parseInt(idStr);
		}
		return null;
	}

	/**
	 * 用于新增item
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveSessionItem() throws Exception {
		quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		Map<String, String> retMap = new HashMap<String, String>();
		if (quote == null) {
			retMap.put("message", "Add new Item failure, The QuoteNo is not exist.");
		} else {
			if (QuoteStatusType.CN.value().equalsIgnoreCase(quote.getStatus())) {
				retMap.put("message", "Add new Item failure, The Quote's status is 'CN'.");
			}
		}
		if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		String srcQuOrderNo = Struts2Util.getParameter("srcQuOrderNo");
		String[] itemsJsonString = Struts2Util.getRequest().getParameterValues("items");
		if (itemsJsonString != null && itemsJsonString.length > 0) {
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			Map<String, QuoteItemDTO> tempItemMap = new HashMap<String, QuoteItemDTO>();
			if (itemMap == null) {
				itemMap = new LinkedHashMap<String, QuoteItemDTO>();
				SessionUtil.insertRow(SessionConstant.QuoteItemList.value(),sessQuoteNo, itemMap);
			}
			quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
			Integer shiptoAddrFlag = quote.getShiptoAddrFlag();
			QuoteAddress defaultAddress = null;
			if (shiptoAddrFlag.equals(1) || shiptoAddrFlag.equals(2)) {
				if (quote.getQuoteAddrList() != null) {
					defaultAddress = quote.getQuoteAddrList().getShipToAddr();
				}
			}
			custNo = quote.getCustNo();
			CustomerDTO customer = this.customerService.getCustomerBase(custNo);
			String customerCompany = customerService.getCustomerCompany(custNo, customer);
			String custTaxStatus = customer.getTaxExemptFlag();
			String taxId = customer.getTaxId();
			Integer defalutStorageId = this.getDefaultStorageId(quote.getWarehouseId());
			Integer defalutShipMethod = this.getDefaultShipMethod();
			Map<String, String> toItemNoIdMap = new HashMap<String, String>();
			a:for (int i = 0; i < itemsJsonString.length; i++) {
				itemId = SessionUtil.generateTempId();
				quoteItem = Struts2Util.conventJsonToJavaObj(itemsJsonString[i], QuoteItemDTO.class);
				if (!StringUtils.isEmpty(quoteItem.getParentId())) {
					QuoteItemDTO sessParentDTO = itemMap.get(quoteItem.getParentId());
					if (QuoteItemStatusType.CN.value().equalsIgnoreCase(sessParentDTO.getStatus())) {
						retMap.put("message", "Add new Item failure, The parent Item's status is 'CN'.");
						Struts2Util.renderJson(retMap);
						return NONE;
					}
				}
				////////////////////////////////
				//添加ServiceTemplate Item
				if (ItemSearchType.SERVICE_TEMPLATE_ITEM.value().equals(quoteItem.getTemplateType())) {
					itemMap = quoteItemService.saveTempItemsItem(itemMap, quote, quoteItem, srcQuOrderNo, 
							customer, defalutShipMethod, defaultAddress, sessQuoteNo);
					this.updateItemDiscount(itemMap);
					QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
					return NONE;
				}
				////////////////////////////////
				if (quoteItem.getTax() == null || ("").equals(quoteItem.getTax().toString())) {
					quoteItem.setTax(new BigDecimal(0));
				}
				if (quoteItem.getType().equalsIgnoreCase("PRODUCT")) {
					for (Iterator<String> iter = itemMap.keySet().iterator(); iter.hasNext();) {
						String key = iter.next();
						QuoteItemDTO val = (QuoteItemDTO) itemMap.get(key);
						if(val != null && val.getCatalogNo().equals(quoteItem.getCatalogNo())){
							val.setQuantity(val.getQuantity() + quoteItem.getQuantity());
							val.setAmount(val.getUnitPrice().multiply(new BigDecimal(val.getQuantity())));
//							quoteItem = quoteItemService.getOfferQuoteItem(quote, val);
							break a;
						}
					}
				}
//				quoteItem = quoteItemService.getOfferQuoteItem(quote, quoteItem);
				tempItemMap.put(itemId, quoteItem);
				String srcQuoteItemNo = quoteItem.getSrcItemNo();
				if (StringUtils.isNotEmpty(srcQuoteItemNo)) {
					toItemNoIdMap.put(srcQuoteItemNo.toString(), itemId);
				}

				SearchItemDTO searchItemDTO = null;
				if (QuoteItemType.PRODUCT.value().equalsIgnoreCase(quoteItem.getType())) {
					searchItemDTO = productService.getSearchItemInfo(quoteItem.getCatalogNo());
					BigDecimal unitCost = productService.getUnitCost(quoteItem.getCatalogNo());
					if(unitCost == null)
						unitCost = new BigDecimal(0.0);
					quoteItem.setCost(unitCost);
					quoteItem.setShipSchedule(searchItemDTO.getScheduleShip());
				} else {
					searchItemDTO = servService.getSearchItemInfo(quoteItem.getCatalogNo(), custNo);
					quoteItem.setCost(new BigDecimal(0.0));
					if(quoteItem!=null&&quoteItem.getParentId()!=null&&StringUtils.isNotEmpty(quoteItem.getParentId())) {
						QuoteItemDTO parentItem = null;
						if(StringUtil.isNumeric(quoteItem.getParentId())) {
							parentItem = this.quoteItemService.findById(Integer.parseInt(quoteItem.getParentId()));
						} else {
							parentItem = (QuoteItemDTO)itemMap.get(quoteItem.getParentId());
						}
						if(parentItem!=null) {
							String parentType = MoreDetailUtil.getServiceNameByClsId(parentItem.getClsId());
							if("geneSynthesis".equals(parentType)) {
								quoteItem.setSize(new Double(4));
								quoteItem.setSizeUom("ug");
							}
						}
						
					}
				}
				quoteItem.setTypeText(quoteItem.getType() + " - "+ searchItemDTO.getClsName());
				quoteItem.setClsId(searchItemDTO.getClsId());
				quoteItem.setBasePrice(quoteItem.getUnitPrice());
				if(quoteItem.getBasePrice()==null) {
					quoteItem.setBasePrice(new BigDecimal(0));
				}
				// 为了能保存QuoteItem设置的shipTOAddrId假数据
				// quoteItem.setShiptoAddrId(6276);
				if ("Y".equalsIgnoreCase(custTaxStatus) && !StringUtils.isEmpty(taxId)) {
					quoteItem.setTaxStatus("N");
				} else {
					quoteItem.setTaxStatus(searchItemDTO.getTaxStatus());
				}
				quoteItem.setShortDesc(searchItemDTO.getDescription());
				quoteItem.setLongDesc(searchItemDTO.getFullDesc());
				quoteItem.setSellingNote(searchItemDTO.getSellingNote());
				quoteItem.setStatus(QuoteItemStatusType.CM.value());
				quoteItem.setStatusText("Committed");
				String otherInfo = quoteService.getItemOtherInfo(QuoteItemType.valueOf(quoteItem.getType()).value(), QuoteItemStatusType.fromValue(quoteItem.getStatus()).value(), quoteItem.getCatalogNo(), quoteItem.getStatusReason(), customerCompany, quote.getCustNo());
				quoteItem.setOtherInfo(otherInfo);
				String preParentId = quoteItem.getParentId();
				if (QuoteItemType.SERVICE.value().equalsIgnoreCase(quoteItem.getType())) {
					this.setServiceForItem(quoteItem, MoreDetailUtil.getServiceNameByClsId(quoteItem.getClsId()));
					if (!StringUtils.isEmpty(quoteItem.getParentId())) {
						preParentId = quoteItemService.getPreParentServiceId(itemMap, quoteItem);
						quoteItem.setPreParentId(preParentId);
					}
				}
				quoteItem.setShipMethod(defalutShipMethod);
				quoteItem.setStorageId(defalutStorageId);
				quoteItem.setShipToAddress(defaultAddress);
				quoteItem.setNameShow(quoteItem.getName());
				if (!StringUtils.isEmpty(quote.getQuoteCurrency())) {
					quoteItem.setCurrencyCode(quote.getQuoteCurrency());
					quoteItem = dozer.map(quoteItem, QuoteItemDTO.class);
					Double rate = publicService.getRateByCurrencyToBaseCurrency(quote.getQuoteCurrency(), quote.getExchRateDate());
					if (rate != null && quoteItem.getUnitPrice() != null) {
						int small = CurrencyType.JPY.value().equalsIgnoreCase(quote.getQuoteCurrency())?0:2;
						quoteItem.setBasePrice(quoteItem.getUnitPrice().multiply(new BigDecimal(rate)).setScale(small, BigDecimal.ROUND_HALF_UP));
					}
				}
				quoteItem.setAmount(quoteItem.getUnitPrice().multiply(new BigDecimal(quoteItem.getQuantity())));
				this.insertNewChild(itemMap, preParentId, itemId, quoteItem);
				//如果添加的Service的Quantity大于1，例如Gene下添加Muta
//				if (QuoteItemType.SERVICE.value().equalsIgnoreCase(quoteItem.getType()) && quoteItem.getQuantity() >1) {
//					String morePreParentId = null;
//					for (int qtyNum=1;qtyNum<quoteItem.getQuantity();qtyNum++) {
//						if (morePreParentId == null) {
//							morePreParentId = itemId;
//						}
//						String moreItemId = SessionUtil.generateTempId();
//						QuoteItemDTO moreOrderItem = dozer.map(quoteItem, QuoteItemDTO.class);
//						moreOrderItem.setItemNo(null);
//						moreOrderItem.setQuantity(1);
//						itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
//						this.insertNewChild(itemMap, morePreParentId, moreItemId, moreOrderItem);
//						morePreParentId = moreItemId;
//					}
//				}
				if (QuoteItemType.SERVICE.value().equals(quoteItem.getType()) && StringUtils.isEmpty(quoteItem.getParentId()) 
						&& (quoteItem.getClsId() == 11 || quoteItem.getClsId() == 12 || quoteItem.getClsId() == 28)) {
					this.getNewChildSerItem(itemId, quoteItem, customer);
				}
			}

			if (StringUtils.isNotEmpty(srcQuOrderNo)) {
				Map<String, QuoteItem> itemNoMap = new HashMap<String, QuoteItem>();
				Map<String, String> itemIdMap = new HashMap<String, String>();
				Integer srcQuoteNo = Integer.parseInt(srcQuOrderNo);
				List<QuoteItem> srcQuoteItemList = quoteService.getQuoteAllItemList(srcQuoteNo);
				for (QuoteItem quoteItem : srcQuoteItemList) {
					itemNoMap.put(quoteItem.getItemNo().toString(), quoteItem);
					itemIdMap.put(quoteItem.getQuoteItemId().toString(),quoteItem.getItemNo().toString());
				}
				Iterator<Entry<String, QuoteItemDTO>> it = tempItemMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, QuoteItemDTO> entry = it.next();
					QuoteItemDTO tmpItemDTO = entry.getValue();
					String srcQuoteItemNo = tmpItemDTO.getSrcItemNo();
					if (StringUtils.isNotEmpty(srcQuoteItemNo)) {
						QuoteItem srcQuoteItem = itemNoMap.get(srcQuoteItemNo);
						Integer parentId = srcQuoteItem.getParentId();
						if (parentId != null) {
							String parentItemNoStr = itemIdMap.get(parentId.toString());
							String parentItemId = toItemNoIdMap.get(parentItemNoStr);
							if (StringUtils.isNotEmpty(parentItemId)) {
								QuoteItemDTO parentItem = tempItemMap.get(parentItemId);
								if (parentItem.getClsId() == 3 && tmpItemDTO.getClsId() == 9) {
									parentItem.getGeneSynthesis().setCloningFlag("Y");
								}
								if (parentItem.getClsId() == 3 && tmpItemDTO.getClsId() == 10) {
									parentItem.getGeneSynthesis().setPlasmidPrepFlag("Y");
								}
								if (parentItem.getClsId() == 3 && tmpItemDTO.getClsId() == 4) {
									parentItem.getGeneSynthesis().setMutagenesisFlag("Y");
								}
								if (parentItem.getClsId() == 4 && tmpItemDTO.getClsId() == 9) {
									parentItem.getMutagenesis().setCloningFlag("Y");
								}
								if (parentItem.getClsId() == 4 && tmpItemDTO.getClsId() == 10) {
									parentItem.getMutagenesis().setPlasmidPrepFlag("Y");
								}
								if (parentItem.getClsId() == 11 && tmpItemDTO.getClsId() == 1) {
									PeptideDTO peptide = new PeptideDTO();
									tmpItemDTO.setPeptide(peptide);
									if(parentItem.getSubItemList() != null && parentItem.getSubItemList().size() > 0){
										parentItem.getSubItemList().add(tmpItemDTO);
									}else{
										parentItem.setSubItemList(new ArrayList<QuoteItemDTO>());
									}
								}
								if ((parentItem.getClsId() == 8 && tmpItemDTO.getClsId() == 10) 
										|| (parentItem.getClsId() == 7 && tmpItemDTO.getClsId() == 10)) {
									parentItem.getRna().setPlasmidPrepFlag("Y");
								}
								tmpItemDTO.setParentId(parentItemId);
//                                tmpItemDTO.setUpdateFlag("Y");
								//toItemNoIdMap.remove(parentItemNoStr);
							}
						}
					}
				}
				tempItemMap.clear();
			}
			this.updateItemDiscount(itemMap);
			QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		}
		return NONE;
	}

    /**
     * add by zhanghuibin
	 * 用于batch新增item
	 * quote_item!saveSessionItem.action?sessQuoteNo=964763&custNo=90669&srcQuOrderNo=
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public String saveBatchSessionItem() throws Exception {
        Integer itemNum = Integer.parseInt(ServletActionContext.getRequest().getParameter("itemNum"));
        String catalogNo = ServletActionContext.getRequest().getParameter("catalogNo");
		quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		Map<String, String> retMap = new HashMap<String, String>();
		if (quote == null) {
			retMap.put("message", "Add new Item failure, The QuoteNo is not exist.");
		} else {
			if (QuoteStatusType.CN.value().equalsIgnoreCase(quote.getStatus())) {
				retMap.put("message", "Add new Item failure, The Quote's status is 'CN'.");
			}
		}
		if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
			return NONE;
		}
		ServiceItemBean serviceItemBean = servService.getGeneServiceItem(catalogNo);
        List<ServiceItemBean> serviceItems = new ArrayList<ServiceItemBean>();
        for(int i=0;i<itemNum;i++){
            ServiceItemBean itemTemp = dozer.map(serviceItemBean, ServiceItemBean.class);
            itemTemp.setServiceId(null);
            itemTemp.setUnitPrice(itemTemp.getUnitPrice()==null?"0":itemTemp.getUnitPrice());
            serviceItems.add(itemTemp);
        }
		if (serviceItems != null && serviceItems.size() > 0) {
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
			Map<String, QuoteItemDTO> tempItemMap = new HashMap<String, QuoteItemDTO>();
			if (itemMap == null) {
				itemMap = new LinkedHashMap<String, QuoteItemDTO>();
				SessionUtil.insertRow(SessionConstant.QuoteItemList.value(),sessQuoteNo, itemMap);
			}
			quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
			Integer shiptoAddrFlag = quote.getShiptoAddrFlag();
			QuoteAddress defaultAddress = null;
			if (shiptoAddrFlag.equals(1) || shiptoAddrFlag.equals(2)) {
				if (quote.getQuoteAddrList() != null) {
					defaultAddress = quote.getQuoteAddrList().getShipToAddr();
				}
			}
			CustomerDTO customer = this.customerService.getCustomerBase(custNo);
			String custTaxStatus = customer.getTaxExemptFlag();
			String taxId = customer.getTaxId();
			Integer defalutStorageId = this.getDefaultStorageId(quote.getWarehouseId());
			Integer defalutShipMethod = this.getDefaultShipMethod();
			Map<String, String> toItemNoIdMap = new HashMap<String, String>();
			a:for (int i = 0; i < serviceItems.size(); i++) {
				itemId = SessionUtil.generateTempId();
				quoteItem = dozer.map(serviceItems.get(i), QuoteItemDTO.class);
                //处理数据
                quoteItem.setItemNo(itemMap.size());
                quoteItem.setQuantity(1);
                quoteItem.setUnitPrice(new BigDecimal(0));
                quoteItem.setAmount(quoteItem.getUnitPrice());
                quoteItem.setType(CatalogType.SERVICE.value());
                quoteItem.setCost(new BigDecimal(0));
                quoteItem.setUpSymbol("$");
                quoteItem.setSizeUom(serviceItems.get(i).getUom());
                quoteItem.setParentId("");
                quoteItem.setPreParentId("");
                quoteItem.setCatalogText(serviceItems.get(i).getCatalogId()+":");
                quoteItem.setSrcItemNo("");
                quoteItem.setTemplateType("");
				if (quoteItem.getTax() == null || ("").equals(quoteItem.getTax().toString())) {
					quoteItem.setTax(new BigDecimal(0));
				}
				tempItemMap.put(itemId, quoteItem);
				String srcQuoteItemNo = quoteItem.getSrcItemNo();
				if (StringUtils.isNotEmpty(srcQuoteItemNo)) {
					toItemNoIdMap.put(srcQuoteItemNo, itemId);
				}
				SearchItemDTO searchItemDTO = servService.getSearchItemInfo(quoteItem.getCatalogNo(), custNo);
				quoteItem.setCost(new BigDecimal(0.0));
				quoteItem.setTypeText(quoteItem.getType() + " - "+ searchItemDTO.getClsName());
				quoteItem.setClsId(searchItemDTO.getClsId());
				quoteItem.setBasePrice(quoteItem.getUnitPrice());
				if(quoteItem.getBasePrice()==null) {
					quoteItem.setBasePrice(new BigDecimal(0));
				}
				if ("Y".equalsIgnoreCase(custTaxStatus) && !StringUtils.isEmpty(taxId)) {
					quoteItem.setTaxStatus("N");
				} else {
					quoteItem.setTaxStatus(searchItemDTO.getTaxStatus());
				}
				quoteItem.setShortDesc(searchItemDTO.getDescription());
				quoteItem.setLongDesc(searchItemDTO.getFullDesc());
				quoteItem.setSellingNote(searchItemDTO.getSellingNote());
			    quoteItem.setOtherInfo("In Stock, Available");
				quoteItem.setStatus("CM");
				quoteItem.setStatusText("Committed");
				this.setServiceForItem(quoteItem, MoreDetailUtil.getServiceNameByClsId(quoteItem.getClsId()));
				quoteItem.setShipMethod(defalutShipMethod);
				quoteItem.setStorageId(defalutStorageId);
				quoteItem.setShipToAddress(defaultAddress);
				quoteItem.setNameShow(quoteItem.getName());
				if (!StringUtils.isEmpty(quote.getQuoteCurrency())) {
					quoteItem.setCurrencyCode(quote.getQuoteCurrency());
					quoteItem = dozer.map(quoteItem, QuoteItemDTO.class);
					Double rate = publicService.getRateByCurrencyToBaseCurrency(quote.getQuoteCurrency(), quote.getExchRateDate());
					if (rate != null && quoteItem.getUnitPrice() != null) {
						int small = CurrencyType.JPY.value().equalsIgnoreCase(quote.getQuoteCurrency())?0:2;
						quoteItem.setBasePrice(quoteItem.getUnitPrice().multiply(new BigDecimal(rate)).setScale(small, BigDecimal.ROUND_HALF_UP));
					}
				}
                //需要同步建立cloning 以及 plasmit 对象 , 为oligo的时候不建立gene
                //如果其余服务类型增加需修改这里
                if (searchItemDTO.getClsId() == 3) {
                    quoteItem.setGeneSynthesis(new OrderGeneSynthesisDTO());
                } else if (searchItemDTO.getClsId() == 34) {
                	quoteItem.setOligo(new OrderOligoDTO());
                }
                if (searchItemDTO.getClsId() == 34) {
                	itemId = quoteItemService.insertSpecialService(itemId, quoteItem, sessQuoteNo, itemMap);
                } else {
    				this.insertNewChild(itemMap, null, itemId, quoteItem);
                }
                //返回itemId
                if (retMap.get("itemNOs") == null) {
                    retMap.put("itemNOs", itemId);
                } else{
                    retMap.put("itemNOs", retMap.get("itemNOs") + "," + itemId);
                }
			}
			this.updateItemDiscount(itemMap);
			QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		}
        if (!retMap.isEmpty()) {
			Struts2Util.renderJson(retMap);
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
		if (StringUtils.isBlank(afterItemId)) {
			Integer itemNo = itemMap.size() + 1;
			newItem.setItemNo(itemNo);
			itemMap.put(newItemId, newItem);
		} else {
			Map<String, QuoteItemDTO> newItemMap = new LinkedHashMap<String, QuoteItemDTO>();
			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet().iterator();
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
//                    newItem.setUpdateFlag("Y");
					newItemMap.put(newItemId, newItem);
				} else {
					if (tmpItemNo.intValue() > itemNo.intValue()) {
						tmpItemDTO.setItemNo(tmpItemNo.intValue() + 1);
					}
//                    tmpItemDTO.setUpdateFlag("Y");
					newItemMap.put(tmpKey, tmpItemDTO);
				}
			}
			SessionUtil.updateRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, newItemMap);
		}
	}
	
	/**
	 * 获得新的子item
	 * @author Zhang Yong
	 * @param parentId
	 * @param parentItem
	 * @param customer
	 */
	private void getNewChildSerItem(String parentId, QuoteItemDTO parentItem, CustomerDTO customer) {
		List<ServiceIntermediate> imdList = servService.getIntermediate(parentItem.getCatalogNo(), "Y");
		if (imdList == null) {
			return;
		}
		for (ServiceIntermediate imd : imdList) {
			String intmdCalogNo = imd.getIntmdCatalogNo();
			QuoteItemDTO itemInfo = this.getBaseInfoByCatalogNo(intmdCalogNo, customer.getCustNo());
			if (itemInfo == null) {
				continue;
			}
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
//            itemInfo.setUpdateFlag("Y");
			String subItemId;
			try {
				subItemId = SessionUtil.generateTempId();
				this.insertNewChild(itemMap, null, subItemId, itemInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private QuoteItemDTO getBaseInfoByCatalogNo(String catalogNo, Integer custNo) {
		try{
		
		SearchItemDTO searchItemDTO = servService.getSearchItemInfo(catalogNo, custNo);
		
		QuoteItemDTO quoteItemDTO = new QuoteItemDTO();
		quoteItemDTO.setName(searchItemDTO.getName());
		quoteItemDTO.setNameShow(searchItemDTO.getName());
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
//        quoteItemDTO.setUpdateFlag("Y");
		return quoteItemDTO;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 初始化QuoteItem的Service
	 * 
	 * @param quoteItem
	 * @param serviceName
	 */
	private void setServiceForItem(QuoteItemDTO quoteItem, String serviceName) {
		if (serviceName.equalsIgnoreCase("geneSynthesis")) {
			quoteItem.setGeneSynthesis(new OrderGeneSynthesisDTO());
		} else if (serviceName.equalsIgnoreCase("mutagenesis")) {
			quoteItem.setMutagenesis(new OrderMutagenesisDTO());
		} else if (serviceName.equalsIgnoreCase("custCloning")) {
			quoteItem.setCustCloning(new OrderCustCloningDTO());
		} else if (serviceName.equalsIgnoreCase("plasmidPreparation")) {
			quoteItem.setPlasmidPreparation(new OrderPlasmidPreparationDTO());
		} else if (serviceName.equalsIgnoreCase("pkgService")) {
			quoteItem.setQuotePkgService(new PkgServiceDTO());
		} else if (serviceName.equals("antibody")) {
			quoteItem.setAntibody(new AntibodyDTO());
		} else if (serviceName.equals("rna")) {
			quoteItem.setRna(new RnaDTO());
		}else if(serviceName.equals("orfClone")){
			quoteItem.setOrfClone(new OrderOrfCloneDTO());
		} else if (serviceName.equals("oligo")) {
			quoteItem.setOligo(new OrderOligoDTO());
		} else if (serviceName.equals("customService")) {
			quoteItem.setCustomService(new CustomServiceDTO());
		} else if (serviceName.equals("sequencing")) {
			quoteItem.setDnaSequencing(new DnaSequencingDTO());
		} else if (serviceName.equals("mutaLib")) {
			quoteItem.setMutationLibraries(new OrderMutationLibrariesDTO());
		}
	}

	/**
	 * 显示编辑item的form
	 */
	@Override
	public String input() {
		QuoteMainDTO quote = (QuoteMainDTO) SessionUtil.getRow(SessionConstant.Quote.value(), sessQuoteNo);
		warehouseId = quote.getWarehouseId();
		// 下拉列表
		List<SpecDropDownListName> speclListName = new ArrayList<SpecDropDownListName>();
		speclListName.add(SpecDropDownListName.SHIP_METHOD);
		// speclListName.add(SpecDropDownListName.PICK_LOCATION);
		specDropDownList = publicService.getSpecDropDownMap(speclListName);
		pickLocationList = publicService.getPickLocationList(warehouseId);
		dropDownMap = new HashMap<String, List<PbDropdownListOptions>>();
		dropDownMap.put(DropDownListName.SHIPPING_ROUTE.value(),publicService.getDropdownList(DropDownListName.SHIPPING_ROUTE.value()));
		//
		if(quoteItem!=null&&QuoteItemType.SERVICE.value().equals(quoteItem.getType())) {
			altProjectManagerList = this.quoteItemService.getAllSalesRep();
		}
		if (quoteItem != null && CatalogType.PRODUCT.value().equals(quoteItem.getType()) && StringUtils.isBlank(quoteItem.getOtherInfo())) {
			String itemOtherInfo = orderService.getItemOtherInfo(quoteItem.getType(), quoteItem.getStatus(), 
					quoteItem.getCatalogNo(), quoteItem.getStatusReason(), null, quote.getCustNo());
			quoteItem.setOtherInfo(itemOtherInfo);
		}
		return "item_detail";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(itemId)) {
			itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
					SessionConstant.QuoteItemList.value(), sessQuoteNo);
			quoteItem = itemMap.get(itemId);
			// 初始化保存前的quoteitem，用于比较
			this.oldQuoteItem = new QuoteItemDTO();
			this.oldQuoteItem.setShipMethod(quoteItem.getShipMethod());
			this.oldQuoteItem.setStatus(quoteItem.getStatus());
			this.oldQuoteItem.setStorageId(quoteItem.getStorageId());
			this.oldQuoteItem.setShipToAddress(quoteItem.getShipToAddress());
			this.oldQuoteItem.setQuantity(quoteItem.getQuantity());
			if(QuoteItemType.SERVICE.value().equals(quoteItem.getType())&&quoteItem.getClsId()!=null) {
				projectManagerList = this.quoteItemService.getSalesRepByClsId(quoteItem.getClsId());
			}
		}
	}

	/**
	 * 保存item
	 */
	@Override
	public String save() throws Exception {
		if (StringUtils.isEmpty(itemId)) {
			itemId = SessionUtil.generateTempId();
		}
		if(quoteItem != null){
//            quoteItem.setUpdateFlag("Y");
			if (StringUtils.isBlank(quoteItem.getNameShow())) {
				quoteItem.setNameShow(quoteItem.getName());
			}
			SessionUtil.updateOneRow(SessionConstant.QuoteItemList.value(), sessQuoteNo, itemId, quoteItem);
		}
		if (this.checkIfRemovePackages(this.oldQuoteItem, quoteItem) == true) {
			QuoteSessionUtil.removeAllPackages(quoteService, sessQuoteNo);
		}
		return null;
	}

	/**
	 * 判断是否要remove package
	 * 
	 * @param oldItemDTO
	 * @param newItemDTO
	 * @return
	 */
	private boolean checkIfRemovePackages(QuoteItemDTO oldItemDTO,
			QuoteItemDTO newItemDTO) {
		if (oldItemDTO == null || newItemDTO == null) {
			return false;
		}
		if (!oldItemDTO.getStatus().equalsIgnoreCase(newItemDTO.getStatus())) {
			return true;
		}
		if (oldItemDTO.getShipMethod().intValue() != newItemDTO.getShipMethod()
				.intValue()) {
			return true;
		}
		if (oldItemDTO.getStorageId().intValue() != newItemDTO.getStorageId()
				.intValue()) {
			return true;
		}
		if (oldItemDTO.getQuantity().intValue() != newItemDTO.getQuantity()
				.intValue()) {
			return true;
		}
		QuoteAddress oldAddress = oldItemDTO.getShipToAddress();
		QuoteAddress newAddress = newItemDTO.getShipToAddress();
		if ((oldAddress == null && newAddress != null)
				|| (oldAddress != null && newAddress == null)) {
			return true;
		} else if (oldAddress != null && newAddress != null) {
			if (!oldAddress.getCountry().equalsIgnoreCase(
					newAddress.getCountry())) {
				return true;
			}
			if (!oldAddress.getZipCode().equalsIgnoreCase(
					newAddress.getZipCode())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 计算target date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String calculateTargetDate() {
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		QuoteItemDTO itemDTO = itemMap.get(itemId);
		TargetDateDTO targetDateDTO = new TargetDateDTO();
		if (QuoteItemType.SERVICE.value().equalsIgnoreCase(itemDTO.getType())) {
			targetDateDTO = quoteItemService.getTargetDate(itemId,
					itemMap);
		}
		Struts2Util.renderJson(targetDateDTO);
		return null;
	}
	
	/**
	 * 对于特殊的服务有Copy功能
	 * @author Zhang Yong
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String copyItem () throws Exception {
		Map<String, QuoteItemDTO> retMap = new LinkedHashMap<String, QuoteItemDTO>();
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
		if (StringUtils.isBlank(itemId) || itemMap == null || itemMap.isEmpty()) {
			return null;
		}
		QuoteItemDTO moduleItem = itemMap.get(itemId);
		if (moduleItem == null || StringUtils.isNotBlank(moduleItem.getParentId())) {
			return null;
		}
		Map<String, QuoteItemDTO> sameGroupItemMap = getGroupItemMap(itemId, null);
		if (sameGroupItemMap == null || sameGroupItemMap.isEmpty()) {
			return null;
		}
		String parentId = null;
		int itemNum = moduleItem.getItemNo();
		for (int i=0;i<sameGroupItemMap.size();i++) {
			Iterator<Entry<String, QuoteItemDTO>> iterator = sameGroupItemMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, QuoteItemDTO> entry = iterator.next();
				QuoteItemDTO sourceItemDTO = entry.getValue();
				QuoteItemDTO itemDTO = dozer.map(sourceItemDTO, QuoteItemDTO.class);
				if (itemNum == itemDTO.getItemNo().intValue()) {
					itemDTO.setQuoteItemId(null);
					itemDTO.setItemNo(null);
					String newItemId = SessionUtil.generateTempId();
					if (StringUtils.isBlank(itemDTO.getParentId())) {
						parentId = newItemId;
					} else {
						itemDTO.setParentId(parentId);
					}
//                    itemDTO.setUpdateFlag("Y");
					itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(SessionConstant.QuoteItemList.value(), sessQuoteNo);
					this.insertNewChild(itemMap, null, newItemId, itemDTO);
					itemNum = sourceItemDTO.getItemNo() + 1;
					break;
				}
			}
		}
		retMap.put(itemId, moduleItem);
		Struts2Util.renderJson(retMap);
		return null;
	}

	/**
	 * 移动item
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String exchangeItem() throws Exception {
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Map<String, QuoteItemDTO> newItemMap = new LinkedHashMap<String, QuoteItemDTO>();
		if (itemMap != null) {
			QuoteItemDTO quoteItemDTOFrom = itemMap.get(itemIdFrom);
			QuoteItemDTO quoteItemDTOTo = itemMap.get(itemIdTo);
			Integer fromNo = quoteItemDTOFrom.getItemNo();
			Integer toNo = quoteItemDTOTo.getItemNo();
			boolean downFlag = true;
			if (fromNo > toNo) {
				downFlag = false;
			}
			quoteItemDTOTo.setItemNo(fromNo);
			quoteItemDTOFrom.setItemNo(toNo);

			Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, QuoteItemDTO> entry = it.next();
				String tmpId = entry.getKey();
				QuoteItemDTO tmpDTO = entry.getValue();
				if (tmpId.equalsIgnoreCase(itemIdTo)
						|| tmpId.equalsIgnoreCase(itemIdFrom)) {
					if (downFlag == true) {
						if (tmpId.equalsIgnoreCase(itemIdFrom)) {
							newItemMap.put(itemIdTo, quoteItemDTOTo);
						} else {
							newItemMap.put(itemIdFrom, quoteItemDTOFrom);
						}
					} else {
						if (tmpId.equalsIgnoreCase(itemIdTo)) {
							newItemMap.put(itemIdFrom, quoteItemDTOFrom);
						} else {
							newItemMap.put(itemIdTo, quoteItemDTOTo);
						}
					}
//                    quoteItemDTOFrom.setUpdateFlag("Y");
				} else {
//                    tmpDTO.setUpdateFlag("Y");
					newItemMap.put(tmpId, tmpDTO);
				}
			}
		}
		SessionUtil.insertRow(SessionConstant.QuoteItemList.value(),
				sessQuoteNo, newItemMap);
		Struts2Util.renderText("SUCCESS");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String updateTargetDate() {
		quote = (QuoteMainDTO) SessionUtil.getRow(
				SessionConstant.Quote.value(), sessQuoteNo);
		itemMap = (Map<String, QuoteItemDTO>) SessionUtil.getRow(
				SessionConstant.QuoteItemList.value(), sessQuoteNo);
		Iterator<Entry<String, QuoteItemDTO>> it = itemMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, QuoteItemDTO> entry = it.next();
			String itemId = entry.getKey();
			QuoteItemDTO itemDTO = entry.getValue();
			int shipSchedule = 0;
			if (QuoteItemType.SERVICE.value().equalsIgnoreCase(itemDTO.getType())) {
				TargetDateDTO targetDateDTO = quoteItemService.getTargetDate(itemId,
						itemMap);
				if(targetDateDTO != null){
					shipSchedule = targetDateDTO.getScheduleDays();
				}
				itemDTO.setShipSchedule(shipSchedule);
			}
		}
		Struts2Util.renderText("SUCCESS");
		return null;
	}

	public Map<SpecDropDownListName, DropDownListDTO> getSpecDropDownList() {
		return specDropDownList;
	}

	public void setSpecDropDownList(
			Map<SpecDropDownListName, DropDownListDTO> specDropDownList) {
		this.specDropDownList = specDropDownList;
	}

	public String getDelItemIdStr() {
		return delItemIdStr;
	}

	public void setDelItemIdStr(String delItemIdStr) {
		this.delItemIdStr = delItemIdStr;
	}

	public String getSessQuoteNo() {
		return sessQuoteNo;
	}

	public void setSessQuoteNo(String sessQuoteNo) {
		this.sessQuoteNo = sessQuoteNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getCustNo() {
		return custNo;
	}

	public void setCustNo(Integer custNo) {
		this.custNo = custNo;
	}

	public QuoteItemDTO getQuoteItem() {
		return quoteItem;
	}

	public void setQuoteItem(QuoteItemDTO quoteItem) {
		this.quoteItem = quoteItem;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = new BigDecimal(amount).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPrmtCode() {
		return prmtCode;
	}

	public void setPrmtCode(String prmtCode) {
		this.prmtCode = prmtCode;
	}

	public Map<String, QuoteItemDTO> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, QuoteItemDTO> itemMap) {
		this.itemMap = itemMap;
	}

	public Integer getStorageId() {
		return storageId;
	}

	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<Integer, String> getShipMethodMap() {
		return shipMethodMap;
	}

	public void setShipMethodMap(Map<Integer, String> shipMethodMap) {
		this.shipMethodMap = shipMethodMap;
	}

	public Map<String, String> getLocationMap() {
		return locationMap;
	}

	public void setLocationMap(Map<String, String> locationMap) {
		this.locationMap = locationMap;
	}

	public String getItemIdFrom() {
		return itemIdFrom;
	}

	public void setItemIdFrom(String itemIdFrom) {
		this.itemIdFrom = itemIdFrom;
	}

	public String getItemIdTo() {
		return itemIdTo;
	}

	public void setItemIdTo(String itemIdTo) {
		this.itemIdTo = itemIdTo;
	}

	public List<ProcessLogDTO> getItemStatusLoglist() {
		return itemStatusLoglist;
	}

	public void setItemStatusLoglist(List<ProcessLogDTO> itemStatusLoglist) {
		this.itemStatusLoglist = itemStatusLoglist;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public QuoteMainDTO getQuote() {
		return quote;
	}

	public void setQuote(QuoteMainDTO quote) {
		this.quote = quote;
	}

	public ItemOtherInfoForNewDTO getItemOtherInfoForNewDTO() {
		return itemOtherInfoForNewDTO;
	}

	public void setItemOtherInfoForNewDTO(
			ItemOtherInfoForNewDTO itemOtherInfoForNewDTO) {
		this.itemOtherInfoForNewDTO = itemOtherInfoForNewDTO;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public List<DropDownDTO> getPickLocationList() {
		return pickLocationList;
	}

	public void setPickLocationList(List<DropDownDTO> pickLocationList) {
		this.pickLocationList = pickLocationList;
	}

	public String getTempStatusStr() {
		return tempStatusStr;
	}

	public void setTempStatusStr(String tempStatusStr) {
		this.tempStatusStr = tempStatusStr;
	}

	public int getPurchaseQuoteFlag() {
		return purchaseQuoteFlag;
	}

	public void setPurchaseQuoteFlag(int purchaseQuoteFlag) {
		this.purchaseQuoteFlag = purchaseQuoteFlag;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<SalesRep> getProjectManagerList() {
		return projectManagerList;
	}

	public void setProjectManagerList(List<SalesRep> projectManagerList) {
		this.projectManagerList = projectManagerList;
	}

	public List<SalesRep> getAltProjectManagerList() {
		return altProjectManagerList;
	}

	public void setAltProjectManagerList(List<SalesRep> altProjectManagerList) {
		this.altProjectManagerList = altProjectManagerList;
	}

	public Map<String, List<PbDropdownListOptions>> getDropDownMap() {
		return dropDownMap;
	}

	public void setDropDownMap(Map<String, List<PbDropdownListOptions>> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}

	public String getShippingRoute() {
		return shippingRoute;
	}

	public void setShippingRoute(String shippingRoute) {
		this.shippingRoute = shippingRoute;
	}

    public Map<String, QuoteItemDTO> getItemListResultMap() {
        return itemListResultMap;
    }

    public void setItemListResultMap(Map<String, QuoteItemDTO> itemListResultMap) {
        this.itemListResultMap = itemListResultMap;
    }
}
