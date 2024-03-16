package org.aula04.teste_ap_rest.controller;

import org.aula04.teste_ap_rest.module.Employee;
import org.aula04.teste_ap_rest.module.Project;
import org.aula04.teste_ap_rest.repository.EmployeeRepository;
import org.aula04.teste_ap_rest.repository.ProjectRepository;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

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
        Set<Employee>employees = new HashSet<>();
        employees.add(employee);


    }
}
