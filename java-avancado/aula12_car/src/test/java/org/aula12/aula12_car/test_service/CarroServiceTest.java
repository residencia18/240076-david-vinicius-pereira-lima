package org.aula12.aula12_car.test_service;

import com.github.javafaker.Faker;
import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.repository.CarroRepository;
import org.aula12.aula12_car.service.CarroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {
    @Mock
    private CarroRepository carroRepository;
    @InjectMocks
    private CarroService carroService;
    private Carro carro;
    private Faker faker;

    @BeforeEach
    public void setUp(){
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
        Carro carroAtualizado = new Carro();
        carroAtualizado.setPlaca("XYZ2A24");
        carroAtualizado.setModelo("Modelo A");
        carroAtualizado.setMarca("Marca B");
        carroAtualizado.setAnoFabricacao(2021);

        when(carroRepository.findById(carro.getId())).thenReturn(Optional.of(carro));
        when(carroRepository.save(any(Carro.class))).thenReturn(carroAtualizado);

        Optional<Carro>resultado = carroService.update(carro.getId(), carroAtualizado);

        assertTrue(resultado.isPresent(), "O carro atualizado deve existir");
        assertEquals(carroAtualizado.getPlaca(), resultado.get().getPlaca(), "A placa do carro atualizado não corresponde ao esperado");
        assertEquals(carroAtualizado.getModelo(), resultado.get().getModelo(), "O modelo do carro atualizado não corresponde ao esperado");
        assertEquals(carroAtualizado.getMarca(), resultado.get().getMarca(), "A marca do carro atualizado não corresponde ao esperado");
        assertEquals(carroAtualizado.getAnoFabricacao(), resultado.get().getAnoFabricacao(), "O ano de fabricação não corresponde ao esperado");

        verify(carroRepository).findById(carro.getId());
        verify(carroRepository).save(any(Carro.class));
    }
}
