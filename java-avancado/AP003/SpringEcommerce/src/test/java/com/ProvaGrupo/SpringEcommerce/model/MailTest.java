package com.ProvaGrupo.SpringEcommerce.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MailTest {

    @Test
    public void testDefaultConstructor() {
        Mail mail = new Mail();
        assertNull(mail.getFrom());
        assertNull(mail.getTo());
        assertNull(mail.getSubject());
        assertNull(mail.getContent());
    }

    @Test
    public void testSetters() {
        Mail mail = new Mail();
        mail.setFrom("sender@example.com");
        mail.setTo("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setContent("Test Content");
        assertEquals("sender@example.com", mail.getFrom());
        assertEquals("recipient@example.com", mail.getTo());
        assertEquals("Test Subject", mail.getSubject());
        assertEquals("Test Content", mail.getContent());
    }

    @Test
    public void testGetters() {
        Mail mail = new Mail();
        mail.setFrom("sender2@example.com");
        mail.setTo("recipient2@example.com");
        mail.setSubject("Test Subject 2");
        mail.setContent("Test Content 2");
        assertEquals("sender2@example.com", mail.getFrom());
        assertEquals("recipient2@example.com", mail.getTo());
        assertEquals("Test Subject 2", mail.getSubject());
        assertEquals("Test Content 2", mail.getContent());
    }
}