package org.aula07.aula07_car.repository;
import org.aula07.aula07_car.module.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long > { }
