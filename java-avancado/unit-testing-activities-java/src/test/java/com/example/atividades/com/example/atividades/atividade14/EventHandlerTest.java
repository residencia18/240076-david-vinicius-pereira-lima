package com.example.atividades.atividade14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventHandlerTest {
    @InjectMocks
    private EventHandler eventHandler;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        eventHandler = new EventHandler(emailService);
    }

    @Test
    void testHandleEventSendsEmail() {
        String event = "Sample Event";
        eventHandler.handleEvent(event);

        verify(emailService, times(1)).sendEmail("test@example.com", "Event Occurred", event);
    }

    @Test
    void testEmptyHandleEvent() {
        String event = "";
        eventHandler.handleEvent(event);

        verify(emailService, times(1)).sendEmail("test@example.com", "Event Occurred", event);
    }

    @Test
    void testNullHandleEvent() {
        String event = null;
        eventHandler.handleEvent(event);

        verify(emailService, times(1)).sendEmail("test@example.com", "Event Occurred", event);
    }

    @Test
    void testMultipleHandleEvents() {
        String event1 = "Event 1";
        String event2 = "Event 2";

        eventHandler.handleEvent(event1);
        eventHandler.handleEvent(event2);

        verify(emailService, times(1)).sendEmail("test@example.com", "Event Occurred", event1);
        verify(emailService, times(1)).sendEmail("test@example.com", "Event Occurred", event2);
    }
}
