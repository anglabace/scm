package com.genscript.gsscm.common.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genscript.gsscm.common.util.PrivilegeServletUtil;
import com.genscript.gsscm.privilege.service.PrivilegeService;
import com.genscript.gsscm.shipment.service.ShipMethodService;
import com.genscript.gsscm.shipment.service.ShippingService;
import com.genscript.gsscm.system.entity.Message;
import com.genscript.gsscm.system.entity.MessageDetail;
import com.genscript.gsscm.system.service.MessageService;


@Service
public class ExceptionInfoCache {
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private ShippingService shippingService;
	
	@Autowired
	private ShipMethodService shipMethodService;


	private Map<String, ExceptionInfoDTO> ecache = new HashMap<String, ExceptionInfoDTO>();
	
	/**
	 * It's a private constructor for singleton instance.
	 */
	@PostConstruct
	public void initExceptionInfoCache() {
		List<Message> messageList;
		try {
			messageList = messageService.getAllMessage();
			List<MessageDetail> messageDetailList = messageService.getAllAMessageDetail();
			if(messageList != null && messageList.size() > 0){
				for (int i = 0; i < messageList.size(); i++) {
					//String cd = messageList.get(i).getCode();
					addException(messageList.get(i), "EN-US",messageDetailList);
				}
			}
		}catch (Throwable t) {
			t.printStackTrace();
			ecache = null;
		}
		/*int i = 0;
		for(Map.Entry<String, ExceptionInfoDTO>entry : ecache.entrySet()){
			i++;
			String key = entry.getKey().toString();
			ExceptionInfoDTO value = entry.getValue();
			System.out.println(i);
			System.out.println(key);
			System.out.println(value.toString());
		}*/
	}
	
	@PostConstruct
	public void initPrivilegeList(){
		PrivilegeServletUtil.setPrivilegeList(this.privilegeService.getAllPrivilege());
		/*ShippingServletUtil.setWarehouseList(this.shippingService.searchWarehouseAll());
		ShippingServletUtil.setShipMethodList(this.shipMethodService.searchShipMethodAll());*/
	}
	
	private void addException(String messageCode, String language){
		Integer messageId;
		MessageDetail messageDetail = null;
		messageId = messageService.getMessageIdByCode(messageCode);
		if(messageId!=null){
			messageDetail = messageService.getMessageDetailById(messageId,language);
		}
		if(messageDetail != null){
			String level = messageService.getMessage(messageCode).getSeverity();
			ExceptionInfoDTO exInfo = new ExceptionInfoDTO(messageDetail.getMsgId(),messageCode, level,messageDetail.getSeverityLocal(),messageDetail.getSummary(),messageDetail.getDetail(),messageDetail.getAction());
			ecache.put(messageCode, exInfo);
		}
		
	}
	
	private void addException(Message message, String language,List<MessageDetail> messageDetailList){
		//Integer messageId;
		MessageDetail messageDetail = null;
		//messageId = messageService.getMessageIdByCode(messageCode);
		if(message!=null){
			for(MessageDetail messageDetail1 : messageDetailList){
				if(language.equals(messageDetail1.getLanguage())){
					if(messageDetail1.getMsgId().equals(message.getId())){
						messageDetail = messageDetail1;
					}
				}
				
			}
			
		}
		if(messageDetail != null){
			String level = message.getSeverity();
			ExceptionInfoDTO exInfo = new ExceptionInfoDTO(messageDetail.getMsgId(),message.getCode(), level,messageDetail.getSeverityLocal(),messageDetail.getSummary(),messageDetail.getDetail(),messageDetail.getAction());
			ecache.put(message.getCode(), exInfo);
		}
		
		
	}


	/**
	 * Based on exception type passed it returns the
	 * <code>ExceptionInfoDTO</code> object from cache.
	 * 
	 * @param errorCode
	 *            error code String.
	 * @return <code>ExceptionInfoDTO</code>
	 * 
	 */
	public ExceptionInfoDTO getExceptionInfo(String errorCode) {
//		for(Map.Entry<String, ExceptionInfoDTO> entry : ecache.entrySet()){
//			System.out.println("Key : "+entry.getKey());
//			if(entry.getKey().equals(errorCode))
//			System.out.println("Value : "+entry.getValue());
//		}
		if (ecache.get(errorCode) == null){
			addException(errorCode, "EN-US");
			return ecache.get(errorCode);
		}
		return (ExceptionInfoDTO) ecache.get(errorCode);
	}
}
