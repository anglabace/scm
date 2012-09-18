package com.genscript.gsscm.ws;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;

public class WSException {

	protected String code = WsConstants.SUCCESS;
	protected String message;
	protected String messageDetail;
	protected int day;
	protected String level;
	protected String localLevel;
	protected String action;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageDetail() {
		return messageDetail;
	}
	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLocalLevel() {
		return localLevel;
	}
	public void setLocalLevel(String localLevel) {
		this.localLevel = localLevel;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * Set return results.
	 */
	public void setResult(String scode,String msg){
		this.code = scode;
		this.message = msg;
	}
	
	public void setResult(String scode,String msg, String messageDetail){
		this.code = scode;
		this.message = msg;
		this.messageDetail = messageDetail;
	}
	
	/**
	 * Set the default unknown error within the system.
	 */
	public void setSystemError() {
		setResult(WsConstants.SYSTEM_ERROR, "Application system error");
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
    public static String getException(Exception ex){
        StackTraceElement[] ste = ex.getStackTrace();
        StringBuffer sb = new StringBuffer();
        sb.append(ex.getMessage() + " ");
        for (int i = 0; i < ste.length; i++) {
          sb.append(ste[i].toString() + "\r\n");
        }
        return sb.toString();
    }
	/**
	 * 
	 */
	public WSException(Exception ex) {
		super();
		this.messageDetail = WSException.getException(ex);
	}
	public WSException() {
		super();

	}
}
