package com.genscript.gsscm.tools.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.core.orm.hibernate.HibernateDao;
import com.genscript.gsscm.common.util.DateUtils;
import com.genscript.gsscm.customer.dto.CustomerBeanDTO;

@Repository
public class CustomerInvoiceDao extends HibernateDao<CustomerBeanDTO, Integer> {
	/**
	 * 分页
	 * @param page
	 * @param filters
	 * @param custNos
	 * @param balances
	 * @param custNo
	 * @param status
	 * @return
	 */
	public Page<CustomerBeanDTO>  findPageForCenter(Page<CustomerBeanDTO> page,String custNos,Float[] balances,String status){
		String hql="select c.custNo,c.firstName,c.lastName,c.organizationName,c.status,c.addrLine1," +
				"c.addrLine2,c.addrLine3,c.busEmail,c.busPhone,c.creationDate from CustomerBean c where 1=1";
		Map<String, Object> map = new HashMap<String, Object>();
		if("ACTIVE".equals(status)){
			hql+=" and c.status='ACTIVE'";
		}else if("INACTIVE".equals(status)){
			hql+=" and c.status='INACTIVE'";
		}else if("SUSPENDED".equals(status)){
			hql+=" and c.status='SUSPENDED'";
		}
		if(!"".equals(custNos)){
			hql+=" and c.custNo in ("+custNos+")";
		}else{
			return page;
		}	
		hql+=" order by c.custNo";
		page = this.findPage(page, hql, map);
		if (page != null && page.getResult() != null&& page.getResult().size() > 0) {
			List objDtoList = page.getResult();
			List<CustomerBeanDTO> cbDtoList = new ArrayList<CustomerBeanDTO>();
			for (int i=0;i<objDtoList.size();i++) {
				CustomerBeanDTO cbDto = new CustomerBeanDTO();
				Object[] object=(Object[])objDtoList.get(i);
				cbDto.setCustNo(Integer.parseInt(object[0].toString()));
				cbDto.setFirstName(object[1] == null?null:object[1].toString());
				cbDto.setLastName(object[2] == null?null:object[2].toString());
				cbDto.setOrganizationName(object[3] == null?null:object[3].toString());
				cbDto.setStatus(object[4] == null?null:object[4].toString());
				cbDto.setAddrLine1(object[5] == null?null:object[5].toString());
				cbDto.setAddrLine2(object[6] == null?null:object[6].toString());
				cbDto.setAddrLine3(object[7] == null?null:object[7].toString());
				cbDto.setBusEmail(object[8] == null?null:object[8].toString());
				cbDto.setBusPhone(object[9] == null?null:object[9].toString());
				cbDto.setCreationDate(DateUtils.formatStr2Date(object[10].toString()));
				if(balances.length>0){
					cbDto.setBalance(balances[i]==null?null:balances[i]);
				}
				cbDtoList.add(cbDto);
			}
			page.setResult(cbDtoList);
	}
		return page;
		
	}
	
	
}
