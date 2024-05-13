package org.aula12.aula12_car.test_controller;

import org.aula12.aula12_car.controller.CarroControllerV2;
import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.service.CarroServiceV2;
import org.aula12.aula12_car.service.ConcessionariaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class CarroControllerV2Test {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarroServiceV2 carroServiceV2;
    @MockBean
    private ConcessionariaService concessionariaService;

    @Test
    public void testFindAllSorted() throws Exception {
        List<Carro> carros = new ArrayList<>();
        when(carroServiceV2.findAllSorted(any(String[].class))).thenReturn(carros);

        mockMvc.perform(get("/v2/carros/sorted").param("sort", "campo1,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(carros.size())))
                .andReturn();
    }


}
