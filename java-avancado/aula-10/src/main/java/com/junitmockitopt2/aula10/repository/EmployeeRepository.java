package com.junitmockitopt2.aula10.repository;


import com.junitmockitopt2.aula10.module.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> findByName(String name);
    @Override
    <S extends Employee> List<S> findAll(Example<S> example);
}
