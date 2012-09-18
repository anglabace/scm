package com.genscript.gsscm.system.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.privilege.dao.UserDao;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.system.dao.UpdateRequestLogDao;
import com.genscript.gsscm.system.entity.UpdateRequestLog;

@Service
@Transactional
public class UpdateRequestLogService {
	@Autowired
	private UpdateRequestLogDao updateRequestLogDao;
	@Autowired
	private UserDao userDao;
	
	public void save(UpdateRequestLog updateRequestLog) {
		updateRequestLogDao.save(updateRequestLog);
	}
	
	public void batchSave(List<UpdateRequestLog> list) {
		if(list!=null&&list.size()>0) {
			for(UpdateRequestLog entity:list) {
				entity.setRequestDate(new Date());
				entity.setRequestedBy(SessionUtil.getUserId());
				updateRequestLogDao.save(entity);
			}
		}
	}
	
	public List<UpdateRequestLog> searchUpdateRequestLog(Object... param) {
		List<UpdateRequestLog> list = this.updateRequestLogDao.findBySomeCondition(param);
		if(list!=null) {
			for(UpdateRequestLog updateRequestLog:list) {
				User user = this.userDao.getById(updateRequestLog.getRequestedBy());
				updateRequestLog.setRequestedByName(user!=null?user.getLoginName():"");
			}
		}
		return list;
	}
}
