package org.aula11.aula11_car.test_service;

import com.github.javafaker.Faker;
import org.aula11.aula11_car.module.Servico;
import org.aula11.aula11_car.repository.ServicoRepository;
import org.aula11.aula11_car.service.ServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServicoServiceTest {
    @Mock
    private ServicoRepository servicoRepository;
    @InjectMocks
    private ServicoService servicoService;
    private Servico servico;
    private Faker faker;

    @BeforeEach
    public void setUp(){
        faker = new Faker(new Locale("en-US"));
        servico = new Servico();
        Date dataInicial = faker.date().future(365, TimeUnit.DAYS);
        Date dataFinal = faker.date().future(30, TimeUnit.DAYS, dataInicial);
        long duracaoMili = dataFinal.getTime() - dataInicial.getTime();
        long duracaoL = TimeUnit.MILLISECONDS.toSeconds(duracaoMili);
        Integer duracao = (int) duracaoL;
        Double preco =  Double.parseDouble(faker.commerce().price().replace(",", "."));

        servico.setId(1L);
        servico.setDescricao(faker.company().buzzword());
        servico.setDuracao(duracao);
        servico.setPreco(preco);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarServico(){
        given(servicoRepository.save(any(Servico.class))).willReturn(servico);
        Servico servicoSalvo = servicoService.create(servico);
        verify(servicoRepository).save(any(Servico.class));
        assertNotNull(servicoSalvo, "O serviço salvo não pode ser nulo");
        assertEquals(servico.getDescricao(), servicoSalvo.getDescricao(), "Descrição do serviço não corresponde ao esperado");
        assertEquals(servico.getDuracao(), servicoSalvo.getDuracao(), "A duração do serviço não corresponde ao esperado");
        assertEquals(servico.getPreco(), servicoSalvo.getPreco(), "O preço do serviço não corresponde ao esperado");
    }

    @Test
    public void shouldUpdateServicoSuccessfully(){
        Servico servicoAtualizado = new Servico();
        servicoAtualizado.setDescricao("Checkup Geral");
        servicoAtualizado.setDuracao(1200);
        servicoAtualizado.setPreco(1000.00);

        when(servicoRepository.findById(servico.getId())).thenReturn(Optional.of(servico));
        when(servicoRepository.save(any(Servico.class))).thenReturn(servicoAtualizado);

        Optional<Servico>resultado = servicoService.update(servico.getId(), servicoAtualizado);

        assertTrue(resultado.isPresent(),"O serviço atualizado deve existir");
        assertEquals(servicoAtualizado.getDescricao(), resultado.get().getDescricao(), "A descrição do serviço atualizado não corresponde ao esperado");
        assertEquals(servicoAtualizado.getDuracao(), resultado.get().getDuracao(), "A duração do serviço atualizado não corresponde ao esperado");
        assertEquals(servicoAtualizado.getPreco(), resultado.get().getPreco(), "O preço do serviço atualizado não corresponde ao esperado");

        verify(servicoRepository).findById(servico.getId());
        verify(servicoRepository).save(any(Servico.class));
    }
}
