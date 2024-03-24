package org.aula06.lombok.repository;


import org.aula06.lombok.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
