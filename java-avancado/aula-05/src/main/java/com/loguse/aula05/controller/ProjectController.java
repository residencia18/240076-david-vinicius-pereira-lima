package com.loguse.aula05.controller;

import com.loguse.aula05.module.Employee;
import com.loguse.aula05.module.Project;
import com.loguse.aula05.repository.EmployeeRepository;
import com.loguse.aula05.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/project")

public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping("/createProject")
    public String createProject(@RequestBody Project entity){
        System.out.println("Create a new Project.");
        Project project = new Project(entity.getProjectName(), entity.getTechnologyUsed());
        project = projectRepository.save(project);
        System.out.println("Saved Project:\t"+project+"\n");
        return "Project saved!!!";
    }
    @PostMapping("/createProjectForEmployees")
    public String createProjectForEmployees(@RequestBody Project entity){
        System.out.println("Create a new Project and add existing Employees into this Project.");
        int empId = 1;
        Employee employee1 = employeeRepository.getReferenceById(empId);
        System.out.println("Employee details:\t"+employee1.toString()+"\n");
        empId = 2;
        Employee employee2 = employeeRepository.getReferenceById(empId);
        System.out.println("Employee details:\t"+employee2.toString()+"\n");

        Project project = new Project(entity.getProjectName(), entity.getTechnologyUsed());
        Set<Employee> employees = new HashSet<>();
        employees.add(employee1);
        employees.add(employee2);

        project.setEmployees(employees);
        project = projectRepository.save(project);
        System.out.println("\nSaved Project :: " + project + "\n");

        return "Project saved!!!";
    }

    @PostMapping("/assignProjectToEmployees/{projId}/{empId}")
    public String assignProjectToEmployees(@PathVariable(name = "projId") Integer projId, @PathVariable(name = "empId") Integer empId){
        System.out.println("\nFetch existing Project and add existing Employee into this Project." + "\n");

        // get Employee
        Employee employee = this.employeeRepository.getById(empId);
        System.out.println("\nEmployee details :: " + employee.toString() + "\n");

        // new Project
        Project project = this.projectRepository.getById(projId);
        System.out.println("\nProject details :: " + project.toString() + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);
        System.out.println("\nSaved Project :: " + project + "\n");

        return "Project saved!!!";
    }

    @GetMapping(value = "/getProject/{projId}")
    public String getProject(@PathVariable(name = "projId") Integer projId) {
        System.out.println("Fetch Project and its Employees." + "\n");

        // get Project details
        Project project = this.projectRepository.getById(projId);
        System.out.println("\nProject details :: " + project.toString() + "\n");
        System.out.println("\nEmployees details :: " + project.getEmployees() + "\n");

        System.out.println("Done!!!\n");

        return "Project fetched successfully!!!";
    }
}


