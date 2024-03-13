package org.aula01.crudpostgrees.repository;

import org.aula01.crudpostgrees.module.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> { }
