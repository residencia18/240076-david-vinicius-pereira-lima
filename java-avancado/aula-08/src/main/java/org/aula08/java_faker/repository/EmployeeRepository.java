package org.aula08.java_faker.repository;


import org.aula08.java_faker.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
