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
    public Optional<Employee> findById(Integer id){
        return employeeRepository.findById(id);
    }

    public Optional<Employee> findByName(String name){
        return employeeRepository.findByName(name);
    }

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> update(Integer employeeId, Employee updatedEmployee){
        return employeeRepository.findById(employeeId).
                map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setTechnicalSkill(updatedEmployee.getTechnicalSkill());
                    return employeeRepository.save(employee);
                });
    }

    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }
}
