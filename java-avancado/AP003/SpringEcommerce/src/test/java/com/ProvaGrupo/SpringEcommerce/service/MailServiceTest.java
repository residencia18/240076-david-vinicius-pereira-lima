package com.ProvaGrupo.SpringEcommerce.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private MailService mailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmail() throws Exception {
        JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);
        MimeMessage message = Mockito.mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(message);

        // Injecting the mocked mail sender into the MailService instance
        MailService mailService = new MailService();
        Field mailSenderField = MailService.class.getDeclaredField("mailSender");
        mailSenderField.setAccessible(true);
        mailSenderField.set(mailService, mailSender);
        
        // Calling the method to send the email
        mailService.sendEmail("test@example.com", "Test Subject", "Test Text");

        // Verifying if the methods were called
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(message);
    }
}