package org.aula08.java_faker.repository;

import org.aula08.java_faker.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
