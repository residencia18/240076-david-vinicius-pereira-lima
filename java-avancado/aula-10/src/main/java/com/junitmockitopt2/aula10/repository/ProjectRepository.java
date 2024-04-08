package com.junitmockitopt2.aula10.repository;

import org.aula09.mokito_junit.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
