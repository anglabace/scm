package com.genscript.gsscm.system.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.genscript.gsscm.common.constant.SessionConstant;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.system.entity.UpdateRequestLog;
import com.genscript.gsscm.system.service.UpdateRequestLogService;
import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name="update_request_log",location="system/update_request_log.jsp")
})
public class UpdateRequestLogAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3848782580799666851L;
	@Autowired
	private UpdateRequestLogService updateRequestLogService;
	private UpdateRequestLog updateRequestLog;
	private String changeType;
	
	public String initDialog() {
		updateRequestLog.setRequestDate(new Date());
		updateRequestLog.setRequestedBy(SessionUtil.getUserId());
		updateRequestLog.setRequestedByName(SessionUtil.getUserName());
		return "update_request_log";
	}
	
	public String save() {
		Map<String,String> rt = new HashMap<String,String>();
		List<UpdateRequestLog> updateRequestLogList = (ArrayList<UpdateRequestLog>)SessionUtil.getRow(SessionConstant.UpdateRequestLog.value(), String.valueOf(updateRequestLog.getObjectId()));
		if(updateRequestLogList==null) {
			updateRequestLogList = new ArrayList<UpdateRequestLog>();
		}
		updateRequestLogList.add(updateRequestLog);
		SessionUtil.updateRow(SessionConstant.UpdateRequestLog.value(), String.valueOf(updateRequestLog.getObjectId()), updateRequestLogList);
//		updateRequestLogService.save(updateRequestLog);
		rt.put("message", "Success");
		Struts2Util.renderJson(rt);
		return null;
	}
	

	public UpdateRequestLog getUpdateRequestLog() {
		return updateRequestLog;
	}

	public void setUpdateRequestLog(UpdateRequestLog updateRequestLog) {
		this.updateRequestLog = updateRequestLog;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
}
