package org.aula07.aula07_car.repository;
import org.aula07.aula07_car.module.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcessionariaRepository  extends JpaRepository<Concessionaria, Long> {
}
