package com.example.SpringReddit.service;

import com.example.SpringReddit.exception.RedditException;
import com.example.SpringReddit.model.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;

	@Async
	void sendMail(Email email) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("springreddit@email.com", "Spring Reddit");
			messageHelper.setTo(email.getRecipient());
			messageHelper.setSubject(email.getSubject());
			messageHelper.setText(email.getBody(), true);
		};
		try {
			mailSender.send(messagePreparator);
			log.info("Verification mail sent!");
		} catch (MailException e) {
			log.error("Exception sending mail", e);
			throw new RedditException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception when sending mail to " + email.getRecipient(), e);
		}
	}


}
