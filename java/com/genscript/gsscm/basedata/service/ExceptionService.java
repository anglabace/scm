package com.genscript.gsscm.basedata.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genscript.gsscm.common.exception.BussinessException;
import com.genscript.gsscm.common.exception.ExceptionInfoCache;
import com.genscript.gsscm.common.exception.ExceptionInfoDTO;
import com.genscript.gsscm.system.service.MessageService;
import com.genscript.gsscm.ws.WSException;

@Service
public class ExceptionService {

	@Autowired
	private MessageService messageService;
	@Autowired
	private ExceptionInfoCache exceptionInfoCache;

	private WSException exDTO;
	
	public WSException getExDTO() {
		return exDTO;
	}


	public void setExDTO(WSException exDTO) {
		this.exDTO = exDTO;
	}


	public WSException getExceptionDetails(Exception exp) {
		boolean bool1 = exp.getCause() instanceof SQLException;
		boolean bool2 = (exp.getCause()==null)?false:(exp.getCause().getCause() instanceof SQLException);
		exp.printStackTrace();
		WSException exDTO = new WSException();
		ExceptionInfoDTO expDTO;
		String retMsg;
		String summary;
		String severityLocal;
		String action;
		String code;
		String level;
			String colName,msg;
			SQLException sqle;
			if (bool1 || bool2) {
				if(exp.getCause() instanceof SQLException){
					sqle = (SQLException) exp.getCause();
					msg = sqle.getMessage();
				}
				else{
					sqle = (SQLException) exp.getCause().getCause();
					msg = sqle.getMessage();
				}
				System.out.println("Error Code : " + sqle.getErrorCode()
						+ " , Error State : " + sqle.getSQLState());
				if (sqle.getErrorCode() == 0 && sqle.getSQLState() == null) {
					code = "SE0201";
					retMsg = "The database cannot be connected";
					summary = "The database cannot be connected";
					level = "ERROR";
					severityLocal = "ERROR";
					action = "Please contact system administrator for more help";
				}else if (sqle.getErrorCode() == 0
						&& sqle.getSQLState().equals("22001")) {
					code = "SE0202";
					expDTO = exceptionInfoCache.getExceptionInfo(code);
					colName = msg.substring(msg.indexOf("column ")
							+ "column ".length(), msg.indexOf(" at row"));
					retMsg = expDTO.getDetail();
					retMsg = retMsg.replaceFirst("\\{\\?\\}", colName);
					summary = expDTO.getSummary();
					level = expDTO.getLevel();
					severityLocal = expDTO.getSeverityLocal();
					action = expDTO.getAction();
				} else if (sqle.getErrorCode() == 1048) {
					code = "SE0203";
					expDTO = exceptionInfoCache.getExceptionInfo(code);
					colName = msg.substring(msg.indexOf("Column ")
							+ "Column ".length(), msg.indexOf("cannot") - 1);
					retMsg = expDTO.getDetail();
					retMsg = retMsg.replaceFirst("\\{\\?\\}", colName);
					summary = expDTO.getSummary();
					level = expDTO.getLevel();
					severityLocal = expDTO.getSeverityLocal();
					action = expDTO.getAction();
				} else {
					code = "SE0204";
					expDTO = exceptionInfoCache.getExceptionInfo(code);
					retMsg = expDTO.getDetail();
					summary = expDTO.getSummary();
					level = expDTO.getLevel();
					severityLocal = expDTO.getSeverityLocal();
					action = expDTO.getAction();
				}				
			} else if (exp instanceof BussinessException) {
				BussinessException bussinessException = (BussinessException) exp;
				code = bussinessException.getCode();
				expDTO = exceptionInfoCache.getExceptionInfo(code);
				if (expDTO == null) {
					expDTO = new ExceptionInfoDTO();
				}
				retMsg = expDTO.getDetail();
				if (bussinessException.getReplaceParamValues() != null && bussinessException.getReplaceParamValues().size()>0) {
					for (String value : bussinessException.getReplaceParamValues()) {
					   retMsg = retMsg.replaceFirst("\\{\\?\\}", value);				
					}
				}
				summary = expDTO.getSummary();
				level = expDTO.getLevel();
				severityLocal = expDTO.getSeverityLocal();
				action = expDTO.getAction();
			} else {
//				System.out.println("Error Code :++++++++++++++++++++ " + exp.getCause() +">>>>>>>>>>" + exp.getCause().getCause() + "!!!!!" + exp.getCause().getClass().getName());
				code = "SE0204";
				expDTO = exceptionInfoCache.getExceptionInfo(code);
				retMsg = expDTO.getDetail();
				summary = expDTO.getSummary();
				level = expDTO.getLevel();
				severityLocal = expDTO.getSeverityLocal();
				action = expDTO.getAction();
			}
		exDTO.setCode(code);
		exDTO.setMessageDetail(retMsg);
		exDTO.setMessage(summary);
		exDTO.setLevel(level);
		exDTO.setLocalLevel(severityLocal);
		exDTO.setAction(action);
		return exDTO;
	}




	@SuppressWarnings("unchecked")
	public void logException(WSException exDTO, Class clazz, Exception th, String functionName,
			String interfaceCode, Integer userId) {
		if(userId == null)
		{
			return;
		}
		String fullPath = clazz.getName();
		String code = exDTO.getCode();
		Integer messageId;
		if (code.equals("SE0201")) {
			messageId = 2;
		} else {
			ExceptionInfoDTO expDTO = exceptionInfoCache.getExceptionInfo(code);
			messageId = expDTO.getMessageId();
		}
		SQLException sqle;
		if (th.getCause() instanceof SQLException && code.equals("SE0201")) {
			return;
		} else if (th.getCause() instanceof SQLException) {
			sqle = (SQLException) th.getCause();
			messageService.logExceptionDtls(fullPath, functionName, WSException
					.getException(sqle), userId, messageId, interfaceCode);
		} else if(th.getCause()==null?false:th.getCause().getCause() instanceof SQLException){
			sqle = (SQLException) th.getCause().getCause();
			messageService.logExceptionDtls(fullPath, functionName, WSException
					.getException(sqle), userId, messageId, interfaceCode);
		} else {
			messageService.logExceptionDtls(fullPath, functionName, WSException
					.getException(th), userId, messageId, interfaceCode);
		}

	}


	public WSException getPHPExceptionDetails(String code, List<String> params) {
		WSException exDTO = new WSException();
		ExceptionInfoDTO expDTO;
		expDTO = exceptionInfoCache.getExceptionInfo(code);
		String retMsg;
		String summary;
		String level;
		String severityLocal;
		String action;

		if (params != null && params.size() > 0) {

			retMsg = expDTO.getDetail();
			String regex = "\\{\\?\\}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(retMsg);
			List<Integer> iList = new ArrayList<Integer>();
			while (matcher.find()) {
				iList.add(matcher.end());
				for (int i = 0; i < params.size(); i++) {
					retMsg = retMsg.replaceFirst(regex, params.get(i));
				}
			}
			if (iList.size() != params.size()) {
				throw new RuntimeException("The params length is not correct");
			}
		} else {
			retMsg = expDTO.getDetail();
		}
		summary = expDTO.getSummary();
		severityLocal = expDTO.getSeverityLocal();
		action = expDTO.getAction();
		level = expDTO.getLevel();
		exDTO.setCode(code);
		exDTO.setMessageDetail(retMsg);
		exDTO.setMessage(summary);
		exDTO.setLevel(level);
		exDTO.setLocalLevel(severityLocal);
		exDTO.setAction(action);
		return exDTO;
	}
}
