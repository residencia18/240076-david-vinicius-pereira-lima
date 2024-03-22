package com.loguse.aula05.repository;

import com.loguse.aula05.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
