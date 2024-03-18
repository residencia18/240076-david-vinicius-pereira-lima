package org.aula04.teste_ap_rest.controller;

import org.aula04.teste_ap_rest.module.Employee;
import org.aula04.teste_ap_rest.module.Project;
import org.aula04.teste_ap_rest.repository.EmployeeRepository;
import org.aula04.teste_ap_rest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @PostMapping(value = "/createEmployee")
    public String createEmployee(@RequestBody Employee entity){
        System.out.println("Create a new Employee.\n");
        Employee employee = new Employee(entity.getName(), entity.getEmail(), entity.getTechnicalSkill());
        employee = employeeRepository.save(employee);
        System.out.println("Saved employee:\t"+employee+"\n");
        return "Employee saved";
    }

    @PostMapping(value = "createEmployeeForProject/{projId}")
    public String createEmployeeForProject(@RequestBody Employee entity, @PathVariable(name = "projId") String projId){
        System.out.println("Create a new Employee and assing to an existing Project.\n");
        Employee employee = new Employee(entity.getName(), entity.getEmail(), entity.getTechnicalSkill());
        employeeRepository.save(employee);
        System.out.println("Saved employee:\t"+employee+"\n");
        Project project = this.projectRepository.getReferenceById(Integer.valueOf(projId));
        System.out.println("Project details:\t"+project.toString()+"\n");
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);
        project.setEmployees(employees);
        project = projectRepository.save(project);
        System.out.println("Employee assigned to the project.");
        return "Employee saved!!!";
    }

    @PostMapping(value = "/assignEmployeeToProject/{projId}")
    public String assignEmployeeToProject(@PathVariable(name = "projId") Integer projId){
        System.out.println("Fetch existing Employee details and assign them to an existing project.");
        int empId = 1;
        Employee employee1 = this.employeeRepository.getReferenceById(empId);
        System.out.println("Employee details:\t"+employee1.toString()+"\n");

        empId = 8;
        Employee employee2 = this.employeeRepository.getReferenceById(empId);
        System.out.println("Employee details:\t"+employee2.toString()+"\n");

        Project project = this.projectRepository.getReferenceById(projId);
        System.out.println("Project details:\t"+project.toString()+"\n");

        Set<Employee>employees = new HashSet<>();
        employees.add(employee1);
        employees.add(employee2);
        project.setEmployees(employees);
        project = projectRepository.save(project);
        System.out.println("Employees assigned to the Project.");
        return "Employee saved!!!";
    }

    @GetMapping(value = "/getEmployee/{empId}")
    public String getEmployee(@PathVariable(name = "empId") Integer empId){
        System.out.println("Fetch Employee and Project details.");
        Employee employee = this.employeeRepository.getReferenceById(empId);
        System.out.println("Employee details:\t"+employee.toString()+"\n");
        System.out.println("Project details:\t"+employee.getProjects()+"\n");
        System.out.println("Done!!!\n");
        return "Employee fetched sucessfully!!!";

    }

}
