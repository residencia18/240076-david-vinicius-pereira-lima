package org.aula06.aula06_car.repository;
import org.aula05.aula05_car.module.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long > { }
