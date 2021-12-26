package com.footsalon.common.mail;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.footsalon.common.code.Config;
import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;



@Component
public class EmailSender {

	JavaMailSender mailSender;
	
	public EmailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendEmail(String to,String subject, String htmlText) {
		MimeMessage msg = mailSender.createMimeMessage();
       
		try {
			msg.setFrom(Config.COMPANY_EMAIL.DESC);
			msg.setRecipients(Message.RecipientType.TO,to);
		    msg.setSubject(subject);
		    msg.setSentDate(new Date());
		    msg.setText(htmlText,"utf-8","html");
		    mailSender.send(msg);
		} catch (MessagingException e) {
			throw new HandlableException(ErrorCode.MAIL_SEND_FAIL_ERROR);
		}
       
	}
	
	
	
	
	
	
	
	
	
	
	
}
