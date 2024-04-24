package org.aula10.aula10_car.test_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.aula10.aula10_car.module.Carro;
import org.aula10.aula10_car.service.CarroService;
import org.aula10.aula10_car.service.ConcessionariaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroControllerTest.class)
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

        mockMvc.perform(post("/api/carros")
                .content(objectMapper.writeValueAsString(carroNovo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(carroSalvo)));
    }
}
