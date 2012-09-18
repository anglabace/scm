package com.genscript.gsscm.quote.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.OrderItemStatusType;
import com.genscript.gsscm.common.constant.QuoteStatusType;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.quote.entity.QuoteItem;
import com.genscript.gsscm.quote.entity.QuoteMain;
import com.genscript.gsscm.quote.entity.QuoteProcessLog;

@Repository
public class QuoteDao extends HibernateDao<QuoteMain, Integer>{
	@Autowired
	private QuoteProcessLogDao quoteProcessLogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private QuoteItemDao quoteItemDao;
	
	private static final String INACTIVE_QUOTE_CLOSE = "update QuoteMain q set q.status =?, q.statusReason =?,q.modifyDate =?,q.modifiedBy =? where q.quoteNo =?";
	private static final String TOTAL_PRODUCTS_QUOTED = "select sum(qi.quantity) from QuoteMain q,QuoteItem qi where q.custNo =:custNo and q.quoteNo = qi.quoteNo and qi.type=:type and q.quoteNo in (:quoteNoList)";
	private static final String GET_SHIPTO_ID = "select q.shiptoAddrId from QuoteMain q where q.quoteNo=? and q.custNo=?";
	private static final String INACTIVE_QUOTE_ITEM = "update QuoteItem q set q.status=?, q.statusReason =?, q.modifyDate =?,q.modifiedBy =? where q.quoteItemId =?";
	private static final String CUST_HAVE_PRMT="select count(DISTINCT t1.cust_no) from order.quote t1 left join order.quote_promotion t2 on t1.quote_no=t2.quote_no  where t1.cust_no=? and t2.prmt_code=?";

	public void delQuote(Integer quoteNo, String targetStatus, String statusReason, Integer userId,String comment){
		QuoteMain quote = this.getById(quoteNo);
		if (quote != null) {
			QuoteProcessLog quoteProcessLog = new QuoteProcessLog();
			String status = quote.getStatus();
			batchExecute(INACTIVE_QUOTE_CLOSE, targetStatus, statusReason,new Date(),userId,quoteNo);
			quoteProcessLog.setCurrentStat(targetStatus);
			
			quoteProcessLog.setNote(comment);
			quoteProcessLog.setPriorStat(status);
			quoteProcessLog.setProcessDate(new Date());
			if (userId != null) {
				quoteProcessLog.setProcessedBy(userDao.getById(userId));
			}
			quoteProcessLog.setQuoteNo(quoteNo);
			quoteProcessLog.setReason(statusReason);
			quoteProcessLogDao.save(quoteProcessLog);
			String itemHql = "from QuoteItem q where q.quoteNo=?";
			List<QuoteItem> itemList = quoteItemDao.find(itemHql, quoteNo);
			if(itemList != null && itemList.size() > 0)
			{
				for(QuoteItem item : itemList ){
					batchExecute(INACTIVE_QUOTE_ITEM, OrderItemStatusType.CN.value(), statusReason,  new Date(), userId, item.getQuoteItemId());
					QuoteProcessLog itemLog = new QuoteProcessLog();
					String itemStatus = item.getStatus();
					itemLog.setQuoteItemId(item.getQuoteItemId());
					itemLog.setPriorStat(itemStatus);
					itemLog.setCurrentStat(OrderItemStatusType.CN.value());
					itemLog.setNote(comment);
					itemLog.setProcessDate(new Date());
					if(userId != null) {
						itemLog.setProcessedBy(userDao.getById(userId));
					}
					itemLog.setQuoteNo(quoteNo);
					itemLog.setReason(statusReason);
					quoteProcessLogDao.save(itemLog);
				}
			}
		}
	}
	
	public Long getTotalProductsQuoted(Map<String, Object> map){
		return findUnique(TOTAL_PRODUCTS_QUOTED, map);
	}

	
	/**
	 * 更新所属custNo
	 * @param sourceCustNo
	 * @param targetCustNo
	 */
	public void updateCustomer(Integer sourceCustNo, Integer targetCustNo, Integer userId) {
		String hql = "update QuoteMain o set o.custNo = ?, o.modifyDate =?, o.modifiedBy =? where o.custNo =?";
		batchExecute(hql, targetCustNo, new Date(), userId, sourceCustNo);
	}

	public Integer getShipToId(final Integer quoteNo, final Integer custNo){
		return findUnique(GET_SHIPTO_ID, quoteNo, custNo);
	}
	
	/**
	 * 查询busEmail
	 * @author zhangyong
	 * @param quoteNo
	 * @return
	 */
	public String findBusEmailByQuoteNo (Integer quoteNo) {
		List<Object> list = this.find("select b.busEmail from QuoteMain a, Customer b, " +
				" where a.custNo = b.custNo and a.quoteNo = ?", quoteNo);
		if (list != null && list.size() > 0) {
			return list.get(0).toString();
		}
		return null;
	}
	
	/**
	 *通过orderNo查询QuoteMain
	 *@author zhangyong
	 * @param orderNo
	 * @return
	 */
	public QuoteMain findByOrderNo (Integer orderNo) {
		if (orderNo != null) {
			List<QuoteMain> quoteList = this.find("from QuoteMain where orderNo = ?", orderNo.toString());
			if (quoteList != null && quoteList.size() > 0) {
				return quoteList.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 通过主键查询
	 * @author zhangyong
	 * @param quoteNo
	 * @return
	 */
	public QuoteMain findByQuoteNo (Integer quoteNo) {
		return this.findUniqueBy("quoteNo", quoteNo);
	}
	
	/**
	 * 判断某客户的报价单是否优惠过
	 * @author lizhang
	 * @param custNo
	 */
	@SuppressWarnings("unchecked")
	public boolean isHasPrmtForCust(Integer custNo,String prmtCode) {
		Query query = this.getSession().createSQLQuery(CUST_HAVE_PRMT);
		query.setParameter(0, custNo);
		query.setParameter(1, prmtCode);
		List<BigInteger> custCount = query.list();
		if(custCount!=null&&custCount.size()>0&&custCount.get(0).longValue()>0l) {
			return true;
		} else {
			return false;
		}
	}
	
	public User getUserByCustNo (Integer quoteNo) {
		String hql = "SELECT u FROM QuoteMain om, Customer c, User u WHERE om.quoteNo=:quoteNo AND om.custNo = c.custNo AND c.techSupport = u.userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quoteNo", quoteNo);
		List<User> userlist = this.find(hql, map);
		if (userlist != null && !userlist.isEmpty()) {
			return userlist.get(0);
		}
		return null;
	}
}
