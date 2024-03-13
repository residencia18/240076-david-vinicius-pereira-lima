package org.aula02.crudpostgresonetomany.repository;

import org.aula02.crudpostgresonetomany.module.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Carro findCarroByPlaca(String placa);
}