package com.junitmockitopt2.aula10.repository;

import org.aula09.mokito_junit.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
