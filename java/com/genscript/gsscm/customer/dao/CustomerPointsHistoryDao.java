package com.genscript.gsscm.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.dto.CustPointHistoryDTO;
import com.genscript.gsscm.customer.entity.CustomerPointsHistory;
import com.genscript.gsscm.customer.entity.GiftCard;

/**
 * 
 * @author zhangyong
 * 
 */
@Repository
public class CustomerPointsHistoryDao extends
		HibernateDao<CustomerPointsHistory, Integer> {

	private static String SELECT_BY_CUST_NO = "select sum(points) from CustomerPointsHistory where custNo = ?";

	private static String SELECT_BY_CUST_NO_TransactionId = "from CustomerPointsHistory where custNo = ? and arTransactionId=?";

	/**
	 * 通过custNo查询CustomerPointsHistory列表
	 * 
	 * @param zhangyong
	 * @return
	 */
	public Long getCustomerPointsSumByCustNo(Integer custNo) throws Exception {
		Long rtnPointSum = 0l;
		try {
			List<Long> list = this.find(SELECT_BY_CUST_NO, custNo);
			if (list != null && list.size() > 0) {
				rtnPointSum = (Long) list.get(0);
			}
			if (rtnPointSum == null) {
				rtnPointSum = 0l;
			}
		} catch (Exception ex) {
			rtnPointSum = 0l;
		}
		return rtnPointSum;
	}

	/**
	 * 
	 */
	public Integer getMaxTransactionId() {
		String hql = "select max(arTransactionId) from CustomerPointsHistory";
		Integer maxId = this.findUnique(hql);
		return maxId;
	}

	/**
	 * 通过custNo和TransactionId查询CustomerPointsHistory列表
	 */
	public List<CustomerPointsHistory> getCustomerHistoryPoints(Integer custNo,
			Integer transactionId) {
		List<CustomerPointsHistory> list = this.find(
				SELECT_BY_CUST_NO_TransactionId, custNo, transactionId);
		return list;
	}

	public Page<CustomerPointsHistory> searchpointhistoryList(
			Page<CustomerPointsHistory> page, Map<String, Object> filtersMap) {
		StringBuffer hql = new StringBuffer()
				.append(" From CustomerPointsHistory where redeemType='AMAZON'  AND giftCardId IS NULL  ");
		if (filtersMap != null && filtersMap.size() > 0) {
			if (filtersMap.get("custNo") != null) {
				hql.append(" And custNo=:custNo");
			}
		}
		return findPage(page, hql.toString(), filtersMap);
	}

	public Page<CustomerPointsHistory> searchpointhistoryListAll(
			Page<CustomerPointsHistory> page,Integer custNo) {	 
		String sqls = "select p from CustomerPointsHistory p where p.custNo="+custNo; 
		return findPage(page, sqls);
	}

	/**
	 * 查看客户以前是否有过兑换
	 */

}
