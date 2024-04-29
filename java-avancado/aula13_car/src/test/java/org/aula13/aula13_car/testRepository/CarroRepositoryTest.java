package org.aula13.aula13_car.testRepository;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.aula12.aula12_car.module.Carro;
import org.aula12.aula12_car.repository.CarroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarroRepositoryTest {
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private Faker faker;

    @AfterEach
    public void setUp(){
    }

    @TestConfiguration
    static class FakerTestConfig{
        @Bean
        public Faker faker(){
            return new Faker();
        }
    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(testEntityManager).isNotNull();
        assertThat(carroRepository).isNotNull();
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
    void createCarro_withValidData_ReturnsCarro(){
        Carro carro = generateFakeCarro();
        Carro carroSalvo = carroRepository.save(carro);
        assertThat(carroSalvo).isNotNull();
        assertThat(carroSalvo.getId()).isGreaterThan(0);
        assertThat(carroSalvo.getPlaca()).isEqualTo(carro.getPlaca());
        Logger log = LoggerFactory.getLogger(CarroRepositoryTest.class);
        assertThat(carroSalvo.getMarca()).isEqualTo(carro.getMarca());
        assertThat(carroSalvo.getModelo()).isEqualTo(carro.getModelo());
        assertThat(carroSalvo.getAnoFabricacao()).isEqualTo(carro.getAnoFabricacao());
    }

    @Test
    void createCarro_withExistingPlaca_ThrowsException(){
        Carro carro1 = generateFakeCarro();
        testEntityManager.persistFlushFind(carro1);

        Carro carro2 = generateFakeCarro();
        carro2.setPlaca(carro1.getPlaca());

        assertThatThrownBy(()->carroRepository.save(carro2)).isInstanceOf(Exception.class);
    }


    @Test
    void findCarro_ById_ReturnsCarro(){
        Carro carro = generateFakeCarro();
        Carro persistedCarro = testEntityManager.persistFlushFind(carro);

        Optional<Carro> foundCarro = carroRepository.findById(persistedCarro.getId());

        assertThat(foundCarro).isNotEmpty();
        assertThat(foundCarro.get().getId()).isEqualTo(persistedCarro.getId());
    }

    @Test
    void findCarros_ByAnoGreatterThan2000_ReturnsListCarros(){
        Carro carro = generateFakeCarro();
        carroRepository.save(carro);

        Carro getCarros = carroRepository.findByJPQL();

        assertThat(getCarros).isNotNull();
    }

    @Test
    void findCarro_ByPlaca_ReturnsCarro(){
        Carro carro = generateFakeCarro();
        Carro persistedCarro = testEntityManager.persistFlushFind(carro);

        Optional<Carro> foundCarro = carroRepository.findByPlaca(persistedCarro.getPlaca());

        assertThat(foundCarro).isNotEmpty();
        assertThat(foundCarro.get().getPlaca()).isEqualTo(persistedCarro.getPlaca());
    }

    @Test
    void listCarros_ReturnsAllCarros(){
        Carro carro1 = generateFakeCarro();
        Carro carro2 = generateFakeCarro();
        testEntityManager.persistFlushFind(carro1);
        testEntityManager.persistFlushFind(carro2);

        List<Carro>carros = carroRepository.findAll();

        assertThat(carros).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void deleteCarro_WithExistingId_RemoveCarro(){
        Carro carro = generateFakeCarro();
        Carro persistedCarro = testEntityManager.persistFlushFind(carro);

        carroRepository.deleteById(persistedCarro.getId());

        Carro carroDeletado = testEntityManager.find(Carro.class, persistedCarro.getId());

        assertThat(carroDeletado).isNull();
    }
}
