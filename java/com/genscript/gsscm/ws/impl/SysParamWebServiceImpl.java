package com.genscript.gsscm.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.genscript.core.orm.Page;
import com.genscript.gsscm.application.dto.ApplicationDTO;
import com.genscript.gsscm.application.dto.ApplicationInterfaceDTO;
import com.genscript.gsscm.application.dto.ApplicationModuleDTO;
import com.genscript.gsscm.application.entity.Application;
import com.genscript.gsscm.application.entity.ApplicationInterface;
import com.genscript.gsscm.application.entity.ApplicationModule;
import com.genscript.gsscm.application.service.ApplicationService;
import com.genscript.gsscm.basedata.service.ExceptionService;
import com.genscript.gsscm.common.PageDTO;
import com.genscript.gsscm.common.constant.SearchProperty;
import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.system.dto.MessageDTO;
import com.genscript.gsscm.system.dto.MessageDetailDTO;
import com.genscript.gsscm.system.entity.Message;
import com.genscript.gsscm.system.entity.MessageDetail;
import com.genscript.gsscm.system.entity.MessageLog;
import com.genscript.gsscm.system.entity.MessageLogBean;
import com.genscript.gsscm.system.service.MessageService;
import com.genscript.gsscm.ws.WSException;
import com.genscript.gsscm.ws.api.SysParamWebService;
import com.genscript.gsscm.ws.request.SysParamRequest;
import com.genscript.gsscm.ws.response.SysParamResponse;

@WebService(serviceName = "SysParamService", portName = "SysParamServicePort", endpointInterface = "com.genscript.gsscm.ws.api.SysParamWebService", targetNamespace = WsConstants.NS)
public class SysParamWebServiceImpl implements SysParamWebService {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ExceptionService exceptionUtil;

	@Autowired
	private DozerBeanMapper dozer;

	private SysParamResponse sysParamResponse;

	WSException wsException;

	private Page<Message> pageMessage;
	private Page<MessageDetail> pageMessageDetail;
	private Page<Application> pageApplication;
	private Page<ApplicationInterface> pageAppInterface;
	private Page<ApplicationModule> pageAppModule;

