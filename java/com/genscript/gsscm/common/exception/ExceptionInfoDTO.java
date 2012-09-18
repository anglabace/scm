package com.genscript.gsscm.common.exception;


public class ExceptionInfoDTO extends BaseDTO {
	static final long serialVersionUID = 5853337547949414866L;
	private Integer messageId;
	private String messageCode;
	private String level;
	private String severityLocal;
	private String summary;
	private String detail;
	private String action;
//	private Integer interfaceId;
	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}
	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	/**
	 * @return the severityLocal
	 */
	public String getSeverityLocal() {
		return severityLocal;
	}
	/**
	 * @param severityLocal the severityLocal to set
	 */
	public void setSeverityLocal(String severityLocal) {
		this.severityLocal = severityLocal;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
//	/**
//	 * @return the interfaceId
//	 */
//	public Integer getInterfaceId() {
//		return interfaceId;
//	}
//	/**
//	 * @param interfaceId the interfaceId to set
//	 */
//	public void setInterfaceId(Integer interfaceId) {
//		this.interfaceId = interfaceId;
//	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the messageId
	 */
	public Integer getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	public ExceptionInfoDTO(){}
	public ExceptionInfoDTO(Integer messageId, String messageCode, String level, String severityLocal,
			String summary, String detail, String action) {
		this.messageId = messageId;
//		this.interfaceId = interfaceId;
		this.level = level;
		this.messageCode = messageCode;
		this.severityLocal = severityLocal;
		this.summary = summary;
		this.detail = detail;
		this.action = action;
	}
	
}
