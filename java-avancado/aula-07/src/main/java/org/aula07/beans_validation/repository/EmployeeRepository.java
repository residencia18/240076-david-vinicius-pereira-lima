package org.aula07.beans_validation.repository;


import org.aula07.beans_validation.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
