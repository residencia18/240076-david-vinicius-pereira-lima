package org.aula11.aula11_car.repository;
import org.aula11.aula11_car.module.Concessionaria;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConcessionariaRepository  extends JpaRepository<Concessionaria, Long> {
    @Override
    <S extends Concessionaria> List<S> findAll(Example<S> example);
}
