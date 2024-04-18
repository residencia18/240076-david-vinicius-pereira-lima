package org.aula07.aula07_car.repository;


import org.aula07.aula07_car.module.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Carro findCarroByPlaca(String placa);
}