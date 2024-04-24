package org.aula11.aula11_car.repository;
import org.aula11.aula11_car.module.Carro;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro>findByPlaca(String placa);

    @Query("select c from Carro c where c.anoFabricacao > 2000" )
    Carro findByJPQL();

    @Override
    <S extends Carro>List<S> findAll(Example<S> example);
}