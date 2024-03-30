package org.aula09.mokito_junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.aula09.mokito_junit.module.Project;
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
