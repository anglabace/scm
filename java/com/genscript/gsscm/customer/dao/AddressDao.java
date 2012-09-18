package com.genscript.gsscm.customer.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.constant.AddressType;
import com.genscript.gsscm.common.constant.OperationType;
import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.constant.StatusType;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.contact.dto.ContactAddressDTO;
import com.genscript.gsscm.customer.dto.CustAddrOperDTO;
import com.genscript.gsscm.customer.dto.CustomerDTO;
import com.genscript.gsscm.customer.entity.Address;

@Repository
public class AddressDao extends HibernateDao<Address, Integer> {

	private static final String DEL_ADDRS = "delete from Address c where c.addrId in (:ids)";
	private static final String DEFAULT_BILLTO_ADDRS = "from Address c where c.defaultFlag = 'Y' and c.addrType=? and c.custNo=?";
	private static final String DEFAULT_SHIPTO_ADDRS = "from Address c where c.defaultFlag = 'Y' and c.addrType=? and c.custNo=?";
	
	public void delAddrList(Object ids) {
		Map<String, Object> map = Collections.singletonMap("ids", ids);
		batchExecute(DEL_ADDRS, map);
	}

	public List<Address> getListByCust(Integer custNo) {
		String hql = "From Address where custNo=?";
		return this.find(hql, custNo);
	}

	public Address getDefaultShipTOAddress(Integer custNo){
		return findUnique(DEFAULT_SHIPTO_ADDRS, AddressType.SHIP_TO.name(), custNo);
	}
	
	public Address getDefaultBillTOAddress(Integer custNo){
		return findUnique(DEFAULT_BILLTO_ADDRS, AddressType.BILL_TO.name(), custNo);
	}
	
	/**
	 * get address from session
	 * @author lizhang
	 * @param sessNo
	 * @return
	 */
	public CustAddrOperDTO searchAddressForSession(String sessNo) {
		CustomerDTO customer = (CustomerDTO)SessionUtil.getRow(SessionConstant.Customer.value(), sessNo);
		//获得customer Address
		List<CustAddrOperDTO> custAddrList = (List<CustAddrOperDTO>)SessionUtil
				.getRow(SessionConstant.CustAddressList.value(), sessNo);
        if (custAddrList == null || custAddrList.isEmpty()) {
			return null;
		}
		CustAddrOperDTO shipToAddr = null;
		CustAddrOperDTO _shipToAddr = null;
		for (CustAddrOperDTO addrDto : custAddrList) {
			if (shipToAddr==null&&AddressType.SHIP_TO.value().equals(addrDto.getAddrType())
					&& ("Y").equals(addrDto.getDefaultFlag())
					&& !OperationType.DEL.value().equals(addrDto.getOperateType())) {
				shipToAddr = addrDto;
			}
			if(AddressType.SHIP_TO.value().equals(addrDto.getAddrType())
					&& ("Y").equals(addrDto.getDefaultFlag())
					&& StatusType.ACTIVE.value().equals(addrDto.getStatus())
					&& !OperationType.DEL.value().equals(addrDto.getOperateType())) {
				_shipToAddr = addrDto;
				break;
			}
		}
		return _shipToAddr!=null?_shipToAddr:shipToAddr;
	}
 
}
