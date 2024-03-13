package org.aula02.crudpostgresonetomany.repository;

import org.aula02.crudpostgresonetomany.module.Concessionaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcessionariaRepository  extends JpaRepository<Concessionaria , Long> {
}
