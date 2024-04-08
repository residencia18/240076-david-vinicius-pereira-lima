package com.junitmockitopt2.aula10.controller;

import com.github.javafaker.Faker;
import com.junitmockitopt2.aula10.module.Employee;
import com.junitmockitopt2.aula10.module.Project;
import com.junitmockitopt2.aula10.repository.EmployeeRepository;
import com.junitmockitopt2.aula10.repository.ProjectRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @PostMapping(value = "/generateFakeEmployee/")
    public String generateFakerEmployee(){
        log.info("Create a new Employee using JavaFaker data.\n");
        Faker usFaker = new Faker(new Locale("en-US"));
        Employee employee = new Employee(usFaker.name().fullName(), usFaker.internet().emailAddress(), StringUtils.capitalize(usFaker.company().profession()));
        employeeRepository.save(employee);
        log.info("Saved employee:\t"+employee+"\n");
        return "Employee saved";
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee){
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @PostMapping(value = "/{projId}/assign")
    public String createEmployee(@RequestBody List<Employee> entitys){
        log.info("Create a new Employee.\n");
        for (Employee entity : entitys) {
            Employee employee = new Employee(entity.getName(), entity.getEmail(), entity.getTechnicalSkill());
            employee = employeeRepository.save(employee);
            log.info("Saved employee:\t" + employee + "\n");
        }
        return "Employee saved";
    }

    @PostMapping(value = "createEmployeeForProject/{projId}")
    public String createEmployeeForProject(@RequestBody Employee entity, @PathVariable(name = "projId") String projId){
        log.info("Create a new Employee and assing to an existing Project.\n");
        Employee employee = new Employee(entity.getName(), entity.getEmail(), entity.getTechnicalSkill());
        employeeRepository.save(employee);
        log.info("Saved employee:\t"+employee+"\n");
        Project project = this.projectRepository.getReferenceById(Integer.valueOf(projId));
        log.info("Project details:\t"+project.toString()+"\n");
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);
        project = projectRepository.save(project);
        log.info("Employee assigned to the project.");
        return "Employee saved!!!";
    }

    @PostMapping(value = "/assignEmployeeToProject/{projId}")
    public String assignEmployeeToProject(@PathVariable(name = "projId") Integer projId){
        log.info("Fetch existing Employee details and assign them to an existing project.");
        int empId = 1;
        Employee employee1 = this.employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee1.toString()+"\n");

        empId = 8;
        Employee employee2 = this.employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee2.toString()+"\n");

        Project project = this.projectRepository.getReferenceById(projId);
        log.info("Project details:\t"+project.toString()+"\n");

        Set<Employee>employees = new HashSet<>();
        employees.add(employee1);
        employees.add(employee2);
        project.setEmployees(employees);
        project = projectRepository.save(project);
        log.info("Employees assigned to the Project.");
        return "Employee saved!!!";
    }

    @GetMapping(value = "/getEmployee/{empId}")
    public String getEmployee(@PathVariable(name = "empId") Integer empId){
        log.info("Fetch Employee and Project details.");
        Employee employee = this.employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee.toString()+"\n");
        log.info("Project details:\t"+employee.getProjects()+"\n");
        log.info("Done!!!\n");
        return "Employee fetched sucessfully!!!";

    }

}
