package org.aula09.aula09_car.repository;
import org.aula09.aula09_car.module.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long > { }
