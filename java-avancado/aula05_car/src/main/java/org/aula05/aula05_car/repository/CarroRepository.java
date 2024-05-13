package org.aula05.aula05_car.repository;
import org.aula05.aula05_car.module.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Carro findCarroByPlaca(String placa);
}