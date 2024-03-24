package org.aula06.lombok.controller;

import org.aula06.lombok.module.Employee;
import org.aula06.lombok.module.Project;
import org.aula06.lombok.repository.EmployeeRepository;
import org.aula06.lombok.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/project")

public class ProjectController {
    private static Logger log = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping("/createProject")
    public String createProject(@RequestBody Project entity){
        log.info("Create a new Project.");
        Project project = new Project(entity.getProjectName(), entity.getTechnologyUsed());
        project = projectRepository.save(project);
        log.info("Saved Project:\t"+project+"\n");
        return "Project saved!!!";
    }
    @PostMapping("/createProjectForEmployees")
    public String createProjectForEmployees(@RequestBody Project entity){
        log.info("Create a new Project and add existing Employees into this Project.");
        int empId = 1;
        Employee employee1 = employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee1.toString()+"\n");
        empId = 2;
        Employee employee2 = employeeRepository.getReferenceById(empId);
        log.info("Employee details:\t"+employee2.toString()+"\n");

        Project project = new Project(entity.getProjectName(), entity.getTechnologyUsed());
        Set<Employee> employees = new HashSet<>();
        employees.add(employee1);
        employees.add(employee2);

        project.setEmployees(employees);
        project = projectRepository.save(project);
        log.info("\nSaved Project :: " + project + "\n");

        return "Project saved!!!";
    }

    @PostMapping("/assignProjectToEmployees/{projId}/{empId}")
    public String assignProjectToEmployees(@PathVariable(name = "projId") Integer projId, @PathVariable(name = "empId") Integer empId){
        log.info("\nFetch existing Project and add existing Employee into this Project." + "\n");

        // get Employee
        Employee employee = this.employeeRepository.getById(empId);
        log.info("\nEmployee details :: " + employee.toString() + "\n");

        // new Project
        Project project = this.projectRepository.getById(projId);
        log.info("\nProject details :: " + project.toString() + "\n");

        // create Employee set
        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        // assign Employee Set to Project
        project.setEmployees(employees);

        // save Project
        project = projectRepository.save(project);
        log.info("\nSaved Project :: " + project + "\n");

        return "Project saved!!!";
    }

    @GetMapping(value = "/getProject/{projId}")
    public String getProject(@PathVariable(name = "projId") Integer projId) {
        log.info("Fetch Project and its Employees." + "\n");

        // get Project details
        Project project = this.projectRepository.getById(projId);
        log.info("\nProject details :: " + project.toString() + "\n");
        log.info("\nEmployees details :: " + project.getEmployees() + "\n");

        log.info("Done!!!\n");

        return "Project fetched successfully!!!";
    }
}


