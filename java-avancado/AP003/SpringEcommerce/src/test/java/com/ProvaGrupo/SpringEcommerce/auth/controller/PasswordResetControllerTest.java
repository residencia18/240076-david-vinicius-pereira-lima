package com.ProvaGrupo.SpringEcommerce.auth.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ProvaGrupo.SpringEcommerce.auth.model.dto.password.PasswordResetDTO;
import com.ProvaGrupo.SpringEcommerce.auth.model.dto.password.PasswordResetRequestDTO;
import com.ProvaGrupo.SpringEcommerce.auth.service.PasswordResetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordResetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordResetService passwordResetService;

    Faker faker = new Faker();

    @Test
    public void testRequestReset() throws Exception {
        String email = faker.internet().emailAddress();
        PasswordResetRequestDTO requestDTO = new PasswordResetRequestDTO(email);

        mockMvc.perform(post("/password/request-reset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());
        
        verify(passwordResetService, times(1)).requestReset(requestDTO);
    }

    @Test
    public void testReset() throws Exception {
        String newPassword = faker.lorem().characters(10, 15) + "A1#";
        String confirmPassword = newPassword; // same password
        PasswordResetDTO resetDTO = new PasswordResetDTO(newPassword, confirmPassword);

        String email = faker.internet().emailAddress();
        String token = faker.number().digits(6);

        mockMvc.perform(post("/password/reset")
                .param("email", email)
                .param("token", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resetDTO)))
                .andExpect(status().isOk());

        verify(passwordResetService, times(1)).reset(email, token, resetDTO);
    }
}