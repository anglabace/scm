package com.genscript.gsscm.customer.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.customer.entity.GiftCard;

@Repository
public class GiftCardDao extends HibernateDao<GiftCard, Integer> {

	private static String SELECT_BY_CARD_VALUE = "from GiftCard where sendDate is null and sentBy is null and cardValue <= ? order by cardValue desc";

	private static String SELECT_BY_CUSTNO = "from GiftCard where custNo = ?";
	/**
	 * 此方法是将PropertyFilter集合转换为Criterion集合，对数据库无任何操作
	 * @author zhangyong
	 * @param filters
	 * @return
	 */
	public List<Criterion> getCriterionListByFilters (List<PropertyFilter> filters) {
		List<Criterion> criterionList = null;
		if (filters != null && filters.size() > 0) {
			criterionList = new ArrayList<Criterion>();
			Criterion[] criterions = buildPropertyFilterCriterions(filters);
			for (Criterion temp : criterions) {
				criterionList.add(temp);
			}
		}
		return criterionList;
	}
	
	/**
	 * 查询比传入的值小或等于的卡
	 * @author zhangyong
	 * @param cardValue
	 * @return
	 */
	public List<GiftCard> findGiftCardByValue (Integer cardValue) {
		return this.find(SELECT_BY_CARD_VALUE,cardValue);
	}

	
	/**
	 * 通过customer的CustNo查询兑换记录
	 * @author zhangyong
	 * @param custNo
	 * @return
	 */
	public List<GiftCard> findGiftCardByCustNo (Integer custNo) {
		return this.find(SELECT_BY_CUSTNO, custNo);
	}
	
	/**
	 * 通过主键查询
	 * @author zhangyong
	 * @param id
	 * @return
	 */
	public GiftCard findById (Integer id) {
		return this.findUniqueBy("id", id);
	}
}
