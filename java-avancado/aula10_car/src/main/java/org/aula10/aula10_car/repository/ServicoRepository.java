package org.aula10.aula10_car.repository;
import org.aula10.aula10_car.module.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long > { }
