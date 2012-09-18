package com.genscript.gsscm.contact.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.contact.entity.ContactInterest;
/**
 * 对Contact模块Interest(type分为'DR', 'AT'两种)部分的数据库操作类;
 * @author wangsf
 *
 */
@Repository
public class ContactInterestDao extends HibernateDao<ContactInterest, Integer> {

	/**
	 * 删除一个contact的Interest列表
	 * @param delInstIdList interestId列表
	 * @param contactNo 当前操作的contact
	 */
	public void delList(List<Integer> delInstIdList, Integer contactNo) {
		String delHQL = "delete from ContactInterest c where c.contactNo=:contactNo and c.interestId in (:interestIdList)";
		Map<String,List<Integer>> map = Collections.singletonMap("interestIdList", delInstIdList);
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("contactNo", contactNo);
		values.put("interestIdList", map.get("interestIdList"));
		batchExecute(delHQL, values);
	}
}
