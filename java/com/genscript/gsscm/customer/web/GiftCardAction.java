package com.genscript.gsscm.customer.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.common.util.WebUtil;
import com.genscript.gsscm.common.web.BaseAction;
import com.genscript.gsscm.customer.dto.CustPointHistoryDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.dto.GiftCardDTO;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.CustomerPointsHistory;
import com.genscript.gsscm.customer.entity.GiftCard;
import com.genscript.gsscm.customer.service.CustomerPointsHistoryService;
import com.genscript.gsscm.customer.service.CustomerService;
import com.genscript.gsscm.customer.service.GiftCardService;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.purchase.dto.PurchaseClerksDTO;
import com.genscript.gsscm.ws.WSException;

/**
 * amazon card 编辑相关Action
 * 
 * @author mingrs zhou gang 2011 9 25 after modify it. for new update
 */
@Results({
		@Result(name = "process", location = "systemsetting/amazon/amazon_card_process.jsp"),
		@Result(name = "logs", location = "systemsetting/amazon/amazon_card_logs.jsp"),
		@Result(name = "sendlist", location = "systemsetting/amazon/amazon_card_sendlist.jsp"),
		@Result(name = "SelectCardsByValue", location = "systemsetting/amazon/selectallcardsbyvalue.jsp"),
		@Result(name = "dataError", location = "systemsetting/amazon/dataError.jsp"),
		@Result(name = "input", location = "systemsetting/amazon/edit_amazon_card_process.jsp") })
public class GiftCardAction extends BaseAction<GiftCard> {

	/**
     *
     */
	private static final long serialVersionUID = 5341184600895452503L;

	@Autowired
	private ExceptionService exceptionUtil;
	@Autowired
	private GiftCardService giftCardService;
	@Autowired
	private CustomerPointsHistoryService customerPointsHistoryService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DozerBeanMapper dozer;

	private Integer id;
	private GiftCard entity;
	private Page<GiftCard> page;
	private Page<CustomerPointsHistory> cphpage;
	private Page<GiftCard> giftCardPage;
	private List<GiftCardDTO> giftCardDtoList;
	private List<PurchaseClerksDTO> puchaseClerksDTOList;
	private String CardValue;
	private String CustNo;
	private String Sendid;
	private String CardId;
	private String purchaseDatee;
	private HashMap<Integer, String> map = new HashMap<Integer, String>();

	private String purchaseByName;

	public String getPurchaseDatee() {
		return purchaseDatee;
	}

	public void setPurchaseDatee(String purchaseDatee) {
		this.purchaseDatee = purchaseDatee;
	}

	public String getPurchaseByName() {
		return purchaseByName;
	}

