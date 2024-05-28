package com.ProvaGrupo.SpringEcommerce.auth.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class EmailUtilTest {

    @Test
    public void testSendOtpEmail() throws Exception {
        JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);
        MimeMessage message = Mockito.mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(message);

        // Injecting the mocked mail sender into the EmailUtil instance
        EmailUtil emailUtil = new EmailUtil();
        Field mailSenderField = EmailUtil.class.getDeclaredField("mailSender");
        mailSenderField.setAccessible(true);
        mailSenderField.set(emailUtil, mailSender);
        
        // Calling the method to send the email
        emailUtil.sendOtpEmail("test@example.com", "123456");

        // Verifying if the methods were called
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(message);
    }

    @Test
    public void testSendRecoverPasswordEmail() throws Exception {
        JavaMailSender mailSender = mock(JavaMailSender.class);
        MimeMessage message = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(message);

        // Injecting the mocked mail sender into the EmailUtil instance
        EmailUtil emailUtil = new EmailUtil();
        Field mailSenderField = EmailUtil.class.getDeclaredField("mailSender");
        mailSenderField.setAccessible(true);
        mailSenderField.set(emailUtil, mailSender);

        // Calling the method to send the email
        emailUtil.sendRecoverPasswordEmail("test@example.com", "123456");

        // Verifying if the methods were called
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(message);
    }
}