package com.genscript.gsscm.system.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genscript.gsscm.common.MimeMailService;
import com.genscript.gsscm.common.constant.DocumentType;
import com.genscript.gsscm.common.constant.OrderInstructionType;
import com.genscript.gsscm.common.util.SessionUtil;
import com.genscript.gsscm.order.dao.DocumentDao;
import com.genscript.gsscm.order.entity.Document;
import com.genscript.gsscm.order.service.OrderService;
import com.genscript.gsscm.system.dao.MailLogDao;
import com.genscript.gsscm.system.dto.MailLogDTO;
import com.genscript.gsscm.system.entity.MailLog;

@Service
@Transactional
public class MailLogService{
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private MailLogDao mailLogDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private DozerBeanMapper dozer;
	@Autowired
	private MimeMailService mimeMailService;
	
	/**
	 * 保存邮件
	 */
	public void saveMail(MailLogDTO workOrderNote) throws Exception{
		
		MailLog mail = this.dozer.map(workOrderNote, MailLog.class);
		if (mail.getId() != null && mail.getId().intValue() < 1) {
			mail.setId(null);
		}
		mail.setSendBy(SessionUtil.getUserId());
		if (workOrderNote.getDocumentList() != null
				&& !workOrderNote.getDocumentList().isEmpty()) {
			mail.setDocFlag("Y");
			this.mailLogDao.save(mail);
			for (Document doc : workOrderNote.getDocumentList()) {
				doc.setCreatedBy(SessionUtil.getUserId());
				doc.setModifiedBy(SessionUtil.getUserId());
				doc.setCreationDate(new Date());
				doc.setModifyDate(new Date());
				doc.setRefId(mail.getId());
				doc.setRefType(DocumentType.System_Mail_Log.value());
				this.documentDao.save(doc);
			}
		} else {
			mail.setDocFlag("N");
			this.mailLogDao.save(mail);
		}
		/**发送邮件**/
		String mailTo = mail.getRecipient();
		String content = mail.getContent();
		String subject = mail.getSubject();

		if (mail.getDocFlag().equals("Y")) {
			Integer mailLogId = mail.getId();
			List<Document> docList = documentDao.getDocument(
					DocumentType.System_Mail_Log.value(), mailLogId);

				mimeMailService.sendQuoteFollowUpMail(mailTo, subject, content,docList);
		} else {
			mimeMailService.sendMail(mailTo, subject, content,"scm_admin@genscriptcorp.com");
		}
		logger.debug("The  mail log has sended");
		
	} 	
}
