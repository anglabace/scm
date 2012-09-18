package com.genscript.gsscm.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Text message service class.
 * 
 * @author Golf
 */
@Service
@Transactional
public class TextMailService {

	private static Logger logger = LoggerFactory.getLogger(TextMailService.class);
	
	private JavaMailSender mailSender;
	private String textTemplate;
	
	/**
	 * Users to send plain text e-mail notification changes.
	 */
	public void sendMail(String userName, String mailTo, String subject) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("info@google.com");
		msg.setTo(mailTo);
		msg.setSubject(subject);
		String content = String.format(textTemplate, userName, new Date());
		msg.setText(content);

		try {
			mailSender.send(msg);
			logger.info("Plain text mail has been sent to the {}", StringUtils.arrayToCommaDelimitedString(msg.getTo()));
		} catch (Exception e) {
			logger.error("Failed to send mail ", e);
		}
	}
	
	/**
	 * Spring MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Message content string template.
	 */
	public void setTextTemplate(String textTemplate) {
		this.textTemplate = textTemplate;
	}
}
