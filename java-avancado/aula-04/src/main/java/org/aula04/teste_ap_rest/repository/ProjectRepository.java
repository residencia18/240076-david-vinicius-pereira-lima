package org.aula04.teste_ap_rest.repository;

import org.aula04.teste_ap_rest.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
