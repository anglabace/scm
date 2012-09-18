package com.genscript.gsscm.tools.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.common.util.PagerUtil;
import com.genscript.gsscm.customer.dao.CustomerDao;
import com.genscript.gsscm.customer.dao.CustomerStatusHistoryDao;
import com.genscript.gsscm.customer.dto.CustomerBeanDTO;
import com.genscript.gsscm.customer.dto.CustomerNoteDTO;
import com.genscript.gsscm.customer.entity.CustomerStatusHistory;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.tools.dao.CustomerInvoiceDao;

@Service
@Transactional
public class CustomerInvoiceService {
	
	@Autowired
	private CustomerInvoiceDao customerInvoiceDao;
	@Autowired
	private CustomerStatusHistoryDao customerStatusHistoryDao;
	@Autowired
	private CustomerDao customerDao;
	/**
	 * 获取分页对象
	 * @param page分页对象
	 * @return Page<CustomerBeanDTO>
	 */
	public Page<CustomerBeanDTO> searchCustomerBeanPage(Page<CustomerBeanDTO> customerBeanPage,List<Object[]> arInvoiceList,String status){
		PagerUtil<CustomerBeanDTO> pagerUtil = new PagerUtil<CustomerBeanDTO>();
		customerBeanPage = pagerUtil.getRequestPage();// 获得分页请求相关数据：如第几页
		if (!customerBeanPage.isOrderBySetted()) {// 设置默认排序
			customerBeanPage.setOrderBy("custNo");
			customerBeanPage.setOrder(Page.ASC);
		}
		customerBeanPage.setPageSize(20);// 设置默认每页显示记录条数	
		String custNos="";	
		Float balances[]=new Float[arInvoiceList.size()];
		if(arInvoiceList!=null && arInvoiceList.size()>0){		
			for(int i=0;i<arInvoiceList.size();i++){
				Object[] ob=arInvoiceList.get(i);
				custNos+=ob[0]+",";
				balances[i]=Float.valueOf(String.valueOf(ob[1]));
			}
			custNos=custNos.substring(0,custNos.length()-1);
		}
		
		//把余额放到V_all_customers视图中
		customerBeanPage = this.customerInvoiceDao.findPageForCenter(customerBeanPage,custNos,balances,status);
		return customerBeanPage;
	}
	
	public String LogAndUpdate(CustomerNoteDTO customerNote,String oldCustNo,String oldStatus,Object userId){
		if (customerNote != null) {
			// 改变的原因插入日志表
			CustomerStatusHistory log = new CustomerStatusHistory();
			log.setCustNo(Integer.parseInt(oldCustNo));
			log.setUpdateDate(new Date());
			User updateUser = new User();
			updateUser.setUserId(Integer.parseInt(userId.toString()));
			log.setUpdatedBy(updateUser);
			log.setUpdateReason(customerNote.getMassage());
			log.setCurrentStat(oldStatus);
			log.setPriorStat(customerNote.getStatus());
			this.customerStatusHistoryDao.save(log);
			// 修改状态
			customerDao.updateStatus(Integer.parseInt(oldCustNo), customerNote.getStatus());
			return "success";
		}else{
			return "error";
		}
	}
	
	

}
