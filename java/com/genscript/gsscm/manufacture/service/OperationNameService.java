package com.genscript.gsscm.manufacture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.manufacture.dao.OperationNameDao;

@Service
@Transactional
public class OperationNameService {
	@Autowired
	private OperationNameDao operationNameDao;
	
	/**
	 * fangquan
	 * 2011-11-29
	 * @param order_No
	 * @param item_No
	 * @return
	 */
	public String getOperationName(Integer order_No,Integer item_No){
			String statusName="";
			
			List<String> list=operationNameDao.getOperationName(order_No,item_No);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					if((String)list.get(i)!=null){
						statusName=(String)list.get(i);
						return statusName;
					}				
				}
				
			}else{
				statusName="The state has ended or not found";
			}
		return statusName;
	}
	

}
