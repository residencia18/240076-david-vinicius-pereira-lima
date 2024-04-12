package org.aula04.aula04_car.repository;


import org.aula04.aula04_car.module.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcessionariaRepository  extends JpaRepository<Concessionaria, Long> {
}
