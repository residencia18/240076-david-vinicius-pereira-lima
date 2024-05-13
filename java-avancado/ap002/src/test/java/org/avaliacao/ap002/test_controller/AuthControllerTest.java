package org.avaliacao.ap002.test_controller;


import org.avaliacao.ap002.auth.controller.AuthController;
import org.avaliacao.ap002.auth.entity.user.AuthDTO;
import org.avaliacao.ap002.auth.entity.user.RegisterDTO;
import org.avaliacao.ap002.auth.entity.user.User;
import org.avaliacao.ap002.auth.entity.user.UserRole;
import org.avaliacao.ap002.auth.repository.UserRepository;
import org.avaliacao.ap002.auth.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TokenService tokenService;

    @Test
    public void testLogin_Success() throws Exception {
        AuthDTO authDTO = new AuthDTO("test@example.com", "password");
        User user = new User("test@example.com", "hashedPassword", UserRole.USER);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(tokenService.generateToken(user)).thenReturn("fakeToken");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("fakeToken"));
    }

    @Test
    public void testLogin_Failure_InvalidCredentials() throws Exception {
        AuthDTO authDTO = new AuthDTO("test@example.com", "wrongPassword");

        when(authenticationManager.authenticate(any())).thenThrow(new UsernameNotFoundException("Invalid credentials"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"wrongPassword\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testRegister_Success() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("test@example.com", "password", UserRole.USER);

        when(userRepository.findByEmail("test@example.com")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\",\"role\":\"USER\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegister_Failure_UserExists() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("test@example.com", "password", UserRole.USER);
        User existingUser = new User("test@example.com", "hashedPassword", UserRole.USER);

        when(userRepository.findByEmail("test@example.com")).thenReturn(existingUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\",\"role\":\"USER\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(userRepository, never()).save(any(User.class));
    }
}
