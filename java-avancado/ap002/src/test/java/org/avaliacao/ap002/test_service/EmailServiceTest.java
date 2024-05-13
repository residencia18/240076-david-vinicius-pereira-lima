package org.avaliacao.ap002.test_service;

import org.avaliacao.ap002.auth.entity.MailBody;
import org.avaliacao.ap002.auth.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(javaMailSender);
    }

    @Test
    public void testSendSimpleMessage() {
        MailBody mailBody = new MailBody("recipient@example.com", "Test Subject", "Test Body");

        emailService.sendSimpleMessage(mailBody);

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
