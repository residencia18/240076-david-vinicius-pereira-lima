package org.aula04.aula04_car.repository;

import org.aula04.aula04_car.module.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long > { }
