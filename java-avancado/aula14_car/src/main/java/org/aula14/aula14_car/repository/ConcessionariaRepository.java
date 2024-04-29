package org.aula14.aula14_car.repository;
import org.aula14.aula14_car.module.Concessionaria;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConcessionariaRepository  extends JpaRepository<Concessionaria, Long> {
    //Page<Concessionaria>findByNome(String nome);
    Page<Concessionaria> findAll(Pageable pageable);
    @Override
    <S extends Concessionaria> List<S> findAll(Example<S> example);
}
