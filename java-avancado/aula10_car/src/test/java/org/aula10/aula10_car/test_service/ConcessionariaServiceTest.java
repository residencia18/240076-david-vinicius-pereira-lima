package org.aula10.aula10_car.test_service;

import com.github.javafaker.Faker;
import org.aula10.aula10_car.module.Concessionaria;
import org.aula10.aula10_car.repository.ConcessionariaRepository;
import org.aula10.aula10_car.service.ConcessionariaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConcessionariaServiceTest {
    @Mock
    private ConcessionariaRepository concessionariaRepository;
    @InjectMocks
    private ConcessionariaService concessionariaService;
    private Concessionaria concessionaria;
    private Faker faker;

    @BeforeEach
    public void setUp(){
        faker = new Faker(new Locale("en-US"));
        concessionaria = new Concessionaria();
        concessionaria.setId(1L);
        concessionaria.setNome(faker.company().name());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarFakeConcessionaria(){
        given(concessionariaRepository.save(any(Concessionaria.class))).willReturn(concessionaria);
        Concessionaria savedConcessionaria = concessionariaService.create(concessionaria);
        verify(concessionariaRepository).save(any(Concessionaria.class));
        assertNotNull(savedConcessionaria, "A concessionária salva não pode ser null");
        assertEquals(concessionaria.getNome(), savedConcessionaria.getNome(), "O nome da concessionária não corresponde ao esperado");
    }

    @Test
    public void shouldUpdateConcessionariaSuccessfully(){
        Concessionaria concAtt = new Concessionaria();
        concAtt.setNome("Concessionária 1");

        when(concessionariaRepository.findById(concessionaria.getId())).thenReturn(Optional.of(concessionaria));
        when(concessionariaRepository.save(any(Concessionaria.class))).thenReturn(concAtt);

        Optional<Concessionaria>resultado = concessionariaService.update(concessionaria.getId(), concAtt);

        assertTrue(resultado.isPresent(), "A concessionária atualizada deve existir");
        assertEquals(concAtt.getNome(), resultado.get().getNome(), "O nome da concessionária atualizada não corresponde ao esperado");

        verify(concessionariaRepository).findById(concessionaria.getId());
        verify(concessionariaRepository).save(any(Concessionaria.class));
    }
}
