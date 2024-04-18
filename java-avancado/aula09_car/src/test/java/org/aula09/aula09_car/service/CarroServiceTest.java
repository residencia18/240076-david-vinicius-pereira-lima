package org.aula09.aula09_car.service;

import com.github.javafaker.Faker;
import org.aula09.aula09_car.module.Carro;
import org.aula09.aula09_car.repository.CarroRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CarroServiceTest {
    @Mock
    private CarroRepository carroRepository;
    @InjectMocks
    private CarroService carroService;
    private Carro carro;
    private Faker faker;
    @BeforeEach
    public void setUp() throws Exception {
        Random random = new Random();
        faker = new Faker(new Locale(("en-US")));
        carro = new Carro();
        carro.setId(1L);
        carro.setPlaca(faker.regexify("[A-Z]{3}\\d[A-Z]\\d{2}"));
        carro.setMarca(faker.commerce().productName());
        carro.setModelo(faker.company().name());
        carro.setAnoFabricacao(random.nextInt(1960, LocalDate.now().getYear()));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarFakeCarro(){
        given(carroRepository.save(any(Carro.class))).willReturn(carro);
        Carro savedCarro =  carroService.create(carro);
        verify(carroRepository).save(any(Carro.class));
        assertNotNull(savedCarro, "O carro salvo não pode ser nulo");
        assertEquals(carro.getPlaca(), savedCarro.getPlaca(), "Placa do carro não corresponde ao esperado");
        assertEquals(carro.getModelo(), savedCarro.getModelo(), "Modelo do carro não corresponde ao esperado");
        assertEquals(carro.getMarca(), savedCarro.getMarca(), "Marca do carro não corresponde ao esperado");
        assertEquals(carro.getAnoFabricacao(), savedCarro.getAnoFabricacao(), "Ano de fabricação não corresponde ao esperado");
    }

    @Test
    public void shouldUpdateCarroSuccessfully(){

    }
}
