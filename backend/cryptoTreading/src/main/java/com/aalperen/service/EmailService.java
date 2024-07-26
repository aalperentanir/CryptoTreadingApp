package com.aalperen.service;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	public void sendVerificationOtpEmail(String email, String otp) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
		
		 String subject= "Verify OTP";
		 String text = "Your verification code: "+otp;
		 
		 helper.setSubject(subject);
		 helper.setText(text);
		 helper.setTo(email);
		 
		 try {
			 mailSender.send(message);
			 
		 }catch(Exception e) {
			 
			 throw new MailSendException(e.getMessage());
		 }
	}

}
