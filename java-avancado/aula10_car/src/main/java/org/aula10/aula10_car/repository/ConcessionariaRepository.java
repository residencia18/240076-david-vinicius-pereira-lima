package org.aula10.aula10_car.repository;
import org.aula10.aula10_car.module.Concessionaria;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConcessionariaRepository  extends JpaRepository<Concessionaria, Long> {
    @Override
    <S extends Concessionaria> List<S> findAll(Example<S> example);
}
