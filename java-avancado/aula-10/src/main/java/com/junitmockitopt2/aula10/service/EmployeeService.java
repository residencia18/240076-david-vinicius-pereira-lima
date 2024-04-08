package com.junitmockitopt2.aula10.service;


import com.junitmockitopt2.aula10.module.Employee;
import com.junitmockitopt2.aula10.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

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
