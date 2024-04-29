package org.aula13.aula13_car.test_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.aula12.aula12_car.controller.CarroController;
import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.service.CarroService;
import org.aula12.aula12_car.service.ConcessionariaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarroService carroService;

    @MockBean
    private ConcessionariaService concessionariaService;

    @Autowired
    private Faker faker;

    @TestConfiguration
    static class FakerTestConfig {

        @Bean
        public Faker faker() {
            return new Faker();
        }
    }

    private Carro generateFakeCarro(){
        Carro carro = new Carro();
        carro.setPlaca(faker.regexify("[A-Z]{3}\\d[A-Z]\\d{2}"));
        carro.setMarca(StringUtils.capitalize(faker.company().name()));
        carro.setModelo(faker.commerce().productName());
        carro.setAnoFabricacao(faker.random().nextInt(1960, LocalDate.now().getYear()));
        return carro;
    }

    @Test
    void createCarro_WithValidData_ReturnsCreated() throws Exception{
        Carro carroNovo = generateFakeCarro();

        Carro carroSalvo = generateFakeCarro();
        carroSalvo.setId(faker.number().randomNumber());

        when(carroService.create(any(Carro.class))).thenReturn(carroSalvo);

        mockMvc.perform(post("/carros")
                .content(objectMapper.writeValueAsString(carroNovo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(carroSalvo)));
    }

    @Test
    void getAllCarros_ReturnsListCarros()throws Exception{
        Carro carro = generateFakeCarro();
        when(carroService.findAll()).thenReturn(Arrays.asList(carro));

        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(carro))));
    }

    @Test
    void getCarroById_WhenCarroExists_ReturnsCarro() throws Exception{
        Carro carro = generateFakeCarro();
        when(carroService.findById(1L)).thenReturn(Optional.of(carro));

        mockMvc.perform(get("/carros/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carro)));
    }

    @Test
    void getCarroById_WhenCarroDoesntExists_ReturnsNotFound() throws Exception{
        when(carroService.findById(any(Long.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/carros/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCarro_WhenCarroExists_ReturnsUpdatedCarro() throws Exception{
        Carro updateInfo = generateFakeCarro();
        Carro updatedCarro = generateFakeCarro();

        updatedCarro.setPlaca("Updated "+updatedCarro.getPlaca());
        when(carroService.update(any(Long.class), any(Carro.class))).thenReturn(Optional.of(updatedCarro));

        mockMvc.perform(put("/carros/{id}",1)
                .content(objectMapper.writeValueAsString(updateInfo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedCarro)));

    }
}
