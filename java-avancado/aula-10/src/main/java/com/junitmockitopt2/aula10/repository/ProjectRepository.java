package com.junitmockitopt2.aula10.repository;

import com.junitmockitopt2.aula10.module.Project;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByProjectName(String name);
    @Override
    <S extends Project>List<S> findAll(Example <S> example);
}
