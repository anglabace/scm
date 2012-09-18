package com.genscript.gsscm.ws.request;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.application.dto.ApplicationDTO;
import com.genscript.gsscm.application.dto.ApplicationInterfaceDTO;
import com.genscript.gsscm.application.dto.ApplicationModuleDTO;
import com.genscript.gsscm.application.entity.Application;
import com.genscript.gsscm.application.entity.ApplicationInterface;
import com.genscript.gsscm.application.entity.ApplicationModule;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.system.dto.MessageDTO;
import com.genscript.gsscm.system.dto.MessageDetailDTO;
import com.genscript.gsscm.system.entity.Message;
import com.genscript.gsscm.system.entity.MessageDetail;
import com.genscript.gsscm.system.entity.MessageLog;
import com.genscript.gsscm.system.entity.MessageLogBean;

@XmlType(name = "SysParamRequest", namespace = WsConstants.NS)
public class SysParamRequest extends WsRequest {

	private Integer id;
	private ApplicationDTO applicationDTO;
	private ApplicationInterfaceDTO appIntfaceDTO;
	private ApplicationModuleDTO appModuleDTO;
	private MessageDTO messageDTO;
	private MessageDetailDTO messageDetailDTO;
	private Page<Message> pageMessage;
	private Page<MessageDetail> pageMessageDetail;
	private Page<Application> pageApplication;
	private Page<ApplicationInterface> pageAppInterface;
	private Page<ApplicationModule> pageAppModule;
	private MessageLog messageLog;
	private String code;
	private List<SearchProperty> searchPropertyList;
	private Page<MessageLogBean> pageMessageLog;
	private List<String> params;
	
	
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	public Page<MessageDetail> getPageMessageDetail() {
		return pageMessageDetail;
	}
	public void setPageMessageDetail(Page<MessageDetail> pageMessageDetail) {
		this.pageMessageDetail = pageMessageDetail;
	}
	public Page<Application> getPageApplication() {
		return pageApplication;
	}
	public void setPageApplication(Page<Application> pageApplication) {
		this.pageApplication = pageApplication;
	}
	public Page<ApplicationInterface> getPageAppInterface() {
		return pageAppInterface;
	}
	public void setPageAppInterface(Page<ApplicationInterface> pageAppInterface) {
		this.pageAppInterface = pageAppInterface;
	}
	public Page<ApplicationModule> getPageAppModule() {
		return pageAppModule;
	}
	public void setPageAppModule(Page<ApplicationModule> pageAppModule) {
		this.pageAppModule = pageAppModule;
	}
	public Page<Message> getPageMessage() {
		return pageMessage;
	}
	public void setPageMessage(Page<Message> pageMessage) {
		this.pageMessage = pageMessage;
	}
	public MessageDTO getMessageDTO() {
		return messageDTO;
	}
	public void setMessageDTO(MessageDTO messageDTO) {
		this.messageDTO = messageDTO;
	}
	public MessageDetailDTO getMessageDetailDTO() {
		return messageDetailDTO;
	}
	public void setMessageDetailDTO(MessageDetailDTO messageDetailDTO) {
		this.messageDetailDTO = messageDetailDTO;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ApplicationDTO getApplicationDTO() {
		return applicationDTO;
	}
	public void setApplicationDTO(ApplicationDTO applicationDTO) {
		this.applicationDTO = applicationDTO;
	}
	public ApplicationInterfaceDTO getAppIntfaceDTO() {
		return appIntfaceDTO;
	}
	public void setAppIntfaceDTO(ApplicationInterfaceDTO appIntfaceDTO) {
		this.appIntfaceDTO = appIntfaceDTO;
	}
	public ApplicationModuleDTO getAppModuleDTO() {
		return appModuleDTO;
	}
	public void setAppModuleDTO(ApplicationModuleDTO appModuleDTO) {
		this.appModuleDTO = appModuleDTO;
	}
	public MessageLog getMessageLog() {
		return messageLog;
	}
	public void setMessageLog(MessageLog messageLog) {
		this.messageLog = messageLog;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<SearchProperty> getSearchPropertyList() {
		return searchPropertyList;
	}
	public void setSearchPropertyList(List<SearchProperty> searchPropertyList) {
		this.searchPropertyList = searchPropertyList;
	}
	public Page<MessageLogBean> getPageMessageLog() {
		return pageMessageLog;
	}
	public void setPageMessageLog(Page<MessageLogBean> pageMessageLog) {
		this.pageMessageLog = pageMessageLog;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
