package com.ProvaGrupo.SpringEcommerce.auth.exception.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import com.ProvaGrupo.SpringEcommerce.auth.exception.handler.CustomAcessDeniedHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAcessDeniedHandlerTest {

    private CustomAcessDeniedHandler customAcessDeniedHandler;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ResourceBundle bundle;

    @BeforeEach
    public void init() {
        customAcessDeniedHandler = new CustomAcessDeniedHandler();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testHandle() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        customAcessDeniedHandler.handle(request, response, new AccessDeniedException("Access Denied"));

        verify(response, times(1)).setStatus(HttpServletResponse.SC_FORBIDDEN);
        writer.flush();
        assertEquals(bundle.getString("access.denied"), stringWriter.toString());
    }
}