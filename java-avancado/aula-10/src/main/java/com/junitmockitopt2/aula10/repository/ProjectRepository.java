package com.junitmockitopt2.aula10.repository;

import com.junitmockitopt2.aula10.module.Project;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