	@Override
	public SysParamResponse deleteMessage(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();

		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			messageService.deleteMessage(sysParamRequest.getId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse getMessage(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		wsException = new WSException();
		try {
			Assert.notNull(userId, "user id can't be null");
			Message message = messageService
					.getMessage(sysParamRequest.getId());
			sysParamResponse.setMessageDTO((MessageDTO) dozer.map(message,
					MessageDTO.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse listMessage(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		pageMessage = sysParamRequest.getPageMessage();

		if (!pageMessage.isOrderBySetted()) {
			pageMessage.setOrderBy("id");
			pageMessage.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			pageMessage = messageService.listMessage(pageMessage);

			PageDTO pageDTO = (PageDTO) dozer.map(pageMessage, PageDTO.class);
			List<Message> messageList = pageMessage.getResult();
			List<MessageDTO> messageDTOList = new ArrayList<MessageDTO>();
			for (Message message : messageList) {
				messageDTOList.add(dozer.map(message, MessageDTO.class));
			}

			sysParamResponse.setPageDTO(pageDTO);
			sysParamResponse.setMessageList(messageDTOList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse saveMessage(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			messageService.saveMessage((Message) dozer.map(sysParamRequest
					.getMessageDTO(), Message.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse delAppIrf(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			applicationService.deleteAppIntface(sysParamRequest.getId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse delAppMod(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			applicationService.deleteAppMod(sysParamRequest.getId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse delApplication(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			applicationService.deleteApplication(sysParamRequest.getId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse delMsgDet(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			messageService.deleteMessageDetail(sysParamRequest.getId());
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse getAppIrf(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		wsException = new WSException();
		try {
			Assert.notNull(userId, "user id can't be null");
			ApplicationInterface applicationInterface = applicationService
					.getAppIntface(sysParamRequest.getId());
			sysParamResponse.setAppIntfaceDTO((ApplicationInterfaceDTO) dozer
					.map(applicationInterface, ApplicationInterfaceDTO.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse getAppMod(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		wsException = new WSException();
		try {
			Assert.notNull(userId, "user id can't be null");
			ApplicationModule applicationModule = applicationService
					.getAppMod(sysParamRequest.getId());
			sysParamResponse.setAppModuleDTO((ApplicationModuleDTO) dozer.map(
					applicationModule, ApplicationModuleDTO.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse getApplication(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		wsException = new WSException();
		try {
			Assert.notNull(userId, "user id can't be null");
			Application application = applicationService
					.getApplication(sysParamRequest.getId());
			sysParamResponse.setApplicationDTO((ApplicationDTO) dozer.map(
					application, ApplicationDTO.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse getMsgDet(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		wsException = new WSException();
		try {
			Assert.notNull(userId, "user id can't be null");
			MessageDetail messageDetail = messageService
					.getMessageDetail(sysParamRequest.getId());
			sysParamResponse.setMessageDetailDTO((MessageDetailDTO) dozer.map(
					messageDetail, MessageDetailDTO.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse listAppIrf(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		pageAppInterface = sysParamRequest.getPageAppInterface();

		if (!pageAppInterface.isOrderBySetted()) {
			pageAppInterface.setOrderBy("id");
			pageAppInterface.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			pageAppInterface = applicationService
					.listAppIntface(pageAppInterface);

			PageDTO pageDTO = (PageDTO) dozer.map(pageAppInterface,
					PageDTO.class);
			List<ApplicationInterface> appInterfaceList = pageAppInterface
					.getResult();
			List<ApplicationInterfaceDTO> appInterfaceDTOList = new ArrayList<ApplicationInterfaceDTO>();
			for (ApplicationInterface applicationInterface : appInterfaceList) {
				appInterfaceDTOList.add(dozer.map(applicationInterface,
						ApplicationInterfaceDTO.class));
			}

			sysParamResponse.setPageDTO(pageDTO);
			sysParamResponse.setAppInterfaceDTOList(appInterfaceDTOList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse listAppMod(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		pageAppModule = sysParamRequest.getPageAppModule();

		if (!pageAppModule.isOrderBySetted()) {
			pageAppModule.setOrderBy("id");
			pageAppModule.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			pageAppModule = applicationService.listAppMod(pageAppModule);

			PageDTO pageDTO = (PageDTO) dozer.map(pageAppModule, PageDTO.class);
			List<ApplicationModule> appModuleList = pageAppModule.getResult();
			List<ApplicationModuleDTO> appModuleDTOList = new ArrayList<ApplicationModuleDTO>();
			for (ApplicationModule applicationModule : appModuleList) {
				appModuleDTOList.add(dozer.map(applicationModule,
						ApplicationModuleDTO.class));
			}

			sysParamResponse.setPageDTO(pageDTO);
			sysParamResponse.setAppModuleDTOList(appModuleDTOList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse listApplication(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		pageApplication = sysParamRequest.getPageApplication();

		if (!pageApplication.isOrderBySetted()) {
			pageApplication.setOrderBy("id");
			pageApplication.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			pageApplication = applicationService
					.listApplication(pageApplication);

			PageDTO pageDTO = (PageDTO) dozer.map(pageApplication,
					PageDTO.class);
			List<Application> applicationList = pageApplication.getResult();
			List<ApplicationDTO> applicationDTOList = new ArrayList<ApplicationDTO>();
			for (Application application : applicationList) {
				applicationDTOList.add(dozer.map(application,
						ApplicationDTO.class));
			}

			sysParamResponse.setPageDTO(pageDTO);
			sysParamResponse.setApplicationDTOList(applicationDTOList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse listMsgDet(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		pageMessageDetail = sysParamRequest.getPageMessageDetail();

		if (!pageMessageDetail.isOrderBySetted()) {
			pageMessageDetail.setOrderBy("id");
			pageMessageDetail.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			pageMessageDetail = messageService
					.listMessageDetail(pageMessageDetail);

			PageDTO pageDTO = (PageDTO) dozer.map(pageMessageDetail,
					PageDTO.class);
			List<MessageDetail> messageDetailList = pageMessageDetail
					.getResult();
			List<MessageDetailDTO> messageDetailDTOList = new ArrayList<MessageDetailDTO>();
			for (MessageDetail messageDetail : messageDetailList) {
				messageDetailDTOList.add(dozer.map(messageDetail,
						MessageDetailDTO.class));
			}

			sysParamResponse.setPageDTO(pageDTO);
			sysParamResponse.setMessageDetailDTOList(messageDetailDTOList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse saveAppIrf(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			applicationService.saveAppIntface((ApplicationInterface) dozer.map(
					sysParamRequest.getAppIntfaceDTO(),
					ApplicationInterface.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse saveAppMod(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			applicationService
					.saveAppMod((ApplicationModule) dozer.map(sysParamRequest
							.getAppModuleDTO(), ApplicationModule.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse saveApplication(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			applicationService.saveApplication((Application) dozer.map(
					sysParamRequest.getApplicationDTO(), Application.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse saveMsgDet(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		sysParamResponse = new SysParamResponse();
		try {
			Assert.notNull(userId, "user id can't be null");
			messageService
					.saveMessageDetail((MessageDetail) dozer.map(
							sysParamRequest.getMessageDetailDTO(),
							MessageDetail.class));
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse logException(SysParamRequest sysParamRequest) {
		sysParamResponse = new SysParamResponse();
		Integer messageId, userId;
		String code;
		List<String> params;
		userId = sysParamRequest.getUserId();
		code = sysParamRequest.getCode();
		params = sysParamRequest.getParams();
		MessageLog messageLog = sysParamRequest.getMessageLog();

		try {
			 Assert.notNull(userId, "user id can't be null");
			 Assert.notNull(code, "code can't be null");
			messageId = messageService.getMessageIdByCode(code);
			messageLog.setLogedBy(userId);
			messageLog.setLogDate(new Date());
			messageLog.setMessageId(messageId);
			messageService.saveMessageLog(messageLog);
			sysParamResponse.setWsException(exceptionUtil
					.getPHPExceptionDetails(code, params));
			sysParamResponse.setHasException(false);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysParamResponse;
	}

	@Override
	public SysParamResponse searchException(SysParamRequest sysParamRequest) {
		Integer userId = sysParamRequest.getUserId();
		Page<MessageLogBean> page = null;
		SysParamResponse sysResponse = new SysParamResponse();
		Map<String, String> filterMap = new HashMap<String, String>();
		Page<MessageLogBean> pageMessageLog = sysParamRequest
				.getPageMessageLog();
		if (!pageMessageLog.isOrderBySetted()) {
			pageMessageLog.setOrderBy("logId");
			pageMessageLog.setOrder(Page.ASC);
		}
		try {
			Assert.notNull(userId, "user id can't be null");
			List<SearchProperty> srchPropList = sysParamRequest
					.getSearchPropertyList();
			if (srchPropList == null) {
				page = messageService.searchMessageLog(pageMessageLog);
			} else {
				for (SearchProperty searchProperty : srchPropList) {
					String propName = searchProperty.getPropertyName();
					Assert.notNull(propName, "property name can't be null");
					Assert.notNull(searchProperty.getSearchOperator(),
							"property operate can't be null");
					Assert.notNull(searchProperty.getSearchPropertyType(),
							"property type can't be null");
					String srchOperator = searchProperty.getSearchOperator()
							.name();
					String propType = searchProperty.getSearchPropertyType()
							.name();
					String propValue1 = searchProperty.getPropertyValue1();

					StringBuilder srchStr = new StringBuilder();
					srchStr.append(srchOperator).append(propType).append("_")
							.append(propName);
					filterMap.put(srchStr.toString(), propValue1);
				}
				page = messageService.searchMessageLog(pageMessageLog,
						filterMap);
			}
			PageDTO pageDTO = (PageDTO) dozer.map(page, PageDTO.class);
			List<MessageLogBean> messageLogList = page.getResult();
			sysResponse.setPageDTO(pageDTO);
			sysResponse.setMessageLogList(messageLogList);
		} catch (Exception e) {
			WSException exDTO = exceptionUtil.getExceptionDetails(e);
			if (userId != null)
				exceptionUtil
						.logException(exDTO, this.getClass(), e, new Exception()
								.getStackTrace()[0].getMethodName(),
								"INTF0101", userId);
			sysParamResponse.setHasException(true);
			sysParamResponse.setWsException(exDTO);
		}
		return sysResponse;
	}

}
