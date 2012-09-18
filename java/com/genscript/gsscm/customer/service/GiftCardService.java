package com.genscript.gsscm.customer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.property.IndexPropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.GiftCardDao;
import com.genscript.gsscm.customer.entity.Customer;
import com.genscript.gsscm.customer.entity.GiftCard;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.purchase.dao.PurchaseClerksDao;
import com.genscript.gsscm.purchase.dto.PurchaseClerksDTO;
import com.genscript.gsscm.purchase.entity.PurchaseClerks;

@Service
@Transactional
public class GiftCardService {
	@Autowired
	private GiftCardDao giftCardDao;
	@Autowired
	private PurchaseClerksDao purchaseClerksDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DozerBeanMapper dozer;

	@Transactional(readOnly = true)
	public Page<GiftCard> searchGiftCard(Page<GiftCard> page,
			List<PropertyFilter> filters) throws Exception {
		String buyer = "";
		String email = "";
		PropertyFilter purchasedByBuyerFilter = null;
		PropertyFilter purchasedByEmailFilter = null;
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (("buyer").equals(filter.getPropertyName())) {
				buyer = filter.getPropertyValue().toString().trim();
				purchasedByBuyerFilter = filter;
				break;
			} else if (("email").equals(filter.getPropertyName())) {
				email = filter.getPropertyValue().toString().trim();
				purchasedByEmailFilter = filter;
				break;
			}
		}
		if (purchasedByBuyerFilter != null) {
			filters.remove(purchasedByBuyerFilter);
		}
		if (purchasedByEmailFilter != null) {
			filters.remove(purchasedByEmailFilter);
		}
		/*
		 * if (!("").equals(buyer)) { List<User> userlist =
		 * userDao.findUserByLoginName(buyer); if (userlist != null &&
		 * userlist.size() > 0) { Integer[] userIds = new
		 * Integer[userlist.size()]; for (int i = 0; i < userlist.size(); i++) {
		 * User user = userlist.get(i); userIds[i] = user.getUserId(); }
		 * Criterion criterionCustNo = Restrictions.in("purchasedBy", userIds);
		 * criterionList.add(criterionCustNo); } else { page.setTotalCount(0l);
		 * page.setPageNo(1); return page; }JNMJ-6H27NE-X5JQ } if
		 * (!("").equals(email)) { List<Integer> custNolist = customerDao
		 * .findCustomerByBusEmail(email); if (custNolist != null &&
		 * custNolist.size() > 0) { Integer[] custNos = new
		 * Integer[custNolist.size()]; for (int i = 0; i < custNolist.size();
		 * i++) { custNos[i] = custNolist.get(i); } Criterion criterionCustNo =
		 * Restrictions.in("custNo", custNos);
		 * criterionList.add(criterionCustNo); } else { page.setTotalCount(0l);
		 * page.setPageNo(1); return page; } }
		 */
		List<Criterion> criterions = giftCardDao
				.getCriterionListByFilters(filters);
		criterionList.addAll(criterions);
		Object[] criterionobjs = criterionList.toArray();
		Criterion[] criterionarr = new Criterion[criterionobjs.length];
		for (int i = 0; i < criterionobjs.length; i++) {
			criterionarr[i] = (Criterion) criterionobjs[i];
		}
		return this.giftCardDao.findPage(page, criterionarr);
	}

	@Transactional(readOnly = true)
	public GiftCard getGiftCardById(Integer id) {
		return this.giftCardDao.getById(id);
	}

	public void saveGiftCard(GiftCard entity) {
		this.giftCardDao.save(entity);
	}

	@Transactional(readOnly = true)
	public List<PurchaseClerksDTO> searchPurchaseClerkDTOList(String status) {
		List<PurchaseClerks> list = purchaseClerksDao
				.searchPurchaseClerkList(status);
		System.out.println(list.size());
		List<PurchaseClerksDTO> listDTO = new ArrayList<PurchaseClerksDTO>();
		if (list != null && !list.isEmpty()) {
			for (PurchaseClerks pc : list) {
				PurchaseClerksDTO dto = new PurchaseClerksDTO();
				dto = this.dozer.map(pc, PurchaseClerksDTO.class);
				User user = this.userDao.getById(pc.getClerkId());
				if (user != null) {
					dto.setClerkName(user.getLoginName());
				}
				listDTO.add(dto);
			}
		}
		System.out.println(listDTO.size());
		return listDTO;
	}

	public Page<GiftCard> searchGiftCardByValue(Page<GiftCard> page,
			Integer cardValue) {
		String FIND_BY_CARD_VALUE = "from GiftCard where sendDate is null and sentBy is null and cardValue =? order by cardValue desc";
		Page<GiftCard> giftCardPage = this.giftCardDao.findPage(page,
				FIND_BY_CARD_VALUE, cardValue);
		System.out.println(giftCardPage.getResult());
		return giftCardPage;
	}

	public Boolean updateById(Integer id, String CustNo) {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + id
				+ ">>>>>>>" + CustNo);
		if (id != null) {
			Date dd = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(dd);
			Integer sendby = SessionUtil.getUserId();
			String sql = "";
			sql = "update `customer`.`gift_card` set send_date='" + str
					+ "',sent_by=" + sendby + ",cust_no = " + CustNo
					+ " where id= " + id;
			System.out.println(">>>>>" + sql);
			int a = this.giftCardDao.getSession().createSQLQuery(sql)
					.executeUpdate();
			if (a != -1) {
				return true;
			}
		}
		return false;
	}
}
