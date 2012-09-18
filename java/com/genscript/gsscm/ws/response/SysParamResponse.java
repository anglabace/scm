package com.genscript.gsscm.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.genscript.gsscm.application.dto.ApplicationDTO;
import com.genscript.gsscm.application.dto.ApplicationInterfaceDTO;
import com.genscript.gsscm.application.dto.ApplicationModuleDTO;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.system.dto.MessageDTO;
import com.genscript.gsscm.system.dto.MessageDetailDTO;
import com.genscript.gsscm.system.entity.MessageLogBean;

@XmlType(name = "SysParamResponse", namespace = WsConstants.NS)
public class SysParamResponse extends WsResponse{

	private ApplicationDTO applicationDTO;
	private ApplicationInterfaceDTO appIntfaceDTO;
	private ApplicationModuleDTO appModuleDTO;
	private MessageDTO messageDTO;
	private MessageDetailDTO messageDetailDTO;
	private PageDTO pageDTO;
	private List<MessageDTO> messageList;
	private List<MessageDetailDTO> messageDetailDTOList;
	private List<ApplicationDTO> applicationDTOList;
	private List<ApplicationInterfaceDTO> appInterfaceDTOList;
	private List<ApplicationModuleDTO> appModuleDTOList;
	private List<MessageLogBean> messageLogList;
	
	@XmlElementWrapper(name = "messageDetailList")
	@XmlElement(name = "messageDetail")
	public List<MessageDetailDTO> getMessageDetailDTOList() {
		return messageDetailDTOList;
	}
	public void setMessageDetailDTOList(List<MessageDetailDTO> messageDetailDTOList) {
		this.messageDetailDTOList = messageDetailDTOList;
	}
	@XmlElementWrapper(name = "applicationList")
	@XmlElement(name = "application")
	public List<ApplicationDTO> getApplicationDTOList() {
		return applicationDTOList;
	}
	public void setApplicationDTOList(List<ApplicationDTO> applicationDTOList) {
		this.applicationDTOList = applicationDTOList;
	}
	
	@XmlElementWrapper(name = "appInterfaceList")
	@XmlElement(name = "appInterface")
	public List<ApplicationInterfaceDTO> getAppInterfaceDTOList() {
		return appInterfaceDTOList;
	}
	public void setAppInterfaceDTOList(List<ApplicationInterfaceDTO> appInterfaceDTOList) {
		this.appInterfaceDTOList = appInterfaceDTOList;
	}
	
	@XmlElementWrapper(name = "appModuleList")
	@XmlElement(name = "appModule")
	public List<ApplicationModuleDTO> getAppModuleDTOList() {
		return appModuleDTOList;
	}
	public void setAppModuleDTOList(List<ApplicationModuleDTO> appModuleDTOList) {
		this.appModuleDTOList = appModuleDTOList;
	}
	@XmlElementWrapper(name = "messageList")
	@XmlElement(name = "message")
	public List<MessageDTO> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<MessageDTO> messageList) {
		this.messageList = messageList;
	}
	public PageDTO getPageDTO() {
		return pageDTO;
	}
	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
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
	public List<MessageLogBean> getMessageLogList() {
		return messageLogList;
	}
	public void setMessageLogList(List<MessageLogBean> messageLogList) {
		this.messageLogList = messageLogList;
	}
	
}
