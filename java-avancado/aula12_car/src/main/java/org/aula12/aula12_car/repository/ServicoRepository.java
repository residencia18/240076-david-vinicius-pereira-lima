package org.aula12.aula12_car.repository;
import org.aula12.aula12_car.module.Servico;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long > {
    <S extends Servico> List<S> findAll(Example<S> example);
}
