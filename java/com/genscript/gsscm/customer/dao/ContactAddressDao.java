package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.contact.dto.ContactModelDTO;
import com.genscript.gsscm.contact.entity.ContactAddress;

@Repository
public class ContactAddressDao extends HibernateDao<ContactAddress, Integer> {
	private static final String DELETE_CONTACT_ADDRESS = "delete from ContactAddress c where c.addrId in (:ids)";
   public List<ContactAddress> getListByContact(Integer contactNo) {
	   String hql = "From ContactAddress where contactNo=?";
	   return this.find(hql, contactNo);
   }	

	public void delAddressList(Object ids){
		Map<String,Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DELETE_CONTACT_ADDRESS, map);
	}
	
	public void removeDefaltFlag(Integer curtAddrId, String addrType, Integer contactNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contactNo", contactNo);
		map.put("defaultFlag", "N");
		map.put("addrType",addrType);	
		String hql = "update ContactAddress c set c.defaultFlag=:defaultFlag where c.contactNo=:contactNo and addrType=:addrType";
		if (curtAddrId!=null && curtAddrId.intValue()>0) {
			hql += " and c.addrId<>:addrId";
			map.put("addrId", curtAddrId);
		}
		batchExecute(hql, map);		
	}
	
	/**
	 * get address from session
	 * @author lizhang
	 * @param sessNo
	 * @return
	 */
	public ContactAddressDTO searchAddressForSession(String sessNo) {
		ContactModelDTO contact = (ContactModelDTO)SessionUtil.getRow(SessionConstant.Contact.value(),sessNo);
		Map<String, ContactAddressDTO> sessAddrMap = (Map<String, ContactAddressDTO>) SessionUtil
		.getRow(SessionConstant.ContactAddress.value(), sessNo);
		ContactAddressDTO shipToAddr = null;
    	ContactAddressDTO _shipToAddr = null;
        if (sessAddrMap != null && sessAddrMap.size() > 0) {
			for (String contactAddrId : sessAddrMap.keySet()) {
				ContactAddressDTO addrDto = sessAddrMap.get(contactAddrId);
				if (shipToAddr==null&&AddressType.SHIP_TO.value().equals(addrDto.getAddrType())
						&& StatusType.ACTIVE.value().equals(addrDto.getStatus())) {
					shipToAddr = addrDto;
				}
				if(AddressType.SHIP_TO.value().equals(addrDto.getAddrType())&&("Y").equals(addrDto.getDefaultFlag())
						&&StatusType.ACTIVE.value().equals(addrDto.getStatus())) {
					_shipToAddr = addrDto;
					break;
				}
			}
        }
        return _shipToAddr!=null?_shipToAddr:shipToAddr;
	}
   
}
