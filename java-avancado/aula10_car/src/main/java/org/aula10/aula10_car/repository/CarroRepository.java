package org.aula10.aula10_car.repository;
import org.aula10.aula10_car.module.Carro;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro>findByPlaca(String placa);
    @Override
    <S extends Carro>List<S> findAll(Example<S> example);
}