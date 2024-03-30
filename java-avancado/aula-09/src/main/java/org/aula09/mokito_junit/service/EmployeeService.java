package org.aula09.mokito_junit.service;

import org.aula09.mokito_junit.module.Employee;
import org.aula09.mokito_junit.repository.EmployeeRepository;

import java.util.Optional;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> update(Integer employeeId, Employee updatedEmployee){
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
        if(existingEmployee.isPresent()){
            updatedEmployee.setId(employeeId);
            return Optional.of(employeeRepository.save(updatedEmployee));
        }
        return Optional.empty();
    }
}
