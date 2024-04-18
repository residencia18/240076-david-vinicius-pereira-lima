package org.aula08.aula08_car.repository;
import org.aula08.aula08_car.module.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long > { }
