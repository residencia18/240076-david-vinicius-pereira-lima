package org.aula04.teste_ap_rest.repository;

import org.aula04.teste_ap_rest.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