	public void setPurchaseByName(String purchaseByName) {
		this.purchaseByName = purchaseByName;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	private String orderNo;

	public String getSendid() {
		return Sendid;
	}

	public void setSendid(String sendid) {
		Sendid = sendid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustNo() {
		return CustNo;
	}

	public void setCustNo(String custNo) {
		CustNo = custNo;
	}

	public String sendList() {
		PagerUtil<CustomerPointsHistory> pagerUtil = new PagerUtil<CustomerPointsHistory>();
		cphpage = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		if (cphpage.getPageSize() == null
				|| cphpage.getPageSize().intValue() < 1) {
			cphpage.setPageSize(20);
		}
		if (!cphpage.isOrderBySetted()) {
			cphpage.setOrderBy("id");
			cphpage.setOrder(Page.DESC);
		}
		cphpage = this.customerPointsHistoryService.searchpointhistorylist(
				cphpage, filters);
		PageDTO pagerInfo = pagerUtil.formPage(cphpage);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "sendlist";
	}

	public String SelectCardsByValue() {
		PagerUtil<GiftCard> giftCardPagerUtil = new PagerUtil<GiftCard>();
		giftCardPage = giftCardPagerUtil.getRequestPage();
		System.out.println(">>>>>>>>>>>>>>>>>>...." + CardValue);
		if (CardValue != null && !"".equals(CardValue)
				&& (Integer.parseInt(CardValue) > 0) && orderNo != null
				&& CustNo != null && !"".equals(CustNo) && !"".equals(orderNo)) {

			if (giftCardPage.getPageSize() == null
					|| giftCardPage.getPageSize().intValue() < 1) {
				giftCardPage.setPageSize(20);
			}
			if (!giftCardPage.isOrderBySetted()) {
				giftCardPage.setOrderBy("id");
				giftCardPage.setOrder(Page.DESC);
			}
			giftCardPage = this.giftCardService.searchGiftCardByValue(
					giftCardPage, Integer.parseInt(CardValue));
			PageDTO pagerInfo = giftCardPagerUtil.formPage(giftCardPage);
			ServletActionContext.getRequest().setAttribute("pagerInfo",
					pagerInfo);
			return "SelectCardsByValue";
		} else {
			return "dataError";
		}

	}

	public String SaveAndSend() {
		Map<String, Object> rt = new HashMap<String, Object>();
		// 接受值 并且发送邮件给用户
		// System.out.println(CustNo);
		try {
			if (CardId != "" && CardId != null) {
				Boolean b = this.customerPointsHistoryService
						.updateByCustNoandcardId(Integer.parseInt(Sendid),
								CustNo);
				Boolean b2 = this.giftCardService.updateById(
						Integer.parseInt(CardId), CustNo);
				
				if (b && b2) {
					this.customerService.sendMailtoUserByCustNo(CustNo,
						Integer.parseInt(CardId));
					rt.put("message", "Save success");
					Struts2Util.renderJson(rt);
				}
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			String s1 = exDTO.getMessageDetail();
			int tmpid = s1.indexOf("sales_contact");
			if (tmpid == -1) {

			} else {
				s1 = s1.replaceAll("sales_contact", "sales_manager");
				exDTO.setMessageDetail(s1);
			}
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public Page<CustomerPointsHistory> getCphpage() {
		return cphpage;
	}

	public Page<GiftCard> getGiftCardPage() {
		return giftCardPage;
	}

	public void setGiftCardPage(Page<GiftCard> giftCardPage) {
		this.giftCardPage = giftCardPage;
	}

	public void setCphpage(Page<CustomerPointsHistory> cphpage) {
		this.cphpage = cphpage;
	}

	public String getCardValue() {
		return CardValue;
	}

	public void setCardValue(String cardValue) {
		CardValue = cardValue;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = this.giftCardService.getGiftCardById(id);
		} else {
			entity = new GiftCard();
		}

	}

	public GiftCard getModel() {
		/**
		 * 向跳转页面绑定ServiceDTO类型的输出数据。
		 */
		return entity;
	}

	private Integer purchaseBy;

	public Integer getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(Integer purchaseBy) {
		this.purchaseBy = purchaseBy;
	}

	@Override
	public String input() throws Exception {
		String status = StatusType.ACTIVE.value();
		purchaseBy = SessionUtil.getUserId();
		purchaseByName = this.userDao.getLoginName(purchaseBy);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		purchaseDatee = sdf.format(now);
		System.out.println(purchaseDatee);
		puchaseClerksDTOList = this.giftCardService
				.searchPurchaseClerkDTOList(status);
		return "input";
	}

	@Override
	public String list() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<GiftCard> pagerUtil = new PagerUtil<GiftCard>();
		page = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		PropertyFilter filter = new PropertyFilter("NULLI_custNo", null);
		filters.add(filter);
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(20);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = this.giftCardService.searchGiftCard(page, filters);
		if (!page.getResult().isEmpty()) {
			for (GiftCard giftCard : page.getResult()) {
				if (giftCard != null) {
					if (giftCard.getPurchasedBy() != null
							&& !giftCard.getPurchasedBy().equals("")) {
						User ppser = this.userDao.getById(giftCard
								.getPurchasedBy());
						giftCard.setPurchasedName(ppser != null ? ppser
								.getLoginName() : "");
					}
				}
			}
		}

		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "process";
	}

	public String logList() throws Exception {
		// 获得分页请求相关数据：如第几页
		PagerUtil<GiftCard> pagerUtil = new PagerUtil<GiftCard>();
		page = pagerUtil.getRequestPage();
		List<PropertyFilter> filters = WebUtil
				.buildPropertyFilters(ServletActionContext.getRequest());
		PropertyFilter filter = new PropertyFilter("NOTNULLI_custNo", null);
		filters.add(filter);
		// 设置默认每页显示记录条数
		if (page.getPageSize() == null || page.getPageSize().intValue() < 1) {
			page.setPageSize(20);
		}
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = this.giftCardService.searchGiftCard(page, filters);
		// String status = StatusType.ACTIVE.value();
		// puchaseClerksDTOList = this.giftCardService
		// .searchPurchaseClerkDTOList(status);
		giftCardDtoList = new ArrayList<GiftCardDTO>();
		if (!page.getResult().isEmpty()) {
			for (GiftCard giftCard : page.getResult()) {
				// CustomerDTO customer = this.customerService
				// .getCustomerDetail(giftCard.getCustNo());每次
				// 通过这种方法取得customer对象 也就是用户邮箱地址
				// 会联系erp系统 需要调用webservice 所以会很慢。
				// 现在改用别的方法：
				String busEmail = "";
				busEmail = this.customerService
						.getBusEmailByCustomerNo(giftCard.getCustNo());
				GiftCardDTO dto = this.dozer.map(giftCard, GiftCardDTO.class);
				if (busEmail != null && !"".equals(busEmail)) {
					dto.setEmail(busEmail);
				}

				if (giftCard != null) {
					if (giftCard.getSentBy() != null
							&& !"".equals(giftCard.getSentBy())) {
						User sser = this.userDao.getById(giftCard.getSentBy());
						if (sser != null) {
							dto.setSender(sser.getLoginName());
						}
					}
					if (giftCard.getPurchasedBy() != null
							&& !giftCard.getPurchasedBy().equals("")) {
						User ppser = this.userDao.getById(giftCard
								.getPurchasedBy());
						if (ppser != null) {
							dto.setPurchasePro(ppser.getLoginName());
						}
					}
				}

				giftCardDtoList.add(dto);
			}
		}
		// 把结果集中的分页信息转化为PageDTO并保存在request的pagerInfo里
		PageDTO pagerInfo = pagerUtil.formPage(page);
		ServletActionContext.getRequest().setAttribute("pagerInfo", pagerInfo);
		return "logs";
	}

	@Override
	public String save() throws Exception {
		Map<String, Object> rt = new HashMap<String, Object>();
		try {
			if (this.entity != null) {
				Integer purchasedBy1 = SessionUtil.getUserId();
				entity.setPurchasedBy(purchasedBy1);
				Date purchaseDate = new Date();
				entity.setPurchaseDate(purchaseDate);
				this.giftCardService.saveGiftCard(entity);
				rt.put("message", SUCCESS);
			} else {
				rt.put("message", "This date is null!!");
			}
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil.logException(exDTO, this.getClass(), e,
					new Exception().getStackTrace()[0].getMethodName(),
					"INTF0203", SessionUtil.getUserId());
			rt.put("hasException", "Y");
			rt.put("exception", exDTO);
		}
		Struts2Util.renderJson(rt);
		return null;
	}

	public Page<GiftCard> getPage() {
		return page;
	}

	public void setPage(Page<GiftCard> page) {
		this.page = page;
	}

	public List<PurchaseClerksDTO> getPuchaseClerksDTOList() {
		return puchaseClerksDTOList;
	}

	public void setPuchaseClerksDTOList(
			List<PurchaseClerksDTO> puchaseClerksDTOList) {
		this.puchaseClerksDTOList = puchaseClerksDTOList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEntity(GiftCard entity) {
		this.entity = entity;
	}

	public GiftCard getEntity() {
		return entity;
	}

	public List<GiftCardDTO> getGiftCardDtoList() {
		return giftCardDtoList;
	}

	public void setGiftCardDtoList(List<GiftCardDTO> giftCardDtoList) {
		this.giftCardDtoList = giftCardDtoList;
	}

}
