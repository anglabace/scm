package com.genscript.gsscm.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.core.orm.Page;
import com.genscript.core.orm.PropertyFilter;
import com.genscript.gsscm.system.dao.MessageDao;
import com.genscript.gsscm.system.dao.MessageDetailDao;
import com.genscript.gsscm.system.dao.MessageLogBeanDao;
import com.genscript.gsscm.system.dao.MessageLogDao;
import com.genscript.gsscm.system.entity.Message;
import com.genscript.gsscm.system.entity.MessageDetail;
import com.genscript.gsscm.system.entity.MessageLog;
import com.genscript.gsscm.system.entity.MessageLogBean;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private MessageDetailDao messageDetailDao;
	@Autowired
	private MessageLogDao messageLogDao;
	@Autowired
	private MessageLogBeanDao messageLogBeanDao;
	
	@Transactional(readOnly = true)
	public Message getMessage(Integer id) {
		return messageDao.get(id);
	}
	@Transactional(readOnly = true)
	public List<Message> getAllMessage() {
		return messageDao.getAll();
	}
	@Transactional(readOnly = true)
	public Message getMessage(String code)
	{
		return messageDao.findUniqueBy("code", code);
	}
	@Transactional(readOnly = true)
	public Integer getMessageIdByCode(String code) {
		return messageDao.findUniqueBy("code", code).getId();
	}
	public void saveMessage(Message entity) {
		messageDao.save(entity);
	}
	
	public void deleteMessage(Integer id) {
		messageDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Message> listMessage(final Page<Message> page) {
		return messageDao.getAll(page);
	}
	
	@Transactional(readOnly = true)
	public MessageDetail getMessageDetail(Integer id) {
		return messageDetailDao.get(id);
	}
	@Transactional(readOnly = true)
	public MessageDetail getMessageDetailById(Integer id, String language){
		return messageDetailDao.getMessageDetail(id, language);
	}
	@Transactional(readOnly = true)
	public List<MessageDetail> getAllAMessageDetail(){
		return messageDetailDao.getAll();
	}
	public void saveMessageDetail(MessageDetail entity) {
		messageDetailDao.save(entity);
	}
	
	public void deleteMessageDetail(Integer id) {
		messageDetailDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MessageDetail> listMessageDetail(final Page<MessageDetail> page) {
		return messageDetailDao.getAll(page);
	}
	
	public void saveMessageLog(MessageLog messageLog){
		messageLogDao.save(messageLog);
	}
	@Transactional(readOnly = true)
	public Page<MessageLogBean> searchMessageLog(Page<MessageLogBean> page, List<PropertyFilter> filters){
		return messageLogBeanDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<MessageLogBean> searchMessageLog(Page<MessageLogBean> page){
		return messageLogBeanDao.findPage(page);
	}
	
	@Transactional(readOnly = true)
	public Page<MessageLogBean> searchMessageLog(Page<MessageLogBean> page, final Map<String, String> filterParamMap){
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();

			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}
		return messageLogBeanDao.findPage(page, filterList);
	}
	public void logExceptionDtls(String fullPath, String functionName, String stackInfo,
			Integer loggedBy, Integer msgId, String intfCd)
	{
		MessageLog messageLog = new MessageLog(fullPath, functionName, stackInfo,
				loggedBy, msgId, intfCd);
		messageLogDao.save(messageLog);
	}
	
	public void logExceptionDtls(String fullPath, String functionName, String stackInfo,
			Integer loggedBy, String code, String intfCode)
	{
		MessageLog messageLog = new MessageLog(fullPath, functionName, stackInfo,
				loggedBy, getMessage(code).getId(), intfCode);
		messageLogDao.save(messageLog);
	}
	public void logExceptionDtls(String fullPath, String functionName,
			Integer loggedBy, String code, String intfCode)
	{
		MessageLog messageLog = new MessageLog(fullPath, functionName,loggedBy, 
				getMessage(code).getId(), intfCode);
		messageLogDao.save(messageLog);
	}
}
