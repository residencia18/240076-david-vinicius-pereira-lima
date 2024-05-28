package com.ProvaGrupo.SpringEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * This class is responsible for sending emails.
 */
@Component
public class MailService {

    /**
     * JavaMailSender instance to send emails.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends an email to the user.
     * @param email email to send
     * @param subject email subject
     * @param text email text
     * @throws MessagingException if an error occurs while sending the email
     */
    public void sendEmail(String email, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }
}
