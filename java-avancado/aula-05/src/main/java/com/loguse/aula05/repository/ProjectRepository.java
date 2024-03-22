package com.loguse.aula05.repository;


import com.loguse.aula05.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
