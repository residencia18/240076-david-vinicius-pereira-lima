package org.aula08.aula08_car.repository;
import org.aula08.aula08_car.module.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Carro findCarroByPlaca(String placa);
}