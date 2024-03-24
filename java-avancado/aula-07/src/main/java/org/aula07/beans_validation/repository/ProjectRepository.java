package org.aula07.beans_validation.repository;

import org.aula07.beans_validation.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
